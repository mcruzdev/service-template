package com.github.{{ cookiecutter.user }}.{{ cookiecutter.application_name | replace('-', '') | lower }};

public record InfoOutput(
        String applicationName,
        String version
) {
}
