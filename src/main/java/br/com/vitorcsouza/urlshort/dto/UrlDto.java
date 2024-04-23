package br.com.vitorcsouza.urlshort.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class UrlDto {
    private Long id;
    @NotBlank
    private String url;
    private String urlshortened;
    private LocalDateTime expirationdate;
}
