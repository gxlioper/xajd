/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-2 ����11:59:19 
 */  
package com.zfsoft.xgxt.base.export.dao.imp;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-12-2 ����11:59:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ExportDao {
	/*<?xml version="1.0" encoding="UTF-8" ?>
	<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

	<mapper namespace="com.zfsoft.dao.daointerface.IExportDao" >

		<!-- ��ѯ���˵�λ -->
	    <select id="getExportConfig" parameterType="com.zfsoft.dao.entities.ExportModel"
				resultType="com.zfsoft.dao.entities.ExportConfigModel">
				select * from zftal_gtgl_dczdpzb where dcclbh=#{dcclbh} and zgh=#{zgh}
		</select>
		
		<!-- �����û��������� -->
		<insert id="insertConfig" parameterType="list">
			insert into zftal_gtgl_dczdpzb(dcclbh,dcclmc,zd,zdmc,xssx,zgh,sfmrzd,zt)
			<foreach collection="list" item="item" index="index"
					separator=" from dual union select"  
	                open="select" close=" from dual">
				#{item.dcclbh},'',#{item.zd},#{item.zdmc},#{item.xssx},#{item.zgh},'',#{item.zt} 
			</foreach>
		</insert>
		
		<!-- ɾ���û��������� -->
		<delete id="deleteConfig" parameterType="com.zfsoft.dao.entities.ExportModel">
			delete from zftal_gtgl_dczdpzb where dcclbh=#{dcclbh} and zgh=#{zgh}
		</delete>
	</mapper>*/
}
