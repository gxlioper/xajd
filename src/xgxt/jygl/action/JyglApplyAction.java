package xgxt.jygl.action;

/*
 * 创建日期 2006-9-16
 *
 *  要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import oracle.sql.CLOB;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.DAO.DAO;
import xgxt.DAO.ExcelMB;
import xgxt.DAO.SxjyDAO;
import xgxt.action.ApplyAction;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.czxx.dyxx.DyxxDAO;
import xgxt.form.CommanForm;
import xgxt.jygl.dao.ByqxDao;
import xgxt.jygl.dao.BzgzzyDao;
import xgxt.jygl.dao.BzpyfsDao;
import xgxt.jygl.dao.DqlxDao;
import xgxt.jygl.dao.DwdqDao;
import xgxt.jygl.dao.Dwxz2Dao;
import xgxt.jygl.dao.DwxzDao;
import xgxt.jygl.dao.FileTool;
import xgxt.jygl.dao.HyflDao;
import xgxt.jygl.dao.JyglwjscDao;
import xgxt.jygl.dao.JzzbzDao;
import xgxt.jygl.dao.SydqDao;
import xgxt.jygl.dao.SydzgbmDao;
import xgxt.jygl.dao.XbDao;
import xgxt.jygl.dao.XlDao;
import xgxt.jygl.dao.YjspyfsDao;
import xgxt.jygl.dao.YjszyDao;
import xgxt.jygl.dao.ZgbmDao;
import xgxt.jygl.dao.ZzmmDao;
import xgxt.jygl.service.JyglService;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.Pages;
import xgxt.utils.RowidToPk;
import xgxt.utils.dealDate;
import xgxt.utils.String.StringUtils;
import xgxt.xljk.lrh_Util.util.lrh_commen_util;

import common.Globals;

/**
 * @author bat_zzj
 */

public class JyglApplyAction extends ApplyAction {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommanForm chkUser = (CommanForm) form;
		try {
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				chkUser.setErrMsg("登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}

			HttpSession session = request.getSession();
			String userType = "";
			if (session == null) {
				request.setAttribute("errMsg", "sadfsdf");
				return mapping.findForward("false");
			}
			userType = session.getAttribute("userOnLine").toString();

			ActionForward myActFwd = null;
			String myAct = mapping.getParameter();

			if (myAct.equalsIgnoreCase("grjlApply")) {
				myActFwd = grjlApply(mapping, form, request, response, userType); // 关于个人简历录入界面
			} else if (myAct.equalsIgnoreCase("grjlQuerySh")) {
				myActFwd = grjlQuerySh(mapping, form, request, response,
						userType); // 关于个人简历查询审核页面 查询 批量通过、否决
			} else if (myAct.equalsIgnoreCase("grjlViewMoreinfo")) {
				myActFwd = grjlViewMoreinfo(mapping, form, request, response); // 个人简历详细查看
			} else if (myAct.equalsIgnoreCase("jyglGrjlsh")) {
				myActFwd = jyglGrjlsh(mapping, form, request, response); // 个人简历窗口弹出及审核
			} else if (myAct.equalsIgnoreCase("grjlUpdate")) {
				myActFwd = grjlUpdate(mapping, form, request, response); // 个人简历修改
			} else if (myAct.equalsIgnoreCase("grjlResultQuery")) {
				myActFwd = grjlResultQuery(mapping, form, request, response,
						userType); // 个人简历结果查询

			} else if (myAct.equalsIgnoreCase("zpxxfb")) {
				myActFwd = zpxxfb(mapping, form, request, response, userType); // 招聘信息发布
			} else if (myAct.equalsIgnoreCase("zpxxquery")) {
				myActFwd = zpxxquery(mapping, form, request, response, userType); // 招聘信息查询，删除
			} else if (myAct.equalsIgnoreCase("zpxxViewMoreinfo")) {
				myActFwd = zpxxViewMoreinfo(mapping, form, request, response); // 招聘信息详细查询
			} else if (myAct.equalsIgnoreCase("zpxxupdate")) {
				myActFwd = zpxxupdate(mapping, form, request, response); // 招聘信息修改
			} else if (myAct.equalsIgnoreCase("zpxxSendGrjl")) {
				myActFwd = zpxxSendGrjl(mapping, form, request, response); // 投简历（页面打开,投）
			} else if (myAct.equalsIgnoreCase("zpxxResultQuery")) {
				myActFwd = zpxxResultQuery(mapping, form, request, response,
						userType); // 企业查看个人简历页面打开
			} else if (myAct.equalsIgnoreCase("zpxxViewMoreGrjlInfo")) {
				myActFwd = zpxxViewMoreGrjlInfo(mapping, form, request,
						response); // 企业查看个人简详细信息
			} else if (myAct.equalsIgnoreCase("zpxxReturnMess")) {
				myActFwd = zpxxReturnMess(mapping, form, request, response,
						userType); // 企业意见反馈
			} else if (myAct.equalsIgnoreCase("zpxxReturnMessQuery")) {
				myActFwd = zpxxReturnMessQuery(mapping, form, request,
						response, userType); // 企业意见反馈查询
			} else if (myAct.equalsIgnoreCase("zpxxReturnMessMoreQuery")) {
				myActFwd = zpxxReturnMessMoreQuery(mapping, form, request,
						response, userType); // 企业意见反馈查询(查看详细信息)

			} else if (myAct.equalsIgnoreCase("turndmkqueryjsp")) {
				myActFwd = turndmkqueryjsp(mapping, form, request, response,
						userType); // 跳转代码库查询器
			} else if (myAct.equalsIgnoreCase("dmkquery")) {
				myActFwd = dmkquery(mapping, form, request, response); // 代码库查询
			} else if (myAct.equalsIgnoreCase("bysxxapply")) {

				if (Globals.XXDM_FJGCXY.equals(Base.xxdm)) {

				}

				myActFwd = bysxxapply(mapping, form, request, response,
						userType); // 学生信息页面打开
			} else if (myAct.equalsIgnoreCase("bysjbxxbSave")) {
				myActFwd = bysxxapply(mapping, form, request, response,
						userType); // 学生生基本信息表录入
			} else if (myAct.equalsIgnoreCase("bysxxplsb")) {
				myActFwd = bysxxplsb(mapping, form, request, response, userType); // 毕业生信息批量表录入
			} else if (myAct.equalsIgnoreCase("JyglDataSearch")) {
				myActFwd = JyglDataSearch(mapping, form, request, response,
						userType); // 学生生基本信息查询
			} else if (myAct.equalsIgnoreCase("JyglBysjbxxbDel")) {
				myActFwd = JyglBysjbxxbDel(mapping, form, request, response); // 学生生基本信息删除
			} else if (myAct.equalsIgnoreCase("JyglViewMoreinfo")) {
				myActFwd = JyglViewMoreinfo(mapping, form, request, response); // 学生生详细信息查询
			} else if (myAct.equalsIgnoreCase("JyglInfoUpdate")) {
				myActFwd = JyglInfoUpdate(mapping, form, request, response); // 学生信息修改
			} else if (myAct.equalsIgnoreCase("jyxyInput")) {
				myActFwd = jyxyInput(mapping, form, request, response, userType); // 就业协议下拉框信息传入及协议保存
			} else if (myAct.equalsIgnoreCase("jyxyTurnInfo")) {
				myActFwd = jyxyTurnInfo(mapping, form, request, response,
						userType); // 通过查询学生信息填写就业协议
			} else if (myAct.equalsIgnoreCase("JyglQueryStuInfo")) {
				myActFwd = JyglQueryStuInfo(mapping, form, request, response); // 通过弹出学生查询窗口用于填写学生上报信息
			} else if (myAct.equalsIgnoreCase("JyxyDataSearch")) {
				myActFwd = JyxyDataSearch(mapping, form, request, response,
						userType); // 就业协议的（查询，删除）
			} else if (myAct.equalsIgnoreCase("JyglJyxyViewMoreinfo")) {
				myActFwd = JyglJyxyViewMoreinfo(mapping, form, request,
						response); // 就业协议详细信息的查询
			} else if (myAct.equalsIgnoreCase("jyglJyxyFdysh")) {
				myActFwd = jyglJyxyFdysh(mapping, form, request, response); // 就业协议辅导员审核
			} else if (myAct.equalsIgnoreCase("jyglJyxyXxsh")) {
				myActFwd = jyglJyxyXxsh(mapping, form, request, response); // 就业协议学校审核
			} else if (myAct.equalsIgnoreCase("jyglJyxyUpdate")) {
				myActFwd = jyglJyxyUpdate(mapping, form, request, response); // 就业协议修改
			} else if (myAct.equalsIgnoreCase("jyglJyxyResultQuery")) {
				myActFwd = jyglJyxyResultQuery(mapping, form, request,
						response, userType); // 就业协议审核结果查询
			} else if (myAct.equalsIgnoreCase("jyglJyxyLqdj")) {
				myActFwd = jyglJyxyLqdj(mapping, form, request, response); // 就业协议领取登记
			} else if (myAct.equalsIgnoreCase("viewJyxyLqdjInfo")) {
				myActFwd = viewJyxyLqdjInfo(mapping, form, request, response); // 就业协议领取登记详细信息查看
			} else if (myAct.equalsIgnoreCase("jyxyBlsq")) {
				myActFwd = jyxyBlsq(mapping, form, request, response); // 就业协议补领申请
			} else if (myAct.equalsIgnoreCase("jyxyBlsqQuery")) {
				myActFwd = jyxyBlsqQuery(mapping, form, request, response); // 就业协议补领申请管理
			} else if (myAct.equalsIgnoreCase("jyxyBlsqViewMore")) {
				myActFwd = jyxyBlsqViewMore(mapping, form, request, response); // 就业协议补领申请详细查看-审核
			} else if (myAct.equalsIgnoreCase("dwxxmkwh")) {
				myActFwd = dwxxmkwhViewMore(mapping, form, request, response); // 单位信息维护--地质大学
			} else if (myAct.equalsIgnoreCase("dwlfxx")) {
				myActFwd = dwlfxxViewMore(mapping, form, request, response); // 单位来访信息--地质大学
			} else if (myAct.equalsIgnoreCase("zpxxdw")) {
				myActFwd = zpxxdwViewMore(mapping, form, request, response); // 招聘信息刷单位
			} else if (myAct.equalsIgnoreCase("xsxxsh")) {
				myActFwd = xsxxsh(mapping, form, request, response, userType); // 招聘信息刷单位
			} else if (myAct.equalsIgnoreCase("zxzxshtz")) {
				myActFwd = zxzxshtz(mapping, form, request, response); // 咨询预约管理（学生回复）
			} else if (myAct.equalsIgnoreCase("zxzxshcx")) {
				myActFwd = zxzxshcx(mapping, form, request, response); // 咨询预约管理（学生回复）

			} else if (myAct.equalsIgnoreCase("jyzxTeaInput")) {
				myActFwd = jyzxTeaInput(mapping, form, request, response,
						userType); // 就业咨询教师登记
			} else if (myAct.equalsIgnoreCase("jyzxYuyuequery")) {
				myActFwd = jyzxYuyuequery(mapping, form, request, response,
						userType); // 学生咨询预约(查询 删除)
			} else if (myAct.equalsIgnoreCase("jyzxViewMoreInfo")) {
				myActFwd = jyzxViewMoreInfo(mapping, form, request, response); // 学生咨询（看详细信息）
			} else if (myAct.equalsIgnoreCase("jyzxZxsUpdate")) {
				myActFwd = jyzxZxsUpdate(mapping, form, request, response); // 咨询师信息修改
			} else if (myAct.equalsIgnoreCase("jyzxZxsYuyue")) {
				myActFwd = jyzxZxsYuyue(mapping, form, request, response,
						userType); // 咨询师预约
			} else if (myAct.equalsIgnoreCase("jyzxZxyygl")) {
				myActFwd = jyzxZxyygl(mapping, form, request, response,
						userType); // 咨询预约管理（页面打开,查询，删除）
			} else if (myAct.equalsIgnoreCase("zxyyglViewMoreInfo")) {
				myActFwd = zxyyglViewMoreInfo(mapping, form, request, response); // 咨询预约管理（看详细信息）
			} else if (myAct.equalsIgnoreCase("answerYuyue")) {
				myActFwd = answerYuyue(mapping, form, request, response); // 咨询预约管理（教师回复）
			} else if (myAct.equalsIgnoreCase("updateYuyue")) {
				myActFwd = updateYuyue(mapping, form, request, response); // 咨询预约管理（修改）
			} else if (myAct.equalsIgnoreCase("jyzxResultQuery")) {
				myActFwd = jyzxResultQuery(mapping, form, request, response,
						userType); // 咨询预约管理（结果查询）
			} else if (myAct.equalsIgnoreCase("answerYuyueStu")) {
				myActFwd = answerYuyueStu(mapping, form, request, response); // 咨询预约管理（学生回复）

			} else if (myAct.equalsIgnoreCase("byqxInput")) {
				myActFwd = byqxInput(mapping, form, request, response, userType); // 毕业去向录入
			} else if (myAct.equalsIgnoreCase("byqxQuery")) {
				myActFwd = byqxQuery(mapping, form, request, response, userType); // 毕业去向查询(页面打开,删除）
			} else if (myAct.equalsIgnoreCase("byqxViewMoreQuery")) {
				myActFwd = byqxViewMoreQuery(mapping, form, request, response); // 毕业去向详细信息查看
			} else if (myAct.equalsIgnoreCase("jyglByqxUpdate")) {
				myActFwd = jyglByqxUpdate(mapping, form, request, response); // 毕业去向修改
			} else if (myAct.equalsIgnoreCase("jyglByqxSh")) {
				myActFwd = jyglByqxSh(mapping, form, request, response); // 毕业去向审核

			} else if (myAct.equalsIgnoreCase("jyglgetfile")) {
				myActFwd = jyglgetfile(mapping, form, request, response); // 文件上传
			} else if (myAct.equalsIgnoreCase("jygldelfile")) {
				myActFwd = jygldelfile(mapping, form, request, response); // 文件删除
			} else if (myAct.equalsIgnoreCase("jyglteafiledown")) {
				myActFwd = jyglteafiledown(mapping, form, request, response); // 教师表格下载
			} else if (myAct.equalsIgnoreCase("jyglstufiledown")) {
				myActFwd = jyglstufiledown(mapping, form, request, response); // 学生表格下载
			} else if (myAct.equalsIgnoreCase("showzcwjlist")) {
				myActFwd = showzcwjlist(mapping, form, request, response,
						userType); // 显示政策文件发布页面
			} else if (myAct.equalsIgnoreCase("showmorezcwj")) {
				myActFwd = showmorezcwj(mapping, form, request, response); // 显示政策文件详细内容以及发布政策文件
			} else if (myAct.equalsIgnoreCase("zcwjupdate")) {
				myActFwd = zcwjupdate(mapping, form, request, response); // 政策文件修改
			} else if (myAct.equalsIgnoreCase("zcwjquery")) {
				myActFwd = zcwjquery(mapping, form, request, response); // 政策文件查询
			} else if (myAct.equalsIgnoreCase("jyglexpData")) {
				myActFwd = jyglexpData(mapping, form, request, response); // 就业管理数据导出

			} else if (myAct.equalsIgnoreCase("jyzpWeiHu")) {
				myActFwd = jyzpWeiHu(mapping, form, request, response, userType); // 就业招聘维护
			} else if (myAct.equalsIgnoreCase("jyxyWeiHu")) {
				myActFwd = jyxyWeiHu(mapping, form, request, response, userType); // 就业协议维护
			} else if (myAct.equalsIgnoreCase("userczmx")) {
				myActFwd = userczmx(mapping, form, request, response); // 用户操作明细（查询，删除）
			} else if (myAct.equalsIgnoreCase("onebutton")) {
				myActFwd = onebutton(mapping, form, request, response); // 一键统计
			} else if (myAct.equalsIgnoreCase("stuinfo")) {
				myActFwd = stuinfo(mapping, form, request, response); // 学生就业信息
			} else if (myAct.equalsIgnoreCase("stuinfoquery")) {
				myActFwd = stuinfoquery(mapping, form, request, response); // 学生基本信息查询（针对宁波职业）
			} else if (myAct.equalsIgnoreCase("stuinfoInput")) {
				myActFwd = stuinfoInput(mapping, form, request, response); // 学生就业信息录入
			} else if (myAct.equalsIgnoreCase("stuinfomorequery")) {
				myActFwd = stuinfomorequery(mapping, form, request, response); // 学生就业信息详细查看
			} else if (myAct.equalsIgnoreCase("stuinfoUpdate")) {
				myActFwd = stuinfoUpdate(mapping, form, request, response); // 学生就业信息修改
			} else if (myAct.equalsIgnoreCase("xsjytj")) {
				myActFwd = xsjytj(mapping, form, request, response); // 学生就业统计
			} else if (myAct.equalsIgnoreCase("dwxxInput")) {
				myActFwd = dwxxinfoInput(mapping, form, request, response); // 单位信息录入
			} else if (myAct.equalsIgnoreCase("dwxxUpdate")) {
				myActFwd = dwxxinfoUpdate(mapping, form, request, response); // 单位信息录入
			} else if (myAct.equalsIgnoreCase("jyqktj")) {
				myActFwd = jyqktj(mapping, form, request, response); // 单位信息录入
			} else if (myAct.equalsIgnoreCase("ahgc_jyglBysxxSb")) {
				myActFwd = ahgcjyglBysxxSb(mapping, form, request, response); // 安徽工程毕业生信息上报
			} else if (myAct.equalsIgnoreCase("zjlgjyxyexp")) {
				myActFwd = zjlgjyxyexp(mapping, form, request, response); // 安徽工程毕业生信息上报
			} else if (myAct.equalsIgnoreCase("jyxxlr")) {
				myActFwd = jyxxlr(mapping, form, request, response); // 安徽工程毕业生信息上报
			} else if (myAct.equalsIgnoreCase("jyxxcx")) {
				myActFwd = jyxxcx(mapping, form, request, response); // 安徽工程毕业生信息上报
			} else if (myAct.equalsIgnoreCase("jyxxtj")) {
				myActFwd = jyxxtj(mapping, form, request, response); // 安徽工程毕业生信息上报
			} else if (myAct.equalsIgnoreCase("ydtj")) {
				myActFwd = ydtj(mapping, form, request, response);
			}
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			chkUser.setErrMsg("数据连接中断，请重新登陆！");
		}
		return new ActionForward("/login.jsp", false);
	}

	// 个人简历录入
	private ActionForward grjlApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		DAO dao = DAO.getInstance();
		String xxdm = dao.getXxdm();
		HttpSession session = request.getSession();
		String sql = "";
		CommanForm applyForm = new CommanForm();
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = applyForm.getXh();
		String act = request.getParameter("act");
		String xsxh = request.getParameter("xsxh");
		String[] rs = null;
		String doType = request.getParameter("doType");
		sql = "select zzmmdm,zzmm from dmk_zzmm"; // 政治面貌
		List zzmmList = dao.getList(sql, new String[] {}, new String[] {
				"zzmmdm", "zzmm" });
		request.setAttribute("zzmmList", zzmmList);
		String tableName = "jygl_grjlb";

		String xxdm1 = (String) request.getSession().getAttribute("xxdm"); // 2学校代码
		String xsxh1 = (String) request.getSession().getAttribute("userName");
		if ("12453".equals(xxdm1) && !"".equals(xsxh1)) {
			String[] str = dao.getOneRs(
					"select sfsh from jygl_xsjbxxb where xsxh=?",
					new String[] { xsxh1 }, new String[] { "sfsh" });
			@SuppressWarnings("unused")
			boolean bool = false;
			if (str != null) {
				if ("已通过√".equals(str[0])) {
					bool = true;
				} else {
					userType = "shwtg";
					act = "bnsq";
					request.setAttribute("exists", "exists");
				}
			}
		}

		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
			sql = "select * from jygl_xsjbxxb where xsxh=?";
			String[] colList = dao
					.getColumnName("select * from jygl_xsjbxxb where 1=2");
			rs = dao.getOneRs(sql, new String[] { xh }, colList);
			if (rs != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), rs[i]);
				}
				// 通过学号查民族
				sql = "select mzmc from mzdmb where mzdm=(select mzdm from bks_xsqtxx where xh=?)";
				rs = dao.getOneRs(sql, new String[] { xh },
						new String[] { "mzmc" });
				if (null != rs) {
					map.put("mz", rs[0]);
				}
				// 生源地区
				String sydqdm = map.get("sydqdm");
				sql = "select * from dmk_sydq where sydqdm=?";
				rs = dao.getOneRs(sql, new String[] { sydqdm },
						new String[] { "sydq" });
				if (null != rs) {
					map.put("sydq", rs[0]);
				}
				// 出生年月从身份证转换
				if (map.get("id") != null) {
					if (map.get("id").length() == 18) {
						String csny = map.get("id").substring(6, 14);
						String year1 = csny.substring(0, 4);
						String mon1 = csny.substring(4, 6);
						String day1 = csny.substring(6, 8);
						csny = year1 + "年" + mon1 + "月" + day1 + "日";
						map.put("csny", csny);
					}
					if (map.get("id").length() == 15) {
						String csny = map.get("id").substring(6, 12);
						String year1 = csny.substring(0, 2);
						String mon1 = csny.substring(2, 4);
						String day1 = csny.substring(4, 6);
						csny = "19" + year1 + "年" + mon1 + "月" + day1 + "日";
						map.put("csny", csny);
					}
				}
				// 性别代码转成性别
				if (!(xxdm.equals(Globals.XXDM_YNYS))) {
					if (map.get("xbdm").equals("1")) {
						map.put("xb", "男");
					} else if (map.get("xbdm").equals("2")) {
						map.put("xb", "女");
					}

					// 学历代码转换
					String xldm = map.get("xldm");
					sql = "select * from dmk_xl where xldm=?";
					rs = dao.getOneRs(sql, new String[] { xldm },
							new String[] { "xl" });
					map.put("xl", StringUtils.isArrayNotNull(rs) ? rs[0] : "");
				}
			}
		}
		if ("go".equals(act)) {
			sql = "select * from  jygl_xsjbxxb where xsxh=?";
			String[] colList = dao
					.getColumnName("select * from jygl_xsjbxxb where 1=2");
			rs = dao.getOneRs(sql, new String[] { xsxh }, colList);
			if (rs != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), rs[i]);
				}
				// 通过学号查民族
				sql = "select mzmc from mzdmb where mzdm=(select mzdm from bks_xsqtxx where xh=?)";
				rs = dao.getOneRs(sql, new String[] { xsxh },
						new String[] { "mzmc" });
				if (null != rs) {
					map.put("mz", rs[0]);
				}
				// 生源地区
				String sydqdm = map.get("sydqdm");
				sql = "select * from dmk_sydq where sydqdm=?";
				rs = dao.getOneRs(sql, new String[] { sydqdm },
						new String[] { "sydq" });
				if (null != rs) {
					map.put("sydq", rs[0]);
				}
				// 出生年月从身份证转换
				if (map.get("id") != null) {
					if (map.get("id").length() == 18) {
						String csny = map.get("id").substring(6, 14);
						String year1 = csny.substring(0, 4);
						String mon1 = csny.substring(4, 6);
						String day1 = csny.substring(6, 8);
						csny = year1 + "年" + mon1 + "月" + day1 + "日";
						map.put("csny", csny);
					}
					if (map.get("id").length() == 15) {
						String csny = map.get("id").substring(6, 12);
						String year1 = csny.substring(0, 2);
						String mon1 = csny.substring(2, 4);
						String day1 = csny.substring(4, 6);
						csny = "19" + year1 + "年" + mon1 + "月" + day1 + "日";
						map.put("csny", csny);
					}
				}
				// 性别代码转成性别
				if (!(xxdm.equals(Globals.XXDM_YNYS))) {
					if ("1".equals(map.get("xbdm"))) {
						map.put("xb", "男");
					} else if ("2".equals(map.get("xbdm"))) {
						map.put("xb", "女");
					}
					// 学历代码转换
					String xldm = map.get("xldm");
					sql = "select * from dmk_xl where xldm=?";
					rs = dao.getOneRs(sql, new String[] { xldm },
							new String[] { "xl" });
					map.put("xl", rs != null && rs.length > 0 ? rs[0] : "");
				}
			}
		}

		if ("save".equals(doType)) {
			String id = request.getParameter("id"); // 身份证号码1
			String idsee = request.getParameter("idsee"); // 身份证是否保密2
			xsxh = request.getParameter("xsxh"); // 学号3
			String name = DealString.toGBK(request.getParameter("name")); // 姓名4
			String xb = DealString.toGBK(request.getParameter("xb")); // 性别5
			String csny = DealString.toGBK(request.getParameter("csny")); // 出生年月6
			String mz = DealString.toGBK(request.getParameter("mz")); // 民族7
			String xl = DealString.toGBK(request.getParameter("xl")); // 学历8
			String zzmm = DealString.toGBK(request.getParameter("zzmm")); // 政治面貌9
			String zymc = DealString.toGBK(request.getParameter("zymc")); // 专业名称10
			String fxzymc = DealString.toGBK(request.getParameter("fxzymc")); // 辅修专业名称11
			String sydq = DealString.toGBK(request.getParameter("sydq")); // 生源地区12
			String lxdz = DealString.toGBK(request.getParameter("lxdz")); // 联系地址13
			String lxdh = request.getParameter("lxdh"); // 联系电话14
			String yzbm = request.getParameter("yzbm"); // 邮政编码15
			String email = request.getParameter("email"); // 电子邮箱16
			String hjqk = DealString.toGBK(request.getParameter("hjqk")); // 获奖情况17
			String xxqk = DealString.toGBK(request.getParameter("xxqk")); // 学习情况18
			String xjysjl = DealString.toGBK(request.getParameter("xjysjl")); // 校级以上奖励19
			String shsjqk = DealString.toGBK(request.getParameter("shsjqk")); // 社会实践情况20
			String gzjl = DealString.toGBK(request.getParameter("gzjl")); // 工作经历21
			String grtc = DealString.toGBK(request.getParameter("grtc")); // 个人特长22
			String zwtj = DealString.toGBK(request.getParameter("zwtj")); // 个人推荐23
			String xxtj = DealString.toGBK(request.getParameter("xxtj")); // 学校推荐24
			String fbsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 发布时间25
							// //
							// 取至数据库临时表
							new String[] {}, new String[] { "sdate" }))[0];

			boolean judge = false;
			// if (!"teacher".equals(userType)) {
			@SuppressWarnings("unused")
			boolean bool = StandardOperation.delete("delete from " + tableName
					+ " where xsxh='" + xsxh + "'", tableName, request);
			judge = StandardOperation.insert(tableName, new String[] { "id",
					"idsee", "xsxh", "name", "xb", "csny", "mz", "xl", "zzmm",
					"zymc", "fxzymc", "sydq", "lxdz", "lxdh", "yzbm", "email",
					"hjqk", "xxqk", "xjysjl", "shsjqk", "gzjl", "grtc", "zwtj",
					"xxtj", "fbsj" }, new String[] { id, idsee, xsxh, name, xb,
					csny, mz, xl, zzmm, zymc, fxzymc, sydq, lxdz, lxdh, yzbm,
					email, hjqk, xxqk, xjysjl, shsjqk, gzjl, grtc, zwtj, xxtj,
					fbsj }, request);
			// }
			if (judge) {
				request.setAttribute("save", "ok");

				// 添加操作的记录
				if ("teacher".equals(userType)) {
					String userName = session.getAttribute("userName")
							.toString();
					String whensj = (dao
							.getOneRs(
									"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
									new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

					StandardOperation.insert("JYGL_USERCZMXB", new String[] {
							"userid", "dowhat", "whichtable", "whichpk",
							"whensj" }, new String[] { userName, "增加", "个人简历表",
							"学号:" + xsxh, whensj }, request);
				}
			} else {
				request.setAttribute("save", "no");
			}
		}
		List<String[]> xscjList = new ArrayList<String[]>();
		List<String[]> xscjLists1 = new ArrayList<String[]>();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		List<List<String[]>> xscjLists = new ArrayList<List<String[]>>();
		sql = "select * from cjb where xh=?";
		xscjList = dao.rsToVator(sql, new String[] { userName }, new String[] {
				"kcmc", "cj" });
		int j = 0;
		for (int i = 0; i < xscjList.size(); i++) {
			String[] lists = xscjList.get(i);
			if (j == 0 || (j == i && j != 0 && i != 1)) {
				j++;
				xscjLists1 = new ArrayList<String[]>();
				xscjLists1.add(lists);
			} else {
				j = 0;
				xscjLists1.add(lists);
				xscjLists.add(xscjLists1);
			}
		}
		request.setAttribute("xscjList", xscjLists);
		map.put("stuExists", "yes");
		map.put("userType", userType);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 个人简历查询审核
	private ActionForward grjlQuerySh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		// 初始化页面，返回查询信息
		DAO dao = DAO.getInstance();
		String usertyzljl = request.getSession().getAttribute("userType")
				.toString();
		if (usertyzljl.equalsIgnoreCase("stu")) {
			request.setAttribute("errMsg", "学生无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
		if (usertyzljl.equalsIgnoreCase("xy")) {
			request.setAttribute("errMsg", Base.YXPZXY_KEY+"用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}

		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String tableName = "jygl_grjlb"; // 查询表名
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String doType3 = request.getParameter("doType3");
		String act = request.getParameter("act");
		String sql = "";
		String pk = "xsxh"; // 主键
		String querry = " where 1=1 "; // 条件初始化
		String rsNum = "0"; // 返回的记录数
		HashMap<String, String> map = new HashMap<String, String>();

		String xsxh = request.getParameter("xsxh"); // 学号
		String name = DealString.toGBK(request.getParameter("name")); // 姓名
		String xb = DealString.toGBK(request.getParameter("xb")); // 性别
		String xymc = DealString.toGBK(request.getParameter("xymc")); // 学院
		String nj = request.getParameter("nj"); // 年级
		String xxsh = DealString.toGBK(request.getParameter("xxsh")); // 学校审核
		// 关于发布时间的处理 用于查询条件
		String xjsj = DealString.toGBK(request.getParameter("xjsj")); // 相距时间
		String nowsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 发布时间
				// //
				// 取至数据库临时表
				new String[] {}, new String[] { "sdate" }))[0];
		String shsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 学校审核时间
				// //
				// 取至数据库临时表
				new String[] {}, new String[] { "sdate" }))[0];
		String sjyear = nowsj.substring(0, 4);
		String sjmonth = nowsj.substring(4, 6);
		String sjday = nowsj.substring(6, 8);
		String sjqt = nowsj.substring(8, 14);
		String nowsj1 = sjyear + "-" + sjmonth + "-" + sjday;
		dealDate dealdate = new dealDate();
		String beforesj = "";
		if (!"".equals(xjsj)) {
			beforesj = dealdate.getDate2(Integer.parseInt(xjsj), nowsj1);
			beforesj = beforesj.replaceAll("-", "") + sjqt;
		}
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String xxdm = (String) session.getAttribute("xxdm");
		sql = "select xm from view_fdyxx where zgh=?";
		String[] fdysf = dao.getOneRs(sql, new String[] { userName },
				new String[] { "xm" });
		// 批量审核通过
		if ("pass".equalsIgnoreCase(doType3)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = pkstring.split("!!#!!");

			for (int i = 1; i < pkstringI.length; i++) {
				String whichxh = pkstringI[i];
				if ("xy".equals(session.getAttribute("userType").toString())
						&& "12453".equals(xxdm)) {
					sql = "update jygl_grjlb set fdysh='已通过√',fdyshr='"
							+ userName + "',fdyshsj='" + shsj
							+ "' where xsxh=?";
				} else {
					sql = "update jygl_grjlb set xxsh='已通过√',shperson='"
							+ userName + "',shsj='" + shsj + "' where xsxh=?";
				}
				boolean judge = false;
				judge = dao.runUpdate(sql, new String[] { whichxh });
				if (judge) {
					request.setAttribute("allpass", "ok");
				} else {
					request.setAttribute("allpass", "no");
				}
			}
		}
		// 批量审核否决
		if ("notpass".equalsIgnoreCase(doType3)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = pkstring.split("!!#!!");

			for (int i = 1; i < pkstringI.length; i++) {
				String whichxh = pkstringI[i];
				if ("xy".equals(session.getAttribute("userType").toString())
						&& "12453".equals(xxdm)) {
					sql = "update jygl_grjlb set fdysh='未通过X',fdyshr='"
							+ userName + "',fdyshsj='" + shsj
							+ "' where xsxh=?";
				} else {
					sql = "update jygl_grjlb set xxsh='未通过X',shperson='"
							+ userName + "',shsj='" + shsj + "' where xsxh=?";
				}
				boolean judge = false;
				judge = dao.runUpdate(sql, new String[] { whichxh });
				if (judge) {
					request.setAttribute("allnotpass", "ok");
				} else {
					request.setAttribute("allnotpass", "no");
				}
			}
		}

		// if ("teacher".equals(userType)) {
		// sql = "select usertype,xymc from JYGL_JYXYWHB where username=?";
		// String[] teainfo = dao.getOneRs(sql, new String[] { userName },
		// new String[] { "usertype", "xymc" });
		// String userty = "";
		// if (null != teainfo) {
		// userty = teainfo[0];
		// xymc = teainfo[1];
		// }
		// if ("辅导员".equals(userty)) {
		// map.put("xymc", xymc);
		// request.setAttribute("who", "fudaoyuan");
		// } else {
		// request.setAttribute("who", "teacher");
		// }
		// }

		if ("teacher".equals(userType)) {
			sql = "select usertype,xymc from JYGL_JYXYWHB where username=?";
			String[] teainfo = dao.getOneRs(sql, new String[] { userName },
					new String[] { "usertype", "xymc" });
			String userty = "";

			if (null != teainfo) {
				userty = teainfo[0];
				xymc = teainfo[1];
			}
			if ("辅导员".equals(userty)) {
				map.put("xymc", xymc);
				request.setAttribute("who", "fudaoyuan");
			} else if (StringUtils.isArrayNotNull(fdysf)) {
				request.setAttribute("who", "fudaoyuan");
			} else {
				request.setAttribute("who", "teacher");
			}
		}

		if ("query".equals(doType)) {
			map.put("xsxh", xsxh);
			map.put("name", name);
			map.put("xb", xb);
			map.put("xymc", xymc);
			map.put("nj", nj);
			map.put("xxsh", xxsh);
			map.put("xjsj", xjsj);
		}

		if ("del".equals(doType2)) {
			// 删除数据
			boolean judge = false;
			String pkValue = request.getParameter("pkValue");
			pkValue = pkValue.replaceAll(" ", "+");
			sql = "delete from " + tableName + " where xsxh='" + pkValue + "'";
			judge = dao.runUpdate(sql, new String[] {});
			if (judge) {
				request.setAttribute("delete", "ok");

				// 删除操作的记录
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"删除", "个人简历表", "学号:" + pkValue, whensj },
						request);

			} else {
				request.setAttribute("delete", "no");
			}
		}

		if (xsxh == null) {
			xsxh = "";
		}
		if (name == null) {
			name = "";
		}
		if (xb == null) {
			xb = "";
		}
		if (xymc == null) {
			xymc = "";
		}
		if (nj == null) {
			nj = "";
		}
		if (xxsh == null) {
			xxsh = "";
		}
		if (xjsj == null) {
			xjsj = "";
		}
		if ((xsxh == null) || xsxh.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xsxh like '%" + xsxh + "%' ";
		}
		if ((name == null) || name.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and name like '%" + name + "%' ";
		}
		if ((xb == null) || xb.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xb = '" + xb + "'";
		}
		if ((xymc == null) || xymc.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += " and xsxh in (select xsxh from jygl_xsjbxxb where xsxh in (select xh from view_xsjbxx where xymc ='"
					+ xymc + "'))";
		}
		if ((nj == null) || nj.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xsxh in (select xsxh from jygl_xsjbxxb where nd = '"
					+ nj + "')";
		}
		if ((xxsh == null) || xxsh.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xxsh = '" + xxsh + "' ";
		}
		if ((xjsj == null) || xjsj.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and (fbsj between '" + beforesj + "' and '" + nowsj
					+ "') ";
		}

		String usertype = session.getAttribute("userType").toString();
		if ("12453".equals(xxdm)) {
			if ("xx".equals(usertype) || "admin".equals(usertype)) {
				sql = "select " + pk + " 主键,rownum 行号,a.* from " + tableName
						+ " a " + querry + " and fdysh='已通过√'";
			} else {
				sql = "select " + pk + " 主键,rownum 行号,a.* from " + tableName
						+ " a " + querry;
			}

		} else {
			sql = "select " + pk + " 主键,rownum 行号,a.* from " + tableName
					+ " a " + querry;
		}
		colList = new String[] { "主键", "行号", "name", "xsxh", "xb", "xl",
				"zymc", "fbsj", "readtimes", "xxsh", "fdysh" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ("go".equals(act)) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		}
		if (rs == null) {
			rsNum = "0";
		} else {
			rsNum = String.valueOf(rs.size());
		}
		if ("12453".equals(xxdm)) {
			usertype = request.getSession().getAttribute("userType").toString();
			String bbmc = (String) request.getSession().getAttribute("bmmc");
			if ("xx".equals(usertype) || "admin".equals(usertype)) {
				request.setAttribute("who", "teacher");
			} else if ("xy".equals(usertype)) {
				request.setAttribute("who", "fudaoyuan");
				map.put("xymc", bbmc);
			}
		}

		request.setAttribute("rs1", map); // 发送查询数据回页面
		request.setAttribute("njList", dao.getNjList()); // 发送入学年度列表
		request.setAttribute("xyList", dao.getXyList());// 发送学院列表
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		return mapping.findForward("success");
	}

	// 点击查看个人简历详细
	private ActionForward grjlViewMoreinfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = "xsxh";
		String sql = ""; // sql语句
		DAO dao = DAO.getInstance();
		String realTable = "jygl_grjlb";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// 查询数据
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from jygl_grjlb where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			sql = "select xymc from view_xsjbxx where xh=?";
			String[] xymc = dao.getOneRs(sql, new String[] { pkValue },
					new String[] { "xymc" });
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				map.put("xymc", xymc[0]);
				String fbsj = null;
				String shsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				// 发布时间转换
				String sj = map.get("fbsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				fbsj = sjyear + "年" + sjmon + "月" + sjday + "日" + sjhour + "时";
				map.put("fbsj", fbsj);
				// 审核时间转换
				if (null != map.get("shsj")) {
					sj = map.get("shsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					shsj = sjyear + "年" + sjmon + "月" + sjday + "日" + sjhour
							+ "时";
					map.put("shsj", shsj);
				}
			}
		}
		request.setAttribute("rs", map);// 发送数据集
		return mapping.findForward("success");
	}

	// 个人简历学校审核
	private ActionForward jyglGrjlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pk = "xsxh";
		String sql = ""; // sql语句
		DAO dao = DAO.getInstance();
		String realTable = "jygl_grjlb";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();
		String xxsh = DealString.toGBK(request.getParameter("xxsh"));
		String btgyy = DealString.toGBK(request.getParameter("btgyy"));
		String xsxh = request.getParameter("xsxh");
		String act = request.getParameter("act");
		HttpSession session = request.getSession();
		String shperson = session.getAttribute("userName").toString();
		String xxtj = DealString.toGBK(request.getParameter("xxtj"));
		String userType = (String) session.getAttribute("userType");
		// String xxdm = (String) session.getAttribute("xxdm");
		String bbmctt = (String) session.getAttribute("bmmc");

		if ("xx".equals(userType) || "admin".equals(userType)) {
			request.setAttribute("ldgxusertype", "xx");
		} else if ("xy".equals(userType)) {
			request.setAttribute("ldgxusertype", "xy");
			map.put("xymc", bbmctt);
		}
		// String userName = session.getAttribute("userName").toString();
		sql = "select xm from view_fdyxx where zgh=?";
		// String[] isfdy = dao.getOneRs(sql, new String[] { userName },
		// new String[] { "xm" });

		if ("xy".equals(userType)) {
			if ("shenhe".equals(act)) {
				request.setAttribute("who", "fudaoyuan");
				String fdyshsj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 学校审核时间
								// //
								// 取至数据库临时表
								new String[] {}, new String[] { "sdate" }))[0];

				String fdysh = DealString.toGBK(request.getParameter("fdysh"));
				String fdyshyj = DealString.toGBK(request
						.getParameter("fdyshyj"));
				// String fdyshr;

				if ("已通过√".equals(fdysh)) {
					btgyy = "";
				}
				if ("未审核".equals(fdysh)) {
					fdyshyj = "";
					// fdyshr = "";
					fdyshsj = "";
					shperson = "";
				}
				sql = "update " + realTable + " set fdysh='" + fdysh
						+ "' , fdyshyj='" + fdyshyj + "' ,fdyshsj='" + fdyshsj
						+ "' ,fdyshr='" + shperson + "' where xsxh='" + xsxh
						+ "'";

				boolean judge = false;
				judge = dao.runUpdate(sql, new String[] {});
				if (judge) {
					request.setAttribute("shenhe", "ok");
				} else {
					request.setAttribute("shenhe", "no");
				}
				sql = "select * from jygl_grjlb where xsxh=?";
				String[] colList = dao
						.getColumnName("select * from jygl_grjlb where 1=2"); // 返回列名数组
				String[] stuinfo = dao.getOneRs(sql, new String[] { xsxh },
						colList);
				sql = "select xymc from view_xsjbxx where xh=?";
				String[] xymc = dao.getOneRs(sql, new String[] { xsxh },
						new String[] { "xymc" });
				if (stuinfo != null) {
					for (int i = 0; i < colList.length; i++) {
						map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
					}
					if (xymc != null) {
						map.put("xymc", xymc[0]);
					}
					fdyshsj = null;
					String sjyear = null;
					String sjmon = null;
					String sjday = null;
					String sjhour = null;
					if (null != map.get("fdyshsj")) {
						String sj = map.get("fdyshsj").toString();
						sjyear = sj.substring(0, 4);
						sjmon = sj.substring(4, 6);
						sjday = sj.substring(6, 8);
						sjhour = sj.substring(8, 10);
						fdyshsj = sjyear + "年" + sjmon + "月" + sjday + "日"
								+ sjhour + "时";
						map.put("fdyshsj", fdyshsj);
					}
					if (null != map.get("shsj")) {
						String sj = map.get("shsj").toString();
						sjyear = sj.substring(0, 4);
						sjmon = sj.substring(4, 6);
						sjday = sj.substring(6, 8);
						sjhour = sj.substring(8, 10);
						fdyshsj = sjyear + "年" + sjmon + "月" + sjday + "日"
								+ sjhour + "时";
						map.put("shsj", fdyshsj);
					}
				}
			}
		} else {
			if ("shenhe".equals(act)) {
				String shsj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 学校审核时间
								// //
								// 取至数据库临时表
								new String[] {}, new String[] { "sdate" }))[0];
				if ("已通过√".equals(xxsh)) {
					btgyy = "";
				}
				if ("未审核".equals(xxsh)) {
					btgyy = "";
					shperson = "";
					shsj = "";
				}
				sql = "update " + realTable + " set xxsh='" + xxsh
						+ "' , btgyy='" + btgyy + "' ,shsj='" + shsj
						+ "' ,shperson='" + shperson + "' ,xxtj='" + xxtj
						+ "' where xsxh='" + xsxh + "'";

				boolean judge = false;
				judge = dao.runUpdate(sql, new String[] {});
				if (judge) {
					request.setAttribute("shenhe", "ok");
				} else {
					request.setAttribute("shenhe", "no");
				}
				sql = "select * from jygl_grjlb where xsxh=?";
				String[] colList = dao
						.getColumnName("select * from jygl_grjlb where 1=2"); // 返回列名数组
				String[] stuinfo = dao.getOneRs(sql, new String[] { xsxh },
						colList);
				sql = "select xymc from view_xsjbxx where xh=?";
				String[] xymc = dao.getOneRs(sql, new String[] { xsxh },
						new String[] { "xymc" });
				if (stuinfo != null) {
					for (int i = 0; i < colList.length; i++) {
						map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
					}
					if (xymc != null) {
						map.put("xymc", xymc[0]);
					}
					shsj = null;
					String sjyear = null;
					String sjmon = null;
					String sjday = null;
					String sjhour = null;
					if (null != map.get("shsj")) {
						String sj = map.get("shsj").toString();
						sjyear = sj.substring(0, 4);
						sjmon = sj.substring(4, 6);
						sjday = sj.substring(6, 8);
						sjhour = sj.substring(8, 10);
						shsj = sjyear + "年" + sjmon + "月" + sjday + "日"
								+ sjhour + "时";
						map.put("shsj", shsj);
					}
					if (null != map.get("fdyshsj")) {
						String sj = map.get("fdyshsj").toString();
						sjyear = sj.substring(0, 4);
						sjmon = sj.substring(4, 6);
						sjday = sj.substring(6, 8);
						sjhour = sj.substring(8, 10);
						shsj = sjyear + "年" + sjmon + "月" + sjday + "日"
								+ sjhour + "时";
						map.put("fdyshsj", shsj);
					}
				}
			}
		}
		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			return mapping.findForward("false");
		} else if ("shenhe".equalsIgnoreCase(doType)) {
			// 查询数据
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from jygl_grjlb where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			sql = "select xymc from view_xsjbxx where xh=?";
			String[] xymc = dao.getOneRs(sql, new String[] { pkValue },
					new String[] { "xymc" });
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				map.put("xymc", xymc[0]);
				String shsj = null;
				String fbsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				if (null != map.get("shsj")) {
					String sj = map.get("shsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					shsj = sjyear + "年" + sjmon + "月" + sjday + "日" + sjhour
							+ "时";
					map.put("shsj", shsj);
				}
				if (null != map.get("fbsj")) {
					String sj = map.get("fbsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					fbsj = sjyear + "年" + sjmon + "月" + sjday + "日" + sjhour
							+ "时";
					map.put("fbsj", fbsj);
				}
				if (null != map.get("fdyshsj")) {
					String sj = map.get("fdyshsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					fbsj = sjyear + "年" + sjmon + "月" + sjday + "日" + sjhour
							+ "时";
					map.put("fdyshsj", fbsj);
				}
			}
		}

		String usertype = request.getSession().getAttribute("userType")
				.toString();
		String bbmc = request.getSession().getAttribute("bmmc").toString();
		if ("xx".equals(usertype) || "admin".equals(usertype)) {
			request.setAttribute("ldgxusertype", "xx");
		} else if ("xy".equals(usertype)) {
			request.setAttribute("ldgxusertype", "xy");
			map.put("xymc", bbmc);
		}

		request.setAttribute("rs", map);// 发送数据集
		return mapping.findForward("success");
	}

	// 个人简历修改
	private ActionForward grjlUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String pk = "xsxh";
		String pkValue = request.getParameter("pkValue");
		String tableName = "jygl_grjlb";
		String doType = request.getParameter("doType");
		String xsxh = request.getParameter("xsxh");
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();

		sql = "select zzmmdm,zzmm from dmk_zzmm"; // 政治面貌
		List zzmmList = dao.getList(sql, new String[] {}, new String[] {
				"zzmmdm", "zzmm" });
		request.setAttribute("zzmmList", zzmmList);

		if ("update".equals(doType)) {
			String idsee = request.getParameter("idsee"); // 身份证是否保密
			String zzmm = DealString.toGBK(request.getParameter("zzmm")); // 政治面貌9
			String fxzymc = DealString.toGBK(request.getParameter("fxzymc")); // 辅修专业名称11
			String lxdz = DealString.toGBK(request.getParameter("lxdz")); // 联系地址13
			String lxdh = request.getParameter("lxdh"); // 联系电话14
			String yzbm = request.getParameter("yzbm"); // 邮政编码15
			String email = request.getParameter("email"); // 电子邮箱16
			String hjqk = DealString.toGBK(request.getParameter("hjqk")); // 获奖情况17
			String xxqk = DealString.toGBK(request.getParameter("xxqk")); // 学习情况18
			String xjysjl = DealString.toGBK(request.getParameter("xjysjl")); // 校级以上奖励19
			String shsjqk = DealString.toGBK(request.getParameter("shsjqk")); // 社会实践情况20
			String gzjl = DealString.toGBK(request.getParameter("gzjl")); // 工作经历21
			String grtc = DealString.toGBK(request.getParameter("grtc")); // 个人特长22
			String zwtj = DealString.toGBK(request.getParameter("zwtj")); // 个人推荐23
			String xxtj = DealString.toGBK(request.getParameter("xxtj")); // 学校推荐24
			String fbsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 发布时间25
							// //
							// 取至数据库临时表
							new String[] {}, new String[] { "sdate" }))[0];
			boolean judge = false;
			judge = StandardOperation.update(tableName, new String[] { "idsee",
					"zzmm", "fxzymc", "lxdz", "lxdh", "yzbm", "email", "hjqk",
					"xxqk", "xjysjl", "shsjqk", "gzjl", "grtc", "zwtj", "xxtj",
					"fbsj" }, new String[] { idsee, zzmm, fxzymc, lxdz, lxdh,
					yzbm, email, hjqk, xxqk, xjysjl, shsjqk, gzjl, grtc, zwtj,
					xxtj, fbsj }, pk, xsxh, request);
			if (judge) {
				request.setAttribute("update", "ok");

				// 修改操作的记录
				HttpSession session = request.getSession();
				String userName = session.getAttribute("userName").toString();
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"修改", "个人简历表", "学号:" + xsxh, whensj }, request);
			} else {
				request.setAttribute("update", "no");
			}
		}

		if ("".equals(pkValue) || pkValue == null) {
			pkValue = xsxh;
		}
		sql = "select * from " + tableName + " where xsxh=?";
		String[] colulist = dao.getColumnName("select * from " + tableName
				+ " where 1=2");
		String[] grjlinfo = dao.getOneRs(sql, new String[] { pkValue },
				colulist);
		sql = "select xymc from view_xsjbxx where xh=?";
		String[] xymc = dao.getOneRs(sql, new String[] { pkValue },
				new String[] { "xymc" });
		if (grjlinfo != null) {
			for (int i = 0; i < colulist.length; i++) {
				map.put(colulist[i].toLowerCase(), grjlinfo[i]); // 将记录循环放入map中
			}
			map.put("xymc", xymc[0]);
			String shsj = null;
			String fbsj = null;
			String sjyear = null;
			String sjmon = null;
			String sjday = null;
			String sjhour = null;
			if (null != map.get("shsj")) {
				String sj = map.get("shsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				shsj = sjyear + "年" + sjmon + "月" + sjday + "日" + sjhour + "时";
				map.put("shsj", shsj);
			}
			if (null != map.get("fbsj")) {
				String sj = map.get("fbsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				fbsj = sjyear + "年" + sjmon + "月" + sjday + "日" + sjhour + "时";
				map.put("fbsj", fbsj);
			}
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 个人简历结果查询
	private ActionForward grjlResultQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		String pk = "xsxh";
		String[] colList = null;
		String[] colListCN = null;
		ArrayList<Object> rs = new ArrayList<Object>(); // 获得记录
		String rsNum = "0";
		String tableName = "jygl_grjlb";
		List topTr = null;
		HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();

		if (userType.equals("teacher")) {
			sql = "select " + pk + " 主键,rownum 行号,a.* from " + tableName
					+ " a " + " where SHPERSON='" + userName + "'";
			colList = new String[] { "主键", "行号", "name", "xsxh", "xb", "xl",
					"zymc", "fbsj", "readtimes", "xxsh" };
			colListCN = dao.getColumnNameCN(colList, tableName);
			topTr = dao.arrayToList(colList, colListCN);
			if ((request.getParameter("act") != null)
					&& request.getParameter("act").equalsIgnoreCase("go")) {
				rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
				if (rs == null) {
					rsNum = "0";
				} else {
					rsNum = String.valueOf(rs.size());
				}
			}
			request.setAttribute("ableToView", "teacher");
		}

		if (userType.equals("student")) {
			sql = "select " + pk + " 主键,rownum 行号,a.* from " + tableName
					+ " a " + " where xsxh=" + userName;
			colList = new String[] { "主键", "行号", "name", "xsxh", "xb", "xl",
					"zymc", "fbsj", "xxsh" };
			colListCN = dao.getColumnNameCN(colList, tableName);
			topTr = dao.arrayToList(colList, colListCN);
			if ((request.getParameter("act") != null)
					&& request.getParameter("act").equalsIgnoreCase("go")) {
				rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
				if (rs == null) {
					rsNum = "0";
				} else {
					rsNum = String.valueOf(rs.size());
				}
			}
			request.setAttribute("ableToView", "student");
		}

		map.put("userName", userName);
		request.setAttribute("rs1", map);
		if (rs == null || rs.size() == 0) {
			String msg = "无登记数据，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		request.setAttribute("rs", rs); // 发送数据集
		request.setAttribute("topTr", topTr); // 发送表头
		request.setAttribute("rsNum", rsNum); // 发送记录数
		return mapping.findForward("success");
	}

	// 招聘信息发布
	private ActionForward zpxxfb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {

		String sql = "";
		DAO dao = DAO.getInstance();
		String tableName = "jygl_zpxxb";
		HashMap<String, String> map = new HashMap<String, String>();
		String act = request.getParameter("act");
		sql = "select hyfldm,hyfl from dmk_hyfl"; // 行业分类
		List hyflList = dao.getList(sql, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);

		if ("save".equals(act)) {
			String zpzw = DealString.toGBK(request.getParameter("zpzw"));
			String gsmc = DealString.toGBK(request.getParameter("gsmc"));
			String email = request.getParameter("email");
			String lxdh = request.getParameter("lxdh");
			String gzdd = DealString.toGBK(request.getParameter("gzdd")); // 工作地点
			String zprs = DealString.toGBK(request.getParameter("zprs"));
			String hyfl = DealString.toGBK(request.getParameter("hyfl"));
			String wyyq = DealString.toGBK(request.getParameter("wyyq"));
			String syxs = request.getParameter("syxs");
			String zzxs = DealString.toGBK(request.getParameter("zzxs"));
			String xlyq = DealString.toGBK(request.getParameter("xlyq"));
			String gwzz = DealString.toGBK(request.getParameter("gwzz"));
			String zwyq = DealString.toGBK(request.getParameter("zwyq"));
			String gsjj = DealString.toGBK(request.getParameter("gsjj"));
			String msxd = DealString.toGBK(request.getParameter("msxd"));
			String msdd = DealString.toGBK(request.getParameter("msdd"));
			String yddh = DealString.toGBK(request.getParameter("yddh"));
			String lxr = DealString.toGBK(request.getParameter("lxr"));
			String gswz = DealString.toGBK(request.getParameter("gswz"));
			String cz = DealString.toGBK(request.getParameter("cz"));
			String zphfs = DealString.toGBK(request.getParameter("zphfs"));
			String zpsj = DealString.toGBK(request.getParameter("zpsj"));
			String zpdd = DealString.toGBK(request.getParameter("zpdd"));
			String mssj = "";
			String day = DealString.toGBK(request.getParameter("day"));
			String hour = request.getParameter("hour");
			String min = request.getParameter("min");
			if (Base.isNull(day)) {
				mssj = "";
			} else if (Base.isNull(hour)) {
				mssj = day;
			} else if (Base.isNull(min)) {
				mssj = day + " " + hour;
			} else {
				mssj = day + " " + hour + ":" + min;
			}
			String fbsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 发布时间
							// //
							// 取至数据库临时表
							new String[] {}, new String[] { "sdate" }))[0];

			boolean judge = false;

			judge = StandardOperation.insert(tableName, new String[] { "zpzw",
					"gsmc", "email", "lxdh", "gzdd", "zprs", "hyfl", "wyyq",
					"syxs", "zzxs", "xlyq", "mssj", "gwzz", "zwyq", "gsjj",
					"fbsj", "msxd", "msdd", "yddh", "lxr", "gswz", "cz",
					"zphfs", "zpsj", "zpdd" }, new String[] { zpzw, gsmc,
					email, lxdh, gzdd, zprs, hyfl, wyyq, syxs, zzxs, xlyq,
					mssj, gwzz, zwyq, gsjj, fbsj, msxd, msdd, yddh, lxr, gswz,
					cz, zphfs, zpsj, zpdd }, request);
			if (judge) {
				request.setAttribute("insert", "ok");

				// 增加操作的记录
				HttpSession session = request.getSession();
				String userName = session.getAttribute("userName").toString();
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"增加", "招聘信息表",
								"公司名称:" + gsmc + " 招聘职位:" + zpzw, whensj },
						request);
			} else {
				request.setAttribute("insert", "no");
			}

		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 招聘信息查询(删除，查询)

	private ActionForward zpxxquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {

		DAO dao = DAO.getInstance();
		String sql = "";
		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String tableName = "jygl_zpxxb";
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");

		sql = "select gsmc,zpzw from jygl_zpxxb where rowid=?";
		String pkval = request.getParameter("pkValue");
		String[] zpinfo = dao.getOneRs(sql, new String[] { pkval },
				new String[] { "gsmc", "zpzw" });

		if ("teacher".equals(userType)) {
			request.setAttribute("who", "teacher");
		}
		if ("del".equals(doType2)) {
			// 删除数据
			boolean judge = false;
			String pkValue = request.getParameter("pkValue");
			pkValue = pkValue.replaceAll(" ", "+");
			sql = "delete from " + tableName + " where rowid='" + pkValue + "'";
			judge = dao.runUpdate(sql, new String[] {});
			if (judge) {
				request.setAttribute("delete", "ok");

				// 删除操作的记录

				HttpSession session = request.getSession();
				String userName = session.getAttribute("userName").toString();
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

				judge = StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"删除", "招聘信息表",
								"公司名称:" + zpinfo[0] + " 招聘职位:" + zpinfo[1],
								whensj }, request);
			} else {
				request.setAttribute("delete", "no");
			}
		}

		sql = "select hyfldm,hyfl from dmk_hyfl"; // 行业分类
		List hyflList = dao.getList(sql, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);

		sql = "select dwdqdm,dwdq from dmk_dwdq"; // 工作地点
		List dwdqList = dao.getList(sql, new String[] {}, new String[] {
				"dwdqdm", "dwdq" });
		request.setAttribute("dwdqList", dwdqList);

		String rsNum = "0"; // 返回的记录数
		String zpzw = DealString.toGBK(request.getParameter("zpzw")); // 招聘职位
		String gsmc = DealString.toGBK(request.getParameter("gsmc")); // 公司名称
		String gzdd = DealString.toGBK(request.getParameter("gzdd")); // 工作地点
		String hyfl = DealString.toGBK(request.getParameter("hyfl")); // 行业分类
		String zzxs = DealString.toGBK(request.getParameter("zzxs")); // 转正薪水
		String xlyq = DealString.toGBK(request.getParameter("xlyq")); // 学历要求

		// 关于发布时间的处理 用于查询条件
		String xjsj = DealString.toGBK(request.getParameter("xjsj")); // 相距时间

		String nowsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 发布时间
				// //
				// 取至数据库临时表
				new String[] {}, new String[] { "sdate" }))[0];
		String sjyear = nowsj.substring(0, 4);
		String sjmonth = nowsj.substring(4, 6);
		String sjday = nowsj.substring(6, 8);
		String sjqt = nowsj.substring(8, 14);
		String nowsj1 = sjyear + "-" + sjmonth + "-" + sjday;
		dealDate dealdate = new dealDate();
		String beforesj = "";
		if (!"".equals(xjsj)) {
			beforesj = dealdate.getDate2(Integer.parseInt(xjsj), nowsj1);
			beforesj = beforesj.replaceAll("-", "") + sjqt;
		}

		String querry = " where 1=1 ";
		HashMap<String, String> map = new HashMap<String, String>();

		if ("query".equals(doType)) {
			map.put("zpzw", zpzw);
			map.put("gsmc", gsmc);
			map.put("gzdd", gzdd);
			map.put("hyfl", hyfl);
			map.put("zzxs", zzxs);
			map.put("xlyq", xlyq);
			map.put("xjsj", xjsj);
		}

		if (zpzw == null) {
			zpzw = "";
		}
		if (gsmc == null) {
			gsmc = "";
		}
		if (gzdd == null) {
			gzdd = "";
		}
		if (hyfl == null) {
			hyfl = "";
		}
		if (zzxs == null) {
			zzxs = "";
		}
		if (xlyq == null) {
			xlyq = "";
		}
		if (xjsj == null) {
			xjsj = "";
		}
		if ((zpzw == null) || zpzw.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and zpzw like '%" + zpzw + "%' ";
		}
		if ((gsmc == null) || gsmc.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and gsmc like '%" + gsmc + "%' ";
		}
		if ((gzdd == null) || gzdd.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and gzdd like '%" + gzdd + "%' ";
		}
		if ((hyfl == null) || hyfl.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and hyfl = '" + hyfl + "' ";
		}
		if ((zzxs == null) || zzxs.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and zzxs = '" + zzxs + "' ";
		}
		if ((xlyq == null) || xlyq.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xlyq = '" + xlyq + "' ";
		}
		if ((xjsj == null) || xjsj.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and (fbsj between '" + beforesj + "' and '" + nowsj
					+ "') ";
		}

		sql = "select  rownum 行号,a.* ,a.rowid from " + tableName + " a "
				+ querry;
		colList = new String[] { "行号", "gsmc", "zpzw", "gzdd", "xlyq", "fbsj",
				"readtimes" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		colList = new String[] { "rowid", "行号", "gsmc", "zpzw", "gzdd", "xlyq",
				"fbsj", "readtimes" };
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("rs1", map);// 把查询条件发回去
		return mapping.findForward("success");
	}

	// 招聘信息详细查看
	private ActionForward zpxxViewMoreinfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = "rowid";
		String sql = ""; // sql语句
		DAO dao = DAO.getInstance();
		String realTable = "jygl_zpxxb";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		pkValue = pkValue.replaceAll(" ", "+");
		HashMap<String, String> map = new HashMap<String, String>();

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// 查询数据
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from jygl_zpxxb where 1=2"); // 返回列名数组
			String[] zpxxinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (zpxxinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), zpxxinfo[i]); // 将记录循环放入map中
				}
				String fbsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				String sjmin = null;
				String sjsec = null;
				String sj = map.get("fbsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				sjmin = sj.substring(10, 12);
				sjsec = sj.substring(12, 14);

				fbsj = sjyear + "年" + sjmon + "月" + sjday + "日" + " " + sjhour
						+ ":" + sjmin + ":" + sjsec;
				map.put("fbsj", fbsj);
				String mssj = map.get("mssj");
				if (mssj != null) {
					if (mssj.length() < 13 && mssj.length() > 9) {
						mssj = mssj.substring(0, 10);
						map.put("mssj", mssj);
					} else if (mssj.length() < 9) {
						mssj = "";
						map.put("mssj", mssj);
					}
				}
				sql = "select readtimes from " + realTable + " where rowid=?";
				String[] readtimes = dao.getOneRs(sql,
						new String[] { pkValue }, new String[] { "readtimes" });
				int timeint = Integer.parseInt(readtimes[0]);
				timeint += 1;
				String tinestr = String.valueOf(timeint);
				StandardOperation.update(realTable,
						new String[] { "readtimes" }, new String[] { tinestr },
						pk, pkValue, request);
			}
		}
		request.setAttribute("rs", map);// 发送数据集
		return mapping.findForward("success");
	}

	// 招聘信息修改
	private ActionForward zpxxupdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String tableName = "jygl_zpxxb";
		String rowid = request.getParameter("rowid");
		rowid = rowid.replaceAll(" ", "+");
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();
		String act = request.getParameter("act");

		sql = "select * from jygl_zpxxb where rowid=?";
		String[] colList = dao
				.getColumnName("select * from jygl_zpxxb where 1=2"); // 返回列名数组
		String[] zpxxinfo = dao.getOneRs(sql, new String[] { rowid }, colList);
		if (zpxxinfo != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), zpxxinfo[i]); // 将记录循环放入map中
			}
			String fbsj = null;
			String sjyear = null;
			String sjmon = null;
			String sjday = null;
			String sjhour = null;
			String sjmin = null;
			String sjsec = null;
			String sj = map.get("fbsj").toString();
			sjyear = sj.substring(0, 4);
			sjmon = sj.substring(4, 6);
			sjday = sj.substring(6, 8);
			sjhour = sj.substring(8, 10);
			sjmin = sj.substring(10, 12);
			sjsec = sj.substring(12, 14);

			fbsj = sjyear + "年" + sjmon + "月" + sjday + "日" + " " + sjhour
					+ ":" + sjmin + ":" + sjsec;
			map.put("fbsj", fbsj);
			map.put("rowid", rowid);

			String mssj = map.get("mssj");
			if (mssj != null) {
				if (mssj.length() > 9) {
					String day = mssj.substring(0, 10);
					map.put("day", day);
				}
				if (mssj.length() < 9) {
					mssj = "";
					map.put("mssj", mssj);
				}
				if (mssj.length() > 12) {
					String hour = mssj.substring(11, 13);
					map.put("hour", hour);
					String min = mssj.substring(14, 16);
					map.put("min", min);
				}
			}
		}
		request.setAttribute("rs", map);

		sql = "select hyfldm,hyfl from dmk_hyfl"; // 行业分类
		List hyflList = dao.getList(sql, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);

		if ("update".equals(act)) {
			String zpzw = DealString.toGBK(request.getParameter("zpzw"));
			String gsmc = DealString.toGBK(request.getParameter("gsmc"));
			String email = request.getParameter("email");
			if ("null@null".equals(email)) {
				email = "";
			}
			String lxdh = request.getParameter("lxdh");
			String gzdd = DealString.toGBK(request.getParameter("gzdd")); // 工作地点
			String zprs = DealString.toGBK(request.getParameter("zprs"));
			String hyfl = DealString.toGBK(request.getParameter("hyfl"));
			String wyyq = DealString.toGBK(request.getParameter("wyyq"));
			String syxs = DealString.toGBK(request.getParameter("syxs"));
			String zzxs = DealString.toGBK(request.getParameter("zzxs"));
			String xlyq = DealString.toGBK(request.getParameter("xlyq"));
			String mssj = DealString.toGBK(request.getParameter("day")) + " "
					+ request.getParameter("hour") + ":"
					+ request.getParameter("min");
			String msxd = DealString.toGBK(request.getParameter("msxd"));
			String msdd = DealString.toGBK(request.getParameter("msdd"));
			String gwzz = DealString.toGBK(request.getParameter("gwzz"));
			String zwyq = DealString.toGBK(request.getParameter("zwyq"));
			String gsjj = DealString.toGBK(request.getParameter("gsjj"));
			boolean judge = false;
			judge = StandardOperation.update(tableName, new String[] { "zpzw",
					"gsmc", "email", "lxdh", "gzdd", "zprs", "hyfl", "wyyq",
					"syxs", "zzxs", "xlyq", "mssj", "msxd", "msdd", "gwzz",
					"zwyq", "gsjj" }, new String[] { zpzw, gsmc, email, lxdh,
					gzdd, zprs, hyfl, wyyq, syxs, zzxs, xlyq, mssj, msxd, msdd,
					gwzz, zwyq, gsjj }, "rowid", rowid, request);
			if (judge) {
				request.setAttribute("update", "ok");

				// 修改操作的记录
				HttpSession session = request.getSession();
				String userName = session.getAttribute("userName").toString();
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"修改", "招聘信息表",
								"公司名称:" + gsmc + " 招聘职位:" + zpzw, whensj },
						request);
			} else {
				request.setAttribute("update", "no");
			}

			sql = "select * from jygl_zpxxb where rowid=?";
			String[] colList1 = dao
					.getColumnName("select * from jygl_zpxxb where 1=2"); // 返回列名数组
			String[] zpxxinfo1 = dao.getOneRs(sql, new String[] { rowid },
					colList1);
			if (zpxxinfo1 != null) {
				for (int i = 0; i < colList1.length; i++) {
					map.put(colList1[i].toLowerCase(), zpxxinfo1[i]); // 将记录循环放入map中
				}
				String fbsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				String sjmin = null;
				String sjsec = null;
				String sj = map.get("fbsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				sjmin = sj.substring(10, 12);
				sjsec = sj.substring(12, 14);

				fbsj = sjyear + "年" + sjmon + "月" + sjday + "日" + " " + sjhour
						+ ":" + sjmin + ":" + sjsec;
				map.put("fbsj", fbsj);

				mssj = map.get("mssj");
				if (mssj != null) {
					if (mssj.length() > 9) {
						String day = mssj.substring(0, 10);
						map.put("day", day);
					}
					if (mssj.length() < 9) {
						mssj = "";
						map.put("mssj", mssj);
					}
					if (mssj.length() > 12) {
						String hour = mssj.substring(11, 13);
						map.put("hour", hour);
						String min = mssj.substring(14, 16);
						map.put("min", min);
					}
				}
			}
			request.setAttribute("rs", map);
		}
		return mapping.findForward("success");
	}

	// 投送简历
	private ActionForward zpxxSendGrjl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String sql = ""; // sql语句
		DAO dao = DAO.getInstance();
		String realTable = "jygl_zpxxb";
		String tableName = "JYGL_ZPXXGLB";
		String doType = request.getParameter("doType");
		String gsmc = DealString.toGBK(request.getParameter("gsmc"));
		String zpzw = DealString.toGBK(request.getParameter("zpzw"));

		HashMap<String, String> map = new HashMap<String, String>();
		String act = request.getParameter("act");
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();

		if ("send".equals(act)) {

			sql = "select * from jygl_grjlb where xsxh=?";
			String[] stuinfo = dao.getOneRs(sql, new String[] { userName },
					new String[] { "xsxh" });
			if (null == stuinfo) {
				request.setAttribute("havegrjl", "no");
			} else {
				gsmc = DealString.toGBK(request.getParameter("gsmc"));
				zpzw = DealString.toGBK(request.getParameter("zpzw"));
				String tjsj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 发布时间
								// 取至数据库临时表
								new String[] {}, new String[] { "sdate" }))[0];

				sql = "select * from " + tableName
						+ " where gsmc=? and zpzw=? and xsxh=?";
				String[] zpinfo = dao.getOneRs(sql, new String[] { gsmc, zpzw,
						userName }, new String[] { "gsmc", "zpzw", "xsxh" });
				if (null != zpinfo) {
					request.setAttribute("insert", "no");
				} else {
					boolean judge = false;
					judge = StandardOperation.insert(tableName, new String[] {
							"gsmc", "zpzw", "xsxh", "tjsj" }, new String[] {
							gsmc, zpzw, userName, tjsj }, request);
					if (judge) {
						request.setAttribute("send", "ok");
					} else {
						request.setAttribute("send", "no");
					}
				}
			}
		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// 查询数据
			sql = "select * from " + realTable + " where gsmc='" + gsmc
					+ "' and zpzw='" + zpzw + "'";
			String[] colList = dao
					.getColumnName("select * from jygl_zpxxb where 1=2"); // 返回列名数组
			String[] zpxxinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (zpxxinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), zpxxinfo[i]); // 将记录循环放入map中
				}
				String fbsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				String sjmin = null;
				String sjsec = null;
				String sj = map.get("fbsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				sjmin = sj.substring(10, 12);
				sjsec = sj.substring(12, 14);

				fbsj = sjyear + "年" + sjmon + "月" + sjday + "日" + " " + sjhour
						+ ":" + sjmin + ":" + sjsec;
				map.put("fbsj", fbsj);
				String mssj;
				if (Base.isNull(map.get("mssj"))) {
					mssj = "";
				} else {
					mssj = map.get("mssj");
				}
				if (mssj.length() < 13 && mssj.length() > 9) {
					mssj = mssj.substring(0, 10);
					map.put("mssj", mssj);
				} else if (mssj.length() < 9) {
					mssj = "";
					map.put("mssj", mssj);
				}
				sql = "select xxsh from JYGL_GRJLB where xsxh=?";
				String[] stuinfo = dao.getOneRs(sql, new String[] { userName },
						new String[] { "xxsh" });
				if (stuinfo != null) {
					map.put("xxsh", stuinfo[0]);
				}
			}
		}
		request.setAttribute("rs", map);// 发送数据集
		return mapping.findForward("success");
	}

	// 企业查看个人简历页面打开
	private ActionForward zpxxResultQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		String[] colList = null;
		String[] colListCN = null;
		ArrayList<Object> rs = new ArrayList<Object>(); // 获得记录
		String rsNum = "0";
		String tableName = "JYGL_ZPXXGLB";
		List topTr = null;
		HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String gsmc = "";
		String doType = request.getParameter("doType");

		if ("del".equals(doType)) {
			gsmc = DealString.toGBK(request.getParameter("gsmc"));
			String zpzw = DealString.toGBK(request.getParameter("zpzw"));
			String xsxh = request.getParameter("xsxh");

			boolean judge = false;
			sql = "delete from JYGL_ZPXXGLB where gsmc=? and zpzw=? and xsxh=?";
			judge = dao.runUpdate(sql, new String[] { gsmc, zpzw, xsxh });
			if (judge) {
				request.setAttribute("delete", "ok");

				// 删除操作的记录
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

				judge = StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"删除", "招聘信息管理表",
								"公司名称:" + gsmc + " 职位:" + zpzw + " 学号:" + xsxh,
								whensj }, request);

			} else {
				request.setAttribute("delete", "no");
			}
		}

		if (userType.equals("teacher")) {
			sql = "select gsmc from jygl_jyzpwhb where username=?";
			String[] userinfo = dao.getOneRs(sql, new String[] { userName },
					new String[] { "gsmc" });
			if (userinfo != null) {
				gsmc = userinfo[0];
			}

			if (!"".equals(gsmc)) {
				sql = "select rownum 行号,a.* from " + tableName + " a "
						+ " where gsmc='" + gsmc + "'";
				colList = new String[] { "行号", "gsmc", "zpzw", "xsxh", "tjsj" };
				colListCN = dao.getColumnNameCN(colList, tableName);
				topTr = dao.arrayToList(colList, colListCN);
				if ((request.getParameter("act") != null)
						&& request.getParameter("act").equalsIgnoreCase("go")) {
					rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
					if (rs == null) {
						rsNum = "0";
					} else {
						rsNum = String.valueOf(rs.size());
					}
				}
			}
		}

		map.put("userName", userName);
		request.setAttribute("rs1", map);
		request.setAttribute("rs", rs); // 发送数据集
		request.setAttribute("topTr", topTr); // 发送表头
		request.setAttribute("rsNum", rsNum); // 发送记录数
		return mapping.findForward("success");
	}

	// 企业点击查看个人简历详细信息
	private ActionForward zpxxViewMoreGrjlInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = "xsxh";
		String sql = ""; // sql语句
		DAO dao = DAO.getInstance();
		String realTable = "jygl_grjlb";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("xsxh");
		HashMap<String, String> map = new HashMap<String, String>();

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// 查询数据
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from jygl_grjlb where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			sql = "select xymc from view_xsjbxx where xh=?";
			String[] xymc = dao.getOneRs(sql, new String[] { pkValue },
					new String[] { "xymc" });
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				map.put("xymc", xymc[0]);
				String fbsj = null;
				String shsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				// 发布时间
				String sj = map.get("fbsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				fbsj = sjyear + "年" + sjmon + "月" + sjday + "日" + sjhour + "时";
				map.put("fbsj", fbsj);
				// 审核时间
				if (null != map.get("shsj")) {
					sj = map.get("shsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					shsj = sjyear + "年" + sjmon + "月" + sjday + "日" + sjhour
							+ "时";
					map.put("shsj", shsj);
				}
				if ("no".equals(map.get("idsee"))) {
					map.put("id", "");
				}
				if ("不通过X".equals(map.get("xxsh"))
						|| "未审核".equals(map.get("xxsh"))) {
					HashMap<String, String> map1 = new HashMap<String, String>();
					map = map1;
					request.setAttribute("notice", "no");
				}
				sql = "select readtimes from " + realTable + " where xsxh=?";
				String[] readtimes = dao.getOneRs(sql,
						new String[] { pkValue }, new String[] { "readtimes" });
				int timeint = Integer.parseInt(readtimes[0]);
				timeint += 1;
				String tinestr = String.valueOf(timeint);
				StandardOperation.update(realTable,
						new String[] { "readtimes" }, new String[] { tinestr },
						pk, pkValue, request);
			}
		}
		request.setAttribute("rs", map);// 发送数据集
		return mapping.findForward("success");
	}

	// 企业意见反馈-保存
	private ActionForward zpxxReturnMess(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) throws Exception {
		DAO dao = DAO.getInstance();
		String tableName = "jygl_qyyjfkb";
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "";
		String gsmc = "";
		String doType = request.getParameter("doType");

		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String user = session.getAttribute("userType").toString();
		sql = "select gsmc from jygl_jyzpwhb where username=?";
		String[] gsinfo = dao.getOneRs(sql, new String[] { userName },
				new String[] { "gsmc" });
		if (gsinfo != null) {
			gsmc = gsinfo[0];
			request.setAttribute("updategsmc", "no");
		} else if ("admin".equals(user)) {
			request.setAttribute("updategsmc", "ok");
		}
		map.put("gsmc", gsmc);

		if ("save".equals(doType)) {
			gsmc = DealString.toGBK(request.getParameter("gsmc"));
			String dwxz = DealString.toGBK(request.getParameter("dwxz")); // 单位性质
			String hyfl = DealString.toGBK(request.getParameter("hyfl")); // 行业分类
			String zyzs1 = DealString.toGBK(request.getParameter("zyzs1")); // 专业知识1
			String zyzs2 = DealString.toGBK(request.getParameter("zyzs2")); // 专业知识2
			String gzbx1 = DealString.toGBK(request.getParameter("gzbx1")); // 工作表现1
			String gzbx2 = DealString.toGBK(request.getParameter("gzbx2")); // 工作表现2
			String jnjq1 = DealString.toGBK(request.getParameter("jnjq1")); // 技能技巧1
			String jnjq2 = DealString.toGBK(request.getParameter("jnjq2")); // 技能技巧2
			String jnjq3 = DealString.toGBK(request.getParameter("jnjq3")); // 技能技巧3
			String jnjq4 = DealString.toGBK(request.getParameter("jnjq4")); // 技能技巧4
			String jnjq5 = DealString.toGBK(request.getParameter("jnjq5")); // 技能技巧5
			String myd = DealString.toGBK(request.getParameter("myd")); // 总体满意度
			String yjfkbt = DealString.toGBK(request.getParameter("yjfkbt"));// 意见反馈标题
			String yjfknr = DealString.toGBK(request.getParameter("yjfknr"));// 意见反馈内容
			String fksj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
							new String[] {}, new String[] { "sdate" }))[0]; // 提交时间
			String fknd = fksj.substring(0, 4); // 反馈年度

			boolean judge = false;
			judge = StandardOperation.insert(tableName, new String[] { "gsmc",
					"dwxz", "hyfl", "zyzs1", "zyzs2", "gzbx1", "gzbx2",
					"jnjq1", "jnjq2", "jnjq3", "jnjq4", "jnjq5", "myd", "fksj",
					"fknd", "yjfkbt", "yjfknr" }, new String[] { gsmc, dwxz,
					hyfl, zyzs1, zyzs2, gzbx1, gzbx2, jnjq1, jnjq2, jnjq3,
					jnjq4, jnjq5, myd, fksj, fknd, yjfkbt, yjfknr }, request);
			if (judge) {
				request.setAttribute("insert", "ok");

				// 增加操作的记录
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"增加", "企业意见反馈表", "公司名称:" + gsmc, whensj },
						request);

			} else {
				request.setAttribute("insert", "no");
			}
		}

		sql = "select dwxzdm,dwxz from dmk_dwxz2"; // 单位性质代码2
		List dwxzdm2List = dao.getList(sql, new String[] {}, new String[] {
				"dwxzdm", "dwxz" });
		request.setAttribute("dwxzdm2List", dwxzdm2List);

		sql = "select hyfldm,hyfl from dmk_hyfl"; // 行业分类
		List hyflList = dao.getList(sql, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 企业反馈信息查询
	private ActionForward zpxxReturnMessQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) throws Exception {

		DAO dao = DAO.getInstance();
		String sql = "";
		String rsNum = "0";
		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String tableName = "jygl_qyyjfkb";
		String gsmc = DealString.toGBK(request.getParameter("gsmc"));
		String myd = DealString.toGBK(request.getParameter("myd"));
		String dwxz = DealString.toGBK(request.getParameter("dwxz"));
		String hyfl = DealString.toGBK(request.getParameter("hyfl"));
		String fknd = DealString.toGBK(request.getParameter("fknd"));
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");

		sql = "select hyfldm,hyfl from dmk_hyfl"; // 行业分类
		List hyflList = dao.getList(sql, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);

		sql = "select dwxzdm,dwxz from dmk_dwxz2"; // 单位性质
		List dwxzList = dao.getList(sql, new String[] {}, new String[] {
				"dwxzdm", "dwxz" });
		request.setAttribute("dwxzList", dwxzList);

		String querry = " where 1=1 ";
		HashMap<String, String> map = new HashMap<String, String>();

		sql = "select gsmc from jygl_qyyjfkb where rowid=?";
		String pkval = request.getParameter("pkValue");
		String[] yjfkinfo = dao.getOneRs(sql, new String[] { pkval },
				new String[] { "gsmc" });

		if ("del".equals(doType2)) {
			// 删除数据
			boolean judge = false;
			String pkValue = request.getParameter("pkValue");
			pkValue = pkValue.replaceAll(" ", "+");
			sql = "delete from " + tableName + " where rowid='" + pkValue + "'";
			judge = dao.runUpdate(sql, new String[] {});
			if (judge) {
				request.setAttribute("delete", "ok");

				// 删除操作的记录

				HttpSession session = request.getSession();
				String userName = session.getAttribute("userName").toString();
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

				judge = StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" },
						new String[] { userName, "删除", "企业意见反馈表",
								"公司名称:" + yjfkinfo[0], whensj }, request);
			} else {
				request.setAttribute("delete", "no");
			}
		}

		if ("query".equals(doType)) {
			map.put("gsmc", gsmc);
			map.put("myd", myd);
			map.put("hyfl", hyfl);
			map.put("dwxz", dwxz);
			map.put("fknd", fknd);
		}

		if (gsmc == null) {
			gsmc = "";
		}
		if (myd == null) {
			myd = "";
		}
		if (hyfl == null) {
			hyfl = "";
		}
		if (dwxz == null) {
			dwxz = "";
		}
		if (fknd == null) {
			fknd = "";
		}
		if ((gsmc == null) || gsmc.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and gsmc like '%" + gsmc + "%' ";
		}
		if ((myd == null) || myd.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and myd = '" + myd + "' ";
		}
		if ((hyfl == null) || hyfl.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and hyfl = '" + hyfl + "' ";
		}
		if ((dwxz == null) || dwxz.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and dwxz = '" + dwxz + "' ";
		}
		if ((fknd == null) || fknd.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and fknd = '" + fknd + "' ";
		}
		sql = "select  rownum 行号,a.* ,a.rowid from " + tableName + " a "
				+ querry;
		colList = new String[] { "行号", "fknd", "gsmc", "hyfl", "dwxz", "myd",
				"fksj" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		colList = new String[] { "rowid", "行号", "fknd", "gsmc", "hyfl", "dwxz",
				"myd", "fksj" };
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("rs1", map);// 把查询条件发回去
		return mapping.findForward("success");

	}

	// 企业反馈信息查询-查看详细信息
	private ActionForward zpxxReturnMessMoreQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) throws Exception {

		String pk = "rowid";
		String sql = ""; // sql语句
		DAO dao = DAO.getInstance();
		String realTable = "jygl_qyyjfkb";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		pkValue = pkValue.replaceAll(" ", "+");
		HashMap<String, String> map = new HashMap<String, String>();

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// 查询数据
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from jygl_qyyjfkb where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				String fksj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				// 发布时间转换
				String sj = map.get("fksj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				fksj = sjyear + "年" + sjmon + "月" + sjday + "日";
				map.put("fksj", fksj);
			}
		}
		request.setAttribute("rs", map);// 发送数据集
		return mapping.findForward("success");
	}

	/*
	 * 弹出代码库查询器页面
	 */
	private ActionForward turndmkqueryjsp(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String sql = "";
		CommanForm turnForm = new CommanForm();
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = turnForm.getXh();
		if (userType.equalsIgnoreCase("student")) {
			xh = session.getAttribute("userName").toString();
		}
		sql = "select a.*,b.lxdh lxdh1,b.sqyy,b.bz bz1 from view_xsjbxx a left join knssqb b on a.xh=b.xh where a.xh=?";
		String[] colList = dao
				.getColumnName("select a.*,b.lxdh lxdh1,b.sqyy,b.bz bz1 from view_xsjbxx a,knssqb b where a.xh=b.xh and 1=2");
		String[] rs = dao.getOneRs(sql, new String[] { xh }, colList);
		if (rs != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), rs[i]);
			}
		}
		map.put("stuExists", "yes");
		map.put("userType", userType);
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	/*
	 * 代码库查询
	 */
	public ActionForward dmkquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String sjzd = (String) request.getParameter("sjzd");
		String dm = null;
		String mc = null;
		String quesType = (String) request.getParameter("myselect");
		if (!"".equals(request.getParameter("dm"))
				&& request.getParameter("dm") != null) {
			dm = request.getParameter("dm");
		}
		if (!"".equals(request.getParameter("mc"))
				&& request.getParameter("mc") != null) {
			mc = DealString.toGBK(request.getParameter("mc"));
		}
		ByqxDao byqxDao = new ByqxDao(); // 毕业去向
		BzpyfsDao bzgzpyfsDao = new BzpyfsDao(); // 本专培养方式
		BzgzzyDao bzgzzyDao = new BzgzzyDao(); // 本专高职专业
		DwdqDao dwdqDao = new DwdqDao(); // 单位地区
		Dwxz2Dao dwxz2Dao = new Dwxz2Dao(); // 单位性质2
		DwxzDao dwxzDao = new DwxzDao(); // 单位性质
		HyflDao hyflDao = new HyflDao(); // 行业分类
		JzzbzDao jzzbzDao = new JzzbzDao(); // 居住证或蓝表标志位
		SydqDao sydqDao = new SydqDao(); // 生源地区
		SydzgbmDao sydzgdwDao = new SydzgbmDao(); // 生源地主管部门
		YjspyfsDao yjspyfsDao = new YjspyfsDao(); // 研究生培养方式
		YjszyDao yjszyDao = new YjszyDao(); // 研究生专业
		ZgbmDao zgbmDao = new ZgbmDao(); // 主管部门
		XbDao xbDao = new XbDao(); // 性别
		XlDao xlDao = new XlDao(); // 学历
		ZzmmDao zzmmDao = new ZzmmDao(); // 政治面貌
		DqlxDao dqlxDao = new DqlxDao(); // 地区流向
		ArrayList list = new ArrayList();
		String dmkmc = null;
		String rsNum = "0";// 返回的记录数

		if (quesType.equals("2")) {
			if (sjzd.equals("yjszy")) {
				list = yjszyDao.getList(dm, mc);
				dmkmc = "研究生专业代码库";
			} else if (sjzd.equals("bzgzzy")) {
				list = bzgzzyDao.getList(dm, mc);
				dmkmc = "本专高职专业代码库";
			} else if (sjzd.equals("sydq")) {
				list = sydqDao.getList(dm, mc);
				dmkmc = "生源地区代码库";
			} else if (sjzd.equals("yjspyfs")) {
				list = yjspyfsDao.getList(dm, mc);
				dmkmc = "研究生培养方式代码库";
			} else if (sjzd.equals("bzgzpyfs")) {
				list = bzgzpyfsDao.getList(dm, mc);
				dmkmc = "本专培养方式代码库";
			} else if (sjzd.equals("dwdq")) {
				list = dwdqDao.getList(dm, mc);
				dmkmc = "单位地区代码库";
			} else if (sjzd.equals("byqx")) {
				list = byqxDao.getList(dm, mc);
				dmkmc = "毕业去向代码库";
			} else if (sjzd.equals("jzzbz")) {
				list = jzzbzDao.getList(dm, mc);
				dmkmc = "居住证或蓝表标志位代码库";
			} else if (sjzd.equals("sydzgdw")) {
				list = sydzgdwDao.getList(dm, mc);
				dmkmc = "生源地主管部门代码库";
			} else if (sjzd.equals("dwxz")) {
				list = dwxzDao.getList(dm, mc);
				dmkmc = "单位性质代码库";
			} else if (sjzd.equals("dwxz2")) {
				list = dwxz2Dao.getList(dm, mc);
				dmkmc = "单位性质2代码库";
			} else if (sjzd.equals("zgbm")) {
				list = zgbmDao.getList(dm, mc);
				dmkmc = "主管部门代码库";
			} else if (sjzd.equals("hyfl")) {
				list = hyflDao.getList(dm, mc);
				dmkmc = "行业分类代码库";
			} else if (sjzd.equals("xb")) {
				list = xbDao.getList(dm, mc);
				dmkmc = "性别代码库";
			} else if (sjzd.equals("xl")) {
				list = xlDao.getList(dm, mc);
				dmkmc = "学历代码库";
			} else if (sjzd.equals("zzmm")) {
				list = zzmmDao.getList(dm, mc);
				dmkmc = "政治面貌代码库";
			} else if (sjzd.equals("dqlx")) {
				list = dqlxDao.getList(dm, mc);
				dmkmc = "地区流向代码库";
			}

		} else {
			if (sjzd.equals("yjszy")) {
				list = yjszyDao.getListJ(dm, mc);
			} else if (sjzd.equals("bzgzzy")) {
				list = bzgzzyDao.getListJ(dm, mc);
			} else if (sjzd.equals("sydq")) {
				list = sydqDao.getListJ(dm, mc);
			} else if (sjzd.equals("yjspyfs")) {
				list = yjspyfsDao.getListJ(dm, mc);
			} else if (sjzd.equals("bzgzpyfs")) {
				list = bzgzpyfsDao.getListJ(dm, mc);
			} else if (sjzd.equals("dwdq")) {
				list = dwdqDao.getListJ(dm, mc);
			} else if (sjzd.equals("byqx")) {
				list = byqxDao.getListJ(dm, mc);
			} else if (sjzd.equals("jzzbz")) {
				list = jzzbzDao.getListJ(dm, mc);
			} else if (sjzd.equals("sydzgdw")) {
				list = sydzgdwDao.getListJ(dm, mc);
			} else if (sjzd.equals("dwxz")) {
				list = dwxzDao.getListJ(dm, mc);
			} else if (sjzd.equals("dwxz2")) {
				list = dwxz2Dao.getListJ(dm, mc);
			} else if (sjzd.equals("zgbm")) {
				list = zgbmDao.getListJ(dm, mc);
			} else if (sjzd.equals("hyfl")) {
				list = hyflDao.getListJ(dm, mc);
			} else if (sjzd.equals("xb")) {
				list = xbDao.getListJ(dm, mc);
			} else if (sjzd.equals("xl")) {
				list = xlDao.getListJ(dm, mc);
			} else if (sjzd.equals("zzmm")) {
				list = zzmmDao.getListJ(dm, mc);
			} else if (sjzd.equals("dqlx")) {
				list = dqlxDao.getListJ(dm, mc);
			}
		}
		rsNum = String.valueOf(list.size());
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("list", list);
		request.setAttribute("dmkmc", dmkmc);
		return mapping.findForward("success");
	}

	// 为学生信息上报查询数据
	private ActionForward JyglQueryStuInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 数据查询
		CommanForm dataSearchForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userOnLine").toString();
		String userSpecType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		boolean disabled = true;
		if (userType.equalsIgnoreCase("student")) {
			String xh = session.getAttribute("userName").toString();
			return new ActionForward("/stu_info_details.do?xh=" + xh, false);
		}
		DAO dao = DAO.getInstance();
		String act = request.getParameter("act");
		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql语句
		String querry = " where 1=1 ";// sql条件
		String rsNum = "0";// 返回的记录数
		String nj = request.getParameter("nj");
		String xy = request.getParameter("xydm");
		String zy = request.getParameter("zydm");
		String bj = request.getParameter("bjdm");
		String xh = request.getParameter("xh");
		String xm = request.getParameter("xm");
		if (xy == null) {
			xy = "";
		}
		if (zy == null) {
			zy = "";
		}
		if ((nj == null) || nj.equalsIgnoreCase("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and nj = '" + nj + "' ";
		}
		if ((xy == null) || xy.equalsIgnoreCase("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xydm = '" + xy + "' ";
		}
		if ((zy == null) || zy.equalsIgnoreCase("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and zydm = '" + zy + "' ";
		}
		if ((bj == null) || bj.equalsIgnoreCase("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and bjdm = '" + bj + "' ";
		}
		if ((xh == null) || xh.equalsIgnoreCase("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xh = '" + xh + "' ";
		}
		if ((xm == null) || xm.equalsIgnoreCase("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xm like '%" + DealString.toGBK(xm) + "%' ";
			dataSearchForm.setXm(DealString.toGBK(xm));
		}

		colList = new String[] { "行号", "xh", "xm", "xb", "nj", "bjmc" };
		sql = "select rownum 行号,a.* from view_xsjbxx a" + querry;
		colListCN = dao.getColumnNameCN(colList, "view_xsjbxx");
		List topTr = dao.arrayToList(colList, colListCN);
		if ("go".equals(act)) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		if (userSpecType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			dataSearchForm.setXydm(xy);
			disabled = false;
		}
		request.setAttribute("disabled", disabled);
		request.setAttribute("njList", dao.getNjList());// 发送年级列表
		request.setAttribute("xyList", dao.getXyList());// 发送学院列表
		request.setAttribute("zyList", dao.getZyList(xy));// 发送专业列表
		request.setAttribute("bjList", dao.getBjList(xy, zy));// 发送专业列表
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		return mapping.findForward("success");
	}

	// 毕业生信息上报页面的数据获得并保存
	private ActionForward bysxxplsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		return mapping.findForward("success");
	}

	public ActionForward ynysBysxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String[] rs = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String xh = request.getParameter("xh");
		String sql = "";
		StuInfoDAO stuDao = new StuInfoDAO();
		request.setAttribute("njList", dao.getNjList());// 发送年级列表

		sql = "select zzmmdm,zzmm from dmk_zzmm"; // 政治面貌代码
		List zzmmdmList = dao.getList(sql, new String[] {}, new String[] {
				"zzmmdm", "zzmm" });
		request.setAttribute("zzmmdmList", zzmmdmList);

		sql = "select sydqdm,sydq from dmk_sydq"; // 生源地区
		List sydqdmList = dao.getList(sql, new String[] {}, new String[] {
				"sydqdm", "sydq" });
		request.setAttribute("sydqdmList", sydqdmList);

		sql = "select dwxzdm,dwxz from dmk_dwxz"; // 就业意向
		List dwxzdmList = dao.getList(sql, new String[] {}, new String[] {
				"dwxzdm", "dwxz" });
		request.setAttribute("dwxzdmList", dwxzdmList);

		sql = "select byqxdm,byqx from dmk_byqx"; // 就业方式
		List byqxdmList = dao.getList(sql, new String[] {}, new String[] {
				"byqxdm", "byqx" });
		request.setAttribute("byqxdmList", byqxdmList);

		sql = "select distinct zymc from view_njxyzybj";// 专业名称
		List zyList = dao
				.getList(sql, new String[] {}, new String[] { "zymc" });
		request.setAttribute("zyList", zyList);

		sql = "select distinct bjmc from view_njxyzybj";// 班级名称
		List bjList = dao
				.getList(sql, new String[] {}, new String[] { "bjmc" });
		request.setAttribute("bjList", bjList);

		sql = "select mzmc,mzdm from mzdmb";// 民族
		List mzList = dao.getList(sql, new String[] {}, new String[] { "mzmc",
				"mzdm" });
		request.setAttribute("mzList", mzList);

		sql = "select * from view_xsjbxx where XH=?"; // 查询该学号的相关内容的sql语句
		String[] colList = dao
				.getColumnName("select * from view_xsjbxx where 1=2"); // 返回列名数组
		rs = dao.getOneRs(sql, new String[] { xh }, colList); // 获得从毕业生基本信息表（视图）中查询的记录集

		if (rs != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), rs[i]); // 将记录循环放入map中
			}
		}

		if ("save".equals(request.getParameter("doType"))) {
			String xsxh = request.getParameter("xh");
			String name = DealString.toGBK(request.getParameter("xm"));
			String xb = DealString.toGBK(request.getParameter("xb"));
			String nj = request.getParameter("nj");
			String zymc = DealString.toGBK(request.getParameter("zymc"));
			String sydq = request.getParameter("jgs");
			String jgshi = request.getParameter("jgshi");
			String jgx = request.getParameter("jgx");
			String csrq = DealString.toGBK(request.getParameter("csrq"));
			String mz = DealString.toGBK(request.getParameter("mz"));
			String zzmm = DealString.toGBK(request.getParameter("zzmm"));
			String lxdh = request.getParameter("lxdh");
			String nd = request.getParameter("nd");
			String bynd = request.getParameter("bynd");
			String qkqk = DealString.toGBK(request.getParameter("qkqk"));
			String jcqk = DealString.toGBK(request.getParameter("jcqk"));
			String jyyx = DealString.toGBK(request.getParameter("jyyx"));
			String jyfs = DealString.toGBK(request.getParameter("jyfs"));
			String sbsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
							new String[] {}, new String[] { "sdate" }))[0]; // 上报时间

			boolean judge = false;
			judge = StandardOperation.insert("jygl_xsjbxxb", new String[] {
					"xsxh", "name", "xb", "nj", "zymc", "sydqdm", "jgshi",
					"jgx", "csrq", "mz", "zzmm", "lxdh", "nd", "bynd", "qkqk",
					"jcqk", "jyyx", "jyfs", "sbsj" }, new String[] { xsxh,
					name, xb, nj, zymc, sydq, jgshi, jgx, csrq, mz, zzmm, lxdh,
					nd, bynd, qkqk, jcqk, jyyx, jyfs, sbsj }, request);
			if (judge) {
				request.setAttribute("inserted", "ok");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		map.put("1", "1");
		request.setAttribute("rs", map);
		request.setAttribute("realTable", "jygl_xsjbxxb"); // 表是学生基本信息表

		request.setAttribute("shiList", stuDao.getShiList("").get("shiList"));
		request.setAttribute("xianList", stuDao.getShiList("").get("xianList"));
		request.setAttribute("ssList", stuDao.getSsList());
		return mapping.findForward("success2");
	}

	// 毕业生信息上报页面的数据获得并保存
	private ActionForward bysxxapply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		DAO dao = DAO.getInstance();
		String xxdm = Base.xxdm;
		String xxmc = Base.xxmc;
		HttpSession session = request.getSession(); // 获得会话
		String sql = ""; // 生成一个空sql字符串变量
		String xslb = ""; // 学生类别
		String[] rs = null;
		HashMap<String, String> map = new HashMap<String, String>(); // 生成hashmap形式的数组，hashmap型有个好处就是键值对应
		String xh = request.getParameter("xh");

		if (xxdm.equals(Globals.XXDM_YNYS)) {
			return ynysBysxx(mapping, form, request, response);
		}
		String zydm = DealString.toGBK(request.getParameter("zydm"));
		sql = "select sydqdm,sydq from dmk_sydq"; // 生源地区
		List sydqdmList = dao.getList(sql, new String[] {}, new String[] {
				"sydqdm", "sydq" });
		request.setAttribute("sydqdmList", sydqdmList);
		// 专业代码
		String titName = request.getParameter("titName");
		// 表头名
		String dotpye = request.getParameter("doType");
		String act = request.getParameter("act");
		// 是否进行save操作
		String[] pages = null;
		String[] titNames = null;
		if (xxdm.equals(Globals.XXDM_ZJJJZYJSXY)) {
			pages = new String[] { "专科生" };
			titNames = new String[] { "zks" };
		} else {
			pages = new String[] { "专科生", "本科生", "研究生" };
			titNames = new String[] { "zks", "bks", "yjs" };
		}
		List pagesList = dao.arrayToList(titNames, pages); // 把表头信息装入数组
		request.setAttribute("pages", pagesList);
		if (titName == null)
			titName = titNames[0];

		request.setAttribute("titName", titName);

		if (titName == null || titName.equals("zks")) {
			sql = "select pyfsdm,pyfs from dmk_bzpyfs";
			List pyfsList = dao.getList(sql, new String[] {}, new String[] {
					"pyfsdm", "pyfs" });
			request.setAttribute("pyfsList", pyfsList);
		} else if (titName == null || titName.equals("bks")) {
			sql = "select pyfsdm,pyfs from dmk_bzpyfs";
			List pyfsList = dao.getList(sql, new String[] {}, new String[] {
					"pyfsdm", "pyfs" });
			request.setAttribute("pyfsList", pyfsList);
		} else if (titName.equals("yjs")) {
			sql = "select pyfsdm,pyfs from dmk_yjspyfs";
			List pyfsList = dao.getList(sql, new String[] {}, new String[] {
					"pyfsdm", "pyfs" });
			request.setAttribute("pyfsList", pyfsList);
		}

		if (userType.equalsIgnoreCase("student")) { // 核对用户类型，如果是学生登陆，学号就直接是该学生的学号了
			xh = session.getAttribute("userName").toString();
		}

		if (!"cancle".equalsIgnoreCase(act)) {
			if ((Globals.XXDM_ZGDZDX).equals(xxdm)) {
				sql = "SELECT v.* FROM view_xsjbxx v WHERE v.xh=?"; // 查询该学号的相关内容的sql语句
				String[] colListdz2 = dao
						.getColumnName("select * from view_xsjbxx where 1=2"); // 返回列名数组
				String[] colListdz1 = { "xydm", "xymc", "bjmc", "zymc" };
				String[] colList1 = new String[colListdz1.length
						+ colListdz2.length];
				System.arraycopy(colListdz2, 0, colList1, 0, colListdz2.length);
				System.arraycopy(colListdz1, 0, colList1, colListdz2.length,
						colListdz1.length);
				rs = dao.getOneRs(sql, new String[] { xh }, colList1); // 获得从毕业生基本信息表（视图）中查询的记录集

				if (rs != null) {
					for (int i = 0; i < colList1.length; i++) {
						map.put(colList1[i].toLowerCase(), rs[i]); // 将记录循环放入map中
					}
					String nd = map.get("nj");
					map.put("nd", nd);
				}
				if (Globals.XXDM_ZGDZDX.equals(xxdm)) {
					if ("1".equals(map.get("xbm"))) {
						map.put("xbdm", "男");
					} else {
						map.put("xbdm", "女");
					}
				}
				zydm = map.get("zydm");
				sql = "select jygl_zydm,jygl_zymc from DMK_ZYDMDZB where jwc_zydm=?";
				String[] dminfo = dao.getOneRs(sql, new String[] { zydm },
						new String[] { "jygl_zydm", "jygl_zymc" });
				if (null != dminfo) {
					map.put("zydm", dminfo[0]);
					map.put("zymc", dminfo[1]);
				}
				if (null != rs) {
					request.setAttribute("nochange", "yes");
				}
				sql = "select mzmc from mzdmb";// 民族
				List mzList = dao.getList(sql, new String[] {},
						new String[] { "mzmc" });
				request.setAttribute("mzList", mzList);
			} else {
				String[] colList1 = null; // 返回列名数组
				String isstu = (String) request.getSession().getAttribute(
						"userType");
				if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm)
						&& "stu".equals(isstu)) {
					sql = "SELECT distinct * from jygl_xsjbxxb a LEFT JOIN bks_xsjbxx b ON a.xsxh=b.xh where xsxh=?"; // 查询该学号的相关内容的sql语句
					colList1 = dao
							.getColumnName("SELECT * from jygl_xsjbxxb,bks_xsjbxx where 1=2");
				} else {
					// sql = "select * from jygl_xsjbxxb where xsxh=?"; //
					// 查询该学号的相关内容的sql语句
					// colList1 =
					// dao.getColumnName("SELECT * from jygl_xsjbxxb,bks_xsjbxx
					// where 1=2");
					// sql =
					// "SELECT distinct * FROM bks_xsjbxx b left JOIN
					// jygl_xsjbxxb a ON a.xsxh=b.xh WHERE xh=?";
					// // 查询该学号的相关内容的sql语句
					sql = "select * from view_xsjbxx where xh=?";
					colList1 = dao
							.getColumnName("select * from view_xsjbxx where 1=2");
				}
				rs = dao.getOneRs(sql, new String[] { xh }, colList1); // 获得从毕业生基本信息表（视图）中查询的记录集
				if (rs != null) {
					for (int i = 0; i < colList1.length; i++) {
						map.put(colList1[i].toLowerCase(), rs[i]); // 将记录循环放入map中
					}
					if (map.get("nd") == null || "".equals(map.get("nd"))
							|| "null".equals(map.get("nd"))) {
						String nd = map.get("nj");
						map.put("nd", nd);
					}
					if (map.get("sfzh") == null || "".equals(map.get("sfzh"))) {
						String sfzh1 = map.get("id");
						map.put("sfzh", sfzh1);
					}
					if ("男".equals(map.get("xb"))) {
						map.put("xbm", "1");
					} else {
						map.put("xbm", "2");
					}
				}
				zydm = map.get("zydm");
				sql = "select jygl_zydm,jygl_zymc from DMK_ZYDMDZB where jwc_zydm=?";
				String[] dminfo = dao.getOneRs(sql, new String[] { zydm },
						new String[] { "jygl_zydm", "jygl_zymc" });
				if (null != dminfo) {
					map.put("zydm", dminfo[0]);
					map.put("zymc", dminfo[1]);
				}
				if (null != rs) {
					request.setAttribute("nochange", "yes");
				}
			}
		}
		if (null != rs && "query".equals(act)) {
			request.setAttribute("nochange", "yes");
		} else if (null == rs) {
			request.setAttribute("nochange", "no");
		}

		// 判断学生类别选择用哪个学历数组
		if (titName.equals("zks")) {
			request.setAttribute("Listxl", "zks");
		}
		if (titName.equals("bks")) {
			request.setAttribute("Listxl", "bks");
		}
		if (titName.equals("yjs")) {
			request.setAttribute("Listxl", "yjs");
		}

		sql = "select sydqdm,sydq from dmk_sydq"; // 生源地区
		List sydqList = dao.getList(sql, new String[] {}, new String[] {
				"sydqdm", "sydq" });
		request.setAttribute("sydqList", sydqList);

		map.put("stuExists", "yes");
		map.put("userType", userType);

		map.put("xxdm", xxdm);
		map.put("xxmc", xxmc);

		request.setAttribute("rs", map);
		StuInfoDAO stuDao = new StuInfoDAO();
		request.setAttribute("shiList", stuDao.getShiList("").get("shiList"));
		request.setAttribute("xianList", stuDao.getShiList("").get("xianList"));
		request.setAttribute("ssList", stuDao.getSsList());
		sql = "select zzmmdm,zzmm from dmk_zzmm"; // 政治面貌
		List zzmmList = dao.getList(sql, new String[] {}, new String[] {
				"zzmmdm", "zzmm" });
		request.setAttribute("zzmmList", zzmmList);
		if (Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)) {
			sql = "select * from jygl_xsccb order by dm";
			request.setAttribute("xsccList", dao.getListNotOut(sql,
					new String[] {}));
		}

		if (dotpye == null) {
			if ((Globals.XXDM_ZGDZDX).equals(Base.xxdm)) {
				return mapping.findForward("success3");
			} else if (Globals.XXDM_FJGCXY.equals(Base.xxdm)) {
				return mapping.findForward("success4");
			} else {
				return mapping.findForward("success");
			}
		} else if (dotpye.equals("save")) {

			// 毕业生信息上报
			if (Globals.XXDM_ZGMSXY.equals(xxdm)) {
				sql = "SELECT pycc FROM xsxxb WHERE xh=?";
				String[] pycc1 = dao.getOneRs(sql, new String[] { xh },
						new String[] { "pycc" });
				if (null != pycc1) {
					xslb = pycc1[0];
				}
				if (null == xslb || "".equals(pycc1)) {
					sql = "SELECT pycc FROM bks_xsjbxx WHERE xh=?";
					String[] pycc2 = dao.getOneRs(sql, new String[] { xh },
							new String[] { "pycc" });
					if (null != pycc1) {
						xslb = pycc2[0];
					}
				}
				if (null == xslb) {
					xslb = "";
				}
			} else {
				if (request.getParameter("titName").equals("yjs")) {
					xslb = "研究生";
				} else if (request.getParameter("titName").equals("bks")) {
					xslb = "本科生";
				} else if (request.getParameter("titName").equals("zks")) {
					xslb = "专科生";
				} // 学生类别（yjs研究生，bks本科生,zks专科生）
			}
			String id = request.getParameter("sfzh"); // 身份证号码
			xxdm = request.getParameter("xxdm"); // 学校代码
			xxmc = DealString.toGBK(request.getParameter("xxmc")); // 学校名称
			String zydm1 = request.getParameter("zydm"); // 专业代码
			String zymc = DealString.toGBK(request.getParameter("zymc")); // 专业名称
			String xsxh = request.getParameter("xh"); // 学号
			String name = DealString.toGBK(request.getParameter("xm")); // 姓名
			String xbdm = request.getParameter("xbm"); // 性别代码
			String xldm = request.getParameter("xldm"); // 学历代码
			String xzdm = request.getParameter("xz"); // 学制
			String sydqdm = request.getParameter("sydqdm"); // 生源地区代码
			String pyfsdm = request.getParameter("pyfsdm"); // 培养方式代码
			String nd = request.getParameter("nd");// 入学年度
			String jgx = request.getParameter("jgx");
			String xscc = request.getParameter("xscc");
			String bynd = String.valueOf(Integer.parseInt(nd)
					+ Integer.parseInt(xzdm)); // 毕业年度
			String memo = DealString.toGBK(request.getParameter("memo"));
			String sbsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
							new String[] {}, new String[] { "sdate" }))[0]; // 上报时间
			// 地质大学特有
			String jgshi = DealString.toGBK(request.getParameter("jgshi")); // 生源地区市级
			String xymc = DealString.toGBK(request.getParameter("xymc")); // 学院名称
			String xydm = request.getParameter("xydm"); // 学院代码
			String bjdm = request.getParameter("bjdm"); // 班级代码
			String lxfs = DealString.toGBK(request.getParameter("lxfs")); // 联系方式
			String lxdzxx = DealString.toGBK(request.getParameter("lxdzxx")); // 电子邮箱
			String qq = DealString.toGBK(request.getParameter("qq")); // QQ
			String mz = DealString.toGBK(request.getParameter("mz")); // 民族
			String zzmm = DealString.toGBK(request.getParameter("zzmm")); // 政治面貌
			String xysbh = DealString.toGBK(request.getParameter("xysbh")); // 协议书
			jgshi = Base.isNull(jgshi) ? "" : jgshi;
			jgx = Base.isNull(jgx) ? "" : jgx;
			xscc = Base.isNull(xscc) ? "" : xscc;
			// 从数据库临时表获得
			sql = "select mzmc from mzdmb";// 民族
			List mzList = dao.getList(sql, new String[] {},
					new String[] { "mzmc" });
			request.setAttribute("mzList", mzList);
			/*
			 * 调用通用方法插入数据，参数有三个 1、表名 2、字段名构成的字符串数组 3、值构成的字符串数组
			 */
			sql = "select * from jygl_xsjbxxb where xsxh=?";
			String[] rs1 = dao.getOneRs(sql, new String[] { xsxh },
					new String[] { "xsxh" });
			if ("12453".equals(xxdm)) {
				String sql2 = "select xydm,xymc from view_xsxxb where xh=?";
				String[] rs3 = dao.getOneRs(sql2, new String[] { xsxh },
						new String[] { "xydm", "xymc" });
				if (rs3 != null) {
					xydm = rs3[0];
					xymc = rs3[1];
				}

			}

			boolean judge = false;
			if (rs1 == null) {
				if ((Base.xxdm).equals("10491")) {
					judge = StandardOperation.insert("jygl_xsjbxxb",
							new String[] { "xslb", "id", "xxdm", "xxmc",
									"zydm", "zymc", "xsxh", "name", "xbdm",
									"xldm", "xzdm", "sydqdm", "pyfsdm", "sbsj",
									"nd", "bynd", "memo", "jgshi", "xymc",
									"xydm", "bjdm", "lxfs", "lxdzxx", "qq",
									"mz", "zzmm", "xysbh" }, new String[] {
									xslb, id, xxdm, xxmc, zydm1, zymc, xsxh,
									name, xbdm, xldm, xzdm, sydqdm, pyfsdm,
									sbsj, nd, bynd, memo, jgshi, xymc, xydm,
									bjdm, lxfs, lxdzxx, qq, mz, zzmm, xysbh },
							request);
				} else {
					judge = StandardOperation.insert("jygl_xsjbxxb",
							new String[] { "xslb", "id", "xxdm", "xxmc",
									"zydm", "zymc", "xsxh", "name", "xbdm",
									"xldm", "xzdm", "sydqdm", "pyfsdm", "sbsj",
									"nd", "bynd", "memo", "xydm", "xymc",
									"jgshi", "jgx", "xscc" }, new String[] {
									xslb, id, xxdm, xxmc, zydm1, zymc, xsxh,
									name, xbdm, xldm, xzdm, sydqdm, pyfsdm,
									sbsj, nd, bynd, memo, xydm, xymc, jgshi,
									jgx, xscc }, request);
				}
				// if (xslb.equals("") || id.equals("") || xxdm.equals("")
				// || xxmc.equals("") || zydm1.equals("")
				// || zymc.equals("") || xsxh.equals("")
				// || name.equals("") || xbdm.equals("")
				// || xldm.equals("") || xzdm.equals("")
				// || sydqdm.equals("") || pyfsdm.equals("")
				// || sbsj.equals("") || nd.equals("") || bynd.equals("")) {
				// request.setAttribute("inserted", "que");
				// } else if (judge) {
				if (judge) {
					request.setAttribute("inserted", "ok");

					// 添加操作的记录
					if ("teacher".equals(userType)) {

						String userName = session.getAttribute("userName")
								.toString();
						String whensj = (dao
								.getOneRs(
										"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
										new String[] {},
										new String[] { "sdate" }))[0]; // 提交时间

						StandardOperation.insert("JYGL_USERCZMXB",
								new String[] { "userid", "dowhat",
										"whichtable", "whichpk", "whensj" },
								new String[] { userName, "增加", "学生基本信息表",
										"学号:" + xsxh, whensj }, request);
					}
				} else {
					request.setAttribute("inserted", "no");
				}
			} else {
				request.setAttribute("inserted", "isexist");
			}
			String isstu = (String) request.getSession().getAttribute(
					"userType");
			// System.out.println(Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm) &&
			// "stu".equals(isstu) && rs1 != null);
			if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm)
					&& "stu".equals(isstu) && rs1 != null) {

				String sql12 = "select * from jygl_xsjbxxb where xsxh=? and sftj='2'";
				String[] teainfo = dao.getOneRs(sql12, new String[] { xsxh },
						new String[] { "id" });
				if (teainfo == null) {
					String[] pycc1 = dao.getOneRs(
							"SELECT pycc FROM xsxxb WHERE xh=?",
							new String[] { xh }, new String[] { "pycc" });
					if (null != pycc1) {
						xslb = pycc1[0];
					}
					if (null == xslb || "".equals(pycc1)) {
						String[] pycc2 = dao.getOneRs(
								"SELECT pycc FROM bks_xsjbxx WHERE xh=?",
								new String[] { xh }, new String[] { "pycc" });
						if (null != pycc1) {
							xslb = pycc2[0];
						}
					}
					if (null == xslb) {
						xslb = "";
					}
					String sfzid = request.getParameter("sfzh");
					judge = StandardOperation.update("jygl_xsjbxxb",
							new String[] { "id", "xldm", "pyfsdm", "sydqdm",
									"memo", "sftj", "xslb" }, new String[] {
									sfzid, xldm, pyfsdm, sydqdm, memo, "2",
									xslb }, "xsxh", xsxh, request);
				} else {
					request.setAttribute("yjtj", "yjtj");
				}
			}
			dotpye = null;
		}
		request.setAttribute("realTable", "jygl_xsjbxxb"); // 表是学生基本信息表
		if (Globals.XXDM_ZGDZDX.equals(xxdm)) {
			return mapping.findForward("success3");
		} else if (Globals.XXDM_FJGCXY.equals(xxdm)) {
			return mapping.findForward("success4");
		} else {
			return mapping.findForward("success");
		}
	}

	// 学生信息查询
	private ActionForward JyglDataSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) throws Exception {
		CommanForm commanForm = (CommanForm) form;
		DAO dao = DAO.getInstance(); // 实例化通用方法，并获得数据库连接
		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = ""; // sql语句
		String querry = " where 1=1 "; // sql条件
		String tableName = "jygl_xsjbxxb"; // 查询信息源表（多为视图名）
		String rsNum = "0";// 返回的记录数
		String xxdm = dao.getXxdm();
		String dataType = request.getParameter("act"); // 接收数据类型
		String xsxh = request.getParameter("xsxh"); // 接收学号
		String name = DealString.toGBK(request.getParameter("name")); // 接收姓名
		String xbdm = request.getParameter("xbdm"); // 接收性别代码
		String xb = DealString.toGBK(request.getParameter("xb"));// 接收性别
		String xslb = DealString.toGBK(request.getParameter("xslb")); // 接收学生类别
		String xymc = DealString.toGBK(request.getParameter("xymc")); // 接收学院名称
		String nd = request.getParameter("nd"); // 接收年度
		String bynd = request.getParameter("bynd"); // 毕业年度
		String pk = "xsxh"; // 主键
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");

		String sfsh = request.getParameter("sfsh");
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();

		// 云南艺术
		if (xxdm.equals(Globals.XXDM_YNYS)) {

			// 把上次提交的值传回去
			if ("query".equals(doType)) {
				map.put("xsxh", xsxh);
				map.put("name", name);
				map.put("xb", xb);
				map.put("xymc", xymc);
				map.put("nd", nd);
				map.put("bynd", bynd);
			}

			if (xsxh == null) {
				xsxh = "";
			}
			if (name == null) {
				name = "";
			}
			if (xbdm == null) {
				xbdm = "";
			}
			if (xymc == null) {
				xymc = "";
			}
			if (nd == null) {
				nd = "";
			}
			if (bynd == null) {
				bynd = "";
			}
			if ((xsxh == null) || xsxh.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xsxh like '%" + xsxh + "%' ";
			}
			if ((name == null) || name.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and name like '%" + name + "%' ";
			}
			if ((xbdm == null) || xbdm.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xbdm='" + xbdm + "' ";
			}
			if ((xymc == null) || xymc.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xsxh in (select xsxh from jygl_xsjbxxb where xsxh in (select xh from view_xsjbxx where xymc ='"
						+ xymc + "'))";
			}
			if ((nd == null) || nd.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and nd='" + nd + "' ";
			}
			if ((bynd == null) || bynd.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and bynd='" + bynd + "' ";
			}

			sql = "select " + pk + " 主键,rownum 行号,a.* from " + tableName
					+ " a " + querry;
			colList = new String[] { "主键", "行号", "nd", "bynd", "name", "xsxh",
					"xb", "zymc", "sbsj" };
			colListCN = dao.getColumnNameCN(colList, tableName);
			List topTr = dao.arrayToList(colList, colListCN);
			if ((request.getParameter("act") != null)
					&& request.getParameter("act").equalsIgnoreCase("go")) {
				rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
				if (rs == null) {
					rsNum = "0";
				} else {
					rsNum = String.valueOf(rs.size());
				}
			}
			request.setAttribute("realTable", "jygl_xsjbxxb"); // 表是学生基本信息表
			request.setAttribute("ndList", dao.getNjList()); // 发送入学年度列表
			request.setAttribute("xyList", dao.getXyList());// 发送学院列表
			request.setAttribute("act", dataType);// 发送数据查询类型
			request.setAttribute("rs", rs);// 发送数据集
			request.setAttribute("topTr", topTr);// 发送表头
			request.setAttribute("rsNum", rsNum);// 发送记录数
			request.setAttribute("rs1", map);
			return mapping.findForward("success2");

		}

		if (userType.equals("teacher")) {
			sql = "select * from jygl_jyxywhb where username=?";
			String[] teainfo = dao.getOneRs(sql, new String[] { userName },
					new String[] { "usertype", "xymc" });
			@SuppressWarnings("unused")
			String tea = "";
			if (null != teainfo) {
				tea = teainfo[0];
			}
			// if ("辅导员".equals(tea)) {
			// map.put("xymc", teainfo[1]);
			// request.setAttribute("who", "fudaoyuan");
			// } else {
			// request.setAttribute("who", "teacher");
			// }

		}

		// 上海工程及通用
		// 把上次提交的值传回去
		if ("query".equals(doType)) {
			map.put("xsxh", xsxh);
			map.put("name", name);
			map.put("xbdm", xbdm);
			map.put("xslb", xslb);
			map.put("xymc", xymc);
			map.put("nd", nd);
			map.put("bynd", bynd);
			map.put("sfsh", request.getParameter("sfsh"));
		}

		if (xsxh == null) {
			xsxh = "";
		}
		if (name == null) {
			name = "";
		}
		if (xbdm == null) {
			xbdm = "";
		}
		if (xslb == null) {
			xslb = "";
		}
		if (xymc == null) {
			xymc = "";
		}
		if (nd == null) {
			nd = "";
		}
		if (bynd == null) {
			bynd = "";
		}
		if ((xsxh == null) || xsxh.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xsxh like '%" + xsxh + "%' ";
		}
		if ((name == null) || name.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and name like '%" + name + "%' ";
		}
		if ((xbdm == null) || xbdm.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xbdm='" + xbdm + "' ";
		}
		if ((xslb == null) || xslb.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xslb='" + xslb + "' ";
		}
		if ((xymc == null) || xymc.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xsxh in (select xsxh from jygl_xsjbxxb where xsxh in (select xh from view_xsjbxx where xymc ='"
					+ xymc + "'))";
		}
		if ((nd == null) || nd.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and nd='" + nd + "' ";
		}
		if ((bynd == null) || bynd.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and bynd='" + bynd + "' ";
		}
		if (((sfsh == null) || sfsh.equals(""))
				|| !Globals.XXDM_ZGMSXY.equals(xxdm)) {
			querry += " and 1=1 ";
		} else {
			if (sfsh == "1" || "1".equals(sfsh)) {
				sfsh = "未审核";
			} else if (sfsh == "2" || "2".equals(sfsh)) {
				sfsh = "已通过√";
			} else {
				sfsh = "未通过X";
			}
			querry += " and sfsh='" + sfsh + "' ";
		}
		if ("12453".equals(xxdm)) {
			String usertype = (String) request.getSession().getAttribute(
					"userType");
			if ("xx".equals(usertype) || "admin".equals(usertype)) {
				sfsh = "已通过√";
				querry += " and fdysh='" + sfsh + "' ";
			}
			String usertuyep = (String) request.getSession().getAttribute(
					"userType");
			String bmmc = (String) request.getSession().getAttribute("bmmc");
			if ("xx".equals(usertuyep) || "admin".equals(usertuyep)) {
				request.setAttribute("who", "teacher");
			} else if ("xy".equals(usertuyep)) {
				request.setAttribute("who", "fudaoyuan");
				map.put("xymc", bmmc);
			}
		}
		sql = "select " + pk + " 主键,rownum r,a.* from " + tableName + " a "
				+ querry;
		if (Globals.XXDM_ZGDZDX.equals(xxdm)
				|| Globals.XXDM_ZGMSXY.equals(xxdm)) {
			colList = new String[] { "主键", "r", "nd", "bynd", "name", "xsxh",
					"xbdm", "xldm", "xslb", "xzdm", "zymc", "sbsj", "sfsh",
					"btgyy" };
		} else if ("12453".equals(xxdm)) {
			colList = new String[] { "主键", "r", "nd", "bynd", "name", "xsxh",
					"xbdm", "xldm", "xslb", "xzdm", "zymc", "sbsj", "fdysh",
					"sfsh" };
		} else {
			colList = new String[] { "主键", "r", "nd", "bynd", "name", "xsxh",
					"xslb", "xzdm", "zymc", "sbsj" };
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
	
			rs.addAll(CommonQueryDAO.commonQuery(sql, "", new String[] {},
					colList, commanForm));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
	
		if ("12453".equals(xxdm)) {
			String utp = (String) request.getSession().getAttribute("userType");
			if ("xx".equals(utp) || "admin".equals(utp)) {
				request.setAttribute("userType", "xx");
			}
		}
		request.setAttribute("realTable", "jygl_xsjbxxb"); // 表是学生基本信息表
		request.setAttribute("ndList", dao.getNjList()); // 发送入学年度列表
		request.setAttribute("xyList", dao.getXyList());// 发送学院列表
		request.setAttribute("act", dataType);// 发送数据查询类型
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("rs1", map);
		return mapping.findForward("success");
	}

	// 学生信息删除
	private ActionForward JyglBysjbxxbDel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String sql = ""; // sql语句
		DAO dao = DAO.getInstance();
		CommanForm commForm = (CommanForm) form;
		String xxdm = dao.getXxdm();
		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String realTable = "jygl_xsjbxxb"; // 要查询的数据源表
		String doType = request.getParameter("doType");
		String pk = "xsxh";
		String dataType = request.getParameter("act"); // 接收数据类型
		String querry = " where 1=1 "; // sql条件
		String pkValue = request.getParameter("pkValue");
		String xsxh = request.getParameter("xsxh"); // 接收学号
		String name = DealString.toGBK(request.getParameter("name")); // 接收姓名
		String xbdm = request.getParameter("xbdm"); // 接收性别代码
		String xb = DealString.toGBK(request.getParameter("xb"));
		String xslb = DealString.toGBK(request.getParameter("xslb")); // 接收学生类别
		String xymc = DealString.toGBK(request.getParameter("xymc")); // 接收学院名称
		String nd = request.getParameter("nd"); // 接收年度
		String bynd = request.getParameter("bynd"); // 接收毕业年度
		String rsNum = "0";// 返回的记录数
		HashMap<String, String> map = new HashMap<String, String>();
		String doType2 = request.getParameter("doType2");
		HttpSession session = request.getSession();

		// 批量删除
		if ("delall".equalsIgnoreCase(doType)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = pkstring.split("!!#!!");

			// ================2010.10.15 edit by
			// luojw==========================
			if (pkstringI != null && pkstringI.length > 0) {
				String[] actSql = new String[pkstringI.length - 1];
				int n = 0;
				for (int i = 1; i < pkstringI.length; i++) {
					actSql[n] = "delete from jygl_xsjbxxb where xsxh = '"
							+ pkstringI[i] + "' ";
					n++;
				}
				boolean judge = dao.saveArrDate(actSql);
				if (judge) {
					request.setAttribute("delall", "ok");
				} else {
					request.setAttribute("delall", "no");
				}
			}
			// for (int i = 1; i < pkstringI.length; i++) {
			// String whichxh = pkstringI[i];
			// boolean judge = false;
			// judge = StandardOperation.delete(realTable, pk, whichxh,
			// request);
			// if (judge) {
			// request.setAttribute("delall", "ok");
			//
			// // 删除操作的记录
			//
			// String userName = session.getAttribute("userName")
			// .toString();
			// String whensj = (dao
			// .getOneRs(
			// "select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
			// new String[] {}, new String[] { "sdate" }))[0]; // 提交时间
			//
			// StandardOperation.insert("JYGL_USERCZMXB", new String[] {
			// "userid", "dowhat", "whichtable", "whichpk",
			// "whensj" }, new String[] { userName, "删除",
			// "学生基本信息表", "学号:" + whichxh, whensj }, request);
			// } else {
			// request.setAttribute("delall", "no");
			// }
			// }
		}

		if (xxdm.equals(Globals.XXDM_YNYS)) {
			// 把上次提交的值传回去
			if ("query".equals(doType2)) {
				map.put("xsxh", xsxh);
				map.put("name", name);
				map.put("xb", xb);
				map.put("xymc", xymc);
				map.put("nd", nd);
				map.put("bynd", bynd);
			}

			if (xsxh == null) {
				xsxh = "";
			}
			if (name == null) {
				name = "";
			}
			if (xbdm == null) {
				xbdm = "";
			}
			if (xymc == null) {
				xymc = "";
			}
			if (nd == null) {
				nd = "";
			}
			if (bynd == null) {
				bynd = "";
			}
			if ((xsxh == null) || xsxh.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xsxh like '%" + xsxh + "%' ";
			}
			if ((name == null) || name.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and name like '%" + name + "%' ";
			}
			if ((xbdm == null) || xbdm.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xbdm='" + xbdm + "' ";
			}
			if ((xymc == null) || xymc.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xsxh in (select xsxh from jygl_xsjbxxb where xsxh in (select xh from view_xsjbxx where xymc ='"
						+ xymc + "'))";
			}
			if ((nd == null) || nd.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and nd='" + nd + "' ";
			}
			if ((bynd == null) || bynd.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and bynd='" + bynd + "' ";
			}

			if ((doType == null) || doType.equalsIgnoreCase("")) {
				// 参数异常
				return mapping.findForward("false");
			} else if (doType.equalsIgnoreCase("del")) {
				// 删除数据
				sql = "delete from " + realTable + " where " + pk + "='"
						+ pkValue + "'";
				boolean judge = false;
				judge = dao.runUpdate(sql, new String[] {});
				if (judge) {
					request.setAttribute("delete", "ok");

					// 删除操作的记录

					String userName = session.getAttribute("userName")
							.toString();
					String whensj = (dao
							.getOneRs(
									"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
									new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

					StandardOperation.insert("JYGL_USERCZMXB", new String[] {
							"userid", "dowhat", "whichtable", "whichpk",
							"whensj" }, new String[] { userName, "删除",
							"学生基本信息表", "学号:" + pkValue, whensj }, request);
				} else {
					request.setAttribute("delete", "no");
				}
			}

			sql = "select " + pk + " 主键,rownum r,a.* from " + realTable + " a "
					+ querry;
			colList = new String[] { "主键", "r", "nd", "bynd", "name", "xsxh",
					"xb", "zymc", "sbsj" };
			colListCN = dao.getColumnNameCN(colList, realTable);
			List topTr = dao.arrayToList(colList, colListCN);

			rs.addAll(CommonQueryDAO.commonQuery(sql, "", new String[] {},
					colList, commForm));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
			request.setAttribute("realTable", "jygl_xsjbxxb"); // 表是学生基本信息表
			request.setAttribute("ndList", dao.getNjList()); // 发送入学年度列表
			request.setAttribute("xyList", dao.getXyList());// 发送学院列表
			request.setAttribute("act", dataType);// 发送数据查询类型
			request.setAttribute("rs", rs);// 发送数据集
			request.setAttribute("topTr", topTr);// 发送表头
			request.setAttribute("rsNum", rsNum);// 发送记录数
			request.setAttribute("rs1", map);
			return mapping.findForward("success2");
		}

		// 把上次提交的值传回去
		if ("query".equals(doType2)) {
			map.put("xsxh", xsxh);
			map.put("name", name);
			map.put("xbdm", xbdm);
			map.put("xslb", xslb);
			map.put("xymc", xymc);
			map.put("nd", nd);
			map.put("bynd", bynd);
		}

		if (xsxh == null) {
			xsxh = "";
		}
		if (name == null) {
			name = "";
		}
		if (xbdm == null) {
			xbdm = "";
		}
		if (xslb == null) {
			xslb = "";
		}
		if (xymc == null) {
			xymc = "";
		}
		if (nd == null) {
			nd = "";
		}
		if (bynd == null) {
			bynd = "";
		}
		if ((xsxh == null) || xsxh.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xsxh = '" + xsxh + "' ";
		}
		if ((name == null) || name.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and name = '" + name + "' ";
		}
		if ((xbdm == null) || xbdm.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xbdm='" + xbdm + "' ";
		}
		if ((xslb == null) || xslb.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xslb='" + xslb + "' ";
		}
		if ((xymc == null) || xymc.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xsxh in (select xsxh from jygl_xsjbxxb where xsxh in (select xh from view_xsjbxx where xymc ='"
					+ xymc + "'))";
		}
		if ((nd == null) || nd.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and nd='" + nd + "' ";
		}
		if ((bynd == null) || bynd.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and bynd='" + bynd + "' ";
		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("del")) {
			// 删除数据
			sql = "delete from " + realTable + " where " + pk + "='" + pkValue
					+ "'";
			boolean judge = false;
			judge = dao.runUpdate(sql, new String[] {});
			if (judge) {
				request.setAttribute("delete", "ok");

				// 删除操作的记录

				String userName = session.getAttribute("userName").toString();
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"删除", "学生基本信息表", "学号:" + pkValue, whensj },
						request);
			} else {
				request.setAttribute("delete", "no");
			}
		}

		sql = "select " + pk + " 主键,rownum r,a.* from " + realTable + " a "
				+ querry;
		colList = new String[] { "主键", "r", "nd", "bynd", "name", "xsxh", "xb",
				"zymc", "sbsj" };
		colListCN = dao.getColumnNameCN(colList, realTable);
		List topTr = dao.arrayToList(colList, colListCN);

		rs.addAll(CommonQueryDAO.commonQuery(sql, "", new String[] {}, colList,
				commForm));
		if (rs == null) {
			rsNum = "0";
		} else {
			rsNum = String.valueOf(rs.size());
		}

		String userType = session.getAttribute("userType").toString();
		if ("teacher".equals(userType) || "admin".equals(userType)) {
			request.setAttribute("who", "teacher");
		}

		request.setAttribute("realTable", realTable);// 把表名发回去
		request.setAttribute("rs1", map);
		request.setAttribute("ndList", dao.getNjList());// 发送年度列表
		request.setAttribute("xyList", dao.getXyList());// 发送学院列表
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数

		return mapping.findForward("success");
	}

	// 点击查看学生更详细信息
	private ActionForward JyglViewMoreinfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = "xsxh";
		String sql = ""; // sql语句
		DAO dao = DAO.getInstance();
		String xxdm = dao.getXxdm();
		String realTable = "jygl_xsjbxxb";
		String doType = request.getParameter("doType");
		String shenhe = request.getParameter("shenhe");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();

		if (xxdm.equals(Globals.XXDM_YNYS)) {
			if ((doType == null) || doType.equalsIgnoreCase("")) {
				// 参数异常
				return mapping.findForward("false");
			} else if (doType.equalsIgnoreCase("view")) {
				// 查询数据
				sql = "select * from " + realTable + " where " + pk + "='"
						+ pkValue + "'";
				String[] colList = dao
						.getColumnName("select * from jygl_xsjbxxb where 1=2"); // 返回列名数组
				String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
				sql = "select xymc from view_xsjbxx where xh=?";
				String[] xymc = dao.getOneRs(sql, new String[] { pkValue },
						new String[] { "xymc" });
				if (stuinfo != null) {
					for (int i = 0; i < colList.length; i++) {
						map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
					}
					sql = "select (select qxmc from dmk_qx where qxdm=?)||(select qxmc from dmk_qx where qxdm=?)||(select qxmc from dmk_qx where qxdm=?) sydq from dual";
					String sydqinfo = dao.getOneRs(sql,
							new String[] { map.get("sydqdm"), map.get("jgshi"),
									map.get("jgx") }, "sydq");
					map.put("sydq", sydqinfo);
					if (null != xymc) {
						map.put("xymc", xymc[0]);
					}
					String sbsj = null;
					String sjyear = null;
					String sjmon = null;
					String sjday = null;
					String sj = map.get("sbsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sbsj = sjyear + "年" + sjmon + "月" + sjday + "日";
					map.put("sbsj", sbsj);
				}
			}
			request.setAttribute("rs", map);// 发送数据集
			return mapping.findForward("success2");

		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// 查询数据
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from jygl_xsjbxxb where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			sql = "select xymc from view_xsjbxx where xh=?";
			String[] xymc = dao.getOneRs(sql, new String[] { pkValue },
					new String[] { "xymc" });
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				if (Globals.XXDM_ZGDZDX.equals(xxdm)
						|| Globals.XXDM_ZGMSXY.equals(xxdm)) {
					String shsj = null;
					// String fbsj = null;
					String sjyear = null;
					String sjmon = null;
					String sjday = null;
					String sjhour = null;
					if (null != (map.get("shsj"))) {
						String sj = map.get("shsj").toString();
						sjyear = sj.substring(0, 4);
						sjmon = sj.substring(4, 6);
						sjday = sj.substring(6, 8);
						sjhour = sj.substring(8, 10);
						shsj = sjyear + "年" + sjmon + "月" + sjday + "日"
								+ sjhour + "时";
						map.put("shsj", shsj);
					}
				}
				// 显示出来的性别是中文
				if ("1".equals(map.get("xbdm"))) {
					map.put("xbdm", "男");
				} else if ("2".equals(map.get("xbdm"))) {
					map.put("xbdm", "女");
				}
				sql = "select xl from dmk_xl where xldm=?";
				String xldm = map.get("xldm");
				String xlinfo = dao.getOneRs(sql, new String[] { xldm }, "xl");
				map.put("xl", xlinfo);

				sql = "select sydq from dmk_sydq where sydqdm=?";
				String sydqdm = map.get("sydqdm");
				String sydqinfo = dao.getOneRs(sql, new String[] { sydqdm },
						"sydq");
				String qxdm = map.get("jgshi");
				String sydqinfo1 = dao.getOneRs(
						"select qxmc from dmk_qx WHERE qxdm = ?",
						new String[] { qxdm }, "qxmc");
				String jgx = map.get("jgx");
				String jgxmc = dao.getOneRs(
						"select qxmc from dmk_qx WHERE qxdm = ?",
						new String[] { jgx }, "qxmc");
				map.put("sydq", sydqinfo + sydqinfo1 + jgxmc);

				if ("专科生".equals(map.get("xslb"))
						|| "本科生".equals(map.get("xslb"))) {
					sql = "select pyfs from dmk_bzpyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, "pyfs");
					map.put("pyfs", pyfsinfo);
				} else if ("研究生".equals(map.get("xslb"))) {
					sql = "select pyfs from dmk_yjspyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, "pyfs");
					map.put("pyfs", pyfsinfo);
				}
				if (null != xymc) {
					map.put("xymc", xymc[0]);
				}
				String sbsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sj = map.get("sbsj");
				if (!"".equalsIgnoreCase(sj) && null != sj) {
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sbsj = sjyear + "年" + sjmon + "月" + sjday + "日";
					map.put("sbsj", sbsj);
				}
			}
		}
		request.setAttribute("rs", map);// 发送数据集
		request.setAttribute("doType", doType);
		if ("shenhe".equals(shenhe)) {
			return mapping.findForward("successsh");
		} else {
			return mapping.findForward("success");
		}
	}

	// 毕业生基本信息信息修改
	private ActionForward JyglInfoUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = "xsxh";
		String sql = ""; // sql语句
		DAO dao = DAO.getInstance();
		String xxdm = dao.getXxdm();
		String realTable = "jygl_xsjbxxb";
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String pkValue = request.getParameter("pkValue");
		String doNow = request.getParameter("doNow");

		if (xxdm.equals(Globals.XXDM_YNYS)) {
			StuInfoDAO stuDao = new StuInfoDAO();
			HashMap<String, String> map = new HashMap<String, String>();
			request.setAttribute("njList", dao.getNjList());// 发送年级列表

			sql = "select zzmmdm,zzmm from dmk_zzmm"; // 政治面貌代码
			List zzmmdmList = dao.getList(sql, new String[] {}, new String[] {
					"zzmmdm", "zzmm" });
			request.setAttribute("zzmmdmList", zzmmdmList);

			sql = "select sydqdm,sydq from dmk_sydq"; // 生源地区
			List sydqdmList = dao.getList(sql, new String[] {}, new String[] {
					"sydqdm", "sydq" });
			request.setAttribute("sydqdmList", sydqdmList);

			sql = "select dwxzdm,dwxz from dmk_dwxz"; // 就业意向
			List dwxzdmList = dao.getList(sql, new String[] {}, new String[] {
					"dwxzdm", "dwxz" });
			request.setAttribute("dwxzdmList", dwxzdmList);

			sql = "select byqxdm,byqx from dmk_byqx"; // 就业方式
			List byqxdmList = dao.getList(sql, new String[] {}, new String[] {
					"byqxdm", "byqx" });
			request.setAttribute("byqxdmList", byqxdmList);

			sql = "select distinct zymc from view_njxyzybj";// 专业名称
			List zyList = dao.getList(sql, new String[] {},
					new String[] { "zymc" });
			request.setAttribute("zyList", zyList);

			sql = "select distinct bjmc from view_njxyzybj";// 班级名称
			List bjList = dao.getList(sql, new String[] {},
					new String[] { "bjmc" });
			request.setAttribute("bjList", bjList);

			sql = "select mzmc from mzdmb";// 民族
			List mzList = dao.getList(sql, new String[] {},
					new String[] { "mzmc" });
			request.setAttribute("mzList", mzList);

			if ((doType == null) || doType.equalsIgnoreCase("")) {
				// 参数异常
				return mapping.findForward("false");
			} else if (doType.equalsIgnoreCase("update") && pkValue != null) {
				// 查询数据
				sql = "select * from " + realTable + " where " + pk + "='"
						+ pkValue + "'";
				String[] colList = dao
						.getColumnName("select * from jygl_xsjbxxb where 1=2"); // 返回列名数组
				String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
				sql = "select xymc from view_xsjbxx where xh=?";
				String[] xymc = dao.getOneRs(sql, new String[] { pkValue },
						new String[] { "xymc" });
				if (stuinfo != null) {
					for (int i = 0; i < colList.length; i++) {
						map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
					}
					map.put("xymc", xymc[0]);
					if (null != (map.get("sbsj"))) {
						String sbsj = null;
						String sjyear = null;
						String sjmon = null;
						String sjday = null;
						String sj = map.get("sbsj").toString();
						sjyear = sj.substring(0, 4);
						sjmon = sj.substring(4, 6);
						sjday = sj.substring(6, 8);
						sbsj = sjyear + "年" + sjmon + "月" + sjday + "日";
						map.put("sbsj", sbsj);
					}
				}
			}
			if ("update2".equals(doType2)) {
				String xsxh = request.getParameter("xsxh"); // 学号
				String name = DealString.toGBK(request.getParameter("name")); // 姓名
				String xb = DealString.toGBK(request.getParameter("xb")); // 性别
				String nj = request.getParameter("nj"); // 年级
				String zymc = DealString.toGBK(request.getParameter("zymc"));// 专业名称
				String sydq = request.getParameter("sydq"); // 生原地区
				String jgshi = request.getParameter("jgshi"); // 生原籍贯市
				String jgx = request.getParameter("jgx"); // 生原籍贯县
				String csrq = DealString.toGBK(request.getParameter("csrq")); //
				String mz = DealString.toGBK(request.getParameter("mz")); // 民族
				String zzmm = DealString.toGBK(request.getParameter("zzmm")); // 政治面貌
				String lxdh = request.getParameter("lxdh"); // 联系电话
				String nd = request.getParameter("nd"); // 年度
				String bynd = request.getParameter("bynd"); // 毕业年度
				String qkqk = DealString.toGBK(request.getParameter("qkqk"));// 欠款情况
				String jcqk = DealString.toGBK(request.getParameter("jcqk"));// 奖励惩情况
				String jyyx = DealString.toGBK(request.getParameter("jyyx")); // 就业意向
				String jyfs = DealString.toGBK(request.getParameter("jyfs")); // 就业方式

				boolean judge = false;
				judge = StandardOperation.update(realTable, new String[] {
						"name", "xb", "nj", "zymc", "sydq", "csrq", "mz",
						"zzmm", "lxdh", "nd", "bynd", "qkqk", "jcqk", "jyyx",
						"jyfs", "jgshi", "jgx" }, new String[] { name, xb, nj,
						zymc, sydq, csrq, mz, zzmm, lxdh, nd, bynd, qkqk, jcqk,
						jyyx, jyfs, jgshi, jgx }, pk, xsxh, request);

				if (judge) {
					request.setAttribute("updated", "ok");
					// 修改操作的记录
					HttpSession session = request.getSession();
					String userName = session.getAttribute("userName")
							.toString();
					String whensj = (dao
							.getOneRs(
									"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
									new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

					StandardOperation.insert("JYGL_USERCZMXB", new String[] {
							"userid", "dowhat", "whichtable", "whichpk",
							"whensj" }, new String[] { userName, "修改",
							"学生基本信息表", "学号:" + xsxh, whensj }, request);
				} else {
					request.setAttribute("updated", "no");
				}
				doType2 = null;
				sql = "select * from jygl_xsjbxxb where xsxh=?";
				String[] colList = dao
						.getColumnName("select * from jygl_xsjbxxb where 1=2");
				String[] stuinfo = dao.getOneRs(sql, new String[] { xsxh },
						colList);
				sql = "select xymc from view_xsjbxx where xh=?";
				String[] xymc = dao.getOneRs(sql, new String[] { xsxh },
						new String[] { "xymc" });

				if (stuinfo != null) {
					for (int i = 0; i < colList.length; i++) {
						map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
					}
					map.put("xymc", xymc[0]);
					if (null != (map.get("sbsj"))) {
						String sbsj = null;
						String sjyear = null;
						String sjmon = null;
						String sjday = null;
						String sj = map.get("sbsj").toString();
						sjyear = sj.substring(0, 4);
						sjmon = sj.substring(4, 6);
						sjday = sj.substring(6, 8);
						sbsj = sjyear + "年" + sjmon + "月" + sjday + "日";
						map.put("sbsj", sbsj);
					}
				}
			}
			request.setAttribute("shiList", stuDao.getShiList("")
					.get("shiList"));
			request.setAttribute("xianList", stuDao.getShiList("").get(
					"xianList"));
			request.setAttribute("rs", map);// 发送数据集
			return mapping.findForward("success2");

		}

		if ("change".equals(doNow) || "update".equals(doType)
				&& null != doType2) {
			pkValue = request.getParameter("xsxh");
		}

		HashMap<String, String> map = new HashMap<String, String>();
		sql = "select xslb from jygl_xsjbxxb where xsxh=?";
		String[] xslbinfo = dao.getOneRs(sql, new String[] { pkValue },
				new String[] { "xslb" });

		if ("专科生".equals(xslbinfo[0]) || "本科生".equals(xslbinfo[0])) {
			sql = "select pyfsdm,pyfs from dmk_bzpyfs"; // 本专生培养方式
			List pyfsList = dao.getList(sql, new String[] {}, new String[] {
					"pyfsdm", "pyfs" });
			request.setAttribute("pyfsList", pyfsList);
		} else {
			sql = "select pyfsdm,pyfs from dmk_yjspyfs"; // 研究生培养方式
			List pyfsList = dao.getList(sql, new String[] {}, new String[] {
					"pyfsdm", "pyfs" });
			request.setAttribute("pyfsList", pyfsList);
		}

		sql = "select sydqdm,sydq from dmk_sydq"; // 生源地区
		List sydqList = dao.getList(sql, new String[] {}, new String[] {
				"sydqdm", "sydq" });
		request.setAttribute("sydqList", sydqList);

		StuInfoDAO stuDao = new StuInfoDAO();
		request.setAttribute("shiList", stuDao.getShiList("").get("shiList"));
		request.setAttribute("xianList", stuDao.getShiList("").get("xianList"));
		request.setAttribute("ssList", stuDao.getSsList());

		sql = "select xldm,xl from dmk_xl"; // 学历
		List xldmList = dao.getList(sql, new String[] {}, new String[] {
				"xldm", "xl" });
		request.setAttribute("xldmList", xldmList);

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("update") && pkValue != null) {
			// 查询数据
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from jygl_xsjbxxb where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			sql = "select xymc from view_xsjbxx where xh=?";
			String[] xymc = dao.getOneRs(sql, new String[] { pkValue },
					new String[] { "xymc" });
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}

				map.put("xymc", xymc[0]);
				String sbsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sj = Base.isNull(map.get("sbsj")) ? "" : map.get("sbsj")
						.toString();
				sjyear = Base.isNull(map.get("sbsj")) ? "" : sj.substring(0, 4);
				sjmon = Base.isNull(map.get("sbsj")) ? "" : sj.substring(4, 6);
				sjday = Base.isNull(map.get("sbsj")) ? "" : sj.substring(6, 8);
				sbsj = sjyear + "年" + sjmon + "月" + sjday + "日";
				map.put("sbsj", sbsj);

			}
		}
		if ("update2".equals(doType2)) {
			String xsxh = request.getParameter("xsxh");
			String xslb = DealString.toGBK(request.getParameter("xslb"));
			String id = request.getParameter("id");
			String name = DealString.toGBK(request.getParameter("name"));
			String xbdm = request.getParameter("xbdm");
			String zydm = request.getParameter("zydm");
			String zymc = DealString.toGBK(request.getParameter("zymc"));
			String xldm = request.getParameter("xldm");
			String xzdm = request.getParameter("xzdm");
			String sydqdm = request.getParameter("sydqdm");
			String pyfsdm = request.getParameter("pyfsdm");
			String nd = request.getParameter("nd");
			String bynd = request.getParameter("bynd");
			String memo = DealString.toGBK(request.getParameter("memo"));
			String jgshi = request.getParameter("jgshi");
			String jgx = request.getParameter("jgx");
			jgshi = Base.isNull(jgshi) ? "" : jgshi;
			jgx = Base.isNull(jgx) ? "" : jgx;
			if (Globals.XXDM_ZGDZDX.equals(xxdm)) {
				String xydm = request.getParameter("xydm");
				String xymc = DealString.toGBK(request.getParameter("xymc"));
				String lxfs = DealString.toGBK(request.getParameter("lxfs"));
				String lxdzxx = DealString
						.toGBK(request.getParameter("lxdzxx"));
				String qq = DealString.toGBK(request.getParameter("qq"));
				String xysbh = DealString.toGBK(request.getParameter("xysbh"));
				String bjdm = DealString.toGBK(request.getParameter("bjdm"));
				sql = "update " + realTable + " set xslb='" + xslb + "',id='"
						+ id + "',name='" + name + "',xbdm='" + xbdm
						+ "',zydm='" + zydm + "',zymc='" + zymc + "',xldm='"
						+ xldm + "',xzdm='" + xzdm + "',sydqdm='" + sydqdm
						+ "',pyfsdm='" + pyfsdm + "',nd='" + nd + "',bynd='"
						+ bynd + "',memo='" + memo + "',xydm='" + xydm
						+ "',xymc='" + xymc + "',lxfs='" + lxfs + "',lxdzxx='"
						+ lxdzxx + "',qq='" + qq + "',xysbh='" + xysbh
						+ "',bjdm='" + bjdm + "',jgshi='" + jgshi
						+ "' where xsxh='" + xsxh + "'";
			} else {
				sql = "update " + realTable + " set xslb='" + xslb + "',id='"
						+ id + "',name='" + name + "',xbdm='" + xbdm
						+ "',zydm='" + zydm + "',zymc='" + zymc + "',xldm='"
						+ xldm + "',xzdm='" + xzdm + "',sydqdm='" + sydqdm
						+ "',pyfsdm='" + pyfsdm + "',nd='" + nd + "',bynd='"
						+ bynd + "',memo='" + memo + "',jgshi='" + jgshi
						+ "',jgx='" + jgx + "' where xsxh='" + xsxh + "'";
			}
			boolean judge = false;
			judge = dao.runUpdate(sql, new String[] {});
			if (xslb.equals("") || id.equals("") || name.equals("")
					|| xbdm.equals("") || xldm.equals("") || xzdm.equals("")
					|| sydqdm.equals("") || pyfsdm.equals("") || nd.equals("")
					|| zydm.equals("") || zymc.equals("") || bynd.equals("")) {
				request.setAttribute("updated1", "que");
			}
			if (judge) {
				request.setAttribute("updated", "ok");
				// 修改操作的记录
				HttpSession session = request.getSession();
				String userName = session.getAttribute("userName").toString();
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"修改", "学生基本信息表", "学号:" + xsxh, whensj },
						request);
			} else {
				request.setAttribute("updated", "no");
			}
			doType2 = null;
			sql = "select * from jygl_xsjbxxb where xsxh=?";
			String[] colList = dao
					.getColumnName("select * from jygl_xsjbxxb where 1=2");
			String[] stuinfo = dao
					.getOneRs(sql, new String[] { xsxh }, colList);
			sql = "select xymc from view_xsjbxx where xh=?";
			String[] xymc = dao.getOneRs(sql, new String[] { xsxh },
					new String[] { "xymc" });

			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				map.put("xymc", xymc[0]);
				String sbsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sj = map.get("sbsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sbsj = sjyear + "年" + sjmon + "月" + sjday + "日";
				map.put("sbsj", sbsj);
			}
		}
		request.setAttribute("rs", map);// 发送数据集
		return mapping.findForward("success");
	}

	// 就业协议信息传入
	private ActionForward jyxyInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		// TODO
		CommanForm dataSearchForm = (CommanForm) form;
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		String xsxhao = request.getParameter("xsxh");
		String whichxxdm = StandardOperation.getXxdm();// 学校代码
		request.setAttribute("xxdm", whichxxdm);
		StuInfoDAO stuDao = new StuInfoDAO();
		// 云南艺术
		if (whichxxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {

			sql = "select dwxzdm,dwxz from dmk_dwxz2"; // 单位性质代码2
			List dwxzdm2List = dao.getList(sql, new String[] {}, new String[] {
					"dwxzdm", "dwxz" });
			request.setAttribute("dwxzdm2List", dwxzdm2List);

			// 学生基本信息
			String xh = DealString.toGBK(request.getParameter("xh")); // 学号
			String xm = DealString.toGBK(request.getParameter("xm")); // 姓名
			String xb = DealString.toGBK(request.getParameter("xb")); // 性别
			String nl = DealString.toGBK(request.getParameter("nl")); // 年龄
			String mz = DealString.toGBK(request.getParameter("mz")); // 民族
			String zzmm = DealString.toGBK(request.getParameter("zzmm")); // 政治面貌
			String pyfs = DealString.toGBK(request.getParameter("pyfs")); // 培养方式
			String xl = DealString.toGBK(request.getParameter("xl")); // 学历
			String zymc = DealString.toGBK(request.getParameter("zymc")); // 专业名称
			String xz = DealString.toGBK(request.getParameter("xz")); // 学制
			String jtdz = DealString.toGBK(request.getParameter("jtdz")); // 家庭地址
			String lxdh = DealString.toGBK(request.getParameter("lxdh")); // 联系电话
			// 单位基本信息
			String dwmc = DealString.toGBK(request.getParameter("dwmc")); // 单位名称
			String dwls = DealString.toGBK(request.getParameter("dwls")); // 单位隶属
			String dwlxr = DealString.toGBK(request.getParameter("dwlxr")); // 单位联系人
			String dwlxdh = DealString.toGBK(request.getParameter("dwlxdh")); // 单位联系电话
			String dwyb = DealString.toGBK(request.getParameter("dwyb")); // 单位邮编
			String dwdz = DealString.toGBK(request.getParameter("dwdz")); // 单位地址
			String dwxz = DealString.toGBK(request.getParameter("dwxz")); // 单位性质
			String hyfl = DealString.toGBK(request.getParameter("hyfl")); // 行业分类
			String dayjdz = DealString.toGBK(request.getParameter("dayjdz")); // 档案邮寄地址
			String fbsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
							new String[] {}, new String[] { "sdate" }))[0]; // 发布时间

			if ("save".equals(doType)) {
				boolean judge = false;
				judge = StandardOperation.insert("ynys_jygl_jyxy",
						new String[] { "xh", "xm", "xb", "nl", "mz", "zzmm",
								"pyfs", "xl", "zymc", "xz", "jtdz", "lxdh",
								"dwmc", "dwls", "dwlxr", "dwlxdh", "dwyb",
								"dwdz", "dwxz", "hyfl", "dayjdz", "fbsj" },
						new String[] { xh, xm, xb, nl, mz, zzmm, pyfs, xl,
								zymc, xz, jtdz, lxdh, dwmc, dwls, dwlxr,
								dwlxdh, dwyb, dwdz, dwxz, hyfl, dayjdz, fbsj },
						request);

				if (judge) {
					request.setAttribute("save", "ok");
				} else {
					request.setAttribute("save", "no");
				}
			}
			// String usertype = session.getAttribute("userType").toString();
			String userName = session.getAttribute("userName").toString();
			sql = "select * from view_xsxxb where xh=?";
			String[] colList = { "xh", "xm", "xb", "mz", "zzmm", "zymc", "xz",
					"jtdz", "lxdh" };
			String[] stuinfo = dao.getOneRs(sql, new String[] { userName },
					new String[] { "xh", "xm", "xb", "mz", "zzmmmc", "zymc",
							"xz", "jtdz", "lxdh" });

			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
			}
			request.setAttribute("rs", map);
			return mapping.findForward("success2");
		}

		// 云南艺术到此结束

		ArrayList<HashMap<String, String>> ListA = new ArrayList<HashMap<String, String>>();
		String[] dwxzdmA = { "10", "15", "20", "21", "22", "23", "25", "29",
				"31", "32", "33", "35", "39", "40", "55", "56" };
		String[] dwxzdmA1 = { "党政机关", "县及县以下党政机关、事业单位和社会团体组织*", "科研设计单位",
				"高等教育单位", "中等、初等教育单位", "医疗卫生单位", "艰苦行业事业单位*", "其他事业单位", "国有企业",
				"三资企业", "中小企业*", "艰苦行业企业*", "其他企业", "部队*", "农村建制村*", "城镇社区*" };
		for (int i = 0; i < 16; i++) {
			HashMap<String, String> mapA = new HashMap<String, String>();
			mapA.put("dwxzdm", dwxzdmA[i]);
			mapA.put("dwxz", dwxzdmA1[i]);
			ListA.add(mapA);
		}
		request.setAttribute("ListA", ListA);

		// b
		ArrayList<HashMap<String, String>> ListB = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> mapB = new HashMap<String, String>();
		mapB.put("dwxzdm", "80");
		ListB.add(mapB);
		request.setAttribute("ListB", ListB);

		// c
		ArrayList<HashMap<String, String>> ListC = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> mapC = new HashMap<String, String>();
		mapC.put("dwxzdm", "85");
		ListC.add(mapC);
		request.setAttribute("ListC", ListC);

		// d
		ArrayList<HashMap<String, String>> ListD = new ArrayList<HashMap<String, String>>();
		String[] dwxzdmD = { "75", "76", "77" };
		String[] dwxzdmD1 = { "自主创业*", "自由职业", "其他灵活就业" };
		for (int d = 0; d < 3; d++) {
			HashMap<String, String> mapD = new HashMap<String, String>();
			mapD.put("dwxzdm", dwxzdmD[d]);
			mapD.put("dwxz", dwxzdmD1[d]);
			ListD.add(mapD);
		}
		request.setAttribute("ListD", ListD);
		// e
		ArrayList<HashMap<String, String>> ListE = new ArrayList<HashMap<String, String>>();
		String[] dwxzdmE = { "50", "51" };
		for (int d = 0; d < 2; d++) {
			HashMap<String, String> mapE = new HashMap<String, String>();
			mapE.put("dwxzdm", dwxzdmE[d]);
			ListE.add(mapE);
		}
		request.setAttribute("ListE", "ListE");
		// F
		ArrayList<HashMap<String, String>> ListF = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> mapF = new HashMap<String, String>();
		mapF.put("dwxzdm", "70");
		ListF.add(mapF);
		request.setAttribute("ListF", ListF);

		// G
		ArrayList<HashMap<String, String>> ListG = new ArrayList<HashMap<String, String>>();
		String[] dwxzdmG = { "71", "72" };
		for (int g = 0; g < 2; g++) {
			HashMap<String, String> mapG = new HashMap<String, String>();
			mapG.put("dwxzdm", dwxzdmG[g]);
			ListG.add(mapG);
		}
		request.setAttribute("ListG", "ListG");
		// H
		ArrayList<HashMap<String, String>> ListH = new ArrayList<HashMap<String, String>>();
		String[] dwxzdmH = { "70", "71", "72" };
		for (int h = 0; h < 3; h++) {
			HashMap<String, String> mapH = new HashMap<String, String>();
			mapH.put("dwxzdm", dwxzdmH[h]);
			ListH.add(mapH);
		}
		request.setAttribute("ListH", "ListH");
		// I
		ArrayList<HashMap<String, String>> ListI = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> mapI = new HashMap<String, String>();
		mapI.put("dwxzdm", "");
		ListI.add(mapI);
		request.setAttribute("ListI", ListI);
		// J
		ArrayList<HashMap<String, String>> ListJ = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> mapJ = new HashMap<String, String>();
		mapJ.put("zgbm", "升学");
		ListJ.add(mapJ);
		request.setAttribute("ListJ", ListJ);
		// K
		ArrayList<HashMap<String, String>> ListK = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> mapK = new HashMap<String, String>();
		mapK.put("zgbm", "出国、退学、延长");
		ListK.add(mapK);
		request.setAttribute("ListK", ListK);
		// L
		sql = "select zgbmdm,zgbm from dmk_zgbmL";
		List ListL = dao.getList(sql, new String[] {}, new String[] { "zgbmdm",
				"zgbm" });
		request.setAttribute("ListL", ListL);
		// M
		ArrayList<HashMap<String, String>> ListM = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> mapM = new HashMap<String, String>();
		mapM.put("zgbm", "");
		ListM.add(mapM);
		request.setAttribute("ListM", ListM);

		// 控制进入哪个logic标签
		String byqxdm = request.getParameter("byqxdm");
		JyglService service = new JyglService();
		HashMap<String, String> flagmap = new HashMap<String, String>();
		if (!Base.isNull(byqxdm)) {
			flagmap = service.getRedFlag(byqxdm);
		}
		request.setAttribute("redflag", flagmap);
		if ("first".equals(act)) {
			request.setAttribute("whichlist", "H");
			request.setAttribute("whichzgbmlist", "X");
			request.setAttribute("danweiname", "X");
		}
		if ("01".equals(byqxdm) || "13".equals(byqxdm) || "15".equals(byqxdm)) {
			request.setAttribute("whichlist", "A");
		}
		if ("02".equals(byqxdm) || "03".equals(byqxdm) || "12".equals(byqxdm)) {
			request.setAttribute("whichlist", "B");
		}
		if ("04".equals(byqxdm)) {
			request.setAttribute("whichlist", "C");
			request.setAttribute("chuguo", "N");
		}
		if ("14".equals(byqxdm)) {
			request.setAttribute("whichlist", "D");
		}
		if ("17".equals(byqxdm)) {
			request.setAttribute("whichlist", "E");
		}
		if ("06".equals(byqxdm)) {
			request.setAttribute("whichlist", "F");
		}
		if ("07".equals(byqxdm)) {
			request.setAttribute("whichlist", "G");
		}
		if ("16".equals(byqxdm) || (null == byqxdm && (!"first".equals(act)))
				|| ("".equals(byqxdm) && (!"first".equals(act)))) {
			request.setAttribute("whichlist", "H");
		}
		if ("05".equals(byqxdm) || "08".equals(byqxdm) || "11".equals(byqxdm)) {
			request.setAttribute("whichlist", "I");
		}

		if ("02".equals(byqxdm) || "03".equals(byqxdm) || "12".equals(byqxdm)) {
			request.setAttribute("whichzgbmlist", "J");
		}
		if ("04".equals(byqxdm) || "05".equals(byqxdm) || "08".equals(byqxdm)
				|| "11".equals(byqxdm)) {
			request.setAttribute("whichzgbmlist", "K");
		}
		if ("01".equals(byqxdm) || "13".equals(byqxdm) || "14".equals(byqxdm)
				|| "15".equals(byqxdm) || "17".equals(byqxdm)) {
			request.setAttribute("whichzgbmlist", "L");
		}
		if ("06".equals(byqxdm) || "07".equals(byqxdm) || "16".equals(byqxdm)
				|| "".equals(byqxdm) || "09".equals(byqxdm)) {
			request.setAttribute("whichzgbmlist", "M");
		}
		// 和单位名称有关的下选项
		if ("01".equals(byqxdm) || "02".equals(byqxdm) || "03".equals(byqxdm)
				|| "12".equals(byqxdm) || "13".equals(byqxdm)
				|| "14".equals(byqxdm) || "15".equals(byqxdm)
				|| "17".equals(byqxdm)) {
			request.setAttribute("danweiname", "X");
		} else if ("04".equals(byqxdm)) {
			request.setAttribute("danweiname", "Y");
		} else {
			request.setAttribute("danweiname", "O");
		}

		// 把第一部分的信息传递进来
		if (userType.equalsIgnoreCase("student")) {
			act = "go";
		}

		if (Globals.XXDM_ZGDZDX.equals(whichxxdm)) {
			sql = "select pyfsdm,pyfs from dmk_bzpyfs";
			List pyfsList = dao.getList(sql, new String[] {}, new String[] {
					"pyfsdm", "pyfs" });
			request.setAttribute("pyfsList", pyfsList);

		}
		// 判断是否是上海本地学生 上海本地学生不用选择生源地主管部门
		if ("310000".equals(map.get("sydqdm"))) {
			request.setAttribute("sydqdmIsSh", "kong");
		} else {
			request.setAttribute("sydqdmIsSh", "notkong");
		}
		// 通用登陆后下拉框信息显示
		sql = "select zzmmdm,zzmm from dmk_zzmm"; // 政治面貌代码
		List zzmmdmList = dao.getList(sql, new String[] {}, new String[] {
				"zzmmdm", "zzmm" });
		request.setAttribute("zzmmdmList", zzmmdmList);
		if (userType.equals("teacher")) {
			sql = "select byqxdm,byqx from dmk_byqx";
		} else {
			sql = "select byqxdm,byqx from dmk_byqx where byqxdm not in('05','06','07','08','09','11')";
		}
		// 毕业去向代码
		List byqxdmList = dao.getList(sql, new String[] {}, new String[] {
				"byqxdm", "byqx" });
		request.setAttribute("byqxdmList", byqxdmList);

		sql = "select dwdqdm,dwdq from dmk_dwdq"; // 单位地区
		List dwdqList = dao.getList(sql, new String[] {}, new String[] {
				"dwdqdm", "dwdq" });
		request.setAttribute("dwdqList", dwdqList);

		sql = "select zgbmdm,zgbm from dmk_zgbm"; // 主管部门代码
		List zgbmList = dao.getList(sql, new String[] {}, new String[] {
				"zgbmdm", "zgbm" });
		request.setAttribute("zgbmList", zgbmList);

		sql = "select jzzhlbbzwdm,jzzhlbbzw from dmk_jzzhlbbzw"; // 居住证或蓝表标志位
		List jzzhlbbzwdmList = dao.getList(sql, new String[] {}, new String[] {
				"jzzhlbbzwdm", "jzzhlbbzw" });
		request.setAttribute("jzzhlbbzwdmList", jzzhlbbzwdmList);

		sql = "select sydzgbmdm,sydzgbm from dmk_sydzgbm"; // 生源地主管单位名称
		List sydzgbmList = dao.getList(sql, new String[] {}, new String[] {
				"sydzgbmdm", "sydzgbm" });
		request.setAttribute("sydzgbmList", sydzgbmList);

		sql = "select dwxzdm,dwxz from dmk_dwxz"; // 单位性质代码
		List dwxzdmList = dao.getList(sql, new String[] {}, new String[] {
				"dwxzdm", "dwxz" });
		request.setAttribute("dwxzdmList", dwxzdmList);

		sql = "select dwxzdm,dwxz from dmk_dwxz2"; // 单位性质代码2
		List dwxzdm2List = dao.getList(sql, new String[] {}, new String[] {
				"dwxzdm", "dwxz" });
		request.setAttribute("dwxzdm2List", dwxzdm2List);

		sql = "select dqlxdm,dqlx from dmk_dqlx"; // 地区流向
		List dqlxdmList = dao.getList(sql, new String[] {}, new String[] {
				"dqlxdm", "dqlx" });
		request.setAttribute("dqlxdmList", dqlxdmList);

		sql = "select hyfldm,hyfl from dmk_hyfl"; // 行业分类
		List hyflList = dao.getList(sql, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);

		String xxdm1 = (String) request.getSession().getAttribute("xxdm"); // 2学校代码
		String xsxh1 = DealString.toGBK(request.getParameter("xsxh"));
		if ("12453".equals(xxdm1) && !"".equals(xsxh1)) {
			String[] str = dao.getOneRs(
					"select sfsh from jygl_xsjbxxb where xsxh=?",
					new String[] { xsxh1 }, new String[] { "sfsh" });
			@SuppressWarnings("unused")
			boolean bool = false;
			if (str != null) {
				if ("已通过√".equals(str[0])) {
					bool = true;
				} else {
					doType = "shwtg";
					request.setAttribute("exists", "exists");
				}
			}
		}

		boolean isSave = false;

		if ("save".equals(doType)) {
			boolean save = false;
			String id = DealString.toGBK(request.getParameter("id")); // 1身份证号
			String xxdm = DealString.toGBK(request.getParameter("xxdm")); // 2学校代码
			String xx = DealString.toGBK(request.getParameter("xxmc")); // 3学校名称
			String zydm = DealString.toGBK(request.getParameter("zydm")); // 4专业代码
			String zy = DealString.toGBK(request.getParameter("zymc")); // 5专业名称
			String xsxh = DealString.toGBK(request.getParameter("xsxh")); // 6学号
			String name = DealString.toGBK(request.getParameter("name")); // 7姓名
			String xbdm = DealString.toGBK(request.getParameter("xbdm")); // 8性别代码
			String xldm = DealString.toGBK(request.getParameter("xldm")); // 9学历代码
			String xzdm = DealString.toGBK(request.getParameter("xzdm")); // 10学制
			String sydqdm = DealString.toGBK(request.getParameter("sydqdm")); // 11生源地区代码
			String pyfsdm = DealString.toGBK(request.getParameter("pyfsdm")); // 12培养方式代码
			String memo = DealString.toGBK(request.getParameter("memo")); // 13备注
			String zzmmdm = DealString.toGBK(request.getParameter("zzmmdm")); // 14政治面貌代码
			String sydq = DealString.toGBK(request.getParameter("sydq")); // 15生源地区名称
			String xxdjh = DealString.toGBK(request.getParameter("xxdjh")); // 16信息登记号
			String zzjgdm = DealString.toGBK(request.getParameter("zzjgdm")); // 17组织机构代码
			String dwmc = DealString.toGBK(request.getParameter("dwmc")); // 18单位名称
			String dwdqdm = DealString.toGBK(request.getParameter("dwdqdm")); // 19单位地区代码
			String dwdq = DealString.toGBK(request.getParameter("dwdq")); // 20单位地区名称
			String zgbm = DealString.toGBK(request.getParameter("zgbm")); // 21主管部门名称
			byqxdm = DealString.toGBK(request.getParameter("byqxdm")); // 22毕业去向代码
			String blueno = DealString.toGBK(request.getParameter("blueno")); // 23蓝表批准文号
			String wjybz = DealString.toGBK(request.getParameter("wjybz")); // 24未就业标志
			String wjyyy = DealString.toGBK(request.getParameter("wjyyy")); // 25未就业原因
			String lxdz = DealString.toGBK(request.getParameter("lxdz")); // 26联系地址
			String yb = DealString.toGBK(request.getParameter("yb")); // 27邮编
			String dh = DealString.toGBK(request.getParameter("dh")); // 28电话
			// String bgdabz = request.getParameter("bgdabz"); // 29保管档案标志
			String bz1 = DealString.toGBK(request.getParameter("bz1")); // 30自定义1
			String bz2 = DealString.toGBK(request.getParameter("bz2")); // 31自定义2
			String bz3 = DealString.toGBK(request.getParameter("bz3")); // 32自定义3
			String bz4 = DealString.toGBK(request.getParameter("bz4")); // 33自定义4
			String bz5 = DealString.toGBK(request.getParameter("bz5")); // 34自定义5
			String jzzhlbbzwdm = DealString.toGBK(request
					.getParameter("jzzhlbbzwdm")); // 35
			// 居住证或蓝表标志位
			String sydzgbm = DealString.toGBK(request.getParameter("sydzgbm")); // 36生源地主管单位
			String dwxzdm = DealString.toGBK(request.getParameter("dwxzdm")); // 37单位性质代码
			String zgbmdm = DealString.toGBK(request.getParameter("zgbmdm")); // 38主管部门代码
			String dwxzdm2 = DealString.toGBK(request.getParameter("dwxzdm2")); // 39单位性质代码2
			String dwdz = DealString.toGBK(request.getParameter("dwdz")); // 40单位地址
			String dwdh = DealString.toGBK(request.getParameter("dwdh")); // 41单位电话
			String dwyb = DealString.toGBK(request.getParameter("dwyb")); // 42单位邮编
			String dajsd = DealString.toGBK(request.getParameter("dajsd")); // 43档案接受地
			String dajsddz = DealString.toGBK(request.getParameter("dajsddz")); // 44档案接受地地址
			String dajsdyb = DealString.toGBK(request.getParameter("dajsdyb")); // 45档案接受地邮编
			String dynypjgz = DealString
					.toGBK(request.getParameter("dynypjgz")); // 46第一年月平均工资
			String wyj = DealString.toGBK(request.getParameter("wyj")); // 47违约金
			String dqlx = DealString.toGBK(request.getParameter("dqlx")); // 48地区流向
			String hyfl = DealString.toGBK(request.getParameter("hyfl")); // 49行业分类
			String zydk = DealString.toGBK(request.getParameter("zydk")); // 50专业对口
			String xysbh = DealString.toGBK(request.getParameter("xysbh"));
			String dwlxr = request.getParameter("dwlxr");
			String dajsddwmc = request.getParameter("dajsddwmc");
			String dwgm = request.getParameter("dwgm");
			String dwzczj = request.getParameter("dwzczj");
			String bdkssj = request.getParameter("bdkssj");
			String bdjssj = request.getParameter("bdjssj");
			String bdzh = request.getParameter("bdzh");
			String tjsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 51提交时间
							// //
							// 取至数据库临时表
							new String[] {}, new String[] { "sdate" }))[0];
			String xslb = DealString.toGBK(request.getParameter("xslb")); // 52学生类别
			String bynd = DealString.toGBK(request.getParameter("bynd")); // 53毕业年度
			String xymc = DealString.toGBK(request.getParameter("xymc")); // 59学院名称
			String nd = DealString.toGBK(request.getParameter("nd")); // 60入学年度
			// 54审核时间 55撤消时间 56不通过原因 57辅导员审核 58学校审核
			if (Globals.XXDM_ZGDZDX.equals(whichxxdm)) {
				String jgshi = DealString.toGBK(request.getParameter("jgshi"));
				String zzmm = DealString.toGBK(request.getParameter("zzmm"));
				String mz = DealString.toGBK(request.getParameter("mz"));
				String zymc = DealString.toGBK(request.getParameter("zymc"));
				String qq = DealString.toGBK(request.getParameter("qq"));
				String xb = DealString.toGBK(request.getParameter("xb"));
				String wpdw = DealString.toGBK(request.getParameter("wpdw"));
				String zsmc = DealString.toGBK(request.getParameter("zsmc"));
				String zdcl = DealString.toGBK(request.getParameter("zdcl"));
				String bdzbz = DealString.toGBK(request.getParameter("bdzbz"));
				String sjdw = DealString.toGBK(request.getParameter("sjdw"));
				String pqdw = DealString.toGBK(request.getParameter("pqdw"));
				String pqdwid = DealString
						.toGBK(request.getParameter("pqdwid"));
				String dwxz1 = DealString.toGBK(request.getParameter("dwxz1"));
				String dwxz2 = DealString.toGBK(request.getParameter("dwxz2"));
				@SuppressWarnings("unused")
				String dwxzmc = DealString
						.toGBK(request.getParameter("dwxzmc"));
				String zgbm1 = DealString.toGBK(request.getParameter("zgbm1"));
				String zgbm2 = DealString.toGBK(request.getParameter("zgbm2"));
				String zgbmmc = DealString
						.toGBK(request.getParameter("zgbmmc"));
				String dwszd2 = DealString
						.toGBK(request.getParameter("dwszd2"));
				String dwszd3 = DealString
						.toGBK(request.getParameter("dwszd3"));
				String dwszd1 = DealString
						.toGBK(request.getParameter("dwszd1"));
				String sjszd1 = DealString
						.toGBK(request.getParameter("sjszd1"));
				String sjszd2 = DealString
						.toGBK(request.getParameter("sjszd2"));
				String sjszd3 = DealString
						.toGBK(request.getParameter("sjszd3"));
				String hkdz = DealString.toGBK(request.getParameter("hkdz"));
				String gprq = DealString.toGBK(request.getParameter("gprq"));
				String gpyy = DealString.toGBK(request.getParameter("gpyy"));
				String gpcs = DealString.toGBK(request.getParameter("gpcs"));
				String ydwmc = DealString.toGBK(request.getParameter("ydwmc"));
				String bdzbh = DealString.toGBK(request.getParameter("bdzbh"));
				String xxmc = DealString.toGBK(request.getParameter("xxmc"));
				String jyzk = DealString.toGBK(request.getParameter("jyzk"));
				String jgshi2 = DealString
						.toGBK(request.getParameter("jgshi2"));
				String jgshi3 = DealString
						.toGBK(request.getParameter("jgshi3"));
				save = StandardOperation.insert("jygl_jyxy", new String[] {
						"dwdq", "jgshi2", "jgshi3", "dajsd", "dajsdyb", "memo",
						"byqxdm", "pyfsdm", "tjsj", "nd", "xbdm", "xslb",
						"bynd", "xxdm", "xxmc", "jgshi", "sydqdm", "xzdm",
						"zzmm", "xldm", "xymc", "mz", "zymc", "qq", "xb", "id",
						"name", "xysbh", "xsxh", "wpdw", "zsmc", "zdcl",
						"bdzbz", "sjdw", "pqdw", "pqdwid", "dwxz1", "dwxz2",
						"dwxzdm2", "zgbm1", "zgbm2", "zgbmmc", "dwszd1",
						"dwszd2", "dwszd3", "sjszd1", "sjszd2", "sjszd3",
						"hkdz", "gprq", "gpyy", "gpcs", "ydwmc", "bdzbh",
						"wjybz", "wjyyy", "jyzk" }, new String[] { dwdq,
						jgshi2, jgshi3, dajsd, dajsdyb, memo, byqxdm, pyfsdm,
						tjsj, nd, xbdm, xslb, bynd, xxdm, xxmc, jgshi, sydqdm,
						xzdm, zzmm, xldm, xymc, mz, zymc, qq, xb, id, name,
						xysbh, xsxh, wpdw, zsmc, zdcl, bdzbz, sjdw, pqdw,
						pqdwid, dwxz1, dwxz2, dwxzdm2, zgbm1, zgbm2, zgbmmc,
						dwszd1, dwszd2, dwszd3, sjszd1, sjszd2, sjszd3, hkdz,
						gprq, gpyy, gpcs, ydwmc, bdzbh, wjybz, wjyyy, jyzk },
						request);
			} else if (Globals.XXDM_NGSXY.equalsIgnoreCase(xxdm)) {
				// 南国商学院
				String filePath = null;
				String dir = request.getRealPath("/") + "upload";
				File f = new File(dir);
				if (!f.exists()) {
					f.mkdir();
				}
				String dateStr = "";
				Timestamp date = new Timestamp(System.currentTimeMillis());
				dateStr += date.toString().substring(0, 19);
				dateStr = dateStr.replaceAll("-", "").replaceAll(" ", "")
						.replaceAll(":", "");
				FormFile file = dataSearchForm.getUploadFile();
				if (file != null) {
					String fName = dateStr + file.getFileName();
					filePath = dir + "/" + fName;
				}
				save = StandardOperation.insert("jygl_jyxy", new String[] {
						"id", "xxdm", "xxmc", "zydm", "zymc", "xsxh", "name",
						"xbdm", "xldm", "xzdm", "sydqdm", "pyfsdm", "memo",
						"zzmmdm", "sydq", "xxdjh", "zzjgdm", "dwmc", "dwdqdm",
						"dwdq", "zgbm", "byqxdm", "blueno", "wjybz", "wjyyy",
						"lxdz", "yb", "dh", "bz1", "bz2", "bz3", "bz4", "bz5",
						"jzzhlbbzwdm", "sydzgbm", "dwxzdm", "zgbmdm",
						"dwxzdm2", "dwdz", "dwdh", "dwyb", "dajsd", "dajsddz",
						"dajsdyb", "dynypjgz", "wyj", "dqlx", "hyfl", "zydk",
						"tjsj", "xslb", "bynd", "xymc", "nd", "xysbh", "dwlxr",
						"dajsddwmc", "dwgm", "dwzczj", "bdkssj", "bdjssj",
						"bdzh", "wjdz" }, new String[] { id, xxdm, xx, zydm,
						zy, xsxh, name, xbdm, xldm, xzdm, sydqdm, pyfsdm, memo,
						zzmmdm, sydq, xxdjh, zzjgdm, dwmc, dwdqdm, dwdq, zgbm,
						byqxdm, blueno, wjybz, wjyyy, lxdz, yb, dh, bz1, bz2,
						bz3, bz4, bz5, jzzhlbbzwdm, sydzgbm, dwxzdm, zgbmdm,
						dwxzdm2, dwdz, dwdh, dwyb, dajsd, dajsddz, dajsdyb,
						dynypjgz, wyj, dqlx, hyfl, zydk, tjsj, xslb, bynd,
						xymc, nd, xysbh, dwlxr, dajsddwmc, dwgm, dwzczj,
						bdkssj, bdjssj, bdzh, filePath }, request);

				if (save) {
					// 插入成功进行上传
					int size = file.getFileSize();
					if (size > 0) {// 有文件上传
						InputStream in = file.getInputStream();
						OutputStream out = new FileOutputStream(filePath);
						int bytesRead = 0;
						byte[] buffer = new byte[size];
						while ((bytesRead = in.read(buffer, 0, size)) != -1) {
							out.write(buffer, 0, bytesRead);
						}
						out.close();
						in.close();
					}
				}

			} else {
				save = StandardOperation.insert("jygl_jyxy", new String[] {
						"id", "xxdm", "xxmc", "zydm", "zymc", "xsxh", "name",
						"xbdm", "xldm", "xzdm", "sydqdm", "pyfsdm", "memo",
						"zzmmdm", "sydq", "xxdjh", "zzjgdm", "dwmc", "dwdqdm",
						"dwdq", "zgbm", "byqxdm", "blueno", "wjybz", "wjyyy",
						"lxdz", "yb", "dh", "bz1", "bz2", "bz3", "bz4", "bz5",
						"jzzhlbbzwdm", "sydzgbm", "dwxzdm", "zgbmdm",
						"dwxzdm2", "dwdz", "dwdh", "dwyb", "dajsd", "dajsddz",
						"dajsdyb", "dynypjgz", "wyj", "dqlx", "hyfl", "zydk",
						"tjsj", "xslb", "bynd", "xymc", "nd", "xysbh", "dwlxr",
						"dajsddwmc", "dwgm", "dwzczj", "bdkssj", "bdjssj",
						"bdzh" }, new String[] { id, xxdm, xx, zydm, zy, xsxh,
						name, xbdm, xldm, xzdm, sydqdm, pyfsdm, memo, zzmmdm,
						sydq, xxdjh, zzjgdm, dwmc, dwdqdm, dwdq, zgbm, byqxdm,
						blueno, wjybz, wjyyy, lxdz, yb, dh, bz1, bz2, bz3, bz4,
						bz5, jzzhlbbzwdm, sydzgbm, dwxzdm, zgbmdm, dwxzdm2,
						dwdz, dwdh, dwyb, dajsd, dajsddz, dajsdyb, dynypjgz,
						wyj, dqlx, hyfl, zydk, tjsj, xslb, bynd, xymc, nd,
						xysbh, dwlxr, dajsddwmc, dwgm, dwzczj, bdkssj, bdjssj,
						bdzh }, request);
			}
			if (save) {
				request.setAttribute("save", "ok");
				// 添加操作的记录
				if ("teacher".equals(userType)) {
					String userName = session.getAttribute("userName")
							.toString();
					String whensj = (dao
							.getOneRs(
									"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
									new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

					StandardOperation.insert("JYGL_USERCZMXB", new String[] {
							"userid", "dowhat", "whichtable", "whichpk",
							"whensj" }, new String[] { userName, "增加", "就业协议表",
							"学号:" + xsxh, whensj }, request);
				}
			} else {
				request.setAttribute("save", "no");
			}

			// ----------2010/6/8 edit by luojw -------------

			String[] colList = new String[] { "id", "xxdm", "xxmc", "zydm",
					"zymc", "xsxh", "name", "xbdm", "xldm", "xzdm", "sydqdm",
					"pyfsdm", "memo", "zzmmdm", "sydq", "xxdjh", "zzjgdm",
					"dwmc", "dwdqdm", "dwdq", "zgbm", "byqxdm", "blueno",
					"wjybz", "wjyyy", "lxdz", "yb", "dh", "bz1", "bz2", "bz3",
					"bz4", "bz5", "jzzhlbbzwdm", "sydzgbm", "dwxzdm", "zgbmdm",
					"dwxzdm2", "dwdz", "dwdh", "dwyb", "dajsd", "dajsddz",
					"dajsdyb", "dynypjgz", "wyj", "dqlx", "hyfl", "zydk",
					"tjsj", "xslb", "bynd", "xymc", "nd", "xysbh", "dwlxr",
					"dajsddwmc", "dwgm", "dwzczj", "bdkssj", "bdjssj", "bdzh" };
			String[] valList = new String[] { id, xxdm, xx, zydm, zy, xsxh,
					name, xbdm, xldm, xzdm, sydqdm, pyfsdm, memo, zzmmdm, sydq,
					xxdjh, zzjgdm, dwmc, dwdqdm, dwdq, zgbm, byqxdm, blueno,
					wjybz, wjyyy, lxdz, yb, dh, bz1, bz2, bz3, bz4, bz5,
					jzzhlbbzwdm, sydzgbm, dwxzdm, zgbmdm, dwxzdm2, dwdz, dwdh,
					dwyb, dajsd, dajsddz, dajsdyb, dynypjgz, wyj, dqlx, hyfl,
					zydk, tjsj, xslb, bynd, xymc, nd, xysbh, dwlxr, dajsddwmc,
					dwgm, dwzczj, bdkssj, bdjssj, bdzh };

			getMapInfo(map, colList, valList);

			isSave = true;
			// ----------end-------------
		}

		if ("go".equalsIgnoreCase(act) || "go".equals(doType) || isSave) {
			// 把刷新以前的值传回页面
			String xxdjh = DealString.toGBK(request.getParameter("xxdjh"));
			String zzjgdm = DealString.toGBK(request.getParameter("zzjgdm"));
			String dwmc = DealString.toGBK(request.getParameter("dwmc"));
			String zzmmdm = DealString.toGBK(request.getParameter("zzmmdm"));
			String lxdz = DealString.toGBK(request.getParameter("lxdz"));
			String yb = DealString.toGBK(request.getParameter("yb"));
			String dh = DealString.toGBK(request.getParameter("dh"));
			String jzzhlbbzwdm = DealString.toGBK(request
					.getParameter("jzzhlbbzwdm"));
			String memo = DealString.toGBK(request.getParameter("memo"));
			String sydzgbm = DealString.toGBK(request.getParameter("sydzgbm"));

			// 修改
			String dwxzdm2 = DealString.toGBK(request.getParameter("dwxzdm2"));
			String dajsd = DealString.toGBK(request.getParameter("dajsd"));
			String dwdz = DealString.toGBK(request.getParameter("dwdz"));
			String dajsddz = DealString.toGBK(request.getParameter("dajsddz"));
			String dwdh = DealString.toGBK(request.getParameter("dwdh"));
			String dajsdyb = DealString.toGBK(request.getParameter("dajsdyb"));
			String dwyb = DealString.toGBK(request.getParameter("dwyb"));
			String dqlx = DealString.toGBK(request.getParameter("dqlx"));
			String wyj = DealString.toGBK(request.getParameter("wyj"));
			String hyfl = DealString.toGBK(request.getParameter("hyfl"));
			String dynypjgz = DealString
					.toGBK(request.getParameter("dynypjgz"));
			String zydk = DealString.toGBK(request.getParameter("zydk"));
			String wjyyy = DealString.toGBK(request.getParameter("wjyyy"));
			String wjybz = DealString.toGBK(request.getParameter("wjybz"));
			String bz1 = DealString.toGBK(request.getParameter("bz1"));
			String bz2 = DealString.toGBK(request.getParameter("bz2"));
			String bz3 = DealString.toGBK(request.getParameter("bz3"));
			String bz4 = DealString.toGBK(request.getParameter("bz4"));
			String bz5 = DealString.toGBK(request.getParameter("bz5"));
			// String bdkssj = request.getParameter("bdkssj");
			// String bdjssj = request.getParameter("bdjssj");
			// String bdzh = request.getParameter("bdzh");

			map.put("dwxzdm2", dwxzdm2);
			map.put("dajsd", dajsd);
			map.put("dwdz", dwdz);
			map.put("dajsddz", dajsddz);
			map.put("dwdh", dwdh);
			map.put("dajsdyb", dajsdyb);
			map.put("dwyb", dwyb);
			map.put("dqlx", dqlx);
			map.put("wyj", wyj);
			map.put("hyfl", hyfl);
			map.put("dynypjgz", dynypjgz);
			map.put("zydk", zydk);
			map.put("wjybz", wjybz);
			map.put("wjyyy", wjyyy);
			map.put("bz1", bz1);
			map.put("bz2", bz2);
			map.put("bz3", bz3);
			map.put("bz4", bz4);
			map.put("bz5", bz5);

			map.put("xxdjh", xxdjh);
			map.put("zzjgdm", zzjgdm);
			map.put("dwmc", dwmc);
			map.put("zzmmdm", zzmmdm);
			map.put("lxdz", lxdz);
			map.put("yb", yb);
			map.put("dh", dh);
			map.put("jzzhlbbzwdm", jzzhlbbzwdm);
			map.put("memo", memo);
			map.put("sydzgbm", sydzgbm);

			if ("go".equals(doType)) {
				xsxhao = request.getParameter("xsxh");

			}
			if (userType.equalsIgnoreCase("student")) {
				xsxhao = session.getAttribute("userName").toString();
			}
			sql = "select * from jygl_xsjbxxb where xsxh=?"; // 查询该学号的相关内容的sql语句
			String[] colList = dao
					.getColumnName("select * from jygl_xsjbxxb where 1=2"); // 返回列名数组
			String[] rs = dao.getOneRs(sql, new String[] { xsxhao }, colList); // 获得从毕业生基本信息表（视图）中查询的记录集
			if (rs != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), rs[i]); // 将记录循环放入map中
				}
			}

			String zydm = map.get("zydm");
			sql = "select jygl_zydm,jygl_zymc from DMK_ZYDMDZB where jwc_zydm=?";
			String[] dminfo = dao.getOneRs(sql, new String[] { zydm },
					new String[] { "jygl_zydm", "jygl_zymc" });
			if (null != dminfo) {
				map.put("zydm", dminfo[0]);
				map.put("zymc", dminfo[1]);
			}
			if (Globals.XXDM_ZGDZDX.equals(whichxxdm)) {
				// 转换培养性别代码
				if ("1".equals(map.get("xbdm"))) {
					map.put("xb", "男");
				} else {
					map.put("xb", "女");
				}
				// 学制代码转换
				// String xz = (map.get("xzdm")) + "年";
				// map.put("xzdm", xz);
				// 转换学历代码
				// sql = "select xl from dmk_xl where xldm=?";
				// String xldm = map.get("xldm");
				// String xl = "";
				// String[] xlinfo = dao.getOneRs(sql, new String[] { xldm },
				// new String[] { "xl" });
				// if (null != xlinfo) {
				// xl = xlinfo[0];
				// }
				// map.put("xlmc", xl);
				// // 转换培养方式代码(本专生，研究生)
				// String xslb = map.get("xslb");
				// if ("专科生".equals(xslb) || "本科生".equals(xslb)) {
				// sql = "select pyfs from dmk_bzpyfs where pyfsdm=?";
				// String pyfsdm = map.get("pyfsdm");
				// String pyfs = "";
				// String[] pyfsinfo = dao.getOneRs(sql, new String[] { pyfsdm
				// },
				// new String[] { "pyfs" });
				// if (null != pyfsinfo) {
				// pyfs = pyfsinfo[0];
				// }
				// map.put("pyfsdm", pyfs);
				// } else if ("研究生".equals(xslb)) {
				// sql = "select pyfs from dmk_yjspyfs where pyfsdm=?";
				// String pyfsdm = map.get("pyfsdm");
				// String pyfs = "";
				// String[] pyfsinfo = dao.getOneRs(sql, new String[] { pyfsdm
				// },
				// new String[] { "pyfs" });
				// if (null != pyfsinfo) {
				// pyfs = pyfsinfo[0];
				// }
				// map.put("pyfsdm", pyfs);
				// }
			}

			String sydqdm = map.get("sydqdm");
			sql = "select sydq from dmk_sydq where sydqdm=?";
			String[] sydqmc = dao.getOneRs(sql, new String[] { sydqdm },
					new String[] { "sydq" });
			if (sydqmc != null) {
				map.put("sydq", sydqmc[0]);
			}
			if (!Base.isNull(dwmc)) {
				map.put("dwmc", dwmc);
			}
			sql = "select xymc from view_xsjbxx where xh=?";
			String[] xymc1 = dao.getOneRs(sql, new String[] { xsxhao },
					new String[] { "xymc" });
			if (xymc1 != null) {
				map.put("xymc", xymc1[0]);
				map.put("dwdq", DealString.toGBK(request.getParameter("dwdq")));
			}

			if ("go".equals(request.getParameter("doType"))
					|| "go".equals(request.getParameter("act"))) {
				String dwdq = DealString.toGBK(request.getParameter("dwdq"));
				sql = "select * from dmk_dwdq where dwdq=?";
				String[] rs1 = dao.getOneRs(sql, new String[] { dwdq },
						new String[] { "dwdqdm" });
				map.put("dwdqdm", "310100");
				if (rs1 != null) {
					map.put("dwdqdm", rs1[0]);
				}
				if ("04".equals(request.getParameter("byqxdm"))) {
					map.put("dwdqdm", "990000");
				}
			}
			map.put("zgbm", DealString.toGBK(request.getParameter("zgbm")));

			if ("01".equals(byqxdm) || "13".equals(byqxdm)
					|| "14".equals(byqxdm) || "15".equals(byqxdm)
					|| "17".equals(byqxdm)) {
				String zgbm = DealString.toGBK(request.getParameter("zgbm"));
				sql = "select * from dmk_zgbm where zgbm=?";
				String[] rs2 = dao.getOneRs(sql, new String[] { zgbm },
						new String[] { "zgbmdm" });
				if (rs2 != null) {
					map.put("zgbmdm", rs2[0]);
				}
			}
		}

		map.put("byqxdm", byqxdm);
		if ("save".equals(doType)) {
			map.put("byqxdm", "");
		}
		map.put("stuExists", "yes");
		map.put("userType", userType);

		String cgxys = request.getParameter("cgxys");
		if ("cgxys".equals(cgxys) || cgxys == "cgxys") {
			map.put("xh", map.get("xsxh"));
			map.put("xm", map.get("name"));
		}
		request.setAttribute("rs", map);
		// String dd = map.get("dwmc").toString();
		String xxdm = dao.getXxdm();

		if (Globals.XXDM_ZGDZDX.equals(xxdm)) {
			request.setAttribute("shiList", stuDao.getShiList("")
					.get("shiList"));
			request.setAttribute("xianList", stuDao.getShiList("").get(
					"xianList"));
			request.setAttribute("ssList", stuDao.getSsList());
			return mapping.findForward("successdz");
		} else if (Globals.XXDM_CSLGXY.equals(xxdm)) {// 常熟理工学院
			request.setAttribute("shiList", stuDao.getShiList("")
					.get("shiList"));
			request.setAttribute("xianList", stuDao.getShiList("").get(
					"xianList"));
			request.setAttribute("ssList", stuDao.getSsList());
			return mapping.findForward("success");
		} else if ("cgxys".equals(cgxys) || cgxys == "cgxys") {
			return mapping.findForward("success1");
		} else {
			return mapping.findForward("success");
		}
	}

	// 弹出窗口查询学生信息 然后填充至1、就业协议
	private ActionForward jyxyTurnInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		CommanForm commanForm = (CommanForm) form;
		DAO dao = DAO.getInstance(); // 实例化通用方法，并获得数据库连接
		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = ""; // sql语句
		String querry = " where 1=1 "; // sql条件
		String tableName = "jygl_xsjbxxb"; // 查询信息源表（多为视图名）
		String rsNum = "0";// 返回的记录数
		String dataType = DealString.toGBK(request.getParameter("act")); // 接收数据类型
		String xsxh = DealString.toGBK(request.getParameter("xsxh")); // 接收学号
		String name = DealString.toGBK(request.getParameter("name")); // 接收姓名
		String xbdm = DealString.toGBK(request.getParameter("xbdm")); // 接收性别代码
		String xslb = DealString.toGBK(request.getParameter("xslb")); // 接收学生类别
		String xymc = DealString.toGBK(request.getParameter("xymc")); // 接收学院名称
		String nd = DealString.toGBK(request.getParameter("nd")); // 接收年度
		String bynd = DealString.toGBK(request.getParameter("bynd")); // 毕业年度
		String pk = "xsxh"; // 主键
		HashMap<String, String> map = new HashMap<String, String>();
		String act = DealString.toGBK(request.getParameter("act"));
		String bysjytj = request.getParameter("bysjytjb");
		if (StringUtils.isNotNull(bysjytj)) {
			request.setAttribute("bysjytjb", bysjytj);
		}

		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		if ("teacher".equals(userType)) {
			sql = "select usertype,xymc from JYGL_JYXYWHB where username=?";
			String[] teainfo = dao.getOneRs(sql, new String[] { userName },
					new String[] { "usertype", "xymc" });
			String userty = "";
			if (null != teainfo) {
				userty = teainfo[0];
				xymc = teainfo[1];
			}
			if ("辅导员".equals(userty)) {
				map.put("xymc", xymc);
				request.setAttribute("who", "fudaoyuan");
			} else {
				request.setAttribute("who", "teacher");
			}
		}

		if ("go".equals(act)) {
			map.put("xsxh", xsxh);
			map.put("name", name);
			map.put("xbdm", xbdm);
			map.put("xslb", xslb);
			map.put("xymc", xymc);
			map.put("nd", nd);
			map.put("bynd", bynd);
		}

		if (xsxh == null) {
			xsxh = "";
		}
		if (name == null) {
			name = "";
		}
		if (xbdm == null) {
			xbdm = "";
		}
		if (xslb == null) {
			xslb = "";
		}
		if (xymc == null) {
			xymc = "";
		}
		if (nd == null) {
			nd = "";
		}
		if (bynd == null) {
			bynd = "";
		}
		if ((xsxh == null) || xsxh.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xsxh like '%" + xsxh + "%' ";
		}
		if ((name == null) || name.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and name like '%" + name + "%' ";
		}
		if ((xbdm == null) || xbdm.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xbdm='" + xbdm + "' ";
		}
		if ((xslb == null) || xslb.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xslb='" + xslb + "' ";
		}
		if ((xymc == null) || xymc.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xsxh in (select xsxh from jygl_xsjbxxb where xsxh in (select xh from view_xsjbxx where xymc ='"
					+ xymc + "'))";
		}
		if ((nd == null) || nd.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and nd='" + nd + "' ";
		}
		if ((bynd == null) || bynd.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and bynd='" + bynd + "' ";
		}

		sql = "select " + pk + " 主键,rownum r,a.nd,a.bynd,a.xsxh,a.name,a.xslb,a.zymc,a.sbsj," +
				"case when xbdm='1' then '男' else '女' end xb from " + tableName + " a "
				+ querry;
		colList = new String[] { "主键", "r", "nd", "bynd", "xsxh", "name",
				"xb", "xslb", "zymc", "sbsj" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(CommonQueryDAO.commonQuery(sql, "", new String[] {},
					colList, commanForm));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		String cgxys = request.getParameter("cgxys");
		if (cgxys == "cgxys" || "cgxys".equals(cgxys)) {
			request.setAttribute("cgxys", cgxys);
		}
		request.setAttribute("ndList", dao.getNjList());// 发送年度列表
		request.setAttribute("xyList", dao.getXyList());// 发送学院列表
		request.setAttribute("act", dataType);// 发送数据查询类型
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("rs1", map);// 发送查询条件数组
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		return mapping.findForward("success");
	}

	// 就业协议查询
	private ActionForward JyxyDataSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) throws Exception {
		CommanForm commanForm = (CommanForm) form;
		DAO dao = DAO.getInstance(); // 实例化通用方法，并获得数据库连接
		ArrayList<Object> rs = new ArrayList<Object>(); // 获得记录
		ArrayList<HashMap<String, String>> ynysrs = new ArrayList<HashMap<String, String>>();// 针对云南艺术
		String[] colList = null; // 列名数组
		String[] colListCN = null; // 中文列名数组
		String sql = ""; // sql语句
		String querry = " where 1=1 "; // sql条件
		String tableName = "jygl_jyxy"; // 查询信息源表（多为视图名）
		String rsNum = "0"; // 返回的记录数
		String dataType = DealString.toGBK(request.getParameter("act")); // 接收数据类型
		String xsxh = DealString.toGBK(request.getParameter("xsxh")); // 接收学号
		String name = DealString.toGBK(request.getParameter("name")); // 接收姓名
		String xbdm = DealString.toGBK(request.getParameter("xbdm")); // 接收性别代码
		String xslb = DealString.toGBK(request.getParameter("xslb")); // 接收学生类别
		String xymc = DealString.toGBK(request.getParameter("xymc")); // 接收学院名称
		String bynd = DealString.toGBK(request.getParameter("bynd")); // 毕业年度
		String nd = DealString.toGBK(request.getParameter("nd")); // 入学年度
		String wjybz = DealString.toGBK(request.getParameter("wjybz")); // 未就业标志
		String fdysh = DealString.toGBK(request.getParameter("fdysh")); // 辅导员审核
		String xxsh = DealString.toGBK(request.getParameter("xxsh")); // 学校审核
		String pk = "xsxh"; // 主键
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = DealString.toGBK(request.getParameter("doType"));
		String doType2 = DealString.toGBK(request.getParameter("doType2"));
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));

		HttpSession session = request.getSession();// 从session中获得信息
		String userName = session.getAttribute("userName").toString();
		String ynys_userty = session.getAttribute("userType").toString();
		String ynys_userdep = session.getAttribute("userDep").toString();
		String xysbh = request.getParameter("xysbh");
		String userlx = "";

		String whichxx = StandardOperation.getXxdm();

		if (whichxx.equalsIgnoreCase(Globals.XXDM_YNYS)) {// 云南艺术

			if (ynys_userty.equalsIgnoreCase("xx")
					|| ynys_userty.equalsIgnoreCase("admin")) {
				request.setAttribute("who", "xx");

			} else if (ynys_userty.equalsIgnoreCase("xy")) {
				request.setAttribute("who", "xy");
			}

			StringBuffer query = new StringBuffer();
			query.append(" where 1=1 ");
			String xh = DealString.toGBK(request.getParameter("xh"));
			String xm = DealString.toGBK(request.getParameter("xm"));
			String xb = DealString.toGBK(request.getParameter("xb"));
			String nj = DealString.toGBK(request.getParameter("nj"));
			String xydm = DealString.toGBK(request.getParameter("xydm"));
			String zydm = DealString.toGBK(request.getParameter("zydm"));
			String bjdm = DealString.toGBK(request.getParameter("bjdm"));

			if ("del".equals(doType2)) {
				boolean judge = false;
				sql = "delete from ynys_jygl_jyxy where xh=?";
				judge = StandardOperation.delete("ynys_jygl_jyxy", "xh",
						pkValue, request);
				if (judge) {
					request.setAttribute("delete", "ok");
				} else {
					request.setAttribute("delete", "no");
				}
			}
			map.put("xh", xh);
			map.put("xm", xm);
			map.put("xb", xb);
			map.put("nj", nj);
			map.put("xydm", xydm);
			map.put("zydm", zydm);
			map.put("bjdm", bjdm);

			if (ynys_userty.equalsIgnoreCase("xy")) {
				map.put("xydm", ynys_userdep);
			}

			if (!"".equalsIgnoreCase(xh) && null != xh) {
				query.append(" and xh like '%");
				query.append(xh);
				query.append("%' ");
			}
			if (!"".equalsIgnoreCase(xm) && null != xm) {
				query.append(" and xm like '%");
				query.append(xm);
				query.append("%' ");
			}
			if (!"".equalsIgnoreCase(xb) && null != xb) {
				query.append(" and xb like '%");
				query.append(xb);
				query.append("%' ");
			}

			if (!"".equalsIgnoreCase(xydm) && null != xydm) {
				query.append(" and xydm='");
				query.append(xydm);
				query.append("' ");
			}

			if (!"".equalsIgnoreCase(zydm) && null != zydm) {
				query.append(" and zydm='");
				query.append(zydm);
				query.append("' ");
			}

			if (!"".equalsIgnoreCase(bjdm) && null != bjdm) {
				query.append(" and bjdm='");
				query.append(bjdm);
				query.append("' ");
			}

			querry = query.toString();

			sql = "select rownum 行号,a.* from view_ynys_jygl_jyxy a " + querry;
			colList = new String[] { "行号", "xh", "xm", "xb", "xymc", "zymc",
					"bjmc", "fbsj" };
			colListCN = dao.getColumnNameCN(colList, "view_ynys_jygl_jyxy");
			List topTr = dao.arrayToList(colList, colListCN);
			if ((request.getParameter("act") != null)
					&& request.getParameter("act").equalsIgnoreCase("go")) {
				ynysrs = dao.getArrayList2(sql, new String[] {}, colList);
				if (rs == null) {
					rsNum = "0";
				} else {
					rsNum = String.valueOf(ynysrs.size());
				}
			}

			request.setAttribute("njList", dao.getNjList()); // 发送年级列表
			request.setAttribute("xyList", dao.getXyList());// 发送学院列表
			request.setAttribute("zyList", dao.getZyList(xydm));// 发送专业列表
			request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));// 发送班级列表
			request.setAttribute("rs", ynysrs); // 发送数据集
			request.setAttribute("topTr", topTr); // 发送表头
			request.setAttribute("rsNum", rsNum); // 发送记录数
			request.setAttribute("rs1", map); // 把查询条件再次封装后发送回去
			return mapping.findForward("success2");

		}

		if ("teacher".equals(userType)) {
			sql = "select usertype,xymc from JYGL_JYXYWHB where username=?";
			String[] teainfo = dao.getOneRs(sql, new String[] { userName },
					new String[] { "usertype", "xymc" });
			String userty = "";
			if (null != teainfo) {
				userty = teainfo[0];
				xymc = teainfo[1];
			}
			if ("辅导员".equals(userty)) {
				map.put("xymc", xymc);
				request.setAttribute("who", "fudaoyuan");
			} else {
				request.setAttribute("who", "teacher");
			}
		}
		if (ynys_userty.equalsIgnoreCase("xx")
				|| ynys_userty.equalsIgnoreCase("admin")) {
			request.setAttribute("whos", "xx");

		} else if (ynys_userty.equalsIgnoreCase("xy")) {
			request.setAttribute("whos", "xy");
		}
		// 删除就业协议数据
		if ("del".equals(doType2)) {
			DyxxDAO dyxxDao = new DyxxDAO();
			boolean judge = false;
			String xxdm = Base.xxdm;
			sql = "delete from " + tableName + " where " + pk + "='" + pkValue
					+ "'";
			if (Globals.XXDM_NGSXY.equalsIgnoreCase(xxdm)) {
				dyxxDao.fileDel(tableName, "wjdz", pk, pkValue);
			}
			judge = dao.runUpdate(sql, new String[] {});

			if (judge) {
				request.setAttribute("delete", "ok");

				// 删除操作的记录
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"删除", "就业协议表", "学号:" + pkValue, whensj },
						request);
			} else {
				request.setAttribute("delete", "no");
			}
		}

		// 把上次提交的值传回去
		if ("query".equals(doType)) {
			map.put("xsxh", xsxh);
			map.put("name", name);
			map.put("xbdm", xbdm);
			map.put("xslb", xslb);
			map.put("xymc", xymc);
			map.put("bynd", bynd);
			map.put("wjybz", wjybz);
			map.put("fdysh", fdysh);
			map.put("xxsh", xxsh);
			map.put("nd", nd);
		}

		if (xsxh == null) {
			xsxh = "";
		}
		if (name == null) {
			name = "";
		}
		if (xbdm == null) {
			xbdm = "";
		}
		if (xslb == null) {
			xslb = "";
		}
		if (xymc == null) {
			xymc = "";
		}
		if (bynd == null) {
			bynd = "";
		}
		if (wjybz == null) {
			wjybz = "";
		}
		if (fdysh == null) {
			fdysh = "";
		}
		if (xxsh == null) {
			xxsh = "";
		}
		if (nd == null) {
			nd = "";
		}

		if ((xsxh == null) || xsxh.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xsxh like '%" + xsxh + "%' ";
		}
		if ((name == null) || name.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and name like '%" + name + "%' ";
		}
		if ((xbdm == null) || xbdm.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xbdm='" + xbdm + "' ";
		}
		if ((xslb == null) || xslb.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xslb='" + xslb + "' ";
		}
		if ((xymc == null) || xymc.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xymc='" + xymc + "' ";
		}
		if ((bynd == null) || bynd.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and bynd='" + bynd + "' ";
		}
		if ((wjybz == null) || wjybz.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and wjybz='" + wjybz + "' ";
		}
		if ((fdysh == null) || fdysh.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and fdysh='" + fdysh + "' ";
		}
		if ((xxsh == null) || xxsh.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xxsh='" + xxsh + "' ";
		}
		if ((nd == null) || nd.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and nd='" + nd + "' ";
		}
		if ("12061".equals(whichxx) || "11122".equals(whichxx)) {
			if ("xx".equals(ynys_userty) || "admin".equals(ynys_userty)) {
				querry += " and fdysh='已通过√'";
			}
		}
		if (StringUtils.isNotNull(xysbh)) {
			querry += " and xysbh='" + xysbh + "' ";
		}

		sql = "select "
				+ pk
				+ " 主键,rownum r,(select xb from dmk_xb b where a.xbdm=b.xbdm) xb,a.nd,a.bynd,a.name,a.xsxh,a.xslb,a.xymc,a.zymc,a.fdysh,a.xxsh from "
				+ tableName + " a " + querry;
		colList = new String[] { "主键", "r", "nd", "bynd", "name", "xsxh", "xb",
				"xslb", "xymc", "zymc", "fdysh", "xxsh" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(CommonQueryDAO.commonQuery(sql, "", new String[] {},
					colList, commanForm));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		if ("xx".equals(ynys_userty) || "admin".equals(ynys_userty)) {
			userlx = "xx";
		}
		if (ynys_userty.equalsIgnoreCase("xy")) {
			map.put("xymc", session.getAttribute("bmmc").toString());
			request.setAttribute("whos", "xy");
		}

		request.setAttribute("userlx", userlx);
		request.setAttribute("realTable", "jygl_jyxy"); // 表是学生基本信息表
		request.setAttribute("ndList", dao.getNjList()); // 发送入学年度列表
		request.setAttribute("xyList", dao.getXyList());// 发送学院列表
		request.setAttribute("act", dataType); // 发送数据查询类型
		request.setAttribute("rs", rs); // 发送数据集
		request.setAttribute("topTr", topTr); // 发送表头
		request.setAttribute("rsNum", rsNum); // 发送记录数
		request.setAttribute("rs1", map); // 把查询条件再次封装后发送回去
		return mapping.findForward("success");
	}

	// 点击查看就业协议更详细信息
	private ActionForward JyglJyxyViewMoreinfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = "xsxh";
		String sql = ""; // sql语句
		DAO dao = DAO.getInstance();
		String realTable = "jygl_jyxy";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();
		String whichxx = StandardOperation.getXxdm();

		if (whichxx.equalsIgnoreCase(Globals.XXDM_YNYS)) {// 云南艺术
			if (doType.equalsIgnoreCase("view")) {
				sql = "select * from ynys_jygl_jyxy where xh='" + pkValue + "'";
				String[] colList = dao
						.getColumnName("select * from ynys_jygl_jyxy where 1=2"); // 返回列名数组
				String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
				if (stuinfo != null) {
					for (int i = 0; i < colList.length; i++) {
						map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
					}

				}
			}
			request.setAttribute("rs", map);// 发送数据集
			return mapping.findForward("success2");

		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// 查询数据
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from jygl_jyxy where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				// 辅导员审核时间
				String fdyshsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				String sjmin = null;
				String sjss = null;
				if (null != map.get("fdyshsj")) {
					String sj = map.get("fdyshsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					sjmin = sj.substring(10, 12);
					sjss = sj.substring(12, 14);
					fdyshsj = sjyear + "年" + sjmon + "月" + sjday + "日 "
							+ sjhour + ":" + sjmin + ":" + sjss;
					map.put("fdyshsj", fdyshsj);
				}
				// 学校审核时间
				String xxshsj = null;
				sjyear = null;
				sjmon = null;
				sjday = null;
				sjhour = null;
				sjmin = null;
				sjss = null;
				if (null != map.get("xxshsj")) {
					String sj1 = map.get("xxshsj").toString();
					sjyear = sj1.substring(0, 4);
					sjmon = sj1.substring(4, 6);
					sjday = sj1.substring(6, 8);
					sjhour = sj1.substring(8, 10);
					sjmin = sj1.substring(10, 12);
					sjss = sj1.substring(12, 14);
					xxshsj = sjyear + "年" + sjmon + "月" + sjday + "日 " + sjhour
							+ ":" + sjmin + ":" + sjss;
					map.put("xxshsj", xxshsj);
				}
				// 发布时间
				String sj;
				String tjsj = null;
				if (null == map.get("tjsj")) {
					sj = "";
				} else {
					sj = map.get("tjsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					tjsj = sjyear + "年" + sjmon + "月" + sjday + "日" + sjhour
							+ "时";
				}
				map.put("tjsj", tjsj);
			}
			// 转换培养性别代码
			if ("1".equals(map.get("xbdm"))) {
				map.put("xbdm", "男");
			} else {
				map.put("xbdm", "女");
			}
			// 学制代码转换
			String xz = (map.get("xzdm")) + "年";
			map.put("xzdm", xz);
			// 转换学历代码
			sql = "select xl from dmk_xl where xldm=?";
			String xldm = map.get("xldm");
			String xl = "";
			String[] xlinfo = dao.getOneRs(sql, new String[] { xldm },
					new String[] { "xl" });
			if (null != xlinfo) {
				xl = xlinfo[0];
			}
			map.put("xldm", xl);
			// 转换培养方式代码(本专生，研究生)
			String xslb = map.get("xslb");
			if ("专科生".equals(xslb) || "本科生".equals(xslb)) {
				sql = "select pyfs from dmk_bzpyfs where pyfsdm=?";
				String pyfsdm = map.get("pyfsdm");
				String pyfs = "";
				String[] pyfsinfo = dao.getOneRs(sql, new String[] { pyfsdm },
						new String[] { "pyfs" });
				if (null != pyfsinfo) {
					pyfs = pyfsinfo[0];
				}
				map.put("pyfsdm", pyfs);
			} else if ("研究生".equals(xslb)) {
				sql = "select pyfs from dmk_yjspyfs where pyfsdm=?";
				String pyfsdm = map.get("pyfsdm");
				String pyfs = "";
				String[] pyfsinfo = dao.getOneRs(sql, new String[] { pyfsdm },
						new String[] { "pyfs" });
				if (null != pyfsinfo) {
					pyfs = pyfsinfo[0];
				}
				map.put("pyfsdm", pyfs);
			}
			// 转换毕业去向代码
			sql = "select byqx from dmk_byqx where byqxdm=?";
			String byqxdm = map.get("byqxdm");
			String byqx = "";
			String[] byqxinfo = dao.getOneRs(sql, new String[] { byqxdm },
					new String[] { "byqx" });
			if (null != byqxinfo) {
				byqx = byqxinfo[0];
			}
			map.put("byqxdm", byqx);
			// 转换政治面貌代码
			sql = "select zzmm from dmk_zzmm where zzmmdm=?";
			String zzmmdm = map.get("zzmmdm");
			String zzmm = "";
			String[] zzmminfo = dao.getOneRs(sql, new String[] { zzmmdm },
					new String[] { "zzmm" });
			if (null != zzmminfo) {
				zzmm = zzmminfo[0];
			}
			map.put("zzmmdm", zzmm);
			// 转换未就业标志
			if ("0".equals(map.get("wjybz"))) {
				map.put("wjybz", "就业");
			} else if ("1".equals(map.get("wjybz"))) {
				map.put("wjybz", "未就业");
			}
			// 转换居住证或蓝表标志位
			sql = "select JZZHLBBZW from dmk_JZZHLBBZW where jzzhlbbzwdm=?";
			String jzzhlbbzwdm = map.get("jzzhlbbzwdm");
			String jzzhlbbzw = "";
			String[] jzzhlbbzwinfo = dao.getOneRs(sql,
					new String[] { jzzhlbbzwdm }, new String[] { "jzzhlbbzw" });
			if (null != jzzhlbbzwinfo) {
				jzzhlbbzw = jzzhlbbzwinfo[0];
			}
			map.put("jzzhlbbzwdm", jzzhlbbzw);
			// 转换单位性质代码
			sql = "select dwxz from dmk_dwxz where dwxzdm=?";
			String dwxzdm = map.get("dwxzdm");
			String dwxz = "";
			String[] dwxzinfo = dao.getOneRs(sql, new String[] { dwxzdm },
					new String[] { "dwxz" });
			if (null != dwxzinfo) {
				dwxz = dwxzinfo[0];
			}
			map.put("dwxzdm", dwxz);
			// 转换单位性质代码2
			sql = "select dwxz from dmk_dwxz2 where dwxzdm=?";
			String dwxzdm2 = map.get("dwxzdm2");
			String dwxz2 = "";
			String[] dwxz2info = dao.getOneRs(sql, new String[] { dwxzdm2 },
					new String[] { "dwxz" });
			if (null != dwxz2info) {
				dwxz2 = dwxz2info[0];
			}
			map.put("dwxzdm2", dwxz2);
			// 转换专业对口代码
			if ("1".equals(map.get("zydk"))) {
				map.put("zydk", "是");
			} else if ("2".equals(map.get("zydk"))) {
				map.put("zydk", "否");
			}
			// 保管档案标志
			if ("1".equals(map.get("bgdabz"))) {
				map.put("bgdabz", "保存档案");
			} else if ("0".equals(map.get("bgdabz"))) {
				map.put("bgdabz", "不保存档案");
			}
			// 转换违约金和第一年月平均工资
			if (map.get("wyj") != "" && null != (map.get("wyj"))) {
				String wyj = map.get("wyj") + "元";
				map.put("wyj", wyj);
			}
			if (map.get("dynypjgz") != "" && null != (map.get("dynypjgz"))) {
				String dynypjgz = map.get("dynypjgz") + "元";
				map.put("dynypjgz", dynypjgz);
			}
		}
		if (!Base.isNull(map.get("wjdz"))
				&& !"".equalsIgnoreCase(map.get("wjdz"))) {
			request.setAttribute("youFj", "youFj");
		}
		request.setAttribute("rs", map);// 发送数据集
		if (Globals.XXDM_ZGDZDX.equals(whichxx)) {
			// 转换省代码
			String sydqdm = map.get("sydqdm");
			sql = "select * from dmk_sydq where sydqdm=?";
			String[] rs1 = dao.getOneRs(sql, new String[] { sydqdm },
					new String[] { "sydq" });
			if (null != rs1) {
				map.put("sydqdm", rs1[0]);
			}
			// 转换市代码
			String jgshi = map.get("jgshi");
			sql = "select qxmc from dmk_qx where qxdm=?";
			String[] rs2 = dao.getOneRs(sql, new String[] { jgshi },
					new String[] { "qxmc" });
			if (null != rs2) {
				map.put("jgshi", rs2[0]);
			}
			//
			// 转换省代码
			String sydqdm1 = map.get("dwszd1");
			sql = "select * from dmk_sydq where sydqdm=?";
			String[] rs3 = dao.getOneRs(sql, new String[] { sydqdm1 },
					new String[] { "sydq" });
			if (null != rs3) {
				map.put("dwszd1", rs3[0]);
			}
			// 转换市代码
			String jgshi2 = map.get("jgshi2");
			sql = "select qxmc from dmk_qx where qxdm=?";
			String[] rs4 = dao.getOneRs(sql, new String[] { jgshi2 },
					new String[] { "qxmc" });
			if (null != rs4) {
				map.put("jgshi2", rs4[0]);
			}
			//
			// 转换省代码
			String dwszd3 = map.get("dwszd3");
			sql = "select * from dmk_sydq where sydqdm=?";
			String[] rs5 = dao.getOneRs(sql, new String[] { dwszd3 },
					new String[] { "sydq" });
			if (null != rs5) {
				map.put("dwszd3", rs5[0]);
			}
			// 转换市代码
			String jgshi3 = map.get("jgshi3");
			sql = "select qxmc from dmk_qx where qxdm=?";
			String[] rs6 = dao.getOneRs(sql, new String[] { jgshi3 },
					new String[] { "qxmc" });
			if (null != rs6) {
				map.put("jgshi3", rs6[0]);
			}
			//
			if ("0".equals(map.get("jyzk"))) {
				map.put("jyzk", "就业");
			} else if ("1".equals(map.get("jyzk"))) {
				map.put("jyzk", "未就业");
			}
			return mapping.findForward("successdz");
		} else {
			return mapping.findForward("success");
		}
	}

	// 弹出辅导员审核窗口并审核内容
	private ActionForward jyglJyxyFdysh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pk = "xsxh";
		String sql = ""; // sql语句
		DAO dao = DAO.getInstance();
		String realTable = "jygl_jyxy";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();
		String fdysh = DealString.toGBK(request.getParameter("fdysh"));
		String xxsh = DealString.toGBK(request.getParameter("xxsh"));
		String btgyy = DealString.toGBK(request.getParameter("btgyy"));
		String xsxh = request.getParameter("xsxh");
		String act = request.getParameter("act");
		HttpSession session = request.getSession();
		String fdyshren = session.getAttribute("userName").toString();

		if ("shenhe".equals(act)) {
			String fdyshsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 辅导员审核时间
							// //
							// 取至数据库临时表
							new String[] {}, new String[] { "sdate" }))[0];
			if ("已通过√".equals(fdysh)) {
				btgyy = "";
			}
			if ("未审核".equals(fdysh) && "未审核".equals(xxsh)) {
				fdyshsj = "";
				fdyshren = "";
			}
			sql = "update " + realTable + " set fdysh='" + fdysh
					+ "' , btgyy='" + btgyy + "' ,fdyshsj='" + fdyshsj
					+ "' ,fdyshren='" + fdyshren + "' where xsxh='" + xsxh
					+ "'";

			boolean judge = false;
			judge = dao.runUpdate(sql, new String[] {});
			if (judge) {
				request.setAttribute("shenhe", "ok");
			} else {
				request.setAttribute("shenhe", "no");
			}

			sql = "select * from jygl_jyxy where xsxh=?";
			String[] colList = dao
					.getColumnName("select * from jygl_jyxy where 1=2"); // 返回列名数组
			String[] stuinfo = dao
					.getOneRs(sql, new String[] { xsxh }, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				fdyshsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				if (null != map.get("fdyshsj")) {
					String sj = map.get("fdyshsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					fdyshsj = sjyear + "年" + sjmon + "月" + sjday + "日" + sjhour
							+ "时";
					map.put("fdyshsj", fdyshsj);
				}
				// 发布时间
				String sj = map.get("tjsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				String tjsj = sjyear + "年" + sjmon + "月" + sjday + "日" + sjhour
						+ "时";
				map.put("tjsj", tjsj);
				// //转换培养性别代码
				if ("1".equals(map.get("xbdm"))) {
					map.put("xbdm", "男");
				} else {
					map.put("xbdm", "女");
				}
				// 学制代码转换
				String xz = (map.get("xzdm")) + "年";
				map.put("xzdm", xz);
				// 转换学历代码
				sql = "select xl from dmk_xl where xldm=?";
				String xldm = map.get("xldm");
				String xl = "";
				String[] xlinfo = dao.getOneRs(sql, new String[] { xldm },
						new String[] { "xl" });
				if (null != xlinfo) {
					xl = xlinfo[0];
				}
				map.put("xldm", xl);
				// 转换培养方式代码(本专生，研究生)
				String xslb = map.get("xslb");
				if ("专科生".equals(xslb) || "本科生".equals(xslb)) {
					sql = "select pyfs from dmk_bzpyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfs = "";
					String[] pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, new String[] { "pyfs" });
					if (null != pyfsinfo) {
						pyfs = pyfsinfo[0];
					}
					map.put("pyfsdm", pyfs);
				} else if ("研究生".equals(xslb)) {
					sql = "select pyfs from dmk_yjspyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfs = "";
					String[] pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, new String[] { "pyfs" });
					if (null != pyfsinfo) {
						pyfs = pyfsinfo[0];
					}
					map.put("pyfsdm", pyfs);
				}
				// 转换毕业去向代码
				sql = "select byqx from dmk_byqx where byqxdm=?";
				String byqxdm = map.get("byqxdm");
				String byqx = "";
				String[] byqxinfo = dao.getOneRs(sql, new String[] { byqxdm },
						new String[] { "byqx" });
				if (null != byqxinfo) {
					byqx = byqxinfo[0];
				}
				map.put("byqxdm", byqx);
				// 转换政治面貌代码
				sql = "select zzmm from dmk_zzmm where zzmmdm=?";
				String zzmmdm = map.get("zzmmdm");
				String zzmm = "";
				String[] zzmminfo = dao.getOneRs(sql, new String[] { zzmmdm },
						new String[] { "zzmm" });
				if (null != zzmminfo) {
					zzmm = zzmminfo[0];
				}
				map.put("zzmmdm", zzmm);
				// 转换未就业标志
				if ("0".equals(map.get("wjybz"))) {
					map.put("wjybz", "就业");
				} else if ("1".equals(map.get("wjybz"))) {
					map.put("wjybz", "未就业");
				}
				// 转换居住证或蓝表标志位
				sql = "select JZZHLBBZW from dmk_JZZHLBBZW where jzzhlbbzwdm=?";
				String jzzhlbbzwdm = map.get("jzzhlbbzwdm");
				String jzzhlbbzw = "";
				String[] jzzhlbbzwinfo = dao.getOneRs(sql,
						new String[] { jzzhlbbzwdm },
						new String[] { "jzzhlbbzw" });
				if (null != jzzhlbbzwinfo) {
					jzzhlbbzw = jzzhlbbzwinfo[0];
				}
				map.put("jzzhlbbzwdm", jzzhlbbzw);
				// 转换单位性质代码
				sql = "select dwxz from dmk_dwxz where dwxzdm=?";
				String dwxzdm = map.get("dwxzdm");
				String dwxz = "";
				String[] dwxzinfo = dao.getOneRs(sql, new String[] { dwxzdm },
						new String[] { "dwxz" });
				if (null != dwxzinfo) {
					dwxz = dwxzinfo[0];
				}
				map.put("dwxzdm", dwxz);
				// 转换单位性质代码2
				sql = "select dwxz from dmk_dwxz2 where dwxzdm=?";
				String dwxzdm2 = map.get("dwxzdm2");
				String dwxz2 = "";
				String[] dwxz2info = dao.getOneRs(sql,
						new String[] { dwxzdm2 }, new String[] { "dwxz" });
				if (null != dwxz2info) {
					dwxz2 = dwxz2info[0];
				}
				map.put("dwxzdm2", dwxz2);
				// 转换专业对口代码
				if ("1".equals(map.get("zydk"))) {
					map.put("zydk", "是");
				} else if ("2".equals(map.get("zydk"))) {
					map.put("zydk", "否");
				}
				// 保管档案标志
				if ("1".equals(map.get("bgdabz"))) {
					map.put("bgdabz", "保存档案");
				} else if ("0".equals(map.get("bgdabz"))) {
					map.put("bgdabz", "不保存档案");
				}
				// 转换违约金和第一年月平均工资
				if (map.get("wyj") != "" && null != (map.get("wyj"))) {
					String wyj = map.get("wyj") + "元";
					map.put("wyj", wyj);
				}
				if (map.get("dynypjgz") != "" && null != (map.get("dynypjgz"))) {
					String dynypjgz = map.get("dynypjgz") + "元";
					map.put("dynypjgz", dynypjgz);
				}
			}
		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			return mapping.findForward("false");
		} else if ("shenhe".equalsIgnoreCase(doType)) {
			// 查询数据
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from jygl_jyxy where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				String fdyshsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				if (null != map.get("fdyshsj")) {
					String sj = map.get("fdyshsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					fdyshsj = sjyear + "年" + sjmon + "月" + sjday + "日" + sjhour
							+ "时";
					map.put("fdyshsj", fdyshsj);
				}
				// 发布时间
				String sj = map.get("tjsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				String tjsj = sjyear + "年" + sjmon + "月" + sjday + "日" + sjhour
						+ "时";
				map.put("tjsj", tjsj);
				// //转换培养性别代码
				if ("1".equals(map.get("xbdm"))) {
					map.put("xbdm", "男");
				} else {
					map.put("xbdm", "女");
				}
				// 学制代码转换
				String xz = (map.get("xzdm")) + "年";
				map.put("xzdm", xz);
				// 转换学历代码
				sql = "select xl from dmk_xl where xldm=?";
				String xldm = map.get("xldm");
				String xl = "";
				String[] xlinfo = dao.getOneRs(sql, new String[] { xldm },
						new String[] { "xl" });
				if (null != xlinfo) {
					xl = xlinfo[0];
				}
				map.put("xldm", xl);
				// 转换培养方式代码(本专生，研究生)
				String xslb = map.get("xslb");
				if ("专科生".equals(xslb) || "本科生".equals(xslb)) {
					sql = "select pyfs from dmk_bzpyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfs = "";
					String[] pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, new String[] { "pyfs" });
					if (null != pyfsinfo) {
						pyfs = pyfsinfo[0];
					}
					map.put("pyfsdm", pyfs);
				} else if ("研究生".equals(xslb)) {
					sql = "select pyfs from dmk_yjspyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfs = "";
					String[] pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, new String[] { "pyfs" });
					if (null != pyfsinfo) {
						pyfs = pyfsinfo[0];
					}
					map.put("pyfsdm", pyfs);
				}
				// 转换毕业去向代码
				sql = "select byqx from dmk_byqx where byqxdm=?";
				String byqxdm = map.get("byqxdm");
				String byqx = "";
				String[] byqxinfo = dao.getOneRs(sql, new String[] { byqxdm },
						new String[] { "byqx" });
				if (null != byqxinfo) {
					byqx = byqxinfo[0];
				}
				map.put("byqxdm", byqx);
				// 转换政治面貌代码
				sql = "select zzmm from dmk_zzmm where zzmmdm=?";
				String zzmmdm = map.get("zzmmdm");
				String zzmm = "";
				String[] zzmminfo = dao.getOneRs(sql, new String[] { zzmmdm },
						new String[] { "zzmm" });
				if (null != zzmminfo) {
					zzmm = zzmminfo[0];
				}
				map.put("zzmmdm", zzmm);
				// 转换未就业标志
				if ("0".equals(map.get("wjybz"))) {
					map.put("wjybz", "就业");
				} else if ("1".equals(map.get("wjybz"))) {
					map.put("wjybz", "未就业");
				}
				// 转换居住证或蓝表标志位
				sql = "select JZZHLBBZW from dmk_JZZHLBBZW where jzzhlbbzwdm=?";
				String jzzhlbbzwdm = map.get("jzzhlbbzwdm");
				String jzzhlbbzw = "";
				String[] jzzhlbbzwinfo = dao.getOneRs(sql,
						new String[] { jzzhlbbzwdm },
						new String[] { "jzzhlbbzw" });
				if (null != jzzhlbbzwinfo) {
					jzzhlbbzw = jzzhlbbzwinfo[0];
				}
				map.put("jzzhlbbzwdm", jzzhlbbzw);
				// 转换单位性质代码
				sql = "select dwxz from dmk_dwxz where dwxzdm=?";
				String dwxzdm = map.get("dwxzdm");
				String dwxz = "";
				String[] dwxzinfo = dao.getOneRs(sql, new String[] { dwxzdm },
						new String[] { "dwxz" });
				if (null != dwxzinfo) {
					dwxz = dwxzinfo[0];
				}
				map.put("dwxzdm", dwxz);
				// 转换单位性质代码2
				sql = "select dwxz from dmk_dwxz2 where dwxzdm=?";
				String dwxzdm2 = map.get("dwxzdm2");
				String dwxz2 = "";
				String[] dwxz2info = dao.getOneRs(sql,
						new String[] { dwxzdm2 }, new String[] { "dwxz" });
				if (null != dwxz2info) {
					dwxz2 = dwxz2info[0];
				}
				map.put("dwxzdm2", dwxz2);
				// 转换专业对口代码
				if ("1".equals(map.get("zydk"))) {
					map.put("zydk", "是");
				} else if ("2".equals(map.get("zydk"))) {
					map.put("zydk", "否");
				}
				// 保管档案标志
				if ("1".equals(map.get("bgdabz"))) {
					map.put("bgdabz", "保存档案");
				} else if ("0".equals(map.get("bgdabz"))) {
					map.put("bgdabz", "不保存档案");
				}
				// 转换违约金和第一年月平均工资
				if (map.get("wyj") != "" && null != (map.get("wyj"))) {
					String wyj = map.get("wyj") + "元";
					map.put("wyj", wyj);
				}
				if (map.get("dynypjgz") != "" && null != (map.get("dynypjgz"))) {
					String dynypjgz = map.get("dynypjgz") + "元";
					map.put("dynypjgz", dynypjgz);
				}
			}
		}
		request.setAttribute("rs", map);// 发送数据集

		String xxdm = StandardOperation.getXxdm();
		if (Globals.XXDM_ZGDZDX.equals(xxdm)) {
			return mapping.findForward("successdz");
		} else {
			return mapping.findForward("success");
		}
	}

	// 弹出学校审核窗口并审核内容
	private ActionForward jyglJyxyXxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pk = "xsxh";
		String sql = ""; // sql语句
		DAO dao = DAO.getInstance();
		String realTable = "jygl_jyxy";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();
		String xxsh = DealString.toGBK(request.getParameter("xxsh"));
		String btgyy = DealString.toGBK(request.getParameter("btgyy"));
		String xsxh = request.getParameter("xsxh");
		String act = request.getParameter("act");
		String fdysh = DealString.toGBK(request.getParameter("fdysh"));
		HttpSession session = request.getSession();
		String xxshren = session.getAttribute("userName").toString();

		if ("shenhe".equals(act)) {
			String xxshsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 学校审核时间
							// //
							// 取至数据库临时表
							new String[] {}, new String[] { "sdate" }))[0];
			if ("已通过√".equals(xxsh)) {
				btgyy = "";
			}
			if ("未审核".equals(xxsh)) {
				xxshren = "";
				xxshsj = "";
			}
			if ("未审核".equals(xxsh) && "已通过√".equals(fdysh)) {
				btgyy = "";
			}
			sql = "update " + realTable + " set xxsh='" + xxsh + "' , btgyy='"
					+ btgyy + "' ,xxshsj='" + xxshsj + "' ,xxshren='" + xxshren
					+ "' where xsxh='" + xsxh + "'";

			boolean judge = false;
			judge = dao.runUpdate(sql, new String[] {});
			if (judge) {
				request.setAttribute("shenhe", "ok");
			} else {
				request.setAttribute("shenhe", "no");
			}

			sql = "select * from jygl_jyxy where xsxh=?";
			String[] colList = dao
					.getColumnName("select * from jygl_jyxy where 1=2"); // 返回列名数组
			String[] stuinfo = dao
					.getOneRs(sql, new String[] { xsxh }, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				// 学校审核时间
				xxshsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				if (null != map.get("xxshsj")) {
					String sj = map.get("xxshsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					xxshsj = sjyear + "年" + sjmon + "月" + sjday + "日" + sjhour
							+ "时";
					map.put("xxshsj", xxshsj);
				}
				// 辅导员审核时间
				String fdyshsj = null;
				sjyear = null;
				sjmon = null;
				sjday = null;
				if (null != map.get("fdyshsj")) {
					String sj1 = map.get("fdyshsj").toString();
					sjyear = sj1.substring(0, 4);
					sjmon = sj1.substring(4, 6);
					sjday = sj1.substring(6, 8);
					sjhour = sj1.substring(8, 10);
					fdyshsj = sjyear + "年" + sjmon + "月" + sjday + "日" + sjhour
							+ "时";
					map.put("fdyshsj", fdyshsj);
				}
				// 发布时间
				String sj;
				String tjsj = null;
				if (null != map.get("fdyshsj")) {
					sj = map.get("tjsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					tjsj = sjyear + "年" + sjmon + "月" + sjday + "日" + sjhour
							+ "时";
				}
				map.put("tjsj", tjsj);
				// 转换培养性别代码
				if ("1".equals(map.get("xbdm"))) {
					map.put("xbdm", "男");
				} else {
					map.put("xbdm", "女");
				}
				// 学制代码转换
				String xz = (map.get("xzdm")) + "年";
				map.put("xzdm", xz);
				// 转换学历代码
				sql = "select xl from dmk_xl where xldm=?";
				String xldm = map.get("xldm");
				String xl = "";
				String[] xlinfo = dao.getOneRs(sql, new String[] { xldm },
						new String[] { "xl" });
				if (null != xlinfo) {
					xl = xlinfo[0];
				}
				map.put("xldm", xl);
				// 转换培养方式代码(本专生，研究生)
				String xslb = map.get("xslb");
				if ("专科生".equals(xslb) || "本科生".equals(xslb)) {
					sql = "select pyfs from dmk_bzpyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfs = "";
					String[] pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, new String[] { "pyfs" });
					if (null != pyfsinfo) {
						pyfs = pyfsinfo[0];
					}
					map.put("pyfsdm", pyfs);
				} else if ("研究生".equals(xslb)) {
					sql = "select pyfs from dmk_yjspyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfs = "";
					String[] pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, new String[] { "pyfs" });
					if (null != pyfsinfo) {
						pyfs = pyfsinfo[0];
					}
					map.put("pyfsdm", pyfs);
				}
				// 转换毕业去向代码
				sql = "select byqx from dmk_byqx where byqxdm=?";
				String byqxdm = map.get("byqxdm");
				String byqx = "";
				String[] byqxinfo = dao.getOneRs(sql, new String[] { byqxdm },
						new String[] { "byqx" });
				if (null != byqxinfo) {
					byqx = byqxinfo[0];
				}
				map.put("byqxdm", byqx);
				// 转换政治面貌代码
				sql = "select zzmm from dmk_zzmm where zzmmdm=?";
				String zzmmdm = map.get("zzmmdm");
				String zzmm = "";
				String[] zzmminfo = dao.getOneRs(sql, new String[] { zzmmdm },
						new String[] { "zzmm" });
				if (null != zzmminfo) {
					zzmm = zzmminfo[0];
				}
				map.put("zzmmdm", zzmm);
				// 转换未就业标志
				if ("0".equals(map.get("wjybz"))) {
					map.put("wjybz", "就业");
				} else if ("1".equals(map.get("wjybz"))) {
					map.put("wjybz", "未就业");
				}
				// 转换居住证或蓝表标志位
				sql = "select JZZHLBBZW from dmk_JZZHLBBZW where jzzhlbbzwdm=?";
				String jzzhlbbzwdm = map.get("jzzhlbbzwdm");
				String jzzhlbbzw = "";
				String[] jzzhlbbzwinfo = dao.getOneRs(sql,
						new String[] { jzzhlbbzwdm },
						new String[] { "jzzhlbbzw" });
				if (null != jzzhlbbzwinfo) {
					jzzhlbbzw = jzzhlbbzwinfo[0];
				}
				map.put("jzzhlbbzwdm", jzzhlbbzw);
				// 转换单位性质代码
				sql = "select dwxz from dmk_dwxz where dwxzdm=?";
				String dwxzdm = map.get("dwxzdm");
				String dwxz = "";
				String[] dwxzinfo = dao.getOneRs(sql, new String[] { dwxzdm },
						new String[] { "dwxz" });
				if (null != dwxzinfo) {
					dwxz = dwxzinfo[0];
				}
				map.put("dwxzdm", dwxz);
				// 转换单位性质代码2
				sql = "select dwxz from dmk_dwxz2 where dwxzdm=?";
				String dwxzdm2 = map.get("dwxzdm2");
				String dwxz2 = "";
				String[] dwxz2info = dao.getOneRs(sql,
						new String[] { dwxzdm2 }, new String[] { "dwxz" });
				if (null != dwxz2info) {
					dwxz2 = dwxz2info[0];
				}
				map.put("dwxzdm2", dwxz2);
				// 转换专业对口代码
				if ("1".equals(map.get("zydk"))) {
					map.put("zydk", "是");
				} else if ("2".equals(map.get("zydk"))) {
					map.put("zydk", "否");
				}
				// 保管档案标志
				if ("1".equals(map.get("bgdabz"))) {
					map.put("bgdabz", "保存档案");
				} else if ("0".equals(map.get("bgdabz"))) {
					map.put("bgdabz", "不保存档案");
				}
				// 转换违约金和第一年月平均工资
				if (map.get("wyj") != "" && null != (map.get("wyj"))) {
					String wyj = map.get("wyj") + "元";
					map.put("wyj", wyj);
				}
				if (map.get("dynypjgz") != "" && null != (map.get("dynypjgz"))) {
					String dynypjgz = map.get("dynypjgz") + "元";
					map.put("dynypjgz", dynypjgz);
				}
			}
		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			return mapping.findForward("false");
		} else if ("shenhe".equalsIgnoreCase(doType)) {
			// 查询数据
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from jygl_jyxy where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}

				String xxshsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				if (null != map.get("xxshsj")) {
					String sj = map.get("xxshsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					xxshsj = sjyear + "年" + sjmon + "月" + sjday + "日" + sjhour
							+ "时";
					map.put("xxshsj", xxshsj);
				}
				// 辅导员审核时间
				String fdyshsj = null;
				sjyear = null;
				sjmon = null;
				sjday = null;
				if (null != map.get("fdyshsj")) {
					String sj1 = map.get("fdyshsj").toString();
					sjyear = sj1.substring(0, 4);
					sjmon = sj1.substring(4, 6);
					sjday = sj1.substring(6, 8);
					sjhour = sj1.substring(8, 10);
					fdyshsj = sjyear + "年" + sjmon + "月" + sjday + "日" + sjhour
							+ "时";
					map.put("fdyshsj", fdyshsj);
				}
				// 发布时间
				String sj;
				String tjsj = null;
				if (null != map.get("tjsj")) {
					sj = map.get("tjsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					tjsj = sjyear + "年" + sjmon + "月" + sjday + "日" + sjhour
							+ "时";
				}
				map.put("tjsj", tjsj);
				// //转换培养性别代码
				if ("1".equals(map.get("xbdm"))) {
					map.put("xbdm", "男");
				} else {
					map.put("xbdm", "女");
				}
				// 学制代码转换
				String xz = (map.get("xzdm")) + "年";
				map.put("xzdm", xz);
				// 转换学历代码
				sql = "select xl from dmk_xl where xldm=?";
				String xldm = map.get("xldm");
				String xl = "";
				String[] xlinfo = dao.getOneRs(sql, new String[] { xldm },
						new String[] { "xl" });
				if (null != xlinfo) {
					xl = xlinfo[0];
				}
				map.put("xldm", xl);
				// 转换培养方式代码(本专生，研究生)
				String xslb = map.get("xslb");
				if ("专科生".equals(xslb) || "本科生".equals(xslb)) {
					sql = "select pyfs from dmk_bzpyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfs = "";
					String[] pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, new String[] { "pyfs" });
					if (null != pyfsinfo) {
						pyfs = pyfsinfo[0];
					}
					map.put("pyfsdm", pyfs);
				} else if ("研究生".equals(xslb)) {
					sql = "select pyfs from dmk_yjspyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfs = "";
					String[] pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, new String[] { "pyfs" });
					if (null != pyfsinfo) {
						pyfs = pyfsinfo[0];
					}
					map.put("pyfsdm", pyfs);
				}
				// 转换毕业去向代码
				sql = "select byqx from dmk_byqx where byqxdm=?";
				String byqxdm = map.get("byqxdm");
				String byqx = "";
				String[] byqxinfo = dao.getOneRs(sql, new String[] { byqxdm },
						new String[] { "byqx" });
				if (null != byqxinfo) {
					byqx = byqxinfo[0];
				}
				map.put("byqxdm", byqx);
				// 转换政治面貌代码
				sql = "select zzmm from dmk_zzmm where zzmmdm=?";
				String zzmmdm = map.get("zzmmdm");
				String zzmm = "";
				String[] zzmminfo = dao.getOneRs(sql, new String[] { zzmmdm },
						new String[] { "zzmm" });
				if (null != zzmminfo) {
					zzmm = zzmminfo[0];
				}
				map.put("zzmmdm", zzmm);
				// 转换未就业标志
				if ("0".equals(map.get("wjybz"))) {
					map.put("wjybz", "就业");
				} else if ("1".equals(map.get("wjybz"))) {
					map.put("wjybz", "未就业");
				}
				// 转换居住证或蓝表标志位
				sql = "select JZZHLBBZW from dmk_JZZHLBBZW where jzzhlbbzwdm=?";
				String jzzhlbbzwdm = map.get("jzzhlbbzwdm");
				String jzzhlbbzw = "";
				String[] jzzhlbbzwinfo = dao.getOneRs(sql,
						new String[] { jzzhlbbzwdm },
						new String[] { "jzzhlbbzw" });
				if (null != jzzhlbbzwinfo) {
					jzzhlbbzw = jzzhlbbzwinfo[0];
				}
				map.put("jzzhlbbzwdm", jzzhlbbzw);
				// 转换单位性质代码
				sql = "select dwxz from dmk_dwxz where dwxzdm=?";
				String dwxzdm = map.get("dwxzdm");
				String dwxz = "";
				String[] dwxzinfo = dao.getOneRs(sql, new String[] { dwxzdm },
						new String[] { "dwxz" });
				if (null != dwxzinfo) {
					dwxz = dwxzinfo[0];
				}
				map.put("dwxzdm", dwxz);
				// 转换单位性质代码2
				sql = "select dwxz from dmk_dwxz2 where dwxzdm=?";
				String dwxzdm2 = map.get("dwxzdm2");
				String dwxz2 = "";
				String[] dwxz2info = dao.getOneRs(sql,
						new String[] { dwxzdm2 }, new String[] { "dwxz" });
				if (null != dwxz2info) {
					dwxz2 = dwxz2info[0];
				}
				map.put("dwxzdm2", dwxz2);
				// 转换专业对口代码
				if ("1".equals(map.get("zydk"))) {
					map.put("zydk", "是");
				} else if ("2".equals(map.get("zydk"))) {
					map.put("zydk", "否");
				}
				// 保管档案标志
				if ("1".equals(map.get("bgdabz"))) {
					map.put("bgdabz", "保存档案");
				} else if ("0".equals(map.get("bgdabz"))) {
					map.put("bgdabz", "不保存档案");
				}
				// 转换违约金和第一年月平均工资
				if (map.get("wyj") != "" && null != (map.get("wyj"))) {
					String wyj = map.get("wyj") + "元";
					map.put("wyj", wyj);
				}
				if (map.get("dynypjgz") != "" && null != (map.get("dynypjgz"))) {
					String dynypjgz = map.get("dynypjgz") + "元";
					map.put("dynypjgz", dynypjgz);
				}
			}
		}
		request.setAttribute("rs", map);// 发送数据集
		String xxdm = Base.xxdm;
		System.out.println(map.get("wjdz"));
		if (xxdm.equals(Globals.XXDM_NGSXY)
				&& !"".equalsIgnoreCase(map.get("wjdz"))
				&& map.get("wjdz") != null) {
			request.setAttribute("youFj", "youFj");
		}

		if (Globals.XXDM_ZGDZDX.equals(StandardOperation.getXxdm())) {
			if ("0".equals(map.get("jyzk"))) {
				map.put("jyzk", "就业");
			} else if ("1".equals(map.get("jyzk"))) {
				map.put("jyzk", "未就业");
			}
			// 转换省代码
			String sydqdm = map.get("sydqdm");
			sql = "select * from dmk_sydq where sydqdm=?";
			String[] rs1 = dao.getOneRs(sql, new String[] { sydqdm },
					new String[] { "sydq" });
			if (null != rs1) {
				map.put("sydqdm", rs1[0]);
			}
			// 转换市代码
			String jgshi = map.get("jgshi");
			sql = "select qxmc from dmk_qx where qxdm=?";
			String[] rs2 = dao.getOneRs(sql, new String[] { jgshi },
					new String[] { "qxmc" });
			if (null != rs2) {
				map.put("jgshi", rs2[0]);
			}
			//
			// 转换省代码
			String sydqdm1 = map.get("dwszd1");
			sql = "select * from dmk_sydq where sydqdm=?";
			String[] rs3 = dao.getOneRs(sql, new String[] { sydqdm1 },
					new String[] { "sydq" });
			if (null != rs3) {
				map.put("dwszd1", rs3[0]);
			}
			// 转换市代码
			String jgshi2 = map.get("jgshi2");
			sql = "select qxmc from dmk_qx where qxdm=?";
			String[] rs4 = dao.getOneRs(sql, new String[] { jgshi2 },
					new String[] { "qxmc" });
			if (null != rs4) {
				map.put("jgshi2", rs4[0]);
			}
			//
			// 转换省代码
			String dwszd3 = map.get("dwszd3");
			sql = "select * from dmk_sydq where sydqdm=?";
			String[] rs5 = dao.getOneRs(sql, new String[] { dwszd3 },
					new String[] { "sydq" });
			if (null != rs5) {
				map.put("dwszd3", rs5[0]);
			}
			// 转换市代码
			String jgshi3 = map.get("jgshi3");
			sql = "select qxmc from dmk_qx where qxdm=?";
			String[] rs6 = dao.getOneRs(sql, new String[] { jgshi3 },
					new String[] { "qxmc" });
			if (null != rs6) {
				map.put("jgshi3", rs6[0]);
			}

			return mapping.findForward("successdz");
		} else {
			return mapping.findForward("success");
		}
	}

	// 就业协议更新
	private ActionForward jyglJyxyUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = "xsxh";
		String sql = ""; // sql语句
		DAO dao = DAO.getInstance();
		String realTable = "jygl_jyxy";
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();
		String xsxh = request.getParameter("xsxh");
		String act = request.getParameter("act");
		String whichArea = request.getParameter("whichArea");
		map.put("whichArea", whichArea);
		String whichxx = StandardOperation.getXxdm();

		if (whichxx.equalsIgnoreCase(Globals.XXDM_YNYS)) {// 云南艺术

			sql = "select dwxzdm,dwxz from dmk_dwxz2"; // 单位性质代码2
			List dwxzdm2List = dao.getList(sql, new String[] {}, new String[] {
					"dwxzdm", "dwxz" });
			request.setAttribute("dwxzdm2List", dwxzdm2List);

			String xh = DealString.toGBK(request.getParameter("xh"));

			if ("".equals(xh) || null == xh) {
				xh = pkValue;
			}

			if ("update".equalsIgnoreCase(doType)) {

				// 学生基本信息
				String jtdz = DealString.toGBK(request.getParameter("jtdz")); // 家庭地址
				String lxdh = DealString.toGBK(request.getParameter("lxdh")); // 联系电话
				// 单位基本信息
				String dwmc = DealString.toGBK(request.getParameter("dwmc")); // 单位名称
				String dwls = DealString.toGBK(request.getParameter("dwls")); // 单位隶属
				String dwlxr = DealString.toGBK(request.getParameter("dwlxr")); // 单位联系人
				String dwlxdh = DealString
						.toGBK(request.getParameter("dwlxdh")); // 单位联系电话
				String dwyb = DealString.toGBK(request.getParameter("dwyb")); // 单位邮编
				String dwdz = DealString.toGBK(request.getParameter("dwdz")); // 单位地址
				String dwxz = DealString.toGBK(request.getParameter("dwxz")); // 单位性质
				String hyfl = DealString.toGBK(request.getParameter("hyfl")); // 行业分类
				String dayjdz = DealString
						.toGBK(request.getParameter("dayjdz")); // 档案邮寄地址
				String fbsj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // 发布时间

				boolean judge = false;
				judge = StandardOperation.update("ynys_jygl_jyxy",
						new String[] { "jtdz", "lxdh", "dwmc", "dwls", "dwlxr",
								"dwlxdh", "dwyb", "dwdz", "dwxz", "hyfl",
								"dayjdz", "fbsj" }, new String[] { jtdz, lxdh,
								dwmc, dwls, dwlxr, dwlxdh, dwyb, dwdz, dwxz,
								hyfl, dayjdz, fbsj }, "xh", xh, request);

				if (judge) {
					request.setAttribute("update", "ok");
				} else {
					request.setAttribute("update", "no");
				}
			}

			sql = "select * from ynys_jygl_jyxy where xh=?"; // 查询该学号的相关内容的sql语句
			String[] colList = dao
					.getColumnName("select * from ynys_jygl_jyxy where 1=2"); // 返回列名数组
			String[] rs = dao.getOneRs(sql, new String[] { xh }, colList); // 获得从毕业生基本信息表（视图）中查询的记录集
			if (rs != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), rs[i]); // 将记录循环放入map中
				}
			}

			request.setAttribute("rs", map);// 发送数据集
			return mapping.findForward("success2");
		}

		if ("shenhe".equals(whichArea)) {
			request.setAttribute("whichArea", "shenhe");
		} else if ("result".equals(whichArea)) {
			request.setAttribute("whichArea", "result");
		}

		sql = "select zzmmdm,zzmm from dmk_zzmm"; // 政治面貌代码
		List zzmmdmList = dao.getList(sql, new String[] {}, new String[] {
				"zzmmdm", "zzmm" });
		request.setAttribute("zzmmdmList", zzmmdmList);

		sql = "select byqxdm,byqx from dmk_byqx"; // 毕业去向代码
		List byqxdmList = dao.getList(sql, new String[] {}, new String[] {
				"byqxdm", "byqx" });
		request.setAttribute("byqxdmList", byqxdmList);

		sql = "select dwdqdm,dwdq from dmk_dwdq"; // 单位地区
		List dwdqList = dao.getList(sql, new String[] {}, new String[] {
				"dwdqdm", "dwdq" });
		request.setAttribute("dwdqList", dwdqList);

		sql = "select zgbmdm,zgbm from dmk_zgbm"; // 主管部门
		List zgbmList = dao.getList(sql, new String[] {}, new String[] {
				"zgbmdm", "zgbm" });
		request.setAttribute("zgbmList", zgbmList);

		sql = "select jzzhlbbzwdm,jzzhlbbzw from dmk_jzzhlbbzw"; // 居住证或蓝表标志位
		List jzzhlbbzwdmList = dao.getList(sql, new String[] {}, new String[] {
				"jzzhlbbzwdm", "jzzhlbbzw" });
		request.setAttribute("jzzhlbbzwdmList", jzzhlbbzwdmList);

		sql = "select sydzgbmdm,sydzgbm from dmk_sydzgbm"; // 生源地主管单位名称
		List sydzgbmList = dao.getList(sql, new String[] {}, new String[] {
				"sydzgbmdm", "sydzgbm" });
		request.setAttribute("sydzgbmList", sydzgbmList);

		sql = "select dwxzdm,dwxz from dmk_dwxz"; // 单位性质代码
		List dwxzdmList = dao.getList(sql, new String[] {}, new String[] {
				"dwxzdm", "dwxz" });
		request.setAttribute("dwxzdmList", dwxzdmList);

		sql = "select dwxzdm,dwxz from dmk_dwxz2"; // 单位性质代码2
		List dwxzdm2List = dao.getList(sql, new String[] {}, new String[] {
				"dwxzdm", "dwxz" });
		request.setAttribute("dwxzdm2List", dwxzdm2List);

		sql = "select dqlxdm,dqlx from dmk_dqlx"; // 地区流向
		List dqlxdmList = dao.getList(sql, new String[] {}, new String[] {
				"dqlxdm", "dqlx" });
		request.setAttribute("dqlxdmList", dqlxdmList);

		sql = "select hyfldm,hyfl from dmk_hyfl"; // 行业分类
		List hyflList = dao.getList(sql, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);

		if ("update".equals(act)) {
			xsxh = DealString.toGBK(request.getParameter("xsxh")); // 学号
			String memo = DealString.toGBK(request.getParameter("memo")); // 13备注
			String zzmmdm = request.getParameter("zzmmdm"); // 14政治面貌代码
			String xxdjh = request.getParameter("xxdjh"); // 16信息登记号
			String zzjgdm = request.getParameter("zzjgdm"); // 17组织机构代码
			String dwmc = DealString.toGBK(request.getParameter("dwmc")); // 18单位名称
			String dwdqdm = request.getParameter("dwdqdm"); // 19单位地区代码
			String dwdq = DealString.toGBK(request.getParameter("dwdq")); // 20单位地区名称
			String zgbm = DealString.toGBK(request.getParameter("zgbm")); // 21主管部门名称
			String byqxdm = request.getParameter("byqxdm"); // 22毕业去向代码
			String blueno = request.getParameter("blueno"); // 23蓝表批准文号
			String wjybz = DealString.toGBK(request.getParameter("wjybz")); // 24未就业标志
			String wjyyy = DealString.toGBK(request.getParameter("wjyyy")); // 25未就业原因
			String lxdz = DealString.toGBK(request.getParameter("lxdz")); // 26联系地址
			String yb = request.getParameter("yb"); // 27邮编
			String dh = request.getParameter("dh"); // 28电话
			String bgdabz = request.getParameter("bgdabz"); // 29保管档案标志
			String bz1 = DealString.toGBK(request.getParameter("bz1")); // 30自定义1
			String bz2 = DealString.toGBK(request.getParameter("bz2")); // 31自定义2
			String bz3 = DealString.toGBK(request.getParameter("bz3")); // 32自定义3
			String bz4 = DealString.toGBK(request.getParameter("bz4")); // 33自定义4
			String bz5 = DealString.toGBK(request.getParameter("bz5")); // 34自定义5
			String jzzhlbbzwdm = request.getParameter("jzzhlbbzwdm"); // 35
			// 居住证或蓝表标志位
			String sydzgbm = DealString.toGBK(request.getParameter("sydzgbm")); // 36生源地主管单位
			String dwxzdm = request.getParameter("dwxzdm"); // 37单位性质代码
			String zgbmdm = request.getParameter("zgbmdm"); // 38主管部门代码
			String dwxzdm2 = request.getParameter("dwxzdm2"); // 39单位性质代码2
			String dwdz = DealString.toGBK(request.getParameter("dwdz")); // 40单位地址
			String dwdh = request.getParameter("dwdh"); // 41单位电话
			String dwyb = request.getParameter("dwyb"); // 42单位邮编
			String dajsd = DealString.toGBK(request.getParameter("dajsd")); // 43档案接受地
			String dajsddz = DealString.toGBK(request.getParameter("dajsddz")); // 44档案接受地地址
			String dajsdyb = request.getParameter("dajsdyb"); // 45档案接受地邮编
			String dynypjgz = request.getParameter("dynypjgz"); // 46第一年月平均工资
			String wyj = request.getParameter("wyj"); // 47违约金
			String dqlx = DealString.toGBK(request.getParameter("dqlx")); // 48地区流向
			String hyfl = DealString.toGBK(request.getParameter("hyfl")); // 49行业分类
			String zydk = request.getParameter("zydk"); // 50专业对口

			String fdysh = DealString.toGBK(request.getParameter("fdysh"));// 辅导员审核
			String xxsh = DealString.toGBK(request.getParameter("xxsh"));// 学校审核
			String xysbh = DealString.toGBK(request.getParameter("xysbh"));
			String dwlxr = request.getParameter("dwlxr");
			String dajsddwmc = request.getParameter("dwlxr");
			String dwgm = request.getParameter("dwlxr");
			String dwzczj = request.getParameter("dwlxr");

			// 已通过√ 未通过X
			if ("未通过X".equals(fdysh)) {
				fdysh = "未审核";
			}
			if ("未通过X".equals(xxsh)) {
				xxsh = "未审核";
			}

			boolean judge = false;
			if (Globals.XXDM_ZGDZDX.equals(whichxx)) {
				String jgshi = DealString.toGBK(request.getParameter("jgshi"));
				String zzmm = DealString.toGBK(request.getParameter("zzmm"));
				String mz = DealString.toGBK(request.getParameter("mz"));
				String zymc = DealString.toGBK(request.getParameter("zymc"));
				String qq = DealString.toGBK(request.getParameter("qq"));
				// String xb = DealString.toGBK(request.getParameter("xb"));
				String wpdw = DealString.toGBK(request.getParameter("wpdw"));
				String zsmc = DealString.toGBK(request.getParameter("zsmc"));
				String zdcl = DealString.toGBK(request.getParameter("zdcl"));
				String bdzbz = DealString.toGBK(request.getParameter("bdzbz"));
				String sjdw = DealString.toGBK(request.getParameter("sjdw"));
				String pqdw = DealString.toGBK(request.getParameter("pqdw"));
				String pqdwid = DealString
						.toGBK(request.getParameter("pqdwid"));
				String dwxz1 = DealString.toGBK(request.getParameter("dwxz1"));
				String dwxz2 = DealString.toGBK(request.getParameter("dwxz2"));
				String zgbm1 = DealString.toGBK(request.getParameter("zgbm1"));
				String zgbm2 = DealString.toGBK(request.getParameter("zgbm2"));
				String zgbmmc = DealString
						.toGBK(request.getParameter("zgbmmc"));
				String dwszd2 = DealString
						.toGBK(request.getParameter("dwszd2"));
				String dwszd3 = DealString
						.toGBK(request.getParameter("dwszd3"));
				String dwszd1 = DealString
						.toGBK(request.getParameter("dwszd1"));
				String sjszd1 = DealString
						.toGBK(request.getParameter("sjszd1"));
				String sjszd2 = DealString
						.toGBK(request.getParameter("sjszd2"));
				String sjszd3 = DealString
						.toGBK(request.getParameter("sjszd3"));
				String hkdz = DealString.toGBK(request.getParameter("hkdz"));
				String gprq = DealString.toGBK(request.getParameter("gprq"));
				String gpyy = DealString.toGBK(request.getParameter("gpyy"));
				String gpcs = DealString.toGBK(request.getParameter("gpcs"));
				String ydwmc = DealString.toGBK(request.getParameter("ydwmc"));
				String bdzbh = DealString.toGBK(request.getParameter("bdzbh"));
				String jyzk = DealString.toGBK(request.getParameter("jyzk"));
				String jgshi2 = DealString
						.toGBK(request.getParameter("jgshi2"));
				String jgshi3 = DealString
						.toGBK(request.getParameter("jgshi3"));
				judge = StandardOperation.update(realTable, new String[] {
						"dwxzdm2", "dwdq", "byqxdm", "dajsd", "dajsdyb",
						"jgshi", "zzmm", "mz", "jgshi2", "jgshi3", "zymc",
						"qq", "xysbh", "xsxh", "wpdw", "zsmc", "zdcl", "bdzbz",
						"sjdw", "pqdw", "pqdwid", "dwxz1", "dwxz2", "zgbm1",
						"zgbm2", "zgbmmc", "dwszd1", "dwszd2", "dwszd3",
						"sjszd1", "sjszd2", "sjszd3", "hkdz", "gprq", "gpyy",
						"gpcs", "ydwmc", "bdzbh", "wjybz", "wjyyy", "jyzk" },
						new String[] { dwxzdm2, dwdq, byqxdm, dajsd, dajsdyb,
								jgshi, zzmm, mz, jgshi2, jgshi3, zymc, qq,
								xysbh, xsxh, wpdw, zsmc, zdcl, bdzbz, sjdw,
								pqdw, pqdwid, dwxz1, dwxz2, zgbm1, zgbm2,
								zgbmmc, dwszd1, dwszd2, dwszd3, sjszd1, sjszd2,
								sjszd3, hkdz, gprq, gpyy, gpcs, ydwmc, bdzbh,
								wjybz, wjyyy, jyzk }, "xsxh", xsxh, request);
			} else {
				judge = StandardOperation.update(realTable, new String[] {
						"memo", "zzmmdm", "xxdjh", "zzjgdm", "dwmc", "dwdqdm",
						"dwdq", "zgbm", "byqxdm", "blueno", "wjybz", "wjyyy",
						"lxdz", "yb", "dh", "bgdabz", "bz1", "bz2", "bz3",
						"bz4", "bz5", "jzzhlbbzwdm", "sydzgbm", "dwxzdm",
						"zgbmdm", "dwxzdm2", "dwdz", "dwdh", "dwyb", "dajsd",
						"dajsddz", "dajsdyb", "dynypjgz", "wyj", "dqlx",
						"hyfl", "zydk", "fdysh", "xxsh", "xysbh", "dwlxr",
						"dajsddwmc", "dwgm", "dwzczj" }, new String[] { memo,
						zzmmdm, xxdjh, zzjgdm, dwmc, dwdqdm, dwdq, zgbm,
						byqxdm, blueno, wjybz, wjyyy, lxdz, yb, dh, bgdabz,
						bz1, bz2, bz3, bz4, bz5, jzzhlbbzwdm, sydzgbm, dwxzdm,
						zgbmdm, dwxzdm2, dwdz, dwdh, dwyb, dajsd, dajsddz,
						dajsdyb, dynypjgz, wyj, dqlx, hyfl, zydk, fdysh, xxsh,
						xysbh, dwlxr, dajsddwmc, dwgm, dwzczj }, "xsxh", xsxh,
						request);
			}
			if (judge) {
				request.setAttribute("update", "ok");

				// 修改操作的记录
				HttpSession session = request.getSession();
				String userName = session.getAttribute("userName").toString();
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"修改", "就业协议表", "学号:" + xsxh, whensj }, request);
			} else {
				request.setAttribute("update", "no");
			}

			sql = "select * from jygl_jyxy where xsxh=?";
			String[] colList = dao
					.getColumnName("select * from jygl_jyxy where 1=2"); // 返回列名数组
			String[] stuinfo = dao
					.getOneRs(sql, new String[] { xsxh }, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				// 辅导员审核时间
				String fdyshsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				String sjmin = null;
				String sjss = null;
				if (null != map.get("fdyshsj")) {
					String sj = map.get("fdyshsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					sjmin = sj.substring(10, 12);
					sjss = sj.substring(12, 14);
					fdyshsj = sjyear + "年" + sjmon + "月" + sjday + "日 "
							+ sjhour + ":" + sjmin + ":" + sjss;
					map.put("fdyshsj", fdyshsj);
				}
				// 学校审核时间
				String xxshsj = null;
				sjyear = null;
				sjmon = null;
				sjday = null;
				sjhour = null;
				sjmin = null;
				sjss = null;
				if (null != map.get("xxshsj")) {
					String sj1 = map.get("xxshsj").toString();
					sjyear = sj1.substring(0, 4);
					sjmon = sj1.substring(4, 6);
					sjday = sj1.substring(6, 8);
					sjhour = sj1.substring(8, 10);
					sjmin = sj1.substring(10, 12);
					sjss = sj1.substring(12, 14);
					xxshsj = sjyear + "年" + sjmon + "月" + sjday + "日 " + sjhour
							+ ":" + sjmin + ":" + sjss;
					map.put("xxshsj", xxshsj);
				}
				// 发布时间
				String sj = map.get("tjsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				String tjsj = sjyear + "年" + sjmon + "月" + sjday + "日" + sjhour
						+ "时";
				map.put("tjsj", tjsj);
				// 转换培养性别代码
				if ("1".equals(map.get("xbdm"))) {
					map.put("xbdm", "男");
				} else {
					map.put("xbdm", "女");
				}
				// 学制代码转换
				String xz = (map.get("xzdm")) + "年";
				map.put("xzdm", xz);
				// 转换学历代码
				sql = "select xl from dmk_xl where xldm=?";
				String xldm = map.get("xldm");
				String xl = "";
				String[] xlinfo = dao.getOneRs(sql, new String[] { xldm },
						new String[] { "xl" });
				if (null != xlinfo) {
					xl = xlinfo[0];
				}
				map.put("xldm", xl);
				// 转换培养方式代码(本专生，研究生)
				String xslb = map.get("xslb");
				if ("专科生".equals(xslb) || "本科生".equals(xslb)) {
					sql = "select pyfs from dmk_bzpyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfs = "";
					String[] pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, new String[] { "pyfs" });
					if (null != pyfsinfo) {
						pyfs = pyfsinfo[0];
					}
					map.put("pyfsdm", pyfs);
				} else if ("研究生".equals(xslb)) {
					sql = "select pyfs from dmk_yjspyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfs = "";
					String[] pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, new String[] { "pyfs" });
					if (null != pyfsinfo) {
						pyfs = pyfsinfo[0];
					}
					map.put("pyfsdm", pyfs);
				}
			}
			request.setAttribute("whichArea", "shenhe");
		}

		if ("first".equalsIgnoreCase(doType)) {
			// 查询数据
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from jygl_jyxy where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				// 辅导员审核时间
				String fdyshsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				String sjmin = null;
				String sjss = null;
				if (null != map.get("fdyshsj")) {
					String sj = map.get("fdyshsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					sjmin = sj.substring(10, 12);
					sjss = sj.substring(12, 14);
					fdyshsj = sjyear + "年" + sjmon + "月" + sjday + "日 "
							+ sjhour + ":" + sjmin + ":" + sjss;
					map.put("fdyshsj", fdyshsj);
				}
				// 学校审核时间
				String xxshsj = null;
				sjyear = null;
				sjmon = null;
				sjday = null;
				sjhour = null;
				sjmin = null;
				sjss = null;
				if (null != map.get("xxshsj")) {
					String sj1 = map.get("xxshsj").toString();
					sjyear = sj1.substring(0, 4);
					sjmon = sj1.substring(4, 6);
					sjday = sj1.substring(6, 8);
					sjhour = sj1.substring(8, 10);
					sjmin = sj1.substring(10, 12);
					sjss = sj1.substring(12, 14);
					xxshsj = sjyear + "年" + sjmon + "月" + sjday + "日 " + sjhour
							+ ":" + sjmin + ":" + sjss;
					map.put("xxshsj", xxshsj);
				}
				// 发布时间
				String sj;
				String tjsj = null;
				if (null != map.get("tjsj")) {
					sj = map.get("tjsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					tjsj = sjyear + "年" + sjmon + "月" + sjday + "日" + sjhour
							+ "时";
				}
				map.put("tjsj", tjsj);
				// 转换培养性别代码
				if ("1".equals(map.get("xbdm"))) {
					map.put("xbdm", "男");
				} else {
					map.put("xbdm", "女");
				}
				// 学制代码转换
				String xz = (map.get("xzdm")) + "年";
				map.put("xzdm", xz);
				// 转换学历代码
				sql = "select xl from dmk_xl where xldm=?";
				String xldm = map.get("xldm");
				String xl = "";
				String[] xlinfo = dao.getOneRs(sql, new String[] { xldm },
						new String[] { "xl" });
				if (null != xlinfo) {
					xl = xlinfo[0];
				}
				map.put("xldm", xl);
				// 转换培养方式代码(本专生，研究生)
				String xslb = map.get("xslb");
				if ("专科生".equals(xslb) || "本科生".equals(xslb)) {
					sql = "select pyfs from dmk_bzpyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfs = "";
					String[] pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, new String[] { "pyfs" });
					if (null != pyfsinfo) {
						pyfs = pyfsinfo[0];
					}
					map.put("pyfsdm", pyfs);
				} else if ("研究生".equals(xslb)) {
					sql = "select pyfs from dmk_yjspyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfs = "";
					String[] pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, new String[] { "pyfs" });
					if (null != pyfsinfo) {
						pyfs = pyfsinfo[0];
					}
					map.put("pyfsdm", pyfs);
				}

			}
		}

		if ("refresh".equals(doType2)) {
			String dwdq = DealString.toGBK(request.getParameter("dwdq"));
			String zgbm = DealString.toGBK(request.getParameter("zgbm"));

			// 单位地区代码获得装入map
			sql = "select * from dmk_dwdq where dwdq=?";
			String[] dwdqinfo = dao.getOneRs(sql, new String[] { dwdq },
					new String[] { "dwdqdm" });
			String dwdqdm = "";
			if (null != dwdqinfo) {
				dwdqdm = dwdqinfo[0];
			}

			// 主管部门代码获得装入map
			sql = "select * from dmk_zgbm where zgbm=?";
			String[] zgbminfo = dao.getOneRs(sql, new String[] { zgbm },
					new String[] { "zgbmdm" });
			String zgbmdm = "";
			if (null != zgbminfo) {
				zgbmdm = zgbminfo[0];
			}

			// 将目前页面上已有的数据传回来装入map再传回去

			String xslb = DealString.toGBK(request.getParameter("xslb"));
			String bynd = request.getParameter("bynd");
			String xymc = DealString.toGBK(request.getParameter("xymc"));

			String id = request.getParameter("id"); // 1身份证号
			String xxdm = request.getParameter("xxdm"); // 2学校代码
			String xxmc = DealString.toGBK(request.getParameter("xxmc")); // 3学校名称
			String zydm = request.getParameter("zydm"); // 4专业代码
			String zymc = DealString.toGBK(request.getParameter("zymc")); // 5专业名称
			xsxh = request.getParameter("xsxh"); // 6学号
			String name = DealString.toGBK(request.getParameter("name")); // 7姓名
			String xbdm = DealString.toGBK(request.getParameter("xbdm")); // 8性别代码
			String xldm = DealString.toGBK(request.getParameter("xldm")); // 9学历代码
			String xzdm = DealString.toGBK(request.getParameter("xzdm")); // 10学制
			String sydqdm = DealString.toGBK(request.getParameter("sydqdm")); // 11生源地区代码
			String pyfsdm = DealString.toGBK(request.getParameter("pyfsdm")); // 12培养方式代码
			String memo = DealString.toGBK(request.getParameter("memo")); // 13备注
			String zzmmdm = request.getParameter("zzmmdm"); // 14政治面貌代码
			String sydq = DealString.toGBK(request.getParameter("sydq")); // 15生源地区名称
			String xxdjh = request.getParameter("xxdjh"); // 16信息登记号
			String zzjgdm = request.getParameter("zzjgdm"); // 17组织机构代码
			String dwmc = DealString.toGBK(request.getParameter("dwmc")); // 18单位名称
			// dwdqdm = request.getParameter("dwdqdm"); // 19单位地区代码
			// dwdq = DealString.toGBK(request.getParameter("dwdq")); //
			// 20单位地区名称
			// zgbm = DealString.toGBK(request.getParameter("zgbm")); //
			// 21主管部门名称
			// zgbmdm = request.getParameter("zgbmdm"); // 38主管部门代码
			String byqxdm = request.getParameter("byqxdm"); // 22毕业去向代码
			String blueno = request.getParameter("blueno"); // 23蓝表批准文号
			String wjybz = request.getParameter("wjybz"); // 24未就业标志
			String wjyyy = DealString.toGBK(request.getParameter("wjyyy")); // 25未就业原因
			String lxdz = DealString.toGBK(request.getParameter("lxdz")); // 26联系地址
			String yb = request.getParameter("yb"); // 27邮编
			String dh = request.getParameter("dh"); // 28电话
			String bgdabz = request.getParameter("bgdabz"); // 29保管档案标志
			String bz1 = DealString.toGBK(request.getParameter("bz1")); // 30自定义1
			String bz2 = DealString.toGBK(request.getParameter("bz2")); // 31自定义2
			String bz3 = DealString.toGBK(request.getParameter("bz3")); // 32自定义3
			String bz4 = DealString.toGBK(request.getParameter("bz4")); // 33自定义4
			String bz5 = DealString.toGBK(request.getParameter("bz5")); // 34自定义5
			String jzzhlbbzwdm = request.getParameter("jzzhlbbzwdm"); // 35居住证或蓝表标志位
			String sydzgbm = DealString.toGBK(request.getParameter("sydzgbm")); // 36生源地主管单位
			String dwxzdm = request.getParameter("dwxzdm"); // 37单位性质代码
			String dwxzdm2 = request.getParameter("dwxzdm2"); // 39单位性质代码2
			String dwdz = DealString.toGBK(request.getParameter("dwdz")); // 40单位地址
			String dwdh = request.getParameter("dwdh"); // 41单位电话
			String dwyb = request.getParameter("dwyb"); // 42单位邮编
			String dajsd = DealString.toGBK(request.getParameter("dajsd")); // 43档案接受地
			String dajsddz = DealString.toGBK(request.getParameter("dajsddz")); // 44档案接受地地址
			String dajsdyb = request.getParameter("dajsdyb"); // 45档案接受地邮编
			String dynypjgz = request.getParameter("dynypjgz"); // 46第一年月平均工资
			String wyj = request.getParameter("wyj"); // 47违约金
			String dqlx = DealString.toGBK(request.getParameter("dqlx")); // 48地区流向
			String hyfl = DealString.toGBK(request.getParameter("hyfl")); // 49行业分类
			String zydk = request.getParameter("zydk"); // 50专业对口

			String fdysh = DealString.toGBK(request.getParameter("fdysh"));// 辅导员审核
			String xxsh = DealString.toGBK(request.getParameter("xxsh"));// 学校审核
			String fdyshren = request.getParameter("fdyshren");// 辅导员审核人
			String xxshren = request.getParameter("xxshren");// 学校审核人
			String fdyshsj = DealString.toGBK(request.getParameter("fdyshsj"));// 辅导员审核时间
			String xxshsj = DealString.toGBK(request.getParameter("xxshsj"));// 学校审核时间

			// 装入map
			map.put("xslb", xslb);
			map.put("bynd", bynd);
			map.put("xymc", xymc);

			map.put("id", id);
			map.put("xxdm", xxdm);
			map.put("xxmc", xxmc);
			map.put("zydm", zydm);
			map.put("zymc", zymc);
			map.put("xsxh", xsxh);
			map.put("name", name);
			map.put("xbdm", xbdm);
			map.put("xldm", xldm);
			map.put("xzdm", xzdm);
			map.put("sydqdm", sydqdm);
			map.put("pyfsdm", pyfsdm);
			map.put("memo", memo);
			map.put("zzmmdm", zzmmdm);
			map.put("sydq", sydq);
			map.put("xxdjh", xxdjh);
			map.put("zzjgdm", zzjgdm);
			map.put("dwmc", dwmc);
			map.put("dwdqdm", dwdqdm);
			map.put("dwdq", dwdq);
			map.put("zgbm", zgbm);
			map.put("zgbmdm", zgbmdm);
			map.put("byqxdm", byqxdm);
			map.put("blueno", blueno);
			map.put("wjybz", wjybz);
			map.put("wjyyy", wjyyy);
			map.put("lxdz", lxdz);
			map.put("yb", yb);
			map.put("dh", dh);
			map.put("bgdabz", bgdabz);
			map.put("bz1", bz1);
			map.put("bz2", bz2);
			map.put("bz3", bz3);
			map.put("bz4", bz4);
			map.put("bz5", bz5);
			map.put("jzzhlbbzwdm", jzzhlbbzwdm);
			map.put("sydzgbm", sydzgbm);
			map.put("dwxzdm", dwxzdm);
			map.put("dwxzdm2", dwxzdm2);
			map.put("dwdz", dwdz);
			map.put("dwdh", dwdh);
			map.put("dwyb", dwyb);
			map.put("dajsd", dajsd);
			map.put("dajsddz", dajsddz);
			map.put("dajsdyb", dajsdyb);
			map.put("dynypjgz", dynypjgz);
			map.put("wyj", wyj);
			map.put("dqlx", dqlx);
			map.put("hyfl", hyfl);
			map.put("zydk", zydk);

			map.put("fdysh", fdysh);
			map.put("xxsh", xxsh);
			map.put("fdyshren", fdyshren);
			map.put("xxshren", xxshren);
			map.put("fdyshsj", fdyshsj);
			map.put("xxshsj", xxshsj);
			request.setAttribute("whichArea", "shenhe");
		}
		request.setAttribute("rs", map);// 发送数据集
		StuInfoDAO stuDao = new StuInfoDAO();
		request.setAttribute("shiList", stuDao.getShiList("").get("shiList"));
		request.setAttribute("xianList", stuDao.getShiList("").get("xianList"));
		request.setAttribute("ssList", stuDao.getSsList());

		if (Globals.XXDM_ZGDZDX.equals(whichxx)) {
			sql = "select pyfsdm,pyfs from dmk_bzpyfs";
			List pyfsList = dao.getList(sql, new String[] {}, new String[] {
					"pyfsdm", "pyfs" });
			request.setAttribute("pyfsList", pyfsList);
			return mapping.findForward("successdz");
		} else {
			return mapping.findForward("success");
		}
	}

	// 就业协议审核结果查询
	private ActionForward jyglJyxyResultQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) throws Exception {
		CommanForm commanForm = (CommanForm) form;
		DAO dao = DAO.getInstance();
		String sql = "";
		String pk = "xsxh";
		String[] colList = null;
		String[] colListCN = null;
		ArrayList<Object> rs = new ArrayList<Object>(); // 获得记录
		String rsNum = "0";
		String tableName = "jygl_jyxy";
		List topTr = null;
		HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();

		if (userType.equals("teacher")) {
			String admin = session.getAttribute("userType").toString();
			if ("xx".equalsIgnoreCase(admin) || "admin".equalsIgnoreCase(admin)) {
				// -----------2010/6/4 edit by luojw -----------
				sql = "select " + pk + " 主键,rownum r,a.* from " + tableName
						+ " a ";
				// ----------end -----------
			} else {
				sql = "select " + pk + " 主键,rownum r,a.* from " + tableName
						+ " a " + " where fdyshren='" + userName + "'";
			}
			colList = new String[] { "主键", "r", "nd", "bynd", "name", "xsxh",
					"xbdm", "xslb", "xymc", "zymc", "fdysh", "xxsh" };
			colListCN = dao.getColumnNameCN(colList, tableName);
			topTr = dao.arrayToList(colList, colListCN);
			if ((request.getParameter("act") != null)
					&& request.getParameter("act").equalsIgnoreCase("go")) {
				rs.addAll(CommonQueryDAO.commonQuery(sql, "", new String[] {},
						colList, commanForm));
				if (rs == null) {
					rsNum = "0";
				} else {
					rsNum = String.valueOf(rs.size());
				}
			}
			request.setAttribute("ableToView", "teacher");
		}

		if (userType.equals("student")) {
			sql = "select " + pk + " 主键,rownum r,a.* from " + tableName + " a "
					+ " where xsxh=" + userName;
			colList = new String[] { "主键", "r", "nd", "bynd", "name", "xsxh",
					"xbdm", "xslb", "xymc", "zymc", "fdysh", "xxsh" };
			colListCN = dao.getColumnNameCN(colList, tableName);
			topTr = dao.arrayToList(colList, colListCN);
			if ((request.getParameter("act") != null)
					&& request.getParameter("act").equalsIgnoreCase("go")) {
				rs.addAll(CommonQueryDAO.commonQuery(sql, "", new String[] {},
						colList, commanForm));
				if (rs == null) {
					rsNum = "0";
				} else {
					rsNum = String.valueOf(rs.size());
				}
			}
			request.setAttribute("ableToView", "student");
		}

		map.put("userName", userName);
		request.setAttribute("rs1", map);
		request.setAttribute("rs", rs); // 发送数据集
		request.setAttribute("topTr", topTr); // 发送表头
		request.setAttribute("rsNum", rsNum); // 发送记录数
		return mapping.findForward("success");
	}

	// 就业协议领取登记
	private ActionForward jyglJyxyLqdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		String realTable = "jygl_jyxyslqdjb";
		String pk = "xh";
		String sql = "";
		// 判断用户类型
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String xh = DealString.toGBK(request.getParameter("xh"));
		String xm = DealString.toGBK(request.getParameter("xm"));
		String xb = DealString.toGBK(request.getParameter("xb"));
		String jyxybh = DealString.toGBK(request.getParameter("jyxybh"));
		String nj = DealString.toGBK(request.getParameter("nj"));
		String xydm = DealString.toGBK(request.getParameter("xydm"));
		String zydm = DealString.toGBK(request.getParameter("zydm"));
		String bjdm = DealString.toGBK(request.getParameter("bjdm"));
		String lqqk = DealString.toGBK(request.getParameter("lqqk"));

		if ("admin".equalsIgnoreCase(userType)
				|| "xx".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xx");
		} else if ("xy".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xy");
			xydm = userDep;
		}

		if ("delall".equalsIgnoreCase(doType)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				pkstringI = pkstring.split("!!#!!");
			}

			boolean judge = false;
			for (int i = 1; i < pkstringI.length; i++) {
				String whichxh = pkstringI[i];
				judge = StandardOperation.delete(realTable, pk, whichxh,
						request);
			}
			if (judge) {
				request.setAttribute("delall", "ok");
			} else {
				request.setAttribute("delall", "no");
			}
		}

		StringBuffer query = new StringBuffer();

		if (!("".equals(xh))) {
			query.append(" and xh like '%");
			query.append(xh);
			query.append("%'");
		}
		if (!("".equals(xm))) {
			query.append(" and xm like '%");
			query.append(xm);
			query.append("%'");
		}
		if (!("".equals(xb))) {
			query.append(" and xb like '%");
			query.append(xb);
			query.append("%'");
		}
		if (!("".equals(jyxybh))) {
			query.append(" and jyxybh like '%");
			query.append(jyxybh);
			query.append("%'");
		}
		if (!("".equals(nj))) {
			query.append(" and nj = '");
			query.append(nj);
			query.append("'");
		}
		if (!("".equals(xydm))) {
			query.append(" and xydm='");
			query.append(xydm);
			query.append("'");
		}
		if (!("".equals(zydm))) {
			query.append(" and zydm='");
			query.append(zydm);
			query.append("'");
		}
		if (!("".equals(bjdm))) {
			query.append(" and bjdm='");
			query.append(bjdm);
			query.append("'");
		}
		// ============begin 中国美术学院 2009/6/4 luojw ===========
		String xxdm = StandardOperation.getXxdm();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)) {
			if (!("".equals(lqqk))) {
				query.append(" and lqqk='");
				query.append(lqqk);
				query.append("'");
			}
		}
		// ============end 中国美术学院 2009/6/4 luojw ===========

		String query1 = query.toString();

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from view_jygl_jyxyslqdjb a where 1=1 "
				+ query1;

		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// 学生基本信息
		sql = "select a.r 行号,a.* from (select a.*,rownum r from (select distinct  a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.jyxybh from view_jygl_jyxyslqdjb a where 1=1 "
				+ query1
				+ " ) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize());

		String[] colList = new String[] { "行号", "xh", "xm", "xb", "nj", "xymc",
				"zymc", "bjmc", "jyxybh" };
		if ("go".equalsIgnoreCase(act)) {

			// ============begin 中国美术学院 2009/6/4 luojw ===========
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)) {
				sql = "select a.r 行号,a.* from (select a.*,rownum r from (select distinct  a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.jyxybh,a.lqqk from view_jygl_jyxyslqdjb a where 1=1 "
						+ query1
						+ " ) a ) a where a.r>"
						+ dataSearchForm.getPages().getStart()
						+ " and a.r<="
						+ (dataSearchForm.getPages().getStart() + dataSearchForm
								.getPages().getPageSize());

				colList = new String[] { "行号", "xh", "xm", "xb", "nj", "xymc",
						"zymc", "bjmc", "jyxybh", "lqqk" };
			}
			// ============end 中国美术学院 2009/6/4 luojw ===========

			rs = dao.getArrayList2(sql, new String[] {}, colList);
			map.put("xh", xh);
			map.put("xm", xm);
			map.put("xb", xb);
			map.put("nj", nj);
			map.put("jyxybh", jyxybh);
			map.put("xydm", xydm);
			map.put("zydm", zydm);
			map.put("bjdm", bjdm);
			map.put("lqqk", lqqk);
		}

		sql = "select count(*) from view_jygl_jyxyslqdjb where 1=1 " + query1;
		int rsNuminfo = dao.getOneRsint(sql);
		String rsNum = String.valueOf(rsNuminfo);
		String[] colListCN = dao.getColumnNameCN(colList,
				"view_jygl_jyxyslqdjb");
		List topTr = dao.arrayToList(colList, colListCN);

		request.setAttribute("querry", query1);
		request.setAttribute("rs1", map);
		request.setAttribute("realTable", "jygl_jyxyslqdjb");
		request.setAttribute("table", "view_jygl_jyxyslqdjb");
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);
		request.setAttribute("njList", dao.getNjList());// 发送学年列表
		request.setAttribute("xyList", dao.getXyList());// 发送学院列表
		request.setAttribute("zyList", dao.getZyList(xydm));// 发送专业列表
		request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));// 发送专业列表
		return mapping.findForward("success");
	}

	// 就业领取登记详细信息
	private ActionForward viewJyxyLqdjInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		String sql = "";
		String pk = "xh";
		String pkValue = request.getParameter("pkValue");
		String tableName = "view_jygl_jyxyslqdjb";
		String act = request.getParameter("act");
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		// String xxdm = StandardOperation.getXxdm();

		if ("view".equalsIgnoreCase(act)) {
			// 查询数据
			sql = "select * from " + tableName + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + tableName
					+ " where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
			}
		}

		// ============begin 中国美术学院 2009/6/4 luojw ===========
		String xxdm = StandardOperation.getXxdm();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_SJXY)) {
			if ("add".equalsIgnoreCase(act)) {
				request.setAttribute("type", "add");
				request.setAttribute("update", "no");
			}
			if ("edit".equalsIgnoreCase(act)) {
				String xh = DealString.toGBK(request.getParameter("xh")).trim();
				// 查询数据
				sql = "select * from " + tableName + " where " + pk + "='" + xh
						+ "'";
				String[] colList = dao.getColumnName("select * from "
						+ tableName + " where 1=2"); // 返回列名数组
				String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
				if (stuinfo != null) {
					for (int i = 0; i < colList.length; i++) {
						map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
					}
				}

				request.setAttribute("type", "add");
				request.setAttribute("update", "yes");
			}
			if ("save".equalsIgnoreCase(act)) {
				CommanForm dataSearchForm = (CommanForm) form;

				String xh = dataSearchForm.getXh();
				String jyxybh = dataSearchForm.getJyxybh();
				String remark = DealString.toGBK(dataSearchForm.getRemark());
				String lqqk = DealString.toGBK(request.getParameter("lqqk"));
				boolean insert = StandardOperation.delete("jygl_jyxyslqdjb",
						"xh", xh, request);
				if (insert) {
					insert = StandardOperation.insert("jygl_jyxyslqdjb",
							new String[] { "xh", "jyxybh", "remark", "lqqk" },
							new String[] { xh, jyxybh, remark, lqqk }, request);
				}
				if (insert) {
					request.setAttribute("inserted", "ok");
				} else {
					request.setAttribute("inserted", "no");
				}
			}
			if ("allSave".equalsIgnoreCase(act)) {
				CommanForm dataSearchForm = (CommanForm) form;
				String[] checkVal = dataSearchForm.getPk1();
				// 判断有否选中
				if (checkVal != null) {
					sql = "";
					StringBuffer sb = new StringBuffer();
					String[] pksql = new String[] {};
					String lqsj = StandardOperation.getRightTime().substring(0,
							8);
					for (int i = 0; i < checkVal.length; i++) {
						String xh = DealString.toGBK(checkVal[i]);
						sql = "update jygl_jyxyslqdjb set lqqk = '已领取',lqsj='"
								+ lqsj + "' where xh = '" + xh + "'";
						sb.append(sql);
						sb.append("!!#!!");
					}
					pksql = sb.toString().split("!!#!!");
					dao.runBatch(pksql);
				}
				return new ActionForward("/jygl_xylqdj.do?act=go");
			}
		}
		// ============end 中国美术学院 2009/6/4 luojw ===========
		if (true) {
			sql = "select NEWJYXYBH from view_jygl_jyxyblsqb where " + pk
					+ "='" + pkValue + "' and xxsh='已通过√'";
			String newjyxybh = dao.getOneRs(sql, new String[] {}, "NEWJYXYBH");
			map
					.put("newremark", "新就业协议编号："
							+ newjyxybh
							+ "\n"
							+ (Base.isNull(map.get("remark")) ? "" : map
									.get("remark")));
		}
		if ("stu".equalsIgnoreCase(userType)) {
			// 查询数据
			sql = "select * from view_xsxxb where xh ='" + userName + "'";
			String[] colList = dao
					.getColumnName("select * from view_xsxxb where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
			}
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 就业协议补领
	private ActionForward jyxyBlsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String doType = request.getParameter("doType");

		if ("save".equalsIgnoreCase(doType)) {
			String xh = DealString.toGBK(request.getParameter("xh")); // 学号
			String xl = DealString.toGBK(request.getParameter("xl")); // 学历
			String bysj = DealString.toGBK(request.getParameter("bysj")); // 毕业时间
			String sqbg = DealString.toGBK(request.getParameter("sqbg")); // 申请报告
			String sqsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
							new String[] {}, new String[] { "sdate" }))[0]; // 申请时间

			boolean judge = false;

			judge = StandardOperation.insert("JYGL_JYXYBLSQB", new String[] {
					"xh", "xl", "bysj", "sqbg", "sqsj" }, new String[] { xh,
					xl, bysj, sqbg, sqsj }, request);
			if (judge) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "no");
			}

		}

		if (!"stu".equalsIgnoreCase(userType)) {
			request.setAttribute("nopower", "nopower");
		} else {
			String[] stuinfo = dao.getOneRs(
					"select * from view_xsjbxx where xh=?",
					new String[] { userName }, new String[] { "xh", "xb", "xm",
							"xymc", "zymc" });
			String xysbhinfo = dao.getOneRs(
					"select jyxybh from view_jygl_jyxyslqdjb where xh=?",
					new String[] { userName }, "jyxybh");

			if (null == xysbhinfo || "".equalsIgnoreCase(xysbhinfo)) {
				request.setAttribute("nojyxy", "nojyxy");
			}
			if (null != stuinfo) {
				map.put("xh", stuinfo[0]);
				map.put("xb", stuinfo[1]);
				map.put("xm", stuinfo[2]);
				map.put("xymc", stuinfo[3]);
				map.put("zymc", stuinfo[4]);
				if (null != xysbhinfo) {
					map.put("jyxybh", xysbhinfo);
				}
			}

		}

		request.setAttribute("rs", map);

		return mapping.findForward("success");
	}

	// 就业协议补领管理
	private ActionForward jyxyBlsqQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		String tableName = "view_jygl_jyxyblsqb";
		String realTable = "jygl_jyxyblsqb";
		String pk = "xh";
		String sql = "";
		// 判断用户类型
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String xh = DealString.toGBK(request.getParameter("xh"));
		String xm = DealString.toGBK(request.getParameter("xm"));
		String xb = DealString.toGBK(request.getParameter("xb"));
		String nj = DealString.toGBK(request.getParameter("nj"));
		String xydm = DealString.toGBK(request.getParameter("xydm"));
		String zydm = DealString.toGBK(request.getParameter("zydm"));
		String bjdm = DealString.toGBK(request.getParameter("bjdm"));
		String xysh = DealString.toGBK(request.getParameter("xysh"));
		String xxsh = DealString.toGBK(request.getParameter("xxsh"));
		if ("admin".equalsIgnoreCase(userType)
				|| "xx".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xx");
		} else if ("xy".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xy");
			xydm = userDep;
		}

		if ("delall".equalsIgnoreCase(doType)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				pkstringI = pkstring.split("!!#!!");
			}

			boolean judge = false;
			for (int i = 1; i < pkstringI.length; i++) {
				String whichxh = pkstringI[i];
				judge = StandardOperation.delete(realTable, pk, whichxh,
						request);
			}
			if (judge) {
				request.setAttribute("delall", "ok");
			} else {
				request.setAttribute("delall", "no");
			}
		}

		StringBuffer query = new StringBuffer();

		if (!("".equals(xh))) {
			query.append(" and xh like '%");
			query.append(xh);
			query.append("%'");
		}
		if (!("".equals(xm))) {
			query.append(" and xm like '%");
			query.append(xm);
			query.append("%'");
		}
		if (!("".equals(xb))) {
			query.append(" and xb like '%");
			query.append(xb);
			query.append("%'");
		}

		if (!("".equals(nj))) {
			query.append(" and dqszj = '%");
			query.append(nj);
			query.append("%'");
		}
		if (!("".equals(xydm))) {
			query.append(" and xydm='");
			query.append(xydm);
			query.append("'");
		}
		if (!("".equals(zydm))) {
			query.append(" and zydm='");
			query.append(zydm);
			query.append("'");
		}
		if (!("".equals(bjdm))) {
			query.append(" and bjdm='");
			query.append(bjdm);
			query.append("'");
		}
		if (!("".equals(xysh))) {
			query.append(" and xysh='");
			query.append(xysh);
			query.append("'");
		}
		if (!("".equals(xxsh))) {
			query.append(" and xxsh='");
			query.append(xxsh);
			query.append("'");
		}

		String query1 = query.toString();

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from view_jygl_jyxyblsqb a where 1=1 "
				+ query1;

		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// 学生基本信息
		sql = "select a.r 行号,a.* from (select a.*,rownum r from (select distinct  a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.xyshimg,a.xxshimg from view_jygl_jyxyblsqb a where 1=1 "
				+ query1
				+ " ) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize());

		String[] colList = new String[] { "行号", "xh", "xm", "xb", "nj", "xymc",
				"zymc", "bjmc", "xyshimg", "xxshimg" };
		if ("go".equalsIgnoreCase(act)) {
			rs = dao.getArrayList2(sql, new String[] {}, colList);
			map.put("xh", xh);
			map.put("xm", xm);
			map.put("xb", xb);
			map.put("nj", nj);
			map.put("xydm", xydm);
			map.put("zydm", zydm);
			map.put("bjdm", bjdm);
			map.put("xysh", xysh);
			map.put("xxsh", xxsh);
		}

		sql = "select count(*) from view_jygl_jyxyblsqb where 1=1 " + query1;
		int rsNuminfo = dao.getOneRsint(sql);
		String rsNum = String.valueOf(rsNuminfo);
		String[] colListCN = dao
				.getColumnNameCN(colList, "view_jygl_jyxyblsqb");
		List topTr = dao.arrayToList(colList, colListCN);

		request.setAttribute("querry", query1);
		request.setAttribute("rs1", map);
		request.setAttribute("realTable", "view_jygl_jyxyblsqb");
		request.setAttribute("table", "view_jygl_jyxyblsqb");
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);
		request.setAttribute("tableName", tableName);
		request.setAttribute("njList", dao.getNjList());// 发送年级列表
		request.setAttribute("xyList", dao.getXyList());// 发送学院列表
		request.setAttribute("zyList", dao.getZyList(xydm));// 发送专业列表
		request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));// 发送专业列表
		return mapping.findForward("success");
	}

	// 就业协议补领详细查看--审核
	private ActionForward jyxyBlsqViewMore(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		DAO dao = DAO.getInstance();
		String sql = "";
		String pk = "xh";
		String pkValue = request.getParameter("pkValue");
		String tableName = "view_jygl_jyxyblsqb";
		String realTable = "jygl_jyxyblsqb";
		String act = request.getParameter("act");

		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");

		boolean judge = false;

		if ("xysh".equalsIgnoreCase(doType2)) {
			String xysh = DealString.toGBK(request.getParameter("xysh"));
			String xyshsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
							new String[] {}, new String[] { "sdate" }))[0]; // 时间
			judge = StandardOperation.update(realTable, new String[] { "xysh",
					"xyshsj" }, new String[] { xysh, xyshsj }, pk, pkValue,
					request);

			if (judge) {
				request.setAttribute("xysh", "ok");
			} else {
				request.setAttribute("xysh", "no");
			}
		}

		if ("xxsh".equalsIgnoreCase(doType2)) {
			String xxsh = DealString.toGBK(request.getParameter("xxsh"));
			String newjyxybh = DealString.toGBK(request
					.getParameter("newjyxybh"));
			String xxshsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
							new String[] {}, new String[] { "sdate" }))[0]; // 时间
			judge = StandardOperation.update(realTable, new String[] { "xxsh",
					"xxshsj", "newjyxybh" }, new String[] { xxsh, xxshsj,
					newjyxybh }, pk, pkValue, request);

			if (judge) {
				request.setAttribute("xxsh", "ok");
			} else {
				request.setAttribute("xxsh", "no");
			}

		}

		if ("view".equalsIgnoreCase(act)) {
			// 查询数据
			sql = "select * from " + tableName + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + tableName
					+ " where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				String sqsj = map.get("sqsj");
				String xyshsj = map.get("xyshsj");
				String xxshsj = map.get("xxshsj");

				if (null != sqsj && !"".equalsIgnoreCase(sqsj)) {
					map.put("sqsj", dao.doForTime(sqsj));
				}
				if (null != xyshsj && !"".equalsIgnoreCase(xyshsj)) {
					map.put("xyshsj", dao.doForTime(xyshsj));
				}
				if (null != xxshsj && !"".equalsIgnoreCase(xxshsj)) {
					map.put("xxshsj", dao.doForTime(xxshsj));
				}

			}
		}

		request.setAttribute("pk", pkValue);
		request.setAttribute("rs", map);

		if ("xysh".equalsIgnoreCase(doType)) {
			return mapping.findForward("xysh");
		}
		if ("xxsh".equalsIgnoreCase(doType)) {
			return mapping.findForward("xxsh");
		}

		return mapping.findForward("success");
	}

	// 毕业去向页面打开
	private ActionForward byqxInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		DAO dao = DAO.getInstance();
		String xxdm = StandardOperation.getXxdm();
		String sql = "";
		String tableName = "jygl_byqx";
		HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		userType = session.getAttribute("userOnLine").toString();
		String xsxh = request.getParameter("xsxh");
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		String bysjytj = request.getParameter("bysjytjb");

		if ("student".equals(userType)) {
			xsxh = session.getAttribute("userName").toString();
			act = "go";
		}

		sql = "select byqxdm,byqx from dmk_byqx";
		List byqxList = dao.getList(sql, new String[] {}, new String[] {
				"byqxdm", "byqx" });
		request.setAttribute("byqxList", byqxList);

		if (xxdm.equals(Globals.XXDM_ZZSF)) { // 漳州师范
			if ("save".equals(doType)) {
				String bynd = request.getParameter("bynd"); // 毕业年度
				String xymc = DealString.toGBK(request.getParameter("xymc"));// 学院名称
				xsxh = request.getParameter("xsxh");// 学号
				String name = DealString.toGBK(request.getParameter("name"));// 姓名
				String xb = DealString.toGBK(request.getParameter("xb")); // 性别
				String id = request.getParameter("id"); // 身份证号
				String sydq = DealString.toGBK(request.getParameter("sydq")); // 生源地区
				String byqx = DealString.toGBK(request.getParameter("byqx")); // 毕业去向
				String lxdz = DealString.toGBK(request.getParameter("lxdz")); // 联系地址
				String lxdh = request.getParameter("lxdh"); // 联系电话
				String yzbm = request.getParameter("yzbm"); // 邮政编码
				String yddh = request.getParameter("yddh"); // 移动电话
				String email = request.getParameter("email"); // 电子邮箱
				String dayjdz = DealString
						.toGBK(request.getParameter("dayjdz")); // 档案邮寄地址
				String dajydh = DealString
						.toGBK(request.getParameter("dajydh")); // 档案机要单号
				String ydjycrq = DealString.toGBK(request
						.getParameter("ydjycrq")); // 邮电局邮戳日期
				String bdzh = DealString.toGBK(request.getParameter("bdzh")); // 报道证号
				String xysbh = DealString.toGBK(request.getParameter("xysbh")); // 协议书编号
				String xslb = DealString.toGBK(request.getParameter("xslb")); // 学生类别

				String tjsj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

				boolean judge = false;
				judge = StandardOperation.insert(tableName, new String[] {
						"bynd", "xymc", "xsxh", "name", "xb", "id", "sydq",
						"byqx", "lxdz", "lxdh", "yzbm", "yddh", "email",
						"tjsj", "dayjdz", "dajydh", "ydjycrq", "bdzh", "xysbh",
						"xslb" }, new String[] { bynd, xymc, xsxh, name, xb,
						id, sydq, byqx, lxdz, lxdh, yzbm, yddh, email, tjsj,
						dayjdz, dajydh, ydjycrq, bdzh, xysbh, xslb }, request);
				if (judge) {
					request.setAttribute("save", "ok");

					// 添加操作的记录
					if ("teacher".equals(userType)) {
						String userName = session.getAttribute("userName")
								.toString();
						String whensj = (dao
								.getOneRs(
										"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
										new String[] {},
										new String[] { "sdate" }))[0]; // 提交时间

						StandardOperation.insert("JYGL_USERCZMXB",
								new String[] { "userid", "dowhat",
										"whichtable", "whichpk", "whensj" },
								new String[] { userName, "增加", "毕业去向表",
										"学号:" + xsxh, whensj }, request);
					}
				} else {
					request.setAttribute("save", "no");
				}
			}
			map.put("stuExists", "yes");
			map.put("userType", userType);
			request.setAttribute("rs", map);
			return mapping.findForward("success2");// 漳州师范

		} else if (xxdm.equals(Globals.XXDM_YNYS)) { // 云南艺术

			if ("save".equals(doType)) {
				String bynd = request.getParameter("bynd"); // 毕业年度
				String xymc = DealString.toGBK(request.getParameter("xymc"));// 学院名称
				xsxh = request.getParameter("xsxh");// 学号
				String name = DealString.toGBK(request.getParameter("name"));// 姓名
				String xb = DealString.toGBK(request.getParameter("xb")); // 性别
				String sydq = DealString.toGBK(request.getParameter("sydq")); // 生源地区
				String byqx = DealString.toGBK(request.getParameter("byqx")); // 毕业去向
				String lxdz = DealString.toGBK(request.getParameter("lxdz")); // 联系地址
				String lxdh = request.getParameter("lxdh"); // 联系电话
				String yzbm = request.getParameter("yzbm"); // 邮政编码
				String yddh = request.getParameter("yddh"); // 移动电话
				String email = request.getParameter("email"); // 电子邮箱
				String xslb = DealString.toGBK(request.getParameter("xslb")); // 学生类别
				String tjsj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

				boolean judge = false;
				judge = StandardOperation.insert(tableName, new String[] {
						"bynd", "xymc", "xsxh", "name", "xb", "sydq", "byqx",
						"lxdz", "lxdh", "yzbm", "yddh", "email", "tjsj",
						"xslb", "xxsh" }, new String[] { bynd, xymc, xsxh,
						name, xb, sydq, byqx, lxdz, lxdh, yzbm, yddh, email,
						tjsj, xslb, "直接通过" }, request);
				if (judge) {
					request.setAttribute("save", "ok");

					// 添加操作的记录
					if ("teacher".equals(userType)) {
						String userName = session.getAttribute("userName")
								.toString();
						String whensj = (dao
								.getOneRs(
										"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
										new String[] {},
										new String[] { "sdate" }))[0]; // 提交时间

						StandardOperation.insert("JYGL_USERCZMXB",
								new String[] { "userid", "dowhat",
										"whichtable", "whichpk", "whensj" },
								new String[] { userName, "增加", "毕业去向表",
										"学号:" + xsxh, whensj }, request);
					}
				} else {
					request.setAttribute("save", "no");
				}
			}

			map.put("stuExists", "yes");
			map.put("userType", userType);
			request.setAttribute("rs", map);
			return mapping.findForward("success3");// 云南艺术

		}

		// ------------2010/6/8 edit by luojw -----------
		boolean isSave = false;

		if ("save".equals(doType)) {

			xsxh = request.getParameter("xsxh");// 学号
			if ("12453".equals(xxdm)) {
				String[] str = dao.getOneRs(
						"select sfsh from jygl_xsjbxxb where xsxh=?",
						new String[] { xsxh }, new String[] { "sfsh" });
				boolean bool = false;
				if (str != null) {
					if ("已通过√".equals(str[0])) {
						bool = true;
					}
				}
				if (bool) {
					String bynd = request.getParameter("bynd"); // 毕业年度
					String xymc = DealString
							.toGBK(request.getParameter("xymc"));// 学院名称
					String name = DealString
							.toGBK(request.getParameter("name"));// 姓名
					String xb = DealString.toGBK(request.getParameter("xb")); // 性别
					String id = request.getParameter("id"); // 身份证号
					String sydq = DealString
							.toGBK(request.getParameter("sydq")); // 生源地区
					String byqx = DealString
							.toGBK(request.getParameter("byqx")); // 毕业去向
					String lxdz = DealString
							.toGBK(request.getParameter("lxdz")); // 联系地址
					String lxdh = request.getParameter("lxdh"); // 联系电话
					String yzbm = request.getParameter("yzbm"); // 邮政编码
					String yddh = request.getParameter("yddh"); // 移动电话
					String email = DealString.toGBK(request
							.getParameter("email")); // 电子邮箱
					String xslb = DealString
							.toGBK(request.getParameter("xslb")); // 学生类别

					String tjsj = (dao
							.getOneRs(
									"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
									new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

					boolean judge = false;
					judge = StandardOperation.insert(tableName, new String[] {
							"bynd", "xymc", "xsxh", "name", "xb", "id", "sydq",
							"byqx", "lxdz", "lxdh", "yzbm", "yddh", "email",
							"tjsj", "xslb" }, new String[] { bynd, xymc, xsxh,
							name, xb, id, sydq, byqx, lxdz, lxdh, yzbm, yddh,
							email, tjsj, xslb }, request);
					if (judge) {
						request.setAttribute("save", "ok");

						// 添加操作的记录
						if ("teacher".equals(userType)) {
							String userName = session.getAttribute("userName")
									.toString();
							String whensj = (dao
									.getOneRs(
											"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
											new String[] {},
											new String[] { "sdate" }))[0]; // 提交时间

							StandardOperation
									.insert("JYGL_USERCZMXB", new String[] {
											"userid", "dowhat", "whichtable",
											"whichpk", "whensj" },
											new String[] { userName, "增加",
													"毕业去向表", "学号:" + xsxh,
													whensj }, request);
						}
					} else {
						request.setAttribute("save", "no");
					}
				} else {
					request.setAttribute("exists", "exists");
				}
			} else {

				String bynd = request.getParameter("bynd"); // 毕业年度
				String xymc = DealString.toGBK(request.getParameter("xymc"));// 学院名称
				xsxh = request.getParameter("xsxh");// 学号
				String name = DealString.toGBK(request.getParameter("name"));// 姓名
				String xb = DealString.toGBK(request.getParameter("xb")); // 性别
				String id = request.getParameter("id"); // 身份证号
				String sydq = DealString.toGBK(request.getParameter("sydq")); // 生源地区
				String byqx = DealString.toGBK(request.getParameter("byqx")); // 毕业去向
				String lxdz = DealString.toGBK(request.getParameter("lxdz")); // 联系地址
				String lxdh = request.getParameter("lxdh"); // 联系电话
				String yzbm = request.getParameter("yzbm"); // 邮政编码
				String yddh = request.getParameter("yddh"); // 移动电话
				String email = DealString.toGBK(request.getParameter("email")); // 电子邮箱
				String xslb = DealString.toGBK(request.getParameter("xslb")); // 学生类别
				String jydw = request.getParameter("jydw");
				String tjsj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // 提交时间
				map.put("jydw", jydw);
				map.put("byqx", byqx);
				map.put("lxdz", lxdz);
				map.put("lxdh", lxdh);
				map.put("yzbm", yzbm);
				map.put("yddh", yddh);
				map.put("email", email);

				boolean judge = false;
				judge = StandardOperation.insert(tableName, new String[] {
						"bynd", "xymc", "xsxh", "name", "xb", "id", "sydq",
						"byqx", "lxdz", "lxdh", "yzbm", "yddh", "email",
						"tjsj", "xslb", "jydw" }, new String[] { bynd, xymc,
						xsxh, name, xb, id, sydq, byqx, lxdz, lxdh, yzbm, yddh,
						email, tjsj, xslb, jydw }, request);
				if (judge) {
					request.setAttribute("save", "ok");

					// 添加操作的记录
					if ("teacher".equals(userType)) {
						String userName = session.getAttribute("userName")
								.toString();
						String whensj = (dao
								.getOneRs(
										"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
										new String[] {},
										new String[] { "sdate" }))[0]; // 提交时间

						StandardOperation.insert("JYGL_USERCZMXB",
								new String[] { "userid", "dowhat",
										"whichtable", "whichpk", "whensj" },
								new String[] { userName, "增加", "毕业去向表",
										"学号:" + xsxh, whensj }, request);
					}
				} else {
					request.setAttribute("save", "no");
				}
				isSave = true;
				
			}
		}

		if ("go".equalsIgnoreCase(act) || isSave) {
			sql = "select * from jygl_xsjbxxb where xsxh=?"; // 查询该学号的相关内容的sql语句
			String[] colList = dao
					.getColumnName("select * from jygl_xsjbxxb where 1=2"); // 返回列名数组
			if (StringUtils.isNotNull(xsxh)) {
				xsxh = xsxh.trim();
			}
			String[] rs = dao.getOneRs(sql, new String[] { xsxh }, colList); // 获得从毕业生基本信息表（视图）中查询的记录集
			if (rs != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), rs[i]); // 将记录循环放入map中
				}
			}
			if ("12453".equals(xxdm)) {
				if (null != rs) {
					if (null != map.get("xldm")) {
						String xlsql = "select * from xldmb where xldm=?";
						rs = dao.getOneRs(xlsql,
								new String[] { map.get("xldm") },
								new String[] { "xlmc" });
						if (rs != null) {
							map.put("xlmc", rs[0]);
						}
					}
					map.put("xz", map.get("xzdm"));
				}
				map.put("byxx", "中国劳动关系学院");
				sql = "select sydq from dmk_sydq where sydqdm=?";
				String[] stuinfo = dao.getOneRs(sql, new String[] { map
						.get("sydqdm") }, new String[] { "sydq" });
				if (null != stuinfo) {
					map.put("syd", stuinfo[0]);
				}
			}
			if (null != map) {
				String xbdm = map.get("xbdm");
				if ("1".equals(xbdm)) {
					map.put("xb", "男");
				} else if ("2".equals(xbdm)) {
					map.put("xb", "女");
				}
			}
			sql = "select xymc from view_xsjbxx where xh=?";
			String[] xymc = dao.getOneRs(sql, new String[] { xsxh },
					new String[] { "xymc" });
			if (xymc != null) {
				map.put("xymc", xymc[0]);
			}
			sql = "select sydq from dmk_sydq where sydqdm=?";
			String[] stuinfo = dao.getOneRs(sql, new String[] { map
					.get("sydqdm") }, new String[] { "sydq" });
			if (null != stuinfo) {
				map.put("sydq", stuinfo[0]);
			}
		}
		// ------------end -----------
		map.put("stuExists", "yes");
		map.put("userType", userType);
		request.setAttribute("rs", map);
		
		if("save".equalsIgnoreCase(doType)){
			String lxdh = request.getParameter("lxdh"); // 联系电话
			map.put("lxdh", lxdh);
		}

		if ("bysjytjb".equals(bysjytj)) {
			request.setAttribute("rs1", map);
			lrh_commen_util commen_util = new lrh_commen_util();
			request.setAttribute("xyList", commen_util.get_xyList());
			request.setAttribute("zyList", commen_util.get_zyList(""));
			request.setAttribute("bjList", commen_util.get_bjList("", ""));
			return mapping.findForward("bysjytj");
		} else {
			return mapping.findForward("success");
		}

	}

	// 毕业去向查询页面打开、查询、删除
	private ActionForward byqxQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		DAO dao = DAO.getInstance(); // 实例化通用方法，并获得数据库连接
		CommanForm commanForm = (CommanForm) form;
		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = ""; // sql语句
		String querry = " where 1=1 "; // sql条件
		String tableName = "view_jygl_byqx"; // 查询信息源表（多为视图名）
		String realTable = "jygl_byqx"; // 查询信息源表
		String rsNum = "0";// 返回的记录数
		String xxdm = StandardOperation.getXxdm();
		String dataType = request.getParameter("act"); // 接收数据类型
		String xsxh = request.getParameter("xsxh"); // 接收学号
		String name = DealString.toGBK(request.getParameter("name")); // 接收姓名
		String xb = DealString.toGBK(request.getParameter("xb")); // 接收性别
		String nj = DealString.toGBK(request.getParameter("nj")); // 年级
		String xymc = DealString.toGBK(request.getParameter("xymc")); // 学院名称
		String zymc = DealString.toGBK(request.getParameter("zymc")); // 专业名称
		String bjmc = DealString.toGBK(request.getParameter("bjmc")); // 班级名称
		String bynd = request.getParameter("bynd"); // 毕业年度
		String xxsh = DealString.toGBK(request.getParameter("xxsh")); // 审核

		String pk = "xsxh"; // 主键
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String pkValue = request.getParameter("pkValue");
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();

		if ("del".equals(doType2)) {
			boolean judge = false;
			judge = StandardOperation.delete(realTable, pk, pkValue, request);
			if (judge) {
				request.setAttribute("delete", "ok");

				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"删除", "毕业去向表", "学号:" + pkValue, whensj },
						request);
			} else {
				request.setAttribute("delete", "no");
			}
		}

		if (userType.equals("teacher")) {
			request.setAttribute("whichtype", "allteacher");
			sql = "select * from jygl_jyxywhb where username=?";
			String[] teainfo = dao.getOneRs(sql, new String[] { userName },
					new String[] { "usertype", "xymc", "zymc", "bj" });
			String tea = "";
			if (null != teainfo) {
				tea = teainfo[0];

			}
			if ("辅导员".equals(tea)) {
				map.put("xymc", teainfo[1]);
				map.put("zymc", teainfo[2]);
				map.put("bjmc", teainfo[3]);
				request.setAttribute("who", "fudaoyuan");
			} else {
				request.setAttribute("who", "teacher");
			}
		} else if ("student".equals(userType)) {
			map.put("xsxh", userName);
			request.setAttribute("whichtype", "student");
		}

		if (xxdm.equals(Globals.XXDM_ZZSF)) { // 漳州师范
			String xydm = "";
			String zydm = "";
			sql = "select distinct xydm from view_njxyzybj where xymc=?";
			String[] xyinfo = dao.getOneRs(sql, new String[] { xymc },
					new String[] { "xydm" });
			if (null != xyinfo) {
				xydm = xyinfo[0];
			}
			sql = "select distinct zydm from view_njxyzybj where zymc=?";
			String[] zyinfo = dao.getOneRs(sql, new String[] { zymc },
					new String[] { "zydm" });
			if (null != zyinfo) {
				zydm = zyinfo[0];
			}

			// 把上次提交的值传回去
			if ("query".equals(doType)) {
				map.put("xsxh", xsxh);
				map.put("name", name);
				map.put("xb", xb);
				map.put("nj", nj);
				map.put("xymc", xymc);
				map.put("zymc", zymc);
				map.put("bjmc", bjmc);
				map.put("bynd", bynd);
				map.put("xxsh", xxsh);
			}

			if (xsxh == null) {
				xsxh = "";
			}
			if (name == null) {
				name = "";
			}
			if (xb == null) {
				xb = "";
			}
			if (nj == null) {
				nj = "";
			}

			if (xymc == null) {
				xymc = "";
			}
			if (zymc == null) {
				zymc = "";
			}
			if (bjmc == null) {
				bjmc = "";
			}

			if (bynd == null) {
				bynd = "";
			}
			if (xxsh == null) {
				xxsh = "";
			}
			if ((xsxh == null) || xsxh.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xsxh like '%" + xsxh + "%' ";
			}
			if ((name == null) || name.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and name like '%" + name + "%' ";
			}
			if ((nj == null) || nj.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and nj = '" + nj + "' ";
			}

			if ((xb == null) || xb.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xb='" + xb + "' ";
			}
			if ((xymc == null) || xymc.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xymc='" + xymc + "' ";
			}
			if ((zymc == null) || zymc.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and zymc='" + zymc + "' ";
			}
			if ((bjmc == null) || bjmc.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and bjmc='" + bjmc + "' ";
			}

			if ((bynd == null) || bynd.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and bynd='" + bynd + "' ";
			}
			if ((xxsh == null) || xxsh.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xxsh='" + xxsh + "' ";
			}
			sql = "select " + pk + " 主键,rownum r,a.* from " + tableName + " a "
					+ querry;
			colList = new String[] { "主键", "r", "bynd", "name", "xsxh", "xb",
					"lxdh", "yddh", "tjsj", "xxsh" };
			colListCN = dao.getColumnNameCN(colList, tableName);
			List topTr = dao.arrayToList(colList, colListCN);

			rs.addAll(CommonQueryDAO.commonQuery(sql, "", new String[] {},
					colList, commanForm));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}

			request.setAttribute("realTable", "jygl_byqx"); // 表是学生基本信息表
			request.setAttribute("njList", dao.getNjList()); // 发送年级列表
			request.setAttribute("xyList", dao.getXyList());// 发送学院列表
			request.setAttribute("zyList", dao.getZyList(xydm));// 发送专业列表
			request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));// 发送学院列表
			request.setAttribute("act", dataType);// 发送数据查询类型
			request.setAttribute("rs", rs);// 发送数据集
			request.setAttribute("topTr", topTr);// 发送表头
			request.setAttribute("rsNum", rsNum);// 发送记录数
			request.setAttribute("rs1", map);
			return mapping.findForward("success2");

		}

		// 把上次提交的值传回去
		if ("query".equals(doType)) {
			map.put("xsxh", xsxh);
			map.put("name", name);
			map.put("xb", xb);
			map.put("xymc", xymc);
			map.put("bynd", bynd);
			map.put("xxsh", xxsh);
		}

		if (xsxh == null) {
			xsxh = "";
		}
		if (name == null) {
			name = "";
		}
		if (xb == null) {
			xb = "";
		}
		if (xymc == null) {
			xymc = "";
		}
		if (bynd == null) {
			bynd = "";
		}
		if (xxsh == null) {
			xxsh = "";
		}
		if ((xsxh == null) || xsxh.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xsxh like '%" + xsxh + "%' ";
		}
		if ((name == null) || name.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and name like '%" + name + "%' ";
		}
		if ((xb == null) || xb.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xb='" + xb + "' ";
		}
		if ((xymc == null) || xymc.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xymc='" + xymc + "' ";
		}

		if ((bynd == null) || bynd.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and bynd='" + bynd + "' ";
		}
		if ((xxsh == null) || xxsh.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xxsh='" + xxsh + "' ";
		}

		if (xxdm.equals(Globals.XXDM_YNYS)) {// 云南艺术
			colList = new String[] { "主键", "行号", "bynd", "name", "xsxh", "xb",
					"lxdh", "yddh", "tjsj" };
			sql = "select " + pk + " 主键,rownum 行号,a.* from " + realTable
					+ " a " + querry;
		} else {
			colList = new String[] { "主键", "r", "bynd", "name", "xsxh", "xb",
					"xymc", "lxdh", "yddh", "tjsj", "xxsh", "fdysh" };
			sql = "select " + pk + " 主键,rownum r,a.* from " + realTable + " a "
					+ querry;
		}
		colListCN = dao.getColumnNameCN(colList, realTable);
		List topTr = dao.arrayToList(colList, colListCN);
	

		if (xxdm.equals(Globals.XXDM_YNYS)) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));

		} else {
			rs.addAll(CommonQueryDAO.commonQuery(sql, "", new String[] {},
					colList, commanForm));
		}
		if (rs == null) {
			rsNum = "0";
		} else {
			rsNum = String.valueOf(rs.size());
		}
		

		String usertype = request.getSession().getAttribute("userType")
				.toString();
		String bbmc = (String) request.getSession().getAttribute("bmmc");
		if ("xx".equals(usertype) || "admin".equals(usertype)) {
			request.setAttribute("ldgxusertype", "xx");
			request.setAttribute("who", "teacher");
		} else if ("xy".equals(usertype)) {
			request.setAttribute("ldgxusertype", "xy");
			request.setAttribute("who", "fudaoyuan");
			map.put("xymc", bbmc);
		}

		request.setAttribute("realTable", "jygl_byqx"); // 表是学生基本信息表
		request.setAttribute("xyList", dao.getXyList());// 发送学院列表
		request.setAttribute("act", dataType);// 发送数据查询类型
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("rs1", map);

		if (!xxdm.equals(Globals.XXDM_YNYS)) {
			request.setAttribute("view", "yes");
		}

		request.setAttribute("path", "openbyqxqueryweb.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("success");
	}

	// 点击查看毕业去向更详细信息
	private ActionForward byqxViewMoreQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = "xsxh";
		String sql = ""; // sql语句
		String xxdm = StandardOperation.getXxdm();
		DAO dao = DAO.getInstance();
		// String realTable = "jygl_byqx";
		String tableName = "view_jygl_byqx";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// 查询数据
			sql = "select * from " + tableName + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from view_jygl_byqx where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			HashMap<String,String>jyqxMap=dao.getMap(" select * from jygl_byqx where "+pk+"=? ",new String[]{pkValue},new String[]{"xgyj"});
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}

				String tjsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				String sj = map.get("tjsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				tjsj = sjyear + "年" + sjmon + "月" + sjday + "日 " + sjhour + "时";
				map.put("tjsj", tjsj);
				if (map.get("shsj") != null && !"".equals(map.get("shsj"))) {
					String shsj = null;
					sjyear = null;
					sjmon = null;
					sjday = null;
					sjhour = null;
					sj = map.get("shsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					shsj = sjyear + "年" + sjmon + "月" + sjday + "日 " + sjhour
							+ "时";
					map.put("shsj", shsj);
				}
				map.putAll(jyqxMap);
			}
		}
		if (xxdm.equals(Globals.XXDM_ZZSF)) {// 樟州师范
			request.setAttribute("rs", map);// 发送数据集
			return mapping.findForward("success2");
		} else if (xxdm.equals(Globals.XXDM_YNYS)) {// 云南艺术
			request.setAttribute("rs", map);// 发送数据集
			return mapping.findForward("success3");
		}
		request.setAttribute("rs", map);// 发送数据集
		return mapping.findForward("success");
	}

	// 毕业去向修改
	private ActionForward jyglByqxUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = "xsxh";
		String sql = ""; // sql语句
		DAO dao = DAO.getInstance();
		String xxdm = StandardOperation.getXxdm();
		String realTable = "jygl_byqx";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();
		String xsxh = request.getParameter("xsxh");
		String act = request.getParameter("act");

		sql = "select byqxdm,byqx from dmk_byqx";
		List byqxList = dao.getList(sql, new String[] {}, new String[] {
				"byqxdm", "byqx" });
		request.setAttribute("byqxList", byqxList);

		sql = "select * from  jygl_byqx where xsxh=?";
		String[] byqxinfo = dao.getOneRs(sql, new String[] { xsxh },
				new String[] { "xxsh" });
		String byqxin = "";
		if (null != byqxinfo) {
			byqxin = byqxinfo[0];
		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			return mapping.findForward("false");
		} else if ("update".equalsIgnoreCase(doType)) {
			// 查询数据
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from jygl_byqx where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				String tjsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				String sj = map.get("tjsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				tjsj = sjyear + "年" + sjmon + "月" + sjday + "日 " + sjhour + "时";
				map.put("tjsj", tjsj);
				if (map.get("shsj") != null && !"".equals(map.get("shsj"))) {
					String shsj = null;
					sjyear = null;
					sjmon = null;
					sjday = null;
					sjhour = null;
					sj = map.get("shsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					shsj = sjyear + "年" + sjmon + "月" + sjday + "日 " + sjhour
							+ "时";
					map.put("shsj", shsj);
				}
			}
		}

		if (xxdm.equals(Globals.XXDM_ZZSF)) { // 漳州师范
			if ("update".equals(act)) {
				String byqx = DealString.toGBK(request.getParameter("byqx")); // 毕业去向
				String lxdz = DealString.toGBK(request.getParameter("lxdz")); // 联系地址
				String lxdh = request.getParameter("lxdh"); // 联系电话
				String yzbm = request.getParameter("yzbm"); // 邮政编码
				String yddh = request.getParameter("yddh"); // 移动电话
				String email = request.getParameter("email"); // 电子邮箱
				String dayjdz = DealString
						.toGBK(request.getParameter("dayjdz")); // 档案邮寄地址
				String dajydh = DealString
						.toGBK(request.getParameter("dajydh")); // 档案机要单号
				String ydjycrq = DealString.toGBK(request
						.getParameter("ydjycrq")); // 邮电局邮戳日期
				String bdzh = DealString.toGBK(request.getParameter("bdzh")); // 报道证号
				String xysbh = DealString.toGBK(request.getParameter("xysbh")); // 协议书编号
				
				boolean judge = false;

				if ("未通过X".equals(byqxin) || "未审核".equals(byqxin)) {
					judge = StandardOperation.update(realTable, new String[] {
							"byqx", "lxdz", "lxdh", "yzbm", "yddh", "email",
							"xxsh", "dayjdz", "dajydh", "ydjycrq", "bdzh",
							"xysbh" }, new String[] { byqx, lxdz, lxdh, yzbm,
							yddh, email, "未审核", dayjdz, dajydh, ydjycrq, bdzh,
							xysbh }, "xsxh", xsxh, request);
				}
				if (judge) {
					request.setAttribute("update", "ok");

					// 修改操作的记录
					HttpSession session = request.getSession();
					String userName = session.getAttribute("userName")
							.toString();
					String whensj = (dao
							.getOneRs(
									"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
									new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

					StandardOperation.insert("JYGL_USERCZMXB", new String[] {
							"userid", "dowhat", "whichtable", "whichpk",
							"whensj" }, new String[] { userName, "修改", "毕业去向表",
							"学号:" + xsxh, whensj }, request);
				} else {
					request.setAttribute("update", "no");
				}

				sql = "select * from view_jygl_byqx where xsxh=?";
				String[] colList = dao
						.getColumnName("select * from view_jygl_byqx where 1=2"); // 返回列名数组
				String[] stuinfo = dao.getOneRs(sql, new String[] { xsxh },
						colList);
				if (stuinfo != null) {
					for (int i = 0; i < colList.length; i++) {
						map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
					}
					String tjsj = null;
					String sjyear = null;
					String sjmon = null;
					String sjday = null;
					String sjhour = null;
					String sj = map.get("tjsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					tjsj = sjyear + "年" + sjmon + "月" + sjday + "日 " + sjhour
							+ "时";
					map.put("tjsj", tjsj);
					if (map.get("shsj") != null && !"".equals(map.get("shsj"))) {
						String shsj = null;
						sjyear = null;
						sjmon = null;
						sjday = null;
						sjhour = null;
						sj = map.get("shsj").toString();
						sjyear = sj.substring(0, 4);
						sjmon = sj.substring(4, 6);
						sjday = sj.substring(6, 8);
						sjhour = sj.substring(8, 10);
						shsj = sjyear + "年" + sjmon + "月" + sjday + "日 "
								+ sjhour + "时";
						map.put("shsj", shsj);
					}
				}
			}

			request.setAttribute("rs", map);// 发送数据集
			return mapping.findForward("success2");
		}

		if ("update".equals(act)) {
			String byqx = DealString.toGBK(request.getParameter("byqx")); // 毕业去向
			String lxdz = DealString.toGBK(request.getParameter("lxdz")); // 联系地址
			String lxdh = request.getParameter("lxdh"); // 联系电话
			String yzbm = request.getParameter("yzbm"); // 邮政编码
			String yddh = request.getParameter("yddh"); // 移动电话
			String email = request.getParameter("email"); // 电子邮箱
			String jydw = request.getParameter("jydw"); // 协议书编号	
			boolean judge = false;

			if ("未通过X".equals(byqxin) || "未审核".equals(byqxin)
					|| "直接通过".equals(byqxin)) {

				if (xxdm.equals(Globals.XXDM_YNYS)) {
					judge = StandardOperation
							.update(realTable, new String[] { "byqx", "lxdz",
									"lxdh", "yzbm", "yddh", "email" },
									new String[] { byqx, lxdz, lxdh, yzbm,
											yddh, email }, "xsxh", xsxh,
									request);
				} else {
					judge = StandardOperation.update(realTable, new String[] {
							"byqx", "lxdz", "lxdh", "yzbm", "yddh", "email",
							"xxsh","jydw" }, new String[] { byqx, lxdz, lxdh, yzbm,
							yddh, email, "未审核" ,jydw}, "xsxh", xsxh, request);
				}
			}
			if (judge) {
				request.setAttribute("update", "ok");

				// 修改操作的记录
				HttpSession session = request.getSession();
				String userName = session.getAttribute("userName").toString();
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"修改", "毕业去向表", "学号:" + xsxh, whensj }, request);
			} else {
				request.setAttribute("update", "no");
			}

			sql = "select * from jygl_byqx where xsxh=?";
			String[] colList = dao
					.getColumnName("select * from jygl_byqx where 1=2"); // 返回列名数组
			String[] stuinfo = dao
					.getOneRs(sql, new String[] { xsxh }, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				String tjsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				String sj = map.get("tjsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				tjsj = sjyear + "年" + sjmon + "月" + sjday + "日 " + sjhour + "时";
				map.put("tjsj", tjsj);
				if (map.get("shsj") != null && !"".equals(map.get("shsj"))) {
					String shsj = null;
					sjyear = null;
					sjmon = null;
					sjday = null;
					sjhour = null;
					sj = map.get("shsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					shsj = sjyear + "年" + sjmon + "月" + sjday + "日 " + sjhour
							+ "时";
					map.put("shsj", shsj);
				}
			}
		}

		request.setAttribute("rs", map);// 发送数据集
		if (xxdm.equals(Globals.XXDM_YNYS)) {// 云南艺术
			return mapping.findForward("success3");
		}
		return mapping.findForward("success");
	}

	// 弹出毕业趋向学校审核窗口并审核内容
	private ActionForward jyglByqxSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pk = "xsxh";
		HttpSession session = request.getSession();
		String xxdm = StandardOperation.getXxdm();
		String sql = ""; // sql语句
		DAO dao = DAO.getInstance();
		String realTable = "jygl_byqx";
		String tableName = "view_jygl_byqx";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();
		String xxsh = DealString.toGBK(request.getParameter("xxsh"));
		String xgyj = DealString.toGBK(request.getParameter("xgyj"));
		String xsxh = request.getParameter("xsxh");
		String act = request.getParameter("act");
		String shperson = session.getAttribute("userName").toString();
		String usertype = (String) request.getSession()
				.getAttribute("userType");

		if ("xx".equals(usertype) || "admin".equals(usertype)) {
			request.setAttribute("ldgxusertype", "xx");
			request.setAttribute("who", "teacher");
		} else if ("xy".equals(usertype)) {
			request.setAttribute("ldgxusertype", "xy");
			request.setAttribute("who", "fudaoyuan");
		}

		String fdysh = DealString.toGBK(request.getParameter("fdysh"));
		// String fdyshr = DealString.toGBK(request.getParameter("fdyshr"));

		if ("shenhe".equals(act)) {
			if ("12453".equals(xxdm)) {
				if (usertype == "xx" || "xx".equals(usertype)
						|| "admin".equals(usertype)) {

					String shsj = (dao
							.getOneRs(
									"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 辅导员审核时间
									// //
									// 取至数据库临时表
									new String[] {}, new String[] { "sdate" }))[0];
					if ("已通过√".equals(xxsh)) {
						// xgyj = "";
					}
					if ("未审核".equals(xxsh)) {
						shsj = "";
						xgyj = "";
						shperson = "";
					}
					sql = "update " + realTable + " set xxsh='" + xxsh
							+ "' , xgyj='" + xgyj + "' ,shsj='" + shsj
							+ "' ,shperson='" + shperson + "' where xsxh='"
							+ xsxh + "'";

					boolean judge = false;
					judge = dao.runUpdate(sql, new String[] {});

					if (judge) {
						request.setAttribute("shenhe", "ok");
					} else {
						request.setAttribute("shenhe", "no");
					}

					sql = "select * from " + realTable + " where xsxh=?";
					String[] colList = dao
							.getColumnName("select * from jygl_byqx where 1=2"); // 返回列名数组
					String[] stuinfo = dao.getOneRs(sql, new String[] { xsxh },
							colList);
					if (stuinfo != null) {
						for (int i = 0; i < colList.length; i++) {
							map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
						}
						shsj = null;
						String sjyear = null;
						String sjmon = null;
						String sjday = null;
						String sjhour = null;
						if (null != map.get("shsj")) {
							String sj = map.get("shsj").toString();
							sjyear = sj.substring(0, 4);
							sjmon = sj.substring(4, 6);
							sjday = sj.substring(6, 8);
							sjhour = sj.substring(8, 10);
							shsj = sjyear + "年" + sjmon + "月" + sjday + "日"
									+ sjhour + "时";
							map.put("shsj", shsj);
						}
						if (null != map.get("tjsj")) {
							String sj = map.get("tjsj").toString();
							sjyear = sj.substring(0, 4);
							sjmon = sj.substring(4, 6);
							sjday = sj.substring(6, 8);
							sjhour = sj.substring(8, 10);
							String tjsj = sjyear + "年" + sjmon + "月" + sjday
									+ "日" + sjhour + "时";
							map.put("tjsj", tjsj);
						}
					}
				} else {
					String shsj = (dao
							.getOneRs(
									"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 辅导员审核时间
									// //
									// 取至数据库临时表
									new String[] {}, new String[] { "sdate" }))[0];
					if ("已通过√".equals(xxsh)) {
						xgyj = "";
					}
					if ("未审核".equals(xxsh)) {
						shsj = "";
						xgyj = "";
						shperson = "";
					}
					sql = "update " + realTable + " set fdysh='" + fdysh
							+ "' ,fdyshsj='" + shsj + "' ,fdyshr='" + shperson
							+ "' where xsxh='" + xsxh + "'";

					boolean judge = false;
					judge = dao.runUpdate(sql, new String[] {});

					if (judge) {
						request.setAttribute("shenhe", "ok");
					} else {
						request.setAttribute("shenhe", "no");
					}

					sql = "select * from " + realTable + " where xsxh=?";
					String[] colList = dao
							.getColumnName("select * from jygl_byqx where 1=2"); // 返回列名数组
					String[] stuinfo = dao.getOneRs(sql, new String[] { xsxh },
							colList);
					if (stuinfo != null) {
						for (int i = 0; i < colList.length; i++) {
							map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
						}
						shsj = null;
						String sjyear = null;
						String sjmon = null;
						String sjday = null;
						String sjhour = null;
						if (null != map.get("shsj")) {
							String sj = map.get("shsj").toString();
							sjyear = sj.substring(0, 4);
							sjmon = sj.substring(4, 6);
							sjday = sj.substring(6, 8);
							sjhour = sj.substring(8, 10);
							shsj = sjyear + "年" + sjmon + "月" + sjday + "日"
									+ sjhour + "时";
							map.put("shsj", shsj);
						}
						if (null != map.get("fdyshsj")) {
							String sj = map.get("fdyshsj").toString();
							sjyear = sj.substring(0, 4);
							sjmon = sj.substring(4, 6);
							sjday = sj.substring(6, 8);
							sjhour = sj.substring(8, 10);
							shsj = sjyear + "年" + sjmon + "月" + sjday + "日"
									+ sjhour + "时";
							map.put("fdyshsj", shsj);
						}
						if (null != map.get("tjsj")) {
							String sj = map.get("tjsj").toString();
							sjyear = sj.substring(0, 4);
							sjmon = sj.substring(4, 6);
							sjday = sj.substring(6, 8);
							sjhour = sj.substring(8, 10);
							String tjsj = sjyear + "年" + sjmon + "月" + sjday
									+ "日" + sjhour + "时";
							map.put("tjsj", tjsj);
						}
					}
				}
			} else {

				String shsj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 辅导员审核时间
								// //
								// 取至数据库临时表
								new String[] {}, new String[] { "sdate" }))[0];
				if ("已通过√".equals(xxsh)) {
					xgyj = request.getParameter("xgyj");
				}
				if ("未审核".equals(xxsh)) {
					shsj = "";
					xgyj = "";
					shperson = "";
				}
				sql = "update " + realTable + " set xxsh='" + xxsh
						+ "' , xgyj='" + xgyj + "' ,shsj='" + shsj
						+ "' ,shperson='" + shperson + "' where xsxh='" + xsxh
						+ "'";

				boolean judge = false;
				judge = dao.runUpdate(sql, new String[] {});

				if (judge) {
					request.setAttribute("shenhe", "ok");
				} else {
					request.setAttribute("shenhe", "no");
				}

				sql = "select * from " + realTable + " where xsxh=?";
				String[] colList = dao
						.getColumnName("select * from jygl_byqx where 1=2"); // 返回列名数组
				String[] stuinfo = dao.getOneRs(sql, new String[] { xsxh },
						colList);
				if (stuinfo != null) {
					for (int i = 0; i < colList.length; i++) {
						map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
					}
					shsj = null;
					String sjyear = null;
					String sjmon = null;
					String sjday = null;
					String sjhour = null;
					if (null != map.get("shsj")) {
						String sj = map.get("shsj").toString();
						sjyear = sj.substring(0, 4);
						sjmon = sj.substring(4, 6);
						sjday = sj.substring(6, 8);
						sjhour = sj.substring(8, 10);
						shsj = sjyear + "年" + sjmon + "月" + sjday + "日"
								+ sjhour + "时";
						map.put("shsj", shsj);
					}
					if (null != map.get("tjsj")) {
						String sj = map.get("tjsj").toString();
						sjyear = sj.substring(0, 4);
						sjmon = sj.substring(4, 6);
						sjday = sj.substring(6, 8);
						sjhour = sj.substring(8, 10);
						String tjsj = sjyear + "年" + sjmon + "月" + sjday + "日"
								+ sjhour + "时";
						map.put("tjsj", tjsj);
					}
					if (null != map.get("fdyshsj")) {
						String sj = map.get("fdyshsj").toString();
						sjyear = sj.substring(0, 4);
						sjmon = sj.substring(4, 6);
						sjday = sj.substring(6, 8);
						sjhour = sj.substring(8, 10);
						String tjsj = sjyear + "年" + sjmon + "月" + sjday + "日"
								+ sjhour + "时";
						map.put("fdyshsj", tjsj);
					}
				}

			}
		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			return mapping.findForward("false");
		} else if ("shenhe".equalsIgnoreCase(doType)) {
			// 查询数据
			sql = "select * from " + tableName + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from view_jygl_byqx where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			HashMap<String,String>jyqxMap=dao.getMap(" select * from jygl_byqx where "+pk+"=? ",new String[]{pkValue},new String[]{"xgyj","xslb"});
			
			if ("专科生".equals(jyqxMap.get("xslb"))
					|| "本科生".equals(jyqxMap.get("xslb"))) {
				String pyfsinfo = dao.getOneRs("select a.*,(select pyfs from dmk_bzpyfs " +
						"b where a.pyfsdm=b.pyfsdm)pyfs  from jygl_xsjbxxb a where xsxh=? ",
						new String[]{pkValue},"pyfs");
				map.put("pyfs", pyfsinfo);
			} else if ("研究生".equals(jyqxMap.get("xslb"))) {
				String pyfsinfo = dao.getOneRs("select a.*,(select pyfs from dmk_yjspyfs " +
						"b where a.pyfsdm=b.pyfsdm)pyfs  from jygl_xsjbxxb a where xsxh=? ",
						new String[]{pkValue},"pyfs");
				map.put("pyfs", pyfsinfo);
			}
		
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				String shsj = null;
				String sjyear = null;
				String sjmon = null;
				String sjday = null;
				String sjhour = null;
				if (null != map.get("shsj")) {
					String sj = map.get("shsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					shsj = sjyear + "年" + sjmon + "月" + sjday + "日" + sjhour
							+ "时";
					map.put("shsj", shsj);
				}
				if (null != map.get("tjsj")) {
					String sj = map.get("tjsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					String tjsj = sjyear + "年" + sjmon + "月" + sjday + "日"
							+ sjhour + "时";
					map.put("tjsj", tjsj);
				}
				if (null != map.get("fdyshsj")) {
					String sj = map.get("fdyshsj").toString();
					sjyear = sj.substring(0, 4);
					sjmon = sj.substring(4, 6);
					sjday = sj.substring(6, 8);
					sjhour = sj.substring(8, 10);
					String tjsj = sjyear + "年" + sjmon + "月" + sjday + "日"
							+ sjhour + "时";
					map.put("fdyshsj", tjsj);
				}
				map.putAll(jyqxMap);
			}
		}
		if (xxdm.equals(Globals.XXDM_ZZSF)) {
			request.setAttribute("rs", map);// 发送数据集
			return mapping.findForward("success2");

		}
		request.setAttribute("rs", map);// 发送数据集
		return mapping.findForward("success");
	}

	// 文件上传
	private ActionForward jyglgetfile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// String uploadPath = "C:\\upload\\"; // 用于存放上传文件的目录
		// String tempPath = "C:\\upload\\tmp\\"; // 用于存放临时文件的目录
		//
		// DiskFileUpload fu = new DiskFileUpload();
		// // 设置最大文件尺寸，这里是4MB
		// fu.setSizeMax(4194304);
		// // 设置缓冲区大小，这里是4kb
		// fu.setSizeThreshold(4096);
		// // 设置临时目录：
		// fu.setRepositoryPath(tempPath);
		//
		// // 得到所有的文件：
		// List fileItems = fu.parseRequest(request);
		// Iterator i = fileItems.iterator();
		// // 依次处理每一个文件：
		// while (i.hasNext()) {
		// FileItem fi = (FileItem) i.next();
		// // 获得文件名，这个文件名包括路径：
		// String fileName = fi.getName();
		// if (fileName != null) {
		// // 在这里可以记录用户和文件信息
		// // ...
		// // 写入文件a.txt，你也可以从fileName中提取文件名：
		// fi.write(new File(uploadPath + "a.txt"));
		// }
		// }
		//
		// DealString deal = new DealString();
		// JyglwjscDao dao = new JyglwjscDao();
		// String upFile = request.getParameter("act");
		// String upFile2 = request.getParameter("act2");
		// String dir = deal.toGBK(request.getParameter("dir"));
		// dir = dir.replace('\\', '#');
		// dir = dir.substring(dir.lastIndexOf("#") + 1, dir.length());
		// String dir2 = deal.toGBK(request.getParameter("dir2"));
		// CommanForm commanForm = (CommanForm) form;
		// if (null != (upFile) && upFile.equalsIgnoreCase("up")) {
		// FormFile file = commanForm.getFile();
		// InputStream is = null;
		// boolean res = false;
		// OutputStream ou = new FileOutputStream(new File("d:/file/file.ddd"));
		// byte[] b = new byte[is.available()];
		// is.read(b);
		// ou.write(b);
		// ou.flush();
		//
		// int pos = dir.lastIndexOf(" . ");
		// dir.substring(pos);
		//
		// try {
		// is = file.getInputStream();
		// } catch (IOException e) {
		// request.setAttribute("res", "文件读取错误，" + e.toString());
		// return mapping.findForward("success");
		// }
		//
		// if (upFile.equalsIgnoreCase("up")) {
		// String fType = file.getFileName();
		// fType = fType.substring(fType.lastIndexOf(".") + 1, fType
		// .length());
		// res = dao.getMFile("", file.getFileName(), dir, String
		// .valueOf(file.getFileSize()));
		// if (file.getFileName().equals(null)
		// || file.getFileName().equals("")) {
		// request.setAttribute("res", "que");
		// } else if (res) {
		// request.setAttribute("res", "ok");
		// } else {
		// request.setAttribute("res", "no");
		// }
		// }
		// }
		//
		// if (null != (upFile2) && upFile2.equalsIgnoreCase("up")) {
		// FormFile file2 = commanForm.getFile2();
		// InputStream is = null;
		// boolean res = false;
		// try {
		// is = file2.getInputStream();
		// } catch (IOException e) {
		// request.setAttribute("res", "文件读取错误，" + e.toString());
		// return mapping.findForward("success");
		// }
		//
		// if (upFile2.equalsIgnoreCase("up")) {
		// String fType = file2.getFileName();
		// fType = fType.substring(fType.lastIndexOf(".") + 1, fType
		// .length());
		// res = dao.getMFile2("", file2.getFileName(), dir2, String
		// .valueOf(file2.getFileSize()));
		// if (file2.getFileName().equals(null)
		// || file2.getFileName().equals("")) {
		// request.setAttribute("res", "que");
		// } else if (res) {
		// request.setAttribute("res", "ok");
		// } else {
		// request.setAttribute("res", "no");
		// }
		// }
		// }
		// response.reset();
		// List fjList = dao.getMyFile();
		// List fjList2 = dao.getMyFile2();
		// request.setAttribute("fjList", fjList);
		// request.setAttribute("fjList2", fjList2);

		// 重新写
		// DealString deal = new DealString();
		// DAO dao = DAO.getInstance();
		//		
		// String path = "files"; //文件目标目录
		// String tempPath ="C:\\TEMP";
		// String sql="insert into jswjsc
		// (id,name,filesize,path,recordid,fjtype,addtime) values
		// (S_ACCESSORY_ID.NEXTVAL,?,?,?,?,?,to_char(sysdate,'YYYY-MM-DD/HH24:MI:ss'))";
		// HttpServletRequest fileuploadRequest = null; //获取上传请求
		// FileTool fileTool = new FileTool();
		// fileTool.mkdirs(tempPath);
		// CommanForm commanForm = (CommanForm) form;
		// FormFile file = commanForm.getFile();
		// String dir = deal.toGBK(request.getParameter("dir"));//获得源文件路径
		//        
		//        
		//        
		// Iterator iter = null; //叠代
		// DiskFileUpload fu = new DiskFileUpload();
		// List fileItems = fu.parseRequest(fileuploadRequest);
		// iter = fileItems.iterator();
		//        
		// fu.setSizeMax(new Long("104857600")); //文件大小不超过10M
		//		
		// FileItem item = (FileItem)iter.next();
		//        
		// String fileName = file.getFileName();
		// String filename1=fileName.substring(0, fileName.lastIndexOf("."));
		// //.前面的文件名
		// String filetype =fileName.substring(fileName.lastIndexOf(".")+1,
		// fileName.length()); //文件类型
		// java.util.Date dt=new java.util.Date(); //上传日期
		// long lg=dt.getTime();
		// Long ld=new Long(lg);
		// String filename2=ld.toString(); //把上传日期作为文件名的一部分
		// //以上可以看出文件名有三部分组成 文件名 系统时间 文件类型
		// String wjdx = String.valueOf(file.getFileSize());
		// //将获得的int型文件大小转化成string型
		// item.write(new File(tempPath +"\\" + filename2+"."+filetype));
		// dao.runUpdate(sql, new String[]{filename1,wjdx,path+"/"+
		// filename2+"."+filetype,filetype});
		// //文件名 文件大小 路径 记录号 类型

		return mapping.findForward("success");
	}

	// 文件下载
	// private ActionForward jygldownfile(ActionMapping mapping, ActionForm
	// form,
	// HttpServletRequest request, HttpServletResponse response)
	// throws Exception {
	// String val = request.getParameter("val");
	//
	// HashMap<String, String> map = new HashMap<String, String>();
	// map.put("xls", "application/vnd.ms-excel");
	// map.put("doc", "application/msword");
	// map.put("txt", "text/plain");
	// if (act.equalsIgnoreCase("upInfo.do")) {
	// String wjm = dao.getDFile(val);
	// String cType = "";
	// if (Base.isNull(wjm)) {
	// cType = "unknown";
	// } else {
	// cType = map.get(wjm);
	// }
	// BLOB blob = dao.getDFile1(val);
	// response.reset();
	// response.setContentType(cType);
	// OutputStream os = response.getOutputStream();
	// os.write(blob.getBytes(1L, (int) blob.length()));
	// }
	// return mapping.findForward("success");
	// }

	// 文件删除
	private ActionForward jygldelfile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JyglwjscDao dao = new JyglwjscDao();
		String act = request.getParameter("act");

		boolean judge = false;
		if (act.equals("del")) {
			String wjname = DealString.toGBK(request.getParameter("wjname"));
			judge = StandardOperation
					.delete("scwjb", "wjname", wjname, request);
			if (judge) {
				request.setAttribute("act", "ok");
			} else {
				request.setAttribute("act", "no");
			}
		} else if (act.equals("del2")) {
			String wjname = DealString.toGBK(request.getParameter("wjname"));
			judge = StandardOperation.delete("scwjb2", "wjname", wjname,
					request);
			if (judge) {
				request.setAttribute("act", "ok");
			} else {
				request.setAttribute("act", "no");
			}
		}

		List fjList = dao.getMyFile();
		List fjList2 = dao.getMyFile2();
		request.setAttribute("fjList", fjList);
		request.setAttribute("fjList2", fjList2);
		return mapping.findForward("success");
	}

	// 教师文件下载
	private ActionForward jyglteafiledown(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JyglwjscDao dao = new JyglwjscDao();

		List fjList = dao.getMyFile();
		request.setAttribute("fjList", fjList);
		String act = request.getParameter("act");
		if ("down".equals(act)) {
			String dir = DealString.toGBK(request.getParameter("dir"));
			String filename = request.getParameter("filename");
			FileTool fileTool = new FileTool();
			fileTool.downFile(response, dir, filename);
		}
		return mapping.findForward("success");
	}

	// 学生文件下载
	private ActionForward jyglstufiledown(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JyglwjscDao dao = new JyglwjscDao();

		List fjList2 = dao.getMyFile2();
		request.setAttribute("fjList2", fjList2);
		String act = request.getParameter("act");
		if ("down".equals(act)) {
			String dir = DealString.toGBK(request.getParameter("dir"));
			String filename = DealString
					.toGBK(request.getParameter("filename"));
			FileTool fileTool = new FileTool();
			fileTool.downFile(response, dir, filename);
		}
		return mapping.findForward("success");
	}

	// 政策文件发布的页面显示
	private ActionForward showzcwjlist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {

		String tableName = "jygl_zcwjb";// 数据源表-政策文件表
		String[] colList = null;// 输入字段
		String[] colListCN = null; // 对应中文字段名
		// 查询具体数据的sql语句
		SxjyDAO dao = new SxjyDAO();
		HashMap<String, String> map = new HashMap<String, String>();
		String delone = DealString.toGBK(request.getParameter("delone"));
		delone = delone.replaceAll(":", "").replaceAll("-", "").replaceAll(" ",
				"");
		HttpSession session = request.getSession();
		String wjbt = DealString.toGBK(request.getParameter("wjbt")); // 文件标题
		String wjnr = DealString.toGBK(request.getParameter("wjnr")); // 文件内容
		String wjlx = DealString.toGBK(request.getParameter("wjlx")); // 文件类型
		String fbr = (String) session.getAttribute("userName"); // 发布人
		String act = request.getParameter("act");
		String userName = (String) session.getAttribute("userName");

		String fbsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 发布时间
				// //
				// 取至数据库临时表
				new String[] {}, new String[] { "sdate" }))[0];
		if ("save".equals(act)) {
			if (!"".equals(wjlx) && null != wjlx) {
				String sql = "insert into jygl_zcwjb(wjbt,wjlx,fbr,fbsj) values('"
						+ wjbt
						+ "','"
						+ wjlx
						+ "','"
						+ fbr
						+ "','"
						+ fbsj
						+ "')";
				// sqlclob = "update jygl_zcwjb set wjnr=?";
				boolean judge = false;
				judge = StandardOperation.insert(tableName, new String[] {
						"wjbt", "wjlx", "wjnr", "fbr", "fbsj" }, new String[] {
						wjbt, wjlx, wjnr, fbr, fbsj }, request);
				if (judge) {
					request.setAttribute("inserted", "ok");

					// 添加操作的记录
					if ("teacher".equals(userType)) {
						String whensj = (dao
								.getOneRs(
										"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
										new String[] {},
										new String[] { "sdate" }))[0]; // 提交时间

						StandardOperation.insert("JYGL_USERCZMXB",
								new String[] { "userid", "dowhat",
										"whichtable", "whichpk", "whensj" },
								new String[] { userName, "增加", "政策文件表",
										"文件标题:" + wjbt, whensj }, request);
					}

				} else {
					request.setAttribute("inserted", "no");
				}
			} else {
				request.setAttribute("inserted", "que");
			}
		}
		if ("del".equals(act)) {
			boolean judge = false;
			judge = StandardOperation
					.delete(tableName, "fbsj", delone, request);
			if (judge) {
				request.setAttribute("del", "ok");

				// 删除操作的记录

				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

				judge = StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"删除", "政策文件表", "发布时间:" + delone, whensj },
						request);
			} else {
				request.setAttribute("del", "no");
			}
		}

		List<HashMap<String, String>> wjlxList = dao.getList(
				"select * from dmk_zcwjlxb", new String[] {}, new String[] {
						"lxdm", "lxmc" });

		StringBuilder sql = new StringBuilder();
		sql
				.append("select a.wjbt,(select lxmc from dmk_zcwjlxb where lxdm=a.wjlx) lxmc,wjlx,a.fbr,a.readtimes,");
		sql
				.append("to_char(to_date(fbsj,'yyyymmddhh24miss'),'yyyy-mm-dd hh24:mi:ss') fbsj from jygl_zcwjb a ");
		sql
				.append("where exists (select 1 from dmk_zcwjlxb where lxdm=a.wjlx) order by lxmc,fbsj desc");

		colList = new String[] { "wjbt", "wjlx", "lxmc", "fbr", "readtimes",
				"fbsj" };
		colListCN = new String[] { "文件标题", "文件类型", "发布人", "浏览次数", "发布时间" };
		List topTr = dao.arrayToList(colList, colListCN);
		List<HashMap<String, String>> rs = dao.getList(sql.toString(),
				new String[] {}, colList);

		int m = 0;
		List<HashMap<String, String>> temp = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < rs.size(); i++) {
			String currLxmc = rs.get(i).get("wjlx");
			String nextLxmc = i == rs.size() - 1 ? "" : rs.get(i + 1).get(
					"wjlx");

			if (currLxmc.equals(nextLxmc)) {
				m++;
			} else {
				m = 0;
			}
			if (m > 2) {
				temp.add(rs.get(i + 1));
			}
		}

		rs.removeAll(temp);

		request.setAttribute("wjlxList", wjlxList);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);

		/*
		 * // 国家级文件版块 sql = "select " + pk + " 主键,rownum 行号,a.* from " +
		 * tableName + " a " + "where wjlx='国家级文件'"; colList = new String[] {
		 * "主键", "行号", "wjbt", "wjlx", "fbr", "readtimes", "fbsj" }; colListCN =
		 * dao.getColumnNameCN(colList, tableName); List topTr1 =
		 * dao.arrayToList(colList, colListCN); rs1.addAll(dao.rsToVator(sql,
		 * new String[] {}, colList));
		 *  // 市级文件版块 sql = "select " + pk + " 主键,rownum 行号,a.* from " +
		 * tableName + " a " + "where wjlx='市级文件'"; colList = new String[] {
		 * "主键", "行号", "wjbt", "wjlx", "fbr", "readtimes", "fbsj" }; colListCN =
		 * dao.getColumnNameCN(colList, tableName); List topTr2 =
		 * dao.arrayToList(colList, colListCN); rs2.addAll(dao.rsToVator(sql,
		 * new String[] {}, colList));
		 *  // 学级文件版块 sql = "select " + pk + " 主键,rownum 行号,a.* from " +
		 * tableName + " a " + "where wjlx='学生工作处文件' or wjlx='毕业生就业指导中心文件'" + "
		 * or wjlx='其他校内文件' or wjlx='校内文件'"; colList = new String[] { "主键",
		 * "行号", "wjbt", "wjlx", "fbr", "readtimes", "fbsj" }; colListCN =
		 * dao.getColumnNameCN(colList, tableName); List topTr3 =
		 * dao.arrayToList(colList, colListCN); rs3.addAll(dao.rsToVator(sql,
		 * new String[] {}, colList));
		 *  // 公告栏 sql = "select " + pk + " 主键,rownum 行号,a.* from " + tableName + "
		 * a " + "where wjlx='公告栏'"; colList = new String[] { "主键", "行号",
		 * "wjbt", "wjlx", "fbr", "readtimes", "fbsj" }; colListCN =
		 * dao.getColumnNameCN(colList, tableName); List topTr4 =
		 * dao.arrayToList(colList, colListCN); rs4.addAll(dao.rsToVator(sql,
		 * new String[] {}, colList));
		 *  // 校园专场招聘会 sql = "select " + pk + " 主键,rownum 行号,a.* from " +
		 * tableName + " a " + "where wjlx='校园专场招聘会'"; colList = new String[] {
		 * "主键", "行号", "wjbt", "wjlx", "fbr", "readtimes", "fbsj" }; colListCN =
		 * dao.getColumnNameCN(colList, tableName); List topTr5 =
		 * dao.arrayToList(colList, colListCN); rs5.addAll(dao.rsToVator(sql,
		 * new String[] {}, colList));
		 *  // 生源介绍 sql = "select " + pk + " 主键,rownum 行号,a.* from " + tableName + "
		 * a " + "where wjlx='生源介绍'"; colList = new String[] { "主键", "行号",
		 * "wjbt", "wjlx", "fbr", "readtimes", "fbsj" }; colListCN =
		 * dao.getColumnNameCN(colList, tableName); List topTr6 =
		 * dao.arrayToList(colList, colListCN); rs6.addAll(dao.rsToVator(sql,
		 * new String[] {}, colList));
		 * 
		 * request.setAttribute("topTr1", topTr1);// 发送表头(国家)
		 * request.setAttribute("topTr2", topTr2);// 发送表头(市)
		 * request.setAttribute("topTr3", topTr3);// 发送表头(学校)
		 * request.setAttribute("topTr4", topTr4);// 发送表头(公告栏)
		 * request.setAttribute("topTr5", topTr5);// 发送表头(校园专场招聘会)
		 * request.setAttribute("topTr6", topTr6);// 发送表头(生源介绍) //
		 * request.setAttribute("rs1", rs1);// 发送数据集(国家)
		 * request.setAttribute("rs2", rs2);// 发送数据集(市)
		 * request.setAttribute("rs3", rs3);// 发送数据集(学校)
		 * request.setAttribute("rs4", rs4);// 发送数据集(公告栏)
		 * request.setAttribute("rs5", rs5);// 发送数据集(校园专场招聘会)
		 * request.setAttribute("rs6", rs6);// 发送数据集(生源介绍)
		 */
		request.setAttribute("map", map);
		request.setAttribute("path", "zcwjfb.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("success");
	}

	// 政策文件的详细信息
	private ActionForward showmorezcwj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();

		String sql = "";
		String[] tit = { "wjbt", "fbr", "fbsj" };
		String tableName = "JYGL_ZCWJB";
		String wjbt = DealString.toGBK(request.getParameter("wjbt"));
		HashMap<String, String> map = new HashMap<String, String>();

		sql = "select * from " + tableName + " where wjbt=?";
		String[] zcwj = dao.getOneRs(sql, new String[] { wjbt }, tit);
		if (zcwj != null) {
			for (int i = 0; i < tit.length; i++) {
				map.put(tit[i].toLowerCase(), zcwj[i]); // 将记录循环放入map中
			}
		}
		CLOB clob = dao.getOneClob(sql, new String[] { wjbt }, "wjnr");
		if (null != clob) {
			map.put("wjnr", clob.getSubString(1L, (int) clob.length()));
		}
		sql = "select readtimes from " + tableName + " where wjbt=?";
		String[] readtimes = dao.getOneRs(sql, new String[] { wjbt },
				new String[] { "readtimes" });
		if (null != readtimes) {
			int timeint = Integer.parseInt(readtimes[0]);
			timeint += 1;
			String tinestr = String.valueOf(timeint);
			StandardOperation.update(tableName, new String[] { "readtimes" },
					new String[] { tinestr }, "wjbt", wjbt, request);
		}

		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 政策文件修改
	private ActionForward zcwjupdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String tableName = "jygl_zcwjb";
		String updateone = DealString.toGBK(request.getParameter("updateone"));
		updateone = updateone.replaceAll(":", "").replaceAll("-", "")
				.replaceAll(" ", "");
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();
		String act = request.getParameter("act");

		sql = "select * from jygl_zcwjb where fbsj=?";
		String[] colList = dao
				.getColumnName("select * from jygl_zcwjb where 1=2"); // 返回列名数组
		String[] zcwjinfo = dao.getOneRs(sql, new String[] { updateone },
				colList);
		if (zcwjinfo != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), zcwjinfo[i]); // 将记录循环放入map中
			}
		}
		request.setAttribute("rs", map);

		if ("update".equals(act)) {
			String wjbt = DealString.toGBK(request.getParameter("wjbt"));
			String wjlx = DealString.toGBK(request.getParameter("wjlx"));
			String wjnr = DealString.toGBK(request.getParameter("wjnr"));
			boolean judge = false;
			judge = StandardOperation.update(tableName, new String[] { "wjlx",
					"wjnr" }, new String[] { wjlx, wjnr }, "wjbt", wjbt,
					request);
			if (judge) {
				request.setAttribute("update", "ok");

				// 修改操作的记录
				HttpSession session = request.getSession();
				String userName = session.getAttribute("userName").toString();
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"修改", "政策文件表", "文件标题:" + wjbt, whensj },
						request);
			} else {
				request.setAttribute("update", "no");
			}

			sql = "select * from jygl_zcwjb where wjbt=?";
			String[] colList1 = dao
					.getColumnName("select * from jygl_zcwjb where 1=2"); // 返回列名数组
			String[] zcwjinfo1 = dao.getOneRs(sql, new String[] { wjbt },
					colList1);
			if (zcwjinfo1 != null) {
				for (int i = 0; i < colList1.length; i++) {
					map.put(colList1[i].toLowerCase(), zcwjinfo1[i]); // 将记录循环放入map中
				}
			}

			request.setAttribute("rs", map);

		}
		List<HashMap<String, String>> wjlxList = dao.getList(
				"select * from dmk_zcwjlxb", new String[] {}, new String[] {
						"lxdm", "lxmc" });

		request.setAttribute("wjlxList", wjlxList);
		return mapping.findForward("success");
	}

	// 政策文件查询
	private ActionForward zcwjquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws SQLException {

		DAO dao = DAO.getInstance();
		CommanForm myForm = (CommanForm) form;
		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";
		String pk = "wjbt"; // 主键
		String tableName = "jygl_zcwjb";
		String rsNum = "0"; // 返回的记录数
		String wjbt = DealString.toGBK(request.getParameter("wjbt")); // 文件标题
		String wjlx = DealString.toGBK(request.getParameter("wjlx")); // 文件类型
		String fbr = request.getParameter("fbr"); // 发布人

		// 关于发布时间的处理 用于查询条件
		String xjsj = DealString.toGBK(request.getParameter("xjsj")); // 相距时间

		String nowsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 发布时间
				// //
				// 取至数据库临时表
				new String[] {}, new String[] { "sdate" }))[0];
		String sjyear = nowsj.substring(0, 4);
		String sjmonth = nowsj.substring(4, 6);
		String sjday = nowsj.substring(6, 8);
		String sjqt = nowsj.substring(8, 14);
		String nowsj1 = sjyear + "-" + sjmonth + "-" + sjday;
		dealDate dealdate = new dealDate();
		String beforesj = "";
		// Integer.valueOf(xjsj)
		if (!"".equals(xjsj)) {
			beforesj = dealdate.getDate2(Integer.parseInt(xjsj), nowsj1);
			beforesj = beforesj.replaceAll("-", "") + sjqt;
		}

		String querry = " where 1=1 ";
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");

		if ("query".equals(doType)) {
			map.put("wjbt", wjbt);
			map.put("wjlx", wjlx);
			map.put("fbr", fbr);
			map.put("xjsj", xjsj);
		}

		if (wjbt == null) {
			wjbt = "";
		}
		if (wjlx == null) {
			wjlx = "";
		}
		if (fbr == null) {
			fbr = "";
		}
		if (xjsj == null) {
			xjsj = "";
		}
		if ((wjbt == null) || wjbt.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and wjbt like '%" + wjbt + "%' ";
		}
		if ((wjlx == null) || wjlx.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and wjlx = '" + wjlx + "' ";
		}
		if ((fbr == null) || fbr.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and fbr = '" + fbr + "' ";
		}
		if ((xjsj == null) || xjsj.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and (fbsj between '" + beforesj + "' and '" + nowsj
					+ "') ";
		}

		Pages page = myForm.getPages();
		page.setMaxRecord(Integer.parseInt(dao.getOneRs(
				"select count(*) count from jygl_zcwjb " + querry,
				new String[] {}, "count")));

		StringBuilder sb = new StringBuilder();

		sb.append("select * from (select wjbt 主键,rownum r,a.*,");
		sb.append("(select lxmc from dmk_zcwjlxb where lxdm=a.wjlx) lxmc ");
		sb.append("from jygl_zcwjb a ");
		sb.append(querry);
		sb.append(") where r > ");
		sb.append(page.getStart());
		sb.append(" and r <= ");
		sb.append((page.getStart() + page.getPageSize()));
		sql = sb.toString();
		colList = new String[] { "主键", "r", "wjbt", "lxmc", "fbr", "fbsj",
				"readtimes" };
		colListCN = new String[] { "主键", "行号", "文件标题", "文件类型", "发布人", "发布时间",
				"浏览次数" };
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("rs1", map);// 把查询条件发回去

		// String sql = "";
		// String[] tit = { "wjbt", "fbr", "fbsj" };
		//
		// String wjbt = deal.toGBK(request.getParameter("wjbt"));
		// HashMap<String, String> map = new HashMap<String, String>();
		//
		// sql = "select * from jygl_zcwjb" + " where wjbt=?";
		// String[] zcwj = dao.getOneRs(sql, new String[] { wjbt }, tit);
		// if (zcwj != null) {
		// for (int i = 0; i < tit.length; i++) {
		// map.put(tit[i].toLowerCase(), zcwj[i]); // 将记录循环放入map中
		// }
		// }
		// CLOB clob = dao.getOneClob(sql, new String[] { wjbt }, "wjnr");
		// if (null != clob) {
		// map.put("wjnr", clob.getSubString(1L, (int) clob.length()));
		// }
		// request.setAttribute("rs", map);
		List<HashMap<String, String>> wjlxList = dao.getList(
				"select * from dmk_zcwjlxb", new String[] {}, new String[] {
						"lxdm", "lxmc" });

		request.setAttribute("wjlxList", wjlxList);

		return mapping.findForward("success");
	}

	// 数据导出
	private ActionForward jyglexpData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = request.getParameter("tableName");
		String sql = "";
		String zd = "";
		String querry = " where 1=1";
		DAO dao = DAO.getInstance();
		String xxdm = dao.getXxdm();
		if ("dwlfxxdjb".equals(tableName)) {
			if (xxdm.equals(Globals.XXDM_ZGDZDX)) {
				zd = " * ";
			}
		}
		if ("jygl_xsjbxxb".equals(tableName)) {
			if (xxdm.equals(Globals.XXDM_YNYS)) {
				zd = " * ";
				String xsxh = request.getParameter("xsxh"); // 接收学号
				String name = DealString.toGBK(request.getParameter("name")); // 接收姓名
				String xb = request.getParameter("xb"); // 接收性别代码
				String xymc = DealString.toGBK(request.getParameter("xymc")); // 接收学院名称
				String nd = request.getParameter("nd"); // 接收年度
				String bynd = request.getParameter("bynd"); // 毕业年度

				if (xsxh == null) {
					xsxh = "";
				}
				if (name == null) {
					name = "";
				}
				if (xb == null) {
					xb = "";
				}
				if (xymc == null) {
					xymc = "";
				}
				if (nd == null) {
					nd = "";
				}
				if (bynd == null) {
					bynd = "";
				}
				if ((xsxh == null) || xsxh.equals("")) {
					querry += "and 1=1 ";
				} else {
					querry += "and xsxh like '%" + xsxh + "%' ";
				}
				if ((name == null) || name.equals("")) {
					querry += "and 1=1 ";
				} else {
					querry += "and name like '%" + name + "%' ";
				}
				if ((xb == null) || xb.equals("")) {
					querry += " and 1=1 ";
				} else {
					querry += " and xb='" + xb + "' ";
				}
				if ((xymc == null) || xymc.equals("")) {
					querry += " and 1=1 ";
				} else {
					querry += " and xsxh in (select xsxh from jygl_xsjbxxb where xsxh in (select xh from view_xsjbxx where xymc ='"
							+ xymc + "'))";
				}
				if ((nd == null) || nd.equals("")) {
					querry += " and 1=1 ";
				} else {
					querry += " and nd='" + nd + "' ";
				}
				if ((bynd == null) || bynd.equals("")) {
					querry += " and 1=1 ";
				} else {
					querry += " and bynd='" + bynd + "' ";
				}
			}

			zd = " * ";
			String xsxh = request.getParameter("xsxh"); // 接收学号
			String name = DealString.toGBK(request.getParameter("name")); // 接收姓名
			String xbdm = request.getParameter("xbdm"); // 接收性别代码
			String xslb = DealString.toGBK(request.getParameter("xslb")); // 接收学生类别
			String xymc = DealString.toGBK(request.getParameter("xymc")); // 接收学院名称
			String nd = request.getParameter("nd"); // 接收年度
			String bynd = request.getParameter("bynd"); // 毕业年度
			String sfsh = request.getParameter("sfsh");
			if (xsxh == null) {
				xsxh = "";
			}
			if (name == null) {
				name = "";
			}
			if (xbdm == null) {
				xbdm = "";
			}
			if (xslb == null) {
				xslb = "";
			}
			if (xymc == null) {
				xymc = "";
			}
			if (nd == null) {
				nd = "";
			}
			if (bynd == null) {
				bynd = "";
			}

			if ((xsxh == null) || xsxh.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xsxh like '%" + xsxh + "%' ";
			}
			if ((name == null) || name.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and name like '%" + name + "%' ";
			}
			if ((xbdm == null) || xbdm.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xbdm='" + xbdm + "' ";
			}
			if ((xslb == null) || xslb.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xslb='" + xslb + "' ";
			}
			if ((xymc == null) || xymc.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xsxh in (select xsxh from jygl_xsjbxxb where xsxh in (select xh from view_xsjbxx where xymc ='"
						+ xymc + "'))";
			}
			if ((nd == null) || nd.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and nd='" + nd + "' ";
			}
			if ((bynd == null) || bynd.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and bynd='" + bynd + "' ";
			}
			if (sfsh == null || "".equals(sfsh)) {
				querry += " and 1=1 ";
			} else {
				if (sfsh == "1" || "1".equals(sfsh)) {
					sfsh = "未审核";
				} else if (sfsh == "2" || "2".equals(sfsh)) {
					sfsh = "已通过√";
				} else if (sfsh == "3" || "3".equals(sfsh)) {
					sfsh = "未通过X";
				}
				querry += "and sfsh='" + sfsh + "'";
			}
		}
		if ("jygl_jyxy".equals(tableName)) {
			zd = " * ";

			String xsxh = request.getParameter("xsxh"); // 接收学号
			String name = DealString.toGBK(request.getParameter("name")); // 接收姓名
			String xbdm = request.getParameter("xbdm"); // 接收性别代码
			String xslb = DealString.toGBK(request.getParameter("xslb")); // 接收学生类别
			String xymc = DealString.toGBK(request.getParameter("xymc")); // 接收学院名称
			String bynd = request.getParameter("bynd"); // 毕业年度
			String nd = request.getParameter("nd"); // 入学年度
			String wjybz = request.getParameter("wjybz"); // 未就业标志
			String fdysh = DealString.toGBK(request.getParameter("fdysh")); // 辅导员审核
			String xxsh = DealString.toGBK(request.getParameter("xxsh")); // 学校审核

			if (xsxh == null) {
				xsxh = "";
			}
			if (name == null) {
				name = "";
			}
			if (xbdm == null) {
				xbdm = "";
			}
			if (xslb == null) {
				xslb = "";
			}
			if (xymc == null) {
				xymc = "";
			}
			if (bynd == null) {
				bynd = "";
			}
			if (wjybz == null) {
				wjybz = "";
			}
			if (fdysh == null) {
				fdysh = "";
			}
			if (xxsh == null) {
				xxsh = "";
			}
			if (nd == null) {
				nd = "";
			}

			if ((xsxh == null) || xsxh.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xsxh like '%" + xsxh + "%' ";
			}
			if ((name == null) || name.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and name like '%" + name + "%' ";
			}
			if ((xbdm == null) || xbdm.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xbdm='" + xbdm + "' ";
			}
			if ((xslb == null) || xslb.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xslb='" + xslb + "' ";
			}
			if ((xymc == null) || xymc.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xymc='" + xymc + "' ";
			}
			if ((bynd == null) || bynd.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and bynd='" + bynd + "' ";
			}
			if ((wjybz == null) || wjybz.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and wjybz='" + wjybz + "' ";
			}
			if ((fdysh == null) || fdysh.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and fdysh='" + fdysh + "' ";
			}
			if ((xxsh == null) || xxsh.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xxsh='" + xxsh + "' ";
			}
			if ((nd == null) || nd.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and nd='" + nd + "' ";
			}
		}

		if ("jygl_byqx".equals(tableName)) {
			zd = " * ";

			String xsxh = request.getParameter("xsxh"); // 接收学号
			String name = DealString.toGBK(request.getParameter("name")); // 接收姓名
			String xb = request.getParameter("xb"); // 接收性别
			String xslb = DealString.toGBK(request.getParameter("xslb")); // 接收学生类别
			String xymc = DealString.toGBK(request.getParameter("xymc")); // 接收学院名称
			String bynd = request.getParameter("bynd"); // 毕业年度
			String xxsh = DealString.toGBK(request.getParameter("xxsh")); // 学校审核

			if (xsxh == null) {
				xsxh = "";
			}
			if (name == null) {
				name = "";
			}
			if (xb == null) {
				xb = "";
			}
			if (xslb == null) {
				xslb = "";
			}
			if (xymc == null) {
				xymc = "";
			}
			if (bynd == null) {
				bynd = "";
			}
			if (xxsh == null) {
				xxsh = "";
			}

			if ((xsxh == null) || xsxh.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xsxh like '%" + xsxh + "%' ";
			}
			if ((name == null) || name.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and name like '%" + name + "%' ";
			}
			if ((xb == null) || xb.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xb='" + xb + "' ";
			}
			if ((xslb == null) || xslb.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xslb='" + xslb + "' ";
			}
			if ((xymc == null) || xymc.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xymc='" + xymc + "' ";
			}
			if ((bynd == null) || bynd.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and bynd='" + bynd + "' ";
			}
			if ((xxsh == null) || xxsh.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and xxsh='" + xxsh + "' ";
			}
		}

		if ("jygl_userczmxb".equals(tableName)) {
			zd = " * ";
			String userid = request.getParameter("userid");
			String dowhat = DealString.toGBK(request.getParameter("dowhat"));
			String whichtable = DealString.toGBK(request
					.getParameter("whichtable"));

			// 关于发布时间的处理 用于查询条件
			String xjsj = DealString.toGBK(request.getParameter("xjsj")); // 相距时间

			String nowsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 发布时间
							// //
							// 取至数据库临时表
							new String[] {}, new String[] { "sdate" }))[0];
			String sjyear = nowsj.substring(0, 4);
			String sjmonth = nowsj.substring(4, 6);
			String sjday = nowsj.substring(6, 8);
			String sjqt = nowsj.substring(8, 14);
			String nowsj1 = sjyear + "-" + sjmonth + "-" + sjday;
			dealDate dealdate = new dealDate();
			String beforesj = "";
			if (!"".equals(xjsj)) {
				beforesj = dealdate.getDate2(Integer.parseInt(xjsj), nowsj1);
				beforesj = beforesj.replaceAll("-", "") + sjqt;
			}

			if (userid == null) {
				userid = "";
			}
			if (dowhat == null) {
				dowhat = "";
			}
			if (whichtable == null) {
				whichtable = "";
			}
			if (xjsj == null) {
				xjsj = "";
			}
			if ((userid == null) || userid.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and userid like '%" + userid + "%' ";
			}
			if ((dowhat == null) || dowhat.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and dowhat = '" + dowhat + "' ";
			}
			if ((whichtable == null) || whichtable.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += " and whichtable='" + whichtable + "' ";
			}
			if ((xjsj == null) || xjsj.equals("")) {
				querry += " and 1=1 ";
			} else {
				querry += "and (whensj between '" + beforesj + "' and '"
						+ nowsj + "') ";
			}
		}

		if ("view_jygl_jyxyslqdjb".equalsIgnoreCase(tableName)) {
			zd = " xh,xb,xm,zymc,jyxybh,remark  ";
			// querry += request.getParameter("querry");
			if (!Base.isNull(request.getParameter("xh"))) {
				querry += " and xh='" + request.getParameter("xh") + "' ";
			}
			if (!Base.isNull(request.getParameter("xm"))) {
				querry += " and xm='"
						+ DealString.toGBK(request.getParameter("xm")) + "' ";
			}
			if (!Base.isNull(request.getParameter("jyxybh"))) {
				querry += " and jyxybh='"
						+ DealString.toGBK(request.getParameter("jyxybh"))
						+ "' ";
			}
			if (!Base.isNull(request.getParameter("nj"))) {
				querry += " and nj='"
						+ DealString.toGBK(request.getParameter("nj")) + "' ";
			}
			if (!Base.isNull(request.getParameter("lqqk"))) {
				querry += " and lqqk='"
						+ DealString.toGBK(request.getParameter("lqqk")) + "' ";
			}
			if (!Base.isNull(request.getParameter("xydm"))) {
				querry += " and xydm='"
						+ DealString.toGBK(request.getParameter("xydm")) + "' ";
			}
			if (!Base.isNull(request.getParameter("zydm"))) {
				querry += " and zydm='"
						+ DealString.toGBK(request.getParameter("zydm")) + "' ";
			}
			if (!Base.isNull(request.getParameter("bjdm"))) {
				querry += " and bjdm='"
						+ DealString.toGBK(request.getParameter("bjdm")) + "' ";
			}
		}

		if ("view_jygl_xsjyqkb".equals(tableName)) {
			zd = " * ";
			String query = DealString.toGBK(request.getParameter("querry"));
			querry += query;
			sql = "select " + zd + " from " + tableName + querry;
		} else {

			sql = "select " + zd + " from " + tableName + querry;
		}

		if ("dwxxb".equals(tableName)) {
			zd = " * ";
			String query = DealString.toGBK(request.getParameter("querry"));
			querry += query;
			sql = "select " + zd + " from " + tableName + querry;
		}
		if ("bjjyqktjb".equalsIgnoreCase(tableName)) {

			String query = DealString.toGBK(request.getParameter("querry"));
			sql = "select rownum 序号, a.xymc 学院名称,a.xydm 学院代码,a.zydm 专业代码,a.bjdm 班级代码, a.bjmc 班级名称 ,a.bjrs 班级人数 ,'' 班主任,d.lsdwrs 落实单位人数 ,b.qyrs 签约人数 ,(select to_char(c.jyrs/a.bjrs*100,'999.99')||'%' from dual) 就业率,(select to_char(b.qyrs/a.bjrs*100,'999.99')||'%' from dual) 签约率 from "
					+ "(select xymc,xydm,zydm,bjdm,bjmc,count(*) bjrs from view_xsjbxx group by bjdm,bjmc,xymc,xydm,zydm) a "
					+ "left join "
					+ "(select bjdm,count(*) qyrs from view_jygl_xsjyqkb where sfqy='是' group by bjdm) b "
					+ "on a.bjdm=b.bjdm "
					+ "left join "
					+ "(select bjdm,count(*) jyrs from view_jygl_xsjyqkb where sfjy='是' group by bjdm) c "
					+ "on a.bjdm=c.bjdm "
					+ "left join "
					+ "(select bjdm,count(*) lsdwrs from view_jygl_xsjyqkb where dwzm is not null group by bjdm) d "
					+ "on a.bjdm=d.bjdm " + query + "order by 学院名称";

		}

		if ("zyjyqktjb".equalsIgnoreCase(tableName)) {

			String query = DealString.toGBK(request.getParameter("querry"));
			sql = "select rownum 序号, a.xymc 学院名称,a.xydm 学院代码,a.zymc 专业名称,a.zydm 专业代码,a.zyrs 专业人数,d.lsdwrs 落实单位人数,b.qyrs 签约人数,replace((select to_char(c.jyrs/a.zyrs*100,'999.99')||'%' from dual),'    ','0') 就业率,replace((select to_char(b.qyrs/a.zyrs*100,'999.99')||'%' from dual),'    ','0') 签约率 from "
					+ "(select xymc,xydm,zydm,zymc,count(*) zyrs from view_xsjbxx group by zydm,zymc,xymc,xydm) a "
					+ "left join "
					+ "(select zydm,count(*) qyrs from view_jygl_xsjyqkb where sfqy='是' group by zydm) b "
					+ "on a.zydm=b.zydm "
					+ "left join "
					+ "(select zydm,count(*) jyrs from view_jygl_xsjyqkb where sfjy='是' group by zydm) c "
					+ "on a.zydm=c.zydm "
					+ "left join "
					+ "(select zydm,count(*) lsdwrs from view_jygl_xsjyqkb where dwzm='有' group by zydm) d "
					+ "on a.zydm=d.zydm " + query + " order by 学院名称";

		}

		if ("fyjyqktjb".equalsIgnoreCase(tableName)) {

			String query = DealString.toGBK(request.getParameter("querry"));
			sql = "select rownum 序号, a.xymc 学院名称,a.xydm 学院代码,a.xyrs 学院人数,d.lsdwrs 落实单位人数,b.qyrs 签约人数,replace((select to_char(c.jyrs/a.xyrs*100,'999.99')||'%' from dual),'    ','0') 就业率,replace((select to_char(b.qyrs/a.xyrs*100,'999.99')||'%' from dual),'    ','0') 签约率 from "
					+ "(select xymc,xydm,count(*) xyrs from view_xsjbxx group by xymc,xydm) a "
					+ "left join "
					+ "(select xydm,count(*) qyrs from view_jygl_xsjyqkb where sfqy='是' group by xydm) b "
					+ "on a.xydm=b.xydm "
					+ "left join "
					+ "(select xydm,count(*) jyrs from view_jygl_xsjyqkb where sfjy='是' group by xydm) c "
					+ "on a.xydm=c.xydm "
					+ "left join "
					+ "(select xydm,count(*) lsdwrs from view_jygl_xsjyqkb where dwzm='有' group by xydm) d "
					+ "on a.xydm=d.xydm " + query + " order by 学院名称";

		}

		if ("jyqktjb".equalsIgnoreCase(tableName)) {

			sql = "select a.zrs 总人数,d.lsdwrs 落实单位人数,c.qyrs 签约人数, replace((select to_char(c.qyrs/a.zrs*100,'999.99')||'%' from dual),'    ','0') 签约率, b.jyrs 就业人数,replace((select to_char(b.jyrs/a.zrs*100,'999.99')||'%' from dual),'    ','0') 就业率 from "
					+ "(select count(*) zrs from view_xsjbxx ) a , "
					+ "(select count(*) jyrs from view_jygl_xsjyqkb where sfjy='是') b, "
					+ "(select count(*) qyrs from view_jygl_xsjyqkb where sfqy='是') c, "
					+ "(select count(*) lsdwrs from view_jygl_xsjyqkb where dwzm='有') d";

		}

		if ("fbjyqktjb".equalsIgnoreCase(tableName)) {

			String query = DealString.toGBK(request.getParameter("querry"));
			sql = "select rownum 序号, a.xh 学号,a.xm 姓名,a.xb 性别,a.dwmc 单位名称,a.dwdz 单位地址,a.dwdh 单位电话,a.sfjy 是否就业,a.sfqy 是否签约,a.djsj 登记时间 from view_jygl_xsjyqkb a "
					+ query + " order by xh";

		}

		if ("zydkqktjb".equalsIgnoreCase(tableName)) {

			String query = DealString.toGBK(request.getParameter("querry"));
			sql = "select rownum 序号, a.xymc 学院名称,a.xydm 学院代码,a.zymc 专业名称,a.zydm 专业代码,a.zyrs 专业人数,d.lsdwrs 落实单位人数,b.zydkrs 专业对口人数,replace((select to_char(b.zydkrs/a.zyrs*100,'999.99')||'%' from dual),'    ','0') 对口率 from "
					+ "(select xymc,xydm,zymc,zydm,count(*) zyrs from view_xsjbxx group by xymc,xydm,zymc,zydm) a "
					+ "left join "
					+ "(select zydm,count(*) zydkrs from view_jygl_xsjyqkb where zydkqk='完全对口' or zydkqk='基本对口' group by zydm) b "
					+ "on a.zydm=b.zydm "
					+ "left join "
					+ "(select zydm,count(*) lsdwrs from view_jygl_xsjyqkb where dwzm='有' group by zydm) d "
					+ "on a.zydm=d.zydm " + query + " order by 学院名称";
			;

		}

		if ("gzqktjb".equalsIgnoreCase(tableName)) {

			sql = "select rownum 序号,b.gzqk 工资情况,c.lsdwrs 落实单位人数,replace((select to_char(c.lsdwrs/a.zrs*100,'999.99')||'%' from dual),'    ','0') 比例 from "
					+ "(select count(*) zrs from view_jygl_xsjyqkb ) a , "
					+ "(select gzqk ,count(*) zrs from view_jygl_xsjyqkb group by gzqk ) b "
					+ "left join "
					+ "(select gzqk ,count(*) lsdwrs from view_jygl_xsjyqkb where dwzm='有' group by gzqk) c "
					+ "on b.gzqk = c.gzqk ";

		}

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		if (tableName == null) {
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		} else {
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		}
		return mapping.findForward("success");
	}

	// 就业咨询教师登记
	private ActionForward jyzxTeaInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {

		DAO dao = DAO.getInstance();
		String tableName = "jygl_zxjsxxb";
		HashMap<String, String> map = new HashMap<String, String>();
		String act = request.getParameter("act");
		if ("save".equals(act)) {
			String num = request.getParameter("num");
			String name = DealString.toGBK(request.getParameter("name"));
			String age = request.getParameter("age");
			String xb = DealString.toGBK(request.getParameter("xb"));
			String userid = DealString.toGBK(request.getParameter("userid"));
			String password = DealString
					.toGBK(request.getParameter("password"));
			String zxszg = DealString.toGBK(request.getParameter("zxszg"));
			String lxdh = request.getParameter("lxdh");
			String email = request.getParameter("email");
			String zxsjj = DealString.toGBK(request.getParameter("zxsjj"));

			boolean judge = false;
			judge = StandardOperation.insert(tableName, new String[] { "num",
					"name", "age", "xb", "userid", "password", "zxszg", "lxdh",
					"email", "zxsjj" }, new String[] { num, name, age, xb,
					userid, password, zxszg, lxdh, email, zxsjj }, request);
			if (judge) {
				request.setAttribute("save", "ok");

				// 添加操作的记录
				if ("teacher".equals(userType)) {
					HttpSession session = request.getSession(); // 获得会话
					String userName = session.getAttribute("userName")
							.toString();
					String whensj = (dao
							.getOneRs(
									"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
									new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

					StandardOperation.insert("JYGL_USERCZMXB", new String[] {
							"userid", "dowhat", "whichtable", "whichpk",
							"whensj" }, new String[] { userName, "增加",
							"咨询教师信息表", "咨询师编号:" + num, whensj }, request);
				}
			} else {
				request.setAttribute("save", "no");
			}

			map = dao.getMap("select * from jygl_zxjsxxb where num=?",
					new String[] { num }, new String[] { "num", "name", "age",
							"xb", "userid", "password", "zxszg", "lxdh",
							"email", "zxsjj" });

		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 学生咨询预约 查询和删除
	private ActionForward jyzxYuyuequery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) throws Exception {

		DAO dao = DAO.getInstance(); // 实例化通用方法，并获得数据库连接
		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = ""; // sql语句
		String querry = " where 1=1 "; // sql条件
		String tableName = "jygl_zxjsxxb"; // 查询信息源表（多为视图名）
		String rsNum = "0";// 返回的记录数
		String dataType = request.getParameter("act"); // 接收数据类型
		String num = request.getParameter("num"); // 接收编号
		String name = DealString.toGBK(request.getParameter("name")); // 接收姓名
		String xb = DealString.toGBK(request.getParameter("xb")); // 接收性别代码
		String pk = "num"; // 主键
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String pkValue = request.getParameter("pkValue");
		String type = (String) request.getSession().getAttribute("userType");

		if (!"stu".equalsIgnoreCase(type)) {
			request.setAttribute("errMsg", "对不起，本页面只有学生用户可以访问!");
			return new ActionForward("/errMsg.do", false);
		}

		if (userType.equals("student")) {
			request.setAttribute("isstudent", "ok");
		} else {
			request.setAttribute("isstudent", "no");
		}

		if ("del".equals(doType2)) {
			boolean judge = false;
			judge = StandardOperation.delete(tableName, pk, pkValue, request);
			if (judge) {
				request.setAttribute("delete", "ok");

				// 删除操作的记录
				HttpSession session = request.getSession();
				String userName = session.getAttribute("userName").toString();
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"删除", "咨询教师信息表", "咨询师编号:" + pkValue, whensj },
						request);
			} else {
				request.setAttribute("delete", "no");
			}
		}

		// 把上次提交的值传回去
		if ("query".equals(doType)) {
			map.put("num", num);
			map.put("name", name);
			map.put("xb", xb);
		}

		if (num == null) {
			num = "";
		}
		if (name == null) {
			name = "";
		}
		if (xb == null) {
			xb = "";
		}
		if ((num == null) || num.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and num like '%" + num + "%' ";
		}
		if ((name == null) || name.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and name like '%" + name + "%' ";
		}
		if ((xb == null) || xb.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xb='" + xb + "' ";
		}

		sql = "select " + pk + " 主键,rownum 行号,a.* from " + tableName + " a "
				+ querry;
		colList = new String[] { "主键", "行号", "num", "name", "xb", "age",
				"lxdh", "zxszg", "readtimes" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		request.setAttribute("act", dataType);// 发送数据查询类型
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("rs1", map); // 返回的查询条件
		return mapping.findForward("success");
	}

	// 学生咨询预约 详细信息
	private ActionForward jyzxViewMoreInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = "num";
		String sql = ""; // sql语句
		DAO dao = DAO.getInstance();
		String realTable = "jygl_zxjsxxb";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// 查询数据
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + realTable
					+ " where 1=2"); // 返回列名数组
			String[] zxsinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (zxsinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), zxsinfo[i]); // 将记录循环放入map中
				}
			}
			sql = "select readtimes from " + realTable + " where num=?";
			String[] readtimes = dao.getOneRs(sql, new String[] { pkValue },
					new String[] { "readtimes" });
			int timeint = Integer.parseInt(readtimes[0]);
			timeint += 1;
			String tinestr = String.valueOf(timeint);
			StandardOperation.update(realTable, new String[] { "readtimes" },
					new String[] { tinestr }, pk, pkValue, request);
		}
		request.setAttribute("rs", map);// 发送数据集
		return mapping.findForward("success");
	}

	// 学生咨询预约 修改咨询师信息
	private ActionForward jyzxZxsUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pk = "num";
		String sql = ""; // sql语句
		DAO dao = DAO.getInstance();
		String realTable = "jygl_zxjsxxb";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// 查询数据
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + realTable
					+ " where 1=2"); // 返回列名数组
			String[] zxsinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (zxsinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), zxsinfo[i]); // 将记录循环放入map中
				}
			}
		}
		if ("update".equals(doType)) {
			String num = request.getParameter("num");
			String name = DealString.toGBK(request.getParameter("name"));
			String age = request.getParameter("age");
			String xb = DealString.toGBK(request.getParameter("xb"));
			String userid = DealString.toGBK(request.getParameter("userid"));
			String password = DealString
					.toGBK(request.getParameter("password"));
			String zxszg = DealString.toGBK(request.getParameter("zxszg"));
			String lxdh = request.getParameter("lxdh");
			String email = request.getParameter("email");
			String zxsjj = DealString.toGBK(request.getParameter("zxsjj"));

			boolean judge = false;
			judge = StandardOperation.update(realTable, new String[] { "name",
					"age", "xb", "userid", "password", "zxszg", "lxdh",
					"email", "zxsjj" }, new String[] { name, age, xb, userid,
					password, zxszg, lxdh, email, zxsjj }, pk, num, request);
			if (judge) {
				request.setAttribute("update", "ok");

				// 修改操作的记录
				HttpSession session = request.getSession();
				String userName = session.getAttribute("userName").toString();
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"修改", "咨询教师信息表", "咨询师编号:" + num, whensj },
						request);
			} else {
				request.setAttribute("update", "no");
			}

			sql = "select * from " + realTable + " where " + pk + "='" + num
					+ "'";
			String[] colList = dao.getColumnName("select * from " + realTable
					+ " where 1=2"); // 返回列名数组
			String[] zxsinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (zxsinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), zxsinfo[i]); // 将记录循环放入map中
				}
			}
		}

		request.setAttribute("rs", map);// 发送数据集
		return mapping.findForward("success");
	}

	// 咨询师预约
	private ActionForward jyzxZxsYuyue(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		String pk = "num";
		String sql = ""; // sql语句
		DAO dao = DAO.getInstance();
		String realTable = "jygl_zxjsxxb"; // 咨询教师信息表
		String tableName = "jygl_zxyyglb"; // 咨询预约管理表
		String doType = request.getParameter("doType");
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// 查询数据
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + realTable
					+ " where 1=2"); // 返回列名数组
			String[] zxsinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (zxsinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), zxsinfo[i]); // 将记录循环放入map中
				}
			}
		}

		if ("save".equals(doType)) {
			String num = request.getParameter("num"); // 咨询师编号
			String meet = DealString.toGBK(request.getParameter("meet")); // 是否要求见面
			String qwyjtime = request.getParameter("qwyjtime"); // 期望约见时间
			String zxwtjs = DealString.toGBK(request.getParameter("zxwtjs")); // 咨询问题简述
			String tjsj = GetTime.getSystemTime();
			boolean judge = false;
			judge = StandardOperation
					.insert(tableName, new String[] { "num", "xsxh", "meet",
							"qwyjtime", "zxwtjs", "tjsj" }, new String[] { num,
							userName, meet, qwyjtime, zxwtjs, tjsj }, request);
			if (judge) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "no");
			}
			/*
			 * // 再次显示该咨询师信息 sql = "select * from " + realTable + " where num='" +
			 * num + "'"; String[] colList = dao.getColumnName("select * from " +
			 * realTable + " where 1=2"); // 返回列名数组 String[] zxsinfo =
			 * dao.getOneRs(sql, new String[] {}, colList); if (zxsinfo != null) {
			 * for (int i = 0; i < colList.length; i++) {
			 * map.put(colList[i].toLowerCase(), zxsinfo[i]); // 将记录循环放入map中 } }
			 */
		}

		request.setAttribute("rs", map);// 发送数据集
		return mapping.findForward("success");
	}

	// 咨询师预约管理（页面打开,查询，删除）
	private ActionForward jyzxZxyygl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		DAO dao = DAO.getInstance(); // 实例化通用方法，并获得数据库连接
		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = ""; // sql语句
		String querry = " where 1=1 "; // sql条件
		String tableName = "JYGL_ZXYYGLB"; // 查询信息源表（多为视图名）
		String rsNum = "0";// 返回的记录数
		String dataType = request.getParameter("act"); // 接收数据类型
		String num = request.getParameter("num"); // 接收咨询师编号
		String xsxh = request.getParameter("xsxh"); // 接收学生学号
		String meet = DealString.toGBK(request.getParameter("meet")); // 接收是否要求见面
		String xsqr = DealString.toGBK(request.getParameter("xsqr")); // 接收学生确认
		String zxsqr = DealString.toGBK(request.getParameter("zxsqr")); // 接收咨询师确认
		String pk = "num||xsxh||tjsj"; // 主键
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String pkValue = request.getParameter("pkValue");
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		String type = (String) session.getAttribute("userType");

		if ("stu".equalsIgnoreCase(type)) {
			request.setAttribute("errMsg", "对不起，您无权访问此页!");
			return new ActionForward("/errMsg.do", false);

		}
		// 关于发布时间的处理 用于查询条件
		String xjsj = DealString.toGBK(request.getParameter("xjsj")); // 相距时间

		String nowsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmdd') sdate from dual", // 发布时间
				// //
				// 取至数据库临时表
				new String[] {}, new String[] { "sdate" }))[0];
		String sjyear = nowsj.substring(0, 4);
		String sjmonth = nowsj.substring(4, 6);
		String sjday = nowsj.substring(6, 8);
		String nowsj1 = sjyear + "-" + sjmonth + "-" + sjday;
		dealDate dealdate = new dealDate();
		String beforesj = "";
		if (!"".equals(xjsj)) {
			beforesj = dealdate.getDate2(Integer.parseInt(xjsj), nowsj1);
			beforesj = beforesj.replaceAll("-", "");
		}

		// 删除
		if ("del".equals(doType2)) {
			boolean judge = false;
			judge = StandardOperation.delete(tableName, pk, pkValue, request);
			if (judge) {
				request.setAttribute("delete", "ok");

				// 删除操作的记录
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"删除", "咨询预约管理表", "学号:" + pkValue, whensj },
						request);
			} else {
				request.setAttribute("delete", "no");
			}
		}

		// 把上次提交的值传回去
		if ("query".equals(doType)) {
			map.put("num", num);
			map.put("xsxh", xsxh);
			map.put("xjsj", xjsj);
			map.put("meet", meet);
			map.put("xsqr", xsqr);
			map.put("zxsqr", zxsqr);
		}

		if (num == null) {
			num = "";
		}
		if (xsxh == null) {
			xsxh = "";
		}
		if (xjsj == null) {
			xjsj = "";
		}
		if (meet == null) {
			meet = "";
		}
		if (xsqr == null) {
			xsqr = "";
		}
		if (zxsqr == null) {
			zxsqr = "";
		}

		if ((num == null) || num.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and num like '%" + num + "%' ";
		}
		if ((xsxh == null) || xsxh.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xsxh like '%" + xsxh + "%' ";
		}
		if ((xjsj == null) || xjsj.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and (replace(tjsj,'-','') between '" + beforesj
					+ "' and '" + nowsj + "') ";
		}
		if ((meet == null) || meet.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and meet='" + meet + "' ";
		}
		if ((xsqr == null) || xsqr.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xsqr='" + xsqr + "' ";
		}
		if ((zxsqr == null) || zxsqr.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and zxsqr='" + zxsqr + "' ";
		}

		sql = "select " + pk + " 主键,rownum 行号,a.* from " + tableName + " a "
				+ querry;
		colList = new String[] { "主键", "行号", "num", "xsxh", "tjsj", "qwyjtime",
				"xsqr", "zxsqr", "meet" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		request.setAttribute("act", dataType);// 发送数据查询类型
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("rs1", map); // 返回的查询条件
		return mapping.findForward("success");
	}

	// 咨询预约管理 详细信息
	private ActionForward zxyyglViewMoreInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = "num||xsxh||tjsj";
		String sql = ""; // sql语句
		DAO dao = DAO.getInstance();
		String realTable = "JYGL_ZXYYGLB";
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// 查询数据
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + realTable
					+ " where 1=2"); // 返回列名数组
			String[] zxyyglinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (zxyyglinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), zxyyglinfo[i]); // 将记录循环放入map中
				}
			}
			// 获取咨询师姓名
			String num = map.get("num");
			sql = "select name from jygl_zxjsxxb where num=?";
			String[] zxsinfo = dao.getOneRs(sql, new String[] { num },
					new String[] { "name" });
			map.put("zxsname",
					null == zxsinfo || Base.isNull(zxsinfo[0]) ? "咨询师不存在或者被删除"
							: zxsinfo[0]);
			// 获取学生其他信息
			String xsxh = map.get("xsxh");
			sql = "select * from view_xsjbxx where xh=?";
			String[] stuinfo = dao.getOneRs(sql, new String[] { xsxh },
					new String[] { "xm", "xb", "nj", "xymc", "zymc", });
			if (stuinfo != null) {
				map.put("name", stuinfo[0]);
				map.put("xb", stuinfo[1]);
				map.put("nj", stuinfo[2]);
				map.put("xymc", stuinfo[3]);
				map.put("zymc", stuinfo[4]);
			}
			/*
			 * // 把时间转化过来 String tjsj = null; String sjyear = null; String sjmon =
			 * null; String sjday = null; String sjhour = null; String sjmin =
			 * null; String sjss = null; // 把咨询师确认时间转化过来 String zxsqrsj = null; //
			 * 把学生确认时间转化过来 String xsqrsj = null;
			 */
		}
		request.setAttribute("rs", map);// 发送数据集
		return mapping.findForward("success");
	}

	// 咨询回复
	private ActionForward answerYuyue(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pk = "num||xsxh||tjsj";
		String sql = ""; // sql语句
		DAO dao = DAO.getInstance();
		String realTable = "JYGL_ZXYYGLB";
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();

		if ("answer".equals(act)) {
			String zxwthf = DealString.toGBK(request.getParameter("zxwthf"));
			String zxsqr = DealString.toGBK(request.getParameter("zxsqr"));
			String yjdd = DealString.toGBK(request.getParameter("yjdd"));
			String yjsj = DealString.toGBK(request.getParameter("yjsj"));
			String zxsqrsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 发布时间
							// //
							// 取至数据库临时表
							new String[] {}, new String[] { "sdate" }))[0];
			boolean judge = false;
			judge = StandardOperation.update(realTable, new String[] {
					"zxwthf", "zxsqr", "yjdd", "yjsj", "zxsqrsj" },
					new String[] { zxwthf, zxsqr, yjdd, yjsj, zxsqrsj }, pk,
					pkValue, request);
			if (judge) {
				request.setAttribute("answer", "ok");
			} else {
				request.setAttribute("answer", "no");
			}
		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// 查询数据
			if ("".equals(pkValue)) {
				pkValue = request.getParameter("xsxh");
			}
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + realTable
					+ " where 1=2"); // 返回列名数组
			String[] zxyyglinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (zxyyglinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), zxyyglinfo[i]); // 将记录循环放入map中
				}
			}
			// 获取咨询师姓名
			String num = map.get("num");
			sql = "select name from jygl_zxjsxxb where num=?";
			String[] zxsinfo = dao.getOneRs(sql, new String[] { num },
					new String[] { "name" });
			if (zxsinfo != null) {
				map.put("zxsname", zxsinfo[0]);
			}
			// 获取学生其他信息
			String xsxh = map.get("xsxh");
			sql = "select * from view_xsjbxx where xh=?";
			String[] stuinfo = dao.getOneRs(sql, new String[] { xsxh },
					new String[] { "xm", "xb", "nj", "xymc", "zymc", });
			if (stuinfo != null) {
				map.put("name", stuinfo[0]);
				map.put("xb", stuinfo[1]);
				map.put("nj", stuinfo[2]);
				map.put("xymc", stuinfo[3]);
				map.put("zymc", stuinfo[4]);
			}
			// 把提交时间转化过来
			/*
			 * String tjsj = null; String sjyear = null; String sjmon = null;
			 * String sjday = null; String sjhour = null; String sjmin = null;
			 * String sjss = null;
			 */
			/*
			 * if (null != map.get("tjsj")) { String sj =
			 * map.get("tjsj").toString(); sjyear = sj.substring(0, 4); sjmon =
			 * sj.substring(4, 6); sjday = sj.substring(6, 8); sjhour =
			 * sj.substring(8, 10); sjmin = sj.substring(10, 12); sjss =
			 * sj.substring(12, 14); tjsj = sjyear + "年" + sjmon + "月" + sjday +
			 * "日 " + sjhour + ":" + sjmin + ":" + sjss; map.put("tjsj", tjsj); } //
			 * 把咨询师确认时间转化过来 String zxsqrsj = null; if (null !=
			 * map.get("zxsqrsj")) { String sj = map.get("zxsqrsj").toString();
			 * sjyear = sj.substring(0, 4); sjmon = sj.substring(4, 6); sjday =
			 * sj.substring(6, 8); sjhour = sj.substring(8, 10); sjmin =
			 * sj.substring(10, 12); sjss = sj.substring(12, 14); zxsqrsj =
			 * sjyear + "年" + sjmon + "月" + sjday + "日 " + sjhour + ":" + sjmin +
			 * ":" + sjss; map.put("zxsqrsj", zxsqrsj); } // 把学生确认时间转化过来 String
			 * xsqrsj = null; if (null != map.get("xsqrsj")) { String sj =
			 * map.get("xsqrsj").toString(); sjyear = sj.substring(0, 4); sjmon =
			 * sj.substring(4, 6); sjday = sj.substring(6, 8); sjhour =
			 * sj.substring(8, 10); sjmin = sj.substring(10, 12); sjss =
			 * sj.substring(12, 14); xsqrsj = sjyear + "年" + sjmon + "月" + sjday +
			 * "日 " + sjhour + ":" + sjmin + ":" + sjss; map.put("xsqrsj",
			 * xsqrsj); }
			 */
		}
		request.setAttribute("rs", map);// 发送数据集
		return mapping.findForward("success");
	}

	// 
	private ActionForward zjlgjyxyexp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		StringBuffer querry = new StringBuffer(" where a.xsxh is not null ");
		String xsxh = request.getParameter("xsxh"); // 学号
		String name = DealString.toGBK(request.getParameter("name")); // 姓名
		String xb = DealString.toGBK(request.getParameter("xb")); // 性别
		String xymc = DealString.toGBK(request.getParameter("xymc")); // 学院
		String nj = request.getParameter("nj"); // 年级
		String xxsh = DealString.toGBK(request.getParameter("xxsh")); // 学校审核
		// 关于发布时间的处理 用于查询条件
		String xjsj = DealString.toGBK(request.getParameter("xjsj")); // 相距时间
		String nowsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 发布时间
				// //
				// 取至数据库临时表
				new String[] {}, new String[] { "sdate" }))[0];
		// String shsj = (dao.getOneRs(
		// "select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", //
		// 学校审核时间
		// //
		// 取至数据库临时表
		// new String[] {}, new String[] { "sdate" }))[0];
		String sjyear = nowsj.substring(0, 4);
		String sjmonth = nowsj.substring(4, 6);
		String sjday = nowsj.substring(6, 8);
		String sjqt = nowsj.substring(8, 14);
		String nowsj1 = sjyear + "-" + sjmonth + "-" + sjday;
		dealDate dealdate = new dealDate();
		String beforesj = "";
		if (!"".equals(xjsj)) {
			beforesj = dealdate.getDate2(Integer.parseInt(xjsj), nowsj1);
			beforesj = beforesj.replaceAll("-", "") + sjqt;
		}
		querry.append(StringUtils.isNull(xsxh) ? " and 1=1" : " and xsxh='"
				+ xsxh + "'");
		querry.append(StringUtils.isNull(xxsh) ? " and 1=1" : " and xxsh='"
				+ xxsh + "'");
		querry.append(StringUtils.isNull(name) ? " and 1=1" : " and xm like '%"
				+ name + "%'");
		querry.append(StringUtils.isNull(xb) ? " and 1=1" : " and xb = '" + xb
				+ "'");
		querry
				.append(StringUtils.isNull(xymc) ? " and 1=1"
						: "  and xymc in (select xsxh from jygl_xsjbxxb where xsxh in (select xh from view_xsjbxx where xymc ='"
								+ xymc + "'))");
		querry.append(StringUtils.isNull(nj) ? " and 1=1"
				: " and xsxh in (select xsxh from jygl_xsjbxxb where nd = '"
						+ nj + "')");
		querry
				.append(StringUtils.isNull(xjsj) ? " and 1=1"
						: " and (fbsj between '" + beforesj + "' and '" + nowsj
								+ "') ");

		String sql = "select (select xxmc from xtszb) xxmc,(select xxdm from xtszb) xxdm,b.yxmc,a.zymc,b.zydm,b.szbj,b.xm,a.xsxh,b.sfzhm,b.xbdm,c.csrq,b.mzdm,b.zzmmdm,b.syszd,a.xxsh,b.syszddm,b.xldm,b.xz,b.sfzz,b.sfsf,b.sfdl,b.zslb,b.pyfsdm,b.dxhwpdw,b.rxsj,b.bysj,a.lxdh,b.sjhm,b.qqhm,a.lxdz,b.dzxx,a.yzbm,b.yxdm,b.zydm,b.zydm,a.shsjqk,a.hjqk,b.wydj,b.jsjdj,a.grtc,b.zyyx,b.sybz1,b.sybz2,b.sybz3 from jygl_grjlb a left join zjgsjyxytj b on a.xsxh=b.xh left join view_xsxxb c on a.xsxh=c.xh"
				+ querry.toString();
		Vector<Object> vec = new Vector<Object>();
		WritableWorkbook wwb = Workbook.createWorkbook(response
				.getOutputStream());
		WritableSheet ws = wwb.createSheet("数据导出", 0);
		try {
			String ColumnName[] = new String[] { "xxmc", "xxdm", "yxmc",
					"zymc", "zydm", "szbj", "xm", "xsxh", "sfzhm", "xbdm",
					"csrq", "mzdm", "zzmmdm", "syszd", "syszddm", "xldm", "xz",
					"sfzz", "sfsf", "sfdl", "zslb", "pyfsdm", "dxhwpdw",
					"rxsj", "bysj", "lxdh", "sjhm", "qqhm", "lxdz", "dzxx",
					"yzbm", "yxdm", "zydm", "zydm", "shsjqk", "hjqk", "wydj",
					"jsjdj", "grtc", "zyyx", "sybz1", "sybz2", "sybz3" };
			String ColumnNameCN[] = new String[] { "学校名称", "学校代码", "院系名称",
					"专业名称", "专业代码", "所在班级", "学生姓名", "学号", "身份证号", "性别代码",
					"出生日期", "民族代码", "政治面貌", "生源地", "生源地代码", "学历层次", "学制",
					"是否在职", "是否师范", "是否独立", "招生类别编号", "培养方式代码", "定向或委培单位",
					"入学年份", "毕业年份", "联系电话", "手机号码", "QQ号码", "联系地址", "电子邮箱",
					"邮编", "院系编号", "专业编号", "班级编号", "担任社会工作情况", "获奖情况", "英语水平",
					"计算机水平", "兴趣特长", "职业意向", "就业地意向1", "就业地意向2", "就业地意向3" };
			for (int m = 0; m < ColumnNameCN.length; m++) {
				ws.addCell(new Label(m, 0, ColumnNameCN[m]));
			}
			vec.addAll(dao.rsToVator(sql, new String[] {}, ColumnName));
			int k = ColumnName.length;
			for (int i = 0; i < vec.size(); i++) {
				String[] tmp = (String[]) vec.toArray()[i];
				for (int j = 0; j < k; j++) {
					ws.addCell(new Label(j, i + 1, tmp[j]));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据导出失败!");
		} finally {
			wwb.write();
			wwb.close();
		}
		return mapping.findForward("");
	}

	// 预约修改
	private ActionForward updateYuyue(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String xsxh = request.getParameter("xsxh");
		String meet = request.getParameter("meet");
		String tableName = "JYGL_ZXYYGLB";
		String pk = "num||xsxh||tjsj"; // 主键
		String sql = "";
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();

		if ("".equals(pkValue) || null == pkValue) {
			pkValue = xsxh;
		}
		if ("update".equals(doType)) {
			String qwyjtime = request.getParameter("qwyjtime");
			String zxwtjs = DealString.toGBK(request.getParameter("zxwtjs"));

			boolean judge = false;
			judge = StandardOperation.update(tableName, new String[] { "meet",
					"qwyjtime", "zxwtjs" }, new String[] { meet, qwyjtime,
					zxwtjs }, pk, pkValue, request);
			if (judge) {
				request.setAttribute("update", "ok");

				// 修改操作的记录
				HttpSession session = request.getSession();
				String userName = session.getAttribute("userName").toString();
				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

				StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"修改", "咨询预约管理表", "学号:" + xsxh, whensj },
						request);
			} else {
				request.setAttribute("update", "no");
			}
		}

		if ("go".equals(act)) {
			sql = "select * from " + tableName + " where num||xsxh||tjsj=?";
			String[] colList = dao.getColumnName("select * from " + tableName
					+ " where 1=2"); // 返回列名数组
			String[] zxyuyueinfo = dao.getOneRs(sql, new String[] { pkValue },
					colList);
			if (zxyuyueinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), zxyuyueinfo[i]); // 将记录循环放入map中
				}
				// 获取咨询师姓名
				String num = map.get("num");
				sql = "select name from jygl_zxjsxxb where num=?";
				String[] zxsinfo = dao.getOneRs(sql, new String[] { num },
						new String[] { "name" });
				if (zxsinfo != null) {
					map.put("zxsname", zxsinfo[0]);
				}
				// 获取学生其他信息
				xsxh = map.get("xsxh");
				sql = "select * from view_xsjbxx where xh=?";
				String[] stuinfo = dao.getOneRs(sql, new String[] { xsxh },
						new String[] { "xm", "xb", "nj", "xymc", "zymc", });
				if (stuinfo != null) {
					map.put("name", stuinfo[0]);
					map.put("xb", stuinfo[1]);
					map.put("nj", stuinfo[2]);
					map.put("xymc", stuinfo[3]);
					map.put("zymc", stuinfo[4]);
				}
				if ("meetonchange".equals(doType2)) {
					map.put("meet", meet);
				}
				if ("是".equals(map.get("meet"))) {
					request.setAttribute("mt", "yes");
				} else {
					request.setAttribute("mt", "no");
				}
			}
		}

		request.setAttribute("rs", map);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("success");
	}

	// 咨询预约结果查询
	private ActionForward jyzxResultQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response, String userType) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		String pk = "num||xsxh||tjsj";
		String[] colList = null;
		String[] colListCN = null;
		ArrayList<Object> rs = new ArrayList<Object>(); // 获得记录
		String rsNum = "0";
		String tableName = "jygl_zxyyglb";
		List topTr = null;
		// HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userlb = session.getAttribute("userType").toString();

		if (userType.equals("teacher")) {
			request.setAttribute("isteacher", "ok");
		} else {
			request.setAttribute("isteacher", "no");
		}

		if (userType.equals("teacher")) {
			if (userlb.equals("admin")) {
				sql = "select " + pk + " 主键,rownum 行号,a.* from " + tableName
						+ " a ";
			} else {
				sql = "select num from JYGL_ZXJSXXB where userid=?";
				String[] teainfo = dao.getOneRs(sql, new String[] { userName },
						new String[] { "num" });
				String num = "!!00";
				if (teainfo != null && teainfo.length > 0) {
					num = teainfo[0];
				}
				sql = "select " + pk + " 主键,rownum 行号,a.* from " + tableName
						+ " a " + " where num='" + num + "'";
			}
			colList = new String[] { "主键", "行号", "num", "xsxh", "tjsj",
					"qwyjtime", "xsqr", "zxsqr", "meet" };
			colListCN = dao.getColumnNameCN(colList, tableName);
			topTr = dao.arrayToList(colList, colListCN);
			if ((request.getParameter("act") != null)
					&& request.getParameter("act").equalsIgnoreCase("go")) {
				rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
				if (rs == null) {
					rsNum = "0";
				} else {
					rsNum = String.valueOf(rs.size());
				}
			}
			request.setAttribute("who", "teacher");
		}

		if (userType.equals("student")) {
			sql = "select " + pk + " 主键,rownum 行号,a.* from " + tableName
					+ " a " + " where xsxh='" + userName + "'";
			;
			colList = new String[] { "主键", "行号", "num", "xsxh", "tjsj",
					"qwyjtime", "xsqr", "zxsqr", "meet" };
			colListCN = dao.getColumnNameCN(colList, tableName);
			topTr = dao.arrayToList(colList, colListCN);
			if ((request.getParameter("act") != null)
					&& request.getParameter("act").equalsIgnoreCase("go")) {
				rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
				if (rs == null) {
					rsNum = "0";
				} else {
					rsNum = String.valueOf(rs.size());
				}
			}
			request.setAttribute("who", "student");
		}

		request.setAttribute("rs", rs); // 发送数据集
		request.setAttribute("topTr", topTr); // 发送表头
		request.setAttribute("rsNum", rsNum); // 发送记录数
		return mapping.findForward("success");
	}

	// 学生咨询回复
	private ActionForward answerYuyueStu(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String pk = "num||xsxh||tjsj";
		String sql = ""; // sql语句9
		DAO dao = DAO.getInstance();
		String realTable = "JYGL_ZXYYGLB";
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();

		if ("answer".equals(act)) {
			String xsqr = DealString.toGBK(request.getParameter("xsqr"));
			String xsqrsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 发布时间
							// //
							// 取至数据库临时表
							new String[] {}, new String[] { "sdate" }))[0];
			boolean judge = false;
			judge = StandardOperation.update(realTable, new String[] { "xsqr",
					"xsqrsj" }, new String[] { xsqr, xsqrsj }, pk, pkValue,
					request);
			if (judge) {
				request.setAttribute("answer", "ok");
			} else {
				request.setAttribute("answer", "no");
			}
		}

		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// 参数异常
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("view")) {
			// 查询数据
			if ("".equals(pkValue)) {
				pkValue = request.getParameter("xsxh");
			}
			sql = "select * from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + realTable
					+ " where 1=2"); // 返回列名数组
			String[] zxyyglinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (zxyyglinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), zxyyglinfo[i]); // 将记录循环放入map中
				}
			}
			if ("不赴约X".equals(map.get("xsqr"))
					|| "已确认√".equals(map.get("xsqr"))) {
				request.setAttribute("xsqr", "no");
			} else {
				request.setAttribute("xsqr", "ok");
			}
			// 获取咨询师姓名
			String num = map.get("num");
			sql = "select name from jygl_zxjsxxb where num=?";
			String[] zxsinfo = dao.getOneRs(sql, new String[] { num },
					new String[] { "name" });
			if (zxsinfo != null) {
				map.put("zxsname", zxsinfo[0]);
			}
			// 获取学生其他信息
			String xsxh = map.get("xsxh");
			sql = "select * from view_xsjbxx where xh=?";
			String[] stuinfo = dao.getOneRs(sql, new String[] { xsxh },
					new String[] { "xm", "xb", "nj", "xymc", "zymc", });
			if (stuinfo != null) {
				map.put("name", stuinfo[0]);
				map.put("xb", stuinfo[1]);
				map.put("nj", stuinfo[2]);
				map.put("xymc", stuinfo[3]);
				map.put("zymc", stuinfo[4]);
			}
			// 把提交时间转化过来
			String sjyear = null;
			String sjmon = null;
			String sjday = null;
			String sjhour = null;
			String sjmin = null;
			String sjss = null;
			/*
			 * if (null != map.get("tjsj")) { String sj =
			 * map.get("tjsj").toString(); sjyear = sj.substring(0, 4); sjmon =
			 * sj.substring(4, 6); sjday = sj.substring(6, 8); sjhour =
			 * sj.substring(8, 10); sjmin = sj.substring(10, 12); sjss =
			 * sj.substring(12, 14); tjsj = sjyear + "年" + sjmon + "月" + sjday +
			 * "日 " + sjhour + ":" + sjmin + ":" + sjss; map.put("tjsj", tjsj); }
			 */
			// 把咨询师确认时间转化过来
			String zxsqrsj = null;
			if (null != map.get("zxsqrsj")) {
				String sj = map.get("zxsqrsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				sjmin = sj.substring(10, 12);
				sjss = sj.substring(12, 14);
				zxsqrsj = sjyear + "年" + sjmon + "月" + sjday + "日 " + sjhour
						+ ":" + sjmin + ":" + sjss;
				map.put("zxsqrsj", zxsqrsj);
			}
			// 把学生确认时间转化过来
			String xsqrsj = null;
			if (null != map.get("xsqrsj")) {
				String sj = map.get("xsqrsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				sjmin = sj.substring(10, 12);
				sjss = sj.substring(12, 14);
				xsqrsj = sjyear + "年" + sjmon + "月" + sjday + "日 " + sjhour
						+ ":" + sjmin + ":" + sjss;
				map.put("xsqrsj", xsqrsj);
			}
		}
		request.setAttribute("rs", map);// 发送数据集
		return mapping.findForward("success");
	}

	// 就业招聘维护
	private ActionForward jyzpWeiHu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<Object> rs = new ArrayList<Object>(); // 获得记录
		String pk = "username";
		String tableName = "JYGL_JYZPWHB";
		String rsNum = "";
		String doType = request.getParameter("doType");
		String username = request.getParameter("username");
		String gsmc = DealString.toGBK(request.getParameter("gsmc"));
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();

		String xxdm = StandardOperation.getXxdm();
		List gsmcList = null;
		if (Globals.XXDM_ZGDZDX.equals(xxdm)) {
			sql = "select distinct dwmc from dwxxb";
			gsmcList = dao.getList(sql, new String[] {},
					new String[] { "dwmc" });
		} else {
			sql = "select distinct gsmc from jygl_zpxxb";
			gsmcList = dao.getList(sql, new String[] {},
					new String[] { "gsmc" });
		}
		request.setAttribute("gsmcList", gsmcList);

		if ("add".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.insert(tableName, new String[] {
					"username", "gsmc" }, new String[] { username, gsmc },
					request);
			if (judge) {
				request.setAttribute("add", "ok");

				// 增加操作的记录
				if ("teacher".equals(userType)) {
					String whensj = (dao
							.getOneRs(
									"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
									new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

					StandardOperation.insert("JYGL_USERCZMXB", new String[] {
							"userid", "dowhat", "whichtable", "whichpk",
							"whensj" }, new String[] { userName, "增加",
							"就业招聘维护表", "用户名:" + username + " 公司名称:" + gsmc,
							whensj }, request);
				}
			} else {
				request.setAttribute("add", "no");
			}
		}
		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete(tableName, pk, username, request);
			if (judge) {
				request.setAttribute("delete", "ok");

				// 删除操作的记录

				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

				judge = StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"删除", "就业招聘维护表", "用户名:" + username, whensj },
						request);
			} else {
				request.setAttribute("delete", "no");
			}
		}

		sql = "select " + pk + " 主键,rownum 行号,a.* from " + tableName + " a ";
		String[] colList = new String[] { "主键", "行号", "username", "gsmc" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr); // 发送表头
		request.setAttribute("rs1", map);
		return mapping.findForward("success");
	}

	// 就业协议维护
	private ActionForward jyxyWeiHu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<Object> rs = new ArrayList<Object>(); // 获得记录
		String pk = "username";
		String tableName = "JYGL_JYXYWHB";
		String rsNum = "";
		String doType = request.getParameter("doType");
		String username = request.getParameter("username");
		String usertype = DealString.toGBK(request.getParameter("usertype"));
		String nj = request.getParameter("nj");
		String xymc = DealString.toGBK(request.getParameter("xymc"));
		String zymc = DealString.toGBK(request.getParameter("zymc"));
		String bj = DealString.toGBK(request.getParameter("bj"));
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();

		if ("refresh".equals(doType)) {
			map.put("username", username);
			map.put("usertype", usertype);
			map.put("nj", nj);
			map.put("xymc", xymc);
			map.put("zymc", zymc);
			map.put("bj", bj);
		}

		// sql = "select distinct gsmc from jygl_zpxxb";
		// List gsmcList = dao.getList(sql, new String[] {},
		// new String[] { "gsmc" });
		// request.setAttribute("gsmcList", gsmcList);

		if ("add".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.insert(tableName, new String[] {
					"username", "usertype", "nj", "xymc", "zymc", "bj" },
					new String[] { username, usertype, nj, xymc, zymc, bj },
					request);
			if (judge) {
				request.setAttribute("add", "ok");

				// 增加操作的记录
				if ("teacher".equals(userType)) {
					String whensj = (dao
							.getOneRs(
									"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
									new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

					StandardOperation.insert("JYGL_USERCZMXB", new String[] {
							"userid", "dowhat", "whichtable", "whichpk",
							"whensj" }, new String[] { userName, "增加",
							"就业协议维护表",
							"用户名:" + username + "  用户类型:" + usertype, whensj },
							request);
				}
			} else {
				request.setAttribute("add", "no");
			}
		}
		if ("del".equals(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete(tableName, pk, username, request);
			if (judge) {
				request.setAttribute("delete", "ok");

				// 删除操作的记录

				String whensj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual",
								new String[] {}, new String[] { "sdate" }))[0]; // 提交时间

				judge = StandardOperation.insert("JYGL_USERCZMXB",
						new String[] { "userid", "dowhat", "whichtable",
								"whichpk", "whensj" }, new String[] { userName,
								"删除", "就业协议维护表", "用户名:" + username, whensj },
						request);
			} else {
				request.setAttribute("delete", "no");
			}
		}

		sql = "select " + pk + " 主键,rownum 行号,a.* from " + tableName + " a ";
		String[] colList = new String[] { "主键", "行号", "username", "usertype",
				"xymc", "zymc", "bj" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		String xydm = "";
		sql = "select xydm from view_njxyzybj where xymc=?";
		String[] xyinfo = dao.getOneRs(sql, new String[] { xymc },
				new String[] { "xydm" });
		if (null != xyinfo) {
			xydm = xyinfo[0];
		}

		String zydm = "";
		sql = "select zydm from view_njxyzybj where zymc=?";
		String[] zyinfo = dao.getOneRs(sql, new String[] { zymc },
				new String[] { "zydm" });
		if (null != zyinfo) {
			zydm = zyinfo[0];
		}

		request.setAttribute("njList", dao.getNjList()); // 发送年级列表
		request.setAttribute("xyList", dao.getXyList()); // 发送学院列表
		request.setAttribute("zyList", dao.getZyList(xydm));// 发送专业列表
		request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));// 发送班级列表
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr); // 发送表头
		request.setAttribute("rs1", map);
		return mapping.findForward("success");
	}

	// 用户操作明细
	private ActionForward userczmx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance(); // 实例化通用方法，并获得数据库连接
		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = ""; // sql语句
		String querry = " where 1=1 "; // sql条件
		String tableName = "JYGL_USERCZMXB"; // 查询信息源表（多为视图名）
		String rsNum = "0";// 返回的记录数
		HashMap<String, String> map = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String pk = "rowid";
		String pkValue = request.getParameter("pkValue");

		if ("del".equals(doType2)) {
			boolean judge = false;
			judge = StandardOperation.delete(tableName, pk, pkValue, request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}
		String userid = request.getParameter("userid");
		String dowhat = DealString.toGBK(request.getParameter("dowhat"));
		String whichtable = DealString
				.toGBK(request.getParameter("whichtable"));

		// 关于发布时间的处理 用于查询条件
		String xjsj = DealString.toGBK(request.getParameter("xjsj")); // 相距时间

		String nowsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 发布时间
				// //
				// 取至数据库临时表
				new String[] {}, new String[] { "sdate" }))[0];
		String sjyear = nowsj.substring(0, 4);
		String sjmonth = nowsj.substring(4, 6);
		String sjday = nowsj.substring(6, 8);
		String sjqt = nowsj.substring(8, 14);
		String nowsj1 = sjyear + "-" + sjmonth + "-" + sjday;
		dealDate dealdate = new dealDate();
		String beforesj = "";
		if (!"".equals(xjsj) && "-1".equals(xjsj)) {
			beforesj=nowsj.substring(0,8)+"000000";
			nowsj=nowsj.substring(0,8)+"235959";
		}else if(!"".equals(xjsj) ){
			beforesj = dealdate.getDate2(Integer.parseInt(xjsj), nowsj1);
			beforesj = beforesj.replaceAll("-", "") + sjqt;
		}

		// 把上次提交的值传回去
		if ("query".equals(doType)) {
			map.put("userid", userid);
			map.put("dowhat", dowhat);
			map.put("whichtable", whichtable);
			map.put("xjsj", xjsj);
		}

		if (userid == null) {
			userid = "";
		}
		if (dowhat == null) {
			dowhat = "";
		}
		if (whichtable == null) {
			whichtable = "";
		}
		if (xjsj == null) {
			xjsj = "";
		}
		if ((userid == null) || userid.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and userid like '%" + userid + "%' ";
		}
		if ((dowhat == null) || dowhat.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and dowhat = '" + dowhat + "' ";
		}
		if ((whichtable == null) || whichtable.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and whichtable='" + whichtable + "' ";
		}
		if ((xjsj == null) || xjsj.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += "and (whensj between '" + beforesj + "' and '" + nowsj
					+ "') ";
		}

		if ("delall".equals(doType2)) {
			boolean judge = false;
			sql = "delete from " + tableName + " " + querry;
			judge = dao.runUpdate(sql, new String[] {});
			if (judge) {
				request.setAttribute("deleteall", "ok");
			} else {
				request.setAttribute("deleteall", "on");
			}
		}

		sql = "select  rownum 行号,a.rowid,a.* from " + tableName + " a "
				+ querry;
		colList = new String[] { "行号", "userid", "dowhat", "whichtable",
				"whichpk", "whensj" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		colList = new String[] { "rowid", "行号", "userid", "dowhat",
				"whichtable", "whichpk", "whensj" };
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("rs1", map); // 返回的查询条件
		return mapping.findForward("success");
	}

	// 一键统计
	private ActionForward onebutton(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();
		int allxybyrsnum1 = 0; // 1年度所有学院的毕业人数
		int allxyqyrsnum1 = 0; // 1年度所有学院的签约认数
		int allxyjyrsnum1 = 0; // 1年度所有学院的就业人数
		int allxybyrsnum2 = 0; // 2年度所有学院的毕业人数
		int allxyqyrsnum2 = 0; // 2年度所有学院的签约人数
		int allxyjyrsnum2 = 0; // 2年度所有学院的就业人数
		String allxyqyl1 = ""; // 第一年度所有学院签约率
		String allxyqyl2 = ""; // 第二年度所有学院签约率
		String allxyjyl1 = ""; // 第一年度所有学院就业率
		String allxyjyl2 = ""; // 第二年度所有学院就业率
		String bynd1 = request.getParameter("bynd1");
		String bynd2 = request.getParameter("bynd2");
		// 找出所有的学院把名称及代码放入string型的数组
		List<HashMap<String, String>> xylist = dao.getXyList();
		String xymcstring = "";
		String xydmstring = "";
		String mark = "%NNNNN%";
		HashMap<String, String> xymap = new HashMap<String, String>();
		List<ArrayList<String[]>> list = new ArrayList<ArrayList<String[]>>();

		if ("ok".equals(doType)) {

			map.put("bynd1", bynd1);
			map.put("bynd2", bynd2);

			for (int i = 0; i < xylist.size(); i++) {
				xymap = xylist.get(i);
				String xymcpoint = xymap.get("xymc");
				String xydmpoint = xymap.get("xydm");
				xymcstring += xymcpoint + mark;
				xydmstring += xydmpoint + mark;
			}
			String[] xymc = xymcstring.split(mark);
			String[] xydm = xydmstring.split(mark);
			String xymingcheng = "";
			String xydaima = "";

			// 做表头
			String[] topTr = { "学院", "专业", bynd1 + "年度毕业人数", bynd1 + "年度签约人数",
					bynd1 + "年度就业人数", bynd1 + "年度签约率", bynd2 + "年度签约率",
					bynd1 + "年度就业率", bynd2 + "年度就业率" };
			ArrayList<HashMap<String, String>> top = new ArrayList<HashMap<String, String>>();
			for (int k = 0; k < topTr.length; k++) {
				HashMap<String, String> maptop = new HashMap<String, String>();
				maptop.put("top", topTr[k]);
				top.add(maptop); // 把map中的值放进表头数组
			}
			// 循环取出学院列表
			for (int j = 0; j < xymc.length; j++) {
				xymingcheng = xymc[j];
				xydaima = xydm[j];

				// 取出该学院的专业代码放入数组
				HashMap<String, String> zymap = new HashMap<String, String>();
				List<HashMap<String, String>> zylist = dao.getZyList(xydaima);
				String zymcstring = "";
				String zydmstring = "";
				for (int k = 0; k < zylist.size(); k++) {
					zymap = zylist.get(k);
					String zymcpoint = zymap.get("zymc");
					String zydmpoint = zymap.get("zydm");
					zymcstring += zymcpoint + mark;
					zydmstring += zydmpoint + mark;
				}
				String[] zymcall = zymcstring.split(mark);
				String zymc = "";
				ArrayList<String[]> rs = new ArrayList<String[]>();
				// 循环取出专业列表
				for (int i = 0; i < zymcall.length; i++) {
					if (zymcall != null) {
						zymc = zymcall[i];
					}
					ArrayList<Object> byrsrs1 = new ArrayList<Object>();
					ArrayList<Object> qyrsrs1 = new ArrayList<Object>();
					ArrayList<Object> jyrsrs1 = new ArrayList<Object>();
					// 年度1毕业人数
					sql = "select b.xsxh from jygl_xsjbxxb b where  exists(select a.xh from view_xsjbxx a where a.zymc=? and a.xh=b.xsxh) and b.bynd=?";
					byrsrs1.addAll(dao.rsToVator(sql, new String[] { zymc,
							bynd1 }, new String[] { "xsxh" }));
					String byrsnum1 = "";
					if (null != byrsrs1) {
						byrsnum1 = String.valueOf(byrsrs1.size());
					}

					// 年度1签约人数
					sql = "select b.xsxh from jygl_jyxy b where exists(select a.xh from view_xsjbxx a where a.zymc=? and a.xh=b.xsxh) and b.bynd=?";
					qyrsrs1.addAll(dao.rsToVator(sql, new String[] { zymc,
							bynd1 }, new String[] { "xsxh" }));
					String qyrsnum1 = "";
					if (null != qyrsrs1) {
						qyrsnum1 = String.valueOf(qyrsrs1.size());
					}

					// 年度1签约率
					int inqyrsnum = Integer.valueOf(qyrsnum1);// 签约人数
					int inbyrsnum = Integer.valueOf(byrsnum1);// 毕业人数
					String qyl1 = "";
					if (inbyrsnum != 0) {
						qyl1 = (dao.getOneRs("select to_char(" + inqyrsnum
								+ "/" + inbyrsnum
								+ "*100,'999.99')||'%' aa from dual",
								new String[] {}, new String[] { "aa" }))[0];// 签约率
						if ("    .00%".equals(qyl1)) {
							qyl1 = "0.00%";
						}
					}

					ArrayList<Object> byrsrs2 = new ArrayList<Object>();
					ArrayList<Object> qyrsrs2 = new ArrayList<Object>();
					ArrayList<Object> jyrsrs2 = new ArrayList<Object>();
					// 年度2毕业人数
					sql = "select b.xsxh from jygl_xsjbxxb b where exists(select xh from view_xsjbxx a where zymc=? and a.xh=b.xsxh) and b.bynd=?";
					byrsrs2.addAll(dao.rsToVator(sql, new String[] { zymc,
							bynd2 }, new String[] { "xsxh" }));
					String byrsnum2 = "";
					if (null != byrsrs2) {
						byrsnum2 = String.valueOf(byrsrs2.size());
					}
					map.put("byrs2", byrsnum2);

					// 年度2签约人数
					sql = "select b.xsxh from jygl_jyxy b where exists(select a.xh from view_xsjbxx a where a.zymc=? and a.xh=b.xsxh) and b.bynd=?";
					qyrsrs2.addAll(dao.rsToVator(sql, new String[] { zymc,
							bynd2 }, new String[] { "xsxh" }));
					String qyrsnum2 = "";
					if (null != qyrsrs2) {
						qyrsnum2 = String.valueOf(qyrsrs2.size());
					}
					map.put("qyrs", qyrsnum2);

					// 年度2签约率
					inqyrsnum = Integer.valueOf(qyrsnum2);// 签约人数
					inbyrsnum = Integer.valueOf(byrsnum2);// 毕业人数
					String qyl2 = "";
					if (inbyrsnum != 0) {
						qyl2 = (dao.getOneRs("select to_char(" + inqyrsnum
								+ "/" + inbyrsnum
								+ "*100,'999.99')||'%' aa from dual",
								new String[] {}, new String[] { "aa" }))[0];// 签约率
						if ("    .00%".equals(qyl2)) {
							qyl2 = "0.00%";
						}
					}
					// 年度1就业人数
					sql = "select b.xsxh from jygl_jyxy b where exists(select a.xh from view_xsjbxx a where a.zymc=? and a.xh=b.xsxh) and b.bynd=? and b.wjybz='0'";
					jyrsrs1.addAll(dao.rsToVator(sql, new String[] { zymc,
							bynd1 }, new String[] { "xsxh" }));
					String jyrsnum1 = "";
					if (null != jyrsrs1) {
						jyrsnum1 = String.valueOf(jyrsrs1.size());
					}
					// 年度2就业人数
					sql = "select b.xsxh from jygl_jyxy b where  exists(select a.xh from view_xsjbxx a where a.zymc=? and a.xh=b.xsxh) and b.bynd=? and b.wjybz='0'";
					jyrsrs2.addAll(dao.rsToVator(sql, new String[] { zymc,
							bynd2 }, new String[] { "xsxh" }));
					String jyrsnum2 = "";
					if (null != jyrsrs1) {
						jyrsnum2 = String.valueOf(jyrsrs2.size());
					}
					// 年度1就业率
					int injyrsnum = Integer.valueOf(jyrsnum1);// 就业人数
					inbyrsnum = Integer.valueOf(byrsnum1);// 毕业人数
					String jyl1 = "";
					if (inbyrsnum != 0) {
						jyl1 = (dao.getOneRs("select to_char(" + injyrsnum
								+ "/" + inbyrsnum
								+ "*100,'999.99')||'%' aa from dual",
								new String[] {}, new String[] { "aa" }))[0];// 就业率
						if ("    .00%".equals(jyl1)) {
							jyl1 = "0.00%";
						}
					}
					// 年度2就业率
					injyrsnum = Integer.valueOf(jyrsnum2);// 就业人数
					inbyrsnum = Integer.valueOf(byrsnum2);// 毕业人数
					String jyl2 = "";
					if (inbyrsnum != 0) {
						jyl2 = (dao.getOneRs("select to_char(" + injyrsnum
								+ "/" + inbyrsnum
								+ "*100,'999.99')||'%' aa from dual",
								new String[] {}, new String[] { "aa" }))[0];// 就业率
						if ("    .00%".equals(jyl2)) {
							jyl2 = "0.00%";
						}
					}

					String[] tjvalues = { xymingcheng, zymc, byrsnum1,
							qyrsnum1, jyrsnum1, qyl1, qyl2, jyl1, jyl2 }; // 统计出来的一个专业数据

					rs.add(tjvalues);// 其中一个专业的统计数据 放入记录集
					if (i == ((zymcall.length) - 1)) {
						// 1年度学院毕业总人数
						sql = "select b.xsxh from jygl_xsjbxxb b where exists(select a.xh from view_xsjbxx a where a.xymc=? and a.xh=b.xsxh) and b.bynd=?";
						ArrayList<Object> xybyrsrs1 = new ArrayList<Object>();
						xybyrsrs1.addAll(dao.rsToVator(sql, new String[] {
								xymingcheng, bynd1 }, new String[] { "xsxh" }));
						String xybyrsnum1 = "";
						if (null != xybyrsrs1) {
							xybyrsnum1 = String.valueOf(xybyrsrs1.size());
						}
						// 1年度学院签约总人数
						sql = "select xsxh from jygl_jyxy where xymc=? and bynd=?";
						ArrayList<Object> xyqyrsrs1 = new ArrayList<Object>();
						xyqyrsrs1.addAll(dao.rsToVator(sql, new String[] {
								xymingcheng, bynd1 }, new String[] { "xsxh" }));
						String xyqyrsnum1 = "";
						if (null != xyqyrsrs1) {
							xyqyrsnum1 = String.valueOf(xyqyrsrs1.size());
						}
						// 1年度学院签约率
						int inxyqyrsnum = Integer.valueOf(xyqyrsnum1);// 签约人数
						int inxybyrsnum = Integer.valueOf(xybyrsnum1);// 毕业人数
						String xyqyl1 = "";
						if (inxybyrsnum != 0) {
							xyqyl1 = (dao.getOneRs("select to_char("
									+ inxyqyrsnum + "/" + inxybyrsnum
									+ "*100,'999.99')||'%' aa from dual",
									new String[] {}, new String[] { "aa" }))[0];// 1年度学院签约率
							if ("    .00%".equals(xyqyl1)) {
								xyqyl1 = "0.00%";
							}
						}

						// 2年度学院毕业总人数
						sql = "select b.xsxh from jygl_xsjbxxb b where exists(select a.xh from view_xsjbxx a where a.xymc=? and a.xh=b.xsxh) and b.bynd=?";
						ArrayList<Object> xybyrsrs2 = new ArrayList<Object>();
						xybyrsrs2.addAll(dao.rsToVator(sql, new String[] {
								xymingcheng, bynd2 }, new String[] { "xsxh" }));
						String xybyrsnum2 = "";
						if (null != xybyrsrs2) {
							xybyrsnum2 = String.valueOf(xybyrsrs2.size());
						}
						// 2年度学院签约总人数
						sql = "select b.xsxh from jygl_xsjbxxb b where exists(select a.xh from view_xsjbxx a where a.xymc=? and a.xh=b.xsxh) and b.bynd=?";
						ArrayList<Object> xyqyrsrs2 = new ArrayList<Object>();
						xyqyrsrs2.addAll(dao.rsToVator(sql, new String[] {
								xymingcheng, bynd2 }, new String[] { "xsxh" }));
						String xyqyrsnum2 = "";
						if (null != xyqyrsrs2) {
							xyqyrsnum2 = String.valueOf(xyqyrsrs2.size());
						}
						// 2年度学院签约率
						inxyqyrsnum = Integer.valueOf(xyqyrsnum2);// 签约人数
						inxybyrsnum = Integer.valueOf(xybyrsnum2);// 毕业人数
						String xyqyl2 = "";
						if (inxybyrsnum != 0) {
							xyqyl2 = (dao.getOneRs("select to_char("
									+ inxyqyrsnum + "/" + inxybyrsnum
									+ "*100,'999.99')||'%' aa from dual",
									new String[] {}, new String[] { "aa" }))[0];// 2年度学院签约率
							if ("    .00%".equals(xyqyl2)) {
								xyqyl2 = "0.00%";
							}
						}
						// 1年度学院就业总人数
						sql = "select xsxh from jygl_jyxy where xymc=? and bynd=? and wjybz='0'";
						ArrayList<Object> xyjyrsrs1 = new ArrayList<Object>();
						xyjyrsrs1.addAll(dao.rsToVator(sql, new String[] {
								xymingcheng, bynd1 }, new String[] { "xsxh" }));
						String xyjyrsnum1 = "";
						if (null != xyjyrsrs1) {
							xyjyrsnum1 = String.valueOf(xyjyrsrs1.size());
						}
						// 1年度学院就业率
						int inxyjyrsnum = Integer.valueOf(xyjyrsnum1);// 签约人数
						inxybyrsnum = Integer.valueOf(xybyrsnum1);// 毕业人数
						String xyjyl1 = "";
						if (inxybyrsnum != 0) {
							xyjyl1 = (dao.getOneRs("select to_char("
									+ inxyjyrsnum + "/" + inxybyrsnum
									+ "*100,'999.99')||'%' aa from dual",
									new String[] {}, new String[] { "aa" }))[0];// 1年度学院就业率
							if ("    .00%".equals(xyjyl1)) {
								xyjyl1 = "0.00%";
							}
						}
						// 2年度学院就业总人数
						sql = "select xsxh from jygl_jyxy where xymc=? and bynd=? and wjybz='0'";
						ArrayList<Object> xyjyrsrs2 = new ArrayList<Object>();
						xyjyrsrs2.addAll(dao.rsToVator(sql, new String[] {
								xymingcheng, bynd2 }, new String[] { "xsxh" }));
						String xyjyrsnum2 = "";
						if (null != xyjyrsrs2) {
							xyjyrsnum2 = String.valueOf(xyjyrsrs2.size());
						}
						// 2年度学院就业率
						inxyjyrsnum = Integer.valueOf(xyjyrsnum2);// 就业人数
						inxybyrsnum = Integer.valueOf(xybyrsnum2);// 毕业人数
						String xyjyl2 = "";
						if (inxybyrsnum != 0) {
							xyjyl2 = (dao.getOneRs("select to_char("
									+ inxyjyrsnum + "/" + inxybyrsnum
									+ "*100,'999.99')||'%' aa from dual",
									new String[] {}, new String[] { "aa" }))[0];// 1年度学院就业率
							if ("    .00%".equals(xyjyl2)) {
								xyjyl2 = "0.00%";
							}
						}

						String[] xytjvalues = { "", "小计", xybyrsnum1,
								xyqyrsnum1, xyjyrsnum1, xyqyl1, xyqyl2, xyjyl1,
								xyjyl2 };
						rs.add(xytjvalues);
						allxybyrsnum1 += Integer.valueOf(xybyrsnum1);
						allxyqyrsnum1 += Integer.valueOf(xyqyrsnum1);
						allxyjyrsnum1 += Integer.valueOf(xyjyrsnum1);
						allxybyrsnum2 += Integer.valueOf(xybyrsnum2);
						allxyqyrsnum2 += Integer.valueOf(xyqyrsnum2);
						allxyjyrsnum2 += Integer.valueOf(xyjyrsnum2);
						// 最后的总结,放到最后一个循环的最后
						if (j == ((xymc.length) - 1)) {
							// 第一年度所有学院签约率
							if (allxybyrsnum1 != 0) {
								allxyqyl1 = (dao.getOneRs("select to_char("
										+ allxyqyrsnum1 + "/" + allxybyrsnum1
										+ "*100,'999.99')||'%' aa from dual",
										new String[] {}, new String[] { "aa" }))[0];// 1年度全学院签约率
								if ("    .00%".equals(allxyqyl1)) {
									allxyqyl1 = "0.00%";
								}
							}
							// 第二年度所有学院签约率
							if (allxybyrsnum2 != 0) {
								allxyqyl2 = (dao.getOneRs("select to_char("
										+ allxyqyrsnum2 + "/" + allxybyrsnum2
										+ "*100,'999.99')||'%' aa from dual",
										new String[] {}, new String[] { "aa" }))[0];// 2年度全学院签约率
								if ("    .00%".equals(allxyqyl2)) {
									allxyqyl2 = "0.00%";
								}
							}
							// 第一年度所有学院就业率
							if (allxybyrsnum1 != 0) {
								allxyjyl1 = (dao.getOneRs("select to_char("
										+ allxyjyrsnum1 + "/" + allxybyrsnum1
										+ "*100,'999.99')||'%' aa from dual",
										new String[] {}, new String[] { "aa" }))[0];// 1年度全学院就业率
								if ("    .00%".equals(allxyjyl1)) {
									allxyjyl1 = "0.00%";
								}
							}
							// 第二年度所有学院就业率
							if (allxybyrsnum2 != 0) {
								allxyjyl2 = (dao.getOneRs("select to_char("
										+ allxyjyrsnum2 + "/" + allxybyrsnum2
										+ "*100,'999.99')||'%' aa from dual",
										new String[] {}, new String[] { "aa" }))[0];// 2年度全学院就业率
								if ("    .00%".equals(allxyjyl2)) {
									allxyjyl2 = "0.00%";
								}
							}

							String strallxybyrsnum1 = String
									.valueOf(allxybyrsnum1);
							String strallxyqyrsnum1 = String
									.valueOf(allxyqyrsnum1);
							String strallxyjyrsnum1 = String
									.valueOf(allxyjyrsnum1);
							String[] allxytjvalues = { "", "总计",
									strallxybyrsnum1, strallxyqyrsnum1,
									strallxyjyrsnum1, allxyqyl1, allxyqyl2,
									allxyjyl1, allxyjyl2 };
							rs.add(allxytjvalues);

						}
					}
				}
				list.add(rs);
			}
			request.setAttribute("top", top);// 发送表头
			request.setAttribute("list", list);
		}
		request.setAttribute("rs1", map);
		return mapping.findForward("success");
	}

	// 学生就业信息
	private ActionForward stuinfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String sql = "";
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		String rsNum = "0";
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();

		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		if ("admin".equalsIgnoreCase(userType)
				|| "xx".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xx");
		} else if ("xy".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xy");
		}

		// 单个删除
		if (null != pkValue) {
			pkValue = pkValue.replace(" ", "+");
		}

		if ("del".equalsIgnoreCase(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("jygl_xsjyqkb", "rowid", pkValue,
					request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		// 批量删除
		if ("delall".equalsIgnoreCase(doType2)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				pkstringI = pkstring.split("!!#!!");
			}

			for (int i = 1; i < pkstringI.length; i++) {
				String whichrid = pkstringI[i];
				boolean judge = false;
				judge = StandardOperation.delete("jygl_xsjyqkb", "rowid",
						whichrid, request);
				if (judge) {
					request.setAttribute("delall", "ok");
				} else {
					request.setAttribute("delall", "no");
				}
			}
		}

		// 全部清空
		if ("delallinfo".equalsIgnoreCase(doType2)) {

			boolean judge = false;

			judge = dao.runUpdateTab("truncate table jygl_xsjyqkb");

			if (judge) {
				request.setAttribute("delallinfo", "ok");
			} else {
				request.setAttribute("delallinfo", "no");
			}

		}

		String xh = DealString.toGBK(request.getParameter("xh")); // 学号
		String xm = DealString.toGBK(request.getParameter("xm")); // 姓名
		String xn = DealString.toGBK(request.getParameter("xn")); // 学年
		String nj = DealString.toGBK(request.getParameter("nj")); // 年级
		String xydm = DealString.toGBK(request.getParameter("xydm")); // 学院名称
		String zydm = DealString.toGBK(request.getParameter("zydm")); // 专业名称
		String bjdm = DealString.toGBK(request.getParameter("bjdm")); // 班级名称
		String dwmc = DealString.toGBK(request.getParameter("dwmc")); // 单位名称
		String gwxz = DealString.toGBK(request.getParameter("gwxz")); // 岗位性质
		String sfjy = DealString.toGBK(request.getParameter("sfjy")); // 是否就业
		String sfqy = DealString.toGBK(request.getParameter("sfqy")); // 是否签约
		if ("query".equals(act)) {
			map.put("xh", xh);
			map.put("xm", xm);
			map.put("xn", xn);
			map.put("nj", nj);
			map.put("xydm", xydm);
			map.put("zydm", zydm);
			map.put("bjdm", bjdm);
			map.put("dwmc", dwmc);
			map.put("gwxz", gwxz);
			map.put("sfjy", sfjy);
			map.put("sfqy", sfqy);
		}

		StringBuffer query = new StringBuffer();

		if (!("".equals(xh))) {
			query.append(" and xh like '%");
			query.append(xh);
			query.append("%'");
		}
		if (!("".equals(xm))) {
			query.append(" and xm like '%");
			query.append(xm);
			query.append("%'");
		}
		if (!("".equals(xn))) {
			query.append(" and xn= '");
			query.append(xn);
			query.append("'");
		}
		if (!("".equals(nj))) {
			query.append(" and nj='");
			query.append(nj);
			query.append("'");
		}
		if (!("".equals(xydm))) {
			query.append(" and xydm= '");
			query.append(xydm);
			query.append("'");
		}
		if (!("".equals(zydm))) {
			query.append(" and zydm='");
			query.append(zydm);
			query.append("'");
		}
		if (!("".equals(bjdm))) {
			query.append(" and bjdm='");
			query.append(bjdm);
			query.append("'");
		}
		if (!("".equals(dwmc))) {
			query.append(" and dwmc like '%");
			query.append(dwmc);
			query.append("%'");
		}
		if (!("".equals(gwxz))) {
			query.append(" and gwxz='");
			query.append(gwxz);
			query.append("'");
		}
		if (!("".equals(sfjy))) {
			query.append(" and sfjy='");
			query.append(sfjy);
			query.append("'");
		}
		if (!("".equals(sfqy))) {
			query.append(" and sfqy='");
			query.append(sfqy);
			query.append("'");
		}

		String query1 = query.toString();

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from view_jygl_xsjyqkb a where 1=1 "
				+ query1;

		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// 就业信息
		sql = "select * from (select a.*,rownum r from (select distinct a.rid,a.xh,a.xm,a.xb,a.dwmc,a.sfjy,a.sfqy,a.djsj from view_jygl_xsjyqkb a where 1=1 "
				+ query1
				+ " order by djsj desc) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " order by djsj desc";

		colList = new String[] { "rid", "xh", "xm", "xb", "dwmc", "sfjy",
				"sfqy", "djsj" };

		if ("query".equals(act)) {
			rs = dao.getArrayList2(sql, new String[] {}, colList);
		}
		sql = "select count(*) from view_jygl_xsjyqkb where 1=1 " + query1;
		int rsNuminfo = dao.getOneRsint(sql);
		rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);
		colListCN = dao.getColumnNameCN(colList, "view_jygl_xsjyqkb");
		List topTr = dao.arrayToList(colList, colListCN);

		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		request.setAttribute("topTr", topTr);
		request.setAttribute("realTable", "jygl_xsjyqkb");
		request.setAttribute("querry", query1);
		request.setAttribute("njList", dao.getNjList());// 发送年级列表
		request.setAttribute("xyList", dao.getXyList());// 发送学院列表
		request.setAttribute("zyList", dao.getZyList(xydm));// 发送专业列表
		request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));// 发送专业列表
		return mapping.findForward("success");

	}

	// 学生基本信息查询（针对宁波职业）
	private ActionForward stuinfoquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String[] colList = null;
		String[] colListCN = null;
		String rsNum = "0";
		String sql = "";
		String act = request.getParameter("act");
		String xxdm = session.getAttribute("xxdm").toString();

		String userType = session.getAttribute("userType").toString();
		if ("admin".equalsIgnoreCase(userType)
				|| "xx".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xx");
		} else if ("xy".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xy");
		}

		String xh = DealString.toGBK(request.getParameter("xh")); // 学号
		String xm = DealString.toGBK(request.getParameter("xm")); // 姓名
		String xn = DealString.toGBK(request.getParameter("xn")); // 学年
		String xq = DealString.toGBK(request.getParameter("xq")); // 学期
		String xydm = DealString.toGBK(request.getParameter("xydm")); // 学院名称
		String zydm = DealString.toGBK(request.getParameter("zydm")); // 专业名称
		String bjdm = DealString.toGBK(request.getParameter("bjdm")); // 班级名称

		if ("query".equals(act)) {
			map.put("xh", xh);
			map.put("xm", xm);
			map.put("xn", xn);
			map.put("xq", xq);
			map.put("xydm", xydm);
			map.put("zydm", zydm);
			map.put("bjdm", bjdm);

		}

		StringBuffer query = new StringBuffer();

		if (!("".equals(xh))) {
			query.append(" and xh like '%");
			query.append(xh);
			query.append("%'");
		}
		if (!("".equals(xm))) {
			query.append(" and xm like '%");
			query.append(xm);
			query.append("%'");
		}
		if (!("".equals(xn))) {
			query.append(" and xn= '");
			query.append(xn);
			query.append("'");
		}
		if (!("".equals(xq))) {
			query.append(" and xq= '");
			query.append(xq);
			query.append("'");
		}
		if (!("".equals(xydm))) {
			query.append(" and xydm= '");
			query.append(xydm);
			query.append("'");
		}
		if (!("".equals(zydm))) {
			query.append(" and zydm= '");
			query.append(zydm);
			query.append("'");
		}
		if (!("".equals(bjdm))) {
			query.append(" and bjdm= '");
			query.append(bjdm);
			query.append("'");
		}
		String query1 = query.toString();

		CommanForm dataSearchForm = (CommanForm) form;
		if ("10863".equals(xxdm)) {
			sql = "select count(*) count from view_dgsxxssxjl a where 1=1 "
					+ query1;
		} else {
			sql = "select count(*) count from view_xsxxb a where 1=1 " + query1;
		}

		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// 学生信息
		if ("10863".equals(xxdm)) {
			sql = "select * from (select a.*,rownum r from (select distinct a.rowid rid,a.xh,a.xm,a.xn,a.xq,a.sfdlh from view_dgsxxssxjl a where 1=1 "
					+ query1
					+ " order by xh ) a ) a where a.r>"
					+ dataSearchForm.getPages().getStart()
					+ " and a.r<="
					+ (dataSearchForm.getPages().getStart() + dataSearchForm
							.getPages().getPageSize()) + " order by xh";
		} else {
			sql = "select * from (select a.*,rownum r from (select distinct a.xh,a.xm,xb,nj,(select distinct xymc from view_njxyzybj b where a.xydm=b.xydm) xydm,(select distinct zymc from view_njxyzybj b where a.zydm=b.zydm) zydm,(select distinct bjmc from view_njxyzybj b where a.bjdm=b.bjdm) bjdm from view_xsxxb a where 1=1 "
					+ query1
					+ " order by xh ) a ) a where a.r>"
					+ dataSearchForm.getPages().getStart()
					+ " and a.r<="
					+ (dataSearchForm.getPages().getStart() + dataSearchForm
							.getPages().getPageSize()) + " order by xh";
		}
		if ("10863".equals(xxdm)) {
			colList = new String[] { "rid", "xh", "xm", "xn", "xq", "sfdlh" };
		} else {
			colList = new String[] { "xh", "xm", "xb", "nj", "xydm", "zydm",
					"bjdm" };
		}

		if ("query".equals(act)) {
			rs = dao.getArrayList2(sql, new String[] {}, colList);
		}
		sql = "select count(*) from view_dgsxxssxjl where 1=1 " + query1;
		int rsNuminfo = dao.getOneRsint(sql);
		rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);
		List topTr;
		// String[] colListEN;
		if ("10863".equals(xxdm)) {
			colListCN = dao.getColumnNameCN(colList, "view_dgsxxssxjl");
		} else {
			colListCN = new String[] { "学号", "姓名", "性别", "年级", "学院名称", "专业名称",
					"班级名称" };
			colList = new String[] { "xh", "xm", "xb", "nj", "xydm", "zydm",
					"bjdm" };
		}
		topTr = dao.arrayToList(colList, colListCN);

		request.setAttribute("rs1", map);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("xnList", dao.getXnndList());
		request.setAttribute("xqList", dao.getXqList());
		request.setAttribute("xyList", dao.getXyList());// 发送学院列表
		request.setAttribute("zyList", dao.getZyList(xydm));// 发送专业列表
		request.setAttribute("bjList", dao.getBjList(xydm, zydm));// 发送专业列表
		return mapping.findForward("success");

	}

	// 单位信息增加
	private ActionForward dwxxinfoInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		request.setAttribute("rs", map);
		String act = request.getParameter("act");
		String dwupdate = request.getParameter("dwupdate");
		String sendgo = request.getParameter("sendgo");
		DAO dao = DAO.getInstance();

		String dwxzcx = "select dwxzdm,dwxz from dmk_dwxz2"; // 单位性质代码2
		List dwxzdm2List = dao.getList(dwxzcx, new String[] {}, new String[] {
				"dwxzdm", "dwxz" });
		request.setAttribute("dwxxdm", dwxzdm2List);

		String zgdwmc = "select zgbmdm,zgbm from dmk_zgbm"; // 生源地主管单位名称
		List sydzgbmList = dao.getList(zgdwmc, new String[] {}, new String[] {
				"zgbmdm", "zgbm" });
		request.setAttribute("sydzgbmList", sydzgbmList);

		String hyfl = "select hyfldm,hyfl from dmk_hyfl"; // 行业分类
		List hyflList = dao.getList(hyfl, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);

		StuInfoDAO stuDao = new StuInfoDAO();
		request.setAttribute("shiList", stuDao.getShiList("").get("shiList"));
		request.setAttribute("xianList", stuDao.getShiList("").get("xianList"));
		request.setAttribute("ssList", stuDao.getSsList());

		if ("go".equalsIgnoreCase(act)) {
			String pkValue = DealString.toGBK(request.getParameter("pkValue"));
			String sql = "select * from dwxxb where dwid='" + pkValue + "'";
			String[] colList = dao
					.getColumnName("select * from dwxxb a where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				if (!"dwupdate".equalsIgnoreCase(dwupdate)) {
					String shen = map.get("szdqsh");
					String shi = map.get("szdqsi");
					// 转换省代码
					String sqldzsh = "select * from dmk_sydq where sydqdm=?";
					String[] rs5 = dao.getOneRs(sqldzsh, new String[] { shen },
							new String[] { "sydq" });
					if (null != rs5) {
						map.put("szdqsh", rs5[0]);
					}
					// 转换市代码
					String sqlshidw = "select qxmc from dmk_qx where qxdm=?";
					String[] rs6 = dao.getOneRs(sqlshidw, new String[] { shi },
							new String[] { "qxmc" });
					if (null != rs6) {
						map.put("szdqsi", rs6[0]);
					}
				}
				map.put("gsmc", map.get("dwmc"));
				map.put("gswz", map.get("dwzy"));
			}
			// if (null != map.get("xh") && !"".equalsIgnoreCase(map.get("xh")))
			// {
			// String xh = map.get("xh");
			// sql =
			// "select xm,xb,nj,xymc,zymc,bjmc from view_xsjbxx where xh=?";
			// String[] stuinfo2 = dao
			// .getOneRs(sql, new String[] { xh }, new String[] {
			// "xm", "xb", "nj", "xymc", "zymc", "bjmc" });
			// if (null != stuinfo2) {
			// map.put("xm", stuinfo2[0]);
			// map.put("xb", stuinfo2[1]);
			// map.put("nj", stuinfo2[2]);
			// map.put("xymc", stuinfo2[3]);
			// map.put("zymc", stuinfo2[4]);
			// map.put("bjmc", stuinfo2[5]);
			// }
			// }
			request.setAttribute("rs", map);
			if ("dwupdate".equalsIgnoreCase(dwupdate)) {
				return mapping.findForward("successupdate");
			} else if ("sendgo".equalsIgnoreCase(sendgo)) {
				return mapping.findForward("sendgo");
			} else {
				return mapping.findForward("successgo");
			}
		}

		if ("save".equalsIgnoreCase(act)) {
			String dwid = request.getParameter("dwid");
			String dwmc = DealString.toGBK(request.getParameter("dwmc"));// 单位名称
			String dwxz = DealString.toGBK(request.getParameter("dwxz"));// 单位性质
			String dwlb = DealString.toGBK(request.getParameter("dwlb"));// 单位类别
			String zgbm = DealString.toGBK(request.getParameter("zgbm"));// 主管部门
			String sshy = DealString.toGBK(request.getParameter("hyfl"));// 所属行业
			String szdqsh = DealString.toGBK(request.getParameter("szdqsh"));// 所在地区省
			String szdqsi = DealString.toGBK(request.getParameter("szdqsi"));// 所在地区市
			String zcsj = DealString.toGBK(request.getParameter("clrq"));// 成立日期
			String dz = DealString.toGBK(request.getParameter("dz"));// 通讯地址
			String yb = DealString.toGBK(request.getParameter("yb"));// 邮政编码
			String lxbm = DealString.toGBK(request.getParameter("lxbm"));// 联系部门
			String lxr = DealString.toGBK(request.getParameter("lxr"));// 联系人
			String lxdh = DealString.toGBK(request.getParameter("lxdh"));// 联系电话
			String cz = DealString.toGBK(request.getParameter("cz"));// 单位传真
			String email = DealString.toGBK(request.getParameter("email"));// 邮箱
			String dwzy = DealString.toGBK(request.getParameter("dwzy"));// 单位主页
			String dazjdz = DealString.toGBK(request.getParameter("dazjdz"));// 档案转寄地址
			String dayzbm = DealString.toGBK(request.getParameter("dayzbm"));// 档案邮政编码
			String dwjj = DealString.toGBK(request.getParameter("dwjj"));// 单位介绍

			// String djsj = (dao
			// .getOneRs(
			// "select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", //
			// 登记时间
			// // //
			// // 取至数据库临时表
			// new String[] {}, new String[] { "sdate" }))[0];

			boolean judge = false;
			if ("dwupdate".equalsIgnoreCase(dwupdate)) {
				judge = StandardOperation.update("dwxxb", new String[] {
						"dwmc", "dwxz", "dwlb", "zgbm", "hyfl", "szdqsh",
						"szdqsi", "clrq", "dz", "yb", "lxbm", "lxr", "lxdh",
						"cz", "email", "dwzy", "dazjdz", "dayzbm", "dwjj" },
						new String[] { dwmc, dwxz, dwlb, zgbm, sshy, szdqsh,
								szdqsi, zcsj, dz, yb, lxbm, lxr, lxdh, cz,
								email, dwzy, dazjdz, dayzbm, dwjj }, "dwid",
						dwid, request);
				if (judge) {
					request.setAttribute("update", "ok");
				} else {
					request.setAttribute("update", "no");
				}
				return mapping.findForward("successupdate");
			} else {
				judge = StandardOperation.insert("dwxxb", new String[] {
						"dwmc", "dwxz", "dwlb", "zgbm", "hyfl", "szdqsh",
						"szdqsi", "clrq", "dz", "yb", "lxbm", "lxr", "lxdh",
						"cz", "email", "dwzy", "dazjdz", "dayzbm", "dwjj" },
						new String[] { dwmc, dwxz, dwlb, zgbm, sshy, szdqsh,
								szdqsi, zcsj, dz, yb, lxbm, lxr, lxdh, cz,
								email, dwzy, dazjdz, dayzbm, dwjj }, request);

				if (judge) {
					request.setAttribute("save", "ok");
				} else {
					request.setAttribute("save", "no");
				}
			}
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");
	}

	// 学生就业信息增加
	private ActionForward stuinfoInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		request.setAttribute("rs", map);
		String act = request.getParameter("act");
		DAO dao = DAO.getInstance();
		String xxdm = request.getSession().getAttribute("xxdm").toString();
		String sql = "";
		String[] colList = null;

		if ("go".equalsIgnoreCase(act)) {
			String pkValue = DealString.toGBK(request.getParameter("pkValue"));
			if ("10863".equals(xxdm)) {
				sql = "select * from view_dgsxxssxjl where rownum='" + pkValue
						+ "'";
				colList = dao
						.getColumnName("select * from view_dgsxxssxjl a where 1=2"); // 返回列名数组
			} else {
				sql = "select distinct a.xh,a.xm,xb,nj,(select distinct xymc from view_njxyzybj b where a.xydm=b.xydm) xymc,(select distinct zymc from view_njxyzybj b where a.zydm=b.zydm) zymc,(select distinct bjmc from view_njxyzybj b where a.bjdm=b.bjdm) bjmc from view_xsxxb a where xh='"
						+ pkValue + "'";
				colList = new String[] { "xh", "xm", "xb", "nj", "xymc",
						"zymc", "bjmc" };
			}
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				map.put("dwdh", map.get("dwlxdh"));
				map.put("gwxz", map.get("xssxgwxz"));
				map.put("gwmc", map.get("xssxgw"));
			}
			if (null != map.get("xh") && !"".equalsIgnoreCase(map.get("xh"))) {
				String xh = map.get("xh");
				sql = "select xm,xb,nj,xymc,zymc,bjmc from view_xsjbxx where xh=?";
				String[] stuinfo2 = dao
						.getOneRs(sql, new String[] { xh }, new String[] {
								"xm", "xb", "nj", "xymc", "zymc", "bjmc" });
				if (null != stuinfo2) {
					map.put("xm", stuinfo2[0]);
					map.put("xb", stuinfo2[1]);
					map.put("nj", stuinfo2[2]);
					map.put("xymc", stuinfo2[3]);
					map.put("zymc", stuinfo2[4]);
					map.put("bjmc", stuinfo2[5]);
				}
			}
		}

		if ("save".equalsIgnoreCase(act)) {
			String xh = DealString.toGBK(request.getParameter("xh")); // 学号
			String dwzm = DealString.toGBK(request.getParameter("dwzm")); // 单位证明
			String zydkqk = DealString.toGBK(request.getParameter("zydkqk")); // 专业对口情况
			String jybm = DealString.toGBK(request.getParameter("jybm")); // 就业部门
			String gwxz = DealString.toGBK(request.getParameter("gwxz")); // 岗位性质
			String gwmc = DealString.toGBK(request.getParameter("gwmc")); // 岗位名称
			String gzqk = DealString.toGBK(request.getParameter("gzqk")); // 工资情况
			String gzd = DealString.toGBK(request.getParameter("gzd")); // 工作地
			String sfjy = DealString.toGBK(request.getParameter("sfjy")); // 是否就业
			String sfqy = DealString.toGBK(request.getParameter("sfqy")); // 是否签约
			String dwmc = DealString.toGBK(request.getParameter("dwmc")); // 单位名称
			String dwxz = DealString.toGBK(request.getParameter("dwxz")); // 单位性质
			String dwdz = DealString.toGBK(request.getParameter("dwdz")); // 单位地址
			String dwlxr = DealString.toGBK(request.getParameter("dwlxr")); // 单位联系人
			String dwdh = DealString.toGBK(request.getParameter("dwdh")); // 单位电话
			String djsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 登记时间
							// //
							// 取至数据库临时表
							new String[] {}, new String[] { "sdate" }))[0];

			boolean judge = false;

			judge = StandardOperation.insert("jygl_xsjyqkb", new String[] {
					"xh", "dwzm", "zydkqk", "jybm", "gwxz", "gwmc", "gzqk",
					"gzd", "sfjy", "sfqy", "dwmc", "dwxz", "dwdz", "dwlxr",
					"dwdh", "djsj" }, new String[] { xh, dwzm, zydkqk, jybm,
					gwxz, gwmc, gzqk, gzd, sfjy, sfqy, dwmc, dwxz, dwdz, dwlxr,
					dwdh, djsj }, request);

			if (judge) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "no");
			}

		}

		request.setAttribute("rs", map);

		return mapping.findForward("success");

	}

	// 学生就业信息修改
	private ActionForward stuinfoUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		String sql = "";

		sql = "select dm,mc from dm_gb_zhrmghgxzqhdm"; // 单位地区
		List gzdList = dao.getList(sql, new String[] {}, new String[] { "dm",
				"mc" });
		request.setAttribute("gzdList", gzdList);

		if (null != pkValue && !"".equalsIgnoreCase(pkValue)) {
			pkValue = pkValue.replace(" ", "+");
		}

		String dwmc = DealString.toGBK(request.getParameter("dwmc")); // 单位名称
		String dwxz = DealString.toGBK(request.getParameter("dwxz")); // 单位性质
		String dwdz = DealString.toGBK(request.getParameter("dwdz")); // 单位地址
		String dwlxr = DealString.toGBK(request.getParameter("dwlxr")); // 单位联系人
		String dwdh = DealString.toGBK(request.getParameter("dwdh")); // 单位电话
		String dwzm = DealString.toGBK(request.getParameter("dwzm")); // 单位证明
		String zydkqk = DealString.toGBK(request.getParameter("zydkqk")); // 专业对口情况
		String jybm = DealString.toGBK(request.getParameter("jybm")); // 就业部门
		String gwxz = DealString.toGBK(request.getParameter("gwxz")); // 岗位性质
		String gwmc = DealString.toGBK(request.getParameter("gwmc")); // 岗位名称
		String gzqk = DealString.toGBK(request.getParameter("gzqk")); // 工资情况
		String gzd = DealString.toGBK(request.getParameter("gzd")); // 工作地
		String sfjy = DealString.toGBK(request.getParameter("sfjy")); // 是否就业
		String sfqy = DealString.toGBK(request.getParameter("sfqy")); // 是否签约
		String djsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 登记时间
				// //
				// 取至数据库临时表
				new String[] {}, new String[] { "sdate" }))[0];

		if ("update".equalsIgnoreCase(doType)) {

			boolean judge = false;
			judge = StandardOperation.update("jygl_xsjyqkb", new String[] {
					"dwmc", "dwxz", "dwdz", "dwlxr", "dwdh", "dwzm", "zydkqk",
					"jybm", "gwxz", "gwmc", "gzqk", "gzd", "sfjy", "sfqy",
					"djsj" },
					new String[] { dwmc, dwxz, dwdz, dwlxr, dwdh, dwzm, zydkqk,
							jybm, gwxz, gwmc, gzqk, gzd, sfjy, sfqy, djsj },
					"rowid", pkValue, request);
			if (judge) {
				request.setAttribute("update", "ok");
			} else {
				request.setAttribute("update", "no");
			}

		}

		sql = "select * from view_jygl_xsjyqkb where rid='" + pkValue + "'";
		String[] colList = dao
				.getColumnName("select * from view_jygl_xsjyqkb where 1=2"); // 返回列名数组
		String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
		if (stuinfo != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
			}
			djsj = map.get("djsj");
			if (null != djsj && !"".equalsIgnoreCase(djsj)) {
				map.put("djsj", dao.doForTime(djsj));
			}
		}

		request.setAttribute("rs", map);

		return mapping.findForward("success");

	}

	// 学生就业信息详细查看
	private ActionForward stuinfomorequery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		DAO dao = DAO.getInstance();

		if (null != pkValue && !"".equalsIgnoreCase(pkValue)) {
			pkValue = pkValue.replace(" ", "+");
		}

		String sql = "select * from view_jygl_xsjyqkb where rid='" + pkValue
				+ "'";
		String[] colList = dao
				.getColumnName("select * from view_jygl_xsjyqkb where 1=2"); // 返回列名数组
		String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
		if (stuinfo != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
			}
			String djsj = map.get("djsj");
			if (null != djsj && !"".equalsIgnoreCase(djsj)) {
				map.put("djsj", dao.doForTime(djsj));
			}
		}
		request.setAttribute("rs", map);
		return mapping.findForward("success");

	}

	// 学生就业统计
	private ActionForward xsjytj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String xydm = "";
		String zydm = "";
		String bjdm = "";
		String rsNum = "";
		String sql = "";
		String act = request.getParameter("act");

		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> rs1 = new HashMap<String, String>();
		String tjlx = DealString.toGBK(request.getParameter("tjlx"));

		if ("班级就业情况汇总统计表".equalsIgnoreCase(tjlx)) {
			xydm = request.getParameter("xydm");
			zydm = request.getParameter("zydm");
			bjdm = request.getParameter("bjdm");
			request.setAttribute("which", "班级就业情况汇总统计表");

			StringBuffer query = new StringBuffer();

			query.append(" where 1=1 ");

			if (!("".equals(xydm))) {
				query.append(" and xydm = '");
				query.append(xydm);
				query.append("'");
			}
			if (!("".equals(zydm))) {
				query.append(" and zydm = '");
				query.append(zydm);
				query.append("'");
			}
			if (!("".equals(bjdm))) {
				query.append(" and bjdm = '");
				query.append(bjdm);
				query.append("'");
			}

			String querry = query.toString();

			sql = "select rownum , a.xymc,a.xydm,a.zydm,a.bjdm, a.bjmc ,a.bjrs ,(select b.zgh from fdybjb b where a.bjdm=b.bjdm) bzr,d.lsdwrs ,b.qyrs ,replace((select to_char(c.jyrs/a.bjrs*100,'999.99')||'%' from dual),'    ','0') jyl,replace((select to_char(b.qyrs/a.bjrs*100,'999.99')||'%' from dual),'    ','0') qyl from "
					+ "(select xymc,xydm,zydm,bjdm,bjmc,count(*) bjrs from view_xsjbxx "
					+ querry
					+ " group by bjdm,bjmc,xymc,xydm,zydm) a "
					+ "left join "
					+ "(select bjdm,count(*) qyrs from view_jygl_xsjyqkb where sfqy='是' group by bjdm) b "
					+ "on a.bjdm=b.bjdm "
					+ "left join "
					+ "(select bjdm,count(*) jyrs from view_jygl_xsjyqkb where sfjy='是' group by bjdm) c "
					+ "on a.bjdm=c.bjdm "
					+ "left join "
					+ "(select bjdm,count(*) lsdwrs from view_jygl_xsjyqkb where dwzm='有' group by bjdm) d "
					+ "on a.bjdm=d.bjdm ";

			String[] colList = { "rownum", "xymc", "bjmc", "bjrs", "bzr",
					"lsdwrs", "qyrs", "jyl", "qyl" }; // 表头英文字段
			String[] colListCN = { "序号", "学院", "班级", "班级人数", "班主任", "落实单位人数",
					"签约人数", "就业率", "签约率" };// 表头中文字段
			ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			int len = (colList.length > colListCN.length) ? colListCN.length
					: colList.length;
			HashMap<String, String> map = null;
			for (int i = 0; i < len; i++) {
				map = new HashMap<String, String>();
				map.put("en", colList[i]);
				map.put("cn", colListCN[i]);
				list.add(map);
			}

			if ("go".equalsIgnoreCase(act)) {
				rs = dao.getArrayList2(sql, new String[] {}, colList);
				int rsNuminfo = rs.size();
				rsNum = String.valueOf(rsNuminfo);
				request.setAttribute("rsNum", rsNum);
				rs1.put("xydm", xydm);
				rs1.put("zydm", zydm);
				rs1.put("bjdm", bjdm);

			}
			request.setAttribute("topTr", list);
			request.setAttribute("querry", querry);

		}
		if ("专业就业情况汇总统计表".equalsIgnoreCase(tjlx)) {
			xydm = request.getParameter("xydm");
			zydm = request.getParameter("zydm");
			request.setAttribute("which", "专业就业情况汇总统计表");

			StringBuffer query = new StringBuffer();

			query.append(" where 1=1 ");

			if (!("".equals(xydm))) {
				query.append(" and xydm = '");
				query.append(xydm);
				query.append("'");
			}
			if (!("".equals(zydm))) {
				query.append(" and zydm = '");
				query.append(zydm);
				query.append("'");
			}

			String querry = query.toString();

			sql = "select rownum, a.xymc,a.xydm,a.zymc,a.zydm,a.zyrs,d.lsdwrs,b.qyrs,replace((select to_char(c.jyrs/a.zyrs*100,'999.99')||'%' from dual),'    ','0') jyl,replace((select to_char(b.qyrs/a.zyrs*100,'999.99')||'%' from dual),'    ','0') qyl from "
					+ "(select xymc,xydm,zydm,zymc,count(*) zyrs from view_xsjbxx group by zydm,zymc,xymc,xydm) a "
					+ "left join "
					+ "(select zydm,count(*) qyrs from view_jygl_xsjyqkb where sfqy='是' group by zydm) b "
					+ "on a.zydm=b.zydm "
					+ "left join "
					+ "(select zydm,count(*) jyrs from view_jygl_xsjyqkb where sfjy='是' group by zydm) c "
					+ "on a.zydm=c.zydm "
					+ "left join "
					+ "(select zydm,count(*) lsdwrs from view_jygl_xsjyqkb where dwzm='有' group by zydm) d "
					+ "on a.zydm=d.zydm " + querry;

			String[] colList = { "rownum", "xymc", "zymc", "zyrs", "lsdwrs",
					"qyrs", "jyl", "qyl" }; // 表头英文字段
			String[] colListCN = { "序号", "学院", "专业", "专业人数", "落实单位人数", "签约人数",
					"就业率", "签约率" };// 表头中文字段
			ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			int len = (colList.length > colListCN.length) ? colListCN.length
					: colList.length;
			HashMap<String, String> map = null;
			for (int i = 0; i < len; i++) {
				map = new HashMap<String, String>();
				map.put("en", colList[i]);
				map.put("cn", colListCN[i]);
				list.add(map);
			}

			if ("go".equalsIgnoreCase(act)) {
				rs = dao.getArrayList2(sql, new String[] {}, colList);
				int rsNuminfo = rs.size();
				rsNum = String.valueOf(rsNuminfo);
				request.setAttribute("rsNum", rsNum);
				rs1.put("xydm", xydm);
				rs1.put("zydm", zydm);
			}
			request.setAttribute("topTr", list);
			request.setAttribute("querry", querry);
		}
		if ("分院就业情况汇总统计表".equalsIgnoreCase(tjlx)) {
			xydm = request.getParameter("xydm");
			request.setAttribute("which", "分院就业情况汇总统计表");

			StringBuffer query = new StringBuffer();

			query.append(" where 1=1 ");

			if (!("".equals(xydm))) {
				query.append(" and xydm = '");
				query.append(xydm);
				query.append("'");
			}

			String querry = query.toString();

			sql = "select rownum, a.xymc,a.xydm,a.xyrs,d.lsdwrs,b.qyrs,replace((select to_char(c.jyrs/a.xyrs*100,'999.99')||'%' from dual),'    ','0') jyl,replace((select to_char(b.qyrs/a.xyrs*100,'999.99')||'%' from dual),'    ','0') qyl from "
					+ "(select xymc,xydm,count(*) xyrs from view_xsjbxx group by xymc,xydm) a "
					+ "left join "
					+ "(select xydm,count(*) qyrs from view_jygl_xsjyqkb where sfqy='是' group by xydm) b "
					+ "on a.xydm=b.xydm "
					+ "left join "
					+ "(select xydm,count(*) jyrs from view_jygl_xsjyqkb where sfjy='是' group by xydm) c "
					+ "on a.xydm=c.xydm "
					+ "left join "
					+ "(select xydm,count(*) lsdwrs from view_jygl_xsjyqkb where dwzm='有' group by xydm) d "
					+ "on a.xydm=d.xydm " + querry;

			String[] colList = { "rownum", "xymc", "xyrs", "lsdwrs", "qyrs",
					"jyl", "qyl" }; // 表头英文字段
			String[] colListCN = { "序号", "学院", "学院人数", "落实单位人数", "签约人数", "就业率",
					"签约率" };// 表头中文字段
			ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			int len = (colList.length > colListCN.length) ? colListCN.length
					: colList.length;
			HashMap<String, String> map = null;
			for (int i = 0; i < len; i++) {
				map = new HashMap<String, String>();
				map.put("en", colList[i]);
				map.put("cn", colListCN[i]);
				list.add(map);
			}

			if ("go".equalsIgnoreCase(act)) {
				rs = dao.getArrayList2(sql, new String[] {}, colList);
				int rsNuminfo = rs.size();
				rsNum = String.valueOf(rsNuminfo);
				request.setAttribute("rsNum", rsNum);
				rs1.put("xydm", xydm);
			}
			request.setAttribute("topTr", list);
			request.setAttribute("querry", querry);
		}
		if ("就业情况汇总统计总表".equalsIgnoreCase(tjlx)) {
			request.setAttribute("which", "就业情况汇总统计总表");

			sql = "select a.zrs,d.lsdwrs,c.qyrs, replace((select to_char(c.qyrs/a.zrs*100,'999.99')||'%' from dual),'    ','0') qyl, b.jyrs,replace((select to_char(b.jyrs/a.zrs*100,'999.99')||'%' from dual),'    ','0') jyl from "
					+ "(select count(*) zrs from view_xsjbxx ) a , "
					+ "(select count(*) jyrs from view_jygl_xsjyqkb where sfjy='是') b, "
					+ "(select count(*) qyrs from view_jygl_xsjyqkb where sfqy='是') c, "
					+ "(select count(*) lsdwrs from view_jygl_xsjyqkb where dwzm='有') d";

			String[] colList = { "zrs", "lsdwrs", "qyrs", "qyl", "jyrs", "jyl" }; // 表头英文字段
			String[] colListCN = { "总人数", "落实单位人数", "签约人数", "签约率", "就业人数",
					"就业率" };// 表头中文字段
			ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			int len = (colList.length > colListCN.length) ? colListCN.length
					: colList.length;
			HashMap<String, String> map = null;
			for (int i = 0; i < len; i++) {
				map = new HashMap<String, String>();
				map.put("en", colList[i]);
				map.put("cn", colListCN[i]);
				list.add(map);
			}

			if ("go".equalsIgnoreCase(act)) {
				rs = dao.getArrayList2(sql, new String[] {}, colList);
				int rsNuminfo = rs.size();
				rsNum = String.valueOf(rsNuminfo);
				request.setAttribute("rsNum", rsNum);
			}
			request.setAttribute("topTr", list);

		}
		if ("分班就业情况明细表".equalsIgnoreCase(tjlx)) {
			xydm = request.getParameter("xydm");
			zydm = request.getParameter("zydm");
			bjdm = request.getParameter("bjdm");
			request.setAttribute("which", "分班就业情况明细表");

			StringBuffer query = new StringBuffer();

			query.append(" where 1=1 ");

			if (!("".equals(xydm))) {
				query.append(" and xydm = '");
				query.append(xydm);
				query.append("'");
			}
			if (!("".equals(zydm))) {
				query.append(" and zydm = '");
				query.append(zydm);
				query.append("'");
			}
			if (!("".equals(bjdm))) {
				query.append(" and bjdm = '");
				query.append(bjdm);
				query.append("'");
			}

			String querry = query.toString();

			sql = "select rownum, a.xh,a.xm,a.xb,a.dwmc,a.dwdz,a.dwdh,a.sfjy,a.sfqy,a.djsj from view_jygl_xsjyqkb a "
					+ querry;

			String[] colList = { "rownum", "xh", "xm", "xb", "dwmc", "dwdz",
					"dwdh", "sfjy", "sfqy", "djsj" }; // 表头英文字段
			String[] colListCN = { "序号", "学号", "姓名", "性别", "单位名称", "单位地址",
					"单位电话", "是否就业", "是否签约", "登记时间" };// 表头中文字段
			ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			int len = (colList.length > colListCN.length) ? colListCN.length
					: colList.length;
			HashMap<String, String> map = null;
			for (int i = 0; i < len; i++) {
				map = new HashMap<String, String>();
				map.put("en", colList[i]);
				map.put("cn", colListCN[i]);
				list.add(map);
			}

			if ("go".equalsIgnoreCase(act)) {
				rs = dao.getArrayList2(sql, new String[] {}, colList);
				int rsNuminfo = rs.size();
				rsNum = String.valueOf(rsNuminfo);
				request.setAttribute("rsNum", rsNum);
				rs1.put("xydm", xydm);
				rs1.put("zydm", zydm);
				rs1.put("bjdm", bjdm);

			}
			request.setAttribute("topTr", list);
			request.setAttribute("querry", querry);

		}
		if ("专业对口情况统计表".equalsIgnoreCase(tjlx)) {
			xydm = request.getParameter("xydm");
			zydm = request.getParameter("zydm");
			request.setAttribute("which", "专业对口情况统计表");

			StringBuffer query = new StringBuffer();

			query.append(" where 1=1 ");

			if (!("".equals(xydm))) {
				query.append(" and xydm = '");
				query.append(xydm);
				query.append("'");
			}
			if (!("".equals(zydm))) {
				query.append(" and zydm = '");
				query.append(zydm);
				query.append("'");
			}

			String querry = query.toString();

			sql = "select rownum, a.xymc,a.xydm,a.zymc,a.zydm,a.zyrs,d.lsdwrs,b.zydkrs,replace((select to_char(b.zydkrs/a.zyrs*100,'999.99')||'%' from dual),'    ','0') dkl from "
					+ "(select xymc,xydm,zymc,zydm,count(*) zyrs from view_xsjbxx group by xymc,xydm,zymc,zydm) a "
					+ "left join "
					+ "(select zydm,count(*) zydkrs from view_jygl_xsjyqkb where zydkqk='完全对口' or zydkqk='基本对口' group by zydm) b "
					+ "on a.zydm=b.zydm "
					+ "left join "
					+ "(select zydm,count(*) lsdwrs from view_jygl_xsjyqkb where dwzm='有' group by zydm) d "
					+ "on a.zydm=d.zydm " + querry;

			String[] colList = { "rownum", "xymc", "zymc", "zyrs", "lsdwrs",
					"zydkrs", "dkl" }; // 表头英文字段
			String[] colListCN = { "序号", "学院", "专业", "专业人数", "落实单位人数",
					"专业对口人数", "对口率" };// 表头中文字段
			ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			int len = (colList.length > colListCN.length) ? colListCN.length
					: colList.length;
			HashMap<String, String> map = null;
			for (int i = 0; i < len; i++) {
				map = new HashMap<String, String>();
				map.put("en", colList[i]);
				map.put("cn", colListCN[i]);
				list.add(map);
			}

			if ("go".equalsIgnoreCase(act)) {
				rs = dao.getArrayList2(sql, new String[] {}, colList);
				int rsNuminfo = rs.size();
				rsNum = String.valueOf(rsNuminfo);
				request.setAttribute("rsNum", rsNum);
				rs1.put("xydm", xydm);
				rs1.put("zydm", zydm);
			}
			request.setAttribute("topTr", list);
			request.setAttribute("querry", querry);

		}
		if ("就业工资状况统计表".equalsIgnoreCase(tjlx)) {
			request.setAttribute("which", "就业工资状况统计表");

			sql = "select rownum ,b.gzqk,c.lsdwrs,replace((select to_char(c.lsdwrs/a.zrs*100,'999.99')||'%' from dual),'    ','0') bl from "
					+ "(select count(*) zrs from view_jygl_xsjyqkb ) a , "
					+ "(select gzqk ,count(*) zrs from view_jygl_xsjyqkb group by gzqk ) b "
					+ "left join "
					+ "(select gzqk ,count(*) lsdwrs from view_jygl_xsjyqkb where dwzm='有' group by gzqk) c "
					+ "on b.gzqk = c.gzqk ";

			String[] colList = { "rownum", "gzqk", "lsdwrs", "bl" }; // 表头英文字段
			String[] colListCN = { "序号", "工资", "落实单位人数", "比例" };// 表头中文字段
			ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			int len = (colList.length > colListCN.length) ? colListCN.length
					: colList.length;
			HashMap<String, String> map = null;
			for (int i = 0; i < len; i++) {
				map = new HashMap<String, String>();
				map.put("en", colList[i]);
				map.put("cn", colListCN[i]);
				list.add(map);
			}

			if ("go".equalsIgnoreCase(act)) {
				rs = dao.getArrayList2(sql, new String[] {}, colList);
				int rsNuminfo = rs.size();
				rsNum = String.valueOf(rsNuminfo);
				request.setAttribute("rsNum", rsNum);
			}
			request.setAttribute("topTr", list);
		}

		rs1.put("tjlx", tjlx);
		request.setAttribute("rs", rs);
		request.setAttribute("rs1", rs1);
		request.setAttribute("tjlx", tjlx);
		request.setAttribute("xyList", dao.getXyList());// 发送学院列表
		request.setAttribute("zyList", dao.getZyList(xydm));// 发送专业列表
		request.setAttribute("bjList", dao.getBjList(xydm, zydm));// 发送专业列表
		return mapping.findForward("success");
	}

	// 单位信息管理--地质大学
	private ActionForward dwxxmkwhViewMore(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommanForm dataSearchForm = (CommanForm) form;
		String sql = "";
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		String rsNum = "0";
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String pkValue = request.getParameter("pkValue");
		String queryType = request.getParameter("queryType");
		request.setAttribute("qt", queryType);
		HashMap<String, String> map = new HashMap<String, String>();

		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		HttpSession session = request.getSession();

		String dwxzcx = "select dwxzdm,dwxz from dmk_dwxz2"; // 单位性质代码2
		List dwxzdm2List = dao.getList(dwxzcx, new String[] {}, new String[] {
				"dwxzdm", "dwxz" });
		request.setAttribute("dwxxdm", dwxzdm2List);

		String hyfl = "select hyfldm,hyfl from dmk_hyfl"; // 行业分类
		List hyflList = dao.getList(hyfl, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);

		String userType = session.getAttribute("userType").toString();
		if ("admin".equalsIgnoreCase(userType)
				|| "xx".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xx");
		} else if ("xy".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xy");
		}

		// 单个删除
		if (null != pkValue) {
			pkValue = pkValue.replace(" ", "+");
		}

		if ("del".equalsIgnoreCase(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("dwxxb", "dwid", pkValue, request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		// 批量删除
		if ("delall".equalsIgnoreCase(doType2)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				pkstringI = pkstring.split("!!#!!");
			}

			for (int i = 1; i < pkstringI.length; i++) {
				String whichrid = pkstringI[i];
				boolean judge = false;
				judge = StandardOperation.delete("dwxxb", "dwid", whichrid,
						request);
				if (judge) {
					request.setAttribute("delall", "ok");
				} else {
					request.setAttribute("delall", "no");
				}
			}
		}

		// 全部清空
		if ("delallinfo".equalsIgnoreCase(doType2)) {

			boolean judge = false;

			judge = dao.runUpdateTab("truncate table dwxxb");

			if (judge) {
				request.setAttribute("delallinfo", "ok");
			} else {
				request.setAttribute("delallinfo", "no");
			}

		}

		String szdqsi = DealString.toGBK(request.getParameter("szdqsi")); // 专业名称
		String dwxz = DealString.toGBK(request.getParameter("dwxz")); // 班级名称

		String szdqsh = DealString.toGBK(request.getParameter("szdqsh")); // 岗位性质
		String sshy = DealString.toGBK(request.getParameter("hyfl")); // 所属行业
		String zgbm = DealString.toGBK(request.getParameter("zgbm")); // 主管部门

		String dwmc = DealString.toGBK(request.getParameter("dwmc")); // 单位名称
		dataSearchForm.setDwmc(dwmc);
		if ("query".equals(act)) {
			map.put("zgbm", zgbm);
			map.put("hyfl", sshy);
			map.put("dwxz", dwxz);
		}

		StringBuffer query = new StringBuffer();

		if (!("".equals(dwmc))) {
			query.append(" and dwmc like '%");
			query.append(dwmc);
			query.append("%'");
		}
		if (!("".equals(zgbm))) {
			query.append(" and zgbm like '%");
			query.append(zgbm);
			query.append("%'");
		}
		if (!("".equals(sshy))) {
			query.append(" and hyfl= '");
			query.append(sshy);
			query.append("'");
		}
		if (!("".equals(szdqsh))) {
			query.append(" and szdqsh='");
			query.append(szdqsh);
			query.append("'");
		}
		if (!("".equals(szdqsi))) {
			query.append(" and szdqsi= '");
			query.append(szdqsi);
			query.append("'");
		}
		if (!("".equals(dwxz))) {
			query.append(" and dwxz='");
			query.append(dwxz);
			query.append("'");
		}

		String query1 = query.toString();

		sql = "select count(*) count from dwxxb a where 1=1 " + query1;

		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// 就业信息
		sql = "select * from (select a.*,rownum r from (select distinct a.dwid,a.dwmc,a.zgbm,a.clrq,a.lxbm,a.lxr,a.lxdh from dwxxb a where 1=1 "
				+ query1
				+ " order by dwmc desc) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " order by dwmc desc";

		colList = new String[] { "dwid", "dwmc", "zgbm", "clrq", "lxbm", "lxr",
				"lxdh" };

		if ("query".equals(act)) {
			rs = dao.getArrayList2(sql, new String[] {}, colList);
		}
		sql = "select count(*) from dwxxb where 1=1 " + query1;
		int rsNuminfo = dao.getOneRsint(sql);
		rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);
		colListCN = dao.getColumnNameCN(colList, "dwxxb");
		List topTr = dao.arrayToList(colList, colListCN);

		String zgdwmc = "select zgbmdm,zgbm from dmk_zgbm"; // 生源地主管单位名称
		List sydzgbmList = dao.getList(zgdwmc, new String[] {}, new String[] {
				"zgbmdm", "zgbm" });
		request.setAttribute("sydzgbmList", sydzgbmList);

		StuInfoDAO stuDao = new StuInfoDAO();
		request.setAttribute("shiList", stuDao.getShiList(szdqsh)
				.get("shiList"));
		request.setAttribute("xianList", stuDao.getShiList("").get("xianList"));
		request.setAttribute("ssList", stuDao.getSsList());

		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		request.setAttribute("topTr", topTr);
		request.setAttribute("realTable", "dwxxb");
		request.setAttribute("querry", query1);
		// request.setAttribute("njList", dao.getNjList());// 发送年级列表
		// request.setAttribute("xyList", dao.getXyList());// 发送学院列表
		// request.setAttribute("zyList", dao.getZyList(xydm));// 发送专业列表
		// request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));//
		// 发送专业列表
		return mapping.findForward("success");
	}

	// 单位来访信息--地质大学
	private ActionForward dwlfxxViewMore(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		DAO dao = DAO.getInstance();
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		String pk = request.getParameter("pkValue");
		StringBuffer query1 = new StringBuffer();
		String sql = "";
		String[] colList;
		String rsNum = "0"; // 返回的记录数

		if ("save".equals(doType)) {
			String dwbh = DealString.toGBK(request.getParameter("dwbh")); // 编号
			String dwmc = DealString.toGBK(request.getParameter("dwmc")); // 单位名称
			String dwdh = DealString.toGBK(request.getParameter("dwdh")); // 单位电话
			String dwcz = DealString.toGBK(request.getParameter("dwcz")); // 单位传真
			String dwyx = DealString.toGBK(request.getParameter("dwyx")); // 单位邮箱
			String dwdz = DealString.toGBK(request.getParameter("dwdz")); // 单位地址
			String dwyb = DealString.toGBK(request.getParameter("dwyb")); // 单位邮编
			String sj = DealString.toGBK(request.getParameter("sj")); // 时间
			String xm = DealString.toGBK(request.getParameter("xm")); // 姓名
			String xb = DealString.toGBK(request.getParameter("xb")); // 性别
			String bm = DealString.toGBK(request.getParameter("bm")); // 部门
			String zw = DealString.toGBK(request.getParameter("zw")); // 职务
			String gddh = DealString.toGBK(request.getParameter("gddh")); // 固定电话
			String yddh = DealString.toGBK(request.getParameter("yddh")); // 移动电话
			String dzyx = DealString.toGBK(request.getParameter("dzyx")); // 电子邮件
			String lfsy = DealString.toGBK(request.getParameter("lfsy")); // 来访事宜
			String ytdd = DealString.toGBK(request.getParameter("ytdd")); // 会谈地点
			String rs1 = DealString.toGBK(request.getParameter("rs")); // 人数
			String jdr = DealString.toGBK(request.getParameter("jdr")); // 接待人
			String jddd = DealString.toGBK(request.getParameter("jddd")); // 接待地点
			String jdcyr1 = DealString.toGBK(request.getParameter("jdcyr1")); // 接待参与人1
			String jdcyr2 = DealString.toGBK(request.getParameter("jdcyr2")); // 接待参与人2
			String jdcyr3 = DealString.toGBK(request.getParameter("jdcyr3")); // 接待参与人3
			String jdcyr4 = DealString.toGBK(request.getParameter("jdcyr4")); // 接待参与人4
			String bz = DealString.toGBK(request.getParameter("bz")); // 备注
			String savetype = request.getParameter("savetype");
			boolean judge = false;
			if ("updatedw".equals(savetype)) {
				String pkid = request.getParameter("dwid");
				judge = StandardOperation.delete("dwlfxxdjb", "dwid", pkid,
						request);
				if (judge) {
					StandardOperation.insert("dwlfxxdjb", new String[] {
							"dwbh", "dwmc", "dwdh", "dwcz", "dwyx", "dwdz",
							"dwyb", "sj", "xm", "xb", "bm", "zw", "gddh",
							"yddh", "dzyx", "lfsy", "ytdd", "rs", "jdr",
							"jddd", "jdcyr1", "jdcyr2", "jdcyr3", "jdcyr4",
							"bz", }, new String[] { dwbh, dwmc, dwdh, dwcz,
							dwyx, dwdz, dwyb, sj, xm, xb, bm, zw, gddh, yddh,
							dzyx, lfsy, ytdd, rs1, jdr, jddd, jdcyr1, jdcyr2,
							jdcyr3, jdcyr4, bz, }, request);
				}
				if (judge) {
					request.setAttribute("update", "ok");

					// 添加操作的记录
				} else {
					request.setAttribute("update", "no");
				}
			} else {
				judge = StandardOperation.insert("dwlfxxdjb", new String[] {
						"dwbh", "dwmc", "dwdh", "dwcz", "dwyx", "dwdz", "dwyb",
						"sj", "xm", "xb", "bm", "zw", "gddh", "yddh", "dzyx",
						"lfsy", "ytdd", "rs", "jdr", "jddd", "jdcyr1",
						"jdcyr2", "jdcyr3", "jdcyr4", "bz", }, new String[] {
						dwbh, dwmc, dwdh, dwcz, dwyx, dwdz, dwyb, sj, xm, xb,
						bm, zw, gddh, yddh, dzyx, lfsy, ytdd, rs1, jdr, jddd,
						jdcyr1, jdcyr2, jdcyr3, jdcyr4, bz, }, request);
				if (judge) {
					request.setAttribute("insert", "ok");

					// 添加操作的记录
				} else {
					request.setAttribute("insert", "no");
				}
			}
		}
		if ("sect".equals(doType)) {
			if ("query".equals(act)) {
				String dwbh = DealString.toGBK(request.getParameter("dwbh")); // 编号
				String dwmc = DealString.toGBK(request.getParameter("dwmc")); // 单位名称
				String dwdh = request.getParameter("dwdh"); // 单位电话
				String xm = DealString.toGBK(request.getParameter("xm")); // 姓名
				if (!("".equals(dwbh))) {
					query1.append(" and dwbh like '%");
					query1.append(dwbh);
					query1.append("%'");
				}
				if (!("".equals(dwmc))) {
					query1.append(" and dwmc like '%");
					query1.append(dwmc);
					query1.append("%'");
				}
				if (!("".equals(dwdh))) {
					query1.append(" and dwdh like '%");
					query1.append(dwdh);
					query1.append("%'");
				}
				if (!("".equals(xm))) {
					query1.append(" and xm like '%");
					query1.append(xm);
					query1.append("%'");
				}
				CommanForm dataSearchForm = (CommanForm) form;
				sql = "select count(*) count from dwlfxxdjb a where 1=1 "
						+ query1;

				dataSearchForm.getPages().setMaxRecord(
						Integer.parseInt(dao.getOneRs(sql, new String[] {},
								"count")));

				sql = "select * from (select a.*,rownum r from (select distinct a.dwid,a.dwbh,a.dwmc,a.xm,a.dwdh,a.bm from dwlfxxdjb a where 1=1 "
						+ query1
						+ " order by dwbh desc) a ) a where a.r>"
						+ dataSearchForm.getPages().getStart()
						+ " and a.r<="
						+ (dataSearchForm.getPages().getStart() + dataSearchForm
								.getPages().getPageSize())
						+ " order by dwbh desc";

				colList = new String[] { "dwid", "dwbh", "dwmc", "xm", "dwdh",
						"bm" };

				if ("query".equals(act)) {
					rs = dao.getArrayList2(sql, new String[] {}, colList);
				}
				sql = "select count(*) from dwlfxxdjb where 1=1 " + query1;
				int rsNuminfo = dao.getOneRsint(sql);
				rsNum = String.valueOf(rsNuminfo);
				String[] colListCN = dao.getColumnNameCN(colList, "dwlfxxdjb");
				List topTr = dao.arrayToList(colList, colListCN);
				request.setAttribute("rs", rs);// 发送数据集
				request.setAttribute("topTr", topTr);// 发送表头
				request.setAttribute("rsNum", rsNum);// 发送记录数
			}
			return mapping.findForward("sect");
		} else if ("update".equals(doType)) {
			sql = "select d.* from dwlfxxdjb d where dwid =?";

			String[] colListdw = new String[] { "dwid", "dwbh", "dwmc", "dwdh",
					"dwcz", "dwyx", "dwdz", "dwyb", "sj", "xm", "xb", "bm",
					"zw", "gddh", "yddh", "dzyx", "lfsy", "ytdd", "rs", "jdr",
					"jddd", "jdcyr1", "jdcyr2", "jdcyr3", "jdcyr4", "bz" }; // 返回列名数组
			String[] zpxxinfo = dao.getOneRs(sql, new String[] { pk },
					colListdw);
			if (zpxxinfo != null) {
				for (int i = 0; i < colListdw.length; i++) {
					map.put(colListdw[i].toLowerCase(), zpxxinfo[i]); // 将记录循环放入map中
				}

				request.setAttribute("rs2", map);// 发送数据集
				request.setAttribute("dwtype", "add");
				request.setAttribute("savetype", "updatedw");
				return mapping.findForward("success");
			}
		} else if ("dnset".equals(doType)) {
			sql = "select d.* from dwlfxxdjb d where dwid =?";

			String[] colListdw = new String[] { "dwbh", "dwmc", "dwdh", "dwcz",
					"dwyx", "dwdz", "dwyb", "sj", "xm", "xb", "bm", "zw",
					"gddh", "yddh", "dzyx", "lfsy", "ytdd", "rs", "jdr",
					"jddd", "jdcyr1", "jdcyr2", "jdcyr3", "jdcyr4", "bz" }; // 返回列名数组
			String[] zpxxinfo = dao.getOneRs(sql, new String[] { pk },
					colListdw);
			if (zpxxinfo != null) {
				for (int i = 0; i < colListdw.length; i++) {
					map.put(colListdw[i].toLowerCase(), Base
							.isNull(zpxxinfo[i]) ? "" : zpxxinfo[i].trim()); // 将记录循环放入map中
				}

				request.setAttribute("rs2", map);// 发送数据集
				request.setAttribute("dwtype", "dnset");
				return mapping.findForward("success");
			}
		}
		// 单个删除
		if ("del".equalsIgnoreCase(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("dwlfxxdjb", "dwid", pk, request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
			return mapping.findForward("sect");
		}

		// 批量删除
		if ("delall".equalsIgnoreCase(doType)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				pkstringI = pkstring.split("!!#!!");
			}

			for (int i = 1; i < pkstringI.length; i++) {
				String whichrid = pkstringI[i];
				boolean judge = false;
				judge = StandardOperation.delete("dwlfxxdjb", "dwid", whichrid,
						request);
				if (judge) {
					request.setAttribute("delall", "ok");
				} else {
					request.setAttribute("delall", "no");
				}
			}
			return mapping.findForward("sect");
		}

		request.setAttribute("rs2", map);// 发送数据集
		request.setAttribute("dwtype", "add");
		return mapping.findForward("success");
	}

	// 单位信息修改
	private ActionForward dwxxinfoUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		DAO dao = DAO.getInstance();
		String doType = request.getParameter("doType");
		String sql = "";

		sql = "select dm,mc from dm_gb_zhrmghgxzqhdm"; // 单位地区
		List gzdList = dao.getList(sql, new String[] {}, new String[] { "dm",
				"mc" });
		request.setAttribute("gzdList", gzdList);

		if (null != pkValue && !"".equalsIgnoreCase(pkValue)) {
			pkValue = pkValue.replace(" ", "+");
		}

		String dwmc = DealString.toGBK(request.getParameter("dwmc")); // 单位名称
		String dwxz = DealString.toGBK(request.getParameter("dwxz")); // 单位性质
		String dwdz = DealString.toGBK(request.getParameter("dwdz")); // 单位地址
		String dwlxr = DealString.toGBK(request.getParameter("dwlxr")); // 单位联系人
		String dwdh = DealString.toGBK(request.getParameter("dwdh")); // 单位电话
		String dwzm = DealString.toGBK(request.getParameter("dwzm")); // 单位证明
		String zydkqk = DealString.toGBK(request.getParameter("zydkqk")); // 专业对口情况
		String jybm = DealString.toGBK(request.getParameter("jybm")); // 就业部门
		String gwxz = DealString.toGBK(request.getParameter("gwxz")); // 岗位性质
		String gwmc = DealString.toGBK(request.getParameter("gwmc")); // 岗位名称
		String gzqk = DealString.toGBK(request.getParameter("gzqk")); // 工资情况
		String gzd = DealString.toGBK(request.getParameter("gzd")); // 工作地
		String sfjy = DealString.toGBK(request.getParameter("sfjy")); // 是否就业
		String sfqy = DealString.toGBK(request.getParameter("sfqy")); // 是否签约
		String djsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 登记时间
				// //
				// 取至数据库临时表
				new String[] {}, new String[] { "sdate" }))[0];

		if ("update".equalsIgnoreCase(doType)) {

			boolean judge = false;
			judge = StandardOperation.update("jygl_xsjyqkb", new String[] {
					"dwmc", "dwxz", "dwdz", "dwlxr", "dwdh", "dwzm", "zydkqk",
					"jybm", "gwxz", "gwmc", "gzqk", "gzd", "sfjy", "sfqy",
					"djsj" },
					new String[] { dwmc, dwxz, dwdz, dwlxr, dwdh, dwzm, zydkqk,
							jybm, gwxz, gwmc, gzqk, gzd, sfjy, sfqy, djsj },
					"rowid", pkValue, request);
			if (judge) {
				request.setAttribute("update", "ok");
			} else {
				request.setAttribute("update", "no");
			}

		}

		sql = "select * from view_jygl_xsjyqkb where rid='" + pkValue + "'";
		String[] colList = dao
				.getColumnName("select * from view_jygl_xsjyqkb where 1=2"); // 返回列名数组
		String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
		if (stuinfo != null) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
			}
			djsj = map.get("djsj");
			if (null != djsj && !"".equalsIgnoreCase(djsj)) {
				map.put("djsj", dao.doForTime(djsj));
			}
		}

		request.setAttribute("rs", map);

		return mapping.findForward("success");

	}

	// 招聘信息发布刷单位
	private ActionForward zpxxdwViewMore(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sql = "";
		DAO dao = DAO.getInstance();
		String[] colList = null;
		String[] colListCN = null;
		String rsNum = "0";
		String act = request.getParameter("act");
		String doType = request.getParameter("doType");
		String doType2 = request.getParameter("doType2");
		String pkValue = request.getParameter("pkValue");
		String queryType = request.getParameter("queryType");
		request.setAttribute("qt", queryType);
		HashMap<String, String> map = new HashMap<String, String>();

		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		HttpSession session = request.getSession();

		String dwxzcx = "select dwxzdm,dwxz from dmk_dwxz2"; // 单位性质代码2
		List dwxzdm2List = dao.getList(dwxzcx, new String[] {}, new String[] {
				"dwxzdm", "dwxz" });
		request.setAttribute("dwxxdm", dwxzdm2List);

		String zgdwmc = "select sydzgbmdm,sydzgbm from dmk_sydzgbm"; // 生源地主管单位名称
		List sydzgbmList = dao.getList(zgdwmc, new String[] {}, new String[] {
				"sydzgbmdm", "sydzgbm" });
		request.setAttribute("sydzgbmList", sydzgbmList);

		String hyfl = "select hyfldm,hyfl from dmk_hyfl"; // 行业分类
		List hyflList = dao.getList(hyfl, new String[] {}, new String[] {
				"hyfldm", "hyfl" });
		request.setAttribute("hyflList", hyflList);

		StuInfoDAO stuDao = new StuInfoDAO();
		request.setAttribute("shiList", stuDao.getShiList("").get("shiList"));
		request.setAttribute("xianList", stuDao.getShiList("").get("xianList"));
		request.setAttribute("ssList", stuDao.getSsList());

		String userType = session.getAttribute("userType").toString();
		if ("admin".equalsIgnoreCase(userType)
				|| "xx".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xx");
		} else if ("xy".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xy");
		}

		// 单个删除
		if (null != pkValue) {
			pkValue = pkValue.replace(" ", "+");
		}

		if ("del".equalsIgnoreCase(doType)) {
			boolean judge = false;
			judge = StandardOperation.delete("dwxxb", "dwid", pkValue, request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		// 批量删除
		if ("delall".equalsIgnoreCase(doType2)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				pkstringI = pkstring.split("!!#!!");
			}

			for (int i = 1; i < pkstringI.length; i++) {
				String whichrid = pkstringI[i];
				boolean judge = false;
				judge = StandardOperation.delete("dwxxb", "dwid", whichrid,
						request);
				if (judge) {
					request.setAttribute("delall", "ok");
				} else {
					request.setAttribute("delall", "no");
				}
			}
		}

		// 全部清空
		if ("delallinfo".equalsIgnoreCase(doType2)) {

			boolean judge = false;

			judge = dao.runUpdateTab("truncate table dwxxb");

			if (judge) {
				request.setAttribute("delallinfo", "ok");
			} else {
				request.setAttribute("delallinfo", "no");
			}

		}

		String szdqsi = DealString.toGBK(request.getParameter("szdqsi")); // 专业名称
		String dwxz = DealString.toGBK(request.getParameter("dwxz")); // 班级名称

		String szdqsh = DealString.toGBK(request.getParameter("szdqsh")); // 岗位性质
		String sshy = DealString.toGBK(request.getParameter("hyfl")); // 所属行业
		String zgbm = DealString.toGBK(request.getParameter("zgbm")); // 主管部门

		String dwmc = DealString.toGBK(request.getParameter("dwmc")); // 单位名称
		if ("query".equals(act)) {
			map.put("zgbm", zgbm);
			map.put("hyfl", sshy);
			map.put("dwxz", dwxz);
		}

		StringBuffer query = new StringBuffer();

		if (!("".equals(dwmc))) {
			query.append(" and dwmc like '%");
			query.append(dwmc);
			query.append("%'");
		}
		if (!("".equals(zgbm))) {
			query.append(" and zgbm like '%");
			query.append(zgbm);
			query.append("%'");
		}
		if (!("".equals(sshy))) {
			query.append(" and hyfl= '");
			query.append(sshy);
			query.append("'");
		}
		if (!("".equals(szdqsh))) {
			query.append(" and szdqsh='");
			query.append(szdqsh);
			query.append("'");
		}
		if (!("".equals(szdqsi))) {
			query.append(" and szdqsi= '");
			query.append(szdqsi);
			query.append("'");
		}
		if (!("".equals(dwxz))) {
			query.append(" and dwxz='");
			query.append(dwxz);
			query.append("'");
		}

		String query1 = query.toString();

		CommanForm dataSearchForm = (CommanForm) form;
		sql = "select count(*) count from dwxxb a where 1=1 " + query1;

		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		// 就业信息
		sql = "select * from (select a.*,rownum r from (select distinct a.dwid,a.dwmc,a.zgbm,a.zcsj,a.lxbm,a.lxr,a.lxdh from dwxxb a where 1=1 "
				+ query1
				+ " order by dwmc desc) a ) a where a.r>"
				+ dataSearchForm.getPages().getStart()
				+ " and a.r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize()) + " order by dwmc desc";

		colList = new String[] { "dwid", "dwmc", "zgbm", "zcsj", "lxbm", "lxr",
				"lxdh" };

		if ("query".equals(act)) {
			rs = dao.getArrayList2(sql, new String[] {}, colList);
		}
		sql = "select count(*) from dwxxb where 1=1 " + query1;
		int rsNuminfo = dao.getOneRsint(sql);
		rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);
		colListCN = dao.getColumnNameCN(colList, "dwxxb");
		List topTr = dao.arrayToList(colList, colListCN);

		request.setAttribute("rs", rs);
		request.setAttribute("rs1", map);
		request.setAttribute("topTr", topTr);
		request.setAttribute("realTable", "dwxxb");
		request.setAttribute("querry", query1);
		return mapping.findForward("success");
	}

	// 学生信息学校审核
	private ActionForward xsxxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String userType) throws Exception {
		String pk = "xsxh";
		String sql = ""; // sql语句
		DAO dao = DAO.getInstance();
		String realTable = "jygl_xsjbxxb";
		// String doType = request.getParameter("doType");
		// String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();
		String sfsh = DealString.toGBK(request.getParameter("sfsh"));
		String btgyy = DealString.toGBK(request.getParameter("btgyy"));
		String xsxh = request.getParameter("xsxh");
		String act = request.getParameter("act");
		HttpSession session = request.getSession();
		String shperson = session.getAttribute("userName").toString();
		String userName = session.getAttribute("userName").toString();
		// String xxtj = DealString.toGBK(request.getParameter("xxtj"));
		ArrayList<Object> rs = new ArrayList<Object>();
		String[] colList = null;
		String[] colListCN = null;
		// String doType2 = request.getParameter("doType2");
		String doType3 = request.getParameter("doType3");
		String querry = " where 1=1 "; // 条件初始化
		String rsNum = "0"; // 返回的记录数
		String tableName = "jygl_xsjbxxb"; // 查询表名
		String usertype = (String) request.getSession()
				.getAttribute("userType");

		if ("shenhe".equals(act)) {
			String xxdm = (String) request.getSession().getAttribute("xxdm");
			if ("12453".equals(xxdm)) {
				if (usertype == "xx" || "xx".equals(usertype)
						|| "admin".equals(usertype)) {
					String shsj = (dao
							.getOneRs(
									"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 学校审核时间
									// //
									// 取至数据库临时表
									new String[] {}, new String[] { "sdate" }))[0];
					if ("已通过√".equals(sfsh)) {

					}
					if ("未审核".equals(sfsh)) {
						btgyy = "";
						shperson = "";
						shsj = "";
					}
					sql = "update " + realTable + " set sfsh='" + sfsh + "' ,"
							+ "shsj='" + shsj + "' ,shperson='" + shperson
							+ "' ,btgyy='" + btgyy + "' where xsxh='" + xsxh
							+ "'";

					boolean judge = false;
					judge = dao.runUpdate(sql, new String[] {});
					if (judge) {
						request.setAttribute("shenhe", "ok");
					} else {
						request.setAttribute("shenhe", "no");
					}
				} else {
					String shsj = (dao
							.getOneRs(
									"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 学校审核时间
									// //
									// 取至数据库临时表
									new String[] {}, new String[] { "sdate" }))[0];
					if ("已通过√".equals(sfsh)) {

					}
					if ("未审核".equals(sfsh)) {
						btgyy = "";
						shperson = "";
						shsj = "";
					}
					sql = "update " + realTable + " set fdysh='" + sfsh + "' ,"
							+ "fdyshsj='" + shsj + "' ,fdyshr='" + shperson
							+ "' where xsxh='" + xsxh + "'";

					boolean judge = false;
					judge = dao.runUpdate(sql, new String[] {});
					if (judge) {
						request.setAttribute("shenhe", "ok");
					} else {
						request.setAttribute("shenhe", "no");
					}
				}
			} else {

				String shsj = (dao
						.getOneRs(
								"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 学校审核时间
								// //
								// 取至数据库临时表
								new String[] {}, new String[] { "sdate" }))[0];
				if ("已通过√".equals(sfsh)) {

				}
				if ("未审核".equals(sfsh)) {
					btgyy = "";
					shperson = "";
					shsj = "";
				}
				sql = "update " + realTable + " set sfsh='" + sfsh + "' ,"
						+ "shsj='" + shsj + "' ,shperson='" + shperson
						+ "' ,btgyy='" + btgyy + "' where xsxh='" + xsxh + "'";

				boolean judge = false;
				judge = dao.runUpdate(sql, new String[] {});
				if (judge) {
					request.setAttribute("shenhe", "ok");
				} else {
					request.setAttribute("shenhe", "no");
				}

			}
		}
		// 关于发布时间的处理 用于查询条件
		String xjsj = DealString.toGBK(request.getParameter("xjsj")); // 相距时间

		String nowsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 发布时间
				// //
				// 取至数据库临时表
				new String[] {}, new String[] { "sdate" }))[0];
		String shsj = (dao.getOneRs(
				"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 学校审核时间
				// //
				// 取至数据库临时表
				new String[] {}, new String[] { "sdate" }))[0];
		String sjyear = nowsj.substring(0, 4);
		String sjmonth = nowsj.substring(4, 6);
		String sjday = nowsj.substring(6, 8);
		String sjqt = nowsj.substring(8, 14);
		String nowsj1 = sjyear + "-" + sjmonth + "-" + sjday;
		dealDate dealdate = new dealDate();
		String beforesj = "";
		if (!"".equals(xjsj)) {
			beforesj = dealdate.getDate2(Integer.parseInt(xjsj), nowsj1);
			beforesj = beforesj.replaceAll("-", "") + sjqt;
		}
		String name = DealString.toGBK(request.getParameter("name")); // 姓名
		// String xb = DealString.toGBK(request.getParameter("xb")); // 性别
		String xymc = DealString.toGBK(request.getParameter("xymc")); // 学院
		// String nj = request.getParameter("nj"); // 年级
		// String xxsh = DealString.toGBK(request.getParameter("xxsh")); // 学校审核
		// 关于发布时间的处理 用于查询条件

		// 批量审核通过
		if ("pass".equalsIgnoreCase(doType3)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = pkstring.split("!!#!!");

			for (int i = 1; i < pkstringI.length; i++) {
				String whichxh = pkstringI[i];
				sql = "update jygl_xsjbxxb set sfsh='已通过√',shperson='"
						+ shperson + "',shsj='" + shsj + "' where xsxh=?";
				boolean judge = false;
				judge = dao.runUpdate(sql, new String[] { whichxh });
				if (judge) {
					request.setAttribute("allpass", "ok");
				} else {
					request.setAttribute("allpass", "no");
				}
			}
		}
		// 批量审核否决
		if ("notpass".equalsIgnoreCase(doType3)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = pkstring.split("!!#!!");

			for (int i = 1; i < pkstringI.length; i++) {
				String whichxh = pkstringI[i];
				sql = "update jygl_xsjbxxb set sfsh='未通过X',shperson='"
						+ shperson + "',shsj='" + shsj + "' where xsxh=?";
				boolean judge = false;
				judge = dao.runUpdate(sql, new String[] { whichxh });
				if (judge) {
					request.setAttribute("allnotpass", "ok");
				} else {
					request.setAttribute("allnotpass", "no");
				}
			}
		}
		String xxdm = dao.getXxdm();
		String dataType = request.getParameter("act"); // 接收数据类型
		String xbdm = request.getParameter("xbdm"); // 接收性别代码
		String xslb = DealString.toGBK(request.getParameter("xslb")); // 接收学生类别
		String nd = request.getParameter("nd"); // 接收年度
		String bynd = request.getParameter("bynd"); // 毕业年度

		if (userType.equals("teacher")) {
			sql = "select * from jygl_jyxywhb where username=?";
			String[] teainfo = dao.getOneRs(sql, new String[] { userName },
					new String[] { "usertype", "xymc" });
			String tea = "";
			if (null != teainfo) {
				tea = teainfo[0];
			}
			if ("辅导员".equals(tea)) {
				map.put("xymc", teainfo[1]);
				request.setAttribute("who", "fudaoyuan");
			} else {
				request.setAttribute("who", "teacher");
			}
		}

		map.put("xsxh", xsxh);
		map.put("name", name);
		map.put("xbdm", xbdm);
		map.put("xslb", xslb);
		map.put("xymc", xymc);
		map.put("nd", nd);
		map.put("bynd", bynd);

		if (xsxh == null) {
			xsxh = "";
		}
		if (name == null) {
			name = "";
		}
		if (xbdm == null) {
			xbdm = "";
		}
		if (xslb == null) {
			xslb = "";
		}
		if (xymc == null) {
			xymc = "";
		}
		if (nd == null) {
			nd = "";
		}
		if (bynd == null) {
			bynd = "";
		}
		if ((xsxh == null) || xsxh.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xsxh like '%" + xsxh + "%' ";
		}
		if ((name == null) || name.equals("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and name like '%" + name + "%' ";
		}
		if ((xbdm == null) || xbdm.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xbdm='" + xbdm + "' ";
		}
		if ((xslb == null) || xslb.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xslb='" + xslb + "' ";
		}
		if ((xymc == null) || xymc.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and xsxh in (select xsxh from jygl_xsjbxxb where xsxh in (select xh from view_xsjbxx where xymc ='"
					+ xymc + "'))";
		}
		if ((nd == null) || nd.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and nd='" + nd + "' ";
		}
		if ((bynd == null) || bynd.equals("")) {
			querry += " and 1=1 ";
		} else {
			querry += " and bynd='" + bynd + "' ";
		}

		sql = "select " + pk + " 主键,rownum 行号,a.* from " + tableName + " a "
				+ querry;
		colList = new String[] { "主键", "行号", "nd", "bynd", "name", "xsxh",
				"xbdm", "xldm", "xslb", "xzdm", "zymc", "sbsj", "sfsh" };
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		request.setAttribute("realTable", "jygl_xsjbxxb"); // 表是学生基本信息表
		request.setAttribute("ndList", dao.getNjList()); // 发送入学年度列表
		request.setAttribute("xyList", dao.getXyList());// 发送学院列表
		request.setAttribute("act", dataType);// 发送数据查询类型
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("rs1", map);

		String view = request.getParameter("view");
		if ("view".equalsIgnoreCase(view)) {
			// 查询数据
			sql = "select * from " + realTable + " where " + pk + "='" + xsxh
					+ "'";
			colList = dao.getColumnName("select * from jygl_xsjbxxb where 1=2"); // 返回列名数组
			String[] stuinfo = dao.getOneRs(sql, new String[] {}, colList);
			sql = "select xymc from view_xsjbxx where xh=?";
			String[] xymc1 = dao.getOneRs(sql, new String[] { xsxh },
					new String[] { "xymc" });
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // 将记录循环放入map中
				}
				if (Globals.XXDM_ZGDZDX.equals(xxdm)) {
					shsj = null;
					// String fbsj = null;
					sjyear = null;
					String sjmon = null;
					sjday = null;
					String sjhour = null;
					if (null != (map.get("shsj"))) {
						String sj = map.get("shsj").toString();
						sjyear = sj.substring(0, 4);
						sjmon = sj.substring(4, 6);
						sjday = sj.substring(6, 8);
						sjhour = sj.substring(8, 10);
						shsj = sjyear + "年" + sjmon + "月" + sjday + "日"
								+ sjhour + "时";
						map.put("shsj", shsj);
					}
				}
				// 显示出来的性别是中文
				if ("1".equals(map.get("xbdm"))) {
					map.put("xbdm", "男");
				} else if ("2".equals(map.get("xbdm"))) {
					map.put("xbdm", "女");
				}
				sql = "select xl from dmk_xl where xldm=?";
				String xldm = map.get("xldm");
				String xlinfo = dao.getOneRs(sql, new String[] { xldm }, "xl");
				map.put("xl", xlinfo);

				sql = "select sydq from dmk_sydq where sydqdm=?";
				String sydqdm = map.get("sydqdm");
				String sydqinfo = dao.getOneRs(sql, new String[] { sydqdm },
						"sydq");
				String qxdm = map.get("jgshi");
				String sydqinfo1 = dao.getOneRs(
						"select qxmc from dmk_qx WHERE qxdm = ?",
						new String[] { qxdm }, "qxmc");
				map.put("sydq", sydqinfo + sydqinfo1);

				if ("专科生".equals(map.get("xslb"))
						|| "本科生".equals(map.get("xslb"))) {
					sql = "select pyfs from dmk_bzpyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, "pyfs");
					map.put("pyfs", pyfsinfo);
				} else if ("研究生".equals(map.get("xslb"))) {
					sql = "select pyfs from dmk_yjspyfs where pyfsdm=?";
					String pyfsdm = map.get("pyfsdm");
					String pyfsinfo = dao.getOneRs(sql,
							new String[] { pyfsdm }, "pyfs");
					map.put("pyfs", pyfsinfo);
				}
				if (null != xymc) {
					map.put("xymc", xymc1[0]);
				}
				String sbsj = null;
				sjyear = null;
				String sjmon = null;
				sjday = null;
				String sj = map.get("sbsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sbsj = sjyear + "年" + sjmon + "月" + sjday + "日";
				map.put("sbsj", sbsj);
			}
			request.setAttribute("rs", map);
		}

		if ("shenhe".equals(act)) {
			return mapping.findForward("success");
		} else {
			return mapping.findForward("successdz");
		}
	}

	// 就业情况统计
	@SuppressWarnings("unchecked")
	private ActionForward jyqktj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tjxm = request.getParameter("tjxm");
		String bynd = request.getParameter("nd");
		String type = request.getParameter("type");
		List<HashMap<String, Object>> rs = new ArrayList<HashMap<String, Object>>();
		JyglService service = new JyglService();
		String[] keys = null;
		String[] values = null;
		if (!Base.isNull(tjxm)) {
			if (tjxm.equals("xjsp")) {
				if ("tj".equals(type)) {
					keys = new String[] { "xy", "zy", "cyrs", "xjspfb", "xj1",
							"xj2", "xj3", "xj4", "xj5", "xj6", "pjgz" };
					values = new String[] { "学院", "专业", "抽样人数", "薪金水平分布（人数）",
							"0-1000", "1000-1500", "1500-2000", "2000-2500",
							"2500-3000", "3000以上", "平均工资" };
					rs = service.getXjspList_ser(bynd);
					HashMap totalmap = service.getXjspTotalMap(rs);
					request.setAttribute("totalmap", totalmap);
				} else {
					String modelPath = servlet.getServletContext().getRealPath(
							"")
							+ "/print/shgc_xjtjb.xls";
					response.reset();
					response.setContentType("application/vnd.ms-excel");
					WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(
							new File(modelPath), response.getOutputStream());
					service.printByxj(wwb, bynd);
					return mapping.findForward("");
				}

			} else if (tjxm.equals("zydkl")) {
				if ("tj".equals(type)) {
					keys = new String[] { "xy", "zy", "cyrs", "byqx", "bzy",
							"kzy", "zydkl" };
					values = new String[] { "学院", "专业", "抽样人数", "毕业去向", "本专业",
							"跨专业", "专业对口率" };
					rs = service.getZydklList_ser(bynd);
					HashMap totalmap = service.getZydkTotalMap(rs);
					request.setAttribute("totalmap", totalmap);
				} else {
					String modelPath = servlet.getServletContext().getRealPath(
							"")
							+ "/print/shgc_jyzydkl.xls";
					response.reset();
					response.setContentType("application/vnd.ms-excel");
					WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(
							new File(modelPath), response.getOutputStream());
					service.printZydkl(wwb, bynd);
					return mapping.findForward("");
				}
			} else if (tjxm.equals("dyfb")) {
				if ("tj".equals(type)) {
					keys = new String[] { "xy", "byrs", "qyrs", "tjjswqyrs",
							"dwszdq", "sq", "jq", "wd" };
					values = new String[] { "学院", "毕业生数", "签约人数", "统计基数为签约人数",
							"单位所在地区", "市区", "郊区", "外地" };
					rs = (List<HashMap<String, Object>>) service
							.getDyfbList(bynd);
				} else {
					String modelPath = servlet.getServletContext().getRealPath(
							"")
							+ "/print/shgc_byjyfb.xls";
					response.reset();
					response.setContentType("application/vnd.ms-excel");
					WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(
							new File(modelPath), response.getOutputStream());
					service.printDyfb(wwb, bynd);
					return mapping.findForward("");
				}
			} else if (tjxm.equals("dwxzqk")) {
				if ("tj".equals(type)) {
					keys = new String[] { "xy", "byrs", "qyrs", "tjjswqyrs",
							"dwxz", "gy", "jt", "my", "sy", "gf", "hz", "dz" };
					values = new String[] { "学院", "毕业生数", "签约人数", "统计基数为签约人数",
							"单位性质", "国有", "集体", "民营", "私营", "股份", "合资", "独资" };
					rs = (List<HashMap<String, Object>>) service
							.getDwxzqkList(bynd);
				} else {
					String modelPath = servlet.getServletContext().getRealPath(
							"")
							+ "/print/shgc_jydwxz.xls";
					response.reset();
					response.setContentType("application/vnd.ms-excel");
					WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(
							new File(modelPath), response.getOutputStream());
					service.printXzqk(wwb, bynd);
					return mapping.findForward("");
				}

			} else if (tjxm.equals("hyfb")) {
				if ("tj".equals(type)) {
					keys = new String[] { "xy", "byrs", "qyrs", "tjjswqyrs",
							"dwszhy", "zzy", "fwy", "ydtxjsjxxy", "jrbxfdc",
							"jykywgtyfl", "pflsmycy", "jzy", "jtyscc",
							"gjdzjgshtt", "qt" };
					values = new String[] { "学院", "毕业生数", "签约人数", "统计基数为签约人数",
							"单位所在行业", "制造业", "社会服务业", "邮电通信计算机信息业",
							"金融、保险及房地产业", "教育科研、文广、体育卫生和社会福利业", "批发零售贸易、餐饮业",
							"建筑业", "交通运输、仓储业", "国家、党政机关和社会团体", "其他" };
					rs = (List<HashMap<String, Object>>) service
							.getHyfbList(bynd);
				} else {
					String modelPath = servlet.getServletContext().getRealPath(
							"")
							+ "/print/shgc_jyhyfb.xls";
					response.reset();
					response.setContentType("application/vnd.ms-excel");
					WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(
							new File(modelPath), response.getOutputStream());
					service.printHyfb(wwb, bynd);
					return mapping.findForward("");
				}

			} else if (tjxm.equals("jyphb")) {
				if ("tj".equals(type)) {
					keys = new String[] { "xy", "zy", "byrs", "qyrs", "qyl",
							"qylpx", "jyrs", "jyl", "jylpx" };
					values = new String[] { "学院", "专业", "毕业人数", "签约人数", "签约率",
							"签约率排序", "就业人数", "就业率", "就业率排序" };
					rs = service.getJyphbList_ser(bynd);
					HashMap totalmap = service.getJyphTotalMap(rs);
					request.setAttribute("totalmap", totalmap);
				} else {
					String modelPath = servlet.getServletContext().getRealPath(
							"")
							+ "/print/shgc_jyphb.xls";
					response.reset();
					response.setContentType("application/vnd.ms-excel");
					WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(
							new File(modelPath), response.getOutputStream());
					service.printJyphb(wwb, bynd);
					return mapping.findForward("");
				}
			}
			request.setAttribute("tjxm", tjxm);
			request.setAttribute("rs", rs);
			request.setAttribute("tabtop", service.getTabTopMap(keys, values));
		}
		request.setAttribute("ndList", Base.getXnndList());
		return mapping.findForward("success");
	}

	private boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase("") || str
				.equalsIgnoreCase("all"));
	}

	// 学生咨询回复
	private ActionForward zxzxshtz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// CommanForm dataSearchForm = (CommanForm) form;
		String pk = "xsxh";
		String sql = ""; // sql语句
		DAO dao = DAO.getInstance();
		String tableName = "zxzyzxsqb";
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> mapsavesq = new HashMap<String, String>();
		HashMap<String, String> map = new HashMap<String, String>();

		if ("savesq".equals(act)) {
			String xh = request.getParameter("xh");
			String xsxm = DealString.toGBK(request.getParameter("xsxm"));
			String age = request.getParameter("age");
			String xb = DealString.toGBK(request.getParameter("xb"));
			String zymc = DealString.toGBK(request.getParameter("zymc"));
			String email = request.getParameter("email");
			String lxdh = DealString.toGBK(request.getParameter("lxdh"));
			String zxnr = DealString.toGBK(request.getParameter("zxnr"));
			String zxjshf = DealString.toGBK(request.getParameter("zxjshf"));
			String hfrxm = DealString.toGBK(request.getParameter("hfrxm"));
			String hfsj = request.getParameter("hfsj");

			mapsavesq.put("xh", xh);
			mapsavesq.put("xsxm", xsxm);
			mapsavesq.put("age", age);
			mapsavesq.put("xb", xb);
			mapsavesq.put("zymc", zymc);
			mapsavesq.put("email", email);
			mapsavesq.put("lxdh", lxdh);
			mapsavesq.put("zxnr", zxnr);
			mapsavesq.put("zxjshf", zxjshf);
			mapsavesq.put("hfrxm", hfrxm);
			mapsavesq.put("hfsj", hfsj);

			String zxsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 发布时间
							// //
							// 取至数据库临时表
							new String[] {}, new String[] { "sdate" }))[0];

			boolean judge = false;
			judge = StandardOperation.insert(tableName, new String[] { "xh",
					"xsxm", "age", "xb", "zymc", "email", "lxdh", "zxnr",
					"zxsj", "zxjshf", "hfrxm", "hfsj" }, new String[] { xh,
					xsxm, age, xb, zymc, email, lxdh, zxnr, zxsj, zxjshf,
					hfrxm, hfsj }, request);
			if (judge) {
				request.setAttribute("save", "ok");
			} else {
				request.setAttribute("save", "no");
			}
			request.setAttribute("rs", mapsavesq);
			return mapping.findForward("success");
		}

		if ("answer".equals(act)) {
			String xsqr = DealString.toGBK(request.getParameter("xsqr"));
			String xsqrsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 发布时间
							// //
							// 取至数据库临时表
							new String[] {}, new String[] { "sdate" }))[0];
			boolean judge = false;
			judge = StandardOperation.update(tableName, new String[] { "xsqr",
					"xsqrsj" }, new String[] { xsqr, xsqrsj }, pk, pkValue,
					request);
			if (judge) {
				request.setAttribute("answer", "ok");
			} else {
				request.setAttribute("answer", "no");
			}
		}

		if ("view".equalsIgnoreCase(doType)) {
			// 查询数据
			if ("".equals(pkValue)) {
				pkValue = request.getParameter("xsxh");
			}
			sql = "select * from " + tableName + " where " + pk + "='"
					+ pkValue + "'";
			String[] colList = dao.getColumnName("select * from " + tableName
					+ " where 1=2"); // 返回列名数组
			String[] zxyyglinfo = dao.getOneRs(sql, new String[] {}, colList);
			if (zxyyglinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), zxyyglinfo[i]); // 将记录循环放入map中
				}
			}
			if ("不赴约X".equals(map.get("xsqr"))
					|| "已确认√".equals(map.get("xsqr"))) {
				request.setAttribute("xsqr", "no");
			} else {
				request.setAttribute("xsqr", "ok");
			}
			// 获取咨询师姓名
			String num = map.get("num");
			sql = "select name from jygl_zxjsxxb where num=?";
			String[] zxsinfo = dao.getOneRs(sql, new String[] { num },
					new String[] { "name" });
			if (zxsinfo != null) {
				map.put("zxsname", zxsinfo[0]);
			}
			// 获取学生其他信息
			String xsxh = map.get("xsxh");
			sql = "select * from view_xsjbxx where xh=?";
			String[] stuinfo = dao.getOneRs(sql, new String[] { xsxh },
					new String[] { "xm", "xb", "nj", "xymc", "zymc", });
			if (stuinfo != null) {
				map.put("name", stuinfo[0]);
				map.put("xb", stuinfo[1]);
				map.put("nj", stuinfo[2]);
				map.put("xymc", stuinfo[3]);
				map.put("zymc", stuinfo[4]);
			}
			// 把提交时间转化过来
			String tjsj = null;
			String sjyear = null;
			String sjmon = null;
			String sjday = null;
			String sjhour = null;
			String sjmin = null;
			String sjss = null;
			if (null != map.get("tjsj")) {
				String sj = map.get("tjsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				sjmin = sj.substring(10, 12);
				sjss = sj.substring(12, 14);
				tjsj = sjyear + "年" + sjmon + "月" + sjday + "日 " + sjhour + ":"
						+ sjmin + ":" + sjss;
				map.put("tjsj", tjsj);
			}
			// 把咨询师确认时间转化过来
			String zxsqrsj = null;
			if (null != map.get("zxsqrsj")) {
				String sj = map.get("zxsqrsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				sjmin = sj.substring(10, 12);
				sjss = sj.substring(12, 14);
				zxsqrsj = sjyear + "年" + sjmon + "月" + sjday + "日 " + sjhour
						+ ":" + sjmin + ":" + sjss;
				map.put("zxsqrsj", zxsqrsj);
			}
			// 把学生确认时间转化过来
			String xsqrsj = null;
			if (null != map.get("xsqrsj")) {
				String sj = map.get("xsqrsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				sjmin = sj.substring(10, 12);
				sjss = sj.substring(12, 14);
				xsqrsj = sjyear + "年" + sjmon + "月" + sjday + "日 " + sjhour
						+ ":" + sjmin + ":" + sjss;
				map.put("xsqrsj", xsqrsj);
			}
		}
		String stutype = (String) request.getSession().getAttribute("userType");
		if ("stu".equals(stutype)) {
			String stuname = (String) request.getSession().getAttribute(
					"userName");
			String[] cxstu = dao.getOneRs(
					"SELECT xh,xm,xb,zymc FROM view_xsjbxx where xh=?",
					new String[] { stuname }, new String[] { "xh", "xm", "xb",
							"zymc" });
			// String xsqrsj;
			if (cxstu != null) {
				map.put("xh", cxstu[0]);
				map.put("xsxm", cxstu[1]);
				map.put("xb", cxstu[2]);
				map.put("zymc", cxstu[3]);
			}

		}
		request.setAttribute("rs", map);// 发送数据集
		return mapping.findForward("success");
	}

	// 学生咨询查询
	private ActionForward zxzxshcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommanForm dataSearchForm = (CommanForm) form;
		// String pk = "xsxh";
		String sql = ""; // sql语句
		DAO dao = DAO.getInstance();
		String tableName = "zxzyzxsqb";
		// String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		// String pkValue = request.getParameter("pkValue");
		HashMap<String, String> mapsavesq = new HashMap<String, String>();
		HashMap<String, String> mapsq = new HashMap<String, String>();
		HashMap<String, String> map = new HashMap<String, String>();
		String querry = " where 1=1 ";

		String[] colList = null;
		String[] colListCN = null;
		ArrayList<Object> rs = new ArrayList<Object>(); // 获得记录
		String rsNum = "0";
		List topTr = null;
		// HashMap<String, String> map = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();

		if (userType.equals("xy")) {
			request.setAttribute("isteacher", "ok");
		} else {
			request.setAttribute("isteacher", "no");
		}

		if ("teaview".equals(act)) {
			String pk12 = request.getParameter("rid");
			String pk1 = RowidToPk.rowidToPK(pk12);

			colList = new String[] { "rid", "xh", "xsxm", "age", "xb", "zymc",
					"email", "lxdh", "zxnr", "zxsj", "zxjshf", "hfrxm", "hfsj" };
			sql = "select ROWID rid,a.* from zxzyzxsqb a where rowid='" + pk1
					+ "'";
			map = dao.getRSArray(sql, colList);
			request.setAttribute("rs", map);
			request.setAttribute("who", "teacher");
			return mapping.findForward("success1");
		}

		if ("stuview".equals(act)) {
			String pk12 = request.getParameter("rid");
			String pk1 = RowidToPk.rowidToPK(pk12);
			colList = new String[] { "rid", "xh", "xsxm", "age", "xb", "zymc",
					"email", "lxdh", "zxnr", "zxsj", "zxjshf", "hfrxm", "hfsj" };
			sql = "select ROWID rid,a.* from zxzyzxsqb a where rowid='" + pk1
					+ "'";
			map = dao.getRSArray(sql, colList);
			// 把提交时间转化过来
			String tjsj = null;
			String sjyear = null;
			String sjmon = null;
			String sjday = null;
			String sjhour = null;
			String sjmin = null;
			String sjss = null;
			String hfsj1 = map.get("hfsj");
			if (hfsj1 == null) {
				hfsj1 = "";
			}
			if ("".equals(hfsj1)) {
				String sj = map.get("hfsj").toString();
				sjyear = sj.substring(0, 4);
				sjmon = sj.substring(4, 6);
				sjday = sj.substring(6, 8);
				sjhour = sj.substring(8, 10);
				sjmin = sj.substring(10, 12);
				sjss = sj.substring(12, 14);
				tjsj = sjyear + "年" + sjmon + "月" + sjday + "日 " + sjhour + ":"
						+ sjmin + ":" + sjss;
				map.put("hfsj", tjsj);
			}
			request.setAttribute("rs", map);
			request.setAttribute("who", "stu");
			return mapping.findForward("success2");
		}

		if (userType.equals("stu")) {
			sql = "select rowid 主键,rownum 行号,a.* from " + tableName + " a "
					+ " where xh='" + userName + "'";
			colList = new String[] { "主键", "行号", "xh", "xsxm", "zymc", "age",
					"xb", "lxdh", "zxsj", "zxjshf", "hfrxm", "hfsj" };
			colListCN = dao.getColumnNameCN(colList, tableName);
			topTr = dao.arrayToList(colList, colListCN);
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
			request.setAttribute("who", "student");

			request.setAttribute("rsp", mapsq);
			request.setAttribute("rs", rs); // 发送数据集
			request.setAttribute("topTr", topTr); // 发送表头
			request.setAttribute("rsNum", rsNum); // 发送记录数
			return mapping.findForward("success");
		}

		if ("go".equals(act)) {
			String xm = DealString.toGBK(request.getParameter("xm"));
			String xb = DealString.toGBK(request.getParameter("xb"));
			String xh = DealString.toGBK(request.getParameter("xh"));
			String lxdh = DealString.toGBK(request.getParameter("lxdh"));
			mapsq.put("xm", xm);
			mapsq.put("xb", xb);
			mapsq.put("xh", xh);
			mapsq.put("lxdh", lxdh);
			request.setAttribute("rsp", mapsq);
			if (xm == null) {
				xm = "";
			}
			if (xb == null) {
				xb = "";
			}
			if (xh == null) {
				xh = "";
			}
			if (lxdh == null) {
				lxdh = "";
			}

			if (xm.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xsxm ='" + xm + "' ";
			}
			if (xb.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xb ='" + xb + "' ";
			}
			if (xh.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and xh ='" + xh + "' ";
			}
			if (lxdh.equals("")) {
				querry += "and 1=1 ";
			} else {
				querry += "and lxdh ='" + lxdh + "' ";
			}
			sql = "select count(*) count from zxzyzxsqb a " + querry;

			dataSearchForm.getPages().setMaxRecord(
					Integer.parseInt(dao
							.getOneRs(sql, new String[] {}, "count")));

			sql = "select * from (select ROWID rid,rownum r,a.*  from (SELECT xh,xsxm,zymc,age,xb,lxdh,zxsj,zxjshf,hfrxm,hfsj FROM zxzyzxsqb a "
					+ querry
					+ " order by zxsj desc) a ) a where a.r>"
					+ dataSearchForm.getPages().getStart()
					+ " and a.r<="
					+ (dataSearchForm.getPages().getStart() + dataSearchForm
							.getPages().getPageSize()) + " order by zxsj desc";

			colList = new String[] { "rid", "xh", "xsxm", "zymc", "age", "xb",
					"lxdh", "zxsj", "zxjshf", "hfrxm", "hfsj" };
			if ("go".equals(act)) {
				// rs = dao.getArrayList2(sql, new String[] {}, colList);
				rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			}
			sql = "select count(*) from zxzyzxsqb " + querry;
			int rsNuminfo = dao.getOneRsint(sql);
			rsNum = String.valueOf(rsNuminfo);
			colListCN = dao.getColumnNameCN(colList, "zxzyzxsqb");
			topTr = dao.arrayToList(colList, colListCN);
			request.setAttribute("rs", rs);// 发送数据集
			request.setAttribute("topTr", topTr);// 发送表头
			request.setAttribute("rsNum", rsNum);// 发送记录数
			return mapping.findForward("success");

		}
		if ("answer".equals(act)) {
			String zxjshf = DealString.toGBK(request.getParameter("zxjshf"));
			String pk12 = request.getParameter("rid");
			String pk1 = RowidToPk.rowidToPK(pk12);
			String hfsj = (dao
					.getOneRs(
							"select to_char(sysdate,'yyyymmddhh24miss') sdate from dual", // 发布时间
							// //
							// 取至数据库临时表
							new String[] {}, new String[] { "sdate" }))[0];
			String[] yhxmts = dao.getOneRs(
					"SELECT * FROM jygl_zxjsxxb WHERE userid=?",
					new String[] { userName }, new String[] { "name" });
			String yhxm;
			if (yhxmts != null) {
				yhxm = yhxmts[0];
			} else {
				yhxm = "";
			}
			String ishf = (dao.getOneRs(
					"select ROWID rid,a.* from zxzyzxsqb a where rowid=?",
					new String[] { pk1 }, new String[] { "hfsj" }))[0];
			boolean judge = false;
			if (yhxm == null || "".equals(yhxm)) {
				request.setAttribute("notea", "notea");
			} else if (ishf != null) {
				request.setAttribute("ishf", "ishf");
				String rid = request.getParameter("rid");
				String xh = request.getParameter("xh");
				String xsxm = DealString.toGBK(request.getParameter("xsxm"));
				String age = request.getParameter("age");
				String xb = DealString.toGBK(request.getParameter("xb"));
				String zymc = DealString.toGBK(request.getParameter("zymc"));
				String email = request.getParameter("email");
				String lxdh = DealString.toGBK(request.getParameter("lxdh"));
				String zxnr = DealString.toGBK(request.getParameter("zxnr"));
				String zxjshf1 = DealString.toGBK(request
						.getParameter("zxjshf"));

				mapsavesq.put("rid", rid);
				mapsavesq.put("xh", xh);
				mapsavesq.put("xsxm", xsxm);
				mapsavesq.put("age", age);
				mapsavesq.put("xb", xb);
				mapsavesq.put("zymc", zymc);
				mapsavesq.put("email", email);
				mapsavesq.put("lxdh", lxdh);
				mapsavesq.put("zxnr", zxnr);
				mapsavesq.put("zxjshf", zxjshf1);
			} else {
				judge = StandardOperation.update(tableName, new String[] {
						"zxjshf", "hfrxm", "hfsj" }, new String[] { zxjshf,
						yhxm, hfsj }, "rowid", pk1, request);
				if (judge) {
					request.setAttribute("answer", "ok");
				} else {
					request.setAttribute("answer", "no");
				}
				String rid = request.getParameter("rid");
				String xh = request.getParameter("xh");
				String xsxm = DealString.toGBK(request.getParameter("xsxm"));
				String age = request.getParameter("age");
				String xb = DealString.toGBK(request.getParameter("xb"));
				String zymc = DealString.toGBK(request.getParameter("zymc"));
				String email = request.getParameter("email");
				String lxdh = DealString.toGBK(request.getParameter("lxdh"));
				String zxnr = DealString.toGBK(request.getParameter("zxnr"));
				String zxjshf1 = DealString.toGBK(request
						.getParameter("zxjshf"));

				mapsavesq.put("rid", rid);
				mapsavesq.put("xh", xh);
				mapsavesq.put("xsxm", xsxm);
				mapsavesq.put("age", age);
				mapsavesq.put("xb", xb);
				mapsavesq.put("zymc", zymc);
				mapsavesq.put("email", email);
				mapsavesq.put("lxdh", lxdh);
				mapsavesq.put("zxnr", zxnr);
				mapsavesq.put("zxjshf", zxjshf1);
			}
			request.setAttribute("rs", mapsavesq);
			return mapping.findForward("success1");
		}
		request.setAttribute("rsp", mapsq);
		request.setAttribute("rs", map);// 发送数据集
		return mapping.findForward("success");
	}

	// 安徽工程毕业生信息上报
	private ActionForward ahgcjyglBysxxSb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CommanForm actionForm = (CommanForm) form;
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql语句
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		StringBuffer querry1 = new StringBuffer("");
		String rsNum = "0";// 返回的记录数
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xy = Base.chgNull(request.getParameter("xydm"), "", 1);
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String xm = Base.chgNull(request.getParameter("xm"), "", 1);
		String go = request.getParameter("go");
		sql = "select * from xtszb where rownum=1";
		String[] result = dao.getOneRs(sql, new String[] {},
				new String[] { "dqxn" });
		String resnd = "";
		if (!Base.isNull(result[0])) {
			resnd = result[0].substring(5);
		}

		if ("imp".equalsIgnoreCase(go)) {
			String cbVal = Base.chgNull(request.getParameter("cbVal"), "", 1);
			String xxdm = StandardOperation.getXxdm();
			String xxmc = StandardOperation.getXxmc();
			String[] cbValT = cbVal.split("!!splitOne!!");
			String[] sqlT = new String[cbValT.length];

			for (int i = 1; i < cbValT.length; i++) {
				String pkT = cbValT[i];
				sqlT[i] = "insert into jygl_xsjbxxb(xsxh,ID,NAME,xbdm,zydm,zymc,xzdm,nd,xxdm,xxmc,sbsj,bynd) (SELECT xh,sfzh,xm,(CASE WHEN xb LIKE '%男%' THEN '1' WHEN xb LIKE '%女%' THEN '2' ELSE '0' END) xb,zydm,zymc,xz,dqszj,'"
						+ xxdm
						+ "','"
						+ xxmc
						+ "',(select to_char(sysdate,'yyyymmddhh24miss') sdate from dual),"
						+ resnd + " FROM view_xsjbxx where xh='" + pkT + "')";
			}
			dao.runBatch(sqlT);
			go = "go";
		}

		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = Base.chgNull(request.getParameter("zydm"), "", 1);
		String bj = Base.chgNull(request.getParameter("bjdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);

		if (!isNull(nj)) {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("' ");
		} else {

			String resnd1 = "";
			if (!Base.isNull(result[0])) {
				resnd1 = result[0].substring(5);
			} else {
				resnd1 = "";
			}
			querry.append(" and nj='");
			querry.append((Integer.parseInt(resnd1) - 4));
			querry.append("' ");
		}
		if (!isNull(xy)) {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (!isNull(zy)) {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (!isNull(bj)) {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (!isNull(xh)) {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		if (!isNull(xm)) {
			querry.append(" and xm='");
			querry.append(xm);
			querry.append("' ");
		}
		querry.append(querry1.toString());
		colList = new String[] { "pk", "xh", "xm", "xb", "xymc", "zymc",
				"bjmc", "bynd" };
		sql = "select pk,xh,xm,xb,xymc,zymc,bjmc,bynd from (select * from (select xh pk,rownum r,xh,xm,xb,xymc,zymc,bjmc,nj+xz bynd from view_xsjbxx a"
				+ querry.toString()
				+ "and (nj+xz)="
				+ resnd
				+ " and not exists(select 1 from jygl_xsjbxxb b WHERE a.xh=b.xsxh)) where r<="
				+ (actionForm.getPages().getStart() + actionForm.getPages()
						.getPageSize())
				+ ") where r>"
				+ actionForm.getPages().getStart();
		String sqlt = "select count(*) num from view_xsjbxx a"
				+ querry.toString()
				+ " and (nj+xz)="
				+ resnd
				+ " and not exists(select 1 from jygl_xsjbxxb b WHERE a.xh=b.xsxh)";
		colListCN = dao.getColumnNameCN(colList, "view_xsjbxx");
		List topTr = dao.arrayToList(colList, colListCN);
		if ((go != null) && go.equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		map.put("xydm", xy);
		map.put("zydm", zy);
		map.put("bjdm", bj);
		map.put("xm", xm);
		map.put("nj", nj);
		map.put("xh", xh);
		xy = xy == null ? "" : xy;
		zy = zy == null ? "" : zy;
		nj = nj == null ? "" : nj;
		String bjKey = xy + "!!" + zy + "!!" + nj;
		actionForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sqlt, new String[] {}, "num")));
		request.setAttribute("rs1", map);
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xy));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		return mapping.findForward("success");

	}

	// 就业信息录入
	private ActionForward jyxxlr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		CommanForm actionForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String doType = request.getParameter("doType");
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String bjdm = request.getParameter("bjdm");
		String nj = request.getParameter("nj");
		String xh = request.getParameter("xh");
		String nd = request.getParameter("nd");
		String xm = request.getParameter("xm");
		String sfjy = request.getParameter("sfjy");
		String sfqy = request.getParameter("sfqy");
		String pkStr = request.getParameter("pkStr");
		String pk = request.getParameter("pk");
		String edit = request.getParameter("edit");
		String sql = "";
		String yhm = "";
		String fdy = "";
		boolean flag = false;
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		if ("xy".equals(userType)) {
			xydm = userDep;
			actionForm.setXydm(userDep);
		}
		if (!Base.isNull(xydm)) {
			sb.append(" and xydm='");
			sb.append(xydm);
			sb.append("'");
		}
		if (!Base.isNull(zydm)) {
			sb.append(" and zydm='");
			sb.append(zydm);
			sb.append("'");
		}
		if (!Base.isNull(bjdm)) {
			sb.append(" and bjdm='");
			sb.append(bjdm);
			sb.append("'");
		}
		if (!Base.isNull(nj)) {
			sb.append(" and nj='");
			sb.append(nj);
			sb.append("'");
		}
		if (!Base.isNull(nd)) {
			sb.append(" and bynd='");
			sb.append(nd);
			sb.append("'");
		}
		if (!Base.isNull(xh)) {
			sb.append(" and xh='");
			sb.append(xh);
			sb.append("'");
		}
		if (!Base.isNull(xm)) {
			sb.append(" and xm like '%");
			sb.append(xm);
			sb.append("%'");
		}
		if (!Base.isNull(sfjy)) {
			sb.append(" and sfjy='");
			sb.append(sfjy);
			sb.append("'");
		}

		if (!Base.isNull(sfqy)) {
			if ("否".equals(sfqy)) {
				sb.append(" and (sfqy ='否' or sfqy is null)");
			} else {
				sb.append(" and sfqy='");
				sb.append(sfqy);
				sb.append("'");
			}
		}

		if (Fdypd.isFdy(userName)) {
			if (Fdypd.isFdy(userName)) {
				sb
						.append(" and exists (select 1 from fdybjb b where zgh like '"
								+ userName + "' and a.bjdm = b.bjdm)");
				fdy = "yes";
			}
		}
		if ("delete".equals(doType)) {
			sql = "delete from jygl_xsjbxxb where instr(?,'!@!'||xsxh||'!@!')>0";
			flag = dao.runUpdate(sql, new String[] { pkStr });
			doType = "query";
			request.setAttribute("result", flag);
		}
		if ("save".equals(doType)) {
			String dwmc = request.getParameter("dwmc");
			String dwdz = request.getParameter("dwdz");
			String zydk = request.getParameter("zydk");
			String qybz = request.getParameter("qybz");
			String lxr = request.getParameter("lxr");
			String lxdh = request.getParameter("lxdh");
			String jyqy = request.getParameter("jyqy");
			String syshi = request.getParameter("syshi");
			String syxian = request.getParameter("syxian");
			sql = "delete from jygl_xsjyxx where xh=?";
			flag = dao.runUpdate(sql, new String[] { xh });
			if (flag) {
				nd = Base.isNull(nd) ? Base.currNd : nd;
				sql = "select yhm from yhb where zdm in(select zdm from yhzb where zmc like '%就业办%')";
				yhm = dao.getOneRs(sql, new String[] {}, "yhm");
				String[] vals = null;
				if (!Base.isNull(yhm) || "admin".equals(userType)) {
					sql = "insert into jygl_xsjyxx (xh,nd,dwmc,dwdz,zydk,sfjy,qybz,sfqy,jyqy,lxr,lxdh,syshi,syxian) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
					vals = new String[] { xh, nd, dwmc, dwdz, zydk, sfjy, qybz,
							sfqy, jyqy, lxr, lxdh, syshi, syxian };
				} else {
					sql = "insert into jygl_xsjyxx (xh,nd,dwmc,dwdz,zydk,sfjy,qybz) values(?,?,?,?,?,?,?)";
					vals = new String[] { xh, nd, dwmc, dwdz, zydk, sfjy, qybz };
				}
				flag = dao.runUpdate(sql, vals);

			}
			doType = "update";
			request.setAttribute("result", flag);
		}
		if ("update".equals(doType) || "view".equals(doType)) {
			sql = "select * from view_xsjyxx where xh=?";
			HashMap<String, String> map = dao.getMapNotOut(sql,
					new String[] { pk });
			if (!"stu".equals(userType)) {
				request.setAttribute("edit", "yes");
			} else {
				request.setAttribute("edit", edit);
			}
			sql = "select byqxdm dm,byqx mc from dmk_byqx";
			request.setAttribute("byqxList", dao.getListNotOut(sql,
					new String[] {}));
			sql = "select sydqdm dm,sydq mc from dmk_sydq";
			request.setAttribute("sydqList", dao.getListNotOut(sql,
					new String[] {}));
			request.setAttribute("rs", map);
			request.setAttribute("lx", doType);
			StuInfoDAO stuDao = new StuInfoDAO();
			request.setAttribute("ssList", stuDao.getSsList());
			request.setAttribute("syshiList", stuDao
					.getShiList(map.get("jyqy")).get("shiList"));
			request.setAttribute("syxianList", stuDao.getShiList(
					map.get("syshi")).get("xianList"));
			return mapping.findForward("operationOne");
		}

		if ("query".equals(doType)) {
			sql = "select * from (select a.*,nvl(nd,'" + nd
					+ "') bynd from view_xsjyxx a) a " + sb;
			List<HashMap<String, String>> rs = dao.getListNotOut(sql,
					new String[] {});
			String[] en = new String[] { "pk", "xh", "xm", "bynd", "nj",
					"xymc", "zymc", "bjmc", "dwmc", "lxdh", "sfjy", "sfqy" };
			String[] cn = new String[] { "pk", "学号", "姓名", "年度", "年级", "学院名称",
					"专业名称", "班级名称", "单位名称", "单位联系电话", "是否就业", "是否签约" };
			List topTr = dao.arrayToList(en, cn);
			request.setAttribute("topTr", topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs.size());

		}
		if (nd == null) {
			actionForm.setNd(Base.currNd);
		}
		request.setAttribute("realTable", "jygl_xsjbxxb");
		request.setAttribute("tableName", "view_xsjyxx");
		request.setAttribute("userName ", userName);
		request.setAttribute("ndList", dao.getNdList());
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", dao.getZyList(actionForm.getXydm()));// 发送专业列表
		request.setAttribute("bjList", dao.getBjList(actionForm.getXydm(),
				actionForm.getZydm(), actionForm.getNj()));// 发送班级列表
		if ("yes".equals(fdy)) {
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));
			request.setAttribute("xyList", Fdypd.getFdyXyList(userName));
			request.setAttribute("userName ", userName);
			request.setAttribute("fdy", fdy);

		}
		return mapping.findForward("success");
	}

	// 就业信息录入
	private ActionForward jyxxcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		CommanForm actionForm = (CommanForm) form;
		HttpSession session = request.getSession();
		// String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String doType = request.getParameter("doType");
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String bjdm = request.getParameter("bjdm");
		String nj = request.getParameter("nj");
		String xh = request.getParameter("xh");
		String nd = request.getParameter("nd");
		String xm = request.getParameter("xm");
		String sfjy = request.getParameter("sfjy");
		String sfqy = request.getParameter("sfqy");
		String sql = "";
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		if ("xy".equals(userType)) {
			xydm = userDep;
			actionForm.setXydm(userDep);
		}
		if (!Base.isNull(xydm)) {
			sb.append(" and xydm='");
			sb.append(xydm);
			sb.append("'");
		}
		if (!Base.isNull(zydm)) {
			sb.append(" and zydm='");
			sb.append(zydm);
			sb.append("'");
		}
		if (!Base.isNull(bjdm)) {
			sb.append(" and bjdm='");
			sb.append(bjdm);
			sb.append("'");
		}
		if (!Base.isNull(nj)) {
			sb.append(" and nj='");
			sb.append(nj);
			sb.append("'");
		}
		if (!Base.isNull(nd)) {
			sb.append(" and bynd='");
			sb.append(nd);
			sb.append("'");
		}
		if (!Base.isNull(xh)) {
			sb.append(" and xh='");
			sb.append(xh);
			sb.append("'");
		}
		if (!Base.isNull(xm)) {
			sb.append(" and xm like '%");
			sb.append(xm);
			sb.append("%'");
		}
		if (!Base.isNull(sfjy)) {
			sb.append(" and sfjy='");
			sb.append(sfjy);
			sb.append("'");
		}
		if (!Base.isNull(sfqy)) {
			sb.append(" and sfqy='");
			sb.append(sfqy);
			sb.append("'");
		}
		if ("query".equals(doType)) {
			sql = "select * from (select a.*,nvl(nd,'" + nd
					+ "') bynd from view_xsjyxx a) " + sb;
			List<HashMap<String, String>> rs = dao.getListNotOut(sql,
					new String[] {});
			String[] en = new String[] { "pk", "xh", "xm", "xymc", "zymc",
					"bjmc", "dwmc", "lxdh", "sfjy", "sfqy" };
			String[] cn = new String[] { "pk", "学号", "姓名", "学院名称", "专业名称",
					"班级名称", "单位名称", "单位联系电话", "是否就业", "是否签约" };
			List topTr = dao.arrayToList(en, cn);
			request.setAttribute("topTr", topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs.size());
		}
		if (nd == null) {
			actionForm.setNd(Base.currNd);
		}
		request.setAttribute("realTable", "jygl_xsjyxx");
		request.setAttribute("tableName", "view_xsjyxx");
		request.setAttribute("ndList", dao.getNdList());
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", dao.getZyList(actionForm.getXydm()));// 发送专业列表
		request.setAttribute("bjList", dao.getBjList(actionForm.getXydm(),
				actionForm.getZydm(), actionForm.getNj()));// 发送班级列表
		return mapping.findForward("success");
	}

	// 就业信息统计
	private ActionForward jyxxtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		CommanForm actionForm = (CommanForm) form;
		HttpSession session = request.getSession();
		// String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String doType = request.getParameter("doType");
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String bjdm = request.getParameter("bjdm");
		String nj = request.getParameter("nj");
		String nd = request.getParameter("nd");
		String lb = request.getParameter("lb");
		String sql = "";
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		String[] en = null;
		String[] cn = null;
		String tjlb = "";
		if ("xy".equals(userType)) {
			xydm = userDep;
			actionForm.setXydm(userDep);
		}
		if (!Base.isNull(xydm)) {
			sb.append(" and xydm='");
			sb.append(xydm);
			sb.append("'");
		}
		if (!Base.isNull(zydm)) {
			sb.append(" and zydm='");
			sb.append(zydm);
			sb.append("'");
		}
		if (!Base.isNull(bjdm)) {
			sb.append(" and bjdm='");
			sb.append(bjdm);
			sb.append("'");
		}
		if (!Base.isNull(nj)) {
			sb.append(" and nj='");
			sb.append(nj);
			sb.append("'");
		}
		if (!Base.isNull(nd)) {
			sb.append(" and nd='");
			sb.append(nd);
			sb.append("'");
		}

		if ("tj".equals(doType) || "tjdc".equals(doType)) {
			if ("xydm".equals(lb)) {
				en = new String[] { "xymc", "zrs", "jyrs", "jyl", "qyrs", "qyl" };
				cn = new String[] { "学院名称", "总人数", "就业人数", "就业率", "签约人数", "签约率" };
				sql = "select a.xymc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0)qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
						+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xydm,xymc from view_xsjyxx "
						+ sb
						+ " group by xydm,xymc) a left join (select count(xh) "
						+ "jyrs,xydm from view_xsjyxx "
						+ sb
						+ " and sfjy='是' group by xydm) b on a.xydm=b.xydm) a left join (select count(xh) qyrs,xydm from "
						+ "view_xsjyxx "
						+ sb
						+ " and sfqy='是' group by xydm) b on a.xydm=b.xydm";
			} else if ("zydm".equals(lb)) {
				en = new String[] { "xymc", "zymc", "zrs", "jyrs", "jyl",
						"qyrs", "qyl" };
				cn = new String[] { "学院名称", "专业名称", "总人数", "就业人数", "就业率",
						"签约人数", "签约率" };
				sql = "select a.xymc,a.zymc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0) qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
						+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xymc,zydm,zymc from view_xsjyxx "
						+ sb
						+ " group by xydm,xymc,zydm,zymc) a left join (select count(xh) "
						+ "jyrs,zydm from view_xsjyxx "
						+ sb
						+ " and sfjy='是' group by xydm,zydm) b on a.zydm=b.zydm) a left join (select count(xh) qyrs,zydm from "
						+ "view_xsjyxx  "
						+ sb
						+ " and sfqy='是' group by xydm,zydm) b on a.zydm=b.zydm";
			} else if ("bjdm".equals(lb)) {
				en = new String[] { "xymc", "zymc", "bjmc", "zrs", "jyrs",
						"jyl", "qyrs", "qyl" };
				cn = new String[] { "学院名称", "专业名称", "班级名称", "总人数", "就业人数",
						"就业率", "签约人数", "签约率" };
				sql = "select a.xymc,a.zymc,a.bjmc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0)qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
						+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xymc,zymc,bjdm,bjmc from view_xsjyxx "
						+ sb
						+ " group by xydm,xymc,zydm,zymc,bjdm,bjmc) a left join (select count(xh) "
						+ "jyrs,zydm,bjdm from view_xsjyxx "
						+ sb
						+ " and sfjy='是' group by xydm,zydm,bjdm) b on a.bjdm=b.bjdm) a left join (select count(xh) qyrs,zydm,bjdm from "
						+ "view_xsjyxx  "
						+ sb
						+ " and sfqy='是' group by xydm,zydm,bjdm) b on a.bjdm=b.bjdm";
			} else {
				if (!Base.isNull(bjdm)) {
					tjlb = "bjdm";
				} else if (!Base.isNull(zydm)) {
					tjlb = "zydm";
				} else {
					tjlb = "xydm";
				}
				if ("jyqy".equals(lb)) {
					if ("bjdm".equals(tjlb)) {
						en = new String[] { "xymc", "zymc", "bjmc", "jyqy",
								"zrs", "jyrs", "jyl", "qyrs", "qyl" };
						cn = new String[] { "学院名称", "专业名称", "班级名称", "就业区域",
								"总人数", "就业人数", "就业率", "签约人数", "签约率" };
						sql = "select a.xymc,a.zymc,a.bjmc,a.jyqymc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0)qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
								+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xymc,zymc,bjdm,bjmc,jyqy,jyqymc from view_xsjyxx "
								+ sb
								+ " group by xydm,xymc,zydm,zymc,bjdm,bjmc,jyqy,jyqymc) a left join (select count(xh) "
								+ "jyrs,zydm,bjdm,jyqy from view_xsjyxx "
								+ sb
								+ " and sfjy='是' group by xydm,zydm,bjdm,jyqy) b on a.bjdm=b.bjdm and a.jyqy=b.jyqy)  a left join (select count(xh) qyrs,zydm,bjdm,jyqy from "
								+ "view_xsjyxx  "
								+ sb
								+ " and sfqy='是' group by xydm,zydm,bjdm,jyqy) b on a.bjdm=b.bjdm and a.jyqy=b.jyqy ";
					} else if ("zydm".equals(tjlb)) {
						en = new String[] { "xymc", "zymc", "jyqy", "zrs",
								"jyrs", "jyl", "qyrs", "qyl" };
						cn = new String[] { "学院名称", "专业名称", "就业区域", "总人数",
								"就业人数", "就业率", "签约人数", "签约率" };
						sql = "select a.xymc,a.zymc,a.jyqymc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0)qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
								+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xymc,zydm,zymc,jyqy,jyqymc from view_xsjyxx "
								+ sb
								+ " group by xydm,xymc,zydm,zymc,jyqy,jyqymc) a left join (select count(xh) "
								+ "jyrs,zydm,jyqy from view_xsjyxx "
								+ sb
								+ " and sfjy='是' group by xydm,zydm,jyqy) b on a.zydm=b.zydm and a.jyqy=b.jyqy) a left join (select count(xh) qyrs,zydm,jyqy from "
								+ "view_xsjyxx  "
								+ sb
								+ " and sfqy='是' group by xydm,zydm,jyqy) b on a.zydm=b.zydm and a.jyqy=b.jyqy ";
					} else {
						en = new String[] { "xymc", "jyqy", "zrs", "jyrs",
								"jyl", "qyrs", "qyl" };
						cn = new String[] { "学院名称", "就业区域", "总人数", "就业人数",
								"就业率", "签约人数", "签约率" };
						sql = "select a.xymc,a.jyqymc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0)qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
								+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xydm,xymc,jyqy,jyqymc from view_xsjyxx "
								+ sb
								+ " group by xydm,xymc,jyqy,jyqymc) a left join (select count(xh) "
								+ "jyrs,xydm,jyqy from view_xsjyxx "
								+ sb
								+ " and sfjy='是' group by xydm,jyqy) b on a.xydm=b.xydm and a.jyqy=b.jyqy) a left join (select count(xh) qyrs,xydm,jyqy from "
								+ "view_xsjyxx  "
								+ sb
								+ " and sfqy='是' group by xydm,jyqy) b on a.xydm=b.xydm  and a.jyqy=b.jyqy ";
					}
				} else if ("sydqdm".equals(lb)) {
					if ("bjdm".equals(tjlb)) {
						en = new String[] { "xymc", "zymc", "bjmc", "sydq",
								"zrs", "jyrs", "jyl", "qyrs", "qyl" };
						cn = new String[] { "学院名称", "专业名称", "班级名称", "生源地区",
								"总人数", "就业人数", "就业率", "签约人数", "签约率" };
						sql = "select a.xymc,a.zymc,a.bjmc,a.sydqmc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0)qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
								+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xymc,zymc,bjdm,bjmc,sydqdm,sydqmc from view_xsjyxx "
								+ sb
								+ " group by xydm,xymc,zydm,zymc,bjdm,bjmc,sydqdm,sydqmc) a left join (select count(xh) "
								+ "jyrs,zydm,bjdm,sydqdm from view_xsjyxx "
								+ sb
								+ " and sfjy='是' group by xydm,zydm,bjdm,sydqdm) b on a.bjdm=b.bjdm and a.sydqdm=b.sydqdm) a left join (select count(xh) qyrs,zydm,bjdm,sydqdm from "
								+ "view_xsjyxx  "
								+ sb
								+ " and sfqy='是' group by xydm,zydm,bjdm,sydqdm) b on a.bjdm=b.bjdm  and a.sydqdm=b.sydqdm ";
					} else if ("zydm".equals(tjlb)) {
						en = new String[] { "xymc", "zymc", "sydq", "zrs",
								"jyrs", "jyl", "qyrs", "qyl" };
						cn = new String[] { "学院名称", "专业名称", "生源地区", "总人数",
								"就业人数", "就业率", "签约人数", "签约率" };
						sql = "select a.xymc,a.zymc,a.sydqmc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0)qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
								+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xymc,zydm,zymc,sydqdm,sydqmc from view_xsjyxx "
								+ sb
								+ " group by xydm,xymc,zydm,zymc,sydqdm,sydqmc) a left join(select count(xh) "
								+ "jyrs,zydm,sydqdm from view_xsjyxx "
								+ sb
								+ " and sfjy='是' group by xydm,zydm,sydqdm) b on a.zydm=b.zydm and a.sydqdm=b.sydqdm) a left join (select count(xh) qyrs,zydm,sydqdm from "
								+ "view_xsjyxx  "
								+ sb
								+ " and sfqy='是' group by xydm,zydm,sydqdm) b on a.zydm=b.zydm and a.sydqdm=b.sydqdm ";
					} else {
						en = new String[] { "xymc", "sydq", "zrs", "jyrs",
								"jyl", "qyrs", "qyl" };
						cn = new String[] { "学院名称", "生源地区", "总人数", "就业人数",
								"就业率", "签约人数", "签约率" };
						sql = "select a.xymc,a.sydqmc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0)qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
								+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xydm,xymc,sydqdm,sydqmc from view_xsjyxx "
								+ sb
								+ " group by xydm,xymc,sydqdm,sydqmc) a left join (select count(xh) "
								+ "jyrs,xydm,sydqdm from view_xsjyxx "
								+ sb
								+ " and sfjy='是' group by xydm,sydqdm) b on a.xydm=b.xydm and a.sydqdm=b.sydqdm) a left join (select count(xh) qyrs,xydm,sydqdm from "
								+ "view_xsjyxx  "
								+ sb
								+ " and sfqy='是' group by xydm,sydqdm) b on a.xydm=b.xydm and a.sydqdm=b.sydqdm";
					}
				} else if ("qybz".equals(lb)) {
					if ("bjdm".equals(tjlb)) {
						en = new String[] { "xymc", "zymc", "bjmc", "qybz",
								"zrs", "jyrs", "jyl", "qyrs", "qyl" };
						cn = new String[] { "学院名称", "专业名称", "班级名称", "签约标志",
								"总人数", "就业人数", "就业率", "签约人数", "签约率" };
						sql = "select a.xymc,a.zymc,a.bjmc,a.qybzmc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0)qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
								+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xymc,zymc,bjdm,bjmc,qybz,qybzmc from view_xsjyxx "
								+ sb
								+ " group by xydm,xymc,zydm,zymc,bjdm,bjmc,qybz,qybzmc) a left join (select count(xh) "
								+ "jyrs,zydm,bjdm,qybz from view_xsjyxx "
								+ sb
								+ " and sfjy='是' group by xydm,zydm,bjdm,qybz) b on a.bjdm=b.bjdm and a.qybz=b.qybz) a left join (select count(xh) qyrs,zydm,bjdm,qybz from "
								+ "view_xsjyxx  "
								+ sb
								+ " and sfqy='是' group by xydm,zydm,bjdm,qybz) b on a.bjdm=b.bjdm  and a.qybz=b.qybz ";
					} else if ("zydm".equals(tjlb)) {
						en = new String[] { "xymc", "zymc", "qybz", "zrs",
								"jyrs", "jyl", "qyrs", "qyl" };
						cn = new String[] { "学院名称", "专业名称", "签约标志", "总人数",
								"就业人数", "就业率", "签约人数", "签约率" };
						sql = "select a.xymc,a.zymc,a.qybzmc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0)qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
								+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xymc,zydm,zymc,qybz,qybzmc from view_xsjyxx "
								+ sb
								+ " group by xydm,xymc,zydm,zymc,qybz,qybzmc) a left join (select count(xh) "
								+ "jyrs,zydm,qybz from view_xsjyxx "
								+ sb
								+ " and sfjy='是' group by xydm,zydm,qybz) b on a.zydm=b.zydm and a.qybz=b.qybz) a left join (select count(xh) qyrs,zydm,qybz from "
								+ "view_xsjyxx  "
								+ sb
								+ " and sfqy='是' group by xydm,zydm,qybz) b on a.zydm=b.zydm and a.qybz=b.qybz ";
					} else {
						en = new String[] { "xymc", "qybz", "zrs", "jyrs",
								"jyl", "qyrs", "qyl" };
						cn = new String[] { "学院名称", "签约标志", "总人数", "就业人数",
								"就业率", "签约人数", "签约率" };
						sql = "select a.xymc,a.qybzmc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0)qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
								+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xydm,xymc,qybz,qybzmc from view_xsjyxx "
								+ sb
								+ " group by xydm,xymc,qybz,qybzmc) a left join (select count(xh) "
								+ "jyrs,xydm,qybz from view_xsjyxx "
								+ sb
								+ " and sfjy='是' group by xydm,qybz) b on a.xydm=b.xydm and a.qybz=b.qybz) a left join (select count(xh) qyrs,xydm,qybz from "
								+ "view_xsjyxx  "
								+ sb
								+ " and sfqy='是' group by xydm,qybz) b on a.xydm=b.xydm  and a.qybz=b.qybz ";
					}
				} else if ("xscc".equals(lb)) {
					if ("bjdm".equals(tjlb)) {
						en = new String[] { "xymc", "zymc", "bjmc", "xscc",
								"zrs", "jyrs", "jyl", "qyrs", "qyl" };
						cn = new String[] { "学院名称", "专业名称", "班级名称", "学生层次",
								"总人数", "就业人数", "就业率", "签约人数", "签约率" };
						sql = "select a.xymc,a.zymc,a.bjmc,a.xsccmc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0)qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
								+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xymc,zymc,bjdm,bjmc,xscc,xsccmc from view_xsjyxx "
								+ sb
								+ " group by xydm,xymc,zydm,zymc,bjdm,bjmc,xscc,xsccmc) a left join (select count(xh) "
								+ "jyrs,zydm,bjdm,xscc from view_xsjyxx "
								+ sb
								+ " and sfjy='是' group by xydm,zydm,bjdm,xscc) b on a.bjdm=b.bjdm and a.xscc=b.xscc) a left join (select count(xh) qyrs,zydm,bjdm,xscc from "
								+ "view_xsjyxx  "
								+ sb
								+ " and sfqy='是' group by xydm,zydm,bjdm,xscc) b on a.bjdm=b.bjdm  and a.xscc=b.xscc ";
					} else if ("zydm".equals(tjlb)) {
						en = new String[] { "xymc", "zymc", "xscc", "zrs",
								"jyrs", "jyl", "qyrs", "qyl" };
						cn = new String[] { "学院名称", "专业名称", "学生层次", "总人数",
								"就业人数", "就业率", "签约人数", "签约率" };
						sql = "select a.xymc,a.zymc,a.xsccmc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0)qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
								+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xymc,zydm,zymc,xscc,xsccmc from view_xsjyxx "
								+ sb
								+ " group by xydm,xymc,zydm,zymc,xscc,xsccmc) a left join (select count(xh) "
								+ "jyrs,zydm,xscc from view_xsjyxx "
								+ sb
								+ " and sfjy='是' group by xydm,zydm,xscc) b on a.zydm=b.zydm and a.xscc=b.xscc) a left join (select count(xh) qyrs,zydm,xscc from "
								+ "view_xsjyxx  "
								+ sb
								+ " and sfqy='是' group by xydm,zydm,xscc) b on a.zydm=b.zydm and a.xscc=b.xscc ";
					} else {
						en = new String[] { "xymc", "xscc", "zrs", "jyrs",
								"jyl", "qyrs", "qyl" };
						cn = new String[] { "学院名称", "学生层次", "总人数", "就业人数",
								"就业率", "签约人数", "签约率" };
						sql = "select a.xymc,a.xsccmc,zrs,jyrs,round(jyrs*10000/zrs)/100||'%' jyl,nvl(b.qyrs,0)qyrs,round(nvl(b.qyrs,0)*10000/zrs)/100||'%' "
								+ "qyl from (select a.*,nvl(b.jyrs,0)jyrs from (select count(xh) zrs,xydm,xymc,xscc,xsccmc from view_xsjyxx "
								+ sb
								+ " group by xydm,xymc,xscc,xsccmc) a left join (select count(xh) "
								+ "jyrs,xydm,xscc from view_xsjyxx "
								+ sb
								+ " and sfjy='是' group by xydm,xscc) b on a.xydm=b.xydm and a.xscc=b.xscc) a left join (select count(xh) qyrs,xydm,xscc from "
								+ "view_xsjyxx  "
								+ sb
								+ " and sfqy='是' group by xydm,xscc) b on a.xydm=b.xydm and a.xscc=b.xscc";
					}
				}
			}
			Vector<String[]> rs = dao.rsToVatorNotOut(sql, new String[] {});

			List topTr = dao.arrayToList(en, cn);
			request.setAttribute("topTr", topTr);
			if ("tjdc".equals(doType)) {
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				ExcelMB ex = new ExcelMB();
				WritableWorkbook wwb = Workbook.createWorkbook(response
						.getOutputStream());
				WritableSheet ws = wwb.createSheet("就业率签约率统计表", 0);
				WritableCellFormat wcf2 = new WritableCellFormat();
				wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
						Alignment.CENTRE, VerticalAlignment.CENTRE, true,
						Border.ALL);
				for (int i = 0; i < cn.length; i++) {
					ws.addCell(new Label(i, 0, cn[i], wcf2));
					ex.printToOneCell_multy(ws, cn[i], i, 0, 10, true,
							Alignment.CENTRE, VerticalAlignment.CENTRE, false,
							650, Border.ALL);
				}
				for (int i = 0; i < rs.size(); i++) {
					String[] array = rs.get(i);
					for (int j = 0; j < array.length; j++) {
						ws.addCell(new Label(j, i + 1, array[j], wcf2));
					}
				}
				ExcelMethods.submitWritableWorkbookOperations(wwb);
				return mapping.findForward("");
			}
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs.size());
		}
		if (nd == null) {
			actionForm.setNd(Base.currNd);
		}
		request.setAttribute("realTable", "jygl_xsjyxx");
		request.setAttribute("tableName", "view_xsjyxx");
		request.setAttribute("ndList", dao.getNdList());
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", dao.getZyList(actionForm.getXydm()));// 发送专业列表
		request.setAttribute("bjList", dao.getBjList(actionForm.getXydm(),
				actionForm.getZydm(), actionForm.getNj()));// 发送班级列表
		return mapping.findForward("success");
	}

	public HashMap<String, String> getMapInfo(HashMap<String, String> map,
			String[] colList, String[] valList) {
		if (colList != null && valList != null
				&& colList.length == valList.length) {
			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i], valList[i]);
			}
		}
		return map;
	}

	/**
	 * 就业月度统计
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ydtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		DAO dao = DAO.getInstance();
		CommanForm commanForm = (CommanForm) form;
		String doType = request.getParameter("doType");
		String tjfs = request.getParameter("tjfs");
		String[] topTr = null;
		String[] colList = null;
		String sql = "";
		StringBuilder sb = new StringBuilder();
		sb.append(" where 1=1 ");

		if ("xy".equals(userType)) {
			commanForm.setXydm(userDep);
		}

		if (!Base.isNull(commanForm.getNd())) {
			sb.append(" and nd='");
			sb.append(commanForm.getNd());
			sb.append("'");
		}

		if (!Base.isNull(commanForm.getNj())) {
			sb.append(" and nj='");
			sb.append(commanForm.getNj());
			sb.append("'");
		}

		if (!Base.isNull(commanForm.getXydm())) {
			sb.append(" and xydm='");
			sb.append(commanForm.getXydm());
			sb.append("'");
		}

		if (!Base.isNull(commanForm.getZydm())) {
			sb.append(" and zydm='");
			sb.append(commanForm.getZydm());
			sb.append("'");
		}

		if (!Base.isNull(commanForm.getBjdm())) {
			sb.append(" and bjdm='");
			sb.append(commanForm.getBjdm());
			sb.append("'");
		}

		if (!Base.isNull(doType)) {
			if ("nj".equals(tjfs)) {
				sql = "select * from (select a.*,'"
						+ commanForm.getMonth()
						+ "' yf,"
						+ "case when a.zrs>0 then (trunc(a.jyrs/a.zrs,2))*100||'%' else '0%' end jyl,"
						+ " case when a.zrs>0 then (trunc(a.qyrs/a.zrs,2))*100||'%' else '0%' end qyl"
						+ " from (  select a.*,nvl(b.qyrs,0) qyrs from ( select a.* ,nvl(b.jyrs,0) jyrs from "
						+ "(select count(a.xh) zrs,a.nd,a.nj from view_xsjyxx a group by a.nj, a.nd ) a left join "
						+ "(select count(xh) jyrs,nj,nd from view_xsjyxx where sfjy = '是' and substr(lrsj, 5, 2)="
						+ commanForm.getMonth()
						+ " group by nj,nd) b"
						+ " on a.nj=b.nj and a.nd=b.nd ) a left join "
						+ "(select count(xh) qyrs,nj,nd from view_xsjyxx where sfqy = '是' and substr(lrsj, 5, 2)="
						+ commanForm.getMonth() + " group by nj,nd) b"
						+ " on a.nj=b.nj and a.nd=b.nd ) a )" + sb.toString();
				topTr = new String[] { "年级", "年度", "毕业总人数", "月份", "就业人数",
						"签约人数", "就业率", "签约率" };
				colList = new String[] { "nj", "nd", "zrs", "yf", "jyrs",
						"qyrs", "jyl", "qyl" };
			} else if ("xy".equals(tjfs)) {
				sql = "select * from (select a.*,'"
						+ commanForm.getMonth()
						+ "' yf,"
						+ "(select xymc from view_njxyzybj where xydm=a.xydm and rownum=1) xymc,"
						+ "case when a.zrs>0 then (trunc(a.jyrs/a.zrs,2))*100||'%' else '0%' end jyl,"
						+ " case when a.zrs>0 then (trunc(a.qyrs/a.zrs,2))*100||'%' else '0%' end qyl"
						+ " from (  select a.*,nvl(b.qyrs,0) qyrs from ( select a.* ,nvl(b.jyrs,0) jyrs from "
						+ "(select count(a.xh) zrs,a.nd,a.xydm from view_xsjyxx a group by a.xydm, a.nd ) a left join "
						+ "(select count(xh) jyrs,xydm,nd from view_xsjyxx where sfjy = '是' and substr(lrsj, 5, 2)="
						+ commanForm.getMonth()
						+ " group by xydm,nd) b"
						+ " on a.xydm=b.xydm and a.nd=b.nd ) a left join "
						+ "(select count(xh) qyrs,xydm,nd from view_xsjyxx where sfqy = '是' and substr(lrsj, 5, 2)="
						+ commanForm.getMonth() + " group by xydm,nd) b"
						+ " on a.xydm=b.xydm and a.nd=b.nd ) a )"
						+ sb.toString();
				topTr = new String[] { "学院", "年度", "毕业总人数", "月份", "就业人数",
						"签约人数", "就业率", "签约率" };
				colList = new String[] { "xymc", "nd", "zrs", "yf", "jyrs",
						"qyrs", "jyl", "qyl" };
			} else if ("zy".equals(tjfs)) {
				sql = "select * from (select a.*,'"
						+ commanForm.getMonth()
						+ "' yf,"
						+ "(select xymc from view_njxyzybj where zydm=a.zydm and rownum=1) xymc,"
						+ "(select xydm from view_njxyzybj where zydm=a.zydm and rownum=1) xydm,"
						+ "(select zymc from view_njxyzybj where zydm=a.zydm and rownum=1) zymc,"
						+ "case when a.zrs>0 then (trunc(a.jyrs/a.zrs,2))*100||'%' else '0%' end jyl,"
						+ " case when a.zrs>0 then (trunc(a.qyrs/a.zrs,2))*100||'%' else '0%' end qyl"
						+ " from (  select a.*,nvl(b.qyrs,0) qyrs from ( select a.* ,nvl(b.jyrs,0) jyrs from "
						+ "(select count(a.xh) zrs,a.nd,a.zydm from view_xsjyxx a group by a.zydm, a.nd ) a left join "
						+ "(select count(xh) jyrs,zydm,nd from view_xsjyxx where sfjy = '是' and substr(lrsj, 5, 2)="
						+ commanForm.getMonth()
						+ " group by zydm,nd) b"
						+ " on a.zydm=b.zydm and a.nd=b.nd ) a left join "
						+ "(select count(xh) qyrs,zydm,nd from view_xsjyxx where sfqy = '是' and substr(lrsj, 5, 2)="
						+ commanForm.getMonth() + " group by zydm,nd) b"
						+ " on a.zydm=b.zydm and a.nd=b.nd ) a )"
						+ sb.toString();
				topTr = new String[] { "学院", "专业", "年度", "毕业总人数", "月份", "就业人数",
						"签约人数", "就业率", "签约率" };
				colList = new String[] { "xymc", "zymc", "nd", "zrs", "yf",
						"jyrs", "qyrs", "jyl", "qyl" };
			} else {
				sql = "select * from (select a.*,'"
						+ commanForm.getMonth()
						+ "' yf,"
						+ "(select nj from view_njxyzybj where bjdm=a.bjdm and rownum=1) nj,"
						+ "(select bjmc from view_njxyzybj where bjdm=a.bjdm and rownum=1) bjmc,"
						+ "(select xymc from view_njxyzybj where bjdm=a.bjdm and rownum=1) xymc,"
						+ "(select xydm from view_njxyzybj where bjdm=a.bjdm and rownum=1) xydm,"
						+ "(select zymc from view_njxyzybj where bjdm=a.bjdm and rownum=1) zymc,"
						+ "case when a.zrs>0 then (trunc(a.jyrs/a.zrs,2))*100||'%' else '0%' end jyl,"
						+ " case when a.zrs>0 then (trunc(a.qyrs/a.zrs,2))*100||'%' else '0%' end qyl"
						+ " from (  select a.*,nvl(b.qyrs,0) qyrs from ( select a.* ,nvl(b.jyrs,0) jyrs from "
						+ "(select count(a.xh) zrs,a.nd,a.bjdm from view_xsjyxx a group by a.bjdm, a.nd ) a left join "
						+ "(select count(xh) jyrs,bjdm,nd from view_xsjyxx where sfjy = '是' and substr(lrsj, 5, 2)="
						+ commanForm.getMonth()
						+ " group by bjdm,nd) b"
						+ " on a.bjdm=b.bjdm and a.nd=b.nd ) a left join "
						+ "(select count(xh) qyrs,bjdm,nd from view_xsjyxx where sfqy = '是' and substr(lrsj, 5, 2)="
						+ commanForm.getMonth() + " group by bjdm,nd) b"
						+ " on a.bjdm=b.bjdm and a.nd=b.nd ) a )"
						+ sb.toString();
				topTr = new String[] { "年级", "学院", "专业", "班级", "年度", "毕业总人数",
						"月份", "就业人数", "签约人数", "就业率", "签约率" };
				colList = new String[] { "nj", "xymc", "zymc", "bjmc", "nd",
						"zrs", "yf", "jyrs", "qyrs", "jyl", "qyl" };
			}

			List<String[]> rs = dao.rsToVator(sql, new String[] {}, colList);
			if (null != rs && "query".equals(doType)) {
				request.setAttribute("rsNum", rs.size());
				request.setAttribute("rs", rs);
			}

			if (null != rs && "expData".equals(doType)) {
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				ExcelMB ex = new ExcelMB();
				WritableWorkbook wwb = Workbook.createWorkbook(response
						.getOutputStream());
				WritableSheet ws = wwb.createSheet("月度就业统计表", 0);
				WritableCellFormat wcf2 = new WritableCellFormat();
				wcf2 = ExcelMethods.getWcf(WritableFont.ARIAL, 10, false,
						Alignment.CENTRE, VerticalAlignment.CENTRE, true,
						Border.ALL);
				for (int i = 0; i < topTr.length; i++) {
					ws.addCell(new Label(i, 0, topTr[i], wcf2));
					ex.printToOneCell_multy(ws, topTr[i], i, 0, 10, true,
							Alignment.CENTRE, VerticalAlignment.CENTRE, false,
							650, Border.ALL);
				}
				for (int i = 0; i < rs.size(); i++) {
					String[] array = rs.get(i);
					for (int j = 0; j < array.length; j++) {
						ws.addCell(new Label(j, i + 1, array[j], wcf2));
					}
				}
				ExcelMethods.submitWritableWorkbookOperations(wwb);
				return mapping.findForward("");
			}
		}

		request.setAttribute("topTr", topTr);
		request.setAttribute("monthList", getMonthList());
		request.setAttribute("path", "jyydbb.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
		return mapping.findForward("ydtj");
	}

	/**
	 * 月份列表
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getMonthList() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		for (int i = 1; i < 10; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", "0" + i);
			list.add(map);
		}
		for (int i = 10; i < 13; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("en", String.valueOf(i));
			list.add(map);
		}

		return list;
	}
}
