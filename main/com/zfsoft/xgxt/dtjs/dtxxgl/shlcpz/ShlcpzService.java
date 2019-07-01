/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:06:43 
 */
package com.zfsoft.xgxt.dtjs.dtxxgl.shlcpz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������Ϣ����ģ��
 * @�๦������:��ٹ���service
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-9-9 ����12:06:43
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ShlcpzService extends SuperServiceImpl<ShlcpzForm, ShlcpzDao> {
	/**
	 * �����ڲ���ɾ������
	 */
	public static String _BCZSCID="-1";
	/**
	 * �Ƿ�����뿪�� Ĭ��ֵ ��������
	 */
	public static String _SFKSQ_BKSQ="0";
	ShlcpzDao dao = new ShlcpzDao();

	public ShlcpzService() {
		this.setDao(dao);
	}
	public boolean save(ShlcpzForm sf) throws Exception {
		return update(sf);
	}

	public boolean update(ShlcpzForm sf) throws Exception {
		return this.runUpdate(sf);
	}
	/**
	 * 
	 * @����:����ɾ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-23 ����11:33:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 */
	
	public String[] delete(String[] ids) throws Exception{
		List<String> delId=new ArrayList<String>();
		StringBuffer noDel=new StringBuffer();
		boolean isHaveNoId=false;
		if(null==ids||ids.length<=0){
			return null;
		}
		int i=0;
		for(String str:ids){
				delId.add(str);//��¼ɾ��id
				//ȥ����Ӧ��������
				ShlcpzForm zf=getModel(str);
				zf.setSplc("");
				zf.setKsqkg(_SFKSQ_BKSQ);
				zf.setKsqkssj("");
				zf.setKsqjssj("");
				if(runUpdate(zf)){i++;}
		}
		String str=noDel.toString();
		//ȥ�������ය��
		str=isHaveNoId?str.substring(0,str.length()-1):_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	/**
	 * 
	 * @����:��ÿ������ӽ׶δ���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-22 ����05:12:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getJdList() throws Exception {
		return dao.getJdList();
	}
	/**
	 * 
	 * @����:��ÿ����޸ĵĽ׶δ���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-23 ����09:30:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jddm ��ǰ�׶δ���
	 * @param jdmc ��ǰ�׶�����
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 */
	public List<HashMap<String, String>> getJdList(String jddm,String jdmc) throws Exception {
		List<HashMap<String, String>> updateList=dao.getJdList();
		HashMap<String, String> hm=new HashMap<String, String>();
		hm.put("jddm", jddm);
		hm.put("jdmc", jdmc);
		updateList.add(hm);
		return updateList;
	}
	/**
	 * 
	 * @����:�Ƿ��ж�Ӧ��������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-10-28 ����02:29:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jddm
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 */
	public boolean isHaveSPlc(String jddm) throws Exception{
		ShlcpzForm sf=getModel(jddm);
		if(null!=sf&&StringUtils.isNotNull(sf.getSplc())){
			return true;
		}
		return false;
	}
}
