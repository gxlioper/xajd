package xgxt.rcgl.nbcs;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.studentInfo.service.StudentInfoService;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

public class XsfwzdzxglAction  extends DispatchAction {
	
	/***
	 * ����ѧ����Ϣ�б�
	 * @param request
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @return void
	 * */
	public void appendProperties(HttpServletRequest request, String xydm,
			String zydm, String nj) throws Exception {
		String xy = "";
		String zy = "";
		String njLocal = nj;
		xy = xy==null ? "": (xydm==null ? "" : xydm); 
		zy = zy==null ? "": (zydm==null ? "" : zydm); 
		njLocal = nj==null ? "": nj;
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		
		request.setAttribute("writeAble", "yes");//�ж��û���дȨ		
		request.setAttribute("njList", Base.getNjList());//�꼶�б�
		request.setAttribute("xyList", Base.getXyList());//ѧԺ�б�
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));//רҵ�б�
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));//�༶�б�
	}
	
	/***
	 * ����ʱ����Ϣ�б�
	 * @param request
	 * @param xydm
	 * @param zydm
	 * @param nj
	 * @return void
	 * */
	public void appendTimeList(HttpServletRequest request){
		request.setAttribute("xqList", Base.getXqList());//ѧ���б�
		request.setAttribute("xnList", Base.getXnndList());//ѧ���б�
		request.setAttribute("ndList", Base.getXnndList());//ѧ���б�
		request.setAttribute("yfList", Base.getYfList());//�·��б�
	}
	
	/**
	 * ��ʾѧ�����������˹�����Ϣ
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xslfslrgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsfwzdzxglService service = new XsfwzdzxglService();
		
		request.setAttribute("slbmList", service.queryXslfslbmdmb());
		request.setAttribute("realTable", "xslfbmslrb");
		request.setAttribute("writeAble", "yes");
		return mapping.findForward("xslfslrgl");
	}
	
	/**
	 * ��ѯѧ�����������˹�����Ϣ
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xslfslrxxcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsfwzdzxglService service = new XsfwzdzxglService();
		XsfwzdzxglForm model = (XsfwzdzxglForm)form;
		
		List<String[]> rs = service.queryXslfbmslrb(model);
		
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("topTr", service.getXslfslrTopTr());		
		request.setAttribute("slbmList", service.queryXslfslbmdmb());//�������б�
		request.setAttribute("realTable", "xslfbmslrb");
		request.setAttribute("writeAble", "yes");
		return mapping.findForward("xslfslrgl");
	}
	
	/**
	 * ��ʾѧ��������������Ϣ����ҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xslfslrxxAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsfwzdzxglService service = new XsfwzdzxglService();
		
		request.setAttribute("slbmList", service.queryXslfslbmdmb());//�������б�
		return mapping.findForward("xslfslrxxAdd");
	}
	
	/**
	 * ��ʾѧ��������������Ϣ�޸�ҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xslfslrxxModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsfwzdzxglService service = new XsfwzdzxglService();
		XsfwzdzxglForm model = (XsfwzdzxglForm)form;
		model.setPkValue(request.getParameter("pkValue"));
		
		request.setAttribute("rs", service.queryXslfbmslrbOne(model));//��������ѯ��Ϣ
		return mapping.findForward("xslfslrxxModi");
	}
	
	/**
	 * ѧ��������������Ϣ����
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward saveXslfslrxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsfwzdzxglService service = new XsfwzdzxglService();
		XsfwzdzxglForm model = (XsfwzdzxglForm)form;
		String page = "xslfslrxxModi";
		String doType = request.getParameter("doType");
		
		model.setPkValue(model.getSlbmdm()+model.getSlryhm());//��������ֵ		
		page = StringUtils.isNotNull(doType) && "add".equalsIgnoreCase(doType) ? "xslfslrxxAdd" : page;
		
		request.setAttribute("result", service.saveXslfbmslrb(model,request));//��Ϣ����
		request.setAttribute("slbmList", service.queryXslfslbmdmb());//�������б�		
		request.setAttribute("rs", service.queryXslfbmslrbOne(model));
		return mapping.findForward(page);
	}
	
	/**
	 * ѧ��������������Ϣɾ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward delXslfslrxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsfwzdzxglService service = new XsfwzdzxglService();
		XsfwzdzxglForm model = (XsfwzdzxglForm)form;
		
		request.setAttribute("result", service.delXslfbmslrb(model));//ɾ����Ϣ
		return xslfslrxxcx(mapping, form, request, response);
	}
	
	/**
	 * ѧ��������������Ϣ��ѯ����
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward expXslfslrxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsfwzdzxglService service = new XsfwzdzxglService();
		XsfwzdzxglForm model = (XsfwzdzxglForm)form;
		model.setSlbmdm(request.getParameter("slbmdm"));
		model.setSlryhm(request.getParameter("slryhm"));
		model.setSlrxm(request.getParameter("slrxm"));
		
		String[] colList = {"slbmdm","slbmmc","slryhm","slrxm","slrlxdh"};
		String[] colListCN = service.getColCN("view_xslfbmslrb", colList);
		List<String[]> rs = service.queryXslfbmslrbForExp(model);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		Excel2Oracle.exportData(rs,colList,colListCN, response.getOutputStream());
		return mapping.findForward("");
	}
	
	/**
	 * ��ʾѧ��������Ϣ��ѯҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xslfxxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsfwzdzxglService service = new XsfwzdzxglService();
		
		request.setAttribute("slbmList", service.queryXslfslbmdmb());
		request.setAttribute("realTable", "xslfglb");
		request.setAttribute("writeAble", "yes");
		appendProperties(request, "","","");
		appendTimeList(request);
		return mapping.findForward("xslfxxgl");
	}
	
	/**
	 * ��ѯѧ������������Ϣ
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xslfxxcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsfwzdzxglService service = new XsfwzdzxglService();
		XsfwzdzxglForm model = (XsfwzdzxglForm)form;
		
		List<String[]> rs = service.queryXslfglb(model);
		
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("topTr", service.getXslfTopTr());		
		request.setAttribute("slbmList", service.queryXslfslbmdmb());//�������б�
		request.setAttribute("realTable", "xslfglb");
		appendProperties(request, model.getXydm(), model.getZydm(), model.getNj());
		appendTimeList(request);
		return mapping.findForward("xslfxxgl");
	}
	
	/**
	 * ��ʾѧ������������Ϣ����ҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xslfxxAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsfwzdzxglService service = new XsfwzdzxglService();
		StudentInfoService stuinfo = new StudentInfoService();
		String xh = request.getParameter("xh");
		HashMap<String, String> map = new HashMap<String, String>();
		
		if(StringUtils.isNotNull(xh)){//��ѯѧ��������Ϣ
			map = stuinfo.getStuInfo(xh);
		}
		map.put("nd", Base.currNd);
		map.put("yf",GetTime.getNowMonth().length() == 1 ? 0+GetTime.getNowMonth() : GetTime.getNowMonth());
		request.setAttribute("rs", map);
		request.setAttribute("slbmList", service.queryXslfslbmdmb());//�������б�		
		return mapping.findForward("xslfxxAdd");
	}
	
	/**
	 * ��ʾѧ������������Ϣ�޸�ҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xslfxxModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsfwzdzxglService service = new XsfwzdzxglService();
		XsfwzdzxglForm model = (XsfwzdzxglForm)form;
		model.setPkValue(request.getParameter("pkValue"));
		
		request.setAttribute("rs", service.queryXslfglbOne(model));//��������ѯ��Ϣ
		request.setAttribute("slbmList", service.queryXslfslbmdmb());//�������б�
		return mapping.findForward("xslfxxModi");
	}
	
	/**
	 * ѧ������������Ϣ����
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward saveXslfxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsfwzdzxglService service = new XsfwzdzxglService();
		XsfwzdzxglForm model = (XsfwzdzxglForm)form;
		String page = "xslfxxModi";
		String doType = request.getParameter("doType");
		
		model.setPkValue(model.getXh()+model.getLfrq());//��������ֵ		
		page = StringUtils.isNotNull(doType) && "add".equalsIgnoreCase(doType) ? "xslfxxAdd" : page;
		
		request.setAttribute("result", service.saveXslfglb(model,request));//��Ϣ����
		request.setAttribute("slbmList", service.queryXslfslbmdmb());//�������б�		
		request.setAttribute("rs", service.queryXslfglbOne(model));
		return mapping.findForward(page);
	}
	
	/**
	 * ѧ��������������Ϣɾ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward delXslfxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsfwzdzxglService service = new XsfwzdzxglService();
		XsfwzdzxglForm model = (XsfwzdzxglForm)form;
		
		request.setAttribute("result", service.delXslfglb(model));//ɾ����Ϣ
		return xslfxxcx(mapping, form, request, response);
	}
	
	/**
	 * ѧ��������������Ϣ��ѯ����
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward expXslfxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsfwzdzxglService service = new XsfwzdzxglService();
		XsfwzdzxglForm model = (XsfwzdzxglForm)form;
		
		model.setXh(request.getParameter("xh"));
		model.setXm(request.getParameter("xm"));
		model.setNd(request.getParameter("nd"));
		model.setYf(request.getParameter("yf"));
		model.setSlbmdm(request.getParameter("slbmdm"));
		model.setNj(request.getParameter("nj"));
		model.setXydm(request.getParameter("xydm"));
		model.setZydm(request.getParameter("zydm"));
		model.setBjdm(request.getParameter("bjdm"));
		model.setSlrxm(request.getParameter("slrxm"));
		
		String[] colList = {"nd","yf","xh","xm","xb","xz","xydm","xymc","zydm","zymc","bjdm","bjmc","nj","lffs","lfrq","lfsy","slbmdm","slbmmc","slr","slrxm","slsj","hfr","hfsj","bjsj","fwzl","lxdh","bz"};
		String[] colListCN = service.getColCN("view_xslfglb", colList);
		List<String[]> rs = service.queryXslfglbForExp(model);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		Excel2Oracle.exportData(rs,colList,colListCN, response.getOutputStream());
		return mapping.findForward("");
	}
	
	/**
	 * ��ʾѧ�����ûطõǼ���Ϣҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xslfhfdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsfwzdzxglService service = new XsfwzdzxglService();
		XsfwzdzxglForm model = (XsfwzdzxglForm)form;
		model.setPkValue(request.getParameter("pkValue"));
		
		request.setAttribute("rs", service.queryXslfglbOne(model));//��������ѯ��Ϣ
		return mapping.findForward("xslfhfdj");
	}
	
	/**
	 * ѧ�����ûط���Ϣ����
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward saveXslfhf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsfwzdzxglService service = new XsfwzdzxglService();
		XsfwzdzxglForm model = (XsfwzdzxglForm)form;
		
		model.setPkValue(model.getXh()+model.getLfrq());//��������ֵ		
		
		request.setAttribute("result", service.saveXslfhf(model,request));//��Ϣ����
		request.setAttribute("rs", service.queryXslfglbOne(model));
		return mapping.findForward("xslfhfdj");
	}
	
	
	/**
	 * ��ʾ������Ϣ����ҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward cqxxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsfwzdzxglService service = new XsfwzdzxglService();
		
		request.setAttribute("slbmList", service.queryXslfslbmdmb());
		request.setAttribute("realTable", "xszdfwzxcqb");
		request.setAttribute("writeAble", "yes");
		appendTimeList(request);
		return mapping.findForward("cqxxgl");
	}
	
	/**
	 * ��ѯ������Ϣ��Ϣ
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward cqxxcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsfwzdzxglService service = new XsfwzdzxglService();
		XsfwzdzxglForm model = (XsfwzdzxglForm)form;
		
		List<String[]> rs = service.queryXszdfwzxcqb(model);
		
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("topTr", service.getCqxxTopTr());		
		request.setAttribute("slbmList", service.queryXslfslbmdmb());//�������б�
		request.setAttribute("realTable", "xszdfwzxcqb");
		request.setAttribute("writeAble", "yes");
		appendTimeList(request);
		return mapping.findForward("cqxxgl");
	}
	
	/**
	 * ��ʾ������Ϣ����ҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward cqxxAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsfwzdzxglService service = new XsfwzdzxglService();
		
		appendTimeList(request);
		request.setAttribute("slbmList", service.queryXslfslbmdmb());//�������б�
		return mapping.findForward("cqxxAdd");
	}
	
	/**
	 * ��ʾ������Ϣ�޸�ҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward cqxxModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsfwzdzxglService service = new XsfwzdzxglService();
		XsfwzdzxglForm model = (XsfwzdzxglForm)form;
		model.setPkValue(request.getParameter("pkValue"));
		
		request.setAttribute("rs", service.queryXszdfwzxcqbOne(model));//��������ѯ��Ϣ
		return mapping.findForward("cqxxModi");
	}
	
	/**
	 * ������Ϣ����
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward saveCqxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsfwzdzxglService service = new XsfwzdzxglService();
		XsfwzdzxglForm model = (XsfwzdzxglForm)form;
		String page = "cqxxModi";
		String doType = request.getParameter("doType");
		
		model.setPkValue(model.getNd()+model.getYf()+model.getSlbmdm());//��������ֵ		
		page = StringUtils.isNotNull(doType) && "add".equalsIgnoreCase(doType) ? "cqxxAdd" : page;
		
		request.setAttribute("result", service.saveXszdfwzxcqb(model,request));//��Ϣ����
		request.setAttribute("slbmList", service.queryXslfslbmdmb());//�������б�		
		request.setAttribute("rs", service.queryXszdfwzxcqbOne(model));
		appendTimeList(request);
		return mapping.findForward(page);
	}
	
	/**
	 * ������Ϣɾ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward delCqxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsfwzdzxglService service = new XsfwzdzxglService();
		XsfwzdzxglForm model = (XsfwzdzxglForm)form;
		
		request.setAttribute("result", service.delXszdfwzxcqb(model));//ɾ����Ϣ
		return cqxxcx(mapping, form, request, response);
	}
	
	/**
	 * ������Ϣ��ѯ����
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward expCqxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsfwzdzxglService service = new XsfwzdzxglService();
		XsfwzdzxglForm model = (XsfwzdzxglForm)form;
		model.setSlbmdm(request.getParameter("slbmdm"));
		model.setNd(request.getParameter("nd"));
		model.setYf(request.getParameter("yf"));
		
		String[] colList = {"nd","yf","slbmdm","slbmmc","ykfts","jscqts","xscqts","bz"};
		String[] colListCN = service.getColCN("view_xszdfwzxcqb", colList);
		List<String[]> rs = service.queryXszdfwzxcqbForExp(model);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		Excel2Oracle.exportData(rs,colList,colListCN, response.getOutputStream());
		return mapping.findForward("");
	}
	
	/**
	 * ��ӡ�ط�ͳ�Ʊ�
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward printHfxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsfwzdzxglService service = new XsfwzdzxglService();
		XsfwzdzxglForm model = (XsfwzdzxglForm) form;
		model.setNd(request.getParameter("nd"));
		model.setYf(request.getParameter("yf"));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.printHftjb(response.getOutputStream(),model); 
		return mapping.findForward("");
	}
	
	/**
	 * ��ӡ�ط�δ����ͳ�Ʊ�
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward printHfwbxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsfwzdzxglService service = new XsfwzdzxglService();
		XsfwzdzxglForm model = (XsfwzdzxglForm) form;
		model.setNd(request.getParameter("nd"));
		model.setYf(request.getParameter("yf"));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.printHfwbxxb(response.getOutputStream(),model); 
		return mapping.findForward("");
	}
	
	
	/**
	 * ��ӡ������Ϣͳ�Ʊ�
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward printCqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsfwzdzxglService service = new XsfwzdzxglService();
		XsfwzdzxglForm model = (XsfwzdzxglForm)form;
		String nd = request.getParameter("nd");
		String yf = request.getParameter("yf");		
		
		model.setNd(nd);
		model.setYf(yf);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.printCqtjb(response.getOutputStream(),model);
		return mapping.findForward("");
	}
	
}
