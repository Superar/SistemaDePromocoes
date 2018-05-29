package br.ufscar.dc.promocoes.views;

import br.ufscar.dc.promocoes.beans.Administrador;
import br.ufscar.dc.promocoes.beans.Hotel;
import br.ufscar.dc.promocoes.beans.Site;
import br.ufscar.dc.promocoes.dao.AdministradorDAO;
import br.ufscar.dc.promocoes.dao.HotelDAO;
import br.ufscar.dc.promocoes.dao.SiteDAO;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class Auth implements Serializable {
    public enum Role {
        ADMIN("administrador"),
        HOTEL("hotel"),
        SITE("site"),
        NONE("");

        private final String nome;

        Role(String nome) {
            this.nome = nome;
        }

        @Override
        public String toString() {
            return nome;
        }
    }

    private Role role = Role.NONE;
    private String nome = "";
    private String senha= "";
    private String id = "";

    @Inject
    MensagemHandler msg;

    @Inject
    AdministradorDAO administradorDAO;

    @Inject
    HotelDAO hotelDAO;

    @Inject
    SiteDAO siteDAO;

    public String getRole() {
        return role.toString();
    }

    public void setRole(String role) {
        if(role.equals("administrador"))
            this.role = Role.ADMIN;
        else if(role.equals("hotel"))
            this.role = Role.HOTEL;
        else if(role.equals("site"))
            this.role = Role.SITE;
        else
            this.role = Role.NONE;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String login(){

        try {
            if (role == Role.ADMIN) {
                Administrador admin = administradorDAO.recuperarAdministrador(id);

                if (admin == null)
                    msg.setMensagem(true, "usuario não existe", MensagemHandler.TipoMensagem.TIPO_ERRO);

                else if (!admin.getSenha().equals(senha))
                    msg.setMensagem(true, "senha inválida", MensagemHandler.TipoMensagem.TIPO_ERRO);

                else {
                    nome = admin.getLogin();
                    return "index?faces-redirect=true";
                }


            } else if (role == Role.SITE) {
                Site site = siteDAO.recuperarSite(id);

                if (site == null)
                    msg.setMensagem(true, "site não cadastrado", MensagemHandler.TipoMensagem.TIPO_ERRO);

                else if (!site.getSenha().equals(senha))
                    msg.setMensagem(true, "senha inválida", MensagemHandler.TipoMensagem.TIPO_ERRO);

                else {
                    nome = site.getNome();
                    return "index?faces-redirect=true";
                }

            } else if (role == Role.HOTEL) {
                Hotel hotel = hotelDAO.recuperarHotel(id);
                if (hotel == null)
                    msg.setMensagem(true, "hotel não cadastrado", MensagemHandler.TipoMensagem.TIPO_ERRO);

                else if (!hotel.getSenha().equals(senha))
                    msg.setMensagem(true, "senha inválida", MensagemHandler.TipoMensagem.TIPO_ERRO);

                else {
                    nome = hotel.getNome();
                    return "index?faces-redirect=true";
                }
            }

        } catch (SQLException | NamingException ex) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
            return "errors?faces-redirect=true";
        }

        senha = "";
        return "loginForm";
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "loginForm?faces-redirect=true";
    }

    public boolean isLogged() {
        return role != Role.NONE;
    }

    public boolean isAdmin() {
        return role == Role.ADMIN;
    }

    public boolean isHotel() {
        return role == Role.HOTEL;
    }

    public boolean isSite() {
        return role == Role.SITE;
    }

}
