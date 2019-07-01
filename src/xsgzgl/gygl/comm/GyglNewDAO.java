package xsgzgl.gygl.comm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.xml.XMLReader;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.tsgl.TsglForm;

/**
 * Title: 学工管理系统
 * Description: 公寓管理_第三版_通用_DAO类
 * Copyright: Copyright (c) 2011
 * Company: zfsoft
 * @author zhang
 * @version 1.0
 */

public class GyglNewDAO extends CommDAO {
	DAO dao = DAO.getInstance();
	
	public HashMap<String,String> getStuInfo_gy(String xh){
		String sql = "select * from view_xsjbxx where xh=?";
		return dao.getMap(sql, new String[]{xh}, new String[]{"xh","xm","xymc","xydm","zymc","zydm","bjdm","bjmc","nj","xb"});
	}
	
	public List<HashMap<String, String>> getLdxxList(User user){
		StringBuffer sql = new StringBuffer();
		sql.append("select distinct t.lddm dm,t.ldmc mc from xg_gygl_new_ldxxb t");
		if ("yes".equalsIgnoreCase(user.getGyglyQx())) {
		 sql.append(" where exists(select 1 from xg_gygl_new_gyfdyb a");
		 sql.append(" where t.lddm = a.lddm and a.yhm='"+user.getUserName()+"')");
		}
		sql.append(" order by t.lddm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	/**
	 * 获取退宿原因list
	 * @return
	 */
	public List<HashMap<String, String>> getTsyyList(){
		String sql = "select * from xg_gygl_new_tsyydmb order by to_number(tsyydm)";		
		return dao.getList(sql, new String[]{},new String[]{"tsyydm","tsyymc"});
	}
	//获取调整原因
	public List<HashMap<String, String>> getTzyyList(){
		String sql = "select * from XG_GYGL_NEW_TZYYDMB order by to_number(tzyydm)";		
		return dao.getList(sql, new String[]{},new String[]{"tzyydm","tzyymc"});
	}
	/**
	 * 获取信息List，主要获取加载列表信息
	 * @author sjf
	 * @param tableName
	 * @param queryMap
	 * @param colList
	 * @return
	 */
	public List<HashMap<String, String>> getRsList(String tableName, Map<String, String> queryMap, String[] colList){
		StringBuilder query = new StringBuilder(" where 1=1 ");
		 
		List<String> inputList = new ArrayList<String>();
 		
		// 拼写where条件
		if(queryMap != null){
			for(Map.Entry<String, String> entry : queryMap.entrySet()){
				if(!"orderBy".equalsIgnoreCase(entry.getKey()) && StringUtils.isNotNull(entry.getValue())){
					query.append(" and ").append(entry.getKey()).append("=?");
					inputList.add(entry.getValue());
				}
			}
			
			if(StringUtils.isNotNull(queryMap.get("orderBy"))){
				query.append(" ").append(queryMap.get("orderBy"));
			}
		}
	
		return CommonQueryDAO.commonQueryforList(tableName, query.toString(), (String[])inputList.toArray(new String[]{}), colList, "");
	}

	/**
	 * 
	 * @描述: 新版学籍异动
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-17 下午04:15:41
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getTsxxNewSql(TsglForm model){
		
		String sql="select a.xh,a.lddm,a.ldmc,a.qsh,a.cwh,a.rzsj,a.rzczr," +
		"b.nj,b.xydm,b.zydm,b.bjdm,b.xymc,b.zymc,b.bjmc,b.xm,b.xb " +
		"from view_xg_gygl_new_cwxx a left join view_xsxxb b on a.xh=b.xh where ";
		
		String pkValue=null;
		String sql2 = "";
		if(Base.isNull(model.getXh())){
			sql2 = sql + " lddm||qsh||cwh=?";
			pkValue=model.getLddm();
		}else{
			sql2 = sql + " a.xh=?";
			pkValue=model.getXh();
		}
		
		Map<String,String> xscw=dao.getMapNotOut(sql2, new String[]{pkValue});
		String czsj = GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss");
		
		// SQL定義
		StringBuilder rs_sql = new StringBuilder();
		// 退宿
		if( model.getSsydlx()!=null && "00".equals(model.getSsydlx())){
			
			String sfcwcsh = "是".equals(model.getSfcwcsh())?"0":"1";
			rs_sql.append("insert into XG_GYGL_NEW_SSYD_SSYDJG ");
			rs_sql.append(" ( XH ,");
			rs_sql.append(" CZSJ ,");
			rs_sql.append(" XN ,");
			rs_sql.append(" XQ ,");
			rs_sql.append(" SSYDLX ,");
			rs_sql.append(" TSTZYY ,");
			rs_sql.append(" TSTZSJ ,");
			rs_sql.append(" TJSQRXM ,");//操作人姓名
			rs_sql.append(" BZ ,");
			rs_sql.append(" YDQLDDM ,");
			rs_sql.append(" YDQLDMC ,");
			rs_sql.append(" YDQQSH ,");
			rs_sql.append(" YDQCWH ,");
			rs_sql.append(" SFCWCSH ,");
			rs_sql.append(" SJLY ,");
			rs_sql.append(" YDQQSRZSJ )  ");
			rs_sql.append(" values ( ");
			rs_sql.append(" '"+ xscw.get("xh") + "', ");
			rs_sql.append(" '"+ czsj + "', ");
			rs_sql.append(" '"+ model.getXn() + "', ");
			rs_sql.append(" '"+ model.getXq() + "', ");
			rs_sql.append(" '"+ model.getSsydlx() + "' ,"); //调整类型
			rs_sql.append(" '"+ model.getTsyy() + "', ");//退宿调整原因代码
			rs_sql.append(" '"+ model.getTssj() + "', ");
			rs_sql.append(" '"+ model.getTsczr() +"', ");//操作人姓名
			rs_sql.append(" '"+ model.getBz() + "', ");
			rs_sql.append(" '"+ xscw.get("lddm") + "', ");
			rs_sql.append(" '"+ xscw.get("ldmc") + "', ");
			rs_sql.append(" '"+ xscw.get("qsh") + "', ");
			rs_sql.append(" '"+ xscw.get("cwh") + "', ");
			rs_sql.append(" '"+ sfcwcsh + "', ");
			rs_sql.append(" '0' ,"); //数据来源：0（结果库）
			rs_sql.append(" '"+ xscw.get("rzsj") + "' ");
			rs_sql.append(" ) ");
			
			// 宿舍调整
		}else if(model.getSsydlx()!=null && "01".equals(model.getSsydlx())){

			String sql3 = "";
			if(! Base.isNull(model.getYdhldqscw())){
				sql3 = sql + " lddm||qsh||cwh=?";
				pkValue=model.getYdhldqscw();
			}
			Map<String,String> ydhcw = dao.getMapNotOut(sql3, new String[]{pkValue});
			
			rs_sql.append("insert into XG_GYGL_NEW_SSYD_SSYDJG ");
			rs_sql.append(" ( XH ,");
			rs_sql.append(" CZSJ ,");
			rs_sql.append(" XN ,");
			rs_sql.append(" XQ ,");
			rs_sql.append(" SSYDLX ,");
			rs_sql.append(" TSTZYY ,");
			rs_sql.append(" TSTZSJ ,");
			rs_sql.append(" TJSQRXM ,");//操作人姓名
			rs_sql.append(" BZ ,");
			rs_sql.append(" YDQLDDM ,");
			rs_sql.append(" YDQLDMC ,");
			rs_sql.append(" YDQQSH ,");
			rs_sql.append(" YDQCWH ,");
			
			rs_sql.append(" YDHLDDM ,");
			rs_sql.append(" YDHLDMC ,");
			rs_sql.append(" YDHQSH ,");
			rs_sql.append(" YDHCWH ,");
			rs_sql.append(" YDHNJ ,");
			rs_sql.append(" YDHXYDM ,");
			rs_sql.append(" YDHZYDM ,");
			rs_sql.append(" YDHBJDM ,");
			
			rs_sql.append(" SFCWCSH ,");
			rs_sql.append(" SJLY ,");
			rs_sql.append(" YDQQSRZSJ )  ");
			rs_sql.append(" values ( ");
			rs_sql.append(" '"+ xscw.get("xh") + "', ");
			rs_sql.append(" '"+ czsj + "', ");
			rs_sql.append(" '"+ model.getXn() + "', ");
			rs_sql.append(" '"+ model.getXq() + "', ");
			rs_sql.append(" '"+ model.getSsydlx() + "' ,"); //调整类型
			rs_sql.append(" '"+ model.getTsyy() + "', ");//退宿调整原因代码
			rs_sql.append(" '"+ model.getTssj() + "', ");
			rs_sql.append(" '"+ model.getTsczr() +"', ");//操作人姓名
			rs_sql.append(" '"+ model.getBz() + "', ");
			rs_sql.append(" '"+ xscw.get("lddm") + "', ");
			rs_sql.append(" '"+ xscw.get("ldmc") + "', ");
			rs_sql.append(" '"+ xscw.get("qsh") + "', ");
			rs_sql.append(" '"+ xscw.get("cwh") + "', ");

			rs_sql.append(" '"+ ydhcw.get("lddm") + "', ");
			rs_sql.append(" '"+ ydhcw.get("ldmc") + "', ");
			rs_sql.append(" '"+ ydhcw.get("qsh") + "', ");
			rs_sql.append(" '"+ ydhcw.get("cwh") + "', ");
			rs_sql.append(" '"+ ydhcw.get("nj") + "', ");
			rs_sql.append(" '"+ ydhcw.get("xydm") + "', ");
			rs_sql.append(" '"+ ydhcw.get("zydm") + "', ");
			rs_sql.append(" '"+ ydhcw.get("bjdm") + "', ");
			
			rs_sql.append(" '0', "); //床位不初始化
			rs_sql.append(" '0' ,"); //数据来源：0（结果库）
			rs_sql.append(" '"+ xscw.get("rzsj") + "' ");
			rs_sql.append(" ) ");
		}
		
		return rs_sql.toString();
	}
	
	/**
	 * 获取退宿信息sql
	 * @param model
	 * @return
	 */
	public String getTsxxSql(TsglForm model){
		String sql="select a.xh,a.lddm,a.ldmc,a.qsh,a.cwh,a.rzsj,a.rzczr," +
		"b.nj,b.xydm,b.zydm,b.bjdm,b.xymc,b.zymc,b.bjmc,b.xm,b.xb " +
		//"from view_xg_gygl_new_cwxx a left join view_xsjbxx b on a.xh=b.xh where ";//增加对批量退宿的支持
		"from view_xg_gygl_new_cwxx a left join view_xsxxb b on a.xh=b.xh where ";
		
		String pkValue=null;
		if(Base.isNull(model.getXh())){
			sql+=" lddm||qsh||cwh=?";
			pkValue=model.getLddm();
		}else{
			sql+=" a.xh=?";
			pkValue=model.getXh();
		}
		
		Map<String,String> xscw=dao.getMapNotOut(sql, new String[]{pkValue});
		
		String rs_sql="insert into xg_gygl_new_tsxxb(xh,ldmc,qsh,cwh,tsyy,tssj,bz,tsczr,lddm,nj,xydm,zydm,bjdm," +
				"rzsj,rzczr,xymc,zymc,bjmc,xm,xb) " +
				"values('"+xscw.get("xh")+"','"+xscw.get("ldmc")+"','"+xscw.get("qsh")+"','"+xscw.get("cwh")+"'," +
				"'"+Base.chgNull(model.getTsyy(),"", 0)+"','"+Base.chgNull(model.getTssj(), "", 0)+"'," +
				"'"+Base.chgNull(model.getBz(), "", 0)+"','"+Base.chgNull(model.getTsczr(), "", 0)+"'," +
				"'"+xscw.get("lddm")+"','"+xscw.get("nj")+"','"+xscw.get("xydm")+"','"+xscw.get("zydm")+"'," +
				"'"+xscw.get("bjdm")+"','"+xscw.get("rzsj")+"','"+xscw.get("rzczr")+"','"+xscw.get("xymc")+"'," +
				"'"+xscw.get("zymc")+"','"+xscw.get("bjmc")+"','"+xscw.get("xm")+"','"+xscw.get("xb")+"')";

		return rs_sql;
	}
	
	/**
	 * 获取公寓管理员的条件
	 * @param myForm
	 * @return
	 */
	public String getSearchTjByGyfdy(HttpServletRequest request){		
		String gyglyQx = (String)request.getSession().getAttribute("gyglyQx");
		String username = (String)request.getSession().getAttribute("userName");
		String gygly_path = XMLReader.getFlowControl("gygl", "gygly_path");
		String path = (String)request.getAttribute("path");
		if(path!=null && gygly_path.contains(path) && "yes".equalsIgnoreCase(gyglyQx)){
			return " and lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '"+username+"')";
		}else{
			return "";
		}
	}
	/**
	 * 获取公寓管理员的条件（not in）
	 */
	public String getSearchTjByGyfdyNotIn(HttpServletRequest request){		
		String gyglyQx = (String)request.getSession().getAttribute("gyglyQx");
		String username = (String)request.getSession().getAttribute("userName");
		String gygly_path = XMLReader.getFlowControl("gygl", "gygly_path");
		String path = (String)request.getAttribute("path");
		if(path!=null && gygly_path.contains(path) && "yes".equalsIgnoreCase(gyglyQx)){
			return " and lddm not in (select lddm from xg_gygl_new_gyfdyb where yhm = '"+username+"')";
		}else{
			return "";
		}
	}
	/**
	 * 获取党团信息相关学生信息
	 * @param myForm
	 * @return
	 */
	public String getSearchTjForDtxx(HttpServletRequest request){		
		String gyglyQx = (String)request.getSession().getAttribute("gyglyQx");
		String username = (String)request.getSession().getAttribute("userName");
		String gygly_path = XMLReader.getFlowControl("gygl", "gygly_path");
		String path = (String)request.getAttribute("path");
		if(path!=null && gygly_path.contains(path) && "yes".equalsIgnoreCase(gyglyQx)){
			return " and lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '"+username+"')";
		}else{
			return "";
		}
	}
	public HashMap<String,String> getGyglCssz(){
		DAO dao = DAO.getInstance();
		HashMap<String,String> map=new HashMap<String, String>();
		String sql="select csdm,csz from xg_gygl_new_jbszb";
		List<String[]> list=dao.rsToVator(sql, new String[]{}, new String[]{"csdm","csz"});
		if(list!=null&&list.size()>0){
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				String[] strs = (String[]) iterator.next();
				map.put(strs[0], strs[1]);
			}
		}
		
		
		return map;
	}
	
	/**
	 * 执行存储过程
	 * @param String sql
	 * @return boolean
	 */
	public boolean runPro(String sql){
		DAO dao=DAO.getInstance();
		try {
			return dao.runProcuder(sql, new String[]{});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<String[]> getStuGyxxList(String xh,String[] out) {
		DAO dao=DAO.getInstance();
		
		List<String[]> rs = new ArrayList<String[]>();
		rs = CommonQueryDAO.commonQueryNotFy("", "", new String[]{xh}, out, 
				"select * from view_xg_gygl_new_cwxx where xh = ?");
		
		return rs;
	}
	
	/**
	 * 获取入住原因list
	 * @return
	 */
	public List<HashMap<String, String>> getRzyyList(){
		String sql = "select * from xg_gygl_new_rzyydmb order by rzyydm";		
		return dao.getList(sql, new String[]{},new String[]{"rzyydm","rzyymc"});
	}
}