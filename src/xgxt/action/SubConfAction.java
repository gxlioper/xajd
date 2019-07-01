/*
 * �������� 2006-9-6
 *
 *  Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package xgxt.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.comm.CommService;
import xgxt.comm.CommSetting;
import xgxt.comm.MessageInfo;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.jxgl.cdty.gfjy.CdtyGfjyForm;
import xgxt.jxgl.cdty.gfjy.CdtyGfjyService;
import xgxt.pjpy.cqkjxy.PjpyCqkjxyActionForm;
import xgxt.pjpy.model.zbdx_rychsqsh_model;
import xgxt.pjpy.util.zbdx_pjpy_rychsh_util;
import xgxt.pjpy.zjkjxy.PjpyZjkjxyService;
import xgxt.pjpy.zjsyzy.PjpyZjsyzyForm;
import xgxt.pjpy.zjsyzy.PjpyZjsyzyService;
import xgxt.qgzx.bjlhdx.BjlhQgzxService;
import xgxt.qgzx.comm.gwsqwh.QgzxXsgwsqForm;
import xgxt.qgzx.comm.gwsqwh.QgzxXsgwsqService;
import xgxt.qgzx.dao.QgzxDao;
import xgxt.qgzx.hngydx.dao.HngydxGwglDAO;
import xgxt.qgzx.service.QgzxService;
import xgxt.qgzx.service.XsgwglService;
import xgxt.qgzx.xbemy.XbemyQgzxDAO;
import xgxt.qgzx.zgdzdx.QgzxZgdzdxService;
import xgxt.qgzx.zgkydx.QgzxZgkydxService;
import xgxt.qtsj.shgc.form.ShgcTbxxForm;
import xgxt.qtsj.shgc.service.TbsqcxService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.CheckPower;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.MakeQuery;
import xgxt.utils.Pages;
import xgxt.utils.UserTypePd;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xgxt.xljk.lrh_Util.util.stu_info_util;

import com.zfsoft.basic.BasicAction;
import common.Globals;
import common.GlobalsVariable;

/**
 * @author bat_zzj 
 *  Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class SubConfAction extends BasicAction {
	//DAO dao = DAO.getInstance();  
	String writeAble = "";
	private String xbemyxdw = "xbemyxdw";//��������ԺУ��ί
	private String xbemyxz = "xbemyxz";//��������ԺУ��
	private static final String LXCK = "��У�쿴";
	private boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase("") || str.equalsIgnoreCase("all"));
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
//		String currXn = Base.currXn;
//		String currNd = Base.currNd;
//		String dqXn = dao.getConf(0);
//		String dqXq = dao.getConf(1);
//		String dqNd = dao.getConf(4);
		CommanForm chkUser = (CommanForm) form;
		DAO dao = DAO.getInstance();

		try {
			//		�ж��û���дȨ
			writeAble = Base.getWriteAble(request);

			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				chkUser.setErrMsg("��½��ʱ�������µ�½��");
				return new ActionForward("/login.jsp", false);
			}

			ActionForward myActFwd = null;
			String act = mapping.getParameter();
			if (act.equalsIgnoreCase("priseConfRS")) {//�������ò�ѯҳ��
				myActFwd = priseConf(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("initBaseData")) {
				myActFwd = initBaseData(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("xySetStuNum")) {
				myActFwd = xySetStuNum(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("xySetBysStuNum")) {
				myActFwd = xySetBysStuNum(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("showYxBysFpb")) {
				myActFwd = showYxBysFpb(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("viewTotStuNum")) {
				myActFwd = viewTotStuNum(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("viewFpb")) {
				myActFwd = viewFpb(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("chgPriseXn")) {
				myActFwd = chgPriseXn(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("chgPriseTime")) {
				myActFwd = chgPriseTime(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("chgPriseBat")) {////��������-��������-�������ñ���
				myActFwd = chgPriseBat(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("chgPriseOne")) {//�������õ�������
				myActFwd = chgPriseOne(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("setPriseCond")) {//��������-��������
				myActFwd = setPriseCond(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("priseCheck")) {//��ѧ���������
				myActFwd = priseCheck(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("priseChkOne")) {//��ѧ�𵥸����
				myActFwd = priseChkOne(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("priseAutoCheck")) {//��ѧ���Զ����
				myActFwd = priseAutoChk(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("creditCheck")) {//�����ƺ����
				myActFwd = creditCheck(mapping, form, request, response);
				writeAble = CheckPower.checkUsrPower(request.getSession().getAttribute("userName").toString(), "credit_check.do") ? "yes" : "no";
			} else if (act.equalsIgnoreCase("creditChkOne")) {//�����ƺŵ������
				myActFwd = creditChkOne(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("creditAutoCheck")) {//�����ƺ��Զ����
				myActFwd = creditAutoChk(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("assisCheck")) {
				myActFwd = assisCheck(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("assisChkOne")) {
				myActFwd = assisChkOne(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("commCheck")) {
				myActFwd = commCheck(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("commChkOne")) {
				myActFwd = commCheckOne(mapping, form, request, response);
				writeAble = CheckPower.checkUsrPower(request.getSession().getAttribute("userName").toString(), "comm_check.do")?"yes":"no";
			} else if (act.equalsIgnoreCase("commMonCheck")) {
				myActFwd = commMonCheck(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("commMonChkOne")) {
				myActFwd = commMonCheckOne(mapping, form, request, response);
				writeAble=CheckPower.checkUsrPower(request.getSession().getAttribute("userName").toString(), "comm_money_check.do")?"yes":"no";
			} else if (act.equalsIgnoreCase("postCheck")) {
				myActFwd = workCheck(mapping, form, request, response);
				writeAble = CheckPower.checkUsrPower(request.getSession().getAttribute("userName").toString(), "post_check.do")?"yes":"no";// дȨ��
			} else if (act.equalsIgnoreCase("postChkOne")) {
				myActFwd = postCheckOne(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("postStuChk")) {
				myActFwd = postStuCheck(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("postStuChkOne")) {
				myActFwd = postStuCheckOne(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("chgPriseXnInit")) {
				request.setAttribute("ndList", dao.getXnndList());
				request.setAttribute("xqList", dao.getXqList());
				return mapping.findForward("success");
			} else if (act.equalsIgnoreCase("priseConfOne")) {//ѧУ��������
				return mapping.findForward("success");
			} else if (act.equalsIgnoreCase("viewJxjHz")) {
				myActFwd = viewJxjHz(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("setJxjHz")) {
				myActFwd = setJxjHz(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("ticketConf")) {
				myActFwd = ticketConf(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("ticketBookSearch")) {
				myActFwd = ticketBookSearch(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("ticketBookEdit")) {
				myActFwd = ticketBookEdit(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("stuTicketBookSearch")) {
				myActFwd = stuTicketBookSearch(mapping, form, request, response);
			} else if (act.equalsIgnoreCase("playCheck")) {
				myActFwd = playCheck(mapping, form, request, response);
			} else if ("autoaccount".equalsIgnoreCase(act)){
				myActFwd = autoaccount(mapping, form, request, response);
			} else if ("insureAppAudit".equalsIgnoreCase(act)){
				myActFwd = insureAppAudit(mapping, form, request, response);
			} else if ("insureChkOne".equalsIgnoreCase(act)){
				myActFwd = insureChkOne(mapping, form, request, response);
				writeAble = CheckPower.checkUsrPower(request.getSession().getAttribute("userName").toString(), "InsureAppAudit.do")?"yes":"no";// дȨ��
			} else if ("stuPunishAudit".equalsIgnoreCase(act)){ //ѧУ���
				myActFwd = stuPunishAudit(mapping, form, request, response);
			} else if ("stuPunishAuditOne".equalsIgnoreCase(act)){ //ѧУ���
				myActFwd = stuPunishAuditOne(mapping, form, request, response);
			}else if("insureInfo".equalsIgnoreCase(act)){
				myActFwd=insureInfo(mapping, form, request, response);
			}else if("ticketBookTotalSearch".equalsIgnoreCase(act)){
				myActFwd=ticketBookTotalSearch(mapping, form, request, response);
			}else if("insureHisQuery".equalsIgnoreCase(act)){
				myActFwd=InsureHisQuery(mapping, form, request, response);
			}else if("stuPunishAuditByFsj".equalsIgnoreCase(act)){//��������Ժѧ��Υ��У��ί���
				myActFwd=stuPunishAuditByFsj(mapping, form, request, response);
			}else if("stuPunishAuditOneByFsj".equalsIgnoreCase(act)){//��������Ժѧ��Υ��У��ί�������
				myActFwd=stuPunishAuditOneByFsj(mapping, form, request, response);
			}else if("stuPunishAuditByXz".equalsIgnoreCase(act)){//��������Ժѧ��Υ��У�����
				myActFwd=stuPunishAuditByXz(mapping, form, request, response);
			}else if("stuPunishAuditOneByXz".equalsIgnoreCase(act)){//��������Ժѧ��Υ��У���������
				myActFwd=stuPunishAuditOneByXz(mapping, form, request, response);
			} else if ("jxjrsDataExp".equalsIgnoreCase(act)) {
				myActFwd=jxjrsDataExp(mapping,form,request,response);
			} else if ("qgzxLsgwxx".equalsIgnoreCase(act)) {
				myActFwd=qgzxLsgwxx(mapping,form,request,response);
			} else if ("lsgwView".equalsIgnoreCase(act)) {
				myActFwd=lsgwView(mapping,form,request,response);
			}
			request.setAttribute("writeAble", writeAble);
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			chkUser.setErrMsg("���������Թ��ϣ�" + e.toString());
			return new ActionForward("/errMsg.do", false);
		}
	}

	/**
	 * �༶Ͷ����Ϣ��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 * */
	private ActionForward insureInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {        	
		CommanForm checkForm = (CommanForm) form;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();   
		ArrayList<String[]>  rs=new ArrayList<String[]>(); 
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		String writeAble = Base.getWriteAble(request);
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String xydm = checkForm.getXydm();
		String bjdm = checkForm.getBjdm();
		String zydm = checkForm.getZydm();
		String nj = checkForm.getNj();
		String xh = DealString.toGBK(checkForm.getXh());
		String xm = DealString.toGBK(checkForm.getXm());
		String xymc = dao.getOneRs("select xymc from view_njxyzybj where xydm=?", new String[]{xydm}, "xymc");
		String zymc = dao.getOneRs("select zymc from view_njxyzybj where zydm=?", new String[]{zydm}, "zymc");
		String bjmc = dao.getOneRs("select bjmc from view_njxyzybj where bjdm=?", new String[]{bjdm}, "bjmc");
		String tableName="view_tbxx";//view_bxxxgropbybj

		checkForm.setXh(xh);
		checkForm.setXm(xm);

		StringBuffer query=new StringBuffer();
		query.append(xydm==null||xydm.trim().length()<1?"":" and xydm='"+xydm+"'");
		query.append(bjdm==null||bjdm.trim().length()<1?"":" and bjdm='"+bjdm+"'");
		query.append(zydm==null||zydm.trim().length()<1?"":" and zydm='"+zydm+"'");
		query.append(nj==null||nj.trim().length()<1?"":" and nj='"+nj+"'");
		query.append(nj==null||"".equalsIgnoreCase(nj)?"":" and nj='"+nj+"'");
		query.append(xh==null||"".equalsIgnoreCase(xh) ? "" : " and xh='" + xh + "'");
		query.append(xm==null||"".equalsIgnoreCase(xm) ? "" : " and trim(xm)='" + xm + "'");

		String sql="select * from "+tableName+" where 1=1 "+query.toString();
		String[] outputValue={"xh","xm","sftb","bf","bxxzmc"};

		if(userType != null && "xy".equalsIgnoreCase(userType)){
			xydm = userDep;
			checkForm.setXydm(xydm);
		}

		if(request.getParameter("go") != null && request.getParameter("go").equalsIgnoreCase("go")){
			String[] title_en = {"xh","xm","sftb","bf","bxxzmc"};
			String[] title_cn = dao.getColumnNameCN(title_en, tableName);
			for(int i=0;i<title_en.length;i++){
				HashMap<String, String> temmap = new HashMap<String, String>();
				temmap.put("cn", title_cn[i]);//��title_en[i]�޸�Ϊcn
				topTr.add(temmap);
			}
			rs=dao.rsToVator(sql,new String[]{}, outputValue);
			if(rs != null ){
				int rsNum = rs.size();
				request.setAttribute("rsNum", rsNum);
			}    
			request.setAttribute("rs", rs);
			request.setAttribute("topTr", topTr);
			request.setAttribute("xybj",xymc+" "+zymc+" "+bjmc);
		}

		xydm = xydm == null ? "" : xydm;
		zydm = zydm == null ? "" : zydm;
		nj = nj == null ? "" : nj;
		String bjKey = xydm+"!!"+zydm+"!!"+nj;

		request.setAttribute("xyList",Base.getXyList());
		request.setAttribute("zyList",Base.getZyMap().get(xydm));
		request.setAttribute("bjList",Base.getBjMap().get(bjKey));
		request.setAttribute("njList",Base.getNjList());
		request.setAttribute("writeAble", writeAble);
		return mapping.findForward("success");
	}

	private ActionForward playCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		CommanForm checkForm = (CommanForm) form;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql���
		String querry = " where 1=1 ";// sql����
		String tips = "";// λ����ʾ��Ϣ
		String tableName = "";// ��ѯ��ϢԴ����Ϊ��ͼ����
		String rsNum = "0";// ���صļ�¼��
		String realTable = "";// ����Դ��
		String pk = "";// ����Դ����������ʽΪ���ֶ���||�ֶ���||�ֶ�������
		String writeAble = "yes";// дȨ��
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xxdm = dao.getXxdm();

		String bmdm = checkForm.getBmdm();
		String sqr = DealString.toGBK(request.getParameter("sqr")).trim();
		String syrq = checkForm.getSyrq();
		String yesNo = DealString.toGBK(checkForm.getYesNo()).trim();
		String ykxx = DealString.toGBK(request.getParameter("ykxx")).trim();
		String xxqdm = request.getParameter("xxqdm");
		String dmtxx = DealString.toGBK(request.getParameter("dmtxx")).trim();

		sql = "select b.bmdm,b.bmmc from ZXBZ_XXBMDM b";
		List bmList = dao.getList(sql, new String[]{}, new String[]{"bmdm","bmmc"});
		request.setAttribute("bmList", bmList);

		tableName = "view_hdzxxx";
		realTable = "hdzxsqb";
		pk = "bmdm||sqr||djsj";
		tips = "�ճ����� - ��� - ��������";


		if (!isNull(bmdm)) {
			querry += "and bmdm = '" + bmdm + "' ";
		} 
		if (!sqr.equalsIgnoreCase("")) {
			querry += "and sqr = '" + sqr + "' ";
		} 
		if (!isNull(syrq)) {
			querry += "and syrq = '" + syrq + "' ";
		} 
		if (!yesNo.equalsIgnoreCase("")) {
			querry += "and hdsh = '" + yesNo + "' ";
		} 
		if(ykxx.equalsIgnoreCase("on")){
			querry += "and ykxx = '��Ҫ' ";
		}
		else{
			querry += "and ykxx = '����Ҫ' ";
		}
		if(!isNull(xxqdm)){
			querry += " and xxqdm = '" + xxqdm + "'";
		}
		if(dmtxx.equalsIgnoreCase("on")){
			querry += " and dmtxx = '��Ҫ'";
		} else {
			querry += " and dmtxx = '����Ҫ'";
		}

		if(xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)){
			request.setAttribute("isXNMZ", "yes");
			colList = new String[] { "bgcolor", "����", "�к�","xxqmc","bmmc", "sqr", "syrq", "hdkssj", "hdjssj", "ykxx","dmtxx","hdsh" };
			request.setAttribute("isXNMZ", "yes");
		} else {
			colList = new String[] { "bgcolor", "����", "�к�","bmmc", "sqr", "syrq", "hdkssj", "hdjssj", "ykxx","hdsh" };
		}
		sql = "select * from " + tableName;
		sql = "select (case when nvl(a.hdsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor," + 
		pk + " ����,rownum �к�,a.* from " + tableName + " a"+ querry;
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
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("act", "view");// �������ݲ�ѯ����
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);// ���ͼ�¼��
		request.setAttribute("chkList", dao.getChkList(3));
		List xxqList = dao.getList("select dm,xqmc from dm_zju_xq", new String[]{}, new String[]{"dm","xqmc"});
		request.setAttribute("xxqList", xxqList);//����У��������
		return mapping.findForward("success");

	}

	private ActionForward priseConf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// ��ʼ��ҳ�棬���ز�ѯ��Ϣ
		String xxdm = StandardOperation.getXxdm();
		String utype = request.getSession().getAttribute("userType").toString();
		//ѧ���û�û��Ȩ�޵�¼
		if (GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(utype)
				|| GlobalsVariable.XTDM_STU.equalsIgnoreCase(utype)) {
			request.setAttribute("errMsg", "����Ȩ���ʸ�ҳ��!");
			return new ActionForward("/errMsg.do");
		}
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG) || xxdm.equalsIgnoreCase(Globals.XXDM_GUIZHDX)){
			return new ActionForward("/zjlgPjpy.do?method=cssz",false);
		} else if (Globals.XXDM_XMLGXY.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/pjpy_xmlg_csblsz.do", false);
		} else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/gzdxPjpy.do?method=rssz", false);
		} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/pjpy_zjcm_rsblsz.do", false);
		} else if (Globals.XXDM_LSXY.equalsIgnoreCase(xxdm)){
			return new ActionForward("/pjpy_zjcm_rsblsz.do", false);
		} else if (Globals.XXDM_ZJKJXY.equalsIgnoreCase(xxdm)){
			return new ActionForward("/pjpy_tyb_pjsjsz.do", false);
		} else if (Globals.XXDM_NTZYDX.equalsIgnoreCase(xxdm)){
			return new ActionForward("/pjpy_tyb_pjsjsz.do", false);
		}
		DAO dao = DAO.getInstance();
		String currXn = "";
		String currNd = "";
		currXn = dao.getConf(2);
		currNd = dao.getConf(3);
		CommanForm priseForm = (CommanForm) form;

		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		HttpSession session = request.getSession();
		String sql = "";// sql���
		String querry = " where 1=1 ";// sql����
		String tips = "";// λ����ʾ��Ϣ
		String tableName = "";// ��ѯ��ϢԴ����Ϊ��ͼ����
		String rsNum = "0";// ���صļ�¼��
		String realTable = "";// ����Դ��
		String pk = "";// ����Դ����������ʽΪ���ֶ���||�ֶ���||�ֶ�������
		String writeAble = "yes";// дȨ��
		String dataType = request.getParameter("act");
		String dispFlag = priseForm.getDispFlag();
		dispFlag = (dispFlag == null) ? "xydm" : dispFlag;
		String xydm = priseForm.getXydm();
		String zydm = priseForm.getZydm();
		String bjdm = priseForm.getBjdm();
		String nj = priseForm.getNj();
		String jxjfl = DealString.toGBK(priseForm.getJxjfl());
		xydm = isNull(xydm) ? "all" : xydm;
		zydm = isNull(zydm) ? "all" : zydm;
		bjdm = isNull(bjdm) ? "all" : bjdm;
		jxjfl = jxjfl==null ? "" : jxjfl;
		request.setAttribute("xq", dao.getOneRs("select jxjsqxq from xtszb", new String[]{}, "jxjsqxq"));
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String bmdm = xydm;
		if (userType.equalsIgnoreCase("xy")&&!xxdm.equalsIgnoreCase(Globals.XXDM_ZJCMXY)) {
			return new ActionForward("/xy_priseConf.do",false);
		}else if(userType.equalsIgnoreCase("xy")){
			xydm = userDep;
		}
		

		if (dispFlag.equalsIgnoreCase("xydm")) {
			if (isNull(xydm)) {
				querry += " and 1=1 and bmlb='xydm' ";
			} else {
				querry += " and bmdm='" + xydm + "' and bmlb='xydm' ";
			}
		}

		if (dispFlag.equalsIgnoreCase("zydm")) {
			if (isNull(zydm) && isNull(xydm)) {
				querry += " and 1=1 and bmlb='zydm' ";
			} else if (isNull(zydm) && !isNull(xydm)) {
				querry += " and bmlb='zydm' and bmdm in ("
					+ "select distinct zydm from view_njxyzybj where "
					+ "xydm='" + xydm + "') ";
			} else if (!isNull(zydm)) {
				querry += " and bmlb='zydm' and bmdm='" + zydm + "' ";
			}
		}

		if (dispFlag.equalsIgnoreCase("bjdm")) {
			if (isNull(bjdm) && isNull(zydm) && isNull(xydm)) {
				querry += " and 1=1 and bmlb='bjdm' ";
			} else if (isNull(bjdm) && isNull(zydm) && !isNull(xydm)) {
				querry += " and bmlb='bjdm' and bmdm in ("
					+ "select distinct bjdm from view_njxyzybj where "
					+ "xydm='" + xydm + "') ";
			} else if (isNull(bjdm) && !isNull(zydm)) {
				querry += " and bmlb='bjdm' and bmdm in ("
					+ "select distinct bjdm from view_njxyzybj where "
					+ "zydm='" + zydm + "') ";
			} else if (!isNull(bjdm)) {
				querry += " and bmlb='bjdm' and bmdm='" + bjdm + "' ";
			}
		}
		
		

		if (isNull(jxjfl)) {
			querry += " and 1=1 ";
		} else {
			querry += " and jxjfl='" + jxjfl + "'" ;
		}		

		String jxjdm = priseForm.getXmdm();
		jxjdm = jxjdm ==null ||jxjdm.equals("null") ? "" : jxjdm;
		if (dataType == null) {
			dataType = "";
		}
		querry += " and xn = '" + currXn + "' ";
		querry += " and nd = '" + currNd + "' ";
		if (isNull(nj)) {
			querry += " and 1=1 ";
		} else {
			querry += " and nj = '" +nj+ "' ";
		}
		if (isNull(jxjdm)) {
			querry += " and 1=1 ";
		} else {
			querry += " and jxjdm = '" + jxjdm + "' ";
		}
		if (!isNull(xydm)) {
			//��ѯ���ϽǱ�����ݵĲ���
			String sql1 = "select nvl(sum(cprs*jxjbl),'0') rsNum1,nvl(sum(jxjrs),'0') rsNum2 from xyjxjrs where bmdm=? and jxjdm=? and xn=? and nd=? and bmlb='xydm'";
			String[] rs1 = dao.getOneRs(sql1, new String[] { xydm,
					"0000000001", currXn, currNd }, new String[] { "rsNum1",
			"rsNum2" });
			String[] rs2 = dao.getOneRs(sql1, new String[] { xydm,
					"0000000002", currXn, currNd }, new String[] { "rsNum1",
			"rsNum2" });
			String[] rs3 = dao.getOneRs(sql1, new String[] { xydm,
					"0000000003", currXn, currNd }, new String[] { "rsNum1",
			"rsNum2" });
			String[] rs4 = dao.getOneRs(sql1, new String[] { xydm,
					"0000000009", currXn, currNd }, new String[] { "rsNum1",
			"rsNum2" });
			String[] rs5 = dao.getOneRs(sql1, new String[] { xydm,
					"0000000010", currXn, currNd }, new String[] { "rsNum1",
			"rsNum2" });
			String[] rs6 = dao.getOneRs(sql1, new String[] { xydm,
					"0000000011", currXn, currNd }, new String[] { "rsNum1",
			"rsNum2" });
			String[] rs7 = new String[2];
			try {
				rs7[0] = String.valueOf(Float.parseFloat(rs1[0]) * 1500
						+ Float.parseFloat(rs2[0]) * 1000
						+ Float.parseFloat(rs3[0]) * 500
						+ Float.parseFloat(rs4[0]) * 500
						+ Float.parseFloat(rs5[0]) * 500
						+ Float.parseFloat(rs6[0]) * 500);
				rs7[1] = String.valueOf(Float.parseFloat(rs1[1]) * 1500
						+ Float.parseFloat(rs2[1]) * 1000
						+ Float.parseFloat(rs3[1]) * 500
						+ Float.parseFloat(rs4[1]) * 500
						+ Float.parseFloat(rs5[1]) * 500
						+ Float.parseFloat(rs6[1]) * 500);
				if (Float.parseFloat(rs7[0]) - Float.parseFloat(rs7[1]) > 500) {
					request.setAttribute("bgColor", "#0000FF");
				} else if (Float.parseFloat(rs7[0]) - Float.parseFloat(rs7[1]) > 0) {
					request.setAttribute("bgColor", "#000000");
				} else {
					request.setAttribute("bgColor", "#FF0000");
				}
			} catch (NumberFormatException e) {

			}
			request.setAttribute("rs1", rs1[0]);
			request.setAttribute("rs2", rs2[0]);
			request.setAttribute("rs3", rs3[0]);
			request.setAttribute("rs4", rs4[0]);
			request.setAttribute("rs5", rs5[0]);
			request.setAttribute("rs6", rs6[0]);
			request.setAttribute("rs7", rs7[0]);
			request.setAttribute("rs11", rs1[1]);
			request.setAttribute("rs21", rs2[1]);
			request.setAttribute("rs31", rs3[1]);
			request.setAttribute("rs41", rs4[1]);
			request.setAttribute("rs51", rs5[1]);
			request.setAttribute("rs61", rs6[1]);
			request.setAttribute("rs71", rs7[1]);
		}
		realTable = "xyjxjrs";
//		ѧ��mod
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJCMXY)){
			String xq = priseForm.getXq();
			if(xq==null||xq.equalsIgnoreCase("")){
				xq = Base.getJxjsqxq();
			}
			querry += " and xqdm='" + xq + "' ";
			pk = "xn||bmdm||nd||jxjdm||xqdm";
		}else{
			pk = "xn||bmdm||nd||jxjdm";
		}
		
		tips = "�������� - �������� - ��������";
//		String xxmc=request.getSession().getAttribute("xxmc").toString();
		tableName = "view_xyjxjrs";
		colList = new String[] { "����", "�к�", "mc","nj", "jxjmc","jxjbl", "cprs", "jsrs","jxjrs","jsje" };
//		if (Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
//		colList = new String[] { "����", "�к�", "mc", "jxjmc","jxjbl", "cprs", "jsrs","jxjrs" };
//		}
		sql = "select " + pk + " ����,rownum �к�,a.xn,a.nd,a.bmdm,a.bmlb,a.jxjdm,a.cprs,nvl(case when instr(to_char(jsrs), '.', 1, 1) = 1 then '0' || to_char(jsrs) else to_char(jsrs) end, '0') jsrs,a.jxjrs,a.jxjmc,a.jxjlb,a.jxjjb," +
				"a.jlje,a.mc,a.jxjfl,a.jxjbl,a.nj,nvl(a.jxjrs,'0')*(select jlje from jxjdmb b where b.jxjdm=a.jxjdm) jsje from " + tableName + " a"+ querry;
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
		if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLYDX)){
			sql = "select jxjdm,jxjmc from jxjdmb_tmp_bjly";
		}else{
			sql = "select jxjdm,jxjmc from jxjdmb order by jxjdm";
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){//�人����ѧ
			sql = "select distinct JXJFL jxjfldm,JXJFL jxjflmc from jxjdmb";
			List jxjflList = dao.getList(sql, new String[]{}, new String[]{"jxjfldm","jxjflmc"});
			request.setAttribute("jxjflList", jxjflList);
			if(jxjfl!=null && jxjfl!=""){
				sql = "select distinct jxjdm,jxjmc from jxjdmb where jxjfl='" + jxjfl + "' order by jxjdm";
			}else{
				sql = "select distinct jxjdm,jxjmc from jxjdmb order by jxjdm";
			}
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJCMXY)){
			sql = "select distinct jxjdm,jxjmc from jxjdmb where jxjlb = 'У' order by jxjdm";
		}
		List jxjList = dao.getList(sql, new String[] {}, new String[] {"jxjdm", "jxjmc" });
		priseForm.setXn(currXn);
		priseForm.setNd(currNd);
		priseForm.setXq(Base.getJxjsqxq());
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)||xxdm.equalsIgnoreCase(Globals.XXDM_ZJCMXY)){
			//ѧ��ģʽ
			request.setAttribute("xqmod", "on");
		}else{
			request.setAttribute("xqmod", "off");
		}
		
		request.setAttribute("path", "prise_conf_rs.do");
		FormModleCommon.commonRequestSet(request);
		
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("act", dataType);// �������ݲ�ѯ����
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("njList", dao.getNjList());// �����꼶�б�
		request.setAttribute("xnList", dao.getXnndList());// ����ѧ���б�
		request.setAttribute("xqList", dao.getXqList());// ����ѧ���б�
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", dao.getZyList(xydm));// ����רҵ�б�
		request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));
		request.setAttribute("jxjList", jxjList);
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);
		request.setAttribute("userDepName", dao.getXymcById(bmdm));
		priseForm.setXydm(xydm);
		priseForm.setZydm(zydm);
		priseForm.setBjdm(bjdm);
		return mapping.findForward("success");
	}

	private ActionForward initBaseData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// ��ʼ����ǰѧ�ꡢѧ������
		DAO dao = DAO.getInstance();
		String sql = "";// sql���
		String xxdm = StandardOperation.getXxdm();
		String[] inputcol = null;
		if(xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)){
			sql = "{call initAhjgBaseData()}";
			inputcol = new String[]{};
		}if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJCMXY)){
			sql = "{call initPjxqBaseData()}";
			inputcol = new String[]{};
		}else{
			sql = "{call initBaseData(?)}";
			if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
				inputcol = new String[]{"byny"};
			}else{
				inputcol = new String[]{""};
			}
		}

		boolean res = dao.runProcuder(sql,inputcol);

		if (res) {
			request.setAttribute("initOK", "ok");
		} else {
			request.setAttribute("initOK", "no");
		}
		return mapping.findForward("success");
	}

	private ActionForward chgPriseXn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// ���ĵ�ǰѧ�ꡢѧ��
		CommanForm priseForm = (CommanForm) form;
		DAO dao = DAO.getInstance();
		String sql = "";// sql���
		String xn = priseForm.getXn();
		String nd = priseForm.getNd();
		String xq = priseForm.getXq();
		sql = "update xtszb set jxjsqxn=?,jxjsqnd=?,jxjsqxq =? where rownum=1";
		dao.runUpdate(sql, new String[] { xn, nd, xq });
		Base.setJxjsqxn(xn);
		Base.setJxjsqnd(nd);
		Base.setJxjsqxq(xq);
		sql = "select xqmc from xqdzb  where xqdm =?";
		String xqmc = dao.getOneRs(sql, new String[]{xq}, "xqmc");
		Base.setJxjsqxqmc(xqmc);
		return null;
	}

	private ActionForward chgPriseTime(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// ��ʼ���������ڣ����޸ġ����棩����
		String xxdm = StandardOperation.getXxdm();
		DAO dao = DAO.getInstance();
		String currXn = "";
		String currNd = "";
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
			currXn = dao.getConf(2);
			currNd = dao.getConf(3);
		}else{
			currXn = dao.getConf(2);
			currNd = dao.getConf(3);
		}

//		String jxjXn="";
//		String jxjNd="";
//		String sql_lrh="select JXJSQXN ,JXJSQND from xtszb ";
//		HashMap<String, String> map_lrh = new HashMap<String, String>();
//		map_lrh=dao.getMapNotOut(sql_lrh, new String [] {});
//		jxjXn=map_lrh.get("jxjsqxn").toString();
//		jxjNd=map_lrh.get("jxjsqnd").toString();
//		String dqXn = dao.getConf(0);
//		String dqXq = dao.getConf(1);
//		String dqNd = dao.getConf(4);
		CommanForm priseForm = (CommanForm) form;
		String sql = "";// sql���
		String querry = "";
		String xy = request.getParameter("xy");
		String zy = request.getParameter("zy");
		String bj = request.getParameter("bj");
		String jxj = request.getParameter("jxj");
		String bmdm = "";
		String bmlb = "xydm";
		bmdm = !isNull(xy) ? xy : null;
		bmdm = !isNull(zy) ? zy : null;
		bmdm = !isNull(bj) ? bj : null;
		bmlb = !isNull(bj) ? "bjdm" : bmlb;
		bmlb = isNull(zy) ? "zydm" : bmlb;

		String jxjmc;
		if (isNull(bmdm)) {
			querry += " and 1=1";
		} else {
			querry += " and bmdm='" + bmdm + "'";
		}
		if (isNull(bmlb)) {
			querry += " and 1=1";
		} else {
			querry += " and bmlb='" + bmlb + "'";
		}
		if (isNull(jxj) || jxj.equals("null")) {
			jxjmc = "ȫ��";
			querry += " and 1=1";
		} else {
			if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLYDX)){
				sql = "select jxjmc from jxjdmb_tmp_bjly where jxjdm=?";
			}else{
				sql = "select jxjmc from jxjdmb where jxjdm=?";
			}


			String[] tmp = dao.getOneRs(sql, new String[] { jxj },
					new String[] { "jxjmc" });
			jxjmc = tmp[0];
			querry += " and jxjdm='" + jxj + "'";
		}
//		sql = "select strtodatetime(substr(shkssj,1,8)) shkssj1,"
//		+ "substr(shkssj,9,2) shkssj2,"
//		+ "substr(shkssj,11,2) shkssj3,"
//		+ "substr(shkssj,13,2) shkssj4,"
//		+ "strtodatetime(substr(shjzdj,1,8)) shjzdj1,"
//		+ "substr(shjzdj,9,2) shjzdj2,"
//		+ "substr(shjzdj,11,2) shjzdj3,"
//		+ "substr(shjzdj,13,2) shjzdj4,"
//		+ "strtodatetime(substr(xssqkssj,1,8)) xssqkssj1,"
//		+ "substr(xssqkssj,9,2) xssqkssj2,"
//		+ "substr(xssqkssj,11,2) xssqkssj3,"
//		+ "substr(xssqkssj,13,2) xssqkssj4,"
//		+ "strtodatetime(substr(xssqjssj,1,8)) xssqjssj1,"
//		+ "substr(xssqjssj,9,2) xssqjssj2,"
//		+ "substr(xssqjssj,11,2) xssqjssj3,"
//		+ "substr(xssqjssj,13,2) xssqjssj4 from xyshsjb "
//		+ "where xn=? and nd=? and xmgs='jxj' and rownum=1";
//		String[] rst = { "shkssj1", "shkssj2", "shkssj3", "shkssj4", "shjzdj1",
//		"shjzdj2", "shjzdj3", "shjzdj4", "xssqkssj1", "xssqkssj2",
//		"xssqkssj3", "xssqkssj4", "xssqjssj1", "xssqjssj2",
//		"xssqjssj3", "xssqjssj4" };
//		String[] sj = dao.getOneRs(sql, new String[] { currXn, currNd }, rst);
		String[] tj = null;
//		if (sj == null) {
//		sj = new String[] { "", "", "", "", "", "", "", "", "", "", "", "",
//		"", "", "", "" };
//		}
//		for (int i = 0; i < sj.length; i++) {
//		request.setAttribute(rst[i], sj[i]);
//		}
		String[] opList = new String[] { "jxjbl", "jxjrs","cprs","jsje" };
		if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {
			sql = "select nvl(jxjbl*100,0) jxjbl,pjf from xyjxjrs where xn=? and nd=? "
				+ querry + " and rownum=1 order by jxjbl*100 desc";
			tj = dao.getOneRs(sql, new String[] { currXn, currNd },
					new String[] { "jxjbl" , "pjf"});
			if (tj == null) {
				tj = new String[] { "",""};
			}else{
				request.setAttribute("pjf", tj[1]);//���ս���
			}
		}else {
			sql = "select nvl(jxjbl*100,0) jxjbl,jxjrs,cprs,jsje from xyjxjrs where xn=? and nd=? "
				+ querry + " and rownum=1 order by jxjbl*100 desc";
			tj = dao.getOneRs(sql, new String[] { currXn, currNd },
					opList);
		}

		if (tj == null) {
			tj = new String[] { "","","","" };
		}
		for (int i=0;i<tj.length;i++) {
			request.setAttribute(opList[i], tj[i]);
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {
			request.setAttribute("showAHJG", "yes");
		}
		request.setAttribute("xymc", dao.getBmmcByDm(xy, "ȫ��"));
		request.setAttribute("zymc", dao.getBmmcByDm(zy, "ȫ��"));
		request.setAttribute("bjmc", dao.getBmmcByDm(bj, "ȫ��"));
		request.setAttribute("jxjmc", jxjmc);
		priseForm.setXydm(xy);
		priseForm.setZydm(zy);
		priseForm.setBjdm(bj);
		priseForm.setXmdm(jxj);
		priseForm.setXn(currXn);
		priseForm.setNd(currNd);
		request.setAttribute("jxjbl", tj[0]);
		request.setAttribute("jxjrs", tj[1]==null ? "" : tj[1]);
		request.setAttribute("currxn", currXn);
		request.setAttribute("currnd", currNd);
		return new ActionForward("/sjcz/set_prise_bat.jsp", false);
	}

	private ActionForward chgPriseBat(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String xxdm = StandardOperation.getXxdm();
		DAO dao = DAO.getInstance();
		String currXn = "";
		String currNd = "";
		currXn = dao.getConf(2);
		currNd = dao.getConf(3);
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
			currXn = dao.getConf(2);
			currNd = dao.getConf(3);
		}
		// �����޸�
		CommanForm priseForm = (CommanForm) form;
		String sql = "";// sql���
		String querry = "";
		Vector<HashMap<String, Object>> vecSql = new Vector<HashMap<String, Object>>();
		HashMap<String, Object> mapSql = new HashMap<String, Object>();
		String xydm = priseForm.getXydm();
		String zydm = priseForm.getZydm();
		String bjdm = priseForm.getBjdm();
		String jxjdm = priseForm.getXmdm();
		String rsbl = request.getParameter("jyrs");
		String jxjrs = request.getParameter("jxjrs");//��ѧ������
		String sdfs = DealString.toGBK(request.getParameter("sdfs"));
		String sqkssj = request.getParameter("xssqkssj");
		String sqjssj = request.getParameter("xssqjssj");
		String shjssj = request.getParameter("shjzdj");
		String shkssj = request.getParameter("shkssj");

		String pjf = request.getParameter("pjf");//���ս�����ҵѧԺרҵƽ����
		//���ڱ�����ҵ��ѧ,ӦΪ�ñ���Ϊ��,ȡ1ֵ,��ҵ��ʱ�����ò�������ȡ
		if(sqkssj==null){
			sqkssj="1";
		}
		if(sqjssj==null){
			sqjssj="1";
		}
		if(shjssj==null){
			shjssj="1";
		}
		if(shkssj==null){
			shkssj="1";
		}
		sql = "delete from xyshsjb where xn=? and nd=? and xmgs=?";
		mapSql.put("sqlTxt", sql);
		mapSql.put("sqlVal", new String[] { currXn, currNd, "jxj" });
		vecSql.add(mapSql);
		sql = "insert into xyshsjb(xh,shxmdm,shjzdj,xssqkssj,xssqjssj,shkssj,nd,xn,xmgs) "
			+ "values(lsh_sjb.nextval,?,?,?,?,?,?,?,?)";
		mapSql = new HashMap<String, Object>();
		mapSql.put("sqlTxt", sql);
		mapSql.put("sqlVal", new String[] { "jxj", shjssj, sqkssj, sqjssj,shkssj, currNd, currXn, "jxj" });
		vecSql.add(mapSql);
		double bl = 0.00;
		try {
			bl = ((int) (Float.parseFloat(rsbl) * 1000)) / 100000.00;
		} catch (NumberFormatException e) {
			bl = 0.00;
		}

		if (!isNull(bjdm)) {
			querry = " and bmdm='" + bjdm + "' and bmlb='bjdm' ";
		}
		else if (!isNull(zydm)) {
			querry = " and bmdm='" + zydm + "' and bmlb='zydm' ";
		}else if(!isNull(xydm)){
			querry = " and bmdm='" + xydm + "' and bmlb='xydm' ";
		}else {
			querry = " and 1=1 ";
		}
		if (isNull(jxjdm)) {
			querry += " and 1=1 ";
		} else {
			querry += " and jxjdm='" + jxjdm + "' ";
		}
		
		String jxjdmQuery = "";
		if (isNull(jxjdm)) {
			jxjdmQuery += " and 1=1 ";
		} else {
			jxjdmQuery += " and jxjdm='" + jxjdm + "' ";
		}
		boolean res = false;
		if (bl >= 0) {
			if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {
				sql = "update xyjxjrs set zsbj='1',pjf='"+pjf+"', jxjbl='" + bl
				+ "',jxjrs=(case when to_number(cprs)*" + bl
				+ "<0.5 then '0' else to_char(round(to_number(cprs)*"+bl+")) end) where xn=? and nd=? " + querry;
			}else if(xxdm.equals(Globals.XXDM_WHLGDX) && sdfs!=null && sdfs.equals("����")){
				sql = "update xyjxjrs set zsbj='1', jxjbl='',jxjrs='" + jxjrs + "' where xn=? and nd=? " + querry;
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {//�Լ������������������
				sql = "update xyjxjrs set zsbj='1', jxjbl='" + bl
				+ "',jxjrs=(case when to_number(cprs)*" + bl
				+ "<0.5 then '0' else to_char(round(to_number(cprs)*"+bl+")) end) where xn=? and nd=? " + querry;

				String squerry = " and bmlb='zydm' and bmdm in (select distinct zydm from view_njxyzybj where xydm='"+xydm+"')";
				String ssql = "update xyjxjrs set zsbj='1', jxjbl='" + bl
				+ "',jxjrs=(case when to_number(cprs)*" + bl
				+ "<0.5 then '0' else to_char(round(to_number(cprs)*"+bl+")) end) where xn=? and nd=? " + jxjdmQuery + squerry;
				dao.runUpdate(ssql, new String[]{Base.getJxjsqxn(), Base.getJxjsqnd()});
				squerry = " and bmlb='bjdm' and bmdm in (select distinct bjdm from view_njxyzybj where xydm='"+xydm+"')";
				ssql = "update xyjxjrs set zsbj='1', jxjbl='" + bl
				+ "',jxjrs=(case when to_number(cprs)*" + bl
				+ "<0.5 then '0' else to_char(round(to_number(cprs)*"+bl+")) end) where xn=? and nd=? " + jxjdmQuery + squerry;
				dao.runUpdate(ssql, new String[]{Base.getJxjsqxn(), Base.getJxjsqnd()});
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY) || xxdm.equalsIgnoreCase(Globals.XXDM_SHCBYSGDZKXX) || xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)) {
				sql = "update xyjxjrs set zsbj='1', jxjbl='" + bl
				+ "',jxjrs=(case when to_number(cprs)*" + bl
				+ "<0.5 then '0' else to_char(round(to_number(cprs)*"+bl+")) end) where xn=? and nd=? " + querry;
				if (xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ) || Globals.XXDM_HZZY.equalsIgnoreCase(xxdm) || Globals.XXDM_SHCBYSGDZKXX.equalsIgnoreCase(xxdm)) {
					String squerry = " and bmlb='zydm' and bmdm in (select distinct zydm from view_njxyzybj where xydm='"+xydm+"')";
					String ssql = "update xyjxjrs set zsbj='1', jxjbl='" + bl
					+ "',jxjrs=(case when to_number(cprs)*" + bl
					+ "<0.5 then '0' else to_char(round(to_number(cprs)*"+bl+")) end) where xn=? and nd=? " + jxjdmQuery+squerry;
					dao.runUpdate(ssql, new String[]{Base.getJxjsqxn(), Base.getJxjsqnd()});
					squerry = " and bmlb='bjdm' and bmdm in (select distinct bjdm from view_njxyzybj where xydm='"+xydm+"')";
					ssql = "update xyjxjrs set zsbj='1', jxjbl='" + bl
					+ "',jxjrs=(case when to_number(cprs)*" + bl
					+ "<0.5 then '0' else to_char(round(to_number(cprs)*"+bl+")) end) where xn=? and nd=? " + jxjdmQuery+squerry;
					dao.runUpdate(ssql, new String[]{Base.getJxjsqxn(), Base.getJxjsqnd()});
				}
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
				sql = "update xyjxjrs set zsbj='1', jxjbl='" + bl
				+ "',jxjrs=(case when to_number(cprs)*" + bl
				+ "<0.5 then '0' else to_char(round(to_number(cprs)*"+bl+")) end) where xn=? and nd=? " + querry;

			} else if (Globals.XXDM_SCJZZYJSXY.equalsIgnoreCase(xxdm)) {
				sql = "update xyjxjrs set zsbj='1', jxjbl='" + bl
				+ "',jxjrs=(case when cprs*" + bl
				+ "<1 then '0' else (case when instr(cprs*" + bl
				+ ",'.')>0 then substr(cprs*" + bl + ",1,instr(cprs*" + bl
				+ ",'.')-1) else to_char(cprs*" + bl
				+ ") end) end) where xn=? and nd=? " + querry;


				String squerry = " and bmlb='zydm' and bmdm in (select distinct zydm from view_njxyzybj where xydm='"+xydm+"')";
				String ssql = "update xyjxjrs set zsbj='1', jxjbl='" + bl
				+ "',jxjrs=(case when to_number(cprs)*" + bl
				+ "<0.5 then '0' else to_char(round(to_number(cprs)*"+bl+")) end) where xn=? and nd=? " + jxjdmQuery+squerry;
				dao.runUpdate(ssql, new String[]{Base.getJxjsqxn(), Base.getJxjsqnd()});
				squerry = " and bmlb='bjdm' and bmdm in (select distinct bjdm from view_njxyzybj where xydm='"+xydm+"')";
				ssql = "update xyjxjrs set zsbj='1', jxjbl='" + bl
				+ "',jxjrs=(case when to_number(cprs)*" + bl
				+ "<0.5 then '0' else to_char(round(to_number(cprs)*"+bl+")) end) where xn=? and nd=? " + jxjdmQuery+ squerry;
				dao.runUpdate(ssql, new String[]{Base.getJxjsqxn(), Base.getJxjsqnd()});

			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJCMXY)) {//�㽭��ý0.45��λ
				sql = "update xyjxjrs set zsbj='1', jxjbl='" + bl
				+ "',jxjrs=(case when to_number(cprs)*" + bl
				+ "<0.45 then '0' else to_char(round(round(to_number(cprs)*"+bl+",1))) end) where xn=? and nd=? and xqdm = ? " + querry;

				String squerry = " and bmlb='zydm' and bmdm in (select distinct zydm from view_njxyzybj where xydm='"+xydm+"')";
				String ssql = "update xyjxjrs set zsbj='1', jxjbl='" + bl
				+ "',jxjrs=(case when to_number(cprs)*" + bl
				+ "<0.45 then '0' else to_char(round(round(to_number(cprs)*"+bl+",1))) end) where xn=? and nd=? and xqdm = ? " + jxjdmQuery + squerry;
				dao.runUpdate(ssql, new String[]{Base.getJxjsqxn(), Base.getJxjsqnd(),Base.getJxjsqxq()});
				squerry = " and bmlb='bjdm' and bmdm in (select distinct bjdm from view_njxyzybj where xydm='"+xydm+"')";
				ssql = "update xyjxjrs set zsbj='1', jxjbl='" + bl
				+ "',jxjrs=(case when to_number(cprs)*" + bl
				+ "<0.45 then '0' else to_char(round(round(to_number(cprs)*"+bl+",1))) end) where xn=? and nd=? and xqdm = ? " + jxjdmQuery+squerry;
				dao.runUpdate(ssql, new String[]{Base.getJxjsqxn(), Base.getJxjsqnd(),Base.getJxjsqxq()});
			}else{
//				sql = "update xyjxjrs set zsbj='1', jxjbl='" + bl
//				+ "',jxjrs=(case when cprs*" + bl
//				+ "<1 then '0' else (case when instr(cprs*" + bl
//				+ ",'.')>0 then substr(cprs*" + bl + ",1,instr(cprs*" + bl
//				+ ",'.')-1) else to_char(cprs*" + bl
//				+ ") end) end) where xn=? and nd=? " + querry;
				sql = "update xyjxjrs set zsbj='1', jxjbl='" + bl
				+ "',jxjrs=(case when cprs*" + bl
				+ "<1 then '0' else (case when instr(cprs*" + bl
				+ ",'.')>0 then substr(cprs*" + bl + ",1,instr(cprs*" + bl
				+ ",'.')-1) else to_char(cprs*" + bl
				+ ") end) end) where xn=? and nd=? " + querry;

				String squerry = " and bmlb='zydm' and bmdm in (select distinct zydm from view_njxyzybj where xydm='"+xydm+"')";
				String ssql = "update xyjxjrs set zsbj='1', jxjbl='" + bl
				+ "',jxjrs=(case when to_number(cprs)*" + bl
				+ "<0.5 then '0' else to_char(round(to_number(cprs)*"+bl+")) end) where xn=? and nd=? " + jxjdmQuery +squerry;
				dao.runUpdate(ssql, new String[]{Base.getJxjsqxn(), Base.getJxjsqnd()});
				squerry = " and bmlb='bjdm' and bmdm in (select distinct bjdm from view_njxyzybj where xydm='"+xydm+"')";
				ssql = "update xyjxjrs set zsbj='1', jxjbl='" + bl
				+ "',jxjrs=(case when to_number(cprs)*" + bl
				+ "<0.5 then '0' else to_char(round(to_number(cprs)*"+bl+")) end) where xn=? and nd=? " + jxjdmQuery+squerry;
				dao.runUpdate(ssql, new String[]{Base.getJxjsqxn(), Base.getJxjsqnd()});
			}
			mapSql = new HashMap<String, Object>();
			mapSql.put("sqlTxt", sql);
			mapSql.put("sqlVal", new String[] { currXn, currNd });
			vecSql.add(mapSql);
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJCMXY)) {//�㽭��ý0.45��λ
				mapSql.put("sqlVal", new String[] {Base.getJxjsqxn(), Base.getJxjsqnd(),Base.getJxjsqxq()});
			}
			res = dao.runUpdate(vecSql);
			if(res){
				if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
					sql = "delete from xyfpjxjfs where xn=? and nd=?";
					dao.runUpdate(sql, new String []{currXn,currNd});
					sql = "insert into xyfpjxjfs (XN,ND,BMDM,FPFS) select '"+currXn+"' xn,'"+currNd+"' nd,xydm,'bj' FPFS from (select distinct xydm,xymc from view_njxyzybj)";
					dao.runUpdate(sql, new String []{});
					sql = "delete from xyjxjfpb";
					dao.runUpdate(sql, new String []{});
					sql = "insert into xyjxjfpb select a.xn,a.nd,b.xydm bmdm,a.bmdm fpbm,a.nj,a.cprs,jxjrs,jxjdm,jxjrs,xqdm from xyjxjrs a left join  view_njxyzybj b on a.bmdm = b.bjdm where bmlb = 'bjdm'";
					dao.runUpdate(sql, new String []{});
				}
			}
		}
		if (res) {
			dao.runUpdate("update xyjxjrs a set jsje=(jxjrs*(select jlje from jxjdmb b where b.jxjdm=a.jxjdm)) where jxjrs is not null and jxjrs>0 and xn='"+Base.getJxjsqxn()+"' and nd='"+Base.getJxjsqnd()+"'", new String[]{});
			request.setAttribute("succ", "OK");
		} else {
			request.setAttribute("succ", "NO");
		}
		return mapping.findForward("success");
	}

	private ActionForward chgPriseOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
//		String xxdm = StandardOperation.getXxdm();
//		String currXn = "";
//		String currNd = "";
//		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
//		currXn = dao.getConf(2);
//		currNd = dao.getConf(3);
//		}else{
//		currXn = Base.currXn;
//		currNd = Base.currNd;
//		}
//		String dqXn = dao.getConf(0);
//		String dqXq = dao.getConf(1);
//		String dqNd = dao.getConf(4);
		DAO dao = DAO.getInstance();
		String xxdm = dao.getXxdm();
//		String xxdm = dao.getXxdm();
		String jxjXn="";
		String jxjNd="";
		String jxjXq="";
		String sql_lrh="select JXJSQXN ,JXJSQND from xtszb ";
		HashMap<String, String> map_lrh = new HashMap<String, String>();
		map_lrh=dao.getMapNotOut(sql_lrh, new String [] {});
		jxjXn=map_lrh.get("jxjsqxn").toString();
		jxjNd=map_lrh.get("jxjsqnd").toString();
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJCMXY)){
			jxjXq=Base.getJxjsqxq();
		}
		// �����޸�
		HttpSession session = request.getSession();
		String sql = "";// sql���
		String bmdm = request.getParameter("bm");
		String pk = request.getParameter("pk");
		String nj = request.getParameter("nj");		
		String jxjdm = request.getParameter("jxjdm");
//		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		String rstz = request.getParameter("rstz");
		String jsje = request.getParameter("jsje");
		/*if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {

		}else{*/
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJCMXY)){
			sql = "update xyjxjrs set jxjrs = ? where xn=? and nd=? and jxjdm=? and bmdm=? and xqdm = ? and nj = ?";
			boolean result = dao.runUpdate(sql, new String[]{StringUtils.isNull(rstz) ? "" : rstz.trim(),jxjXn,jxjNd,jxjdm,bmdm,jxjXq,StringUtils.isNull(nj) ? "" : nj.trim()});
			request.setAttribute("chged", result==true ? "ok" : "no");
		}else {
			if(null != userType && !"xy".equalsIgnoreCase(userType)){
			sql = "update xyjxjrs set jxjrs = ? where xn=? and nd=? and jxjdm=? and bmdm=? and nj=?";
			boolean result = false;
			if (Globals.XXDM_HZZY.equalsIgnoreCase(xxdm)) {
				sql = "update xyjxjrs set jxjrs = ? where xn=? and nd=? and jxjdm=? and bmdm=?";
				result = dao.runUpdate(sql, new String[]{StringUtils.isNull(rstz) ? "" : rstz.trim(),jxjXn,jxjNd,jxjdm,bmdm});
			} else if (Globals.XXDM_SCJZZYJSXY.equalsIgnoreCase(xxdm)) {
				sql = "update xyjxjrs set jsje = ? where xn=? and nd=? and jxjdm=? and bmdm=? and nj=?";
				result = dao.runUpdate(sql, new String[]{StringUtils.isNull(jsje) ? "" : jsje,jxjXn,jxjNd,jxjdm,bmdm,StringUtils.isNull(nj) ? "" : nj.trim()});
			} else {
				result = dao.runUpdate(sql, new String[]{StringUtils.isNull(rstz) ? "" : rstz.trim(),jxjXn,jxjNd,jxjdm,bmdm,StringUtils.isNull(nj) ? "" : nj.trim()});
			}

			request.setAttribute("chged", result==true ? "ok" : "no");
			}else{
			/* sql = "{call SETPRISENUM(?,?,?,?,?,?,?,?)}";
			  String[] vals = dao.getProVal(sql, new String[] { rstz, jxjXn, jxjNd,
					bmdm, jxjdm,userDep,nj, "0" }, new int[] { 8 });
			  request.setAttribute("chged", vals[0]);*/
			//boolean res = dao.runUpdate("update xyjxjrs set jxjrs=? where xn||bmdm||nd||jxjdm=?", new String[]{rstz, jxjXn + bmdm + jxjNd + jxjdm});
			boolean res = dao.runUpdate("update xyjxjfpb set rstz=? where xn=? and nd=? and fpbm=? and jxjdm=? and nj=?", new String[]{StringUtils.isNull(rstz) ? "" : rstz.trim(), jxjXn, jxjNd, bmdm, jxjdm, StringUtils.isNull(nj) ? "" :nj.trim()});	
			if (res) {
				request.setAttribute("chged", "ok");
			} else {
				request.setAttribute("chged", "no");
			}
		}
		
		//}
		if(userType.equalsIgnoreCase("xy")){
			return new ActionForward("/xy_priseConf_one.do?pk=" + pk, false);
		}
		}
		return new ActionForward("/sjcz/prise_conf_one.jsp", false);
	}

	@SuppressWarnings("unchecked")
	private ActionForward setPriseCond(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// ���ý�ѧ��������
		String xxdm = StandardOperation.getXxdm();
		
		String utype = request.getSession().getAttribute("userType").toString();
		//ѧ���û�û��Ȩ�޵�¼
		if (GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(utype)
				|| GlobalsVariable.XTDM_STU.equalsIgnoreCase(utype)) {
			request.setAttribute("errMsg", "����Ȩ���ʸ�ҳ��!");
			return new ActionForward("/errMsg.do");
		}
		//����ѧԺ����ͨ���������̣�ע������20110506��sjf
//		if(xxdm.equalsIgnoreCase(Globals.XXDM_XCXY)){//����ѧԺ
//			return new ActionForward("/pjpyxcxywh.do?method=tjsz",false);
//		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)){//�㽭����ѧ
			return new ActionForward("/zjlgPjpy.do?method=tjsz",false);
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JHZYJSXY)){//��ְҵ
			return new ActionForward("/jhzy_pjpysz.do?method=tjsz",false);
		}
		DAO dao = DAO.getInstance();
		CommanForm myForm = (CommanForm) form;
//		HttpSession session = request.getSession();
		String jxj = request.getParameter("xmdm");
		String zdm = request.getParameter("zdm");
		String ysf = request.getParameter("ysf");
		String val = request.getParameter("val");
		String pk = "jxjdm||tjzdm||tjzdlyb";
		if(Globals.XXDM_ZJSYZYXY.equalsIgnoreCase(xxdm)){
			pk = "xn||jxjdm||tjzdm";
		}
		String zdmc = DealString.toGBK(request.getParameter("zdmc"));
		String xn  = myForm.getXn();
		String go = request.getParameter("go");
		String sql = "";// sql���

		String delALL = request.getParameter("delALL");
		String pkVal = request.getParameter("pkVal");
		if (!StringUtils.isNull(pkVal)) {
			boolean flag = dao.runUpdate("delete from jxjhdtj where "+pk+"=?", new String[]{pkVal});
			request.setAttribute("flag", flag); 
		}
		if ("1".equalsIgnoreCase(delALL)) {
			boolean flag = dao.runUpdate("delete from jxjhdtj", new String[]{});
			request.setAttribute("flag", flag); 
		}
//		String userType = session.getAttribute("userType").toString();

		if (xxdm.equalsIgnoreCase(Globals.XXDM_JGSDX)) {//����ɽ��ѧ
			return new ActionForward("/pjpy_jgsdx_tjszdefault.do", false);
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){//�人����ѧ
			return new ActionForward("/pjpy_whlgdx.do?method=tjssInit",false);
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)
				|| Globals.XXDM_SCJZZYJSXY.equalsIgnoreCase(xxdm)
				|| Globals.XXDM_ZJGSZYJSXY.equalsIgnoreCase(xxdm)) {// ���ս���,�㽭����,�Ĵ�����
			return new ActionForward("/pjpy_anjg_tjsz.do", false);
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLYDX)){
			return new ActionForward("/pjpy_bjly_tj.do",false);
		}else{
			sql = "select jxjdm,jxjmc from jxjdmb";
		}
		if (Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/pjpy_nbzy_tjsz.do", false);
		}
	/*	if (Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/pjpy_nblg_tjsz.do", false);
		}*/ //lyl

		List jxjList = dao.getList(sql, new String[] {}, new String[] {
				"jxjdm", "jxjmc" });
		sql = "select zdmc||'!!'||lyb zd,zdsm from jxjtjzdb";
		List zdList = dao.getList(sql, new String[] {}, new String[] { "zd", 
		"zdsm" });
		if(Globals.XXDM_ZJSYZYXY.equalsIgnoreCase(xxdm)){//�㽭��ҵְҵ
			request.setAttribute("isZjsyzyxy", "isZjsyzyxy");
			zdList = dao.getzJxjTj(1);
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
			zdList = new ArrayList<HashMap<String, String>>();
			String[] enList = new String[]{"xf!!bks_xscj", "jd!!bks_xscj"};
			String[] cnList = new String[]{"ѧ��", "����ѧ��"};
			for (int i=0; i<enList.length;i++) {
				HashMap<String, String> tmpMap = new HashMap<String, String>();
				tmpMap.put("zd", enList[i]);
				tmpMap.put("zdsm", cnList[i]);
				zdList.add(tmpMap);
			}
		}
		if ((go != null) && go.equalsIgnoreCase("go")) {
			if (isNull(zdm)) {
				return mapping.findForward("false");
			}
			if(Globals.XXDM_ZJSYZYXY.equalsIgnoreCase(xxdm)){//�㽭��ҵְҵ
				sql = "insert into jxjhdtj(xn,jxjdm,tjzdm,tj,zdbj,zdmc) values(?,?,?,?,?,?)";
				dao.runUpdate(sql, new String[] { xn,jxj,zdm,ysf,val,zdmc});
			}else{
				String[] tmp = zdm.split("!!");
				
				sql = "insert into jxjhdtj(xn,jxjdm,tjzdm,tjzdlyb,tj) values(?,?,?,?,?)";
				boolean update = dao.runUpdate(sql, new String[] { xn,jxj, tmp != null && tmp.length > 0 ? tmp[0] : "", tmp != null && tmp.length > 1 ?tmp[1] : "null", ysf + "'" + val + "'"});
				if(update){
					request.setAttribute("add", "yes");
				}else{
					request.setAttribute("add", "no");
				}
			}
		} else if ((go != null) && go.equalsIgnoreCase("del")) {
//			String pkVal = request.getParameter("pkVal");
//			sql = "delete from jxjhdtj where replace(replace((tjzdlyb||'.'||tjzdm||zdcz||tj),' ',''),chr(39),'') = ?";
//			dao.runUpdate(sql, new String[] { pkVal.replaceAll(" ", "").replaceAll("'", "") });
		}
		String querry  = " where 1=1 ";
		if(!isNull(jxj)){
			querry += " and jxjdm='"+jxj+"' ";
		}
		if(!isNull(xn)){
			querry +="  and xn='"+xn+"' ";
		}
		String colList[] = null;
		String colListCN [] = null;
		if (!isNull(jxj)) {
//			if (!isNull(xn)){
//			sql = "select jxjdm||tjzdm||tjzdlyb||zdcz pk,rownum,xn,(tjzdlyb||'.'||tjzdm) tjzdm,tj,(select b.jxjmc from jxjdmb b where a.jxjdm=b.jxjdm) jxjmc,tjzdlyb,zdcz from jxjhdtj a where jxjdm=? and xn=?";
//			inputArr = new String[]{jxj,xn};
//			} else{ 
//			sql = "select jxjdm||tjzdm||tjzdlyb||zdcz pk, rownum,xn,(tjzdlyb||'.'||tjzdm) tjzdm,tj,(select b.jxjmc from jxjdmb b where a.jxjdm=b.jxjdm) jxjmc,tjzdlyb,zdcz from jxjhdtj a where jxjdm=? ";
//			inputArr = new String[]{jxj};
//			}
			if(Globals.XXDM_ZJSYZYXY.equalsIgnoreCase(xxdm)){//�㽭��ҵְҵ
				sql = "select "+pk+" pkV, rownum ���,xn,zdmc,tj,(select b.jxjmc from jxjdmb b where a.jxjdm=b.jxjdm) jxjmc,zdbj from jxjhdtj a " ;
				colList =  new String[] {"pkV","���", "xn","jxjmc","zdmc", "tj", "zdbj" };
				colListCN = new String[]{"pkV","���","ѧ��","��ѧ��","����","����","����ֵ"};
			}else{
				sql = "select "+pk+" pkV, rownum ,xn,(case when tjzdm='dcj' then '�³ɼ�' when tjzdm='zcj' then '�ǳɼ�' when tjzdm='tcj' then '��ɼ�' when tjzdm='zpf' then '������' when tjzdm='zdcj' then '��Ϳγ̳ɼ�' else (tjzdlyb||'.'||tjzdm) end) tjzdm,(replace(tj,'>=','���ڵ���')) tj,(select b.jxjmc from jxjdmb b where a.jxjdm=b.jxjdm) jxjmc,tjzdlyb from jxjhdtj a ";
				colListCN = new String[]{"pkV","���","ѧ��","��ѧ��","�����ֶ�","����"};
				colList =  new String[] {"pkV","rownum", "xn","jxjmc","tjzdm", "tj" };
			}	
			List topTr = dao.arrayToList(colList, colListCN);
			List<String[]> rs = new ArrayList<String[]>();
			rs = dao.rsToVator(sql+querry, new String[] {}, colList);
			if (rs != null && rs.size() > 0) {
				for (int i=0;i<rs.size();i++) {
					String[] array = rs.get(i);
					if (array != null && array[5] != null && array[5] != "") {
						rs.get(i)[5] = array[5].replace("'", "");
					}
				}
			}
			request.setAttribute("rs", rs);
			request.setAttribute("topTr",topTr);
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)) {
			//String[] enList = new String[]{"rownum", "xn", "cjbl", "qtbl"};
			//String[] cnList = new String[]{"�к�", "ѧ��", "�ɼ���ռ����", "������ռ����"};
			request.setAttribute("res", dao.rsToVator("select rownum,xn,cjbl,qtbl from zhcpblszb", new String[]{}, new String[]{"rownum", "xn", "cjbl", "qtbl"}));
//			/request.setAttribute("top", dao.arrayToList(enList, cnList));
		}
		request.setAttribute("xnList", dao.getXnndList());
		request.setAttribute("jxjList", jxjList);
		request.setAttribute("zdList", zdList);
		return mapping.findForward("success");
	}

	@SuppressWarnings("unchecked")
	private ActionForward priseCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String xxdm = StandardOperation.getXxdm();
		DAO dao = DAO.getInstance();
		String currXn = "";
		String currNd = "";
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		
		//ѧ���û�û��Ȩ�޵�¼
		String uType = request.getSession().getAttribute("userType").toString();
		if (GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(uType)
				|| GlobalsVariable.XTDM_STU.equalsIgnoreCase(uType)) {
			request.setAttribute("message", "����Ȩ���ʸ�ҳ��!");
			return new ActionForward("/prompt.do",false);
		}
		
		if (Globals.XXDM_WHLGDXHXXY.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/pjpy_hxxy_jxjshwh.do", false);
		} else if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/zjlgPjpy.do?method=jxjshQuery", false);
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)) {
			return new ActionForward("/pjpy_xnmz_jxjsh.do", false);
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SHCBYSGDZKXX)) {
			return new ActionForward("/pjpy_shcbys_jxjsh.do", false);
		} else if (Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/pjpy_nbzy_jxjsh.do", false);
		/*} else if (Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/pjpy_nblg_jxjsh.do", false);*/ //lyl
		} else if (Globals.XXDM_JHZYJSXY.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/jhzy_pjpy_shManage.do", false);
		} else if (Globals.XXDM_XMLGXY.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/pjpy_xmlg_jxjsh.do", false);
		}else if (Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/mjxyJxj.do?method=shJxj", false);
		} else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
			if ("xy".equalsIgnoreCase(userType)) {
				request.setAttribute("errMsg", "����Ȩ���ʸ�ҳ��,�������Ա��ϵ��");
				return new ActionForward("/errMsg.do", false);
			}
		} else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/pjpy_czxx_jxjsh.do", false);
		} else if (Globals.XXDM_CQDZKJ.equals(xxdm)
				 ||Globals.XXDM_CQGYZY.equals(xxdm)
				 ||Globals.XXDM_CSDLZYJSXY.equals(xxdm)) {
			return new ActionForward("/typj.do?method=priseCheck",false);
		}	
		currXn = dao.getConf(2);
		currNd = dao.getConf(3);
//		String jxjXn="";
//		String jxjNd="";
//		String sql_lrh="select JXJSQXN ,JXJSQND from xtszb ";
//		HashMap<String, String> map_lrh = new HashMap<String, String>();
//		map_lrh=dao.getMapNotOut(sql_lrh, new String [] {});
//		jxjXn=map_lrh.get("jxjsqxn").toString();
//		jxjNd=map_lrh.get("jxjsqnd").toString();
//		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZBDX))
//		{
//		currXn=jxjXn;
//		currNd=jxjNd;
//		}
		// ��ʼ��ҳ�棬���ز�ѯ��Ϣ
		CommanForm priseForm = (CommanForm) form;
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		
		String sql = "";// sql���
		String querry = " where 1=1 ";// sql����
		String tips = "";// λ����ʾ��Ϣ
		String tableName = "";// ��ѯ��ϢԴ����Ϊ��ͼ����
		String rsNum = "0";// ���صļ�¼��
		String realTable = "";// ����Դ��
		String pk = "";// ����Դ����������ʽΪ���ֶ���||�ֶ���||�ֶ�������
		String rw = "no";// дȨ��
		String dataType = request.getParameter("act");
		String dispFlag = priseForm.getDispFlag();
		dispFlag = (dispFlag == null) ? "xydm" : dispFlag;
		String xydm = priseForm.getXydm();
		String zydm = priseForm.getZydm();
		String bjdm = priseForm.getBjdm();
		String yesNo = DealString.toGBK(priseForm.getYesNo());
		
		
		String userName = session.getAttribute("userName").toString();
		String isFdy = session.getAttribute("isFdy").toString();//�ж��Ƿ��Ǹ���Ա
		//String xq = dao.getConf(6);
		//if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
		String xq = request.getParameter("xq");

		//}
		String tab = request.getParameter("tab");//��ɳ������άѧ��
		tab = StringUtils.isNull(tab) ? "" : tab;
		//�������
		String[] checkVal = priseForm.getCheckVal();
		String checkall = request.getParameter("checkall");
		String shzt = request.getParameter("shzt");
		String nj = request.getParameter("nj");
		String xh = request.getParameter("xh");
		String xm = request.getParameter("xm");

		if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
			request.setAttribute("showhzy", "showhzy");
			request.setAttribute("chkList", dao.getChkList(3));
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
			request.setAttribute("showjsxx", "showjsxx");
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_XCXY)){
			request.setAttribute("showxcxy", "showxcxy");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {
			request.setAttribute("isAHJG", "yes");
		}//end if
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {
			request.setAttribute("showzjjd", "yes");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
			if (StringUtils.isEqual(isFdy, "true")) {
				request.setAttribute("iscsmzFdy", "yes");
			}
			/*if (!StringUtils.isNull(tab) && StringUtils.isEqual(tab, "qtjxj")){
				if (StringUtils.isEqual(isFdy, "true")) {
					request.setAttribute("iscsmzFdy", "yes");
				}
			}//������ѧ��
			else {
				if (StringUtils.isEqual(isFdy, "true")){
					return new ActionForward("/jxjshdefault.do",false);
				} else {
					return new ActionForward("/jxjshdefault.do",false);
				}
			}//��άѧ��*/		
		}
		/*if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			request.setAttribute("shownblg", "yes");
		}*/  //lyl 2011-05-25
		if (userType.equalsIgnoreCase("xy") && !UserTypePd.isFdy(isFdy)) {
			xydm = userDep;
		}
		String bmdm = xydm;
		if (userType.equalsIgnoreCase("xy") && !UserTypePd.isFdy(isFdy)) {
			bmdm = userDep;
		}

		//��ѯ��ѧ����������
		/*if (!StringUtils.isNull(dispFlag) && StringUtils.isEqual(dispFlag, "bjdm")) {
			bmdm = request.getParameter("bjdm");
		}
		if (!StringUtils.isNull(dispFlag) && StringUtils.isEqual(dispFlag, "zydm")) {
			bmdm = request.getParameter("zydm");
		}*/
		priseForm.setXydm(xydm);
		//����û��Ǹ���Ա
		if (StringUtils.isEqual(isFdy, "true")) {
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)) {//����Ƽ�ѧԺ
				request.setAttribute("cqkjisFdy", "yes");
			} if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {//���ս�����ҵѧԺ
				request.setAttribute("isahjgFdy", "yes");
			}
		}
		if (bmdm != null) {
			sql = "select a.jxjdm ��ѧ�����,a.jxjmc ��ѧ������,nvl(b.ͨ��,'0') ͨ��,nvl(b.��ͨ��,'0') ��ͨ��,nvl(b.δ���,'0') δ���,c.���� from jxjdmb a,"
				+ "(select jxjdm,max(decode(xxsh,'ͨ��',num,0)) ͨ��,max(decode(xxsh,'��ͨ��',num,0)) ��ͨ��,max(decode(xxsh,'δ���',num,0)) δ��� from "
				+ "(select jxjdm,xxsh,count(*) num from view_xsjxjb where xysh='ͨ��' and xydm=? group by jxjdm,xxsh"
				+ ") group by jxjdm) b,( select bmdm,jxjdm,sum(jxjrs) ���� from xyjxjrs where xn=? and nd=? and bmdm=? group by bmdm,jxjdm"
				+ ") c where a.jxjdm=b.jxjdm(+) and a.jxjlb='У' and a.jxjdm=c.jxjdm(+) order by a.jxjdm";
			String[] jxjTmpTopTr = new String[] { "��ѧ������", "ͨ��", "��ͨ��", "δ���", "����" };
			ArrayList jxjtmp = dao.rsToVator(sql, new String[] { bmdm, currXn,
					currNd, bmdm }, jxjTmpTopTr);
			request.setAttribute("jxjtmp", jxjtmp);
			request.setAttribute("xydm", bmdm);
		}

		String jxjdm = priseForm.getXmdm();

		String jxjfl = "";
	/*	if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			//��ȡ��ѧ������
			String[] jxjmc = dao.getOneRs("select jxjmc,jxjfl from jxjdmb where jxjdm = ?", new String[]{jxjdm}, new String[]{"jxjmc", "jxjfl"});
			if (jxjmc != null && jxjmc.length == 2) {
				request.setAttribute("jmc", jxjmc[0]);
				jxjfl = jxjmc[1];
			}
		}*/ //lyl 2011-05-25
		if (dataType == null) {
			dataType = "";
		}
		querry += "and xn = '" + currXn + "' ";

		if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
			querry += "and nd IS NULL ";	//�Ϻ����̼�����ѧ	Ҫ���ε�����ȵĲ�ѯ
		} else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
			querry += "and 1=1 ";	//���ݴ�ѧ	Ҫ���ε�����ȵĲ�ѯ
		} else {
			querry += "and nd = '" + currNd + "' ";
		}

		if (isNull(jxjdm)) {
			querry += "and 1=1 ";
		} else {
			querry += " and jxjdm = '" + jxjdm + "' ";
		}
		if (!isNull(bjdm)) {
			querry += " and bjdm = '" + bjdm + "' ";
		} else if (!isNull(zydm)) {
			querry += " and zydm = '" + zydm + "' ";
		} else if (!isNull(xydm)) {
			querry += " and xydm = '" + xydm + "' ";
		} else {
			querry += " and 1=1 ";
		}
		if (!StringUtils.isNull(nj)) {
			querry += " and nj = '" + nj + "' ";
		}
		if (!StringUtils.isNull(xq)) {
			querry += " and xq = '" + xq + "' ";
		}
		if (!StringUtils.isNull(xh)) {
			querry += " and xh='";
			querry += xh;
			querry += "'";
		}
		if (!StringUtils.isNull(xm)) {
			querry += " and xm='";
			querry += xm;
			querry += "'";
		}
		String zt = priseForm.getZt();

		realTable = "xsjxjb";
		pk = "xn||nd||xh||jxjdm";
		tips = "�������� - ��� - ��ѧ�����";
		tableName = "view_xsjxjb";

		if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
			String jxjlbs = request.getParameter("jxjlb");
			if (!StringUtils.isNull(jxjlbs) && StringUtils.isEqual(jxjlbs, "1")) {
				querry += " and jxjxydm='";
				querry += dao.getOneRs("select xgbdm from xtszb", new String[]{}, "xgbdm");
				querry+="' ";
			} else if (!StringUtils.isNull(jxjlbs) && StringUtils.isEqual(jxjlbs, "2")) {
				querry += " and jxjxydm='";
				querry += userDep;
				querry += "' ";
			} else {
				querry += " and 1=1 ";
			}
		}
		if(Globals.XXDM_LSXY.equalsIgnoreCase(xxdm)){
			//��ˮѧԺ
			pk = "xh||jxjdm||xn||xq";
		}

		if (userType.equalsIgnoreCase("xx")) {//ѧУ��ѯ
			colList = new String[] { "color", "bgcolor", "����", "�к�", "xh",
					"nd", "xn", "xm", "bjmc", "jxjmc", "xxsh" };
			if (!StringUtils.isNull(zt)) {
				querry += " and xxsh='" + DealString.toGBK(zt) + "' ";
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
				if(!isNull(yesNo)){
					querry += "and xxsh = '" + yesNo + "' ";
				}else{
					querry += "and 1=1";
				}
				colList = new String[] { "color", "bgcolor", "����", "�к�", "xn",
						"nd", "xh", "xm", "bjmc", "jxjmc", "xxsh"};
			}
			
			if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)
					|| Globals.XXDM_SCJZZYJSXY.equalsIgnoreCase(xxdm)
					|| Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)
					|| Globals.XXDM_CDFZGDZKXX.equalsIgnoreCase(xxdm)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)) {
				querry += " and fdysh='ͨ��' ";
			}
			
			sql = "select rownum �к�,c.* from (select (case when nvl(b.tjflag,'��')='��' then 'blue' else 'red' end) color, "
				+ " (case nvl(b.xxsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
				+ "b.* from (select "
				+ pk
				+ " ����,a.* from "
				+ tableName
				+ " a"
				+ querry
				+ " and xysh='ͨ��') b order by bgcolor desc) c ";
			
			if(xxdm.equalsIgnoreCase(Globals.XXDM_NBTYZYJSXY)){
				colList = new String[] { "color", "bgcolor", "����", "�к�", "xh",
						"nd", "xn", "xm", "bjmc", "jxjmc","zhpfmc", "xxsh" };
				sql = "select rownum �к�,c.* from (select (case when nvl(b.tjflag,'��')='��' then 'blue' else 'red' end) color, "
					+ " (case nvl(b.xxsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ "b.*,(select mc from (select xh,xn,(dense_rank() over(partition by zydm,xn order by to_number(zpf) desc nulls last)) " +
					"mc from view_zhszcp ) where xh=b.xh and xn=b.xn)  zhpfmc from (select "
					+ pk
					+ " ����,a.xh,a.nd,a.xn,a.xm,a.bjmc,a.jxjmc,xxsh,zydm,tjflag,(select zpf from zhszcp d where a.xh=d.xh and a.xn=d.xn and a.nd=d.nd) zpf from "
					+ tableName
					+ " a"
					+ querry
					+ " and xysh='ͨ��') b order by bgcolor desc) c ";
			}
			//������
			/*if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {

				colList = new String[] { "color", "bgcolor", "����", "�к�", "xn",
						"nd", "xh", "xm", "bjmc", "jxjmc","jqfpm", "zhszcpzfpm","cet4","xxsh" };
				sql = "select rownum �к�,c.* from (select (case when nvl(b.tjflag,'��')='��' then 'blue' else 'red' end) color, "
					+ " (case nvl(b.xxsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ "b.* from (select "
					+ pk
					+ " ����,a.xh,a.xn,a.nd,a.xm,a.bjmc,a.bjdm,a.zydm,a.jxjmc,a.jqf,a.zhszcpzf,a.xxsh,a.xysh,a.tjflag,a.zymc," +
					"(select b.jqfpm from view_zhszcp b where a.xh=b.xh and a.xn=b.xn and a.nd=b.nd) jqfpm," +
					"(select b.zhszcpzfpm from view_zhszcp b where a.xh=b.xh and a.xn=b.xn and a.nd=b.nd) zhszcpzfpm," +
					"(case when a.zymc like '%Ӣ��%' then (select cet4 from (select b.xh, b.djksmc, max(b.cj) cet4 from xsdjksb b where a.xh = b.xh and b.djksmc like (select c.djksmc from djksdmb c where dymc = 'PCET4') group by xh, djksmc)) else (select cet4 from (select b.xh, b.djksmc, max(b.cj) cet4 from xsdjksb b where a.xh = b.xh and b.djksmc like (select c.djksmc from djksdmb c where dymc = 'CET4') group by xh, djksmc)) end) cet4 from "
					+ tableName
					+ " a"
					+ querry
					+ " and xysh='ͨ��') b order by bgcolor desc,tjflag desc) c ";
			}*/ //lyl 2011-05025
			//�㽭����
			else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {
				colList = new String[] { "color", "bgcolor", "����", "�к�", "xn",
						"nd", "xh", "xm", "bjmc", "jxjmc","zhszcppm", "xxsh" };
				sql = "select rownum �к�,c.* from (select (case when nvl(b.tjflag,'��')='��' then 'blue' else 'red' end) color, "
					+ " (case nvl(b.xxsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ "b.* from (select "
					+ pk
					+ " ����,a.*,(select zhszcppm from (select xh,xn,xq,nd,zhszcpzf,(dense_rank() over (partition by bjdm,xn,xq,nd order by zhszcpzf desc nulls last)) zhszcppm  from view_zjjd_zhszcp) b where b.xh=a.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd) zhszcppm from "
					+ tableName
					+ " a"
					+ querry
					+ " and xysh='ͨ��') b order by bgcolor desc) c ";
			}
			//��ְԺ
			else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				colList = new String[] { "color", "bgcolor", "����", "�к�", "xn",
						"nd", "xh", "xm", "bjmc", "jxjmc","zhpfmc","cjmc","fdyqm","xyqm","xysh", "xxsh" };
				sql = "select rownum �к�,c.color,c.bgcolor,c.����,c.xn,c.fdyqm,c.xyqm,c.cjmc,c.xydm,c.zydm,c.jxjdm,c.bjdm,c.zhpfmc,c.nd,c.tjflag,c.xh,c.xysh,c.xm,c.bjmc,c.jxjmc,c.zhszcpzf,c.xxsh from (select (case when nvl(b.tjflag,'��')='��' then 'blue' else 'red' end) color, "
					+ " (case nvl(b.xxsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ "b.����,b.xn,b.jxjdm,b.xydm,b.zydm,b.bjdm,b.fdyqm,b.xyqm,b.cjmc,b.zhpfmc,b.tjflag,b.nd,b.xysh,b.xh,b.xm,b.bjmc,b.jxjmc,b.zhszcpzf,b.xxsh from (select "
					+ pk
					+ " ����,a.xh,a.jxjdm,a.fdyqm,a.xyqm,a.xydm,a.zydm,a.bjdm,a.cjmc,a.zhpfmc,a.xn,a.nd,a.xq,a.bjmc,a.xm,a.xysh,a.jxjmc,a.xxsh,a.tjflag,(select zhszcpzf from view_zhszcp b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd) zhszcpzf from "
					+ tableName
					+ " a"
					+ querry
					+ " ) b order by bgcolor desc) c ";
			}
			else if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {//��������
				sql = "select rownum �к�,c.* from (select (case when nvl(b.tjflag,'��')='��' then 'blue' else 'red' end) color, "
					+ " (case nvl(b.xxsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ "b.* from (select "
					+ pk
					+ " ����,a.* from "
					+ tableName
					+ " a"
					+ querry
					+ " and xysh='ͨ��') b order by bgcolor desc) c ";
				colList = new String[] { "color", "bgcolor", "����", "�к�", "xn",
						"xm", "bjmc", "jxjmc","sxzzddszf", "kxwhszf", "sxlxszf", "sjlxcxf","zhszcpzf", "xxsh"};
			}
			//��ɳ����
			else if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				colList = new String[] { "color", "bgcolor", "����", "�к�",
						"nd", "xm", "bjmc", "jxjmc","jxjlb", "fdysh", "xysh", "xxsh" };
			}
			//�㽭��ý
			else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
				colList = new String[] { "color", "bgcolor", "����", "�к�", "xh",
						"nd", "xn", "xm", "bjmc", "jxjmc","cj", "bjpm",  "xxsh" };
				sql = "select rownum �к�,c.* from (select (case when nvl(b.tjflag,'��')='��' then 'blue' else 'red' end) color, "
					+ " (case nvl(b.xxsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ "b.* from (select "
					+ pk
					+ " ����,a.*,(select b.cj from view_zjcm_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) cj," +
					"(select b.bjpm from view_zjcm_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) bjpm from "
					+ tableName
					+ " a"
					+ querry
					+ " and xysh='ͨ��') b order by bgcolor desc) c ";
			}
			else if (Globals.XXDM_SCJZZYJSXY.equalsIgnoreCase(xxdm)) {
				colList = new String[] { "color", "bgcolor", "����", "�к�", "xh",
						"xm", "bjmc", "jxjmc","jlje", "mincj", "avgcj", "bjpm", "zypm", "cxcj", "kh","xxsh" };
				sql = "select rownum �к�,c.*,d.mincj,d.avgcj,d.bjpm,d.zypm from (select (case when nvl(b.tjflag,'��')='��' then 'blue' else 'red' end) color, "
					+ " (case nvl(b.xxsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ "b.* from (select "
					+ pk
					+ " ����,a.xh,a.cxcj,a.kh,a.jlje,a.xn,a.xq,a.nd,a.xm,a.bjmc,a.bjdm,a.zydm,a.jxjmc,a.jqf,a.zhszcpzf,a.xxsh,a.xysh,a.tjflag,a.zymc," +
					"(select b.jqfpm from view_zhszcp b where a.xh=b.xh and a.xn=b.xn and a.nd=b.nd) jqfpm," +
					"(select b.zhszcpzfpm from view_zhszcp b where a.xh=b.xh and a.xn=b.xn and a.nd=b.nd) zhszcpzfpm," +
					"(case when a.zymc like '%Ӣ��%' then (select cet4 from (select b.xh, b.djksmc, max(b.cj) cet4 from xsdjksb b where a.xh = b.xh and b.djksmc like (select c.djksmc from djksdmb c where dymc = 'PCET4') group by xh, djksmc)) else (select cet4 from (select b.xh, b.djksmc, max(b.cj) cet4 from xsdjksb b where a.xh = b.xh and b.djksmc like (select c.djksmc from djksdmb c where dymc = 'CET4') group by xh, djksmc)) end) cet4 from "
					+ tableName
					+ " a"
					+ querry
					+ " and xysh='ͨ��') b order by bgcolor desc,tjflag desc) c left join view_xscjpmb d on c.xh=d.xh and c.xn=d.xn and c.xq=d.xq ";
			}
			else if (Globals.XXDM_XCXY.equalsIgnoreCase(xxdm)) {
				colList = new String[] { "color", "bgcolor", "����", "�к�", "xh", "xm", "bjmc", "xn",
						"nd", "jxjmc","pjcj","cxcj","zcj","pm", "xxsh" };
				sql = "select rownum �к�,c.* from (select (case when nvl(b.tjflag,'��')='��' then 'blue' else 'red' end) color, "
					+ " (case nvl(b.xxsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ "b.* from (select "
					+ pk
					+ " ����,a.xh,a.xm,a.bjmc,a.xn,a.xq,a.xxsh,a.jxjmc,a.tjflag,a.nd,(select b.pjcj from view_xcxy_pjxscjb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) pjcj,(select b.cxcj from view_xcxy_pjxscjb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) cxcj,(select b.zcj from view_xcxy_pjxscjb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) zcj,(select b.pm from view_xcxy_pjxscjb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) pm from "
					+ tableName
					+ " a "
					+ querry
					+ " and xysh='ͨ��') b order by bgcolor desc) c ";
			} 
			else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {//���ݴ�ѧ
				
				colList = new String[] { "pk", "bgcolor", "r", "xh",
						 "xm", "bjmc", "xn", "xycpf", "zhbxf", "xwbxf", "tcbxf", "zf", "bjpm","cjgk", "cfgk", "jxjmc", "xxsh" };
				sql = "select a.*, rownum r from (select * from (select a.*,b.bjpm from (select a.*,(case when gk >0 then '#CCCCCC' when cf >0 then '#DDDDDD' else '' end) bgcolor," +
						"(case when gk > 0 then '��' else '��' end) cjgk," +
						"(case when cf > 0 then '��' else '��' end) cfgk from (select xh||xn||jxjdm pk,xh,(select xm from view_xsjbxx b where a.xh=b.xh) xm,xn,(select nj from view_xsjbxx b where a.xh=b.xh) nj,(select xydm from view_xsjbxx b where a.xh=b.xh) xydm,(select zydm from view_xsjbxx b where a.xh=b.xh) zydm,(select bjdm from view_xsjbxx b where a.xh=b.xh) bjdm,(select bjmc from view_xsjbxx b where a.xh=b.xh) bjmc,jxjdm," +
						"(select (nvl(case when instr(to_char(b.xycpf),'.',1,1)=1 then '0'||to_char(b.xycpf) when instr(to_char(b.xycpf),'-',1,1)=1 and instr(to_char(b.xycpf),'.',2,1)=2 then replace(b.xycpf,'-','-0') else to_char(b.xycpf) end, '0')) xycpf from gzdx_zhszcpb b where a.xh=b.xh and a.xn=b.xn) xycpf," +
						"(select (nvl(case when instr(to_char(b.zhbxf),'.',1,1)=1 then '0'||to_char(b.zhbxf) when instr(to_char(b.zhbxf),'-',1,1)=1 and instr(to_char(b.zhbxf),'.',2,1)=2 then replace(b.zhbxf,'-','-0') else to_char(b.zhbxf) end, '0')) zhbxf from gzdx_zhszcpb b where a.xh=b.xh and a.xn=b.xn) zhbxf," +
						"(select (nvl(case when instr(to_char(b.xwbxf),'.',1,1)=1 then '0'||to_char(b.xwbxf) when instr(to_char(b.xwbxf),'-',1,1)=1 and instr(to_char(b.xwbxf),'.',2,1)=2 then replace(b.xwbxf,'-','-0') else to_char(b.xwbxf) end, '0')) xwbxf from gzdx_zhszcpb b where a.xh=b.xh and a.xn=b.xn) xwbxf," +
						"(select (nvl(case when instr(to_char(b.tcbxf),'.',1,1)=1 then '0'||to_char(b.tcbxf) when instr(to_char(b.tcbxf),'-',1,1)=1 and instr(to_char(b.tcbxf),'.',2,1)=2 then replace(b.tcbxf,'-','-0') else to_char(b.tcbxf) end, '0')) tcbxf from gzdx_zhszcpb b where a.xh=b.xh and a.xn=b.xn) tcbxf," +
						"(select (nvl(case when instr(to_char(b.zf),'.',1,1)=1 then '0'||to_char(b.zf) when instr(to_char(b.zf),'-',1,1)=1 and instr(to_char(b.zf),'.',2,1)=2 then replace(b.zf,'-','-0') else to_char(b.zf) end, '0')) zf from gzdx_zhszcpb b where a.xh=b.xh and a.xn=b.xn) zf," +
						"(select jxjmc from jxjdmb b where a.jxjdm=b.jxjdm) jxjmc,xxsh," +
						"(select count(b.xh) gk from cjb b where a.xh=b.xh and a.xn=b.xn and (b.cj < 60 or b.bkcj is not null or b.cxcj is not null)) gk," +
						"(select count(b.xh) cf from wjcfb b where a.xh=b.xh and a.xn=b.xn and b.cfwh is not null) cf from xsjxjb a) a) a left join (select a.xh,a.xn,(rank() over (partition by xn,bjdm order by zf desc nulls last)) bjpm,b.bjdm from gzdx_zhszcpb a,view_xsjbxx b where a.xh=b.xh) b on a.xh=b.xh and a.xn=b.xn) a order by gk,cf,bjpm) a"
						+ querry;
				
			}
		} else {//ѧԺ��ѯ
			colList = new String[] { "color", "bgcolor", "����", "�к�", "xh",
					"nd", "xn", "xm", "bjmc", "jxjmc", "xysh" };
			if (!StringUtils.isNull(zt)) {
				querry += " and xysh='" + DealString.toGBK(zt) + "' ";
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
				if(!isNull(yesNo)){
					querry += "and xysh = '" + yesNo + "' ";
				}else{
					querry += "and 1=1";
				}
				colList = new String[] { "color", "bgcolor", "����", "�к�", "xn",
						"nd", "xh", "xm", "bjmc", "jxjmc", "xysh"};
			}

			if (!UserTypePd.isFdy(isFdy)) {
				priseForm.setXydm(userDep);
			}
			

			//ѧԺ�û�:����Ƽ�ѧԺ�����ս�����ҵѧԺ
			if (StringUtils.isEqual(isFdy, "false")){
				if (xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)) {
					querry += " and fdysh='ͨ��' ";
				}//��ɳ�����������
				if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
					querry += " and fdysh='ͨ��' ";
				}
				if (xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)) {
					//�人����ѧ
					querry += " and fdysh='ͨ��' ";
				}
				if (Globals.XXDM_CDFZGDZKXX.equalsIgnoreCase(xxdm)) {
					querry += " and fdysh='ͨ��' ";
				}
				if (Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
					querry += " and fdysh='ͨ��' ";
				}
				//�㽭��ýѧԺ
				if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)
						|| Globals.XXDM_SCJZZYJSXY.equalsIgnoreCase(xxdm)
						|| Globals.XXDM_NBTYZYJSXY.equalsIgnoreCase(xxdm)) {
					querry += " and fdysh='ͨ��' ";
				}
			}

			sql = "select rownum �к�,c.* from (select (case when nvl(b.tjflag,'��')='��' then 'blue' else 'red' end) color, "
				+ " (case nvl(b.xysh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
				+ "b.* from (select "
				+ pk
				+ " ����,a.* from "
				+ tableName
				+ " a"
				+ querry
				+ " and xydm='"
				+ userDep
				+ "') b order by bgcolor desc) c ";
			if(xxdm.equalsIgnoreCase(Globals.XXDM_NBTYZYJSXY)){
				colList = new String[] { "color", "bgcolor", "����", "�к�", "xh",
						"nd", "xn", "xm", "bjmc", "jxjmc","zhpfmc", "xysh" };
				sql = "select rownum �к�,c.* from (select (case when nvl(b.tjflag,'��')='��' then 'blue' else 'red' end) color, "
					+ " (case nvl(b.xysh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ "b.*,(select mc from (select xh,xn,(dense_rank() over(partition by zydm,xn order by to_number(zpf) desc nulls last)) " +
					"mc from view_zhszcp ) where xh=b.xh and xn=b.xn)  zhpfmc from (select "
					+ pk
					+ " ����,a.xh,a.nd,a.xn,a.xm,a.bjmc,a.jxjmc,xysh,zydm,xydm,bjdm,nj,fdysh,jxjdm,tjflag,(select zpf from zhszcp d where a.xh=d.xh and a.xn=d.xn and a.nd=d.nd) zpf from "
					+ tableName
					+ " a"
					+ querry
					+ " and xydm='"
					+ userDep
					+ "') b order by bgcolor desc) c ";
			}
			//������
		/*	if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				colList = new String[] { "color", "bgcolor", "����", "�к�", "xn",
						"nd", "xh", "xm", "bjmc", "jxjmc","jqfpm","zhszcpzfpm","cet4", "xysh" };
				sql = "select rownum �к�,c.* from (select (case when nvl(b.tjflag,'��')='��' then 'blue' else 'red' end) color, "
					+ " (case nvl(b.xysh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ "b.* from (select "
					+ pk
					+ " ����,a.xh,a.xn,a.nd,a.xm,a.bjmc,a.bjdm,a.zydm,a.jxjmc,a.jqf,a.zhszcpzf,a.xysh,a.tjflag," +
					"(select b.jqfpm from view_zhszcp b where a.xh=b.xh and a.xn=b.xn and a.nd=b.nd) jqfpm," +
					"(select b.zhszcpzfpm from view_zhszcp b where a.xh=b.xh and a.xn=b.xn and a.nd=b.nd) zhszcpzfpm," +
					"(case when a.zymc like '%Ӣ��%' then (select cet4 from (select b.xh, b.djksmc, max(b.cj) cet4 from xsdjksb b where a.xh = b.xh and b.djksmc like (select c.djksmc from djksdmb c where dymc = 'PCET4') group by xh, djksmc)) else (select cet4 from (select b.xh, b.djksmc, max(b.cj) cet4 from xsdjksb b where a.xh = b.xh and b.djksmc like (select c.djksmc from djksdmb c where dymc = 'CET4') group by xh, djksmc)) end) cet4 from "
					+ tableName
					+ " a"
					+ querry
					+ " and xydm='"
					+ userDep
					+ "') b order by bgcolor desc) c ";
			}*/  //lyl
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {
				colList = new String[] { "color", "bgcolor", "����", "�к�", "xn",
						"nd", "xh", "xm", "bjmc", "jxjmc","zhszcppm", "xysh"};
				sql = "select rownum �к�,c.* from (select (case when nvl(b.tjflag,'��')='��' then 'blue' else 'red' end) color, "
					+ " (case nvl(b.xysh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ "b.* from (select "
					+ pk
					+ " ����,a.*,(select zhszcppm from (select xh,xn,xq,nd,zhszcpzf,(dense_rank() over (partition by bjdm,xn,xq,nd order by zhszcpzf desc nulls last)) zhszcppm  from view_zjjd_zhszcp) b where b.xh=a.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd) zhszcppm from "
					+ tableName
					+ " a"
					+ querry
					+ " and xydm='"
					+ userDep
					+ "') b order by bgcolor desc) c ";
			}
			//��ְԺ
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				colList = new String[] { "color", "bgcolor", "����", "�к�", "xn",
						"nd", "xh", "xm", "bjmc", "jxjmc","zhpfmc","cjmc","fdyqm","xyqm", "xysh" };
				sql = "select rownum �к�,c.color,c.bgcolor,c.����,c.xn,c.xydm,c.zydm,c.bjdm,c.fdyqm,c.xyqm,c.jxjdm,c.cjmc,c.zhpfmc,c.nd,c.tjflag,c.xh,c.xm,c.bjmc,c.jxjmc,c.zhszcpzf,c.xysh from (select (case when nvl(b.tjflag,'��')='��' then 'blue' else 'red' end) color, "
					+ " (case nvl(b.xysh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ "b.����,b.xn,b.xydm,b.zydm,b.jxjdm,b.bjdm,b.tjflag,b.fdyqm,b.xyqm,b.cjmc,b.zhpfmc,b.nd,b.xh,b.xm,b.bjmc,b.jxjmc,b.zhszcpzf,b.xysh from (select "
					+ pk
					+ " ����,a.xh,a.xn,a.jxjdm,a.nd,a.xydm,a.zydm,a.bjdm,a.fdyqm,a.xyqm,a.cjmc,a.zhpfmc,a.xq,a.bjmc,a.xm,a.jxjmc,a.xysh,a.tjflag,(select zhszcpzf from view_zhszcp b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd) zhszcpzf from "
					+ tableName
					+ " a"
					+ querry
					+ " and xydm='"
					+ userDep
					+ "') b order by bgcolor desc) c ";
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				sql = "select rownum �к�,c.*,(case when c.xxsh='ͨ��' then 'disabled' else '' end) flag from (select (case when nvl(b.tjflag,'��')='��' then 'blue' else 'red' end) color, "
					+ " (case nvl(b.xysh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ "b.* from (select "
					+ pk
					+ " ����,a.* from "
					+ tableName
					+ " a"
					+ querry
					+ " and xydm='"
					+ userDep
					+ "') b order by bgcolor desc) c ";
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				colList = new String[] { "flag","color", "bgcolor", "����", "�к�",
						"nd", "xm", "bjmc", "jxjmc","jxjlb","fdysh", "xysh","xxsh" };
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {//��������
				colList = new String[] { "color", "bgcolor", "����", "�к�", "xn",
						"xm", "bjmc", "jxjmc","sxzzddszf", "kxwhszf", "sxlxszf", "sjlxcxf","zhszcpzf", "xysh" };
				sql = "select rownum �к�,c.* from (select (case when nvl(b.tjflag,'��')='��' then 'blue' else 'red' end) color, "
					+ " (case nvl(b.xysh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ "b.* from (select "
					+ pk
					+ " ����,a.* from "
					+ tableName
					+ " a"
					+ querry
					+ " and xydm='"
					+ userDep
					+ "') b order by bgcolor desc) c ";
			}
			if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
				sql = "select rownum �к�,c.* from (select (case when nvl(b.tjflag,'��')='��' then 'blue' else 'red' end) color, "
					+ " (case nvl(b.xysh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ "b.* from (select "
					+ pk
					+ " ����,a.*,(select b.cj from view_zjcm_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) " +
					"cj,(select b.bjpm from view_zjcm_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) bjpm from "
					+ tableName
					+ " a"
					+ querry
					+ " and xydm='"
					+ userDep
					+ "') b order by bgcolor desc) c ";
				colList = new String[] { "color", "bgcolor", "����", "�к�", "xh",
						"nd", "xn", "xm", "bjmc", "jxjmc","cj","bjpm", "xysh" };
			}
			if (Globals.XXDM_SCJZZYJSXY.equalsIgnoreCase(xxdm)) {
				colList = new String[] { "color", "bgcolor", "����", "�к�", "xh",
						"xm", "bjmc", "jxjmc","jlje", "mincj", "avgcj", "bjpm", "zypm", "cxcj", "kh","xxsh" };
				sql = "select rownum �к�,c.*,d.mincj,d.avgcj,d.bjpm,d.zypm from (select (case when nvl(b.tjflag,'��')='��' then 'blue' else 'red' end) color, "
					+ " (case nvl(b.xysh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ "b.* from (select "
					+ pk
					+ " ����,a.* from "
					+ tableName
					+ " a"
					+ querry
					+ " and xydm='"
					+ userDep
					+ "') b order by bgcolor desc) c left join view_xscjpmb d on c.xn=d.xn and c.xh=d.xh and c.xq=d.xq";
			}
			if (Globals.XXDM_XCXY.equalsIgnoreCase(xxdm)) {
				sql = "select rownum �к�,c.* from (select (case when nvl(b.tjflag,'��')='��' then 'blue' else 'red' end) color, "
					+ " (case nvl(b.xysh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ "b.*,(select e.pjcj from view_xcxy_pjxscjb e where b.xh=e.xh and e.xn=b.xn and e.xq=b.xq) pjcj,(select e.cxcj from view_xcxy_pjxscjb e where e.xh=b.xh and e.xn=b.xn and e.xq=b.xq) cxcj,(select e.zcj from view_xcxy_pjxscjb e where e.xh=b.xh and e.xn=b.xn and e.xq=b.xq) zcj,(select e.pm from view_xcxy_pjxscjb e where e.xh=b.xh and e.xn=b.xn and e.xq=b.xq) pm from (select "
					+ pk
					+ " ����,a.xh,a.xm,a.bjmc,a.xn,a.xq,a.xysh,a.jxjmc,a.tjflag,a.nd, a.xydm,a.jxjdm,a.bjdm,a.zydm from "
					+ tableName
					+ " a"
					+ querry
					+ " and xydm='"
					+ userDep
					+ "') b order by bgcolor desc) c ";
				colList = new String[] { "color", "bgcolor", "����", "�к�", "xh", "xm", "bjmc", "xn",
						"nd", "jxjmc","pjcj", "cxcj", "zcj", "pm", "xysh"};
			}
		}

		//����Ϊ�������ѧУ����Ա��
		if (StringUtils.isEqual(isFdy, "true")) {//����û��Ǹ���Ա
			//����Ƽ�ѧԺ
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)) {
				colList = new String[] {"color", "bgcolor", "����", "�к�", "xn",
						"nd", "xh", "xm", "bjmc", "jxjmc", "fdysh", "tjflag"};

				sql = "select rownum �к�,c.* from (select (case when nvl(b.tjflag,'��')='��' then 'blue' else 'red' end) color, "
					+ " (case nvl(b.fdysh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ "b.* from (select "
					+ pk
					+ " ����,a.* from "
					+ tableName
					+ " a"
					+ querry
					+ ") b order by bgcolor desc) c ";
			}//end if
			//��ɳ�����������
			else if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				if (!StringUtils.isNull(zt)) {
					querry += " and fdysh='" + DealString.toGBK(zt) + "' ";
				}
				querry += " and exists(select 1 from view_fdybbj d where a.bjdm=d.bjdm and d.zgh='"+userName+"') ";
				colList = new String[] {"flag","color", "bgcolor", "����", "�к�",
						"nd", "xm", "bjmc", "jxjmc", "jxjlb","fdysh","xysh","xxsh"};

				sql = "select rownum �к�,c.*,(case when c.xysh='ͨ��' then 'disabled' when c.xxsh='ͨ��' then 'disabled' else '' end) flag from (select (case when nvl(b.tjflag,'��')='��' then 'blue' else 'red' end) color, "
					+ " (case nvl(b.fdysh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ "b.* from (select "
					+ pk
					+ " ����,a.* from "
					+ tableName
					+ " a"
					+ querry
					+ ") b order by bgcolor desc) c ";
			}
			//�人����ѧ
			else if (xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)) {
				colList = new String[] {"color", "bgcolor", "����", "�к�", "xn",
						"nd", "xh", "xm", "bjmc", "jxjmc", "fdysh"};
				querry += " and exists (select 1 from view_fdybbj d where a.bjdm=d.bjdm and d.zgh='"+userName+"')";
				sql = "select rownum �к�,c.* from (select (case when nvl(b.tjflag,'��')='��' then 'blue' else 'red' end) color, "
					+ " (case nvl(b.fdysh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ "b.* from (select "
					+ pk
					+ " ����,a.* from "
					+ tableName
					+ " a"
					+ querry
					+ ") b order by bgcolor desc) c ";
			}
			//�ɼ���֯�ߵ�ר��ѧУ
			else if (Globals.XXDM_CDFZGDZKXX.equalsIgnoreCase(xxdm)) {
				querry += " and exists(select 1 from view_fdybbj d where a.bjdm=d.bjdm and d.zgh='"+userName+"') ";
				if (!StringUtils.isNull(zt)) {
					querry += " and fdysh='" + DealString.toGBK(zt) + "' ";
				}
				sql = "select rownum �к�,c.* from (select (case when nvl(b.tjflag,'��')='��' then 'blue' else 'red' end) color, "
					+ " (case nvl(b.fdysh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ "b.* from (select "
					+ pk
					+ " ����,a.* from "
					+ tableName
					+ " a"
					+ querry
					+ " and xydm='"
					+ userDep
					+ "') b order by bgcolor desc) c ";
				colList = new String[] { "color", "bgcolor", "����", "�к�", "xh",
						"nd", "xn", "xm", "bjmc", "jxjmc", "fdysh" };
			}
			//���ս�����ҵѧԺ
			else if (Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
				colList = new String[] { "color", "bgcolor", "����", "�к�", "xh",
						"nd", "xn", "xm", "bjmc", "jxjmc", "fdysh" };
				querry += " and exists(select 1 from view_fdybbj d where a.bjdm=d.bjdm and d.zgh='"+userName+"') ";
				if (!StringUtils.isNull(zt)) {
					querry += " and fdysh='" + DealString.toGBK(zt) + "' ";
				}
				sql = "select rownum �к�,c.* from (select (case when nvl(b.tjflag,'��')='��' then 'blue' else 'red' end) color, "
					+ " (case nvl(b.fdysh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ "b.* from (select "
					+ pk
					+ " ����,a.* from "
					+ tableName
					+ " a"
					+ querry
					+ " and xydm='"
					+ userDep
					+ "') b order by bgcolor desc) c ";

			}
			//�㽭��ýѧԺ
			else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
				querry += " and exists(select 1 from view_fdybbj d where a.bjdm=d.bjdm and d.zgh='"+userName+"') ";
				if (!StringUtils.isNull(zt)) {
					querry += " and fdysh='" + DealString.toGBK(zt) + "' ";
				}
				sql = "select rownum �к�,c.* from (select (case when nvl(b.tjflag,'��')='��' then 'blue' else 'red' end) color, "
					+ " (case nvl(b.fdysh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ "b.* from (select "
					+ pk
					+ " ����,a.*,(select b.cj from view_zjcm_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) cj" +
					",(select b.bjpm from view_zjcm_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) bjpm from "
					+ tableName
					+ " a"
					+ querry
					+ " and xydm='"
					+ userDep
					+ "') b order by bgcolor desc) c ";
				colList = new String[] { "color", "bgcolor", "����", "�к�", "xh",
						"nd", "xn", "xm", "bjmc", "jxjmc", "cj", "bjpm", "fdysh" };
			}
			else if (Globals.XXDM_SCJZZYJSXY.equalsIgnoreCase(xxdm)) {
				
				querry += " and exists(select 1 from view_fdybbj d where a.bjdm=d.bjdm and d.zgh='"+userName+"') ";
				if (!StringUtils.isNull(zt)) {
					querry += " and fdysh='" + DealString.toGBK(zt) + "' ";
				}
				colList = new String[] { "color", "bgcolor", "����", "�к�", "xh",
						"xm", "bjmc", "jxjmc","jlje","mincj","avgcj","bjpm","zypm","cxcj","kh", "fdysh" };
				sql = "select rownum �к�,c.*,d.mincj,d.avgcj,d.bjpm,d.zypm from (select (case when nvl(b.tjflag,'��')='��' then 'blue' else 'red' end) color, "
					+ " (case nvl(b.fdysh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ "b.* from (select "
					+ pk
					+ " ����,a.* from "
					+ tableName
					+ " a"
					+ querry
					+ " and xydm='"
					+ userDep
					+ "') b order by bgcolor desc) c left join view_xscjpmb d on c.xh=d.xh and c.xn=d.xn and c.xq=d.xq";
				if (!StringUtils.isNull(jxjdm)) {
					String bjsqrs = dao.getOneRs("select rstz from xyjxjfpb where fpbm=? and xn=? and nd=? and jxjdm=?", new String[]{bjdm,Base.getJxjsqxn(),Base.getJxjsqnd(),jxjdm}, "rstz");
					String bjysqrs = dao.getOneRs("select count(*) rstz from view_xsjxjb where bjdm=? and xn=? and nd=? and jxjdm=?", new String[]{bjdm,Base.getJxjsqxn(),Base.getJxjsqnd(),jxjdm}, "rstz");
					String bjwshrs = dao.getOneRs("select count(*) rstz from view_xsjxjb where bjdm=? and xn=? and nd=? and jxjdm=? and fdysh='δ���'", new String[]{bjdm,Base.getJxjsqxn(),Base.getJxjsqnd(),jxjdm}, "rstz");;
					request.setAttribute("bjsqrs", bjsqrs);
					request.setAttribute("bjysqrs", bjysqrs);
					request.setAttribute("bjwshrs", bjwshrs);
				}
			} else if(xxdm.equalsIgnoreCase(Globals.XXDM_NBTYZYJSXY)){
				colList = new String[] { "color", "bgcolor", "����", "�к�", "xh",
						"nd", "xn", "xm", "bjmc", "jxjmc","zhpfmc", "fdysh" };
				querry += " and exists(select 1 from view_fdybbj d where c.bjdm=d.bjdm and d.zgh='"+userName+"') ";
				if (!StringUtils.isNull(zt)) {
					querry += " and fdysh='" + DealString.toGBK(zt) + "' ";
				}
				sql += querry;
			}else {
				if(Globals.XXDM_NBTYZYJSXY.equalsIgnoreCase(xxdm)){
					colList = new String[] { "color", "bgcolor", "����", "�к�", "xh",
							"nd", "xn", "xm", "bjmc", "jxjmc", "fdysh" };
				}
				if(!Globals.XXDM_LSXY.equalsIgnoreCase(xxdm)){
					//����ˮѧԺ
					querry += " and exists(select 1 from view_fdybbj d where c.bjdm=d.bjdm and d.zgh='"+userName+"') ";
					if (!StringUtils.isNull(zt)) {
						querry += " and fdysh='" + DealString.toGBK(zt) + "' ";
					}
					sql += querry;	
				}
				
			}
		}//end if

		if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){	//@author: ChenHuamao	�Ϻ����̼�����ѧ	��Ҫ��ȣ�Ҳû�б������
			colList = new String[] { "color", "bgcolor", "����", "�к�", "xn",
					"xh", "xm", "bjmc", "jxjmc", "xxsh"};
			sql = "select rownum �к�,c.* from (select (case when nvl(b.tjflag,'��')='��' then 'blue' else 'red' end) color, "
				+ " (case nvl(b.xxsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
				+ "b.* from (select "
				+ pk
				+ " ����,a.* from "
				+ tableName
				+ " a"
				+ querry
				+ " and xysh='ͨ��') b order by bgcolor desc) c";
		}
		boolean flag = false;
		String currTime = DateUtils.getTime();
		if(checkall!=null && !("".equals(checkall.trim())) && "yes".equalsIgnoreCase(checkall)){
			// TODO �����checkall���������������ֵ��yes����ô�ͱ���ѡ�еļ�¼
			if(shzt != null){
				String shjg = shzt.equalsIgnoreCase("pass")?"ͨ��":(shzt.equalsIgnoreCase("nopass")?"��ͨ��":"δ���");
				String[] cols = null;
				if("xy".equalsIgnoreCase(userType)){
					cols = new String[]{"xysh","xyshsj"};
				} else {
					cols = new String[]{"xxsh","xxshsj"};
				}
				if (StringUtils.isEqual(isFdy, "true")) {//����Ա���
//					if (xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)) {
//					cols = new String[]{"fdysh"};
//					}
////					��ɳ�����������
//					if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
//					cols = new String[]{"fdysh"};
//					}
//					//�人����ѧ
//					if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){
//					cols = new String[]{"fdysh"};
//					}
					if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)
							|| Globals.XXDM_CDFZGDZKXX.equalsIgnoreCase(xxdm)
							|| Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)
							|| Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)
							|| Globals.XXDM_SCJZZYJSXY.equalsIgnoreCase(xxdm)
							|| xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)
							|| xxdm.equalsIgnoreCase(Globals.XXDM_NBTYZYJSXY)) {
						cols = new String[]{"fdysh","fdyshsj"};
					}
				}

				//��������ѧ���������(���ǰ�Լ���������������������ж�)
	/*			if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
					String sShresult = "";//��ѧ����˽��
					String sJxjtg = "";//��ѧ����˳�רҵ�������
					String xhxnnd = "";//ѧ��ѧ�������ʱ����

					if (StringUtils.isEqual(shzt, "pass")) {//�������ͨ��
						//����ѧ����ѧ�����
						if (!StringUtils.isNull(jxjfl) && StringUtils.isEqual(jxjfl, "����ѧ����ѧ��")) {
							for(int i=0;i<checkVal.length;i++){
								xhxnnd = dao.getOneRs("select xn||nd||xh pk from view_xsjxjb where xn||nd||xh||jxjdm = ?", new String[]{checkVal[i]}, "pk");
								zydm = dao.getOneRs("select zydm from view_xsjxjb where xn||nd||xh||jxjdm = ?", new String[]{checkVal[i]}, "zydm");
								//�Ƿ��м��
								String num = dao.getOneRs("select count(*) num from view_xsjxjb where xn||nd||xh=? and xysh='ͨ��' and xxsh='ͨ��' and jxjmc in (select jxjmc from jxjdmb where jxjfl='���ѧ��')", new String[]{xhxnnd}, "num");
								int tmp = !StringUtils.isNull(num) ? Integer.parseInt(num) : 0;
								if (tmp > 0) {
									sShresult += (i+1);
									sShresult += ",";
									continue;
								}
								//�Ƿ�רҵ��������
								String jxjrs = dao.getOneRs("select jxjrs from xyjxjrs where xn||nd=? and bmlb=? and bmdm=? and jxjdm=?", new String[]{xhxnnd != null && xhxnnd.length()>13 ? xhxnnd.substring(0, 13) : "", "zydm", zydm, jxjdm}, "jxjrs");
								String shtgrs = dao.getOneRs("select count(*) num from xsjxjb where xn||nd=? and jxjdm=? and xysh='ͨ��' and xxsh='ͨ��' ", new String[]{xhxnnd != null && xhxnnd.length()>13 ? xhxnnd.substring(0, 13) : "" , jxjdm}, "num");
								int tmprs = !StringUtils.isNull(jxjrs) ? Integer.parseInt(jxjrs) : 0;
								int tmptgrs = !StringUtils.isNull(shtgrs) ? Integer.parseInt(shtgrs) : 0;
								if ((tmptgrs >= tmprs) && (tmprs != 0)) {
									sJxjtg += (i+1);
									sJxjtg += ",";
									continue;
								}
								StandardOperation.update("xsjxjb",cols,new String[]{shjg,currTime},"xn||nd||xh||jxjdm",checkVal[i],request);
							}
						} else if (!StringUtils.isNull(jxjfl) && StringUtils.isEqual(jxjfl, "���ѧ��")) {
							//���ѧ��
							for(int i=0;i<checkVal.length;i++){
								xhxnnd = dao.getOneRs("select xn||nd||xh pk from view_xsjxjb where xn||nd||xh||jxjdm = ?", new String[]{checkVal[i]}, "pk");
								//�Ƿ��м��
								String num = dao.getOneRs("select count(*) num from view_xsjxjb where xn||nd||xh=? and xysh='ͨ��' and xxsh='ͨ��' and jxjmc in (select jxjmc from jxjdmb where jxjfl='����ѧ����ѧ��')", new String[]{xhxnnd}, "num");
								int tmp = !StringUtils.isNull(num) ? Integer.parseInt(num) : 0;
								if (tmp > 0) {
									sShresult += (i+1);
									sShresult += ",";
									continue;
								}
								//�Ƿ�רҵ��������
								String jxjrs = dao.getOneRs("select jxjrs from xyjxjrs where xn||nd=? and bmlb=? and bmdm=? and jxjdm=?", new String[]{xhxnnd != null && xhxnnd.length()>13 ? xhxnnd.substring(0, 13) : "", "zydm", zydm, jxjdm}, "jxjrs");
								String shtgrs = dao.getOneRs("select count(*) num from xsjxjb where xn||nd||jxjdm=? and xysh='ͨ��' and xxsh='ͨ��' ", new String[]{xhxnnd != null && xhxnnd.length()>13 ? xhxnnd.substring(0, 13) : "" + jxjdm}, "num");
								int tmprs = !StringUtils.isNull(jxjrs) ? Integer.parseInt(jxjrs) : 0;
								int tmptgrs = !StringUtils.isNull(shtgrs) ? Integer.parseInt(shtgrs) : 0;
								if ((tmptgrs >= tmprs) && (tmprs != 0)) {
									sJxjtg += (i+1);
									sJxjtg += ",";
									continue;
								}
								StandardOperation.update("xsjxjb",cols,new String[]{shjg,currTime},"xn||nd||xh||jxjdm",checkVal[i],request);
							}
						} else {
							//������ѧ�����
							for(int i=0;i<checkVal.length;i++){
								//�Ƿ�רҵ��������
								String jxjrs = dao.getOneRs("select jxjrs from xyjxjrs where xn||nd=? and bmlb=? and bmdm=? and jxjdm=?", new String[]{xhxnnd != null && xhxnnd.length()>13 ? xhxnnd.substring(0, 13) : "", "zydm", zydm, jxjdm}, "jxjrs");
								String shtgrs = dao.getOneRs("select count(*) num from xsjxjb where xn||nd||jxjdm=? and xysh='ͨ��' and xxsh='ͨ��' ", new String[]{xhxnnd != null && xhxnnd.length()>13 ? xhxnnd.substring(0, 13) : "" + jxjdm}, "num");
								int tmprs = !StringUtils.isNull(jxjrs) ? Integer.parseInt(jxjrs) : 0;
								int tmptgrs = !StringUtils.isNull(jxjrs) ? Integer.parseInt(shtgrs) : 0;
								if ((tmptgrs >= tmprs) && (tmprs != 0)) {
									sJxjtg += (i+1);
									sJxjtg += ",";
									continue;
								}
								StandardOperation.update("xsjxjb",cols,new String[]{shjg,currTime},"xn||nd||xh||jxjdm",checkVal[i],request);
							}
						}
					} else {//������˲�ͨ��
						for(int i=0;i<checkVal.length;i++){
							StandardOperation.update("xsjxjb",cols,new String[]{shjg,currTime},"xn||nd||xh||jxjdm",checkVal[i],request);
						}
					}
					//�Ƿ��м��
					if (!StringUtils.isNull(sShresult)) {
						sShresult = String.format("���е� %1$s ���������ʧ�ܣ�ԭ�����������ѧ���м�ý�ѧ��\n��˶Ըý�ѧ��ļ�ù涨��", sShresult.substring(0, sShresult.length() - 1));

						request.setAttribute("shresult", sShresult);
					}
					//�Ƿ�רҵ����
					if (!StringUtils.isNull(sJxjtg)) {
						sJxjtg = String.format("���е� %1$s ���������ʧ�ܣ���ȷ�������ͨ��������δ�����ý�ѧ����������ƣ�", sJxjtg.substring(0, sJxjtg.length() - 1));
						request.setAttribute("sJxjtg", sJxjtg);
					}

				} else */  //lyl 
					
				if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
					String xzrs = "";//��������
//					String fpfs = "";//���䷽ʽ
					String tgrs = "";//��ͨ������
					String sTmp = "";
					currTime = DateUtils.getYear() + DateUtils.getMonth() + DateUtils.getDayOfMonth();
					if (!StringUtils.isNull(shzt) && StringUtils.isEqual(shzt, "pass")) {//ͨ��
						//�жϽ�ѧ��ͨ�������Ƿ���������
						String[] jxjsqxnndxq = dao.getOneRs("select jxjsqxn,jxjsqnd,jxjsqxq from xtszb where rownum=1", new String[]{}, new String[]{"jxjsqxn", "jxjsqnd", "jxjsqxq"});
						for (int i=0;i<checkVal.length;i++) {
							String[] xyzybjjxjdmnj = dao.getOneRs("select xydm,zydm,bjdm,jxjdm,nj from view_xsjxjb where " + pk + "=?", new String[]{checkVal[i]}, new String[]{"xydm", "zydm", "bjdm","jxjdm", "nj"});

							if (xyzybjjxjdmnj != null && xyzybjjxjdmnj.length == 5) {
								xzrs = dao
								.getOneRs(
										"select jxjrs from xyjxjrs where xn=? and nd=? and bmdm=? and bmlb=? and jxjdm=?",
										new String[] { jxjsqxnndxq[0],
												jxjsqxnndxq[1], xyzybjjxjdmnj[2],"bjdm", xyzybjjxjdmnj[3]}, "jxjrs");
								/*fpfs = "רҵ";
								if (StringUtils.isNull(xzrs)) {
									xzrs = dao
									.getOneRs(
											"select rstz from xyjxjfpb where xn=? and nd=? and bmdm=? and fpbm=? and jxjdm=? and nj=?",
											new String[] { jxjsqxnndxq[0],
													jxjsqxnndxq[1], xyzybjjxjdmnj[0],
													xyzybjjxjdmnj[2], xyzybjjxjdmnj[3],
													xyzybjjxjdmnj[4] }, "rstz");
									fpfs = "�༶";
								}*/
							}
							/*if (!StringUtils.isNull(fpfs) && StringUtils.isEqual(fpfs, "רҵ")) {
								tgrs = dao.getOneRs("select count(*) num from view_xsjxjb where jxjdm=? and xn=? and nd=? and nj=? and zydm=? and xysh='ͨ��' and xxsh='ͨ��'", new String[]{xyzybjjxjdmnj[3],jxjsqxnndxq[0],jxjsqxnndxq[1],xyzybjjxjdmnj[4],xyzybjjxjdmnj[1]}, "num");
							} else {*/
							if (userType.equalsIgnoreCase("xy")) {
								tgrs = dao.getOneRs("select count(*) num from view_xsjxjb where jxjdm=? and xn=? and nd=? and nj=? and bjdm=? and xysh='ͨ��' ", new String[]{xyzybjjxjdmnj[3],jxjsqxnndxq[0],jxjsqxnndxq[1],xyzybjjxjdmnj[4],xyzybjjxjdmnj[2]}, "num");
							} else {
								tgrs = dao.getOneRs("select count(*) num from view_xsjxjb where jxjdm=? and xn=? and nd=? and nj=? and bjdm=? and xysh='ͨ��' and xxsh='ͨ��'", new String[]{xyzybjjxjdmnj[3],jxjsqxnndxq[0],jxjsqxnndxq[1],xyzybjjxjdmnj[4],xyzybjjxjdmnj[2]}, "num");
							}
							//}
							if (!StringUtils.isNull(xzrs)) {
								int iXzrs = Integer.parseInt(xzrs.trim());
								int iTgrs = StringUtils.isNull(tgrs) ? 0 : Integer.parseInt(tgrs.trim());
								if (iTgrs >= iXzrs) {
									sTmp += (i+1);
									sTmp += ",";
									continue;
								}
							}
							StandardOperation.update("xsjxjb",cols,new String[]{shjg,currTime},"xn||nd||xh||jxjdm",checkVal[i],request);
						}
					} else {//��ͨ��
						for(int i=0;i<checkVal.length;i++){
							StandardOperation.update("xsjxjb",cols,new String[]{shjg,currTime},"xn||nd||xh||jxjdm",checkVal[i],request);
						}
					}
					if (!StringUtils.isNull(sTmp)) {
						sTmp = String.format("���е� %1$s �����ݲ���ʧ�ܣ���ѧ�������༶�������� %2$s �ˣ������ͨ�� %3$s ��", sTmp.substring(0, sTmp.length()-1),xzrs,tgrs);
						request.setAttribute("shresult", sTmp);
					}
				} else if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {
					String xzrs = "";//��������
					String tgrs = "";//��ͨ������
					String sTmp = "";
					if (!StringUtils.isNull(shzt) && StringUtils.isEqual(shzt, "pass")) {//ͨ��
						//�жϽ�ѧ��ͨ�������Ƿ���������
						String[] jxjsqxnndxq = dao.getOneRs("select jxjsqxn,jxjsqnd,jxjsqxq from xtszb where rownum=1", new String[]{}, new String[]{"jxjsqxn", "jxjsqnd", "jxjsqxq"});
						for (int i=0;i<checkVal.length;i++) {
							String[] xyzybjjxjdmnj = dao.getOneRs("select xydm,zydm,bjdm,jxjdm,nj from view_xsjxjb where " + pk + "=?", new String[]{checkVal[i]}, new String[]{"xydm", "zydm", "bjdm","jxjdm", "nj"});

							if (xyzybjjxjdmnj != null && xyzybjjxjdmnj.length == 5) {
								xzrs = dao
								.getOneRs(
										"select jxjrs from xyjxjrs where xn=? and nd=? and bmdm=? and bmlb=? and jxjdm=?",
										new String[] { jxjsqxnndxq[0],
												jxjsqxnndxq[1], xyzybjjxjdmnj[2],"bjdm", xyzybjjxjdmnj[3]}, "jxjrs");
							}
							if (userType.equalsIgnoreCase("xy")) {
								//����Ա���ͨ��������
								if ("true".equalsIgnoreCase(isFdy)) {
									tgrs = dao
									.getOneRs(
											"select count(*) num from view_xsjxjb where jxjdm=? and xn=? and nd=? and nj=? and bjdm=? and fdysh='ͨ��' ",
											new String[] {
													xyzybjjxjdmnj[3],
													jxjsqxnndxq[0],
													jxjsqxnndxq[1],
													xyzybjjxjdmnj[4],
													xyzybjjxjdmnj[2] },
													"num");
								} else {//ѧԺ���ͨ��������
									tgrs = dao
									.getOneRs(
											"select count(*) num from view_xsjxjb where jxjdm=? and xn=? and nd=? and nj=? and bjdm=? and xysh='ͨ��' ",
											new String[] {
													xyzybjjxjdmnj[3],
													jxjsqxnndxq[0],
													jxjsqxnndxq[1],
													xyzybjjxjdmnj[4],
													xyzybjjxjdmnj[2] },
													"num");
								}
							} else {//ѧУ���ͨ��������
								tgrs = dao.getOneRs("select count(*) num from view_xsjxjb where jxjdm=? and xn=? and nd=? and nj=? and bjdm=? and xxsh='ͨ��'", new String[]{xyzybjjxjdmnj[3],jxjsqxnndxq[0],jxjsqxnndxq[1],xyzybjjxjdmnj[4],xyzybjjxjdmnj[2]}, "num");
							}
							if (!StringUtils.isNull(xzrs)) {
								int iXzrs = Integer.parseInt(xzrs);
								int iTgrs = StringUtils.isNull(tgrs) ? 0 : Integer.parseInt(tgrs);
								if (iTgrs >= iXzrs && iXzrs!=0) {
									sTmp += (i+1);
									sTmp += ",";
									continue;
								}
							}
							StandardOperation.update("xsjxjb",cols,new String[]{shjg,currTime},"xn||nd||xh||jxjdm",checkVal[i],request);
						}
					} else {//��ͨ��
						for(int i=0;i<checkVal.length;i++){
							StandardOperation.update("xsjxjb",cols,new String[]{shjg,currTime},"xn||nd||xh||jxjdm",checkVal[i],request);
						}
					}
					if (!StringUtils.isNull(sTmp)) {
						sTmp = String.format("���е� %1$s �����ݲ���ʧ�ܣ���ѧ�������༶�������� %2$s �ˣ������ͨ�� %3$s ��", sTmp.substring(0, sTmp.length()-1),xzrs,tgrs);
						request.setAttribute("shresult", sTmp);
					}
				}else if (xxdm.equalsIgnoreCase(Globals.XXDM_LSXY)) {
					String xzrs = "";//��������
					String tgrs = "";//��ͨ������
					String sTmp = "";
					if (!StringUtils.isNull(shzt) 
							&& StringUtils.isEqual(shzt, "pass") 
							&& "xy".equalsIgnoreCase(userType)) {//ͨ��
						//�жϽ�ѧ��ͨ�������Ƿ���������
						String[] jxjsqxnndxq = dao.getOneRs("select jxjsqxn,jxjsqnd,jxjsqxq from xtszb where rownum=1", new String[]{}, new String[]{"jxjsqxn", "jxjsqnd", "jxjsqxq"});
						for (int i=0;i<checkVal.length;i++) {
							String[] xyzybjjxjdmnj = dao.getOneRs("select xh,xydm,zydm,bjdm,jxjdm,nj from view_xsjxjb where " + pk + "=?", new String[]{checkVal[i]}, new String[]{"xydm", "zydm", "bjdm","jxjdm", "nj","xh"});
							String fpfs = dao.getOneRs("select distinct bmlb fpfs from xyjxjrs where xn=? and jxjdm=? and jxjrs is not null", 
														new String[]{jxjsqxnndxq[0],xyzybjjxjdmnj[3]}, "fpfs");
							String[] input = {};
							if (xyzybjjxjdmnj != null && xyzybjjxjdmnj.length == 6) {
								String fpbm = "zydm".equalsIgnoreCase(fpfs) ? xyzybjjxjdmnj[1] : xyzybjjxjdmnj[2];
								fpbm = "xydm".equalsIgnoreCase(fpfs) ? xyzybjjxjdmnj[0] : fpbm;
								
								input = new String[] { jxjsqxnndxq[0],xyzybjjxjdmnj[3], fpbm, xyzybjjxjdmnj[4]};
								xzrs = dao.getOneRs(
										"select jxjrs from xyjxjrs where xn=? and jxjdm=? and bmdm=? and nj=? ",
										input, 
										"jxjrs");
								//xzrs = StringUtils.isNull(xzrs) ? "-1" : xzrs;
							}
							input = new String[] { xyzybjjxjdmnj[5],xyzybjjxjdmnj[3],
												   jxjsqxnndxq[0],xyzybjjxjdmnj[4]};
							String sqlT = "select count(*) num from view_xsjxjb a where not exists(select 1 from view_xsjxjb b where a.xh=b.xh and a.xn=b.xn and b.xh=?) and jxjdm=? and xn=? and nj=? and xysh='ͨ��' ";
							if("xydm".equalsIgnoreCase(fpfs)){
								sqlT += " and xydm=?";
								input = StringUtils.joinStrArr(input, new String[]{xyzybjjxjdmnj[0]});
							} else if ("zydm".equalsIgnoreCase(fpfs)){
								sqlT += " and zydm=?";
								input = StringUtils.joinStrArr(input, new String[]{xyzybjjxjdmnj[1]});
							} else {
								sqlT += " and bjdm=?";
								input = StringUtils.joinStrArr(input, new String[]{xyzybjjxjdmnj[2]});
							}
							tgrs = dao.getOneRs(
									sqlT,
									input,
									"num");
							tgrs = StringUtils.isNull(tgrs) ? "0" : tgrs;
							if (!StringUtils.isNull(xzrs)) {
								int iXzrs = Integer.parseInt(xzrs);
								int iTgrs = StringUtils.isNull(tgrs) ? 0 : Integer.parseInt(tgrs);
								if (iTgrs >= iXzrs && iXzrs!=0) {
									sTmp += (i+1);
									sTmp += ",";
									continue;
								}
							}
							StandardOperation.update("xsjxjb",cols,new String[]{shjg,currTime},"xh||jxjdm||xn||xq",checkVal[i],request);
						}
					} else {//��ͨ��
						for(int i=0;i<checkVal.length;i++){
							StandardOperation.update("xsjxjb",cols,new String[]{shjg,currTime},"xh||jxjdm||xn||xq",checkVal[i],request);
						}
					}
					if (!StringUtils.isNull(sTmp)) {
						sTmp = String.format("���е� %1$s �����ݲ���ʧ�ܣ����ͨ�������Ѿ��ﵽ�趨������", sTmp.substring(0, sTmp.length()-1));
						request.setAttribute("shresult", sTmp);
					}
				} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {//�㽭����
					String xzrs = "";//��������
					String fpfs = "bjdm";//���䷽ʽ
					String tgrs = "";//��ͨ������
					String sTmp = "";
					if (!StringUtils.isNull(shzt) && StringUtils.isEqual(shzt, "pass")) {//ͨ��
						//�жϽ�ѧ��ͨ�������Ƿ���������
						String[] jxjsqxnndxq = dao.getOneRs("select jxjsqxn,jxjsqnd,jxjsqxq from xtszb where rownum=1", new String[]{}, new String[]{"jxjsqxn", "jxjsqnd", "jxjsqxq"});
						for (int i=0;i<checkVal.length;i++) {
							String[] xyzybjjxjdmnj = dao.getOneRs("select xydm,zydm,bjdm,jxjdm,nj from view_xsjxjb where " + pk + "=?", new String[]{checkVal[i]}, new String[]{"xydm", "zydm", "bjdm","jxjdm", "nj"});

							if (xyzybjjxjdmnj != null && xyzybjjxjdmnj.length == 5) {
								xzrs = dao
								.getOneRs(
										"select jxjrs from xyjxjrs where xn=? and nd=? and bmdm=? and bmlb=? and jxjdm=?",
										new String[] { jxjsqxnndxq[0],
												jxjsqxnndxq[1], xyzybjjxjdmnj[2],fpfs, xyzybjjxjdmnj[3]}, "jxjrs");

								if (StringUtils.isEqual(userType, "xx")) {
									tgrs = dao
									.getOneRs(
											"select count(*) num from view_xsjxjb where jxjdm=? and xn=? and nd=? and nj=? and bjdm=? and xysh='ͨ��' and xxsh='ͨ��'",
											new String[] {
													xyzybjjxjdmnj[3],
													jxjsqxnndxq[0],
													jxjsqxnndxq[1],
													xyzybjjxjdmnj[4],
													xyzybjjxjdmnj[2] },
									"num");
								} else {
									tgrs = dao
									.getOneRs(
											"select count(*) num from view_xsjxjb where jxjdm=? and xn=? and nd=? and nj=? and bjdm=? and xysh='ͨ��' ",
											new String[] {
													xyzybjjxjdmnj[3],
													jxjsqxnndxq[0],
													jxjsqxnndxq[1],
													xyzybjjxjdmnj[4],
													xyzybjjxjdmnj[2] },
									"num");
								}
								if (!StringUtils.isNull(xzrs)) {
									int iXzrs = Integer.parseInt(xzrs);
									int iTgrs = StringUtils.isNull(tgrs) ? 0 : Integer.parseInt(tgrs);
									if (iTgrs >= iXzrs) {
										sTmp += (i+1);
										sTmp += ",";
										continue;
									}
								}
								StandardOperation.update("xsjxjb",cols,new String[]{shjg,currTime},"xn||nd||xh||jxjdm",checkVal[i],request);
							}
						}
					} else {//��ͨ��
						for(int i=0;i<checkVal.length;i++){
							StandardOperation.update("xsjxjb",cols,new String[]{shjg,currTime},"xn||nd||xh||jxjdm",checkVal[i],request);
						}
					}
					if (!StringUtils.isNull(sTmp)) {
						sTmp = String.format("���е� %1$s �����ݲ���ʧ�ܣ���ѧ�������������� %2$s �ˣ������ͨ�� %3$s ��", sTmp.substring(0, sTmp.length()-1),xzrs,tgrs);
						request.setAttribute("shresult", sTmp);
					}

				}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)){
					//�㽭��ҵְҵ
					PjpyZjsyzyForm myModel = new PjpyZjsyzyForm(); 
					PjpyZjsyzyService zysyzyService = new PjpyZjsyzyService();	
					HashMap<String, String> zjsyzyMap =  new HashMap<String, String>();		
					String mes = "";
					for(int i=0;i<checkVal.length;i++){
						if (!StringUtils.isNull(shzt) && StringUtils.isEqual(shzt, "pass")){
							zjsyzyMap = zysyzyService.getStuJxjInfo(pk,checkVal[i]);
							pk = "xn||nd||xh||jxjdm";
							jxjdm = zjsyzyMap.get("jxjdm");
							String xn = zjsyzyMap.get("xn");
							xq = zjsyzyMap.get("xq");
							String nd = zjsyzyMap.get("nd");
							xh = zjsyzyMap.get("xh");
							String jmc = zjsyzyMap.get("jxjmc");
							myModel.setJxjdm(jxjdm);
							myModel.setXn(xn);
							myModel.setXq(xq);
							myModel.setNd(nd);
							myModel.setPk(pk);
							myModel.setXh(xh);
							myModel.setXydm(zjsyzyMap.get("xydm"));
							myModel.setNj(zjsyzyMap.get("nj"));
							myModel.setPkValue(xn+nd+xh+jxjdm);
							myModel.setYesNo("ͨ��");
							myModel.setSh(zjsyzyMap.get("xysh"));
							String result = zysyzyService.saveCheck(myModel, request,jmc);
							mes +=  result != null && !"".equalsIgnoreCase(result) ? result + "\n" : "";
							flag = mes == null || "".equalsIgnoreCase(mes) ? true : false;
						}else{
							flag = StandardOperation.update("xsjxjb",cols,new String[]{shjg,currTime},"xn||nd||xh||jxjdm",checkVal[i],request);
						}							
					}
					mes += mes != null && !"".equalsIgnoreCase(mes.replaceAll(" ", "")) ? "���ʧ��!" : "";
					request.setAttribute("mes", mes);
				} else if (Globals.XXDM_SCJZZYJSXY.equalsIgnoreCase(xxdm)) {
					if ("ͨ��".equalsIgnoreCase(shjg)) {
						if ("xy".equalsIgnoreCase(userType)) {
							String cb = "";
							//ѧԺ���û����ʱ�жϵ�ǰ��˽���Ƿ��г�ѧУ���õ��ܽ��
							for(int i=0;i<checkVal.length;i++){
								HashMap<String, String> rsMap = dao
								.getMapNotOut(
										"select jxjdm,jlje from view_xsjxjb where xn||nd||xh||jxjdm=?",
										new String[] { checkVal[i] });
								String xytgje = dao.getOneRs("select sum(jlje) je from view_xsjxjb where xn=? and xq=? and xydm=? and jxjdm=? and xysh='ͨ��'", new String[]{Base.getJxjsqxn(), Base.getJxjsqxq(),xydm, rsMap.get("jxjdm")}, "je");
								String jsje = dao
								.getOneRs(
										"select sum(to_number(jsje)) jsje from xyjxjrs where bmdm=? and bmlb='xydm' and jxjdm=? and xn=? and nd=?",
										new String[] {xydm,rsMap.get("jxjdm"),Base.getJxjsqxn(),Base.getJxjsqnd()}, "jsje");
								double xyje = StringUtils.isNull(xytgje) ? 0 : Double.parseDouble(xytgje);
								double xxje = StringUtils.isNull(jsje) ? 0 : Double.parseDouble(jsje);
								double djlje = StringUtils.isNull(rsMap.get("jlje")) ? 0 : Double.parseDouble(rsMap.get("jlje"));
								if ((xyje+djlje) > xxje) {
									cb +=(i+1)+",";
									continue;
								} else {
									StandardOperation.update("xsjxjb",cols,new String[]{shjg,currTime},"xn||nd||xh||jxjdm",checkVal[i],request);
								}
							}	 
							if (!StringUtils.isNull(cb)) {
								request.setAttribute("shresult", "�����ɣ����е�" + cb + "���������ʧ�ܣ���ǰ���ͨ������ѳ�ѧУ���õĽ�ѧ���ܽ�");
							}

						} else {
							for(int i=0;i<checkVal.length;i++){
								StandardOperation.update("xsjxjb",cols,new String[]{shjg,currTime},"xn||nd||xh||jxjdm",checkVal[i],request);
							}	
						}
					} else {
						for(int i=0;i<checkVal.length;i++){
							StandardOperation.update("xsjxjb",cols,new String[]{shjg,currTime},"xn||nd||xh||jxjdm",checkVal[i],request);
						}
					}
				} else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
					String[] sqlArr = new String[checkVal.length];
					for (int i=0;i<checkVal.length;i++) {
						StringBuffer sqls = new StringBuffer("update xsjxjb set xxsh='");
						sqls.append(shjg);
						sqls.append("' where xh||xn||jxjdm='");
						sqls.append(checkVal[i]);
						sqls.append("'");
						sqlArr[i] = sqls.toString();
					}
					if ("ͨ��".equalsIgnoreCase(shjg)) {
						int[] result = dao.runBatch(sqlArr);
						flag = dao.checkBatch(result);
					} else {
						int[] result = dao.runBatch(sqlArr);
						flag = dao.checkBatch(result);
					}
				}  else {//����ѧУ���
					for(int i=0;i<checkVal.length;i++){
						StandardOperation.update("xsjxjb",cols,new String[]{shjg,currTime},"xn||nd||xh||jxjdm",checkVal[i],request);
					}
				}
				flag = true;	
			}

		}


		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if (Globals.XXDM_SCJZZYJSXY.equalsIgnoreCase(xxdm)) {

			String[] cn = new String[] { "color", "bgcolor", "����", "�к�", "ѧ��",
					"����", "�༶", "��ѧ������", "��ѧ����", "��ͳɼ�", "ƽ���ɼ�", "�༶����",
					"רҵ����", "���гɼ�", "���п���", "���" };
			topTr = dao.arrayToList(colList, cn);
		} else if (Globals.XXDM_XCXY.equalsIgnoreCase(xxdm)) {
			
			String[] cnList = new String[]{ "color", "bgcolor", "����", "�к�", "ѧ��", "����", "�༶", "ѧ��",
					"���", "��ѧ��","ƽ���ɼ�", "���гɼ�", "�ܳɼ�", "����", "���"};
			topTr = dao.arrayToList(colList, cnList);
		}
		/*if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			String[] en = null;
			String[] cn = null;
			topTr = new ArrayList();
			if (userType.equalsIgnoreCase("xx")) {
				en = new String[] { "color", "bgcolor", "����", "�к�", "xn",
						"nd", "xh", "xm", "bjmc", "jxjmc","jqfpm", "zhszcpzfpm","cet4","xxsh" };
				cn = new String[]{"color","bgcolor","����", "�к�","ѧ��","���",
						"ѧ��","����","�༶����","��ѧ������","��Ȩ��רҵ����",
						"�ۺ����ʰ༶����","Ӣ���ļ�","ѧУ���"};
			} else {
				en = new String[] { "color", "bgcolor", "����", "�к�", "xn",
						"nd", "xh", "xm", "bjmc", "jxjmc","jqfpm", "zhszcpzfpm","cet4","xysh" };
				cn = new String[]{"color","bgcolor","����", "�к�","ѧ��","���",
						"ѧ��","����","�༶����","��ѧ������","��Ȩ��רҵ����",
						"�ۺ����ʰ༶����","Ӣ���ļ�","ѧԺ���"};
			} 
			for (int i=0;i<en.length;i++) {
				HashMap<String, String> tmp = new HashMap<String, String>();
				tmp.put("en", en[i]);
				tmp.put("cn", cn[i]);
				topTr.add(tmp);
			}
		}*/  //lyl 2011-05-25
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {
			String[] en = null;
			String[] cn = null;
			topTr = new ArrayList();
			if ("xy".equalsIgnoreCase(userType)) {//ѧԺ
				en = new String[] { "color", "bgcolor", "����", "�к�", "xn",
						"nd", "xh", "xm", "bjmc", "jxjmc","zhszcppm", "xysh" };
				cn = new String[]{"color","bgcolor","����", "�к�","ѧ��","���",
						"ѧ��","����","�༶����","��ѧ������","�ۺϲ����༶����",Base.YXPZXY_KEY+"���"};
			} else {//��������
				en = new String[] { "color", "bgcolor", "����", "�к�", "xn",
						"nd", "xh", "xm", "bjmc", "jxjmc","zhszcppm", "xxsh" };
				cn = new String[]{"color","bgcolor","����", "�к�","ѧ��","���",
						"ѧ��","����","�༶����","��ѧ������","�ۺϲ����༶����","ѧУ���"};
			} 
			for (int i=0;i<en.length;i++) {
				HashMap<String, String> tmp = new HashMap<String, String>();
				tmp.put("en", en[i]);
				tmp.put("cn", cn[i]);
				topTr.add(tmp);
			}
		}
		if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {//�㽭��ý
			String[] enList = new String[]{};
			String[] cnList = new String[]{};
			topTr = new ArrayList<HashMap<String, String>>();
			if ("xy".equalsIgnoreCase(userType)) {
				if ("true".equalsIgnoreCase(isFdy)) {
					enList = new String[] { "color", "bgcolor", "����", "�к�", "xh",
							"nd", "xn", "xm", "bjmc", "jxjmc", "cj", "bjpm", "fdysh" };
					cnList = new String[] { "color", "bgcolor", "����", "�к�", "ѧ��",
							"���", "ѧ��", "����", "�༶", "��ѧ��", "�ۺ����ʷ�", "�༶����", "����Ա���" };
				} else {
					enList = new String[] { "color", "bgcolor", "����", "�к�", "xh",
							"nd", "xn", "xm", "bjmc", "jxjmc", "cj", "bjpm", "xysh" };
					cnList = new String[] { "color", "bgcolor", "����", "�к�", "ѧ��",
							"���", "ѧ��", "����", "�༶", "��ѧ��", "�ۺ����ʷ�", "�༶����", Base.YXPZXY_KEY+"���" };
				}
			} else {
				enList = new String[] { "color", "bgcolor", "����", "�к�", "xh",
						"nd", "xn", "xm", "bjmc", "jxjmc", "cj", "bjpm", "xxsh" };
				cnList = new String[] { "color", "bgcolor", "����", "�к�", "ѧ��",
						"���", "ѧ��", "����", "�༶", "��ѧ��", "�ۺ����ʷ�", "�༶����", "ѧУ���" };

			}
			for (int i=0;i<enList.length;i++) {
				HashMap<String, String> tmp = new HashMap<String, String>();
				tmp.put("en", enList[i]);
				tmp.put("cn", cnList[i]);
				topTr.add(tmp);
			}
		}
		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
			topTr = new ArrayList<HashMap<String, String>>();
			String[] cn = new String[] { "pk", "bgcolor", "�к�", "ѧ��",
					 "����", "�༶", "ѧ��", "ѧҵ������", "�ۺϱ��ַ�", "��Ϊ���ַ�", "ͻ�����ַ�", "�ܷ�", "����","�ҿ�", "����", "��ѧ��", "���" };
			String[] en = new String[] { "pk", "bgcolor", "�к�", "xh",
					 "xm", "bjmc", "xn", "xycpf", "zhbxf", "xwtxf", "tcbxf", "zf", "bjpm","cjgk", "cfgk", "jxjmc", "xxsh" };
			for (int i=0;i<en.length;i++) {
				HashMap<String, String> tmp = new HashMap<String, String>();
				tmp.put("en", en[i]);
				tmp.put("cn", cn[i]);
				topTr.add(tmp);
			}
		}
		if (((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("a")) || flag) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
			sql = "select jxjrs from view_xyjxjrs where jxjdm = ? and bmlb = ? and xn = ? and nd = ? and bmdm = ?";
			String [] jxjrs = dao.getOneRs(sql, new String []{jxjdm,dispFlag,currXn,currNd,bmdm}, new String []{"jxjrs"});
			if(jxjrs==null || jxjrs[0] == null){
				request.setAttribute("jxjrs", "δ����");
			}else{
				request.setAttribute("jxjrs", jxjrs[0]);
			}
			// ���ͽ�ѧ����������
		}


		request.setAttribute("bjList", dao.getBjList(xydm, zydm));
		//��ɳ��������Ա��ȡ��Ӧרҵ�༶
		if (StringUtils.isEqual(isFdy, "true")) {
//			if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)
//			|| Globals.XXDM_CDFZGDZKXX.equalsIgnoreCase(xxdm)
//			|| Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)
//			|| Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)
//			|| Globals.XXDM_SCJZZYJSXY.equalsIgnoreCase(xxdm)
//			) {
			String fdyName = session.getAttribute("userName").toString();
			fdyName = !StringUtils.isNull(fdyName) ? fdyName : "";
			sql = "select bjdm from fdybjb where zgh=?";
			String[] bjdmList = dao.getRs(sql, new String[]{fdyName}, "bjdm");
			@SuppressWarnings("unused")
			String[] zydmList = new String[]{};
			String[] bjmcList = new String[bjdmList.length];
			@SuppressWarnings("unused")
			String[] zymcList = null;
			List<HashMap<String, String>> bjList = new ArrayList<HashMap<String,String>>();
//			List<HashMap<String, String>> zyList = new ArrayList<HashMap<String,String>>();
			if (bjdmList != null && bjdmList.length > 0) {		
				StringBuffer strSql = new StringBuffer("select zydm from view_njxyzybj where bjdm in (");
				for (int i=0;i<bjdmList.length;i++) {
					strSql.append("'");
					strSql.append(bjdmList[i]);
					strSql.append("',");
					bjmcList[i] = dao.getOneRs("select bjmc from view_njxyzybj where bjdm=?", new String[]{bjdmList[i]}, "bjmc");
				}
				sql = strSql.substring(0, strSql.length()-1).toString() + ") group by zydm";
//				zydmList = dao.getRs(sql, new String[]{}, "zydm");
//				if (zydmList != null && zydmList.length>0) {
//				zymcList = new String[zydmList.length];
//				for (int i=0;i<zydmList.length;i++) {
//				zymcList[i] = dao.getOneRs("select zymc from view_njxyzybj where zydm=?", new String[]{zydmList[i]}, "zymc");
//				}
//				}
			}
			bjList = getFdyBjlist(bjdmList, bjmcList, xxdm);
			//zyList = dao.arrayToList(zydmList, zymcList);
			//request.setAttribute("zyList", zyList);// ����רҵ�б�
			request.setAttribute("bjList", bjList);// ���Ͱ༶�б�
			//}
		}

		sql = "select jxjdm,jxjmc from jxjdmb order by jxjdm";
		List jxjList = dao.getList(sql, new String[] {}, new String[] {
				"jxjdm", "jxjmc" });
		rw = Base.chkUPower(userName,"prise_check.do",false)==1?"yes":"no";
		priseForm.setXn(currXn);
		priseForm.setNd(currNd);
		priseForm.setYesNo(yesNo);
		//ȡ����������
		request.setAttribute("rw", rw);// ���Ͷ�дȨ��
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("act", dataType);// �������ݲ�ѯ����
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("njList", dao.getNjList());// �����꼶�б�
		request.setAttribute("xnList", dao.getXnndList());// ����ѧ���б�
		request.setAttribute("xqList", dao.getXqList());// ����ѧ���б�
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", dao.getZyList(xydm));// ����רҵ�б�
		priseForm.setXq(Base.getJxjsqxq());
		if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
			if (StringUtils.isEqual(userType, "xy")) {
				String jxjlb = request.getParameter("jxjlb");
				if (StringUtils.isNull(jxjlb) || StringUtils.isEqual(jxjlb, "1")) {
					jxjList = dao.getList("select distinct jxjdm,jxjmc from jxjdmb where xydm=? order by jxjdm", new String[]{dao.getOneRs("select xgbdm from xtszb", new String[]{}, "xgbdm")}, new String[]{"jxjdm", "jxjmc"});
				} else {
					jxjList = dao.getList("select distinct jxjdm,jxjmc from jxjdmb where xydm=? order by jxjdm", new String[]{session.getAttribute("userDep").toString()}, new String[]{"jxjdm", "jxjmc"});
				}
			}
			if ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)) {
				String jxjsql = "select jxjdm,jxjmc from jxjdmb where 1=1 ";
				if (!StringUtils.isNull(request.getParameter("xydm"))) {
					jxjsql += " and xydm='" + request.getParameter("xydm") + "'";
				}
				jxjList = dao.getList(jxjsql, new String[]{}, new String[]{"jxjdm", "jxjmc"});
			}
			if ("true".equalsIgnoreCase(isFdy)) {
				request.setAttribute("iscsmz", "fdy");
			}
		}

		request.setAttribute("jxjList", jxjList);// ����רҵ�б�
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);// ���ͼ�¼��
		request.setAttribute("userDepName", dao.getXymcById(bmdm));
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("xq", Base.getJxjsqxq());
		/*if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			request.setAttribute("isnblg", "noview");
		}*/  //lyl 2011-05-25
		priseForm.setZt(DealString.toGBK(zt));
		//���ݴ�ѧ
		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
			return mapping.findForward("gzdx_prise_check");
		}
		return mapping.findForward("success");
	}

	private ActionForward priseChkOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// �鿴���޸ĵ�����¼
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		CommanForm priseChkForm = (CommanForm) form;
		String act = request.getParameter("act");
		String pkVal = request.getParameter("pkVal");
		String pk = "xn||nd||xh||jxjdm";
		String xyyj = request.getParameter("xyyj");
		if(xyyj!=null){
			xyyj.replaceAll("[\\t||\\n]", "");
		}
		String xxyj = request.getParameter("xxyj");
		if(xxyj!=null){
			xxyj.replaceAll("[\\t||\\n]", "");
		}
		String fdyyj = request.getParameter("fdyyj");
		if(fdyyj!=null){
			fdyyj.replaceAll("[\\t||\\n]", "");
		}
		String wyhyj = Base.chgNull(request.getParameter("wyhyj"),"",1);
		String sqly = Base.chgNull(request.getParameter("sqly"),"",1);
		String jfqk = Base.chgNull(request.getParameter("jfqk"),"",1);
		String xxdm = StandardOperation.getXxdm();
		String writeAble = (CheckPower.checkUsrPower(session.getAttribute("userName").toString(), "prise_check.do"))?"yes" : "no";
		String isFdy = session.getAttribute("isFdy").toString();
		String xhtmp = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String nd = request.getParameter("nd");
		String jxjdm = "";
		String zydm = "";
		String[] tempList = new String[4];

		if(xxdm.equals(Globals.XXDM_WHLGDX)){
			return new ActionForward("/pjpy_whlgdx.do?method=showCheckPrise", false);
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)){
			//�㽭��ҵְҵ����ѧԺ
			return new ActionForward("/pjpy_zjsyzy.do?method=showCheckPrise");
		}
		if (Globals.XXDM_SCJZZYJSXY.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/pjpy_scjz_jxjsh.do?pkValue=" + pkVal, false);
		}
		if(Globals.XXDM_LSXY.equalsIgnoreCase(xxdm)){
			//��ˮѧԺ
			return new ActionForward("/pjpyLsxy.do?method=jxjShOne&pkValue=" + pkVal, false);
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {
			String[] pks = dao.getOneRs("select xh||xn||nd pk,bjpddj from view_xsjxjb where xn||nd||xh||jxjdm = ?", new String[]{pkVal}, new String[]{"pk", "bjpddj"});
			String[] zhszcpcj = dao.getOneRs("select dyzf,zyzf,tyzf,zhszcpzf,zhszpm,xn,nd,xh from (select nd,xn,xh,dyzf,zyzf,tyzf,zhszcpzf,(dense_rank() over (partition by bjdm,xn,nd,xq order by zhszcpzf desc nulls last)) zhszpm from view_zjjd_zhszcp) where xh||xn||nd = ? and rownum=1", new String[]{pks != null ? pks[0] : ""}, new String[]{"dyzf", "zyzf", "tyzf", "zhszcpzf", "zhszpm"});
			if (zhszcpcj != null && zhszcpcj.length == 5) {
				request.setAttribute("dyzf", zhszcpcj[0]);
				request.setAttribute("zyzf", zhszcpcj[1]);
				request.setAttribute("tyzf", zhszcpcj[2]);
				request.setAttribute("zhszcpzf", zhszcpcj[3]);

				request.setAttribute("zhszpm", zhszcpcj[4]);
				request.setAttribute("bjpddj", pks!=null ? pks[1] : "");
			}
		}

		/*if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			String[] tmpList = dao.getOneRs("select jxjdm,zydm from view_xsjxjb where xn||nd||xh||jxjdm=?", new String[]{pkVal}, new String[]{"jxjdm", "zydm"});
			if (tmpList != null && tmpList.length == 2) {
				jxjdm = tmpList[0];
				zydm = tmpList[1];
			}
		}*/  //lyl
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
			request.setAttribute("showhzy", "showhzy");
			request.setAttribute("writeAble", writeAble);
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
			request.setAttribute("showjsxx", "showjsxx");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_JGSDX)) {
			request.setAttribute("isjgs", "yes");
		}
	/*	if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			String jqfpm = request.getParameter("jqfpm");
			String zhszcpzfpm = request.getParameter("zhszcpzfpm");
			request.setAttribute("jqfpm", jqfpm);
			request.setAttribute("zhszcpzfpm", zhszcpzfpm);
			request.setAttribute("shownblg", "yes");
		}*/ //lyl
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {
			request.setAttribute("showzjjd", "yes");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
			request.setAttribute("showcsmzxy", "yes");
		}
	
		String sql = "";
		String userType = dao.getUserType(session.getAttribute("userDep").toString());
		String[] colList;
		if ((act == null) || !act.equalsIgnoreCase("save")) {
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				tempList = dao.getOneRs("select xh,xn,nd,xq from view_xsjxjb where xn||nd||xh||jxjdm = ?", new String[]{pkVal}, new String[]{"xh", "xn", "nd", "xq"});
				if (tempList == null) {
					tempList[0]="";
					tempList[1]="";
					tempList[2]="";
					tempList[3]="";
				}
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)) {//�㶫����ѧԺ
				request.setAttribute("isgdby", "yes");
			}
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {//ѧԺ�û�
				if (xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)) {//�㶫����ѧԺ
					sql = "select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,JXJMC,XYSH yesNo,XYSHYJ,TJFLAG,DCJ,ZCJ,TCJ,KYCG,SQLY,DRZW,FDYYJ,XYPSWYHYJ,cjmc,zhpfmc,jfqk,xq,CXCJ from view_xsjxjb where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "JXJMC", "yesNo", "TJFLAG",
							"DCJ", "ZCJ", "TCJ", "KYCG", "SQLY", "DRZW", "XYSHYJ", "FDYYJ", "XYPSWYHYJ","cjmc","zhpfmc","jfqk","xq", "CXCJ" };
				/*}else if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
					sql = "select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,JXJMC,XYSH yesNo,XYSHYJ,TJFLAG,DCJ,ZCJ,TCJ,KYCG,SQLY,DRZW,FDYYJ,XYPSWYHYJ,cjmc,zhpfmc,jfqk,xq,CXCJ,bjdm,zydm,jqf,zhszcpzf from view_xsjxjb where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "JXJMC", "yesNo", "TJFLAG", //lyl
							"DCJ", "ZCJ", "TCJ", "KYCG", "SQLY", "DRZW", "XYSHYJ", "FDYYJ", "XYPSWYHYJ","cjmc","zhpfmc","jfqk","xq", "CXCJ","jqf","zhszcpzf" };
				*/ } else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
					sql = "select "
						+ "a.xn||a.nd||a.xh||a.jxjdm pk"
						+ ",a.ND,a.XN,a.XH,a.XM,a.NJ,a.XYMC,a.ZYMC,a.BJMC,a.XB,a.fdyqm,a.xyqm,a.JXJMC,a.XYSH yesNo,a.XYSHYJ,a.TJFLAG,a.DCJ,a.ZCJ,a.TCJ,a.KYCG,a.SQLY,a.DRZW,a.FDYYJ,a.XYPSWYHYJ,a.cjmc,a.zhpfmc,a.jfqk,(case when a.xq='03' then '��һѧ��' else '�ڶ�ѧ��' end) xq,(select zhszcpcjpm from (select c.xh,c.xn,c.nd,c.xq,c.zhszcpzf,(case when c.zhszcpcjpm is not null then c.zhszcpcjpm else (select to_char(b.zhszcpcjpm) from (select xh,xn,xq,nd,(dense_rank() over(partition by bjdm,xn,xq,nd order by to_number(zhszcpzf) desc nulls last)) zhszcpcjpm from view_zhszcp) b where c.xh=b.xh and c.xn=b.xn and c.xq=b.xq and c.nd=b.nd) end) zhszcpcjpm from view_zhszcp c where c.xh='"+tempList[0]+"' and c.xn='"+tempList[1]+"' and c.nd='"+tempList[2]+"' and c.xq='"+tempList[3]+"')) zhszcpcjpm,a.zhszcpzf,a.pjcj from view_xsjxjb a where "
						+ "a.xn||a.nd||a.xh||a.jxjdm" + "='" + pkVal + "'";
					colList = new String[] { "pk", "ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "JXJMC", "yesNo", "TJFLAG",
							"DCJ", "ZCJ", "TCJ", "KYCG", "SQLY", "DRZW", "fdyqm", "xyqm","XYSHYJ", "FDYYJ", "XYPSWYHYJ","cjmc","zhpfmc","jfqk","xq", "zhszcpcjpm", "zhszcpzf", "pjcj" };
					request.setAttribute("xn||nd||xh||jxjdm", pkVal);
				} else if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
					sql = "select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,JXJMC,XYSH yesNo,XYSHYJ,TJFLAG,DCJ,ZCJ,TCJ,KYCG,SQLY,DRZW,FDYYJ,XYPSWYHYJ,cjmc,zhpfmc,jfqk,xq,xxsh from view_xsjxjb where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "JXJMC", "yesNo", "TJFLAG",
							"DCJ", "ZCJ", "TCJ", "KYCG", "SQLY", "DRZW", "XYSHYJ", "FDYYJ", "XYPSWYHYJ","cjmc","zhpfmc","jfqk","xq","xxsh" };
				} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
					sql = "select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,BZ,XYMC,ZYMC,BJMC,XB,JXJMC,XYSH yesNo,XYSHYJ,TJFLAG,DCJ," +
						"ZCJ,TCJ,KYCG,SQLY,DRZW,FDYYJ,XYPSWYHYJ,cjmc,zhpfmc,jfqk,xq,(select b.cj " +
						"from view_zjcm_zhszcpb b where a.xn=b.xn and a.xq=b.xq and a.xh=b.xh) cj," +
						"(select b.bjpm from view_zjcm_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) zhszcpcjpm from " +
						"view_xsjxjb a where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "JXJMC", "yesNo",
							"TJFLAG", "DCJ", "ZCJ", "TCJ", "KYCG", "SQLY",
							"DRZW", "XYSHYJ", "FDYYJ", "XYPSWYHYJ", "cjmc",
							"zhpfmc", "jfqk", "xq", "BZ", "cj", "zhszcpcjpm" };
				} else if (Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
					sql = "select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,BZ,XYMC,ZYMC,BJMC,XB,JXJMC,XYSH yesNo,XYSHYJ,TJFLAG,DCJ,ZCJ,TCJ,KYCG,SQLY,DRZW,FDYYJ,XYPSWYHYJ,cjmc,zhpfmc,jfqk,xq,(select b.szmc1 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szmc1,(select b.szmc2 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szmc2,(select b.szmc3 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szmc3,(select b.szmc4 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szmc4,(select b.szmc5 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szmc5,(select b.szcj1 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szcj1,(select b.szcj2 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szcj2,(select b.szcj3 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szcj3,(select b.szcj4 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szcj4,(select b.szcj5 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szcj5,(select b.zhszcpcj from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) zhszcpcj,(select b.zhszcpcjpm from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) zhszcpcjpm from view_xsjxjb a where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk,"ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "JXJMC", "yesNo", "TJFLAG",
							"DCJ", "ZCJ", "TCJ", "KYCG", "SQLY", "DRZW", "XYSHYJ", "FDYYJ", "XYPSWYHYJ","cjmc","zhpfmc","jfqk","xq" , "szmc1","szmc2","szmc3","szmc4","szmc5","szcj1","szcj2","szcj3","szcj4","szcj5","zhszcpcj","zhszcpcjpm","BZ"};
				}else if (Globals.XXDM_NBTYZYJSXY.equalsIgnoreCase(xxdm)) {
					sql = "select a.*,(select mc"
						+" from (select xh,xn,xq,(dense_rank() over(partition by zydm,xn order by to_number(zpf) desc nulls last)) mc "
                		+" from view_zhszcp) where xh = a.xh and xn = a.xn) cjmc from (select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,zydm,BZ,XYMC,ZYMC,BJMC,XB,JXJMC,XYSH yesNo,XYSHYJ,TJFLAG,DCJ,ZCJ,TCJ,KYCG,SQLY,DRZW,FDYYJ,XYPSWYHYJ,zhpfmc,jfqk,xq,dykhdj,xstzjkbz," +
							"(select zpf from zhszcp b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) zpf " +
							"from view_xsjxjb a where "
						+ pk + "='" + pkVal + "') a";
					colList = new String[] { pk,"ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "JXJMC", "yesNo", "TJFLAG",
							"DCJ", "ZCJ", "TCJ", "KYCG", "SQLY", "DRZW", "XYSHYJ", "FDYYJ", "XYPSWYHYJ","cjmc","zhpfmc","jfqk","xq" , "BZ","zpf","dykhdj","xstzjkbz"};
				}else {
					sql = "select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,BZ,XYMC,ZYMC,BJMC,XB,JXJMC,XYSH yesNo,XYSHYJ,TJFLAG,DCJ,ZCJ,TCJ,KYCG,SQLY,DRZW,FDYYJ,XYPSWYHYJ,cjmc,zhpfmc,jfqk,xq,(select zpf from zhszcp b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) zpf from view_xsjxjb a where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk,"ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "JXJMC", "yesNo", "TJFLAG",
							"DCJ", "ZCJ", "TCJ", "KYCG", "SQLY", "DRZW", "XYSHYJ", "FDYYJ", "XYPSWYHYJ","cjmc","zhpfmc","jfqk","xq" , "BZ","zpf"};
				}
				if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
					request.setAttribute("iscsmz", "xy");
				}
			} else {//ѧУ�û�
				if (xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)) {//�㶫����ѧԺ
					sql = "select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,JXJMC,XXSH yesNo,XXSHYJ,TJFLAG,DCJ,ZCJ,TCJ,KYCG,SQLY,DRZW ,FDYYJ,XYPSWYHYJ,XYSHYJ,cjmc,zhpfmc,jfqk,xq,CXCJ from view_xsjxjb where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "JXJMC", "yesNo", "TJFLAG",
							"DCJ", "ZCJ", "TCJ", "KYCG", "SQLY", "DRZW", "XYSHYJ", "FDYYJ", "XYPSWYHYJ", "XXSHYJ","cjmc","zhpfmc","jfqk","xq", "CXCJ" };
				/*} else if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
					sql = "select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,JXJMC,XXSH yesNo,XXSHYJ,TJFLAG,DCJ,ZCJ,TCJ,KYCG,SQLY,DRZW ,FDYYJ,XYPSWYHYJ,XYSHYJ,cjmc,zhpfmc,jfqk,xq,bjdm,zydm,jqf,zhszcpzf from view_xsjxjb where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "JXJMC", "yesNo", "TJFLAG", //lyl
							"DCJ", "ZCJ", "TCJ", "KYCG", "SQLY", "DRZW", "XYSHYJ", "FDYYJ", "XYPSWYHYJ", "XXSHYJ","cjmc","zhpfmc","jfqk","xq" ,"jqf","zhszcpzf"};
				*/} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
					sql = "select "
						+ "a.xn||a.nd||a.xh||a.jxjdm pk"
						+ ",a.ND,a.XN,a.XH,a.XM,a.NJ,a.XYMC,a.ZYMC,a.BJMC,a.XB,a.JXJMC,a.fdyqm,a.xyqm,a.XXSH yesNo,a.XXSHYJ,a.TJFLAG,a.DCJ,a.ZCJ,a.TCJ,a.KYCG,a.SQLY,a.DRZW ,a.FDYYJ,a.XYPSWYHYJ,a.XYSHYJ,a.cjmc,a.zhpfmc,a.jfqk,(case when a.xq='03' then '��һѧ��' else '�ڶ�ѧ��'  end) xq,(select zhszcpcjpm from (select c.xh,c.xn,c.nd,c.xq,c.zhszcpzf,(case when c.zhszcpcjpm is not null then c.zhszcpcjpm else (select to_char(b.zhszcpcjpm) from (select xh,xn,xq,nd,(dense_rank() over(partition by bjdm,xn,xq,nd order by to_number(zhszcpzf) desc nulls last)) zhszcpcjpm from view_zhszcp) b where c.xh=b.xh and c.xn=b.xn and c.xq=b.xq and c.nd=b.nd) end) zhszcpcjpm from view_zhszcp c where c.xh='"+tempList[0]+"' and c.xn='"+tempList[1]+"' and c.nd='"+tempList[2]+"' and c.xq='"+tempList[3]+"')) zhszcpcjpm,a.zhszcpzf,a.pjcj from view_xsjxjb a where "
						+ "a.xn||a.nd||a.xh||a.jxjdm" + "='" + pkVal + "'";
					colList = new String[] { "pk", "ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "JXJMC", "yesNo", "TJFLAG",
							"DCJ", "ZCJ", "TCJ", "KYCG", "SQLY", "DRZW","fdyqm", "xyqm",  "XYSHYJ", "FDYYJ", "XYPSWYHYJ", "XXSHYJ","cjmc","zhpfmc","jfqk","xq", "zhszcpcjpm" ,"zhszcpzf", "pjcj"};
					request.setAttribute("xn||nd||xh||jxjdm", pkVal);
				} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
					sql = "select "
						+ pk
						+ ",BZ,ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,JXJMC,XXSH yesNo,XXSHYJ,TJFLAG," +
						"DCJ,ZCJ,TCJ,KYCG,SQLY,DRZW ,FDYYJ,XYPSWYHYJ,XYSHYJ,cjmc,zhpfmc," +
						"jfqk,xq,(select b.cj from view_zjcm_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) cj" +
						",(select b.bjpm from view_zjcm_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) zhszcpcjpm from view_xsjxjb a where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "JXJMC", "yesNo", "TJFLAG",
							"DCJ", "ZCJ", "TCJ", "KYCG", "SQLY",
							"DRZW", "XYSHYJ", "FDYYJ", "XYPSWYHYJ", "XXSHYJ",
							"cjmc", "zhpfmc", "jfqk", "xq", "BZ" ,"cj","zhszcpcjpm"};
				} else if (Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
					sql = "select "
						+ pk
						+ ",BZ,ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,JXJMC,XXSH yesNo,XXSHYJ,TJFLAG,DCJ,ZCJ,TCJ,KYCG,SQLY,DRZW ,FDYYJ,XYPSWYHYJ,XYSHYJ,cjmc,zhpfmc,jfqk,xq,(select b.szmc1 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szmc1,(select b.szmc2 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szmc2,(select b.szmc3 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szmc3,(select b.szmc4 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szmc4,(select b.szmc5 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szmc5,(select b.szcj1 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szcj1,(select b.szcj2 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szcj2,(select b.szcj3 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szcj3,(select b.szcj4 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szcj4,(select b.szcj5 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szcj5,(select b.zhszcpcj from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) zhszcpcj,(select b.zhszcpcjpm from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) zhszcpcjpm from view_xsjxjb a where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "JXJMC", "yesNo", "TJFLAG",
							"DCJ", "ZCJ", "TCJ", "KYCG", "SQLY", "DRZW", "XYSHYJ", "FDYYJ", "XYPSWYHYJ", "XXSHYJ","cjmc","zhpfmc","jfqk","xq" ,"szmc1","szmc2","szmc3","szmc4","szmc5","szcj1","szcj2","szcj3","szcj4","szcj5","zhszcpcj","zhszcpcjpm", "BZ"};
				} else if (Globals.XXDM_NBTYZYJSXY.equalsIgnoreCase(xxdm)) {
					sql = "select a.*,(select mc"
						+" from (select xh,xn,xq,(dense_rank() over(partition by zydm,xn order by to_number(zpf) desc nulls last)) mc "
                		+" from view_zhszcp) where xh = a.xh and xn = a.xn) cjmc from (select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,zydm,BZ,XYMC,ZYMC,BJMC,XB,JXJMC,XXSH yesNo,XXSHYJ,TJFLAG,DCJ,ZCJ,TCJ,KYCG,SQLY,DRZW,FDYYJ,XYPSWYHYJ,zhpfmc,jfqk,xq," +
							"(select zpf from zhszcp b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) zpf,xyshyj " +
							"from view_xsjxjb a where "
						+ pk + "='" + pkVal + "') a";
					colList = new String[] { pk,"ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "JXJMC", "yesNo", "TJFLAG",
							"DCJ", "ZCJ", "TCJ", "KYCG", "SQLY", "DRZW", "XYSHYJ", "FDYYJ", "XYPSWYHYJ","cjmc","zhpfmc","jfqk","xq" , "BZ","zpf","XXSHYJ"};
				}else {
					sql = "select "
						+ pk
						+ ",BZ,ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,JXJMC,XXSH yesNo,XXSHYJ,TJFLAG,DCJ,ZCJ,TCJ,KYCG,SQLY,DRZW ,FDYYJ,XYPSWYHYJ,XYSHYJ,cjmc,zhpfmc,jfqk,xq,(select b.zpf from zhszcp b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) zpf from view_xsjxjb a where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "JXJMC", "yesNo", "TJFLAG",
							"DCJ", "ZCJ", "TCJ", "KYCG", "SQLY", "DRZW", "XYSHYJ", "FDYYJ", "XYPSWYHYJ", "XXSHYJ","cjmc","zhpfmc","jfqk","xq" ,"BZ","zpf"};
				}
			}

			//��ȡӢ��ɼ�
		/*	if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				String[] xhandzymc = dao.getOneRs("select xh,zymc from view_xsjxjb where " 
						+ pk + "='" + pkVal + "'" , new String[]{}, new String[]{"xh", "zymc"});
				String sCet4 = "";
				String sCet6 = "";
				String sSql4 = "";
				String sSql6 = "";
				if (xhandzymc != null && xhandzymc.length == 2) {
					if (xhandzymc[1].indexOf("Ӣ��") != -1) {//Ӣ��רҵ
						sSql4 = "select cj from (select xh,djksmc,max(cj) cj from xsdjksb where djksmc like (select djksmc from djksdmb where dymc = 'PCET4') group by xh,djksmc) where xh = ?";
						sSql6 = "select cj from (select xh,djksmc,max(cj) cj from xsdjksb where djksmc like (select djksmc from djksdmb where dymc = 'PCET6') group by xh,djksmc) where xh = ?";
					} else {//��Ӣ��רҵ
						sSql4 = "select cj from (select xh,djksmc,max(cj) cj from xsdjksb where djksmc like (select djksmc from djksdmb where dymc = 'CET4') group by xh,djksmc) where xh = ?";
						sSql6 = "select cj from (select xh,djksmc,max(cj) cj from xsdjksb where djksmc like (select djksmc from djksdmb where dymc = 'CET6') group by xh,djksmc) where xh = ?";
					}
					sCet4 = dao.getOneRs(sSql4, new String[]{xhandzymc[0]}, "cj");
					sCet6 = dao.getOneRs(sSql6, new String[]{xhandzymc[0]}, "cj");
				}
				request.setAttribute("cet4", sCet4);//Ӣ���ļ�
				request.setAttribute("cet6", sCet6);//Ӣ������
			}*/ //lyl

			if (xxdm.equalsIgnoreCase(Globals.XXDM_JGSDX)) {//����ɽ��ѧҪ���ѧ����������Ƿ�ʦ��
				sql = "select "
					+ pk
					+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,JXJMC,XXSH yesNo,XXSHYJ,TJFLAG,DCJ,ZCJ,TCJ,KYCG,SQLY,DRZW ,FDYYJ,XYPSWYHYJ,XYSHYJ,cjmc,zhpfmc,jfqk,xq,sfsf,hjxjqk from view_xsjxjb where "
					+ pk + "='" + pkVal + "'";
				colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ",
						"XYMC", "ZYMC", "BJMC", "XB", "JXJMC", "yesNo", "TJFLAG",
						"DCJ", "ZCJ", "TCJ", "KYCG", "SQLY", "DRZW", "XYSHYJ", "FDYYJ", "XYPSWYHYJ", "XXSHYJ","cjmc","zhpfmc","jfqk","xq","sfsf","hjxjqk" };
			}

			if (StringUtils.isEqual(isFdy, "true")) {//����Ǹ���Ա
				if (xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY) ) {//����Ƽ�ѧԺ,���ս�����ҵѧԺ
					sql = "select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,JXJMC,FDYSH yesNo,XXSHYJ,TJFLAG,DCJ,ZCJ,TCJ,KYCG,SQLY,DRZW ,FDYYJ,XYPSWYHYJ,XYSHYJ,cjmc,zhpfmc,jfqk,xq from view_xsjxjb where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "JXJMC", "yesNo", "TJFLAG",
							"DCJ", "ZCJ", "TCJ", "KYCG", "SQLY", "DRZW","cjmc","zhpfmc","xq" };

					String[] rs = dao.getOneRs(sql, new String[] {}, colList);
					for (int i = 0; i < colList.length; i++) {
						rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? ""
								: rs[i];
						request.setAttribute(colList[i], rs[i]);
					}
					//	priseChkForm.setYesNo(rs[11]);
					PjpyCqkjxyActionForm pjpyform = new PjpyCqkjxyActionForm();
					pjpyform.setYesNo(rs[11]);
					request.setAttribute("pjpyForm", pjpyform);
					request.setAttribute("userType", userType);
					request.setAttribute("chkList", dao.getChkList(3));
					return new ActionForward("/pjpy/cqlkxy/pjpy_cqkjxy_fdysh.jsp",false);
				}//end if
//				��ɳ�����������
				if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
					sql = "select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,JXJMC,FDYSH yesNo,XYSHYJ,TJFLAG,DCJ,ZCJ,TCJ,KYCG,SQLY,DRZW,FDYYJ,XYPSWYHYJ,cjmc,zhpfmc,jfqk,xq,xysh,xxsh from view_xsjxjb where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "JXJMC", "yesNo", "TJFLAG",
							"DCJ", "ZCJ", "TCJ", "KYCG", "SQLY", "DRZW", "XYSHYJ", "FDYYJ", "XYPSWYHYJ","cjmc","zhpfmc","jfqk","xq","xysh","xxsh" };

					request.setAttribute("iscsmz", "fdy");
				}
				if (Globals.XXDM_CDFZGDZKXX.equalsIgnoreCase(xxdm) || Globals.XXDM_SCJZZYJSXY.equalsIgnoreCase(xxdm)) {
					sql = "select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,BZ,XYMC,ZYMC,BJMC,XB,JXJMC,FDYSH yesNo,XYSHYJ,TJFLAG,DCJ,ZCJ,TCJ,KYCG,SQLY,DRZW,FDYYJ,XYPSWYHYJ,cjmc,zhpfmc,jfqk,xq from view_xsjxjb where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk,"ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "JXJMC", "yesNo", "TJFLAG",
							"DCJ", "ZCJ", "TCJ", "KYCG", "SQLY", "DRZW", "XYSHYJ", "FDYYJ", "XYPSWYHYJ","cjmc","zhpfmc","jfqk","xq" , "BZ"};
				}
				if (Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
					sql = "select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,BZ,XYMC,ZYMC,BJMC,XB,JXJMC,FDYSH yesNo,XYSHYJ,TJFLAG,DCJ,ZCJ,TCJ,KYCG,SQLY,DRZW,FDYYJ,XYPSWYHYJ,cjmc,zhpfmc,jfqk,xq,(select b.szmc1 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szmc1,(select b.szmc2 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szmc2,(select b.szmc3 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szmc3,(select b.szmc4 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szmc4,(select b.szmc5 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szmc5,(select b.szcj1 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szcj1,(select b.szcj2 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szcj2,(select b.szcj3 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szcj3,(select b.szcj4 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szcj4,(select b.szcj5 from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) szcj5,(select b.zhszcpcj from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) zhszcpcj,(select b.zhszcpcjpm from ahjg_szfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) zhszcpcjpm from view_xsjxjb a where "
						+ pk+ "='" + pkVal + "'";
					colList = new String[] { pk,"ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "JXJMC", "yesNo", "TJFLAG",
							"DCJ", "ZCJ", "TCJ", "KYCG", "SQLY", "DRZW", "XYSHYJ", "FDYYJ", "XYPSWYHYJ","cjmc","zhpfmc","jfqk","xq" ,"szmc1","szmc2","szmc3","szmc4","szmc5","szcj1","szcj2","szcj3","szcj4","szcj5","zhszcpcj","zhszcpcjpm", "BZ"};
				}
				//�㽭��ýѧԺ
				if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
					sql = "select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,BZ,XYMC,ZYMC,BJMC,XB,JXJMC,FDYSH yesNo,XYSHYJ,TJFLAG,DCJ," +
						"ZCJ,TCJ,KYCG,SQLY,DRZW,FDYYJ,XYPSWYHYJ,cjmc,zhpfmc,jfqk,xq," +
						"(select b.cj from view_zjcm_zhszcpb b where a.xh=b.xh and a.xn=b.xn and " +
						"a.xq=b.xq) cj,(select b.bjpm from view_zjcm_zhszcpb b where a.xh=b.xh " +
						"and a.xn=b.xn and a.xq=b.xq) zhszcpcjpm from view_xsjxjb a where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk,"ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "JXJMC", "yesNo", "TJFLAG",
							"DCJ", "ZCJ", "TCJ", "KYCG", "SQLY", "DRZW", "XYSHYJ", "FDYYJ", "XYPSWYHYJ","cjmc","zhpfmc","jfqk","xq" , "BZ", "cj", "zhszcpcjpm"};
				}
				if (Globals.XXDM_NBTYZYJSXY.equalsIgnoreCase(xxdm)) {
					sql = "select a.*,(select mc"
						+" from (select xh,xn,xq,(dense_rank() over(partition by zydm,xn order by to_number(zpf) desc nulls last)) mc "
                		+" from view_zhszcp) where xh = a.xh and xn = a.xn) cjmc from (select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,zydm,BZ,XYMC,ZYMC,BJMC,XB,JXJMC,FDYSH yesNo,XYSHYJ,TJFLAG,DCJ,ZCJ,TCJ,KYCG,SQLY,DRZW,FDYYJ,XYPSWYHYJ,zhpfmc,jfqk,xq,dykhdj,xstzjkbz," +
							"(select zpf from zhszcp b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) zpf " +
							"from view_xsjxjb a where "
						+ pk + "='" + pkVal + "') a";
					colList = new String[] { pk,"ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "JXJMC", "yesNo", "TJFLAG",
							"DCJ", "ZCJ", "TCJ", "KYCG", "SQLY", "DRZW", "XYSHYJ", "FDYYJ", "XYPSWYHYJ","cjmc","zhpfmc","jfqk","xq" , "BZ","zpf","dykhdj","xstzjkbz"};
				}
			}//end if
			String[] rs = dao.getOneRs(sql, new String[] {}, colList);
			if (rs == null) {
				rs = new String[colList.length];	                
			}
			for (int i = 0; i < colList.length; i++) {
				rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? ""
						: rs[i];
				request.setAttribute(colList[i], rs[i]);


			}
			priseChkForm.setYesNo(rs == null ? "" : rs[11]);
			request.setAttribute("tgres", priseChkForm.getYesNo());
			request.setAttribute("userType", userType);
			request.setAttribute("chkList", dao.getChkList(3));
			priseChkForm.setBz(rs[rs.length-1]);
			request.setAttribute("pkVal", pkVal);
			return mapping.findForward("success");
		}

		String yesNo = request.getParameter("yesNo");
		yesNo = DealString.toGBK(yesNo);
		yesNo = !StringUtils.isNull(yesNo) ? yesNo : "";
		String[] tmp = new String[]{};
		String sResult = "";//��˽��

		String fdyqm = DealString.toGBK(request.getParameter("fdyqm"));
		String xyqm = DealString.toGBK(request.getParameter("xyqm"));
		String cjmc = DealString.toGBK(request.getParameter("cjmc"));
		String zhpfmc = DealString.toGBK(request.getParameter("zhpfmc"));
		cjmc = StringUtils.isNull(cjmc) ? "" : cjmc.trim();
		zhpfmc = StringUtils.isNull(zhpfmc) ? "" : zhpfmc.trim();
		//ѧԺ��У���
		String currTime = DateUtils.getTime();
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
			currTime = DateUtils.getYear() + DateUtils.getMonth() + DateUtils.getDayOfMonth();
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {//ѧԺ���
				sql = "update xsjxjb set xysh=?,xyshyj=?,fdyyj=?,xypswyhyj=?,jfqk=?,sqly=?,cjmc='"+cjmc+"',zhpfmc='"+zhpfmc+"',fdyqm='"+fdyqm+"',xyqm='"+xyqm+"' where " + pk + "=?";
				tmp = new String[]{yesNo,xyyj,fdyyj,wyhyj,jfqk,sqly,pkVal};
				if (StringUtils.isEqual(yesNo, "ͨ��")) {
					//�жϽ�ѧ��ͨ�������Ƿ���������
					String[] jxjsqxnndxq = dao.getOneRs("select jxjsqxn,jxjsqnd,jxjsqxq from xtszb where rownum=1", new String[]{}, new String[]{"jxjsqxn", "jxjsqnd", "jxjsqxq"});
					if (jxjsqxnndxq== null || jxjsqxnndxq[0]==null) {
						request.setAttribute("sResult", "�����ڲ�������-����ѧ�������ý�ѧ������ѧ��,���,ѧ��!");
						request.setAttribute("result", "no");
						return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
					}
					String[] xyzybjjxjdmnj = dao.getOneRs("select xydm,zydm,bjdm,jxjdm,nj from view_xsjxjb where " + pk + "=?", new String[]{pkVal}, new String[]{"xydm", "zydm", "bjdm","jxjdm", "nj"});
					if (StringUtils.isEqual(xyzybjjxjdmnj[3], "028")
							|| StringUtils.isEqual(xyzybjjxjdmnj[3], "029")
							|| StringUtils.isEqual(xyzybjjxjdmnj[3], "030")
							|| StringUtils.isEqual(xyzybjjxjdmnj[3], "031")) {
						String xzrs = "";//��������
//						String fpfs = "";//���䷽ʽ
						String tgrs = "";//��ͨ������
						if (xyzybjjxjdmnj != null && xyzybjjxjdmnj.length == 5) {
							xzrs = dao
							.getOneRs(
									"select jxjrs from xyjxjrs where xn=? and nd=? and bmdm=? and bmlb=? and jxjdm=?",
									new String[] { jxjsqxnndxq[0],
											jxjsqxnndxq[1], xyzybjjxjdmnj[2],
											"bjdm", xyzybjjxjdmnj[3] }, "jxjrs");

						}
						tgrs = dao
						.getOneRs(
								"select count(*) num from view_xsjxjb where (jxjdm='028' or jxjdm='029' or jxjdm='030' or jxjdm='031') and xn=? and nd=? and nj=? and bjdm=? and xysh='ͨ��' ",
								new String[] { jxjsqxnndxq[0],
										jxjsqxnndxq[1],
										xyzybjjxjdmnj[4],
										xyzybjjxjdmnj[2] }, "num");
						if (!StringUtils.isNull(xzrs)) {
							int iXzrs = Integer.parseInt(xzrs.trim());
							int iTgrs = StringUtils.isNull(tgrs) ? 0 : Integer.parseInt(tgrs.trim());
							if (iTgrs >= iXzrs) {
								String sTmp = String.format("��ʾ����ѧ��������������Ϊ %1$s ��,�����ͨ�� %2$s ��!", xzrs, tgrs);
								request.setAttribute("sResult", sTmp);
								request.setAttribute("result", "no");
								return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
							}
						}
					} else {
						String xzrs = "";//��������
//						String fpfs = "";//���䷽ʽ
						String tgrs = "";//��ͨ������
						if (xyzybjjxjdmnj != null && xyzybjjxjdmnj.length == 5) {
							xzrs = dao
							.getOneRs(
									"select jxjrs from xyjxjrs where xn=? and nd=? and bmdm=? and bmlb=? and jxjdm=?",
									new String[] { jxjsqxnndxq[0],
											jxjsqxnndxq[1], xyzybjjxjdmnj[2],
											"bjdm", xyzybjjxjdmnj[3] }, "jxjrs");

						}
						tgrs = dao.getOneRs("select count(*) num from view_xsjxjb where jxjdm=? and xn=? and nd=? and nj=? and bjdm=? and xysh='ͨ��' ", new String[]{xyzybjjxjdmnj[3],jxjsqxnndxq[0],jxjsqxnndxq[1],xyzybjjxjdmnj[4],xyzybjjxjdmnj[2]}, "num");
						if (!StringUtils.isNull(xzrs)) {
							int iXzrs = Integer.parseInt(xzrs.trim());
							int iTgrs = StringUtils.isNull(tgrs) ? 0 : Integer.parseInt(tgrs.trim());
							if (iTgrs >= iXzrs) {
								String sTmp = String.format("��ʾ����ѧ�������༶��������Ϊ %1$s ��,�����ͨ�� %2$s ��!", xzrs, tgrs);
								request.setAttribute("sResult", sTmp);
								request.setAttribute("result", "no");
								return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
							}
						}
					}
				}
			} else {//ѧУ���

				sql = "update xsjxjb set xxsh=?,xxshyj=? where " + pk + "=?";
				tmp = new String[]{yesNo,xxyj,pkVal};
				if (StringUtils.isEqual(yesNo, "ͨ��")) {
					String[] xyzybjjxjdmnj = dao.getOneRs("select xydm,zydm,bjdm,jxjdm,nj from view_xsjxjb where " + pk + "=?", new String[]{pkVal}, new String[]{"xydm", "zydm", "bjdm","jxjdm", "nj"});
					String[] jxjsqxnndxq = dao.getOneRs("select jxjsqxn,jxjsqnd,jxjsqxq from xtszb where rownum=1", new String[]{}, new String[]{"jxjsqxn", "jxjsqnd", "jxjsqxq"});
					if (jxjsqxnndxq== null || jxjsqxnndxq[0]==null) {
						request.setAttribute("sResult", "�����ڲ�������-����ѧ�������ý�ѧ������ѧ��,���,ѧ��!");
						request.setAttribute("result", "no");
						return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
					}
					if (StringUtils.isEqual(xyzybjjxjdmnj[3], "028")
							|| StringUtils.isEqual(xyzybjjxjdmnj[3], "029")
							|| StringUtils.isEqual(xyzybjjxjdmnj[3], "030")
							|| StringUtils.isEqual(xyzybjjxjdmnj[3], "031")) {//���ȡ��ѧʵ����ѧ��ı���
//						�жϽ�ѧ��ͨ�������Ƿ���������
						String xzrs = "";//��������
//						String fpfs = "";//���䷽ʽ
						String tgrs = "";//��ͨ������
						if (xyzybjjxjdmnj != null && xyzybjjxjdmnj.length == 5) {
							xzrs = dao
							.getOneRs(
									"select jxjrs from xyjxjrs where xn=? and nd=? and bmdm=? and bmlb=? and jxjdm=?",
									new String[] { jxjsqxnndxq[0],
											jxjsqxnndxq[1], xyzybjjxjdmnj[2],
											"bjdm", "028" }, "jxjrs");

						}
						tgrs = dao
						.getOneRs(
								"select count(*) num from view_xsjxjb where (jxjdm='028' or jxjdm='029' or jxjdm='030' or jxjdm='031') and xn=? and nd=? and nj=? and bjdm=? and xysh='ͨ��' and xxsh='ͨ��'",
								new String[] {
										jxjsqxnndxq[0], jxjsqxnndxq[1],
										xyzybjjxjdmnj[4],
										xyzybjjxjdmnj[2] }, "num");
						if (!StringUtils.isNull(xzrs)) {
							int iXzrs = Integer.parseInt(xzrs.trim());
							int iTgrs = StringUtils.isNull(tgrs) ? 0 : Integer.parseInt(tgrs.trim());
							if (iTgrs >= iXzrs) {
								String sTmp = String.format("��ʾ����ѧ��������������Ϊ %1$s ��,�����ͨ�� %2$s ��!", xzrs, tgrs);
								request.setAttribute("sResult", sTmp);
								request.setAttribute("result", "no");
								return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
							}
						}

					} else {

						//�жϽ�ѧ��ͨ�������Ƿ���������
						String xzrs = "";//��������
//						String fpfs = "";//���䷽ʽ
						String tgrs = "";//��ͨ������
						if (xyzybjjxjdmnj != null && xyzybjjxjdmnj.length == 5) {
							xzrs = dao
							.getOneRs(
									"select jxjrs from xyjxjrs where xn=? and nd=? and bmdm=? and bmlb=? and jxjdm=?",
									new String[] { jxjsqxnndxq[0],
											jxjsqxnndxq[1], xyzybjjxjdmnj[2],
											"bjdm", xyzybjjxjdmnj[3] }, "jxjrs");

						}
						tgrs = dao
						.getOneRs(
								"select count(*) num from view_xsjxjb where jxjdm=? and xn=? and nd=? and nj=? and bjdm=? and xysh='ͨ��' and xxsh='ͨ��'",
								new String[] { xyzybjjxjdmnj[3],
										jxjsqxnndxq[0], jxjsqxnndxq[1],
										xyzybjjxjdmnj[4],
										xyzybjjxjdmnj[2] }, "num");
						if (!StringUtils.isNull(xzrs)) {
							int iXzrs = Integer.parseInt(xzrs.trim());
							int iTgrs = StringUtils.isNull(tgrs) ? 0 : Integer.parseInt(tgrs.trim());
							if (iTgrs >= iXzrs) {
								String sTmp = String.format("��ʾ����ѧ�������༶��������Ϊ %1$s ��,�����ͨ�� %2$s ��!", xzrs, tgrs);
								request.setAttribute("sResult", sTmp);
								request.setAttribute("result", "no");
								return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
							}
						}

					}
				} 
			}
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {//���ս���
			String xh = request.getParameter("xh");
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				if ("true".equalsIgnoreCase(isFdy)) {//����Ա���

					String[] jxjsqxnndxq = dao.getOneRs("select jxjsqxn,jxjsqnd,jxjsqxq from xtszb where rownum=1", new String[]{}, new String[]{"jxjsqxn", "jxjsqnd", "jxjsqxq"});
					if (jxjsqxnndxq== null || jxjsqxnndxq[0]==null) {
						request.setAttribute("sResult", "�����ڲ�������-����ѧ�������ý�ѧ������ѧ��,���,ѧ��!");
						request.setAttribute("result", "no");
						return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
					}
					sql = "update xsjxjb set fdysh=?,fdyyj=?,jfqk=?,sqly=? where " + pk + "=?";
					tmp = new String[]{yesNo,fdyyj,jfqk,sqly,pkVal};
					if (StringUtils.isEqual(yesNo, "ͨ��")) {
						//�жϽ�ѧ��ͨ�������Ƿ���������

						String[] xyzybjjxjdmnj = dao.getOneRs("select xydm,zydm,bjdm,jxjdm,nj from view_xsjxjb where " + pk + "=?", new String[]{pkVal}, new String[]{"xydm", "zydm", "bjdm","jxjdm", "nj"});
						if (xyzybjjxjdmnj==null) {
							xyzybjjxjdmnj = new String[5];
						}

						String xzrs = "";//��������
//						String fpfs = "";//���䷽ʽ
						String tgrs = "";//��ͨ������
						if (xyzybjjxjdmnj != null && xyzybjjxjdmnj.length == 5) {
							xzrs = dao
							.getOneRs(
									"select jxjrs from xyjxjrs where xn=? and nd=? and bmdm=? and bmlb=? and jxjdm=?",
									new String[] { jxjsqxnndxq[0],
											jxjsqxnndxq[1], xyzybjjxjdmnj[2],
											"bjdm", xyzybjjxjdmnj[3] }, "jxjrs");

						}
						tgrs = dao.getOneRs("select count(*) num from view_xsjxjb where jxjdm=? and xn=? and nd=? and nj=? and bjdm=? and fdysh='ͨ��' ", new String[]{xyzybjjxjdmnj[3],jxjsqxnndxq[0],jxjsqxnndxq[1],xyzybjjxjdmnj[4],xyzybjjxjdmnj[2]}, "num");
						if (!StringUtils.isNull(xzrs)) {
							int iXzrs = Integer.parseInt(xzrs);
							int iTgrs = StringUtils.isNull(tgrs) ? 0 : Integer.parseInt(tgrs);
							if (iTgrs >= iXzrs && iXzrs!=0) {
								String sTmp = String.format("��ʾ����ѧ�������༶��������Ϊ %1$s ��,�����ͨ�� %2$s ��!", xzrs, tgrs);
								request.setAttribute("sResult", sTmp);
								request.setAttribute("result", "no");
								return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
							}
						}


					}
//					����������Ŀ���ۺϷ�
					String isExists = dao
					.getOneRs(
							"select count(*) num from ahjg_szfb where xh=? and xn=? and xq=?",
							new String[] { xh, jxjsqxnndxq[0],
									jxjsqxnndxq[2] }, "num");//�������Ƿ����
					if (!StringUtils.isNull(isExists) && !"0".equalsIgnoreCase(isExists)) {//����
						dao.runUpdate("update ahjg_szfb set szmc1=?,szmc2=?,szmc3=?,szmc4=?,szmc5=?" +
								",szcj1=?,szcj2=?,szcj3=?,szcj4=?,szcj5=?,zhszcpcj=?,zhszcpcjpm=? " +
								"where xh=? and xn=? and xq=?",
								new String[] {
								DealString
								.toGBK(request
										.getParameter("szmc1")),
										DealString
										.toGBK(request
												.getParameter("szmc2")),
												DealString
												.toGBK(request
														.getParameter("szmc3")),
														DealString
														.toGBK(request
																.getParameter("szmc4")),
																DealString
																.toGBK(request
																		.getParameter("szmc5")),
																		request
																		.getParameter("szcj1"),
																		request
																		.getParameter("szcj2"),
																		request
																		.getParameter("szcj3"),
																		request
																		.getParameter("szcj4"),
																		request
																		.getParameter("szcj5"),
																		request
																		.getParameter("zhszcpcj"),
																		request
																		.getParameter("zhszcpcjpm"),
																		xh, jxjsqxnndxq[0],
																		jxjsqxnndxq[2] });
					} else {//����
						dao.runUpdate("insert into ahjg_szfb (xh,xn,xq,szmc1,szmc2,szmc3,szmc4,szmc5,szcj1,szcj2,szcj3,szcj4,szcj5,zhszcpcj,zhszcpcjpm) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
								new String[] {xh,jxjsqxnndxq[0],jxjsqxnndxq[2],
								DealString
								.toGBK(request
										.getParameter("szmc1")),
										DealString
										.toGBK(request
												.getParameter("szmc2")),
												DealString
												.toGBK(request
														.getParameter("szmc3")),
														DealString
														.toGBK(request
																.getParameter("szmc4")),
																DealString
																.toGBK(request
																		.getParameter("szmc5")),
																		request
																		.getParameter("szcj1"),
																		request
																		.getParameter("szcj2"),
																		request
																		.getParameter("szcj3"),
																		request
																		.getParameter("szcj4"),
																		request
																		.getParameter("szcj5"),
																		request
																		.getParameter("zhszcpcj"),
																		request
																		.getParameter("zhszcpcjpm")});
					}
				} else {//ѧԺ���
					sql = "update xsjxjb set xysh=?,xyshyj=?,xypswyhyj=?,jfqk=?,sqly=? where " + pk + "=?";
					tmp = new String[]{yesNo,xyyj,wyhyj,jfqk,sqly,pkVal};
					if (StringUtils.isEqual(yesNo, "ͨ��")) {
						//�жϽ�ѧ��ͨ�������Ƿ���������
						String[] jxjsqxnndxq = dao.getOneRs("select jxjsqxn,jxjsqnd,jxjsqxq from xtszb where rownum=1", new String[]{}, new String[]{"jxjsqxn", "jxjsqnd", "jxjsqxq"});
						if (jxjsqxnndxq== null || jxjsqxnndxq[0]==null) {
							request.setAttribute("sResult", "�����ڲ�������-����ѧ�������ý�ѧ������ѧ��,���,ѧ��!");
							request.setAttribute("result", "no");
							return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
						}
						String[] xyzybjjxjdmnj = dao.getOneRs("select xydm,zydm,bjdm,jxjdm,nj from view_xsjxjb where " + pk + "=?", new String[]{pkVal}, new String[]{"xydm", "zydm", "bjdm","jxjdm", "nj"});

						String xzrs = "";//��������
//						String fpfs = "";//���䷽ʽ
						String tgrs = "";//��ͨ������
						if (xyzybjjxjdmnj != null && xyzybjjxjdmnj.length == 5) {
							xzrs = dao
							.getOneRs(
									"select jxjrs from xyjxjrs where xn=? and nd=? and bmdm=? and bmlb=? and jxjdm=?",
									new String[] { jxjsqxnndxq[0],
											jxjsqxnndxq[1], xyzybjjxjdmnj[2],
											"bjdm", xyzybjjxjdmnj[3] }, "jxjrs");

						}
						tgrs = dao.getOneRs("select count(*) num from view_xsjxjb where jxjdm=? and xn=? and nd=? and nj=? and bjdm=? and xysh='ͨ��' ", new String[]{xyzybjjxjdmnj[3],jxjsqxnndxq[0],jxjsqxnndxq[1],xyzybjjxjdmnj[4],xyzybjjxjdmnj[2]}, "num");
						if (!StringUtils.isNull(xzrs)) {
							int iXzrs = Integer.parseInt(xzrs);
							int iTgrs = StringUtils.isNull(tgrs) ? 0 : Integer.parseInt(tgrs);
							if (iTgrs >= iXzrs && iXzrs!=0) {
								String sTmp = String.format("��ʾ����ѧ�������༶��������Ϊ %1$s ��,�����ͨ�� %2$s ��!", xzrs, tgrs);
								request.setAttribute("sResult", sTmp);
								request.setAttribute("result", "no");
								return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
							}
						}

					}
				}

			} else {//ѧУ���

				sql = "update xsjxjb set xxsh=?,xxshyj=? where " + pk + "=?";
				tmp = new String[]{yesNo,xxyj,pkVal};
				if (StringUtils.isEqual(yesNo, "ͨ��")) {
					String[] xyzybjjxjdmnj = dao.getOneRs("select xydm,zydm,bjdm,jxjdm,nj from view_xsjxjb where " + pk + "=?", new String[]{pkVal}, new String[]{"xydm", "zydm", "bjdm","jxjdm", "nj"});
					String[] jxjsqxnndxq = dao.getOneRs("select jxjsqxn,jxjsqnd,jxjsqxq from xtszb where rownum=1", new String[]{}, new String[]{"jxjsqxn", "jxjsqnd", "jxjsqxq"});
					if (jxjsqxnndxq== null || jxjsqxnndxq[0]==null) {
						request.setAttribute("sResult", "�����ڲ�������-����ѧ�������ý�ѧ������ѧ��,���,ѧ��!");
						request.setAttribute("result", "no");
						return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
					}

					//�жϽ�ѧ��ͨ�������Ƿ���������
					String xzrs = "";//��������
//					String fpfs = "";//���䷽ʽ
					String tgrs = "";//��ͨ������
					if (xyzybjjxjdmnj != null && xyzybjjxjdmnj.length == 5) {
						xzrs = dao
						.getOneRs(
								"select jxjrs from xyjxjrs where xn=? and nd=? and bmdm=? and bmlb=? and jxjdm=?",
								new String[] { jxjsqxnndxq[0],
										jxjsqxnndxq[1], xyzybjjxjdmnj[2],
										"bjdm", xyzybjjxjdmnj[3] }, "jxjrs");

					}
					tgrs = dao
					.getOneRs(
							"select count(*) num from view_xsjxjb where jxjdm=? and xn=? and nd=? and nj=? and bjdm=? and xxsh='ͨ��'",
							new String[] { xyzybjjxjdmnj[3],
									jxjsqxnndxq[0], jxjsqxnndxq[1],
									xyzybjjxjdmnj[4],
									xyzybjjxjdmnj[2] }, "num");
					if (!StringUtils.isNull(xzrs)) {
						int iXzrs = Integer.parseInt(xzrs);
						int iTgrs = StringUtils.isNull(tgrs) ? 0 : Integer.parseInt(tgrs);
						if (iTgrs >= iXzrs && iXzrs!=0) {
							String sTmp = String.format("��ʾ����ѧ�������༶��������Ϊ %1$s ��,�����ͨ�� %2$s ��!", xzrs, tgrs);
							request.setAttribute("sResult", sTmp);
							request.setAttribute("result", "no");
							return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
						}
					}

				} 
			}


		}else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {//�㽭����  ����ѧУ���Ҳ���������

			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {//ѧԺ���
				sql = "update xsjxjb set xysh=?,xyshyj=?,fdyyj=?,xypswyhyj=?,jfqk=?,sqly=? where " + pk + "=?";
				tmp = new String[]{yesNo,xyyj,fdyyj,wyhyj,jfqk,sqly,pkVal};
				if (StringUtils.isEqual(yesNo, "ͨ��")) {
					//�жϽ�ѧ��ͨ�������Ƿ���������
					String[] jxjsqxnndxq = dao.getOneRs("select jxjsqxn,jxjsqnd,jxjsqxq from xtszb where rownum=1", new String[]{}, new String[]{"jxjsqxn", "jxjsqnd", "jxjsqxq"});
					if (jxjsqxnndxq== null || jxjsqxnndxq[0]==null) {
						request.setAttribute("sResult", "�����ڲ�������-����ѧ�������ý�ѧ������ѧ��,���,ѧ��!");
						request.setAttribute("result", "no");
						return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
					}
					String[] xyzybjjxjdmnj = dao.getOneRs("select xydm,zydm,bjdm,jxjdm,nj from view_xsjxjb where " + pk + "=?", new String[]{pkVal}, new String[]{"xydm", "zydm", "bjdm","jxjdm", "nj"});

					String xzrs = "";//��������
					String fpfs = "bjdm";//���䷽ʽ
					String tgrs = "";//��ͨ������
					if (xyzybjjxjdmnj != null && xyzybjjxjdmnj.length == 5) {
						xzrs = dao
						.getOneRs(
								"select jxjrs from xyjxjrs where xn=? and nd=? and bmdm=? and bmlb=? and jxjdm=?",
								new String[] { jxjsqxnndxq[0],
										jxjsqxnndxq[1], xyzybjjxjdmnj[2],
										fpfs, xyzybjjxjdmnj[3] }, "jxjrs");

					}
					tgrs = dao.getOneRs("select count(*) num from view_xsjxjb where jxjdm=? and xn=? and nd=? and nj=? and bjdm=? and xysh='ͨ��' ", new String[]{xyzybjjxjdmnj[3],jxjsqxnndxq[0],jxjsqxnndxq[1],xyzybjjxjdmnj[4],xyzybjjxjdmnj[2]}, "num");
					if (!StringUtils.isNull(xzrs)) {
						int iXzrs = Integer.parseInt(xzrs.trim());
						int iTgrs = StringUtils.isNull(tgrs) ? 0 : Integer.parseInt(tgrs.trim());
						if (iTgrs >= iXzrs) {//�Ƿ񳬱�
							String sTmp = String.format("��ʾ����ѧ��������������Ϊ %1$s ��,�����ͨ�� %2$s ��!", xzrs, tgrs);
							request.setAttribute("sResult", sTmp);
							request.setAttribute("result", "no");
							return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
						}
					}
				}
			} else {//ѧУ���
				sql = "update xsjxjb set xxsh=?,xxshyj=? where " + pk + "=?";
				tmp = new String[]{yesNo,xxyj,pkVal};
				if (StringUtils.isEqual(yesNo, "ͨ��")) {
					String[] xyzybjjxjdmnj = dao.getOneRs("select xydm,zydm,bjdm,jxjdm,nj from view_xsjxjb where " + pk + "=?", new String[]{pkVal}, new String[]{"xydm", "zydm", "bjdm","jxjdm", "nj"});
					String[] jxjsqxnndxq = dao.getOneRs("select jxjsqxn,jxjsqnd,jxjsqxq from xtszb where rownum=1", new String[]{}, new String[]{"jxjsqxn", "jxjsqnd", "jxjsqxq"});
					if (jxjsqxnndxq== null || jxjsqxnndxq[0]==null) {
						request.setAttribute("sResult", "�����ڲ�������-����ѧ�������ý�ѧ������ѧ��,���,ѧ��!");
						request.setAttribute("result", "no");
						return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
					}

					//�жϽ�ѧ��ͨ�������Ƿ���������
					String xzrs = "";//��������
					String fpfs = "bjdm";//���䷽ʽ
					String tgrs = "";//��ͨ������
					if (xyzybjjxjdmnj != null && xyzybjjxjdmnj.length == 5) {
						xzrs = dao
						.getOneRs(
								"select jxjrs from xyjxjrs where xn=? and nd=? and bmdm=? and bmlb=? and jxjdm=?",
								new String[] { jxjsqxnndxq[0],
										jxjsqxnndxq[1], xyzybjjxjdmnj[2],
										fpfs, xyzybjjxjdmnj[3] }, "jxjrs");

					}
					tgrs = dao
					.getOneRs(
							"select count(*) num from view_xsjxjb where jxjdm=? and xn=? and nd=? and nj=? and bjdm=? and xysh='ͨ��' and xxsh='ͨ��'",
							new String[] { xyzybjjxjdmnj[3],
									jxjsqxnndxq[0], jxjsqxnndxq[1],
									xyzybjjxjdmnj[4],
									xyzybjjxjdmnj[2] }, "num");
					if (!StringUtils.isNull(xzrs)) {
						int iXzrs = Integer.parseInt(xzrs.trim());
						int iTgrs = StringUtils.isNull(tgrs) ? 0 : Integer.parseInt(tgrs.trim());
						if (iTgrs >= iXzrs) {//�Ƿ񳬱�
							String sTmp = String.format("��ʾ����ѧ��������������Ϊ %1$s ��,�����ͨ�� %2$s ��!", xzrs, tgrs);
							request.setAttribute("sResult", sTmp);
							request.setAttribute("result", "no");
							return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
						}
					}

				}
			} 

		} else{//�Ǻ�ְԺѧԺ��У���
			if (StringUtils.isEqual(isFdy, "true")) {//��ɳ��������Ա���
				if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
					sql = "update xsjxjb set fdysh=?,fdyyj=? where " + pk + "=?";
					tmp = new String[]{yesNo,fdyyj,pkVal};
				} else if (Globals.XXDM_CDFZGDZKXX.equalsIgnoreCase(xxdm) || Globals.XXDM_SCJZZYJSXY.equalsIgnoreCase(xxdm)) {
					sql = "update xsjxjb set fdysh=?,fdyyj=? where " + pk + "=?";
					tmp = new String[]{yesNo,fdyyj,pkVal};
				} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)
						|| Globals.XXDM_NBTYZYJSXY.equalsIgnoreCase(xxdm)) {
					sql = "update xsjxjb set fdysh=?,fdyyj=? where " + pk + "=?";
					tmp = new String[]{yesNo,fdyyj,pkVal};
				} else {
					sql = "update xsjxjb set xysh=?,xyshyj=?,fdyyj=?,xypswyhyj=? where " + pk + "=?";
					tmp = new String[]{yesNo,xyyj,fdyyj,wyhyj,pkVal};
				}
			} else {
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {//ѧԺ���
					if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {//��ɳ����ѧԺ���
						sql = "update xsjxjb set xysh=?,xyshyj=?,xypswyhyj=? where " + pk + "=?";
						tmp = new String[]{yesNo,xyyj,wyhyj,pkVal};
					} /*else if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY) && StringUtils.isEqual(yesNo, "ͨ��")) {//�������������
						//�������ʱ���жϸ����Ƿ��м��������ѧ����Ƿ��г�רҵ��������
						String jxjfl = dao.getOneRs("select jxjfl from jxjdmb where jxjdm = (select jxjdm from view_xsjxjb where xn||nd||xh||jxjdm=?)", new String[]{pkVal}, "jxjfl");
						jxjfl = !StringUtils.isNull(jxjfl) ? jxjfl : "";
						//����ѧ����ѧ�����
						if ( StringUtils.isEqual(jxjfl, "����ѧ����ѧ��")) {
							//�Ƿ��м��
							String num = dao.getOneRs("select count(*) num from view_xsjxjb where xn||nd||xh=? and xysh='ͨ��' and xxsh='ͨ��' and jxjmc in (select jxjmc from jxjdmb where jxjfl='���ѧ��')", new String[]{xn + nd + xhtmp}, "num");
							int temp = !StringUtils.isNull(num) ? Integer.parseInt(num) : 0;
							//�Ƿ�רҵ��������
							String jxjrs = dao.getOneRs("select jxjrs from xyjxjrs where xn||nd=? and bmlb=? and bmdm=? and jxjdm=?", new String[]{xn + nd, "zydm", zydm, jxjdm}, "jxjrs");
							String shtgrs = dao.getOneRs("select count(*) num from xsjxjb where xn||nd||jxjdm=? and xysh='ͨ��' and xxsh='ͨ��' ", new String[]{xn + nd + jxjdm}, "num");
							int tmprs = !StringUtils.isNull(jxjrs) ? Integer.parseInt(jxjrs) : 0;
							int tmptgrs = !StringUtils.isNull(shtgrs) ? Integer.parseInt(shtgrs) : 0;

							if (((tmprs == 0) && temp==0) || (temp ==0 && (tmprs > tmptgrs))) {//��������
								sql = "update xsjxjb set xysh=?,xyshyj=?,fdyyj=?,xypswyhyj=? where " + pk + "=?";
								tmp = new String[]{yesNo,xyyj,fdyyj,wyhyj,pkVal};
							} else {
								sResult = "������ѡ��ѧ��ʱ�в�������δ���ϣ�\nԭ������ǲ����Ͻ�ѧ���ù涨�����ͨ�������ѳ�רҵ������������ȷ�ϣ�";
								request.setAttribute("sResult", sResult);
								request.setAttribute("result", "no");
								return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
							}
						} else if (StringUtils.isEqual(jxjfl, "���ѧ��")) {//���ѧ�����
							//�Ƿ��м��
							String num = dao.getOneRs("select count(*) num from view_xsjxjb where xn||nd||xh=? and xysh='ͨ��' and xxsh='ͨ��' and jxjmc in (select jxjmc from jxjdmb where jxjfl='����ѧ����ѧ��')", new String[]{xn + nd + xhtmp}, "num");
							int temp = !StringUtils.isNull(num) ? Integer.parseInt(num) : 0;
							//�Ƿ�רҵ��������
							String jxjrs = dao.getOneRs("select jxjrs from xyjxjrs where xn||nd=? and bmlb=? and bmdm=? and jxjdm=?", new String[]{xn + nd, "zydm", zydm, jxjdm}, "jxjrs");
							String shtgrs = dao.getOneRs("select count(*) num from xsjxjb where xn||nd||jxjdm=? and xysh='ͨ��' and xxsh='ͨ��' ", new String[]{xn + nd + jxjdm}, "num");
							int tmprs = !StringUtils.isNull(jxjrs) ? Integer.parseInt(jxjrs) : 0;
							int tmptgrs = !StringUtils.isNull(shtgrs) ? Integer.parseInt(shtgrs) : 0;

							if (((tmprs == 0) && temp==0) || (temp ==0 && (tmprs > tmptgrs))) {//��������
								sql = "update xsjxjb set xysh=?,xyshyj=?,fdyyj=?,xypswyhyj=? where " + pk + "=?";
								tmp = new String[]{yesNo,xyyj,fdyyj,wyhyj,pkVal};
							}else {
								sResult = "������ѡ��ѧ��ʱ�в�������δ���ϣ�\nԭ������ǲ����Ͻ�ѧ���ù涨�����ͨ�������ѳ�רҵ������������ȷ�ϣ�";
								request.setAttribute("sResult", sResult);
								request.setAttribute("result", "no");
								return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
							}
						} else {//������ѧ��
							//�Ƿ�רҵ��������
							String jxjrs = dao.getOneRs("select jxjrs from xyjxjrs where xn||nd=? and bmlb=? and bmdm=? and jxjdm=?", new String[]{xn + nd, "zydm", zydm, jxjdm}, "jxjrs");
							String shtgrs = dao.getOneRs("select count(*) num from xsjxjb where xn||nd||jxjdm=? and xysh='ͨ��' and xxsh='ͨ��' ", new String[]{xn + nd + jxjdm}, "num");
							int tmprs = !StringUtils.isNull(jxjrs) ? Integer.parseInt(jxjrs) : 0;
							int tmptgrs = !StringUtils.isNull(shtgrs) ? Integer.parseInt(shtgrs) : 0;
							if ((tmprs == 0) || (tmprs > tmptgrs)) {
								sql = "update xsjxjb set xysh=?,xyshyj=?,fdyyj=?,xypswyhyj=? where " + pk + "=?";
								tmp = new String[]{yesNo,xyyj,fdyyj,wyhyj,pkVal};
							}else {
								sResult = "������ѡ��ѧ��ʱ�в�������δ���ϣ�\nԭ������ǲ����Ͻ�ѧ���ù涨�����ͨ�������ѳ�רҵ������������ȷ�ϣ�";
								request.setAttribute("sResult", sResult);
								request.setAttribute("result", "no");
								return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
							}
						} //lyl
					} */else {
						sql = "update xsjxjb set xysh=?,xyshyj=?,fdyyj=?,xypswyhyj=? where " + pk + "=?";
						tmp = new String[]{yesNo,xyyj,fdyyj,wyhyj,pkVal};
					}
				} else  {//ѧУ���
					/*if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY) && StringUtils.isEqual(yesNo, "ͨ��")) {//�������������
						//�������ʱ���жϸ����Ƿ��м��������ѧ����Ƿ��г�רҵ��������
						String jxjfl = dao.getOneRs("select jxjfl from jxjdmb where jxjdm = (select jxjdm from view_xsjxjb where xn||nd||xh||jxjdm=?)", new String[]{pkVal}, "jxjfl");
						jxjfl = !StringUtils.isNull(jxjfl) ? jxjfl : "";
						//����ѧ����ѧ�����
						if (StringUtils.isEqual(jxjfl, "����ѧ����ѧ��")) {
							//�Ƿ��м��
							String num = dao.getOneRs("select count(*) num from view_xsjxjb where xn||nd||xh=? and xysh='ͨ��' and xxsh='ͨ��' and jxjmc in (select jxjmc from jxjdmb where jxjfl='���ѧ��')", new String[]{xn + nd + xhtmp}, "num");
							int temp = !StringUtils.isNull(num) ? Integer.parseInt(num) : 0;
							//�Ƿ�רҵ��������
							String jxjrs = dao.getOneRs("select jxjrs from xyjxjrs where xn||nd=? and bmlb=? and bmdm=? and jxjdm=?", new String[]{xn + nd, "zydm", zydm, jxjdm}, "jxjrs");
							String shtgrs = dao.getOneRs("select count(*) num from xsjxjb where xn||nd||jxjdm=? and xysh='ͨ��' and xxsh='ͨ��' ", new String[]{xn + nd + jxjdm}, "num");
							int tmprs = !StringUtils.isNull(jxjrs) ? Integer.parseInt(jxjrs) : 0;
							int tmptgrs = !StringUtils.isNull(shtgrs) ? Integer.parseInt(shtgrs) : 0;

							if (((tmprs == 0) && temp==0) || (temp ==0 && (tmprs > tmptgrs))) {//��������
								sql = "update xsjxjb set xxsh=?,xxshyj=? where " + pk + "=?";
								tmp = new String[]{yesNo,xxyj,pkVal};
							} else {
								sResult = "������ѡ��ѧ��ʱ�в�������δ���ϣ�\nԭ������ǲ����Ͻ�ѧ���ù涨�����ͨ�������ѳ�רҵ������������ȷ�ϣ�";
								request.setAttribute("sResult", sResult);
								request.setAttribute("result", "no");
								return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
							}
						} else if (StringUtils.isEqual(jxjfl, "���ѧ��")) {//���ѧ�����
							//�Ƿ��м��
							String num = dao.getOneRs("select count(*) num from view_xsjxjb where xn||nd||xh=? and xysh='ͨ��' and xxsh='ͨ��' and jxjmc in (select jxjmc from jxjdmb where jxjfl='����ѧ����ѧ��')", new String[]{xn + nd + xhtmp}, "num");
							int temp = !StringUtils.isNull(num) ? Integer.parseInt(num) : 0;
							//�Ƿ�רҵ��������
							String jxjrs = dao.getOneRs("select jxjrs from xyjxjrs where xn||nd=? and bmlb=? and bmdm=? and jxjdm=?", new String[]{xn + nd, "zydm", zydm, jxjdm}, "jxjrs");
							String shtgrs = dao.getOneRs("select count(*) num from xsjxjb where xn||nd||jxjdm=? and xysh='ͨ��' and xxsh='ͨ��' ", new String[]{xn + nd + jxjdm}, "num");
							int tmprs = !StringUtils.isNull(jxjrs) ? Integer.parseInt(jxjrs) : 0;
							int tmptgrs = !StringUtils.isNull(shtgrs) ? Integer.parseInt(shtgrs) : 0;

							if (((tmprs == 0) && temp==0) || (temp ==0 && (tmprs > tmptgrs))) {//��������
								sql = "update xsjxjb set xxsh=?,xxshyj=? where " + pk + "=?";
								tmp = new String[]{yesNo,xxyj,pkVal};
							} else {
								sResult = "������ѡ��ѧ��ʱ�в�������δ���ϣ�\nԭ������ǲ����Ͻ�ѧ���ù涨�����ͨ�������ѳ�רҵ������������ȷ�ϣ�";
								request.setAttribute("sResult", sResult);
								request.setAttribute("result", "no");
								return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
							}
						} else {//������ѧ��
							//�Ƿ�רҵ��������
							String jxjrs = dao.getOneRs("select jxjrs from xyjxjrs where xn||nd=? and bmlb=? and bmdm=? and jxjdm=?", new String[]{xn + nd, "zydm", zydm, jxjdm}, "jxjrs");
							String shtgrs = dao.getOneRs("select count(*) num from xsjxjb where xn||nd||jxjdm=? and xysh='ͨ��' and xxsh='ͨ��' ", new String[]{xn + nd + jxjdm}, "num");
							int tmprs = !StringUtils.isNull(jxjrs) ? Integer.parseInt(jxjrs) : 0;
							int tmptgrs = !StringUtils.isNull(shtgrs) ? Integer.parseInt(shtgrs) : 0;
							if ((tmprs == 0) || (tmprs > tmptgrs)) {
								sql = "update xsjxjb set xxsh=?,xxshyj=? where " + pk + "=?";
								tmp = new String[]{yesNo,xxyj,pkVal};
							} else {
								sResult = "������ѡ��ѧ��ʱ�в�������δ���ϣ�\nԭ������ǲ����Ͻ�ѧ���ù涨�����ͨ�������ѳ�רҵ������������ȷ�ϣ�";
								request.setAttribute("sResult", sResult);
								request.setAttribute("result", "no");
								return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
							}
						}//lyl
					}else {*/ 
						sql = "update xsjxjb set xxsh=?,xxshyj=? where " + pk + "=?";
						tmp = new String[]{yesNo,xxyj,pkVal};
					//}
				}
			}
		}		
//		int a=0;

		//
		
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
			if (userType.equalsIgnoreCase("xy")) {
				//sql = "update xsjxjb set xysh='"+yesNo+"',xyshyj='"+xyyj+"',fdyyj='"+fdyyj+"',xypswyhyj='"+wyhyj+"',jfqk='"+jfqk+"',sqly='"+sqly+"',cjmc='"+cjmc+"',zhpfmc='"+zhpfmc+"',fdyqm='"+fdyqm+"',xyqm='"+xyqm+"' where " + pk + "='"+pkVal+"'";
				//tmp = new String[]{yesNo,xyyj,fdyyj,wyhyj,jfqk,sqly,pkVal};
				StandardOperation.update("xsjxjb", new String[]{"xysh","xyshyj","fdyyj","xypswyhyj","jfqk","sqly","cjmc","zhpfmc","fdyqm","xyqm","xyshsj"}, new String[]{yesNo,xyyj,fdyyj,wyhyj,jfqk,sqly,cjmc,zhpfmc,fdyqm,xyqm,currTime}, pk, pkVal, request);
			} else {
				//sql = "update xsjxjb set xxsh='"+yesNo+"',xxshyj='"+xxyj+"' where " + pk + "='"+pkVal+"'";
				//tmp = new String[]{yesNo,xxyj,pkVal};
				StandardOperation.update("xsjxjb", new String[]{"xxsh","xxshyj","xxshsj"}, new String[]{yesNo,xxyj,currTime}, pk, pkVal, request);
			}

		} else if (Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
			if ("xy".equalsIgnoreCase(userType)) {
				if ("true".equalsIgnoreCase(isFdy)) {
					//sql = "update xsjxjb set fdysh=?,fdyyj=?,jfqk=?,sqly=? where " + pk + "=?";
					//tmp = new String[]{yesNo,fdyyj,jfqk,sqly,pkVal};
					StandardOperation.update("xsjxjb", new String[]{"fdysh","fdyyj","jfqk","sqly","fdyshsj"}, new String[]{yesNo,fdyyj,jfqk,sqly,currTime}, pk, pkVal, request);
				} else {
					//sql = "update xsjxjb set xysh=?,xyshyj=?,xypswyhyj=?,jfqk=?,sqly=? where " + pk + "=?";
					//tmp = new String[]{yesNo,xyyj,wyhyj,jfqk,sqly,pkVal};
					StandardOperation.update("xsjxjb", new String[]{"xysh","xyshyj","xypswyhyj","jfqk","sqly","xyshsj"}, new String[]{yesNo,xyyj,wyhyj,jfqk,sqly,currTime}, pk, pkVal, request);
				}
			} else {
				//sql = "update xsjxjb set xxsh=?,xxshyj=? where " + pk + "=?";
				//tmp = new String[]{yesNo,xxyj,pkVal};
				StandardOperation.update("xsjxjb", new String[]{"xxsh","xxshyj","xxshsj"}, new String[]{yesNo,xxyj,currTime}, pk, pkVal, request);
			}
		} else if (Globals.XXDM_ZJJDZYJSXY.equalsIgnoreCase(xxdm)) {
			if ("xy".equalsIgnoreCase(userType)) {
				//sql = "update xsjxjb set xysh=?,xyshyj=?,fdyyj=?,xypswyhyj=?,jfqk=?,sqly=? where " + pk + "=?";
				//tmp = new String[]{yesNo,xyyj,fdyyj,wyhyj,jfqk,sqly,pkVal};
				StandardOperation.update("xsjxjb", new String[]{"xysh","xyshyj","fdyyj","xypswyhyj","jfqk","sqly","xyshsj"}, new String[]{yesNo,xyyj,fdyyj,wyhyj,jfqk,sqly,currTime}, pk, pkVal, request);
			} else {
				//sql = "update xsjxjb set xxsh=?,xxshyj=? where " + pk + "=?";
				//tmp = new String[]{yesNo,xxyj,pkVal};
				StandardOperation.update("xsjxjb", new String[]{"xxsh","xxshyj","xxshsj"}, new String[]{yesNo,xxyj,currTime}, pk, pkVal, request);
			}
		} else if (!Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)
				&& !Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)
				&& !Globals.XXDM_CDFZGDZKXX.equalsIgnoreCase(xxdm)
				&& !Globals.XXDM_SCJZZYJSXY.equalsIgnoreCase(xxdm)
				&& !Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)
				&& !Globals.XXDM_NBTYZYJSXY.equalsIgnoreCase(xxdm)) {
			if ("xy".equalsIgnoreCase(userType)) {
				if ("true".equalsIgnoreCase(isFdy)) {
					//sql = "update xsjxjb set xysh=?,xyshyj=?,fdyyj=?,xypswyhyj=? where " + pk + "=?";
					//tmp = new String[]{yesNo,xyyj,fdyyj,wyhyj,pkVal};
					StandardOperation.update("xsjxjb", new String[]{"xysh","xyshyj","fdyyj","xypswyhyj","fdyshsj"}, new String[]{yesNo,xyyj,fdyyj,wyhyj,currTime}, pk, pkVal, request);
				} else {
					sql = "update xsjxjb set xysh=?,xyshyj=?,fdyyj=?,xypswyhyj=? where " + pk + "=?";
					tmp = new String[]{yesNo,xyyj,fdyyj,wyhyj,pkVal};
					StandardOperation.update("xsjxjb", new String[]{"xysh","xyshyj","fdyyj","xypswyhyj","xyshsj"}, new String[]{yesNo,xyyj,fdyyj,wyhyj,currTime}, pk, pkVal, request);
				}
			} else {
				//sql = "update xsjxjb set xxsh=?,xxshyj=? where " + pk + "=?";
				//tmp = new String[]{yesNo,xxyj,pkVal};
				StandardOperation.update("xsjxjb", new String[]{"xxsh","xxshyj","xxshsj"}, new String[]{yesNo,xxyj,currTime}, pk, pkVal, request);
			}
		} else {
			dao.runUpdate(sql, tmp);
		}
		request.setAttribute("result", "view");
		if (StringUtils.isEqual(isFdy, "true")) {
			priseChkForm.setSzmc1(DealString.toGBK(request.getParameter("szmc1")));
			priseChkForm.setSzmc2(DealString.toGBK(request.getParameter("szmc2")));
			priseChkForm.setSzmc3(DealString.toGBK(request.getParameter("szmc3")));
			priseChkForm.setSzmc4(DealString.toGBK(request.getParameter("szmc4")));
			priseChkForm.setSzmc5(DealString.toGBK(request.getParameter("szmc5")));
//			��ɳ�����������
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
			} else if (Globals.XXDM_CDFZGDZKXX.equalsIgnoreCase(xxdm) || Globals.XXDM_SCJZZYJSXY.equalsIgnoreCase(xxdm)) {
				return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
			} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {//�㽭��ý
				return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
			} else {//����ѧУ����Ա���(�������)

			}
		}
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
				return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
			}
//			��ɳ�����������
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
			} 
			else{
				return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
			}
		} else{	
			return new ActionForward("/priseChkOne.do?act=view&pkVal="+pkVal,false);
		}
	}

	private ActionForward priseAutoChk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String xxdm = StandardOperation.getXxdm();
		DAO dao = DAO.getInstance();
		String currXn = "";
		String currNd = "";
//		String currXq = "";
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
			currXn = dao.getConf(2);
			currNd = dao.getConf(3);
		}else{
			currXn = Base.currXn;
			currNd = Base.currNd;
//			currXq = Base.currXq;
		}
//		String dqXn = dao.getConf(0);
//		String dqXq = dao.getConf(1);
//		String dqNd = dao.getConf(4);
		// �Զ����
		String sql = "";
		CommanForm autoChk = (CommanForm) form;
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		userType = (!StringUtils.isNull(userType)) ? userType : "";
		autoChk.setXn(currXn);
		autoChk.setNd(currNd);
		String bmdm = autoChk.getXydm();

		//String pjf = autoChk.getPjf();//���ս����Զ����ƽ����
		//String bjbl = autoChk.getBjbl();//�Զ������ռ�༶����
		if (userType.equalsIgnoreCase("xy")) {
			bmdm = userDep;
		}

		boolean res = false;
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZBDX)){
			currXn = dao.getConf(2);
			currNd = dao.getConf(3);
			sql = "{call ZBDX_PRISEAUTOCHK(?,?,?)}";
			res = dao.runProcuder(sql, new String[] { bmdm, 
					currXn, currNd });
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
			sql = "{call JSXX_PRISEAUTOCHK(?,?,?)}";
			res = dao.runProcuder(sql, new String[] { bmdm, 
					currXn, Base.getJxjsqxq() });
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {//���ս�����ѧ���Զ����
			String[] jxjsqxnxq = dao.getOneRs(
					"select jxjsqxn,jxjsqxq,jxjsqnd from xtszb where rownum=1",
					new String[] {}, new String[] { "jxjsqxn", "jxjsqxq",
					"jxjsqnd" });
			String jxjsqxn = "";//��ѧ������ѧ��
			String jxjsqxq = "";//��ѧ������ѧ��
			String jxjsqnd = "";
			if (jxjsqxnxq != null && jxjsqxnxq.length == 3) {
				jxjsqxn = (!StringUtils.isNull(jxjsqxnxq[0])) ? jxjsqxnxq[0] : "";
				jxjsqxq = (!StringUtils.isNull(jxjsqxnxq[1])) ? jxjsqxnxq[1] : "";
				jxjsqnd = (!StringUtils.isNull(jxjsqxnxq[2])) ? jxjsqxnxq[2] : "";
			}
			String isFdy = request.getSession().getAttribute("isFdy").toString();

			if (StringUtils.isEqual(userType, "xy")) {// ѧԺ�û��Զ����
				if ("true".equalsIgnoreCase(isFdy)) {
					String bjdm = request.getParameter("bjdm");
					String jxjdm = request.getParameter("xmdm");
					sql = "{call PROC_AUTOPRISE_AHJG(?,?,?,?,?,?,?)}";
					res = dao.runProcuder(sql, new String[] { bjdm, jxjsqxn,
							jxjsqxq, jxjsqnd, jxjdm,"true","xy" });
				} else {
					//String bjdm = request.getParameter("xydm");
					String jxjdm = request.getParameter("xmdm");
					sql = "{call PROC_AUTOPRISE_AHJG(?,?,?,?,?,?,?)}";
					res = dao.runProcuder(sql, new String[] { bmdm, jxjsqxn,
							jxjsqxq, jxjsqnd, jxjdm,"false","xy" });
				}

			} else if (StringUtils.isEqual(userType, "xx")
					|| StringUtils.isEqual(userType, "admin")) {// ѧУ������Ա�û��Զ����
				//String bjdm = request.getParameter("xydm");
				String jxjdm = request.getParameter("xmdm");
				sql = "{call PROC_AUTOPRISE_AHJG(?,?,?,?,?,?,?)}";
				res = dao.runProcuder(sql, new String[] { bmdm, jxjsqxn,
						jxjsqxq, jxjsqnd, jxjdm,"false","xx" });
			}

		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			//������ѧԺ �Զ���˲��ô洢����,��SQL���ֱ�����
//			String jxjsqxn = "";
		} else {
			sql = "{call PRISEAUTOCHK(?,?,?)}";
			res = dao.runProcuder(sql, new String[] { bmdm, 
					currXn, currNd });
		}
		if (res) {
			request.setAttribute("autoChk", "ok");
		} else {
			request.setAttribute("autoChk", "no");
		}
		// }
		return mapping.findForward("success");
	}

	@SuppressWarnings("unchecked")
	private ActionForward creditCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// ��ʼ��ҳ�棬���ز�ѯ��Ϣ
		CommanForm priseForm = (CommanForm) form;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql���
		String querry = " where 1=1 ";// sql����
		String tips = "";// λ����ʾ��Ϣ
		String tableName = "";// ��ѯ��ϢԴ����Ϊ��ͼ����
		String rsNum = "0";// ���صļ�¼��
		String realTable = "";// ����Դ��
		String pk = "";// ����Դ����������ʽΪ���ֶ���||�ֶ���||�ֶ�������
		String writeAble = "yes";// дȨ��
		String dataType = request.getParameter("act");
		String nj = priseForm.getNj();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String userType = dao.getUserType(userDep);
		String yesNo = DealString.toGBK(request.getParameter("yesNo"));
		String xxdm = StandardOperation.getXxdm();
		String isFdy = session.getAttribute("isFdy").toString();
		String xh = request.getParameter("xh");
		String xm = request.getParameter("xm");
		
		//ѧ���û�û��Ȩ�޵�¼
		String uType = request.getSession().getAttribute("userType").toString();
		if (GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(uType)
				|| GlobalsVariable.XTDM_STU.equalsIgnoreCase(uType)) {
			request.setAttribute("message", "����Ȩ���ʸ�ҳ��!");
			return new ActionForward("/prompt.do",false);
		}
		
		/*if (Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/pjpy_nblg_rychsh.do", false);
		}else*/ //lyl 2011-00
			
		if(Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)){//�㽭����ѧ
			return new ActionForward("/zjlgPjpy.do?method=rychCkDefault", false);
		}else if(Globals.XXDM_JHZYJSXY.equalsIgnoreCase(xxdm)){//��ְҵ����ѧԺ
			return new ActionForward("/jhzy_rych.do?method=rychSh", false);
		}else if(Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)){//�㽭��ýѧԺ
			return new ActionForward("/zjcm_rych.do?method=shManage", false);
		}else if(Globals.XXDM_XMLGXY.equalsIgnoreCase(xxdm)){//������ѧԺ
			return new ActionForward("/xmlgpjpy.do?method=rychsh", false);
		}else if(Globals.XXDM_NBTYZYJSXY.equalsIgnoreCase(xxdm)){//������һְҵ����ѧԺ
			return new ActionForward("/nbty_rych.do?method=rychSh",false);
		}else if(Globals.XXDM_MJXY.equalsIgnoreCase(xxdm)){//����ѧԺ
			return new ActionForward("/mjxyRych.do?method=shRych",false);
		}else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
			if ("xy".equalsIgnoreCase(userType)) {
				request.setAttribute("errMsg", "����Ȩ���ʸ�ҳ��,�������Ա��ϵ��");
				return new ActionForward("/errMsg.do", false);
			}
		} else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/pjpy_czxx_rychsh.do", false);
		} else if (Globals.XXDM_CQDZKJ.equals(xxdm)
				 ||Globals.XXDM_CQGYZY.equals(xxdm)) {
			return new ActionForward("/typj.do?method=creditCheck",false);
		} else if(Globals.XXDM_LSXY.equalsIgnoreCase(xxdm))	{//��ˮѧԺ
			return new ActionForward("/typj.do?method=creditCheck",false);
		}
		//String xq = dao.getConf(6);
		//if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
		String xq = request.getParameter("xq");
		//}
		String zt = request.getParameter("zt");
		String stab = request.getParameter("stab");//�Ƚ���������������ƺ����ʱ���ж�
		stab = StringUtils.isNull(stab) ? "" : stab;
		if (StringUtils.isEqual(isFdy, "true")) {
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)) {
				request.setAttribute("cqksisFdy", "yes");
			}
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
			request.setAttribute("showhzy", "showhzy");
			request.setAttribute("chkList", dao.getChkList(3));
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
			request.setAttribute("showjsxx", "showjsxx");
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_XCXY)){
			request.setAttribute("showxcxy", "showxcxy");
		}
	
		sql = "select jxjsqxn,jxjsqnd from xtszb where rownum=1";
		colList = dao.getOneRs(sql, new String[] {}, new String[] { "jxjsqxn",
		"jxjsqnd" });
		String xn = colList[0];
		String nd = colList[1];
		priseForm.setXn(xn);
		priseForm.setNd(nd);
		String bmlb = request.getParameter("bmlb");
		if ((bmlb == null) || !bmlb.equalsIgnoreCase("zydm")) {
			bmlb = "xydm";
		}
		String bmdm = request.getParameter(bmlb);
		String zydm = request.getParameter("zydm");
		String bjdm = request.getParameter("bjdm");
		if (userType.equalsIgnoreCase("xy") && !UserTypePd.isFdy(isFdy)) {
			bmdm = userDep;
		}
		String jxjdm = priseForm.getXmdm();

		if (dataType == null) {
			dataType = "";
		}
		querry += "and xn = '" + xn + "' ";
		if (!Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
			querry += "and nd = '" + nd + "' ";
		}
		
		if (isNull(nj)) {
			querry += "and 1=1 ";
		} else {
			querry += "and nj = '" + nj + "' ";
		}
		if (isNull(jxjdm)) {
			querry += "and 1=1 ";
		} else {
			querry += "and rychdm = '" + jxjdm + "' ";
		}
		if (isNull(bmdm)) {
			querry += "and 1=1 ";
		} else {
			querry += "and xydm = '" + bmdm + "' ";
		}
		if (!StringUtils.isNull(zydm)) {
			querry += " and zydm = '"+zydm+"' ";
		}
		if (!StringUtils.isNull(bjdm)) {
			querry += " and bjdm = '"+bjdm+"' ";
		}
		if (!StringUtils.isNull(xq)) {
			querry += " and xq = '"+xq+"' ";
		}
		if (!StringUtils.isNull(xh)) {
			querry += " and xh='";
			querry += xh;
			querry += "'";
		}
		if (!StringUtils.isNull(xm)) {
			querry += " and xm='";
			querry += xm;
			querry += "'";
		}
		realTable = "xsjxjb";
		pk = "xn||nd||xh||rychdm";
		tips = "�������� - ��� - �����ƺ����";
		tableName = "view_xsrychb";

		if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
			String jxjlbs = request.getParameter("jxjlb");
			if (!StringUtils.isNull(jxjlbs) && StringUtils.isEqual(jxjlbs, "1")) {
				querry += " and jxjxydm='";
				querry += dao.getOneRs("select xgbdm from xtszb", new String[]{}, "xgbdm");
				querry+="' ";
			} else if (!StringUtils.isNull(jxjlbs) && StringUtils.isEqual(jxjlbs, "2")) {
				querry += " and jxjxydm='";
				querry += userDep;
				querry += "' ";
			} else {
				querry += " and 1=1 ";
			}
		}

		if (userType.equalsIgnoreCase("xx")) {//ѧУ�û�
			if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
				if(isNull(yesNo)){
					querry += "and 1=1";
				} else{
					querry += "and xxsh = '" + yesNo + "'";
				}
			}
			if (!StringUtils.isNull(zt)) {
				querry += " and xxsh='" + DealString.toGBK(zt) + "' ";
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)) {
				querry += " and fdysh='ͨ��' ";
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				querry += " and fdysh='ͨ��' ";
			}
			if (Globals.XXDM_CDFZGDZKXX.equalsIgnoreCase(xxdm)) {
				querry += " and fdysh='ͨ��' ";
			}
			if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
				querry += " and fdysh='ͨ��' ";
			}
			colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd", "xh",
					"xm", "bjmc", "rychmc", "xxsh" };
			sql = "select rownum �к�,(case nvl(a.xxsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
				+ " a.* from(select "
				+ pk
				+ " ����,a.* from "
				+ tableName
				+ " a" + querry + " and xysh='ͨ��' order by xxsh desc) a";
		/*	if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd", "xh",
						"xm", "bjmc", "rychmc", "dcj", "zcj", "tcj","dcjpm", "xxsh" };
				sql = "select rownum �к�,(case nvl(a.xxsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " ����,a.xn,a.nd,a.xh,a.xm,a.bjmc,a.rychmc,a.bjdm,a.dcj,a.zcj,a.tcj,dense_rank() over (partition by a.bjdm order by a.dcj desc nulls last) dcjpm,a.xxsh from "
					+ tableName
					+ " a" + querry + " and xysh='ͨ��' order by xxsh desc) a";
			}*/ //lyl 2011-05-25
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd" ,"xq","xh",
						"xm", "bjmc", "rychmc","zhpfmc","cjmc","fdyqm","xyqm","xysh", "xxsh" };
				sql = "select rownum �к�,(case nvl(a.xxsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ " a.xn,a.����,a.fdyqm,a.rychdm,a.xydm,a.zydm,a.bjdm,a.xq,a.xyqm,a.cjmc,a.zhpfmc,a.nd,a.xh,a.xm,a.bjmc,a.xysh,a.rychmc,a.xxsh from(select "
					+ pk
					+ " ����,a.xn,a.rychdm,a.zydm,a.bjdm,a.xydm,a.fdyqm,a.xyqm,a.zhpfmc,a.xq,a.nd,a.xh,a.xm,a.bjmc,a.xysh,a.rychmc,a.cjmc,a.zhszcpzf,a.xxsh from "
					+ tableName
					+ " a" + querry + " order by xxsh desc) a";
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
				colList = new String[] { "bgcolor", "����", "�к�", "xn",
						"xm", "bjmc", "rychmc", "sxzzddszf", "kxwhszf", "sxlxszf", "sjlxcxf","ynyszhszcpzf","xxsh" };
				sql = "select rownum �к�,(case nvl(a.xxsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " ����,a.* from "
					+ tableName
					+ " a" + querry + " and xysh='ͨ��' order by xxsh desc) a";
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				colList = new String[] { "bgcolor", "����", "�к�", "nd",
						"xm", "bjmc", "rychmc", "jxjlb","fdysh", "xysh", "xxsh" };
			}
			//�㽭��ý
			if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
				colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd", "xh",
						"xm", "bjmc", "rychmc", "cj", "bjpm", "xxsh" };
				sql = "select rownum �к�,(case nvl(a.xxsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " ����,a.*,(select b.cj from view_zjcm_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) cj," +
					"(select b.bjpm from view_zjcm_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) bjpm from "
					+ tableName
					+ " a" + querry + " and xysh='ͨ��' order by xxsh desc) a";
			}
			if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {				
				
				colList = new String[] { "pk", "bgcolor", "r", "xh",
						 "xm", "bjmc", "xn", "xycpf", "zhbxf", "xwbxf", "tcbxf", "zf", "bjpm","cjgk", "cfgk", "rychmc", "xxsh" };
				sql = "select a.*, rownum r from (select * from (select a.*,b.bjpm from (select a.*,(case when gk >0 then '#CCCCCC' when cf >0 then '#DDDDDD' else '' end) bgcolor," +
						"(case when gk > 0 then '��' else '��' end) cjgk," +
						"(case when cf > 0 then '��' else '��' end) cfgk from (select xh||xn||rychdm pk,xh,(select xm from view_xsjbxx b where a.xh=b.xh) xm,xn,(select nj from view_xsjbxx b where a.xh=b.xh) nj,(select xydm from view_xsjbxx b where a.xh=b.xh) xydm,(select zydm from view_xsjbxx b where a.xh=b.xh) zydm,(select bjdm from view_xsjbxx b where a.xh=b.xh) bjdm,(select bjmc from view_xsjbxx b where a.xh=b.xh) bjmc,rychdm," +
						"(select (nvl(case when instr(to_char(b.xycpf),'.',1,1)=1 then '0'||to_char(b.xycpf) when instr(to_char(b.xycpf),'-',1,1)=1 and instr(to_char(b.xycpf),'.',2,1)=2 then replace(b.xycpf,'-','-0') else to_char(b.xycpf) end, '0')) xycpf from gzdx_zhszcpb b where a.xh=b.xh and a.xn=b.xn) xycpf," +
						"(select (nvl(case when instr(to_char(b.zhbxf),'.',1,1)=1 then '0'||to_char(b.zhbxf) when instr(to_char(b.zhbxf),'-',1,1)=1 and instr(to_char(b.zhbxf),'.',2,1)=2 then replace(b.zhbxf,'-','-0') else to_char(b.zhbxf) end, '0')) zhbxf from gzdx_zhszcpb b where a.xh=b.xh and a.xn=b.xn) zhbxf," +
						"(select (nvl(case when instr(to_char(b.xwbxf),'.',1,1)=1 then '0'||to_char(b.xwbxf) when instr(to_char(b.xwbxf),'-',1,1)=1 and instr(to_char(b.xwbxf),'.',2,1)=2 then replace(b.xwbxf,'-','-0') else to_char(b.xwbxf) end, '0')) xwbxf from gzdx_zhszcpb b where a.xh=b.xh and a.xn=b.xn) xwbxf," +
						"(select (nvl(case when instr(to_char(b.tcbxf),'.',1,1)=1 then '0'||to_char(b.tcbxf) when instr(to_char(b.tcbxf),'-',1,1)=1 and instr(to_char(b.tcbxf),'.',2,1)=2 then replace(b.tcbxf,'-','-0') else to_char(b.tcbxf) end, '0')) tcbxf from gzdx_zhszcpb b where a.xh=b.xh and a.xn=b.xn) tcbxf," +
						"(select (nvl(case when instr(to_char(b.zf),'.',1,1)=1 then '0'||to_char(b.zf) when instr(to_char(b.zf),'-',1,1)=1 and instr(to_char(b.zf),'.',2,1)=2 then replace(b.zf,'-','-0') else to_char(b.zf) end, '0')) zf from gzdx_zhszcpb b where a.xh=b.xh and a.xn=b.xn) zf," +
						"(select rychmc from rychdmb b where a.rychdm=b.rychdm) rychmc,xxsh," +
						"(select count(b.xh) gk from cjb b where a.xh=b.xh and a.xn=b.xn and (b.cj < 60 or b.bkcj is not null or b.cxcj is not null)) gk," +
						"(select count(b.xh) cf from wjcfb b where a.xh=b.xh and a.xn=b.xn and b.cfwh is not null) cf from xsrychb a) a) a left join (select a.xh,a.xn,(rank() over (partition by xn,bjdm order by zf desc nulls last)) bjpm,b.bjdm from gzdx_zhszcpb a,view_xsjbxx b where a.xh=b.xh) b on a.xh=b.xh and a.xn=b.xn) a order by gk,cf,bjpm) a"
						+ querry;
			}
		} else {//ѧԺ
			if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
				if(isNull(yesNo)){
					querry += "and 1=1";
				} else{
					querry += "and xysh = '" + yesNo + "'";
				}
			}
			if (!StringUtils.isNull(zt)) {
				querry += " and xysh='" + DealString.toGBK(zt) + "' ";
			}
			if (!UserTypePd.isFdy(isFdy)) {//����Ա������β���Ҫ����ѧԺ�б�ֵ				
				priseForm.setXydm(userDep);
			}
			colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd", "xh",
					"xm", "bjmc", "rychmc", "xysh" };
			if (StringUtils.isEqual(isFdy, "false")) {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)) {
					querry += " and fdysh='ͨ��'";
				}
				if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
					querry += " and fdysh='ͨ��'";
				}
				if (xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)) {
					//�人����ѧ
					querry += " and fdysh='ͨ��'";
				}
				if (Globals.XXDM_CDFZGDZKXX.equalsIgnoreCase(xxdm)) {
					querry += " and fdysh='ͨ��' ";
				}
				if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
					querry += " and fdysh='ͨ��' ";
				}
			}
			sql = "select rownum �к�,(case when nvl(a.xysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
				+ "a.* from(select "
				+ pk
				+ " ����,a.* from "
				+ tableName
				+ " a"
				+ querry
				+ " and xydm='"
				+ userDep
				+ "' order by xysh desc) a";
		/*	if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd", "xh",
						"xm", "bjmc", "rychmc", "dcj", "zcj", "tcj", "dcjpm","xysh" };
				sql = "select rownum �к�,(case when nvl(a.xysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " ����,a.xn,a.nd,a.xh,a.xm,a.bjmc,a.rychmc,a.dcj,a.zcj,a.tcj,a.bjdm,dense_rank() over (partition by a.bjdm order by a.dcj desc nulls last) dcjpm,a.xysh from  "
					+ tableName
					+ " a"
					+ querry
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a";
			}*/ //lyl
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd","xq", "xh",
						"xm", "bjmc", "rychmc","zhpfmc","cjmc","fdyqm","xyqm", "xysh" };
				sql = "select rownum �к�,(case when nvl(a.xysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.xn,a.����,a.nd,a.rychdm,a.xydm,a.zydm,a.bjdm,a.fdyqm,a.xq,a.xyqm,a.cjmc,a.zhpfmc,a.xh,a.xm,a.bjmc,a.rychmc,a.xysh from(select "
					+ pk
					+ " ����,a.xn,a.nd,a.xq,a.rychdm,a.zydm,a.bjdm,a.xydm,a.fdyqm,a.xyqm,a.zhpfmc,a.xh,a.xm,a.bjmc,a.rychmc,a.cjmc,a.zhszcpzf,a.xysh from "
					+ tableName
					+ " a"
					+ querry
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a";
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				colList = new String[] {"flag", "bgcolor", "����", "�к�", "nd",
						"xm", "bjmc", "rychmc", "jxjlb","fdysh", "xysh","xxsh" };
				sql = "select (case when a.xxsh='ͨ��' then 'disabled' else '' end) flag,rownum �к�,(case nvl(a.xysh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " ����,a.* from "
					+ tableName
					+ " a"
					+ querry
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a";
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
				colList = new String[] { "bgcolor", "����", "�к�", "xn",
						"xm", "bjmc", "rychmc","sxzzddszf", "kxwhszf", "sxlxszf", "sjlxcxf","ynyszhszcpzf", "xysh" };
				sql = "select rownum �к�,(case when nvl(a.xysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " ����,a.* from "
					+ tableName
					+ " a"
					+ querry
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a";
			}
			//�㽭��ý
			if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
				sql = "select rownum �к�,(case when nvl(a.xysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " ����,a.*,(select b.cj from view_zjcm_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) cj," +
					"(select b.bjpm from view_zjcm_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) bjpm from "
					+ tableName
					+ " a"
					+ querry
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a";
				colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd", "xh",
						"xm", "bjmc", "rychmc", "cj", "bjpm", "xysh" };
			}
		}

		if (StringUtils.isEqual(isFdy, "true")) {//����Ǹ���Ա
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)) {//���������Ƽ�ѧԺ
				colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd", "xh",
						"xm", "bjmc", "rychmc", "fdysh" };
				sql = "select rownum �к�,(case when nvl(a.fdysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " ����,a.* from "
					+ tableName
					+ " a"
					+ querry
					+ " order by fdysh desc) a";
			}
			//��ɳ����
			else if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				if (!StringUtils.isNull(zt)) {
					querry += " and fdysh='" + DealString.toGBK(zt) + "' ";
				}
				querry += " and exists(select 1 from view_fdybbj d where a.bjdm=d.bjdm and d.zgh='"+userName+"') ";
				colList = new String[] {"flag", "bgcolor", "����", "�к�", "nd",
						"xm", "bjmc", "rychmc", "jxjlb","fdysh","xysh","xxsh" };
				sql = "select (case when a.xysh='ͨ��' then 'disabled' when a.xxsh='ͨ��' then 'disabled' else '' end) flag,rownum �к�,(case nvl(a.fdysh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " ����,a.* from "
					+ tableName
					+ " a"
					+ querry
					+ " order by fdysh desc) a";
			}
			//�人��
			else if (xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)) {
				colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd", "xh",
						"xm", "bjmc", "rychmc", "fdysh" };
				querry += " and exists (select 1 from view_fdybbj d where a.bjdm=d.bjdm and d.zgh='"+userName+"')";
				sql = "select rownum �к�,(case when nvl(a.fdysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " ����,a.* from "
					+ tableName
					+ " a"
					+ querry
					+ " order by fdysh desc) a";
			}
			else if (Globals.XXDM_CDFZGDZKXX.equalsIgnoreCase(xxdm)) {
				if (!StringUtils.isNull(zt)) {
					querry += " and fdysh='" + DealString.toGBK(zt) + "' ";
				}
				querry += " and exists(select 1 from view_fdybbj d where a.bjdm=d.bjdm and d.zgh='"+userName+"') ";
				colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd", "xh",
						"xm", "bjmc", "rychmc", "fdysh" };
				sql = "select rownum �к�,(case when nvl(a.fdysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " ����,a.* from "
					+ tableName
					+ " a"
					+ querry
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a";
			}
			//�㽭��ýѧԺ
			else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {

				if (!StringUtils.isNull(zt)) {
					querry += " and fdysh='" + DealString.toGBK(zt) + "' ";
				}
				querry += " and exists(select 1 from view_fdybbj d where a.bjdm=d.bjdm and d.zgh='"+userName+"') ";
				colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd", "xh",
						"xm", "bjmc", "rychmc", "cj", "bjpm", "fdysh" };
				sql = "select rownum �к�,(case when nvl(a.fdysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ "a.* from(select "
					+ pk
					+ " ����,a.*,(select b.cj from view_zjcm_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) cj," +
					"(select b.bjpm from view_zjcm_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) bjpm from "
					+ tableName
					+ " a"
					+ querry
					+ " and xydm='"
					+ userDep
					+ "' order by xysh desc) a";

			} else {
				querry += " and exists(select 1 from view_fdybbj d where a.bjdm=d.bjdm and d.zgh='"+userName+"') ";
				if (!StringUtils.isNull(zt)) {
					querry += " and fdysh='" + DealString.toGBK(zt) + "' ";
				}
				sql += querry;
			}
		}

		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		//��������ѯ��ͷ������������
		/*if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			String[] en = null;
			String[] cn = null;
			topTr = new ArrayList();
			if (userType.equalsIgnoreCase("xx")) {
				en = new String[] { "bgcolor", "����", "�к�", "xn", "nd", "xh",
						"xm", "bjmc", "rychmc", "dcj", "zcj","tcj", "dcjpm","xxsh" };
				cn = new String[]{"bgcolor","����", "�к�","ѧ��","���",
						"ѧ��","����","�༶����","�����ƺ�","�³ɼ�","�ǳɼ�",
						"��ɼ�","�³ɼ��༶����","ѧУ���"};
			} else {
				en = new String[] { "bgcolor", "����", "�к�", "xn", "nd", "xh",
						"xm", "bjmc", "rychmc", "dcj", "zcj", "tcj", "dcjpm","xysh" };
				cn = new String[]{"bgcolor","����", "�к�","ѧ��","���",
						"ѧ��","����","�༶����","�����ƺ�","�³ɼ�","�ǳɼ�",
						"��ɼ�","�³ɼ��༶����","ѧԺ���"};
			} 
			for (int i=0;i<en.length;i++) {
				HashMap<String, String> tmp = new HashMap<String, String>();
				tmp.put("en", en[i]);
				tmp.put("cn", cn[i]);
				topTr.add(tmp);
			}
		}*/ // lyl 
		//�㽭��ý
		if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
			String[] enList = new String[]{};
			String[] cnList = new String[]{};
			topTr = new ArrayList<HashMap<String, String>>();
			if ("xy".equalsIgnoreCase(userType)) {//ѧԺ
				if ("true".equalsIgnoreCase(isFdy)) {
					enList = new String[] { "bgcolor", "����", "�к�", "xn", "nd", "xh",
							"xm", "bjmc", "rychmc", "cj", "bjpm", "fdysh" };
					cnList = new String[] { "bgcolor", "����", "�к�", "ѧ��", "���", "ѧ��",
							"����", "�༶", "�����ƺ�", "�ۺ����ʷ�", "�༶����", "����Ա���" };
				} else {
					enList = new String[] { "bgcolor", "����", "�к�", "xn", "nd", "xh",
							"xm", "bjmc", "rychmc", "cj", "bjpm", "xysh" };
					cnList = new String[] { "bgcolor", "����", "�к�", "ѧ��", "���", "ѧ��",
							"����", "�༶", "�����ƺ�", "�ۺ����ʷ�", "�༶����", Base.YXPZXY_KEY+"���" };
				}
			} else {//��������
				enList = new String[] { "bgcolor", "����", "�к�", "xn", "nd", "xh",
						"xm", "bjmc", "rychmc", "cj", "bjpm", "xxsh" };
				cnList = new String[] { "bgcolor", "����", "�к�", "ѧ��", "���", "ѧ��",
						"����", "�༶", "�����ƺ�", "�ۺ����ʷ�", "�༶����", "ѧУ���" };
			}
			for (int i=0;i<enList.length;i++) {
				HashMap<String, String> tmp = new HashMap<String, String>();
				tmp.put("en", enList[i]);
				tmp.put("cn", cnList[i]);
				topTr.add(tmp);
			}
		}
		//���ݴ�ѧ
		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
			topTr = new ArrayList<HashMap<String, String>>();
			String[] en = new String[] { "pk", "bgcolor", "r", "xh",
					 "xm", "bjmc", "xn", "xycpf", "zhbxf", "xwbxf", "tcbxf", "zf", "bjpm","cjgk", "cfgk", "rychmc", "xxsh" };
			String[] cn = new String[] { "pk", "bgcolor", "�к�", "ѧ��",
					 "����", "�༶", "ѧ��", "ѧҵ������", "�ۺϱ��ַ�", "��Ϊ���ַ�", "ͻ�����ַ�", "�ܷ�", "����","�ҿ�", "����", "����", "���" };
			for (int i=0;i<en.length;i++) {
				HashMap<String, String> tmp = new HashMap<String, String>();
				tmp.put("en", en[i]);
				tmp.put("cn", cn[i]);
				topTr.add(tmp);
			}
		}
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("a")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
			//������������������
		/*	if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				String rychdm = ! StringUtils.isNull(priseForm.getXmdm()) ? priseForm.getXmdm() : "";
				String rychpxbl = dao.getOneRs("select pxbl from rychdmb where rychdm = ?", new String[]{rychdm}, "pxbl");
				String xsrs = dao.getOneRs("select count(*) num from view_xsjbxx", new String[]{}, "num");
				int pxbl = 0;
				int ixsrs = !StringUtils.isNull(xsrs) ? Integer.parseInt(xsrs) : 0;
				if (!StringUtils.isNull(rychpxbl) && rychpxbl.lastIndexOf("%") > 0) {
					pxbl = Integer.parseInt(rychpxbl.substring(0, rychpxbl.length()-1))*ixsrs/100;
				} else if (!StringUtils.isNull(rychpxbl) && rychpxbl.lastIndexOf("%") <= 0) {
					pxbl = Integer.parseInt(rychpxbl)*ixsrs/100;
				} else {
					pxbl = 0;
				}
				if (pxbl > 0) {
					request.setAttribute("pxrs", String.format("%1$d", pxbl));
				} else {
					request.setAttribute("pxrs", "δ����");
				}
			}*/ //lyl
		}
		sql = "select rychdm,rychmc from rychdmb";
		List rychList = dao.getList(sql, new String[] {}, new String[] {
				"rychdm", "rychmc" });
		priseForm.setYesNo(yesNo);


		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("act", dataType);// �������ݲ�ѯ����
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("njList", dao.getNjList());// �����꼶�б�
		request.setAttribute("xnList", dao.getXnndList());// ����ѧ���б�
		request.setAttribute("xqList", dao.getXqList());// ����ѧ���б�
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", dao.getZyList(bmdm));// ����רҵ�б�
		request.setAttribute("bjList", dao.getBjList(bmdm, zydm));
		priseForm.setXq(xq);
//		��ɳ��������Ա��ȡ��Ӧרҵ�༶
		if (StringUtils.isEqual(isFdy, "true")) {
//			if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)
//			|| Globals.XXDM_CDFZGDZKXX.equalsIgnoreCase(xxdm)
//			|| Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {

			String fdyName = session.getAttribute("userName").toString();
			fdyName = !StringUtils.isNull(fdyName) ? fdyName : "";
			sql = "select bjdm from fdybjb where zgh=?";
			String[] bjdmList = dao.getRs(sql, new String[]{fdyName}, "bjdm");
			String[] zydmList = new String[]{};
			String[] bjmcList = new String[bjdmList.length];
			String[] zymcList = null;
			List<HashMap<String, String>> bjList = new ArrayList<HashMap<String,String>>();
//			List<HashMap<String, String>> zyList = new ArrayList<HashMap<String,String>>();
			if (bjdmList != null && bjdmList.length > 0) {		
				StringBuffer strSql = new StringBuffer("select zydm from view_njxyzybj where bjdm in (");
				for (int i=0;i<bjdmList.length;i++) {
					strSql.append("'");
					strSql.append(bjdmList[i]);
					strSql.append("',");
					bjmcList[i] = dao.getOneRs("select bjmc from  view_njxyzybj where bjdm=?", new String[]{bjdmList[i]}, "bjmc");
				}
				sql = strSql.substring(0, strSql.length()-1).toString() + ") group by zydm";
				zydmList = dao.getRs(sql, new String[]{}, "zydm");
				if (zydmList != null && zydmList.length>0) {
					zymcList = new String[zydmList.length];
					for (int i=0;i<zydmList.length;i++) {
						zymcList[i] = dao.getOneRs("select zymc from view_njxyzybj where zydm=?", new String[]{zydmList[i]}, "zymc");
					}
				}
			}
			bjList = getFdyBjlist(bjdmList, bjmcList, xxdm);
			//zyList = dao.arrayToList(zydmList, zymcList);
			//request.setAttribute("zyList", zyList);// ����רҵ�б�
			request.setAttribute("bjList", bjList);// ���Ͱ༶�б�
			//}
		}

		if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
			if (StringUtils.isEqual(userType, "xy")) {
				String jxjlb = request.getParameter("jxjlb");
				if (StringUtils.isNull(jxjlb) || StringUtils.isEqual(jxjlb, "1")) {
					rychList = dao.getList("select distinct rychdm,rychmc from rychdmb where xydm=? order by rychdm", new String[]{dao.getOneRs("select xgbdm from xtszb", new String[]{}, "xgbdm")}, new String[]{"rychdm", "rychmc"});
				} else {
					rychList = dao.getList("select distinct rychdm,rychmc from rychdmb where xydm=? order by rychdm", new String[]{session.getAttribute("userDep").toString()}, new String[]{"rychdm", "rychmc"});
				}
			}
			if ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)) {
				String jxjsql = "select rychdm,rychmc from rychdmb where 1=1 ";
				if (!StringUtils.isNull(request.getParameter("xydm"))) {
					jxjsql += " and xydm='" + request.getParameter("xydm") + "'";
				}
				rychList = dao.getList(jxjsql, new String[]{}, new String[]{"rychdm", "rychmc"});
			}
			if ("true".equalsIgnoreCase(isFdy)) {
				request.setAttribute("iscsmz", "fdy");
			}
		}

		request.setAttribute("rychList", rychList);// ����רҵ�б�
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);// ���ͼ�¼��
		/*if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			request.setAttribute("shownblg", "yes");
			request.setAttribute("xxdm", dao.getXxdm());
		}*/ // lyl 2011-05-25
		if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {
			request.setAttribute("showahjg", "yes");
		}
		if(xxdm.equals(Globals.XXDM_ZBDX))
		{
			return mapping.findForward("zbdx_rychsh");
		}
		else if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {
			return new ActionForward("/rychshdefault.do",false);
		}
		else if (xxdm.equalsIgnoreCase(Globals.XXDM_JGSDX) && !StringUtils.isEqual(stab, "stab")) {
			return new ActionForward("/pjpy_jgsdx_rychshdefault.do",false);
		}
		else if (xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDXHXXY) && !StringUtils.isEqual(stab, "stab")) {

			return new ActionForward("/pjpy_whlghxxy_rychsh.do",false);
		} else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
			return mapping.findForward("gzdx_credit_check");
		}
		priseForm.setZt(DealString.toGBK(zt));
		return mapping.findForward("success");
	}

	private ActionForward creditChkOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// �鿴���޸ĵ�����¼
		DAO dao = DAO.getInstance();
		CommanForm priseChkForm = (CommanForm) form;
		String act = request.getParameter("act");
		String pkVal = request.getParameter("pkVal");
		HttpSession session = request.getSession();
		String xxdm = StandardOperation.getXxdm();
		String dcjpm = request.getParameter("zhszcpzfpm");//�������³ɼ�����
		String[] tempList = new String[]{};
		String userName = request.getSession().getAttribute("userName").toString();
		String rychdm = "";

		if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){//�人����ѧ
			return new ActionForward("/pjpy_whlgdx.do?method=showCheckRych",false);			
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)){
			//�㽭��ҵְҵ
			return new ActionForward("/pjpy_zjsyzy.do?method=showCheckRych",false);
		}
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
			tempList = dao.getOneRs("select xh,xn,nd,xq from view_xsrychb where xn||nd||xh||rychdm=?", new String[]{pkVal}, new String[]{"xh", "xn", "nd", "xq"});
			if (tempList == null) {
				tempList[0] = "";
				tempList[1] = "";
				tempList[2] = "";
				tempList[3] = "";
			} 
			request.setAttribute("showhzy", "showhzy");
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
			request.setAttribute("showjsxx", "showjsxx");
		}
		/*if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			request.setAttribute("shownblg", "yes");
			request.setAttribute("dcjpm", dcjpm);
		}*/ //lyl
		//�㽭����£��ǳɼ�
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {
			request.setAttribute("showzjjd", "yes");
			String[] xhxnnd = dao.getOneRs("select xh,xn,nd from view_xsrychb where xn||nd||xh||rychdm=?", new String[]{pkVal}, new String[]{"xh", "xn", "nd"});
			if (xhxnnd != null) {
				String[] tmp = dao
				.getOneRs(
						"select zhszcpzf,zhszpm from (select xh,xn,nd,zhszcpzf,(dense_rank() over (partition by bjdm,xn,nd order by zhszcpzf desc nulls last)) zhszpm from view_zjjd_zhszcp) where xh=? and xn=? and nd=?",
						new String[] { xhxnnd[0], xhxnnd[1], xhxnnd[2] },
						new String[] { "zhszcpzf", "zhszpm" });
				if (tmp != null) {
					request.setAttribute("zhszcpzf", tmp[0]);
					request.setAttribute("zhszpm", tmp[1]);
				}
				String dyzypjcj = dao.getOneRs("select xh,xn,trunc(avg(dyzf),1) dypjcj from view_zjjd_zhszcp where xh=? and xn=? group by xh,xn", new String[] { xhxnnd[0],
						xhxnnd[1] }, "dypjcj");
				String zypjcj = dao.getOneRs("select xh,xn,trunc(avg(zpcj2),1) zypjcj from cjb@dblink_jw where xh=? and xn=? group by xh,xn", new String[]{xhxnnd[0],
						xhxnnd[1]}, "zypjcj");
				request.setAttribute("dypjcj", dyzypjcj);
				request.setAttribute("zypjcj", zypjcj);
			}
		}
		String pk = "xn||nd||xh||rychdm";
		String sql = "";
		String userType = dao.getUserType(session.getAttribute("userDep").toString());
		String isFdy = session.getAttribute("isFdy").toString();
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String nd = request.getParameter("nd");
		String sj = DateUtils.getTime();
		String rychmc = DealString.toGBK(request.getParameter("rychmc"));
		//�б���ѧ
		if(xxdm.equals(Globals.XXDM_ZBDX))
		{
			if((act == null) || !act.equalsIgnoreCase("save"))
			{
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sql = "select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,RYCHMC,RYCHDM,XYSH yesNo,DCJ,ZCJ,TCJ,FKYJ from view_xsrychb where "
						+ pk + "='" + pkVal + "'";
				} 
				else {
					sql = "select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,RYCHMC,RYCHDM,XXSH yesNo,DCJ,ZCJ,TCJ,FKYJ from view_xsrychb where "
						+ pk + "='" + pkVal + "'";
				}
				String[] colList2 = new String[] {"ND","XN","XH","RYCHDM","yesNo"};
				String[] rs2 = dao.getOneRs(sql, new String[] {}, colList2);
				zbdx_pjpy_rychsh_util myutil=new zbdx_pjpy_rychsh_util();
				zbdx_rychsqsh_model rychsqsh=new zbdx_rychsqsh_model();
				rychsqsh.getRychsh().setXn(rs2[1]);
				rychsqsh.getRychsh().setNd(rs2[0]);
				rychsqsh.getStu().setXH(rs2[2]);
				rychsqsh.getRychsh().setRychdm(rs2[3]);
				rychsqsh=myutil.zbdx_pjpy_getrychsh_detail(rychsqsh);
				request.setAttribute("zbdxrychsh_rs", rychsqsh);
				stu_info_util stu_util= new stu_info_util();
				rychsqsh.setStu(stu_util.stu_find_byxh(rychsqsh.getStu().getXH()));
				request.setAttribute("yesNo",rs2[4]);
				priseChkForm.setYesNo(rs2[4]);
				request.setAttribute("chkList", dao.getChkList(3));
				request.setAttribute("userType", userType);
				request.setAttribute("pkVal", pkVal);
				return mapping.findForward("zbdx_rychsh_checkOne");
			}
			else
			{
				String yesNo = request.getParameter("yesNo");
				String fkyj = DealString.toGBK(request.getParameter("sjfkyj"));
				yesNo = DealString.toGBK(yesNo);
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sql = "update xsrychb set xysh=?,fkyj=? where " + pk + "=?";
				} else {
					sql = "update xsrychb set xxsh=?,fkyj=? where " + pk + "=?";
				}
				dao.runUpdate(sql, new String[] { yesNo, fkyj, pkVal });
				return null;
			}
		}
		//����ѧУ
		if ((act == null) || !act.equalsIgnoreCase("save")) {//��ʾ
			if (xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)) {//�㶫����ѧԺ
				request.setAttribute("isgdby", "yes");
			}
			String[] colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ",
					"XYMC", "ZYMC", "BJMC", "XB", "RYCHMC", "yesNo", "DCJ",
					"ZCJ", "TCJ", "FKYJ" };
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {//ѧԺ
				if (xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)) {//�㶫����ѧԺ
					sql = "select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,RYCHMC,XYSH yesNo,DCJ,ZCJ,TCJ,FKYJ,(select zhszcp.cxcj from zhszcp where zhszcp.xn=view_xsrychb.xn and zhszcp.xh=view_xsrychb.xh and zhszcp.nd=view_xsrychb.nd) CXCJ from view_xsrychb where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "RYCHMC", "yesNo", "DCJ",
							"ZCJ", "TCJ", "FKYJ", "CXCJ" };
				} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {//�㽭����Ҫ��ʾ�༶�ȼ����⣬�Ƶȼ�
					sql = "select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,RYCHMC,XYSH yesNo,DCJ,ZCJ,TCJ,FKYJ,wydj,jsjdj,bjpddj from view_xsrychb where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "RYCHMC", "yesNo", "DCJ",
							"ZCJ", "TCJ", "FKYJ", "wydj", "jsjdj", "bjpddj" };
				} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
					colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "RYCHMC", "yesNo", "DCJ",
							"ZCJ", "TCJ", "FKYJ", "pjcj", "cjmc", "zhpfmc", "FDYYJ", "XYYJ", "fdyqm", "xyqm", "rychdm" ,"zysj"};
					sql = "select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,FDYYJ,XYYJ,rychdm,fdyqm,xyqm,XB,RYCHMC,XYSH yesNo,DCJ,ZCJ,TCJ,FKYJ,zhpfmc,zysj,pjcj,cjmc,(select zhszcpcjpm from (select c.xh,c.xn,c.nd,c.xq,c.zhszcpzf,(case when c.zhszcpcjpm is not null then c.zhszcpcjpm else (select to_char(b.zhszcpcjpm) from (select xh,xn,xq,nd,(dense_rank() over(partition by bjdm,xn,xq,nd order by to_number(zhszcpzf) desc nulls last)) zhszcpcjpm from view_zhszcp) b where c.xh=b.xh and c.xn=b.xn and c.xq=b.xq and c.nd=b.nd) end) zhszcpcjpm from view_zhszcp c where c.xh='"+tempList[0]+"' and c.xn='"+tempList[1]+"' and c.nd='"+tempList[2]+"')) zhszcpcjpm from view_xsrychb a where "
						+ pk + "='" + pkVal + "'";
				} else if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
					sql = "select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,RYCHMC,XYSH yesNo,xyyj,SXZZDDSZF,KXWHSZF,SXLXSZF,SJLXCXF,YNYSZHSZCPZF from view_xsrychb where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "RYCHMC", "yesNo","xyyj", "SXZZDDSZF", "KXWHSZF", "SXLXSZF", "SJLXCXF", "YNYSZHSZCPZF"};
				}else if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
					colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "RYCHMC", "yesNo", "DCJ",
							"ZCJ", "TCJ", "FKYJ","xxsh" ,"xyyj"};
					sql = "select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,RYCHMC,XYSH yesNo,DCJ,ZCJ,TCJ,FKYJ,xxsh, xyyj from view_xsrychb where "
						+ pk + "='" + pkVal + "'";
				} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {//�㽭��ýѧԺ
					sql = "select "
						+ pk
						+ ",BZ,ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,RYCHMC,XYSH yesNo,DCJ,ZCJ,TCJ,FKYJ," +
						"xxsh,xyyj,(select b.cj from view_zjcm_zhszcpb b where a.xh=b.xh " +
						"and a.xn=b.xn and a.xq=b.xq) cj,(select b.bjpm from view_zjcm_zhszcpb" +
						" b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) bjpm from view_xsrychb a where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk,"ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "RYCHMC", "yesNo", "DCJ",
							"ZCJ", "TCJ", "FKYJ","xyyj","BZ","cj", "bjpm" };
				} else if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm)) {
					sql = "select "
						+ pk
						+ ",byjyqx,mzpyqksm,jcqk,(select b.drzw from xsrychxxb b where a.xh=b.xh) drzw,(select b.brjl from xsrychxxb b where a.xh=b.xh) brjl,(select b.zysj from xsrychxxb b where a.xh=b.xh) zysj,(select b.hjqk from xsrychxxb b where a.xh=b.xh) hjqk,(select b.zzmmmc from view_xsxxb b where a.xh=b.xh) zzmmmc,(select b.mzmc from view_xsxxb b where a.xh=b.xh) mzmc,BZ,ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,RYCHMC,XYSH yesNo,DCJ,ZCJ,TCJ,FKYJ,xxsh,xyyj from view_xsrychb a where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk,"ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "RYCHMC", "yesNo", "DCJ",
							"ZCJ", "TCJ", "FKYJ","xyyj","BZ","byjyqx","mzpyqksm","jcqk","drzw","zzmmmc","mzmc" ,"brjl","zysj","hjqk"};
				}else {
					sql = "select "
						+ pk
						+ ",BZ,ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,RYCHMC,XYSH yesNo,DCJ,ZCJ,TCJ,FKYJ,xxsh,xyyj,(select b.zysj from xsrychxxb b where a.xh=b.xh) zysjs,(select b.brjl from xsrychxxb b where a.xh=b.xh) brjl,(select b.drzw from xsrychxxb b where a.xh=b.xh) drzw,(select b.byzx from xsrychxxb b where a.xh=b.xh) byzx,(select b.wysp from xsfzxxb b where a.xh=b.xh) wysp,(select b.hjqk from xsrychxxb b where a.xh=b.xh) hjqk from view_xsrychb a where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk,"ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "RYCHMC", "yesNo", "DCJ",
							"ZCJ", "TCJ", "FKYJ","xyyj","BZ", "drzw","wysp", "brjl", "zysjs", "byzx", "hjqk" };
				}
			} 
			else {//ѧУ
				if (xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)) {//�㶫����ѧԺ
					sql = "select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,RYCHMC,XXSH yesNo,DCJ,ZCJ,TCJ,FKYJ,(select zhszcp.cxcj from zhszcp where zhszcp.xn=view_xsrychb.xn and zhszcp.xh=view_xsrychb.xh and zhszcp.nd=view_xsrychb.nd) CXCJ from view_xsrychb where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "RYCHMC", "yesNo", "DCJ",
							"ZCJ", "TCJ", "FKYJ", "CXCJ" };
				}else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {//�㽭����Ҫ��ʾ�༶�ȼ����⣬�Ƶȼ�
					sql = "select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,RYCHMC,XXSH yesNo,DCJ,ZCJ,TCJ,FKYJ,wydj,jsjdj,bjpddj from view_xsrychb where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "RYCHMC", "yesNo", "DCJ",
							"ZCJ", "TCJ", "FKYJ", "wydj", "jsjdj", "bjpddj" };
				}else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
					colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "RYCHMC", "yesNo", "DCJ",
							"ZCJ", "TCJ", "FKYJ","pjcj", "cjmc", "zhpfmc", "FDYYJ", "XYYJ", "fdyqm", "xyqm" ,"zysj"};
					sql = "select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,RYCHMC,FDYYJ,XYYJ,zhpfmc,fdyqm,xyqm,XXSH yesNo,DCJ,zysj,ZCJ,TCJ,FKYJ,cjmc,pjcj,(select zhszcpcjpm from (select c.xh,c.xn,c.nd,c.xq,c.zhszcpzf,(case when c.zhszcpcjpm is not null then c.zhszcpcjpm else (select to_char(b.zhszcpcjpm) from (select xh,xn,xq,nd,(dense_rank() over(partition by bjdm,xn,xq,nd order by to_number(zhszcpzf) desc nulls last)) zhszcpcjpm from view_zhszcp) b where c.xh=b.xh and c.xn=b.xn and c.xq=b.xq and c.nd=b.nd) end) zhszcpcjpm from view_zhszcp c where c.xh='"+tempList[0]+"' and c.xn='"+tempList[1]+"' and c.nd='"+tempList[2]+"')) zhszcpcjpm from view_xsrychb a where "
						+ pk + "='" + pkVal + "'";
					if (userName.equalsIgnoreCase("tw")) {
						colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ",
								"XYMC", "ZYMC", "BJMC", "XB", "RYCHMC", "yesNo", "DCJ",
								"ZCJ", "TCJ", "FKYJ", "pjcj", "cjmc", "zhpfmc", "FDYYJ", "XYYJ", "fdyqm", "xyqm", "rychdm" ,"zysj"};
						sql = "select "
							+ pk
							+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,FDYYJ,XYYJ,rychdm,fdyqm,xyqm,XB,RYCHMC,XYSH yesNo,DCJ,ZCJ,TCJ,FKYJ,zhpfmc,zysj,pjcj,cjmc,(select zhszcpcjpm from (select c.xh,c.xn,c.nd,c.xq,c.zhszcpzf,(case when c.zhszcpcjpm is not null then c.zhszcpcjpm else (select to_char(b.zhszcpcjpm) from (select xh,xn,xq,nd,(dense_rank() over(partition by bjdm,xn,xq,nd order by to_number(zhszcpzf) desc nulls last)) zhszcpcjpm from view_zhszcp) b where c.xh=b.xh and c.xn=b.xn and c.xq=b.xq and c.nd=b.nd) end) zhszcpcjpm from view_zhszcp c where c.xh='"+tempList[0]+"' and c.xn='"+tempList[1]+"' and c.nd='"+tempList[2]+"')) zhszcpcjpm from view_xsrychb a where "
							+ pk + "='" + pkVal + "'";
					}
				} else if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
					sql = "select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,RYCHMC,XXSH yesNo,xxyj,SXZZDDSZF,KXWHSZF,SXLXSZF,SJLXCXF,YNYSZHSZCPZF from view_xsrychb where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "RYCHMC", "yesNo", "xxyj", "SXZZDDSZF", "KXWHSZF", "SXLXSZF", "SJLXCXF", "YNYSZHSZCPZF" };
				} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {//�㽭��ýѧԺ
					sql = "select "
						+ pk
						+ ",BZ,ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,RYCHMC,XXSH yesNo,DCJ,ZCJ,TCJ,FKYJ,zysj," +
						"xxyj,(select b.cj from view_zjcm_zhszcpb b where a.xh=b.xh and a.xn=b.xn" +
						" and a.xq=b.xq) cj,(select b.bjpm from view_zjcm_zhszcpb b where a.xh=b.xh" +
						" and a.xn=b.xn and a.xq=b.xq) bjpm,fdyyj,xyyj from view_xsrychb a where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "RYCHMC", "yesNo", "DCJ",
							"ZCJ", "TCJ", "FKYJ","xxyj","BZ", "cj", "bjpm" ,"fdyyj","xyyj"};
				} else if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm)) {

					sql = "select "
						+ pk
						+ ",byjyqx,mzpyqksm,jcqk,(select b.drzw from xsrychxxb b where a.xh=b.xh) drzw,(select b.brjl from xsrychxxb b where a.xh=b.xh) brjl,(select b.zysj from xsrychxxb b where a.xh=b.xh) zysj,(select b.hjqk from xsrychxxb b where a.xh=b.xh) hjqk,(select b.zzmmmc from view_xsxxb b where a.xh=b.xh) zzmmmc,(select b.mzmc from view_xsxxb b where a.xh=b.xh) mzmc,BZ,ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,RYCHMC,XXSH yesNo,DCJ,ZCJ,TCJ,FKYJ,zysj,xxyj,fdyyj,xyyj from view_xsrychb a where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "RYCHMC", "yesNo", "DCJ",
							"ZCJ", "TCJ", "FKYJ","xxyj","BZ","fdyyj","xyyj" ,"byjyqx","mzpyqksm","jcqk","drzw","zzmmmc","mzmc","zysj","brjl","hjqk"};
				}else {
					sql = "select "
						+ pk
						+ ",BZ,ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,RYCHMC,XXSH yesNo,DCJ,ZCJ,TCJ,FKYJ,zysj,xxyj,fdyyj,xyyj,(select b.zysj from xsrychxxb b where a.xh=b.xh) zysjs,(select b.brjl from xsrychxxb b where a.xh=b.xh) brjl,(select b.drzw from xsrychxxb b where a.xh=b.xh) drzw,(select b.byzx from xsrychxxb b where a.xh=b.xh) byzx,(select b.wysp from xsfzxxb b where a.xh=b.xh) wysp,(select b.hjqk from xsrychxxb b where a.xh=b.xh) hjqk from view_xsrychb a where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "RYCHMC", "yesNo", "DCJ",
							"ZCJ", "TCJ", "FKYJ","xxyj","BZ","fdyyj","xyyj" ,"drzw","wysp", "zysj","brjl", "zysjs", "byzx", "hjqk"};
				}
			}
			if (StringUtils.isEqual(isFdy, "true")) {//����Ǹ���Ա
				if (xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)) {//����Ƽ�ѧԺ
					sql = "select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,RYCHMC,FDYSH yesNo,DCJ,ZCJ,TCJ,FKYJ from view_xsrychb where "
						+ pk + "='" + pkVal + "'";
				}
//				��ɳ����
				if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
					colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "RYCHMC", "yesNo", "DCJ",
							"ZCJ", "TCJ", "FKYJ","xysh","xxsh", "xyyj" };
					sql = "select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,RYCHMC,FDYSH yesNo,DCJ,ZCJ,TCJ,FKYJ,xysh,xxsh,fdyyj xyyj from view_xsrychb where "
						+ pk + "='" + pkVal + "'";
				}
				if (Globals.XXDM_CDFZGDZKXX.equalsIgnoreCase(xxdm)) {
					sql = "select "
						+ pk
						+ ",BZ,ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,RYCHMC,FDYSH yesNo,DCJ,ZCJ,TCJ,FKYJ,xxsh,fdyyj xyyj from view_xsrychb where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk,"ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "RYCHMC", "yesNo", "DCJ",
							"ZCJ", "TCJ", "FKYJ","xyyj","BZ" };
				}
				if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {//�㽭��ýѧԺ
					sql = "select "
						+ pk
						+ ",BZ,ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,RYCHMC,FDYSH yesNo,DCJ,ZCJ,TCJ,FKYJ," +
						"xxsh,fdyyj xyyj,(select b.cj from view_zjcm_zhszcpb b where a.xh=b.xh" +
						" and a.xn=b.xn and a.xq=b.xq) cj,(select b.bjpm from view_zjcm_zhszcpb " +
						"b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) bjpm from view_xsrychb a where "
						+ pk + "='" + pkVal + "'";
					colList = new String[] { pk,"ND", "XN", "XH", "XM", "NJ",
							"XYMC", "ZYMC", "BJMC", "XB", "RYCHMC", "yesNo", "DCJ",
							"ZCJ", "TCJ", "FKYJ","xyyj","BZ","cj","bjpm" };
				}
			}
			String[] rs = dao.getOneRs(sql, new String[] {}, colList);
			for (int i = 0; i < colList.length; i++) {
				rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
						: rs[i];
				if (colList[i].equalsIgnoreCase("rychdm")) {
					rychdm = rs[i];
				}
				if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm)) { 
					if (colList[i].equalsIgnoreCase("rychmc")) {
						rychmc = rs[i];
					}
				}
				request.setAttribute(colList[i], rs[i]);
				if ("xyyj".equalsIgnoreCase(colList[i])) {//����ѧԺ���
					priseChkForm.setXyyj(rs[i]);
				}
				if ("xxyj".equalsIgnoreCase(colList[i])) {//����ѧУ���
					priseChkForm.setXxyj(rs[i]);
				}
				if ("fdyyj".equalsIgnoreCase(colList[i])) {//���ø���Ա���
					priseChkForm.setFdyyj(rs[i]);
				}
			}
			if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm)) {
				if (rychmc.contains("Ժ�ű�ҵ��")) {
					request.setAttribute("info", "yes");
					request.setAttribute("yybys", "yes");
				}
				if (rychmc.contains("ʡ�ű�ҵ��")) {
					request.setAttribute("sybys", "yes");
					request.setAttribute("info", "yes");
				}
			}
			priseChkForm.setYesNo(rs[11]);
			priseChkForm.setBz(rs != null ? rs[rs.length-1] : "");
			if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
				priseChkForm.setXyyj(rs[12]);
				priseChkForm.setXxyj(rs[12]);
			} else if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm)) {
				priseChkForm.setXyyj(rs[16]);
				priseChkForm.setXxyj(rs[16]);
			}

			request.setAttribute("tgres", priseChkForm.getYesNo());//�Ƿ�ͨ��
			request.setAttribute("userType", userType);
			request.setAttribute("chkList", dao.getChkList(3));
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				if (userType.equalsIgnoreCase("xy")) {
					if (!userName.equalsIgnoreCase("wth01")) {//����ѧ���ɲ�����Ԣ��ר�����
						if (rychdm.equalsIgnoreCase("008")) {
							request.setAttribute("notgyk", "yes");
						}
					}
					if (!userName.equalsIgnoreCase("tw")) {//����ѧ���ɲ�����ί��ר�����
						if (rychdm.equalsIgnoreCase("009")) {
							request.setAttribute("notgyk", "yes");
						}
					}
				}

			}

			return mapping.findForward("success");
		}
		String yesNo = request.getParameter("yesNo");
		String fkyj = DealString.toGBK(request.getParameter("sjfkyj"));
		yesNo = DealString.toGBK(yesNo);
		String fdyyj = DealString.toGBK(request.getParameter("fdyyj"));
		String xyyj = DealString.toGBK(request.getParameter("xyyj"));
		String fdyqm = DealString.toGBK(request.getParameter("fdyqm"));
		String xyqm = DealString.toGBK(request.getParameter("xyqm"));
		String zysj = DealString.toGBK(request.getParameter("zysj"));
		String xxyj = DealString.toGBK(request.getParameter("xxyj"));
		yesNo = !StringUtils.isNull(yesNo) ? yesNo : "";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "update xsrychb set xysh=?,xyyj=?,xyshsj='"+sj+"' where " + pk + "=?";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				sql = "update xsrychb set xysh=?,fkyj=?,fdyyj='"+fdyyj+"',xyyj='"+xyyj+"',fdyqm='"+fdyqm+"',xyqm='"+xyqm+"' where " + pk + "=?";
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
				sql = "update xsrychb set xysh=?,xyyj='"+xyyj+"',fkyj=? where " + pk + "=?";
			} else if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm)) {
				sql = "update xsrychb set xysh=?,fkyj=?,xyyj='"+xyyj+"' where " + pk + "=?";
			}
		} else {
			sql = "update xsrychb set xxsh=?,xxyj=?,xxshsj='"+sj+"' where " + pk + "=?";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
				sql = "update xsrychb set xxsh=?,xxyj='"+xxyj+"',fkyj=? where " + pk + "=?";
			} else if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm)) {
				sql = "update xsrychb set xxsh=?,fkyj=?,xxyj='"+xxyj+"' where " + pk + "=?";
			}
		}
		if (StringUtils.isEqual(isFdy, "true")) {//����Ǹ���Ա
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)) {//����Ƽ�ѧԺ
				sql = "update xsrychb set fdysh=?,fkyj=? where " + pk + "=?";
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				sql = "update xsrychb set fdysh=?,fdyyj=? where " + pk + "=?";
			}
			if (Globals.XXDM_CDFZGDZKXX.equalsIgnoreCase(xxdm)) {
				sql = "update xsrychb set fdysh=?,fdyyj=? where " + pk + "=?";
			}
			if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
				sql = "update xsrychb set fdysh=?,fdyyj=? where " + pk + "=?";
			}
		}
		//��������������жϼ���������Ƿ�רҵ��������
	/*	if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			if (StringUtils.isEqual(yesNo, "ͨ��")) {//ֻ�����ͨ��ʱ����
				String pxbl = dao.getOneRs("select distinct pxbl from rychdmb where rychmc=?", new String[]{rychmc}, "pxbl");
				String xsrs = dao.getOneRs("select count(*) num from view_xsjbxx", new String[]{}, "num");
				int sdrs = 0;//�趨����
				if (!StringUtils.isNull(pxbl) && !StringUtils.isNull(xsrs)) {
					if (pxbl.lastIndexOf("%") > 0) {
						sdrs = Integer.parseInt(pxbl.substring(0, pxbl.length()-1))* Integer.parseInt(xsrs)/100;
					} else {
						sdrs = Integer.parseInt(pxbl)* Integer.parseInt(xsrs)/100;
					}
				}
				//�����ͨ������
				String tgrs = dao.getOneRs("select count(*) num from view_xsrychb where xn=? and nd=? and rychmc=? and xysh='ͨ��' and xxsh='ͨ��' ", new String[]{xn,nd,rychmc}, "num");
				int itgrs = !StringUtils.isNull(tgrs) ? Integer.parseInt(tgrs) : 0;
				//�Ƿ��м��
				String num = dao.getOneRs("select count(*) num from view_xsrychb where xh=? and xn=? and nd=? and xysh='ͨ��' and xxsh='ͨ��'", new String[]{xh, xn, nd}, "num");
				int inum = !StringUtils.isNull(num) ? Integer.parseInt(num) : 0;
				if ((sdrs != 0) && (itgrs > sdrs)) {//��ͨ����רҵ��������������
					request.setAttribute("insert", "no");
					return new ActionForward("/creditChkOne.do?act=view&pkVal=" + pkVal, false);
				} else if (inum > 0) {//��ò���������
					request.setAttribute("insert", "no");
					return new ActionForward("/creditChkOne.do?act=view&pkVal=" + pkVal, false);
				}else {
					dao.runUpdate(sql, new String[] { yesNo, fkyj, pkVal });
					request.setAttribute("insert", "yes");
					return new ActionForward("/creditChkOne.do?act=view&pkVal=" + pkVal, false);
				}
			} else {
				dao.runUpdate(sql, new String[] { yesNo, fkyj, pkVal });
				request.setAttribute("insert", "yes");
				return new ActionForward("/creditChkOne.do?act=view&pkVal=" + pkVal, false);
			} //lyl
		} else*/ if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {//�㽭����
			if (StringUtils.isEqual(yesNo, "ͨ��")) {//ֻ�����ͨ��ʱ����
				String pxbl = dao
				.getOneRs(
						"select distinct tj from jxjhdtj where tjzdm='pxbl' and jxjdm=(select rychdm from view_xsrychb where xn||nd||xh||rychdm=? )",
						new String[] { pkVal }, "tj");//��ѡ����
				String tgrs = "";//ͨ������
				String jsrs = dao.getOneRs("select round(to_number(count(*))*"
						+ (StringUtils.isNull(pxbl) ? "0" : pxbl)
						+ ") num from view_xsjbxx", new String[] {}, "num");//��������
				if (userType.equalsIgnoreCase("xy")) {
					tgrs = dao.getOneRs("select count(*) num from view_xsrychb where xn=? and nd=? and rychmc=? and xysh='ͨ��' ", new String[]{xn,nd,rychmc}, "num");
				} else {
					tgrs = dao.getOneRs("select count(*) num from view_xsrychb where xn=? and nd=? and rychmc=? and xysh='ͨ��' and xxsh='ͨ��' ", new String[]{xn,nd,rychmc}, "num");
				}
				int itgrs = !StringUtils.isNull(tgrs) ? Integer.parseInt(tgrs) : 0;
				int ijsrs = !StringUtils.isNull(jsrs) ? Integer.parseInt(jsrs) : 0;
				if ((ijsrs != 0) && (itgrs >= ijsrs)) {
					request.setAttribute("insert", "cb");
					request.setAttribute("failinfo", String.format("��ʾ���ý�����������Ϊ %1$d �������ͨ�� %2$d ��!", ijsrs, itgrs));
					return new ActionForward("/creditChkOne.do?act=view&pkVal=" + pkVal, false);
				} else {
					dao.runUpdate(sql, new String[] { yesNo, fkyj, pkVal });
					request.setAttribute("insert", "yes");
					return new ActionForward("/creditChkOne.do?act=view&pkVal=" + pkVal, false);
				}
			} else {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
					rychdm = dao.getOneRs("select rychdm from view_xsrychb where xn||nd||xh||rychdm=?", new String[]{pkVal}, "rychdm");
					if (StringUtils.isEqual(rychdm, "005")
							|| StringUtils.isEqual(rychdm, "008")
							|| StringUtils.isEqual(rychdm, "009")
							|| StringUtils.isEqual(rychdm, "010")) {
						String nums = dao.getOneRs("select count(*) num from view_xsrychb where xh=? and xn=? xq=? and xysh='ͨ��' and rychdm in ('005','008','009','010')", new String[]{xh,tempList[1],tempList[3]}, "num");
						if (!StringUtils.isNull(nums) && !StringUtils.isEqual(nums, "0")) {
							request.setAttribute("insert", "cb");
						} else {
							dao.runUpdate(sql, new String[] { yesNo, fkyj, pkVal });
							request.setAttribute("insert", "yes");
						}
					}else {
						dao.runUpdate(sql, new String[] { yesNo, fkyj, pkVal });
						request.setAttribute("insert", "yes");
					}

				} else {
					dao.runUpdate(sql, new String[] { yesNo, fkyj, pkVal });
					request.setAttribute("insert", "yes");
				}
				return new ActionForward("/creditChkOne.do?act=view&pkVal=" + pkVal, false);
			}
		} else {
			boolean bFlag = false;
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				if (userName.equalsIgnoreCase("tw")) {
					sql = "update xsrychb set xysh=?,fkyj=?,fdyyj='"+fdyyj+"',xyyj='"+xyyj+"',fdyqm='"+fdyqm+"',xyqm='"+xyqm+"' where " + pk + "=?";
				}
			}

			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				rychdm = dao.getOneRs("select rychdm from view_xsrychb where xn||nd||xh||rychdm=?", new String[]{pkVal}, "rychdm");
				if (StringUtils.isEqual(rychdm, "005")
						|| StringUtils.isEqual(rychdm, "008")
						|| StringUtils.isEqual(rychdm, "009")
						|| StringUtils.isEqual(rychdm, "010")) {
					String nums = dao.getOneRs("select count(*) num from view_xsrychb where xh=? and xn=? and xq=? and xysh='ͨ��' and rychdm in ('005','008','009','010')", new String[]{xh,tempList[1],tempList[3]}, "num");
					if (!StringUtils.isNull(nums) && !StringUtils.isEqual(nums, "0")) {
						request.setAttribute("cb", "yes");
					} else {
						bFlag = dao.runUpdate(sql, new String[] { yesNo, fkyj, pkVal });
					}
				}else {
					//sql = "update xsrychb set xysh=?,fkyj=?,fdyyj='"+fdyyj+"',xyyj='"+xyyj+"',fdyqm='"+fdyqm+"',xyqm='"+xyqm+"' where " + pk + "=?";
					if (userType.equalsIgnoreCase("xy")) {
						bFlag = StandardOperation.update("xsrychb", new String[]{"xysh","fkyj","fdyyj","xyyj","fdyqm","xyqm"}, new String[]{yesNo,fkyj,fdyyj,xyyj,fdyqm,xyqm}, pk, pkVal, request);
					} else {
						//sql = "update xsrychb set xxsh=?,fkyj=? where " + pk + "=?";
						bFlag = StandardOperation.update("xsrychb", new String[]{"xxsh","fkyj"}, new String[]{yesNo,fkyj}, pk, pkVal, request);
					}
					//bFlag = dao.runUpdate(sql, new String[] { yesNo, fkyj, pkVal });
				}

			} else {
				if ("xy".equalsIgnoreCase(userType)) {
					fkyj = xyyj;
				} else {
					fkyj = xxyj;
				}
				bFlag = dao.runUpdate(sql, new String[] { yesNo, fkyj, pkVal });
				priseChkForm.setXyyj("");
				priseChkForm.setXxyj("");
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				String cjmc = DealString.toGBK(request.getParameter("cjmc"));
				String zhpfmc = DealString.toGBK(request.getParameter("zhpfmc"));
				cjmc = StringUtils.isNull(cjmc) ? "" : cjmc.trim();
				zhpfmc = StringUtils.isNull(zhpfmc) ? "" : zhpfmc.trim();
				dao.runUpdate("update xsrychxxb set zysj = ?,cjmc=?,zhpfmc=? where xh=?", new String[]{zysj,cjmc,zhpfmc,xh});
			}
			if (bFlag) {
				request.setAttribute("insert", "yes");
			} else {
				request.setAttribute("insert", "no");
			}
		}
		priseChkForm.setXyyj(DealString.toGBK(priseChkForm.getXyyj()));
		priseChkForm.setXxyj(DealString.toGBK(priseChkForm.getXxyj()));
		return new ActionForward("/creditChkOne.do?act=view&pkVal=" + pkVal, false);
	}

	private ActionForward creditAutoChk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// �Զ����
		DAO dao = DAO.getInstance();
		String sql = "";
		String xn = dao.getConf(2);
		String nd = dao.getConf(3);
		HttpSession session = request.getSession();
		String xxdm = StandardOperation.getXxdm();
		String rychdm = request.getParameter("xmdm");
		String xydm = request.getParameter("xydm");
		if (xydm == null) {
			xydm = session.getAttribute("userDep").toString();
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
			sql = "{call JSXX_RYChAUTOCHK(?,?,?)}";
			dao.runProcuder(sql, new String[] { rychdm,xn,nd });
		}else{
			sql = "{call PRISEAUTOCHK(?)}";
			dao.runProcuder(sql, new String[] { xydm });
		}
		// request.setAttribute("go","go");
		return mapping.findForward("success");
	}

	private ActionForward assisCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// ��ʼ��ҳ�棬���ز�ѯ��Ϣ
		CommanForm checkForm = (CommanForm) form;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql���
		String querry = " where 1=1 ";// sql����
		String querry1 = "";
		String tips = "";// λ����ʾ��Ϣ
		String tableName = "";// ��ѯ��ϢԴ����Ϊ��ͼ����
		String rsNum = "0";// ���صļ�¼��
		String realTable = "";// ����Դ��
		String pk = "";// ����Դ����������ʽΪ���ֶ���||�ֶ���||�ֶ�������
		String writeAble = "yes";// дȨ��
		String dataType = request.getParameter("act");
		String nj = checkForm.getNj();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xy = checkForm.getXydm();
		String xydm = request.getParameter("xydm");
		String xh = DealString.toGBK(checkForm.getXh());
		String sbsj = DealString.toGBK(checkForm.getSbsj());
		String sfzh = DealString.toGBK(checkForm.getSfzh());
		String hth = DealString.toGBK(checkForm.getHth());
		String xxdm = StandardOperation.getXxdm();
		if(xydm != null && xy == null ){
			xy = xydm;
		}
		if(userType.equalsIgnoreCase("xy") && (xy == null ||xy.trim().equals(""))){
			xy = userDep;
		}
		String zy = checkForm.getZydm();
		String bj = checkForm.getBjdm();
		sql = "select dqxn,dqnd from xtszb where rownum=1";
		colList = dao.getOneRs(sql, new String[] {}, new String[] { "dqxn",
		"dqnd" });
		String xn = colList[0];
		String nd = colList[1];
		checkForm.setXn(xn);
		checkForm.setNd(nd);
		if (dataType == null) {
			checkForm.setErrMsg("asdf");
			return mapping.findForward("false");
		} else if (dataType.equalsIgnoreCase("xpjjzlzxdk")) {
			realTable = "xpjjzxdksqb";
			pk = "xh||nd";
			tips = "ѧ������ - ��� - ��ƽ����������ѧ�������";
			tableName = "view_xpjjzxdksq";
			colList = new String[] { "bgcolor", "����", "XH",
					"XM", "NJ", "XY", "SQDKJE", "DKQX", "" };
		} else if (dataType.equalsIgnoreCase("txsq")) {
			realTable = "txsqb";
			pk = "xh||nd";
			tips = "ѧ������ - ��� - ��Ϣ���";
			tableName = "xstxsqb";
			colList = new String[] { "bgcolor", "����", "nd", "xh",
					"xm", "xb", "sfzh", "" };
			sql = "select 'txsq' shxmdm,'��Ϣ' shxmmc from dual";
			List shxmList = dao.getList(sql, new String[] {},
					new String[] { "shxmdm" , "shxmmc" });
			request.setAttribute("shxmList", shxmList);
			querry1 = " and 1=1 ";
		}else {
			checkForm.setErrMsg("asdf");
			return mapping.findForward("false");
		}
		if (isNull(nj)) {
			querry += "and 1=1 ";
		} else {
			querry += "and nj = '" + nj + "' ";
		}
		if (isNull(xy)) {
			querry += "and 1=1 ";
		} else {
			querry += "and xydm = '" + xy + "' ";
		}
		if (isNull(zy)) {
			querry += "and 1=1 ";
		} else {
			querry += "and zydm = '" + zy + "' ";
		}
		if (isNull(bj)) {
			querry += "and 1=1 ";
		} else {
			querry += "and bjdm = '" + bj + "' ";
		}
		if (isNull(xh)) {
			querry += "and 1=1 ";
		} else {
			querry += "and xh = '" + xh + "' ";
		}
		if (isNull(sfzh)) {
			querry += "and 1=1 ";
		} else {
			querry += "and sfzh = '" + sfzh + "' ";
		}
		if (isNull(hth)) {
			querry += "and 1=1 ";
		} else {
			querry += "and hth = '" + hth + "' ";
		}
		if (isNull(sbsj)) {
			sbsj = "";
			querry += "and 1=1 ";
		} else {
			querry += "and sbsj = '" + sbsj + "' ";
		}
		request.setAttribute("sbsj", sbsj);
		querry += querry1;
		if (userType.equalsIgnoreCase("xx")) {
			sql = "select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
				+ " a.* from(select "
				+ pk
				+ " ����,a.* from "
				+ tableName
				+ " a"
				+ querry
				+ " and xysh='ͨ��' order by xxsh desc) a";
			colList[colList.length - 1] = "xxsh";
		} else {
			checkForm.setXydm(userDep);
			sql = "select (case when nvl(a.xysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
				+ "a.* from(select "
				+ pk
				+ " ����,a.* from "
				+ tableName
				+ " a"
				+ querry
				+ " and xydm='"
				+ userDep
				+ "' order by xysh desc) a";
			colList[colList.length - 1] = "xysh";
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

		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZBDX)){
			if("xy".equalsIgnoreCase(userType))
			{
				writeAble="no";
			}
			else if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType))
			{
				writeAble="yes";
			}
		}

		if (userType.equalsIgnoreCase("xx")) {
			request.setAttribute("isXX", "is");
		}else{
			request.setAttribute("isXX", "no");
		}
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("act", dataType);// �������ݲ�ѯ����
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("njList", dao.getNjList());// �����꼶�б�
		request.setAttribute("xnList", dao.getXnndList());// ����ѧ���б�
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", dao.getZyList(xy));// ����רҵ�б�
		request.setAttribute("bjList", dao.getBjList(xy, zy));// ���Ͱ༶�б�
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);// ���ͼ�¼��
		return mapping.findForward("success");
	}

	private ActionForward assisChkOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DAO dao = DAO.getInstance();
		ActionForward myActFwd = null;
		CommanForm priseChkForm = (CommanForm) form;
		String act = request.getParameter("act");
		String actDo = request.getParameter("actDo");
		String pkVal = request.getParameter("pkVal");
		String tName = request.getParameter("tName"); 
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj")); 
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj")); 
		String xyzzfzryj = DealString.toGBK(request.getParameter("xyzzfzryj"));
		if (xyzzfzryj != null) {
			xyzzfzryj = xyzzfzryj.trim();
		}
		if (xyshyj != null) {
			xyshyj = xyshyj.trim();
		}
		if (xxshyj != null) {
			xxshyj = xxshyj.trim();
		}
		String realTable = "";
		String tips = "";
		HttpSession session = request.getSession();
		String pk = "";
		String sql = "";
		String query = "";
		String[] colList = new String[] {};
		String userType = dao.getUserType(session.getAttribute("userDep")
				.toString());
		List xszzDjList = dao.getXszzDjList();
		request.setAttribute("xszzDjList", xszzDjList);
		if (!actDo.equalsIgnoreCase("save")) {
			if (act.equalsIgnoreCase("txsq")) {
				realTable = "txsqb";
				pk = "xh||nd";
				tips = "ѧ������ - ��� - ��Ϣ��� - �������";
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sql = "select "
						+ pk
						+ " pk,xh,xb,xm,sfzh,xxmc,dkyh,dkzje,dkqx,yflx,bktx,jytx,zctx,nd,sqsj,xxshyj,xyshyj,xysh yesNo from xstxsqb where "
						+ pk + "='" + pkVal + "'";
				} else {
					sql = "select "
						+ pk
						+ " pk,xh,xb,xm,sfzh,xxmc,dkyh,dkzje,dkqx,yflx,bktx,jytx,zctx,nd,sqsj,xxshyj,xyshyj,xxsh yesNo from xstxsqb where "
						+ pk + "='" + pkVal + "'";
				}
				myActFwd = new ActionForward("/sjcz/assis_check_tx.jsp",false);
				colList = new String[] { "pk","xh","xb","xm","sfzh","xxmc","dkyh","dkzje","dkqx",
						"yflx","bktx","jytx","zctx","nd","sqsj","xxshyj","xyshyj","yesNo" };
				tName = "xstxsq";
			}
			else if (act.equalsIgnoreCase("xpjjzlzxdk")) {
				realTable = "xpjjzxdksqb";
				pk = "xh||nd";
				tips = "ѧ������ - ��� - ��ƽ����������ѧ������� - �������";
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sql = "select "
						+ pk
						+ " pk,sxq,yzbm,jtdh,jhr1_xm,jhr1_zy,jhr1_sfzh,jhr1_gzdw,jhr2_xm,jhr2_zy,jhr2_sfzh,jhr2_gzdw,nd,"
						+ "xh,xm,xb,csrq,sfzh,nj,xy,zymc,qsdh,xz,sqdkje,dkqx,szzq,XYSH yesNo from view_xpjjzxdksq where "
						+ pk + "='" + pkVal + "'";
				} else {
					sql = "select "
						+ pk
						+ " pk,sxq,yzbm,jtdh,jhr1_xm,jhr1_zy,jhr1_sfzh,jhr1_gzdw,jhr2_xm,jhr2_zy,jhr2_sfzh,jhr2_gzdw,nd,"
						+ "xh,xm,xb,csrq,sfzh,nj,xy,zymc,qsdh,xz,sqdkje,dkqx,szzq,XXSH yesNo from view_xpjjzxdksq where "
						+ pk + "='" + pkVal + "'";
				}
				colList = new String[] { "pk","sxq","yzbm","jtdh","jhr1_xm","jhr1_zy",
						"jhr1_sfzh","jhr1_gzdw","jhr2_xm","jhr2_zy","jhr2_sfzh","jhr2_gzdw",
						"nd","xh","xm","xb","csrq","sfzh","nj","xy","zymc",
						"qsdh","xz","sqdkje","dkqx","szzq","yesNo" };
				myActFwd = new ActionForward("/sjcz/assis_check_xpjjzlzxdk.jsp",false);
			} 

			String[] rs = dao.getOneRs(sql, new String[] {}, colList);
			if (rs == null) {
				rs = new String[colList.length];
			}
			for (int i = 0; i < colList.length; i++) {
				rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
						: rs[i];
				if (colList[i].equalsIgnoreCase("yesNo")) {
					priseChkForm.setYesNo(rs[i]);
				}
				request.setAttribute(colList[i], rs[i]);
			}
			request.setAttribute("tName", realTable);
			request.setAttribute("userType", userType);
			request.setAttribute("chkList", dao.getChkList(3));
			request.setAttribute("tips", tips);
			request.setAttribute("act", act);

		}
		if (act.equalsIgnoreCase("txsq")) {
			pk = "xh||nd";
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) 
				query = ",xyshyj='"+xyshyj+"'";
			else
				query = ",xxshyj='"+xxshyj+"'";
		} else if (act.equalsIgnoreCase("xpjjzlzxdk")) {
			pk = "xh||nd";
			if (actDo.equalsIgnoreCase("save")) {
				String yesNo = DealString.toGBK(request.getParameter("yesNo"));
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sql = "update " + tName + " set xysh=?" + query + " where "
					+ pk + "=?";
				} else {
					sql = "update " + tName + " set xxsh=?" + query + " where "
					+ pk + "=?";
				}
				dao.runUpdate(sql, new String[] { yesNo, pkVal });
			}
		}
		return myActFwd;

	}

	private ActionForward commCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// ��ʼ��ҳ�棬���ز�ѯ��Ϣ
		CommanForm checkForm = (CommanForm) form;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql���
		String querry = " where 1=1 ";// sql����
		String tips = "";// λ����ʾ��Ϣ
		String tableName = "";// ��ѯ��ϢԴ����Ϊ��ͼ����
		String rsNum = "0";// ���صļ�¼��
		String realTable = "";// ����Դ��
		String pk = "";// ����Դ����������ʽΪ���ֶ���||�ֶ���||�ֶ�������
		String writeAble = "yes";// дȨ��
		String dataType = request.getParameter("act");
		String nj = checkForm.getNj();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		writeAble = CheckPower.checkUsrPower(session.getAttribute("userName").toString(),"comm_check.do")? "yes" : "no";

		sql = "select dqxn,dqnd from xtszb where rownum=1";
		colList = dao.getOneRs(sql, new String[] {}, new String[] { "dqxn","dqnd" });
		String xn = colList[0];
		String nd = colList[1];
		checkForm.setXn(xn);
		checkForm.setNd(nd);
		String bmdm = request.getParameter("xydm");
		String jlxmdm = checkForm.getXmdm();

		sql = "select dwjlxmdm,dwjlxmmc from dwjlxmdmb";
		List jlxmList = dao.getList(sql, new String[] {}, new String[] {
				"dwjlxmdm", "dwjlxmmc" });
		request.setAttribute("jlxmList", jlxmList);

		realTable = "dwjlsqb";
		pk = "xn||nd||xh||jlxmdm";
		tips = "���⽻�� - ��� - ���⽻�����";
		tableName = "view_dwjlsq";
		querry += "and xn = '" + xn + "' ";
		querry += "and nd = '" + nd + "' ";
		if (isNull(nj)) {
			querry += "and 1=1 ";
		} else {
			querry += "and nj = '" + nj + "' ";
		}
		if (isNull(jlxmdm)) {
			querry += "and 1=1 ";
		} else {
			querry += "and jlxmdm = '" + jlxmdm + "' ";
		}
		if (isNull(bmdm)) {
			querry += "and 1=1 ";
		} else {
			querry += "and xydm = '" + bmdm + "' ";
		}
		//	userType="xy";
		if (userType.equalsIgnoreCase("xx")) {
			userDep = "";
			colList = new String[] { "bgcolor","r", "����", "�к�", "xn", "nd", "xh",
					"xm", "bjmc", "dwjlxmmc", "xxsh" ,"xxzs"};
			sql = "select rownum r, rownum �к�,(case when nvl(a.xxsh,'δ���')='ͨ��' and nvl(a.xxzs,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
				+ " a.* from(select "
				+ pk
				+ " ����,a.* from "
				+ tableName
				+ " a" + querry + " and xysh='ͨ��' order by xxsh desc) a";
		} else {
			checkForm.setXydm(userDep);
			colList = new String[] { "bgcolor", "r","����", "�к�", "xn", "nd", "xh",
					"xm", "bjmc", "dwjlxmmc", "xysh" };
			sql = "select rownum r, rownum �к�,(case when nvl(a.xysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
				+ " a.* from(select "
				+ pk
				+ " ����,a.* from "
				+ tableName
				+ " a"
				+ querry
				+ " and xydm='"
				+ userDep
				+ "' order by xysh desc) a";
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator("select * from(select * from("+sql+") where r<="+(checkForm.getPages().getStart()+checkForm.getPages().getPageSize())
					+") where r>" + checkForm.getPages().getStart(), new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		//String [] XnNd = dao.getOneRs("select dqxn,dqnd from xtszb", new String[]{}, new String[]{"dqxn","dqnd"});
		//checkForm.setXn(XnNd[0]);
		//checkForm.setNd(XnNd[1]);
		//TODO����ҳ
		checkForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs("select count(*) count from (" + sql + ")", new String[] {}, "count")));
		sql = "select rychdm,rychmc from rychdmb";
		List rychList = dao.getList(sql, new String[] {}, new String[] {
				"rychdm", "rychmc" });
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("act", dataType);// �������ݲ�ѯ����
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("njList", dao.getNjList());// �����꼶�б�
		request.setAttribute("xnList", dao.getXnndList());// ����ѧ���б�
		request.setAttribute("xqList", dao.getXqList());// ����ѧ���б�
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", dao.getZyList(userDep));// ����רҵ�б�
		request.setAttribute("rychList", rychList);// ����רҵ�б�
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);// ���ͼ�¼��
		return mapping.findForward("success");
	}

	private ActionForward commCheckOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// �鿴���޸ĵ�����¼
		DAO dao = DAO.getInstance();
		CommanForm priseChkForm = (CommanForm) form;
		String act = request.getParameter("act");
		String pkVal = request.getParameter("pkVal");
		HttpSession session = request.getSession();
//		String writeAble=CheckPower.checkUsrPower(session.getAttribute("userName").toString(), "comm_check.do")?"yes":"no";
		String pk = "xn||nd||xh||jlxmdm";
		String sql = "";
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String[] colList = new String[] {};
		if ((act == null) || !act.equalsIgnoreCase("save")) {
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select "
					+ pk
					+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,XXSH yesNo,XXZS yesNoEnd,DWJLXMMC,DWJLFSMC,DWJLLBMC,SQSJ from view_dwjlsq where "
					+ pk + "='" + pkVal + "'";
				colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ", "XYMC",
						"ZYMC", "BJMC", "XB", "DWJLXMMC", "yesNo", "yesNoEnd", "DWJLFSMC",
						"DWJLLBMC", "SQSJ" };
			} else {
				sql = "select "
					+ pk
					+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,XYSH yesNo,DWJLXMMC,DWJLFSMC,DWJLLBMC,SQSJ from view_dwjlsq where "
					+ pk + "='" + pkVal + "'";
				colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ", "XYMC",
						"ZYMC", "BJMC", "XB", "DWJLXMMC", "yesNo", "DWJLFSMC",
						"DWJLLBMC", "SQSJ" };
			}

			String[] rs = dao.getOneRs(sql, new String[] {}, colList);
			for (int i = 0; i < colList.length; i++) {
				rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
						: rs[i];
				request.setAttribute(colList[i], rs[i]);
			}
			priseChkForm.setYesNo(rs[11]);	
			if(userType.equalsIgnoreCase("xx")){
				priseChkForm.setYesNoEnd(rs[12]);
			}
			request.setAttribute("userType", userType);
			request.setAttribute("chkList", dao.getChkList(3));
			request.setAttribute("writeAble", writeAble);
			return mapping.findForward("success");
		}
		String yesNo = request.getParameter("yesNo");
		yesNo = DealString.toGBK(yesNo);
		if (userType.equalsIgnoreCase("xx")) {
			String yesNoEnd = request.getParameter("yesNoEnd");
			yesNoEnd = DealString.toGBK(yesNoEnd);			
			StandardOperation.update("dwjlsqb", new String[] {"xxsh","xxzs"}, new String[] {yesNo,yesNoEnd}, pk, pkVal, request);
		} else {
			StandardOperation.update("dwjlsqb", new String[] {"xysh"}, new String[] {yesNo},pk, pkVal, request);
		}	
		request.setAttribute("writeAble", writeAble);
		return null;

	}

	private ActionForward commMonCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// ��ʼ��ҳ�棬���ز�ѯ��Ϣ
		CommanForm checkForm = (CommanForm) form;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql���
		String querry = " where 1=1 ";// sql����
		String tips = "";// λ����ʾ��Ϣ
		String tableName = "";// ��ѯ��ϢԴ����Ϊ��ͼ����
		String rsNum = "0";// ���صļ�¼��
		String realTable = "";// ����Դ��
		String pk = "";// ����Դ����������ʽΪ���ֶ���||�ֶ���||�ֶ�������
		String writeAble = "yes";// дȨ��
		String dataType = request.getParameter("act");
		String nj = checkForm.getNj();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		writeAble = CheckPower.checkUsrPower(session.getAttribute("userName").toString(), "comm_money_check.do") ?"yes" : "no";

		sql = "select dqxn,dqnd from xtszb where rownum=1";
		colList = dao.getOneRs(sql, new String[] {}, new String[] { "dqxn",
		"dqnd" });
		String xn = colList[0];
		String nd = colList[1];
		checkForm.setXn(xn);
		checkForm.setNd(nd);
		String bmdm = request.getParameter("xydm");
		String jlxmdm = checkForm.getXmdm();

		sql = "select dwjlxmdm,dwjlxmmc from dwjlxmdmb";
		List jlxmList = dao.getList(sql, new String[] {}, new String[] {
				"dwjlxmdm", "dwjlxmmc" });
		request.setAttribute("jlxmList", jlxmList);

		realTable = "dwjljxjsqb";
		pk = "xn||nd||xh||jlxmdm";
		tips = "���⽻�� - ��� - ���⽻����ѧ�����";
		tableName = "view_dwjljxjsq";
		querry += "and xn = '" + xn + "' ";
		querry += "and nd = '" + nd + "' ";
		if (isNull(nj)) {
			querry += "and 1=1 ";
		} else {
			querry += "and nj = '" + nj + "' ";
		}
		if (isNull(jlxmdm)) {
			querry += "and 1=1 ";
		} else {
			querry += "and jlxmdm = '" + jlxmdm + "' ";
		}
		if (isNull(bmdm)) {
			querry += "and 1=1 ";
		} else {
			querry += "and xydm = '" + bmdm + "' ";
		}
		if (userType.equalsIgnoreCase("xx")) {
			userDep = "";
			colList = new String[] { "bgcolor","r", "����", "�к�", "xn", "nd", "xh",
					"xm", "bjmc", "dwjlxmmc", "sqje", "xxsh" };
			sql = "select rownum r, rownum �к�,(case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
				+ " a.* from(select "
				+ pk
				+ " ����,a.* from "
				+ tableName
				+ " a" + querry + " and xysh='ͨ��' order by xxsh desc) a";
		} else {
			checkForm.setXydm(userDep);
			colList = new String[] { "bgcolor","r", "����", "�к�", "xn", "nd", "xh",
					"xm", "bjmc", "dwjlxmmc", "sqje", "xysh" };
			sql = "select rownum r, rownum �к�,(case when nvl(a.xysh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
				+ " a.* from(select "
				+ pk
				+ " ����,a.* from "
				+ tableName
				+ " a"
				+ querry
				+ " and xydm='"
				+ userDep
				+ "' order by xysh desc) a";
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator("select * from(select * from(" + sql + ") where r<="+checkForm.getPages().getStart()+checkForm.getPages().getPageSize()
					+") where r>"+checkForm.getPages().getStart(), new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		//TODO����ҳ
		checkForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs("select count(*) count from ("+sql+")", new String[] {}, "count")));
		sql = "select rychdm,rychmc from rychdmb";
		List rychList = dao.getList(sql, new String[] {}, new String[] {
				"rychdm", "rychmc" });
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("act", dataType);// �������ݲ�ѯ����
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("njList", dao.getNjList());// �����꼶�б�
		request.setAttribute("xnList", dao.getXnndList());// ����ѧ���б�
		request.setAttribute("xqList", dao.getXqList());// ����ѧ���б�
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", dao.getZyList(userDep));// ����רҵ�б�
		request.setAttribute("rychList", rychList);// ����רҵ�б�
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);// ���ͼ�¼��
		return mapping.findForward("success");
	}

	private ActionForward commMonCheckOne(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// �鿴���޸ĵ�����¼
		DAO dao = DAO.getInstance();
		CommanForm priseChkForm = (CommanForm) form;
		String act = request.getParameter("act");
		String pkVal = request.getParameter("pkVal");
		HttpSession session = request.getSession();
		String writeAble=CheckPower.checkUsrPower(session.getAttribute("userName").toString(), "comm_money_check.do")?"yes":"no";
		String pk = "xn||nd||xh||jlxmdm";
		String sql = "";
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String[] colList = new String[] {};
		if ((act == null) || !act.equalsIgnoreCase("save")) {
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select "
					+ pk
					+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,XXSH yesNo,DWJLXMMC,DWJLFSMC,DWJLLBMC,SQSJ,SQJE from view_dwjljxjsq where "
					+ pk + "='" + pkVal + "'";
			} else {
				sql = "select "
					+ pk
					+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,XYSH yesNo,DWJLXMMC,DWJLFSMC,DWJLLBMC,SQSJ,SQJE from view_dwjljxjsq where "
					+ pk + "='" + pkVal + "'";
			}
			colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ", "XYMC",
					"ZYMC", "BJMC", "XB", "DWJLXMMC", "yesNo", "DWJLFSMC",
					"DWJLLBMC", "SQSJ", "SQJE" };
			String[] rs = dao.getOneRs(sql, new String[] {}, colList);
			for (int i = 0; i < colList.length; i++) {
				rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
						: rs[i];
				request.setAttribute(colList[i], rs[i]);
			}
			priseChkForm.setYesNo(rs[11]);
			request.setAttribute("userType", userType);
			request.setAttribute("chkList", dao.getChkList(3));
			request.setAttribute("writeAble", writeAble);
			return mapping.findForward("success");
		}
		String yesNo = request.getParameter("yesNo");
		yesNo = DealString.toGBK(yesNo);
		if (userType.equalsIgnoreCase("xx")) {
			StandardOperation.update("dwjljxjsqb", new String[] {"xxsh"}, new String[] {yesNo}, pk, pkVal, request);
		} else {
			StandardOperation.update("dwjljxjsqb", new String[] {"xysh"}, new String[] {yesNo}, pk, pkVal, request);
		}
		return null;

	}

	private ActionForward xySetStuNum(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String xxdm = StandardOperation.getXxdm();
		DAO dao = DAO.getInstance();
		String currXn = "";
		String currNd = "";
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
			currXn = dao.getConf(2);
			currNd = dao.getConf(3);
		}else{
			currXn = Base.currXn;
			currNd = Base.currNd;
		}
//		String dqXn = dao.getConf(0);
//		String dqXq = dao.getConf(1);
//		String dqNd = dao.getConf(4);
		// ѧԺ�ϱ���������
		CommanForm priseForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String sql = "";// sql���
		int j = 0;
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("save")) {
			String[] stuNum = priseForm.getStuNum();
			int totNum = 0;
			String[] njL = priseForm.getNjL();
			for (int i = 0; i < stuNum.length; i++) {
				try {
					j = Integer.parseInt(stuNum[i]);
					totNum += j;
					sql = "update xyjxjrs set cprs=? where nd=? and xn=? and bmdm=?";
					dao.runUpdate(sql, new String[] { String.valueOf(j),
							currNd, currXn, njL[i] });
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
			sql = "update xyjxjrs set cprs=? where nd=? and xn=? and bmdm=?";
			dao.runUpdate(sql, new String[] { String.valueOf(totNum), currNd,
					currXn, userDep });
		}
		String bmmc = dao.getXymcById(userDep);
		String[] outV = new String[] { "bmdm", "xymc", "cprs" };
		sql = "select distinct a.xn,a.nd,a.bmdm,b.xymc,a.cprs from xyjxjrs a, "
			+ "(select distinct xydm,xymc from view_njxyzybj where "
			+ "xydm=? union all (select distinct zydm,zymc from view_njxyzybj "
			+ "where xydm=?)) b where a.bmdm=b.xydm and a.xn=? and a.nd=? and "
			+ "a.bmdm<>? order by a.cprs";
		List valList = dao.getList(sql, new String[] { userDep, userDep,
				currXn, currNd, userDep }, outV);
		request.setAttribute("jxjsqxn", currXn);
		request.setAttribute("jxjsqnd", currNd);
		request.setAttribute("rs", valList);
		request.setAttribute("bmmc", bmmc);
		return mapping.findForward("success");
	}    

	private ActionForward xySetBysStuNum(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String xxdm = StandardOperation.getXxdm();
		DAO dao = DAO.getInstance();
		String currXn = "";
		String currNd = "";
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
			currXn = dao.getConf(2);
			currNd = dao.getConf(3);
		}else{
			currXn = Base.currXn;
			currNd = Base.currNd;
		}
//		String dqXn = dao.getConf(0);
//		String dqXq = dao.getConf(1);
//		String dqNd = dao.getConf(4);
		// ѧԺ�ϱ���������
		CommanForm priseForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String sql = "";// sql���
		int j = 0;
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("save")) {
			String[] stuNum = priseForm.getStuNum();
			int totNum = 0;
			String[] njL = priseForm.getNjL();
			for (int i = 0; i < stuNum.length; i++) {
				try {
					j = Integer.parseInt(stuNum[i]);
					totNum += j;
					sql = "update xyjxjrs set bysrs=? where nd=? and xn=? and bmdm=?";
					dao.runUpdate(sql, new String[] { String.valueOf(j),
							currNd, currXn, njL[i] });
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
			sql = "update xyjxjrs set bysrs=? where nd=? and xn=? and bmdm=?";
			dao.runUpdate(sql, new String[] { String.valueOf(totNum), currNd,
					currXn, userDep });
		}
		String bmmc = dao.getXymcById(userDep);
		String[] outV = new String[] { "bmdm", "xymc", "bysrs" };
		sql = "select distinct a.xn,a.nd,a.bmdm,b.xymc,a.bysrs from xyjxjrs a, "
			+ "(select distinct xydm,xymc from view_njxyzybj where "
			+ "xydm=? union all (select distinct zydm,zymc from view_njxyzybj "
			+ "where xydm=?)) b where a.bmdm=b.xydm and a.xn=? and a.nd=? and "
			+ "a.bmdm<>? order by a.bysrs";
		List valList = dao.getList(sql, new String[] { userDep, userDep,
				currXn, currNd, userDep }, outV);
		request.setAttribute("jxjsqxn", currXn);
		request.setAttribute("jxjsqnd", currNd);
		request.setAttribute("rs", valList);
		request.setAttribute("bmmc", bmmc);
		return mapping.findForward("success");
	}

	private ActionForward viewTotStuNum(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjkjxyService zjkjService = new PjpyZjkjxyService();
		String xxdm = StandardOperation.getXxdm();
		DAO dao = DAO.getInstance();
		String currXn = "";
		String currNd = "";
		String jxjXn="";
		String jxjNd="";
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
			currXn = dao.getConf(2);
			currNd = dao.getConf(3);
		}else{
			currXn = Base.currXn;
			currNd = Base.currNd;
		}
		String sql_lrh="select JXJSQXN ,JXJSQND from xtszb ";
		HashMap<String, String> map_lrh = new HashMap<String, String>();
		map_lrh=dao.getMapNotOut(sql_lrh, new String [] {});
		jxjXn=map_lrh.get("jxjsqxn").toString();
		jxjNd=map_lrh.get("jxjsqnd").toString();
//		String dqXn = dao.getConf(0);
//		String dqXq = dao.getConf(1);
//		String dqNd = dao.getConf(4);
		// ѧУ�鿴��������
		String sql = "";// sql���
		String pjzq = zjkjService.getPjpySqzq();
		String[] inputValue = {jxjNd,jxjXn};
		if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLYDX)){
			sql = "select distinct bmdm,mc,cprs from view_bjly_xyjxjrs where nd=? and xn=? and bmlb='xydm' order by bmdm";
		}else{			
			if(StringUtils.isNull(pjzq)){
				//���������ڵ����ã�ʹ��Ĭ�ϵĲ�ѯ
				sql = "select distinct bmdm,mc,nj,cprs from view_xyjxjrs where nd=? and xn=? and bmlb='xydm' order by bmdm";
			} else if("xn".equalsIgnoreCase(pjzq)){
				//��������Ϊѧ��
				sql = "select distinct bmdm,mc,nj,cprs from view_xyjxjrs where xn=? and bmlb='xydm' order by bmdm";
				inputValue = new String[]{Base.getJxjsqxn()};
			} else if("nd".equalsIgnoreCase(pjzq)){
				//��������Ϊ���
				sql = "select distinct bmdm,mc,nj,cprs from view_xyjxjrs where nd=? and bmlb='xydm' order by bmdm";
				inputValue = new String[]{Base.getJxjsqnd()};
			} else if("xq".equalsIgnoreCase(pjzq)){
				//��������Ϊѧ��
				sql = "select distinct bmdm,mc,nj,cprs from view_xyjxjrs where xn=? and xqdm=? and bmlb='xydm' order by bmdm";
				inputValue = new String[]{Base.getJxjsqxn(),Base.getJxjsqxq()};
			}
		}
		List rs = dao.getList(sql, 
				              inputValue,
				              new String[] { "bmdm", "mc","nj", "cprs" });
		request.setAttribute("rs", rs);
		request.setAttribute("jxjsqxn", jxjXn);
		request.setAttribute("jxjsqnd", jxjNd);
		return mapping.findForward("success");
	}

	private ActionForward viewFpb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
//		String xxdm = StandardOperation.getXxdm();
//		String currXn = "";
//		String currNd = "";
//		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
//		currXn = dao.getConf(2);
//		currNd = dao.getConf(3);
//		}else{
//		currXn = Base.currXn;
//		currNd = Base.currNd;
//		}
		DAO dao = DAO.getInstance();
		String jxjXn="";
		String jxjNd="";
//		String dqXn = dao.getConf(0);
//		String dqXq = dao.getConf(1);
//		String dqNd = dao.getConf(4);
		String sql_lrh="select JXJSQXN ,JXJSQND from xtszb ";
		HashMap<String, String> map_lrh = new HashMap<String, String>();
		map_lrh=dao.getMapNotOut(sql_lrh, new String [] {});
		jxjXn=map_lrh.get("jxjsqxn").toString();
		jxjNd=map_lrh.get("jxjsqnd").toString();
		// �鿴��ѧ����������
		String[][] jxjInfo = new String[][] { { "0000000001", "ѧҵһ��", "1500" },
				{ "0000000002", "ѧҵ����", "1000" },
				{ "0000000003", "ѧҵ����", "500" },
				{ "0000000009", "��Ṥ��", "500" },
				{ "0000000010", "���ʵ��", "500" },
				{ "0000000011", "����", "500" } };
		String[] colList = null;
		String[] colListCN = null;
		Vector<Object> rs = new Vector<Object>();
		String sql = "";// sql���
		request.setAttribute("jxjsqxn", jxjXn);
		request.setAttribute("jxjsqnd", jxjNd);
		colList = new String[] { jxjXn, jxjNd };
//		sql = "select a.bmmc ��������,b.* from zxbz_xxbmdm a,"
//		+ "( select bmdm ���Ŵ���,max(decode(jxjdm,'"
//		+ jxjInfo[0][0] + "',zrs||'/'||sjrs,'0/0')) "
//		+ jxjInfo[0][1] + ",max(decode(jxjdm,'"
//		+ jxjInfo[1][0] + "',zrs||'/'||sjrs,'0/0')) "
//		+ jxjInfo[1][1] + ",max(decode(jxjdm,'"
//		+ jxjInfo[2][0] + "',zrs||'/'||sjrs,'0/0')) "
//		+ jxjInfo[2][1] + ",max(decode(jxjdm,'"
//		+ jxjInfo[3][0] + "',zrs||'/'||sjrs,'0/0')) "
//		+ jxjInfo[3][1] + ",max(decode(jxjdm,'"
//		+ jxjInfo[4][0] + "',zrs||'/'||sjrs,'0/0')) "
//		+ jxjInfo[4][1] + ",max(decode(jxjdm,'"
//		+ jxjInfo[5][0] + "',zrs||'/'||sjrs,'0/0')) "
//		+ jxjInfo[5][1] + ",((max(decode(jxjdm,'"
//		+ jxjInfo[0][0] + "',zrs,0))*"
//		+ jxjInfo[0][2] + "+" + "max(decode(jxjdm,'"
//		+ jxjInfo[1][0] + "',zrs,0))*"
//		+ jxjInfo[1][2] + "+" + "max(decode(jxjdm,'"
//		+ jxjInfo[2][0] + "',zrs,0))*"
//		+ jxjInfo[2][2] + "+" + "max(decode(jxjdm,'"
//		+ jxjInfo[3][0] + "',zrs,0))*"
//		+ jxjInfo[3][2] + "+" + "max(decode(jxjdm,'"
//		+ jxjInfo[4][0] + "',zrs,0))*"
//		+ jxjInfo[4][2] + "+" + "max(decode(jxjdm,'"
//		+ jxjInfo[5][0] + "',zrs,0))*"
//		+ jxjInfo[5][2] + ")||'/'||"
//		+ " (max(decode(jxjdm,'" + jxjInfo[0][0]
//		+ "',sjrs,0))*" + jxjInfo[0][2] + "+"
//		+ "max(decode(jxjdm,'" + jxjInfo[1][0]
//		+ "',sjrs,0))*" + jxjInfo[1][2] + "+"
//		+ "max(decode(jxjdm,'" + jxjInfo[2][0]
//		+ "',sjrs,0))*" + jxjInfo[2][2] + "+"
//		+ "max(decode(jxjdm,'" + jxjInfo[3][0]
//		+ "',sjrs,0))*" + jxjInfo[3][2] + "+"
//		+ "max(decode(jxjdm,'" + jxjInfo[4][0]
//		+ "',sjrs,0))*" + jxjInfo[4][2] + "+"
//		+ "max(decode(jxjdm,'" + jxjInfo[5][0]
//		+ "',sjrs,0))*" + jxjInfo[5][2]
//		+ " )) �����" + " from"
//		+ " ( select bmdm,jxjdm,sum(cprs*jxjbl) zrs,sum(jxjrs) sjrs from xyjxjrs "
//		+ " where xn=? and nd=?"
//		+ " group by bmdm,jxjdm"
//		+ " ) where jxjdm in('0000000001','0000000002','0000000003','0000000009','0000000010','0000000011') group by bmdm"
//		+ " ) b where a.bmdm=b.���Ŵ���";

		sql = "select a.bmmc ��������,a.bmdm,b.* from zxbz_xxbmdm a,"
			+ "( select bmdm ���Ŵ���,max(decode(jxjdm,'"
			+ jxjInfo[0][0] + "',sjrs,'0')) "
			+ jxjInfo[0][1] + ",max(decode(jxjdm,'"
			+ jxjInfo[1][0] + "',sjrs,'0')) "
			+ jxjInfo[1][1] + ",max(decode(jxjdm,'"
			+ jxjInfo[2][0] + "',sjrs,'0')) "
			+ jxjInfo[2][1] + ",max(decode(jxjdm,'"
			+ jxjInfo[3][0] + "',sjrs,'0')) "
			+ jxjInfo[3][1] + ",max(decode(jxjdm,'"
			+ jxjInfo[4][0] + "',sjrs,'0')) "
			+ jxjInfo[4][1] + ",max(decode(jxjdm,'"
			+ jxjInfo[5][0] + "',sjrs,'0')) "
			+ jxjInfo[5][1] + ",((max(decode(jxjdm,'"
			+ jxjInfo[0][0] + "',zrs,0))*"
			+ jxjInfo[0][2] + "+" + "max(decode(jxjdm,'"
			+ jxjInfo[1][0] + "',zrs,0))*"
			+ jxjInfo[1][2] + "+" + "max(decode(jxjdm,'"
			+ jxjInfo[2][0] + "',zrs,0))*"
			+ jxjInfo[2][2] + "+" + "max(decode(jxjdm,'"
			+ jxjInfo[3][0] + "',zrs,0))*"
			+ jxjInfo[3][2] + "+" + "max(decode(jxjdm,'"
			+ jxjInfo[4][0] + "',zrs,0))*"
			+ jxjInfo[4][2] + "+" + "max(decode(jxjdm,'"
			+ jxjInfo[5][0] + "',zrs,0))*"
			+ jxjInfo[5][2] + " )) ����� from"
			+ " ( select bmdm,jxjdm,sum(cprs*jxjbl) zrs,sum(jxjrs) sjrs from xyjxjrs "
			+ " where xn=? and nd=?"
			+ " group by bmdm,jxjdm"
			+ " ) where jxjdm in('0000000001','0000000002','0000000003','0000000009','0000000010','0000000011') group by bmdm"
			+ " ) b where a.bmdm=b.���Ŵ��� order by a.bmdm";
		colListCN = new String[] { "��������", "ѧҵһ��", "ѧҵ����", "ѧҵ����", "��Ṥ��",
				"���ʵ��", "����", "�����" };
		List topTr = dao.arrayToList(colListCN, colListCN);
		rs.addAll(dao.rsToVator(sql, colList, colListCN));
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		return mapping.findForward("success");
	}


	private ActionForward workCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// ��ʼ��ҳ�棬���ز�ѯ��Ϣ
		User user = getUser(request);
		CommanForm checkForm = (CommanForm) form;		
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		QgzxService service = new QgzxService();
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql���
		String querry = " where 1=1 ";// sql����
		String tableName = "";// ��ѯ��ϢԴ����Ϊ��ͼ����
		String rsNum = "0";// ���صļ�¼��
		String realTable = "";// ����Դ��
		String pk = "";// ����Դ����������ʽΪ���ֶ���||�ֶ���||�ֶ�������
		String dataType = request.getParameter("act");
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xxdm=StandardOperation.getXxdm();
		String userName = session.getAttribute("userName").toString();
		
		String xn = checkForm.getXn();
		String nd = checkForm.getNd();
		String xueqi = checkForm.getXq();

		String xqdm = request.getParameter("xqdm");
		String gwdm = DealString.toGBK(checkForm.getXmdm());
		String yrdwdm = DealString.toGBK(checkForm.getYrdwdm());
		String gwxz = checkForm.getGwxz();
		
		realTable = "gwxxb";
		pk = "gwdm||gwsbsj";
		tableName = "view_gwxx";
		
		//ѧ���û����ܷ���
		if("stu".equalsIgnoreCase(user.getUserType())){
			request.setAttribute("errMsg", "�ù��ܶ�ѧ���û������ţ�");
			return new ActionForward("/errMsg.do",false);
		}
		if(!isNull(xn)){
			querry += "and xn = '" + xn + "' ";
		}
		if(!isNull(nd)){
			querry += "and nd = '" + nd + "' ";
		}		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZBDX))
		{
			request.setAttribute("xxdm", xxdm);
		}else{
			if(!isNull(xueqi)){
				querry += "and xueqi = '" + xueqi + "' ";
			}
		}
		if (!isNull(gwdm)) {
			querry += "and gwdm like '%" + DealString.replaceImmitStr(gwdm) + "%' ";
		}
		if (!isNull(xqdm)) {
			querry += "and xq = '" + xqdm + "' ";
		}
		if (!isNull(yrdwdm)) {
			querry += "and sqdw = '" + yrdwdm + "' ";
		}
		if (!isNull(checkForm.getShjg())) {
			querry += "and shjg = '" + checkForm.getShjg() + "' ";
		}
		if (!isNull(checkForm.getGwflag())) {
			querry += "and gwflag = '" + checkForm.getGwflag() + "' ";
		}
		if (!isNull(gwxz)) {
			querry += "and gwxz = '" + gwxz + "' ";
		}
		if (service.isYrdwUser(userName)) {
			//���˵�λ��ѯ
			if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
				
				querry += " and exists (select 1 from yrdwdmb b where a.sqdw=b.yrdwdm and b.yrdwdm='"+service.getYrdwUser(userName)+"')";
			}else{
				querry += " and exists (select 1 from yrdwdmb b where a.sqdw=b.yrdwdm and b.dlm='"+userName+"')";
			}
		}else if("xy".equalsIgnoreCase(userType)){
			if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
				
				querry += " and exists (select * from yhb c where c.yhm=a.gwfbr and c.szbm='"+userDep+"' ) ";
			}
		}

		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZBDX)){
			//�б���ѧ
			colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd", 
					"gwdm", "gwxzmc", "xqmc", "gwsbsj", "xyrs", "syknss", "shjg" };
		} else if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){
			//��������
			colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd", 
					"gwdm", "gwxzmc", "xymc", "gwsbsj", "xyrs", "syknss", "shjg" };
		} else if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){
			//��������
			colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd", 
					"gwdm", "gwxzmc", "xymc", "gwsbsj", "xyboy", "xygirl", "shjg"};
		} else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
			//���ݴ�ѧ			
			if (!service.isYrdwUser(userName)) {
				//ѧУ�û���ѯ
				querry += "  and gwxz<>(select gwxzdm from gwxzdmb where gwxzmc='��ʱ��λ')";	
			}
			colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd", "xueqimc",
					"gwdm","yrdwmc", "gwsbsj", "xyrs", "sqsyrs","yrdwsh", "shjg" };
		}else if(Globals.XXDM_MJXY.equalsIgnoreCase(Base.xxdm)){
			colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd", "xueqimc",
					"gwdm", "gwxzmc", "yrdwmc", "gwsbsj", "xyrs","shjg" };
		} else{
			colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd", "xueqimc",
					"gwdm", "gwxzmc", "xqmc","yrdwmc", "gwsbsj", "xyrs", "sqsyrs","syknss","sqsyknss", "shjg" };
		}
		
		Pages pages = checkForm.getPages();
		sql = "select rownum �к�,(case nvl(a.shjg,'δ���') when 'ͨ��' then '#99CCFF' when 'δ���' then '#FFFFFF' else '#FF9999' end) bgcolor,"
			+ " a.* from(select "
			+ pk
			+ " ����,rownum r,a.* from "
			+ tableName
			+ " a"
			+ querry + " order by shjg desc) a where r>" + pages.getStart() 
			+ " and r<=" 
			+ (pages.getStart()+pages.getPageSize());
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HNGYDX)){
			//���Ϲ�ҵ��ѧ
			//ѧУ�û�
			sql = "select rownum �к�,(case nvl(a.shjg,'δ���') when 'ͨ��' then '#99CCFF' when 'δ���' then '#FFFFFF' else '#FF9999' end) bgcolor,"
				+ " a.* from(select "
				+ pk
				+ " ����,rownum r,a.* from "
				+ tableName
				+ " a"
				+ querry + " order by shjg desc)a where r>" 
				+ pages.getStart() + " and r<=" 
				+ (pages.getStart()+pages.getPageSize());
			colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd", "xueqimc",
					"gwdm", "gwxzmc", "xqmc", "gwsbsj", "xyrs", "syknss", "shjg" };
		} 
		pages.setMaxRecord(Integer.parseInt(
				                dao.getOneRs("select count(*)num from " + tableName + " a " + querry, 
				                		     new String[]{}, "num")));
		colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			rsNum = rs == null ? "0" : rs.size()+"";
		}

		if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){
			request.setAttribute("showbjlh", "showbjlh");
		}
		HashMap<String, String> paramter = new HashMap<String, String>();
		paramter.put("userName", userName);
		paramter.put("xn", checkForm.getXn());
		paramter.put("nd", checkForm.getNd());
		paramter.put("xq", checkForm.getXq());
		paramter.put("yrdwdm", checkForm.getYrdwdm());
		paramter.put("gwxzdm", checkForm.getGwxz());
		paramter.put("shFlag", "false");
		
		
		
		request.setAttribute("gwList",service.getGwmcxxList(paramter,"yes"));//�������ͨ����λ�б�
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("act", dataType);// �������ݲ�ѯ����
		request.setAttribute("njList", dao.getNjList());// �����꼶�б�
		request.setAttribute("xnList", Base.getXnndList());// ����ѧ���б�
		request.setAttribute("xqList", Base.getXqList());// ����ѧ���б�
		request.setAttribute("xiaoquList",dao.getXiaoQuList());// ����У���б�
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", dao.getZyList(""));// ����רҵ�б�
//		request.setAttribute("gwList", service.getGwmcList(userName,false));// ���͸�λ�б�
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);//�û�����
		request.setAttribute("yrdwList", service.getYrdwList(userName));//���˵�λ
		request.setAttribute("gwxzList", service.getGwxzList());//���˵�λ
		//����б�
		request.setAttribute("chkList", dao.getChkList(3));
		//·��
		request.setAttribute("path", "post_check.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("success");
	}

	private ActionForward postCheckOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// �鿴���޸ĵ�����¼
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		QgzxService service = new QgzxService();
		HngydxGwglDAO gwDao = new HngydxGwglDAO();
		String userType = dao.getUserType(session.getAttribute("userDep").toString());
		String userName = session.getAttribute("userName").toString();

		HashMap<String, String> map = new HashMap<String, String>();
		String xxdm = StandardOperation.getXxdm();		
		String act = request.getParameter("act");
		String pkVal = DealString.toGBK(request.getParameter("pkVal"));		
		String xhStr = "";
		String pk = "gwdm||gwsbsj";
		String sql = "";
		String xxyj = "";
		String sql1 = "";
		int count = 0;
		String[] input = null;
		boolean isYrdw = service.isYrdwUser(userName);
		//��ȡ�û��Ƿ���ָ�������˵�λ�û�
		gwDao.checkIsYrdw(userName);

		if ((act == null) || !act.equalsIgnoreCase("save")) {
			//��Ϣ��ѯ
			String[] colList=null;			
			String yhshzd = "shjg";//�û�����ֶ�
			if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
				//���ݴ�ѧ
				if(isYrdw){
					yhshzd = "yrdwsh";
				}else if("xy".equals(userType)){
					yhshzd ="yrdwsh";
				}
			}
			sql = "select "
				+ pk
				+ "," + yhshzd+ " yesNo,xqmc,nd,gwdm,xn,sqdw,xueqi,gwsbsj,gwxzmc,gzksrq,jybcbz,gzjsrq,xyrs,gzsj,zjf," 
				+ "spbcbz,sqsyrs,sqsyknss,gznr,sqdwyj,qgbyj,xgbyj,syknss,fzr,lxdh,a.jcfs,a.jcfsmc," 
				+ "yrdwmc,xueqimc,xymc,zcjf,gzdd,gwsl,gwtsyq,dwdz,xyboy,xygirl,ryyq,gzyd from view_gwxx a where "
				+ pk + "='" + pkVal + "'";
			colList = new String[] { pk, "yesNo", "xqmc", "nd",
					"gwdm", "xn", "sqdw", "xueqi", "gwsbsj", "gwxzmc",
					"gzksrq", "jybcbz", "gzjsrq", "xyrs", "gzsj", "zjf",
					"spbcbz", "sqsyrs", "sqsyknss", "gznr", "sqdwyj", "qgbyj",
					"xgbyj", "syknss", "fzr", "jcfs", "yrdwmc", "xueqimc","xymc",
					"zcjf", "gzdd", "gwsl", "gwtsyq", "dwdz", "xyboy", "xygirl",
					"ryyq","lxdh","gzyd", "jcfsmc"};
			//�㽭��ҵ��ѧ֮��ѧԺ
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJXY)){
				sql = "select "
					+ pk
					+ "," + yhshzd+ " yesNo,xqmc,nd,gwdm,xn,sqdw,xueqi,gwsbsj,gwxzmc,gzksrq,jybcbz,gzjsrq,xyrs,gzsj,zjf," 
					+ "spbcbz,sqsyrs,sqsyknss,gznr,sqdwyj,qgbyj,xgbyj,syknss,fzr,lxdh,a.jcfs,a.jcfsmc," 
					+ "yrdwmc,xueqimc,xymc,zcjf,gzdd,gwsl,gwtsyq,dwdz,xyboy,xygirl,ryyq,gzyd,sqkssj,sqjssj from view_gwxx a where "
					+ pk + "='" + pkVal + "'";
				colList = new String[] { pk, "yesNo", "xqmc", "nd",
						"gwdm", "xn", "sqdw", "xueqi", "gwsbsj", "gwxzmc",
						"gzksrq", "jybcbz", "gzjsrq", "xyrs", "gzsj", "zjf",
						"spbcbz", "sqsyrs", "sqsyknss", "gznr", "sqdwyj", "qgbyj",
						"xgbyj", "syknss", "fzr", "jcfs", "yrdwmc", "xueqimc","xymc",
						"zcjf", "gzdd", "gwsl", "gwtsyq", "dwdz", "xyboy", "xygirl",
						"ryyq","lxdh","gzyd", "jcfsmc","sqkssj","sqjssj"};
			}
		
			map = dao.getMap(sql, new String[] {}, colList);
			if(xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
				//��������Ժ
				sql = " select a.gwdm||a.gwsbsj pk"
					+",a.* ,a.shjg yesNo from view_gwxx a where a.gwdm||a.gwsbsj"
					+ "='" + pkVal + "'";
				colList = dao.getColumnName("select * from view_gwxx where 1=1");
				String[] newColList = new String[colList.length+2];
				for(int i=0;i<colList.length; i++){
					newColList[i] = colList[i].toLowerCase();
				}
				newColList[colList.length+0] = "pk";
				newColList[colList.length+1] = "yesNo";
				map = dao.getMap(sql, new String[]{}, newColList);
				map.put("gwdm||gwsbsj", map.get("pk"));
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){
				//��������
				String jcbz = dao.getOneRs("select mxsbc from gwsqsjb where rownum=1", new String[]{}, "mxsbc");
				map.put("jybcbz", jcbz);
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)|| xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
				//����Ƽ� ||�Ϻ����̼�����ѧ
				String[] sj = { "�����ޣ�7:30��8:20��", "��1-2�ڣ�8:30��10:05��",
						"��3-4�ڣ�10:25��12:00��", "���ݣ�12:00��13:45��",
						"��5-6�ڣ�13:50��15:25��", "��7-8�ڣ�15:30��17:05��",
				"�����ޣ�17:50��20:15��" };
				List<HashMap<String,String>> kxList = new ArrayList<HashMap<String,String>>();
				if (pkVal != null && !pkVal.equalsIgnoreCase("")) {		
					sql = "select to_number(xq-1) xq, sj, kxbz from gwgzsjb where gwdm||gwsbsj = ? and kxbz=1 order by xq,sj ";
					List<HashMap<String, String>> kxbz= dao.getList(sql, new String[]{pkVal}, new String[]{"xq","sj","kxbz"});
					request.setAttribute("kxbz", kxbz);
					request.setAttribute("kxbzNum", kxbz.size());
				}
				for (int i = 0; i < 7; i++) {
					HashMap<String, String> map2 = new HashMap<String, String>();
					map2.put("sj", sj[i]);
					map2.put("sjIndex", String.valueOf(i));
					kxList.add(map2);
				}
				request.setAttribute("whkxList", kxList);
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
				//��������Ժ
				XbemyQgzxDAO xbDao = new XbemyQgzxDAO();
				List<HashMap<String, String>>  filedList = xbDao.getFiledInfo("001");
				request.setAttribute("filedList", filedList);
				request.setAttribute("rsNum", filedList.size());
			}
			


			sql1 = "select rownum,xh,xm,bjmc,sfpks,lxdh,decode(xxyj,'ͨ��','CHECKED','') xxyj from view_xsgwxx where gwdm||gwsbsj" + "='" + pkVal + "' and xyyj='ͨ��'";// and nd=? and xn=? and xq=?
			if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){
				//��������
				//����Ա
				sql1 = "select rownum,xh,xm,bjmc,sfpks,lxdh,decode(xxyj,'ͨ��','CHECKED','') xxyj from view_xsgwxx where gwdm||gwsbsj" + "='" + pkVal + "' and fdyyj='ͨ��'";// and nd=? and xn=? and xq=?
			}
			List<HashMap<String, String>> li=dao.getListNotOut(sql1, new String[]{});//map.get("nd"),map.get("xn"),map.get("xueqi")
			count = li.size();
			request.setAttribute("count", Integer.toString(count));
			request.setAttribute("xssqList", li);// �������ݼ�    
			request.setAttribute("xhStr", xhStr);	
			request.setAttribute("xxdm", xxdm);
			request.setAttribute("knsbl", service.getKnsbl());
			request.setAttribute("rs", map);
			request.setAttribute("userType", userType);
			request.setAttribute("chkList", dao.getChkList(3));
			if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){
				//��������
				return new ActionForward("/qgzx/bjlhdx/post_check_one.jsp",false);
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){
				//��������
				return mapping.findForward("qgzx_ynys_gwsh");				
			} else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
				//���ݴ�ѧ				
				if (!isYrdw) {
					request.setAttribute("notYrdw", "yes");
				}
				return mapping.findForward("gzdx_gwsh");
			} else{
				return mapping.findForward("success");
			}
		}
		//������Ϣ
		String yesNo = DealString.toGBK(request.getParameter("yesNo"));
		String yhshzd = "shjg";
		String spbcbz = request.getParameter("spbcbz");//���������׼
		String sqsyrs = request.getParameter("sqsyrs");//����ʹ������
		String sqsyknss = request.getParameter("sqsyknss");//����ʹ����������
		if(StringUtils.isNull(sqsyrs)){
			sqsyrs = "0";
		}
		if(StringUtils.isNull(sqsyknss)){
			sqsyknss = "0";
		}
		String qgbyj = request.getParameter("qgbyj");
		String xgbyj = request.getParameter("xgbyj");
		String sqdwyj = request.getParameter("sqdwyj");		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){
			//��������
			sql = "update gwxxb set shjg=?,spbcbz=?,sqsyrs=?,sqsyknss=?,qgbyj=?,xgbyj=?,tmpsqsyrs=?,tmpsqsyknss=? where "
				+ pk + "=?";
			dao.runUpdate(sql, new String[] { yesNo, spbcbz, sqsyrs, sqsyknss,
					qgbyj, xgbyj, sqsyrs, sqsyknss, pkVal});
			dao.writeLog(sql, new String[] { yesNo, spbcbz, sqsyrs, sqsyknss,
					qgbyj, xgbyj, sqsyrs, sqsyknss}, null, "�޸ļ�¼��gwxxb-�ڹ���ѧ-��˸�λ", request);
		}else if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			//���ݴ�ѧ
			if(isYrdw){
				//���˵�λ���
				yhshzd = "yrdwsh";
			}
			
			if("xy".equals(userType)){
				//���˵�λ���
				yhshzd = "yrdwsh";
			}
			input = new String[]{yhshzd,"spbcbz","sqsyrs","sqsyknss","qgbyj","xgbyj","sqdwyj"};
			StandardOperation.update("gwxxb", input, new String[]{yesNo,spbcbz,sqsyrs,sqsyknss,qgbyj,xgbyj,sqdwyj}, pk, pkVal, request);
		}else{
			
			yhshzd = "shjg";
			input = new String[]{yhshzd,"spbcbz","sqsyrs","sqsyknss","qgbyj","xgbyj"};
			StandardOperation.update("gwxxb", input, new String[]{yesNo,spbcbz,sqsyrs,sqsyknss,qgbyj,xgbyj}, pk, pkVal, request);
		}
		xhStr = request.getParameter("xhStr");
		String xharray[] = xhStr.split("-");

		sql="select xh from xsgwxxb where "+pk+"=? ";
		xharray=dao.getRs(sql, new String []{pkVal}, "xh");
		for(int j=0;j<xharray.length;j++){
			if(yesNo.equals("ͨ��")){
				xxyj = request.getParameter(xharray[j]);
				if(xxyj == null){
					break;
				}else if("1".equalsIgnoreCase(xxyj)){
					xxyj = "ͨ��";
				}else{
					xxyj = "��ͨ��";
				}
			}else{
				xxyj = "��ͨ��";
			}
			if(Globals.XXDM_ZBDX.equalsIgnoreCase(xxdm))
			{	//�б���ѧ
				sql = "update xsgwxxb set xxyj=?,xyyj=? where " + pk + "=? and xh=?";
				dao.runUpdate(sql, new String[] { xxyj,xxyj,pkVal, xharray[j] });
				dao.writeLog(sql, new String[] { xxyj,xxyj}, null, "�޸ļ�¼��gwxxb-�ڹ���ѧ-���ѧ�������λ", request);
			}else{
				String flag = dao.getOneRs("select xxyj from xsgwxxb where " + pk + "=? and xh=?", new String[]{pkVal, xharray[j]}, "xxyj");
				if("ͨ��".equalsIgnoreCase(xxyj)){
					if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){
						//�������ϴ�ѧ
						if(!"ͨ��".equalsIgnoreCase(flag)){
							boolean pksbz = dao.isKns(xharray[j]);
							if(pksbz){
//								sqsyknss=Integer.toString(Integer.valueOf(sqsyknss)-1);
//								sqsyrs=Integer.toString(Integer.valueOf(sqsyrs)-1);
//								sql = "update gwxxb set sqsyknss=?,sqsyrs=? where " + pk + "=?";
//								dao.runUpdate(sql, new String[] { sqsyknss, sqsyrs, pkVal });
								sql = "update xsgwxxb set xxyj=?  where " + pk + "=? and xh=?";
								dao.runUpdate(sql, new String[] { xxyj, pkVal, xharray[j] });
							}else{
//								sqsyrs=Integer.toString(Integer.valueOf(sqsyrs)-1);
//								sql = "update gwxxb set sqsyrs=? where " + pk + "=?";
//								dao.runUpdate(sql, new String[] { sqsyrs, pkVal });
								sql = "update xsgwxxb set xxyj=? where " + pk + "=? and xh=?";
								dao.runUpdate(sql, new String[] { xxyj, pkVal, xharray[j] });
							}
						}
					}else{
						sql = "update xsgwxxb set xxyj=? where " + pk + "=? and xh=?";
						dao.runUpdate(sql, new String[] { xxyj, pkVal, xharray[j] });
					}
				}else{
					if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){
						//��������
						if("ͨ��".equalsIgnoreCase(flag)){
							boolean pksbz = dao.isKns(xharray[j]);
							if(pksbz){
//								sqsyknss=Integer.toString(Integer.valueOf(sqsyknss)+1);
//								sqsyrs=Integer.toString(Integer.valueOf(sqsyrs)+1);
//								sql = "update gwxxb set sqsyknss=?,sqsyrs=? where " + pk + "=?";
//								dao.runUpdate(sql, new String[] { sqsyknss, sqsyrs, pkVal });
								sql = "update xsgwxxb set xxyj=? where " + pk + "=? and xh=?";
								dao.runUpdate(sql, new String[] { xxyj, pkVal, xharray[j] });
							}else{
//								sqsyrs=Integer.toString(Integer.valueOf(sqsyrs)+1);
//								sql = "update gwxxb set sqsyrs=? where " + pk + "=?";
//								dao.runUpdate(sql, new String[] { sqsyrs, pkVal });
								sql = "update xsgwxxb set xxyj=? where " + pk + "=? and xh=?";
								dao.runUpdate(sql, new String[] { xxyj, pkVal, xharray[j] });
							}
						}else{
							sql = "update xsgwxxb set xxyj=? where " + pk + "=? and xh=?";
							dao.runUpdate(sql, new String[] { xxyj, pkVal, xharray[j] });
						}
					}else{
						sql = "update xsgwxxb set xxyj=? where " + pk + "=? and xh=?";
						dao.runUpdate(sql, new String[] { xxyj, pkVal, xharray[j] });
					}	
				}
				boolean pksbz = dao.isKns(xharray[j]);
				if(pksbz){
					dao.writeLog(sql, new String[] { sqsyknss, sqsyrs }, null, "�޸ļ�¼��gwxxb-�ڹ���ѧ-��˸�λ", request);
				}else{
					dao.writeLog(sql, new String[] { sqsyrs }, null, "�޸ļ�¼��gwxxb-�ڹ���ѧ-��˸�λ", request);
				}
				dao.writeLog(sql, new String[] { xxyj }, null, "�޸ļ�¼��xsgwxxb-�ڹ���ѧ-���ѧ�������λ", request);
			}	
		}
		request.setAttribute("result", "view");
		request.setAttribute("xxdm", xxdm);
		return new ActionForward("/postChkOne.do?act=view",false);
	}

	/**
	 *ѧ����λ��˲�ѯ
	 * */
	private ActionForward postStuCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		//�û���Ϣ
		User user = getUser(request);
		CommanForm checkForm = (CommanForm) form;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		QgzxService service = new QgzxService();
		
		List<String[]> rs = new ArrayList<String[]>();
		HashMap<String, String> map = new HashMap<String, String>();
		String xxdm = StandardOperation.getXxdm(); 
		String[] colList = null;
		String sql = "";// sql���
		String querry = " where a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj ";// sql����
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		//��ͼ��
		String tableName = "view_xsgwxx";
		// ���صļ�¼��
		String rsNum = "0";
		// ����Դ��
		String realTable = "xsgwxxb";		
		//��������
		String dataType = request.getParameter("act");
		//�û�����
		String userType = user.getUserType();
		String userDep=session.getAttribute("userDep").toString();
		//�û���
		String userName = user.getUserName();
		
		String bmdm = request.getParameter("xydm");
		String xn = request.getParameter("xn");
		String nd = request.getParameter("nd");
		String xq = request.getParameter("xq");	
		
		//ѧ���û����ܷ���
		if("stu".equalsIgnoreCase(user.getUserType())){
			request.setAttribute("errMsg", "�ù��ܶ�ѧ���û������ţ�");
			return new ActionForward("/errMsg.do",false);
		}
		
		XsgwglService gwglService = new XsgwglService();		
		//��ȡ�ڹ���ѧ����������Ϣ
		HashMap<String,String> confMap = gwglService.getQgzxConfig();		
		//�жϵ�ǰ��½���û��Ƿ�Ϊ����Ա
		boolean isFdy = "true".equalsIgnoreCase(session.getAttribute("fdyQx").toString()) ? true : false;
		//�жϵ�ǰ��¼���û��Ƿ�Ϊ���˵�λ
		boolean isYrdw = service.isYrdwUser(user.getUserName());
		
		// ����Դ������
		String pk = "a.xh||a.gwdm||a.sqsj";
		checkForm.setPk(pk);
		checkForm.setTableName(tableName);
		//��ȡ��λ������Ϣ
		String gwdm = DealString.toGBK(checkForm.getXmdm());
		if (StringUtils.isNotNull(gwdm)) {
			if(xxdm.equalsIgnoreCase(Globals.XXDM_JGSDX)){
				checkForm.setGwdm(gwdm.split("-")[0]);
			}else{
				checkForm.setGwdm(gwdm);
			}
		}
		
		if(StringUtils.isNull(xn) && !"go".equalsIgnoreCase(request.getParameter("go"))){
			checkForm.setXn(confMap.get("xn"));
		}else{
			checkForm.setXn(xn);
		}		
		if(StringUtils.isNull(nd) && !"go".equalsIgnoreCase(request.getParameter("go"))){
			checkForm.setNd(confMap.get("nd"));
		}else{
			checkForm.setNd(nd);
		}
		if(StringUtils.isNull(xq) && !"go".equalsIgnoreCase(request.getParameter("go"))){
			checkForm.setXq(confMap.get("xq"));
		}else{
			checkForm.setXq(xq);
		}
		//���˵�λ
		if(Globals.XXDM_ZJJSZYJSXY.equalsIgnoreCase(Base.xxdm)){
			
		}else if(isYrdw && StringUtils.isNull(checkForm.getYrdwdm())){
			checkForm.setYrdwdm(service.getYrdwUser(userName));
		}
		if(Globals.XXDM_HBJTZYJSXY.equalsIgnoreCase(Base.xxdm) || Globals.XXDM_SZWBZYJSXY.equalsIgnoreCase(Base.xxdm) || Globals.XXDM_WHSYFWXY.equalsIgnoreCase(Base.xxdm)){
			//������ͨ,�������ְҵ����ѧԺ  ѧԺ���---ѧУ���
			if("xy".equalsIgnoreCase(user.getUserType())){
				checkForm.setXydm(user.getUserDep());
			}
		}
		//���˵�λ���
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JGSDX)){
			map.put("yrdwsh", "yes");
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HNGYDX)){
			//���Ϲ�ҵ��ѧ 
			map.put("fdysh", "yes");
		}
		if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm)){
			if(StringUtils.isNull(request.getParameter("go")) && ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)) && !isYrdw){
				checkForm.setXyyj("ͨ��");
			}
		}
		
		// =========end ���ΰ 2009/3/31===========
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(gwglService.queryXsgwsh(checkForm,user));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
			topTr = gwglService.getXsgwxxShTopTr(user);
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JGSDX) && !isNull(request.getParameter("zdsh")) && Integer.parseInt(rsNum) > 0) {
			//����ɽ��ѧ
			//���Զ����ͨ����ѧ�������״̬��XYYJ��XXYJ��Ϊͨ��
			List<String[]> updateStuList = dao.rsToVator(sql, new String[] {}, colList);
			String tempSql = "select * from xsgwxxb  where xh||gwdm||sqsj=?";
			String[] getOneResult = dao.getOneRs(tempSql, new String[] {updateStuList.get(0)[1]}, new String[] {"gwdm","gwsbsj"});
			String jobPk = getOneResult[0] + getOneResult[1];
			//�õ���������������ѧ����ѧ��
			StringBuffer sb = new StringBuffer();
			QgzxDao qgzxDao = new QgzxDao();
			List<String> stuList = qgzxDao.getOKStuid(getOneResult[0] + "-" + getOneResult[1]);
			for(int i=0;i<stuList.size();i++){
				if(i < stuList.size()-1){
					sb.append(stuList.get(i) + ",");
				}else{
					sb.append(stuList.get(i));
				}
			}
			//������������֧
			if(request.getParameter("zdsh").equalsIgnoreCase("1")) {
				//��֧1������������ֱ�ӽ�ѧ����״̬����Ϊͨ��������������Ϊ��ͨ��
				if(isYrdw) {	
					qgzxDao.updateCheckStu(jobPk, sb, request, "XYYJ","1");
				}else if(user.getUserType().equalsIgnoreCase("xx")) {
					//ѧУ
					qgzxDao.updateCheckStu(jobPk, sb, request, "XXYJ","1");
				}
				rs.clear();
				rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			}else if(request.getParameter("zdsh").equalsIgnoreCase("3")) {
				//��֧2�����������������������Զ���ˣ������ȿ�������������ѧ������
				tempSql = "select * from  gwxxb where GWDM||GWSBSJ=?";
				String[] oneRes = dao.getOneRs(tempSql, new String[] {jobPk}, new String[] {"xyrs"});
				int needNum = Integer.parseInt(oneRes[0]);
				//ֻ��ȡһ��������������ѧ����ѧ�Ž��и���
				sb = new StringBuffer();
				for(int i=0;i < needNum;i++){
					if(i < needNum-1){
						sb.append(stuList.get(i) + ",");
					}else{
						sb.append(stuList.get(i));
					}
				}
				if(isYrdw) {	
					qgzxDao.updateCheckStu(jobPk, sb, request, "XYYJ","3");
				}else if(userType.equalsIgnoreCase("xx")) {
					//ѧУ
					qgzxDao.updateCheckStu(jobPk, sb, request, "XXYJ","3");
				}
				rs.clear();
				rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			}else if(request.getParameter("zdsh").equalsIgnoreCase("2")){
				//��֧3�������������������Ҳ����Զ���ˣ���ֻ��ʾ����������ѧ������ 
				String query = querry.replace(" and xyyj='ͨ��'", " and 1=1");
				sql = "select rownum �к�,(case nvl(a.xxyj,'δ���') when 'ͨ��' then '#99CCFF' when 'δ���' then '#FFFFFF' else '#FF9999' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " ����,a.* from "
					+ tableName
					+ " a,view_gwxx b"
					+ query + " and xh in (" + sb.toString() + ") order by xyyj desc) a";
				rs.clear();
				rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			}
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){
			//�������ϴ�ѧ
			if("".equalsIgnoreCase(bmdm) || bmdm == null){
				bmdm = "%";
			}
			sql = "select xn,nd,xq from gwsqsjb where rownum=1";
			colList = dao.getOneRs(sql, new String[] {}, new String[] { "xn", "nd", "xq" });
			sql = "select a.gwdm,count(*) rs from view_xsgwxx a,view_gwxx b where a.xxyj='ͨ��' and a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj " +
			"and a.xn=? and a.xq=? and a.nd=? and a.xydm like ? group by a.gwdm";
			List<HashMap<String, String>> tsgwxx = dao.getList(sql, new String[]{colList[0],colList[2],colList[1],bmdm}, new String[]{"gwdm","rs"});
			request.setAttribute("tsgwList", tsgwxx);
			request.setAttribute("showbjlh", "showbjlh");
			System.out.println("bjlh");
		}
		
		HashMap<String, String> paramter = new HashMap<String, String>();
		paramter.put("userName", userName);
		paramter.put("xn", checkForm.getXn());
		paramter.put("nd", checkForm.getNd());
		paramter.put("xq", checkForm.getXq());
		paramter.put("yrdwdm", checkForm.getYrdwdm());
		paramter.put("gwxzdm", checkForm.getGwxz());
		request.setAttribute("gwList",service.getGwmcxxList(paramter,"no"));//�������ͨ����λ�б�
		
		request.setAttribute("flag", service.checkAudiTime());
		writeAble = (Base.chkUPower(user.getUserName(), "post_stu_check.do", (userType.equalsIgnoreCase("stu"))) == 1) ? "yes" : "no";
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("act", dataType);// �������ݲ�ѯ����
//		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("xnList", dao.getXnndList());// ����ѧ���б�
		request.setAttribute("xqList", dao.getXqList());// ����ѧ���б�
		request.setAttribute("xiaoquList", dao.getXiaoQuList());// ����У���б�
		
		List<HashMap<String,String>>yrdwList=new ArrayList<HashMap<String,String>>();
		
		// -----------�㽭���費��Ҫ�������˵�λ-----------
		if(Globals.XXDM_ZJJSZYJSXY.equalsIgnoreCase(xxdm)){
			yrdwList=service.getYrdwList();
		}else {
			yrdwList= service.getYrdwList(user.getUserName());
		}
		request.setAttribute("yrdwList",yrdwList);// ���˵�λ�б�
		request.setAttribute("gwxzList", service.getGwxzList());//��λ����
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);// ���ͼ�¼��
		request.setAttribute("xxdm", xxdm);
		FormModleCommon.setNjXyZyBjList(request);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("path", "post_stu_check.do");
		FormModleCommon.commonRequestSet(request);
		//�人��ҵ����Ա���û��������ݷ�Χ
		if(xxdm.equalsIgnoreCase(Globals.XXDM_WHSYFWXY)){
			FormModleCommon.setNjXyZyBjListForFdyBzr(request);
			FormModleCommon.setNdXnXqList(request);
		}
		//�㽭��ͨ����Ա���û��������ݷ�Χ
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJTZYJSXY)){
			FormModleCommon.setNjXyZyBjListForFdyBzr(request);
			FormModleCommon.setNdXnXqList(request);
			request.setAttribute("isFdy", session.getAttribute("fdyQx").toString());
			request.setAttribute("isBzr", session.getAttribute("bzrQx").toString());
			
			if(userType.equals("xy")){
				if ("true".equalsIgnoreCase(session.getAttribute("fdyQx").toString())) {
					//checkForm.setXydm("");
				}else if("true".equalsIgnoreCase(session.getAttribute("bzrQx").toString())) {
					//checkForm.setXydm("");
				}else{
					checkForm.setXydm(userDep);
				}
			}
		}
		request.setAttribute("userDep", userDep);
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
			return mapping.findForward("zgdzdx");
		}else{
			return mapping.findForward("success");
		}
	}	

	/**
	 * Method postStuCheckOne ѧ����λ���
	 * @param mapping
	 * @param form
	 * @param request 
	 * @param response
	 * @return ActionForward
	 * **/
	private ActionForward postStuCheckOne(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// �鿴���޸ĵ�����¼
		CommanForm model = (CommanForm) form;
		DAO dao = DAO.getInstance();
		QgzxService service = new QgzxService();
		XsxxglService xsxxglService = new XsxxglService();
		HashMap<String, String> map = new HashMap<String, String>();
		boolean flag = false;
		String act = request.getParameter("act");
		String pkVal = DealString.toGBK(request.getParameter("pkVal"));
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		request.setAttribute("isFdy", session.getAttribute("fdyQx").toString());
		request.setAttribute("isBzr", session.getAttribute("bzrQx").toString());
		String pk = "xh||gwdm||sqsj";
		String sql = "";
		String xxdm = StandardOperation.getXxdm();
		String xh = DealString.toGBK(request.getParameter("xh"));
		String userType = dao.getUserType(session.getAttribute("userDep").toString());
		sql = "select * from fdyxxb where zgh='" + userName + "'";
		String tag = dao.returntag(sql, new String []{});
		String yrdwTag = "empty";

		// �ж��Ƿ������˵�λ�û�
		String num = dao.getOneRs("select count(*) num from yrdwdmb where dlm=?", new String[]{userName}, "num");
		if(Integer.parseInt(num)>0){
			yrdwTag = "notempty";
		}		

		if ((act == null) || !act.equalsIgnoreCase("save")) {
			//��Ϣ��ѯ
			//������Ϣ ����Ա���--->���˵�λ���
			if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {
				//������Ϣ
				if("notempty".equalsIgnoreCase(tag)){
					sql = "select "
						+ pk
						+ ",xzb,zzmmm,cxdd,jg,sfyytytp,sfgr,sflszn,mnyjngzfy,sfwsrh,sfzbh,sfdbh,sffmsxg,sfcnh,sfdsr,jtcy1_xm,jtcy1_cw,jtcy1_nl,jtcy1_jkzk,jtcy1_gzdwjzw,jtcy1_nsr,jtcy2_xm,jtcy2_cw,jtcy2_nl,jtcy2_jkzk,jtcy2_gzdwjzw,jtcy2_nsr,jtcy3_xm,jtcy3_cw,jtcy3_nl,jtcy3_jkzk,jtcy3_gzdwjzw,jtcy3_nsr,jtcy4_xm,jtcy4_cw,jtcy4_nl,jtcy4_jkzk,jtcy4_gzdwjzw,jtcy4_nsr,jtcy5_xm,jtcy5_cw,jtcy5_nl,jtcy5_jkzk,jtcy5_gzdwjzw,jtcy5_nsr,jtjjknqk,sfpks,btzw,jtmntg,hjmnsqfy,qjxfs,yhdkzljje,mqqgzxqk,yhtc,xh,gwdm,sqsj,xssq,xyyj,xxyj,bz,kcjqgzxsj,lxdh,xn,nd,xq,gwsbsj,xsgzqk,gzbx,kh,gzjl,fdyyj,xxqk,bkhywbjgkc,xqmc,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,zzmm,fdyyj yesNo from view_jsxx_xsgwxx where "
						+ pk + "='" + pkVal + "'";
				} else {
					sql = "select "
						+ pk
						+ ",xzb,zzmmm,cxdd,jg,sfyytytp,sfgr,sflszn,mnyjngzfy,sfwsrh,sfzbh,sfdbh,sffmsxg,sfcnh,sfdsr,jtcy1_xm,jtcy1_cw,jtcy1_nl,jtcy1_jkzk,jtcy1_gzdwjzw,jtcy1_nsr,jtcy2_xm,jtcy2_cw,jtcy2_nl,jtcy2_jkzk,jtcy2_gzdwjzw,jtcy2_nsr,jtcy3_xm,jtcy3_cw,jtcy3_nl,jtcy3_jkzk,jtcy3_gzdwjzw,jtcy3_nsr,jtcy4_xm,jtcy4_cw,jtcy4_nl,jtcy4_jkzk,jtcy4_gzdwjzw,jtcy4_nsr,jtcy5_xm,jtcy5_cw,jtcy5_nl,jtcy5_jkzk,jtcy5_gzdwjzw,jtcy5_nsr,jtjjknqk,sfpks,btzw,jtmntg,hjmnsqfy,qjxfs,yhdkzljje,mqqgzxqk,yhtc,xh,gwdm,sqsj,xssq,xyyj,xxyj,bz,kcjqgzxsj,lxdh,xn,nd,xq,gwsbsj,xsgzqk,gzbx,kh,gzjl,fdyyj,xxqk,bkhywbjgkc,xqmc,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,zzmm,xyyj yesNo from view_jsxx_xsgwxx where "
						+ pk + "='" + pkVal + "'";
				}
				String[] colList = new String[] { pk, "xzb", "zzmmm", "cxdd", "jg", "sfyytytp", "sfgr", "sflszn", "mnyjngzfy", "sfwsrh", "sfzbh", "sfdbh", "sffmsxg", "sfcnh", "sfdsr", "jtcy1_xm", "jtcy1_cw", "jtcy1_nl", "jtcy1_jkzk", "jtcy1_gzdwjzw", "jtcy1_nsr", "jtcy2_xm", "jtcy2_cw", "jtcy2_nl", "jtcy2_jkzk", "jtcy2_gzdwjzw", "jtcy2_nsr", "jtcy3_xm", "jtcy3_cw", "jtcy3_nl", "jtcy3_jkzk", "jtcy3_gzdwjzw", "jtcy3_nsr", "jtcy4_xm", "jtcy4_cw", "jtcy4_nl", "jtcy4_jkzk", "jtcy4_gzdwjzw", "jtcy4_nsr", "jtcy5_xm", "jtcy5_cw", "jtcy5_nl", "jtcy5_jkzk", "jtcy5_gzdwjzw", "jtcy5_nsr", "jtjjknqk", "sfpks", "btzw", "jtmntg", "hjmnsqfy", "qjxfs", "yhdkzljje", "mqqgzxqk", "yhtc", "xh", "gwdm", "sqsj", "xssq", "xyyj", "xxyj", "bz", "kcjqgzxsj", "lxdh", "xn", "nd", "xq", "gwsbsj", "xsgzqk", "gzbx", "kh", "gzjl", "fdyyj", "xxqk", "bkhywbjgkc", "xqmc", "xm", "xb", "nj", "xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "zzmm", "yesNo" };
				String[] rs = dao.getOneRs(sql, new String[] {}, colList);
				for(int i = 0; i < colList.length; i++){
					if(null == rs[i]){
						map.put(colList[i], "");
					}else{
						map.put(colList[i], rs[i]);
					}
				}
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){
				//�������� ����Ա���--->ѧУ���
				if("notempty".equalsIgnoreCase(tag)){
					//����Ա
					sql = "select "
						+ pk
						+ ",a.xh,a.nd,a.xm,a.xn,a.xb,a.gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj,a.nj,a.sqsj,a.xymc,a.sfpks,a.zymc,a.lxdh,a.bjmc,a.fdyyj yesNo,a.zzmm zzmmm,a.jtcy,a.jtysr,a.gjzxdk,a.pkdj,a.xg,a.yhtc,a.zzqk,a.gzjl,a.ldyx,a.xssq,(select mzmc from view_stu_details b where a.xh=b.xh)mzmc,(select jtszd from view_xsfzxx b where a.xh=b.xh)jtdz,(select yb from view_xsfzxx b where a.xh=b.xh)jtyb,(select lxdh  from view_xsfzxx b where a.xh=b.xh)jtdh,bh,gh,xxshyj from view_xsgwxx a where "
						+ pk + "='" + pkVal + "'";
				}else {
					//ѧУ
					sql = "select "
						+ pk
						+ ",a.xh,a.nd,a.xm,a.xn,a.xb,a.gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj,a.nj,a.sqsj,a.xymc,a.sfpks,a.zymc,a.lxdh,a.bjmc,a.xxyj yesNo,a.zzmm zzmmm,a.jtcy,a.jtysr,a.gjzxdk,a.pkdj,a.xg,a.yhtc,a.zzqk,a.gzjl,a.ldyx,a.xssq,(select mzmc from view_stu_details b where a.xh=b.xh)mzmc,(select jtszd from view_xsfzxx b where a.xh=b.xh)jtdz,(select yb from view_xsfzxx b where a.xh=b.xh)jtyb,(select lxdh  from view_xsfzxx b where a.xh=b.xh)jtdh,bh,gh,xxshyj from view_xsgwxx a where "
						+ pk + "='" + pkVal + "'";
				}
				String[] colList = new String[] { pk, "xh", "nd", "xm", "xn",
						"xb", "gwdm", "nj", "sqsj", "xymc", "sfpks", "zymc",
						"lxdh", "bjmc", "yesNo", "zzmmm","jtcy","jtysr",
						"gjzxdk","pkdj","xg","yhtc","zzqk","gzjl","ldyx","xssq",
						"mzmc","jtdz","jtyb","jtdh","bh","gh","xxshyj","gwdmgwsbsj"};
				String[] rs = dao.getOneRs(sql, new String[] {}, colList);
				for(int i = 0; i < colList.length; i++){
					if(null == rs[i]){
						map.put(colList[i], "");
					}else{
						map.put(colList[i], rs[i]);
					}
				}
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)){
				//����ѧԺ ����Ա���---->���˵�λ���
				if ("notempty".equalsIgnoreCase(tag)) {
					sql = "select "
						+ pk
						+ ",xh,nd,xm,xn,xb,gwdm,nj,sqsj,xymc,sfpks,zymc,lxdh,bjmc,fdyyj yesNo,zzmm zzmmm,jtzyjjly,kcjqgzxsj,yhtc,jtcy1_xm,jtcy1_cw,jtcy1_gzdwjzw,jtcy1_nsr,jtcy2_xm,jtcy2_cw,jtcy2_gzdwjzw,jtcy2_nsr,jtcy3_xm,jtcy3_cw,jtcy3_gzdwjzw,jtcy3_nsr from view_xsgwxx where "
						+ pk + "='" + pkVal + "'";
				}else{
					sql = "select "
						+ pk
						+ ",xh,nd,xm,xn,xb,gwdm,nj,sqsj,xymc,sfpks,zymc,lxdh,bjmc,xyyj yesNo,zzmm zzmmm,jtzyjjly,kcjqgzxsj,yhtc,jtcy1_xm,jtcy1_cw,jtcy1_gzdwjzw,jtcy1_nsr,jtcy2_xm,jtcy2_cw,jtcy2_gzdwjzw,jtcy2_nsr,jtcy3_xm,jtcy3_cw,jtcy3_gzdwjzw,jtcy3_nsr from view_xsgwxx where "
						+ pk + "='" + pkVal + "'";
				}
				String[] colList = new String[] { pk, "xh", "nd", "xm", "xn","xb", "gwdm", "nj", "sqsj", "xymc", 
						"sfpks", "zymc","lxdh", "bjmc", "yesNo", "zzmmm","jtzyjjly","kcjqgzxsj",
						"yhtc","jtcy1_xm","jtcy1_cw","jtcy1_gzdwjzw","jtcy1_nsr","jtcy2_xm","jtcy2_cw",
						"jtcy2_gzdwjzw","jtcy2_nsr","jtcy3_xm","jtcy3_cw","jtcy3_gzdwjzw","jtcy3_nsr" };
				String[] rs = dao.getOneRs(sql, new String[] {}, colList);
				for(int i = 0; i < colList.length; i++){
					if(null == rs[i]){
						map.put(colList[i], "");
					}else{
						map.put(colList[i], rs[i]);
					}
				}
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)){
				//�㶫Ů��ְҵ����ѧԺ ����Ա��� ---->���˵�λ���
				if ("notempty".equalsIgnoreCase(tag)) {
					sql = "select "
						+ pk
						+ ",xh,nd,xm,xn,xb,gwdm,nj,sqsj,xymc,sfpks,zymc,lxdh,bjmc,fdyyj yesNo,kcjqgzxsj,yhtc,lxdh,ssbh,jtnsr,bhgkm,wjcf from view_xsgwxx where "
						+ pk + "='" + pkVal + "'";
				}else{
					sql = "select "
						+ pk
						+ ",xh,nd,xm,xn,xb,gwdm,nj,sqsj,xymc,sfpks,zymc,lxdh,bjmc,xyyj yesNo,kcjqgzxsj,yhtc, lxdh,ssbh,jtnsr,bhgkm,wjcf from view_xsgwxx where "
						+ pk + "='" + pkVal + "'";
				}
				String[] colList = new String[] { pk, "xh", "nd", "xm", "xn","xb", "gwdm", "nj", "sqsj", "xymc", 
						"sfpks", "zymc","lxdh", "bjmc", "yesNo","kcjqgzxsj","yhtc", "lxdh","ssbh","jtnsr","bhgkm","wjcf"};
				String[] rs = dao.getOneRs(sql, new String[] {}, colList);
				for(int i = 0; i < colList.length; i++){
					if(null == rs[i]){
						map.put(colList[i], "");
					}else{
						map.put(colList[i], rs[i]);
					}
				}			
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
				//��������Ժ ����Ա��� ---->���˵�λ���
				if ("notempty".equalsIgnoreCase(tag)) {
					sql = "select a.*,a.xh||a.gwdm||a.sqsj pk a.fdyyj yesNo from view_xsgwxx a where "
						+ "a.xh||a.gwdm||a.sqsj='" + pkVal + "'";
				}else{
					sql = "select a.*, a.xh||a.gwdm||a.sqsj pk, a.xyyj yesNo from view_xsgwxx a where "
						+ "a.xh||a.gwdm||a.sqsj='" + pkVal + "'";
				}
				String[] column = dao.getColumnName("select * from view_xsgwxx where 1=1");
				String[] colList = new String[column.length + 2];
				for(int i=0; i<column.length; i++){
					String temp = column[i];
					temp = temp == null || "".equalsIgnoreCase(temp) ? "" : temp.toLowerCase();
					colList[i] = temp;
				}
				colList[column.length] = "pk";
				colList[column.length + 1] = "yesNo";
				map = dao.getMap(sql, new String[]{}, colList);
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
				//�й����ʴ�ѧ ----->���˵�λ���
				String shType = "xxyj";
				
				sql = "select a.*, (select mzmc from view_xsxxb where xh = a.xh) mzmc,a.xh||a.gwdm||a.sqsj pk, "+shType+" yesNo from view_xsgwxx a where " + "a.xh||a.gwdm||a.sqsj='" + pkVal + "'";

				String[] colList = new String[] { "pk", "xh", "nd", "xm", "xn","xb", "gwdm", "nj", "sqsj", "xymc", 
						"sfpks", "zymc","bjmc", "yesNo","kcjqgzxsj","lxdh","yhtc","yjshf","zzmm","bz","xssq","mzmc" ,"zwxzdm"};

				map = dao.getMap(sql, new String[]{},colList);
				map.put("sfpks", dao.isKns(map.get("xh")) == true ? "��" : "��");
				request.setAttribute("zwxzdmList", service.getZwxzdmList());
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)){
				//���������ѧ ѧԺ���---->���˵�λ���------>ѧУ���
				if("notempty".equalsIgnoreCase(yrdwTag)){
					//���˵�λ���
					sql = "select "
						+ pk
						+ ",xh,nd,xm,xn,xb,gwdm,nj,sqsj,xymc,sfpks,zymc,lxdh,bjmc,xyyj yesNo,zzmm zzmmm from view_xsgwxx where "
						+ pk + "='" + pkVal + "'";
				}else if(userType != null && userType.equalsIgnoreCase("xy")){
					//ѧԺ���
					sql = "select "
						+ pk
						+ ",xh,nd,xm,xn,xb,gwdm,nj,sqsj,xymc,sfpks,zymc,lxdh,bjmc,fdyyj yesNo,zzmm zzmmm from view_xsgwxx where "
						+ pk + "='" + pkVal + "'";
				}else{
					//ѧУ���
					sql = "select "
						+ pk
						+ ",xh,nd,xm,xn,xb,gwdm,nj,sqsj,xymc,sfpks,zymc,lxdh,bjmc,xxyj yesNo,zzmm zzmmm from view_xsgwxx where "
						+ pk + "='" + pkVal + "'";
				}
				String[] colList = new String[] { pk, "xh", "nd", "xm", "xn",
						"xb", "gwdm", "nj", "sqsj", "xymc", "sfpks", "zymc",
						"lxdh", "bjmc", "yesNo", "zzmmm" };

				map = dao.getMap(sql, new String[]{}, colList);
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_HNGYDX) || xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)){
				//���Ϲ�ҵ��ѧ || �й�����ѧԺ
				if("notempty".equalsIgnoreCase(yrdwTag)){
					//���˵�λ���
					sql = "select "
						+ pk
						+ ",xh,nd,xm,xn,xb,gwdm,nj,sqsj,xymc,sfpks,zymc,lxdh,bjmc,xyyj yesNo,zzmm zzmmm,gzjl,yhtc,xssq sqly,bz from view_xsgwxx where "
						+ pk + "='" + pkVal + "'";
				}else if(userType != null && userType.equalsIgnoreCase("xy")){
					//ѧԺ���
					sql = "select "
						+ pk
						+ ",xh,nd,xm,xn,xb,gwdm,nj,sqsj,xymc,sfpks,zymc,lxdh,bjmc,fdyyj yesNo,zzmm zzmmm,gzjl,yhtc,xssq sqly,bz from view_xsgwxx where "
						+ pk + "='" + pkVal + "'";
				}else{
					//ѧУ���
					sql = "select "
						+ pk
						+ ",xh,nd,xm,xn,xb,gwdm,nj,sqsj,xymc,sfpks,zymc,lxdh,bjmc,xxyj yesNo,zzmm zzmmm,gzjl,yhtc,xssq sqly,bz from view_xsgwxx where "
						+ pk + "='" + pkVal + "'";
				}
				String[] colList = new String[] { pk, "xh", "nd", "xm", "xn",
						"xb", "gwdm", "nj", "sqsj", "xymc", "sfpks", "zymc",
						"lxdh", "bjmc", "yesNo", "zzmmm","gzjl","yhtc","sqly","bz" };
				map = dao.getMap(sql, new String[]{}, colList);
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){
				//�������ϴ�ѧ
				//���˵�λ���
				sql = "select "
					+ pk
					+ ",xh,nd,xm,xn,xb,gwdm,nj,sqsj,xymc,sfpks,zymc,lxdh,bjmc,xyyj yesNo,zzmm zzmmm,gzjl from view_xsgwxx where "
					+ pk + "='" + pkVal + "'";
				String[] colList = new String[] { pk, "xh", "nd", "xm", "xn",
						"xb", "gwdm", "nj", "sqsj", "xymc", "sfpks", "zymc",
						"lxdh", "bjmc", "yesNo", "zzmmm" };

				map = dao.getMap(sql, new String[]{}, colList);
			}else if( xxdm.equalsIgnoreCase(Globals.XXDM_SZWBZYJSXY)){//xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY) ||  lyl
				//������
				if(userType != null && "xy".equalsIgnoreCase(userType)){
					//ѧԺ���
					sql = "select "
						+ pk
						+ ",xh,nd,xm,xn,xb,gwdm,nj,sqsj,xymc,sfpks,zymc,lxdh,bjmc,xyyj yesNo,zzmm zzmmm,gzjl,kcjqgzxsj,xssq sqly from view_xsgwxx where "
						+ pk + "='" + pkVal + "'";
				}else{
					//ѧУ���
					sql = "select "
						+ pk
						+ ",xh,nd,xm,xn,xb,gwdm,nj,sqsj,xymc,sfpks,zymc,lxdh,bjmc,xxyj yesNo,zzmm zzmmm,kcjqgzxsj, xssq sqly from view_xsgwxx where "
						+ pk + "='" + pkVal + "'";
				}
				String[] colList = new String[] { pk, "xh", "nd", "xm", "xn",
						"xb", "gwdm", "nj", "sqsj", "xymc", "sfpks", "zymc",
						"lxdh", "bjmc", "yesNo", "zzmmm" ,"kcjqgzxsj", "sqly"};

				map = dao.getMap(sql, new String[]{}, colList);
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_HYGXY)){//������ѧԺ
				//ѧУ���
				sql = "select "
					+ pk
					+ ",xh,nd,xm,xn,xb,gwdm,nj,sqsj,xymc,sfpks,zymc,lxdh,bjmc,xxyj yesNo,zzmm zzmmm,kcjqgzxsj, xssq sqly from view_xsgwxx where "
					+ pk + "='" + pkVal + "'";
				String[] colList = new String[] { pk, "xh", "nd", "xm", "xn",
						"xb", "gwdm", "nj", "sqsj", "xymc", "sfpks", "zymc",
						"lxdh", "bjmc", "yesNo", "zzmmm" ,"kcjqgzxsj", "sqly"};

				map = dao.getMap(sql, new String[]{}, colList);
				map.put("sfpks", dao.isKns(map.get("xh")) == true ? "��" : "��");
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_HBJTZYJSXY)){//������ͨ
				if ("notempty".equalsIgnoreCase(tag)) {
					sql = "select "
						+ pk
						+ ",xh,nd,xm,xn,xb,gwdm,nj,sqsj,xymc,sfpks,zymc,lxdh,bjmc,fdyyj yesNo,zzmm zzmmm,gzjl,kcjqgzxsj,xssq sqly,xssq," 
						+ "(select yrdwmc from yrdwdmb b where a.yrdwdm=b.yrdwdm)sqdw " 
						+ ",yhtc,sfgr,sfdq,sfdbh,sfyfdx," 
						+ "yjshf,bz,gwsbsj,xyyj,xxyj from view_xsgwxx a where "
						+ pk + "='" + pkVal + "'";
				}else if("notempty".equalsIgnoreCase(yrdwTag)){
					//���˵�λ���
					sql = "select "
						+ pk
						+ ",xh,nd,xm,xn,xb,gwdm,nj,sqsj,xymc,sfpks,zymc,lxdh,bjmc,xyyj yesNo,zzmm zzmmm,gzjl,kcjqgzxsj,xssq sqly,xssq," 
						+ "(select yrdwmc from yrdwdmb b where a.yrdwdm=b.yrdwdm)sqdw " 
						+ ",yhtc,sfgr,sfdq,sfdbh,sfyfdx," 
						+ "yjshf,bz,gwsbsj,xyyj,xxyj from view_xsgwxx a where "
						+ pk + "='" + pkVal + "'";
				}else{
					//ѧУ���
					sql = "select "
						+ pk
						+ ",xh,nd,xm,xn,xb,gwdm,nj,sqsj,xymc,sfpks,zymc,lxdh,bjmc,xxyj yesNo,zzmm zzmmm,kcjqgzxsj, xssq sqly,xssq," 
						+ "(select yrdwmc from yrdwdmb b where a.yrdwdm=b.yrdwdm)sqdw " 
						+ ",yhtc,sfgr,sfdq,sfdbh,sfyfdx,"
						+ "yjshf,bz,gwsbsj,xyyj,xxyj from view_xsgwxx a where "
						+ pk + "='" + pkVal + "'";
				}
				String[] colList = new String[] { pk, "xh", "nd", "xm", "xn",
						"xb", "gwdm", "nj", "sqsj", "xymc", "sfpks", "zymc",
						"lxdh", "bjmc", "yesNo", "zzmmm" ,"kcjqgzxsj", "sqly",
						"sqdw", "yhtc", "sfgr","sfdq", "sfdbh", "sfyfdx", 
						"yjshf", "bz", "xssq","gwsbsj","xyyj","xxyj"};

				map = dao.getMap(sql, new String[]{}, colList);
				map.putAll(xsxxglService.selectStuinfo(map.get("xh")));
				map.put("sfpks", dao.isKns(map.get("xh")) == true ? "��" : "��");
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_WHSYFWXY)){//�人��ҵ
				if("notempty".equalsIgnoreCase(tag)){
					sql = "select "
						+ pk
						+ ",xh,nd,xm,xn,xb,gwdm,nj,sqsj,xymc,sfpks,zymc,lxdh,bjmc,fdyyj yesNo,zzmm zzmmm,gzjl,kcjqgzxsj,xssq sqly,xssq," 
						+ "(select yrdwmc from yrdwdmb b where a.yrdwdm=b.yrdwdm)sqdw " 
						+ ",yhtc,sfgr,sfdq,sfdbh,sfyfdx," 
						+ "yjshf,bz,gwsbsj,xyyj,xxyj from view_xsgwxx a where "
						+ pk + "='" + pkVal + "'";
				}else if("notempty".equalsIgnoreCase(yrdwTag)){
					//���˵�λ���
					sql = "select "
						+ pk
						+ ",xh,nd,xm,xn,xb,gwdm,nj,sqsj,xymc,sfpks,zymc,lxdh,bjmc,xyyj yesNo,zzmm zzmmm,gzjl,kcjqgzxsj,xssq sqly,xssq," 
						+ "(select yrdwmc from yrdwdmb b where a.yrdwdm=b.yrdwdm)sqdw " 
						+ ",yhtc,sfgr,sfdq,sfdbh,sfyfdx," 
						+ "yjshf,bz,gwsbsj,xyyj,xxyj from view_xsgwxx a where "
						+ pk + "='" + pkVal + "'";
				}else{
					//ѧУ���
					sql = "select "
						+ pk
						+ ",xh,nd,xm,xn,xb,gwdm,nj,sqsj,xymc,sfpks,zymc,lxdh,bjmc,xxyj yesNo,zzmm zzmmm,kcjqgzxsj, xssq sqly,xssq," 
						+ "(select yrdwmc from yrdwdmb b where a.yrdwdm=b.yrdwdm)sqdw " 
						+ ",yhtc,sfgr,sfdq,sfdbh,sfyfdx,"
						+ "yjshf,bz,gwsbsj,xyyj,xxyj from view_xsgwxx a where "
						+ pk + "='" + pkVal + "'";
				}
				String[] colList = new String[] { pk, "xh", "nd", "xm", "xn",
						"xb", "gwdm", "nj", "sqsj", "xymc", "sfpks", "zymc",
						"lxdh", "bjmc", "yesNo", "zzmmm" ,"kcjqgzxsj", "sqly",
						"sqdw", "yhtc", "sfgr","sfdq", "sfdbh", "sfyfdx", 
						"yjshf", "bz", "xssq","gwsbsj","xyyj","xxyj"};

				map = dao.getMap(sql, new String[]{}, colList);
				map.putAll(xsxxglService.selectStuinfo(map.get("xh")));
				map.put("sfpks", dao.isKns(map.get("xh")) == true ? "��" : "��");
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJSZYJSXY)){//�㽭����
			
				//ѧУ���
				sql = "select "
					+ pk
					+ ",xh,nd,xm,xn,xb,gwdm,nj,sqsj,xymc,sfpks,zymc,lxdh,bjmc,xxyj yesNo,zzmm zzmmm,kcjqgzxsj, xssq sqly,xssq," 
					+ "(select yrdwmc from yrdwdmb b where a.yrdwdm=b.yrdwdm)sqdw " 
					+ ",yhtc,sfgr,sfdq,sfdbh,sfyfdx,"
					+ "yjshf,bz,gwsbsj,xyyj,xxyj from view_xsgwxx a where "
					+ pk + "='" + pkVal + "'";
				
				String[] colList = new String[] { pk, "xh", "nd", "xm", "xn",
						"xb", "gwdm", "nj", "sqsj", "xymc", "sfpks", "zymc",
						"lxdh", "bjmc", "yesNo", "zzmmm" ,"kcjqgzxsj", "sqly",
						"sqdw", "yhtc", "sfgr","sfdq", "sfdbh", "sfyfdx", 
						"yjshf", "bz", "xssq","gwsbsj","xxyj"};

				map = dao.getMap(sql, new String[]{}, colList);
				map.putAll(xsxxglService.selectStuinfo(map.get("xh")));
				map.put("sfpks", dao.isKns(map.get("xh")) == true ? "��" : "��");
			}else {
				if(session.getAttribute("fdyQx").toString().equals("true")){
					//����Ա
					sql = "select "
						+ pk
						+ ",xh,nd,xm,xn,xb,gwdm,nj,sqsj,xymc,sfpks,zymc,lxdh,bjmc,fdyyj yesNo,zzmm zzmmm,gzjl,kcjqgzxsj,xssq sqly,xssq," 
						+ "(select yrdwmc from yrdwdmb b where a.yrdwdm=b.yrdwdm)sqdw " 
						+ ",yhtc,sfgr,sfdq,sfdbh,sfyfdx," 
						+ "yjshf,bz,gwsbsj,xyyj,xxyj from view_xsgwxx a where "
						+ pk + "='" + pkVal + "'";
				}
				else if("notempty".equalsIgnoreCase(yrdwTag)){
					//���˵�λ���
					sql = "select "
						+ pk
						+ ",xh,nd,xm,xn,xb,gwdm,nj,sqsj,xymc,sfpks,zymc,lxdh,bjmc,xyyj yesNo,zzmm zzmmm,gzjl,kcjqgzxsj,xssq sqly,xssq," 
						+ "(select yrdwmc from yrdwdmb b where a.yrdwdm=b.yrdwdm)sqdw " 
						+ ",yhtc,sfgr,sfdq,sfdbh,sfyfdx," 
						+ "yjshf,bz,gwsbsj,xyyj,xxyj from view_xsgwxx a where "
						+ pk + "='" + pkVal + "'";
				}
				
				else{
					//ѧУ���
					sql = "select "
						+ pk
						+ ",xh,nd,xm,xn,xb,gwdm,nj,sqsj,xymc,sfpks,zymc,lxdh,bjmc,xxyj yesNo,zzmm zzmmm,kcjqgzxsj, xssq sqly,xssq," 
						+ "(select yrdwmc from yrdwdmb b where a.yrdwdm=b.yrdwdm)sqdw " 
						+ ",yhtc,sfgr,sfdq,sfdbh,sfyfdx,"
						+ "yjshf,bz,gwsbsj,xyyj,xxyj from view_xsgwxx a where "
						+ pk + "='" + pkVal + "'";
				}
				String[] colList = new String[] { pk, "xh", "nd", "xm", "xn",
						"xb", "gwdm", "nj", "sqsj", "xymc", "sfpks", "zymc",
						"lxdh", "bjmc", "yesNo", "zzmmm" ,"kcjqgzxsj", "sqly",
						"sqdw", "yhtc", "sfgr","sfdq", "sfdbh", "sfyfdx", 
						"yjshf", "bz", "xssq","gwsbsj","xyyj","xxyj"};

				map = dao.getMap(sql, new String[]{}, colList);
				map.putAll(xsxxglService.selectStuinfo(map.get("xh")));
				map.put("sfpks", dao.isKns(map.get("xh")) == true ? "��" : "��");
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)){
				//����Ƽ�ѧԺ ��ѯѧ���ɲμ��ڹ���ѧ�Ŀ���ʱ��
				xh = (xh==null || xh.equalsIgnoreCase("")) ? "" : xh.trim();

				String[] sj = { "�����ޣ�7:30��8:20��", "��1-2�ڣ�8:30��10:05��",
						"��3-4�ڣ�10:25��12:00��", "���ݣ�12:00��13:45��",
						"��5-6�ڣ�13:50��15:25��", "��7-8�ڣ�15:30��17:05��",
				"�����ޣ�17:50��20:15��" };
				String[] xq = { "mon", "tue", "wed", "thu", "fri", "sat", "sun" };

				List<HashMap> kxList = new ArrayList<HashMap>();
				sql = "select kxbz from xsqgzxsjb where xh = ?  order by xq,sj ";
				String[] kxbz = dao.getRs(sql, new String[] { xh }, "kxbz");
				if (kxbz != null && kxbz.length != 0) {
					String[] kx = new String[7];
					int j = 0;
					for (int i = 0; i < 7; i++) {
						for (int x = 0; x < 7; x++) {
							kx[x] = kxbz[x + j];
						}
						j += 7;
						HashMap<String, String> map2 = new HashMap<String, String>();
						for (int p = 0; p < 7; p++) {
							map2.put(xq[p], String.valueOf(kx[p]));
						}
						map2.put("sj", sj[i]);
						map2.put("sjIndex", String.valueOf(i));
						kxList.add(map2);
					}					
					request.setAttribute("kxList", kxList);

				} else {
					for (int i = 0; i < 7; i++) {
						HashMap<String, String> map2 = new HashMap<String, String>();
						map2.put("sj", sj[i]);
						map2.put("sjIndex", String.valueOf(i));
						kxList.add(map2);
					}
					request.setAttribute("whkxList", kxList);
					request.setAttribute("kxbz", "no");
				}
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
				//��������Ժ
				XbemyQgzxDAO xbDao = new XbemyQgzxDAO();
				List  filedList = xbDao.getFiledInfo("002");
				request.setAttribute("filedList", filedList);
				request.setAttribute("rsNum", filedList.size());
			}
			// ����������Ϣ
			map = service.addOtherInfo(map);
			if(xxdm.equalsIgnoreCase(Globals.XXDM_JGSDX)){
				//����ɽ��ѧ
				request.setAttribute("gwyq", service.getGwyqInfo(pk,pkVal));
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){
				//��������
				sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from view_gwxx where SHJG='ͨ��'";				
				request.setAttribute("gwList", dao.getList(sql, new String[]{}, new String[]{"gwdm","gwdmgwsbsj"}));
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJXY)){
				//�㹤��֮��ѧԺ
				QgzxXsgwsqService xsgwsqService = new QgzxXsgwsqService();
				QgzxXsgwsqForm qgzxForm = new QgzxXsgwsqForm();
				qgzxForm.setGwdmsbsj(map.get("gwdm") + map.get("gwsbsj"));
				String bz = map.get("bz"); //��ȡѧ����λ��Ϣ��ע���
				map.putAll(xsgwsqService.getGwxx(qgzxForm)); //��ȡ��λ��Ϣ
				map.put("bz", bz); //�������뱸ע��Ϣ(���ⱻ��λ��Ϣ�ı�ע�ֶθ���)
			}
			request.setAttribute("rs", map);
			request.setAttribute("userType", userType);
			request.setAttribute("chkList", dao.getChkList(3));
			request.setAttribute("xxdm", xxdm);	
			//������Ϣ
			if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
				return new ActionForward("/sjcz/post_stu_check_one_jsxx.jsp",false);
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){
				//�人����ѧ
				return new ActionForward("/qgzx/whlgdx/post_stu_check_one.jsp",false);
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_HNGYDX)){
				//���Ϲ�ҵ��ѧ
				return new ActionForward("/xsqgzx.do?method=getAuditingInfo",false);
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
				//�й����ʴ�ѧ
				return new ActionForward("/qgzx/zgdzdx/post_stu_check_one.jsp",false);
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJXY)){
				//�㹤��֮��ѧԺ
				request.setAttribute("act", "sh");
				return new ActionForward("/qgzx/comm/gwsqwh/xssqShOne.jsp");
			}
			return mapping.findForward("success");
		}
		String yesNo = DealString.toGBK(request.getParameter("yesNo"));	

		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
			//�й����ʴ�ѧ
			String zwxzdm = model.getZwxzdm();//ְλ���ʴ���
			QgzxDao qgzxDao = new QgzxDao();
			//���˵�λһ�����
			//ѧ��ֻ����һ����λ��û�и�λ��ѧ���ɷ������룬ֱ������ѧ�����õ���λ
			String shType="xxyj";//���״̬	
			if("ͨ��".equalsIgnoreCase(yesNo)){
				//�ж��Ƿ�������������ſ�����
				String msg = qgzxDao.checkGwrs(pkVal);
				if(Base.isNull(msg)){
					flag = StandardOperation.update("xsgwxxb", 
							                        new String[]{shType, "zwxzdm"}, 
							                        new String[]{yesNo, zwxzdm}, 
							                        pk, 
							                        pkVal, 
							                        request);
				}else{
					request.setAttribute("mes", msg);
				}
			}else{
				flag = StandardOperation.update("xsgwxxb", new String[]{shType}, new String[]{yesNo}, pk, pkVal, request);
			}
			//request.setAttribute("zwxzdmList", service.getZwxzdmList());
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)){
			//���������ѧ
			//���˵�λ���
			if("notempty".equalsIgnoreCase(yrdwTag)){
				flag = StandardOperation.update("xsgwxxb", new String []{"xyyj"}, new String []{yesNo}, pk, pkVal, request);
			}else if(userType != null && userType.equalsIgnoreCase("xy")){
				flag = StandardOperation.update("xsgwxxb", new String []{"fdyyj"}, new String []{yesNo}, pk, pkVal, request);
			}else{
				flag = StandardOperation.update("xsgwxxb", new String []{"xxyj"}, new String []{yesNo}, pk, pkVal, request);
			}
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_HNGYDX)|| xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)){
			//���Ϲ�ҵ��ѧ || �й�����ѧԺ
			//ѧԺ���------>ѧУ���-------->���˵�λ���
			if("notempty".equalsIgnoreCase(yrdwTag)){
				//���˵�λ
				flag = StandardOperation.update("xsgwxxb", new String []{"xyyj"}, new String []{yesNo}, pk, pkVal, request); 
			}else if(userType != null && "xy".equalsIgnoreCase(userType)){
				//ѧԺ
				flag = StandardOperation.update("xsgwxxb", new String []{"fdyyj"}, new String []{yesNo}, pk, pkVal, request);
			}else{
				//ѧУ
				flag = StandardOperation.update("xsgwxxb", new String []{"xxyj"}, new String []{yesNo}, pk, pkVal, request);
			}
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){
			//�������ϴ�ѧ
			BjlhQgzxService bjlhService = new BjlhQgzxService();
			userType = "xyyj";
			String mes = bjlhService.checkGwrs(pkVal, userType);
			if(mes != null && !"".equalsIgnoreCase(mes) && yesNo != null && "ͨ��".equalsIgnoreCase(yesNo)){
				request.setAttribute("mes",mes);
				flag = false;
			}else{
				flag = StandardOperation.update("xsgwxxb", new String []{"xyyj"}, new String []{yesNo}, pk, pkVal, request);
			}
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)){
			//�й���ҵ��ѧ				
			QgzxZgdzdxService zgdzService = new QgzxZgdzdxService();
			QgzxZgkydxService zgkdService = new QgzxZgkydxService();
			if(zgdzService.isHmdMember(xh) && "ͨ��".equalsIgnoreCase(yesNo)){
				request.setAttribute("isHmd", "yes");
				request.setAttribute("mes", "��ѧ���Ѿ������������,��ʱ�޷����ͨ��!");
			}else{
				String xxshyj = DealString.toGBK(request.getParameter("xxshyj"));
				String bh = request.getParameter("bh");
				String gh = request.getParameter("gh");

				if("notempty".equalsIgnoreCase(yrdwTag)){
					if("ͨ��".equalsIgnoreCase(yesNo) && zgkdService.checkPostPass(xh, "xyyj")){
						request.setAttribute("mes", "��ѧ���Ѿ��и�λͨ�����,��ʱ�����ٴ����ͨ��!");
					}else{
						flag = StandardOperation.update("xsgwxxb", new String []{"xyyj","bh","gh","xxshyj"}, new String []{yesNo,bh,gh,xxshyj}, pk, pkVal, request);
					}
				}

				if(userType.equalsIgnoreCase("xx")||userType.equalsIgnoreCase("admin")){	
					if("ͨ��".equalsIgnoreCase(yesNo) && zgkdService.checkPostPass(xh, "xxyj")){
						request.setAttribute("mes", "��ѧ���Ѿ��и�λͨ�����,��ʱ�����ٴ����ͨ��!");
					}else{
						flag = StandardOperation.update("xsgwxxb", new String []{"xxyj","bh","gh","xxshyj"}, new String []{yesNo,bh,gh,xxshyj}, pk+"||xyyj", pkVal+"ͨ��", request);
					}
				}
			}
		}else if( xxdm.equalsIgnoreCase(Globals.XXDM_SZWBZYJSXY)){ //xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY) || lyl
			//������ ѧԺ----->ѧУ
			String xxshyj = DealString.toGBK(request.getParameter("xxshyj"));				
			if(userType != null && "xy".equalsIgnoreCase(userType)){
				String xxyj = dao.getOneRs("select xxyj from view_xsgwxx where " + pk + "=?", new String[]{pkVal}, "xxyj");
				if(xxyj != null && "ͨ��".equalsIgnoreCase(xxyj)){
					request.setAttribute("mes", "ѧУ�Ѿ����ͨ�����������޸���˽��!");
				}else{
					flag = StandardOperation.update("xsgwxxb", new String []{"xyyj","xxshyj"}, new String []{yesNo,xxshyj}, pk, pkVal, request);
				}
			}else {
				flag = StandardOperation.update("xsgwxxb", new String []{"xxyj","xxshyj"}, new String []{yesNo,xxshyj}, pk, pkVal, request);
			}
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_HYGXY)){
			//������ѧԺ
			flag = StandardOperation.update("xsgwxxb", new String []{"xxyj"}, new String []{yesNo}, pk, pkVal, request);
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_NBTYZYJSXY)){
			//������һѧԺ
			String shjg = "xxyj";
			if("xy".equalsIgnoreCase(userType)){
				shjg = "xyyj";
			}
			flag = StandardOperation.update("xsgwxxb", new String []{shjg}, new String []{yesNo}, pk, pkVal, request);
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJSZYJSXY)){
			//�㽭����ѧԺ һ����ˣ�2011.12.5 qlj��
			flag = StandardOperation.update("xsgwxxb", new String []{"xxyj"}, new String []{yesNo}, pk, pkVal, request);
		}
//		else if(xxdm.equalsIgnoreCase(Globals.XXDM_HBJTZYJSXY)){
//			String shjg = "xxyj";
//			if("xy".equalsIgnoreCase(userType)){
//				shjg = "xyyj";
//			}
//			flag = StandardOperation.update("xsgwxxb", new String []{shjg}, new String []{yesNo}, pk, pkVal, request);
//		}
		else{		
			String xxshyj = DealString.toGBK(request.getParameter("xxshyj"));
			String xyshyj = DealString.toGBK(request.getParameter("xxshyj"));
			String bh = request.getParameter("bh");
			String gh = request.getParameter("gh");
			//����Ա���
			if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_HBJTZYJSXY)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_WHSYFWXY)){
				if("notempty".equalsIgnoreCase(tag)){
					flag = StandardOperation.update("xsgwxxb", new String []{"fdyyj","bh","gh","xxshyj"}, new String []{yesNo,bh,gh,xxshyj}, pk, pkVal, request);
				}

			}
			//���˵�λ���
			if(!xxdm.equalsIgnoreCase(Globals.XXDM_YNYS) 
					&& !xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){
				if(session.getAttribute("fdyQx").toString().equals("true")){
					flag = StandardOperation.update("xsgwxxb", new String []{"fdyyj","bh","gh","xxshyj"}, new String []{yesNo,bh,gh,xxshyj}, pk, pkVal, request);
				}
				else if("notempty".equalsIgnoreCase(yrdwTag)){
					flag = StandardOperation.update("xsgwxxb", new String []{"xyyj","bh","gh","xxshyj"}, new String []{yesNo,bh,gh,xxshyj}, pk, pkVal, request);
				}
			}

			//ѧУ���
			if(!(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_YNYS) )){
				if((userType.equalsIgnoreCase("xx")||userType.equalsIgnoreCase("admin")) && "empty".equalsIgnoreCase(yrdwTag)){	
					flag = StandardOperation.update("xsgwxxb", new String []{"xxyj","bh","gh","xxshyj"}, new String []{yesNo,bh,gh,xxshyj}, pk, pkVal, request);						
				}
			}
			if(Globals.XXDM_YNYS.equalsIgnoreCase(xxdm)){
				if((userType.equalsIgnoreCase("xx")||userType.equalsIgnoreCase("admin")) && "empty".equalsIgnoreCase(tag)){	
					flag = StandardOperation.update("xsgwxxb", new String []{"xxyj","bh","gh","xxshyj"}, new String []{yesNo,bh,gh,xxshyj}, pk, pkVal, request);						
				}
			}
			
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){
				//�㽭����
				QgzxDao qgzxDao = new QgzxDao();
				HashMap< String, String > sjMap = qgzxDao.getSqsjInfo();
				String nd = sjMap.get("nd");
				String xn = sjMap.get("xn");
				String xq = sjMap.get("xq");
				if(userType.equalsIgnoreCase("xx")||userType.equalsIgnoreCase("admin")){	
					if(qgzxDao.checkExists("xsgwxxb", "xh||xn||nd||xq||xxyj", xh+xn+nd+xq+"ͨ��") && yesNo.equalsIgnoreCase("ͨ��")){
						request.setAttribute("mes", "�Ѿ��и�λ���ͨ�����޷��ٴ����ͨ����");
						flag = false;
					}else{
						flag = StandardOperation.update("xsgwxxb", new String []{"xxyj","bh","gh","xxshyj"}, new String []{yesNo,bh,gh,xxshyj}, pk, pkVal, request);
					}
				}
			}
			

		}
		request.setAttribute("flag", flag);
		request.setAttribute("result", "view");
		return new ActionForward("/postStuChkOne.do?act=abc",false);
	}

	private ActionForward viewJxjHz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String xxdm = StandardOperation.getXxdm();
		DAO dao = DAO.getInstance();
		String currXn = "";
		String currNd = "";
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
			currXn = dao.getConf(2);
			currNd = dao.getConf(3);
		}else{
			currXn = Base.currXn;
			currNd = Base.currNd;
		}
//		String dqXn = dao.getConf(0);
//		String dqXq = dao.getConf(1);
//		String dqNd = dao.getConf(4);
		String sql = "";
		String sqlTmp = "";
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		Vector<Object> vec = new Vector<Object>();
		String[] cols = new String[] { "xymc", "xh", "xm", "jxjdm1", "jlje1" };
		Vector<Object> colsTmp = new Vector<Object>();
		if (userType.equalsIgnoreCase("xx")) {
			sql = "select count(*) num from jxjhzb where xn=? and nd=?";
		} else {
			sql = "select count(*) num from jxjhzb where xn=? and nd=? and bmdm='"
				+ userDep + "'";
		}
		String[] dataExists = dao.getOneRs(sql,
				new String[] { currXn, currNd }, new String[] { "num" });
		if (Integer.parseInt(dataExists[0]) != 0) {
			for (int i = 2; i <= 7; i++) {
				sqlTmp = sql + " and jxjdm" + String.valueOf(i)
				+ " is not null";
				dataExists = dao
				.getOneRs(sqlTmp, new String[] { currXn, currNd },
						new String[] { "num" });
				if (Integer.parseInt(dataExists[0]) > 0) {
					colsTmp.add("jxjdm" + String.valueOf(i));
					colsTmp.add("jlje" + String.valueOf(i));
				}
			}
			cols = new String[colsTmp.size() + 6];
			cols[0] = "xymc";
			cols[1] = "xh";
			cols[2] = "xm";
			cols[3] = "jxjdm1";
			cols[4] = "jlje1";
			for (int i = 5; i < cols.length - 1; i++) {
				cols[i] = (String) colsTmp.get(i - 5);
			}
			cols[cols.length - 1] = "jjze";
			String[] colsCN = dao.getColumnNameCN(cols, "view_jxjhz");
			vec = new Vector<Object>();
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select * from view_jxjhz where xn=? and nd=?";
			} else {
				sql = "select * from view_jxjhz where xn=? and nd=? and bmdm='"
					+ userDep + "'";
			}
			vec.addAll(dao
					.rsToVator(sql, new String[] { currXn, currNd }, cols));
			List topTr = dao.arrayToList(cols, colsCN);
			request.setAttribute("topTr", topTr);
		}
		request.setAttribute("rsNum", String.valueOf(vec.size()));
		request.setAttribute("rs", vec);
		request.setAttribute("writeAble", "yes");
		return mapping.findForward("success");
	}

	private ActionForward showYxBysFpb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String xxdm = StandardOperation.getXxdm();
		DAO dao = DAO.getInstance();
		String currXn = "";
		String currNd = "";
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
			currXn = dao.getConf(2);
			currNd = dao.getConf(3);
		}else{
			currXn = Base.currXn;
			currNd = Base.currNd;
		}
//		String dqXn = dao.getConf(0);
//		String dqXq = dao.getConf(1);
//		String dqNd = dao.getConf(4);
		String sql = "";
		String zjdxyxbysdm = "004";//�㽭��ѧ�����ҵ��  ����
		String zjsyxbysdm = "005";//�㽭ʡ�����ҵ��   ����
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		if (userType.equalsIgnoreCase("xx")) {
			sql = "select rownum,a.xn,a.nd,a.bmdm,a.bysrs,a.xymc,nvl(a.tot,'0') tot,nvl(a.tg,'0') tg,"
				+ "nvl(a.bysrs*0.05,'0') tot1,nvl(b.tg1,'0') tg1 from(select a.xn,a.nd,"
				+ "a.bmdm,a.bysrs,a.xymc,b.tot,b.tg from (select distinct xn,nd,a.bmdm,bysrs,b.xymc "
				+ "from xyjxjrs a,view_njxyzybj b where bmlb='xydm' and a.bmdm=b.xydm and a.xn=? "
				+ "and a.nd=?) a left join (select xn,nd,xydm,(max(decode(xxsh,'ͨ��',num,0)) + "
				+ "max(decode(xxsh,'��ͨ��',num,0)) + max(decode(xxsh,'δ���',num,0))) tot,"
				+ "max(decode(xxsh,'ͨ��',num,0)) tg from (select xn,nd,xydm,xxsh,count(*) num from view_xsrychb "
				+ "where xn=? and nd=? and rychdm=? group by xn,nd,xydm,xxsh) "
				+ "group by xn,nd,xydm) b on a.bmdm=b.xydm and a.xn=b.xn and a.nd=b.nd order by a.bmdm) a "
				+ "left join (select xn,nd,xydm,(max(decode(xxsh,'ͨ��',num,0)) + max(decode(xxsh,'��ͨ��',num,0))"
				+ " + max(decode(xxsh,'δ���',num,0))) tot1,max(decode(xxsh,'ͨ��',num,0)) tg1 from "
				+ "(select xn,nd,xydm,xxsh,count(*) num from view_xsrychb where xn=? and nd=? "
				+ "and rychdm=? group by xn,nd,xydm,xxsh) group by xn,nd,xydm) b on a.bmdm=b.xydm and a.xn=b.xn "
				+ "and a.nd=b.nd";
		} else {
			sql = "select rownum,a.xn,a.nd,a.bmdm,a.bysrs,a.xymc,nvl(a.tot,'0') tot,nvl(a.tg,'0') tg,"
				+ "nvl(a.bysrs*0.05,'0') tot1,nvl(b.tg1,'0') tg1 from(select a.xn,a.nd,"
				+ "a.bmdm,a.bysrs,a.xymc,b.tot,b.tg from (select distinct xn,nd,a.bmdm,bysrs,b.xymc "
				+ "from xyjxjrs a,view_njxyzybj b where bmlb='xydm' and a.bmdm=b.xydm and a.xn=? "
				+ "and a.nd=?) a left join (select xn,nd,xydm,(max(decode(xxsh,'ͨ��',num,0)) + "
				+ "max(decode(xxsh,'��ͨ��',num,0)) + max(decode(xxsh,'δ���',num,0))) tot,"
				+ "max(decode(xxsh,'ͨ��',num,0)) tg from (select xn,nd,xydm,xxsh,count(*) num from view_xsrychb "
				+ "where xn=? and nd=? and rychdm=? group by xn,nd,xydm,xxsh) "
				+ "group by xn,nd,xydm) b on a.bmdm=b.xydm and a.xn=b.xn and a.nd=b.nd order by a.bmdm) a "
				+ "left join (select xn,nd,xydm,(max(decode(xxsh,'ͨ��',num,0)) + max(decode(xxsh,'��ͨ��',num,0))"
				+ " + max(decode(xxsh,'δ���',num,0))) tot1,max(decode(xxsh,'ͨ��',num,0)) tg1 from "
				+ "(select xn,nd,xydm,xxsh,count(*) num from view_xsrychb where xn=? and nd=? "
				+ "and rychdm=? group by xn,nd,xydm,xxsh) group by xn,nd,xydm) b on a.bmdm=b.xydm and a.xn=b.xn "
				+ "and a.nd=b.nd where bmdm='" + userDep + "'";
		}
		ArrayList dataExists = dao.rsToVator(sql,
				new String[] { currXn, currNd,currXn, currNd,zjdxyxbysdm,currXn, currNd,zjsyxbysdm }, new String[] { "rownum","xymc","bysrs","tot","tg","tot1","tg1" });

		request.setAttribute("rsNum", String.valueOf(dataExists.size()));
		request.setAttribute("rs", dataExists);
		request.setAttribute("writeAble", "yes");
		return mapping.findForward("success");
	}

	private ActionForward setJxjHz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String xxdm = StandardOperation.getXxdm();
		DAO dao = DAO.getInstance();
		String currXn = "";
		String currNd = "";
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
			currXn = dao.getConf(2);
			currNd = dao.getConf(3);
		}else{
			currXn = Base.currXn;
			currNd = Base.currNd;
		}
//		String dqXn = dao.getConf(0);
//		String dqXq = dao.getConf(1);
//		String dqNd = dao.getConf(4);
		String sql = "";
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		sql = "{call jxjhz(?,?,?)}";
		if (userType.equalsIgnoreCase("xx")) {
			userDep = "";
		}
		boolean res = dao.runProcuder(sql, new String[] { currXn, currNd,
				userDep });
		if (res) {
			request.setAttribute("initHZ", "ok");
		} else {
			request.setAttribute("initHZ", "no");
		}
		return mapping.findForward("success");
	}

	private ActionForward ticketConf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// ���ó�ƱԤ��ʱ��
		CommanForm priseForm = (CommanForm) form;
		DAO dao = DAO.getInstance();
		String sql = "";// sql���
		String xn = "";
		String nd = "";
		String xq = "";
		String kssj = "";
		String jssj = "";
		String cckssj = "";
		String ccjssj = "";
		if ((request.getParameter("act") != null)
				&& request.getParameter("act").equalsIgnoreCase("save")) {
			xn = request.getParameter("xn");
			nd = request.getParameter("nd");
			xq = request.getParameter("xq");
			kssj = request.getParameter("kssqsj");
			jssj = request.getParameter("jssqsj");
			cckssj = request.getParameter("cckssj");
			ccjssj = request.getParameter("ccjssj");
			// sql = "delete from xsfzsqkfsjb where xn=? and nd=? and xq=?";
			sql = "delete from cpydsjb";
			dao.runUpdate(sql, new String[] {});
			sql = "insert into cpydsjb(xn,nd,xq,kssj,jssj,cckssj,ccjssj) values(?,?,?,?,?,?,?)";
			boolean ok = dao.runUpdate(sql, new String[] { xn, nd, xq, kssj, jssj ,cckssj, ccjssj});

			if (ok) {
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}
		sql = "select xn,nd,xq, strtodatetime(substr(kssj,1,8)) kssj1,"
			+ "substr(kssj,9,2) kssj2," 
			+ "substr(kssj,11,2) kssj3,"
			+ "substr(kssj,13,2) kssj4,"
			+ "strtodatetime(substr(jssj,1,8)) jssj1,"
			+ "substr(jssj,9,2) jssj2," 
			+ "substr(jssj,11,2) jssj3,"
			+ "substr(jssj,13,2) jssj4 ,strtodatetime(substr(cckssj,1,8)) cckssj1,"
			+ "substr(cckssj,9,2) cckssj2,"
			+ "substr(cckssj,11,2) cckssj3,"
			+ "substr(cckssj,13,2) cckssj4,strtodatetime(substr(ccjssj,1,8)) ccjssj1,"
			+ "substr(ccjssj,9,2) ccjssj2,"
			+ "substr(ccjssj,11,2) ccjssj3,"
			+ "substr(ccjssj,13,2) ccjssj4 "
			+ " from cpydsjb " + "where rownum=1";
		String[] rst = { "xn", "nd", "xq", "kssj1", "kssj2", "kssj3", "kssj4",
				"jssj1", "jssj2", "jssj3", "jssj4", "cckssj1", "cckssj2", "cckssj3",
				"cckssj4", "ccjssj1", "ccjssj2","ccjssj3", "ccjssj4"};
		String[] sj = dao.getOneRs(sql, new String[] {}, rst);
		if (sj == null) {
			sj = new String[rst.length];
			for (int i = 0; i < sj.length; i++) {
				sj[i] = "";
			}
		}
		for (int i = 0; i < sj.length; i++) {
			request.setAttribute(rst[i], sj[i]);
		}
		priseForm.setXn(sj[0]);
		priseForm.setNd(sj[1]);
		priseForm.setXq(sj[2]);
		request.setAttribute("xnndList", dao.getXnndList());
		request.setAttribute("xqList", dao.getXqList());
		return mapping.findForward("success");
	}

	private ActionForward ticketBookSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CommanForm checkForm = (CommanForm) form;
		HttpSession session = request.getSession();
		String xxdm = session.getAttribute("xxdm").toString();
		DAO dao = DAO.getInstance();
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql���
		String querry = " where 1=1 ";// sql����
		String tips = "";// λ����ʾ��Ϣ
		String tableName = "";// ��ѯ��ϢԴ����Ϊ��ͼ����
		String rsNum = "0";// ���صļ�¼��
		String realTable = "";// ����Դ��
		String pk = "";// ����Դ����������ʽΪ���ֶ���||�ֶ���||�ֶ�������
		String writeAble = "yes";// дȨ��
		String dataType = request.getParameter("act");
		String userType = session.getAttribute("userOnLine").toString();
		if (userType.equalsIgnoreCase("student")) {
			return new ActionForward("/stu_ticket_book_search.do", false);
		}
		sql = "select xn,nd,xq from cpydsjb where rownum=1";
		colList = dao.getOneRs(sql, new String[] {}, new String[] { "xn", "nd","xq" });
		String xn = colList[0];
		String nd = colList[1];
		String xq = colList[2];
		checkForm.setXn(xn);
		checkForm.setNd(nd);
		checkForm.setXq(xq);
		String cc = request.getParameter("cc");
		String dph = checkForm.getDph();
		realTable = "cpydb";
		pk = "dph";
		tips = "�ճ����� - Ʊ����� - ��Ʊ�����ѯ";
		tableName = "view_cpyd";
		querry += "and xn = '" + xn + "' ";
		querry += "and nd = '" + nd + "' ";
		querry += "and xq = '" + xq + "' ";
		if (isNull(cc)) {
			querry += "and 1=1 ";
		} else {
			querry += "and cc = '" + cc + "' ";
		}
		if (isNull(dph)) {
			querry += "and 1=1 ";
		} else {
			querry += "and dph = '" + dph + "' ";
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)){
			colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd", "xqmc",
					"dph", "xh", "xm", "cc", "ydjg", "sflq", "czmc", "ccrq","Ʊ��" };
		}else{
			colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd", "xqmc",
					"dph", "xh", "xm", "cc", "ydjg", "sflq", "dz", "pj","ccrq"};
		}
		sql = "select rownum �к�,(case nvl(a.ydjg,'') when '�ɹ�' then '#99CCFF' when 'ʧ��' then '#FF9999' else '#FFFFFF' end) bgcolor,"
			+ " a.* from(select "
			+ pk
			+ " ����,a.* from "
			+ tableName
			+ " a"
			+ querry + " order by ydjg desc,sflq asc,ydsj asc) a";
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)){
			sql = "select rownum �к�,(case nvl(a.ydjg,'') when '�ɹ�' then '#99CCFF' when 'ʧ��' then '#FF9999' else '#FFFFFF' end) bgcolor,"
				+ " a.* from(select "
				+ pk
				+ " ����,a.xn,a.nd,a.xqmc,a.dph,a.xh,a.xm,a.cc,a.ydjg,a.sflq,(select b.zdz from hccpydkb b where a.xh=b.xh and a.cc=b.cc) czmc,a.ccrq,(select b.pj from hccpydkb b where a.xh=b.xh and a.cc=b.cc) Ʊ�� from "
				+ tableName
				+ " a"
				+ querry + " order by ydjg desc,sflq asc,ydsj asc) a";
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
		sql = "select cc from hcccb";
		List ccList = dao.getList(sql, new String[] {}, new String[] { "cc" });
		request.setAttribute("ccList", ccList);
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("act", dataType);// �������ݲ�ѯ����
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("xnList", dao.getXnndList());// ����ѧ���б�
		request.setAttribute("xqList", dao.getXqList());// ����ѧ���б�
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);// ���ͼ�¼��
		return mapping.findForward("success");
	}

	private ActionForward ticketBookTotalSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CommanForm checkForm = (CommanForm) form;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql���
		String querry = " where 1=1 ";// sql����
		String tips = "";// λ����ʾ��Ϣ
		String tableName = "";// ��ѯ��ϢԴ����Ϊ��ͼ����
		String rsNum = "0";// ���صļ�¼��
		String realTable = "";// ����Դ��
		String pk = "";// ����Դ����������ʽΪ���ֶ���||�ֶ���||�ֶ�������
		String writeAble = "yes";// дȨ��
//		String xxdm = session.getAttribute("xxdm").toString();
		String dataType = request.getParameter("act");
		String userType = session.getAttribute("userOnLine").toString();
		sql = "select xn,nd,xq from cpydsjb where rownum=1";
		colList = dao.getOneRs(sql, new String[] {}, new String[] { "xn", "nd", "xq" });
		String xn = colList[0];	
		String nd = colList[1];
		String xq = colList[2];
		checkForm.setXn(xn);
		checkForm.setNd(nd);
		checkForm.setXq(xq);
		String cc = request.getParameter("cc");
		String dz = request.getParameter("dz");
		String ccrq = request.getParameter("ccrq");
		String bxcc = (request.getParameter("bxcc")!=null && request.getParameter("bxcc").equalsIgnoreCase("1"))?"��":"";
		String ksy = (request.getParameter("ksy")!=null && request.getParameter("ksy").equalsIgnoreCase("1"))?"��":"";
		String klc = (request.getParameter("klc")!=null && request.getParameter("klc").equalsIgnoreCase("1"))?"��":"";
		String kwz = (request.getParameter("kwz")!=null && request.getParameter("kwz").equalsIgnoreCase("1"))?"��":"";
		String qt = (request.getParameter("qt")!=null && request.getParameter("qt").equalsIgnoreCase("1"))?"��":"";
		String dzmc = dao.getOneRs("select czmc from czdmb where czdm=?", new String[]{dz}, "czmc");
		dzmc = dzmc==null || "".equalsIgnoreCase(dzmc) ? "":dzmc;
		//realTable = "cpydb";
		//pk = "";
		tips = "�ճ����� - Ʊ����� - ��Ʊͳ�Ʋ�ѯ";
		tableName = "view_cptj";
		querry += " and xn = '" + xn + "' ";
		querry += " and nd = '" + nd + "' ";
		querry += " and xq = '" + xq + "' ";
		if (isNull(cc)) {
			querry += " and 1=1 ";
		} else {
			querry += " and cc = '" + cc + "' ";
		}
		if (isNull(dzmc)) {
			querry += " and 1=1 ";
		} else {
			querry += " and dz = '" + dzmc + "' ";
		}
		if (isNull(ccrq)) {
			querry += " and 1=1 ";
		} else {
			querry += " and ccrq = '" + ccrq + "' ";
		}
		if(isNull(bxcc)){
			querry += " and 1=1 ";
		} else{
			querry +=" and bxcc= '" + bxcc + "' ";
		}
		if(isNull(ksy)){
			querry += " and 1=1 ";
		} else{
			querry +=" and ksy= '" + ksy + "' ";
		}
		if(isNull(klc)){
			querry += " and 1=1 ";
		} else{
			querry +=" and klc= '" + klc + "' ";
		}
		if(isNull(kwz)){
			querry += " and 1=1 ";
		} else{
			querry +=" and kwz= '" + kwz + "' ";
		}
		if(isNull(qt)){
			querry += " and 1=1 ";
		} else{
			querry +=" and qt= '" + qt + "' ";
		}
		colList = new String[] {"�к�", "ccrq","cc","dzmc","yzzs","bxcc","ksy","klc","kwz","qt"};	
		sql = "select rownum �к�,"
			+ " a.* from "
			+ tableName
			+ " a "
			+ querry + " order by cc";
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
		sql = "select cc from hcccb";
		List ccList = dao.getList(sql, new String[] {}, new String[] {
		"cc" });
		sql = "select czdm,czmc from czdmb";
		List czList = dao.getList(sql, new String[] {}, new String[] {"czdm","czmc"});
		request.setAttribute("dzList", czList);
		request.setAttribute("ccList", ccList);
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("act", dataType);// �������ݲ�ѯ����
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("xnList", dao.getXnndList());// ����ѧ���б�
		request.setAttribute("xqList", dao.getXqList());// ����ѧ���б�
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);// ���ͼ�¼��
		return mapping.findForward("success");
	}		


	private ActionForward ticketBookEdit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// �鿴���޸ĵ�����¼
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String act = request.getParameter("act");
		String pkVal = DealString.toGBK(request.getParameter("pkVal"));
		HttpSession session = request.getSession();
		String xxdm = session.getAttribute("xxdm").toString();
//		String xh = request.getParameter("xh");
//		String cc= request.getParameter("cc");
		String pk = "dph";
		String sql = "";
		String userType = dao.getUserType(session.getAttribute("userDep")
				.toString());
		if ((act == null) || !act.equalsIgnoreCase("save")) {
			sql = "select a."
				+ pk
				+ ",a.xh,a.nd,a.xm,a.xn,a.xb,a.xqmc,a.nj,a.dph,a.xymc,a.cc,a.zymc,a.dz,a.pj,a.bjmc,a.ccrq,a.bxcc,a.ksy,a.klc,a.kwz,a.qt,a.ydjg,a.sflq,a.jbr,a.bz from view_cpyd a where a."
				+ pk + "='" + pkVal + "'";
			String[] colList = new String[] { pk, "xh", "nd", "xm", "xn", "xb",
					"xqmc", "nj", "dph", "xymc", "cc", "zymc", "dz",
					"bjmc", "ccrq", "bxcc", "ksy", "klc", "kwz", "qt", "ydjg", "sflq", "jbr", "bz","pj" };
			String[] rs = dao.getOneRs(sql, new String[] {}, colList);
			map.put(pk, rs[0]);
			map.put("xh", rs[1]);
			map.put("nd", rs[2]);
			map.put("xm", rs[3]);
			map.put("xn", rs[4]);
			map.put("xb", rs[5]);
			map.put("xqmc", rs[6]);
			map.put("nj", rs[7]);
			map.put("dph", rs[8]);
			map.put("xymc", rs[9]);
			map.put("cc", rs[10]);
			map.put("zymc", rs[11]);
			map.put("dz", rs[12]);
			map.put("bjmc", rs[13]);
			map.put("ccrq", rs[14]);
			map.put("bxcc", rs[15]);
			map.put("ksy", rs[16]);
			map.put("klc", rs[17]);
			map.put("kwz", rs[18]);
			map.put("qt", rs[19]);
			map.put("ydjg", rs[20]);
			map.put("sflq", rs[21]);
			map.put("jbr", rs[22]);
			map.put("bz", rs[23]);
			map.put("pj", rs[24]);
//			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)){
//			map.put("dz", dao.getOneRs("select zdz from hccpydkb where dph=(select dph from view_cpyd where " + pk + "='" + pkVal + "')", new String[]{}, "zdz"));
//			}
			request.setAttribute("rs", map);
			request.setAttribute("userType", userType);
			request.setAttribute("lqList", dao.getChkList(2));
			request.setAttribute("jgList", dao.getChkList(4));
			sql = "select jbrgh,jbrxm from jbrxxb order by jbrgh";
			List jbrList = dao.getList(sql, new String[] {}, new String[] {
					"jbrgh", "jbrxm" });
			request.setAttribute("jbrList", jbrList);
			return mapping.findForward("success");
		}
		String ydjg = DealString.toGBK(request.getParameter("ydjg"));
		String sflq = DealString.toGBK(request.getParameter("sflq"));
		String jbr = request.getParameter("jbr");
		String ccrq = request.getParameter("ccrq");
		sql = "update cpydb set ydjg=?,sflq=?,jbr=? where " + pk + "=?";
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)){
			sql = "update cpydb set ydjg=?,sflq=?,jbr=?,ccrq=? where " + pk + "=?";
			dao.runUpdate(sql, new String[] { ydjg, sflq, jbr, ccrq, pkVal });
		}else{
			dao.runUpdate(sql, new String[] { ydjg, sflq, jbr, pkVal });
		}
		return null;

	}



	@SuppressWarnings("unchecked")
	private ActionForward insureAppAudit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CommanForm checkForm = (CommanForm) form;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		Vector<Object> rs = new Vector<Object>();
		String writeAble = CheckPower.checkUsrPower(session.getAttribute("userName").toString(), "InsureAppAudit.do")?"yes":"no";// дȨ��
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql���
		String querry = " where 1=1 ";// sql����
		String tips = "";// λ����ʾ��Ϣ
		String tableName = "";// ��ѯ��ϢԴ����Ϊ��ͼ����
		String rsNum = "0";// ���صļ�¼��
		String realTable = "";// ����Դ��
		String pk = "";// ����Դ����������ʽΪ���ֶ���||�ֶ���||�ֶ�������		
		String dataType = request.getParameter("act");
		String nj = checkForm.getNj();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		sql = "select dqxn,dqnd from xtszb where rownum=1";
		colList = dao.getOneRs(sql, new String[] {}, new String[] { "dqxn","dqnd" });
		String xn = colList[0];
		String nd = colList[1];
		checkForm.setXn(xn);
		checkForm.setNd(nd);
		String bmdm = request.getParameter("xydm");
		String bxxzdm = request.getParameter("bxxzdm");
		String xxdm = StandardOperation.getXxdm();
		String shzt = DealString.toGBK(checkForm.getShzt());
		checkForm.setShzt(shzt);
		sql = "select bxxzdm,bxxzmc from bxxzb";
		List tbxzdmList = dao.getList(sql, new String[] {}, new String[] {"bxxzdm", "bxxzmc" });
		request.setAttribute("tbxzdmList", tbxzdmList);
		if(userType.equalsIgnoreCase("xy")){
			bmdm = userDep;
			checkForm.setXydm(bmdm);
		}

		realTable = "xsbxb";
		pk = "xh||nd||tbgsdm||tbxzdm";
		tips = "�������� - ������Ϣ - ѧУ���";
		tableName = "view_xsbxxx";
		querry += "and xn = '" + xn + "' ";
		querry += "and nd = '" + nd + "' ";
		if (isNull(nj)) {
			querry += "and 1=1 ";
		} else {
			querry += "and nj = '" + nj + "' ";
		}
		if (isNull(bxxzdm)) {
			querry += "and 1=1 ";
		} else {
			querry += "and tbxzdm = '" + bxxzdm + "' ";
		}
		if (isNull(shzt)) {
			querry += "and 1=1 ";
		} else {
			querry += "and xxsh = '" + shzt + "' ";
		}
		if (isNull(bmdm)) {
			querry += "and 1=1 ";
		} else {
			querry += "and xydm = '" + bmdm + "' ";
		}

		colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd", "xh",
				"xm", "bjmc", "bxxzmc","bxnx","bf","xxsh","xysh","xxyj","xyyj" };
		if(userType.equalsIgnoreCase("xy")){
			sql = "select rownum �к�,(case nvl(a.xysh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
				+ " a.* from(select "
				+ pk
				+ " ����,a.* from "
				+ tableName
				+ " a" + querry + " and sqsj is not null order by xysh desc) a ";
		}
		if(userType.equalsIgnoreCase("xx")){
			sql = "select rownum �к�,(case nvl(a.xxsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
				+ " a.* from(select "
				+ pk
				+ " ����,a.* from "
				+ tableName
				+ " a" + querry + " and sqsj is not null and xysh='ͨ��'  order by xxsh desc) a ";
		}
		
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {
			if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
				ShgcTbxxForm model = new ShgcTbxxForm();
				TbsqcxService service = new TbsqcxService();
				model.setXn(xn);
				model.setNd(nd);
				model.setNj(nj);
				model.setXydm(bmdm);
				model.setUserType(userType);
				rs.addAll(service.audtingQue(model).getRs()) ;
				topTr = service.audtingQue(model).getTopTr();
			}else{
				rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			}
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("act", dataType);// �������ݲ�ѯ����
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("njList", dao.getNjList());// �����꼶�б�
		request.setAttribute("xnList", dao.getXnndList());// ����ѧ���б�
		request.setAttribute("xqList", dao.getXqList());// ����ѧ���б�
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", dao.getZyList(userDep));// ����רҵ�б�
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);// ���ͼ�¼��
		request.setAttribute("path", "InsureAppAudit.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("success");
	}

	private ActionForward insureChkOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String xxdm = StandardOperation.getXxdm();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		CommanForm priseChkForm = (CommanForm) form;
		String act = request.getParameter("act");
		String pkVal = request.getParameter("pkVal");		
		String pk = "xh||nd||tbgsdm||tbxzdm";
		String xxyj = DealString.toGBK(request.getParameter("xxyj"));
		String xyyj = DealString.toGBK(request.getParameter("xyyj"));
		String sql = "";
		String[] colList = new String[] {};
		if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
			pk = "xh||nd";
		}
		if ((act == null) || !act.equalsIgnoreCase("save")) {
			if(userType.equalsIgnoreCase("xy")){
				sql = "select "
					+ pk
					+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,XYSH yesNo,bxgsmc,bxxzmc,bxnx,bf,xxyj,xyyj from view_xsbxxx where "
					+ pk + "='" + pkVal + "'";

			}
			if(userType.equalsIgnoreCase("xx")){
				sql = "select "
					+ pk
					+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,XXSH yesNo,bxgsmc,bxxzmc,bxnx,bf,xxyj,xyyj from view_xsbxxx where "
					+ pk + "='" + pkVal + "'";
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
				//�Ϻ����̼�����ѧ
				if(userType.equalsIgnoreCase("xy")){
					sql = "select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,XYSH yesNo,bxgsmc,bxxzmc,bxnx,nvl(bf,'0')bf,xxyj,xyyj from view_xsbxxx where "
						+ pk + "='" + pkVal + "'";
				}else{
					sql = "select "
						+ pk
						+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,XXSH yesNo,bxgsmc,bxxzmc,bxnx,nvl(bf,'0')bf,xxyj,xyyj from view_xsbxxx where "
						+ pk + "='" + pkVal + "'";
				}
			}
			colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ", "XYMC",
					"ZYMC", "BJMC", "XB", "bxgsmc", "yesNo", "bxxzmc",
					"bxnx", "bf","xxyj" ,"xyyj"};
			HashMap<String, String> rs = dao.getMap(sql, new String[] {}, colList);			
			for (int i = 0; i < colList.length; i++) {				
				request.setAttribute(colList[i], rs.get(colList[i]));
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
				String[] bxxzmc = dao.getRs("select bxxzmc from view_xsbxxx where xh||nd=?", new String[]{pkVal}, "bxxzmc");
				String bf = dao.getOneRs("select sum(bf) bf from view_xsbxxx where xh||nd=?", new String[]{pkVal}, "bf"); 
				String sBxxzmc = "";
				for(int i=0; i<bxxzmc.length; i++){
					sBxxzmc += " " + bxxzmc[i];
				}
				sBxxzmc = sBxxzmc==null ? "" : sBxxzmc;
				request.setAttribute("bf", bf);
				request.setAttribute("bxxzmc", sBxxzmc);
			}
			priseChkForm.setYesNo(rs.get("yesNO"));
			request.setAttribute("rs", rs);
			request.setAttribute("chkList", dao.getChkList(3));
			request.setAttribute("writeAble", writeAble);
			return mapping.findForward("success");
		}
		String yesNo = request.getParameter("yesNo");
		yesNo = DealString.toGBK(yesNo);

		if(userType.equalsIgnoreCase("xy")){
			StandardOperation.update("xsbxb", new String[] {"xysh","xyyj"}, new String[] {yesNo,xyyj}, pk, pkVal, request);
		}
		if(userType.equalsIgnoreCase("xx")){
			StandardOperation.update("xsbxb", new String[] {"xxsh","xxyj"}, new String[] {yesNo,xxyj}, pk, pkVal, request);
		}

		request.setAttribute("userType", userType);
		return null;
	}

	private ActionForward stuPunishAudit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CommanForm checkForm = (CommanForm) form;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String xxdm = Base.xxdm;
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
			return new ActionForward("/shgcxxshdefault.do",false);
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)) {//�㶫����ѧԺΥ�ʹ������
			return new ActionForward("/wjcfgdbyshdefault.do", false);
		} else if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/wjcf_zjlg_cfsh.do", false);
		} else if (Globals.XXDM_XMLGXY.equalsIgnoreCase(xxdm)) {//������������˹���
			return new ActionForward("/wjcf_xmlg_cfsh.do", false);
		}
		String res = request.getParameter("res");
		if (!StringUtils.isNull(res)) {
			request.setAttribute("res", res);
		}
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql���
		String querry = " where 1=1 ";// sql����
		String tips = "";// λ����ʾ��Ϣ
		String tableName = "";// ��ѯ��ϢԴ����Ϊ��ͼ����
		String rsNum = "0";// ���صļ�¼��
		String realTable = "";// ����Դ��
		String pk = "";// ����Դ����������ʽΪ���ֶ���||�ֶ���||�ֶ�������
		String writeAble = "yes";// дȨ��
		String dataType = request.getParameter("act");
		String nj = checkForm.getNj();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		String bmdm="";
		if ("xy".equalsIgnoreCase(userType)) {
			bmdm = userDep;
		} else {
			bmdm = request.getParameter("xydm");
		}
		checkForm.setXydm(bmdm);
		
		if (StringUtils.isNull(checkForm.getXn())) {
			checkForm.setXn(Base.currXn);
		}
		if (StringUtils.isNull(checkForm.getNd())) {
			checkForm.setNd(Base.currNd);
		}		
		String xn = checkForm.getXn();
		String nd = checkForm.getNd();
		
		String cflb = request.getParameter("cflb");
		String shzt = DealString.toGBK(checkForm.getShzt());
		String xysh = checkForm.getXysh();
		String xxsh = checkForm.getXxsh();
		checkForm.setShzt(shzt);
		realTable = "wjcfb";
		pk = "xh||xn||sbsj";
		tips = "Υ�ʹ��� - ������� - ���";
		String cfnx = request.getParameter("cfnx");
		String xftz = request.getParameter("xftz");
		tableName = "view_wjcf";
		querry += "and xn = '" + xn + "' ";
		if (StringUtils.isNotNull(nj)) {
			querry += "and nj = '" + nj + "' ";
		}
		if (StringUtils.isNotNull(cflb)) {
			querry += "and cflb = '" + cflb + "' ";
		}
		if (StringUtils.isNotNull(shzt)) {
			querry += "and xxsh = '" + shzt + "' ";
		}
		if (StringUtils.isNotNull(bmdm)) {
			querry += "and xydm = '" + bmdm + "' ";
		}
		if (!StringUtils.isNull(checkForm.getZydm())) {
			querry += " and zydm='" + checkForm.getZydm() + "'";
		}
		if (!StringUtils.isNull(checkForm.getBjdm())) {
			querry += " and bjdm ='" + checkForm.getBjdm() + "'";
		}
		if (!StringUtils.isNull(checkForm.getXh())) {
			querry += " and xh = '" + checkForm.getXh() + "'";
		}
		if (!StringUtils.isNull(cfnx)) {
			querry += " and cfnx like '%" + cfnx + "%' ";
		}
		if (!StringUtils.isNull(xftz)) {
			querry += " and xftz = '" + xftz + "' ";
		}
		if (!StringUtils.isNull(xysh)) {
			querry += " and xysh = '" + xysh + "' ";
		}
		if (!StringUtils.isNull(xxsh)) {
			querry += " and xxsh = '" + xxsh + "' ";
		}
		if (!Base.isNull(checkForm.getSfqs())) {
			querry += " and sfqs='" + checkForm.getSfqs() + "'";
		}
		
		String xsqr = request.getParameter("xsqr");
		if (StringUtils.isNotNull(xsqr)) {
			querry += " and xsqr = '";
			querry += xsqr;
			querry += "'";
		} 
		if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(xxdm)){//�㽭�Ƽ�ѧԺ
			if(!"xy".equalsIgnoreCase(userType)){//��ѧԺ�û�
				querry += " and shbm<>'Ժ��' and xysh='ͨ��'";
			}
		}
			
		if (Globals.XXDM_HYGXY.equalsIgnoreCase(xxdm)) {
			if ("xy".equalsIgnoreCase(userType)) {
				tips = "Υ�ʹ��� - ��� - ϵԺ���";
				querry += "and cfjb <= (select cfjb from cflbdmb where cflbmc like '%�ǹ�%')";
			} else {
				tips = "Υ�ʹ��� - ��� - ѧ�������";
				querry += "and xysh='ͨ��' ";
			}
		}else if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){//���ݴ�ѧ2010.9.2qlj
			if("xy".equalsIgnoreCase(userType)){
				querry+=" and xsqr='��ȷ��' ";
			}else if(!"xy".equalsIgnoreCase(userType)){
				querry+=" and shbm<>'Ժ��' and xsqr='��ȷ��' and xysh='ͨ��' ";
			}		
		}else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)
				&& !"xy".equalsIgnoreCase(userType)) {
			querry += " and xysh='ͨ��'";
		}else if (Globals.XXDM_NBCSZYJSXY.equalsIgnoreCase(xxdm)) {
			if ("xx".equalsIgnoreCase(userType)) {
				querry += "  and xysh='ͨ��'";
			}
		}else if(Globals.XXDM_BJQNZZXY.equalsIgnoreCase(xxdm)){
			if ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)) {
				querry += "  and xysh='ͨ��'";
			}
		}
		userDep = "";
		List cflbList=null;
		colList = new String[] { "bgcolor", "����","dis", "�к�", "xn", "nd", "xh",
				"xm", "bjmc", "cflbmc","cfyymc","cfwh","cfsj","xxsh" };
		
		String sh = request.getParameter("sh");
		if (StringUtils.isNotNull(sh)) {
			if ("xy".equalsIgnoreCase(userType)) {
				querry += " and xysh='";
				querry += sh;
				querry += "'";
			} else {
				querry += " and xxsh='";
				querry += sh;
				querry += "'";
			}
		}
		
		if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
			sql = "select cflbdm,cflbmc from cflbdmb where cflbmc=? or cflbmc=?";
			cflbList = dao.getList(sql, new String[] {"����","���ؾ���"}, new String[] {
					"cflbdm", "cflbmc" });			
			sql = "select rownum �к�,'' dis,(case nvl(a.xxsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
				+ " a.* from(select "
				+ pk
				+ " ����,a.* from "
				+ "view_wjcfsb"
				+ " a" + querry + " and (cflbmc='����' or cflbmc='���ؾ���') and sbsj is not null order by xxsh desc) a";
		} else if (Globals.XXDM_HYGXY.equalsIgnoreCase(xxdm)) {
			if ("xy".equalsIgnoreCase(userType)) {
				colList = new String[] { "bgcolor", "����", "dis", "�к�", "xn", "nd", "xh",
						"xm", "bjmc", "cflbmc","cfyymc","sbsj","xysh" };
				sql = "select cflbdm,cflbmc from cflbdmb";
				cflbList = dao.getList(sql, new String[] {}, new String[] {
						"cflbdm", "cflbmc" });
				sql = "select rownum �к�,'' dis,(case nvl(a.xysh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " ����,a.* from "
					+ tableName
					+ " a" + querry + " and sbsj is not null order by xxsh desc) a";
			} else {
				sql = "select cflbdm,cflbmc from cflbdmb";
				cflbList = dao.getList(sql, new String[] {}, new String[] {
						"cflbdm", "cflbmc" });
				sql = "select rownum �к�,'' dis,(case nvl(a.xxsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " ����,a.* from "
					+ tableName
					+ " a" + querry + " and sbsj is not null order by xxsh desc) a";
			}
		} else if (Globals.XXDM_NBCSZYJSXY.equalsIgnoreCase(xxdm)) {
			sql = "select cflbdm,cflbmc from cflbdmb";
			cflbList = dao.getList(sql, new String[] {}, new String[] {
					"cflbdm", "cflbmc" });
			colList = new String[] { "bgcolor", "����","dis",  "�к�", "xn", "nd", "xh",
					"xm", "bjmc", "cflbmc","cfyymc","cfwh","cfsj","xxsh", "xftz", "sfsb" };
			if ("xy".equalsIgnoreCase(userType)) {
				sql = "select rownum �к�,'' dis,(case nvl(a.xysh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " ����,a.xn,a.nd,a.xh,a.xm,a.bjmc,a.cflbmc,a.cfyymc,a.cfwh,a.cfsj,a.sfsb,a.xysh,(case when xftz='yes' then '���·�' else 'δ�·�' end) xftz from "
					+ tableName
					+ " a" + querry + " and sbsj is not null order by xxsh desc) a";
				colList = new String[] { "bgcolor", "����", "dis", "�к�", "xn", "nd", "xh",
						"xm", "bjmc", "cflbmc","cfyymc","cfwh","cfsj","xysh", "xftz", "sfsb" };
			} else {
				sql = "select rownum �к�,'' dis,(case nvl(a.xxsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " ����,a.xn,a.nd,a.xh,a.xm,a.bjmc,a.cflbmc,a.cfyymc,a.cfwh,a.cfsj,a.sfsb,a.xxsh,(case when xftz='yes' then '���·�' else 'δ�·�' end) xftz from "
					+ tableName
					+ " a" + querry + " and sbsj is not null order by xxsh desc) a";
			}
		} else if(Globals.XXDM_WHSYFWXY.equalsIgnoreCase(Base.xxdm)){
			sql = "select cflbdm,cflbmc from cflbdmb";
			cflbList = dao.getList(sql, new String[] {}, new String[] {
					"cflbdm", "cflbmc" });
			
			if ("xy".equalsIgnoreCase(userType)) {
				sql = "select rownum �к�,(case when xxsh='ͨ��' then 'disabled' else '' end) dis, (case nvl(a.xysh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " ����,a.* from "
					+ tableName
					+ " a" + querry + " and xxsh='δ���' and sbsj is not null order by xxsh desc) a";
				colList = new String[] { "bgcolor", "����", "dis", "�к�", "xn", "nd", "xh",
						"xm", "bjmc", "cflbmc","cfyymc","cfwh","cfsj","shbm", "xsqr","xysh" };
			} else {
				sql = "select rownum �к�,'' dis,(case nvl(a.xxsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " ����,a.* from "
					+ tableName
					+ " a " + querry + " and shbm='У��'  and xysh='ͨ��' and sbsj is not null order by xxsh desc) a";
				
					colList = new String[] { "bgcolor", "����", "dis", "�к�", "xn", "nd", "xh",
							"xm", "bjmc", "cflbmc","cfyymc","cfwh","cfsj","shbm", "xsqr","sfqs", "xxsh" };
					
			}
		} else{
			sql = "select cflbdm,cflbmc from cflbdmb";
			cflbList = dao.getList(sql, new String[] {}, new String[] {
					"cflbdm", "cflbmc" });
			
			if ("xy".equalsIgnoreCase(userType)) {
				sql = "select rownum �к�,(case when xxsh='ͨ��' then 'disabled' else '' end) dis, (case nvl(a.xysh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " ����,a.* from "
					+ tableName
					+ " a" + querry + " and sbsj is not null order by xxsh desc) a";
				
				if (Globals.XXDM_GZDX.equals(xxdm)) {
					colList = new String[] { "bgcolor", "����","dis", "�к�", "xn", "nd", "xh",
							"xm", "bjmc", "cflbmc","cfyymc","cfwh","cfsj","xsqr","xysh","xxsh" };
				} else {
					colList = new String[] { "bgcolor", "����", "dis", "�к�", "xn", "nd", "xh",
							"xm", "bjmc", "cflbmc","cfyymc","cfwh","cfsj","shbm", "xsqr","xysh" };
				}
				
			} else {
				sql = "select rownum �к�,'' dis,(case nvl(a.xxsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " ����,a.* from "
					+ tableName
					+ " a " + querry + " and sbsj is not null order by xxsh desc) a";
				
//					colList = new String[] { "bgcolor", "����", "dis", "�к�", "xn", "nd", "xh",
//							"xm", "bjmc", "cflbmc","cfyymc","cfwh","cfsj","shbm", "xsqr","sfqs", "xxsh" };

					colList = new String[] { "bgcolor", "����", "dis", "�к�", "xn", "xh",
							"xm", "bjmc", "cflbmc","cfyymc","cfwh","cfsj", "xxsh" };
					
			}
			
		}
		
		
		
		request.setAttribute("cflbList", cflbList);
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
//		if ((request.getParameter("go") != null)
//				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		//}
		request.setAttribute("shList", dao.getChkList(3));
		
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("act", dataType);// �������ݲ�ѯ����
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("njList", dao.getNjList());// �����꼶�б�
		request.setAttribute("xnList", dao.getXnndList());// ����ѧ���б�
		request.setAttribute("xqList", dao.getXqList());// ����ѧ���б�
		
		//request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		//request.setAttribute("zyList", dao.getZyList(userDep));// ����רҵ�б�
		//request.setAttribute("bjList", dao.getBjList("", "", nj));
		
		FormModleCommon.setNjXyZyBjList(request);
		
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);// ���ͼ�¼��
		//�����ж�Ҫ���д��ֱ���Ĵ�ӡ
		if (xxdm.equalsIgnoreCase(Globals.XXDM_CDTYXY)) {
			request.setAttribute("printWj", "true");
		}
		return mapping.findForward("success");
	}

	private ActionForward stuTicketBookSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String xxdm = StandardOperation.getXxdm();
		String sql = "";
		String[] colList = null;
		String[] colListCN = null;
		Vector<Object> rs = new Vector<Object>();
		String xh = session.getAttribute("userName").toString();
		colList = new String[] { "�к�", "xn", "nd", "xqmc","dph", "xh", "xm", "cc", "ydjg", "sflq", "czmc", "ccrq","Ʊ��" };
		colListCN = dao.getColumnNameCN(colList, "VIEW_CPYD");
		List topTr = dao.arrayToList(colList, colListCN);
		sql = "select rownum �к�, a.* from view_cpyd a where xh=?";
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)){
			sql = "select rownum �к�,a.xn,a.nd,a.xqmc,a.dph,a.xh,a.xm,a.cc,a.ydjg,a.sflq,(select b.zdz from hccpydkb b where a.xh=b.xh and a.cc=b.cc) czmc,a.ccrq ,(select b.pj from hccpydkb b where a.xh=b.xh and a.cc=b.cc) Ʊ�� from view_cpyd a where a.xh=?";
		}
		rs.addAll(dao.rsToVator(sql, new String[] { xh }, colList));
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("num", String.valueOf(rs.size()));
		return mapping.findForward("success");
	}

	private ActionForward autoaccount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
//		String currXn = Base.currXn;
//		String currNd = Base.currNd;
		String dqNd = "";
		String dqXq = "";
		String dqXn = "";
		String xxdm = StandardOperation.getXxdm();
		DAO dao = DAO.getInstance();
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
			dqXn = dao.getConf(2);
			dqNd = dao.getConf(3);
			dqXq = dao.getConf(6);
		} else {
			dqXn = dao.getConf(0);
			dqXq = dao.getConf(1);
			dqNd = dao.getConf(4);
		}
		// �Զ����������ɼ��������ɼ�
		String sql = "";
		boolean res = false;
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)){
			String xn = request.getParameter("xn");
			String xq = request.getParameter("xq");
			String bjdm = request.getParameter("bjdm");
			sql = "{call syzy_zhszcp_calculate(?,?,?)}";
			res  = dao.runProcuder(sql, new String[]{xn,xq,bjdm});
			if (res) {
				request.setAttribute("autoCj", "ok");
			} else {
				request.setAttribute("autoCj", "no");
			}
			return new ActionForward("/dyszjfwh.do",false);
		} else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZBDX)){
			//�б���ѧ���Զ������ۺ����ʲ����ɼ�
			dqXn = dao.getConf(2);
			dqNd = dao.getConf(3);
//			String aaa=request.getSession().getAttribute("userDep").toString();
			sql = "{call zbdx_zhszcp_calculate(?)}";
			res  = dao.runProcuder(sql, new String[]{request.getSession().getAttribute("userDep").toString()});
			if (res) {
				request.setAttribute("autoCj", "ok");
			} else {
				request.setAttribute("autoCj", "no");
			}
			return mapping.findForward("success");
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
			sql = "{call AUTOACCOUNT(?,?,?)}";
			res = dao.runProcuder(sql, new String[] {dqXn, dqNd, dqXq});
			if (res) {
				request.setAttribute("autoCj", "ok");
			} else {
				request.setAttribute("autoCj", "no");
			}
			return mapping.findForward("success");
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
			String xn = request.getParameter("xn");
			String xq = request.getParameter("xq");

			if("".equalsIgnoreCase(xn) || xn == null){
				xn = dqXn;
			}
			if("".equalsIgnoreCase(xq) || xq == null){
				xq = dqXq;
			}
			String bjdm = request.getParameter("bjdm");
			if(request.getParameter("type")!=null&&request.getParameter("type").equalsIgnoreCase("drjs")){
				sql = "{call JSXX_DRAUTOACCOUNT(?,?,?,?)}";
			}else{
				sql = "{call JSXX_AUTOACCOUNT(?,?,?,?)}";
			}
			res = dao.runProcuder(sql, new String[] {xn, dqNd, xq , bjdm});
			dao.runProcuder(sql, new String[] {xn, dqNd, xq , bjdm});
			if(res){
				request.setAttribute("autoCj", "ok");    		
			}else{
				request.setAttribute("autoCj", "no");
			}
			return mapping.findForward("success");
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJMZYJSXY)){
			//�㽭��óְҵ����ѧԺ 

		}
		return new ActionForward("/errMsg.do",false);
	}

	/**
	 * ѧУ���Υ���걨
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private ActionForward stuPunishAuditOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DAO dao = DAO.getInstance();
		QgzxZgdzdxService qgzxZgdzdxService =new QgzxZgdzdxService();
		CommanForm priseChkForm = (CommanForm) form;
		String act = request.getParameter("act");
		String pkVal = request.getParameter("chgCodeFlag") == null ? DealString.toGBK(request.getParameter("pkVal")) : request.getAttribute("pkVal").toString();
		String xxclyj=DealString.toGBK(priseChkForm.getXXCLYJ());
		String pk = "xh||xn||sbsj";
		String sql = "";
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName=session.getAttribute("userName").toString();
		String[] colList = new String[] {};
		String xxdm=dao.getXxdm();
		String cfsj = "";
		String cfwh = "";
		String xyclyj = DealString.toGBK(priseChkForm.getXyclyj());
		List cflbList = dao.getList("select cflbdm,cflbmc from cflbdmb", new String[] {}, new String[] {
				"cflbdm", "cflbmc" });
		List cfyyList = dao.getList("select cfyydm,cfyymc from cfyydmb", new String[] {}, new String[] {
				"cfyydm", "cfyymc" });
		request.setAttribute("cflbList", cflbList);
		request.setAttribute("cfyyList", cfyyList);
		if ((act == null) || !act.equalsIgnoreCase("save")) {
			if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
				sql = "select "
					+ pk
					+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,sbsj,XB,XXSH yesNo,cflbmc,cfyymc,XXCLYJ,wjsj,shsj from view_wjcfsb where "
					+ pk + "='" + pkVal + "'";

				colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ", "XYMC",
						"ZYMC", "BJMC", "XB", "cflbmc", "yesNo", "cfyymc","XXCLYJ","wjsj","shsj", "sbsj"};
				request.setAttribute("showXbemy", "showXbemy");
			}else{
				sql = "select "
					+ pk
					+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,sbsj,BJMC,XB,XXSH yesNo,cflbmc,cfyymc,XXCLYJ,xyclyj,cfsj,cfwh,cflb,cfyy,bz,kf,(select shbm from cflbdmb b where a.cflb=b.cflbdm) shbm,lxcksj,cfnx,sfsb,sbsy,wjsj,xsqr,qrsj,fsjname,pysj,sfqs from view_wjcf a where "
					+ pk + "='" + pkVal + "'";
				
				if (Globals.XXDM_HYGXY.equalsIgnoreCase(xxdm)) {
					if ("xy".equalsIgnoreCase(userType)) {
						sql = "select "
							+ pk
							+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,sbsj,BJMC,XB,xysh yesNo,cflbmc,cfyymc,XXCLYJ,xyclyj,cfsj,cfwh,cflb,cfyy,bz,kf,(select shbm from cflbdmb b where a.cflb=b.cflbdm) shbm,lxcksj,cfnx,sfsb,sbsy,wjsj,xsqr,qrsj,fsjname,pysj,sfqs from view_wjcf a where "
							+ pk + "='" + pkVal + "'";
					}
				}else {
					if ("xy".equalsIgnoreCase(userType)) {
						if(xxdm.equalsIgnoreCase(Globals.XXDM_GZDX)){
							request.setAttribute("fsjnames", qgzxZgdzdxService.getFdyXm(userName));
							sql = "select "
								+ pk
								+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,sbsj,BJMC,XB,XYSH yesNo,cflbmc,cfyymc,xyclyj,XYCLYJ XXCLYJ,cfsj,cfwh,cflb,cfyy,bz,kf,(select shbm from cflbdmb b where a.cflb=b.cflbdm) shbm,lxcksj,cfnx,sfsb,sbsy,wjsj,xsqr,qrsj,fsjname,pysj,sfqs from view_wjcf a where "
								+ pk + "='" + pkVal + "'";
							
							
						}else{
						sql = "select "
							+ pk
							+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,sbsj,BJMC,XB,XYSH yesNo,cflbmc,cfyymc,xyclyj,XYCLYJ XXCLYJ,cfsj,cfwh,cflb,cfyy,bz,kf,(select shbm from cflbdmb b where a.cflb=b.cflbdm) shbm,lxcksj,cfnx,sfsb,sbsy,wjsj,xsqr,qrsj,fsjname,pysj,sfqs from view_wjcf a where "
							+ pk + "='" + pkVal + "'";
						}
						
					}
				}	
				colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ", "XYMC",
						"ZYMC", "BJMC", "XB", "cflbmc", "yesNo", "cfyymc","XXCLYJ","pysj","sfqs",
						"cfsj","cfwh","cflb","cfyy", "sbsj","bz","kf","xyclyj", "shbm", "lxcksj","cfnx", "sfsb", "sbsy","wjsj", "xsqr", "qrsj","fsjname"};
				List<HashMap<String, String>> fjList = dao.getList(
						"select cflbmc,cfyymc,cfwh,cfsj,fjsclj from view_wjcf where "
						+ pk + "='" + pkVal + "'", new String[] {},
						new String[] {"cflbmc", "cfyymc", "cfwh", "cfsj", "fjsclj"});
				if (fjList != null && fjList.size() > 0) {
					if (StringUtils.isNull(fjList.get(0).get("fjsclj"))) {
						fjList = new ArrayList<HashMap<String,String>>();
					}
				}
				request.setAttribute("fjList", fjList);
			}
			String[] rs = dao.getOneRs(sql, new String[] {}, colList);
			if (rs == null) {
				rs = new String[colList.length];
				for (int i = 0; i < colList.length; i++) {
					rs[i] = "";
					request.setAttribute(colList[i], rs[i]);
				}
			} else {
				for (int i = 0; i < colList.length; i++) {
					rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? "" : rs[i];
					request.setAttribute(colList[i], rs[i]);
				}
			}	

			cfsj = rs!=null ? rs[16] : "";
			cfwh = rs!=null ? rs[17] : "";
			priseChkForm.setYesNo(rs[11]);
			priseChkForm.setXXCLYJ(rs[13]);
			priseChkForm.setCfsj(cfsj);
			priseChkForm.setCfwh(cfwh);
			
			//�Գ�����Ϣ�����ж�
			if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)
					&& "xy".equalsIgnoreCase(userType)) {
				//ѧԺ�û���һ�����ͨ�������ĺ�Ͳ����ٲ���
				if (!StringUtils.isNull(cfwh) && "ͨ��".equalsIgnoreCase(rs[11])) {
					request.setAttribute("czxx_wjcf_writable", "no");
				}
			}
			
			if(rs.length>20){
				priseChkForm.setCflb(rs[18]);
				priseChkForm.setCfyy(rs[19]);
				priseChkForm.setBz(rs[21]);
				priseChkForm.setXyclyj(rs[23]);
				priseChkForm.setKf(rs[22]);
				priseChkForm.setSbsy(rs[28]);
			}
			
			//����ô�������У�����ôѧԺ�Ͳ��ܽ��з��Ĳ���,���ǿ������
			if ("xy".equalsIgnoreCase(userType)) {
				//��˲���(У��У��Ժ��Ժ��)
				if ("У��".equalsIgnoreCase(rs[24])) {
					request.setAttribute("xy_writable", "no");
					
//					ѧУ���ͨ����,ѧԺ�����ٲ���
					String xxsh = dao.getOneRs("select xxsh from wjcfb where "+pk+ "='" + pkVal + "'" , new String[]{}, "xxsh");
					if ("ͨ��".equalsIgnoreCase(xxsh)) {
						request.setAttribute("xyNowrite", "true");
					}
				}
				
			}
			
			//������ʾ�����У�쿴ʱ��
		//	if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
				if (rs != null && LXCK.equalsIgnoreCase(rs[10])) {
					
					request.setAttribute("lxck", "yes");
				}
		//	}
			
			request.setAttribute("chkList", dao.getChkList(3));
			return mapping.findForward("success");
		}
		String yesNo = request.getParameter("yesNo");
		yesNo = DealString.toGBK(yesNo);
		String shsj=DealString.toGBK(request.getParameter("shsj"));		
		String view = "";
		if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
			boolean temp = StandardOperation.update("wjcfsbb", new String[]{"xxsh", "xxclyj", "shsj"}, new String[]{yesNo,xxclyj,shsj}, "xh||xn||sbsj", pkVal, request);
			if (temp){
				if (yesNo.equalsIgnoreCase("ͨ��")){//��������ԺѧУ��˳ɹ������ѧ��Υ�ͱ�
					boolean del = StandardOperation.delete("wjcfb", "xh||xn||sbsj", pkVal, request);
					if (del){
						sql = "insert into wjcfb (xn,nd,xh,cflb,cfyy,cfsj,cfwh,bz,xq,wjsj,xxsh) select xn,nd,xh,cflb,cfyy,cfsj,cfwh,bz,xq,wjsj,'ͨ��' from view_wjcfsb where xh||xn||sbsj=?";
						dao.runUpdate(sql,new String[]{pkVal});
					}
				}else{//����ɾ��ѧ��Υ����Ϣ
					StandardOperation.delete("wjcfb", "xh||xn||sbsj", pkVal, request);
				}
			}
		} else {
			String tempPkVal = request.getParameter("xh")+request.getParameter("xn")+DealString.toGBK(request.getParameter("sbsj"));
			String cflb = request.getParameter("cflb");
			String cfyy = request.getParameter("cfyy");
			String kf = DealString.toGBK(request.getParameter("kf"));
			String lxcksj = request.getParameter("lxcksj");
			@SuppressWarnings("unused")
			DAO whdao = DAO.getInstance();

			@SuppressWarnings("unused")
			String yxcfwh = DealString.toGBK(request.getParameter("cfwh"));
			String[] whres = null;//whdao.getOneRs("SELECT cfwh FROM wjcfb w  WHERE w.cfwh=?", new String[]{yxcfwh}, new String[]{"cfwh"});
			if ("".equals(whres) || whres == null) {
				if (Globals.XXDM_HYGXY.equalsIgnoreCase(xxdm)) {
					if ("xy".equalsIgnoreCase(userType)) {
						sql = "update wjcfb set xysh=?,xyclyj=?,cflb=?,cfyy=?,kf=? where xh||xn||sbsj=?";
						StandardOperation.update("wjcfb", new String[] { "xysh",
								"xyclyj", "cflb", "cfyy", "kf" },
								new String[] { yesNo, xyclyj,
										cflb, cfyy, kf }, "xh||xn||sbsj", pkVal, request);
					} else {
						sql = "update wjcfb set xxsh=?,xxclyj=?,cfsj=?,cfwh=?,cflb=?,cfyy=?,kf=? where xh||xn||sbsj=?";
						StandardOperation.update("wjcfb", new String[] { "xxsh",
								"xxclyj", "cflb", "cfyy", "kf" },
								new String[] { yesNo, xxclyj,
										cflb, cfyy, kf }, "xh||xn||sbsj", pkVal, request);
					}
				} else {
					//sql = "update wjcfb set xxsh=?,xxclyj=?,cfsj=?,cfwh=?,cflb=?,cfyy=?,kf=? where xh||xn||sbsj=?";
					String cfnx = request.getParameter("cfnx");
					String pysj = request.getParameter("pysj");
					if ("xy".equalsIgnoreCase(userType)) {
						String xxsh = "";
						String shbm = dao.getOneRs("select shbm from cflbdmb where cflbdm=?", new String[]{cflb}, "shbm");
						if ("Ժ��".equalsIgnoreCase(shbm)) {
							xxsh="ͨ��";
						} else {
							xxsh = "δ���";
						}
						StandardOperation.update("wjcfb", new String[] { "xysh",
								"xyclyj", "cfsj", "cfwh", "cflb", "cfyy", "kf","xxsh", "lxcksj" , "cfnx","fsjname"},
								new String[] { yesNo, xxclyj,
								request.getParameter("cfsj"),
								StringUtils.isNull(DealString.toGBK(request.getParameter("cfwh"))) ? "" : DealString.toGBK(request.getParameter("cfwh")).trim(),
										cflb, cfyy, kf,xxsh,lxcksj,cfnx,qgzxZgdzdxService.getFdyXm(userName) }, "xh||xn||sbsj", pkVal, request);
					} else {
						String []zd= new String[] { "xxsh",
								"xxclyj", "cfsj", "cfwh", "cflb", "cfyy", "kf", "lxcksj" , "cfnx","pysj"};
						String [] inputValue=new String[] { yesNo, xxclyj,
								request.getParameter("cfsj"),
								StringUtils.isNull(DealString.toGBK(request.getParameter("cfwh"))) ? "" : DealString.toGBK(request.getParameter("cfwh")).trim(),
										cflb, cfyy, kf,lxcksj ,cfnx,pysj};
						//���ݴ�ѧ ���Ӹ���������ֶ�2010.9.14 qlj
						if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)
							&&("xy".equalsIgnoreCase(userType))){
							zd=new String[]{ "xxsh",
									"xxclyj", "cfsj", "cfwh", "cflb", "cfyy", "kf", "lxcksj" , "cfnx","fsjname"};
							inputValue=new String[] { yesNo, xxclyj,
									request.getParameter("cfsj"),
									StringUtils.isNull(DealString.toGBK(request.getParameter("cfwh"))) ? "" : DealString.toGBK(request.getParameter("cfwh")).trim(),
											cflb, cfyy, kf,lxcksj ,cfnx,qgzxZgdzdxService.getFdyXm(userName)};
						}
						StandardOperation.update("wjcfb",zd,inputValue, "xh||xn||sbsj", pkVal, request);
					}
					
				}
				view = "view";
			}else{
				view = "noview";
			}
			//dao.runUpdate(sql, new String[] { yesNo,xxclyj,request.getParameter("cfsj"),DealString.toGBK(request.getParameter("cfwh")),cflb,cfyy,pkVal });
			request.setAttribute("pkVal", tempPkVal);
		}
		request.setAttribute("result", view);
		return new ActionForward("/stuPunishAuditOne.do?act=view&chgCodeFlag=yes",false);
	}


	private ActionForward InsureHisQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DAO dao = DAO.getInstance();
		CommanForm insureHisForm = (CommanForm) form;		
		String tabName = insureHisForm.getTabName();
		String rsNum = "0";// ���صļ�¼��
		String sql="";
		String querry = " where 1=1 ";// sql����
		Vector<Object> rs = new Vector<Object>();
		if(tabName==null || tabName.equals("")){
			tabName = "view_xsbxxx";
		}

		String xh = insureHisForm.getXh();
		String xm = insureHisForm.getXh();
		String xy = insureHisForm.getXydm();
		String zy = insureHisForm.getZydm();
		String bj = insureHisForm.getBjdm();
		String nj = insureHisForm.getNj();
		String xn = insureHisForm.getXn();	
		if (xh == null) {
			xh = "";
		}
		if (xy == null) {
			xy = "";
		}
		if (zy == null) {
			zy = "";
		}
		if (xm == null){
			xm = "";
		}
		if (bj == null){
			bj = "";
		}

		if ((xh == null) || xh.equalsIgnoreCase("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xh = '" + xh + "' ";
		}
		if ((nj == null) || nj.equalsIgnoreCase("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and nj = '" + nj + "' ";
		}
		if ((xn == null) || xn.equalsIgnoreCase("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xn = '" + xn + "' ";
		}

		if ((xm == null) || xm.equalsIgnoreCase("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xm like  '%" + xm + "%' ";
		}

		if ((bj == null) || bj.equalsIgnoreCase("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and bjdm = '" + bj + "' ";
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
		String[] colList=new String[] {"xh","xn","xm","xb","bjmc","bxnx","bf","bxdc"};

		sql = "select xh,xn,xm,xb,bjmc,bxnx,bf,bxdc from " + tabName + querry.toString()+" and nj<((select dqnd from xtszb)-xz)";
		if("xfzrx".equalsIgnoreCase(tabName)){
			tabName = "view_gdnz_lpxx";			
			sql = "select xh,xn,xm,xb,bjmc,bxqx,bf,bxdc from " + tabName + querry.toString()+" and nj<((select dqnd from xtszb)-xz)";
			sql = sql+" and sfxfzrx='1'";
			colList=new String[] {"xh","xn","xm","xb","bjmc","bxqx","bf","bxdc"};
		}
		if("lpxx".equalsIgnoreCase(tabName)){
			tabName = "view_gdnz_lpxx";			
			sql = "select xh,xn,xm,xb,bjmc,bxqx,bf,bxdc from " + tabName + querry.toString()+" and nj<((select dqnd from xtszb)-xz)";
			sql = sql+" and sfxfzrx='0'";
			colList=new String[] {"xh","xn","xm","xb","bjmc","bxqx","bf","bxdc"};
		}

		String[] colListCN = dao.getColumnNameCN(colList, tabName);
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

		request.setAttribute("njList", dao.getNjList());
		request.setAttribute("xyList",dao.getXyList());
		request.setAttribute("zyList", dao.getZyList(xy));
		request.setAttribute("bjList", dao.getBjList(xy, zy, nj));	
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("topTr", topTr);
		return mapping.findForward("success");
	}

	/**
	 * ��������Ժ��ѧ��Υ��У��ί���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private ActionForward stuPunishAuditByFsj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
		String userName=session.getAttribute("userName").toString();
		if (!userName.equalsIgnoreCase(xbemyxdw)){//����û�����У��ί�򲻾߱����Ȩ��
			request.setAttribute("errMsg", "��ҳ��ֻ��У��ί������û��ſ���");
			return mapping.findForward("false");
		}
		CommanForm checkForm = (CommanForm) form;
		DAO dao = DAO.getInstance();
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql���
		String querry = " where 1=1 ";// sql����
		String tips = "";// λ����ʾ��Ϣ
		String tableName = "";// ��ѯ��ϢԴ����Ϊ��ͼ����
		String rsNum = "0";// ���صļ�¼��
		String realTable = "";// ����Դ��
		String pk = "";// ����Դ����������ʽΪ���ֶ���||�ֶ���||�ֶ�������
		String writeAble = "yes";// дȨ��
		String dataType = request.getParameter("act");
		String nj = checkForm.getNj();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		sql = "select dqxn,dqnd from xtszb where rownum=1";
		colList = dao.getOneRs(sql, new String[] {}, new String[] { "dqxn",
		"dqnd" });
		String xn = colList[0];
		String nd = colList[1];
		checkForm.setXn(xn);
		checkForm.setNd(nd);
		String bmdm = request.getParameter("xydm");
		String cflb = request.getParameter("cflb");
		String shzt = DealString.toGBK(checkForm.getShzt());
		checkForm.setShzt(shzt);
		sql = "select cflbdm,cflbmc from cflbdmb where cflbmc=? or cflbmc=? or cflbmc=?";
		List cflbList = dao.getList(sql, new String[] {"�ǹ�","����ѧ��","��У�쿴"}, new String[] {
				"cflbdm", "cflbmc" });
		request.setAttribute("cflbList", cflbList);

		realTable = "wjcfb";
		pk = "xh||cfrq||cfwh";
		tips = "Υ�ʹ��� - ��� - У��ί���";
		tableName = "view_wjcfsb";
		querry += "and xn = '" + xn + "' ";
		querry += "and nd = '" + nd + "' ";
		if (Base.isNull(nj)) {
			querry += "and 1=1 ";
		} else {
			querry += "and nj = '" + nj + "' ";
		}
		if (Base.isNull(cflb)) {
			querry += "and 1=1 ";
		} else {
			querry += "and cflb = '" + cflb + "' ";
		}
		if (Base.isNull(shzt)) {
			querry += "and 1=1 ";
		} else {
			querry += "and xxsh = '" + shzt + "' ";
		}
		if (Base.isNull(bmdm)) {
			querry += "and 1=1 ";
		} else {
			querry += "and xydm = '" + bmdm + "' ";
		}
		userDep = "";
		colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd", "xh",
				"xm", "bjmc", "cflbmc","cfyymc","sbsj" ,"xdwsh"};
		sql = "select rownum �к�,(case nvl(a.xdwsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
			+ " a.* from(select "
			+ pk
			+ " ����,a.* from "
			+ tableName
			+ " a" + querry + "and (cflbmc='�ǹ�' or cflbmc='��У�쿴' or cflbmc='����ѧ��') and sbsj is not null order by xdwsh desc) a";
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
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("act", dataType);// �������ݲ�ѯ����
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("njList", dao.getNjList());// �����꼶�б�
		request.setAttribute("xnList", dao.getXnndList());// ����ѧ���б�
		request.setAttribute("xqList", dao.getXqList());// ����ѧ���б�
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", dao.getZyList(userDep));// ����רҵ�б�
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);// ���ͼ�¼��
		return mapping.findForward("success");
	}

	/**
	 * ��������Ժ��ѧ��Υ��У��ί�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private ActionForward stuPunishAuditOneByFsj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DAO dao = DAO.getInstance();
		CommanForm priseChkForm = (CommanForm) form;
		String act = request.getParameter("act");
		String pkVal = DealString.toGBK(request.getParameter("pkVal"));
		String xdwyj=DealString.toGBK(priseChkForm.getXdwyj());
		String pk = "xh||cfrq||cfwh";
		String sql = "";
		String[] colList = new String[] {};
		String cflbmc=DealString.toGBK(request.getParameter("cflbmc"));
		if ((act == null) || !act.equalsIgnoreCase("save")) {
			sql = "select "
				+ pk
				+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,cflbmc,cfyymc,wjsj,xdwsh,xdwyj,xdwshsj from view_wjcfsb where "
				+ pk + "='" + pkVal + "'";
			colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ", "XYMC",
					"ZYMC", "BJMC", "XB", "cflbmc", "cfyymc","wjsj","xdwsh","xdwyj","xdwshsj"};
			String[] rs = dao.getOneRs(sql, new String[] {}, colList);
			for (int i = 0; i < colList.length; i++) {
				rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
						: rs[i];
				request.setAttribute(colList[i], rs[i]);
			}
			priseChkForm.setYesNo(rs[13]);
			priseChkForm.setXdwyj(rs[14]);
			request.setAttribute("chkList", dao.getChkList(3));
			return mapping.findForward("success");
		}
		String yesNo = request.getParameter("yesNo");
		yesNo = DealString.toGBK(yesNo);
		String xdwshsj=DealString.toGBK(request.getParameter("xdwshsj"));
		/*sql = "update wjcfsbb set xdwsh=?,xdwyj=?,xdwshsj=? where xh||cfsj||cfwh=?";
		boolean temp = dao.runUpdate(sql, new String[] { yesNo,xdwyj,xdwshsj,pkVal, });*/
		boolean temp = StandardOperation.update("wjcfsbb", new String[]{"xdwsh", "xdwyj", "xdwshsj"}, new String[]{yesNo,xdwyj,xdwshsj}, "xh||cfsj||cfwh", pkVal, request);
		if (temp){//У��ί���ͨ�����걨ͨ����Ϣ���뵽Υ�ͱ�
			if (yesNo.equalsIgnoreCase("ͨ��") && cflbmc.equalsIgnoreCase("�ǹ�")){
				/*sql = "delete from wjcfb where xh||cfsj||cfwh=?";
				boolean del = dao.runUpdate(sql,new String[]{pkVal});*/
				boolean del = StandardOperation.delete("wjcfb", "xh||cfsj||cfwh", pkVal, request);
				if (del){
					sql="insert into wjcfb (xn,nd,xh,cflb,cfyy,cfsj,cfwh,bz,xq,wjsj,xxsh) select xn,nd,xh,cflb,cfyy,cfsj,cfwh,bz,xq,wjsj,'ͨ��' from view_wjcfsb where xh||cfsj||cfwh=? ";
					dao.runUpdate(sql,new String[]{pkVal});
				}
			}else{//����ɾ��ѧ��Υ����Ϣ
				/*sql = "delete from wjcfb where xh||cfsj||cfwh=?";
				dao.runUpdate(sql, new String[]{pkVal});*/
				StandardOperation.delete("wjcfb", "xh||cfsj||cfwh", pkVal, request);
			}
		}
		request.setAttribute("result", "view");
		return new ActionForward("/stuPunishAuditOneByFsj.do?act=view",false);
	}

	/**
	 *  ��������Ժ��ѧ��Υ��У�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private ActionForward stuPunishAuditByXz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CommanForm checkForm = (CommanForm) form;
		
		String xxdm = Base.xxdm;
		if (Globals.XXDM_XMLGXY.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/wjcf_xmlg_xbsh.do", false);
		}
		
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		
		if (!userName.equalsIgnoreCase(xbemyxz)){//����û�����У���򲻾߱����Ȩ��
			request.setAttribute("errMsg", "��ҳ��ֻ��У���û��ſ���");
			return mapping.findForward("false");
		}
		
		DAO dao = DAO.getInstance();
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql���
		String querry = " where 1=1 ";// sql����
		String tips = "";// λ����ʾ��Ϣ
		String tableName = "";// ��ѯ��ϢԴ����Ϊ��ͼ����
		String rsNum = "0";// ���صļ�¼��
		String realTable = "";// ����Դ��
		String pk = "";// ����Դ����������ʽΪ���ֶ���||�ֶ���||�ֶ�������
		String writeAble = "yes";// дȨ��
		String dataType = request.getParameter("act");
		String nj = checkForm.getNj();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		sql = "select dqxn,dqnd from xtszb where rownum=1";
		colList = dao.getOneRs(sql, new String[] {}, new String[] { "dqxn","dqnd" });
		String xn = colList[0];
		String nd = colList[1];
		checkForm.setXn(xn);
		checkForm.setNd(nd);
		String bmdm = request.getParameter("xydm");
		String cflb = request.getParameter("cflb");
		String shzt = DealString.toGBK(checkForm.getShzt());
		checkForm.setShzt(shzt);
		sql = "select cflbdm,cflbmc from cflbdmb where cflbmc=? or cflbmc=?";
		List cflbList = dao.getList(sql, new String[] {"����ѧ��","��У�쿴"}, new String[] {
				"cflbdm", "cflbmc" });
		request.setAttribute("cflbList", cflbList);
		//	realTable = "wjcfb";
		pk = "xh||cfrq||cfwh";
		tips = "Υ�ʹ��� - ��� - У�����";
		tableName = "view_wjcfsb";
		querry += "and xn = '" + xn + "' ";
		querry += "and nd = '" + nd + "' ";
		if (Base.isNull(nj)) {
			querry += "and 1=1 ";
		} else {
			querry += "and nj = '" + nj + "' ";
		}
		if (Base.isNull(cflb)) {
			querry += "and 1=1 ";
		} else {
			querry += "and cflb = '" + cflb + "' ";
		}
		if (Base.isNull(shzt)) {
			querry += "and 1=1 ";
		} else {
			querry += "and xxsh = '" + shzt + "' ";
		}
		if (Base.isNull(bmdm)) {
			querry += "and 1=1 ";
		} else {
			querry += "and xydm = '" + bmdm + "' ";
		}
		userDep = "";
		colList = new String[] { "bgcolor", "����", "�к�", "xn", "nd", "xh",
				"xm", "bjmc", "cflbmc","cfyymc","sbsj" ,"xdwsh","xzsh"};
		sql = "select rownum �к�,(case nvl(a.xzsh,'δ���') when 'ͨ��' then '#FFFFFF' when 'δ���' then '#DDDDDD' else '#BBBBBB' end) bgcolor,"
			+ " a.* from(select "
			+ pk
			+ " ����,a.* from "
			+ tableName
			+ " a" + querry + "and (cflbmc='��У�쿴' or cflbmc='����ѧ��') and xdwsh='ͨ��' and sbsj is not null order by xzsh desc) a";
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
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("act", dataType);// �������ݲ�ѯ����
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("njList", dao.getNjList());// �����꼶�б�
		request.setAttribute("xnList", dao.getXnndList());// ����ѧ���б�
		request.setAttribute("xqList", dao.getXqList());// ����ѧ���б�
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", dao.getZyList(userDep));// ����רҵ�б�
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);// ���ͼ�¼��
		return mapping.findForward("success");
	}

	/**
	 * ��������Ժ��ѧ��Υ��У���������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private ActionForward stuPunishAuditOneByXz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DAO dao = DAO.getInstance();
		CommanForm priseChkForm = (CommanForm) form;
		String act = request.getParameter("act");
		String pkVal = DealString.toGBK(request.getParameter("pkVal"));
		String xzyj=DealString.toGBK(request.getParameter("xzyj"));
		String pk = "xh||cfrq||cfwh";
		String sql = "";
		String[] colList = new String[] {};
		if ((act == null) || !act.equalsIgnoreCase("save")) {
			sql = "select "
				+ pk
				+ ",ND,XN,XH,XM,NJ,XYMC,ZYMC,BJMC,XB,cflbmc,cfyymc,wjsj,xdwsh,xdwyj,xdwshsj,xzsh,xzyj,xzshsj from view_wjcfsb where "
				+ pk + "='" + pkVal + "'";
			colList = new String[] { pk, "ND", "XN", "XH", "XM", "NJ", "XYMC",
					"ZYMC", "BJMC", "XB", "cflbmc", "cfyymc","wjsj","xdwsh","xdwyj","xdwshsj","xzsh","xzyj","xzshsj"};
			String[] rs = dao.getOneRs(sql, new String[] {}, colList);
			for (int i = 0; i < colList.length; i++) {
				rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
						: rs[i];
				request.setAttribute(colList[i], rs[i]);
			}
			priseChkForm.setYesNo(rs[16]);
			priseChkForm.setXzyj(rs[17]);
			request.setAttribute("chkList", dao.getChkList(3));
			return mapping.findForward("success");
		}
		String yesNo = request.getParameter("yesNo");
		yesNo = DealString.toGBK(yesNo);
		String xzshsj=DealString.toGBK(request.getParameter("xzshsj"));
		/*sql = "update wjcfsbb set xzsh=?,xzyj=?,xzshsj=? where xh||cfsj||cfwh=?";
		boolean temp = dao.runUpdate(sql, new String[] { yesNo,xzyj,xzshsj,pkVal, });*/
		boolean temp = StandardOperation.update("wjcfsbb", new String[]{"xzsh", "xzyj", "xzshsj"}, new String[]{yesNo,xzyj,xzshsj}, "xh||cfsj||cfwh", pkVal, request);
		if (temp){//У�����ͨ������뵽Υ�ʹ��ֱ�
			if (yesNo.equalsIgnoreCase("ͨ��")){
				/*sql = "delete from wjcfb where xh||cfsj||cfwh=?";
				boolean del = dao.runUpdate(sql,new String[]{pkVal});*/
				boolean del = StandardOperation.delete("wjcfb", "xh||cfsj||cfwh", pkVal, request);
				if (del){
					sql = "insert into wjcfb (xn,nd,xh,cflb,cfyy,cfsj,cfwh,bz,xq,wjsj,xxsh) select xn,nd,xh,cflb,cfyy,cfsj,cfwh,bz,xq,wjsj,'ͨ��' from view_wjcfsb where xh||cfsj||cfwh=? ";
					dao.runUpdate(sql, new String[]{pkVal});
				}
			}else{//����ɾ��ѧ��Υ����Ϣ
				/*sql = "delete from wjcfb where xh||cfsj||cfwh=?";
				dao.runUpdate(sql, new String[]{pkVal});*/
				StandardOperation.delete("wjcfb", "xh||cfsj||cfwh", pkVal, request);
			}
		}
		request.setAttribute("result", "view");
		return new ActionForward("/stuPunishAuditOneByXz.do?act=view",false);
	}

	/**
	 * ��ѧ���������ݵ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private ActionForward jxjrsDataExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DAO dao = DAO.getInstance();
//		CommanForm priseChkForm = (CommanForm) form;
		String[] jxjsqxnnd = dao.getOneRs("select jxjsqxn,jxjsqnd from xtszb", new String[]{}, new String[]{"jxjsqxn", "jxjsqnd"});
		String jxjdm = request.getParameter("jxjdm");
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String bjdm = request.getParameter("bjdm");
		String bmlb = request.getParameter("bmlb");
		String nj = request.getParameter("nj");
//		String xq = request.getParameter("xq");
		StringBuffer query = new StringBuffer();
		if (!StringUtils.isNull(jxjdm)) {
			query.append(" and jxjdm = '");
			query.append(jxjdm);
			query.append("' ");
		}
		
		if (!StringUtils.isNull(xydm)) {
			query.append(" and bmdm in (select xydm from view_njxyzybj where xydm='");
			query.append(xydm);
			query.append("') ");
		}
		if (!StringUtils.isNull(zydm)) {
			query.append(" and bmdm in (select zydm from view_njxyzybj where zydm='");
			query.append(zydm);
			query.append("') ");
		}
		if (!StringUtils.isNull(bjdm)) {
			query.append(" and bmdm = '");
			query.append(bjdm);
			query.append("' ");
		}
		if (!StringUtils.isNull(jxjsqxnnd[0])) {
			query.append(" and xn='");
			query.append(jxjsqxnnd[0]);
			query.append("' ");
		}
		if (!StringUtils.isNull(jxjsqxnnd[1])) {
			query.append(" and nd='");
			query.append(jxjsqxnnd[1]);
			query.append("' ");
		}
		if (!StringUtils.isNull(bmlb)) {
			query.append(" and bmlb = '");
			query.append(bmlb);
			query.append("' ");
		}
		if (!StringUtils.isNull(nj)) {
			query.append(" and nj = '");
			query.append(nj);
			query.append("' ");

		}
//		if (!StringUtils.isNull(xq)) {
//			query.append(" and xqdm = '");
//			query.append(xq);
//			query.append("' ");
//
//		}

		String jxjmc = dao.getOneRs("select jxjmc from jxjdmb where jxjdm=?", new String[]{jxjdm}, "jxjmc");
		String titile = String.format(" %1$s ѧ�� %2$s ��� %3$s �������ñ�", jxjsqxnnd[0], jxjsqxnnd[1], jxjmc);
		List<String[]> resList = dao.rsToVator("select mc,nj,jxjmc,jxjbl,cprs,jsrs,jxjrs from view_xyjxjrs where 1=1 " + query.toString(), new String[]{}, new String[]{"mc", "nj","jxjmc", "jxjbl", "cprs", "jsrs", "jxjrs"});
		String modelPath = servlet.getServletContext().getRealPath("")+"/print/hzy_jxjrsexp.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
		WritableSheet ws = wwb.getSheet(0);
		WritableCellFormat tStyle = new WritableCellFormat();
		WritableCellFormat wcfStyle = new WritableCellFormat();
		Alignment alignMent = Alignment.CENTRE;
		wcfStyle.setAlignment(alignMent);
		tStyle.setAlignment(Alignment.CENTRE);
		wcfStyle.setBorder(Border.ALL, BorderLineStyle.THIN);
		//TODO �����ӡ
		ws.addCell(new Label(0,0,titile,wcfStyle));
		if (resList != null && resList.size() > 0) {
			for (int i=0;i<resList.size();i++) {
				String[] tmpList = resList.get(i);
				for (int j=0;j<tmpList.length;j++){
					ws.addCell(new Label(j,i+2,tmpList[j],wcfStyle));
				}
			}

		}
		ExcelMethods.submitWritableWorkbookOperations(wwb);//������ͻ���
		return mapping.findForward("");
	}

	/**
	 * ����Ա��Ӧ�༶�б�
	 * @param arr1
	 * @param arr2
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getFdyBjlist(String[] arr1,
			String[] arr2, String xxdm) throws Exception {
		// ����������ϲ���һ��List�����������Сһ�£�ͨ��Ϊ��Ӣ�Ķ��ա�����Ҫ��Ӣ����ǰ�������ں�
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		int len = (arr1.length > arr2.length) ? arr2.length : arr1.length;
		HashMap<String, String> map = null;
		for (int i = 0; i < len; i++) {
			map = new HashMap<String, String>();
			if (Globals.XXDM_CSMZZYJSXY.equalsIgnoreCase(xxdm)) {
				map.put("en", arr1[i]);
				map.put("cn", arr2[i]);
			} else {
				map.put("bjdm", arr1[i]);
				map.put("bjmc", arr2[i]);
			}
			list.add(map);
		}
		return list;
	}
	
	/**
	 * �ڹ���ѧ��ʱ��λ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qgzxLsgwxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		User user = getUser(request);
		
		RequestForm rForm = new RequestForm();
		CommanForm myForm = (CommanForm) form;
		DAO dao=DAO.getInstance();
		CommService commService=new CommService();
		MakeQuery maekeQ=new MakeQuery();
		
		String xxdm = Base.xxdm;
		String gzzsj_ks=request.getParameter("gzzsj_ks");
		String gzzsj_js=request.getParameter("gzzsj_js");
		
		String gzkssj_ks=request.getParameter("gzkssj_ks");
		String gzkssj_js=request.getParameter("gzkssj_js");
		
		String gzjssj_ks=request.getParameter("gzjssj_ks");
		String gzjssj_js=request.getParameter("gzjssj_js");
		
		request.setAttribute("path", "qgzxLsgwxx.do");
		FormModleCommon.commonRequestSet(request);
		
		String doType=request.getParameter("doType");
		String message="";
		if("del".equalsIgnoreCase(doType)){
			String[]pkV=request.getParameterValues("pkV");
			String[]delSql=new String[pkV.length];
			for(int i=0;i<pkV.length;i++){
				
				delSql[i]=" delete from xg_zjjs_qgzx_lsgwxxb where guid='"+pkV[i]+"' ";
			}
			boolean flag=commService.saveArrDate(delSql);
			
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
			request.setAttribute("message", message);
		}
		
//		 ===============ͨ������ ������=================
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);
		
		//�ж��Ƿ����㽭����ְҵ����ѧԺ
		if("12862".equalsIgnoreCase(xxdm)){
		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);
		// -----------------topTr begin----------------------
		String[]en={"guid","gwmc","xh","xm","nj","xymc","zymc","bjmc","cj","gzzsj"};
		String[]cn={"���","��λ����","ѧ��","����","�꼶",Base.YXPZXY_KEY,"רҵ","�༶","���","������ʱ��"};
		
		dao.arrayToList(en, cn);
		List<HashMap<String,String>>topTr=dao.arrayToList(en, cn);
		// -----------------topTr end-----------------------
		
		// ----------------�����ѯ begin------------------------
		StringBuilder sql = new StringBuilder();
		List<String[]>rs=new ArrayList<String[]>();
		sql.append("  select rownum r,a.* from xg_view_zjjs_qgzx_lsgwxx a ");
		
		String[]queryList={"nj","xydm","zydm","bjdm"};
		String[]querylikeList={"xh","xm","gwmc"};
		StringBuilder query=new StringBuilder();
			if(!Base.isNull(gzzsj_ks)){
				query.append(" and to_number(gzzsj) >=to_number('"+gzzsj_ks+"') ");
			}
			if(!Base.isNull(gzzsj_js)){
				query.append(" and to_number(gzzsj) <=to_number('"+gzzsj_js+"') ");
			}
			maekeQ.makeQuery(queryList, querylikeList, myForm);
			rs = CommonQueryDAO.commonQuery(sql.toString(), maekeQ.getQueryString()
					+ query, maekeQ.getInputList(), en, myForm);
			// -----------------�����ѯ  end----------------------------------
			

			String showNum = String.valueOf(topTr.size()-1);
			commSetting.setShowNum(showNum);

			rForm.setCommSetting(commSetting);

			rForm.setRsArrList((ArrayList<String[]>)rs);
			rForm.setColList(en);
			commService.setRequestValue(rForm, user, request);
			
			// ===============ͨ������ end ============
			
			request.setAttribute("path", "qgzxLsgwxx.do");
			FormModleCommon.commonRequestSet(request);
			FormModleCommon.setNjXyZyBjListForFdyBzr(request);
			FormModleCommon.setNdXnXqList(request);
			//��ͷ
			request.setAttribute("topTr", topTr);
		}else{
			// ��ʾ����ʼ��
			String startNum = "0";
			commSetting.setStartNum(startNum);
			// -----------------topTr begin----------------------
			String[]en={"guid","gwmc","xh","xm","nj","xymc","zymc","bjmc","gzkssj","gzjssj","cj","gzzsj"};
			String[]cn={"���","��λ����","ѧ��","����","�꼶",Base.YXPZXY_KEY,"רҵ","�༶","������ʼʱ��","��������ʱ��","���","������ʱ��"};
			
			dao.arrayToList(en, cn);
			List<HashMap<String,String>>topTr=dao.arrayToList(en, cn);
			// -----------------topTr end-----------------------
			
			// ----------------�����ѯ begin------------------------
			StringBuilder sql = new StringBuilder();
			List<String[]>rs=new ArrayList<String[]>();
			sql.append("  select rownum r,a.* from xg_view_zjjs_qgzx_lsgwxx a ");
			
			String[]queryList={"nj","xydm","zydm","bjdm"};
			String[]querylikeList={"xh","xm","gwmc"};
			StringBuilder query=new StringBuilder();
			if(!Base.isNull(gzkssj_ks)){
				query.append(" and gzkssj >='"+gzkssj_ks+"' ");
			}
			if(!Base.isNull(gzkssj_js)){
				query.append(" and gzkssj <='"+gzkssj_js+"' ");
			}
			if(!Base.isNull(gzjssj_ks)){
				query.append(" and gzjssj >='"+gzjssj_ks+"' ");
			}
			if(!Base.isNull(gzjssj_js)){
				query.append(" and gzjssj <='"+gzjssj_js+"' ");
			}
			maekeQ.makeQuery(queryList, querylikeList, myForm);
			rs = CommonQueryDAO.commonQuery(sql.toString(), maekeQ.getQueryString()
					+ query, maekeQ.getInputList(), en, myForm);
			// -----------------�����ѯ  end----------------------------------
			

			String showNum = String.valueOf(topTr.size()-1);
			commSetting.setShowNum(showNum);

			rForm.setCommSetting(commSetting);

			rForm.setRsArrList((ArrayList<String[]>)rs);
			rForm.setColList(en);
			commService.setRequestValue(rForm, user, request);
			
			// ===============ͨ������ end ============
			
			request.setAttribute("path", "qgzxLsgwxx.do");
			FormModleCommon.commonRequestSet(request);
			FormModleCommon.setNjXyZyBjListForFdyBzr(request);
			FormModleCommon.setNdXnXqList(request);
			//��ͷ
			request.setAttribute("topTr", topTr);
		}
		return mapping.findForward("qgzxLsgwxx");
	}
	

	/**
	 * �ڹ���ѧ��ʱ��λ��Ϣ(��ϸ��Ϣ)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsgwView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DAO dao=DAO.getInstance();
		
		String pkValue = request.getParameter("pkValue");
		String xxdm = Base.xxdm;
		if("12862".equalsIgnoreCase(xxdm)){
		// -----------------��ѯ������¼---------------------
			HashMap<String,String>rs=new HashMap<String,String>();
			StringBuilder sql=new StringBuilder();
			
			sql.append(" select xh,xm,nj,xymc,zymc,bjmc,cj,gzkssj,gzjssj, ");
			sql.append(" gzdz,gznr,gwmc,xb,gzzsj from xg_view_zjjs_qgzx_lsgwxx ");
			sql.append(" where guid= ? ");
			
			String[]colList={"xh","xm","nj","xymc","zymc","bjmc","gzkssj","gzjssj","cj","gzdz","gznr","gwmc","xb","gzzsj"};
			rs=dao.getMap(sql.toString(), new String[]{pkValue},colList);
			
			request.setAttribute("path", "qgzxLsgwxx.do");
			FormModleCommon.commonRequestSet(request);
			request.setAttribute("rs", rs);
		}else{
			// -----------------��ѯ������¼---------------------
			HashMap<String,String>rs=new HashMap<String,String>();
			StringBuilder sql=new StringBuilder();
			
			sql.append(" select xh,xm,nj,xymc,zymc,bjmc,cj,gzkssj,gzjssj, ");
			sql.append(" gzdz,gznr,gwmc,xb from xg_view_zjjs_qgzx_lsgwxx ");
			sql.append(" where guid= ? ");
			
			String[]colList={"xh","xm","nj","xymc","zymc","bjmc","gzkssj","gzjssj","cj","gzdz","gznr","gwmc","xb"};
			rs=dao.getMap(sql.toString(), new String[]{pkValue},colList);
			
			request.setAttribute("path", "qgzxLsgwxx.do");
			FormModleCommon.commonRequestSet(request);
			request.setAttribute("rs", rs);
		}
		return mapping.findForward("lsgwView");
	}
	


}
