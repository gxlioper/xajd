/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-25 ����02:36:17 
 */  
package com.zfsoft.xgxt.xpjpy.zcdj;

import java.util.HashMap;
import java.util.List;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ���ݹ�ҵ���Ի� �����۲�ȼ����� 
 * @���ߣ� xucy[����:754]
 * @ʱ�䣺 2013-9-25 ����02:36:17 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZcdjService {
	
	private ZcdjDao dao = new ZcdjDao();
	
	/**
	 * 
	 * @����:TODO��ȡ�۲�ȼ�����ֵ�б�
	 * @���ߣ�xucy[���ţ�754]
	 * @���ڣ�2013-9-25 ����05:02:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZcdjList() {
		List<HashMap<String, String>> list = dao.getZcdjList();
		return list;
	}
	
	//��ȡ���е��꼶
	public List<HashMap<String, String>> getViewNjList(){
	    return dao.getViewNjList();
	}
	
}
