package com.github.{{ cookiecutter.user }}.{{ cookiecutter.application_name | replace('-', '') | lower }};

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {

    private final String applicationName;
    private final String version;

    public InfoController(
            @Value("${app.name}") String applicationName,
            @Value("${app.version}") String version
    ) {
        this.applicationName = applicationName;
        this.version = version;
    }

    @GetMapping
    public ResponseEntity<InfoOutput> info() {
        var info = new InfoOutput(applicationName, version);
        return ResponseEntity.ok(info);
    }

}
