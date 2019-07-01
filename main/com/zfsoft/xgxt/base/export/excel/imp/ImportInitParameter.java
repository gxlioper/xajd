package com.zfsoft.xgxt.base.export.excel.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.export.service.IImportService;
import com.zfsoft.xgxt.base.export.service.imp.ImportServiceImpl;

/**
 * ���빦�ܳ�ʼ������ <li>����ֻΪ���빦���ṩ���������ʼ������<li>
 * 
 * @author JiangDong.Yi
 * 
 */
public class ImportInitParameter {
	// ��������Դ
	private Map<String, Object> importDataSource = null;
	// �����ָ��
	private final String IMPORT_INFO_SPLIT_SYMBAL = "-";

	// ��ʼ���ַ�������
	private final String RULE_STRINGLENGTHRULE = "StringLengthRule";
	// ��������Ψһ����֤
	private final String RULE_IMPORTUNIQUERULE = "ImportUniqueRule";
	// �����֤
	private final String RULE_IMPORTFOREIGNKEYRULE = "ImportForeignKeyRule";

	public ImportInitParameter(Map<String, Object> importDataSource) {
		this.importDataSource = importDataSource;
	}

	/**
	 * ��ȡ��ʼ����������
	 * 
	 * @param className
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public Object[] getInitParameterArray(String className, String parame) {
		if (StringUtils.isNull(className)) {
			return null;
		}
		// �жϳ�ʼ���� ���Ż�
		if (RULE_STRINGLENGTHRULE.equals(className)) {
			return getStringLengthRule(parame);
		} else if (RULE_IMPORTUNIQUERULE.equals(className)) {
			return getImportUniqueRule(parame);
		} else if (RULE_IMPORTFOREIGNKEYRULE.equals(className)) {
			return getImportForeignKeyRule(parame);
		}
		return null;
	}

	/**
	 * ��֤���ݳ��� ��ʼ������
	 * 
	 * @param str
	 * @return
	 */
	private Integer[] getStringLengthRule(String parame) {
		if (StringUtils.isNull(parame)) {
			return null;
		}
		String[] parames = parame.split(IMPORT_INFO_SPLIT_SYMBAL);
		Integer[] ints = new Integer[parames.length];
		for (int i = 0; i < parames.length; i++) {
			ints[i] = Integer.valueOf(parames[i]);
		}
		return ints;
	}

	/**
	 * ������֤����Ψһ�Գ�ʼ������ <li>��֧�ֵ�������Ψһ����֤</li>
	 * 
	 * @param parame
	 *            Ψһ��Լ������������ͼ��_Ψһ����Ӧ�ֶ���_�������ݵ�ӳ���ֶ�����
	 * @return
	 */
	private Object[] getImportUniqueRule(String parame) {
		if (StringUtils.isNull(parame)) {
			return null;
		}
		IImportService importService=new ImportServiceImpl();
	/*	IImportService importService = (IImportService) ServiceFactory
				.getService("importService");*/
		// ȡ������
		String[] parames = parame.split(IMPORT_INFO_SPLIT_SYMBAL);
		// �˴�д����������ΪΨһ����ֻ֤֧�� ����Ψһ����֤�������Ƕ���Ψһ��
		if (parames.length != 2) {
			return null;
		}
		Object[] objs = new Object[parames.length];

		// ������֤��ֵ�����ݱ���������
		HashMap<String, String> vParame = new HashMap<String, String>();
		vParame.put("tableName", parames[0]);
		vParame.put("columnName", parames[1]);

		// ��ȡ����֤������
		Map<String, Object> ds = importDataSource;
		HashMap<String, String> dataSource = formatImportUniqueRuleParame((String[]) ds
				.get(parame));

		// ����֤��ֵת���� hashMap��ʽ��������֤
		HashMap<String, String> vData = importService
				.getValidateValueMap(vParame);
		// ���ò���
		objs[0] = dataSource;
		objs[1] = vData;
		return objs;
	}

	/**
	 * ������֤���� �����ʼ������
	 * 
	 * @param parame
	 * @return
	 */
	private Object[] getImportForeignKeyRule(String parame) {
		if (StringUtils.isNull(parame)) {
			return null;
		}
		// �˴�д������Ϊ��֤������֤����ֻ�ṩһ����������֤
		Object[] objs = new Object[1];
		IImportService importService=new ImportServiceImpl();
/*		IImportService importService = (IImportService) ServiceFactory
				.getService("importService");*/
		// ȡ������
		String[] parames = parame.split(IMPORT_INFO_SPLIT_SYMBAL);
		if (parames.length != 3) {
			return null;
		}
		// ������֤��ֵ�����ݱ���������
		HashMap<String, String> vParame = new HashMap<String, String>();
		String columnName = "";
		String tableName = "";
		for (int i = 0; i < parames.length; i++) {
			if (i == 0) {
				tableName = parames[i];
			} else {
				if (!StringUtils.isNull(columnName)) {
					columnName = columnName + "," + parames[i];
				} else {
					columnName = parames[i];
				}
			}

		}
		vParame.put("tableName", tableName);
		vParame.put("columnName", columnName);

		// ����֤��ֵת���� hashMap��ʽ��������֤
		List<HashMap<String, String>> vDataList = importService
				.getValidateValueList(vParame);
		// ���ò���
		objs[0] = formatForeignKeyRuleParame(vDataList, columnName.split(","));

		return objs;
	}

	/*********************************** �������� **************************************/
	/**
	 * ��ʽ������Ψһ�������
	 * 
	 * @param str
	 * @return
	 */
	private HashMap<String, String> formatImportUniqueRuleParame(String[] str) {
		if (str == null) {
			return null;
		}
		HashMap<String, String> hashMap = new HashMap<String, String>();
		HashMap<String, String> hashMapTemp = new HashMap<String, String>();
		// ��֤�ظ������ݼ���hashMap��.
		for (int i = 0; i < str.length; i++) {
			if (hashMapTemp.containsKey(str[i])) {
				hashMap.put(str[i], str[i]);
			}
			hashMapTemp.put(str[i], str[i]);
		}
		return hashMap;
	}

	/**
	 * ��ʽ���⽨�������
	 * 
	 * @param vDataList
	 * @param parame
	 * @return
	 */
	private HashMap<String, String> formatForeignKeyRuleParame(
			List<HashMap<String, String>> vDataList, String[] parame) {
		if (vDataList == null || parame == null) {
			return null;
		}
		HashMap<String, String> parameData = null;
		// ��list��HashMap��ת���� hashmap��hashmap
		parameData = new HashMap<String, String>();
		for (int i = 0; i < vDataList.size(); i++) {
			if (parame.length == 1) {
				parameData.put(vDataList.get(i).get(parame[0].toLowerCase()),
						vDataList.get(i).get(parame[0].toLowerCase()));
			} else if (parame.length == 2) {
				parameData.put(vDataList.get(i).get(parame[0].toLowerCase()),
						vDataList.get(i).get(parame[1].toLowerCase()));
			}

		}

		return parameData;
	}

}
