package com.zfsoft.xgxt.base.export.validator.error;

import xgxt.utils.String.StringUtils;

/**
 * ���Դ�����Ϣʵ�塣�����Զ�Ӧ�Ĵ�����Ϣ��
 * 
 * @author Jiangdong.Yi
 * 
 */
public class FieldError {
	private Object entity;
	private String field;
	private String errMessage;
	private String defaultMessage = "���ǺϷ������ݣ�";

	/**
	 * ���캯�������еĲ���ֻ��errMessage����Ϊ�գ���Ϊ����Ĭ��ֵ����������������Ϊ�գ�
	 * 
	 * @param entity
	 *            ��֤��ʵ���ࡣ
	 * @param field
	 *            ��Ӧ�����ԡ�����ֻ����һ����
	 * @param errMessage
	 *            ָ������֤��Ϣ��
	 */
	public FieldError(Object entity, String field, String errMessage) {
		if (entity == null) {
			throw new IllegalArgumentException(
					"argument for entity must not be null!");
		}
		if (StringUtils.isNull(field)) {
			throw new IllegalArgumentException("argument field[Object:"
					+ entity.getClass

					().getSimpleName() + "] not be null!");
		}
		this.entity = entity;
		this.field = field;
		this.errMessage = errMessage;
	}

	/**
	 * ��ȡ��Ӧ��ʵ�����
	 * 
	 * @return ���ض�Ӧ��ʵ�����
	 */
	public Object getEntity() {
		return entity;
	}

	/**
	 * ��class��getName()������ȡʵ������ơ�
	 * 
	 * @return ����ʵ������ơ�
	 */
	public String getObjectName() {
		return entity.getClass().getName();
	}

	public void setEntity(Object entity) {
		this.entity = entity;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	/**
	 * ��ȡ��֤�ı�����Ϣ������û�ָ���ı�����ϢΪ�գ��򷵻�Ĭ�ϵı�����Ϣ��
	 * 
	 * @return ������֤�ı�����Ϣ��
	 */
	public String getErrMessage() {
		return StringUtils.isNull(errMessage) ? defaultMessage : errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	public void setDefaultMessage(String defaultMessage) {
		this.defaultMessage = defaultMessage;
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		FieldError e = (FieldError) o;
		return getField().equals(e.getField())
				&& getErrMessage().equals(e.getErrMessage());
	}
}
