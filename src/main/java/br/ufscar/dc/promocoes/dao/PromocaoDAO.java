package br.ufscar.dc.promocoes.dao;

import br.ufscar.dc.promocoes.beans.Promocao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PromocaoDAO {

    private final static String CRIAR_PROMOCAO_SQL = "INSERT INTO PROMOCOES.PROMOCAO"
            + " (URLSITE, CNPJHOTEL, PRECO, DATAINICIAL, DATAFINAL)"
            + " VALUES (?, ?, ?, ?, ?)";
    DataSource dataSource;

    public PromocaoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Promocao gravarPromocao(Promocao promocao) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(CRIAR_PROMOCAO_SQL);) {
            ps.setString(1, promocao.getURLSite());
            ps.setString(2, promocao.getCNPJHotel());
            ps.setDouble(3, promocao.getPreco());
            ps.setDate(4, new Date(promocao.getDataInicial().getTime()));
            ps.setDate(5, new Date(promocao.getDataFinal().getTime()));
            ps.execute();
        }
        return promocao;
    }
}
