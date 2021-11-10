package com.felix.fonteneau.contentdrivenrest.controller;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.felix.fonteneau.contentdrivenrest.model.Contentable;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class CMSController {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @PostMapping(path = "/addContent", consumes = "application/json")
    public ResponseEntity<Object> addContent(@RequestBody Contentable contentable) {

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(contentable.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
