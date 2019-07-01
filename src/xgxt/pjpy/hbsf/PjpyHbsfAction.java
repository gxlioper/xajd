
package xgxt.pjpy.hbsf;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.ExcelMethods;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����ʦ��ѧԺ��������Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-10</p>
 */
public class PjpyHbsfAction extends DispatchAction {

	String xydm = "";
	String zydm = "";
	String nj = "";
	
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
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		String userType = request.getSession().getAttribute("userType").toString();
		request.setAttribute("writeAble", "yes");//�ж��û���дȨ
		request.setAttribute("xqList", Base.getXqList());//ѧ���б�
		request.setAttribute("xnList", Base.getXnndList());//ѧ���б�
		request.setAttribute("njList", Base.getNjList());//�꼶�б�
		request.setAttribute("xyList", Base.getXyList());//ѧԺ�б�
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//רҵ�б�
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//�༶�б�
		request.setAttribute("userType", userType);//�û�����
	}
	
	/**
	 * ��ѧ��������ҳ
	 * priseApply ---- ��ѧ������ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward priseApply(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyHbsfActionForm hbsfForm = (PjpyHbsfActionForm) form;
		PjpyHbsfService service = new PjpyHbsfService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();//�û�����
		String userName = session.getAttribute("userName").toString();//�û���
		String xh = request.getParameter("xh");
		userType = !StringUtils.isNull(userType) ? userType : "";
		userName = !StringUtils.isNull(userName) ? userName : "";
		List<HashMap<String, String>> jxjList = service.getJxjList();//��ѧ�б�
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap.put("stuExists", "yes");
		
		//�����ѧ���û����ȡѧ�������Ϣ
		if (StringUtils.isEqual(userType, "stu") || StringUtils.isEqual(userType, "student")) {
			xh = userName;
		}
		stuMap = service.getStuInfo(xh);
		if (stuMap == null) {//û�и�����Ϣ
			stuMap.put("stuExists", "no");
		}
		String jxjdm = hbsfForm.getJxjdm();
		HashMap<String, String> jxjMap = service.getJxjJelb(jxjdm);//��ȡ��ѧ����Ϣ
		if (jxjMap != null) {
			stuMap.put("jxjlb", jxjMap.get("jxjlb"));
			stuMap.put("jlje", jxjMap.get("jlje"));
		}
		HashMap<String, String> jxjSqxnndMap = service.getJxjSqxnnd();
		if (jxjSqxnndMap != null) {
			stuMap.put("xn", jxjSqxnndMap.get("jxjsqxn"));//��ѧ������ѧ��
			stuMap.put("nd", jxjSqxnndMap.get("jxjsqnd"));//��ѧ���������
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("jxjList", jxjList);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("priseapply");
	}
	
	/**
	 * ��ѧ�����뱣��
	 * jxjsqsave ---- ��ѧ�����뱣�� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsqSave(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyHbsfActionForm hbsfForm = (PjpyHbsfActionForm) form;
		PjpyHbsfService service = new PjpyHbsfService();
		String xh = request.getParameter("xh");//ѧ��
		String wysp = DealString.toGBK(request.getParameter("wysp"));//����ˮƽ
		String sjhm = DealString.toGBK(request.getParameter("sjhm"));//�ֻ�����
		String drzw = DealString.toGBK(request.getParameter("drzw"));//����ְ��
		JxjsqSaveModel jxjsqModel = new JxjsqSaveModel();//��ѧ������MODEL
		BeanUtils.copyProperties(jxjsqModel, hbsfForm);
		jxjsqModel.setXh(xh);
		jxjsqModel.setWysp(wysp);
		jxjsqModel.setSjhm(sjhm);
		jxjsqModel.setDrzw(drzw);
		//��������Ƿ��ظ�
		boolean bChk = service.chkDataExists(jxjsqModel.getXn() + jxjsqModel.getNd() + xh + jxjsqModel.getJxjdm());
		if (!bChk) {
			boolean bFlag = service.jxjsqSave(jxjsqModel, request);//���뱣��
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		} else {
			request.setAttribute("inserted", "exists");
		}
		return mapping.findForward("priseapply");
	}
	
	/**
	 * �ۺ����ʲ�����ҳ
	 * zhszcpdefault ---- �ۺ����ʲ���Ĭ�� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpDefault(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("tableName", "view_zhszcp");//��ͼ��
		request.setAttribute("realTable", "zhszcp");//����
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcpdefault");
	}
	
	/**
	 * �ۺ����ʲ�����ѯ
	 * zhszcpQry ---- �ۺ����ʲ�����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpQry(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyHbsfActionForm hbsfForm = (PjpyHbsfActionForm) form;
		PjpyHbsfService service = new PjpyHbsfService();
		ZhszcpQryModel zhszcpModel = new ZhszcpQryModel();//�ۺ����ʲ�����ѯMODEL
		BeanUtils.copyProperties(zhszcpModel, hbsfForm);
		List<HashMap<String, String>> topList = service.getZhszcpTitle();//��ѯ��ͷ
		List<String[]> resList = service.getZhszcpResult(zhszcpModel);//��ѯ���
		
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//��¼��
		request.setAttribute("tableName", "view_zhszcp");//��ͼ��
		request.setAttribute("realTable", "zhszcp");//����
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcpdefault");
	}
	
	/**
	 * �ۺ����ʲ�����������ҳ��
	 * zhszcpadd ---- �ۺ����ʲ������� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpAdd(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyHbsfService service = new PjpyHbsfService();
		String xh = request.getParameter("xh");
		xh = !StringUtils.isNull(xh) ? xh : "";
		HashMap<String, String> resMap = service.getStuInfo(xh);//��ȡѧ�������Ϣ
		if (resMap == null) {
			resMap.put("stuExists", "no");//�����ڸ�ѧ����Ϣ
		}else {
			resMap.put("stuExists", "yes");//���ڸ�ѧ����Ϣ
		}
		request.setAttribute("rs", resMap);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcpadd");
	}
	
	/**
	 * �ۺ����ʲ�����������
	 * zhszcpsave ---- �ۺ����ʲ������� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpSave(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyHbsfActionForm hbsfForm = (PjpyHbsfActionForm) form;
		PjpyHbsfService service = new PjpyHbsfService();
		String xh = request.getParameter("xh");
		xh = !StringUtils.isNull(xh) ? xh : "";
		HashMap<String, String> resMap = service.getStuInfo(xh);//��ȡѧ�������Ϣ
		if (resMap == null) {
			resMap.put("stuExists", "no");//�����ڸ�ѧ����Ϣ
		}else {
			resMap.put("stuExists", "yes");//���ڸ�ѧ����Ϣ
		}
		hbsfForm.setXh(xh);
		ZhszcpSaveModel zhszcpModel = new ZhszcpSaveModel();//�ۺ����ʲ�������
		BeanUtils.copyProperties(zhszcpModel, hbsfForm);
		boolean bFlag = service.zhszcpSave(zhszcpModel, request);//���ݱ���
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		hbsfForm.setCfjz(DealString.toGBK(hbsfForm.getCfjz()));
		hbsfForm.setBz(DealString.toGBK(hbsfForm.getBz()));
		hbsfForm.setCpjg(DealString.toGBK(hbsfForm.getCpjg()));
		request.setAttribute("rs", resMap);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcpadd");
	}
	
	/**
	 * �ۺ����ʲ�������ɾ��
	 * zhszcpdel ---- �ۺ����ʲ�������ɾ�� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpDel(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyHbsfActionForm hbsfForm = (PjpyHbsfActionForm) form;
		PjpyHbsfService service = new PjpyHbsfService();
		String[] keys = hbsfForm.getCbv();
		String sJg = service.zhszcpDel(keys, request);//��Ϣ����ɾ��
		request.setAttribute("result", sJg);
		request.setAttribute("tableName", "view_zhszcp");//��ͼ��
		request.setAttribute("realTable", "zhszcp");//����
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcpdefault");
	}
	
	/**
	 * �ۺ����ʲ���������Ϣ�޸���Ϣ��ʾ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpView(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyHbsfActionForm hbsfForm = (PjpyHbsfActionForm) form;
		PjpyHbsfService service = new PjpyHbsfService();
		String pkValue = request.getParameter("pkValue");
		String type = request.getParameter("type");
		type = !StringUtils.isNull(type) ? type : "";
		HashMap<String, String> resMap = service.getZhszcpInfoByPk(pkValue);//��ȡ�����Ϣ
		if (StringUtils.isEqual(type, "modi")) {//�޸���ʾ
			request.setAttribute("updated", "yes");
		} else {//ֻ��ʾ
			request.setAttribute("updated", "no");
		}
		hbsfForm.setDcj(resMap.get("dcj"));
		hbsfForm.setTcj(resMap.get("tcj"));
		hbsfForm.setZcj(resMap.get("zcj"));
		hbsfForm.setZhszcpzf(resMap.get("zhszcpzf"));
		hbsfForm.setZhcppm(resMap.get("zhcppm"));
		hbsfForm.setXn(resMap.get("xn"));
		hbsfForm.setNd(resMap.get("nd"));
		hbsfForm.setXq(resMap.get("xq"));
		hbsfForm.setBjghkms(resMap.get("bjghkms"));
		hbsfForm.setCfjz(resMap.get("cfjz"));
		hbsfForm.setCpjg(resMap.get("cpjg"));
		hbsfForm.setBz(resMap.get("bz"));
		hbsfForm.setSxddbx(resMap.get("sxddbx"));
		hbsfForm.setSxddf(resMap.get("sxddf"));
		hbsfForm.setSxjkf(resMap.get("sxjkf"));
		hbsfForm.setZzllxx(resMap.get("zzllxx"));
		hbsfForm.setSsjsqk(resMap.get("ssjsqk"));
		hbsfForm.setShsjhd(resMap.get("shsjhd"));
		hbsfForm.setGbrzbx(resMap.get("gbrzbx"));
		hbsfForm.setQttcsj(resMap.get("qttcsj"));
		hbsfForm.setZpf(resMap.get("zpf"));
		hbsfForm.setZytz(resMap.get("zytz"));
		hbsfForm.setYyjn(resMap.get("yyjn"));
		hbsfForm.setJsjjn(resMap.get("jsjjn"));
		hbsfForm.setZyjn(resMap.get("zyjn"));
		hbsfForm.setKxjs(resMap.get("kxjs"));
		hbsfForm.setCxnl(resMap.get("cxnl"));
		hbsfForm.setKcjqpfj(resMap.get("kcjqpfj"));
		hbsfForm.setKcjqpfjpm(resMap.get("kcjqpfjpm"));
		hbsfForm.setTydb(resMap.get("tydb"));
		hbsfForm.setTyhd(resMap.get("tyhd"));
		hbsfForm.setTsqk(resMap.get("tsqk"));
		hbsfForm.setXljkhd(resMap.get("xljkhd"));
		hbsfForm.setXlszzf(resMap.get("xlszzf"));
		hbsfForm.setXlszzk(resMap.get("xlszzk"));
		hbsfForm.setStszzf(resMap.get("stszzf"));
		request.setAttribute("rs", resMap);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcpview");
	}
	
	/**
	 * �ۺ����ʲ������ݵ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpExp(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyHbsfService service = new PjpyHbsfService();
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String bjdm = request.getParameter("bjdm");
		String xn = request.getParameter("xn");
		String modelPath = "";
		if (!StringUtils.isNull(bjdm) && !StringUtils.isNull(xn)) {//�༶��ѧ���ѡ
			modelPath = servlet.getServletContext().getRealPath("")+"/print/zhszcpbyhbsf.xls";
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
			service.printZhszcp(wwb, xydm, zydm, bjdm, xn);//XLS�������
		}
		
		return mapping.findForward("");	
	}
}
