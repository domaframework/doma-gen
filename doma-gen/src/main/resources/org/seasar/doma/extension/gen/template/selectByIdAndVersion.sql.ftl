select
<#list entityDesc.entityPropertyDescs as property>
    ${property.columnName}<#if property_has_next>,</#if>
</#list>
from
    ${entityDesc.qualifiedTableName}
where
<#list entityDesc.idEntityPropertyDescs as property>
    ${property.columnName} = /* ${property.name} */1
    and
</#list>
<#if entityDesc.idEntityPropertyDescs?size gt 0 && entityDesc.versionEntityPropertyDesc??>
    ${entityDesc.versionEntityPropertyDesc.columnName} = /* ${entityDesc.versionEntityPropertyDesc.name} */1
</#if>
