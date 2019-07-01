package xgxt.pjpy.ahjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

import common.Globals;
import common.GlobalsVariable;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ���ս�����ҵѧԺ��������Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-05-30</p>
 */
public class PjpyAhjgAction extends DispatchAction {
	
	private String xydm = "";//ѧԺ����
	private String zydm = "";//רҵ����
	private String nj = "";//�꼶
	private CommonAction comAct = null;

	/**
	 * ��Ϣά��ѧ���ɼ���ѯĬ��ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjpyahjgxscjDefault(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgService service = new PjpyAhjgService();
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		
		//ѧ���û�ֻ�ܲ�ѯ�Լ���ѧ���ɼ�
		String uType = request.getSession().getAttribute("userType").toString();
		if (GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(uType)
				|| GlobalsVariable.XTDM_STU.equalsIgnoreCase(uType)) {
			//TODO ��ʱ������Ȩ�����ڸ�ҳ�棬��ʱ���ٰ�ѧ���ɼ���ѯ����ȥ
			request.setAttribute("message", "����Ȩ���ʸ�ҳ��!");
			return new ActionForward("/prompt.do",false);
		}
		
		
		
		if (StringUtils.isNull(ahjgForm.getXn())) {
			ahjgForm.setXn(Base.getJxjsqxn());
		}
		if (StringUtils.isNull(ahjgForm.getXq())) {
			ahjgForm.setXq(Base.getJxjsqxq());
		}
		
		String xxdm = StandardOperation.getXxdm();
		String jwflag = service.getJwFlag();//��ѯ����汾,1Ϊѧ��,0Ϊ����,����Ϊ������˾����
		if (xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_SHCBYSGDZKXX)) {//�����ǹ���,ͨ��WEBSERVICEͬ��ѧ���ɼ�����
			return new ActionForward("/pjpy_xnmz_xscjwh.do", false);
		}
//		if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm)
//				|| Globals.XXDM_ZJJJZYJSXY.equalsIgnoreCase(xxdm)
//				|| Globals.XXDM_WHLGDXHXXY.equalsIgnoreCase(xxdm)
//				|| Globals.XXDM_ZJSYZYXY.equalsIgnoreCase(xxdm)) {// ������ѧ��
//			return new ActionForward("/pjpy_zgms_cjwh.do", false);
//		}
		if ("1".equalsIgnoreCase(jwflag)) {// ������ѧ��
			return new ActionForward("/pjpy_zgms_cjwh.do", false);
		}
		String userType = request.getSession().getAttribute("userType") != null ? request
				.getSession().getAttribute("userType").toString()
				: "";
				if (Fdypd.isBzr(request.getSession().getAttribute("userName").toString(), "")) {
					userType = "bzr";
				}
		if ("xy".equalsIgnoreCase(userType)) {
			xydm=request.getSession().getAttribute("userDep") != null ? request
					.getSession().getAttribute("userDep").toString()
					: "";
			ahjgForm.setXydm(xydm);
		} else {
			xydm = ahjgForm.getXydm();
		}
		zydm = ahjgForm.getZydm();
		nj = ahjgForm.getNj();
		String tableName = "";
		String realTable = "";
		if (!StringUtils.isNull(ahjgForm.getCjlx())) {
			if ("cjb".equalsIgnoreCase(ahjgForm.getCjlx())) {
				tableName = "view_cjb";
				realTable = "cjb";
			} else if ("djksb".equalsIgnoreCase(ahjgForm.getCjlx())) {
				tableName = "view_xsdjksb";
				realTable = "xsdjksb";
				request.setAttribute("djksmcList", service.getDjksmc());
			}
		}
		
		// -------------2010/5/24 edit by luojw --------------
		if (Globals.XXDM_GZDX.equalsIgnoreCase(Base.xxdm)) {
			List<HashMap<String, String>> kcxzList = service.getKsxzList();
			if (kcxzList != null && kcxzList.size() > 0) {
				request.setAttribute("kcxzList", kcxzList);
			}
		}
		// -------------end --------------
		request.setAttribute("path", "pjpy_ahjg_xscjdefault.do");
		FormModleCommon.commonRequestSet(request);
		appendProperties(request, xydm, zydm, nj);//����ҳ���б�
		request.setAttribute("tableName", tableName);//��ѯ��Ϊ��ͼ��
		request.setAttribute("realTable", realTable);//����
		return mapping.findForward("pjpyahjgxscjdefault");
	}

	/**
	 * ��Ϣά��ѧ���ɼ���ѯҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjpyahjgxscjQry(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		String xh = DealString.toGBK(ahjgForm.getXh());
		String xm = DealString.toGBK(ahjgForm.getXm());
		PjpyAhjgXscjQryModel pjpyahjgxscjqryModel = new PjpyAhjgXscjQryModel();//ѧ���ɼ���ѯMODEL
		List<String[]> listRs = new ArrayList<String[]>();//ѧ���ɼ���ѯ���
		List<HashMap<String, String>> listTopTr = new ArrayList<HashMap<String,String>>();//ѧ���ɼ���ѯ��ͷ
		PjpyAhjgService service = new PjpyAhjgService();
		String userType = request.getSession().getAttribute("userType") != null ? request
				.getSession().getAttribute("userType").toString()
				: "";
		String userName = request.getSession().getAttribute("userName") != null ? request
				.getSession().getAttribute("userName").toString(): "";
		
		if (Fdypd.isBzr(request.getSession().getAttribute("userName").toString(), "")) {
				userType = "bzr";
		}
		if ("xy".equalsIgnoreCase(userType)) {
			xydm=request.getSession().getAttribute("userDep") != null ? request
					.getSession().getAttribute("userDep").toString()
					: "";
					ahjgForm.setXydm(xydm);
		} else {
			xydm = ahjgForm.getXydm();
		}
		BeanUtils.copyProperties(pjpyahjgxscjqryModel, ahjgForm);
		if (!StringUtils.isNull(ahjgForm.getCjlx()) && StringUtils.isEqual(ahjgForm.getCjlx(), "cjb")) {
			if (Globals.XXDM_YCSFXY.equalsIgnoreCase(Base.xxdm)) {
				listTopTr = service.getSearchTitle(7);//����7�����ѧ���ɼ���ѯ��ͷ
			}else if (Globals.XXDM_AHZYJSXY.equalsIgnoreCase(Base.xxdm)) {
				listTopTr = service.getSearchTitle(8);//����7�����ѧ���ɼ���ѯ��ͷ
			}else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(Base.xxdm)) {
				listTopTr = service.getSearchTitle(9);//����7�����ѧ���ɼ���ѯ��ͷ
			} else if (Globals.XXDM_GZDX.equalsIgnoreCase(Base.xxdm)) {// ���ݴ�ѧ
				listTopTr = service.getSearchTitle(10);
			} else {
				listTopTr = service.getSearchTitle(1);// ����1�����ѧ���ɼ���ѯ��ͷ
			}
			listRs = service.getPjpyXscjResult(pjpyahjgxscjqryModel, ahjgForm,userType,userName);
			int count = service.getPjpyXscjResultNum(pjpyahjgxscjqryModel,userType,userName);
			ahjgForm.getPages().setMaxRecord(Integer.parseInt(String.valueOf(count)));//�������ļ�¼��
		} else {
			listTopTr = service.getSearchTitle(6);//����6�����ѧ���ȼ����Բ�ѯ��ͷ
			listRs = service.getPjpyDjkscjResult(pjpyahjgxscjqryModel, ahjgForm);
			int count = service.getPjpyDjkscjResultNum(pjpyahjgxscjqryModel);
			ahjgForm.getPages().setMaxRecord(Integer.parseInt(String.valueOf(count)));//�������ļ�¼��
		}
		
		zydm = ahjgForm.getZydm();
		nj = ahjgForm.getNj();
		String tableName = "";
		String realTable = "";
		if (!StringUtils.isNull(ahjgForm.getCjlx())) {
			if ("cjb".equalsIgnoreCase(ahjgForm.getCjlx())) {
				tableName = "view_cjb";
				realTable = "cjb";
				if (Globals.XXDM_GZDX.equalsIgnoreCase(Base.xxdm)) {// ���ݴ�ѧ
					tableName = "view_gzdx_cjb";
				}
			} else if ("djksb".equalsIgnoreCase(ahjgForm.getCjlx())) {
				tableName = "view_xsdjksb";
				realTable = "xsdjksb";
				request.setAttribute("djksmcList", service.getDjksmc());
			}
		}
		
		// -------------2010/5/24 edit by luojw --------------
		if (Globals.XXDM_GZDX.equalsIgnoreCase(Base.xxdm)) {
			List<HashMap<String, String>> kcxzList = service.getKsxzList();
			if (kcxzList != null && kcxzList.size() > 0) {
				String[] kcxz = pjpyahjgxscjqryModel.getKcxz();
				String checked_Kcxz = "";
				if (kcxz != null && kcxz.length > 0) {
					for (int i = 0; i < kcxz.length; i++) {
						checked_Kcxz += kcxz[i] + "!!@@!!";
					}
				}
				request.setAttribute("checked_Kcxz", checked_Kcxz);
				request.setAttribute("kcxzList", kcxzList);
			}
		}
		// -------------end --------------
		
		request.setAttribute("tableName", tableName);//��ѯ��Ϊ��ͼ��
		request.setAttribute("realTable", realTable);//����
		appendProperties(request, xydm, zydm, nj);
		ahjgForm.setXh(xh);
		ahjgForm.setXm(xm);
		request.setAttribute("topTr", listTopTr);
		request.setAttribute("rs", listRs);
		request.setAttribute("rsNum", listRs != null ? listRs.size() : 0);
		return mapping.findForward("pjpyahjgxscjdefault");
	}
	
	/**
	 * ѧ���ɼ�,�ȼ����Գɼ����������ͬ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ahjgcjTb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		PjpyAhjgService service = new PjpyAhjgService();
		String cjlx = request.getParameter("cjlx");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		boolean bFlag = service.cjTb(cjlx, xn, xq);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		String tableName = "";
		String realTable = "";
		if (!StringUtils.isNull(ahjgForm.getCjlx())) {
			if ("cjb".equalsIgnoreCase(ahjgForm.getCjlx())) {
				tableName = "view_cjb";
				realTable = "cjb";
			} else if ("djksb".equalsIgnoreCase(ahjgForm.getCjlx())) {
				tableName = "view_xsdjksb";
				realTable = "xsdjksb";
			}
		}
		request.setAttribute("tableName", tableName);//��ѯ��Ϊ��ͼ��
		request.setAttribute("realTable", realTable);//����
		appendProperties(request, xydm, zydm, nj);//����ҳ���б�
		return mapping.findForward("pjpyahjgxscjdefault");
	}
	
	/**
	 * ��������Ĭ��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjpyahjgrysqDefault(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<HashMap<String, String>> rysqList = new ArrayList<HashMap<String,String>>();
		PjpyAhjgService service = new PjpyAhjgService();
		rysqList = service.getRysqList(1);//�õ����������б���Ϣ
		request.setAttribute("list", rysqList);
		return mapping.findForward("pjpyahjgrysqdefault");
	}
	
	/**
	 * �Ƚ���������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjpyahjgxsjtSq(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgService service = new PjpyAhjgService();
		List<HashMap<String, String>> titleList = service.getRysqList(4);
		String defaultTitle = request.getParameter("titName");
		if(defaultTitle == null || defaultTitle.trim().equals("")){
			defaultTitle = "xjbj";
		}
		appentProperties1(request, xydm, zydm, nj);
		request.setAttribute("defaultTitle", defaultTitle);
		request.setAttribute("titleList", titleList);
		return mapping.findForward("pjpyahjgxsjtsq");
	}
	
	/**
	 * �Ƚ��༶����,����֤�����Ƿ��ظ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjpyahjgxjbjSq(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		XjBjSqModel xjbjSqModel = new XjBjSqModel();//�Ƚ��༶����MODEL
		BeanUtils.copyProperties(xjbjSqModel, ahjgForm);
		PjpyAhjgService service = new PjpyAhjgService();
		List<HashMap<String, String>> titleList = service.getRysqList(4);
		String defaultTitle = request.getParameter("titName");
		if(defaultTitle == null || defaultTitle.trim().equals("")){
			defaultTitle = "xjbj";
		}
		boolean bChk = service.chkDataByXjbj(xjbjSqModel);//��֤�����Ƿ��ظ�
		if (bChk) {//����
			request.setAttribute("result", "haved");
		}else {//������
			boolean bFlag = service.saveXjBjInfo(xjbjSqModel, request);//��������
			if (bFlag) {
				request.setAttribute("result", "true");
			}else{
				request.setAttribute("result", "false");
			}//end if
		}//end if
		
		appentProperties1(request, xydm, zydm, nj);
		request.setAttribute("defaultTitle", defaultTitle);
		request.setAttribute("titleList", titleList);
		ahjgForm.setBzr(DealString.toGBK(ahjgForm.getBzr()));
		ahjgForm.setBzxm(DealString.toGBK(ahjgForm.getBzxm()));
		ahjgForm.setZysj(DealString.toGBK(ahjgForm.getZysj()));
		return mapping.findForward("pjpyahjgxsjtsq");
	}
	
	/**
	 * ���÷���:��REQUEST�д��ҳ����Ҫ���ص�LIST����
	 * @param request
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @param xq
	 * @throws Exception
	 */
	public void appendProperties(HttpServletRequest request, String xydm,
			String zydm, String nj) throws Exception {
		String xy = "";
		String zy = "";
		String njLocal = nj;
		xy = xy==null ? "": xydm; 
		zy = zy==null ? "": zydm; 
		njLocal = nj==null ? "": nj;
		xy = StringUtils.isNull(xy) ? "" : xy;
		zy = StringUtils.isNull(zy) ? "" : zy;
		nj = StringUtils.isNull(nj) ? "" : nj;
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		/*String realTable = "pjpy_bjbklb";
		String tableName = "pjpy_bjbklb";*/
		String userType = request.getSession().getAttribute("userType").toString();
		if (Fdypd.isBzr(request.getSession().getAttribute("userName").toString(), "")) {
			userType = "bzr";
		}
		request.setAttribute("writeAble", "yes");//�ж��û���дȨ
		/*request.setAttribute("tableName", tableName);//��ѯ��Ϊ��ͼ��
		request.setAttribute("realTable", realTable);//����*/
		request.setAttribute("xqList", Base.getXqList());//ѧ���б�
		request.setAttribute("xnList", Base.getXnndList());//ѧ���б�
		request.setAttribute("njList", Base.getNjList());//�꼶�б�
		request.setAttribute("xyList", Base.getXyList());//ѧԺ�б�
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//רҵ�б�
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//�༶�б�
		request.setAttribute("userType", userType);//�û�����
		if (request.getSession().getAttribute("fdyQx").toString() != null
				&& "true".equalsIgnoreCase(request.getSession().getAttribute("fdyQx")
						.toString())) {
			// ����Ա��¼
			request.setAttribute("bjList", Fdypd.getFdybjList(request.getSession().getAttribute("userName").toString()));// ���Ͱ༶�б�
			request.setAttribute("zyList", Fdypd.getFdyZyList(request.getSession().getAttribute("userName").toString()));// ���Ͱ༶�б�
		}
	}
	
	public void appentProperties1(HttpServletRequest request, String xydm,
			String zydm, String nj) throws Exception {
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = request.getSession().getAttribute("userType").toString();
		String zyKey = "";
		String bjKey = "!!!!";
		if("xy".equalsIgnoreCase(userType)){
			zyKey = userDep;
			bjKey = zyKey+"!!"+""+"!!"+"";
		}
		request.setAttribute("userType", userType);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));
	}

	/**
	 * �༶������Ĭ��ҳ��
	 * bjbkl ---- �༶������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjbklDefault(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			ahjgForm.setXydm(xydm);
		}
		nj = ahjgForm.getNj();
		zydm = ahjgForm.getZydm();
		appendProperties(request, xydm, zydm, nj);
		
		request.setAttribute("tableName", "pjpy_bjbklb");//��ѯ��Ϊ��ͼ��
		request.setAttribute("realTable", "pjpy_bjbklb");//����
		return mapping.findForward("bjbkldefault");
	}
	
	/**
	 * ��ٳ�����Ĭ��ҳ��
	 * zccql ---- ��ٳ����� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zccqlDefault(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			ahjgForm.setXydm(xydm);
		}
		nj = ahjgForm.getNj();
		zydm = ahjgForm.getZydm();
		appendProperties(request, xydm, zydm, nj);
		//appendProperties(request, xydm, zydm, nj);
		request.setAttribute("tableName", "pjpy_bjzccqlb");//��ѯ��Ϊ��ͼ��
		request.setAttribute("realTable", "pjpy_bjzccqlb");//����
		return mapping.findForward("zccqldefault");
	}

	/**
	 * �༶�����ʲ�ѯ
	 * bjbklqry ---- �༶�����ʲ�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjbklQry(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			ahjgForm.setXydm(xydm);
		}
		nj = ahjgForm.getNj();
		zydm = ahjgForm.getZydm();
		appendProperties(request, xydm, zydm, nj);
		PjpyAhjgXscjQryModel pjpyahjgxscjqryModel = new PjpyAhjgXscjQryModel();//�༶�����ʲ�ѯMODEL
		BeanUtils.copyProperties(pjpyahjgxscjqryModel, ahjgForm);
		PjpyAhjgService service = new PjpyAhjgService();
		List<HashMap<String, String>> titList = service.getSearchTitle(2);//�༶�����ʲ�ѯ��ͷ
		List<String[]> resList = service.getBjbklQryResult(pjpyahjgxscjqryModel);//��ѯ���
		
		//appendProperties(request, xydm, zydm, nj);
		request.setAttribute("topTr", titList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		request.setAttribute("tableName", "pjpy_bjbklb");//��ѯ��Ϊ��ͼ��
		request.setAttribute("realTable", "pjpy_bjbklb");//����
		return mapping.findForward("bjbkldefault");
	}

	/**
	 * �༶����������
	 * bjbkladd ---- �༶����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjbklAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		appendProperties(request, xydm, zydm, nj);
		String sPk = DealString.toGBK(request.getParameter("pkValue"));
		request.setAttribute("pkValue", sPk);
		return mapping.findForward("bjbkladd");
	}
	
	/**
	 * �༶�����ʱ���
	 * bjbklsave ---- �༶�����ʱ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  bjbklSave(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		//String bjdm = request.getParameter("bjdm");
		BjbklSaveModel bjbklSaveModel = new BjbklSaveModel();//�༶�����ʱ���MODEL
		BeanUtils.copyProperties(bjbklSaveModel, ahjgForm);
		//bjbklSaveModel.setBjdm(bjdm);
		PjpyAhjgService service = new PjpyAhjgService();
		boolean bChk = service.chkBjbkl(bjbklSaveModel);//����ǰ��������Ƿ��ظ�
		if (bChk) {//�ظ�
			request.setAttribute("inserted", "haved");
		} else {//���ظ�
			boolean bFlag = service.saveBjbkl(bjbklSaveModel, request);//��������
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			}else {
				request.setAttribute("inserted", "no");
			}//end if
		}//end if
		ahjgForm.setBzxm(DealString.toGBK(ahjgForm.getBzxm()));
		ahjgForm.setBzr(DealString.toGBK(ahjgForm.getBzr()));
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("bjbkladd");
	}
	
	/**
	 * �༶�������޸���Ϣ��ʾ
	 * bjbklmodi ---- �༶�������޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjbklModi(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sPk = DealString.toGBK(request.getParameter("pkValue"));
//		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		PjpyAhjgService service = new PjpyAhjgService();
		HashMap<String, String> resMap = service.getBjbklByPk(sPk);
		String bjbkl = resMap.get("bjbkl");
		bjbkl = !StringUtils.isNull(bjbkl) ? bjbkl.substring(0, bjbkl.length()-1): "";
		resMap.put("bjbkl", bjbkl);
		request.setAttribute("rs", resMap);
		
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("pkValue", sPk);
		return mapping.findForward("bjbklmodi");
	}
	
	/**
	 * �༶�������޸���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjbklModi1(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sPk = DealString.toGBK(request.getParameter("pkValue"));
		String bzxm = DealString.toGBK(request.getParameter("bzxm"));
		String bzr = DealString.toGBK(request.getParameter("bzr"));
		String xsrs = request.getParameter("xsrs");
		String bjbkl = request.getParameter("bjbkl");
		PjpyAhjgService service = new PjpyAhjgService();
		BjbklSaveModel bjbklSaveModel = new BjbklSaveModel();
		bjbklSaveModel.setBzxm(bzxm);
		bjbklSaveModel.setBzr(bzr);
		bjbklSaveModel.setXsrs(xsrs);
		bjbklSaveModel.setBjbkl(bjbkl);
		boolean bFlag = service.updateBjbkl(sPk, bjbklSaveModel, request);//��������
		if (bFlag) {//�ɹ�
			request.setAttribute("inserted", "yes");
		}else {//ʧ��
			request.setAttribute("inserted", "no");
		}//end if
		
		HashMap<String, String> resMap = service.getBjbklByPk(sPk);//���»�ȡ��Ϣ
		request.setAttribute("pkValue", sPk);
		request.setAttribute("rs", resMap);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("bjbklmodi");
	}
	
	/**
	 * �༶����������ɾ��
	 * bjbkldel ---- �༶����������ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjbklDel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			ahjgForm.setXydm(xydm);
		}
		nj = ahjgForm.getNj();
		zydm = ahjgForm.getZydm();
		//appendProperties(request, xydm, zydm, nj);
		String[] pkList = ahjgForm.getCbv();
		PjpyAhjgService service = new PjpyAhjgService();
		String sRes = service.delBjbkl(pkList);
		if (!StringUtils.isNull(sRes)) {
			request.setAttribute("result", "view");
		}else {
			request.setAttribute("result", sRes);
		}//end if
		
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("tableName", "pjpy_bjbklb");//��ѯ��Ϊ��ͼ��
		request.setAttribute("realTable", "pjpy_bjbklb");//����
		return mapping.findForward("bjbkldefault");
	}
	
	/**
	 * ��ٳ����ʲ�ѯ
	 * zccqlqry ---- ��ٳ����ʲ�ѯ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zccqlQry(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			ahjgForm.setXydm(xydm);
		}
		nj = ahjgForm.getNj();
		zydm = ahjgForm.getZydm();
		//appendProperties(request, xydm, zydm, nj);
		ZccqlQueryModel zccqlQryModel = new ZccqlQueryModel();//��ٳ����ʲ�ѯMODEL
		BeanUtils.copyProperties(zccqlQryModel, ahjgForm);
		PjpyAhjgService service = new PjpyAhjgService();
		List<HashMap<String, String>> titList = service.getSearchTitle(3);//��ѯ��ͷ
		List<String[]> resList = service.getZccqlResult(zccqlQryModel);//��ѯ���
		
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("tableName", "pjpy_bjzccqlb");//��ѯ��Ϊ��ͼ��
		request.setAttribute("realTable", "pjpy_bjzccqlb");//����
		request.setAttribute("topTr", titList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//
		return mapping.findForward("zccqldefault");
	}

	/**
	 * ��ٳ���������
	 * zccqladd ---- ��ٳ��������� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zccqlAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		appendProperties(request, xydm, zydm, nj);
		String sPk = DealString.toGBK(request.getParameter("pkValue"));
		request.setAttribute("pkValue", sPk);
		return mapping.findForward("zccqladd");
	}
	
	/**
	 * ��ٳ����ʱ���
	 * zccqlsave ---- ��ٳ����ʱ��� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zccqlSave(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		ZccqlSaveModel zccqlSaveModel = new ZccqlSaveModel();//��ٳ��ڱ���MODEL
		PjpyAhjgService service = new PjpyAhjgService();
		BeanUtils.copyProperties(zccqlSaveModel, ahjgForm);
		boolean bChk = service.chkZccql(zccqlSaveModel);//��������Ƿ��ظ�
		if (bChk) {
			request.setAttribute("inserted", "haved");//����
		}else {
			boolean bFlag = service.saveZccql(zccqlSaveModel, request);//���ݱ���
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			}else {
				request.setAttribute("inserted", "no");
			}//end if
		}//end if
		
		ahjgForm.setBzr(DealString.toGBK(ahjgForm.getBzr()));
		ahjgForm.setBzxm(DealString.toGBK(ahjgForm.getBzxm()));
		appendProperties(request, xydm, zydm, nj);
		String sPk = DealString.toGBK(request.getParameter("pkValue"));
		request.setAttribute("pkValue", sPk);
		return mapping.findForward("zccqladd");
	}
	
	/**
	 * ��ٳ�������Ϣ��ʾ
	 * zccqlview ---- ��ٳ�������Ϣ��ʾ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zccqlView(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sPk = DealString.toGBK(request.getParameter("pkValue"));
		PjpyAhjgService service = new PjpyAhjgService();
		HashMap<String, String> resMap = service.getZccqlByPk(sPk);
		String zccql = resMap.get("zccql");
		zccql = !StringUtils.isNull(zccql) ? zccql.substring(0, zccql.length() - 1) : "";
		resMap.put("zccql", zccql);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("pkValue", sPk);
		request.setAttribute("rs", resMap);
		return mapping.findForward("zccqlview");
	}
	
	/**
	 * ��ٳ�������Ϣ�޸�
	 * zccqlmodi ---- ��ٳ�������Ϣ�޸� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zccqlModi(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sPk = DealString.toGBK(request.getParameter("pkValue"));
		ZccqlSaveModel zccqlSaveModel = new ZccqlSaveModel();
		String bzxm = DealString.toGBK(request.getParameter("bzxm"));
		String bzr = DealString.toGBK(request.getParameter("bzr"));
		String xsrs = request.getParameter("xsrs");
		String zccql = request.getParameter("zccql");
		zccqlSaveModel.setBzr(bzr);
		zccqlSaveModel.setBzxm(bzxm);
		zccqlSaveModel.setXsrs(xsrs);
		zccqlSaveModel.setZccql(zccql);
		PjpyAhjgService service = new PjpyAhjgService();
		boolean bFlag = service.updateZccql(sPk, zccqlSaveModel, request);//�޸�����
		if (bFlag) {//�ɹ�
			request.setAttribute("inserted", "yes");
		}else {//ʧ��
			request.setAttribute("inserted", "no");
		}
		
		HashMap<String, String> resMap = service.getZccqlByPk(sPk);//���»�ȡ��Ϣ
		request.setAttribute("pkValue", sPk);
		request.setAttribute("rs", resMap);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zccqlview");
	}

	/**
	 * ��ٳ�����ɾ��
	 * zccqldel ---- ��ٳ�����ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zccqlDel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			ahjgForm.setXydm(xydm);
		}
		nj = ahjgForm.getNj();
		zydm = ahjgForm.getZydm();
		//appendProperties(request, xydm, zydm, nj);
		String[] pkList = ahjgForm.getCbv();
		PjpyAhjgService service = new PjpyAhjgService();
		String sRes = service.delZccql(pkList);//����ɾ��
		if (!StringUtils.isNull(sRes)) {
			request.setAttribute("result", "view");
		}else {
			request.setAttribute("result", sRes);
		}//end if
		
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("tableName", "pjpy_bjzccqlb");//��ѯ��Ϊ��ͼ��
		request.setAttribute("realTable", "pjpy_bjzccqlb");//����
		return mapping.findForward("zccqldefault");
	}


	/**
	 * �����ƺ����Ĭ��ҳ��
	 * rychshdefault ---- �����ƺ����Ĭ��ҳ�� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychShDefault(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<HashMap<String, String>> rysqList = new ArrayList<HashMap<String,String>>();
		PjpyAhjgService service = new PjpyAhjgService();
		rysqList = service.getRysqList(1);//�õ����������б���Ϣ
		request.setAttribute("list", rysqList);
		return mapping.findForward("rychshdefault");
	}
	
	/**
	 * �Ƚ�������˲�ѯĬ��ҳ��
	 * xjjtshdefault ---- �Ƚ�������˲�ѯĬ��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjjtShDefault(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? ""
				: session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep") == null ? ""
				: session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			ahjgForm.setXydm(xydm);
		} else {
			xydm = ahjgForm.getXydm();
		}
		nj = ahjgForm.getNj();
		zydm = ahjgForm.getZydm();
		List<HashMap<String, String>> shxmList = new ArrayList<HashMap<String,String>>();
		PjpyAhjgService service = new PjpyAhjgService();
		shxmList = service.getRysqList(4);
		request.setAttribute("shxmList", shxmList);//�����Ŀ�б�
		ahjgForm.setShxm("xjbj");
		appendProperties(request, xydm, zydm, nj);
		comAct = new CommonAction();
		comAct.appendFdybjList(request);
		request.setAttribute("ag", ahjgForm);
		return mapping.findForward("xjjtshdefault");
	}
	
	/**
	 * �Ƚ�������˲�ѯ
	 * xsjtqry ---- �Ƚ�������˲�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsjtQry(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		XjBjQryModel xjbjQryModel = new XjBjQryModel();//�Ƚ��༶��ѯMODEL
		
		HttpSession session    = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String sUserType        = session.getAttribute("userType").toString();//�û�����
		String sIsFdy         = session.getAttribute("isFdy").toString();//����Ա
		String userName       = session.getAttribute("userName").toString();
		String xydm = "";
		//�û���ѧԺʱ
		if("xy".equalsIgnoreCase(sUserType)){
			xydm = bmdm;
			ahjgForm.setXydm(xydm);
		}  else {
			xydm = ahjgForm.getXydm();
		}
		nj = ahjgForm.getNj();
		zydm = ahjgForm.getZydm();
		BeanUtils.copyProperties(xjbjQryModel, ahjgForm);
		xjbjQryModel.setShxm("xjbj");
		List<HashMap<String, String>> shxmList = new ArrayList<HashMap<String,String>>();
		PjpyAhjgService service = new PjpyAhjgService();
		List<HashMap<String, String>> topList = service.getXjBjTitle(xjbjQryModel, sUserType, sIsFdy);
		List<String[]> resList = service.getXjBjResult(xjbjQryModel, sUserType, sIsFdy, userName);

		/*if (StringUtils.isEqual(sShxm, "xjbj")) {
			//request.setAttribute("xjbj", "�Ƚ��༶����ΪȫУ�༶����6%");
		}else {
			//request.setAttribute("xjbj", "�����������ΪȫУ������3%");
		}*/
		String sBjs = service.getBjzs();//�༶���ȱ���
		sBjs = "�Ƚ��༶�����Ȳ�Ӧ����" + sBjs +"��!";
		shxmList = service.getRysqList(2);
		appendProperties(request, xydm, zydm, nj);
		comAct = new CommonAction();
		comAct.appendFdybjList(request);
		ahjgForm.setShxm("xjbj");
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		request.setAttribute("shxmList", shxmList);
		request.setAttribute("bjzs", sBjs);
		request.setAttribute("ag", ahjgForm);
		return mapping.findForward("xjjtshdefault");
	}
	
	/**
	 * �Ƚ��������
	 * xjjtsh ---- �Ƚ�������� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjjtSh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sRes  = request.getParameter("param1");
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		XjjtShModel xjjtShModel = new XjjtShModel();
		BeanUtils.copyProperties(xjjtShModel, ahjgForm);
		HttpSession session    = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String sUserType        = session.getAttribute("userType").toString();//�û�����
		String sIsFdy         = session.getAttribute("isFdy").toString();//����Ա
		String xydm = "";
		//�û���ѧԺʱ
		if("xy".equalsIgnoreCase(sUserType)){
			xydm = bmdm;
			ahjgForm.setXydm(xydm);
		} 
		PjpyAhjgService service = new PjpyAhjgService();
		xjjtShModel.setShxm("xjbj");
		String res = service.submitShResult(sRes, sUserType, sIsFdy, xjjtShModel, request);//�ύ��˽��
		if (StringUtils.isNull(res)) {
			request.setAttribute("inserted", "view");
		} else {
			request.setAttribute("failinfo", String.format("��ʾ�����е� %1$s �����ݲ���ʧ�ܣ�ԭ�����ѳ����Ȱ༶����", res));
			request.setAttribute("inserted", "no");
		}
		List<HashMap<String, String>> shxmList = new ArrayList<HashMap<String,String>>();
		shxmList = service.getRysqList(2);
		ahjgForm.setShxm("xjbj");
		
		appendProperties(request, xydm, zydm, nj);
		comAct = new CommonAction();
		comAct.appendFdybjList(request);
		//request.setAttribute("result", "view");
		request.setAttribute("shxmList", shxmList);
		request.setAttribute("ag", ahjgForm);
		return mapping.findForward("xjjtshdefault");
	}
	
	/**
	 * �Ƚ����嵥�������ʾ
	 * xjjtshview ---- �Ƚ����嵥�������ʾ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjjtshView(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		HttpSession session    = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String sUserType        = session.getAttribute("userType").toString();//�û�����
		String sIsFdy         = session.getAttribute("isFdy").toString();//����Ա
		String xydm = "";
		//�û���ѧԺʱ
		if("xy".equalsIgnoreCase(sUserType)){
			xydm = bmdm;
			ahjgForm.setXydm(xydm);
		} 
		String sPkValue = DealString.toGBK(request.getParameter("pkValue"));
		PjpyAhjgService service = new PjpyAhjgService();
		HashMap<String, String> resList = service.getXjBjResultByOne(sPkValue, sUserType, sIsFdy);
		List<HashMap<String, String>> shxmList = new ArrayList<HashMap<String,String>>();
		shxmList = service.getChkList(3);
		String sBjCfrs = service.getBjcfRs(resList.get("bjdm"), resList.get("xn"));//��ȡѧ���ڰ༶Υ������
		
		request.setAttribute("bjcfrs", sBjCfrs);
		request.setAttribute("rs", resList);
		request.setAttribute("shxmList", shxmList);
		ahjgForm.setShxm(resList.get("sh"));
		ahjgForm.setShyj(resList.get("yj"));
		request.setAttribute("pkValue", sPkValue);
		return mapping.findForward("xjjtshview");
	}
	
	/**
	 * �Ƚ����嵥����ˡ�
	 * shjgbyone ---- �Ƚ����嵥����� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shjgByOne(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sPkValue = DealString.toGBK(request.getParameter("pkValue"));
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		XjjtShModel xjjtshModel = new XjjtShModel();
		BeanUtils.copyProperties(xjjtshModel, ahjgForm);
		xjjtshModel.setPkValue(sPkValue);
		HttpSession session    = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String sUserType        = session.getAttribute("userType").toString();//�û�����
		String sIsFdy         = session.getAttribute("isFdy").toString();//����Ա
		String xydm = "";
		//�û���ѧԺʱ
		if("xy".equalsIgnoreCase(sUserType)){
			xydm = bmdm;
			ahjgForm.setXydm(xydm);
		} 
		PjpyAhjgService service = new PjpyAhjgService();
		
		int tg = service.getXjbjShjg(request);//��ͨ���༶��
		String sBjs = service.getBjzs();//�༶���ȱ���
		int xzs = StringUtils.isNull(sBjs) ? 0 : Integer.parseInt(sBjs);
		
		if (!StringUtils.isNull(xjjtshModel.getShxm()) && StringUtils.isEqual(DealString.toGBK(xjjtshModel.getShxm()), "ͨ��")) {
			if ((xzs != 0) && (tg>=xzs)) {
				request.setAttribute("inserted", "cb");
			} else {
				boolean bFlag = service.saveXjjtOne(xjjtshModel, sUserType, sIsFdy, request);//������
				if (bFlag){//�ɹ�
					request.setAttribute("inserted", "yes");
				}else {//ʧ��
					request.setAttribute("inserted", "no");
				}
			}
		} else {
			boolean bFlag = service.saveXjjtOne(xjjtshModel, sUserType, sIsFdy, request);//������
			if (bFlag){//�ɹ�
				request.setAttribute("inserted", "yes");
			}else {//ʧ��
				request.setAttribute("inserted", "no");
			}
		}
		
		
		HashMap<String, String> resList = service.getXjBjResultByOne(sPkValue, sUserType, sIsFdy);
		List<HashMap<String, String>> shxmList = new ArrayList<HashMap<String,String>>();
		shxmList = service.getChkList(3);
		String sBjCfrs = service.getBjcfRs(resList.get("bjdm"), resList.get("xn"));//��ȡѧ���ڰ༶Υ������
		request.setAttribute("bjcfrs", sBjCfrs);
		
		request.setAttribute("rs", resList);
		request.setAttribute("shxmList", shxmList);
		ahjgForm.setShyj(DealString.toGBK(ahjgForm.getShyj()));
		request.setAttribute("pkValue", sPkValue);
		return mapping.findForward("xjjtshview");
	}

	/**
	 * �����ƺŽ����ѯĬ��ҳ��
	 * rychjgcxmr ---- �����ƺŽ����ѯĬ�� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychjgCxMr(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<HashMap<String, String>> rysqList = new ArrayList<HashMap<String,String>>();
		PjpyAhjgService service = new PjpyAhjgService();
		rysqList = service.getRysqList(1);//�õ����������б���Ϣ
		request.setAttribute("list", rysqList);
		return mapping.findForward("rychjgcxmr");
	}
	
	/**
	 * �Ƚ��༶�����ѯĬ��ҳ��
	 * xjjtjgcx ---- �Ƚ��༶�����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjjtJgCx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		List<HashMap<String, String>> shxmList = new ArrayList<HashMap<String,String>>();
		PjpyAhjgService service = new PjpyAhjgService();
		shxmList = service.getRysqList(4);
		request.setAttribute("shxmList", shxmList);//�����Ŀ�б�
		HttpSession session    = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String sUserType        = session.getAttribute("userType").toString();//�û�����
//		String isFdy         = session.getAttribute("isFdy").toString();//����Ա
		String xydm = "";
		//�û���ѧԺʱ
		if("xy".equalsIgnoreCase(sUserType)){
			xydm = bmdm;
			ahjgForm.setXydm(xydm);
		} 
		ahjgForm.setShxm("xjbj");
		appendProperties(request, xydm, zydm, nj);
		comAct = new CommonAction();
		comAct.appendFdybjList(request);
		request.setAttribute("realTable", "pjpy_xjbjandwmsqb");//����
		request.setAttribute("tableName", "pjpy_xjbjandwmsqb");//��ͼ�� 
		request.setAttribute("ag", ahjgForm);
		return mapping.findForward("xjjtjgcx");
	}
	
	/**
	 * �Ƚ��༶��ѯ���
	 * xjbjcxjg ---- �Ƚ��༶��ѯ��� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjCxJg(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		XjBjQryModel xjbjqryModel = new XjBjQryModel();
		PjpyAhjgService service = new PjpyAhjgService();
		HttpSession session    = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String sUserType        = session.getAttribute("userType").toString();//�û�����
		String sIsFdy         = session.getAttribute("isFdy").toString();//����Ա
		String userName = session.getAttribute("userName") == null ? ""
				: session.getAttribute("userName").toString();
		String xydm = "";
		//�û���ѧԺʱ
		if("xy".equalsIgnoreCase(sUserType)){
			xydm = bmdm;
			ahjgForm.setXydm(xydm);
		} 
		BeanUtils.copyProperties(xjbjqryModel, ahjgForm);
		xjbjqryModel.setShxm("xjbj");
		List<String[]> resList = service.xjbjCxJg(xjbjqryModel, sIsFdy, userName);//��ѯ���
		List<HashMap<String, String>> topList = service.xjbjJgCxBt();//��ѯ��ͷ
		List<HashMap<String, String>> shxmList = service.getRysqList(4);
		request.setAttribute("shxmList", shxmList);//�����Ŀ�б�
		ahjgForm.setShxm("xjbj");
		appendProperties(request, xydm, zydm, nj);
		comAct = new CommonAction();
		comAct.appendFdybjList(request);
		request.setAttribute("ag", ahjgForm);
		request.setAttribute("realTable", "pjpy_xjbjandwmsqb");//����
		request.setAttribute("tableName", "pjpy_xjbjandwmsqb");//��ͼ��
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//��¼��
		return mapping.findForward("xjjtjgcx");
	}
	
	/**
	 * ������Ϣ��ʾ
	 * modiview ---- �޸���ʾ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjModiView(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sPk = DealString.toGBK(request.getParameter("pkValue"));
		PjpyAhjgService service = new PjpyAhjgService();
		HashMap<String, String> resMap = service.xjbjXxByPk(sPk);//ͨ��������ȡ��ذ༶��Ϣ
		
		request.setAttribute("rs", resMap);
		request.setAttribute("pkValue", sPk);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("xjbjmodiview");
	}
	
	/**
	 * �Ƚ��༶�����޸�
	 * xjbjmodi ---- �Ƚ��༶�޸� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjModi(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String sPk = DealString.toGBK(request.getParameter("pkValue"));
		PjpyAhjgService service = new PjpyAhjgService();
		XjBjSqModel xjbjsqModel = new XjBjSqModel();
		xjbjsqModel.setBzr(DealString.toGBK(request.getParameter("bzr")));
		xjbjsqModel.setBzxm(DealString.toGBK(request.getParameter("bzxm")));
		xjbjsqModel.setXsrs(request.getParameter("xsrs"));
		xjbjsqModel.setZysj(DealString.toGBK(request.getParameter("zysj")));
		boolean bFlag = service.bcxjbjJg(xjbjsqModel, sPk, request);//�޸Ľ��
		HashMap<String, String> resMap = service.xjbjXxByPk(sPk);//ͨ��������ȡ��ذ༶��Ϣ
		if (bFlag) {//�ɹ�
			request.setAttribute("inserted", "yes");
		}else {//ʧ��
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("rs", resMap);
		request.setAttribute("pkValue", sPk);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("xjbjmodiview");
	}
	
	/**
	 * �Ƚ��༶����ɾ��
	 * xjbjsqdel ---- �Ƚ��༶����ɾ�� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjsqDel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		String[] pkList = ahjgForm.getCbv();//�����б�
		PjpyAhjgService service = new PjpyAhjgService();
		String sRes = service.delXjbjXx(pkList);//����ɾ��
		if (!StringUtils.isNull(sRes)) {
			request.setAttribute("result", "view");
		}else {
			request.setAttribute("result", sRes);
		}//end if
		ahjgForm.setShxm("xjbj");
		List<HashMap<String, String>> shxmList = service.getRysqList(4);
		request.setAttribute("shxmList", shxmList);//�����Ŀ�б�
		appendProperties(request, xydm, zydm, nj);
		comAct = new CommonAction();
		comAct.appendFdybjList(request);
		request.setAttribute("realTable", "pjpy_xjbjandwmsqb");//����
		request.setAttribute("tableName", "pjpy_xjbjandwmsqb");//��ͼ��
		request.setAttribute("ag", ahjgForm);
		return mapping.findForward("xjjtjgcx");
	}
	
	/**
	 * �Ƚ��������ȷ����
	 * xjjtpbfbb ---- �Ƚ��������ȷ���� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	/*public ActionForward xjjtPbfbb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgService service = new PjpyAhjgService();
		List<String[]> resList = service.getBjzs();//���
		List<HashMap<String, String>> topList = service.getSearchTitle(4);//��ͷ
		request.setAttribute("rs", resList);
		request.setAttribute("topTr", topList);
		request.setAttribute("jxjsqxn", Base.currXn);//��ǰѧ��
		return mapping.findForward("xjjtpbfbb");
	}*/
	
	/**
	 * �Ƚ��������ȷ����
	 * xjgrfbb ---- �Ƚ��������ȷ���� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjgrFbb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgService service = new PjpyAhjgService();
		List<HashMap<String, String>> topList = service.getSearchTitle(5); //��ͷ
		List<String[]> resList = service.getBjzsByGr();//���
		String[] jxjSqxnnd = service.getJxjsqxn();//��ѧ������ѧ�����
		request.setAttribute("xjgrsqxn", jxjSqxnnd != null ? jxjSqxnnd[0] : "");//��ǰѧ��
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		return mapping.findForward("xjgrfbb");
	}
	
	/**
	 * �Ƚ��������Ĭ��ҳ��
	 * xjgrshdefault ---- �Ƚ��������Ĭ��ҳ�� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjgrShDefault(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		PjpyAhjgService service = new PjpyAhjgService();
		HttpSession session    = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String sUserType        = session.getAttribute("userType").toString();//�û�����
//		String sIsFdy         = session.getAttribute("isFdy").toString();//����Ա
		String xydm = "";
		//�û���ѧԺʱ
		if("xy".equalsIgnoreCase(sUserType)){
			xydm = bmdm;
			ahjgForm.setXydm(xydm);
		} 
		List<HashMap<String, String>> rychList = service.getRychList();
//		String[] jxjSqxnnd = service.getJxjsqxn();//��ѧ������ѧ�����
		/*ahjgForm.setXn(jxjSqxnnd != null ? jxjSqxnnd[0] : "");//��ǰѧ��
		ahjgForm.setNd(jxjSqxnnd != null ? jxjSqxnnd[1] : "");//��ǰ���*/
		appendProperties(request, xydm, zydm, nj);
		comAct = new CommonAction();
		comAct.appendFdybjList(request);
		request.setAttribute("tableName", "view_xsrychb");
		request.setAttribute("realTable", "xsrychb");
		request.setAttribute("pk", "xn||nd||xh||rychdm");
		request.setAttribute("rychList", rychList);
		request.setAttribute("ag", ahjgForm);
		return mapping.findForward("xjgrshdefault");
	}
	
	/**
	 * �Ƚ�������˲�ѯҳ��
	 * xjgrshqry ---- �Ƚ�������˲�ѯ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjgrShQry(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		XjgrQryModel xjgrqryModel = new XjgrQryModel();
		
		PjpyAhjgService service = new PjpyAhjgService();
		HttpSession session    = request.getSession();
		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String sUserType        = session.getAttribute("userType").toString();//�û�����
		String sIsFdy         = session.getAttribute("isFdy").toString();//����Ա
		String userName       = session.getAttribute("userName").toString();
		String xydm = "";
		//�û���ѧԺʱ
		if("xy".equalsIgnoreCase(sUserType)){
			xydm = bmdm;
			ahjgForm.setXydm(xydm);
		} 
		BeanUtils.copyProperties(xjgrqryModel, ahjgForm);
		List<HashMap<String, String>> rychList = service.getRychList();//�����ƺ��б�
//		String[] jxjSqxnnd = service.getJxjsqxn();//��ѧ������ѧ�����
		//ahjgForm.setXn(jxjSqxnnd != null ? jxjSqxnnd[0] : "");//��ǰѧ��
		//ahjgForm.setNd(jxjSqxnnd != null ? jxjSqxnnd[1] : "");//��ǰ���
		List<HashMap<String, String>> topList = service.getXjgrTitle(sUserType, sIsFdy);//��ͷ
		List<String[]> resList = service.getXjgrResult(sUserType, sIsFdy, xjgrqryModel, sIsFdy, userName);//���
		
		appendProperties(request, xydm, zydm, nj);
		comAct = new CommonAction();
		comAct.appendFdybjList(request);
		request.setAttribute("tableName", "view_xsrychb");
		request.setAttribute("realTable", "xsrychb");
		request.setAttribute("pk", "xn||nd||xh||rychdm");
		request.setAttribute("rychList", rychList);
		request.setAttribute("ag", ahjgForm);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//��¼��
		return mapping.findForward("xjgrshdefault");
	}
	
	/**
	 * �Ƚ����˵��������ʾ
	 * xjgfshone ---- �Ƚ����˵��������ʾ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjgrShOne(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		PjpyAhjgService service = new PjpyAhjgService();
		HttpSession session    = request.getSession();
//		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String sUserType        = session.getAttribute("userType").toString();//�û�����
		String sIsFdy         = session.getAttribute("isFdy").toString();//����Ա
		HashMap<String, String> resMap = service.getXjgrByPk(sUserType, sIsFdy, pkValue);//��ȡ������Ϣ
		List<HashMap<String, String>> chkList = service.getChkList(3);//����б�
		HashMap<String, String> xspjfmcList = service.getXsPsxxb(xh, xn);//ѧ��������Ϣ��
		if (xspjfmcList != null) {
			resMap.put("pjf", xspjfmcList.get("pjf"));
			resMap.put("mc", xspjfmcList.get("mc"));
			resMap.put("drzw", xspjfmcList.get("drzw"));
			resMap.put("xxjsmc", xspjfmcList.get("xxjsmc"));
			resMap.put("bjrychmc", xspjfmcList.get("bjrychmc"));
			String cet4 = xspjfmcList.get("cet4");//Ӣ���ļ��Ƿ�ͨ��
			String jsjej = xspjfmcList.get("jsjej");//����������Ƿ�ͨ��
			cet4 = (!StringUtils.isNull(cet4)) && StringUtils.isEqual(cet4, "tg") ? "ͨ��" : "δͨ��";
			jsjej = (!StringUtils.isNull(jsjej)) && StringUtils.isEqual(jsjej, "tg") ? "ͨ��" : "δͨ��";
			resMap.put("cet4", cet4);
			resMap.put("jsjej", jsjej);
			resMap.put("wmss", xspjfmcList.get("wmss"));
		}
		String[] dfAndPmList = service.getStudfAndPm(xh);//��ȡ������ʮ�Ѵ�ѧ���÷ּ�����
		if (dfAndPmList != null && dfAndPmList.length == 2) {
			resMap.put("pm", dfAndPmList[0]);
			resMap.put("df", dfAndPmList[1]);
		}
		ahjgForm.setYesNo(resMap.get("yesno"));
		request.setAttribute("chkList", chkList);
		request.setAttribute("rs", resMap);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("ag", ahjgForm);
		return mapping.findForward("xjgrshone");
	}
	
	/**
	 * �Ƚ����˵������
	 * xjgrshbyone ---- �Ƚ����˵������ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjgrshByOne(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String rychdm = request.getParameter("rychdm");
		String oldxh = request.getParameter("oldxh");
		String yesNo = request.getParameter("yesNo");
		PjpyAhjgService service = new PjpyAhjgService();
		HttpSession session    = request.getSession();
//		String bmdm            = session.getAttribute("userDep").toString();//�û����ڲ���
		String sUserType        = session.getAttribute("userType").toString();//�û�����
		String sIsFdy         = session.getAttribute("isFdy").toString();//����Ա
		String sFlag = service.xjgrshByOne(sUserType, sIsFdy, yesNo, pkValue, request, rychdm, oldxh);//���(����Ա,ѧԺ,ѧУ)
		HashMap<String, String> resMap = service.getXjgrByPk(sUserType, sIsFdy, pkValue);//��ȡ������Ϣ
		List<HashMap<String, String>> chkList = service.getChkList(3);//����б�
		ahjgForm.setYesNo(resMap.get("yesNo"));
		if (StringUtils.isNull(sFlag)) {
			request.setAttribute("inserted", "yes");
		}else {
			request.setAttribute("failInfo", sFlag);//������Ϣ
			request.setAttribute("inserted", "no");
		}//end if
		
		request.setAttribute("chkList", chkList);
		request.setAttribute("rs", resMap);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("ag", ahjgForm);
		return mapping.findForward("xjgrshone");
	}
	
	/**
	 * ѧϰ����Ĭ��ҳ��
	 * xxjsdefault ---- ѧϰ����Ĭ��ҳ�� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xxjsDefault(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			ahjgForm.setXydm(xydm);
		}
		nj = ahjgForm.getNj();
		zydm = ahjgForm.getZydm();
		//appendProperties(request, xydm, zydm, nj);
		appendProperties(request, xydm, zydm, nj);//����ҳ���б�
		request.setAttribute("realTable", "xsxxjsb");
		request.setAttribute("tableName", "xsxxjsb");
		return mapping.findForward("xxjsdefault");
	}
	
	/**
	 * ѧϰ������ѯ
	 * xsxxjsqry ---- ѧ��ѧϰ������ѯ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsxxjsQry(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			ahjgForm.setXydm(xydm);
		}
		nj = ahjgForm.getNj();
		zydm = ahjgForm.getZydm();
		//appendProperties(request, xydm, zydm, nj);
		XxjsQryModel xxjsqryModel = new XxjsQryModel();//ѧϰ������ѯMODEL
		BeanUtils.copyProperties(xxjsqryModel, ahjgForm);
		PjpyAhjgService service = new PjpyAhjgService();
		List<HashMap<String, String>> topList = service.getXxjsTitle();//��ͷ
		List<String[]> resList = service.getXxjsResult(xxjsqryModel);//���
		
		ahjgForm.setXh(DealString.toGBK(ahjgForm.getXh()));
		ahjgForm.setXm(DealString.toGBK(ahjgForm.getXm()));
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		request.setAttribute("topTr", topList);
		appendProperties(request, xydm, zydm, nj);//����ҳ���б�
		request.setAttribute("realTable", "xsxxjsb");
		request.setAttribute("tableName", "xsxxjsb");
		return mapping.findForward("xxjsdefault");
	}
	
	/**
	 * ѧϰ��������
	 * xxjsadd ---- ѧϰ�������� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xxjsAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		PjpyAhjgService service = new PjpyAhjgService();
		String xh = request.getParameter("xh");
		HashMap<String, String> resMap = service.getXsInfo(xh);//ͨ��ѧ�Ż�ȡѧ��������ѧԺ��רҵ���༶
		List<HashMap<String, String>> xxjsList = service.getXxjSList();//�����б�
		request.setAttribute("xxjsList", xxjsList);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rs", resMap);
		return mapping.findForward("xxjsadd");
	}
	
	/**
	 *  ѧϰ������Ϣ����
	 *  savexxjsxx ---- ѧϰ������Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveXxjsxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		XsjxSaveModel xsjxsaveModel = new XsjxSaveModel();//����MODEL
		BeanUtils.copyProperties(xsjxsaveModel, ahjgForm);
		PjpyAhjgService service = new PjpyAhjgService();
		boolean bFlag = service.saveXxjsInfo(xsjxsaveModel, request);//���ݱ���
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		}else {
			request.setAttribute("inserted", "no");
		}
		
		String xh = request.getParameter("xh");
		HashMap<String, String> resMap = service.getXsInfo(xh);//ͨ��ѧ�Ż�ȡѧ��������ѧԺ��רҵ���༶
		List<HashMap<String, String>> xxjsList = service.getXxjSList();//�����б�
		ahjgForm.setBz(DealString.toGBK(ahjgForm.getBz()));
		request.setAttribute("xxjsList", xxjsList);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rs", resMap);
		return mapping.findForward("xxjsadd");
	}
	
	/**
	 * ѧϰ�����޸���ʾ
	 * xxjsModi ----ѧϰ�����޸���ʾ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xxjsModi(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));//����
		PjpyAhjgService service = new PjpyAhjgService();
		HashMap<String, String> resMap = service.getXxjsInfoByPk(pkValue);//ͨ��PK��ȡ��Ϣ
		List<HashMap<String, String>> xxjsList = service.getXxjSList();//�����б�
		request.setAttribute("xxjsList", xxjsList);
		request.setAttribute("rs", resMap);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("xxjsmodi");
	}
	
	/**
	 * ѧϰ��������ɾ��
	 * xxjsdel ---- ѧϰ����ɾ�� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xxjsDel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		String[] pkList = ahjgForm.getCbv();//�����б�
		PjpyAhjgService service = new PjpyAhjgService();
		String sRes = service.xxjsDel(pkList);//����ɾ��
		if (!StringUtils.isNull(sRes)) {
			request.setAttribute("result", "view");
		}else {
			request.setAttribute("result", sRes);
		}//end if
		
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("realTable", "xsxxjsb");
		request.setAttribute("tableName", "xsxxjsb");
		return mapping.findForward("xxjsdefault");
	}
	
	/**
	 * ��ѧ��֤�鵥����ӡ����ӡ����ҳ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjzsprint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		String xxdm = StandardOperation.getXxdm();
		PjpyAhjgService service = new PjpyAhjgService();
		String xh = request.getParameter("xh");
		String xm = DealString.toGBK(request.getParameter("xm"));
		String hjxn = request.getParameter("hjxn");
		String hjxq = DealString.toGBK(request.getParameter("hjxq"));
		String hjxjdj = DealString.toGBK(request.getParameter("hjxjdj"));
		String hjny = DealString.toGBK(request.getParameter("hjny"));
		String hjqsxn = "";//����ʼѧ��
		String hjjsxn = "";//�񽱽���ѧ��
		String fzxn = "";//��֤��
		String fzy = "";//��֤��
		if (!StringUtils.isNull(hjxn) && hjxn.indexOf("-") < 0 && hjxn.length()==8) {
			 hjqsxn = hjxn.substring(0, 4);
			 hjjsxn = hjxn.substring(4, 8);
			 hjqsxn = !StringUtils.isNull(hjqsxn) ? hjqsxn.substring(2, 4) : "";
			 hjjsxn = !StringUtils.isNull(hjjsxn) ? hjjsxn.substring(2, 4) : "";
		}else if (!StringUtils.isNull(hjxn) && hjxn.length()==9) {
			hjqsxn = hjxn.substring(2, 4);
			hjjsxn = hjxn.substring(7, 9);
		}
		if (Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
			 if (hjny.indexOf("-") != -1) {
				 fzxn = hjny.substring(0, hjny.indexOf("-"));
				 fzy = hjny.substring(hjny.indexOf("-")+1, hjny.length());
			 }
		} else {
			if (!StringUtils.isNull(hjny) && hjny.indexOf("-") < 0 && hjny.length()==6) {
				 fzxn = hjny.substring(0, 4);
				 fzy = hjny.substring(4, 6);
			}else if (!StringUtils.isNull(hjny) && hjny.length()==7) {
				fzxn = hjny.substring(0, 4);
				fzy = hjny.substring(5, 7);
			}
		}
		String[] topleftList = service.getTopLeftStr("allschool");
		String topstr = "";
		String leftstr = "";
		if (topleftList != null && topleftList.length == 2) {
			topstr = topleftList[0];
			leftstr = topleftList[1];
		}
		request.setAttribute("xm", xm);
		request.setAttribute("hjqsxn", hjqsxn);
		request.setAttribute("hjjsxn", hjjsxn);
		request.setAttribute("hjxjdj", hjxjdj);
		request.setAttribute("fzxn", fzxn);
		request.setAttribute("fzy", fzy);
		request.setAttribute("xh", xh);
		request.setAttribute("hjxq", hjxq);
		request.setAttribute("topstr", topstr);
		request.setAttribute("leftstr", leftstr);
		return mapping.findForward("jxjzsprint");
	}
	
	/**
	 * ��ѧ��֤������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjzsPrintMore(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
//		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		String xxdm = StandardOperation.getXxdm();
		PjpyAhjgService service = new PjpyAhjgService();
		String xh = request.getParameter("xh");
		String xm = DealString.toGBK(request.getParameter("xm"));
		String hjxn = request.getParameter("hjxn");
		String hjxq = DealString.toGBK(request.getParameter("hjxq"));
		String hjxjdj = DealString.toGBK(request.getParameter("hjxjdj"));
		String hjny = DealString.toGBK(request.getParameter("hjny"));
		String hjqsxn = "";//����ʼѧ��
		String hjjsxn = "";//�񽱽���ѧ��
		String fzxn = "";//��֤��
		String fzy = "";//��֤��
		if (!StringUtils.isNull(hjxn) && hjxn.indexOf("-") < 0 && hjxn.length()==8) {
			 hjqsxn = hjxn.substring(0, 4);
			 hjjsxn = hjxn.substring(4, 8);
			 hjqsxn = !StringUtils.isNull(hjqsxn) ? hjqsxn.substring(2, 4) : "";
			 hjjsxn = !StringUtils.isNull(hjjsxn) ? hjjsxn.substring(2, 4) : "";
		}else if (!StringUtils.isNull(hjxn) && hjxn.length()==9) {
			hjqsxn = hjxn.substring(2, 4);
			hjjsxn = hjxn.substring(7, 9);
		}
		if (Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
			if (hjny.indexOf("-") != -1) {
				 fzxn = hjny.substring(0, hjny.indexOf("-"));
				 fzy = hjny.substring(hjny.indexOf("-")+1, hjny.length());
			 }
		} else {
			if (!StringUtils.isNull(hjny) && hjny.indexOf("-") < 0 && hjny.length()==6) {
				 fzxn = hjny.substring(0, 4);
				 fzy = hjny.substring(4, 6);
			}else if (!StringUtils.isNull(hjny) && hjny.length()==7) {
				fzxn = hjny.substring(0, 4);
				fzy = hjny.substring(5, 7);
			}
		}
		String[] topleftList = service.getTopLeftStr("allschool");
		String topstr = "";
		String leftstr = "";
		if (topleftList != null && topleftList.length == 2) {
			topstr = topleftList[0];
			leftstr = topleftList[1];
		}
		request.setAttribute("xm", xm);
		request.setAttribute("hjxn", hjxn);
		request.setAttribute("hjny", hjny);
		request.setAttribute("hjqsxn", hjqsxn);
		request.setAttribute("hjjsxn", hjjsxn);
		request.setAttribute("hjxjdj", hjxjdj);
		request.setAttribute("fzxn", fzxn);
		request.setAttribute("fzy", fzy);
		request.setAttribute("xh", xh);
		request.setAttribute("hjxq", hjxq);
		request.setAttribute("topstr", topstr);
		request.setAttribute("leftstr", leftstr);
		return mapping.findForward("jxjzsprintmore");
	}
	
	/**
	 * ������������������ҳ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjszDefault(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		PjpyAhjgService service = new PjpyAhjgService();
		//���ݲ������Ͳ�ͬ���ز�ͬ����Ŀ�б�
		List<HashMap<String, String>> jxjList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> rychList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> tjList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> zdczList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> zdbjList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		List<String[]> resList = new ArrayList<String[]>(); 
		String xxdm = StandardOperation.getXxdm();
		
		if (!StringUtils.isNull(ahjgForm.getCslx()) && StringUtils.isEqual(ahjgForm.getCslx(), "jxj")) {
			jxjList = service.getJxjList();//��Ŀ�б�
			if (Globals.XXDM_ZJGSZYJSXY.equalsIgnoreCase(xxdm)) {
				String tjdm = request.getParameter("tjdm");
				if (!StringUtils.isNull(tjdm) && "zfpm".equalsIgnoreCase(tjdm)) {
					zdbjList = service.getZdbjList(3);//�ֶαȽ��б�
					request.setAttribute("fsfs", "pm");
				} else if (!StringUtils.isNull(tjdm) && !"zfpm".equalsIgnoreCase(tjdm)) {
					zdbjList = service.getZdbjList(4);//�ֶαȽ��б�
				} else {
					zdbjList = service.getZdbjList(2);//�ֶαȽ��б�
				}
				tjList = service.getJxjTjList(4);//�ֶ������б�
			}else {
				tjList = service.getJxjTjList(0);//�ֶ������б�
				zdczList = service.getZdczList(0);//�ֶβ����б�
				zdbjList = service.getZdbjList(0);//�ֶαȽ��б�
			}			
			request.setAttribute("jxj", "1");
		}
		if (!StringUtils.isNull(ahjgForm.getCslx()) && StringUtils.isEqual(ahjgForm.getCslx(), "rych")) {
			rychList = service.getRych();//��Ŀ�б�
			if (Globals.XXDM_ZJGSZYJSXY.equalsIgnoreCase(xxdm)) {
				tjList = service.getJxjTjList(5);//�ֶ������б�
				zdbjList = service.getZdbjList(1);//�ֶαȽ��б�
			}else if (Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
				zdbjList = service.getZdbjList(5);//�ֶαȽ��б�
				tjList = service.getJxjTjList(1);//�ֶ������б�
				zdczList = service.getZdczList(1);//�ֶβ����б�
				
			}else {
				tjList = service.getJxjTjList(1);//�ֶ������б�
				zdczList = service.getZdczList(1);//�ֶβ����б�
				zdbjList = service.getZdbjList(0);//�ֶαȽ��б�
			}
			
			request.setAttribute("rych", "2");
		}
		if (Globals.XXDM_SCJZZYJSXY.equalsIgnoreCase(xxdm)) {
			zdbjList = service.getZdbjList(1);//�ֶαȽ��б�
			tjList = service.getJxjTjList(3);//�ֶ������б�
		} 
		topList = service.getTjTitle();
		request.setAttribute("topTr", topList);
		request.setAttribute("tjList", tjList);
		request.setAttribute("zdczList", zdczList);
		request.setAttribute("zdbjList", zdbjList);
		request.setAttribute("jxjList", jxjList);
		request.setAttribute("rychList", rychList);
		if (!StringUtils.isNull(ahjgForm.getXmdm())) {
			resList = service.getTjResult(ahjgForm.getXmdm());
		}
		request.setAttribute("rs", resList);
		appendProperties(request, xydm, zydm, nj);//����ҳ�����е������б�		
		return mapping.findForward("tjszdefault");
	}
	
	/**
	 * ������������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjPdtjAdd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		TjszModel tjzdModel = new TjszModel();
		PjpyAhjgService service = new PjpyAhjgService();
		//���ݲ������Ͳ�ͬ���ز�ͬ����Ŀ�б�
		List<HashMap<String, String>> jxjList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> rychList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> tjList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> zdczList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> zdbjList = new ArrayList<HashMap<String,String>>();
		String tjmc = DealString.toGBK(request.getParameter("zdmc"));
		String czmc = DealString.toGBK(request.getParameter("czmc"));
		String xxdm = StandardOperation.getXxdm();
		ahjgForm.setXn(Base.getJxjsqxn());//��ѧ������ѧ��
		if (!StringUtils.isNull(ahjgForm.getCslx()) && StringUtils.isEqual(ahjgForm.getCslx(), "jxj")) {
			jxjList = service.getJxjList();//��Ŀ�б�
			if (Globals.XXDM_ZJGSZYJSXY.equalsIgnoreCase(xxdm)) {
				String tjdm = request.getParameter("tjdm");
				if (!StringUtils.isNull(tjdm) && "zfpm".equalsIgnoreCase(tjdm)) {
					zdbjList = service.getZdbjList(3);//�ֶαȽ��б�
					request.setAttribute("fsfs", "pm");
				} else if (!StringUtils.isNull(tjdm) && !"zfpm".equalsIgnoreCase(tjdm)) {
					zdbjList = service.getZdbjList(4);//�ֶαȽ��б�
				} else {
					zdbjList = service.getZdbjList(2);//�ֶαȽ��б�
				}
				tjList = service.getJxjTjList(4);//�ֶ������б�
			} else {
			tjList = service.getJxjTjList(0);//�ֶ������б�
			zdczList = service.getZdczList(0);//�ֶβ����б�
			zdbjList = service.getZdbjList(0);//�ֶαȽ��б�
			}
			request.setAttribute("jxj", "1");
		}
		if (!StringUtils.isNull(ahjgForm.getCslx()) && StringUtils.isEqual(ahjgForm.getCslx(), "rych")) {
			rychList = service.getRych();//��Ŀ�б�
			if (Globals.XXDM_ZJGSZYJSXY.equalsIgnoreCase(xxdm)) {
				tjList = service.getJxjTjList(5);//�ֶ������б�
				zdbjList = service.getZdbjList(1);//�ֶαȽ��б�
			}else if (Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
				zdbjList = service.getZdbjList(5);//�ֶαȽ��б�
				tjList = service.getJxjTjList(1);//�ֶ������б�		
			}else {
			tjList = service.getJxjTjList(1);//�ֶ������б�
			zdczList = service.getZdczList(1);//�ֶβ����б�
			zdbjList = service.getZdbjList(0);//�ֶαȽ��б�
			}
			request.setAttribute("rych", "2");
		}
		if (Globals.XXDM_SCJZZYJSXY.equalsIgnoreCase(xxdm)) {
			zdbjList = service.getZdbjList(1);//�ֶαȽ��б�
			tjList = service.getJxjTjList(3);//�ֶ������б�
		}
		BeanUtils.copyProperties(tjzdModel, ahjgForm);
		tjzdModel.setTjmc(tjmc);
		tjzdModel.setTjzdlyb("zhszcp");
		tjzdModel.setCzmc(czmc);//TODO
		boolean bFlag = service.tjSave(tjzdModel, request);//���ݱ���
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		List<HashMap<String, String>> topList = service.getTjTitle();
		List<String[]> resList = service.getTjResult(ahjgForm.getXmdm());
		request.setAttribute("tjList", tjList);
		request.setAttribute("zdczList", zdczList);
		request.setAttribute("zdbjList", zdbjList);
		request.setAttribute("jxjList", jxjList);
		request.setAttribute("rychList", rychList);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		appendProperties(request, xydm, zydm, nj);//����ҳ�����е������б�
		return mapping.findForward("tjszdefault");
	}
	
	/**
	 * ������������ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjszplDel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		PjpyAhjgService service = new PjpyAhjgService();
		String xxdm = StandardOperation.getXxdm();
		//���ݲ������Ͳ�ͬ���ز�ͬ����Ŀ�б�
		List<HashMap<String, String>> rychList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> tjList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> jxjList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> zdczList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> zdbjList = new ArrayList<HashMap<String,String>>();
		if (!StringUtils.isNull(ahjgForm.getCslx()) && StringUtils.isEqual(ahjgForm.getCslx(), "jxj")) {
			jxjList = service.getJxjList();//��Ŀ�б�
			tjList = service.getJxjTjList(0);//�ֶ������б�
			zdczList = service.getZdczList(0);//�ֶβ����б�
			zdbjList = service.getZdbjList(0);//�ֶαȽ��б�
			request.setAttribute("jxj", "1");
		}
		if (!StringUtils.isNull(ahjgForm.getCslx()) && StringUtils.isEqual(ahjgForm.getCslx(), "rych")) {
			rychList = service.getRych();//��Ŀ�б�
			tjList = service.getJxjTjList(1);//�ֶ������б�
			zdczList = service.getZdczList(1);//�ֶβ����б�
			zdbjList = service.getZdbjList(0);//�ֶαȽ��б�
			if (Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
				zdbjList = service.getZdbjList(5);// �ֶαȽ��б�
			}
			request.setAttribute("rych", "2");
		}
		boolean bFlag = service.tjszplDel();//����ɾ��
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("tjList", tjList);
		request.setAttribute("zdczList", zdczList);
		request.setAttribute("zdbjList", zdbjList);
		request.setAttribute("jxjList", jxjList);
		request.setAttribute("rychList", rychList);
		appendProperties(request, xydm, zydm, nj);//����ҳ�����е������б�
		return mapping.findForward("tjszdefault");
	}
	
	/**
	 * �������õ���ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tjszdgDel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjpyAhjgActionForm ahjgForm = (PjpyAhjgActionForm) form;
		PjpyAhjgService service = new PjpyAhjgService();
		String xxdm = StandardOperation.getXxdm();
		//���ݲ������Ͳ�ͬ���ز�ͬ����Ŀ�б�
		List<HashMap<String, String>> jxjList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> rychList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> tjList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> zdczList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> zdbjList = new ArrayList<HashMap<String,String>>();
		if (!StringUtils.isNull(ahjgForm.getCslx()) && StringUtils.isEqual(ahjgForm.getCslx(), "jxj")) {
			jxjList = service.getJxjList();//��Ŀ�б�
			tjList = service.getJxjTjList(0);//�ֶ������б�
			zdczList = service.getZdczList(0);//�ֶβ����б�
			zdbjList = service.getZdbjList(0);//�ֶαȽ��б�
			request.setAttribute("jxj", "1");
		}
		if (!StringUtils.isNull(ahjgForm.getCslx()) && StringUtils.isEqual(ahjgForm.getCslx(), "rych")) {
			rychList = service.getRych();//��Ŀ�б�
			tjList = service.getJxjTjList(1);//�ֶ������б�
			zdczList = service.getZdczList(1);//�ֶβ����б�
			zdbjList = service.getZdbjList(0);//�ֶαȽ��б�
			if (Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
				zdbjList = service.getZdbjList(5);// �ֶαȽ��б�
			}
			request.setAttribute("rych", "2");
		}
		boolean bFlag = service.tjszdgDel(request.getParameter("pkValue"));//����ɾ��
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		List<HashMap<String, String>> topList = service.getTjTitle();
		if (!StringUtils.isNull(ahjgForm.getXmdm())) {
			List<String[]> resList = service.getTjResult(ahjgForm.getXmdm());
			request.setAttribute("rs", resList);
		}
		request.setAttribute("topTr", topList);
		request.setAttribute("tjList", tjList);
		request.setAttribute("zdczList", zdczList);
		request.setAttribute("zdbjList", zdbjList);
		request.setAttribute("jxjList", jxjList);
		request.setAttribute("rychList", rychList);
		appendProperties(request, xydm, zydm, nj);//����ҳ�����е������б�
		return mapping.findForward("tjszdefault");
	}
	
	/**
	 * ѧ���ɼ���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xscjb (ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		PjpyAhjgService service = new PjpyAhjgService();
		String xh = request.getParameter("xh");
		String xxdm = StandardOperation.getXxdm();
		List<String[]> resList = service.getXscj(xh);// �����ڽ�ѧ������ѧ��ѧ�ڳɼ�
		String[] jxjsqxnxq = service.getJxjsqxnxq();
		if (jxjsqxnxq == null) {
			jxjsqxnxq = new String[2];
		}
		if (Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
			request.setAttribute("title", String.format("%1$s ��ɼ����ܱ�",
					jxjsqxnxq[0], jxjsqxnxq[1]));
		} else if(Globals.XXDM_JHZYJSXY.equalsIgnoreCase(xxdm)){
			request.setAttribute("title", String.format("%1$s ��ɼ����ܱ�",
					jxjsqxnxq[0], jxjsqxnxq[1]));
		}else {
			request.setAttribute("title", String.format("%1$s ��� %2$s ѧ�ڳɼ����ܱ�",
					jxjsqxnxq[0], jxjsqxnxq[1]));
		}
		request.setAttribute("rs", resList);
		request.setAttribute("xxdm", xxdm);
		return mapping.findForward("xscjb");
	}
	
	public static void main(String...strings) {
		System.out.println("a".hashCode());
		System.out.println("z".hashCode());
		System.out.println("A".hashCode());
		System.out.println("Z".hashCode());
	}
}
