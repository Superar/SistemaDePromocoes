package br.ufscar.dc.promocoes.views;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class Auth implements Serializable {
    private String role = "";
    private String nome = "";
    private String senha= "";

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String login(){
        return "index?faces-redirect=true";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "loginForm?faces-redirect=true";
    }

    public boolean isLogged() {
        return role != null && !role.isEmpty();
    }

    public boolean isAdmin() {
        return role.equals("administrador");
    }

    public boolean isHotel() {
        return role.equals("hotel");
    }

    public boolean isSite() {
        return role.equals("site");
    }

}
