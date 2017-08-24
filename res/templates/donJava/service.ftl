package ${package!};

import don.core.common.service.ServiceBase;
import ${(data.basePackage)!}.dao.I${domainName!}Dao;
import ${(data.basePackage)!}.service.I${domainName!}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${(data.basePackage)!}.domain.${domainName!};
import java.util.Optional;
import org.springframework.util.Assert;

/**
 * ${(table.tableComment)!}
 * Created by ${(data.author)!}.
 * Copyright (c) ${.now}, All Rights Reserved.
 * ${(data.url)!}
 */
@Service
public class ${className!} extends ServiceBase implements I${className!} {

    @Autowired
    private I${domainName!}Dao ${domainName?uncap_first!}Dao;

    @Override
    public Optional<${domainName!}> findById(${(primaryData.dataType.javaType)!} ${(primaryData.name)!}) {
        Assert.notNull(${(primaryData.name)!},"${(primaryData.name)!} is required");
        ${domainName} ${domainName?uncap_first!} = ${domainName?uncap_first!}Dao.selectByPrimaryKey(${(primaryData.name)!});
        return Optional.ofNullable(${domainName?uncap_first!});
     }

    @Override
    public void deleteById(${(primaryData.dataType.javaType)!} ${(primaryData.name)!}) {
        Assert.notNull(${(primaryData.name)!},"${(primaryData.name)!} is required");
        ${domainName?uncap_first!}Dao.deleteByPrimaryKey(${(primaryData.name)!});
    }

}