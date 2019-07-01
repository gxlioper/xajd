/*
 * 创建日期 2006-10-31
 *
 *  要更改此生成的文件的模板，请转至
 * 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
package xgxt.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.utils.Check_Input_Value;
import xgxt.utils.String.StringUtils;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import common.Globals;
import common.GlobalsVariable;

/**
 * @author bat_zzj
 * 
 * 要更改此生成的类型注释的模板，请转至 窗口 － 首选项 － Java － 代码样式 － 代码模板
 */
public class MakePDF extends Action {

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

			ActionForward myActFwd = null;
			String act = mapping.getParameter();

			if (act.equalsIgnoreCase("nameList")) {
				myActFwd = nameList(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("prisePrint")) {
				myActFwd = prisePrint(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("stuCardPrint")) {
				myActFwd = stuCardPrint(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("autoCardPrint")) {
				myActFwd = autoCardPrint(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("noticePrintOne")) {
				myActFwd = noticePrintOne(mapping, form, request, response);
			}
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			chkUser.setErrMsg("出现灾难性故障，");
			return new ActionForward("/errMsg.do", false);
		}
	}

	protected Font getChineseFont(int nfontsize, boolean isBold)
			throws IOException, DocumentException {
		BaseFont bfChinese;
		if (isBold) {
			bfChinese = BaseFont.createFont("STSong-Light,Bold",
					"UniGB-UCS2-H", BaseFont.EMBEDDED);
		} else {
			bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
					BaseFont.EMBEDDED);
		}
		Font FontChinese = new Font(bfChinese, nfontsize, Font.BOLD);
		return FontChinese;
	}

	// 转化中文
	protected Cell ChangeCell(String str, int nfontsize, boolean isBold)
			throws IOException, BadElementException, DocumentException {
		Phrase ph = ChangeChinese(str, nfontsize, isBold);
		Cell cell = new Cell(ph);
		// cell.setBorderWidth(3);

		return cell;
	}

	// 转化中文
	protected Chunk ChangeChunk(String str, int nfontsize, boolean isBold)
			throws IOException, BadElementException, DocumentException {
		Font FontChinese = getChineseFont(nfontsize, isBold);
		Chunk chunk = new Chunk(str, FontChinese);
		return chunk;
	}

	// 转化中文
	protected Phrase ChangeChinese(String str, int nfontsize, boolean isBold)
			throws IOException, BadElementException, DocumentException {
		Font FontChinese = getChineseFont(nfontsize, isBold);
		Phrase ph = new Phrase(str, FontChinese);
		return ph;
	}

	private ActionForward nameList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 名单输出
		ActionForward newFwd = null;
		CommanForm priseForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String typ = request.getParameter("typ");
		if (!Base.isNull(typ) && typ.equalsIgnoreCase("cri")) {
			return nameListCri(mapping, form, request, response);
		}
		DAO dao = DAO.getInstance();
		String sql = "";// sql语句
		String context = "";
		String title = "";
		String xn = "";
		String nd = "";
		String xx = "";
		int chgLine = 0;
		String[] colList;
		sql = "select xxmc,jxjsqxn,jxjsqnd from xtszb where rownum=1";
		colList = dao.getOneRs(sql, new String[] {}, new String[] { "jxjsqxn",
				"jxjsqnd", "xxmc" });
		xn = colList[0];
		nd = colList[1];
		xx = colList[2];
		if (userType.equalsIgnoreCase("xy")) {
			xx = colList[2] + dao.getXymcById(userDep);
			sql = "select jxjdm,jxjmc,xymc,zymc,bjmc,xh,xm from view_xsjxjb where jxjlb='校' and"
					+ " xn=? and nd=? and xydm=? and xysh='通过' order by jxjdm,xymc,zymc,bjmc,xh";
		} else {
			xx = colList[2];
			sql = "select jxjdm,jxjmc,xymc,zymc,bjmc,xh,(case when xysh='未审核' or xysh='未通过' then xm||'('||xysh||')' when xysh='通过' and xxsh='未通过' then xm||'(未通过)' when xysh='通过' and xxsh='未审核' then xm||'(未审核)' else xm end)xm from view_xsjxjb where jxjlb='校' and"
					+ " xn=? and nd=? and xydm like ? order by jxjdm,xymc,zymc,bjmc,xh";
			userDep = "%";
		}
		title = xn + "学年 奖学金名单" + '\n';
		Vector<Object> rs = new Vector<Object>();
		if (typ == null) {
			priseForm.setErrMsg("sdf");
			newFwd = mapping.findForward("false");
		} else if (typ.equalsIgnoreCase("prise")) {
			Document document = new Document();
			document.setPageSize(PageSize.A4);
			document.setMargins(30, 30, 30, 30);
			ByteArrayOutputStream ba = new ByteArrayOutputStream();
			rs.addAll(dao
					.rsToVator(sql, new String[] { xn, nd, userDep },
							new String[] { "jxjmc", "xymc", "zymc", "bjmc",
									"xh", "xm" }));
			String tmpXY = "";
			String tmpJXJ = "";
			String[] tmpList;
			try {
				PdfWriter writer = PdfWriter.getInstance(document, ba);
				writer.setViewerPreferences(PdfWriter.HideMenubar);
				// float width = psize.width();
				// float height = psize.height();
				document.open();
				// PdfContentByte cb = writer.getDirectContentUnder();
				// cb.beginText();
				// BaseFont bf = getChineseFont(14,true).getBaseFont();
				// cb.setFontAndSize(bf, 14);
				// cb.showTextAligned(PdfContentByte.ALIGN_CENTER,xx,width/2,height-30,0);
				// cb.showTextAligned(PdfContentByte.ALIGN_CENTER,title,width/2,height-50,0);
				// cb.showTextAligned(PdfContentByte.ALIGN_LEFT,context,0,0,0);
				// cb.showText(title);
				// cb.showText(context);
				// cb.endText();
				// BaseFont bf =
				// BaseFont.createFont("c:\\winnt\\fonts\\SIMLI.TTF,1",
				// BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
				Paragraph myPhrase;
				myPhrase = new Paragraph(ChangeChunk(xx, 20, true));
				myPhrase.setAlignment(Element.ALIGN_CENTER);
				// FontFactory.
				FontFactory.getFont("隶书", 16);
				document.add(myPhrase);
				myPhrase = new Paragraph(ChangeChunk(title, 18, true));
				myPhrase.setAlignment(Element.ALIGN_CENTER);
				FontFactory.getFont("宋体", 16);
				document.add(myPhrase);
				Phrase myPhrase1;
				for (int i = 0; i < rs.size(); i++) {
					tmpList = (String[]) rs.get(i);
					if (!tmpJXJ.equalsIgnoreCase(tmpList[0])) {
						context = "\n" + "\n" + tmpList[0];
						tmpJXJ = tmpList[0];
						tmpXY = "";
						myPhrase = new Paragraph(ChangeChunk(context, 16, true));
						FontFactory.getFont("宋体", 16);
						document.add(myPhrase);
						chgLine = 0;
					}
					if (!userType.equalsIgnoreCase("xy")
							&& !tmpXY.equalsIgnoreCase(tmpList[1])) {
						context = '\n' + tmpList[1] + '\n';
						tmpXY = tmpList[1];
						myPhrase = new Paragraph(ChangeChunk(context, 14, true));
						FontFactory.getFont("宋体", 14);
						document.add(myPhrase);
						chgLine = 0;
					}
					context = "";
					if (chgLine == 0) {
						context += "　　　";
					}
					context += chgTxt(tmpList[5]);
					if (context.length() > 9) {
						chgLine++;
					}
					chgLine++;
					if (chgLine == 6) {
						context += "\n";
						chgLine = 0;
					}
					myPhrase1 = ChangeChinese(context, 12, false);
					document.add(myPhrase1);
				}
				document.close();
			} catch (DocumentException de) {
				de.printStackTrace();
				System.err.println("A Document error:" + de.getMessage());
			}
			response.setContentType("application/pdf");
			response.setContentLength(ba.size());
			ServletOutputStream out = response.getOutputStream();
			ba.writeTo(out);
			out.flush();
		}
		return newFwd;
	}

	private ActionForward nameListCri(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 名单输出
		ActionForward newFwd = null;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		DAO dao = DAO.getInstance();

		String sql = "";// sql语句
		String context = "";
		String title = "";
		String xx = "";
		int chgLine = 0;
		String[] colList;
		sql = "select xxmc from xtszb where rownum=1";
		colList = dao.getOneRs(sql, new String[] {}, new String[] { "xxmc" });
		if (userType.equalsIgnoreCase("xy")) {
			xx = colList[0] + dao.getXymcById(userDep);
			sql = "select rychdm,rychmc,xymc,zymc,bjmc,xh,xm from view_xsrychb where "
					+ " xn=? and nd=? and xydm=? and rychdm in(?,?) order by rychdm,xymc,zymc,bjmc,xh";
		} else {
			xx = colList[0];
			sql = "select rychdm,rychmc,xymc,zymc,bjmc,xh,xm from view_xsrychb where "
					+ " xn=? and nd=? and xydm like ? and rychdm in(?,?) order by rychdm,xymc,zymc,bjmc,xh";
			userDep = "%";
		}
		title = Base.currNd + "年度 优秀毕业生名单" + '\n';
		Vector<Object> rs = new Vector<Object>();
		Document document = new Document();
		document.setPageSize(PageSize.A4);
		document.setMargins(30, 30, 30, 30);
		ByteArrayOutputStream ba = new ByteArrayOutputStream();
		rs.addAll(dao.rsToVator(sql, new String[] { Base.currXn, Base.currNd,
				userDep, Base.sydm, Base.xydm }, new String[] { "rychmc",
				"xymc", "zymc", "bjmc", "xh", "xm" }));
		String tmpXY = "";
		String tmpJXJ = "";
		String[] tmpList;
		try {
			PdfWriter writer = PdfWriter.getInstance(document, ba);
			writer.setViewerPreferences(PdfWriter.HideMenubar);
			document.open();
			Paragraph myPhrase;
			myPhrase = new Paragraph(ChangeChunk(xx, 20, true));
			myPhrase.setAlignment(Element.ALIGN_CENTER);
			FontFactory.getFont("隶书", 16);
			document.add(myPhrase);
			myPhrase = new Paragraph(ChangeChunk(title, 18, true));
			myPhrase.setAlignment(Element.ALIGN_CENTER);
			FontFactory.getFont("宋体", 16);
			document.add(myPhrase);
			Phrase myPhrase1;
			for (int i = 0; i < rs.size(); i++) {
				tmpList = (String[]) rs.get(i);
				if (!tmpJXJ.equalsIgnoreCase(tmpList[0])) {
					context = "\n" + "\n" + tmpList[0];
					tmpJXJ = tmpList[0];
					tmpXY = "";
					myPhrase = new Paragraph(ChangeChunk(context, 16, true));
					FontFactory.getFont("宋体", 16);
					document.add(myPhrase);
					chgLine = 0;
				}
				if (!userType.equalsIgnoreCase("xy")
						&& !tmpXY.equalsIgnoreCase(tmpList[1])) {
					context = '\n' + tmpList[1] + '\n';
					tmpXY = tmpList[1];
					myPhrase = new Paragraph(ChangeChunk(context, 14, true));
					FontFactory.getFont("宋体", 14);
					document.add(myPhrase);
					chgLine = 0;
				}
				context = "";
				if (chgLine == 0) {
					context += "　　　";
				}
				context += chgTxt(tmpList[5]);
				if (context.length() > 9) {
					chgLine++;
				}
				chgLine++;
				if (chgLine == 6) {
					context += "\n";
					chgLine = 0;
				}
				myPhrase1 = ChangeChinese(context, 12, false);
				document.add(myPhrase1);
			}
			document.close();
		} catch (DocumentException de) {
			de.printStackTrace();
			System.err.println("A Document error:" + de.getMessage());
		}
		response.setContentType("application/pdf");
		response.setContentLength(ba.size());
		ServletOutputStream out = response.getOutputStream();
		ba.writeTo(out);
		out.flush();
		return newFwd;
	}

	private String chgTxt(String str) {
		String res = str;
		if (res.length() == 2) {
			res = str.substring(0, 1) + "　" + str.substring(1, 2);
		}

		for (int i = 0; i < 8 - res.length(); i++) {
			res = res + "　";
		}
		boolean lll = res.length() > 6;
		if (lll) {
			for (int i = 0; i < 13 - res.length(); i++) {
				res = res + "　";
			}
		}
		return res;
	}

	private ActionForward prisePrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 证书打印
		CommanForm priseForm = (CommanForm) form;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql语句
		String querry = " where 1=1 ";// sql条件
		String rsNum = "0";// 返回的记录数
		String writeAble = "yes";// 写权限
		String userDep = session.getAttribute("userDep").toString();
		String userType = request.getSession().getAttribute("userType").toString();
		String bmdm = priseForm.getXydm();
		String zydm = priseForm.getZydm();
		String bjdm = priseForm.getBjdm();
		String xxdm = dao.getXxdm();
		String xq = priseForm.getXq();
		if (userType.equalsIgnoreCase("xy")) {
			bmdm = userDep;
		}
		//学生用户无权进入该页面
		if (GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(userType)
				|| GlobalsVariable.XTDM_STU.equalsIgnoreCase(userType)) {
			request.setAttribute("errMsg", "您无权访问该页面!");
			return new ActionForward("/errMsg.do");
		}

		String jxjdm = priseForm.getXmdm();

		sql = "select jxjsqxn,jxjsqnd,replace(jxjsqxn||'-'||jxjsqnd,'200','') sj from xtszb where rownum=1";
		colList = dao.getOneRs(sql, new String[] {}, new String[] { "jxjsqxn",
				"jxjsqnd", "sj" });
		String xn = request.getParameter("xn");
//		String nd = colList[1];
		String sj = colList[2];
//		if (StringUtils.isNull(xn)) {
//			xn = Base.getJxjsqxn();
//		}
//		if (StringUtils.isNull(xq)) {
//			xq = Base.getJxjsqxq();
//		}
		if (!StringUtils.isNull(xn)) {
			querry += "and xn = '" + xn + "' ";	
		}
		
		//querry += "and nd = '" + nd + "' ";
		if ((jxjdm == null) || jxjdm.equalsIgnoreCase("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and jxjdm = '" + jxjdm + "' ";
		}
		if ((bmdm == null) || bmdm.equalsIgnoreCase("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xydm = '" + bmdm + "' ";
		}
		if ((zydm == null) || zydm.equalsIgnoreCase("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and zydm = '" + zydm + "' ";
		}
		if ((bjdm == null) || bjdm.equalsIgnoreCase("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and bjdm = '" + bjdm + "' ";
		}
		if (!StringUtils.isNull(xq)) {
			querry += "and xq='";
			querry += xq;
			querry += "' ";
		}
		if (userType.equalsIgnoreCase("xy")) {
			priseForm.setXydm(userDep);
		}
		colList = new String[] { "行号", "xh", "xm", "xn","xqmc","zymc", "bjmc", "jxjmc" };
		sql = "select rownum 行号,c.*,(select b.xqmc from xqdzb b where b.xqdm=c.xq) xqmc from (select  * from view_xsjxjb " + querry
				+ " and xxsh='通过' order by xh) c";
		//colListCN = dao.getColumnNameCN(colList, "view_xsjxjb");
		//String[] enList = new String[]{ "rownum", "xh", "xm", "xn","xqmc","zymc", "bjmc", "jxjmc" };
		//String[] cnList = new String[]{ "行号", "学号", "姓名", "学年","学期","专业", "班级", "奖学金" };
		colListCN = new String[]{ "行号", "学号", "姓名", "学年","学期","专业", "班级", "奖学金" };
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
		sql = "select jxjdm,jxjmc from jxjdmb";
		List jxjList = dao.getList(sql, new String[] {}, new String[] {
				"jxjdm", "jxjmc" });
		
		//中州大学荣誉证书打印
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZZDX)) {
			request.setAttribute("showzzdx", "yes");
		}
		
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("xyList", dao.getXyList());// 发送学院列表
		request.setAttribute("zyList", dao.getZyList(bmdm));// 发送专业列表
		request.setAttribute("bjList", dao.getBjList(bmdm, zydm));
		request.setAttribute("jxjList", jxjList);// 发送专业列表
		request.setAttribute("xqList", Base.getXqList());//学期列表
		request.setAttribute("xnList", dao.getXnndList());
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("sj", sj);
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		return mapping.findForward("success");
	}

	private ActionForward stuCardPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CommanForm printForm = (CommanForm) form;
		DAO dao = DAO.getInstance();
		String xxdm = StandardOperation.getXxdm();
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql语句
		String querry = " where 1=1 ";// sql条件
		String rsNum = "0";// 返回的记录数
		String xy = printForm.getXydm();
		String zy = printForm.getZydm();
		String bj = printForm.getBjdm();
		String xh = request.getParameter("xh");
		String xm = DealString.toGBK(request.getParameter("xm"));
		if (xxdm.equals("12957")) {
			request.setAttribute("xxdm", "szxx");
		} else if (xxdm.equals("10513")) {
			request.setAttribute("xxdm", "hbsf");
		} else if (xxdm.equals(Globals.XXDM_ZGDZDX)) { //中国地质大学
			request.setAttribute("xxdm", "zgdzdx");
		} else {
			request.setAttribute("xxdm", "normal");
		}

		if (xy == null) {
			xy = "";
		}
		if (zy == null) {
			zy = "";
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

		if (xh != null && Check_Input_Value.check2(xh)) {
			querry += " and xh like '%" + xh + "%'";
		}
		if (xm != null && Check_Input_Value.check2(xm)) {
			querry += " and xm like '%" + xm + "%'";
		}
		colList = new String[] { "行号", "xh", "xm", "xb", "nj", "bjmc" };
		sql = "select rownum 行号,a.* from view_xsjbxx a" + querry;
		colListCN = dao.getColumnNameCN(colList, "view_xsjbxx");
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
			colList = new String[] { "行号", "xh", "xm", "xb", "xymc", "bjmc",
					"xz", "rxny", "jg", "sfzh" };
			sql = "select * from (select rownum 行号,a.xh,a.xm,a.xb,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.xz,b.rxny,b.jgm jg,a.sfzh from view_xsjbxx a left join bks_xsjbxx b on a.xh=b.xh) "
					+ querry;
			colListCN = new String[] { "行号", "学号", "姓名", "性别", Base.YXPZXY_KEY+"名称", "班级名称",
					"学制", "入学年月", "籍贯", "身份证号" };
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SZXXZYJSXY)) {
			colList = new String[] { "行号", "xh", "xm", "xb", "zymc", "bjmc",
					"rxny", "jg", "csrq" };
			sql = "select 行号,xh,xm,xb,zymc,bjmc,rxny,jg,csrq from (select rownum 行号, a.xh,a.xm,a.xb,a.xymc,a.xydm,a.zymc,a.zydm,a.bjmc,a.bjdm,b.jgm jg,b.rxny,c.csrq,d.zp from view_xsjbxx a left join bks_xsjbxx b on a.xh=b.xh left join bks_xsqtxx c on c.xh=a.xh left join xszpb d on a.xh=d.xh)"
					+ querry;
			colListCN = new String[] { "行号", "学号", "姓名", "性别", "专业名称", "班级名称",
					"入学年月", "籍贯", "出生日期" };
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
			colList = new String[] { "行号", "xh", "xm", "xb", "zymc", "bjmc",
					"rxny", "jg", "csrq" };
			sql = "select 行号,xh,xm,xb,zymc,bjmc,rxny,jg,csrq from (select rownum 行号, a.xh,a.xm,a.xb,a.xymc,a.xydm,a.zymc,a.zydm,a.bjmc,a.bjdm,b.jgm jg,b.rxny,c.csrq,d.zp from view_xsjbxx a left join bks_xsjbxx b on a.xh=b.xh left join bks_xsqtxx c on c.xh=a.xh left join xszpb d on a.xh=d.xh)"
					+ querry;
			colListCN = new String[] { "行号", "学号", "姓名", "性别", "专业名称", "班级名称",
					"入学年月", "籍贯", "出生日期" };
		}
		if (xxdm.equalsIgnoreCase("10513")) {
			colList = new String[] { "行号", "xh", "xm", "xb", "csrq", "jg",
					"nj", "xymc", "zymc" };
			sql = "select 行号,xh,xm,xb,csrq,jg,nj,xymc,zymc from (select rownum 行号, a.xh,a.xm,a.xb,a.nj,a.xymc,a.xydm,a.zymc,a.zydm,a.bjdm,a.bjdm,b.jgm jg,b.rxny,c.csrq,d.zp from view_xsjbxx a left join bks_xsjbxx b on a.xh=b.xh left join bks_xsqtxx c on c.xh=a.xh left join xszpb d on a.xh=d.xh)"
					+ querry;
			colListCN = new String[] { "行号", "学号", "姓名", "性别", "出生日期", "籍贯",
					"年级", Base.YXPZXY_KEY+"名称", "专业名称" };

		}

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

		String bmdm = printForm.getXydm();
		String zydm = printForm.getZydm();
		request.setAttribute("writeAble", "yes");// 发送读写权限
		request.setAttribute("xyList", dao.getXyList());// 发送学院列表
		request.setAttribute("zyList", dao.getZyList(bmdm));// 发送专业列表
		request.setAttribute("bjList", dao.getBjList(bmdm, zydm));
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		return mapping.findForward("success");
	}

	private ActionForward autoCardPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		CommanForm printForm = (CommanForm) form;
		String bmdm = printForm.getXydm();
		String zydm = printForm.getZydm();
		request.setAttribute("writeAble", "yes");// 发送读写权限
		request.setAttribute("xyList", dao.getXyList());// 发送学院列表
		request.setAttribute("zyList", dao.getZyList(bmdm));// 发送专业列表
		request.setAttribute("bjList", dao.getBjList(bmdm, zydm));
		return mapping.findForward("success");
	}

	/** 通知书打印维护单个打印 */
	private ActionForward noticePrintOne(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DAO dao = DAO.getInstance();
		String xh = Base.chgNull(request.getParameter("xh"), "", 0);
		String sql = "";
		String[] colList = { "xm", "xh", "zymc", "xb", "jg", "xymc", "nj","mz","bjmc","xymc","xz","sfzh","jtdz"};
		String fzrq = request.getParameter("fzrq");
		String bfrq = request.getParameter("bfrq");
		String yxrq = request.getParameter("yxrq");
		String dzdxyz = request.getParameter("dzdxyz");
		String page = "";
		String isLd = request.getParameter("isLd");
		String xxdm = request.getSession().getAttribute("xxdm").toString();
		HashMap<String, String> rsMap = new HashMap<String, String>();
		HashMap<String, String> csMap = new HashMap<String, String>();
		HashMap<String, String> rxMap = new HashMap<String, String>();
		HashMap<String, String> fzMap = new HashMap<String, String>();
		HashMap<String, String> bfMap = new HashMap<String, String>();
		HashMap<String, String> yxMap = new HashMap<String, String>();

		// 学生基本信息
		sql = "select a.xh,a.xm,a.xb,a.bjmc,a.zymc,a.xymc,a.nj,(select b.jgm from bks_xsjbxx b where a.xh=b.xh) jg,a.mz,a.bjmc,a.xymc,a.xz,a.sfzh,a.jtdz from view_xsxxb a where a.xh=?";
		rsMap = dao.getMap(sql, new String[] { xh }, colList);
		request.setAttribute("rsMap", rsMap);

		// 学生出生日期
		sql = "select a.csrq csrq from view_stu_details a,view_xsjbxx b where a.xh=b.xh and a.xh=?";
		String csrq=dao.getOneRs(sql, new String[] {xh}, "csrq");	
		//csrq="2008-04-02";
		if(csrq == null || "".equalsIgnoreCase(csrq)){
			csMap.put("csN", "-");
			csMap.put("csY", "-");
			csMap.put("csR", "-");
		} else if (csrq.indexOf("-") < 0 && !csrq.equalsIgnoreCase("")) {
			csMap.put("csN", csrq.substring(0, 4));
			csMap.put("csY", csrq.substring(4, 6));
			csMap.put("csR", csrq.substring(6, 8));
		} else {
			csMap.put("csN", csrq.substring(0, 4));
			csMap.put("csY", csrq.substring(csrq.indexOf("-") + 1, csrq
					.lastIndexOf("-")));
			csMap.put("csR", csrq.substring(csrq.lastIndexOf("-") + 1, csrq
					.length()));
		}
		request.setAttribute("csMap", csMap);

		// 学生入学日期
		sql = "select a.rxny rxny from bks_xsjbxx a,view_xsjbxx b where a.xh=b.xh and a.xh=?";
		String rxny=dao.getOneRs(sql, new String[] {xh}, "rxny");	
		//rxny="2003-05-30";
		if(rxny == null || "".equalsIgnoreCase(rxny)){			
			rxMap.put("rxN", "-");
			rxMap.put("rxY", "-");
			rxMap.put("rxR", "-");
		} else if (rxny.indexOf("-") < 0 && rxny.length() == 8
				&& !rxny.equalsIgnoreCase("")) {
			// rxny="20020931";
			rxMap.put("rxN", rxny.substring(0, 4));
			rxMap.put("rxY", rxny.substring(4, 6));
			rxMap.put("rxR", rxny.substring(6, 8));
		} else if (rxny.length() == 10) {
			rxMap.put("rxN", rxny.substring(0, 4));
			rxMap.put("rxY", rxny.substring(rxny.indexOf("-") + 1, rxny
					.lastIndexOf("-")));
			rxMap.put("rxR", rxny.substring(rxny.lastIndexOf("-") + 1, rxny
					.length()));
		}
		request.setAttribute("rxMap", rxMap);

		// 发证日期
		sql = "select substr(nvl('" + fzrq
				+ "','0000-00-00'),1,4) fzN,substr(nvl('" + fzrq
				+ "','0000-00-00'),6,2) fzY,substr(nvl('" + fzrq
				+ "','0000-00-00'),9,2) fzR from dual";
		fzMap = dao.getMap(sql, new String[] {}, new String[] { "fzN", "fzY",
				"fzR" });
		request.setAttribute("fzMap", fzMap);

        //深圳信息
        //补发日期
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SZXXZYJSXY) || xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
			
			sql = "select substr(nvl('" + bfrq
					+ "','0000-00-00'),1,4) bfN,substr(nvl('" + bfrq
					+ "','0000-00-00'),6,2) bfY,substr(nvl('" + bfrq
					+ "','0000-00-00'),9,2) bfR from dual";
			bfMap = dao.getMap(sql, new String[] {}, new String[] { "bfN",
					"bfY", "bfR" });
			request.setAttribute("bfMap", bfMap);
		}

		// 有效日期
		sql = "select substr(nvl('" + yxrq
				+ "','0000-00-00'),1,4) yxN,substr(nvl('" + yxrq
				+ "','0000-00-00'),6,2) yxY,substr(nvl('" + yxrq
				+ "','0000-00-00'),9,2) yxR from dual";
		yxMap = dao.getMap(sql, new String[] {}, new String[] { "yxN", "yxY",
				"yxR" });
		request.setAttribute("yxMap", yxMap);

		request.setAttribute("fzrq", fzrq);
		request.setAttribute("bfrq", bfrq);
		request.setAttribute("yxrq", yxrq);

		// 位置
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SZXXZYJSXY)) {// 深圳信息
			page = "szxxxszs";
			request.setAttribute("xxdm", "szxx");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HBSFXY)) {// 湖北师范信息
			page = "hbsfxsz";
			request.setAttribute("xxdm", "hbsf");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {// 中国地质大学
			page = "zgdzxsz";
			request.setAttribute("xxdm", "zgdzdx");
		}

		String topstr = dao.getOneRs("select top from printpage where page=?",
				new String[] { page }, "top");
		String leftstr = dao.getOneRs(
				"select left from printpage where page=?",
				new String[] { page }, "left");
		request.setAttribute("topstr", topstr);
		request.setAttribute("leftstr", leftstr);

		// 单打
		if (isLd != null && !"".equalsIgnoreCase(isLd)
				&& xxdm.equals(Globals.XXDM_SZXXZYJSXY)) {
			// 深圳信息
			return mapping.findForward("szxxxszdd");
		} else if (isLd != null && !"".equalsIgnoreCase(isLd)
				&& xxdm.equals(Globals.XXDM_HBSFXY)) {
			// 湖北师范
			return mapping.findForward("hbsfxszdd");
		}else if (isLd != null && !"".equalsIgnoreCase(isLd)
				&& xxdm.equals(Globals.XXDM_ZGDZDX)) {
			// 中国地质大学
			if(Globals.XXDM_ZGDZDX.equals(xxdm) && "wztz".equals(dzdxyz)){
				return mapping.findForward("dzdxwztz");
			}else{
				return mapping.findForward("zgdzdxszdd");
			}
		}

		// 连打
		if (xxdm.equals(Globals.XXDM_SZXXZYJSXY)) {
			return mapping.findForward("szxxxszld");
		} else if (xxdm.equals(Globals.XXDM_HBSFXY)) {
			return mapping.findForward("hbsfxszld");
		}else if (xxdm.equals(Globals.XXDM_ZGDZDX)) {//中国地质大学
			return mapping.findForward("zgdzdxld");
		}

		return mapping.findForward("print_note");

	}

	public void savePagePara(String top, String left, String page) {
		DAO dao = DAO.getInstance();
		String testSql = "select count(*) cont from printpage where page=?";
		try {
			String testRes = dao.getOneRs(testSql, new String[] { page },
					"cont");
			String sql = "";
			
			if (testRes == null || testRes.trim().equals("")
					|| testRes.trim().equals("0")) {
				sql = "insert into printpage(top,left,page) values(?,?,?)";
			} else {
				sql = "update printpage set top=?,left=? where page=?";
			}
			boolean result = 			dao.runUpdate(sql, new String[] { top, left, page });
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
