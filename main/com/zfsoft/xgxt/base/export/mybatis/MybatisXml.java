/**
 * @部门:学工产品事业部
 * @日期：2013-11-27 上午10:42:47 
 */
package com.zfsoft.xgxt.base.export.mybatis;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: mybatis配置bean
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2013-11-27 上午10:42:47
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class MybatisXml {
	private String id;
	private String parameterType;
	private String resultType;
	private String sql;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id要设置的
	 *            id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the parameterType
	 */
	public String getParameterType() {
		return parameterType;
	}

	/**
	 * @param parameterType要设置的
	 *            parameterType
	 */
	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}

	/**
	 * @return the resultType
	 */
	public String getResultType() {
		return resultType;
	}

	/**
	 * @param resultType要设置的
	 *            resultType
	 */
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	/**
	 * @return the sql
	 */
	public String getSql() {
		return sql;
	}

	/**
	 * @param sql要设置的
	 *            sql
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}
}
