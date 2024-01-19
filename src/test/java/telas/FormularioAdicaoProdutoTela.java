package telas;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormularioAdicaoProdutoTela extends BaseTela {

    public FormularioAdicaoProdutoTela(WebDriver app) {
        super(app);
    }


    public FormularioAdicaoProdutoTela preencherNomeProduto(String key_nomeProduto){
        app.findElement(By.id("com.lojinha:id/productName")).click();
        app.findElement(By.id("com.lojinha:id/productName")).findElement(By.id("com.lojinha:id/editText")).sendKeys(key_nomeProduto);

        return this;
    }

    public FormularioAdicaoProdutoTela preencherValorProduto(String key_valorProduto){
        app.findElement(By.id("com.lojinha:id/productValue")).click();
        app.findElement(By.id("com.lojinha:id/productValue")).findElement(By.id("com.lojinha:id/editText")).sendKeys(key_valorProduto);

        return this;
    }

    public FormularioAdicaoProdutoTela preencherCoresProduto(String key_cores) {
        app.findElement(By.id("com.lojinha:id/productColors")).click();
        app.findElement(By.id("com.lojinha:id/productColors")).findElement(By.id("com.lojinha:id/editText")).sendKeys(key_cores);

        return this;
    }

    public FormularioAdicaoProdutoTela submissaoComErro() {
        app.findElement(By.id("com.lojinha:id/saveButton")).click();

        return this;
    }

    public String obterMensagemDeErro() {
       return capturarToast();
    }
}
