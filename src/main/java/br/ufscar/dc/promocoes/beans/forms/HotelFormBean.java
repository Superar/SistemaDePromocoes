package br.ufscar.dc.promocoes.beans.forms;

import java.util.ArrayList;
import java.util.List;

public class HotelFormBean {
    String nome, cnpj, senha, cidade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public List<String> validar() {
        List<String> erros = new ArrayList<>();

        if (this.nome.isEmpty()){
            erros.add("Preencha o nome do hotel");
        }

        if(this.cnpj.length() != 14){
            erros.add("CNPJ não compatível");
        } else if(!this.cnpj.matches("[0-9]+") ){
            erros.add("O CNPJ só pode conter números");
        }

        if(this.senha.isEmpty()){
            erros.add("Forneça uma senha para o hotel");
        }

        if (this.cidade.isEmpty()){
            erros.add("Preencha o nome da cidade");
        }


        return erros;
    }
}
