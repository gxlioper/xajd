/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-14 ����02:34:09 
 */  
package com.zfsoft.xgxt.szdw.bjxwjl.bjxwjlwh;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xsgzgl.szdw.general.dwwh.DwwhService;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-5-14 ����02:34:09 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BjxwjlwhService extends SuperServiceImpl<BjxwjlwhForm, BjxwjlwhDao> {

	/**
	 * ����Ա��Ϣ
	 */
	private static final DwwhService dwwhService = new DwwhService();
	
	/**
	 * 
	 * @����:��ȡ����Ա��Ϣ
	 */
	public HashMap<String, String> getFdyxx(String zgh){
		
		 HashMap<String, String> data = dwwhService.ckJzgxx(zgh);
		
		 data.put("xn", Base.currXn);
		 data.put("xq", Base.currXq);
		 data.put("xqmc", DAO.getInstance().getXqmcForXqdm(Base.currXq));
		 
		 return data;
	}
	
	/**
	 * 
	 * @����:��ȡ����Ա����༶��Ϣ
	 */
	public List<HashMap<String , String>> getFdyBjxxList(String zgh){
		return dao.getFdyBjxxList(zgh);
	}
	
	/**
	 * 
	 * @����:��ȡ�����Ϣ
	 */
	public List<HashMap<String , String>> getLbList(){
		return dao.getLbList();
	}
	
	//����༶��¼
	public boolean saveBjjl(String xn , String xqdm , String zgh , List records) {
		
		return dao.saveBjjl(xn, xqdm, zgh, records);
	}
	
	/**
	 ��ȡ��¼��Ϣ guid
	 */
	public HashMap<String , String> getModelMap(String guid) throws Exception{
		return dao.getModelMap(guid);
	}
	
	public BjxwjlwhService(){
		setDao(new BjxwjlwhDao());
	}
	
}
