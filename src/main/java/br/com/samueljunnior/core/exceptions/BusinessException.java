package br.com.samueljunnior.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

@AllArgsConstructor
@NoArgsConstructor
public class BusinessException extends PicPayException {
    private String error;
    private HttpStatus statusCode;

    public BusinessException(String error){
        this.statusCode = HttpStatus.BAD_REQUEST;
        this.error = error;
    }

    @Override
    public ProblemDetail toProblmeDetail() {
        final var problemDetail = ProblemDetail
                .forStatusAndDetail(this.statusCode, this.error);

        problemDetail.setTitle("It´s a business exception.");

        return problemDetail;
    }
}
