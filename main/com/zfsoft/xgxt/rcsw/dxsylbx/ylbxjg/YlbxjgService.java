/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-7 ����04:42:04 
 */
package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

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
public class YlbxjgService extends SuperServiceImpl<YlbxjgForm, YlbxjgDao> {

	private YlbxjgDao dao = new YlbxjgDao();
	public static String _BCZSCID="-1";
	
	public YlbxjgService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @����:TODO(�ж�ҽ�Ʊ��ս�������Ƿ��Ѿ����ڸ�ѧ��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-19 ����08:39:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public boolean isExistByYlbxjg(YlbxjgForm model, String type)
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
	 * @����:TODO(����ҽ�Ʊ��ս��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-10 ����04:28:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveYlbxjg(YlbxjgForm model) throws Exception {
		boolean insertResult = super.runInsert(model);
		return insertResult;
	}	
	
	/**
	 * 
	 * @����:TODO(����ҽ�Ʊ��ս��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-10 ����04:28:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateYlbxjg(YlbxjgForm model) throws Exception {
		boolean updateResult = super.runUpdate(model);
		return updateResult;
	}
	
	/**
	 * 
	 * @����:TODO(ɾ��ҽ�Ʊ��ս��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-13 ����11:33:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] deleteYlbxjg(String[] ids) throws Exception{
		
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
				HashMap<String, String> hm=dao.getYljg(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		
		int i=delId.size()>0?ylbxjgDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//ȥ�������ය��
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 
	 * @����:TODO(ɾ��ҽ�Ʊ��ս��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-13 ����11:34:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	private int ylbxjgDelete(String[] ids) throws Exception {
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
	public Map<String, String> viewOneYlbxjgList(String xszbbjgId) throws Exception {
		return dao.viewOneYlbxjgList(xszbbjgId);
	}
	
	/**
	 * 
	 * @����:TODO(��ǰѧ������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-8 ����03:17:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public String getCurrentXqmc(YlbxjgForm model)throws Exception {
		String xqmc = dao.getCurrentXqmc(model);
		return xqmc;
	}
	

}
