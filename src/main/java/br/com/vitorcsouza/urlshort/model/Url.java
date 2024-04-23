package br.com.vitorcsouza.urlshort.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "url_short")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 200)
    private String url;

    @NotBlank
    @Size(max = 10)
    private String urlshortened;

    @NotNull
    private Integer countuse;

    @NotNull
    private LocalDateTime creationdate;

    @NotNull
    private LocalDateTime expirationdate;

    public Url( String url, String urlshortened) {
        this.url = url;
        this.urlshortened = urlshortened;
        this.countuse = 0;
        this.creationdate = LocalDateTime.now();
        this.expirationdate = LocalDateTime.now().plusWeeks(1);
    }

    public void useUrl(){
        this.countuse++;
    }
}
