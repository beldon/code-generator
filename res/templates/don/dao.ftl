package ${package!}

import ${(data.basePackage)!}.domain.${domainName!}
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.ResultMap
import org.springframework.stereotype.Repository
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Param
import don.core.bean.Page
import don.core.bean.Query

/**
 * ${(table.tableComment)!}
 * Created by ${(data.author)!}.
 * Copyright (c) ${.now}, All Rights Reserved.
 * ${(data.url)!}
 */
@Repository
interface ${className!} {

    fun insert(${domainName?uncap_first!}: ${domainName!}): Int

    fun insertSelective(${domainName?uncap_first!}: ${domainName!}): Int

    @Delete("delete from ${(table.tableName)!} where ${(primaryData.column.columnName)!} = ${r"#{"}${(primaryData.name)!}${r"}"}")
    fun deleteByPrimaryKey(${(primaryData.name)!}: ${(primaryData.type.javaType)!}): Int

    fun updateByPrimaryKey(${domainName?uncap_first!}: ${domainName!}): Int

    fun updateByPrimaryKeySelective(${domainName?uncap_first!}: ${domainName!}): Int

    @Select("select * from ${(table.tableName)!} where ${(primaryData.column.columnName)!} = ${r"#{"}${(primaryData.name)!}${r"}"}")
    fun selectByPrimaryKey(${(primaryData.name)!}: ${(primaryData.type.javaType)!}): ${domainName!}?

    @ResultMap("BaseResultMap")
    fun query(@Param("query") query: Query): List<${domainName!}>

    fun query(@Param("page") page: Page, @Param("query") query: Query): List<${domainName!}>

}