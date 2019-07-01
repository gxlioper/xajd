
package xgxt.pjpy.tyb.zhszcp.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.bdsz.BdszService;
import xgxt.form.User;
import xgxt.pjpy.PjpyTyService;
import xgxt.pjpy.ntzydx.PjpyNtzydxService;
import xgxt.pjpy.tyb.cssz.service.PjpyPjzqszService;
import xgxt.pjpy.tyb.zhszcp.model.PjpyZhszcpModel;
import xgxt.pjpy.tyb.zhszcp.service.PjpyZctjszService;
import xgxt.pjpy.tyb.zhszcp.service.PjpyZhszcpService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.UserTypePd;
import xgxt.utils.String.StringUtils;
import xgxt.utils.img.ImageExportUtils;
import xgxt.wjcf.wjcfutils.CommonAction;
import xgxt.xtwh.xtwhOther.XtwhOtherService;

import com.zfsoft.basic.BasicService;
import common.GlobalsVariable;


public class PjpyZhszcpwhAction extends CommonAction {
	
	//һ������Ĭ�ϱ��
	private static final String ZHSZCP_YJDM = "999";
	
    /**
    * �ۺ����ʲ����ļ���Ŀ����ά��
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
    * */
    public ActionForward zhszcpSjwh(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response) throws Exception{
       
    	PjpyZhszcpwhActionForm actionForm = (PjpyZhszcpwhActionForm) form;
    	PjpyZhszcpService service = new PjpyZhszcpService();
        PjpyPjzqszService pjzqService = new PjpyPjzqszService(); 
        PjpyZctjszService tjszService = new PjpyZctjszService();
        XtwhOtherService xtwhService = new XtwhOtherService();
        PjpyTyService tyService = new PjpyTyService();
        User user = getUser(request);
        
        if (UserTypePd.isXy(user.getUserType()) && !UserTypePd.isFdy(user.getIsFdy())) {
        	actionForm.setXydm(user.getUserDep());
        }
        
        //��Ŀ���뼶��,2,3,4��
        String dmlb = request.getParameter("dmlb");
        //��Ŀ����
        String dm = request.getParameter("dm");
        //����
        String bm = request.getParameter("bm");
        //��˼���
        String shjb = tjszService.queryZhszcpxmShjb(dm, bm);
        //��������
        String act = request.getParameter("act");
        //�����Զ����ֶ�ֵ��
        String bdszBcnrb = "py_bdsz_bcnr";
        
        List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>(); 
        List<String[]> rs = new ArrayList<String[]>();
        List<HashMap<String, String>> titleList = tjszService.queryZcDmxxList(dm);
        
        //�ж��Ƿ�ɲ���
		if(!GlobalsVariable.XTDM_ADMIN.equalsIgnoreCase(getUserType(request)) 
				&&!xtwhService.gnkgFlag(GlobalsVariable.GNDM_PJPY_ZHSZCP)
					&& tyService.checkKgflag()){
			String msg = "ά��������ʱ�����Ų��������޲�ѯ��";
			request.setAttribute("yhInfo", msg);
		}
        
        addpendUserInfo(actionForm, request);
        //��ѯ���ݲ���
        if (QUERY.equalsIgnoreCase(act)) {
			PjpyZhszcpModel model = new PjpyZhszcpModel();
			BeanUtils.copyProperties(model, actionForm);
			model.setShjb(new String[]{shjb});

			// �Զ����ֶ��б�
			HashMap<String, String[]> zdyzdMap = service.getZdyzdMap(bm);
			// ��ѯ��ʾ�ֶ��б�
			HashMap<String, String[]> cxzdMap = service.getPjpyCxzdMap(bm,shjb);
			
			//��ѯ������ֶ���Ϣ
			BasicService basicService = new BasicService();
			String[] zbzdArray = basicService.getTableColumn(bm);
			
			topTr = service.getZhszcpTitle(cxzdMap);
			rs = service.queryZhszcpResult(model, cxzdMap,zdyzdMap, bm, dm, zbzdArray);
		}
        if(DELETE.equalsIgnoreCase(act)){//ɾ������
        	String realTable = bm.substring(5, bm.length());
        	//ɾ����������
        	deleteOperation(request, realTable); 
        	//ɾ����������
        	boolean result = (Boolean)request.getAttribute("result");
        	if(result){//ɾ������ɹ�
        		HashMap<String, String[]> primaryMap = getValueArrayMap(request, PRIFIX_PRIMARY_KEY);
        		String[] primaryKeys = {};
        		int i = 0;
        		for(String key : primaryMap.keySet()){
        			if(i++ == 0 ){
        				primaryKeys = primaryMap.get(key);
        			}
        		}
        		result = service.deleteBdszNrbcb(primaryKeys,bm,bdszBcnrb,user);
        		request.setAttribute("result", result);
        		request.setAttribute("message", result ? MESSAGE_SUCCESS : MESSAGE_FAIL);
        	}
        }
        
        // �����۲�����
        request.setAttribute("pjzq", pjzqService.getZczq());

        //ҳǩ�б�
        request.setAttribute("pages", titleList);
        bm = StringUtils.isNull(bm) ? (titleList != null && titleList.size() > 0 ? titleList.get(0).get("bm") : bm) : bm;
        request.setAttribute("bm", bm);
        request.setAttribute("dm", dm);
        request.setAttribute("shjb", shjb);
        
        //����дȨ��title��Ϣ�ŵ�request��
        appendTableXx(request, bm, StringUtils.isNotNull(bm) ? bm.substring(5,bm.length()) : "");
        appendOperQx(request, "pjpy_tyb_zhszcpSjwh.do?dmlb=" + dmlb + "&dm="+ dm);
        appendQryResult(request, topTr, rs);
        FormModleCommon.commonRequestSet(request);
        FormModleCommon.setNjXyZyBjListForFdyBzr(request);
        FormModleCommon.setNdXnXqList(request);
        request.setAttribute("dmlb", dmlb);
        request.setAttribute("dm", dm);
        request.setAttribute("chkList", service.getChkList(3));//����б�
        request.setAttribute("disli", getDisliValue(request.getParameter("disli"), titleList));//�Ƿ���ʾ������ť��־
        return mapping.findForward("query");
        
    }
    
    private String getDisliValue(String liValue, List<HashMap<String, String>> titleList) {
    	String sfwh = titleList != null && titleList.size() > 0
				&& titleList.get(0) != null ? titleList.get(0).get("sfwh") : "";
    	return StringUtils.isNull(liValue) ? (StringUtils.isNull(sfwh) ? "" : sfwh) : liValue;
    }
    
    /**
     * �ۺ����ʲ��������������Ӳ���
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
    	
    	PjpyZhszcpwhActionForm zcForm = (PjpyZhszcpwhActionForm) form;
    	setDefaultValue(zcForm);
    	PjpyZhszcpService service = new PjpyZhszcpService();
    	
    	//����
    	String bm = request.getParameter("bm");
    	//bm = StringUtil.isNull(bm) ? "" : bm.replace("view_", "");
    	
    	//�������
    	String dmlb = request.getParameter("dmlb");
    	//����
    	String dm = request.getParameter("dm");
    	
    	//��ŵ�ǰѧ�꣬ѧ�ڣ����
    	HashMap<String, String> rs = new HashMap<String, String>();
    	
    	String xh = request.getParameter("xh");
    	if (!StringUtils.isNull(xh)) {
    		rs.putAll(CommonQueryDAO.commonQueryOne("view_xsjbxx",
					new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
							"bjmc","xz" }, "xh", xh));
    	}
    	
    	//�������ݲ���
    	if (SAVE.equalsIgnoreCase(request.getParameter("act"))) {
    		//�Զ����ֶ��б�
			List<HashMap<String, String>> zdyzdMap = service.getZdyZdList(bm);
			//�ֶ��б�ֵ
			HashMap<String, String[]> valueMap = getParamterMapArray(request, zdyzdMap);
			
			PjpyZhszcpModel model = new PjpyZhszcpModel();
			BeanUtils.copyProperties(model, zcForm);
			model.setXh(xh);
			
			//���ڱ���ʧ�ܵ����ݽ��з��ز���ʾ
			boolean result = service.saveZhszcpInfo(model, valueMap, bm,
					zdyzdMap);
			request.setAttribute("result", result);
//			request.setAttribute("errmsg", "�������,���е�"
//					+ (StringUtils.isNotNull(result) ? result.substring(1,
//							result.length() - 1) : "")
//					+ "�����ݱ���ʧ��,���������ݿ��Ѵ��ڸü�¼.");
    	}
    	
    	
    	
    	rs.put("xn", Base.getJxjsqxn());
    	rs.put("xqmc", Base.getJxjsqxqmc());
    	rs.put("nd", Base.getJxjsqnd());
    	request.setAttribute("bm", bm);
    	request.setAttribute("rs", rs);
    	appendOperQx(request, "pjpy_tyb_zhszcpSjwh.do?dmlb=" + dmlb + "&dm=" + dm);
    	//�洢����ͼ��Ϣ
    	appendTableXx(request, bm, bm.substring(5,bm.length()));
    	request.setAttribute("dmlb", dmlb);
        request.setAttribute("dm", dm);
		return mapping.findForward("zhszcpAdd");
	}
    
    /**
     * �ۺ����ʲ������������޸Ĳ���
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward zhszcpModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	
    	PjpyZhszcpwhActionForm model = (PjpyZhszcpwhActionForm)form;
    	PjpyZhszcpService service = new PjpyZhszcpService();
    	BdszService bdszService = new BdszService();
    	//�������� �鿴�����
    	String act = request.getParameter("act");
    	//����
    	String pkValue = request.getParameter("pkValue");
    	//��ͼ����
    	String viewName = request.getParameter("bm");
    	//�������
    	String dmlb = request.getParameter("dmlb");
    	//����
    	String dm = request.getParameter("dm");
        //��˼���
        String shjb = request.getParameter("shjb");
    	//������ ͨ����ͼ���ƽ�ȡ
    	String tableName = StringUtils.isNotNull(viewName) 
    	                   ? viewName.replace("view_", "")
    	                   : "";   
    	//��ʼ��model�еĲ�������ֵ
    	appendDefault(model, request, shjb);
    	if(SAVE.equalsIgnoreCase(act)){//��Ϣ����
    		HashMap<String, String> tableMap = new HashMap<String, String>();
    		tableMap.put("tableName", tableName);
    		tableMap.put("viewName", viewName);
    		
    		HashMap<String, String> valueMap = new HashMap<String, String>();
    		List<HashMap<String, String>> zdyzd = bdszService.getZdydz(viewName, "pjpy");
    		valueMap = getParamterMap(request, zdyzd);//��ȡҳ���ֶ�ֵ
    		valueMap.putAll(getValueMap(request, PRIFIX_SAVE));//��ȡsaveǰ׺�ֶ�ֵ
    		valueMap.put("pkValue", pkValue);
    		//���渽����Ϣ
    		boolean result = service.saveZdyzdNrByFlag(viewName, new HashMap[]{valueMap});
        	request.setAttribute("result", result ? "yes" : "no");
    	}
    	//����������ѯ������
    	tableName = service.selectTableExists(tableName) ? tableName : viewName;
    	selectPageDataByOne(request,tableName, viewName, pkValue);
    	HashMap<String, String> rs =(HashMap<String, String>)request.getAttribute("rs");
    	rs.putAll(service.queryZdyzdNr(viewName, pkValue));//�Զ����ֶ���Ϣ
    	rs.putAll(CommonQueryDAO.commonQueryOne("view_xsjbxx",
				new String[] {"xz" }, "xh", rs.get("xh")));
    	
    	//���ֶ���Ϣ,����ҳ���Զ����ǩ��ʾ���� 
    	bdszService.getBdZd(viewName, "pjpy", model);    	
    	request.setAttribute("act", act);
    	appendOperQx(request, "pjpy_tyb_zhszcpSjwh.do?dmlb=" + dmlb + "&dm=" + dm);
    	//�洢����ͼ��Ϣ
    	appendTableXx(request, viewName, viewName);
    	request.setAttribute("dmlb", dmlb);
        request.setAttribute("dm", dm);
    	FormModleCommon.commonRequestSet(request);
    	request.setAttribute("shjb", shjb);
    	request.setAttribute("bm", viewName);
    	return mapping.findForward("zhszcpModi");
    }
    
    
    /**
     * �ۺ����ʲ������
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward zhszcpSjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	
    	PjpyZhszcpwhActionForm actionForm = (PjpyZhszcpwhActionForm) form;
    	PjpyZhszcpService service = new PjpyZhszcpService();
        PjpyPjzqszService pjzqService = new PjpyPjzqszService(); 
        PjpyZctjszService tjszService = new PjpyZctjszService();        
        
        //��������
        String act = request.getParameter("act");
        //��Ŀ���뼶��,2,3,4��
        String dmlb = request.getParameter("dmlb");
        //��Ŀ����
        String dm = request.getParameter("dm");
        //����
        String bm = request.getParameter("bm");
        //��˼���
        String shjb = tjszService.queryZhszcpxmShjb(dm, bm);
        
        User user = getUser(request);
        
        if (UserTypePd.isXy(user.getUserType()) && !UserTypePd.isFdy(user.getIsFdy())) {
        	actionForm.setXydm(user.getUserDep());
        }
        
        List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>(); 
        List<String[]> rs = new ArrayList<String[]>();
        List<HashMap<String, String>> titleList = tjszService.queryZcDmxxList(dm);
        
        //ѧԺ�û�Ĭ�ϲ�ѯ�Լ�ѧԺ����
        appendDefault(actionForm, request, shjb); 
        //���
        if(SH_TG.equalsIgnoreCase(act) || SH_BTG.equalsIgnoreCase(act)){
        	//������ͼ����ȡ������
        	String tableName = bm.substring(5,bm.length());
        	//������Ϣ
        	HashMap<String, String[]> primaryMap = getValueArrayMap(request, PRIFIX_PRIMARY_KEY);
        	//��ȡ����ֶκ��ֶ�ֵ
        	HashMap<String, String> valueMap = new HashMap<String, String>();
        	valueMap = getZhszcpShzdxx(actionForm, shjb, act);
        	//��˲���
        	auditingBatchOperation(request, primaryMap, valueMap, tableName);
        }
        if (QUERY.equalsIgnoreCase(act)) {
        	//��ѯ���ݲ���
        	PjpyZhszcpModel model = new PjpyZhszcpModel();
    		BeanUtils.copyProperties(model, actionForm);
    		
    		//Ĭ�ϵ���˲�ѯ����
    		appendShtj(model,request,shjb);
    		
			// �Զ����ֶ��б�
			HashMap<String, String[]> zdyzdMap = service.getZdyzdMap(bm);
			
			//��ѯ������ֶ���Ϣ
			BasicService basicService = new BasicService();
			String[] zbzdArray = basicService.getTableColumn(bm);
			
			// ��ѯ��ʾ�ֶ��б�
			HashMap<String, String[]> cxzdMap = service.getPjpyCxzdMap(bm,shjb);
			topTr = service.getZhszcpTitle(cxzdMap);
			rs = service.queryZhszcpResult(model, cxzdMap,zdyzdMap, bm, dm, zbzdArray);
		}
        
        // ������������
        request.setAttribute("pjzq", pjzqService.getPjzq());

        //ҳǩ�б�
        request.setAttribute("pages", titleList);
        request.setAttribute("bm", bm);
        request.setAttribute("dm", dm);
        shjb = StringUtils.isNull(shjb) ? (titleList != null && titleList.size() > 0 ? titleList.get(0).get("shjb") : shjb) : shjb;
        request.setAttribute("shjb", shjb);
        
        
        //����дȨ��title��Ϣ�ŵ�request��
        appendTableXx(request, bm, bm);
        appendOperQx(request, "pjpy_tyb_zhszcpSjsh.do?dmlb=" + dmlb + "&dm=" + dm);// ����
        appendQryResult(request, topTr, rs);
        FormModleCommon.commonRequestSet(request);
        FormModleCommon.setNjXyZyBjListForFdyBzr(request);
        FormModleCommon.setNdXnXqList(request);
        request.setAttribute("dmlb", dmlb);
        request.setAttribute("dm", dm);
        //����б�
        request.setAttribute("chkList", service.getChkList(3));
    
		return mapping.findForward("zhszcpSh");
	}
    
    /**
     * �ۺ����ʲ���������¼���
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward zhszcpShOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	
    	PjpyZhszcpwhActionForm model = (PjpyZhszcpwhActionForm)form;
    	PjpyZhszcpService service = new PjpyZhszcpService();
    	BdszService bdszService = new BdszService();
    	//�������� �鿴�����
    	String act = request.getParameter("act");
    	//����
    	String pkValue = request.getParameter("pkValue");
    	//��ͼ����
    	String viewName = request.getParameter("bm");
    	//�������
    	String dmlb = request.getParameter("dmlb");
    	//����
    	String dm = request.getParameter("dm");
        //��˼���
        String shjb = request.getParameter("shjb");
    	//������ ͨ����ͼ���ƽ�ȡ
    	String tableName = StringUtils.isNotNull(viewName) 
    	                   ? viewName.substring(5,viewName.length())
    	                   : "";   
    	//��ʼ��model�еĲ�������ֵ
    	appendDefault(model, request, shjb);
    	
    	if(SAVE.equalsIgnoreCase(act)){//��Ϣ����
        	//������Ϣ
        	HashMap<String, String[]> primaryMap = new HashMap<String, String[]>();
        	primaryMap.put("cbv", new String[]{pkValue});
        	//��ȡ����ֶκ��ֶ�ֵ
        	HashMap<String, String> valueMap = new HashMap<String, String>();
        	valueMap = getValueMap(request, PRIFIX_SAVE);
        	valueMap.put(getShzd(model, shjb)+"sj", GetTime.getNowTime2());//���ʱ��
        	//��˲���
        	auditingBatchOperation(request, primaryMap, valueMap, tableName);
    	}
    	
    	//����������ѯ������
    	selectPageDataByOne(request,tableName, viewName, pkValue);
    	HashMap<String, String> rs =(HashMap<String, String>)request.getAttribute("rs");
    	rs.putAll(service.queryZdyzdNr(viewName, pkValue));//�Զ����ֶ���Ϣ
    	rs.putAll(CommonQueryDAO.commonQueryOne("view_xsjbxx",
				new String[] {"xz" }, "xh", rs.get("xh")));
    	
    	//���ֶ���Ϣ,����ҳ���Զ����ǩ��ʾ���� 
    	bdszService.getBdZd(viewName, "pjpy", model);
    	
    	request.setAttribute("act", act);
    	//��������б�����
    	request.setAttribute("chkList", service.getChkList(3));
    	appendOperQx(request, "pjpy_tyb_zhszcpSjsh.do?dmlb=" + dmlb + "&dm=" + dm);
    	//�洢����ͼ��Ϣ
    	appendTableXx(request, viewName, viewName);
    	request.setAttribute("shjb", shjb);
    	request.setAttribute("bm", viewName);
    	request.setAttribute("dmlb", dmlb);
        request.setAttribute("dm", dm);
        appendOperQx(request, "pjpy_tyb_zhszcpSjsh.do?dmlb=" + dmlb + "&dm=" + dm);// ����
        FormModleCommon.commonRequestSet(request);
    	return mapping.findForward("zhszcpShOne");
    }
    
    
    /**
     * �����������
     * @param request
     * @param shjb
     * */
    public void appendShtj(PjpyZhszcpModel model,HttpServletRequest request,String shjb){
    	String userType = getSessionAttributeValue(request, "userType");
    	String userDep = getSessionAttributeValue(request, "userDep");
    	if("3".equalsIgnoreCase(shjb)){//�������
    		if ("xy".equalsIgnoreCase(userType)
					&& !"true".equalsIgnoreCase(getSessionAttributeValue(
							request, "isFdy"))) {
				model.setFdysh("ͨ��");
				model.setXydm(userDep);
				model.setQueryequals_xydm(userDep);
			} else if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {
				model.setXysh("ͨ��");
			}
    	}
    	if("2".equalsIgnoreCase(shjb)){//�������
    		if(!"xy".equalsIgnoreCase(userType)){
    			model.setXysh("ͨ��");
    		}else {
    			model.setXydm(userDep);
    			model.setQueryequals_xydm(userDep);
    		}
    	}
    	model.setShjb(new String[]{shjb});    	
    }
    
    /**
     * �����û������Ϣ
     * @param model
     * @param request
     * */
    public void addpendUserInfo(PjpyZhszcpwhActionForm model,HttpServletRequest request){
    	String userType = getSessionAttributeValue(request, "userType");
    	//�û���Ϣ���ӵ�form��
    	model.setUserType(userType);
    	model.setUserName(getSessionAttributeValue(request, "userName"));
    	model.setIsFdy(getSessionAttributeValue(request, "isFdy"));
    }
    
    /**
     * ����Ĭ������
     * @param request
     * @param shjb
     * */
    public void appendDefault(PjpyZhszcpwhActionForm model,HttpServletRequest request,String shjb){
    	String userType = getSessionAttributeValue(request, "userType");
    	String userDep = getSessionAttributeValue(request, "userDep");
    	//�û���Ϣ���ӵ�form��
    	addpendUserInfo(model, request);    	
    	
    	if("3".equalsIgnoreCase(shjb)){//�������
    		if("xy".equalsIgnoreCase(userType) && "false".equalsIgnoreCase(getSessionAttributeValue(request, "isFdy"))){    			
    			model.setXydm(userDep);
    			model.setQueryequals_xydm(userDep);
    		}
    	}
    	if("2".equalsIgnoreCase(shjb)){//�������
    		if(!"xy".equalsIgnoreCase(userType)){
    			model.setXysh("ͨ��");
    		}else {
    			model.setXydm(userDep);
    			model.setQueryequals_xydm(userDep);
    		}
    	}
    	
    }
    
     /**
     * ��ȡ����ֶ���Ϣ
     * @param actionForm
     * @param shjb
     * @param act
     * @return HashMap<String, String>
     * */
   public HashMap<String, String> getZhszcpShzdxx(PjpyZhszcpwhActionForm actionForm, 
		                                          String shjb, 
		                                          String act){
	   
	   HashMap<String, String> valueMap = new HashMap<String, String>();
	   act = SH_TG.equalsIgnoreCase(act) ? "ͨ��" : "��ͨ��";//��˽��
	   String shzd = getShzd(actionForm, shjb);//��ȡ����ֶ�
	   
	   valueMap.put(shzd, act);//��˽��
	   valueMap.put(shzd+"sj", GetTime.getNowTime2());//���ʱ��
	   
	   return valueMap;
   }
   
   /**
    * ��ȡ��ǰ�û�Ҫ����������ֶ�
    * @param actionForm
    * @param shjb
    * @return String
    * */
   public String getShzd(PjpyZhszcpwhActionForm actionForm, 
           				 String shjb){
	   String isFdy = actionForm.getIsFdy();//����Ա
	   String userType = actionForm.getUserType();//�û�����
	   String shzd = "xxsh";//Ĭ��ΪѧУ
	   
	   
	   if("3".equalsIgnoreCase(shjb)){//�������
		   if(UserTypePd.isFdy(isFdy)){//����Ա
			   shzd = "fdysh";
		   }else if(UserTypePd.isXy(userType)){//ѧԺ
			   shzd = "xysh";
		   }
	   }
	   if("2".equalsIgnoreCase(shjb)){//�������
		   if(UserTypePd.isXy(userType)){//ѧԺ
			   shzd = "xysh";
		   }
	   }
	   return shzd;
   }
   
   /**
    * ��ȡҳ���Զ����ֶε�ֵ
    * @param request
    * @param zdxxList
    * @return HashMap<String, String>
    * */
   public HashMap<String, String> getParamterMap(HttpServletRequest request,
			List<HashMap<String, String>> zdxxList) {
	   HashMap<String, String> map = new HashMap<String, String>();
	   for(HashMap<String, String> zdxx : zdxxList){
		   map.put(zdxx.get("zdid"), request.getParameter(zdxx.get("zdid")));
	   }
	   return map;
   }
   
   /**
    * ��ȡҳ���Զ����ֶε�����ֵ  
    * @param request
    * @param zdxxList
    * @return HashMap<String, String>
    * */
   public HashMap<String, String[]> getParamterMapArray(
			HttpServletRequest request, List<HashMap<String, String>> zdxxList) {
	   HashMap<String, String[]> map = new HashMap<String, String[]>();
	   for(HashMap<String, String> zdxx : zdxxList){
		   map.put(zdxx.get("zdid"), request.getParameterValues(zdxx.get("zdid")));
	   }
	   return map;
   }
    
    /**
     * �ۺ����ʲ����Զ����ֶ�չ��
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward zhszcpZdzx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
    	
    	PjpyZhszcpwhActionForm tybForm = (PjpyZhszcpwhActionForm) form;
    	
    	PjpyZctjszService tjszService = new PjpyZctjszService();
    	PjpyZhszcpService service = new PjpyZhszcpService();
    	
    	//����
    	String bm = request.getParameter("bm");  
    	String dm = request.getParameter("dm");
    	String dmlb = request.getParameter("dmlb");
    	String xh = request.getParameter("xh");
    	
    	//�������ݲ���
    	if (SAVE.equalsIgnoreCase(request.getParameter("act"))) {
    		//��ȡ��������
    		//String realTable = StringUtils.isNotNull(bm) ? bm.substring(5, bm.length()) : "";
    		//�Զ����ֶ��б�
			List<HashMap<String, String>> zdyzdMap = service.getZdyZdList(bm);
			//�ֶ��б�ֵ
			HashMap<String, String[]> valueMap = getParamterMapArray(request, zdyzdMap);
			
			PjpyZhszcpModel model = new PjpyZhszcpModel();
			BeanUtils.copyProperties(model, tybForm);
			
			boolean result = service.saveZhszcpInfo(model, valueMap, bm,
					zdyzdMap);
			//���ڱ���ʧ�ܵ����ݽ��з��ز���ʾ
			request.setAttribute("result",result);
//			request.setAttribute("errmsg", "�������,���е�"
//					+ (StringUtils.isNotNull(result) ? result.substring(0,
//							result.length() - 1) : "")
//					+ "�����ݲ���ʧ��,���������ݿ��Ѵ��ڸü�¼.");
    	}
    		
    	
    	//�Զ����ֶ������б� ���������ͷ
    	List<HashMap<String, String>> rs = tjszService.queryZhszcpxm( bm); 
    	request.setAttribute("rs", rs);
    	
    	//����ѯ�������Զ����ֶη�װ��JSON���ݶ���
    	request.setAttribute("jsonStr", service.appendJsOjbectByZdyzdxx(rs));
    	
    	request.setAttribute("dm", dm);
    	request.setAttribute("bm", bm);
    	request.setAttribute("dmlb", dmlb);
    	request.setAttribute("xh", xh);
    	return mapping.findForward("zhszcpZdzx");
    }
    
    /**
     * �۲��ܷ�ͳ�Ʋ�ѯ�����
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward zhszcpHz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
    	PjpyZhszcpwhActionForm tybForm = (PjpyZhszcpwhActionForm)form;
    	PjpyZhszcpService service = new PjpyZhszcpService();
    	PjpyPjzqszService pjzqService = new PjpyPjzqszService();
//    	PjpyZctjszService tjszService = new PjpyZctjszService();
    	String zczq = pjzqService.getZczq();//��ȡ�۲�����
  
    	List<String[]> list = new ArrayList<String[]>();
    	List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
    	
    	 User user = getUser(request);
         
         if (UserTypePd.isXy(user.getUserType())) {
        	 tybForm.setQueryequals_xydm(user.getUserDep());
         } else if (GlobalsVariable.XTDM_STU
				.equalsIgnoreCase(user.getUserType())
				|| GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(user
						.getUserType())) {
			request.setAttribute("errMsg", "�Բ���,����Ȩ���ʴ�ҳ��");
			return new ActionForward("/errMsg.do",false);
		}
         
    	
    	if (QUERY.equalsIgnoreCase(request.getParameter("act"))) {
    		HashMap<String, String> map = new HashMap<String, String>();
    		map.put("2", request.getParameter("queryequals_ejdm"));//����	
    		map.put("3", request.getParameter("queryequals_sjdm"));//��������	
    		map.put("4", request.getParameter("queryequals_sidm"));//�ļ�����
    		String jb = tybForm.getQueryequals_jb();
    		
    		HashMap<String, String[]> colMap = service.getZhszcpzfExpTitle(zczq,
    				jb, map, true, tybForm);//��ȡ�������ֶ���Ϣ
    		String[] columnCN = colMap.get("cn");//��ͷ

    		//��ѯ����������
    		list = service.queryZhszcpzfForExp(zczq, jb, map, colMap
    				.get("en"), tybForm, true);

    		topTr = service.queryZhszcpTitle(columnCN);
    		//this.selectPageDataByPagination(request, form, "pjpy_zhszcpb", "view_pjpy_zhszcpb", new String[]{"pkValue", "�к�", "nd", "xn", "xq", "xh", "xm", "bjmc", "mc", "fs", "pm"});
    	}
    	
    	//��ѯ���е��۲������Ϣ
    	List<HashMap<String, String>> dmList = service.queryZhszcpdmList();
    	//���಻ͬ����Ĵ�����Ϣ
    	String ejdm = tybForm.getQueryequals_ejdm();
    	String sjdm = tybForm.getQueryequals_sjdm();
    	request.setAttribute("ejdmList", service.queryZhszcpdmList(dmList, "2"));//����
    	request.setAttribute("sajdmList",
				StringUtils.isNotNull(ejdm) ? service.queryZhszcpdmList("3", ejdm)
						: service.queryZhszcpdmList(dmList, "3"));// ����
    	request.setAttribute("sijdmList",
				StringUtils.isNotNull(sjdm) ? service.queryZhszcpdmList("4", sjdm)
						: service.queryZhszcpdmList(dmList, "4"));//�ļ�
    	
    	//����ѧԺ��רҵ���༶���꼶��ѧ�꣬ѧ�ڣ�����б�
    	FormModleCommon.setNjXyZyBjListForFdyBzr(request);
    	FormModleCommon.setNdXnXqList(request);
    	
    	//�����۲�����
        request.setAttribute("pjzq", zczq);
        request.setAttribute("pjpyzq", pjzqService.getPjzq());//������������
        appendOperQx(request, "pjpy_tyb_zhszcp.do");
        
        //����Ĭ�ϵ�ѧ�꣬ѧ�ڣ����ѡ��
        setDefaultValue(tybForm);
        appendTableXx(request, "view_pjpy_zhszcpb", "pjpy_zhszcpb");
        
        appendQryResult(request, topTr, list);
        
    	return mapping.findForward("zhszcpHz");
    }

    //����Ĭ�ϵ�ѧ�꣬ѧ�ڣ����ѡ��
	private void setDefaultValue(PjpyZhszcpwhActionForm tybForm) {
		if (StringUtils.isNull(tybForm.getQueryequals_xn())) {
        	tybForm.setQueryequals_xn(Base.getJxjsqxn());
        }
        if (StringUtils.isNull(tybForm.getQueryequals_nd())) {
        	tybForm.setQueryequals_nd(Base.getJxjsqnd());
        }
        if (StringUtils.isNull(tybForm.getQueryequals_xq())) {
        	tybForm.setQueryequals_xq(Base.getJxjsqxq());
        }
        if (StringUtils.isNull(tybForm.getXn())) {
        	tybForm.setXn(Base.getJxjsqxn());
        }
        if (StringUtils.isNull(tybForm.getXq())) {
        	tybForm.setXq(Base.getJxjsqxq());
        }
        if (StringUtils.isNull(tybForm.getNd())) {
        	tybForm.setNd(Base.getJxjsqnd());
        }
	}
    
	/**
	 * �Զ������۲��ܷ���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward countZccj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		PjpyZhszcpwhActionForm tybForm = (PjpyZhszcpwhActionForm) form;
		PjpyZhszcpService service = new PjpyZhszcpService();
		PjpyZhszcpModel model = new PjpyZhszcpModel();
		setDefaultValue(tybForm);
		
		User user = getUser(request);
        if (UserTypePd.isXy(user.getUserType())) {
        	 tybForm.setQueryequals_xydm(user.getUserDep());
        }
		
		BeanUtils.copyProperties(model, tybForm);
		model.setYjdm(ZHSZCP_YJDM);
		model.setPjzq(request.getParameter("pjpyzq"));
		model.setZczq(request.getParameter("pjzq"));
		
		//�Զ�����
		boolean result = service.countZhszcpzf(model);
		request.setAttribute("result", result);
		
		zhszcpHz(mapping, form, request, response);
		return mapping.findForward("zhszcpHz");
	}
	
	/**
     * �ۺ����ʲ����ֵܷ���
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward zhszcpzfExport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyZhszcpwhActionForm model = (PjpyZhszcpwhActionForm) form;
		PjpyPjzqszService pjzqService = new PjpyPjzqszService();
		PjpyZhszcpService service = new PjpyZhszcpService();
		
		User user = getUser(request);
         
         if (UserTypePd.isXy(user.getUserType())) {
        	 model.setQueryequals_xydm(user.getUserDep());
         }
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("2", request.getParameter("queryequals_ejdm"));//����	
		map.put("3", request.getParameter("queryequals_sjdm"));//��������	
		map.put("4", request.getParameter("queryequals_fjdm"));//�ļ�����
		String jb = model.getQueryequals_jb();

		String pjzq = pjzqService.getPjzq();
		HashMap<String, String[]> colMap = service.getZhszcpzfExpTitle(pjzq,
				jb, map, false, model);//��ȡ�������ֶ���Ϣ
		String[] columnCN = colMap.get("cn");//��ͷ

		//��ѯ����������
		List<String[]> list = service.queryZhszcpzfForExp(pjzq, jb, map, colMap
				.get("en"), model, false);

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(list, columnCN, columnCN, response.getOutputStream());
		return mapping.findForward("");
	}
    
   /**
     * �ۺϲ����ɼ�ͳ��
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward zccjtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	PjpyTyService service = new PjpyTyService();
    	PjpyZhszcpwhActionForm myForm = (PjpyZhszcpwhActionForm)form;
    	myForm.setQueryequals_nd(Base.getJxjsqnd());
		myForm.setQueryequals_xn(Base.getJxjsqxn());
		myForm.setQueryequals_xq(Base.getJxjsqxq());
		
    	//ѧԺרҵ�༶�꼶�б�
    	FormModleCommon.setNjXyZyBjListForFdyBzr(request);
    	//ѧ�����ѧ���б�
		FormModleCommon.setNdXnXqList(request);
    	//��������
    	request.setAttribute("pjzq", service.getZhcpSqzq());
    	request.setAttribute("path", "zccjtj.do");
    	FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zccjtj");
	}    
    
    /**
     * �ۺϲ����ɼ�ͳ�Ʊ����ӡ
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward printZhcpcjtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	PjpyZhszcpwhActionForm myForm = (PjpyZhszcpwhActionForm)form;
    	String type = request.getParameter("type");//��Ӧ�ı���
    	PjpyNtzydxService ntzyService = new PjpyNtzydxService();
    	
    	response.reset();
		response.setContentType("application/vnd.ms-excel");
		//��ӡ�ۺϲ���ͳ�Ʊ�
		ntzyService.printZhcptjbb(myForm,type,response.getOutputStream());
		return mapping.findForward("");
	}
   
    /**
     * ����ѧ������Ƭ��Ϣ
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward picexport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
    	
    	ImageExportUtils img = new ImageExportUtils();
    	PjpyZhszcpService service = new PjpyZhszcpService();
    	//String[] xhArray = service.queryStuXh("302063101");
    	String[] xhArray = {"3060823107",
    			"3060601049",
    			"3060831028",
    			"3060902142",
    			"3061401031",
    			"3061511032",
    			"3061511038",
    			"3061904047",
    			"3063027071",
    			"3063027090",
    			"3063027096",
    			"3070902103",
    			"3070911004",
    			"3070921069",
    			"3071001249",
    			"3071401037",
    			"3071511047",
    			"3071511052",
    			"3071511054",
    			"3071511056",
    			"3080102424",
    			"3080102998"
    		};
    	img.stuImageExport(xhArray, response);
    	
    	return mapping.findForward("");
    }
}
