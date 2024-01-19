package modulos.produto;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import telas.LoginTela;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@DisplayName("Testes Mobile do Módulo de Produto")
public class ProdutoTest {
    private WebDriver app;

    @BeforeEach
    public void beforeEach() throws MalformedURLException {
        //Abrir o app
        DesiredCapabilities capacidades = new DesiredCapabilities();
        capacidades.setCapability("deviceName", "Google Nexus 4");
        capacidades.setCapability("platformName","Android");
        capacidades.setCapability("udid","192.168.157.101:5555");
        capacidades.setCapability("appPackage","com.lojinha");
        capacidades.setCapability("appActivity","com.lojinha.ui.MainActivity");
        capacidades.setCapability("app","C:\\Users\\04000056465\\Downloads\\Lojinha+Android+Nativa\\lojinha-nativa.apk");

        this.app = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capacidades);
        this.app.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterEach
    public void afterEach() {
        app.quit();
    }

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
}
