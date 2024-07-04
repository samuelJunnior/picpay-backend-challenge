package br.com.samueljunnior.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

public record InvalidField(String field, String reason) {
}
