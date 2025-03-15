package br.com.gama.leituras.frontend.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record LeituraDto(@NotBlank String idLogger,
                         float valor,
                         @NotNull LocalDate data,
                         Long id,
                         @NotBlank String responsavel) {


}
