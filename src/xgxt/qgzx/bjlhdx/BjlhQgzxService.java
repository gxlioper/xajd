package xgxt.qgzx.bjlhdx;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: �������ϴ�ѧ�ڹ���ѧģ��Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-11-17</p>
 */
public class BjlhQgzxService {
	BjlhQgzxDao dao = new BjlhQgzxDao();
	
	/**
	 * �޸ĸ�λ��Ϣ
	 * @param model
	 * @param request
	 * @return boolean
	 * */
	public boolean modiGwxx(CommanForm model, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String gwdm = DealString.toGBK(model.getGwdm());
		String xyrs = model.getXyrs();
		String xyknsrs = model.getXyknsrs();
		String gwxz = DealString.toGBK(model.getGwxz());
		String gzsj = DealString.toGBK(model.getGzsj());
		String gznr = DealString.toGBK(model.getGznr());
		String gwsbsj = DealString.toGBK(model.getGwsbsj());
		
		flag = StandardOperation.update("gwxxb", new String[]{"gwxz","xyrs","syknss","gzsj","gznr"}, 
				new String[]{gwxz,xyrs,xyknsrs,gzsj,gznr}, "gwdm||gwsbsj", gwdm+gwsbsj, request);
		
		return flag;
	}
	
	/**
	 * ��ȡ��λ�����б�
	 * @return List
	 * */
	public List getGwxzList(){		
		return dao.getGwxzList();
	}
	
	/**
	 * ����λ�����Ƿ���
	 * @param pkValue
	 * @return String
	 * */
	public String checkGwrs(String pkValue,String userType){		
		return dao.checkGwrs(pkValue,userType);
	}
	
	/**
	 * ��ȡѧ����λ��Ϣ
	 * @param pk
	 * @param pkVal
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuPost(String pk, String pkVal){
		String sql = "";
		sql = "select "
			+ pk
			+ ",xh,nd,xm,xn,xb,gwdm,nj,sqsj,xymc,sfpks,zymc,lxdh,bjmc,xyyj yesNo,xsgzqk,gzbx from view_xsgwxx where "
			+ pk + "=?";
		String[] colList = new String[] { pk, "xh", "nd", "xm", "xn", "xb",
			"gwdm", "nj", "sqsj", "xymc", "sfpks", "zymc", "lxdh",
			"bjmc", "yesNo", "xsgzqk", "gzbx" };
		return dao.getMap(sql, new String[]{pkVal}, colList);
	}
	
	/**
	 * ��ȡ��ѯѧ��������¼��Ϣ���ͷ
	 * @return String[]
	 * */
	public String[] getWorkLogTitle(){
		String[] colList = new String[] { "pk", "day1", "day2", "day3", "day4",
				"day5", "day5", "day6", "day7", "day8", "day9", "day10",
				"day11", "day12", "day13", "day14", "day15", "day16",
				"day17", "day18", "day19", "day20", "day21", "day22",
				"day23", "day24", "day25", "day26", "day27", "day28",
				"day29", "day30", "day31" };
		
		return colList;
	}
	
	/**
	 * ��ȡѧ���Ĺ�����¼
	 * @param xh
	 * @param time
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuWorkLog(String xh, String time) throws Exception{
		HashMap<String, String> tmpMap = new HashMap<String, String>();
		String sql = "";
		String[] colList = getWorkLogTitle();
	
	    sql = "select a.xh||a.time pk,a.* from xskhyb a where a.xh = ? and a.time = ? ";
	
	    tmpMap = dao.getMap(sql, new String[] { xh, time }, colList);
	    if (null == tmpMap) {
		    String sqlTmp = "insert into xskhyb (xh,time,sftj) values ('" + xh + "','" + time + "','no')";
		    dao.runUpdate(sqlTmp, new String[] {});
		    tmpMap = dao.getMap(sql, new String[] { xh, time }, colList);
	    }
	
	    return tmpMap;
	}
	
	
	/**
	 * ��ȡ��ǰ�·�
	 * @return String
	 * */
	public String getCurrentMonth(){
		String yf = dao.getOneRs("select to_char(sysdate,'mm') month from dual", new String[]{}, "month");	
		return yf;
	}
	
	/**
	 * ��ȡ�ڹ���ѧ����
	 * @return HashMap<String, String> 
	 * */
	public HashMap<String, String> getQgzxParameter(){
		String sql = "select nd, xn, xq, kssj, jssj, knsbl, mxsbc, mtzgxs, myzgxs, myzgbc, xwkssj, xwjssj, myzgsjfs from gwsqsjb";
		String[] outputValue = {"nd", "xn", "xq", "kssj", "jssj", "knsbl", "mxsbc", "mtzgxs", "myzgxs", "myzgbc", "xwkssj", "xwjssj", "myzgsjfs"};
		
		return dao.getMap(sql, new String[]{}, outputValue);
	}
	
	/**
	 * ��ȡʱ�������б�����
	 * @return List
	 * */
	public List getSjList(){
		return dao.getQgzxsjList();
	}
	
	/**
	 * ��ȡ�·������б�����
	 * @return List
	 * */
	public List getYfList(){
		return dao.getYfList();
	}
}
