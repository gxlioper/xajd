package com.zfsoft.xgxt.base.export.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.export.model.ImportModel;

/**
 * ��������
 * 
 * @author Jiangdong.Yi
 * 
 */
public interface IImportService {
	// ������ʱ�ļ�Ŀ¼
	public static final String IMPORT_TEMP_DIRPATH = "\\temp\\importTemp\\";
	// ����ģ���ļ�ǰ׺
	public static final String IMPORT_TEMPLATE = "template";
	// ��������ļ�ǰ׺
	public static final String IMPORT_ERROR = "error";

	// ������֤����Ĭ��ֵ "��"
	public static final String IMPORT_YZCS_DEFAULT = "��";

	// �����ļ�Ĭ�Ϻ�׺
	public static final String IMPORT_SUFFLX = "xls";

	// ��������Ψһ����֤
	public final String RULE_IMPORTUNIQUERULE = "ImportUniqueRule";

	// ���ڵ�����֤��Ͳ����
	public final String UNITESIGN = "##UNITE##";

	// �Ƿ�ϲ���֤: ��
	public final String SFHBYZ_1 = "1";
	// �Ƿ�ϲ���֤: ��
	public final String SFHBYZ_0 = "0";

	/**
	 * ��ȡ����� �б���ݵ���ģ�����
	 * 
	 * @param drmkdm
	 * @return
	 */
	public List<ImportModel> getImportTableListByDrmkdm(String drmkdm);

	/**
	 * ��ȡ��֤�ֶ��б� ���ݵ���ģ�����͵������
	 * 
	 * @param model
	 * @return
	 */
	public List<ImportModel> getValidateColumnList(String drmkdm, String drbm);

	/**
	 * ��ȡ�����ֶ��б� ���ݵ���ģ�����͵������
	 * 
	 * @param drmkdm
	 * @param drbm
	 * @return
	 */
	public List<ImportModel> getImportColumnList(String drmkdm, String drbm);

	/**
	 * ��ȡ����ģ�� �򵥵ĵ���ģ��
	 * 
	 * @param drmkdm
	 * @param drbm
	 * @return
	 */
	public File getImportTemplate(ImportModel importModel) throws Exception;

	/**
	 * ��ȡ�����������Excel
	 * 
	 * @param drmkdm
	 *            ����ģ�����
	 * @param drbm
	 *            �������
	 * @param errorList
	 *            �����б�
	 * @param user
	 *            �û���Ϣ
	 * @return
	 * @throws Exception
	 */
	public File getImportErrortData(String drmkdm, String drbm,
			List<String[]> errorList, User user) throws Exception;

	/**
	 * ��������
	 * 
	 * @param model
	 * @return
	 */
	public HashMap<String, Object> importData(ImportModel model, User user)
			throws Exception;

	/**
	 * ��ȡ��֤�����б�
	 * 
	 * @param parame
	 *            {tableName:"",columnName:"XXX,XXX"}
	 * @return List
	 */
	public List<HashMap<String, String>> getValidateValueList(
			HashMap<String, String> parame);

	/**
	 * ��ȡ��֤�����б�
	 * 
	 * @param parame
	 *            {tableName:"",columnName:"XXX,XXX"}
	 * @return HashMap
	 */
	public HashMap<String, String> getValidateValueMap(
			HashMap<String, String> parame);
}
