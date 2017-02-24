# System

Sistema criado pelo Desenvolvedor Thiago Augusto para a Hotmart.

Projeto Maven com dependências AngularJs, spring-webwmc, jackson-databind, jstl, commons-io, javax.ws.rs-api, swagger-annotations e jsr311-api.

Baixe o projeto, feito isso, faça o Maven Update Project.
Para rodar o projeto basta apenas fazer o Maven Install do Projeto e colocar o system.war gerado dentro tomcat.

Server utilizado para o Desenvolvimento: apache-tomcat-8.0.41.
Telas desenvolvidas em AngularJs.

Infelizmente, mesmo eu colocando a anotação "@JsonIgnore" nos métodos "gets" dentro classe de modelo, as propriedades ainda continuam retornando para a tela.

Exemplo de get para a listagem:
http://localhost:8080/system/archive/listAllArchives

Exemplo de get para download do arquivo:
http://localhost:8080/system/archive/download/{id}

Exemplo de post para criação do arquivo:
http://localhost:8080/system/archive/createArchive
{
  "id": null,
  "userName": "{nome do usuário}",
  "mimetypeFile": "application/{type}",
  "nameFile": "{nome do arquivo}",
  "idFile": null,
  "contentFile": "{conteúdo do arquivo}",
  "sizeFile": {tamanho do arquivo}
  }

Exemplo de post para deletar um arquivo:
http://localhost:8080/system/archive/deleteArchive/{id}


Tela em Angular do projeto:
http://localhost:8080/system/

