# Votação de Pautas

Avaliação técnica de um sistema de votação de pautas desenvolvido em Java com SpringBoot


## Tecnologias

Java 17

SpringBoot

Lombok (Fonte: https://projectlombok.org/)

Maven 3.5.2+

Banco de dados H2

Banco de dados MySql

## Instalação


```bash
  https://github.com/marcoteixeirab15/votacao-pauta.git
  cd votacao-pauta
````
Para executar a aplicação utilizando o banco de dados H2, execute o comando:
```bash
    mvn spring-boot:run
````

Para executar a aplicação utilizando o banco de dados MySql, será necessário executar o mysql localmente:

- Criar um banco de dados como nome votacao_pauta.

- Alterar o arquivo `application.properties` no campo `spring.profiles.active`
  substituir o parametro `test` por `dev`

Execute o comando
```bash
    mvn spring-boot:run
````
Aguarde executar e acesse:

http://localhost:8080/swagger-ui


Após a primeira execução do projeto utilizando o parametro `dev`,
atualizar no arquivo `application-dev.properties` o campo `spring.jpa.hibernate.ddl-auto` com o parametro `none`,
para evitar uma nova criação da base de dados