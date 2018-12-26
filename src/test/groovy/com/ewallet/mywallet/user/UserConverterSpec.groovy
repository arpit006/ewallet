package com.ewallet.mywallet.user

import com.ewallet.mywallet.userdetails.UserConverter
import com.ewallet.mywallet.userdetails.UserDetails
import com.ewallet.mywallet.userdetails.UserDetailsVo
import reactor.core.publisher.Mono
import spock.lang.Shared
import spock.lang.Specification

class UserConverterSpec extends Specification {

    @Shared
    UserConverter converter = new UserConverter()


    def "To convert Entity to Vo"() {
        given:
            UserDetails userDetails = new UserDetails()
            userDetails.setName("Arpit")
            userDetails.setMobileNo("123")
            userDetails.setEmail("abc@xyz.com")
            userDetails.setUuid("test_uuid")
            userDetails.setWalletBalance(new BigDecimal(100))

        when:
        UserDetailsVo userDetailsVo = converter.convertEntityToVO(userDetails)

        then:
            userDetailsVo.name == "Arpit"
            userDetailsVo.email == "abc@xyz.com"
            userDetailsVo.mobileNo == "123"
            userDetailsVo.walletBalance == 100
            userDetailsVo.uuid == "test_uuid"

    }

    def "To convert Vo to Entity"() {
        given:
            UserDetailsVo userDetailsVo = UserDetailsVo.builder()
                                                        .uuid("test_uuid")
                                                        .name("Arpit")
                                                        .mobileNo("123")
                                                        .email("abc@xyz.com")
                                                        .walletBalance(new BigDecimal(100))
                                                        .build()

        when:
            UserDetails userDetails = converter.convertVoToEntity(userDetailsVo)

        then:
            userDetails.name == "Arpit"
            userDetails.uuid == "test_uuid"
            userDetails.email == "abc@xyz.com"
            userDetails.mobileNo == "123"
            userDetails.walletBalance == 100
    }

    /*def "To update entity from Vo"() {
        given:
        UserDetailsVo userDetailsVo = UserDetailsVo.builder()
                .uuid("test_uuid")
                .name("Arpit")
                .mobileNo("123")
                .email("abc@xyz.com")
                .walletBalance(new BigDecimal(100))
                .password("hello123")
                .userName("arpit")
                .build()

        UserDetails userDetails = new UserDetails()
        userDetails.setName("Arpit Srivastava")
        userDetails.setMobileNo("12345")
        userDetails.setEmail("xyz@abc.com")
        userDetails.setUuid("test_uuidss")
        userDetails.setWalletBalance(new BigDecimal(1000))
        userDetails.setPassword("hello1234")
        userDetails.setUserName("Arp")

        when:
        UserDetailsVo updatedUserVo = converter.updateEntityFromVo(userDetails, userDetailsVo)

        then:
        updatedUserVo.walletBalance == 100
        updatedUserVo.name == "Arpit"
        updatedUserVo.mobileNo == "12345"
        updatedUserVo.email == "abc@xyz.com"
        updatedUserVo.userName == "arpita"
    }*/
}
