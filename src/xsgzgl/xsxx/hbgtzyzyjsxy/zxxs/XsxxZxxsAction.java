package xsgzgl.xsxx.hbgtzyzyjsxy.zxxs;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.DAO.DAO;
import xgxt.DAO.PicDAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.base.Encrypt;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.form.User;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.studentInfo.ynys.XsxxYnysService;
import xgxt.utils.CheckPower;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xsxx.comm.jbsz.XsxxJbszForm;
import xgxt.xsxx.comm.jbsz.XsxxJbszService;
import xgxt.xsxx.comm.jbxx.XsxxJbxxForm;
import xgxt.xsxx.comm.jbxx.XsxxJbxxService;
import xsgzgl.gygl.comm.GyglNewInit;

import com.zfsoft.basic.BasicAction;
import common.GlobalsVariable;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_在校学生_湖北国土资源职业学院_Action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XsxxZxxsAction extends BasicAction {
	

	/**
	 * 学生信息增加页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 * 
	 * @author honglin
	 * @date 2012-6-14
	 * 
	 */
	public ActionForward xsxxAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		CommanForm stuForm = (CommanForm) form;
		DAO dao = DAO.getInstance();
		StuInfoDAO stuDao = new StuInfoDAO();
		XsxxglService service = new XsxxglService();
		String xydm = stuForm.getXydm();
		String zydm = stuForm.getZydm();
		String bjdm = stuForm.getBjdm();
		String nj = stuForm.getNj();
		stuForm.setXb("1");
		request.setAttribute("rs", stuForm);
		
		request.setAttribute("ssList", stuDao.getSsList());//获取省市列表
		request.setAttribute("shiList", stuDao.getShiList("9999999").get("shiList"));
		request.setAttribute("xianList",  stuDao.getShiList("9999999").get("xianList"));
		request.setAttribute("jgshiList", stuDao.getShiList("9999999").get("shiList"));
		request.setAttribute("jgxianList",  stuDao.getShiList("9999999").get("xianList"));
		//户口
		request.setAttribute("hkshiList", stuDao.getShiList("9999999").get("shiList"));
		request.setAttribute("hkxianList", stuDao.getShiList("9999999").get("xianList"));
		// ----------end-------------
		xydm = (xydm == null ? "" : xydm);
		zydm = (zydm == null ? "" : zydm);
		bjdm = (bjdm == null ? "" : bjdm);
		nj = (nj == null ? "" : nj);
		String bjKey = xydm + "!!" + zydm + "!!" + nj;
		request.setAttribute("xydm", xydm);
		request.setAttribute("zydm", zydm);
		request.setAttribute("bjdm", bjdm);
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xydm));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("mzList", dao.getMzList());
		request.setAttribute("zzmmList", dao.getZzmmList());
		request.setAttribute("yhList", service.getYhList());//银行信息列表
		request.setAttribute("pyccList", service.getList(GlobalsVariable.XTWH_PYCC_LIST));//培养层次
		request.setAttribute("kslbList", service.getList(GlobalsVariable.XTWH_KSLB_LIST));//考生类别
		request.setAttribute("rxfsList", service.getList(GlobalsVariable.XTWH_RXFS_LIST));//入学方式
		request.setAttribute("pyfsList", service.getList(GlobalsVariable.XTWH_PYFS_LIST));//培养方式
		//存放相关家庭关系
		request.setAttribute("jtgxList", service.getJtgxList());
		StuInfoDAO stuInfoDao = new StuInfoDAO();
		request.setAttribute("xjztList", stuInfoDao.getXjztList());
		//校区信息
		request.setAttribute("yxdmList", stuInfoDao.getYxdmList());
		//获取title
		request.setAttribute("path", "stu_info_query.do?method=stuInfo");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xsxxAdd");
	}
	/**
	 * 学生信息修改页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 * 
	 * @author honglin
	 * @date 2012-6-14
	 */
	public ActionForward xsxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CommanForm stuForm = (CommanForm) form;
		HttpSession session = request.getSession();
		Base.chkSessionTimeOut(session);
		DAO dao = DAO.getInstance();
		StuInfoDAO stuDao = new StuInfoDAO();
		XsxxglService service = new XsxxglService();

		String oper = "update";
		String userName = session.getAttribute("userName").toString();
		String writeAble = CheckPower.checkUsrPower(userName, "stu_info_query.do") ? "yes" : "no";
		String xxdm = StandardOperation.getXxdm();
		String[] columns = null;
		HashMap<String, String> map = new HashMap<String, String>();

		String sql = "";
		String xb = "";// 性别单选
		String xydm = stuForm.getXydm();
		String zydm = stuForm.getZydm();
		String bjdm = stuForm.getBjdm();
		String nj = stuForm.getNj();
		String xh = stuForm.getXh();
		String type = request.getParameter("type");
		
		//地址信息是否采用标准码
		boolean dzxxqdmFlag = "yes".equalsIgnoreCase(service.getXsxxCs().get("dzxxqdm")) ? true : false;
		if ("update".equalsIgnoreCase(oper)) {
			xh = request.getParameter("xh");			
			if(type!=null && "details".equalsIgnoreCase(type)){
				request.setAttribute("details", "details");
			}						
			//数据查询

			columns = dao.getColumnName("select a.xjlb xjzt,a.syd lydq,a.hkszd hkd,a.* from view_xsxxb a where 1=2");
			sql = "select a.xjlb xjzt,a.syd lydq,a.hkszd hkd,a.* from view_xsxxb a where xh=?";	
			
			for(int i=0; i<columns.length; i++){
				columns[i] = columns[i].toLowerCase();
			}
			map = dao.getMap(sql, new String[] { xh }, columns);
			
			if("new".equalsIgnoreCase(Base.getInitProperties().get("edition"))){
				// sjf
				Map<String, String> fzxxMap = dao.getMapNotOut("select * from xsfzxxb a where a.xh=?", new String[]{xh});
				
				for(Map.Entry<String, String> fzxx : fzxxMap.entrySet()){
					String key = fzxx.getKey();
					String value = fzxx.getValue();
					
					if(map.containsKey(key)){
						if(StringUtils.isNull(map.get(key))){
							map.put(key, value);
						}
					}else {
						map.put(key, value);
					}
				}
			
				// ======================学生身体信息表 - qlj ================
				map.putAll(service.getXsstList(xh));
				// =============================================
				
			}
			// 汉字转换成数字
			xb = map.get("xb");
			if(Base.getXsxxwh().equalsIgnoreCase("bkwh")){
				if (xb != null && !"".equalsIgnoreCase(xb)) {
					map.put("xb", "男".equalsIgnoreCase(xb) ? "1" : "2");
				}
			}
			if(dzxxqdmFlag){//地址信息取标准码
				String syd = map.get("syd");				
				if(!Base.isNull(syd)){
					String[] arrDq = syd.split("/");
					map.put("syshen", (arrDq != null && arrDq.length>=1)?arrDq[0]:"");
					map.put("syshi", (arrDq != null && arrDq.length>=2)?arrDq[1]:"");
					map.put("syxian", (arrDq != null && arrDq.length>=3)?arrDq[2]:"");					
				}								
				if(StringUtils.isNotNull(map.get("jg"))){
					String[] arrDq = map.get("jg").split("/");
					map.put("jgshen", (arrDq != null && arrDq.length>=1)?arrDq[0]:"");
					map.put("jgshi", (arrDq != null && arrDq.length>=2)?arrDq[1]:"");
					map.put("jgxian", (arrDq != null && arrDq.length>=3)?arrDq[2]:"");
				}
				if(StringUtils.isNotNull(map.get("hkd"))){
					String[] arrDq = map.get("hkd").split("/");
					map.put("hkshen", (arrDq != null && arrDq.length>=1)?arrDq[0]:"");
					map.put("hkshi", (arrDq != null && arrDq.length>=2)?arrDq[1]:"");
					map.put("hkxian", (arrDq != null && arrDq.length>=3)?arrDq[2]:"");
				}
				request.setAttribute("ssList", stuDao.getSsList());	
				request.setAttribute("shiList", stuDao.getShiList(map.get("syshen")).get("shiList"));
				request.setAttribute("xianList",  stuDao.getShiList(map.get("syshen")).get("xianList"));
				request.setAttribute("jgshiList", stuDao.getShiList(map.get("jgshen")).get("shiList"));
				request.setAttribute("jgxianList",  stuDao.getShiList(map.get("jgshen")).get("xianList"));
				// ----------2010/5/13 edit by luojw -------------
				// 户口
				request.setAttribute("hkshiList", stuDao.getShiList(
						map.get("hkshen")).get("shiList"));
				request.setAttribute("hkxianList", stuDao.getShiList(
						map.get("hkshi")).get("xianList"));
				// ----------end-------------
			}
		
			XsxxJbxxForm myForm=new XsxxJbxxForm();
			XsxxJbxxService jbxxService =new XsxxJbxxService();
			if(Base.getXsxxwh().equalsIgnoreCase("kwh")){
				myForm.setTableName("xsxxb");
				myForm.setXh(xh);
				HashMap<String,String>hashMap=jbxxService.getXsxgxx(myForm);
				if(hashMap!=null && hashMap.size()>0){
					map.putAll(hashMap);
				}else{
					String msg = "学生信息数据源未确定，请联系相关人员！";
					request.setAttribute("yhInfo", msg);
					return new ActionForward("/yhInfo.do", false);
				}
			}
			
			//========2011.1.21 lr 加载取自其它模块的数据（如辅导员）
			
			// ======================可维护学生信息================================
			
			if(Base.getXsxxwh().equalsIgnoreCase("kwh")){
				myForm.setTableName("view_xsxxb");
				myForm.setXh(xh);
				map.putAll(jbxxService.getXsxgxx(myForm));
			}
			//  ======================================================
			
			map.putAll(service.getXsglxx(xh));
			//====end==============
			request.setAttribute("rs", map);
			xydm = map.get("xydm");
			bjdm = map.get("bjdm");
			zydm = map.get("zydm");
			nj = map.get("nj");
		}

		xydm = (xydm == null ? "" : xydm);
		zydm = (zydm == null ? "" : zydm);
		bjdm = (bjdm == null ? "" : bjdm);
		nj = (nj == null ? "" : nj);
		String bjKey = xydm + "!!" + zydm + "!!" + nj;
		request.setAttribute("xydm", xydm);
		request.setAttribute("zydm", zydm);
		request.setAttribute("bjdm", bjdm);
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xydm));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("oper", oper);
		request.setAttribute("mzList", dao.getMzList());
		request.setAttribute("zzmmList", dao.getZzmmList());
		request.setAttribute("yhList", service.getYhList());//银行信息列表
		request.setAttribute("pyccList", service.getList(GlobalsVariable.XTWH_PYCC_LIST));//培养层次
		request.setAttribute("kslbList", service.getList(GlobalsVariable.XTWH_KSLB_LIST));//考生类别
		request.setAttribute("rxfsList", service.getList(GlobalsVariable.XTWH_RXFS_LIST));//入学方式
		request.setAttribute("pyfsList", service.getList(GlobalsVariable.XTWH_PYFS_LIST));//培养方式
		request.setAttribute("sfzxk", request.getParameter("sfzxk"));
		
		//==================== 新版家庭信息与学生信息合并标志 - sjf ===================//
		request.setAttribute("edition", Base.getInitProperties().get("edition"));
		
		//存放相关家庭关系
		request.setAttribute("jtgxList", service.getJtgxList());
		//========================= end ==================================//
		
		//  =================判断学生信息是否可维护========================
		if(Base.getXsxxwh().equalsIgnoreCase("kwh")
				&& "add".equalsIgnoreCase(oper)){
			//判断非空字段
			if(stuDao.checkFkzd()){
				return new ActionForward("/xsxxJbxx.do?method=xsxxwh",false);
			}else{
				String msg = "非空字段未维护，请联系管理人员！";
				request.setAttribute("yhInfo", msg);
				return new ActionForward("/yhInfo.do", false);
			}
		//修改操作
		}else if(Base.getXsxxwh().equalsIgnoreCase("kwh")){
			if(!stuDao.checkFkzd()){
				String msg = "非空字段未维护，请联系管理人员！";
				request.setAttribute("yhInfo", msg);
				return new ActionForward("/yhInfo.do", false);
			}else{
				XsxxJbxxForm myForm=new XsxxJbxxForm();
				XsxxJbszForm model=new XsxxJbszForm();
				XsxxJbxxService jbxxService =new XsxxJbxxService();
				XsxxJbszService jbszService =new XsxxJbszService();
				XsxxYnysService ynysService = new XsxxYnysService();
				CommanForm dataSearchForm =new CommanForm();
				//显示区域
				request.setAttribute("xsqList",jbxxService.getXsqnrList(myForm));
				//字段位置
				request.setAttribute("zdwzList", jbxxService.getZdwzList(myForm));
				//启用字段
				request.setAttribute("qyzdList", jbxxService.getQyzdList(myForm));
				//显示区显示字段
				request.setAttribute("xsqzdList", jbszService.getXsqZdwzList(model));
				//加载下拉列表
				jbxxService.setXlkList(myForm, request);
				
				jbxxService.checkZdSize(request,model);
				request.setAttribute("ssList", ynysService.getSsList());
		        request.setAttribute("shiList", ynysService.getShiList(dataSearchForm.getJgshen()).get("shiList"));
				request.setAttribute("xianList",  ynysService.getShiList(dataSearchForm.getJgshen()).get("xianList"));
				FormModleCommon.setNjXyZyBjListForFdyBzr(request);
				return mapping.findForward("xsxxwh");
			}
		}
		StuInfoDAO stuInfoDao = new StuInfoDAO();
		//学籍状态，是否注册
		request.setAttribute("xjztList", stuInfoDao.getXjztList());
		//校区信息
		request.setAttribute("yxdmList", stuInfoDao.getYxdmList());
		
		//获取title
		//判断是否为历史学生信息，如果为no就代表是
		String sfzxk =  request.getParameter("sfzxk");
		if(sfzxk!=null){
			if(sfzxk.equals("no")){
				request.setAttribute("path", "stu_info_query.do?method=stuInfo&sfzxk=no");
				FormModleCommon.commonRequestSet(request);
			}else{
				request.setAttribute("path", "stu_info_query.do?method=stuInfo");
				FormModleCommon.commonRequestSet(request);
			}
		}else{
			request.setAttribute("path", "stu_info_query.do?method=stuInfo");
			FormModleCommon.commonRequestSet(request);
		}
		//获取title
		//request.setAttribute("path", "stu_info_query.do?method=stuInfo");
		//FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xsxxUpdate");	
	}

	
	/**
	 * 学生信息，查看详细页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 * @author honglin
	 * @date 2012-6-14
	 */
	public ActionForward xsxxDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XsxxglService xsxxglService = new XsxxglService();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		HashMap<String, String> map = new HashMap<String, String>();
		String xxdm = StandardOperation.getXxdm();
		String xh = request.getParameter("xh");
		String url = request.getParameter("url");
		String sql = "";
		
		String[] titleEn = null;
		String[] titleCn = null;
		
		titleEn = new String[]{"xsxx","jtxx","dtjs","pjpy","dwjl","xszz",
				            "qgzx","xljk","xsjx","wjcf","xscj","qtsj"};
		titleCn = new String[]{"基本信息","家庭信息","党团建设",
				            "评奖评优","对外交流","学生资助","勤工助学","心理健康",
				            "学生军训","违纪处分","学生成绩","其它数据"};
		if (userType.equalsIgnoreCase("stu")) {
			xh = userName;
		}			
		String page = "xsxxDetail";

		// 学生基本信息
		sql = "select a.xh,a.jkzk, a.xm,a.xb, a.csrq,(select xqmc from dm_zju_xq where dm=a.yxdm) yxdm, a.mzmc, a.zzmmmc,a.ykth, a.sfzh, a.syd lydq," +
			  " a.syd csdm, a.ssbh, a.nj, a.xymc, a.zymc, a.bjmc, a.xz, a.xjlb xjztm,a.sfhq," +
			  "a.lxdzxx, a.lxdh, a.sjhm, a.qsdh,a.hkszd,a.ksh,b.JTCY1_XM,b.JTCY2_XM," +
			  "b.jtszd, b.lxdh1 jtdh,a.zw,a.jg,b.yb,a.ccqj,a.qqhm,a.dah,a.ylbxh,a.yhmc,a.yhkh,pycc," +
			  "(select distinct pyccmc from xg_xsxx_pyccdmb c where a.pycc=c.pyccdm)pyccmc," +
			  "pyfs,(select distinct pyfsmc from xg_xsxx_pyfsdmb c where a.pyfs=c.pyfsdm)pyfsmc," +
			  "a.ykth,a.kh,sfzc,sfzd,sfzx,sfbys,sfyby,rxrq,byny,xmpy,cym,sg,tz,tc,byzh,xwzsbh," +
			  "kslb,(select distinct kslbmc from xg_xsxx_kslbdmb c where a.kslb=c.kslbdm)kslbmc," +
			  "rxfs,(select distinct rxfsmc from xg_xsxx_rxfsdmb c where a.rxfs=c.rxfsdm)rxfsmc" +
			  "  ,a.bz from view_xsxxb a left join xsfzxxb b on a.xh=b.xh where a.xh=?";
		
		map = dao.getMap(sql, new String[] {xh}, new String[] { "xh", "xm",
				"xb", "csrq","yxdm", "mzmc", "zzmmmc", "sfzh", "lydq", "csdm",
				"ssbh", "nj", "xymc", "zymc", "bjmc", "xz", "xjztm",
				"lxdzxx", "lxdh", "sjhm", "qsdh" ,"jg","jtszd","yb","ccqj",
				"qqhm","dah","ylbxh","yhmc","yhkh","ksh","jtdh","jtcy1_xm","jtcy2_xm",
				"sfzc","sfzd","sfzx","sfbys","sfyby","rxrq","byny","xmpy","cym",
				"sg","tz","tc","kslb","rxfs","hkszd","kh", "ykth","pycc","pyccmc","pyfs",
				"rxfsmc","kslbmc","pyfsmc","xwzsbh","byzh","jkzk","sfhq","bz"});
		if("yes".equalsIgnoreCase(xsxxglService.getXsxxCs().get("dzxxqdm"))){//地址信息取代码
			sql = " select (select b.qxmc from dmk_qx b where b.qxdm=substr(a.syd,0,instr(a.syd,'/')-1) )" 
				  + "||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.syd,instr(a.syd,'/')+1,instr(a.syd,'/',1,2)-instr(a.syd,'/')-1))"
			      + " ||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.syd,instr(a.syd,'/',-1)+1)) lydq,"
			      + "(select b.qxmc from dmk_qx b where b.qxdm=substr(a.hkszd,0,instr(a.hkszd,'/')-1) )" 
				  + "||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.hkszd,instr(a.hkszd,'/')+1,instr(a.hkszd,'/',1,2)-instr(a.hkszd,'/')-1))"
			      + " ||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.hkszd,instr(a.hkszd,'/',-1)+1)) hkszd,"
			      + "(select b.qxmc from dmk_qx b where b.qxdm=substr(a.jg,0,instr(a.jg,'/')-1) ) "
			      + "||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.jg,instr(a.jg,'/')+1,instr(a.jg,'/',1,2)-instr(a.jg,'/')-1))"
			      + "||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.jg,instr(a.jg,'/',-1)+1)) jg,bz from view_xsxxb a where xh=?";
			map.putAll(dao.getMap(sql, new String[]{xh}, new String[]{"lydq", "jg", "hkszd"}));
		}

		
		
		// 学籍异动
		String xqydSql = "select a.*,(select ydlbmc from dm_ydlb b where b.ydlbm = a.ydlbm) ydlbmc," +
				"(select xjzt from dm_ydlb b where b.ydlbm = a.ydlbm) xjzt from bks_xjydxx a where xh=?";
		map.putAll(dao.getMapNotOut(xqydSql, new String[]{xh}));
		
		if("/jygl/zgmy/jyxyslqdj_viewmore.jsp".equals(url)){
			page = "sjxyStuInto";
		}
		if(!Base.isNull(request.getParameter("page"))){
			page = request.getParameter("page");
		}
		
		//加载取自其它模块的数据（如辅导员）
		map.putAll(xsxxglService.getXsglxx(xh));
		//====end==============
		
		// ======================可维护学生信息================================
		XsxxJbxxForm myForm=new XsxxJbxxForm();
		XsxxJbxxService jbxxService =new XsxxJbxxService();
		XsxxJbszService jbszService =new XsxxJbszService();
		if(Base.getXsxxwh().equalsIgnoreCase("kwh")){
			myForm.setTableName("xsxxb");
			myForm.setXh(xh);
			HashMap<String,String> hashMap = jbxxService.getXsxgxx(myForm);
			if(hashMap!=null && hashMap.size()>0){
				map.putAll(hashMap);
			}else{
				String msg = "学生信息数据源未确定，请联系相关人员！";
				request.setAttribute("yhInfo", msg);
				return new ActionForward("/yhInfo.do", false);
			}
		}
		//  ======================================================
		
		request.setAttribute("rs", map);
		//学生照片地址
		request.setAttribute("src", "/xgxt/sjcz/xszp.jsp?xh=" + xh);
		request.setAttribute("xh", xh);
		request.setAttribute("xxdm", xxdm);	
		
		//选项卡集合
		List<HashMap<String, String>> xsPages = null;
		//默认显示
		String mrxs = "xsxx";
		
		// 新版本学生信息详细页面可以配置
		if("new".equalsIgnoreCase(Base.getInitProperties().get("edition"))){
			xsPages = xsxxglService.getXsPages();	
			//公寓信息
			request.setAttribute("showXsgy", "showXsgy");//显示公寓管理
			request.setAttribute("showZsjbxx", "showZsjbxx");//显示住宿基本信息
			request.setAttribute("xszsjbxx",xsxxglService.getXszsjbxx(xh));
			request.setAttribute("gygl_xqbj", GyglNewInit.XQBJ);//校区标记
			request.setAttribute("gygl_yqbj", GyglNewInit.YQBJ);//园区标记
			int gygl_colspan=5+("0".equals(GyglNewInit.XQBJ)?0:1)+("0".equals(GyglNewInit.YQBJ)?0:1);
			request.setAttribute("gygl_colspan", gygl_colspan);
		}else{
			xsPages = dao.arrayToList(titleEn, titleCn);
		}
		
		if(xsPages != null && xsPages.size()>0){
			mrxs = xsPages.get(0).get("en");
			request.setAttribute("mkxx", "1");
		}
		
		// 如果是打印操作
		if("print".equalsIgnoreCase(page)){
			String printMks = request.getParameter("print_mk");
			String[] printMk = printMks.split("split!!");
			
			request.setAttribute("printMk", printMk);
		}
			
		request.setAttribute("pages", xsPages);
		//学生资助是否使用通用的资助标志
		request.setAttribute("xszztyFlag", Base.getInitProperties().get("xszztyFlag"));
		request.setAttribute("doType", request.getParameter("doType"));
		request.setAttribute("mrxs", mrxs);
		request.setAttribute("edition", Base.getInitProperties().get("edition"));
		
		if(Base.getXsxxwh().equalsIgnoreCase("kwh")){
			XsxxJbszForm model=new XsxxJbszForm();
			XsxxYnysService ynysService = new XsxxYnysService();
			CommanForm dataSearchForm =new CommanForm();
			//显示区域
			request.setAttribute("xsqList",jbxxService.designXsxx(myForm));
			//字段位置
			request.setAttribute("zdwzList", jbxxService.getZdwzList(myForm));
			//启用字段
			request.setAttribute("qyzdList", jbxxService.getQyzdList(myForm));
			//显示区显示字段
			request.setAttribute("xsqzdList", jbszService.getXsqZdwzList(myForm));
			//加载下拉列表
			jbxxService.setXlkList(myForm, request);
			
			jbxxService.checkZdSize(request,model);
			request.setAttribute("ssList", ynysService.getSsList());
	        request.setAttribute("shiList", ynysService.getShiList(dataSearchForm.getJgshen()).get("shiList"));
			request.setAttribute("xianList",  ynysService.getShiList(dataSearchForm.getJgshen()).get("xianList"));
			FormModleCommon.setNjXyZyBjListForFdyBzr(request);
			//非打印就转跳到学生详细信息
			if(!"print".equalsIgnoreCase(page)){
				return mapping.findForward("xsxxcx");
			}else{
				return mapping.findForward("xsxxcxPrint");
			}
		}
		return mapping.findForward(page);
	}

	
	/**
	 * 学生信息保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 * @author honglin
	 * @date 2012-6-14
	 */
	public ActionForward xsxxSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		CommanForm studetail = (CommanForm) form;
		XsxxglService service = new XsxxglService();
		HttpSession session = request.getSession();
		Base.chkSessionTimeOut(session);
		Encrypt ept = new Encrypt();
		//初始化学生密码
		String mm = ept.encrypt("888888");
		DAO dao = DAO.getInstance();
		StuInfoDAO stuDao = new StuInfoDAO();	
		HashMap<String,String> map = new HashMap<String,String>();
		boolean flag = false;
		String doType = request.getParameter("oper");
		String userName = session.getAttribute("userName").toString();
		String writeAble = CheckPower.checkUsrPower(userName, "stu_info_query.do") ? "yes" : "no";
		String xxdm = StandardOperation.getXxdm();
		String[] columns = null;
		String[] jtxxCol = null;
		String[] jtxxValue = null;

		String xh = DealString.toGBK(studetail.getXh());
		String xm = DealString.toGBK(studetail.getXm());
		String xb = DealString.toGBK(studetail.getXb());
		String nj = DealString.toGBK(studetail.getNj());
		String xz = DealString.toGBK(studetail.getXz());
		String xy = DealString.toGBK(studetail.getXy());
		String zymc = DealString.toGBK(studetail.getZymc());
		String bjmc = DealString.toGBK(studetail.getBjmc());
		String bjdm = DealString.toGBK(studetail.getBjdm());
		String zydm = DealString.toGBK(studetail.getZydm());
		String xydm = DealString.toGBK(studetail.getXydm());

		String xmpy = DealString.toGBK(studetail.getXmpy());
		String cym = DealString.toGBK(studetail.getCym());
		String xszp = DealString.toGBK(studetail.getXszp());
		String csrq = DealString.toGBK(studetail.getCsrq());
		String sfzh = DealString.toGBK(studetail.getSfzh());
		String jg = DealString.toGBK(studetail.getJg());
		String mz = DealString.toGBK(studetail.getMz());
		String zzmm = DealString.toGBK(studetail.getZzmm());
		String syd = DealString.toGBK(studetail.getSyd());
		String xx = DealString.toGBK(studetail.getXx());
		String sg = DealString.toGBK(studetail.getSg());
		String tz = DealString.toGBK(studetail.getTz());
		String tc = DealString.toGBK(studetail.getTc());

		String lxdh = DealString.toGBK(studetail.getLxdh());
		String dzyx = DealString.toGBK(studetail.getDzyx());
		String sjhm = DealString.toGBK(studetail.getSjhm());

		String kslb = DealString.toGBK(studetail.getKslb());
		String rxfs = DealString.toGBK(studetail.getRxfs());
		String pyfs = DealString.toGBK(studetail.getPyfs());
		String pycc = DealString.toGBK(studetail.getPycc());
		String xjztm = DealString.toGBK(studetail.getXjzt());
		String bz = DealString.toGBK(studetail.getBz());
		String byxx = studetail.getByxx();

		String jkzk = DealString.toGBK(studetail.getJkzk());
		String jtdz = DealString.toGBK(studetail.getJtdz());
		String jtyb = DealString.toGBK(studetail.getJtyb());
		String whcd = DealString.toGBK(studetail.getWhcd());
		String jlcfjl = DealString.toGBK(studetail.getJlcfjl());

		String ssbh = DealString.toGBK(studetail.getSsbh());
		String ssdh = DealString.toGBK(studetail.getSsdh());
		String zsrq = DealString.toGBK(studetail.getZsrq());
		String zsjzrq = DealString.toGBK(studetail.getZsjzrq());
		String ssbz = DealString.toGBK(studetail.getSsbz());
		String ssch = DealString.toGBK(studetail.getSsch());
		String rxqdw= DealString.toGBK(studetail.getRxqdw());
		
		String rxrq = DealString.toGBK(studetail.getRxrq());
		String fdyxm = DealString.toGBK(studetail.getFdyxm());

		
		String qqhm = studetail.getQqhm();

		String kh = DealString.toGBK(studetail.getKh());
		String hkszd = DealString.toGBK(studetail.getHkszd());

		String sfzx = DealString.toGBK(studetail.getSfzx());
		String sfyby = DealString.toGBK(studetail.getSfyby());

		String ksh = studetail.getKsh();
		String byny = DealString.toGBK(studetail.getByny());
		String zsbh = DealString.toGBK(studetail.getZsbh());
		String zslb = DealString.toGBK(studetail.getZslb());
		String ccqj = DealString.toGBK(studetail.getCcqj());

		String dasfyl = DealString.toGBK(studetail.getDasfyl());
		String daylyy = DealString.toGBK(studetail.getDaylyy());

		String yxdm = studetail.getYxdm();
		String xwdm = studetail.getXw();
		String sfzz = DealString.toGBK(studetail.getSfzz());
		String sfsf = DealString.toGBK(studetail.getSfsf());
		String sfdl = DealString.toGBK(studetail.getSfdl());
		String dxhwp = DealString.toGBK(studetail.getDxhwp());
		String bysj = studetail.getBysj();
		String zxwyyzdm = studetail.getZxwyyzdm();
		String wydj = DealString.toGBK(studetail.getWydj());
		String jsjdj = DealString.toGBK(studetail.getJsjdj());
		String yzbm = DealString.toGBK(studetail.getYzbm());
		String lxdz = DealString.toGBK(studetail.getLxdz());
		String shzw = DealString.toGBK(studetail.getShzw());
		String jypx = DealString.toGBK(studetail.getJypx());
		String xmsj = DealString.toGBK(studetail.getXmsj());
		String zgzs = DealString.toGBK(studetail.getZgzs());
		String jljn = DealString.toGBK(studetail.getJljn());
		String sybz1 = DealString.toGBK(studetail.getSybz1());
		String sybz2 = DealString.toGBK(studetail.getSybz2());
		String sybz3 = DealString.toGBK(studetail.getSybz3());
		String xldm = studetail.getXldm();
		String zkzh = studetail.getZkzh();//准考证号
		String grjl = DealString.toGBK(studetail.getGrjl());//个人简历
		String sfcj = DealString.toGBK(studetail.getSfcj());
		String yhkh = DealString.toGBK(studetail.getYhkh());//银行卡号
		String sfbys = DealString.toGBK(studetail.getSfbys());//是否毕业生
		String yhdm = studetail.getYhdm();
		
		String hkshen = studetail.getHkshen();
		String hkshi = studetail.getHkshi();
		String hkxian = studetail.getHkxian();
		String zcsxhm = studetail.getZcsxhm();
		String rxqwhcd = studetail.getRxqwhcd();
		String hkxz = studetail.getHkxz();
		
		String xzcm= DealString.toGBK(studetail.getXzcm());//鞋子尺码
		String xsxw= DealString.toGBK(studetail.getXsxw());//学生胸围
		String sfhq = studetail.getSfhq();//该字段存储浙江建设的“是否进修生”
		
		
		
		studetail.setSfbys(sfbys);
		studetail.setDasfyl(dasfyl);
		studetail.setDaylyy(daylyy);
		studetail.setKh(kh);
		studetail.setFdyxm(fdyxm);
		studetail.setXx(xx);
		studetail.setWhcd(whcd);
		studetail.setJkzk(jkzk);
		studetail.setJtdz(jtdz);
		studetail.setByxx(byxx);
		studetail.setJlcfjl(jlcfjl);
		studetail.setKslb(kslb);
		studetail.setRxfs(rxfs);
		studetail.setPyfs(pyfs);
		studetail.setPycc(pycc);
		studetail.setXszp(xszp);
		studetail.setSsbh(ssbh);
		studetail.setSsdh(ssdh);
		studetail.setZsrq(zsrq);
		studetail.setZsjzrq(zsjzrq);
		studetail.setSsbz(ssbz);
		studetail.setSsch(ssch);
		studetail.setXh(xh);
		studetail.setXy(xy);
		studetail.setZymc(zymc);
		studetail.setBjmc(bjmc);
		studetail.setBjdm(bjdm);
		studetail.setZydm(zydm);
		studetail.setXydm(xydm);
		studetail.setBz(bz);
		studetail.setXm(xm);
		studetail.setXmpy(xmpy);
		studetail.setNj(nj);
		studetail.setSyd(syd);
		studetail.setCsrq(csrq);
		studetail.setSfzh(sfzh);
		studetail.setXb(xb);
		studetail.setMz(mz);
		studetail.setZzmm(zzmm);
		studetail.setLxdh(lxdh);
		studetail.setDzyx(dzyx);
		studetail.setCym(cym);
		studetail.setSg(sg);
		studetail.setTz(tz);
		studetail.setTc(tc);
		studetail.setRxqdw(rxqdw);
		

		String[] values1 = null;
		String sql = "";
		xy = dao.getOneRs("select xymc from view_njxyzybj where xydm=?", new String[] { xydm }, "xymc");
		zymc = dao.getOneRs("select zymc from view_njxyzybj where zydm=?", new String[] { zydm }, "zymc");
		bjmc = dao.getOneRs("select bjmc from view_njxyzybj where bjdm=?", new String[] { bjdm }, "bjmc");

		boolean dzxxqdmFlag = "yes".equalsIgnoreCase(service.getXsxxCs().get("dzxxqdm")) ? true : false;
		
		// 是否在学生信息中维护家庭信息
		String edition = Base.getInitProperties().get("edition");
		
		if("new".equalsIgnoreCase(edition)){
			String lxdh1 = studetail.getLxdh1();
			String jtszd = studetail.getJtszd();
			String jjzk = studetail.getJjzk();
			String jtcy1_xm = studetail.getJtcy1_xm();
			String jtcy2_xm = studetail.getJtcy2_xm();
			String jtcy3_xm = studetail.getJtcy3_xm();
			String jtcy1_gx = studetail.getJtcy1_gx();
			String jtcy2_gx = studetail.getJtcy2_gx();
			String jtcy3_gx = studetail.getJtcy3_gx();
			String jtcy1_nl = studetail.getJtcy1_nl();
			String jtcy2_nl = studetail.getJtcy2_nl();
			String jtcy3_nl = studetail.getJtcy3_nl();
			String jtcy1_sfzh = studetail.getJtcy1_sfzh();
			String jtcy2_sfzh = studetail.getJtcy2_sfzh();
			String jtcy3_sfzh = studetail.getJtcy3_sfzh();
			String jtcy1_mz = studetail.getJtcy1_mz();
			String jtcy2_mz = studetail.getJtcy2_mz();
			String jtcy3_mz = studetail.getJtcy3_mz();
			String jtcy1_zzmm = studetail.getJtcy1_zzmm();
			String jtcy2_zzmm = studetail.getJtcy2_zzmm();
			String jtcy3_zzmm = studetail.getJtcy3_zzmm();
			String jtcy1_zy = studetail.getJtcy1_zy();
			String jtcy2_zy = studetail.getJtcy2_zy();
			String jtcy3_zy = studetail.getJtcy3_zy();
			String jtcy1_zw = studetail.getJtcy1_zw();
			String jtcy2_zw = studetail.getJtcy2_zw();
			String jtcy3_zw = studetail.getJtcy3_zw();
			String jtcy1_lxdh1 = studetail.getJtcy1_lxdh1();
			String jtcy2_lxdh1 = studetail.getJtcy2_lxdh1();
			String jtcy3_lxdh1 = studetail.getJtcy3_lxdh1();
			String jtcy1_lxdh2 = studetail.getJtcy1_lxdh2();
			String jtcy2_lxdh2 = studetail.getJtcy2_lxdh2();
			String jtcy3_lxdh2 = studetail.getJtcy3_lxdh2();
			String jtcy1_gzdz = studetail.getJtcy1_gzdz();
			String jtcy2_gzdz = studetail.getJtcy2_gzdz();
			String jtcy3_gzdz = studetail.getJtcy3_gzdz();
		
			jtxxCol = new String[]{"xh", "lxdh1", "yb", "jtszd", "jjzk", "jtcy1_xm", "jtcy2_xm", "jtcy3_xm",
					"jtcy1_gx", "jtcy2_gx", "jtcy3_gx", "jtcy1_nl", "jtcy2_nl", "jtcy3_nl", "jtcy1_sfzh", "jtcy2_sfzh",
					"jtcy3_sfzh", "jtcy1_mz", "jtcy2_mz", "jtcy3_mz", "jtcy1_zzmm", "jtcy2_zzmm", "jtcy3_zzmm", "jtcy1_zy",
					"jtcy2_zy", "jtcy3_zy", "jtcy1_zw", "jtcy2_zw", "jtcy3_zw", "jtcy1_lxdh1", "jtcy2_lxdh1", "jtcy3_lxdh1",
					"jtcy1_lxdh2", "jtcy2_lxdh2", "jtcy3_lxdh2", "jtcy1_gzdz", "jtcy2_gzdz", "jtcy3_gzdz"};
			
			jtxxValue = new String[]{xh, lxdh1, jtyb, jtszd, jjzk, jtcy1_xm, jtcy2_xm, jtcy3_xm,
					jtcy1_gx, jtcy2_gx, jtcy3_gx, jtcy1_nl, jtcy2_nl, jtcy3_nl, jtcy1_sfzh, jtcy2_sfzh,
					jtcy3_sfzh, jtcy1_mz, jtcy2_mz, jtcy3_mz, jtcy1_zzmm, jtcy2_zzmm, jtcy3_zzmm, jtcy1_zy,
					jtcy2_zy, jtcy3_zy, jtcy1_zw, jtcy2_zw, jtcy3_zw, jtcy1_lxdh1, jtcy2_lxdh1, jtcy3_lxdh1,
					jtcy1_lxdh2, jtcy2_lxdh2, jtcy3_lxdh2, jtcy1_gzdz, jtcy2_gzdz, jtcy3_gzdz};
		}
		
		columns = new String[] { "xh", "xy", "zymc", "bjmc", "bjdm",
				"zydm", "xydm", "bz", "xm", "xmpy", "nj", "syd", "csrq",
				"sfzh", "xb", "mz", "zzmm", "lxdh", "dzyx", "cym", "sg",
				"tz", "tc", "kslb", "rxfs", "pyfs", "pycc", "xjztm", "jg",
				"xszp", "xz", "sjhm","kh","dasfyl","daylyy","yxdm","xw",
				"sfzz","sfsf","sfdl","dxhwp","rxrq","bysj","zsbh","zxwyyzdm",
				"wydj","jsjdj","qqhm","lxdz","yzbm","shzw","jypx","xmsj",
				"zgzs","jljn","sybz1","sybz2","sybz3","xldm","zslb","sfyby",
				"sfzx","byny","ccqj","jlcfjl","grjl","zkzh","sfcj","sfbys",
				"yhdm","yhkh", "hkshen", "hkshi", "hkxian"};

		values1 = new String[] { xh, xy, zymc, bjmc, bjdm, zydm, xydm, bz,
				xm, xmpy, nj, syd, csrq, sfzh, xb, mz, zzmm, lxdh, dzyx,
				cym, sg, tz, tc, kslb, rxfs, pyfs, pycc, xjztm, jg, xszp,
				xz, sjhm , kh, dasfyl, daylyy,yxdm,xwdm,sfzz,sfsf,sfdl,dxhwp,
				rxrq,bysj,zsbh,zxwyyzdm,wydj,jsjdj,qqhm,lxdz,yzbm,shzw,jypx,
				xmsj,zgzs,jljn,sybz1,sybz2,sybz3,xldm,zslb,sfyby,sfzx,byny,ccqj,
				jlcfjl,grjl,zkzh,sfcj,sfbys,yhdm,yhkh, hkshen, hkshi, hkxian};
		if(Base.getXsxxwh().equalsIgnoreCase("bkwh")){
			if(dzxxqdmFlag){
				jg           = studetail.getJgshen()+"/"+studetail.getJgshi()+"/"+studetail.getJgxian();
				syd          = studetail.getSyshen()+"/"+studetail.getSyshi()+"/"+studetail.getSyxian();
				hkszd        = studetail.getHkshen()+"/"+studetail.getHkshi()+"/"+studetail.getHkxian();
			}
		}
		// 其他学校
		columns = new String[] { "xh", "xy", "zymc", "bjmc", "bjdm",
				"zydm", "xydm", "bz", "xm", "xmpy", "nj", "syd", "csrq",
				"sfzh", "xb", "mz", "zzmm", "lxdh", "dzyx", "cym", "sg",
				"tz", "tc", "kslb", "rxfs", "pyfs", "pycc", "xjztm", "jg",
				"xszp", "xz", "sjhm","kh","dasfyl","daylyy","yxdm","xw",
				"sfzz","sfsf","sfdl","dxhwp","rxrq","bysj","zsbh","zxwyyzdm",
				"wydj","jsjdj","qqhm","lxdz","yzbm","shzw","jypx","xmsj",
				"zgzs","jljn","sybz1","sybz2","sybz3","xldm","zslb","sfyby",
				"sfzx","byny","ccqj","jlcfjl","grjl","zkzh","sfcj","sfbys",
				"yhdm","yhkh","hkszd","dah","ylbxh","ksh","rxqdw","rxqdwyb",
				"rxqdwdz","gzbx","ssbh","qsdh","sfgat","sfssmz","rxqwhcd",
				"zcsxhm","hkxz","sfzc","sfzd","jkzk","sfhq"};

		values1 = new String[] { xh, xy, zymc, bjmc, bjdm, zydm, xydm, bz,
				xm, xmpy, nj, syd, csrq, sfzh, xb, mz, zzmm, lxdh, dzyx,
				cym, sg, tz, tc, kslb, rxfs, pyfs, pycc, xjztm, jg, xszp,
				xz, sjhm , kh, dasfyl, daylyy,yxdm,xwdm,sfzz,sfsf,sfdl,dxhwp,
				rxrq,bysj,zsbh,zxwyyzdm,wydj,jsjdj,qqhm,lxdz,yzbm,shzw,jypx,
				xmsj,zgzs,jljn,sybz1,sybz2,sybz3,xldm,zslb,sfyby,sfzx,byny,ccqj,
				jlcfjl,grjl,zkzh,sfcj,sfbys,yhdm,yhkh,hkszd,studetail.getDah(),
				studetail.getYlbxh(),ksh,studetail.getRxqdw(),studetail.getRxqdwyb(),
				studetail.getRxqdwdz(),studetail.getGzbx(),studetail.getSsbh(),
				studetail.getQsdh(),studetail.getSfgat(),studetail.getSfssmz(),
				rxqwhcd,zcsxhm,hkxz,studetail.getSfzc(),studetail.getSfzd(),studetail.getJkzk(),sfhq};
		// 保存学生基本信息
		List<HashMap<String, String>> oneStu = dao.getList("select xh from xsxxb where xh=?", new String[] { xh }, new String[] { "xh" });
		if (oneStu != null && oneStu.size() > 0) {
			// xsxxb中已经存在该学生			
			User user = getUser(request);
			flag = StandardOperation.update("xsxxb", columns, values1, "xh", xh, user);
			
			// 修改家庭信息
			if(flag && "new".equalsIgnoreCase(edition)){
				List<HashMap<String, String>> jtxxList = dao.getList("select xh from xsfzxxb where xh=?", new String[] { xh }, new String[] { "xh" });
				
				if(jtxxList != null && jtxxList.size()>0){
					StandardOperation.update("xsfzxxb", jtxxCol, jtxxValue, "xh", xh, user);
				}else {
					StandardOperation.insert("xsfzxxb", jtxxCol, jtxxValue, request);
				}
			}
			if (flag) {
				//保存联系方式
				flag = StandardOperation.update("xsfzxxb", new String[] { "sjhm", "email", "lxdh2" }, new String[] { sjhm, dzyx, lxdh }, "xh", xh, user);
			}
		} else {
			// 信息插入学生信息表
			flag = StandardOperation.insert("xsxxb", columns, values1, request);
			
			// =============================== 新版本插入家庭信息 ==================================== //
			if(flag && "new".equalsIgnoreCase(edition)){
				StandardOperation.insert("xsfzxxb", jtxxCol, jtxxValue, request);
			}
			//增加照片
			if(flag && Base.getXsxxwh().equalsIgnoreCase("kwh")){
				FormFile file = studetail.getUploadFile();
				if(file!=null && file.getFileSize()>0){
					if(file.getFileSize()>=100*1024){
						request.setAttribute("errMsg", "文件过大(大于100K)");
						return mapping.findForward("success");
					}
					if (file == null) {
						return mapping.findForward("false");
					}
					InputStream in = file.getInputStream();
					PicDAO picDAO = new PicDAO();
					picDAO.savePic(in, xh,"stu");
					in.close();
				}
			}
			
			// =============================== END ================================================= //
			
			if (flag) {
				// 初始化密码
				int num = Integer.parseInt(dao.getOneRs(
						"select count(*) num from view_xsjbxx where xh=?",
						new String[] { xh }, "num"));
				if (num <= 0) {
					num = Integer.parseInt(dao.getOneRs(
							"select count(*) num from xsmmb where xh=?",
							new String[] { xh }, "num"));
					if (num <= 0) {
						sql = "insert into xsmmb (xh,mm) values(?,?)";
						flag = dao.runUpdate(sql, new String[] { xh, mm });
					}
				}
			}			
		}
		
		//数据查询
		columns = dao.getColumnName("select a.xjlb xjzt,a.syd lydq,a.* from view_xsxxb a where 1=2");
		sql = "select a.xjlb xjzt,a.syd lydq,a.* from view_xsxxb a where xh=?";	

		for(int i=0; i<columns.length; i++){
			columns[i] = columns[i].toLowerCase();
		}
		map = dao.getMap(sql, new String[] { xh }, columns);
		
		// 如果是新版本就放入家庭信息
		if("new".equalsIgnoreCase(edition)){
			map.putAll(dao.getMapNotOut("select * from xsfzxxb a where a.xh=?", new String[]{xh}));
		}

		// 汉字转换成数字
		xb = map.get("xb");
		if (xb != null && !"".equalsIgnoreCase(xb)) {
			map.put("xb", "男".equalsIgnoreCase(xb) ? "1" : "2");
		}		
		if(dzxxqdmFlag){
			map.put("jgshen", studetail.getJgshen());
			map.put("jgshi", studetail.getJgshi());
			map.put("jgxian",studetail.getJgxian());
			map.put("syshen",studetail.getSyshen());
			map.put("syshi", studetail.getSyshi());
			map.put("syxian",studetail.getSyxian());
			map.put("hkshen",studetail.getHkshen());
			map.put("hkshi", studetail.getHkshi());
			map.put("hkxian",studetail.getHkxian());
			request.setAttribute("ssList", stuDao.getSsList());	
			request.setAttribute("shiList", stuDao.getShiList(studetail.getSyshen()).get("shiList"));
			request.setAttribute("xianList",  stuDao.getShiList(studetail.getSyshen()).get("xianList"));
			request.setAttribute("jgshiList", stuDao.getShiList(studetail.getJgshen()).get("shiList"));
			request.setAttribute("jgxianList",  stuDao.getShiList(studetail.getJgshen()).get("xianList"));
			request.setAttribute("hkshiList", stuDao.getShiList(studetail.getJgshen()).get("shiList"));
			request.setAttribute("hkxianList",  stuDao.getShiList(studetail.getJgshen()).get("xianList"));
		}
		xydm = (xydm == null ? "" : xydm);
		zydm = (zydm == null ? "" : zydm);
		nj = (nj == null ? "" : nj);
		String bjKey = xydm + "!!" + zydm + "!!" + nj;

		request.setAttribute("rs", map);
		request.setAttribute("oper", doType);
		request.setAttribute("result", flag);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("zydm", zydm);
		request.setAttribute("xydm", xydm);
		request.setAttribute("bjdm", bjdm);
		request.setAttribute("jtgxList", service.getJtgxList());
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xydm));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("mzList", dao.getMzList());
		request.setAttribute("zzmmList", dao.getZzmmList());
		request.setAttribute("yhList", service.getYhList());//银行信息列表
		request.setAttribute("pyccList", service.getList(GlobalsVariable.XTWH_PYCC_LIST));//培养层次
		request.setAttribute("kslbList", service.getList(GlobalsVariable.XTWH_KSLB_LIST));//考生类别
		request.setAttribute("rxfsList", service.getList(GlobalsVariable.XTWH_RXFS_LIST));//入学方式
		request.setAttribute("pyfsList", service.getList(GlobalsVariable.XTWH_PYFS_LIST));//培养方式
		
		if(Base.getXsxxwh().equalsIgnoreCase("kwh")){
			XsxxJbxxForm myForm=new XsxxJbxxForm();
			XsxxJbszForm model=new XsxxJbszForm();
			XsxxJbxxService jbxxService =new XsxxJbxxService();
			XsxxJbszService jbszService =new XsxxJbszService();
			XsxxYnysService ynysService = new XsxxYnysService();
			CommanForm dataSearchForm =new CommanForm();
			//显示区域
			request.setAttribute("xsqList",jbxxService.getXsqnrList(myForm));
			//字段位置
			request.setAttribute("zdwzList", jbxxService.getZdwzList(myForm));
			//启用字段
			request.setAttribute("qyzdList", jbxxService.getQyzdList(myForm));
			//显示区显示字段
			request.setAttribute("xsqzdList", jbszService.getXsqZdwzList(model));
			//加载下拉列表
			jbxxService.setXlkList(myForm, request);
			
			jbxxService.checkZdSize(request,model);
			request.setAttribute("ssList", ynysService.getSsList());
	        request.setAttribute("shiList", ynysService.getShiList(dataSearchForm.getJgshen()).get("shiList"));
			request.setAttribute("xianList",  ynysService.getShiList(dataSearchForm.getJgshen()).get("xianList"));
			FormModleCommon.setNjXyZyBjListForFdyBzr(request);
			return mapping.findForward("xsxxwh");
		}
		//ForwardConfig[name=stuinfo,path=/sjcz/stu_info_modify.jsp,redirect=false,contextRelative=false,module=null]
		return transferPage(request, mapping);
	}

	public ActionForward transferPage(HttpServletRequest request, ActionMapping mapping){
		DAO dao = DAO.getInstance();
		StuInfoDAO stuInfoDao = new StuInfoDAO();
		String xxdm = StandardOperation.getXxdm();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		request.setAttribute("xjztList", stuInfoDao.getXjztList());
		//校区信息
		request.setAttribute("yxdmList", stuInfoDao.getYxdmList());
		
		request.setAttribute("path", "stu_info_query.do?method=stuInfo");
		FormModleCommon.commonRequestSet(request);
		String type=request.getParameter("oper");
		if(type!=null){
			if(type.equals("update")){
				return mapping.findForward("stuinfo2");  
			}else{
				return mapping.findForward("stuinfo");
			}
		}else{
			return mapping.findForward("stuinfo");
		}
		
	}

}
