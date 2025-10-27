package main.java.br.exemplo.atividade;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes automatizados para CadastroService e AuthService.
 * Requisitos:
 * - E-mail inválido → cadastrar retorna false.
 * - E-mail válido + senha válida → cadastrar retorna true e gera hash.
 * - Reuso do AuthService em outro contexto.
 */
public class CadastroServiceTest {

    private final AuthService authService = new AuthService();
    private final CadastroService cadastroService = new CadastroService(authService);

    @Test
    void deveRetornarFalseParaEmailInvalido() {
        boolean resultado = cadastroService.cadastrar("email_invalido", "senha123");
        assertFalse(resultado, "Deveria retornar false para e-mail inválido");
    }

    @Test
    void deveCadastrarComEmailValidoESenhaValida() {
        boolean resultado = cadastroService.cadastrar("usuario@teste.com", "senha123");
        assertTrue(resultado, "Deveria retornar true para e-mail e senha válidos");
    }

    @Test
    void deveReusarAuthServiceEmOutroContexto() {
        String senha = "minhaSenhaSegura";
        String hash = authService.hashSenha(senha);

        assertTrue(authService.isEmailValido("teste@dominio.com"), "E-mail válido deve retornar true");
        assertTrue(authService.confereSenha(senha, hash), "Senha deve coincidir com o hash gerado");
        assertFalse(authService.confereSenha("senhaErrada", hash), "Senha incorreta não deve coincidir");
    }
}
