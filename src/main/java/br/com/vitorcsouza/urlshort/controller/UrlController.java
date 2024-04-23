package br.com.vitorcsouza.urlshort.controller;

import br.com.vitorcsouza.urlshort.dto.UrlDto;
import br.com.vitorcsouza.urlshort.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/")
public class UrlController {

    @Autowired
    private UrlService service;

    @PostMapping("generate")
    @Transactional
    public ResponseEntity<UrlDto> GenerateUrlShort(@RequestBody @Valid UrlDto dto, UriComponentsBuilder uriComponentsBuilder){
        UrlDto url = service.setShortUrl(dto);
        URI endereco = uriComponentsBuilder.path("/generate/{id}").buildAndExpand(url.getId()).toUri();

        return ResponseEntity.created(endereco).body(url);
    }

    @GetMapping("{shortlink}")
    public ResponseEntity<?> RedirectLink(@PathVariable String shortlink, HttpServletResponse response) throws IOException {
        String url = service.getOriginalLink(shortlink);
        if(url == null){
            System.out.println("sem shortlink");
            return ResponseEntity.noContent().build();
        }
        response.sendRedirect(url);
        return null;
    }
}
