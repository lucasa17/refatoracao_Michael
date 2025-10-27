package main.java.br.exemplo.atividade;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Serviço reutilizável de autenticação.
 * Fornece validação de e-mail e hash seguro de senha.
 */
public class AuthService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public boolean isEmailValido(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }
        return EmailValidator.getInstance().isValid(email);
    }

    public String hashSenha(String senha) {
        if (senha == null || senha.isBlank()) {
            throw new IllegalArgumentException("Senha não pode ser nula ou vazia");
        }
        return encoder.encode(senha);
    }

    public boolean confereSenha(String senha, String hash) {
        if (senha == null || hash == null) {
            return false;
        }
        return encoder.matches(senha, hash);
    }
}
