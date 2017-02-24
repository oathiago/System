# System

Sistema criado pelo Desenvolvedor Thiago Augusto para a Hotmart.

Projeto Maven com dependências AngularJs, spring-webwmc, jackson-databind, jstl, commons-io, javax.ws.rs-api, swagger-annotations e jsr311-api.

Baixe o projeto, feito isso, faça o Maven Update Project.
Para rodar o projeto basta apenas fazer o Maven Install do Projeto e colocar o system.war gerado dentro tomcat.

Server utilizado para o Desenvolvimento: apache-tomcat-8.0.41.
Telas desenvolvidas em AngularJs.

Infelizmente, mesmo eu colocando a anotação "@JsonIgnore" nos métodos "gets" dentro classe de modelo, as propriedades ainda continuam retornando para a tela.
