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
 * 
<#if lib.author??>
 * @author ${lib.author}
</#if>
 */
public class ${simpleName}<#if superclassSimpleName??> extends ${superclassSimpleName}<#else> implements EntityListener<${entityClassSimpleName}></#if> {
<#if !superclassSimpleName??>

    @Override
    public void preInsert(${entityClassSimpleName} entity) {
    }

    @Override
    public void preUpdate(${entityClassSimpleName} entity) {
    }

    @Override
    public void preDelete(${entityClassSimpleName} entity) {
    }
</#if>
}