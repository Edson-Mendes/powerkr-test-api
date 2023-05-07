<h1 align="center"> Avaliação PowerKR</h1>

![Badge Java](https://img.shields.io/static/v1?label=Java&message=17&color=orange&style=for-the-badge&logo=java)
![Badge Springboot](https://img.shields.io/static/v1?label=Springboot&message=v3.0.6&color=brightgreen&style=for-the-badge&logo=spring)
![Badge Postgresql](https://img.shields.io/static/v1?label=PostgreSQL&message=v15.2&color=blue&style=for-the-badge&logo=PostgreSQL)
![Badge Heroku](https://img.shields.io/static/v1?label=Heroku&message=Deploy&color=4f3074&style=for-the-badge&logo=Heroku)

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
- `Heroku`
- `JUnit 5`

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
O password é salvo criptografado no banco de dados usando BCryp.
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

## Documentação
A descrição de cada API e recursos está disponível na interface gráfica gerada pelo 
[Swagger](https://powerkr-test-api.herokuapp.com/swagger-ui.html)

### Acesso a recursos que requerem usuário autenticado
Endpoints que requerem usuário autenticado devem receber um token no header Authorization da requisição, exemplo:
```
  'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJQb3dlcktSIFRlc3QgQVBJIiwic3ViIjoiZWRzb25AZW1haWwuY29tIiwiaWF0IjoxNjgzNDcxMzY3LCJleHAiOjE2ODM0NzQ5Njd9.RZ0rKo6LcLWlGj0vqfl9_AHCmDJXy9vJ4FS7C6_vRFg'
```
O token expira em 1 hora e pode ser gerado através do endpoint de autenticação.<br>
Caso o header não seja enviado, ou um token inválido seja enviado, uma resposta com status **401 (Unauthorized)**  será devolvida.

### Detalhes de erros
Abaixo estão detalhes sobre status 4xx que o client pode receber como resposta.

#### Campos inválidos
O client enviou campos inválidos no corpo da requisição, pode ocorrer em requisições POST e PUT.<br>
A resposta vem com status 400 e o corpo da resposta fornece quais campos e por que estão inválidos.

#### Credenciais inválidas
O client tentou realizar login com credenciais inválidas, verifique se o email ou password estão corretos.<br>
A resposta vem com status 400 e mais detalhes no corpo da resposta.<br>
Pode ocorrer em requisições POST para /api/v1/auth

#### Unauthorized
o client tentou acessar um recurso protegido e que requer token de acesso.<br>
A resposta vem com status 401.

#### Email já está em uso
O email informado pelo client já está em uso no sistema, pode ocorrer em requisições POST de usuário.<br>
A resposta vem com status 409 e mais detalhes no corpo da resposta.

#### Recurso não encontrado
O recurso que o client tentou acessar não existe na base de dados. Pode ocorrer em requisições GET por ID, PUT e DELETE.<br>
A resposta vem com status 404 e mais detalhes no corpo da resposta.

### Swagger
Para enviar requisições através do [Swagger](https://powerkr-test-api.herokuapp.com/swagger-ui.html), basta clicar no 
recurso desejado, clicar no botão **Try it out**, adicionar Parâmetros (se necessário), 
adicionar corpo da requisição (se necessário) e clicar em **Execute**.<br><br>
Para enviar token no header Authorization, realize o login através do recurso Autenticação, 
copie o token que foi devolvido no corpo da resposta, sem as aspas, clique no botão **Authorize**, que abrirá 
um pop-up, cole o token no campo **value** e clique em **Authorize**. Feche o pop-up e pronto, suas requisições 
serão enviadas com o header Authorization e o token informado.

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
Se algo der errado, fique a vontate para usar a versão em [deploy](https://powerkr-test-api.herokuapp.com/swagger-ui.html).

## Deploy
Realizei o deploy da aplicação no **Heroku**, você pode testar/usar através da interface gráfica gerada pelo swagger 
[swagger-ui](https://powerkr-test-api.herokuapp.com/swagger-ui.html).<br>
Ou enviando requisições diretamente aos endpoints usando alguma ferramenta de sua preferência como 
curl, Postman, Insomnia etc.

- `Host da API`: https://powerkr-test-api.herokuapp.com
- `Endpoints disponíveis`
  - `/api/v1/auth`: Endpoint para autenticar usuário e gerar token de acesso. Suporta requisições POST.
  
  - `/api/v1/users`: Endpoint para Cadastrar usuários e Buscar todos os usuários cadastrados.
  Suporta requisições GET e POST.
  
  - `/api/v1/users/ID`: Endpoint para Buscar usuário por ID, Atualizar por ID e Deletar por ID.
  Suporta requisições GET, PUT e DELETE.
  
  - `/api/v1/tasks`: Endpoint para Cadastrar tarefas e Buscar todos as tarefas cadastradas.
  Suporta requisições GET e POST.
  
  - `/api/v1/tasks/ID`: Endpoint para Buscar tarefas por ID, Atualizar por ID e Deletar por ID.
  Suporta requisições GET, PUT e DELETE.
  - `/swagger-ui.html`: Endpoint que devolve a interface gráfica do Swagger, através dele o usuário da API
  tem uma interface como documentação, e a possibilidade de interagir com os endpoints/recursos.
  Suporta requisições GET.

OBS: O plano que eu uso do Heroku **adormece** a aplicação depois de certo tempo inativo,
então pode ser que a primeira requisição demore um pouco (até uns 60 segundos).
