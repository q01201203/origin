<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.origin.data.mybatis.dao.${model}Dao">
	<sql id="selectPart">
        SELECT
			<#list fieldList as column>
                <#if column_has_next>
        		    ${column.columnName},
                <#else>
                    ${column.columnName}
                </#if>
			</#list>
        FROM ${tableName}
        where 1=1
	</sql>
    <sql id="queryParams">
		<#list fieldList as field>
			<if test="${field.fieldName} != null">AND ${field.columnName}=&{${field.fieldName}}</if>
		</#list>
    </sql>
    <resultMap type="com.origin.data.mybatis.entity.${model}" id="${_model}Map">
    	<id property="id" column="id" />
    	<#list fieldList as column>
    	<result property="${column.fieldName}" column="${column.columnName}"/>
    	</#list>
    </resultMap>
    <select id="findByPK" parameterType="int" resultType="com.origin.data.mybatis.entity.${model}">
        <include refid="selectPart"/>
        AND id=&{0}
    </select>
    <select id="findFirst" parameterType="com.origin.data.mybatis.entity.${model}" resultType="com.origin.data.mybatis.entity.${model}">
        <include refid="selectPart"/>
        <include refid="queryParams"/>
    </select>
    <select id="find" parameterType="com.origin.data.mybatis.entity.${model}" resultType="com.origin.data.mybatis.entity.${model}">
        <include refid="selectPart"/>
        <include refid="queryParams"/>
    </select>
    <insert id="save" parameterType="com.origin.data.mybatis.entity.${model}">
        <selectKey resultType="java.lang.Integer" keyProperty="id" order="AFTER">
            SELECT LAST_INSERT_ID()
        </selectKey>
        INSERT INTO ${tableName} (
            <trim suffix="" suffixOverrides=",">
            <#list fieldList as field>
                <#if field.fieldName != "id">
                    <#if field_has_next>
                        <if test="${field.fieldName} != null">`${field.columnName}`,</if>
                    <#else >
                        <if test="${field.fieldName} != null ">`${field.columnName}`</if>
                    </#if>
                </#if>
            </#list>
            </trim>
		)
		VALUES (
            <trim suffix="" suffixOverrides=",">
			<#list fieldList as field  >
                <#if field.fieldName != "id">
                    <#if field_has_next>
                        <if test="${field.fieldName} != null">&{${field.fieldName}},</if>
                    <#else>
                        <if test="${field.fieldName} != null">&{${field.fieldName}}</if>
                    </#if>
                </#if>
			</#list>
            </trim>
		)
    </insert>
    <update id="update" parameterType="com.origin.data.mybatis.entity.${model}" >
        UPDATE ${tableName}  SET
         id=id
		<#list fieldList as field>
            <#if field.fieldName != "id">
                <if test="${field.fieldName} != null">,${field.columnName}=&{${field.fieldName}}</if>
            </#if>
		</#list>
        WHERE `id` = &{id}
    </update>
    <update id="updateBatch" parameterType="list" >
        <foreach collection="list" item="item"  separator=";" >
            update ${tableName}
            set id=id
            <#list fieldList as field>
                <#if field.fieldName != "id">
                    <if test="item.${field.fieldName} != null">,${field.columnName}=&{item.${field.fieldName}}</if>
                </#if>
            </#list>
                where id=&{item.id}
        </foreach>
    </update>
    <delete id="delete" parameterType="int">
        DELETE FROM ${tableName} WHERE id=&{id}
    </delete>
</mapper>