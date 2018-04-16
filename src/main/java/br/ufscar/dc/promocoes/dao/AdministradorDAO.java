package br.ufscar.dc.promocoes.dao;

import br.ufscar.dc.promocoes.beans.Administrador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AdministradorDAO {

    private final static String CRIAR_ADMINISTRADOR_SQL = "INSERT INTO PROMOCOES.ADMINISTRADOR"
            + " (LOGIN, SENHA)"
            + " VALUES (?, ?)";

    DataSource dataSource;

    public AdministradorDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Administrador gravarAdministrador(Administrador administrador) throws SQLException, NamingException {
        try (Connection con = dataSource.getConnection();
                PreparedStatement ps = con.prepareStatement(CRIAR_ADMINISTRADOR_SQL);) {
            ps.setString(1, administrador.getLogin());
            ps.setString(2, administrador.getSenha());
            ps.execute();
        }
        return administrador;
    }
}
