package main.java.br.exemplo.atividade;

/**
 * Serviço de cadastro refatorado para reuso via AuthService.
 */
public class CadastroService {

    private final AuthService authService;

    public CadastroService(AuthService authService) {
        this.authService = authService;
    }

    public boolean cadastrar(String email, String senha) {
        if (!authService.isEmailValido(email)) {
            System.out.println("E-mail inválido!");
            return false;
        }

        String senhaHash = authService.hashSenha(senha);
        if (senhaHash == null || senhaHash.isEmpty()) {
            System.out.println("Erro ao gerar hash da senha!");
            return false;
        }

        // Aqui seria o ponto de persistência (simulação)
        System.out.println("Usuário cadastrado com sucesso!");
        System.out.println("Hash gerado: " + senhaHash);

        return true;
    }
}
