<#-- このテンプレートに対応するデータモデルのクラスは org.seasar.doma.extension.gen.SqlTestCaseDesc です -->
<#import "/lib.ftl" as lib>
<#if lib.copyright??>
${lib.copyright}
</#if>
<#if packageName??>
package ${packageName};
</#if>

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

import junit.framework.TestCase;

import org.seasar.doma.jdbc.NoCacheSqlFileRepository;
import org.seasar.doma.jdbc.SqlFile;
import org.seasar.doma.jdbc.SqlFileRepository;
import org.seasar.doma.jdbc.dialect.Dialect;

/**
 * 
<#if lib.author??>
 * @author ${lib.author}
</#if>
 */
public class <#if entityPrefix??>${entityPrefix}</#if>${simpleName}<#if entitySuffix??>${entitySuffix}</#if> extends TestCase {

    /** */
    protected SqlFileRepository repository;

    /** */
    protected Dialect dialect;

    /** */
    protected Driver driver;

    /** */
    protected String url;

    /** */
    protected String user;

    /** */
    protected String password;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        repository = new NoCacheSqlFileRepository();
        dialect = new ${dialectClassName}();
        @SuppressWarnings("unchecked")
        Class<Driver> driverClass = (Class<Driver>) Class.forName("${driverClassName}");
        driver = driverClass.newInstance();
        DriverManager.registerDriver(driver);
        url = "${url}";
        user = "${user}";
        password = "${password}";
    }

    @Override
    protected void tearDown() throws Exception {
        DriverManager.deregisterDriver(driver);
        super.tearDown();
    }

    /**
     * 
     * @param sqlFile
     * @throws Exception
     */
    protected void execute(SqlFile sqlFile) throws Exception {
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            try {
                statement.execute(sqlFile.getSql());
            } finally {
                statement.close();
            }
        } finally {
            try {
                connection.rollback();
            } finally {
                connection.close();
            }
        }
    }

    /**
     * 
     * @return
     * @throws Exception
     */
    protected Connection getConnection() throws Exception {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * 
     * @return method
     * @throws Exception
     */
    protected Method getMethod() throws Exception {
        return getClass().getMethod(getName(), new Class[] {});
    }
<#list methodDescs as methodDesc>

    /**
     * 
     * @throws Exception
     */
    public void ${methodDesc.methodName}() throws Exception {
        SqlFile sqlFile = repository.getSqlFile(getMethod(), "${methodDesc.path}", dialect);
        execute(sqlFile);
    }
</#list>

}