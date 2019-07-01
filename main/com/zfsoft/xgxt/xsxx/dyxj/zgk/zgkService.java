/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-2 ����05:37:40 
 */  
package com.zfsoft.xgxt.xsxx.dyxj.zgk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xsxx.dyxj.cssz.CsszModel;
import com.zfsoft.xgxt.xsxx.dyxj.cssz.CsszService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-12-2 ����05:37:40 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class zgkService extends SuperServiceImpl<zgkForm, zgkDao> {
	/**
	 * 
	 * @����: �����Ƿ������Ŀ��ʹ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-5 ����10:35:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotUsed(String[] ids){
		return dao.checkIsNotUsed(ids);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:��������ѧ�����ʸ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-5 ����02:49:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveAddXsIntoZgk(String[] xhs) throws Exception{
		CsszModel csszModel = new CsszService().getModel();
		List<String[]> paraList = new ArrayList<String[]>();
		for (int i = 0; i < xhs.length; i++) {
			paraList.add(new String[]{csszModel.getSqxn(),csszModel.getSqxq() ,xhs[i]});
		}
		return dao.saveBatchXs(paraList);
	}
	
	/**
	 * 
	 * @����: ���ʸ����ɸѡѧ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-5 ����05:13:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getZgkStuList(zgkForm t, User user)
	throws Exception {
		return dao.getZgkStuList(t, user);
	}
	
	/**
	 * 
	 * @����: ��֤ѧ�ţ�ѧ�꣬ѧ���Ƿ�����ʸ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-6 ����09:24:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsInZgk(String xh,String xn,String xq){
		return dao.checkIsInZgk(xh, xn, xq);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:��������ѧ�����ʸ��
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-12-5 ����02:49:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveAddXsIntoZgkBytj(List<HashMap<String, String>> resultList) throws Exception{
		CsszModel csszModel = new CsszService().getModel();
		List<String[]> paraList = new ArrayList<String[]>();
		for (int i = 0; i < resultList.size(); i++) {
			paraList.add(new String[]{csszModel.getSqxn(),csszModel.getSqxq() ,resultList.get(i).get("xh")});
		}
		return dao.saveBatchXs(paraList);
	}

}
