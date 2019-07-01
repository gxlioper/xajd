package xgxt.pjpy.hhgxy.pjtj;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class PjpyHhgxyPjtjDAO {
	
	/**
	 * 根据班级、学年、学期获取班级科目
	 * @return
	 */
	public List<HashMap<String,String>>getKmmc(PjpyModel model){
		DAO dao=DAO.getInstance();
		String bjdm=model.getBjdm();
		String xn=model.getXn();
		String xq=model.getXq();
		String sql="select distinct(kcsbm),kcmc from view_cjb where bjdm=? and xn=? and xq=? ";
		List<HashMap<String,String>>list=dao.getList(sql, new String[]{bjdm,xn,xq}, new String[]{"kcsbm","kcmc"});
		return list;
	}
	
	/**
	 * 根据班级、学年、学期获取学生成绩
	 * @return
	 */
	public List<HashMap<String,String>>getXscj(PjpyModel model){
		DAO dao=DAO.getInstance();
		String bjdm=model.getBjdm();
		String xn=model.getXn();
		String xq=model.getXq();
		String sql="select xh,kcsbm,cj from view_cjb where bjdm=? and xn=? and xq=? ";
		List<HashMap<String,String>>list=dao.getList(sql, new String[]{bjdm,xn,xq}, new String[]{"xh","kcsbm","cj"});
		return list;
	}
	
	/**
	 * 获取综合测评信息
	 * @return
	 */
	public List<HashMap<String,String>>getZccj(PjpyModel model){
		DAO dao=DAO.getInstance();
		String bjdm=model.getBjdm();
		String xn=model.getXn();
		String xq=model.getXq();
		String sql=" select distinct(b.xh),b.xm,a.dcj,a.zcj,a.tcj,a.zpf,a.bz from view_cjb b left join view_zhszcp a  on a.xh=b.xh and a.xn=b.xn and a.xq=b.xq where b.bjdm=? and b.xn=? and b.xq=? ";
		String[]colist={"xh","xm","dcj","tcj","zcj","zpf","bz"};
		List<HashMap<String,String>>list=dao.getList(sql, new String[]{bjdm,xn,xq},colist);
		return list;
		
	}
	
	/**
	 * 获取学期名称
	 */
	public HashMap<String,String>getXqmc(PjpyModel model){
		DAO dao=DAO.getInstance();
		String xq=model.getXq();
		String sql="select xqmc from xqdzb where xqdm=? ";
		HashMap<String,String>hashMap=dao.getMap(sql, new String[]{xq},new String[]{"xqmc"});
		return hashMap;
	}
	
}
