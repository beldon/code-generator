package ${package!}

import ${(data.basePackage)!}.domain.${domainName!}

/**
 * ${(table.tableComment)!}
 * Created by ${(data.author)!}.
 * Copyright (c) ${.now}, All Rights Reserved.
 * ${(data.url)!}
 */
interface ${className!} {
    fun findById(${(primaryData.name)!}: ${(primaryData.dataType.javaType)!}): ${domainName!}?

    fun deleteById(${(primaryData.name)!}: ${(primaryData.dataType.javaType)!}): Int
}