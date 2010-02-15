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
@Dao<#if configClassSimpleName??>(config = ${configClassSimpleName}.class)</#if>
public interface ${simpleName} {

<#if entityDesc.idEntityPropertyDescs?size gt 0>
    /**
     * @return the ${entityDesc.simpleName} entity
     */
    @Select
    ${entityDesc.simpleName} selectById(<#list entityDesc.idEntityPropertyDescs as property>${property.propertyClassSimpleName} ${property.name}<#if property_has_next>, </#if></#list>);

</#if>
<#if entityDesc.idEntityPropertyDescs?size gt 0 && entityDesc.versionEntityPropertyDesc??>
    /**
     * @return the ${entityDesc.simpleName} entity
     */
    @Select(ensureResult = true)
    ${entityDesc.simpleName} selectByIdAndVersion(<#list entityDesc.idEntityPropertyDescs as property>${property.propertyClassSimpleName} ${property.name}, </#list>${entityDesc.versionEntityPropertyDesc.propertyClassSimpleName} ${entityDesc.versionEntityPropertyDesc.name});

</#if>
    /**
     * @return affected rows
     */
    @Insert
    int insert(${entityDesc.simpleName} entity);

    /**
     * @return affected rows
     */
    @Update
    int update(${entityDesc.simpleName} entity);

    /**
     * @return affected rows
     */
    @Delete
    int delete(${entityDesc.simpleName} entity);
}