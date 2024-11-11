# Projeto Spring CineStream

## Descrição
O CineStream é uma aplicação que utiliza a [API pública do TMDb](https://www.themoviedb.org/documentation/api) para buscar informações de filmes e séries, e armazena dados temporariamente usando o banco de dados em memória H2. Este README explica como foi feito o consumo da API externa e a persistência dos dados.

## Configuração do Banco de Dados H2

A aplicação usa o banco de dados H2 para persistir dados em memória durante a execução. No arquivo `application.properties`, as configurações do H2 estão definidas como segue:

```properties
# Configuração do banco de dados H2
spring.datasource.url=jdbc:h2:mem:test
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.h2.console.path=/h2
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
```

**URL**: ``jdbc:h2:mem:test`` - O banco de dados é criado em memória e será descartado ao final da execução.

**Console**: Está habilitado em /h2, onde é possível acessar a interface do banco.


## Consumo da API do TMDb

A aplicação consome dados da API do TMDb através da classe `ApiClient`. Esta classe utiliza o `RestTemplate` do Spring para fazer requisições HTTP aos endpoints do TMDb. Abaixo estão alguns dos principais métodos e explicações de como foi feita a integração.

### Configurações da API

As configurações da URL base da API e da chave de acesso estão definidas no arquivo `application.properties`:

```properties
#Configuração da API TMDb
api.base.url=https://api.themoviedb.org/3
api.key=<chave_api>
```

## Métodos para Consumo da API do TMDb

Abaixo estão os métodos principais implementados para consumir a API do TMDb, utilizando o `RestTemplate` do Spring para fazer requisições HTTP e obter dados de filmes e séries.

### 1. Buscar Filmes por Título

Este método, `buscarFilmesPorTitulo`, consulta o endpoint `/search/movie` para buscar filmes de acordo com o título fornecido. Ele recebe o título e o número da página como parâmetros.

```java
public Page<TmdbFilme> buscarFilmesPorTitulo(String titulo, Integer page) {
    String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl)
            .path("/search/movie")
            .queryParam("page", page)
            .queryParam("query", titulo)
            .queryParam("language", "pt-BR")
            .toUriString();

    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", String.format("Bearer %s", apiKey));

    HttpEntity<String> entity = new HttpEntity<>(headers);

    var response = restTemplate.exchange(
        url,
        HttpMethod.GET,
        entity,
        new ParameterizedTypeReference<Page<TmdbFilme>>() {}
    );

    return response.getBody();
}
```

**Parâmetros:** `titulo (título do filme) e page (número da página).

**Cabeçalhos de Autenticação:** O token da API é incluído no cabeçalho `Authorization` usando o formato Bearer.

**Resposta:** Retorna um objeto `Page<TmdbFilme>` com os dados dos filmes correspondentes.


### 2. Buscar Series por Título

O método `buscarSeriesPorTitulo` é similar ao anterior, mas consulta o endpoint `/search/tv para buscar séries de acordo com o título fornecido.

E com resposta retornando o objeto `Page<TmdbSerie>` com os dados das séries correspondentes.

### 3. Buscar Filmes Por Generos
O método `generosFilmes`  consulta o endpoint `/genre/movie/list` para obter uma lista de gêneros disponíveis para filmes.

Para séries o método é semelhante.

### 4. Buscar Filmes por Ano de Lançamento

O método `buscarFilmesPorAnoLancamento` realiza uma busca de filmes com base no ano de lançamento. Ele consulta o mesmo endpoint `/search/movie e filtra os resultados por ano.