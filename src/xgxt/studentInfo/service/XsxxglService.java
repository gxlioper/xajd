package xgxt.studentInfo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.DAO.GetDropDownList;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.search.SearchService;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.form.SaveForm;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.studentInfo.dao.StudentInfoDao;
import xgxt.studentInfo.dao.XsxxglDAO;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.studentInfo.model.XsxxpzModel;
import xgxt.studentInfo.zgdzdx.XsxxZgdzdxDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import common.Globals;
import common.GlobalsVariable;

public class XsxxglService {
	XsxxglDAO dao = new XsxxglDAO();  
	StuInfoDAO stuDao = new StuInfoDAO();
	String xxdm = StandardOperation.getXxdm();
	
	public String getTableName(){
		HashMap<String, String> map = new HashMap<String, String>();
		return map.get(xxdm);
	}
	
	public String getXqmc(String xqmc){
		return dao.selectXqmc(xqmc);
	}
	
	/**
	 * 获取要导出的列
	 * @param tableName
	 * @return List
	 * */	  
	public List<HashMap<String, String>> getColumn2Export(String tableName){		 
//		StudentInfoService service = new StudentInfoService();
		GetDropDownList util = new GetDropDownList();
//		String sql = service.getXsxxToExp();		
//		return dao.getColumn2ExpList(sql,tableName);
		return util.getTableColForExp("view_xsxxb");
	}
	
	/**
	 * 组合查询表字段的查询语句
	 * @param tableName
	 * @param key
	 * @param form
	 * @return String
	 * */
	public String getColumnSql2Exp(String tableName,String[]key,CommanForm form){
		String sql = "select ";
		StringBuffer column = new StringBuffer();//查询字段		
		
		for(int i=0;i<key.length; i++){//组合要导出的具体字段
			if(key[i].toLowerCase().equalsIgnoreCase("sfyby")){
				column.append(" decode(sfyby,'','否',sfyby) sfyby");
			}else if(key[i].toLowerCase().equalsIgnoreCase("sfzx")){
				column.append(" decode(sfzx,'','在校',sfzx) sfzx");
			}else{
				column.append(key[i]);
			}
			column.append(",");
		}
		column.deleteCharAt(column.length()-1);//去除最后的","号	
		sql += column;
		sql += " from " + tableName + " a ";

		return sql;
	}
	
	/**
	 * 学生信息导出页面条件获取
	 * @param model
	 * @param isFdy
	 * @return String
	 * */
	public String getWhereSql(CommanForm model,String isFdy){
		String xxdm = StandardOperation.getXxdm();
		String userName = model.getUserName();
		StringBuffer whereSql = new StringBuffer(" where 1=1 ");
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xh = model.getXh();
		String xm = DealString.toGBK(model.getXm());
		String xb = DealString.toGBK(model.getXb());
		String mzdm = DealString.toGBK(model.getMzdm());
		String zzmmdm = DealString.toGBK(model.getZzmmdm());
		String jtcyxm = DealString.toGBK(model.getJtcyxm());
		String ssbh = DealString.toGBK(model.getSsbh());
		String sfzh = model.getSfzh();
		String xjztm = DealString.toGBK(model.getXjzt());
		String xz = model.getXz();
		String jg = DealString.toGBK(model.getJg());
		String syd = DealString.toGBK(model.getSyd());
		String rxsj = model.getRxsj();
		String byny = model.getByny();
		String zw = DealString.toGBK(model.getZw());
		String pycc = DealString.toGBK(model.getPycc());
		String sfzx = DealString.toGBK(model.getSfzx());
		String hkszd = DealString.toGBK(model.getHkszd());
		String sfyby = DealString.toGBK(model.getSfyby());
		String fdyQx = model.getFdyQx();
		
		//String sfzx = DealString.toGBK(model.getSfzx());
		
		
		if(fdyQx != null && fdyQx.equalsIgnoreCase("true")){
			whereSql.append(" and exists(select 1 from view_fdybbj f where a.bjdm=f.bjdm and f.zgh='"+userName+"')");
		}
		
		//通用条件start
		if(nj!=null && !nj.equals("")){//年级
			whereSql.append(" and nj='");
			whereSql.append(nj);
			whereSql.append("'");
		}
		if(xydm!=null && !xydm.equals("")){//学院代码
			whereSql.append(" and xydm='");
			whereSql.append(xydm);
			whereSql.append("'");
		}
		if(zydm!=null && !zydm.equals("")){//专业代码
			whereSql.append(" and zydm='");
			whereSql.append(zydm);
			whereSql.append("'");
		}
		if(bjdm!=null && !bjdm.equals("")){//班级代码
			whereSql.append(" and bjdm='");
			whereSql.append(bjdm);
			whereSql.append("'");
		}
		if(xh!=null && !xh.equals("")){//学号
			whereSql.append(" and xh like '%");
			whereSql.append(xh);
			whereSql.append("%'");
		}
		if(xm!=null && !xm.equals("")){//姓名
			whereSql.append(" and xm like '%");
			whereSql.append(xm);
			whereSql.append("%' ");
		}
		if(xb!=null && !xb.equals("")){//性别
			whereSql.append(" and xb ='");
			whereSql.append(xb);
			whereSql.append("' ");
		}
		if(mzdm!=null && !mzdm.equals("")){//民族代码
			whereSql.append(" and mzdm ='");
			whereSql.append(mzdm);
			whereSql.append("' ");
		}
		if(zzmmdm!=null && !zzmmdm.equals("")){//政治面貌代码
			whereSql.append(" and zzmmdm ='");
			whereSql.append(zzmmdm);
			whereSql.append("' ");
		}
		if(jtcyxm!=null && !jtcyxm.equals("")){//家庭成员姓名
			whereSql.append(" and (jtcy1_xm ='");
			whereSql.append(jtcyxm);
			whereSql.append("' ");
			whereSql.append(" or jtcy2_xm ='");
			whereSql.append(jtcyxm);
			whereSql.append("' ");
			whereSql.append(" or jtcy3_xm ='");
			whereSql.append(jtcyxm);
			whereSql.append("') ");
		}
		if(ssbh!=null && !ssbh.equals("")){//宿舍编号
			whereSql.append(" and ssbh like '%");
			whereSql.append(ssbh);
			whereSql.append("%' ");
		}
		if(hkszd!=null && !hkszd.equals("")){//户口所在地
			whereSql.append(" and hkszd ='");
			whereSql.append(hkszd);
			whereSql.append("' ");
		}
		if(sfzh!=null && !sfzh.equals("")){//身份证号
			whereSql.append(" and sfzh like'%");
			whereSql.append(sfzh);
			whereSql.append("%' ");
		}		
		if(xz!=null && !xz.equals("")){//学制
			whereSql.append(" and xz='");
			whereSql.append(xz);
			whereSql.append("' ");
		}
		if(jg!=null && !jg.equals("")){//籍贯
			whereSql.append(" and jg like'%");
			whereSql.append(jg);
			whereSql.append("%' ");
		}
		if(syd!=null && !syd.equals("")){//生源地 云南艺术
			whereSql.append(" and syd ='");
			whereSql.append(syd);
			whereSql.append("' ");
		}
		if(rxsj!=null && !rxsj.equals("")){//入学时间
			whereSql.append(" and rxrq ='");
			whereSql.append(rxsj);
			whereSql.append("' ");
		}
		if(byny!=null && !byny.equals("")){//毕业年月
			whereSql.append(" and byny ='");
			whereSql.append(byny);
			whereSql.append("' ");
		}
		if(zw!=null && !zw.equals("")){//职务 云南艺术
			whereSql.append(" and zw ='");
			whereSql.append(zw);
			whereSql.append("' ");
		}
		if(pycc!=null && !pycc.equals("")){//培养层次
			whereSql.append(" and pycc='");
			whereSql.append(pycc);
			whereSql.append("' ");
		}		
		if(xjztm!=null && !xjztm.equals("")){//学籍状态码 
			if(xxdm.equals(Globals.XXDM_XBMZDX)){//西北民族大学
				if(xjztm.equals("有学籍")){
					whereSql.append(" and (xjlb='");
					whereSql.append(xjztm);
					whereSql.append("' or xjlb='有' or xjlb is null)");
				}else if(xjztm.equals("无学籍")){
					whereSql.append(" and (xjlb='");
					whereSql.append(xjztm);
					whereSql.append("' or xjlb='无')");
				}					
			}else{//其它学校
				whereSql.append(" and xjlb='");
				whereSql.append(xjztm);
				whereSql.append("' ");
			}
		}
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){//宁波理工
			whereSql.append("and (xjztm<>'无学籍' and xjztm is not null)");
		}
		if(!xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){//非杭州职业技术学院
			if(sfyby!=null && !sfyby.equals("")){//是否已毕业
				if(sfyby.equalsIgnoreCase("否")){
					whereSql.append(" and (sfyby ='否' or sfyby is null)");
				}else{
					whereSql.append(" and sfyby='是'");
				}
			}
			if(sfzx!=null && !sfzx.equals("")){//是否在校
				if(sfzx.equalsIgnoreCase("在校")){
					whereSql.append(" and (sfzx ='在校' or sfzx is null)");
				}else{
					whereSql.append(" and sfzx='不在校'");
				}
			}
		}
		//通用条件end
		
		//学校特殊条件 start
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){//杭州院
			whereSql.append(" and (sfyby<>'是' or sfyby is null )");//未毕业的学生
			whereSql.append(" and not exists(select 1 from view_xjydjbxx b where b.xh=view_xsxxb.xh and ydxh=(select max(ydxh) from view_xjydjbxx b where b.xh=view_xsxxb.xh) and " +
					"exists(select 1 from dm_ydlb c where c.sfzx='不在校' and b.ydlbmc=c.ydlbmc))");//异动后不在校的学生			
		}		
		
		if(xxdm.equals(Globals.XXDM_XBMZDX)){//西北民族大学
			if(sfzx!=null && sfzx.equalsIgnoreCase("1")){	
				//在校学生 
				whereSql.append(" and exists(select xh from (select xh,xm,decode(nvl((select (select sfzx from dm_ydlb m where b.ydlbdm=m.ydlbm) sfzx from view_xjydjbxx b where  f.xh=b.xh and b.ydxh=(select max(ydxh) from view_xjydjbxx c where b.xh=c.xh) and exists (select c.ydlbmc from dm_ydlb c where  b.ydlbmc=c.ydlbmc)),f.sfzx),null,'在校',nvl((select (select sfzx from dm_ydlb m where b.ydlbdm=m.ydlbm) sfzx from view_xjydjbxx b where  f.xh=b.xh and b.ydxh=(select max(ydxh) from view_xjydjbxx c where b.xh=c.xh) and exists (select c.ydlbmc from dm_ydlb c where  b.ydlbmc=c.ydlbmc)),f.sfzx)) sfzx from view_xsxxb f) f where f.sfzx='在校' and view_xsxxb.xh=f.xh)");
			}
			if(sfzx!=null && sfzx.equalsIgnoreCase("0")){
				//不在校
				whereSql.append(" and exists(select xh from (select xh,xm,decode(nvl((select (select sfzx from dm_ydlb m where b.ydlbdm=m.ydlbm) sfzx from view_xjydjbxx b where  f.xh=b.xh and b.ydxh=(select max(ydxh) from view_xjydjbxx c where b.xh=c.xh) and exists (select c.ydlbmc from dm_ydlb c where  b.ydlbmc=c.ydlbmc)),f.sfzx),null,'在校',nvl((select (select sfzx from dm_ydlb m where b.ydlbdm=m.ydlbm) sfzx from view_xjydjbxx b where  f.xh=b.xh and b.ydxh=(select max(ydxh) from view_xjydjbxx c where b.xh=c.xh) and exists (select c.ydlbmc from dm_ydlb c where  b.ydlbmc=c.ydlbmc)),f.sfzx)) sfzx from view_xsxxb f) f where f.sfzx='不在校' and view_xsxxb.xh=f.xh)");
			}	
		}
		
		if(xxdm.equals(Globals.XXDM_WHLGDX)){//武汉理工大学
			if(isFdy!=null && isFdy.equals("true")){//辅导员
				whereSql.append("and exists(select 1 from view_fdybbj b where b.bjdm=view_xsxxb.bjdm and b.zgh='" + userName + "')");
			}			
		}
		//学校特殊条件 end
		//组合查询条件语句end
		return whereSql.toString();
	}
	
	/**
	 * 保存学生信息信息发布信息
	 * @param title
	 * @param content
	 * @param request
	 * @return boolean
	 * @throws Exception
	 * */
	public boolean saveStuInfoPub(String title, String content, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String tableName = "xsxx_xxfbb";
		flag = StandardOperation.delete(tableName, "title", title, request);
		if(flag){
			flag = StandardOperation.insert(tableName, new String[]{"title", "content"}, new String[]{title,content},request);
		}		
		return flag;
	}
	
	/**
	 * 初始化毕业年份
	 * @param condition
	 * @param request
	 * @return boolean
	 * */
	public boolean saveByny(CommanForm model, HttpServletRequest request) throws Exception{
		String sql = "update xsxxb set byny = (to_number(nj) + to_number(xz))||'-" + model.getYf() + "-" + model.getRq() + "' where 1=1 ";
		StringBuffer sb = new StringBuffer();
		
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xz = model.getXz();
		
		sb.append(nj != null && !"".equalsIgnoreCase(nj) ? " and nj='" + nj + "'" : "");
		sb.append(xydm != null && !"".equalsIgnoreCase(xydm) ? " and xydm='" + xydm + "'" : "");
		sb.append(zydm != null && !"".equalsIgnoreCase(zydm) ? " and zydm='" + zydm + "'" : "");
		sb.append(bjdm != null && !"".equalsIgnoreCase(bjdm) ? " and bjdm='" + bjdm + "'" : "");
		sb.append(xz != null && !"".equalsIgnoreCase(xz) ? " and xz='" + xz + "'" : "");
		
		sql += sb.toString();
		
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		sql += searchTj;
		//String searchTjByUser = searchService.getSearchTjByUser(request, "a", "xydm", "bjdm");
		request.setAttribute("searchTj", model.getSearchModel());

		//SearchService searchService = new SearchService();
		//String searchTjByUser = searchService.getSearchTjByUser(request, "a", "xydm", "bjdm");
	
		//将xsxxb中不存在的学生插入到学生信息表中
		StandardOperation.update("xsxxb", "insert into xsxxb(xh,xm,xb,xydm,zydm,bjdm,nj,xz,xjztm,ssbh,mz,zzmm,qsdh,ssch,rzrq,zsjzrq,syd,csrq,pycc,rxrq,jg,hkszd,sfzx,lxdh,sjhm,jtdh,jtdz,jtyb,sfzh,dzyx)(select xh,xm,xb,xydm,zydm,bjdm,nj,xz,xjztm,ssbh,mz,zzmm,qsdh,ssch,zsrq,zsjzrq,syd,csrq,pycc,rxrq,jg,hkszd,sfzx,lxdh,sjhm,jtdh,jtdz,jtyb,sfzh,dzyx from view_xsxxb a where not exists(select 1 from xsxxb b where a.xh=b.xh))" , request);
		
		DAO dao = DAO.getInstance();
		boolean flag = dao.runUpdate(sql, inputV);
		
		return flag;
	}
	
	/**
	 * 初始化毕业生
	 * @param CommanForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * */
	public boolean saveBys(CommanForm model, HttpServletRequest request) throws Exception{
		String sql = "update xsxxb set sfbys = '是' where 1=1 ";
		StringBuffer sb = new StringBuffer();
		
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xz = model.getXz();
		
		sb.append(nj != null && !"".equalsIgnoreCase(nj) ? " and nj='" + nj + "'" : "");
		sb.append(xydm != null && !"".equalsIgnoreCase(xydm) ? " and xydm='" + xydm + "'" : "");
		sb.append(zydm != null && !"".equalsIgnoreCase(zydm) ? " and zydm='" + zydm + "'" : "");
		sb.append(bjdm != null && !"".equalsIgnoreCase(bjdm) ? " and bjdm='" + bjdm + "'" : "");
		sb.append(xz != null && !"".equalsIgnoreCase(xz) ? " and xz='" + xz + "'" : "");
		
		sql += sb.toString();
		//将xsxxb中不存在的学生插入到学生信息表中
		StandardOperation.update("xsxxb", "insert into xsxxb(xh,xm,xb,xydm,zydm,bjdm,nj,xz,xjztm,ssbh,mz,zzmm,qsdh,ssch,rzrq,zsjzrq,syd,csrq,pycc,rxrq,jg,hkszd,sfzx,lxdh,sjhm,jtdh,jtdz,jtyb,sfzh,dzyx)(select xh,xm,xb,xydm,zydm,bjdm,nj,xz,xjztm,ssbh,mz,zzmm,qsdh,ssch,zsrq,zsjzrq,syd,csrq,pycc,rxrq,jg,hkszd,sfzx,lxdh,sjhm,jtdh,jtdz,jtyb,sfzh,dzyx from view_xsxxb a where not exists(select 1 from xsxxb b where a.xh=b.xh))" , request);
		
		return StandardOperation.update("xsxxb", sql, request);
	}
	
	/**
	 * 按选择的学生初始化毕业年份
	 * @param pk
	 * @param request
	 * @return boolean
	 * */
	public boolean saveBynyByData(CommanForm model, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String pk = model.getPkValue();
		String yf = model.getYf();
		String rq = model.getSj();
		String[] xh = pk.split("!!");
		String message = "";
		//将xsxxb中不存在的学生插入到学生信息表中
		StandardOperation.update("xsxxb", "insert into xsxxb(xh,xm,xb,xydm,zydm,bjdm,nj,xz,xjztm,ssbh,mz,zzmm,qsdh,ssch,rzrq,zsjzrq,syd,csrq,pycc,rxrq,jg,hkszd,sfzx,lxdh,sjhm,jtdh,jtdz,jtyb,sfzh,dzyx)(select xh,xm,xb,xydm,zydm,bjdm,nj,xz,xjztm,ssbh,mz,zzmm,qsdh,ssch,zsrq,zsjzrq,syd,csrq,pycc,rxrq,jg,hkszd,sfzx,lxdh,sjhm,jtdh,jtdz,jtyb,sfzh,dzyx from view_xsxxb a where not exists(select 1 from xsxxb b where a.xh=b.xh))" , request);
		
		for(int i=0; i<xh.length; i++){
			if(xh[i] != null && !"".equalsIgnoreCase(xh[i])){
				flag = StandardOperation.update("xsxxb", "update xsxxb set byny = (to_number(nj) + to_number(xz))||'-" + yf + "-" + rq + "' where xh='"+xh[i]+"' ", request);
				if(!flag){
					if(message == ""){
						message += i+1;
					}else{
						message += "," + (i+1);
					}					 
				}
			}
		}
		
		if(message != null && !"".equalsIgnoreCase(message)){
			request.setAttribute("mes", "操作失败的记录：\n第" + message + "条记录！");
		}
		return flag;
	}
	
	/**
	 * 按选择的学生初始化毕业生
	 * @param String pk
	 * @param HttpServletRequest request
	 * @return boolean
	 * */
	public boolean saveBysByData(String pk, HttpServletRequest request) throws Exception{
		boolean flag = false;
		String[] xh = pk.split("!!");
		String message = "";
		//将xsxxb中不存在的学生插入到学生信息表中
		StandardOperation.update("xsxxb", "insert into xsxxb(xh,xm,xb,xydm,zydm,bjdm,nj,xz,xjztm,ssbh,mz,zzmm,qsdh,ssch,rzrq,zsjzrq,syd,csrq,pycc,rxrq,jg,hkszd,sfzx,lxdh,sjhm,jtdh,jtdz,jtyb,sfzh,dzyx)(select xh,xm,xb,xydm,zydm,bjdm,nj,xz,xjztm,ssbh,mz,zzmm,qsdh,ssch,zsrq,zsjzrq,syd,csrq,pycc,rxrq,jg,hkszd,sfzx,lxdh,sjhm,jtdh,jtdz,jtyb,sfzh,dzyx from view_xsxxb a where not exists(select 1 from xsxxb b where a.xh=b.xh))" , request);
		
		for(int i=0; i<xh.length; i++){
			if(xh[i] != null && !"".equalsIgnoreCase(xh[i])){
				flag = StandardOperation.update("xsxxb", "update xsxxb set sfbys = '是' where xh='"+xh[i]+"' ", request);
				if(!flag){
					if(message == ""){
						message += i+1;
					}else{
						message += "," + (i+1);
					}					 
				}
			}
		}
		
		if(message != null && !"".equalsIgnoreCase(message)){
			request.setAttribute("mes", "操作失败的记录：\n第" + message + "条记录！");
		}
		return flag;
	}
	
	/**
	 * 查询学生个人信息
	 * @param String xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectStuinfo(String xh){
		return dao.selectStuDetail(xh);
	}
	
	/**
	 * 查询学生个人公寓信息
	 * @param String xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectStuinfoGy(String xh){
		return dao.selectStuDetailGy(xh);
	}	
	
	/**
	 * 获取学生相关信息
	 * @param xh
	 * @param tableName
	 * @param colList1
	 * @return List
	 * */
	public List<String[]> getInfoOfStu(String xh,String tableName,String[] colList1){
		return stuDao.getAllOfInfoByPrint(xh, tableName, colList1);
	}
	
	/**
	 * 获取学生家庭信息
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuJtxx(String xh){
		HashMap<String, String> map = stuDao.getXsjtxx(xh);
		//将家庭成员出生日期转换成年龄
		map.put("jtcy1_nl", changeNl(map.get("jtcy1_csrq")));
		map.put("jtcy2_nl", changeNl(map.get("jtcy2_csrq")));
		map.put("jtcy3_nl", changeNl(map.get("jtcy3_csrq")));
		return map;
	}
	
	public List<HashMap<String, String>> getTabCNofXszz(){		
		return stuDao.getTabCNofXszz(xxdm);
	}
	
	public String[] getColOfXszz(String table){
		return stuDao.getColOfXszz(xxdm, table);
	}
	
	public List<String[]> getXszzInfo(String xh, String table){		
		return stuDao.getXszzInfo(xh, xxdm, table);
	}
	
	/**
	 * 中国矿业大学获取综合素质测评信息
	 * @param xh
	 * @param colList
	 * @return List
	 * */
	public List<String[]> getZhszcpxx(String xh,String[] colList) throws Exception{
		return dao.getZhszcpxx(xh, colList);
	}
	
	/**
	 * 中国矿业大学获取党员信息
	 * @param xh
	 * @return List
	 * */
	public List<String[]> getDyxx(String xh){
		return dao.getDyxx(xh);
	}
	
	/**
	 * 获取页面选择的条件
	 * @param whereSql
	 * @return String
	 * */
	public String getCondition(String whereSql){
		String[] keys = whereSql.split("@");
		StringBuffer condition = new StringBuffer(" where 1=1 ");
		for(int i=0; i<keys.length; i++){
			String[] value = keys[i].split("!!"); 
			if(value != null && value.length>1){
				String key = value[0];
				String val = value[1];
				if(key.equalsIgnoreCase("nfby")){
					if(!StringUtils.isNull(val) && "yes".equalsIgnoreCase(val)){
						val = "是";
					}
				}
				if(key.equalsIgnoreCase("jtcyxm")){
					condition.append(" and  exists(select 1 from view_xsjtxx b where a.xh=b.xh and (b.jtcy1_xm like '%");
					condition.append(val + "%' or jtcy2_xm like '%");
					condition.append(val + "%' or jtcy3_xm like '%");
					condition.append(val + "%'))");
				}else if(key.equalsIgnoreCase("jtnsrd") || key.equalsIgnoreCase("jtnsrg")){
					condition.append(" and exists(select 1 from view_xsjtxx b where a.xh=b.xh and exists(select jtzsr from (select xh,to_number(jtzsr)jtzsr from view_xsjtxx where trim(jtzsr) is not null) c where b.xh=c.xh ");
					if(key.equalsIgnoreCase("jtnsrd")){
						condition.append(" and to_number(jtzsr)>= ");
						condition.append(val);
					}
					if(key.equalsIgnoreCase("jtnsrg")){
						condition.append(" and to_number(jtzsr)<=");
						condition.append(val);
					}
					condition.append("))");
				}else if(key.equalsIgnoreCase("jgshen") || key.equalsIgnoreCase("jgshi") || key.equalsIgnoreCase("jgxian")){
					condition.append(" and jg like '%");
					condition.append(val.replace("'", "‘") + "%'");
				}else if(key.equalsIgnoreCase("sydshen") || key.equalsIgnoreCase("sydshi") || key.equalsIgnoreCase("sydxian")){
					condition.append(" and syd like '%");
					condition.append(val.replace("'", "‘") + "%'");
				}else if(key.equalsIgnoreCase("hkszdshen") || key.equalsIgnoreCase("hkszdshi") || key.equalsIgnoreCase("hkszdxian")){
					condition.append(" and hkszd like '%");
					condition.append(val.replace("'", "‘") + "%'");
				}else if("sfzxk".equalsIgnoreCase(key) && "no".equalsIgnoreCase(val.replace("'", "‘"))){//区分是否是在校库
					condition.append(" and (sfzx='不在校' or sfyby='是')");
				}else{
					condition.append(" and ");
					condition.append(key);
					
					if(key.equalsIgnoreCase("xh") 
							||key.equalsIgnoreCase("xm") 
							|| key.equalsIgnoreCase("sfzh")
							|| key.equalsIgnoreCase("ssbh")
							|| key.equalsIgnoreCase("jg")
							|| key.equalsIgnoreCase("pycc")
							|| key.equalsIgnoreCase("zw")
							|| key.equalsIgnoreCase("csrq")
							|| key.equalsIgnoreCase("syd")
							|| key.equalsIgnoreCase("byny")
							|| key.equalsIgnoreCase("rxrq")
							|| key.equalsIgnoreCase("sjhm")
							|| key.equalsIgnoreCase("hkszd")
							|| key.equalsIgnoreCase("ksh")
							|| key.equalsIgnoreCase("xz")){//模糊查询字段
						condition.append( " like '%" );
						condition.append(val);
						condition.append("%'");
					}else{
						condition.append( " = '" );
						condition.append(val);
						condition.append("'");
					}
			}
		}
		}
		//区分是否是在校库
		if(!whereSql.contains("sfzxk") && !Base.xxdm.equals(Globals.XXDM_ZGDZDX)){
			condition.append(" and (sfyby='否' or sfyby is null) and (sfzx='在校' or sfzx is null)");
		}
		return condition.toString();
	}
	
	/**
	 * 查询班级的父级信息
	 * @param bjdm
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryBjParentInfo(String bjdm){
		XsxxglDAO dao = new XsxxglDAO();
		return dao.queryBjParentInfo(bjdm);
	}
	
	/**
	 * 获取学制列表
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXzList(){
		return dao.getXzList();
	}
	
	/**
	 * 获取学生成绩报告单信息查询表头
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getCjbgdxxToptr(){
		String[] colList = new String[]{"主键","xn","xqmc","xh","xm","xb","nj","bjmc","yskts","sskts","bingjia","shijia","chidao"};
		String[] colListCN = dao.getColumnNameCN(colList, "view_hzzy_cjbgdxxb");
		return dao.arrayToList(colList, colListCN);
	}
	
	/**
	 * 查询学生成绩报告单信息
	 * @param CommanForm model
	 * @return List<String[]>
	 * @throws Exception
	 * */
	public List<String[]> queryXscjbgdxx(CommanForm model) throws Exception{
		return dao.selectXscjbgdxx(model);
	}
	
	/**
	 * 查询单条学生成绩报告单信息
	 * @param String pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryXscjbgdxxOne(String pkValue){
		return dao.selectXscjbgdxxOne(pkValue);
	}
	
	/**
	 * 保存学生成绩报告单信息
	 * @param CommanForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean saveXscjbgdxx(CommanForm model, HttpServletRequest request) throws Exception{
		boolean result = false;
		String tableName = "view_hzzy_cjbgdxxb";
		String pk = "xn||xq||xh";
		String pkValue = model.getXn()+DealString.toGBK(model.getXq())+DealString.toGBK(model.getXh());
		
		String[] columns = new String[]{"xn","xq","xh","yskts","sskts","bingjia","shijia","kuangke","chidao","qita","zhszpd","bzr","bzrlxdh"};
		String[] values = FormModleCommon.modelToStrings(columns, model);
		if(dao.checkExists(tableName, pk,pkValue)){	
			//修改
			result = StandardOperation.update("hzzy_cjbgdxxb", columns, values, pk, pkValue, request);
		}else{
			//增加
			result = StandardOperation.insert("hzzy_cjbgdxxb", columns, values, request);
		}
		return result;
	}
	
	/**
	 * 删除学生成绩上报表信息
	 * @param CommanForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * */
	public boolean delXscjbgdxx(CommanForm model){
		boolean result = true;
		String[] value = model.getPkV();
		String[] sqlArr = new String[value.length];
		for(int i=0; i<value.length; i++){
			sqlArr[i] = "delete from hzzy_cjbgdxxb where xn||xq||xh ='" + value[i] + "'";
		}
		try{
			dao.runBatch(sqlArr);
		}catch(Exception e){
			e.printStackTrace();
			result = false;
		}
		
		return result;
	}
	
	/**
	 * 查询成绩报告单打印时间信息
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectCjbgddysjxx(){
		return dao.selectCjbgddysjxx();
	}
	
	/**
	 * 获取省
	 * */
	public List<HashMap<String, String>> getSsList(){
		return stuDao.getSsList();
	}
	
	/**
	 * 获取市/县
	 * @param String shenId
	 * @return HashMap<String, List<HashMap<String, String>>>
	 * */
	public HashMap<String, List<HashMap<String, String>>> getShiXianList(String shenId){
		return stuDao.getShiList(shenId);
	}
	
	/**
	 * 初始化学籍信息列表
	 * @param type
	 * @return List<HashMap<String,String>>
	 * */
	public List<HashMap<String,String>> initXjxxList(String type){		
		if("yhzk".equalsIgnoreCase(type)){//婚姻状况
			return stuDao.getHyzkList();
		}else if("gb".equalsIgnoreCase(type)){//国别
			return stuDao.getGbList();
		}else if("xq".equalsIgnoreCase(type)){//校区
			return dao.getWhList("dm_zju_xq", "dm", "xqmc", "", "", "");			
		}else if("xslb".equalsIgnoreCase(type)){//学生类别
			return dao.getWhList("dmk_xslb", "lbdm", "lbmc","","", "");
		}else if("zjlx".equalsIgnoreCase(type)){//证件类型
			return dao.getWhList("dmk_zjlx", "zjlxdm", "zjlxmc","","", "");
		}else if("gatq".equalsIgnoreCase(type)){//港澳台侨
			return dao.getWhList("dmk_gatq", "gatqdm", "gatqmc","","", "");
		}else if("zykl".equalsIgnoreCase(type)){//专业科类
			return dao.getWhList("dmk_zykl", "zykldm", "zyklmc","","", "");
		}else if("pylx".equalsIgnoreCase(type)){//培养类型
			return dao.getWhList("dmk_pylx", "pylxdm", "pylxmc","","", "");
		}else if("xxxs".equalsIgnoreCase(type)){//学习形式
			return dao.getWhList("dmk_xxxs", "xxxsdm", "xxxsmc","","", "");
		}else if("hdxlfs".equalsIgnoreCase(type)){//获得学历方式
			return dao.getWhList("dmk_hdxlfs", "hdxlfsdm", "hdxlfsmc","","", "");
		}else if("xxfs".equalsIgnoreCase(type)){//学习方式
			return dao.getWhList("dmk_xxfs", "xxfsdm", "xxfsmc","","", "");
		}else if("xsly".equalsIgnoreCase(type)){//学生来源
			return dao.getWhList("dmk_xsly", "xslydm", "xslymc","","", "");
		}else if("zb".equalsIgnoreCase(type)){//州别
			return dao.getWhList("dmk_zb", "zbdm", "zbmc","","", "");
		}else if("jdfs".equalsIgnoreCase(type)){//就读方式
			return dao.getWhList("dmk_jdfs", "jdfsdm", "jdfsmc","","", "");
		}else if("wyyz".equalsIgnoreCase(type)){//外语语种
			return dao.getWhList("dmk_wyyz", "wyyzdm", "wyyzmc","","", "");
		}
		return new ArrayList<HashMap<String,String>>();
	}
	
	
	/**
	 * 查询银行信息
	 * @return List<HashMap<String,String>>
	 * */
	public List<HashMap<String,String>> getYhList(){
		return dao.selectYhxx();
	}
	
	/**
	 * 判断当前时间是否在设定的修改学生信息时间范围内
	 * @return boolean
	 * */
	public boolean isXsxxxgsj(){
		boolean flag = true;
		//获取参数设置信息
		HashMap<String, String> confMap = dao.selectXsxxglXsxx();		
		if("yes".equalsIgnoreCase(confMap.get("issz"))){
			//需要时间段限制
			if(!dao.isXsxxxgsj()){
				//在不可修改的时间段内
				flag = false;
			}
		}
		return flag;
	}
	
	/**
	 * 学生信息修改是否已经通过
	 * @param xh
	 * @return boolean
	 * */
	public boolean checkXsxgqx(String xh){
		boolean flag = true;
		if(dao.checkExists("xsxx_xsxgxxb", "xh||shjg", xh+"通过")){
			flag = false;
		}
		return flag;
	}
	
	/**
	 * 获取学生信息管理参数设置
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsxxCs(){
		return dao.selectXsxxglXsxx();
	}
	
	/**
	 * 查询除学号外的可修改字段
	 * @param String yhjs
	 * */
	public String getzdNoXh(String yhjs){
		return dao.selectXgzdNoXh(yhjs);
	}
	
	/**
	 * 获取学生信息
	 * */
	public HashMap<String, String> getStuAndFamily(String xh, String yhjs){
		String xxdm = StandardOperation.getXxdm();
		HashMap<String, String> map = dao.getStuAndFamily(xh, yhjs, xxdm);
		return map;
	}
	
	/**
	 * 获取学生信息修改提示信息
	 * */
	public String getXsxxxgtsxx(String xh,String shjg){		
		String message = "";		
		//判断学生是否修改了字段
		if(StringUtils.isNull(shjg)
				&& dao.checkExists("xg_xsxx_xsxgzdb", "xh", xh)){
			message = "你的信息修改已经通过审核！";			
		}else if("未审核".equalsIgnoreCase(shjg)){
			message = "你的信息修改还没有经过审核！";
		}else if("不通过".equalsIgnoreCase(shjg)){
			message = "你的信息修改没有通过审核！";
		}
		
		return message;
	}
	
	/**
	 * 获取学费设置信息表头
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXfszTopTr(){
		String[] colList = {"bjdm","nj","xymc","zymc","bjmc","xfje"};		
		String[] colListCN = dao.getColumnNameCN(colList, "view_xsxxgl_xfszb");
		return dao.arrayToList(colList, colListCN);
	}
	
	/**
	 * 查询学费设置信息
	 * @param CommanForm model
	 * @return List<String[]>
	 * */
	public  List<String[]> queryXfszxx(CommanForm model){
		return dao.selectXfszxx(model);
	}
	
	/**
	 * 学费设置
	 * @throws Exception 
	 * */
	public boolean saveXfsz(CommanForm model) throws Exception{
		return dao.updateXfszxx(model);
	}
	
	/**
	 * 查询成绩表信息
	 * @param CommanForm model
	 * @return List<HashMap<String, String>>
	 * */
	public List<List<HashMap<String, String>>> queryXscjb(CommanForm model){
		return dao.selectCjb(model);
	}
	
	/**
	 * 获得发布信息
	 * @param CommanForm model
	 * @return List<HashMap<String, String>>
	 * */
	public String getInfo_ser(String title){
		String sql = "select content from xsxx_xxfbb where title=?";
		return dao.getOneRs(sql, new String[]{title},"content");
	}
	
	/**
	 * 查询学生开学应缴学费
	 * @param String xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXfje(String xh){
		return dao.getXsxf(xh);
	}
	
	/**
	 * 获取学生成绩查询结果
	 * @param CommanForm model
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> queryXscjbForPrint(CommanForm model){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		String sql = "select kcsbm,cj from cjb a where xh=? and xn=? and xq=?";
		List<HashMap<String,String>> listTem = dao.getList(sql,new String[]{model.getXh(),model.getXn(),model.getXq()},new String[]{"kcsbm","cj"});
		int listLen = listTem.size();			
		int HalfLen = 0;
		int m = 0;
		//默认显示12行
		m = listLen%2;
		int n=0;
		if(m==1){//listLen是偶数否则是奇数
			if(listLen==2){
				HalfLen = 2; 
			}else{
				HalfLen = listTem.size()/2;
				n=HalfLen+1;
			}					
		}else{
			if(listLen==1){
				HalfLen = 2;
			}else{
				HalfLen = (listTem.size()+1)/2;
				n= HalfLen;
			}
		}
		for(int i=0;i<n;i++){						
			HashMap<String,String> map = new HashMap<String, String>();
			HashMap<String, String> value = listTem.size()>i ? listTem.get(i) : new HashMap<String, String>();
			HashMap<String, String> nextValue = listTem.size()> i+1 ? listTem.get(i+1) : new HashMap<String, String>();
			HashMap<String, String> halfValue = listTem.size() > n+i ? listTem.get(n+i) : new HashMap<String, String>();
			if(listLen==1||listLen==2){
                if(listLen==2){
                	if(i==0){
                		map.put("rowOneA",String.valueOf(i+1));
                		map.put("kcOneA",value== null ? "" :value.get("kcsbm"));
                		map.put("cjOneA",value== null ? "" :value.get("cj"));	
                		map.put("rowOneB",String.valueOf(2));
                		map.put("kcOneB",nextValue== null ? "" :nextValue.get("kcsbm"));
                		map.put("cjOneB",nextValue== null ? "" :nextValue.get("cj"));
                	}
                }else{
                	if(i==0){
                		map.put("rowOneA",String.valueOf(i+1));
                		map.put("kcOneA",value== null ? "" :value.get("kcsbm"));
                		map.put("cjOneA",value== null ? "" :value.get("cj"));	
                		map.put("rowOneB","");
                		map.put("kcOneB","");
                		map.put("cjOneB","");
                	}
                }
			}else{
				if(i==n-1){
						map.put("rowOneA",String.valueOf(i+1));
						map.put("kcOneA",value== null ? "" :value.get("kcsbm"));
						map.put("cjOneA",value== null ? "" :value.get("cj"));	
						map.put("rowOneB",String.valueOf(n+i+1));
						map.put("kcOneB",halfValue == null ? "" : halfValue.get("kcsbm"));
						map.put("cjOneB",halfValue == null ? "" : halfValue.get("cj"));
				}else{
					map.put("rowOneA",String.valueOf(i+1));
					map.put("kcOneA",value== null ? "" :value.get("kcsbm"));
					map.put("cjOneA",value== null ? "" :value.get("cj"));	
					map.put("rowOneB",String.valueOf(n+i+1));
					map.put("kcOneB",halfValue== null ? "" : halfValue.get("kcsbm"));
					map.put("cjOneB",halfValue == null ? "" : halfValue.get("cj"));
				}
			}
			list.add(map);
		}
		return list;
	}

	/**
	 * 保存学生信息模块参数设置信息
	 * @param StudentInfoForm model
	 * @return boolean 
	 * @throws Exception 
	 * */
	public boolean saveStuConfig(StudentInfoForm model) throws Exception{
		return dao.saveStuConfig(model);
	}
	
	/**
	 *判断学生信息是否已经确认,确认返回true，否则返回false
	 *@param xh
	 *@return boolean 
	 * */
	public boolean checkXsxxsfqr(String xh){
		return dao.checkExists("xsxxb", "xh||xsqrxxbz", xh+"是");
	}
	
	/**
	 * 保存学生学习经历信息
	 * @param xh
	 * @param valueMap
	 * @return boolean
	 * */
	public boolean saveXsxxjl(String xh, HashMap<String, String[]> valueMap){
		return dao.saveXsxxjlb(xh, valueMap);
	}
	
	/**
	 * 保存学生社会关系信息
	 * @param xh
	 * @param valueMap
	 * @return boolean
	 * */
	public boolean saveXsshgx(String xh, HashMap<String, String[]> valueMap){
		return dao.saveXsshgx(xh, valueMap);
	}
	
	
	/**
	 * 删除学生学习经历信息
	 * @param xh
	 * @param valueMap
	 * @return boolean
	 * */
	public boolean deleteXsxxjl(HttpServletRequest request){
		boolean result = false;
		try{
			result = StandardOperation.delete("xsxxjlb", "xh", request.getParameter("save_xh"), request);
		}catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 查询学生学习经历信息
	 * @param xh
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXsxxjl(String xh){
		return stuDao.getXsxxjl(xh);
	}
	
	/**
	 * 查询学生学习经历信息
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsxxjlb(String xh){
		return stuDao.getXsxxjlb(xh);
	}
	
	/**
	 * 将出生日期转换成年龄
	 * @param csrq
	 * @return String
	 * */
	public String changeNl(String csrq){
		String csn = "";//出生年
		String nl = "";//年龄
		if(StringUtils.isNotNull(csrq) && csrq.length()>3){
			csn = csrq.substring(0,4);
			nl = (Integer.parseInt(GetTime.getNowYear()) - Integer.parseInt(csn)) + ""; 
		}
		
		return nl;
	}
	
	/**
	 * 获取学生关联信息
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsglxx(String xh){
		return stuDao.selectXsglxx(xh);
	}
	
	
	/**
	 * 获取学生扩展字段zd1,zd2,zd3,zd4,zd5
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXskzzd(String xh){
		return stuDao.getXskzzd(xh);
	}
	

	/**
	 * 是否特殊学生
	 * @param xh
	 * @return
	 */
	public String sfTsxs(String xh) {
		String count = stuDao.getTsxs(xh);
		return "0".equalsIgnoreCase(count)?"否":"是";
	}
	
	/**
	 * 获取代码列表数据
	 * @param lx
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getList(String lx){
		//学籍状态
		if(GlobalsVariable.XSXX_KTEYS_XJZTLIST.equalsIgnoreCase(lx)){
			return stuDao.getXjztList();
		}
		//异动类别
		if(GlobalsVariable.XSXX_KTEYS_YDLBLIST.equalsIgnoreCase(lx)){
			XsxxZgdzdxDAO dao = new XsxxZgdzdxDAO();
			return dao.getYdlbList();
		}
		//是否在校
		if(GlobalsVariable.XSXX_KTEYS_SFZXLIST.equalsIgnoreCase(lx)){
			//无数据维护，系统默认值
			List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dm", "在校");
			map.put("mc", "在校");
			list.add(map);
			
			map = new HashMap<String, String>();
			map.put("dm", "不在校");
			map.put("mc", "不在校");
			list.add(map);
			return list;
		}
		//审核列表
		if(GlobalsVariable.XTWH_SH_LIST.equalsIgnoreCase(lx)){
			DAO dao = DAO.getInstance();			
			return dao.getChkList(3);
		}
		//部门类别
		if(GlobalsVariable.XTWH_XXBMLB_LIST.equalsIgnoreCase(lx)){
			return dao.getWhList("xg_xsxx_bmlbdmb", "dm", "mc", "", "", "");
		}
		//学生层次
		if(GlobalsVariable.XTWH_PYCC_LIST.equalsIgnoreCase(lx)){
			return dao.getWhList("xg_xsxx_pyccdmb", "pyccdm", "pyccmc", "", "", "");
		}
		//考生类别
		if(GlobalsVariable.XTWH_KSLB_LIST.equalsIgnoreCase(lx)){
			return dao.getWhList("xg_xsxx_kslbdmb", "kslbdm", "kslbmc", "", "", "");
		}
		//入学方式
		if(GlobalsVariable.XTWH_RXFS_LIST.equalsIgnoreCase(lx)){
			return dao.getWhList("xg_xsxx_rxfsdmb", "rxfsdm", "rxfsmc", "", "", "");
		}
		//培养方式
		if(GlobalsVariable.XTWH_PYFS_LIST.equalsIgnoreCase(lx)){
			return dao.getWhList("xg_xsxx_pyfsdmb", "pyfsdm", "pyfsmc", "", "", "");
		}
		
		//审核流程
		if("shlcList".equalsIgnoreCase(lx)){
			return dao.getWhList("xg_xtwh_splc", "id", "mc", "", "djlx", "xsxx");
		}
		
		//民族
		if(GlobalsVariable.XTWH_MZ_LIST.equalsIgnoreCase(lx)){
			return dao.getMzList();			
		}
		//政治面貌
		if(GlobalsVariable.XTWH_ZZMM_LIST.equalsIgnoreCase(lx)){
			return dao.getZzmmList();
		}
		//银行
		if(GlobalsVariable.XTWH_YH_LIST.equalsIgnoreCase(lx)){
			return getYhList();
		}
		
		return new ArrayList<HashMap<String,String>>();
	}
	
	/**
	 * 获取最大异动序号
	 * @return String
	 * */
	public String getMaxYdxh(){
		StudentInfoDao studao = new StudentInfoDao();
		return studao.getMaxYdxh();
	}
	
	/**
	 * 获取家庭关系List
	 * @return
	 * @author sjf
	 */
	public List<HashMap<String, String>> getJtgxList(){
		String[] colList = new String[]{"dm", "mc"};
		String query = " order by dm";
		
		return CommonQueryDAO.commonQueryforList("xszz_jtcygxb", query, new String[]{}, colList, "");
	}
	
	/**
	 * 获取详细页面配置选项卡list
	 * @return
	 * @author sjf
	 */
	public List<HashMap<String, String>> getPagesList(){
		String[] colList = new String[]{"ename", "cname", "sfxs", "xssx"};
		return CommonQueryDAO.commonQueryforList("xg_xsxx_xspz", "", new String[]{}, colList, "");
	}
	
	/**
	 * 保存设置
	 * @param model
	 * @return
	 * @author sjf
	 */
	public boolean savePages(XsxxpzModel model){
		boolean flag = false;
		String[] arrzd = new String[]{"ename", "cname", "sfxs","xssx"};
		String[] onezd = new String[]{};
		String[] pkValue = model.getEname();
		String pk = "ename";
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName("xg_xsxx_xspz");
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		
		DAO dao = DAO.getInstance();
		try {
			flag = dao.saveData(saveForm, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag; 
	}
	
	public List<HashMap<String, String>> getXsPages(){
		String[] colList = new String[]{"en", "cn"};
		String sql = "select ename en, cname cn from xg_xsxx_xspz where sfxs<>'0' order by xssx";
		DAO dao = DAO.getInstance();
		return dao.getList(sql, new String[]{}, colList);
	}
	
	/**
	 * 获取学生身体信息
	 * @param xh
	 * @return List<HashMap<String, String>>
	 * author qlj
	 */
	public HashMap<String, String> getXsstList(String xh){
		return dao.getXsstList(xh);
	}
	/**
	 * 获取学生姓名
	 * @param xh
	 */
	public String getStuName(String xh) {
	    String xm = dao.getStuInfo(xh);
	    return xm;
	    
	    
	}
	
	/**
	 * 获取当前时间
	 * @param xh
	 */
	public String getNowTime() {
	    String nowTime = dao.nowTime();
	   	return nowTime;
	    
	}
	
	/**
	 * 获取学生住宿基本信息
	 * @param xh
	 * @return
	 * @author gaobb 2011-11-22 新版公寓
	 */
	public HashMap<String,String> getXszsjbxx(String xh){
		return dao.getXszsjbxx(xh);
	}
	
	/**
	 * 获取苏州工业园区综合测评列表
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getSzgyyqZhcpList(String xh){
		return dao.getSzgyyqZhcpList(xh);
	}
}