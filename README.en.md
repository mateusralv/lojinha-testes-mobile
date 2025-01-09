> [Portuguese version](https://github.com/mateusralv/lojinha-testes-mobile/blob/main/README.md)
# Case: Quality in the Android Loja App

![Alt ou título da imagem](https://snapstack.cz/wp-content/uploads/2020/07/ToolsForBuildingApps_636x300-op.png)

> The main goal of this study to ensure the quality of an Android mobile application from cases of manual and automated tests.

## 🚀 Getting Started

Test project for the App Loja developed in [Julio de Lima Software Quality and Testing Program](https://mentoria.juliodelima.com.br/). 

The app has:
1. Login screen
2. Screen for products list
3. Screen for adding products 
4. Screen for adding components to a product.
   
<img src="/images/loginTela.png" height="300"> | <img src="/images/ListadeProdutosTela.png" height="300"> | <img src="/images/AdicionarProdutoTela.png" height="300"> | <img src="/images/AdicionarComponenteTela.png" height="300">

Features:
1. Adding new products with or without components
2. Exclude products
3. Edit products
4. Leave the shop.

For videos of the running of the app [see:](https://drive.google.com/file/d/1SjSIc3uqDEuE_9K5n3M3jkGoFqX3e8F1/view?usp=sharing)
   
## 🚧 Adjustments and improvements

The study is still under development and the next updates will focus on the following tasks:

1. - [x] Visual Regression Tests
3. - [x] Exploit Tests and validate APP requests to a REST API
4. - [x] Create the first test case that will be automated based on a business rule
5. - [x] Automate the created test case
6. - [ ] Create test cases that cover all business rules 
7. - [ ] Automate all test cases
8. - [ ] Translation of the project to English  

## 🛠️ Built on:

* [Intellij](https://www.jetbrains.com/pt-br/idea/)
* [Maven](https://maven.apache.org/) 
* [Selenium](https://www.selenium.dev/) 
* [JUnit](https://junit.org/junit4/) 
* [Appium](https://appium.io/docs/en/2.4/) 
* [Appium Inspector](https://github.com/appium/appium-inspector)
* [Genymotion](https://www.genymotion.com/) 
* [James PROXY](https://github.com/james-proxy/james) 
* [Page Object Pattern](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/) 
* [Gherkin Reference](https://cucumber.io/docs/gherkin/) 


## ⚙️ Running the tests
### 🗺️ Exploratory Tests
  There is an official Android guide that shows the main quality criteria for an Android app. See [here](https://developer.android.com/training/testing/fundamentals?hl=pt-br).  
From this guide and studies I followed some key points for the exploratory tests:  

##### 👾Tests related to Android system
  - Rotation: Rotate the device on various screens
  - Permission: What happens if permissions are not given to the app?
  - Airplane mode: Use the app in airplane mode and put the device in airplane mode while using the app.
  - Interruptions: How does the app work when you receive a notification or connection?
  - Connections: How does the app behave on slow connections? If you switch from wi-fi to mobile data does the app still work? If you change the language the currency changes?

#### 📈Tests related to business rules
- [x] Execute business rules
- [x] Continuity of flows
- [x] Listing of many records
- [x] Masks, enabling/disabling and typing fields
- [x] Network variation
- [x] API integration
- [x] Loss of focus of the Application

#### 🕵🏻‍♂️Validate APP requests to a REST API
- Requirements:
  James Proxy and Genymotion
  
- Steps:
  1. Open the James Proxy and take the port
  2. Open the terminal and take the local ip
  3. Genymotion: Configure the proxy on the device
  4. Interact with the App and view requests in James Proxy  

  <img src="/images/APIRequests1.png" height="300">
  
### 🛠️Create the first test case that will be automated based on a business rule
Among the business rules is that:
```
"It is only possible to add a product that the value is between R$0.01 and R$ 7,000.00"
```
So based on the  **Gherkin** model we have:  
```
Given the app store installed on the device  
And the user accesses the login page  
And fill in your valid credentials  
And triggers the login option  
When you press the "+" button to add a product  
And fill with name and value above $ 7,000.00  
Then an error alert is displayed.  
```


<img src="/images/MensagemDeErro.png" height="300">

### 👨‍💻 Automated Test
JUnit and Appium libraries were used together with Selenium Webdriver. The rule for the documentation used was #PageObjects 

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
                .preencherValorProduto("7001")
                .preencherCoresProduto("Preto,Branco")
                .submissaoComErro()
                .obterMensagemDeErro();

        //Validar a mensagem de erro
        Assertions.assertEquals("O Valor do Produto deve estar entre R$0,01 e R$7.000,00",mensagemApresentada);
    }
~~~
See the full code [here](https://github.com/mateusralv/lojinha-testes-mobile/blob/main/src/test/java/modulos/produto/ProdutoTest.java#L37C16-L56C16 
).

## 🔩 Results:
### API requests
```
When the login is done is sent the 3 requests of type GET equal, we could talk to the development team to decrease to one and
decreasing the login time of the usual that if we thought on a scale of thousands users could make a difference.
```
### Exploratory Tests
* Loss of focus of the Application

```
Steps:
Given the fabric adding products
And the usual is filling in the name
When a notification arrives
And the user answers the notification
Then back to continue the action in the app

Result experienced:
The app flow continues to work.

Current Result:
The app closes and the device’s home screen is displayed.

Evidence:
[You can use a picture or video here]
```

### Automated Tests
```
1/1 Test Passed
```
