package xgxt.rcgl.nbzy;

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
import xgxt.dtjs.utils.GetWriteAbleAndTitle;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.utils.Fdypd;
import xgxt.utils.GetTime;


public class MpglAction  extends DispatchAction {
	public ActionForward bysmpxxwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ����ְҵ ��ҵ����Ƭ��Ϣά��
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String tableName = "view_xyqkyl";
		String realTable = "xyqkylb";

		ArrayList<String[]> rs = null;
		MpglForm myForm = (MpglForm) form;
		MpglService service = new MpglService();
		MpglModel myModel = new MpglModel();
		String xy = myForm.getXydm();

		xy = (xy == null) ? "" : xy;

		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}

		BeanUtils.copyProperties(myModel, myForm);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs = service.getBysmpxxwh(myModel);
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}

		List topTr = service.getBysmpxxwhTopTr();
		request.setAttribute("xydm", xy);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("njList", Base.getNjList());
		commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward("bysmpxxwh");
	}
	
	public ActionForward nbzyBysmpOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ����ְҵ ��ҵ����Ƭ��Ϣ�����鿴
		String pk = DealString.toGBK(request.getParameter("pk"));
		MpglService service = new MpglService();
		HashMap<String, String> rs = service.getBysmpOne(pk);
		request.setAttribute("rs", rs);
		request.setAttribute("pk", pk);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("njList", Base.getNjList());
		return mapping.findForward("bysmpxxwhOne");
	}

	public ActionForward saveBysmpOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ����ְҵ ��ҵ����Ƭ��Ϣ����
		MpglForm myForm = (MpglForm) form;
		MpglService service = new MpglService();
		MpglModel myModel = new MpglModel();
		String pk = DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel, myForm);
			boolean inserted = service.updataBysmp(myModel, pk, request);
			if (inserted) {
				request.setAttribute("inserted", "ok");
			} else {
				request.setAttribute("inserted", "no");
			}
		return mapping.findForward("bysmpxxwhOne");
	}

	public ActionForward delBysmpOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ����ְҵ ��ҵ����Ƭ��Ϣ����ɾ��
		MpglService service = new MpglService();
		String pk = DealString.toGBK(request.getParameter("pk"));
		boolean inserted = service.deleteBysmpOne(pk, request);
		if (inserted) {
			request.setAttribute("delete", "ok");
		} else {
			request.setAttribute("delete", "no");
		}
		return bysmpxxwh(mapping, form, request, response);
	}
	
	public ActionForward xyftjmdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ��ְԺ��Լ��Ϣ¼��
		HttpSession session = request.getSession();
		String userType = (String)session.getAttribute("userType");
		if(!userType.equalsIgnoreCase("xy")){
			request.setAttribute("noqx", "yes");
		}
		return mapping.findForward("xyftjmdj");
	}
	
	public ActionForward saveXyftjmdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ��ְԺ��Լ��Ϣ¼�뱣��
		HttpSession session = request.getSession();
		String xydm = (String)session.getAttribute("userDep");
		MpglForm myForm = (MpglForm) form;
		MpglService service = new MpglService();
		MpglModel myModel = new MpglModel();
		BeanUtils.copyProperties(myModel, myForm);
		myModel.setXydm(xydm);
		boolean inserted = service.updataXyftjm(myModel, "", request);
		if (inserted) {
			request.setAttribute("inserted", "ok");
		} else {
			request.setAttribute("inserted", "no");
		}
		ModelToStrings.formToGBK(myForm);
		return mapping.findForward("xyftjmdj");
	}
	
	public ActionForward xyftjmsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ����ְҵ ��ְԺ��Լ���
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String tableName = "view_xyftjmdj";
		String realTable = "xyftjmdjb";

		ArrayList<String[]> rs = null;
		MpglForm myForm = (MpglForm) form;
		MpglService service = new MpglService();
		MpglModel myModel = new MpglModel();
		String xy = myForm.getXydm();

		xy = (xy == null) ? "" : xy;

		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}

		BeanUtils.copyProperties(myModel, myForm);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs = service.getXyftjm(myModel);
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}
		List topTr = service.getXyftjmTopTr();
		request.setAttribute("xydm", xy);
		request.setAttribute("xyList", Base.getXyList());
		ModelToStrings.formToGBK(myForm);
		commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward("xyftjmsh");
	}
	
	public ActionForward xyftjmshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ����ְҵ ��ְԺ��Լ�������
		String pk = DealString.toGBK(request.getParameter("pk"));
		MpglService service = new MpglService();
		HashMap<String, String> rs = service.getXyftjmsh(pk);
		request.setAttribute("rs", rs);
		request.setAttribute("pk", pk);
		request.setAttribute("xyList", Base.getXyList());
		return mapping.findForward("xyftjmshOne");
	}

	public ActionForward saveXyftjmshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ����ְҵ ��ְԺ��Լ������˱���
		MpglForm myForm = (MpglForm) form;
		MpglService service = new MpglService();
		MpglModel myModel = new MpglModel();
		String pk = DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel, myForm);
			boolean inserted = service.updataXyftjm(myModel, pk, request);;
			if (inserted) {
				request.setAttribute("inserted", "ok");
			} else {
				request.setAttribute("inserted", "no");
			}
		return mapping.findForward("bysmpxxwhOne");
	}

	public ActionForward delXyftjmshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ����ְҵ ��ְԺ��Լ����ɾ��
		MpglService service = new MpglService();
		String pk = DealString.toGBK(request.getParameter("pk"));
		boolean inserted = service.deleteXyftjmshOne(pk, request);
		if (inserted) {
			request.setAttribute("delete", "ok");
		} else {
			request.setAttribute("delete", "no");
		}
		return xyftjmsh(mapping, form, request, response);
	}
	
	private void commonRequestSet(HttpServletRequest request,
			String tableName, String realTable, ArrayList<String[]> rs,
			List topTr) {
		// Request��ֵ��ͨ�÷���2 ������title�����ݿ���ȡ
		String writeAble = request.getParameter("writeAble");
		String title = DealString.toGBK(request.getParameter("title"));
		if (Base.isNull(writeAble)) {
			String[] message = GetWriteAbleAndTitle.getWriteAbleAndTitle(request);
			writeAble = message[0];
			title = message[1];
		}
		if (rs != null) {
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("title", title);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
	}
	
	public ActionForward XyftjmshReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ����ְҵ ��ְԺ��Լ�������
		String pk = DealString.toGBK(request.getParameter("pk"));
		MpglService service = new MpglService();
		HashMap<String, String> rs = service.getXyftjmsh(pk);
		request.setAttribute("rs", rs);
		request.setAttribute("pk", pk);
		request.setAttribute("dysj", GetTime.getNowTime5());
		request.setAttribute("xyList", Base.getXyList());
		return mapping.findForward("xyftjmshReport");
	}
	
	//������Ϣ��ѯ
	public ActionForward hkxxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MpglForm  myForm = (MpglForm) form;
		MpglService service = new MpglService();
		String doType = request.getParameter("doType");
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? ""
				:session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep") == null ? ""
				:session.getAttribute("userDep").toString();
		if("xy".equals(userType)){
			myForm.setXydm(userDep);
		}
		String xydm = myForm.getXydm() == null ? "":myForm.getXydm();
		String zydm = myForm.getZydm() == null ? "":myForm.getZydm();
		String nj = myForm.getNj() == null ? "":myForm.getNj();
		String bjKey = xydm + "!!" + zydm + "!!" + nj;
		if("query".equals(doType)){
			myForm.getPages().setMaxRecord(Integer.parseInt(service.queryHkxxCount_ser(myForm)));
			request.setAttribute("rs", service.queryHkxx_ser(myForm));
			request.setAttribute("topTr", service.getTableTop("xshkgl"));
		}
		myForm.setXm(DealString.toGBK(myForm.getXm()));
		request.setAttribute("tableName", "view_xshkgl");
		request.setAttribute("realTable", "xshkgl");
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xydm));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		return mapping.findForward("hkxxgl");
	}
	
	//������Ϣ����
	public ActionForward addhkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MpglForm  myForm = (MpglForm) form;
		MpglService service = new MpglService();
		HashMap<String,String> map = new HashMap<String,String>();
		String doType = request.getParameter("doType");	
		if("save".equals(doType)){
			request.setAttribute("result", service.saveXsHkxx_ser(myForm));
		}	
		if(!Base.isNull(myForm.getXh())){
			map = service.getXsJbxx_ser(myForm.getXh());
		}
		request.setAttribute("rs", map);
		return mapping.findForward("addhkxx");
	}
	
	//������Ϣ�޸�
	public ActionForward updatehkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MpglForm  myForm = (MpglForm) form;
		MpglService service = new MpglService();
		HashMap<String,String> map = new HashMap<String,String>();
		String doType = request.getParameter("doType");	
		String pkVStr = request.getParameter("pkVStr");
		String view = request.getParameter("view");	
		if("save".equals(doType)){
			request.setAttribute("result", service.updateXsHkxx_ser(myForm));
		}	
		if(myForm.getXh()!=null){
			pkVStr = myForm.getXh();
		}
		if(!Base.isNull(pkVStr)){
			map = service.getXsHkxx_ser(pkVStr);
		}
		request.setAttribute("view", view);
		request.setAttribute("rs", map);
		return mapping.findForward("updatehkxx");
	}
	//������Ϣɾ��
	public ActionForward deletehkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MpglService service = new MpglService();
		String pkVStr = request.getParameter("pkVStr");
		request.setAttribute("result", service.deleteXsHkxx_ser(pkVStr));
		return hkxxgl(mapping, form, request, response);
	}
	
	//���֤��������
	public ActionForward xssfzbbsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MpglForm  myForm = (MpglForm) form;
		MpglService service = new MpglService();
		HttpSession session = request.getSession();
		boolean flag = false;
		String userType = session.getAttribute("userType") == null ? ""
				:session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName") == null ? ""
				:session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		HashMap<String,String> map = new HashMap<String,String>();
		if("stu".equals(userType)){
			if("save".equals(doType)){
				request.setAttribute("result", service.saveXsBbsq_ser(myForm));
			}
			flag = service.isCzhk_ser(userName);
			if(flag){
				map = service.getXsSfzJbxx_ser(userName);
				request.setAttribute("rs", map);
			}
			request.setAttribute("sfksq", flag);
		}else{
			if("save".equals(doType)){
				request.setAttribute("result", service.saveXsBbsqByTeacher_ser(myForm));
			}
			if(!Base.isNull(myForm.getXh())){
				map = service.getXsxx_ser(myForm.getXh());
			}
			request.setAttribute("rs", map);
		}
		return mapping.findForward("sfzbbsq");
	}
	//���֤���������޸�
	public ActionForward updatesfzbbxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MpglForm  myForm = (MpglForm) form;
		MpglService service = new MpglService();
		HashMap<String,String> map = new HashMap<String,String>();
		String doType = request.getParameter("doType");	
		String pkVStr = request.getParameter("pkVStr");
		String view = request.getParameter("view");	
		if("save".equals(doType)){
			request.setAttribute("result", service.updateXsSfzsqxx_ser(myForm));
		}	
		if(myForm.getXh()!=null && myForm.getSqsj()!=null){
			pkVStr = myForm.getXh()+myForm.getSqsj();
		}
		if(!Base.isNull(pkVStr)){
			map = service.getXsSfzbbxx_ser(pkVStr);
		}
		request.setAttribute("view", view);
		request.setAttribute("rs", map);
		return mapping.findForward("updatesfzsqxx");
	}
	
	//���֤����ɾ��
	public ActionForward deleteSfzbbsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		MpglService service = new MpglService();
		String pkVStr = request.getParameter("pkVStr");
		request.setAttribute("result", service.deleteXsSfzsqxx_ser(pkVStr));
		return xssfzbbsh(mapping, form, request, response);
	}
	
	//���֤�������
	public ActionForward xssfzbbsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse Response){
		MpglForm  myForm = (MpglForm) form;
		MpglService service = new MpglService();
		String doType = request.getParameter("doType");	
		String pkVStr = request.getParameter("pkVStr");
		String fdysh = request.getParameter("fdysh");
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName") == null ? ""
				:session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		if (session.getAttribute("fdyQx").toString() != null
				&& session.getAttribute("fdyQx").equals(true)) {// ����Ա��¼
			userType = "fdy";
		}			
			
		if(Base.isNull(myForm.getXn())){
			myForm.setXn(Base.currXn);
		}
		if(Base.isNull(myForm.getXq())){
			myForm.setXq(Base.currXq);
		}
		if("sqsh".equals(doType)){
			request.setAttribute("result", service.sfzbbSh_ser(pkVStr, fdysh));
			doType = "query";
		}
		if("query".equals(doType)){
			List<HashMap<String, String>> rs = service.querySfzbbSh_ser(myForm,userName,userType);
			request.setAttribute("topTr", service.getTableTop("sfzbb"));
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs.size());			
		}		
		myForm.setXm(DealString.toGBK(myForm.getXm()));
		request.setAttribute("realTable", "sfzbbsqb");
		request.setAttribute("tableName", "view_sfzbbsqb");
		request.setAttribute("userType", userType);
		request.setAttribute("xnList",Base.getXnndList());
   	 	request.setAttribute("xqList",Base.getXqList());
   	 	request.setAttribute("bjList", Fdypd.getFdybjList(userName));// ���Ͱ༶�б�
		request.setAttribute("zyList", Fdypd.getFdyZyList(userName));// ���Ͱ༶�б�
		request.setAttribute("xyList", Fdypd.getFdyXyList(userName));// ���Ͱ༶�б�
		return mapping.findForward("sfzbbsh");
	}
	
	
	//���֤�������
	public ActionForward sfzbbshcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse Response){
		MpglForm  myForm = (MpglForm) form;
		MpglService service = new MpglService();
		String doType = request.getParameter("doType");	
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName") == null ? ""
				:session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType") == null ? ""
				:session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep") == null ? ""
				:session.getAttribute("userDep").toString();
		if("stu".equals(userType)){
			request.setAttribute("topTr", service.getTableQueryTop("sfzbb"));
			request.setAttribute("rs", service.sfzbbShxscx_ser(userName));	
			return mapping.findForward("sfzbbshxscx");
		}else if("xy".equals(userType)){
			myForm.setXydm(userDep);
		}
		if (session.getAttribute("fdyQx").toString() != null
				&& session.getAttribute("fdyQx").equals(true)) {
			// ����Ա��¼
			userType = "fdy";
		}
		if(Base.isNull(myForm.getXn())){			
			myForm.setXn(Base.currXn);
		}
		if(Base.isNull(myForm.getXq())){
			myForm.setXq(Base.currXq);
		}
		if("query".equals(doType)){
			List<HashMap<String, String>> rs = service.querySfzbb_ser(myForm,userName,userType);
			request.setAttribute("topTr", service.getTableQueryTop("sfzbb"));
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs.size());		
		}		
		myForm.setXm(DealString.toGBK(myForm.getXm()));
		request.setAttribute("realTable", "sfzbbsqb");
		request.setAttribute("tableName", "view_sfzbbsqb");
		request.setAttribute("userType", userType);
		request.setAttribute("xnList",Base.getXnndList());
   	 	request.setAttribute("xqList",Base.getXqList());
   	 	request.setAttribute("bjList", Fdypd.getFdybjList(userName));// ���Ͱ༶�б�
		request.setAttribute("zyList", Fdypd.getFdyZyList(userName));// ���Ͱ༶�б�
		request.setAttribute("xyList", Fdypd.getFdyXyList(userName));// ���Ͱ༶�б�
		return mapping.findForward("sfzbbshcx");
	}
	
	/** 
	 * ���ڹ�����֪��Ϣ����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward showHkglInfoPub(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String doType = request.getParameter("doType");
		String title = DealString.toGBK(request.getParameter("title"));
		String content = DealString.toGBK(request.getParameter("content1"));
		MpglService service = new MpglService();
		String infoType = "";
		if("11078".equals(Base.xxdm)){
			infoType = "tzgg";
		}else{
			infoType = "hkglxz";
		}
		if("save".equals(doType)){
			request.setAttribute("result", service.saveHkxz(title, content, request,infoType));
		}
		request.setAttribute("info",service.getHkxz(infoType));
		return mapping.findForward("hkglInfoPub");
	}
	
	/** 
	 * ѧ���鿴���ڹ�����֪
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward viewHkglxzInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		MpglService service = new MpglService();
		request.setAttribute("info",service.getHkxz("hkglxz"));
		return mapping.findForward("viewhkglInfo");
	}
}
