/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.doma.extension.gen.internal.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.seasar.doma.extension.gen.GenException;
import org.seasar.doma.extension.gen.GenNullPointerException;
import org.seasar.doma.extension.gen.Logger;
import org.seasar.doma.extension.gen.internal.message.Message;

/**
 * @author taedium
 * 
 */
public final class JdbcUtil {

    protected static final Pattern jdbcUrlPattern = Pattern
            .compile("jdbc:([^:]+):");

    public static Connection getConnection(DataSource dataSource) {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new GenException(Message.DOMAGEN9001, e, e);
        }
    }

    public static Statement createStatement(Connection connection) {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            throw new GenException(Message.DOMAGEN9001, e, e);
        }
    }

    public static PreparedStatement prepareStatement(Connection connection,
            String sql) {
        try {
            return connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new GenException(Message.DOMAGEN9001, e, e);
        }
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                Logger.debug(e);
            }
        }
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                Logger.debug(e);
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                Logger.debug(e);
            }
        }
    }

    public static String inferDialectName(String url) {
        return match(url, name -> {
            switch (name) {
            case "h2":
                return "h2";
            case "hsqldb":
                return "hsqldb";
            case "sqlite":
                return "sqlite";
            case "mysql":
                return "mysql";
            case "postgresql":
                return "postgres";
            case "sqlserver":
                return "mssql";
            case "oracle":
                return "oracle";
            case "db2":
                return "db2";
            default:
                return null;
            }
        });
    }

    public static String inferDriverClassName(String url) {
        return match(url, name -> {
            switch (name) {
            case "h2":
                return "org.h2.Driver";
            case "hsqldb":
                return "org.hsqldb.jdbc.JDBCDriver";
            case "sqlite":
                return "org.sqlite.JDBC";
            case "mysql":
                return "com.mysql.jdbc.Driver";
            case "postgresql":
                return "org.postgresql.Driver";
            case "sqlserver":
                return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            case "oracle":
                return "oracle.jdbc.driver.OracleDriver";
            case "db2":
                return "com.ibm.db2.jcc.DB2Driver";
            default:
                return null;
            }
        });
    }

    protected static <R> R match(String url, Function<String, R> mapper) {
        if (url == null) {
            throw new GenNullPointerException("url");
        }
        Matcher matcher = jdbcUrlPattern.matcher(url);
        if (matcher.lookingAt()) {
            String name = matcher.group(1);
            return mapper.apply(name);
        }
        return null;
    }

}
