# ATIVIDADE TÉCNICA ESIG

## Branchs
- Main: Não está rodando por conta do JSF
- Spring-funcionando: Está rodando, mas não tem front-end

Iniciei a atividade técnica utilizando spring, fiz todo o back para ligar com o front. Quando conclui o back, parti para fazer o fronto utilizando JSF 4. 
Quando iniciei o processo de criação do fronto, descobri que JSF não é compatível com Spring 3.5, versão a qual eu estava utilizando. Dado esse faço, fiz inumeras tentativas de ajustar o back para o front, entretanto, não obtive sucesso
A bairo irei fazer um breve resumo do que foi feito e o que não foi possível concluir.

## Coisas que tentei fazer para o projeto funcionar
- Troca do Java 24 para o Java 21
- Fiz a configuração do pom.xml para aceitar o JSF 4 e o Spring 3.5, mas não consegui fazer o projeto rodar.
- Remover todas as dependencias Spring e atualizar todos os arquivos para serem aceitos pelo JSF ja que eu estava utilizando anotações que não eram aceitas pejo JakartaEE.
- Atualizei todos os arquivos que tinha feito para o modelo aceito pelo Wildfly 36.
- Tentei fazer o deploy do projeto no Wildfly 36, mas o deploy não funcionou.
- Descobri que o Wildfly 36 não aceita o JSF 4, então tentei fazer o deploy no Tomcat, mas não consegui.
- Tentei fazer o downgrade para o WildFly 23, mas ainda não rodou
- Fiz o download do Java 11 para ver se rodava, mas não rodou
- Fiz a mudança do JakartaEE 10 para o JavaxEE 8, mas não rodou
- Tentei criar um projeto JSF 4 do zero, mas não consegui fazer a configuração do pom.xml para aceitar o JSF 4 e o Spring 3.5.

## Concluido
### Back-end - Spring
- Criação de um projeto Spring Boot com as dependências necessárias para o funcionamento do back-end utilizando Maven como gerenciador de dependencias.
- Criação de um banco de dados em memória utilizando PostgreSQL.
- Criação de um modelo de dados para representar as entidades do sistema.
- Criação de um repositório para acessar os dados do banco de dados.
- Persistencia de dados utilizando JPA.
- Criação de um serviço para encapsular a lógica negocial.
- Interação com o banco de dados utilizando Spring Data JPA.
- Interação com o usuario por meio de linha de comando.
#### Obs: Vale salientar que tentei inumeras maneiras para conseguir fazer rodar, entretanto, não tive sucesso

## Não concluido
### Front-end - JSF
#### Fiz isso, mas não consegui rodar.
- Criação de um projeto JSF 4 com as dependências necessárias para o funcionamento do front-end utilizando Maven como gerenciador de dependencias.
- Criação dos Beans gerenciados para representar as entidades do sistema.
- Criação de um arquivo de configuração do JSF para definir as rotas e os componentes da aplicação.
- Criação de um arquivo de configuração do JSF para definir os componentes da aplicação.
