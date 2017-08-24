package ${package!}

import don.core.common.service.ServiceBase
import ${(data.basePackage)!}.dao.I${domainName!}Dao
import ${(data.basePackage)!}.service.I${domainName!}Service
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * ${(table.tableComment)!}
 * Created by ${(data.author)!}.
 * Copyright (c) ${.now}, All Rights Reserved.
 * ${(data.url)!}
 */
@Service
class ${className!} : ServiceBase(), I${className!} {

    @Autowired
    private lateinit var ${domainName?uncap_first!}Dao: I${domainName!}Dao

    override fun deleteById(${(primaryData.name)!}: ${(primaryData.dataType.javaType)!}) = ${domainName?uncap_first!}Dao.deleteByPrimaryKey(${(primaryData.name)!})

    override fun findById(${(primaryData.name)!}: ${(primaryData.dataType.javaType)!}) = ${domainName?uncap_first!}Dao.selectByPrimaryKey(${(primaryData.name)!})

}