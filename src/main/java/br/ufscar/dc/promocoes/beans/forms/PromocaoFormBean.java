package br.ufscar.dc.promocoes.beans.forms;

import java.util.ArrayList;
import java.util.List;

public class PromocaoFormBean {
    String URLSite, CNPJHotel, preco, dataInicial, dataFinal;

    public String getURLSite() {
        return URLSite;
    }

    public void setURLSite(String URLSite) {
        this.URLSite = URLSite;
    }

    public String getCNPJHotel() {
        return CNPJHotel;
    }

    public void setCNPJHotel(String CNPJHotel) {
        this.CNPJHotel = CNPJHotel;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public List<String> validar() {
        List<String> erros = new ArrayList<>();


        return (erros.isEmpty() ? null : erros);
    }
}
