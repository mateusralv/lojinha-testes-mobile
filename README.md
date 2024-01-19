# Garantir a Qualidade do App Android Lojinha 

![GitHub repo size](https://img.shields.io/github/repo-size/iuricode/README-template?style=for-the-badge)
![GitHub language count](https://img.shields.io/github/languages/count/iuricode/README-template?style=for-the-badge)
![GitHub forks](https://img.shields.io/github/forks/iuricode/README-template?style=for-the-badge)
![Bitbucket open issues](https://img.shields.io/bitbucket/issues/iuricode/README-template?style=for-the-badge)
![Bitbucket open pull requests](https://img.shields.io/bitbucket/pr-raw/iuricode/README-template?style=for-the-badge)

![Alt ou título da imagem](https://snapstack.cz/wp-content/uploads/2020/07/ToolsForBuildingApps_636x300-op.png)

> Esse projeto tem como objetivo garantir a qualidade de uma aplicaçao mobile Android a partir de casos de testes manuais e automatizados.

### 🚧 Ajustes e melhorias

O projeto ainda está em desenvolvimento e as próximas atualizações serão voltadas nas seguintes tarefas:

1. - [x] Testes de Regressão Visual
2. - [x] Validar as requisições do APP a uma API REST
3. - [x] Seguir o guia de testes Exploratorios
4. - [x] Criar o primeiro caso de teste que sera automatizado baseado em uma regra de negócio
5. - [ ] Automatizar o caso de teste criado
6. - [ ] Criar casos de testes que cubram as todas regras de negócio 
7. - [ ] Automatizar todos os casos de testes 

<!-- . ## 💻 Pré-requisitos

Antes de começar, verifique se você atendeu aos seguintes requisitos:

- Você instalou a versão mais recente de `<linguagem / dependência / requeridos>`
- Você tem uma máquina `<Windows / Linux / Mac>`. Indique qual sistema operacional é compatível / não compatível.
- Você leu `<guia / link / documentação_relacionada_ao_projeto>`.

  
#  Desafio de testar uma aplicaçao Android

Esse projeto tem como objetivo garantir a qualidade de uma aplicaçao mobile Android a partir de casos de testes manuais e automatizados.-->

## 🚀 Começando

Projeto de testes voltado para o App Lojinha desenvolvido no [Programa de Testes e Qualidade de Software Julio de Lima](https://mentoria.juliodelima.com.br/). 

O app consiste em:
1. Tela de login
2. Tela de lista de produtos
3. Tela de adição de produtos
4. Tela de adição de componentes a um produto.
<img src="../lojinha-testes-mobile/images/loginTela.png" height="100">

## 🛠️ Construído com

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
  Existe um guia oficial do Android que mostra os principais critérios de qualidades para um app Android. Veja: 
A partir desse guia e estudos eu segui alguns pontos chaves para os testes exploratorios:
#### 👾Testes relacionados ao sistema Android
  - Rotation: Rotacione o dispositivo em varias telas
  - Permission: O que acontece se as permissões não forem dadas ao app?
  - Airplane mode: Usar o app no modo avião e colocar o device no modo avião enquanto usa o app.
  - Interruptions: Qual o funcionamento do app quando recebe uma notificaçao ou uma ligaçao?
  - Connections: Como o app se comporta em conexões lentas? Se trocar do wi-fi para os dados moveis o app continua funcionando? Se trocar o idioma a moeda troca?

#### 📈Testes relacionados as regras de negócios
- [x] Executar as regras de negócios
- [ ] Continuidade dos fluxos
- [ ] Listagem de muitos registros
- [ ] Mascaras, habilitação/desabilitação e tipagem dos campos
- [ ] Variação de rede
- [ ] Integração com API
- [ ] Perda de foco da Aplicação

### 🕵🏻‍♂️Validar as requisições do APP a uma API REST
- Requisitos:
  James Proxy
  Genymotion
  
- Passos:
  1. Abra o James Proxy e pegue o a porta
  2. Abra o terminal e pegue o ip local
  3. No Genymotion: Configure o proxy no dispositivo
  4. Interaja com o App e visualize as requições no James Proxy

     [video]
  
- Análise de Resultados
  Quando o login é feito é mandado o 3 requisições do tipo GET iguais, poderiamos conversar com o time de desenvolvimento para diminuir para uma, diminuindo o tempo de login do usuario que se pensamos em uma escala de milhares usuarios poderia fazer a diferença.

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

[video]

### 👨‍💻 Automatizar o caso de teste criado
Foi utiliz
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

### 🔩 Analise os testes de ponta a ponta

Explique que eles verificam esses testes e porquê.

```
Dar exemplos
```

### ⌨️ E testes de estilo de codificação

Explique que eles verificam esses testes e porquê.

```
Dar exemplos
```

## 📦 Implantação

Adicione notas adicionais sobre como implantar isso em um sistema ativo


## 🖇️ Colaborando

Por favor, leia o [COLABORACAO.md](https://gist.github.com/usuario/linkParaInfoSobreContribuicoes) para obter detalhes sobre o nosso código de conduta e o processo para nos enviar pedidos de solicitação.

## 📌 Versão

Nós usamos [SemVer](http://semver.org/) para controle de versão. Para as versões disponíveis, observe as [tags neste repositório](https://github.com/suas/tags/do/projeto). 

## ✒️ Autores

Mencione todos aqueles que ajudaram a levantar o projeto desde o seu início

* **Um desenvolvedor** - *Trabalho Inicial* - [umdesenvolvedor](https://github.com/linkParaPerfil)
* **Fulano De Tal** - *Documentação* - [fulanodetal](https://github.com/linkParaPerfil)

Você também pode ver a lista de todos os [colaboradores](https://github.com/usuario/projeto/colaboradores) que participaram deste projeto.

## 📄 Licença

Este projeto está sob a licença (sua licença) - veja o arquivo [LICENSE.md](https://github.com/usuario/projeto/licenca) para detalhes.

## 🎁 Expressões de gratidão

* Conte a outras pessoas sobre este projeto 📢;
* Convide alguém da equipe para uma cerveja 🍺;
* Um agradecimento publicamente 🫂;
* etc.


---
⌨️ com ❤️ por [Armstrong Lohãns](https://gist.github.com/lohhans) 😊
