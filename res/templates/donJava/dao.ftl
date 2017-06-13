package ${package!};

import ${(data.basePackage)!}.domain.${domainName!};
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import don.core.bean.Page;
import don.core.bean.Query;
import java.util.List;

/**
 * ${(table.tableComment)!}
 * Created by ${(data.author)!}.
 * Copyright (c) ${.now}, All Rights Reserved.
 * ${(data.url)!}
 */
@Repository
public interface ${className!} {

    int insert(${domainName!} ${domainName?uncap_first!});

    int insertSelective(${domainName!} ${domainName?uncap_first!});

    @Delete("delete from ${(table.tableName)!} where ${(primaryData.column.columnName)!} = ${r"#{"}${(primaryData.name)!}${r"}"}")
    int deleteByPrimaryKey(${(primaryData.type.javaType)!} ${(primaryData.name)!});

    int updateByPrimaryKey(${domainName!} ${domainName?uncap_first!});

    int updateByPrimaryKeySelective(${domainName!} ${domainName?uncap_first!});

    @Select("select * from ${(table.tableName)!} where ${(primaryData.column.columnName)!} = ${r"#{"}${(primaryData.name)!}${r"}"}")
    @ResultMap("BaseResultMap")
    ${domainName!} selectByPrimaryKey(${(primaryData.type.javaType)!} ${(primaryData.name)!});

    @ResultMap("BaseResultMap")
    List<${domainName!}> query(@Param("query") Query query);

    @ResultMap("BaseResultMap")
    List<${domainName!}> query(@Param("${domainName?uncap_first!}") Page<${domainName!}> page, @Param("query") Query query);

}