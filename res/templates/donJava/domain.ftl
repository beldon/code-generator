package ${package!};

<#list importType as im>
import ${im!};
</#list>

/**
 * ${(table.tableComment)!}
 * Created by ${(data.author)!}.
 * Copyright (c) ${.now}, All Rights Reserved.
 * ${url!}
 */
public class ${domainName!} {
<#list columnDatas as columnData>
    /**
     * ${(columnData.column.columnComment)!}
     */
    private ${(columnData.dataType.javaType)!} ${(columnData.name)!};
</#list>

<#list columnDatas as columnData>
    public ${(columnData.dataType.javaType)!} get${(columnData.name)?cap_first!}() {
        return ${(columnData.name)!};
    }

    public void set${(columnData.name)?cap_first!}(${(columnData.dataType.javaType)!} ${(columnData.name)!}) {
        this.${(columnData.name)!} = ${(columnData.name)!};
    }

</#list>
}
