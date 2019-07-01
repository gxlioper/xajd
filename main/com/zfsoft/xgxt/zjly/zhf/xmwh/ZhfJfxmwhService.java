/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-27 ����05:08:33 
 */  
package com.zfsoft.xgxt.zjly.zhf.xmwh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ۺϷֹ���ģ��
 * @�๦������: �Ʒ���Ŀ(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-6-27 ����05:08:33 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZhfJfxmwhService extends SuperServiceImpl<ZhfJfxmwhForm, ZhfJfxmwhDao>{
	private ZhfJfxmwhDao dao = new ZhfJfxmwhDao();
	
	/** 
	 * @����:��֤�Ƿ����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-27 ����05:20:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isExist(ZhfJfxmwhForm t){
		return dao.count(t)>0;
	}
	
	/**
	 * @throws Exception  
	 * @����:�Ʒ���Ŀ��Ȩ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-28 ����03:42:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bmdms
	 * @param jfxms
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean jfxmSq(String[]bmdms,String[]jfxms) throws Exception{
		boolean result = dao.deljfxmSq(bmdms, jfxms);//��ɾ����Ȩ
		result = dao.jfxmSq(bmdms, jfxms);//�ٲ�����Ȩ
		return result;
	}
	/**
	 * @throws Exception  
	 * @����:�Ʒ���Ŀȡ����Ȩ(������һ�仰�����������������)
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-1-16 ����02:29:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bmdms
	 * @param jfxms
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean jfxmQx(String[]bmdms,String[]jfxms) throws Exception{
		boolean result = dao.deljfxmSq(bmdms, jfxms);//��ɾ����Ȩ
		return result;
	}
	
	/** 
	 * @����:��ȡ�����б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-28 ����04:07:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cxzd
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBmList(String cxzd){
		List<HashMap<String, String>> bmList = dao.getBmList(cxzd);
		return bmList;
	}
	
	/** 
	 * @����:��ȡ�����б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-29 ����09:23:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPageListForSq(ZhfJfxmwhForm t) throws Exception{
		return dao.getPageListForSq(t);
	}
	
	/** 
	 * @����:�������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-29 ����09:26:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jfxmmcs
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean jdsz(String[] jfxmmcs) throws Exception{
		List<HashMap<String, String>> list = dao.getJfxmList(jfxmmcs);
		if(null != list && list.size()>1){
			return dao.jdsz(list);
		}else{
			return false;
		}
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�CP[���ţ�1352]
	 * @���ڣ�2017-1-16 ����02:58:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getnewjfxmdm() {
		return dao.getnewjfxmdm();
	}
}
