package br.ufscar.dc.promocoes.beans.forms;

import java.util.ArrayList;
import java.util.List;

public class SiteFormBean {
    String nome;
    String url;
    String senha;
    String telefone;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<String> validar() {
        List<String> erros = new ArrayList<>();

        if (this.nome.isEmpty()){
            erros.add("Preencha o nome do site");
        }

        if(this.url.isEmpty()){
            erros.add("Defina a url do site");
        }

        if (this.senha.isEmpty()){
            erros.add("Forneça uma senha para o site");
        }

        if (this.telefone.isEmpty()){
            erros.add("Informe o telefone de contato");
        }

        return erros;
    }
}
