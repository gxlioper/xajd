/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-31 ����08:27:26 
 */  
package com.zfsoft.xgxt.rcsw.xszbb.xszbblxwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.action.Base;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;



/**'
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ��֤�������͹���ģ��
 * @�๦������: TODO(ѧ��֤��������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-17 ����08:54:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XszbblxwhService extends SuperServiceImpl<XszbblxwhForm, XszbblxwhDao>  {

	private XszbblxwhDao dao = new XszbblxwhDao();
	public static String _BCZSCID="-1";
	
	public XszbblxwhService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @����:TODO(��ȡѧ��֤��������List)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����08:55:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>>  getBblxPageList(XszbblxwhForm model) throws Exception{
		return dao.getBblxPageList(model);
	}
	
	/**
	 * 
	 * @����:TODO(��������ŵ�ѧ��֤�������ʹ���)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����08:57:06
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String changeXszbblxdm() {
		String max = dao.getMaxXszbblxdm();
		if(Base.isNull(max)){
			return "001";
		}else{
			max = String.valueOf((Integer.parseInt(max)+1));
			String pre = "";
			for(int i = 0; i < 3-max.length();i ++){
				pre+="0";
			}
			return pre+max;
		}
	}
	
	/**
	 * 
	 * @����:TODO(���ӣ��޸�ѧ��֤��������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����08:57:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveBblxInfo(XszbblxwhForm model,String type) throws Exception{
		boolean result = false;
		if ("add".equals(type)) {
			String xszbblxdm = changeXszbblxdm();
			model.setXszbblxdm(xszbblxdm);
			return dao.addBblxInfo(model);
		} else if ("update".equals(type)) {
			return dao.updateBblxInfo(model);
		}
		return result;
	}
	/**
	 * 
	 * @����:TODO(�޸�ѧ��֤�������� ������ѯ)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����10:58:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param xszbblxdm
	 * @return
	 * @throws Exception
	 * XszbblxwhForm �������� 
	 * @throws
	 */
	public XszbblxwhForm getXszbblxwhForm(XszbblxwhForm t ,String xszbblxdm) throws Exception{
		return dao.getXszbblxwhForm(t,xszbblxdm);
	}
	

	
	/**
	 * 
	 * @����:TODO(�жϲ������������Ƿ��Ѿ����������� �� ������Ӧ��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-20 ����11:22:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param values
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String  checkXszbbdmForbbsq(String values)throws Exception{
    	String resultBblxmc="";
    	String[] rcswBblxmc=dao.checkXszbbdmForbbsq(values);
    	for(int i=0;i<rcswBblxmc.length;i++){
			if(i==rcswBblxmc.length-1){
				resultBblxmc+=rcswBblxmc[i];
			}else{
				resultBblxmc+=rcswBblxmc[i]+",";
			}
			
		}
		return resultBblxmc;
	}
	
	/**
	 * 
	 * @����:TODO(ɾ��ѧ��֤��������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����06:11:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] deleteXszbblxwhInfo(String[] ids) throws Exception{
		List<String> delId=new ArrayList<String>();//��ɾ����id����
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.isCanDel(str)){
				delId.add(str);//��¼ɾ��id
			}else{
				HashMap<String, String> hm=dao.getBbsq(str);
				noDel.append(hm.get("xszbblxmc"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?bbsqDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//ȥ�������ය��
		str=isHaveNoId?str:_BCZSCID;
		
		return new String[]{String.valueOf(i),str};
	}
	/**
	 * 
	 * @����:TODO(ɾ��ѧ��֤��������ά��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-20 ����02:04:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	private int bbsqDelete(String[] xszbblxdm) throws Exception {
		
		return runDelete(xszbblxdm);
	}

	
}
