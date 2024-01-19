# Garantir a Qualidade do App Android Lojinha 

![Alt ou título da imagem](https://snapstack.cz/wp-content/uploads/2020/07/ToolsForBuildingApps_636x300-op.png)

> Esse projeto tem como objetivo garantir a qualidade de uma aplicaçao mobile Android a partir de casos de testes manuais e automatizados.

### 🚧 Ajustes e melhorias

O projeto ainda está em desenvolvimento e as próximas atualizações serão voltadas nas seguintes tarefas:

1. - [x] Testes de Regressão Visual
2. - [x] Validar as requisições do APP a uma API REST
3. - [x] Seguir o guia de testes Exploratorios
4. - [x] Criar o primeiro caso de teste que sera automatizado baseado em uma regra de negócio
5. - [x] Automatizar o caso de teste criado
6. - [ ] Criar casos de testes que cubram as todas regras de negócio 
7. - [ ] Automatizar todos os casos de testes
8. - [ ] Tradução do projeto para o inglês  

## 🚀 Começando

Projeto de testes voltado para o App Lojinha desenvolvido no [Programa de Testes e Qualidade de Software Julio de Lima](https://mentoria.juliodelima.com.br/). 

O app consiste em:
1. Tela de login
2. Tela de lista de produtos
3. Tela de adição de produtos
4. Tela de adição de componentes a um produto.
   
<img src="/images/loginTela.png" height="300"> | <img src="/images/ListadeProdutosTela.png" height="300"> | <img src="/images/AdicionarProdutoTela.png" height="300"> | <img src="/images/AdicionarComponenteTela.png" height="300">

Funcionalidades:
1. Adicionar novos produtos com ou sem componentes
2. Excluir produtos
3. Editar produtos
4. Sair da lojinha.

## 🛠️ Construído com:

* [Intellij](https://www.jetbrains.com/pt-br/idea/) - Ferramenta para escrita do código dos testes automatizados
* [Maven](https://maven.apache.org/) - Gerente de Dependência
* [Selenium](https://www.selenium.dev/) - Framework base
* [JUnit](https://junit.org/junit4/) -
* [Appium](https://appium.io/docs/en/2.4/) -
* [Appium Inspector](https://github.com/appium/appium-inspector) - Um inspetor de GUI para aplicativos móveis
* [Genymotion](https://www.genymotion.com/) - Usado para simular um device mobile
* [James PROXY](https://github.com/james-proxy/james) - Monitorar requisições do app a uma API Rest
* [Page Object Pattern](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/) - Padrão utilizado para descrever e documentar o fluxo dos testes.
* [Gherkin Reference](https://cucumber.io/docs/gherkin/) - Padrão utilizado para escrita dos casos de testes.
* [ROME](https://rometools.github.io/rome/) - Usada para gerar RSS


## ⚙️ Executando os testes
### 🗺️ Seguir o guia de testes Exploratorios
  Existe um guia oficial do Android que mostra os principais critérios de qualidades para um app Android. Veja [aqui](https://developer.android.com/training/testing/fundamentals?hl=pt-br).  
A partir desse guia e estudos eu segui alguns pontos chaves para os testes exploratorios:  

#### 👾Testes relacionados ao sistema Android
  - Rotation: Rotacione o dispositivo em varias telas
  - Permission: O que acontece se as permissões não forem dadas ao app?
  - Airplane mode: Usar o app no modo avião e colocar o device no modo avião enquanto usa o app.
  - Interruptions: Qual o funcionamento do app quando recebe uma notificaçao ou uma ligaçao?
  - Connections: Como o app se comporta em conexões lentas? Se trocar do wi-fi para os dados moveis o app continua funcionando? Se trocar o idioma a moeda troca?

#### 📈Testes relacionados as regras de negócios
- [x] Executar as regras de negócios
- [x] Continuidade dos fluxos
- [x] Listagem de muitos registros
- [x] Mascaras, habilitação/desabilitação e tipagem dos campos
- [x] Variação de rede
- [x] Integração com API
- [x] Perda de foco da Aplicação

### 🕵🏻‍♂️Validar as requisições do APP a uma API REST
- Requisitos:
  James Proxy
  Genymotion
  
- Passos:
  1. Abra o James Proxy e pegue o a porta
  2. Abra o terminal e pegue o ip local
  3. No Genymotion: Configure o proxy no dispositivo
  4. Interaja com o App e visualize as requições no James Proxy  

  <img src="/images/APIRequests1.png" height="300">
  
### 🛠️Criar o primeiro caso de teste que sera automatizado baseado em uma regra de negócio
Dentre as regras de negócio está que:
"Só é possível adicionar um produto que o valor esteja entre R$0,01 e R$7.000,00"

Então baseando no padrão **Gherkin** temos:  

**Dado** o app lojinha instalado no device  
**E** o usuario acessa a pagina de login  
**E** preenche com suas credenciais validas  
**E** aciona a opção de realizar login  
**Quando** aciona o botão "+" para adicionar um produto  
**E** preenche com nome e valor acima de R$7.000,00  
**Então** é exibido um alerta de erro.  

<img src="/images/MensagemDeErro.png" height="300">

### 👨‍💻 Automatizar o caso de teste criado
Foi utilizada as bibliotecas do JUnit e do Appium junto com Selenium Webdriver. O padrão para a documentação utilizado foi o #PageObjects 

~~~java
@DisplayName("Validacao do valor de produto não permitido")
    @Test
    public void testValidacaoDoValorNaoPermitido() {
        //Fazer login
        String mensagemApresentada = new LoginTela(app)
                .preencherUsuario("admin")
                .preencherSenha("admin")
                .submeterLogin()
                .abrirTelaAdicaoProduto()
                .preencherNomeProduto("Iphone")
                .preencherValorProduto("7000")
                .preencherCoresProduto("Preto,Branco")
                .submissaoComErro()
                .obterMensagemDeErro();

        //Validar a mensagem de erro
        Assertions.assertEquals("O Valor do Prodrudo deve estar entre R$0,01 e R$7.000,00",mensagemApresentada);
    }
~~~
Veja o código completo [aqui](https://github.com/mateusralv/lojinha-testes-mobile/blob/main/src/test/java/modulos/produto/ProdutoTest.java#L37C16-L56C16 
).

## 🔩 Analise dos Resultados:
### Requisições de API
```
Quando o login é feito é mandado o 3 requisições do tipo GET iguais, poderiamos conversar com o time de desenvolvimento para diminuir para uma e
diminuindo o tempo de login do usuario que se pensamos em uma escala de milhares usuarios poderia fazer a diferença.
```
### Testes Exploratorios
* Perda de foco da Aplicação

```
Passos:
Dado a tela de adiçao de produtos
E o usuario está preenchendo o nome
Quando uma notificação chega
E o usuario responde a notificação
Então volta para continuar a ação no app

Resultado experado:
O fluxo do app continue funcionando.

Resultado Atual:
O app fecha e a tela inicial do device é exibida.
```

### Testes Automatizados
```
1/1 Teste Passando
```

