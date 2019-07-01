/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-18 ����02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmwh.shsz;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ������
 * @�๦������: ��Ŀά��-��˵�����������
 * @���ߣ� ligl
 * @ʱ�䣺 2013-4-18 ����02:42:37
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class XmwhShszService extends
		SuperServiceImpl<XmwhShszForm, XmwhShszDao> {

	private XmwhShszDao dao = new XmwhShszDao();

	public XmwhShszService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @����:�������
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-22 ����05:08:13
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
	 * @���ڣ�2013-4-24 ����10:14:13
	 * @�޸ļ�¼: 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * XmwhJdszForm �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getByXmdm(String xmdm) throws Exception{
		return dao.getByXmdm(xmdm);		
	}
	
	/**
	 * 
	 * @����:������Ŀ���룬��ȡ�����õĿɵ�����Ŀ
	 * @���ߣ�ligl
	 * @���ڣ�2013-4-24 ����10:14:13
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


}
