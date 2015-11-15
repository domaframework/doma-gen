<#-- このテンプレートに対応するデータモデルのクラスは org.seasar.doma.extension.gen.EntityListenerDesc です -->
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
public class ${simpleName}<#if superclassSimpleName??> extends ${superclassSimpleName}<<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityClassSimpleName}><#else> implements EntityListener<<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityClassSimpleName}></#if> {
<#if !superclassSimpleName??>

    @Override
    public void preInsert(<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityClassSimpleName} entity, PreInsertContext<<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityClassSimpleName}> context) {
    }

    @Override
    public void preUpdate(<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityClassSimpleName} entity, PreUpdateContext<<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityClassSimpleName}> context) {
    }

    @Override
    public void preDelete(<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityClassSimpleName} entity, PreDeleteContext<<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityClassSimpleName}> context) {
    }

    @Override
    public void postInsert(<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityClassSimpleName} entity, PostInsertContext<<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityClassSimpleName}> context) {
    }

    @Override
    public void postUpdate(<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityClassSimpleName} entity, PostUpdateContext<<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityClassSimpleName}> context) {
    }

    @Override
    public void postDelete(<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityClassSimpleName} entity, PostDeleteContext<<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityClassSimpleName}> context) {
    }
</#if>
}