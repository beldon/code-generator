<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package!}.${className!}">
    <resultMap id="BaseResultMap" dataType="${(data.basePackage)!}.domain.${domainName!}">
    <#list columnDatas as columnData>
        <#if columnData.column.columnKey == "PRI">
        <id column="${(columnData.column.columnName)!}" property="${(columnData.name)!}" jdbcType="${(columnData.dataType.jdbcType)!}" /> <!--${(columnData.column.columnComment)!}-->
        <#else >
        <result column="${(columnData.column.columnName)!}" property="${(columnData.name)!}" jdbcType="${(columnData.dataType.jdbcType)!}" /> <!--${(columnData.column.columnComment)!}-->
        </#if>
    </#list>
    </resultMap>
    <!-- 除了id以外全部属性 -->
    <sql id="columns_no_id">
<#list columnDatas as columnData>
    <#if columnData.column.columnKey != "PRI">
        ${(columnData.column.columnName)!}<#if columnData_has_next>,</#if><!--${(columnData.column.columnComment)!}-->
    </#if>
</#list>
    </sql>

    <!-- 包括id在内全部属性 -->
    <sql id="columns_all">
        ${(primaryData.column.columnName)!},<include refid="columns_no_id"/>
    </sql>

    <sql id="properties_no_id">
    <#list columnDatas as columnData>
        <#if columnData.column.columnKey != "PRI">
        ${r"#{"}${(columnData.name)!}${r"}"}<#if columnData_has_next>,</#if><!--${(columnData.column.columnComment)!}-->
        </#if>
    </#list>
    </sql>
    <!-- 包括id在内全部属性 -->
    <sql id="properties_all">
        ${r"#{"}${(primaryData.name)!}${r"}"},<include refid="properties_no_id"/>
    </sql>

    <!--普通插入-->
    <insert id="insert" parameterType="${(data.basePackage)!}.domain.${domainName!}">
        <selectKey keyProperty="${(primaryData.name)!}" resultType="String" order="BEFORE">
            select  replace(uuid(),'-','')
        </selectKey>
        insert into ${(table.tableName)!} (<include refid="columns_all"/>)
        values (<include refid="properties_all" />)
    </insert>

    <!--非空插入-->
    <insert id="insertSelective" parameterType="${(data.basePackage)!}.domain.${domainName!}" >
        <selectKey keyProperty="${(primaryData.name)!}" resultType="String" order="BEFORE">
            select  replace(uuid(),'-','')
        </selectKey>
        insert into ${(table.tableName)!}
        <trim prefix="(" suffix=")" suffixOverrides="," >
        <#list columnDatas as columnData>
            <if test="${(columnData.name)!} != null" >
                ${(columnData.column.columnName)!}<#if columnData_has_next>,</#if><!--${(columnData.column.columnComment)!}-->
             </if>
        </#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides="," >
        <#list columnDatas as columnData>
            <if test="${(columnData.name)!} != null" >
                ${r"#{"}${(columnData.name)!}${r"}"}<#if columnData_has_next>,</#if><!--${(columnData.column.columnComment)!}-->
            </if>
        </#list>
        </trim>
    </insert>

    <!--更新所有-->
    <update id="updateByPrimaryKey" parameterType="${(data.basePackage)!}.domain.${domainName!}" >
        update ${(table.tableName)!}
        <set >
        <#list columnDatas as columnData>
            <#if columnData.column.columnKey != "PRI">
            ${(columnData.column.columnName)!}= ${r"#{"}${(columnData.name)!}${r"}"}<#if columnData_has_next>,</#if><!--${(columnData.column.columnComment)!}-->
            </#if>
        </#list>
        </set>
        where ${(primaryData.name)!} = ${r"#{"}${(primaryData.name)!}${r"}"}
    </update>
    <!--更新非空-->
    <update id="updateByPrimaryKeySelective" parameterType="${(data.basePackage)!}.domain.${domainName!}" >
        update ${(table.tableName)!}
        <set >
        <#list columnDatas as columnData>
            <#if columnData.column.columnKey != "PRI">
             <if test="${(columnData.name)!} != null" >
                ${(columnData.column.columnName)!}= ${r"#{"}${(columnData.name)!}${r"}"}<#if columnData_has_next>,</#if><!--${(columnData.column.columnComment)!}-->
             </if>
            </#if>
        </#list>
        </set>
        where ${(primaryData.name)!} = ${r"#{"}${(primaryData.name)!}${r"}"}
    </update>

    <select id="query" resultMap="BaseResultMap">
        select * from ${(table.tableName)!}
        <where>
            <if test="query != null">
                <trim suffixOverrides="AND">
                    <if test="query.domain != null">
                        <trim suffixOverrides="AND">
                        <#list columnDatas as columnData>
                            <#if columnData.column.columnKey != "PRI">
                            <if test="query.domain.${(columnData.name)!} != null">${(columnData.column.columnName)!}= ${r"#{"}query.domain.${(columnData.name)!}${r"}"}<#if columnData_has_next> AND </#if></if><!--${(columnData.column.columnComment)!}-->
                            </#if>
                        </#list>
                        </trim>
                        AND
                    </if>

                    <if test="query.compares != null">
                        <trim suffixOverrides="AND">
                            <foreach collection="query.compares" item="compare" separator="AND">
                                `${r"${"}compare.column${r"}"}`
                                <choose>
                                    <when test="compare.dataType == 'lt'">&lt;</when>
                                    <when test="compare.dataType == 'lte'">&lt;=</when>
                                    <when test="compare.dataType == 'gt'">&gt;</when>
                                    <when test="compare.dataType == 'gte'">&gt;=</when>
                                </choose>
                                '${r"${"}compare.value${r"}"}'
                            </foreach>
                        </trim>
                        AND
                    </if>

                    <if test="query.andLikes != null">
                        <trim suffixOverrides="AND">
                            <foreach collection="query.andLikes" item="like" separator="AND">
                                `${r"${"}like.column${r"}"}` like
                                <choose>
                                    <when test="like.leftLike and like.rightLike">concat('%','${r"${"}like.keyword${r"}"}','%')
                                    </when>
                                    <when test="like.leftLike">concat('%','${r"${"}like.keyword${r"}"}','')</when>
                                    <when test="like.rightLike">concat('','${r"${"}like.keyword${r"}"}','%')</when>
                                </choose>
                            </foreach>
                        </trim>
                        AND
                    </if>
                    <if test="query.orLikes != null">
                        <trim suffixOverrides="AND" prefix="(" suffix=")">
                            <foreach collection="query.orLikes" item="like" separator="OR">
                                `${r"${"}like.column${r"}"}` like
                                <choose>
                                    <when test="like.leftLike and like.rightLike">concat('%','${r"${"}like.keyword${r"}"}','%')
                                    </when>
                                    <when test="like.leftLike">concat('%','${r"${"}like.keyword${r"}"}','')</when>
                                    <when test="like.rightLike">concat('','${r"${"}like.keyword${r"}"}','%')</when>
                                </choose>
                            </foreach>
                        </trim>
                    </if>
                </trim>

                <if test="query.orders != null">
                    order by
                    <foreach collection="query.orders" item="order" separator=",">
                        `${r"${"}order.column${r"}"}` ${r"${"}order.dataType${r"}"}
                    </foreach>
                </if>

            </if>
        </where>
    </select>

</mapper>