package br.com.vitorcsouza.urlshort.service;

import br.com.vitorcsouza.urlshort.dto.UrlDto;
import br.com.vitorcsouza.urlshort.model.Url;
import br.com.vitorcsouza.urlshort.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;


@Service
public class UrlService {
    @Autowired
    private UrlRepository repository;

    public UrlDto setShortUrl(UrlDto dto) {

        Url url;
        boolean existShortlink = repository.existsByUrl(dto.getUrl());

        if(!existShortlink){
            url = new Url(
                    dto.getUrl(),
                    encodeUrl()
            );
            repository.save(url);
        } else{
            url = repository.findByUrl(dto.getUrl());
        }

        dto.setExpirationdate(url.getExpirationdate());
        dto.setId(url.getId());
        dto.setUrlshortened(url.getUrlshortened());

        return dto;
    }

    private String encodeUrl() {
        SecureRandom random = new SecureRandom();
        int Length = 6;
        byte[] bytes = new byte[Length];
        random.nextBytes(bytes);

        return Base64.getUrlEncoder().encodeToString(bytes);
    }

    public String getOriginalLink(String shortlink) {
        boolean existeShortlink = repository.existsByUrlshortened(shortlink);
        if(existeShortlink){
            Url url = repository.findByUrlshortened(shortlink);

            if (LocalDateTime.now().isAfter(url.getExpirationdate())) {
                repository.delete(url);
                return null;
            }
            url.useUrl();
            repository.save(url);
            System.out.println(url.getCountuse());

            return url.getUrl();
        } else {
            return null;
        }

    }


}
