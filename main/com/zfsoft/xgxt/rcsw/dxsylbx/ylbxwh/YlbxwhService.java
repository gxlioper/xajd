/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-31 ����08:27:26 
 */  
package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxwh;

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

public class YlbxwhService extends SuperServiceImpl<YlbxwhForm, YlbxwhDao>  {

	private YlbxwhDao dao = new YlbxwhDao();
	public static String _BCZSCID="-1";
	
	public YlbxwhService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @����:TODO(����ȫ�����Ա����б�)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-6 ����02:08:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>>  getCzqebzlxPageList(YlbxwhForm model) throws Exception{
		return dao.getCzqebzlxPageList(model);
	}
	
	/**
	 * 
	 * @����:TODO(�α�״���б�)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-6 ����02:08:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>>  getCbzklxPageList(YlbxwhForm model) throws Exception{
		return dao.getCbzklxPageList(model);
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
	public String changeCzqebzlxdm() {
		String max = dao.getMaxCzqelxdm();
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
	 * @����:TODO(�������ȫ�����Ա���)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-6 ����04:13:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveCzqebzlxInfo(YlbxwhForm model,String type) throws Exception{
		boolean result = false;
		if ("add".equals(type)) {
			String czqebzdm = changeCzqebzlxdm();
			model.setCzqebzdm(czqebzdm);
			return dao.addCzqebzlxInfo(model);
		} else if ("update".equals(type)) {
			return dao.updateCzqebzlxInfo(model);
		}
		return result;
	}
	
	/**
	 * 
	 * @����:TODO(��ȡ����ȫ�����Ա���)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-6 ����04:12:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param czqebzdm
	 * @return
	 * @throws Exception
	 * YlbxwhForm �������� 
	 * @throws
	 */
	public YlbxwhForm getCzqebzlxForm(YlbxwhForm t ,String czqebzdm) throws Exception{
		return dao.getCzqebzlxForm(t,czqebzdm);
	}
	
	/**
	 * 
	 * @����:TODO(����ҽ�Ʊ���ά��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-6 ����06:08:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveCbzklxInfo(YlbxwhForm model,String type) throws Exception{
		boolean result = false;
		if ("add".equals(type)) {
			String cbzkdm = changeCbzklxdm();
			model.setCbzkdm(cbzkdm);
			return dao.addCbzklxInfo(model);
		} else if ("update".equals(type)) {
			return dao.updateCbzklxInfo(model);
		}
		return result;
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
	public String changeCbzklxdm() {
		String max = dao.getMaxCbzklxdm();
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
	 * @����:TODO(��ȡ�α�״��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2014-1-7 ����09:58:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param czqebzdm
	 * @return
	 * @throws Exception
	 * YlbxwhForm �������� 
	 * @throws
	 */
	public YlbxwhForm getCbzklxForm(YlbxwhForm t ,String cbzkdm) throws Exception{
		return dao.getCbzklxForm(t,cbzkdm);
	}
	
	
	/**
	 * 
	 * @����:TODO(ɾ����ѧ��ҽ�Ʊ�������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����06:11:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] deleteCzqebzlxwhInfo(String[] ids) throws Exception{
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
				HashMap<String, String> hm=dao.getCzqebzdm(str);
				noDel.append(hm.get("czqebzmc"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?czqebzlxwhDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//ȥ�������ය��
		str=isHaveNoId?str:_BCZSCID;
		
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 
	 * @����:TODO(ɾ����ѧ��ҽ�Ʊ�������ά��)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-20 ����02:04:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	private int czqebzlxwhDelete(String[] czqebzdm) throws Exception {
		return runDelete(czqebzdm);
	}
	
	
	
	/**
	 * 
	 * @����:TODO(ɾ����ѧ��ҽ�Ʊ��ղα�״������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-17 ����06:11:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] delCbzklxwhInfo(String[] ids) throws Exception{
		List<String> delId=new ArrayList<String>();//��ɾ����id����
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.cbzkdmIsCanDel(str)){
				delId.add(str);//��¼ɾ��id
			}else{
				HashMap<String, String> hm=dao.getCbzkdm(str);
				noDel.append(hm.get("cbzkmc"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?cbzklxwhDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//ȥ�������ය��
		str=isHaveNoId?str:_BCZSCID;
		
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 
	 * @����:TODO(ɾ����ѧ��ҽ�Ʊ��ղα�״������)
	 * @���ߣ�Dlq[���ţ�995]
	 * @���ڣ�2013-12-20 ����02:04:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	private int cbzklxwhDelete(String[] czqebzdm) throws Exception {
		
		return dao.deleteCbzklxb(czqebzdm);
	}
	
}
