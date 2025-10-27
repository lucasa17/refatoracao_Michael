package main.java.br.exemplo.atividade;

/**
 * Classe principal para testar manualmente o serviço de cadastro.
 */
public class App {
    public static void main(String[] args) {
        AuthService auth = new AuthService();
        CadastroService cadastro = new CadastroService(auth);

        System.out.println("Teste 1: E-mail inválido");
        cadastro.cadastrar("email_invalido", "senha123");

        System.out.println("\nTeste 2: E-mail válido");
        cadastro.cadastrar("usuario@teste.com", "senhaSegura123");

        System.out.println("\nTeste 3: Reuso direto do AuthService");
        String senha = "minhaSenha";
        String hash = auth.hashSenha(senha);
        System.out.println("Confere senha: " + auth.confereSenha(senha, hash));
    }
}
