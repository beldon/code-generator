package ${package!};

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

/**
* ${(table.tableComment)!}
* Created by ${author!}.
* Copyright (c) ${.now}, All Rights Reserved.
* ${(data.url)!}
*/
public interface ${className!} {
    int insert();
    int update();
    int delete(Long id);
}
