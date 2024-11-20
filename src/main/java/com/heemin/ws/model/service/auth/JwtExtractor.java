package com.heemin.ws.model.service.auth;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

public final class JwtExtractor {

    private JwtExtractor() {
    }

    public static Optional<String> extract(HttpServletRequest request) {
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (!StringUtils.hasText(header) || !header.toLowerCase().startsWith("bearer ")) {
            return Optional.empty();
        }

        return Optional.of(header.split(" ")[1]);
    }
}
