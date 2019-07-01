
package xgxt.xslx.action;

import java.util.List;

import xgxt.DAO.DAO;

public class XslxDao {
	DAO dao = DAO.getInstance();
	
	/**
	 * 获取辅导员所带班级列表
	 * */
	public List getBjListByFdy(String fdyName){
		List bjList = null;
		String sql = "select distinct a.bjdm,a.bjmc from view_bybbj a left join  view_fdybbj b on a.bjdm=b.bjdm where b.zgh=?";
		bjList = dao.getList(sql, new String[]{fdyName}, new String[]{"bjdm","bjmc"});
		return bjList;
	}
	
	/**
	 * 根据学院代码获取学院列表
	 * */
	public List getXyListByXydm(String xydm){
		List zyList = null;
		String sql = "select distinct xydm,xymc from view_njxyzybj where xydm=?";
		zyList = dao.getList(sql, new String[]{xydm}, new String[]{"xydm","xymc"});
		return zyList;
	}
	
	/**
	 * 根据学院代码获取班级列表
	 * */
	public List getBjListByXydm(String xydm){
		List bjList = null;
		String sql = "select distinct bjdm,bjmc from view_bybbj where xydm=?";
		bjList = dao.getList(sql, new String[]{xydm}, new String[]{"bjdm","bjmc"});
		return bjList;
	}
	
	/**
	 * 获取班级列表
	 * */
	public List getBjList(String xydm,String zydm,String nj){
		List bjList = null;
		StringBuffer sb = new StringBuffer();
		sb.append(xydm ==null || "".equalsIgnoreCase(xydm) ? "" : " and xydm ='" + xydm + "'");
		sb.append(zydm==null || "".equalsIgnoreCase(zydm) ? "" : " and zydm ='" + zydm + "'");
		sb.append(nj == null || "".equalsIgnoreCase(nj) ? "" : " and nj='" + nj + "'");
		
		String sql = "select distinct bjdm,bjmc from view_bybbj where 1=1 " + sb.toString();
		bjList = dao.getList(sql, new String[]{}, new String[]{"bjdm","bjmc"});
		return bjList;
	}
	
	public boolean getSjInfo(String xm,String xydm){
		boolean flag = false;
		String sql = "select count(*) num from xdzsjxxb where xm<>? and xydm=?";
		int num = Integer.parseInt(dao.getOneRs(sql, new String[]{xm,xydm}, "num"));
		if(num>0){
			flag = true;
		}else{
			flag = false;
		}
		
		return flag;
	}
}
