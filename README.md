# Pink aplication

Objetivo: Projeto backend que dá acesso a um produto por meio de uma assinatura.


Recursos utilizados: java/spring; h2database; lombok; spring-boot; springdoc-openapi

#### . Modelo de entidade relacional

![mermaid-diagram-2024-02-02-180047_PINK02.png](src%2Fmain%2Fresources%2Fstatic%2Fmermaid-diagram-2024-02-02-180047_PINK02.png)

O princípio da normalização de dados visa reduzir a redundância e a inconsistência dos dados, por isso é necessário evitar ter vínculos duplicados entre as classes. Por exemplo, se o usuário já tem um vínculo com a classe Subscription, que por sua vez tem um vínculo com a classe Product, então não é necessário que o usuário também tenha um vínculo direto com a classe Product, pois isso pode gerar dados repetidos ou conflitantes.

#### . Modelo de arquitetura

Esses são pacotes comuns utilizados em projetos Java, especialmente em aplicativos que seguem o padrão arquitetural MVC (Model-View-Controller) e em arquiteturas de serviços. O padrão de projeto busca separar as responsabilidades de cada camada da aplicação. Cada componente desempenha uma função específica, como:

| Pacote     | Descrição                                                             |
|------------|-----------------------------------------------------------------------|
| config     | Configurações gerais da aplicação. Nessa aplicação foi utilizado para configurar o springdoc-openapi; que gera a documentação da API no Swagger. |
| controller | Recebe requisições do usuário e retorna respostas para a camada de apresentação. |
| handler    | Trata erros e exceções, enviando mensagens apropriadas para o usuário ou para logs. |
| model      | Define entidades do domínio da aplicação, incluindo atributos, validações e relacionamentos. |
| repository | Abstrai o acesso aos dados, realizando operações de persistência e consulta. |
| service    | Implementa a lógica de negócio da aplicação, interagindo com modelos e repositórios. |

#### . Classe do tipo enum

Um tipo enum em Java é uma forma de declarar um conjunto de constantes com identificadores únicos. Na prática, isso é útil para representar um conjunto fixo de valores que são logicamente relacionados e que não devem ser modificados durante a execução do programa.

Abaixo o exemplo que foi aplicado na aplicação para verificar o status

```java
public enum SubscriptionSTS {
    ACTIVE("ACTIVE"),   // Active subscription status
    CANCEL("CANCEL"),   // Canceled subscription status
    PENDING("PENDING"); // Pending subscription status

    private final String menuSTS; // Representation of the status for display in menus or interfaces

    /**
     * @param menuSTS The representation of the status for display in menus or interfaces.
     */
    SubscriptionSTS(String menuSTS) {
        this.menuSTS = menuSTS;
    }

    /**
     * @return The representation of the status for display in menus or interfaces.
     */
    public String getMenuSTS() {
        return menuSTS;
    }
}
```

#### . Maven | Gestor de Dependências

| Maven    | ferramenta de gerenciamento de dependências para projetos Java.|

No uso uso de dependências, foram encontrados conflitos no uso do openapi na versão mais atualizada. Sendo a versão abaixo adequada para o projeto.

<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
			<version>2.0.2</version>
		</dependency>


Após o ajuste foi possível verificar a documentação da API produzida no Swagger. Com a depência e ajustes na camada do Controller foi possível configurar o request/response para ser executado diretamente pelo Swagger.

#### . API documentada com Swagger

![swagger2.png](src%2Fmain%2Fresources%2Fstatic%2Fswagger2.png)

![swagger.png](src%2Fmain%2Fresources%2Fstatic%2Fswagger.png)

#### . Banco de Dados H2Database

Para teste da API foram separadas algumas querys que populam e consultam o banco de dados pela api.

Com a execução do arquivo em resources > data, será possível popular o banco e utilizar as querys de consulta.

![GIF Example](src%2Fmain%2Fresources%2Fstatic%2Fsql_H2.gif)

![Sql_H2DatabaseConsulta.png](src%2Fmain%2Fresources%2Fstatic%2FSql_H2DatabaseConsulta.png)

#### . Exception Handler

Foi possível criar uma exceção personalizada. Que foi colocada como teste no SubscriptionService, quando há tentativa de cancelamento/delete da assinatura pela segunda vez.


"Um manipulador de exceção é o código que estipula o que um programa fará quando um evento anômalo, interromper o fluxo normal das instruções desse programa."

(Ref.: https://glysns.gitbook.io/spring-framework/spring-web/exception-handlers)
![handler.png](src%2Fmain%2Fresources%2Fstatic%2Fhandler.png)



###### Formatação de arquivo .md

- `src`: the folder to maintain sources
  [here](https://github.com/deisekinsk/).
> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.


