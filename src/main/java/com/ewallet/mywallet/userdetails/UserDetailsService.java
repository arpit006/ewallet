package com.ewallet.mywallet.userdetails;

import com.ewallet.mywallet.common.exception.IncorrectPasswordException;
import com.ewallet.mywallet.common.exception.InsufficientFundsException;
import com.ewallet.mywallet.common.exception.UserNotFoundException;
import com.ewallet.mywallet.transactions.ITransactionsService;
import com.ewallet.mywallet.transactions.TransactionsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class UserDetailsService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private ITransactionsService transactionsService;


    private DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    private Date today = Calendar.getInstance().getTime();
    private String transactionDate = df.format(today);

    @Override
    public Mono<UserDetailsVo> signUp(UserDetailsVo userDetailsVo) {
        TransactionsVo transactionsVo = TransactionsVo.builder()
                .sender(userDetailsVo.getUserName())
                .receiver("started e-wallet with initial balance = ")
                .amount(new BigDecimal(0))
                .time(transactionDate)
                .build();

        transactionsService.buildTransaction(transactionsVo);

        String plainTextPassword = userDetailsVo.getPassword();
        String salt = BCrypt.gensalt();
        String hashed = BCrypt.hashpw(plainTextPassword, salt);

        return Mono.just(userConverter.convertVoToEntity(userDetailsVo.withPassword(hashed)))
                .map(e -> userRepository.save(e))
                .flatMap(t -> Mono.just(userConverter.convertEntityToVO(t)));
    }

    @Override
    public Mono<UserDetailsVo> findByUsername(String userName)throws UserNotFoundException {
        if(userRepository.findByUserName(userName) != null) {
            return Mono.just(userRepository.findByUserName(userName))
                    .flatMap(t -> Mono.just(userConverter.convertEntityToVO(t)));
        }
        else throw new UserNotFoundException();
    }

    @Override
    public Mono<UserDetailsVo> addBalance(String userName, BigDecimal amount)throws UserNotFoundException {

        if(userRepository.findByUserName(userName) != null) {
            TransactionsVo transactionsVo = TransactionsVo.builder()
                    .sender(userName)
                    .receiver("Deposit")
                    .amount(amount)
                    .time(transactionDate)
                    .build();

            transactionsService.buildTransaction(transactionsVo);

            return Mono.just(userConverter.convertEntityToVO(userRepository.findByUserName(userName)))
                    .map(u -> u.withUuid(u.getUuid())
                            .withUserName(u.getUserName())
                            .withEmail(u.getEmail())
                            .withMobileNo(u.getMobileNo())
                            .withName(u.getName())
                            .withWalletBalance(u.getWalletBalance().add(amount))
                            .withPassword(u.getPassword()))
                    .flatMap(l -> Mono.just(userConverter.convertVoToEntity(l)))
                    .map(g -> userRepository.save(g))
                    .flatMap(p -> Mono.just(userConverter.convertEntityToVO(p)));
        }
        else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public Mono<UserDetailsVo> withdrawBalance(String userName, BigDecimal amount)throws InsufficientFundsException, UserNotFoundException {

        if(userRepository.findByUserName(userName) != null) {
            if(userRepository.findByUserName(userName).getWalletBalance().compareTo(amount) >= 0.0) {
                TransactionsVo transactionsVo = TransactionsVo.builder()
                        .sender(userName)
                        .receiver("Withdraw")
                        .amount(amount)
                        .time(transactionDate)
                        .build();

                transactionsService.buildTransaction(transactionsVo);

                return Mono.just(userConverter.convertEntityToVO(userRepository.findByUserName(userName)))
                        .map(u -> u.withUuid(u.getUuid())
                                .withUserName(u.getUserName())
                                .withEmail(u.getEmail())
                                .withMobileNo(u.getMobileNo())
                                .withName(u.getName())
                                .withWalletBalance(u.getWalletBalance().subtract(amount))
                                .withPassword(u.getPassword()))
                        .flatMap(l -> Mono.just(userConverter.convertVoToEntity(l)))
                        .map(g -> userRepository.save(g))
                        .flatMap(p -> Mono.just(userConverter.convertEntityToVO(p)));
            }
            else throw new InsufficientFundsException();
        }
        else throw new UserNotFoundException();

    }

    @Override
    public Mono<UserDetailsVo> transferBalance(String from, String to, BigDecimal amount) {
        UserDetailsVo receiver = userConverter.convertEntityToVO(userRepository.findByUserName(to));
        BigDecimal updatedReceiverBalance = receiver.getWalletBalance().add(amount);

        UserDetailsVo updatedReceiver = UserDetailsVo
                .builder()
                .uuid(receiver.getUuid())
                .name(receiver.getName())
                .mobileNo(receiver.getMobileNo())
                .email(receiver.getEmail())
                .walletBalance(updatedReceiverBalance)
                .userName(receiver.getUserName())
                .password(receiver.getPassword())
                .build();

        TransactionsVo transactionsVo = TransactionsVo.builder()
                .sender(from)
                .receiver(to)
                .amount(amount)
                .time(transactionDate)
                .build();

        transactionsService.buildTransaction(transactionsVo);

        userRepository.save(userConverter.convertVoToEntity(updatedReceiver));

        return Mono.just(userConverter.convertEntityToVO(userRepository.findByUserName(from)))
                .map(v -> v.withUuid(v.getUuid())
                        .withName(v.getName())
                        .withUserName(v.getUserName())
                        .withEmail(v.getEmail())
                        .withMobileNo(v.getMobileNo())
                        .withPassword(v.getPassword())
                        .withWalletBalance(v.getWalletBalance().subtract(amount)))
                .flatMap(l -> Mono.just(userConverter.convertVoToEntity(l)))
                .map(t -> userRepository.save(t))
                .flatMap(v -> Mono.just(userConverter.convertEntityToVO(v)));

    }


    @Override
    public Mono<UserDetailsVo> updateUserInfo(String userName, UserDetailsVo userDetailsVo)throws UserNotFoundException {
        if(userRepository.findByUserName(userName) != null) {
            return Mono.just(userConverter.convertEntityToVO(userRepository.findByUserName(userName)))
                    .map(u -> u.withUuid(u.getUuid())
                            .withUserName(userDetailsVo.getUserName() == null ? u.getUserName() : userDetailsVo.getUserName())
                            .withEmail(userDetailsVo.getEmail() == null ? u.getEmail() : userDetailsVo.getEmail())
                            .withMobileNo(userDetailsVo.getMobileNo() == null ? u.getMobileNo() : userDetailsVo.getMobileNo())
                            .withName(userDetailsVo.getName() == null ? u.getName() : userDetailsVo.getName())
                            .withWalletBalance(u.getWalletBalance())
                            .withPassword(u.getPassword()))
                    .flatMap(l -> Mono.just(userConverter.convertVoToEntity(l)))
                    .map(g -> userRepository.save(g))
                    .flatMap(p -> Mono.just(userConverter.convertEntityToVO(p)));
        }
        else throw new UserNotFoundException();

    }


    @Override
    public Mono<UserDetailsVo> findByMobileNo(String mobileNo)throws UserNotFoundException {
        if (userRepository.findByMobileNo(mobileNo) != null) {
            return Mono.just(userRepository.findByMobileNo(mobileNo))
                    .flatMap(u -> Mono.just(userConverter.convertEntityToVO(u)));
        }
        else throw new UserNotFoundException();
    }

    @Override
    public Mono<UserDetailsVo> findLoginDetails(String userName, String password) throws IncorrectPasswordException, UserNotFoundException {
        if(userRepository.findByUserName(userName) != null) {
            String hashed = userRepository.findByUserName(userName).getPassword();
            if (BCrypt.checkpw(password, hashed)) {
                return Mono.just(userRepository.findByUserName(userName))
                        .flatMap(t -> Mono.just(userConverter.convertEntityToVO(t)));
            } else throw new IncorrectPasswordException();

        }
        else throw new UserNotFoundException();

    }

}
