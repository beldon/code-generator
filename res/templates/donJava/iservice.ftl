package ${package!};

import ${(data.basePackage)!}.domain.${domainName!};
import java.util.Optional;

/**
 * ${(table.tableComment)!}
 * Created by ${(data.author)!}.
 * Copyright (c) ${.now}, All Rights Reserved.
 * ${(data.url)!}
 */
public interface ${className!} {

    Optional<${domainName!}> findById(${(primaryData.type.javaType)!} ${(primaryData.name)!});

    void deleteById(${(primaryData.type.javaType)!} ${(primaryData.name)!});
}