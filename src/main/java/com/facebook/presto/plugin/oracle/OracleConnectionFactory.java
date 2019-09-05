package com.facebook.presto.plugin.oracle;

import com.facebook.presto.plugin.jdbc.BaseJdbcConfig;
import com.facebook.presto.plugin.jdbc.ConnectionFactory;
import com.google.inject.Inject;
import oracle.jdbc.OracleDriver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class OracleConnectionFactory implements ConnectionFactory {

    private final OracleDriver oracleDriver;

    private final BaseJdbcConfig config;

    @Inject
    public OracleConnectionFactory(BaseJdbcConfig config) {
        this.oracleDriver = new OracleDriver();
        this.config = config;
    }

    @Override
    public Connection openConnection() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", config.getConnectionUser());
        properties.setProperty("password", config.getConnectionPassword());
        return oracleDriver.connect(config.getConnectionUrl(), properties);
    }
}
