package br.ufscar.dc.promocoes.dao;

import br.ufscar.dc.promocoes.beans.Hotel;
import br.ufscar.dc.promocoes.beans.Site;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SiteDAO {

    private final static String CRIAR_SITE_SQL = "INSERT INTO PROMOCOES.SITE"
            + " (URL, NOME, SENHA, TELEFONE)"
            + " VALUES (?, ?, ?, ?)";
    DataSource dataSource;

    public SiteDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Site gravarSite(Site site) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(CRIAR_SITE_SQL);) {
            ps.setString(1, site.getUrl());
            ps.setString(2, site.getNome());
            ps.setString(3, site.getSenha());
            ps.setString(4, site.getTelefone());
            ps.execute();
        }
        return site;
    }
}
