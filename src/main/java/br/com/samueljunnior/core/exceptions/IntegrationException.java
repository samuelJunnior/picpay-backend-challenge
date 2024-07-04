package br.com.samueljunnior.core.exceptions;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

@RequiredArgsConstructor
public class IntegrationException extends PicPayException {

    private final String detail;

    @Override
    public ProblemDetail toProblmeDetail() {
        final var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, this.detail);
        problemDetail.setTitle("Integration Error");
        return problemDetail;
    }
}
