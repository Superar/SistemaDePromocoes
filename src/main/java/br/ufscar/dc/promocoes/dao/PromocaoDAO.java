package br.ufscar.dc.promocoes.dao;

public class PromocaoDAO {
    private final static String CRIAR_PROMOCAO_SQL = "INSERT INTO PROMOCOES.PROMOCAO"
            + " (URLSITE, CNPJHOTEL, PRECO, DATAINICIAL, DATAFINAL)"
            + " VALUES (?, ?, ?, ?, ?)";
    
}
