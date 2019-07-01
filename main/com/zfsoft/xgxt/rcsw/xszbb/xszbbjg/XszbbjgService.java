/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-7 ����04:42:04 
 */
package com.zfsoft.xgxt.rcsw.xszbb.xszbbjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import xgxt.form.User;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.qjgl.qjsh.QjshForm;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ��֤����������ģ��
 * @�๦������: TODO(ѧ��֤������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-25 ����02:05:10 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class XszbbjgService extends SuperServiceImpl<XszbbjgForm, XszbbjgDao> {

	private XszbbjgDao dao = new XszbbjgDao();
	public static String _BCZSCID="-1";
	
	public XszbbjgService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @����:TODO(�ж�ѧ��֤�����������Ƿ��Ѿ����ڸ�ѧ��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-19 ����08:39:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public boolean isExistByXszbbsqjg(XszbbjgForm model, String type)
	throws Exception {
		boolean flag = false;
		if ("save".equalsIgnoreCase(type)) {
			String shzt = dao.checkExistForSave(model);
			if (!"0".equalsIgnoreCase(shzt)) {
				flag = true;
			}
		} 
		return flag;
	}
	
	/**
	 * 
	 * @����:TODO(����ѧ��֤������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-19 ����08:46:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveXszbbsqjg(XszbbjgForm model) throws Exception {
		boolean insertResult = super.runInsert(model);
		return insertResult;
	}	
	
	/**
	 * 
	 * @����:TODO(����ѧ��֤������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-19 ����09:33:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateXszbbsqjg(XszbbjgForm model) throws Exception {
		boolean updateResult = super.runUpdate(model);
		return updateResult;
	}
	
	/**
	 * 
	 * @����:TODO(ɾ��ѧ��֤������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-19 ����10:07:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] deleteXszbbjg(String[] ids) throws Exception{
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
				HashMap<String, String> hm=dao.getBbjg(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?bbjgDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//ȥ�������ය��
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 
	 * @����:TODO(ɾ��ѧ��֤������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-19 ����10:07:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	private int bbjgDelete(String[] ids) throws Exception {
		return runDelete(ids);
	}
	/**
	 * 
	 * @����:TODO(�鿴ѧ��֤������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-19 ����10:46:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xwjgId
	 * @return
	 * @throws Exception
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> viewOneXszbbjgList(String xszbbjgId) throws Exception {
		return dao.viewOneXszbbjgList(xszbbjgId);
	}


}
