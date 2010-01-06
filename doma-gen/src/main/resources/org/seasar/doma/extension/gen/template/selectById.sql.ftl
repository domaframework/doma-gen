select
<#list entityDesc.entityPropertyDescs as property>
    ${property.columnName}<#if property_has_next>,</#if>
</#list>
from
    ${entityDesc.qualifiedTableName}
where
<#list entityDesc.idEntityPropertyDescs as property>
    ${property.columnName} = /* ${property.name} */1<#if property_has_next>
    and</#if>
</#list>
