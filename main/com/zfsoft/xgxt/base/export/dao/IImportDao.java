package com.zfsoft.xgxt.base.export.dao;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.export.model.ImportModel;

/**
 * ����dao
 * 
 * @author Jiangdong.Yi
 * 
 */
public interface IImportDao extends BaseDao<ImportModel> {

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
	public List<ImportModel> getValidateColumnListByDrmkdmAndDrbm(
			ImportModel model);

	/**
	 * ��ѯ��ȡ����֤�б�
	 * 
	 * @param model
	 * @return
	 */
	public List<ImportModel> getColumnValidateListByDrmkdmAndDrbm(
			ImportModel model);

	/**
	 * ��ȡ���б�ϲ���֤����
	 * 
	 * @param model
	 * @return
	 * 
	 *         public List<ImportModel> getColumnListByDrmkdmAndDrbm(ImportModel
	 *         model);
	 */

	/**
	 * ��������ҵ������
	 * 
	 * @param hashMap
	 * @return
	 */
	public int batchInsertData(HashMap<String, Object> hashMap);

	/**
	 * ��ȡ��֤�����б�
	 * 
	 * @param parame
	 * @return
	 */
	public List<HashMap<String, String>> getValidateValueList(
			HashMap<String, String> parame);

	/**
	 * ��ȡ��֤����Map
	 * 
	 * @param parame
	 * @return
	 */
	public List<HashMap<String, String>> getValidateValueMap(
			HashMap<String, String> parame);

	/**
	 * ��ȡ�����ֶ��б� ���ݵ���ģ�����͵������
	 * 
	 * @param model
	 * @return
	 */
	public List<ImportModel> getImportColumnListByDrmkdmAndDrbm(
			ImportModel model);
	
	

	/**
	 * ��ȡ���ֶμ��ֶγ��ȵ�����
	 * 
	 * @param parame
	 * @return
	 */
	public List<HashMap<String, String>> getTableColumns(String tableName);
	
	/**
	 * ���û����롾���Ի���
	 */
	public boolean batchInsertData_yhzgl(HashMap<String, Object> hashMap);
}
