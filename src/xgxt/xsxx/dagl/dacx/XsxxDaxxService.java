/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-22 ����11:50:09 
 */  
package xgxt.xsxx.dagl.dacx;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯҳ��
 * @�๦������:  
 * @���ߣ� ��־��[����:1060]
 * @ʱ�䣺 2014-4-22 ����11:50:09 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsxxDaxxService extends SuperServiceImpl<XsxxDaxxForm, XsxxDaxxDao> {

	public XsxxDaxxService() {
		super.setDao(new XsxxDaxxDao());
	}
	
	/**
	 * ��ѯ����һ��������Ϣ
	 * @param xm,sfzh
	 * @return
	 */
	public HashMap<String,String> getXsxxDaxx(String xm, String sfzh){
		return dao.getXsxxDaxx(xm, sfzh);
	}
}
