package xgxt.xsgygl.bjlh.ssfp;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;

public class SsfpService {
	SsfpDAO dao = new SsfpDAO();
	
	/**
	 * �������б�
	 * @return
	 */
	public List<HashMap<String, String>> getFpbjList() {
		return dao.getSelectList("fpbj");
	}
	
	/**
	 * ��ѯѧԺ�б�,������,��Ϣ,�Զ���,����ѧԺ������ǰ��
	 * @return
	 */
	public List<HashMap<String, String>> getXyList() {
		return dao.getXyList();
	}
	
	/**
	 * ������ѧԺ�б�Ļ���������"��ί", "������ѧ��", "���д�", "���˽�����"
	 * @param xyList
	 * @return
	 */
	public List<HashMap<String, String>> appendXyList(List<HashMap<String, String>> xyList) {
		String[] en = {"0202", "0405", "0110", "0117"};
		String[] cn = {"��ί", "������ѧ��", "���д�", "���˽�����"};
		for (int i=0;i<en.length;i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("xydm", en[i]);
			map.put("xymc", cn[i]);
			xyList.add(map);
		}
		return xyList;
	}
	
	/**
	 * �ѻ��ִ�λ������������Ե��Ǵ�λ������
	 * @param xydm
	 * @param fpbj ȫ���ƣ���ί��������ѧ�������д������˽�����
	 * @return
	 */
	 public  String[] getSsFpYhfcws(String xydm, String fpbj){
		 return dao.getSsFpYhfcws(xydm,fpbj);
	 }
	 
	/**
	 * δ����ѧ����������������Ե���ѧ��������
	 * @param xydm
	 * @param fpbj ȫ���ƣ���ί��������ѧ�������д������˽�����
	 * @return
	 */
	public String[] getCwFpZsData(String xydm, String fpbj){
			return dao.getCwFpZsData(xydm,fpbj);
		}
	
	 /**
	  * δ���������б�
	  * @param xqdm
	  * @param xb
	  * @param lddm
	  * @param cs
	  * @return
	  */
	 public List<HashMap<String, String>> getSsFpSsXxList(String xqdm,String xb,String lddm,String cs) {
		 return dao.getSsFpSsXxList(xqdm, xb, lddm, cs);
	 }
	 
	 /**
		 * �����ѻ���������Ϣ�б�
		 * @param lddm ¥������
		 * @param xqdm У������
		 * @param xydm ѧԺ����
		 * @param bjdm �༶����
		 * @return 
		 */
	public List<HashMap<String, String>> getSsFpFpSsXxList(String lddm,String cs,String xydm, String fpbj) {
			return dao.getSsFpFpSsXxList(lddm, cs, xydm, fpbj);
		}
	
	/**
	 * �������������Ϣ
	 * @param oldMappingItems
	 * @param newMappingItems
	 * @return
	 * @throws Exception
	 */
	public boolean saveSsfpxx(String oldMappingItems, String newMappingItems) throws Exception{
		return dao.saveSsfpxx(oldMappingItems, newMappingItems);
	}
	
	/**
	 * ��ѯ���Ữ�ֱ�ͷ
	 * @return
	 */
	public List<HashMap<String, String>> querySshfTitle() {
		String[] en = new String[]{ "pk", "r", "xqmc", "ldmc", "xbxd","cs", "qsh", "xymc"};
		String[] cn = new String[]{ "pk", "�к�", "У������", "¥������","�Ա��޶�", "��������", "���Һ�", "����"+Base.YXPZXY_KEY+"(����)"};
		return dao.getList(en, cn);
	}
	
	/**
	 * ��ѯ���Ữ�ֽ��
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> querySsfpResult(SsfpModel model) throws Exception{
		return dao.querySsfpResult(model);
	}
	
	/**
	 * ͨ������(ssbh)ɾ�����������Ϣ
	 * @param pkList
	 * @return
	 */
	public boolean deleteSsfpxx(String[] pkList) throws Exception{
		return dao.deleteSsfpxx(pkList);
	}
	
	/**
	 * ��ù�Ԣ��������б�
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getGyglList(String tableName, SsfpModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getGyglList(tableName, model, colList);
	}
}
