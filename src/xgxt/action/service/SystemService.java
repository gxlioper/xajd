package xgxt.action.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.action.base.BaseDAO;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.utils.String.StringUtils;
import common.Globals;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 系统管理模块Service
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-04-28</p>
 */
public class SystemService {
	BaseDAO dao = new BaseDAO();
	/**
	 * 获取代码维护查询信息sql
	 * @param tName
	 * @param tTem
	 * @return String
	 * */
	@SuppressWarnings("unchecked")
	public String getQuerrySQL(String tName,String tTem){
		HashMap<String, String> map = (HashMap<String, String>)dao.getPkOfTable(tName).get(0);
		String pk = map.get("pk");
		String xxdm = StandardOperation.getXxdm();
		String openFlag = Base.initProperties.get("codeByDept");
		String sql = "";
		
		if(openFlag != null && "true".equalsIgnoreCase(openFlag)){
			sql = "select rownum 行号,a.*,(select xm from yhb where yhm=(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"'))添加人,(select bmmc from view_yhxx c where yhm=(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"'))部门,(select czsj from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')添加时间 from " + tName + " a";
		
			if (tName.equalsIgnoreCase("xljk_stsslbdmb")) {
				sql = "select rownum 行号,a.SSLXDM,a.SSLXMC,(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')添加人,(select bmmc from view_yhxx c where yhm=(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"'))部门,(select czsj from dmwhczrxxb b where b.xmdm=a."+pk+" )添加时间 from " + tName + " a";
			}
			if ("sztz_xmdmb".equalsIgnoreCase(tName)) {
				sql = "select rownum 行号,a.*,(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')添加人,(select bmmc from view_yhxx c where yhm=(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"'))部门,(select czsj from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')添加时间 from view_sztz_xmdmb a";
			}
			if ("sztz_sqsblyb".equalsIgnoreCase(tName)) {
				sql = "select rownum 行号,a.* ,(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')添加人,(select bmmc from view_yhxx c where yhm=(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"'))部门,(select czsj from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')添加时间 from view_sztz_sqsblyb a";
			}
			if ("sztz_hjjbdmb".equalsIgnoreCase(tName)) {
				sql = "select rownum 行号,a.*,(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')添加人,(select bmmc from view_yhxx c where yhm=(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"'))部门,(select czsj from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')添加时间 from view_sztz_hjjbdmb a";
			}
			if ("yrdwdmb".equalsIgnoreCase(tName)) {
				tName = "view_yrdwdmb";
				sql = "select rownum 行号,a.yrdwdm,a.yrdwmc,a.lxr,a.lxdh,a.dwlb,a.ssxq,a.xymc, a.dlm,(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')添加人,(select bmmc from view_yhxx c where yhm=(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"'))部门,(select czsj from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')添加时间 from view_yrdwdmb a";
			}
			if ("gywsjcbmb".equalsIgnoreCase(tName)) {
				sql = "select rownum 行号,a.bmdm,a.bmmc,(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')添加人,(select bmmc from view_yhxx c where yhm=(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"'))部门,(select czsj from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')添加时间 from gywsjcbmb a ";
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
				if ("yrdwdmb".equalsIgnoreCase(tName)) {
					sql = "select rownum 行号, a.yrdwdm,a.yrdwmc,a.lxr,a.lxdh,a.dwlb,a.ssxq,a.xydm,(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')添加人,(select bmmc from view_yhxx c where yhm=(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"'))部门,(select czsj from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')添加时间 from view_yrdwdmb a ";
				}
			}
			if ("rcgl_dmwhb".equalsIgnoreCase(tName)) {
				sql = "select rownum 行号,a.MKDM,a.MKMC,(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')添加人,(select bmmc from view_yhxx c where yhm=(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"'))部门,(select czsj from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')添加时间 from rcgl_dmwhb a where a.MKSS='"
						+ tTem + "'";
			}
	        if("zbrydmb".equalsIgnoreCase(tName)){
	        	sql = "select rownum 行号, a.zbrydm,a.zbrymc,(select ldmc from sslddmb b where a.szlddm=b.lddm )ldmc ,(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')添加人,(select czsj from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')添加时间,(select bmmc from view_yhxx c where yhm=(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"'))部门 from zbrydmb a";
	        }
			if ("jxjdmb".equalsIgnoreCase(tName)) {
				if (Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
					sql = "select rownum 行号,a.jxjdm,a.jxjmc,a.jxjjb,a.jlje,(select b.jxjlbmc from jxjlbdmb b where b.jxjlbdm=a.jxjlb) jxjlb,(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')添加人,(select bmmc from view_yhxx c where yhm=(select czr from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"'))部门,(select czsj from dmwhczrxxb b where b.xmdm=a."+pk+" and b.ssb='"+tName+"')添加时间 from "
							+ tName + " a";
				}
			}
		}else{
			sql = "select rownum 行号,a.* from " + tName + " a";
			
			if (tName.equalsIgnoreCase("xljk_stsslbdmb")) {
				sql = "select rownum 行号,a.SSLXDM,a.SSLXMC from " + tName + " a";
			}
			if ("sztz_xmdmb".equalsIgnoreCase(tName)) {
				sql = "select rownum 行号,a.* from view_sztz_xmdmb a";
			}
			if ("sztz_sqsblyb".equalsIgnoreCase(tName)) {
				sql = "select rownum 行号,a.* from view_sztz_sqsblyb a";
			}
			if ("sztz_hjjbdmb".equalsIgnoreCase(tName)) {
				sql = "select rownum 行号,a.* from view_sztz_hjjbdmb a";
			}
			if ("yrdwdmb".equalsIgnoreCase(tName)) {
				tName = "view_yrdwdmb";
				sql = "select rownum 行号,a.yrdwdm,a.yrdwmc,a.lxr,a.lxdh,a.dwlb,a.ssxq,a.xymc, a.dlm,a.yrdwdz from view_yrdwdmb a";
			}
			if ("gywsjcbmb".equalsIgnoreCase(tName)) {
				sql = "select rownum 行号,a.bmdm,a.bmmc from gywsjcbmb a ";
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
				if ("yrdwdmb".equalsIgnoreCase(tName)) {
					sql = "select rownum 行号, a.yrdwdm,a.yrdwmc,a.lxr,a.lxdh,a.dwlb,a.ssxq,a.xydm from view_yrdwdmb a ";
				}
			}
			if ("rcgl_dmwhb".equalsIgnoreCase(tName)) {
				sql = "select rownum 行号,a.MKDM,a.MKMC from rcgl_dmwhb a where a.MKSS='"
						+ tTem + "'";
			}
	        if("zbrydmb".equalsIgnoreCase(tName)){
	        	sql = "select rownum 行号, a.zbrydm,a.zbrymc,(select ldmc from sslddmb b where a.szlddm=b.lddm )ldmc from zbrydmb a";
	        }
			if ("jxjdmb".equalsIgnoreCase(tName)) {
				if (Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
					sql = "select rownum 行号,a.jxjdm,a.jxjmc,a.jxjjb,a.jlje,(select b.jxjlbmc from jxjlbdmb b where b.jxjlbdm=a.jxjlb) jxjlb from "
							+ tName + " a";
				}
			}
		}
		return sql;
	}
	
	/**
	 * 检测记录是否存在
	 * @param tableName
	 * @param pk
	 * @param pkValue
	 * @return boolean
	 * */
	public boolean checkExists(String tableName, String pk, String pkValue){
		return dao.checkExists(tableName, pk, pkValue);
	}
	
	/**
	 * 获取导出用户组权限的查询语句
	 * @param model
	 * @return String
	 * */
	public String querryZqx(CommanForm model){
		String zdm = model.getZdm();
		String gnmkdm = model.getGnmkdm();
		StringBuffer sql = new StringBuffer("select zdm,zmc,gnmkdm,gnmkmc,dxq,dyym,xssx from view_yhzqx where 1=1 ");
		
		if(zdm != null && !"".equalsIgnoreCase(zdm)&&!zdm.equalsIgnoreCase("null")){
			sql.append(" and zdm='");
			sql.append(zdm);
			sql.append("'");
		}
		
		if(gnmkdm != null && !"".equalsIgnoreCase(gnmkdm)&&!gnmkdm.equalsIgnoreCase("null")){
			sql.append(" and gnmkdm like '");
			sql.append(gnmkdm);
			sql.append("%'");
		}
		return sql.toString();
	}
	
	/**
	 * 获取导出用户权限的查询语句
	 * @param model
	 * @return String
	 * */
	public String querryYhqx(CommanForm model){
		String yhm = model.getYhm();
		String gnmkdm = model.getGnmkdm();
		StringBuffer sql = new StringBuffer("select yhm,gnmkdm,gnmkmc,dxq,dyym,xssx,zdm from view_yhqx where 1=1 ");
		
		if(yhm != null && !"".equalsIgnoreCase(yhm)){
			sql.append(" and yhm='");
			sql.append(yhm);
			sql.append("'");
		}
		
		if(gnmkdm != null && !"".equalsIgnoreCase(gnmkdm)){
			sql.append(" and gnmkdm like '");
			sql.append(gnmkdm);
			sql.append("%'");
		}
		return sql.toString();
	}
	
	/**
	 * 根据部门名称查询部门id,查询不到id，插入一条新的记录，返回插入的id
	 * */
	public String getUserPartId(String bmmc, String unit,HttpServletRequest request){
		String result = "";
		String sql = "select bmdm from zxbz_xxbmdm where bmmc=?";
		result = dao.getOneRs(sql, new String[]{bmmc}, "bmdm");
		if(StringUtils.isNull(result)){//根据部门名称查询不到部门代码
			result = "xg" + System.currentTimeMillis();//生成部门代码
			String[] columns = {"bmdm","bmmc","bmjb","bmlb"};
			String[] values = {result,bmmc,"2",unit};//非学院的部门部门级别默认为2
			StandardOperation.insert("zxbz_xxbmdm", columns, values, request);
		}
		return result;
	}
	
	/**
	 * 根据单位名称查询部门id,查询不到id，插入一条新的记录，返回插入的id
	 * */
	public String getUserUnitId(String dwmc, HttpServletRequest request){
		String result = "";
		String sql = "select dwdm from bks_dwdmb where dwmc=?";
		result = dao.getOneRs(sql, new String[]{dwmc}, "dwdm");
		if(StringUtils.isNull(result)){//根据单位名称查询不到单位代码
			result = "xg" + System.currentTimeMillis();//生成部门代码
			String[] columns = {"dwdm","dwmc"};
			String[] values = {result,dwmc};
			StandardOperation.insert("bks_dwdmb", columns, values, request);
		}
		return result;
	}

}
