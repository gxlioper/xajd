/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-7 ����04:42:04 
 */
package com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �𳵳˳�����������ģ��
 * @�๦������: TODO(�𳵳˳�������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2013-12-26 ����09:34:43 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class HcccqjjgService extends SuperServiceImpl<HcccqjjgForm, HcccqjjgDao> {

	private HcccqjjgDao dao = new HcccqjjgDao();
	public static String _BCZSCID="-1";
	
	public HcccqjjgService() {
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
	public boolean isExistByHcccqjjg(HcccqjjgForm model, String type)
	throws Exception {
		boolean flag = false;
		if ("save".equalsIgnoreCase(type)) {
			String num = dao.checkExistForSave(model);
			if (!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}else{
			String num = dao.checkExistForUpdate(model);
			if (!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
			
		}
		return flag;
	}
	
	/**
	 * ���ͨ������ɾ��������
	 */
	public boolean deleteExist(HcccqjjgForm model) throws Exception {
		return dao.deleteExist(model);
	}
	/**
	 * 
	 * @����:TODO(����𳵳˳�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-19 ����08:46:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveHcccqjjg(HcccqjjgForm model) throws Exception {
		boolean insertResult = super.runInsert(model);
		return insertResult;
	}	
	
	/**
	 * 
	 * @����:TODO(�޸Ļ𳵳˳�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-25 ����10:44:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean updateHcccqjjg(HcccqjjgForm model) throws Exception {
		boolean updateResult = super.runUpdate(model);
		return updateResult;
	}
	
	/**
	 * 
	 * @����:TODO(ɾ���𳵳˳�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-19 ����10:07:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] deleteHcccqjjg(String[] ids) throws Exception{
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
				HashMap<String, String> hm=dao.getHcccqjjg(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?hcccqjjgDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//ȥ�������ය��
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 
	 * @����:TODO(ɾ���𳵳˳�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-19 ����10:07:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	private int hcccqjjgDelete(String[] ids) throws Exception {
		return runDelete(ids);
	}
	

	/**
	 * 
	 * @����:TODO(����ѧ�Ų鿴�𳵳˳�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-25 ����01:32:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * ArrayList<String[]> �������� 
	 * @throws
	 */
	public ArrayList<String[]>  getMoreHcccqjjgList(HcccqjjgForm model)throws Exception {
		return dao.getMoreHcccqjjgList(model);
	}
	

}
