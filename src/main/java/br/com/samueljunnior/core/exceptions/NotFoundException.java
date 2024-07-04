package br.com.samueljunnior.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class NotFoundException extends PicPayException {

    @Override
    public ProblemDetail toProblmeDetail() {
        final var problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Resource not found");
        return super.toProblmeDetail();
    }
}
