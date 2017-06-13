package ${package!};

import don.core.common.service.ServiceBase;
import ${(data.basePackage)!}.dao.I${domainName!}Dao;
import ${(data.basePackage)!}.service.I${domainName!}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${(data.basePackage)!}.domain.${domainName!};
import java.util.Optional;

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
    public Optional<${domainName!}> findById(String uid) {
        ${domainName} ${domainName?uncap_first!} = ${domainName?uncap_first!}Dao.selectByPrimaryKey(${(primaryData.name)!});
        return Optional.ofNullable(${domainName?uncap_first!});
     }

    @Override
    public void deleteById(String uid) {
        ${domainName?uncap_first!}Dao.deleteByPrimaryKey(${(primaryData.name)!});
    }

}