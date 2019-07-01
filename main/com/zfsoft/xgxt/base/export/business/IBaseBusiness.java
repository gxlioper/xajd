/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-4 ����03:11:43 
 */
package com.zfsoft.xgxt.base.export.business;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.export.model.ImportModel;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����ҵ����չ
 * @�๦������: ҵ����չ�ӿ�
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-12-4 ����03:11:43
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public interface IBaseBusiness {
	/**
	 * 
	 * @����: ҵ�����excel��������ҵ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-31 ����11:20:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param excelObject
	 * @return List<List<ImportModel>> ��������
	 */
	public List<List<ImportModel>> businessExcute(
			HashMap<String, Object> excelObject);

	/**
	 * 
	 * @����:ҵ����չ�������ݲ���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-9 ����10:15:38
	 * @param model ����������������ö�Ӧ����Ϣ��
	 * @param importColumnList ʵ�ʵ����ľ�������Ϣ
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� void ��������
	 */
	public List<ImportModel> businessInsertData(ImportModel model,
			List<ImportModel> importColumnList);
	
}
