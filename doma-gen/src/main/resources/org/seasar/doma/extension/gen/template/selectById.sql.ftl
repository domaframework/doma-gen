select
<#list entityDesc.entityPropertyDescs as property>
  ${property.columnName}<#if property_has_next>,</#if>
</#list>
from
  ${entityDesc.tableName}
where
<#list entityDesc.idEntityPropertyDescs as property>
  ${property.columnName} = /* ${property.name} */<#if property.number>1<#else>'a'</#if><#if property_has_next>
  and</#if>
</#list>
