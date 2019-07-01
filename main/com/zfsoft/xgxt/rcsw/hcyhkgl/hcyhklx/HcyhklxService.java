package com.zfsoft.xgxt.rcsw.hcyhkgl.hcyhklx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * ���Żݿ�����
 */
public class HcyhklxService extends SuperServiceImpl<HcyhklxForm, HcyhklxDao>  {

	private HcyhklxDao dao = new HcyhklxDao();
	public static String _BCZSCID="-1";
	
	public HcyhklxService() {
		super.setDao(dao);
	}
	
	/**
	 * ��������ŵĻ��Żݿ����ʹ���
	 */
	public String changeHcyhkgllxdm() {
		String max = dao.getMaxHcyhkgllxdm();
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
	 * ���ӣ��޸Ļ��Żݿ�����
	 */
	public boolean saveInfo(HcyhklxForm model,String type) throws Exception{
		boolean result = false;
		if ("add".equals(type)) {
			String hcyhkgllxdm = changeHcyhkgllxdm();
			model.setLxdm(hcyhkgllxdm);
			return dao.addInfo(model);
		} else if ("update".equals(type)) {
			return dao.updateInfo(model);
		}
		return result;
	}
	/**
	 * ���Żݿ������Ƿ����
	 */
	public boolean isExist(HcyhklxForm model, String type)
			throws Exception {
		return dao.isExist(model, type);
	}
	/**
	 * �޸Ļ��Żݿ����� ������ѯ
	 */
	public HcyhklxForm getHcyhklxForm(HcyhklxForm t) throws Exception{
		return dao.getHcyhklxForm(t);
	}
	
	/**
	 * ɾ��ѧ��֤��������
	 */
	public String[] deleteHcyhklxInfo(String[] ids) throws Exception{
		List<String> delId=new ArrayList<String>();//��ɾ����id����
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		HcyhklxForm t = new HcyhklxForm();
		for(String str : ids){
			if(dao.isCanDel(str)){
				delId.add(str);//��¼ɾ��id
			}else{
				t.setLxdm(str);
				HcyhklxForm temp = dao.getHcyhklxForm(t);
				noDel.append(temp.getLxmc());
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?runDelete(delId.toArray(new String[]{})):0;
		String str = new StringBuffer(noDel.reverse().toString().replaceFirst(",", "")).reverse().toString();
		//ȥ�������ය��
		str=isHaveNoId?str:_BCZSCID;
		
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * ��ȡ���л��Żݿ�����
	 */
	public List<HashMap<String, String>> getHcyhklxList()
		throws Exception {
		return dao.getHcyhklxList();
	}
	
}
