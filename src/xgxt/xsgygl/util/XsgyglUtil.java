
package xgxt.xsgygl.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.struts.action.ActionForm;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.CommonQueryDAO;
import xgxt.xsgygl.action.XsgyglForm;
import xgxt.xsgygl.dao.GyglShareDAO;
import xgxt.xsgygl.dao.gyglDao;

public class XsgyglUtil {
	/**各功能常用到的变量*/
	private String xn = "";//学年
	private String xq = "";//学期
	private String xh = "";//学号
	private String lddm = "";//楼栋代码
//	private String nd = "";//年度
	private String nj = "";//年级
	private String xy = "";//学院
	private String zy = "";//专业
	private String bj = "";//班级
	private String xm = "";//姓名
	private String qsh = "";//寝室号
	private String cwh = "";//床位号
	private String pycc = "";//培养层次

	public XsgyglUtil(HttpServletRequest request,ActionForm form) {	
		XsgyglForm xsgyForm = (XsgyglForm)form;
		xn   = xsgyForm.getXn();
		xq   = xsgyForm.getXq();
	    lddm = xsgyForm.getLddm();
	    xh   = xsgyForm.getXh();
//	    nd   = xsgyForm.getNd();
	    nj   = xsgyForm.getNj();
	    zy   = xsgyForm.getZydm();
	    bj   = xsgyForm.getBjdm();
	    xm   = DealString.toGBK(xsgyForm.getXm());
	    qsh  = xsgyForm.getQsh();
	    cwh  = xsgyForm.getCwh();
	    xy   = xsgyForm.getXydm();
	    pycc = xsgyForm.getPycc();
	    xsgyForm.setXm(xm);
	   
	}
	public void utilGywxsq(HttpServletRequest request,ActionForm form) throws Exception{
		DAO dao = DAO.getInstance();
		gyglDao  myDao = new gyglDao();
		HashMap<String,String> map = new HashMap<String,String>();
		String doType = request.getParameter("doType");//操作类型
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userOnLine").toString();//用户类型
		String userName = session.getAttribute("userName").toString();//用户登录名
		XsgyglForm xsgyForm = (XsgyglForm)form;		
		String bxr = xsgyForm.getBxr();//保修人
		String bxsj = request.getParameter("bxsj");//报修时间
		String ssbh = request.getParameter("ssbh");//宿舍编号
		String xm = DealString.toGBK(request.getParameter("xm"));//保修人姓名
		String wxnr = DealString.toGBK(request.getParameter("wxnr"));//维修内容
		String bz = DealString.toGBK(request.getParameter("bz"));//备注
		String fzbm = DealString.toGBK(request.getParameter("fzbm"));
		String lxfs = DealString.toGBK(request.getParameter("lxfs"));//联系方式
		String realTable = "gywxglsqb";//公寓维修管理申请表（物理表）
		String xxdm = dao.getXxdm();
		if(xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)){//长沙民政职业技术学院
			request.setAttribute("isCSMZZYJSXY", "CSMZZYJSXY");
			request.setAttribute("wxnrList",myDao.getGyWxNrList());//维修内容列表
			request.setAttribute("fzbmList",myDao.getGyWxFzBmList());//负责部门列表
		}
		boolean done = false;
        if(userType.equalsIgnoreCase("student")){//学生用户访问时获取相关信息
        	map = dao.getMap("select xh,xm,ssbh,ldmc,qsh from view_xszsxx where xh=?", new String[]{userName}, new String[]{"xh","xm","ssbh","ldmc","qsh"});
            map.put("bxr",userName);
        }else{//教师用户访问时获取相关信息
        	request.setAttribute("isNotStu", "isNotStu");
        }
		
		if(Base.isNull(doType)){//默认当前学年学期
			map.put("xn",Base.currXn);
			map.put("xq",Base.currXq);
		}else{
			if(doType.equalsIgnoreCase("save")){//保存数据
				done = StandardOperation.delete(realTable, new String[]{"ssbh","bxsj"},new String[]{ssbh,bxsj},request);
				if(done){
					done = StandardOperation.insert(realTable, new String[]{"xn","xq","bz","ssbh","bxsj","wxnr","bxr","bxrxm","fzbm","lxfs"},
							new String[]{xn,xq,bz,ssbh,bxsj,wxnr,bxr,xm,fzbm,lxfs}, request);
					map.put("xn",xn);
					map.put("xq",xq);
					map.put("bxsj",bxsj);
					map.put("wxnr",wxnr);
					map.put("bz",bz);
					map.put("fzbm",fzbm);
					map.put("lxfs",lxfs);
				}
			}
			if(done){
				request.setAttribute("done","yes");
			}else{
				request.setAttribute("done","no");
			}
		}
		request.setAttribute("userType", userType);
		gyglDao.getXnxqList(request);//获取学年学期List数据
		request.setAttribute("rs", map);
	}
	public void gywxSqShXx(HttpServletRequest request,ActionForm form){
		DAO dao = DAO.getInstance();
		gyglDao  myDao = new gyglDao();
		XsgyglForm xsgyForm = (XsgyglForm)form;
		StringBuffer querry = new StringBuffer();
		Vector<Object> rs = new Vector<Object>();
		String go = request.getParameter("go");
		String tips = "公寓管理 - 审核 - 公寓(宿舍)维修审核";
		String rsNum = "";
		String pk = "ssbh||bxsj";
		String tableName = "view_gywxglsqxx";
		String realTable = "gywxglsqb";
		String kssj = xsgyForm.getKssj();//开始日期查询条件
		String jssj = xsgyForm.getJssj();//结束日期
		String shzt = DealString.toGBK(xsgyForm.getYesNo());//审核状态标志	
		String fzbm = xsgyForm.getFzbm();//负责部门代码
        String xxdm = dao.getXxdm();
		xsgyForm.setYesNo(shzt);
		List topTr = null;
		if(xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)){//长沙民政职业技术学院
			request.setAttribute("isCSMZZYJSXY", "CSMZZYJSXY");
			request.setAttribute("fzbmList",myDao.getGyWxFzBmList());//负责部门列表
		}
		if(Base.isNull(go)){
			xn = Base.currXn;
			xq = Base.currXq;
			xsgyForm.setXn(xn);
			xsgyForm.setXq(xq);
		}
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
		querry.append(Base.isNull(lddm)?"":" and lddm='"+lddm+"' ");
		querry.append(Base.isNull(qsh)?"":" and qsh='"+qsh+"' ");
		querry.append(Base.isNull(shzt)?"":" and xxsh='"+shzt+"' ");
		querry.append(Base.isNull(fzbm)?"":" and fzbm='"+fzbm+"' ");
		if(!Base.isNull(kssj)&&!Base.isNull(jssj)){
			querry.append(" and bxsj between '"+kssj+"' and '"+jssj+"'");
		}else{
			if(!Base.isNull(kssj)){
				querry.append(" and bxsj='"+kssj+"'  ");
			}else if(!Base.isNull(jssj)){
				querry.append(" and bxsj='"+jssj+"' ");
			}
		}		
		if(!Base.isNull(go)){
			String[] colList = new String[]{"bgcolor","主键","xn","xq","bxsj","wxnr","bxrxm","ldmc","qsh","xxsh"};
			String[] colCNList = dao.getColumnNameCN(colList, tableName);
			StringBuffer sql = new StringBuffer();
			sql.append(" select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor, a.* from ( ");
			if(xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)){//长沙民政职业技术学院				
				sql.append(" select "+pk+" 主键 ,xn,xq,bxsj,(select nrmc from csmz_gywxnrdmb c where c.nrdm=wxnr) wxnr,bxrxm,ldmc,qsh,xxsh from "+tableName+querry+" order by xxsh desc ) a ");
			}else{
				sql.append(" select "+pk+" 主键 ,xn,xq,bxsj,wxnr,bxrxm,ldmc,qsh,xxsh from "+tableName+querry+" order by xxsh desc ) a ");
			}
			topTr = dao.arrayToList(colList, colCNList);
				rs.addAll(dao.rsToVator(sql.toString(), new String[] {}, colList));
				request.setAttribute("rs", rs);
				if (rs == null) {
					rsNum = "0";
				} else {
					rsNum = String.valueOf(rs.size());
				}		
		}
		request.setAttribute("rsNum",rsNum);
		request.setAttribute("tips", tips);
		request.setAttribute("topTr",topTr);	
		gyglDao.getLdLcQshList(request);
		request.setAttribute("chkList", dao.getChkList(3));//审核状态列表
		request.setAttribute("tableName",tableName);
		request.setAttribute("realTable",realTable);
	}
	public void gywxSqSh(HttpServletRequest request,ActionForm form) throws Exception{
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>(); 
	    String pkValue = request.getParameter("pkValue");
	    String doType = request.getParameter("doType");
	    String xxshyy = DealString.toGBK(request.getParameter("xxshyy"));
		String wxsj =  DealString.toGBK(request.getParameter("wxsj"));
		String rydm =  DealString.toGBK(request.getParameter("rydm"));
		wxsj = Base.isNull(wxsj)?"暂未安排":wxsj;
		rydm = Base.isNull(rydm)?"暂未安排":rydm;
	    String sql = "";
	    String pk = "ssbh||bxsj";
	    boolean done = false;
	    if(dao.getXxdm().equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)){//长沙民政职业技术学院
	    	request.setAttribute("isCSMZZYJSXY", "CSMZZYJSXY");
			sql = "select a.* from (select a.ssbh,a.xn,a.xq,a.ldmc,a.qsh,a.bxsj,(select nrmc from csmz_gywxnrdmb c where c.nrdm=a.wxnr)wxnr, " 
				+"(select fzbmmc from csmz_gywxfzbmdmb c where c.fzbmdm=a.fzbm)fzbm,a.bxrxm,a.xxsh yesNo,a.bz sqbz ,a.lxfs,b.wxsj,b.rydm,b.bz from  "
				+"view_gywxglsqxx a left join view_gywxglxx b on a.ssbh||a.bxsj=b.ssbh||b.bxsj) a where a.ssbh||a.bxsj=? ";
	    }else{
			sql = "select a.* from (select a.ssbh,a.xn,a.xq,a.ldmc,a.qsh,a.bxsj,a.wxnr,a.bxrxm,a.xxsh yesNo,a.bz sqbz,a.fzbm,a.lxfs,a.wxsj,a.wxry rydm,b.bz from  "
				   +"view_gywxglsqxx a left join view_gywxglxx b on a.ssbh||a.bxsj=b.ssbh||b.bxsj) a where a.ssbh||a.bxsj=?";
	    }
		map = dao.getMap(sql,new String[]{pkValue},new String[]{"ssbh","xn","xq","ldmc","qsh","bxsj","wxnr","bxrxm","yesNo","sqbz","fzbm","wxsj","rydm","lxfs","bz"});
		List ryList = dao.getList("select rydm,rymc from gywxryb where rydm <> '000'", new String[] {}, new String[] {
				"rydm", "rymc" });
		if(!Base.isNull(doType)&&"save".equalsIgnoreCase(doType)){
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			done = StandardOperation.update("gywxglsqb",new String[]{"xxsh","xxshyy","wxsj","wxry"},new String[]{yesNo,xxshyy,wxsj,rydm},pk,pkValue,request);
			if(done){
				if(yesNo.equalsIgnoreCase("通过")){
					String bz  = DealString.toGBK(request.getParameter("bz"));
					done = StandardOperation.delete("gywxglb", pk,pkValue, request);
					if(done){
						done = StandardOperation.insert("gywxglb",new String[]{"xn","xq","ssbh","bxsj","wxnr","bz"},
								new String[]{map.get("xn"),map.get("xq"),map.get("ssbh"),map.get("bxsj"),map.get("wxnr"),
								bz},request);
					}
				}else{				
					StandardOperation.delete("gywxglb", pk, pkValue, request);
				}
			}
			if(done==true){
				request.setAttribute("done","yes");
			}else{
				request.setAttribute("done","no");
			}
		}	
		//审核结果
		String[] shJg = dao.getOneRs("select xxshyy from gywxglsqb where "+ pk + "=? ",new String[]{pkValue},new String[]{"xxshyy"}); 
		if(shJg!=null){
			map.put("xxshyy",shJg[0]);
		}
		request.setAttribute("ryList", ryList);
		request.setAttribute("rs",map);
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("userType",dao.getUserType(request.getSession().getAttribute("userDep").toString()));
	}
	public void ssYdSq(HttpServletRequest request,ActionForm form) throws Exception{
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		HttpSession session = request.getSession();
		String doType = request.getParameter("doType");
		String userType = session.getAttribute("userOnLine").toString();//用户类型
		String userName = session.getAttribute("userName").toString();//用户登录名
		boolean done = false;
		String realTable = "ssydsqb";//宿舍异动申请表
		if(userType.equalsIgnoreCase("student")){//学生用户访问时获取相关信息
        	map = dao.getMap("select xh,xm,xb,nj,xymc,zymc,bjmc,rzrq,ssbh,lddm yqdlddm,ldmc ydqldmc,qsh ydqqsh,cwh ydqcwh from view_xszsxx where xh=?",
        		new String[]{userName}, new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","rzrq","ssbh","yqdlddm","ydqldmc","ydqqsh","ydqcwh"});
            xh = userName;       
		}else{//教师用户访问时获取相关信息
			request.setAttribute("isNotStu", "isNotStu");
        }
		if(Base.isNull(doType)){//默认当前学年学期
			map.put("xn",Base.currXn);
			map.put("xq",Base.currXq);
		}else{
			if(doType.equalsIgnoreCase("save")){//保存数据
				String ydsj = request.getParameter("ydsj");
				String ydly = DealString.toGBK(request.getParameter("ydly"));
				String ydqssbh = map.get("ssbh");
				String ydhssbh = dao.getOneRs("select ssbh from ssxxb where lddm=? and qsh=?",new String[]{lddm,qsh},"ssbh");//lddm+"-"+qsh;
				done = StandardOperation.delete(realTable, new String[]{"xh","sqydsj"},new String[]{xh,ydsj},request);
				if(done){
					done = StandardOperation.insert(realTable, new String[]{"xh","xn","xq","ydqssbh","ydhssbh","ydly","sqydsj","ydhcwh"},
							new String[]{xh,xn,xq,ydqssbh,ydhssbh,ydly,ydsj,cwh}, request);					
				}
				map.put("xn",xn);
				map.put("xq",xq);
				map.put("lddm", lddm);
				map.put("qsh",qsh);
				map.put("cwh",cwh);
				map.put("ydsj",ydsj);
				map.put("ydly",ydly);
			}
			if(done){
				request.setAttribute("done","yes");
			}else{
				request.setAttribute("done","no");
			}
		}		
		gyglDao.getLdLcQshList(request);//获取楼栋学年学期List数据
		request.setAttribute("rs",map);
	}
	public void ssYdSqShxx(HttpServletRequest request,ActionForm form,String userType) {
		DAO dao = DAO.getInstance();
		XsgyglForm xsgyForm = (XsgyglForm)form;
		StringBuffer querry = new StringBuffer();//查询条件
		Vector<Object> rs = new Vector<Object>();
		String go = request.getParameter("go");//查询标志
		String rsNum = "";//记录数
		String pk = "xh||sqydsj";//关键字段
		String tableName = "view_ssydsqxx";//视图
		String realTable = "ssydsqb";//物理图
		String kssj = xsgyForm.getKssj();//开始日期查询条件
		String jssj = xsgyForm.getJssj();//结束日期
		String shzt = DealString.toGBK(xsgyForm.getYesNo());//审核状态标志
		xsgyForm.setYesNo(shzt);
		List topTr = null;//字段说明List
		if(Base.isNull(go)){//默认当前学年、年度
			xn = Base.currXn;
			xq = Base.currXq;
			xsgyForm.setXn(xn);
			xsgyForm.setXq(xq);
		}
		if(userType.equalsIgnoreCase("xy")){
			xy = request.getSession().getAttribute("userDep").toString();
			xsgyForm.setXydm(xy);
		}
		
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
		querry.append(Base.isNull(zy)?"":" and zydm='"+zy+"' ");
		querry.append(Base.isNull(bj)?"":" and bjdm='"+bj+"' ");
		querry.append(Base.isNull(xh)?"":" and xh='"+xh+"' ");
		querry.append(Base.isNull(xy)?"":" and xydm='"+xy+"' ");			
		if(!Base.isNull(kssj)&&!Base.isNull(jssj)){
			querry.append(" and sqydsj between '"+kssj+"' and '"+jssj+"'");
		}else{
			if(!Base.isNull(kssj)){
				querry.append(" and sqydsj='"+kssj+"'  ");
			}else if(!Base.isNull(jssj)){
				querry.append(" and sqydsj='"+jssj+"' ");
			}
		}		
		if(!Base.isNull(go)&&"go".equalsIgnoreCase(go)){
			String[] colList = new String[]{"bgcolor","主键","xh","xm","xb","sqydsj","ydqldmc","ydqqsh","ldmc","qsh","xxsh"};
			StringBuffer sql = new StringBuffer();
			if(userType.equalsIgnoreCase("xx")
					||userType.equalsIgnoreCase("admin")){
				querry.append(Base.isNull(shzt)?"":" and xxsh='"+shzt+"' ");
				querry.append(" and xysh='通过' ");
				sql.append(" select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor, a.* from ( ");
				sql.append(" select "+pk+" 主键 ,xh,xm,xb,sqydsj,ydqldmc,ydqqsh,ldmc,qsh,xxsh from "+tableName+querry+" order by xxsh desc,sqydsj desc ) a ");
			}else if(userType.equalsIgnoreCase("xy")){
				querry.append(Base.isNull(shzt)?"":" and xysh='"+shzt+"' ");
				sql.append(" select (case when nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor, a.* from ( ");
				sql.append(" select "+pk+" 主键 ,xh,xm,xb,sqydsj,ydqldmc,ydqqsh,ldmc,qsh,xysh from "+tableName+querry+" order by xxsh desc,sqydsj desc ) a ");
				colList = new String[]{"bgcolor","主键","xh","xm","xb","sqydsj","ydqldmc","ydqqsh","ldmc","qsh","xysh"};
			}
			String[] colCNList = dao.getColumnNameCN(colList, tableName);
			topTr = dao.arrayToList(colList, colCNList);
				rs.addAll(dao.rsToVator(sql.toString(), new String[] {}, colList));
				request.setAttribute("rs", rs);
				if (rs == null) {
					rsNum = "0";
				} else {
					rsNum = String.valueOf(rs.size());
				}	
		}
		gyglDao.getXnxqList(request);//学年学期List
		gyglDao.getXyZyBjxx(request);//学院专业班级List
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum",rsNum);
		request.setAttribute("chkList", dao.getChkList(3));//审核状态列表
		request.setAttribute("tableName",tableName);
		request.setAttribute("realTable",realTable);
	}
	public void ssYdSqSh(HttpServletRequest request,ActionForm form,String userType) throws Exception {
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>(); 
	    String pkValue = request.getParameter("pkValue");//主键值
	    String doType = request.getParameter("doType");//操作类型
	    String xyshyy  = DealString.toGBK(request.getParameter("xyshyy"));
	    String xxshyy  = DealString.toGBK(request.getParameter("xxshyy"));
	    String sql = "";
	    String pk = "xh||sqydsj";//ssydsqb表主键
	    String shType = "";
	    if(userType.equalsIgnoreCase("xx")
				||userType.equalsIgnoreCase("admin")){
	    	shType = "xxsh";
	    }else{
	    	shType = "xysh";
	    }
	    boolean done = false;
		//获得ssydsqb中相关值集
	    sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,xn,xq,ydqldmc,ydqqsh,ydqssbh,ydqcwh,ydqrzsj,ldmc,qsh,ydhssbh,ydhcwh,sqydsj,ydly from view_ssydsqxx where "+pk+"=? ";
		map = dao.getMap(sql,new String[]{pkValue},new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","xn","xq","ydqldmc","ydqqsh","ydqssbh","ydqcwh","ydqrzsj","ldmc","qsh","ydhssbh","ydhcwh","sqydsj","ydly"});
		//数据保存
		if(!Base.isNull(doType)&&"save".equalsIgnoreCase(doType)){//查询
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if(userType.equalsIgnoreCase("xy")){
				done = StandardOperation.update("ssydsqb",new String[]{"xysh","xyshyy"},new String[]{yesNo,xyshyy},pk,pkValue,request);//修改ssydsqb表中院系审核状态			
			}else if(userType.equalsIgnoreCase("xx")
					||userType.equalsIgnoreCase("admin")){
				done = StandardOperation.update("ssydsqb",new String[]{"xxsh","xxshyy"},new String[]{yesNo,xxshyy},pk,pkValue,request);//修改ssydsqb表中学校审核状态
				if(yesNo.equalsIgnoreCase("通过")){	//学校级用户审核通过后						
					String bz  = DealString.toGBK(request.getParameter("bz"));
					boolean del = StandardOperation.delete("ssydxxb", "xh||ydsj",pkValue, request);
					if(del){
						//通过时将申请相关信息插入宿舍异动信息表中
						boolean ins = StandardOperation.insert("ssydxxb",new String[]{"xh","xn","xq","ydqssbh","ydhssbh","ydly","ydsj","bz","ydqcwh","ydhcwh","ydqrzsj","ydhrzsj"},
								new String[]{map.get("xh"),map.get("xn"),map.get("xq"),map.get("ydqssbh"),map.get("ydhssbh"),map.get("ydly"),map.get("sqydsj"),bz,map.get("ydqcwh"),map.get("ydhcwh"),map.get("ydqrzsj"),
								map.get("ydhrzsj")},request);
						if(ins){
							//通过时修改学生住宿信息
							   StandardOperation.update("xszsxxb", new String[] { "ssbh",
									"cwh", "rzrq" }, new String[] {map.get("ydhssbh"),map.get("ydhcwh") , 
									map.get("sqydsj") }, new String[] { "xh" }, new String[] { map.get("xh") },
									request);
						}							
					}
				}else {//不通过时如果	宿舍异动信息表中有相关信息删除相关信息				
					StandardOperation.delete("ssydxxb",  "xh||ydsj",pkValue, request);
				}
			}			
			
			if(done==true){
				request.setAttribute("done","yes");
			}else{
				request.setAttribute("done","no");
			}
		}
		//审核结果
		String[] shJg = dao.getOneRs("select "+shType+" yesNo,xyshyy,xxshyy from ssydsqb where "+ pk + "=? ",new String[]{pkValue},new String[]{"yesNo","xyshyy","xxshyy"}); 
		if(shJg!=null){
			map.put("yesNo",shJg[0]);
			map.put("xyshyy",shJg[1]);
			map.put("xxshyy",shJg[2]);
		}
		sql = map.get("ydqrzsj");
		request.setAttribute("rs",map);
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("userType",dao.getUserType(request.getSession().getAttribute("userDep").toString()));
		request.setAttribute("chkList", dao.getChkList(3));
	}
	public void jqLxSq(HttpServletRequest request,ActionForm form) throws Exception{
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		HashMap<String,String> jqmap = new HashMap<String,String>();
		HashMap<String,String> qsmap = new HashMap<String,String>();
		HttpSession session = request.getSession();
		String xxdm = StandardOperation.getXxdm();
		String doType = request.getParameter("doType");
		String userType = session.getAttribute("userOnLine").toString();//用户类型
		String userName = session.getAttribute("userName").toString();//用户登录名
		boolean done = false;
		String realTable = "gygl_Jqlxsqb";//宿舍异动申请表
		if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){//云南艺术学院
			realTable="gygl_jqlxxxb";
		}
		if(userType.equalsIgnoreCase("student")){//学生用户访问时获取相关信息
        	map = dao.getMap("select xh,xm,xb,nj,xymc,zymc,bjmc from view_xsjbxx where xh=?",
        		new String[]{userName}, new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc"});
            xh = userName;  
		}else{//教师用户访问时获取相关信息
			request.setAttribute("isNotStu", "isNotStu");
        }
		if(Base.isNull(doType)){//默认当前学年学期
			map.put("xn",Base.currXn);
			map.put("xq",Base.currXq);
		}else{
			if(doType.equalsIgnoreCase("save")){//保存数据
				String ssbh = dao.getOneRs("select ssbh from ssxxb where lddm=? and qsh=?",new String[]{lddm,qsh},"ssbh");//lddm+"-"+qsh;
				if(xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
					ssbh = null;
				}
		        String kssj = request.getParameter("kssj");
		        String jssj = request.getParameter("jssj");
		        String lxyy = DealString.toGBK(request.getParameter("lxyy"));
		        String lxdh = DealString.toGBK(request.getParameter("lxdh"));
				done = StandardOperation.delete(realTable, new String[]{"xn","xq","xh"},new String[]{xn,xq,xh},request);
				if(done){
					
					done = StandardOperation.insert(realTable, new String[]{"xn","xq","xh","ssbh","zskssj","zsjssj","lxyy","lxdh"},
							new String[]{xn,xq,xh,ssbh,kssj,jssj,lxyy,lxdh}, request);
				}
				jqmap =dao.getMap("select xn,xq,zskssj kssj,zsjssj jssj,lxdh,lxyy,ssbh from "+realTable+" where xh=?",
	        			new String[]{userName}, new String[]{"xn","xq","kssj","jssj","lxdh","lxyy","ssbh"});
	        	qsmap =dao.getMap("select qsh,lddm from view_xszsxx where ssbh= ?", new String[]{jqmap.get("ssbh")}, new String[]{"qsh","lddm"});
	            xh = userName;  
	            map.putAll(jqmap);
	            map.putAll(qsmap);
			}
			if(done){
				request.setAttribute("done","yes");
			}else{
				request.setAttribute("done","no");
			}
		}
		gyglDao.getLdLcQshList(request);//获取楼栋学年学期List数据	
		request.setAttribute("rs",map);
	}
	
	
	public void jqLxSqShXx(HttpServletRequest request,ActionForm form){
		DAO dao = DAO.getInstance();
		XsgyglForm xsgyForm = (XsgyglForm)form;
		StringBuffer querry = new StringBuffer();//查询条件
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		Vector<Object> rs = new Vector<Object>();
		String xxdm = StandardOperation.getXxdm();
		String go = request.getParameter("go");//查询标志
		String rsNum = "";//记录数
		String pk = "xn||xq||xh";//关键字段
		String tableName = "";
		if(xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
			 tableName = "view_xbemy_gygl_jqlxsq";//视图
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){//云南艺术
			 tableName = "view_xbemy_gygl_jqlxxx";
		}else{
			 tableName = "view_gygl_jqlxsq";//视图
		}
		List topTr = null;//字段说明List
		String realTable = "gygl_jqlxsqb";//物理表
		String shzt = DealString.toGBK(xsgyForm.getYesNo());//审核状态标志
		xsgyForm.setYesNo(shzt);
		if(Base.isNull(go)){//默认当前学年、年度
			xn = Base.currXn;
			xq = Base.currXq;
			xsgyForm.setXn(xn);
			xsgyForm.setXq(xq);
		}
		if(userType.equalsIgnoreCase("xy")){
			xy = userDep;
			xsgyForm.setXydm(xy);
		}
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
		querry.append(Base.isNull(zy)?"":" and zydm='"+zy+"' ");
		querry.append(Base.isNull(bj)?"":" and bjdm='"+bj+"' ");
		querry.append(Base.isNull(xh)?"":" and xh='"+xh+"' ");
		if(!Base.isNull(go)&&"go".equalsIgnoreCase(go)){//查询
			
			String[] colList = { "bgcolor","主键","xn", "xq", "xh", "xb", "xm", "nj", "xymc","zymc", "bjmc","zskssj", "zsjssj","xxsh"};				
			if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){
				 colList = new String[]{ "bgcolor","主键","xn", "xqmc", "xh", "xb", "xm", "nj", "xymc","zymc", "bjmc","zskssj", "zsjssj","xxsh"};
			}
			StringBuffer sql = new StringBuffer();
			if(userType.equalsIgnoreCase("xx")
					|| userType.equalsIgnoreCase("admin")) {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {
					querry.append(Base.isNull(shzt) ? "" : " and xxsh='" + shzt
							+ "' ");
					sql.append(" select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor, a.* from ( ");
					sql.append(" select "
									+ pk
									+ " 主键 ,xn, xq, xh, xb, xm, nj, xymc,zymc, bjmc,zskssj, zsjssj,xxsh from "
									+ "view_hh_gygl_jqlxsq" + querry
									+ " order by xxsh desc,zskssj desc ) a ");
				}if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){ 
					querry.append(Base.isNull(shzt) ? "" : " and xxsh='" + shzt
							+ "' ");
					
					sql.append(" select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor, a.* from ( ");
					sql.append(" select "
									+ pk
									+ " 主键 ,xn, xqmc, xh, xb, xm, nj, xymc,zymc, bjmc,zskssj, zsjssj,xxsh from "
									+ tableName + querry
									+ " order by xxsh desc,zskssj desc ) a ");
				}else {
					querry.append(Base.isNull(shzt) ? "" : " and xxsh='" + shzt
							+ "' ");
					querry.append(" and xysh='通过'");
					sql.append(" select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor, a.* from ( ");
					sql.append(" select "
									+ pk
									+ " 主键 ,xn, xq, xh, xb, xm, nj, xymc,zymc, bjmc,zskssj, zsjssj,xxsh from "
									+ tableName + querry
									+ " order by xxsh desc,zskssj desc ) a ");
				}
			}else if(userType.equalsIgnoreCase("xy")){
				querry.append(Base.isNull(shzt)?"":" and xysh='"+shzt+"' ");
				sql.append(" select (case when nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor, a.* from ( ");
				sql.append(" select "+pk+" 主键 ,xn, xq, xh, xb, xm, nj, xymc,zymc, bjmc,zskssj, zsjssj,xysh from "+tableName+querry+" order by xysh desc,zskssj desc ) a ");
				colList = new String[]{ "bgcolor","主键","xn", "xq", "xh", "xb", "xm", "nj", "xymc","zymc", "bjmc","zskssj", "zsjssj","xysh"};	
			}
			String[] colCNList = dao.getColumnNameCN(colList, tableName);
			topTr = dao.arrayToList(colList, colCNList);			
			rs.addAll(dao.rsToVator(sql.toString(), new String[] {}, colList));
			request.setAttribute("rs", rs);
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}	
		}
		gyglDao.getXnxqList(request);//学年学期List
		gyglDao.getXyZyBjxx(request);//学院专业班级List
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum",rsNum);
		request.setAttribute("chkList", dao.getChkList(3));//审核状态列表
		request.setAttribute("tableName",tableName);
		request.setAttribute("realTable",realTable);
	}
	public void jqLxSqSh(HttpServletRequest request,ActionForm form) throws Exception{
		DAO dao = DAO.getInstance();
		String userType = request.getSession().getAttribute("userType").toString();
		HashMap<String,String> map = new HashMap<String,String>(); 
	    String pkValue = request.getParameter("pkValue");//主键值
	    String xyshyy  = DealString.toGBK(request.getParameter("xyshyy"));
	    String xxshyy  = DealString.toGBK(request.getParameter("xxshyy"));
	    String doType = request.getParameter("doType");//操作类型
	    String xxdm = StandardOperation.getXxdm();
	    String sql = "";
	    String pk = "xn||xq||xh";//gygl_jqlxsqb表主键
	    boolean done = false;
	    String shType ="";
	    if(userType.equalsIgnoreCase("xx")
				||userType.equalsIgnoreCase("admin")){
	    	shType = "xxsh";
	    }else{
	    	shType = "xysh";
	    }
//	  获得gygl_jqlxsqb中相关值集
	    if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {
			sql = " select xn,xq,xh,xm,xb,nj,xymc,zymc,bjmc,zskssj,zsjssj,lxdh,lxyy,ssbh from view_hh_gygl_jqlxsq where "
					+ pk + "=? ";
			map = dao.getMap(sql, new String[] { pkValue }, new String[] {
					"xn", "xq", "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc",
					"zskssj", "zsjssj", "lxdh", "lxyy", "ssbh"});
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
			sql = " select xn,xq,xh,xm,xb,nj,xymc,zymc,bjmc,zskssj,zsjssj,lxdh,lxyy,ssbh from view_xbemy_gygl_jqlxsq where "
				+ pk + "=? ";
			map = dao.getMap(sql, new String[] { pkValue }, new String[] {
				"xn", "xq", "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc",
				 "zskssj", "zsjssj", "lxdh", "lxyy", "ssbh"});
		} else if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){//云南艺术
			sql = " select xn,xq,xh,xm,xb,nj,xymc,zymc,bjmc,ldmc,qsh,zskssj,zsjssj,lxdh,lxyy,ssbh from view_xbemy_gygl_jqlxxx where "
				+ pk + "=? ";
			map = dao.getMap(sql, new String[] { pkValue }, new String[] {
				"xn", "xq", "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc","qsh","ldmc",
				"zskssj", "zsjssj", "lxdh", "lxyy", "ssbh"});
		} else{
			sql = " select xn,xq,xh,xm,xb,nj,xymc,zymc,bjmc,ldmc,qsh,zskssj,zsjssj,lxdh,lxyy,ssbh from view_gygl_jqlxsq where "
					+ pk + "=? ";
			map = dao.getMap(sql, new String[] { pkValue }, new String[] {
					"xn", "xq", "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc",
					"ldmc", "qsh", "zskssj", "zsjssj", "lxdh", "lxyy", "ssbh" });
		}	    
		//数据保存
		if(!Base.isNull(doType)&&"save".equalsIgnoreCase(doType)){
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));	
			if(userType.equalsIgnoreCase("xx")
					||userType.equalsIgnoreCase("admin")){
				/*if(xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
					done = StandardOperation.update("gygl_jqlxsqb",new String[]{"xxsh"},new String[]{yesNo},pk,pkValue,request);//修改ssydsqb表中审核状态
				}else{*/
					if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){//云南艺术
						done = StandardOperation.update("gygl_jqlxxxb",new String[]{"xxsh","xxshyy"},new String[]{yesNo,xxshyy},pk,pkValue,request);//修改ssydsqb表中审核状态
					}else{
						done = StandardOperation.update("gygl_jqlxsqb",new String[]{"xxsh","xxshyy"},new String[]{yesNo,xxshyy},pk,pkValue,request);//修改ssydsqb表中审核状态
						if(done){
					    	if(yesNo.equalsIgnoreCase("通过")){//通过时将申请相关信息插入假期留校信息表中							
					    		boolean del = StandardOperation.delete("gygl_jqlxxxb", pk,pkValue, request);
					    		if(del){
					    			StandardOperation.insert("gygl_jqlxxxb",new String[]{"xn","xq","xh","ssbh","zskssj","zsjssj","lxyy","lxdh"},
					    					new String[]{map.get("xn"),map.get("xq"),map.get("xh"),map.get("ssbh"),map.get("zskssj"),map.get("zsjssj"),map.get("lxyy"),map.get("lxdh")},request);
					    		}
					    	}else{//不通过时如果	假期留校信息表中有相关信息删除相关信息				
					    		StandardOperation.delete("gygl_jqlxxxb",pk,pkValue, request);
					    	}
					    }
					}
					
				//}
			}else if(userType.equalsIgnoreCase("xy")){
				done = StandardOperation.update("gygl_jqlxsqb",new String[]{"xysh","xyshyy"},new String[]{yesNo,xyshyy},pk,pkValue,request);//修改ssydsqb表中审核状态
			}
			
			if(done==true){
				request.setAttribute("done","yes");
			}else{
				request.setAttribute("done","no");
			}
		}
		
		//审核结果
		String tablename="gygl_jqlxsqb";
		if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){
			tablename="gygl_jqlxxxb";
		}
		String[] shJg = dao.getOneRs("select "+shType+" yesNo,xyshyy,xxshyy from "+tablename+" where "+ pk + "=? ",new String[]{pkValue},new String[]{"yesNo","xyshyy","xxshyy"}); 
		if(shJg!=null){
			map.put("yesNo",shJg[0]);
			map.put("xyshyy",shJg[1]);
			map.put("xxshyy",shJg[2]);
		}
		request.setAttribute("rs",map);
		request.setAttribute("userType",dao.getUserType(request.getSession().getAttribute("userDep").toString()));
		request.setAttribute("pkValue",pkValue);		
		request.setAttribute("chkList", dao.getChkList(3));
	}
	public void sqJgCx(HttpServletRequest request,ActionForm form){		
		DAO dao = DAO.getInstance();
		String userType = request.getSession().getAttribute("userOnLine").toString();//用户类型
		String userName = request.getSession().getAttribute("userName").toString();//用户登录名
		String sql = "";
		String xxdm = dao.getXxdm();
		request.setAttribute("xxdm", xxdm);
		Vector<Object> rsWx = new Vector<Object>();//公寓维修申请查询结果集
		Vector<Object> rsYd = new Vector<Object>();//宿舍异动申请查询结果集
		Vector<Object> rsLx = new Vector<Object>();//假期留校申请查询结果集
		Vector<Object> rsWz = new Vector<Object>();//学生外住申请查询结果集
		Vector<Object> rsJswmhd = new Vector<Object>();//学生精神文明活动申请查询结果集
		String[] colList = null;
		String[] colListCN = null;
		if("student".equalsIgnoreCase(userType)){
			xh = userName; 
			//公寓维修
			sql = " select (case when xxsh='通过'then  '#FFFFFF' else '#CCCCCC'  end )color,xn,(select xqmc from xqdzb b where b.xqdm=xq)xq,ldmc,qsh,bxsj,wxnr,bxr,bxrxm,xxsh,xxshyy,wxsj,(select rymc from gywxryb b where b.rydm=a.wxry )wxry  from view_gywxglsqxx a where bxr=? order by bxsj desc";
			colList = new String[] { "color","xn","xq","ldmc","qsh","bxsj","wxnr","bxr","bxrxm","xxsh","xxshyy","wxsj","wxry"};
			colListCN = dao.getColumnNameCN(colList, "view_gywxglsqxx");
			List topTrWx = dao.arrayToList(colList, colListCN);
			rsWx.addAll(dao.rsToVator(sql, new String[]{xh}, colList));			
			request.setAttribute("rsWx",rsWx);
			request.setAttribute("topTrWx", topTrWx);
			request.setAttribute("numWx", String.valueOf(rsWx.size()));
			//宿舍异动
			sql = "select (case when xxsh='通过'then  '#FFFFFF' else '#CCCCCC'  end )color, sqydsj,ydqldmc,ydqqsh,ldmc,qsh,xysh,xxsh,xyshyy,xxshyy from view_ssydsqxx where xh=? order by sqydsj desc ";
			colList = new String[] { "color","sqydsj","ydqldmc","ydqqsh","ldmc","qsh","xysh","xxsh" ,"xyshyy","xxshyy"};
			colListCN = dao.getColumnNameCN(colList, "view_ssydsqxx");
			List topTrYd = dao.arrayToList(colList, colListCN);
			rsYd.addAll(dao.rsToVator(sql, new String[]{xh},colList));
			request.setAttribute("rsYd",rsYd);
			request.setAttribute("topTrYd",topTrYd);
			request.setAttribute("numYd",String.valueOf(rsYd.size()));
			
			//假期留校
			colListCN = dao.getColumnNameCN(colList, "view_gygl_jqlxsq");
//			if(Globals.XXDM_XBEMY.equalsIgnoreCase(xxdm)){
			 sql = "select (case when a.xxsh='通过' then  '#FFFFFF' else '#CCCCCC'  end )color,a.xh,a.xn,(select xqmc from xqdzb b where b.xqdm=a.xq)xq,(case when b.ldmc is null then " +
			 		"'暂未分配' else b.ldmc end) ldmc,(case when b.qsh is null" +
			 		" then '暂未分配' else b.qsh end) qsh,a.zskssj,a.zsjssj," +
			 		"a.lxdh,a.lxyy,(case when b.ssbh is null then '暂未分配' else " +
			 		"b.ssbh end) ssbh,a.xxsh,a.xysh,a.xyshyy,a.xxshyy,(case when b.cwh is null " +
			 		" then '暂未分配' else b.cwh end) cwh from gygl_jqlxsqb a left " +
			 		"join  view_gygl_jqlxxx b on a.xh=b.xh and a.xn=b.xn and " +
			 		"a.xq=b.xq where a.xh=? order by a.xn desc ";
			 colList=new String[]{"color","xn","xq","ldmc","qsh","zskssj","zsjssj","lxdh","lxyy","xysh","xxsh","xyshyy","xxshyy"};
			 colListCN=new String[]{"color","学年","学期","楼栋名称","寝室号","住宿开始时间","住宿结束时间","联系电话","留校理由","院系审核","学校审核",Base.YXPZXY_KEY+"审核说明","学校审核说明"};			 
//			}else{
//				sql = "select xn,xq,xh,nj,xymc,zymc,bjmc,ldmc,qsh,zskssj,zsjssj,lxdh,lxyy,ssbh,xysh,xxsh,xyshyy,xxshyy from view_gygl_jqlxsq where xh=? order by zskssj desc ";
//				colList = new String[] { "xn","xq","ldmc","qsh","zskssj","zsjssj","lxdh","lxyy","ssbh","xysh","xxsh","xyshyy","xxshyy" };
//			}
			
			List topTrLx = dao.arrayToList(colList, colListCN);
			rsLx.addAll(dao.rsToVator(sql,new String[]{xh},colList));
			request.setAttribute("rsLx",rsLx);
			request.setAttribute("topTrLx", topTrLx);
			request.setAttribute("numLx",String.valueOf(rsLx.size()));
			
			//学生外住申请结果
			sql = "select  (case when xxsh='通过'then  '#FFFFFF' else '#CCCCCC'  end )color,xn,(select xqmc from xqdzb b where b.xqdm=xq)xq,wzksrq,jhwzsj,wzlxdm,wzyy,wzdz,xxsh,xysh from gygl_xswzsqb where xh=? order by wzksrq asc ";
			colList = new String[] { "color","xn","xq","wzksrq","jhwzsj","wzlxdm","wzyy","wzdz","xxsh","xysh"};
			colListCN = dao.getColumnNameCN(colList, "gygl_xswzsqb");
            List topTrWz =  dao.arrayToList(colList, colListCN);
            rsWz.addAll(dao.rsToVator(sql,new String[]{xh},colList));
            request.setAttribute("rsWz",rsWz);
            request.setAttribute("topTrWz",topTrWz);
            request.setAttribute("numWz", String.valueOf(rsWz.size()));
            
            //学生精神文明活动申请
    		sql = "select  (case when xxsh='通过'then  '#FFFFFF' else '#CCCCCC'  end )color,hdmc,zbdw,hddd,hdksrq,hdjsrq,fzrxm,fzrlxfs,(case when hdnr is null then '' else substr(hdnr,1,5)||'...' end)hdnr,'院系审核：'||xysh||'  '||'学校审核：'||xxsh shzt,sqsj from jswmhdsqb where sqrdlm=?";
    		colList   = new String[]{"color","hdmc","zbdw","hddd","hdksrq","hdjsrq","fzrxm","fzrlxfs","hdnr","shzt","sqsj" };
            colListCN = new String[]{"color", "活动名称","主办单位","活动地点","活动开始日期","活动结束日期","负责人姓名","负责人联系方式","活动内容","审核情况","申请时间"};
            List topTrJswmhd =  dao.arrayToList(colList, colListCN);
            rsJswmhd.addAll(dao.rsToVator(sql,new String[]{xh},colList));
            request.setAttribute("rsJswmhd",rsJswmhd);
            request.setAttribute("topTrJswmhd",topTrJswmhd);
            request.setAttribute("numJswmhd", String.valueOf(rsJswmhd.size()));
                       	
            //总结果记录数
            request.setAttribute("numCout",String.valueOf(rsWx.size()+rsYd.size()+rsLx.size()+rsWz.size()+rsJswmhd.size()));
		}else{		   
            request.setAttribute("isNotStu", "isNotStu");
		}
	}
	public void pubYj(HttpServletRequest request,ActionForm form,String userName) throws Exception{		
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");//是否保存参数		
		String id = DealString.toString(request.getParameter("id"));//意见记录ID
		HashMap<String,String> map = new HashMap<String,String>(); 
		boolean done = false;
		String sql = "";
		if(!Base.isNull(doType)&&doType.equalsIgnoreCase("pub")){//保存
			String title = DealString.toGBK(request.getParameter("title"));//意见标题
			String content = DealString.toGBK(request.getParameter("content"));//意见内容
			if(Base.isNull(id)){//发布意见
				sql = "insert into xsgygl_xsyjb(id,title,content,xh) values(S_GYGLXSYJ_ID.NEXTVAL,?,?,?)";
				done = dao.insert(sql,new String[]{title,content,userName});
				if(done){dao.writeLog(sql, new String[]{title,content,userName}, null,"向学生意见表插入记录", request);}//写入日志
			}else{//修改意见
				sql = "update xsgygl_xsyjb set title=?,content=? where id=? ";
				done = StandardOperation.update("xsgygl_xsyjb", new String[]{"title","content"},new String[]{title,content},"id",id, request);
                id="";//记录修改后将ID参数值清空			
			}
			if(done==true){			    
				request.setAttribute("done","yes");
			}else{
				request.setAttribute("done","no");
			}
		}else if(!Base.isNull(doType)&&doType.equalsIgnoreCase("modi")){
			map = dao.getMap(" select title,content from xsgygl_xsyjb where id=?",new String[]{id},new String[]{"title","content"});
		}else if(!Base.isNull(doType)&&doType.endsWith("del")){//删除数据
			StandardOperation.delete("xsgygl_xsyjb","id",id, request);//删除学生意见表中该条记录
			StandardOperation.delete("xsgygl_xsyjhfb","id",id, request);//删除学生意见回复表中该条记录
		}
		//有回复则学生就无修改删除操作权限
		sql = "select id,title,content,pubdate,xh,(case when id in (select id from xsgygl_xsyjhfb) then '有' else '无' end ) ywhf from xsgygl_xsyjb where xh=? order by pubdate desc ";
		List yjList = dao.getList(sql,new String[]{userName},new String[]{"id","title","content","pubdate","xh","ywhf"});
		request.setAttribute("yjList", yjList);
		request.setAttribute("rs", map);
		request.setAttribute("yjid",id);
	}
	public void YjXxQur(HttpServletRequest request,ActionForm form,String userName) throws Exception{
		DAO dao = DAO.getInstance();
//		XsgyglForm dsForm = (XsgyglForm)form;
		String doType = request.getParameter("doType");
		if(!Base.isNull(doType)&&doType.equalsIgnoreCase("del")){//删除意见记录
			String id = request.getParameter("id");
			StandardOperation.delete("xsgygl_xsyjb","id",id, request);//删除学生意见表中该条记录
			StandardOperation.delete("xsgygl_xsyjhfb","id",id, request);//删除学生意见回复表中该条记录
		}
		String sql = "select id,title,content,pubdate,xh,(case when id in (select id from xsgygl_xsyjhfb) then '有' else '无' end ) ywhf from xsgygl_xsyjb order by pubdate desc ";
		List yjList = dao.getList(sql,new String[]{},new String[]{"id","title","content","pubdate","xh","ywhf"});
		request.setAttribute("rs", yjList);
	}
	public void viewYjXx(HttpServletRequest request,ActionForm form,String userName) throws Exception{
		DAO dao = DAO.getInstance();
		HashMap<String,String> mapa = new HashMap<String,String>();
		HashMap<String,String> mapb = new HashMap<String,String>();
		String id = request.getParameter("id");
		String sql = " select id,title,content,pubdate,xh from xsgygl_xsyjb where id=? ";
		mapa = dao.getMap(sql,new String[]{id},new String[]{"id","title","content","pubdate","xh"});
		sql = "select recontent,repubdate,yhm from xsgygl_xsyjhfb where id=? ";
		mapb = dao.getMap(sql, new String[]{id},new String[]{"recontent","repubdate","yhm"});
	    request.setAttribute("rsYj",mapa);
		request.setAttribute("rsRe",mapb);
	}
	public void yjHf(HttpServletRequest request,ActionForm form,String userName) throws Exception{
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		String doType = request.getParameter("doType");//是否保存参数
		String act = request.getParameter("act");//操类型
		String id = DealString.toString(request.getParameter("id"));//意见记录ID
		boolean done = false;
		if(!Base.isNull(act)&&act.equalsIgnoreCase("add")){
			map = dao.getMap(" select 'RE：'||title title from xsgygl_xsyjb where id=?",new String[]{id},new String[]{"title"});
		}else if(!Base.isNull("modi")){
			map = dao.getMap(" select 'RE：'||a.title title,b.recontent content from xsgygl_xsyjb a right join xsgygl_xsyjhfb b on a.id=b.id and a.id=?",new String[]{id},new String[]{"title","content"});
		}		
		if(!Base.isNull(doType)&&doType.equalsIgnoreCase("save")){//回复内容保存
			String recontent = DealString.toGBK(request.getParameter("content"));
			if(act.equalsIgnoreCase("add")){//意见回复
				done = StandardOperation.insert("xsgygl_xsyjhfb", new String[]{"id","recontent","yhm"},new String[]{id,recontent,userName}, request);
			}else if(act.equalsIgnoreCase("modi")){//意见回复修改
				done = StandardOperation.update("xsgygl_xsyjhfb", new String[]{"recontent","yhm"},new String[]{recontent,userName},"id",id, request);
			}
			if(done==true){			    
				request.setAttribute("done","yes");
			}else{
				request.setAttribute("done","no");
			}
		}	
		request.setAttribute("rs", map);
		request.setAttribute("act",act);
		request.setAttribute("id",id);
	}
	public void xsGyGL_LsSj(HttpServletRequest request,ActionForm form) throws Exception{
		DAO dao = DAO.getInstance();
		List<String[]>rs=new ArrayList<String[]>();
		StringBuffer querry = new StringBuffer();//查询条件
		String xsbz = DealString.toGBK(request.getParameter("xsbz"));
		String xb = DealString.toGBK(request.getParameter("xb"));
		XsgyglForm xsgyForm = (XsgyglForm)form;
		xsgyForm.setXsbz(xsbz);
		xsgyForm.setXb(xb);
		String xxdm = dao.getXxdm();
		String rsNum = "0";//记录数
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"'");
		querry.append(Base.isNull(xy)?"":" and xydm='"+xy+"'");
		querry.append(Base.isNull(zy)?"":" and zydm='"+zy+"'");
		querry.append(Base.isNull(bj)?"":" and bjdm='"+bj+"'");
		querry.append(Base.isNull(xh)?"":" and xh like '%'||?||'%' ");
		querry.append(Base.isNull(xm)?"":" and xm like '%'||?||'%' ");
		querry.append(Base.isNull(xsbz)?"":" and xsbz like '"+xsbz+"'");
		querry.append(Base.isNull(xb)?"":" and xb = '"+xb+"'");
		String[] colList = new String[] { "xh", "xm", "nj", "xb", "xymc","zymc","bjmc" };//显示字段
		String[] colListCN = dao.getColumnNameCN(colList, "view_xszslsxx");//显示字段注释
		String sql = "select a.* from (select distinct xh,rownum r, xm,nj,xb,xymc,zymc,bjmc from view_xszslsxx "+ querry+") a ";
		if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
			colList = new String[] { "xh", "xm", "nj", "xb", "xymc","zymc","bjmc","xsbz" };//显示字段
			colListCN = dao.getColumnNameCN(colList, "view_xszslsxx");//显示字段注释
			sql = " select a.* from ( distinct xh,rownum r, xm,nj,xb,xymc,zymc,bjmc,xsbz from view_xszslsxx "+ querry+") a ";
		}
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			String[] inputValue = new String[]{};
			
			if (!Base.isNull(xh) && !Base.isNull(xm)) {
				inputValue = new String[] { xh, xm };
			} else if (!Base.isNull(xh)) {
				inputValue = new String[] { xh };
			} else if (!Base.isNull(xm)) {
				inputValue = new String[] { xm };
			}
			
			rs=CommonQueryDAO.commonQuery(sql, "", inputValue, colList, xsgyForm);
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}			
		}	
		gyglDao.getXyZyBjxx(request);
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("tableName","view_xszslsxx");
		request.setAttribute("xxdm",xxdm);
		request.setAttribute("realTable","xszslsxxb");
	}
	public void viewGyLsSj(HttpServletRequest request,ActionForm form) throws SQLException{
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		String sql = " ";
		map = dao.getMap(" select xh,xm from view_xsjbxx where xh=? ",new String[]{xh},new String[]{"xh","xm"});
		request.setAttribute("rsStu", map);       
		sql = "select xh,xm,ssbh,ldmc,qsh,cwh,rzrq,tfrq,sfbz from view_xszslsxx where xh=? order by rzrq";			
		List zslsList = dao.getList(sql, new String[]{xh}, new String[]{"xh","xm","ssbh","ldmc","qsh","cwh","rzrq","tfrq","sfbz"});
		request.setAttribute("zslsList",zslsList);//曾住宿情况
	}
	public void fykBbtj(HttpServletRequest request,ActionForm form,HttpServletResponse response) throws IOException, WriteException{
		DAO dao = DAO.getInstance();
		String sql = "";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		WritableSheet ws = wwb.createSheet("房源库统计", 0);
		
		WritableFont wf = new WritableFont(WritableFont.TIMES); // 构造字体格式
		WritableCellFormat wcf = new WritableCellFormat(); // 构造单元格格式
		wf.setBoldStyle(WritableFont.NO_BOLD); // 设置字体格式(非粗体)
		wf.setColour(Colour.getInternalColour(20)); // 设置字体格式(红色)
		wf.setUnderlineStyle(UnderlineStyle.NO_UNDERLINE); // 设置字体格式(无下划线)
		wf.setPointSize(13); // 设置字体格式(大小)
		wcf.setFont(wf); // 设置指定字体格式的单元格格式
		wcf.setAlignment(Alignment.CENTRE); // 指定格式设置字符左右居中
		wcf.setVerticalAlignment(VerticalAlignment.CENTRE); // 指定格式设置字符上下居中
		//wcf.setWrap(true); // 指定格式设置自动换行
		ws.setColumnView(0, 12); // 设置1列的列宽
		ws.setColumnView(1, 12); // 设置2列的列宽
		ws.setColumnView(2, 12); // 设置3列的列宽
		ws.setColumnView(3, 12); // 设置4列的列宽
		ws.setColumnView(4, 40); // 设置5列的列宽
	    
		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式
		wcf2.setVerticalAlignment(VerticalAlignment.CENTRE); // 指定格式设置字符上下居中
		
		ws.addCell(new Label(0, 0,"楼栋",wcf)); // 添加有指定格式的单元格值
		ws.addCell(new Label(1, 0,"宿舍编号",wcf));
		ws.addCell(new Label(2, 0,"空床位数",wcf));
		ws.addCell(new Label(3, 0,"已住人数",wcf));
		ws.addCell(new Label(4, 0,"已住人学号、姓名",wcf));		
		
		List<HashMap<String, String>> ldList = dao.getList(" select lddm,ldmc from sslddmb  order by  lddm  ", new String[]{}, new String[]{"lddm","ldmc"}); // 获取楼栋列表
		int nextNumV1 = 0;
		int nextNumV2 = 1;
		for(int i=0;i<ldList.size();i++){//循环遍历每栋楼
			String lddm = ldList.get(i).get("lddm");
			String ldmc = ldList.get(i).get("ldmc");
			sql = "select count(*) cout from ssxxb where lddm=? ";
			String sscout_ald = dao.getOneRs(sql,new String[]{lddm}, "cout");
			Integer.parseInt(sscout_ald);						
			if(i==0){// 设置1列2行起始纵向合并单元格
				ws.mergeCells(0,1,0,Integer.parseInt(sscout_ald)); 
			}else{//设置上次合并行为起点纵向合并单元格
				String sscout_ald_last = dao.getOneRs(sql,new String[]{ldList.get(i-1).get("lddm")}, "cout");
				nextNumV1 += Integer.parseInt(sscout_ald_last);
				ws.mergeCells(0,nextNumV1-Integer.parseInt(sscout_ald_last)+1,0,nextNumV1);				
			}
			
			ws.addCell(new Label(0,nextNumV1+1,ldmc,wcf2));//填充第一列楼栋名称
			
			sql = "select ssbh from ssxxb where lddm=? order by ssbh";
			List<HashMap<String, String>> ssbhList = dao.getList(sql, new String[]{lddm},new String[]{"ssbh"});
			
			StringBuffer sqlV = new StringBuffer();//查询入住成员
			sqlV.append(" select ssbh, cy from ( select ssbh, max(ltrim(sys_connect_by_path(xh||xm,'、'),'、')) cy from ( ");
			sqlV.append(" select distinct xh,xm,ssbh, row_number() over(partition by ssbh order by xh ) pno,  ");
			sqlV.append(" row_number() over(partition by ssbh order by xh )-1 sno ");
			sqlV.append(" from view_xszsxx ) a start with pno='1' connect by prior pno = sno and prior ssbh=ssbh");
			sqlV.append(" group by ssbh order by ssbh ) where ssbh = ?");		
			for(int j=0;j<ssbhList.size();j++){//循环遍历每栋楼宿舍
				String ssbh = ssbhList.get(j).get("ssbh");
				ws.addCell(new Label(1,nextNumV2,ssbh));
				
				String yzrs = dao.getOneRs(" select count(*) cout from xszsxxb where ssbh = ?",new String[]{ssbh},"cout");
				String zcws = dao.getOneRs(" select nvl(cws,'0')cws from ssxxb where ssbh=? ",new String[]{ssbh},"cws");
				String kycw = String.valueOf(Integer.parseInt(Base.isNull(zcws)?"0":zcws)-Integer.parseInt(Base.isNull(yzrs)?"0":yzrs));
				
				ws.addCell(new Label(2,nextNumV2,kycw));
				ws.addCell(new Label(3,nextNumV2,yzrs));
				
				String cy = dao.getOneRs(sqlV.toString(),new String[]{ssbh},"cy");//宿舍成员 
				ws.addCell(new Label(4,nextNumV2,cy));
				
				nextNumV2++;//单元格序号
			}
		}
		wwb.write();
		wwb.close();
	}
	public void jQlXsPb(HttpServletRequest request,ActionForm form){
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		String pkValue = request.getParameter("pkValue");
		String sql = "";
		String csnl = "";
		if(!Base.isNull(pkValue)){//
			sql =" select (select lydq from view_stu_details a where a.xh=xh and rownum=1 )lydq,(select ssbh from xszsxxb a where a.xh=xh and rownum=1 )ssbh," +
				 "substr((select csrq from view_stu_details a where a.xh=xh and rownum=1),1,4)csnd, xn,xq,xh,xm,xb,nj,xymc,zymc,bjmc,ldmc," +
				 "qsh,zskssj,zsjssj,lxdh,lxyy,ssbh,ldmc,qsh from view_gygl_jqlxsq where xn||xq||xh=? ";
			map = dao.getMap(sql,new String[]{pkValue},new String[]{"lydq","csnd","xn","xq","xh","xm","xb","nj","xymc","zymc","bjmc","ldmc","qsh","zskssj","zsjssj","lxdh","lxyy","ssbh","ldmc","qsh"});				
			csnl = String.valueOf(Integer.parseInt(Base.currNd)-Integer.parseInt(Base.isNull(map.get("csnd"))?"0":map.get("csnd")));
		}else if(!Base.isNull(xh)){//
			sql ="select (select lydq from view_stu_details a where a.xh=xh and rownum=1 )lydq, "
				+"substr((select csrq from view_stu_details a where a.xh=xh and rownum=1),1,4)csnd, "
				+"xh,xm,xb,nj,xymc,zymc,bjmc,xz,ssbh from view_xsjbxx where xh=?";
			map = dao.getMap(sql,new String[]{xh}, new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","xz","ssbh","lydq","csnd"});	
			csnl = String.valueOf(Integer.parseInt(Base.currNd)-Integer.parseInt(Base.isNull(map.get("csnd"))?"0":map.get("csnd")));
			map.put("lxyy", DealString.toGBK(request.getParameter("lxyy")));
			map.put("lxdh",request.getParameter("lxdh"));
		}
		if(csnl.equalsIgnoreCase(Base.currNd)){
			csnl = "";
		}
		map.put("nd",csnl);
		map.put("xxmc", StandardOperation.getXxmc());
		request.setAttribute("rs", map);
	}
	public void sSyDsPb(HttpServletRequest request,ActionForm form){
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		String pkValue = request.getParameter("pkValue");
		String  sql = "";
		String csnl = "";
		if(!Base.isNull(pkValue)){//
			sql = "select (select lydq from view_stu_details a where a.xh=xh and rownum=1 )lydq, " +
					" substr((select csrq from view_stu_details a where a.xh=xh and rownum=1),1,4)csnd, " +
					"xh,xm,xb,nj,xymc,zymc,bjmc,ydqssbh ssbh,ydhssbh,ydly from view_ssydsqxx where xh||sqydsj=?  ";
			map = dao.getMap(sql,new String[]{pkValue},new String[]{"lydq","csnd","xh","xm","xb","nj","xymc","zymc","bjmc","ssbh","ydhssbh","ydly"});
			csnl = String.valueOf(Integer.parseInt(Base.currNd)-Integer.parseInt(Base.isNull(map.get("csnd"))?"0":map.get("csnd")));
		}else if(!Base.isNull(xh)){//
			sql = "select (select lydq from view_stu_details a where a.xh=xh and rownum=1 )lydq," 
					+"substr((select csrq from view_stu_details a where a.xh=xh and rownum=1),1,4)csnd, "
			        +"xh,xm,xb,nj,xymc,zymc,bjmc,xz,ssbh,lxdh from view_xsjbxx where xh=?";
			map = dao.getMap(sql,new String[]{xh}, new String[]{"lydq","csnd","xh","xm","xb","nj","xymc","zymc","bjmc","xz","ssbh"});		      				
			map.put("ydly", DealString.toGBK(request.getParameter("ydly")));
			csnl = String.valueOf(Integer.parseInt(Base.currNd)-Integer.parseInt(Base.isNull(map.get("csnd"))?"0":map.get("csnd")));
			
		}
		if(csnl.equalsIgnoreCase(Base.currNd)){
			csnl = "";
		}
		map.put("nl",csnl);
		map.put("xxmc", StandardOperation.getXxmc());
		request.setAttribute("rs", map);
	}
	public boolean plDelete(HttpServletRequest request) throws Exception{
		DAO dao = DAO.getInstance();
//		String tabName = request.getParameter("tableName");
		String realTable = request.getParameter("realTable");
		String toHis = DealString.toGBK(request.getParameter("toHistory"));
		String tssj  = request.getParameter("tssjv");
		String delPk = request.getParameter("delPk");
		String pk = request.getParameter("pk");
		String sfsf = request.getParameter("sfsf");
		String[] pkValueA = delPk.split("!!");
		String sql = "";
		String[] instPkSql = null;
		String[] delPkSql  = new String[pkValueA.length];
		StringBuffer instSqlStr = new StringBuffer();
		for(int i=0;i<pkValueA.length;i++){
			sql = " insert into xszslsxxb(xh,ssbh,bz,cwh,rzrq,tfrq,xm,xb,nj,xymc,zymc,bjmc,ldmc,qsh,sfbz) " 
				+" select xh,ssbh,bz,cwh,rzrq,'"+tssj+"'tfrq,xm,xb,nj,xymc,zymc,bjmc,yqmc||ldmc,qsh,sfbz from view_xszsxx where "+pk+"='"+pkValueA[i]+"'";
			instSqlStr.append(Base.isNull(pkValueA[i])?"":sql).append("!!");						
		}
		instPkSql = instSqlStr.toString().split("!!");
		 //-----------------2010.12.6 qph----学生退宿后释放房源信息-----------------
		StringBuilder querySql = new StringBuilder();
		querySql.append("select distinct a.ssbh from xszsxxb a where xh in (");
		  //-----------------2010.12.6 qph--end-------------------
		
	    for (int i = 0; i < pkValueA.length; i++) {
	    	delPkSql[i] = Base.isNull(pkValueA[i])?"":"delete "+realTable+" where "+pk+"= '"+pkValueA[i]+"'";	
	    	
	    	 //-----------------2010.12.6 qph---------------------
	    	if (!Base.isNull(pkValueA[i])) {
	    		querySql.append("'")
	    				.append(pkValueA[i]);
	    		
	    		if (i==pkValueA.length-1) {
	    			querySql.append("')");
	    		} else {
	    			querySql.append("',");
	    		}
	    		
	    	}
	    }              
	
	    String[] ssbhArr = dao.getArray(querySql.toString(), new String[] {}, "ssbh");
	    //-----------------2010.12.6 qph--end-------------------
	    
	    boolean doFlag = false;
        if(toHis.equalsIgnoreCase("yes")){//将该学生住宿信息删除并转移到住宿历史信息表中   
        	String[] exesql = dao.unionArray(instPkSql, delPkSql);
        	int[] array = dao.runBatch(exesql);
        	doFlag = dao.checkBatch(array);    	
        }else{
        	int[] array = dao.runBatch(delPkSql);
        	doFlag = dao.checkBatch(array);   
        }
        
        //-----------------2010.12.6 qph---------------------
        if ("yes".equals(sfsf) && null != ssbhArr && ssbhArr.length>0) {
        	
        	String[] delSqlArr = new String[ssbhArr.length];
        	
        	for (int i = 0 ; i < ssbhArr.length ; i++) {
        		StringBuilder delSql = new StringBuilder();
        		
        		delSql.append("delete from ssfpb a where ssbh='")
        			  .append(ssbhArr[i])
        			  .append("'  and (select count(1) from xszsxxb where ssbh='")
        			  .append(ssbhArr[i])
        			  .append("')=0");
        		delSqlArr[i] = delSql.toString();
        	}
        	int[] result = dao.runBatch(delSqlArr);
        	doFlag = dao.checkBatch(result);
        }
        //-----------------2010.12.6 qph--end-------------------
        
        String msg = doFlag ? "退宿成功！" : "退宿失败！";
        request.setAttribute("msg", msg);
        
		return doFlag;
	}
	public void xsZsXxToHis(HttpServletRequest request) throws Exception{
		DAO dao = DAO.getInstance();
		String sfsf = request.getParameter("sfsf");//是否释放房源
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xy)?"":" and xydm='"+xy+"' ");
		querry.append(Base.isNull(zy)?"":" and zydm='"+zy+"' ");
		querry.append(Base.isNull(bj)?"":" and bjdm='"+bj+"' ");
		querry.append(Base.isNull(pycc)?"":" and pycc='"+pycc+"' ");
		boolean done = false;
		String tableName = "view_xszsxx";
		//String tableName2 = "view_xsjbxx";
		//=============2010.11.27 edit by luojw=======================
		String tableName2 = "view_xsxxb";
	    String	userName = request.getSession().getAttribute("userName").toString();
	    String tssj = request.getParameter("tssjv"); 
		if(GyglShareDAO.isSssAdmin(userName)){
			tableName  = "view_ssszsxx";
			tableName2  = "sss_xxb";
			
		}
		String sql = "insert into xszslsxxb(xh,ssbh,bz,cwh,rzrq,tfrq,xm,xb,nj,xymc,zymc,bjmc,ldmc,qsh,sfbz)" +
				" select xh,ssbh,bz,cwh,rzrq,'"+tssj+"'tfrq,xm,xb,nj,xymc,zymc,bjmc,yqmc||ldmc,qsh,sfbz from  "+tableName+" "+querry.toString();
		done = dao.runProcuder(sql, new String[]{});
		
		 //-----------------2010.12.6 qph----学生退宿后释放房源信息-----------------
		StringBuilder querySql = new StringBuilder();
		querySql.append("select distinct a.ssbh from xszsxxb a where xh in (")
				.append("select xh from ")
				.append(tableName2)
				.append(querry)
				.append(")");
		 String[] ssbhArr = dao.getArray(querySql.toString(), new String[] {}, "ssbh");
		  //-----------------2010.12.6 qph--end-------------------
		
		if(done){
			dao.writeLog(sql, new String[]{},new String[]{},"将学生住宿信息表中满足"+querry.toString()+"条件的学生住宿信息插入学生住宿历史信息表（xszslsxxb）中", request);
			sql = "delete from xszsxxb where xh in (select xh from "+tableName2+querry+")";
			done = dao.runUpdate(sql, new String[]{});
			if(done){
				dao.writeLog(sql,new String[]{},new String[]{},"删除学生住宿信息表中满足"+querry.toString()+"条件的学生住宿信息",request);
			}
		}
		
		 //-----------------2010.12.6 qph---------------------
        if ("yes".equals(sfsf) && null != ssbhArr && ssbhArr.length>0) {
        	
        	String[] delSqlArr = new String[ssbhArr.length];
        	
        	for (int i = 0 ; i < ssbhArr.length ; i++) {
        		StringBuilder delSql = new StringBuilder();
        		
        		delSql.append("delete from ssfpb a where ssbh='")
        			  .append(ssbhArr[i])
        			  .append("'  and (select count(1) from xszsxxb where ssbh='")
        			  .append(ssbhArr[i])
        			  .append("')=0");
        		delSqlArr[i] = delSql.toString();
        	}
        	int[] result = dao.runBatch(delSqlArr);
        	done = dao.checkBatch(result);
        }
        //-----------------2010.12.6 qph--end-------------------
        
		
		
		String msg = done ? "整体退宿成功！" : "整体退宿失败！";
		request.setAttribute("msg", msg);
	}
	/**返回登录用户相关信息*/
	public HashMap<String,String> getUserInfo(String userType,String sUName){
		DAO dao    = DAO.getInstance();
		String sql = "";
		HashMap<String,String> map = new HashMap<String,String>();
		if(userType!=null&&userType.equalsIgnoreCase("stu")){
			sql = "select xh,xm,xb,nj,xymc,bjmc,zymc,sjhm,lxdzxx,lxdh from view_xsjbxx where xh=?";
			map = dao.getMap(sql,new String[]{sUName},new String[]{"xh","xm","xb","nj","xymc","bjmc","zymc","sjhm","lxdzxx","lxdh"});
		}
		return map;
	}
	/**学生外住申请信息保存*/
    public boolean xsZdSqSave(HttpServletRequest request) throws Exception{
    	boolean done = false;
    	String realTable = "gygl_xswzsqb";
    	String primaryKey = "xh||xn||xq||wzksrq";
    	String wzksrq = request.getParameter("wzksrq");
    	String jhwzsj = DealString.toGBK(request.getParameter("jhwzsj"));
    	String wzlxdm = DealString.toGBK(request.getParameter("wzlxdm"));
    	String wzdz   = DealString.toGBK(request.getParameter("wzdz"));
    	String wzyy   = DealString.toGBK(request.getParameter("wzyy"));
//    	String lxfs   = DealString.toGBK(request.getParameter("lxfs"));
    	String jzsfty = DealString.toGBK(request.getParameter("jzsfty"));
    	done = StandardOperation.delete(realTable, primaryKey, xh+xn+xq+wzksrq, request);//删除记录
    	if(done){
    		done = StandardOperation.insert(realTable, new String[]{"xh","xn","xq","wzksrq","jhwzsj","wzlxdm","wzyy","wzdz","jzsfty"},
    			   new String[]{xh,xn,xq,wzksrq,jhwzsj,wzlxdm,wzyy,wzdz,jzsfty}, request);//保存
    	}
    	return done;
    }
    /**走读(外住)申请审核查询*/
	public void xsZdSqShXx(HttpServletRequest request,ActionForm form){
		DAO dao = DAO.getInstance();
		XsgyglForm xsgyForm = (XsgyglForm)form;
		StringBuffer querry = new StringBuffer();//查询条件
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		Vector<Object> rs = new Vector<Object>();
		String go = request.getParameter("go");//查询标志
		String rsNum = "";//记录数
		String pk = "xh||xn||xq||wzksrq";//关键字段
		String tableName = "view_xswzsqxx";//视图
		String realTable = "gygl_xswzsqb";
		List topTr = null;//字段说明List
		String shzt = DealString.toGBK(xsgyForm.getYesNo());//审核状态标志
		xsgyForm.setYesNo(shzt);
		String wzlxdm = xsgyForm.getWzlxdm();
		String wzksrq = xsgyForm.getWzksrq();
		if(Base.isNull(go)){//默认当前学年、年度
			xn = Base.currXn;
			xq = Base.currXq;
			xsgyForm.setXn(xn);
			xsgyForm.setXq(xq);
		}
		if(userType.equalsIgnoreCase("xy")){
			xy = userDep;
			xsgyForm.setXydm(xy);
		}
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		querry.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
		querry.append(Base.isNull(zy)?"":" and zydm='"+zy+"' ");
		querry.append(Base.isNull(bj)?"":" and bjdm='"+bj+"' ");
		querry.append(Base.isNull(xh)?"":" and xh='"+xh+"' ");
		querry.append(Base.isNull(wzksrq)?"":" and wzksrq='"+wzksrq+"'");
		querry.append(Base.isNull(wzlxdm)?"":" and wzlxdm='"+wzlxdm+"'");
		
		if(!Base.isNull(go)
				&&"go".equalsIgnoreCase(go)){//查询
			String[] colList = { "bgcolor","主键","xh","xn", "xq", "xb", "xm", "nj","zymc", "bjmc","wzksrq", "jhwzsj","wzlxmc","wzdz","xxsh"};				
			StringBuffer sql = new StringBuffer();
			if(userType.equalsIgnoreCase("xx")
					||userType.equalsIgnoreCase("admin")){
				querry.append(Base.isNull(shzt)?"":" and xxsh='"+shzt+"' ");
				querry.append(" and xysh='通过'") ;
				sql.append(" select (case when nvl(xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
				sql.append(pk+" 主键 ,xh,xn, xq, xb, xm, nj,zymc, bjmc,wzksrq, jhwzsj,wzlxmc,wzdz,xxsh from "+tableName+querry+" order by xxsh desc,wzksrq desc ");
			}else if(userType.equalsIgnoreCase("xy")){
				querry.append(Base.isNull(shzt)?"":" and xysh='"+shzt+"' ");
				sql.append(" select (case when nvl(xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor, ");
				sql.append(pk+" 主键 ,xh,xn, xq, xb, xm, nj,zymc, bjmc,wzksrq, jhwzsj,wzlxmc,wzdz,xysh from "+tableName+querry+" order by xysh desc,wzksrq desc  ");
				colList = new String[]{ "bgcolor","主键","xh","xn", "xq", "xb", "xm", "nj","zymc", "bjmc","wzksrq", "jhwzsj","wzlxmc","wzdz","xysh"};				
			}
			
			String[] colCNList = dao.getColumnNameCN(colList, tableName);
			topTr = dao.arrayToList(colList, colCNList);			
			rs.addAll(dao.rsToVator(sql.toString(), new String[] {}, colList));
			request.setAttribute("rs", rs);
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}	
		}
		gyglDao.getXnxqList(request);//学年学期List
		gyglDao.getXyZyBjxx(request);//学院专业班级List
		request.setAttribute("wzlxList",gyglDao.getWzlxList());//外住类型列表
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum",rsNum);
		request.setAttribute("chkList", dao.getChkList(3));//审核状态列表
		request.setAttribute("tableName",tableName);
		request.setAttribute("realTable",realTable);
	}
	/**学生走读(外住)申请审核信息显示及操作
	 * @throws Exception */
	public void xsZdSqSh(HttpServletRequest request) throws Exception{
		DAO dao = DAO.getInstance();
		String userType = request.getSession().getAttribute("userType").toString();
		HashMap<String,String> map = new HashMap<String,String>(); 
	    String pkValue = request.getParameter("pkValue");//主键值
	    String doType = request.getParameter("doType");//操作类型
	    String sql = "";
	    String pk = "xh||xn||xq||wzksrq";//关键字段
	    String TableName = "view_xswzsqxx";
	    String realTable = "gygl_xswzsqb";
	    boolean done = false;	
	    String shType = "";
	    if(userType.equalsIgnoreCase("xx")
				||userType.equalsIgnoreCase("admin")){
	    	shType = "xxsh";
	    }else{
	    	shType = "xysh";
	    }
		//获得中相关值集	    
	    sql = " select xh,xn,xq,xm,xb,nj,xymc,zymc,bjmc,wzksrq,jhwzsj,wzlxdm,wzlxmc,wzyy,wzdz,jzsfty,sjhm,lxdh,lxdzxx,"+shType+" yesNo from "+TableName+" where "+pk+"=? ";
		map = dao.getMap(sql,new String[]{pkValue},new String[]{"xh","xn","xq","xm","xb","nj","xymc","zymc","bjmc","wzksrq","jhwzsj","wzlxdm","wzlxmc","wzyy","wzdz","jzsfty","sjhm","lxdh","lxdzxx","yesNo"});
		request.setAttribute("rs",map);
//		数据保存
		if(!Base.isNull(doType)&&"save".equalsIgnoreCase(doType)){
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));	
			if(userType.equalsIgnoreCase("xx")
					||userType.equalsIgnoreCase("admin")){
				done = StandardOperation.update(realTable,new String[]{"xxsh"},new String[]{yesNo},pk,pkValue,request);//修改ssydsqb表中审核状态
				if(done){
					if(yesNo.equalsIgnoreCase("通过")){//通过时将申请相关信息插入外住学生信息表中							
						boolean del = StandardOperation.delete("gygl_xswzxxb", pk,pkValue, request);
						if(del){
							boolean ins = StandardOperation.insert("gygl_xswzxxb",new String[]{"xh","wzlxdm","wzyy","wzdz","xn","xq","wzksrq","jhwzsj"},
									new String[]{map.get("xh"),map.get("wzlxdm"),map.get("wzyy"),map.get("wzdz"),map.get("xn"),map.get("xq"),map.get("wzksrq"),map.get("jhwzsj")},request);
							if(ins){
								dao.runUpdate("delete from xszsxxb where xh=? ",new String[]{map.get("xh")});
							}
						}
					}else{//不通过时如果	外住学生信息表中有相关信息删除相关信息				
						StandardOperation.delete("gygl_xswzxxb",pk,pkValue, request);
					}
				}
			}else if(userType.equalsIgnoreCase("xy")){
				done = StandardOperation.update(realTable,new String[]{"xysh"},//修改ssydsqb表中审核状态
						new String[]{yesNo},pk,pkValue,request);
			}			
			if(done){
				request.setAttribute("done","yes");
			}else{
				request.setAttribute("done","no");
			}
		}			
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("chkList", dao.getChkList(3));
	}
	
	public void jqlxPrint(HttpServletRequest request) throws Exception {

		String sql = "";
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String pkValue = DealString.toGBK(request.getParameter("pk"));

		sql = "select a.xn, a.xq,(case when a.xq='01' then '春' else '秋' end) xqmc,a.xh,a.xb,a.xm,a.nj,a.xymc,a.zymc,a.bjmc,b.ssbh,a.zskssj,a.zsjssj,"
				+ " a.lxdh,a.lxyy,b.bz,b.cwh from view_hh_gygl_jqlxsq a,gygl_jqlxxxb b where a.xn||a.xq||a.xh =? "
				+ " and a.xn||a.xq||a.xh = b.xn||b.xq||b.xh";
		String[] colList = new String[] { "xn", "xq", "xqmc", "xh", "xb", "xm",
				"nj", "xymc", "zymc", "bjmc", "ssbh", "zskssj", "zsjssj",
				"lxdh", "lxyy", "bz","cwh" };
		map = dao.getMap(sql, new String[] { pkValue }, colList);
		String xh = map.get("xh");
		
		HashMap<String, String> tempmap1 = dao.getMap(
				"select * from xszsxxb where xh=?", new String[] { xh },
				new String[] { "ssbh", "cwh" });
		String ssbh = tempmap1.get("ssbh");
		String yssbh="";
		if(ssbh!=null&&!"".equals(ssbh)){
		String cwh = tempmap1.get("cwh");
		String ybh[] = ssbh.split("-");
		yssbh = ybh[0] + "号楼" + ybh[1] + "/" + cwh + "床";
		}
		ssbh = map.get("ssbh");
		if(ssbh!=null&&!"".equals(ssbh)){
		String[] bh = ssbh.split("-");
		String cwh=map.get("cwh");
		ssbh = bh[0] + "号楼" + bh[1]+ "/" + cwh + "床";;
		}
		
		map.put("ssbh", ssbh);
		map.put("yssbh", yssbh);

		String xxdm = StandardOperation.getXxdm();
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("rs", map);

	}
	public HashMap<String,String> getWxDxx (String pkValue){
		DAO dao = DAO.getInstance();
		String pk = "ssbh||bxsj";
		String sql = " select xn,(select xqmc from xqdzb where xqdm=xq)xq,ldmc,qsh,bxsj,wxnr,bxr,bxrxm,xxshyy,wxsj,(select rymc from gywxryb b where b.rydm=a.wxry )wxry,lxfs  from view_gywxglsqxx a where "+pk+"=? ";
		String[] colList = new String[] { "xn","xq","ldmc","qsh","bxsj","wxnr","bxr","bxrxm","xxshyy","wxsj","wxry","lxfs"};
		return dao.getMap(sql,new String[]{pkValue},colList);
	}
	public ArrayList<String[]> getNjrzsj (String nj){
		DAO dao = DAO.getInstance();
		String sql = "";
		if(Base.isNull(nj)){
			sql = "select nj,rzsj from njrzsj order by nj";
		}else{
			sql = "select nj,rzsj from njrzsj where nj='"+nj+"'";
		}
		return dao.rsToVator2(sql, new String[]{}, new String[]{"nj","rzsj"});
	}
	public String saveNjrzsj (String nj,String rzsj){
		DAO dao = DAO.getInstance();
		String sql = "delete from njrzsj where nj=?";
		boolean flag = false;
		try{
			flag = dao.runUpdate(sql, new String[]{nj});
			if(flag && !Base.isNull(rzsj)){
				sql = "insert into njrzsj(nj,rzsj) values (?,?)";
				flag = dao.runUpdate(sql,  new String[]{nj,rzsj});
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
		return flag == true? "yes":"no";
	}
	public boolean xssjTb() throws Exception {
		DAO dao = DAO.getInstance();
		String nj = Base.currNd;
		String sql = "{call gygl_zsxxtb(?)}";
		boolean res = dao.runProcuder(sql,new String []{nj});
		return res;
	}
}
