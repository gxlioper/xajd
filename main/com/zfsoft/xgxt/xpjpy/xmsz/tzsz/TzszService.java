/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-5 ����11:11:11
 */
package com.zfsoft.xgxt.xpjpy.xmsz.tzsz;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ��Ŀά��-��˵�����������
 * @���ߣ� ligl
 * @���ڣ�2013-8-5 ����11:11:11
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class TzszService extends
		SuperServiceImpl<TzszModel, TzszDao> {

	private TzszDao dao = new TzszDao();

	public TzszService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @����:�������
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼: 
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean shsz(String xmdm,String xmdms) throws Exception{
		return dao.runShsz(xmdm,xmdms);
	}
	
	/**
	 * 
	 * @����:����xmdm��ѯ���õļ�¼
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼: 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * XmwhJdszForm �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getByXmdm(String xmdm,String currXn, String currXq) throws Exception{
		return dao.getByXmdm(xmdm,currXn,currXq);		
	}
	
	/**
	 * 
	 * @����:������Ŀ���룬��ȡ�����õĿɵ�����Ŀ
	 * @���ߣ�ligl
	 * @���ڣ�2013-8-5 ����11:11:11
	 * @�޸ļ�¼: 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * XmwhJdszForm �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getKshxm(String xmdm) throws Exception{
		return dao.getKshxm(xmdm);
	}

	
	/**
	 * 
	 * @����: ��ѯ�ɵ�����������Ŀ�б� 
	 * @���ߣ�Penghui.Qu [���ţ�445]
	 * @���ڣ�2013-8-5 ����02:26:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String,String>> getKtzPjxmList(String xmdm){
		if (StringUtil.isNull(xmdm)){
			return null;
		}
		return dao.getKtzPjxmList(xmdm);
	}
	
	/**
	 * 
	 * @����:������Ŀ����ɾ����¼
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-12-23 ����05:23:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean runDeleteByXmdm(String xmdm) throws Exception {
		return dao.runDeleteByXmdm(xmdm);
	}

}
