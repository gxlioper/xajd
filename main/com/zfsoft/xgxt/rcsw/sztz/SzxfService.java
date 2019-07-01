/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-10-29 ����09:48:42 
 */  
package com.zfsoft.xgxt.rcsw.sztz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


public class SzxfService extends SuperServiceImpl<SzxfModel, SzxfDao> {
	private static final String PASS = "�ϸ�";
	private static final String FAIL = "���ϸ�";

	
	
	/**
	 * 
	 * @����: ѧ�ֻ���
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2015-3-16 ����06:44:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getPageXfhzList(SzxfModel model, User user) throws Exception{
		
		return dao.getPageXfhzList(model, user);
	}
	
	
	public List<HashMap<String,String>> getAllXfhzList(SzxfModel model, User user) throws Exception{
		
		Pages pages = model.getPages();
		pages.setPageSize(Integer.MAX_VALUE);
		
		model.setPages(pages);
		
		return dao.getPageXfhzList(model, user);
	}
	
	/** 
	 * @����:��ȡ����ѧ��ͳ���б�(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-2 ����10:56:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPageGrxftjList(SzxfModel t, User user) throws Exception {
		return dao.getPageGrxftjList(t, user);
	}
	
	/** 
	 * @����:��������ѧ��ͳ���б����չ������Ի����ƣ�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-12-2 ����04:18:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getAllGrxftjList(SzxfModel t, User user) throws Exception{
		Pages pages = t.getPages();
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.setPages(pages);
		
		List<HashMap<String, String>> list = dao.getPageGrxftjList(t, user);
		for(HashMap<String, String> map : list){
			String zxf = map.get("zxf");    //�ܷ�
			String axf = map.get("azf");    //A��Ŀ����
			String bxf = map.get("bzf");    //B��Ŀ����
			String cxf = map.get("czf");    //C��Ŀ����
			String dxf = map.get("dzf");    //D��Ŀ����
			String exf = map.get("ezf");    //E��Ŀ����
			if(Double.parseDouble(zxf)< 8){  //����ܷ�С��8�����ϸ�
				map.put("sfhg", FAIL);
			}else{
				//�������Ŀ��������꣬���ϸ�
				if(Double.parseDouble(axf)<0.5 || Double.parseDouble(bxf)<0.5 || Double.parseDouble(cxf)<2 || Double.parseDouble(dxf)<0.5 || Double.parseDouble(exf)<0.5){
					map.put("sfhg", FAIL);
				}else{
					map.put("sfhg", PASS);
				}
			}
		}
		return list;
	}
}
