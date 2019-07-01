package com.zfsoft.xgxt.base.export.validator;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.export.exception.ErrorValidationException;
import com.zfsoft.xgxt.base.export.validator.error.ErrorBindingResultImpl;
import com.zfsoft.xgxt.base.export.validator.error.IErrorBindingResult;
import com.zfsoft.xgxt.base.export.validator.rules.IValidateRule;

/**
 * ��֤�����ࡣ ͨ��java������ƻ�ȡ�����е�ֵ����֤�� ʹ��ʾ����<br>
 * <li>˵������֤���Utils������֤����ڡ�<li>
 * 
 * @author Jiangdong.Yi
 * 
 */
public class ValidatorUtils {
	/** �����֤��Ϣ֮��ķָ��� */
	public static String VALIDATE_INFO_SPLIT_SYMBAL = ";";

	/**
	 * ʹ��ָ������֤�����ʵ����ָ������ֵ������֤��<br>
	 * <ul>
	 * <li>��������������Ϊ�գ�</li><br>
	 * <li>������ָ����������֤���е�ָ������</li><br>
	 * </ul>
	 * 
	 * @param entity
	 *            ָ����ʵ����Ϣ
	 * @param fieldName
	 *            ʵ���ж�Ӧ���������ơ�����ͬʱָ����������������һ����֤�� ���֮���á�,��������
	 * @param validateRules
	 *            ��֤�Ĺ�����Щ�����ʵ����framework�µ�ValidateRule�ӿ�
	 * @return �������֤������Ϣ����ͨ��IErrorBindingResult���ء�
	 */
	public static IErrorBindingResult validateEntity(Object entity,
			String fieldName, Object... validateRules) {
		if (entity == null) {
			throw new IllegalArgumentException(
					"argument for entity must not be null!");
		}
		if (StringUtils.isNull(fieldName)) {
			throw new IllegalArgumentException("argument fieldName[Object:"
					+ entity.getClass().getSimpleName() + "] not be null!");
		}
		if (validateRules == null || validateRules.length < 1) {
			throw new IllegalArgumentException(
					"argument for validateRules must not be null!");
		}
		IErrorBindingResult result = new ErrorBindingResultImpl(entity);
		String[] fields = fieldName.split(",");
		for (String field : fields) {
			for (Object rule : validateRules) {
				rule = validateAndGetObj(rule);
				IValidateRule r = (IValidateRule) rule;
				if (!r.validate(getEntityValue(entity, field))) {
					result.rejectValue(field, r.getValidateInfo());
				}
			}
		}
		return result;
	}

	/**
	 * ʹ��ָ������֤�����ʵ����ָ������ֵ������֤���������֤��Ϣ������֤��Ϣ�׳��쳣<br>
	 * <ul>
	 * <li>��������������Ϊ�գ�</li><br>
	 * <li>������ָ����������֤���е�ָ������</li><br>
	 * </ul>
	 * 
	 * @param entity
	 *            ָ����ʵ����Ϣ
	 * @param fieldName
	 *            ʵ���ж�Ӧ���������ơ�����ͬʱָ�����������
	 * 
	 *            ������һ����֤�����֮���á�,��������
	 * @param validateRules
	 *            ��֤�Ĺ�����Щ�����ʵ��ValidateRule�ӿ�
	 * @return �������֤������Ϣ,��ͨ��IErrorBindingResult���ء�
	 * @throws ErrorValidationException
	 *             ������֤��ͨ������Ϣʱ���׳����쳣��
	 */
	public static void validateEntityWithException(Object entity,
			String fieldName, Object... validateRules)
			throws ErrorValidationException {
		IErrorBindingResult result = validateEntity(entity, fieldName,
				validateRules);
		if (result.hasErrors()) {
			throw new ErrorValidationException(result);
		}
	}

	/**
	 * ��ָ������֤������֤������ֵ����������֤��Ϣֱ�ӷ���Ϊ�ַ�����<br>
	 * �����������ֱ֤�Ӹ��������ݣ�����Ҫ��javaBean��<br>
	 * 
	 * @param value
	 *            ָ����ֵ
	 * @param validateRules
	 *            ָ������֤����
	 * @return ������֤��Ϣ�����û�У��򷵻�""��
	 */
	public static String validateValue(Object value, Object... validateRules) {
		if (validateRules == null || validateRules.length < 1) {
			throw new IllegalArgumentException(
					"argument for validateRules must not be null!");
		}
		String result = "";// ��֤��Ϣ��
		for (Object rule : validateRules) {
			rule = validateAndGetObj(rule);
			IValidateRule r = (IValidateRule) rule;
			if (!r.validate(value)) {
				result += r.getValidateInfo() + VALIDATE_INFO_SPLIT_SYMBAL;
			}
		}
		return result.endsWith(ValidatorUtils.VALIDATE_INFO_SPLIT_SYMBAL) ? result
				.substring(0, result.length() - 1)
				: result;
	}

	/**
	 * ��ָ������֤������֤������ֵ����������֤��Ϣֱ�ӷ���Ϊ�ַ�����<br>
	 * �����������ֱ֤�Ӹ��������ݣ�����Ҫ��javaBean��<br>
	 * 
	 * @param value
	 *            ָ����ֵ
	 * @param parameters
	 *            ��֤���ݷ�Χ��֤�Ĳ�����������ͨ�����췽�����ݡ������鳤��Ϊ��Ӧ����֤���� ���췽����Ӧ���ȡ�����λ��Ϊ��Ӧ�������λ�á�
	 * @param validateRules
	 *            ָ������֤����
	 * @return ������֤��Ϣ�����û�У��򷵻�""��
	 */
	public static String validateValueByParameter(Object value,
			Object[] parameters, Object... validateRules) {
		if (parameters == null) {
			throw new IllegalArgumentException(
					"argument for parameters must not be null!");
		}
		if (validateRules == null || validateRules.length < 1) {
			throw new IllegalArgumentException(
					"argument for validateRules must not be null!");
		}
		String result = "";// ��֤��Ϣ��
		for (Object rule : validateRules) {
			rule = validateAndNewObj(rule, parameters);
			IValidateRule r = (IValidateRule) rule;
			if (!r.validate(value)) {
				result += r.getValidateInfo() + VALIDATE_INFO_SPLIT_SYMBAL;
			}
		}
		return result.endsWith(ValidatorUtils.VALIDATE_INFO_SPLIT_SYMBAL) ? result
				.substring(0, result.length() - 1)
				: result;
	}

	/**
	 * ��֤����֤�����Ƿ�Ϊ����ͬʱת��Ϊʵ���ࡣ
	 * 
	 * @param rule
	 *            �������֤����������
	 * @return ����ʵ���������֤�������
	 */
	private static Object validateAndGetObj(Object rule) {
		if (rule == null) {
			throw new IllegalArgumentException("rule not be null!");
		}
		try {
			// ����ö�����IValidateRule��ʵ�֣�����Ϊ��IValidateRuleʵ�����class��ʽ��������NotEmpty.class��ʽ��
			if (!(rule instanceof IValidateRule)) {
				rule = ((Class<?>) rule).newInstance();
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
		return rule;
	}

	/**
	 * ��֤����֤�����Ƿ�Ϊ����ͬʱת��Ϊʵ����(ͨ��new ��ȡ�¶���)��
	 * 
	 * @param rule
	 *            �������֤����������
	 * @param parameters
	 *            ��֤���ݷ�Χ��֤�Ĳ�����������ͨ�����췽�����ݡ������鳤��Ϊ��Ӧ����֤���� ���췽����Ӧ���ȡ�����λ��Ϊ��Ӧ�������λ�á�
	 * @return ����ʵ���������֤�������
	 */
	@SuppressWarnings("unchecked")
	private static Object validateAndNewObj(Object rule, Object[] parameters) {
		if (rule == null) {
			throw new IllegalArgumentException("rule not be null!");
		}
		if (parameters == null) {
			throw new IllegalArgumentException("parameters not be null!");
		}
		try {
			// �������
			Class[] classs = new Class[parameters.length];
			for (int i = 0; i < parameters.length; i++) {
				classs[i] = parameters[i].getClass();
			}
			// ����ö�����IValidateRule��ʵ�֣�����Ϊ��IValidateRuleʵ�����class��ʽ��������NotEmpty.class��ʽ��
			if (!(rule instanceof IValidateRule)) {
				rule = ((Class<?>) rule).getConstructor(classs).newInstance(
						parameters);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
		return rule;
	}

	/**
	 * ͨ��java�ķ�����ƻ�ȡָ���Ķ����ж�Ӧ���Ե�ֵ��֧�ֶ���
	 * 
	 * Ƕ��ֵ�Ļ�ȡ��<br>
	 * �������Ϊ�գ���ֱ���׳��쳣��
	 * 
	 * @param entity
	 *            ����
	 * @param fieldName
	 *            �����е�����
	 * @return ���ض�Ӧ��ֵ
	 */
	private static Object getEntityValue(Object entity, String fieldName) {
		if (entity == null) {
			throw new IllegalArgumentException(
					"argument for entity must not be null!");
		}
		if (StringUtils.isNull(fieldName)) {
			throw new IllegalArgumentException("argument fieldName[Object:"
					+ entity.getClass().getSimpleName() + "] not be null!");
		}
		try {
			String[] fields = fieldName.split("\\.");
			Object obj = entity;
			for (int i = 0; (fields != null) && i < fields.length - 1; i++) {
				String method = "get".concat(
						fields[i].substring(0, 1).toUpperCase()).concat(
						fields[i].substring(1));
				obj = obj.getClass().getMethod(method).invoke(obj);
			}
			if (fields != null && fields.length > 0) {
				fieldName = fields[fields.length - 1];
			}
			String method = "get".concat(
					fieldName.substring(0, 1).toUpperCase()).concat(
					fieldName.substring(1));
			return obj.getClass().getMethod(method).invoke(obj);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}
}
