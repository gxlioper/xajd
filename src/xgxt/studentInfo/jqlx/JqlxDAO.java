package xgxt.studentInfo.jqlx;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;

public class JqlxDAO {
	
	DAO dao = DAO.getInstance();
	
	/**
	 * ͨ��ѧ�Ż�ȡѧ����Ϣ
	 * @param xh ѧ��
	 * @return
	 */
	public HashMap<String, String> getStuInfo(String xh){
		String sql = "select * from (select a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,b.xm fdyxm from view_xsxxb a left join (select a.*,b.xm from fdybjb a,fdyxxb b where a.zgh=b.zgh) b on a.bjdm=b.bjdm) where xh=?";
		return dao.getMap(sql, new String[]{xh}, new String[]{"xh","xm","xb","xymc","zymc","bjmc","fdyxm"});
	}
	
	/**
	 * ��ȡѧ��������У������Ϣ
	 * @param pk ѧ��+ѧ��+ѧ��
	 * @return
	 */
	public HashMap<String, String> getJqsqInfo(String pk){
		String sql = "select * from view_xg_xsxx_xsjqlxsqb where xn||xq||xh=? ";
		return dao.getMapNotOut(sql, new String[]{pk});
	}
	
	/**
	 * ��ȡѧ��������У��������
	 * @param pk ѧ��+ѧ��+ѧ��
	 * @return
	 */
	public String getCont(String pk){
		String sql = "select count(1) cont from xg_xsxx_xsjqlxsqb where xn||xq||xh=?";
		return dao.getOneRs(sql, new String[]{pk}, "cont");
	}
	
	/**
	 * ����ϵͳ��ǰ����ѧ�ڻ�ȡѧ������
	 * @return
	 */
	public String getXqmcFromXqdm(){
		String sql = "select xqmc from xqdzb where xqdm=?";
		return dao.getOneRs(sql, new String[]{Base.currXq}, "xqmc");
	}
	
	/**
	 * �����б�ѡ��ά��
	 * 
	 * @param lx
	 * @return
	 */
	public List<HashMap<String, String>> getSelectList(String lx) {
		String[] dm = null;
		String[] mc = null;
		
		if ("shzt".equalsIgnoreCase(lx)) {
			dm = new String[] { "δ���", "ͨ��","��ͨ��" };
			mc = new String[] { "δ���", "ͨ��" ,"��ͨ��"};
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
}
