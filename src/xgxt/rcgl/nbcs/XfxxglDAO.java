package xgxt.rcgl.nbcs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

public class XfxxglDAO extends DAO{
	ArrayList<String> value = new ArrayList<String>();
	
	/**
	 * 获取查询条件
	 * */
	public StringBuffer getWhereSql(XfxxglForm model){
		StringBuffer sb = new StringBuffer(" where 1=1 ");		
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj = model.getNj();		
		String nd = model.getNd();
		String xh = model.getXh();
		String xm = model.getXm();
		
		if(!StringUtils.isNull(xydm)){
			sb.append( " and xydm=?");
			value.add(xydm);
		}
		if(!StringUtils.isNull(zydm)){
			sb.append( " and zydm=?");
			value.add(zydm);
		}
		if(!StringUtils.isNull(bjdm)){
			sb.append( " and bjdm=?");
			value.add(bjdm);
		}
		if(!StringUtils.isNull(nj)){
			sb.append( " and nj=?");
			value.add(nj);
		}
		if(!StringUtils.isNull(nd)){
			sb.append( " and nd=?");
			value.add(nd);
		}
		if(!StringUtils.isNull(xh)){
			sb.append( " and xh like '%'||?||'%'");
			value.add(xh);
		}
		if(!StringUtils.isNull(xm)){
			sb.append( " and xm like '%'||?||'%'");
			value.add(xm);
		}
		return sb;
	}
	
	/**
	 * 检测记录是否存在
	 * @param String tableName
	 * @param String pk
	 * @param String pkValue
	 * @return boolean
	 * */
	public boolean checkExists(String tableName, String pk, String pkValue){
		String sql = "select count(*)num from " + tableName + " where " + pk + " =?";
		String result = getOneRs(sql, new String[]{pkValue},"num");
		return Integer.parseInt(StringUtils.isNull(result) ? "0" : result) >0 ? true : false;
	}
	
	
	
	/**
	 * 查询学生学费信息
	 * @param BylfglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> selectXsxfxxb(XfxxglForm model,String[] outputValue){
		outputValue = outputValue ==  null ? new String[]{"pk","nd","xh","xm","bjmc","qjxf","qtf","qjdgf","qjzjf","jfzt"} : outputValue;
		String whereSql = getWhereSql(model).toString();
		String sql = "select  rownum r, a.*  from (select nd||xh pk,a.nd ,a.xh,a.xm,a.bjmc,a.qjxf,a.qtf,a.qjdgf,(qjxf+qtf+qjdgf)qjzjf,jfzt from view_nbcs_xsxfxxb a" + whereSql + ")a";
		Pages paganitionModel = model.getPages();
		paganitionModel.setMaxRecord(Integer.parseInt(getOneRs("select count(*) num from view_nbcs_xsxfxxb" + whereSql, value != null ? value.toArray(new String[0]) : new String[]{}, "num")));
		sql = "select * from (" + sql + ") where r>" + model.getPages().getStart() + " and r<=" + (model.getPages().getStart()+model.getPages().getPageSize()); 
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * 查询学生学费信息导出
	 * @param BylfglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> selectXsxfxxbForExp(XfxxglForm model){
		String[] outputValue = {"nd","xh","xm","xb","xz","nj","xydm","xymc","zydm","zymc","bjdm","bjmc","xxxs","pycc","zkzh","qjxf","qtf","qjdgf","qjzjf","jfzt"};
		String whereSql = getWhereSql(model).toString();
		String sql = "select nd,xh,xm,xb,xz,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xxxs,pycc,zkzh,qjxf,qtf,qjdgf,(qjxf+qtf+qjdgf)qjzjf,jfzt from view_nbcs_xsxfxxb ";
		
		return rsToVator(sql+whereSql, value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	
	/**
	 * 根据主键学生学费信息
	 * @param BylfglForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectXsxfxxbOne(XfxxglForm model){
		String[] outputValue = {"nd","xh","xm","xb","xz","nj","xydm","xymc","zydm","zymc","bjdm","bjmc","xxxs","pycc","zkzh","qjxf","qtf","qjdgf","jfzt"};
		String sql = "select nd,xh,xm,xb,xz,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xxxs,pycc,zkzh,qjxf,qtf,qjdgf,jfzt from view_nbcs_xsxfxxb where nd||xh=?";
		
		return getMap(sql, new String[]{model.getPkValue()}, outputValue);
	}
	
	/**
	 * 查询教材费结算信息
	 * @param BylfglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> selectJcfjsb(XfxxglForm model,String[] outputValue){
		outputValue = outputValue ==  null ? new String[]{"pk","nd","xh","xm","nj","xymc","zymc","bjmc","jyje"} : outputValue;
		String whereSql = getWhereSql(model).toString();
		String sql = "select  rownum r, a.*  from (select nd||xh pk,a.nd ,a.xh,a.xm,a.bjmc,a.xymc,a.zymc,a.nj,a.jyje from view_jcfjsb a" + whereSql + ")a";
		Pages paganitionModel = model.getPages();
		paganitionModel.setMaxRecord(Integer.parseInt(getOneRs("select count(*) num from view_jcfjsb" + whereSql, value != null ? value.toArray(new String[0]) : new String[]{}, "num")));
		sql = "select * from (" + sql + ") where r>" + model.getPages().getStart() + " and r<=" + (model.getPages().getStart()+model.getPages().getPageSize()); 
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * 查询教材费结算信息导出
	 * @param BylfglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> selectJcfjsbForExp(XfxxglForm model){
		String[] outputValue = {"nd","xh","xm","xb","xz","nj","xydm","xymc","zydm","zymc","bjdm","bjmc","jyje"};
		String whereSql = getWhereSql(model).toString();
		String sql = "select nd,xh,xm,xb,xz,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,jyje from view_jcfjsb ";
		
		return rsToVator(sql+whereSql, value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	
	/**
	 * 根据主键教材费结算信息
	 * @param BylfglForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectJcfjsbOne(XfxxglForm model){
		String[] outputValue = {"nd","xh","xm","xb","xz","nj","xydm","xymc","zydm","zymc","bjdm","bjmc","jyje"};
		String sql = "select nd,xh,xm,xb,xz,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,jyje from view_jcfjsb where nd||xh=?";
		
		return getMap(sql, new String[]{model.getPkValue()}, outputValue);
	}
	
	/**
	 * 检测学生学费信息是否已经存在
	 * @param HashMap<String, String> map
	 * @return boolean
	 * */
	public boolean checkXsxfxx(HashMap<String, String> map){		
		return checkExists("nbcs_xsxfxxb", "nd||xh", map.get("nd")+map.get("xh"));
	}	
	
	/**
	 * 检测教材费结算信息是否已经存在
	 * @param HashMap<String, String> map
	 * @return boolean
	 * */
	public boolean checkJcjsxx(HashMap<String, String> map){		
		return checkExists("jcfjsb", "nd||xh", map.get("nd")+map.get("xh"));
	}
}
