package ${package!}

<#list importType as im>
import ${im!}
</#list>

/**
 * ${(table.tableComment)!}
 * Created by ${(data.author)!}.
 * Copyright (c) ${.now}, All Rights Reserved.
 * ${url!}
 */
class ${domainName!} {
<#list columnDatas as columnData>
    /**
     * ${(columnData.column.columnComment)!}
     */
    var ${(columnData.name)!}: ${(columnData.dataType.javaType)!}? = null
</#list>
}