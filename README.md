<h1 align="center"> Avaliação PowerKR</h1>

![Badge Java](https://img.shields.io/static/v1?label=Java&message=17&color=orange&style=for-the-badge&logo=java)
![Badge Springboot](https://img.shields.io/static/v1?label=Springboot&message=v3.0.6&color=brightgreen&style=for-the-badge&logo=spring)
![Badge Postgresql](https://img.shields.io/static/v1?label=PostgreSQL&message=v15.2&color=blue&style=for-the-badge&logo=PostgreSQL)

## Resumo do projeto
REST API para avaliação das minhas habilidades como desenvolvedor Backend Java.

## Tecnologias e ferramentas
- `IntelliJ`
- `Java 17`
- `Spring Boot`
- `Spring Data JPA`
- `Spring Validation`
- `Spring Security`
- `SpringDoc - OpenAPI`
- `Docker`
- `PostgreSQL`
- `Flyway`
- `JJWT`
- `Lombok`

## Funcionalidades

### API de gerenciamento de Autenticação

- `Login de usuário - POST /api/v1/auth`: O login é realizado enviando as credenciais do usuário (email e password) 
em um JSON no corpo da requisição. Segue abaixo um exemplo do corpo da requisição.
    ```json
    {
      "email" : "lorem@email.com",
      "password" : "1234567890"
    }
    ```
    Em caso de sucesso a resposta tem status 200 com um JSON no corpo da resposta contendo **token** e **type**, em que **token**
    é um JWT com expiração de **1 hora** que deve ser enviado no *header* **Authorization** em requisições 
    que requerem usuário autenticado, e **type** é o tipo do token, no caso dessa aplicação é o tipo *bearer*.
    Segue abaixo um exemplo de corpo da resposta.
  ```json
    {
      "token": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJQb3dlcktSIFRlc3QgQVBJIiwic3ViIjoibG9yZW1AZW1haWwuY29tIiwiaWF0IjoxNjgzNDYwNjkxLCJleHAiOjE2ODM1NDcwOTF9.P6eDc2gizVNUvXE6B-6wYvC4hmK4ffQHTSwKdnlgaTM",
      "type": "Bearer"
    }
  ```

### API de gerenciamento de Usuário

- `Criar Usuário - POST /api/v1/users`: Criar usuário enviando as informações **name**, **email** e **password**
em um JSON no corpo da requisição.<br> 
Não é necessário estar autenticado.<br> 
Segue abaixo um exemplo do corpo da requisição.
    ```json
    {
      "name" : "Lorem Ipsum",
      "email" : "lorem@email.com",
      "password" : "1234567890"
    }
    ```
    Em caso de sucesso a resposta tem status 201 com um JSON no corpo da resposta contendo **id**, **name** e **email** do 
    usuário cadastrado. Segue abaixo um exemplo do corpo da resposta.
  ```json
    {
      "id" : 150,
      "name" : "Lorem Ipsum",
      "email" : "lorem@email.com"
    }
  ```
<br>

- `Buscar Usuários - GET /api/v1/users`: Buscar todos os usuários cadastrados no sistema.<br>
  É necessário enviar token de autenticação no header da requisição, exemplo: **'Authorization: Bearer token.exemplo.de-autenticação'**.
<br>

    Em caso de sucesso a resposta tem status 200 com um JSON no corpo da resposta contendo uma lista de informações 
    dos usuários cadastrados **id**, **name** e **email**. Segue abaixo um exemplo do corpo da resposta.
    ```json
        [
          {
            "id" : 150,
            "name" : "Lorem Ipsum",
            "email" : "lorem@email.com"
          },
          {
            "id" : 151,
            "name" : "Dolor Sit",
            "email" : "dolor@email.com"
          }
        ]
    ```
<br>

- `Buscar Usuário por ID - GET /api/v1/users/ID`: Buscar usuário por **ID**. Onde **ID** é o identificador do usuário.<br>
  É necessário enviar token de autenticação no header da requisição, exemplo: **'Authorization: Bearer token.exemplo.de-autenticação'**.
<br>

    Em caso de sucesso a resposta tem status 200 com um JSON no corpo da resposta contendo **id**, **name** e **email** do
    usuário buscado.<br> 
    Segue abaixo um exemplo do corpo da resposta.
    ```json
        {
          "id" : 150,
          "name" : "Lorem Ipsum",
          "email" : "lorem@email.com"
        }
    ```
<br>

- `Atualizar Usuário - PUT /api/v1/users/ID`: Atualizar usuário por **ID**. Onde **ID** é o identificador do usuário e
  enviar as novas informações do usuário **name** e **email** em um JSON no corpo da requisição.<br>
  É necessário enviar token de autenticação no header da requisição, exemplo: **'Authorization: Bearer token.exemplo.de-autenticação'**.
  <br>
  Segue abaixo um exemplo do corpo da requisição.
    ```json
    {
      "name" : "Lorem Xpto Ipsum",
      "email" : "lorem.ipsum@email.com"
    }
    ```

  Em caso de sucesso a resposta tem status 200 com um JSON no corpo da resposta contendo **id**, **name** e **email** do
  usuário atualizado.<br>
  Segue abaixo um exemplo do corpo da resposta.
  ```json
    {
      "id" : 150,
      "name" : "Lorem Xpto Ipsum",
      "email" : "lorem.ipsum@email.com"
    }
  ```
<br>

- `Deletar Usuário - DELETE /api/v1/users/ID`: Deletar usuário por **ID**. Onde **ID** é o identificador do usuário a
  ser deletado.<br>
  É necessário enviar token de autenticação no header da requisição, exemplo: **'Authorization: Bearer token.exemplo.de-autenticação'**.
  <br>

  Em caso de sucesso a resposta tem status 204.
<br>

### API de gerenciamento de Tarefa

- `Criar Tarefa - POST /api/v1/tasks`: Criar tarefa enviando as informações **title**, **description** e **creationDate**
  em um JSON no corpo da requisição.<br>
  É necessário enviar token de autenticação no header da requisição, 
  exemplo: **'Authorization: Bearer token.exemplo.de-autenticação'**.<br>
  Segue abaixo um exemplo do corpo da requisição.
    ```json
    {
      "title" : "Programar",
      "description" : "Praticar programação por 2 horas",
      "creationDate" : "2023-05-07T08:00:00"
    }
    ```
  Em caso de sucesso a resposta tem status 201 com um JSON no corpo da resposta contendo **id**, **title**, 
  **description** e **status** da tarefa cadastrada. Segue abaixo um exemplo do corpo da resposta.
  ```json
    {
      "id" : 97,
      "title" : "Tarefa xpto 2",
      "description" : "descrição da tarefa xpto 2",
      "creationDate" : "2023-05-07T15:00:00",
      "status" : "OPEN"
    }
  ```
<br>

- `Buscar Tarefas - GET /api/v1/tasks`: Buscar todas as tarefas cadastradas no sistema.<br>
  É necessário enviar token de autenticação no header da requisição, exemplo: **'Authorization: Bearer token.exemplo.de-autenticação'**.
  <br>

  Em caso de sucesso a resposta tem status 200 com um JSON no corpo da resposta contendo uma lista de informações
  das tarefas cadastradas **id**, **title**, **description**, **status** e **conclusionDate** (conclusionDate só é enviado 
  se a tarefa estiver com **status** CONCLUDED).<br>
  Segue abaixo um exemplo do corpo da resposta.
  ```json
    [
      {
        "id" : 97,
        "title" : "Tarefa xpto 1",
        "description" : "descrição da tarefa xpto 1",
        "creationDate" : "2023-05-04T10:00:00",
        "conclusionDate" : "2023-05-07T08:00:00",
        "status" : "CONCLUDED"
      },
      {
        "id" : 98,
        "title" : "Tarefa xpto 2",
        "description" : "descrição da tarefa xpto 2",
        "creationDate" : "2023-05-07T15:00:00",
        "status" : "OPEN"
      }
    ]
  ```
<br>

- `Buscar Tarefa por ID - GET /api/v1/tasks/ID`: Buscar tarefa por **ID**. Onde **ID** é o identificador da tarefa.<br>
  É necessário enviar token de autenticação no header da requisição, exemplo: **'Authorization: Bearer token.exemplo.de-autenticação'**.
  <br>

  Em caso de sucesso a resposta tem status 200 com um JSON no corpo da resposta contendo **id**, **title**, 
  **description**, **status** e **conclusionDate** (conclusionDate só é enviado se a tarefa estiver com **status** CONCLUDED).
  Segue abaixo um exemplo do corpo da resposta.
  ```json
    {
        "id" : 97,
        "title" : "Tarefa xpto 1",
        "description" : "descrição da tarefa xpto 1",
        "creationDate" : "2023-05-04T10:00:00",
        "conclusionDate" : "2023-05-07T08:00:00",
        "status" : "CONCLUDED"
    }
  ```
<br>

- `Atualizar Tarefa - PUT /api/v1/tasks/ID`: Atualizar tarefa por **ID**. Onde **ID** é o identificador da tarefa e
  enviar as novas informações da tarefa **title**, **description**, **creationDate** e **status** em um JSON no corpo da requisição.<br>
  É necessário enviar token de autenticação no header da requisição, exemplo: **'Authorization: Bearer token.exemplo.de-autenticação'**.
  <br>
  Segue abaixo um exemplo do corpo da requisição.
    ```json
    {
      "title" : "Tarefa xpto 2",
      "description" : "descrição da tarefa xpto 2",
      "creationDate" : "2023-05-07T15:00:00",
      "status" : "CONCLUDED"
    }
    ```

  Em caso de sucesso a resposta tem status 200 com um JSON no corpo da resposta contendo **id**, **title**,
  **description**, **creationDate** e **status** e **conclusionDate** 
  (conclusionDate só é enviado se a tarefa estiver com **status** CONCLUDED).<br>
  Segue abaixo um exemplo do corpo da resposta.
  ```json
    {
        "id" : 98,
        "title" : "Tarefa xpto 2",
        "description" : "descrição da tarefa xpto 2",
        "creationDate" : "2023-05-07T15:00:00",
        "conclusionDate" : "2023-05-07T22:00:00",
        "status" : "CONCLUDED"
    }
  ```
<br>

- `Deletar Tarefa - DELETE /api/v1/tasks/ID`: Deletar tarefa por **ID**. Onde **ID** é o identificador da tarefa a
  ser deletada.<br>
  É necessário enviar token de autenticação no header da requisição, exemplo: **'Authorization: Bearer token.exemplo.de-autenticação'**.
  <br>

  Em caso de sucesso a resposta tem status 204.
<br>

## Como executar a aplicação
  
  ### Via Docker
Executar um container da aplicação que está no Docker hub. É necessário ter Docker compose instalado no computador.<br>
Clone o projeto:
    
```bash
    git clone git@github.com:Edson-Mendes/powerkr-test-api.git
```

Execute o comando abaixo na pasta clonada:
  ```bash
  docker compose -f powerkrtest-api.yml up
  ```

O comando acima executará o arquivo [powerkrtest-api.yml](https://github.com/Edson-Mendes/powerkr-test-api/blob/main/powerkrtest-api.yml),
que irá subir um container PostgreSQL e um container da Aplicação.<br>
Após subir os containers, acesse <http://localhost:8888/swagger-ui.html>.<br>