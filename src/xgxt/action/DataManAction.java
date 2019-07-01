/*
 * �������� 2006-8-29
 *
 *  Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package xgxt.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.MessageResources;

import xgxt.DAO.DAO;
import xgxt.DAO.GetDropDownList;
import xgxt.action.dataMan.ModiData;
import xgxt.action.service.DataManService;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.comm.CommService;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.daoActionLogic.StuInfoActionLogic;
import xgxt.dtjs.zjcm.dao.DtjszjcmDAO;
import xgxt.dtjs.zjcm.server.DtjszjcmService;
import xgxt.form.CommanForm;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.jxgl.JxglDAO;
import xgxt.jxgl.JxglJzService;
import xgxt.pjpy.PjpyDAO;
import xgxt.pjpy.czxx.dycj.DyjcfDAO;
import xgxt.pjpy.szyqxy.zhszcp.PjpySzyqxyZhszcpDAO;
import xgxt.qgzx.service.QgzxService;
import xgxt.rcgl.gzdx.RcglGzdxService;
import xgxt.studentInfo.csmzzyjsxy.StudentInfoCsmzService;
import xgxt.studentInfo.dao.StuInfoDAO;
import xgxt.studentInfo.dao.StudentInfoDao;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.studentInfo.xbemy.xbemyStudentInfoDAO;
import xgxt.studentInfo.ynys.XsxxYnysService;
import xgxt.utils.Arrays2;
import xgxt.utils.CheckPower;
import xgxt.utils.Check_Input_Value;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.New_Random_ID;
import xgxt.utils.ToolClass;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xgxt.utils.form.FormUtils;
import xgxt.wjcf.wjcfutils.CommonAction;
import xgxt.xsgygl.GyglTyDAO;
import xgxt.xsgygl.dao.GyglShareDAO;
import xgxt.xsgygl.dao.gyglDao;
import xgxt.xsgygl.ynys.qsz.YnysQszService;
import xgxt.xsxx.comm.jbsz.XsxxJbszForm;
import xgxt.xsxx.comm.jbsz.XsxxJbszService;
import xgxt.xsxx.comm.jbxx.XsxxJbxxForm;
import xgxt.xsxx.comm.jbxx.XsxxJbxxService;
import xsgzgl.gygl.comm.GyglNewInit;

import com.zfsoft.basic.BasicAction;
import common.Globals;
import common.GlobalsVariable;

/**
 * @author bat_zzj
 * 
 * Ҫ���Ĵ����ɵ�����ע�͵�ģ�壬��ת�� ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
public class DataManAction extends BasicAction {
	
	String writeAble = "";

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// ͨ�õ����ݲ���ģ��
		CommanForm chkUser = (CommanForm) form;
		
		User user = getUser(request);// �û�����
		
		RequestForm rForm = new RequestForm();
		CommService commService = new CommService();

		commService.setRequestValue(rForm, user, request);
		
		try {
			// �ж��û���дȨ
			writeAble = Base.getWriteAble(request);
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				chkUser.setErrMsg("��½��ʱ�������µ�½��");
				return new ActionForward("/login.jsp", false);
			}
			HttpSession session = request.getSession();
			if (session == null) {
				request.setAttribute("errMsg", "sadfsdf");
				return mapping.findForward("false");
			}
			ActionForward myActFwd = null;
			String myAct = mapping.getParameter();
			if ("DATASEARCH".equalsIgnoreCase(myAct)) {
				myActFwd = dataSearch(mapping, form, request, response);
			} else if ("modiData".equalsIgnoreCase(myAct)) {
				//myActFwd = modiData(mapping, form, request, response);
				ModiData mode = new ModiData();
				myActFwd = mode.modiData(mapping, form, request, response,
						writeAble);
			} else if ("stuInfo".equalsIgnoreCase(myAct)) {
				myActFwd = stuInfo(mapping, form, request, response);
			} else if ("stuInfoXX".equalsIgnoreCase(myAct)) {
				myActFwd = stuInfoXX(mapping, form, request, response);
			} else if ("stuInfoDetails".equalsIgnoreCase(myAct)) {
				myActFwd = stuInfoDetails(mapping, form, request, response);
			} else if ("uploadFile".equalsIgnoreCase(myAct)) { //�ϴ��ļ�
				myActFwd = uploadFile(mapping, form, request, response);
			} else if ("importData".equalsIgnoreCase(myAct)) { //��������
				myActFwd = initDataItem(mapping, form, request, response);
			} else if ("saveData".equalsIgnoreCase(myAct)) {
				myActFwd = importData(mapping, form, request, response);
			} else if ("expData".equalsIgnoreCase(myAct)) {//���ݵ���
				myActFwd = expData(mapping, form, request, response);
			} else if ("bbdcExport".equalsIgnoreCase(myAct)) {//ͬ�ô�ѧ�Զ��嵼��
				myActFwd = bbdcExport(mapping, form, request, response);
			} else if ("expData2".equalsIgnoreCase(myAct)) {
				myActFwd = expData2(mapping, form, request, response);
			} else if ("modiStuInfo".equalsIgnoreCase(myAct)) {
				myActFwd = modiStuInfo(mapping, form, request, response);
			} else if ("createTestPaper".equalsIgnoreCase(myAct)) {
				myActFwd = createTestPaper(mapping, form, request, response);
			} else if ("data_import".equalsIgnoreCase(myAct)) {
				myActFwd = data_import(mapping, form, request, response);				
			} else if ("impDataCommit".equalsIgnoreCase(myAct)) {
				myActFwd = impDataCommit(mapping, form, request, response);
			} else if ("submitTestPaper".equalsIgnoreCase(myAct)) {
				myActFwd = submitTestPaper(mapping, form, request, response);
			} else if ("stuarchives".equalsIgnoreCase(myAct)) {
				myActFwd = stuArchives(mapping, form, request, response);
			} else if ("stuhdjxjinfo".equalsIgnoreCase(myAct)) {
				myActFwd = stuArchivesInfo(mapping, form, request, response);
			} else if ("xsxxzdgz".equalsIgnoreCase(myAct)) {
				myActFwd = xsxxzdgzInfo(mapping, form, request, response);
			} else if ("xsxxytxx".equalsIgnoreCase(myAct)) {
				myActFwd = xsxxytxxInfo(mapping, form, request, response);
			} else if ("xlcscj".equalsIgnoreCase(myAct)) {
				myActFwd = xlcscj(mapping, form, request, response);
			} else if ("xlcsstfx".equalsIgnoreCase(myAct)) {
				myActFwd = testResultView(mapping, form, request, response);
			} else if ("familyInfo".equalsIgnoreCase(myAct)) {
				myActFwd = StuInfoActionLogic.familyInfo(mapping, form,request, response);
				request.setAttribute("path", "modi_stu_famil.do");
				FormModleCommon.commonRequestSet(request);
				writeAble = request.getAttribute("writeAble").toString();
			} else if("print".equalsIgnoreCase(myAct)){
				myActFwd = dataPrint(mapping, form, request, response);
			} else if("xszsxxcheck".equalsIgnoreCase(myAct)){//ѧ��ס����Ϣ��֤��ѯ
				myActFwd=xszsxxCheck(mapping, form, request, response);
			}
			DAO dao = DAO.getInstance();
			if (dao.getXxdm().equalsIgnoreCase(Globals.XXDM_HZZY)) {
				if (request.getSession().getAttribute("userName").toString().equalsIgnoreCase("zf01")) {
					request.setAttribute("writeAble", "yes");
				} else {
					request.setAttribute("writeAble", writeAble);
				}
			}else if(Globals.XXDM_XYSFXY.equalsIgnoreCase(Base.xxdm)){
				request.setAttribute("writeAble", writeAble);
				request.setAttribute("isPrint", "yes");
				
			}else {
				request.setAttribute("writeAble", writeAble);
			}
			System.out.println(myActFwd);
			return myActFwd;
		} catch (Exception e) {
			e.printStackTrace();
			chkUser.setErrMsg("���������жϣ������µ�½��");
			return new ActionForward("/login.jsp", false);
		}
	}


	//���ݵ���
	private ActionForward data_import(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String act = DealString.toGBK(request.getParameter("act"));
		request.setAttribute("moditag", request.getParameter("moditag"));
		if("realTable2".equalsIgnoreCase(act)){//����й����ͬһҳ����������
			return mapping.findForward("success2");
		}		
		return mapping.findForward("success");
	}
	
	private ActionForward dataPrint(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String xh = request.getParameter("xh");
		String zysj = request.getParameter("zysj");
		String zzmmmc = request.getParameter("zzmmmc");
		String rych = request.getParameter("rych");
		
		String sql = "select xh,xm,xymc,xb,zymc,bjmc,zzmmmc from view_xsxxb where xh=?";
		
		Map<String, String> map = DAO.getInstance().getMapNotOut(sql, new String[]{xh});
		
		request.setAttribute("map", map);
		request.setAttribute("zysj", zysj);
		request.setAttribute("rych", rych);
		request.setAttribute("zzmmmc", zzmmmc);
		return mapping.findForward("success");
	}



	private ActionForward testResultView(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		String sql = "";

		// String realTable = request.getParameter("realTable");
		// String doType = request.getParameter("doType");
		// String tableName = request.getParameter("tableName");
		String pk = request.getParameter("pk");
		// String from = request.getParameter("from");
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> map = new HashMap<String, String>();

		sql = " select distinct t.sjm,t.dtys,nvl(t.sjsm,'����') sjsm,t.sjxd,t.xh,t.dtsj,t.cj,nvl(t.bz,'����') bz,t.xm,t.bjmc "
			+ " from view_xsxlcsxx t "
			+ " where "
			+ pk
			+ " = '"
			+ pkValue
			+ "' ";
		String[] outValue = dao.getOneRs(sql, new String[] {}, new String[] {
				"sjm", "dtys", "sjsm", "sjxd", "xh", "dtsj", "cj", "bz", "xm","bjmc" });
		map.put("sjm", outValue[0]);
		map.put("dtys", outValue[1]);
		map.put("sjsm", outValue[2]);
		map.put("sjxd", outValue[3]);
		map.put("xh", outValue[4]);
		map.put("dtsj", outValue[5]);
		map.put("cj", outValue[6]);
		map.put("bz", outValue[7]);
		map.put("xm", outValue[8]);
		map.put("bjmc", outValue[9]);

		sql = " select count(*) stsl from (select stlsh "
			+ " from view_xsxlcsxx t " + " where " + pk + " = '" + pkValue
			+ "' " + " group by stlsh)";
		String stsl = dao.getOneRs(sql, new String[] {}, "stsl");
		map.put("stsl", stsl);

		sql = " select stlxdm,stlxmc,count(stlxdm) lxsl from "
			+ " (select stlsh,stlxdm,stlxmc from view_xsxlcsxx t "
			+ " where " + pk + " = '" + pkValue + "' "
			+ " group by stlsh,stlxdm,stlxmc)group by stlxdm,stlxmc";
		List lxList = dao.getList(sql, new String[] {}, new String[] {
				"stlxdm", "stlxmc", "lxsl" });

		// sql = " select
		// stxh,stlsh,stnr,stlxdm,stlxmc,stndjbdm,stndjbmc,nvl(stfz,'')
		// stfz,xxlsh,xxxh,xxnr,nvl(xxfz,'') xxfz " +
		// "from view_xsxlcsxx t where t.xh||t.dtsj||t.sjlsh =
		// '302022101120070524095303101' " +
		// "order by stxh,xxxh";
		// List stxxList = dao.getList(sql, new String[]{},
		// new String[]{
		// "stxh","stlsh","stnr","stlxdm","stlxmc","stndjbdm","stndjbmc","stfz","xxlsh","xxxh","xxnr","xxfz"
		// });

		ArrayList<HashMap<String, List<HashMap<String, String>>>> stxxList = new ArrayList<HashMap<String, List<HashMap<String, String>>>>();
		sql = " select distinct * from (select stxh,stlsh,stnr,stlxdm,stlxmc,stndjbdm,stndjbmc,nvl(stfz,'') stfz,dtda,dtfz,stda,stdajs "
			+ " from view_xsxlcsxx t "
			+ " where "
			+ pk
			+ " = '"
			+ pkValue
			+ "' " + " order by stxh desc,xxxh desc) order by stxh";
		List<HashMap<String, String>> stList = dao.getList(sql,
				new String[] {}, new String[] { "stxh", "stlsh", "stnr",
				"stlxdm", "stlxmc", "stndjbdm", "stndjbmc", "stfz",
				"dtda", "dtfz", "stda", "stdajs" });
		int size = stList.size();
		for (int i = 0; i < size; i++) {
			HashMap<String, String> mapt = new HashMap<String, String>();
			HashMap<String, List<HashMap<String, String>>> mapte = new HashMap<String, List<HashMap<String, String>>>();
			mapt = stList.get(i);
			String stlsht = (String) mapt.get("stlsh");
			sql = " select stlsh,xxlsh,xxxh,xxnr,nvl(xxfz,'') xxfz "
				+ " from view_xsxlcsxx t where " + pk + " = '" + pkValue
				+ "' " + " and stlsh = ? order by stxh,xxxh ";
			List<HashMap<String, String>> xxList = dao.getList(sql,
					new String[] { stlsht }, new String[] { "stlsh", "xxlsh","xxxh", "xxnr", "xxfz" });
			mapte.put("xxList", xxList);
			stxxList.add(mapte);
		}

		request.setAttribute("rs", map);
		request.setAttribute("lxList", lxList);
		request.setAttribute("stxxList", stxxList);

		return mapping.findForward("success");
	}

	private ActionForward xlcscj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DAO dao = DAO.getInstance();
		CommanForm comForm = (CommanForm) form;
		Vector<Object> rs = new Vector<Object>();
		String rsNum = "0";
		String sql = "";
		String query = " where 1=1 ";
		String tips = "������ - ������� - �ɼ���ѯ";
		String pk = "xh||dtsj||sjlsh";
		String tableName = "view_xsxlcsxx";
		String act = request.getParameter("go");
		String writeAble = "yes";
		String realTable = "xsxlcs";

		String xh = comForm.getXh();
		String nj = comForm.getNj();
		String dtsj = comForm.getDtsj();
		String sjlsh = comForm.getSjlsh();
		String xydm = comForm.getXydm();
		String zydm = comForm.getZydm();
		String bjdm = comForm.getBjdm();

		if (xh != null && !xh.equalsIgnoreCase(""))
			query += " and xh = '" + xh + "'";
		if (xydm != null && !xydm.equalsIgnoreCase(""))
			query += " and xydm = '" + xydm + "'";
		if (zydm != null && !zydm.equalsIgnoreCase(""))
			query += " and zydm = '" + zydm + "'";
		if (bjdm != null && !bjdm.equalsIgnoreCase(""))
			query += " and bjdm = '" + bjdm + "'";
		if (dtsj != null && !dtsj.equalsIgnoreCase(""))
			query += " and dtsj like '" + dtsj + "%'";
		if (sjlsh != null && !sjlsh.equalsIgnoreCase(""))
			query += " and sjlsh = '" + sjlsh + "'";
		if (nj != null && !nj.equalsIgnoreCase(""))
			query += " and nj = '" + nj + "'";

		sql = " select "
			+ pk
			+ " ����,rownum �к�,v.xh,v.xm,v.sjm,v.dtsj,v.dtys,v.cj from (select distinct xh,xm,sjlsh,sjm,dtsj,dtys,cj from "
			+ tableName + query + ") v order by dtsj ";
		String[] colList = new String[] { "����", "�к�", "xh", "xm", "sjm","dtsj", "dtys", "cj" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);

		if (act != null && act.equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		sql = " select sjlsh,sjm from sjb ";
		List sjxxList = dao.getList(sql, new String[] {}, new String[] {"sjlsh", "sjm" });

		request.setAttribute("tips", tips);
		request.setAttribute("sjxxList", sjxxList);
		request.setAttribute("topTr", topTr);
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("act", "view");// �������ݲ�ѯ����
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("njList", dao.getNjList());// �����꼶�б�
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", dao.getZyList(xydm));// ����רҵ�б�
		request.setAttribute("bjList", dao.getBjList(xydm, zydm));// ����רҵ�б�
		return mapping.findForward("success");
	}

	@SuppressWarnings("unchecked")
	private ActionForward dataSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// ���ݲ�ѯ
		
		CommanForm dataSearchForm = (CommanForm) form;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = " ";// sql���
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		String tips = "";// λ����ʾ��Ϣ
		String tableName = request.getParameter("tableName");// ��ѯ��ϢԴ����Ϊ��ͼ����
		String rsNum = "0";// ���صļ�¼��
		String realTable = "";// ����Դ��
		String pk = "";// ����Դ����������ʽΪ���ֶ���||�ֶ���||�ֶ�������
		String dataType = request.getParameter("act");
		String xh = request.getParameter("xh");
		xh = xh != null && !"".equalsIgnoreCase(xh) ? DealString.toGBK(xh) : xh;
		String xm = DealString.toGBK(dataSearchForm.getXm());
		String nd = dataSearchForm.getNd();
		String nf = dataSearchForm.getNf();
		String yf = dataSearchForm.getYf();
		String xjzt = DealString.toGBK(dataSearchForm.getXjzt());
		String nj = dataSearchForm.getNj();
		String xn = dataSearchForm.getXn();
		String xq = dataSearchForm.getXq();
		String xy = DealString.toGBK(dataSearchForm.getXydm());
		String zy = dataSearchForm.getZydm();
		String bj = dataSearchForm.getBjdm();
		String hth = dataSearchForm.getHth();
		String bzffny = dataSearchForm.getBzffny();
		String sfzh = dataSearchForm.getSfzh();
		String xmdm = dataSearchForm.getXmdm();
		String kh = dataSearchForm.getKh();
		String xxsh = DealString.toGBK(dataSearchForm.getXxsh());
		String cflb = dataSearchForm.getCflb();
		String cfyy = dataSearchForm.getCfyy();
		String wjkssj = dataSearchForm.getWjkssj();
		String wjjssj = dataSearchForm.getWjjssj();
		String cfkssj = dataSearchForm.getCfkssj();
		String cfjssj = dataSearchForm.getCfjssj();
		String userDep = (String) session.getAttribute("userDep");
		String userName = (String) session.getAttribute("userName");
		String userType = dao.getUserType(userDep);
		String userOnline = (String) session.getAttribute("userOnLine");
		String userType2 = (String) session.getAttribute("userType");   //ֱ�ӻ���û����ͣ��˷�ʽ��ȡ������Ϊ���ࣺ1��"stu"ѧ��2��"teacher"��ʦ
		String pkValue = request.getParameter("pkValue");
		String ndVar = request.getParameter("ndList");// for �ۺ����ʲ����е����
		String dxq = request.getParameter("dxq");
		String dxq1 = request.getParameter("writeAble");
		String xxmc = StandardOperation.getXxmc();	
		String xxdm = StandardOperation.getXxdm();
		String yrdwdm = dataSearchForm.getYrdwdm();
		String isFdy = session.getAttribute("isFdy").toString();
		String sfjw = request.getParameter("sfjw");
		String xsqr = request.getParameter("xsqr");
		String lydm =request.getParameter("lydm");
		String sfcx = request.getParameter("sfcx");
		
		//ѧ������Ȩ�޿��ƿ�ʼ
		if (dataType!=null && (dataType.equalsIgnoreCase("dormInfo")
				||dataType.equalsIgnoreCase("usingInfo")
				||dataType.equalsIgnoreCase("dailyNote")
				||dataType.equalsIgnoreCase("weeklyCollect"))) {
			if(userType2.equalsIgnoreCase("stu")){//ѧ���޲���Ȩ��
				request.setAttribute("errMsg", "ѧ����Ȩ���ʸ�ҳ�棡");
				return new ActionForward("/errMsg.do", false);
			}  
		}
		//ѧ������Ȩ�޿��ƽ���

		String tab = request.getParameter("tab");//��ɳ������ύ�����ж�
		tab = StringUtils.isNull(tab) ? "" : tab;
//		if((xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)||xxdm.equalsIgnoreCase(Globals.XXDM_YNYS))&& dataType!=null && dataType.equalsIgnoreCase("studentPaperAgain") ){//����Ƽ�ѧԺ,��������	
		if(xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)&& dataType!=null && dataType.equalsIgnoreCase("studentPaperAgain") ){//����Ƽ�ѧԺ,��������	
			return mapping.findForward("cqkj");
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
			//�Ϻ����̼�����ѧ
			if(dataType!=null && "insureInfo".equalsIgnoreCase(dataType)){
				return new ActionForward("/shgc_tbxxwh.do?method=tbxxwhQue");
			}
		}
		String qssj=request.getParameter("qssj");
		String zzsj=request.getParameter("zzsj");
		// long t1 = System.currentTimeMillis();
		String isBzr="";
		dataSearchForm.setXm(xm);
		if (!("".equalsIgnoreCase(dxq) || dxq == null)) {
			writeAble = dxq;
		} else {
			if (!("".equalsIgnoreCase(dxq1) || dxq1 == null)) {
				writeAble = dxq1;
			}			
		}
		if(dataType.equalsIgnoreCase("workOutlay")){
			writeAble = "no";
		}
		if (pkValue == null) {
			pkValue = "";
		}
		request.setAttribute("pkValue", pkValue);
		String jxhjlx = "";
		if ("xy".equalsIgnoreCase(userType)) {
			sql = "select xydel from xtszb where rownum=1";
			String xydel = dao.getOneRs(sql, new String[] {}, "xydel");
			if ("".equalsIgnoreCase(xydel) || xydel == null
					|| "0".equalsIgnoreCase(xydel)) {
				request.setAttribute("xydel", "no");
			}
		}

		if (null == dataType) {
			dataType = request.getParameter("dataType");
		}

		if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
			request.setAttribute("showhzy", "showhzy");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)) {
			request.setAttribute("showzszy", "showzszy");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SZXXZYJSXY)) {
			request.setAttribute("showszxx", "showszxx");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
			request.setAttribute("isSHGC", "is");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
			request.setAttribute("showbjlh", "showbjlh");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)) {
			request.setAttribute("showZjsyzy", "showZjsyzy");
			if ("student".equalsIgnoreCase(userOnline)) {
				request.setAttribute("showStu", "showStu");
			}
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {
			//����ط�ֻ���ۺ����ʲ������д�ӡ.���ģ��û�д�ӡ������ֻ�ǽ�����Ϣ������
			request.setAttribute("showjsxx", "showjsxx");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
			request.setAttribute("showzgdzdx", "showzgdzdx");
		}
		/*if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
			request.setAttribute("showXbemy", "showXbemy");
		}*/
		boolean isHTXX = false;
		//�Ƿ��������ղ�
		boolean isXLPC = false;
		//��ɳ����ְҵ����ѧԺ����Ⱥ��ѧ������ǼǱ�
		boolean isTSXSDJ = false;
		//��ɳ�������С����Ա
		boolean isBJXZRY = false;
		//��ɳ���������������
		boolean isTSQTGJ = false;
		//��ɳ��������Ⱥ�����볷��
		boolean isTSQTSQCX = false;
		//��ɳ�����������������ͳ��
		boolean isXLJKTJ = false;
		//�Ƿ��������ղ�
		boolean isZXQK = false;
		//�Ƿ�����������ְ��ʦ
		boolean isJZJS = false;
		//��ѧ�������������
		boolean isXLJKHD = false;
		//��ѧ��������������������ѯԤԼ�ǼǱ�
		boolean isXLZXYY = false;
		//˫�ܶ�̬��Ϣ
		boolean isSZDTXX = false;
		boolean isXSJBXX = false;
		boolean isXSZZQK = false;
		boolean isZXDK_XSXXCX = false;
		boolean isFSBZ = false;
		// int rightNd = Integer.parseInt(Base.currNd);
		List<HashMap<String, String>> ndList = Base.getXnndList();
		if (userType.equalsIgnoreCase("xx")) {
			request.setAttribute("isXX", "is");
		} else {
			request.setAttribute("isXX", "no");
		}
		if ("trainPrise".equalsIgnoreCase(dataType)) {
			jxhjlx = dataSearchForm.getGrhj();
			if ("bjhj".equalsIgnoreCase(jxhjlx)) {
				xh = "";
			} else if ("yxhj".equalsIgnoreCase(jxhjlx)) {
				xh = "";
				zy = "";
				bj = "";
			}
		}
		if (xh == null) {
			xh = "";
		}
		if (xy == null) {
			xy = "";
		}
		if (zy == null) {
			zy = "";
		}		
		if (dataType == null) {
			dataType = "";
		}
		if (userType.equalsIgnoreCase("xy")
				&& !dataType.equalsIgnoreCase("dormInfo")
				&& !dataType.equalsIgnoreCase("trainTime")
				&& !dataType.equalsIgnoreCase("workOutlay")) {
			xy = userDep;
			dataSearchForm.setXydm(xy);			
		}
		if(session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")){
			xy = Fdypd.fdybjck(userName,xy);
			dataSearchForm.setXydm(xy);
		}
		String tt = (String) request.getSession().getAttribute("userType");
		if ((xh != null) && !(xh.equalsIgnoreCase(""))) {
			if (dataType == "xlytqk" || "xlytqk".equalsIgnoreCase(dataType)) {
				querry.append(" and a.xh = '");
				querry.append(xh);
				querry.append("' ");
			} else if ("view_hcyhkbb".equalsIgnoreCase(tableName)
					|| "view_xhff".equalsIgnoreCase(tableName)
					|| "view_xszbb".equalsIgnoreCase(tableName)
					|| "view_xszsxx".equalsIgnoreCase(tableName)) {// ѧ��ס����Ϣ,edit by luojw 2010.11.6
				querry.append(" and xh like '%");
				querry.append(xh);
				querry.append("%' ");
			} else if (!("schoolBadgeAgain".equalsIgnoreCase(dataType)
					|| "oneCardAgain".equalsIgnoreCase(dataType)
					|| "studentPaperAgain".equalsIgnoreCase(dataType)
					|| "trainCheapAgain".equalsIgnoreCase(dataType))) {
				querry.append(" and xh like '%");
				querry.append(xh);
				querry.append("%' ");
			} else if (tt.equals("stu")) {
				querry.append(" and xh like '%");
				querry.append(xh);
				querry.append("%' ");
			}
		}
		if ((xm == null) || xm.equalsIgnoreCase("")) {
		} else {
			querry.append("and xm like '%");
			querry.append(xm);
			querry.append("%' ");
		}
		String csjg1 = DealString.toGBK(request.getParameter("csjg"));
		if("����".equals(csjg1) && Globals.XXDM_ZGKYDX.equals(xxdm)){
			csjg1 = "0002";
		}
		if("����".equals(csjg1) && Globals.XXDM_ZGKYDX.equals(xxdm)){
			csjg1 = "0001";
		}
		String csnj1 = DealString.toGBK(request.getParameter("csnj"));
		if(!"".equals(csjg1)){
			querry.append("and csjg like '%");
			querry.append(csjg1);
			querry.append("%' ");
		}
		if(!"".equals(csnj1)){
			querry.append("and csnj like '%");
			querry.append(csnj1);
			querry.append("%' ");
		}		
		if(Globals.XXDM_ZJLG.equals(xxdm) && "view_xytbgxxsxx".equals(tableName)){
			String xlcslb = request.getParameter("xlcslb");
			String xlwtlx = request.getParameter("xlwtlx");
			String sfkns = request.getParameter("sfkns");
			String sfdq = request.getParameter("sfdq");
			if(StringUtils.isNotNull(xlcslb)){
				querry.append("and xlcslb like '%");
				querry.append(xlcslb);
				querry.append("%' ");
			}
			if(StringUtils.isNotNull(xlwtlx)){
				querry.append("and xlwtlx like '%");
				querry.append(xlwtlx);
				querry.append("%' ");
			}
			if(StringUtils.isNotNull(sfkns)){
				querry.append("and sfkns like '%");
				querry.append(sfkns);
				querry.append("%' ");
			}
			if(StringUtils.isNotNull(sfdq)){
				querry.append("and sfdq like '%");
				querry.append(sfdq);
				querry.append("%' ");
			}
		}
		
		//��ְ��ʦ��ѯ
		if ("jzjsxx".equals(dataType)) {
			String cxdw = DealString.toGBK(request.getParameter("szdw"));
			String cxzw = DealString.toGBK(request.getParameter("zwzc"));
			String cxsj = DealString.toGBK(request.getParameter("sjhm"));
			if ((cxdw == null) || cxdw.equalsIgnoreCase("")) {
			} else {
				sql = "select DISTINCT xymc from view_njxyzybj where xydm=?";
				String[] rs1 = dao.getOneRs(sql, new String[] { cxdw },
						new String[] { "xymc" });

				querry.append("and xymc like '%");
				querry.append(rs1[0]);
				querry.append("%' ");
			}
			if ((cxzw == null) || cxzw.equalsIgnoreCase("")) {
			} else {
				querry.append("and zwzc like '%");
				querry.append(cxzw);
				querry.append("%' ");
			}
			if ((cxsj == null) || cxsj.equalsIgnoreCase("")) {
			} else {
				querry.append("and sjhm like '%");
				querry.append(cxsj);
				querry.append("%' ");
			}
		}
		//���｡���������ѯ
		if ("xljkjyhd".equals(dataType)) {
			String cxzt = DealString.toGBK(request.getParameter("zt"));
			String cxxy = DealString.toGBK(request.getParameter("xy"));
			String cxdd = DealString.toGBK(request.getParameter("dd"));
			String cxhdrq = DealString.toGBK(request.getParameter("hdrq"));
			if ((cxzt == null) || cxzt.equalsIgnoreCase("")) {
			} else {
				querry.append("and zt like '%");
				querry.append(cxzt);
				querry.append("%' ");
			}
			if ((cxxy == null) || cxxy.equalsIgnoreCase("")) {
			} else {
				sql = "select DISTINCT xymc from view_njxyzybj where xydm=?";
				String[] rs1 = dao.getOneRs(sql, new String[] { cxxy },
						new String[] { "xymc" });
				querry.append("and xy like '%");
				querry.append(rs1[0]);
				querry.append("%' ");
			}
			if ((cxdd == null) || cxdd.equalsIgnoreCase("")) {
			} else {
				querry.append("and dd like '%");
				querry.append(cxdd);
				querry.append("%' ");
			}
			if ((cxhdrq == null) || cxhdrq.equalsIgnoreCase("")) {
			} else {
				querry.append("and hdrq like '%");
				querry.append(cxhdrq);
				querry.append("%' ");
			}
		}
		//˫�ܶ�̬��Ϣ��ѯ
		if ("szdtxx".equals(dataType)) {
			String cxdtxy = DealString.toGBK(request.getParameter("xymc"));
			String cxdtsj = DealString.toGBK(request.getParameter("dtsj"));
//			String xn1 = DealString.toGBK(request.getParameter("xn"));
//			String xq1 = DealString.toGBK(request.getParameter("xq"));
			String zc = DealString.toGBK(request.getParameter("zc"));
			if ((cxdtxy == null) || cxdtxy.equalsIgnoreCase("")) {
			} else {
				querry.append("and xymc like '%");
				querry.append(cxdtxy);
				querry.append("%' ");
			}
			if ((cxdtsj == null) || cxdtsj.equalsIgnoreCase("")) {
			} else {
				querry.append("and dtsj like '%");
				querry.append(cxdtsj);
				querry.append("%' ");
			}

			if ((zc != null) || !zc.equalsIgnoreCase("")) {
				querry.append("and zc like '%");
				querry.append(zc);
				querry.append("%' ");
				Map zcmap = new HashMap();
				zcmap.put("zc",zc);
				request.setAttribute("zcmap", zcmap);
			}
		}

		if("xlytqk".equals(dataType)){
			String cxdtsj = DealString.toGBK(request.getParameter("dtsj"));
			if ((cxdtsj == null) || cxdtsj.equalsIgnoreCase("")) {
			} else {
				querry.append("and dtsj like '%");
				querry.append(cxdtsj);
				querry.append("%' ");
			}
		}

		if ((nj == null) || nj.equalsIgnoreCase("")) {

		} else {
			querry.append(" and nj = '");
			querry.append(nj);
			querry.append("' ");
		}
		if (!StringUtils.isNull(nd)) {
			if("view_xlcsjg".equals(tableName)){                                                                                                                                                                                                                                                                                                                                                                                                              
				querry.append(" and substr(cssj,0,4) = '");
				querry.append(nd);
				querry.append("' ");
			}else{
				querry.append(" and nd = '");
				querry.append(nd);
				querry.append("' ");
			}
		}
		if (!StringUtils.isNull(kh)) {
			querry.append(" and kh = '");
			querry.append(kh);
			querry.append("' ");
		}
		if (!StringUtils.isNull(xxsh)) {
			querry.append(" and xxsh = '");
			querry.append(xxsh);
			querry.append("' ");
		}
		if ((xn == null) || xn.equalsIgnoreCase("")) {

		} else {
			querry.append(" and xn = '");
			querry.append(xn);
			querry.append("' ");
		}
		if ((xq == null) || xq.equalsIgnoreCase("")) {

		} else {
			querry.append(" and xq = '");
			querry.append(xq);
			querry.append("' ");
		}
		if ((sfzh == null) || "".equalsIgnoreCase(sfzh)) {

		} else if (!(dataType.equalsIgnoreCase("discipInfo"))) {
			querry.append(" and sfzh like '%");
			querry.append(sfzh);
			querry.append("%' ");
		}
		if ((zy == null) || zy.equalsIgnoreCase("")) {

		} else {
			querry.append(" and zydm = '");
			querry.append(zy);
			querry.append("' ");
		}
		if ((bj == null) || bj.equalsIgnoreCase("")||bj.equalsIgnoreCase("null")) {

		} else {
			querry.append(" and bjdm = '");
			querry.append(bj);
			querry.append("' ");
		}
		if ((xmdm == null) || xmdm.equalsIgnoreCase("")) {

		} else {
			if (tableName.equalsIgnoreCase("view_dwjlsq")) {
				querry.append("and jlxmdm = '");
				querry.append(xmdm);
				querry.append("' ");
			}
		}
		if ((hth == null) || ("".equalsIgnoreCase(hth))) {

		} else {
			querry.append(" and hth = '");
			querry.append(hth);
			querry.append("'");
		}
		if ((bzffny == null) || ("".equalsIgnoreCase(bzffny))) {

		} else {
			querry.append(" and bzffny = '");
			querry.append(bzffny);
			querry.append("'");
		}
		if ((xy == null) || xy.equalsIgnoreCase("")||xy.equalsIgnoreCase("null")||"szdtxx".equals(dataType)
				||"xljkjyhd".equals(dataType)||"jzjsxx".equals(dataType)) {

		} else {
			querry.append(" and xydm = '");
			querry.append(xy);
			querry.append("' ");
		}
		if ((xjzt == null) || xjzt.equalsIgnoreCase("")) {

		} else {
			querry.append(" and xjzt = '");
			querry.append(xjzt);
			querry.append("' ");
		}
		if (((nd == null) || nd.equalsIgnoreCase(" "))
				|| ((ndVar == null) || ("".equals(ndVar.trim())))) {

		} else if ((nd != null && !nd.trim().equals(""))
				|| ((ndVar != null) && !("".equals(ndVar.trim())))) {
			querry.append(" and nd='");
			querry.append(nd);
			querry.append("' ");
		}
		if (tableName != null && tableName.equalsIgnoreCase("view_xshsxf")) {
			if ((nf == null) || nf.equalsIgnoreCase(" ")) {

			} else if (nf != null && !nf.trim().equals("")) {
				querry.append(" and nf='");
				querry.append(nf);
				querry.append("' ");
			}
			if ((yf == null) || yf.equalsIgnoreCase(" ")) {

			} else if (yf != null && !yf.trim().equals("")) {
				querry.append(" and yf='");
				querry.append(yf);
				querry.append("' ");
			}
		}
		
		if ("view_wjcf".equalsIgnoreCase(tableName)) {
			if (StringUtils.isNotNull(xsqr)) {
				querry.append(" and xsqr = '");
				querry.append(xsqr);
				querry.append("'");
			}
			//�Ƿ���
		}
		
		if ("view_xytbgxxsxx".equalsIgnoreCase(tableName)) {
			if (!Base.isNull(lydm) && !"".equalsIgnoreCase(lydm)) {
				querry.append(" and lydm = '");
				querry.append(lydm);
				querry.append("'");
			}
		}

		if(session.getAttribute("fdyQx").toString().equalsIgnoreCase("true") && !dataType.equalsIgnoreCase("workOutlay")){
			//����Ա��¼
			querry.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='" + userName + "')");
		}	

		if (dataType.equalsIgnoreCase("train")) {
			realTable = "xspxxxb";
			pk = "xn||xq||xh||pxxmdm";
			tips = "˼����� - ����ά�� - ��ѵ��Ϣ";
			tableName = "view_xspxxx";
			colList = new String[] { "����", "nd", "xn", "xq", "xh", "xm", "nj",
					"bjmc", "pxxmmc", "pxjg" };
		} else if (dataType.equalsIgnoreCase("active")) {
			realTable = "rdjjfzxxb";
			pk = "xn||xq||xh";
			tips = "˼����� - ����ά�� - �뵳��������";
			tableName = "view_rdjjfzxx";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				colList = new String[] { "����", "nd", "xn", "xq", "xh", "xm",
						"nj", "bjmc", "sfzh", "csrq", "rzqk", "kssj", "tjdw",
						"lxr1", "lxr2" };
			} else {
				colList = new String[] { "����", "nd", "xn", "xqmc", "xh", "xm",
						"nj", "bjmc", "kssj" };
			}
			// ================2009/9/24 luojw begin=====================
			if (Globals.XXDM_JHZYJSXY.equalsIgnoreCase(xxdm)) {
				String kssj = dataSearchForm.getGzkssj();
				String jssj = dataSearchForm.getGzjssj();
				if (!Base.isNull(kssj) && !Base.isNull(jssj)) {
					querry.append(" and kssj >='");
					querry.append(kssj);
					querry.append("'and kssj <='");
					querry.append(jssj);
					querry.append("'");
				} else if (!Base.isNull(kssj)) {
					querry.append(" and kssj >='");
					querry.append(kssj);
					querry.append("'");
				} else if (!Base.isNull(jssj)) {
					querry.append(" and kssj <='");
					querry.append(jssj);
					querry.append("'");
				}
			}
			// ================2009/9/24 luojw end=====================
		} else if (dataType.equalsIgnoreCase("prepare")) {
			realTable = "ybdyxxb";
			pk = "xn||xq||xh";
			tips = "˼����� - ����ά�� - Ԥ����Ա";
			tableName = "view_ybdyxx";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				colList = new String[] { "����", "nd", "xn", "xq", "xh", "xm",
						"nj", "bjmc", "sfzh", "csrq", "rzqk", "lxr1", "lxr2",
						"kssj", "jssj" };
			} else {
				colList = new String[] { "����", "nd", "xn", "xqmc", "xh", "xm",
						"nj", "bjmc", "kssj", "jssj" };
			}
		} else if (dataType.equalsIgnoreCase("party")) {
			realTable = "dyxxb";
			pk = "xn||xq||xh";
			tips = "˼����� - ����ά�� - ��Ա��Ϣ";
			tableName = "view_dyxx";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				colList = new String[] { "����", "nd", "xn", "xq", "xh", "xm",
						"nj", "bjmc", "rdsj", "sfzh", "csrq", "rzqk" };
			}else {
				colList = new String[] { "����", "nd", "xn", "xqmc", "xh", "xm",
						"nj", "bjmc", "rdsj" };
			}
		} else if (dataType.equalsIgnoreCase("prise")) {
			
			realTable = "xsjxjb";
			pk = "xn||nd||jxjdm||xh";
			tips = "�������� - ��ѧ������ - ��������ѯ";
			tableName = "view_xsjxjb";
			if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
				colList = new String[] { "����", "jxjlb", "nd", "xn", "xh", "xm",
						"bjmc", "xq", "jxjmc","cjmc","zhpfmc","fdyqm","xyqm", "xysh", "xxsh" };
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)) {
				colList = new String[] { "����", "jxjlb", "nd", "xn", "xh", "xm",
						"bjmc", "jxjdm", "jxjmc", "fdysh","xysh", "xxsh"};

			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				String shzt = request.getParameter("shzt");
				shzt = StringUtils.isNull(shzt) ? "" : shzt;
				if (StringUtils.isEqual("0", shzt)) {
					querry.append(" and fdysh='ͨ��' and xysh='ͨ��' and xxsh='ͨ��' ");
				}else if (StringUtils.isEqual("1", shzt)) {
					querry.append(" and fdysh='��ͨ��' and xysh='��ͨ��' and xxsh='��ͨ��' ");
				} else if (StringUtils.isEqual("2", shzt)) {
					querry.append(" and fdysh='δ���' and xysh='δ���' and xxsh='δ���' ");
				}
				colList = new String[] { "����", "jxjlb", "nd", "xn", "xh", "xm",
						"bjmc", "jxjmc", "jxjlb","fdysh", "xysh", "xxsh" };
				/*if (!StringUtils.isNull(tab) && StringUtils.isEqual(tab, "qtjxj")) {
					colList = new String[] { "����", "jxjlb", "nd", "xn", "xh", "xm",
							"bjmc", "jxjdm", "jxjmc", "fdysh", "xysh", "xxsh", "tjflag" };
				}//������ѧ��
				else{
					return new ActionForward("/jxjsqqrydefault.do",false);
				}//��άѧ��*/
				String jxjlb = request.getParameter("jxjlb");

//				String tmpbjdm = request.getParameter("bjdm");
				if (!StringUtils.isNull(jxjlb) && StringUtils.isEqual(jxjlb, "1")) {
					querry.append(" and jxjxydm='");
					querry.append(dao.getOneRs("select xgbdm from xtszb", new String[]{}, "xgbdm"));
					querry.append("' ");
				} else if (!StringUtils.isNull(jxjlb) && StringUtils.isEqual(jxjlb, "2")) {
					querry.append(" and jxjxydm='");
					querry.append(userDep);
					querry.append("' ");
				} else {
					querry.append(" and 1=1 ");
				}
				if (StringUtils.isEqual("true", isFdy)) {

					querry.append(" and exists(select 1 from view_fdybbj c where a.bjdm=c.bjdm and c.zgh='"+userName+"')");

				}

			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {
				colList = new String[] { "����", "jxjlb", "nd", "xn", "xh", "xm",
						"bjmc", "jxjdm", "jxjmc","fdysh", "xysh", "xxsh"};
				if(isFdy!=null && isFdy.equalsIgnoreCase("true")){//����Աֻ�ܲ�ѯ���Լ������༶��Ϣ
					querry.append(" and exists(select 1 from view_fdybbj c where a.bjdm=c.bjdm and c.zgh='"+userName+"')");
				}
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){
				//�人����ѧ

				colList = new String[] { "����", "jxjlb", "nd", "xn", "xh", "xm",
						"bjmc", "jxjdm", "jxjmc","fdysh", "xysh", "xxsh" };
				String xmfl = DealString.toGBK(request.getParameter("xmfl"));
				if(xmfl!=null && !xmfl.equalsIgnoreCase("")){
					querry.append(" and jxjfl = '");
					querry.append(xmfl);
					querry.append("' ");
				}
				if(isFdy!=null && isFdy.equalsIgnoreCase("true")){//����Աֻ�ܲ�ѯ���Լ������༶��Ϣ
					querry.append(" and exists(select 1 from view_fdybbj c where a.bjdm=c.bjdm and c.zgh='"+userName+"')");
				}

			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {//�㽭����
				return new ActionForward ("/pjpy_zjjd_jxjsqqry.do", false);
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
				return new ActionForward("/pjpy_ynys_jxjsqqry.do", false);
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SHCBYSGDZKXX)) {
				colList = new String[] { "����", "jxjlb", "nd", "xn", "xh", "xm",
						"bjmc", "jxjdm", "jxjmc", "fdysh", "xysh", "xxsh" };
				if(isFdy!=null && isFdy.equalsIgnoreCase("true")){//����Աֻ�ܲ�ѯ���Լ������༶��Ϣ
					querry.append(" and exists(select 1 from view_fdybbj c where a.bjdm=c.bjdm and c.zgh='"+userName+"')");
				}
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)) {
				colList = new String[] { "����", "jxjlb", "xn", "xh", "xm",
						"bjmc", "jxjdm", "jxjmc", "xysh", "xxsh"};
			} else if (Globals.XXDM_ZGKYDX.equalsIgnoreCase(xxdm)) {
				colList = new String[] { "����", "jxjlb", "xh", "xn", "nd", "xm",
						"bjmc", "jxjdm", "jxjmc", "xysh", "xxsh" };
			} else if (Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)) {
				return new ActionForward("/pjpy_nbzy_jxjqry.do", false);
			} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
				colList = new String[] { "����", "jxjlb", "nd", "xn", "xh", "xm",
						"bjmc", "jxjmc", "fdysh", "xysh", "xxsh" };
				if (StringUtils.isEqual("true", isFdy)) {
					querry.append(" and exists(select 1 from view_fdybbj c where a.bjdm=c.bjdm and c.zgh='"+userName+"')");
				}
			} else if (Globals.XXDM_SCJZZYJSXY.equalsIgnoreCase(xxdm)) {
				colList = new String[] { "����", "jxjlb", "nd", "xn", "xh", "xm",
						"bjmc", "jxjmc", "fdysh","xysh", "xxsh" };
				if (StringUtils.isEqual("true", isFdy)) {
					querry.append(" and exists(select 1 from view_fdybbj c where a.bjdm=c.bjdm and c.zgh='"+userName+"')");
				}
			} else if (Globals.XXDM_XMLGXY.equalsIgnoreCase(xxdm)) {//������ѧԺ
				return new ActionForward("/pjpy_xmlg_jxjsqQuery.do", false);
			} else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {//������Ϣ
				return new ActionForward("/pjpy_czxx_jxjQuery.do", false);
			} else{
//				String tmpbjdm = request.getParameter("bjdm");
				colList = new String[] { "����", "jxjlb", "nd", "xn", "xh", "xm",
						"bjmc", "jxjmc", "xysh", "xxsh" };
				if (StringUtils.isEqual("true", isFdy)) {
					querry.append(" and exists(select 1 from view_fdybbj c where a.bjdm=c.bjdm and c.zgh='"+userName+"')");
				}
			}
			String jxjdm = request.getParameter("jxjdm");
			if (jxjdm != null && !(jxjdm.trim().length() < 1)) {
				querry.append(" and jxjdm='");
				querry.append(jxjdm);
				querry.append("' ");
			}
			// /List<HashMap<String, String>> temmap = new
			// ArrayList<HashMap<String,String>>();
			List<HashMap<String, String>> xmlist = dao.getList(
					"select distinct jxjdm,jxjmc from jxjdmb order by jxjdm",
					new String[] {}, new String[] { "jxjdm", "jxjmc" });
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				if (StringUtils.isEqual(userType, "xy")) {
					String jxjlb = request.getParameter("jxjlb");
					if (StringUtils.isNull(jxjlb) || StringUtils.isEqual(jxjlb, "1")) {
						xmlist = dao.getList("select distinct jxjdm,jxjmc from jxjdmb where xydm=? order by jxjdm", new String[]{dao.getOneRs("select xgbdm from xtszb", new String[]{}, "xgbdm")}, new String[]{"jxjdm", "jxjmc"});
					} else {
						xmlist = dao.getList("select distinct jxjdm,jxjmc from jxjdmb where xydm=? order by jxjdm", new String[]{session.getAttribute("userDep").toString()}, new String[]{"jxjdm", "jxjmc"});
					}
				}
				if ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)) {
					String jxjsql = "select jxjdm,jxjmc from jxjdmb where 1=1 ";
					if (!StringUtils.isNull(request.getParameter("xydm"))) {
						jxjsql += " and xydm='" + request.getParameter("xydm") + "'";
					}
					xmlist = dao.getList(jxjsql, new String[]{}, new String[]{"jxjdm", "jxjmc"});
				}
			}
			
			String xysh = request.getParameter("xysh");
			
			if (!Base.isNull(xysh)) {
				querry.append(" and xysh='");
				querry.append(xysh);
				querry.append("' ");
			}
			if (!Base.isNull(xxsh)) {
				querry.append(" and xxsh='");
				querry.append(xxsh);
				querry.append("' ");
			}
			request.setAttribute("shList", dao.getChkList(3));
			request.setAttribute("xmlist", xmlist);
			request.setAttribute("xsjxjb", "xsjxjb");
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJDX)) {
				request.setAttribute("zjujxjrych", "zjujxjrych");
			}
			request.setAttribute("xsjxj", "xsjxj");
			
			
		} else if (dataType.equalsIgnoreCase("credit")) {
			String stab = request.getParameter("stab");
			realTable = "xsrychb";
			pk = "xn||nd||rychdm||xh";
			tips = "�������� - �����ƺ����� - ��������ѯ";
			tableName = "view_xsrychb";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY)) {
				colList = new String[] { "����", "nd", "xn", "xh", "xm", "bjmc",
						"rychmc", "fdysh","xysh", "xxsh" };
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {//���ս�����ҵ
				colList = new String[] { "����", "nd", "xn", "xh", "xm", "bjmc",
						"rychmc","xysh", "xxsh" };
				if(isFdy!=null && isFdy.equalsIgnoreCase("true")){//����Աֻ�ܲ�ѯ���Լ������༶��Ϣ
					querry.append(" and exists(select 1 from view_fdybbj c where a.bjdm=c.bjdm and c.zgh='"+userName+"')");
				}
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				colList = new String[] { "����", "nd", "xn", "xh", "xm", "bjmc",
						"rychmc", "jxjlb","fdysh","xysh", "xxsh" };
				String jxjlb = request.getParameter("jxjlb");
//				String tmpbjdm = request.getParameter("bjdm");
				String shzt = request.getParameter("shzt");
				shzt = StringUtils.isNull(shzt) ? "" : shzt;
				if (StringUtils.isEqual("0", shzt)) {
					querry.append(" and fdysh='ͨ��' and xysh='ͨ��' and xxsh='ͨ��' ");
				}else if (StringUtils.isEqual("1", shzt)) {
					querry.append(" and fdysh='��ͨ��' and xysh='��ͨ��' and xxsh='��ͨ��' ");
				} else if (StringUtils.isEqual("2", shzt)) {
					querry.append(" and fdysh='δ���' and xysh='δ���' and xxsh='δ���' ");
				}
				if (!StringUtils.isNull(jxjlb) && StringUtils.isEqual(jxjlb, "1")) {
					querry.append(" and jxjxydm='");
					querry.append(dao.getOneRs("select xgbdm from xtszb", new String[]{}, "xgbdm"));
					querry.append("' ");
				} else if (!StringUtils.isNull(jxjlb) && StringUtils.isEqual(jxjlb, "2")) {
					querry.append(" and jxjxydm='");
					querry.append(userDep);
					querry.append("' ");
				} else {
					querry.append(" and 1=1 ");
				}
				if (StringUtils.isEqual("true", isFdy)) {
					querry.append(" and exists(select 1 from view_fdybbj c where a.bjdm=c.bjdm and c.zgh='"+userName+"')");
				}
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){//�人����ѧ

				colList = new String[]{ "����", "nd", "xn", "xh", "xm", "bjmc", "rychmc","fdysh","xysh","xxsh"};
				String xmfl = DealString.toGBK(request.getParameter("xmfl"));
				if(xmfl!=null && !xmfl.equalsIgnoreCase("")){
					querry.append(" and rychfl ='");
					querry.append(xmfl);
					querry.append("' ");
				}
				if(isFdy!=null && isFdy.equalsIgnoreCase("true")){//����Աֻ�ܲ�ѯ���Լ������༶��Ϣ
					querry.append(" and exists(select 1 from view_fdybbj c where a.bjdm=c.bjdm and c.zgh='"+userName+"')");
				}
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				colList = new String[] { "����", "nd", "xn","xq", "xh", "xm", "bjmc",
						"rychmc","cjmc","zhpfmc","zysj","fdyqm","xyqm", "xysh", "xxsh" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)) {
				colList = new String[] { "����", "xn", "xh", "xm", "bjmc",
						"rychmc", "xysh", "xxsh" };
			} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
				colList = new String[] { "����", "nd", "xn", "xh", "xm", "bjmc",
						"rychmc", "fdysh", "xysh", "xxsh" };
				if (StringUtils.isEqual("true", isFdy)) {
					querry.append(" and exists(select 1 from view_fdybbj c where a.bjdm=c.bjdm and c.zgh='"+userName+"')");
				}
			} else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {
				return new ActionForward("/pjpy_czxx_rychQuery.do", false);
			} else {
				colList = new String[] { "����", "nd", "xn", "xh", "xm", "bjmc",
						"rychmc", "xysh", "xxsh" };
//				String tmpbjdm = request.getParameter("bjdm");
				if (StringUtils.isEqual("true", isFdy)) {
					querry.append(" and exists(select 1 from view_fdybbj c where a.bjdm=c.bjdm and c.zgh='"+userName+"')");
				}
			}
			String rychdm = request.getParameter("rychdm");
			if (rychdm != null && !(rychdm.trim().length() < 1)) {
				querry.append(" and rychdm='");
				querry.append(rychdm);
				querry.append("' ");
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_JGSDX)) {//����ɽ��ѧ�������
				querry.append(" and rychmc <> '������ѧ��' and rychmc <> '����ѧ��' and rychmc <> '����ѧ�����' and rychmc <> '����ѧ���ɲ�' and rychmc <> '�����ҵ��' ");
			}
			List<HashMap<String, String>> xmlist = dao
			.getList(
					"select distinct RYCHDM,RYCHMC from rychdmb order by rychdm",
					new String[] {},
					new String[] { "rychdm", "rychmc" });
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				if (StringUtils.isEqual(userType, "xy")) {
					String jxjlb = request.getParameter("jxjlb");
					if (StringUtils.isNull(jxjlb) || StringUtils.isEqual(jxjlb, "1")) {
						xmlist = dao.getList("select distinct rychdm,rychmc from rychdmb where xydm=? order by rychdm", new String[]{dao.getOneRs("select xgbdm from xtszb", new String[]{}, "xgbdm")}, new String[]{"rychdm", "rychmc"});
					} else {
						xmlist = dao.getList("select distinct rychdm,rychmc from rychdmb where xydm=? order by rychdm", new String[]{session.getAttribute("userDep").toString()}, new String[]{"rychdm", "rychmc"});
					}
				}
				if ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)) {
					String jxjsql = "select rychdm,rychmc from rychdmb where 1=1 ";
					if (!StringUtils.isNull(request.getParameter("xydm"))) {
						jxjsql += " and xydm='" + request.getParameter("xydm") + "'";
					}
					xmlist = dao.getList(jxjsql, new String[]{}, new String[]{"rychdm", "rychmc"});
				}

//				if (!StringUtils.isNull(isFdy) && StringUtils.isEqual(isFdy, "true")) {
//				//querry.append("");
//				}
			}
			
			String xysh = request.getParameter("xysh");

			if (!Base.isNull(xysh)) {
				querry.append(" and xysh='");
				querry.append(xysh);
				querry.append("' ");
			}
			if (!Base.isNull(xxsh)) {
				querry.append(" and xxsh='");
				querry.append(xxsh);
				querry.append("' ");
			}
			request.setAttribute("shList", dao.getChkList(3));
			request.setAttribute("xmlist", xmlist);
			request.setAttribute("xsrychb", "xsrychb");
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJDX)) {
				request.setAttribute("zjujxjrych", "zjujxjrych");
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)){//���ս�����ҵѧԺ
				if (!StringUtils.isNull(stab) && stab.equalsIgnoreCase("stab")) {

				}else{
					return new ActionForward("/pjpy_ahjg_rychjgcxmr.do",false);
				}
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_JGSDX)) {//����ɽ��ѧ
				if (!StringUtils.isNull(stab) && stab.equalsIgnoreCase("stab")) {

				}else{
					return new ActionForward("/pjpy_jgsdx_rychcxdefault.do",false);
				}
			}
			request.setAttribute("xsrych", "xsrych");
		} else if (dataType.equalsIgnoreCase("integrate")) {
			realTable = "jszhkpb";
			pk = "xn||xq||xh";
			tips = "�������� - ��Ϣά�� - ��ʵ�ۺϿ���";
			tableName = "view_jszhkp";
			colList = new String[] { "����", "nd", "xn", "xq", "xh", "xm",
					"sxpdpj", "zssppj", "xynlpj", "nlpj", "szpj", "xf" };
		} else if (dataType.equalsIgnoreCase("trainPrise")) {
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)
					|| xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)) {
				request.setAttribute("showhzyjx", "showhzyjx");
				if ("".equalsIgnoreCase(dataSearchForm.getGrhj())
						|| dataSearchForm.getGrhj() == null) {
					dataSearchForm.setGrhj("grhj");
				}
				jxhjlx = dataSearchForm.getGrhj();
			}
			if ("grhj".equalsIgnoreCase(jxhjlx) || "".equalsIgnoreCase(jxhjlx)
					|| jxhjlx == null) {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_YCWSZYJSXY)) {
					return new ActionForward("/pjpy_ycws_jxhjwh.do", false);
				}
				realTable = "xsjxhjb";
				pk = "xh||strtodatetime(hjsj)||jxdm";
				tips = "��ѵ���� - ��ѵ���� - ��ѵ�Ŷӻ�";
				tableName = "view_xsjxhj";
				colList = new String[] { "����", "nd", "xn", "xq", "xh", "xm",
						"bjmc", "jxmc", "hjsj" };
			} else if ("bjhj".equalsIgnoreCase(jxhjlx)) {
				realTable = "bjjxhjb";
				pk = "bjdm||strtodatetime(hjsj)||jxdm";
				tips = "��ѵ���� - ��ѵ���� - ��ѵ�Ŷӻ�";
				tableName = "view_bjjxhj";
				colList = new String[] { "����", "nd", "xn", "xq", "nj", "xymc",
						"zymc", "bjmc", "jxmc", "hjsj" };
				request.setAttribute("bjhjType", "bjhj");
				request.setAttribute("xh", "");
			} else if ("yxhj".equalsIgnoreCase(jxhjlx)) {
				realTable = "yxjxhjb";
				pk = "xydm||strtodatetime(hjsj)||jxdm";
				tips = "��ѵ���� - ��ѵ���� - ��ѵ�Ŷӻ�";
				tableName = "view_yxjxhj";
				colList = new String[] { "����", "nd", "xn", "xq", "xymc",
						"jxmc", "hjsj" };
				request.setAttribute("bjhjType", "bjhj");
				request.setAttribute("xyhjType", "xyhj");
				request.setAttribute("xh", "");
				request.setAttribute("zydm", "");
				request.setAttribute("bjdm", "");
			}
		} else if (dataType.equalsIgnoreCase("zhsz")) {
			if (Globals.XXDM_WHLGDXHXXY.equalsIgnoreCase(xxdm)) {
				return new ActionForward("/pjpy_whlghxxy_zhszcpwh.do", false);
			} else if (Globals.XXDM_ZGKYDX.equalsIgnoreCase(xxdm)) {
				return new ActionForward("/pjpy_zgkd_zhszcpwh.do", false);
			/*} else if (Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
				return new ActionForward("/pjpy_nblg_zhszcpwh.do", false);*/ //lyl
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_NBZYJSXY)) {
				return new ActionForward("/pjpy_nbzy_zhszcp.do", false);
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)) {
				return new ActionForward("/pjpy_xnmz_zhszcp.do", false);
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HBSFXY)) {// ����ʦ��ѧԺ�ۺ�����ά��
				return new ActionForward("/pjpy_hbsf_zhszcp.do", false);
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)) {// �人����ѧ�ۺ����ʲ���ά��
				return new ActionForward("/pjpy_whlgdx.do?method=zhszcpSearch",
						false);
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {// �㽭����
				return new ActionForward("/pjpy_zjjd_zhszcpwh.do", false);
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
				return new ActionForward("/pjpy_ynys_zhszcpwh.do", false);
			} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
				return new ActionForward("/pjpy_zjcm_zhszcpwh.do", false);
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				return new ActionForward("/pjpy_hzy_zhszcpdefault.do", false);
				/*
				 * userDep =
				 * request.getSession().getAttribute("userDep").toString();
				 * userType = dao.getUserType(userDep); if
				 * (!"xy".equalsIgnoreCase(userType)) {
				 * request.setAttribute("showzdjs", "showzdjs");
				 * request.setAttribute("showblsz", "showblsz"); } realTable =
				 * "zhszcp"; pk = "xn||xh||xq"; tips = "�������� - ��Ϣά�� - �ۺ����ʲ���"; //
				 * �Ƿ�ʹ��view_zhszcp��ͼ tableName = "view_zhszcp"; colList = new
				 * String[] { "����", "nd", "xn", "xq", "xh", "xm", "bjmc", "dcj",
				 * "zcj", "tcj", "gzxxcx", "cpzf" };
				 */
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {
				userDep = request.getSession().getAttribute("userDep").toString();
				userType = dao.getUserType(userDep);
				request.setAttribute("showjsxx", "showjsxx");
				request.setAttribute("showzdjs", "showzdjs");
				realTable = "zhszcp";
				pk = "xn||xh||xq";
				tips = "�������� - ��Ϣά�� - �ۺ����ʲ���";
				// �Ƿ�ʹ��view_zhszcp��ͼ
				tableName = "view_zhszcp";
				colList = new String[] { "����", "nd", "xn", "xq", "xh", "xm",
						"bjmc", "dcj", "zcj", "tcj", "cpzf", "bjpm", "cxdj" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZBDX)) {
				realTable = "zhszcp";
				userType = dao.getUserType(userDep);
				pk = "xn||xh";
				tips = "�������� - ��Ϣά�� - �ۺ����ʲ���";
				tableName = "view_zhszcp";
				colList = new String[] { "����", "nd", "xn", "xq", "xh", "xm",
						"bjmc", "dcj", "zcj", "tcj", "cxysjszf", "rwszf",
				"zhszcpzf" };
				if ((StandardOperation.getAutoGenZhcp().equalsIgnoreCase("1") && ("xy"
						.equalsIgnoreCase(userType)))
						|| ("xx".equalsIgnoreCase(userType))) {
					request.setAttribute("showzdjs", "showzdjs");
					request.setAttribute("showzbdx_xx", "showzbdx_xx");
					request.setAttribute("xxdm", Globals.XXDM_ZBDX);
				}
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)) {//�㶫����ѧԺ
				request.setAttribute("isgdby", "yes");
				realTable = "zhszcp";
				pk = "xn||xh";
				tips = "�������� - ��Ϣά�� - �ۺ����ʲ���";
				tableName = "view_zhszcp";
				colList = new String[] { "����", "nd", "xn", "xq", "xh",
						"xm", "bjmc", "cxcj" };
			/*} else if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				realTable = "zhszcp";
				pk = "xn||xh";
				tips = "�������� - ��Ϣά�� - �ۺ����ʲ���";
				tableName = "view_zhszcp";
				colList = new String[] { "����", "nd", "xn", "xq", "xh",
						"xm", "bjmc","ddcj","xwcj","shqcj", "kcjqpfj", "zhszcpzf" };*/ //lyl
			} else if(xxdm.equalsIgnoreCase(Globals.XXDM_YCSFXY)){
				return new ActionForward("/pjpy_ycsf_zhszcpwh.do");
				
			} else if (Globals.XXDM_XMLGXY.equalsIgnoreCase(xxdm)) {//�������ۺϲ���
				return new ActionForward("/pjpy_xmlg_zhszcpwhDefault.do", false);
			} else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {//���ݴ�ѧ���ۺϲ���
				return new ActionForward("/pjpy_gzdx_zhszcpwh.do", false);
			} else if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {//������Ϣ�ۺϲ���
				return new ActionForward("/pjpy_czxx_zhszcpwh.do", false);
			} else {
				realTable = "zhszcp";
				pk = "xn||xh";
				tips = "�������� - ��Ϣά�� - �ۺ����ʲ���";
				tableName = "view_zhszcp";
				if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)) {
					colList = new String[] { "����", "xn", "xqmc", "xh",
							"xm", "bjmc", "dcj","dcjpm", "zcj", "zcjpm","tcj","tcjpm", "jnjf","jnjfpm", "cpzf","cpzfpm" };
				} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZZSF)) {
					// new ZzsfPjpyDAO().autoCalcZhcp(request);
					querry.append(" order by zhszcpzf desc");
					colList = new String[] { "����", "nd", "xn", "xq", "xh",
							"xm", "bjmc", "dcj", "zcj", "znszcj", "tcj",
							"jnjf", "zhszcpzf", "bjpm", "njpm" };
				} else if (xxdm.equalsIgnoreCase(Globals.XXDM_NBTYZYJSXY)){
					colList = new String[] { "����", "nd", "xn", "xh",
							"xm", "bjmc", "dcj", "zcj", "tcj" ,"zpf"};
					request.setAttribute("view", "no");
				}else {
					colList = new String[] { "����", "nd", "xn", "xqmc", "xh",
							"xm", "bjmc", "dcj", "zcj", "tcj" ,"zpf"};
				}
			}

			//ѧ���û���Ȩ�����ҳ��
			String uType = request.getSession().getAttribute("userType").toString();
			if (GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(uType)
					|| GlobalsVariable.XTDM_STU.equalsIgnoreCase(uType)) {
				request.setAttribute("message", "����Ȩ���ʸ�ҳ��!");
				return new ActionForward("/prompt.do",false);
			}
			
		} else if (dataType.equalsIgnoreCase("needer")) {
			realTable = "knssqb";
			pk = "xh||xn";
			tips = "ѧ������ - ��Ϣά�� - ��������Ϣ";
			tableName = "view_knsxx";
			colList = new String[] { "����", "nd", "xn", "xq", "xh", "xm",
					"sqsj", "shsj" };
			request.setAttribute("kns", "yes");
		} else if (dataType.equalsIgnoreCase("collegeMon")) {// ѧУ��ѧ��
			realTable = "xxzxdksqb";
			pk = "dkls";
			tips = "ѧ������ - ��Ϣά�� - ѧУ��ѧ��";
			tableName = "view_xxzxdksq";
			colList = new String[] { "����", "dkls", "nd", "xn", "xq", "xh",
					"xm", "sqsj", "sqje" };
		} else if (dataType.equalsIgnoreCase("nationMon")) {// ������ѧ����
			realTable = "gjzxdksqb";
			pk = "dkls";
			tips = "ѧ������ - ��Ϣά�� - ��ѧ����ά��";
			tableName = "view_gjzxdksq";
			colList = new String[] { "����", "dkls", "nd", "xn", "xq", "xh",
					"xm", "sqdkze", "sqsj" };
			xxmc = dao.getOneRs("select xxmc from xtszb", new String[] {},
					new String[] { "xxmc" })[0];
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJDX)) {
				request.setAttribute("whlb", "gjzxdk");
			}
		} else if (dataType.equalsIgnoreCase("htxx")) {// ��ͬ��Ϣ
			realTable = "zxdk_htxx";
			pk = "xh||hth";
			tips = "ѧ������ - ��Ϣά�� - ��ͬ��Ϣ";
			tableName = "zxdk_htxx";
			colList = new String[] { "����", "xxmc", "xm", "xh", "sfzh", "hth",
			"bz" };
			isHTXX = true;
		} else if (dataType.equalsIgnoreCase("fsbz")) {// ��ʳ����
			realTable = "fsbzb";
			pk = "xh||bzffny";
			tips = "ѧ������ - ��Ϣά�� - ��ʳ����";
			tableName = "view_fsbzb";
			colList = new String[] { "bgcolor", "����", "�к�", "xh", "xm", "bjmc","xjzt",
					"byyfbz", "bybfbz", "bysfbz", "bzffny", "xxsh" };
			isFSBZ = true;
			sql = "select 'δ���' xxsh from dual union select '��ͨ��' xxsh from dual union select '��ͨ��' xxsh from dual";
			List xxshList = dao.getList(sql, new String[] {}, new String[] {
			"xxsh" });
			request.setAttribute("xxshList", xxshList);
		} else if (dataType.equalsIgnoreCase("fkxx")) {// �ſ���Ϣ
			realTable = "zxdk_fkxx";
			pk = "sxh";
			tips = "ѧ������ - ��Ϣά�� - �ſ���Ϣ";
			tableName = "zxdk_fkxx";
			colList = new String[] { "����", "sxh", "xxmc", "xm", "sfzh", "hth",
					"htje", "fkje", "nd", "bz" };
			isHTXX = true;
		} else if (dataType.equalsIgnoreCase("zxdk_xsxxcx")) {// ��ѧ����ѧ����Ϣ
			realTable = "zxdk_xsjbxx";
			pk = "xh";
			tips = "ѧ������ - ��Ϣά�� - ѧ����Ϣ";
			tableName = "view_zxdk_xsjbxx";
			colList = new String[] { "����", "ksh", "xh", "xm", "dkze", "yhdkje" };
			isZXDK_XSXXCX = true;
		} else if (dataType.equalsIgnoreCase("nsepInfo")) {
			realTable = "nsepxxb";
			pk = "xn||xh||nsepxmmc";
			tips = "ѧ������ - ��Ϣά�� - NSEP��Ϣ";
			tableName = "view_nsepxx";
			colList = new String[] { "����", "nd", "xn", "xq", "xh", "xm",
					"nsepxmmc", "fzr", "zdls", "dektxf", "sj" };
		} else if (dataType.equalsIgnoreCase("moveInfo")) {
			if(Globals.XXDM_NBCSZYJSXY.equalsIgnoreCase(xxdm)){//��������ְҵ����ѧԺ
				return new ActionForward("/NbcsShsjManage.do",false);
			}
			realTable = "knsshhdxxb";
			pk = "xh||strtodatetime(sj)";
			tips = "ѧ������ - ��Ϣά�� - �����Ϣ";
			tableName = "view_knsshhdxx";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				colList = new String[] { "����", "xh", "xm", "sj", "hdxm",
						"hdnr", "shhdxzmc", "khjg" };
			} else {
				colList = new String[] { "����", "xh", "xm", "hdxm",
						"hdnr", "shhdxzmc","xjts","shry" };
			}
		} else if (dataType.equalsIgnoreCase("testResult")) {
			realTable = "xlcsjgb";
			pk = "xh||csxmdm||strtodatetime(cssj)";
			tips = "������ - ����ά�� - ���Խ��ά��";
			tableName = "view_xlcsjg";
			if(Globals.XXDM_ZGMSXY.equals(xxdm)){
				colList = new String[] { "����", "xh", "xm", "csxmdm", "xlcsxmmc",
						"csjg", "xlcsjgmc", "cssj", "fsbj" };
			}else{
				//colList = new String[] { "����", "xh", "xm", "csxmdm", "xlcsxmmc",
				//		"csjg", "xlcsjgmc", "cssj","csnj", "fsbj","bz" };
				//��ע�ֶ�̫����,�����������ε�
				colList = new String[] { "����", "xh", "xm", "csxmdm", "xlcsxmmc",
						"csjg", "xlcsjgmc", "cssj","csnj", "fsbj"};
			}
		}else if(dataType.equalsIgnoreCase("tsqtgj")){
			isTSQTGJ = true;
			realTable = "tsqtgjb";
			pk = "id";
			tips = "������ - ����ά�� - ����Ⱥ�����";
			tableName = "tsqtgjb";
			colList = new String[] {"id","xh","xm","xb","csny","bjmc","qsh","xsdh","xymc","jzxm","jzsj","bybjqk","xsmqqk","fdysh","xysh","xxsh"};
		}else if(dataType.equalsIgnoreCase("tsxsdjb")){
			isTSXSDJ = true;
			realTable = "tsqtxsdjb";
			pk = "xh";
			tips = "������ - ����ά�� - ����Ⱥ��ѧ������Ǽ�";
			tableName = "view_xsjbxx";
			colList = new String[] { "����", "xh", "xm", "xy", "bjmc",
					"xb", "ssbh", "sjhm", "lxdzxx" };
		}else if(dataType.equalsIgnoreCase("bjxzry")){
			isBJXZRY = true;
			realTable = "bjxzryb";
			pk = "id";
			tips = "������ - ����ά�� - ����Ⱥ��ѧ������Ǽ�";
			tableName = "bjxzryb";
			colList = new String[] {"id","xm","xb","nn","zzmm","zwzc","xrgz","xm1","xb1","nn1","zzmm1","zwzc1","xrgz1","xm2",
					"xb2","nn2","zzmm2","zwzc2","xrgz2","xm3","xb3","nn3","zzmm3","zwzc3","xrgz3","xm4","xb4",
					"nn4","zzmm4","zwzc4","xrgz4","zybjsl","fdysh","xysh","xxsh","fdysj","xyshsj","xxshsj"};
		}else if(dataType.equalsIgnoreCase("tsqtxssqcx")){
			isTSQTSQCX = true;
			realTable = "tsqtxssqcxb";
			pk = "tsid";
			tips = "������ - ����ά�� - ����Ⱥ��ѧ�����볷��";
			tableName = "tsqtxssqcxb";
			colList = new String[] { "tsid","xh","xm","xb","csny","bjmc","qsh","xsdh","xymc",
					"jzxm","jzsj","sqcxyy","fdysh","xysh","xxsh" };
		}else if(dataType.equalsIgnoreCase("xljkjyqktj")){
			isXLJKTJ = true;
			realTable = "xljkjyqktjb";
			pk = "zgid";
			tips = "������ - ����ά�� - �������������ͳ��";
			tableName = "xljkjyqktjb";
			colList = new String[] {"zgid","xm","qs","js","hdcs","xlwys","zsrs","zsnt",
					"zcrs","jshzs","wjgys"};
		}else if(dataType.equalsIgnoreCase("xlytqk")){
			isXLPC = true;
			realTable = "xlytqkb";
			pk = "xh";
			tips = "������ - ����ά�� - ����Լ̸���";
			tableName = "view_xsjbxx";
			colList = new String[] { "����", "xh", "xm", "xy", "bjmc",
					"xb", "ssbh", "sjhm", "lxdzxx" };
		}else if(dataType.equalsIgnoreCase("xlzxqk")){
			isZXQK = true;
			realTable = "xlzxqkb";
			pk = "zxid";
			tips = "������ - ������ѯ - ������ѯ���";
			tableName = "view_xlzxqkb";
			colList = new String[] {"zxid","xh","xm","zxsj", "zxls", "lfjl","wtcl"};
		}else if(dataType.equalsIgnoreCase("xljkjyhd")){
			isXLJKHD = true;
			realTable = "xljkjyhdb";
			pk = "id";
			tips = "������ - ������̬ - �����������";
			tableName = "xljkjyhdb";
			colList = new String[] {"id","zt","xy","jyxs","dd","hdrq","kssjs","kssjf","jssjs","jssjf","zcr","cyxs","cyrs","hdjl","hdxg"};
		}else if(dataType.equalsIgnoreCase("jzjsxx")){
			isJZJS = true;
			realTable = "jzjsdjb";
			pk = "jzid";
			tips = "������ - ������̬ - ��ְ��ʦ��Ϣ";
			tableName = "jzjsdjb";
			colList = new String[] {"jzid","xm","xb","csny","xymc","bgsdh","sjhm","qtlxdh","zwzc","dzyx","gzjl","bz"};
		}else if(dataType.equalsIgnoreCase("xsjbqk")){
			isXSJBXX = true;
			realTable = "gzxsjbqkb";
			pk = "id";
			tips = "������ - �ص��עѧ�� - �������";
			tableName = "view_gzxsjbqkb";
			colList = new String[] {"id","xh","xm","xb","xymc","xzb","cbsb","gzjb","zyzzjbx","yyfx","bz"};
		}else if(dataType.equalsIgnoreCase("xsgzqk")){
			isXSZZQK = true;
			realTable = "gzqkb";
			pk = "id";
			tips = "������ - �ص��עѧ�� - �������";
			tableName = "view_gzqkb";
			colList = new String[] {"id","xh","xm","xb","sfzdgz","xymc","xzb","jtbx","lxr","tbsj"};
		}else if(dataType.equalsIgnoreCase("xlzxyydj")){
			isXLZXYY = true;
			realTable = "xlzxyydjb";
			pk = "id";
			tips = "������ - ������ѯ - ������ѯԤԼ�Ǽ�";
			tableName = "view_xlzxyydjb";
			colList = new String[] {"id","xh","xm","xb","xymc","xzb","gjxx","ssld","dhhm","dzyx","sfzx","sczxxx","sfxlcs","xlxsyy","zxyy","qtzxxx","zxmd","qtwtxx","ddnxbz"};
		}else if(dataType.equalsIgnoreCase("szdtxx")){
			isSZDTXX = true;
			realTable = "szdtxxb";
			pk = "id";
			tips = "������ - ������̬ - ˫�ܶ�̬��Ϣ";
			tableName = "szdtxxb";
			colList = new String[] {"id","xldt","dtsj","jkjygz","zdgzqk","gtjcl","qt","xn","xq","zc"};
		}else if (dataType.equalsIgnoreCase("specialStu")) {
			realTable = "xytbgxxsxxb";
			pk = "xh||tbgxxslbdm";
			tips = "������ - ����ά�� - ����ѧ��ά��";
			tableName = "view_xytbgxxsxx";
			colList = new String[] { "����", "xh", "xm", "tbgxxslbdm",
			"tbgxxslbmc","lymc" };
			
			sql = "select lydm,lymc from xg_xljk_tsxslydmb";
			List tsxslyList = dao.getList(sql, new String[] {},
					new String[] { "lydm", "lymc" });
			
			request.setAttribute("tsxslyList", tsxslyList);
			request.setAttribute("specialStu", "specialStu");
		} else if (dataType.equalsIgnoreCase("discipInfo")) {
			realTable = "wjcfb";
			pk = "xh||cfwh||cfsj";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
				pk = "xh||xn||nd||sbsj";;
			} else if (Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
				pk = "xh||xn||nd||sbsj";
			} 
			tips = "Υ�ʹ��� - ����ά�� - Υ�ʹ�������ά��";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HYGXY)) {
				tips = "Υ�ʹ��� - ����ά�� - Υ�ʹ�������ά��";
			}
			tableName = "view_wjcf";
			request.setAttribute("wjcf", "wjcf");
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				colList = new String[] { "����","dis","xh","xm","bjmc", "xn", "xq",
						"cfsj", "cfwh", "cfyymc","ssjg","cxjg" };
				request.setAttribute("isCSMZ", "yes");
			}else if (Globals.XXDM_HZZY.equalsIgnoreCase(xxdm)) {
				colList = new String[] { "����","dis","xh","xm","bjmc", "xn", "xqmc",
						"cflbmc", "cfsj", "cfwh", "cfyymc" };
			}else if (Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
				colList = new String[] { "����","dis","xh","xm","bjmc", "xn", "xq",
						"cflbmc",  "cfyymc","cfsj", "cfwh","ssjg","cxjg" };
			} else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
				colList = new String[] { "����","dis","xh","xm","bjmc", "xn", "xqmc",
						"cflbmc", "cfyymc","cfsj", "cfwh","xsqr"  };
			} else if(Globals.XXDM_CQGCZY.equalsIgnoreCase(xxdm)){
				colList = new String[] { "����","dis","xh","xm","bjmc", "xn", "xqmc",
						"cflbmc", "cfyymc","cfsj", "cfwh" ,"sfcx"  };
			}else{
				colList = new String[] { "����","dis","xh","xm","bjmc", "xn", "xqmc",
						"cflbmc", "cfyymc","cfsj", "cfwh"  };
			}
			querry.append(" and (xxsh = 'ͨ��') ");
			String cfnx = request.getParameter("cfnx");
			if (!StringUtils.isNull(cfnx)) {
				querry.append(" and cfnx like '%");
				querry.append(cfnx);
				querry.append("%' ");
			}
			
			if (!StringUtils.isNull(wjkssj)) {
				querry.append(" and wjsj >= "+wjkssj);
			}
			
			if (!StringUtils.isNull(wjjssj)) {
				querry.append(" and wjsj <= "+wjjssj);
			}
			
			if (!StringUtils.isNull(cfkssj)) {
				querry.append(" and cfsj >= "+cfkssj);
			}
			
			if (!StringUtils.isNull(wjjssj)) {
				querry.append(" and cfsj <= "+cfjssj);
			}
			sql = "select * from cflbdmb";
			List cflbList = dao.getList(sql, new String[] {}, new String[] {
					"cflbdm", "cflbmc" });
			List cfyyList = dao.getList("select cfyydm,cfyymc from cfyydmb", new String[]{},  new String[]{"cfyydm","cfyymc"});
			if (sfzh == null || "".equalsIgnoreCase(sfzh)) {
				querry.append(" and 1=1");
			} else {
				querry.append(" and exists(select 1 from view_xsjbxx b where b.xh=a.xh and b.sfzh like '%");
				querry.append(sfzh);
				querry.append("%')");
			}
			if (!StringUtils.isNull(cflb)){
				querry.append(" and cflb = '");
				querry.append(cflb);
				querry.append("' ");
			}
			if (!StringUtils.isNull(cfyy)) {
				querry.append(" and cfyy = '");
				querry.append(cfyy);
				querry.append("' ");
			}
			//������Ϣ �Ƿ��������
			if (!StringUtils.isNull(sfjw)) {
				querry.append(" and sfjw = '");
				querry.append(sfjw);
				querry.append("' ");
			}
			//���칤��ְҵ����ѧԺ
			if (Globals.XXDM_CQGCZY.equalsIgnoreCase(xxdm)){
				//�Ƿ���
				if (!StringUtils.isNull(sfcx)) {
					querry.append(" and sfcx = '");
					querry.append(sfcx);
					querry.append("' ");
				}
				request.setAttribute("sfcx", sfcx);
			}
			if (qssj != null && qssj.length()>0){
				querry.append(" and months_between(to_date('");
				querry.append(qssj);
				querry.append("','yyyymmdd'),to_date(cfrq,'yyyymmdd'))<=0 and months_between(to_date('");
				querry.append(zzsj);
				querry.append("','yyyymmdd'),to_date(cfrq,'yyyymmdd'))>=0 ");
				request.setAttribute("qssj", qssj);
				request.setAttribute("zzsj", zzsj);
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
				sql = "select * from cflbdmb where cflbmc = ? or cflbmc = ? or cflbmc = ? or cflbmc = ? or cflbmc = ?";
				cflbList = dao.getList(sql, new String[]{"����", "���ؾ���", "�ǹ�", "��У�쿴", "����ѧ��"}, new String[]{"cflbdm", "cflbmc"});
				request.setAttribute("showXbemy", "showXbemy");
			} else if (Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
				String ywss = request.getParameter("ywss");
				String ywcx = request.getParameter("ywcx");
				if (!StringUtils.isNull(ywss) && "0".equalsIgnoreCase(ywss)) {
					querry.append(" and ssjg is not null ");
				} 
				if (!StringUtils.isNull(ywss) && "1".equalsIgnoreCase(ywss)) {
					querry.append(" and ssjg is null ");
				}
				if (!StringUtils.isNull(ywcx) && "0".equalsIgnoreCase(ywcx)) {
					querry.append(" and cxjg is not null");
				}
				if (!StringUtils.isNull(ywcx) && "1".equalsIgnoreCase(ywcx)) {
					querry.append(" and cxjg is null");
				}
			}
			request.setAttribute("cflbList", cflbList);
			request.setAttribute("cfyyList", cfyyList);
//			if ("true".equalsIgnoreCase(isFdy)) {

//			}
			String cs = request.getParameter("cs");
			if (StringUtils.isNotNull(cs)) {
				querry.append(" and xh in (select xh from wjcfb where xxsh='ͨ��' group by xh having count(xh) >= '"+cs+"')");
			}
		} else if (dataType.equalsIgnoreCase("discipInfoHis")) {
			realTable = "wjcflsb";
			pk = "gxsj";
			tips = "Υ�ʹ��� - ����ά�� - ��ʷ����ά��";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HYGXY)) {
				tips = "Υ�ʹ��� - ����ά�� - ��ʷ����ά��";
			}
			tableName = "view_wjcfls";

			if (Globals.XXDM_HZZY.equalsIgnoreCase(xxdm)) {
				colList = new String[] { "����", "xh", "xm", "xn", "nd", "xqmc",
						"cflbmc", "cfsj", "cfwh", "cfyymc" };
			} else {
				colList = new String[] { "����", "xh", "xm", "xn", "nd", "xqmc",
						"cflbmc", "cfsj", "cfwh", "cfyymc" };
			}
			if (qssj != null && qssj.length()>0){
				querry.append(" and months_between(to_date('");
				querry.append(qssj);
				querry.append("','yyyymmdd'),to_date(cfsj,'yyyymmdd'))<=0 and months_between(to_date('");
				querry.append(zzsj);
				querry.append("','yyyymmdd'),to_date(cfsj,'yyyymmdd'))>=0 ");
				request.setAttribute("qssj", qssj);
				request.setAttribute("zzsj", zzsj);
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
				request.setAttribute("showXbemy", "showXbemy");
			}

		} else if (dataType.equalsIgnoreCase("insureInfo")) {
			realTable = "xsbxb";
			pk = "xh||tbgsdm||tbxzdm||nd";
			tips = "�������� - ������Ϣ - ������Ϣά��";
			tableName = "view_xsbxxx";
			colList = new String[] { "����", "xh", "xm", "bxgsmc", "bxxzmc", 
					                 "tbsj", "nd", "bxnx", "bf", "jfbj", 
					                 "bxpzh", "sfyby" };
			
			//Ͷ���������Ϣά����������Ϣ���洢��һ�����У�������Ϣά����ѯ������ѧУ���ͨ���ļ�¼
			querry.append(" and xxsh = 'ͨ��' ");
			if(StringUtils.isNotNull(dataSearchForm.getSfyby())){
				querry.append(" and  sfyby= '");
				querry.append(dataSearchForm.getSfyby());
				querry.append("' ");
			}
		} else if (dataType.equalsIgnoreCase("InsureAppSearch")) {
			realTable = "xsbxb";
			pk = "xh||tbgsdm||tbxzdm||nd";
			tips = "�������� - ������Ϣ - ��������ѯ";
			tableName = "view_xsbxxx";
			colList = new String[] { "����", "xh", "xm", "bxgsmc", "bxxzmc",
					"nd", "bxnx", "bf", "sqsj", "xxsh" };
			querry.append(" and sqsj is not null ");
		} else if (dataType.equalsIgnoreCase("amendsMainten")) {
			realTable = "lpxxb";
			pk = "xh||bxgsdm||nd||slrq";
			tips = "�������� - ������Ϣ - ������Ϣά��";
			tableName = "view_lpxx";
			colList = new String[] { "����", "xh", "xm", "xymc", "bjmc", "nd",
					"bxgsmc", "slsj", "dzsj", "lpje" };
			if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
				colList = new String[] { "����", "xh", "xm", "xymc", "bjmc",
						"nd", "bxgsmc", "slsj", "dzsj", "lpje", "hffy","������" };
			}
			// �㶫Ů��ְҵ����ѧԺ
			if (xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)) {
				pk = "xh||bxgsdm||nd";
				realTable = "gdnz_lpxxb_bx";
				tableName = "view_gdnz_lpxx";
				colList = new String[] { "����", "xh", "xm", "xymc", "bjmc",
						"nd", "bxgsmc", "sqlpsj", "lpje" };
				request.setAttribute("sfxfzrx", "0");
			}
		} else if (dataType.equalsIgnoreCase("amendsMaintenBySch")) {
			// �㶫Ů��ְҵ����ѧԺ
			pk = "xh||bxgsdm||nd";
			realTable = "gdnz_lpxxb_sh";
			tips = "�������� - ������Ϣ - У��������������Ϣά��";
			tableName = "view_gdnz_lpxx";
			colList = new String[] { "����", "xh", "xm", "xymc", "bjmc", "nd",
					"bxgsmc", "sqlpsj", "lpje" };
			request.setAttribute("sfxfzrx", "1");
		} else if (dataType.equalsIgnoreCase("mealInfo")) {
			realTable = "xshsxfb";
			request.setAttribute("yfList", dao.getYfList());// �����·��б�
			pk = "xh||nf||yf";
			tips = "�������� - ��ʳ������Ϣ - ��ʳ������Ϣ";
			tableName = "view_xshsxf";
			colList = new String[] { "����", "xh", "xm", "nf", "yf", "xfje" };
		} else if (dataType.equalsIgnoreCase("comm")) {
			realTable = "dwjlsqb";
			pk = "xn||nd||xq||jlxmdm||xh";
			tips = "���⽻�� - ���� - ��������ѯ";
			tableName = "view_dwjlsq";
			colList = new String[] { "����", "nd", "xn", "xq", "xh", "xm",
					"dwjlxmmc", "sqsj", "xysh", "xxsh", "xxzs", "jxj" };
			sql = "select dwjlxmdm xmdm,dwjlxmmc xmmc from dwjlxmdmb ";
			List xmList = dao.getList(sql, new String[] {}, new String[] {
					"xmdm", "xmmc" });
			writeAble = CheckPower.checkUsrPower(session.getAttribute(
			"userName").toString(), "comm_result.do") ? "yes" : "no";
			request.setAttribute("xmList", xmList);
		} else if (dataType.equalsIgnoreCase("work")) {
			realTable = "xsgwxxb";
			pk = "xh||gwdm||sqsj";
			tips = "�ڹ���ѧ - ��λ���� - ��������ѯ";
			tableName = "view_xsgwxx";
//			String userOnLine = session.getAttribute("userOnLine").toString();
			if(xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)){
				//��ɳ����
				tips = "ѧ���幤 - ��λ���� - ��������ѯ";
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
				colList = new String[] { "����", "xn", "nd", "xqmc", "xh", "xm",
						"bjmc", "gwdm", "sqsj", "sfpks", "xyyj", "xxyj", "kh" };
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
				// ��������
				colList = new String[] { "����", "xn", "nd", "xqmc", "xh", "xm",
						"bjmc", "gwdm", "sqsj", "sfpks", "fdyyj", "xxyj",
						"xxshyj", "kh","bh","gh" };
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){
				//�㽭����ְҵ����ѧԺ
				colList = new String[] { "����", "xn", "nd", "xqmc", "xh", "xm",
						"bjmc", "gwdm", "sqsj", "sfpks","xxyj" };
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)){
				//
				colList = new String[]{"����", "xn", "nd", "xqmc", "xh", "xm",
						"bjmc", "gwdm", "sqsj", "sfpks", "fdyyj","xyyj", "xxyj"};
			} else {
				colList = new String[] { "����", "xn", "nd", "xqmc", "xh", "xm",
						"bjmc", "gwdm", "sqsj", "sfpks", "xyyj", "xxyj" };
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){
				//�㽭����ְҵ����ѧԺ
				if (yrdwdm != null && !(yrdwdm.trim().length() < 1)) {
					querry.append(" and yrdwdm='");
					querry.append(yrdwdm);
					querry.append("' ");
				}
				String sqlTemp = "select YRDWDM,YRDWMC from yrdwdmb";
				List<HashMap<String,String>> yrdwList = dao.getList(sqlTemp,new String[]{},new String[] {"yrdwdm","yrdwmc"});
				request.setAttribute("yrdwList", yrdwList);
			}		
		} else if (dataType.equalsIgnoreCase("testPaper")
				|| dataType.equalsIgnoreCase("testOnline")) {
			realTable = "sjb";
			pk = "sjlsh";
			tips = "������ - ������� - �Ծ�ά��";
			tableName = "sjb";
			colList = new String[] { "����", "sjlsh", "sjm", "sjxd", "sjxsbj",
			"jrsj" };
			sql = "select sjlsh,sjm from sjb";
			if (dataType.equalsIgnoreCase("testOnline")) {
				sql += " where sjxsbj='��' ";
			}
			List sjList = dao.getList(sql, new String[] {}, new String[] {
					"sjlsh", "sjm" });
			request.setAttribute("sjList", sjList);
			querry = new StringBuffer(" where 1=1 ");
			String sjlsh = dataSearchForm.getSjlsh();
			if ((sjlsh == null) || sjlsh.equalsIgnoreCase("")) {

			} else {
				querry.append(" and sjlsh = '");
				querry.append(sjlsh);
				querry.append("' ");
			}
			if (dataType.equalsIgnoreCase("testOnline")) {
				querry.append(" and sjxsbj ='��'");
			}
			querry.append(" order by jrsj asc ");
		} else if (dataType.equalsIgnoreCase("testQuestion")) {
			realTable = "stb";
			pk = "stlsh";
			tips = "������ - ������� - ���ά��";
			tableName = "view_st";
			colList = new String[] { "����", "stlsh", "stlxmc", "stndjbmc",
					"stnrsl", "stjffscn", "stfz", "stda", "stxsbj" };
			sql = "select stlxdm,stlxmc from stlxdmb";
			List stlxList = dao.getList(sql, new String[] {}, new String[] {
					"stlxdm", "stlxmc" });
			request.setAttribute("stlxList", stlxList);
			sql = "select stndjbdm,stndjbmc from stndjbdmb";
			List stndjbList = dao.getList(sql, new String[] {}, new String[] {
					"stndjbdm", "stndjbmc" });
			request.setAttribute("stndjbList", stndjbList);
			String stlxdm = dataSearchForm.getStlxdm();
			String stndjbdm = dataSearchForm.getStndjbdm();
			if ((stlxdm == null) || stlxdm.equalsIgnoreCase("")) {

			} else {
				querry.append("and stlxdm = '");
				querry.append(stlxdm);
				querry.append("' ");
			}
			if ((stndjbdm == null) || stndjbdm.equalsIgnoreCase("")) {

			} else {
				querry.append("and stndjbdm = '");
				querry.append(stndjbdm);
				querry.append("' ");
			}
		} else if (dataType.equalsIgnoreCase("testOption")) {
			realTable = "xxb";
			pk = "xxlsh";
			tips = "������ - ������� - ѡ��ά��";
			tableName = "xxb";
			colList = new String[] { "����", "xxlsh", "stlsh", "xxxh", "xxnr",
					"xxfz", "xxxsbj" };
			sql = "select stlsh from stb";
			List stlshList = dao.getList(sql, new String[] {},
					new String[] { "stlsh" });
			request.setAttribute("stlshList", stlshList);
			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {

			} else {
				querry.append(" and stlsh = '");
				querry.append(pkValue);
				querry.append("' ");
				dataSearchForm.setStlsh(pkValue);
			}
			String stlsh = dataSearchForm.getStlsh();
			if ((stlsh == null) || stlsh.equalsIgnoreCase("")) {

			} else {
				querry.append(" and stlsh = '");
				querry.append(stlsh);
				querry.append("' ");
			}
		} else if (dataType.equalsIgnoreCase("testPaperQuestion")) {
			realTable = "sjstb";
			pk = "sjlsh||stlsh";
			tips = "������ - ������� - ���ά��";
			tableName = "view_sjst";
			colList = new String[] { "����", "sjm", "stxh", "stlxmc", "stndjbmc",
					"stnrsl", "stjffs", "stfz", "stda", "stlsh" };
			sql = "select sjlsh,sjm from sjb";
			List sjList = dao.getList(sql, new String[] {}, new String[] {
					"sjlsh", "sjm" });
			request.setAttribute("sjList", sjList);
			sql = "select stlxdm,stlxmc from stlxdmb";
			List stlxList = dao.getList(sql, new String[] {}, new String[] {
					"stlxdm", "stlxmc" });
			request.setAttribute("stlxList", stlxList);
			sql = "select stndjbdm,stndjbmc from stndjbdmb";
			List stndjbList = dao.getList(sql, new String[] {}, new String[] {
					"stndjbdm", "stndjbmc" });
			request.setAttribute("stndjbList", stndjbList);
			if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {

			} else {
				querry.append(" and sjlsh = '");
				querry.append(pkValue);
				querry.append("' ");
				dataSearchForm.setSjlsh(pkValue);
			}
			String sjlsh = dataSearchForm.getSjlsh();
			String stlxdm = dataSearchForm.getStlxdm();
			String stndjbdm = dataSearchForm.getStndjbdm();
			if ((sjlsh == null) || sjlsh.equalsIgnoreCase("")) {

			} else {
				querry.append(" and sjlsh = '");
				querry.append(sjlsh);
				querry.append("' ");
			}
			if ((stlxdm == null) || stlxdm.equalsIgnoreCase("")) {

			} else {
				querry.append(" and stlxdm = '");
				querry.append(stlxdm);
				querry.append("' ");
			}
			if ((stndjbdm == null) || stndjbdm.equalsIgnoreCase("")) {

			} else {
				querry.append(" and stndjbdm = '");
				querry.append(stndjbdm);
				querry.append("' ");
			}
			querry.append(" order by stxh asc ");
		} else if (dataType.equalsIgnoreCase("partyApply")) {
			realTable = "rdsqb";
			pk = "xn||xq||xh";
			tips = "˼����� - ����ά�� - �뵳������Ϣ";
			tableName = "view_rdsq";
			colList = new String[] { "����", "nd", "xn", "xq", "xh", "xm", "nj",
					"bjmc", "djsqsj" };
			// =============== begin ���ΰ 2009/4/9===============
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)) {
				realTable = "zgkd_rdsqb";
				tableName = "view_zgkd_rdsq";
			}
			// =============== end ���ΰ 2009/4/9===============
		} else if (dataType.equalsIgnoreCase("thoughtReport")) {
			realTable = "sxhbb";
			pk = "xh||djsj";
			tips = "˼����� - ����ά�� - ˼��㱨��Ϣ";
			tableName = "view_sxhb";
			colList = new String[] { "����", "xh", "xm", "nj", "bjmc", "djsj",
			"numTotal" };

		} else if (dataType.equalsIgnoreCase("assistance")) {
			realTable = "xsbzxxb";
			pk = "xn||xq||xh||bzlxdm";
			tips = "ѧ������ - ��Ϣά�� - ������Ϣά��";
			tableName = "view_xsbz";
			colList = new String[] { "����", "nd", "xn", "xqmc", "xh", "xm",
					"nj", "bjmc", "bzsj", "bzlxmc", "bzed" };
		} else if (dataType.equalsIgnoreCase("trainResult")) {
			realTable = "jxjgb";
			pk = "xn||xq||xh";
			tips = "��ѵ���� - ��Ϣά�� - ��ѵ���ά��";
			tableName = "view_jxjg";
			// =============== begin 2009/5/25 ���ΰ ====================
			colList = new String[] { "����", "nd", "xn", "xqmc", "xh", "xm",
					"nj", "bjmc", "sfhg", "sfjjfz" };
			// =============== end 2009/5/25 ���ΰ ====================
		} else if (dataType.equalsIgnoreCase("studentCadre")) {
			realTable = "xsgbxxb";
			pk = "xn||xq||xh||rzbmdm||drzw";
			tips = "��Ṥ�� - ��Ϣά�� - ѧ���ɲ�";
			tableName = "view_xsgbxx";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				colList = new String[] { "����", "nd", "xn", "xqmc", "xh", "xm",
						"nj", "bjmc", "rzbmmc", "drzw", "qsh", "qsdh", "khjg" };
			} else {
				colList = new String[] { "����", "nd", "xn", "xqmc", "xh", "xm",
						"nj", "bjmc", "rzbmmc", "drzw", "qsh", "qsdh" };
			}
		} else if (dataType.equalsIgnoreCase("volunteerReg")) {
			realTable = "zyzfwdjb";
			pk = "xh||fwsj";
			tips = "��Ṥ�� - ��Ϣά�� - ־Ը�߷���";
			tableName = "view_zyzfwdj";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				colList = new String[] { "����", "nd", "xn", "xqmc", "xh", "xm",
						"nj", "bjmc", "zyzbh", "drzw", "fwsj", "fwl", "khjg","cjzyzfwsj" };
			} else {
				colList = new String[] { "����", "nd", "xn", "xqmc", "xh", "xm",
						"nj", "bjmc", "fwsj", "fwl","cjzyzfwsj" };
			}
		} else if (dataType.equalsIgnoreCase("leagueInfo")) {
			realTable = "sthdxxb";
			pk = "xh||stdm||datetimetostr(jrsj)";
			tips = "��Ṥ�� - ��Ϣά�� - ���Ż";
			tableName = "view_sthdxx";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				colList = new String[] { "����", "nd", "xn", "xqmc", "xh", "xm",
						"nj", "bjmc", "stmc", "jrsj", "tcsj", "rzqk", "khjg" };
			} else {
				colList = new String[] { "����", "nd", "xn", "xqmc", "xh", "xm",
						"nj", "bjmc", "stmc", "hydj", "jrsj", "tcsj" };
			}
		} else if (dataType.equalsIgnoreCase("dormInfo")) {
			@SuppressWarnings("unused")
			boolean res = dao.createCwxx();//��֤��λ��Ϣ�Ƿ���ȷ
			if (res == false){
				request.setAttribute("errINFO","���´�λ����ʱ���ִ���");
			}
			GetDropDownList getL = new GetDropDownList();
//			gyglDao gydao = new gyglDao();
			String xqdm = DealString.toGBK(dataSearchForm.getXqdm());
			String lddm = DealString.toGBK(dataSearchForm.getLddm());
			String qsh = DealString.toGBK(dataSearchForm.getQsh());
			String yqdm = DealString.toGBK(dataSearchForm.getYqdm());
			String cs = request.getParameter("cs");
			realTable = "ssxxb";
			pk = "ssbh";
			tips = "��Ԣ���� - ��Ϣά�� - ��Դ��ά��";
			tableName = "view_ssxx";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				colList = new String[] { "����", "ssbh", "ldmc", "qsh", "qsdh",
						"cws", "fpbj", "xqmc", "shq", "dxdh", "ttdh", "wxport",
						"cs","sfbz"  };
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)){  //��ɳ����
				colList = new String[] { "����", "ssbh", "ldmc", "qsh", "qsdh",
						"cws",  "xqmc","sfbz"  };
			}else if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
				colList = new String[] { "����", "ssbh", "yqmc","ldmc", "qsh", "qsdh",
						"cws", "fpbj", "xqmc","sfbz"  };			
			}else{
				colList = new String[] { "����", "ssbh", "ldmc", "qsh", "qsdh",
						"cws", "fpbj", "xqmc","sfbz"  };
			}

//			List xiaoqquList=GyglShareDAO.getXiaoQuList(userName);						
//			List ldList=GyglShareDAO.getSsldList(xqdm,yqdm,userName);				
//			List yqList = GyglShareDAO.getYqList(xqdm);
//			List lcList = getL.GetLcList(lddm,userName);
//			List ssList = getL.GetQshList2(lddm,cs,userName);
			
			GyglTyDAO gydao = new GyglTyDAO();
			
			String dm = "";
			String value = "";
			String zd = Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm) ? "yqmc||'/'||ldmc"
					: "ldmc";
			if (!Base.isNull(xqdm)) {
				dm = "xqdm";
				value = xqdm;
			}
			
			List<HashMap<String, String>> xqdmList = gydao.getGyglList("dm_zju_xq", "dm", "xqmc", "", "", "");// У��
			List<HashMap<String, String>> yqList = gydao.getGyglList("view_bjlh_ldxx", "yqdm", "yqmc", "", "", "");// ԰��
			List<HashMap<String, String>> ldList = gydao.getGyglList("view_bjlh_ldxx", "lddm", "ldmc", "", dm, value);// ¥��
			List<HashMap<String, String>> csList = gydao.getCsList(lddm);	//����
			List<HashMap<String, String>> qsList = gydao.getQsList(lddm, cs, "");// ����

			if (Base.isNull(xqdm)||xqdm.equalsIgnoreCase("null")) {

			} else {
				querry.append(" and xqdm = '");
				querry.append(xqdm);
				querry.append("' ");
			}
			if (Base.isNull(lddm)||lddm.equalsIgnoreCase("null")) {

			} else {
				querry.append("and lddm = '");
				querry.append(lddm);
				querry.append("' ");
			}
			if (Base.isNull(qsh)
					||qsh.equalsIgnoreCase("null")
					||qsh.equalsIgnoreCase("qsh")) {

			} else {
				querry.append("and qsh = '");
				querry.append(qsh);
				querry.append("' ");
			}
			if (Base.isNull(cs)
					||cs.equalsIgnoreCase("null")
					||cs.equalsIgnoreCase("lc")) {

			} else {
				querry.append("and cs = '");
				querry.append(cs);
				querry.append("' ");
			}
			querry.append(Base.isNull(yqdm)?"":" and yqdm='"+yqdm+"' ");
			if(GyglShareDAO.isSssAdmin(userName)){
				querry.append(" and (fpbj = '˶ʿ' or fpbj='��ʿ') ");
			}else{
				querry.append(" and (fpbj = 'һ��' or fpbj='����') ");
			}						
			//request.setAttribute("xiaoqquList", xiaoqquList);

//			��Ԣ����Ա�жϼ��ظ���¥���б�begin        
//			List listTem = gyglDao.getLddmxXx(request.getSession().getAttribute("userName").toString(),xqdm,yqdm);
//			if(listTem!=null&&listTem.size()>0){
//			gyglDao.reLoadLb(request);
//			}else{
//			request.setAttribute("ldList", ldList);
//			request.setAttribute("lcList", lcList);
//			request.setAttribute("ssList", ssList);
//			}

//			if(listTem.size()>0){
//			ldList = listTem;
//			HashMap<String,String> hmap = (HashMap<String,String>)listTem.get(0);		
//			String ldtem = hmap.get("lddm");
//			lcList = dao.getList("  select '' dm,'--��ѡ��--'mc from dual union all select lc dm,lc mc from (select distinct substr(qsh,1,1) lc from ssxxb where lddm =?) order by dm nulls first", 
//			new String[] {ldtem}, new String[] { "dm","mc" });				

//			ssList = dao.getList(" select '' dm,'--��ѡ��--'mc from dual union all select distinct qsh dm,qsh mc from ssxxb where lddm=? order by dm nulls first ",
//			new String[] {ldtem}, new String[] { "dm","mc" });
//			}
//			��Ԣ����Ա�ж�end

			dataSearchForm.setImgXy(userDep);
			request.setAttribute("xqdmList", xqdmList);
			request.setAttribute("yqList", yqList);
			request.setAttribute("ldList", ldList);
			request.setAttribute("csList", csList);
			request.setAttribute("qsList", qsList);	
//			request.setAttribute("xqdmList", xqdmList);
//			request.setAttribute("ldList", ldList);
//			request.setAttribute("csList", csList);
//			request.setAttribute("ssList", ssList);
//			request.setAttribute("yqList", yqList);									
			//��дȨ�ж�		 			
			request.setAttribute("writeAble1", (Base.chkUPower(userName,"ssxx_search.do?act=dormInfo", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
		} else if (dataType.equalsIgnoreCase("usingInfo")) {		
			//�Ƿ���Ҫ��������ͬ��
			String gysxKz = Base.initProperties.get("gysxKz");
			request.setAttribute("gysxKz",gysxKz);
			
			boolean res = dao.createCwxx();//��֤��λ��Ϣ�Ƿ���ȷ
			if (res == false){
				request.setAttribute("errINFO","���´�λ����ʱ���ִ���");
			}			
//			if(xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)
//			||xxdm.equalsIgnoreCase(Globals.XXDM_HENANGYDX)
//			||Globals.XXDM_JSXX.equalsIgnoreCase(xxdm)){
			if(gyglDao.xsfpMsXxPd(xxdm)){	
				//����Ƿ���������ס����Ϣά��ģʽBegin����ɳ����ְҵ����ѧԺ����1����ʾ��������ס����Ϣά��ģʽ����0����ʾѧ��ס����Ϣά��ά��ģʽ
				String sfbdbz = dao.getOneRs(" select xszsbdms from gygl_xsbdbz where rownum=1 ",new String[]{},"xszsbdms");
				if(Base.isNull(sfbdbz)){
					sfbdbz="0";
				}
				if(sfbdbz.equalsIgnoreCase("1")){
					return new ActionForward("/csmz_gygl.do?method=xsDormDataSearch",false);
				}
				//����Ƿ���������ס����Ϣά��ģʽEnd
			}				
			realTable = "xszsxxb";
			pk = "xh";
			tips = "��Ԣ���� - ��Ϣά�� - ס����Ϣά��";
			String xqdm = dataSearchForm.getXqdm();
//			String yqdm = request.getParameter("yqdm");
			String lddm = dataSearchForm.getLddm();
			String qsh = dataSearchForm.getQsh();
			String lc = dataSearchForm.getLc();
			String xsxb = DealString.toGBK(dataSearchForm.getXb1());
			dataSearchForm.setXb1(xsxb);
			String mz = dataSearchForm.getMz();
			String jg = DealString.toGBK(dataSearchForm.getJg());
			String sydmc = DealString.toGBK(dataSearchForm.getSydmc());
			String jtdz = DealString.toGBK(dataSearchForm.getJtdz());
			String jtdh = dataSearchForm.getJtdh();
//			String sjhm = dataSearchForm.getSjhm();
			String zzmm = dataSearchForm.getZzmm();
			String lxdh   = DealString.toGBK(dataSearchForm.getLxdh());
			String qsdh   = DealString.toGBK(dataSearchForm.getQsdh());	
			String pycc   = DealString.toGBK(dataSearchForm.getPycc());
			String sfqsz= DealString.toGBK(dataSearchForm.getSfqsz());
			dataSearchForm.setPycc(pycc);
			dataSearchForm.setLxdh(lxdh);
			dataSearchForm.setQsdh(qsdh);

			GetDropDownList getList = new GetDropDownList();
			List<HashMap<String, String>> xiaoqquList = new ArrayList<HashMap<String, String>>();
			List<HashMap<String, String>> ldList = new ArrayList<HashMap<String, String>>();
			List<HashMap<String, String>> lcList = new ArrayList<HashMap<String, String>>();
			List<HashMap<String, String>> qshList = new ArrayList<HashMap<String, String>>();

			querry.append(Base.isNull(lxdh)?"":" and (lxdh like '%"+lxdh+"%' or sjhm like '%"+lxdh+"%') ");
			querry.append(Base.isNull(qsdh)?"":" and qsdh='"+qsdh+"' ");
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
				colList = new String[] { "����", "nj","xh", "xm","xb", "ldmc","qsh", 
						"cwh","bjmc", "rzrq",  "zsf", "dsjssf","gyqk","sfbz" };
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {//��������
				colList = new String[] { "����","nj", "xh", "xm","xb", "xqmc", "ldmc","qsh", "cwh",
						"rzrq",  "zsf", "dsjssf","gyqk","sfbz","sfqsz" };
				querry.append(Base.isNull(sfqsz)?"":" and sfqsz='"+sfqsz+"' ");
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
				//tableName = "xbemy_view_xszsxx";				
				gyglDao gydao = new gyglDao();
				request.setAttribute("showxbemy","showxbemy");				
				gydao.getOrthList(dao, request);
				colList = new String[] { "����","nj", "xh", "xm","xb","jg","mz" ,"xqmc","ldmc", "qsh", 
						"cwh", "rzrq", "sfbz" };				
				querry.append(Base.isNull(mz)?"":" and mz ='"+mz+"' ");
				querry.append(Base.isNull(jg)?"":" and jg like '%"+jg+"%' ");
				querry.append(Base.isNull(sydmc)?"":" and sydmc like '%"+sydmc+"%' ");
				querry.append(Base.isNull(jtdz)?"":" and jtdz like '%"+jtdz+"%' ");
				querry.append(Base.isNull(jtdh)?"":" and jtdh like '%"+jtdh+"%' ");
				querry.append(Base.isNull(zzmm)?"":" and zzmm ='"+zzmm+"' ");
				dataSearchForm.setSydmc(sydmc);
				dataSearchForm.setJg(jg);
				dataSearchForm.setJtdz(jtdz);
				dataSearchForm.setXb1(xsxb);
				dataSearchForm.setJtdh(jtdh);
				dataSearchForm.setSfzh(sfzh);
			}else if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
				colList = new String[] { "����","nj", "xh", "xm","xb", "yqmc","ldmc",
						"qsh", "cwh", "rzrq","sfbz"};
			}else if(Globals.XXDM_HHGXY.equalsIgnoreCase(xxdm)){
				colList = new String[] { "����","nj", "xh", "xm","xb","xqmc", "ldmc", "qsh", 
						"cwh", "rzrq", "qsdh","lxdh","sjhm" ,"sfbz"};
			}else  if(Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)){
				colList = new String[] { "����","nj", "xh", "xm","xb","xqmc", "ldmc", "qsh", 
						"cwh", "rzrq", "sfbz","pycc"};
				request.setAttribute("showzjcm","zjcm");
				request.setAttribute("pyccList",dao.getList("select distinct pycc dm,pycc mc from view_xsxxb ", new String[]{},new String[]{"dm","mc"}));
			}else{
				colList = new String[] { "����","nj", "xh", "xm","xb","xqmc", "ldmc", "qsh", 
						"cwh", "rzrq", "sfbz"};
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)) {
				request.setAttribute("showgdnz", "showgdnz");
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)){
				request.setAttribute("showgdbyxy", "showgdbyxy");
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_HNGYDX)){
				request.setAttribute("showhngydx", "showhngydx");
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)){
				request.setAttribute("showhhgxy", "showhhgxy");
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJCMXY)){
				request.setAttribute("showzjcmxy", "showzjcmxy");
			}
			sql = "select dm,xqmc from dm_zju_xq";
			xiaoqquList = dao.getList(sql, new String[] {}, new String[] {
					"dm", "xqmc" });

			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){	//�й����ʴ�ѧ
				sql = " select '' lddm,'--��ѡ��--'ldmc from dual union all select lddm,(select yqmc from yqdmb b where a.yqdm=b.yqdm )||ldmc ldmc from sslddmb  a";			    
			}else{
				sql = " select '' lddm,'--��ѡ��--'ldmc from dual union all select lddm,ldmc from sslddmb ";				
			}
			sql += Base.isNull(xqdm)?"":" where xqdm='" + xqdm + "' ";
			sql +=" order by lddm nulls  first";
			ldList = dao.getList(sql, new String[] {}, new String[] {"lddm", "ldmc" });		
//			sql = "select lc dm,lc mc from (select distinct  cs lc from ssxxb) order by lc";
//			List lcList = dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
			lcList =getList.GetLcList(lddm,userName);
//			sql = "select qsh dm ,qsh mc from (select distinct qsh from ssxxb )order by qsh";
//			List qshList = dao.getList(sql, new String[]{}, new String[]{"dm","mc"});
			if (!Base.isNull(lddm)) {
				qshList = getList.GetQshList2(lddm, lc, userName);
			}else{
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("dm", "");
				map.put("mc", "----��ѡ��----");
				qshList.add(map);
			}
			request.setAttribute("xiaoqquList", xiaoqquList);

//			��Ԣ����Ա�жϼ��ظ���¥���б�begin
			//List listTem = gyglDao.getLddmxXx(request.getSession().getAttribute("userName").toString(),xqdm,yqdm);
			if(gyglDao.isGyFdy(request.getSession().getAttribute("userName").toString())){
				gyglDao.reLoadLb(request);
			}else{
				request.setAttribute("ldList", ldList);
				request.setAttribute("lcList",lcList);
				request.setAttribute("qshList",qshList);
			}
			
//			��Ԣ����Ա�ж�end
			querry.append(Base.isNull(zzmm)?"":" and zzmm ='"+zzmm+"' ");
			querry.append(Base.isNull(xqdm)?"":" and xqdm ='"+xqdm+"' ");
			querry.append((Base.isNull(lddm)||lddm.equalsIgnoreCase("null"))?"":" and lddm ='"+lddm+"' ");
			querry.append((Base.isNull(qsh)||"qsh".equalsIgnoreCase(qsh)||qsh.equalsIgnoreCase("null"))?"":" and qsh ='"+qsh+"' ");
			querry.append(Base.isNull(lc)||"lc".equalsIgnoreCase(lc)?"":" and (substr(qsh,1,1) ='"+lc+"'or cs='"+lc+"' ) ");
			querry.append(Base.isNull(xsxb)?"":" and xb ='"+xsxb+"' ");
			querry.append(Base.isNull(pycc)?"":" and pycc like '%"+pycc+"%' ");
			tableName = "view_xszsxx";			
			request.setAttribute("userName",userName);
			request.setAttribute("zgdzdxSssZs", "false");//���о���ģʽ				

			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){				
				if(GyglShareDAO.isSssAdmin(userName)){//�о����޹ܿ��û�
					tableName = "view_ssszsxx";
					querry.append(" and (fpbj = '˶ʿ' or fpbj='��ʿ')  ");					

					request.setAttribute("njList", Base.getNjList());
					request.setAttribute("ssxyList", GyglShareDAO.getSssXyList(userName));//ѧԺ
					request.setAttribute("xiaoqquList", GyglShareDAO.getXiaoQuList(userName));
					request.setAttribute("ldList", GyglShareDAO.getSsldList(xqdm,"",userName));
					request.setAttribute("ssList", GyglShareDAO.GetQshList(lddm,userName));
					request.setAttribute("ssbjList", GyglShareDAO.getSssBjList(nj, xy, "", userName));					
					request.setAttribute("zgdzdxSssZs", "true");//�о���ģʽ
				}else{//�������û�
					querry.append(" and fpbj ='һ��' ");
				}
			}	
			request.setAttribute("zzmmList", dao.getZzmmList());
			request.setAttribute("writeAble1", (Base.chkUPower(userName,"dorm_Using_Search.do?act=usingInfo", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
		} else if (dataType.equalsIgnoreCase("dailyNote")) {
			realTable = "mrzbjlb";
			pk = "sj||lddm||zbrydm";
			tips = "��Ԣ���� - ֵ���¼ - ÿ�ռ�¼";
			tableName = "view_mrzbjl";

			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)) {
				pk = "sj||zbrydm";
				colList = new String[] { "����", "xqmc", "sj", "tq", "zbrymc",
				"zbrymc1" };
			} else {
				colList = new String[] { "����", "xqmc", "ldmc", "sj", "tq",
				"zbrymc" };
			}
			sql = "select dm,xqmc from dm_zju_xq";
			List xiaoqquList = dao.getList(sql, new String[] {}, new String[] {
					"dm", "xqmc" });
			request.setAttribute("xiaoqquList", xiaoqquList);
			sql = "select lddm,ldmc from sslddmb order by lddm";
			List ldList = dao.getList(sql, new String[] {}, new String[] {
					"lddm", "ldmc" });
			request.setAttribute("ldList", ldList);
			sql = "select zbrydm,zbrymc from zbrydmb";
			List zbryList = dao.getList(sql, new String[] {}, new String[] {
					"zbrydm", "zbrymc" });
			request.setAttribute("zbryList", zbryList);
			String xqdm = dataSearchForm.getXqdm();
			String lddm = dataSearchForm.getLddm();
			String zbrydm = DealString.toGBK(dataSearchForm.getZbrydm());			
			if ((xqdm == null) || xqdm.equalsIgnoreCase("")) {

			} else {
				querry.append(" and xqdm = '");
				querry.append(xqdm);
				querry.append("' ");
			}
			if ((lddm == null) || lddm.equalsIgnoreCase("")) {

			} else {
				querry.append("and lddm = '");
				querry.append(lddm);
				querry.append("' ");
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJJZYJSXY)){//�㽭����ְҵ����ѧԺ
				tableName = "view_zjjj_mrzbjl";
				request.setAttribute("showZjjj","showZjjj");
				querry.append(Base.isNull(zbrydm)?"":"and zbrydm like '"+zbrydm+"%'");
				colList = new String[] { "����", "xqmc", "ldmc", "sj", "tq",
				"zbrydm" };
				dataSearchForm.setZbrydm(zbrydm);
			}else{
				querry.append(Base.isNull(zbrydm)?"":"and zbrydm = '"+zbrydm+"'");	
			}

			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)) {
				request.setAttribute("visible", false);// ��ҵְҵ�е�ÿ��ֵ���¼�в���ʾ¥����
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)){//��ɳ����ְҵ����ѧԺ
				String zbrlx = DealString.toGBK(dataSearchForm.getZbrlx());
				querry.append(Base.isNull(zbrlx)?"":" and zbrlx='"+zbrlx+"' ");
				request.setAttribute("zbrLxList",dao.getChkList(16));
				request.setAttribute("showCsmzzyjsxy","showCsmzzyjsxy");
				dataSearchForm.setZbrlx(zbrlx);
			}

		} else if (dataType.equalsIgnoreCase("weeklyCollect")) {
			realTable = "yzzbhzb";
			pk = "qssj||jssj||lddm";
			tips = "��Ԣ���� - ֵ���¼ - һ�ܻ���";
			tableName = "view_yzzbhz";
			colList = new String[] { "����", "xqmc", "ldmc", "sj", "zyzbjlhzsl",
			"xgbmclqksl" };
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)) {
				pk = "qssj||jssj||hzr";
				colList = new String[] { "����", "xqmc", "hzr", "sj",
						"zyzbjlhzsl", "xgbmclqksl" };
			}
			sql = "select dm,xqmc from dm_zju_xq";
			List xiaoqquList = dao.getList(sql, new String[] {}, new String[] {
					"dm", "xqmc" });
			request.setAttribute("xiaoqquList", xiaoqquList);
			sql = "select lddm,ldmc from sslddmb order by  lddm ";
			List ldList = dao.getList(sql, new String[] {}, new String[] {
					"lddm", "ldmc" });
			request.setAttribute("ldList", ldList);
			String xqdm = dataSearchForm.getXqdm();
			String lddm = dataSearchForm.getLddm();
			if ((xqdm == null) || xqdm.equalsIgnoreCase("")) {

			} else {
				querry.append("and xqdm = '");
				querry.append(xqdm);
				querry.append("' ");
			}
			if ((lddm == null) || lddm.equalsIgnoreCase("")) {

			} else {
				querry.append("and lddm = '");
				querry.append(lddm);
				querry.append("' ");
			}

			xxmc = dao.getOneRs("select xxmc from xtszb", new String[] {},
			"xxmc");
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)) {// //��ҵְҵһ�ܻ����в���ʾ¥����
				request.setAttribute("visible", false);
			}

		} else if (dataType.equalsIgnoreCase("studentPaperPut")) {
			realTable = "xszffb";
			pk = "xh||ffsj";
			tips = "�ճ����� - ѧ��֤���� - ����";
			tableName = "view_xszff";
			colList = new String[] { "����", "nd", "xn", "xq", "xh", "xm",
					"ffsj", "jbrxm", "bjmc" };
		} else if (dataType.equalsIgnoreCase("trainCheapPut")) {
			realTable = "hcyhkffb";
			pk = "xh||ffsj";
			tips = "�ճ����� - ���Żݿ����� - ����";
			if(Globals.XXDM_NGSXY.equalsIgnoreCase(xxdm)){
				tableName = "xg_view_rcsw_hcyhkff";
				colList = new String[] { "����", "nd", "xn", "xq", "xh", "xm",
						"ffsj", "jbrxm", "jtdz", "bjmc" };
			}else{
				tableName = "view_hcyhkff";
				colList = new String[] { "����", "nd", "xn", "xq", "xh", "xm",
						"ffsj", "jbrxm", "jtdz", "bjmc" };
			}
		} else if (dataType.equalsIgnoreCase("schoolBadgePut")) {
			realTable = "xhffb";
			pk = "xh||ffsj";
			tips = "�ճ����� - У�չ��� - ����";
			tableName = "view_xhff";
			colList = new String[] { "����", "nd", "xn", "xqmc", "xh", "xm",
					"ffsj", "jbrxm", "bjmc" };
		} else if (dataType.equalsIgnoreCase("studentPaperAgain")) {
			String bbcx = request.getParameter("bbcx");
			if(bbcx == "bbcx"){
				realTable = "xszbbb";
				pk = "xh||sqsj";
				tips = "�ճ����� - ѧ��֤���� - ����";
				tableName = "view_xszbb";
				colList = new String[] { "����", "nd", "xn", "xq", "xh", "xm",
						"sqsj", "bbsj", "jbrxm", "sflq","fdysh","xysh","xxsh"};
			}else{
				realTable = "xszbbb";
				pk = "xh||sqsj";
				tips = "�ճ����� - ѧ��֤���� - ����";
				tableName = "view_xszbb";
				colList = new String[] { "����", "nd", "xn", "xqmc", "xh", "xm",
						"sqsj", "bbsj", "jbrxm", "sflq"};
				String yyt = request.getParameter("sfxxcv");
				if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS) && !("123".equals(yyt))) {
					querry.append(" and xxsh='ͨ��' ");
				}
				if ("stu".equals(userType)) {
					xh=Base.isNull(xh)?"%":xh;
					querry.append(" and xh like'"+xh+"' ");
				}
			
			}

			if (xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)) {
				querry.append(" and xxsh='ͨ��' order by sflq,sqsj ");
			} else {
				querry.append(" order by sflq,sqsj ");
			}
		} else if (dataType.equalsIgnoreCase("trainCheapAgain")) {
			String bbcx = request.getParameter("bbcx");
			String yyt = request.getParameter("sfxxcv");
			if(bbcx == "bbcx"){
				realTable = "hcyhkbbb";
				pk = "xh||sqsj";
				tips = "�ճ����� - ���Żݿ����� - ����";
				tableName = "view_hcyhkbb";
				colList = new String[] { "����", "nd", "xn", "xq", "xh", "xm",
						"sqsj", "bbsj", "jbrxm", "sflq" ,"fdysh","xysh","xxsh"};
			}else{
				realTable = "hcyhkbbb";
				pk = "xh||sqsj";
				tips = "�ճ����� - ���Żݿ����� - ����";
				tableName = "view_hcyhkbb";
				colList = new String[] { "����", "nd", "xn", "xq", "xh", "xm",
						"sqsj", "bbsj", "jbrxm", "sflq"};
				if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS) &&	!("123".equals(yyt))) {
					querry.append(" and xxsh='ͨ��' ");
				}
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)) {
				querry.append(" and xxsh='ͨ��' order by sflq,sqsj ");
			} else {
				querry.append(" order by sflq,sqsj ");
			}
		} else if (dataType.equalsIgnoreCase("oneCardAgain")) {
			String bbcx = request.getParameter("bbcx");
			String yyt = request.getParameter("sfxxcv");
			if(bbcx == "bbcx"){
				realTable = "yktbbb";
				pk = "xh||sqsj";
				tips = "�ճ����� - һ��ͨ���� - ����";
				tableName = "view_yktbb";
				colList = new String[] { "����", "nd", "xn", "xq", "xh", "xm",
						"sqsj", "bbsj", "jbrxm", "sflq" ,"fdysh","xysh","xxsh"};
			}else{
				realTable = "yktbbb";
				pk = "xh||sqsj";
				tips = "�ճ����� - һ��ͨ���� - ����";
				tableName = "view_yktbb";
				colList = new String[] { "����", "nd", "xn", "xq", "xh", "xm",
						"sqsj", "bbsj", "jbrxm", "sflq"};
				if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS) &&	!("123".equals(yyt))) {
					querry.append(" and xxsh='ͨ��' ");
				}
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)) {
				querry.append(" and xxsh='ͨ��' order by sflq,sqsj ");
			} else {
				querry.append(" order by sflq,sqsj ");
			}
		} else if (dataType.equalsIgnoreCase("schoolBadgeAgain")) {
			String bbcx = request.getParameter("bbcx");
			String yyt = request.getParameter("sfxxcv");
			if(bbcx == "bbcx"){
				realTable = "xhbbb";
				pk = "xh||sqsj";
				tips = "�ճ����� - У�չ��� - ����";
				tableName = "view_xhbb";
				colList = new String[] { "����", "nd", "xn", "xqmc", "xh", "xm",
						"sqsj", "bbsj", "jbrxm", "sflq" ,"fdysh","xysh","xxsh"};
			}else{
				realTable = "xhbbb";
				pk = "xh||sqsj";
				tips = "�ճ����� - У�չ��� - ����";
				tableName = "view_xhbb";
				colList = new String[] { "����", "nd", "xn", "xqmc", "xh", "xm",
						"sqsj", "bbsj", "jbrxm", "sflq"};
				if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS) &&	!("123".equals(yyt))) {
					querry.append(" and xxsh='ͨ��' ");
				}
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)) {
				querry.append(" and xxsh='ͨ��' order by sflq,sqsj ");
			} else {
				querry.append(" order by sflq,sqsj ");
			}
		} else if (dataType.equalsIgnoreCase("trainCheapAddMoney")) {
			realTable = "hcyhkczb";
			pk = "xh||gxsj";
			tips = "�ճ����� - ���Żݿ����� - ��ֵ";
			tableName = "view_hcyhkcz";
			colList = new String[] { "����", "nd", "xn", "xq", "xh", "xm",
					"czje", "czsj", "jbrxm" };
		} else if (dataType.equalsIgnoreCase("stuDiathesisDev")) {
			realTable = "xssztzb";
			pk = "xh||xn||xq";
			tips = "������չ - ��Ϣά�� - ������չά��";
			tableName = "view_xssztz";
			colList = new String[] { "����", "nd", "xn", "xq", "xh", "xm",
					"xymc", "zymc", "bjmc" };
		} else if (dataType.equalsIgnoreCase("trainTime")) {
			realTable = "hcccb";
			pk = "cc";
			tips = "�ճ����� - Ʊ����� - ���β�ѯ";
			tableName = "view_hccc";
			colList = new String[] { "����", "cc", "qdzmc", "zdzmc", "kcsj",
					"ddsj", "yxsj", "pj", "dqzt", "tkz" };
			sql = "select czdm,czmc from czdmb";
			List czList = dao.getList(sql, new String[] {}, new String[] {
					"czdm", "czmc" });
			request.setAttribute("czList", czList);
			sql = "select cc from hcccb";
			List ccList = dao.getList(sql, new String[] {},
					new String[] { "cc" });
			request.setAttribute("ccList", ccList);
			String cc = dataSearchForm.getCc();
			String qdz = dataSearchForm.getQdz();
			String zdz = dataSearchForm.getZdz();
			String tkz = DealString.toGBK(dataSearchForm.getTkz());
			dataSearchForm.setQdz(qdz);
			dataSearchForm.setZdz(zdz);
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)) {
				//��ҵְҵ
				request.setAttribute("showzjsyzy", "showzjsyzy");
				if ((cc == null) || cc.equalsIgnoreCase("")) {

				} else {
					querry.append("and cc = '");
					querry.append(cc);
					querry.append("' ");
				}
				if ((zdz == null) || zdz.equalsIgnoreCase("")) {

				} else {
					querry.append("and tkz like '%");
					querry.append(DealString.toGBK(zdz));
					querry.append("%' ");
				}
			} else {
				if ((cc == null) || cc.equalsIgnoreCase("")) {

				} else {
					querry.append("and cc = '");
					querry.append(cc);
					querry.append("' ");
				}
				if ((qdz == null) || qdz.equalsIgnoreCase("")) {

				} else {
					querry.append("and qdz = '");
					querry.append(qdz);
					querry.append("' ");
				}
				if ((zdz == null) || zdz.equalsIgnoreCase("")) {

				} else {
					querry.append("and zdz = '");
					querry.append(zdz);
					querry.append("' ");
				}
				if ((tkz == null) || tkz.equalsIgnoreCase("")) {

				} else {
					querry.append("and tkz like '%");
					querry.append(tkz);
					querry.append("%' ");
				}
			}
			request.setAttribute("userName", userName);
		} else if (dataType.equalsIgnoreCase("workOutlay")) {
			realTable = "jfhbb";
			pk = "yrdwdm||dateTimeToStr(hbsj)";
			tips = "�ڹ���ѧ - ���ѹ��� - ���Ѽ�¼��ѯ";
			tableName = "view_jfhb";
			String cxfs = DealString.toGBK(dataSearchForm.getCxfs());
			String go = request.getParameter("go");
			if(xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)){
				//��ɳ����
				tips = "ѧ���幤 - ���ѹ��� - ���Ѽ�¼��ѯ";
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
				if ("����".equalsIgnoreCase(cxfs)) {
					return new ActionForward("/jfhzcx.do?go=" + go, false);
				}
				colList = new String[] { "����", "xn", "nd", "xqmc", "yrdwmc",
						"xydm", "hbje", "hbsj" };
			} else {
				colList = new String[] { "����", "xn", "nd", "xqmc", "yrdwmc",
						"hbje", "hblb", "hbsj", "gwxzmc" };
			}
			sql = "select yrdwdm,yrdwmc from yrdwdmb";
			List yrdwList = dao.getList(sql, new String[] {}, new String[] {
					"yrdwdm", "yrdwmc" });
			request.setAttribute("yrdwList", yrdwList);
			String hblb = DealString.toGBK(dataSearchForm.getHblb());
			dataSearchForm.setHblb(hblb);
			dataSearchForm.setCxfs(cxfs);
			if ((yrdwdm == null) || yrdwdm.equalsIgnoreCase("")) {

			} else {
				querry.append("and yrdwdm = '");
				querry.append(yrdwdm);
				querry.append("' ");
			}
			if ((hblb == null) || hblb.equalsIgnoreCase("")) {

			} else {
				querry.append("and hblb = '");
				querry.append(hblb);
				querry.append("' ");
			}
		} else if (dataType.equalsIgnoreCase("xsxxwh")) {
			dataSearchForm.setXh(DealString.toGBK(dataSearchForm.getXh()));
			realTable = "bks_xsszjbxx";
			pk = "xh";
			tips = "ѧ����Ϣ - ѧ����Ϣά�� - ѧ����Ϣά��";
			tableName = "view_xslxfszsxx";
			colList = new String[] { "����", "XH", "XM", "XB", "NJ", "XYMC",
					"XZ", "ZYMC", "BJMC" };
			writeAble = (CheckPower.checkUsrPower(session.getAttribute(
			"userName").toString(),
			"data_search.do?tableName=view_xsjbxx&dataType=xsxxwh")) ? "yes"
					: "no";
		} else if (dataType.equalsIgnoreCase("busTime")) {
			realTable = "qcccb";
			pk = "cc";
			tips = "�ճ����� - Ʊ����� - �������β�ѯ";
			tableName = "view_qccc";
			colList = new String[] { "����", "cc", "qdzmc", "zdzmc", "kcsj",
					"ddsj", "yxsj", "pj", "dqzt", "tkz" };
			sql = "select czdm,czmc from qczdmb";
			List czList = dao.getList(sql, new String[] {}, new String[] {
					"czdm", "czmc" });
			request.setAttribute("czList", czList);
			sql = "select cc from qcccb";
			List ccList = dao.getList(sql, new String[] {},
					new String[] { "cc" });
			request.setAttribute("ccList", ccList);
			String cc = dataSearchForm.getCc();
			String qdz = dataSearchForm.getQdz();
			String zdz = dataSearchForm.getZdz();
			String tkz = DealString.toGBK(dataSearchForm.getTkz());
			if ((cc == null) || cc.equalsIgnoreCase("")) {

			} else {
				querry.append("and cc = '");
				querry.append(cc);
				querry.append("' ");
			}
			if ((qdz == null) || qdz.equalsIgnoreCase("")) {

			} else {
				querry.append("and qdz = '");
				querry.append(qdz);
				querry.append("' ");
			}
			if ((zdz == null) || zdz.equalsIgnoreCase("")) {

			} else {
				querry.append("and zdz = '");
				querry.append(zdz);
				querry.append("' ");
			}
			if ((tkz == null) || tkz.equalsIgnoreCase("")) {

			} else {
				querry.append("and tkz like '%");
				querry.append(tkz);
				querry.append("%' ");
			}
		} else if (dataType.equalsIgnoreCase("gjjxj")) {// ���ҽ�ѧ��
			realTable = "xsjxjzxjsqb";
			tableName = "view_xsjzxj";
			pk = "xh||nd||jzxjmc";
			colList = new String[] { "����", "nd", "xh", "xm", "sqsj", "xysh",
			"xxsh" };
			querry.append(" and jzxjmc='gjjxj'");
			request.setAttribute("kns", "no");
		} else if (dataType.equalsIgnoreCase("gjzxj")) {// ������ѧ��
			realTable = "xsjxjzxjsqb";
			tableName = "view_xsjzxj";
			pk = "xh||nd||jzxjmc";
			colList = new String[] { "����", "nd", "xh", "xm", "sqsj", "xysh",
			"xxsh" };
			querry.append(" and jzxjmc='gjzxj'");
			request.setAttribute("kns", "no");
		} else if (dataType.equalsIgnoreCase("szfjxj")) {// ʡ������ѧ��
			realTable = "xsjxjzxjsqb";
			tableName = "view_xsjzxj";
			pk = "xh||nd||jzxjmc";
			colList = new String[] { "����", "nd", "xh", "xm", "sqsj", "xysh",
			"xxsh" };
			querry.append(" and jzxjmc='szfjxj'");
			request.setAttribute("kns", "no");
		} else if (dataType.equalsIgnoreCase("szfzxj")) {// ʡ������ѧ��
			realTable = "xsjxjzxjsqb";
			tableName = "view_xsjzxj";
			pk = "xh||nd||jzxjmc";
			colList = new String[] { "����", "nd", "xh", "xm", "sqsj", "xysh",
			"xxsh" };
			querry.append(" and jzxjmc='szfzxj'");
			request.setAttribute("kns", "no");
		} else if (dataType.equalsIgnoreCase("lyjszxj")) {// ��Ԫ������ѧ��
			realTable = "xsjxjzxjsqb";
			tableName = "view_xsjzxj";
			pk = "xh||nd||jzxjmc";
			colList = new String[] { "����", "nd", "xh", "xm", "sqsj", "xysh",
			"xxsh" };
			querry.append(" and jzxjmc='lyjszxjsq'");
			request.setAttribute("kns", "no");
		} else if (dataType.equalsIgnoreCase("lstd")) {// ��ɫͨ��
			realTable = "letdsq";
			tableName = "letdsq";
			pk = "xh||nd";
			colList = new String[] { "����", "nd", "xh", "xm", "sqsj", "xysh",
			"xxsh" };
			querry.append(" and 1=1 ");
			request.setAttribute("kns", "no");
		} else if (dataType.equalsIgnoreCase("xsxfjm")) {// ѧ��ѧ�Ѽ���
			realTable = "XSXFJMSPB";
			tableName = "XSXFJMSPB";
			pk = "xh||nd";
			colList = new String[] { "����", "nd", "xh", "xm", "sqsj", "xysh",
			"xxsh" };
			querry.append(" and 1=1 ");
			request.setAttribute("kns", "no");
		} else if (dataType.equalsIgnoreCase("zxdk")) {// ��ѧ����
			realTable = "ZXDK_XSSQB";
			tableName = "ZXDK_XSSQB";
			pk = "xh||nd";
			colList = new String[] { "����", "nd", "xh", "xm", "sqsj", "xysh",
			"xxsh" };
			querry.append(" and 1=1 ");
			request.setAttribute("kns", "no");
		} else if (dataType.equalsIgnoreCase("xpjjdk")) {// ��ƽ�������
			realTable = "xpjjzxdksqb";
			tableName = "view_xpjjzxdksq";
			pk = "xh||nd";
			colList = new String[] { "����", "nd", "xh", "xm",
					"SQDKJE".toLowerCase(), "sqsj", "xysh", "xxsh" };
			// querry += " and jzxjmc='szfzxj'";
			request.setAttribute("kns", "no");
		} else if (dataType.equalsIgnoreCase("xfbz")) {// ѧ�Ѳ���
			realTable = "xsbzb";
			tableName = "view_xsbzxx";
			pk = "xh||bzdm||nd";
			colList = new String[] { "����", "nd", "xh", "xm",
					"ZZFF1QSJE".toLowerCase(), "ZZFF1JSJE".toLowerCase(),
					"sqsj", "xysh", "xxsh" };
			querry
			.append(" and bzdm=(select KNBZDM from knbzdmb where bzlb like 'ѧ��%')");
			request.setAttribute("kns", "no");
		} else if (dataType.equalsIgnoreCase("lsknbz")) {// ��ʱ���Ѳ���
			realTable = "xsbzb";
			tableName = "view_xsbzxx";
			pk = "xh||bzdm||nd";
			colList = new String[] { "����", "nd", "xh", "xm",
					"ZZFF1QSJE".toLowerCase(), "ZZFF1JSJE".toLowerCase(),
					"sqsj", "xysh", "xxsh" };
			querry
			.append(" and bzdm=(select KNBZDM from knbzdmb where bzlb like '��ʱ%')");
			request.setAttribute("kns", "no");
		} else if (dataType.equalsIgnoreCase("xndxj")) {// ���У�ڴ�ѧ��
			realTable = "xsbzb";
			tableName = "view_xsbzxx";
			pk = "xh||bzdm||nd";
			colList = new String[] { "����", "nd", "xh", "xm",
					"ZZFF1QSJE".toLowerCase(), "ZZFF1JSJE".toLowerCase(),
					"sqsj", "xysh", "xxsh" };
			querry
			.append(" and bzdm = (select knbzdm from knbzdmb where bzlb like 'У��%')");
			request.setAttribute("kns", "no");
		} else if (dataType.equalsIgnoreCase("wszxj")) {// ������ѧ��
			realTable = "wszxjsqb";
			tableName = "view_xswszxjsqxx";
			pk = "xh||bzdm||nd";
			colList = new String[] { "����", "nd", "xh", "xm", "sqsj", "xysh",
			"xxsh" };

			List<HashMap<String, String>> wszxjList = dao.getList(
					"select zxjdm,zxjmc from wszxjdmb", new String[] {},
					new String[] { "zxjdm", "zxjmc" });
			String zxjdm = dataSearchForm.getZxjdm();
			if ((zxjdm == null) || zxjdm.equalsIgnoreCase(" ")) {
				querry.append(" and 1=1");
				colList = new String[] { "����", "bzmc", "nd", "xh", "xm",
						"sqsj", "xysh", "xxsh" };
			} else {
				querry.append(" and bzdm='");
				querry.append(zxjdm);
				querry.append("'");
				colList = new String[] { "����", "nd", "xh", "xm", "sqsj",
						"xysh", "xxsh" };
			}
			request.setAttribute("kns", "no");
			request.setAttribute("wszxjList", wszxjList);
		} else if (dataType.equalsIgnoreCase("zxbzInfo")) {
			realTable = "xszxbzb";
			pk = "xh||bzdm||nd";
			tips = "ѧ������ - ��Ϣά�� - ר���";
			tableName = "view_zxbzxx";
			colList = new String[] { "����", "nd", "xh", "xm", "bzmc", "zzff1",
					"zzff1qsje", "zzff1jsje", "xxsh" };
		} else if (dataType.equalsIgnoreCase("lstdInfo")) {
			realTable = "lstdb";
			pk = "xh||nd";
			tips = "ѧ������ - ��Ϣά�� - ��ɫͨ��";
			tableName = "view_lstdxx";
			colList = new String[] { "����", "nd", "xq", "nj", "xh", "xm", "xb",
					"bjmc", };
		} else if (dataType.equalsIgnoreCase("absent")) {
			realTable = "kkqkb";
			pk = "xh||kkrq||kkkc";
			tips = "�ճ����� - ���ڹ��� - �������";
			tableName = "view_kkqkxx";
			
			if ("1049701".equals(xxdm)) {
				colList = new String[] { "����", "nd", "xq", "nj", "xh", "xm",
					"kkrq", "kkkc", "kkjs" };
			} else {
				colList = new String[] { "����", "nd", "xq", "nj", "xh", "xm",
						"kkrq", "kcmc", "kkjs" };
			}
			
			HttpSession session1 = request.getSession();
			String userType1 = session1.getAttribute("userType").toString();
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HYGXY)) {
				tips = "�ճ����� - ���ڹ��� - �������";
			}
			if (userType1.equalsIgnoreCase("stu")) {
				return new ActionForward("/stuabsence.do", false);
			}

		} else if (dataType.equalsIgnoreCase("cjxxwh")) {
			realTable = "xfcjb";
			pk = "xh||nd||xn||xq";
			tips = "�ճ����� - ѧ�Ѵ߽� - �߽���Ϣά��";
			tableName = "view_xfcjxx";
			colList = new String[] { "����", "nd", "xqmc", "nj", "xh", "xn", "xm",
					"xb", "bjmc", "je" };
		} else if (dataType.equalsIgnoreCase("hjxxwh")) {
			realTable = "xfhjb";
			pk = "xh||nd";
			tips = "�ճ����� - ѧ�Ѵ߽� - ������Ϣά��";
			tableName = "view_xfhjxx";
			colList = new String[] { "����", "nd", "xqmc", "nj", "xh", "xm", "xb",
					"bjmc", "hjje", "hjqx" };
		} else if (dataType.equalsIgnoreCase("kccj")) {
			realTable = "kccjcpb";
			pk = "xn||xq||xh";
			tips = "�������� - ��Ϣά�� - �γ̳ɼ�����";
			tableName = "view_kccjcpxx";
			colList = new String[] { "����", "xn", "xq", "xh", "xm", "xb", "nj",
			"cpf" };
		} else if (dataType.equalsIgnoreCase("forstrInfo")) {
			realTable = "xsgzpxxxb";
			pk = "xn||xq||xh||pxxmdm";
			tips = "��Ṥ�� - ��Ϣά�� - ��ѵ��Ϣ";
			tableName = "view_xsgzpxxx";
			colList = new String[] { "����", "nd", "xn", "xq", "xh", "xm", "nj",
					"bjmc", "pxxmmc", "pxjg" };
		} else if (dataType.equalsIgnoreCase("trainbhg")) {
			realTable = "jxjgb";
			pk = "xn||xq||xh";
			tips = "��ѵ���� - ��Ϣά�� - ��ѵ���ϸ�ͳ��";
			tableName = "view_jxbhgxx";
			colList = new String[] { "����", "nd", "xn", "xq", "xh", "xm", "nj",
					"bjmc", "sfhg", "bxsfhg", "sfjjfz" };
		} else if (dataType.equalsIgnoreCase("xsbbcx")) {
			realTable = "xszbbb";
			pk = "xh||sqsj";
			tips = "�ճ����� - ѧ��֤���� - ����";
			tableName = "view_xszbb";
			colList = new String[] { "����", "nd", "xn", "xq", "xh", "xm",
					"sqsj", "bbsj", "jbrxm", "sflq","fdysh","xysh","xxsh"};
		} else {
			dataSearchForm.setErrMsg("S");
			return mapping.findForward("false");
		}
		String xsccdm = request.getParameter("xsccdm");
		if(xsccdm!=null&&!xsccdm.equalsIgnoreCase("")) {
			querry.append("and xsccdm = '");
			querry.append(xsccdm);
			querry.append("' ");
		}
		if (isFSBZ) {
			StringBuffer tempSb = new StringBuffer("");
			tempSb.append("select (case nvl(a.xxsh,'δ���') when '��ͨ��' then '#99CCFF' when 'δ���' then '#FFFFFF' else '#FF9999' end) bgcolor,");
			tempSb.append("a.* from (select ");
			tempSb.append(pk);
			tempSb.append(" ����,rownum �к�,a.* from ");
			tempSb.append(tableName);
			tempSb.append(" a");
			tempSb.append(querry.toString());
			tempSb.append(") a");
			sql = tempSb.toString();
		} else {
			StringBuffer tempSb = new StringBuffer("");
			if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)&& realTable.equalsIgnoreCase("lpxxb")){				
				tempSb.append("select * from (select ");
				tempSb.append(pk);
				tempSb.append(" ����,rownum r,a.* from ");
				tempSb.append("(select xh,nd,max(xm)xm,max(xymc)xymc,max(bjmc)bjmc,bxgsdm,max(bxgsmc)bxgsmc,max(slrq)slrq,max(slrq)slsj,max(dzsj)dzsj,sum(lpje)lpje,sum(hffy)hffy,trunc(sum(lpje)/sum(hffy)*100 ,2)||'%' ������ from view_lpxx group by xh,nd,bxgsdm)");
				tempSb.append(" a");
				tempSb.append(querry);
				tempSb.append(" ) where r<=");
				tempSb.append((dataSearchForm.getPages().getStart() + dataSearchForm.getPages().getPageSize()));
				tempSb.append(" and r>");
				tempSb.append(dataSearchForm.getPages().getStart());
			} else if (Globals.XXDM_YCWSZYJSXY.equalsIgnoreCase(xxdm) && "zhszcp".equalsIgnoreCase(realTable)) {
				tempSb.append("select * from (select ");
				tempSb.append(pk);
				tempSb.append(" ����,rownum r,a.nd,a.xn,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,a.xh,a.xm,a.bjmc,a.dcj,a.zcj,a.tcj,a.zpf from ");
				tempSb.append(tableName);
				tempSb.append(" a");
				tempSb.append(querry);
				tempSb.append(" ) where r<=");
				tempSb.append((dataSearchForm.getPages().getStart() + dataSearchForm.getPages().getPageSize()));
				tempSb.append(" and r>");
				tempSb.append(dataSearchForm.getPages().getStart());
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY) && realTable.equalsIgnoreCase("xsrychb")) {
				tempSb.append("select * from (select ");
				tempSb.append(pk);
				tempSb.append(" ����,rownum r,a.nd,a.xn,a.xq,a.xh,a.xm,a.bjmc,a.rychmc,a.cjmc,a.zhpfmc,a.fdyqm,a.xyqm,(case when a.zysj is null then '' when length(ltrim(a.zysj,' '))>=5 then substr(ltrim(a.zysj,' '),0,5) else ltrim(a.zysj) end) zysj,a.xysh,a.xxsh from ");
				tempSb.append(tableName);
				tempSb.append(" a");
				tempSb.append(querry + " )");

				//��ҳȥ��
				//tempSb.append(" ) where r<=");
				//tempSb.append((dataSearchForm.getPages().getStart() + dataSearchForm.getPages().getPageSize()));
				//tempSb.append(" and r>");
				//tempSb.append(dataSearchForm.getPages().getStart());
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY) && realTable.equalsIgnoreCase("xsjxjb")) {
				tempSb.append("select * from (select ");
				tempSb.append(pk);
				tempSb.append(" ����,rownum r,a.* from ");
				tempSb.append(tableName);
				tempSb.append(" a");
				tempSb.append(querry + " )");

				//��ҳȥ��
				//tempSb.append(" ) where r<=");
				//tempSb.append((dataSearchForm.getPages().getStart() + dataSearchForm.getPages().getPageSize()));
				//tempSb.append(" and r>");
				//tempSb.append(dataSearchForm.getPages().getStart());
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX) && realTable.equalsIgnoreCase("xlytqkb")) {
				dataSearchForm.getPages().setPageSize(100);
				tempSb.append("select * from (select ");
				tempSb.append("a."+pk);
				tempSb.append(" ����,rownum r,a.xm,a.xb,a.nj,a.xy,a.bjmc,a.ssbh,x.* from ");
				tempSb.append(tableName);
				tempSb.append(" a,");
				tempSb.append(" xlytqkb x");
				tempSb.append(querry+"and a.xh=x.xh");
				tempSb.append(" ) where r<=");
				tempSb.append((dataSearchForm.getPages().getStart() + dataSearchForm.getPages().getPageSize()));
				tempSb.append(" and r>");
				tempSb.append(dataSearchForm.getPages().getStart());
			}else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX) && realTable.equalsIgnoreCase("xlcsjgb")) {
				//dataSearchForm.getPages().setPageSize(100);
//				int dd= dataSearchForm.getPages().getPageSize();
				tempSb.append("select * from (select ");
				tempSb.append(pk);
				tempSb.append(" ����,rownum r,a.* from ");
				tempSb.append(tableName);
				tempSb.append(" a");
				tempSb.append(querry);
				tempSb.append(" ) where r<=");
				tempSb.append((dataSearchForm.getPages().getStart() + dataSearchForm.getPages().getPageSize()));
				tempSb.append(" and r>");
				tempSb.append(dataSearchForm.getPages().getStart());
			}else if( realTable.equalsIgnoreCase("wjcfb")){
				tempSb.append("select * from (select ");
				tempSb.append(pk);
				tempSb.append(" ����,case when xysh='ͨ��' or xxsh='ͨ��' or xsqr='��ȷ��' then 'disabled' else '' end dis,rownum r,a.* from ");
				tempSb.append(tableName);
				tempSb.append(" a");
				//querry.append(" and zpf is not null ");
				tempSb.append(querry);
				tempSb.append(" ) where r<=");
				tempSb.append((dataSearchForm.getPages().getStart() + dataSearchForm.getPages().getPageSize()));
				tempSb.append(" and r>");
				tempSb.append(dataSearchForm.getPages().getStart());
				System.out.println(tempSb);
			}else{
				tempSb.append("select * from (select ");
				tempSb.append(pk);
				tempSb.append(" ����,rownum r,a.* from ");
				tempSb.append(tableName);
				tempSb.append(" a");
				//querry.append(" and zpf is not null ");
				tempSb.append(querry);
				tempSb.append(" ) where r<=");
				tempSb.append((dataSearchForm.getPages().getStart() + dataSearchForm.getPages().getPageSize()));
				tempSb.append(" and r>");
				tempSb.append(dataSearchForm.getPages().getStart());
			}
			sql = tempSb.toString();

			if (xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)) {
				tempSb = new StringBuffer("");
				if (dataType.equalsIgnoreCase("amendsMainten")) {
					tempSb.append("select ");
					tempSb.append(pk);
					tempSb.append(" ����,a.* from ");
					tempSb.append(tableName);
					tempSb.append(" a");
					tempSb.append(querry);
					tempSb.append(" and SFXFZRX='0'");
					sql = tempSb.toString();
				}
				if (dataType.equalsIgnoreCase("amendsMaintenBySch")) {
					tempSb.append("select ");
					tempSb.append(pk);
					tempSb.append(" ����,a.* from ");
					tempSb.append(tableName);
					tempSb.append(" a");
					tempSb.append(querry);
					tempSb.append(" and SFXFZRX='1'");
					sql = tempSb.toString();
				}
			}
		}

		// long t2 = System.currentTimeMillis();
		// System.out.println("��ѯ��ʼ����"+(t2-t1));

		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			// colListCN = dao.getColumnNameCN(colList, tableName);
			// topTr = dao.arrayToList(colList, colListCN);
			if (Globals.XXDM_SHGC.equalsIgnoreCase(xxdm) ) {
				if (dataType.equalsIgnoreCase("discipInfo") || dataType.equalsIgnoreCase("discipInfoHis")) {
					colList = new String[] { "����", "xh", "xm", "xn", "nd", "xqmc",
							"cflbmc", "cfsj", "cfwh", "cfyymc" };
				}
			} 
			DataManModel dataManModel = getSearchModel(session, dao, colList,
					tableName);
			colListCN = dataManModel.getColListCN(tableName);
			topTr = dataManModel.getTopTr(tableName);
			// long tt1 = System.currentTimeMillis();
			// System.out.println("���룺"+(tt1-t2));
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)
					&& "trainTime".equalsIgnoreCase(dataType)) {
				String zdz = DealString.toGBK(request.getParameter("zdz"));
				String qdz = DealString.toGBK(request.getParameter("qdz"));
				colList = new String[] { "����", "cc", "dqzt",  "zdz",
						"qdz", "kcsj", "ddsj", "pj" };
				sql = "select "
					+ pk
					+ " ����, cc,dqzt, zdzstr,qdzstr,(substr(zdzstr,1,(select instr(zdzstr,'!',1,1)-1 from dual))) zdz, "
					+ "(substr(zdzstr,(select instr(zdzstr,'!',1,1)+1 from dual),(select instr(zdzstr,'@',1,1)"
					+ " from dual)-(select instr(zdzstr,'!',1,1)+1 from dual))) ddsj ,"
					+ "(substr(zdzstr,(select instr(zdzstr,'@',1,1)+1 from dual),length(zdzstr)-(select instr(zdzstr,'@',1,1) from dual))) pj,"
					+ "(substr(qdzstr,1,(select instr(qdzstr,'!',1,1)-1 from dual))) qdz,"
					+ "(substr(qdzstr,(select instr(qdzstr,'!',1,1)+1 from dual),(select instr(qdzstr,'@',1,1) from dual)-(select instr(qdzstr,'!',1,1)+1 from dual))) kcsj"
					+ " from(select cc,dqzt,yxsj,(select substr(substr(tkz,instr(tkz,',"
					+ zdz
					+ "',1,1)+1,length(tkz)-instr(tkz,',"
					+ zdz
					+ "',1,1)),1,"
					+ "case when instr(substr(tkz,instr(tkz,',"
					+ zdz
					+ "',1,1)+1,length(tkz)-instr(tkz,',"
					+ zdz
					+ "',1,1)),',',1,1)=0"
					+ " then length(substr(tkz,instr(tkz,',"
					+ zdz
					+ "',1,1)+1,length(tkz)-instr(tkz,',"
					+ zdz
					+ "',1,1)))"
					+ "else instr( substr(tkz, instr(tkz,',"
					+ zdz
					+ "',1,1)+1, length(tkz)-instr(tkz,',"
					+ zdz
					+ "',1,1)),',',1,1)-1 end )"
					+ " from dual) zdzstr,(select substr(substr(tkz,instr(tkz,',"
					+ qdz + "',1,1)+1,length(tkz)-instr(tkz,'," + qdz
					+ "',1,1)),1,instr(substr(tkz,instr(tkz,'," + qdz
					+ "',1,1)+1,length(tkz)-instr(tkz,'," + qdz
					+ "',1,1)),',',1,1)-1) from dual) qdzstr"
					+ " from view_hccc " + querry + ")";
				colListCN = dao.getColumnNameCN(colList, tableName);
				topTr = dao.arrayToList(colList, colListCN);
			}
			// long tt2 = System.currentTimeMillis();
			// System.out.println("׼����ѯ��"+(tt2-tt1));
			if (Globals.XXDM_SHGC.equalsIgnoreCase(xxdm) ) {
				if (dataType.equalsIgnoreCase("discipInfo") || dataType.equalsIgnoreCase("discipInfoHis")) {
					colList = new String[] { "����", "xh", "xm", "xn", "nd", "xqmc",
							"cflbmc", "cfsj", "cfwh", "cfyymc" };
//					sql = "select xh||xn||nd||sbsj ����,rownum r,a.xxsh,a.xh,a.xm,a.xymc,a.bjmc,a.nd,a.xn,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,a.cflbmc," +
//					"a.cfsj,a.cfwh,a.cfyymc from view_wjcf a ";
				}
			} 
//			sql=sql.replace("nd =", "nf =");
			rs = dao.rsToVator(sql, new String[] {}, colList);
			// System.out.println(sql);
			// long t3 = System.currentTimeMillis();
			// System.out.println("��ѯ�����"+(t3-t2));
			// String tmpsql = "select count(1) count from " + tableName + " a"
			// + querry;
			// if(xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)){
			// if(tableName.equalsIgnoreCase("view_gdnz_lpxx")){
			// tmpsql = "select count(1) count from " + tableName +" a" +
			// querry+" and sfxfzrx='" + request.getAttribute("sfxfzrx") + "'";
			// }
			// }
			if (Globals.XXDM_ZGKYDX.equalsIgnoreCase(xxdm) && "xlytqk".equalsIgnoreCase(dataType)) {
				sql = "select count(*) cout from view_xsjbxx a, xlytqkb x where 1=1 and a.xh=x.xh";
			}else{
//				String query=querry.toString().replace("nd =", "nf =");
				sql = "select count(*) cout from " + tableName + " a " + querry;
			}
			dataSearchForm.getPages().setMaxRecord(Integer.parseInt(dao .getOneRs(sql, new String[] {}, "cout")));
			// long t4 = System.currentTimeMillis();
			// System.out.println("��ҳ��ʱ��"+(t4-t3));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		if (isHTXX) {
			request.setAttribute("isHTXX", "is");
		} else {
			request.setAttribute("isHTXX", "no");
		}
		if (isZXDK_XSXXCX) {
			request.setAttribute("isXSXXCX", "is");
		} else {
			request.setAttribute("isXSXXCX", "no");
		}
		if (isFSBZ) {
			request.setAttribute("isFSBZ", "is");
		} else {
			request.setAttribute("isFSBZ", "no");
		}
		if (realTable.equalsIgnoreCase("xshsxfb")) {
			request.setAttribute("yfList", dao.getYfList());
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
			request.setAttribute("showbjlh", "showbjlh");
		}
		// TODO ��ҳ

		xy = xy == null ? "" : xy;
		zy = zy == null ? "" : zy;
		nj = nj == null ? "" : nj;
		String bjKey = xy + "!!" + zy + "!!" + nj;

		bzffny = bzffny == null ? "" : bzffny;
		HashMap<String, String> rs1 = new HashMap<String, String>();
		rs1.put("xjzt", xjzt);
		rs1.put("bzffny", bzffny);
		request.setAttribute("rs1", rs1);
		// List<HashMap<String, String>> temp = (Base.getBjMap()).get(bjKey);

		xxmc = dao.getOneRs("select xxmc from xtszb", new String[] {},
				new String[] { "xxmc" })[0];
		request.setAttribute("xxmc", xxmc);// ȡѧУ������Ϣ
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("act", dataType);// �������ݲ�ѯ����
		request.setAttribute("tips", tips);// ����λ����ʾ����Ϣ
		request.setAttribute("njList", Base.getNjList());// �����꼶�б�
		request.setAttribute("xnList", Base.getXnndList());// ����ѧ���б�
		request.setAttribute("xqList", Base.getXqList());// ����ѧ���б�		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
//		request.setAttribute("xyList", Base.getXyList());// ����ѧԺ�б�
//
//		List zyList = (Base.getZyMap()).get(xy);
//		List bjList = (Base.getBjMap()).get(bjKey);

		//	if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY) || xxdm.equalsIgnoreCase(Globals.XXDM_SHCBYSGDZKXX)) {

		if (isFdy.equalsIgnoreCase("true")) {

			if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				request.setAttribute("iscsmz", "fdy");
			}
			String fdyName = session.getAttribute("userName").toString();

			fdyName = !StringUtils.isNull(fdyName) ? fdyName : "";
//			sql = "select bjdm from fdybjb where zgh=?";
//			String[] bjdmList = dao.getRs(sql, new String[]{fdyName}, "bjdm");
//			String[] zydmList = new String[]{};
//			String[] bjmcList = new String[bjdmList.length];
//			String[] zymcList = null;			
//			if (bjdmList != null && bjdmList.length > 0) {		
//			StringBuffer strSql = new StringBuffer("select zydm from view_njxyzybj where bjdm in (");
//			for (int i=0;i<bjdmList.length;i++) {
//			strSql.append("'");
//			strSql.append(bjdmList[i]);
//			strSql.append("',");
//			bjmcList[i] = dao.getOneRs("select bjmc from  view_njxyzybj where bjdm=?", new String[]{bjdmList[i]}, "bjmc");
//			}
//			sql = strSql.substring(0, strSql.length()-1).toString() + ") group by zydm";
//			zydmList = dao.getRs(sql, new String[]{}, "zydm");
//			if (zydmList != null && zydmList.length>0) {
//			zymcList = new String[zydmList.length];
//			for (int i=0;i<zydmList.length;i++) {
//			zymcList[i] = dao.getOneRs("select zymc from view_njxyzybj where zydm=?", new String[]{zydmList[i]}, "zymc");
//			}
//			}
//			}
//			bjList = arrayToList4(bjdmList, bjmcList);
			//����Ա��¼
//			request.setAttribute("bjList", Fdypd.getFdybjList (fdyName));// ���Ͱ༶�б�
//			request.setAttribute("zyList", Fdypd.getFdyZyList (fdyName));// ���Ͱ༶�б�	
//			zyList = Fdypd.getFdyZyList (fdyName);
		}
		//}

		//����ģ������ѧ����εĲ�ѯ����
		if(dataType.equalsIgnoreCase("active")||dataType.equalsIgnoreCase("prepare")||dataType.equalsIgnoreCase("party")) {
			String mySql = "select xsccdm, xsccmc from dtjs_xsccb";
			request.setAttribute("xsccList", dao.getList(mySql,new String[]{},new String[]{"xsccdm","xsccmc"}));// ���Ͱ༶�б�
		}
		String csjg;
		String csnj;
		Map cxmap = new HashMap();
//		List cxlist = new ArrayList();
		if("xlcsjgb".equals(realTable)){
			csjg = DealString.toGBK(request.getParameter("csjg"));
			csnj = DealString.toGBK(request.getParameter("csnj"));
			if(csjg != null || !"".equals(csjg)){
				cxmap.put("csjg", csjg);
			}
			if(csnj != null || !"".equals(csnj)){
				cxmap.put("csnj", csnj);
			}
		}
		request.setAttribute("cxrs", cxmap);

//		request.setAttribute("zyList", zyList);// ����רҵ�б�		
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);
		request.setAttribute("ndList", ndList);// ���5��������б�
//		if(session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")){
//			����Ա��¼
//			bjList = Fdypd.getFdybjList (userName);
//		}	
//		request.setAttribute("bjList", bjList);// ���Ͱ༶�б�



		if(dataType.equalsIgnoreCase("discipInfo")
				&&xxdm.equalsIgnoreCase(Globals.XXDM_NCDXKJXY)){//�ϲ���ѧ��ѧ����ѧԺ
			String tag = dao.returntag("select * from fdyxxb where zgh=? ", new String[]{userName});
			if ("notempty".equalsIgnoreCase(tag)) {//�����α��
				String mySql = "select a.bjdm, bjmc from fdybjb b,view_njxyzybj a where a.bjdm=b.bjdm  and  b.zgh=?";
				request.setAttribute("bjList", dao.getList(mySql,new String[]{userName},new String[]{"bjdm","bjmc"}));// ���Ͱ༶�б�
				isBzr = "yes";
			}else{
				isBzr = "no";
			}
		}
		request.setAttribute("userOnline",userOnline);
		request.setAttribute("isBzr", isBzr);
		String xsbbcx = request.getParameter("bbcx");
		if (isHTXX) {
			return new ActionForward("/sjcz/data_search_htxx.jsp", false);
		}if((realTable == "xszbbb" || realTable == "yktbbb" || realTable == "xhbbb" || realTable == "hcyhkbbb") && "bbcx".equals(xsbbcx)) {
			return new ActionForward("/sjcz/xszbbcx.jsp", false);
		}if(dataType.equalsIgnoreCase("xsbbcx")) {
			return new ActionForward("/sjcz/xszbbcx.jsp", false);
		}else if(isXLJKTJ){
			return new ActionForward("/sjcz/xljkjyqktj.jsp", false);
		}else if(isTSQTSQCX){
			return new ActionForward("/sjcz/tsqtxssqcx.jsp", false);
		}else if(isTSQTGJ){
			return new ActionForward("/sjcz/tsqtgj.jsp", false);
		}else if(isBJXZRY){
			return new ActionForward("/sjcz/bjxzry.jsp", false);
		}else if(isXLZXYY){
			return new ActionForward("/sjcz/xlzxyydj.jsp", false);
		}else if(isTSXSDJ){
			return new ActionForward("/sjcz/tsqtxsdj.jsp", false);
		}else if(isSZDTXX){
			return new ActionForward("/sjcz/szdtxx.jsp", false);
		}else if(isXSJBXX){
			return new ActionForward("/sjcz/gzxsjbqk.jsp", false);
		}else if(isXSZZQK){
			return new ActionForward("/sjcz/gzqk.jsp", false);
		}else if(isXLJKHD){
			return new ActionForward("/sjcz/xljkjyhd.jsp", false);
		}else if(isXLPC){
			return new ActionForward("/sjcz/xqpcyt.jsp", false);
		}else if(isJZJS){
			return new ActionForward("/sjcz/jzjsxx.jsp", false);
		}else if(isZXQK){ 
			return new ActionForward("/sjcz/xlzxqk.jsp", false);
		}else if (isZXDK_XSXXCX) {
			return new ActionForward("/sjcz/data_search_xsxxcx.jsp", false);
		} else if (isFSBZ) {
			return new ActionForward("/sjcz/data_search_fsbz.jsp", false);
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_JHZYJSXY)&&(dataType.equalsIgnoreCase("active")||dataType.equalsIgnoreCase("prepare"))) {
			return new ActionForward("/dtjs/jhzy/data_search_jhzy.jsp", false);
		}  else {
			return mapping.findForward("success");
		}
	}

	/**
	 * �趨��ѯʱ��Ҫ������ 
	 * @param session
	 * @param dao
	 * @param colList
	 * @param tableName
	 * @return
	 */
	private DataManModel getSearchModel(HttpSession session, DAO dao,
			String[] colList, String tableName) {
		DataManModel dataManModel = (DataManModel) session
		.getAttribute("dataManModel");
		if (dataManModel == null) {
			dataManModel = new DataManModel();
			dataManModel.setColListCN(dao, tableName, colList);
			dataManModel.setTopTr(dao, tableName, colList);
			session.setAttribute("dataManModel", dataManModel);
		} else if (!(dataManModel.containTable(tableName))) {
			dataManModel.setColListCN(dao, tableName, colList);
			dataManModel.setTopTr(dao, tableName, colList);
			session.setAttribute("dataManModel", dataManModel);
		}
		return dataManModel;
	}

	private ActionForward modiData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// �������ݲ�ѯ���޸ġ����
		ActionForward newFwd = new ActionForward();
		CommanForm dataSearchForm = (CommanForm) form;
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String pk1 = request.getParameter("pk");
		String pk = Base.isNull(pk1)?"": pk1.replaceAll("-", "||");
		//String pk = pk1.replaceAll("-", "||");
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String[] pkValueA = pkValue.split("!!SplitOneSplit!!");
		String realTable = request.getParameter("realTable");
		String tableName = request.getParameter("tableName");
		String doType = request.getParameter("doType");
		String from = request.getParameter("from");
		boolean ok = false;
		String[] dqndq = dao.getOneRs("select dqxn,dqxq,dqnd from xtszb", new String[] {}, new String[] { "dqxn", "dqxq", "dqnd" });
		String sql = "";
		String xxmc = StandardOperation.getXxmc();
		String xxdm = StandardOperation.getXxdm();
		String dxq = request.getParameter("dxq");
		String dxq1 = request.getParameter("writeAble");

		if (!("".equalsIgnoreCase(dxq) || dxq == null)) {
			writeAble = dxq;
		} else {
			if (!("".equalsIgnoreCase(dxq1) || dxq1 == null)) {
				writeAble = dxq1;
			}
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
			request.setAttribute("showhzy", "showhzy");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			request.setAttribute("shownblg", "shownblg");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)) {
			request.setAttribute("showzszy", "showzszy");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {
			request.setAttribute("showjsxx", "showjsxx");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
			request.setAttribute("showbjlh", "showbjlh");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
			request.setAttribute("showhzy", "showhzy");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)) {
			request.setAttribute("showGdnz", "showGdnz");
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)){
			request.setAttribute("showCsmzzyjsxy","showCsmzzyjsxy");
		}
		/*if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
			request.setAttribute("showXbemy", "showXbemy");
		}*/
		
		if ((doType == null) || doType.equalsIgnoreCase("")) {
			// �����쳣
			dataSearchForm.setErrMsg("sdf");
			return mapping.findForward("false");
		} else if (doType.equalsIgnoreCase("del")) {
			// ɾ������
			if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)) {
				if (pkValueA != null) {
					for (int i = 0; i < pkValueA.length; i++) {
						String tempPk = pkValueA[i];
						String[] temp = dao.getOneRs(
								"select xh,hth from zxdk_htxx where xh||hth=? and rownum=1",
								new String[] { tempPk }, new String[] {
										"xh", "hth" });
						String xh = "";
						String hth = "";
						if (temp != null) {
							xh = temp[0];
							hth = temp[1];
						}
						sql = "select xh,hth1,hth2,hth3,hth4,replace(NVL(dkze,'0'),' ','0') dkze,replace(NVL(ht1_zje,'0'),' ','0') ht1_zje,"
							+ "replace(NVL(ht2_zje,'0'),' ','0') ht2_zje,replace(NVL(ht3_zje,'0'),' ','0') ht3_zje,replace(NVL(ht4_zje,'0'),' ','0') ht4_zje from zxdk_xsjbxx where xh=? and rownum=1";
						String[] xsxx = dao.getOneRs(sql, new String[] { xh },
								new String[] { "xh", "hth1", "hth2", "hth3",
								"hth4", "dkze", "ht1_zje", "ht2_zje",
								"ht3_zje", "ht4_zje" });
						int tempje = 0;
						if (xsxx == null) {
						} else {
							if (hth.equalsIgnoreCase(xsxx[1])) {
								tempje = Integer.parseInt(xsxx[5])
								- Integer.parseInt(xsxx[6]);
								sql = "update zxdk_xsjbxx set hth1=?,ht1_jbjrjg=?,ht1_fzjgmc=?,ht1_pzrq=?,ht1_zje=?,"
									+ "ht1_jby=?,ht1_pzhz=?,ht1_hkksrq=?,ht1_hkjzrq=?,ht1_zqly=?,ht1_zqsj=?,"
									+ "ht1_dkfs=?,ht1_hkfs=?,ht1_hkjzmc=?,ht1_hkjzzh=?,ht1_tqyhbxje=?,ht1_tqjzsj=?,"
									+ "ht1_tqbz=?,dkze=? where xh=?";
								dao.runUpdate(sql, new String[] { "", "", "",
										"", "", "", "", "", "", "", "", "", "",
										"", "", "", "", "",
										String.valueOf(tempje), xh });
							} else if (hth.equalsIgnoreCase(xsxx[2])) {
								tempje = Integer.parseInt(xsxx[5])
								- Integer.parseInt(xsxx[7]);
								sql = "update zxdk_xsjbxx set hth2=?,ht2_jbjrjg=?,ht2_fzjgmc=?,ht2_pzrq=?,ht2_zje=?,"
									+ "ht2_jby=?,ht2_pzhz=?,ht2_hkksrq=?,ht2_hkjzrq=?,ht2_zqly=?,ht2_zqsj=?,"
									+ "ht2_dkfs=?,ht2_hkfs=?,ht2_hkjzmc=?,ht2_hkjzzh=?,ht2_tqyhbxje=?,ht2_tqjzsj=?,"
									+ "ht2_tqbz=?,dkze=? where xh=?";
								dao.runUpdate(sql, new String[] { "", "", "",
										"", "", "", "", "", "", "", "", "", "",
										"", "", "", "", "",
										String.valueOf(tempje), xh });
							} else if (hth.equalsIgnoreCase(xsxx[3])) {
								tempje = Integer.parseInt(xsxx[5])
								- Integer.parseInt(xsxx[8]);
								sql = "update zxdk_xsjbxx set hth3=?,ht3_jbjrjg=?,ht3_fzjgmc=?,ht3_pzrq=?,ht3_zje=?,"
									+ "ht3_jby=?,ht3_pzhz=?,ht3_hkksrq=?,ht3_hkjzrq=?,ht3_zqly=?,ht3_zqsj=?,"
									+ "ht3_dkfs=?,ht3_hkfs=?,ht3_hkjzmc=?,ht3_hkjzzh=?,ht3_tqyhbxje=?,ht3_tqjzsj=?,"
									+ "ht3_tqbz=?,dkze=? where xh=?";
								dao.runUpdate(sql, new String[] { "", "", "",
										"", "", "", "", "", "", "", "", "", "",
										"", "", "", "", "",
										String.valueOf(tempje), xh });
							} else if (hth.equalsIgnoreCase(xsxx[4])) {
								tempje = Integer.parseInt(xsxx[5])
								- Integer.parseInt(xsxx[9]);
								sql = "update zxdk_xsjbxx set hth4=?,ht4_jbjrjg=?,ht4_fzjgmc=?,ht4_pzrq=?,ht4_zje=?,"
									+ "ht4_jby=?,ht4_pzhz=?,ht4_hkksrq=?,ht4_hkjzrq=?,ht4_zqly=?,ht4_zqsj=?,"
									+ "ht4_dkfs=?,ht4_hkfs=?,ht4_hkjzmc=?,ht4_hkjzzh=?,ht4_tqyhbxje=?,ht4_tqjzsj=?,"
									+ "ht4_tqbz=?,dkze=? where xh=?";
								dao.runUpdate(sql, new String[] { "", "", "",
										"", "", "", "", "", "", "", "", "", "",
										"", "", "", "", "",
										String.valueOf(tempje), xh });
							}
						}
					}
				}
			}
			String type = "";
			if (realTable.equalsIgnoreCase("gdnz_lpxxb")) {
				type = request.getParameter("type");
			}
			for (int i = 1; i < pkValueA.length; i++) {
				if (realTable.equalsIgnoreCase("gdnz_lpxxb")) {
					sql = "delete from " + realTable + " where " + pk + "='"
					+ pkValueA[i] + "'";
					sql += " and sfxfzrx=" + type;
					dao.runUpdate(sql, new String[] {});
				}
				if (realTable.equalsIgnoreCase("xlytqkb")){
					pk = "yt_id";
				}
				//=================2009/7/8 luojw ===================
				if (realTable.equalsIgnoreCase("gywsjcb")&&Globals.XXDM_HHGXY.equalsIgnoreCase(xxdm)){
					pk = "xn||xq||jcsj||ssbh";
					pkValue=pkValueA[i].replace("��", "").replace("��", "");
				}
				StandardOperation.delete(realTable, pk, pkValueA[i], request);
				if(realTable.equalsIgnoreCase("gwxxb")){
					StandardOperation.delete("delete from xsgwxxb a where gwdm||gwsbsj='"+pkValueA[i]+"'", "xsgwxxb", request);
				}
			}
			if (realTable.equalsIgnoreCase("dwjlxxb")) {
				newFwd = new ActionForward("/data_search2.do?go=go&act=" + from
						+ "&writeAble=" + writeAble, false);
			} else if (realTable.equalsIgnoreCase("zgkd_rdsqb")) {
				newFwd = new ActionForward("/zgkdDyxx.do?method=rdsq&go=go&act="
						+ from + "&writeAble=" + writeAble, false);
			} else if (realTable.equalsIgnoreCase("sjb")) {
				newFwd = new ActionForward("/test_paper_search.do?go=go&act="
						+ from + "&writeAble=" + writeAble, false);
			} else if (realTable.equalsIgnoreCase("stb")) {
				newFwd = new ActionForward(
						"/test_question_search.do?go=go&act=" + from + "&writeAble=" + writeAble, false);
			} else if (realTable.equalsIgnoreCase("xxb")) {
				newFwd = new ActionForward("/test_option_search.do?go=go&act="
						+ from + "&writeAble=" + writeAble, false);
			} else if (realTable.equalsIgnoreCase("sjstb")) {
				newFwd = new ActionForward(
						"/test_paperQuestion_search.do?go=go&pkValue=&act="
						+ from + "&writeAble=" + writeAble, false);
			} else if (realTable.equalsIgnoreCase("ssxxb")) {
				newFwd = new ActionForward("/ssxx_search.do?go=go&act=" + from
						+ "&writeAble=" + writeAble, false);
			} else if (realTable.equalsIgnoreCase("xszsxxb")) {
				newFwd = new ActionForward("/dorm_Using_Search.do?go=go&act="
						+ from + "&writeAble=" + writeAble, false);
			} else if (realTable.equalsIgnoreCase("hcccb")) {
				newFwd = new ActionForward("/train_time_search.do?go=go&act="
						+ from + "&writeAble=" + writeAble, false);
			} else if (realTable.equalsIgnoreCase("gwxxb")) {
				newFwd = new ActionForward("/data_search2.do?go=go&act=" + from
						+ "&writeAble=" + writeAble, false);
			} else if (realTable.equalsIgnoreCase("gywxglb")) {
				newFwd = new ActionForward("/gywx.do?go=go" + "&writeAble="
						+ writeAble, false);
			} else if (realTable.equalsIgnoreCase("gywsjcb")) {
				if (Globals.XXDM_HHGXY.equalsIgnoreCase(xxdm)) {
					StandardOperation.update("hhgxy_zrsgl", new String[] { "cj" },
							new String[] { "0" }, "xn||xq||ssbh||zs",
							pkValue, request);
				}
				newFwd = new ActionForward("/wsjc.do?go=go" + "&writeAble="
						+ writeAble, false);
			} else if (realTable.equalsIgnoreCase("zsjlb")) {
				newFwd = new ActionForward("/zsjl.do?go=go" + "&writeAble="
						+ writeAble, false);
			} else if (realTable.equalsIgnoreCase("mrzbjlb")) {
				newFwd = new ActionForward("/daily_note_search.do?go=go"
						+ "&writeAble=" + writeAble, false);
			} else if (realTable.equalsIgnoreCase("yzzbhzb")) {
				newFwd = new ActionForward("/weekly_collect_search.do?go=go"
						+ "&writeAble=" + writeAble, false);
			} else if (realTable.equalsIgnoreCase("ssydxxb")) {
				newFwd = new ActionForward("/ssyd.do?go=go" + "&writeAble="
						+ writeAble, false);
			} else if (realTable.equalsIgnoreCase("wmqspbb")) {
				newFwd = new ActionForward("/wmqspb.do?go=go" + "&writeAble="
						+ writeAble, false);
			} else if (realTable.equalsIgnoreCase("jfhbb")) {
				newFwd = new ActionForward(
						"/work_outlay_search.do?go=go&act=workOutlay"
						+ "&writeAble=" + writeAble, false);
			} else  if(realTable.equals("xlytqkb")){
				newFwd = new ActionForward("/xljk_ytqk.do?go=go&act=" + from
						+ "&writeAble=" + writeAble, false);
			}else {
				newFwd = new ActionForward("/data_search.do?go=go&act=" + from
						+ "&writeAble=" + writeAble, false);
			}
		} else if (doType.equalsIgnoreCase("save")) {
			// ��������
			String xh = dataSearchForm.getXh();
			String nd = dataSearchForm.getNd();
			String xn = dataSearchForm.getXn();
			String xq = dataSearchForm.getXq();
			//------------2010/6/25 edit by luojw-----------------------------
			String url = request.getParameter("url");
			newFwd = Base.isNull(url) ? null : new ActionForward(url, false);
			if (realTable.equalsIgnoreCase("xspxxxb")) {
				// ��ѵ��Ϣ
				String pxxmdm = request.getParameter("pxxmdm");
				String pxjg = DealString.toGBK(request.getParameter("pxjg"));
				String pxkssj = request.getParameter("pxkssj").replaceAll("-",
				"");
				String pxjssj = request.getParameter("pxjssj").replaceAll("-",
				"");
				String bz = DealString.toGBK(request.getParameter("bz"));
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "delete from xspxxxb where xn=? and xq=? and xh=? and pxxmdm=?";
					del = dao.runUpdate(sql,
							new String[] { xn, xq, xh, pxxmdm });
				} else {
					sql = "delete from xspxxxb where " + pk + "='" + pkValue
					+ "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into xspxxxb(nd,xn,xq,xh,pxxmdm,pxjg,pxkssj,pxjssj,bz) values(?,?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] { nd, xn, xq, xh, pxxmdm,
							pxjg, pxkssj, pxjssj, bz });
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("rdjjfzxxb")) {
				// �뵳��������
				String pxkssj = request.getParameter("kssj")
				.replaceAll("-", "");
				String bz = DealString.toGBK(request.getParameter("bz"));
				String tjdw = DealString.toGBK(request.getParameter("tjdw"));
				String rzqk = DealString.toGBK(request.getParameter("rzqk"));
				String lxr2 = DealString.toGBK(request.getParameter("lxr2"));
				String pysj = DealString.toGBK(request.getParameter("pysj"));
				String lxr1 = DealString.toGBK(request.getParameter("lxr1"));
				String pycs = DealString.toGBK(request.getParameter("pycs"));
				String ssbx1 = DealString.toGBK(request.getParameter("ssbx1"));
				String ssbx2= DealString.toGBK(request.getParameter("ssbx2"));
				String ssbx3 = DealString.toGBK(request.getParameter("ssbx3"));
				String ssbx4 = DealString.toGBK(request.getParameter("ssbx4"));
				String xsccdm = DealString.toGBK(request.getParameter("xsccdm"));
				String lwjjfzsj = DealString.toGBK(request.getParameter("lwjjfzsj"));
				String lwjhfzdxdsj= DealString.toGBK(request.getParameter("lwjhfzdxdsj"));
				String zbdhtggrrdsj = DealString.toGBK(request.getParameter("zbdhtggrrdsj"));
				String pyr = DealString.toGBK(request.getParameter("pyr"));
				String fzlx = DealString.toGBK(request.getParameter("fzlx"));
				String zbqdsj = DealString
				.toGBK(request.getParameter("zbqdsj"));
				String sfpydy = DealString
				.toGBK(request.getParameter("sfpydy"));
				String thcs = DealString.toGBK(request.getParameter("thcs"));
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "delete from " + realTable
					+ " where xn=? and xq=? and xh=?";
					del = dao.runUpdate(sql, new String[] { xn, xq, xh });
				} else {
					sql = "delete from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
						sql = "insert into "
							+ realTable
							+ "(nd,xn,xq,xh,kssj,bz,tjdw,rzqk,lxr1,lxr2,xsccdm) values(?,?,?,?,?,?,?,?,?,?,?)";
						dao.runUpdate(sql, new String[] { nd, xn, xq, xh,
								pxkssj, bz, tjdw, rzqk, lxr1, lxr2,xsccdm });
					} else {
						sql = "insert into "
							+ realTable
							+ "(nd,xn,xq,xh,lxr1,kssj,bz,pysj,pycs,zbqdsj,sfpydy,thcs,xsccdm,ssbx1,ssbx2,ssbx3,ssbx4,lwjjfzsj,lwjhfzdxdsj,zbdhtggrrdsj,pyr,fzlx) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						dao.runUpdate(sql, new String[] { nd, xn, xq, xh, lxr1,
								pxkssj, bz, pysj, pycs, zbqdsj, sfpydy, thcs,xsccdm,ssbx1,ssbx2,ssbx3,ssbx4,lwjjfzsj,lwjhfzdxdsj,zbdhtggrrdsj,pyr,fzlx });
					}
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
				//	=============== begin �㽭��ýѧԺ ���ΰ 2009/3/9 =============
				if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
					String fzsj = request.getParameter("fzsj");
					DtjszjcmService service = new DtjszjcmService();
					if (!service.addFzdx(nd, xn, xq, xh, fzsj, bz, request)) {
						dataSearchForm.setErrMsg("sdf");
						return mapping.findForward("false");
					}
				}
				// =============== end �㽭��ýѧԺ ���ΰ 2009/3/9 ============
			} else if (realTable.equalsIgnoreCase("jszhkpb")) {
				// ��ʵ�ۺϿ���
				String sxpdpj = DealString
				.toGBK(request.getParameter("sxpdpj"));
				String zssppj = DealString
				.toGBK(request.getParameter("zssppj"));
				String xynlpj = DealString
				.toGBK(request.getParameter("xynlpj"));
				String nlpj = DealString.toGBK(request.getParameter("nlpj"));
				String szpj = DealString.toGBK(request.getParameter("szpj"));
				String xf = request.getParameter("xf");
				String bz = DealString.toGBK(request.getParameter("bz"));
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "delete from " + realTable
					+ " where xn=? and xq=? and xh=?";
					del = dao.runUpdate(sql, new String[] { xn, xq, xh });
				} else {
					sql = "delete from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into "
						+ realTable
						+ "(nd,xn,xq,xh,sxpdpj,zssppj,xynlpj,nlpj,szpj,xf,bz)"
						+ " values(?,?,?,?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] { nd, xn, xq, xh, sxpdpj,
							zssppj, xynlpj, nlpj, szpj, xf, bz });
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("xsjxhjb")) {
				// ��ѵ��
				String hjsj = request.getParameter("hjsj").replaceAll("-", "");
				String jxdm = request.getParameter("jxdm");
				String bz = DealString.toGBK(request.getParameter("bz"));
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					del = StandardOperation.delete(realTable, new String[] {
							"hjsj", "xh" }, new String[] { hjsj, xh }, request);
				} else {
					del = StandardOperation.delete(realTable, pk, pkValue,
							request);
				}
				if (del) {
					StandardOperation.insert(realTable, new String[] { "nd",
							"xn", "xq", "xh", "hjsj", "jxdm", "bz" },
							new String[] { nd, xn, xq, xh, hjsj, jxdm, bz },
							request);
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("zhszcp")) {
				// �ۺ����ʲ�����Ϣ
				String zpf = request.getParameter("zpf");
				String dcj = DealString.toGBK(request.getParameter("dcj"));
				String xydykpf = DealString.toGBK(request
						.getParameter("xydykpf"));
				String gydykpf = DealString.toGBK(request
						.getParameter("gydykpf"));
				String zcj = DealString.toGBK(request.getParameter("zcj"));
				String tcj = DealString.toGBK(request.getParameter("tcj"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				String jnjf = DealString.toGBK(request.getParameter("jnjf"));
				String gzxxcx = DealString
				.toGBK(request.getParameter("gzxxcx"));
				String cpzf = DealString.toGBK(request.getParameter("cpzf"));
				String znszcj = DealString
				.toGBK(request.getParameter("znszcj"));
				String zhszcpzf = DealString
				.toGBK(request.getParameter("cpzf"));
				String cxcj = DealString.toGBK(request.getParameter("cxcj"));//�㶫���Ʋ��гɼ�
				cxcj = !StringUtils.isNull(cxcj) ? cxcj : "";
				String kcjqpfj = request.getParameter("kcjqpfj");
				kcjqpfj = StringUtils.isNull(kcjqpfj) ? "0" : kcjqpfj;
				String ddcj = request.getParameter("ddcj");
				ddcj = StringUtils.isNull(ddcj) ? "0" : ddcj;
				String xwcj = request.getParameter("xwcj");
				xwcj = StringUtils.isNull(xwcj) ? "0" : xwcj;
				String shqcj = request.getParameter("shqcj");
				shqcj = StringUtils.isNull(shqcj) ? "0" : shqcj;
				float dyzf = 0;
				if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {//���ս���������ϸ
					zhszcpzf = DealString.toGBK(request.getParameter("zhszcpzf"));
//					String zxq = request.getParameter("zxq");
					String zgdy = request.getParameter("zgdy");
					String zgybdy = request.getParameter("zgybdy");
					String xjbj = request.getParameter("xjbj");
					String wmss = request.getParameter("wmss");
					String xsgb1 = request.getParameter("xsgb1");
					String xsgb2 = request.getParameter("xsgb2");
					String xsgb3 = request.getParameter("xsgb3");
					String xsgb4 = request.getParameter("xsgb4");
					String wysp = request.getParameter("wysp");
					String jsjsp = request.getParameter("jsjsp");
					String xjjxj1 = request.getParameter("xjjxj1");
					String xjjxj2 = request.getParameter("xjjxj2");
					String xjjxj3 = request.getParameter("xjjxj3");
					String gjjl1 = request.getParameter("gjjl1");
					String gjjl2 = request.getParameter("gjjl2");
					String gjjl3 = request.getParameter("gjjl3");
					String sjjl1 = request.getParameter("sjjl1");
					String sjjl2 = request.getParameter("sjjl2");
					String sjjl3 = request.getParameter("sjjl3");
					String xjgr1 = request.getParameter("xjgr1");
					String xjgr2 = request.getParameter("xjgr2");
					String zf = request.getParameter("zf");
					dao.runUpdate("delete from sjdxspfxzb where xh=?", new String[]{xh});
					dao.runUpdate(
							"insert into sjdxspfxzb (xh,zgdy,zgybdy,xjbj,wmss,xsgb1,xsgb2,xsgb3,xsgb4,wysp,jsjsp,xjjxj1,xjjxj2,xjjxj3,gjjl1,gjjl2,gjjl3,sjjl1,sjjl2,sjjl3,xjgr1,xjgr2,zf) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
							new String[] { xh, zgdy, zgybdy, xjbj,
									wmss, xsgb1, xsgb2, xsgb3, xsgb4,
									wysp, jsjsp, xjjxj1, xjjxj2,
									xjjxj3, gjjl1, gjjl2, gjjl3, sjjl1,
									sjjl2, sjjl3, xjgr1, xjgr2, zf});
				}
				if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
					/*if (!StringUtils.isNull(dcj) && !StringUtils.isNull(kcjqpfj)) {
						int tmp = (int)((Float.parseFloat(ddcj)*10/100 + Float.parseFloat(xwcj)*10/100 + Float.parseFloat(shqcj)*5/100 + Float.parseFloat(kcjqpfj)*75/100)*10);
						zhszcpzf = tmp/10.0 + "";
					} else {
						zhszcpzf = "";
					}*/
					dyzf = Float.parseFloat(ddcj) + Float.parseFloat(xwcj) + Float.parseFloat(shqcj);
					zhszcpzf = (Float.parseFloat(kcjqpfj)*75/100 + dyzf) + "";
					zhszcpzf = dao.getOneRs("select trunc("+zhszcpzf+",2) zhszcpzf from dual", new String[]{}, "zhszcpzf");
				}
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "delete from zhszcp where xn=? and xh=? and nd=? and xq = ?";
					del = dao.runUpdate(sql, new String[] { xn, xh, nd, xq });
				} else {
					sql = "delete from zhszcp where " + pk + "='" + pkValue
					+ "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
						sql = "insert into zhszcp(nd,xn,xq,xh,dcj,zcj,tcj,bz,znszcj,jnjf,zhszcpzf,kcjqpfj,ddcj,xwcj,shqcj) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						dao.runUpdate(sql, new String[] { nd, xn, xq, xh, dcj, zcj,
								tcj, bz, znszcj,jnjf,zhszcpzf,kcjqpfj,ddcj,xwcj,shqcj});
					} else {
						ToolClass.getZhszcp(dao, xxmc, xh, nd, xn, xq, dcj,
								xydykpf, gydykpf, zcj, tcj, bz, jnjf, gzxxcx, cpzf,
								znszcj, zhszcpzf, cxcj, zpf);
					}
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("knssqb")) {
				// ��������Ϣ
				String lxdh = request.getParameter("lxdh");
				String sqyy = DealString.toGBK(request.getParameter("sqyy"));
				String cxsj = request.getParameter("cxsj");
				String bz = DealString.toGBK(request.getParameter("bz"));
				String xysh = DealString.toGBK(request.getParameter("xysh"));
				String xxsh = DealString.toGBK(request.getParameter("xxsh"));
				String yjfsbj = request.getParameter("yjfsbj");
				String shsj = dao.dateToStr(request.getParameter("shsj"));
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "delete from knssqb where xn=? and xh=? and xq=?";
					del = dao.runUpdate(sql, new String[] { xn, xh, xq });
				} else {
					sql = "delete from knssqb where " + pk + "='" + pkValue
					+ "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into knssqb(nd,xn,xq,xh,lxdh,sqyy,cxsj,bz,xysh,xxsh,yjfsbj,shsj) values(?,?,?,?,?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] { nd, xn, xq, xh, lxdh,
							sqyy, cxsj, bz, xysh, xxsh, yjfsbj, shsj });
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("zxdk_htxx")) {
				// �ۺ����ʲ�����Ϣ
				String hth = DealString.toGBK(dataSearchForm.getHth());
				String htjbjrjg = DealString
				.toGBK(dataSearchForm.getHtjbjrjg());
				String htfzjgmc = DealString
				.toGBK(dataSearchForm.getHtfzjgmc());
				String htpzrq = DealString.toGBK(dataSearchForm.getHtpzrq());
				String htzje = DealString.toGBK(dataSearchForm.getHtzje());
				String htjby = DealString.toGBK(dataSearchForm.getHtjby());
				String htpzhz = DealString.toGBK(dataSearchForm.getHtpzhz());
				String hthkksrq = DealString
				.toGBK(dataSearchForm.getHthkksrq());
				String hthkjzrq = DealString
				.toGBK(dataSearchForm.getHthkjzrq());
				String htzqly = DealString.toGBK(dataSearchForm.getHtzqly());
				String htzqsj = DealString.toGBK(dataSearchForm.getHtzqsj());
				String htdkfs = DealString.toGBK(dataSearchForm.getHtdkfs());
				String hthkfs = DealString.toGBK(dataSearchForm.getHthkfs());
				String hthkjzmc = DealString
				.toGBK(dataSearchForm.getHthkjzmc());
				String hthkjzzh = DealString
				.toGBK(dataSearchForm.getHthkjzzh());
				String httqyhbxje = DealString.toGBK(dataSearchForm
						.getHttqyhbxje());
				String httqjzsj = DealString.toGBK(dataSearchForm.getHttqbz());
				String httqbz = DealString.toGBK(dataSearchForm.getHttqbz());
				String dkll = DealString.toGBK(dataSearchForm.getDkll());
				String dklb = DealString.toGBK(dataSearchForm.getDklb());
				String bz = DealString.toGBK(dataSearchForm.getBz());
				String sfzh = DealString.toGBK(dataSearchForm.getSfzh());
				if ((null == sfzh) || ("".equalsIgnoreCase(sfzh))) {
					sfzh = dao
					.getOneRs(
							"select sfzh from bks_xsjbxx where xh=? and rownum=1",
							new String[] { xh },
							new String[] { "sfzh" })[0];
				}
				String xm = dao.getOneRs(
						"select xm from bks_xsjbxx where xh=? and rownum=1",
						new String[] { xh }, new String[] { "xm" })[0];
				pk = xh + hth;
				boolean del = false;
				boolean full = false;
				sql = "select xh,hth1,hth2,hth3,hth4,replace(NVL(dkze,'0'),' ','0') dkze,replace(NVL(ht1_zje,'0'),' ','0') ht1_zje,"
					+ "replace(NVL(ht2_zje,'0'),' ','0') ht2_zje,replace(NVL(ht3_zje,'0'),' ','0') ht3_zje,replace(NVL(ht4_zje,'0'),' ','0') ht4_zje from zxdk_xsjbxx where xh=? and rownum=1";
				String[] xsxx = dao.getOneRs(sql, new String[] { xh },
						new String[] { "xh", "hth1", "hth2", "hth3", "hth4",
						"dkze", "ht1_zje", "ht2_zje", "ht3_zje",
				"ht4_zje" });
				if (xsxx == null) {
					request.setAttribute("isNULL", "is");
				} else {
					int tempje = 0;
					if (hth.equalsIgnoreCase(xsxx[1])
							|| (" ".equalsIgnoreCase(xsxx[1]))
							|| (xsxx[1] == null)) {
						sql = "update zxdk_xsjbxx set hth1=?,ht1_jbjrjg=?,ht1_fzjgmc=?,ht1_pzrq=?,ht1_zje=?,"
							+ "ht1_jby=?,ht1_pzhz=?,ht1_hkksrq=?,ht1_hkjzrq=?,ht1_zqly=?,ht1_zqsj=?,"
							+ "ht1_dkfs=?,ht1_hkfs=?,ht1_hkjzmc=?,ht1_hkjzzh=?,ht1_tqyhbxje=?,ht1_tqjzsj=?,"
							+ "ht1_tqbz=?,dkze=? where xh=?";
						tempje = Integer.parseInt(xsxx[5])
						- Integer.parseInt(xsxx[6])
						+ Integer.parseInt(htzje);
						dao.runUpdate(sql, new String[] { hth, htjbjrjg,
								htfzjgmc, htpzrq, htzje, htjby, htpzhz,
								hthkksrq, hthkjzrq, htzqly, htzqsj, htdkfs,
								hthkfs, hthkjzmc, hthkjzzh, httqyhbxje,
								httqjzsj, httqbz, String.valueOf(tempje), xh });
					} else if (hth.equalsIgnoreCase(xsxx[2])
							|| (" ".equalsIgnoreCase(xsxx[2]))
							|| (xsxx[2] == null)) {
						sql = "update zxdk_xsjbxx set hth2=?,ht2_jbjrjg=?,ht2_fzjgmc=?,ht2_pzrq=?,ht2_zje=?,"
							+ "ht2_jby=?,ht2_pzhz=?,ht2_hkksrq=?,ht2_hkjzrq=?,ht2_zqly=?,ht2_zqsj=?,"
							+ "ht2_dkfs=?,ht2_hkfs=?,ht2_hkjzmc=?,ht2_hkjzzh=?,ht2_tqyhbxje=?,ht2_tqjzsj=?,"
							+ "ht2_tqbz=?,dkze=? where xh=?";
						tempje = Integer.parseInt(xsxx[5])
						- Integer.parseInt(xsxx[7])
						+ Integer.parseInt(htzje);
						dao.runUpdate(sql, new String[] { hth, htjbjrjg,
								htfzjgmc, htpzrq, htzje, htjby, htpzhz,
								hthkksrq, hthkjzrq, htzqly, htzqsj, htdkfs,
								hthkfs, hthkjzmc, hthkjzzh, httqyhbxje,
								httqjzsj, httqbz, String.valueOf(tempje), xh });
					} else if (hth.equalsIgnoreCase(xsxx[3])
							|| (" ".equalsIgnoreCase(xsxx[3]))
							|| (xsxx[3] == null)) {
						sql = "update zxdk_xsjbxx set hth3=?,ht3_jbjrjg=?,ht3_fzjgmc=?,ht3_pzrq=?,ht3_zje=?,"
							+ "ht3_jby=?,ht3_pzhz=?,ht3_hkksrq=?,ht3_hkjzrq=?,ht3_zqly=?,ht3_zqsj=?,"
							+ "ht3_dkfs=?,ht3_hkfs=?,ht3_hkjzmc=?,ht3_hkjzzh=?,ht3_tqyhbxje=?,ht3_tqjzsj=?,"
							+ "ht3_tqbz=?,dkze=? where xh=?";
						tempje = Integer.parseInt(xsxx[5])
						- Integer.parseInt(xsxx[8])
						+ Integer.parseInt(htzje);
						dao.runUpdate(sql, new String[] { hth, htjbjrjg,
								htfzjgmc, htpzrq, htzje, htjby, htpzhz,
								hthkksrq, hthkjzrq, htzqly, htzqsj, htdkfs,
								hthkfs, hthkjzmc, hthkjzzh, httqyhbxje,
								httqjzsj, httqbz, String.valueOf(tempje), xh });
					} else if (hth.equalsIgnoreCase(xsxx[4])
							|| " ".equalsIgnoreCase(xsxx[4])
							|| (xsxx[4] == null)) {
						sql = "update zxdk_xsjbxx set hth4=?,ht4_jbjrjg=?,ht4_fzjgmc=?,ht4_pzrq=?,ht4_zje=?,"
							+ "ht4_jby=?,ht4_pzhz=?,ht4_hkksrq=?,ht4_hkjzrq=?,ht4_zqly=?,ht4_zqsj=?,"
							+ "ht4_dkfs=?,ht4_hkfs=?,ht4_hkjzmc=?,ht4_hkjzzh=?,ht4_tqyhbxje=?,ht4_tqjzsj=?,"
							+ "ht4_tqbz=?,dkze=? where xh=?";
						tempje = Integer.parseInt(xsxx[5])
						- Integer.parseInt(xsxx[9])
						+ Integer.parseInt(htzje);
						dao.runUpdate(sql, new String[] { hth, htjbjrjg,
								htfzjgmc, htpzrq, htzje, htjby, htpzhz,
								hthkksrq, hthkjzrq, htzqly, htzqsj, htdkfs,
								hthkfs, hthkjzmc, hthkjzzh, httqyhbxje,
								httqjzsj, httqbz, String.valueOf(tempje), xh });
					} else {
						full = true;
						request.setAttribute("isFULL", "is");
					}

					if (!full) {
						sql = "delete from zxdk_htxx where xh||hth = '" + pk
						+ "'";
						del = dao.runUpdate(sql, new String[] {});
						if (del) {
							sql = "insert into zxdk_htxx(xxmc,xh,xm,sfzh,hth,htjbjrjg,htfzjgmc,"
								+ "htpzrq,htzje,htjby,htpzhz,hthkksrq,hthkjzrq,htzqly,htzqsj,"
								+ "htdkfs,hthkfs,hthkjzmc,hthkjzzh,httqyhbxje,httqjzsj,httqbz,"
								+ "dkll,dklb,bz) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
							ok = dao.runUpdate(sql, new String[] { xxmc, xh,
									xm, sfzh, hth, htjbjrjg, htfzjgmc, htpzrq,
									htzje, htjby, htpzhz, hthkksrq, hthkjzrq,
									htzqly, htzqsj, htdkfs, hthkfs, hthkjzmc,
									hthkjzzh, httqyhbxje, httqjzsj, httqbz,
									dkll, dklb, bz });
						} else {
							dataSearchForm.setErrMsg("sdf");
							return mapping.findForward("false");
						}
					}
				}
				if (ok) {
					request.setAttribute("ok", "ok");
				} else {
					request.setAttribute("ok", "no");
				}
				request.setAttribute("isHTXX", "is");
				ActionForward forward = new ActionForward(
						"/modiData.do?realTable=zxdk_htxx&doType=add&tableName=zxdk_htxx&pk=xh||hth&from=htxx&pkValue= ",
						false);
				return forward;
			} else if (realTable.equalsIgnoreCase("zxdk_xsjbxx")) {
				// �ۺ����ʲ�����Ϣ
				String ksh = DealString.toGBK(dataSearchForm.getKsh());
				String xm = dao.getOneRs(
						"select xm from bks_xsjbxx where xh=? and rownum=1",
						new String[] { xh }, new String[] { "xm" })[0];
				String xb = DealString.toGBK(dataSearchForm.getXb());
				String sfzh = DealString.toGBK(dataSearchForm.getSfzh());
				String mzmc = DealString.toGBK(dataSearchForm.getMzmc());
				String xymc = DealString.toGBK(dataSearchForm.getXymc());
				String xmc = DealString.toGBK(dataSearchForm.getXmc());
				String rxny = DealString.toGBK(dataSearchForm.getRxny());
				String csrq = DealString.toGBK(dataSearchForm.getCsrq());
				String xz = DealString.toGBK(dataSearchForm.getXz());
				String sedh = DealString.toGBK(dataSearchForm.getSedh());
				String jtsr = DealString.toGBK(dataSearchForm.getJtsr());
				String fqsfzh = DealString.toGBK(dataSearchForm.getFqsfzh());
				String mqsfzh = DealString.toGBK(dataSearchForm.getMqsfzh());
				String jtzz = DealString.toGBK(dataSearchForm.getJtzz());
				String byny = DealString.toGBK(dataSearchForm.getByny());
				String dkze = DealString.toGBK(dataSearchForm.getDkze());
				String xfdks = DealString.toGBK(dataSearchForm.getXfdks());
				String shfdks = DealString.toGBK(dataSearchForm.getShfdks());
				String zsfdks = DealString.toGBK(dataSearchForm.getZsfdks());
				String yhdkje = DealString.toGBK(dataSearchForm.getYhdkje());
				String yzbm = DealString.toGBK(dataSearchForm.getYzbm());
				String fqxm = DealString.toGBK(dataSearchForm.getFqxm());
				String fqgzdw = DealString.toGBK(dataSearchForm.getFqgzdw());
				String fqlxdh = DealString.toGBK(dataSearchForm.getFqlxdh());
				String mqxm = DealString.toGBK(dataSearchForm.getMqxm());
				String mqgzdw = DealString.toGBK(dataSearchForm.getMqgzdw());
				String mqlxdh = DealString.toGBK(dataSearchForm.getMqlxdh());
				String scbyqx = DealString.toGBK(dataSearchForm.getScbyqx());
				String yxlxfs = DealString.toGBK(dataSearchForm.getYxlxfs());
				String dqgzdw = DealString.toGBK(dataSearchForm.getDqgzdw());
				String dqgzdwdz = DealString
				.toGBK(dataSearchForm.getDqgzdwdz());
				String dqgzdwyb = DealString
				.toGBK(dataSearchForm.getDqgzdwyb());
				String dqgzdwdh = DealString
				.toGBK(dataSearchForm.getDqgzdwdh());
				String hth1 = DealString.toGBK(dataSearchForm.getHth1());
				String ht1_jbjrjg = DealString.toGBK(dataSearchForm
						.getHt1_jbjrjg());
				String ht1_fzjgmc = DealString.toGBK(dataSearchForm
						.getHt1_fzjgmc());
				String ht1_pzrq = DealString
				.toGBK(dataSearchForm.getHt1_pzrq());
				String ht1_zje = DealString.toGBK(dataSearchForm.getHt1_zje());
				String ht1_jby = DealString.toGBK(dataSearchForm.getHt1_jby());
				String ht1_pzhz = DealString
				.toGBK(dataSearchForm.getHt1_pzhz());
				String ht1_hkksrq = DealString.toGBK(dataSearchForm
						.getHt1_hkksrq());
				String ht1_hkjzrq = DealString.toGBK(dataSearchForm
						.getHt1_hkjzrq());
				String ht1_zqly = DealString
				.toGBK(dataSearchForm.getHt1_zqly());
				String ht1_zqsj = DealString
				.toGBK(dataSearchForm.getHt1_zqsj());
				String ht1_dkfs = DealString
				.toGBK(dataSearchForm.getHt1_dkfs());
				String ht1_hkfs = DealString
				.toGBK(dataSearchForm.getHt1_hkfs());
				String ht1_hkjzmc = DealString.toGBK(dataSearchForm
						.getHt1_hkjzmc());
				String ht1_hkjzzh = DealString.toGBK(dataSearchForm
						.getHt1_hkjzzh());
				String ht1_tqyhbxje = DealString.toGBK(dataSearchForm
						.getHt1_tqyhbxje());
				String ht1_tqjzsj = DealString.toGBK(dataSearchForm
						.getHt1_tqjzsj());
				String ht1_tqbz = DealString
				.toGBK(dataSearchForm.getHt1_tqbz());
				String hth2 = DealString.toGBK(dataSearchForm.getHth2());
				String ht2_jbjrjg = DealString.toGBK(dataSearchForm
						.getHt2_jbjrjg());
				String ht2_fzjgmc = DealString.toGBK(dataSearchForm
						.getHt2_fzjgmc());
				String ht2_pzrq = DealString
				.toGBK(dataSearchForm.getHt2_pzrq());
				String ht2_zje = DealString.toGBK(dataSearchForm.getHt2_zje());
				String ht2_jby = DealString.toGBK(dataSearchForm.getHt2_jby());
				String ht2_pzhz = DealString
				.toGBK(dataSearchForm.getHt2_pzhz());
				String ht2_hkksrq = DealString.toGBK(dataSearchForm
						.getHt2_hkksrq());
				String ht2_hkjzrq = DealString.toGBK(dataSearchForm
						.getHt2_hkjzrq());
				String ht2_zqly = DealString
				.toGBK(dataSearchForm.getHt2_zqly());
				String ht2_zqsj = DealString
				.toGBK(dataSearchForm.getHt2_zqsj());
				String ht2_dkfs = DealString
				.toGBK(dataSearchForm.getHt2_dkfs());
				String ht2_hkfs = DealString
				.toGBK(dataSearchForm.getHt2_hkfs());
				String ht2_hkjzmc = DealString.toGBK(dataSearchForm
						.getHt2_hkjzmc());
				String ht2_hkjzzh = DealString.toGBK(dataSearchForm
						.getHt2_hkjzzh());
				String ht2_tqyhbxje = DealString.toGBK(dataSearchForm
						.getHt2_tqyhbxje());
				String ht2_tqjzsj = DealString.toGBK(dataSearchForm
						.getHt2_tqjzsj());
				String ht2_tqbz = DealString
				.toGBK(dataSearchForm.getHt2_tqbz());
				String hth3 = DealString.toGBK(dataSearchForm.getHth3());
				String ht3_jbjrjg = DealString.toGBK(dataSearchForm
						.getHt3_jbjrjg());
				String ht3_fzjgmc = DealString.toGBK(dataSearchForm
						.getHt3_fzjgmc());
				String ht3_pzrq = DealString
				.toGBK(dataSearchForm.getHt3_pzrq());
				String ht3_zje = DealString.toGBK(dataSearchForm.getHt3_zje());
				String ht3_jby = DealString.toGBK(dataSearchForm.getHt3_jby());
				String ht3_pzhz = DealString
				.toGBK(dataSearchForm.getHt3_pzhz());
				String ht3_hkksrq = DealString.toGBK(dataSearchForm
						.getHt3_hkksrq());
				String ht3_hkjzrq = DealString.toGBK(dataSearchForm
						.getHt3_hkjzrq());
				String ht3_zqly = DealString
				.toGBK(dataSearchForm.getHt3_zqly());
				String ht3_zqsj = DealString
				.toGBK(dataSearchForm.getHt3_zqsj());
				String ht3_dkfs = DealString
				.toGBK(dataSearchForm.getHt3_dkfs());
				String ht3_hkfs = DealString
				.toGBK(dataSearchForm.getHt3_hkfs());
				String ht3_hkjzmc = DealString.toGBK(dataSearchForm
						.getHt3_hkjzmc());
				String ht3_hkjzzh = DealString.toGBK(dataSearchForm
						.getHt3_hkjzzh());
				String ht3_tqyhbxje = DealString.toGBK(dataSearchForm
						.getHt3_tqyhbxje());
				String ht3_tqjzsj = DealString.toGBK(dataSearchForm
						.getHt3_tqjzsj());
				String ht3_tqbz = DealString
				.toGBK(dataSearchForm.getHt3_tqbz());
				String hth4 = DealString.toGBK(dataSearchForm.getHth4());
				String ht4_jbjrjg = DealString.toGBK(dataSearchForm
						.getHt4_jbjrjg());
				String ht4_fzjgmc = DealString.toGBK(dataSearchForm
						.getHt4_fzjgmc());
				String ht4_pzrq = DealString
				.toGBK(dataSearchForm.getHt4_pzrq());
				String ht4_zje = DealString.toGBK(dataSearchForm.getHt4_zje());
				String ht4_jby = DealString.toGBK(dataSearchForm.getHt4_jby());
				String ht4_pzhz = DealString
				.toGBK(dataSearchForm.getHt4_pzhz());
				String ht4_hkksrq = DealString.toGBK(dataSearchForm
						.getHt4_hkksrq());
				String ht4_hkjzrq = DealString.toGBK(dataSearchForm
						.getHt4_hkjzrq());
				String ht4_zqly = DealString
				.toGBK(dataSearchForm.getHt4_zqly());
				String ht4_zqsj = DealString
				.toGBK(dataSearchForm.getHt4_zqsj());
				String ht4_dkfs = DealString
				.toGBK(dataSearchForm.getHt4_dkfs());
				String ht4_hkfs = DealString
				.toGBK(dataSearchForm.getHt4_hkfs());
				String ht4_hkjzmc = DealString.toGBK(dataSearchForm
						.getHt4_hkjzmc());
				String ht4_hkjzzh = DealString.toGBK(dataSearchForm
						.getHt4_hkjzzh());
				String ht4_tqyhbxje = DealString.toGBK(dataSearchForm
						.getHt4_tqyhbxje());
				String ht4_tqjzsj = DealString.toGBK(dataSearchForm
						.getHt4_tqjzsj());
				String ht4_tqbz = DealString
				.toGBK(dataSearchForm.getHt4_tqbz());
				String bz = DealString.toGBK(dataSearchForm.getBz());
				String xslx = DealString.toGBK(dataSearchForm.getXslx());
				String lxyy = DealString.toGBK(dataSearchForm.getLxyy());
				pk = xh;
				boolean del = false;
				sql = "delete from zxdk_xsjbxx where xh = '" + pk + "'";
				del = dao.runUpdate(sql, new String[] {});
				if (del) {
					sql = "insert into ZXDK_XSJBXX (ksh,xh,xm,xb,sfzh,mzmc,xymc,xmc,"
						+ "rxny,byny,dkze,xfdks,shfdks,zsfdks,yhdkje,hth1,ht1_jbjrjg,"
						+ "ht1_fzjgmc,ht1_pzrq,ht1_zje,ht1_jby,ht1_pzhz,ht1_hkksrq,"
						+ "ht1_hkjzrq,ht1_zqly,ht1_zqsj,ht1_dkfs,ht1_hkfs,ht1_hkjzmc,"
						+ "ht1_hkjzzh,ht1_tqyhbxje,ht1_tqjzsj,ht1_tqbz,jtzz,yzbm,fqxm,"
						+ "fqgzdw,fqlxdh,mqxm,mqgzdw,mqlxdh,scbyqx,yxlxfs,dqgzdw,dqgzdwdz,"
						+ "dqgzdwyb,dqgzdwdh,hth2,ht2_jbjrjg,ht2_fzjgmc,ht2_pzrq,ht2_zje,"
						+ "ht2_jby,ht2_pzhz,ht2_hkksrq,ht2_hkjzrq,ht2_zqly,ht2_zqsj,ht2_dkfs,"
						+ "ht2_hkfs,ht2_hkjzmc,ht2_hkjzzh,ht2_tqyhbxje,ht2_tqjzsj,ht2_tqbz,"
						+ "hth3,ht3_jbjrjg,ht3_fzjgmc,ht3_pzrq,ht3_zje,ht3_jby,ht3_pzhz,"
						+ "ht3_hkksrq,ht3_hkjzrq,ht3_zqly,ht3_zqsj,ht3_dkfs,ht3_hkfs,"
						+ "ht3_hkjzmc,ht3_hkjzzh,ht3_tqyhbxje,ht3_tqjzsj,ht3_tqbz,hth4,"
						+ "ht4_jbjrjg,ht4_fzjgmc,ht4_pzrq,ht4_zje,ht4_jby,ht4_pzhz,ht4_hkksrq,"
						+ "ht4_hkjzrq,ht4_zqly,ht4_zqsj,ht4_dkfs,ht4_hkfs,ht4_hkjzmc,ht4_hkjzzh,"
						+ "ht4_tqyhbxje,ht4_tqjzsj,ht4_tqbz,bz,xslx,xxmc,csrq,xz,fqsfzh,mqsfzh,sedh,jtsr,nd,lxyy ) "
						+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] { ksh, xh, xm, xb, sfzh,
							mzmc, xymc, xmc, rxny, byny, dkze, xfdks, shfdks,
							zsfdks, yhdkje, hth1, ht1_jbjrjg, ht1_fzjgmc,
							ht1_pzrq, ht1_zje, ht1_jby, ht1_pzhz, ht1_hkksrq,
							ht1_hkjzrq, ht1_zqly, ht1_zqsj, ht1_dkfs, ht1_hkfs,
							ht1_hkjzmc, ht1_hkjzzh, ht1_tqyhbxje, ht1_tqjzsj,
							ht1_tqbz, jtzz, yzbm, fqxm, fqgzdw, fqlxdh, mqxm,
							mqgzdw, mqlxdh, scbyqx, yxlxfs, dqgzdw, dqgzdwdz,
							dqgzdwyb, dqgzdwdh, hth2, ht2_jbjrjg, ht2_fzjgmc,
							ht2_pzrq, ht2_zje, ht2_jby, ht2_pzhz, ht2_hkksrq,
							ht2_hkjzrq, ht2_zqly, ht2_zqsj, ht2_dkfs, ht2_hkfs,
							ht2_hkjzmc, ht2_hkjzzh, ht2_tqyhbxje, ht2_tqjzsj,
							ht2_tqbz, hth3, ht3_jbjrjg, ht3_fzjgmc, ht3_pzrq,
							ht3_zje, ht3_jby, ht3_pzhz, ht3_hkksrq, ht3_hkjzrq,
							ht3_zqly, ht3_zqsj, ht3_dkfs, ht3_hkfs, ht3_hkjzmc,
							ht3_hkjzzh, ht3_tqyhbxje, ht3_tqjzsj, ht3_tqbz,
							hth4, ht4_jbjrjg, ht4_fzjgmc, ht4_pzrq, ht4_zje,
							ht4_jby, ht4_pzhz, ht4_hkksrq, ht4_hkjzrq,
							ht4_zqly, ht4_zqsj, ht4_dkfs, ht4_hkfs, ht4_hkjzmc,
							ht4_hkjzzh, ht4_tqyhbxje, ht4_tqjzsj, ht4_tqbz, bz,
							xslx, xxmc, csrq, xz, fqsfzh, mqsfzh, sedh, jtsr,
							nd, lxyy });
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("xxzxdksqb")) {
				// ѧУ��ѧ����Ϣ
				String dkrq = request.getParameter("dkrq");
				String sqje = request.getParameter("sqje");
				String dkje = request.getParameter("dkje");
				String shf = request.getParameter("shf");
				String xf = request.getParameter("xf");
				String hth = DealString.toGBK(request.getParameter("hth"));
				String hkqx = dao.dateToStr(request.getParameter("hkqx"));
				String cxsj = request.getParameter("cxsj");
				String bz = DealString.toGBK(request.getParameter("bz"));
				String xysh = DealString.toGBK(request.getParameter("xysh"));
				String xxsh = DealString.toGBK(request.getParameter("xxsh"));
				String yjfsbj = request.getParameter("yjfsbj");
				String shsj = dao.dateToStr(request.getParameter("shsj"));
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					del = true;
				} else {
					sql = "delete from xxzxdksqb where " + pk + "='" + pkValue
					+ "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into xxzxdksqb(dkls,nd,xn,xq,xh,dkrq,sqje,dkje,shf,xf,hth,hkqx,cxsj,bz,xysh,xxsh,yjfsbj,shsj) values(xxzxdksqb_sequence.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] { nd, xn, xq, xh, dkrq,
							sqje, dkje, shf, xf, hth, hkqx, cxsj, bz, xysh,
							xxsh, yjfsbj, shsj });
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("gjzxdksqb")) {
				// ������ѧ������Ϣ
				String sqdkze = request.getParameter("sqdkze");
				String sqdkxf = request.getParameter("sqdkxf");
				String sqdkshf = request.getParameter("sqdkshf");
				String sqdkzsf = request.getParameter("sqdkzsf");
				String dqdwyb = request.getParameter("dqdwyb");
				String fqsfzh = request.getParameter("fqsfzh");
				String mqsfzh = request.getParameter("mqsfzh");
				String qysfzh = request.getParameter("qysfzh");
				String dkje1 = request.getParameter("dkje1");
				String dkje2 = request.getParameter("dkje2");
				String dkje3 = request.getParameter("dkje3");
				String yjfsbj = request.getParameter("yjfsbj");
				String ffdkze = request.getParameter("ffdkze");
				String ffdkxf = request.getParameter("ffdkxf");
				String ffdkshf = request.getParameter("ffdkshf");
				String ffdkzsf = request.getParameter("ffdkzsf");
				String sqly = DealString.toGBK(request.getParameter("sqly"));
				String scbyqx = DealString
				.toGBK(request.getParameter("scbyqx"));
				String yxlxfs = DealString
				.toGBK(request.getParameter("yxlxfs"));
				String dqgzdw = DealString
				.toGBK(request.getParameter("dqgzdw"));
				String dqdwdz = DealString
				.toGBK(request.getParameter("dqdwdz"));
				String dqdwlxfs = DealString.toGBK(request
						.getParameter("dqdwlxfs"));
				String fqxm = DealString.toGBK(request.getParameter("fqxm"));
				String fqgzdw = DealString
				.toGBK(request.getParameter("fqgzdw"));
				String fqlxfs = DealString
				.toGBK(request.getParameter("fqlxfs"));
				String mqxm = DealString.toGBK(request.getParameter("mqxm"));
				String mqgzdw = DealString
				.toGBK(request.getParameter("mqgzdw"));
				String mqlxfs = DealString
				.toGBK(request.getParameter("mqlxfs"));
				String qyxm = DealString.toGBK(request.getParameter("qyxm"));
				String qygzdw = DealString
				.toGBK(request.getParameter("qygzdw"));
				String qygx = DealString.toGBK(request.getParameter("qygx"));
				String qyzz = DealString.toGBK(request.getParameter("qyzz"));
				String qylxdh = DealString
				.toGBK(request.getParameter("qylxdh"));
				String hth1 = DealString.toGBK(request.getParameter("hth1"));
				String htjbjrjg1 = DealString.toGBK(request
						.getParameter("htjbjrjg1"));
				String fzjgmc1 = DealString.toGBK(request
						.getParameter("fzjgmc1"));
				String hth2 = DealString.toGBK(request.getParameter("hth2"));
				String htjbjrjg2 = DealString.toGBK(request
						.getParameter("htjbjrjg2"));
				String fzjgmc2 = DealString.toGBK(request
						.getParameter("fzjgmc2"));
				String hth3 = DealString.toGBK(request.getParameter("hth3"));
				String htjbjrjg3 = DealString.toGBK(request
						.getParameter("htjbjrjg3"));
				String fzjgmc3 = DealString.toGBK(request
						.getParameter("fzjgmc3"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				String xxsh = DealString.toGBK(request.getParameter("xxsh"));
				String xysh = DealString.toGBK(request.getParameter("xysh"));
				String hknf1 = dao.dateToStr(request.getParameter("hknf1"));
				String hknf2 = dao.dateToStr(request.getParameter("hknf2"));
				String hknf3 = dao.dateToStr(request.getParameter("hknf3"));
				String ffsj = dao.dateToStr(request.getParameter("ffsj"));
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					del = true;
				} else {
					sql = "delete from gjzxdksqb where " + pk + "='" + pkValue
					+ "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into gjzxdksqb(dkls,xn,nd,xq,xh,sqly,sqdkze,sqdkxf,sqdkshf,sqdkzsf,scbyqx,yxlxfs,dqgzdw,dqdwdz,dqdwyb,dqdwlxfs,fqxm,fqsfzh,fqgzdw,fqlxfs,mqxm,mqsfzh,mqgzdw,mqlxfs,qyxm,qysfzh,qygzdw,qygx,qyzz,qylxdh,hth1,htjbjrjg1,fzjgmc1,dkje1,hknf1,hth2,htjbjrjg2,fzjgmc2,dkje2,hknf2,hth3,htjbjrjg3,fzjgmc3,dkje3,hknf3,yjfsbj,bz,ffdkze,ffdkxf,ffdkshf,ffdkzsf,ffsj,xysh,xxsh ) values(gjzxdksqb_sequence.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] { xn, nd, xq, xh, sqly,
							sqdkze, sqdkxf, sqdkshf, sqdkzsf, scbyqx, yxlxfs,
							dqgzdw, dqdwdz, dqdwyb, dqdwlxfs, fqxm, fqsfzh,
							fqgzdw, fqlxfs, mqxm, mqsfzh, mqgzdw, mqlxfs, qyxm,
							qysfzh, qygzdw, qygx, qyzz, qylxdh, hth1,
							htjbjrjg1, fzjgmc1, dkje1, hknf1, hth2, htjbjrjg2,
							fzjgmc2, dkje2, hknf2, hth3, htjbjrjg3, fzjgmc3,
							dkje3, hknf3, yjfsbj, bz, ffdkze, ffdkxf, ffdkshf,
							ffdkzsf, ffsj, xysh, xxsh });
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("nsepxxb")) {
				// NSEP��Ϣ
				String nsepxmmc = DealString.toGBK(request
						.getParameter("nsepxmmc"));
				String jx = DealString.toGBK(request.getParameter("jx"));
				String zdls = DealString.toGBK(request.getParameter("zdls"));
				String fzr = DealString.toGBK(request.getParameter("fzr"));
				String sj = dao.dateToStr(request.getParameter("sj"));
				String dektxf = dataSearchForm.getDektxf();
				String nsepjbdm = request.getParameter("nsepjbdm");
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "delete from nsepxxb where xn=? and xh=? and nsepxmmc=?";
					del = dao.runUpdate(sql, new String[] { xn, xh, nsepxmmc });
				} else {
					sql = "delete from nsepxxb where " + pk + "='" + pkValue
					+ "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into nsepxxb(nd,xn,xq,xh,nsepxmmc,jx,zdls,fzr,sj,dektxf,nsepjbdm) values(?,?,?,?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] { nd, xn, xq, xh, nsepxmmc,
							jx, zdls, fzr, sj, dektxf, nsepjbdm });
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("knsshhdxxb")) {
				// �����Ϣ
				String hdxz = request.getParameter("hdxz");
				String hdxm = DealString.toGBK(request.getParameter("hdxm"));
				String hdnr = DealString.toGBK(request.getParameter("hdnr"));
				String sj = dao.dateToStr(request.getParameter("sj"));
				String khjg = DealString.toGBK(request.getParameter("khjg"));
				String xjts = request.getParameter("xjts");
				String shry = request.getParameter("shry");
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "delete from knsshhdxxb where xh=? and sj=?";
					del = dao.runUpdate(sql, new String[] { xh, sj });
				} else {
					sql = "delete from knsshhdxxb where " + pk + "='" + pkValue
					+ "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
						sql = "insert into knsshhdxxb(xh,hdxz,hdxm,hdnr,sj,khjg) values(?,?,?,?,?,?)";
						dao.runUpdate(sql, new String[] { xh, hdxz, hdxm, hdnr,
								sj, khjg });
					} else {
						sql = "insert into knsshhdxxb(xh,hdxz,hdxm,hdnr,sj,xjts,shry) values(?,?,?,?,?,?,?)";
						dao.runUpdate(sql, new String[] { xh, hdxz, hdxm, hdnr,
								sj,xjts,shry });
					}
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			}else if (realTable.equalsIgnoreCase("xlytqkb")) {
				// �����ղ�Լ̸���Ԥ���
				String lxdzxx = DealString.toGBK(request.getParameter("lxdzxx"));
				String sjhm = DealString.toGBK(request.getParameter("sjhm"));
				String chf = DealString.toGBK(request.getParameter("chf"));
				String cbqk = DealString.toGBK(request.getParameter("cbqk"));
				String ytqk = DealString.toGBK(request.getParameter("ytqk"));
				String cbsb = DealString.toGBK(request.getParameter("cbsb"));
				String gzjb = DealString.toGBK(request.getParameter("gzjb"));
				String ytjy = DealString.toGBK(request.getParameter("ytjy"));
				String ytid = DealString .toGBK(request.getParameter("yt_id"));
				String dtsj = request.getParameter("dtsj");
				boolean del = false;
				if ((xh != null) || !(xh.equalsIgnoreCase(""))) {
					sql = "delete from xlytqkb a where " + pk + "='" + ytid
					+ "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into xlytqkb(yt_id,xh,chf,cbqk,ytqk,cbsb,gzjb,ytjy,lxdzxx,sjhm,dtsj) values(?,?,?,?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] {ytid,xh,chf,cbqk,ytqk,cbsb,gzjb,ytjy,lxdzxx,sjhm,dtsj});
				}
			}else if (realTable.equalsIgnoreCase("xlzxqkb")) {
				// ������ѯ���
				String zxsj = request.getParameter("zxsj");
				String zxid = request.getParameter("pkValue");
				String zxls = DealString.toGBK(request.getParameter("zxls"));
//				String xm = DealString.toGBK(request.getParameter("xm"));
				String lfjl = DealString.toGBK(request.getParameter("lfjl"));
				String wtcl = DealString.toGBK(request.getParameter("wtcl"));
				boolean del = false;
				if ((zxid != null) || !("".equalsIgnoreCase(zxid))) {
					sql = "delete from xlzxqkb a where " + pk + "='" + zxid
					+ "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into xlzxqkb(xh,zxsj,zxls,lfjl,wtcl) values(?,?,?,?,?)";
					dao.runUpdate(sql, new String[] {xh,zxsj,zxls,lfjl,wtcl});
				}
			}else if (realTable.equalsIgnoreCase("jzjsdjb")) {
				// ��ְ��ʦƸ�εǼ�
				String jzid = request.getParameter("jzid");
				String xm = DealString.toGBK(request.getParameter("xm"));
				String xb = DealString.toGBK(request.getParameter("xb"));
				String csny = DealString.toGBK(request.getParameter("csny"));
				String xymc = DealString.toGBK(request.getParameter("xymc"));
				String bgsdh = DealString.toGBK(request.getParameter("bgsdh"));
				String sjhm = DealString.toGBK(request.getParameter("sjhm"));
				String qtlxdh = DealString.toGBK(request.getParameter("qtlxdh"));
				String zwzc = DealString.toGBK(request.getParameter("zwzc"));
				String dzyx = DealString.toGBK(request.getParameter("dzyx"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				String gzjl = DealString.toGBK(request.getParameter("gzjl"));
				boolean del = false;
				if ((jzid != null) || !("".equalsIgnoreCase("jzid"))) {
					sql = "delete from jzjsdjb a where " + pk + "='" + jzid
					+ "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into jzjsdjb(xm,xb,csny,xymc,bgsdh,sjhm,qtlxdh,zwzc,dzyx,gzjl,bz) values(?,?,?,?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] {xm,xb,csny,xymc,bgsdh,sjhm,qtlxdh,zwzc,dzyx,gzjl,bz});
				}
			}else if (realTable.equalsIgnoreCase("xlcsjgb")) {
				// ���������Ϣ
				String csxmdm = DealString.toGBK(request.getParameter("csxmdm"));
				if("�й���ѧ������������".equals(csxmdm) && Globals.XXDM_ZGKYDX.equals(xxdm)){
					csxmdm = "001";
				}
				String csjg = DealString.toGBK(request.getParameter("csjg"));
				String fsbj = DealString.toGBK(request.getParameter("fsbj"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				String cssj = dao.dateToStr(request.getParameter("cssj"));
				@SuppressWarnings("unused")
				String sylb = DealString.toGBK(request.getParameter("sylb"));
				String csnj = DealString.toGBK(request.getParameter("csnj"));
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "delete from xlcsjgb where xh=? and cssj=? and csxmdm=?";
					del = dao.runUpdate(sql, new String[] { xh, cssj, csxmdm });
				} else {
					sql = "delete from xlcsjgb where " + pk + "='" + pkValue
					+ "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					if(!Base.isNull(xn)&&xn.length()>3){
						nd = xn.substring(0, 4);
					}else{
						nd = "";
					}
					
					sql = "insert into xlcsjgb(xn,xq,xh,csxmdm,csjg,bz,fsbj,cssj,csnj,nd) values(?,?,?,?,?,?,?,?,?,?)";
					if(cssj == null || "".equals(cssj)){
						String nowtime = (dao.getOneRs("select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') sdate from dual",
								// ����ʱ��25
								// ȡ�����ݿ���ʱ��
								new String[] {}, new String[] { "sdate" }))[0];
						cssj = nowtime.substring(0, 10);
					}
					dao.runUpdate(sql, new String[] { xn,xq,xh, csxmdm, csjg, bz,
							fsbj, cssj,csnj,nd });
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}						
			} else if (realTable.equalsIgnoreCase("gzxsjbqkb")) {
				// �ص��עѧ�����������
				String id = request.getParameter("id");
				@SuppressWarnings("unused")
				String xymc = DealString.toGBK(request.getParameter("xymc"));
				@SuppressWarnings("unused")
				String xzb = DealString.toGBK(request.getParameter("xzb"));
				@SuppressWarnings("unused")
				String xm = DealString.toGBK(request.getParameter("xm"));
				@SuppressWarnings("unused")
				String xb = DealString.toGBK(request.getParameter("xb"));
				String cbsb = DealString.toGBK(request.getParameter("cbsb"));
				String gzjb = DealString.toGBK(request.getParameter("gzjb"));
				String zyzzjbx = DealString.toGBK(request.getParameter("zyzzjbx"));
				String yyfx = DealString.toGBK(request.getParameter("yyfx"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "delete from gzxsjbqkb where id=?";
					del = dao.runUpdate(sql, new String[] { id});
				} else {
					sql = "delete from gzxsjbqkb where " + pk + "='" + pkValue
					+ "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into gzxsjbqkb(xh,cbsb,gzjb,zyzzjbx,yyfx,bz) values(?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] { xh, cbsb, gzjb,
							zyzzjbx, yyfx, bz });
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}						
			} else if (realTable.equalsIgnoreCase("gzqkb")) {
				//�ص��עѧ�����������
				@SuppressWarnings("unused")
				String id = request.getParameter("id");
				@SuppressWarnings("unused")
				String xymc = DealString.toGBK(request.getParameter("xymc"));
				String sfzdgz = DealString.toGBK(request.getParameter("sfzdgz"));
				@SuppressWarnings("unused")
				String xzb = DealString.toGBK(request.getParameter("xzb"));
				@SuppressWarnings("unused")
				String xm = DealString.toGBK(request.getParameter("xm"));
				@SuppressWarnings("unused")
				String xb = DealString.toGBK(request.getParameter("xb"));
				String jtbx = DealString.toGBK(request.getParameter("jtbx"));
				String lxr = DealString.toGBK(request.getParameter("lxr"));
				String tbsj = DealString.toGBK(request.getParameter("tbsj"));
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "delete from gzqkb where " + pk + "='" + pkValue
					+ "'";
					del = dao.runUpdate(sql, new String[] {});
				} else {
					sql = "delete from gzqkb where " + pk + "='" + pkValue
					+ "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into gzqkb(xh,jtbx,lxr,tbsj,sfzdgz) values(?,?,?,?,?)";
					dao.runUpdate(sql, new String[] {xh,jtbx,lxr,tbsj,sfzdgz});
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}						
			}else if (realTable.equalsIgnoreCase("xlzxyydjb")) {
				//��ѧ��ѧ��������������������ѯԤԼ�ǼǱ�
				@SuppressWarnings("unused")
				String id = request.getParameter("id");
				String gjxx = DealString.toGBK(request.getParameter("gjxx"));
				String ssld = DealString.toGBK(request.getParameter("ssld"));
				String dhhm = DealString.toGBK(request.getParameter("dhhm"));
				String dzyx = DealString.toGBK(request.getParameter("dzyx"));
				String sfzx = DealString.toGBK(request.getParameter("sfzx"));
				String sczxxx = DealString.toGBK(request.getParameter("sczxxx"));
				String sfxlcs = DealString.toGBK(request.getParameter("sfxlcs"));
				String xlxsyy = DealString.toGBK(request.getParameter("xlxsyy"));
				String zxyy = DealString.toGBK(request.getParameter("zxyy"));
				String qtzxxx = DealString.toGBK(request.getParameter("qtzxxx"));
				String zxmd = DealString.toGBK(request.getParameter("zxmd"));
				String qtwtxx = DealString.toGBK(request.getParameter("qtwtxx"));
				String ddnxbz = DealString.toGBK(request.getParameter("ddnxbz"));
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "delete from xlzxyydjb where " + pk + "='" + pkValue
					+ "'";
					del = dao.runUpdate(sql, new String[] {});
				} else {
					sql = "delete from xlzxyydjb where " + pk + "='" + pkValue
					+ "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into xlzxyydjb(xh,gjxx,ssld,dhhm,dzyx,sfzx,sczxxx,sfxlcs,xlxsyy,zxyy,qtzxxx,zxmd,qtwtxx,ddnxbz) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] {xh,gjxx,ssld,dhhm,dzyx,sfzx,sczxxx,sfxlcs,xlxsyy,zxyy,qtzxxx,zxmd,qtwtxx,ddnxbz});
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}						
			}else if (realTable.equalsIgnoreCase("szdtxxb")) {
				//˫�ܶ�̬��Ϣ
				@SuppressWarnings("unused")
				String id = request.getParameter("id");
				String xymc = DealString.toGBK(request.getParameter("xymc"));
				String xldt = DealString.toGBK(request.getParameter("xldt"));
				String dtsj = DealString.toGBK(request.getParameter("dtsj"));
				String jkjygz = DealString.toGBK(request.getParameter("jkjygz"));
				String zdgzqk = DealString.toGBK(request.getParameter("zdgzqk"));
				String gtjcl = DealString.toGBK(request.getParameter("gtjcl"));
				String qt = DealString.toGBK(request.getParameter("qt"));
				String xn1 = request.getParameter("xn");
				String xq1 = request.getParameter("xq");
				String zc = DealString.toGBK(request.getParameter("zc"));
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "delete from szdtxxb where " + pk + "='" + pkValue
					+ "'";
					del = dao.runUpdate(sql, new String[] {});
				} else {
					sql = "delete from szdtxxb where " + pk + "='" + pkValue
					+ "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into szdtxxb(xymc,dtsj,xldt,jkjygz,zdgzqk,gtjcl,qt,xn,xq,zc) values(?,?,?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] {xymc,dtsj,xldt,jkjygz,zdgzqk,gtjcl,qt,xn1,xq1,zc});
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}						
			}else if (realTable.equalsIgnoreCase("bjxzryb")) {
				//���С����Ա
				String id = DealString.toGBK(request.getParameter("id"));
				String xm = DealString.toGBK(request.getParameter("xm"));
				String xb = DealString.toGBK(request.getParameter("xb"));
				String nn = DealString.toGBK(request.getParameter("nn"));
				String zzmm = DealString.toGBK(request.getParameter("zzmm"));
				String zwzc = DealString.toGBK(request.getParameter("zwzc"));
				String xrgz = DealString.toGBK(request.getParameter("xrgz"));
				String xm1 = DealString.toGBK(request.getParameter("xm1"));
				String xb1 = DealString.toGBK(request.getParameter("xb1"));
				String nn1 = DealString.toGBK(request.getParameter("nn1"));
				String zzmm1 = DealString.toGBK(request.getParameter("zzmm1"));
				String zwzc1 = DealString.toGBK(request.getParameter("zwzc1"));
				String xrgz1 = DealString.toGBK(request.getParameter("xrgz1"));
				String xm2 = DealString.toGBK(request.getParameter("xm2"));
				String xb2 = DealString.toGBK(request.getParameter("xb2"));
				String nn2 = DealString.toGBK(request.getParameter("nn2"));
				String zzmm2 = DealString.toGBK(request.getParameter("zzmm2"));
				String zwzc2 = DealString.toGBK(request.getParameter("zwzc2"));
				String xrgz2 = DealString.toGBK(request.getParameter("xrgz2"));
				String xm3 = DealString.toGBK(request.getParameter("xm3"));
				String xb3 = DealString.toGBK(request.getParameter("xb3"));
				String nn3 = DealString.toGBK(request.getParameter("nn3"));
				String zzmm3 = DealString.toGBK(request.getParameter("zzmm3"));
				String zwzc3 = DealString.toGBK(request.getParameter("zwzc3"));
				String xrgz3 = DealString.toGBK(request.getParameter("xrgz3"));
				String xm4 = DealString.toGBK(request.getParameter("xm4"));
				String xb4 = DealString.toGBK(request.getParameter("xb4"));
				String nn4 = DealString.toGBK(request.getParameter("nn4"));
				String zzmm4 = DealString.toGBK(request.getParameter("zzmm4"));
				String zwzc4 = DealString.toGBK(request.getParameter("zwzc4"));
				String xrgz4 = DealString.toGBK(request.getParameter("xrgz4"));
				String zybjsl = DealString.toGBK(request.getParameter("zybjsl"));
				String fdysh = DealString.toGBK(request.getParameter("fdysh"));
				String xysh = DealString.toGBK(request.getParameter("xysh"));
				String xxsh = DealString.toGBK(request.getParameter("xxsh"));
				String fdysj = DealString.toGBK(request.getParameter("fdysj"));
				String xyshsj = DealString.toGBK(request.getParameter("xyshsj"));
				String xxshsj = DealString.toGBK(request.getParameter("xxshsj"));
				boolean del = false;
				if ((id != null)) {
					sql = "delete from bjxzryb where id ='" + id
					+ "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into bjxzryb(id,xm,xb,nn,zzmm,zwzc,xrgz,xm1,xb1,nn1,zzmm1,zwzc1,xrgz1,xm2,xb2,nn2,zzmm2,zwzc2,xrgz2,xm3,xb3,nn3,zzmm3,zwzc3,xrgz3,xm4,xb4,nn4,zzmm4,zwzc4,xrgz4,zybjsl,fdysh,xysh,xxsh,fdysj,xyshsj,xxshsj) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] {id,xm,xb,nn,zzmm,zwzc,xrgz,xm1,xb1,nn1,zzmm1,zwzc1,xrgz1,xm2,xb2,nn2,zzmm2,zwzc2,xrgz2,xm3,
							xb3,nn3,zzmm3,zwzc3,xrgz3,xm4,xb4,nn4,zzmm4,zwzc4,xrgz4,zybjsl,fdysh,xysh,xxsh,fdysj,xyshsj,xxshsj});
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}						
			}else if (realTable.equalsIgnoreCase("tsqtgjb")) {
				//��ɳ����ְҵ����ѧԺ����Ⱥ�������
				String id = DealString.toGBK(request.getParameter("pkValue"));
				String xm = DealString.toGBK(request.getParameter("xm"));
				String xb = DealString.toGBK(request.getParameter("xb"));
				String csny = DealString.toGBK(request.getParameter("csny"));
				String bjmc = DealString.toGBK(request.getParameter("bjmc"));
				String qsh = DealString.toGBK(request.getParameter("qsh"));
				String xsdh = DealString.toGBK(request.getParameter("xsdh"));
				String xymc = DealString.toGBK(request.getParameter("xymc"));
				String jzxm = DealString.toGBK(request.getParameter("jzxm"));
				String jzsj = DealString.toGBK(request.getParameter("jzsj"));
				String bybjqk = DealString.toGBK(request.getParameter("bybjqk"));
				String xsmqqk = DealString.toGBK(request.getParameter("xsmqqk"));
				String fdysh = DealString.toGBK(request.getParameter("fdysh"));
				String xysh = DealString.toGBK(request.getParameter("xysh"));
				String xxsh = DealString.toGBK(request.getParameter("xxsh"));
				boolean del = false;
				if ((id != null)) {
					sql = "delete from tsqtgjb where id ='" + id
					+ "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into tsqtgjb(xh,xm,xb,csny,bjmc,qsh,xsdh,xymc,jzxm,jzsj,bybjqk,xsmqqk,fdysh,xysh,xxsh) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] {xh,xm,xb,csny,bjmc,qsh,xsdh,xymc,jzxm,jzsj,bybjqk,xsmqqk,fdysh,xysh,xxsh});
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}						
			}else if (realTable.equalsIgnoreCase("tsqtxssqcxb")) {
				//��ɳ����ְҵ����ѧԺ�ص�����Ⱥ��ѧ�����볷����
				String tsid = DealString.toGBK(request.getParameter("pkValue"));
				String xm = DealString.toGBK(request.getParameter("xm"));
				String xb = DealString.toGBK(request.getParameter("xb"));
				String csny = DealString.toGBK(request.getParameter("csny"));
				String bjmc = DealString.toGBK(request.getParameter("bjmc"));
				String qsh = DealString.toGBK(request.getParameter("qsh"));
				String xsdh = DealString.toGBK(request.getParameter("xsdh"));
				String xymc = DealString.toGBK(request.getParameter("xymc"));
				String jzxm = DealString.toGBK(request.getParameter("jzxm"));
				String jzsj = DealString.toGBK(request.getParameter("jzsj"));
				String sqcxyy = DealString.toGBK(request.getParameter("sqcxyy"));
				String fdysh = DealString.toGBK(request.getParameter("fdysh"));
				String xysh = DealString.toGBK(request.getParameter("xysh"));
				String xxsh = DealString.toGBK(request.getParameter("xxsh"));
				boolean del = false;
				if ((tsid != null)) {
					sql = "delete from tsqtxssqcxb where tsid ='" + tsid
					+ "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into tsqtxssqcxb(xh,xm,xb,csny,bjmc,qsh,xsdh,xymc,jzxm,jzsj,sqcxyy,fdysh,xysh,xxsh) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] {xh,xm,xb,csny,bjmc,qsh,xsdh,xymc,jzxm,jzsj,sqcxyy,fdysh,xysh,xxsh});
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}						
			}else if (realTable.equalsIgnoreCase("xljkjyqktjb")) {
				//��ɳ����ְҵ����ѧԺ�������������ͳ�Ʊ�
				String zgid = request.getParameter("pkValue");
				String xm = DealString.toGBK(request.getParameter("xm"));
				String qs = DealString.toGBK(request.getParameter("qs"));
				String js = DealString.toGBK(request.getParameter("js"));
				String hdcs = DealString.toGBK(request.getParameter("hdcs"));
				String xlwys = DealString.toGBK(request.getParameter("xlwys"));
				String zsrs = DealString.toGBK(request.getParameter("zsrs"));
				String zsnt = DealString.toGBK(request.getParameter("zsnt"));
				String zcrs = request.getParameter("zcrs");
				String jshzs = request.getParameter("jshzs");
				String wjgys = DealString.toGBK(request.getParameter("wjgys"));
				boolean del = false;
				if ((zgid != null)) {
					sql = "delete from xljkjyqktjb where zgid ='" + zgid
					+ "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into xljkjyqktjb(xm,qs,js,hdcs,xlwys,zsrs,zsnt,zcrs,jshzs,wjgys) values(?,?,?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] {xm,qs,js,hdcs,xlwys,zsrs,zsnt,zcrs,jshzs,wjgys});
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}						
			}else if (realTable.equalsIgnoreCase("xljkjyhdb")) {
				// ��ѧ���������������
				String zt = DealString.toGBK(request.getParameter("zt"));
				String xy = DealString.toGBK(request.getParameter("xy"));
				String jyxs = DealString.toGBK(request.getParameter("jyxs"));
				String dd = DealString.toGBK(request.getParameter("dd"));
				String hdrq = DealString.toGBK(request.getParameter("hdrq"));
				String kssjs = DealString.toGBK(request.getParameter("kssjs"));
				String kssjf = DealString.toGBK(request.getParameter("kssjf"));
				String jssjs = DealString.toGBK(request.getParameter("jssjs"));
				String jssjf = DealString.toGBK(request.getParameter("jssjf"));
				String zcr = DealString.toGBK(request.getParameter("zcr"));
				String cyxs = DealString.toGBK(request.getParameter("cyxs"));
				String cyrs = DealString.toGBK(request.getParameter("cyrs"));
				String hdjl = DealString.toGBK(request.getParameter("hdjl"));
				String hdxg = DealString.toGBK(request.getParameter("hdxg"));
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
//					sql = "delete from xljkjyhdb where id?";
//					del = dao.runUpdate(sql, new String[] {pkValue});
					del = true;
				} else {
					sql = "delete from xljkjyhdb where " + pk + "='" + pkValue
					+ "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into xljkjyhdb(zt,xy,jyxs,dd,hdrq,kssjs,kssjf,jssjs,jssjf,zcr,cyxs,cyrs,hdjl,hdxg) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] {zt,xy,jyxs,dd,hdrq,kssjs,kssjf,jssjs,jssjf,zcr,cyxs,cyrs,hdjl,hdxg});
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			}else if (realTable.equalsIgnoreCase("xytbgxxsxxb")) {
				// �ر����ѧ����Ϣ
				String tbgxxslbdm = request.getParameter("tbgxxslbdm");
				String xytbgxcs = DealString.toGBK(request.getParameter("xytbgxcs"));
				String zxqjzysj = DealString.toGBK(request.getParameter("zxqjzysj"));
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "delete from xytbgxxsxxb where xh=? and tbgxxslbdm=?";
					del = dao.runUpdate(sql, new String[] { xh, tbgxxslbdm });
				} else {
					sql = "delete from xytbgxxsxxb where " + pk + "='"
					+ pkValue + "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					if(Globals.XXDM_CSMZZYJSXY.equals(xxdm)){
						String ssbh = request.getParameter("ssbh");
						String dhhm = request.getParameter("dhhm");
						String csrq = request.getParameter("csrq");
						String jzdh = request.getParameter("jzdh");
						String jzxm = DealString.toGBK(request.getParameter("jzxm"));
						sql = "insert into xytbgxxsxxb(xh,tbgxxslbdm,xytbgxcs,zxqjzysj,ssbh,dhhm,csrq,jzdh,jzxm) values(?,?,?,?,?,?,?,?,?)";
						dao.runUpdate(sql, new String[] {xh,tbgxxslbdm,xytbgxcs,zxqjzysj,ssbh,dhhm,csrq,jzdh,jzxm});
					}else{
						String xlcslb =  request.getParameter("xlcslb");
						String xlwtlx =  request.getParameter("xlwtlx");
						String sfkns =  request.getParameter("sfkns");
						String sfdq =  request.getParameter("sfdq");
						sql = "insert into xytbgxxsxxb(xh,tbgxxslbdm,xytbgxcs,zxqjzysj,xlcslb,xlwtlx,sfkns,sfdq) values(?,?,?,?,?,?,?,?)";
						dao.runUpdate(sql, new String[] { xh, tbgxxslbdm, xytbgxcs,zxqjzysj,xlcslb,xlwtlx,sfkns,sfdq});
					}
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("wjcfb")) {
				// ������Ϣ
				String cflb = request.getParameter("cflb");
				String cfyy = request.getParameter("cfyy");
				String ggcflbdm = request.getParameter("ggcflbdm");
				String cfsj = dao.dateToStr(request.getParameter("cfsj"));
				String cxsj = dao.dateToStr(request.getParameter("cxsj"));
				String cfwh = request.getParameter("cfwh")
				.trim();
				String cxwh = request.getParameter("cxwh")
				.trim();
				String ssjg = request.getParameter("ssjg");
				String bz = request.getParameter("bz");
				String kf = request.getParameter("kf");
				String wjsj=request.getParameter("wjsj");
				String sfjw = request.getParameter("sfjw");
				String lswjjl = request.getParameter("lswjjl");
				String cfnx = request.getParameter("cfnx");
				String filePath = "";
				
				String ycflb = request.getParameter("ycflb");
				
				FormFile file = dataSearchForm.getUploadFile();
				if (file == null || file.getFileSize() <=0) {
					filePath = dao.getOneRs("select fjsclj from wjcfb where xh=? and cfwh=? and cfsj=?", new String[]{xh,cfwh,cfsj}, "fjsclj");
				}
//				String cxjg = DealString.toGBK(request.getParameter("cxjg"));
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "delete from wjcfb where xh=? and cfwh=? and cfsj=?";
					//del = dao.runUpdate(sql, new String[] { xh, cfwh, cfsj });
					del = StandardOperation.delete("wjcfb", "xh||cfwh||cfsj", xh+cfwh+cfsj, request);
				} else {
					sql = "delete from wjcfb where " + pk + "='" + pkValue
					+ "'";
					//del = dao.runUpdate(sql, new String[] {});
					del  = StandardOperation.delete("wjcfb", pk, pkValue, request);
				}
				if (del) {
					if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
						sql = "insert into wjcfb(xh,xn,nd,xq,cflb,cfyy,cfsj,cxsj,cfwh,cxwh,ssjg,bz,xxsh,wjsj) values(?,?,?,?,?,?,?,?,?,?,?,?,'ͨ��',?)";
						dao.runUpdate(sql, new String[] { xh, xn, nd, xq, cflb,
								cfyy, cfsj, cxsj, cfwh, cxwh, ssjg, bz, wjsj });
					} else if (xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)){//�㶫����ѧԺΥ�ʹ�������
						sql = "insert into wjcfb(xh,xn,nd,xq,cflb,cfyy,cfsj,cxsj,cfwh,cxwh,ssjg,bz,xxsh,xysh,xndsh) values(?,?,?,?,?,?,?,?,?,?,?,?,'ͨ��','ͨ��','ͨ��')";
						dao.runUpdate(sql, new String[] { xh, xn, nd, xq, cflb,
								cfyy, cfsj, cxsj, cfwh, cxwh, ssjg, bz });
					} else if (Globals.XXDM_HYGXY.equalsIgnoreCase(xxdm)) {
						StandardOperation.insert("wjcfb", new String[] { "xh",
								"xn", "nd", "xq", "cflb", "cfyy", "cfsj",
								"cxsj", "cfwh", "cxwh", "ssjg", "bz", "xxsh" , "ggcflbdm","kf","xysh"},
								new String[] { xh, xn, nd, xq, cflb, cfyy,
								cfsj, cxsj, cfwh, cxwh, ssjg, bz, "ͨ��" , ggcflbdm,kf,"ͨ��"},
								request);
					} else{

						if (!Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
							//�ϴ������ļ� begin
							String dir = request.getRealPath("/") + "upload";
							File f = new File(dir);
							if (!f.exists()) {
								f.mkdir();
							}
							String message = "";
							String dateStr = "";
							Timestamp date = new Timestamp(System.currentTimeMillis());
							dateStr += date.toString().substring(0, 19);
							dateStr = dateStr.replaceAll("-", "").replaceAll(" ", "").replaceAll(":","");

							if(file==null){
								message = "�����ϴ�ʧ�ܣ�";
								request.setAttribute("message", message);
								request.setAttribute("inserted","no");
								return mapping.findForward("false");
							} else {
								int size = file.getFileSize();
								if(size>0){//���ļ��ϴ�
									String fName = dateStr+file.getFileName();
									InputStream in = file.getInputStream();
									filePath = dir + "/" + fName;
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
							//end
						}
						sql = "insert into wjcfb(xh,xn,nd,xq,cflb,cfyy,cfsj,cxsj,cfwh,cxwh,ssjg,bz,xxsh,kf) values(?,?,?,?,?,?,?,?,?,?,?,?,'ͨ��',?)";
						//dao.runUpdate(sql, new String[] { xh, xn, nd, xq, cflb,
						//		cfyy, cfsj, cxsj, cfwh, cxwh, ssjg, bz });
						boolean result = StandardOperation.insert("wjcfb", new String[] { "xh",
								"xn", "nd", "xq", "cflb", "cfyy", "cfsj",
								"cxsj", "cfwh", "cxwh", "ssjg", "bz", "xxsh" , "ggcflbdm","kf","fjsclj", "ycflb", "sfjw", "lswjjl", "wjsj", "cfnx"},
								new String[] { xh, xn, nd, xq, cflb, cfyy,
								cfsj, cxsj, cfwh, cxwh, ssjg, bz, "ͨ��" , ggcflbdm,kf,filePath, ycflb, sfjw, lswjjl, wjsj, cfnx},
								request);
						if (result) {
							dao
									.runUpdate(
											"update wjcf_xsssb set cflbmc=(select cflbmc from cflbdmb where cflbdm=?), cfyymc=(select cfyymc from cfyydmb where cfyydm=?),ggcflbdm=? where xh=? and cfwh=? and cfsj=?",
											new String[] { ycflb, cfyy,cflb, xh,
													cfwh, cfsj });
						}
					}
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("wjcflsb")) {
				// ��ʷ������Ϣ
				String cflb = request.getParameter("cflb");
				String cfyy = request.getParameter("cfyy");
				String cfsj = dao.dateToStr(request.getParameter("cfsj"));
				String cxsj = dao.dateToStr(request.getParameter("cxsj"));
				String cfwh = DealString.toGBK(request.getParameter("cfwh"));
				String cxwh = DealString.toGBK(request.getParameter("cxwh"));
				String ssjg = DealString.toGBK(request.getParameter("ssjg"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				String lswjjl = request.getParameter("lswjjl");
				String ycflb = request.getParameter("ycflb");
				String sfjw = request.getParameter("sfjw");
				// String xm = DealString.toGBK(request.getParameter("xm"));
				// String nj = request.getParameter("nj");
				// String xb = DealString.toGBK(request.getParameter("xb"));
				// String xymc = DealString.toGBK(request.getParameter("xymc"));
				// String zymc = DealString.toGBK(request.getParameter("zymc"));
				// String bjmc = DealString.toGBK(request.getParameter("bjmc"));
				
				String sbsj = DateUtils.getTime();
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					del = true;
				} else {
					sql = "delete from wjcflsb where " + pk + "='" + pkValue
					+ "'";
					//del = dao.runUpdate(sql, new String[] {});
					del = StandardOperation.delete("wjcflsb", pk, pkValue, request);
				}
				if (del) {
					// sql = "insert into
					// wjcflsb(xh,xn,nd,xq,cflb,cfyy,cfsj,cxsj,cfwh,cxwh,ssjg,bz,xm,nj,xb,xymc,zymc,bjmc)
					// values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					// dao.runUpdate(sql, new String[] { xh, xn, nd, xq, cflb,
					// cfyy, cfsj, cxsj, cfwh, cxwh, ssjg, bz, xm, nj, xb,
					// xymc, zymc, bjmc });
					sql = "insert into wjcflsb(xh,xn,nd,xq,cflb,cfyy,cfsj,cxsj,cfwh,cxwh,ssjg,bz,sbsj) values(?,?,?,?,?,?,?,?,?,?,?,?,?) ";
					//dao.runUpdate(sql, new String[] { xh, xn, nd, xq, cflb,
					//	cfyy, cfsj, cxsj, cfwh, cxwh, ssjg, bz });
					StandardOperation.insert("wjcflsb", new String[] { "xh", "xn",
							"nd", "xq", "cflb", "cfyy", "cfsj", "cxsj", "cfwh", "cxwh", "ssjg",
					"bz" ,"sbsj", "ycflb", "sfjw", "lswjjl"}, new String[] { xh, xn, nd, xq, cflb, cfyy,
							cfsj, cxsj, cfwh, cxwh, ssjg, bz,sbsj, ycflb,sfjw,lswjjl }, request);
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("xsbxb")) {
				// ѧ��������Ϣ
				String tbgsdm = request.getParameter("tbgsdm");
				String tbxzdm = request.getParameter("tbxzdm");
				String tbbj = DealString.toGBK(request.getParameter("tbbj"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				String tbsj = dao.dateToStr(request.getParameter("tbsj"));
				String tuibsj = dao.dateToStr(request.getParameter("tuibsj"));
				String bxpzh = request.getParameter("bxpzh");
				String bxnx = DealString.toGBK(request.getParameter("bxnx"));
				String bf = request.getParameter("bf");
				String bxdc = request.getParameter("bxdc");
				String jfbj = DealString.toGBK(request.getParameter("jfbj"));
				String sqsj = request.getParameter("sqsj");
				sqsj=Base.isNull(sqsj)?dao.getNowTime("YYYYMMDD"):sqsj;
				// String page = request.getParameter("page");
				boolean result = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					result = StandardOperation.delete("xsbxb",
							"xh||tbgsdm||tbxzdm||nd", xh.trim() + tbgsdm.trim()
							+ tbxzdm.trim() + nd.trim(), request);
				} else {
					result = StandardOperation.delete("xsbxb", pk, pkValue,
							request);
				}
				if (result) {
					result = StandardOperation.insert("xsbxb",
							new String[] { "xh", "tbgsdm", "tbxzdm", "tbbj",
							"bz", "tbsj", "tuibsj", "bxpzh", "bxnx",
							"bf", "nd", "jfbj", "xxsh", "bxdc", "sqsj"},
							new String[] { xh, tbgsdm, tbxzdm, tbbj, bz, tbsj,
							tuibsj, bxpzh, bxnx, bf, nd, jfbj, "ͨ��",
							bxdc ,sqsj}, request);
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
				if (xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)) {
					List bxdcList = dao.getList(
							"select dcdm,dcmc from gdnzzy_bxdcdmb order by dcdm",
							new String[] {}, new String[] { "dcdm",
							"dcmc" });
					request.setAttribute("bxdcList", bxdcList);
				}
				if (xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)) {//�㶫Ů��
					request.setAttribute("showGdnz", "showGdnz");
				}

				request.setAttribute("result", result);
				
			} else if (realTable.equalsIgnoreCase("xshsxfb")) {
				// ѧ����ʳ������Ϣ
				String nf = request.getParameter("nf");
				String yf = request.getParameter("yf");
				String xfje = request.getParameter("xfje");
				String bz = DealString.toGBK(request.getParameter("bz"));
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					del = StandardOperation.delete("xshsxfb", "xh||nf||yf", xh
							.trim()
							+ nf.trim() + yf.trim(), request);
				} else {
					del = StandardOperation.delete("xshsxfb", pk, pkValue,
							request);
				}
				if (del) {
					del = StandardOperation.insert("xshsxfb", new String[] {
							"xh", "nf", "yf", "xfje", "bz" }, new String[] {
							xh, nf, yf, xfje, bz }, request);
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("sjb")) {
				// �Ծ�ά��
				String sjm = DealString.toGBK(request.getParameter("sjm"));
				String sjxd = request.getParameter("sjxd");
				String sjxsbj = DealString
				.toGBK(request.getParameter("sjxsbj"));
				String sjsm = DealString.toGBK(request.getParameter("sjsm"));
				boolean update = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "insert into sjb(sjlsh,sjm,sjsm,sjxd,sjxsbj) values(sequence_sjlsh.nextval,?,?,?,?)";
					update = dao.runUpdate(sql, new String[] { sjm, sjsm, sjxd,
							sjxsbj });
				} else {
					sql = "update sjb set sjm=?,sjsm=?,sjxd=?,sjxsbj=? where "
						+ pk + "='" + pkValue + "'";
					update = dao.runUpdate(sql, new String[] { sjm, sjsm, sjxd,
							sjxsbj });
				}
				if (!update) {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("stb")) {
				// ���ά��
				String stlxdm = request.getParameter("stlxdm");
				String stndjbdm = request.getParameter("stndjbdm");
				String stjffs = request.getParameter("stjffs");
				String stfz = request.getParameter("stfz");
				String stda = DealString.toGBK(request.getParameter("stda"));
				String stxsbj = DealString
				.toGBK(request.getParameter("stxsbj"));
				String stnr = DealString.toGBK(request.getParameter("stnr"));
				String stdajs = DealString
				.toGBK(request.getParameter("stdajs"));
				if (stjffs != null && stjffs.equalsIgnoreCase("0")) {
					stda = "��ѡ��÷�";
				}
				boolean update = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "insert into stb(stlsh,stnr,stlxdm,stndjbdm,stjffs,stfz,stda,stdajs,stxsbj) values(sequence_stlsh.nextval,?,?,?,?,?,?,?,?)";
					update = dao.runUpdate(sql, new String[] { stnr, stlxdm,
							stndjbdm, stjffs, stfz, stda, stdajs, stxsbj });
				} else {
					sql = "update stb set stnr=?,stlxdm=?,stndjbdm=?,stjffs=?,stfz=?,stda=?,stdajs=?,stxsbj=? where "
						+ pk + "='" + pkValue + "'";
					update = dao.runUpdate(sql, new String[] { stnr, stlxdm,
							stndjbdm, stjffs, stfz, stda, stdajs, stxsbj });
				}
				if (!update) {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("xxb")) {
				// ѡ��ά��
				String stlsh = request.getParameter("stlsh");
				String xxxh = DealString.toGBK(request.getParameter("xxxh"));
				String xxnr = DealString.toGBK(request.getParameter("xxnr"));
				String xxfz = request.getParameter("xxfz");
				String xxxsbj = DealString
				.toGBK(request.getParameter("xxxsbj"));
				boolean update = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "insert into xxb(xxlsh,stlsh,xxxh,xxnr,xxfz,xxxsbj) values(sequence_xxlsh.nextval,?,?,?,?,?)";
					update = dao.runUpdate(sql, new String[] { stlsh, xxxh,
							xxnr, xxfz, xxxsbj });
				} else {
					sql = "update xxb set stlsh=?,xxxh=?,xxnr=?,xxfz=?,xxxsbj=? where "
						+ pk + "='" + pkValue + "'";
					update = dao.runUpdate(sql, new String[] { stlsh, xxxh,
							xxnr, xxfz, xxxsbj });
				}
				if (!update) {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("sjstb")) {
				// ���ά��
				New_Random_ID newId = new New_Random_ID();
				String sjlsh = request.getParameter("sjlsh");
				String yxstlbStr = DealString.toGBK(request
						.getParameter("yxstlbStr"));
				String yxstlb[] = yxstlbStr.split("-");
				boolean update = false;
				sql = "delete from sjstb where sjlsh=?";
				update = dao.runUpdate(sql, new String[] { sjlsh });
				for (int i = 0; i < yxstlb.length; i++) {
					sql = "insert into sjstb(SJLSH,STLSH,STXH,XN_ID) values (?,?,?,?)";
					update = dao.runUpdate(sql,
							new String[] { sjlsh,yxstlb[i], Integer.toString(i + 1),newId.new_xnid("sjstb")});
				}
				if (!update) {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("rdsqb")) {
				// �뵳������Ϣ
				String djsqsj = dao.dateToStr(request.getParameter("djsqsj"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "delete from " + realTable
					+ " where xn=? and xq=? and xh=?";
					del = dao.runUpdate(sql, new String[] { xn, xq, xh });
				} else {
					sql = "delete from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into " + realTable
					+ "(nd,xn,xq,xh,djsqsj,bz) values(?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] { nd, xn, xq, xh, djsqsj,
							bz });
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
				// =============== begin �㽭��ýѧԺ ���ΰ 2009/3/4 =============
				if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)) {
					DtjszjcmService service = new DtjszjcmService();
					if (!service.addRdjjfz(xn, xq, xh, nd, djsqsj, pkValue, pk,
							request)) {
						dataSearchForm.setErrMsg("sdf");
						return mapping.findForward("false");
					}
				}
				// =============== end �㽭��ýѧԺ ���ΰ 2009/3/4 ============
			} else if (realTable.equalsIgnoreCase("zgkd_rdsqb")) {
				// =============== begin �й���ҵ��ѧ ���ΰ 2009/4/9 =============
				// �뵳������Ϣ
				String djsqsj = dao.dateToStr(request.getParameter("djsqsj"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "delete from " + realTable + " where xh=?";
					del = dao.runUpdate(sql, new String[] { xh });
				} else {
					sql = "delete from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				String rdjjfzsj = dataSearchForm.getRdjjfzsj();
				String rdjjfzpxbh = dataSearchForm.getRdjjfzpxbh();
				String fzdxsj = dataSearchForm.getFzdxsj();
				String fzdxpxbh = dataSearchForm.getFzdxpxbh();
				if (del) {
					sql = "insert into "
						+ realTable
						+ "(xh,djsqsj,bz,rdjjfzsj,rdjjfzpxbh,fzdxsj,fzdxpxbh) values(?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] { xh, djsqsj, bz, rdjjfzsj,
							rdjjfzpxbh, fzdxsj, fzdxpxbh });
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
				// =============== end �й���ҵ��ѧ ���ΰ 2009/4/9 =============

			}else if (realTable.equalsIgnoreCase("sxhbb")) {
				// ˼��㱨��Ϣ
				String djsj = dao.dateToStr(request.getParameter("djsj"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				if (xn == null) {
					xn = " ";
				}
				if (xq == null) {
					xq = " ";
				}
				if (nd == null) {
					nd = " ";
				}
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "delete from " + realTable + " where xh=? and djsj=?";
					del = dao.runUpdate(sql, new String[] { xh, djsj });
				} else {
					sql = "delete from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into " + realTable
					+ "(nd,xn,xq,xh,djsj,bz) values(?,?,?,?,?,?)";
					dao.runUpdate(sql,
							new String[] { nd, xn, xq, xh, djsj, bz });
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("xsbzxxb")) {
				// ѧ��������Ϣ
				String bzsj = dao.dateToStr(request.getParameter("bzsj"));
				String bzlxdm = request.getParameter("bzlxdm");
				String bzed = request.getParameter("bzed");
				String bzyy = DealString.toGBK(request.getParameter("bzyy"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "delete from " + realTable
					+ " where xn=? and xq=? and xh=? and bzlxdm=?";
					del = dao.runUpdate(sql,
							new String[] { xn, xq, xh, bzlxdm });
				} else {
					sql = "delete from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into "
						+ realTable
						+ "(nd,xn,xq,xh,bzsj,bzlxdm,bzed,bzyy,bz) values(?,?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] { nd, xn, xq, xh, bzsj,
							bzlxdm, bzed, bzyy, bz });
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("jxjgb")) {
				// ��ѵ�����Ϣ
				String sfhg = DealString.toGBK(request.getParameter("sfhg"));
				String sfjjfz = DealString
				.toGBK(request.getParameter("sfjjfz"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				String bxsfhg = DealString.toGBK(request.getParameter("bxsfhg"));
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					del = StandardOperation.delete(realTable, new String[] {
							"xn", "xq", "xh" }, new String[] { xn, xq, xh },
							request);
				} else {
					del = StandardOperation.delete(realTable, pk, pkValue,
							request);
				}
				if (del) {
					StandardOperation.insert(realTable, new String[] { "nd",
							"xn", "xq", "xh", "sfhg", "sfjjfz", "bz","bxsfhg" },
							new String[] { nd, xn, xq, xh, sfhg, sfjjfz, bz, bxsfhg},
							request);
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("xsgbxxb")) {
				// ѧ���ɲ���Ϣ
				String rzbmdm = request.getParameter("rzbmdm");
				String drzw = DealString.toGBK(request.getParameter("drzw"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				String khjg = DealString.toGBK(request.getParameter("khjg"));
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "delete from "
						+ realTable
						+ " where xn=? and xq=? and xh=? and rzbmdm=? and drzw=?";
					del = dao.runUpdate(sql, new String[] { xn, xq, xh, rzbmdm,
							drzw });
				} else {
					sql = "delete from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
						sql = "insert into "
							+ realTable
							+ "(nd,xn,xq,xh,rzbmdm,drzw,bz,khjg) values(?,?,?,?,?,?,?,?)";
						dao.runUpdate(sql, new String[] { nd, xn, xq, xh,
								rzbmdm, drzw, bz, khjg });
					} else {
						sql = "insert into "
							+ realTable
							+ "(nd,xn,xq,xh,rzbmdm,drzw,bz) values(?,?,?,?,?,?,?)";
						dao.runUpdate(sql, new String[] { nd, xn, xq, xh,
								rzbmdm, drzw, bz });
					}
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("zyzfwdjb")) {
				// ־Ը�߷�����Ϣ
				String fwl = DealString.toGBK(request.getParameter("fwl"));
				String fwsj = dao.dateToStr(request.getParameter("fwsj"));
				String fwnr = DealString.toGBK(request.getParameter("fwnr"));
				String khjg = DealString.toGBK(request.getParameter("khjg"));
				String zyzbh = DealString.toGBK(request.getParameter("zyzbh"));
				String drzw = DealString.toGBK(request.getParameter("drzw"));
				String cjzyzfwsj = request.getParameter("cjzyzfwsj");
				String[] delSql= new String[1];
				String[] intSql= new String[1];
				String[] uptSql= new String[1];
				boolean done = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "delete from " + realTable + " where xh='"+xh+"' and fwsj='"+fwsj+"'";
					delSql[0] = sql;
//					done = dao.runUpdate(sql, new String[] { xh, fwsj });
				} else {
					sql = "delete from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
					delSql[0] = sql;
					//done = dao.runUpdate(sql, new String[] {});
				}
//				if (done) {
					if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
						sql = "insert into "
							+ realTable
							+ "(nd,xn,xq,xh,fwnr,fwsj,fwl,khjg,zyzbh,drzw) values('"+nd+"','"+xn+"','"+xq+"','"+xh+"','"+fwnr+"','"+fwsj+"','"+fwl+"','"+khjg+"','"+zyzbh+"','"+drzw+"')";
						intSql[0]=sql;
//						done = dao.runUpdate(sql, new String[] { nd, xn, xq, xh, fwnr,
//								fwsj, fwl, khjg, zyzbh, drzw });
//						done = dao.runUpdate(" update zyzfwdjb set cjzyzfwsj=? ",new String[]{cjzyzfwsj});
					} else {
						sql = "insert into "
							+ realTable
							+ "(nd,xn,xq,xh,fwnr,fwsj,fwl,zyzbh,drzw) values('"+nd+"','"+xn+"','"+xq+"','"+xh+"','"+fwnr+"','"+fwsj+"','"+fwl+"','"+zyzbh+"','"+drzw+"')";
//						done = dao.runUpdate(sql, new String[] { nd, xn, xq, xh, fwnr,
//								fwsj, fwl, zyzbh, drzw });
						intSql[0]=sql;
					}
//				} else {
//					dataSearchForm.setErrMsg("sdf");
//					return mapping.findForward("false");
//				}
				uptSql[0]="update zyzfwdjb set cjzyzfwsj='"+cjzyzfwsj+"' where xh='"+xh+"' ";
				String[] exesql = dao.unionArray(delSql, intSql);
				exesql = dao.unionArray(exesql, uptSql);
				int[] array = null;
				array = dao.runBatch(exesql);
				done = dao.checkBatch(array);
				if (!done) {					
					return mapping.findForward("false");
				}	
			} else if (realTable.equalsIgnoreCase("sthdxxb")) {
				// ���Ż��Ϣ
				String stdm = request.getParameter("stdm");
				String jrsj = dao.dateToStr(request.getParameter("jrsj"));
				String tcsj = dao.dateToStr(request.getParameter("tcsj"));
				String hydj = DealString.toGBK(request.getParameter("hydj"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				String rzqk = DealString.toGBK(request.getParameter("rzqk"));
				String khjg = DealString.toGBK(request.getParameter("khjg"));
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "delete from " + realTable
					+ " where xh=? and stdm=? and jrsj=?";
					del = dao.runUpdate(sql, new String[] { xh, stdm, jrsj });
				} else {
					sql = "delete from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
						sql = "insert into "
							+ realTable
							+ "(nd,xn,xq,xh,stdm,jrsj,tcsj,bz,rzqk,khjg) values(?,?,?,?,?,?,?,?,?,?)";
						dao.runUpdate(sql, new String[] { nd, xn, xq, xh, stdm,
								jrsj, tcsj, bz, rzqk, khjg });
					} else {
						sql = "insert into "
							+ realTable
							+ "(nd,xn,xq,xh,stdm,jrsj,tcsj,hydj,bz) values(?,?,?,?,?,?,?,?,?)";
						dao.runUpdate(sql, new String[] { nd, xn, xq, xh, stdm,
								jrsj, tcsj, hydj, bz });
					}
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("ssxxb")) {
				// ��Դ��Ϣ
				String ssbh = DealString.toGBK(request.getParameter("ssbh"));
				String lddm = DealString.toGBK(request.getParameter("lddm"));
				String qsh = DealString.toGBK(request.getParameter("qsh"));
				String qsdh = DealString.toGBK(request.getParameter("qsdh"));
				String cws = DealString.toGBK(request.getParameter("cws"));
				String fpbj = DealString.toGBK(request.getParameter("fpbj"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				String shq = DealString.toGBK(request.getParameter("shq"));
				String dxdh = DealString.toGBK(request.getParameter("dxdh"));
				String ttdh = DealString.toGBK(request.getParameter("ttdh"));
				String sfbz = DealString.toGBK(request.getParameter("sfbz"));
				String wxport = DealString.toGBK(request.getParameter("wxport"));
				String cs = DealString.toGBK(request.getParameter("cs"));
				String sffqfj = DealString.toGBK(request.getParameter("sffqfj"));
				String xlcws = DealString.toGBK(request.getParameter("xlcws"));
				//xlcws = Base.isNull(xlcws)?"1":xlcws;
				boolean del = false;
				//String[] sqlArr = new String[4];

				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					del = StandardOperation.delete(realTable, "ssbh", ssbh,
							request);					
				} else {
					del = StandardOperation.delete(realTable, pk, pkValue,
							request);					
				}
				boolean done = false;
				if (del) {
//					if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
					if (!Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm)) {
						done =StandardOperation.insert(realTable, new String[] {
								"ssbh", "lddm", "qsh", "qsdh", "cws", "fpbj",
								"bz", "shq", "dxdh", "ttdh", "wxport", "cs",
								"sfbz", "sffqfj" }, new String[] { ssbh, lddm,
								qsh, qsdh, cws, fpbj, bz, shq, dxdh, ttdh,
								wxport, cs, sfbz, sffqfj }, request);
					} else {
						done =StandardOperation.insert(realTable, new String[] {
								"ssbh", "lddm", "qsh", "qsdh", "cws", "fpbj",
								"bz", "shq", "dxdh", "ttdh", "wxport", "cs",
								"sfbz", "sffqfj","xlcws" }, new String[] { ssbh, lddm,
								qsh, qsdh, cws, fpbj, bz, shq, dxdh, ttdh,
								wxport, cs, sfbz, sffqfj,xlcws }, request);
					}
					request.setAttribute("done",done);
//					} 
//					else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
//					StandardOperation.insert(realTable, new String[] {
//					"ssbh", "lddm", "qsh", "qsdh", "cws", "fpbj",
//					"bz" , "cs","sfbz"}, new String[] { ssbh, lddm, qsh, qsdh,
//					cws, fpbj, bz, cs,sfbz }, request);
//					} else {
//					StandardOperation.insert(realTable, new String[] {
//					"ssbh", "lddm", "qsh", "qsdh", "cws", "fpbj",
//					"bz","cs","sfbz" }, new String[] { ssbh, lddm, qsh, qsdh,
//					cws, fpbj, bz,cs,sfbz }, request);
//					}
					//���´�λ����
					String[] sqls = new String[Integer.parseInt(cws)+1];
					sqls[0] = "delete from cwxxb where ssbh='"+ssbh+"' ";
					for(int i=1;i<=Integer.parseInt(cws);i++){
						sql = "insert into cwxxb (ssbh,cwh) values('"+ssbh+"','"+i+"')";
						sqls[i] = sql;
					}
					dao.runBatch(sqls);					
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("xszsxxb")) {
				// ס����Ϣά��
				String ssbh = DealString.toGBK(request.getParameter("ssbh"));
				String rzrq = dao.dateToStr(request.getParameter("rzrq"));
				String jzrq = dao.dateToStr(request.getParameter("jzrq"));
				String cwh =  DealString.toGBK(request.getParameter("cwh"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				String zsf =  DealString.toGBK(request.getParameter("zsf"));
				String dsjssf =  DealString.toGBK(request.getParameter("dsjssf"));
				String gyqk = DealString.toGBK(request.getParameter("gyqk"));
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					del = StandardOperation.delete(realTable, "xh", xh, request);
				} else {
					del = StandardOperation.delete(realTable, pk, pkValue,
							request);
				}
				if (del) {					
					StandardOperation.insert(realTable, new String[] {
							"xh", "ssbh", "rzrq", "jzrq", "cwh", "bz",
							"zsf", "dsjssf", "gyqk" }, new String[] { xh,
							ssbh, rzrq, jzrq, cwh, bz, zsf, dsjssf, gyqk },
							request);					
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("mrzbjlb")) {
				// ÿ��ֵ���¼
				String sj = request.getParameter("sj");
				String lddm = request.getParameter("lddm");
				String zbrydm = DealString.toGBK(request.getParameter("zbrydm"));
				String zbrydm1 = request.getParameter("zbrydm1");
				String tq = DealString.toGBK(request.getParameter("tq"));
				String zbjl = DealString.toGBK(request.getParameter("zbjl"));
				String tfsjjcl = DealString.toGBK(request.getParameter("tfsjjcl"));
				String zw  = DealString.toGBK(request.getParameter("zw"));
				String bmdm = request.getParameter("bmdm");
				String xqdm = dataSearchForm.getXqdm();
				String zbrlx = DealString.toGBK(request.getParameter("zbrlx"));
				String dgsj = DealString.toGBK(request.getParameter("dgsj"));
				String lgsj = DealString.toGBK(request.getParameter("lgsj"));

				xxmc = dao.getOneRs("select xxmc from xtszb", new String[] {},"xxmc");
				boolean result = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					result = StandardOperation.delete(realTable, new String[] {
							"sj", "zbrydm" }, new String[] { sj, zbrydm },
							request);
				} else {
					result = StandardOperation.delete(realTable, pk, pkValue,
							request);
				}
				if (result) {
					result =StandardOperation.insert(realTable, new String[] {"sj", "lddm", "zbrydm", "zbrydm1", "tq",
							"zbjl", "tfsjjcl", "xqdm","zbrlx","dgsj","lgsj","zw","bmdm"}, new String[] { sj, lddm,
							zbrydm, zbrydm1, tq, zbjl, tfsjjcl,xqdm,zbrlx,dgsj,lgsj,zw,bmdm }, request);					
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
				request.setAttribute("result", result);
			} else if (realTable.equalsIgnoreCase("yzzbhzb")) {
				// һ��ֵ�����
				String qssj = request.getParameter("qssj");
				String jssj = request.getParameter("jssj");
				String lddm = request.getParameter("lddm");
				String zyzbjlhz = DealString.toGBK(request
						.getParameter("zyzbjlhz"));
				String xgbmclqk = DealString.toGBK(request
						.getParameter("xgbmclqk"));
				String yldps = DealString.toGBK(request.getParameter("yldps"));
				String hzr = DealString.toGBK(dataSearchForm.getHzr());
				String xqdm = dataSearchForm.getXqdm();
				boolean result = false;
				xxmc = dao.getOneRs("select xxmc from xtszb ", new String[] {},
				"xxmc");
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)) {
						result = StandardOperation.delete(realTable, new String[] {
								"qssj", "jssj", "hzr" }, new String[] { qssj,
								jssj, hzr }, request);
					} else {
						result = StandardOperation.delete(realTable, new String[] {
								"qssj", "jssj", "lddm" }, new String[] { qssj,
								jssj, lddm }, request);
					}
				} else {
					result = StandardOperation.delete(realTable, pk, pkValue,
							request);
				}
				if (result) {
					if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)) {
						result=StandardOperation
						.insert(realTable, new String[] { "qssj",
								"jssj", "hzr", "zyzbjlhz", "xgbmclqk",
								"yldps", "xqdm" }, new String[] { qssj,
								jssj, hzr, zyzbjlhz, xgbmclqk, yldps,
								xqdm }, request);
					} else {
						result=StandardOperation.insert(realTable, new String[] {
								"qssj", "jssj", "lddm", "zyzbjlhz", "xgbmclqk",
						"yldps" }, new String[] { qssj, jssj, lddm,
								zyzbjlhz, xgbmclqk, yldps }, request);
					}

				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
				request.setAttribute("result", result);
			} else if (realTable.equalsIgnoreCase("xszffb")
					|| realTable.equalsIgnoreCase("xhffb")) {
				// ѧ��֤����ά����У��ά��
				String ffsj = dao.dateToStr(request.getParameter("ffsj"));
				String jbr = DealString.toGBK(request.getParameter("jbr"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "delete from " + realTable + " where xh=? and ffsj=?";
					del = dao.runUpdate(sql, new String[] { xh, ffsj });
				} else {
					sql = "delete from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into " + realTable
					+ "(nd,xn,xq,xh,ffsj,jbr,bz) values(?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] { nd, xn, xq, xh, ffsj,
							jbr, bz });
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("hcyhkffb")) {
				// ���Żݿ�����ά��
				String ffsj = dao.dateToStr(request.getParameter("ffsj"));
				String jbr = DealString.toGBK(request.getParameter("jbr"));
				String jtdz = DealString.toGBK(request.getParameter("jtdz"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "delete from " + realTable + " where xh=? and ffsj=?";
					del = dao.runUpdate(sql, new String[] { xh, ffsj });
				} else {
					sql = "delete from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into "
						+ realTable
						+ "(nd,xn,xq,xh,ffsj,jbr,jtdz,bz) values(?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] { nd, xn, xq, xh, ffsj,
							jbr, jtdz, bz });
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("xszbbb")
					|| realTable.equalsIgnoreCase("hcyhkbbb")
					|| realTable.equalsIgnoreCase("yktbbb")
					|| realTable.equalsIgnoreCase("xhbbb")) {
				// ѧ��֤����,���Żݿ�����,һ��ͨ����,У�ղ���ά��
//				String sqsj = dao.dateToStr(dao.dateToStr(request
//				.getParameter("sqsj")));
				String jbr = DealString.toGBK(request.getParameter("jbr"));
				String bbsj = dao.dateToStr(request.getParameter("bbsj"));
				String sflq = DealString.toGBK(request.getParameter("sflq"));
				String bbyy = DealString.toGBK(request.getParameter("bbyy"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				String sfzh = DealString.toGBK(request.getParameter("sfzh"));
				String syd = DealString.toGBK(request.getParameter("syd"));
//				String xz = DealString.toGBK(request.getParameter("xz"));
//				String csrq = DealString.toGBK(request.getParameter("csrq"));
//				String hczm = DealString.toGBK(request.getParameter("hczm"));


				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					del = true;
				} else {
					sql = "delete from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
					del = dao.runUpdate(sql, new String[] {});
				}

				if (del) {
					String sqllx = "SELECT * FROM xszbbb WHERE xh||sqsj=?";
					List xhsqsj = dao.getList(sqllx, new String[] {xh+bbsj}, new String[] {"xh","sqsj"});
					if(xhsqsj.size() > 0){
						request.setAttribute("tgcg", "tgcg");
					}else{
						request.setAttribute("tgcg", "tgcg1");
						if(xxdm == "10690" || "10690".equalsIgnoreCase(xxdm)){
							if(realTable.equalsIgnoreCase("xszbbb")){
								String fdysh = "";
								String xxsh = "";
								if (!(pkValue.equalsIgnoreCase(""))) {
									fdysh= "ͨ��";
									xxsh = "ͨ��";
								}else{
									fdysh= "ͨ��";
									xxsh = "δ���";
								}
								sql = "insert into "+ realTable+ "(nd,xn,xq,xh,bbsj,jbr,sflq,bbyy,bz,sfzh,syd,fdysh,xxsh) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
								dao.runUpdate(sql, new String[] { nd, xn, xq, xh, bbsj,
										jbr, sflq, bbyy, bz,sfzh,syd ,fdysh,xxsh});
							} else {
								String fdysh = "";
								String xxsh = "";
								if (!(pkValue.equalsIgnoreCase(""))) {
									fdysh= "ͨ��";
									xxsh = "ͨ��";
								}else{
									fdysh= "ͨ��";
									xxsh = "δ���";
								}
								sql = "insert into "+ realTable+ "(nd,xn,xq,xh,bbsj,jbr,sflq,bbyy,bz,fdysh,xxsh) values(?,?,?,?,?,?,?,?,?,?,?)";
								dao.runUpdate(sql, new String[] { nd, xn, xq, xh, bbsj,
										jbr, sflq, bbyy, bz,fdysh,xxsh});
							}
						}else{
							if(realTable.equalsIgnoreCase("xszbbb")){
								sql = "insert into "+ realTable+ "(nd,xn,xq,xh,bbsj,jbr,sflq,bbyy,bz,sfzh,syd) values(?,?,?,?,?,?,?,?,?,?,?)";
								dao.runUpdate(sql, new String[] { nd, xn, xq, xh, bbsj,
										jbr, sflq, bbyy, bz,sfzh,syd });
							} else {
								sql = "insert into "+ realTable+ "(nd,xn,xq,xh,bbsj,jbr,sflq,bbyy,bz) values(?,?,?,?,?,?,?,?,?)";
								dao.runUpdate(sql, new String[] { nd, xn, xq, xh, bbsj,
										jbr, sflq, bbyy, bz});
							}
						}
					}
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("hcyhkczb")) {
				// ���Żݿ���ֵά��
				String jbr = DealString.toGBK(request.getParameter("jbr"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				String czsj = request.getParameter("czsj");
				String czje = request.getParameter("czje");
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					del = true;
				} else {
					sql = "delete from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into "
						+ realTable
						+ "(nd,xn,xq,xh,jbr,bz,czsj,czje) values(?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] { nd, xn, xq, xh, jbr, bz,
							czsj, czje });
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("xssztzb")) {
				// ѧ��������չά��
				String sxzzddsy = DealString.toGBK(request
						.getParameter("sxzzddsy"));
				String shsjzyfw = DealString.toGBK(request
						.getParameter("shsjzyfw"));
				String xskjcxcy = DealString.toGBK(request
						.getParameter("xskjcxcy"));
				String whyssxfz = DealString.toGBK(request
						.getParameter("whyssxfz"));
				String sthdshgz = DealString.toGBK(request
						.getParameter("sthdshgz"));
				String jnpxqt = DealString
				.toGBK(request.getParameter("jnpxqt"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "delete from " + realTable
					+ " where xh=? and xn=? and xq=?";
					del = dao.runUpdate(sql, new String[] { xh, xn, xq });
				} else {
					sql = "delete from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into "
						+ realTable
						+ "(nd,xn,xq,xh,sxzzddsy,shsjzyfw,xskjcxcy,whyssxfz,sthdshgz,jnpxqt,bz) values(?,?,?,?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql,
							new String[] { nd, xn, xq, xh, sxzzddsy, shsjzyfw,
							xskjcxcy, whyssxfz, sthdshgz, jnpxqt, bz });
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("hcccb")) {
				// ������Ϣά��
				String cc = DealString.toGBK(request.getParameter("cc"));
				String qdz = request.getParameter("qdz");
				String zdz = request.getParameter("zdz");
				String kcsj = request.getParameter("kcsj");
				String ddsj = request.getParameter("ddsj");
				String yxsj = request.getParameter("yxsj");
				String pj = request.getParameter("pj");
				String tkz = DealString.toGBK(request.getParameter("tkz"));
				String dqzt = DealString.toGBK(request.getParameter("dqzt"));
				boolean result = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "delete from " + realTable + " where cc=?";
					result = dao.runUpdate(sql, new String[] { cc });
				} else {
					sql = "delete from " + realTable + " where " +  pk + "='"
					+ pkValue + "'";
					result = dao.runUpdate(sql, new String[] {});
				}
				if (result) {
					sql = "insert into "
						+ realTable
						+ "(cc,qdz,zdz,kcsj,ddsj,yxsj,pj,tkz,dqzt) values(?,?,?,?,?,?,?,?,?)";
					result=dao.runUpdate(sql, new String[] { cc, qdz, zdz, kcsj, ddsj,
							yxsj, pj, tkz, dqzt });
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
				request.setAttribute("result", result);
			} else if (realTable.equalsIgnoreCase("bks_xsszjbxx")) {
				// ѧ����Ϣά��
//				WebServiceClientForXmlgxy ws = new WebServiceClientForXmlgxy();
				String lxdh1 = request.getParameter("lxdh1");
				// String lxdh2 = request.getParameter("lxdh2");
				String sjhm = request.getParameter("sjhm");
				String email = request.getParameter("email");
				String ssbh = request.getParameter("ssbh");
				String cwh = request.getParameter("cwh");
				xh = request.getParameter("xh");
				boolean flag = false;
				if (pkValue != null && !pkValue.trim().equalsIgnoreCase("")) {
					sql = "select xh from xsfzxxb where xh=?";
					String str = dao.getOneRs(sql, new String[] { xh }, "xh");
					if (str.equalsIgnoreCase("")) {
						flag = StandardOperation.insert("xsfzxxb", new String[] { "xh", "lxdh1", "sjhm", "email" },
								new String[] { xh, lxdh1, sjhm, email }, request);

					} else {
						flag = StandardOperation.update("xsfzxxb", new String[] { "lxdh1", "sjhm", "email" }, 
								new String[] { lxdh1, sjhm, email }, "xh", xh, request);

					}
					sql = "select xh from xsxxb where xh=?";
					str = dao.getOneRs(sql, new String[] { xh }, "xh");
					if (str.equalsIgnoreCase("")) {
						flag = StandardOperation.update("xsxxb","insert into xsxxb(xh,xm,xb,nj,xydm,xy,zydm,zymc,bjdm,bjmc)" +
								"(select xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc from view_xsjbxx where xh='"+xh+"')", request);

					}
					flag = StandardOperation.update("xsxxb", new String[] {
							"lxdh", "sjhm", "dzyx" }, new String[] { lxdh1,
							sjhm, email }, "xh", xh, request);

					if (!ssbh.equalsIgnoreCase("")) {
						sql = "select xh from xszsxxb where xh=?";
						str = dao.getOneRs(sql, new String[] { xh }, "xh");
						if (str.equalsIgnoreCase("")) {
							flag = StandardOperation.insert("xszsxxb",
									new String[] { "xh", "ssbh", "cwh" },
									new String[] { xh, ssbh, cwh }, request);
						} else {
							flag = StandardOperation.update("xszsxxb",
									new String[] { "ssbh", "cwh" },
									new String[] { ssbh, cwh }, "xh", xh,
									request);
						}
					}
					request.setAttribute("result", flag);
				}
				return null;
			} else if (realTable.equalsIgnoreCase("qcccb")) {
				// ������Ϣά��
				String cc = DealString.toGBK(request.getParameter("cc"));
				String qdz = request.getParameter("qdz");
				String zdz = request.getParameter("zdz");
				String kcsj = request.getParameter("kcsj");
				String ddsj = request.getParameter("ddsj");
				String yxsj = request.getParameter("yxsj");
				String pj = request.getParameter("pj");
				String tkz = DealString.toGBK(request.getParameter("tkz"));
				String dqzt = DealString.toGBK(request.getParameter("dqzt"));
				boolean del = false;
				if ((pkValue == null) || pkValue.equalsIgnoreCase("")) {
					sql = "delete from " + realTable + " where cc=?";
					del = dao.runUpdate(sql, new String[] { cc });
				} else {
					sql = "delete from " + realTable + " where " + pk + "='"
					+ pkValue + "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into "
						+ realTable
						+ "(cc,qdz,zdz,kcsj,ddsj,yxsj,pj,tkz,dqzt) values(?,?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] { cc, qdz, zdz, kcsj, ddsj,
							yxsj, pj, tkz, dqzt });
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("xszxbzb")) {

				String bysj = DealString.toGBK(request.getParameter("bysj"));
				String jtdz = DealString.toGBK(request.getParameter("jtdz"));
				String yzbm = DealString.toGBK(request.getParameter("yzbm"));
				String jtcy1_xm = DealString.toGBK(request
						.getParameter("jtcy1_xm"));
				String jtcy1_gx = DealString.toGBK(request
						.getParameter("jtcy1_gx"));
				String jtcy1_gzdw = DealString.toGBK(request
						.getParameter("jtcy1_gzdw"));
				String jtcy1_ysr = DealString.toGBK(request
						.getParameter("jtcy1_ysr"));
				String jtcy2_xm = DealString.toGBK(request
						.getParameter("jtcy2_xm"));
				String jtcy2_gx = DealString.toGBK(request
						.getParameter("jtcy2_gx"));
				String jtcy2_gzdw = DealString.toGBK(request
						.getParameter("jtcy2_gzdw"));
				String jtcy2_ysr = DealString.toGBK(request
						.getParameter("jtcy2_ysr"));
				String jtcy3_xm = DealString.toGBK(request
						.getParameter("jtcy3_xm"));
				String jtcy3_gx = DealString.toGBK(request
						.getParameter("jtcy3_gx"));
				String jtcy3_gzdw = DealString.toGBK(request
						.getParameter("jtcy3_gzdw"));
				String jtcy3_ysr = DealString.toGBK(request
						.getParameter("jtcy3_ysr"));
				String jtcy4_xm = DealString.toGBK(request
						.getParameter("jtcy4_xm"));
				String jtcy4_gx = DealString.toGBK(request
						.getParameter("jtcy4_gx"));
				String jtcy4_gzdw = DealString.toGBK(request
						.getParameter("jtcy4_gzdw"));
				String jtcy4_ysr = DealString.toGBK(request
						.getParameter("jtcy4_ysr"));
				String jtcy5_xm = DealString.toGBK(request
						.getParameter("jtcy5_xm"));
				String jtcy5_gx = DealString.toGBK(request
						.getParameter("jtcy5_gx"));
				String jtcy5_gzdw = DealString.toGBK(request
						.getParameter("jtcy5_gzdw"));
				String jtcy5_ysr = DealString.toGBK(request
						.getParameter("jtcy5_ysr"));
				String jttgje = DealString
				.toGBK(request.getParameter("jttgje"));
				String zxje = DealString.toGBK(request.getParameter("zxje"));
				String jxje = DealString.toGBK(request.getParameter("jxje"));
				String qgzxje = DealString
				.toGBK(request.getParameter("qgzxje"));
				String xnwxdkje = DealString.toGBK(request
						.getParameter("xnwxdkje"));
				String qtsrje = DealString
				.toGBK(request.getParameter("qtsrje"));
				String zxdkje = DealString
				.toGBK(request.getParameter("zxdkje"));
				String zxdksj = DealString
				.toGBK(request.getParameter("zxdksj"));
				String yffzxdkje = DealString.toGBK(request
						.getParameter("yffzxdkje"));
				String yffzxdksj = DealString.toGBK(request
						.getParameter("yffzxdksj"));
				String sqzzly = DealString
				.toGBK(request.getParameter("sqzzly"));
				String zzff1 = DealString.toGBK(request.getParameter("zzff1"));
				String zzff1qsje = DealString.toGBK(request
						.getParameter("zzff1qsje"));
				String zzff1jsje = DealString.toGBK(request
						.getParameter("zzff1jsje"));
				String xxsh = DealString.toGBK(request.getParameter("yesNo"));

				if (xxsh == null || xxsh.equalsIgnoreCase("")) {
					xxsh = "δ���";
				}

				// String bzdm = dao.getOneRs("select knbzdm from knbzdmb where
				// knbzmc like 'ר��%'",
				// new String[]{}, new String[]{"knbzdm"})[0];
				String bzdm = "1";
				String bzmc = "ר���";

				sql = "delete from " + realTable
				+ " where xh=? and nd=? and bzdm=? ";
				dao.runUpdate(sql, new String[] { xh, nd, bzdm });

				sql = "insert into xszxbzb(bzdm,bzmc,xn,nd,xh,bysj,jtdz,yzbm,"
					+ "jtcy1_xm,jtcy1_gx,jtcy1_gzdw,jtcy1_ysr,"
					+ "jtcy2_xm,jtcy2_gx,jtcy2_gzdw,jtcy2_ysr,"
					+ "jtcy3_xm,jtcy3_gx,jtcy3_gzdw,jtcy3_ysr,"
					+ "jtcy4_xm,jtcy4_gx,jtcy4_gzdw,jtcy4_ysr,"
					+ "jtcy5_xm,jtcy5_gx,jtcy5_gzdw,jtcy5_ysr,"
					+ "jttgje,zxje,jxje,qgzxje,xnwxdkje,qtsrje,zxdkje,zxdksj,yffzxdkje,yffzxdksj,"
					+ "sqzzly,zzff1,zzff1qsje,zzff1jsje,xq,xxsh) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				String[] input = new String[] { bzdm, bzmc, xn, nd, xh, bysj,
						jtdz, yzbm, jtcy1_xm, jtcy1_gx, jtcy1_gzdw, jtcy1_ysr,
						jtcy2_xm, jtcy2_gx, jtcy2_gzdw, jtcy2_ysr, jtcy3_xm,
						jtcy3_gx, jtcy3_gzdw, jtcy3_ysr, jtcy4_xm, jtcy4_gx,
						jtcy4_gzdw, jtcy4_ysr, jtcy5_xm, jtcy5_gx, jtcy5_gzdw,
						jtcy5_ysr, jttgje, zxje, jxje, qgzxje, xnwxdkje,
						qtsrje, zxdkje, zxdksj, yffzxdkje, yffzxdksj, sqzzly,
						zzff1, zzff1qsje, zzff1jsje, xq, xxsh };

				dao.runUpdate(sql, input);

			} else if (realTable.equalsIgnoreCase("lstdb")) {
				String jtdz = DealString.toGBK(request.getParameter("jtdz"));
				String je = DealString.toGBK(request.getParameter("je"));
				sql = "delete from " + realTable + " where xh=? and nd=? ";
				dao.runUpdate(sql, new String[] { xh, nd });
				sql = "insert into " + realTable + " (xh,jtdz,je,nd,xn,xq) "
				+ " values(?,?,?,?,?,?)";
				String[] input = new String[] { xh, jtdz, je, nd, xn, xq };
				dao.runUpdate(sql, input);
			} else if (realTable.equalsIgnoreCase("xfcjb")) {
				String je = DealString.toGBK(request.getParameter("je"));
				String qflxdm = dataSearchForm.getQflxdm();
				String bz = DealString.toGBK(dataSearchForm.getBz());
			
				sql = "delete from "
					+ realTable
					+ " where xh = ? and nd = ? and xn = ? and xq = ? and qflxdm = ?";
				dao.runUpdate(sql, new String[] { xh, nd, xn, xq, qflxdm });
				sql = "insert into " + realTable + " (xh,je,nd,xn,xq,qflxdm,bz) "
				+ " values(?,?,?,?,?,?,?)";
				String[] input = new String[] { xh, je, nd, xn, xq, qflxdm,bz };
				dao.runUpdate(sql, input);
			} else if (realTable.equalsIgnoreCase("xfhjb")) {
				String hjje = DealString.toGBK(request.getParameter("hjje"));
				String hjqx = DealString.toGBK(request.getParameter("hjqx"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				sql = "delete from " + realTable + " where xh=? and nd=? ";
				dao.runUpdate(sql, new String[] { xh, nd });
				sql = "insert into " + realTable
				+ " (xh,hjje,nd,xn,xq,hjqx,bz) "
				+ " values(?,?,?,?,?,?,?)";
				String[] input = new String[] { xh, hjje, nd, xn, xq, hjqx, bz };
				dao.runUpdate(sql, input);
			} else if (realTable.equalsIgnoreCase("gywxglb")) {

				String lddm = DealString.toGBK(dataSearchForm.getLddm());
//				String userName = session.getAttribute("userName").toString();
				//String fzld = gyglDao.getLddmxXx( userName);
				//��Ԣ����Ա�ж�begin
//				String lddmStr = gyglDao.getLddmxXx(userName);
//				String isGyFdy = "no";
//				if(!Base.isNull(lddmStr)){
//				lddm = lddmStr; 
//				//comForm.setLddm(lddm);
//				isGyFdy = "yes";
//				}
//				request.setAttribute("isGyFdy","");
				//��Ԣ����Ա�ж�end


				String qsh = DealString.toGBK(dataSearchForm.getQsh());
				String bxsj = DealString.toGBK(dataSearchForm.getBxsj());
				String wxsj = DealString.toGBK(dataSearchForm.getWxsj());
				String wxry = DealString.toGBK(dataSearchForm.getRydm());
				String wxnr = DealString.toGBK(request.getParameter("wxnr"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				String wxbm = DealString.toGBK(request.getParameter("bmdm"));
				String bxbm = DealString.toGBK(request.getParameter("bxbm"));
				String bxr = DealString.toGBK(request.getParameter("bxr"));
				String ssbh = dao.getOneRs("select ssbh from ssxxb where lddm=? and qsh=?",new String[]{lddm,qsh},"ssbh");

				sql = " select ssbh from ssxxb where ssbh = ? ";
				String tmp = dao.getOneRs(sql, new String[] { ssbh }, "ssbh");
				
				boolean result = false;
				//==============2010/7/28 edit by luojw=============================
				if (tmp != null && !tmp.equalsIgnoreCase("")) {
					result = StandardOperation.delete(realTable, new String[] {
							"ssbh", "bxsj" }, new String[] { ssbh, bxsj },
							request);
					if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
						if (wxsj.equalsIgnoreCase("")
								|| wxsj.equalsIgnoreCase("δά��") || wxsj == null) {
							result = StandardOperation
									.insert(realTable,
											new String[] { "xn", "xq", "ssbh",
													"bxsj", "wxnr", "bz" },
											new String[] { xn, xq, ssbh, bxsj,
													wxnr, bz }, request);
						} else {
							result = StandardOperation.insert(realTable,
									new String[] { "xn", "xq", "ssbh", "bxsj",
											"wxsj", "wxnr", "wxry", "bz",
											"wxbm" }, new String[] { xn, xq,
											ssbh, bxsj, wxsj, wxnr, wxry, bz,
											wxbm }, request);
						}
					} else {
						if (wxsj.equalsIgnoreCase("")
								|| wxsj.equalsIgnoreCase("δά��") || wxsj == null) {
							result = StandardOperation.insert(realTable,
									new String[] { "xn", "xq", "ssbh", "bxsj",
											"wxnr", "bz", "bxbm", "bxr" },
									new String[] { xn, xq, ssbh, bxsj, wxnr,
											bz, bxbm, bxr }, request);
						} else {
							result = StandardOperation.insert(realTable,
									new String[] { "xn", "xq", "ssbh", "bxsj",
											"wxsj", "wxnr", "wxry", "bz",
											"bxbm", "bxr" }, new String[] { xn,
											xq, ssbh, bxsj, wxsj, wxnr, wxry,
											bz, bxbm, bxr }, request);
						}
					}
				}
				request.setAttribute("result", result);
			} else if (realTable.equalsIgnoreCase("gywsjcb")) {//TODO
				String lddm = DealString.toGBK(dataSearchForm.getLddm());
				//��Ԣ����Ա�ж�begin
//				String userName = session.getAttribute("userName").toString();
//				String lddmStr = gyglDao.getLddmxXx(userName);
//				String isGyFdy = "no";
//				if(!Base.isNull(lddmStr)){
//				lddm = lddmStr; 
//				//comForm.setLddm(lddm);
//				isGyFdy = "yes";					
//				}
//				request.setAttribute("isGyFdy","");
				//��Ԣ����Ա�ж�end					

				//==================begin 2009/5/25  =================
				String qsh = DealString.toGBK(dataSearchForm.getQsh());
				String jcsj = DealString.toGBK(dataSearchForm.getJcsj());
				String jcbm = DealString.toGBK(dataSearchForm.getJcbm());
				String fs = DealString.toGBK(request.getParameter("fs"));
				String dj = DealString.toGBK(request.getParameter("dj"));
				String zs = DealString.toGBK(request.getParameter("zs"));
				String bz = DealString.toGBK(request.getParameter("bz"));               
				String ssbh =dao.getOneRs("select ssbh from ssxxb where lddm=? and qsh=?",new String[]{lddm,qsh},"ssbh");
				
				if (zs != null && zs.length() != 1 && zs.length() != 2) {
					zs = zs.replace("��", "").replace("��", "");
				}
				if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {
					dj = DealString.toGBK(request.getParameter("fs"));
					fs = dj;
					dj = dao.getOneRs(
							"select wsjcdj from hhgxy_wsjcdj where wsjccj = ?",
							new String[] { dj }, "wsjcdj");
				}
				sql = " select ssbh from ssxxb where ssbh = ? ";
				String tmp = dao.getOneRs(sql, new String[] { ssbh }, "ssbh");
				if (tmp != null && !tmp.equalsIgnoreCase("")) {
					boolean done = false;
					if (!Base.isNull(jcsj)) {
					//-------------2010/6/25 edit by luojw------------------------
					//if (xxdm.equalsIgnoreCase(Globals.XXDM_WXSYZYJSXY)) {
						done = StandardOperation.delete(realTable, new String[] {
								"xn", "xq", "ssbh", "jcsj" }, new String[] { xn,
								xq, ssbh, jcsj }, request);
					} else {
						done = StandardOperation.delete(realTable, new String[] {
								"xn", "xq", "ssbh", "zs" }, new String[] { xn,
								xq, ssbh, zs }, request);
					}
					if (done) {
						if (!Base.isNull(jcsj)) {
							done= StandardOperation.insert(realTable, new String[] {
									"xn", "xq", "jcbm", "fs", "bz", "dj",
									"ssbh", "zs", "jcsj" }, new String[] { xn,
									xq, jcbm, fs, bz, dj, ssbh, zs, jcsj },
									request);
						} else {
							done = StandardOperation.insert(realTable, new String[] {
									"xn", "xq", "jcbm", "fs", "bz", "dj",
									"ssbh", "zs" }, new String[] { xn, xq,
									jcbm, fs, bz, dj, ssbh, zs }, request);
						}
					}
					request.setAttribute("done", done);
				}
//				==================end 2009/5/25 ���ΰ =================
			} else if (realTable.equalsIgnoreCase("kccjcpb")) {
				String cpf = DealString.toGBK(request.getParameter("cpf"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				sql = " delete from " + realTable
				+ " where xh = ? and xn = ? and xq = ? ";
				dao.runUpdate(sql, new String[] { xh, xn, xq });
				sql = " insert into " + realTable
				+ " (xh,xn,xq,cpf,bz) values (?,?,?,?,?)";
				dao.runUpdate(sql, new String[] { xh, xn, xq, cpf, bz });
			} else if (realTable.equalsIgnoreCase("zsjlb")) {

//				String lddm = DealString.toGBK(request.getParameter("lddm"));
//				String qsh = DealString.toGBK(request.getParameter("qsh"));
				String wjsj = DealString.toGBK(dataSearchForm.getWjsj());
				String wjsy = DealString.toGBK(request.getParameter("wjsy"));
				String cljg = DealString.toGBK(request.getParameter("cljg"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				String ch = DealString.toGBK(request.getParameter("cwh"));
				String wjlbdm = request.getParameter("wjlbdm");
				String ssbh = request.getParameter("ssbh");
				String saveType = request.getParameter("saveType");
				String yjfs = request.getParameter("fs");
				String xsxx = DealString.toGBK(request.getParameter("xsxx"));
				sql = " select ssbh from ssxxb where ssbh = ? ";
				String tmp = dao.getOneRs(sql, new String[] { ssbh }, "ssbh");
				boolean doFlag = false;
				if (tmp != null && !tmp.equalsIgnoreCase("")) {
					if(!Base.isNull(saveType)&&"more".equalsIgnoreCase(saveType)){
						String[] rzStu        = dao.getRs(" select xh from xszsxxb where ssbh=? ",new String[]{ssbh},"xh"); 
						String[] inserSql     = new String[rzStu.length];
						String[] delSql       = new String[rzStu.length];

						//if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {
							String brsql = "delete from " + realTable
							+ " where xh=? and wjsj='" + wjsj + "'";
							dao.runUpdate(brsql, new String[] { xh });
							brsql = "insert into "
								+ realTable
								+ "(xh,xn,xq,ssbh,wjsy,cljg,wjsj,bz,ch,wjlbdm,yjfs) "
								+ "values('" + xh + "','" + xn + "','" + xq
								+ "','" + ssbh + "','" + wjsy + "','"
								+ cljg + "','" + wjsj + "','" + bz + "','"
								+ ch + "','" + wjlbdm + "','" + yjfs + "')";
							dao.runUpdate(brsql, new String[] {});

							rzStu = new String[0];
							if (!"".equalsIgnoreCase(xsxx)) {
								String[] xx = xsxx.split("!!SplitSignOne!!");
								if (xx.length > 0) {
									rzStu = new String[xx.length];
									for (int i = 0; i < xx.length; i++) {
										String[] xx1 = xx[i].split(":");
										String xhV = xx1[1].replace("����", "")
										.trim();
										rzStu[i] = xhV;
									}
								}
							}
						//}
						for(int i=0;i<rzStu.length;i++){
							delSql[i]   = "delete from "+realTable+" where xh='"+rzStu[i]+"' and wjsj='"+wjsj+"'";
							//if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {
								inserSql[i] = "insert into "
									+ realTable
									+ "(xh,xn,xq,ssbh,wjsy,cljg,wjsj,bz,ch,wjlbdm,yjfs) "
									+ "values('" + rzStu[i] + "','" + xn
									+ "','" + xq + "','" + ssbh + "','"
									+ wjsy + "','" + cljg + "','" + wjsj
									+ "','" + bz + "','" + ch + "','"
									+ wjlbdm + "','" + yjfs + "')";

								String[] exesql = new String[2];
								exesql[0] = "delete from " + realTable
								+ " where xh='" + xh + "' and wjsj='"
								+ wjsj + "' ";

								exesql[1] = "insert into "
									+ realTable
									+ "(xh,xn,xq,ssbh,wjsy,cljg,wjsj,bz,ch,wjlbdm,yjfs) "
									+ "values('" + xh + "','" + xn + "','"
									+ xq + "','" + ssbh + "','" + wjsy
									+ "','" + cljg + "','" + wjsj + "','"
									+ bz + "','" + ch + "','" + wjlbdm
									+ "','" + yjfs + "')";

								int[] array = null;
								array = dao.runBatch(exesql);
								doFlag = dao.checkBatch(array);
//							} else {
//								inserSql[i] = "insert into "
//									+ realTable
//									+ "(xh,xn,xq,ssbh,wjsy,cljg,wjsj,bz,ch,wjlbdm) "
//									+ "values('" + rzStu[i] + "','" + xn
//									+ "','" + xq + "','" + ssbh + "','"
//									+ wjsy + "','" + cljg + "','" + wjsj
//									+ "','" + bz + "','" + ch + "','"
//									+ wjlbdm + "')";
//							}
						}
						String[] exesql = dao.unionArray(delSql, inserSql);
						int[] array = null;
						array = dao.runBatch(exesql);
						doFlag = dao.checkBatch(array);
					}else{
						String[] exesql = new String[2];
						exesql[0] = "delete from "+realTable+" where xh='"+xh+"' and wjsj='"+wjsj+"' ";
						//if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {
							exesql[1] = "insert into "
								+ realTable
								+ "(xh,xn,xq,ssbh,wjsy,cljg,wjsj,bz,ch,wjlbdm,yjfs) "
								+ "values('" + xh + "','" + xn + "','" + xq
								+ "','" + ssbh + "','" + wjsy + "','"
								+ cljg + "','" + wjsj + "','" + bz + "','"
								+ ch + "','" + wjlbdm + "','" + yjfs + "')";
//						} else {
//							exesql[1] = "insert into "
//								+ realTable
//								+ "(xh,xn,xq,ssbh,wjsy,cljg,wjsj,bz,ch,wjlbdm) "
//								+ "values('" + xh + "','" + xn + "','" + xq
//								+ "','" + ssbh + "','" + wjsy + "','"
//								+ cljg + "','" + wjsj + "','" + bz + "','"
//								+ ch + "','" + wjlbdm + "')";
//						}
						int[] array = null;
						array = dao.runBatch(exesql);
						doFlag = dao.checkBatch(array);
					}
					request.setAttribute("done", doFlag);
				}	
			} else if (realTable.equalsIgnoreCase("xsgzpxxxb")) {
				String pxxm = DealString.toGBK(request.getParameter("pxxmdm"));
				String pxjg = DealString.toGBK(request.getParameter("pxjg"));
				String pxkssj = DealString.toGBK(request.getParameter("pxkssj"));
				String pxjssj = DealString.toGBK(request.getParameter("pxjssj"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				boolean del = false;
				if ((pkValue == null) || (pkValue == "")) {
					sql = "delete xsgzpxxxb where xh=? and xn=? and xq=? and nd=?";
					del = dao.runUpdate(sql, new String[] { xh, xn, xq, nd });
				} else {
					sql = "delete xsgzpxxxb where " + pk + "= '" + pkValue
					+ "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into "
						+ realTable
						+ "(xh,xn,xq,nd,pxxmdm,pxjg,pxkssj,pxjssj,bz) values (?,?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] { xh, xn, xq, nd, pxxm,
							pxjg, pxkssj, pxjssj, bz });
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}

			} else if (realTable.equalsIgnoreCase("ssydxxb")) {
				String lddm = DealString.toGBK(dataSearchForm.getLddm());
				String qsh = DealString.toGBK(dataSearchForm.getQsh());
				String ydsj = DealString.toGBK(request.getParameter("ydsj"));
				String ydly = DealString.toGBK(request.getParameter("ydly"));
				String ydqssbh = DealString.toGBK(dataSearchForm.getYdqssbh());
				String ydqcwh = DealString.toGBK(dataSearchForm.getYdqcwh());
				String ydqrzsj = DealString.toGBK(dataSearchForm.getYdqrzsj());
				String ydhcwh = DealString.toGBK(request.getParameter("cwh"));
				String sfjh = request.getParameter("sfjh");//�Ƿ�λ������־
				String ydhssbh = dao.getOneRs("select ssbh from ssxxb where lddm=? and qsh=?",new String[]{lddm,qsh},"ssbh");
				String bz = DealString.toGBK(request.getParameter("bz"));
				boolean result = false;

				String xh1 = null;
				String rzrq2 = null;
				if(sfjh != null && sfjh.equals("yes")){
					sql = "select xh,rzrq from xszsxxb where ssbh=? and cwh=?";
					String[] info = dao.getOneRs(sql,new String[]{ydhssbh,ydhcwh},new String[]{"xh","rzrq"});
					xh1 = info[0];
					rzrq2 = info[1];
				}			
				if ((pkValue == null) || (pkValue == "")) {
					sql = "delete ssydxxb where xh=? and ydsj=?";
					result = dao.runUpdate(sql, new String[] { xh, ydsj });
					if(xh1 != null && !xh1.equals("")){
						sql = "delete ssydxxb where xh=? and ydsj=?";
						result = dao.runUpdate(sql, new String[] { xh1, ydsj });
					}
				} else {
					sql = "delete ssydxxb where " + pk + "= '" + pkValue + "'";
					result = dao.runUpdate(sql, new String[] {});
					if(xh1 != null && !xh1.equals("")){
						sql = "delete ssydxxb where " + pk + "= ?";
						result = dao.runUpdate(sql, new String[] {xh1+"||"+ydsj});
					}
				}
				if (result) {
					sql = "insert into "
						+ realTable
						+ "(xh,xn,xq,ydqssbh,ydhssbh,ydly,ydsj,bz,ydqcwh,ydhcwh,ydqrzsj,ydhrzsj) values (?,?,?,?,?,?,?,?,?,?,?,?)";
					String upzsxxsql = "update xszsxxb set ssbh=?,cwh=?,rzrq=? where xh=?";
					result=dao.runUpdate(upzsxxsql, new String[] { ydhssbh,ydhcwh,ydsj,xh });
					result=dao.runUpdate(sql, new String[] { xh, xn, xq, ydqssbh,
							ydhssbh, ydly, ydsj, bz, ydqcwh, ydhcwh, ydqrzsj,
							ydsj });
					String count = dao.getOneRs("select count(xh)cout from xszslsxxb where  xh||ssbh||cwh||rzrq||tfrq=? ",
							new String[]{xh+ydqssbh+ydqcwh+ydqrzsj+ydsj}, "cout");
					if(Integer.parseInt(count)==0){
						sql = "insert into xszslsxxb(xh,ssbh,bz,cwh,rzrq,tfrq,sfbz) values (?,?,?,?,?,?,?)";
						result=dao.runUpdate(sql, new String[] { xh,ydqssbh,bz,ydqcwh,ydqrzsj,ydsj,dao.getOneRs("select sfbz from ssxxb where ssbh=? ",new String[]{ydqssbh},"sfbz")});
					}
					if(xh1 != null && !xh1.equals("")){
						sql = "insert into "
							+ realTable
							+ "(xh,xn,xq,ydqssbh,ydhssbh,ydly,ydsj,bz,ydqcwh,ydhcwh,ydqrzsj,ydhrzsj) values (?,?,?,?,?,?,?,?,?,?,?,?)";
						upzsxxsql = "update xszsxxb set ssbh=?,cwh=?,rzrq=? where xh=?";
						result=dao.runUpdate(upzsxxsql, new String[] { ydqssbh, ydqcwh,
								ydsj, xh1 });
						result=dao.runUpdate(sql, new String[] { xh1, xn, xq, ydhssbh,
								ydqssbh, ydly, ydsj, bz, ydhcwh, ydqcwh, ydqrzsj,
								ydsj });
						String count1 = dao.getOneRs("select count(xh)cout from xszslsxxb where  xh||ssbh||cwh||rzrq||tfrq=? ",
								new String[]{xh1+ydhssbh+ydhcwh+rzrq2+ydsj}, "cout");
						if(Integer.parseInt(count1)==0){
							sql = "insert into xszslsxxb(xh,ssbh,bz,cwh,rzrq,tfrq,sfbz) values (?,?,?,?,?,?,?)";
							result=dao.runUpdate(sql, new String[] { xh1,ydhssbh,bz,ydhcwh,rzrq2,ydsj,dao.getOneRs("select sfbz from ssxxb where ssbh=? ",new String[]{ydqssbh},"sfbz")});
						}
					}
				}
				request.setAttribute("result", result);
			} else if (realTable.equalsIgnoreCase("bjjxhjb")) {
				String bjdm = DealString.toGBK(request.getParameter("bjdm"));
				String hjsj = request.getParameter("hjsj").replaceAll("-", "");
				String jxdm = DealString.toGBK(request.getParameter("jxdm"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				boolean del = false;
				if ((pkValue == null) || (pkValue == "")) {
					del = StandardOperation.delete(realTable, new String[] {
							"bjdm", "hjsj" }, new String[] { bjdm, hjsj },
							request);
				} else {
					del = StandardOperation.delete(realTable, pk, pkValue,
							request);
				}
				if (del) {
					StandardOperation.insert(realTable, new String[] { "bjdm",
							"hjsj", "jxdm", "nd", "xn", "xq", "bz" },
							new String[] { bjdm, hjsj, jxdm, nd, xn, xq, bz },
							request);
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
				dataSearchForm.setBjdm(bjdm);
			} else if (realTable.equalsIgnoreCase("yxjxhjb")) {
				String xydm = DealString.toGBK(request.getParameter("xydm"));
				String hjsj = request.getParameter("hjsj").replaceAll("-", "");
				String jxdm = DealString.toGBK(request.getParameter("jxdm"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				boolean del = false;
				if ((pkValue == null) || pkValue == "") {
					del = StandardOperation.delete(realTable, new String[] {
							"xydm", "hjsj" }, new String[] { xydm, hjsj },
							request);
				} else {
					del = StandardOperation.delete(realTable, pk, pkValue,
							request);
				}
				if (del) {
					StandardOperation.insert(realTable, new String[] { "xydm",
							"hjsj", "jxdm", "nd", "xn", "xq", "bz" },
							new String[] { xydm, hjsj, jxdm, nd, xn, xq, bz },
							request);
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
			} else if (realTable.equalsIgnoreCase("wmqspbb")) {
				boolean done=false;
				String lddm = dataSearchForm.getLddm();
				String qsh = DealString.toGBK(dataSearchForm.getQsh());
				String pysj = request.getParameter("pysj");
				String bz = DealString.toGBK(request.getParameter("bz"));
				String fzld = "";
				String qslb = dataSearchForm.getQslb();
				String qsjje = "0";
				String xxfw = DealString.toGBK(request.getParameter("xxfw"));

				if (qslb != null && !"".equalsIgnoreCase(qslb)) {// ������ҽ����
					qsjje = dao.getOneRs(
							"select qsjje from qslbdmb where lbdm = ?",
							new String[] { qslb }, "qsjje");
				}
				//String userDep = session.getAttribute("userDep").toString();
//				String userType = session.getAttribute("userType").toString();
				String userName = request.getSession().getAttribute("userName").toString();
				//��Ԣ����Ա�ж�begin			
//				String lddmStr = gyglDao.getLddmxXx(userName);
//				String isGyFdy = "no";
//				if(!Base.isNull(lddmStr)){
//				lddm = lddmStr; 					
//				isGyFdy = "yes";					
//				}
//				request.setAttribute("isGyFdy","");
				//��Ԣ����Ա�ж�end	

				if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)
						||xxdm.equalsIgnoreCase(Globals.XXDM_NCDXKJXY)) {
					// ֵ����Ա��¼��Ĭ����ֵ��¥��
					sql = "select szlddm from zbrydmb where zbrydm = ?";
					fzld = dao.getOneRs(sql, new String[] { userName },
					"szlddm");
					if (!("".equalsIgnoreCase(fzld) || fzld == null)) {
						lddm = fzld;
					} 
				}
				String ssbh = dao.getOneRs("select ssbh from ssxxb where lddm=? and qsh=?",new String[]{lddm,qsh},"ssbh");

				sql = " select ssbh from ssxxb where ssbh = ? ";
				String tmp = dao.getOneRs(sql, new String[] { ssbh }, "ssbh");
				if (tmp != null && !tmp.equalsIgnoreCase("")) {
					boolean del = StandardOperation.delete(realTable,
							new String[] { "ssbh", "xn", "xq" }, new String[] {
							ssbh, xn, xq }, request);
					if (del) {
						done = StandardOperation.insert(realTable, new String[] {
								"xn", "xq", "bz", "pysj", "ssbh", "qslb",
								"qsjje","xxfw" }, new String[] { xn, xq, bz, pysj,
								ssbh, qslb, qsjje,xxfw }, request);
						if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
							sql = "{call AUTOQSPYJF(?,?,?)}";
							dao.runProcuder(sql, new String[] { xn, xq, ssbh });
						}
					}
				}
				request.setAttribute("done",done);
			} else if (realTable.equalsIgnoreCase("lpxxb")) {
				String bxgsdm = request.getParameter("bxgsdm");
				String lpje = request.getParameter("lpje");
				String slrq = request.getParameter("slrq");
				String dzsj = DealString.toGBK(request.getParameter("dzsj"));
				String lpyy = DealString.toGBK(request.getParameter("lpyy"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				String hffy = DealString.toGBK(request.getParameter("hffy"));
				String bxxzdm = request.getParameter("bxxzdm");
				boolean del = false;				
				if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
					//�Ϻ����̼�����ѧ
					del = StandardOperation.delete("lpxxb",
							"xh||nd", xh.trim() + nd.trim() , request);
				}else{
					if ((pkValue == null) || (pkValue == "")) {
						del = StandardOperation.delete("lpxxb",
								"xh||bxgsdm||nd||slrq", xh.trim() + bxgsdm.trim()
								+ nd.trim() + slrq.trim(), request);
					} else {
						del = StandardOperation.delete("lpxxb", pk, pkValue,request);
					}
				}
				if (del) {
					if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
						String xxshyj = DealString.toGBK(request.getParameter("xxshyj"));
//						StandardOperation.insert(realTable, new String[] { "xh",
//						"bxgsdm", "nd", "xn", "xq", "slrq", "dzsj", "lpje",
//						"lpyy", "bz" ,"hffy","bxxzdm","xxshyj"}, new String[] { xh, bxgsdm, nd, xn,
//						xq, slrq, dzsj, lpje, lpyy, bz ,hffy,bxxzdm,xxshyj}, request);
						//��������Ϣ����
						int num = Integer.parseInt(request.getParameter("bxxzNum"));
						for(int i=0;i<num; i++){
							hffy = request.getParameter("hffy"+i);
							bxxzdm = request.getParameter("bxxzdm"+i);	
							lpje = request.getParameter("lpje"+i);
							if(bxxzdm!=null && !"".equalsIgnoreCase(bxxzdm)){
								StandardOperation.insert(realTable, new String[] { "xh",
										"bxgsdm", "nd", "xn", "xq", "slrq", "dzsj", "lpje",
										"lpyy", "bz" ,"hffy","bxxzdm","xxshyj"}, new String[] { xh, bxgsdm, nd, xn,
										xq, slrq, dzsj, lpje, lpyy, bz ,hffy,bxxzdm,xxshyj}, request);								
							}
						}
					}else{
						StandardOperation.insert(realTable, new String[] { "xh",
								"bxgsdm", "nd", "xn", "xq", "slrq", "dzsj", "lpje",
								"lpyy", "bz","bxxzdm", "hffy" }, new String[] { xh, bxgsdm, nd, xn,
								xq, slrq, dzsj, lpje, lpyy, bz, bxxzdm, hffy}, request);
					}
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}
				List bxxzList = dao.getList("select distinct bxxzdm,bxxzmc from bxxzb", new String[]{}, new String[]{"bxxzdm","bxxzmc"});
				request.setAttribute("bxxzList",bxxzList);
			} else if (realTable.equalsIgnoreCase("kkqkb")) {
				String kkrq = request.getParameter("kkrq");
				String kkkc = request.getParameter("kkkc");
				String kkjs = request.getParameter("kkjs");
				String wzx = DealString.toGBK(request.getParameter("wzx"));
				String skqk = DealString.toGBK(request.getParameter("skqk"));
				String zdl = DealString.toGBK(request.getParameter("zdl"));
				String cljg = DealString.toGBK(request.getParameter("cljg"));
				boolean del = false;
				if ((pkValue == null) || (pkValue == "")) {
					sql = "delete from kkqkb where xh=? and kkrq=? and kkkc=?";
					del = dao.runUpdate(sql, new String[] { xh, kkrq, kkkc });
				} else {
					sql = "delete from kkqkb where " + pk + "='" + pkValue
					+ "'";
					del = dao.runUpdate(sql, new String[] {});
				}
				if (del) {
					sql = "insert into "
						+ realTable
						+ "(xh,xn,xq,nd,kkrq,kkkc,kkjs,cljg,zdl,skqk,wzx) values (?,?,?,?,?,?,?,?,?,?,?)";
					dao.runUpdate(sql, new String[] { xh, xn, xq, nd, kkrq,
							kkkc, kkjs, cljg , zdl, skqk, wzx});
				} else {
					dataSearchForm.setErrMsg("sdf");
					return mapping.findForward("false");
				}

			} else if (realTable.equalsIgnoreCase("gdnz_lpxxb_bx")
					|| realTable.equalsIgnoreCase("gdnz_lpxxb_sh")) {// �㶫Ů��ְҵ����������Ϣ
				String bxgsdm = request.getParameter("bxgsdm");
				String bxnx = request.getParameter("bxqx");
				String bxdc = request.getParameter("bxdc");
				String bf = request.getParameter("bf");
				String jzyy = DealString.toGBK(request.getParameter("jzyy"));
				String lpje = request.getParameter("lpje");
				String zykssj = request.getParameter("zykssj");
				String zyjzsj = request.getParameter("zyjzsj");
				String spsj = request.getParameter("spsj");
				String spje = request.getParameter("spje");
				String sqlpsj = request.getParameter("sqlpsj");
				String clqd = DealString.toGBK(request.getParameter("clqd"));
				String bpyy = DealString.toGBK(request.getParameter("bpyy"));
				String sqlpyy = DealString.toGBK(request.getParameter("sqlpyy"));
				String bz = DealString.toGBK(request.getParameter("bz"));
				String bdhm = DealString.toGBK(request.getParameter("bdh"));
				String sfxfzrx = realTable.equalsIgnoreCase("gdnz_lpxxb_sh") ? "1": "0";

				boolean del = false;
				if ((pkValue == null) || (pkValue == "")) {
					del = StandardOperation.delete("gdnz_lpxxb",
							"xh||bxgsdm||nd||sfxfzrx", xh.trim()
							+ bxgsdm.trim() + nd.trim()
							+ sfxfzrx.trim(), request);
				} else {
					del = StandardOperation.delete("gdnz_lpxxb", pk, pkValue,
							request);
				}
				if (del) {
					StandardOperation.insert("gdnz_lpxxb", new String[] { "xh",
							"bxgsdm", "nd", "xn", "xq", "sfxfzrx", "bxnx",
							"bxdc", "bf", "sqlpsj", "sqlpyy", "jzyy", "zykssj",
							"zyjzsj", "clqd", "spsj", "spje", "lpje", "bpyy",
							"bz", "bdhm" }, new String[] { xh, bxgsdm, nd, xn,
							xq, sfxfzrx, bxnx, bxdc, bf, sqlpsj, sqlpyy, jzyy,
							zykssj, zyjzsj, clqd, spsj, spje, lpje, bpyy, bz,
							bdhm }, request);
				} else {
					dataSearchForm.setErrMsg("����ʧ�ܣ�");
					return mapping.findForward("false");
				}
			}
			// -----------------2010/6/25 edit by luojw----------------------
			//newFwd = null;
		} else {
			// TODO ����ҳ��
			
			if ("view".equalsIgnoreCase(doType) && "view_wjcf".equalsIgnoreCase(tableName)) {
				request.setAttribute("wjcfWritable", "no");
			}
			
			if(xxdm.equalsIgnoreCase(Globals.XXDM_CQKJXY) && tableName.equalsIgnoreCase("view_xsgwxx")){
				//����Ƽ�
				String xh = request.getParameter("xh");				
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
			if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm) && realTable.equalsIgnoreCase("xsgwxxb")){
				//��ѯ���ݴ�ѧѧ����λ��ϸ��Ϣ
				return new ActionForward("/qgzxLogic.do?method=showGzdxXsgwxxb");
			}
			if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm) && realTable.equalsIgnoreCase("xsgwxxb")){
				//��ѯ�й����ʴ�ѧѧ����λ��ϸ��Ϣ
				return new ActionForward("/qgzxLogic.do?method=showZgdzdxXsgwxxb");
			}
			//********2010.4.7 qph begin*********//
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX) && realTable.equalsIgnoreCase("xlytqkb")){
				sql = "select a.xm,a.xb,a.xymc,a.xydm,a.bjmc,a.ssbh,x.* from " + tableName+" a";
			}else{
				sql = "select * from " + tableName+" t";
			}
			//********2010.4.7 qph end*********//
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX) && realTable.equalsIgnoreCase("xlytqkb")){
				sql += (",xlytqkb x");
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)
					&& tableName.equalsIgnoreCase("view_xsgwxx")) {
				sql = "select a.*,(select zzmmmc from view_stu_details b where a.xh=b.xh) zzmmmc,(select mzmc from view_stu_details b where a.xh=b.xh) mzmc,(select jtszd jtdz from view_xsfzxx b where a.xh=b.xh) jtdz,(select yb from view_xsfzxx b where a.xh=b.xh)jtyb,(select lxdh1 from view_xsfzxx b where a.xh=b.xh) jtdh from "
					+ tableName + " a ";
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC) && realTable.equalsIgnoreCase("lpxxb")){
				sql = "select xh,max(xm)xm,max(xb)xb,max(nj)nj,max(zymc)zymc,max(xymc)xymc,max(bjmc)bjmc,nd,xn,xq,max(bxgsmc),bxgsdm,max(slrq)slrq,max(dzsj)dzsj,sum(lpje)lpje,sum(hffy)hffy from " + tableName ;
				sql += " where " + pk + "='" + pkValue + "'";	
				sql += " group by xh,nd,xn,xq,bxgsdm";
			}
			String[] record = null;
			String[] colList = dao.getColumnName(sql);

			if(!(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC) && realTable.equalsIgnoreCase("lpxxb"))){
				if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX) && realTable.equalsIgnoreCase("xlytqkb")){
					sql += (" where a." + pk + "='" + pkValue + "'");
				}else{
					sql += (" where t." + pk + "='" + pkValue + "'");
				}
			}
			if((xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX) && realTable.equalsIgnoreCase("xlytqkb"))){
				sql += (" and a.yt_id=x.yt_id ");
			}
			String rec = dao.getStringToSplit(sql, new String[] {}, colList);
			if (rec.equalsIgnoreCase("Error")) {
				record = new String[colList.length];
			} else {
				String[] tmp = rec.split("!!SplitSignOne!!");
				if (tmp.length != 2) {
					if ((realTable.equalsIgnoreCase("sxhbb") || tableName.equalsIgnoreCase("view_gdnz_lpxx") || realTable.equalsIgnoreCase("lpxxb"))
							&& tmp.length > 2) {
						record = tmp[1].split("!!SplitSignTwo!!");
					} else {
						record = new String[colList.length + 1];
					}
				} else {
					record = tmp[1].split("!!SplitSignTwo!!");
				}
			}
			if (record.length != colList.length + 1) {
				record = new String[colList.length + 1];
			}
			HashMap<String, String> map = new HashMap<String, String>();

			for (int i = 0; i < colList.length; i++) {
				map.put(colList[i].toLowerCase(), Base.isNull(record[i + 1])?"":record[i + 1].trim());
			}
			if(Globals.XXDM_ZJLG.equals(xxdm)){
				String xh = map.get("xh");
//				System.out.println(xh);
				if(StringUtils.isNull(xh)){
					map.put("sfkns","");
				}else{
					map.put("sfkns", DAO.getInstance().isKns(xh)?"��":"��");
				}
			}
			//���Ž���ѧ������б�
			if (realTable.equalsIgnoreCase("dyxxb")
					|| realTable.equalsIgnoreCase("ybdyxxb")
					|| realTable.equalsIgnoreCase("rdjjfzxxb")) {
				String mySql = "select xsccdm, xsccmc from dtjs_xsccb";
				List<HashMap<String, String>> temp = dao.getList(mySql,new String[]{},new String[]{"xsccdm","xsccmc"});
				request.setAttribute("xsccList",temp );// ����dtjs_xsccb�б�
				request.setAttribute("xxdm",xxdm );// ����dtjs_xsccb�б�
			} 
			if (realTable.equalsIgnoreCase("xspxxxb")) {
				sql = "select pxxmdm xmdm,pxxmmc xmmc from pxxmdmb";
				List xmdmList = dao.getList(sql, new String[] {}, new String[] {
						"xmdm", "xmmc" });
				request.setAttribute("xmdmList", xmdmList);
			} else if (realTable.equalsIgnoreCase("rdjjfzxxb")
					|| realTable.equalsIgnoreCase("ybdyxxb")
					|| realTable.equalsIgnoreCase("xxb")
					|| realTable.equalsIgnoreCase("jxjgb")) {
				request.setAttribute("ynList", dao.getChkList(2));
			} else if (realTable.equalsIgnoreCase("knsshhdxxb")) {
				sql = "select shhdxzdm,shhdxzmc from shhdxzdmb";
				List shhdxzList = dao.getList(sql, new String[] {},
						new String[] { "shhdxzdm", "shhdxzmc" });
				request.setAttribute("shhdxzList", shhdxzList);
			} else if (realTable.equalsIgnoreCase("jszhkpb")) {
				request.setAttribute("scoreList", dao.getScore(5));
			} else if (realTable.equalsIgnoreCase("xsjxhjb")) {

				sql = "select jxdm,jxmc from jxjxdmb";
				List xmdmList = dao.getList(sql, new String[] {}, new String[] {
						"jxdm", "jxmc" });
				request.setAttribute("jxList", xmdmList);
			} else if (realTable.equalsIgnoreCase("zhszcp")) {
				request.setAttribute("scoreList", dao.getScore(5));
				if (xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)) {//�㶫����ѧԺ
					request.setAttribute("isgdby", "yes");
				}
				if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {//������
					String[] cjAndPm = dao.getOneRs("select zhszcpzf,zhpm,kcjqpfj,jpfpm,dcj,dcjpm from (select xh,xn,xq,nd,zhszcpzf, (dense_rank() over (partition by bjdm order by to_number(zhszcpzf) desc nulls last)) zhpm,kcjqpfj, (dense_rank() over (partition by zydm order by to_number(kcjqpfj) desc nulls last)) jpfpm,trunc((to_number(ddcj)+to_number(xwcj)+to_number(shqcj)),2) dcj,(dense_rank() over (partition by bjdm order by to_number(trunc((to_number(ddcj)+to_number(xwcj)+to_number(shqcj)),2)) desc nulls last)) dcjpm from view_zhszcp) where xn||xh=?", new String[]{pkValue}, new String[]{"zhszcpzf", "zhpm", "dcj", "dcjpm", "kcjqpfj", "jpfpm"});
					if (cjAndPm != null && cjAndPm.length == 6) {
						request.setAttribute("zhszcpzf", cjAndPm[0]);
						request.setAttribute("zhpm", cjAndPm[1]);
						request.setAttribute("dcj", cjAndPm[2]);
						request.setAttribute("dcjpm", cjAndPm[3]);
						request.setAttribute("kcjqpfj", cjAndPm[4]);
						request.setAttribute("jpfpm", cjAndPm[5]);
					}

					request.setAttribute("shownbng", "yes");
				}
				if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {
					String[] tmpList = new String[] { "zgdy", "zgybdy", "xjbj",
							"wmss", "xsgb1", "xsgb2", "xsgb3", "xsgb4", "wysp",
							"jsjsp", "xjjxj1", "xjjxj2", "xjjxj3", "gjjl1",
							"gjjl2", "gjjl3", "sjjl1", "sjjl2", "sjjl3",
							"xjgr1", "xjgr2", "zf" }; 
					String[] pfList = dao.getOneRs("select * from sjdxspfxzb where xh=(select xh from view_zhszcp where xn||xh=?)", new String[]{pkValue}, tmpList);
					String[] xqS = dao.getOneRs("select xh,xz,nj,((select to_number(a.dqnd) from xtszb a)-to_number(nj))*2 zxq,(select b.dqxq from xtszb b) dqxq from view_zhszcp where xn||xh = ?", new String[]{pkValue}, new String[]{"zxq", "dqxq"});
					if (xqS != null && xqS.length == 2) {
						int iXq = 0;
						if (!StringUtils.isNull(xqS[1]) && StringUtils.isEqual(xqS[1], "02")) {
							iXq = StringUtils.isNull(xqS[0]) ? 0 : (Integer.parseInt(xqS[0]) + 1);
						}
						if (!StringUtils.isNull(xqS[1]) && StringUtils.isEqual(xqS[1], "01")) {
							iXq = StringUtils.isNull(xqS[0]) ? 0 : Integer.parseInt(xqS[0]);
						}
						map.put("zxq", iXq + "");//��ȡѧ���ۼ�ѧ����
					}
					if (pfList != null && pfList.length == 22) {
						for (int i=0;i<tmpList.length;i++) {
							map.put(tmpList[i], pfList[i]);
						}
					}
				}
			} else if (realTable.equalsIgnoreCase("nsepxxb")) {
				sql = "select  nsepxmdm,nsepxmmc from nsepxmdmb";
				List nsepList = dao.getList(sql, new String[] {}, new String[] {
						"nsepxmdm", "nsepxmmc" });
				sql = "select nsepjbdm,nsepjbmc from nsepjbdmb";
				List nsepjbList = dao.getList(sql, new String[] {},
						new String[] { "nsepjbdm", "nsepjbmc" });
				request.setAttribute("nsepjbList", nsepjbList);
				request.setAttribute("nsepList", nsepList);
			} else if (realTable.equalsIgnoreCase("xlcsjgb")) {
				String csxmdm = request.getParameter("csxmdm");
				String xh = request.getParameter("xh");
				String xm = DealString.toGBK(request.getParameter("xm"));
				String xb = DealString.toGBK(request.getParameter("xb"));
				String nj = request.getParameter("nj");
				String xymc = DealString.toGBK(request.getParameter("xymc"));
				String zymc = DealString.toGBK(request.getParameter("zymc"));
				String bjmc = DealString.toGBK(request.getParameter("bjmc"));
				sql = "select xlcsxmdm,xlcsxmmc from xlcsxmdmb";
				List csxmList = dao.getList(sql, new String[] {}, new String[] {
						"xlcsxmdm", "xlcsxmmc" });
				SimpleDateFormat simp = new SimpleDateFormat("yyyy-MM-dd");
				String csnj = simp.format(new Date());
				request.setAttribute("csxmList", csxmList);
				request.setAttribute("csnj", csnj);
				if ((csxmdm != null) && !csxmdm.equals("")) {
					sql = "select xlcsjgdm,xlcsjgmc from xlcsjgdmb where xlcsxmdm='"
						+ csxmdm + "'";
					List csjgList = dao.getList(sql, new String[] {},
							new String[] { "xlcsjgdm", "xlcsjgmc" });
					request.setAttribute("csjgList", csjgList);
					map.put("xh", xh);
					map.put("csxmdm", csxmdm);
					map.put("xm", xm);
					map.put("xb", xb);
					map.put("nj", nj);
					map.put("xymc", xymc);
					map.put("zymc", zymc);
					map.put("bjmc", bjmc);
				} else if ((map.get("csxmdm") != null)
						&& !map.get("csxmdm").equals("")) {
					sql = "select xlcsjgdm,xlcsjgmc from xlcsjgdmb where xlcsxmdm='"
						+ map.get("csxmdm") + "'";
					List csjgList = dao.getList(sql, new String[] {},
							new String[] { "xlcsjgdm", "xlcsjgmc" });
					request.setAttribute("csjgList", csjgList);
				}
			} else if (realTable.equalsIgnoreCase("zxdk_htxx")) {
			} else if (realTable.equalsIgnoreCase("xytbgxxsxxb")) {
				sql = "select tbgxxslbdm,tbgxxslbmc from tbgxxslbdmb";
				List tbgxxslbdmList = dao.getList(sql, new String[] {},
						new String[] { "tbgxxslbdm", "tbgxxslbmc" });
				request.setAttribute("tbgxxslbdmList", tbgxxslbdmList);
			} else if (realTable.equalsIgnoreCase("wjcfb")
					|| realTable.equalsIgnoreCase("wjcflsb")) {
				List cflbList = null;
				if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
					request.setAttribute("isCSMZ","yes");
				}
				if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
					sql = "select cflbdm,cflbmc from cflbdmb where cflbmc = ? or cflbmc = ? or cflbmc = ? or cflbmc = ? or cflbmc = ?";
					cflbList = dao.getList(sql, new String[] {"����", "���ؾ���", "�ǹ�", "��У�쿴", "����ѧ��"}, new String[] {
							"cflbdm", "cflbmc" });
				}else{
					sql = "select cflbdm,cflbmc from cflbdmb";
					cflbList = dao.getList(sql, new String[] {}, new String[] {
							"cflbdm", "cflbmc" });
				}
				request.setAttribute("cflbList", cflbList);
				sql = "select cfyydm,cfyymc from cfyydmb";
				List cfyyList = dao.getList(sql, new String[] {}, new String[] {
						"cfyydm", "cfyymc" });
				
				request.setAttribute("cfyyList", cfyyList);
				request.setAttribute("njList", dao.getNjList());
				String clwh = StandardOperation.getWjcfClwh(xxdm);
				if ("".equalsIgnoreCase(clwh) || clwh == null) {
					request.setAttribute("clwh", "");
				} else {
					request.setAttribute("clwh", clwh);
				}
				if (session == null) {
					request.setAttribute("errMsg", "sadfsdf");
					return mapping.findForward("false");
				}
				String userType = session.getAttribute("userType").toString();
				if ("xx".equalsIgnoreCase(userType)
						|| "admin".equalsIgnoreCase(userType)) {
					request.setAttribute("writeAble", "yes");
				} else {
					request.setAttribute("writeAble", "no");
				}
				
				//����ѧУ������У�쿴�Ĳ���ʾ���ʱ��,�ĺ�
				if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm) && "��У�쿴".equalsIgnoreCase(map.get("cflbmc"))) {
					request.setAttribute("zjlgLxck", "yes");
				} else if (Globals.XXDM_XMLGXY.equalsIgnoreCase(xxdm) && "��У�쿴".equalsIgnoreCase(map.get("cflbmc"))) {
					request.setAttribute("xmlgLxck", "yes");
				} else if (Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm) && "��У�쿴".equalsIgnoreCase(map.get("cflbmc"))) {
					request.setAttribute("lxck", "yes");
				}

			} else if (realTable.equalsIgnoreCase("xsbxb")) {
				sql = "select bxgsdm,bxgsmc from bxgsdmb";
				List tbgsdmList = dao.getList(sql, new String[] {},
						new String[] { "bxgsdm", "bxgsmc" });
				request.setAttribute("tbgsdmList", tbgsdmList);
				sql = "select bxxzdm,bxxzmc from bxxzb";
				List tbxzdmList = dao.getList(sql, new String[] {},
						new String[] { "bxxzdm", "bxxzmc" });
				request.setAttribute("tbxzdmList", tbxzdmList);
				if (xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)) {
					List bxdcList = dao.getList( "select dcdm,dcmc from gdnzzy_bxdcdmb order by dcdm",
							new String[] {}, new String[] { "dcdm", "dcmc" });
					request.setAttribute("bxdcList", bxdcList);
				}
			}

			else if (realTable.equalsIgnoreCase("xsjxjb")) {
				String userType = "";
				if (session == null) {
					request.setAttribute("errMsg", "sadfsdf");
					return mapping.findForward("false");
				}
				userType = session.getAttribute("userOnLine").toString();

				String jxjdm = request.getParameter("xmdm");
				if (jxjdm != null) {
					sql = "select jxjlb,jlje from jxjdmb where jxjdm=?";
					String[] jxjInfo = dao.getOneRs(sql, new String[] { jxjdm }, new String[] { "jxjlb", "jlje" });
					if (jxjInfo != null) {
						map.put("jxjlb", jxjInfo[0]);
						map.put("jlje", jxjInfo[1]);
					}
				}
				sql = "select * from jxjdmb";
				List jxjList = dao.getList(sql, new String[] {}, new String[] {"jxjdm", "jxjmc" });
				request.setAttribute("jxjList", jxjList);
				sql = "select jxjsqxn,jxjsqnd from xtszb where rownum=1";
				String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {"jxjsqxn", "jxjsqnd" });
				map.put("xn", tmp[0]);
				map.put("nd", tmp[1]);
				map.put("xmdm", jxjdm);
				map.put("stuExists", "yes");
				map.put("userType", userType);
				request.setAttribute("oldjxjdm", map.get("jxjdm"));
			} else if (realTable.equalsIgnoreCase("xshsxfb")) {
				request.setAttribute("yfList", dao.getYfList());
			} else if (realTable.equalsIgnoreCase("zxdk_xsjbxx")) {
				String kh = dao.getOneRs("select kh from view_stu_details where xh=?", new String[] { pkValue }, "kh");
				map.put("kh", kh);
			} else if (realTable.equalsIgnoreCase("xsrychb")) {
				HashMap<String, String> stuxx = dao.getMapNotOut("select zysj,brjl,hjqk,drzw,byzx,(select b.wysp from xsfzxxb b where a.xh=b.xh) wysp from xsrychxxb a where xh=?", new String[]{map.get("xh")});
				map.putAll(stuxx);
				String userType = "";
				if (session == null) {
					request.setAttribute("errMsg", "sadfsdf");
					return mapping.findForward("false");
				}
				userType = session.getAttribute("userOnLine").toString();

				sql = "select * from rychdmb";
				List rychList = dao.getList(sql, new String[] {}, new String[] {"rychdm", "rychmc" });
				request.setAttribute("rychList", rychList);
				sql = "select jxjsqxn,jxjsqnd from xtszb where rownum=1";
				String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {"jxjsqxn", "jxjsqnd" });
				map.put("xn", tmp[0]);
				map.put("nd", tmp[1]);
				map.put("stuExists", "yes");
				map.put("userType", userType);
				dataSearchForm.setXmdm(map.get("rychdm"));
			} else if (realTable.equalsIgnoreCase("gwxxb")) {// �ڹ���ѧ��𷢷���д�굥				
				String cjffsj = dao.getOneRs("select cjffsj from gwsqsjb",new String[]{},"cjffsj");
				//��ȡ��𷢷���Ⱥ��·�
				sql = "select xn ,nd, xq, jfglkg from gwsqsjb where rownum=1";
				map.putAll(dao.getMap(sql, new String[] {}, new String[] { "xn", "nd", "xq", "jfglkg"}));

				String[] tmp = null;
				sql = "select to_char(sysdate,'yyyy-mm-dd') time from dual";
				tmp = dao.getOneRs(sql, new String[] {}, new String[] { "time" });
				map.put("yf", tmp[0].substring(5, 7));
				if(cjffsj != null && !"".equalsIgnoreCase(cjffsj) && cjffsj.length()>5){
					map.put("nd",cjffsj.substring(0,4));
					map.put("yf", cjffsj.substring(4,6));
				}

				sql = "select sqdw,gznr,gwxzmc,decode(jcfs,'h','Ԫ/Сʱ','d','Ԫ/��','w','Ԫ/��','m','Ԫ/��','n','Ԫ/־Ը����') jcfs,spbcbz,decode(jcfs,'h','Сʱ','d','��','w','��','m','��') gzsjdw, gwsbsj, gwdm, yrdwmc, gwxz,xymc from view_gwxx where "
					+ pk + "='" + pkValue + "'";
				tmp = dao.getOneRs(sql, new String[] {}, new String[] { "sqdw", "gznr", "gwxzmc", "jcfs", "spbcbz", "gzsjdw", "gwsbsj",
						"gwdm", "yrdwmc", "gwxz", "xymc" });
				map.put("sqdw", tmp[0]);
				map.put("gznr", tmp[1]);
				map.put("gwxzmc", tmp[2]);
				map.put("jcfs", tmp[3]);
				map.put("jybcbz", tmp[4]);
				map.put("gzsjdw", tmp[5]);
				map.put("gwsbsj", tmp[6]);
				map.put("gwdm", tmp[7]);
				map.put("yrdwmc", tmp[8]);
				map.put("gwxz", tmp[9]);
				map.put("xymc", tmp[10]);
				// ʣ�ྭ��=�������+�������-���Ž��(�������+�������=hbje)
				sql = "select nvl((select sum(hbje) from jfhbb where nd=? and yrdwdm=? and (gwxzdm=? or gwxzdm is null) ),0)-nvl((select sum(cjje) from view_xscjff where nd=? and sqdw=? and (gwxzmc=? or gwxzmc is null)),0) syjf from dual";
				tmp = dao.getOneRs(sql, new String[] { map.get("nd"),
						map.get("sqdw"), map.get("gwxz"),  map.get("nd"),
						map.get("sqdw"), map.get("gwxzmc") }, new String[] { "syjf" });
				if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){
					sql = "select nvl((select sum(hbje) from jfhbb where nd=? and yrdwdm=?),0)-nvl((select sum(cjje) from view_xscjff where nd=? and sqdw=? and yrdwmc=?),0) syjf from dual";
					tmp = dao.getOneRs(sql, new String[] { map.get("nd"),
							map.get("sqdw"), map.get("nd"),
							map.get("sqdw"), map.get("yrdwmc") }, new String[] { "syjf" });
				}

				map.put("syjf", tmp[0]);
				if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
					//��������
					String jcbz = dao.getOneRs("select mxsbc from gwsqsjb where rownum=1", new String[] {}, "mxsbc");
					map.put("jybcbz", jcbz);
					request.setAttribute("bjlh", "yes");
				}
				request.setAttribute("workInfo", map);

//				sql = "select * from xscjffb a,xsgwxxb b where a.xh=b.xh and a.gwdm=b.gwdm and a.sqsj=b.gwsbsj and b.gwdm||b.gwsbsj='"
//					+ pkValue
//					+ "' and a.nd='"
//					+ map.get("nd")
//					+ "' and a.yf='" + map.get("yf") + "'";

//				List li_lrh = dao.getListNotOut(sql, new String[] {});
//				if (null != li_lrh && 0 != li_lrh.size()) {
//					request.setAttribute("doflag", "modi");
//				} else {
//					request.setAttribute("doflag", "add");
//				}
				if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
					//��������
					//��ѯ�ø�λ�ڸڵ���Ҫ���ų���ѧ��
					sql = "select a.*,b.bz,(case when c.sfsh='ͨ��' then c.xghkh else c.xgqkh end) kh,d.yje cjje,d.ysj gzsj  from (select rownum,xh,xm,bjmc,gwsbsj,gwdm from view_xsgwxx where gwdm||gwsbsj=(select gwdm||gwsbsj from view_gwxx where "
						+ pk
						+ "='"
						+ pkValue
						+ "') and xyyj='ͨ��' and xxyj='ͨ��') a left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
						+ " and b.nd='"
						+ map.get("nd")
						+ "' and b.yf='"
						+ map.get("yf")
						+ "' left join khxgsqb c on c.xh=a.xh��left join (select xh,ysj,yje,time from xskhyb where sftj = 'yes') d on a.xh = d.xh and d.time = '"
						+ map.get("nd") + map.get("yf") + "' ";

				}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
					//�й����ʴ�ѧ
					sql = "select a.*,b.gzsj,b.cjje,b.bz,b.khqk,b.xxsh ,(case when c.sfsh='ͨ��' then c.xghkh else c.xgqkh end) kh  from (select rownum,xh,xm,bjmc,gwsbsj,gwdm from view_xsgwxx where gwdm||gwsbsj=(select gwdm||gwsbsj from view_gwxx where "
						+ pk
						+ "='"
						+ pkValue
						+ "') and xyyj='ͨ��') a left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
						+ " and b.nd='"
						+ map.get("nd")
						+ "' and b.yf='"
						+ map.get("yf")
						+ "' left join khxgsqb c on c.xh=b.xh";	
				}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){
					//�㽭����ְҵ����ѧԺ
					String cjffY = "";
					String cjffM = "";
					if(cjffsj != null && cjffsj.length()==6){
						cjffY = cjffsj.substring(0,4);
						cjffM = cjffsj.substring(4,6);
					}else{
						cjffY = Base.currNd;
						cjffM = GetTime.getSystemTime().substring(5,7);
					}
					sql = "select a.*,b.gzsj,b.cjje,b.bz,b.khqk,b.xxsh ,(case when c.sfsh='ͨ��' then c.xghkh else c.xgqkh end) kh  from (select rownum,xh,xm,bjmc,gwsbsj,gwdm from view_xsgwxx where gwdm||gwsbsj=(select gwdm||gwsbsj from view_gwxx where "
						+ pk
						+ "='"
						+ pkValue
						+ "') and xxyj='ͨ��') a left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
						+ " and b.nd='"
						+ cjffY
						+ "' and b.yf='"
						+ cjffM
						+ "' left join khxgsqb c on c.xh=b.xh";
					request.setAttribute("cjffY",cjffY);
					request.setAttribute("cjffM",cjffM);
				}else if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
					//������ѧԺ
					sql = "select a.*,b.gzsj,b.cjje,b.bz,b.khqk,b.xxsh ,(case when c.sfsh='ͨ��' then c.xghkh else c.xgqkh end) kh  from (select rownum,xh,xm,bjmc,gwsbsj,gwdm from view_xsgwxx where gwdm||gwsbsj=(select gwdm||gwsbsj from view_gwxx where "
						+ pk
						+ "='"
						+ pkValue
						+ "') and xyyj='ͨ��' and xxyj='ͨ��') a left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
						+ " and b.nd='"
						+ map.get("nd")
						+ "' and b.yf='"
						+ map.get("yf")
						+ "' left join khxgsqb c on c.xh=b.xh";	
				}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)){
					//�㽭����ѧ
					sql = "select a.*,decode(b.gzsj,'',(select ygzsj from view_qgzx_xsgzkhb c where a.gwdm=c.gwdm and a.gwsbsj=c.gwsbsj and a.xh=c.xh and c.nd='"+map.get("nd")+"' and c.yf='"+map.get("yf")+"'),b.gzsj)gzsj,decode(b.cjje,'',(select ffcjje from view_qgzx_xsgzkhb c where a.gwdm=c.gwdm and a.gwsbsj=c.gwsbsj and a.xh=c.xh and c.nd='"+map.get("nd")+"' and c.yf='"+map.get("yf")+"'),b.cjje)cjje,b.bz,b.khqk,b.xxsh ,(case when c.sfsh='ͨ��' then c.xghkh else c.xgqkh end) kh  from (select rownum,xh,xm,bjmc,gwsbsj,gwdm from view_xsgwxx where gwdm||gwsbsj=(select gwdm||gwsbsj from view_gwxx where "
						+ pk
						+ "='"
						+ pkValue
						+ "') and xyyj='ͨ��' and xxyj='ͨ��') a left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
						+ " and b.nd='"
						+ map.get("nd")
						+ "' and b.yf='"
						+ map.get("yf")
						+ "' left join khxgsqb c on c.xh=b.xh";
				}else if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
					//���ݴ�ѧ
					sql = "select a.*,b.gzsj,(select ygzsj from view_qgzx_xsgzkhb c where c.nd='"
						+ map.get("nd")
						+ "' and c.yf='"
						+ map.get("yf")
						+ "' and a.xh=c.xh and a.gwdm=c.gwdm and a.gwsbsj=c.gwsbsj) khgzsj," 
						+ "b.cjje,(select ffcjje from view_qgzx_xsgzkhb c where c.nd='"
						+ map.get("nd")
						+ "' and c.yf='"
						+ map.get("yf")
						+ "' and a.xh=c.xh and a.gwdm=c.gwdm and a.gwsbsj=c.gwsbsj) khcjje,b.bz,b.khqk,b.xxsh,b.gzpj ,(select kh from view_xsxxb c where a.xh=c.xh) kh  from (select rownum,xh,xm,bjmc,gwsbsj,gwdm from view_xsgwxx where gwdm||gwsbsj=(select gwdm||gwsbsj from view_gwxx where "
						+ pk
						+ "='"
						+ pkValue
						+ "') and sfyx='��Ч') a left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
						+ " and b.nd='"
						+ map.get("nd")
						+ "' and b.yf='"
						+ map.get("yf")
						+ "'";	
				}else if(Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)){
					//�㽭��ýѧԺ
					sql = "select a.*,b.gzsj,b.cjje,b.bz,b.khqk,b.xxsh ,nvl(b.yhkh,(select yhkh from xsxxb c where a.xh=c.xh)) kh,nvl(b.yhmc,(select yhmc from view_xsxxb c where a.xh=c.xh))yhmc  from (select rownum,xh,xm,bjmc,gwsbsj,gwdm from view_xsgwxx where gwdm||gwsbsj=(select gwdm||gwsbsj from view_gwxx where "
						+ pk
						+ "='"
						+ pkValue
						+ "') and xyyj='ͨ��' and xxyj='ͨ��') a left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
						+ " and b.nd='"
						+ map.get("nd")
						+ "' and b.yf='"
						+ map.get("yf")
						+ "'";
				}else if(Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)){
					//������Ϣְҵ����ѧԺ ��ְ��ѧ�������ų��
					sql = "select a.*,b.gzsj,b.cjje,b.bz,b.khqk,b.xxsh ,(case when c.sfsh='ͨ��' then c.xghkh else c.xgqkh end) kh  from (select rownum,xh,xm,bjmc,gwsbsj,gwdm from view_xsgwxx where (lzsj is null or (lzsj is not null and to_number(substr(lzsj,1,4)||substr(lzsj,6,2)||substr(lzsj,9,2))>to_char(sysdate,'yyyymmdd'))) and gwdm||gwsbsj=(select gwdm||gwsbsj from view_gwxx where "
						+ pk
						+ "='"
						+ pkValue
						+ "') and xyyj='ͨ��' and xxyj='ͨ��') a left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
						+ " and b.nd='"
						+ map.get("nd")
						+ "' and b.yf='"
						+ map.get("yf")
						+ "' left join khxgsqb c on c.xh=b.xh";
				} else {
					sql = "select a.*,b.gzsj,b.cjje,b.bz,b.khqk,b.xxsh ,(case when c.sfsh='ͨ��' then c.xghkh else c.xgqkh end) kh  from (select rownum,xh,xm,bjmc,gwsbsj,gwdm from view_xsgwxx where gwdm||gwsbsj=(select gwdm||gwsbsj from view_gwxx where "
						+ pk
						+ "='"
						+ pkValue
						+ "') and xyyj='ͨ��' and xxyj='ͨ��') a left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
						+ " and b.nd='"
						+ map.get("nd")
						+ "' and b.yf='"
						+ map.get("yf")
						+ "' left join khxgsqb c on c.xh=b.xh";	
				}
				System.out.println(sql);
				ArrayList<HashMap<String, String>> ffList = new ArrayList<HashMap<String, String>>();
				List<HashMap<String, String>> li_lrh2 = dao.getListNotOut(sql,new String[] {});
				for (int i = 0; i < li_lrh2.size(); i++) {
					HashMap<String, String> map1 = new HashMap<String, String>();
					map1 = li_lrh2.get(i);
					ffList.add(map1);
				}

				//��λ�����󣬷���ѧ���ڵ���ǰ�ĸ�λ����
				QgzxService service = new QgzxService();
				if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)){
					//�㽭��
					ffList.addAll(service.queryTzqxsgw(ffList, pkValue,map.get("nd"),map.get("yf")));
				}else{
					ffList.addAll(service.queryTzqxsgw(ffList, pk, pkValue));
				}

				request.setAttribute("count", Integer.toString(ffList.size()));
				request.setAttribute("ffList", ffList);// ��λ�µ�����ѧ����Ϣ�б�
				return new ActionForward("/sjcz/work_pay_details.jsp", false);
			} else if (realTable.equalsIgnoreCase("sjb")) {
				request.setAttribute("ynList", dao.getChkList(2));
				if (request.getParameter("buttonxsbj").equalsIgnoreCase("viewOnly")) {
					realTable = "sjb_view";
				}
			} else if (realTable.equalsIgnoreCase("stb")) {
				sql = "select stlxdm,stlxmc from stlxdmb";
				List stlxList = dao.getList(sql, new String[] {}, new String[] {
						"stlxdm", "stlxmc" });
				request.setAttribute("stlxList", stlxList);
				sql = "select stndjbdm,stndjbmc from stndjbdmb";
				List stndjbList = dao.getList(sql, new String[] {},
						new String[] { "stndjbdm", "stndjbmc" });
				request.setAttribute("stndjbList", stndjbList);
				request.setAttribute("ynList", dao.getChkList(2));
			} else if (realTable.equalsIgnoreCase("sjstb")) {
				String sjlsh = request.getParameter("sjlsh");
				if (sjlsh != null) {
					sql = "select sjlsh,sjm from sjb where sjlsh=?";
					String[] sjInfo = dao.getOneRs(sql, new String[] { sjlsh },
							new String[] { "sjlsh", "sjm" });
					if (sjInfo != null) {
						map.put("sjlsh", sjInfo[0]);
						map.put("sjm", sjInfo[1]);
					}
				}
				sql = "select a.stlsh,a.stlsh||'------------'||rownum stlshstxh from (select * from sjstb a where sjlsh=? order by stxh asc) a";
				List stList = dao.getList(sql, new String[] { sjlsh },
						new String[] { "stlsh", "stlshstxh" });
				request.setAttribute("stList", stList);
			} else if (realTable.equalsIgnoreCase("xsbzxxb")) {
				sql = "select bzlxdm,bzlxmc from bzlxdmb";
				List bzlxList = dao.getList(sql, new String[] {}, new String[] {
						"bzlxdm", "bzlxmc" });
				request.setAttribute("bzlxList", bzlxList);
			} else if (realTable.equalsIgnoreCase("xsgbxxb")) {
				sql = "select rzbmdm,rzbmmc from rzbmdmb";
				List rzbmList = dao.getList(sql, new String[] {}, new String[] {
						"rzbmdm", "rzbmmc" });
				request.setAttribute("rzbmList", rzbmList);
				request.setAttribute("chkList", dao.getChkList(3));
			} else if (realTable.equalsIgnoreCase("sthdxxb")) {
				sql = "select stdm,stmc from stdmb";
				List stList = dao.getList(sql, new String[] {}, new String[] {"stdm", "stmc" });
				request.setAttribute("stList", stList);
			} else if (realTable.equalsIgnoreCase("ssxxb")) {
				String lddm     = map.get("lddm");	
				String userName = session.getAttribute("userName").toString();
				if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {										
					userName = StringUtils.isNull(userName) ? "" : userName;
					request.setAttribute("showdzdx", "yes");					
					request.setAttribute("fpbjList", gyglDao.getQsLxList(2));
					sql = "select lddm,(select yqmc from yqdmb b where a.yqdm=b.yqdm)||ldmc ldmc from sslddmb a order by  lddm ";
				}else{
					sql = "select lddm,ldmc from sslddmb order by  lddm ";
					request.setAttribute("fpbjList", gyglDao.getQsLxList(1));
				}
				List ldList = dao.getList(sql, new String[] {}, new String[] {
						"lddm", "ldmc" });
				List csList = GyglShareDAO.getLcList2(lddm);
//				��Ԣ����Ա�жϼ��ظ���¥���б�begin
				//List listTem = gyglDao.getLddmxXx(userName,xqdm,yqdm);
				if(gyglDao.isGyFdy(userName)){
					gyglDao.reLoadLb(request);
				}else{
					request.setAttribute("ldList", ldList);
					request.setAttribute("csList",csList);//¥���б�
				}
//				��Ԣ����Ա�ж�end

			} else if (realTable.equalsIgnoreCase("xszsxxb")) {
				String sslx = DealString.toGBK(dataSearchForm.getSslx());//��������
				String ssbh = dataSearchForm.getSsbh();
				String xh = dataSearchForm.getXh();
				String xb = "";
				String xydm = "";
				String nj   = "";
				String bjdm = "";
				String cwh = "";
//				String[] xsRzIf = null;
				String userDep = session.getAttribute("userDep").toString();				
				String userType = dao.getUserType(userDep);
				String userName = session.getAttribute("userName").toString();
				StringBuffer sqlT = new StringBuffer();
//				if(Base.isNull(sslx)){
//				sslx = "yfpss";//�ѻ���ѧԺ����					
//				}
				if("xy".equalsIgnoreCase(userType)){
					sslx = "yfpss";
				}                
				StringBuffer querry = new StringBuffer();
				StringBuffer querryT = new StringBuffer();				
				boolean isSss = GyglShareDAO.isSssAdmin(userName);//�о����û�ʶ��
				String tabname = "";

				if (xh != null && !xh.equalsIgnoreCase("")) {
					if(isSss){
						tabname = "sss_xxb";
					}else{
						tabname = "view_xsjbxx";
					}
					sql = " select xh,xm,xb,nj,xymc,zymc,bjmc,xydm,bjdm from "+tabname+" where xh = ? ";
					String[] outPut = { "xh", "xm", "xb", "nj", "xymc", "zymc",
							"bjmc","xydm","bjdm" };
					String[] outValue = dao.getOneRs(sql, new String[] { xh },
							outPut);
					for (int i = 0; i < outPut.length; i++) {
						map.put(outPut[i], outValue[i]);
					}
				}
				xh   = map.get("xh");
				xb   = map.get("xb");//�Ա�
				xydm = map.get("xydm");//ѧԺ����
				nj   = map.get("nj");//�꼶
				bjdm = map.get("bjdm");//�༶����

				List<HashMap<String, String>> cwhList = new ArrayList<HashMap<String, String>>();
				List<HashMap<String, String>> ssList = new ArrayList<HashMap<String, String>>();
				List temCwhList = null;

				if(!Base.isNull(doType)
						&&(doType.equalsIgnoreCase("modi")
								||doType.equalsIgnoreCase("view"))){
					if(!Base.isNull(pkValue)){//�޸ġ��鿴ѧ��ס����Ϣ����ҳ��						
						sql  = " select ssbh from xszsxxb where " + pk + "=?";
						ssbh= dao.getOneRs(sql, new String[] { pkValue },"ssbh");					
						if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {//�й����ʴ�ѧ(�ֱ��ơ�˶ʿ����ʿ)
							if(isSss){
								sql  = " select count(a.xydm) cout from ssfpb a,view_ssszsxx b where a.ssbh=b.ssbh and a.xydm=b.xydm and a.nj=b.nj and b.xh=? ";
							}else{
								sql  = " select count(a.xydm) cout from ssfpb a,view_xszsxx b where a.ssbh=b.ssbh and a.xydm=b.xydm and a.nj=b.nj and b.xh=? ";
							}
						}else{
							sql  = " select count(a.xydm) cout from ssfpb a,view_xszsxx b where a.ssbh=b.ssbh and a.xydm=b.xydm and a.nj=b.nj and b.xh=? ";
						}
						if(dao.getOneRs(sql,new String[]{pkValue},"cout").equalsIgnoreCase("0")){//�жϸ�����ס�����Ƿ���δ����ѧԺ����
							sslx = "wfpss";
						}else{
							sslx = "yfpss";
						}
					}					
				}
//				���������б�begin
				querry.append((Base.isNull(xb))?"":" and (xbxd = '" + xb + "' or xbxd='���') ");//��ѯ������Ϣ����	
				if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {//�й����ʴ�ѧ
					if(isSss){
						if(sslx.equalsIgnoreCase("wfpss")){//δ����ѧԺ�����У�����
							sql = " select distinct ssbh dm,yqmc||ldmc||qsh mc from view_ssxx  a where (fpbj = '˶ʿ' or fpbj = '��ʿ') and not exists (select * from ssfpb b where a.ssbh=b.ssbh ) " + querry
							+ " order by ssbh ";
						}else{//�ѻ���ѧԺ����
							sql = "select distinct a.ssbh dm,b.yqmc||b.ldmc||b.qsh mc from ssfpb a,view_ssxx b where (fpbj = '˶ʿ' or fpbj = '��ʿ') and a.ssbh=b.ssbh "+querry+" order by a.ssbh ";
						}
						request.setAttribute("xslx", "sss");
					}else{
						if(sslx.equalsIgnoreCase("wfpss")){//δ����ѧԺ�����У�����
							sql = " select distinct ssbh dm,yqmc||ldmc||qsh mc from view_ssxx  a where fpbj = 'һ��' and not exists (select * from ssfpb b where a.ssbh=b.ssbh ) " + querry
							+ " order by ssbh ";
						}else{//�ѻ���ѧԺ����
							querry.append((Base.isNull(nj))?"  ":" and nj='"+nj+"' ");
							querry.append((Base.isNull(xydm))?"  ":" and xydm='"+xydm+"' ");
							sql = "select distinct a.ssbh dm,b.yqmc||b.ldmc||b.qsh mc from ssfpb a,view_ssxx b where fpbj='һ��' and a.ssbh=b.ssbh "+querry+" order by a.ssbh ";
						}
					}
				}else{						
					if(sslx.equalsIgnoreCase("wfpss")){//δ����ѧԺ�����У�����
//						if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){//����ְҵ����ѧԺ							
//						sql = "select distinct a.ssbh dm,a.ldmc||a.qsh mc from view_ssxx a where fpbj='һ��' and not exists( "
//						+"select b.ssbh ,b.cout from (select ssbh ,count(*)cout from ssfpb b group by ssbh )b where a.ssbh=b.ssbh"
//						+" and  a.cws=b.cout ) "+querry+" order by a.ssbh ";
//						}else{
						sql = " select distinct a.ssbh dm,a.ldmc||'/'||a.qsh mc from view_ssxx a where fpbj='һ��' and not exists (" 
							+ " select b.ssbh,b.cwssum from (select ssbh,sum(cws) cwssum from ssfpb group by ssbh )b where a.ssbh=b.ssbh and a.cws=b.cwssum "
							+ " ) "+querry+" order by a.ssbh";
//						}
					}else {//�ѻ���ѧԺ����
						if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){//����ְҵ����ѧԺ
							querry.append((Base.isNull(bjdm))?" and 1=2 ":" and bjdm='"+bjdm+"' ");
							sql = "select distinct a.ssbh dm,a.ldmc||a.qsh mc from view_ssxx a,ssfpb b where a.ssbh=b.ssbh and a.fpbj='һ��' "+querry;
						}else{
							querry.append((Base.isNull(nj))?"":" and nj='"+nj+"' ");
							querry.append((Base.isNull(xydm)?" and 1=2 ":" and xydm='"+xydm+"' "));
							sql = " select distinct a.ssbh dm,a.ldmc||a.qsh mc from view_ssxx a,ssfpb b where a.ssbh=b.ssbh and fpbj = 'һ��' " + querry
							+ " order by a.ssbh ";
						}
					}		
				}
				ssList = dao.getList(sql, new String[] {},
						new String[] { "dm","mc" });
				request.setAttribute("ssList", ssList);
				////���������б�end	

//				���ɴ�λ�б�begin
				if(sslx.equalsIgnoreCase("yfpss")){
					if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {//�й����ʴ�ѧ(�ֱ��ơ�˶ʿ����ʿ)
						querryT.append((Base.isNull(ssbh))?" and 1=2 ":" and a.ssbh='"+ssbh+"' ");
						//��������ְҵ����ѧԺ ��λ�Ŵ������ģ����Ի��޸�
						String sb1 = "";
						if("12898".equals(Base.xxdm)){
							sb1 = "a.cwh";
						}else{
							sb1 = "to_number(a.cwh)";
						}
						sqlT.append("select a.cwh dm,a.cwh mc from cwxxb a,ssfpb b  where a.ssbh=b.ssbh and a.ssbh||a.cwh not in (select distinct ssbh||cwh from (select ssbh,cwh from xszsxxb union  select ssbh,cwh from wxs_xszsxxb) b where a.ssbh=b.ssbh) "+querryT+" order by "+sb1+" ");
						cwhList = dao.getList(sqlT.toString(), new String[] {}, new String[] {"dm", "mc" });
					}else if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){//����ְҵ����ѧԺ						
						querryT.append((Base.isNull(bjdm))?" and 1=1 ":" and a.bjdm='"+bjdm+"' ");
						querryT.append((Base.isNull(ssbh))?" and 1=2 ":" and a.ssbh='"+ssbh+"' ");
						querryT.append(" order by dm ");
						sqlT.append(" select cwh dm,cwh mc from ssfpb a,view_ssxx b where ");
						sqlT.append(" a.ssbh=b.ssbh and not exists (select distinct ssbh,cwh from(select ssbh,cwh from xszsxxb union select ssbh, cwh from wxs_xszsxxb) b where a.ssbh=b.ssbh and a.cwh=b.cwh) "+querryT );
						cwhList = dao.getList(sqlT.toString(), new String[] {}, new String[] {"dm", "mc" });
					}else{	
						querryT.append(Base.isNull(xydm)?" and 1=2 ":"  and a.xydm='" + xydm + "'");
						querryT.append(Base.isNull(nj)?" and 1=2 ":"  and a.nj='" + nj + "'");
						querryT.append((Base.isNull(ssbh))?" and 1=2 ":" and a.ssbh='"+ssbh+"' ");
						//��������ְҵ����ѧԺ ��λ�Ŵ������ģ����Ի��޸�
						String sb2 = "";
						if("12898".equals(Base.xxdm)){
							sb2 = "c.cwh";
						}else{
							sb2 = "to_number(c.cwh)";
						}
						sqlT.append(" select dm,mc from(select c.cwh dm,c.cwh mc,");
						sqlT.append(" to_number(b.cws) - to_number((select count(ssbh) from xszsxxb b where a.ssbh = b.ssbh)) leaveCws,");
						sqlT.append(" (select count(ssbh)from view_xszsxx b where a.ssbh = b.ssbh and a.nj=b.nj and a.xydm=b.xydm)rzs,");			
						sqlT.append(" row_number() over( partition by a.ssbh order by a.ssbh,"+sb2+") px,a.cws fps  from ssfpb a,view_ssxx b,");
						sqlT.append(" cwxxb c where a.ssbh = b.ssbh and a.ssbh = c.ssbh");
						sqlT.append(querryT.toString());
						sqlT.append(" and a.ssbh||c.cwh not in (select distinct ssbh||cwh from xszsxxb) and a.ssbh || c.cwh not in ( select distinct ssbh || cwh from wxs_xszsxxb) ");
						sqlT.append(" )a where px <=fps-rzs ");
						cwhList = dao.getList(sqlT.toString(), new String[] {}, new String[] {"dm", "mc" });
					}
				}else if(sslx.equalsIgnoreCase("wfpss")){					
					if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {//�й����ʴ�ѧ(�ֱ��ơ�˶ʿ����ʿ)						
						querryT.append((Base.isNull(ssbh))?" and 1=2 ":" and a.ssbh='"+ssbh+"' ");
						//��������ְҵ����ѧԺ ��λ�Ŵ������ģ����Ի��޸�
						String sb3 = "";
						if("12898".equals(Base.xxdm)){
							sb3 = "a.cwh";
						}else{
							sb3 = "to_number(a.cwh)";
						}
						sqlT.append(" select a.cwh dm,a.cwh mc from cwxxb a  where a.ssbh||a.cwh  not in ( select distinct ssbh||cwh from (select ssbh,cwh from xszsxxb union select ssbh,cwh from wxs_xszsxxb ) b where a.ssbh=b.ssbh) "+querryT+" order by "+sb3+" ");
						cwhList = dao.getList(sqlT.toString(), new String[] {}, new String[] {"dm", "mc" });   
					}else if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){//����ְҵ����ѧԺ						

						querryT.append((Base.isNull(ssbh))?" and 1=2 ":" and a.ssbh='"+ssbh+"' ");
						querryT.append(" order by dm ");	

						sqlT.append(" select cwh dm,cwh mc from cwxxb a where  not exists (select distinct ssbh ,cwh from ( select distinct ssbh,cwh from view_xszsxx ");
						sqlT.append(" union all select distinct ssbh, cwh from wxs_xszsxxb) b where a.ssbh=b.ssbh and a.cwh=b.cwh  )"+querryT );
						cwhList= dao.getList(sqlT.toString(), new String[] {}, new String[] {"dm", "mc" });						

//						sqlT = new StringBuffer();
//						sqlT.append(" select distinct cwh dm,cwh mc from xszsxxb a where xh=? ");
//						temCwhList =  dao.getList(sqlT.toString(), new String[] {pkValue}, new String[] {"dm", "mc" });                        
//						cwhList.addAll(temCwhList);
					}else{
						//��������ְҵ����ѧԺ ��λ�Ŵ������ģ����Ի��޸�
						String sb = "";
						if("12898".equals(Base.xxdm)){
							sb = "cwh";
						}else{
							sb = "to_number(cwh)";
						}
						sqlT.append(" select cwh dm,cwh mc from cwxxb a where ssbh=? and not exists(select distinct ssbh,cwh from(select ssbh,cwh from xszsxxb union select ssbh,cwh from wxs_xszsxxb) b where a.ssbh||a.cwh=b.ssbh||b.cwh) order by "+sb+" ");
						cwhList =  dao.getList(sqlT.toString(), new String[] {ssbh}, new String[] {"dm", "mc" });
						//querryT.append(Base.isNull(xydm)?" and 1=1 ":"  and a.xydm='" + xydm + "'");
						//querryT.append(Base.isNull(nj)?" and 1=1 ":"  and a.nj='" + nj + "'");
						//querryT.append((Base.isNull(ssbh))?" and 1=2 ":" and a.ssbh='"+ssbh+"' ");
						//querryT.append(" order by dm");
						sqlT = new StringBuffer();
//						sqlT.append("select  dm,mc from (select c.cwh dm,c.cwh mc, ");
//						sqlT.append("a.ssbh,a.nj,a.xydm from ssfpb a, view_ssxx b, cwxxb c where a.ssbh = b.ssbh and a.ssbh = c.ssbh and a.cws=b.cws ");
//						sqlT.append("and a.ssbh||c.cwh not in (select distinct ssbh||cwh from xszsxxb) union select a.cwh en,  ");
//						sqlT.append("a.cwh cn,a.ssbh ,a.nj,a.xydm from ( select a.ssbh,cwh,xydm,nj from ( ");
//						sqlT.append("select a.ssbh, rank() over ( partition by a.ssbh,xydm,nj order by to_number(a.cwh) ) px,a.cwh,cws,countcwh,xydm,nj from ( ");
//						sqlT.append("select ssbh,cws,cwh,countcwh,xydm,nj from (select distinct a.ssbh, c.cwh, a.cws,countcwh,d.xydm,d.nj from ssfpb a,cwxxb c,  ");
//						sqlT.append("(select ssbh,countcwh,xydm,nj from (select distinct a.ssbh,count(b.cwh) countcwh,a.xydm,a.nj from (select a.ssbh,a.xydm,a.nj ");
//						sqlT.append("from ssfpb a,view_ssxx b where a.cws <> b.cws and a.ssbh=b.ssbh ) a left join (select distinct a.ssbh,a.cwh,b.nj,b.xydm  ");
//						sqlT.append("from xszsxxb a,view_xsjbxx b where a.xh=b.xh ) b on a.ssbh=b.ssbh and a.xydm=b.xydm and a.nj=b.nj group by a.ssbh,a.xydm,a.nj ");
//						sqlT.append(")) d where a.ssbh = c.ssbh and a.ssbh = d.ssbh and a.ssbh || c.cwh not in (select ssbh || cwh from xszsxxb) and d.countcwh<>a.cws ");
//						sqlT.append("and a.xydm=d.xydm and a.nj=d.nj )) a )a  where px between 1 and a.cws-a.countcwh ) a,view_ssxx b where a.ssbh = b.ssbh )a, ");
//						sqlT.append("view_ssxx b,ssfpb c where a.ssbh = b.ssbh and a.ssbh = c.ssbh and c.xydm=a.xydm and c.nj=a.nj ");
//						sqlT.append(querryT);
						sqlT.append("select cwh dm,cwh mc,ssbh from (select "+sb+" cwh,ssbh from cwxxb c where exists( select 1 from view_xszsxx d where c.ssbh=d.ssbh and c.cwh=d.cwh )   order by cwh ) a ");
						sqlT.append("where ssbh = ? and rownum <=( select nvl(sum(cws),0) from ssfpb b where b.ssbh=a.ssbh)");
						temCwhList = dao.getList(sqlT.toString(), new String[] {ssbh}, new String[] {"dm", "mc" });
						cwhList.removeAll(temCwhList);
					}
				}
				request.setAttribute("cwhList", cwhList);//��λ���б�	
				//���ɴ�λ�б�end								

				sql = " select cwh from xszsxxb where ssbh = ? and " + pk + "=?";
				cwh = dao.getOneRs(sql, new String[] { ssbh,pkValue }, "cwh");
				if(!Base.isNull(cwh)){
					HashMap<String, String> mymap = new HashMap<String, String>();
					mymap.put("dm",cwh);
					mymap.put("mc",cwh);
					cwhList.add(mymap);
				}

				map.put("sslx", sslx);//��������
				map.put("ssbh",ssbh);//������
				map.put("sfbz",dao.getOneRs("select sfbz from view_ssxx where ssbh=? ",new String[]{ssbh},"sfbz"));
				if(Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)
						||Globals.XXDM_HHGXY.equalsIgnoreCase(xxdm)){
					sql = "select nvl(b.lz,'����')lz, nvl(a.cz,'����')cz from (select (select xm||'/'||xh||'/'||bjmc from view_xsjbxx d where d.xh=b.cz and rownum=1) cz,";               
					sql +="c.lddm from czxxb b,ssxxb c where b.lddm=c.lddm and sfzz='��' and c.ssbh=? and rownum=1) a ";
					sql +="left join (select (select xm||'/'||xh||'/'||bjmc from view_xsjbxx d where d.xh=b.lz and rownum=1) lz,c.lddm from ";
					sql +="lzxxb b,ssxxb c where b.lddm=c.lddm and sfzz='��' and c.ssbh=? and rownum=1)b on a.lddm=b.lddm";
					request.setAttribute("rsLzCz",dao.getMap(sql,new String[]{ssbh,ssbh},new String[]{"lz","cz"}));
					request.setAttribute("rslxr",dao.getMap("select xm||'/'||xh||'/'||bjmc lxr from view_zrqfzrxx where fzssbh like '%"+ssbh+"%' and sfzz='��' and rownum=1 ",new String[]{},new String[]{"lxr"}));
					request.setAttribute("showlczrr", "showlczrr");
					sql = " select max(ltrim(sys_connect_by_path(xm||'('||'�绰��'||(select lxdh  from gygl_lzxxb b where a.yhm=b.yhm and rownum=1 )||')','��'),'��')) cy from (";
					sql +="	(select lddm,yhm,xm, row_number() over (partition by lddm order by lddm,yhm,xm) pno,row_number() over (partition by lddm order by lddm,yhm,xm)-1 sno";
					sql +="	from gygl_lzxxb where xn=? and xq=? and lddm=(select lddm from view_ssxx b where b.ssbh=? and rownum=1 )group by lddm,yhm,xm)a";
					sql +=" ) start with pno=1 connect by prior pno=sno and prior lddm=lddm  ";
					request.setAttribute("gyfdyxx", dao.getMap(sql,new String[]{Base.currXn,Base.currXq,ssbh},new String[]{"cy"}));
				}
				if(Globals.XXDM_XCXY.equals(xxdm)){//����ѧԺ
					String xsnj = map.get("nj");
					sql = "select rzsj from njrzsj where nj=?";
					String rzsj = dao.getOneRs(sql, new String[]{xsnj}, "rzsj");
					request.setAttribute("rzsj", rzsj!=null ? rzsj:"");
				}
				request.setAttribute("doType", doType);
				request.setAttribute("userType", userType);
				
				if(Globals.XXDM_JHZYJSXY.equalsIgnoreCase(xxdm)&&"view".equalsIgnoreCase(doType)){//��ְҵ����ѧԺ
					sql = "select * from (select distinct xn,(select xqmc from xqdzb b where b.xqdm=a.xq)xqmc,xq,wsdj,jcrq from jhzy_qswsjcb a where ssbh=?) order by xn,xq";
					request.setAttribute("rsOtherInfo","info");
					request.setAttribute("rsWsjc",dao.rsToVator(sql,new String[]{ssbh},new String[]{"xn","xqmc","wsdj","jcrq"}));
					sql = "select xn,xqmc,dj from  view_xjqsxx where fdysh='ͨ��'and xysh='ͨ��' and xxsh='ͨ��' and ssbh=? order by xn,xq";
					request.setAttribute("rsXjwmqs",dao.rsToVator(sql,new String[]{ssbh},new String[]{"xn","xqmc","dj"}));
					sql = "select xn,xqmc,wjlbmc,yjfs,wjsj,ldmc||qsh zz from view_zsjlxx where xh=? order by xn,xq,wjsj";
					request.setAttribute("rsZsjl",dao.rsToVator(sql,new String[]{xh},new String[]{"xn","xqmc","wjlbmc","yjfs","wjsj","zz"}));					
				    sql = "select xm from jhzy_gyfdyb where ssbh=? and xn=? and xq=? ";
				    request.setAttribute("rsGyFdy",dao.getMap(sql,new String[]{ssbh,Base.currXn,Base.currXq},new String[]{"xm"}));
				    sql = "select (select lz||'/'||xm from view_lzxx a,ssxxb b where a.lddm=b.lddm and b.ssbh=? and sfzz='��'and rownum=1 )lz, ";
				    sql += "(select cz||'/'||xm from view_czxx a,ssxxb b where a.lddm=b.lddm and a.lc=b.cs and b.ssbh=?  and sfzz='��'and rownum=1)cz ,";
				    sql += "(select qsz||'/'||xm from view_qszxx where ssbh=? and sfzz='��'and rownum=1)qsz from dual";
				    request.setAttribute("rsLcqsz",dao.getMap(sql,new String[]{ssbh,ssbh,ssbh},new String[]{"lz","cz","qsz"}));
				   
				}
				//map.put("picture", PictureUtil.getPicPath(request.getSession().getServletContext().getRealPath("").toString(),new String[]{xh},null));

			} else if (realTable.equalsIgnoreCase("mrzbjlb")
					|| realTable.equalsIgnoreCase("yzzbhzb")) {
				sql = "select lddm,ldmc from sslddmb order by  lddm ";
				List ldList = dao.getList(sql, new String[] {}, new String[] {
						"lddm", "ldmc" });
				request.setAttribute("ldList", ldList);
				sql = "select zbrydm,zbrymc from zbrydmb";
				List zbryList = dao.getList(sql, new String[] {}, new String[] {
						"zbrydm", "zbrymc" });
				request.setAttribute("zbryList", zbryList);
				if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)) {
					request.setAttribute("syzy", true);
					sql = "select dm,xqmc from dm_zju_xq";
					List xiaoqquList = dao.getList(sql, new String[] {},
							new String[] { "dm", "xqmc" });
					request.setAttribute("xiaoqquList", xiaoqquList);
				}
				if(xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)){
					List zbrLxList = dao.getChkList(16);//ֵ����Ա����
					request.setAttribute("zbrLxList",zbrLxList);
				}
				if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJJZYJSXY)){//�㽭����ְҵ����ѧԺ
					request.setAttribute("showZjjj","showZjjj");
					request.setAttribute("bmList",dao.getBmList());
				}
			} else if (realTable.equalsIgnoreCase("xszffb")
					|| realTable.equalsIgnoreCase("hcyhkffb")
					|| realTable.equalsIgnoreCase("xhffb")
					|| realTable.equalsIgnoreCase("xszbbb")
					|| realTable.equalsIgnoreCase("hcyhkbbb")
					|| realTable.equalsIgnoreCase("yktbbb")
					|| realTable.equalsIgnoreCase("xhbbb")
					|| realTable.equalsIgnoreCase("hcyhkczb")) {
				sql = "select jbrgh,jbrxm from jbrxxb order by jbrgh";  //��������Ϣ��
				List jbrList = dao.getList(sql, new String[] {}, new String[] {
						"jbrgh", "jbrxm" });
				request.setAttribute("jbrList", jbrList);
				request.setAttribute("ynList", dao.getChkList(2));
				
				if (!"xhffb".equalsIgnoreCase(realTable) && !"xhbbb".equalsIgnoreCase(realTable)) {
					map.put("xn", dqndq[0]);// xn
					map.put("xq", dqndq[1]);// xq
					map.put("nd", dqndq[2]);// nd
				}
				
			} else if (realTable.equalsIgnoreCase("hcccb")) {
				sql = "select czdm,czmc from czdmb";
				List czList = dao.getList(sql, new String[] {}, new String[] {
						"czdm", "czmc" });
				request.setAttribute("czList", czList);
			} else if (realTable.equalsIgnoreCase("bks_xsszjbxx")) {
				map = new HashMap<String, String>();
				String xh = request.getParameter("pkValue");
				sql = "select * from view_xslxfszsxx where xh=?";
				String[] list = new String[] { "xh", "xm", "xymc", "zymc",
						"bjmc", "lxdh1", "sjhm", "email", "ssbh",
				"cwh"};
				String[] xsxx = dao.getOneRs(sql, new String[] { xh }, list);
				for (int i = 0; i < xsxx.length; i++) {
					if (!(xsxx[i] == null)) {
						map.put(list[i].toLowerCase(), xsxx[i].toString());
					} else
						map.put(list[i].toLowerCase(), "");
				}
				String userType = session.getAttribute("userType").toString();

				request.setAttribute("rs", map);
				request.setAttribute("userType", userType);
				request.setAttribute("tableName", tableName);
				request.setAttribute("realTable", realTable);
				request.setAttribute("tips", "ѧ����Ϣ - ѧ����Ϣά�� - ����ѧ����Ϣά��");

			} else if (realTable.equalsIgnoreCase("qcccb")) {
				sql = "select czdm,czmc from qczdmb";
				List czList = dao.getList(sql, new String[] {}, new String[] {
						"czdm", "czmc" });
				request.setAttribute("czList", czList);
			} else if (realTable.equalsIgnoreCase("xszxbzb")) {

				String userType = session.getAttribute("userType").toString();
				request.setAttribute("userType", userType);
				request.setAttribute("chkList", dao.getChkList(3));

				sql = "select * from view_zxbzxx ";
				String[] outString = dao.getColumnName(sql + " where 1=2 ");
				String[] outValue = dao.getOneRs(sql + " where " + pk + " =? ",
						new String[] { pkValue }, outString);

				if (outValue != null) {
					for (int i = 0; i < outString.length; i++) {
						if (outValue[i] != null) {
							map.put(outString[i].toLowerCase(), outValue[i]);
						} else {
							map.put(outString[i].toLowerCase(), "");
						}
					}
				}

			} else if (realTable.equalsIgnoreCase("xsgwxxb")) {
				sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from gwxxb";
				List gwList = dao.getList(sql, new String[] {}, new String[] {
						"gwdm", "gwdmgwsbsj" });
				request.setAttribute("gwList", gwList);
			} else if (realTable.equalsIgnoreCase("lstdb")) {
			} else if (realTable.equalsIgnoreCase("kkqkb")) {
				if (doType == "add") {
					map.put("xn", dqndq[0]);// xn
					map.put("xq", dqndq[1]);// xq
					map.put("nd", dqndq[2]);// nd
				}
				List kcList = null;
				if (xxdm.equalsIgnoreCase(Globals.XXDM_GZCJXY)) {
					String xn = dataSearchForm.getXn();
					String xq = dataSearchForm.getXq();
					String nd = dataSearchForm.getNd();
					String xh = request.getParameter("xh");
					String cg = DealString.toGBK(request.getParameter("chag"));
					String bjdm = dao.getOneRs(
							"select bjdm from view_xsjbxx where xh=?",
							new String[] { xh }, "bjdm");
					sql = "select * from view_xsjbxx where xh=?";
					colList = dao
					.getColumnName("select * from view_xsjbxx where 1=2");
					String[] rs = dao.getOneRs(sql, new String[] { xh },
							colList);
					if (rs != null) {
						for (int i = 0; i < colList.length; i++) {
							map.put(colList[i].toLowerCase(), rs[i]);
						}
					}
					if (cg.equalsIgnoreCase("true")) {
						map.put("xn", xn);// xn
						map.put("xq", xq);// xq
						map.put("nd", nd);// nd
					}
					dataSearchForm.setXh(xh);
					kcList = dao.getKcList("gzcj_kcxx", bjdm, xn, xq);
					request.setAttribute("isGZCJXY", "yes");
				} else {
					sql = "select distinct kcdm,kcmc from bks_kcdm";
					kcList = dao.getList(sql, new String[] {}, new String[] {
							"kcdm", "kcmc" });
				}
				request.setAttribute("kcList", kcList);
			} else if (realTable.equalsIgnoreCase("xfcjb")) {
				List<HashMap<String, String>> qflxList = new ArrayList<HashMap<String, String>>();
				qflxList = dao.getList("select qflxdm,qflxmc from qflxdmb",
						new String[] {}, new String[] { "qflxdm", "qflxmc" });
				request.setAttribute("qflxList", qflxList);
				
				/*map.put("xn", dqndq[0]);// xn
				map.put("xq", dqndq[1]);// xq
				map.put("nd", dqndq[2]);// nd*/		
				
				if (xxdm.equalsIgnoreCase(Globals.XXDM_GZCJXY)) {// ���ݳǽ�
					String xh = request.getParameter("xh");
					sql = "select * from view_xfcjxx where xh=?";
					List xfcjList = dao.getList(sql, new String[] { xh },
							new String[] { "xn", "je", "qflxmc", "bz" });

					sql = "select hjje,hjqx from view_xfhjxx where xh=?";
					List xfhjList = dao.getList(sql, new String[] { xh },
							new String[] { "hjje", "hjqx" });

					request.setAttribute("xfcjList", xfcjList);
					request.setAttribute("xfhjList", xfhjList);
				}
			} else if (realTable.equalsIgnoreCase("gywxglb")) {				

				String lddm = request.getParameter("lddm");
				StringBuffer cxtj = new StringBuffer();				
				String userType = session.getAttribute("userType").toString();
//				String userName = session.getAttribute("userName").toString();
				if(doType.equalsIgnoreCase("add")){//���ʱĬ��ϵͳ��������
					map.put("xn",Base.currXn);
					map.put("xq",Base.currXq);
				}
				//��Ԣ����Ա�ж�begin
//				String lddmStr = gyglDao.getLddmxXx(userName);
//				String isGyFdy = "no";
//				if(!Base.isNull(lddmStr)){
//				lddm = lddmStr; 
//				//comForm.setLddm(lddm);
//				isGyFdy = "yes";
//				map.put("lddm", lddm);
//				}
//				request.setAttribute("isGyFdy","");
				//��Ԣ����Ա�ж�end					

				if(xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)){
					request.setAttribute("isGDBYXY","yes");
				}
				if(Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)){
					request.setAttribute("isNBZYJSXY","isNBZYJSXY");
				}
				cxtj.append(" where 1=1");
				if ("".equalsIgnoreCase(lddm) || lddm == null) {
					cxtj.append(" and 1=1");
				} else {
					if (Check_Input_Value.check2(lddm)) {
						cxtj.append(" and lddm='");
						cxtj.append(lddm);
						cxtj.append("'");
					}
				}
//				sql = "select lddm,ldmc from sslddmb order by  lddm ";
//				List ldList = dao.getList(sql, new String[] {}, new String[] {
//				"lddm", "ldmc" });
//				request.setAttribute("ldList", ldList);
				sql = "select rydm,rymc from gywxryb where rydm <> '000' ";
				List ryList = dao.getList(sql, new String[] {}, new String[] {
						"rydm", "rymc" });
				request.setAttribute("ryList", ryList);
				sql = "select bmdm,bmmc from gywsjcbmb";
				List bmList = dao.getList(sql, new String[] {}, new String[] {
						"bmdm", "bmmc" });
				request.setAttribute("bmList", bmList);
//				sql = "select distinct qsh from ssxxb " + cxtj;
//				List ssList = dao.getList(sql, new String[] {},
//				new String[] { "qsh" });
//				request.setAttribute("ssList", ssList);
				gyglDao.getLdLcQshList(request);
				request.setAttribute("userType", userType);
				request.setAttribute("xxdm", xxdm);
				sql = "select dm,mc from wxnrdmb order by dm ";
				List wxnrList = dao.getList(sql, new String[] {}, new String[] {
						"dm", "mc" });
				request.setAttribute("wxnrList", wxnrList);
			} else if (realTable.equalsIgnoreCase("gywsjcb")) {
				if(Globals.XXDM_WHLGDXHXXY.equalsIgnoreCase(xxdm)
						&&"add".equalsIgnoreCase(doType)){//�人����ѧ����ѧԺ
					return new ActionForward("/whlghxxy_Gygl.do?method=wsjcInput",false);
				}
				String clin = "�������";
				String lddm = request.getParameter("lddm");
				StringBuffer cxtj = new StringBuffer();
				if(doType.equalsIgnoreCase("add")){//���ʱĬ��ϵͳ��������
					map.put("xn",Base.currXn);
					map.put("xq",Base.currXq);
				}
				String userType = session.getAttribute("userType").toString();
//				String userName = session.getAttribute("userName").toString();
				//��Ԣ����Ա�ж�begin
//				String lddmStr = gyglDao.getLddmxXx(userName);
//				String isGyFdy = "no";
//				if(!Base.isNull(lddmStr)){
//				lddm = lddmStr; 
//				//comForm.setLddm(lddm);
//				isGyFdy = "yes";
//				map.put("lddm", lddm);
//				}
//				request.setAttribute("isGyFdy","");
				//��Ԣ����Ա�ж�end					

				cxtj.append(" where 1=1");
				if ("".equalsIgnoreCase(lddm) || lddm == null) {
					cxtj.append(" and 1=1");
				} else {
					if (Check_Input_Value.check2(lddm)) {
						cxtj.append(" and lddm='");
						cxtj.append(lddm);
						cxtj.append("'");
					}
				}
//				sql = "select lddm,ldmc from sslddmb order by  lddm ";
//				List ldList = dao.getList(sql, new String[] {}, new String[] {
//				"lddm", "ldmc" });
				sql = "select bmdm,bmmc from gywsjcbmb";
				List bmList = dao.getList(sql, new String[] {}, new String[] {
						"bmdm", "bmmc" });

				List djList = dao.getScore(5);				
//				request.setAttribute("ldList", ldList);
				if(Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)){//�㽭��ýѧԺ
					djList = dao.getScore(6);
				}else if(Globals.XXDM_WHLGDXHXXY.equalsIgnoreCase(xxdm)){
					djList = dao.getList("select * from nwwsdjdmb order by nwwsdjfs desc",new String[]{},new String[]{"nwwsdjdm","nwwsdjmc","nwwsdjfs"});
					request.setAttribute("zsList",gyglDao.dao_zsList());
				}else if(Globals.XXDM_NBZYJSXY.equalsIgnoreCase(xxdm)){
					djList = dao.getScore(7);
				}else if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {// �й��ش�
					djList = dao.getWhList("zgdd_wsjcdj", "dm", "mc", "", "",
							"");
					djList.remove(0);
				}else if (Globals.XXDM_HHGXY.equalsIgnoreCase(xxdm)) {// ������ѧԺ
					//TODO
					sql ="select xqzs from xtszb";
					String zs = dao.getOneRs(sql, new String[]{}, "xqzs");
					List<HashMap<String, String>> zsList = new ArrayList<HashMap<String, String>>();
					List<HashMap<String, String>> wsjcdjList = new ArrayList<HashMap<String, String>>();
					if (zs != null && !"".equalsIgnoreCase(zs)) {
						int zsV = Integer.parseInt(zs);
						for(int i=1;i<=zsV;i++){
							HashMap<String, String> zsMap = new HashMap<String, String>();
							zsMap.put("dm",String.valueOf(i));
							zsMap.put("mc","��"+String.valueOf(i)+"��");
							zsList.add(zsMap);
						}
					}					
					wsjcdjList=dao.getList("select wsjcdj dj,wsjccj cj from hhgxy_wsjcdj order by dj",
							new String[]{}, new String[]{"cj","dj"});

					request.setAttribute("zsList", zsList);
					request.setAttribute("wsjcdjList", wsjcdjList);
				}
				request.setAttribute("bmList", bmList);
				request.setAttribute("djList", djList);
//				sql = "select distinct qsh from ssxxb " + cxtj;
//				List ssList = dao.getList(sql, new String[] {},
//				new String[] { "qsh" });
//				request.setAttribute("ssList", ssList);
				request.setAttribute("userType", userType);
				request.setAttribute("xxdm", xxdm);
				if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJJZYJSXY)
						||xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
					if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJJZYJSXY)){
						clin="����������Ϣ";						
					}
					if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
						clin="��������";						
					}
					request.setAttribute("noshowbm","noshowbm");
				}
				request.setAttribute("clin", clin);
				gyglDao.getLdLcQshList(request);
			} else if (realTable.equalsIgnoreCase("zsjlb")) {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_GZCJXY)) {
					return new ActionForward("/gygl_gzcj_zsjl.do", false);
				}
				if(doType.equalsIgnoreCase("add")){//��Ϣ���ʱĬ��ϵͳ��������
					map.put("xn",Base.currXn);
					map.put("xq",Base.currXq);
				}
				String userDep = session.getAttribute("userDep").toString();
				String userType = dao.getUserType(userDep);
//				String userName = session.getAttribute("userName").toString();
				String lddm = request.getParameter("lddm");
				StringBuffer cxtj = new StringBuffer();
				String[] ldQshTem = null;// �洢ѧ����ס¥�����Һ�
				String xh = request.getParameter("xh");// ס�޼�����Ϣ���ʱ�������ѡѧ��
				String[] colListV = new String[] { "xh", "xm","xb","nj","xymc","zymc","bjmc"};
				String[] rsV = dao.getOneRs("select xh,xm,xb,nj,xymc,zymc,bjmc from view_xsjbxx where xh=?",new String[] { xh }, colListV);
				if (rsV != null) {
					for (int i = 0; i < colListV.length; i++) {
						map.put(colListV[i], rsV[i]);
					}
					// Ĭ��ѧ����ס����
					ldQshTem = dao.getOneRs("select lddm, qsh ,cwh,ssbh,ldmc from view_xszsxx where xh=?",
							new String[] { xh }, new String[] { "lddm", "qsh","cwh","ssbh" ,"ldmc"});
					if (ldQshTem != null) {
						map.put("lddm", (Base.isNull(ldQshTem[0]) ? "": ldQshTem[0]));
						map.put("qsh", (Base.isNull(ldQshTem[1]) ? "": ldQshTem[1]));
						map.put("cwh", (Base.isNull(ldQshTem[2]) ? "": ldQshTem[2]));
						map.put("ssbh", (Base.isNull(ldQshTem[3]) ? "": ldQshTem[3]));
						map.put("ldmc", (Base.isNull(ldQshTem[4]) ? "": ldQshTem[4]));
					}
				}
				cxtj.append(" where 1=1");
				if ("".equalsIgnoreCase(lddm) || lddm == null) {
					cxtj.append(" and 1=1");
				} else {
					if (Check_Input_Value.check2(lddm)) {
						cxtj.append(" and lddm='");
						cxtj.append(lddm);
						cxtj.append("'");
					}
				}
				sql = "select lddm,ldmc from sslddmb order by  lddm ";
				List ldList = dao.getList(sql, new String[] {}, new String[] {
						"lddm", "ldmc" });
				sql = "select wjlbdm,wjlbmc from gygl_zswjlbdmb";
				List wjlbList = dao.getList(sql, new String[] {}, new String[] {
						"wjlbdm", "wjlbmc" });
				if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {
					sql = "select cfdjfs,cfdjmc from hhgxy_cfdj";
					List cfdjList = dao.getList(sql, new String[] {},
							new String[] { "cfdjfs", "cfdjmc" });
					request.setAttribute("cfdjList", cfdjList);
					map.put("fs",map.get("yjfs"));
				}

				request.setAttribute("wjlbList", wjlbList);
				session.setAttribute("ldList", ldList);
				sql = "select distinct qsh from ssxxb " + cxtj;
				List ssList = dao.getList(sql, new String[] {},
						new String[] { "qsh" });
				request.setAttribute("ssList", ssList);
				request.setAttribute("userType", userType);
				request.setAttribute("xxdm", xxdm);
				if(!Base.isNull(xh)){
					String stuzsinfo = dao.returntag("select * from view_xszsxx where xh=?",new String[]{xh});
					if ("empty".equalsIgnoreCase(stuzsinfo)) {
						request.setAttribute("message", "notIn");//��ѧ����δס��
					}
				}

			} else if (realTable.equalsIgnoreCase("xsgzpxxxb")) {
				sql = "select pxxmdm xmdm,pxxmmc xmmc from xsgbpxxmdmb";
				List xmdmList = dao.getList(sql, new String[] {}, new String[] {
						"xmdm", "xmmc" });
				request.setAttribute("xmdmList", xmdmList);
			} else if (realTable.equalsIgnoreCase("nsepxxb")) {
				List<HashMap<String, String>> nsepList = new ArrayList<HashMap<String, String>>();
				List<HashMap<String, String>> nsepjbList = new ArrayList<HashMap<String, String>>();
				nsepjbList = dao.getList(
						"select nsepjbdm,nsepjbmc from nsepjbdmb",
						new String[] {},
						new String[] { "nsepjbdm", "nsepjbmc" });
				nsepList = dao.getList(
						"select nsepxmdm,nsepxmmc from nsepxmdmb",
						new String[] {},
						new String[] { "nsepxmdm", "nsepxmmc" });
				request.setAttribute("nsepjbList", nsepjbList);
				request.setAttribute("nsepList", nsepList);
			} else if (realTable.equalsIgnoreCase("ssydxxb")) {

//				List<HashMap<String, String>> ldList = new ArrayList<HashMap<String, String>>();
//				List<HashMap<String, String>> lcList = new ArrayList<HashMap<String, String>>();
//				List<HashMap<String, String>> ssList = new ArrayList<HashMap<String, String>>();
//				List<HashMap<String, String>> cwhList = new ArrayList<HashMap<String, String>>();
//				GetDropDownList getList = new GetDropDownList();
				String lddm = request.getParameter("lddm");
				String qsh = DealString.toGBK(request.getParameter("qsh"));
				String lc  = request.getParameter("lc");
				String cwh = request.getParameter("cwh");
				@SuppressWarnings("unused")
				String ssbh = dao.getOneRs("select ssbh from ssxxb where lddm=? and qsh=? and rownum=1 ",new String[]{lddm,qsh},"ssbh");
				if(!Base.isNull(pkValue)){
					sql = "select lddm,cs,ydhssbh ssbh,qsh,cwh from view_ssydxx where "+ pk + "=? and rownum=1";
					String[] ssInfo  = dao.getOneRs(sql, new String[] { pkValue },new String[]{"lddm","cs","ssbh","qsh","cwh"});
					if(ssInfo!=null){
						lddm = Base.isNull(ssInfo[0])?"":ssInfo[0];
						lc = Base.isNull(ssInfo[1])?"":ssInfo[1];				
						ssbh = Base.isNull(ssInfo[2])?"":ssInfo[2];
						qsh = Base.isNull(ssInfo[3])?"":ssInfo[3];
						cwh = Base.isNull(ssInfo[4])?"":ssInfo[4];
					}
				}

				/**
				sql = "select lddm,ldmc from sslddmb order by  lddm ";
				ldList = dao.getList(sql, new String[] {}, new String[] {
						"lddm", "ldmc" });								
				ssList = getList.GetQshList(lddm);              
              	lcList=getList.GetLcList(lddm);
                	ssList=getList.GetQshList2(lddm,lc);

                cwhList = getList.GetCwhList(ssbh);
				 */

//				StringBuffer cxtj = new StringBuffer();
//				cxtj.append(" where 1=1");
//				if ("".equalsIgnoreCase(lddm) || lddm == null) {
//				cxtj.append(" and 1=1");
//				} else {
//				if (Check_Input_Value.check2(lddm)) {
//				cxtj.append(" and lddm='");
//				cxtj.append(lddm);
//				cxtj.append("'");
//				}
//				}

//				sql = "select distinct qsh from ssxxb " + cxtj;
//				List ssList = dao.getList(sql, new String[] {},
//				new String[] { "qsh" });


//				if (lddm != null && !lddm.equalsIgnoreCase("") && qsh != null
//				&& !qsh.equalsIgnoreCase("")) {

//				sql = " select cws from ssxxb where ssbh = ? ";
//				String cws = dao.getOneRs(sql, new String[] {ssbh }, "cws");
//				for (int i = 1; i <= Integer.parseInt(cws); i++) {
//				HashMap<String, String> mapt = new HashMap<String, String>();
//				mapt.put("cwh", String.valueOf(i));
//				cwhList.add(mapt);
//				}
//				sql = " select cwh from xszsxxb where ssbh = ? ";
//				List sycwhList = dao.getList(sql, new String[] { ssbh },
//				new String[] { "cwh" });
//				cwhList.removeAll(sycwhList);
//				} else {
//				sql = "select distinct ydhssbh from view_ssydxx where "
//				+ pk + "=?";
//				String ssbh = dao.getOneRs(sql, new String[] { pkValue },
//				"ydhssbh");
//				sql = "select distinct cwh from cwxxb a where ssbh like ? and not exists (select * from xszsxxb b where a.ssbh=b.ssbh and a.cwh=b.cwh) order by cwh";
//				cwhList = dao.getList(sql, new String[] { ssbh },
//				new String[] { "cwh" });
//				sql = "select distinct cwh from view_ssydxx where " + pk
//				+ "=?";
//				String cwh = dao.getOneRs(sql, new String[] { pkValue },
//				"cwh");
//				HashMap<String, String> mapt = new HashMap<String, String>();
//				mapt.put("cwh", cwh);
//				cwhList.add(mapt);
//				}
				map.put("lddm",lddm);
				map.put("lc",lc);
				map.put("qsh",qsh);
				map.put("cwh",cwh);

				/**
				request.setAttribute("ldList",ldList);
				request.setAttribute("ssList",ssList);
				request.setAttribute("lcList",lcList);
				request.setAttribute("cwhList",cwhList);
				 */
				gyglDao.getLdLcQshList(request);
				if(doType.equalsIgnoreCase("add")){//���ʱ Ĭ��������ѧ��ѧ�����				
					map.put("xn", Base.currXn);
					map.put("xq", Base.currXq);
					map.put("nd", Base.currNd);
				}
			} else if (realTable.equalsIgnoreCase("bjjxhjb")) {
				String nj = dataSearchForm.getNj();
				String xy = dataSearchForm.getXydm();
				String zy = dataSearchForm.getZydm();
				if (xy == null) {
					xy = "";
				}
				if (zy == null) {
					zy = "";
				}
				sql = "select jxdm,jxmc from jxjxdmb";
				List xmdmList = dao.getList(sql, new String[] {}, new String[] {
						"jxdm", "jxmc" });
				request.setAttribute("jxList", xmdmList);
				request.setAttribute("xyList", dao.getXyList());
				request.setAttribute("zyList", dao.getZyList(xy));
				request.setAttribute("bjList", dao.getBjList(xy, zy, nj));
				request.setAttribute("urlV", request.getParameter("urlV")
						.replaceAll("!!-!!", "&"));
			} else if (realTable.equalsIgnoreCase("yxjxhjb")) {
				sql = "select jxdm,jxmc from jxjxdmb";
				List xmdmList = dao.getList(sql, new String[] {}, new String[] {
						"jxdm", "jxmc" });
				request.setAttribute("jxList", xmdmList);
				request.setAttribute("xyList", dao.getXyList());
			} else if (realTable.equalsIgnoreCase("wmqspbb")) {
				String clin = "��������";
				String lddm = request.getParameter("lddm");
				StringBuffer cxtj = new StringBuffer();
				String userType = request.getSession().getAttribute("userType").toString();
				String fzld = "";
				String userName = request.getSession().getAttribute("userName").toString();
				if(doType.equalsIgnoreCase("add")){//���ʱĬ��ϵͳ��������
					map.put("xn",Base.currXn);
					map.put("xq",Base.currXq);
				}
				//��Ԣ����Ա�ж�begin			
//				String lddmStr = gyglDao.getLddmxXx(userName);
//				String isGyFdy = "no";
//				if(!Base.isNull(lddmStr)){
//				lddm = lddmStr; 					
//				isGyFdy = "yes";
//				map.put("lddm", lddm);
//				}
//				request.setAttribute("isGyFdy","");
				//��Ԣ����Ա�ж�end	

				if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
					request.setAttribute("showhzy", "showhzy");
					// ֵ����Ա��¼��Ĭ����ֵ��¥��
					sql = "select szlddm from zbrydmb where zbrydm = ?";
					fzld = dao.getOneRs(sql, new String[] { userName },
					"szlddm");
					if (!("".equalsIgnoreCase(fzld) || fzld == null)) {
						lddm = fzld;
						map.put("lddm", fzld);
						userType = "xy";
					} 
				}
				if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)
						||xxdm.equalsIgnoreCase(Globals.XXDM_ZJJJZYJSXY)
						||xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)
						||Globals.XXDM_HHGXY.equalsIgnoreCase(xxdm)){//����ְҵ����ѧԺ���㽭����ְҵ����ѧԺ,������ѧԺ
					request.setAttribute("showlb","showlb");
				}

				if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJJZYJSXY)){
					clin = "�Ǽ���������";
				}
				if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
					request.setAttribute("showzgdzdx","showzgdzdx");
				}
				cxtj.append(" where 1=1");
				if ("".equalsIgnoreCase(lddm) || lddm == null) {
					cxtj.append(" and 1=1");
				} else {
					if (Check_Input_Value.check2(lddm)) {
						cxtj.append(" and lddm='");
						cxtj.append(lddm);
						cxtj.append("'");
					}
				}
				sql = "select lbdm,lbmc from qslbdmb";
				List qslbList = dao.getList(sql, new String[] {}, new String[] {
						"lbdm", "lbmc" });
				request.setAttribute("qslbList", qslbList);
//				sql = "select lddm,ldmc from sslddmb order by  lddm ";
//				List ldList = dao.getList(sql, new String[] {}, new String[] {
//				"lddm", "ldmc" });
				request.setAttribute("userType", userType);
//				request.setAttribute("ldList", ldList);
				sql = "select distinct qsh from ssxxb " + cxtj;
//				List ssList = dao.getList(sql, new String[] {},
//				new String[] { "qsh" });
//				request.setAttribute("ssList", ssList);
				request.setAttribute("xxdm", xxdm);
				request.setAttribute("userType",userType);
				request.setAttribute("clin", clin);
				gyglDao.getLdLcQshList(request);
			}else if(realTable.equalsIgnoreCase("zyzfwdjb")){
			   String xh = request.getParameter("xh");
			   if(!Base.isNull(xh)){
				   map =  CommonQueryDAO.getStuInfo(xh);
			   }
			   if(map.get("xn")==null){
				   map.put("xn",Base.currXn);
			   }
			   if(map.get("nd")==null){
				   map.put("nd",Base.currNd);
			   }
			   if(map.get("xq")==null){
				   map.put("xq",Base.currXq);
			   }
			   String cjzyzfwsj =  CommonQueryDAO.dao_getInfo("zyzfwdjb",null," where xh='"+map.get("xh")+"' and rownum=1 and cjzyzfwsj is not null ").get("cjzyzfwsj");
			   map.put("cjzyzfwsj",Base.isNull(cjzyzfwsj)?"":cjzyzfwsj);
			}else if (realTable.equalsIgnoreCase("lpxxb")) {
				sql = "select bxgsdm,bxgsmc from bxgsdmb";
				List bxgsList = dao.getList(sql, new String[] {}, new String[] {
						"bxgsdm", "bxgsmc" });				
				sql = "select bxxzdm,bxxzmc from bxxzb where 1=1";

				List bxxzList = dao.getList(sql, new String[] {}, new String[] {
						"bxxzdm", "bxxzmc" });
				if(xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)){
					if(doType.equalsIgnoreCase("modi")||doType.equalsIgnoreCase("view")){
						sql = "select bxxzdm,hffy,lpje from view_lpxx where xh||bxgsdm||nd||slrq=?";
						List bxfyList = dao.getList(sql, new String[]{pkValue}, new String[]{"bxxzdm","hffy","lpje"});
						request.setAttribute("bxfyList", bxfyList);
						if(bxfyList!=null){
							request.setAttribute("bxfyNum", bxfyList.size());
						}						
					}
					if(bxxzList!=null){
						request.setAttribute("bxxzNum", bxxzList.size());
					}
				}
				request.setAttribute("bxgsList", bxgsList);
				request.setAttribute("bxxzList", bxxzList);
			} else if (realTable.equalsIgnoreCase("gdnz_lpxxb_bx")
					|| realTable.equalsIgnoreCase("gdnz_lpxxb_sh")) {
				// �㶫Ů��������Ϣ
				sql = "select bxgsdm,bxgsmc from bxgsdmb";
				List bxgsList = dao.getList(sql, new String[] {}, new String[] {
						"bxgsdm", "bxgsmc" });
				sql = "select dcdm,dcmc from gdnzzy_bxdcdmb";
				List bxdcList = dao.getList(sql, new String[] {}, new String[] {
						"dcdm", "dcmc" });
				request.setAttribute("bxdcList", bxdcList);
				if (realTable.equalsIgnoreCase("gdnz_lpxxb_sh")) {
					request.setAttribute("showXfzrx", "showXfzrx");
				}
				request.setAttribute("bxgsList", bxgsList);
			}
			List xlzxjsxm = null;
			if(realTable.equalsIgnoreCase("xlzxqkb")){
				sql = "SELECT zxxxm FROM xljk_zxsxxb";
				xlzxjsxm = dao.getList(sql, new String[] {}, new String[] {
				"zxxxm" });
			}
			String xsdy = request.getParameter("xsdy");
			String xsjbxx = request.getParameter("xsjbxx");
			String a = request.getQueryString();
			String b = request.getRequestURL().toString();
			b = b.substring(b.lastIndexOf('/'), b.length());
			b = b + "?" + a;
			request.setAttribute("url", b);
			request.setAttribute("pkValue", pkValue);// ���ͱ�����
			request.setAttribute("doType", doType);
			request.setAttribute("njList", Base.getNjList());// �����꼶�б�
			request.setAttribute("xnList", Base.getXnndList());// ����ѧ���б�
			request.setAttribute("xqList", Base.getXqList());// ����ѧ���б�
			request.setAttribute("xyList", Base.getXyList());// ����ѧԺ�б�
			request.setAttribute("xlzxjsxm",xlzxjsxm);// ������ѯʦ�����б�

			request.setAttribute("rs", map);
//			if ((xxdm.equalsIgnoreCase(Globals.XXDM_SZXXZYJSXY)  || xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX))
//			&& ((realTable.equalsIgnoreCase("ybdyxxb")) || (realTable.equalsIgnoreCase("dyxxb")))) {
//			newFwd = new ActionForward("/sxjy/szxxdj/" + realTable + ".jsp", false);
//			} else
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)
					&& realTable.equalsIgnoreCase("zhszcp")) {
				newFwd = new ActionForward("/added/syzy_zhszcp.jsp", false);
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)
					&& tableName.equalsIgnoreCase("view_gdnz_lpxx")) {
				newFwd = new ActionForward("/qtsj/gdnzzy/gdnz_lpxxb.jsp");
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_GZCJXY)
					&& realTable.equalsIgnoreCase("xfcjb")) {// ���ݳǽ�ѧ�Ѵ߽�
				newFwd = new ActionForward("/rcsw/gzcj/xfcjb.jsp");
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_GZCJXY)
					&& realTable.equalsIgnoreCase("xfhjb")) {// ���ݳǽ�ѧ�ѻ���
				newFwd = new ActionForward("/rcsw/gzcj/xfhjb.jsp");
			} 
			// ==========begin ���ΰ 2009/4/8 ==================
			else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)
					&& realTable.equalsIgnoreCase("zgkd_rdsqb")) {// �й�����뵳����
				newFwd = new ActionForward("/dtjs/zgkd/zgkd_rdsqb.jsp");
			}
			// ==========end ���ΰ 2009/4/8 ==================
			else {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
					request.setAttribute("showXbemy", "showXbemy");
				}else if (StringUtils.isEqual(xxdm, Globals.XXDM_AHJZGYXY)) {
					request.setAttribute("isAHJG", "yes");
				}
				if(xxdm.equalsIgnoreCase(Globals.XXDM_GZCJXY) 
						&& realTable.equalsIgnoreCase("wjcfb")){
					request.setAttribute("isCSMZ", "yes");
				}
				if("xsdy".equals(xsdy)){
					realTable = "xlytqkxsb";
				}
				if("xsjbxx".equals(xsjbxx)){
					realTable = "gzxsjbxsyyqkb";
				}
				if (Globals.XXDM_ZGMSXY.equalsIgnoreCase(xxdm)) {
					if (realTable.equalsIgnoreCase("xsrychb")) {
						String rychmc = map.get("rychmc");
						if (!StringUtils.isNull(map.get("rychdm"))) {
							HashMap<String, String> rychMap = dao
							.getMapNotOut(
									"select byjyqx,mzpyqksm,jcqk,(select b.brjl from xsrychxxb b where a.xh=b.xh) brjl,(select b.hjqk from xsrychxxb b where a.xh=b.xh) hjqk,(select b.zysj from xsrychxxb b where a.xh=b.xh) zysj,(select b.drzw from xsrychxxb b where a.xh=b.xh) drzw,b.zzmmmc,b.mzmc,b.syd,b.jtdz from xsrychb a left join view_xsxxb b on a.xh=b.xh where a.xh=? and a.xn=? and a.xq=? and a.rychdm=?",
									new String[] { map.get("xh"),
											map.get("xn"),
											map.get("xq"),
											map.get("rychdm") });
							map.put("byjyqx", rychMap.get("byjyqx"));
							map.put("mzpyqksm", rychMap.get("mzpyqksm"));
							map.put("jcqk", rychMap.get("jcqk"));
							map.put("drzw", rychMap.get("drzw"));
							map.put("zzmmmc", rychMap.get("zzmmmc"));
							map.put("mzmc", rychMap.get("mzmc"));
							map.put("syd", rychMap.get("syd"));
							map.put("jtdz", rychMap.get("jtdz"));
							map.put("brjl", rychMap.get("brjl"));
							map.put("hjqk", rychMap.get("hjqk"));
							map.put("zysj", rychMap.get("zysj"));

						}
						if (rychmc.contains("Ժ�ű�ҵ��")) {
							request.setAttribute("info", "yes");
							request.setAttribute("yybys", "yes");
						}
						if (rychmc.contains("ʡ�ű�ҵ��")) {
							request.setAttribute("sybys", "yes");
							request.setAttribute("info", "yes");
						}
					}
				}
				newFwd = new ActionForward("/sjcz/" + realTable + ".jsp", false);
			}

		}
		return newFwd;
	}

	private ActionForward stuInfoXX(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		YnysQszService qszService=new YnysQszService();
		ActionForward newFwd = null;
		CommanForm dataSearchForm = (CommanForm) form;
//		StudentInfoForm stuForm = new StudentInfoForm();
		StuInfoDAO stuDao = new StuInfoDAO();
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		xbemyStudentInfoDAO xbDao = new xbemyStudentInfoDAO();
		StudentInfoDao studao = new StudentInfoDao();		
		HashMap<String, String> map = new HashMap<String, String>();
		HashMap<String, String> map2 = new HashMap<String, String>();// ѧ��������Ϣ,���½�βΪ1�Ķ�Ϊ��ѯѧ��������Ϣ����
		String from = request.getParameter("from");
		String xh = request.getParameter("xh");
		String page = request.getParameter("page");
		String xxdm = StandardOperation.getXxdm();
		// redirect���ڷ��뵽����ĺ���
		String redirect = request.getParameter("redirect");
		String variable = request.getParameter("variable");
		String oper = request.getParameter("oper");
		String xydm = dataSearchForm.getXydm();
		String zydm = dataSearchForm.getZydm();
		String nj = dataSearchForm.getNj();	
		String pkval = DealString.toGBK(request.getParameter("pkval"));
		request.setAttribute("oper", "add");
		request.setAttribute("doType", oper);
		request.setAttribute("njList", dao.getNjList());
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("zyList", dao.getZyList(xydm));
		request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));
		request.setAttribute("mzList", dao.getMzList());
		request.setAttribute("zzmmList", dao.getZzmmList());
		request.setAttribute("ndList", Base.getXnndList());	
		request.setAttribute("xjztList", stuDao.getXjztList());
		request.setAttribute("qsz", qszService.getQsz(xh));//		���ҳ�
		String userName2 = (String) session.getAttribute("userName");//���ʴ�ѧ
		boolean isSssXs = false;
		
		if(from.equalsIgnoreCase("/shgc/stu_info/stu_archives_info.jsp")){
			return new ActionForward("/stu_archives_now.do?doType=add&type=add&xh="+xh, false);
		}
		
		if(from.equalsIgnoreCase("/dtjs/zgdzdx/wlxx/wlxxUpdate.jsp")){
			return new ActionForward("/zgddWlxx.do?method=wlxxUpdate&doType=stuInfo",false);
		}
		if(from.equalsIgnoreCase("/jxgl/nblg/xsjxcxgbxx.jsp")){
			JxglDAO jxglDao = new JxglDAO();
			request.setAttribute("ldList", jxglDao.getLdList("", ""));
			request.setAttribute("xnList", jxglDao.getXnndList());
		}
		if (from.equalsIgnoreCase("/jxgl/gt/jxglStuInfoOne.jsp")) {
			JxglDAO jxglDao = new JxglDAO();

			request.setAttribute("ldList", jxglDao.getNextMinJz());
		}
		if(from.equalsIgnoreCase("/sjcz/xlytqkb.jsp")){
			JxglDAO jxglDao = new JxglDAO();
			request.setAttribute("ldList", jxglDao.getLdList("", ""));
			request.setAttribute("xnList", jxglDao.getXnndList());
		}
		if(from.equalsIgnoreCase("/shgc/stu_info/stu_info_modify.jsp")){
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
				StuInfoDAO stuDAO = new StuInfoDAO();
				List<HashMap<String, String>> xsztList = stuDAO
				.getXsztList("��ѧ��");
				request.setAttribute("xsztList", xsztList);
			}
		}
		if(from.equalsIgnoreCase("/sjcz/dyxxb.jsp")||from.equalsIgnoreCase("/sjcz/ybdyxxb.jsp")||from.equalsIgnoreCase("/sjcz/rdjjfzxxb.jsp")){//��Ա���
			String mySql = "select xsccdm, xsccmc from dtjs_xsccb";
			List<HashMap<String, String>> temp = dao.getList(mySql,new String[]{},new String[]{"xsccdm","xsccmc"});
			request.setAttribute("xsccList",temp );// ����dtjs_xsccb�б�
			request.setAttribute("xxdm",xxdm );// ����dtjs_xsccb�б�
		}
		if(from.equalsIgnoreCase("/pjpy/zjsyzy/szjf.jsp")){//�㽭��ҵְҵ���ʻ���ά��
			return new ActionForward("/pjpy_zjsyzy.do?method=addSzjf",false);
		}
		if(from.equalsIgnoreCase("/qgzx/zgdzdx/qgzxzxsq.jsp")){
			//�й����ʴ�ѧ�ڹ���ѧ֮������
			return new ActionForward("/qgzxZgdzdx.do?method=qgzxzx",false);
		}
		if(from.equalsIgnoreCase("/qgzx/zgdzdx/gwfp.jsp")){
			return new ActionForward("/qgzxZgdzdx.do?method=showGwfp",false);
		}
		if(from.equalsIgnoreCase("/qgzx/zgdzdx/qgzxsq.jsp")){
			return new ActionForward("/qgzxZgdzdx.do?method=qgzxsq",false);
		}
		if(from.equalsIgnoreCase("/qgzx/zgdzdx/xwgwsq.jsp")){
			return new ActionForward("/qgzxZgdzdx.do?method=showGwsq",false);
		}
		if(from.equalsIgnoreCase("/added/syzy_zhszcp.jsp")){
			//�㽭��ҵְҵ����ѧԺ
			return new ActionForward("/pjpy_zjsyzy.do?method=getZhszcpStuinfo");
		}
		if(from.equalsIgnoreCase("/xsxx/zgkd/stu_family_zgkd.jsp")){
			//�й���ҵ��ѧѧ����ͥ��Ϣ
			return new ActionForward("/xsxx_zgkd.do?method=showStuFamily");
		}
		if(from.equalsIgnoreCase("/xsxx/zgdzdx/zgdzdx_stu_modinfo.jsp")){
			return new ActionForward("/xsxxZgdzdx.do?method=stuModiInfo",false);
		}
		if(from.equalsIgnoreCase("/xsxx/zgkd/zgkd_stu_modinfo.jsp")){
			//�й���ҵ��ѧѧ����Ϣ
			return new ActionForward("/xsxx_zgkd.do?method=stuModiInfo",false);
		}

		if (from.equalsIgnoreCase("/wjcf/hygxy/gzjygl/add.jsp")) {
			return new ActionForward("/wjcf_hygxy_gzjyadd.do?pkval=" + pkval + "&xh=" + xh, false);
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
			isSssXs = GyglShareDAO.isSssAdmin(userName2);
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){//�人����ѧ
			if(from.equals("/pjpy/whlgdx/whlgdx_pjpy_apply.jsp")){//��ѧ������
				return new ActionForward("/pjpy_whlgdx.do?method=jxjApply",false);
			}
			if(from.equalsIgnoreCase("/pjpy/whlgdx_rych_apply.jsp")){//�����ƺ�����
				return new ActionForward("/pjpy_whlgdx.do?method=rychApply",false);
			}
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY) && from.equalsIgnoreCase("/sjcz/xfcjb.jsp")){
			String pkValue = request.getParameter("pkValue");
			return new ActionForward("/rcswXfcj.do?method=getHjInfo&xh="+xh+"&pkValue=" + pkValue,false);
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
			request.setAttribute("ddqkList", xbDao.getDDqkList());
		}
		if (from.equalsIgnoreCase("/shgc/stu_info/stu_info_modify.jsp")
				|| from.equalsIgnoreCase("/shgc/stu_info/stu_info.jsp")) {
			request.setAttribute("ydlbList", dao.getYdlbList());			
			request.setAttribute("ydxh", studao.getMaxYdxh());
		}
		if (from.equalsIgnoreCase("/sjcz/xlzxqkb.jsp")) {
			List xlzxjsxm = null;
			if(from.equalsIgnoreCase("/sjcz/xlzxqkb.jsp")){
				String sql = "SELECT zxxxm FROM xljk_zxsxxb";
				xlzxjsxm = dao.getList(sql, new String[] {}, new String[] {
				"zxxxm" });
			}
			request.setAttribute("xlzxjsxm",xlzxjsxm);// ������ѯʦ�����б�
		}

		if(from.equalsIgnoreCase("/xsxx/csmzzyjsxy/stu_hkzt_add.jsp") || from.equalsIgnoreCase("/xsxx/csmzzyjsxy/stu_hkzt_mod.jsp")){
			StudentInfoCsmzService service = new StudentInfoCsmzService();
			request.setAttribute("hkztList", service.getHkztList());
		}
		if (from.equalsIgnoreCase("/rcsw/gzcj/xfcjb.jsp")) {
			String sql = "";
			sql = "select * from view_xfcjxx where xh=?";
			List xfcjList = dao.getList(sql, new String[] { xh }, new String[] {
					"xn", "je", "qflxmc", "bz" });

			sql = "select hjje,hjqx from view_xfhjxx where xh=?";
			List xfhjList = dao.getList(sql, new String[] { xh }, new String[] {
					"hjje", "hjqx" });
			if (xh == null || "".equalsIgnoreCase(xh)) {
				request.setAttribute("xfcjList", new ArrayList());
				request.setAttribute("xfhjList", new ArrayList());
			} else {
				request.setAttribute("xfcjList", xfcjList);
				request.setAttribute("xfhjList", xfhjList);
			}
		}
		// ================= begin �㽭��ýѧԺ ���ΰ 2009/3/5 ======================
		if (from.equalsIgnoreCase("/dtjs/zjcm/fzdxOne.jsp")) {

			DtjszjcmDAO zjcmDao = new DtjszjcmDAO();
			String sql = "";
			sql = "select xh, nd, xm, xn, xb,xq, nj, kssj, xymc, "
				+ "zymc, bjmc, bz ,xsccmc from view_rdjjfzxx where xh = ? ";
			String[] colList = new String[] { "xh", "nd", "xm", "xn", "xb",
					"xq", "nj", "kssj", "xymc", "zymc", "bjmc", "bz", "xsccmc" };

			HashMap<String, String> rsZjcm = zjcmDao.swclQueryOne(colList, xh,
					map, sql);
			if (xh == null || "".equalsIgnoreCase(xh)) {
				request.setAttribute("rsZjcm", new HashMap<String, String>());
			} else {
				request.setAttribute("rsZjcm", rsZjcm);
			}
		}
		if (from.equalsIgnoreCase("/dtjs/zjcm/swclShq.jsp")) {

			DtjszjcmService service = new DtjszjcmService();
			SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
			String time = f.format(new Date());
			String title = "���Ž���-����ά��-����������";

			List<HashMap<String, String>> cl = service.getClList();

			request.setAttribute("title", title);
			request.setAttribute("sssj", time);
			request.setAttribute("clList", cl);

		}
		if (from.equalsIgnoreCase("/dtjs/zjcm/dnjcShq.jsp")) {

			DtjszjcmService service = new DtjszjcmService();
			String title = "���Ž���-����ά��-���ڽ�������";

			// ��ý�������
			List<HashMap<String, String>> lx = service.getJclxList();
			// ��ý�������
			List<HashMap<String, String>> ly = service.getJclyList();

			request.setAttribute("title", title);
			request.setAttribute("lxList", lx);
			request.setAttribute("lyList", ly);

		}
		// ================= end �㽭��ýѧԺ ���ΰ 2009/3/5 =====================
		if (from.equalsIgnoreCase("/jxgl/gt/jxcxgbOne.jsp")) {
			return new ActionForward("/jxglgt.do?method=forwardPage&gb=xh&xh="
					+ xh, false);
		}
		// ================= begin ������ ���ΰ 2009/4/8======================
		if (from.equalsIgnoreCase("/jxgl/nblg/nblg_xsgbOne.jsp")) {
			JxglJzService service = new JxglJzService();
			// ���������Ϣ
			List<HashMap<String, String>> ldList = service.getLdList();

			request.setAttribute("ldList", ldList);
		}
		// ================= end ������ ���ΰ 2009/4/8 ======================
		// ================= begin �人����ѧ����ѧԺ ���ΰ 2009/5/31======================
		if (from.equalsIgnoreCase("/pjpy/whlghxxy/rychsq.jsp")) {
			//PjpyHxxyService service = new PjpyHxxyService();
//			String sql = "select t.*, b.mzmc, b.wydj, b.csrq, b.lxdh, b.rxrq from "
//			+ " view_xsrychb t, view_xsxxb b where t.xh = b.xh and t.xh=?";
			String sql = "select b.mzmc, b.wydj, b.csrq, b.lxdh, b.zzmmmc,b.rxrq from "
				+ " view_xsxxb b where b.xh=?";
//			map = dao.getMap(sql, new String[] { xh }, new String[] { "xh",
//			"xm", "xb", "nj", "xymc", "zymc", "bjmc", "mzmc", "wydj",
//			"csrq", "lxdh", "rxrq" });
			map = dao.getMap(sql, new String[] { xh }, new String[] { "mzmc", "wydj","zzmmmc",
					"csrq", "lxdh", "rxrq" });
			String xn = Base.getJxjsqxn();//��ѧ������ѧ��
			String nd = Base.getJxjsqnd();//��ѧ���������
			//String xq = Base.getJxjsqxq();//��ѧ������ѧ��
			CommonAction com = new CommonAction();
			com.appendRychList(request);// ��ҳ����������ƺ��б�
			map.put("nd", nd);
			map.put("xn", xn);		
		}
		// ================= end �人����ѧ����ѧԺ ���ΰ 2009/5/31 ======================
		// ================= begin �й�����ѧԺ ���ΰ 2009/5/31 ======================
		if (from.equalsIgnoreCase("/zgmsxy_xszz.do?method=gjzxdksq")) {
			String sql = "select a.lxdh, a.jtzz, a.yzbm, a.fqxm, a.fqysr, a.fqdh, a.fqgzdw,"
				+ " a.mqxm, a.mqysr, a.mqdh, a.mqgzdw,b.sqje from zgmsxy_gjzxdkgrxx a,"
				+ " zgmsxy_gjzxdkdkxx b where a.xh = b.xh and a.xh=?";
			map = dao.getMap(sql, new String[] { xh }, new String[] { "lxdh",
					"jtzz", "yzbm", "fqxm", "fqysr", "fqdh", "fqgzdw", "mqxm",
					"mqysr", "mqdh", "mqgzdw", "sqje" });
			map.put("nd", xh);
		}
		// ================= end �й�����ѧԺ ���ΰ 2009/5/31 ======================
		if (from.equalsIgnoreCase("/qtsj/gdnzzy/gdnz_lpxxb.jsp")) {
			// �㶫Ů������ bxgsdm,bxdcList
			List bxgsList = dao.getList("select bxgsdm,bxgsmc from bxgsdmb",
					new String[] {}, new String[] { "bxgsdm", "bxgsmc" });
			List bxdcList = dao.getList("select dcdm,dcmc from gdnzzy_bxdcdmb",
					new String[] {}, new String[] { "dcdm", "dcmc" });
			String type = request.getParameter("type");
			if (type != null && !type.equalsIgnoreCase(""))
				request.setAttribute("showXfzrx", "showXfzrx");
			request.setAttribute("bxgsList", bxgsList);
			request.setAttribute("bxdcList", bxdcList);
			writeAble = CheckPower.checkUsrPower(session.getAttribute(
			"userName").toString(), "newStuInsureApply.do") ? "yes"
					: "no";
		}
		if (from.equalsIgnoreCase("/sjcz/xsbxb.jsp")) {
			if (xxdm.equalsIgnoreCase(Globals.XXDM_GDNZZYJSXY)) {//�㶫Ů��
				request.setAttribute("showGdnz", "showGdnz");
				List bxdcList = dao.getList(
						"select dcdm,dcmc from gdnzzy_bxdcdmb",
						new String[] {}, new String[] { "dcdm", "dcmc" });
				request.setAttribute("bxdcList", bxdcList);
			}
		}
		if (from.equalsIgnoreCase("/sjcz/kkqkb.jsp")) {
			String sqls = "";
			List kcList = null;
			if (xxdm.equalsIgnoreCase(Globals.XXDM_SZXXZYJSXY)) {
				sqls = "select distinct kcdm,kcmc from bks_kcdm@dblink_jw";
				kcList = dao.getList(sqls, new String[] {}, new String[] {
						"kcdm", "kcmc" });
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_GZCJXY)) {
				String bjdm = dao.getOneRs(
						"select bjdm from view_xsjbxx where xh=?",
						new String[] { xh }, "bjdm");
				kcList = dao.getKcList("gzcj_kcxx", bjdm, dao.getConf(0), dao
						.getConf(1));
				request.setAttribute("isGZCJXY", "yes");
			} else {
				sqls = "select distinct kcdm,kcmc from bks_kcdm";
				kcList = dao.getList(sqls, new String[] {}, new String[] {
						"kcdm", "kcmc" });
			}
			request.setAttribute("kcList", kcList);
		}
		if (from.equalsIgnoreCase("/sjcz/nsepxxb.jsp")) {
			List<HashMap<String, String>> nsepList = new ArrayList<HashMap<String, String>>();
			List<HashMap<String, String>> nsepjbList = new ArrayList<HashMap<String, String>>();
			nsepjbList = dao.getList("select nsepjbdm,nsepjbmc from nsepjbdmb",
					new String[] {}, new String[] { "nsepjbdm", "nsepjbmc" });
			nsepList = dao.getList("select nsepxmdm,nsepxmmc from nsepxmdmb",
					new String[] {}, new String[] { "nsepxmdm", "nsepxmmc" });
			request.setAttribute("nsepjbList", nsepjbList);
			request.setAttribute("nsepList", nsepList);
		}
		if (from.equalsIgnoreCase("/added/syzy_zhszcp.jsp")) {
			request.setAttribute("scoreList", dao.getScore(5));
		}
		if ("true".equalsIgnoreCase(redirect)
				&& "ydinfo".equalsIgnoreCase(variable)) {
			if ("stuinfo".equalsIgnoreCase(page)) {
				newFwd = StandardOperation.getStudetailInfo(mapping, form,
						request, response, from, xh, variable);
				return newFwd;
			}
			if ("xsxx".equalsIgnoreCase(page)) {
				return newFwd = StandardOperation.getStudetailInfo(mapping,
						form, request, response, from, xh, "xsxx");
			}
			if ("archives".equalsIgnoreCase(page)) {
				return newFwd = StandardOperation.getStudetailArchives(mapping,
						form, request, response, from, xh, variable);
			}
			if("stustatus".equalsIgnoreCase(page)){
				List ydlbList = dao.getList("select distinct ydlbm ydlbdm,ydlbmc from dm_ydlb", new String[]{}, new String[]{"ydlbdm","ydlbmc"});
				request.setAttribute("rs",xbemyStudentInfoDAO.getStuInfo(xh));
				request.setAttribute("ydlbList",ydlbList);
				return new ActionForward(from, false);
			}
			newFwd = StandardOperation.getXjydStuInfo(mapping, form, request,
					response, from, xh, variable);
			return newFwd;
		}
		if ("true".equalsIgnoreCase(redirect) && variable != null) {
			newFwd = StandardOperation.getStuInfo(mapping, form, request,
					response, from, xh, variable);
			return newFwd;
		}

		String tempSql = "select ydlb from bks_stu_info_xjydb where xh=?";
		String xszt = "";
		String[] xsztl = dao.getOneRs(tempSql, new String[] { xh },new String[] { "ydlb" });
		if ((xsztl == null) || ("��ѧ".equalsIgnoreCase(xsztl[0]))) {
			tempSql = "select ydlb from bks_stu_info_xjydb where xh=?";
			xsztl = dao.getOneRs(tempSql, new String[] { xh }, new String[] { "ydlb" });
			if (xsztl == null) {
				xszt = "�ڶ�";
			} else {
				xszt = xsztl[0];
			}
		} else {
			xszt = xsztl[0];
		}
		String infoListString = request.getParameter("getStuInfo");
		String[] infoList = infoListString.split("-");
		String sql = "";
		String sql1 = "";
		String[] infoList1 = null;
		if(!isSssXs){
			sql = "select v.xh,v.xm,v.xb,v.nj,v.xydm,v.xymc,v.zydm,v.zymc,v.bjdm,v.bjmc,v.ssbh,v.qsdh,v.lxdzxx," +
			"v.lxdh,v.sjhm,v.mm,v.sfzh,a.xszt,v.xz," +
			"(select a.mz from view_xsxxb a where a.xh=v.xh)mzdm," +
			"(select a.mzmc from view_xsxxb a where a.xh=v.xh)mzmc," +
			"(select a.csrq from view_xsxxb a where a.xh=v.xh)csrq," +
			"(select a.zzmmmc from view_stu_details a where a.xh = v.xh) zzmmmc," +
			"(select a.jg from view_xsxxb a where a.xh=v.xh) jg," + 
			"(select a.kh from view_stu_details a where a.xh = v.xh) kh," +
			"v.xzb,v.ssbh,v.sjhm,v.lxdzxx from view_xsjbxx v," +
			"(select '" + xszt + "' xszt from dual) a where upper(v.xh)=?";
			// ѧ��������Ϣ��ѯ
			sql1 = "select XH,XM,NJ,ZYMC,xymc,BJMC,LXDH1,LXDH2,SJHM,EMAIL,JTSZD,HKSZD,JKZK,JTCY1_XM,JTCY1_GX," +
			"JTCY1_ZY,JTCY1_GZDZ,JTCY1_ZW,JTCY1_SFZH,JTCY1_LXDH1,JTCY1_LXDH2,JTCY2_XM,JTCY2_GX,JTCY2_ZY," +
			"JTCY2_GZDZ,JTCY2_ZW,JTCY2_SFZH,JTCY2_LXDH1,JTCY2_LXDH2,JTCY3_XM,JTCY3_GX,JTCY3_ZY,JTCY3_GZDZ," +
			"JTCY3_ZW,JTCY3_SFZH,JTCY3_LXDH1,JTCY3_LXDH2,SFKNS,BRTXDZ,YB from view_xsfzxx where upper(xh)=?";
			//ѧ��������Ϣ�ֶ�
			infoList1 = ("XH,XM,NJ,ZYMC,xymc,BJMC,LXDH1,LXDH2,SJHM,EMAIL,JTSZD,HKSZD,JKZK,JTCY1_XM,JTCY1_GX," +
					"JTCY1_ZY,JTCY1_GZDZ,JTCY1_ZW,JTCY1_SFZH,JTCY1_LXDH1,JTCY1_LXDH2,JTCY2_XM,JTCY2_GX," +
					"JTCY2_ZY,JTCY2_GZDZ,JTCY2_ZW,JTCY2_SFZH,JTCY2_LXDH1,JTCY2_LXDH2,JTCY3_XM,JTCY3_GX," +
			"JTCY3_ZY,JTCY3_GZDZ,JTCY3_ZW,JTCY3_SFZH,JTCY3_LXDH1,JTCY3_LXDH2,SFKNS,BRTXDZ,YB").toLowerCase().split(",");
		}else{
			sql = "select v.xh,v.xm,v.xb,v.nj,v.xydm,v.xymc,v.zydm,v.zymc,v.bjdm,v.bjmc" +
			" from sss_xxb v where upper(v.xh)=?";
			// ѧ��������Ϣ��ѯ
			sql1 = "select v.xh,v.xm,v.xb,v.nj,v.xydm,v.xymc,v.zydm,v.zymc,v.bjdm,v.bjmc" +
			" from sss_xxb v where upper(v.xh)=?";
			//ѧ��������Ϣ�ֶ�
			infoList1 = ("XH,XM,NJ,ZYMC,xymc,BJMC").toLowerCase().split(",");
		}



		String[] stuInfo1 = dao.getOneRs(sql1, new String[] { xh.toUpperCase() }, infoList1);// ѧ����ͥ��Ϣ
		String[] stuInfo = null;
		if(infoList != null && infoList.length>0 && StringUtils.isNotNull(infoList[0]) ){
			stuInfo = dao.getOneRs(sql, new String[] { xh.toUpperCase() }, infoList);//ѧ��������Ϣ
		}
		String[] dqnqd = dao.getOneRs("select dqxn,dqxq,dqnd from xtszb",
				new String[] {}, new String[] { "dqxn", "dqxq", "dqnd" });
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
			String hzysql = "select shhdxzdm,shhdxzmc from shhdxzdmb";
			List shhdxzList = dao.getList(hzysql, new String[] {},
					new String[] { "shhdxzdm", "shhdxzmc" });
			request.setAttribute("shhdxzList", shhdxzList);
			request.setAttribute("showhzy", "showhzy");
		}
		// ========== begin ���ΰ 2009/4/9 =============== lyl
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			String zdy = dao.getOneRs(
					"select zdy from view_nblg_xsbz where xh = ?",
					new String[] { xh }, "zdy");
			String jgmc = dao.getOneRs(
					"select jgmc from view_nblg_xsbz where xh = ?",
					new String[] { xh }, "jgmc");
			String ldmc = dao.getOneRs(
					"select ldmc from view_nblg_xsbz where xh = ?",
					new String[] { xh }, "ldmc");
			request.setAttribute("nb", "nb");
			request.setAttribute("zdy", zdy);
			request.setAttribute("jgmc", jgmc);
			request.setAttribute("ldmc", ldmc);
		}
		// ========== end ���ΰ 2009/4/9 ===============
		// ========== begin ���ΰ 2009/4/10 ===============
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			String rtsj = dao.getOneRs(
					"select t.jrgqtsj from xsxxb t where t.xh = ?",
					new String[] { xh }, "jrgqtsj");
			map.put("jrgqtsj", rtsj);
		}
		// ========== end ���ΰ 2009/4/10 ===============
		// ========== begin ���ΰ 2009/5/19 ===============
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)) {
			String pkValue = "";
			if (request.getParameter("pkval") != null
					&& !"".equals(request.getParameter("pkval"))) {
				pkValue = request.getParameter("pkval").replace(" ", "");
			}
			String userType = request.getSession().getAttribute("userType")
			.toString();

			GetDropDownList getList = new GetDropDownList();
			String lc = null;
			String lddm = null;
			String ssbh = "";
			String sql2 = "";

			List<HashMap<String, String>> ldList = new ArrayList<HashMap<String, String>>();
			List<HashMap<String, String>> lcList = new ArrayList<HashMap<String, String>>();
			List<HashMap<String, String>> qshList = new ArrayList<HashMap<String, String>>();
			List<HashMap<String, String>> cwhList = new ArrayList<HashMap<String, String>>();

			sql2 = "select lddm,ldmc from sslddmb ";
			sql2 += " order by  lddm ";

			ldList = dao.getList(sql2, new String[] {}, new String[] { "lddm",
			"ldmc" });
			lcList = getList.GetLcList(lddm,"");
			qshList = getList.GetQshList2(lddm, lc,"");
			cwhList = getList.GetCwhList(ssbh);

			HashMap<String, String> tempmap = dao
			.getMap(
					"select * from view_hh_gygl_jqlxsq t where t.xn||t.xq||t.xh = ?",
					new String[] { pkValue }, new String[] { "xn",
							"xq", "lxyy", "zskssj", "zsjssj", "lxdh" });
			HashMap<String, String> tempmap1 = dao.getMap(
					"select * from xszsxxb where xh=?", new String[] { xh },
					new String[] { "ssbh", "cwh" });
			ssbh = tempmap1.get("ssbh");

			String yssbh = "";
			if (ssbh != null && !"".equals(ssbh)) {
				String cwh = tempmap1.get("cwh");
				String bh[] = ssbh.split("-");
				yssbh = bh[0] + "��¥" + bh[1] + "/" + cwh + "��";
			}

			String lxdh = tempmap.get("lxdh");
			String lxyy = tempmap.get("lxyy");
			String zskssj = tempmap.get("zskssj");
			String zsjssj = tempmap.get("zsjssj");

			map.put("yssbh", yssbh);
			map.put("xq", tempmap.get("xq"));
			map.put("xn", tempmap.get("xn"));
			map.put("lxdh", lxdh);
			map.put("lxyy", lxyy);
			map.put("zskssj", zskssj);
			map.put("zsjssj", zsjssj);
			request.setAttribute("userType", userType);
			request.setAttribute("xxdm", xxdm);
			request.setAttribute("ldList", ldList);
			request.setAttribute("lcList", lcList);
			request.setAttribute("qshList", qshList);
			request.setAttribute("cwhList", cwhList);
		}
		// ========== end ���ΰ 2009/5/19 ===============
		// ========== begin ���ΰ 2009/5/13 ===============
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)) {
			String rdsqsj = dao.getOneRs(
					"select t.djsqsj from zgkd_rdsqb t where t.xh = ?",
					new String[] { xh }, "djsqsj");
			String qdwjjfzsj = dao.getOneRs(
					"select t.rdjjfzsj from zgkd_rdsqb t where t.xh = ?",
					new String[] { xh }, "rdjjfzsj");
			String fzdxsj = dao.getOneRs(
					"select t.fzdxsj from zgkd_rdsqb t where t.xh = ?",
					new String[] { xh }, "fzdxsj");
			map.put("rdsqsj", rdsqsj);
			map.put("qdwjjfzsj", qdwjjfzsj);
			map.put("fzdxsj", fzdxsj);
		}
		// ========== end ���ΰ 2009/5/13 ===============
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)) {
			request.setAttribute("showzszy", "showzszy");
		}
		if (stuInfo == null || stuInfo.length<1) {
			map.put("stuExists", "no");
			map.put("xh", "");
		} else {
			for (int i = 0; i < infoList.length; i++) {
				request.setAttribute(infoList[i], stuInfo[i]);
				map.put(infoList[i], stuInfo[i]);
			}
			map.put("stuExists", "yes");
			map.put("xh", xh);
			dataSearchForm.setXh(xh);
		}
		if (stuInfo1 == null) {
			map2.put("stuExists", "no");
			map2.put("xh", "");
		} else {
			for (int i = 0; i < infoList1.length; i++) {
				request.setAttribute(infoList1[i], stuInfo1[i]);
				map2.put(infoList1[i], stuInfo1[i]);
			}
		}
		if (from.equalsIgnoreCase("/sjcz/xspxxxb.jsp")) {
			sql = "select pxxmdm xmdm,pxxmmc xmmc from pxxmdmb";
			List xmdmList = dao.getList(sql, new String[] {}, new String[] {
					"xmdm", "xmmc" });
			request.setAttribute("xmdmList", xmdmList);
		} else if (from.equalsIgnoreCase("/shgc/stu_info/stu_archives.jsp")) {
			// ========== begin ���ΰ 2009/6/3 ===============
			String mz = dao.getOneRs(
					"select t.mzmc from view_xsxxb t where t.xh = ?",
					new String[] { xh }, "mzmc");
			String bynd = dao.getOneRs(
					"select t.bynd from jygl_xsjbxxb t where t.xsxh = ?",
					new String[] { xh }, "bynd");
			String sfzh = dao.getOneRs(
					"select t.id from jygl_xsjbxxb t where t.xsxh = ?",
					new String[] { xh }, "id");
			map.put("sfzh", sfzh);
			map.put("bynf", bynd);
			map.put("mzmc", mz);
			// ========== end ���ΰ 2009/6/3 ===============
		} else if(from.equalsIgnoreCase("/pjpy/zjjd/gybxf/gybxfOne.jsp")){
			String ssbh = dao.getOneRs(
					"select t.ssbh from view_xszsxx t where t.xh = ?",
					new String[] { xh }, "ssbh");
			map.put("ssbh", ssbh);
		} else if (from.equalsIgnoreCase("/sjcz/rdjjfzxxb.jsp")
				|| from.equalsIgnoreCase("/sjcz/ybdyxxb.jsp")
				|| from.equalsIgnoreCase("/sjcz/jxjgb.jsp")) {
			request.setAttribute("ynList", dao.getChkList(2));
		} else if (from.equalsIgnoreCase("/sjcz/jszhkpb.jsp")) {
			request.setAttribute("scoreList", dao.getScore(5));
		} else if (from.equalsIgnoreCase("/sjcz/xsjxhjb.jsp")) {
			sql = "select jxdm,jxmc from jxjxdmb";
			List xmdmList = dao.getList(sql, new String[] {}, new String[] {
					"jxdm", "jxmc" });
			request.setAttribute("jxList", xmdmList);
		} else if (from.equalsIgnoreCase("/sjcz/zhszcp.jsp")) {
			if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {
				String[] opList = new String[] { "zgdy", "zgybdy", "xjbj",
						"wmss", "xsgb1", "xsgb2", "xsgb3", "xsgb4", "wysp",
						"jsjsp", "xjjxj1", "xjjxj2", "xjjxj3", "gjjl1",
						"gjjl2", "gjjl3", "sjjl1", "sjjl2", "sjjl3",
						"xjgr1", "xjgr2", "zf" }; 
				String[] pfList = dao.getOneRs("select * from sjdxspfxzb where xh=?", new String[]{xh}, opList);
				if (pfList != null && pfList.length==22) {
					for (int i=0;i<opList.length;i++) {
						map.put(opList[i], pfList[i]);
					}
				}
				String[] xqS = dao.getOneRs("select xh,xz,nj,((select to_number(a.dqnd) from xtszb a)-to_number(nj))*2 zxq,(select b.dqxq from xtszb b) dqxq from view_xsjbxx where xh = ?", new String[]{xh}, new String[]{"zxq", "dqxq"});
				if (xqS != null && xqS.length == 2) {
					int iXq = 0;
					if (!StringUtils.isNull(xqS[1]) && StringUtils.isEqual(xqS[1], "02")) {
						iXq = StringUtils.isNull(xqS[0]) ? 0 : (Integer.parseInt(xqS[0]) + 1);
					}
					if (!StringUtils.isNull(xqS[1]) && StringUtils.isEqual(xqS[1], "01")) {
						iXq = StringUtils.isNull(xqS[0]) ? 0 : Integer.parseInt(xqS[0]);
					}
					map.put("zxq", iXq + "");//��ȡѧ���ۼ�ѧ����
				}

				request.setAttribute("isAHJG", "yes");
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)) {//�㶫����ѧԺ
				request.setAttribute("isgdby", "yes");
			}
			/*if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {//������
				String jqf = "0";
				String sqls = "select trunc((sum(zxf) / sum(xf)),2) jqf from (select distinct a.xn,a.xh,a.kcmc," +
				"to_number(a.cj) cj,a.xf,trunc((cj * a.xf),2) zxf  from cjb a  " +
				"where a.bkcj is null and a.cxcj is null and (a.cxbj is null or a.cxbj = '0') " +
				"and a.kcxz <> 'У��ѡ�޿�'  and a.kcxz <> 'У��ѡ�޿�' and a.xh = ? and a.xn = ?) " +
				"group by xh, xn";

				jqf = dao.getOneRs(sqls, new String[]{xh,dao.getOneRs("select jxjsqxn from xtszb", new String[]{}, "jxjsqxn")}, "jqf");
				jqf = jqf == null || jqf.equalsIgnoreCase("") ? "0" : jqf;
				map.put("kcjqpfj", jqf);
				request.setAttribute("shownbng", "yes");
			} */
			request.setAttribute("scoreList", dao.getScore(5)); //lyl
		} else if (from.equalsIgnoreCase("/sjcz/nsepxxb.jsp")) {
			sql = "select nsepjbdm,nsepjbmc from nsepjbdmb";
			List nsepjbList = dao.getList(sql, new String[] {}, new String[] {
					"nsepjbdm", "nsepjbmc" });
			request.setAttribute("nsepjbList", nsepjbList);
		} else if (from.equalsIgnoreCase("/sjcz/xlcsjgb.jsp")) {
			sql = "select xlcsxmdm,xlcsxmmc from xlcsxmdmb";
			List csxmList = dao.getList(sql, new String[] {}, new String[] {
					"xlcsxmdm", "xlcsxmmc" });
			request.setAttribute("csxmList", csxmList);
		} else if (from.equalsIgnoreCase("/sjcz/xytbgxxsxxb.jsp")) {
			sql = "select tbgxxslbdm,tbgxxslbmc from tbgxxslbdmb";
			List tbgxxslbdmList = dao.getList(sql, new String[] {},
					new String[] { "tbgxxslbdm", "tbgxxslbmc" });
			sql = "select lydm,lymc from xg_xljk_tsxslydmb";
			List tsxslyList = dao.getList(sql, new String[] {},
					new String[] { "lydm", "lymc" });
			
			request.setAttribute("tbgxxslbdmList", tbgxxslbdmList);
			request.setAttribute("tsxslyList", tsxslyList);
		} else if(from.equalsIgnoreCase("/shgc/stu_info/xbemy/stuTransferApp.jsp")){			
			sql = "select xh,xm,xb,mz mzdm,mzmc,rxrq,lxdh,xymc zcxymc,xydm zcxydm,zymc zczymc,zydm zczydm,bjdm zcbjdm,nj zcnj,xz zcxz,pycc zcxlcc,(select xxmc from xtszb)zcxxmc,'ת��' zxlx from view_xsxxb where xh=?";
			map = dao.getMap(sql, new String[]{xh}, new String[]{"xh","xm","xb","mzdm","mzmc","rxrq","lxdh","zcxxmc","zcxymc","zczymc","zcnj","zcxz","zcxlcc","zxlx","zcxydm","zczydm","zcbjdm"});
		} else if (from.equalsIgnoreCase("/sjcz/wjcfb.jsp")
				|| from.equalsIgnoreCase("/sjcz/wjcflsb.jsp")) {
			List cflbList = null;
			if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
				sql = "select cflbdm,cflbmc from cflbdmb where cflbmc = ? or cflbmc = ? or cflbmc = ? or cflbmc = ? or cflbmc = ?";
				cflbList = dao.getList(sql, new String[] {"����", "���ؾ���", "�ǹ�", "��У�쿴", "����ѧ��"}, new String[] {
						"cflbdm", "cflbmc" });
			}else{
				sql = "select cflbdm,cflbmc from cflbdmb";
				cflbList = dao.getList(sql, new String[] {}, new String[] {
						"cflbdm", "cflbmc" });
			}
			request.setAttribute("cflbList", cflbList);
			sql = "select cfyydm,cfyymc from cfyydmb";
			List cfyyList = dao.getList(sql, new String[] {}, new String[] {
					"cfyydm", "cfyymc" });
			request.setAttribute("cfyyList", cfyyList);
			String clwh = StandardOperation.getWjcfClwh(xxdm);
			if ("".equalsIgnoreCase(clwh) || clwh == null) {
				request.setAttribute("clwh", "���ѧ[]��");
			} else {
				request.setAttribute("clwh", clwh);
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_XBEMY)){
				request.setAttribute("showXbemy", "showXbemy");
			}
		} else if (from.equalsIgnoreCase("/sjcz/xsbxb.jsp") || from.equalsIgnoreCase("/qtsj/shgc/tbxxAdd.jsp")
				|| from.equalsIgnoreCase("/qtsj/shgc/tbsqAdd.jsp")) {
			sql = "select bxgsdm,bxgsmc from bxgsdmb";
			List tbgsdmList = dao.getList(sql, new String[] {}, new String[] {"bxgsdm", "bxgsmc" });
			request.setAttribute("tbgsdmList", tbgsdmList);

			sql = "select bxxzdm,bxxzmc from bxxzb";
			List tbxzdmList = dao.getList(sql, new String[] {}, new String[] {"bxxzdm", "bxxzmc" });
			request.setAttribute("tbxzdmList", tbxzdmList);
			request.setAttribute("bxxzList", tbxzdmList);
			request.setAttribute("bxxzNum", tbxzdmList.size());
			List nxList = dao.getNxList();
			request.setAttribute("nxList", nxList);
		} else if (from.equalsIgnoreCase("/sjcz/xshsxfb.jsp")) {
			request.setAttribute("yfList", dao.getYfList());
		} else if (from.equalsIgnoreCase("/sjcz/xsjxjb.jsp")) {
			String userType = "";
			if (session == null) {
				request.setAttribute("errMsg", "sadfsdf");
				return mapping.findForward("false");
			}
			userType = session.getAttribute("userOnLine").toString();

			String jxjdm = request.getParameter("xmdm");
			if (jxjdm != null) {
				sql = "select jxjlb,jlje from jxjdmb where jxjdm=?";
				String[] jxjInfo = dao.getOneRs(sql, new String[] { jxjdm },
						new String[] { "jxjlb", "jlje" });
				if (jxjInfo != null) {
					map.put("jxjlb", jxjInfo[0]);
					map.put("jlje", jxjInfo[1]);
				}
			}
			sql = "select * from jxjdmb";
			List jxjList = dao.getList(sql, new String[] {}, new String[] {
					"jxjdm", "jxjmc" });
			request.setAttribute("jxjList", jxjList);
			sql = "select jxjsqxn,jxjsqnd from xtszb where rownum=1";
			String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
					"jxjsqxn", "jxjsqnd" });
			map.put("xn", tmp[0]);
			map.put("nd", tmp[1]);
			map.put("stuExists", "yes");
			map.put("userType", userType);
		} else if (from.equalsIgnoreCase("/sjcz/xsrychb.jsp")) {
			String userType = "";
			if (session == null) {
				request.setAttribute("errMsg", "sadfsdf");
				return mapping.findForward("false");
			}
			userType = session.getAttribute("userOnLine").toString();

			sql = "select * from rychdmb";
			List rychList = dao.getList(sql, new String[] {}, new String[] {
					"rychdm", "rychmc" });
			request.setAttribute("rychList", rychList);
			sql = "select jxjsqxn,jxjsqnd from xtszb where rownum=1";
			String[] tmp = dao.getOneRs(sql, new String[] {}, new String[] {
					"jxjsqxn", "jxjsqnd" });
			HashMap<String, String> stuMap = dao.getMapNotOut("select lxdh,jtdz,syd from view_xsxxb where xh=?", new String[]{xh});
			map.putAll(stuMap);
			map.put("xn", tmp[0]);
			map.put("nd", tmp[1]);
			map.put("stuExists", "yes");
			map.put("userType", userType);
		} else if (from.equalsIgnoreCase("/sjcz/xsbzxxb.jsp")) {
			sql = "select bzlxdm,bzlxmc from bzlxdmb";
			List bzlxList = dao.getList(sql, new String[] {}, new String[] {
					"bzlxdm", "bzlxmc" });
			request.setAttribute("bzlxList", bzlxList);
		} else if (from.equalsIgnoreCase("/sjcz/xsgbxxb.jsp")) {
			sql = "select rzbmdm,rzbmmc from rzbmdmb";
			List rzbmList = dao.getList(sql, new String[] {}, new String[] {
					"rzbmdm", "rzbmmc" });
			request.setAttribute("rzbmList", rzbmList);
			request.setAttribute("chkList", dao.getChkList(3));
		} else if (from.equalsIgnoreCase("/sjcz/sthdxxb.jsp")) {
			sql = "select stdm,stmc from stdmb";
			List stList = dao.getList(sql, new String[] {}, new String[] {
					"stdm", "stmc" });
			request.setAttribute("stList", stList);
		} else if (from.equalsIgnoreCase("/pjpy/szyqxy/zhszcp/5s_Add.jsp")){
			PjpySzyqxyZhszcpDAO pjpyDAO = new PjpySzyqxyZhszcpDAO();
			map.put("xn", Base.currXn);
			map.put("xq", pjpyDAO.getXqmc(Base.currXq));
		} else if (from.equalsIgnoreCase("/sjcz/xszsxxb.jsp")) {
			String xb = "";
			String userName = session.getAttribute("userName").toString();
			String userDep = session.getAttribute("userDep").toString();				
			String userType = dao.getUserType(userDep);
			boolean isSss = GyglShareDAO.isSssAdmin(userName);
			if(stuInfo != null){
				xb = stuInfo[1];
			}

//			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {
//			if(isSss){
//			sql = " select ssbh from view_ssxx where (fpbj = '˶ʿ' or fpbj = '��ʿ') and (xbxd=? or xbxd='���')order by ssbh ";
//			request.setAttribute("xslx", "sss");
//			}else{
//			sql = " select ssbh from view_ssxx where fpbj = 'һ��' and (xbxd=? or xbxd='���') order by ssbh ";
//			}
//			}else{
//			sql = " select ssbh from view_ssxx where fpbj = 'һ��' and (xbxd=? or xbxd='���') order by ssbh ";
//			}

//			���������б�begin
			StringBuffer querry = new StringBuffer("  ");
			if(!"xy".equalsIgnoreCase(userType)){//ѧԺ�������û�
				querry.append(" and 1=2 ");
			}
			querry.append((Base.isNull(xb))?"":" and (xbxd = '" + xb + "' or xbxd='���') ");//��ѯ������Ϣ����					
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)) {//�й����ʴ�ѧ
				if(isSss){
					sql = "select distinct a.ssbh dm,b.yqmc||b.ldmc||b.qsh mc from ssfpb a,view_ssxx b where (fpbj = '˶ʿ' or fpbj = '��ʿ') and a.ssbh=b.ssbh "+querry+" order by a.ssbh ";				
					request.setAttribute("xslx", "sss");
				}else{					
					sql = "select distinct a.ssbh dm,b.yqmc||b.ldmc||b.qsh mc from ssfpb a,view_ssxx b where fpbj='һ��' and a.ssbh=b.ssbh "+querry+" order by a.ssbh ";
				}
			}else{						
				//�ѻ���ѧԺ����
				if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){//����ְҵ����ѧԺ
//					querry.append((Base.isNull(map.get("bjdm")))?" and 1=2 ":" and bjdm='"+map.get("bjdm")+"' ");
					sql = "select distinct a.ssbh dm,a.ldmc||a.qsh mc from view_ssxx a,ssfpb b where a.ssbh=b.ssbh and a.fpbj='һ��' and bjdm=(select bjdm from view_xsxxb where xh='"+xh+"' and rownum=1 ) "+querry;
				}else{
//					querry.append((Base.isNull(map.get("nj")))?"":" and nj='"+map.get("nj")+"' ");
//					querry.append((Base.isNull(map.get("xydm"))?" and 1=2 ":" and xydm='"+map.get("xydm")+"' "));
					sql = " select distinct a.ssbh dm,a.ldmc||a.qsh mc from view_ssxx a,ssfpb b where a.ssbh=b.ssbh and fpbj = 'һ��' and nj||xydm=(select nj||xydm from view_xsxxb where xh='"+xh+"' and rownum=1 ) " + querry
					+ " order by a.ssbh ";
				}
			}
			List ssList = dao.getList(sql, new String[] {},
					new String[] { "dm","mc" });			
			request.setAttribute("ssList", ssList);
			////���������б�end	


//			List ssList = dao.getList(sql, new String[] { xb },
//			new String[] { "ssbh" });
			request.setAttribute("ssList", ssList);
			request.setAttribute("cwhList", new ArrayList());
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)
					||xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
				request.setAttribute("showhzy", "showhzy");
			}
			if(Globals.XXDM_XCXY.equals(xxdm)){
				String xsnj = map.get("nj");
				sql = "select rzsj from njrzsj where nj=?";
				String rzsj = dao.getOneRs(sql, new String[]{xsnj}, "rzsj");
				request.setAttribute("rzsj", rzsj!=null ? rzsj:"");
			}
			request.setAttribute("userType", userType);
		} else if (from.equalsIgnoreCase("/sjcz/xszffb.jsp")
				|| from.equalsIgnoreCase("/sjcz/hcyhkffb.jsp")
				|| from.equalsIgnoreCase("/sjcz/xhffb.jsp")
				|| from.equalsIgnoreCase("/sjcz/xszbbb.jsp")
				|| from.equalsIgnoreCase("/sjcz/hcyhkbbb.jsp")
				|| from.equalsIgnoreCase("/sjcz/yktbbb.jsp")
				|| from.equalsIgnoreCase("/sjcz/xhbbb.jsp")
				|| from.equalsIgnoreCase("/sjcz/hcyhkczb.jsp")) {
			sql = "select jbrgh,jbrxm from jbrxxb order by jbrgh";
			List jbrList = dao.getList(sql, new String[] {}, new String[] {
					"jbrgh", "jbrxm" });
			request.setAttribute("jbrList", jbrList);
			request.setAttribute("ynList", dao.getChkList(2));
			map.put("xn", dqnqd[0]);
			map.put("xq", dqnqd[1]);
			map.put("nd", dqnqd[2]);
			List<HashMap<String, String>> qflxList = new ArrayList<HashMap<String, String>>();
			qflxList = dao.getList("select qflxdm,qflxmc from qflxdmb",
					new String[] {}, new String[] { "qflxdm", "qflxmc" });
			request.setAttribute("qflxList", qflxList);
		} else if (from.equalsIgnoreCase("/sjcz/modi_stu_info.jsp")) {
			request.setAttribute("xh", xh);
			if(xxdm.equalsIgnoreCase(Globals.XXDM_DBLYDX)|| xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)){
				//������ҵ��ѧ
				return new ActionForward("/stu_info_add.do?method=modiStuInfo");
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZBDX)) {
				//�б���ѧ
				String ssbh = "";
				sql = "select ssbh from xszsxxb where xh=?";
				String[] outValue = dao.getOneRs(sql, new String[] { xh },
						new String[] { "ssbh" });
				if (outValue == null) {
					map2.put("ssbh", "");
				} else {
					ssbh = outValue[0];
					map2.put("ssbh", ssbh);
				}

				String qsdh = "";
				sql = "select qsdh from zxbz_xsssxx where ssbh=?";
				outValue = dao.getOneRs(sql, new String[] { ssbh },
						new String[] { "qsdh" });
				if (outValue == null) {
					map2.put("qsdh", "");
				} else {
					qsdh = outValue[0];
					map2.put("qsdh", qsdh);
				}
				request.setAttribute("rs1", map2);

				return mapping.findForward("zbdx_xsxx_modi");
			}
			if(xxdm.equalsIgnoreCase(Globals.XXDM_NCDXKJXY)){
				//�ϲ��Ƽ�
//				StuInfoDAO stuDao = new StuInfoDAO();
				String userName = request.getSession().getAttribute("userName").toString();				
				if(stuDao.isFdy(userName)){
					if(!stuDao.stuIsOwnFdy(xh, userName)){
						request.setAttribute("cWrite", "no");
					}
				}
			}
		} else if (from.equalsIgnoreCase("/xszxjxjhzb.do")) {
			from = "/sqb/xszxjxjhzb.jsp";
			String tips = "�������� - ��� - ר�ѧ����� - ����";
			String dqnd = dao.getOneRs("select dqnd from xtszb",
					new String[] {}, new String[] { "dqnd" })[0];
			List xyList = dao.getXyList();
			String sql2 = "";
			sql2 = "select jxjdm zxjxjdm,jxjmc zxjxjmc from jxjdmb where JXJLB='��'";
			List zxjxjList = dao.getList(sql2, new String[] {}, new String[] {
					"zxjxjdm", "zxjxjmc" });
			ArrayList<HashMap<String, String>> ndfwList = new ArrayList<HashMap<String, String>>();
			for (int i = 7; i > 0; i--) {
				HashMap<String, String> map1 = new HashMap<String, String>();
				String ndstr = (new Integer((new Integer(dqnd)).intValue() - i)
				.toString())
				+ "-"
				+ (new Integer((new Integer(dqnd)).intValue() - i + 1)
				.toString());
				map1.put("ndfw", ndstr);
				ndfwList.add(map1);
			}
			request.setAttribute("xyList", xyList);
			request.setAttribute("zxjxjList", zxjxjList);
			request.setAttribute("ndfwList", ndfwList);
			request.setAttribute("tips", tips);
			request.setAttribute("realTable", "xszxjxjhzb");
			request.setAttribute("pk", "xh||zxjdm||ndfw");
			request.setAttribute("url", "xszxjxjhzb.do");
			dataSearchForm.setXydm(map.get("xydm"));
		} else if (from.equalsIgnoreCase("/xszz/wszxjsq.jsp")) {
			String tips = "";
			tips = "��ǰ����λ�ã�ѧ������ - ���� - ������ѧ������";
			List wszxjList = dao.getList(
					"select zxjdm wszxjdm,zxjmc wszxjmc from wszxjdmb",
					new String[] {}, new String[] { "wszxjdm", "wszxjmc" });
			request.setAttribute("tips", tips);
			request.setAttribute("wszxjList", wszxjList);
		} else if (from.equalsIgnoreCase("/xszz/szxx_wszxjsq.jsp")) {
			String tips = "";
			tips = "��ǰ����λ�ã�ѧ������ - ���� - ������ѧ������";
			List wszxjList = dao.getList(
					"select zxjdm wszxjdm,zxjmc wszxjmc from wszxjdmb",
					new String[] {}, new String[] { "wszxjdm", "wszxjmc" });
			request.setAttribute("tips", tips);
			request.setAttribute("wszxjList", wszxjList);
			List zmclList = dao.getList(
					"select dm zmcldm,mc zmclmc from zmcldmb", new String[] {},
					new String[] { "zmcldm", "zmclmc" });
			request.setAttribute("zmclList", zmclList);
		} else if (from.equalsIgnoreCase("/xszz/xndxjSq.jsp")) {
			String tips = "";
			tips = "��ǰ����λ�ã�ѧ������ - ���� - У�ڴ�ѧ������";
			request.setAttribute("tips", tips);
			request.setAttribute("sfksq", "yes");
		} else if (from.equalsIgnoreCase("/sqb/hzjy_xssq.jsp")) {
			String pk = "xh||xn||sfbk";
			String realTable = "hzjysqb";
			String[] xtnqd = dao.getOneRs(
					"select dqxn,dqxq,dqnd from xtszb where rownum=1",
					new String[] {}, new String[] { "dqxn", "dqxq", "dqnd" });

			sql = "select * from view_hzjysqxx a where xh=?";
			// ѧ���������ѧ��
			String[] ysqxn = dao.getOneRs(
					"select distinct xn from view_hzjysqxx where xh=?",
					new String[] { xh }, new String[] { "xn" });
			// String sql2 = "select * from view_hzjysqxx a where xh=? and
			// xn=(select max(xn) from view_hzjysqxx b where a.xh=b.xh)";
			if (ysqxn != null && Arrays2.search(ysqxn, xtnqd[0]) >= 0) {
				sql = "select * from view_hzjysqxx a where xh=? and xn=(select max(xn) from view_hzjysqxx b where a.xh=b.xh)";
			}
			String[] cols = dao
			.getColumnName("select * from view_hzjysqxx where 1=2");
			for (int i = 0; i < cols.length; i++) {
				cols[i] = cols[i].toLowerCase();
			}
			String[] rs = dao.getOneRs(sql, new String[] { xh }, cols);
			for (int i = 0; i < rs.length; i++) {
				map.put(cols[i], rs[i] == null ? "" : rs[i]);
			}
			request.setAttribute("pk", pk);
			request.setAttribute("sfksq", true);
			request.setAttribute("realTable", realTable);
			request.setAttribute("rs", map);
		} else if (from.equalsIgnoreCase("/shgc/hzjy_bggzsq.jsp")) {
			sql = "select xh,xm,zymc,bjmc,xymc,gzdwqc ygzdwmc,lxr ygzdwlxr,lxdh ygzdwlxdh from view_hzjybgxx where xh=?";
			String[] cols = { "xh", "xm", "zymc", "bjmc", "xymc", "ygzdwmc",
					"ygzdwlxr", "ygzdwlxdh" };
			String[] rsVals = dao.getOneRs(sql, new String[] { xh }, cols);
			HashMap<String, String> map22 = new HashMap<String, String>();
			for (int i = 0; i < cols.length; i++) {
				map22.put(cols[i],((rsVals != null) && (rsVals[i] != null) ? rsVals[i]: ""));
			}
			request.setAttribute("rs", map22);
			request.setAttribute("sfksq", true);
		} else if (from.equalsIgnoreCase("/sjcz/xsgzpxxxb.jsp")) {
			sql = "select pxxmdm xmdm,pxxmmc xmmc from xsgbpxxmdmb";
			List xmdmList = dao.getList(sql, new String[] {}, new String[] {
					"xmdm", "xmmc" });
			request.setAttribute("xmdmList", xmdmList);
		} else if (from.equalsIgnoreCase("/sjcz/xfcjb.jsp")
				|| from.equalsIgnoreCase("/sjcz/kkqkb.jsp")
				|| from.equalsIgnoreCase("/rcsw/gzcj/xfcjb.jsp")) {
			map.put("xn", dqnqd[0]);
			map.put("xq", dqnqd[1]);
			map.put("nd", dqnqd[2]);
			List<HashMap<String, String>> qflxList = new ArrayList<HashMap<String, String>>();
			qflxList = dao.getList("select qflxdm,qflxmc from qflxdmb",
					new String[] {}, new String[] { "qflxdm", "qflxmc" });
			request.setAttribute("qflxList", qflxList);
		} else if (from.equalsIgnoreCase("/xszz/knbz.jsp")) {
			request.setAttribute("sfksq", true);
			request.setAttribute("bzlb", "lsknbz");
			request.setAttribute("tips", "��ǰ����λ�ã�ѧ������ - ���� - ��ʱ���Ѳ���");
		} else if (from.equalsIgnoreCase("/sjcz/ssydxxb.jsp")) {
//			List<HashMap<String, String>> ldList = new ArrayList<HashMap<String, String>>();
//			List<HashMap<String, String>> lcList = new ArrayList<HashMap<String, String>>();
//			List<HashMap<String, String>> ssList = new ArrayList<HashMap<String, String>>();
//			List<HashMap<String, String>> cwhList = new ArrayList<HashMap<String, String>>();
//			GetDropDownList getList = new GetDropDownList();
			String lddm = request.getParameter("lddm");
			String qsh = DealString.toGBK(request.getParameter("qsh"));
			@SuppressWarnings("unused")
			String lc  = request.getParameter("lc");
			@SuppressWarnings("unused")
			String cwh = request.getParameter("cwh");
			String pkValue = request.getParameter("pkValue");
			@SuppressWarnings("unused")
			String ssbh = dao.getOneRs("select ssbh from ssxxb where lddm=? and qsh=? and rownum=1 ",new String[]{lddm,qsh},"ssbh");
			if(!Base.isNull(pkValue)){
				sql = "select lddm,cs,ydhssbh ssbh,qsh,cwh from view_ssydxx where xh||ydsj=? and rownum=1";
				String[] ssInfo  = dao.getOneRs(sql, new String[] { pkValue },new String[]{"lddm","cs","ssbh","qsh","cwh"});
				if(ssInfo!=null){
					lddm = Base.isNull(ssInfo[0])?"":ssInfo[0];
					lc = Base.isNull(ssInfo[1])?"":ssInfo[1];				
					ssbh = Base.isNull(ssInfo[2])?"":ssInfo[2];
					qsh = Base.isNull(ssInfo[3])?"":ssInfo[3];
					cwh = Base.isNull(ssInfo[4])?"":ssInfo[4];
				}
			}
//			sql = "select lddm,ldmc from sslddmb order by  lddm ";
//			ldList = dao.getList(sql, new String[] {}, new String[] {
//			"lddm", "ldmc" });								
//			ssList = getList.GetQshList(lddm);              
//			lcList=getList.GetLcList(lddm);
//			ssList=getList.GetQshList2(lddm,lc);

//			cwhList = getList.GetCwhList(ssbh);
//			request.setAttribute("ldList",ldList);
//			request.setAttribute("ssList",ssList);
//			request.setAttribute("lcList",lcList);
//			request.setAttribute("cwhList",cwhList);
			gyglDao.getLdLcQshList(request);
			String zsxxsql = "select ssbh ydqssbh,cwh ydqcwh,rzrq ydqrzsj,sfbz from view_xszsxx where xh=?";
			String[] stuzsinfo = dao.getOneRs(zsxxsql, new String[] { xh },
					new String[] { "ydqssbh", "ydqcwh", "ydqrzsj","sfbz" });
			if (stuzsinfo == null) {
				request.setAttribute("message", "notIn");//��ѧ����δס��
				request.setAttribute("result", "view");
			} else {
				map.put("ydqssbh", stuzsinfo[0]);
				map.put("ydqcwh", stuzsinfo[1]);
				map.put("ydqrzsj", stuzsinfo[2]);
				map.put("ydqsfbz",stuzsinfo[3]);
			}
		} else if (from.equalsIgnoreCase("/sjcz/bbsqb.jsp")) {
			String bblx = request.getParameter("bblx");
			return new ActionForward("/bbsqb.do?bblx=" + bblx + "&xh=" + xh,
					false);
		} else if (from.equalsIgnoreCase("/sjcz/xsjlb.jsp")) {
			String lddm = request.getParameter("lddm");
			String userType = request.getParameter("userType");
			String fzld = "";
			List ldList = null;
			String userName = request.getSession().getAttribute("userName")
			.toString();
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				sql = "select szlddm from zbrydmb where zbrydm = ?";
				fzld = dao.getOneRs(sql, new String[] { userName }, "szlddm");
				if ("".equalsIgnoreCase(fzld) || fzld == null) {
					sql = "select lddm,ldmc from sslddmb order by  lddm ";
					ldList = dao.getList(sql, new String[] {}, new String[] {
							"lddm", "ldmc" });
				} else {
					lddm = fzld;
					sql = "select lddm,ldmc from sslddmb where lddm =? order by  lddm ";
					ldList = dao.getList(sql, new String[] { fzld },
							new String[] { "lddm", "ldmc" });
				}
			} else {
				sql = "select lddm,ldmc from sslddmb order by  lddm ";
				ldList = dao.getList(sql, new String[] {}, new String[] {
						"lddm", "ldmc" });
			}

			StringBuffer cxtj = new StringBuffer();
			cxtj.append(" where 1=1");
			if ("".equalsIgnoreCase(lddm) || lddm == null) {
				cxtj.append(" and 1=1");
			} else {
				if (Check_Input_Value.check2(lddm)) {
					cxtj.append(" and lddm='");
					cxtj.append(lddm);
					cxtj.append("'");
				}
			}

			sql = "select distinct qsh from ssxxb " + cxtj;
			List ssList = dao.getList(sql, new String[] {},
					new String[] { "qsh" });

			request.setAttribute("ssList", ssList);
			request.setAttribute("ldList", ldList);
			request.setAttribute("tabName", "xsjlb");
			request.setAttribute("userType", userType);
		} else if (from.equalsIgnoreCase("/sjcz/xscfb.jsp")) {
			String lddm = request.getParameter("lddm");
			String userType = request.getParameter("userType");
			String fzld = "";
			List ldList = null;
			String userName = request.getSession().getAttribute("userName")
			.toString();
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
				sql = "select szlddm from zbrydmb where zbrydm = ?";
				fzld = dao.getOneRs(sql, new String[] { userName }, "szlddm");
				if ("".equalsIgnoreCase(fzld) || fzld == null) {
					sql = "select lddm,ldmc from sslddmb order by  lddm ";
					ldList = dao.getList(sql, new String[] {}, new String[] {
							"lddm", "ldmc" });
				} else {
					lddm = fzld;
					sql = "select lddm,ldmc from sslddmb where lddm =? order by  lddm ";
					ldList = dao.getList(sql, new String[] { fzld },
							new String[] { "lddm", "ldmc" });
				}
			} else {
				sql = "select lddm,ldmc from sslddmb order by  lddm ";
				ldList = dao.getList(sql, new String[] {}, new String[] {
						"lddm", "ldmc" });
			}
			StringBuffer cxtj = new StringBuffer();
			cxtj.append(" where 1=1");
			if ("".equalsIgnoreCase(lddm) || lddm == null) {
				cxtj.append(" and 1=1");
			} else {
				if (Check_Input_Value.check2(lddm)) {
					cxtj.append(" and lddm='");
					cxtj.append(lddm);
					cxtj.append("'");
				}
			}
			sql = "select distinct qsh from ssxxb " + cxtj;
			List ssList = dao.getList(sql, new String[] {},
					new String[] { "qsh" });
			request.setAttribute("ssList", ssList);
			request.setAttribute("ldList", ldList);
			request.setAttribute("tabName", "xscfb");
			request.setAttribute("userType", userType);
		} else if (from.equalsIgnoreCase("/shgc/xsgygl_xszsxx.jsp")) {
			sql = "select lddm,ldmc from sslddmb order by  lddm ";
			List ldList = dao.getList(sql, new String[] {}, new String[] {
					"lddm", "ldmc" });
			sql = "select distinct xh,xm from view_xszsxx where lddm=''";
			List lzList = dao.getList(sql, new String[] {}, new String[] {
					"xh", "xm" });
			sql = "select distinct xh,xm from view_xszsxx where ssbh=''";
			List qszList = dao.getList(sql, new String[] {}, new String[] {
					"xh", "xm" });
			request.setAttribute("lzList", lzList);
			request.setAttribute("qszList", qszList);
			request.setAttribute("ldList", ldList);
		} else if (from.equalsIgnoreCase("/sqb/gwsq.jsp")) {
			sql = "select gwdm,gwdm||'-'||gwsbsj gwdmgwsbsj from gwxxb";
			List gwList = dao.getList(sql, new String[] {}, new String[] {
					"gwdm", "gwdmgwsbsj" });
			request.setAttribute("gwList", gwList);
		} else if (from.equalsIgnoreCase("/sjcz/lpxxb.jsp")) {
			sql = "select bxgsdm,bxgsmc from bxgsdmb";
			List bxgsList = dao.getList(sql, new String[] {}, new String[] {
					"bxgsdm", "bxgsmc" });
			sql = "select bxxzdm,bxxzmc from bxxzb";
			List bxxzList = dao.getList(sql, new String[] {}, new String[] {
					"bxxzdm", "bxxzmc" });
			request.setAttribute("bxxzNum", bxxzList.size());
			request.setAttribute("bxgsList", bxgsList);
			request.setAttribute("bxxzList", bxxzList);
		} else if (from.equalsIgnoreCase("/gygl/gygl_xshdpy_apply.jsp")) {
			sql = "select lddm,ldmc from sslddmb order by  lddm ";
			List ldList = dao.getList(sql, new String[] {}, new String[] {
					"lddm", "ldmc" });
			request.setAttribute("ldList", ldList);
		} else if (from.equalsIgnoreCase("/gygl/gygl_lczxxb.jsp")) {
			String[] colList = { "lddm", "qsh", "qsdh" };
			sql = "select lddm,qsh,qsdh from view_xszsxx where xh=?";
			String[] fzxx = dao.getOneRs(sql, new String[] { xh }, colList);
			if (fzxx != null) {
				for (int i = 0; i < fzxx.length; i++) {
					map.put(colList[i], fzxx[i]);
				}
			}
			sql = "select lddm,ldmc from sslddmb order by  lddm ";
			List ldList = dao.getList(sql, new String[] {}, new String[] {
					"lddm", "ldmc" });
			request.setAttribute("ldList", ldList);
		} else if (from.equalsIgnoreCase("/xsjbxx_input_webopen.do")) {
			sql = "select * from view_xsjbxx where xh=?";
			String[] colList = dao
			.getColumnName("select * from view_xsjbxx where 1=2"); // ������������
			String[] stuinfo = dao.getOneRs(sql, new String[] { xh }, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
				}
			}
			request.setAttribute("rs", map);
			return mapping.findForward("success2");
		} else if (from.equalsIgnoreCase("/hzjy/hzjy_xssq.jsp")) {
			sql = "select * from view_xsjbxx where xh=?";
			String[] colList = dao
			.getColumnName("select * from view_xsjbxx where 1=2"); // ������������
			String[] stuinfo = dao.getOneRs(sql, new String[] { xh }, colList);
			if (stuinfo != null) {
				for (int i = 0; i < colList.length; i++) {
					map.put(colList[i].toLowerCase(), stuinfo[i]); // ����¼ѭ������map��
				}
			}

			String userType = session.getAttribute("userOnLine").toString();
			// Ҫ���ж��û� �����жϽ������ȥ
			if ("teacher".equalsIgnoreCase(userType)) {
				request.setAttribute("userType", "teacher");
				request.setAttribute("sfksq", "true");
			} else {
				request.setAttribute("userType", "student");
				request.setAttribute("sfksq", "true");
			}
			request.setAttribute("rs", map);
			return mapping.findForward("success3");
		} else if (from.equalsIgnoreCase("/sjcz/zsjlb.jsp")) {
			if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {
				sql = "select wjlbdm,wjlbmc from gygl_zswjlbdmb";
				List wjlbList = dao.getList(sql, new String[] {}, new String[] {
						"wjlbdm", "wjlbmc" });
				request.setAttribute("wjlbList", wjlbList);
				request.setAttribute("showjsxx", "showjsxx");
			}
			String lddm = request.getParameter("lddm");
			StringBuffer cxtj = new StringBuffer();
			sql = "select lddm,qsh from view_xszsxx where xh=?";
			String[] zsxx = dao.getOneRs(sql, new String[] { xh },
					new String[] { "lddm", "qsh" });
			if (zsxx != null) {
				lddm = zsxx[0];
				map.put("lddm", zsxx[0]);
				map.put("qsh", zsxx[1]);
			}
			cxtj.append(" where 1=1");
			if ("".equalsIgnoreCase(lddm) || lddm == null) {
				cxtj.append(" and 1=1");
			} else {
				if (Check_Input_Value.check2(lddm)) {
					cxtj.append(" and lddm='");
					cxtj.append(lddm);
					cxtj.append("'");
				}
			}
			sql = "select distinct qsh from ssxxb " + cxtj;
			List ssList = dao.getList(sql, new String[] {},
					new String[] { "qsh" });

			request.setAttribute("ssList", ssList);
		} else if (from.equalsIgnoreCase("/gygl/gygl_jqlxxxb.jsp")) {
			String lddm = request.getParameter("lddm");
			StringBuffer cxtj = new StringBuffer();
			cxtj.append(" where 1=1");
			if ("".equalsIgnoreCase(lddm) || lddm == null) {
				cxtj.append(" and 1=1");
			} else {
				if (Check_Input_Value.check2(lddm)) {
					cxtj.append(" and lddm='");
					cxtj.append(lddm);
					cxtj.append("'");
				}
			}
			sql = "select distinct qsh from ssxxb " + cxtj;
			List ssList = dao.getList(sql, new String[] {},
					new String[] { "qsh" });
			request.setAttribute("ssList", ssList);
			sql = "select lddm,ldmc from sslddmb order by  lddm ";
			List ldList = dao.getList(sql, new String[] {}, new String[] {
					"lddm", "ldmc" });
			request.setAttribute("ldList", ldList);
		} else if (from.equalsIgnoreCase("/qgzx/ynys/qgzx_ynys_gwtzb.jsp")) {
			List gwList = null;
			List gwsbsjList = null;
			List qgwList = null;
			List qgwsbsjList = null;
			String userDep = request.getSession().getAttribute("userDep")
			.toString();
			String userType = dao.getUserType(userDep);
			sql = "select distinct gwdm,gwdm from view_xsgwxx where xh=?";
			QgzxService service = new QgzxService();
			sql += service.appendTggwtj();
			qgwList = dao.getList(sql, new String[] { xh }, new String[] {
					"gwdm", "gwdm" });
			sql = "select distinct gwsbsj,substr(gwsbsj,1,4)||'-'||substr(gwsbsj,5,2)||'-'||substr(gwsbsj,7,2)||' '||"
				+ "substr(gwsbsj,9,2)||':'||substr(gwsbsj,11,2)||':'||substr(gwsbsj,13,2) labgwsbsj from view_xsgwxx where xh=?";
			sql += service.appendTggwtj();
			qgwsbsjList = dao.getList(sql, new String[] { xh }, new String[] {
					"gwsbsj", "labgwsbsj" });
			if ("xy".equalsIgnoreCase(userType)) {
				sql = "select distinct gwdm,gwdm from view_gwxx where xydm=? and shjg='ͨ��' and gzjsrq>to_char(sysdate,'yyyymmdd')";
				gwList = dao.getList(sql, new String[] { userDep },
						new String[] { "gwdm", "gwdm" });
				sql = "select distinct gwsbsj,gwsbsj from view_gwxx where xydm=? and shjg='ͨ��' and gzjsrq>to_char(sysdate,'yyyymmdd')";
				gwsbsjList = dao.getList(sql, new String[] { userDep },
						new String[] { "gwsbsj", "gwsbsj" });
			} else {
				sql = "select distinct gwdm,gwdm from view_gwxx where shjg='ͨ��' and gzjsrq>to_char(sysdate,'yyyymmdd')";
				gwList = dao.getList(sql, new String[] {}, new String[] {
						"gwdm", "gwdm" });
				sql = "select distinct gwsbsj,substr(gwsbsj,1,4)||'-'||substr(gwsbsj,5,2)||'-'||substr(gwsbsj,7,2)||' '||"
					+ "substr(gwsbsj,9,2)||':'||substr(gwsbsj,11,2)||':'||substr(gwsbsj,13,2) labgwsbsj from view_gwxx where shjg='ͨ��' and gzjsrq>to_char(sysdate,'yyyymmdd')";
				gwsbsjList = dao.getList(sql, new String[] {}, new String[] {
						"gwsbsj", "labgwsbsj" });
			}

			request.setAttribute("qgwList", qgwList);
			request.setAttribute("qgwsbsjList", qgwsbsjList);
			request.setAttribute("gwList", gwList);
			request.setAttribute("gwsbsjList", gwsbsjList);
			request.setAttribute("writeAble", "yes");
		} else if (from.equalsIgnoreCase("/jxgl/xsjxbz.jsp")) {
			request.setAttribute("bzList", dao.getBzList());
		} else if (from.equalsIgnoreCase("/jxgl/xsjxcf.jsp")) {
			request.setAttribute("bzList", dao.getBzList());
		} else if (from.equalsIgnoreCase("/jygl/zgmy/jyxyslqdj_viewmore.jsp")) {
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)) {
				//============begin �й�����ѧԺ 2009/6/4 luojw ===========
				request.setAttribute("type", "add");
				request.setAttribute("update", "no");
				//============end �й�����ѧԺ 2009/6/4 luojw ===========
			}

		}
		//�人����ѧ ѧ������
		else if(from.equalsIgnoreCase("/whlg_xszz.do?method=jtjjknssq")
				|| from.equalsIgnoreCase("/whlg_xszz.do?method=gjzxdksq")
				|| from.equalsIgnoreCase("/whlg_xszz.do?method=gjzxdksp")
				|| from.equalsIgnoreCase("/whlg_xszz.do?method=gjzxdkhkxysq")
				|| from.equalsIgnoreCase("/whlg_xszz.do?method=xszzsq")
				|| from.equalsIgnoreCase("/xszz_ynys.do?method=gjlzjxjpre")
				|| from.equalsIgnoreCase("/xszz_ynys.do?method=szflzjxjpre")
				|| from.equalsIgnoreCase("/xljk_whlgdx_xskhdxwh.do?doType=add") //�人����ѧ  ������
		){
			from += "&getXh=" + xh;
		}
		if(Globals.XXDM_ZJLG.equals(xxdm)){
			if(StringUtils.isNull(xh)){
				map.put("sfkns","");
			}else{
				map.put("sfkns", dao.isKns(xh)?"��":"��");
			}
		}
		request.setAttribute("njList", dao.getNjList());// �����꼶�б�
		request.setAttribute("xnList", dao.getXnndList());// ����ѧ���б�
		request.setAttribute("xqList", dao.getXqList());// ����ѧ���б�
		request.setAttribute("rs", map);
		String userDep = (String) session.getAttribute("userDep");
		String userType = dao.getUserType(userDep);
		if (from.equalsIgnoreCase("/jygl/jycjh_hyxx_add.jsp")){
			return new ActionForward("/jyglInfo.do?method=add_hyxx&doType=info&xh="+xh, false);
		}
		if("/dtjs/ahjg/stu_info_add.jsp".equals(from)){
			return new ActionForward("/ahjg_wtqtgl.do?act=add&xh="+xh, false);
		}
		if(from.equalsIgnoreCase("/pjpy/szyqxy/zhszcp/xthd/dshd/dshd_Add.jsp")){
			return new ActionForward("/pjpyszyqwh.do?method=szyc_dshdAdd&xxk=dshd&xh="+xh, false);
		}

		if(from.equalsIgnoreCase("/pjpy/szyqxy/zhszcp/xthd/ltjz/ivlt_Add.jsp")){
			return new ActionForward("/pjpyszyqwh.do?method=szyc_dshdAdd&xxk=ivlt&xh="+xh, false);
		}

		if(from.equalsIgnoreCase("/pjpy/szyqxy/zhszcp/xthd/xthddj/xthd_Add.jsp")){
			return new ActionForward("/pjpyszyqwh.do?method=szyc_dshdAdd&xxk=wthd&xh="+xh, false);
		}

		if(from.equalsIgnoreCase("/pjpy/szyqxy/zhszcp/xthd/yybd/yybd_Add.jsp")){
			return new ActionForward("/pjpyszyqwh.do?method=szyc_dshdAdd&xxk=yybd&xh="+xh, false);
		}

		if(from.equalsIgnoreCase("/pjpy/szyqxy/zhszcp/zznl_Add.jsp")){
			return new ActionForward("/pjpyszyqwh.do?method=szyc_zznlAdd&xh="+xh, false);
		}
		if (from.equalsIgnoreCase("/sjcz/modi_stu_info.jsp")
				&& userType.equalsIgnoreCase("xy")) {
			sql = "select * from xsfzxxb where 1=2";
			String[] outString = dao.getColumnName(sql);
			String[] outValue = new String[] {};
			sql = "select * from xsfzxxb where xh=?";
			outValue = dao.getOneRs(sql, new String[] { xh }, outString);
			if (outValue == null) {
				outValue = new String[outString.length];
			}
			for (int i = 0; i < outValue.length; i++) {
				map.put(outString[i].toLowerCase(), outValue[i]);
			}

			sql = "select * from view_xsjbxx where xh=?";
			outString = new String[] { "xm", "zymc", "xymc", "nj", "bjmc",
					"xb", "xh" };
			outValue = dao.getOneRs(sql, new String[] { xh }, outString);
			if (outValue == null) {
				outValue = new String[outString.length];
			}
			for (int i = 0; i < outValue.length; i++) {
				map.put(outString[i].toLowerCase(), outValue[i]);
			}
			// if(xxdm.equalsIgnoreCase(Globals.XXDM_DBLYDX)){
			map.put("email",dao.getOneRs("select getmes(a.xh,'dzyx',(select b.lxdzxx from bks_xstxxx b where a.xh=b.xh)) email from xsxxb a where a.xh=?",
					new String[] { xh }, "email"));
			map.put("sjhm",dao.getOneRs("select getmes(a.xh,'sjhm',(select b.sjhm from bks_xstxxx b where a.xh=b.xh)) sjhm from xsxxb a where a.xh=?",
					new String[] { xh }, "sjhm"));
			map.put("lxdh1",dao.getOneRs("select getmes(a.xh,'lxdh',(select b.lxdh from bks_xstxxx b where a.xh=b.xh)) lxdh from xsxxb a where a.xh=?",
					new String[] { xh }, "lxdh"));
			// }

			if(xxdm.equalsIgnoreCase(Globals.XXDM_HNGYDX)){
				String tmpSql = "select dasfyl,daylyy from view_xsxxb where xh=?";
				map.putAll(dao.getMap(tmpSql,new String[]{xh},new String[]{"dasfyl","daylyy"}));
			}
			request.setAttribute("xxdm", xxdm);
			request.setAttribute("rs", map);
			request.setAttribute("writeAble", writeAble);
			request.setAttribute("rs1", map);
			request.setAttribute("writeAble", "yes");
		}
		if(Globals.XXDM_ZJLG.equals(xxdm)){
			String sql12 = "select * from jxjdmb";	
			List jxjList = dao.getList(sql12, new String[] {}, new String[] {	
					"jxjdm", "jxjmc" });
			request.setAttribute("jzxjxmList", jxjList);
		}
		request.setAttribute("doType",(request.getParameter("doType") == null) ? " " : request.getParameter("doType"));
		//================2009/6/24 luojw begin ======================
		if (from.equalsIgnoreCase("/szdw/member_One.jsp")) {
			request.setAttribute("doType", "add");
		}
		// ================2009/6/24 luojw end ======================
		request.setAttribute("pkValue",(request.getParameter("pkValue") == null) ? " " : request.getParameter("pkValue"));


		//�㽭��������������
		if (from.equalsIgnoreCase("/zjlgPjpy.do?method=rychSq")) {
			map.put("xn", dqnqd[0]);
			map.put("xq", dqnqd[1]);
			map.put("nd", dqnqd[2]);
			List<HashMap<String, String>> qflxList = new ArrayList<HashMap<String, String>>();
			qflxList = dao.getList("select qflxdm,qflxmc from qflxdmb",
					new String[] {}, new String[] { "qflxdm", "qflxmc" });
			request.setAttribute("qflxList", qflxList);
		}
		if ("/jygl/zgmy/jyxyslqdj_viewmore.jsp".equals(from)) {
			request.setAttribute("type", "add");
			request.setAttribute("update", "yes");
		}

		request.setAttribute("rs", map);
		newFwd = new ActionForward(from, false);
		return newFwd;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	private ActionForward stuInfoDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/*
		 * 2012-11-1ǰ̨ҳ�涼�Ѿ���Ϊͨ��ҳ�棬�����漰�����Ի�ѧУ�ģ����ܻ����ҳ�沿����Ϣ��ʾ��ȫ���Ͼ������Ѹ�
		 * 
		 */
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
		
		if (userType.equalsIgnoreCase("stu")) {
			xh = userName;
		}
		//�ɶ�����֮ǰ�и��Ի��޸ģ�����ԭ�о�ҳ��
		//�Ĵ�����ְҵ����ѧԺ֮ǰ�и��Ի��޸ģ�����ԭ�о�ҳ��
		//����ְҵ����ѧԺ֮ǰ�и��Ի��޸ģ�����ԭ�о�ҳ��
		//�й����ʴ�ѧ֮ǰ�и��Ի��޸ģ�����ԭ�о�ҳ��
		if (!Globals.XXDM_CDTYXY.equalsIgnoreCase(xxdm)&&!Globals.XXDM_SCJDZYJSXY.equalsIgnoreCase(xxdm)&&!Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)&&!Globals.XXDM_CZZYJSXY.equalsIgnoreCase(xxdm)) {
			return new ActionForward("/xsxx_tygl.do?method=ckZxsxx&xh="+xh,false);
		}
		
		String[] titleEn = null;
		String[] titleCn = null;
		
		titleEn = new String[]{"xsxx","jtxx","dtjs","pjpy","dwjl","xszz",
				            "qgzx","xljk","xsjx","wjcf","xscj","qtsj"};
		titleCn = new String[]{"������Ϣ","��ͥ��Ϣ","���Ž���",
				            "��������","���⽻��","ѧ������","�ڹ���ѧ","������",
				            "ѧ����ѵ","Υ�ʹ���","ѧ���ɼ�","��������"};
		//String[] tmp = null;
					
		
		String page = "success";

		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
			//������
			titleEn = new String[]{"xsxx","xjyd","jtxx","dtjs","pjpy","dwjl","xszz",
		            "qgzx","xljk","xsjx","wjcf","qtsj", "xsgy"};
			titleCn = new String[]{"������Ϣ","ѧ���춯","��ͥ��Ϣ","���Ž���",
		            "��������","���⽻��","ѧ������","�ڹ���ѧ","������",
		            "ѧ����ѵ","Υ�ʹ���","��������", "ѧ����Ԣ"};
			request.setAttribute("showXsgy", "showXsgy");
			request.setAttribute("showZsjl", "showZsjl");
			request.setAttribute("showWmqs", "showWmqs");
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
			//����ְҵ����ѧԺ
			titleEn = new String[]{"xsxx","xjyd","jtxx","dtjs","pjpy","dwjl","xszz",
		            "qgzx","xljk","xsjx","wjcf","qtsj", "xsgy","xycjd", "xssfxx"};
			titleCn = new String[]{"������Ϣ","ѧ���춯","��ͥ��Ϣ","���Ž���",
		            "��������","���⽻��","ѧ������","�ڹ���ѧ","������",
		            "ѧ����ѵ","Υ�ʹ���","��������", "ѧ����Ԣ","ѧҵ�ɼ���", "�շ���Ϣ"};
			request.setAttribute("showXsgy", "showXsgy");
			request.setAttribute("showWmqs", "showWmqs");
			request.setAttribute("showhzy", "showhzy");
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)){
			//�й�����ѧԺ
			titleEn = new String[]{"xsxx","xjyd","jtxx","dtjs","pjpy","dwjl","xszz",
		            "qgzx","xljk","xsjx","wjcf","qtsj", "zgmyxscj"};
			titleCn = new String[]{"������Ϣ","ѧ���춯","��ͥ��Ϣ","���Ž���",
		            "��������","���⽻��","ѧ������","�ڹ���ѧ","������",
		            "ѧ����ѵ","Υ�ʹ���","��������", "ѧ���ɼ�"};
		}else if(Globals.XXDM_BJQNZZXY.equalsIgnoreCase(xxdm)){
			//������������ѧԺ
			titleEn = new String[]{"xsxx","xjyd","jtxx","dtjs","pjpy","xszz",
		            "qgzx","xljk","wjcf","xscj"};
			titleCn = new String[]{"������Ϣ","ѧ���춯","��ͥ��Ϣ","���Ž���",
		            "��������","ѧ������","�ڹ���ѧ","������",
		            "Υ�ʹ���","ѧ���ɼ�"};
		}

		// ѧ��������Ϣ
		sql = "select a.xh,a.jkzk, a.xm,a.xb, a.csrq,(select xqmc from dm_zju_xq where dm=a.yxdm) yxdm, a.mzmc, a.zzmmmc,a.ykth, a.sfzh, a.syd lydq," +
			  " a.syd csdm, a.ssbh, a.nj, a.xymc, a.zymc, a.bjmc, a.xz, a.xjlb xjztm,a.sfhq," +
			  "a.lxdzxx, a.lxdh, a.sjhm, a.qsdh,a.hkszd,a.ksh,b.jtcy1_xm,b.jtcy1_gx,b.jtcy1_nl,b.jtcy1_sfzh,b.jtcy1_mz,(select mzmc from mzdmb where b.jtcy1_mz = mzdm) jtcy1_mzmc,b.jtcy1_zzmm,(select zzmmmc from zzmmdmb where zzmmdm = jtcy1_zzmm) jtcy1_zzmmmc,b.jtcy1_zy,b.jtcy1_zw,b.jtcy1_lxdh1,b.jtcy1_lxdh2,b.jtcy1_gzdz," +
			  "b.jtcy2_xm,b.jtcy2_gx,b.jtcy2_nl,b.jtcy2_sfzh,b.jtcy2_mz,(select mzmc from mzdmb where b.jtcy2_mz = mzdm) jtcy2_mzmc,b.jtcy2_zzmm,(select zzmmmc from zzmmdmb where zzmmdm = jtcy2_zzmm) jtcy2_zzmmmc,b.jtcy2_zy,b.jtcy2_zw,b.jtcy2_lxdh1,b.jtcy2_lxdh2,b.jtcy2_gzdz," +
			  "b.jtcy3_xm,b.jtcy3_gx,b.jtcy3_nl,b.jtcy3_sfzh,b.jtcy3_mz,(select mzmc from mzdmb where b.jtcy3_mz = mzdm) jtcy3_mzmc,b.jtcy3_zzmm,(select zzmmmc from zzmmdmb where zzmmdm = jtcy3_zzmm) jtcy3_zzmmmc,b.jtcy3_zy,b.jtcy3_zw,b.jtcy3_lxdh1,b.jtcy3_lxdh2,b.jtcy3_gzdz,b.jtszd, b.lxdh1 jtdh,b.jjzk,a.zw,a.jg,b.yb,a.ccqj,a.qqhm,a.dah,a.ylbxh,a.yhmc,a.yhkh,pycc," +
			  "(select distinct pyccmc from xg_xsxx_pyccdmb c where a.pycc=c.pyccdm)pyccmc," +
			  "pyfs,(select distinct pyfsmc from xg_xsxx_pyfsdmb c where a.pyfs=c.pyfsdm)pyfsmc," +
			  "a.ykth,a.kh,sfzc,sfzd,sfzx,sfbys,sfyby,rxrq,byny,xmpy,cym,sg,tz,tc,byzh,xwzsbh," +
			  "kslb,(select distinct kslbmc from xg_xsxx_kslbdmb c where a.kslb=c.kslbdm)kslbmc," +
			  "rxfs,(select distinct rxfsmc from xg_xsxx_rxfsdmb c where a.rxfs=c.rxfsdm)rxfsmc" +
			  "  ,a.bz from view_xsxxb a left join xsfzxxb b on a.xh=b.xh where a.xh=?";
		String[] output = {"xh", "xm",
				"xb", "csrq","yxdm", "mzmc", "zzmmmc", "sfzh", "lydq", "csdm",
				"ssbh", "nj", "xymc", "zymc", "bjmc", "xz", "xjztm",
				"lxdzxx", "lxdh", "sjhm", "qsdh", "jtszd","jg","ksh",
				"jtcy1_xm","jtcy2_xm","hkszd","kh", "yhkh", "ykth","pyccmc",
				"rxfsmc","kslbmc","pyfsmc","jkzk","bz"};
		
		// else������Ȼִ�иöδ��� ----sjf
		// map = dao.getMap(sql, new String[] { xh },output);
	
		 if (xxdm.equalsIgnoreCase(Globals.XXDM_XCXY)) {
			// ����ѧԺ  ������ȡ�Խ���
		
			map = dao.getMap(sql, new String[] { xh },output);
			
			sql = "select ksh from bks_xsjbxx xh=?";
			map.put("ksh", dao.getOneRs(sql, new String[]{xh}, "ksh"));
			
		} else if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
			//�й����ʴ�ѧ		
			sql = "select * from (select a.xh, a.xm,a.xb, a.csrq, a.mzmc, a.zzmmmc, a.sfzh,a.byxx,a.rxrq,a.syd lydq, a.syd csdm," + 
			"a.ssbh, a.nj, a.xymc, a.zymc, a.bjmc, a.xz, a.xjlb xjztm,a.lxdzxx, a.lxdh, a.sjhm, a.qsdh,a.hkszd, a.rxqdw," +
			"case when a.ksh is null then (select ksh from bks_xsjbxx b where a.xh = b.xh) else a.ksh end ksh,b.JTCY1_XM," +
			"b.JTCY2_XM,b.jtszd ,a.zw,a.jg,a.rxnj,a.rxfs,a.xslbmc,a.xslxmc,a.sfzc,a.byny,a.sfyby,a.sfbys,a.xmpy,a.cym,a.sg,a.tz,a.tc," +
			"a.kslb,a.pyfs,a.pycc,sfzx,(select ydlbmc from dm_ydlb where ydlbm=(select ydlbm from bks_xjydxx b " +
			"where ydxh=(select max(ydxh) from bks_xjydxx c where a.xh=c.xh))) ydlbmc,a.bz,a.qqhm from view_xsxxb a " +
			"left join xsfzxxb b on a.xh=b.xh) where xh=?";		
			map = dao.getMap(sql, new String[] {xh}, new String[] { "xh", "xm",
					"xb", "csrq", "mzmc", "zzmmmc", "sfzh", "lydq", "csdm","sfzc",
					"ssbh", "nj", "xymc", "zymc", "bjmc", "xz", "xjztm","rxrq",
					"byxx","ksh","rxqdw", "lxdzxx", "lxdh", "sjhm", "qsdh" , "byny",
					"sfyby","sfbys","xmpy","jtszd","rxnj","cym","sg","tz","tc","kslb",
					"rxfs","xslbmc","xslxmc","pyfs","pycc","jg","sfzx","ydlbmc", "bz","qqhm"});
		}else if(Globals.XXDM_LSSFXY.equalsIgnoreCase(xxdm)){//��ɽʦ��
//			ѧ��������Ϣ
			sql = "select * from (select a.xh, a.xm,a.xb, a.csrq, a.mzmc, a.zzmmmc, a.sfzh, " 
				+" (select b.qxmc from dmk_qx b where b.qxdm=substr(a.syd,0,instr(a.syd,'/')-1) )"
				+" ||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.syd,instr(a.syd,'/')+1,instr(a.syd,'/',1,2)-instr(a.syd,'/')-1))"
				+" ||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.syd,instr(a.syd,'/',-1)+1))"
				+" lydq, a.syd csdm,a.ssbh, a.nj, a.xymc, a.zymc, a.bjmc, a.xz, a.xjlb xjztm,a.lxdzxx, a.lxdh, a.sjhm, a.qsdh,a.hkszd, " 
				+"(select ksh from bks_xsjbxx b where a.xh=b.xh) ksh,b.JTCY1_XM,b.JTCY2_XM,b.jtszd ,a.zw," 
				+"(select b.qxmc from dmk_qx b where b.qxdm=substr(a.jg,0,instr(a.jg,'/')-1) )"
				+"||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.jg,instr(a.jg,'/')+1,instr(a.jg,'/',1,2)-instr(a.jg,'/')-1))"
				+"||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.jg,instr(a.jg,'/',-1)+1))"
				+" jg,(select b.qxmc from dmk_qx b where b.qxdm=substr(a.jtdz,0,instr(a.jtdz,'/')-1) ) "
				+" ||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.jtdz,instr(a.jtdz,'/')+1,instr(a.jtdz,'/',1,2)-instr(a.jtdz,'/')-1))"
				+" ||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.jtdz,instr(a.jtdz,'/',1,2)+1,instr(a.jtdz,'/',1,3)-instr(a.jtdz,'/',1,2)-1)) "
				+" ||substr(a.jtdz,instr(a.jtdz,'/',-1)+1,length(a.jtdz)) jtdz "
				+"from view_xsxxb a left join xsfzxxb b on a.xh=b.xh) where xh=?";	
			map = dao.getMap(sql, new String[] {xh}, new String[] { "xh", "xm",
					"xb", "csrq", "mzmc", "zzmmmc", "sfzh", "lydq", "csdm",
					"ssbh", "nj", "xymc", "zymc", "bjmc", "xz", "xjztm",
					"lxdzxx", "lxdh", "sjhm", "qsdh" ,"jg","jtdz"});
		}
//		else if (Globals.XXDM_NJJS.equalsIgnoreCase(xxdm)) {// �Ͼ���ʦ
//			output = new String[] { "xh", "xm",
//				"xb", "csrq", "mzmc", "zzmmmc", "sfzh", "lydq", "csdm",
//				"ssbh", "nj", "xymc", "zymc", "bjmc", "xz", "xjztm",
//				"lxdzxx", "lxdh", "sjhm", "qsdh" ,"jg","jtszd","yb","ccqj",
//				"qqhm","dah","ylbxh","yhmc","yhkh","ksh","jtdh","jtcy1_xm","jtcy2_xm",
//				"sfzc","sfzd","sfzx","sfbys","sfyby","rxrq","byny","xmpy","cym",
//				"sg","tz","tc","kslb","rxfs","hkszd","kh", "ykth","pycc","pyccmc","pyfs",
//				"rxfsmc","kslbmc","pyfsmc","xwzsbh","byzh","jkzk"};
//			map = dao.getMap(sql, new String[] { xh }, output);
//			if ("yes"
//					.equalsIgnoreCase(xsxxglService.getXsxxCs().get("dzxxqdm"))) {// ��ַ��Ϣȡ����
//				sql = " select zcsxhm,kh,(select b.qxmc from dmk_qx b where b.qxdm=substr(a.syd,0,instr(a.syd,'/')-1) )"
//						+ "||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.syd,instr(a.syd,'/')+1,instr(a.syd,'/',1,2)-instr(a.syd,'/')-1))"
//						+ " ||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.syd,instr(a.syd,'/',-1)+1)) lydq,"
//						+ "(select b.qxmc from dmk_qx b where b.qxdm=substr(a.jg,0,instr(a.jg,'/')-1) ) "
//						+ "||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.jg,instr(a.jg,'/')+1,instr(a.jg,'/',1,2)-instr(a.jg,'/')-1))"
//						+ "||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.jg,instr(a.jg,'/',-1)+1)) jg, "
//						+ "(select b.qxmc from dmk_qx b where b.qxdm=substr(a.hkszd,0,instr(a.hkszd,'/')-1) )"
//						+ "||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.hkszd,instr(a.hkszd,'/')+1,instr(a.hkszd,'/',1,2)-instr(a.hkszd,'/')-1))"
//						+ "||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.hkszd,instr(a.hkszd,'/',-1)+1)) hkszd, "
//						+ "(select b.mc from xsxx_rxqwhcdb b where a.rxqwhcd = b.dm) rxqwhcd, "
//						+ "(select b.mc from xsxx_hkxzb b where a.hkxz = b.dm) hkxz "
//						+ "from view_xsxxb a where xh=?";
//				map.putAll(dao.getMap(sql, new String[] { xh }, new String[] {
//						"lydq", "jg", "hkxz", "hkszd", "zcsxhm","rxqwhcd","kh" }));
//			}
//			// ------------end -------------
//			//---------------edit quph---�������麣ѧԺ------------
//		}
		else if (Globals.XXDM_BJLGDXZHXY.equals(xxdm)) {

			StringBuilder zhSql = new StringBuilder();
			
			zhSql.append("select * from (select a.xh,a.xm,a.xb,a.csrq,a.mzmc,a.zzmmmc,");
			zhSql.append("a.sfzh,a.sydqmc lydq,a.syd csdm,a.ssbh,a.nj,a.xymc,a.zymc,a.bjmc,");
			zhSql.append("a.xz,a.xjlb xjztm,a.lxdzxx,a.lxdh,a.sjhm,a.qsdh,a.cym,a.sg,a.tz,a.tc,a.hkszdmc hkszd,");
			zhSql.append("(select ksh from bks_xsjbxx b where a.xh = b.xh) ksh,");
			zhSql.append("nvl((select sfbd from zhxyxsbdxx where xh=a.xh and xn=(select dqxn from xtszb) and xqdm=(select dqxq from xtszb)),'��') sfbd,");
			zhSql.append("nvl((select jfqk from zhxyxsbdxx where xh=a.xh and xn=(select dqxn from xtszb) and xqdm=(select dqxq from xtszb)),'δ�ɷ�') jfqk,");
			zhSql.append("b.JTCY1_XM,b.JTCY2_XM,b.jtszd,a.zw,a.jgmc jg,b.yb,a.ccqj,");
			zhSql.append("(select xm from view_fdybjdz b where a.bjdm = b.bjdm and rownum = 1) fdyxm,");
			zhSql.append("(select lxdh from view_fdybjdz b where a.bjdm = b.bjdm and rownum = 1) fdyyddh ");
			zhSql.append(" from view_xsxxb a left join xsfzxxb b on a.xh = b.xh) where xh =?");
			
			map = dao.getMap(zhSql.toString(), new String[] {xh}, new String[] { "xh", "xm",
					"xb", "csrq", "mzmc", "zzmmmc", "sfzh", "lydq", "csdm",
					"ssbh", "nj", "xymc", "zymc", "bjmc", "xz", "xjztm","hkszd","sg","tz","tc","cym",
					"lxdzxx", "lxdh", "sjhm", "qsdh" ,"jg","jtszd","yb","ccqj",
					"fdyxm","fdyyddh","sfbd","jfqk"});
			//------------------end--------------
		} else if(Globals.XXDM_GDTYZYJSXY.equals(xxdm)){//�㶫����ְҵ����ѧԺ
//			 ѧ��������Ϣ
			sql = "select a.xh, a.xm,a.xb, a.csrq, a.mzmc, a.zzmmmc, a.sfzh, a.syd lydq," +
				  " a.syd csdm, a.ssbh, a.nj, a.xymc, a.zymc, a.bjmc, a.xz, a.xjlb xjztm," +
				  "a.lxdzxx, a.lxdh, a.sjhm, a.qsdh,a.hkszd,a.ksh,b.JTCY1_XM,b.JTCY2_XM," +
				  "b.jtszd, b.lxdh1 jtdh,a.zw,a.jg,b.yb,a.ccqj,a.qqhm,a.dah,a.ylbxh,a.yhmc,a.yhkh,pycc," +
				  "(select distinct pyccmc from xg_xsxx_pyccdmb c where a.pycc=c.pyccdm)pyccmc," +
				  "pyfs,(select distinct pyfsmc from xg_xsxx_pyfsdmb c where a.pyfs=c.pyfsdm)pyfsmc," +
				  "a.ykth,a.kh,sfzc,sfzd,sfzx,sfbys,sfyby,rxrq,byny,xmpy,cym,sg,tz,tc,byzh,xwzsbh," +
				  "kslb,(select distinct kslbmc from xg_xsxx_kslbdmb c where a.kslb=c.kslbdm)kslbmc," +
				  "rxfs,(select distinct rxfsmc from xg_xsxx_rxfsdmb c where a.rxfs=c.rxfsdm)rxfsmc," +
				  "xjh,csd,hkxz,jrzzmmrq,sfhq,ksh," +
				  "(select qxmc from dmk_qx b where b.qxdm=a.csds) csds," +
				  "(select qxmc from dmk_qx b where b.qxdm=a.csdshi) csdshi," +
				  "(select qxmc from dmk_qx b where b.qxdm=a.csdxian) csdxian"+
				  "  from view_xsxxb a left join xsfzxxb b on a.xh=b.xh where a.xh=?";	
			map = dao.getMap(sql, new String[] {xh}, new String[] { "xh", "xm",
					"xb", "csrq", "mzmc", "zzmmmc", "sfzh", "lydq", "csdm",
					"ssbh", "nj", "xymc", "zymc", "bjmc", "xz", "xjztm",
					"lxdzxx", "lxdh", "sjhm", "qsdh" ,"jg","jtszd","yb","ccqj",
					"qqhm","dah","ylbxh","yhmc","yhkh","ksh","jtdh","jtcy1_xm","jtcy2_xm",
					"sfzc","sfzd","sfzx","sfbys","sfyby","rxrq","byny","xmpy","cym",
					"sg","tz","tc","kslb","rxfs","hkszd","kh", "ykth","pycc","pyccmc","pyfs",
					"rxfsmc","kslbmc","pyfsmc","xwzsbh","byzh",
					"xjh","csd","hkxz","jrzzmmrq","sfhq","ksh","csds","csdshi","csdxian"});
			if("yes".equalsIgnoreCase(xsxxglService.getXsxxCs().get("dzxxqdm"))){//��ַ��Ϣȡ����
				sql = " select (select b.qxmc from dmk_qx b where b.qxdm=substr(a.syd,0,instr(a.syd,'/')-1) )" 
					  + "||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.syd,instr(a.syd,'/')+1,instr(a.syd,'/',1,2)-instr(a.syd,'/')-1))"
				      + " ||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.syd,instr(a.syd,'/',-1)+1)) lydq,"
				      + "(select b.qxmc from dmk_qx b where b.qxdm=substr(a.hkszd,0,instr(a.hkszd,'/')-1) )" 
					  + "||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.hkszd,instr(a.hkszd,'/')+1,instr(a.hkszd,'/',1,2)-instr(a.hkszd,'/')-1))"
				      + " ||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.hkszd,instr(a.hkszd,'/',-1)+1)) hkszd,"
				      + "(select b.qxmc from dmk_qx b where b.qxdm=substr(a.jg,0,instr(a.jg,'/')-1) ) "
				      + "||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.jg,instr(a.jg,'/')+1,instr(a.jg,'/',1,2)-instr(a.jg,'/')-1))"
				      + "||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.jg,instr(a.jg,'/',-1)+1)) jg from view_xsxxb a where xh=?";
				map.putAll(dao.getMap(sql, new String[]{xh}, new String[]{"lydq", "jg", "hkszd"}));
			}
		}else if(Globals.XXDM_SHCBYSGDZKXX.equals(xxdm)){//�㶫����ְҵ����ѧԺ
			//ѧ��������Ϣ
			map = dao.getMap(sql, new String[] {xh}, new String[] { "xh", "xm",
					"xb", "csrq", "mzmc", "zzmmmc", "sfzh", "lydq", "csdm",
					"ssbh", "nj", "xymc", "zymc", "bjmc", "xz", "xjztm",
					"lxdzxx", "lxdh", "sjhm", "qsdh" ,"jg","jtszd","yb","ccqj",
					"qqhm","dah","ylbxh","yhmc","yhkh","ksh","jtdh","jtcy1_xm","jtcy2_xm",
					"sfzc","sfzd","sfzx","sfbys","sfyby","rxrq","byny","xmpy","cym",
					"sg","tz","tc","kslb","rxfs","hkszd","kh", "ykth","pycc","pyccmc","pyfs",
					"rxfsmc","kslbmc","pyfsmc","xwzsbh","byzh","jkzk"});
			if("yes".equalsIgnoreCase(xsxxglService.getXsxxCs().get("dzxxqdm"))){//��ַ��Ϣȡ����
				sql = " select (select b.qxmc from dmk_qx b "
					  +" where b.qxdm = substr(a.syd, 0, instr(a.syd, '/') - 1))syds, "
					  
					  +" (select b.qxmc from dmk_qx b "
					  +" where b.qxdm = substr(a.syd, "
					  +" instr(a.syd, '/') + 1,instr(a.syd, '/', 1, 2) - instr(a.syd, '/') - 1)) || "
					  +" (select b.qxmc from dmk_qx b "
					  +" where b.qxdm = substr(a.syd, instr(a.syd, '/', -1) + 1)) hjdz, "
					  +	"(select b.qxmc from dmk_qx b where b.qxdm=substr(a.syd,0,instr(a.syd,'/')-1) )" 
					  + "||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.syd,instr(a.syd,'/')+1,instr(a.syd,'/',1,2)-instr(a.syd,'/')-1))"
				      + " ||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.syd,instr(a.syd,'/',-1)+1)) lydq,"
				      + "(select b.qxmc from dmk_qx b where b.qxdm=substr(a.hkszd,0,instr(a.hkszd,'/')-1) )" 
					  + "||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.hkszd,instr(a.hkszd,'/')+1,instr(a.hkszd,'/',1,2)-instr(a.hkszd,'/')-1))"
				      + " ||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.hkszd,instr(a.hkszd,'/',-1)+1)) hkszd,"
				      + "(select b.qxmc from dmk_qx b where b.qxdm=substr(a.jg,0,instr(a.jg,'/')-1) ) "
				      + "||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.jg,instr(a.jg,'/')+1,instr(a.jg,'/',1,2)-instr(a.jg,'/')-1))"
				      + "||(select b.qxmc from dmk_qx b where b.qxdm=substr(a.jg,instr(a.jg,'/',-1)+1)) jg from view_xsxxb a where xh=?";
				map.putAll(dao.getMap(sql, new String[]{xh}, new String[]{"lydq", "jg", "hkszd","syds","hjdz"}));
			}	
		}else if(Globals.XXDM_BJSGMJSXYFWFY.equalsIgnoreCase(xxdm)){//	�����й�ó��ʦѧԺ�����Ժ	
			sql = "select a.xh,a.jkzk, a.xm,a.xb, a.csrq,(select xqmc from dm_zju_xq where dm=a.yxdm) yxdm, a.mzmc, a.zzmmmc,a.ykth, a.sfzh, a.syd lydq," +
				  " a.syd csdm, a.ssbh, a.nj, a.xymc, a.zymc, a.bjmc, a.xz, a.xjlb xjztm,a.sfhq," +
				  "a.lxdzxx, a.lxdh, a.sjhm, a.qsdh,a.hkszd,a.ksh,b.JTCY1_XM,b.JTCY2_XM," +
				  "b.jtszd, b.lxdh1 jtdh,a.zw,a.jg,b.yb,a.ccqj,a.qqhm,a.dah,a.ylbxh,a.yhmc,a.yhkh,pycc," +
				  "(select distinct pyccmc from xg_xsxx_pyccdmb c where a.pycc=c.pyccdm)pyccmc," +
				  "pyfs,(select distinct pyfsmc from xg_xsxx_pyfsdmb c where a.pyfs=c.pyfsdm)pyfsmc," +
				  "a.ykth,a.kh,sfzc,sfzd,sfzx,sfbys,sfyby,rxrq,byny,xmpy,cym,sg,tz,tc,byzh,xwzsbh," +
				  "kslb,(select distinct kslbmc from xg_xsxx_kslbdmb c where a.kslb=c.kslbdm)kslbmc," +
				  "rxfs,(select distinct rxfsmc from xg_xsxx_rxfsdmb c where a.rxfs=c.rxfsdm)rxfsmc" +
				  "  ,a.bz,a.sfzblx from view_xsxxb a left join xsfzxxb b on a.xh=b.xh where a.xh=?";
			map = dao.getMap(sql, new String[] {xh}, new String[] { "xh", "xm",
					"xb", "csrq","yxdm", "mzmc", "zzmmmc", "sfzh", "lydq", "csdm",
					"ssbh", "nj", "xymc", "zymc", "bjmc", "xz", "xjztm",
					"lxdzxx", "lxdh", "sjhm", "qsdh" ,"jg","jtszd","yb","ccqj",
					"qqhm","dah","ylbxh","yhmc","yhkh","ksh","jtdh","jtcy1_xm","jtcy2_xm",
					"sfzc","sfzd","sfzx","sfbys","sfyby","rxrq","byny","xmpy","cym",
					"sg","tz","tc","kslb","rxfs","hkszd","kh", "ykth","pycc","pyccmc","pyfs",
					"rxfsmc","kslbmc","pyfsmc","xwzsbh","byzh","jkzk","sfhq","bz","sfzblx"});
			if("yes".equalsIgnoreCase(xsxxglService.getXsxxCs().get("dzxxqdm"))){//��ַ��Ϣȡ����
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
			System.out.println(sql);
		}else{
			System.out.println(sql);
			map = dao.getMap(sql, new String[] {xh}, new String[] { "xh", "xm",
					"xb", "csrq","yxdm", "mzmc", "zzmmmc", "sfzh", "lydq", "csdm",
					"ssbh", "nj", "xymc", "zymc", "bjmc", "xz", "xjztm",
					"lxdzxx", "lxdh", "sjhm", "qsdh" ,"jg","jtszd","yb","ccqj",
					"qqhm","dah","ylbxh","yhmc","yhkh","ksh","jtdh",
					"jtcy1_xm","jtcy1_gx","jtcy1_nl","jtcy1_sfzh","jtcy1_mzmc","jtcy1_zzmmmc","jtcy1_zy","jtcy1_zw","jtcy1_lxdh1","jtcy1_lxdh2","jtcy1_gzdz",
					"jtcy2_xm","jtcy2_gx","jtcy2_nl","jtcy2_sfzh","jtcy2_mzmc","jtcy2_zzmmmc","jtcy2_zy","jtcy2_zw","jtcy2_lxdh1","jtcy2_lxdh2","jtcy2_gzdz",
					"jtcy3_xm","jtcy3_gx","jtcy3_nl","jtcy3_sfzh","jtcy3_mzmc","jtcy3_zzmmmc","jtcy3_zy","jtcy3_zw","jtcy3_lxdh1","jtcy3_lxdh2","jtcy3_gzdz",
					"jjzk","sfzc","sfzd","sfzx","sfbys","sfyby","rxrq","byny","xmpy","cym",
					"sg","tz","tc","kslb","rxfs","hkszd","kh", "ykth","pycc","pyccmc","pyfs",
					"rxfsmc","kslbmc","pyfsmc","xwzsbh","byzh","jkzk","sfhq","bz"});
			if("yes".equalsIgnoreCase(xsxxglService.getXsxxCs().get("dzxxqdm"))){//��ַ��Ϣȡ����
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
			System.out.println(sql);
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
			//������ѧԺ
			//sql = "select fdyxm, bzrxm, zlbzrxm,fdyyddh from view_bjxxxx a where exists(select 1 from view_xsjbxx b where a.bjdm=b.bjdm and b.xh=?)";
			//map.putAll(dao.getMap(sql, new String[]{xh}, new String[]{"fdyxm","fdyyddh","bzrxm","zlbzrxm"}));
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)){
			//��ɳ����
			StudentInfoCsmzService service = new StudentInfoCsmzService();
			map.put("hkqcsj", service.getDetailsInfoSelf(xh).get("hkqcsj"));
			map.put("dasfzx", service.getDetailsInfoSelf(xh).get("dasfzx"));
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJGSZYJSXY)){
			//�㽭����ְҵ����ѧԺ
			String syd = dao.getOneRs("select sydmc from syddmb where syddm=?", new String[]{map.get("lydq")}, "sydmc");
			map.put("syd", syd);
			map.put("lydq", syd);
		}
		// �б���ѧ
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZBDX)) {
			String sql_lrh = "select a.xh,a.xm,a.xb,a.csrq,a.mzmc,a.zzmmmc,a.sfzh,a.lydq,a.csdm,a.ssbh,a.nj,a.xymc,a.zymc,a.bjmc,a.xz,a.xjztm,a.lxdzxx,a.lxdh,a.qsdh,a.email,a.sjhm from "
				+ "(select a.XH,a.XM,a.XB,a.NJ,a.XYMC,a.XZ,a.ZYMC,a.BJMC,a.SSBH,a.QSDH,a.LXDZXX email,a.LXDH,a.SFZH ,a.XJZTM,a.LYDQ,a.CSDM,a.CSRQ,a.MZDM,a.ZZMMM,a.MZMC,a.ZZMMMC,a.xjztm,a.KSH ,b.email,b.sjhm from view_stu_details a left join xsfzxxb b on a.xh=b.xh) a "
				+ "WHERE a.xh=?";
			HashMap<String, String> map_lrh = new HashMap<String, String>();
			map_lrh = dao.getMapNotOut(sql_lrh, new String[] { xh });
			map = map_lrh;
			map.put("lxdzxx", map.get("email"));

			sql_lrh = "select xjlb from xsxxb where xh=?";
			map_lrh = dao.getMapNotOut(sql_lrh, new String[] { xh });
			String xjlb = map_lrh.get("xjlb");
			if ("1".equalsIgnoreCase(xjlb)) {
				map.put("xjztm", "��ѧ��");
			} else {
				map.put("xjztm", "��ѧ��");
			}

			sql_lrh = "select * from xsxxb where xh='" + xh + "'";
			map_lrh = dao.getMapNotOut(sql_lrh, new String[] {});
			map.put("lydq", map_lrh.get("syd"));
			map.put("lxdzxx", map_lrh.get("dzyx"));
			map.put("lxdh", map_lrh.get("lxdh"));
			map.put("csdm", map_lrh.get("jg"));
		}

		if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
			//����ְҵ����ѧԺ
			String csrq = dao.getOneRs("select substr(sfzh,7,8) csrq from view_xsjbxx where xh=?", new String[]{xh}, "csrq");
			String ssbh = dao.getOneRs("select ldmc||'-'||qsh||'��' ssbh from view_xszsxx where xh=?", new String[]{xh}, "ssbh");
			String jtdz = dao.getOneRs("select jtszd jtdz from xsfzxxb where xh=?", new String[]{xh}, "jtdz");
			map.put("jtdz",jtdz);
			map.put("ssbh", ssbh);
			map.put("csrq", csrq);
			request.setAttribute("showksh", "showksh");

			//��Ƭ��ʾ
			String dir = request.getRealPath("/") + "/pictures/"  + map.get("xh") + ".jpg";

			File f = new File(dir);
			if (f.exists()) {
				request.setAttribute("showxh", "showxh");
			}else{
				request.setAttribute("showsfzh", "showsfzh");
			}
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_LSSFXY)){//��ɽʦ��ѧԺ
			sql = "select lxdh1 from xsfzxxb where xh=?";
			map.put("jtlxdh",dao.getOneRs(sql, new String[]{xh}, "lxdh1"));
			
			titleEn = new String[]{"xsxx","xjyd","jtxx","dtjs","pjpy","dwjl","xszz",
		            "qgzx","xljk","xsjx","wjcf","qtsj", "lssfxscj"};
			titleCn = new String[]{"������Ϣ","ѧ���춯","��ͥ��Ϣ","���Ž���",
		            "��������","���⽻��","ѧ������","�ڹ���ѧ","������",
		            "ѧ����ѵ","Υ�ʹ���","��������","ѧ���ɼ�"};
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_AHJZGYXY)) {
			titleEn = new String[]{"xsxx","xjyd","jtxx","dtjs","pjpy","dwjl","xszz",
		            "qgzx","xljk","xsjx","wjcf","qtsj", "xycjd", "xssfxx"};
			titleCn = new String[]{"������Ϣ","ѧ���춯","��ͥ��Ϣ","���Ž���",
		            "��������","���⽻��","ѧ������","�ڹ���ѧ","������",
		            "ѧ����ѵ","Υ�ʹ���","��������","ѧҵ�ɼ���", "�շ���Ϣ"};
			request.setAttribute("showhzy", "showhzy");
			//TODO �շ���Ϣ
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)){
			titleEn = new String[]{"xsxx","xjyd","jtxx","dtjs","pjpy","dwjl","xszz",
		            "qgzx","xljk","xsjx","wjcf","qtsj", "xycjd", "jsxxsztz", "xssfxx"};
			titleCn = new String[]{"������Ϣ","ѧ���춯","��ͥ��Ϣ","���Ž���",
		            "��������","���⽻��","ѧ������","�ڹ���ѧ","������",
		            "ѧ����ѵ","Υ�ʹ���","��������","ѧҵ�ɼ���","������չ", "�շ���Ϣ"};
			request.setAttribute("showhzy", "showhzy");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)) {// ��չ�༶��ҵ��¼
			//��������			
			titleEn = new String[]{"xsxx","xjyd","jtxx","dtjs","pjpy","dwjl","xszz",
		            "qgzx","xljk","xsjx","wjcf","qtsj", "xnmzsztz"};
			titleCn = new String[]{"������Ϣ","ѧ���춯","��ͥ��Ϣ","���Ž���",
		            "��������","���⽻��","ѧ������","�ڹ���ѧ","������",
		            "ѧ����ѵ","Υ�ʹ���","��������", "������չ"};
			request.setAttribute("isXNMZ", "isXNMZ");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_XCXY)) {
			//����ѧԺ
			titleEn = new String[]{"xsxx","xjyd","jtxx","dtjs","pjpy","dwjl","xszz",
		            "qgzx","xljk","xsjx","wjcf","qtsj", "bzrfdy"};
			titleCn = new String[]{"������Ϣ","ѧ���춯","��ͥ��Ϣ","���Ž���",
		            "��������","���⽻��","ѧ������","�ڹ���ѧ","������",
		            "ѧ����ѵ","Υ�ʹ���","��������", "������/����Ա"};
			request.setAttribute("isXcxy", "isXcxy");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_GZDX)){
			//���ݴ�ѧ
			titleEn = new String[]{"xsxx","xjyd","jtxx","pjpy","xszz",
		            "qgzx","xljk","wjcf","xscj","qtsj"};
			titleCn = new String[]{"������Ϣ","ѧ���춯","��ͥ��Ϣ",
		            "��������","ѧ������","�ڹ���ѧ","������",
		            "Υ�ʹ���","ѧ���ɼ�","��������"};
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NGSXY)){
			titleEn = new String[]{"xsxx","xjyd","jtxx","dtjs","pjpy","dwjl","xszz",
	            "qgzx","xljk","xsjx","wjcf","xscj","qtsj","jfqk"};
			titleCn = new String[]{"������Ϣ","ѧ���춯","��ͥ��Ϣ","���Ž���",
	            "��������","���⽻��","ѧ������","�ڹ���ѧ","������",
	            "ѧ����ѵ","Υ�ʹ���","ѧ���ɼ�","��������","�ɷ����"};
			request.setAttribute("isNgsxy", "isNgsxy");
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NBZYJSXY)){
			//����ְҵ
			titleEn = new String[]{"xsxx","xjyd","jtxx","dtjs","pjpy","dwjl","xszz",
		            "qgzx","xljk","xsjx","wjcf","qtsj", "sztz"};
			titleCn = new String[]{"������Ϣ","ѧ���춯","��ͥ��Ϣ","���Ž���",
		            "��������","���⽻��","ѧ������","�ڹ���ѧ","������",
		            "ѧ����ѵ","Υ�ʹ���","��������", "������չ"};			
			request.setAttribute("isNbzyjsxy", "isNbzyjsxy");
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGKYDX)){
			//�й���ҵ��ѧ
			page = "zgkydxStudetails";
		}
//		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)) {
//			// �㽭��
//			sql = "select count(*) num from VIEW_XSZZ_COM_KNSRDB1 where xh=? and (xxsh='�ر�����' or xxsh='����')";
//			String num = dao.getOneRs(sql, new String[] { xh }, "num");
//			String isKns = "0".equalsIgnoreCase(num) ? "��" : "��";
//			map.put("isKns", isKns);
//			page = "zjlgstuinfo";
//		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_CSLGXY)){
			//������
			titleEn =new String[] {"xsxx","xjyd","jtxx","dtjs","pjpy","dwjl","xszz",
		            "qgzx","xljk","xsjx","wjcf","xscj","qtsj","xxjl"};
			titleCn =new String[] {"������Ϣ","ѧ���춯","��ͥ��Ϣ","���Ž���",
		            "��������","���⽻��","ѧ������","�ڹ���ѧ","������",
		            "ѧ����ѵ","Υ�ʹ���","ѧ���ɼ�","��������","ѧϰ����"};
			
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJXY)){
			//֮��ѧԺ
			titleEn =new String[] {"xsxx","jygl"};
			titleCn =new String[] {"������Ϣ","��ҵ����"};
			
		}
		// �㽭��ҵְҵ����ѧԺѧ��������γ̳ɼ�
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)) {
			titleEn = new String[]{"xsxx","xjyd","jtxx","dtjs","pjpy","dwjl","xszz",
		            "qgzx","xljk","xsjx","wjcf","qtsj", "xscj"};
			titleCn = new String[]{"������Ϣ","ѧ���춯","��ͥ��Ϣ","���Ž���",
		            "��������","���⽻��","ѧ������","�ڹ���ѧ","������",
		            "ѧ����ѵ","Υ�ʹ���","��������", "ѧ���ɼ�"};
			// ��ѯ�����е�ѧ��
			String[] pkValue = null;
			// List rs= new ArrayList();
			String totalKc = "";
			String totalXf = "";
			List list = new ArrayList();

			sql = "select xn||xq pk from cjb where xh=? and (zpcj1='������' or zpcj1<'60') group by xn,xq,xh order by xn";
			pkValue = dao.getRs(sql, new String[] { xh }, "pk");
			for (int i = 0; i < pkValue.length; i++) {
				List rs25 = new Vector<String[]>();
				HashMap<String, String> map25 = new HashMap<String, String>();
				sql = "select count (*) num from cjb where xn||xq=? and xh=? and (zpcj1='������' or zpcj1<'60')";
				String numKc = dao.getOneRs(sql,
						new String[] { pkValue[i], xh }, "num");
				sql = "select sum(xf) xf from cjb where xn||xq=? and xh=?";
				String numXf = dao.getOneRs(sql,
						new String[] { pkValue[i], xh }, "xf");
				String xn = pkValue[i].substring(0, 9);
				String xq = pkValue[i].substring(9, 10);
				map25.put("xn", xn);
				map25.put("xq", xq);
				map25.put("numKc", numKc);
				map25.put("numXf", numXf);
				rs25.add(map25);
				list.add(rs25);
			}
			sql = "select count(*) num,sum(xf) xf from cjb where xh=? and (zpcj1='������' or zpcj1<'60')";
			totalKc = dao.getOneRs(sql, new String[] { xh }, "num");// �ܵĿγ�����
			sql = "select sum(xf) xf from cjb where xh=?";
			totalXf = dao.getOneRs(sql, new String[] { xh }, "xf");// ��õ���ѧ��

			if (!"0".equalsIgnoreCase(totalKc)) {
				request.setAttribute("totalKc", totalKc);
				request.setAttribute("totalXf", totalXf);
			}
			request.setAttribute("showZjszy", "showZjszy");
			request.setAttribute("rs25", list);
		}
		
		if(xxdm.equalsIgnoreCase(Globals.XXDM_HZSFXY)){
			//����ʦ��
			map.putAll(dao.getMap(" select zd1,zd2,zd3,zd4,zd5 from xsxxb where xh = ? ",new String[]{xh},new String[]{"zd1","zd2","zd3","zd4","zd5"}));
			map.put("sftsxs", ("0".equalsIgnoreCase(dao.getOneRs(" select count(1) num from xg_xljk_tsxsxxb where xh = ? ", new String[]{xh},"num")))?"��":"��");
			page = "hzsfxy";
			
		}
		// ѧ������
		if(!xxdm.equalsIgnoreCase(Globals.XXDM_XCXY)){
			StuInfoDAO stu = new StuInfoDAO();
			List zzrs = stu.getTabCNofXszz(xxdm);
			request.setAttribute("zzrs", zzrs);
			request.setAttribute("zzrssize", zzrs.size());
		}
		
		//��ҵ����
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJXY)){
			StuInfoDAO stu = new StuInfoDAO();
			List jygl = stu.getTabCNofJygl(xxdm);
			request.setAttribute("jyrs", jygl);
			System.out.println(jygl.size());
			request.setAttribute("jyrssize", jygl.size());
		}
		
		//���ݹ�ҵ԰�� �������� �ۺϲ���
		if(xxdm.equalsIgnoreCase(Globals.XXDM_SZGYYQZYJSXY)){
			List<HashMap<String,String>> zhcpList=xsxxglService.getSzgyyqZhcpList(xh);
			request.setAttribute("zhcpList", zhcpList);
		}
		if(xxdm.equalsIgnoreCase(Globals.XXDM_CDTYXY)){
			page = "cdtyxy";
		}
		
		// ѧ���춯
		String xqydSql = "select a.*,(select ydlbmc from dm_ydlb b where b.ydlbm = a.ydlbm) ydlbmc," +
				"(select xjzt from dm_ydlb b where b.ydlbm = a.ydlbm) xjzt from bks_xjydxx a where xh=?";
		map.putAll(dao.getMapNotOut(xqydSql, new String[]{xh}));
		
		if("/jygl/zgmy/jyxyslqdj_viewmore.jsp".equals(url)){
			page = "sjxyStuInto";
		}
		if(!Base.isNull(request.getParameter("page"))){
			page = request.getParameter("page");
		}
		
		//�㽭����ְҵ����ѧԺ ѧ��������Ϣ
		if(Globals.XXDM_ZJJSZYJSXY.equalsIgnoreCase(xxdm)){
			map.putAll(xsxxglService.getXsstList(xh));
		}
		
		//========2011.1.21 lr ����ȡ������ģ������ݣ��縨��Ա��
		map.putAll(xsxxglService.getXsglxx(xh));
		//====end==============
		
		// ======================��ά��ѧ����Ϣ================================
		XsxxJbxxForm myForm=new XsxxJbxxForm();
		XsxxJbxxService jbxxService =new XsxxJbxxService();
		XsxxJbszService jbszService =new XsxxJbszService();
		if(Base.getXsxxwh().equalsIgnoreCase("kwh")){
			myForm.setTableName("xsxxb");
			myForm.setXh(xh);
			HashMap<String,String>hashMap=jbxxService.getXsxgxx(myForm);
			if(hashMap!=null && hashMap.size()>0){
				map.putAll(hashMap);
			}else{
				String msg = "ѧ����Ϣ����Դδȷ��������ϵ�����Ա��";
				request.setAttribute("yhInfo", msg);
				return new ActionForward("/yhInfo.do", false);
			}
		}
		//  ======================================================
		
		request.setAttribute("rs", map);
		//ѧ����Ƭ��ַ
		request.setAttribute("src", "/xgxt/sjcz/xszp.jsp?xh=" + xh);
		request.setAttribute("xh", xh);
		request.setAttribute("xxdm", xxdm);	
		
		//ѡ�����
		List<HashMap<String, String>> xsPages = null;
		//Ĭ����ʾ
		String mrxs = "xsxx";
		
		// �°汾ѧ����Ϣ��ϸҳ���������
		if("new".equalsIgnoreCase(Base.getInitProperties().get("edition"))){
			xsPages = xsxxglService.getXsPages();	
			//��Ԣ��Ϣ
			request.setAttribute("showXsgy", "showXsgy");//��ʾ��Ԣ����
			request.setAttribute("showZsjbxx", "showZsjbxx");//��ʾס�޻�����Ϣ
			request.setAttribute("xszsjbxx",xsxxglService.getXszsjbxx(xh));
			request.setAttribute("gygl_xqbj", GyglNewInit.XQBJ);//У�����
			request.setAttribute("gygl_yqbj", GyglNewInit.YQBJ);//԰�����
			int gygl_colspan=5+("0".equals(GyglNewInit.XQBJ)?0:1)+("0".equals(GyglNewInit.YQBJ)?0:1);
			request.setAttribute("gygl_colspan", gygl_colspan);
		}else{
			xsPages = dao.arrayToList(titleEn, titleCn);
		}
		
		if(xsPages != null && xsPages.size()>0){
			mrxs = xsPages.get(0).get("en");
			request.setAttribute("mkxx", "1");
		}
		
		// ����Ǵ�ӡ����
		if("print".equalsIgnoreCase(page)){
			String printMks = request.getParameter("print_mk");
			String[] printMk = printMks.split("split!!");
			
			request.setAttribute("printMk", printMk);
		}
			
		request.setAttribute("pages", xsPages);
		//ѧ�������Ƿ�ʹ��ͨ�õ�������־
		request.setAttribute("xszztyFlag", Base.getInitProperties().get("xszztyFlag"));
		request.setAttribute("doType", request.getParameter("doType"));
		request.setAttribute("mrxs", mrxs);
		request.setAttribute("edition", Base.getInitProperties().get("edition"));
		
		if(Base.getXsxxwh().equalsIgnoreCase("kwh")){
			XsxxJbszForm model=new XsxxJbszForm();
			XsxxYnysService ynysService = new XsxxYnysService();
			CommanForm dataSearchForm =new CommanForm();
			//��ʾ����
			request.setAttribute("xsqList",jbxxService.designXsxx(myForm));
			//�ֶ�λ��
			request.setAttribute("zdwzList", jbxxService.getZdwzList(myForm));
			//�����ֶ�
			request.setAttribute("qyzdList", jbxxService.getQyzdList(myForm));
			//��ʾ����ʾ�ֶ�
			request.setAttribute("xsqzdList", jbszService.getXsqZdwzList(myForm));
			//���������б�
			jbxxService.setXlkList(myForm, request);
			
			jbxxService.checkZdSize(request,model);
			request.setAttribute("ssList", ynysService.getSsList());
	        request.setAttribute("shiList", ynysService.getShiList(dataSearchForm.getJgshen()).get("shiList"));
			request.setAttribute("xianList",  ynysService.getShiList(dataSearchForm.getJgshen()).get("xianList"));
			FormModleCommon.setNjXyZyBjListForFdyBzr(request);
			//�Ǵ�ӡ��ת����ѧ����ϸ��Ϣ
			if(!"print".equalsIgnoreCase(page)){
				return mapping.findForward("xsxxcx");
			}else{
				return mapping.findForward("xsxxcxPrint");
			}
		}
		return mapping.findForward(page);
	}

	private ActionForward stuInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// ���ݲ�ѯ
		CommanForm dataSearchForm = (CommanForm) form;
		HttpSession session = request.getSession();
		StuInfoDAO stuDao = new StuInfoDAO();
		DAO dao = DAO.getInstance();
		String userType = (String) session.getAttribute("userOnLine");
		String userSpecType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName = (String) session.getAttribute("userName");
		String xxdm = StandardOperation.getXxdm();
		String tableName=request.getParameter("tableName");
//		String isBzr="";
		String oper = request.getParameter("oper");
		String moditag = dao.getOneRs("select modistuinfo from xtszb",
				new String[] {}, "modistuinfo");
		String writeAble = CheckPower.checkUsrPower(userName,
		"stu_info_query.do") ? "yes" : "no";
		String modiTag = dao.getOneRs("select modistuinfo from xtszb",
				new String[] {}, "modistuinfo");

		boolean disabled = true;
		RcglGzdxService rgService = new RcglGzdxService();
		if ("student".equalsIgnoreCase(userType)) {
			//�ճ�������ѧ������Ա���Բ�������ѧ��
			if(!"yes".equalsIgnoreCase(rgService.isXsgly_ser(userName))){
				return new ActionForward("/stu_info_details.do?xh=" + userName, false);
			}else{
				XsxxglService xsxxglService = new XsxxglService();
				HashMap<String, String> stuInfo = xsxxglService.selectStuinfo(userName);
				dataSearchForm.setXydm(stuInfo.get("xydm"));
			}
		}
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql���
		String querry = " where 1=1 ";// sql����
		String rsNum = "0";// ���صļ�¼��
		String nj = dataSearchForm.getNj();
		String xy = dataSearchForm.getXydm();
		String zy = dataSearchForm.getZydm();
		String bj = dataSearchForm.getBjdm();
		String xh = dataSearchForm.getXh();
		String xm = dataSearchForm.getXm();
		String ssbh = dataSearchForm.getSsbh();
		String zdmc=request.getParameter("zdmc");
		if (xy == null) {
			xy = "";
		}
		if (zy == null) {
			zy = "";
		}
		if ("xy".equalsIgnoreCase(userSpecType)
				&& (xy == null || xy.equalsIgnoreCase(""))
				&& !session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")) {
			xy = userDep;
			dataSearchForm.setXydm(xy);
			disabled = false;
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
			querry += "and xh like '%" + xh.trim() + "%' ";
		}
		if ((xm == null) || xm.equalsIgnoreCase("")) {
			querry += "and 1=1 ";
		} else {
			querry += "and xm like '%" + DealString.toGBK(xm) + "%' ";
			dataSearchForm.setXm(DealString.toGBK(xm));
		}
		if("true".equalsIgnoreCase(session.getAttribute("fdyQx").toString())){
			querry += " and exists(select 1 from view_fdybbj b where a.bjdm=b.bjdm and b.zgh='"+userName+"')";
		}
		if("true".equalsIgnoreCase(session.getAttribute("bzrQx").toString())){//���Ӱ��������ݲ�ѯ����Ȩ��
			querry += " and exists(select 1 from view_bzrbbj b where a.bjdm=b.bjdm and b.zgh='"+userName+"')";
		}
		// �㽭����
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJYS)) {
			querry += " and nj>((select dqnd from xtszb)-xz)";
		}
		// �б���ѧ
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZBDX)) {
			// �����Ҳ�ѯ
			if (ssbh == null || ssbh.equalsIgnoreCase("")) {
				querry += " and 1=1";
			} else {
				querry += " and ssbh='" + ssbh + "'";
			}
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
			colList = new String[] { "xh", "r", "xm", "xb", "nj", "xz", "bjmc",
			"zt" };
			sql = "select * from (select * from (select distinct a.xh,rownum r , a.xm,a.xb,a.nj,a.xz,a.bjmc,a.zt from view_bks_xsxx a"
				+ querry
				+ ") where r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize())
						+ " ) where r>"
						+ dataSearchForm.getPages().getStart();
			colListCN = dao.getColumnNameCN(colList, "view_bks_xsxx");
			
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_NTHYZYJSXY)
				&& "xszffb".equalsIgnoreCase(tableName)) {
			colList = new String[] { "xh", "r", "xm", "xb", "nj", "xz",
			"bjmc" };
			sql = "select * from (select * from (select distinct a.xh,rownum r, a.xm,a.xb,a.nj,a.xz,a.bjmc from xg_view_rcsw_wffxszxs a"
				+ querry
				+ " ) where r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize())
						+ ") where r>"
						+ dataSearchForm.getPages().getStart();
			colListCN = dao.getColumnNameCN(colList, "xg_view_rcsw_wffxszxs");
		} else {
			//�㽭��ҵ��ѧ������У����������Ҫ���ӷ���У��
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJGYZJJSXY)){
				if ("1".equalsIgnoreCase(modiTag)) {
					colList = new String[] { "xh", "r", "xm", "xb", "nj", "xz",
					"bjmc" };
					sql = "select * from (select * from (select a.*,rownum r from  (select distinct a.xh,a.xm,a.xb,a.nj,a.xz,a.bjmc from view_xsxxb a"
						+ querry
						+ " ) a ) where r<="
						+ (dataSearchForm.getPages().getStart() + dataSearchForm
								.getPages().getPageSize())
								+ ") where r>"
								+ dataSearchForm.getPages().getStart();
				} else {
					colList = new String[] { "r", "�к�", "xh", "xm", "xb", "nj",
							"xz", "bjmc" };
					sql = "select * from (select * from (select a.*,rownum r,rownum �к� (select distinct a.xh,a.xm,a.xb,a.nj,a.xz,a.bjmc from view_xsxxb a"
						+ querry
						+ ") a ) where r<="
						+ (dataSearchForm.getPages().getStart() + dataSearchForm
								.getPages().getPageSize())
								+ ") where r>"
								+ dataSearchForm.getPages().getStart();
				}
				colListCN = dao.getColumnNameCN(colList, "view_xsjbxx");
			}else{
				if ("1".equalsIgnoreCase(modiTag)) {
					colList = new String[] { "xh", "r", "xm", "xb", "nj", "xz",
					"bjmc" };
					sql = "select * from (select * from (select a.*,rownum r from  (select distinct a.xh,a.xm,a.xb,a.nj,a.xz,a.bjmc from view_xsjbxx a"
						+ querry
						+ " ) a ) where r<="
						+ (dataSearchForm.getPages().getStart() + dataSearchForm
								.getPages().getPageSize())
								+ ") where r>"
								+ dataSearchForm.getPages().getStart();
				} else {
					colList = new String[] { "r", "�к�", "xh", "xm", "xb", "nj",
							"xz", "bjmc" };
					sql = "select * from (select * from (select a.*,rownum r,rownum �к� (select distinct a.xh,a.xm,a.xb,a.nj,a.xz,a.bjmc from view_xsjbxx a"
						+ querry
						+ ") a ) where r<="
						+ (dataSearchForm.getPages().getStart() + dataSearchForm
								.getPages().getPageSize())
								+ ") where r>"
								+ dataSearchForm.getPages().getStart();
				}
				colListCN = dao.getColumnNameCN(colList, "view_xsjbxx");
			}

		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)
				&& "yes".equals(request.getParameter("gfs"))) {	
			colList = new String[] { "xh", "r", "xm", "xb", "nj", "xz","bjmc" };
			sql = "select * from (select * from (select t.*,rownum r from view_zgdd_gfsb t"
				+ querry
				+ " and isgfs='yes')) where r <= "
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize())
						+ " and r>"
						+ dataSearchForm.getPages().getStart();
			request.setAttribute("gfs", request.getParameter("gfs"));
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)
				&& "yes".equals(request.getParameter("wlxx"))) {	
			colList = new String[] { "xh", "r", "xm", "xb", "nj", "xz","bjmc" };
			sql = "select * from (select * from (select t.*,rownum r from view_zgdd_zbrb t"
				+ querry
				+ ")) where r <= "
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize())
						+ " and r>"
						+ dataSearchForm.getPages().getStart();
			request.setAttribute("wlxx", request.getParameter("wlxx"));
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_JHZYJSXY)) {	
			String zzlb = request.getParameter("zzlb");
			if(!Base.isNull(zzlb)){
				zzlb = "Ԥ���۲���".equalsIgnoreCase(DealString.toGBK(zzlb))?"ybybd":"jgmb";
				request.setAttribute("zzlb",zzlb );
			}	
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)
				&& "jq".equals(request.getParameter("jq"))) {	
			colList = new String[] { "xh", "r", "xm", "xb", "nj", "xz", "bjmc","xn","xq" };
			sql = "select * from (select * from (select rownum r,a.*,b.xn,b.xq from (select distinct a.xh,a.xm,a.xb,a.nj,a.xz,a.bjmc from view_xsjbxx a"
				+ querry
				+ " ) a ,view_hh_gygl_jqlxsq b where a.xh=b.xh and b.xxsh='ͨ��' )where r <= "
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize())
						+ ") where r>"
						+ dataSearchForm.getPages().getStart();
			request.setAttribute("jq", request.getParameter("jq"));
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)
				&& "by".equals(request.getParameter("by"))) {
			colList = new String[] { "xsxh", "r", "name", "xb", "nj", "bynd",
					"zymc", "bjmc" };
			sql = "select * from (select * from (select rownum r, a.*, b.xsxh,b.name,b.bynd, b.zymc  from (select distinct a.xh,a.xm,a.xb,a.nj,a.bjmc from view_xsjbxx a"
				+ querry
				+ " ) a ,jygl_xsjbxxb b where a.xh=b.xsxh )where r <= "
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize())
						+ ") where r>"
						+ dataSearchForm.getPages().getStart();
			request.setAttribute("by", request.getParameter("by"));
			colListCN = dao.getColumnNameCN(colList, "jygl_xsjbxxb");
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)
				&& "xs".equals(request.getParameter("jq"))) {
			colList = new String[] { "xh","r","xm", "xb","xymc","bjmc" };

			sql = "select * from (select * from (select rownum r,a.* from (select a.xh, a.xm, b.cym, a.xb, b.csrq, a.xymc, a.bjmc, a.sfzh,"
				+ " b.zkzh, b.mzmc, b.yzbm, b.jtdz, b.jtdh, a.lxdh ,c.rxcj, c.rdsj, c.rddd,"
				+ " c.rdjsr, c.grahtc, c.grjlkssj1, c.grjlkssj2, c.grjlkssj3, c.grjljssj1, c.grjljssj2, c.grjljssj3,"
				+ " c.grjlnr1, c.grjlnr2, c.grjlnr3, c.grjlzw1, c.grjlzw2, c.grjlzw3, c.jtcych1, c.jtcych2, c.jtcych3,"
				+ " c.jtcych4, c.jtcyxm1, c.jtcyxm2, c.jtcyxm3, c.jtcyxm4, c.jtcynl1, c.jtcynl2, c.jtcynl3, c.jtcynl4,"
				+ " c.jtcyzzmm1, c.jtcyzzmm2, c.jtcyzzmm3, c.jtcyzzmm4, c.jtcydw1, c.jtcydw2, c.jtcydw3, c.jtcydw4,"
				+ " c.jtcysr1, c.jtcysr2, c.jtcysr3,c.jtcysr4,c.jtcydh1,c.jtcydh2,c.jtcydh3,c.jtcydh4,c.shgxch1,c.shgxch2,"
				+ " c.shgxch3,c.shgxch4,c.shgxxm1,c.shgxxm2,c.shgxxm3,c.shgxxm4,c.shgxnl1,c.shgxnl2,c.shgxnl3,c.shgxnl4,"
				+ " c.shgxzzmm1,c.shgxzzmm2,c.shgxzzmm3,c.shgxzzmm4,c.shgxdw1,c.shgxdw2,c.shgxdw3,c.shgxdw4,c.shgxsr1,"
				+ " c.shgxsr2,c.shgxsr3,c.shgxsr4,c.shgxdh1,c.shgxdh2,c.shgxdh3,c.shgxdh4,c.bz,c.txrq from view_xsjbxx a,"
				+ " view_xsxxb b,jsxx_xsqkdjb c where a.xh = b.xh and b.xh=c.xh) a"
				+ querry
				+ " ) a )where r <= "
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize())
						+ "and r>"
						+ dataSearchForm.getPages().getStart();

			request.setAttribute("jq", request.getParameter("jq"));
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_JHZYJSXY) && !Base.isNull(request.getParameter("kns"))) {
			String nd = Base.currNd;
			
			if("yes".equals(request.getParameter("kns"))){
				colList = new String[] { "xh","r","xm", "xb","xymc","bjmc","xxsh" };
			sql = "select * from (select a.*,rownum r from (select a.xh,a.xm,a.xb,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.nj,b.xxsh from view_xsjbxx a,xszz_jhzy_kns b where a.xh=b.xh and nd='"
					+ nd
					+ "' and xxsh in ('����','��������')) a"
					+ querry
					+ ")where r <= "
					+ (dataSearchForm.getPages().getStart() + dataSearchForm
							.getPages().getPageSize())
					+ "and r>"
					+ dataSearchForm.getPages().getStart();
			}else if("bkzx".equals(request.getParameter("kns"))){
				colList = new String[] { "xh","r","xm", "xb","xymc","bjmc","xxsh" };
				sql = "select a.* from (select a.*,rownum r from (select a.xh,a.xm,a.xb,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.nj,b.xxsh from view_xsjbxx a,xszz_jhzy_kns b where a.xh=b.xh and nd='"
					+ nd
					+ "' and xxsh in ('����','��������')) a"
					+ querry
					+ ") a where not exists(select 1 from (select count(cj) num,xh,nd from (select substr(xn, 6, 4) nd, xh, cj from cjb) where cj < 60 group by nd,xh) b where a.xh=b.xh and b.nd = '"+nd+"') and r <= "
					+ (dataSearchForm.getPages().getStart() + dataSearchForm
							.getPages().getPageSize())
					+ "and r>"
					+ dataSearchForm.getPages().getStart();
			}
			request.setAttribute("kns", request.getParameter("kns"));
		}
		//System.out.println(request.getParameter("zzmm"));
		if (xxdm.equalsIgnoreCase(Globals.XXDM_JHZYJSXY)) {
			if (!Base.isNull(request.getParameter("zzmm"))) {
				if ("ybdy".equalsIgnoreCase(request.getParameter("zzmm"))) {
					querry += " and not exists (select 1 from dyxxb b where a.xh = b.xh)";
				} else if ("rdjjfz".equalsIgnoreCase(request
						.getParameter("zzmm"))) {
					querry += " and not exists (select 1 from dyxxb b where a.xh = b.xh)";
					querry += " and not exists (select 1 from rdjjfzxxb c where a.xh = c.xh)";
				}
				request.setAttribute("zzmm", request.getParameter("zzmm"));
			}
		}
		List<HashMap<String, String>> topTr = dao.arrayToList(colList, colListCN);

		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		// �ж�ѧУ�Ƿ������޸�ѧ����Ϣ
		if (modiTag != null && modiTag.equalsIgnoreCase("1")) {
			request.setAttribute("userOper", "yes");
		}
		// ѧԺֻ�ܵ�������
		if ("xy".equalsIgnoreCase(userSpecType)
				&& (xy != null || !xy.equalsIgnoreCase(""))) {
			request.setAttribute("writeble", "no");
		} else if ("xx".equalsIgnoreCase(userSpecType)
				|| "admin".equalsIgnoreCase(userSpecType)) {
			request.setAttribute("writeble", "yes");
		}
		// TODO ��ҳ
		//�㽭��ҵְҵ����ѧԺ�����ж�
		if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJGYZJJSXY)){
			sql = "select count(*) count from view_xsxxb a" + querry;
		}else{
			sql = "select count(*) count from view_xsjbxx a" + querry;
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)
				&& "yes".equals(request.getParameter("gfs"))) {	
			sql = "select count(*) count from view_zgdd_gfsb a" + querry +" and isgfs='yes'";
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NTHYZYJSXY)
				&& "xszffb".equalsIgnoreCase(tableName)){
			sql = "select count(*) count from xg_view_rcsw_wffxszxs a" + querry;
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)
				&& "yes".equals(request.getParameter("wlxx"))) {	
			sql = "select count(*) count from view_zgdd_zbrb a" + querry;
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_SHGC)
				|| xxdm.equalsIgnoreCase(Globals.XXDM_YNYS)) {
			sql = "select count(*) count from view_bks_xsxx a" + querry;
		}
		if (xxdm.equalsIgnoreCase(Globals.XXDM_HHGXY)
				&& "jq".equals(request.getParameter("jq"))) {	
			sql = "select count(*) count from view_hh_gygl_jqlxsq a" + querry;
			HashMap<String, String> map=new HashMap<String, String>();		
			map.put("en", "xn");
			map.put("cn", "ѧ��");
			topTr.add(map);
			HashMap<String, String> map2=new HashMap<String, String>();		
			map2.put("en", "xq");
			map2.put("cn", "ѧ��");
			topTr.add(map2);
		}
		
		if (xxdm.equalsIgnoreCase(Globals.XXDM_JHZYJSXY) && !Base.isNull(request.getParameter("kns"))) {
			String nd = Base.currNd;
			if("yes".equals(request.getParameter("kns"))){
				sql = "select count(*) count from xszz_jhzy_kns where nd = '"+nd+"'";
			}else if("bkzx".equals(request.getParameter("kns"))){
				sql = "select count(*) count from xszz_jhzy_kns a where nd = '"+nd+"' and  not exists(select 1 from (select count(cj) num,xh,nd from (select substr(xn, 6, 4) nd, xh, cj from cjb) where cj < 60 group by nd,xh) b where a.xh=b.xh and b.nd = '"+nd+"')";
			}
			topTr.remove(topTr.size()-2);
			HashMap<String, String> map2=new HashMap<String, String>();		
			map2.put("en", "xxsh");
			map2.put("cn", "ѧУ���");
			topTr.add(map2);
		}
		
		if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)&&"xs".equalsIgnoreCase(request.getParameter("jq"))){
			String query = " where a.xh = b.xh ";// sql����
			if ((nj == null) || nj.equalsIgnoreCase("")) {
				query += "and 1=1 ";
			} else {
				query += "and a.nj = '" + nj + "' ";
			}
			if ((xy == null) || xy.equalsIgnoreCase("")) {
				query += "and 1=1 ";
			} else {
				query += "and a.xydm = '" + xy + "' ";
			}
			if ((zy == null) || zy.equalsIgnoreCase("")) {
				query += "and 1=1 ";
			} else {
				query += "and a.zydm = '" + zy + "' ";
			}
			if ((bj == null) || bj.equalsIgnoreCase("")) {
				query += "and 1=1 ";
			} else {
				query += "and a.bjdm = '" + bj + "' ";
			}
			if ((xh == null) || xh.equalsIgnoreCase("")) {
				query += "and 1=1 ";
			} else {
				query += "and a.xh like '%" + xh.trim() + "%' ";
			}
			if ((xm == null) || xm.equalsIgnoreCase("")) {
				query += "and 1=1 ";
			} else {
				query += "and a.xm like '%" + DealString.toGBK(xm) + "%' ";
				dataSearchForm.setXm(DealString.toGBK(xm));
			}
			sql = "select count(b.xh) count from jsxx_xsqkdjb b,view_xsjbxx a" + query;
		}
		//============begin �й�����ѧԺ 2009/6/4 luojw ===========
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZGMSXY)&&"by".equalsIgnoreCase(request.getParameter("by"))){
			String query = " where 1=1 ";// sql����
			if ((nj == null) || nj.equalsIgnoreCase("")) {
				query += "and 1=1 ";
			} else {
				query += "and nj = '" + nj + "' ";
			}
			if ((xy == null) || xy.equalsIgnoreCase("")) {
				query += "and 1=1 ";
			} else {
				query += "and xydm = '" + xy + "' ";
			}
			if ((zy == null) || zy.equalsIgnoreCase("")) {
				query += "and 1=1 ";
			} else {
				query += "and zydm = '" + zy + "' ";
			}
			if ((bj == null) || bj.equalsIgnoreCase("")) {
				query += "and 1=1 ";
			} else {
				query += "and bjdm = '" + bj + "' ";
			}
			if ((xh == null) || xh.equalsIgnoreCase("")) {
				query += "and 1=1 ";
			} else {
				query += "and xsxh like '%" + xh.trim() + "%' ";
			}
			if ((xm == null) || xm.equalsIgnoreCase("")) {
				query += "and 1=1 ";
			} else {
				query += "and name like '%" + DealString.toGBK(xm) + "%' ";
				dataSearchForm.setXm(DealString.toGBK(xm));
			}
			sql = "select count(*) count from jygl_xsjbxxb a" + query;
		}
		//============end �й�����ѧԺ 2009/6/4 luojw ===========		
		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		request.setAttribute("oper", oper);
		request.setAttribute("userSpecType", userSpecType);
		request.setAttribute("disabled", disabled);
		request.setAttribute("njList", dao.getNjList());// �����꼶�б�
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", dao.getZyList(xy));// ����רҵ�б�
		request.setAttribute("bjList", dao.getBjList(xy, zy, nj));// ����רҵ�б�
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("modiTag", moditag);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("zdmc", zdmc);
		request.setAttribute("tableName", tableName);
//		if(xxdm.equalsIgnoreCase(Globals.XXDM_NCDXKJXY)){//�ϲ���ѧ��ѧ����ѧԺ
//		String tag = dao.returntag("select * from fdyxxb where zgh=? ",
//		new String[] { userName });
//		if ("notempty".equalsIgnoreCase(tag)) {//�����α��
//		String mySql = "select a.bjdm, bjmc from fdybjb b,view_njxyzybj a where a.bjdm=b.bjdm  and  b.zgh=?";
//		request.setAttribute("bjList", dao.getList(mySql,
//		new String[] { userName }, new String[] { "bjdm",
//		"bjmc" }));// ���Ͱ༶�б�
//		isBzr = "yes";
//		}else{
//		isBzr = "no";
//		}
//		}
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
//		if(session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")){
//			//����Ա��¼ֻ�ܲ��������༶��ѧ��
//			request.setAttribute("bjList", Fdypd.getFdybjList(userName));// ���Ͱ༶�б�
//			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));// ����רҵ�б�
//			request.setAttribute("xyList", Fdypd.getFdyXyList(userName));// ���Ͱ༶�б�
//			request.setAttribute("bjFdy", "yes");
//			userType = "bzr";
//			request.setAttribute("userType",userType);
//		}
//		request.setAttribute("isBzr", isBzr);	
		request.setAttribute("xjztList", stuDao.getXjztList());
		return mapping.findForward("success");
	}

	private ActionForward uploadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String dir = servlet.getServletContext().getRealPath("/upload");
		File f = new File(dir);
		if (!f.exists()) {
			f.mkdir();
		}
		HttpSession session = request.getSession();
		String fName = session.getAttribute("userName").toString();
		String tabName = request.getParameter("tabName");
		Timestamp date = new Timestamp(System.currentTimeMillis());
		fName += date.toString().substring(0, 19);
		fName = fName.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
		CommanForm hff = (CommanForm) form;
		FormFile file = hff.getFile();
		if (file == null) {
			return mapping.findForward("false");
		}
		int size = file.getFileSize();
		InputStream in = file.getInputStream();
		String filePath = dir + "/" + fName + ".xls";
		OutputStream out = new FileOutputStream(filePath);
		int bytesRead = 0;
		byte[] buffer = new byte[size];
		while ((bytesRead = in.read(buffer, 0, size)) != -1) {
			out.write(buffer, 0, bytesRead);
		}
		out.close();
		in.close();
		request.setAttribute("tabName", tabName);
		request.setAttribute("filepath", filePath);
		request.setAttribute("moditag", request.getParameter("moditag"));
		return mapping.findForward("success");
	}

	private ActionForward initDataItem(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		xgxt.studentInfo.service.StudentInfoService service = new xgxt.studentInfo.service.StudentInfoService();
		DAO dao = DAO.getInstance();
		String filePath = (String) request.getAttribute("filepath");
		String itemStr = Excel2Oracle.getDataItem(filePath);
		String tabName = request.getAttribute("tabName").toString();
		int itemNum = Excel2Oracle.getColumns(filePath);
		CommanForm importDataForm = (CommanForm) form;
		importDataForm.setItemStr(itemStr);
		importDataForm.setItemNum(itemNum);

		String sql = "select * from " + tabName;
		if (tabName != null && tabName.equalsIgnoreCase("xsxxb")) {
			//ѧ����Ϣ
			sql = service.getColumnOfXsxx();
		}
		if (tabName != null && tabName.equalsIgnoreCase("xsfzxxb")) {
			//ѧ��������Ϣ
			sql = service.getColumnOfXsfzxx();
		}
		String[] colName = dao.getColumnName(sql);
		String[] colNameCN = dao.getColumnNameCN(colName, tabName);
		request.setAttribute("tabName", tabName);
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> tmp = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < colName.length; i++) {
			map = new HashMap<String, String>();
			map.put("comments", colNameCN[i]);
			map.put("column_name", colName[i]);
			tmp.add(map);
		}
		List<HashMap<String, String>> orcleItemList = tmp;
		request.setAttribute("orcleItemList", orcleItemList);
		request.setAttribute("filepath", filePath);
		Object ob = request.getAttribute("moditag");
		String moditag = null;
		if (ob != null) {
			moditag = ob.toString();
		}
		if (moditag != null && !"".equalsIgnoreCase(moditag))
			return mapping.findForward("modity");
		else
			return mapping.findForward("success");
	}

	@SuppressWarnings("unchecked")
	private ActionForward importData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// HashMap<String, String> map = new HashMap<String, String>();
		CommanForm comm = (CommanForm)form;
		DAO dao = DAO.getInstance();
		HashMap<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String filePath = request.getParameter("filepath");
		String moditag = "";
		//String pk = request.getParameter("pk");

		moditag = (comm.getDrms()!=null &&"0".equalsIgnoreCase(comm.getDrms()) ? "moditag" : moditag);
		// Form Form=(Form)form;
		// String oracleItem=Form.getOracleItem();
		// int excelItem=Integer.parseInt(request.getParameter("excelItem"));
		String tableName = request.getParameter("tabName");
		String mappingItems = request.getParameter("mappingItems");
		String mappingItems1[] = mappingItems.split("!!SplitSignOne!!");
		int num = mappingItems1.length;
		String mappingItems2[][] = new String[num][];
		String xxdm = dao.getXxdm();
		for (int i = 1; i < num; i++) {
			mappingItems2[i] = mappingItems1[i].split("!!SplitSignTwo!!");
		}
		if (moditag == null || "".equalsIgnoreCase(moditag)) {
			map = Excel2Oracle.importData_error(mappingItems2, tableName,
					filePath, userName, userType);
		}
		if (moditag != null && !"".equalsIgnoreCase(moditag)) {
			map = Excel2Oracle.importData_modify(mappingItems2, tableName,
					filePath, userName, userType);
		}
		//�����������Ȩ�ּ��ۺ������ܷ�
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			if (StringUtils.isEqual(tableName, "zhszcp") || StringUtils.isEqual(tableName, "zhszcplsb")) {
				String[] jxjsqxnxq = dao.getOneRs("select jxjsqxn,jxjsqxq from xtszb where rownum=1", new String[]{}, new String[]{"jxjsqxn", "jxjsqxq"}); 
				String jxjsqxn = "";
				if (jxjsqxnxq != null && jxjsqxnxq.length == 2) {
					jxjsqxn = jxjsqxnxq[0];
				}
				String sql = "select xh from zhszcplsb where xn=? and (kcjqpfj is null or zhszcpzf is null)";
				String[] xh = dao.getRs(sql, new String[]{jxjsqxn}, "xh");//��Ҫ�����Ȩ�ּ��ۺϲ����ֵܷ�ѧ��
				String[] dcj = new String[xh.length];//ѧ���³ɼ��б�
				sql = "select dcj from zhszcplsb where xn=? and xh=?";
				for (int i = 0; i < xh.length; i++) {
					dcj[i] = dao.getOneRs(sql, new String[]{jxjsqxn, xh[i]}, "dcj");
					dcj[i] = !StringUtils.isNull(dcj[i]) ? dcj[i] : "0";
				}
				String[] jqf = new String[xh.length];//ѧ����Ȩ���б�
				//String[] zhcpzf = new String[xh.length];//ѧ���ۺϲ����ܷ��б�
				if (xh != null && xh.length > 0) {
					sql = "select round(sum(zxf) / sum(xf)) jqf from (select distinct a.xn,a.xh,a.kcmc," +
					"to_number(a.cj) cj,a.xf,round(cj * a.xf) zxf  from cjb a  " +
					"where a.bkcj is null and a.cxcj is null and (a.cxbj is null or a.cxbj = '0') " +
					"and a.kcxz <> 'У��ѡ�޿�'  and a.kcxz <> 'У��ѡ�޿�' and a.xh = ? and a.xn = ?) " +
					"group by xh, xn";
					for (int i = 0; i < xh.length; i++) {
						jqf[i] = dao.getOneRs(sql, new String[]{xh[i], jxjsqxn}, "jqf");
						jqf[i] = !StringUtils.isNull(jqf[i]) ? jqf[i] : "0";//��Ȩ��
						//�ۺϲ����ּܷ���
						sql = "update zhszcplsb set kcjqpfj=?,zhszcpzf=? where xh=? and xn=?";
						String zhszcpzf = "";
						float fJqf = Float.parseFloat(jqf[i]);
						float fDcj = Float.parseFloat(dcj[i]);
						zhszcpzf += ((int)((fJqf*75/100 + fDcj*25/100)*10))/10.0;
						dao.runUpdate(sql, new String[]{jqf[i], zhszcpzf, xh[i], jxjsqxn});
					}
					sql = "delete from zhszcp where xn=?";
					boolean bDel = dao.runUpdate(sql, new String[]{jxjsqxn});
					//�������ۺϲ�������
					if (bDel){
						sql = "insert into zhszcp (xn,nd,xq,xh,dcj,zcj,tcj,kcjqpfj,ddcj,xwcj,shqcj,zhszcpzf,bz) select xn,nd,xq,xh,dcj,zcj,tcj,kcjqpfj,ddcj,xwcj,shqcj,zhszcpzf,bz from zhszcplsb where xn=?";
						bDel = dao.runUpdate(sql, new String[]{jxjsqxn});
						if (!bDel) {
							//map.put("importInfo", "���ݸ�ʽ���󣬵���ʧ�ܣ���ȷ�ϣ�");
						}
					}
				}
			}
		}
		//�㽭�����ۺ����ʷּ���
		/*if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {
			if (StringUtils.isEqual(tableName, "zjjd_zhszcplsb")) {
				String[] jxjsqxnxq = dao.getOneRs(
						"select jxjsqxn,jxjsqxq,jxjsqnd from xtszb where rownum=1",
						new String[] {}, new String[] { "jxjsqxn", "jxjsqxq" ,"jxjsqnd"});
				String sql = "select xh,xn,xq,nd,xm,zyfjf,tycj,tyfjf,bz from zjjd_zhszcplsb where xn=? and xq=? and nd=?";

			}
		}*/
		if (map.get("importInfo") == null) {
			map.put("importInfo", "���ݵ���ɹ�!");
		}
		request.setAttribute("total", map.get("total"));
		if (map.get("succInfo") == null) {
			request.setAttribute("succInfo", "");
		} else {
			request.setAttribute("succInfo", map.get("succInfo"));
		}
		if (map.get("failInfo") == null) {
			request.setAttribute("failInfo", "");
		} else {
			request.setAttribute("failInfo", map.get("failInfo"));
		}

		if (map.get("errorList") != null) {
			request.setAttribute("errorList", map.get("errorList"));
		}

		if (null != map.get("error_file") && null != map.get("errorList")) {
			if (((List) map.get("errorList")).size() != 0
					&& map.get("error_file") != null) {
				request.setAttribute("error_file", map.get("error_file")
						.toString().substring(
								map.get("error_file").toString().lastIndexOf(
								"upload")));
			}
		}

		request.setAttribute("importInfo", map.get("importInfo"));
		request.setAttribute("tableName", tableName);
		return mapping.findForward("success");
	}

	@SuppressWarnings("unchecked")
	private ActionForward expData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// ���ݲ�ѯ
//		String col = request.getParameter("mappingItems");
		CommanForm dataSearchForm = (CommanForm) form;
		xgxt.studentInfo.service.StudentInfoService service = new xgxt.studentInfo.service.StudentInfoService();
		QgzxService qgzxService = new QgzxService();
		PjpyDAO pjpyDao = new PjpyDAO();
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String userName = session.getAttribute("userName").toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String xxdm = StandardOperation.getXxdm();
		String[] colList = null;
		String sql = "";// sql���
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		String tableName = "";// ��ѯ��ϢԴ����Ϊ��ͼ����
		
		User user = getUser(request);

		String xh = dataSearchForm.getXh();
		String xm = DealString.toGBK(dataSearchForm.getXm());
		String nj = dataSearchForm.getNj();
		String xn = dataSearchForm.getXn();
		String nd = dataSearchForm.getNd();
		String yf = dataSearchForm.getYf();
		String xjzt = dataSearchForm.getXjzt();
		String gwdm = DealString.toGBK(dataSearchForm.getGwdm());
		String yrdwdm = dataSearchForm.getYrdwdm();
		String xq = dataSearchForm.getXq();
		String xy = dataSearchForm.getXydm();
		String zy = dataSearchForm.getZydm();
		String bj = dataSearchForm.getBjdm();
		String xmdm = dataSearchForm.getXmdm();
		String doType = dataSearchForm.getDoType();
		String bzlx = dataSearchForm.getBzlxdm();
		String bzrq = dataSearchForm.getBzrq();
		String bzrq1 = dataSearchForm.getBzrq1();
		String bzrq2 = dataSearchForm.getBzrq2();
		String bzrqt = dataSearchForm.getBzrqt();
		String bzrq4 = dataSearchForm.getBzrq4();
		String xydmt = dataSearchForm.getXydmt();
		String xymc = dataSearchForm.getXymc();
		String rychdm = dataSearchForm.getRychdm();
		String xhzgh = dataSearchForm.getZgh();
		String xmlx = dataSearchForm.getXmlx();
		//����ʱ��
		String cssj = dataSearchForm.getCssj();
		
		// ��Ʊ
		String cc = dataSearchForm.getCc();
		String ccrq = dataSearchForm.getCcrq();
		String dz = dataSearchForm.getDz();
		String bxcc = (dataSearchForm.getBxcc() != null && dataSearchForm .getBxcc().equalsIgnoreCase("1")) ? "��" : "";
		String ksy = (dataSearchForm.getKsy() != null && dataSearchForm .getKsy().equalsIgnoreCase("1")) ? "��" : "";
		String klc = (dataSearchForm.getKlc() != null && dataSearchForm .getKlc().equalsIgnoreCase("1")) ? "��" : "";
		String kwz = (dataSearchForm.getKwz() != null && dataSearchForm .getKwz().equalsIgnoreCase("1")) ? "��" : "";
		String qt = (dataSearchForm.getQt() != null && dataSearchForm.getQt() .equalsIgnoreCase("1")) ? "��" : "";

		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		tableName = request.getParameter("tableName");
		String realTable = request.getParameter("realTable");
		String jxjdm1 = DealString.toGBK(dataSearchForm.getJxjdm1());
		String jxjdm = dataSearchForm.getJxjdm();
		String type = request.getParameter("type");	
		String sdlddm = DealString.toGBK(request.getParameter("lddm"));
		String jxdm = DealString.toGBK(request.getParameter("jxdm"));

		//У���ڹ���ѧ
		String sqdwmc = DealString.toGBK(request.getParameter("sqdwmc"));
		String sqsj = request.getParameter("sqsj");
		String fflx = DealString.toGBK(dataSearchForm.getFflx());
		String yesNo = DealString.toGBK(dataSearchForm.getYesNo());
		String sqrq = DealString.toGBK(dataSearchForm.getSqrq());
		String zwdm = DealString.toGBK(request.getParameter("zwdm"));
		String sfjw = request.getParameter("sfjw");
		String expType = "";
		
		// ============ 2010/3/25 luojw begin ��������===============
		String zzzt = request.getParameter("zzzt");//���Ž��� ��ְ״̬
		String jczc = request.getParameter("jczc");
		String lx = request.getParameter("lx");//����
		String shzt = request.getParameter("shzt");//���״̬
		String kcxzTj = request.getParameter("kcxzTj");//�������� �ɼ���ѯ �γ�����
		String qxfqj = request.getParameter("qxfqj");//���� Ƿ�ѷ�����
		String zbrydm = request.getParameter("zbrydm");//��Ԣ���� ֵ����
		String xqdm = request.getParameter("xqdm");//��Ԣ���� У������
		String lddm = request.getParameter("lddm");//��Ԣ���� ¥������
		String kmdm = request.getParameter("kmdm");//������չ ��Ŀ����
		String xmmc = request.getParameter("xmmc");//������չ ��Ŀ����
		String xb = request.getParameter("xb");//�Ա�
		String xmjb = request.getParameter("xmjb");//������չ ��Ŀ����
		String sffk = request.getParameter("sffk");//������ѧ���� �Ƿ�Ŵ�
		String sfzh = request.getParameter("sfzh");//���֤�ź�
		String xysh = request.getParameter("xysh");//ѧԺ���
		String xxsh = request.getParameter("xxsh");//ѧУ���
		String shjg = request.getParameter("shjg");//��˽��
		String gwfbr = request.getParameter("gwfbr");
		String wjm = request.getParameter("wjm");//�ļ���
		String jsr = request.getParameter("jsr");// ������
		String daqk = request.getParameter("daqk");// �������
		String cs = request.getParameter("cs");// ����
		String qsh = request.getParameter("qsh");// ���Һ�
		String kssj = request.getParameter("kssj");// ��ʼʱ��
		String jssj = request.getParameter("jssj");// ����ʱ��
		String head = StringUtils.isNull(request.getParameter("head")) ? "" : request.getParameter("head");
		String zt= request.getParameter("zt");
		String lydm=request.getParameter("lydm");
		// view_zjcm_wsjc
		// ����
		String[] tj = new String[] { "zzzt", "jczc", "lx", "shzt", "kcxzTj",
				"qxfqj", "zbrydm", "xqdm", "lddm", "kmdm", "xb", "xmjb",
				"xysh", "xxsh", "shjg", "wjm", "jsr", "daqk", "cs", "qsh" };
		// ����ֵ
		String[] tjz = new String[] { zzzt, jczc, lx, shzt, kcxzTj, qxfqj,
				zbrydm, xqdm, lddm, kmdm, xb, xmjb, xysh, xxsh, shjg, wjm, jsr,
				daqk, cs, qsh };
		
		querry.append(getQuery(tj, tjz));
		
		if (!Base.isNull(xmmc)) {
			querry.append("and xmmc like '%");
			querry.append(xmmc);
			querry.append("%' ");
		}
		if (!Base.isNull(gwfbr)) {
			querry.append("and gwfbr = '");
			querry.append(gwfbr);
			querry.append("' ");
		}
		if (!Base.isNull(dataSearchForm.getZddwmc())) {
			querry.append("and zddwmc like '%");
			querry.append(DealString.replaceImmitStr(dataSearchForm.getZddwmc()));
			querry.append("%' ");
		}
		if (!Base.isNull(dataSearchForm.getJyh())) {
			querry.append("and jyh like '%");
			querry.append(DealString.replaceImmitStr(dataSearchForm.getJyh()));
			querry.append("%' ");
		}
		if("view_ktergysjcb".equalsIgnoreCase(tableName) && !Base.isNull(dataSearchForm.getCssj())){
			querry.append(" and cssj= '");
			querry.append(cssj);
			querry.append("' ");
		}
		if("view_zjcm_wsjc".equalsIgnoreCase(tableName)){//�㽭��ý ��Ԣ�������
			querry.append(Base.isNull(kssj) ? "" : " and a.jcsj >= '" + kssj
					+ "'");
			querry.append(Base.isNull(jssj) ? "" : " and a.jcsj <= '" + jssj
					+ "'");
		}
		if("view_xytbgxxsxx".equalsIgnoreCase(tableName)){
			if(!Base.isNull(lydm)){
				querry.append(" and lydm='"+lydm+"' ");
			}
		}
		if("view_gwxx".equalsIgnoreCase(tableName) 
				&& qgzxService.isYrdwUser(userName)){
//			querry.append("and exists(select 1 from yrdwdmb b where a.sqdw=b.yrdwdm and b.dlm='");
//			querry.append(userName);
//			querry.append("') ");
			yrdwdm = qgzxService.getYrdwUser(userName);
			dataSearchForm.setYrdwdm(yrdwdm);
		}
		if("view_xsgwxx".equalsIgnoreCase(tableName) 
				&& qgzxService.isYrdwUser(userName)){
//			querry.append("and exists(select 1 from yrdwdmb b where a.yrdwdm=b.yrdwdm and b.dlm='");
//			querry.append(userName);
//			querry.append("') ");
			yrdwdm = qgzxService.getYrdwUser(userName);
			dataSearchForm.setYrdwdm(yrdwdm);
		}
		if("view_gwxx".equalsIgnoreCase(tableName) ){
			String sfgq = request.getParameter("sfgq");//�Ƿ����
			if(sfgq != null && "yx".equalsIgnoreCase(sfgq)){
				//��Ч��λ
				if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){//���ݴ�ѧ
					querry.append(" and gzjsrq>to_char(sysdate,'yyyymmdd')");
				}else if(Globals.XXDM_ZJXY.equalsIgnoreCase(xxdm)){ //�㽭��ҵ��ѧ֮��ѧԺ
					querry.append(" and gzjsrq>to_char(sysdate,'yyyymmdd') and shjg='ͨ��' and sqkssj<=to_char(sysdate,'YYYYMMDDHH24MISS') and sqjssj>=to_char(sysdate,'YYYYMMDDHH24MISS') ");
				}else{
					querry.append(" and gzjsrq>to_char(sysdate,'yyyymmdd') and shjg='ͨ��'");
				}
			}else if(sfgq != null && "gq".equalsIgnoreCase(sfgq)){
				//���ڸ�λ
				if(Globals.XXDM_ZJXY.equalsIgnoreCase(xxdm)){//�㽭��ҵ��ѧ֮��ѧԺ
					querry.append(" and (gzjsrq<to_char(sysdate,'yyyymmdd') or shjg<>'ͨ��' or sqkssj>to_char(sysdate,'YYYYMMDDHH24MISS') or sqjssj<to_char(sysdate,'YYYYMMDDHH24MISS'))");
				}else{
					querry.append(" and (gzjsrq<to_char(sysdate,'yyyymmdd') or shjg<>'ͨ��')");
				}
			}
			
		}
		if("view_bks_xjydxx".equalsIgnoreCase(tableName) ){//ѧ���춯�����ѯ
			String ydlbm = request.getParameter("ydlbm");//�춯���
			if (!Base.isNull(ydlbm)) {
				querry.append(" and ydlbm ='");
				querry.append(ydlbm);
				querry.append("' ");
			}
			String ydqnj = request.getParameter("ydqnj");//�춯ǰ�꼶
			if (!Base.isNull(ydqnj)) {
				querry.append(" and ydqnj ='");
				querry.append(ydqnj);
				querry.append("' ");
			}
			String ydqxydm = request.getParameter("ydqxydm");//�춯ǰѧԺ
			if (!Base.isNull(ydqxydm)) {
				querry.append(" and ydqxydm ='");
				querry.append(ydqxydm);
				querry.append("' ");
			}
			String ydqzydm = request.getParameter("ydqzydm");//�춯ǰרҵ
			if (!Base.isNull(ydqzydm)) {
				querry.append(" and ydqzydm ='");
				querry.append(ydqzydm);
				querry.append("' ");
			}
			String ydqbdm = request.getParameter("ydqbdm");//�춯ǰ�༶
			if (!Base.isNull(ydqbdm)) {
				querry.append(" and ydqbdm ='");
				querry.append(ydqbdm);
				querry.append("' ");
			}
			String ydhnj = request.getParameter("ydhnj");//�춯���꼶
			if (!Base.isNull(ydhnj)) {
				querry.append(" and ydhnj ='");
				querry.append(ydhnj);
				querry.append("' ");
			}
			String ydhxydm = request.getParameter("ydhxydm");//�춯��ѧԺ
			if (!Base.isNull(ydhxydm)) {
				querry.append(" and ydhxydm ='");
				querry.append(ydhxydm);
				querry.append("' ");
			}
			String ydhzydm = request.getParameter("ydhzydm");//�춯��רҵ
			if (!Base.isNull(ydhzydm)) {
				querry.append(" and ydhzydm ='");
				querry.append(ydhzydm);
				querry.append("' ");
			}
			String ydhbdm = request.getParameter("ydhbdm");//�춯��༶
			if (!Base.isNull(ydhbdm)) {
				querry.append(" and ydhbdm ='");
				querry.append(ydhbdm);
				querry.append("' ");
			}
			String sqkssj = request.getParameter("sqkssj");//����ʱ�俪ʼ
			if (!Base.isNull(sqkssj)) {
				querry.append(" and to_date(sqsj,'yyyy-mm-dd') >= to_date('");
				querry.append(sqkssj);
				querry.append("','yyyymmdd') ");
			}
			String sqjssj = request.getParameter("sqjssj");//����ʱ�����
			if (!Base.isNull(sqjssj)) {
				querry.append(" and to_date(sqsj,'yyyy-mm-dd') <= to_date('");
				querry.append(sqjssj);
				querry.append("','yyyymmdd') ");
			}
		}
		if("view_rgysyzdmb".equalsIgnoreCase(tableName) && StringUtils.isNotNull(cssj)){
			querry.append(" and cssj='"+cssj+"' ");
		}
		if (!Base.isNull(kcxzTj)) {
			String[] kcxz = kcxzTj.split("!!@@!!");
			if (kcxz != null && kcxz.length > 0) {
				querry.append(" and (");
				for (int i = 0; i < kcxz.length; i++) {
					if(i == 0){
						querry.append(" kcxz = '" + kcxz[i] + "'");
					}else{
						querry.append(" or kcxz = '" + kcxz[i] + "'");
					}
				}
				querry.append(" )");
			}
		}
		
		if ("view_xsbxxx".equalsIgnoreCase(tableName)) {//ѧ��������Ϣ
			querry.append(" and sqsj is not null ");
		}
		
		// ��ٹ���
		else if("xg_view_rcsw_xssq".equalsIgnoreCase(tableName)){
			String qjts = request.getParameter("qjts");
			
			if(StringUtils.isNotNull(qjts)){
				querry.append(" and qjts='").append(qjts).append("'");
			}
		}
		
		if ("view_zgmsxy_gjzxdk".equalsIgnoreCase(tableName)) {//������ѧ�������
			String xydm = dataSearchForm.getXydm();
			String zydm = dataSearchForm.getZydm();
			String bjdm = dataSearchForm.getBjdm();
			
			if(StringUtils.isNotNull(xh)){
				querry.append(" and xh like '%").append(xh).append("%'");
			}
			
			if(StringUtils.isNotNull(sfzh)){
				querry.append(" and sfzh like '%").append(sfzh).append("%'");
			}
			
			if(StringUtils.isNotNull(nj)){
				querry.append(" and nj='").append(nj).append("'");
			}
			
			if(StringUtils.isNotNull(xydm)){
				querry.append(" and xydm='").append(xydm).append("'");
			}
			
			if(StringUtils.isNotNull(zydm)){
				querry.append(" and zydm='").append(zydm).append("'");
			}
			
			if(StringUtils.isNotNull(bjdm)){
				querry.append(" and bjdm='").append(bjdm).append("'");
			}
			
			if(!Base.isNull(sffk)){
				querry.append(" and hth is not null ");
			}
		}
		
		
		/*if ("view_zgmsxy_gjzxdkgrxx".equalsIgnoreCase(tableName)) {//��ҵ����Ϣ
			if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {
				querry.append(" and xysh = 'ͨ��' ");
			}
		}*/
		// ============ 2010/3/25 luojw end ===============
		String[] expCloName = null;
		
		DataManService dataService = new DataManService();
		
		// ===============2011.8.26 qlj====================
		String isFdyStr = String.valueOf(Fdypd.isFdy(userName));
		// ===============2011.8.26 end ====================
		
		if ((null != jxjdm1) && (!"".equalsIgnoreCase(jxjdm1))) {
			String[] jxjTemp = DealString.toGBK(jxjdm1).split("!!splitOne!!");
			jxjdm = jxjTemp[0];
		}		
		if (tableName == null) {
			tableName = " ";
		}
		if (tableName.equalsIgnoreCase("sztz_xmdmb")) {
			tableName = "view_sztz_xmdmb_exp";
		}
		if (tableName.equalsIgnoreCase("sztz_sqsblyb")) {
			tableName = "view_sztz_sqsblyb_exp";
		}
		if (tableName.equalsIgnoreCase("sztz_hjjbdmb")) {
			tableName = "view_sztz_hjjbdmb_exp";
		}

		String zd = " * ";
//		if (xy==null) {
//		xy = "";
//		String xydm = request.getParameter("xydm");
//		if (null == xydm || "".equalsIgnoreCase(xydm)) {								
//		xy = userDep;						
//		} else {
//		xy = xydm;
//		}
//		}
		String fdyQx=session.getAttribute("fdyQx").toString();
		if(userType.equalsIgnoreCase("xy") && "false".equalsIgnoreCase(fdyQx)){			
			xy = userDep;			
		}
		if (zy == null) {
			zy = "";
		}
		
		
		if ((xm == null) || xm.equalsIgnoreCase("")) {
		} else {
			querry.append("and xm like '%");
			querry.append(xm);
			querry.append("%' ");
		}
		if ((jxjdm == null) || jxjdm.equalsIgnoreCase("")) {
		} else {
			querry.append("and jxjdm = '");
			querry.append(jxjdm);
			querry.append("' ");
		}
		if ((nd == null) || nd.equalsIgnoreCase("")) {
		} else {
			querry.append("and nd = '");
			querry.append(nd);
			querry.append("' ");
		}
		if ((xjzt == null) || xjzt.equalsIgnoreCase("")) {
		} else {
			querry.append("and xjzt = '");
			querry.append(xjzt);
			querry.append("' ");
		}
		if ((yf == null) || yf.equalsIgnoreCase("")) {
		} else {
			querry.append("and yf = '");
			querry.append(yf);
			querry.append("' ");
		}
		if ((gwdm == null) || gwdm.equalsIgnoreCase("")) {
		} else {
			querry.append("and gwdm like '%");
			querry.append(DealString.replaceImmitStr(gwdm));
			querry.append("%' ");
		}
		if ((yrdwdm == null) || yrdwdm.equalsIgnoreCase("")) {
		} else {
			if(tableName != null && "view_gwxx".equalsIgnoreCase(tableName)){
				querry.append("and sqdw = '");
				querry.append(yrdwdm);
				querry.append("' ");
			}else{
				querry.append("and yrdwdm = '");
				querry.append(yrdwdm);
				querry.append("' ");
			}
		}
		if(StringUtils.isNotNull(dataSearchForm.getGwflag())){
			querry.append("and gwflag = '");
			querry.append(dataSearchForm.getGwflag());
			querry.append("' ");
		}
		if(StringUtils.isNotNull(dataSearchForm.getJcfs())){
			querry.append("and jcfs = '");
			querry.append(dataSearchForm.getJcfs());
			querry.append("' ");
		}
		if(StringUtils.isNotNull(dataSearchForm.getXxyj()) && "view_gwxx".equalsIgnoreCase(tableName)){
			querry.append("and shjg = '");
			querry.append(dataSearchForm.getXxyj());
			querry.append("' ");
		}
		if(tableName != null && "view_gwxx".equalsIgnoreCase(tableName)){
			if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){//���ݴ�ѧ
				if(StringUtils.isNull(gwfbr)){
					querry.append(" and (gwfbr<> '" + userName + "' or gwfbr is null)");
				}
			}
		}
	//	boolean xhEq = true;
//		xhEq = dataService.checkXhEqOrLike(tableName);
//		if(xhEq){
//			if (xh != null && xh != "") {
//				querry.append("and xh = '");
//				querry.append(xh);
//				querry.append("' ");
//			}
//		}else{
			if (xh != null && xh != "") {
				querry.append("and xh like '%");
				querry.append(xh.replace("'","��"));
				querry.append("%' ");
			}
			if (sfzh != null && sfzh != "") {
				querry.append("and sfzh like '%");
				querry.append(sfzh.replace("'","��"));
				querry.append("%' ");
			}
//		}
		if ((nj == null) || nj.equalsIgnoreCase("")) {
		} else {
			querry.append("and nj = '");
			querry.append(nj);
			querry.append("' ");
		}
		if(rychdm!=null && !rychdm.equalsIgnoreCase("")){
			querry.append("and rychdm = '");
			querry.append(rychdm);
			querry.append("' ");
		}

		if ((sqrq == null) || sqrq.equalsIgnoreCase("")) {
		} else {
			querry.append("and sqrq = '");
			querry.append(sqrq);
			querry.append("' ");
		}
	
		if ((zzzt == null) || zzzt.equalsIgnoreCase("")) {
		} else {
			querry.append("and zzzt = '");
			querry.append(zzzt);
			querry.append("' ");
		}
		
		if ("view_wjcf".equalsIgnoreCase(tableName)) {
			String cflb = request.getParameter("cflb");
			String cfyy = request.getParameter("cfyy");
			if (StringUtils.isNotNull(cflb)) {
				querry.append(" and cflb = '");
				querry.append(cflb);
				querry.append("'");
			}
			if (StringUtils.isNotNull(cfyy)) {
				querry.append(" and cfyy = '");
				querry.append(cfyy);
				querry.append("'");
			}
		}
		
		//ѧ���ɲ�ְ��
		if(tableName.toLowerCase().equalsIgnoreCase("view_bjgbxx")){
			xh = DealString.toGBK(request.getParameter(""));
			xm = DealString.toGBK(request.getParameter("xm"));
			nj = DealString.toGBK(request.getParameter("nj"));
			xy = DealString.toGBK(request.getParameter("xy"));
			zy = DealString.toGBK(request.getParameter("zy"));
			bj = DealString.toGBK(request.getParameter("bj"));
			if(!zwdm.equalsIgnoreCase("")){
				querry.append("and bjgbdm = '");
				querry.append(zwdm);
				querry.append("' ");
			}
		}else if(tableName.toLowerCase().equalsIgnoreCase("view_xshgbxx")||tableName.toLowerCase().equalsIgnoreCase("view_xyxshgbxx")||tableName.toLowerCase().equalsIgnoreCase("view_xljkxshgbxx")){
			if(!zwdm.equalsIgnoreCase("")){
				querry.append("and xshgbdm = '");
				querry.append(zwdm);
				querry.append("' ");
			}
		}
		//�й����ʴ�ѧ�ڹ���ѧ���
		if(tableName.toLowerCase().equalsIgnoreCase("view_qgzxsqb")){
			if(userType != null || ("admin").equalsIgnoreCase(userType) || "xx".equalsIgnoreCase(userType)){
				if(yesNo!=null && !yesNo.equalsIgnoreCase("")){
					querry.append("and xxsh = '");
					querry.append(yesNo);
					querry.append("' ");
				}
			}else{
				if(yesNo!=null && !yesNo.equalsIgnoreCase("")){
					querry.append("and xysh = '");
					querry.append(yesNo);
					querry.append("' ");
				}
			}
		}
		if( xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)&&tableName.equalsIgnoreCase("view_xsxxb")){
			String sfyby = request.getParameter("sfyby");
			request.setAttribute("sfyby", sfyby);
			if (sfyby != null && sfyby.equalsIgnoreCase("yes")) {
				querry.append("and sfyby = '");
				querry.append(sfyby);
				querry.append("' ");
			}
		}
		//��ѵ�Ŷӻ�
		if(tableName.toLowerCase().equalsIgnoreCase("view_jxtdhj")){
			if ((sdlddm == null) || sdlddm.equalsIgnoreCase("")) {
			} else {
				querry.append("and lddm = '");
				querry.append(sdlddm);
				querry.append("' ");
			}

			if ((jxdm == null) || jxdm.equalsIgnoreCase("")) {
			} else {
				querry.append("and jxdm = '");
				querry.append(jxdm);
				querry.append("' ");
			}
		}

		//��ѵѧ����ѵ�ɲ�
		if(tableName.toLowerCase().equalsIgnoreCase("view_jxxscxgb")){
			String fzlddm = DealString.toGBK(request.getParameter("fzlddm"));
			String jxnd = DealString.toGBK(request.getParameter("jxnd"));

			if ((fzlddm == null) || fzlddm.equalsIgnoreCase("")) {
			} else {
				querry.append("and fzlddm = '");
				querry.append(fzlddm);
				querry.append("' ");
			}

			if ((jxnd == null) || jxnd.equalsIgnoreCase("")) {
			} else {
				querry.append("and jxnd = '");
				querry.append(jxnd);
				querry.append("' ");
			}
		}

		//У���λ��Ϣ
		if(tableName.equalsIgnoreCase("qgzx_xwgwxxb")){
			if(sqdwmc !=null && !sqdwmc.equalsIgnoreCase("")){
				querry.append(" and sqdwmc = '");
				querry.append(sqdwmc);
				querry.append("'");
			}
			if(sqsj !=null && !sqsj.equalsIgnoreCase("")){
				querry.append(" and sqsj like '");
				querry.append(sqsj);
				querry.append("%'");
			}
		}
		
		//==============begin luojw 2010/2/3 ===================
		
		if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
			//��Ԣ����
			xqdm = request.getParameter("xqdm");
			lddm = request.getParameter("lddm");
			cs = request.getParameter("cs");
			qsh = request.getParameter("qsh");
			String fbbj = request.getParameter("fbbj");
			String zzbj = request.getParameter("zzbj");
			String sfbj = request.getParameter("sfbj");
			
			if (!Base.isNull(xqdm)) {
				querry.append(" and xqdm = '");
				querry.append(xqdm);
				querry.append("'");
			}
			if (!Base.isNull(lddm)) {
				querry.append(" and lddm = '");
				querry.append(lddm);
				querry.append("'");
			}
			if (!Base.isNull(cs)) {
				querry.append(" and cs = '");
				querry.append(cs);
				querry.append("'");
			}
			if (!Base.isNull(qsh)) {
				querry.append(" and qsh = '");
				querry.append(qsh);
				querry.append("'");
			}
			if (!Base.isNull(fbbj)) {
				querry.append(" and fbbj = '");
				querry.append(fbbj);
				querry.append("'");
			}
			if (!Base.isNull(zzbj)) {
				querry.append(" and zzbj = '");
				querry.append(zzbj);
				querry.append("'");
			}
			if (!Base.isNull(lx)) {
				querry.append(" and lx = '");
				querry.append(lx);
				querry.append("'");
			}
			if (!Base.isNull(sfbj)) {
				querry.append(" and sfbj = '");
				querry.append(sfbj);
				querry.append("'");
			}
		}
		//	==============end luojw 2010/2/3 ===================
		
		// �㽭��
		if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)) {
			//��֧�����
			if (tableName.equalsIgnoreCase("view_xspxxx")
					|| tableName.equalsIgnoreCase("view_rdjjfzxx")
					|| tableName.equalsIgnoreCase("view_ybdyxx")
					|| tableName.equalsIgnoreCase("view_dyxx")
					|| tableName.equalsIgnoreCase("view_zjlg_zbxx")) {
				String zbdm = DealString.toGBK(request.getParameter("zbdm"));
				if (zbdm != null && !zbdm.equalsIgnoreCase("")) {
					querry.append(" and zbdm = '");
					querry.append(zbdm);
					querry.append("'");
				}
			}
			//��֧�����
			if (tableName.equalsIgnoreCase("view_zjlg_zzgx")) {
				String zjlx = DealString.toGBK(request.getParameter("zjlx"));
				if (zjlx != null && !zjlx.equalsIgnoreCase("")) {
					querry.append(" and zjlx = '");
					querry.append(zjlx);
					querry.append("'");
				}
			}
			//���⽻��
			if (tableName.equalsIgnoreCase("view_zjlg_dwjl")) {
				String zjlx = DealString.toGBK(request.getParameter("jlxm"));
				if (zjlx != null && !zjlx.equalsIgnoreCase("")) {
					querry.append(" and jlxm = '");
					querry.append(zjlx);
					querry.append("'");
				}
			}
		}
		
		//�й����ʴ�ѧ���������Ͳ�ѯ
		if(tableName.equalsIgnoreCase("view_xscjff")){
			if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
				if ((fflx == null) || fflx.equalsIgnoreCase("")) {
				} else if(fflx != null && "��ʱ�ڹ���".equalsIgnoreCase(fflx)){
					querry.append(" and exists(select 1 from view_gwxx b where a.gwdm=b.gwdm and a.sqsj=b.gwsbsj and b.gwxzmc like '%��ʱ%') and (fflx<>'����' or fflx is null)");
				}else if(fflx != null && "��������".equalsIgnoreCase(fflx)){
					querry.append(" and fflx='����'");
				}else{
					querry.append("  and exists(select 1 from view_gwxx b where a.gwdm=b.gwdm and a.sqsj=b.gwsbsj and (b.gwxzmc like '%�̶�%' or b.gwxzmc is null)) and (fflx<>'����' or fflx is null)");
				}
				sql = "select xh||gwdm||sqsj||nd||yf ����, nd, yf, xqmc, xh, xm, bjmc, gwdm, cjje, ffsj, gzsj, decode(fflx,'','����',fflx) fflx, xxsh from " + tableName + " a " + querry;
			}
		}
		if (tableName.equalsIgnoreCase("view_xsjxjb")) {
			if (userType.equalsIgnoreCase("xx")) {//��ѯ��ҳ��û����˽���Ŀ��ƣ����²�ѯ�����ݺ͵��������ݲ�һ�� 
				//querry.append(" and xxsh='ͨ��' ");
			} else {
				//querry.append(" and xysh='ͨ��' and xydm='");
				querry.append(" and xydm='");
				querry.append(userDep);
				querry.append("' ");
			}
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				shzt = request.getParameter("shzt");
				shzt = StringUtils.isNull(shzt) ? "" : shzt;
				if (StringUtils.isEqual("0", shzt)) {
					querry.append(" and fdysh='ͨ��' and xysh='ͨ��' and xxsh='ͨ��' ");
				} else if (StringUtils.isEqual("1", shzt)) {
					querry.append(" and fdysh='��ͨ��' and xysh='��ͨ��' and xxsh='��ͨ��' ");
				} else if (StringUtils.isEqual("2", shzt)) {
					querry.append(" and fdysh='δ���' and xysh='δ���' and xxsh='δ���' ");
				}
			} else if (xxdm.equalsIgnoreCase(Globals.XXDM_SHCBYSGDZKXX)) {
				String bz = request.getParameter("bz");
				bz = StringUtils.isNull(bz) ? "" : bz.trim();
				jxjdm = request.getParameter("jxjdm");
				if (!StringUtils.isNull(jxjdm)) {
					querry.append(" and jxjdm ='"+jxjdm+"'");
				}
				if ("xy".equalsIgnoreCase(userType)) {
					String isFdy = session.getAttribute("isFdy").toString();
					if ("true".equalsIgnoreCase(isFdy)) {

						if (!StringUtils.isNull(bz)) {
							if ("0".equalsIgnoreCase(bz)) {
								bz = " and fdysh='δ���'";
							} else if ("1".equalsIgnoreCase(bz)) {
								bz = " and fdysh='��ͨ��'";
							} else if ("2".equalsIgnoreCase(bz)) {
								bz = " and fdysh='ͨ��'";
							} else 
								bz = "";
						} else {
							bz = "";
						}
						querry.append(" and exists(select 1 from view_fdybbj d where view_xsjxjb.bjdm=d.bjdm and d.zgh='"+userName+"')");
					} else {
						if (!StringUtils.isNull(bz)) {
							if ("0".equalsIgnoreCase(bz)) {
								bz = " and xysh='δ���' and fdysh='ͨ��'";
							} else if ("1".equalsIgnoreCase(bz)) {
								bz = " and xysh='��ͨ��' and fdysh='ͨ��'";
							} else if ("2".equalsIgnoreCase(bz)) {
								bz = " and xysh='ͨ��' and fdysh='ͨ��'";
							} else 
								bz = " and fdysh='ͨ��'";
						} else {
							//bz = " and fdysh='ͨ��'";
							bz = "";
						}
					}
				} else {
					if (!StringUtils.isNull(bz)) {
						if ("0".equalsIgnoreCase(bz)) {
							bz = " and xxsh='δ���' and xysh='ͨ��' and fdysh='ͨ��'";
						} else if ("1".equalsIgnoreCase(bz)) {
							bz = " and xxsh='��ͨ��' and xysh='ͨ��' and fdysh='ͨ��'";
						} else if ("2".equalsIgnoreCase(bz)) {
							bz = " and xxsh='ͨ��' and xysh='ͨ��' and fdysh='ͨ��'";
						} else 
							bz = " and fdysh='ͨ��' and xysh='ͨ��'";
					} else {
						//bz = " and fdysh='ͨ��' and xysh='ͨ��'";
						bz = "";
					}
				}
				querry.append(bz);
			}
		}
		if ("view_zgkd_zhszcp".equalsIgnoreCase(tableName)) {//�й���ҵ��ѧ���ʲ�������
			String jbszcpdj = request.getParameter("szdj");
			if (!StringUtils.isNull(jbszcpdj)) {
				jbszcpdj = dao.getOneRs(
						"select szdjmc from szdjdmb where szdjdm=?",
						new String[] { jbszcpdj }, "szdjmc");
				querry.append(" and jbszcpdj='");
				querry.append(jbszcpdj);
				querry.append("' ");
			}
			String isFdy = session.getAttribute("isFdy") == null ? "" : session
					.getAttribute("isFdy").toString();
			if ("true".equalsIgnoreCase(isFdy)) {
				querry.append(" and exists(select 1 from view_fdybbj c where "+tableName+".bjdm=c.bjdm and c.zgh='"+userName+"') ");
			}
		}
		if (tableName.equalsIgnoreCase("view_cptj")) {
			zd = "ccrq,cc,dzmc,yzzs,bxcc,ksy,klc,kwz,qt";
			sql = "select xn,nd,xq from cpydsjb where rownum=1";
			colList = dao.getOneRs(sql, new String[] {}, new String[] { "xn","nd", "xq" });
			xn = colList[0];
			nd = colList[1];
			xq = colList[2];
			dataSearchForm.setXn(xn);
			dataSearchForm.setNd(nd);
			dataSearchForm.setXq(xq);

			if ((ccrq == null) || ccrq.equalsIgnoreCase("")) {
			} else {
				querry.append("and ccrq = '");
				querry.append(ccrq);
				querry.append("' ");
			}
			if ((cc == null) || cc.equalsIgnoreCase("")) {
			} else {
				querry.append("and cc = '");
				querry.append(cc);
				querry.append("' ");
			}
			if ((dz == null) || dz.equalsIgnoreCase("")) {
			} else {
				querry.append("and dz = '");
				querry.append(dz);
				querry.append("' ");
			}
			if ((bxcc == null) || bxcc.equalsIgnoreCase("")) {
			} else {
				querry.append("and bxcc = '");
				querry.append(bxcc);
				querry.append("' ");
			}
			if ((ksy == null) || ksy.equalsIgnoreCase("")) {
			} else {
				querry.append("and ksy = '");
				querry.append(ksy);
				querry.append("' ");
			}
			if ((klc == null) || klc.equalsIgnoreCase("")) {
			} else {
				querry.append("and klc = '");
				querry.append(klc);
				querry.append("' ");
			}
			if ((kwz == null) || kwz.equalsIgnoreCase("")) {
			} else {
				querry.append("and kwz = '");
				querry.append(kwz);
				querry.append("' ");
			}
			if ((qt == null) || qt.equalsIgnoreCase("")) {
			} else {
				querry.append("and qt = '");
				querry.append(qt);
				querry.append("' ");
			}
			if ((nd == null) || nd.equalsIgnoreCase("")) {
			} else {
				querry.append("and nd = '");
				querry.append(nd);
				querry.append("' ");
			}

		}
		if (tableName.equalsIgnoreCase("view_xsrychb")) {
			/*sql = "select jxjsqxn,jxjsqnd from xtszb where rownum=1";
			colList = dao.getOneRs(sql, new String[] {}, new String[] {"jxjsqxn", "jxjsqnd" });
			String sxn = colList[0];
			String snd = colList[1];
			querry.append("and xn = '");
			querry.append(sxn);
			querry.append("' and nd = '");
			querry.append(snd);
			querry.append("' ");*/
			if (xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)) {
				shzt = request.getParameter("shzt");
				shzt = StringUtils.isNull(shzt) ? "" : shzt;
				if (StringUtils.isEqual("0", shzt)) {
					querry.append(" and fdysh='ͨ��' and xysh='ͨ��' and xxsh='ͨ��' ");
				} else if (StringUtils.isEqual("1", shzt)) {
					querry.append(" and fdysh='��ͨ��' and xysh='��ͨ��' and xxsh='��ͨ��' ");
				} else if (StringUtils.isEqual("2", shzt)) {
					querry.append(" and fdysh='δ���' and xysh='δ���' and xxsh='δ���' ");
				}
			}
		} else {
			if ((xn == null) || xn.equalsIgnoreCase("")) {
			} else {
				querry.append("and xn = '");
				querry.append(xn);
				querry.append("' ");
			}
		}
		if ((xq == null) || xq.equalsIgnoreCase("")) {
		} else {
			if(tableName != null && !"view_gwxx".equalsIgnoreCase(tableName)){
				querry.append("and xq = '");
				querry.append(xq);
				querry.append("' ");
			}else{
				querry.append("and xueqi = '");
				querry.append(xq);
				querry.append("' ");
			}
		}
		if ((xy == null) || xy.equalsIgnoreCase("")) {
			if (!userType.equalsIgnoreCase("xx")
					&& (Globals.expHaveXydm(tableName))) {
				querry.append("and xydm='");
				querry.append(userDep);
				querry.append("' ");
			}			
		} else {			
			if ("view_fdyxxbAllinfo".equalsIgnoreCase(tableName)) {
				querry.append("and xydm = '");
				querry.append(xy);
				querry.append("' ");
			} else if("qgzx_xwgwxxb".equalsIgnoreCase(tableName) 
					|| "view_gwxx".equalsIgnoreCase(tableName)
					|| "view_bzrtxl".equalsIgnoreCase(tableName)
					|| "view_xjydjbxx".equalsIgnoreCase(tableName)
					|| "view_jfhb".equalsIgnoreCase(tableName)
					|| "view_zjcm_wsjc".equalsIgnoreCase(tableName)
					|| "view_zjcm_pjpy_tjsz".equalsIgnoreCase(tableName)){
				//��ͼ��û��xydm�ֶβ��ܼ�xydm������
			}else {
				if(xy!=null && !"".equalsIgnoreCase(xy)){
					querry.append("and xydm = '");
					querry.append(xy);
					querry.append("' ");
				}
			}
		}
		if ((zy == null) || zy.equalsIgnoreCase("")) {
		} else {
			querry.append("and zydm = '");
			querry.append(zy);
			querry.append("' ");
		}
		if ((bj == null) || bj.equalsIgnoreCase("")) {
			if ((xxdm.equalsIgnoreCase(Globals.XXDM_XNMZ)
					&& (tableName.equalsIgnoreCase("view_xsjtqkdcb")
							|| tableName.equalsIgnoreCase("view_xsbzxx")
							|| tableName.equalsIgnoreCase("view_xswszxjsqxx") || tableName
							.equalsIgnoreCase("view_xsjxjzxjsq")))||(xxdm.equalsIgnoreCase(Globals.XXDM_WHSYFWXY) && tableName.equalsIgnoreCase("view_xsgwxx"))) {
				if (userBj.size() != 0) {
					querry.append("and bjdm in ('###'");
					for (Iterator<String> it = userBj.iterator(); it.hasNext();) {
						querry.append(", '");
						querry.append(it.next());
						querry.append("'");
					}
					querry.append(" ) ");
				} else {
				}
			} else {
			}
		} else {
			querry.append("and bjdm = '");
			querry.append(bj);
			querry.append("' ");
		}
		if ((xmdm == null) || xmdm.equalsIgnoreCase("")) {
		} else {
			if (tableName.equalsIgnoreCase("view_dwjlsq")) {
				querry.append("and jlxmdm = '");
				querry.append(xmdm);
				querry.append("' ");
			} else if (tableName.equalsIgnoreCase("view_xsrychb")) {
				querry.append("and rychdm = '");
				querry.append(xmdm);
				querry.append("' ");
				if (userType.equalsIgnoreCase("xx")) {
					querry.append(" and xxsh='ͨ��' order by xxsh desc");
				} else {
					querry.append(" and xysh='ͨ��' and xydm='");
					querry.append(userDep);
					querry.append("' order by xysh desc");
				}
				if (xmdm.equalsIgnoreCase(Base.xydm)) {// У��--rychdmb
					zd = " xymc,xh,xm,xb,zymc,byzx,ydcs,edcs,sdcs,shcs,ygcs,yxtycs,bz ";
				} else if (xmdm.equalsIgnoreCase(Base.sydm)) {// ʡ��--rychdmb
					tableName = "view_syhz";
					zd = " xymc,xh,zymc,xm,xb,xl,lydq,zzmm,sfzh ";
				}
			}
		}
		if("view_jfhb".equalsIgnoreCase(tableName)){
			String hblb = request.getParameter("hblb");
			if(StringUtils.isNotNull(hblb)){
				querry.append(" and hblb='");
				querry.append(hblb);
				querry.append("'");
			}
		}
		if (realTable != null && realTable.equalsIgnoreCase("xszxjxjhzb")) {
			String sxxydm = dataSearchForm.getXydm();
			String zxjxjdm = dataSearchForm.getZxjxjdm();
			// String ndfw = dataSearchForm.getNdfw();
			xh = request.getParameter("xh");
			tableName = realTable;
			zd = " rownum as ���,zxjmc,xy,zybj,xh,xm,xb,mzmc,dty,ydcs,edcs,sdcs,dxcs,rychother,"
				+ "pjxfjd,xfjdzh,bxnxf,yycj,dcj,zycjpm,zyzrs,drzw,ndfw";// kyqkother,bz,pwyj,
			querry = new StringBuffer(" where 1=1 ");

			if (sxxydm != null && !sxxydm.equalsIgnoreCase(" ")) {
				querry.append(" and exists(select 1 from zxbz_xxbmdm b where a.xy=b.bmmc and b.bmdm='");
				querry.append(sxxydm);
				querry.append("')");
			}
			if (zxjxjdm != null && !zxjxjdm.equalsIgnoreCase(" ")) {
				querry.append(" and exists(select 1 from jxjdmb b where a.zxjmc=b.jxjmc and b.jxjdm='");
				querry.append(zxjxjdm);
				querry.append("')");
			}
			if (xh != null && !xh.equalsIgnoreCase(" ")&& !xh.equalsIgnoreCase("")) {
				querry.append(" and xh='");
				querry.append(xh);
				querry.append("'");
			}
		}
		if ("tem_jxjlb".equalsIgnoreCase(tableName)) {
			sql = "select COUNT(*) sum from tabs where table_name='TEM_JXJLB'||?";
			String num = dao.getOneRs(sql, new String[] { Base.currNd }, "sum");
			if (num.equals("0")) {
				sql = "{call create_tem_jxjtable}";
				dao.runProcuder(sql, new String[] {});
				sql = "{call jxjhz_generator}";
				dao.runProcuder(sql, new String[] {});
			}
			tableName = "tem_jxjlb" + Base.currNd;
		}
		if ("view_sxjy_fdypyb".equalsIgnoreCase(tableName)) {
			String pyxm = DealString.toGBK(request.getParameter("pyxm"));
			xxsh = DealString.toGBK(request.getParameter("xxsh"));
			querry.append("and pyxm = '");
			querry.append(pyxm);
			querry.append("' ");
			if(xxsh!=null&&!xxsh.equalsIgnoreCase("")) {
				querry.append("and xxsh = '");
				querry.append(xxsh);
				querry.append("' ");
			}
		}
		if ("view_jgmbxx".equalsIgnoreCase(tableName)) {
			String zzlb = DealString.toGBK(request.getParameter("zzlb"));
			zzlb = "ybybd".equalsIgnoreCase(zzlb) || "Ԥ���۲���".equalsIgnoreCase(zzlb) ? "Ԥ���۲���"
					: "�������";
			querry.append("and zzlb = '");
			querry.append(zzlb);
			querry.append("' ");
		}
		if ("bzcx".equalsIgnoreCase(tableName)) {
			String table = "";
			String querry1 = " where 1=1 ";
			String pk = "";
			String xydm1 = dataSearchForm.getXydm1();
			querry = new StringBuffer(" where 1=1 ");
			if (xydm1 == null) {
				xydm1 = "";
			}
			if (!"".equalsIgnoreCase(bzrq)) {
				querry1 += " and f.bzffny='" + bzrq + "'";
			}
			String[] xydmList;
			if (!"".equalsIgnoreCase(xydmt)) {
				xydmList = xydmt.split(",");
			} else {
				sql = "select distinct xydm from view_njxyzybj order by xydm";
				xydmList = dao.getArray(sql, new String[] {}, "xydm");
			}

			if ("lsbz".equalsIgnoreCase(bzlx)) {
				table = "lsbzb";
			} else if ("fsbz".equalsIgnoreCase(bzlx)) {
				table = "fsbzb";
			}

			sql = "select bzlxmc from bzlxb where bzlx=?";
			String bzlxmc = dao.getOneRs(sql, new String[] { bzlx }, "bzlxmc");
			if ("no1".equalsIgnoreCase(doType)) {
				if (!"".equalsIgnoreCase(xy)) {
					querry.append(" and a.xydm='");
					querry.append(xy);
					querry.append("'");
				}
				realTable = "XSZZ_BZ_QUERY1";
				pk = "xh";
				colList = new String[] { "����", "bzlxmc", "xh", "xm", "xymc",
						"bzffny1", "byyfbz1", "bybfbz1", "bysfbz1", "bzffny2",
						"byyfbz2", "bybfbz2", "bysfbz2" };
				StringBuffer sqlB = new StringBuffer();
				sqlB.append("select ");
				sqlB.append(pk);
				sqlB.append(" ����,a.*,(select '");
				sqlB.append(bzlxmc);
				sqlB.append("' bzlxmc from dual) bzlxmc from (select aa.xh,aa.xm,aa.xymc,aa.xydm,NVL((select NVL(x.YDHXJZTM,'����') xjzt from view_xjydjbxx x where x.ydxh in (select max(y.ydxh) from view_xjydjbxx y where y.ydrq in (select max(z.ydrq) from view_xjydjbxx z where substr(z.ydrq,1,7)=aa.bzffny1) and y.xh=aa.xh)),'����') xjzt1,aa.bzffny1,nvl(aa.byyfbz1,0) byyfbz1,nvl(aa.bybfbz1,0) bybfbz1,nvl(aa.bysfbz1,0) bysfbz1,NVL((select NVL(x.YDHXJZTM,'����') xjzt from view_xjydjbxx x where x.ydxh in (select max(y.ydxh) from view_xjydjbxx y where y.ydrq in (select max(z.ydrq) from view_xjydjbxx z where substr(z.ydrq,1,7)=bb.bzffny2) and y.xh=aa.xh)),'����') xjzt2,bb.bzffny2,nvl(bb.byyfbz2,0) byyfbz2,nvl(bb.bybfbz2,0) bybfbz2,nvl(bb.bysfbz2,0) bysfbz2 from (select a.xh,a.xm,a.xymc,a.xydm,'");
				sqlB.append(bzrq1);
				sqlB.append("' bzffny1,(select nvl(f.byyfbz,'0') from ");
				sqlB.append(table);
				sqlB.append(" f where f.bzffny='");
				sqlB.append(bzrq1);
				sqlB.append("' and xxsh='��ͨ��' and a.xh=f.xh) byyfbz1,(select nvl(f.bybfbz,'0') from ");
				sqlB.append(table);
				sqlB.append(" f where f.bzffny='");
				sqlB.append(bzrq1);
				sqlB.append("' and xxsh='��ͨ��' and a.xh=f.xh) bybfbz1,(select nvl(f.bysfbz,'0') from ");
				sqlB.append(table);
				sqlB.append(" f where f.bzffny='");
				sqlB.append(bzrq1);
				sqlB.append("' and xxsh='��ͨ��' and a.xh=f.xh) bysfbz1 from view_xsjbxx a ");
				sqlB.append(querry.toString());
				sqlB.append(") aa,(select a.xh,'");
				sqlB.append(bzrq2);
				sqlB.append("' bzffny2,(select nvl(f.byyfbz,'0') from ");
				sqlB.append(table);
				sqlB.append(" f where f.bzffny='");
				sqlB.append(bzrq2);
				sqlB.append("' and xxsh='��ͨ��' and a.xh=f.xh) byyfbz2,(select nvl(f.bybfbz,'0') from ");
				sqlB.append(table);
				sqlB.append(" f where f.bzffny='");
				sqlB.append(bzrq2);
				sqlB.append("' and xxsh='��ͨ��' and a.xh=f.xh) bybfbz2,(select nvl(f.bysfbz,'0') from ");
				sqlB.append(table);
				sqlB.append(" f where f.bzffny='");
				sqlB.append(bzrq2);
				sqlB
				.append("' and xxsh='��ͨ��' and a.xh=f.xh) bysfbz2 from view_xsjbxx a ");
				sqlB.append(querry.toString());
				sqlB
				.append(") bb where aa.xh=bb.xh) a where not exists(select aa.xh from (select a.xh,a.xm,a.xymc,a.xydm,'");
				sqlB.append(bzrq1);
				sqlB.append("' bzffny1,(select nvl(f.byyfbz,'0') from ");
				sqlB.append(table);
				sqlB.append(" f where f.bzffny='");
				sqlB.append(bzrq1);
				sqlB
				.append("' and xxsh='��ͨ��' and a.xh=f.xh) byyfbz1,(select nvl(f.bybfbz,'0') from ");
				sqlB.append(table);
				sqlB.append(" f where f.bzffny='");
				sqlB.append(bzrq1);
				sqlB
				.append("' and xxsh='��ͨ��' and a.xh=f.xh) bybfbz1,(select nvl(f.bysfbz,'0') from ");
				sqlB.append(table);
				sqlB.append(" f where f.bzffny='");
				sqlB.append(bzrq1);
				sqlB
				.append("' and xxsh='��ͨ��' and a.xh=f.xh) bysfbz1 from view_xsjbxx a ");
				sqlB.append(querry.toString());
				sqlB.append(") aa,(select a.xh,'");
				sqlB.append(bzrq2);
				sqlB.append("' bzffny2,(select nvl(f.byyfbz,'0') from ");
				sqlB.append(table);
				sqlB.append(" f where f.bzffny='");
				sqlB.append(bzrq2);
				sqlB
				.append("' and xxsh='��ͨ��' and a.xh=f.xh) byyfbz2,(select nvl(f.bybfbz,'0') from ");
				sqlB.append(table);
				sqlB.append(" f where f.bzffny='");
				sqlB.append(bzrq2);
				sqlB
				.append("' and xxsh='��ͨ��' and a.xh=f.xh) bybfbz2,(select nvl(f.bysfbz,'0') from ");
				sqlB.append(table);
				sqlB.append(" f where f.bzffny='");
				sqlB.append(bzrq2);
				sqlB
				.append("' and xxsh='��ͨ��' and a.xh=f.xh) bysfbz2 from view_xsjbxx a ");
				sqlB.append(querry.toString());
				sqlB
				.append(") bb where aa.xh=bb.xh and nvl(aa.byyfbz1,0)=nvl(bb.byyfbz2,0) and nvl(aa.bybfbz1,0)=nvl(bb.bybfbz2,0) and a.xh=aa.xh)");
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				Excel2Oracle.exportData(sqlB.toString(), realTable, response
						.getOutputStream());

			} else if ("no2".equalsIgnoreCase(doType)) {
				if (!"".equalsIgnoreCase(xydm1)) {
					querry.append(" and a.xydm='");
					querry.append(xydm1);
					querry.append("'");
				}
				realTable = "XSZZ_BZ_QUERY3";
				pk = "xh";
				colList = new String[] { "����", "bzlxmc", "xh", "xm", "xymc",
						"bzffny", "byyfbz", "bybfbz", "bysfbz" };
				StringBuffer sqlB = new StringBuffer();
				sqlB.append("select * from (select ");
				sqlB.append(pk);
				sqlB.append(" ����,a.*,(select '");
				sqlB.append(bzlxmc);
				sqlB
				.append("' bzlxmc from dual) bzlxmc,NVL((select NVL(x.YDHXJZTM,'����') xjzt from view_xjydjbxx x where x.ydxh in (select max(y.ydxh) from view_xjydjbxx y where y.ydrq in (select max(z.ydrq) from view_xjydjbxx z where substr(z.ydrq,1,7)=a.bzffny) and y.xh=a.xh)),'����') xjzt from (select a.xh,a.xm,a.xydm,a.xymc,'");
				sqlB.append(bzrq);
				sqlB.append("' bzffny,nvl((select f.byyfbz from ");
				sqlB.append(table);
				sqlB.append(" f ");
				sqlB.append(querry1);
				sqlB
				.append(" and f.xh=a.xh and xxsh='��ͨ��'),0) byyfbz,nvl((select f.bybfbz from ");
				sqlB.append(table);
				sqlB.append(" f ");
				sqlB.append(querry1);
				sqlB
				.append(" and f.xh=a.xh and xxsh='��ͨ��'),0) bybfbz,nvl((select f.bysfbz from ");
				sqlB.append(table);
				sqlB.append(" f ");
				sqlB.append(querry1);
				sqlB
				.append(" and f.xh=a.xh and xxsh='��ͨ��'),0) bysfbz from view_xsjbxx a ");
				sqlB.append(querry.toString());
				sqlB
				.append(") a where nvl(a.byyfbz,0)=0 and nvl(a.bybfbz,0)=0 and nvl(a.bysfbz,0)=0)");
				if (xjzt != null && !"".equalsIgnoreCase(xjzt)) {
					sqlB.append(" where xjzt='");
					sqlB.append(xjzt);
					sqlB.append("'");
				}
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				Excel2Oracle.exportData(sqlB.toString(), realTable, response
						.getOutputStream());
			} else if ("no3".equalsIgnoreCase(doType)) {
				Vector<Object> rs = new Vector<Object>();
				String[] colListCN = null;
				pk = "bzffny";
				xymc = "";
				if ("fsbz".equalsIgnoreCase(bzlx)) {
					realTable = "XSZZ_BZ_QUERY2";
					colList = new String[] { "����", "bzffny", "xymc", "fsbzrs",
							"fsbzje", "bz" };
					colListCN = dao.getColumnNameCN(colList,
					"TEMP_XSZZ_BZ_QUERY2");
					int sumRs = 0;
					int sumJe = 0;
					String bz = "";
					for (int i = 0; i < xydmList.length; i++) {
						sql = "select distinct xymc from view_njxyzybj where xydm=?";
						xymc = dao.getOneRs(sql, new String[] { xydmList[i] },
						"xymc");

						sql = "select xymc,count(xydm) fsbzrs,sum(bysfbz) fsbzje,sum(bybfbz) bz from "
							+ "(select a.xh,b.xm,b.xydm,b.xymc,nvl(a.bybfbz,'0') bybfbz,"
							+ "nvl(a.bysfbz,'0') bysfbz,a.bzffny from fsbzb a,view_xsjbxx b "
							+ "where a.xxsh='��ͨ��' and a.bzffny='"
							+ bzrqt;
						if (xjzt != null && !"".equalsIgnoreCase(xjzt)) {
							sql += "' and a.xjzt='"
								+ xjzt;
						}
						sql += "' and b.xydm='"
							+ xydmList[i]
							           + "' and a.xh=b.xh) group by xymc,xydm having xydm is not null";
						String[] temp = dao
						.getOneRs(sql, new String[] {}, new String[] {
								"xymc", "fsbzrs", "fsbzje", "bz" });
						if (temp != null) {
							if (temp[2].equalsIgnoreCase("0")) {
								temp[1] = "0";
								temp[3] = "";
							} else {
								sumRs += Integer.parseInt(temp[1]);
								sumJe += Integer.parseInt(temp[2]);
							}
							if (!"".equalsIgnoreCase(temp[3])
									&& !"0".equalsIgnoreCase(temp[3])) {
								bz = "����" + temp[3];
							} else {
								bz = " ";
							}
							sql = "select '" + bzrqt + "' ����,'" + bzrqt
							+ "' bzffny,'" + temp[0] + "' xymc,'"
							+ temp[1] + "' fsbzrs,'" + temp[2]
							                                + "' fsbzje,'" + bz + "' bz from dual";
							rs.addAll(dao.rsToVator(sql, new String[] {},
									colList));
						} else {
							sql = "select '"
								+ bzrqt
								+ "' ����,'"
								+ bzrqt
								+ "' bzffny,'"
								+ xymc
								+ "' xymc,'0' fsbzrs,'0' fsbzje,' ' bz from dual";
							rs.addAll(dao.rsToVator(sql, new String[] {},
									colList));
						}
					}
					sql = "select '" + bzrqt + "' ����,'" + bzrqt
					+ "' bzffny,'�ϼ�' xymc,'" + sumRs + "' fsbzrs,'"
					+ sumJe + "' fsbzje,' ' bz from dual";
					rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
					response.reset();
					response.setContentType("application/vnd.ms-excel");
					Excel2Oracle.exportDataFor(rs, colListCN, response
							.getOutputStream());
				} else if ("lsbz".equalsIgnoreCase(bzlx)) {
					realTable = "XSZZ_BZ_QUERY4";
					colList = new String[] { "����", "bzffny", "xymc", "lsbzrs",
							"lsbzje", "bz" };
					colListCN = dao.getColumnNameCN(colList,
					"TEMP_XSZZ_BZ_QUERY4");
					int sumRs = 0;
					int sumJe = 0;
					String bz = "";
					for (int i = 0; i < xydmList.length; i++) {
						sql = "select distinct xymc from view_njxyzybj where xydm=?";
						xymc = dao.getOneRs(sql, new String[] { xydmList[i] },
						"xymc");

						sql = "select xymc,count(xydm) lsbzrs,sum(bysfbz) lsbzje,sum(bybfbz) bz from "
							+ "(select a.xh,b.xm,b.xydm,b.xymc,nvl(a.bybfbz,'0') bybfbz,"
							+ "nvl(a.bysfbz,'0') bysfbz,a.bzffny from view_lsbzb a,view_xsjbxx b "
							+ "where a.xxsh='��ͨ��' and a.bzffny='"
							+ bzrqt;
						if (xjzt != null && !"".equalsIgnoreCase(xjzt)) {
							sql += "' and a.xjzt='"
								+ xjzt;
						}
						sql += "' and a.xh=b.xh and b.xydm='"
							+ xydmList[i]
							           + "') group by xymc,xydm having xydm is not null";
						String[] temp = dao
						.getOneRs(sql, new String[] {}, new String[] {
								"xymc", "lsbzrs", "lsbzje", "bz" });
						if (temp != null) {
							if (temp[2].equalsIgnoreCase("0")) {
								temp[1] = "0";
								temp[3] = "";
							} else {
								sumRs += Integer.parseInt(temp[1]);
								sumJe += Integer.parseInt(temp[2]);
							}
							if (!"".equalsIgnoreCase(temp[3])
									&& !"0".equalsIgnoreCase(temp[3])) {
								bz = "����" + temp[3];
							} else {
								bz = " ";
							}
							sql = "select '" + bzrqt + "' ����,'" + bzrqt
							+ "' bzffny,'" + temp[0] + "' xymc,'"
							+ temp[1] + "' lsbzrs,'" + temp[2]
							                                + "' lsbzje,'" + bz + "' bz from dual";
							rs.addAll(dao.rsToVator(sql, new String[] {},
									colList));
						} else {
							sql = "select '"
								+ bzrqt
								+ "' ����,'"
								+ bzrqt
								+ "' bzffny,'"
								+ xymc
								+ "' xymc,'0' lsbzrs,'0' lsbzje,' ' bz from dual";
							rs.addAll(dao.rsToVator(sql, new String[] {},
									colList));
						}
					}
					sql = "select '" + bzrqt + "' ����,'" + bzrqt
					+ "' bzffny,'�ϼ�' xymc,'" + sumRs + "' lsbzrs,'"
					+ sumJe + "' lsbzje,' ' bz from dual";
					rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
					response.reset();
					response.setContentType("application/vnd.ms-excel");
					Excel2Oracle.exportDataFor(rs, colListCN, response
							.getOutputStream());
				}
			} else if ("no4".equalsIgnoreCase(doType)) {
				Vector<Object> rs = new Vector<Object>();
				String[] colListCN = null;
				realTable = table;
				pk = "xh||bzffny";
				if ("fsbz".equalsIgnoreCase(bzlx)) {
					colList = new String[] { "����", "bzffny", "xh", "xm",
							"xymc", "bjmc", "xjzt", "byyfbz", "bybfbz", "bysfbz" };
					colListCN = dao.getColumnNameCN(colList, "view_fsbzb");
					if (xjzt != null && !"".equalsIgnoreCase(xjzt)) {
						sql = "select "
							+ pk
							+ " ����,a.bzffny,a.xh,a.xm,a.xymc,a.bjmc,a.xjzt,a.byyfbz,a.bybfbz,a.bysfbz from "
							+ "(select f.bzffny,f.xh,f.xm,f.xymc,f.bjmc,f.xjzt,f.byyfbz,f.bybfbz,f.bysfbz from view_fsbzb f "
							+ "where not exists(select 1 from view_xsjbxx a where a.xh=f.xh) and f.bzffny='"
							+ bzrq4 + "' and f.xymc like '%" + xymc
							+ "%' and f.xjzt='" + xjzt + "') a";
					} else {
						sql = "select "
							+ pk
							+ " ����,a.bzffny,a.xh,a.xm,a.xymc,a.bjmc,a.xjzt,a.byyfbz,a.bybfbz,a.bysfbz from "
							+ "(select f.bzffny,f.xh,f.xm,f.xymc,f.bjmc,f.xjzt,f.byyfbz,f.bybfbz,f.bysfbz from view_fsbzb f "
							+ "where not exists(select 1 from view_xsjbxx a where a.xh=f.xh) and f.bzffny='"
							+ bzrq4 + "' and f.xymc like '%" + xymc
							+ "%') a";
					}
					rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
					response.reset();
					response.setContentType("application/vnd.ms-excel");
					Excel2Oracle.exportDataFor(rs, colListCN, response
							.getOutputStream());
				} else if ("lsbz".equalsIgnoreCase(bzlx)) {
					colList = new String[] { "����", "bzffny", "xh", "xjzt", "byyfbz",
							"bybfbz", "bysfbz" };
					colListCN = dao.getColumnNameCN(colList, "view_lsbzxx");
					if (xjzt != null && !"".equalsIgnoreCase(xjzt)) {
						sql = "select "
							+ pk
							+ " ����,a.bzffny,a.xh,a.xm,a.xjzt,a.byyfbz,a.bybfbz,a.bysfbz from "
							+ "(select l.bzffny,l.xh,l.xjzt,l.byyfbz,l.bybfbz,l.bysfbz from view_lsbzb l"
							+ "where not exists(select 1 from view_xsjbxx a where a.xh=l.xh) and l.bzffny='"
							+ bzrq4 + "' and l.xymc like '%" + xymc
							+ "%' and l.xjzt='" + xjzt + "') a";
					} else {
						sql = "select "
							+ pk
							+ " ����,a.bzffny,a.xh,a.xm,a.xjzt,a.byyfbz,a.bybfbz,a.bysfbz from "
							+ "(select l.bzffny,l.xh,l.xjzt,l.byyfbz,l.bybfbz,l.bysfbz from view_lsbzb l"
							+ "where not exists(select 1 from view_xsjbxx a where a.xh=l.xh) and l.bzffny='"
							+ bzrq4 + "' and l.xymc like '%" + xymc
							+ "%') a";
					}
					rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
					response.reset();
					response.setContentType("application/vnd.ms-excel");
					Excel2Oracle.exportDataFor(rs, colListCN, response
							.getOutputStream());
				}
			}
		} else if ("fsbzb".equalsIgnoreCase(realTable)
				|| "lsbzb".equalsIgnoreCase(realTable)) {
			if ("fsbzb".equalsIgnoreCase(realTable)) {
				tableName = "view_fsbzb";
			} else {
				tableName = "view_lsbzxx";
			}
			colList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "xjzt", "BYYFBZ", "BYBFBZ", "BYSFBZ", "BZFFNY" };
			String[] colListCN = dao.getColumnNameCN(colList, "view_fsbzb");
			zd = " xh,xm,xb,nj,xymc,zymc,bjmc,xjzt,BYYFBZ,BYBFBZ,BYSFBZ,BZFFNY ";
			Vector<Object> rs = new Vector<Object>();
			querry.append(" and xxsh='��ͨ��'");
			sql = "select " + zd + " from " + tableName + querry.toString();
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));

			sql = "select sum(BYYFBZ) BYYFBZ,sum(BYBFBZ) BYBFBZ,sum(BYSFBZ) BYSFBZ from "
				+ tableName + querry.toString();
			String[] temp = dao.getOneRs(sql, new String[] {}, new String[] {
					"BYYFBZ", "BYBFBZ", "BYSFBZ" });
			if (temp != null) {
				sql = "select '�ϼ�' xh, ' ' xm, ' ' xb, ' ' nj, ' ' xymc, ' ' zymc, ' ' bjmc,' ' xjzt, '"
					+ temp[0]
					       + "' BYYFBZ, '"
					       + temp[1]
					              + "' BYBFBZ, '"
					              + temp[2] + "' BYSFBZ ,' ' BZFFNY from dual ";
				rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			}
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportDataFor(rs, colListCN, response .getOutputStream());
		} else if (type != null && type.equalsIgnoreCase("tg")) {
			tableName = "view_xsgwxx";
			colList = new String[] { "xzb", "xh", "gwdm", "sqsj", "xssq",
					"fdyyj", "xyyj", "xxyj", "xscyj", "bz", "kcjqgzxsj",
					"lxdh", "xn", "nd", "xq", "gwsbsj", "sfpks", "xm", "xb",
					"nj", "xydm", "xymc", "zydm", "zymc", "bjmc", "jtcy",
					"jtysr", "pkdj", "yhtc", "gjzxdk", "zzqk", "xg", "ldyx",
					"gzjl", "zzmm" };
			String[] colListCN = dao.getColumnNameCN(colList, tableName);
			List rs = null;

			querry.append(xy == null || "".equalsIgnoreCase(xy) ? ""
					: " and xydm='" + xy + "'");
			querry.append(zy == null || "".equalsIgnoreCase(xy) ? ""
					: " and zydm='" + zy + "'");
			querry.append(nj == null || "".equalsIgnoreCase(nj) ? ""
					: " and nj='" + nj + "'");
			querry.append(bj == null || "".equalsIgnoreCase(bj) ? ""
					: " and bjdm='" + bj + "'");
			querry.append(xn == null || "".equalsIgnoreCase(xn) ? ""
					: " and xn='" + xn + "'");
			querry.append(nd == null || "".equalsIgnoreCase(nd) ? ""
					: " and nd='" + nd + "'");
			querry.append(xq == null || "".equalsIgnoreCase(xq) ? ""
					: " and xq='" + xq + "'");
			querry.append(xh == null || "".equalsIgnoreCase(xh) ? ""
					: " and xh like'" + xh + "'");

			sql = "select * from "
				+ tableName
				+ querry.toString()
				+ " and xyyj='ͨ��' and xxyj='ͨ��' and fdyyj='ͨ��' and xscyj='ͨ��'";
			rs = dao.rsToVator(sql, new String[] {}, colList);
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportDataFor(rs, colListCN, response
					.getOutputStream());
		}else if(realTable!=null&&(realTable.equalsIgnoreCase("xsjlb")||realTable.equalsIgnoreCase("xscfb"))){//��Ԣ��������
			 lddm = dataSearchForm.getLddm();
			querry.append(Base.isNull(lddm)?" and 1=1 ":" and lddm = '"+lddm+"' ");
			if(realTable.equalsIgnoreCase("xsjlb")){//����
				colList = new String [] {"xn","xq","xh","xm","ssbh","sj","jlnr","ryjf","kpf"};
			}else if(realTable.equalsIgnoreCase("xscfb")){//����
				colList = new String [] {"xn","xq","xh","xm","ssbh","sj","cfnr","rykf","kpf"};
			}
			sql = "select * from " + tableName + querry.toString();
			String[] colListCN = dao.getColumnNameCN(colList, tableName);
			List rs = null;
			rs = dao.rsToVator(sql, new String[] {}, colList);
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportDataFor(rs, colListCN, response
					.getOutputStream());
		}else if(tableName.equalsIgnoreCase("view_ssxx")
				||tableName.equalsIgnoreCase("view_xszsxx")||tableName.equalsIgnoreCase("view_ssszsxx")){
			if(Globals.XXDM_YNYS.equalsIgnoreCase(xxdm)){
				BaseAction baseAction=new BaseAction();
				String tabName="xszsxx";
				String viewName="view_xszsxx";
				dataSearchForm.setQueryequals_bjdm(dataSearchForm.getBjdm());
				dataSearchForm.setQueryequals_zydm(dataSearchForm.getZydm());
				dataSearchForm.setQueryequals_xydm(dataSearchForm.getXydm());
				dataSearchForm.setQueryequals_cs(dataSearchForm.getCs());
				dataSearchForm.setQueryequals_qsh(dataSearchForm.getQsh());
				dataSearchForm.setQueryequals_lddm(dataSearchForm.getLddm());
				dataSearchForm.setQueryequals_xqdm(dataSearchForm.getXqdm());
				dataSearchForm.setQueryequals_nj(dataSearchForm.getNj());
				dataSearchForm.setQueryequals_sfqsz(dataSearchForm.getSfqsz());
				dataSearchForm.setQueryequals_xb(dataSearchForm.getXb());
				dataSearchForm.setQuerylike_xh(dataSearchForm.getXh());
				dataSearchForm.setQuerylike_xm(dataSearchForm.getXm());
				
				try {
					
					baseAction.expPageData(request, response, tabName, viewName, null);
				
				} catch (Exception e) {
				
					e.printStackTrace();
				
				}			
			}
			lddm = dataSearchForm.getLddm();
			String lc = dataSearchForm.getLc();
			qsh = dataSearchForm.getQsh();
			xqdm = dataSearchForm.getXqdm();
			xb = dataSearchForm.getXb1();
			if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)
					||xxdm.equalsIgnoreCase(Globals.XXDM_NCDXKJXY)) {			
//				String fzld = gyglDao.getLddmxXx( userName);
//				if (!("".equalsIgnoreCase(fzld) || fzld == null)) {
//				lddm = fzld;						
//				}
				querry.append((Base.isNull(lc)||lc.equalsIgnoreCase("null"))?" and 1=1 ":" and substr(qsh,1,1)='"+lc+"' ");
			}
			querry.append(Base.isNull(lddm)?" and 1=1 ":" and lddm='"+lddm+"' ");			
			querry.append((Base.isNull(qsh)||qsh.equalsIgnoreCase("null"))?" and 1=1 ":" and qsh='"+qsh+"' ");	
			querry.append(Base.isNull(xqdm)?" and 1=1 ":" and xqdm='"+xqdm+"' ");	
			querry.append(Base.isNull(xb)?" and 1=1 ":" and xb='"+xb+"' ");	
			if(tableName.equalsIgnoreCase("view_ssxx")){
				StringBuffer querryT = new StringBuffer ("where 1=1 ");
				querryT.append(Base.isNull(lddm)?" and 1=1 ":" and lddm='"+lddm+"' ");
				//querryT.append(Base.isNull(lc)?" and 1=1 ":" and substr(qsh,1,1)='"+lc+"' ");
				querryT.append((Base.isNull(qsh)||qsh.equalsIgnoreCase("null"))?" and 1=1 ":" and qsh='"+qsh+"' ");	
				querryT.append(Base.isNull(xqdm)?" and 1=1 ":" and xqdm='"+xqdm+"' ");
				sql = "select * from view_ssxx " + querryT;
			}else if (tableName.equalsIgnoreCase("view_xszsxx")){
				sql = "select xh,ssbh,bz,cwh,rzrq,jzrq,sfbz,zsf,dsjssf,lddm,ldmc,qsh,xqdm,xqmc,qsdh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc from view_xszsxx"+ querry;
			}else{
				sql = "select xh,ssbh,bz,cwh,rzrq,jzrq,zsf,dsjssf,lddm,ldmc,qsh,xqdm,xqmc,qsdh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc from view_ssszsxx"+ querry;
			}
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportData(sql, tableName, response
					.getOutputStream());
		}else if(tableName.equalsIgnoreCase("view_gywxglxx")){
			lddm=dataSearchForm.getLddm();
			qsh=dataSearchForm.getQsh();
			String bxsj=dataSearchForm.getBxsj();
			String wxsj=dataSearchForm.getWxsj();
			querry.append(Base.isNull(lddm)?" and 1=1 ":" and lddm='"+lddm+"' ");			
			querry.append(Base.isNull(qsh)?" and 1=1 ":" and qsh='"+qsh+"' ");	
			querry.append(Base.isNull(xn)?" and 1=1 ":" and xn='"+xn+"' ");
			querry.append(Base.isNull(xq)?" and 1=1 ":" and xq='"+xq+"' ");
			querry.append(Base.isNull(bxsj)?" and 1=1 ":" and bxsj='"+bxsj+"' ");
			querry.append(Base.isNull(wxsj)?" and 1=1 ":" and wxsj='"+wxsj+"' ");

			sql = "select bxsj,wxnr,bxbm,ldmc,qsh,bxr,wxsj,rymc,bz from view_gywxglxx "+querry;
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		}else if(tableName.equalsIgnoreCase("view_xszslsxx")){ 
			String xsbz = DealString.toGBK(request.getParameter("xsbz"));
			xb   = DealString.toGBK(request.getParameter("xb"));
			querry.append(Base.isNull(nj)?" and 1=1 ":" and nj='"+nj+"' ");			
			querry.append(Base.isNull(xy)?" and 1=1 ":" and xydm='"+xy+"' ");	
			querry.append(Base.isNull(zy)?" and 1=1 ":" and zydm='"+zy+"' ");
			querry.append(Base.isNull(bj)?" and 1=1 ":" and bjdm='"+bj+"' ");
			querry.append(Base.isNull(xh)?" and 1=1 ":" and xh='"+xh+"' ");
			querry.append(Base.isNull(xm)?" and 1=1 ":" and xm='"+xm+"' ");
			querry.append(Base.isNull(xb)?" and 1=1 ":" and xb='"+xb+"' ");
			querry.append(Base.isNull(xsbz)?" and 1=1 ":" and xsbz='"+xsbz+"' ");
			sql = "select " + zd + " from " + tableName + querry.toString();
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		}else if(tableName.equalsIgnoreCase("view_gywxglsqxx")){//��Ԣά�����
			lddm = dataSearchForm.getLddm();//¥������
			kssj = request.getParameter("kssj");//����ʱ��
			jssj = request.getParameter("jssj");//����ʱ��
			shzt = DealString.toGBK(dataSearchForm.getYesNo());//���״̬
			qsh  = dataSearchForm.getQsh();//���Һ�
			if(!Base.isNull(kssj)&&!Base.isNull(jssj)){
				querry.append(" and bxsj between '"+kssj+"' and '"+jssj+"'");
			}else{
				if(!Base.isNull(kssj)){
					querry.append(" and bxsj='"+kssj+"'  ");
				}else if(!Base.isNull(jssj)){
					querry.append(" and bxsj='"+jssj+"' ");
				}
			}	
			querry.append(Base.isNull(lddm)?"":" and lddm='"+lddm+"' ");
			querry.append(Base.isNull(qsh)?"":" and qsh='"+qsh+"' ");
			querry.append(Base.isNull(shzt)?"":" and xxsh='"+shzt+"' ");
			sql = "select xn,xq,bxsj,wxnr,bxrxm,ldmc,qsh,xxsh from "+ tableName + querry.toString();
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		}else if(tableName.equalsIgnoreCase("view_ssydsqxx")){//�����춯���
			kssj = request.getParameter("kssj");//�춯ʱ��
			jssj = request.getParameter("jssj");//�춯ʱ��
			shzt = DealString.toGBK(dataSearchForm.getYesNo());//���״̬
			if(!Base.isNull(kssj)&&!Base.isNull(jssj)){
				querry.append(" and sqydsj between '"+kssj+"' and '"+jssj+"'");
			}else{
				if(!Base.isNull(kssj)){
					querry.append(" and sqydsj='"+kssj+"'  ");
				}else if(!Base.isNull(jssj)){
					querry.append(" and sqydsj='"+jssj+"' ");
				}
			}	
			if(userType.equalsIgnoreCase("xx")){
				querry.append(Base.isNull(shzt)?"  and xysh='ͨ��' ":" and xxsh='"+shzt+"' and xysh='ͨ��' ");
				sql = "select xn,xq,sqydsj,xh,xm,xb,nj,xymc,zymc,bjmc,ydqldmc,ydqqsh,ydqcwh,ydqrzsj,ldmc,"
					+"qsh,ydhcwh,ydly,xysh,xxsh from " + tableName + querry.toString();
			}else if(userType.equalsIgnoreCase("xy")){
				querry.append(Base.isNull(shzt)?"":" and xysh='"+shzt+"' ");
				querry.append(" and xydm='"+userDep+"'");
				sql = "select xn,xq,sqydsj,xh,xm,xb,nj,xymc,zymc,bjmc,ydqldmc,ydqqsh,ydqcwh,ydqrzsj,ldmc,"
					+"qsh,ydhcwh,ydly,xxsh,xysh from " + tableName + querry.toString();
			}
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		}else if(tableName.equalsIgnoreCase("view_hh_gygl_jqlxsq")){//������ѧԺ������У
			String xydm = request.getParameter("xydm");
			String zydm = request.getParameter("zydm");
			String bjdm = request.getParameter("bjdm");
			StringBuffer query = new StringBuffer();
			query.append(" where 1=1");
			query.append(Base.isNull(xh)?"":" and a.xh ='"+xh+"' ");
			query.append(Base.isNull(xn)?"":" and a.xn ='"+xn+"' ");
			query.append(Base.isNull(xq)?"":" and a.xq ='"+xq+"' ");
			query.append(Base.isNull(xydm)?"":" and a.xydm ='"+xydm+"' ");
			query.append(Base.isNull(zydm)?"":" and a.zydm ='"+zydm+"' ");
			query.append(Base.isNull(bjdm)?"":" and a.bjdm ='"+bjdm+"' ");

			tableName="view_hh_gygl_jqlxsq";
			colList = new String[] { };
			sql = "select xn,xq, xh, ssbh, zskssj, zsjssj, lxyy,lxdh,bz,cwh from view_hh_gygl_jqlxsq_ex"
				+ query.toString();

			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportData(sql, "view_hh_gygl_jqlxsq_ex", response.getOutputStream());
		}else if(tableName.equalsIgnoreCase("view_gygl_jqlxsq")){//������У���
			shzt = DealString.toGBK(dataSearchForm.getYesNo());//���״̬
			if(userType.equalsIgnoreCase("xx")){				
				querry.append(Base.isNull(shzt)?"  and xysh='ͨ��' ":" and xxsh='"+shzt+"' and xysh='ͨ��' ");
				sql = "select xn,xq,xh,xb,xm,nj,xymc,zymc,bjmc,lxdh,ldmc,qsh,zskssj,zsjssj,lxyy,xxsh,xysh from " + tableName + querry.toString();
			}else if(userType.equalsIgnoreCase("xy")){
				querry.append(Base.isNull(shzt)?"":" and xysh='"+shzt+"' ");
				querry.append(" and xydm='"+userDep+"'");
				sql = "select xn,xq,xh,xb,xm,nj,xymc,zymc,bjmc,lxdh,ldmc,qsh,zskssj,zsjssj,lxyy,xxsh,xysh from " + tableName + querry.toString();
			}
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		}else if(tableName.equalsIgnoreCase("view_xswzsqxx")){//ѧ����ס���߶������
			shzt = DealString.toGBK(request.getParameter("yesNo"));//���״̬
			String wzlxdm = request.getParameter("wzlxdm");//��ס����
			String wzksrq = request.getParameter("wzksrq");//��ס��ʼ����
			querry.append(Base.isNull(wzksrq)?"":" and wzksrq='"+wzksrq+"'");
			querry.append(Base.isNull(wzlxdm)?"":" and wzlxdm='"+wzlxdm+"'");

			if(userType.equalsIgnoreCase("xx")){	
				querry.append(Base.isNull(shzt)?"  and xysh='ͨ��' ":" and xxsh='"+shzt+"' and xysh='ͨ��' ");
				sql = "select xn,xq,xh,xm,xb,nj,xymc,zymc,bjmc,wzksrq,jhwzsj,wzlxmc,wzyy,wzdz,xxsh,xysh from "+ tableName + querry;
			}else if(userType.equalsIgnoreCase("xy")){
				querry.append(Base.isNull(shzt)?"":" and xysh='"+shzt+"' ");
				sql = "select xn,xq,xh,xm,xb,nj,xymc,zymc,bjmc,wzksrq,jhwzsj,wzlxmc,wzyy,wzdz,xxsh,xysh from "+ tableName + querry;
			}
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		}else if(tableName.equalsIgnoreCase("view_xljkzptj")){
			//�����������Ծ�ѧ����Ϣ
			String getcondi = request.getParameter("sql");
			//System.out.println("sql=" + getcondi);
			sql = "select xh,zf,zjf,yxzztksp,qthyz,qpzzyz,rjgxmgyz,yyyz,jlyz,ddyz,kbyz,pzyz,jsbxyz,fjyz from "
				+ tableName + getcondi;
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		}else if(tableName.equalsIgnoreCase("view_zcystmdf")){
			//����
			String getcondi = request.getParameter("sql");
			sql = "select xh,xm,xb,th,yx,df from " + tableName + getcondi;
			//System.out.println(sql);
			response.reset();
			response.setContentType("application/vnd.ms-excel");

			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		}else if(tableName.equalsIgnoreCase("view_zcyscjzbf")){
			//����
			String getcondi = request.getParameter("sql");
			sql = "select xh,xm,xb,a,b,c,e,f,g,h,i,l,m,n,o,q1,q2,q3,q4 from  " + tableName + getcondi;
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		}else if(tableName.equalsIgnoreCase("view_newstureportinfo")){
			sql = "select a.ksh primaryKey,a.ksh,a.xh,a.nj,a.xm,a.xb,a.xxmc,a.xymc,a.zymc,a.bjmc,a.sfjy,a.sfdm,a.jg,a.csrq,sfzh," 
				+ " a.xxszd,a.cym,a.zzmm,a.mz,a.fy,a.xsh,a.zyh,a.zyfx,a.pyfx,a.bh,a.xz,a.pycc,a.syszd,"
				+ " a.lxr,a.lxdh,a.jtdz,a.yzbm,a.kslb,a.kstz,a.bxxs,a.bxlx,a.xxxs,a.bylb,a.zylb,a.lqsj,"
				+ " a.lqxs,a.wyyz,a.gkzf,a.sfmc,a.ldh,a.qsh,"
				+ " a.sflsj,a.sftj,a.sfzsym,a.xybd,a.yybd,a.stbd,a.ssbd,a.ysqsf,a.ysxf,a.sjqsf,a.sjxf,a.sffs"
				+ " from " 
				+ tableName
				+ " a ";
			String tempcha = request.getParameter("chaflag");
			if(!Base.isNull(tempcha) && tempcha.equals("1")){
				//�����Ѿ���������������Ϣ����
				sql += " where a.xybd='��' and a.ssbd='��'";
			}else if (!Base.isNull(tempcha) && tempcha.equals("2")){
				String[] columns = new String[]{"xydm","zydm","bjdm","xh","xm","ksh"};
				sql = "select ksh,xh,xm,xymc,zymc,bjmc,xybd,yybd,stbd,ssbd from " + tableName + FormUtils.getWhereStrByReq(columns, request);
			}
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		}else if(tableName.equalsIgnoreCase("VIEW_XLJK_WHLGDX_KHXX")){
			//��������ѧ��
			StringBuffer sqlBf = new StringBuffer("select xh,xm,xb,nj,xymc,zymc,bjmc,sfzh,ssbh,qsdh,sjhm,xz,lxdh,nl,jg,zyxlwt,yyzdqk,xgqk,mqzt,xjyd,remark from ");
			sqlBf.append(tableName);
			sqlBf.append(querry);
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportData(sqlBf.toString(), tableName, response.getOutputStream());
		}else if(tableName.equalsIgnoreCase("view_skxxx")
				||tableName.equalsIgnoreCase("view_ckxxx")){//�������ᴲλ����
			lddm = request.getParameter("lddm");
			qsh = request.getParameter("qsh");			
			String lc = request.getParameter("lc");

			querry = new StringBuffer(" where 1=1 ") ;
			querry.append((Base.isNull(lddm)) ? " and  1=1": " and lddm='" + lddm + "'");
			querry.append((Base.isNull(qsh)) ? " and 1=1": " and qsh='" + qsh + "'");
			querry.append((Base.isNull(xqdm)) ? " and 1=1": " and xqdm='" + xqdm + "'");
			if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
				querry.append((Base.isNull(lc) ? " and 1=1" : " and substr(qsh,1,1) ='"+ lc + "' "));
			}
			if ("view_skxxx".equalsIgnoreCase(tableName)) {	
				if(Globals.XXDM_HZZY.equalsIgnoreCase(xxdm)){
					sql="select xqmc,ldmc, qsh,cws, fpqk from view_skxxx  "+querry+" order by lddm ,qsh";
				}else{
					if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){
						sql="select xqmc,yqmc,ldmc,qsh,cws,fpqk from view_skxxx  "+querry+" order by lddm ,qsh";
					}else{
						sql="select xqmc,ldmc,qsh,cws,fpqk from view_skxxx  "+querry+" order by lddm ,qsh";
					}
				}								
			} else if ("view_ckxxx".equalsIgnoreCase(tableName)) {
				if(Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)){					
					sql="select xqmc,yqmc,ldmc, qsh,cws,sycw from view_ckxxx  "+querry+" order by lddm,qsh";			
				}else{					
					sql="select xqmc,ldmc, qsh,cws,sycw from view_ckxxx  "+querry+" order by lddm,qsh";								
				}				
			}			
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		}else if(tableName.equalsIgnoreCase("view_dyssxx"))	{
			xqdm = request.getParameter("xqdm");
			String yqdm = request.getParameter("yqdm");			
			lddm = request.getParameter("lddm");
			querry = new StringBuffer();
			//��ѯ����
			querry.append(" where 1=1 ");
			querry.append(Base.isNull(yqdm)?"":" and yqdm='"+yqdm+"' ");
			querry.append(Base.isNull(lddm)?"":" and lddm='"+lddm+"' ");
			querry.append(Base.isNull(xqdm)?"":" and xqdm = '"+xqdm+"' ");
			sql = " select xqmc,yqmc,ldmc,qsh,cy from view_dyssxx "+querry;
			colList = new String[]{"У��","԰��","¥��","���Һ�","���ҵ�Ա��Ա"};//��ѯ��ʾ�ֶ� 
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportData(sql,colList, response.getOutputStream(),"��Ա����ͳ��");
		}else if(tableName.equalsIgnoreCase("view_wmqspbxx")){
			lddm = request.getParameter("lddm");
			String lc   = request.getParameter("lc");
			qsh  = DealString.toGBK(request.getParameter("qsh"));
			String pysj = request.getParameter("pysj");
			String qslb = request.getParameter("qslb");
			querry = new StringBuffer(" where 1=1 ");			
			querry.append(Base.isNull(xh)?"":" and xn = '" + xn +"'");			
			querry.append(Base.isNull(xq)?"":" and xq = '" + xq +"'");
			querry.append(Base.isNull(lddm)?"":" and lddm = '" + lddm + "'");
			querry.append(Base.isNull(qsh)?"":" and qsh = '" + qsh + "'");
			querry.append(Base.isNull(pysj)?"":" and pysj ='" + pysj + "'");			
			querry.append(Base.isNull(qslb)?"":" and qslb='" + qslb + "'");
			querry.append(Base.isNull(lc)?"":" and cs ='"+lc+"' ");						
			if(xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)){
				sql = " select xn, xqmc, ldmc, qsh,  pysj,lbmc,qsjje,cy from " + tableName + querry;				
			}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJJZYJSXY)){	
				sql = " select xn, xqmc, ldmc, qsh,  pysj,lbmc,cy from " + tableName + querry;				
			}else{
				sql = " select xn, xqmc, ldmc, qsh,lbmc,cy from " + tableName + querry;
			}
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		}else if(tableName.equalsIgnoreCase("view_gywsjcxx")){
			lddm=request.getParameter("lddm");
			//String lc=request.getParameter("lc");
			qsh=request.getParameter("qsh");
			String jcsj=request.getParameter("jcsj");
			String jcbm=request.getParameter("jcbm");			
			querry = new StringBuffer(" where 1=1 ");			
			querry.append(Base.isNull(xn)?"":" and xn = '" + xn +"'");	
			querry.append(Base.isNull(xq)?"":" and xq = '" + xq +"'");
			querry.append(Base.isNull(lddm)?"":" and lddm = '" + lddm +"'");
			querry.append(Base.isNull(qsh)?"":" and qsh = '" + qsh +"'");	
			querry.append(Base.isNull(jcsj)?"":" and jcsj = '" + jcsj +"'");
			querry.append(Base.isNull(jcbm)?"":" and jcbm = '" + jcbm +"'");
			sql = " select * from " + tableName + querry;
			// ============ begin 2009/12/10 luojw ====================
			if (xxdm.equalsIgnoreCase(Globals.XXDM_WXSYZYJSXY)){
				sql = " select xn,xqmc,zs,jcsj,ldmc,cs,qsh,fs,dj,bmmc,bz from " + tableName + querry;
			}
			// ============ end 2009/12/10 luojw ====================
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		}else if(tableName.equalsIgnoreCase("view_zsjlxx")){

			lddm = request.getParameter("lddm");
			@SuppressWarnings("unused")
			String lc = request.getParameter("lc");
			qsh = request.getParameter("qsh");
			String gzkssj = request.getParameter("gzkssj");
			String gzjssj =request.getParameter("gzjssj");
			String xydm = request.getParameter("xydm");
			String zydm = request.getParameter("zydm");
			String bjdm = request.getParameter("bjdm");
			String wjlbdm = request.getParameter("wjlbdm");
			String xsxb = DealString.toGBK(request.getParameter("xb1"));
			String mz = request.getParameter("mz");
			String jg = DealString.toGBK(request.getParameter("jg"));
			querry = new StringBuffer(" where 1=1 ");
			querry.append(Base.isNull(xsxb)?"":" and xb ='"+xsxb+"' ");
			querry.append(Base.isNull(mz)?"":" and mz ='"+mz+"' ");
			querry.append(Base.isNull(jg)?"":" and jg ='"+jg+"' ");
			querry.append(Base.isNull(xn)?"":" and xn = '" + xn + "'");		
			querry.append(Base.isNull(xq)?"":" and xq = '" + xq + "'");
			querry.append(Base.isNull(xh)?"":" and xh like '%" + xh + "%'");
			querry.append(Base.isNull(lddm)?"":" and lddm = '" + lddm + "'");
			if(qsh!=null&&!"qsh".equalsIgnoreCase(qsh)){
				querry.append(Base.isNull(qsh)?"":" and qsh = '" + qsh + "'");	
			}
			querry.append(Base.isNull(xydm)?"":" and xydm = '" + xydm + "'");
			querry.append(Base.isNull(zydm)?"":" and zydm = '" + zydm + "'");
			querry.append(Base.isNull(bjdm)?"":" and bjdm = '" + bjdm + "'");
			querry.append(Base.isNull(wjlbdm)?"":" and wjlbdm='"+wjlbdm+"' ");	
			querry.append(Base.isNull(xm)?"":" and xm like '%"+xm+"%'");
			if (!Base.isNull(gzkssj)&&Base.isNull(gzjssj)){
				querry.append(" and wjsj = '" + gzkssj + "'");
			}
			if(!Base.isNull(gzjssj)&&Base.isNull(gzkssj)){
				querry.append(" and wjsj = '"+gzjssj+"' ");
			}
			if(!Base.isNull(gzkssj)&&!Base.isNull(gzjssj)){
				querry.append(" and (wjsj >= '"+gzkssj+"' and wjsj<='"+gzjssj+"' ) ");
			}
			sql = " select * from " + tableName + querry;
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		} else if("view_zsfbgtjxx".equalsIgnoreCase(tableName)) {
			querry    = new StringBuffer(" where 1=1 ");
			nj  = request.getParameter("nj");
			String xydm = request.getParameter("xydm");
			String zydm = request.getParameter("zydm");
			String bjdm = request.getParameter("bjdm");
			String hdksrq = request.getParameter("hdksrq");
			String hdjsrq = request.getParameter("hdjsrq");

			querry.append((Base.isNull(nj)?"":" and nj = '"+nj+"' "));
			querry.append((Base.isNull(xydm)?"":" and (xydm = '"+xydm+"' or xydm='noxydm' )"));
			querry.append((Base.isNull(zydm)?"":" and (zydm = '"+zydm+"' or zydm='nozydm' )"));
			querry.append((Base.isNull(bjdm)?"":" and (bjdm = '"+bjdm+"' or bjdm='nobjdm' )"));
			String title ="";    	
			if(!Base.isNull(hdksrq)&&!Base.isNull(hdjsrq)){
				title += hdksrq+"-"+hdjsrq+"�ڼ�";
				querry.append(" and bgrq between '"+hdksrq+"' and '"+hdjsrq+"' ");
			}else if(!Base.isNull(hdksrq)){
				title += hdksrq;
				querry.append(" and hdksrq like '"+hdksrq+"%' ");
			}else if(!Base.isNull(hdjsrq)){
				title += hdjsrq;
				querry.append(" and hdjsrq like '"+hdjsrq+"%' ");
			}		
			sql = " select xymc,xh,xm,yzss,yzsfbz,xzss,xzsfbz,bgrq,bglx,bz from view_zsfbgtjxx"+querry;
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream(),title+"ѧ��ס�޷ѱ��ͳ��");
		} else if ("view_wjcf".equalsIgnoreCase(tableName) && Globals.XXDM_HYGXY.equalsIgnoreCase(xxdm)) {
			sql = "select " + zd + " from " + tableName + querry.toString();
			sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,xn,nd,xqmc,cflb,cfyy,cflbmc,cfyymc,wjsj,bz,cfjb,cfsj,cfwh,xysh,xyclyj,xxsh,xxclyj,cxwh,cxsj,cxjg from ("+sql+")" ;
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		}else if ("view_lzxx".equalsIgnoreCase(tableName)){
			lddm = request.getParameter("lddm");
			String sfzz = DealString.toGBK(request.getParameter("sfzz"));
			String lz = DealString.toGBK(request.getParameter("lz"));
			querry = new StringBuffer(" where 1=1 ");
			querry.append(Base.isNull(lddm)?"":"  and lddm='"+lddm+"' ");
			querry.append(Base.isNull(sfzz)?"":"  and sfzz='"+sfzz+"' ");
			querry.append(Base.isNull(lz)?"":"  and lz='"+lz+"' ");
			querry.append(Base.isNull(xm)?"":"  and xm like '%"+xm+"%' ");
			sql = " select * from view_lzxx"+querry;
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		}else if ("view_czxx".equalsIgnoreCase(tableName)){
			lddm = request.getParameter("lddm");
			String sfzz = DealString.toGBK(request.getParameter("sfzz"));
			String cz = DealString.toGBK(request.getParameter("cz"));
			querry = new StringBuffer(" where 1=1 ");
			querry.append(Base.isNull(lddm)?"":"  and lddm='"+lddm+"' ");
			querry.append(Base.isNull(sfzz)?"":"  and sfzz='"+sfzz+"' ");
			querry.append(Base.isNull(cz)?"":"  and cz='"+cz+"' ");
			querry.append(Base.isNull(xm)?"":"  and xm like '%"+xm+"%' ");
			sql = " select * from view_czxx"+querry;
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		}else if("view_fdybjdz".equalsIgnoreCase(tableName)){
			sql = " select * from view_fdybjdz  ";
			querry = new StringBuffer(" where 1=1 ");	
			if("xy".equalsIgnoreCase(userType)){
				querry.append(" and bmdm='"+userDep+"' ");
			}
			sql+=querry.toString();
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		}else if("view_fdybjdz".equalsIgnoreCase(tableName)){
			sql = " select * from view_fdybjdz  ";
			querry = new StringBuffer(" where 1=1 ");	
			if("xy".equalsIgnoreCase(userType)){
				querry.append(" and bmdm='"+userDep+"' ");
			}
			sql+=querry.toString();
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		}else if("view_xg_zxdk_sq".equalsIgnoreCase(tableName)){
			//ѧ������
			sql = " select rownum count,xymc,xh,xm,sfzh,sfzyxzzrq,csrq,jtdz,jtyb,dkhj,xq1,xq2,xq3,xq4,xq5,xfje,zsfje,yxmc,xb,rxrq,byny,xz,lxdh,lxdh2,lxdh3,qq,email,fqxm,fqsfzh,mqxm,mqsfzh,sjhm from view_xg_zxdk_sq a  ";
			querry = new StringBuffer(" where 1=1 ");	
			if("xy".equalsIgnoreCase(userType)){
				querry.append(" and xydm='"+userDep+"' ");
			}
			userName = request.getSession().getAttribute("userName").toString();
			String fdysql = "select count(*) num from fdybjb where zgh='"+userName+"'";
			int fdy =Integer.parseInt(dao.getOneRs(fdysql, new String[]{}, "num"));
			boolean isfdy = fdy >0 ? true : false;
			String bzrsql = "select count(*) num from bzrbbb where zgh='"+userName+"'";
			int bzr =Integer.parseInt(dao.getOneRs(bzrsql, new String[]{}, "num"));
			boolean isbzr = bzr >0 ? true : false;
			//nd=Base.currNd;
			querry.append(Base.isNull(nd)?"":"  and nd='"+nd+"' ");
			querry.append(Base.isNull(sfzh)?"":"  and sfzh like '%"+sfzh+"%' ");
			querry.append(Base.isNull(xh)?"":"  and xh like '%"+xh+"%' ");
			String xydm=request.getParameter("xydm");
			querry.append(Base.isNull(xydm)?"":"  and xydm='"+xydm+"' ");
			String zydm=request.getParameter("zydm");
			querry.append(Base.isNull(zydm)?"":"  and zydm='"+zydm+"' ");
			String bjdm=request.getParameter("bjdm");
			querry.append(Base.isNull(bjdm)?"":"  and bjdm='"+bjdm+"' ");
			if(true==isfdy && true==isbzr){
				querry.append(" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ");
				querry.append(" union select 1 from bzrbbb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ");
			}
			else if(isfdy){
				querry.append(" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ");
			}
			else if(isbzr){
				querry.append(" and exists(select 1 from bzrbbb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ");
			}
			sql+=querry.toString();
			
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		}else if("xg_view_zjjs_qgzx_lsgwxx".equalsIgnoreCase(tableName)){
//			StringBuilder query =new StringBuilder();
//			String gzkssj_ks=request.getParameter("gzkssj_ks");//������ʼʱ��_��ʼ
//			String gzkssj_js=request.getParameter("gzkssj_js");//������ʼʱ��_����
//			String gzjssj_ks=request.getParameter("gzjssj_ks");//��������ʱ��_��ʼ
//			String gzjssj_js=request.getParameter("gzjssj_js");//��������ʱ��_����
//			String gzzsj_ks=request.getParameter("gzzsj_ks");//������ʱ��_��ʼ
//			String gzzsj_js=request.getParameter("gzzsj_js");//������ʱ��_����
//			String gwmc=request.getParameter("gwmc");
//			String xydm=request.getParameter("xydm");
//			String zydm=request.getParameter("zydm");
//			String bjdm=request.getParameter("bjdm");
//			
//			query.append(Base.isNull(xm)?"":"  and xm like '%"+xm+"%' ");
//			query.append(Base.isNull(xh)?"":"  and xh like '%"+xh+"%' ");
//			query.append(Base.isNull(gwmc)?"":"  and gwmc like '%"+gwmc+"%' ");
//			query.append(Base.isNull(nj)?"":"  and nj='"+nj+"' ");
//			query.append(Base.isNull(xydm)?"":"  and nj='"+xydm+"' ");
//			query.append(Base.isNull(zydm)?"":"  and nj='"+zydm+"' ");
//			query.append(Base.isNull(bjdm)?"":"  and nj='"+bjdm+"' ");
			if("12862".equalsIgnoreCase(xxdm)){//�㽭���裬���Ի��ж�
				StringBuilder query =new StringBuilder();
				String gzzsj_ks=request.getParameter("gzzsj_ks");//������ʱ��_��ʼ
				String gzzsj_js=request.getParameter("gzzsj_js");//������ʱ��_����
				String gwmc=request.getParameter("gwmc");
				String xydm=request.getParameter("xydm");
				String zydm=request.getParameter("zydm");
				String bjdm=request.getParameter("bjdm");
				
				query.append(Base.isNull(xm)?"":"  and xm like '%"+xm+"%' ");
				query.append(Base.isNull(xh)?"":"  and xh like '%"+xh+"%' ");
				query.append(Base.isNull(gwmc)?"":"  and gwmc like '%"+gwmc+"%' ");
				query.append(Base.isNull(nj)?"":"  and nj='"+nj+"' ");
				query.append(Base.isNull(xydm)?"":"  and nj='"+xydm+"' ");
				query.append(Base.isNull(zydm)?"":"  and nj='"+zydm+"' ");
				query.append(Base.isNull(bjdm)?"":"  and nj='"+bjdm+"' ");
				
				sql="  select  a.xh,a.xm,a.xb,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc, ";
				sql+=" a.gwmc,a.gzdz,a.gzzsj from xg_view_zjjs_qgzx_lsgwxx a where 1=1 ";
				
				if(!Base.isNull(gzzsj_ks)){
					query.append(" and gzzsj >='"+gzzsj_ks+"' ");
				}
				if(!Base.isNull(gzzsj_js)){
					query.append(" and gzzsj <='"+gzzsj_js+"' ");
				}
				sql+=query;
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
			}else{
				StringBuilder query =new StringBuilder();
				String gzkssj_ks=request.getParameter("gzkssj_ks");//������ʼʱ��_��ʼ
				String gzkssj_js=request.getParameter("gzkssj_js");//������ʼʱ��_����
				String gzjssj_ks=request.getParameter("gzjssj_ks");//��������ʱ��_��ʼ
				String gzjssj_js=request.getParameter("gzjssj_js");//��������ʱ��_����
				String gwmc=request.getParameter("gwmc");
				String xydm=request.getParameter("xydm");
				String zydm=request.getParameter("zydm");
				String bjdm=request.getParameter("bjdm");
				
				query.append(Base.isNull(xm)?"":"  and xm like '%"+xm+"%' ");
				query.append(Base.isNull(xh)?"":"  and xh like '%"+xh+"%' ");
				query.append(Base.isNull(gwmc)?"":"  and gwmc like '%"+gwmc+"%' ");
				query.append(Base.isNull(nj)?"":"  and nj='"+nj+"' ");
				query.append(Base.isNull(xydm)?"":"  and nj='"+xydm+"' ");
				query.append(Base.isNull(zydm)?"":"  and nj='"+zydm+"' ");
				query.append(Base.isNull(bjdm)?"":"  and nj='"+bjdm+"' ");
				sql="  select  a.xh,a.xm,a.xb,a.nj,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc, ";
				sql+=" a.gwmc,a.gzkssj,a.gzjssj,a.gzdz from xg_view_zjjs_qgzx_lsgwxx a where 1=1 ";
				
				
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
				sql+=query;
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
			}
			
		}else{
			if (tableName.equalsIgnoreCase("view_stu_gdzlb")) {
				sql = "select xh,xm,zlbmc,sftj from " + tableName;
			} else if (tableName.equalsIgnoreCase("view_xsjbxx")) {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_DBLYDX)) {
					sql = "select xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,csrq,sfzh,sjhm,ah,tc,dzyx,sfdk,jrgcdsj,jrgqtsj,jtdz,jtyb,jtdh,"
						+ "jtjjqk,jtqkjj,jlcfjl,bz,shgxxm1,shgxgx1,shgxgzdw1,shgxzw1,shgxdwdh1,shgxsjhm1,shgxxm2,shgxgx2,shgxgzdw2"
						+ ",shgxzw2,shgxdwdh2,shgxsjhm2 from "
						+ tableName
						+ querry.toString();
				} else {
					if("xlytqkb".equalsIgnoreCase(realTable)){
						sql = "select * from view_xlytqkb"
							+ querry.toString();
						tableName = "view_xlytqkb";
					}else{
						sql = "select " + zd + " from " + tableName
						+ querry.toString();
					}
				}
			}
			else if (tableName.equalsIgnoreCase("view_xsjtxx")) {
				if(xxdm.equalsIgnoreCase("10649")){
					sql = "select xh,xm,nj,bjmc,zymc,xymc,jtszd,jtcy1_xm,jtcy1_zw,jtcy1_gx,jtcy1_zy,jtcy1_mz,jtcy1_zzmm,"
						+ "jtcy1_gzdz,jtcy1_nl,jtcy1_lxdh1,jtcy1_lxdh2,jtcy1_lxdh3,JTCY1_YZBM,jtcy2_xm,jtcy2_zw,jtcy2_gx,jtcy2_zy,jtcy2_mz,jtcy2_zzmm,"
						+ "jtcy2_gzdz,jtcy2_nl,jtcy2_lxdh1,jtcy2_lxdh2,jtcy2_lxdh3,JTCY2_YZBM,jtcy3_xm,jtcy3_zw,jtcy3_gx,jtcy3_zy,jtcy3_mz,jtcy3_zzmm,"
						+ "jtcy3_gzdz,jtcy3_nl,jtcy3_lxdh1,jtcy3_lxdh2,jtcy3_lxdh3,JTCY3_YZBM,zyshgxxx1,zyshgxxx2,zyshgxxx3 from "
						+ tableName + querry.toString();
				}else{
					sql = "select xh,xm,nj,bjmc,zymc,xymc,jtszd,jtcy1_xm,jtcy1_zw,jtcy1_gx,jtcy1_zy,jtcy1_mz,jtcy1_zzmm,"
						+ "jtcy1_gzdz,jtcy1_nl,jtcy1_lxdh1,jtcy1_lxdh2,jtcy1_lxdh3,JTCY1_YZBM,jtcy2_xm,jtcy2_zw,jtcy2_gx,jtcy2_zy,jtcy2_mz,jtcy2_zzmm,"
						+ "jtcy2_gzdz,jtcy2_nl,jtcy2_lxdh1,jtcy2_lxdh2,jtcy2_lxdh3,JTCY2_YZBM,jtcy3_xm,jtcy3_zw,jtcy3_gx,jtcy3_zy,jtcy3_mz,jtcy3_zzmm,"
						+ "jtcy3_gzdz,jtcy3_nl,jtcy3_lxdh1,jtcy3_lxdh2,jtcy3_lxdh3,JTCY3_YZBM from "
						+ tableName + querry.toString();
				}

			}
			else if(tableName.equalsIgnoreCase("view_gygl_fdyxqxx")){
				sql = "select xn,xq,fdyxm,xqsj,ldmc,qsh,zywt,xsfk,bz from view_gygl_fdyxqxx ";	
			}else if(tableName.equalsIgnoreCase("view_mrzbjl")){
				if(xxdm.equalsIgnoreCase(Globals.XXDM_CSMZZYJSXY)){
					sql = "select zbrlx,zbrymc,xqmc,sj,ldmc,zbjl,tfsjjcl,dgsj,lgsj,tq from view_mrzbjl order by zbrlx ";
				}else{
					sql = "select zbrymc,xqmc,sj,ldmc,zbjl,tfsjjcl,tq from view_mrzbjl"
					+ querry.toString();
				}				
			}else if (tableName.equalsIgnoreCase("view_zhszcp")) {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_GDBYXY)) {//�㶫����ѧԺ
					sql = "select xn,nd,xq,xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,cxcj,bz from view_zhszcp" + querry.toString();
				}else if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){//������
					sql = "select xn,xq,xh,xm,nj,xymc,zymc,bjmc,dyzpcj ���������ɼ�,dcjpm ���������༶����,kcjqpfj,jqfpm ��Ȩ��רҵ����,zhszcpzf ,zhpm �ۺϲ����༶���� from (select xn,xq,xh,xm,nj,xymc,zymc,bjmc,(to_number(ddcj)+to_number(xwcj)+to_number(shqcj)) dyzpcj," +
					"(dense_rank() over (partition by bjdm order by to_number(to_number(ddcj)+to_number(xwcj)+to_number(shqcj)) desc nulls last)) dcjpm,kcjqpfj," +
					"(dense_rank() over (partition by zydm order by to_number(kcjqpfj) desc nulls last)) jqfpm,zhszcpzf," +
					"(dense_rank() over (partition by bjdm order by to_number(zhszcpzf) desc nulls last)) zhpm from view_zhszcp )"  + querry.toString();
				}else if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
					sql = "select xn,nd,xq,xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xydykpf,gydykpf,zcj,tcj,gzxxcx,zhszcpzf,zhszcpcjpm,bz from view_zhszcp"  + querry.toString();
				}else if (xxdm.equalsIgnoreCase(Globals.XXDM_YCWSZYJSXY)) {
					sql = "select xn,nd,xq,xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,dcj,zcj,tcj,zpf from view_zhszcp"  + querry.toString();
				} else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJSYZYXY)) {
					sql = "select xn,nd,xqmc,xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,dcj,dcjpm,zcj,zcjpm,tcj,tcjpm,jnjf,jnjfpm,cpzf,cpzfpm,bz from view_zhszcp"  + querry.toString();
				} else if (Globals.XXDM_XMLGXY.equalsIgnoreCase(xxdm)) {
					//TODO ѧԺ������ʽ ������û�е�Ӵ
					HashMap<String, String> rs = dao.getMapNotOut(
							"select xydm,pmfs from pjpy_xmlg_xyzhszpmb where xydm=?",
							new String[] { xy });;
					
					String sqls ="";
					if ("zydm".equalsIgnoreCase(rs.get("pmfs"))) {
						sqls = "select xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq,xymc,zymc,bjmc,nj,xh,xm,xb,dcj,zcj,tcj,xqpm,xnpm from (select a.xh||a.xn||a.xq pk,a.*,dense_rank() over (partition by xq,xydm,zydm,bjdm order by xqzf desc nulls last) xqpm,dense_rank() over (partition by xn,xydm,zydm order by xnzf desc nulls last) xnpm  from (select xh,xm,xn,xq,xydm,zydm,bjdm,xb,nj,xymc,zymc,bjmc,dcj,zcj,tcj,dzscj+zzscj+tzscj xqzf,(sum(dzscj) over (partition by xh,xn) + sum(zzscj) over (partition by xh,xn) + sum(tzscj) over (partition by xh,xn)) xnzf from (select a.xh,a.xn,a.xq,a.dcj,a.zcj,a.tcj,b.xm,b.xb,b.xydm,b.zydm,b.bjdm,b.nj,b.xymc,b.zymc,b.bjmc,(dcj*(select dybl from zjcm_zhszcpblszb)/100) dzscj,(zcj*(select zybl from zjcm_zhszcpblszb)/100) zzscj,(tcj*(select tybl from zjcm_zhszcpblszb)/100) tzscj from zhszcp a left join view_xsjbxx b on a.xh=b.xh))a)a ";
					} else if ("bjdm".equalsIgnoreCase(rs.get("pmfs"))) {
						sqls = "select xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq,xymc,zymc,bjmc,nj,xh,xm,xb,dcj,zcj,tcj,xqpm,xnpm from (select a.xh||a.xn||a.xq pk,a.*,dense_rank() over (partition by xq,xydm,zydm,bjdm order by xqzf desc nulls last) xqpm,dense_rank() over (partition by xn,xydm,zydm,bjdm order by xnzf desc nulls last) xnpm  from (select xh,xm,xn,xq,xydm,zydm,bjdm,xb,nj,xymc,zymc,bjmc,dcj,zcj,tcj,dzscj+zzscj+tzscj xqzf,(sum(dzscj) over (partition by xh,xn) + sum(zzscj) over (partition by xh,xn) + sum(tzscj) over (partition by xh,xn)) xnzf from (select a.xh,a.xn,a.xq,a.dcj,a.zcj,a.tcj,b.xm,b.xb,b.xydm,b.zydm,b.bjdm,b.nj,b.xymc,b.zymc,b.bjmc,(dcj*(select dybl from zjcm_zhszcpblszb)/100) dzscj,(zcj*(select zybl from zjcm_zhszcpblszb)/100) zzscj,(tcj*(select tybl from zjcm_zhszcpblszb)/100) tzscj from zhszcp a left join view_xsjbxx b on a.xh=b.xh))a)a ";
					} else {
						sqls = "select xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq,xymc,zymc,bjmc,nj,xh,xm,xb" +
								",dcj,zcj,tcj,'' xqpm,'' xnpm from view_zhszcp a";
					}
					
					sql = sqls + querry.toString();
					expCloName = new String[] { "ѧ��", "ѧ��", "Ժϵ", "רҵ",
							"�༶", "�꼶", "ѧ��", "����", "�Ա�", "�������ַ�", "�������ַ�",
							"������ַ�", "�ۺϲ���ѧ������", "�ۺϲ���ѧ������" };
					expType = "self";
				} else {
					sql = "select " + zd + " from " + tableName + querry.toString();
				}
			} else if(tableName.equalsIgnoreCase("view_xscjff") && xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
				sql = "select xh,gwdm,cjje,ffsj,bz,sqsj,gzsj,xn,nd,xq,yf,sqdw,xqmc,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,yrdwmc ssbm,yrdwmc ,gwxzmc,xxsh,decode(fflx,'','����',fflx)fflx from " + tableName + " a "+ querry.toString();
			} else if ("view_wjcf".equalsIgnoreCase(tableName) && !Globals.XXDM_HYGXY.equalsIgnoreCase(xxdm)) {
				String actType = request.getParameter("actType");
				if (!"cfxxwh".equalsIgnoreCase(actType)) {
					actType  = " xxsh='ͨ��'";
				} else {
					actType = " 1=1 ";
				}
				if (!StringUtils.isNull(sfjw)) {
					querry.append(" and sfjw='");
					querry.append(sfjw);
					querry.append("'");
				}
				cs = request.getParameter("cs");
				if (StringUtils.isNotNull(cs)) {
					querry.append(" and xh in (select xh from wjcfb where xxsh='ͨ��' group by xh having count(xh) >= '"+cs+"')");
				}
				sql = "select xymc,zymc,bjmc,nj,xn,xq,nd,xh,xm,xb,cflbmc,cfyymc,bz,cfsj,cfwh,xxsh,xxclyj,ssjg,cxwh,cxsj from view_wjcf a " + querry.toString() + (StringUtils.isNull(querry.toString()) ? " where " + actType : " and" + actType);
			}else if ("view_xsjyxx".equalsIgnoreCase(tableName)){
				String sfjy = request.getParameter("sfjy");
				String sfqy = request.getParameter("sfqy");
				if (!StringUtils.isNull(sfjy)) {
					querry.append(" and sfjy='");
					querry.append(sfjy);
					querry.append("'");
				}
				if (!StringUtils.isNull(sfqy)) {
					querry.append(" and sfqy='");
					querry.append(sfqy);
					querry.append("'");
				}
				sql = "select * from view_xsjyxx "+ querry.toString();
			} else if("xljk_xsxlgcyb".equals(tableName)){				
				tableName = "VIEW_XLJK_XSXLGCYB";
				
				String expColumn = request.getParameter("mappingItems");
				if(StringUtils.isNotNull(expColumn)){
					zd = service.getDczd(expColumn);
				}
				if (StringUtils.isNotNull(nj)) {
					querry.append(" and nj='").append(nj).append("' ");
				}
				
				if (StringUtils.isNotNull(xh)) {
					querry.append(" and xh='").append(xh).append("' ");
				}
				
				if (StringUtils.isNotNull(xm)) {
					querry.append(" and xm='").append(xm).append("' ");
				}
				
				if (StringUtils.isNotNull(request.getParameter("xydm"))) {
					querry.append(" and xydm='").append(request.getParameter("xydm")).append("' ");
				}
				
				if (StringUtils.isNotNull(request.getParameter("bjdm"))) {
					querry.append(" and bjdm='").append(request.getParameter("bjdm")).append("' ");
				}
				
				if (StringUtils.isNotNull(request.getParameter("gcybh"))) {
					querry.append(" and gcybh='").append(request.getParameter("gcybh")).append("' ");
				}
				
				sql = "select " + zd + " from " + tableName + " a " + querry.toString();
			}else if("view_zjcm_wsjc".equalsIgnoreCase(tableName)){
				StringBuilder sb=new StringBuilder();
				StringBuilder xsxx=new StringBuilder();
				if(StringUtils.isNotNull(xqdm)){
					sb.append(" and xqdm='").append(xqdm).append("' ");
				}
				if (StringUtils.isNotNull(lddm)) {
					sb.append(" and lddm='").append(lddm).append("' ");
				}
				
				if (StringUtils.isNotNull(cs)) {
					sb.append(" and cs='").append(cs).append("' ");
				}
				
				if (StringUtils.isNotNull(qsh)) {
					sb.append(" and qsh='").append(qsh).append("' ");
				}
				
				if (StringUtils.isNotNull(xn)) {
					sb.append(" and xn='").append(request.getParameter("xn")).append("' ");
				}
				
				if (StringUtils.isNotNull(xq)) {
					sb.append(" and xq='").append(request.getParameter("xq")).append("' ");
				}
				
				if (StringUtils.isNotNull(kssj)) {
					sb.append(" and jcsj>='").append(request.getParameter("kssj")).append("' ");
				}
				
				if (StringUtils.isNotNull(jssj)) {
					sb.append(" and jcsj<='").append(request.getParameter("jssj")).append("' ");
				}
				
				if (StringUtils.isNotNull(nj)) {
					xsxx.append(" and nj='").append(request.getParameter("nj")).append("' ");
				}
				
				if (StringUtils.isNotNull(request.getParameter("xydm"))) {
					xsxx.append(" and xydm='").append(request.getParameter("xydm")).append("' ");
				}
				
				if (StringUtils.isNotNull(request.getParameter("zydm"))) {
					xsxx.append(" and zydm='").append(request.getParameter("zydm")).append("' ");
				}
				
				if (StringUtils.isNotNull(request.getParameter("bjdm"))) {
					xsxx.append(" and bjdm='").append(request.getParameter("bjdm")).append("' ");
				}
				
				sql = " select * from view_zjcm_wsjc a where exists(select * from view_xszsxx b where a.lddm=b.lddm and a.qsh=b.qsh "+xsxx.toString()+")  "+sb.toString();
				System.out.println(sql);
			}else{
				String expColumn = request.getParameter("mappingItems");
				if(StringUtils.isNotNull(expColumn)){
					zd = service.getDczd(expColumn);
				}
				sql = "select " + zd + " from " + tableName + " a " + querry.toString();
			}
//			if(realTable!=null && realTable.equalsIgnoreCase("xsxxb") && tableName.equalsIgnoreCase("view_xsjbxx")){
//				//ѧ����Ϣ
//				sql = service.getXsxxToExp() + querry.toString();	
//				tableName = "view_xsxxb";
//			}			
//			if(realTable!=null && realTable.equalsIgnoreCase("xsfzxxb")){
//				//ѧ����ͥ��Ϣ
//				sql = service.getXsfzxxToExp() + querry.toString();
//			}
			if(realTable!=null && (realTable.equalsIgnoreCase("xsjxjb") || realTable.equalsIgnoreCase("xsrychb"))){
				//��������
				sql = pjpyDao.getJxjColumns(tableName) + querry.toString();
				/*if (xxdm.equalsIgnoreCase(Globals.XXDM_HZZY)) {
					if (tableName.equalsIgnoreCase("view_xsjxjb")) {
						//sql = "select xn,nd,xq,xh,xm,xb,nj,xymc,zymc,bjmc,jxjmc,jxjlb,jlje,xysh,xxsh,fdyyj,xyshyj,xxshyj from view_xsjxjb " + querry.toString();
					} else
					if (tableName.equalsIgnoreCase("view_xsrychb")) {
						sql = "select xn,nd,xq,xh,xm,xb,nj,xymc,zymc,bjmc,rychmc,xysh,xxsh,fdyyj,xyyj,xxyj from view_xsrychb " + querry.toString();
					}
				}*/

				if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)) {
//					String sql1 = "select xh,xm,xn,nd,xq,nj,xb,xymc,zymc,bjmc,xydm,zydm,bjdm,rychdm,rychmc,xysh,xxsh,xyyj,xxyj,bz,wydj,jsjdj,bjpddj from (" + sql + ")";
					String sql1 = "select xh,xm,xn,nd,xq,nj,xb,xymc,zymc,bjmc,xydm,zydm,bjdm,xysh,xxsh,bz,bjpddj from (" + sql + ")";
					//================2010.10.21 edit by luojw==================================
					if ("view_xsrychb".equalsIgnoreCase(tableName)) {
						sql1 = "select xh,xm,xn,nd,xq,nj,xb,xymc,zymc,bjmc,xydm,zydm,bjdm,xysh,xxsh,bz,bjpddj,yhmc,yhkh from (" + sql + ")";
					}
					sql = sql1;
				}
			}
			if ("xsjxjb".equalsIgnoreCase(realTable)) {
				if (Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
					String sql1 = "select xh,xm,xn,nj,xb,xymc,zymc,bjmc,xydm,zydm,bjdm,jxjdm,jxjmc,jxjjb,jxjlb,jlje,xysh,xxsh from ("
						+ sql + ")";
					sql = sql1;
				} else if (Globals.XXDM_AHJZGYXY.equalsIgnoreCase(xxdm)) {
					String sql1 = "select xn,nd,xq,xh,xm,xymc,zymc,bjmc,jxjmc,jlje,kh,xysh,xxsh,bz from ("
						+ sql + ")";
					sql = sql1;
				} else if (Globals.XXDM_SCJZZYJSXY.equalsIgnoreCase(xxdm)) {
					String sql1 = "select a.xn,a.nd,a.xq,a.xh,a.xm,a.xymc,a.zymc,a.bjmc,a.jxjmc,a.jlje,mincj,avgcj,bjpm,zypm,a.cxcj,a.khss,a.kh,a.fdysh,a.xysh,a.xxsh from ("
						+ sql + ") a left join view_xscjpmb b on a.xh=b.xh and a.xn=b.xn and a.xq=b.xq";
					sql = sql1;
				} else if (Globals.XXDM_YCSFXY.equalsIgnoreCase(xxdm)) {
					sql = "select xymc,zymc,bjmc,nj,xh,xm,xb,xn,xqmc,jxjmc,je,pjkhcj,jdkhcj,xykhcj,xyzfpm,zhszcpzf,pm,xyshyj,xxsh,xxshyj from view_ycsf_xsjxjb a";
					sql += querry.toString();
					String dm = request.getParameter("dm");
					String zhszcpzf = request.getParameter("zhszcpzf");
					String pm = request.getParameter("pm");
					String xyzfpm = request.getParameter("xyzfpm");
					String jdcj = request.getParameter("jdcj");
					String xycj = request.getParameter("xycj");
					if (!StringUtils.isNull(dm)) {
						sql += " and dm='";
						sql += dm;
						sql += "' ";
					}
					if (!StringUtils.isNull(zhszcpzf)) {
						sql += " and zhszcpzf>=";
						sql += zhszcpzf;
					}
					if (!StringUtils.isNull(pm)) {
						sql += " and pm<=(select count(*)*("+pm+"/100) from view_xsjbxx where bjdm=a.bjdm) ";
					}
					if (!StringUtils.isNull(xm)) {
						sql += " and xm like'";
						sql += "%"+xm+"%'";
					}
					if (!StringUtils.isNull(xyzfpm)) {
						sql += " and xyzfpm <= (select count(*)*("+xyzfpm+"/100) from view_xsjbxx where bjdm=a.bjdm) ";
					}
					if (!StringUtils.isNull(jdcj)) {
						sql += " and jdkhcj >= "+jdcj+"";
					}
					if (!StringUtils.isNull(xycj)) {
						sql += " and  exists (select 1 from (select min(cj) mincj,xh from view_cjb where (kcxz='����' or kcxz='ѡ��') and xn='"+xn+"' and xq='"+xq+"'  group by xh) where mincj> "+xycj+" and xh=a.xh) ";
					}
					String bzrSql = "";
					//����Ա�û�ֻ��ѯ�༶����
					if ("fdy".equalsIgnoreCase(userType)) {
						bzrSql = " and exists (select 1 from fdybjb b where zgh like '"
							+ userName + "' and a.bjdm = b.bjdm)";
					}
					sql += bzrSql;
				} else if (Globals.XXDM_AHZYJSXY.equalsIgnoreCase(xxdm)) {
					sql = "select xymc,zymc,bjmc,nj,xh,xm,xb,xn,xqmc,jxjmc,je,pjf,pm,fdysh,fdyyj,xysh,xyshyj,xxsh,xxshyj from view_ahzyjs_xsjxjb a";
					sql += querry.toString();
					String dm = request.getParameter("dm");
					if (!StringUtils.isNull(dm)) {
						sql += " and dm='";
						sql += dm;
						sql += "' ";
					}
					if (!StringUtils.isNull(xm)) {
						sql += " and xm like'";
						sql += "%"+xm+"%'";
					}
					String bzrSql = "";
					//����Ա�û�ֻ��ѯ�༶����
					if ("fdy".equalsIgnoreCase(userType)) {
						bzrSql = " and exists (select 1 from fdybjb b where zgh like '"
							+ userName + "' and a.bjdm = b.bjdm)";
					}
					sql += bzrSql;
				} else if (Globals.XXDM_XMLGXY.equalsIgnoreCase(xxdm)) {//��������������
					sql = "select xymc,zymc,bjmc,nj,xh,xm,xb,xn,jxjmc,lbmc,wysp,jsjsp,drzw,jlqk,sqly,xysh,xyshyj,xxsh,xxshyj from (select a.xymc,a.zymc,a.bjmc,a.nj,a.xh," +
							"a.xm,a.xb,a.xn,a.jxjdm,a.lbdm,lbmc,a.jxjmc,b.wysp,b.jsjsp,b.drzw," +
							"b.jlqk,b.sqly,a.xydm,a.zydm,a.bjdm,a.xysh,a.xyshyj,a.xxsh,a.xxshyj from view_xsjxjb a left join xsjxjsqfzxxb" +
							" b on a.xh=b.xh and a.xn=b.xn)";
					
					String lbdm = request.getParameter("lbdm");
					if (!StringUtils.isNull(lbdm)) {
						querry.append(" and lbdm='");
						querry.append(lbdm);
						querry.append("'");
					}
					
					sql = sql + querry.toString();
					expCloName = new String[] { Base.YXPZXY_KEY, "רҵ", "�༶", "�꼶",
						 "ѧ��", "����", "�Ա�", "ѧ��", "��ѧ������",
							"��ѧ�����", "����ˮƽ", "�����ˮƽ", "����ְ��", "�������", "��������" , Base.YXPZXY_KEY+"���", Base.YXPZXY_KEY+"������", "ѧУ���", "ѧУ������"};
					expType = "self";
				} else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {//���ݴ�ѧ
					String sql1 = "select xymc,zymc,bjmc,nj,xh,xm,xn,xq,jxjmc,jlje,xxsh,xxshyj from ("
						+ sql + ")";
					sql = sql1;
				}
			}
			if ("xsrychb".equalsIgnoreCase(realTable)) {
				if (Globals.XXDM_NBLGXY.equalsIgnoreCase(xxdm)) {
					String sql1 = "select xh,xm,xn,nj,xb,xymc,zymc,bjmc,xydm,zydm,bjdm,rychdm,rychmc,xysh,xxsh from ("
						+ sql + ")";
					sql = sql1;
				} else if (Globals.XXDM_YCSFXY.equalsIgnoreCase(xxdm)) {
					sql = "select xymc,zymc,bjmc,nj,xh,xm,xb,xn,xqmc,rychmc,pjkhcj,jdkhcj,xykhcj,zhszcpzf,pm,xyyj,xxsh,xxyj from view_ycsf_xsrychb a";
					sql += querry.toString();
					String dm = request.getParameter("dm");
					String zhszcpzf = request.getParameter("zhszcpzf");
					String pm = request.getParameter("pm");
					if (!StringUtils.isNull(dm)) {
						sql += " and dm='";
						sql += dm;
						sql += "' ";
					}
					if (!StringUtils.isNull(zhszcpzf)) {
						sql += " and zhszcpzf>=";
						sql += zhszcpzf;
					}
					if (!StringUtils.isNull(pm)) {
						sql += " and pm<=";
						sql += pm;
					}
					String bzrSql = "";
					//����Ա�û�ֻ��ѯ�༶����
					if ("fdy".equalsIgnoreCase(userType)) {
						bzrSql = " and exists (select 1 from fdybjb b where zgh like '"
							+ userName + "' and a.bjdm = b.bjdm)";
					}
					sql += bzrSql;
				}
				else if (Globals.XXDM_AHZYJSXY.equalsIgnoreCase(xxdm)) {
					sql = "select xymc,zymc,bjmc,nj,xh,xm,xb,xn,xqmc,rychmc,pjf,pm,fdysh,fdyyj,xysh,xyyj,xxsh,xxyj from view_ahzyjs_xsrychb a";
					sql += querry.toString();
					String dm = request.getParameter("dm");
					if (!StringUtils.isNull(dm)) {
						sql += " and dm='";
						sql += dm;
						sql += "' ";
					}
					if (!StringUtils.isNull(xm)) {
						sql += " and xm like'";
						sql += "%"+xm+"%'";
					}
					String bzrSql = "";
					//����Ա�û�ֻ��ѯ�༶����
					if ("fdy".equalsIgnoreCase(userType)) {
						bzrSql = " and exists (select 1 from fdybjb b where zgh like '"
							+ userName + "' and a.bjdm = b.bjdm)";
					}
					sql += bzrSql;
				}else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {//���ݴ�ѧ
					String sql1 = "select xymc,zymc,bjmc,nj,xh,xm,xn,xq,rychmc,xxsh,xxyj from ("
						+ sql + ")";
					sql = sql1;
				}
			}
			
			if ("view_pjpy_cxfb".equalsIgnoreCase(tableName)) {
				String fs = request.getParameter("fs");
				if ("1".equalsIgnoreCase(fs)) {
					sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,xn,xq,cxxm,cxfs,cxlb from (" + sql + ")" + "where cxlb='1'";
				} else if ("2".equalsIgnoreCase(fs)) {
					sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,xn,xq,cxxm,cxfs,cxlb from (" + sql + ")" + "where cxlb='0'";
				} else if ("3".equalsIgnoreCase(fs)) {
					sql = "select distinct xh,xm,xb,nj,xymc,zymc,bjmc,xn,xq,sum(to_number(cxfs)) over" +
					" (partition by xh,xn,xq) cxfs from (select xymc,zymc,xb,nj,xh,xn,xq,bjmc,xm,(case when trim(cxlb)='1'" +
					" then cxfs when trim(cxlb)='0' then '-'||cxfs else cxfs end) cxfs from ("+sql+"))";
				} else {
					sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,xn,xq,cxxm,cxfs,cxlb from (" + sql + ")";
				}
			}
			if("view_xyftjmdj".equalsIgnoreCase(tableName)){
				//�ճ����� ������
				xysh = DealString.toGBK(request.getParameter("xysh"));
				xxsh = DealString.toGBK(request.getParameter("xxsh"));
				String xydm = DealString.toGBK(request.getParameter("xydm"));
				StringBuffer sb = new StringBuffer("select fttm,ftsj,ftdd,jmfzr,xymc,sfjb from view_xyftjmdj where 1=1 ");
				if(!xysh.equalsIgnoreCase("")){
					sb.append(" and xysh ='");
					sb.append(xysh);
					sb.append("' ");
				}
				if(!xxsh.equalsIgnoreCase("")){
					sb.append(" and xxsh ='");
					sb.append(xysh);
					sb.append("' ");
				}
				if(!xydm.equalsIgnoreCase("")){
					sb.append(" and xydm ='");
					sb.append(xydm);
					sb.append("' ");
				}
				sql = sb.toString();
			}
			if ("pjpy_ycsf_zhszcpb".equalsIgnoreCase(tableName)) {
				sql = "select xymc,zymc,bjmc,nj,xh,xm,xn,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,pjkhcj,jdkhcj from " +
				"(select a.xh,a.xydm,a.nj,a.xymc,a.zymc,a.zydm,a.bjdm,a.xm,a.xb,a.bjmc,'"
				+ xn
				+ "' xn,'"
				+ xq
				+ "' xq,b.pjkhcj,"
				+ "b.jdkhcj,b.xxsh from view_xsjbxx a left join pjpy_ycsf_zhszcpb b on a.xh=b.xh "
				+ "and b.xn='"+xn+"' and b.xq='"+xq+"') a";
				String bzrSql = "";
				//����Ա�û�ֻ��ѯ�༶����
				if ("fdy".equalsIgnoreCase(userType)) {
					bzrSql = " and exists (select 1 from fdybjb b where zgh like '"
						+ userName + "' and a.bjdm = b.bjdm)";
				}
				sql = sql + querry.toString() + bzrSql;
				expType = "self";
				expCloName = new String[]{Base.YXPZXY_KEY+"����","רҵ����","�༶����","�꼶","ѧ��","����","ѧ��","ѧ��","ƽʱ���˳ɼ�","�׶ο��˳ɼ�"};
			}
			if("view_jxgl_jgxx".equalsIgnoreCase(tableName)){
				StringBuffer sb = new StringBuffer(" where 1=1");
				String jxnd =  dataSearchForm.getJxnd();
 				if(!Base.isNull(jxnd)){
					sb.append(" and a.jxnd ='");
					sb.append(jxnd);
					sb.append("' ");
				}
 				sb.append(" order by bzdj");
				sql = "select distinct (a.jgbh), a.xm, a.xb, a.mzdm, a.lxdh, a.ddcs, a.bz, a.jxnd, a.zw,"
					+" a.mzmc,b.bzmc,b.bzdj from view_jxgl_jgxx a left join jxbzdmb b on a.jgbh = b.jgbh";
				sql+=sb.toString();
			}
			if("view_jxgl_jsxx".equalsIgnoreCase(tableName)){
				StringBuffer sb = new StringBuffer(" where 1=1");
				String jxnd =  dataSearchForm.getJxnd();
 				if(!Base.isNull(jxnd)){
					sb.append(" and b.jxnd ='");
					sb.append(jxnd);
					sb.append("' ");
				}
 				sb.append(" order by bzdj");
				sql = "select b.xm,b.����,b.jsdm,b.xb,b.mzmc,b.lxdh,b.jxnd,b.sfzld"
						+ " from (select distinct (a.jsdm),a.xm,a.jsdm ����,b.bzdj,decode(a.xb,'��','1','Ů','2') xb,a.mzmc,a.lxdh,a.jxnd,"
						+ " b.bzdm sfzld from view_jxgl_jsxx a left join jxbzdmb b on b.jsdm = a.jsdm) b ";
				sql+=sb.toString();
			}
			if("view_jhzy_dyf".equalsIgnoreCase(tableName)){
				String dqxq = dao.getOneRs("select xqmc from xqdzb where xqdm = ?", new String[]{Base.currXq},"xqmc");
				StringBuffer sb = new StringBuffer();

				sb.append("select * from (select t.* from (select distinct (xh),xm,xb,nj,xydm,");
				sb.append(" xymc,zydm,zymc,bjdm,bjmc,nvl(xn, dqxn) xn,nvl(xq, dqxq) xq,dyf  from (select *");
				sb.append(" from view_jhzy_dyf union select xh,xm,xb,nj,xydm,xymc,zydm,");
				sb.append(" zymc,bjdm,bjmc,'' xn,'"+Base.currXn+"' dqxn,'' xq,'"+dqxq+"' dqxq,");
				sb.append(" '' dyf from view_xsjbxx b where not exists(select 1 from jhzy_dyf a where ");
				sb.append(" a.xh||a.xn||a.xq = b.xh||'"+Base.currXn+"'||'"+Base.currXq+"')) order by bjdm,xh,xn,xq) t "+querry+" )");
			
				sql = sb.toString();
			}else if("view_jhzy_tyf".equalsIgnoreCase(tableName)){
				String dqxq = dao.getOneRs("select xqmc from xqdzb where xqdm = ?", new String[]{Base.currXq},"xqmc");
				StringBuffer sb = new StringBuffer();

				sb.append("select * from (select t.* from (select distinct (xh),xm,xb,nj,xydm,");
				sb.append(" xymc,zydm,zymc,bjdm,bjmc,nvl(xn, dqxn) xn,nvl(xq, dqxq) xq,tyf  from (select *");
				sb.append(" from view_jhzy_tyf union select xh,xm,xb,nj,xydm,xymc,zydm,");
				sb.append(" zymc,bjdm,bjmc,'' xn,'"+Base.currXn+"' dqxn,'' xq,'"+dqxq+"' dqxq,");
				sb.append(" '' tyf from view_xsjbxx b where not exists(select 1 from jhzy_tyf a where ");
				sb.append(" a.xh||a.xn||a.xq = b.xh||'"+Base.currXn+"'||'"+Base.currXq+"')) order by bjdm,xh,xn,xq) t "+querry+" )");
			
				sql = sb.toString();
			}else if(tableName !=null && tableName.equalsIgnoreCase("view_xjqsxx")){
				String lc = dataSearchForm.getLc();
				qsh = dataSearchForm.getQsh();
				lddm = dataSearchForm.getLddm();
				String xydm = dataSearchForm.getXydm();
				StringBuffer query = new StringBuffer();
				query.append(" where 1=1 ");
				String query1 = "";
				query.append(Base.isNull(xn) ? "" :" and xn='"+xn+"'");
				query.append(Base.isNull(yf) ? "" :" and substr(sqsj,5,2)='"+yf+"'");
				query.append(Base.isNull(lddm) ? "" :" and lddm='"+lddm+"'");
				query.append(Base.isNull(lc) ? "" :" and cs='"+lc+"'");
				query.append(Base.isNull(qsh) ? "" :" and qsh='"+qsh+"'");
				query.append(Base.isNull(yesNo) ? "" :" and xxsh='"+yesNo+"'");
				if(!Base.isNull(xydm)){
					query1 = " and exists (select 1 from (select distinct ssbh from view_xszsxx where xydm='"+xydm+"') where ssbh=a.ssbh)";
				}
				query1 += " order by xn,sqsj,lddm,cs,qsh";
				sql = "select xn,ssbh,ldmc,cs,qsh,lxdh,dj,fdysh,xysh,xxsh,sqsj,qsjsqk from view_xjqsxx a "+query.toString()+query1;
				expType = "self";
				expCloName = new String[]{"ѧ��","������","¥������","¥��","���Һ�","��ϵ�绰","�ȼ�","����Ա���",Base.YXPZXY_KEY+"���","ѧУ���","����ʱ��","���ҽ������"};
				
			}else if(tableName !=null && tableName.equalsIgnoreCase("view_ajqsb")){
				String lc = dataSearchForm.getLc();
				qsh = dataSearchForm.getQsh();
				lddm = dataSearchForm.getLddm();
				xxsh = DealString.toGBK(request.getParameter("xxsh"));
				String sqrq1 = DealString.toGBK(request.getParameter("sqrq1"));
				String sqrq2 = DealString.toGBK(request.getParameter("sqrq2"));
				String sfcx = DealString.toGBK(request.getParameter("sfcx"));
				StringBuffer query = new StringBuffer(" where 1=1 ");
				query.append(Base.isNull(lddm) ? "" :" and lddm='"+lddm+"'");
				query.append(Base.isNull(lc) ? "" :" and cs='"+lc+"'");
				query.append(Base.isNull(qsh) ? "" :" and qsh='"+qsh+"'");
				query.append(Base.isNull(xxsh) ? "" :" and xxsh='"+xxsh+"'");
				query.append(Base.isNull(sqrq1) ? "" :" and sqsj>='"+sqrq1+"'");
				query.append(Base.isNull(sqrq2) ? "" :" and sqsj<='"+sqrq2+"'");
				query.append(Base.isNull(sfcx) ? "" :" and nvl(sfcx,'��')='"+sfcx+"'");
				query.append(Base.isNull(xn) ? "" :" and xn='"+xn+"'");
				query.append(Base.isNull(xq) ? "" :" and xq='"+xq+"'");
				sql = "select * from view_ajqsb "+query.toString();			
			}else if(tableName !=null && tableName.equalsIgnoreCase("view_wmqsb")){
				String lc = dataSearchForm.getLc();
				qsh = dataSearchForm.getQsh();
				lddm = dataSearchForm.getLddm();
				xxsh = DealString.toGBK(request.getParameter("xxsh"));
				String sqrq1 = DealString.toGBK(request.getParameter("sqrq1"));
				String sqrq2 = DealString.toGBK(request.getParameter("sqrq2"));
				StringBuffer query = new StringBuffer(" where 1=1 ");
				query.append(Base.isNull(lddm) ? "" :" and lddm='"+lddm+"'");
				query.append(Base.isNull(lc) ? "" :" and cs='"+lc+"'");
				query.append(Base.isNull(qsh) ? "" :" and qsh='"+qsh+"'");
				query.append(Base.isNull(xxsh) ? "" :" and xxsh='"+xxsh+"'");
				query.append(Base.isNull(sqrq1) ? "" :" and sqsj>='"+sqrq1+"'");
				query.append(Base.isNull(sqrq2) ? "" :" and sqsj<='"+sqrq2+"'");
				query.append(Base.isNull(xn) ? "" :" and xn='"+xn+"'");
				query.append(Base.isNull(xq) ? "" :" and xq='"+xq+"'");
				String sqlquery = "";
				if(!("xx".equals(userType) || "admin".equals(userType))){
					sqlquery = " and exists(select 1 from view_xszsxx where xydm='"+userDep+"' and ssbh=a.ssbh)";
				}
				sql = "select * from view_wmqsb a"+query.toString()+sqlquery;			
			}else if(tableName !=null && tableName.equalsIgnoreCase("view_tsqsb")){
				String lc = dataSearchForm.getLc();
				qsh = dataSearchForm.getQsh();
				lddm = dataSearchForm.getLddm();
				xxsh = DealString.toGBK(request.getParameter("xxsh"));
				String sqrq1 = DealString.toGBK(request.getParameter("sqrq1"));
				String sqrq2 = DealString.toGBK(request.getParameter("sqrq2"));
				StringBuffer query = new StringBuffer(" where 1=1 ");
				query.append(Base.isNull(lddm) ? "" :" and lddm='"+lddm+"'");
				query.append(Base.isNull(lc) ? "" :" and cs='"+lc+"'");
				query.append(Base.isNull(qsh) ? "" :" and qsh='"+qsh+"'");
				query.append(Base.isNull(xxsh) ? "" :" and xxsh='"+xxsh+"'");
				query.append(Base.isNull(sqrq1) ? "" :" and sqsj>='"+sqrq1+"'");
				query.append(Base.isNull(sqrq2) ? "" :" and sqsj<='"+sqrq2+"'");
				query.append(Base.isNull(xn) ? "" :" and xn='"+xn+"'");
				String sqlquery = "";
				if(!("xx".equals(userType) || "admin".equals(userType))){
					sqlquery = " and exists(select 1 from view_xszsxx where xydm='"+userDep+"' and ssbh=a.ssbh)";
				}
				sql = "select * from view_tsqsb a"+query.toString()+sqlquery;				
			}else if(tableName !=null && tableName.equalsIgnoreCase("view_xshkgl")){
				String hkzt = DealString.toGBK(request.getParameter("hkzt"));
				querry.append(Base.isNull(hkzt) ? "" :" and hkzt='"+hkzt+"'");
				sql = "select * from view_xshkgl a"+querry.toString();				
			}else if(tableName !=null && tableName.equalsIgnoreCase("view_sfzbbsqb")){
				String bbzt = DealString.toGBK(request.getParameter("bbzt"));
				String fdysh = DealString.toGBK(request.getParameter("fdysh"));			
				querry.append(Base.isNull(bbzt) ? "" :" and bbzt='"+bbzt+"'");
				querry.append(Base.isNull(fdysh) ? "" :" and fdysh='"+fdysh+"'");
				if (session.getAttribute("fdyQx").toString() != null
						&& "true".equalsIgnoreCase(session.getAttribute("fdyQx")
								.toString())) {
					querry.append(" and exists(select 1 from fdybjb where zgh='"+userName+"' and bjdm=a.bjdm)");
				}
				sql = "select * from view_sfzbbsqb a "+querry.toString();				
			}else if(tableName !=null && tableName.equalsIgnoreCase("view_cqtjb")){
				String jcsj = DealString.toGBK(request.getParameter("jcsj"));
				querry.append(Base.isNull(jcsj) ? "" :" and jcsj='"+jcsj+"'");
				sql = "select * from view_cqtjb a "+querry.toString();
			}else if(tableName !=null && tableName.equalsIgnoreCase("zyyysqb")){
				String bm = DealString.toGBK(request.getParameter("bm"));
				String cddm = DealString.toGBK(request.getParameter("cddm"));
				String yyrq = DealString.toGBK(request.getParameter("yyrq"));
				querry = new StringBuffer(" where 1=1 ");
				userType = session.getAttribute("userType")==null?"":
					session.getAttribute("userType").toString();
				if(!"admin".equals(userType)){
					bm = userDep;
				}
				querry.append(Base.isNull(bm) ? "" :" and bm='"+bm+"'");
				querry.append(Base.isNull(cddm) ? "" :" and cddm='"+cddm+"'");
				querry.append(Base.isNull(yyrq) ? "" :" and yyrq='"+yyrq+"'");
				
				sql = "select (select bmmc from zxbz_xxbmdm where bmdm=a.bm) bmmc,(select mc from cdyyb where dm=a.cddm) cdmc,"
					+"yyrq,yysjd,fzr,lxdh,dmToMc(a.yysb) yysbmc,hdnr,bz,week,xxsh,bm,cddm from zyyysqb a "+querry.toString();
				expType = "self";
				expCloName = new String[]{"��������","��������","ԤԼ����","ԤԼʱ���","������","��ϵ�绰","ԤԼ�豸","�����","��ע","����","ѧУ���","���Ŵ���","���ش���"};
				//���ݴ�ѧ
				if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
					sql = "select (select bmmc from zxbz_xxbmdm where bmdm=a.bm) bmmc,(select mc from cdyyb where dm=a.cddm) cdmc,"
						+"yyrq,yysjd,fzr,lxdh,dmToMc(a.yysb) yysbmc,hdnr,bz,week,xxsh,bm,cddm,sqsj,shpf from zyyysqb a "+querry.toString();
					expType = "self";
					expCloName = new String[]{"��������","��������","ԤԼ����","ԤԼʱ���","������","��ϵ�绰","ԤԼ�豸","�����","��ע","����","ѧУ���","���Ŵ���","���ش���","����ʱ��","�������"};
				} 
			}else if(tableName !=null && tableName.equalsIgnoreCase("jhzy_gyfdyb")){
				String zgh = request.getParameter("zgh");
				xm = request.getParameter("xm");
				String xydm = request.getParameter("xydm");
				lddm = request.getParameter("lddm");
				String lcdm = request.getParameter("lcdm");
				qsh = request.getParameter("qsh");
				StringBuffer query = new StringBuffer("where 1=1 ");
				query.append(Base.isNull(zgh) ? " and 1=1" : " and zgh='"+zgh.trim()+ "'");
				query.append(Base.isNull(xm) ? " and 1=1" : " and xm like '%"+xm.trim()+ "%'");
				query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm.trim()+ "'");
				query.append(Base.isNull(lddm) ? " and 1=1" : " and lddm='"+lddm.trim()+ "'");	
				query.append(Base.isNull(lcdm) ? " and 1=1" : " and cs='"+lcdm.trim()+ "'");
				query.append(Base.isNull(qsh) ? " and 1=1" : " and qsh='"+qsh.trim()+ "'");
				sql = "select distinct ldmc,cs,qsh,zgh,xm,xymc from (select distinct rownum r,lddm,ldmc,cs,qsh,zgh,xm,xydm,xymc from view_xzgyfdy a "+query.toString()+")";
				expType = "self";
				expCloName = new String[]{"¥������","����","���Һ�","����Աְ����","����Ա����",Base.YXPZXY_KEY+"����"};
			}else if(tableName !=null && tableName.equalsIgnoreCase("zbryk")){
				sql = "select xh,xm,xymc,zymc,bjmc,bz from (select a.xh,xm,xymc,zymc,bjmc,a.bz,b.xydm,b.zydm,b.bjdm from zbryk a,view_xsjbxx b where a.xh=b.xh) "+querry.toString();
				expType = "self";
				expCloName = new String[]{"ѧ��","����",Base.YXPZXY_KEY+"����","רҵ����","�༶����","��ע"};
			}else if(tableName !=null && tableName.equalsIgnoreCase("stu_archives_apply")){
				String act = request.getParameter("act");
				xxsh = request.getParameter("xxsh");
				String zdlbcn = "";
				String zdlb = request.getParameter("zdlb");
				if("sx".equals(zdlb)){
					zdlbcn = "��ѧ";
				}else if("zx".equals(zdlb)){
					zdlbcn = "תѧ";
				}else if("tx".equals(zdlb)){
					zdlbcn = "��ѧ";
				}else if("by".equals(zdlb)){
					zdlbcn = "��ҵ";
				}
				if("auditing".equals(act)){
					querry.append(Base.isNull(xxsh) ? " and 1=1" : " and xxsh='"+xxsh+ "'");	
				}
				querry.append(Base.isNull(zdlb) ? " and 1=1" : " and zdlb='"+zdlbcn+ "'");
				sql = "select * from (select a.xh,a.xm,a.xymc,a.zymc,a.bjmc,b.nd,b.zddwmc,b.zddwdz,b.zddwyb,b.zdlb,b.xxsh," +
						"b.zcbh,b.wjh,b.xxmc,b.dazcsj,b.dazcfs,b.jyh,b.sfyhz,b.hztp,a.xydm,a.zydm,a.bjdm " +
					"from view_xsjbxx a,stu_archives_apply b where a.xh=b.xh order by a.xydm,a.zydm,a.bjdm) "+querry;
				expType = "self";
				expCloName = new String[]{"ѧ��","����",Base.YXPZXY_KEY+"����","רҵ����","�༶����","���","ת����λ����","ת����λ��ַ","ת����λ�ʱ�",
						"ת�����","ѧУ���","ת�����","�ļ���","ѧУ����","����ת��ʱ��","����ת����ʽ","��Ҫ��","�Ƿ��л�ִ","��ִͼƬ",Base.YXPZXY_KEY+"����","רҵ����","�༶����"};
			} else if ("view_gzdx_zhszcp".equalsIgnoreCase(tableName)) {
				sql = "select xh,xm,xb,nj,xymc,zymc,bjmc,xn,nvl(case when instr(to_char(xycpf),'.',1,1)=1 then '0'||to_char(xycpf) when instr(to_char(xycpf),'-',1,1)=1 and instr(to_char(xycpf),'.',2,1)=2 then replace(xycpf,'-','-0') else to_char(xycpf) end, '0') xycpf,nvl(case when instr(to_char(zhbxf),'.',1,1)=1 then '0'||to_char(zhbxf) when instr(to_char(zhbxf),'-',1,1)=1 and instr(to_char(zhbxf),'.',2,1)=2 then replace(zhbxf,'-','-0') else to_char(zhbxf) end, '0') zhbxf," +
				"nvl(case when instr(to_char(xwbxf),'.',1,1)=1 then '0'||to_char(xwbxf) when instr(to_char(xwbxf),'-',1,1)=1 and instr(to_char(xwbxf),'.',2,1)=2 then replace(xwbxf,'-','-0') else to_char(xwbxf) end, '0') xwbxf,nvl(case when instr(to_char(tcbxf),'.',1,1)=1 then '0'||to_char(tcbxf) when instr(to_char(tcbxf),'-',1,1)=1 and instr(to_char(tcbxf),'.',2,1)=2 then replace(tcbxf,'-','-0') else to_char(tcbxf) end, '0') tcbxf,nvl(case when instr(to_char(zf),'.',1,1)=1 then '0'||to_char(zf) when instr(to_char(zf),'-',1,1)=1 and instr(to_char(zf),'.',2,1)=2 then replace(zf,'-','-0') else to_char(zf) end, '0') zf,bjpm from view_gzdx_zhszcp" + querry.toString();
				
			} else if (Globals.XXDM_BJLHDX.equalsIgnoreCase(xxdm) && "ssfpb".equalsIgnoreCase(realTable)) {
				
				querry =new StringBuffer(" where 1=1 ");
				
				xqdm = request.getParameter("xqdm");
				lddm = request.getParameter("lddm");
				cs = request.getParameter("cs");
				qsh = request.getParameter("qsh");
				String xbxd = request.getParameter("xb");
				String fbbj = request.getParameter("fbbj");
				String xydm = request.getParameter("xydm");
				if ("0202".equalsIgnoreCase(fbbj)) {
					xydm = "0202";
				} else if ("0405".equalsIgnoreCase(fbbj)) {
					xydm = "0405";
				} else if ("0110".equalsIgnoreCase(fbbj)) {
					xydm = "0110";
				} else if ("0117".equalsIgnoreCase(fbbj)) {
					xydm = "0117";
				} 
				StringBuffer ssfp = new StringBuffer("select (select xqmc from dm_zju_xq b where a.xqdm=b.dm) xqmc,ldmc,xb,cs,qsh,xymc from (")
							                .append("select a.*,(select xqdm from sslddmb b where a.lddm=b.lddm) xqdm,")
							                .append("(select xbxd from sslddmb b where a.lddm=b.lddm) xb,")
							                .append("(select ldmc from sslddmb b where a.lddm=b.lddm) ldmc,")
							                .append("(case when xydm='0202' then '��ί' when xydm='0405' then '������ѧ��' when xydm='0110' then '���д�' when xydm='0117' then '���˽�����' else (select xymc from view_njxyzybj b where a.xydm=b.xydm and rownum=1) end) xymc from (")
							                .append("select xydm,ssbh,cws,(select lddm from bjlh_ssxxb b where a.ssbh=b.lddm||b.cs||b.qsh) lddm,")
							                .append("(select cs from bjlh_ssxxb b where a.ssbh=b.lddm||b.cs||b.qsh) cs,")
							                .append("(select qsh from bjlh_ssxxb b where a.ssbh=b.lddm||b.cs||b.qsh) qsh from ssfpb a) a) a");
				sql = ssfp.toString();
				if (!StringUtils.isNull(xqdm)) {
					querry.append(" and xqdm='");
					querry.append(xqdm);
					querry.append("'");
				}
				if (!StringUtils.isNull(lddm)) {
					querry.append(" and lddm='");
					querry.append(lddm);
					querry.append("'");
				}
				if (!StringUtils.isNull(cs)) {
					querry.append(" and cs='");
					querry.append(cs);
					querry.append("'");
				}
				if (!StringUtils.isNull(qsh)) {
					querry.append(" and qsh='");
					querry.append(qsh);
					querry.append("'");
				}
				if (!StringUtils.isNull(xbxd)) {
					querry.append(" and xb='");
					querry.append(xbxd);
					querry.append("'");
				}
				if (!StringUtils.isNull(xydm)) {
					querry.append(" and xydm='");
					querry.append(xydm);
					querry.append("'");
				}
				sql += querry.toString();
				if ("qrz".equalsIgnoreCase(fbbj)) {
					sql += " and xydm not in ('0202','0405','0110','0117') ";
				}
				expCloName = new String[]{ "У������", "¥������","�Ա��޶�", "��������", "���Һ�", "����"+Base.YXPZXY_KEY+"(����)"};
				expType = "self";
			} else if ("view_czxx_dyjcfb".equalsIgnoreCase(tableName)
					|| "view_czxx_dyfjfb".equalsIgnoreCase(tableName)
					|| "view_czxx_zyfjfb".equalsIgnoreCase(tableName)) {
				if ("true".equalsIgnoreCase(isFdyStr)) {
					sql = "select a.* from ("+sql +") a"+" where exists(select 1 from view_fdybbj c "
					+ "where a.bjdm=c.bjdm and c.zgh='"
					+ userName
					+ "')";
				}
			} else if ("czxx_dycjb".equalsIgnoreCase(tableName)) {
				DyjcfDAO myDao = new DyjcfDAO();
				HashMap<String, String> rs = myDao.queryDycjBl();
				StringBuffer querySql = new StringBuffer("select xymc,xydm,zymc,zydm,bjmc,bjdm,xh,xm,xb,nj,xn,xq,cj,gff,fjf,nvl(case when instr(to_char(dyzf),'.',1,1)=1 then '0'||to_char(dyzf) else to_char(dyzf) end, '0') dyzf ")
				.append("from (select a.*,")
			    .append("nvl(case when instr(to_char(b.cj),'.',1,1)=1 then '0'||to_char(b.cj) else to_char(b.cj) end, '0') cj,nvl(case when instr(to_char(c.fjf),'.',1,1)=1 then '0'||to_char(c.fjf) else to_char(c.fjf) end, '0') fjf,nvl(case when instr(to_char(d.gff),'.',1,1)=1 then '0'||to_char(d.gff) else to_char(d.gff) end, '0') gff,((nvl(b.cj,0)*"+rs.get("dyjcfbl")+"/100) + ")
			    .append("(nvl(c.fjf,0)*"+rs.get("dyfjfbl")+"/100) + (nvl(d.gff,0)*"+rs.get("dyssgffbl")+"/100")
			    .append(")) dyzf from (select xymc,zymc,bjmc,xh,xm,xydm,zydm,bjdm")
			    .append(",nj,xb,'"+xn+"' xn,'"+xq+"' xq from ")
			    .append("view_xsjbxx a) a left join czxx_dyjcfb b ")
			    .append("on a.xh=b.xh and a.xn=b.xn and a.xq=b.xq ")
			    .append("left join (select xh,xn,xq,sum(nvl(lb,'')")
			    .append("||nvl(fs,0)) fjf from czxx_dyfjfb group ")
			    .append("by xh,xn,xq) c on a.xh=c.xh and a.xn=c.xn")
			    .append(" and a.xq=c.xq left join ssshgff d on ")
			    .append("a.xh=d.xh and a.xn=d.xn and a.xq=d.xq) a");
				sql = querySql.toString() + querry.toString();
				if ("true".equalsIgnoreCase(isFdyStr)) {
					sql = "select a.* from ("+sql +") a"+" where exists(select 1 from view_fdybbj c "
					+ "where a.bjdm=c.bjdm and c.zgh='"
					+ userName
					+ "')";
				}
				
				expCloName = new String[]{ Base.YXPZXY_KEY, Base.YXPZXY_KEY+"����","רҵ", "רҵ����", "�༶", "�༶����", "ѧ��", "����", "�Ա�", "�꼶","ѧ��","ѧ��","����������","��������淶��","�������ӷ�","�����ɼ�"};
				expType = "self";
			} else if ("view_cjb".equalsIgnoreCase(tableName)) {
				sql = "select xymc,zymc,bjmc,nj,xh,xm,xb,xn,xq,kcmc,cj,kcxz from (" + sql +")";
				if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {
					sql = "select xymc,zymc,bjmc,nj,xh,xm,xb,xn,xq,kcmc,cj,kcxz,(select round(avg(cj),2) from cjb b where khfs like '����%' and not exists (select 1 from tykjxrwb b where a.xn=b.xn and a.xq=b.xq and a.xkkh=b.xkkh and rownum=1) and a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) kscj,(select round(avg(cj),2) from cjb b where khfs like '����%' and not exists (select 1 from tykjxrwb b where a.xn=b.xn and a.xq=b.xq and a.xkkh=b.xkkh and rownum=1) and a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) kccj from view_cjb a" + querry.toString();
					expCloName = new String[]{ Base.YXPZXY_KEY, "רҵ","�༶", "�꼶", "ѧ��", "����", "�Ա�", "ѧ��", "ѧ��", "�γ�����", "�ɼ�","�γ�����", "���Կ�ƽ���ɼ�", "�����ƽ���ɼ�"};
					expType = "self";
				}
			}else if ("xsxx_xscj.do".equalsIgnoreCase(tableName)) {
				
				String bjdm=request.getParameter("bjdm");
				String kcmc=request.getParameter("kcmc");
				String kcmcQuery = "";
				if(kcmc!=null&&!"".equalsIgnoreCase(kcmc)){
					kcmcQuery = " and kcmc = '"+kcmc+"' ";
				}
				sql=" select a.xn,a.xq,(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc, ";
				sql+=" a.xh,a.xm,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc,a.kcmc,a.cj,";
				sql+=" (rank()over(partition by a.kcmc order by to_number(cj) desc nulls last)) pm,a.zpcj1,  ";
				sql+=" (rank()over(partition by a.kcmc order by to_number(zpcj1) desc nulls last)) zppm  ";
				sql+=" from( ";
		        sql+=" select a.xh,a.xm,a.bjdm,c.kcmc,c.cj,c.zpcj1,b.xn,b.xq from "+Base.xsxxb+" a left join  ";
		        sql+=" (select xh,kcmc,xn,xq from cjb a where   ";	
		        sql+=" exists (select 1  from  "+Base.xsxxb+" b  ";
				sql+=" where a.xh = b.xh  ";
				sql+=" and b.bjdm ='"+bjdm+"')and xn='"+xn+"' and xq='"+xq+"' group by xh,xn,xq,kcmc  ";
		        sql+=" )b on a.xh = b.xh left join  ";
		        sql+=" (select * from cjb a where 	";
		        sql+=" exists (select 1  from  "+Base.xsxxb+" b  ";
		        sql+=" where a.xh = b.xh  ";
				sql+=" and b.bjdm ='"+bjdm+"')and xn='"+xn+"' and xq='"+xq+"')c on a.xh=c.xh and b.kcmc=c.kcmc ";
				sql+=" )a,view_njxyzybj_all b where a.bjdm=b.bjdm ";
				sql+=" and a.bjdm='"+bjdm+"' "+kcmcQuery+" order by kcmc,pm,zppm ";
				Base.YXPZXY_KEY = message.getMessage("lable.xb");
				expCloName = new String[] { "ѧ��", "ѧ��", "ѧ�ڴ���", "ѧ��","����",
						"�꼶", Base.YXPZXY_KEY+"����", Base.YXPZXY_KEY, "רҵ����","רҵ","�༶����","�༶",
						"�γ�����", "�γ̳ɼ�","����","�����ɼ�","��������"};
				expType = "self";
				
			}else if ("view_cjb".equalsIgnoreCase(tableName)) {
				sql = "select xymc,zymc,bjmc,nj,xh,xm,xb,xn,xq,kcmc,cj,kcxz from (" + sql +")";
				if (Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)) {
					sql = "select xymc,zymc,bjmc,nj,xh,xm,xb,xn,xq,kcmc,cj,kcxz,(select round(avg(cj),2) from view_zhhcjb b where khfs like '����%' and not exists (select 1 from tykjxrwb b where a.xn=b.xn and a.xq=b.xq and a.xkkh=b.xkkh and rownum=1) and a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) kscj,(select round(avg(cj),2) from cjb b where khfs like '����%' and not exists (select 1 from tykjxrwb b where a.xn=b.xn and a.xq=b.xq and a.xkkh=b.xkkh and rownum=1) and a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) kccj from view_cjb a" + querry.toString();
					expCloName = new String[]{ Base.YXPZXY_KEY, "רҵ","�༶", "�꼶", "ѧ��", "����", "�Ա�", "ѧ��", "ѧ��", "�γ�����", "�ɼ�","�γ�����", "���Կ�ƽ���ɼ�", "�����ƽ���ɼ�"};
					expType = "self";
				}
			} else if ("czxx_zycjb".equalsIgnoreCase(tableName)) {
				DyjcfDAO myDao = new DyjcfDAO();
				HashMap<String, String> rs = myDao.queryDycjBl();
				StringBuffer querySql = new StringBuffer("select xymc,xydm,zymc,zydm,bjmc,bjdm,xh,xm,xb,nj,")
                .append("xn,xq,")
                .append("nvl(case when instr(to_char(kscj),'.',1,1")
                .append(")=1 then '0'||to_char(kscj) else to_char(")
                .append("kscj) end, '0') kscj,nvl(case when instr")
                .append("(to_char(kccj),'.',1,1)=1 then '0'||to_char")
                .append("(kccj) else to_char(kccj) end, '0') kccj,")
                .append("nvl(case when instr(to_char(zyfjf),'.',1,")
                .append("1)=1 then '0'||to_char(zyfjf) else to_char")
                .append("(zyfjf) end, '0') zyfjf,nvl(case when ")
                .append("instr(to_char(cj),'.',1,1)=1 then '0'||")
                .append("to_char(cj) else to_char(cj) end, '0') cj")
                .append(" from (select a.*,round((nvl(kscj")
                .append(",0)*"+rs.get("zykskmcjbl")+"/100 + nvl(kccj,0)*"+rs.get("zykckmcjbl")+"/100 + nvl(zyfjf")
                .append(",0)*"+rs.get("zyfjfbl")+"/100),2) cj from (select a.*,(select ")
                .append("round(avg(cj),2) from view_zhhcjb b where khfs like")
                .append(" '����%' and kcmc not like '%����%' and ")
                .append("a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) kscj,")
                .append("(select round(avg(cj),2) from view_zhhcjb b where ")
                .append("khfs like '����%' and kcmc not like '%����%'")
                .append(" and a.xh=b.xh and a.xn=b.xn and a.xq=b.xq)")
                .append(" kccj,(select sum(nvl(lb,'')||nvl(fs,'0'))")
                .append(" zf from czxx_zyfjfb b where a.xh=b.xh and")
                .append(" a.xn=b.xn and a.xq=b.xq) zyfjf from (select")
                .append(" xh,xm,xb,nj,xydm,zydm,bjdm,xymc,zymc,bjmc")
                .append(",'"+xn+"' xn,'"+xq+"' xq from view_xsjbxx) a) a) a");
				sql = querySql.toString() + querry.toString();
				if ("true".equalsIgnoreCase(isFdyStr)) {
					sql = "select a.* from ("+sql +") a"+" where exists(select 1 from view_fdybbj c "
					+ "where a.bjdm=c.bjdm and c.zgh='"
					+ userName
					+ "')";
				}
				
				expCloName = new String[]{Base.YXPZXY_KEY, Base.YXPZXY_KEY+"����","רҵ", "רҵ����", "�༶", "�༶����", "ѧ��", "����", "�Ա�","�꼶", "ѧ��","ѧ��", "���Կ�Ŀƽ���ɼ�",
						"�����Ŀƽ���ɼ�", "�������ӷ�", "�����ܳɼ�" };
				expType = "self";
			} else if ("czxx_tjkcjb".equalsIgnoreCase(tableName)) {
				String existsSql = GlobalsVariable.XTDM_JWFLAG_GD.equalsIgnoreCase(Base.getJwflag()) ? " and exists (select 1 from tykjxrwb c where a.xkkh=c.xkkh))" : " and exists (select 1 from tykjxrwb c where a.kcdm=c.xkkh))";
				sql = "select xymc,xydm,zymc,zydm,bjmc,bjdm,xh,xm,xb,nj,xn,xq,kcmc,kcxz,cj from (select a.xh,a.xn,a.xq,a.kcmc,a.kcxz,a.cj,b.xm,b.xb,b.xydm,b.nj,b.zydm,b.bjdm,b.xymc,b.zymc,b.bjmc from cjb a,view_xsjbxx b where a.xh=b.xh " +existsSql+ querry.toString();
				if ("true".equalsIgnoreCase(isFdyStr)) {
					sql = "select a.* from ("+sql +") a"+" where exists(select 1 from view_fdybbj c "
					+ "where a.bjdm=c.bjdm and c.zgh='"
					+ userName
					+ "')";
				}
				expCloName = new String[]{ Base.YXPZXY_KEY, Base.YXPZXY_KEY+"����","רҵ", "רҵ����", "�༶", "�༶����", "ѧ��", "����", "�Ա�","�꼶", "ѧ��","ѧ��","�γ�����","�γ�����","�ɼ�" };
				expType = "self";
			} else if ("czxx_tycjb".equalsIgnoreCase(tableName)) {
				DyjcfDAO myDAO  = new DyjcfDAO();
				HashMap<String, String> rs = myDAO.queryDycjBl();
//				��ѯ���������ɼ���Ϣ
				StringBuffer query_gdtycjxx = new StringBuffer("select a.xn,a.xq,a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.tycj,a.dlf,a.fjf,round(((nvl(tycj,0)*"+rs.get("tyc")+"/100) + (nvl(dlf,0)*"+rs.get("tykwtydlbj")+"/100) + (nvl(fjf,0)*"+rs.get("tyfjfbl")+"/100)),2) zf from (")
				                    .append("select a.xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xn,xq,")
				                    .append("(select avg(cj) from view_zhhcjb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and exists (select 1 from tykjxrwb d where b.xkkh=d.xkkh and rownum=1)) tycj,")
				                    .append("(select fs from czxx_tydlcjb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) dlf,")
				                    .append("(select sum(lb||fs) from czxx_tyfjfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) fjf")
				                    .append(" from (select xh,xm,xb,nj,xydm,zydm,bjdm,xymc,zymc,bjmc,'"+xn+"' xn,'"+xq+"' xq from view_xsjbxx) a) a");
				//��ѯѧ�������ɼ���Ϣ
				StringBuffer query_xftycjxx = new StringBuffer("select a.xn,a.xq,a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.tycj,a.dlf,a.fjf,round(((nvl(tycj,0)*"+rs.get("tyc")+"/100) + (nvl(dlf,0)*"+rs.get("tykwtydlbj")+"/100) + (nvl(fjf,0)*"+rs.get("tyfjfbl")+"/100)),2) zf from (")
                .append("select a.xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xn,xq,")
                .append("(select avg(cj) from view_zhhcjb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and exists (select 1 from tykjxrwb d where b.kcdm=d.xkkh and rownum=1)) tycj,")
                .append("(select fs from czxx_tydlcjb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) dlf,")
                .append("(select sum(lb||fs) from czxx_tyfjfb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) fjf")
                .append(" from (select xh,xm,xb,nj,xydm,zydm,bjdm,xymc,zymc,bjmc,'"+xn+"' xn,'"+xq+"' xq from view_xsjbxx) a) a");
				
				if ("true".equalsIgnoreCase(isFdyStr)) {
					sql = "select a.* from ("+sql +") a"+" where exists(select 1 from view_fdybbj c "
					+ "where a.bjdm=c.bjdm and c.zgh='"
					+ userName
					+ "')";
				}
				sql = GlobalsVariable.XTDM_JWFLAG_GD.equalsIgnoreCase(Base.getJwflag()) ? query_gdtycjxx.toString() : query_xftycjxx.toString();
				sql += querry.toString();
				expCloName = new String[]{ "ѧ��", "ѧ��" ,"ѧ��", "����", "�Ա�","�꼶",Base.YXPZXY_KEY,"רҵ","�༶", "�����γɼ�","�������������ɼ�","�������ӷֳɼ�","�����ܳɼ�"};
				expType = "self";
			}else if ("view_XG_BKS_XSCJFMD".equalsIgnoreCase(tableName)) {
				String pkValue = request.getParameter("pkValue");
				String[] pk = pkValue.split("!!@@!!");
				StringBuffer sb = new StringBuffer();
				sb.append(" and(");
				for (int i = 0; i < pk.length; i++) {
					if (pk[i] != null && pk[i].length() > 9) {
						pk[i] = pk[i].substring(9, pk[i].length());
					}
					if (i == 0) {
						sb.append(" xh = '" + pk[i] + "' ");
					} else {
						sb.append(" or xh = '" + pk[i] + "' ");
					}
				}
				sb.append(" )");
				sql += tj;
			} else if("view_scwjxx".equalsIgnoreCase(tableName)){
				sql = "select * from view_scwjxx where 1=1 ";
				if(!"".equalsIgnoreCase(wjm)&& wjm!=null){
					sql+=" and  wjm= "+wjm;
				}
				sql+=" and wjjszdm like '%"+userName+"%' ";
			} else if ("view_zjlg_dypsf".equalsIgnoreCase(tableName)) {
				sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.xn,a.zwpyf,a.bjpyf,a.xyfjf,a.xysh from ("+sql +") a";
				if ("true".equalsIgnoreCase(isFdyStr)) {
					sql +=" where exists(select 1 from view_fdybbj c ";
					sql	+= "where a.bjdm=c.bjdm and c.zgh='";
					sql	+= userName;
					sql	+= "')";
				}
			} else if ("view_zjlg_dywsf".equalsIgnoreCase(tableName)) {
				sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.xn,a.qsf,(case when a.iszds='checked' then '��' else '��' end) iszds,a.xyfjf,a.xysh from ("+sql +") a";
				if ("true".equalsIgnoreCase(isFdyStr)) {
					sql += " where exists(select 1 from view_fdybbj c ";
					sql += "where a.bjdm=c.bjdm and c.zgh='";
					sql += userName;
					sql += "')";
				}
			} else if ("view_zjlg_dykqf".equalsIgnoreCase(tableName)) {
				sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.xn,a.kqf from ("+sql +") a";
				if ("true".equalsIgnoreCase(isFdyStr)) {
					sql += " where exists(select 1 from view_fdybbj c ";
					sql += "where a.bjdm=c.bjdm and c.zgh='";
					sql += userName;
					sql += "')";
				}
			} else if ("view_zjlg_dyf".equalsIgnoreCase(tableName)) {
				StringBuilder sqlStr = new StringBuilder("select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.xn,a.psfzf,a.wszpf,a.iszds,a.kqzpf,a.zf from (select a.*,(kqzpf+wszpf+psfzf) zf from (select a.*,(case when kqfbl = '0' then nvl(kqf,0) when to_number(kqfbl)>to_number(nvl(kqf,0)) then nvl(kqf,0) else nvl(kqfbl,0) end) kqzpf,")
						.append("(case when wszf = '0' then nvl(wszf,0) when to_number(wsfbl)>to_number(wszf) then nvl(wszf,0) else to_number(nvl(wsfbl,0)) end) wszpf,")
						.append("(case when psfbl = '0' then (zpzf+bpzf+xyfjf) when to_number(psfbl)>to_number(zpzf+bpzf+xyfjf) then (zpzf+bpzf+xyfjf) else to_number(psfbl) end) psfzf from (")
						.append("select a.*,(qsf+wsxyfjf) wszf,(case when isbl='1' then (round(zwpyf*zpfbl/100,2)) else to_number(zwpyf) end) zpzf,")
						.append("(case when isbl='1' then (round(bjpyf*bjfbl/100,2)) else to_number(bjpyf) end) bpzf from (")
						.append("select a.*,(select kqf from zjlg_dykqf b where a.xh=b.xh and a.xn=b.xn) kqf,")
						.append("(select case when iszds='checked' then '��' else '��' end from zjlg_dywsf b where a.xh=b.xh and a.xn=b.xn and xysh='ͨ��') iszds,")
						.append("(select nvl(qsf,0) from zjlg_dywsf b where a.xh=b.xh and a.xn=b.xn and xysh='ͨ��') qsf,")
						.append("(select nvl(xyfjf,0) from zjlg_dywsf b where a.xh=b.xh and a.xn=b.xn and xysh='ͨ��') wsxyfjf,")
						.append("nvl(b.zwpyf,0) zwpyf,nvl(b.bjpyf,0) bjpyf,nvl(b.xyfjf,0) xyfjf,b.isbl,nvl(b.zpfbl,0) zpfbl,nvl(b.bjfbl,0) bjfbl,nvl(b.psfbl,0) psfbl,nvl(b.wsfbl,0) wsfbl,nvl(b.kqfbl,0) kqfbl from (")
						.append("select a.xh,a.xm,a.xb,a.nj,a.xydm,a.zydm,a.bjdm,a.xymc,a.zymc,a.bjmc,'"+xn+"' xn from view_xsjbxx a")
						.append(") a left join view_zjlg_dypszf b on a.xh=b.xh and a.xn=b.xn and b.xysh='ͨ��'")
						.append(") a) a) a");
				sqlStr.append(querry.toString());
				sqlStr.append(") a");
				if ("true".equalsIgnoreCase(isFdyStr)) {
					sqlStr.append(" where exists(select 1 from view_fdybbj c ");
					sqlStr.append("where a.bjdm=c.bjdm and c.zgh='");
					sqlStr.append(userName);
					sqlStr.append("')");
				}
				expCloName = new String[] { "ѧ��", "����", "�Ա�", "�꼶", Base.YXPZXY_KEY, "רҵ",
						"�༶", "ѧ��", "ƽʱ���ܷ�", "�������ܷ�", "�Ƿ��߶���", "���ڷ��ܷ�", "�����ܷ�" };
				expType = "self";
				sql = sqlStr.toString();
			} else if ("view_tzcgcjxx".equalsIgnoreCase(tableName)
					&& Globals.XXDM_LZZYJSXY.equalsIgnoreCase(xxdm)) {
				// ����ְҵ �ڶ�����

				StringBuilder sqlStr = new StringBuilder();
				sqlStr.append("select a.*,nvl(case when instr(to_char(b.bxnzxf),'.',1,1)=1  ");
				sqlStr.append("then '0'||to_char(b.bxnzxf) else to_char(b.bxnzxf) end, '0') bxnzxf ");
				sqlStr.append("from (select a.* from view_tzcgcjxx a ");
				sqlStr.append(querry);
				sqlStr.append(") a ");
				sqlStr.append("left join (select b.xh, b.xn, sum(b.xf) bxnzxf ");
				sqlStr.append("from view_tzcgcjxx b group by b.xh, b.xn) b ");
				sqlStr.append("on a.xh = b.xh and a.xn = b.xn  ");
				sql = sqlStr.toString();

				expType = "self";

				expCloName = new String[] { "����ID", "��ĿID", "ѧ��", "����", "�Ա�",
						"�꼶", Base.YXPZXY_KEY+"����", Base.YXPZXY_KEY, "רҵ����", "רҵ", "�༶����", "�༶", "ѧ��",
						"ѧ��", "ѧ������", "��Ŀ����", "��Ŀ����", "��Ŀ����", "��Ŀ����", "����ID",
						"������", "����ѧ��", "�����ɫ", "��֯��λ", "��Ŀ�걨��", "����ʱ��",
						"�Ƿ��ӡ", "��ѧ���ܷ�", "��ѧ���ܷ�" };
			} else if ("xsh_xcxx".equalsIgnoreCase(tableName)) {
				expCloName = new String[] { "��������","�����ص�","����ʱ��","�����ں�","��ע" };
				expType = "self";
				sql="select a.xczt,a.xcdd,a.xcsj,a.xckh,a.bz from xsh_xcxx a";
			} else if ("xsh_hdxx".equalsIgnoreCase(tableName)) {
				expCloName = new String[] { "�����","�ʱ��","��ص�","�����","������" };
				expType = "self";
				sql="select a.hdzt,a.hdsj,a.hddd,a.hdnr,a.fqr from xsh_hdxx a";
			} else if ("xsh_stglb".equalsIgnoreCase(tableName)) {
				expCloName = new String[] { "��������","��������","���Ŵ���","��ʼ��","����ʱ��","������ּ","���ʽ","ָ����ʦ","������","���Ż�ص�" };
				expType = "self";
				sql="select a.stmc,a.stxz,a.bmdm,a.stcsr,a.stclsj,a.stzz,a.hdxs,a.zdls,a.fzr,a.sthddd from xsh_stglb a where a.stmc||a.stxz||a.bmdm<>'ѧ����ѧУ��'";
			} else if ("view_wjcf_xsssxx".equalsIgnoreCase(tableName) && Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
				expCloName = new String[] {"ίԱ������","ίԱ����������","ѧ��","����","�Ա�","�꼶","ѧ��",Base.YXPZXY_KEY+"����",Base.YXPZXY_KEY+"����","רҵ����","רҵ����","�༶����","�༶����","����ʱ��",
						"�������","���Ĵ���������","���Ĵ����������","��ϵ�绰","�ʱ�","��ϵ��ַ","����ԭ��","����ʱ��",
						"�����ĺ�v����ĺ�","���ʱ��","���","ѧ��","��������","��������","���Ĵ���Ҫ��","�������","��������","����֪ͨ��","��������","�������","�������","����֪ͨ��",
						"�ļ��ϴ�·��","Ŀǰ״̬","����Ա���","����Ա���","��ע"};
				expType = "self";
				sql="select wyhsl,wyhsllr,xh,xm,xb,nj,xn,xydm,xymc,zydm,zymc,bjdm,bjmc,sssj,cflbmc,ggcflbdm,ggcflbmc,lxdh,yb,dz,cfyymc,cfsj,cfwh,jdwh,jdsj,nd,xq,tlly,jdly,yq,slqk,bz,slrq,sltzs,fcrq,csqk,fcqk,fctzs,wjsclj,mqzt,fdysh,fdyyj from view_wjcf_xsssxx";
			} else if ("view_wjcf_xsssxx".equalsIgnoreCase(tableName)) {
				expCloName = new String[] {"ѧ��","����","�Ա�","�꼶","ѧ��","ѧ��",Base.YXPZXY_KEY+"����",Base.YXPZXY_KEY+"����","רҵ����","רҵ����","�༶����","�༶����","����ʱ��",
						"�������","����ԭ��","�����ĺ�","����ʱ��","��ϵ�绰","�ʱ�","��ϵ��ַ",
						"���ߴ����ĺ�","���ߴ���ʱ��","���Ĵ���Ҫ��","���","�������"};
				expType = "self";
				sql="select xh,xm,xb,nj,xn,xq,xydm,xymc,zydm,zymc,bjdm,bjmc,sssj,cflbmc,cfyymc,cfsj,cfwh,lxdh,yb,dz,jdwh,jdsj,yq,jd,jdly from view_wjcf_xsssxx";
				querry.append((Base.isNull(xn)?"" : "and xn='" + xn+"'"));
				querry.append((Base.isNull(nd)?"" : "and nd='" + nd+"'"));
				if ("xy".equalsIgnoreCase(user.getUserType())) {
					querry.append(" and xydm = '" + user.getUserDep() + "'");
				}
				sql += querry;
			}else if ("jswmhdsqb".equalsIgnoreCase(tableName)){
				expCloName = new String[] { "�����", "���쵥λ", "��ص�", "���ʼ����",
						"���ʼʱ��", "���������", "�����ʱ��", "����������", "��������ϵ��ʽ",
						"����������", "��������ϵ��ʽ", "�μ�����", "�����", "�����˵�½��", "����������",
						"Ժϵ���", "ѧУ���", "����ʱ��", };
				expType = "self";
				
				querry = new StringBuffer(" where 1=1 ");
		    	String hdmc   = request.getParameter("hdmc");
		    	String hdnr   =request.getParameter("hdnr");
		    	String zzdw   =request.getParameter("zzdw");
		     	String hdksrq = request.getParameter("hdksrq");
		    	String hdjsrq = request.getParameter("hdjsrq");

		    	querry.append((Base.isNull(hdmc)?"":" and hdmc like '%"+hdmc+"%' "));
		    	querry.append((Base.isNull(hdnr)?"":" and hdnr like '%"+hdnr+"%' "));
		    	querry.append((Base.isNull(zzdw)?"":" and zzdw like '%"+zzdw+"%' "));
		    	querry.append((Base.isNull(nd)?"":" and hdksrq like '"+nd+"%' "));
		    	if(!Base.isNull(hdksrq)&&!Base.isNull(hdjsrq)){
		    		querry.append(" and hdksrq like '"+hdksrq+"%' ");
		    	    querry.append(" and hdjsrq like '"+hdjsrq+"%' ");
		    	}else if(!Base.isNull(hdksrq)){
		    		querry.append(" and hdksrq like '"+hdksrq+"%' ");
		    	}else if(!Base.isNull(hdjsrq)){
		    		querry.append(" and hdjsrq like '"+hdjsrq+"%' ");
		    	}
		    	    	
		       	querry.append(" and xysh='ͨ��' and xxsh='ͨ��' ");
		    	querry.append(" order by sqsj desc");
		    	
		    	sql = " select HDMC,ZBDW,HDDD,HDKSRQ,HDKSSJ,HDJSRQ,"
						+ "HDJSSJ,FZRXM,FZRLXFS,JSRXM,JSRLXFS,CJRS,"
						+ "HDNR,SQRDLM,SQRXM,XYSH,XXSH,SQSJ from jswmhdsqb ";
		    	sql+=querry.toString();
		    	
		    	colList = new String[]{"rid","hdmc","zbdw","hddd","hdksrq","hdjsrq","hdnr"};
			}else if("view_fdyxxbAllinfo".equalsIgnoreCase(tableName)){
				String xsgzyjfx = request.getParameter("xsgzyjfx");//ѧ�������о�����
				String zwm = request.getParameter("zxm");
				if(StringUtils.isNotNull(xsgzyjfx)){
					querry.append(" and xsgzyjfx like '%").append(xsgzyjfx).append("%'");
				}
				if(StringUtils.isNotNull(zwm)){
					querry.append(" and zwmc=(select zwmc from zwb where zwdm='").append(zwm).append("')");
				}
				
				sql = "select * from view_fdyxxbAllinfo ";
				sql+=querry.toString();
			}else if("view_csmz_cgsbxx".equalsIgnoreCase(tableName)){
				String xydm=request.getParameter("xydm");
				if(StringUtils.isNotNull(xydm)){
					querry=new StringBuffer();
					querry.append(" and xydm='"+xydm+"' ");
				}
				sql = " select xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xn,xq,xmmc,kmmc,xmjb,jxm," +
						"xf,jxlb,zzdw,zbsj,xmsbr,xqmc,sqbm,xmms,sqbmmc,cyjs,sbsj,fdysh,bmsh,xysh,xxsh," +
						"kmdm,cgms,sfdy,xmsbrmc from view_csmz_cgsbxx ";
				sql+=querry.toString();
			}else if("view_tzcgcjxx".equalsIgnoreCase(tableName)){
				sql = " select xmid,xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xn,xq,xqmc,xmmc,kmmc,kmdm," +
						"xmjb,jxlb,jxm,xf,cyjs,zzdw,xmsbr,zbsj,sfdy,bxqzxf from view_tzcgcjxx  where 1=1";
				if("xy".equalsIgnoreCase(userType)){
					sql+=" and xydm= '"+userDep+"'";
				}
			}else if("view_gygl_xswzxx".equalsIgnoreCase(tableName)){
				sql = "select rownum ���,xymc,bjmc,xm,wzyy,wzdz,sjhm,jzsfty,bz from view_gygl_xswzxx a ";
				
				if(session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")){
					//����Ա��¼
					querry.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh='" + userName + "')");
				}
				
				sql += querry.toString();
				
				String wzlxdm = request.getParameter("wzlxdm");
				if(StringUtils.isNotNull(xn)){
					head = xn+"ѧ��";
				}
				
				if(StringUtils.isNotNull(xq)){
					String xqmc = "";
					if("01".equalsIgnoreCase(xq)){
						xqmc = "һ ";
					}else {
						xqmc = "��";
					}
					
					head += "��" + xqmc + "ѧ��"; 
				}
				
				head += "������Ϣ���ܱ�";
				
				if(StringUtils.isNotNull(wzlxdm)){
					sql += " and wzlxdm='" + wzlxdm + "'";
				}
				
				if(StringUtils.isNotNull(kssj)){
					sql += " and wzksrq>=" + kssj;
				}
				
				if(StringUtils.isNotNull(jssj)){
					sql += " and wzksrq<=" + jssj;
				}
				
			}else if("view_rcsw_swffrqwh".equalsIgnoreCase(tableName)){
				StringBuilder ffsb=new StringBuilder();
				ffsb.append(" where 1=1 ");
				if(!"".equalsIgnoreCase(xhzgh)&& xhzgh!=null){
					ffsb.append(" and  zgh like '%"+xhzgh+"%'");
				}
				if(!"".equalsIgnoreCase(xm)&& xm!=null){
					ffsb.append(" and  xm like '%"+xm+"%'");
				}
				if(!"".equalsIgnoreCase(kssj)&& kssj!=null){
					ffsb.append(" and  ffsj >= '"+kssj+"' ");
				}
				if(!"".equalsIgnoreCase(jssj)&& jssj!=null){
					ffsb.append(" and  ffsj <= '"+jssj+"' ");
				}
				if(!"".equalsIgnoreCase(xn)&& xn!=null){
					ffsb.append(" and  xn= '"+xn+"' ");
				}
				if(!"".equalsIgnoreCase(xq)&& xq!=null){
					ffsb.append(" and  xq= '"+xq+"' ");
				}
				if(!"".equalsIgnoreCase(nd)&& nd!=null){
					ffsb.append(" and  nd= '"+nd+"' ");
				}
				if(!"".equalsIgnoreCase(xmlx)&& xmlx!=null){
					ffsb.append(" and  xmlx= '"+xmlx+"' ");
				}
				sql=" select * from "+tableName+ffsb;
			}else if("fzjy_fdyltb".equalsIgnoreCase(tableName)){
				StringBuilder query=new StringBuilder();
				query.append(" where 1=1 ");
				if(!"".equalsIgnoreCase(kssj)&& kssj!=null){
					query.append(" and  ffsj=> '"+kssj+"' ");
				}
				if(!"".equalsIgnoreCase(jssj)&& jssj!=null){
					query.append(" and  ffsj <= '"+jssj+"' ");
				}
				if(!"".equalsIgnoreCase(zt)&& zt!=null){
					query.append(" and  zt like '%"+zt+"%'");
				}
				sql=" select * from "+tableName +query;
			}else if("view_yxdxsxsltb".equalsIgnoreCase(tableName)){
				StringBuilder query=new StringBuilder();
				query.append(" where 1=1 ");
				if(!"".equalsIgnoreCase(kssj)&& kssj!=null){
					query.append(" and  ffsj=> '"+kssj+"' ");
				}
				if(!"".equalsIgnoreCase(jssj)&& jssj!=null){
					query.append(" and  ffsj <= '"+jssj+"' ");
				}
				if(!"".equalsIgnoreCase(zt)&& zt!=null){
					query.append(" and  zt like '%"+zt+"%'");
				}
				sql=" select * from "+tableName +query;
			}else if("fzjy_xsrxjyb".equalsIgnoreCase(tableName)){
				StringBuilder query=new StringBuilder();
				query.append(" where 1=1 ");
				if(!"".equalsIgnoreCase(kssj)&& kssj!=null){
					query.append(" and  ffsj=> '"+kssj+"' ");
				}
				if(!"".equalsIgnoreCase(jssj)&& jssj!=null){
					query.append(" and  ffsj <= '"+jssj+"' ");
				}
				if(!"".equalsIgnoreCase(zt)&& zt!=null){
					query.append(" and  zt like '%"+zt+"%'");
				}
				sql=" select * from "+tableName +query;
			}else if("fzjy_hyjtb".equalsIgnoreCase(tableName)){
				StringBuilder query=new StringBuilder();
				query.append(" where 1=1 ");
				if(!"".equalsIgnoreCase(kssj)&& kssj!=null){
					query.append(" and  ffsj=> '"+kssj+"' ");
				}
				if(!"".equalsIgnoreCase(jssj)&& jssj!=null){
					query.append(" and  ffsj <= '"+jssj+"' ");
				}
				if(!"".equalsIgnoreCase(zt)&& zt!=null){
					query.append(" and  zt like '%"+zt+"%'");
				}
				sql=" select * from "+tableName +query;
			}else if("view_fzjyycjyb".equalsIgnoreCase(tableName)){
				String xydm=request.getParameter("xyV");
				String zydm=request.getParameter("zydm");
				String bjdm=request.getParameter("bjdm");
				//Ӣ�Ź���
				StringBuilder query=new StringBuilder();
				query.append(" where 1=1 ");
				if(!"".equalsIgnoreCase(xn)&& xn!=null){
					query.append(" and  xn= '"+xn+"' ");
				}
				if(!"".equalsIgnoreCase(nd)&& nd!=null){
					query.append(" and  nd= '"+nd+"' ");
				}
				if(!"".equalsIgnoreCase(nj)&& nj!=null){
					query.append(" and  nj= '"+nj+"' ");
				}
				if(!"".equalsIgnoreCase(xydm)&& xydm!=null){
					query.append(" and  xydm= '"+xydm+"' ");
				}
				if(!"".equalsIgnoreCase(zydm)&& zydm!=null){
					query.append(" and  zydm ='"+zydm+"'");
				}
				if(!"".equalsIgnoreCase(bjdm)&& bjdm!=null){
					query.append(" and  bjdm = '"+bjdm+"'");
				}
				if(!"".equalsIgnoreCase(xysh)&& xysh!=null){
					query.append(" and  xysh ='"+xysh+"'");
				}
				if(!"".equalsIgnoreCase(xxsh)&& xxsh!=null){
					query.append(" and  xxsh = '"+xxsh+"'");
				}
				if(!"".equalsIgnoreCase(xm)&& xm!=null){
					query.append(" and  xm like '%"+xm+"%'");
				}
				sql=" select * from "+tableName +query;
			}else if("view_fzjyjzfdyb".equalsIgnoreCase(tableName)){
				String xydm=request.getParameter("xyV");
				String zydm=request.getParameter("zydm");
				String bjdm=request.getParameter("bjdm");
				//��ְ����Ա   
				StringBuilder query=new StringBuilder();
				query.append(" where 1=1 ");
				if(!"".equalsIgnoreCase(xn)&& xn!=null){
					query.append(" and  xn= '"+xn+"' ");
				}
				if(!"".equalsIgnoreCase(nd)&& nd!=null){
					query.append(" and  nd= '"+nd+"' ");
				}
				if(!"".equalsIgnoreCase(nj)&& nj!=null){
					query.append(" and  nj= '"+nj+"' ");
				}
				if(!"".equalsIgnoreCase(xydm)&& xydm!=null){
					query.append(" and  xydm= '"+xydm+"' ");
				}
				if(!"".equalsIgnoreCase(zydm)&& zydm!=null){
					query.append(" and  zydm ='"+zydm+"'");
				}
				if(!"".equalsIgnoreCase(bjdm)&& bjdm!=null){
					query.append(" and  bjdm = '"+bjdm+"'");
				}
				if(!"".equalsIgnoreCase(xysh)&& xysh!=null){
					query.append(" and  xysh ='"+xysh+"'");
				}
				if(!"".equalsIgnoreCase(xxsh)&& xxsh!=null){
					query.append(" and  xxsh = '"+xxsh+"'");
				}
				if(!"".equalsIgnoreCase(xm)&& xm!=null){
					query.append(" and  xm like '%"+xm+"%'");
				}
				sql=" select * from "+tableName +query;
			}else if("view_fzjyxsjlsbgr".equalsIgnoreCase(tableName)){
				String xydm=request.getParameter("xyV");
				String zydm=request.getParameter("zydm");
				String bjdm=request.getParameter("bjdm");
				//ѧ�����������걨
				StringBuilder query=new StringBuilder();
				query.append(" where 1=1 ");
				if(!"".equalsIgnoreCase(xn)&& xn!=null){
					query.append(" and  xn= '"+xn+"' ");
				}
				if(!"".equalsIgnoreCase(nd)&& nd!=null){
					query.append(" and  nd= '"+nd+"' ");
				}
				if(!"".equalsIgnoreCase(nj)&& nj!=null){
					query.append(" and  nj= '"+nj+"' ");
				}
				if(!"".equalsIgnoreCase(xydm)&& xydm!=null){
					query.append(" and  xydm= '"+xydm+"' ");
				}
				if(!"".equalsIgnoreCase(zydm)&& zydm!=null){
					query.append(" and  zydm ='"+zydm+"'");
				}
				if(!"".equalsIgnoreCase(bjdm)&& bjdm!=null){
					query.append(" and  bjdm = '"+bjdm+"'");
				}
				if(!"".equalsIgnoreCase(xysh)&& xysh!=null){
					query.append(" and  xysh ='"+xysh+"'");
				}
				if(!"".equalsIgnoreCase(xxsh)&& xxsh!=null){
					query.append(" and  xxsh = '"+xxsh+"'");
				}
				if(!"".equalsIgnoreCase(xm)&& xm!=null){
					query.append(" and  xm like '%"+xm+"%'");
				}
				sql=" select * from "+tableName +query;
			}else if("view_wspyxjgr".equalsIgnoreCase(tableName)){
				String xydm=request.getParameter("xyV");
				String zydm=request.getParameter("zydm");
				String bjdm=request.getParameter("bjdm");
				//���������Ƚ�����
				StringBuilder query=new StringBuilder();
				query.append(" where 1=1 ");
				if(!"".equalsIgnoreCase(xn)&& xn!=null){
					query.append(" and  xn= '"+xn+"' ");
				}
				if(!"".equalsIgnoreCase(nd)&& nd!=null){
					query.append(" and  nd= '"+nd+"' ");
				}
				if(!"".equalsIgnoreCase(nj)&& nj!=null){
					query.append(" and  nj= '"+nj+"' ");
				}
				if(!"".equalsIgnoreCase(xydm)&& xydm!=null){
					query.append(" and  xydm= '"+xydm+"' ");
				}
				if(!"".equalsIgnoreCase(zydm)&& zydm!=null){
					query.append(" and  zydm ='"+zydm+"'");
				}
				if(!"".equalsIgnoreCase(bjdm)&& bjdm!=null){
					query.append(" and  bjdm = '"+bjdm+"'");
				}
				if(!"".equalsIgnoreCase(xysh)&& xysh!=null){
					query.append(" and  xysh ='"+xysh+"'");
				}
				if(!"".equalsIgnoreCase(xxsh)&& xxsh!=null){
					query.append(" and  xxsh = '"+xxsh+"'");
				}
				if(!"".equalsIgnoreCase(xm)&& xm!=null){
					query.append(" and  xm like '%"+xm+"%'");
				}
				sql=" select * from "+tableName +query;
			}else if("view_xhbb".equalsIgnoreCase(tableName)){
				sql += user.getQueryCondition();
			} else if ("view_wjcf_cxcf".equalsIgnoreCase(tableName)) {
				sql = "select xh,xm,xymc,zymc,bjmc,xn,xq,cflbmc,cfyymc,cxsqsj,bz,jcwh,jcsj,xxsh,xxyj from " + tableName + querry;
			}
			sql = dataService.appendXfjsCondition(dataSearchForm,tableName,sql);
			System.out.println(sql +":�������");
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			if("self".equals(expType)){
				Excel2Oracle.exportData(sql, expCloName, response.getOutputStream(), "���ݵ���");
			}else{
				if (tableName == null) {
					Excel2Oracle.exportData(sql, realTable, response.getOutputStream());
				} else if(StringUtils.isNotNull(head)){
					Excel2Oracle.exportData(sql, tableName, head, response.getOutputStream());
				} else {
					Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
				}
			}	
		}

		return mapping.findForward("success");
	}


	@SuppressWarnings("unchecked")
	private ActionForward expData2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DAO dao = DAO.getInstance();
		List<Object> rs = new ArrayList<Object>();
		String colListS = DealString.toGBK(request.getParameter("colListS"));
		String rsExpString = DealString.toGBK(request
				.getParameter("rsExpString"));
		String titName = request.getParameter("tableName");

		String[] colList = colListS.split("##OneSpile##");
		String[] rsTemp = rsExpString.split("##TwoSpile##");
		for (int i = 0; i < rsTemp.length; i++) {
			String[] rsColTemp = rsTemp[i].split("##OneSpile##");
			rs.add(rsColTemp);
		}

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		String[] colListCN = dao.getColumnNameCN(colList, titName);
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());

		return mapping.findForward("success");
	}

	private ActionForward modiStuInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
//		WebServiceClientForXmlgxy wsXmlgxy = new WebServiceClientForXmlgxy();
		String userName = session.getAttribute("userName").toString();// �û���
		String userOnLine = session.getAttribute("userOnLine").toString();
		String act = request.getParameter("act");
		String userType = session.getAttribute("userType").toString();// �û����ͼ��ѧԺ�û�
		String dyym = "modi_stu_info.do";
		String xxdm = StandardOperation.getXxdm();
		String writeAble = "";
		writeAble = CheckPower.checkUsrPageAccessPower(userOnLine, userName,
				dyym) ? "yes" : "no";
		String sql = "";

		// ѧ����½
		if ("stu".equalsIgnoreCase(userType)) {
			HashMap<String, String> map = new HashMap<String, String>();
			// �㽭����ʱ�޸�
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)) {
				request.setAttribute("gwcxview", "1");
				sql = "select * from xsxxxgsjb where kssj<to_char(sysdate,'yyyy-mm-ddhh24miss') and jssj>to_char(sysdate,'yyyy-mm-ddhh24miss')";
				String tag = dao.returntag(sql, new String[] {});
				if ("empty".equalsIgnoreCase(tag) || tag == null
						|| "".equalsIgnoreCase(tag)) {
					request.setAttribute("sqsjFlag", "1");
				}
			}
			// �б���ѧ��ʱ�޸�
			else if (xxdm.equalsIgnoreCase(Globals.XXDM_ZBDX)) {
				request.setAttribute("gwcxview", "1");// ����б���ѧ
				sql = "select * from xsxxxgsjb where kssj<to_char(sysdate,'yyyy-mm-ddhh24miss') and jssj>to_char(sysdate,'yyyy-mm-ddhh24miss')";
				String tag = dao.returntag(sql, new String[] {});
				if ("empty".equalsIgnoreCase(tag) || tag == null
						|| "".equalsIgnoreCase(tag)) {
					request.setAttribute("sqsjFlag", "1");
				}
			}

			// �б���ѧ
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZBDX)) {
				// �б���ѧ��ʾ��Ϣ
				if (request.getAttribute("sqsjFlag") == null) {
					sql = "select * from xsfzxxb where 1=2";
					String[] outString = dao.getColumnName(sql);
					String[] outValue = new String[] {};

					if ((act != null) && act.equalsIgnoreCase("save")) {
						boolean flag = false;
						outValue = new String[outString.length + 1];
						sql = "update xsfzxxb set ";
						// ѭ�����sql���
						for (int j = 0; j < outString.length; j++) {
							outValue[j] = request.getParameter(outString[j]
							                                             .toLowerCase());
							if (outValue[j] == null) {
								outValue[j] = "";
							}
							outValue[j] = DealString.toGBK(outValue[j]);
							if (outString[j].equalsIgnoreCase("XH")) {
								outValue[j] = userName;
							}
							sql += (outString[j] + "=?,");
						}// end for
						sql = sql.substring(0, sql.length() - 1);
						sql += " where xh=?";
						outValue[outValue.length - 1] = userName;
						flag = dao.runUpdate(sql, outValue);

						// д��־
						String[] colList = dao
						.getColumnName("select * from xsfzxxb");
						String[] oldvalues = dao.getOneRs(
								"select * from xsfzxxb where xh='" + userName
								+ "'", new String[] {}, colList);
						dao.writeLog(sql, outValue, oldvalues, "�޸ļ�ͥ��Ϣ",
								request);

						// ���ֻ����룬��ϵ�绰��email��Ϣ���浽xsxxb�У�ȡ����ʱ����xsxxb��ȡ,bks_xstxxx��Ԥ���Ľӿڱ�

						// ס����Ϣ�޸�
						String ssbh = DealString.toGBK(request
								.getParameter("ssbh"));
						String qsdh = DealString.toGBK(request
								.getParameter("qsdh"));
						List zsxxList = dao.getList(
								"select xh from xszsxxb where xh=?",
								new String[] { userName },
								new String[] { "xh" });
						if (zsxxList != null && zsxxList.size() > 0) {
							flag = StandardOperation.update("xszsxxb",
									new String[] { "ssbh" },
									new String[] { ssbh }, "xh", userName,
									request);
						} else {
							flag = StandardOperation.insert("xszsxxb",
									new String[] { "xh", "ssbh", "cwh" },
									new String[] { userName, ssbh, "0" },
									request);
						}

						// ������Ϣ(ϵͳ�е�������Ϣ������ssxxb���У�zxbz_xsssxx����Ԥ���Ľӿڱ�)
						List ssxxList = dao.getList(
								"select ssbh from zxbz_xsssxx where ssbh=?",
								new String[] { ssbh }, new String[] { "ssbh" });
						if (ssxxList != null && ssxxList.size() > 0) {
							flag = StandardOperation.update("zxbz_xsssxx",
									new String[] { "qsdh" },
									new String[] { qsdh }, "ssbh", ssbh,
									request);
						} else {
							flag = StandardOperation.insert("zxbz_xsssxx",
									new String[] { "ssbh", "qsdh" },
									new String[] { ssbh, qsdh }, request);
						}
						request.setAttribute("result", flag);
					}// end if(save)

					// ������Ϣ��ѯ
					sql = "select * from xsfzxxb where xh=?";
					outValue = dao.getOneRs(sql, new String[] { userName },
							outString);
					if (outValue == null) {
						outValue = new String[outString.length];
					}
					for (int i = 0; i < outValue.length; i++) {
						map.put(outString[i].toLowerCase(), outValue[i]);
					}
					String ssbh = "";
					sql = "select ssbh from xszsxxb where xh=?";
					outValue = dao.getOneRs(sql, new String[] { userName },
							new String[] { "ssbh" });
					if (outValue == null) {
						map.put("ssbh", "");
					} else {
						ssbh = outValue[0];
						map.put("ssbh", ssbh);
					}

					String qsdh = "";
					sql = "select qsdh from zxbz_xsssxx where ssbh=?";
					outValue = dao.getOneRs(sql, new String[] { ssbh },
							new String[] { "qsdh" });
					if (outValue == null) {
						map.put("qsdh", "");
					} else {
						qsdh = outValue[0];
						map.put("qsdh", qsdh);
					}

					// ѧ��������Ϣ��ѯ
					sql = "select * from view_xsjbxx where xh=?";
					outString = new String[] { "xm", "zymc", "xymc", "nj",
					"bjmc" };
					outValue = dao.getOneRs(sql, new String[] { userName },
							outString);
					if (outValue == null) {
						outValue = new String[outString.length];
					}
					for (int i = 0; i < outValue.length; i++) {
						map.put(outString[i], outValue[i]);
					}
					request.setAttribute("rs", map);
					request.setAttribute("writeAble", writeAble);
					return mapping.findForward("zbdx_xsxx_modi");
				}// end if(�б���ѧ��ʾ��Ϣ)
				request.setAttribute("writeAble", writeAble);
				return mapping.findForward("zbdx_xsxx_modi");
			}// end if(�б���ѧ)

			// ����ʱ
			if (request.getAttribute("sqsjFlag") == null) {
				sql = "select * from xsfzxxb where 1=2";
				String[] outString = dao.getColumnName(sql);
				String[] outValue = new String[] {};
				if ((act != null) && act.equalsIgnoreCase("save")) {
					boolean flag = false;
					outValue = new String[outString.length + 1];
					// ���sql���
					sql = "update xsfzxxb set ";
					for (int j = 0; j < outString.length; j++) {
						outValue[j] = request.getParameter(outString[j]
						                                             .toLowerCase());
						if (outValue[j] == null) {
							outValue[j] = "";
						}
						outValue[j] = DealString.toGBK(outValue[j]);
						if (outString[j].equalsIgnoreCase("XH")) {
							outValue[j] = userName;
						}
						sql += (outString[j] + "=?,");
					}// end for
					sql = sql.substring(0, sql.length() - 1);
					sql += " where xh=?";
					outValue[outValue.length - 1] = userName;
					flag = dao.runUpdate(sql, outValue);

					// �޸�ͨѶ��Ϣ��xsxxb
					String sjhm = request.getParameter("sjhm");
					String jtdz = DealString.toGBK(request
							.getParameter("jtszd"));
					String dzyx = request.getParameter("email");
					String lxdh = request.getParameter("lxdh1");
					flag = StandardOperation.update("xsxxb", new String[] {
							"jtdz", "dzyx", "lxdh", "sjhm" }, new String[] {
							jtdz, dzyx, lxdh, sjhm }, "xh", userName, request);

					// д��־
					String[] colList = dao
					.getColumnName("select * from xsfzxxb");
					String[] oldVals = dao
					.getOneRs("select * from xsfzxxb where xh='"
							+ userName + "'", new String[] {}, colList);
					dao.writeLog(sql, outValue, oldVals, "�޸�ѧ����ͥ��Ϣ", request);

					request.setAttribute("result", flag);					
				}// end save

				// ��Ϣ��ѯ
				sql = "select * from xsfzxxb where xh=?";
				outValue = dao.getOneRs(sql, new String[] { userName },
						outString);
				if (outValue == null) {
					outValue = new String[outString.length];
				}
				for (int i = 0; i < outValue.length; i++) {
					map.put(outString[i].toLowerCase(), outValue[i]);
				}
				sql = "select * from view_xsjbxx where xh=?";
				outString = new String[] { "xm", "zymc", "xymc", "nj", "bjmc",
						"xb", "lxdh", "sjhm", "lxdzxx" };

				outValue = dao.getOneRs(sql, new String[] { userName },
						outString);
				if (outValue == null) {
					outValue = new String[outString.length];
				}
				for (int i = 0; i < outValue.length; i++) {
					map.put(outString[i].toLowerCase(), outValue[i]);
				}
				if (xxdm.equalsIgnoreCase(Globals.XXDM_DBLYDX)) {
					//������ҵ��ѧ
					map.put("email",dao.getOneRs(
							"select getmes(a.xh,'dzyx',(select b.lxdzxx from bks_xstxxx b where a.xh=b.xh)) email from xsxxb a where a.xh=?",
							new String[] { userName },"email"));
					map.put("sjhm",dao.getOneRs(
							"select getmes(a.xh,'sjhm',(select b.sjhm from bks_xstxxx b where a.xh=b.xh)) sjhm from xsxxb a where a.xh=?",
							new String[] { userName },"sjhm"));
					map.put("lxdh1",dao.getOneRs(
							"select getmes(a.xh,'lxdh',(select b.lxdh from bks_xstxxx b where a.xh=b.xh)) lxdh from xsxxb a where a.xh=?",
							new String[] { userName },"lxdh"));
				}
				request.setAttribute("xxdm", xxdm);
				request.setAttribute("rs", map);
				request.setAttribute("writeAble", writeAble);
				
				return mapping.findForward("success");
			}
		}// end if(����ʱ)
		else if ("xy".equalsIgnoreCase(userType)) {
			// String xxdm = StandardOperation.getXxdm();
			String xh = request.getParameter("xh");
			HashMap<String, String> map = new HashMap<String, String>();
			if (null != xh && "" != xh) {
				if (xxdm.equalsIgnoreCase(Globals.XXDM_ZBDX)) {
					// �б���ѧ					
					sql = "select * from xsfzxxb where 1=2";
					String[] outString = dao.getColumnName(sql);
					String[] outValue = new String[] {};
					// ��Ϣ����
					if ((act != null) && act.equalsIgnoreCase("save")) {
						boolean flag = false;
						outValue = new String[outString.length + 1];
						sql = "update xsfzxxb set ";
						for (int j = 0; j < outString.length; j++) {
							outValue[j] = request.getParameter(outString[j].toLowerCase());
							if (outValue[j] == null) {
								outValue[j] = "";
							}
							outValue[j] = DealString.toGBK(outValue[j]);
							if (outString[j].equalsIgnoreCase("XH")) {
								outValue[j] = xh;
							}
							sql += (outString[j] + "=?,");
						}// end for
						sql = sql.substring(0, sql.length() - 1);
						sql += " where xh=?";
						outValue[outValue.length - 1] = xh;
						flag = dao.runUpdate(sql, outValue);
						// д��־
						String[] colList_xsfz = dao
						.getColumnName("select * from xsfzxxb");
						String[] oldVals_xsfz = dao.getOneRs(
								"select * from xsfzxxb where xh='" + userName
								+ "'", new String[] {}, colList_xsfz);
						dao.writeLog(sql, outValue, oldVals_xsfz, "�޸�ѧ����ͥ��Ϣ",
								request);

						String lxdzxx = DealString.toGBK(request
								.getParameter("email"));
						String lxdh = DealString.toGBK(request
								.getParameter("lxdh1"));
						String sjhm = DealString.toGBK(request
								.getParameter("sjhm"));
						flag = StandardOperation.update("bks_xstxxx",
								new String[] { "lxdzxx", "lxdh", "sjhm" },
								new String[] { lxdzxx, lxdh, sjhm }, "xh", xh,
								request);
						String ssbh = DealString.toGBK(request
								.getParameter("ssbh"));
						String qsdh = DealString.toGBK(request
								.getParameter("qsdh"));
						// ѧ��ס����Ϣ
						List zsxxList = dao.getList(
								"select xh from xszsxxb where xh=?",
								new String[] { xh }, new String[] { "xh" });
						if (zsxxList != null && zsxxList.size() > 0) {
							flag = StandardOperation.update("xszsxxb",
									new String[] { "ssbh" },
									new String[] { ssbh }, "xh", xh, request);
						} else {
							flag = StandardOperation.insert("xszsxxb",
									new String[] { "xh", "ssbh", "cwh" },
									new String[] { xh, ssbh, "0" }, request);
						}
						// ������Ϣ
						List ssxxList = dao.getList(
								"select xh from ssxxb where ssbh=?",
								new String[] { ssbh }, new String[] { "ssbh" });
						if (ssxxList != null && ssxxList.size() > 0) {
							flag = StandardOperation.update("ssxxb",
									new String[] { "qsdh" },
									new String[] { qsdh }, "ssbh", ssbh,
									request);
						} else {
							flag = StandardOperation.insert("ssxxb",
									new String[] { "ssbh", "qsdh", "cwh" },
									new String[] { ssbh, qsdh, "-" }, request);
						}

						// ������ҵ����ѧ��������Ϣ
						if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLYDX)) {
							String[] outStr = new String[] { "xh", "xm", "syd",
									"csrq", "sfzh", "xb", "mz", "zzmm", "cym",
									"sg", "tz", "tc", "xjlb", "jg", "xszp",
									"xx", "jkzk", "jtdz", "jtdh", "jtyb",
									"whcd", "rxqdw", "jrgqtsj", "rtdd",
									"rtjsr1", "rtjsr2", "jrgcdsj", "rddd",
									"rdjsr1", "rdjsr2", "xxlqzy", "zylqzy",
									"jtcygc", "sfhq", "qjhc", "sfjh", "dfxm",
									"dfzzmm", "dfxzhc", "dfxrhz", "jlcfjl",
									"sjhm", "xxjl_nyr1", "xxjl_nr1",
									"xxjl_zw1", "xxjl_zmr1", "xxjl_nyr2",
									"xxjl_nr2", "xxjl_zw2", "xxjl_zmr2",
									"xxjl_nyr3", "xxjl_nr3", "xxjl_zw3",
									"xxjl_zmr3", "xxjl_nyr4", "xxjl_nr4",
									"xxjl_zw4", "xxjl_zmr4", "xxjl_nyr5",
									"xxjl_nr5", "xxjl_zw5", "xxjl_zmr5" };
							if (userName == null || userName == "") {
								for (int i = 0; i < outString.length; i++) {
									map.put(outString[i], "");
								}
							}
							String[] outVal = new String[] {};
							outVal = new String[outStr.length + 1];
							if (flag) {
								sql = "update xsxxb set ";
								for (int j = 0; j < outStr.length; j++) {
									outVal[j] = request.getParameter(outStr[j]
									                                        .toLowerCase());
									if (outVal[j] == null) {
										outVal[j] = "";
									}
									outVal[j] = DealString.toGBK(outVal[j]);
									if (outStr[j].equalsIgnoreCase("XH")) {
										outVal[j] = userName;
									}
									if (j != outStr.length - 1)
										sql += outStr[j] + "=?,";
									else
										sql += outStr[j] + "=? ";
								}
								sql += " where xh=?";
								outVal[outVal.length - 1] = userName;

								flag = dao.runUpdate(sql, outVal);
								// д��־
								String[] colList_xsxx = dao
								.getColumnName("select * from xsxxb");
								String[] oldVals_xsxx = dao.getOneRs(
										"select * from xsxxb where xh='"
										+ userName + "'",
										new String[] {}, colList_xsxx);
								dao.writeLog(sql, outVal, oldVals_xsxx,
										"�޸�ѧ����Ϣ", request);
							}
						}// end if ������ҵ����ѧ����Ϣ
						request.setAttribute("result", flag);
					}// end if(save)
					sql = "select * from xsfzxxb where xh=?";
					outValue = dao.getOneRs(sql, new String[] { xh }, outString);
					if (outValue == null) {
						outValue = new String[outString.length];
					}
					for (int i = 0; i < outValue.length; i++) {
						map.put(outString[i].toLowerCase(), outValue[i]);
					}
					String ssbh = "";
					sql = "select ssbh from xszsxxb where xh=?";
					outValue = dao.getOneRs(sql, new String[] { xh },
							new String[] { "ssbh" });
					if (outValue == null) {
						map.put("ssbh", "");
					} else {
						ssbh = outValue[0];
						map.put("ssbh", ssbh);
					}

					String qsdh = "";
					sql = "select qsdh from ssxxb where ssbh=?";
					outValue = dao.getOneRs(sql, new String[] { ssbh },
							new String[] { "qsdh" });
					if (outValue == null) {
						map.put("qsdh", "");
					} else {
						qsdh = outValue[0];
						map.put("qsdh", qsdh);
					}

					sql = "select * from view_xsjbxx where xh=?";
					outString = new String[] { "xm", "zymc", "xymc", "nj",
					"bjmc" };
					if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLYDX)) {
						sql = "select * from xsxxb where xh=?";
						outString = dao.getColumnName("select * from xsxxb where 1=2");
					}
					outValue = dao
					.getOneRs(sql, new String[] { xh }, outString);
					if (outValue == null) {
						outValue = new String[outString.length];
					}
					for (int i = 0; i < outValue.length; i++) {
						map.put(outString[i], outValue[i]);
					}
					request.setAttribute("rs1", map);
				}// end if(xxdm)
			}// end if(xh=null)
			else {
				request.setAttribute("rs1", map);
			}
			// �б���ѧreturn
			if (xxdm.equalsIgnoreCase(Globals.XXDM_ZBDX)) {
				request.setAttribute("writeAble", writeAble);
				return mapping.findForward("zbdx_xsxx_modi");
			}

			sql = "select * from xsfzxxb where 1=2";
			String[] outString = dao.getColumnName(sql);
			if (xh == null || xh == "") {
				for (int i = 0; i < outString.length; i++) {
					map.put(outString[i], "");
				}
			}
			String[] outValue = new String[] {};
			// String[] outStr = null;
			// String[] outVal = null;
			if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLYDX)) {
				sql = "select * from xsxxb where 1=2";
				// outStr = dao.getColumnName(sql);
				if (xh == null || xh == "") {
					for (int i = 0; i < outString.length; i++) {
						map.put(outString[i], "");
					}
				}
				// outVal = new String[] {};
			}
			if ((act != null) && act.equalsIgnoreCase("save")) {
				// String xh = request.getParameter("xh");
				outValue = new String[outString.length + 1];
				sql = "update xsfzxxb set ";
				for (int j = 0; j < outString.length; j++) {
					outValue[j] = request.getParameter(outString[j]
					                                             .toLowerCase());
					if (outValue[j] == null) {
						outValue[j] = "";
					}
					outValue[j] = DealString.toGBK(outValue[j]);
					if (outString[j].equalsIgnoreCase("XH")) {
						outValue[j] = xh;
					}
					sql += (outString[j] + "=?,");
				}
				sql = sql.substring(0, sql.length() - 1);
				sql += " where xh=?";
				outValue[outValue.length - 1] = xh;
				dao.runUpdate(sql, outValue);
				// д��־
				String[] colList_xsfzxx = dao
				.getColumnName("select * from xsfzxxb");
				String[] oldVals_xsfzxx = dao.getOneRs(
						"select * from xsfzxxb where xh='" + userName + "'",
						new String[] {}, colList_xsfzxx);
				dao
				.writeLog(sql, outValue, oldVals_xsfzxx, "�޸�ѧ����ͥ��Ϣ",
						request);
			}// end save
			sql = "select * from xsfzxxb where xh=?";
			outValue = dao.getOneRs(sql, new String[] { xh }, outString);
			if (outValue == null) {
				outValue = new String[outString.length];
			}
			for (int i = 0; i < outValue.length; i++) {
				map.put(outString[i].toLowerCase(), outValue[i]);
			}
			sql = "select * from view_xsjbxx where xh=?";
			if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLYDX)) {
				sql = "select * from xsxxb where xh=?";
			}
			outString = new String[] { "xm", "zymc", "xymc", "nj", "bjmc" };
			outValue = dao.getOneRs(sql, new String[] { xh }, outString);
			if (outValue == null) {
				outValue = new String[outString.length];
			}
			for (int i = 0; i < outValue.length; i++) {
				map.put(outString[i], outValue[i]);
			}
			request.setAttribute("writeAble", writeAble);
			request.setAttribute("rs1", map);
			return mapping.findForward("success");
		}
		request.setAttribute("writeAble", writeAble);
		return mapping.findForward("success");
	}

	private ActionForward createTestPaper(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "";
		String sjlsh = request.getParameter("sjlsh");
		sql = "select sjlsh,sjm,sjsm,sjxd from sjb where sjlsh=?";
		String[] tmp = dao.getOneRs(sql, new String[] { sjlsh }, new String[] {
				"sjlsh", "sjm", "sjsm", "sjxd" });
		map.put("sjlsh", tmp[0]);
		map.put("sjm", tmp[1]);
		map.put("sjsm", tmp[2]);
		map.put("sjxd", tmp[3]);
		request.setAttribute("sjinfo", map);
		sql = "select count(*) flTotal,stlxmc from view_sjst where sjlsh=? group by stlxdm,stlxmc order by stlxdm";
		List sjinfoList = dao.getList(sql, new String[] { sjlsh },
				new String[] { "flTotal", "stlxmc" });
		request.setAttribute("sjinfoList", sjinfoList);
		sql = "select count(*) total from view_sjst where sjlsh=?";
		String[] tmp1 = dao.getOneRs(sql, new String[] { sjlsh },
				new String[] { "total" });
		request.setAttribute("total", tmp1[0]);
		sql = "select stlsh,stxh,stnr,stlxmc,stndjbmc,stfz from view_sjst where sjlsh=? order by stxh";
		List stList = dao.getList(sql, new String[] { sjlsh }, new String[] {
				"stlsh", "stxh", "stnr", "stlxmc", "stndjbmc", "stfz" });
		request.setAttribute("stList", stList);
		sql = "select stlsh,stlxdm,xxxh,xxnr,xxfz from view_sjsc where sjlsh=? order by stlsh,xxxh";
		List xxList = dao.getList(sql, new String[] { sjlsh }, new String[] {
				"stlsh", "stlxdm", "xxxh", "xxnr", "xxfz" });
		String xxStr = dao.listToString(xxList, new String[] { "stlsh",
				"stlxdm", "xxxh", "xxnr", "xxfz" });
		request.setAttribute("xxStr", xxStr);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy#MM#dd#HH#mm#ss#SSS");
		String dtkssj = sf.format(new Date());
		request.setAttribute("dtkssj", dtkssj);

		return mapping.findForward("success");
	}

	private ActionForward impDataCommit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		DAO dao = DAO.getInstance();
		String sql = "";
		String tableName = request.getParameter("tableName");
		if (tableName.equalsIgnoreCase("xsxxb")
				|| tableName.equalsIgnoreCase("zhszcp")) {
			return null;
		}
		String tableNameTmp = tableName + "_tmp_" + userName;
		sql = "truncate table " + tableName;
		dao.runUpdate(sql, new String[] {});
		sql = "insert into " + tableName + " select * from " + tableNameTmp;
		dao.runUpdate(sql, new String[] {});
		sql = "drop table " + tableNameTmp;
		dao.runUpdate(sql, new String[] {});
		return null;
	}

	private ActionForward submitTestPaper(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// CommanForm dataSearchForm = (CommanForm) form;
		DAO dao = DAO.getInstance();
		String sql = "";
		New_Random_ID newId = new New_Random_ID();
		// Timestamp date = new Timestamp(System.currentTimeMillis());
		// String dtsj = date.toString().substring(0, 19).replaceAll("-",
		// "").replaceAll(" ", "").replaceAll(":", "");
		HttpSession session = request.getSession();
		String xh = session.getAttribute("userName").toString();
		// String xh = "3020221007";
		String xxStr = DealString.toGBK(request.getParameter("xxStr"));
		String sjlsh = request.getParameter("sjlsh");
		String dtkssj = request.getParameter("dtkssj");
		String tt[] = dtkssj.split("#");
		int year = Integer.parseInt(tt[0]);
		int month = Integer.parseInt(tt[1]) - 1;
		int day = Integer.parseInt(tt[2]);
		int hour = Integer.parseInt(tt[3]);
		int minute = Integer.parseInt(tt[4]);
		int second = Integer.parseInt(tt[5]);
		// int milli = Integer.parseInt(tt[6]);
		Calendar kssj = Calendar.getInstance();
		kssj.set(year, month, day, hour, minute, second);
		Calendar now = Calendar.getInstance();
		long t1 = kssj.getTimeInMillis();
		long t2 = now.getTimeInMillis();
		long time = t2 - t1;
		long tmilli = time % 1000;
		long tsecond = ((time - tmilli) / 1000) % 60;
		long tminute = (time - tmilli - tsecond * 1000) / 1000 / 60 % 60;
		long thour = (time - tmilli - tsecond * 1000 - tminute * 1000 * 60)
		/ 1000 / 60 / 60 % 24;
		long tday = (time - tmilli - tsecond * 1000 - tminute * 1000 * 60 - thour * 1000 * 60 * 60)
		/ 1000 / 60 / 60 / 24;
		String dtys = tday + "��" + thour + "Сʱ" + tminute + "��" + tsecond + "��";
		String selectedStr[] = xxStr.split("!!SplitSignOne!!");
		int len = selectedStr.length;
		String selectedStr1[][] = new String[len][2];
		String stfz = "";
		String stjffs = "";
		int cj = 0;
//		int dtfz = 0;
		int leng = 0;
		int xxdtfz = 0;
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dtsj = sf.format(new Date());
		for (int i = 0; i < len; i++) {
			selectedStr1[i] = selectedStr[i].split("!!SplitSignTwo!!");
			sql = " select stjffs from stb where stlsh = ? ";
			stjffs = dao.getOneRs(sql, new String[] { selectedStr1[i][0] },
			"stjffs");
			if (stjffs.equalsIgnoreCase("1")) {
				sql = " select t.stfz from stb t where t.stlsh = ? and t.stda = ?";
				if (selectedStr1[i].length > 1) {
					leng = selectedStr1[i].length;
					stfz = dao.getOneRs(sql, new String[] { selectedStr1[i][0],
							selectedStr1[i][1] }, "stfz");
				} else {
					leng = 0;
				}// ��� by lirong
				if (stfz != null && !stfz.equalsIgnoreCase("")){
//					dtfz = Integer.parseInt(stfz);
				} else {
//					dtfz = 0;
				}
			} else if (stjffs.equalsIgnoreCase("0")) {// ����Ƿַ�ʽ
				leng = (selectedStr1[i].length > 1) ? selectedStr1[i][1]
				                                                      .length() : 0;// �޸� by lirong
				                                                      // int leng=selectedStr1[i][1].length();
				                                                      String xxxh = "";
				                                                      String xxfz = "";

				                                                      for (int j = 0; j < leng; j++) {
				                                                    	  xxxh = String.valueOf(selectedStr1[i][1].charAt(j));
				                                                    	  sql = " select xxfz from xxb where xxxh = ? and stlsh = ?";
				                                                    	  xxfz = dao.getOneRs(sql, new String[] { xxxh,
				                                                    			  selectedStr1[i][0] }, "xxfz");
				                                                    	  if (xxfz != null && !xxfz.equalsIgnoreCase(""))
				                                                    		  xxdtfz = Integer.parseInt(xxfz);
				                                                    	  else
				                                                    		  xxdtfz = 0;
				                                                    	  // dtfz += xxdtfz;
				                                                      }
			}
			//xxdtfz = 0;
			cj += xxdtfz;
			//������ѭ���������ݰ�(�����������),��Ϊ����һ�����������,��Ҫ��sql��
			sql = " insert into dtjlb (xh,sjlsh,stlsh,dtda,dtfz,dtsj,xn_id) values (?,?,?,?,?,?,?)";
			if (leng != 0) {
				dao.runUpdate(sql, new String[] { xh, sjlsh,
						selectedStr1[i][0], selectedStr1[i][1],
						String.valueOf(xxdtfz), dtsj,newId.new_xnid("dtjlb") });
			}
		}
		// �����xn_id by lirong
		sql = "insert into cjjlb (xn_id,xh,dtys,sjlsh,cj,dtsj) values (?,?,?,?,?,?)";
		dao.runUpdate(sql, new String[] { newId.new_xnid("cjjlb"), xh, dtys,
				sjlsh, String.valueOf(cj), dtsj });

		HashMap<String, String> map = new HashMap<String, String>();

		sql = " select distinct t.sjm,t.dtys,nvl(t.sjsm,'����') sjsm,t.sjxd,t.xh,t.dtsj,t.cj,nvl(t.bz,'����') bz,t.xm,t.bjmc "
			+ " from view_xsxlcsxx t "
			+ " where t.xh = ? and t.dtsj = ? and t.sjlsh = ? ";
		String[] outValue = dao.getOneRs(sql, new String[] { xh, dtsj, sjlsh },
				new String[] { "sjm", "dtys", "sjsm", "sjxd", "xh", "dtsj",
				"cj", "bz", "xm", "bjmc" });
		if (outValue != null && outValue.length > 0) {
			map.put("sjm", outValue[0]);
			map.put("dtys", outValue[1]);
			map.put("sjsm", outValue[2]);
			map.put("sjxd", outValue[3]);
			map.put("xh", outValue[4]);
			map.put("dtsj", outValue[5]);
			map.put("cj", outValue[6]);
			map.put("bz", outValue[7]);
			map.put("xm", outValue[8]);
			map.put("bjmc", outValue[9]);
		}
		sql = " select count(*) stsl from (select stlsh "
			+ "from view_xsxlcsxx t "
			+ "where t.xh = ? and t.dtsj = ? and t.sjlsh = ?  "
			+ "group by stlsh)";
		String stsl = dao.getOneRs(sql, new String[] { xh, dtsj, sjlsh },
		"stsl");
		map.put("stsl", stsl);

		sql = " select stlxdm,stlxmc,count(stlxdm) lxsl from "
			+ "(select stlsh,stlxdm,stlxmc from view_xsxlcsxx t "
			+ "where t.xh = ? and t.dtsj = ? and t.sjlsh = ? "
			+ "group by stlsh,stlxdm,stlxmc)group by stlxdm,stlxmc";
		List lxList = dao.getList(sql, new String[] { xh, dtsj, sjlsh },
				new String[] { "stlxdm", "stlxmc", "lxsl" });

		// sql = " select
		// stxh,stlsh,stnr,stlxdm,stlxmc,stndjbdm,stndjbmc,nvl(stfz,'')
		// stfz,xxlsh,xxxh,xxnr,nvl(xxfz,'') xxfz " +
		// "from view_xsxlcsxx t where t.xh||t.dtsj||t.sjlsh =
		// '302022101120070524095303101' " +
		// "order by stxh,xxxh";
		// List stxxList = dao.getList(sql, new String[]{},
		// new String[]{
		// "stxh","stlsh","stnr","stlxdm","stlxmc","stndjbdm","stndjbmc","stfz","xxlsh","xxxh","xxnr","xxfz"
		// });
		ArrayList<HashMap<String, List<HashMap<String, String>>>> stxxList = new ArrayList<HashMap<String, List<HashMap<String, String>>>>();
		sql = " select distinct * from (select stxh,stlsh,stnr,stlxdm,stlxmc,stndjbdm,stndjbmc,nvl(stfz,'') stfz,dtda,dtfz,stda,stdajs "
			+ "from view_xsxlcsxx t "
			+ "where t.xh = ? and t.dtsj = ? and t.sjlsh = ?  "
			+ "order by stxh desc,xxxh desc) order by stxh ";
		String[] stListStr = new String[] { "stxh", "stlsh", "stnr",
				"stlxdm", "stlxmc", "stndjbdm", "stndjbmc", "stfz", "dtda",
				"dtfz", "stda", "stdajs" };
		List<HashMap<String, String>> stList = dao.getList(sql, new String[] {
				xh, dtsj, sjlsh }, stListStr);
		int size = stList.size();
		for (int i = 0; i < size; i++) {
			HashMap<String, String> mapt = new HashMap<String, String>();
			HashMap<String, List<HashMap<String, String>>> mapte = new HashMap<String, List<HashMap<String, String>>>();
			mapt = stList.get(i);
			String stlsht = (String) mapt.get("stlsh");
			sql = " select stlsh,xxlsh,xxxh,xxnr,nvl(xxfz,'') xxfz "
				+ "from view_xsxlcsxx t where t.xh = ? and t.dtsj = ? and t.sjlsh = ? "
				+ "and stlsh = ? order by stxh,xxxh ";
			List<HashMap<String, String>> xxList = dao.getList(sql,
					new String[] { xh, dtsj, sjlsh, stlsht }, new String[] {
					"stlsh", "xxlsh", "xxxh", "xxnr", "xxfz" });
			//����stList������
//			List<HashMap<String, String>> xyList = new ArrayList<HashMap<String, String>>();
//			for(int k=0;k<stListStr.length;k++){
//			HashMap<String, String> tempMap = new HashMap<String, String>();
//			tempMap.put(stListStr[k], (stList.get(i).get(stListStr[k]) != null) ? stList.get(i).get(stListStr[k]).toString() : "");
//			xyList.add(tempMap);
//			}
//			mapte.put("xyList", xyList);
			mapte.put("xxList", xxList);
			//mapte.put("xyList", stList.get(i));
			stxxList.add(mapte);
		}
		request.setAttribute("rs", map);
		request.setAttribute("lxList", lxList);
		request.setAttribute("stxxList", stxxList);
		request.setAttribute("stList", stList);
		return mapping.findForward("success");
	}

	private ActionForward stuArchivesInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		HashMap<String, String> rs = new HashMap<String, String>();
		String[] colList, colList1 = null; // /colList���ڴ洢ѧ�����ѧ����Ϣ���ֶΣ�colList1���ڱ���ѧ�����Ϣ�������ֶ�
		String[] colListCN = null;
		colList = new String[] { "xh", "xm", "xb", "xydm", "xymc", "nj",
				"bjmc", "zydm", "zymc", "bjdm", "bjmc", "drzw", "hdcs", "hjcs",
		"jg" };
		List topTr = null;// �����ͷ��Ϣ
		String xh = request.getParameter("xh");
		if (!xh.equals(" ") || xh != null) {
			// ���ɲ�ѯѧ�����ѧ����ͼsql
			String sql = "select xh,xm,xb,xydm,xymc,nj,bjmc,zydm,zymc,bjdm,bjmc,drzw,hdcs,hjcs,jg from view_xshdjxjxx where xh='"
				+ xh + "'";
			rs = dao.getRSArray(sql, colList);
			colList1 = new String[] { "hdmc", "hdksrq", "hdjsrq" };// ѧ�����Ϣ����ֶ���Ϣ
			colListCN = dao.getColumnNameCN(colList1, "xshdxxb");// ѧ�����Ϣ����ֶ�������Ϣ
			topTr = dao.arrayToList(colList1, colListCN);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		return mapping.findForward("success");
	}
	private ActionForward xsxxytxxInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String url = "/modiData.do?realTable=xlytqkb&doType=view&tableName=view_xsjbxx&pk=xh&from=xlytqk&xsdy=xsdy&pkValue=";
		String pkva = request.getParameter("pkValue");
		ActionForward newforward = new ActionForward(url+pkva);
		return newforward;
	}

	private ActionForward xsxxzdgzInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String guid = "";
		String url = "/modiData.do?realTable=gzxsjbqkb&doType=view&tableName=view_gzxsjbqkb&pk=id&from=xsjbqk&xsjbxx=xsjbxx&pkValue=";
		String pkva = request.getParameter("pkValue");

		String sql = "select id from gzxsjbqkb where xh=?";
		DAO dao = DAO.getInstance();
		String[] rs1 = dao.getOneRs(sql, new String[] {pkva},new String[] { "id" });
		if(rs1 != null){
			guid = rs1[0];
		}

		ActionForward newforward = new ActionForward(url+guid);
		return newforward;
	}
	private ActionForward stuArchives(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DAO dao = DAO.getInstance();
		CommanForm dataSearchForm = (CommanForm) form;
		Vector<Object> rs = new Vector<Object>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql���
		String querry = " where 1=1 ";// sql����
		String rsNum = "0";// ���صļ�¼��
		String nj = dataSearchForm.getNj();
		String xy = dataSearchForm.getXydm();
		String zy = dataSearchForm.getZydm();
		String bj = dataSearchForm.getBjdm();
		String xh = dataSearchForm.getXh();
		String xm = dataSearchForm.getXm();
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

		colList = new String[] { "�к�", "xh", "xm", "xb", "nj", "bjmc", "ZYMC",
				"DRZW", "HDCS", "hjcs" };
		sql = "select rownum �к�,a.* from view_xshdjxjxx a" + querry;

		colListCN = dao.getColumnNameCN(colList, "view_xshdjxjxx");
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
		request.setAttribute("njList", dao.getNjList());// �����꼶�б�
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", dao.getZyList(xy));// ����רҵ�б�
		request.setAttribute("bjList", dao.getBjList(xy, zy));// ����רҵ�б�
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		return mapping.findForward("success");
	}

	public static String stringToFloat(String str) throws Exception {
		if (StringUtils.isNull(str)) {
			return "0";
		} else {
			if (str.indexOf(".") > 0) {
				return str.substring(0, str.indexOf(".")+3);
			} else {
				return "0";
			}	
		}
	}

	public static List<HashMap<String, String>> arrayToList4(String[] arr1,
			String[] arr2) {
		// ����������ϲ���һ��List�����������Сһ�£�ͨ��Ϊ��Ӣ�Ķ��ա�����Ҫ��Ӣ����ǰ�������ں�
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		int len = (arr1.length > arr2.length) ? arr2.length : arr1.length;
		HashMap<String, String> map = null;
		for (int i = 0; i < len; i++) {
			map = new HashMap<String, String>();
			map.put("bjdm", arr1[i]);
			map.put("bjmc", arr2[i]);
			list.add(map);
		}
		return list;
	}

	/**
	 * ƴ�ӵ�������
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public static String getQuery(String[] tj, String[] tjz) {

		StringBuffer query = new StringBuffer();
		if (tj != null && tj.length > 0 && tjz != null && tjz.length > 0
				&& tj.length == tjz.length) {
			for (int i = 0; i < tjz.length; i++) {
				if (!Base.isNull(tjz[i])) {
					query.append(" and ");
					query.append(tj[i]);
					query.append(" = ");
					query.append(" '");
					query.append(tjz[i]);
					query.append("' ");
				}
			}
		}
		return query.toString();
	}
	/**
	 * ѧ��ס����ϢУ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	private ActionForward xszsxxCheck(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String act=Base.chgNull(request.getParameter("act"), "", 0);
		DAO dao = DAO.getInstance();
		String sql=null;
		if("sshf".equals(act)){//���Ữ����֤
			sql="select a.ssbh,a.xydm,a.xy,a.nj,a.rzrs,nvl(b.cws,'δ����') cws,b.bjdm," +
				"(select qsh from ssxxb x where x.ssbh=a.ssbh) qsh," +
				"(select ldmc from sslddmb x,ssxxb y where x.lddm=y.lddm and y.ssbh=a.ssbh) ldmc," +
				"(case when b.cws is null then '����δ����' else '���Ữ�ֲ���' end) exc_type" +
				" from "+
				"(select a.ssbh,b.xydm,b.xy,b.nj,count(1) rzrs from xszsxxb a,xsxxb b where a.xh=b.xh group by a.ssbh,b.xydm,b.xy,b.nj) a "+
				"left join ssfpb b on a.xydm=b.xydm and a.ssbh=b.ssbh and a.nj=b.nj "+
				"where rzrs>nvl(cws,'0')";
		}else{//�Ա�ʹ�λ����֤
			sql="select a.xh,a.ssbh,a.cwh,a.rzrq,b.xm,b.xb,b.xy,b.zymc,b.bjmc,b.nj,c.qsh,c.sfbz,d.ldmc, "+
				"(case when (a.cwh>c.cws or a.cwh<'1') and b.xb<>d.xbxd then '��λ�Ų��������Ա��Ŵ���' "+ 
				"when a.cwh>c.cws or a.cwh<'1' then '��λ�Ų�����'  "+
				"when b.xb<>d.xbxd then '�Ա��Ŵ���' else 'δ֪' end) exc_type "+ 
				"from xszsxxb a,xsxxb b,ssxxb c,sslddmb d where a.xh=b.xh and a.ssbh=c.ssbh and c.lddm=d.lddm "+
				"and (a.cwh>c.cws or a.cwh<'1' or b.xb<>d.xbxd )";
		}
		List<HashMap<String,String>> list=dao.getListNotOut(sql, new String[]{});
		request.setAttribute("rs", list);
		request.setAttribute("act",act );
		return mapping.findForward("success");
	}
	
	/**
	 * 
	 * @����:ͬ�ô�ѧ�㽭��Ժ���Ի���������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-3-26 ����02:09:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward bbdcExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DataManService service = new DataManService();
		
		CommanForm dataSearchForm = (CommanForm) form;
		CommService comService = new CommService();
		
		comService.getModelValue(dataSearchForm, request);
		
		
		User user = getUser(request);
		

		// ============= ִ�д�ӡ���� ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.bbdcExport(dataSearchForm, response.getOutputStream(),user);
		// ============= end ============

		return null;
	}
	
	
	
}
