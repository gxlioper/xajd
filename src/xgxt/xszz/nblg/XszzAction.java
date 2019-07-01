package xgxt.xszz.nblg;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.ExcelMethods;
import xgxt.utils.SearchUtils;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzActionUtils;
import xgxt.xszz.XszzCommenBean;
import xgxt.xszz.commonN05.XszzCommonN05Service;

/**
 * Title: ѧ����������ϵͳ
 * Description: ������ѧ������Action
 * Copyright: Copyright (c) 2009
 * Company: zfsoft
 * Author: ����
 * Version: 1.0
 * Time: 2009-01-13
 */
public class XszzAction extends BaseAction {

//	private boolean isNull(String str) {
//		return ((str == null) || str.equalsIgnoreCase("") || str
//				.equalsIgnoreCase("all"));
//	}
	
	/**
	 * @describe ����ʱ���趨
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward data_zzsj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		String pk = "xmmc||xydm";
		String tips = "ѧ������ - ��������ά�� - ����ʱ��ά��";
		String rsNum = "0";// ���صļ�¼��
		String tableName = "view_nblg_sjb";
		String[] colList = new String[] { "����", "xmmc", "xymc", "xyrs", "kssj", "jssj" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		String querry1 = " and 1=1 ";
		String writeAble = "yes";// дȨ��
		
		String xmmc = Base.chgNull(request.getParameter("xmmc"), "", 1);
		String xydm = Base.chgNull(request.getParameter("xydm"), "", 1);
		if (userType.equalsIgnoreCase("xy")) {
			writeAble = "no";
			xydm = userDep;
		}
		
		querry.append(SearchUtils.equalSql("xmmc", xmmc));
		querry.append(SearchUtils.equalSql("xydm", xydm));
		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xmmc", xmmc);
		map.put("xydm", xydm);
		
		String sql = "";
		String xxdm = StandardOperation.getXxdm();
		querry1 += Globals.NotJudgeWhichSchool(xxdm);
		List<HashMap<String, String>> tempList = null;
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
			sql = "select gnmkmc xmmc from xszz_gnmkb where xxdm=? and gnmklx='����' order by xssx";
			tempList = dao.getList(sql, new String[] { xxdm },
					new String[] { "xmmc" });
		} else if (Globals.JudgeWhichSchool(xxdm)) {
			sql = "select a.gnmkmc xmmc from "
				+ "((select gnmklj,gnmkmc,xssx from xszz_gnmkb where xxdm=? and gnmklx='����') "
				+ "union (select gnmklj,gnmkmc,xssx from xszz_gnmkb where "
				+ "xxmc is null and gnmklx='����' " + querry1 + ")) a order by xssx";
			tempList = dao.getList(sql, new String[] { xxdm },
					new String[] { "xmmc" });
		} else {
			sql = "select gnmkmc xmmc from xszz_gnmkb where xxdm is null and gnmklx='����' and gnmkmc<>'������ѧ�� ' order by xssx";
			tempList = dao.getList(sql, new String[] {}, new String[] {
			"xmmc" });
			String num = dao.getOneRs("select count(*) num from xszz_gnmkb where gnmkmc='������ѧ�� '", new String[]{}, "num");
			if (!"0".equalsIgnoreCase(num)) {
				tempList.addAll(dao.getList(
						"select xmmc from xszz_com_wszxjdmb order by xmdm",
						new String[] {}, new String[] { "xmmc" }));
			}
		}
		
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			String[] xmmcSjb = dao.getArray("select xmmc from view_nblg_sjb group by xmmc", new String[]{}, "xmmc");
			ArrayList<String> insertXmmc = new ArrayList<String>();
			ArrayList<String> delXmmc = new ArrayList<String>();
			
			for (HashMap<String, String> hs : tempList) {
				boolean b = true;
				for (int i = 0; i < xmmcSjb.length && b; i++) {
					if (xmmcSjb[i].equalsIgnoreCase(hs.get("xmmc"))) {
						b = false;
					}
				}
				if (b) {
					insertXmmc.add(hs.get("xmmc"));
				}
			}
			for (int i = 0; i < xmmcSjb.length; i++) {
				boolean b = true;
				for (Iterator<HashMap<String, String>> it = tempList.iterator(); it.hasNext() && b;) {
					if (xmmcSjb[i].equalsIgnoreCase(it.next().get("xmmc"))) {
						b = false;
					}
				}
				if (b) {
					delXmmc.add(xmmcSjb[i]);
				}
			}
			String[] sqlT = new String[insertXmmc.size()+1];
			sqlT[0] = "delete nblg_sjb where xmmc in (''";
			for (String sT : delXmmc) {
				sqlT[0] += ",'"+sT+"'";
			}
			sqlT[0] += ")";
			
			int n = 1;
			for (String sT : insertXmmc) {
				sqlT[n] = "insert into nblg_sjb(xmmc,xydm) (select '"+sT+"',xydm from view_njxyzybj group by xydm)";
				n++;
			}
			dao.runBatch(sqlT);
			
			sql = "select xmmc||xydm ����,a.* from view_nblg_sjb a" + querry.toString();
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		
		request.setAttribute("xmList", tempList);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);
		return mapping.findForward("data_zzsj");
	}

	/**
	 * @describe ���������õ�ʱ��������ϸ��Ϣ�ͱ�����Ϣ
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward zzsjEdit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String writeAble = "yes";// дȨ��
		String[] outValue = null;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String sql = "select xmmc,xydm,xymc,xyrs,kssj,jssj from view_nblg_sjb where xmmc||xydm=?";
		String[] outString = new String[] { "xmmc", "xydm", "xymc",
				"xyrs", "kssj", "jssj" };

		if ("save".equalsIgnoreCase(act)) {
			String xmmc    = Base.chgNull(request.getParameter("xmmc"), "", 1);
			String xydm    = Base.chgNull(request.getParameter("xydm"), "", 1);
			String kssj    = Base.chgNull(request.getParameter("kssj"), "", 1);
			String jssj    = Base.chgNull(request.getParameter("jssj"), "", 1);
			boolean b = false;
			
			String num = dao.getOneRs(
					"select count(*) num from view_nblg_sjb where xmmc||xydm=?",
					new String[] { xmmc + xydm }, "num");
			if("0".equalsIgnoreCase(num)){
				b = StandardOperation
						.insert("nblg_sjb", new String[] { "xmmc",
								"xydm", "kssj", "jssj" },
								new String[] { xmmc, xydm, kssj, jssj },
								request);
			} else {
				b = StandardOperation.update("nblg_sjb", new String[] {
						"kssj", "jssj" }, new String[] { kssj,
						jssj }, "xmmc||xydm", xmmc + xydm, request);
			}
			if(b){
				request.setAttribute("ok", "ok");
				pkVal = xmmc + xydm;
			}else{
				request.setAttribute("ok", "no");
			}
		}
		
		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		int len1 = outString.length;
		int len2 = 0;
		if(outValue != null){
			len2 = outValue.length;
		}
		int max = 0;
		if (len1 >= len2) {
			max = len2;
		} else {
			max = len1;
		}
		for (int i = 0; i < max; i++) {
			if (null != outValue[i]) {
				map.put(outString[i], outValue[i]);
			} else {
				map.put(outString[i], "");
			}
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			writeAble = "no";
		}
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("zzsjEdit");
	}
	
	/**
	 * @describe ������������ʱ��
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward zzsjPlsz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
		String kssj = "1900-01-01";
		String jssj = "1900-01-01";
		HashMap<String, String> map = new HashMap<String, String>();
		if("save".equalsIgnoreCase(act)){
			kssj  = Base.chgNull(request.getParameter("kssj"), "", 1);
			jssj  = Base.chgNull(request.getParameter("jssj"), "", 1);
			String[] pkT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkT.length];
			for (int i = 0; i < pkT.length; i++) {
				sqlT[i] = "update nblg_sjb set kssj='" + kssj
						+ "',jssj='" + jssj + "' where xmmc||xydm='" + pkT[i]
						+ "'";
			}
			dao.runBatch(sqlT);
			request.setAttribute("end", "end");
		}
		map.put("kssj", kssj);
		map.put("jssj", jssj);
		map.put("pkDel", pkDel);
		request.setAttribute("rs", map);
		return mapping.findForward("zzsjPlsz");
	}
	
	/**
	 * @describe ʱ���ʼ��
	 * @author zhoumi
	 * @return
	 * @throws Exception 
	 */
	public ActionForward zzsjcsh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String xxdm = StandardOperation.getXxdm();
		String querry = " and 1=1";
		String sql = "";
		List<HashMap<String, String>> tempList = null;
		StandardOperation.delete("nblg_sjb", new String[]{"1"}, new String[]{"1"}, request);
		
		querry += Globals.NotJudgeWhichSchool(xxdm);
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
			sql = "select gnmkmc xmmc from xszz_gnmkb where xxdm=? and gnmklx='����' order by xssx";
			tempList = dao.getList(sql, new String[] { xxdm },
					new String[] { "xmmc" });
		} else if (Globals.JudgeWhichSchool(xxdm)) {
			sql = "select a.gnmkmc xmmc from "
				+ "((select gnmklj,gnmkmc,xssx from xszz_gnmkb where xxdm=? and gnmklx='����') "
				+ "union (select gnmklj,gnmkmc,xssx from xszz_gnmkb where "
				+ "xxmc is null and gnmklx='����' " + querry + ")) a order by xssx";
			tempList = dao.getList(sql, new String[] { xxdm },
					new String[] { "xmmc" });
		} else {
			sql = "select gnmkmc xmmc from xszz_gnmkb where xxdm is null and gnmklx='����' and gnmkmc<>'������ѧ�� ' order by xssx";
			tempList = dao.getList(sql, new String[] {}, new String[] {
					"xmmc" });
			String num = dao.getOneRs("select count(*) num from xszz_gnmkb where gnmkmc='������ѧ�� '", new String[]{}, "num");
			if (!"0".equalsIgnoreCase(num)) {
				tempList.addAll(dao.getList(
						"select xmmc from xszz_com_wszxjdmb order by xmdm",
						new String[] {}, new String[] { "xmmc" }));
			}
		}
		
		String[] sqlT = new String[tempList.size()];
		int i = 0;
		for (HashMap<String, String> hT : tempList) {
			sqlT[i] = "insert into nblg_sjb(xmmc,xydm) select '"+hT.get("xmmc")+"' xmmc,xydm from view_njxyzybj group by xydm";
			i++;
		}
		dao.runBatch(sqlT);
		return new ActionForward("/nblg_xszz.do?method=data_zzsj&go=go", false);
	}

	/**
	 * ����������ҳ��
	 * knssq ----- ����������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		XszzNblgService service = new XszzNblgService();
		String sUserName = session.getAttribute("userName").toString();//SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();//SESSION�л�ȡ�û�����
		String userDep = session.getAttribute("userDep").toString();//SESSION�л�ȡ�û�����
		String xh = "";//ѧ��
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName : Base.chgNull(request.getParameter("xh"), "", 1);//�û�������ѧ����ֱ�ӻ�ȡ�û���
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getKnsxx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
			}
		}
		stuMap.put("xn", Base.currXn);
		request.setAttribute("sfksq", service.getKnsSqQx(sUserType, userDep,
				xh, Base.currNd));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knssq");
	}
	
	/**
	 * ����������������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knssqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		KnsModel model = new KnsModel();
		BeanUtils.copyProperties(model, xszzNblgActionForm);
		XszzNblgService service = new XszzNblgService();
		boolean bJg = service.saveKnsSqxx(model, request);// ������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getKnsxx(pkVal);// �õ���������ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);
			}
		}
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knssqSave");
	}

	/**
	 * �����������ҳ��
	 * knssqb ----- �����������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knssqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		KnsModel model = new KnsModel();
		BeanUtils.copyProperties(model, xszzNblgActionForm);
		stuMap = service.getKnsSqb(model,request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("knssqb");
	}

	/**
	 * ���������ҳ��
	 * knssh ----- ���������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "", 1));
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())){
			xszzNblgActionForm.setXn(Base.currXn);
		}
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delKnsxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			xszzNblgActionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, xszzNblgActionForm);
		List<HashMap<String, String>> topList = service.getKnsTit();
		List<String[]> resList = service.getKnsRes(queryModel,request);
		String xh = DealString.toGBK(xszzNblgActionForm.getXh());
		xszzNblgActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzNblgActionForm);
		commonAction.appendProperties(request, commenBean, false);//��REQUEST�д��ҳ����ص��б�
		
		xszzNblgActionForm.setMzpy(queryModel.getMzpy());
		xszzNblgActionForm.setXysh(queryModel.getXysh());
		xszzNblgActionForm.setXxsh(queryModel.getXxsh());
		
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", xszzNblgActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "nblg_kns");
		request.setAttribute("tableName", "view_nblg_kns");
		return mapping.findForward("knssh");
	}
	
	/**
	 * �������������
	 * knsplsh ----- �������������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsplsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userType = request.getSession().getAttribute("userType").toString();
		XszzNblgService service = new XszzNblgService();
		HashMap<String, String> rs = new HashMap<String, String>();
		String doType = Base.chgNull(request.getParameter("doType"), "", 1);
		
		String cbVal = Base.chgNull(request.getParameter("cbVal"), "!!splitOne!!", 1);
		String shType = Base.chgNull(request.getParameter("shType"), "", 1);
		String shjg = Base.chgNull(request.getParameter("shjg"), "", 1);
		
		if ("save".equalsIgnoreCase(doType)){
			service.modKnsxx(cbVal, shType, shjg, request);
			request.setAttribute("over", "over");
		}
		
		rs.put("cbVal", cbVal);
		rs.put("shType", shType);
		rs.put("shjg", shjg);
		request.setAttribute("userType", userType);
		request.setAttribute("rs", rs);
		return mapping.findForward("knsplsh");
	}
	
	/**
	 * ��������Ϣ����
	 * knsExp ----- ��������Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzNblgActionForm);
		service.getKnsExp(queryModel, response,request);
		return mapping.findForward("knsExp");
	}

	/**
	 * �����������ϸҳ��
	 * knsshOne ----- �����������ϸҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsxx(pkVal);
		xszzNblgActionForm.setMzpy(stuMap.get("mzpy"));
		xszzNblgActionForm.setXysh(stuMap.get("xysh"));
		xszzNblgActionForm.setXxsh(stuMap.get("xxsh"));
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(26));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsshOne");
	}

	/**
	 * ���������������Ϣ
	 * knsshSave ---- ���������������Ϣ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		KnsModel model = new KnsModel();
		BeanUtils.copyProperties(model, xszzNblgActionForm);
		boolean bJg = service.saveKnsShxx(model, request);// ���������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsxx(pkVal);
		xszzNblgActionForm.setMzpy(stuMap.get("mzpy"));
		xszzNblgActionForm.setXysh(stuMap.get("xysh"));
		xszzNblgActionForm.setXxsh(stuMap.get("xxsh"));
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(26));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsshSave");
	}
	
	/**
	 * ��ʱ���Ѳ�������ҳ��
	 * lsknbzsq ----- ��ʱ���Ѳ�������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsknbzsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		XszzNblgService service = new XszzNblgService();
		String sUserName = session.getAttribute("userName").toString();//SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();//SESSION�л�ȡ�û�����
		String userDep = session.getAttribute("userDep").toString();//SESSION�л�ȡ�û�����
		String xh = "";//ѧ��
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName : Base.chgNull(request.getParameter("xh"), "", 1);//�û�������ѧ����ֱ�ӻ�ȡ�û���
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getLsknbzxx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
			}
		}
		stuMap.put("xn", Base.currXn);
		request.setAttribute("sfksq", service.getLsknbzSqQx(sUserType, userDep,
				xh, Base.currNd));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("lsknbzsq");
	}
	
	/**
	 * ������ʱ���Ѳ���������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsknbzsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		LsknbzModel model = new LsknbzModel();
		BeanUtils.copyProperties(model, xszzNblgActionForm);
		XszzNblgService service = new XszzNblgService();
		boolean bJg = service.saveLsknbzSqxx(model, request);// ������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getLsknbzxx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);
			}
		}
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("lsknbzsqSave");
	}

	/**
	 * ��ʱ���Ѳ��������ҳ��
	 * lsknbzsqb ----- ��ʱ���Ѳ��������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsknbzsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		LsknbzModel model = new LsknbzModel();
		BeanUtils.copyProperties(model, xszzNblgActionForm);
		stuMap = service.getLsknbzSqb(model,request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("lsknbzsqb");
	}

	/**
	 * ��ʱ���Ѳ������ҳ��
	 * lsknbzsh ----- ��ʱ���Ѳ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsknbzsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "", 1));
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())){
			xszzNblgActionForm.setXn(Base.currXn);
		}
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delLsknbzxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modLsknbzxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "ͨ��", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modLsknbzxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "��ͨ��", request);
			queryModel.setGo("go");
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			xszzNblgActionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, xszzNblgActionForm);
		List<HashMap<String, String>> topList = service.getLsknbzTit();
		List<String[]> resList = service.getLsknbzRes(queryModel,request);
		String xh = DealString.toGBK(xszzNblgActionForm.getXh());
		xszzNblgActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzNblgActionForm);
		commonAction.appendProperties(request, commenBean, false);//��REQUEST�д��ҳ����ص��б�
		
		xszzNblgActionForm.setXysh(queryModel.getXysh());
		xszzNblgActionForm.setXxsh(queryModel.getXxsh());
		
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", xszzNblgActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "nblg_lsknbz");
		request.setAttribute("tableName", "view_nblg_lsknbz");
		return mapping.findForward("lsknbzsh");
	}
	
	/**
	 * ��ʱ���Ѳ�����Ϣ����
	 * lsknbzExp ----- ��ʱ���Ѳ�����Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsknbzExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzNblgActionForm);
		service.getLsknbzExp(queryModel, response,request);
		return mapping.findForward("lsknbzExp");
	}

	/**
	 * ��ʱ���Ѳ��������ϸҳ��
	 * lsknbzshOne ----- ��ʱ���Ѳ��������ϸҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsknbzshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getLsknbzxx(pkVal);
		xszzNblgActionForm.setXysh(stuMap.get("xysh"));
		xszzNblgActionForm.setXxsh(stuMap.get("xxsh"));
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("lsknbzshOne");
	}

	/**
	 * ������ʱ���Ѳ��������Ϣ
	 * lsknbzshSave ---- ������ʱ���Ѳ��������Ϣ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsknbzshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		LsknbzModel model = new LsknbzModel();
		BeanUtils.copyProperties(model, xszzNblgActionForm);
		boolean bJg = service.saveLsknbzShxx(model, request);// ���������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getLsknbzxx(pkVal);
		xszzNblgActionForm.setXysh(stuMap.get("xysh"));
		xszzNblgActionForm.setXxsh(stuMap.get("xxsh"));
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("lsknbzshSave");
	}
	
	/**
	 * ��ѧ������ҳ��
	 * zxjsq ----- ��ѧ������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		XszzNblgService service = new XszzNblgService();
		String sUserName = session.getAttribute("userName").toString();//SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();//SESSION�л�ȡ�û�����
		String userDep = session.getAttribute("userDep").toString();//SESSION�л�ȡ�û�����
		String xh = "";//ѧ��
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName : Base.chgNull(request.getParameter("xh"), "", 1);//�û�������ѧ����ֱ�ӻ�ȡ�û���
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getZxjxx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
			}
		}
		stuMap.put("xn", Base.currXn);
		request.setAttribute("sfksq", service.getZxjSqQx(sUserType, userDep,
				xh, Base.currNd));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("zxjsq");
	}
	
	/**
	 * ������ѧ��������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxjsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		ZxjModel model = new ZxjModel();
		BeanUtils.copyProperties(model, xszzNblgActionForm);
		XszzNblgService service = new XszzNblgService();
		boolean bJg = service.saveZxjSqxx(model, request);// ������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getZxjxx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);
			}
		}
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("zxjsqSave");
	}

	/**
	 * ��ѧ�������ҳ��
	 * zxjsqb ----- ��ѧ�������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxjsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		ZxjModel model = new ZxjModel();
		BeanUtils.copyProperties(model, xszzNblgActionForm);
		stuMap = service.getZxjSqb(model,request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("zxjsqb");
	}

	/**
	 * ��ѧ�����ҳ��
	 * zxjsh ----- ��ѧ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "", 1));
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())){
			xszzNblgActionForm.setXn(Base.currXn);
		}
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delZxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modZxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "ͨ��", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modZxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "��ͨ��", request);
			queryModel.setGo("go");
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			xszzNblgActionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, xszzNblgActionForm);
		List<HashMap<String, String>> topList = service.getZxjTit();
		List<String[]> resList = service.getZxjRes(queryModel,request);
		String xh = DealString.toGBK(xszzNblgActionForm.getXh());
		xszzNblgActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzNblgActionForm);
		commonAction.appendProperties(request, commenBean, false);//��REQUEST�д��ҳ����ص��б�
		
		xszzNblgActionForm.setXysh(queryModel.getXysh());
		xszzNblgActionForm.setXxsh(queryModel.getXxsh());
		
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", xszzNblgActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "nblg_zxj");
		request.setAttribute("tableName", "view_nblg_zxj");
		return mapping.findForward("zxjsh");
	}
	
	/**
	 * ��ѧ����Ϣ����
	 * zxjExp ----- ��ѧ����Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxjExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzNblgActionForm);
		service.getZxjExp(queryModel, response,request);
		return mapping.findForward("zxjExp");
	}

	/**
	 * ��ѧ�������ϸҳ��
	 * zxjshOne ----- ��ѧ�������ϸҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxjshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getZxjxx(pkVal);
		xszzNblgActionForm.setXysh(stuMap.get("xysh"));
		xszzNblgActionForm.setXxsh(stuMap.get("xxsh"));
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("zxjshOne");
	}

	/**
	 * ������ѧ�������Ϣ
	 * zxjshSave ---- ������ѧ�������Ϣ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxjshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		ZxjModel model = new ZxjModel();
		BeanUtils.copyProperties(model, xszzNblgActionForm);
		boolean bJg = service.saveZxjShxx(model, request);// ���������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getZxjxx(pkVal);
		xszzNblgActionForm.setXysh(stuMap.get("xysh"));
		xszzNblgActionForm.setXxsh(stuMap.get("xxsh"));
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("zxjshSave");
	}
	
	/**
	 * ������ѧ������ҳ��
	 * gjzxjsq ----- ������ѧ������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		XszzNblgService service = new XszzNblgService();
		String sUserName = session.getAttribute("userName").toString();//SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();//SESSION�л�ȡ�û�����
		String userDep = session.getAttribute("userDep").toString();//SESSION�л�ȡ�û�����
		String xh = "";//ѧ��
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName : Base.chgNull(request.getParameter("xh"), "", 1);//�û�������ѧ����ֱ�ӻ�ȡ�û���
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getGjzxjxx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
			}
		}
		stuMap.put("xn", Base.currXn);
		request.setAttribute("sfksq", service.getGjzxjSqQx(sUserType, userDep,
				xh, Base.currNd));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxjsq");
	}
	
	/**
	 * ���������ѧ��������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		GjzxjModel model = new GjzxjModel();
		BeanUtils.copyProperties(model, xszzNblgActionForm);
		XszzNblgService service = new XszzNblgService();
		boolean bJg = service.saveGjzxjSqxx(model, request);// ������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getGjzxjxx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);
			}
		}
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxjsqSave");
	}

	/**
	 * ������ѧ�������ҳ��
	 * gjzxjsqb ----- ������ѧ�������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		GjzxjModel model = new GjzxjModel();
		BeanUtils.copyProperties(model, xszzNblgActionForm);
		stuMap = service.getGjzxjSqb(model,request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("gjzxjsqb");
	}

	/**
	 * ������ѧ�����ҳ��
	 * gjzxjsh ----- ������ѧ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "", 1));
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())){
			xszzNblgActionForm.setXn(Base.currXn);
		}
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delGjzxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjzxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "ͨ��", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjzxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "��ͨ��", request);
			queryModel.setGo("go");
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			xszzNblgActionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, xszzNblgActionForm);
		List<HashMap<String, String>> topList = service.getGjzxjTit();
		List<String[]> resList = service.getGjzxjRes(queryModel,request);
		String xh = DealString.toGBK(xszzNblgActionForm.getXh());
		xszzNblgActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzNblgActionForm);
		commonAction.appendProperties(request, commenBean, false);//��REQUEST�д��ҳ����ص��б�
		
		xszzNblgActionForm.setXysh(queryModel.getXysh());
		xszzNblgActionForm.setXxsh(queryModel.getXxsh());
		
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", xszzNblgActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "nblg_gjzxj");
		request.setAttribute("tableName", "view_nblg_gjzxj");
		return mapping.findForward("gjzxjsh");
	}
	
	/**
	 * ������ѧ����Ϣ����
	 * gjzxjExp ----- ������ѧ����Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzNblgActionForm);
		service.getGjzxjExp(queryModel, response,request);
		return mapping.findForward("gjzxjExp");
	}

	/**
	 * ������ѧ�������ϸҳ��
	 * gjzxjshOne ----- ������ѧ�������ϸҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxjxx(pkVal);
		xszzNblgActionForm.setXysh(stuMap.get("xysh"));
		xszzNblgActionForm.setXxsh(stuMap.get("xxsh"));
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxjshOne");
	}

	/**
	 * ���������ѧ�������Ϣ
	 * gjzxjshSave ---- ���������ѧ�������Ϣ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		GjzxjModel model = new GjzxjModel();
		BeanUtils.copyProperties(model, xszzNblgActionForm);
		boolean bJg = service.saveGjzxjShxx(model, request);// ���������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxjxx(pkVal);
		xszzNblgActionForm.setXysh(stuMap.get("xysh"));
		xszzNblgActionForm.setXxsh(stuMap.get("xxsh"));
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxjshSave");
	}
	
	/**
	 * ������־��ѧ������ҳ��
	 * gjlzjxjsq ----- ������־��ѧ������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		XszzNblgService service = new XszzNblgService();
		String sUserName = session.getAttribute("userName").toString();//SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();//SESSION�л�ȡ�û�����
		String userDep = session.getAttribute("userDep").toString();//SESSION�л�ȡ�û�����
		String xh = "";//ѧ��
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName : Base.chgNull(request.getParameter("xh"), "", 1);//�û�������ѧ����ֱ�ӻ�ȡ�û���
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getGjlzjxjxx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
			}
		}
		stuMap.put("xn", Base.currXn);
		request.setAttribute("sfksq", service.getGjlzjxjSqQx(sUserType, userDep,
				xh, Base.currNd));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjlzjxjsq");
	}
	
	/**
	 * ���������־��ѧ��������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		GjlzjxjModel model = new GjlzjxjModel();
		BeanUtils.copyProperties(model, xszzNblgActionForm);
		XszzNblgService service = new XszzNblgService();
		boolean bJg = service.saveGjlzjxjSqxx(model, request);// ������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getGjlzjxjxx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);
			}
		}
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjlzjxjsqSave");
	}

	/**
	 * ������־��ѧ�������ҳ��
	 * gjlzjxjsqb ----- ������־��ѧ�������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		GjlzjxjModel model = new GjlzjxjModel();
		BeanUtils.copyProperties(model, xszzNblgActionForm);
		stuMap = service.getGjlzjxjSqb(model,request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("gjlzjxjsqb");
	}

	/**
	 * ������־��ѧ�����ҳ��
	 * gjlzjxjsh ----- ������־��ѧ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "", 1));
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())){
			xszzNblgActionForm.setXn(Base.currXn);
		}
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delGjlzjxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjlzjxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "ͨ��", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjlzjxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "��ͨ��", request);
			queryModel.setGo("go");
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			xszzNblgActionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, xszzNblgActionForm);
		List<HashMap<String, String>> topList = service.getGjlzjxjTit();
		List<String[]> resList = service.getGjlzjxjRes(queryModel,request);
		String xh = DealString.toGBK(xszzNblgActionForm.getXh());
		xszzNblgActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzNblgActionForm);
		commonAction.appendProperties(request, commenBean, false);//��REQUEST�д��ҳ����ص��б�
		
		xszzNblgActionForm.setXysh(queryModel.getXysh());
		xszzNblgActionForm.setXxsh(queryModel.getXxsh());
		
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", xszzNblgActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "nblg_gjlzjxj");
		request.setAttribute("tableName", "view_nblg_gjlzjxj");
		return mapping.findForward("gjlzjxjsh");
	}
	
	/**
	 * ������־��ѧ����Ϣ����
	 * gjlzjxjExp ----- ������־��ѧ����Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzNblgActionForm);
		service.getGjlzjxjExp(queryModel, response,request);
		return mapping.findForward("gjlzjxjExp");
	}

	/**
	 * ������־��ѧ�������ϸҳ��
	 * gjlzjxjshOne ----- ������־��ѧ�������ϸҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjlzjxjxx(pkVal);
		xszzNblgActionForm.setXysh(stuMap.get("xysh"));
		xszzNblgActionForm.setXxsh(stuMap.get("xxsh"));
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjlzjxjshOne");
	}

	/**
	 * ���������־��ѧ�������Ϣ
	 * gjlzjxjshSave ---- ���������־��ѧ�������Ϣ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		GjlzjxjModel model = new GjlzjxjModel();
		BeanUtils.copyProperties(model, xszzNblgActionForm);
		boolean bJg = service.saveGjlzjxjShxx(model, request);// ���������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjlzjxjxx(pkVal);
		xszzNblgActionForm.setXysh(stuMap.get("xysh"));
		xszzNblgActionForm.setXxsh(stuMap.get("xxsh"));
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjlzjxjshSave");
	}

	/**
	 * �ʺ������ѧ������ҳ��
	 * chzxjsq ----- �ʺ������ѧ������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward chzxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xxdm = StandardOperation.getXxdm();
		HttpSession session = request.getSession();
		XszzNblgService service = new XszzNblgService();
		XsxxglService xsxxglService = new XsxxglService();
		
		String sUserName = session.getAttribute("userName").toString();//SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();//SESSION�л�ȡ�û�����
		String userDep = session.getAttribute("userDep").toString();//SESSION�л�ȡ�û�����
		String xh = "";//ѧ��
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName : Base.chgNull(request.getParameter("xh"), "", 1);//�û�������ѧ����ֱ�ӻ�ȡ�û���
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getChzxjxx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_NBTYZYJSXY)){
				//������һ��ͥ���ȡ��ͥ���������е���Ϣ
				stuMap.putAll(xsxxglService.getStuJtxx(xh));
				selectPageDataByOne(request, "xszz_n05_xsjtqkdcb", "view_xszz_n05_xsjtqkdcb", xh);
				stuMap.putAll((HashMap<String, String>)request.getAttribute("rs"));
			}
		}
		stuMap.put("xn", Base.currXn);
		request.setAttribute("sfksq", service.getChzxjSqQx(sUserType, 
				                                           userDep,
				                                           xh, 
				                                           Base.currNd));
		if(Globals.XXDM_NBTYZYJSXY.equalsIgnoreCase(xxdm)){
			//������һ������ʱ������
			request.setAttribute("sfksq", "1");
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("chzxjsq");
	}
	
	/**
	 * ����ʺ������ѧ��������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward chzxjsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XsxxglService xsxxglService = new XsxxglService();		
		ChzxjModel model = new ChzxjModel();
		BeanUtils.copyProperties(model, xszzNblgActionForm);
		XszzNblgService service = new XszzNblgService();
		boolean bJg = service.saveChzxjSqxx(model, request);// ������Ϣ
		String xxdm = StandardOperation.getXxdm();
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getChzxjxx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_NBTYZYJSXY)){
				//������һ��ͥ���ȡ��ͥ���������е���Ϣ
				stuMap.putAll(xsxxglService.getStuJtxx(xh));
				selectPageDataByOne(request, "xszz_n05_xsjtqkdcb", "view_xszz_n05_xsjtqkdcb", xh);
				stuMap.putAll((HashMap<String, String>)request.getAttribute("rs"));
			}
		}
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("chzxjsqSave");
	}

	/**
	 * �ʺ������ѧ�������ҳ��
	 * chzxjsqb ----- �ʺ������ѧ�������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward chzxjsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglService xsxxglService = new XsxxglService();
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		ChzxjModel model = new ChzxjModel();
		BeanUtils.copyProperties(model, xszzNblgActionForm);
		stuMap = service.getChzxjSqb(model,request);
		String xxdm = StandardOperation.getXxdm();
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBTYZYJSXY)){			
			//������һ��ͥ���ȡ��ͥ���������е���Ϣ
			String xh = stuMap.get("xh");
			if(!Base.isNull(xh)){
			stuMap.putAll(xsxxglService.getStuJtxx(xh));
			selectPageDataByOne(request, "xszz_n05_xsjtqkdcb", "view_xszz_n05_xsjtqkdcb", xh);
			stuMap.putAll((HashMap<String, String>)request.getAttribute("rs"));
			}
		}
		request.setAttribute("rs", stuMap);
		return mapping.findForward("chzxjsqb");
	}

	/**
	 * �ʺ������ѧ�����ҳ��
	 * chzxjsh ----- �ʺ������ѧ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward chzxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "", 1));
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())){
			xszzNblgActionForm.setXn(Base.currXn);
		}
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delChzxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modChzxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "ͨ��", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modChzxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "��ͨ��", request);
			queryModel.setGo("go");
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			xszzNblgActionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, xszzNblgActionForm);
		List<HashMap<String, String>> topList = service.getChzxjTit();
		List<String[]> resList = service.getChzxjRes(queryModel,request);
		String xh = DealString.toGBK(xszzNblgActionForm.getXh());
		xszzNblgActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzNblgActionForm);
		commonAction.appendProperties(request, commenBean, false);//��REQUEST�д��ҳ����ص��б�
		
		xszzNblgActionForm.setXysh(queryModel.getXysh());
		xszzNblgActionForm.setXxsh(queryModel.getXxsh());
		
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", xszzNblgActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "nblg_chzxj");
		request.setAttribute("tableName", "view_nblg_chzxj");
		return mapping.findForward("chzxjsh");
	}
	
	/**
	 * �ʺ������ѧ����Ϣ����
	 * chzxjExp ----- �ʺ������ѧ����Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward chzxjExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzNblgActionForm);
		service.getChzxjExp(queryModel, response,request);
		return mapping.findForward("chzxjExp");
	}
	
	
	
	/**
	 * �ʺ������ѧ��ӡ����
	 * printChcszxj ----- �ʺ������ѧ��ӡ����
	 * author ������
	 * data 2010-07-15
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward printChcszxj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		xszzNblgActionForm.setXn(Base.currXn);
		XszzNblgService service=new XszzNblgService();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull("go", "", 1));
		queryModel.setIsQuery(Base.chgNull("isQuery", "", 1));
		BeanUtils.copyProperties(queryModel, xszzNblgActionForm);
		
		
		String modelPath =servlet.getServletContext().getRealPath("") + "/print/xszz/nbty_chcszxj.xls";
		response.setContentType("application/vnd.ms-excel");
		
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
		service.printChcszxj(queryModel,request, wwb);
		return mapping.findForward("");
	}

	/**
	 * �ʺ������ѧ�������ϸҳ��
	 * chzxjshOne ----- �ʺ������ѧ�������ϸҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward chzxjshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XsxxglService xsxxglService = new XsxxglService();
		XszzNblgService service = new XszzNblgService();
		
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getChzxjxx(pkVal);
		xszzNblgActionForm.setXysh(stuMap.get("xysh"));
		xszzNblgActionForm.setXxsh(stuMap.get("xxsh"));
		String xxdm = StandardOperation.getXxdm();
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBTYZYJSXY)){			
			//������һ��ͥ���ȡ��ͥ���������е���Ϣ
			String xh = stuMap.get("xh");
			stuMap.putAll(xsxxglService.getStuJtxx(xh));
			selectPageDataByOne(request, "xszz_n05_xsjtqkdcb", "view_xszz_n05_xsjtqkdcb", xh);
			stuMap.putAll((HashMap<String, String>)request.getAttribute("rs"));
		}
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("chzxjshOne");
	}

	/**
	 * ����ʺ������ѧ�������Ϣ
	 * chzxjshSave ---- ����ʺ������ѧ�������Ϣ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward chzxjshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XsxxglService xsxxglService = new XsxxglService();
		XszzNblgService service = new XszzNblgService();
		ChzxjModel model = new ChzxjModel();
		BeanUtils.copyProperties(model, xszzNblgActionForm);
		boolean bJg = service.saveChzxjShxx(model, request);// ���������Ϣ
		String xxdm = StandardOperation.getXxdm();
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getChzxjxx(pkVal);
		xszzNblgActionForm.setXysh(stuMap.get("xysh"));
		xszzNblgActionForm.setXxsh(stuMap.get("xxsh"));
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBTYZYJSXY)){			
			//������һ��ͥ���ȡ��ͥ���������е���Ϣ
			stuMap.putAll(xsxxglService.getStuJtxx(xh));
			selectPageDataByOne(request, "xszz_n05_xsjtqkdcb", "view_xszz_n05_xsjtqkdcb", xh);
			stuMap.putAll((HashMap<String, String>)request.getAttribute("rs"));
		}
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("chzxjshSave");
	}
	
	/**
	 * �����ܻ��У��У����ѧ������ҳ��
	 * zxszxjsq ----- �����ܻ��У��У����ѧ������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxszxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		XszzNblgService service = new XszzNblgService();
		XsxxglService xsxxglService = new XsxxglService();
		String sUserName = session.getAttribute("userName").toString();//SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();//SESSION�л�ȡ�û�����
		String userDep = session.getAttribute("userDep").toString();//SESSION�л�ȡ�û�����
		String xh = "";//ѧ��
		String xxdm = StandardOperation.getXxdm();
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName : Base.chgNull(request.getParameter("xh"), "", 1);//�û�������ѧ����ֱ�ӻ�ȡ�û���
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getZxszxjxx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_NBTYZYJSXY)){			
				//������һ��ͥ���ȡ��ͥ���������е���Ϣ
				stuMap.putAll(xsxxglService.getStuJtxx(xh));
				selectPageDataByOne(request, "xszz_n05_xsjtqkdcb", "view_xszz_n05_xsjtqkdcb", xh);
				stuMap.putAll((HashMap<String, String>)request.getAttribute("rs"));
			}
		}
		stuMap.put("xn", Base.currXn);
		request.setAttribute("sfksq", service.getZxszxjSqQx(sUserType, 
				                                            userDep, 
				                                            xh, 
				                                            Base.currNd));
		if(Globals.XXDM_NBTYZYJSXY.equalsIgnoreCase(xxdm)){
			//������һ������ʱ������
			request.setAttribute("sfksq", "1");
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("zxszxjsq");
	}
	
	/**
	 * ��������ܻ��У��У����ѧ��������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxszxjsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XsxxglService xsxxglService = new XsxxglService();
		ZxszxjModel model = new ZxszxjModel();
		BeanUtils.copyProperties(model, xszzNblgActionForm);
		XszzNblgService service = new XszzNblgService();
		boolean bJg = service.saveZxszxjSqxx(model, request);// ������Ϣ
		String xxdm = StandardOperation.getXxdm();
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getZxszxjxx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_NBTYZYJSXY)){			
				//������һ��ͥ���ȡ��ͥ���������е���Ϣ
				stuMap.putAll(xsxxglService.getStuJtxx(xh));
				selectPageDataByOne(request, "xszz_n05_xsjtqkdcb", "view_xszz_n05_xsjtqkdcb", xh);
				stuMap.putAll((HashMap<String, String>)request.getAttribute("rs"));
			}
		}
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("zxszxjsqSave");
	}

	/**
	 * �����ܻ��У��У����ѧ�������ҳ��
	 * zxszxjsqb ----- �����ܻ��У��У����ѧ�������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxszxjsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglService xsxxglService = new XsxxglService();
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		ZxszxjModel model = new ZxszxjModel();
		BeanUtils.copyProperties(model, xszzNblgActionForm);
		stuMap = service.getZxszxjSqb(model,request);
		String xxdm = StandardOperation.getXxdm();
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBTYZYJSXY)){			
			//������һ��ͥ���ȡ��ͥ���������е���Ϣ
			String xh = stuMap.get("xh");
			if(!Base.isNull(xh)){
			stuMap.putAll(xsxxglService.getStuJtxx(xh));
			selectPageDataByOne(request, "xszz_n05_xsjtqkdcb", "view_xszz_n05_xsjtqkdcb", xh);
			stuMap.putAll((HashMap<String, String>)request.getAttribute("rs"));
			}
		}
		
		request.setAttribute("rs", stuMap);
		return mapping.findForward("zxszxjsqb");
	}

	/**
	 * �����ܻ���ѧ��ӡ����
	 * printCszhzxj ----- �����ܻ���ѧ��ӡ����
	 * author ������
	 * data 2010-07-15
	 */
	public ActionForward printCszhzxj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		xszzNblgActionForm.setXn(Base.currXn);
		XszzNblgService service=new XszzNblgService();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull("go", "", 1));
		queryModel.setIsQuery(Base.chgNull("isQuery", "", 1));
		BeanUtils.copyProperties(queryModel, xszzNblgActionForm);
		
		String modelPath =servlet.getServletContext().getRealPath("") + "/print/xszz/nbty_cszhzxj.xls";
		response.setContentType("application/vnd.ms-excel");
		
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
		service.printCszhzxj(queryModel,request, wwb);
		return mapping.findForward("");
	}
	
	/**
	 * �����ܻ��У��У����ѧ�����ҳ��
	 * zxszxjsh ----- �����ܻ��У��У����ѧ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxszxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "", 1));
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())){
			xszzNblgActionForm.setXn(Base.currXn);
		}
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delZxszxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modZxszxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "ͨ��", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modZxszxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "��ͨ��", request);
			queryModel.setGo("go");
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			xszzNblgActionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, xszzNblgActionForm);
		List<HashMap<String, String>> topList = service.getZxszxjTit();
		List<String[]> resList = service.getZxszxjRes(queryModel,request);
		String xh = DealString.toGBK(xszzNblgActionForm.getXh());
		xszzNblgActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzNblgActionForm);
		commonAction.appendProperties(request, commenBean, false);//��REQUEST�д��ҳ����ص��б�
		
		xszzNblgActionForm.setXysh(queryModel.getXysh());
		xszzNblgActionForm.setXxsh(queryModel.getXxsh());
		
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", xszzNblgActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "nblg_zxszxj");
		request.setAttribute("tableName", "view_nblg_zxszxj");
		return mapping.findForward("zxszxjsh");
	}
	
	/**
	 * �����ܻ��У��У����ѧ����Ϣ����
	 * zxszxjExp ----- �����ܻ��У��У����ѧ����Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxszxjExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzNblgActionForm);
		service.getZxszxjExp(queryModel, response,request);
		return mapping.findForward("zxszxjExp");
	}

	/**
	 * �����ܻ��У��У����ѧ�������ϸҳ��
	 * zxszxjshOne ----- �����ܻ��У��У����ѧ�������ϸҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxszxjshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XsxxglService xsxxglService = new XsxxglService();
		XszzNblgService service = new XszzNblgService();
		
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getZxszxjxx(pkVal);
		xszzNblgActionForm.setXysh(stuMap.get("xysh"));
		xszzNblgActionForm.setXxsh(stuMap.get("xxsh"));
		String xxdm = StandardOperation.getXxdm();
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBTYZYJSXY)){			
			//������һ��ͥ���ȡ��ͥ���������е���Ϣ
			String xh = stuMap.get("xh");
			stuMap.putAll(xsxxglService.getStuJtxx(xh));
			selectPageDataByOne(request, "xszz_n05_xsjtqkdcb", "view_xszz_n05_xsjtqkdcb", xh);
			stuMap.putAll((HashMap<String, String>)request.getAttribute("rs"));
		}
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("zxszxjshOne");
	}

	/**
	 * ��������ܻ��У��У����ѧ�������Ϣ
	 * zxszxjshSave ---- ��������ܻ��У��У����ѧ�������Ϣ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxszxjshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XsxxglService xsxxglService = new XsxxglService();
		XszzNblgService service = new XszzNblgService();
		ZxszxjModel model = new ZxszxjModel();
		BeanUtils.copyProperties(model, xszzNblgActionForm);
		boolean bJg = service.saveZxszxjShxx(model, request);// ���������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		String xxdm = StandardOperation.getXxdm();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getZxszxjxx(pkVal);
		xszzNblgActionForm.setXysh(stuMap.get("xysh"));
		xszzNblgActionForm.setXxsh(stuMap.get("xxsh"));
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBTYZYJSXY)){			
			//������һ��ͥ���ȡ��ͥ���������е���Ϣ
			stuMap.putAll(xsxxglService.getStuJtxx(xh));
			selectPageDataByOne(request, "xszz_n05_xsjtqkdcb", "view_xszz_n05_xsjtqkdcb", xh);
			stuMap.putAll((HashMap<String, String>)request.getAttribute("rs"));
		}
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("zxszxjshSave");
	}
	
	/**
	 * ������ѧ��������ҳ��
	 * gjzxdksq ----- ������ѧ��������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		XszzNblgService service = new XszzNblgService();
		XsxxglService xsxxglService = new XsxxglService();
		String sUserName = session.getAttribute("userName").toString();//SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();//SESSION�л�ȡ�û�����
		String userDep = session.getAttribute("userDep").toString();//SESSION�л�ȡ�û�����
		String xh = "";//ѧ��
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName : Base.chgNull(request.getParameter("xh"), "", 1);//�û�������ѧ����ֱ�ӻ�ȡ�û���
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getGjzxdkxx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				if (stuMap.size() != 0 && "������".equalsIgnoreCase(stuMap.get("kndj"))){
					stuMap = new HashMap<String, String>();
					stuMap.put("notKns", "notKns");
				}
			}			
		}
		stuMap.put("xn", Base.currXn);
		request.setAttribute("sfksq", service.getGjzxdkSqQx(sUserType, userDep,
				xh, Base.currNd));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxdksq");
	}
	
	/**
	 * ���������ѧ����������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdksqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XsxxglService xsxxglService = new XsxxglService();
		GjzxdkModel model = new GjzxdkModel();
		BeanUtils.copyProperties(model, xszzNblgActionForm);
		XszzNblgService service = new XszzNblgService();
		boolean bJg = service.saveGjzxdkSqxx(model, request);// ������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getGjzxdkxx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				if (stuMap.size() != 0 && "������".equalsIgnoreCase(stuMap.get("kndj"))){
					stuMap = new HashMap<String, String>();
					stuMap.put("notKns", "notKns");
				}
				stuMap.put("xn", Base.currXn);
			}			
		}
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxdksqSave");
	}

	/**
	 * ������ѧ���������ҳ��
	 * gjzxdksqb ----- ������ѧ���������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdksqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		GjzxdkModel model = new GjzxdkModel();
		BeanUtils.copyProperties(model, xszzNblgActionForm);
		stuMap = service.getGjzxdkSqb(model,request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("gjzxdksqb");
	}

	/**
	 * ������ѧ�������ҳ��
	 * gjzxdksh ----- ������ѧ�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdksh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "", 1));
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())){
			xszzNblgActionForm.setXn(Base.currXn);
		}
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delGjzxdkxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjzxdkxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "ͨ��", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjzxdkxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "��ͨ��", request);
			queryModel.setGo("go");
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			xszzNblgActionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, xszzNblgActionForm);
		List<HashMap<String, String>> topList = service.getGjzxdkTit();
		List<String[]> resList = service.getGjzxdkRes(queryModel,request);
		String xh = DealString.toGBK(xszzNblgActionForm.getXh());
		xszzNblgActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzNblgActionForm);
		commonAction.appendProperties(request, commenBean, false);//��REQUEST�д��ҳ����ص��б�
		
		xszzNblgActionForm.setXysh(queryModel.getXysh());
		xszzNblgActionForm.setXxsh(queryModel.getXxsh());
		
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", xszzNblgActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "nblg_gjzxdk");
		request.setAttribute("tableName", "view_nblg_gjzxdk");
		return mapping.findForward("gjzxdksh");
	}
	
	/**
	 * ������ѧ������Ϣ����
	 * gjzxdkExp ----- ������ѧ������Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdkExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzNblgActionForm);
		service.getGjzxdkExp(queryModel, response,request);
		return mapping.findForward("gjzxdkExp");
	}

	/**
	 * ������ѧ���������ϸҳ��
	 * gjzxdkshOne ----- ������ѧ���������ϸҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdkshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxdkxx(pkVal);
		xszzNblgActionForm.setXysh(stuMap.get("xysh"));
		xszzNblgActionForm.setXxsh(stuMap.get("xxsh"));
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxdkshOne");
	}

	/**
	 * ���������ѧ���������Ϣ
	 * gjzxdkshSave ---- ���������ѧ���������Ϣ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdkshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzNblgActionForm xszzNblgActionForm = (XszzNblgActionForm)form;
		XszzNblgService service = new XszzNblgService();
		GjzxdkModel model = new GjzxdkModel();
		BeanUtils.copyProperties(model, xszzNblgActionForm);
		boolean bJg = service.saveGjzxdkShxx(model, request);// ���������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxdkxx(pkVal);
		xszzNblgActionForm.setXysh(stuMap.get("xysh"));
		xszzNblgActionForm.setXxsh(stuMap.get("xxsh"));
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxdkshSave");
	}
}
