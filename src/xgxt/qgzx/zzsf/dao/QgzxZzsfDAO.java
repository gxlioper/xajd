package xgxt.qgzx.zzsf.dao;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����ʦ��ѧԺ�ڹ���ѧDAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-06-24</p>
 */
public class QgzxZzsfDAO extends DAO {
	
	/**
	 * ��ȡ������ĸ�λ�б�
	 * */
	public List getGwList(){
		String sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from view_gwxx a where SHJG='ͨ��' and gzjsrq>=to_char(sysdate,'yyyymmdd')";		
		return getList(sql, new String[]{}, new String[]{"gwdm", "gwdmgwsbsj"});
	}
	/**
	 * ������Ϣ��ѯ
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getBaseInfo(String xh){
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select xh,xm,xb,sfzh,nj,xymc,zymc,bjmc,kh,zzmmmc,ykth,yhkh from view_xsxxb where xh=?";
		map = getMap(sql, new String[]{xh},new String[]{"xh", "xm", "xb", "sfzh", "nj", "xymc", "zymc", "bjmc", "kh", "zzmmmc","yhkh","ykth"});		
		
		map.putAll(getQgzxcs());
		return map;
	}
	
	/**
	 * �ڹ���ѧ������ѯ
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getQgzxcs(){
		String sql = "select * from gwsqsjb";
		return getMap(sql, new String[]{}, new String[]{"xn", "nd", "xq"});
	}
	
	/**
	 * �ж�ѧ����λ�Ƿ����
	 * @param xh
	 * @param pkV
	 * @return boolean
	 * */
	public boolean checkXsgwExists(String xh, String pkV){
		boolean result = false;
		String sql = "select count(*) num from xsgwxxb where xh=? and gwdm||'-'||gwsbsj=?";
		String num = getOneRs(sql, new String[]{xh, pkV},"num");
		
		num = num == null || "".equalsIgnoreCase(num) ? "0" : num;
		result = Integer.parseInt(num) > 0 ? true : false;
		return result; 
	}
	
	/**
	 * ��ȡѧ����λ��Ϣ
	 * @param pkV
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsgwxx(String pkV){
		HashMap<String, String> map = new HashMap<String, String>();
		String sql= "select a.*,a.gwdm||'-'||a.gwsbsj xmdm,xssq sqly,(select zzmmmc from view_xsxxb b where a.xh=b.xh)zzmmmc," +
				"(select kh from view_xsxxb b where a.xh=b.xh)kh,(select ykth from view_xsxxb b where a.xh=b.xh)ykth," +
				"(select yhkh from view_xsxxb b where a.xh=b.xh)yhkh,(select yrdwmc from yrdwdmb b where a.yrdwdm=b.yrdwdm)sqdw," +
				"(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc from view_xsgwxx a where xh||gwdm||sqsj=?";
		String[] output = {"xh","xn","nd","xq","xqmc","xmdm","xm","xb","nj","xymc","zymc","bjmc","gwdm", "gwsbsj", "xssq",
							"sqly", "gzjl", "bz","kh","zzmm","lxdh","sqdw","ykth","yhkh"};
		
		map = getMap(sql, new String[]{pkV}, output);
		return map;
	}
	
	/**
	 * ����������ȡ��λ��Ϣ
	 * @param pkV
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getGwxx(String pkV){
		String sql = "select * from view_gwxx where gwdm||'-'||gwsbsj=?";
		
		return getMap(sql, new String[]{pkV}, new String[]{"gwdm","gwsbsj"});
	}
}
