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
public class ${simpleName}<#if superclassSimpleName??> extends ${superclassSimpleName}<${entityClassSimpleName}><#else> implements EntityListener<${entityClassSimpleName}></#if> {
<#if !superclassSimpleName??>

    @Override
    public void preInsert(${entityClassSimpleName} entity, PreInsertContext context) {
    }

    @Override
    public void preUpdate(${entityClassSimpleName} entity, PreUpdateContext context) {
    }

    @Override
    public void preDelete(${entityClassSimpleName} entity, PreDeleteContext context) {
    }

    @Override
    public void postInsert(${entityClassSimpleName} entity, PostInsertContext context) {
    }

    @Override
    public void postUpdate(${entityClassSimpleName} entity, PostUpdateContext context) {
    }

    @Override
    public void postDelete(${entityClassSimpleName} entity, PostDeleteContext context) {
    }
</#if>
}