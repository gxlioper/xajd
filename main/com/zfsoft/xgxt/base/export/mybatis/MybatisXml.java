/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-11-27 ����10:42:47 
 */
package com.zfsoft.xgxt.base.export.mybatis;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: mybatis����bean
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-11-27 ����10:42:47
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
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
	 * @param idҪ���õ�
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
	 * @param parameterTypeҪ���õ�
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
	 * @param resultTypeҪ���õ�
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
	 * @param sqlҪ���õ�
	 *            sql
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}
}
