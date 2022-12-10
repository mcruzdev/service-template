# Microservice Patterns: Service Template

Esse repositório é um exemplo de como podemos utilizar o padrão [Service Template](https://microservices.io/patterns/service-template.html) na prática.

## Cookiecutter

Nós iremos utilizar o [cookiecutter](https://cookiecutter.readthedocs.io/en/stable/) como ferramenta para gerarmos nossa aplicação a partir de um template.

> Existem diversos tipos de ferramentas para fazer esse trabalho: [Copier](https://copier.readthedocs.io/en/stable/) (Python), [Boilr](https://github.com/tmrts/boilr) (GoLang), [Yemon](https://yeoman.io/) (NodeJS), etc.

Por debaixo dos panos o cookiecutter utiliza [jinja](https://jinja.palletsprojects.com/en/3.1.x/), por isso podemos ver um tratamento especial no que passamos como parâmetro para o cookiecutter.

Nesse template estamos utilizando dos filtros do `jinja` (`replace` e `lower`) para tratar alguns textos que fazer parte do nosso código. Validar tudo com o jinja no código pode ser trabalhoso, para resolvermos esse tipo de problema precisamos de um tratamento melhor nos parâmetros passados ao chamar o cookiecutter.

> Se você quiser algo mais controlado, você pode criar seu próprio CLI e aplicar as validações e integrações necessárias para o seu cenário. Temos um [exemplo de CLI](./custom-cli.py) onde você pode ter como base para entendimento.

## Substituição

Como você pode ver no template, alguns arquivos Java estão fazendo a utilização do template, indicando que aquele ponto do código deve ser substituído por um valor vindo do usuário, como é o caso da classe [`Application.java`](./%7B%7B%20cookiecutter.application_name%20%7D%7D/src/main/java/com/github/mcruzdev/servicetemplate/Application.java).

```java
package com.github.{{ cookiecutter.user }}.{{ cookiecutter.application_name | replace('-', '') | lower }};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
```

## Utilização

Para utilizar a ferramenta é bem simples, basta você utilizar a ferramenta escolhida, em nosso caso o cookiecutter.

```sh
    cookiecutter git@github.com:mcruzdev/service-template.git
```

Se você preferir pode utilizar o CLi do template:

Para isso é necessário o python3 instalado.

Execute o comando `generate`:
```sh
    python3 custom-cli.py generate my-service-template --user mcruzdev
```

Suba a aplicação localmente:
```sh
    cd myservicetemplate
    ./gradlew bootRun
```

Abra seu navegador e acesse `http://localhost:8080/info` ou execute:

```sh
    curl localhost:8080/info
```

O resultado deve se parecer com isso:

```{"applicationName":"myservicetemplate","version":"0.0.1"}```
