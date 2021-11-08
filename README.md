<h1>API REST Grupin-JC</h1>

<p align="center">
  <img src="https://img.shields.io/static/v1?label=Java%2017&message=linguagem&color=red&style=for-the-badge&logo=java"/>
  <img src="https://img.shields.io/static/v1?label=Spring&message=framework&color=green&style=for-the-badge&logo=spring"/>
  <img src="https://img.shields.io/static/v1?label=License&message=Apache%202.0&color=green&style=for-the-badge"/>
  <img src="http://img.shields.io/static/v1?label=PostgreSQL&message=9.5&color=blue&style=for-the-badge&logo=postgreSQL"/>
  <img src="https://img.shields.io/static/v1?label=Swagger%20UI&message=3.0&color=green&style=for-the-badge&logo=swagger"/>
  <img src="https://img.shields.io/static/v1?label=Hibernate&message=framework&color=blue&style=for-the-badge&logo=hibernate"/>
   <img src="http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=RED&style=for-the-badge"/>
</p>

> Status do Projeto: :warning: Em desenvolvimento

### Tópicos

:small_blue_diamond: [Descrição do projeto](#descrição-do-projeto)

:small_blue_diamond: [Funcionalidades](#funcionalidades)

:small_blue_diamond: [Aplicação em produção](#aplicação-em-produção-dash)

:small_blue_diamond: [Pré-requisitos](#pré-requisitos)

:small_blue_diamond: [Como rodar a aplicação](#como-rodar-a-aplicação-arrow_forward)

## Descrição do projeto

<p align="justify">
  O presente projeto tem como principal objetivo ter uma proximidade maior do docente para com o discente. Utilizando a <strong>arquitetura REST</strong> para visando uma ampliação do projeto e curva de aprendizado para os desenvolvedores posteriores, buscamos levar mais conhecimento para os nossos pesquisadores.
</p>

## Funcionalidades

:heavy_check_mark: Upload de imagens

:heavy_check_mark: Disparo de email próprio

:heavy_check_mark: Salvamento de emails para uma Newslleter

## Aplicação em produção :dash:

> Link do site que usa essa API: https://grupin-jc.github.io

## Pré-requisitos

:warning: [Java 17](https://jdk.java.net/17/)

:warning: [Spring boot](https://start.spring.io/)

:warning: [Lombok](https://projectlombok.org/setup/maven)

:warning: [Spring-Data-JPA](https://docs.spring.io/spring-data/jpa/docs/2.5.6/reference/html/#dependencies)

:warning: [Spring-boot-started-email](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-mail/2.5.6)

:warning: [H2-database](http://www.h2database.com/html/build.html#maven2)

## Como rodar a aplicação :arrow_forward:

No terminal, clone o projeto:

```
git clone https://github.com/Grupin-JC/api-grupin
```

...

Coloque um passo a passo para rodar a sua aplicação. **Dica: clone o próprio projeto e verfique se o passo a passo funciona**

## Casos de Uso

Para ver sobre os casos de uso, use o link a abaixo:

> protocolo://domínioAtual/api-docs

```
http://localhost:8080/api-docs
```

## Configuração de email

Caso você queira modificar o email de recebimento:

> src>main>resources>application.properties

coloque os dados nos seguintes campos:

```
spring.mail.username=email
spring.mail.password=senha de liberação do google
```

## Tarefas em aberto

:memo: Criar uma interface administrativa

:memo: Integrar API com a interface administrativa

## Desenvolvedores/Contribuintes :octocat:

| [<img src="https://avatars.githubusercontent.com/u/76585138?v=4" width=115><br><sub>Lucas Mateus</sub>](https://github.com/Lucas-dev-back) |
| :----------------------------------------------------------------------------------------------------------------------------------------: |

## Licença

The [Apache License](https://www.apache.org/licenses/LICENSE-2.0) (Apache 2.0)

Copyright :copyright: 2021 - Grupin-JC

<p>
  O presente projeto pode ser reutilizado por qualquer pessoa, uma vez que os créditos para o autor do código e nós do Grupin-JC que possuímos a propriedade intelectual do software, recebamos os créditos.
</p>

### Features

- [x] CRUD de usuário
- [x] CRUD de projetos
- [x] CRUD de categorias
- [x] CRUD de membros

