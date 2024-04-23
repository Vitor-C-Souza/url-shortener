package br.com.vitorcsouza.urlshort.repository;

import br.com.vitorcsouza.urlshort.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Url findByUrlshortened(String urlShortened);

    boolean existsByUrl(String url);

    Url findByUrl(String url);

    boolean existsByUrlshortened(String shortlink);
}
