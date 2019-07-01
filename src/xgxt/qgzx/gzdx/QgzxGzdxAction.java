package xgxt.qgzx.gzdx;

import xgxt.qgzx.QgzxTyForm;
import xgxt.studentInfo.service.XsxxglService;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.qgzx.service.QgzxService;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ���ݴ�ѧ�ڹ���ѧ-action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ���ΰ
 * @version 1.0
 */

public class QgzxGzdxAction extends BasicAction {
    /**
    * ���������޸�
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
    * */
    public ActionForward jjfzModi(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response){
        XsxxglService xsxxglService = new XsxxglService();
        HashMap<String, String> rs = new HashMap<String, String>();
        String type = request.getParameter("type");

        if("save".equalsIgnoreCase(type)){//��Ϣ�޸�
                updateOperation(request, "qgzx_pjpy_jjfzb");
        }

        String pkValue = request.getParameter("pkValue");
        selectPageDataByOne(request, "qgzx_pjpy_jjfzb", "view_qgzx_pjpy_jjfzb", pkValue);
        rs = (HashMap<String, String>) request.getAttribute("rs");
        rs.putAll(xsxxglService.selectStuinfo(rs.get("xh")));//��ѯѧ��������Ϣ
        request.setAttribute("rs", rs);

        FormModleCommon.commonRequestSet(request);//��дȨ��Ϣ
        request.setAttribute("type", type);
        return mapping.findForward("jjfzModi");
    }


	/**
    * ���������걨
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
    * */
    public ActionForward jjfzsb(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        QgzxTyForm model = (QgzxTyForm)form;
        QgzxGzdxService service = new QgzxGzdxService();
        String userName = session.getAttribute("userName").toString();
        String go = request.getParameter("go");
        String type = request.getParameter("type");
        
        if("go".equalsIgnoreCase(go)){//��ѯ����
        	model.setUserName(userName);
            String[] outputColumn = { "pkValue","xh", "xm", "xb", "xn", "nd", "xqmc", "nj", "xymc", "bjmc", "�Ƿ����걨"};
            List<String[]> rs = service.queryYgbmxsList(model, outputColumn);
            request.setAttribute("topTr", service.getTopTr("view_xsgwxx",outputColumn));
            request.setAttribute("rs", rs);
            request.setAttribute("rsNum", rs.size());
        }
        if("save".equalsIgnoreCase(type)){
        	//�걨����
        	boolean result = service.saveQgzxjjfzsb(model);
        	request.setAttribute("result", result);
        }

        FormModleCommon.commonRequestSet(request);//����дȨ��title��Ϣ�ŵ�request��
        //����ѧԺרҵ�༶�б���Ϣ
        FormModleCommon.setNjXyZyBjListForFdyBzr(request,
                                                 model.getQueryequals_xydm(),
                                                 model.getQueryequals_zydm(),
                                                 model.getQueryequals_bjdm(),
                                                 model.getQueryequals_nj());
        FormModleCommon.setNdXnXqList(request);
        return mapping.findForward("jjfzsb");
    }
    
    /**
     * ���������걨(���˵�λ����)
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * */
     public ActionForward jjfzsbByYrdw(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response){
         HttpSession session = request.getSession();
         QgzxTyForm model = (QgzxTyForm)form;
         QgzxGzdxService service = new QgzxGzdxService();
         QgzxService qgzxService=new QgzxService();
         String userName = session.getAttribute("userName").toString();
         String go = request.getParameter("go");
         String type = request.getParameter("type");
         
         if("go".equalsIgnoreCase(go)){//��ѯ����
         	model.setUserName(userName);
             String[] outputColumn = { "pkValue","xh", "xm", "xb", "xn", "nd", "xqmc", "nj", "xymc", "bjmc","yrdwmc", "�Ƿ����걨"};
             List<String[]> rs = service.queryYgbmxs(model, outputColumn);
             request.setAttribute("topTr", service.getTopTr("view_xsgwxx",outputColumn));
             request.setAttribute("rs", rs);
             request.setAttribute("rsNum", rs.size());
         }
         if("save".equalsIgnoreCase(type)){
         	//�걨����
         	boolean result = service.saveQgzxjjfzsb(model);
         	request.setAttribute("result", result);
         }
         
         if(qgzxService.isYrdwUser(userName)){//�Ƿ����˵�λ
        	 model.setSqdw(qgzxService.getYrdwUser(userName));
        	 request.setAttribute("yrdwdm",model.getSqdw());
         }
         request.setAttribute("path", "gzdxQgzx.do?method=jjfzsbByYrdw");
         FormModleCommon.commonRequestSet(request);//����дȨ��title��Ϣ�ŵ�request��
         //�������˵�λ
         request.setAttribute("isYrdwyh", qgzxService.isYrdwUser(userName));
         request.setAttribute("yrdwList",service.getYrdwList());
         FormModleCommon.setNjXyZyBjListForFdyBzr(request);
         FormModleCommon.setNdXnXqList(request);
         return mapping.findForward("jjfzsbByYrdw");
     }
	    
    /**
    * �������ӽ����ѯ
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
    * */
    public ActionForward jjfzjgcx(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        QgzxTyForm model = (QgzxTyForm)form;
        String userOnLine = session.getAttribute("userOnLine").toString();
        String userName = session.getAttribute("userName").toString();
        String go = request.getParameter("go");
        String type = request.getParameter("type");
        String tableName = "qgzx_pjpy_jjfzb";
        String viewName = "view_qgzx_pjpy_jjfzb";

        if ("xy".equalsIgnoreCase(session.getAttribute("userType").toString())
            && !"true".equalsIgnoreCase(session.getAttribute("fdyQx").toString())) {
            model.setQueryequals_xydm(session.getAttribute("userDep").toString());
        }

        if("student".equalsIgnoreCase(userOnLine)){//ѧ����½
            model.setQuerylike_xh(userName);
        }

        if("go".equalsIgnoreCase(go)){//��ѯ����
            String[] outputColumn = {"pkValue", "xh", "xm", "xb", "xn", "nd", "xqmc", "xymc", "bjmc", "sbsj", "fdysh", "xysh", "xxsh"};
            selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
        }

        if("del".equalsIgnoreCase(type)){//��Ϣɾ��
            deleteOperation(request, tableName);
        }

        FormModleCommon.commonRequestSet(request);//����дȨ��title��Ϣ�ŵ�request��
        //����ѧԺרҵ�༶�б���Ϣ
        FormModleCommon.setNjXyZyBjListForFdyBzr(request,
                                                 model.getQueryequals_xydm(),
                                                 model.getQueryequals_zydm(),
                                                 model.getQueryequals_bjdm(),
                                                 model.getQueryequals_nj());
        FormModleCommon.setNdXnXqList(request);
        request.setAttribute("realTable", tableName);
        return mapping.findForward("jjfzjgcx");
    }

    /**
    * ����������Ϣ����
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
    * @throws Exception
    * */
    public ActionForward jjfzExp(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        expPageData(request, response, "qgzx_pjpy_jjfzb", "view_qgzx_pjpy_jjfzb", null);
        return mapping.findForward("");
    }

    /**
    * �����������
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
    * */
    public ActionForward jjfzSh(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        QgzxTyForm model = (QgzxTyForm)form;
        QgzxGzdxService service = new QgzxGzdxService();
        String userOnLine = session.getAttribute("userOnLine").toString();
        String userName = session.getAttribute("userName").toString();
        String userDep = session.getAttribute("userDep").toString();
        String userType = session.getAttribute("userType").toString();
        String go = request.getParameter("go");
        String type = request.getParameter("type");
        String tableName = "qgzx_pjpy_jjfzb";
        String viewName = "view_qgzx_pjpy_jjfzb";
        String shzd = "xxsh";
        String shjg = request.getParameter("shjg");
        String annexTerm = "";
        String yhlx = "";

        if ("xy".equalsIgnoreCase(userType) && !session.getAttribute("fdyQx").equals(true)) {//ѧԺ���
            model.setQueryequals_xydm(userDep);
            shzd = "xysh";
            yhlx = "xy";
            annexTerm = " and fdysh = 'ͨ��' ";
	    }else if("xx".equalsIgnoreCase(userType) 
	    		|| "admin".equalsIgnoreCase(userType)){//ѧУ�û�
	    	annexTerm = " and xysh = 'ͨ��'";
	    }
        if (session.getAttribute("fdyQx").equals(true)) {//����Ա���
            shzd = "fdysh";
            yhlx = "fdy";
        }

        if("go".equalsIgnoreCase(go)){//��ѯ����
            String[] outputColumn = {"disabled", "bgcolor", "pkValue", "xh", "xm", "xb", "xn", "nd", "xqmc", "nj", "xymc", "bjmc", "sbsj", "fdysh", "xysh", "xxsh"};
            request.setAttribute("clientColumns", service.getCustomAudiColumn(yhlx));
            request.setAttribute("annexTerm", annexTerm);
            selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
        }

        if("save".equalsIgnoreCase(type)){//�����Ϣ����
           	request.setAttribute("shzd",shzd);
           	request.setAttribute("shjg",shjg);
           	auditingBatchOperation(request, tableName);
        }

        FormModleCommon.commonRequestSet(request);//����дȨ��title��Ϣ�ŵ�request��
        //����ѧԺרҵ�༶�б���Ϣ
        FormModleCommon.setNjXyZyBjListForFdyBzr(request,
                                                 model.getQueryequals_xydm(),
                                                 model.getQueryequals_zydm(),
                                                 model.getQueryequals_bjdm(),
                                                 model.getQueryequals_nj());
        FormModleCommon.setNdXnXqList(request);
        return mapping.findForward("jjfzSh");
    }

    /**
    * �������ӵ������
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
    * */
    public ActionForward jjfzShOne(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response){
        XsxxglService xsxxglService = new XsxxglService();
        QgzxGzdxService service = new QgzxGzdxService();
        HashMap<String, String> rs = new HashMap<String, String>();
        String type = request.getParameter("type");//��������
        String pkValue = request.getParameter("pkValue");//����
        String tableName = "qgzx_pjpy_jjfzb";//������
        String viewName = "view_qgzx_pjpy_jjfzb";//��ͼ����
        String pjxn=Base.getJxjsqxn();//����ѧ��
        if("save".equalsIgnoreCase(type)){//��Ϣ�޸�
        	String[] pkArr = new String[]{pkValue};
        	HashMap<String, String[]> primaryMap = new HashMap<String, String[]>();
        	primaryMap.put("chkVal", pkArr);
        	
        	HashMap<String, String> valueMap = new HashMap<String, String>(); 
        	valueMap = getValueMap(request, PRIFIX_SAVE);
        	auditingBatchOperation(request, primaryMap, valueMap, tableName);
        }
        
        selectPageDataByOne(request, tableName, viewName, pkValue);
        rs = (HashMap<String, String>) request.getAttribute("rs");
        rs.putAll(xsxxglService.selectStuinfo(rs.get("xh")));//��ѯѧ��������Ϣ
        request.setAttribute("rs", rs);

        FormModleCommon.commonRequestSet(request);//��дȨ��Ϣ
        request.setAttribute("qgzxgw", service.getQgzxgw(rs.get("xh")));
        request.setAttribute("zcpm", service.getZcpm(pjxn, rs.get("xh")));
        request.setAttribute("bjgkms", service.getBjgkms(pjxn, rs.get("xh")));
        request.setAttribute("wjcs", service.getWjcs(pjxn, rs.get("xh")));
        request.setAttribute("type", type);
        request.setAttribute("chkList", service.getChkList(3));//���״̬�б�
        request.setAttribute("type", type);
        return mapping.findForward("jjfzShOne");
    }



	/**
	 * ��ʱ��λ - ��λ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsgwfpManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		QgzxService qzzxService = new QgzxService();
		QgzxGzdxService service = new QgzxGzdxService();
		QgzxTyForm myForm = (QgzxTyForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// �ڹ���ѧ��ز�������
		HashMap<String, String> qgzxMap = qzzxService.getSqsjInfo();
		
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û�����
		String userDep = (String) session.getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_xsjbxx";
		// ����
		String realTable = "gzdx_lsgwb";
		// ����·��
		String path = "qgzx_lsgw_fp.do";
		// �ڹ�ѧ��
		String xn = qgzxMap.get("xn");
		myForm.setQueryequals_xn(xn);
		myForm.setXn(xn);
		// �ڹ�ѧ��
		String xq = qgzxMap.get("xq");
		myForm.setQueryequals_xq(xq);
		myForm.setXq(xq);
		// �ڹ����
		String nd = qgzxMap.get("nd");
		myForm.setQueryequals_nd(nd);
		myForm.setNd(nd);
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================

		// =================Ȩ�޿��� ==================
		if (qzzxService.isYrdwUser(userName)) {
			//�ж��Ƿ����˵�λ
			myForm.setUserName(userName);
		}else{
			myForm.setUserName(null);
		}

		if ("xy".equalsIgnoreCase(userType)) {
			// ѧԺ
			myForm.setQueryequals_xydm(userDep);
			myForm.setXydm(userDep);
		}
		// =================end==================

		// =================ִ�б������ ==================
		if ("save".equalsIgnoreCase(doType)) {

			 boolean result = service.saveLsgw(myForm, realTable);

			 request.setAttribute("result", result);
		}
		// =================end==================

		// ==================ִ�в�ѯ���� ==================
		if (search) {
			String[] outputColumn = { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		// �����ֶ�
		String[] qtzd = new String[] { "xn", "xq", "nd" };

		// �����ֶ�ֵ
		String[] qtzdz = new String[] { xn, xq, nd };

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "lsgw");
		// =================end ===================

		return mapping.findForward("lsgwfpManage");
	}
	
	/**
	 * ��ʱ��λ - �����ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsgwjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		QgzxService qzzxService = new QgzxService();
		QgzxGzdxService service = new QgzxGzdxService();
		QgzxTyForm myForm = (QgzxTyForm) form;

		// ================= ����ֵ ==================
		HttpSession session = request.getSession();
		// ��½�û�����
		String userType = (String) session.getAttribute("userType");
		// ��½�û���
		String userName = (String) session.getAttribute("userName");
		// ��½�û�����
		String userDep = (String) session.getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_qgzx_lsgwxx";
		// ����
		String realTable = "gzdx_lsgwb";
		// ����·��
		String path = "qgzx_lsgw_jg.do";
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================

		// =================Ȩ�޿��� ==================
		if (qzzxService.isYrdwUser(userName)) {
			//�ж��Ƿ����˵�λ
			myForm.setUserName(userName);
		}else{
			myForm.setUserName(null);
		}

		if ("xy".equalsIgnoreCase(userType)) {
			// ѧԺ
			myForm.setQueryequals_xydm(userDep);
			myForm.setXydm(userDep);
		}
		// =================end==================

		// ==================ִ��ɾ������ ==================
		if ("del".equalsIgnoreCase(doType)) {
			this.deleteOperation(request, realTable);
		}
		// =================end ===================

		// ==================ִ�в�ѯ���� ==================
		if (search) {
			String[] outputColumn = { "pk","xh", "xm", "xb", "xn", "xqmc", "nd",
					"nj", "xymc", "zymc", "bjmc", "lsgwmc", "gwsqsj" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================

		// ==================ִ�е������� ==================
		if ("exp".equalsIgnoreCase(doType)) {
			String[] outputColumn = null;
			expPageData(request, response, realTable, tableName, outputColumn);
			return mapping.findForward("");
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "lsgw");
		// =================end ===================

		return mapping.findForward("lsgwjgManage");
	}
}
