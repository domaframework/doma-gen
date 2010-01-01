<#import "/lib.ftl" as lib>
<#if lib.copyright??>
${lib.copyright}
</#if>
<#if packageName??>
package ${packageName};
</#if>

<#list importNames as importName>
import ${importName};
</#list>

/**
<#if lib.author??>
 * @author ${lib.author}
</#if>
 */
@Dao(config = ${configClassSimpleName}.class)
public interface ${simpleName} {

    /**
     * @return affected rows
     */
    @Insert
    int insert(${entityClassSimpleName} entity);

    /**
     * @return affected rows
     */
    @Update
    int update(${entityClassSimpleName} entity);

    /**
     * @return affected rows
     */
    @Delete
    int delete(${entityClassSimpleName} entity);
}