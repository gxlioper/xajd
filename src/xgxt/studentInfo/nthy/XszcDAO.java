package xgxt.studentInfo.nthy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;

public class XszcDAO {
	
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
		
		if ("zczt".equalsIgnoreCase(lx)) {
			dm = new String[] { "δ����","��ע��","δע��" };
			mc = new String[] { "δ����","��ע��" ,"δע��"};
		} else if ("tjjg".equals(lx)) {
			dm = new String[] { "xy", "zy","bj" };
			mc = new String[] {Base.YXPZXY_KEY, "רҵ","�༶"};
		} else if("sfqf".equals(lx)){
			dm = new String[] { "0","1" };
			mc = new String[] { "�ѽ���" ,"δ����"};
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
	public HashMap<String, String> viewZcxx(XszcActionForm form){
		String sql = "select a.xh,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.nj,a.xb,a.xm,b.xn,b.xq," +
				"b.zczt,b.wzcyy,b.czr,b.czsj,case when nvl(c.sfqf,'0')='1' then 'δ����' else '�ѽ���' end sfqfmc from view_xsjbxx a " +
				"left join xg_xsxx_nthy_xszcb b on a.xh=b.xh and b.xn=? and b.xq=? left join xg_rcsw_nthy_xsqfxxb c on a.xh=c.xh and c.xn=?  where a.xh=?";
		return dao.getMap(sql, new String[]{form.getXn(),form.getXq(),form.getXn(),form.getXh()}, new String[]{"xh","xm","nj","xymc","zymc","bjmc","zczt","wzcyy","czr","czsj","sfqfmc"});
	}
}
