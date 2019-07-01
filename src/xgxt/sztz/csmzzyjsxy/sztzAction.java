package xgxt.sztz.csmzzyjsxy;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WritableFont.FontName;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.CommService;
import xgxt.comm.xml.XMLReader;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.mdqr.MdqrService;
import xgxt.sztz.dao.SztzDao;
import xgxt.sztz.form.SztzForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.RowidToPk;

import common.Globals;

/**
 * 
 * @describe 长沙民政职业技术学院素质拓展
 * @author lp 2007-12-26
 */
public class sztzAction extends DispatchAction {
	public final ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		SztzForm chkUser = (SztzForm) form;
		HttpSession session = request.getSession();
//		String userType = session.getAttribute("userOnLine").toString();
//		boolean isStu = (userType.equalsIgnoreCase("student"));
//		String sUName = session.getAttribute("userName").toString();
		int i = Base.chkTimeOut(request.getSession());
		if (i <= 2) {
			chkUser.setErrMsg("登陆超时，请重新登陆！");
			return new ActionForward("/login.jsp", false);
		}

		if (session == null) {
			request.setAttribute("errMsg", "sadfsdf");
			return mapping.findForward("false");
		}
		String writeAble = Base.getWriteAble(request);
		request.setAttribute("writeAble", writeAble);
		return super.execute(mapping, form, request, response);
	}

	/**
	 * @describe 拓展项目申报
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tzxm_sb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		SztzDao sztzDao = new SztzDao();
		SztzForm sztzForm = (SztzForm) form;
		String userType = session.getAttribute("userType").toString();
		HashMap<String, String> map = new HashMap<String, String>();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String userNameReal = session.getAttribute("userNameReal").toString();
//		String userDepMc = (session.getAttribute("bmmc")==null)?"":session.getAttribute("bmmc").toString(); 
		String xh = "";
		String doType = DealString.toString(request.getParameter("doType"));
		String act = DealString.toString(request.getParameter("act"));
		String pkValue = DealString.toString(request.getParameter("pkValue"));
		String curDep = dao.getOneRs("select xgbdm from xtszb where rownum=1 ",new String[]{},"xgbdm");
		String[] oldV = null;// 旧值
		String yhcz;
		String sql = "";
		String userGB = "";// 班干部操作权限
		map.put("xn", Base.currXn);
		map.put("xq", Base.currXq);
		
		
		
		String tips = "素质拓展 - 拓展项目申报 - 申报";
		String xxdm = dao.getXxdm();
		if(Base.isNull(request.getParameter("xydm"))){
			map.put("xydm", userDep);
		}
		if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){
			tips = "素质拓展 - 素质拓展计划项目 - 申报";
			request.setAttribute("isCSMZ", "isCSMZ");
		}else if(Globals.XXDM_LZZYJSXY.equalsIgnoreCase(xxdm)){
			tips = "第二课堂活动 - 活动项目申报 - 申报";
		}
//		项目负责人（申报人）初始值
		if(Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)){
			map.put("xmsbr",userName);
			map.put("szbmmc", dao.getOneRs("select bmmc from zxbz_xxbmdm where bmdm=?",new String[]{userDep},"bmmc"));
		}else{
			map.put("xmsbrmc", userNameReal);
			map.put("szbmmc", dao.getOneRs("select bmmc from zxbz_xxbmdm where bmdm=?",new String[]{userDep},"bmmc"));
		}
		request.setAttribute("isSTU", "no");// 是否是学生用户
		if ("stu".equalsIgnoreCase(userType)) {
			if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){
				userGB = sztzDao.getBgbPower(userName);
				if (userGB.equalsIgnoreCase("0")) {
					request.setAttribute("errMsg", "您无权访问该页面！");
					return new ActionForward("/errMsg.do", false);
				} else {				
					String bjmc = dao.getOneRs("select bjmc from view_xsjbxx where xh=?",new String[] { userName }, "bjmc");
					request.setAttribute("bjmc", bjmc);
					request.setAttribute("isBJXM", "isBJXM");

				}
				xh = userName;
			}else{
				request.setAttribute("errMsg", "您无权访问该页面！");
				return new ActionForward("/errMsg.do", false);
			}
		}
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String xmmc = DealString.toGBK(request.getParameter("xmmc"));
		String kmdm = request.getParameter("kmdm");
		String xmjb = DealString.toGBK(request.getParameter("xmjb"));
		String xydm = sztzForm.getXydm();

		boolean done = false;

		if (!userDep.equalsIgnoreCase(curDep)) {//不属于学工部用户
			xydm = userDep;
			sztzForm.setXydm(userDep);
			userType = "stu".equalsIgnoreCase(userType)?userType:"xy";//非学生班干部用户设置为"xy"用户	
		}	
		if (act.equalsIgnoreCase("save")) {// 提交保存			
			String zbsj = request.getParameter("zbsj");
			String zzdw = DealString.toGBK(request.getParameter("zzdw"));
			String xmms = DealString.toGBK(request.getParameter("xmms"));
			String hddx = DealString.toGBK(request.getParameter("hddx"));
			String bjdm = DealString.toGBK(request.getParameter("bjdm"));
			String hdjhrs = request.getParameter("hdjhrs");
			String hdxs  = request.getParameter("hdxs");
			String xmsbr = request.getParameter("xmsbr");
			if(Base.isNull(xmsbr)){
				xmsbr = userName;
			}								
			if (doType.equalsIgnoreCase("modi")) {// 修改记录
				yhcz = "修改记录";
				sql = "select * from csmz_tzxmb where id=?";
				oldV = dao.getOneRs(sql, new String[] { pkValue },
						new String[] { "xn", "xq", "xmmc", "kmdm", "xydm",
						"zzdw", "zbsj", "xmjb", "xmms","hdjhrs","hdxs" });
				if(Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)){
					sql = "update csmz_tzxmb set xn=?,xq=?,xmmc=?,kmdm=?,xydm=?,zzdw=?,zbsj=?,xmjb=?,xmms=? ,hddx=?,xmsbr=?,bjdm=?,hdjhrs=?,hdxs=? where id=?";
					done = dao.runUpdate(sql, new String[] { xn, xq, xmmc, kmdm,
							xydm, zzdw, zbsj, xmjb, xmms, hddx,xmsbr,bjdm,hdjhrs,hdxs,pkValue });
					map.put("xmmc", xmmc);
					map.put("xmjb", xmjb);
					map.put("zbsj", zbsj);
					map.put("zzdw", zzdw);
					map.put("hddx", hddx);
					map.put("xmms", xmms);
				}else{
					sql = "update csmz_tzxmb set xn=?,xq=?,xmmc=?,kmdm=?,xydm=?,zzdw=?,zbsj=?,xmjb=?,xmms=? ,hddx=?,bjdm=?,hdjhrs=?,hdxs=? where id=?";
					done = dao.runUpdate(sql, new String[] { xn, xq, xmmc, kmdm,
							xydm, zzdw, zbsj, xmjb, xmms, hddx,bjdm,hdjhrs,hdxs, pkValue });
					map.put("xmmc", xmmc);
					map.put("xmjb", xmjb);
					map.put("zbsj", zbsj);
					map.put("zzdw", zzdw);
					map.put("hddx", hddx);
					map.put("xmms", xmms);
				}
			} else {// 增加记录
				yhcz = "增加记录";
				if (userType.equalsIgnoreCase("stu")) {
					sql = "select * from csmz_tzxmb where xn=? and xq=? and xmmc=? and xmjb=? and xydm=? and kmdm=? and xysh='通过'";
				} else if (userType.equalsIgnoreCase("xx")
						|| userType.equalsIgnoreCase("admin")
						|| userType.equalsIgnoreCase("xy")) {
					sql = "select * from csmz_tzxmb where xn=? and xq=? and xmmc=? and xmjb=? and xydm=? and kmdm=? and xxsh='通过'";
				}

				String isExist = dao.returntag(sql, new String[] { xn, xq,
						xmmc,xmjb,xydm,kmdm });				
				if ("empty".equalsIgnoreCase(isExist)) {// 记录不存在时插入
					sql = "insert into csmz_tzxmb(id,xn,xq,xmmc,kmdm,xydm,zzdw,zbsj,xmjb,xmms,hddx,xmsbr,bjdm,hdjhrs,hdxs)values(S_CSMZ_TZXMB_ID.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					done = dao.runUpdate(sql, new String[] { xn, xq, xmmc,
							kmdm, xydm, zzdw, zbsj, xmjb, xmms, hddx,xmsbr,bjdm,hdjhrs,hdxs });
				} else {// 记录存在时不操作
					request.setAttribute("isExist", "isExist");
				}
				map.put("xmmc", xmmc);
				map.put("xmjb", xmjb);
				map.put("zbsj", zbsj);
				map.put("zzdw", zzdw);
				map.put("hddx", hddx);
				map.put("xmms", xmms);
				map.put("xydm", xydm);	
				
			}
			if (done) {
				if (doType.equalsIgnoreCase("modi")) {
					sql = "delete from csmz_tzxmjxb where xmid=?";
					done = dao.runUpdate(sql, new String[] { pkValue });
				} else {
					sql = "delete from csmz_tzxmjxb where xmid in (select max(to_number(id)) from csmz_tzxmb)";
					done = dao.runUpdate(sql, new String[] {});
				}
				if (done) {
					//硅湖学院  限制分
					if(Globals.XXDM_GHZYJSXY.equalsIgnoreCase(xxdm)){
						for (int i = 1; i <= 15; i++) {
							String jxm = DealString.toGBK(DealString
									.toString(request.getParameter(("jxm" + i))));
							String jxnr = DealString.toGBK(DealString
									.toString(request.getParameter(("jxnr" + i))));
							String xzf = DealString.toGBK(DealString
									.toString(request.getParameter(("xzf" + i))));
							if (jxm != "" || !jxm.equalsIgnoreCase("")) {
								if (doType.equalsIgnoreCase("modi")) {
									sql = "insert into csmz_tzxmjxb(xmid,jxid,jxm,xf,xzf)values(?,?,?,?,?)";
									dao.runUpdate(sql, new String[] { pkValue,
											String.valueOf(i), jxm, jxnr,xzf });
								} else {
									sql = "insert into csmz_tzxmjxb(xmid,jxid,jxm,xf,xzf)values((select max(to_number(id)) from csmz_tzxmb),?,?,?,?)";
									dao.runUpdate(sql, new String[] {
											String.valueOf(i), jxm, jxnr,xzf });
								}
								map.put("jxm"+i, jxm);
								map.put("jxnr"+i, jxnr);
								map.put("xzf"+i, xzf);
							}
						}
					}else{
						for (int i = 1; i <= 15; i++) {
							String jxm = DealString.toGBK(DealString
									.toString(request.getParameter(("jxm" + i))));
							String jxnr = DealString.toGBK(DealString
									.toString(request.getParameter(("jxnr" + i))));
							if (jxm != "" || !jxm.equalsIgnoreCase("")) {
								if (doType.equalsIgnoreCase("modi")) {
									sql = "insert into csmz_tzxmjxb(xmid,jxid,jxm,xf)values(?,?,?,?)";
									dao.runUpdate(sql, new String[] { pkValue,
											String.valueOf(i), jxm, jxnr });
								} else {
									sql = "insert into csmz_tzxmjxb(xmid,jxid,jxm,xf)values((select max(to_number(id)) from csmz_tzxmb),?,?,?)";
									dao.runUpdate(sql, new String[] {
											String.valueOf(i), jxm, jxnr});
								}
								map.put("jxm"+i, jxm);
								map.put("jxnr"+i, jxnr);
							}
							
						}
					}
				}
				request.setAttribute("done", "ok");
				dao.writeLog(sql, new String[] { xn, xq, xmmc, kmdm, xmjb,
						xydm, zbsj, zzdw, xmms, pkValue }, oldV, yhcz, request);
			}
		}   
		if(!Base.isNull(doType)){
			if (doType.equalsIgnoreCase("modi") || doType.equalsIgnoreCase("view")) {// 修改、查看时获得项目相关数据
				sql = "select xn,xq,xmmc,kmdm,xydm,zzdw,zbsj,xmjb,xmms,(select distinct xqmc from xqdzb b where a.xq=b.xqdm)xqmc,hddx,xmsbr," 
					+"(select distinct xm from yhb b where a.xmsbr=b.yhm)xmsbrmc,"
					+" (select distinct bmmc from yhb b,zxbz_xxbmdm c where a.xmsbr=b.yhm and b.szbm=c.bmdm)szbmmc," 
					+"(select xqmc from xqdzb where xqdm=a.xq)xqmc,hdjhrs,hdxs from csmz_tzxmb a where a.id=?";
				map = dao.getMap(sql, new String[] { pkValue }, new String[] {
						"xn", "xq", "xmmc", "kmdm", "xydm", "zzdw", "zbsj", "xmjb",
						"xmms", "xqmc", "hddx","xmsbr","xmsbrmc","szbmmc","hdjhrs","hdxs"});
				sql = "select jxm,xf from csmz_tzxmjxb where xmid=? order by jxid";
				String jxTem = dao.getStringToSplit(sql, new String[] { pkValue },
						new String[] { "jxm", "xf" });
				if (jxTem != "") {
					String[] record = null;
					String[] tmp = jxTem.split("!!SplitSignOne!!");
					if (tmp.length >= 2) {
						for (int i = 1; i < tmp.length; i++) {
							record = tmp[i].split("!!SplitSignTwo!!");
							map.put("jxm" + i, (record[1] == null || ""
									.equalsIgnoreCase(record[1])) ? "" : record[1]);
							map.put("jxnr" + i, (record[2] == null || ""
									.equalsIgnoreCase(record[2])) ? "" : record[2]);
						}
					}
				}
			}else if(!doType.equalsIgnoreCase("del")){

				// 获得增加后项目相关数据
				sql = "select * from csmz_tzxmb where xn=? and xq=? and xmmc=? and xmjb=?";				
				map = dao.getMap(sql, new String[] { xn, xq, xmmc, xmjb },
						new String[] { "xn", "xq", "xmmc", "kmdm", "xydm",
						"zzdw", "zbsj", "xmjb", "xmms", "hddx","xmsbr","hdjhrs","hdxs" });
				sql = "select jxm,xf from csmz_tzxmjxb csmz_tzxmb b where xmid=(select id csmz_tzxmjxb where xn=? and xq=? and xmmc=? and xmjb=? and rownum=1) order by jxid";
				String jxTem = dao.getStringToSplit(sql, new String[] { xn, xq, xmmc, xmjb },
						new String[] { "jxm", "xf" });
				if (jxTem != "") {
					String[] record = null;
					String[] tmp = jxTem.split("!!SplitSignOne!!");
					if (tmp.length >= 2) {
						for (int i = 1; i < tmp.length; i++) {
							record = tmp[i].split("!!SplitSignTwo!!");
							map.put("jxm" + i, (record[1] == null || ""
									.equalsIgnoreCase(record[1])) ? "" : record[1]);
							map.put("jxnr" + i, (record[2] == null || ""
									.equalsIgnoreCase(record[2])) ? "" : record[2]);
						}
					}
				}
			}
		}
		if (doType.equalsIgnoreCase("del")) {// 删除记录
			sql = "select * from csmz_tzxmb where  id=? and (xysh='通过' or xxsh='通过' )";
			String isExist = dao.returntag(sql, new String[] { pkValue });
			if (userType.equalsIgnoreCase("admin")||"empty".equalsIgnoreCase(isExist)) {// 已经通过的记录不能删除或
				yhcz = "删除记录";
				sql = "select * from csmz_tzxmb where id=?";
				oldV = dao.getOneRs(sql, new String[] { pkValue }, new String[] {
						"id", "xn", "xq", "xmmc", "kmdm", "xydm", "zzdw", "zbsj",
						"xmjb", "xmms","hdjhrs","hdxs" });
				sql = "delete from csmz_tzxmb where id=?";
				done = dao.runUpdate(sql, new String[] { pkValue });
				if (done) {
					done=dao.runUpdate("delete from csmz_tzxmjxb where xmid=?",
							new String[] { pkValue });
					dao.runUpdate("delete from csmz_tzcgb a where not exists (select * from csmz_tzxmb b where a.xmid=b.id)",
							new String[] {});
					request.setAttribute("done", "ok");
					dao.writeLog(sql, new String[] {}, oldV, yhcz, request);
				}
				request.setAttribute("result", done);
			}else{
				request.setAttribute("notDel","notDel");
			}
			return new ActionForward(
					"/csmz_sztz.do?method=data_search&go=go&xh=", false);
		}

		List xmjbList = null;// 项目级别
		List kmList = dao.getList("select kmdm,kmm from sztz_kmdmb",
				new String[] {}, new String[] { "kmdm", "kmm" });

		xmjbList = initTzXmJbList(xxdm,userType);
//		if(Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)){
//		xmjbList = sztzDao.getTzXmJbList(6);
//		}else if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){
//		if ("stu".equalsIgnoreCase(userType)) {// 学生用户申报
//		xmjbList = sztzDao.getTzXmJbList(1);
//		request.setAttribute("isSTU", "yes");
//		} else if ("xy".equalsIgnoreCase(userType)) {// 院系用户申报
//		xmjbList = sztzDao.getTzXmJbList(4);
//		} else if ("xx".equalsIgnoreCase(userType)) {// 学校用户申报
//		xmjbList = sztzDao.getTzXmJbList(3);
//		} else if ("admin".equalsIgnoreCase(userType)) {// 管理员
//		xmjbList = sztzDao.getTzXmJbList(4);
//		}
//		}else{
//		xmjbList = sztzDao.getTzXmJbList(4);
//		}
		if("xx".equalsIgnoreCase(userType)){
			sql = "select a.yhm,a.xm||'('||a.yhm||')' xm,b.zmc from yhb a,yhzb b,ZXBZ_XXBMDM c,bks_dwdmb d where a.zdm=b.zdm and a.szbm=c.bmdm and a.dwdm=d.dwdm "
				+ " order by  xm";
		}else{
			sql = "select a.yhm,a.xm||'('||a.yhm||')' xm,b.zmc from yhb a,yhzb b,ZXBZ_XXBMDM c,bks_dwdmb d where a.zdm=b.zdm and a.szbm=c.bmdm and a.dwdm=d.dwdm and a.szbm='"+userDep+"' "
			+ " order by  xm";
		}
		if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){
			if ("stu".equalsIgnoreCase(userType)) {// 学生用户申报
				request.setAttribute("isSTU", "yes");	
			}
		}
		List yhList = dao.getList(sql, new String[] {}, new String[] {"yhm","xm"});
		request.setAttribute("yhList",yhList);
		SztzDao myDao= new SztzDao(); 
		request.setAttribute("bjList",myDao.getTzxmSbList(xh,xydm));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());		
		request.setAttribute("bmList",  dao.getList("select distinct bmdm,bmmc from ZXBZ_XXBMDM order by bmmc", new String[]{},new String[]{"bmdm", "bmmc"}));
		request.setAttribute("kmList", kmList);
		request.setAttribute("xmjbList", xmjbList);
		request.setAttribute("userType", userType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);
		request.setAttribute("rs", map);
		request.setAttribute("tips",tips);
		request.setAttribute("xxdm",xxdm);		
		return mapping.findForward("tzxm_sb");
	}

	/**
	 * @describe 查询共用模块
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward data_search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		SztzDao sztzDao = new SztzDao();
		SztzForm dsForm = (SztzForm) form;
		HttpSession session = request.getSession();
		SztzDao myDao = new SztzDao();
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		String act = request.getParameter("act");
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userOnline = session.getAttribute("userOnLine").toString();
		String tableName = request.getParameter("tableName");
		String realTable = request.getParameter("realTable");
		String xmid=request.getParameter("xmid");
		String xxdm =dao.getXxdm();
		String pk = "";
		String nj = dsForm.getNj();
		String xn = dsForm.getXn();
		String xq = dsForm.getXq();
		String xydm = dsForm.getXydm();
		String zydm = dsForm.getZydm();
		String bjdm = dsForm.getBjdm();
		String yesNo = DealString.toGBK(dsForm.getYesNo());
		dsForm.setYesNo(yesNo);
		String xh = DealString.toGBK(dsForm.getXh());
		dsForm.setXh(xh);
		String xmmc = DealString.toGBK(dsForm.getXmmc()).trim();
		dsForm.setXmmc(xmmc);
		String xm = DealString.toGBK(dsForm.getXm());
		dsForm.setXm(xm);
		String kmdm = dsForm.getKmdm();
		String xmjb = DealString.toGBK(dsForm.getXmjb());
		String curDep = dao.getOneRs("select xgbdm from xtszb where rownum=1 ",new String[]{},"xgbdm");
		curDep = Base.isNull(curDep)?"":curDep;
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		List xmjbList = null;
		String rsNum = "";
		String sql = "";
		String tips = "";
		String userGB = "";
		String newActionF="";
		boolean isSH = false;// 审核页面控制标志
		String xmsb = "no";// 项目申报、审核查询页面显示控制
		String ptcx = "no";// 普通查询页面显示控制标志（只包括年级，学院，专业，班级，学号，姓名）			
		List xyList = Base.getXyList();	
		if(!Base.isNull(xmid)){
			querry.append(" and xmid = '"+xmid+"' ");
		}
		
		
		//硅湖学院 学分申报审核
		
		
		request.setAttribute("path", "csmz_sztz.do?method=data_search&act=xmsbcx"); 	
		FormModleCommon.commonRequestSet(request);
		
		if(act.equalsIgnoreCase("cgsbcheck")
				&& Globals.XXDM_GHZYJSXY.equalsIgnoreCase(xxdm)){
			return new ActionForward("/sztzGhxy.do?method=sztzSh",false);
		}
		
		if (!userDep.equalsIgnoreCase(curDep)) {//不属于学工部用户
			if(act.equalsIgnoreCase("xmsbcx")
					||act.equalsIgnoreCase("xmsbcheck")
					||act.equalsIgnoreCase("cgsbcheck")
					||act.equalsIgnoreCase("cgsb")){
				userType = "stu".equalsIgnoreCase(userType)?userType:"xy";//部门、院系用户均设置为"xy"用户
			}			
		}else{
			userType = "xx";
		}
		//if(!act.equalsIgnoreCase("cgsbcheck")){//成果申报审核时，不限制院系用户
		if(userType.equalsIgnoreCase("xy")){//院系用户
			xydm = userDep;
			dsForm.setXydm(userDep);	
		}
		//}
		if ("stu".equalsIgnoreCase(userType)) {// 有操作权限的学生干部登录
			userGB = myDao.getBgbPower(userName);
			if (userGB.equalsIgnoreCase("0")) {
				request.setAttribute("errMsg", "您无权访问该页面！");
				return new ActionForward("/errMsg.do", false);
			} else {
				String[] temV = dao.getOneRs("select xydm,nj,zydm,bjdm from view_xsjbxx where xh=?",new String[] { userName }, new String[] { "xydm", "nj",
						"zydm", "bjdm" });
				if (temV != null) {
					xydm = (temV[0] == null || temV[0].equalsIgnoreCase("") ? "": temV[0]);
					dsForm.setXydm(xydm);
					if (!("xmsbcheck".equalsIgnoreCase(act)
							||"xmsbcx".equalsIgnoreCase(act))) {// 班干部查询及审核项目申报时去掉bjdm,zydm,nj字段
						nj = (temV[1] == null   || "".equalsIgnoreCase(temV[1]) ? "": temV[1]);
						zydm = (temV[2] == null || "".equalsIgnoreCase(temV[2]) ? "": temV[2]);
						bjdm = (temV[3] == null || "".equalsIgnoreCase(temV[3]) ? "": temV[3]);
						dsForm.setNj(nj);
						dsForm.setZydm(zydm);
						dsForm.setBjdm(bjdm);
					}
					xmjb = "班级";
				}
				xmjbList = sztzDao.getTzXmJbList(1);
			}
		}else{
			xmjbList = initTzXmJbList(xxdm,"");
//			if(Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)){
//			xmjbList = sztzDao.getTzXmJbList(6);
//			}else if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){			
//			xmjbList = sztzDao.getTzXmJbList(5);			
//			}else{
//			xmjbList = sztzDao.getTzXmJbList(4);
//			}
		}
		if (act.equalsIgnoreCase("xmsbcx")) {// 项目申报查询（院系只能查看班级级别项目、学校只能查看院系级别以上的项目）			
			if(!Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){//不是长沙民政的学校
				if ("stu".equalsIgnoreCase(userType)) {
					request.setAttribute("errMsg", "学生用户无权访问该页面！");
					return new ActionForward("/errMsg.do", false);
				}
			}
			realTable = "csmz_tzxmb";
			tableName = "view_csmz_tzxmxx";
			pk = "id";
			tips = "素质拓展 - 拓展项目申报 - 查询";
			if(Globals.XXDM_LZZYJSXY.equalsIgnoreCase(xxdm)){
				tips = "第二课堂活动 - 活动项目申报 - 查询";
			}
			if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){
				tips = "素质拓展 - 素质拓展计划项目 - 查询";
				colList = new String[] { "行号", "主键", "id", "xn", "xqmc",
						"xmmc", "kmmc", "xmjb", "fdysh","xysh", "xxsh" };			
			}else{
				colList = new String[] { "行号", "主键", "id", "xn", "xqmc",
						"xmmc", "kmmc", "xmjb", "xysh", "xxsh" };
			}
			if(!("admin".equalsIgnoreCase(userType)||"xx".equalsIgnoreCase(userType))){
				querry.append(" and xmsbr='"+userName+"'");	
			}
			querry.append((Base.isNull(xmjb)) ? " and 1=1 ": " and xmjb = '" + xmjb + "'");
			dsForm.setXmjb(xmjb);
			xmsb = "yes";
			newActionF = "tzxmcx_Manage";
			querry.append((Base.isNull(xydm)) ? " and 1=1 " : " and xydm = '"+ xydm + "'");
		} else if (act.equalsIgnoreCase("xmsbcheck")) {// 项目申报审核(院系只能审核班级项目、学校只能审核院系以上的项目)
			if(!Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){//不是长沙民政的学校
				if ("stu".equalsIgnoreCase(userType)) {
					request.setAttribute("errMsg", "学生用户无权访问该页面！");
					return new ActionForward("/errMsg.do", false);
				}
			}
			realTable = "csmz_tzxmb";
			tableName = "view_csmz_tzxmxx";
			pk = "id";
			tips = "素质拓展 - 拓展项目审核 - 审核";
			if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){
				tips = "素质拓展 - 素质拓展计划项目 - 审核";
			}
			if(Globals.XXDM_LZZYJSXY.equalsIgnoreCase(xxdm)){
				tips = "第二课堂活动 - 活动项目申报 - 审核";
			}
			if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){
				colList = new String[] { "bgcolor", "主键", "id", "xn", "xqmc",
						"xmmc", "kmmc","xymc", "xmjb","xmsbr", "fdysh","xysh","xxsh" };
				querry.append((Base.isNull(xmjb)) ? " and 1=1 "
						: " and xmjb = '" + xmjb + "'");
				newActionF = "csmztzxmsh_Manage";
			}else{
				colList = new String[] { "bgcolor", "主键", "id", "xn", "xqmc",
						"xmmc", "kmmc","xymc", "xmjb","xmsbr","xysh", "xxsh"};
				querry.append((Base.isNull(xmjb)) ? " and 1=1 "
						: " and xmjb = '" + xmjb + "'");				
				newActionF = "tzxmsh_Manage";	
			}
			if ("xy".equalsIgnoreCase(userType)) {// 院系用户申报
				querry.append(Base.isNull(yesNo)?"":" and xysh = '"+yesNo+"'");
			} else if ("xx".equalsIgnoreCase(userType)
					|| userType.equalsIgnoreCase("admin")) {// 学校用户申报
				querry.append(Base.isNull(yesNo)?"":" and xxsh = '"+yesNo+"'");
			}else{
				querry.append(Base.isNull(yesNo)?"":" and fdysh = '"+yesNo+"'");
				querry.append(" and bjdm='"+dao.getOneRs("select bjdm from view_xsjbxx where xh=?",new String[]{userName},"bjdm")+"'");
			}
			dsForm.setXmjb(xmjb);
			xmsb = "yes";
			isSH = true;
			querry.append((Base.isNull(xydm)) ? " and 1=1 " : " and xydm = '"+ xydm + "'");
		} else if (act.equalsIgnoreCase("cgsb")) {// 成果申报结果查询
			realTable = "csmz_tzcgb";
			tableName = "view_csmz_cgsbxx";
			pk = " pk ";
			tips = "素质拓展 - 拓展学分个人申报 - 申报结果查询";
			if(Globals.XXDM_LZZYJSXY.equalsIgnoreCase(xxdm)){
				tips = "第二课堂活动 - 学生活动分申报 - 申报结果查询";
			}
			if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){
				tips = "素质拓展 - 素质拓展成果申报 - 申报结果查询";
			}else if(Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)){
				tips = "素质拓展 - 拓展得分个人申报 - 申报结果查询";
			}
			if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){
				colList = new String[] { "行号", "主键", "xh", "xm", "xn", "xqmc",
						"xmmc", "kmmc", "xmjb", "cyjs", "fdysh", "xysh", "xxsh" };
			}else if(Globals.XXDM_GHZYJSXY.equalsIgnoreCase(xxdm)
					&& act.equalsIgnoreCase("cgsb")){
				colList = new String[] { "行号", "主键", "xh", "xm", "xn", "xqmc",
						"xmmc", "kmmc", "xmjb", "cyjs","fdysh","bmsh", "xxsh" };
			}else{
				colList = new String[] { "行号", "主键", "xh", "xm", "xn", "xqmc",
						"xmmc", "kmmc", "xmjb", "cyjs","fdysh","xysh", "xxsh" };
			}
			if ("xy".equalsIgnoreCase(userType)) {// 院系用户申报
				querry.append(Base.isNull(yesNo)?"":" and xysh = '"+yesNo+"'");
			} else if ("xx".equalsIgnoreCase(userType)
					|| userType.equalsIgnoreCase("admin")) {// 学校用户申报
				querry.append(Base.isNull(yesNo)?"":" and xxsh = '"+yesNo+"'");
			}else{
				querry.append(Base.isNull(yesNo)?"":" and fdysh = '"+yesNo+"'");
			}
			ptcx = "yes";
			request.setAttribute("writeAble", (Base.chkUPower(userName,"csmz_sztz.do?method=cgsb_result", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
			newActionF = "cgsb_Manage";
			querry.append((Base.isNull(xydm)) ? " and 1=1 " : " and (xydm = '"+ xydm + "' or sqbm='"+xydm+"')");
			xyList = dao.getList("select bmdm xydm,bmmc xymc from zxbz_xxbmdm　 order by bmdm", new String[]{}, new String[]{"xydm","xymc"} );				
		} else if (act.equalsIgnoreCase("cgsbcheck")) {// 成果申报结果审核
			realTable = "csmz_tzcgb";
			tableName = "view_csmz_cgsbxx";
			pk = "pk";
			tips = "素质拓展 - 拓展学分个人申报 - 学分申报审核";
			colList = new String[] { "bgcolor", "主键","xn", "xqmc","xymc", "xh", "xm", 
					"xmmc", "kmmc", "xmjb", "cyjs","fdysh","xysh","xxsh"};
			isSH = true;
			if(Globals.XXDM_LZZYJSXY.equalsIgnoreCase(xxdm)){
				tips = "第二课堂活动 - 学生活动分申报 - 活动分申报审核";
			}
			if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){
				tips = "素质拓展 - 素质拓展成果申报审核 - 申报审核";
				newActionF = "csmzcgsbsh_Manage";				
			}else if(Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)){
				colList = new String[] { "bgcolor", "主键", "xh", "xm", "xn", "xqmc",
						"xmmc", "kmmc", "xmjb", "cyjs","fdysh","xysh","xxsh"};
				newActionF = "cgsbsh_Manage";
			}else{
				if(Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)){
					tips = "素质拓展 - 拓展得分个人申报 - 得分申报审核";
				}
				colList = new String[] { "bgcolor", "主键", "xn", "xqmc", "xymc","xh", "xm",
						"xmmc", "kmmc", "xmjb","cyjs","xysh","xxsh"};				
				newActionF = "cgsbsh_Manage";
			}
			if(Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)){
				String fdyshYesNo = dsForm.getFdyshYesNo();
				String xyshYesNo = dsForm.getXyshYesNo();
				String xxshYesNo = dsForm.getXxshYesNo();
				querry.append(Base.isNull(xyshYesNo)?"":" and xysh = '"+xyshYesNo+"'");
				querry.append(Base.isNull(xxshYesNo)?"":" and xxsh = '"+xxshYesNo+"'");
				querry.append(Base.isNull(fdyshYesNo)?"":" and fdysh = '"+fdyshYesNo+"'");
			}else{
				if ("xy".equalsIgnoreCase(userType)) {// 院系用户
					querry.append(Base.isNull(yesNo)?"":" and xysh = '"+yesNo+"'");
				} else if ("xx".equalsIgnoreCase(userType)
						|| userType.equalsIgnoreCase("admin")) {// 学校用户
					querry.append(Base.isNull(yesNo)?"":" and xxsh = '"+yesNo+"'");
				}else{
					querry.append(Base.isNull(yesNo)?"":" and fdysh = '"+yesNo+"'");
				}
			}

			if(!userDep.equalsIgnoreCase(curDep)){//当学院用户时，由项目负责人审核该负责项目内全校学生申请
//				if(userDep.equalsIgnoreCase(arg0)){
				if ("xx".equalsIgnoreCase(userType)) {// 学校用户				
				}else if ("xy".equalsIgnoreCase(userType)){// 院系用户
					querry.append(" and (sqbm='"+userDep+"' or xydm='"+xydm+"' )");	
				}else{
					querry.append(" and (sqbm='"+userDep+"' or bjdm='"+bjdm+"' )");				
				}			
//				}
			}
			xyList = dao.getList("select bmdm xydm,bmmc xymc from zxbz_xxbmdm　 order by bmdm", new String[]{}, new String[]{"xydm","xymc"} );						
		} else if (act.equalsIgnoreCase("tzcgcj")) {
			realTable = "csmz_tzcjb";
			tableName = "view_tzcgcjxx";
			pk = "pk";
			tips = "素质拓展 - 信息维护 - 素质拓展成绩";
			colList = new String[] { "行号", "主键",  "xn", "xqmc","xymc","xh", "xm",
					"xmmc", "kmmc", "xmjb", "jxm", "xf","bxqzxf" };
			// ---------------2010/7/12 edit by luojw----------
			if (Globals.XXDM_LZZYJSXY.equalsIgnoreCase(xxdm)) {
				tips = "第二课堂活动 - 信息维护 - 学生活动成绩";
				colList = new String[] { "行号", "主键", "xh", "xm", "xn", "xqmc",
						"xmmc", "kmmc", "xmjb", "jxm", "xf","sfdy","bxqzxf","bxnzxf" };
			}
			// ---------------end----------
			if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){
				colList = new String[] { "行号", "主键", "xh", "xm", "xn", "xqmc",
						"xmmc", "kmmc", "xmjb", "jxm", "xf","sfdy","bxqzxf" };
			}
		
			newActionF = "tzcgcj_Manage";
			querry.append((Base.isNull(xydm)) ? " and 1=1 " : " and xydm = '"+ xydm + "'");
		}
		if(isSH){//审核查询时初始学年、学期
			if(xn==null){
				xn=Base.currXn;
				dsForm.setXn(xn);
			}
			if(xq==null){
				xq=Base.currXq;
				dsForm.setXq(xq);
			}	
		}
		//---------------2010/6/3 edit by luojw----------------------------
		querry.append((Base.isNull(xydm)) ? " and 1=1 " : " and xydm = '" + xydm+ "'");
		querry.append((Base.isNull(nj)) ? " and 1=1 " : " and nj = '" + nj+ "'");
		querry.append((Base.isNull(xn)) ? " and 1=1 " : " and xn = '" + xn+ "'");
		querry.append((Base.isNull(xq)) ? " and 1=1 " : " and xq = '" + xq+ "'");
		querry.append((Base.isNull(xh)) ? " and 1=1 " : " and xh like '%" + xh+ "%'");
		querry.append((Base.isNull(xm)) ? " and 1=1 " : " and xm like '%" + xm+ "%'");
		querry.append((Base.isNull(zydm)) ? " and 1=1 " : " and zydm = '"+ zydm + "'");
		querry.append((Base.isNull(bjdm)) ? " and 1=1 " : " and bjdm = '"+ bjdm + "'");
		querry.append((Base.isNull(kmdm)) ? " and 1=1 " : " and kmdm = '"+ kmdm + "'");
		querry.append((Base.isNull(xmmc))?" and 1=1 " : " and xmmc like '%"+xmmc+"%'");		
		//	---------------end----------------------------
		
		// 审核查询
		if (isSH) {
			if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){
				/**
				 * 拓展成果(学分)审核(班级级别项目成果只由学生干部、院系两级审核；系以上级别项目成果由学生干部、院系、学校三级审核)
				 */
				if (realTable.equalsIgnoreCase("csmz_tzcgb")) {
					if (userType.equalsIgnoreCase("xx")
							|| userType.equalsIgnoreCase("admin")) {
						sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' when xmjb='班级' then '#B5BED6' else '#CCCCCC' end) bgcolor,"
							+ " a.* from(select "
							+ pk
							+ " 主键,rownum r,a.xh,a.xm,a.xn,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc,a.xq,a.xmmc,a.kmmc,a.xymc, a.xmjb,a.cyjs,fdysh,xysh,xxsh from "
							+ tableName
							+ " a"
							+ querry.toString()
							+ "and xysh ='通过' order by xxsh desc)a ";
						//colList[colList.length - 1] = "xxsh";
					} else if (userType.equalsIgnoreCase("xy")) {
						//colList[colList.length - 1] = "xysh";
						//colList[colList.length - 2] = "fdysh";
						sql = "select (case when nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
							+ " a.* from(select "
							+ pk
							+ " 主键,rownum r,a.xh,a.xm,a.xn,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc,a.xq,a.xmmc,a.kmmc,a.xymc, a.xmjb,a.cyjs,fdysh,xysh,xxsh from "
							+ tableName
							+ " a"
							+ querry.toString()
							+ "and fdysh ='通过' order by xysh desc)a ";
					} else {
						sql = "select (case when nvl(a.fdysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
							+ " a.* from(select "
							+ pk
							+ " 主键,rownum r,a.xh,a.xm,a.xn,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc,a.xq,a.xmmc,a.kmmc,a.xymc, a.xmjb,a.cyjs,fdysh,xysh, xxsh from "
							+ tableName
							+ " a"
							+ querry.toString() + " order by fdysh desc)a ";
						//colList[colList.length - 1] = "fdysh";
					}
				} else {
					/** 拓展项目审核 */
					if (userDep.equalsIgnoreCase(curDep)) {//学工部审核
						sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
							+ " a.* from(select "
							+ pk
							+ " 主键,rownum r,a.id,a.xn,a.xqmc,a.xmmc,a.kmmc,a.xymc,a.xmjb,xmsbr,(case when a.xmjb<>'班级' then '无需审核' else a.fdysh end)fdysh," 
							+"xysh,xxsh from "
							+ tableName
							+ " a"
							+ querry.toString()
							+ "order by xxsh desc ,xmjb asc ,xn asc,xq asc )a "
							+ " where r<="
							+ (dsForm.getPages().getStart() + dsForm.getPages()
									.getPageSize()) + " and r>"
							+ dsForm.getPages().getStart();
					} else if(userType.equalsIgnoreCase("stu")){//学生班干部审核
						sql = "select (case when nvl(a.fdysh,'未审核')='通过' then '#FFFFFF'  else '#CCCCCC' end) bgcolor,"
							+ " a.* from(select "
							+ pk
							+ " 主键,rownum r,a.* from "
							+ tableName
							+ " a"
							+ querry.toString() + "order by fdysh desc ,xmjb asc ,xn asc,xq asc)a "
							+ " where r<="
							+ (dsForm.getPages().getStart() + dsForm.getPages()
									.getPageSize()) + " and r>"
							+ dsForm.getPages().getStart();
					}else{//其他用户审核
						sql = "select (case when nvl(a.xysh,'未审核')='通过' then '#FFFFFF'  else '#CCCCCC' end) bgcolor,"
							+ " a.* from(select "
							+ pk
							+ " 主键,rownum r,a.id,a.xn,a.xqmc,a.xmmc,a.kmmc,a.xymc,a.xmjb,xmsbr,(case when a.xmjb<>'班级' then '无需审核' else a.fdysh end)fdysh,xysh,xxsh from "
							+ tableName
							+ " a"
							+ querry.toString() + "order by xysh desc ,xmjb asc ,xn asc,xq asc)a "
							+ " where r<="
							+ (dsForm.getPages().getStart() + dsForm.getPages()
									.getPageSize()) + " and r>"
							+ dsForm.getPages().getStart();
					}
					
					//					分页
					dsForm.getPages().setMaxRecord(
							Integer.parseInt(dao.getOneRs("select count(*) count from "
									+ tableName + " a" + querry.toString(),
									new String[] {}, "count")));

				}
			}else{
				String shType = "xysh";
				if (userType.equalsIgnoreCase("xx")
						|| userType.equalsIgnoreCase("admin")) {
					shType = "xxsh";
					//querry.append(" and xysh='通过' ");
				}else{
					shType = "xysh";
				}
				if (realTable.equalsIgnoreCase("csmz_tzcgb")) {
					if(Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)){//宁波理工职业技术学院
						if("isStu".equalsIgnoreCase(userType)){
							shType = "fdysh";
						}
					}
					sql = "select (case when nvl(a."+shType+",'未审核')='通过' then '#FFFFFF'  else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,rownum r,a.xh,a.xm,a.xn,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc,a.xq,a.xymc,a.xmmc,a.kmmc,a.xmjb,a.cyjs, fdysh,xysh,xxsh from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ "  order by xysh,xxsh desc)a "
					+ " where r<="
					+ (dsForm.getPages().getStart() + dsForm.getPages()
								.getPageSize()) + " and r>"
						+ dsForm.getPages().getStart();
				}else{
					sql = "select (case when nvl(a."+shType+",'未审核')='通过' then '#FFFFFF'  else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,rownum r,a.id,a.xn,a.xqmc,a.xymc,a.xmmc,a.kmmc,a.xmjb," 
					+"xysh,xxsh,(select distinct xm from yhb b where b.yhm=a.xmsbr)xmsbr from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ "  order by xxsh desc ,xmjb asc ,xn asc,xq asc )a "
					+ " where r<="
					+ (dsForm.getPages().getStart() + dsForm.getPages()
								.getPageSize()) + " and r>"
						+ dsForm.getPages().getStart();
				}
				//				分页
				System.out.println("select count(*) count from "
						+ tableName + " a" + querry.toString());
				dsForm.getPages().setMaxRecord(
						Integer.parseInt(dao.getOneRs("select count(*) count from "
								+ tableName + " a" + querry.toString(),
								new String[] {}, "count")));
			}
		} else {// 非审核查询
			sql = "select * from (select * from( select rownum 行号,"
				+ pk
				+ " 主键,rownum r,  " 
				+"a.* from "
				+ tableName
				+ " a"
				+ querry.toString()
				+ ") where rownum<="
				+ (dsForm.getPages().getStart() + dsForm.getPages().getPageSize()) + ") where r>"
				+ dsForm.getPages().getStart();
			if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){
				if("view_csmz_tzxmxx".equalsIgnoreCase(tableName)){
					sql = "select * from (select * from( select rownum 行号,"
						+ pk
						+ " 主键,rownum r, id,xn, xqmc,xmmc, kmmc, xmjb, (case when xmjb<>'班级' then '无需审核' else fdysh end)fdysh,xysh, xxsh from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ ") where rownum<="
						+ (dsForm.getPages().getStart() + dsForm.getPages().getPageSize()) + ") where r>"
						+ dsForm.getPages().getStart();
				}
			}
			// ---------------2010/7/12 edit by luojw----------
			if (Globals.XXDM_LZZYJSXY.equalsIgnoreCase(xxdm)
					&& (act.equalsIgnoreCase("tzcgcj"))) {
				sql = "select * from (select * from( select rownum 行号,"
						+ pk
						+ " 主键,rownum r,  "
						+ "a.pk,a.xh,a.xm,a.xn,a.xqmc,a.xmmc,a.kmmc,"
						+ "a.xmjb,a.jxm,a.xf,a.sfdy,a.bxqzxf,"
						+ "b.bxnzxf from (select "
						+ "a.pk,a.xh,a.xm,a.xn,a.xqmc,a.xmmc,a.kmmc,"
						+ "a.xmjb,a.jxm,a.xf,a.sfdy,a.bxqzxf from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ ") a left join (select b.xh, b.xn, b.bxnzxf from "
						+ "  (select b.xh, b.xn, sum(b.xf) bxnzxf "
						+ " from view_tzcgcjxx b group by b.xh, b.xn) b)  "
						+ "  b on a.xh = b.xh and a.xn = b.xn "
						+ " ) where rownum<="
						+ (dsForm.getPages().getStart() + dsForm.getPages()
								.getPageSize()) + ") where r>"
						+ dsForm.getPages().getStart();
			}
			// ---------------end-----------------
//			分页
			dsForm.getPages().setMaxRecord(
					Integer.parseInt(dao.getOneRs("select count(*) count from "
							+ tableName + " a" + querry.toString(),
							new String[] {}, "count")));
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		String xxmc = dao.getOneRs("select xxmc from xtszb", new String[] {},new String[] { "xxmc" })[0];
		request.setAttribute("xxmc", xxmc);// 取学校名称信息
		request.setAttribute("xxdm", xxdm);

		List kmList = dao.getList("select kmdm,kmm from sztz_kmdmb",new String[] {}, new String[] { "kmdm", "kmm" });
		request.setAttribute("xmjbList", xmjbList);
		request.setAttribute("kmList", kmList);
		request.setAttribute("bmList",  dao.getList("select distinct bmdm,bmmc from ZXBZ_XXBMDM order by bmmc", new String[]{},new String[]{"bmdm", "bmmc"}));
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("act", act);// 发送数据查询类型
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		request.setAttribute("isSH", isSH);
		request.setAttribute("xmsb", xmsb);
		request.setAttribute("chkList",dao.getChkList(3));
		request.setAttribute("ptcx", ptcx);

		xydm = xydm == null ? "" : xydm;
		zydm = zydm == null ? "" : zydm;
		nj = nj == null ? "" : nj;
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xqList", Base.getXqList());// 发送学期列表
		request.setAttribute("xyList", xyList);// 发送学院列表
		request.setAttribute("zyList", dao.getZyList(xydm));// 发送专业列表
		request.setAttribute("bjList", dao.getBjList(xydm, zydm));// 发送班级列表
		
		
		
		if(!Base.isNull(newActionF)){
			 
			String jxsb = XMLReader.getFlowControl("sztz", "jxsb");
			
			if("lssb".equalsIgnoreCase(jxsb)
					&& "cgsbsh_Manage".equalsIgnoreCase(newActionF)){
				SztzWhtlService whtlService =new SztzWhtlService();
				
				request.setAttribute("xmList", whtlService.getXmList(xn, xq));
				request.setAttribute("jxList", whtlService.getJxList(xmid));
				return  mapping.findForward("whtl_cgsbsh_Manage");
			}
			
			return mapping.findForward(newActionF);
		}else{
			return mapping.findForward("data_search");
		}
	}
	/**
	 * @describe 拓展项目申报审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tzxm_sh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DAO dao = DAO.getInstance();
		String pkValue = request.getParameter("pkValue");
		String sql = "";
		String yesNo = DealString.toGBK(request.getParameter("yesNo"));
		String doType = request.getParameter("doType");
		String userDep = request.getSession().getAttribute("userDep").toString();
		String curDep = dao.getOneRs("select xgbdm from xtszb where rownum=1 ",new String[]{},"xgbdm");
		curDep = Base.isNull(curDep)?"":curDep;
		String userType =  request.getSession().getAttribute("userType").toString();
		String[] oldV = null;// 旧值
		HashMap<String, String> map = new HashMap<String, String>();
		String shType = "";
		String shZd  = "";
		String shClin = "";
		StringBuffer querry = new StringBuffer();
		boolean shLimit =  false; 
		boolean done = false;
		if (!userDep.equalsIgnoreCase(curDep)) {//不属于学工部用户
			userType = "stu".equalsIgnoreCase(userType)?userType:"xy";//非学生班干部用户设置为"xy"用户				
		}else{
			userType = "xx";
		}
		if ("save".equalsIgnoreCase(doType)) {// 保存审核结果
			if ("xx".equalsIgnoreCase(userType)
					||"admin".equalsIgnoreCase(userType)) {
				sql = "update csmz_tzxmb set xxsh=? where id=?";
				oldV = dao.getOneRs("select * from csmz_tzxmb where id=?",
						new String[] { pkValue }, new String[] { "xn", "xq",
						"xmmc", "xysh" });

			} else if ("xy".equalsIgnoreCase(userType)) {
				sql = "update csmz_tzxmb set xysh=? where id=?";
				oldV = dao.getOneRs("select * from csmz_tzxmb where id=?",
						new String[] { pkValue }, new String[] { "xn", "xq",
						"xmmc", "xxsh" });
			}else{
				sql = "update csmz_tzxmb set fdysh=? where id=?";
				oldV = dao.getOneRs("select * from csmz_tzxmb where id=?",
						new String[] { pkValue }, new String[] { "xn", "xq",
						"xmmc", "fdysh" });
			}
			done = dao.runUpdate(sql, new String[] { yesNo, pkValue });
			if (done) {
				request.setAttribute("done", "yes");
				dao.writeLog(sql, new String[] { yesNo, pkValue }, oldV,
						"拓展项目审核", request);
			} else {
				request.setAttribute("done", "no");
			}
		}
		// 生成页面
//		String xmjbV = dao.getOneRs("select xmjb from csmz_tzxmb where id=?",new String[] { pkValue }, "xmjb");
		if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {		
			String shnr = dao.getOneRs("select count(*)cout from csmz_tzxmb where xysh<>'通过' and id=? ", new String[] { pkValue }, "cout");
			if (!"0".equalsIgnoreCase(shnr)) {
				shLimit = true;// 针对下级未审核通过时,学校用户只能查看
			}
//			if("班级".equalsIgnoreCase(xmjbV)){
//			shClin = "无需审核";
//			}else 
//			if("院系".equalsIgnoreCase(xmjbV)){
			shClin = "待院系(部门)审核通过";
//			}else{
//			shClin = "待院系(部门)审核通过";
//			}
			shType = "学校";
			shZd = "xxsh";
		}else if ("xy".equalsIgnoreCase(userType)) {
			shType = "院系(部门)";
			shZd = "xysh";
			String shnr = dao.getOneRs("select count(*)cout from csmz_tzxmb where (fdysh<>'通过'and xmjb='班级' ) and id=? ", new String[] { pkValue }, "cout");
			if (!"0".equalsIgnoreCase(shnr)) {
				shLimit = true;// 针对下级未审核通过时,院系(部门)用户只能查看
			}		
//			if("班级".equalsIgnoreCase(xmjbV)){
			shClin = "待班级审核通过";
//			}
		}else{
			shType = "班级";
			shZd = "fdysh";
			querry.append(" and xmjb='班级' ");
		}
		sql = "select xn,xmmc,zzdw,zbsj,xmjb,xmms,(select kmm from sztz_kmdmb where kmdm=a.kmdm)kmm,(select xqmc from xqdzb where xqdm=a.xq)xqmc,"
			+ " (select xm  from view_xsjbxx b where b.xh=a.xmsbr )||(select xm from yhb b where b.yhm=a.xmsbr)xmsbr, (select bmmc from zxbz_xxbmdm b where b.bmdm=a.xydm )bmmc,"+shZd+" yesNo from csmz_tzxmb a where a.id=? "+querry;
		map = dao.getMap(sql, new String[]{ pkValue }, new String[] { "xn","xmmc", "kmm", "bmmc", "zzdw",
				"zbsj", "xmjb", "xmms","xqmc","yesNo","xmsbr" });
		Vector<Object> rsJx = new Vector<Object>();
		sql = "select jxm,xf from csmz_tzxmjxb where xmid =? order by jxid";
		rsJx.addAll(dao.rsToVator(sql, new String[] { pkValue }, new String[] {
				"jxm", "xf" }));
		String reNum = "";
		if (rsJx == null) {
			reNum = "0";
		} else {
			reNum = String.valueOf(rsJx.size());
		}
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("rs", map);
		request.setAttribute("reNum", reNum);
		request.setAttribute("rsJx", rsJx);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("shType", shType);
		request.setAttribute("userType", userType);
		request.setAttribute("shLimit", shLimit);
		request.setAttribute("shClin", shClin);

		return mapping.findForward("tzxm_sh");
	}	
	public ActionForward tzxmShView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DAO dao = DAO.getInstance();
		String pkValue = request.getParameter("pkValue");
		String sql = "";
		String xxdm = dao.getXxdm();
		String userType =  request.getSession().getAttribute("userType").toString();
		HashMap<String, String> map = new HashMap<String, String>();
		String view = request.getParameter("view");
		String select = request.getParameter("select");
		String shClin = "";
		StringBuffer querry = new StringBuffer();
		String fsclin = "学分";
		if(Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)){//北京联合大学
			fsclin = "得分";
		}
		String tips = "素质拓展 - 拓展项目审核 - 审核 - 单个审核";
		if(Globals.XXDM_LZZYJSXY.equalsIgnoreCase(xxdm)){
			tips = "第二课堂活动 - 活动项目审核 - 审核 - 单个审核";
			fsclin = "活动分";
		}
		request.setAttribute("fsclin", fsclin);
		// 生成页面		
		sql = "select xn,xmmc,zzdw,zbsj,xmjb,xmms,(select kmm from sztz_kmdmb where kmdm=a.kmdm)kmm,(select xqmc from xqdzb where xqdm=a.xq)xqmc,"
			+ "(select bmmc from zxbz_xxbmdm b where b.bmdm=a.xydm )bmmc,(select distinct xm from yhb b where a.xmsbr=b.yhm)xmsbr, " +
			" (select distinct bmmc from zxbz_xxbmdm b, yhb c where b.bmdm=c.szbm and a.xmsbr=c.yhm)szbm from csmz_tzxmb a where a.id=? "+querry;
		map = dao.getMap(sql, new String[]{ pkValue }, new String[] { "xn","xmmc", "kmm", "bmmc", "zzdw",
				"zbsj", "xmjb", "xmms","xqmc","xmsbr","szbm" });
		Vector<Object> rsJx = new Vector<Object>();
		sql = "select jxm,xf from csmz_tzxmjxb where xmid =? order by jxid";
		rsJx.addAll(dao.rsToVator(sql, new String[] { pkValue }, new String[] {
				"jxm", "xf" }));
		String reNum = "";
		if (rsJx == null) {
			reNum = "0";
		} else {
			reNum = String.valueOf(rsJx.size());
		}		
		request.setAttribute("rs", map);
		request.setAttribute("reNum", reNum);
		request.setAttribute("rsJx", rsJx);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("userType", userType);
		request.setAttribute("shClin", shClin);
		request.setAttribute("view",view);
		request.setAttribute("select",select);
		request.setAttribute("sel",request.getParameter("sel"));
		request.setAttribute("xxdm",xxdm);
		request.setAttribute("tips",tips);
		return mapping.findForward("tzxm_shView");
	}
	public ActionForward  plTzxmCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)  throws Exception{
		DAO dao = DAO.getInstance();
		String pkValue = request.getParameter("pkVStr");
		String check   = request.getParameter("check");
		String shV     = "";
		if("yes".equalsIgnoreCase(check)){
			shV = "通过";
		}else if("no".equalsIgnoreCase(check)){
			shV = "不通过";
		}else{
			shV = "未审核";
		}
		String[] pkValueA = pkValue.split("!!");
		String sql = "";
		StringBuffer sqlStr = new StringBuffer();
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep  = request.getSession().getAttribute("userDep").toString();
		String curDep = dao.getOneRs("select xgbdm from xtszb where rownum=1 ",new String[]{},"xgbdm");
		curDep = Base.isNull(curDep)?"":curDep;
		if (!userDep.equalsIgnoreCase(curDep)) {//不属于学工部用户
			userType = "stu".equalsIgnoreCase(userType)?userType:"xy";//非学生班干部用户设置为"xy"用户				
		}else{
			userType = "xx";
		}
		for(int i=0;i<pkValueA.length;i++){
			if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {
				sql = "update csmz_tzxmb set xxsh='"+shV+"' where id='"+pkValueA[i]+"' ";								
			} else if ("xy".equalsIgnoreCase(userType)) {
				sql = "update csmz_tzxmb set xysh='"+shV+"' where id='"+pkValueA[i]+"' ";				
			} else if("stu".equalsIgnoreCase(userType)){
				sql = "update csmz_tzxmb set fdysh='"+shV+"' where id='"+pkValueA[i]+"' ";
			}
			sqlStr.append(Base.isNull(pkValueA[i])?"":sql).append("!!");			
		}		
		boolean doFlag = false;
		int[] array = dao.runBatch(sqlStr.toString().split("!!"));
		doFlag = dao.checkBatch(array); 	    
		request.setAttribute("done",doFlag); 
		request.setAttribute("userType",userType);
		return new ActionForward("/csmz_sztz.do?method=data_search&act=xmsbcheck&go=go",false);
	}

	public ActionForward  csmz_PlTzxmCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)  throws Exception{
		DAO dao = DAO.getInstance();
		String pkValue = request.getParameter("pkVStr");
		String check   = request.getParameter("check");
		String shV     = "";
		if("yes".equalsIgnoreCase(check)){
			shV = "通过";
		}else if("no".equalsIgnoreCase(check)){
			shV = "不通过";
		}else{
			shV = "未审核";
		}
		String[] pkValueA = pkValue.split("!!");
		String sql = "";
		StringBuffer sqlStr = new StringBuffer();
		String userType = request.getSession().getAttribute("userType").toString();
		for(int i=0;i<pkValueA.length;i++){
			if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {
				sql = "update csmz_tzxmb set xxsh='"+shV+"' where id='"+pkValueA[i]+"' ";								
			} else if ("xy".equalsIgnoreCase(userType)) {
				sql = "update csmz_tzxmb set xysh='"+shV+"' where id='"+pkValueA[i]+"' ";				
			}
			sqlStr.append(Base.isNull(pkValueA[i])?"":sql).append("!!");			
		}		
		boolean doFlag = false;
		int[] array = dao.runBatch(sqlStr.toString().split("!!"));
		doFlag = dao.checkBatch(array); 	    
		request.setAttribute("done",doFlag); 
		return new ActionForward("/csmz_sztz.do?method=data_search&act=xmsbcheck&go=go",false);
	}

	/**
	 * @describe 个人拓展成果申报
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tzcg_sb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DAO dao = DAO.getInstance();
		String userType = request.getSession().getAttribute("userOnLine").toString();
		SztzForm sztzForm = (SztzForm) form;
		SztzDao myDao = new SztzDao();
		String sql = "";
		String act = request.getParameter("act");
		String doType = DealString.toString(request.getParameter("doType"));
		String pkValue = DealString.toString(RowidToPk.rowidToPK(request.getParameter("pkValue")));
		String pk = " xh||xmid||cyjs||jxlb ";
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = dao.getOneRs("select xh from view_csmz_cgsbxx where pk=?", new String[]{pkValue}, "xh");
		String xmdm = dao.getOneRs("select xmid from view_csmz_cgsbxx where pk=?", new String[]{pkValue}, "xmid");
		String cxdo = request.getParameter("cxdo");
		String hjjb = sztzForm.getJxlb();
		String[] oldV = null;
		String yhcz = "";
		String xxdm = dao.getXxdm();
		String tips = "素质拓展 - 拓展学分个人申报 - 填写申报表";
		
	    String jxsb = XMLReader.getFlowControl("sztz", "jxsb");
		if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){
			tips = "素质拓展 - 素质拓展成果申报 - 填写申报表";
			if(Base.isNull(pkValue)){//只有当申报是才显示是否打印标志
				request.setAttribute("showdybz","showdybz");//打印标志
			}
		}else if(Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)){
			tips = "素质拓展 - 拓展得分个人申报 - 填写申报表";
		}else if(Globals.XXDM_LZZYJSXY.equalsIgnoreCase(xxdm)){
			tips = "第二课堂活动 - 学生活动分申报 - 填写申报表";
		}
		if(!Base.isNull(cxdo)||Base.isNull(doType)){
			xh = sztzForm.getXh().trim();
			xmdm = sztzForm.getXmdm().trim();
		}
		// 获得参数设置表数据(获得设置申请时间范围)
		sql = "select * from csmz_sztzszb where to_char(sysdate,'yyyymmddhh24miss') between kssj and jssj";
		String tag = dao.returntag(sql, new String[] {});
		if ("empty".equalsIgnoreCase(tag)) {
			if (doType.equalsIgnoreCase("modi")
					|| doType.equalsIgnoreCase("add")) {// 修改、添加操作下不限制
				request.setAttribute("sqsjTag", "0");
			} else {
				request.setAttribute("sqsjTag", "1");
			}
		}       	       
		// 奖项List
		sql = " select distinct jxm,jxid from csmz_tzxmjxb where 1=2 ";
		List jxjbList = dao.getList(sql, new String[] {}, new String[] {
				"jxid", "jxm" });
		boolean done = false;
		if (userType.equalsIgnoreCase("student")) {
			xh = request.getSession().getAttribute("userName").toString();
		}
		// 学生信息
		sql = "select xh,xm,xb,nj,xymc,zymc,bjmc from view_xsjbxx where xh=?";
		map = dao.getMap(sql, new String[] { xh }, new String[] { "xh", "xm",
				"xb", "nj", "xymc", "zymc", "bjmc" });
		// 项目信息
		if (xmdm != null && !"".equalsIgnoreCase(xmdm)) {
			sql = "select id,xn,(select xqmc from xqdzb b where b.xqdm=xq and rownum=1 )xq,xmmc,kmmc,xymc bmmc,zzdw,zbsj,xmjb,xmms," +
					" (case when xmjb='班级' then (select xm from view_xsjbxx b where b.xh=xmsbr)  else (select xm from yhb b where b.yhm=xmsbr) end)xmsbr" +
					"  from view_csmz_tzxmxx where id=?";
			String[] colList = new String[] { "id", "xn", "xq", "xmmc", "kmmc",
					"bmmc", "zzdw", "zbsj", "xmjb", "xmms","xmsbr" };
			String[] temV = dao.getOneRs(sql, new String[] { xmdm }, colList);
			if (temV != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(),(temV[i] == null || temV[i].equalsIgnoreCase("")) ? "": temV[i]);
				}
			}
			sql = " select jxid,jxm from csmz_tzxmjxb where xmid=?";
			jxjbList = dao.getList(sql, new String[] { xmdm }, new String[] {
					"jxid", "jxm" });
		}
		if (act != null && act.equalsIgnoreCase("save")) {// 数据保存
			String cyjs = DealString.toGBK(request.getParameter("cyjs"));
			String cgms = DealString.toGBK(request.getParameter("cgms"));
			String sfdy = DealString.toGBK(request.getParameter("sfdy"));
			sfdy = Base.isNull(sfdy)?"是":sfdy;
			if (pkValue == null || "".equalsIgnoreCase(pkValue)) {
				yhcz = "添加记录";
				sql = "delete from csmz_tzcgb where xh=? and xmid=? and cyjs=? and jxlb=?";
				done = dao.runUpdate(sql, new String[] { xh, xmdm,cyjs,hjjb});
				if (done) {
					sql = "insert into csmz_tzcgb(xh,xmid,cyjs,cgms,jxlb,sfdy)values(?,?,?,?,?,?)";
					done = dao.runUpdate(sql, new String[] { xh, xmdm, cyjs,
							cgms, hjjb,sfdy });
				}
				
				map.put("cyjs", cyjs);
				map.put("cgms", cgms);
				map.put("jxlb", hjjb);
			} else {
				yhcz = "修改记录";
				sql = "select * from csmz_tzcgb where " + pk + "=?";
				oldV = dao.getOneRs(sql, new String[] { pkValue },
						new String[] { "xh", "xmid", "cyjs", "cgms", "jxlb" });
				sql = "update csmz_tzcgb set xmid=?,cyjs=?,cgms=?,jxlb=?,sfdy=? where " + pk
				+ "=?";
				done = dao.runUpdate(sql, new String[] { xmdm, cyjs, cgms,
						hjjb,sfdy, pkValue });
				map.put("cyjs", cyjs);
				map.put("cgms", cgms);
				map.put("jxlb", hjjb);
				map.put("sfdy", sfdy);
			}
			if (done) {
				dao.writeLog(sql, new String[] { xh, xmdm, cyjs, cgms, hjjb, },oldV, yhcz, request);
				request.setAttribute("done", "yes");
			} else {
				request.setAttribute("done", "no");
			}

		}
		if (doType.equalsIgnoreCase("modi") || doType.equalsIgnoreCase("view")) {
			String[] colList = new String[] { "jxlb", "cyjs", "cgms" ,"sfdy"};
			String[] temV = dao.getOneRs(
					"select jxlb,cyjs,cgms,sfdy from csmz_tzcgb where xh||xmid||cyjs||jxlb=?",
					new String[] { pkValue }, colList);
			if (temV != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(),(temV[i] == null || temV[i].equalsIgnoreCase("")) ? "": temV[i]);
				}
			}
		} else if (doType.equalsIgnoreCase("del")) {
			if (doType.equalsIgnoreCase("del")) {// 删除记录
				yhcz = "删除记录";
				sql = "select * from csmz_tzcgb where " + pk + "=?";
				oldV = dao.getOneRs(sql, new String[] { pkValue },
						new String[] { "xh", "xmid", "cyjs", "cgms", "jxlb" });
				sql = "delete from csmz_tzcgb where  " + pk + "=?";
				done = dao.runUpdate(sql, new String[] { pkValue });
				if (done) {
					request.setAttribute("done", "yes");
					dao.writeLog(sql, new String[] {}, oldV, yhcz, request);
				}
				return new ActionForward(
						"/csmz_sztz.do?method=data_search&go=go&xh=", false);
			}
		}
		map.put("", "");
		request.setAttribute("jxsb", jxsb);
		request.setAttribute("jxjbList", jxjbList);
		request.setAttribute("xmdm", xmdm);
		request.setAttribute("rs", map);
		request.setAttribute("cjsfList", myDao.getTzxmCjSfList());// 发送素质拓展项目参加身份列表
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("tips", tips);
		return mapping.findForward("tzcg_sb");
	}
	
	/**
	 * 素质拓展个人拓展成果申报
	 * 批量维护
	 * @throws Exception 
	 */
	public ActionForward tzcg_wh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();
		SztzDao myDao = new SztzDao();
		DAO dao = DAO.getInstance();
		SztzLzzyService service =new SztzLzzyService();
		SztzForm sztzForm = (SztzForm) form;
		
		String userType=session.getAttribute("userType").toString();
		String userDep=session.getAttribute("userDep").toString();
		String doType=request.getParameter("doType");
		String xn=sztzForm.getXn();
		String xq=sztzForm.getXq();
		String kmdm=sztzForm.getKmdm();
		String xmjb=sztzForm.getXmjb();
		
		List xmjbList = initTzXmJbList(Base.xxdm,"");
		List kmList = dao.getList("select kmdm,kmm from sztz_kmdmb",
				new String[] {}, new String[] { "kmdm", "kmm" });
		
		request.setAttribute("hdxmList", service.getHdxm(xn, xq, kmdm,xmjb ));
		
		//批量保存
		if("save".equalsIgnoreCase(doType)){
			service.plSave(sztzForm, request);
		}
		
		sztzForm.setXydm(sztzForm.getXydm());
		sztzForm.setZydm(sztzForm.getZydm());
		sztzForm.setBjdm(sztzForm.getBjdm());
		sztzForm.setXzx(sztzForm.getXzx());
		sztzForm.setXmmc(sztzForm.getXmmc());
		
		if("xy".equalsIgnoreCase(userType)){
			sztzForm.setQuery_xydm(userDep);
		}
		
		List jxjbList = service.getJxjbList(request, sztzForm.getXmmc());
		request.setAttribute("path", "csmz_sztz.do?method=tzcg_wh");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("cjsf", myDao.getTzxmCjSfList());
		request.setAttribute("jxjbList", jxjbList);
		request.setAttribute("kmList", kmList);
		request.setAttribute("xmjbList", xmjbList);
		return mapping.findForward("tzcg_wh");
	}
	
	
	/**
	 * @describe 拓展项目信息查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward tzxm_xxcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DAO dao = DAO.getInstance();
//		SztzDao myDao = new SztzDao();
		SztzForm myForm = (SztzForm) form;
		String tips = "素质拓展 - 拓展项目信息 - 查询";
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String url = request.getParameter("url");
		String xmmc = DealString.toGBK(myForm.getXmmc());
		String kmdm = myForm.getKmdm();
		String xmjb = DealString.toGBK(myForm.getXmjb());
		String xh = request.getParameter("xh");
//		String xxdm = dao.getXxdm();
		String sql = "";
		String xydm = dao.getOneRs("select xydm from view_xsjbxx where xh=?",
				new String[] { xh }, "xydm");//该生所在学院		
//		String userOnline = request.getSession().getAttribute("userOnLine").toString();
//		String userName   = request.getSession().getAttribute("userName").toString();
//		String userType = request.getSession().getAttribute("userType").toString();	
		boolean xnxqNos = false;
		if(xn==null){
			myForm.setXn(Base.currXn);
		}
		/**
		if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){
			sql = "select xn,xq from csmz_sztzszb where rownum=1 ";
			String[] sqsj = dao.getOneRs(sql, new String[] {}, new String[]{ "xn","xq" });
			if(sqsj!=null){
				xn=sqsj[0];
				xq=sqsj[1];
				myForm.setXn(xn);				
				myForm.setXq(xq);				
				xnxqNos = true;
			}
		}
		 */
		request.setAttribute("xnxqNos",xnxqNos);
		StringBuffer querry = new StringBuffer();
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String pk = " id ";
		String tableName = "view_csmz_tzxmxx";
		String rsNum = "";

		querry.append(" where 1=1 ");
		querry.append((Base.isNull(xn)) ? " and 1=1" : " and xn ='" + xn+ "' ");
		querry.append((Base.isNull(xq)) ? " and 1=1" : " and xq ='" + xq+ "' ");
		querry.append((Base.isNull(xmmc)) ? " and 1=1" : " and xmmc like '%"+ xmmc + "%' ");
		querry.append((Base.isNull(kmdm)) ? " and 1=1" : " and kmdm ='" + kmdm+ "' ");		
		//if("student".equalsIgnoreCase(userOnline)){
		querry.append(" and (xxsh='通过')");
		//}else{
		//if(Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)){
//		if(!"admin".equalsIgnoreCase(userType)){
		//querry.append(" and xmsbr='"+userName+"' ");
//		}
		//}
		//querry.append(" and ((xysh='通过' and xmjb='班级' ) or (xxsh='通过' and xmjb<>'班级')) ");
		//}

//		if(Base.isNull(xmjb)){//未选择项目级别，查询数据中项目级别为“院系”或“班级”的项目信息只能所属该生所在院系项目
		querry.append(" and ((xmjb='院系' and xydm='"+xydm+"') or (xmjb='院系' and xydm not in( ");
		querry.append(" select bmdm from zxbz_xxbmdm where bmjb='1' and bmlb='5' union all( ");
		querry.append(" select b.bmdm from zxbz_xxbmdm a,zxbz_xxbmdm b where a.bmjb='1' and a.bmlb='5' ");
		if(!Base.isNull(xh)){
			querry.append(" and a.bmdm=b.bmfdm ) )) or (xmjb='班级' and xydm='"+xydm+"' and bjdm='"+dao.getOneRs("select bjdm from view_xsjbxx where xh=? ",new String[]{xh},"bjdm")+"' ) or (xmjb<>'院系' and xmjb<>'班级') )");
		}else{
			querry.append(" and a.bmdm=b.bmfdm ) )) or (xmjb='班级' and xydm='"+xydm+"' ) or (xmjb<>'院系' and xmjb<>'班级') )");				
		}
//		}else{
//		if("院系".equalsIgnoreCase(xmjb)
//		||"班级".equalsIgnoreCase(xmjb)){				
//		querry.append((Base.isNull(xydm)) ? " and 1=1" : " and xydm ='" + xydm+ "' ");

//		}
		querry.append( Base.isNull(xmjb)?"":" and xmjb ='" + xmjb+ "' ");
//		}
		myForm.setXmmc(xmmc);
		myForm.setXmjb(xmjb);
		colList = new String[] { "行号","主键","id","xn", "xqmc","xmmc","kmmc","xmjb","xymc","xmsbr"};
		sql = "select * from (select * from( select rownum 行号,"
			+ pk
			+ " 主键,rownum r,a.id,a.xn,a.xqmc,a.xmmc,a.kmmc,a.xmjb,a.xymc,(case when xmjb='班级'then(select distinct xm from view_xsjbxx b where b.xh=a.xmsbr) else (select distinct xm from yhb b where a.xmsbr=b.yhm) end)xmsbr from "
			+ tableName
			+ " a"
			+ querry
			+ ") where rownum<="
			+ (myForm.getPages().getStart() + myForm.getPages().getPageSize()) + ") where r>"
			+ myForm.getPages().getStart();
		// 分页
		myForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs("select count(*) count from " + tableName+ " a" + querry, new String[] {},"count")));
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		List kmList = dao.getList("select kmdm,kmm from sztz_kmdmb",
				new String[] {}, new String[] { "kmdm", "kmm" });
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("kmList", kmList);

		List xmjbList = initTzXmJbList(Base.xxdm,"");
//		if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){
//		request.setAttribute("xmjbList", myDao.getTzXmJbList(5));
//		}else if(Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)){
//		request.setAttribute("xmjbList", myDao.getTzXmJbList(6));
//		}else{
//		request.setAttribute("xmjbList", myDao.getTzXmJbList(4));
//		}
		request.setAttribute("xmjbList",xmjbList);
		request.setAttribute("tips", tips);
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("url", DealString.toString(url));
		request.setAttribute("xh", xh);
		
		return mapping.findForward("tzxm_xxcx");
	}

	/**
	 * @describe (学生)拓展成果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward cgsb_result(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		String userType = request.getSession().getAttribute("userOnLine").toString();
		String sql = "";
		SztzForm applyForm = (SztzForm) form;
		String xh = applyForm.getXh();
		String xxdm = dao.getXxdm();
		String tips = "素质拓展 - 拓展学分个人申报 - 申报结果查询";

		String[] colList = null;
		String[] colListCN = null;
		Vector<Object> rs = new Vector<Object>();
		colList = new String[] { "行号", "xn", "xqmc", "xmmc", "sbsj", "xmjb","xf","fdysh","xysh","xxsh" };
		sql = "select rownum 行号, a.xn,a.xqmc,a.xmmc,a.sbsj,xmjb,xf,fdysh,xysh,xxsh from view_csmz_cgsbxx a where xh=?";
		if(Globals.XXDM_LZZYJSXY.equalsIgnoreCase(xxdm)){
			tips = "第二课堂活动 -学生活动分申报 - 申报结果查询";
		}
		if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){
			tips = "素质拓展 - 素质拓展成果申报 - 申报结果查询";
		}else if(Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)){
			tips = "素质拓展 - 拓展得分个人申报 - 申报结果查询";
			colList = new String[] { "行号", "xn", "xqmc", "xmmc", "sbsj", "xmjb","xf","fdysh","xysh","xxsh" };				
		}else{
			colList = new String[] { "行号", "xn", "xqmc", "xmmc", "sbsj", "xmjb","xf","fdysh","xysh","xxsh" };			
		}

		request.setAttribute("tips",tips);
		if (userType.equalsIgnoreCase("student")) {
			xh = request.getSession().getAttribute("userName").toString();
			colListCN = dao.getColumnNameCN(colList, "view_csmz_cgsbxx");
			List topTr = dao.arrayToList(colList, colListCN);

			rs.addAll(dao.rsToVator(sql, new String[] { xh }, colList));
			request.setAttribute("topTr", topTr);
			request.setAttribute("rs", rs);
			// request.setAttribute("writeAble", "no");
			request.setAttribute("num", String.valueOf(rs.size()));
		} else {
			return new ActionForward("/csmz_sztz.do?method=data_search&act=cgsb", false);// 项目申报查询
		}		
		return mapping.findForward("cgsb_result");
	}

	/**
	 * @describe 素质拓展设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward param_set(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		DAO dao = DAO.getInstance();
		SztzForm myForm = (SztzForm) form;
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String sql = "";
		String act = request.getParameter("act");
		String xxdm = dao.getXxdm();
		String userType = session.getAttribute("userOnLine").toString();
		boolean isStu = (userType.equalsIgnoreCase("student"));
		String tips = "素质拓展 - 参数设置 - 参数设定";
		if(Globals.XXDM_LZZYJSXY.equalsIgnoreCase(xxdm)){
			tips = "第二课堂活动 - 参数设置 - 参数设定";
		}


		if (isStu) {
			request.setAttribute("errMsg", "学生用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
		if (act != null && act.equalsIgnoreCase("save")) {// 保存、修改
			String kssj = request.getParameter("kssqsj");
			String jssj = request.getParameter("jssqsj");
			String bjgbdm = request.getParameter("bjgbdm");
			String xn    = request.getParameter("xn");
			String xq    = request.getParameter("xq");
			boolean done = false;

			sql = "delete from csmz_sztzszb";
			done = dao.runUpdate(sql, new String[] {});
			if (done) {
				sql = "insert into csmz_sztzszb(kssj,jssj,bjgbdm,xn,xq)values(?,?,?,?,?)";
				done = dao.runUpdate(sql, new String[] { kssj, jssj, bjgbdm,xn,xq });
			}
			if (done) {
				request.setAttribute("ok", "ok");
				String logMsg = userName + "设置了素质拓展参数：申请开始时间" + kssj
				+ "、申请结束时间" + jssj;
				dao.writeLog(sql, new String[] {}, new String[] {}, logMsg,
						request);
			} else {
				request.setAttribute("ok", "no");
			}
		}
		sql = "select strtodatetime(substr(kssj,1,8)) kssj1,"
			+ "substr(kssj,9,2) kssj2," + "substr(kssj,11,2) kssj3,"
			+ "substr(kssj,13,2) kssj4,"
			+ "strtodatetime(substr(jssj,1,8)) jssj1,"
			+ "substr(jssj,9,2) jssj2," + "substr(jssj,11,2) jssj3,"
			+ "substr(jssj,13,2) jssj4,bjgbdm,xn,xq from csmz_sztzszb "
			+ "where rownum=1";
		String[] colList = { "kssj1", "kssj2", "kssj3", "kssj4", "jssj1",
				"jssj2", "jssj3", "jssj4", "bjgbdm","xn","xq" };
		String[] sqsj = dao.getOneRs(sql, new String[] {}, colList);
		if (sqsj == null) {
			sqsj = new String[colList.length];
			for (int i = 0; i < sqsj.length; i++) {
				sqsj[i] = "";
			}
		} else {
			myForm.setBjgbdm(sqsj[8]);
			myForm.setXn(sqsj[9]);
			myForm.setXq(sqsj[10]);
		}
		for (int i = 0; i < sqsj.length; i++) {
			request.setAttribute(colList[i], sqsj[i]);
		}
		List bjgbList = dao.getList("select bjgbdm,bjgbmc from sxjy_bjgbzlb ",
				new String[] {}, new String[] { "bjgbdm", "bjgbmc" });
		request.setAttribute("bjgbList", bjgbList);
		if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)||Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)){//长沙民政、宁波理工学院
			request.setAttribute("showXsGbCzZl", "showXsGbCzZl");//学生干部操作种类
		}
		if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){
			request.setAttribute("showCSMZZYJSXY", "showCSMZZYJSXY");
		}
		if(Globals.XXDM_JSXX.equalsIgnoreCase(xxdm)){
			request.setAttribute("showJSXX","showJSXX");			
		}
		if(Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)){
			request.setAttribute("XXDM_BJLHDX", "XXDM_BJLHDX");
		}
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		request.setAttribute("tips",tips);
		return mapping.findForward("param_set");
	}

	/**
	 * @describe 拓展成果审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tzcg_sh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String pkValue = RowidToPk.rowidToPK(request.getParameter("pkValue"));
//		String xmid = DealString.toString(request.getParameter("xmid"));
//		String xmjb =  DealString.toString(DealString.toGBK(request.getParameter("xmjb")));
//		String xh = request.getParameter("xh");
//		String jxlb = request.getParameter("jxlb");
		String sql = "";
		String yesNo = DealString.toGBK(request.getParameter("yesNo"));
		String doType = request.getParameter("doType");
		String userType = session.getAttribute("userType").toString();
		String xxdm = dao.getXxdm();
		String userDep = session.getAttribute("userDep").toString();
		String curDep = dao.getOneRs("select xgbdm from xtszb where rownum=1 ",new String[]{},"xgbdm");
//		String userName = session.getAttribute("userName").toString();
		String tips = "素质拓展 - 学分申报审核 - 审核 - 单个审核";
		if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){
			tips = "素质拓展 - 素质拓展成果申报审核 - 审核 - 单个审核";
		}
		if (!userDep.equalsIgnoreCase(curDep)) {//不属于学工部用户			
			userType = "stu".equalsIgnoreCase(userType)?userType:"xy";//部门、院系用户均设置为"xy"用户										
		}else{
			userType = "xx";
		}
		HashMap<String, String> map = new HashMap<String, String>();
		boolean done = false;
		if ("save".equalsIgnoreCase(doType)) {// 保存审核结果
			if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {
				sql = "update csmz_tzcgb set xxsh=? where rowid=?";
			} else if ("xy".equalsIgnoreCase(userType)) {
				sql = "update csmz_tzcgb set xysh=? where rowid=?";
			} else {
				sql = "update csmz_tzcgb set fdysh=? where rowid=?";
			}
			done = dao.runUpdate(sql, new String[] { yesNo, pkValue });
			if (done) {
//				if (userType.equalsIgnoreCase("xy")) {// 院系用户审核班级级别项目通过，将相关数据插入成绩表中
//				if (xmjb.equalsIgnoreCase("班级")) {
//				sql = "delete from csmz_tzcjb where xh||xmid||cyjs||jxlb =(select xh||xmid||cyjs||jxlb from csmz_tzcgb where rowid=? )";
//				boolean del = dao.runUpdate(sql,
//				new String[] { pkValue });
//				if ("通过".equalsIgnoreCase(yesNo)
//				|| "已通过".equalsIgnoreCase(yesNo)) {
//				if (del) {
//				sql = "insert into csmz_tzcjb(xh,xmid,jxlb,sfdy,cyjs) select xh,xmid,jxlb,sfdy,cyjs from csmz_tzcgb where rowid=? ";
//				dao.runUpdate(sql, new String[] { pkValue});
//				}
//				}
//				}
//				} else 
				if (userType.equalsIgnoreCase("xx")
						|| "admin".equalsIgnoreCase(userType)) {// 学校用户审核院系及以上级别项目通过，将相关数据插入成绩表中
					//if (!xmjb.equalsIgnoreCase("班级")) {
					sql = "delete from csmz_tzcjb where xh||xmid||cyjs||jxlb =(select xh||xmid||cyjs||jxlb from csmz_tzcgb where rowid=? )";
					boolean del = dao.runUpdate(sql,
							new String[] { pkValue });
					if ("通过".equalsIgnoreCase(yesNo)
							|| "已通过".equalsIgnoreCase(yesNo)) {
						if (del) {
							sql = "insert into csmz_tzcjb(xh,xmid,jxlb,sfdy,cyjs) select xh,xmid,jxlb,sfdy,cyjs from csmz_tzcgb where rowid=? ";
							dao.runUpdate(sql, new String[] {pkValue});
						}
					}
					//}
				}
				request.setAttribute("done", "yes");
				dao.writeLog(sql, new String[] { yesNo, pkValue }, null,
						"拓展项目审核", request);
			} else {
				request.setAttribute("done", "no");
			}
		}
		// 生成页面
		if (userType.equalsIgnoreCase("xx")
				|| userType.equalsIgnoreCase("admin")) {
			sql = "select xmid,xh,xm,xb,nj,xymc,zymc,bjmc,xn,xq,xmmc,kmmc,xmjb,jxlb,jxm,cyjs,sbsj,xmsbrmc,sqbmmc,cgms,zzdw,zbsj,xxsh yesNo from view_csmz_cgsbxx where pk=?";
			String xmjbV = dao.getOneRs("select xmjb from view_csmz_cgsbxx where xh||xmid=?",
					new String[] { pkValue }, "xmjb");
			if (xmjbV == "班级" || xmjbV.equalsIgnoreCase("班级")) {
				request.setAttribute("isBJXM", "isBJXM");// 针对班级项目成果,学校用户只能查看
			}
		} else if (userType.equalsIgnoreCase("xy")) {
			sql = "select xmid,xh,xm,xb,nj,xymc,zymc,bjmc,xn,xq,xmmc,kmmc,xmjb,jxlb,jxm,cyjs,sbsj,xmsbrmc,sqbmmc,cgms,zzdw,zbsj,xysh yesNo from view_csmz_cgsbxx where pk=?";
		} else {
			sql = "select xmid,xh,xm,xb,nj,xymc,zymc,bjmc,xn,xq,xmmc,kmmc,xmjb,jxlb,jxm,cyjs,sbsj,xmsbrmc,sqbmmc,cgms,zzdw,zbsj,fdysh yesNo from view_csmz_cgsbxx where pk=?";
		}
		map = dao.getMap(sql, new String[] {pkValue}, new String[] { "xmid",
				"xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc", "xn", "xq",
				"xmmc", "kmmc", "xmjb", "jxlb", "jxm", "cyjs", "sbsj","xmsbrmc",
				"sqbmmc","cgms","zzdw", "zbsj", "yesNo" });
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("rs", map);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("tips",tips);
		return mapping.findForward("tzcg_sh");
	}
	public ActionForward  plCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)  throws Exception{
		DAO dao = DAO.getInstance();
		String xxdm   = Base.xxdm;
		String pkValue = request.getParameter("pkVStr");
		String check   = request.getParameter("check");
		String kcxf    = request.getParameter("kcxf");
		String shV     = "";
		String jxlb =request.getParameter("jxlb");
		if("yes".equalsIgnoreCase(check)){
			shV = "通过";
		}else if("no".equalsIgnoreCase(check)){
			shV = "不通过";
		}else{
			shV = "未审核";
		}
		String[] pkValueA = pkValue.split("!!");
		String sql = "";
		StringBuffer sqlStr = new StringBuffer();
		String userDep = request.getSession().getAttribute("userDep").toString();
		String curDep = dao.getOneRs("select xgbdm from xtszb where rownum=1 ",new String[]{},"xgbdm");
		curDep = Base.isNull(curDep)?"":curDep;
		String userType = request.getSession().getAttribute("userType").toString();
		if (!userDep.equalsIgnoreCase(curDep)) {//不属于学工部用户
			userType = "stu".equalsIgnoreCase(userType)?userType:"xy";//非学生班干部用户设置为"xy"用户				
		}else{
			userType = "xx";
		}
		for(int i=0;i<pkValueA.length;i++){
			if(Globals.XXDM_WHTLZYJSXY.equalsIgnoreCase(Base.xxdm)){
				if ("xx".equalsIgnoreCase(userType)
						|| "admin".equalsIgnoreCase(userType)) {
					sql = "delete from csmz_tzcjb where xh||xmid||cyjs||jxlb =(select xh||xmid||cyjs||jxlb from csmz_tzcgb where  xh||xmid||cyjs||jxlb='"+pkValueA[i]+"') ";
					sqlStr.append(Base.isNull(pkValueA[i])?"":sql).append("!!");
					if("yes".equalsIgnoreCase(check)){
						sql = "insert into csmz_tzcjb(xh,xmid,jxlb,sfdy,cyjs) select xh,xmid,'"+jxlb+"',sfdy,cyjs from csmz_tzcgb where xh||xmid||cyjs||jxlb='"+pkValueA[i]+"' ";
						sqlStr.append(Base.isNull(pkValueA[i])?"":sql).append("!!");
					}
					
					sql = "update csmz_tzcgb set xxsh='"+shV+"',jxlb='"+jxlb+"' where xh||xmid||cyjs||jxlb='"+pkValueA[i]+"' ";
					sqlStr.append(Base.isNull(pkValueA[i])?"":sql).append("!!");
				
					
				} else if ("xy".equalsIgnoreCase(userType)) {
					sql = "update csmz_tzcgb set xysh='"+shV+"',jxlb='"+jxlb+"' where xh||xmid||cyjs||jxlb='"+pkValueA[i]+"' ";
					sqlStr.append(Base.isNull(pkValueA[i])?"":sql).append("!!");
				}else if("stu".equalsIgnoreCase(userType)){
					sql = "update csmz_tzcgb set fdysh='"+shV+"' where xh||xmid||cyjs||jxlb='"+pkValueA[i]+"' ";
					sqlStr.append(Base.isNull(pkValueA[i])?"":sql).append("!!");				
				}
			}else{
				if ("xx".equalsIgnoreCase(userType)
						|| "admin".equalsIgnoreCase(userType)) {
					sql = "update csmz_tzcgb set xxsh='"+shV+"' where xh||xmid||cyjs||jxlb='"+pkValueA[i]+"' ";
					sqlStr.append(Base.isNull(pkValueA[i])?"":sql).append("!!");
					sql = "delete from csmz_tzcjb where xh||xmid||cyjs||jxlb =(select xh||xmid||cyjs||jxlb from csmz_tzcgb where  xh||xmid||cyjs||jxlb='"+pkValueA[i]+"') ";
					sqlStr.append(Base.isNull(pkValueA[i])?"":sql).append("!!");
					if("yes".equalsIgnoreCase(check)){
						sql = "insert into csmz_tzcjb(xh,xmid,jxlb,sfdy,cyjs) select xh,xmid,jxlb,sfdy,cyjs from csmz_tzcgb where xh||xmid||cyjs||jxlb='"+pkValueA[i]+"' ";
						sqlStr.append(Base.isNull(pkValueA[i])?"":sql).append("!!");
					}
				} else if ("xy".equalsIgnoreCase(userType)) {
					sql = "update csmz_tzcgb set xysh='"+shV+"' where xh||xmid||cyjs||jxlb='"+pkValueA[i]+"' ";
					sqlStr.append(Base.isNull(pkValueA[i])?"":sql).append("!!");
				}else if("stu".equalsIgnoreCase(userType)){
					sql = "update csmz_tzcgb set fdysh='"+shV+"' where xh||xmid||cyjs||jxlb='"+pkValueA[i]+"' ";
					sqlStr.append(Base.isNull(pkValueA[i])?"":sql).append("!!");				
				}
			}
			if(Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)){//北京联合大学
				if("no".equalsIgnoreCase(check)){//当审核不通过时，将扣除分存入相关信用扣分表中
					if(!Base.isNull(kcxf)){//当存在扣除分时
						sql = " insert into xykfb(xh,xmxn,xmxq,id,kfs,kfxq) select xh,(select xn from csmz_tzcgb where xh||xmid||cyjs||jxlb='"+pkValueA[i]+"')xn," 
						+"(select xq from csmz_tzcgb where xh||xmid||cyjs||jxlb='"+pkValueA[i]+"')xq,"
						+"xh||xmid||cyjs||jxlb,'"+kcxf+"'kf,bjmc||''||xm||' '||xn||' '||xqmc||' '||xmmc||' '||xmjb||' '||jxm from view_csmz_cgsbxx where pk='"+pkValueA[i]+"'";
						sqlStr.append(Base.isNull(pkValueA[i])?"":sql).append("!!");
					}
				}
			}
		}		
		boolean doFlag = false;
		int[] array = dao.runBatch(sqlStr.toString().split("!!"));
		doFlag = dao.checkBatch(array);
		request.setAttribute("done",doFlag); 
		return new ActionForward("/csmz_sztz.do?method=data_search&act=cgsbcheck&go=go",false);
	}
	public ActionForward tzcg_shView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DAO dao = DAO.getInstance();
		String pkValue = RowidToPk.rowidToPK(request.getParameter("pkValue"));
		@SuppressWarnings("unused")
		String xmid = DealString.toString(request.getParameter("xmid"));
		@SuppressWarnings("unused")
		String xh = request.getParameter("xh");
		@SuppressWarnings("unused")
		String jxlb = request.getParameter("jxlb");
		String view = request.getParameter("view");
		String select = request.getParameter("select");
		String sql = "";
		String xxdm = dao.getXxdm();
		String jxsb = XMLReader.getFlowControl("sztz", "jxsb");
		String tips = "素质拓展 - 学分申报审核 - 审核 - 单个审核";
		HashMap<String, String> map = new HashMap<String, String>();
		if(Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)){
			tips = "素质拓展 - 得分申报审核 - 审核 - 单个审核";
		}
		if(Globals.XXDM_LZZYJSXY.equalsIgnoreCase(xxdm)){
			tips = " 第二课堂活动 - 学生活动分申报 - 活动分申报审核";
		}
		// 生成页面		
		sql = "select xmid,xh,xm,xb,nj,xymc,zymc,bjmc,xn,xqmc xq,xmmc," +
		"kmmc,xmjb,jxlb,jxm,cyjs,sbsj,cgms,zzdw,zbsj,xmsbrmc xmsbr,xmms from view_csmz_cgsbxx where pk=?";		
		map = dao.getMap(sql, new String[] { pkValue }, new String[] { "xmid",
				"xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc", "xn", "xq",
				"xmmc", "kmmc", "xmjb", "jxlb", "jxm", "cyjs", "sbsj", "cgms",
				"zzdw", "zbsj","xmsbr","xmms" });
		
		
		sql = " select jxid,jxm from csmz_tzxmjxb where xmid=?";
		List<HashMap<String,String>>jxjbList = dao.getList(sql, new String[] { map.get("xmid") }, new String[] {
				"jxid", "jxm" });
		request.setAttribute("jxjbList", jxjbList);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("rs", map);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("tips",tips);
		request.setAttribute("view",view);
		request.setAttribute("select",select);
		request.setAttribute("sel",request.getParameter("sel"));
		request.setAttribute("jxsb",jxsb);
		return mapping.findForward("tzcg_shView");
	}

	/**
	 * @describe 全班通过审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward all_check(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DAO dao = DAO.getInstance();
		SztzForm myForm = (SztzForm) form;
		String userType = request.getParameter("userType");
		String bjdm = myForm.getBjdm();
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		boolean res = false;
		String sql = "{call csmz_xfsbautochk(?,?,?,?)}";
		res = dao.runProcuder(sql, new String[] { userType, bjdm, xn, xq });
		if (res) {
			request.setAttribute("autoChk", "ok");
		} else {
			request.setAttribute("autoChk", "no");
		}
		return mapping.findForward("all_check");
	}

	/**
	 * @describe 拓展成绩信息维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xxwh_tzcgcj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DAO dao = DAO.getInstance();
		SztzForm sztzForm = (SztzForm) form;
		SztzDao myDao = new SztzDao();
		String sql = "";
		String act = request.getParameter("act");
		String doType = DealString.toString(request.getParameter("doType"));
		String pkValue = DealString.toString(request.getParameter("pkValue"));
		String pk = "rowid";
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = dao.getOneRs("select xh from view_tzcgcjxx  where pk=?", new String[]{pkValue}, "xh");
		String xmdm = dao.getOneRs("select xmid from view_tzcgcjxx where pk=?", new String[]{pkValue}, "xmid");
		String hjjb = sztzForm.getJxlb();
		String xxdm = dao.getXxdm();
		String cyjs = DealString.toGBK(request.getParameter("cyjs"));
		String cxdo = request.getParameter("cxdo");
		String[] oldV = null;
		String yhcz = "";
		if(Base.isNull(xh)){
			xh = sztzForm.getXh().trim();
		}
		if(!Base.isNull(cxdo)||Base.isNull(xmdm)){   		
			xmdm = sztzForm.getXmdm().trim();
		}

		// 奖项List
		sql = " select distinct jxm,jxid from csmz_tzxmjxb where 1=2";
		List jxjbList = dao.getList(sql, new String[] {}, new String[] {
				"jxid", "jxm" });
		boolean done = false;
		// 学生信息
		sql = "select xh,xm,xb,nj,xymc,zymc,bjmc from view_xsjbxx where xh=?";
		map = dao.getMap(sql, new String[] { xh }, new String[] { "xh", "xm",
				"xb", "nj", "xymc", "zymc", "bjmc" });

		if (act != null && act.equalsIgnoreCase("save")) {// 数据保存
			String sfdy  = DealString.toGBK(request.getParameter("sfdy"));
			xmdm  = sztzForm.getXmdm().trim();
			sfdy = Base.isNull(sfdy)?"是":sfdy;
			if (pkValue == null || "".equalsIgnoreCase(pkValue)) {
				yhcz = "添加记录";
//				sql = "delete from csmz_tzcjb where xh=? and xmid=?";
//				done = dao.runUpdate(sql, new String[] { xh, xmdm });
//				if (done) {					
				sql = "insert into csmz_tzcjb(xh,xmid,jxlb,sfdy,cyjs)values(?,?,?,?,?)";					
				done = dao.runUpdate(sql, new String[] { xh, xmdm, hjjb,sfdy,cyjs });
//				}
			} else {
				yhcz = "修改记录";
				sql = "select xh,xmid,jxlb from csmz_tzcjb where " + pk + "=?";
				oldV = dao.getOneRs(sql, new String[] { pkValue },
						new String[] { "xh", "xmid", "jxlb" });
				sql = "update csmz_tzcjb set xmid=?,jxlb=?,sfdy=?,cyjs=? where " + pk + "=?";
				done = dao.runUpdate(sql, new String[] { xmdm, hjjb,sfdy,cyjs, pkValue });
			}
			if (done) {
				dao.writeLog(sql, new String[] { xh, xmdm, hjjb, }, oldV, yhcz,request);
				request.setAttribute("done", "yes");
			} else {
				request.setAttribute("done", "no");
			}
		}

		if (doType.equalsIgnoreCase("modi") || doType.equalsIgnoreCase("view")) {
			String[] colList = new String[] { "jxlb", "xf","sfdy","cyjs" };
			String[] temV = dao.getOneRs(
					"select jxlb,xf,sfdy,cyjs from view_tzcgcjxx  where pk=?",
					new String[] { pkValue }, colList);
			if (temV != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(),(temV[i] == null || temV[i].equalsIgnoreCase("")) ? "": temV[i]);
				}
			}
		} else if (doType.equalsIgnoreCase("del")) {
			if (doType.equalsIgnoreCase("del")) {// 删除记录
				yhcz = "删除记录";
				sql = "select xh,xmid,jxlb from csmz_tzcjb where " + pk + "=?";
				oldV = dao.getOneRs(sql, new String[] { pkValue },
						new String[] { "xh", "xmid", "jxlb" });
				sql = "delete from csmz_tzcjb where  " + pk + "=?";
				done = dao.runUpdate(sql, new String[] { pkValue });
				if (done) {
					request.setAttribute("done", "yes");
					dao.writeLog(sql, new String[] {}, oldV, yhcz, request);
				}
				return new ActionForward(
						"/csmz_sztz.do?method=data_search&go=go&xh=", false);
			}
		}

		// 项目信息
		if (xmdm != null && !"".equalsIgnoreCase(xmdm)) {
			sql = "select id,xn,xq,xmmc,kmmc,xymc bmmc,zzdw,zbsj,xmjb from view_csmz_tzxmxx where id=?";
			String[] colList = new String[] { "id", "xn", "xq", "xmmc", "kmmc",
					"bmmc", "zzdw", "zbsj", "xmjb" };
			String[] temV = dao.getOneRs(sql, new String[] { xmdm }, colList);
			if (temV != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(),(temV[i] == null || temV[i].equalsIgnoreCase("")) ? "": temV[i]);
				}
			}
			// 项目对应的奖项List
			sql = " select jxid,jxm from csmz_tzxmjxb where xmid=?";
			jxjbList = dao.getList(sql, new String[] { xmdm }, new String[] {
					"jxid", "jxm" });
		}


		if (xmdm != null && !"".equalsIgnoreCase(xmdm)) {
			if (hjjb != null && !hjjb.equalsIgnoreCase("")) {
				String xf = dao.getOneRs(
						"select xf from csmz_tzxmjxb where xmid=? and jxid=?",
						new String[] { xmdm, hjjb }, "xf");
				map.put("xf", xf);
				map.put("jxlb", hjjb);
			}
		}
		String fsclin = "学分";
		if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){						
			request.setAttribute("showdybz","showdybz");//打印标志	
			
		}
		if(Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)){
			request.setAttribute("XXDM_BJLHDX","XXDM_BJLHDX");//打印标志	
			fsclin = "得分";
		}
		if(Globals.XXDM_LZZYJSXY.equalsIgnoreCase(xxdm)){
			fsclin = "活动分";
		}
		request.setAttribute("fsclin", fsclin);
		map.put("", "");
		request.setAttribute("jxjbList", jxjbList);
		request.setAttribute("xmdm", xmdm);
		request.setAttribute("rs", map);
		request.setAttribute("cjsfList", myDao.getTzxmCjSfList());// 发送素质拓展项目参加身份列表
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("xxwh_tzcgcj");
	}

	/**
	 * @describe 素质拓展项目汇总
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward tzxm_tj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		SztzForm myForm = (SztzForm) form;
		String xxdm = Base.xxdm;
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
//		SztzDao myDao = new SztzDao();
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");
		String sql = "";
		String sqbm = "";// 申请部门
		Vector<Object> rs = new Vector<Object>();
		Vector<Object> rs2 = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String rsNum = "";
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String xmjb = DealString.toGBK(myForm.getXmjb());
		String xydm = myForm.getXydm();
		String tips = "素质拓展 - 统计分析 - 拓展项目汇总";
		if(Globals.XXDM_LZZYJSXY.equalsIgnoreCase(xxdm)){
			tips = "第二课堂活动 - 统计分析 - 活动项目汇总";
		}
		if (userType.equalsIgnoreCase("xy")) {
			myForm.setXydm(userDep);
			xydm = userDep;
		}
		if(xn==null){
			xn = Base.currXn;
			myForm.setXn(xn);
		}
		if(xq==null){
			xq = Base.currXq;
			myForm.setXq(xq);
		}
		myForm.setXmjb(xmjb);
		querry.append((Base.isNull(xn)) ? " and 1=1 " : " and xn='" + xn+ "' ");
		querry.append((Base.isNull(xq)) ? " and 1=1 " : " and xq='" + xq+ "' ");
		querry.append((Base.isNull(xmjb) ? " and 1=1 " : " and xmjb='" + xmjb+ "' "));
		querry.append((Base.isNull(xydm)) ? " and 1=1" : " and xydm='" + xydm+ "'");

		sql = " select rownum 行号,a.* from  view_csmz_tzxmtjxx  a"+ querry;
		colList = new String[] { "行号", "xn", "xqmc", "kmmc", "xmmc", "zbsj",
				"zzdw", "xmjb", "hddx" };
		colListCN = dao.getColumnNameCN(colList, "view_csmz_tzxmtjxx");
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		if (request.getParameter("print") != null
				&& request.getParameter("print").equalsIgnoreCase("do")) {// 项目报表打印
			sqbm = DealString.toString(dao.getOneRs("select bmmc from ZXBZ_XXBMDM where bmdm=?", new String[]{xydm},"bmmc"));
			colList = new String[] { "行号", "kmmc", "xmmc", "zbsj", "xmjb",
			"hddx" };
			rs2.addAll(dao.rsToVator(sql, new String[] {}, colList));
			request.setAttribute("xxmc", Base.xxmc);
			request.setAttribute("rs2", rs2);
			request.setAttribute("xn", xn);
			request.setAttribute("xq", xq);
			request.setAttribute("sqbm", sqbm);
			request.setAttribute("sqrq", DealString.getDateTime().substring(0,10));
			return mapping.findForward("tzxm_tj_print");
		}


		List xmjbList = initTzXmJbList(Base.xxdm,"");
//		if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(Base.xxdm)){
//		xmjbList= myDao.getTzXmJbList(5);
//		}else if(Globals.XXDM_BJLHDX.equalsIgnoreCase(Base.xxdm)){
//		xmjbList= myDao.getTzXmJbList(6);
//		}else{
//		xmjbList = myDao.getTzXmJbList(4);
//		}
		request.setAttribute("xmjbList", xmjbList);
		request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xqList", Base.getXqList());// 发送学期列表
		request.setAttribute("bmList",dao.getList("select distinct bmdm,bmmc from ZXBZ_XXBMDM order by bmmc nulls first", new String[]{},new String[]{"bmdm", "bmmc"}));
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("userType", userType);
		request.setAttribute("tips", tips);
		return mapping.findForward("tzxm_tj");
	}

	/**
	 * @describe 拓展学分查询统计
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward tzxf_cxtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		StringBuffer querry = new StringBuffer();
		querry.append(" where 1=1 ");
		String xxdm     = dao.getXxdm();
		SztzForm myForm = (SztzForm) form;
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String nj = myForm.getNj();
		String nd = myForm.getNd();//年段
		String yesNo = myForm.getYesNo();
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String bjdm = myForm.getBjdm();
		String cxcz = DealString.toString(myForm.getCxcz());// 统计类型
		String fsdcx = myForm.getFsdcx();// 分数段查询类型
		String printDo = request.getParameter("print");// 报表打印操作
		String[] colList = new String[] {};
		String[] colListCN = new String[] {};
		List topTr = null;
		Vector<Object> rs = new Vector<Object>();
		String rsNum = "";
		String clue = "";// 提示
		String sfXsPage = "true";// 是否分页显示
		String bbymType = "";// 报表页面类型
		String tableName = "view_tzcjcxtj";
		String zjNj = (Integer.parseInt(Base.currNd)-6)+"";//最近年级
		String tips = "素质拓展 - 统计分析 - 学分查询统计";
		if(Globals.XXDM_LZZYJSXY.equalsIgnoreCase(xxdm)){
			tips = "第二课堂活动 - 统计分析 - 活动分查询统计";
		}
		if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){
			request.setAttribute("showcsmz","showcsmz");
			tableName = "view_csmz_tzcjcxtj";
		}else{
			querry.append((Base.isNull(xq)) ? " and 1=1 " : " and xq='" + xq
					+ "' ");
		}
		querry.append((Base.isNull(xn)) ? " and 1=1 " : " and xn='" + xn
				+ "' ");
		String sql = " select rownum 行号,a.* from " + tableName + " a where 1=2";
		String sql1 = " select count(*) count from " + tableName + " where 1=2";

		if (cxcz != null && !cxcz.equalsIgnoreCase("")) {
			if (cxcz.equalsIgnoreCase("fsd")) {// 分数段查询
				if (fsdcx != null && !fsdcx.equalsIgnoreCase("")) {
					if (fsdcx.equalsIgnoreCase("dy")) {// 大于查询
						String dyfvalue = myForm.getDyfvalue();
						dyfvalue = Base.isNull(dyfvalue)?"0":dyfvalue;
						clue = "（按分数段查询）大于" + dyfvalue + "分;";
						querry.append(" and sumxf>=to_number('" + dyfvalue
								+ "')");
						bbymType = "a";
					} else if (fsdcx.equalsIgnoreCase("xy")) {// 小于查询
						String xyvalue = myForm.getXyvalue();
						xyvalue = Base.isNull(xyvalue)?"0":xyvalue;
						querry.append(" and sumxf<=to_number(" + xyvalue + ")");
						bbymType = "b";
						clue = "（按分数段查询）小于" + xyvalue + "分;";
					} else if (fsdcx.equalsIgnoreCase("fsdvalue")) {// 区间查询
						String fsdvalues = myForm.getFsdvalues();
						String fsdvaluee = myForm.getFsdvaluee();
						fsdvalues = Base.isNull(fsdvalues)?"0":fsdvalues;
						fsdvaluee = Base.isNull(fsdvaluee)?"0":fsdvaluee;
						querry.append(" and sumxf between to_number("
								+ fsdvalues + ") and to_number(" + fsdvaluee
								+ ") ");
						clue = "（按分数段查询）介于" + fsdvalues + "分到" + fsdvaluee
						+ "分;";
						bbymType = "c";
					} else if (fsdcx.equalsIgnoreCase("zgs")) {// 名次统计
						String zgsvalue = myForm.getZgsvalue();
						zgsvalue = Base.isNull(zgsvalue)?"0":zgsvalue;
						String sqlv = "select sumxf from (select rownum r, sumxf from "
							+ "( select sumxf from "+tableName
							+ querry.toString()
							+ " group by sumxf  order by to_number(sumxf) desc) where sumxf is not null ) where r=? ";
						String xfValueS = dao.getOneRs(sqlv,
								new String[] { zgsvalue }, "sumxf");// 获得名次起始分值
						String xfValueE = dao.getOneRs(sqlv,
								new String[] { "1" }, "sumxf");// 获得名次最高学分值
						xfValueS = Base.isNull(xfValueS) ? "0" : xfValueS;
						xfValueE = Base.isNull(xfValueE) ? "0" : xfValueE;
						querry.append(" and sumxf between " + xfValueS
								+ " and " + xfValueE + " ");
						clue = "（按分数段查询）排行前" + zgsvalue + "名;";
						bbymType = "d";
					}
				}
				if (printDo.equalsIgnoreCase("do")) {// 报表打印时不分页
					sql = "select rownum r,a.* from (select * from "
						+ tableName
						+ " order by sumxf desc )  a "
						+ querry.toString() + "";
				} else {
					sql = "select * from (select * from( select rownum 行号, rownum r,a.* from (select * from "
						+ tableName
						+ " order by sumxf desc )  a "
						+ querry.toString()
						+ " ) where rownum<="
						+ (myForm.getPages().getStart() + myForm.getPages()
								.getPageSize())
								+ " ) where r>"
								+ myForm.getPages().getStart();
					sql1 = " select count(*) count from " + tableName + ""
					+ querry.toString();
				}
				if(tableName.equalsIgnoreCase("view_csmz_tzcjcxtj")){
					colList = new String[] { "xn","xh", "xm", "xymc", "zymc",
							"bjmc", "sumxf" };					
				}else{
					colList = new String[] { "xn","xqmc","xh", "xm", "xymc", "zymc",
							"bjmc", "sumxf" };
				}
				colListCN = dao.getColumnNameCN(colList, tableName);
			} else if (cxcz.equalsIgnoreCase("yxpj")) {// 按(院)系平均分统计
				sfXsPage = "false";
				clue = "按(院)系平均分统计;";
				sql = "  select xymc,case when avgxf<1 and avgxf>0 then '0'||trim(avgxf) else avgxf end avgxf from (select a.xymc, nvl(to_char(avgxf,'9999.99'),'0.00')avgxf from  (select distinct xydm,xymc from view_njxyzybj order by xymc)a left join " 
					+"(select xydm,xymc,(case when avgxf<1 then '0'||avgxf else avgxf end) avgxf from (select xydm,xymc, to_char(avg(sumxf)) avgxf from "
					+ tableName
					+ querry
					+ " group by xydm, xymc order by avgxf desc))b on a.xydm=b.xydm) ";
				colList = new String[] {  "xymc", "avgxf" };
				colListCN = new String[] {  "院系", "平均分" };
				bbymType = "e";
			} else if (cxcz.equalsIgnoreCase("bjpj")) {// 按班级平均分统计
				sfXsPage = "false";
				clue = "按班级平均分统计;";
				StringBuilder bjpj=new StringBuilder();
				bjpj.append(" where 1=1 ");
				bjpj.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
				bjpj.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
				bjpj.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
				bjpj.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
				sql = " select nj,bjmc,xymc,bjdm,zydm,xydm,case when avgxf<1 and avgxf>0 then '0'||trim(avgxf) else avgxf end avgxf from(select a.nj,a.bjmc,a.xydm,a.zydm,a.bjdm, a.xymc,nvl(to_char(b.avgxf,'9999.99'),'0.00')avgxf from  (select distinct bjdm,nj,bjmc,xymc,xydm,zydm from view_njxyzybj where nj>='"+zjNj+"'  order by bjmc)a left join"
				+" (select bjdm,bjmc,(case when avgxf<1 then '0'||avgxf else avgxf end) avgxf "
				+ "from (select bjdm,bjmc,to_char(avg(sumxf)) avgxf from "+tableName
				+ querry
				+ " group by bjdm,bjmc order by avgxf desc))b on a.bjdm=b.bjdm)";
				sql+=bjpj;
				colList = new String[] {  "bjmc", "xymc", "avgxf" };
				colListCN = new String[] {  "班级", "院系", "平均分" };
				bbymType = "f";
			} else if (cxcz.equalsIgnoreCase("bjcjtj")) {// 班级学分统计
				sfXsPage = "false";
				querry.append((Base.isNull(bjdm)) ? " and 1=1 " : " and bjdm='"
					+ bjdm + "' ");
				clue = "按班级学分统计";
				if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){
					sql = "select rownum 行号,xn,xh,xm,xb,sxddsy_xf,zyfw_xf,kxjs_xf,whys_xf,shstgz_xf,jnpx_xf,sumxf from  "+tableName
					+ querry+" order by xh,xn desc";
					colList = new String[] {  "xh", "xm", "xb","xn","sxddsy_xf","zyfw_xf","kxjs_xf","whys_xf","shstgz_xf","jnpx_xf","sumxf"};
					colListCN = new String[] { "学号", "姓名", "性别","学年","思想道德素养","志愿服务","科技学术","文化艺术","社团社会工作","技能培训","总分"};
				}
				else{
					sql = " select rownum r,xh,xn,(select xqmc from xqdzb b where b.xqdm=a.xq)xqmc, "
						+"(select xm from view_xsjbxx b where b.xh=a.xh)xm, (select xb from view_xsjbxx b where b.xh=a.xh)xb, "
						+" case when xf<1 and xf>0 then '0'||trim(xf) else to_char(xf) end xf   "
						+"from (select xn,xq,xh,sum(xf)xf from  view_tzcgcjxx "
						+ querry+" group by xn,xq,xh  order by xh)a";
					colList = new String[] { "xn","xqmc", "xh", "xm", "xb", "xf" };
					colListCN = new String[] { "学年","学期","学号", "姓名", "性别",  "所获总分" };
				}
				bbymType = "g";
			}else if(cxcz.equalsIgnoreCase("bytj")){//毕业条件(长沙民政职业技术学院)
				tableName ="view_csmz_tzcjcxtj2";
				querry = new StringBuffer(" where 1=1 ");
				sql = "";
				querry.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
				querry.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
				querry.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
				querry.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
				if("dyn".equalsIgnoreCase(nd)){//第一年内									
					if("yes".equalsIgnoreCase(yesNo)){//第一年内，在思想政治与道德素养、社会实践与志愿服务、文化艺术与身心发展等3个方面分别获得1个以上学分，在科技学术与创新创业、社团活动与社会工作等2个方面分别获得0.5个以上学分，达到学分制的要求；
						querry.append(" and xn like nj||'%' ");	
						querry.append(" and sxddsy_xf>=1 and whys_xf>=1 and zyfw_xf>=1 and kxjs_xf>=0.5 and shstgz_xf>=0.5 ");						
					}else{
						querry.append(" and (xn like nj||'%' or xn is null) ");	
						querry.append(" and ((sxddsy_xf<1 or sxddsy_xf is null) or (whys_xf<1 or whys_xf is null) or (zyfw_xf<1 or zyfw_xf is null) or (kxjs_xf<0.5 or kxjs_xf is null) or (shstgz_xf<0.5 or shstgz_xf is null) ) ");						
					}
					sql = "select a.* from "+tableName+" a "+querry+" order by xh";
				}else if("den".equalsIgnoreCase(nd)){//第二年内					
					if("yes".equalsIgnoreCase(yesNo)){//第二年内与第一年同样
						querry.append(" and xn like '%'||(nj+1)||'%'||(nj+2)||'%'  ");
						querry.append(" and sxddsy_xf>=1 and whys_xf>=1 and zyfw_xf>=1 and kxjs_xf>=0.5 and shstgz_xf>=0.5  order by xh");
					}else{
						querry.append(" and (xn like '%'||(nj+1)||'%'||(nj+2)||'%'   or xn is null ) ");						
						querry.append(" and ((sxddsy_xf<1 or sxddsy_xf is null) or (whys_xf<1 or whys_xf is null) or (zyfw_xf<1 or zyfw_xf is null) or (kxjs_xf<0.5 or kxjs_xf is null) or (shstgz_xf<0.5 or shstgz_xf is null)) order by xh");
					}
					sql = "select a.* from "+tableName+" a "+querry;
				}else{//两年内
					StringBuffer querry2 = new StringBuffer(" where 1=1 ");
					if("yes".equalsIgnoreCase(yesNo)){//在前两年内,在思想政治与道德素养、社会实践与志愿服务、文化艺术与身心发展等3个方面分别获得2个以上学分,在科技学术与创新创业、社团活动与社会工作等2个方面分别获得1个以上学分，达到学分制的有关要求；
						querry.append(" and (xn like '%'||(nj+1)||'%'||(nj+2)||'%'   or xn like nj||'%') ");
						querry2.append(" and sxddsy_xf>=2 and whys_xf>=2 and zyfw_xf>=2 and kxjs_xf>=1 and shstgz_xf>=1  ");				
					}else{
						querry.append(" and (xn like '%'||(nj+1)||'%'||(nj+2)||'%'  or xn like nj||'%' or xn is null)  ");
						querry2.append(" and ((sxddsy_xf < 2 or  sxddsy_xf is null) or (whys_xf < 2 or  whys_xf is null) or (zyfw_xf < 2 or zyfw_xf is null) or (kxjs_xf < 1 or  kxjs_xf is null) or (shstgz_xf < 1 or shstgz_xf is null)) order by xh ");										
					}				
					sql = "select  xh,xm,bjmc," +
					"case when sxddsy_xf<1 then '0'||sxddsy_xf else to_char(sxddsy_xf) end  sxddsy_xf," +
					"case when zyfw_xf<1 then '0'||zyfw_xf else to_char(zyfw_xf) end  zyfw_xf," +
					"case when kxjs_xf<1 then '0'||kxjs_xf else to_char(kxjs_xf) end  kxjs_xf," +
					"case when whys_xf<1 then '0'||whys_xf else to_char(whys_xf) end whys_xf," +
					"case when shstgz_xf<1 then '0'||shstgz_xf else to_char(shstgz_xf) end shstgz_xf," +
					"case when jnpx_xf<1 then '0'||jnpx_xf else to_char(jnpx_xf) end jnpx_xf," +
					"case when sumxf<1 then '0'||sumxf else to_char(sumxf) end sumxf " +
					" from (select xh,xm,bjmc,sum(sxddsy_xf)sxddsy_xf,sum(zyfw_xf)zyfw_xf,sum(kxjs_xf)kxjs_xf,";
					sql +="sum(whys_xf)whys_xf,sum(shstgz_xf)shstgz_xf,sum(jnpx_xf)jnpx_xf,sum(sumxf)sumxf from (";
					sql += " select * from "+tableName +querry;
					sql +=" )group by xh,xm,bjmc)a  "+querry2;					
				}
				colList = new String[] { "xh", "xm", "bjmc", "sxddsy_xf", "zyfw_xf", "kxjs_xf",
						"whys_xf", "shstgz_xf", "jnpx_xf", "sumxf" };
				colListCN = new String[] {  "学号", "姓名", "班级", "思想道德素养",
						"志愿服务", "科技学术", "文化艺术", "社团社会工作", "技能培训学分", "合计" };
				bbymType = "h";
			}
		}

		topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			System.out.println(sql);
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		if(Base.isNull(xn)&&Base.isNull(xq)){
			clue = "历年"+clue;
		}
		// 分页查询记录总数
		myForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs(sql1, new String[] {}, "count")));
		getListxx(request, dao, xydm, zydm, nj);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rs", rs);
		request.setAttribute("clue", clue);
		request.setAttribute("sfXsPage", sfXsPage);
		if (printDo != null && printDo.equalsIgnoreCase("do")) {// 报表打印
			String printTitle = "大学生素质拓展学分报表";
			if(Globals.XXDM_LZZYJSXY.equalsIgnoreCase(xxdm)){
				printTitle = "大学生第二课堂活动分报表";
			};
			request.setAttribute("printTitle", printTitle);
			request.setAttribute("xn", xn);
			request.setAttribute("xq", xq);
			String title = "";
			if (!bbymType.equalsIgnoreCase("")) {
				if(Base.isNull(xn)&&Base.isNull(xq)){
					title += "历年";
				}else{
					title = Base.isNull(xn)?"":xn+"学年";
					title += Base.isNull(xq)?"":"第"+xq+"学期";
				}
				if (bbymType.equalsIgnoreCase("a")) {
					title += "大于" + myForm.getDyfvalue() + "分学生名单";
				} else if (bbymType.equalsIgnoreCase("b")) {
					title += "小于" + myForm.getXyvalue() + "分学生名单";
				} else if (bbymType.equalsIgnoreCase("c")) {
					title += "介于" + myForm.getFsdvalues() + "到"
					+ myForm.getFsdvaluee() + "分学生名单";
				} else if (bbymType.equalsIgnoreCase("d")) {
					title += "排列前" + myForm.getZgsvalue() + "名学生名单";
				} else if (bbymType.equalsIgnoreCase("e")) {
					title += "(院)系平均分报表";
				} else if (bbymType.equalsIgnoreCase("f")) {
					title += "班级平均分报表";
				} else if (bbymType.equalsIgnoreCase("g")) {
					String bjmc = dao.getOneRs("select bjmc from view_njxyzybj where bjdm=?",
							new String[] { myForm.getBjdm() }, "bjmc");
					title = DealString.toString(bjmc) + " 统计报表";
					String Maxbjxf = dao.getOneRs("select max(to_number(xf)) maxxf from view_tzcgcjxx"
							+ querry, new String[] {}, "maxxf");
					String Avgbjxf = dao.getOneRs("select to_char(avg(to_number(xf)),'999.99') avgxf from view_tzcgcjxx"
							+ querry, new String[] {}, "avgxf");
					request.setAttribute("maxXf", (Base.isNull(Maxbjxf)) ? "0": Maxbjxf);
					request.setAttribute("avgxf", (Base.isNull(Avgbjxf) ? "0" : Avgbjxf));
				}else if("h".equalsIgnoreCase(bbymType)){	
					String title1="";
					if(Base.isNull(nj)&&Base.isNull(xydm)
							&&Base.isNull(zydm)
							&&Base.isNull(bjdm)){
						title +="全校在校生";
					}else{
						title1 +=Base.isNull(nj)?"":"年级："+nj+" ";
						title1 +=Base.isNull(xydm)?"":"院系："+dao.getOneRs("select xymc from  view_njxyzybj where xydm=? and rownum=1 ",new String[]{xydm},"xymc")+" ";
						title1 +=Base.isNull(zydm)?"":"专业："+dao.getOneRs("select zymc from  view_njxyzybj where zydm=? and rownum=1 ",new String[]{zydm},"zymc")+" ";
						title1 +=Base.isNull(bjdm)?"":"班级："+dao.getOneRs("select bjmc from  view_njxyzybj where bjdm=? and rownum=1 ",new String[]{bjdm},"bjmc")+" ";
					}
					title +=("dyn".equalsIgnoreCase(nd))?"第一年内":"";
					title +=("den".equalsIgnoreCase(nd))?"第二年内":"";
					title +=("ln".equalsIgnoreCase(nd))?"两年内":"";
					title +="yes".equalsIgnoreCase(yesNo)?"达到毕业要求学生列表":"未达到毕业要求学生列表";
					request.setAttribute("title1", title1);
				}
				request.setAttribute("title", title);
				return mapping.findForward("fsdcx_b_print");
			}
		}
		request.setAttribute("cxcz",cxcz);
		request.setAttribute("xxdm",xxdm);
		request.setAttribute("tips",tips);
		return mapping.findForward("tzxf_cxtj");
	}

	// 获取列表信息
	private static void getListxx(HttpServletRequest request, DAO dao,
			String xydm, String zydm, String nj) {
		xydm = xydm == null ? "" : xydm;
		zydm = zydm == null ? "" : zydm;
		nj = nj == null ? "" : nj;
//		String bjKey = xydm + "!!" + zydm + "!!" + nj;
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xqList", Base.getXqList());// 发送学期列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", dao.getZyList(xydm));// 发送专业列表
		request.setAttribute("bjList", dao.getBjList(xydm, zydm));// 发送班级列表
	}

	/** 长沙民政个人成果统计查询 */
	public ActionForward grcg_cxtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		SztzForm dsForm = (SztzForm) form;
		HttpSession session = request.getSession();
		StringBuffer querry = new StringBuffer();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		String tableName = "view_tzcgcjxx";
		String pk = "xh";
		String nj = dsForm.getNj();
		String xydm = dsForm.getXydm();
		String zydm = dsForm.getZydm();
		String bjdm = dsForm.getBjdm();
		String xh = dsForm.getXh();
		String xm = DealString.toGBK(dsForm.getXm());
		dsForm.setXm(xm);
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String rsNum = "";
		String sql = "";
		String tips = "";
		if (userType.equalsIgnoreCase("xy")) {
			xydm = userDep;
			dsForm.setXydm(xydm);
		}
		querry.append(" where 1=1 ");
		querry.append(Base.isNull(xydm) ? "" : " and xydm='" + xydm + "' ");
		querry.append(Base.isNull(bjdm) ? "" : " and bjdm='" + bjdm + "'");
		querry.append(Base.isNull(nj) ? "" : " and nj='" + nj + "' ");
		querry.append(Base.isNull(zydm) ? "" : " and zydm='" + zydm + "' ");
		querry.append(Base.isNull(xh) ? "" : " and xh='" + xh + "' ");
		querry.append(Base.isNull(xm) ? "" : " and xm like '%" + xm + "%' ");
		sql = "select * from (select * from( select rownum 行号,"
			+ pk
			+ " 主键,rownum r ,(select count(*) from view_tzcgcjxx b where a.xh=b.xh)成果次数,a.* from "
			+ "(select distinct xh,xm,xb,xymc,zymc,bjmc from "
			+ tableName
			+ " a"
			+ querry.toString()
			+ ")a) where rownum<="
			+ (dsForm.getPages().getStart() + dsForm.getPages()
					.getPageSize()) + ") where r>"
					+ dsForm.getPages().getStart();
		// 分页
		dsForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(
						"select count(distinct xh) count from " + tableName
						+ " a" + querry.toString(), new String[] {},
				"count")));
		tips = "素质拓展 - 统计分析 - 个人成果查询统计";
		colList = new String[] { "行号", "主键", "xh", "xm", "xb", "xymc", "zymc",
				"bjmc", "成果次数" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if (request.getParameter("go") != null
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		getListxx(request, dao, xydm, zydm, nj);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);
		request.setAttribute("tips", tips);
		request.setAttribute("userType", userType);
		return mapping.findForward("grcgtj_cx");
	}

	/** 长沙民政个人成果统计 */
	public ActionForward grcgTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		String xh = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = new String[] { "hh", "xmmc", "zbsj", "jxm", "cyjs",
				"xmjb", "xf" };
		String sql = "select rownum hh,xmmc,zbsj,jxm,cyjs,xmjb,xf from view_tzcgcjxx where xh=? and kmmc like ? ";
		List<HashMap<String, String>> szList = dao.getList(sql, new String[] {
				xh, "%思想政治%" }, colList);
		List<HashMap<String, String>> shsjList = dao.getList(sql, new String[] {
				xh, "%社会实践%" }, colList);
		List<HashMap<String, String>> kjcxList = dao.getList(sql, new String[] {
				xh, "%科技创新%" }, colList);
		List<HashMap<String, String>> whsxList = dao.getList(sql, new String[] {
				xh, "%文化艺术%" }, colList);
		List<HashMap<String, String>> sthdList = dao.getList(sql, new String[] {
				xh, "%社团活动%" }, colList);
		List<HashMap<String, String>> jnpxList = dao.getList(sql, new String[] {
				xh, "%技能培训%" }, colList);
		map = dao.getMap("select xymc,bjmc from view_xsjbxx where xh=? ",
				new String[] { xh }, new String[] { "xymc", "bjmc" });
		map.put("xh", xh);
		map.put("xn", Base.currXn);
		map.put("xq", Base.currXq);
		request.setAttribute("rs", map);
		request.setAttribute("szList", szList);
		request.setAttribute("shsjList", shsjList);
		request.setAttribute("kjcxList", kjcxList);
		request.setAttribute("whsxList", whsxList);
		request.setAttribute("sthdList", sthdList);
		request.setAttribute("jnpxList", jnpxList);
		return mapping.findForward("grcgxfsbb");
	}

	/** 成果认证审核 url:csmz_sztz.do?method=tzxf_rzhz*/
	public ActionForward tzxf_rzhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		SztzDao myDao = new SztzDao();
		HttpSession session = request.getSession();
//		String tableName = "view_tzcgrzxx";
		SztzForm sztzForm = (SztzForm) form;
		String nj = sztzForm.getNj();		
		String xydm = sztzForm.getXydm();
		String zydm = sztzForm.getZydm();
		String bjdm = sztzForm.getBjdm();
		String xh = DealString.toGBK(sztzForm.getXh());
		String xm = DealString.toGBK(sztzForm.getXm());
		sztzForm.setXm(xm);
		sztzForm.setXh(xh);
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userOnLine").toString();
		String userName = session.getAttribute("userName").toString();
		String userTypeT = dao.getUserType(userDep);
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String rsNum = "";
		StringBuffer sql = new StringBuffer();
		//String tips = "";
		List topTr = null;
		StringBuffer querry = new StringBuffer(" where 1=1 "); 
		if(userType.equalsIgnoreCase("teacher")){
			userType = userTypeT;
			if (userType.equalsIgnoreCase("xy")) {			
				xydm = userDep;
				sztzForm.setXydm(xydm);
				querry.append(" and fdysh='通过' ");
			}else if(userType.equalsIgnoreCase("xx")) {
				querry.append(" and fdysh='通过' and xysh='通过' ");

			}		
		}else{
//			有操作权限的学生干部登录
			String userGB = myDao.getBgbPower(userName);
			if (userGB.equalsIgnoreCase("0")) {
				request.setAttribute("errMsg", "您无权访问该页面！");
				return new ActionForward("/errMsg.do", false);
			} else {
				String[] temV = dao.getOneRs("select xydm,nj,zydm,bjdm from view_xsjbxx where xh=?",
						new String[] { userName }, new String[] { "xydm", "nj","zydm", "bjdm" });
				if (temV != null) {
					xydm = (temV[0] == null || temV[0].equalsIgnoreCase("") ? "": temV[0]);
					sztzForm.setXydm(xydm);					
					nj = (temV[1] == null || temV[1].equalsIgnoreCase("") ? "": temV[1]);
					zydm = (temV[2] == null || temV[2].equalsIgnoreCase("") ? "": temV[2]);
					bjdm = (temV[3] == null || temV[3].equalsIgnoreCase("") ? "": temV[3]);
					sztzForm.setNj(nj);
					sztzForm.setZydm(zydm);
					sztzForm.setBjdm(bjdm);									
				}
			}
		}				
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			colList = new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","zxf","fdysh","xysh","xxsh"};
			colListCN = new String[]{"学号","姓名","性别","年级",Base.YXPZXY_KEY,"专业","班级","素质总分","班级审核","院系审核","学校审核"};
			//querry.append("  ");
			querry.append(Base.isNull(nj) ? "" : " and nj='" + nj + "' ");
			querry.append(Base.isNull(xydm) ? "" : " and xydm='" + xydm + "'");
			querry.append(Base.isNull(bjdm) ? "" : " and bjdm='" + bjdm + "' ");
			querry.append(Base.isNull(zydm) ? "" : " and zydm='" + zydm + "' ");
			querry.append(Base.isNull(xh) ? "" : " and xh='" + xh + "' ");
			querry.append(Base.isNull(xm) ? "" : " and xm like '%" + xm + "%' ");					
			topTr = dao.arrayToList(colList, colListCN);
			sql.append("select distinct xh,xm,xb,nj,xymc,zymc,bjmc,(case when zxf<1 then '0'||to_char(ltrim(trim (zxf),'0')) else trim(zxf) end )zxf,fdysh,xysh,xxsh from view_tzcgrzxx "+querry);		
			rs.addAll(dao.rsToVator(sql.toString(), new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		getListxx(request, dao, xydm, zydm, nj);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);
		request.setAttribute("userType",userType);
		return mapping.findForward("tzxf_rzhz");
	}
	public ActionForward cgrzsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();		
		HttpSession session  = request.getSession();
		String xh = request.getParameter("xh").trim();
		String nj = request.getParameter("nj").trim();
		String xm = DealString.toGBK(request.getParameter("xm"));
		String xy = DealString.toGBK(request.getParameter("xy"));
		String zy = DealString.toGBK(request.getParameter("zy"));
		String bj = DealString.toGBK(request.getParameter("bj"));
		//String doType = DealString.toString(request.getParameter("doType"));
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userOnLine").toString();
		String[] colList = null;
		String sql = "";
		String shType = "";//审核类型
		String fieldSql = "";
		String userTypeT = dao.getUserType(userDep);
		HashMap<String, String> rsa = new HashMap<String, String>();
		if(userType.equalsIgnoreCase("teacher")) {//老师审核
			if (userTypeT.equalsIgnoreCase("xx")) {
				userType = userTypeT;
				shType = "学校";
				fieldSql =  " xxsh ";
			} else if (userTypeT.equalsIgnoreCase("xy")) {
				userType = userTypeT;
				shType = "院系";
				fieldSql = " xysh ";
			} 			
		}else {//班级干部审核
			shType = "班级";
			fieldSql = " fdysh ";
		}
		rsa = dao.getMap(" select "+fieldSql+" yesNo from csmz_cgrzb where xh=?",new String[]{xh},new String[]{"yesNo"});
		Vector<Object> rs = new Vector<Object>();
		String kmdm[]  = dao.getRs("select kmdm from sztz_kmdmb order by kmdm", new String[]{},"kmdm");
		int xmNum = 0;
		for(int i=0;i<kmdm.length;i++){	
			HashMap<String, Object> map = new HashMap<String, Object>();

			sql="select kmmc,xmmc,xmjb,xn,xq,xf,jxm,cyjs from view_tzcgcjxx where xh =?  and kmdm=? order by xn,xq,xmjb ";
			colList=new String[]{"xmmc","xmjb","xn","xq","jxm","xf","cyjs"};

			List xmList = dao.getList(sql, new String[] { xh, kmdm[i]}, colList);
			map.put("xmList", xmList);
			map.put("kmList", dao.getList("select kmm from sztz_kmdmb where kmdm='"+kmdm[i]+"'", new String[] {}, new String[]{"kmm"}));                	       	
			rs.add(map);
			map.put("kmzf", dao.getOneRs("select (case when nvl(sum(xf),'0')<1 then '0'||to_char(ltrim(trim (nvl(sum(xf),'0')),'0')) else trim(nvl(sum(xf),'0')) end ) kmzf from view_tzcgcjxx where xh=? and kmdm=?", new String[]{xh,kmdm[i]}, "kmzf"));

			xmNum += xmList.size();
		}	
		request.setAttribute("rsa", rsa);
		request.setAttribute("shType", shType);
		request.setAttribute("rs",rs);//发送数据集
		request.setAttribute("xmNum",xmNum);
		request.setAttribute("xh", xh);
		request.setAttribute("nj", nj);
		request.setAttribute("xm", xm);
		request.setAttribute("xy", xy);
		request.setAttribute("zy", zy);
		request.setAttribute("bj", bj);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("userType", userType);
		return mapping.findForward("cgrzsh");
	}
	public ActionForward cgrzshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String yesNo = DealString.toGBK(request.getParameter("yesNo"));
		DAO dao = DAO.getInstance();
		HttpSession session  = request.getSession();	
		String xh       = request.getParameter("xh");
		String nj = request.getParameter("nj").trim();
		String xm = DealString.toGBK(request.getParameter("xm"));
		String xy = DealString.toGBK(request.getParameter("xy"));
		String zy = DealString.toGBK(request.getParameter("zy"));
		String bj = DealString.toGBK(request.getParameter("bj"));
		String userDep  = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userOnLine").toString();
		String fieldSql = "";
		String realTable = "csmz_cgrzb";
		boolean done = false;
		if(userType.equalsIgnoreCase("teacher")) {//老师审核
			if (dao.getUserType(userDep).equalsIgnoreCase("xx")) {             
				fieldSql =  " xxsh ";
			} else if (dao.getUserType(userDep).equalsIgnoreCase("xy")) {				
				fieldSql = " xysh ";
			} 			
		}else {//班级干部审核			
			fieldSql = " fdysh ";
		}
		String isEixst = dao.returntag("select * from csmz_cgrzb where xh=?",new String[]{xh});
		if("notempty".equalsIgnoreCase(isEixst)){
			done = StandardOperation.update(realTable, new String[]{fieldSql}, new String[]{yesNo}, "xh",xh, request);			
		}else if("empty".equalsIgnoreCase(isEixst)){
			done = StandardOperation.insert(realTable,  new String[]{"xh",fieldSql}, new String[]{xh,yesNo},request);	
		}
		request.setAttribute("done",done);
		request.setAttribute("xh", xh);
		request.setAttribute("nj", nj);
		request.setAttribute("xm", xm);
		request.setAttribute("xy", xy);
		request.setAttribute("zy", zy);
		request.setAttribute("bj", bj);
		return new ActionForward("/csmz_sztz.do?method=cgrzsh",false);
	}
	public ActionForward tzcgrz_jlhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();		
		HttpSession session = request.getSession();

		SztzForm sztzForm = (SztzForm) form;
		String nj = sztzForm.getNj();		
		String xydm = sztzForm.getXydm();
		String zydm = sztzForm.getZydm();
		String bjdm = sztzForm.getBjdm();
		String xn   = sztzForm.getXn();
		String xq   = sztzForm.getXq();
		String xh = DealString.toGBK(sztzForm.getXh());
		String xm = DealString.toGBK(sztzForm.getXm());
		sztzForm.setXm(xm);
		sztzForm.setXh(xh);
		if(xn==null){
			sztzForm.setXn(Base.currXn);
		}
		if(xq==null){
			sztzForm.setXq(Base.currXq);
		}
		String userDep = session.getAttribute("userDep").toString();		
		String userType = dao.getUserType(userDep);
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String rsNum = "";
		StringBuffer sql = new StringBuffer();

		List topTr = null;
		StringBuffer querry = new StringBuffer(" where 1=1 "); 
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			colList = new String[]{"pk","xh","xm","xb","nj","xymc","zymc","bjmc","zxf"};
			colListCN = new String[]{"pk","学号","姓名","性别","年级",Base.YXPZXY_KEY,"专业","班级","素质总分"};
			//querry.append("  ");
			querry.append(Base.isNull(nj) ? "" : " and nj='" + nj + "' ");
			querry.append(Base.isNull(xydm) ? "" : " and xydm='" + xydm + "'");
			querry.append(Base.isNull(bjdm) ? "" : " and bjdm='" + bjdm + "' ");
			querry.append(Base.isNull(zydm) ? "" : " and zydm='" + zydm + "' ");
			querry.append(Base.isNull(xh) ? "" : " and xh='" + xh.trim() + "' ");
			querry.append(Base.isNull(xm) ? "" : " and xm like '%" + xm.trim() + "%' ");

			topTr = dao.arrayToList(colList, colListCN);
			sql.append(" select xh pk,xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,(case when zxf<1 then '0'||to_char(ltrim(trim (zxf),'0')) else trim(zxf) end )zxf from (");	
			sql.append(" select distinct xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,(select sum(xf) zxf from  view_tzcgcjxx b "+querry+" and a.xh=b.xh )zxf  ");
			sql.append(" from view_tzcgcjxx a "+querry+" )");

			rs.addAll(dao.rsToVator(sql.toString(), new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		getListxx(request, dao, xydm, zydm, nj);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);
		request.setAttribute("userType",userType);
		return mapping.findForward("tzcgrz_jlhz");
	}
	public  ActionForward cgjlhzb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DAO dao    = DAO.getInstance();
		MdqrService mdqrService=new MdqrService();
//		ExcelMB excelMb = new ExcelMB();
		String pkValue = request.getParameter("pkValue");
		int row = 0;
		String xn = request.getParameter("xzxn");
		String xq = request.getParameter("xzxq");
		HashMap<String,String> map  = CommonQueryDAO.dao_getInfo("view_tzcgcjxx", null," where xh||xn||xq='"+pkValue+xn+xq+"'  and rownum=1  ");
		String xqmc = mdqrService.getXqmc(xq);
		xqmc=Base.isNull(xqmc)?"  ":xqmc;		
		
		String xh = map.get("xh");
		xh=Base.isNull(xh)?"    ":xh;
		String xm = map.get("xm");
		xm=Base.isNull(xm)?"    ":xm;
		String xy = map.get("xymc");
		xy=Base.isNull(xy)?"      ":xy;
		String bj = map.get("bjmc");
		bj=Base.isNull(bj)?"      ":bj;
		
		String xxmc =Base.xxmc;
		String sql = "";
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		WritableSheet ws = wwb.createSheet("大学生素质拓展成果记录表", 0);

		WritableCellFormat wcf = new WritableCellFormat(); // 构造单元格格式		
		wcf = getWcf(WritableFont.ARIAL,12,true,Alignment.LEFT,VerticalAlignment.CENTRE,true,Border.TOP);			

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式		
		wcf2 = getWcf(WritableFont.ARIAL,10,false,Alignment.LEFT,VerticalAlignment.CENTRE,true,Border.ALL);			

		WritableCellFormat wcf3 = new WritableCellFormat(); // 构造单元格格式		
		wcf3 = getWcf(WritableFont.ARIAL,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,Border.ALL);			

		WritableCellFormat wcf4 = new WritableCellFormat(); // 构造单元格格式		
		wcf4 = getWcf(WritableFont.ARIAL,10,false,Alignment.LEFT,VerticalAlignment.BOTTOM,true,Border.TOP);

		WritableCellFormat wcf5 = new WritableCellFormat(); // 构造单元格格式		
		wcf5 = getWcf(WritableFont.ARIAL,16,true,Alignment.CENTRE,VerticalAlignment.BOTTOM,true,Border.BOTTOM);			

		WritableCellFormat wcf6 = new WritableCellFormat(); // 构造单元格格式		
		wcf6 = getWcf(WritableFont.ARIAL,10,true,Alignment.LEFT,VerticalAlignment.TOP,true,Border.ALL);			

		//填充标题
		ws.mergeCells(0, 0, 8, 2);
		ws.setRowView(0, 650); // 设置指定行高
		ws.addCell(new Label(0, 0, xxmc+" "+xn+" 学年 "+xqmc+" 素质拓展记录表",wcf5)); // 添加有指定格式的单元格值	

//		ws.mergeCells(0, 2, 7, 2);	
//		ws.setRowView(2, 500); // 设置指定行高
//		ws.addCell(new Label(0,2,"",wcf2));

		ws.setRowView(3, 600); // 设置指定行高			
		ws.addCell(new Label(0,3,"系别",wcf2));			
		//ws.setColumnView(1,20);
		ws.mergeCells(1, 3, 2, 3);
		ws.addCell(new Label(1,3,xy,wcf2));

		ws.addCell(new Label(3,3,"班级",wcf2));		
		ws.setColumnView(4,bj.length()*2);
		ws.addCell(new Label(4,3,bj,wcf2));

		ws.addCell(new Label(5,3,"姓名",wcf2));		
		ws.setColumnView(6,xm.length()*2);
		ws.addCell(new Label(6,3,xm,wcf2));

		ws.addCell(new Label(7,3,"学号",wcf2));		
		ws.setColumnView(8,xh.length()+xh.length()/2);
		ws.addCell(new Label(8,3,xh,wcf2));
		String[] kmdm  = dao.getRs("select kmdm from sztz_kmdmb order by kmdm", new String[]{},"kmdm");	
		String[] kmmc  = dao.getRs("select kmm from sztz_kmdmb order by kmdm", new String[]{},"kmm");	
		String[] kmzf  = new String[kmdm.length]; 
		row=3;
		for(int i=0;i<kmdm.length;i++){	
			double xf = 0.0;
			row=row+1;
			ws.setRowView(row,450 );
			ws.mergeCells(0, row, 7, row);	
			ws.addCell(new Label(0,row,kmmc[i],wcf));//科目名填充
			row=row+1;
			ws.addCell(new Label(0,row,"序号",wcf2));
			ws.mergeCells(1, row, 3, row);	
			ws.addCell(new Label(1,row,"项目名称",wcf2));			
			ws.addCell(new Label(4,row,"时间",wcf2));					
			ws.addCell(new Label(5,row,"成果",wcf2));
			ws.addCell(new Label(6,row,"性质",wcf2));
			ws.addCell(new Label(7,row,"级别",wcf2));		
			ws.addCell(new Label(8,row,"认证学分",wcf2));	
			sql="select rownum r,xmmc,zbsj,jxm,cyjs,xmjb,xf from (select xmmc,zbsj,jxm,cyjs,xmjb,xf from view_tzcgcjxx where xh =?  and xn=? and xq=? and kmdm=? order by xn,xq,xmjb) ";
			String[] colList=new String[]{"r","xmmc","zbsj","jxm","cyjs","xmjb","xf"};
			List<HashMap<String, String>> xmList = dao.getList(sql, new String[] { xh,xn,xq, kmdm[i]}, colList);			
			xmList.add(new HashMap<String, String>());		
			for(int j=0;j<xmList.size();j++){
				row=row+1;
				ws.addCell(new Label(0,row,xmList.get(j).get("r"),wcf2));
				ws.mergeCells(1, row,3 , row);
				int rowH = Base.isNull(xmList.get(j).get("xmmc"))?0:xmList.get(j).get("xmmc").length();
				int curRowH = (rowH/14==0)?(2+1/2):rowH/14+2;				

				ws.setRowView(row,curRowH*250 );//根据项目名称内容设置项目名称单元格高度
				ws.addCell(new Label(1,row,xmList.get(j).get("xmmc"),wcf2));

				ws.addCell(new Label(4,row,xmList.get(j).get("zbsj"),wcf2));
				ws.addCell(new Label(5,row,xmList.get(j).get("jxm"),wcf2));
				ws.addCell(new Label(6,row,xmList.get(j).get("cyjs"),wcf2));
				ws.addCell(new Label(7,row,xmList.get(j).get("xmjb"),wcf2));
				ws.addCell(new Label(8,row,xmList.get(j).get("xf"),wcf2));
				if(j==xmList.size()){
					row=row+1;	
					ws.addCell(new Label(0,row," ",wcf4));					
					ws.addCell(new Label(1,row," ",wcf4));
					ws.mergeCells(1, row,3 , row);	
					ws.addCell(new Label(4,row," ",wcf4));
					ws.addCell(new Label(5,row," ",wcf4));
					ws.addCell(new Label(6,row," ",wcf4));
					ws.addCell(new Label(7,row," ",wcf4));
					ws.addCell(new Label(8,row," ",wcf4));
				}
				xf += Double.parseDouble(Base.isNull(xmList.get(j).get("xf"))?"0":xmList.get(j).get("xf"));
			}
			kmzf[i]=xf+"";
		}
		//ws.removeRow(row);
		row=row+1;
		ws.mergeCells(0,row,8 , row);
		ws.addCell(new Label(0,row,"",wcf4));
//		row=row+1;
//		ws.setRowView(row, 1000); // 设置指定行高	
//		ws.mergeCells(0,row,8 , row);

		String zjstr = "";
		double xfv =0.0;
		for(int i=0;i<kmmc.length;i++){
			zjstr+=kmmc[i]+"，";
			zjstr+=kmzf[i]+"分；";
			xfv +=Double.parseDouble(kmzf[i]);
		}
		zjstr = "总学分："+xfv+"分。 其中：";//+zjstr;
		row=row+1;
		ws.setRowView(row, 500); // 设置指定行高	
		ws.mergeCells(0,row,8 , row);
		ws.addCell(new Label(0,row,zjstr,wcf3));
		int rowV = 0;
		if(kmmc.length/3>0){
			rowV=kmmc.length/3+1;
		}
		int kmmcNum=0;
		for(int i=1;i<rowV+1;i++){
			row++;
			String kmmc1=(kmmcNum<=kmmc.length-1)?kmmc[kmmcNum]:"";
			String kmmc2=(kmmcNum<=kmmc.length-2)?kmmc[kmmcNum+1]:"";
			String kmmc3=(kmmcNum<=kmmc.length-3)?kmmc[kmmcNum+2]:"";
			String kmzf1=(kmmcNum<=kmmc.length-1)?kmzf[kmmcNum]:"";
			String kmzf2=(kmmcNum<=kmmc.length-2)?kmzf[kmmcNum+1]:"";
			String kmzf3=(kmmcNum<=kmmc.length-3)?kmzf[kmmcNum+2]:"";
			ws.mergeCells(0,row,1 , row);
			ws.addCell(new Label(0,row,kmmc1,wcf3));			
			ws.addCell(new Label(2,row,kmzf1,wcf3));
			ws.mergeCells(3,row,4 , row);
			ws.addCell(new Label(3,row,kmmc2,wcf3));
			ws.addCell(new Label(5,row,kmzf2,wcf3));
			ws.mergeCells(6,row,7 , row);
			ws.addCell(new Label(6,row,kmmc3,wcf3));
			ws.addCell(new Label(8,row,kmzf3,wcf3));
			kmmcNum+=3;
		}		
		row=row+1;
		char newline = 0x000A;
		ws.mergeCells(0,row,0 , row+12);
		ws.addCell(new Label(0,row,"认@证@意@见".replace('@', newline),wcf3));
		ws.mergeCells(1,row,4 , row+6);
		ws.addCell(new Label(1,row,"审核意见:@@@@班素质拓展认证小组（签字）：@    年  月  日".replace('@', newline),wcf6));
		ws.mergeCells(1,row+7,4 , row+12);
		ws.addCell(new Label(1,row+7,"审核意见:@@@@系素质拓展认证中心（盖章）@    年  月  日".replace('@', newline),wcf6));
		ws.mergeCells(5,row,8, row+6);
		ws.addCell(new Label(5,row,"审核意见:@@@@辅导员签字：@    年  月  日".replace('@', newline),wcf6));
		ws.mergeCells(5,row+7,8 , row+12);
		ws.addCell(new Label(5,row+7,"审核意见:@@@@院素质拓展认证中心（盖章）@    年  月  日".replace('@', newline),wcf6));		
		ExcelMethods.submitWritableWorkbookOperations(wwb);
		return null;
	}
	public ActionForward getBjxx(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		DAO dao = DAO.getInstance();
		SztzForm sztzForm = (SztzForm) form;
		String xydm = sztzForm.getXydm();
		String zydm = sztzForm.getZydm();
		String nj   = sztzForm.getNj();
		getListxx(request, dao, xydm, zydm, nj);
		return mapping.findForward("getBjxx");
	}

	public ActionForward cgrzhzb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		SztzForm sztzForm = (SztzForm) form;
		String bjdm = sztzForm.getBjdm();
		String xn   = sztzForm.getXn();
		String[] xybjnj   = new String[3];
		xybjnj   = dao.getOneRs("select distinct nj,xymc,bjmc from view_njxyzybj where bjdm=? and rownum=1  ", new String[]{bjdm},new String[]{"nj","xymc","bjmc"});
		String nj = Base.isNull(xybjnj[0])?"":xybjnj[0];
		String xymc = Base.isNull(xybjnj[1])?"":xybjnj[1];
		String bjmc = Base.isNull(xybjnj[2])?"":xybjnj[2];
		String xnNd = Base.isNull(xn)?nj+"-至今":xn+"学年度";
		String xnQuer =  Base.isNull(xn)?"%":xn;
		//String[] note = dao.getOneRs("", new String[]{},new String[]{});
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		WritableSheet ws = wwb.createSheet("素质拓展学分认证汇总表", 0);
//		标题格式		
		WritableCellFormat wcf = new WritableCellFormat();		
//		字体，字体大小18，字体是否粗体，左右居中，上下居于底部，单元格内容自动换行，不显示边框(如，左边，右边或周边等)
		wcf = getWcf(WritableFont.ARIAL,20,true,Alignment.CENTRE,VerticalAlignment.BOTTOM,true,Border.NONE);	

		WritableCellFormat wcf1 = new WritableCellFormat(); // 构造单元格格式		
		wcf1 = getWcf(WritableFont.ARIAL,13,false,Alignment.CENTRE,VerticalAlignment.BOTTOM,true,Border.NONE);			

		WritableCellFormat wcf2 = new WritableCellFormat(); // 构造单元格格式		
		wcf2 = getWcf(WritableFont.ARIAL,10,true,Alignment.CENTRE,VerticalAlignment.CENTRE,true,Border.ALL);		

//		内容字体，单元格格式	
		WritableCellFormat wcf3 = new WritableCellFormat(); // 构造单元格格式		
		wcf3 = getWcf(WritableFont.ARIAL,10,false,Alignment.CENTRE,VerticalAlignment.CENTRE,true,Border.ALL);
//		表尾字体，单元格格式			
		WritableCellFormat wcf4 = new WritableCellFormat(); // 构造单元格格式		
		wcf4 = getWcf(WritableFont.ARIAL,10,false,Alignment.LEFT,VerticalAlignment.CENTRE,true,Border.NONE);

		List<HashMap<String, String>> kmList = dao.getList("select kmdm,kmm from sztz_kmdmb order by kmdm", new String[]{},new String[]{"kmdm","kmm"});	
		int colCout = 6+kmList.size();//总字段项数等于固定值6+科目数（非固定值）
		for(int i=0;i<12;i++){//每列宽度
			ws.setColumnView(i, 7);
		}

//		填充标题
		ws.mergeCells(0, 0, colCout-1, 0);
		ws.setRowView(0, 650); // 设置指定行高
		ws.addCell(new Label(0, 0, xnNd+"素质拓展学分认证汇总表", wcf)); // 添加有指定格式的单元格值	
//		填充第二行
		ws.mergeCells(0, 1, colCout-1, 0);
		ws.setRowView(1, 450); // 设置指定行高
		ws.addCell(new Label(0, 1,xymc+"    "+bjmc+"   "+"   填表时间：       年    月    日 " , wcf1)); // 添加有指定格式的单元格值	
//		填充第二行
		ws.setRowView(2, 1500); // 设置指定行高		
		ws.addCell(new Label(0, 2,"学号", wcf2)); // 添加有指定格式的单元格值	
		ws.addCell(new Label(1, 2,"姓名", wcf2)); // 添加有指定格式的单元格值	
		int titColNum = 2;
		for(int i=0;i<kmList.size();i++){
			ws.addCell(new Label(titColNum, 2,kmList.get(i).get("kmm"), wcf2)); // 添加有指定格式的单元格值				
			titColNum++;
		}
		ws.addCell(new Label(titColNum, 2,"合计学分", wcf2)); // 添加有指定格式的单元格值	
		ws.addCell(new Label(++titColNum, 2,"班级认证中心意见", wcf2)); // 添加有指定格式的单元格值	
		ws.addCell(new Label(++titColNum, 2,"系级认证中心意见", wcf2)); // 添加有指定格式的单元格值	
		ws.addCell(new Label(++titColNum, 2,"院级认证中心意见", wcf2)); // 添加有指定格式的单元格值       
		int contRowNum = 2;//从第三行开始统计内容
		List<HashMap<String, String>>  xhList = dao.getList("select distinct xh,xm from view_tzcgcjxx where  bjdm=? and xn like ?  ",new String[]{bjdm,xnQuer},new String[]{"xh","xm"});
		for(int i=0;i<xhList.size();i++){
			++contRowNum;
			String xh = xhList.get(i).get("xh");
			String xm = xhList.get(i).get("xm");			
			ws.addCell(new Label(0, contRowNum,xh, wcf3)); // 添加有指定格式的单元格值
			ws.addCell(new Label(1, contRowNum,xm, wcf3));
			int kmCol = 1;
			double zxf = 0.0;
			for(int j=0;j<kmList.size();j++){
				++kmCol;
				String kmdm = kmList.get(j).get("kmdm");
				String sql = "select (case when sxf<1 then '0'||to_char(ltrim(trim ( sxf),'0')) else trim( sxf) end )sumxf from (select nvl(sum(xf),'0.00')sxf from view_tzcgcjxx where xh=? and kmdm=? and xn like ?) ";
				String sumxf = dao.getOneRs(sql,new String[]{xh,kmdm,xnQuer},"sumxf");
				ws.addCell(new Label(kmCol, contRowNum,sumxf, wcf3)); // 添加有指定格式的单元格值				
				zxf=zxf+Float.parseFloat(sumxf);
			}
			ws.addCell(new Label(++kmCol, contRowNum,new java.text.DecimalFormat("#,##0.00").format(zxf)+"", wcf3));
			ws.addCell(new Label(++kmCol, contRowNum,"", wcf3));
			ws.addCell(new Label(++kmCol, contRowNum,"", wcf3));
			ws.addCell(new Label(++kmCol, contRowNum,"", wcf3));
		}
//		填充表尾
		++contRowNum;
		ws.mergeCells(0, contRowNum, colCout-1, 0);
		ws.setRowView(contRowNum, 650); // 设置指定行高
		ws.addCell(new Label(0, contRowNum, "注：此表一式两份，经审核后院、系各一份", wcf4)); 
		++contRowNum;
		ws.mergeCells(0, contRowNum, colCout-1, 0);
		ws.addCell(new Label(0, contRowNum, "系素质拓展中心（签章）                      院大学生素质拓展中心（签章）", wcf4));
		ExcelMethods.submitWritableWorkbookOperations(wwb);
		return mapping.findForward("success");
	}

	/**
	 * 
	 * @param wf 字体格式对象
	 * @param fontSize 字体大小
	 * @param xStyle 单元格内容横向显示
	 * @param yStyle 单元格内容纵向显示
	 * @param newLine 是否自动换行
	 * @param borderShow 边框线是否显示
	 * @return 单元格格式对象
	 * @throws Exception 
	 */
	public WritableCellFormat getWcf(FontName name,int fontSize,boolean bold,Alignment xStyle,VerticalAlignment yStyle,boolean newLine,Border bordery) throws Exception{
		WritableFont wf = new WritableFont(name); // 构造字体格式
		WritableCellFormat wcf = new WritableCellFormat();		
		wf.setPointSize(fontSize);
		wcf.setFont(wf);
		wcf.setAlignment(xStyle); //指定格式设置字符左右显示位置(如：左右居中)
		wcf.setVerticalAlignment(yStyle); //指定格式设置字符上下显示位置（如：上下居中）				
		wcf.setWrap(newLine);
		if(bordery!=Border.NONE){
			wcf.setBorder(bordery, BorderLineStyle.THIN); //设置单元格外边线						
		}
		return wcf;
	}
	/**
	 * 
	 * @param 拓展项目申报添加
	 * @throws Exception 
	 * @throws Exception 
	 */
	public ActionForward tzxmAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		DAO dao = DAO.getInstance();
		List xmjbList = null;// 项目级别
		SztzDao sztzDao = new SztzDao();
		HttpSession session = request.getSession();
		SztzForm sztzForm = (SztzForm) form;
		String userType = session.getAttribute("userType").toString();
		HashMap<String, String> map = new HashMap<String, String>();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
//		String userNameReal = session.getAttribute("userNameReal").toString();
//		String userDepMc = (session.getAttribute("bmmc")==null)?"":session.getAttribute("bmmc").toString(); 
		String xh = "";
//		String pk = "xn||xq||xmmc||xmjb";
		String doType = DealString.toString(request.getParameter("doType"));
		String act = DealString.toString(request.getParameter("act"));
		String pkValue =DealString.toString(request.getParameter("pkValue"));
		String view = DealString.toString(request.getParameter("view"));
		String curDep = dao.getOneRs("select xgbdm from xtszb where rownum=1 ",new String[]{},"xgbdm");
		String xxdm = Base.xxdm;
		String xydm = sztzForm.getXydm();
		String sql = "";
		boolean done=false;
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String xmmc = DealString.toGBK(request.getParameter("xmmc"));
		String kmdm = request.getParameter("kmdm");
		String xmjb = DealString.toGBK(request.getParameter("xmjb"));
		String zbsj = request.getParameter("zbsj");
		String zzdw = DealString.toGBK(request.getParameter("zzdw"));
		String xmms = DealString.toGBK(request.getParameter("xmms"));
		String hddx = DealString.toGBK(request.getParameter("hddx"));
		String bjdm = DealString.toGBK(request.getParameter("bjdm"));
		String hdjhrs = DealString.toGBK(request.getParameter("hdjhrs"));
		String hdxs = DealString.toGBK(request.getParameter("hdxs"));
		String xmsbr = request.getParameter("xmsbr");
		String xmsbrv = request.getParameter("xmsbr1");
		xmsbr = Base.isNull(xmsbr)?xmsbrv:xmsbr;
		String tips = "素质拓展 - 拓展项目申报 - 结果查询 - 信息维护";
		String fsclin = "学分";
		if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){//长沙民政职业技术学院
			request.setAttribute("isCSMZ", "isCSMZ");
		}
		if(Globals.XXDM_LZZYJSXY.equalsIgnoreCase(xxdm)){//柳州职业技术学院学校代码 
			tips = "第二课堂活动 - 活动项目申报 - 结果查询 - 信息维护";
			fsclin = "活动分";
		}
		if(Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)){//北京联合大学
			fsclin = "得分";
		}
		request.setAttribute("fsclin",fsclin);
		if(Base.isNull(xmsbr)){
			xmsbr = userName;
		}
		if (!userDep.equalsIgnoreCase(curDep)) {//不属于学工部用户
			xydm = userDep;
			sztzForm.setXydm(userDep);
			userType = "stu".equalsIgnoreCase(userType)?userType:"xy";//非学生班干部用户设置为"xy"用户			
		}
		if("stu".equalsIgnoreCase(userType)){
			bjdm = dao.getOneRs("select bjdm from view_xsjbxx where xh=?",new String[]{userName},"bjdm");
			sztzForm.setBjdm(bjdm);
		}

		if("save".equalsIgnoreCase(act)){
			sql = "select * from csmz_tzxmb where xn=? and xq=? and xmmc=? and xmjb=? and xydm=? and kmdm=? and (xysh='通过' or xxsh='通过' )";
			String isExist = dao.returntag(sql, new String[] { xn, xq,xmmc,xmjb,xydm,kmdm });
			if (userType.equalsIgnoreCase("admin")||"empty".equalsIgnoreCase(isExist)) {// 通过审核的记录不存在时插入、修改或存在的记录只能由管理员进行修改
				if("add".equalsIgnoreCase(doType)){
					sql = "insert into csmz_tzxmb(id,xn,xq,xmmc,kmdm,xydm,zzdw,zbsj,xmjb,xmms,hddx,xmsbr,bjdm,fdysh,xysh,xxsh,hdjhrs,hdxs)values(S_CSMZ_TZXMB_ID.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,'通过','通过','通过',?,?)";
					done = dao.runUpdate(sql, new String[] { xn, xq, xmmc,
							kmdm, xydm, zzdw, zbsj, xmjb, xmms, hddx,xmsbr,bjdm,hdjhrs,hdxs });					
					if(done){
						sql = "delete from csmz_tzxmjxb where xmid in (select max(to_number(id)) from csmz_tzxmb)";
						done = dao.runUpdate(sql, new String[] {});	//删除相关奖项表
					}
				}else{
					sql = "update csmz_tzxmb set xn=?,xq=?,xmmc=?,kmdm=?,xydm=?,zzdw=?,zbsj=?,xmjb=?,xmms=?,hddx=?,xmsbr=?,bjdm=?,hdjhrs=?,hdxs=? where id=?";
					done = dao.runUpdate(sql, new String[] { xn, xq, xmmc,kmdm, xydm, zzdw, zbsj, xmjb, xmms, hddx,xmsbr,bjdm,hdjhrs,hdxs,pkValue });
					if(done){
						sql = "delete from csmz_tzxmjxb where xmid=? ";
						done = dao.runUpdate(sql, new String[] {pkValue});	//删除相关奖项表
					}
				}
				if (done) {	
					if (done) {
						for (int i = 1; i <= 15; i++) {
							String jxm = DealString.toGBK(DealString.toString(request.getParameter(("jxm" + i))));
							String jxnr = DealString.toGBK(DealString.toString(request.getParameter(("jxnr" + i))));
							if (jxm != "" || !jxm.equalsIgnoreCase("")) {
								if (doType.equalsIgnoreCase("modi")) {
									sql = "insert into csmz_tzxmjxb(xmid,jxid,jxm,xf)values(?,?,?,?)";
									dao.runUpdate(sql, new String[] { pkValue,
											String.valueOf(i), jxm, jxnr });
								} else {
									sql = "insert into csmz_tzxmjxb(xmid,jxid,jxm,xf)values((select max(to_number(id)) from csmz_tzxmb),?,?,?)";
									dao.runUpdate(sql, new String[] {
											String.valueOf(i), jxm, jxnr });
								}
							}
						}
					}
					request.setAttribute("done", "ok");				
				}
			}else{// 记录存在时不操作
				request.setAttribute("isExist", "isExist");
			}
		}
		if(Base.isNull(pkValue)){
			map.put("xn",xn);
			map.put("xq",xq);
			map.put("xmmc",xmmc);
			map.put("kmdm",kmdm);
			map.put("xmjb",xmjb);
			map.put("xydm",xydm);
			map.put("zbsj",zbsj);
			map.put("zzdw",zzdw);
			map.put("hddx",hddx);
			map.put("bjdm",bjdm);
			map.put("xmsbr",xmsbr);
			map.put("xmsbr1",xmsbrv);
			map.put("xmms",xmms);
			map.put("hdjhrs",hdjhrs);
			map.put("hdxs",hdxs);
			sql = "select jxm,xf from csmz_tzxmjxb  where xmid=(select id from csmz_tzxmb where xn=? and xq=? and xmmc=? and xmjb=? and rownum=1) order by jxid";
			String jxTem = dao.getStringToSplit(sql, new String[] { xn, xq, xmmc, xmjb },
					new String[] { "jxm", "xf" });
			if (jxTem != "") {
				String[] record = null;
				String[] tmp = jxTem.split("!!SplitSignOne!!");
				if (tmp.length >= 2) {
					for (int i = 1; i < tmp.length; i++) {
						record = tmp[i].split("!!SplitSignTwo!!");
						map.put("jxm" + i, (record[1] == null || ""
								.equalsIgnoreCase(record[1])) ? "" : record[1]);
						map.put("jxnr" + i, (record[2] == null || ""
								.equalsIgnoreCase(record[2])) ? "" : record[2]);
					}
				}
			}
		}else{
			sql = "select * from(select a.id,xn,xq,xmmc,kmdm,xydm,zzdw,zbsj,xmjb,xmms,bjdm,(select distinct xqmc from xqdzb b where a.xq=b.xqdm)xqmc,hddx,xmsbr," 
				+" (select distinct xm from yhb b where a.xmsbr=b.yhm)xmsbrmc,"
				+" (select distinct bmmc from yhb b,zxbz_xxbmdm c where a.xmsbr=b.yhm and b.szbm=c.bmdm)szbmmc,hdjhrs,hdxs" 
				+" from csmz_tzxmb a) where id=?";
			map = dao.getMap(sql, new String[] { pkValue }, new String[] {
					"xn", "xq", "xmmc", "kmdm", "xydm", "zzdw", "zbsj", "xmjb",
					"xmms","bjdm","xqmc", "hddx","xmsbr","xmsbrmc","szbmmc","hdjhrs","hdxs"});
			sql = "select jxm,xf from csmz_tzxmjxb where xmid=? order by jxid";
			String jxTem = dao.getStringToSplit(sql, new String[] { pkValue },
					new String[] { "jxm", "xf" });
			if (jxTem != "") {
				String[] record = null;
				String[] tmp = jxTem.split("!!SplitSignOne!!");
				if (tmp.length >= 2) {
					for (int i = 1; i < tmp.length; i++) {
						record = tmp[i].split("!!SplitSignTwo!!");
						map.put("jxm" + i, (record[1] == null || ""
								.equalsIgnoreCase(record[1])) ? "" : record[1]);
						map.put("jxnr" + i, (record[2] == null || ""
								.equalsIgnoreCase(record[2])) ? "" : record[2]);
					}
				}
			}
			if(!"班级".equalsIgnoreCase(map.get("xmjb"))){
				map.put("xmsbr1",map.get("xmsbr"));//设置KEY为"xmsbr1"对应页面相应标志
			}else{
				xydm = map.get("xydm");
				bjdm = map.get("bjdm");			   
			}
		}
		Vector<Object> rs = new Vector<Object>();
		@SuppressWarnings("unused")
		String rsNum = "0";
		if("toview".equalsIgnoreCase(view)){//查看该项目学生成果申报情况
			StringBuffer sqls = new StringBuffer();
			sqls.append("select a.*,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc ");
			sqls.append("from (select * from ( select xh,xmid, ");
			sqls.append("(select jxm from csmz_tzxmjxb c where c.xmid=a.xmid and c.jxid=a.jxlb)jxm, ");
			sqls.append("(select xf from csmz_tzxmjxb c where c.xmid=a.xmid and c.jxid=a.jxlb)xf, ");
			sqls.append("cyjs,sfdy,xxsh from csmz_tzcgb a ) ");
			sqls.append("union (select * from ( select xh,xmid, ");
			sqls.append("(select jxm from csmz_tzxmjxb c where c.xmid=a.xmid and c.jxid=a.jxlb)jxm, ");
			sqls.append("(select xf from csmz_tzxmjxb c where c.xmid=a.xmid and c.jxid=a.jxlb)xf, ");
			sqls.append(" cyjs,sfdy,'通过' xxsh from csmz_tzcjb a)) ");
			sqls.append(")a left join view_xsjbxx b on a.xh=b.xh  ");
			sqls.append(" where xmid=? ");
			rs.addAll(dao.rsToVator(sqls.toString(), new String[] {pkValue}, new String[]{"xh","xm","xb","nj","xymc","bjmc","jxm","xf","xxsh"}));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		xmjbList = initTzXmJbList(xxdm,"");
//		if(Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)){
//		xmjbList = sztzDao.getTzXmJbList(6);
//		}else if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){			
//		xmjbList = sztzDao.getTzXmJbList(5);	
//		}else{
//		xmjbList = sztzDao.getTzXmJbList(4);	
//		}
		List kmList = dao.getList("select kmdm,kmm from sztz_kmdmb",
				new String[] {}, new String[] { "kmdm", "kmm" });
		request.setAttribute("rs",Base.getBjMap());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());		
		request.setAttribute("bmList",  dao.getList("select distinct bmdm,bmmc from ZXBZ_XXBMDM order by bmmc", new String[]{},new String[]{"bmdm", "bmmc"}));
		request.setAttribute("kmList", kmList);
		request.setAttribute("xmjbList", xmjbList);
		request.setAttribute("bjList",sztzDao.getTzxmSbList(xh,xydm));
		request.setAttribute("yhList",sztzDao.getYhList(xydm));
		request.setAttribute("xsList", sztzDao.getXsList(bjdm));
		request.setAttribute("doType",doType);
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("userType",userType);
		request.setAttribute("view",view);
		request.setAttribute("rs",map);
		request.setAttribute("res", rs);
		request.setAttribute("xxdm",xxdm);
		request.setAttribute("tips",tips);
		return mapping.findForward("tzxmAdd");
	}
	public ActionForward tzcg_adjust(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DAO dao = DAO.getInstance();
//		SztzDao myDao = new SztzDao();
		HttpSession session = request.getSession();
//		String tableName = "view_tzcgrzxx";
		SztzForm sztzForm = (SztzForm) form;
		String nj = sztzForm.getNj();		
		String xydm = sztzForm.getXydm();
		String zydm = sztzForm.getZydm();
		String bjdm = sztzForm.getBjdm();
		String xh = DealString.toGBK(sztzForm.getXh());
		String xm = DealString.toGBK(sztzForm.getXm());
		sztzForm.setXm(xm);
		sztzForm.setXh(xh);
		String userDep = session.getAttribute("userDep").toString();
		String userTypeT = session.getAttribute("userOnLine").toString();
		String userName = session.getAttribute("userName").toString();
		String userType = dao.getUserType(userDep);
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String rsNum = "";
		StringBuffer sql = new StringBuffer();
		//String tips = "";
		List topTr = null;
		StringBuffer querry = new StringBuffer(" where 1=1 "); 
		if(userTypeT.equalsIgnoreCase("student")){			
			String[] temV = dao.getOneRs("select xydm,nj,zydm,bjdm,xh,xm from view_xsjbxx where xh=?",
					new String[] { userName }, new String[] { "xydm", "nj","zydm", "bjdm" ,"xh","xm"});
			if (temV != null) {
				xydm = (temV[0] == null || temV[0].equalsIgnoreCase("") ? "": temV[0]);								
				nj = (temV[1] == null || temV[1].equalsIgnoreCase("") ? "": temV[1]);
				zydm = (temV[2] == null || temV[2].equalsIgnoreCase("") ? "": temV[2]);
				bjdm = (temV[3] == null || temV[3].equalsIgnoreCase("") ? "": temV[3]);
				xh = (temV[4] == null || temV[4].equalsIgnoreCase("") ? "": temV[4]);
				xm = (temV[5] == null || temV[5].equalsIgnoreCase("") ? "": temV[5]);
				sztzForm.setXydm(xydm);	
				sztzForm.setNj(nj);
				sztzForm.setZydm(zydm);
				sztzForm.setBjdm(bjdm);
				sztzForm.setNj(nj);
				sztzForm.setXh(xh);
				sztzForm.setXm(xm);
				userType = userTypeT;
			}
		}else if("xy".equalsIgnoreCase(userType)){
			xydm = userDep;
			sztzForm.setXydm(xydm);	
		}
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			colList = new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","cout"};
			colListCN = new String[]{"学号","姓名","性别","年级",Base.YXPZXY_KEY,"专业","班级","打印成果总数"};
			//querry.append("  ");
			querry.append(Base.isNull(nj) ? "" : " and nj='" + nj + "' ");
			querry.append(Base.isNull(xydm) ? "" : " and xydm='" + xydm + "'");
			querry.append(Base.isNull(bjdm) ? "" : " and bjdm='" + bjdm + "' ");
			querry.append(Base.isNull(zydm) ? "" : " and zydm='" + zydm + "' ");
			querry.append(Base.isNull(xh) ? "" : " and xh='" + xh + "' ");
			querry.append(Base.isNull(xm) ? "" : " and xm like '%" + xm + "%' ");					
			topTr = dao.arrayToList(colList, colListCN);
//			sql.append("select cout,a.xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc "); 
//			sql.append("from (select xh,count(xh)cout from ( select xh,xmid, "); 
//			sql.append("(select xn from csmz_tzxmb c where c.id=a.xmid)xn, "); 
//			sql.append("(select xq from csmz_tzxmb c where c.id=a.xmid)xq, "); 
//			sql.append("(select kmdm from csmz_tzxmb c where c.id=a.xmid)kmdm, "); 
//			sql.append("(select xmjb from csmz_tzxmb c where c.id=a.xmid)xmjb, "); 
//			sql.append("cyjs,sfdy from csmz_tzcgb a where xxsh<>'通过' "); 
//			sql.append("union ( "); 
//			sql.append("select xh,xmid,(select xn from csmz_tzxmb c where c.id=a.xmid)xn, "); 
//			sql.append("(select xq from csmz_tzxmb c where c.id=a.xmid)xq, "); 
//			sql.append("(select kmdm from csmz_tzxmb c where c.id=a.xmid)kmdm, "); 
//			sql.append("(select xmjb from csmz_tzxmb c where c.id=a.xmid)xmjb, "); 
//			sql.append("cyjs,sfdy from csmz_tzcjb a)) where sfdy='是' group by xh "); 
//			sql.append(")a left join view_xsjbxx b on a.xh=b.xh	 ");
			sql.append(" select * from view_cgtzquerr ");
			rs.addAll(dao.rsToVator(sql+querry.toString(), new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		getListxx(request, dao, xydm, zydm, nj);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);
		request.setAttribute("userType",userType);
		return mapping.findForward("tzcg_adjust");
	}
	public ActionForward tzcgAjs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String[] pksArray = request.getParameterValues("pksAll");
		String[] sfdyArray = request.getParameterValues("sfdy");
		DAO dao = DAO.getInstance();		
		HttpSession session  = request.getSession();
		String xh = request.getParameter("xh").trim();
		String nj = request.getParameter("nj").trim();
		String xm = DealString.toGBK(request.getParameter("xm"));
		String xy = DealString.toGBK(request.getParameter("xy"));
		String zy = DealString.toGBK(request.getParameter("zy"));
		String bj = DealString.toGBK(request.getParameter("bj"));
		//String doType = DealString.toString(request.getParameter("doType"));
		//String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userOnLine").toString();
		String act      = request.getParameter("act");
		String[] colList = null;
		String pkey = "xh||xmid||cyjs||jxlb";
		String sql = "";
		String shType = "";//审核类型

		if("save".equalsIgnoreCase(act)){//保存
			String pkStr = DealString.toGBK(request.getParameter("pkStr"));
			String pkVStr = DealString.toGBK(request.getParameter("pkVStr"));			
			String[]   pk = pksArray;//pkStr.split("!!");
			String[]   pkV = sfdyArray;//pkVStr.split("!!");
			
			StringBuffer sqlStr = new StringBuffer();
			if(pk!=null){
				for(int i=0;i<pk.length;i++){
					sql = "update csmz_tzcgb set sfdy='"+DealString.toGBK(pkV[i])+"' where "+pkey+"='"+DealString.toGBK(pk[i])+"' ";
					sqlStr.append(sql).append("!!");
					sql = "update csmz_tzcjb set sfdy='"+DealString.toGBK(pkV[i])+"' where "+pkey+"='"+DealString.toGBK(pk[i])+"' ";
					sqlStr.append(sql).append("!!");
				}
			}

			boolean doFlag = false;
			int[] array = dao.runBatch(sqlStr.toString().split("!!"));
			doFlag = dao.checkBatch(array); 	    
			request.setAttribute("done",doFlag);
		}

		Vector<Object> rs = new Vector<Object>();
		List<HashMap<String, String>>  kmdm  = dao.getList("select xn,kmdm from view_cgtz where xh=? group by xn,kmdm order by xn,kmdm", new String[]{xh},new String[]{"xn","kmdm"});
		int xmNum = 0;
		for(int i=0;i<kmdm.size();i++){	
			HashMap<String, Object> map = new HashMap<String, Object>();
			sql="select "+pkey+" pk,xmid,(select kmm from sztz_kmdmb b where a.kmdm=b.kmdm )kmmc," +
			"(select xmmc from csmz_tzxmb b where b.id=a.xmid)xmmc,xmjb,xn," +
			"(select xqmc from xqdzb b where b.xqdm=a.xq)xq,jxm,xf," +
			"cyjs,xxsh,sfdy from view_cgtz a where xh =?   and xn=? and kmdm=? order by xn,xq,xmjb ";
			colList=new String[]{"pk","xmid","xmmc","xmjb","xn","xq","jxm","xf","cyjs","xxsh","sfdy"};
			List xmList = dao.getList(sql, new String[] { xh, kmdm.get(i).get("xn"),kmdm.get(i).get("kmdm")}, colList);
			map.put("xmList", xmList);
			map.put("kmList", dao.getList("select  '"+kmdm.get(i).get("xn")+"'xn,kmm from sztz_kmdmb where kmdm='"+kmdm.get(i).get("kmdm")+"'", new String[] {}, new String[]{"xn","kmm"}));                	       	
			rs.add(map);
			xmNum += xmList.size();
		}			
		request.setAttribute("shType", shType);
		request.setAttribute("rs",rs);//发送数据集
		//request.setAttribute("rsa",rsa);//发送数据集
		request.setAttribute("xmNum",xmNum);
		request.setAttribute("xh", xh);
		request.setAttribute("nj", nj);
		request.setAttribute("xm", xm);
		request.setAttribute("xy", xy);
		request.setAttribute("zy", zy);
		request.setAttribute("bj", bj);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("userType", userType);
		return mapping.findForward("tzcgAjs");
	}
	
	public List<HashMap<String, String>> initTzXmJbList(String xxdm,String userType){
		SztzDao sztzDao = new SztzDao();
		List<HashMap<String, String>> xmjbList = null;
		if(Base.isNull(userType)){
			if(Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)){
				xmjbList = sztzDao.getTzXmJbList(6);
			}else if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){			
				xmjbList = sztzDao.getTzXmJbList(5);	
			}else{
				xmjbList = sztzDao.getTzXmJbList(4);	
			}
		}else{
			if(Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)){
				xmjbList = sztzDao.getTzXmJbList(6);
			}else if(Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)){
				if ("stu".equalsIgnoreCase(userType)) {// 学生用户申报
					xmjbList = sztzDao.getTzXmJbList(1);
				} else if ("xy".equalsIgnoreCase(userType)) {// 院系用户申报
					xmjbList = sztzDao.getTzXmJbList(4);
				} else if ("xx".equalsIgnoreCase(userType)) {// 学校用户申报
					xmjbList = sztzDao.getTzXmJbList(3);
				} else if ("admin".equalsIgnoreCase(userType)) {// 管理员
					xmjbList = sztzDao.getTzXmJbList(4);
				}
			}else{
				xmjbList = sztzDao.getTzXmJbList(4);
			}
		}
		return xmjbList;
	}
	/**
	 * 成果申报记录批量删除
	 */
	public ActionForward cgsbInfoDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkVStr = request.getParameter("pkVStr");
		DAO dao = DAO.getInstance();			
		String[] pkValueA = pkVStr.split("!!");		
		String[] updPkSql  = new String[pkValueA.length];		
		for (int i = 0; i < pkValueA.length; i++) {
			updPkSql[i] = Base.isNull(pkValueA[i])?"":"delete csmz_tzcgb where (xh||xmid||cyjs||jxlb )= '"+pkValueA[i]+"'  ";							
		}              
		int[] array = dao.runBatch(updPkSql);
		boolean flog=dao.checkBatch(array);   
		request.setAttribute("result", flog);
		return data_search(mapping, form, request, response);
	}
	/**
	 * 柳州职业第二活动项目申请表打印
	 */
	public ActionForward sqbPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String pkValue = request.getParameter("pkValue");
		request.setAttribute("rs",CommonQueryDAO.dao_getInfo("view_csmz_tzxmxx",null," where id='"+pkValue+"' "));
		return mapping.findForward("sqbPrint");
	}
	
	/**
	 * 柳州职业第二活动分记录汇总表打印
	 */
	public ActionForward hdxfhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		SztzForm sztzForm = (SztzForm) form;
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = null;
		String xn = sztzForm.getXn();
		String xq = sztzForm.getXq();
		String nj = sztzForm.getNj();
		String xh = sztzForm.getXh();
		String xm = sztzForm.getXm();
		String xydm = sztzForm.getXydm();
		String zydm = sztzForm.getZydm();
		String bjdm = sztzForm.getBjdm();
		String xqmc = CommonQueryDAO.getXqMc(xq);
		String clin = " "+xn+" "+(Base.isNull(xqmc)?" ":xqmc+"学期 ");
		if(!Base.isNull(xh)){
			clin += xh;
		}else if(!Base.isNull(xm)){
			clin += xm;
		}else if(!Base.isNull(bjdm)){
			clin += CommonQueryDAO.dao_getInfo("view_njxyzybj", new String[]{"bjmc"}," where bjdm='"+bjdm+"' and rownum=1").get("bjmc");
		}else{
			HashMap<String,String> map = new HashMap<String, String>();
			if(!Base.isNull(zydm)){				
				map = CommonQueryDAO.dao_getInfo("view_njxyzybj", new String[]{"zymc","nj","xymc"}," where zydm='"+zydm+"' and xydm like '"+(Base.isNull(xydm)?"%":xydm)+"' and  nj like '"+(Base.isNull(nj)?"%":nj)+"' and rownum=1");
				clin += nj+" "+map.get("xymc")+" "+map.get("zymc");
			}else{
				if(!Base.isNull(xydm)){
					map = CommonQueryDAO.dao_getInfo("view_njxyzybj", new String[]{"nj","xymc"}," where  xydm ='"+xydm+"' and nj like '"+(Base.isNull(nj)?"%":nj)+"' and rownum=1");
					clin += nj+" "+map.get("xymc");
				}else{
					if(!Base.isNull(nj)){
						clin += nj;
					}else{	
						clin += "全校";
					}
				}
			}
		}
		
		clin+=" "+"第二课堂汇总表";
		try {
			wwb =Workbook.createWorkbook(response.getOutputStream());
			StringBuffer sql = new StringBuffer();
			sql.append(" select rownum r,a.* from ( ");
			sql.append("select xh,xm,bjmc,zymc,nj,xymc,sum(nvl(xf,0))cout from view_tzcgcjxx where 1=1 ");
			sql.append(Base.isNull(xh)?"":" and xh='"+xh.trim()+"' ");
			sql.append(Base.isNull(xm)?"":" and xm like '%"+xm.trim()+"%' ");
			sql.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
			sql.append(Base.isNull(xq)?"":" and xq='"+xq+"' ");
			sql.append(Base.isNull(nj)?"":" and nj='"+nj+"' ");
			sql.append(Base.isNull(xydm)?"":" and xydm='"+xydm+"' ");
			sql.append(Base.isNull(zydm)?"":" and zydm='"+zydm+"' ");
			sql.append(Base.isNull(bjdm)?"":" and bjdm='"+bjdm+"' ");
			sql.append("group by xh,xm,bjmc,zymc,nj,xymc order by xh)a");
			List<HashMap<String,String>> list = dao.getList(sql.toString(),new String[]{},new String[]{"r","xh","xm","bjmc","nj","zymc","xymc","cout"});
			WritableSheet ws = wwb.createSheet(clin, 0);
			ExcelMB ex = new ExcelMB();
			ws.mergeCells(0, 0, 7, 2);
			ex.printToOneCell_multy(ws, clin, 0, 0,15, true, Alignment.CENTRE,
					VerticalAlignment.CENTRE, true, 650, Border.NONE);
			WritableCellFormat wcf = new WritableCellFormat(); // 构造单元格格式
			wcf = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
					Alignment.CENTRE, VerticalAlignment.CENTRE, true, Border.ALL);
			
			ws.addCell(new Label(0,3,"序号",wcf));
			ws.addCell(new Label(1,3,"学号",wcf));
			ws.addCell(new Label(2,3,"姓名",wcf));
			ws.addCell(new Label(3,3,"班级",wcf));
			ws.addCell(new Label(4,3,"年级",wcf));
			ws.addCell(new Label(5,3,"专业",wcf));
			ws.addCell(new Label(6,3,Base.YXPZXY_KEY,wcf));
			ws.addCell(new Label(7,3,"活动总分",wcf));
			for(int i=0;i<list.size();i++){
				String cout = list.get(i).get("cout");
				if((Float.parseFloat(cout)<1&&Float.parseFloat(cout)>0)){
					cout="0"+cout;
				}
				ws.addCell(new Label(0,4+i,list.get(i).get("r"),wcf));
				ws.addCell(new Label(1,4+i,list.get(i).get("xh"),wcf));
				ws.addCell(new Label(2,4+i,list.get(i).get("xm"),wcf));
				ws.addCell(new Label(3,4+i,list.get(i).get("bjmc"),wcf));
				ws.addCell(new Label(4,4+i,list.get(i).get("zymc"),wcf));
				ws.addCell(new Label(5,4+i,list.get(i).get("nj"),wcf));
				ws.addCell(new Label(6,4+i,list.get(i).get("xymc"),wcf));
				ws.addCell(new Label(7,4+i,cout,wcf));
			}
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据导出失败!");
		} 
		return mapping.findForward("");
	}
	
	/**
	 * 天津理工学生拓展成绩统计 tjlgTzcj 
	 * tjlgTzcj
	 * author 裘力俊
	 * data 2010-7-15
	 * @throws IOException 
	 */
	public ActionForward tjlgTzcj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			SztzForm sztzForm = (SztzForm) form;
			SztzTjlgService service=new SztzTjlgService();
			String pkValue=request.getParameter("pkValue");
			sztzForm.setXh(service.getTzxsxx(pkValue).get("xh"));
			String modelPath =servlet.getServletContext().getRealPath("") + "/print/rcsw/rcsw_tjlgdx_tzcj.xls";
			response.setContentType("application/vnd.ms-excel");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
		    service.tjlgTzcj(sztzForm,request, wwb);
		    return mapping.findForward("");
	}
	
	/** 
	 * @描述:学分申报审核结果保存
	 * @日期：2013-12-11 上午10:32:24
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	public ActionForward cgshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SztzForm sztzForm = (SztzForm) form;
		CommService commService = new CommService();
		commService.getModelValue(sztzForm, request);
		String[] pkArry=sztzForm.getPkValue();
		String pkValue = "";
		if(null!=pkArry && pkArry.length>0){
			for (int i=0;i<pkArry.length;i++){
				if(i==(pkArry.length-1)){
					pkValue+="'"+pkArry[i]+"'";
				}else{
					pkValue+="'"+pkArry[i]+"',";
				}
				
			}
		}
		DAO dao= DAO.getInstance();
		boolean flag=false;
		int result=0;
		String sql="select count(*) num from csmz_tzcgb where (xh||xmid||cyjs||jxlb) in ("+pkValue+") and (xysh<>'通过' or ( fdysh<>'通过' ) ) ";		
		result=Integer.parseInt(dao.getOneRs(sql, new String[]{}, "num"));
		if(result>0){
			flag=true;
		}else{
			flag=false;
		}             
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(flag);
		return null;
	}
}
