package xgxt.studentInfo.nthy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;

public class XsbdDAO {
	
	DAO dao = DAO.getInstance();
	
	/**
	 * �����б�ѡ��ά��
	 * 
	 * @param lx
	 * @return
	 */
	public List<HashMap<String, String>> getSelectList(String lx) {
		String[] dm = null;
		String[] mc = null;
		
		if ("bdzt".equalsIgnoreCase(lx)) {
			dm = new String[] { "δ����","�ѱ���","δ����" };
			mc = new String[] { "δ����","�ѱ���" ,"δ����"};
		} else if ("tjjg".equals(lx)) {
			dm = new String[] { "xy", "zy","bj" };
			mc = new String[] {Base.YXPZXY_KEY, "רҵ","�༶"};
		} 
		return dao.arrayToList(dm, mc);
	}
	

	/**
	 * ѧ���б�
	 * @return
	 */
	public List<HashMap<String, String>> getXq() {
		String sql = "select xqdm,xqmc from xqdzb where xqjb<3";
		return dao.getList(sql, new String[] {}, new String[] {"xqdm","xqmc"});
	}
	
	/**
	 * ��ȡѧ������
	 * @return
	 */
	public List<HashMap<String, String>> getXnList() {
		// ���ز�ѯʱʹ�õ�ѧ�ꡢ����б�
		ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
		String xn = null;
		String nd = null;
		HashMap<String, String> map = new HashMap<String, String>();
		int currentNd = Integer.parseInt(DealString.getDateTime().substring(0,
				4));
		for (int i = currentNd - 5; i < currentNd + 5; i++) {
			map = new HashMap<String, String>();
			nd = String.valueOf(i);
			xn = String.valueOf(i) + "-" + String.valueOf(i + 1);
			map.put("nd", nd);
			map.put("xn", xn);
			aList.add(map);
		}
		return aList;
	}
	
	/**
	 * �鿴ѧ�����������Ϣ
	 * @param form
	 * @return
	 */
	public HashMap<String, String> viewBdxx(XsbdActionForm form){
		String sql = "select a.xh,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.nj,a.xb,a.xm,b.xn,b.xq,b.bdzt,b.wbdyy,b.czr,b.czsj from view_xsjbxx a left join xg_xsxx_nthy_xsbdb b on a.xh=b.xh and b.xn=? and b.xq=?   where a.xh=?";
		return dao.getMap(sql, new String[]{form.getXn(),form.getXq(),form.getXh()}, new String[]{"xh","xm","nj","xymc","zymc","bjmc","bdzt","wbdyy","czr","czsj"});
	}
}
