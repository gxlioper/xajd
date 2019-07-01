
package xgxt.pjpy.hzy;

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

import xgxt.DAO.DAO;
import xgxt.DAO.SxjyDAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����ְԺ��������Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-19</p>
 */
public class PjpyHzzyAction extends DispatchAction {

	String xydm = "";
	String zydm = "";
	String nj = "";
	
	CommonAction commonAction = new CommonAction();
	
	/**
	 * �ɼ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cjImp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyHzzyActionForm hzzyForm = (PjpyHzzyActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual(userType, "xy")) {//ѧԺ�û�
			xydm = userDep;
			hzzyForm.setXydm(xydm);
		}
		request.setAttribute("tableName", "view_cjb");//����
		request.setAttribute("realTable", "cjb");
		commonAction.appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("cjimp");
	}
	
	/**
	 * �ɼ���ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cjQry(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyHzzyActionForm hzzyForm = (PjpyHzzyActionForm) form;
		PjpyHzzyService service = new PjpyHzzyService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual(userType, "xy")) {//ѧԺ�û�
			xydm = userDep;
			hzzyForm.setXydm(xydm);
		}
		CjbModel cjbModel = new CjbModel();
		BeanUtils.copyProperties(cjbModel, hzzyForm);
		List<HashMap<String, String>> topList = service.getTableQryTitle("cjb");//��ѯ��ͷ
		List<String[]> resList = service.getCjbQryResult(cjbModel);//��ѯ���
		request.setAttribute("tableName", "view_cjb");//����
		request.setAttribute("realTable", "cjb");
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//��¼��
		commonAction.appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("cjimp");
	}
	
	/**
	 * �ۺ����ʲ�����ҳ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpDefault(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyHzzyActionForm hzzyForm = (PjpyHzzyActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual(userType, "xy")) {//ѧԺ�û�
			xydm = bmdm;
			hzzyForm.setXydm(xydm);
		}
		request.setAttribute("tableName", "view_zhszcp");//��ͼ��
		request.setAttribute("realTable", "zhszcp");//����
		commonAction.appendProperties(request, xydm, zydm, nj);//����ҳ���б�
		return mapping.findForward("zhszcpdefault");
	}
	
	/**
	 * �ۺ����ʲ�����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpQry(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyHzzyActionForm hzzyForm = (PjpyHzzyActionForm) form;
		PjpyHzzyService service = new PjpyHzzyService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual(userType, "xy")) {//ѧԺ�û�
			xydm = bmdm;
			hzzyForm.setXydm(xydm);
		}
		ZhszcpModel zhszcpModel = new ZhszcpModel();
		BeanUtils.copyProperties(zhszcpModel, hzzyForm);
		List<HashMap<String, String>> topList = service.getTableQryTitle("zhszcp");//��ѯ��ͷ
		List<String[]> resList = service.getZhszcpQryResult(zhszcpModel);//��ѯ���
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);//��¼��
		request.setAttribute("tableName", "view_zhszcp");//��ͼ��
		request.setAttribute("realTable", "zhszcp");//����
		commonAction.appendProperties(request, xydm, zydm, nj);//����ҳ���б�
		return mapping.findForward("zhszcpdefault");
	}
	
	/**
	 * �ۺ����ʲ�����������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpAdd(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyHzzyActionForm hzzyForm = (PjpyHzzyActionForm) form;
		PjpyHzzyService service = new PjpyHzzyService();
		String xh = request.getParameter("xh");
		HashMap<String, String> resMap = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			resMap = service.getStuXx(xh);//��ȡѧ���ۺ����ʲ�����
			if (resMap != null) {
				resMap.put("stuExists", "yes");
			} else {
				resMap.put("stuExists", "no");
			}
		}
		if (resMap != null) {
			hzzyForm.setXn(resMap.get("xn"));
			hzzyForm.setNd(resMap.get("nd"));
			hzzyForm.setXq(resMap.get("xq"));
			hzzyForm.setGydykpf(resMap.get("gydykpf"));
			hzzyForm.setXydykpf(resMap.get("xydykpf"));
			hzzyForm.setGzxxcx(resMap.get("gzxxcx"));
			hzzyForm.setTcj(resMap.get("tcj"));
			hzzyForm.setZcj(resMap.get("zcj"));
			hzzyForm.setZhszcpzf(resMap.get("zhszcpzf"));
			hzzyForm.setZhszcpcjpm(resMap.get("zhszcpcjpm"));
			hzzyForm.setBz(resMap.get("bz"));
		}
		request.setAttribute("rs", resMap);
		commonAction.appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zhszcpadd");
	}
	
	/**
	 * �ۺ����ʲ�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpSave(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyHzzyActionForm hzzyForm = (PjpyHzzyActionForm) form;
		PjpyHzzyService service = new PjpyHzzyService();
		hzzyForm.setXh(request.getParameter("xh"));
		ZhszcpModel zhszcpModel = new ZhszcpModel();
		BeanUtils.copyProperties(zhszcpModel, hzzyForm);
		boolean bFlag = service.zhszcpSave(zhszcpModel, request);//������Ϣ
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("rs", new HashMap<String, String>());
		commonAction.appendProperties(request, xydm, zydm, nj);
		hzzyForm.setBz(DealString.toGBK(hzzyForm.getBz()));
		return mapping.findForward("zhszcpadd");
	}
	
	/**
	 * �ۺ����ʲ�������ɾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpDel(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyHzzyActionForm hzzyForm = (PjpyHzzyActionForm) form;
		PjpyHzzyService service = new PjpyHzzyService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String bmdm = session.getAttribute("userDep").toString();
		if (StringUtils.isEqual(userType, "xy")) {//ѧԺ�û�
			xydm = bmdm;
			hzzyForm.setXydm(xydm);
		}
		String sFlag = service.zhszcpDel(hzzyForm.getCbv(), request);//����ɾ��
		if (StringUtils.isNull(sFlag)) {//�ɹ�
			request.setAttribute("deleted", "yes");
		} else {//ʧ��
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", sFlag);//ʧ����Ϣ
		}
		request.setAttribute("tableName", "view_zhszcp");//��ͼ��
		request.setAttribute("realTable", "zhszcp");//����
		commonAction.appendProperties(request, xydm, zydm, nj);//����ҳ���б�
		return mapping.findForward("zhszcpdefault");
	}
	
	/**
	 *  �ۺ����ʲ�����ʾ��ϸ��Ϣ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpView(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyHzzyActionForm hzzyForm = (PjpyHzzyActionForm) form;
		PjpyHzzyService service = new PjpyHzzyService();
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> resMap = service.getZhszcpXx(pkValue);//�ۺ����ʲ����ɼ�
		if (resMap != null) {
			String sfzss = resMap.get("sfzss");
			if (!StringUtils.isNull(sfzss) && StringUtils.isEqual("sfzss", "0")) {
				sfzss = "��";
			} else {
				sfzss = "��";
			}
			resMap.put("sfzss", sfzss);
			hzzyForm.setXn(resMap.get("xn"));
			hzzyForm.setXq(resMap.get("xq"));
			hzzyForm.setNd(resMap.get("nd"));
			hzzyForm.setXydykpf(resMap.get("xydykpf"));
			hzzyForm.setZcj(resMap.get("zcj"));
			hzzyForm.setTcj(resMap.get("tcj"));
			hzzyForm.setGzxxcx(resMap.get("gzxxcx"));
			hzzyForm.setGydykpf(resMap.get("gydykpf"));
			hzzyForm.setZhszcpcjpm(resMap.get("zhszcpcjpm"));
			hzzyForm.setZhszcpzf(resMap.get("zhszcpzf"));
			hzzyForm.setBz(resMap.get("bz"));
		}
		request.setAttribute("rs", resMap);
		request.setAttribute("pkValue", pkValue);
		commonAction.appendProperties(request, xydm, zydm, nj);//����ҳ���б�
		return mapping.findForward("zhszcpview");
	}
	
	/**
	 *  �ۺ����ʲ����޸ı��� 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpModi(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyHzzyActionForm hzzyForm = (PjpyHzzyActionForm) form;
		PjpyHzzyService service = new PjpyHzzyService();
		String pkValue = request.getParameter("pkValue");
		hzzyForm.setXh(request.getParameter("xh"));
		ZhszcpModel zhszcpModel = new ZhszcpModel();
		BeanUtils.copyProperties(zhszcpModel, hzzyForm);
		boolean bFlag = service.zhszcpModi(zhszcpModel, pkValue, request);//�����޸�
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("rs", new HashMap<String, String>());
		hzzyForm.setBz(DealString.toGBK(hzzyForm.getBz()));
		commonAction.appendProperties(request, xydm, zydm, nj);//����ҳ���б�
		return mapping.findForward("zhszcpview");
	}
	
	/**
	 * �����ӡ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hzyrychprint(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyHzzyService service = new PjpyHzzyService();
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> resMap = service.rychPrint(pkValue);
		String fdyshsj = resMap.get("fdyshsj");
		String xyshsj = resMap.get("xyshsj");
		String xxshsj = resMap.get("xxshsj");
		if (!StringUtils.isNull(fdyshsj)) {
			resMap.put("fdyshyear", fdyshsj.substring(0,4));
			resMap.put("fdyshmon", fdyshsj.substring(4,6));
			resMap.put("fdyshdate", fdyshsj.substring(6,8));
		}
		if (!StringUtils.isNull(xyshsj)) {
			resMap.put("xyshyear",xyshsj.substring(0,4));
			resMap.put("xyshmon", xyshsj.substring(4,6));
			resMap.put("xyshdate", xyshsj.substring(6,8));
		}
		if (!StringUtils.isNull(xxshsj)) {
			resMap.put("xxshyear",xxshsj.substring(0,4));
			resMap.put("xxshmon", xxshsj.substring(4,6));
			resMap.put("xxshdate", xxshsj.substring(6,8));
		}
		resMap.put("title", "У��" + (StringUtils.isNull(resMap.get("rychmc")) ? "" : (StringUtils.isEqual(resMap.get("rychmc"), "����ѧ���ɲ�(ϵԺѧ����֯)") ? resMap.get("rychmc").substring(0, resMap.get("rychmc").length() - 8) : resMap.get("rychmc").substring(0, resMap.get("rychmc").length() - 4))) + "�ǼǱ�");
		String xq = resMap.get("xq");
		if (!StringUtils.isNull(xq)) {
			resMap.put("xq", StringUtils.isEqual(xq, "01") ? "�ڶ�ѧ��" : "�ڶ�ѧ��");
		}
		request.setAttribute("rs", resMap);
		return mapping.findForward("hzyrychprint");
	}
	
	public ActionForward hzzyjxjModi(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyHzzyActionForm hzzyForm = (PjpyHzzyActionForm) form;
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rsMap = new HashMap<String, String>();
		String[] opList = new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","xn","nd","xq","zhpfmc","cjmc","pjcj","sjhm","jxjdm","drzw","jfqk","sqly"};
		DAO dao = DAO.getInstance();
		rsMap = dao.getMap("select xh,xm,xb,nj,xymc,zymc,bjmc,xn,nd,xq,zhpfmc,cjmc,pjcj,jfqk,jxjdm,sqly,drzw,sjhm from view_xsjxjb where xn||nd||jxjdm||xh=?", new String[]{pkValue}, opList);
		String xq = rsMap.get("xq");
		xq = StringUtils.isNull(xq) ? "" : xq;
		if (StringUtils.isEqual(xq, "03")) {
			xq = "��һѧ��";
		} else {
			xq = "�ڶ�ѧ��";
		}
		rsMap.put("xq", xq);
		request.setAttribute("rs", rsMap);
		hzzyForm.setJxjdm(rsMap.get("jxjdm"));
		hzzyForm.setCjmc(rsMap.get("cjmc"));
		hzzyForm.setDrzw(rsMap.get("drzw"));
		hzzyForm.setZhpfmc(rsMap.get("zhpfmc"));
		hzzyForm.setPjcj(rsMap.get("pjcj"));
		hzzyForm.setSjhm(rsMap.get("sjhm"));
		hzzyForm.setSqly(rsMap.get("sqly"));
		hzzyForm.setJfqk(rsMap.get("jfqk"));
		
		request.setAttribute("pkValue", pkValue);
		commonAction.appendJxjList(request);
		commonAction.appendProperties(request, xydm, zydm, nj);
		
		return mapping.findForward("jxjmodiview");
	}
	
	public ActionForward hzzyjxjmodiSave(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyHzzyActionForm hzzyForm = (PjpyHzzyActionForm) form;
		String pkValue = request.getParameter("pkValue");
		hzzyForm.setXn(request.getParameter("xn"));
		hzzyForm.setNd(request.getParameter("nd"));
		hzzyForm.setXq(request.getParameter("xq"));
		hzzyForm.setXh(request.getParameter("xh"));
		boolean bFlag = jxjModi(pkValue, hzzyForm, request);
		if (bFlag) {//�ɹ�
			request.setAttribute("inserted", "yes");
		} else {//ʧ��
			request.setAttribute("inserted", "no");
		}
		commonAction.appendJxjList(request);
		commonAction.appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rs", new HashMap());
		hzzyForm.setDrzw("");
		hzzyForm.setSqly("");
		hzzyForm.setJfqk("");
		return mapping.findForward("jxjmodiview");
	}
	
	public ActionForward hzzyrychModi(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		PjpyHzzyActionForm hzzyForm = (PjpyHzzyActionForm) form;
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rsMap = new HashMap<String, String>();
		String[] opList = new String[]{"xh","xm","xb","nj","xymc","zymc","bjmc","xn","nd","xq","zhpfmc","cjmc","sjhm","rychdm","drzw","zzmm","mz","zysj","jtdz"};
		DAO dao = DAO.getInstance();
		rsMap = dao.getMap("select xh,xm,xb,nj,xymc,zymc,bjmc,xn,nd,xq,zhpfmc,cjmc,rychdm,zysj,(select sjhm from xsfzxxb b where b.xh=view_xsrychb.xh) sjhm,(select drzw from xsrychxxb where xsrychxxb.xh=view_xsrychb.xh) drzw,(select zzmm from xsrychxxb where xsrychxxb.xh=view_xsrychb.xh) zzmm,(select mzmc from view_xsxxb d where view_xsrychb.xh=d.xh) mz,(select jtdz from xsrychxxb where xsrychxxb.xh=view_xsrychb.xh) jtdz from view_xsrychb where xn||nd||rychdm||xh=?", new String[]{pkValue}, opList);
		String xq = rsMap.get("xq");
		xq = StringUtils.isNull(xq) ? "" : xq;
		if (StringUtils.isEqual(xq, "03")) {
			xq = "��һѧ��";
		} else {
			xq = "�ڶ�ѧ��";
		}
		rsMap.put("xq", xq);
		request.setAttribute("rs", rsMap);
		
		hzzyForm.setCjmc(rsMap.get("cjmc"));
		hzzyForm.setDrzw(rsMap.get("drzw"));
		hzzyForm.setZhpfmc(rsMap.get("zhpfmc"));
		hzzyForm.setSjhm(rsMap.get("sjhm"));
		hzzyForm.setZzmm(rsMap.get("zzmm"));
		hzzyForm.setJtdz(rsMap.get("jtdz"));
		hzzyForm.setMz(rsMap.get("mz"));
		hzzyForm.setZysj(rsMap.get("zysj"));
		hzzyForm.setRychdm(rsMap.get("rychdm"));
		request.setAttribute("pkValue", pkValue);
		commonAction.appendRychList(request);
		commonAction.appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("rychmodiview");
	}
	
	public ActionForward hzzyrychmodiSave(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyHzzyActionForm hzzyForm = (PjpyHzzyActionForm) form;
		String pkValue = request.getParameter("pkValue");
		hzzyForm.setXn(request.getParameter("xn"));
		hzzyForm.setNd(request.getParameter("nd"));
		hzzyForm.setXq(request.getParameter("xq"));
		hzzyForm.setXh(request.getParameter("xh"));
		boolean bFlag = rychModi(hzzyForm, pkValue, request);
		if (bFlag) {//�ɹ�
			request.setAttribute("inserted", "yes");
		} else {//ʧ��
			request.setAttribute("inserted", "no");
		}
		commonAction.appendRychList(request);
		commonAction.appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rs", new HashMap());
		hzzyForm.setZzmm("");
		hzzyForm.setZysj("");
		hzzyForm.setJtdz("");
		hzzyForm.setMz("");
		hzzyForm.setDrzw("");
		return mapping.findForward("rychmodiview");
	}
	
	
	
	
	/**
	 * ��ѧ���޸ı���
	 * @param pkValue
	 * @param jxjpdModel
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean jxjModi(String pkValue, PjpyHzzyActionForm form,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		boolean bDel = false;
		DAO dao = DAO.getInstance();
		if (StringUtils.isEqual(pkValue, 
				 form.getXn() + form.getNd()
				+ form.getJxjdm() +form.getXh())) {
			//bDel = StandardOperation.delete("xsjxjb", "xn||nd||jxjdm||xh", pkValue, request);
			/*bFlag = dao
					.runUpdate(
							"update xsjxjb set cjmc=?,pjcj=?,zhpfmc=?,drzw=?,jfqk=?,sqly=? where xn||nd||jxjdm||xh=?",
							new String[] { DealString.toGBK(form.getCjmc()),
									DealString.toGBK(form.getPjcj()),
									DealString.toGBK(form.getZhpfmc()),
									DealString.toGBK(form.getDrzw()),DealString.toGBK(form.getJfqk()),DealString.toGBK(form.getSqly()),pkValue });*/
			bFlag = StandardOperation.update("xsjxjb", new String[]{"cjmc","pjcj","zhpfmc","drzw","jfqk","sqly"}, new String[] { DealString.toGBK(form.getCjmc()),
					DealString.toGBK(form.getPjcj()),
					DealString.toGBK(form.getZhpfmc()),
					DealString.toGBK(form.getDrzw()),DealString.toGBK(form.getJfqk()),DealString.toGBK(form.getSqly())}, "xn||nd||jxjdm||xh", pkValue, request);
		} else {
			bDel = StandardOperation.delete("xsjxjb", "xn||nd||jxjdm||xh", pkValue, request);
			bDel = StandardOperation.delete("xsjxjb", "xn||nd||jxjdm||xh", form.getXn() + form.getNd()
					+ form.getJxjdm() +form.getXh(), request);
			if (bDel) {
				bFlag = StandardOperation.insert("xsjxjb", new String[] { "xh",
						"xn","xq" , "nd", "jxjdm", "cjmc","pjcj","zhpfmc","drzw","jfqk","sqly" },
				new String[] {
						form.getXh(),
						form.getXn(),
						dao.getOneRs("select jxjsqxq from xtszb", new String[] {},
								"jxjsqxq"), form.getNd(), form.getJxjdm(),
						DealString.toGBK(form.getCjmc()),
						DealString.toGBK(form.getPjcj()),
						DealString.toGBK(form.getZhpfmc()),
						DealString.toGBK(form.getDrzw()),
						DealString.toGBK(form.getJfqk()),
						DealString.toGBK(form.getSqly())}, request);
				
				String tmptmp = dao.getOneRs("select count(*) num from xsfzxxb where xh=?",new String[]{form.getXh()},"num");
				tmptmp = StringUtils.isNull(tmptmp) ? "0" : tmptmp;
				if(tmptmp.equalsIgnoreCase("0")){
					String sql = "insert into xsfzxxb(sjhmxh) values(?,?)";		    
					dao.runUpdate(sql,new String[]{form.getSjhm(),form.getXh()});
				} else {
					dao.runUpdate("update xsfzxxb set sjhm=? where xh=?",new String[]{form.getSjhm(),form.getXh()});
				}
			}
		}
		
		return bFlag;
	}
	
	
	/**
	 * �����ƺ��޸�
	 * @param rychModel
	 * @param pkVlue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean rychModi(PjpyHzzyActionForm form, String pkValue ,HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		boolean bDel = false;
		DAO dao = DAO.getInstance();
		if (StringUtils
				.isEqual(pkValue,  form.getXn()
						 + form.getNd()
						+ form.getRychdm() + form.getXh())) {
			bDel = StandardOperation.delete("xsrychb", "xn||nd||rychdm||xh", pkValue, request);
			if(bDel){
			StandardOperation.insert("xsrychb",
					new String[] { "xh", "xn", "xq", "nd", "rychdm","zysj"}, new String[] {
							form.getXh(),form.getXn(),dao.getOneRs("select jxjsqxq from xtszb", new String[]{}, "jxjsqxq"),form.getNd(),form.getRychdm(),DealString.toGBK(form.getZysj())}, request);
			}
			//dao.runUpdate("delete from xsrychxxb where xh=?", new String[]{form.getXh()});
			StandardOperation.delete("xsrychxxb", "xh", form.getXh(), request);
			String sql = "insert into xsrychxxb(xh,drzw,tydbqk,byzx,jtdz,syd,brjl,zysj,hjqk,zzmm,cjmc,pjcj,zhpfmc) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			//dao.runUpdate(sql,new String[]{form.getXh(),DealString.toGBK(form.getDrzw()),"","",DealString.toGBK(form.getJtdz()),"","",DealString.toGBK(form.getZysj()),"",DealString.toGBK(form.getZzmm()),DealString.toGBK(form.getCjmc()),"",DealString.toGBK(form.getZhpfmc())});
			
			StandardOperation.insert("xsrychxxb", new String[]{"xh","drzw","tydbqk","byzx","jtdz","syd","brjl","zysj","hjqk","zzmm","cjmc","pjcj","zhpfmc"}, new String[]{form.getXh(),DealString.toGBK(form.getDrzw()),"","",DealString.toGBK(form.getJtdz()),"","",DealString.toGBK(form.getZysj()),"",DealString.toGBK(form.getZzmm()),DealString.toGBK(form.getCjmc()),"",DealString.toGBK(form.getZhpfmc())}, request);
			
			String tmptmp = dao.getOneRs("select count(*) num from xsfzxxb where xh=?",new String[]{form.getXh()},"num");
			tmptmp = StringUtils.isNull(tmptmp) ? "0" : tmptmp;
			if(tmptmp.equalsIgnoreCase("0")){
				sql = "insert into xsfzxxb(sjhm,wysp,xh,lxdh2) values(?,?,?,?)";		    
				dao.runUpdate(sql,new String[]{form.getSjhm(),"",form.getXh(),""});
			} else {
				dao.runUpdate("update xsfzxxb set sjhm=? where xh=?",new String[]{form.getSjhm(),form.getXh()});
			}
			bFlag = true;
		} else {
			bDel = StandardOperation.delete("xsrychb",
					"xn||nd||rychdm||xh", pkValue, request);
			bDel = StandardOperation.delete("xsrychb",
					"xn||nd||rychdm||xh", form.getXn()
					 + form.getNd()
						+ form.getRychdm() + form.getXh(),
					request);
			if (bDel) {
				bFlag = StandardOperation.insert("xsrychb",
						new String[] { "xh", "xn", "xq", "nd", "rychdm"}, new String[] {
								form.getXh(),form.getXn(),dao.getOneRs("select jxjsqxq from xtszb", new String[]{}, "jxjsqxq"),form.getNd(),form.getRychdm()}, request);
				//dao.runUpdate("delete from xsrychxxb where xh=?", new String[]{form.getXh()});
				
				StandardOperation.delete("xsrychxxb", "xh", form.getXh(), request);
				
				String sql = "insert into xsrychxxb(xh,drzw,tydbqk,byzx,jtdz,syd,brjl,zysj,hjqk,zzmm,cjmc,pjcj,zhpfmc) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
				//dao.runUpdate(sql,new String[]{form.getXh(),DealString.toGBK(form.getDrzw()),"","",DealString.toGBK(form.getJtdz()),"","",DealString.toGBK(form.getZysj()),"",DealString.toGBK(form.getZzmm()),DealString.toGBK(form.getCjmc()),"",DealString.toGBK(form.getZhpfmc())});
				
				StandardOperation.insert("xsrychxxb", new String[]{"xh","drzw","tydbqk","byzx","jtdz","syd","brjl","zysj","hjqk","zzmm","cjmc","pjcj","zhpfmc"}, new String[]{form.getXh(),DealString.toGBK(form.getDrzw()),"","",DealString.toGBK(form.getJtdz()),"","",DealString.toGBK(form.getZysj()),"",DealString.toGBK(form.getZzmm()),DealString.toGBK(form.getCjmc()),"",DealString.toGBK(form.getZhpfmc())}, request);
				
				String tmptmp = dao.getOneRs("select count(*) num from xsfzxxb where xh=?",new String[]{form.getXh()},"num");
				tmptmp = StringUtils.isNull(tmptmp) ? "0" : tmptmp;
				if(tmptmp.equalsIgnoreCase("0")){
					sql = "insert into xsfzxxb(sjhm,wysp,xh,lxdh2) values(?,?,?,?)";		    
					dao.runUpdate(sql,new String[]{form.getSjhm(),"",form.getXh(),""});
				} else {
					dao.runUpdate("update xsfzxxb set sjhm=? where xh=?",new String[]{form.getSjhm(),form.getXh()});
				}
			}
		}
		
		return bFlag;
	}
	
	/**
	 * ��ѧ�����뱨������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hzzyZsld(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		String pks = request.getParameter("pks");
		DAO daoo = DAO.getInstance();
		SxjyDAO dao = new SxjyDAO();
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<String[]> cjrs = new ArrayList<String[]>();
		String [] colList = new String []{"xh","bjmc","xm","xymc","xn","xq","xm","drzw","jxjmc","cjmc","zhpfmc","jfqk","fdyyj","xyshyj","xxshyj","jxjdm","xyshsj","fdyqm","xyqm", "xxshsj"};
		map = dao.sxjyQueryOne("view_xsjxjb", colList, "xn||nd||xh||jxjdm", pkValue);
		if (map == null || StringUtils.isNull(map.get("xh")))  {
			map = dao.sxjyQueryOne("view_xsjxjb", colList, "xn||nd||jxjdm||xh", pkValue);
		}
		String xh = map.get("xh");
		String[] jxjsqxnnd = dao.getOneRs("select jxjsqxn,jxjsqnd,jxjsqxq from xtszb", new String[]{}, new String[]{"jxjsqxn", "jxjsqnd", "jxjsqxq"});
		if (jxjsqxnnd == null) {
			jxjsqxnnd[0] = "";
			jxjsqxnnd[1] = "";
			jxjsqxnnd[2] = "";
		}
		String query = " where xh = ? and xn='" + jxjsqxnnd[0] +"' and xq='" +jxjsqxnnd[2]+ "' ";
		colList = new String []{"kcsbm","cj","kcxz"};
		cjrs = dao.sxjyQuery("cjb", query, new String []{xh}, colList, "");
		String [] cjList = null;
		int cjrsLength = cjrs.size()+1;
		for(int i=1;i<21;i++){
			if(i<cjrsLength){
				cjList = cjrs.get(i-1);
				map.put("kcmc"+i, cjList[0].replace("(ѡ)", "")+"("+cjList[2]+")");
				map.put("kccj"+i, cjList[1]);
			}else{
				map.put("kcmc"+i, "");
				map.put("kccj"+i, "");
			}
		}
		String sql = "select b.xm from view_xsjbxx a left join view_fdybjdz b on a.bjdm = b.bjdm where a.xh = ?";
		map.put("bzrxm", daoo.getOneRs(sql,new String []{xh},"xm"));
		String jxjdm = map.get("jxjdm");
		if(jxjdm.equalsIgnoreCase("024")||jxjdm.equalsIgnoreCase("025")||jxjdm.equalsIgnoreCase("026")||jxjdm.equalsIgnoreCase("027")){
			map.put("title", "����ѧ����ѧ��");
		}else{
			map.put("title", "���ѧ��������");
		}
		if (!StringUtils.isNull(map.get("xq")) && StringUtils.isEqual(map.get("xq"), "03")) {
			map.put("xq", "��һѧ��");
		} else {
			map.put("xq", "�ڶ�ѧ��");
		}
		String xyshsj = map.get("xyshsj");
		String xxshsj = map.get("xxshsj");
		map.put("xyshyear", StringUtils.isNull(xyshsj) ? "" : xyshsj.substring(0, 4));//ѧԺ������
		map.put("xyshmon", StringUtils.isNull(xyshsj) ? "" : xyshsj.substring(4, 6));//ѧԺ����·�
		map.put("xyshdate", StringUtils.isNull(xyshsj) ? "" : xyshsj.substring(6, 8));//ѧԺ�����
		
		map.put("xxshyear", StringUtils.isNull(xxshsj) ? "" : xxshsj.substring(0, 4));//ѧУ������
		map.put("xxshmon", StringUtils.isNull(xxshsj) ? "" : xxshsj.substring(4, 6));//ѧУ����·�
		map.put("xxshdate", StringUtils.isNull(xxshsj) ? "" : xxshsj.substring(6, 8));//ѧУ�����
		request.setAttribute("rs", map);// �������ݼ�
		
		request.setAttribute("pks", pks);
		return mapping.findForward("hzzyzsld");
	}
	
	/**
	 * �����ƺű�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hzyrychBbld(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyHzzyService service = new PjpyHzzyService();
		String pkValue = request.getParameter("pkValue");
		String pks = request.getParameter("pks");//��ѡ��VALUE
		HashMap<String, String> resMap = service.rychPrint(pkValue);
		String fdyshsj = resMap.get("fdyshsj");
		String xyshsj = resMap.get("xyshsj");
		String xxshsj = resMap.get("xxshsj");
		if (!StringUtils.isNull(fdyshsj)) {
			resMap.put("fdyshyear", fdyshsj.substring(0,4));
			resMap.put("fdyshmon", fdyshsj.substring(4,6));
			resMap.put("fdyshdate", fdyshsj.substring(6,8));
		}
		if (!StringUtils.isNull(xyshsj)) {
			resMap.put("xyshyear",xyshsj.substring(0,4));
			resMap.put("xyshmon", xyshsj.substring(4,6));
			resMap.put("xyshdate", xyshsj.substring(6,8));
		}
		if (!StringUtils.isNull(xxshsj)) {
			resMap.put("xxshyear",xxshsj.substring(0,4));
			resMap.put("xxshmon", xxshsj.substring(4,6));
			resMap.put("xxshdate", xxshsj.substring(6,8));
		}
		resMap.put("title", "У��" + (StringUtils.isNull(resMap.get("rychmc")) ? "" : (StringUtils.isEqual(resMap.get("rychmc"), "����ѧ���ɲ�(ϵԺѧ����֯)") ? resMap.get("rychmc").substring(0, resMap.get("rychmc").length() - 8) : resMap.get("rychmc").substring(0, resMap.get("rychmc").length() - 4))) + "�ǼǱ�");
		String xq = resMap.get("xq");
		if (!StringUtils.isNull(xq)) {
			resMap.put("xq", StringUtils.isEqual(xq, "01") ? "�ڶ�ѧ��" : "�ڶ�ѧ��");
		}
		request.setAttribute("rs", resMap);
		request.setAttribute("pks", pks);
		return mapping.findForward("hzzyrychld");
	}
	/**
	 * �Ƚ��༶��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hzzyxjbjLd(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		/*HttpSession session = request.getSession();
		String xxmc = session.getAttribute("xxmc").toString();
		String pkValue = request.getParameter("pkValue");
		String pks = request.getParameter("pks");
		HzyPjpyDAO dao = new HzyPjpyDAO();
		DAO daoo = DAO.getInstance();
		String defaultTitle = request.getParameter("titName");
		//String bjdm = request.getParameter("bjdm");

		//String xn = Base.getJxjsqxn();
		//String xq = Base.getJxjsqxq();
		String rychdm = (defaultTitle.equalsIgnoreCase("xjbj"))?"00001":(defaultTitle.equalsIgnoreCase("wmbj")?"00002":"");
		String[] columns = {"xn","xq","rychdm","bzxm","xsrs","bzr","zysj"};
		String primaryKey = "xn||xq||rychdm||bjdm";
		String primaryKeyValue = pkValue;
		String[] values = dao.getMes(primaryKey, primaryKeyValue, columns);
		//String bjmc = dao.getBjmc(bjdm);
		String bzxm = values != null && values.length == 7 ? values[3] : "";
		String xsrs = values != null && values.length == 7 ? values[4] : "";
		String bzr = values != null && values.length == 7 ? values[5] : "";
		String zysj = values != null && values.length == 7 ? values[6] : "";//��Ҫ�¼�
		//��ְԺ���Ƚ��༶�������༶�Ƽ���
		//String modelPath = servlet.getServletContext().getRealPath("")+"/print/pjpy_hzy_xjbjandwmbjsq.xls";
		String title = rychdm.equals("00001")? xxmc + "�Ƚ��༶�Ƽ���":(rychdm.equals("00002")? xxmc + "�����༶�Ƽ���":"");
		request.setAttribute("bjmc", bjmc);
		request.setAttribute("bzxm", bzxm);
		request.setAttribute("xsrs", xsrs);
		request.setAttribute("bzr", bzr);
		request.setAttribute("zysj", zysj);
		request.setAttribute("title", title);
		String[] xyxxshsj = daoo.getOneRs("select xyyj,xxyj,xyshsj,xxshsj,xyqm from pjpy_xjbjandwmsqb where xn||xq||rychdm||bjdm=?", new String[]{primaryKeyValue}, new String[]{"xyyj", "xxyj", "xyshsj", "xxshsj", "xyqm"});
		if (xyxxshsj != null && xyxxshsj.length == 5) {
			request.setAttribute("xyyj", xyxxshsj[0]);
			request.setAttribute("xyshyear", StringUtils.isNull(xyxxshsj[2]) ? "" : xyxxshsj[2].substring(0, 4));
			request.setAttribute("xyshmon", StringUtils.isNull(xyxxshsj[2]) ? "" : xyxxshsj[2].substring(4, 6));
			request.setAttribute("xyshdate", StringUtils.isNull(xyxxshsj[2]) ? "" : xyxxshsj[2].substring(6, 8));
			request.setAttribute("xxyj", xyxxshsj[1]);
			request.setAttribute("xxshyear", StringUtils.isNull(xyxxshsj[3]) ? "" : xyxxshsj[3].substring(0, 4));
			request.setAttribute("xxshmon", StringUtils.isNull(xyxxshsj[3]) ? "" : xyxxshsj[3].substring(4, 6));
			request.setAttribute("xxshdate", StringUtils.isNull(xyxxshsj[3]) ? "" : xyxxshsj[3].substring(6, 8));
			request.setAttribute("xyqm", xyxxshsj[4]);
		}
		request.setAttribute("pks", pks);*/
		return mapping.findForward("hzzyxjbjld");
	}
	
	/**
	 * ����ǩ��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward plqm(ActionMapping mapping, ActionForm form,
	HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String isFdy = session.getAttribute("isFdy").toString();
		PjpyHzzyActionForm hzzyForm = (PjpyHzzyActionForm) form;
		PjpyHzzyService service = new PjpyHzzyService();
		
		String pks = request.getParameter("ks");
		if (!StringUtils.isNull(pks)) {
			if (pks.lastIndexOf("@")==(pks.length()-1)) {
				pks = pks.substring(0, pks.length()-1);
			}
		}
		
		String act = request.getParameter("act");
		String tableName = request.getParameter("tableName");
		if ("save".equalsIgnoreCase(act)) {
			String cbv = request.getParameter("pks");
			service.updateQm(request.getParameter("qm"), cbv, userType, isFdy, request.getParameter("tableName"));
			request.setAttribute("inserted", "yes");
			hzzyForm.setQm(DealString.toGBK(hzzyForm.getQm()));
		}
		request.setAttribute("pks", pks);
		request.setAttribute("writable", "yes");
		request.setAttribute("tableName", tableName);
		return mapping.findForward("plqmpage");
	}
}
