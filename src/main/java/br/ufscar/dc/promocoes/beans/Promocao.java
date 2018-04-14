package br.ufscar.dc.promocoes.beans;

import java.util.Date;

public class Promocao {

    private int id;
    private String URLSite, CNPJHotel;
    private double preco;
    private Date dataInicial, dataFinal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

}
