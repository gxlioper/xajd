package xgxt.xtwh.xtwhOther;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.base.DealString;

/**
 * ����ά��һЩ�̶��������򷽷�
 */
public class DmwhSelectMethod {

	// ѧ��
	public static List<HashMap<String, String>> getXnList() {
		ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		String xn = null;
		map.put("xn", "");
		aList.add(map);
		int currentNd = Integer.parseInt(DealString.getDateTime().substring(0,
				4));
		for (int i = currentNd - 5; i < currentNd + 5; i++) {
			map = new HashMap<String, String>();
			xn = String.valueOf(i) + "-" + String.valueOf(i + 1);
			map.put("dm", xn);
			map.put("mc", xn);
			aList.add(map);
		}
		return aList;
	}

	// ���
	public static List<HashMap<String, String>> getNdList() {
		ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		String nd = null;
		map.put("nd", "");
		aList.add(map);
		int currentNd = Integer.parseInt(DealString.getDateTime().substring(0,
				4));
		for (int i = currentNd - 5; i < currentNd + 5; i++) {
			map = new HashMap<String, String>();
			nd = String.valueOf(i);
			map.put("dm", nd);
			map.put("mc", nd);
			aList.add(map);
		}
		return aList;
	}

	/**
	 * �Ƿ���У
	 */
	public static List<HashMap<String, String>> getSfList() {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("dm", "��");
		map.put("mc", "��");
		list.add(map);

		map = new HashMap<String, String>();

		map.put("dm", "��");
		map.put("mc", "��");
		list.add(map);

		return list;
	}
	
	/**
	 * �Ƿ�
	 * 
	 * @return
	 */
	public static List<HashMap<String, String>> getIsNot() {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("dm", "yes");
		map.put("mc", "��");
		list.add(map);

		map = new HashMap<String, String>();

		map.put("dm", "no");
		map.put("mc", "��");
		list.add(map);

		return list;
	}

	/**
	 * �Ƿ�
	 * 
	 * @return
	 */
	public static List<HashMap<String, String>> getIsNotByNumb() {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("dm", "1");
		map.put("mc", "��");
		list.add(map);

		map = new HashMap<String, String>();

		map.put("dm", "0");
		map.put("mc", "��");
		list.add(map);

		return list;
	}

	/**
	 * �Ƿ���У
	 */
	public static List<HashMap<String, String>> getSfzxList() {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dm", "��У");
		map.put("mc", "��У");
		list.add(map);

		map = new HashMap<String, String>();

		map.put("dm", "����У");
		map.put("mc", "����У");
		list.add(map);

		return list;
	}
	
	/**
	 * ��˲���
	 */
	public static List<HashMap<String, String>> getShbm() {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dm", "У��");
		map.put("mc", "У��");
		list.add(map);

		map = new HashMap<String, String>();

		map.put("dm", "Ժ��");
		map.put("mc", "Ժ��");
		list.add(map);

		return list;
	}

	/**
	 * �鵵������� ��ȡ������
	 */
	public static List<HashMap<String, String>> getCzrList() {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dm", "ѧУ");
		map.put("mc", "ѧУ");
		list.add(map);

		map = new HashMap<String, String>();

		map.put("dm", "ѧԺ");
		map.put("mc", "ѧԺ");
		list.add(map);

		map = new HashMap<String, String>();

		map.put("dm", "����Ա");
		map.put("mc", "����Ա");
		list.add(map);

		return list;
	}

	/**
	 * ��ȡ�Ա�����
	 */
	public static List<HashMap<String, String>> getXbList() {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dm", "��");
		map.put("mc", "��");
		list.add(map);

		map = new HashMap<String, String>();

		map.put("dm", "Ů");
		map.put("mc", "Ů");
		list.add(map);

		return list;
	}
	
	/**
	 * ��ȡ��Ԣ�Ա�����
	 */
	public static List<HashMap<String, String>> getGyXbList() {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dm", "��");
		map.put("mc", "��");
		list.add(map);

		map = new HashMap<String, String>();

		map.put("dm", "Ů");
		map.put("mc", "Ů");
		list.add(map);

		map = new HashMap<String, String>();

		map.put("dm", "���");
		map.put("mc", "���");
		list.add(map);
		
		return list;
	}
	
	/**
	 * ��������
	 * @return
	 */
	public static List<HashMap<String, String>> getStmc(){
		DAO dao=DAO.getInstance();
		StringBuilder sb=new StringBuilder();
		sb.append("select a.stmc || a.stxz || a.bmdm dm, ");
		sb.append("a.stmc || '(' || case  when a.stxz = 'ѧԺ' then ");
		sb.append("(select distinct b.xymc from view_njxyzybj b where a.bmdm = b.xydm) ");
		sb.append("when a.stxz = 'רҵ' then ");
		sb.append("(select distinct b.zymc from view_njxyzybj b where a.bmdm = b.zydm) ");
		sb.append("when a.stxz = '�༶' then ");
		sb.append("(select distinct b.bjmc from view_njxyzybj b where a.bmdm = b.bjdm) ");
		sb.append("else 'ѧУ' end || ')' mc from xsh_stglb a ");
		return dao.getList(sb.toString(), new String[]{}, new String[]{"dm","mc"});
	}
	
	/**
	 * ��ȡ��λ���
	 */
	public static List<HashMap<String, String>> getDwlb() {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dm", "У��");
		map.put("mc", "У��");
		list.add(map);

		map = new HashMap<String, String>();

		map.put("dm", "У��");
		map.put("mc", "У��");
		list.add(map);

		return list;
	}
	
	/**
	 * ��������
	 */
	public static List<HashMap<String, String>> getBxlx() {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dm", "��ѧ��");
		map.put("mc", "��ѧ��");
		list.add(map);

		map = new HashMap<String, String>();

		map.put("dm", "�Զ���");
		map.put("mc", "�Զ���");
		list.add(map);

		return list;
	}
	
	/**
	 * ��ȡ�Ա�����
	 */
	public static List<HashMap<String, String>> getXbForSsxxList() {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dm", "��");
		map.put("mc", "��");
		list.add(map);

		map = new HashMap<String, String>();

		map.put("dm", "Ů");
		map.put("mc", "Ů");
		list.add(map);
		
		map = new HashMap<String, String>();
		
		map.put("dm", "���");
		map.put("mc", "���");
		list.add(map);

		return list;
	}
	
	/**
	 *  ��ȡ�Ӽ�������
	 */
	
	public static List<HashMap<String, String>> getJjfList(){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dm", "����");
		map.put("mc", "����");
		list.add(map);

		map = new HashMap<String, String>();

		map.put("dm", "�ӷ�");
		map.put("mc", "�ӷ�");
		list.add(map);

		return list;
	}
}
