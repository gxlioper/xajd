package xsgzgl.gygl.comm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.User;

/**
 * Title: ѧ������ϵͳ
 * Description: ��Ԣ����_������_ͨ��-service��
 * Copyright: Copyright (c) 2011
 * Company: zfsoft
 * @author zhang
 * @version 3.0
 */

public class GyglNewService extends CommService {

	GyglNewDAO gyglNewDao = new GyglNewDAO();
	
	public List<HashMap<String, String>> getToplist(String[] colListCN ) throws Exception {
		String[] en = new String[colListCN.length];
		for(int i=0;i<en.length;i++){
			en[i]="tr_"+i;
		}
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.arrayToList(en,colListCN);
		return list;
	}
			
	/**
	 * ��ȡtopTr
	 * @param mk
	 * @return
	 */
	public String[] getTopTr(String mk){
		String[] topTr = null;
		
		if("qswh".equalsIgnoreCase(mk)){
			topTr = new String[]{"¥������","���Һ�","�����Ա�","�����꼶","����"+Base.YXPZXY_KEY,"��λ��","������λ","���д�λ��","��ס���"};
		}else if("cwwh".equalsIgnoreCase(mk)){
			if("xydm".equals(GyglNewInit.CWFPDX)){//��λ�������ΪѧԺʱ������ʾ�����༶����
				topTr = new String[]{"¥������","���Һ�","��λ��","��λ�Ա�","�����꼶","����"+Base.YXPZXY_KEY,"ѧ��","����","�Ƿ���"};
			}else if("zydm".equals(GyglNewInit.CWFPDX)){//��λ�������ΪѧԺʱ������ʾ�����༶����
				topTr = new String[]{"¥������","���Һ�","��λ��","��λ�Ա�","�����꼶","����"+Base.YXPZXY_KEY,"����רҵ","ѧ��","����","�Ƿ���"};
			}else {
				topTr = new String[]{"¥������","���Һ�","��λ��","��λ�Ա�","�����꼶","����"+Base.YXPZXY_KEY,"�����༶","ѧ��","����","�Ƿ���"};
			}
		}else if("zsgl".equalsIgnoreCase(mk)){		
			if(Globals.XXDM_zjgmzyjsxy.equalsIgnoreCase(Base.xxdm)){
				topTr = new String[]{"¥������","���Һ�","��λ��","��λ�Ա�","ѧ��","����","רҵ����","�༶","�Ƿ���","��ϵ��ʽ"};
			}else if("13033".equalsIgnoreCase(Base.xxdm)) {
				topTr = new String[]{"¥������","���Һ�","��λ��","��λ�Ա�","ѧ��","����","רҵ����","�༶","�Ƿ���","��ע","Υ�����"};
			}else if("10868".equals(Base.xxdm)){
				topTr = new String[]{"¥������","���Һ�","��λ��","��λ�Ա�","ѧ��","����","רҵ����","�༶","�Ƿ���","��ע","����ʱ��","�Ǽ�","����ʱ��"};
			} else if("12216".equals(Base.xxdm)) {
				topTr = new String[]{"¥������","���Һ�","��λ��","��λ�Ա�","ѧ��","����","ѧԺ����","רҵ����","�༶","�Ƿ���","��ע"};
			}else {
				topTr = new String[]{"¥������","���Һ�","��λ��","��λ�Ա�","ѧ��","����","רҵ����","�༶","�Ƿ���","��ע"};
			}
		}else if("xxtj".equalsIgnoreCase(mk)){
			if("1".equals(GyglNewInit.XQBJ)){
				topTr = new String[]{"У��", "¥��", "����", "������", "Ů����", "����", "������", "Ů����", "����", "������", "Ů����", "����", "������", "Ů����"};
			}else{
				topTr = new String[]{"¥��", "����", "������", "Ů����", "����", "������", "Ů����", "����", "������", "Ů����", "����", "������", "Ů����"};
			}
		}else if("sjsz".equalsIgnoreCase(mk)){
			topTr = new String[]{"�꼶", Base.YXPZXY_KEY, "��ʼʱ��", "����ʱ��", "����״̬"};
		}else if("yh".equalsIgnoreCase(mk)){
			topTr = new String[]{"�û���", "����", "���ڲ���"};
		}else if("xs".equalsIgnoreCase(mk)){
			topTr = new String[]{"ѧ��", "����", "�Ա�", "�꼶", Base.YXPZXY_KEY, "רҵ", "�༶"};
		}else if("gyglry".equals(mk)){
			if("10466".equals(Base.xxdm)){//����ũҵ��ѧ
				topTr=new String[]{"¥��","���","���Һ�","����","�Ա�","ѧ��","����","��ϵ�绰","��ע"};
			}else {
				topTr=new String[]{"¥��","���","���Һ�","����","�Ա�","ѧ��","����","��ϵ�绰","����绰","��ע"};
			}
		}else if("rzyy".equals(mk)){
			topTr = new String[]{"��סԭ�����","��סԭ������"};
		}else if("dtxxtj".equals(mk))
			topTr = new String[]{"ϵ������", "ʱ��", "����", "ʱ��", "����", "�ϻ�ʱ��", "����", "Ԥ��ϸ�����ʱ��", "�ϻ�ʱ��", "����", "Ԥ����", "�ϻ�ʱ��", "����"};
		return topTr;
	}
	
	/**
	 * ��ȡ¥����Ϣ
	 * @return
	 */
	public List<HashMap<String, String>> getLdList(){
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("orderBy", "order by lddm");
		
		return gyglNewDao.getRsList("xg_gygl_new_ldxxb", map, new String[]{"lddm", "ldmc"});
	}
	/**
	 * 
	 * @����:��ѯ¥����Ϣ�������û�Ȩ�ޣ�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-1-3 ����10:07:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getLdxxList(User user){
		
		return gyglNewDao.getLdxxList(user);
	}
	
	public HashMap<String,String> getStuInfo_gy(String xh){
		return gyglNewDao.getStuInfo_gy(xh);
	}
	
	/**
	 * ��ȡ����ԭ��list
	 * @return
	 */
	public List<HashMap<String, String>> getTsyyList(){
		return gyglNewDao.getTsyyList();
	}
	
	public List<HashMap<String, String>> getTzyyList(){
		return gyglNewDao.getTzyyList();
	}
	/**
	 * ��ȡ��Ԣ����Ա������
	 * @param myForm
	 * @return
	 */
	public String getSearchTjByGyfdy(HttpServletRequest request){
		return gyglNewDao.getSearchTjByGyfdy(request);
	}
	/**
	 * ��ȡ��Ԣ����Ա��������not in��
	 */
	public String getSearchTjByGyfdyNotIn(HttpServletRequest request){
		return gyglNewDao.getSearchTjByGyfdyNotIn(request);
	}
	
	/**
	 * ��ȡ��ʼ������list
	 * @return
	 */
	public List<HashMap<String, String>> getCshlxList(){
		HashMap<String, String> map = new HashMap<String, String>();
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		map.put("dm", "1");
		map.put("mc", "��մ�λȫ������");
		list.add(map);
		String cwfpdx = GyglNewInit.CWFPDX;
		if("bjdm".equalsIgnoreCase(cwfpdx)){
			map = new HashMap<String, String>();
			map.put("dm", "0");
			map.put("mc", "��մ�λ�༶����");
			list.add(map);
		}
		
		return list;
	}
	
	public List<HashMap<String,String>> getXqList(){

		Map<String, String> map = new HashMap<String, String>();
		map.put("orderBy", "order by dm");
		
		return gyglNewDao.getRsList("dm_zju_xq", map, new String[]{"dm", "xqmc"});
	}
	
	/**2012-03-06 23
	 * ��ȡ԰���б�
	 * @return
	 */
	public List<HashMap<String,String>> getYqList(String xqdm){

		Map<String, String> map = new HashMap<String, String>();
		if("1".equals(GyglNewInit.XQBJ)){//У�����
			map.put("xqdm", xqdm);
		}
		map.put("orderBy", "order by yqdm");
		
		
		return gyglNewDao.getRsList("zxbz_ssyqdm", map, new String[]{"yqdm", "yqmc"});
	}
	
	/**
	 * ��ȡ��סԭ��list
	 * @return
	 */
	public List<HashMap<String, String>> getRzyyList(){
		return gyglNewDao.getRzyyList();
	}
}
