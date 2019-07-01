/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-21 ����05:00:41 
 */  
package com.zfsoft.xgxt.jjgl.cssz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.OptionUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-8-21 ����05:00:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszService  extends SuperServiceImpl<CsszForm, CsszDao> {

	/**
	 * 
	 * @����: ��ȡ����������Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-21 ����05:06:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String , String> getConfigMap(){
		Map<String , String> config = dao.getConfigMap();
		return config == null ? new HashMap<String, String>() : config;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:�����������
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-25 ����10:34:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveCssz(CsszForm model) throws Exception{
		//��ɾ��
		//�����
		dao.runDelete(null);
		return dao.runInsert(model);
	}
	
	/**
	 * ��ȡ�����ʸ��б�
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-22 ����01:50:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getFbzgList(){
		return dao.getFbzgList();
	}
	
	/**
	 * 
	 * @����:��ȡ�����б�
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-22 ����01:23:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String , String>> getKgOptions(){
		/**
		 *  �̶�ѡ�����
		 */
		OptionUtil optionUtil = new OptionUtil();
		
		List<HashMap<String , String>> kgList = optionUtil.getOptions(OptionUtil.ONOFF);
		
		return kgList;
	}
	
}
