# Encurtador URL

O objetivo desse projeto é fazer um encurtador de urls em API's.

Nesse projeto foram usados as tecnologias Java com Spring Boot, PostgreSQL como gerenciador de banco de dados, Flyway para não precisar rodar SQL's manualmente e o Swagger para documentação da API.

### Como rodar?

* Primeiro é preciso ter o PostgreSQL instalado e conetado, Java 8 e Maven
* É preciso criar uma nova database com o nome "postgres" (Caso queira usar uma base já existente, basta mudar a url de conexão no propertie "spring.datasource.url" no arquivo [application.properties](https://github.com/jaspionjpg/encurtador-uol/blob/master/src/main/resources/application.properties)
* Dentro da database criada é necessario criar 1 schema
	* 'public': é o schema que será usado para administração do flyway (Você deve criá-lo)
	    * CREATE SCHEMA PUBLIC;
	* 'encurtador_uol': é o schema que a aplicação ira usar para fazer a persistencia de dados (é criado automaticamento pelo flyway automaticamente e você não deve criar)
	* As criação de tabelas da aplicação são gerenciados pelo flyway com base na versão dos arquivos da pasta [db/migration](https://github.com/jaspionjpg/encurtador-uol/tree/master/src/main/resources/db/migration), por conta disso não é necessario rodar mais nenhuma SQL.
* Para rodar a aplicação:
	* Caso for rodar o projeto em alguma IDE, basta importar na sua IDE esperar o maven da sua IDE baixar as dependências e ir no arquivo [EncurtadorUolApplication.java](https://github.com/jaspionjpg/encurtador-uol/blob/master/src/main/java/br/com/richardmartins/encurtadoruol/EncurtadorUolApplication.java), clicar com o botão direito do seu mouse e achar a opção de rodar a aplicação
	* Caso queira rodar sem IDE:
		* Abra o seu terminal na raiz do projeto baixado
		* rode: mvn clean install
		* rode: java -jar target/encurtador-uol-0.0.1-SNAPSHOT.jar e pronto :)

### Particularidades

##### Testes Unitários

Os testes unitários são feitos em cima do H2 que é um banco de dados em mémoria

##### Autentificação
O projeto é uma api com autentificação com Oauth portanto para criar suas url's curtas é necessario sempre fazer um login antes, para isso foi criado um usuario em memoria para a utilização. onde o username é 'uol' e o password é '123'

Nota: Para a documentação do swagger e o redirecionamento da url encurtada não é necessario autentificação.

Para pegar o token de autentificação e fazer chamadas na api é necessario fazer uma requisição na url 
http://{{url_de_dominio_sua_aplicacao}}/oauth/token passando no body o username, password e a grant_type que é 'password', tambem é preciso colocar no header a autentificação Basic de client e secret que são 'encurtador_url' e 'secreto'

##### Exemplo
![Autentificacao](https://github.com/jaspionjpg/encurtador-uol/blob/master/documentos/Basic.png)
![Corpo](https://github.com/jaspionjpg/encurtador-uol/blob/master/documentos/Autentificacao.png)

E com isso você tem o token para acessar as API's.

Sempre que você for fazer uma requisição em uma API é necessario passar o header 'Authorization' como valor um "Bearer " seguido do "access_token"

### API

Bom.. a api tem o Swagger UI configurado, que pode ser acessado atravez da aplicação no link <http://localhost:8080/swagger-ui.html>

Porem tambem é explicado detalhado cada um separadamente logo a baixo:

* [Api de busca de link](https://github.com/jaspionjpg/encurtador-uol/blob/master/documentos/readme_buscar.md)
* [Api de encurtar link](https://github.com/jaspionjpg/encurtador-uol/blob/master/documentos/readme_encurtar.md)
* [Api de buscar estatísticas](https://github.com/jaspionjpg/encurtador-uol/blob/master/documentos/readme_estatisticas.md)
* [Url de redirecionamento](https://github.com/jaspionjpg/encurtador-uol/blob/master/documentos/readme_redirecionar.md)
