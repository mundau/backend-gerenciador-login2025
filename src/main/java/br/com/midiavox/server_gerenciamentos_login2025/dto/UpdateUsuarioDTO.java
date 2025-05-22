package br.com.midiavox.server_gerenciamentos_login2025.dto;

public class UpdateUsuarioDTO {

    private String email;
    private String password;    // senha atual
    private String newPassword; // nova senha

    // Getters y Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
