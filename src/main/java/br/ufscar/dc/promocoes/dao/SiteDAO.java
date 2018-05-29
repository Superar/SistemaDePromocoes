package br.ufscar.dc.promocoes.dao;

import br.ufscar.dc.promocoes.beans.Site;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.naming.NamingException;
import javax.sql.DataSource;

@ApplicationScoped
public class SiteDAO {

    private final static String CRIAR_SITE_SQL = "INSERT INTO PROMOCOES.SITE"
            + " (URL, NOME, SENHA, TELEFONE)"
            + " VALUES (?, ?, ?, ?)";

    private final static String RECUPERAR_SITE_SQL = "SELECT"
            + " URL, NOME, SENHA, TELEFONE"
            + " FROM PROMOCOES.SITE"
            + " WHERE URL = ?";

    @Resource(name = "jdbc/PromocoesDBLocal")
    private DataSource dataSource;

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

    public Site recuperarSite(String URL) throws SQLException, NamingException {
        Site site = null;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(RECUPERAR_SITE_SQL)) {

            ps.setString(1, URL);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    site = new Site();
                    site.setUrl(rs.getString("URL"));
                    site.setNome(rs.getString("NOME"));
                    site.setSenha(rs.getString("SENHA"));
                    site.setTelefone(rs.getString("TELEFONE"));
                }
            }

        }

        return site;
    }
}
