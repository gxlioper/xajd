/**
 * @部门:学工产品事业部
 * @日期：2013-12-2 上午11:59:19 
 */  
package com.zfsoft.xgxt.base.export.dao.imp;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2013-12-2 上午11:59:19 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ExportDao {
	/*<?xml version="1.0" encoding="UTF-8" ?>
	<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

	<mapper namespace="com.zfsoft.dao.daointerface.IExportDao" >

		<!-- 查询用人单位 -->
	    <select id="getExportConfig" parameterType="com.zfsoft.dao.entities.ExportModel"
				resultType="com.zfsoft.dao.entities.ExportConfigModel">
				select * from zftal_gtgl_dczdpzb where dcclbh=#{dcclbh} and zgh=#{zgh}
		</select>
		
		<!-- 插入用户导出配置 -->
		<insert id="insertConfig" parameterType="list">
			insert into zftal_gtgl_dczdpzb(dcclbh,dcclmc,zd,zdmc,xssx,zgh,sfmrzd,zt)
			<foreach collection="list" item="item" index="index"
					separator=" from dual union select"  
	                open="select" close=" from dual">
				#{item.dcclbh},'',#{item.zd},#{item.zdmc},#{item.xssx},#{item.zgh},'',#{item.zt} 
			</foreach>
		</insert>
		
		<!-- 删除用户导出配置 -->
		<delete id="deleteConfig" parameterType="com.zfsoft.dao.entities.ExportModel">
			delete from zftal_gtgl_dczdpzb where dcclbh=#{dcclbh} and zgh=#{zgh}
		</delete>
	</mapper>*/
}
