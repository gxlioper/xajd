/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-11-23 ����08:40:48 
 */  
package com.zfsoft.xgxt.xszz.zzdy.zzbtff;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2015-11-23 ����08:40:48 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZzdyBtffService extends SuperServiceImpl<ZzdyBtffForm, ZzdyBtffDao> {
	private ZzdyBtffDao dao = new ZzdyBtffDao();
	
	public boolean isHave(ZzdyBtffForm model) {
		return dao.isHave(model);
	}

	/**
	 * 
	 * @����:���������޸�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-11-23 ����05:55:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean btff(ZzdyBtffForm model) throws Exception {
			return  dao.btff(model);
	}
	/**
	 * 
	 * @����:��ȡ�����·��б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-11-25 ����11:55:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getFfyfList() throws Exception {
		return dao.getFfyfList();
	}
	
	public List<HashMap<String, String>> getFfjlList(String xh ,String xmdm) throws Exception {
		return dao.getFfjlList(xh,xmdm);
	}

}
