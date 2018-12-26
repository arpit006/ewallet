package com.ewallet.mywallet.common.audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class NotificationServiceAuditorAware implements AuditorAware<String> {

    // TODO: Get username from security context
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("E-Wallet User");
    }
}