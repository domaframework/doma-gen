select
<#list entityDesc.entityPropertyDescs as property>
	${property.columnName}<#if property_has_next>,</#if>
</#list>
from
	${entityDesc.tableName}
where
<#list entityDesc.idEntityPropertyDescs as property>
	${property.columnName} = /* ${property.name} */<#if property.number>1<#else>'a'</#if>
	and
</#list>
<#if entityDesc.idEntityPropertyDescs?size gt 0 && entityDesc.versionEntityPropertyDesc??>
	${entityDesc.versionEntityPropertyDesc.columnName} = /* ${entityDesc.versionEntityPropertyDesc.name} */1
</#if>
