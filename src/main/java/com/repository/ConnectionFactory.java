package com.repository;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {

    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String JAVA_COMP_ENV_JDBC_POSTGRES = "java:/comp/env/jdbc/postgresPlanes";

    public static Connection getConnection() {
        try {
            InitialContext ic = new InitialContext();
            DataSource dataSource = (DataSource) ic.lookup(JAVA_COMP_ENV_JDBC_POSTGRES);
            if (isDataSourceFound(dataSource)) {
                throw new RuntimeException("Data source not found");
            }
            return dataSource.getConnection();
        } catch (NamingException | SQLException e) {
            LOGGER.log(Level.WARNING, "Error while getting connection", e);
            throw new RuntimeException(e);
        }
    }

    private static boolean isDataSourceFound(DataSource dataSource) {
        return dataSource == null;
    }
}
