package br.com.alura.leilao.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {

    private LoginPage pagina;

    @BeforeEach
    public void beforeEach() {
        this.pagina = new LoginPage();
    }

    @AfterEach
    public void afterEach() {
        pagina.fechar();
    }

    @Test
    public void deveriaefeturaLoginComDadosValidos() {
        pagina.preencheFormularioDeLogin("fulano", "pass");
        pagina.efetuaLogin();

        Assertions.assertFalse(pagina.isPaginaDeLogin());
        Assertions.assertEquals("fulano", pagina.getNomeUsuarioLogado());
    }

    @Test
    public void naoDeveriaLogarComDadosInvalidos() {
        pagina.preencheFormularioDeLogin("invalido", "123123");
        pagina.efetuaLogin();

        Assertions.assertTrue(pagina.isPaginaDeLoginComDadosInvalidos());
        Assertions.assertNull(pagina.getNomeUsuarioLogado());
        Assertions.assertTrue(pagina.contemTexto("Usuário e senha inválidos."));
    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
        pagina.navegaParaPaginaDeLeiloes();

        Assertions.assertTrue(pagina.isPaginaDeLogin());
        Assertions.assertFalse(pagina.contemTexto("Dados do Leilão"));
    }
}
