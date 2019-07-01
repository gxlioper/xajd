package xgxt.qgzx.whlgdx.dao;

import java.util.HashMap;
import java.util.List;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.daoActionLogic.StandardOperation;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �ڹ���ѧģ���人����ѧ��λ����DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-28</p>
 */
public class WhlgdxGwglDAO {
	/**
	 * ��ѯ��λ��ϸ��ϸ
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getGwxx(String pkValue){
		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		//��λ������Ϣ
		String[] outputValue = {"xq","gwdm","gwxzmc","yrdwmc","xn","nd","gzksrq","gzjsrq","xyrs","syknss","sqsyrs","sqsyknss","jcfsmc","spbcbz","mssjd","msdd",
				"fzr","lxdh","gzdd","gzsj","xydmc","rzyq_nj","rzyq_xb","rzyq_zymc","rzyq_wyyq","rzyq_gzjn","rzyq_qt","gzmd","gznd","gzyd","gzjj","mtbzgz","dqbzgz","bz"};
		String sql = "select * from view_gwxx where gwdm||gwsbsj=?";
		map = dao.getMap(sql, new String[]{pkValue}, outputValue);
		//�ж���������ø�λ
		sql = "select count(*) count from view_xsgwxx where gwdm||gwsbsj=?";
		map.put("sqrs", dao.getOneRs(sql, new String[]{pkValue}, "count"));
		//�ж���������ͨ��
		sql = "select count(*) count from view_xsgwxx where gwdm||gwsbsj=? and xxyj='ͨ��'";
		map.put("tgrs", dao.getOneRs(sql, new String[]{pkValue},"count"));
		return map;
	}
	
	/**
	 * ��ȡ���ѧ��ѧ���·�
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getNdYf(){
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		
		String sql = "select nd,xn,xq from gwsqsjb where rownum=1";
		map.putAll(dao.getMap(sql, new String[] {},new String[]{"nd","xn","xq"}));
		
		sql = "select to_char(sysdate,'yyyy-mm-dd') time from dual";
		String time = dao.getOneRs(sql, new String[]{}, "time");		
		String yf = time.substring(5,7);		
		map.put("yf", yf);
		
		
//		String sql = "select xn,nd,xq from gwsqsjb where rownum=1";
//		String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] { "xn", "nd", "xq" });
//		map.put("xn", tmp[0]);
//		map.put("nd", tmp[1]);
//		map.put("xq", tmp[2]);
//		sql = "select to_char(sysdate,'yyyy-mm-dd') time from dual";
//		tmp = dao.getOneRs(sql, new String[] {}, new String[] { "time" });
//		map.put("yf", tmp[0].substring(5, 7));
		
		return map;
	}
	
	/**
	 * ��ѯ��λ��ϸ��Ϣ
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getCjffGwxx(String pkValue){
		String xxdm = StandardOperation.getXxdm();
		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		String sql = "";
		String nd = this.getNdYf().get("nd");
		String cjffsj = dao.getOneRs("select cjffsj from gwsqsjb", new String[]{}, "cjffsj");
		if(cjffsj != null && !"".equalsIgnoreCase(cjffsj) && !xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
			nd = cjffsj.substring(0,4);
		}
		//��𷢷Ÿ�λ��Ϣ
		sql = "select sqdw,gznr,gwxzmc,decode(jcfs,'h','Ԫ/Сʱ','d','Ԫ/��','w','Ԫ/��','m','Ԫ/��','n','Ԫ/־Ը����') jcfs," +
			  "spbcbz,decode(jcfs,'h','Сʱ','d','��','w','��','m','��') gzsjdw, gwsbsj, gwdm, yrdwmc, gwxz,xymc " +
			  "from view_gwxx where gwdm||gwsbsj='" + pkValue + "'";
		map.putAll(dao.getMap(sql, new String[] {}, new String[] { "sqdw", "gznr", "gwxzmc", "jcfs", "spbcbz", "gzsjdw", "gwsbsj",
				"gwdm", "yrdwmc", "gwxz", "xymc" }));
		
		// ʣ�ྭ��=�������+�������-���Ž��(�������+�������=hbje)
		sql = "select nvl((select sum(hbje) from jfhbb where nd=? and yrdwdm=? and gwxzdm=? and yrdwdm=?),0)-nvl((select sum(cjje) from view_xscjff where nd=? and sqdw=? and gwxzmc=? and yrdwmc=?),0) syjf from dual";
		String syjf = dao.getOneRs(sql, new String[] { nd,
				map.get("sqdw"), map.get("gwxz"), map.get("sqdw"), map.get("nd"),
				map.get("sqdw"), map.get("gwxzdm"), map.get("sqdw")}, "syjf");
		map.put("syjf", syjf);
		map.put("nd", nd);
		map.put("xn", this.getNdYf().get("xn"));
		map.put("xq",  this.getNdYf().get("xq"));
		map.put("yf",  this.getNdYf().get("yf"));
		map.put("yf",cjffsj != null && !"".equalsIgnoreCase(cjffsj) && !xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)? cjffsj.substring(4,6): map.get("yf"));
		return map;
	}
	
	/**
	 * ��ѯѧ����𷢷���Ϣ
	 * @param pkValue
	 * @return List
	 * */
	public List getStuCjffxx(String pkValue){
		List list = null;
		DAO dao = DAO.getInstance();
		String nd = this.getNdYf().get("nd");
		String yf = this.getNdYf().get("yf");
		String cjffsj = dao.getOneRs("select cjffsj from gwsqsjb", new String[]{}, "cjffsj");
		if(cjffsj != null && !"".equalsIgnoreCase(cjffsj)){
			nd = cjffsj.substring(0,4);
			yf = cjffsj.substring(4,6);
		}
		
		String sql = "select xh,xm,bjmc,gzsj,cjje,bz,decode(xxsh,'ͨ��','CHECKED','') xxsh from view_xscjff a where exists(select 1 from view_xsgwxx b where a.gwdm=b.gwdm and a.xh=b.xh and b.gwdm||b.gwsbsj=?) and nd=? and yf=?";
		list = dao.getList(sql, new String[]{pkValue,nd,yf}, new String[]{"xh","xm","bjmc","gzsj","cjje","bz","xxsh"});
		return list;
	}
	
	/**
	 * ��ȡ��𷢷�ʱ��
	 * @return String
	 * */
	public String getCjffsj(){
		DAO dao = DAO.getInstance();
		String sql = "select cjffsj from gwsqsjb";
		
		return dao.getOneRs(sql, new String[]{}, "cjffsj");
	}
}
