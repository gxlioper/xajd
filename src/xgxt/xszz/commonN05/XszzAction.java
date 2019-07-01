package xgxt.xszz.commonN05;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.pjpy.tginterface.PjpyCommonInterface;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.SearchUtils;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzActionUtils;
import xgxt.xszz.XszzCommenBean;
import xgxt.xszz.XszzService;
import xgxt.xszz.XszzTyForm;
import xgxt.xszz.njjs.XszzNjjsService;

import common.Globals;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: N05ѧ������Action</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-10-13</p>
 */
public class XszzAction extends BaseAction {
    /**
    * ѧ�Ѽ���3
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
     * @throws Exception 
    * */
    public ActionForward xfjm3(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        XszzCommonN05ActionForm model = (XszzCommonN05ActionForm)form;
        XszzCommonN05Service service = new XszzCommonN05Service();
        String userOnLine = session.getAttribute("userOnLine").toString();
        String userName = session.getAttribute("userName").toString();
        String go = request.getParameter("go");
        String type = request.getParameter("type");
        String tableName = "xszz_com_xfjm3";//����
        String viewName = "view_xszz_com_xfjm3";//��ͼ��
        String shjb = service.getShjb(viewName);//��˼���
        String modID="stu_result_cdopen_common.do?cdlb=result&doType=open";//�˵�·��

        if ("xy".equalsIgnoreCase(session.getAttribute("userType").toString())
            && !"true".equalsIgnoreCase(session.getAttribute("fdyQx").toString())) {
            model.setQueryequals_xydm(session.getAttribute("userDep").toString());
        }

        if("student".equalsIgnoreCase(userOnLine)){//ѧ����½
            model.setQuerylike_xh(userName);
        }

        if("go".equalsIgnoreCase(go)){//��ѯ����
            String[] outputColumn = { "pkValue", "xh", "xm", "xb", "xn", "nj", 
            		                  "xymc", "bjmc", "sqdc", "sqsj", "spdc", 
            		                  "jmje", "fdysh", "xysh", "xxsh"};
            if("2".equalsIgnoreCase(shjb)){//�������
            	outputColumn = new String[]{ "pkValue", "xh", "xm", "xb", "xn", 
            			                     "nj", "xymc", "bjmc", "sqdc", "sqsj", 
            			                     "spdc", "jmje", "xysh", "xxsh"};
            }
            selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
        }

        if("del".equalsIgnoreCase(type)){//��Ϣɾ��
            deleteOperation(request, tableName);
        }

        request.setAttribute("path", modID);
        FormModleCommon.commonRequestSet(request);//����дȨ��title��Ϣ�ŵ�request��
        //����ѧԺרҵ�༶�б���Ϣ
        FormModleCommon.setNjXyZyBjListForFdyBzr(request,
                                                 model.getQueryequals_xydm(),
                                                 model.getQueryequals_zydm(),
                                                 model.getQueryequals_bjdm(),
                                                 model.getQueryequals_nj());
        FormModleCommon.setNdXnXqList(request);
        request.setAttribute("realTable", tableName);
        return mapping.findForward("xfjm3");
    }

    /**
    * ѧ�Ѽ���3����
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
    * @throws Exception 
    * */
    public ActionForward xfjm3Add(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();	
        XsxxglService xsxxglService = new XsxxglService();
        XszzCommonN05Service service = new XszzCommonN05Service();

        String type = request.getParameter("type");
        String userOnLine = session.getAttribute("userOnLine").toString();
        String userName = session.getAttribute("userName").toString();
        String userType = session.getAttribute("userType").toString();        
        HashMap<String, String> rs = new HashMap<String, String>();
        
        String xh = request.getParameter("xh");        
        String tableName = "xszz_com_xfjm3";
        String viewName = "view_xszz_com_xfjm3";
        String shjb = service.getShjb(viewName);
        String bxkns = service.getSfkns(viewName);//�Ƿ������������

        if("student".equalsIgnoreCase(userOnLine)){//ѧ����½
            xh = userName;
        }

        String pkValue = xh + Base.currXn;//��ʾҳ��ʱ��Ĭ��ѧ��Ϊ��ǰѧ��
        if("save".equalsIgnoreCase(type)){//��Ϣ����
        	pkValue = request.getParameter("save_xh") + request.getParameter("save_xn");           
            insertOperation(request, tableName);//��������           
            if(MESSAGE_EXISTS_ERROR.equalsIgnoreCase(request.getAttribute("message").toString())){
            	//��ӵ������Ѿ�����,�����޸Ĳ���
            	updateOperation(request, tableName);
            }
        }
        //��ѯѧ�Ѽ���������Ϣ
        selectPageDataByOne(request, tableName, viewName, pkValue);
        rs = (HashMap<String, String>) request.getAttribute("rs");

        //��ѯѧ��������Ϣ
        rs.putAll(xsxxglService.selectStuinfo(xh));
        rs.put("save_xh", rs.get("xh"));
        rs.put("save_sqsj", "save".equalsIgnoreCase(type) ? rs.get("sqsj") : GetTime.getNowTime2());//��ʼ������ʱ��
        rs.put("save_xn", "save".equalsIgnoreCase(type) ? rs.get("xn") : Base.currXn);//��ʼ��ѧ��
        rs.put("isKns", service.isKns(xh) ? "true" : "false");//�ж�ѧ���Ƿ���������
        request.setAttribute("rs", rs);

        FormModleCommon.commonRequestSet(request);//��дȨ��Ϣ
        request.setAttribute("sfksq", service.getSqQx("ѧ�Ѽ���", userType, xh));//�Ƿ��������
        request.setAttribute("shjb", shjb);
        request.setAttribute("bxkns", bxkns);   

        FormModleCommon.commonRequestSet(request);//��дȨ��Ϣ
        return mapping.findForward("xfjm3Add");
    }

    /**
    * ѧ�Ѽ���3������Ϣ�޸�
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
    * */
    public ActionForward xfjm3Modi(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response){
        XsxxglService xsxxglService = new XsxxglService();
        HashMap<String, String> rs = new HashMap<String, String>();
        String type = request.getParameter("type");

        if("save".equalsIgnoreCase(type)){//��Ϣ�޸�
                updateOperation(request, "xszz_com_xfjm3");
        }

        String pkValue = request.getParameter("pkValue");
        selectPageDataByOne(request, "xszz_com_xfjm3", "view_xszz_com_xfjm3", pkValue);
        rs = (HashMap<String, String>) request.getAttribute("rs");
        rs.putAll(xsxxglService.selectStuinfo(rs.get("xh")));//��ѯѧ��������Ϣ
        request.setAttribute("rs", rs);

        FormModleCommon.commonRequestSet(request);//��дȨ��Ϣ
        request.setAttribute("type", type);
        return mapping.findForward("xfjm3Modi");
    }

    /**
    * ѧ�Ѽ���3����
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
    * @throws Exception
    * */
    public ActionForward xfjm3Exp(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        expPageData(request, response, "xszz_com_xfjm3", "view_xszz_com_xfjm3", null);
        return mapping.findForward("");
    }

    /**
    * ѧ�Ѽ���3���
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
     * @throws Exception 
    * */
    public ActionForward xfjm3Sh(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        XszzCommonN05ActionForm model = (XszzCommonN05ActionForm)form;
        XszzCommonN05Service service = new XszzCommonN05Service();
        
        String userDep = session.getAttribute("userDep").toString();
        String userType = session.getAttribute("userType").toString();
        String go = request.getParameter("go");
        String type = request.getParameter("type");
        String tableName = "xszz_com_xfjm3";
        String viewName = "view_xszz_com_xfjm3";
        String shzd = "xxsh";
        String shjg = request.getParameter("shjg");
        String shjb = service.getShjb(viewName);
        String yhlx = "xx";//�Զ����û�����
        String annexTerm = "";//��ѯ����

        if ("xy".equalsIgnoreCase(userType)) {//ѧԺ���
	            model.setQueryequals_xydm(userDep);
	            shzd = "xysh";
	            yhlx = "xy";
	            if("3".equalsIgnoreCase(shjb)){
	            	annexTerm = " and fdysh = 'ͨ��' ";
	            }
	    }else if("xx".equalsIgnoreCase(userType) 
	    		|| "admin".equalsIgnoreCase(userType)){//ѧУ�û�
	    	annexTerm = "and xysh = 'ͨ��'";
	    }
        if("3".equalsIgnoreCase(shjb)){//������� ����Ա���	        
	        if ("true".equalsIgnoreCase(session.getAttribute("fdyQx").toString())) {//����Ա���
	            shzd = "fdysh";
	            yhlx = "fdy";
	        }
        }

        if("go".equalsIgnoreCase(go)){//��ѯ����
            String[] outputColumn = {"disabled", "bgcolor", "pkValue", "xh", "xm", 
            		                 "xb", "xn", "nj", "xymc", "bjmc", "sqdc", 
	            		                 "sqsj", "spdc", "jmje", "xysh", "xxsh"};
            if("3".equalsIgnoreCase(shjb)){
            	outputColumn = new String[]{"disabled", "bgcolor", "pkValue", "xh", "xm", 
							                "xb", "xn", "nj", "xymc", "bjmc", "sqdc", 
							                "sqsj", "spdc", "jmje", "fdysh", "xysh", "xxsh"};
            }
            request.setAttribute("clientColumns", service.getCustomAudiColumn(yhlx));
            request.setAttribute("annexTerm", annexTerm);
            selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
        }

        if("save".equalsIgnoreCase(type)){//�����Ϣ����
           	HashMap<String, String[]> primaryMap = getValueArrayMap(request, PRIFIX_PRIMARY_KEY);
           	HashMap<String, String> valueMap = new HashMap<String, String>();
           	valueMap.put(shzd, shjg);
           	
           	valueMap.put(shzd+"sj", GetTime.getNowTime2());
           	auditingBatchOperation(request, primaryMap, valueMap, tableName);
        }

        
        request.setAttribute("path", "xszz_xzcd_open.do?cdlb=shcd&doType=open");
        FormModleCommon.commonRequestSet(request);//����дȨ��title��Ϣ�ŵ�request��
        //����ѧԺרҵ�༶�б���Ϣ
        FormModleCommon.setNjXyZyBjListForFdyBzr(request,
                                                 model.getQueryequals_xydm(),
                                                 model.getQueryequals_zydm(),
                                                 model.getQueryequals_bjdm(),
                                                 model.getQueryequals_nj());
        FormModleCommon.setNdXnXqList(request);
        return mapping.findForward("xfjm3Sh");
    }

    /**
    * ѧ�Ѽ��ⵥ�����
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
    * */
    public ActionForward xfjm3ShOne(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response){
        XsxxglService xsxxglService = new XsxxglService();
        XszzCommonN05Service service = new XszzCommonN05Service();
        HashMap<String, String> rs = new HashMap<String, String>();
        
        String type = request.getParameter("type");
        String tableName = "xszz_com_xfjm3";
        String viewName = "view_xszz_com_xfjm3";
        String pkValue = request.getParameter("pkValue");

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
        request.setAttribute("type", type);
        request.setAttribute("chkList", service.getChkList(3));//���״̬�б�
        request.setAttribute("sj", GetTime.getNowTime2());//���ʱ��
        return mapping.findForward("xfjm3ShOne");
    }

    /**
     * ѧ�Ѽ��ⱨ���ӡ
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * */
     public ActionForward xfjm3sqb(ActionMapping mapping, ActionForm form,
         HttpServletRequest request, HttpServletResponse response){
     	 XszzCommonN05Service service = new XszzCommonN05Service();    	
         XsxxglService xsxxglService = new XsxxglService();
         PjpyCommonInterface pjInterface = new PjpyCommonInterface();//�������Žӿ�
         HashMap<String, String> rs = new HashMap<String, String>();
         String tableName = "xszz_com_xfjm3";
         String viewName = "view_xszz_com_xfjm3";
         String pkValue = request.getParameter("pkValue");
         
         selectPageDataByOne(request, tableName, viewName, pkValue);//ѧ�Ѽ�����Ϣ��ѯ
         rs = (HashMap<String, String>) request.getAttribute("rs");
         rs.putAll(xsxxglService.selectStuinfo(rs.get("xh")));//ѧ����Ϣ��ѯ        
         
         request.setAttribute("rs", rs);
         return mapping.findForward("xfjm3sqb");
     }

    /**
    * ס�޷ѻ���1
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
     * @throws Exception 
    * */
    public ActionForward zsfhj1(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        XszzCommonN05ActionForm model = (XszzCommonN05ActionForm)form;
        XszzCommonN05Service service = new XszzCommonN05Service();
        String userOnLine = session.getAttribute("userOnLine").toString();
        String userName = session.getAttribute("userName").toString();
        String go = request.getParameter("go");
        String type = request.getParameter("type");
        String tableName = "xszz_com_zsfhj1";
        String viewName = "view_xszz_com_zsfhj1";
        String modID="stu_result_cdopen_common.do?cdlb=result&doType=open";//�˵�·��
        String shjb = service.getShjb(viewName);        

        if ("xy".equalsIgnoreCase(session.getAttribute("userType").toString())
            && !"true".equalsIgnoreCase(session.getAttribute("fdyQx").toString())) {
            model.setQueryequals_xydm(session.getAttribute("userDep").toString());
        }

        if("student".equalsIgnoreCase(userOnLine)){//ѧ����½
            model.setQuerylike_xh(userName);
        }

        if("go".equalsIgnoreCase(go)){//��ѯ����
            String[] outputColumn = { "pkValue","xh", "xm", "xb", "xn", "nj", "xymc", "bjmc", 
            		                  "hjje", "yjjfsj", "yjjfje", "sqsj", "fdysh", 
            		                  "xysh", "xxsh"};
            if("2".equalsIgnoreCase(shjb)){//�������
            	outputColumn = new String[]{"pkValue", "xh", "xm", "xb", "xn", "nj", "xymc", "bjmc", 
		                  					 "hjje", "yjjfsj", "yjjfje", "sqsj", 
		                  					 "xysh", "xxsh"};
            }
            selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
        }

        if("del".equalsIgnoreCase(type)){//��Ϣɾ��
            deleteOperation(request, tableName);
        }

        request.setAttribute("path", modID);
        FormModleCommon.commonRequestSet(request);//����дȨ��title��Ϣ�ŵ�request��
        //����ѧԺרҵ�༶�б���Ϣ
        FormModleCommon.setNjXyZyBjListForFdyBzr(request,
                                                 model.getQueryequals_xydm(),
                                                 model.getQueryequals_zydm(),
                                                 model.getQueryequals_bjdm(),
                                                 model.getQueryequals_nj());
        FormModleCommon.setNdXnXqList(request);
        request.setAttribute("realTable", tableName);
        request.setAttribute("shjb", shjb);
        return mapping.findForward("zsfhj1");
    }

    /**
    * ����ѻ�������
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
     * @throws Exception 
    * */
    public ActionForward zsfhj1Add(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();	
        XszzCommonN05Service service = new XszzCommonN05Service();
        XsxxglService xsxxglService = new XsxxglService();

        String type = request.getParameter("type");
        String userOnLine = session.getAttribute("userOnLine").toString();
        String userName = session.getAttribute("userName").toString();
        String userType = session.getAttribute("userType").toString();
        HashMap<String, String> rs = new HashMap<String, String>();
        String xh = request.getParameter("xh");
        String tableName = "xszz_com_zsfhj1";
        String viewName = "view_xszz_com_zsfhj1";
        String shjb = service.getShjb(viewName);
        String bxkns = service.getSfkns(viewName);//�Ƿ������������

        if("student".equalsIgnoreCase(userOnLine)){//ѧ����½
            xh = userName;
        }

        String pkValue = xh + Base.currXn;//��ʾҳ��ʱ��Ĭ��ѧ��Ϊ��ǰѧ��
        if("save".equalsIgnoreCase(type)){//��Ϣ����
            pkValue = request.getParameter("save_xh") + request.getParameter("save_xn");           
            insertOperation(request, tableName);//��������           
            if(MESSAGE_EXISTS_ERROR.equalsIgnoreCase(request.getAttribute("message").toString())){
            	//��ӵ������Ѿ�����,�����޸Ĳ���
            	updateOperation(request, tableName);
            }
        }
        
        //��ѯס�޷ѻ���������Ϣ
        selectPageDataByOne(request, tableName, viewName, pkValue);
        rs = (HashMap<String, String>) request.getAttribute("rs");

        //��ѯѧ��������Ϣ
        rs.putAll(xsxxglService.selectStuinfo(xh));
        rs.put("save_xh", rs.get("xh"));
        rs.put("save_sqsj", "save".equalsIgnoreCase(type) ? rs.get("sqsj") : GetTime.getNowTime2());//��ʼ������ʱ��
        rs.put("save_xn", "save".equalsIgnoreCase(type) ? rs.get("xn") : Base.currXn);//��ʼ��ѧ��
        rs.put("isKns", service.isKns(xh) ? "true" : "false");//�ж�ѧ���Ƿ���������
        request.setAttribute("rs", rs);

        FormModleCommon.commonRequestSet(request);//��дȨ��Ϣ
        request.setAttribute("sfksq", service.getSqQx("ס�޷ѻ���", userType, xh));//�Ƿ��������
        request.setAttribute("shjb", shjb);
        request.setAttribute("bxkns", bxkns);        
        return mapping.findForward("zsfhj1Add");
    }

    /**
    * ס�޷ѻ����޸�
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
    * */
    public ActionForward zsfhj1Modi(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response){
        XsxxglService xsxxglService = new XsxxglService();
        HashMap<String, String> rs = new HashMap<String, String>();
        String type = request.getParameter("type");

        if("save".equalsIgnoreCase(type)){//��Ϣ�޸�
                updateOperation(request, "xszz_com_zsfhj1");
        }

        String pkValue = request.getParameter("pkValue");
        selectPageDataByOne(request, "xszz_com_zsfhj1", "view_xszz_com_zsfhj1", pkValue);
        rs = (HashMap<String, String>) request.getAttribute("rs");
        rs.putAll(xsxxglService.selectStuinfo(rs.get("xh")));//��ѯѧ��������Ϣ
        request.setAttribute("rs", rs);

        FormModleCommon.commonRequestSet(request);//��дȨ��Ϣ
        request.setAttribute("type", type);
        return mapping.findForward("zsfhj1Modi");
    }

    /**
    * ס�޷ѻ�����Ϣ����
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
    * @throws Exception
    * */
    public ActionForward zsfhj1Exp(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        expPageData(request, response, "xszz_com_zsfhj1", "view_xszz_com_zsfhj1", null);
        return mapping.findForward("");
    }

    /**
    * ס�޷ѻ������
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
     * @throws Exception 
    * */
    public ActionForward zsfhj1Sh(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        XszzCommonN05ActionForm model = (XszzCommonN05ActionForm)form;
        XszzCommonN05Service service = new XszzCommonN05Service();
        String userDep = session.getAttribute("userDep").toString();
        String userType = session.getAttribute("userType").toString();
        String go = request.getParameter("go");
        String type = request.getParameter("type");
        String tableName = "xszz_com_zsfhj1";
        String viewName = "view_xszz_com_zsfhj1";
        String shzd = "xxsh";
        String shjg = request.getParameter("shjg");
        String shjb = service.getShjb(viewName);
        String yhlx = "xx";//�Զ����û�����
        String annexTerm = "";//��ѯ����

        if ("xy".equalsIgnoreCase(userType)) {//ѧԺ���
	            model.setQueryequals_xydm(userDep);
	            shzd = "xysh";
	            yhlx = "xy";
	            if("3".equalsIgnoreCase(shjb)){
	            	annexTerm = " and fdysh = 'ͨ��' ";
	            }
	    }else if("xx".equalsIgnoreCase(userType) 
	    		|| "admin".equalsIgnoreCase(userType)){//ѧУ�û�
	    	annexTerm = "and xysh = 'ͨ��'";
	    }
        if("3".equalsIgnoreCase(shjb)){//������� ����Ա���	        
	        if ("true".equalsIgnoreCase(session.getAttribute("fdyQx").toString())) {//����Ա���
	            shzd = "fdysh";
	            yhlx = "fdy";
	        }
        }

        if("go".equalsIgnoreCase(go)){//��ѯ����
            String[] outputColumn = {"disabled", "bgcolor", "pkValue", "xh", "xm", 
            		                 "xb", "xn", "nj", "xymc", "bjmc", "hjje", 
            		                 "yjjfsj", "yjjfje", "sqsj", "xysh", "xxsh"};
            if("3".equalsIgnoreCase(shjb)){
            	outputColumn = new String[]{"disabled", "bgcolor", "pkValue", "xh", "xm", 
							                "xb", "xn", "nj", "xymc", "bjmc", "hjje", 
							                "yjjfsj", "yjjfje", "sqsj", "fdysh", "xysh", "xxsh"};
            }
            request.setAttribute("clientColumns", service.getCustomAudiColumn(yhlx));
            request.setAttribute("annexTerm", annexTerm);
            selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
        }

        if("save".equalsIgnoreCase(type)){//�����Ϣ����
           	HashMap<String, String[]> primaryMap = getValueArrayMap(request, PRIFIX_PRIMARY_KEY);
           	HashMap<String, String> valueMap = new HashMap<String, String>();
           	valueMap.put(shzd, shjg);
           	
           	valueMap.put(shzd+"sj", GetTime.getNowTime2());
           	auditingBatchOperation(request, primaryMap, valueMap, tableName);
        }

        request.setAttribute("path", "xszz_xzcd_open.do?cdlb=shcd&doType=open");
        FormModleCommon.commonRequestSet(request);//����дȨ��title��Ϣ�ŵ�request��
        //����ѧԺרҵ�༶�б���Ϣ
        FormModleCommon.setNjXyZyBjListForFdyBzr(request,
                                                 model.getQueryequals_xydm(),
                                                 model.getQueryequals_zydm(),
                                                 model.getQueryequals_bjdm(),
                                                 model.getQueryequals_nj());
        FormModleCommon.setNdXnXqList(request);
        return mapping.findForward("zsfhj1Sh");
    }

    /**
    * ס�޷ѻ����������
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
    * */
    public ActionForward zsfhj1ShOne(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response){
    	XszzCommonN05Service service = new XszzCommonN05Service();    	
        XsxxglService xsxxglService = new XsxxglService();
        HashMap<String, String> rs = new HashMap<String, String>();
        
        String type = request.getParameter("type");
        String tableName = "xszz_com_zsfhj1";
        String viewName = "view_xszz_com_zsfhj1";
        String pkValue = request.getParameter("pkValue");

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
        request.setAttribute("type", type);
        request.setAttribute("chkList", service.getChkList(3));//���״̬�б�
        request.setAttribute("sj", GetTime.getNowTime2());//���ʱ��
        return mapping.findForward("zsfhj1ShOne");
    }

    /**
     * ס�޷ѻ�������ӡ
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * */
     public ActionForward zsfhj1sqb(ActionMapping mapping, ActionForm form,
         HttpServletRequest request, HttpServletResponse response){
     	 XszzCommonN05Service service = new XszzCommonN05Service();    	
         XsxxglService xsxxglService = new XsxxglService();
         HashMap<String, String> rs = new HashMap<String, String>();
         String tableName = "xszz_com_zsfhj1";
         String viewName = "view_xszz_com_zsfhj1";
         String pkValue = request.getParameter("pkValue");
         
         selectPageDataByOne(request, tableName, viewName, pkValue);
         rs = (HashMap<String, String>) request.getAttribute("rs");
         rs.putAll(xsxxglService.selectStuinfo(rs.get("xh")));//ѧ����Ϣ��ѯ
         
         request.setAttribute("rs", rs);
         return mapping.findForward("zsfhj1sqb");
     }
    
    
    /**
    * ѧ�ѻ���3���ݲ�ѯ
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
     * @throws Exception 
    * */
    public ActionForward xfhj3(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response) throws Exception{    	
        HttpSession session = request.getSession();
        XszzCommonN05ActionForm model = (XszzCommonN05ActionForm)form;
        XszzCommonN05Service service = new XszzCommonN05Service();
        String userOnLine = session.getAttribute("userOnLine").toString();
        String userName = session.getAttribute("userName").toString();
        String go = request.getParameter("go");
        String type = request.getParameter("type");
        String tableName = "xszz_com_xfhj3";
        String viewName = "view_xszz_com_xfhj3";
        String modID="stu_result_cdopen_common.do?cdlb=result&doType=open";//�˵�·��
        String shjb = service.getShjb(viewName);
                
        if ("xy".equalsIgnoreCase(session.getAttribute("userType").toString())
            && !"true".equalsIgnoreCase(session.getAttribute("fdyQx").toString())) {
            model.setQueryequals_xydm(session.getAttribute("userDep").toString());
        }

        if("student".equalsIgnoreCase(userOnLine)){//ѧ����½
            model.setQuerylike_xh(userName);
        }

        if("go".equalsIgnoreCase(go)){//��ѯ����
            String[] outputColumn = { "pkValue", "xh", "xm", "xb","sfzh", "xn", 
            		                  "nj", "xymc", "bjmc", "hjje",
            		                  "sqsj", "fdysh", "xysh", "xxsh"};
            if("2".equalsIgnoreCase(shjb)){
            	outputColumn = new String[]{ "pkValue", "xh", "xm", "xb","sfzh", "xn", 
		                  "nj", "xymc", "bjmc", "hjje",
		                  "sqsj", "xysh", "xxsh"};
            }
            selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
        }

        if("del".equalsIgnoreCase(type)){//��Ϣɾ��
            deleteOperation(request, tableName);
        }

        request.setAttribute("path", modID);
        FormModleCommon.commonRequestSet(request);//
        //����ѧԺרҵ�༶�б���Ϣ
        FormModleCommon.setNjXyZyBjListForFdyBzr(request,
                                                 model.getQueryequals_xydm(),
                                                 model.getQueryequals_zydm(),
                                                 model.getQueryequals_bjdm(),
                                                 model.getQueryequals_nj());
        FormModleCommon.setNdXnXqList(request);
        request.setAttribute("realTable", tableName);
        request.setAttribute("shjb", shjb);        
        return mapping.findForward("xfhj3");
    }

    /**
    * ѧ�ѻ���3����
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
     * @throws Exception 
    * */
    public ActionForward xfhj3Add(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();	
        XsxxglService xsxxglService = new XsxxglService();
        XszzCommonN05Service service = new XszzCommonN05Service();
        HashMap<String, String> rs = new HashMap<String, String>();
        
        String type = request.getParameter("type");
        String userOnLine = session.getAttribute("userOnLine").toString();
        String userName = session.getAttribute("userName").toString();
        String userType = session.getAttribute("userType").toString();
       
        String tableName = "xszz_com_xfhj3";
        String viewName = "view_xszz_com_xfhj3";
        String xh = request.getParameter("xh");
        String shjb = service.getShjb(viewName);//��˼���
        String bxkns = service.getSfkns(viewName);//�Ƿ������������

        if("student".equalsIgnoreCase(userOnLine)){//ѧ����½
            xh = userName;
        }
        
        String pkValue = xh + Base.currXn;//��ʾҳ��ʱ��Ĭ��ѧ��Ϊ��ǰѧ��
        if("save".equalsIgnoreCase(type)){//��Ϣ����
            pkValue = request.getParameter("save_xh") + request.getParameter("save_xn");           
            insertOperation(request, tableName);//��������           
            if(MESSAGE_EXISTS_ERROR.equalsIgnoreCase(request.getAttribute("message").toString())){
            	//��ӵ������Ѿ�����,�����޸Ĳ���
            	updateOperation(request, tableName);
            }
        }
        
        //��ѯѧ�Ѽ���������Ϣ
        selectPageDataByOne(request, tableName, viewName, pkValue);
        rs = (HashMap<String, String>) request.getAttribute("rs");

        //��ѯѧ��������Ϣ
        rs.putAll(xsxxglService.selectStuinfo(xh));
        rs.put("save_xh", rs.get("xh"));
        rs.put("save_sqsj", "save".equalsIgnoreCase(type) ? rs.get("sqsj") : GetTime.getNowTime2());//��ʼ������ʱ��
        rs.put("save_xn", "save".equalsIgnoreCase(type) ? rs.get("xn") : Base.currXn);//��ʼ��ѧ��
        rs.put("isKns", service.isKns(xh) ? "true" : "false");
        request.setAttribute("rs", rs);

        FormModleCommon.commonRequestSet(request);//��дȨ��Ϣ
        request.setAttribute("sfksq", service.getSqQx("ѧ�ѻ���", userType, xh));//�Ƿ��������
        request.setAttribute("shjb", shjb);
        request.setAttribute("bxkns", bxkns);
        return mapping.findForward("xfhj3Add");
    }

    /**
    *  ѧ�ѻ���3�޸�
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
    * */
    public ActionForward xfhj3Modi(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response){
        XsxxglService xsxxglService = new XsxxglService();
        HashMap<String, String> rs = new HashMap<String, String>();
        String type = request.getParameter("type");

        if("save".equalsIgnoreCase(type)){//��Ϣ�޸�
                updateOperation(request, "xszz_com_xfhj3");
        }

        String pkValue = request.getParameter("pkValue");
        selectPageDataByOne(request, "xszz_com_xfhj3", "view_xszz_com_xfhj3", pkValue);
        rs = (HashMap<String, String>) request.getAttribute("rs");
        rs.putAll(xsxxglService.selectStuinfo(rs.get("xh")));//��ѯѧ��������Ϣ
        request.setAttribute("rs", rs);

        FormModleCommon.commonRequestSet(request);//��дȨ��Ϣ
        request.setAttribute("type", type);
        return mapping.findForward("xfhj3Modi");
    }

    /**
    * ѧ�ѻ���3��Ϣ����
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
    * @throws Exception
    * */
    public ActionForward xfhj3Exp(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        expPageData(request, response, "xszz_com_xfhj3", "view_xszz_com_xfhj3", null);
        return mapping.findForward("");
    }

    /**
    * ѧ�ѻ���3��˲�ѯ
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
     * @throws Exception 
    * */
    public ActionForward xfhj3Sh(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession();
        XszzCommonN05ActionForm model = (XszzCommonN05ActionForm)form;
        XszzCommonN05Service service = new XszzCommonN05Service();
        
        String userType = session.getAttribute("userType").toString();
        String userDep = session.getAttribute("userDep").toString();
        
        String go = request.getParameter("go");
        String type = request.getParameter("type");
        String tableName = "xszz_com_xfhj3";
        String viewName = "view_xszz_com_xfhj3";
        String shzd = "xxsh";
        String shjg = request.getParameter("shjg");
        String shjb = service.getShjb(viewName);
        String yhlx = "xx";//�Զ����û�����
        String annexTerm = "";//��ѯ����

        if ("xy".equalsIgnoreCase(userType)) {//ѧԺ���
	            model.setQueryequals_xydm(userDep);
	            shzd = "xysh";
	            yhlx = "xy";
	            if("3".equalsIgnoreCase(shjb)){
	            	annexTerm = " and fdysh = 'ͨ��' ";
	            }
	    }else if("xx".equalsIgnoreCase(userType) 
	    		|| "admin".equalsIgnoreCase(userType)){//ѧУ�û�
	    	annexTerm = "and xysh = 'ͨ��'";
	    }
        if("3".equalsIgnoreCase(shjb)){//������� ����Ա���	        
	        if ("true".equalsIgnoreCase(session.getAttribute("fdyQx").toString())) {//����Ա���
	            shzd = "fdysh";
	            yhlx = "fdy";
	        }
        }

        if("go".equalsIgnoreCase(go)){//��ѯ����
            String[] outputColumn = {"disabled", "bgcolor", "pkValue", "xh", "xm", 
            		                 "xb", "xn", "nj", "xymc", "bjmc", "hjje", 
            		                 "fmsfzxxfhj", "sqsj", "xysh", "xxsh"};
            if("3".equalsIgnoreCase(shjb)){
            	outputColumn = new String[]{"disabled", "bgcolor", "pkValue", "xh", "xm", 
		                 "xb", "xn", "nj", "xymc", "bjmc", "hjje", 
		                 "fmsfzxxfhj", "sqsj", "fdysh", "xysh", "xxsh"};
            }
            request.setAttribute("clientColumns", service.getCustomAudiColumn(yhlx));
            request.setAttribute("annexTerm", annexTerm);
            selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
        }

        if("save".equalsIgnoreCase(type)){//�����Ϣ����
           	HashMap<String, String[]> primaryMap = getValueArrayMap(request, PRIFIX_PRIMARY_KEY);
           	HashMap<String, String> valueMap = new HashMap<String, String>();
           	valueMap.put(shzd, shjg);
           	
           	valueMap.put(shzd+"sj", GetTime.getNowTime2());
           	auditingBatchOperation(request, primaryMap, valueMap, tableName);
        }

        request.setAttribute("path", "xszz_xzcd_open.do?cdlb=shcd&doType=open");
        FormModleCommon.commonRequestSet(request);//����дȨ��title��Ϣ�ŵ�request��
        //����ѧԺרҵ�༶�б���Ϣ
        FormModleCommon.setNjXyZyBjListForFdyBzr(request,
                                                 model.getQueryequals_xydm(),
                                                 model.getQueryequals_zydm(),
                                                 model.getQueryequals_bjdm(),
                                                 model.getQueryequals_nj());
        FormModleCommon.setNdXnXqList(request);
        return mapping.findForward("xfhj3Sh");
    }

    /**
    * ѧ�ѻ���3�������
    * @param mapping
    * @param form
    * @param request
    * @param response
    * @return ActionForward
    * */
    public ActionForward xfhj3ShOne(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response){
    	XszzCommonN05Service service = new XszzCommonN05Service();
        XsxxglService xsxxglService = new XsxxglService();
        HashMap<String, String> rs = new HashMap<String, String>();
        
        String tableName = "xszz_com_xfhj3";
        String viewName = "view_xszz_com_xfhj3";
        String type = request.getParameter("type");
        String pkValue = request.getParameter("pkValue");

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
        request.setAttribute("type", type);
        request.setAttribute("chkList", service.getChkList(3));//���״̬�б�
        request.setAttribute("sj", GetTime.getNowTime2());//���ʱ��
        return mapping.findForward("xfhj3ShOne");
    }
    
    /**
     * ѧ�ѻ��������ӡ
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * */
     public ActionForward xfhj3sqb(ActionMapping mapping, ActionForm form,
         HttpServletRequest request, HttpServletResponse response){
     	 XszzCommonN05Service service = new XszzCommonN05Service();    	
         XsxxglService xsxxglService = new XsxxglService();
         PjpyCommonInterface pjInterface = new PjpyCommonInterface();//�������Žӿ�
         HashMap<String, String> rs = new HashMap<String, String>();
         String tableName = "xszz_com_xfhj3";
         String viewName = "view_xszz_com_xfhj3";
         String pkValue = request.getParameter("pkValue");
         
         
         selectPageDataByOne(request, tableName, viewName, pkValue);//ѧ�ѻ�����Ϣ��ѯ
         rs = (HashMap<String, String>) request.getAttribute("rs");
         rs.putAll(xsxxglService.selectStuinfo(rs.get("xh")));//ѧ����Ϣ��ѯ
         selectPageDataByOne(request, "xszz_n05_xsjtqkdcb", "view_xszz_n05_xsjtqkdcb", rs.get("xh"));//��ͥ���������Ϣ��ѯ
         rs.putAll((HashMap<String, String>)request.getAttribute("rs"));
         //��ѯѧ���Ƿ���Υ�ʹ�����Ϣ
         List<String[]> wjcfList = pjInterface.queryStuCfxx(rs);
         if(wjcfList != null){
        	 rs.put("sfywjxx",  wjcfList.size() >0? "��" : "��");
         }
         String xh = rs.get("xh");
         //��ѯÿѧ�����������
         List<HashMap<String, String>> xnList = service.getStuZxxn(xh);
         //��ѧ����
         rs.putAll(service.queryXnje(xnList,xh,"zxdk"));
         //��ѧ��
         rs.putAll(service.queryXnje(xnList,xh,"zxj"));
         //��ѧ��
         rs.putAll(service.queryXnje(xnList,xh,"jxj"));
         //�ڹ���ѧ
         rs.putAll(service.queryXnje(xnList,xh,"qgzx"));
         //ѧ�Ѽ���
         rs.putAll(service.queryXnje(xnList,xh,"xfjm"));
         //��ʱ���Ѳ���
         rs.putAll(service.queryXnje(xnList,xh,"lsknbz"));
         //������ѧ��
         rs.putAll(service.queryXnje(xnList,xh,"cszxj"));
         request.setAttribute("rs", rs);
         return mapping.findForward("xfhj3sqb");
     }


    //	ҳ�����ֵ��ȡ
	public List<HashMap<String, String>> getParams(HttpServletRequest request) {
		Enumeration paramsEnum = request.getParameterNames();
		List<HashMap<String, String>> valueList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String[]> map_values = new HashMap<String, String[]>();
		int valueListSize = -1;
		while (paramsEnum.hasMoreElements()) {
			String param = (String) paramsEnum.nextElement();
			if (param.startsWith("_")) {
				String[] paramValue = request.getParameterValues(param);
				map_values.put(param.replace("_", ""), paramValue);
				valueListSize = paramValue.length;
			}
		}
		if (valueListSize > -1) {
			for (int i = 0; i < valueListSize; i++) {
				valueList.add(new HashMap<String, String>());
			}
			Set<String> keySet = map_values.keySet();
			for (String key : keySet) {
				String[] values = map_values.get(key);
				for (int i = 0; i < valueList.size(); i++) {
					HashMap<String, String> m = valueList.get(i);
					m.put(key, DealString.toGBK(values[i]));
				}
			}
		}
		return valueList;
	}
	
	/**
	 * ��Ŀ�������ҳ�� xmxgsz ----- ��Ŀ�������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xmxgsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));

		if ("csh".equalsIgnoreCase(queryModel.getGo())) {
			service.delKnsrd1xx(actionForm.getPk(), request);
			queryModel.setGo("go");
		}

		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getXmszTit();
		List<String[]> resList = service.getXmszRes(queryModel, request,
				actionForm);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		request.setAttribute("xmList", service.getXmList());
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		return mapping.findForward("xmxgsz");
	}
	
	/**
	 * ��������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dgszxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		if ("save".equalsIgnoreCase(act)) {
			XmszModel model = new XmszModel();
			BeanUtils.copyProperties(model, actionForm);
			String[] sqlT = new String[3];
			sqlT[0] = "update xszz_n05_shjb set shjb='" + model.getShjb()
					+ "',sfkns='" + model.getSfkns() + "' where xmb='"
					+ model.getXmb() + "'";
			sqlT[1] = "delete xszz_xsjbxx where xmmc='"
					+ model.getXmb().toUpperCase() + "' and zdmc='FDYSH'";
			if ("3".equalsIgnoreCase(model.getShjb())) {
				sqlT[2] = "insert into xszz_xsjbxx(xmmc,zdmc,xssx) values('"
						+ model.getXmb().toUpperCase() + "','FDYSH','20')";
			}
			dao.runBatch(sqlT);
			request.setAttribute("ok", "ok");
			pkVal = model.getXmb();
		}

		HashMap<String, String> stuMap = service.getXmszxx(pkVal);

		request.setAttribute("rs", stuMap);
		return mapping.findForward("dgszxm");
	}
	
	/**
	 * ��������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward plszxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "!", 1);
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		XmszModel model = new XmszModel();
		if ("save".equalsIgnoreCase(act)) {
			BeanUtils.copyProperties(model, actionForm);
			String[] pkT = pkVal.split("!");
			String[] sqlT = new String[pkT.length * 3];
			int j = 0;
			for (int i = 0; i < pkT.length; i++) {
				sqlT[j] = "update xszz_n05_shjb set shjb='" + model.getShjb()
						+ "',sfkns='" + model.getSfkns() + "' where xmb='"
						+ pkT[i] + "'";
				j++;
				sqlT[j] = "delete xszz_xsjbxx where xmmc='"
						+ pkT[i].toUpperCase() + "' and zdmc='FDYSH'";
				j++;
				if ("3".equalsIgnoreCase(model.getShjb())) {
					sqlT[j] = "insert into xszz_xsjbxx(xmmc,zdmc,xssx) values('"
							+ pkT[i].toUpperCase() + "','FDYSH','20')";
					j++;
				}
			}
			dao.runBatch(sqlT);
			request.setAttribute("end", "end");
		}

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap.put("pkVal", pkVal);
		stuMap.put("shjb", model.getShjb());
		stuMap.put("sfkns", model.getSfkns());
		
		request.setAttribute("rs", stuMap);
		return mapping.findForward("plszxm");
	}
	
	/**
	 * ������ѧ����Ŀά��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wszxjxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delWszxjxm(actionForm.getPk(), request);
			queryModel.setGo("go");
		}
		
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getWszxjxmTit();
		List<String[]> resList = service.getWszxjxmRes(queryModel,request,actionForm);
		
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		request.setAttribute("pk", "xmdm");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("wszxjxmList", dao.getArrayList("SELECT xmdm,xmmc FROM xszz_com_wszxjdmb ORDER BY xmdm", new String[]{}, new String[]{"xmdm", "xmmc"}));
		return mapping.findForward("wszxjxm");
	}
	
	/**
	 * ������ѧ����Ŀ��Ϣ�༭ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wszxjxmEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05Service service = new XszzCommonN05Service();
		String xmdm = Base.chgNull(request.getParameter("xmdm"), "", 1);
		String act = Base.chgNull(request.getParameter("act"), "", 1);
		List<HashMap<String, String>> xmjeList = null;
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xmdm)) {
			stuMap = service.getWszxjxmByXmdm(xmdm);// �õ���ϸ��Ϣ
			xmjeList = service.getWszxjXmjeList(xmdm);
		}
		
		request.setAttribute("xmjeList", xmjeList);
		request.setAttribute("act", act);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("wszxjxmEdit");
	}
	
	/**
	 * ������ѧ����Ŀ��Ϣ�༭����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wszxjxmEditSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05Service service = new XszzCommonN05Service();
		String xmdm = Base.chgNull(request.getParameter("xmdm"), "", 1);
		String xmmc = Base.chgNull(request.getParameter("xmmc"), "", 1);
		
		boolean bJg = service.saveWszxjxm(xmdm, xmmc, request);// ������Ϣ
		
		List<HashMap<String, String>> xmjeList = null;
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getWszxjxmByXmdm(xmdm);// �õ���ϸ��Ϣ
		if (bJg) {
			List<HashMap<String,String>> valueList = null;
			valueList = getParams(request);
			
			bJg = service.saveWszxjje(valueList, xmdm);
			
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		xmjeList = service.getWszxjXmjeList(xmdm);
		
		request.setAttribute("xmjeList", xmjeList);
		request.setAttribute("act", "mod");
		request.setAttribute("rs", stuMap);
		return mapping.findForward("wszxjxmEditSave");
	}
	
	/**
	 * ��ͥ�������1����ҳ��
	 * jtqkdc1sq ----- ��ͥ�������1����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jtqkdc1sq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String xh = "";// ѧ��
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// �û�������ѧ����ֱ�ӻ�ȡ�û���
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xh : pkVal;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(pkVal)) {
			stuMap = service.getJtqkdc1xx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(pkVal);// �õ�ѧ����Ϣ
			}
		}

		stuMap.put("xxdm", StandardOperation.getXxdm());
		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("sfksq", service.getSqQx("��ͥ�������", sUserType, xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("jtqkdc1sq");
	}
	
	/**
	 * �����ͥ�������1������Ϣ jtqkdc1sqSave ---- �����ͥ�������1������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jtqkdc1sqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Jtqkdc1Model model = new Jtqkdc1Model();
		BeanUtils.copyProperties(model, actionForm);
		XszzCommonN05Service service = new XszzCommonN05Service();
		boolean bJg = service.saveJtqkdc1Sqxx(model, request);// ������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getJtqkdc1xx(xh);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
			}
		}

		stuMap.put("xxdm", StandardOperation.getXxdm());
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", xh);
		return mapping.findForward("jtqkdc1sqSave");
	}
	
	/**
	 * ��ͥ�������1�����ҳ�� jtqkdc1sqb ----- ��ͥ�������1�����ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jtqkdc1sqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		XszzCommonN05Service service = new XszzCommonN05Service();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getJtqkdc1xx(actionForm.getXh());
		stuMap.put("xxmc", StandardOperation.getXxmc());
		stuMap.put("xxdm", StandardOperation.getXxdm());
		request.setAttribute("rs", stuMap);
		return mapping.findForward("jtqkdc1sqb");
	}
	
	/**
	 * ��ͥ�������1��ѯҳ�� jtqkdc1query ----- ��ͥ�������1��ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jtqkdc1query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delJtqkdc1xx(actionForm.getPk(), request);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getJtqkdc1Tit();
		List<String[]> resList = service.getJtqkdc1Res(queryModel, request,
				actionForm);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		actionForm.setXh(DealString.toGBK(actionForm.getXh()));
		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getJtqkdc1ResNum(queryModel, request)));
		request.setAttribute("pk", "xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_com_knsrdb1");
		request.setAttribute("tableName", "view_xszz_com_knsrdb1");
		return mapping.findForward("jtqkdc1query");
	}
	
	/**
	 * ��ͥ�������1��ϸҳ�� jtqkdc1queryOne ----- ��ͥ�������1��ϸҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jtqkdc1queryOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		XszzCommonN05Service service = new XszzCommonN05Service();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getJtqkdc1xx(actionForm.getXh());
		stuMap.put("xxdm", StandardOperation.getXxdm());
		request.setAttribute("rs", stuMap);
		return mapping.findForward("jtqkdc1queryOne");
	}
	
	/**
	 * ��ͥ�������1��Ϣ���� jtqkdc1Exp ----- ��ͥ�������1��Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jtqkdc1Exp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, actionForm);
		service.getJtqkdc1Exp(queryModel, response, request);
		return mapping.findForward("jtqkdc1Exp");
	}
	
	/**
	 * �������϶�1����ҳ��
	 * knsrd1sq ----- �������϶�1����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd1sq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String xh = "";// ѧ��
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// �û�������ѧ����ֱ�ӻ�ȡ�û���
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getKnsrd1xx(pkVal);// �õ���ϸ��Ϣ

			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);
			}
		}

		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("sfksq", Base.xxdm.equalsIgnoreCase(Globals.XXDM_GZDX) ? service.getSqQx("�����������϶�", sUserType, xh) : service.getSqQx("�������϶�", sUserType, xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsrd1sq");
	}
	
	/**
	 * �����������϶�1������Ϣ knsrd1sqSave ---- �����������϶�1������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd1sqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Knsrdb1Model model = new Knsrdb1Model();
		BeanUtils.copyProperties(model, actionForm);
		XszzCommonN05Service service = new XszzCommonN05Service();
		String shjb = "3";//������Ϊ3�����
		
		boolean bJg = service.saveKnsrd1Sqxx(model, request,shjb);// ������Ϣ
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
			stuMap = service.getKnsrd1xx(pkVal);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);// ��ǰѧ��
			}
		}

		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsrd1sqSave");
	}
	
	/**
	 * �������϶�1�����ҳ�� knsrd1sqb ----- �������϶�1�����ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd1sqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		XszzCommonN05Service service = new XszzCommonN05Service();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsrd1xx(actionForm.getXn() + actionForm.getXh());
		
		stuMap.put("xxmc", StandardOperation.getXxmc());

		String fdysh = Base.chgNull(stuMap.get("fdysh"), "", 1);
		String xysh = Base.chgNull(stuMap.get("xysh"), "", 1);
		String xxsh = Base.chgNull(stuMap.get("xxsh"), "", 1);

		if (!"δ���".equalsIgnoreCase(fdysh)
				&& StringUtils.isIgnoreCaseEqual(fdysh, xysh)) {
			request.setAttribute("xt1", "is");
		} else {
			request.setAttribute("xt1", "no");
		}
		if (!"δ���".equalsIgnoreCase(xysh)
				&& StringUtils.isIgnoreCaseEqual(xysh, xxsh)) {
			request.setAttribute("xt2", "is");
		} else {
			request.setAttribute("xt2", "no");
		}
		
		request.setAttribute("rs", stuMap);
		return mapping.findForward("knsrd1sqb");
	}
	
	/**
	 * �������϶�1���ҳ�� knsrd1sh ----- �������϶�1���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd1sh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xxdm = StandardOperation.getXxdm();
		String auditingModel = Globals.getXszzAuditingModel().get(xxdm);
		ActionForward af = null;
		String isQuery = Base.chgNull(request.getParameter("isQuery"), "", 1);
		if(Globals.stuModel.equalsIgnoreCase(auditingModel) || Base.isNull(auditingModel) || "is".equalsIgnoreCase(isQuery)){
			//��ѧ��Ϊ��λ���
			af = knsrd1shStuModel(mapping, form, request, response);
		}else{//�Բ���Ϊ��λ���
			af = knsrd1shDeptModel(mapping, form, request, response);
		}
		return af;
	}
	
	/**
	 * �������϶�1���ҳ�� knsrd1shStuModel ----- �������϶�1��� StuModel���ģʽ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd1shStuModel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delKnsrd1xx(actionForm.getPk(), request);
			queryModel.setGo("go");
		}
		if ("ybkn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsrd1xx(actionForm.getPk(), "һ������", request);
			queryModel.setGo("go");
		}
		if ("kn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsrd1xx(actionForm.getPk(), "����", request);
			queryModel.setGo("go");
		}
		if ("tskn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsrd1xx(actionForm.getPk(), "��������", request);
			queryModel.setGo("go");
		}
		if ("bkn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsrd1xx(actionForm.getPk(), "������", request);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			actionForm.setXn(Base.currXn);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getKnsrd1Tit();
		List<String[]> resList = service.getKnsrd1Res(queryModel, request,
				actionForm);
		String xh = DealString.toGBK(actionForm.getXh());
		actionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getKnsrd1ResNum(queryModel, request)));
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_com_knsrdb1");
		request.setAttribute("tableName", "view_xszz_com_knsrdb1");
		return mapping.findForward("knsrd1sh");
	}
	
	/**
	 * �������϶�1���ҳ�� knsrd1shByFdy ----- �������϶�1��� ����Ա���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd1shByFdy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));
		String shjb = "3";

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delKnsrd1xx(actionForm.getPk(), request);
			queryModel.setGo("go");
		}
		if ("ybkn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsrd1xxByDepModel(actionForm.getPk(), "һ������", request, shjb);
			queryModel.setGo("go");
		}
		if ("kn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsrd1xxByDepModel(actionForm.getPk(), "����", request, shjb);
			queryModel.setGo("go");
		}
		if ("tskn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsrd1xxByDepModel(actionForm.getPk(), "��������", request, shjb);
			queryModel.setGo("go");
		}
		if ("bkn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsrd1xxByDepModel(actionForm.getPk(), "������", request, shjb);
			queryModel.setGo("go");
		}
		if("tjsh".equalsIgnoreCase(queryModel.getGo())){
			actionForm.setUserName(userName);
			actionForm.setXn(request.getParameter("xn"));
			actionForm.setBjdm(request.getParameter("tjbjdm"));
			service.addKnsrd1shtj(actionForm,request);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			actionForm.setXn(Base.currXn);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getKnsrd1Tit();
		List<String[]> resList = service.getKnsrd1ResByFdy(queryModel, request,
				actionForm);
		String xh = DealString.toGBK(actionForm.getXh());
		actionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getKnsrd1ResNum(queryModel, request)));
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_com_knsrdb1");
		request.setAttribute("tableName", "view_xszz_com_knsrdb1");
		return mapping.findForward("knsrd1sh");
	}
	
	/**
	 * �������϶�1���ҳ�� knsrd1shDeptModel ----- �������϶�1��� deptModel���ģʽ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd1shDeptModel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		ActionForward af = null;
		
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = session.getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",1));
		String shjb = "3";//������Ϊ3�����

		if("3".equalsIgnoreCase(shjb) && session.getAttribute("fdyQx").equals(true)){//������� �û��Ǹ���Ա ��ѧ��Ϊ��λ���
			//���ѡ���˰༶����ʾ�ύ״̬
			if(!Base.isNull(actionForm.getBjdm())){
				actionForm.setXn(Base.currXn);
				request.setAttribute("tjxx", service.getFdyshtjbxx("knsrd1",actionForm.getBjdm(),actionForm.getXn()));
			}
			request.setAttribute("fdyshFlag", true);
			af = knsrd1shByFdy(mapping, form, request, response);
		}else{//�������
			actionForm.setShjb(shjb);
			actionForm.setUserName(session.getAttribute("userName").toString());
			if ("del".equalsIgnoreCase(queryModel.getGo())) {
				service.delKnsrd1xx(actionForm.getPk(), request);
				queryModel.setGo("go");
			}
			if ("tg".equalsIgnoreCase(queryModel.getGo())) {
				actionForm.setShjg("ͨ��");				
				service.audiKnsrd1ForDep(actionForm, request);
				queryModel.setGo("go");
			}
			if ("btg".equalsIgnoreCase(queryModel.getGo())) {
				actionForm.setShjg("��ͨ��");
				service.audiKnsrd1ForDep(actionForm,request);
				queryModel.setGo("go");
			}
			if ("th".equalsIgnoreCase(queryModel.getGo())) {//�˻�
				actionForm.setShjg("�˻�");
				service.audiKnsrd1ForDep(actionForm, request);
				queryModel.setGo("go");
			}
			if("tjjg".equalsIgnoreCase(queryModel.getGo())){//�ύ��˽��
				service.commitResult(userDep,userName,"knsrd1","xn",Base.currXn);
				queryModel.setGo("go");
			}
	
			if (userType.equalsIgnoreCase("xy")) {
				actionForm.setXydm(userDep);
			}
			if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
				actionForm.setXn(Base.currXn);
			}
			BeanUtils.copyProperties(queryModel, actionForm);
			List<HashMap<String, String>> topList = service.getKnsrd1TitForDM(userType);
			List<String[]> resList = service.getKnsrd1ResForDM(queryModel, request,actionForm,shjb);
			
			request.setAttribute("topTr", topList);
			request.setAttribute("rs", resList);
			request.setAttribute("rsNum", resList != null ? resList.size() : 0);
	
			XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
			XszzCommenBean commenBean = new XszzCommenBean();
			BeanUtils.copyProperties(commenBean, actionForm);
			commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�
	
			request.setAttribute("pk", "xn||xh");
			request.setAttribute("hForm", actionForm);
			request.setAttribute("isQuery", queryModel.getIsQuery());
			request.setAttribute("realTable", "xszz_com_knsrdb1");
			request.setAttribute("tableName", "view_xszz_com_knsrdb1");
			request.setAttribute("tjjg", service.queryXyshjg(userDep,Base.currXn,"knsrd1"));
			request.setAttribute("shjb", shjb);
			af = mapping.findForward("knsrd1shdm");
		}
		
		return af;
	}
	
	/**
	 * �������϶�1�����ϸ��Ϣҳ�� knsrd1shdmOne ----- �������϶�1���deptModel���ģʽ	
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd1shdmOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm model = (XszzCommonN05ActionForm)form;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String userType = session.getAttribute("userType").toString();	
		String shjb = "3";//������Ϊ3�����
		String type = request.getParameter("type");
		String shjg = request.getParameter("shjg");
		String shyj = request.getParameter("shyj");
		String operFlag = request.getParameter("operFlag");
		String doType = request.getParameter("doType");
		
		if(!Base.isNull(type) && "save".equalsIgnoreCase(type)){
			boolean result = true;
			try{
				model.setPk(new String[]{"",pkVal});
				model.setShjg(shjg);
				model.setShyj(shyj);
				model.setShjb(shjb);
				model.setUserName(session.getAttribute("userName").toString());
				service.audiKnsrd1ForDep(model, request);
			}catch(Exception e){
				result = false;
			}
			request.setAttribute("result", result);
		}
		HashMap<String, String>  map = service.getKnsrd1shtjjg(pkVal,userType,shjb);
		List<String[]> rs = service.getKnsrd1Stush(pkVal,userType,shjb);
		map.put("shjg", shjg);
		
		
		request.setAttribute("map", map);
		request.setAttribute("operFlag", operFlag);
		request.setAttribute("doType", doType);
		request.setAttribute("rs", rs);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("chkList", service.getChkList(27));//�����˻�ѡ��
		request.setAttribute("topTr", service.getKnsrd1TitForStush(userType));
		return mapping.findForward("knsrd1shdmOne");
	}
	
	/**
	 * �������϶�1��Ϣ���� knsrd1Exp ----- �������϶�1��Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd1Exp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, actionForm);
		service.getKnsrd1Exp(queryModel, response, request);
		return mapping.findForward("knsrd1Exp");
	}
	
	/**
	 * �������϶�1�����ϸҳ�� knsrd1shOne ----- �������϶�1�����ϸҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd1shOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsrd1xx(pkVal);

		actionForm.setFdysh(stuMap.get("fdysh"));
		actionForm.setXysh(stuMap.get("xysh"));
		actionForm.setXxsh(stuMap.get("xxsh"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		
		if(Globals.deptModel.equalsIgnoreCase(Globals.getXszzAuditingModel().get(StandardOperation.getXxdm()))){
			//�Բ���Ϊ��λ���
			request.setAttribute("shmodel", "depModel");
		}

		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(12));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsrd1shOne");
	}
	
	/**
	 * �����������϶�1�����Ϣ knsrd1shSave ---- �����������϶�1�����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd1shSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Knsrdb1Model model = new Knsrdb1Model();
		BeanUtils.copyProperties(model, actionForm);
		XszzCommonN05Service service = new XszzCommonN05Service();
		boolean bJg = service.saveKnsrd1Shxx(model, request);// ���������������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsrd1xx(pkVal);

		actionForm.setFdysh(stuMap.get("fdysh"));
		actionForm.setXysh(stuMap.get("xysh"));
		actionForm.setXxsh(stuMap.get("xxsh"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(12));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsrd1shSave");
	}
	
	/**
	 * �������϶�2����ҳ��
	 * knsrd2sq ----- �������϶�2����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd2sq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String xh = "";// ѧ��
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// �û�������ѧ����ֱ�ӻ�ȡ�û���
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getKnsrd2xx(pkVal);// �õ���ϸ��Ϣ

			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);
			}
		}

		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("sfksq", service.getSqQx("�������϶�", sUserType, xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsrd2sq");
	}
	
	/**
	 * �����������϶�2������Ϣ knsrd2sqSave ---- �����������϶�2������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd2sqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Knsrdb1Model model = new Knsrdb1Model();
		BeanUtils.copyProperties(model, actionForm);
		XszzCommonN05Service service = new XszzCommonN05Service();
		boolean bJg = service.saveKnsrd2Sqxx(model, request);// ������Ϣ
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
			stuMap = service.getKnsrd2xx(pkVal);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);// ��ǰѧ��
			}
		}

		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsrd2sqSave");
	}
	
	/**
	 * �������϶�2�����ҳ�� knsrd2sqb ----- �������϶�2�����ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd2sqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		XszzCommonN05Service service = new XszzCommonN05Service();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsrd2xx(actionForm.getXn() + actionForm.getXh());
		
		stuMap.put("xxmc", StandardOperation.getXxmc());

		String fdysh = Base.chgNull(stuMap.get("fdysh"), "", 1);
		String xysh = Base.chgNull(stuMap.get("xysh"), "", 1);
		String xxsh = Base.chgNull(stuMap.get("xxsh"), "", 1);

		if (!"δ���".equalsIgnoreCase(fdysh)
				&& StringUtils.isIgnoreCaseEqual(fdysh, xysh)) {
			request.setAttribute("xt1", "is");
		} else {
			request.setAttribute("xt1", "no");
		}
		if (!"δ���".equalsIgnoreCase(xysh)
				&& StringUtils.isIgnoreCaseEqual(xysh, xxsh)) {
			request.setAttribute("xt2", "is");
		} else {
			request.setAttribute("xt2", "no");
		}
		
		request.setAttribute("rs", stuMap);
		return mapping.findForward("knsrd2sqb");
	}
	
	/**
	 * �������϶�2���ҳ�� knsrd2sh ----- �������϶�2���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd2sh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xxdm = StandardOperation.getXxdm();
		String auditingModel = Globals.getXszzAuditingModel().get(xxdm);
		ActionForward af = null;
		
		if(Globals.stuModel.equalsIgnoreCase(auditingModel) || Base.isNull(auditingModel)){
			//��ѧ��Ϊ��λ���
			af = knsrd2shStuModel(mapping, form, request, response);
		}else{//�Բ���Ϊ��λ���
			af = knsrd2shDeptModel(mapping, form, request, response);
		}
		return af;
	}
	
	/**
	 * �������϶�2���ҳ�� knsrd2shStuModel ----- �������϶�2���(StuModel���ģʽ)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd2shStuModel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delKnsrd2xx(actionForm.getPk(), request);
			queryModel.setGo("go");
		}
		if ("ybkn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsrd2xx(actionForm.getPk(), "һ������", request);
			queryModel.setGo("go");
		}
		if ("kn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsrd2xx(actionForm.getPk(), "����", request);
			queryModel.setGo("go");
		}
		if ("tskn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsrd2xx(actionForm.getPk(), "��������", request);
			queryModel.setGo("go");
		}
		if ("bkn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsrd2xx(actionForm.getPk(), "������", request);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			actionForm.setXn(Base.currXn);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getKnsrd2Tit();
		List<String[]> resList = service.getKnsrd2Res(queryModel, request, actionForm);
		String xh = DealString.toGBK(actionForm.getXh());
		actionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getKnsrd2ResNum(queryModel, request)));
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_com_knsrdb1");
		request.setAttribute("tableName", "view_xszz_com_knsrdb1");
		return mapping.findForward("knsrd2sh");
	}
	
	/**
	 * �������϶�2���ҳ�� knsrd2shByFdy ----- �������϶�2���(����Աģʽ)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd2shByFdy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delKnsrd2xx(actionForm.getPk(), request);
			queryModel.setGo("go");
		}
		if ("ybkn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsrd2xx(actionForm.getPk(), "һ������", request);
			queryModel.setGo("go");
		}
		if ("kn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsrd2xx(actionForm.getPk(), "����", request);
			queryModel.setGo("go");
		}
		if ("tskn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsrd2xx(actionForm.getPk(), "��������", request);
			queryModel.setGo("go");
		}
		if ("bkn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsrd2xx(actionForm.getPk(), "������", request);
			queryModel.setGo("go");
		}
		if("tjsh".equalsIgnoreCase(queryModel.getGo())){
			actionForm.setXn(request.getParameter("xn"));
			actionForm.setBjdm(request.getParameter("tjbjdm"));
			service.addKnsrd2shtj(actionForm,request);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			actionForm.setXn(Base.currXn);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getKnsrd2Tit();
		List<String[]> resList = service.getKnsrd2ResByFdy(queryModel, request, actionForm);
		String xh = DealString.toGBK(actionForm.getXh());
		actionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getKnsrd2ResNum(queryModel, request)));
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_com_knsrdb1");
		request.setAttribute("tableName", "view_xszz_com_knsrdb1");
		return mapping.findForward("knsrd2sh");
	}
	
	/**
	 * �������϶�2���ҳ�� knsrd2shDeptModel ----- �������϶�2���(deptModel���ģʽ)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd2shDeptModel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",1));		
		ActionForward af = null;
		String shjb = "3";//�������
		
		if("3".equalsIgnoreCase(shjb) && session.getAttribute("fdyQx").equals(true)){//������� �û��Ǹ���Ա ��ѧ��Ϊ��λ���
			//���ѡ���˰༶����ʾ�ύ״̬
			if(!Base.isNull(actionForm.getBjdm())){
				actionForm.setXn(Base.currXn);	
				request.setAttribute("tjxx", service.getFdyshtjbxx("knsrd2",actionForm.getBjdm(),actionForm.getXn()));
			}
			request.setAttribute("fdyshFlag", true);
			af = knsrd2shByFdy(mapping, form, request, response);
		}else{//�������
			if ("tg".equalsIgnoreCase(queryModel.getGo())) {
				service.audiKnsrd2ForDep(actionForm.getPk(), "ͨ��", "", request, shjb);
				queryModel.setGo("go");
			}
			if ("btg".equalsIgnoreCase(queryModel.getGo())) {
				service.audiKnsrd2ForDep(actionForm.getPk(), "��ͨ��", "", request, shjb);
				queryModel.setGo("go");
			}
			if ("bkn".equalsIgnoreCase(queryModel.getGo())) {
				service.audiKnsrd2ForDep(actionForm.getPk(), "������", "", request, shjb);
				queryModel.setGo("go");
			}
	
			if (userType.equalsIgnoreCase("xy")) {
				actionForm.setXydm(userDep);
			}
			if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
				actionForm.setXn(Base.currXn);
			}
			BeanUtils.copyProperties(queryModel, actionForm);
			List<HashMap<String, String>> topList = service.getKnsrd2TitForDM(userType);
			List<String[]> resList = service.getKnsrd2ResForDM(queryModel, request, actionForm,shjb);
			String xh = DealString.toGBK(actionForm.getXh());
			actionForm.setXh(xh);
			request.setAttribute("topTr", topList);
			request.setAttribute("rs", resList);
			request.setAttribute("rsNum", resList != null ? resList.size() : 0);
	
			XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
			XszzCommenBean commenBean = new XszzCommenBean();
			BeanUtils.copyProperties(commenBean, actionForm);
			commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�
	
			actionForm.setXm(DealString.toGBK(actionForm.getXm()));
			actionForm.setXb(DealString.toGBK(actionForm.getXb()));
			actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));
	
			//actionForm.getPages().setMaxRecord(Integer.parseInt(service.getKnsrd2ResNum(queryModel, request)));
			request.setAttribute("pk", "xn||xh");
			request.setAttribute("hForm", actionForm);
			request.setAttribute("isQuery", queryModel.getIsQuery());
			request.setAttribute("realTable", "xszz_com_knsrdb1");
			request.setAttribute("tableName", "view_xszz_com_knsrdb1");
			af = mapping.findForward("knsrd2shdm");
		}
		return af;
	}
	
	/**
	 * �������϶�2�����ϸ��Ϣҳ�� knsrd2shdmOne ----- �������϶�2���deptModel���ģʽ	
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd2shdmOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();	
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String userType = session.getAttribute("userType").toString();
		String shjb = "3";
		String type = request.getParameter("type");
		String shjg = request.getParameter("shjg");
		String shyj = request.getParameter("shyj");
		String operFlag = request.getParameter("operFlag");
		
		if(!Base.isNull(type) && "save".equalsIgnoreCase(type)){
			boolean result = true;
			try{
				service.audiKnsrd2ForDep(new String[]{"",pkVal}, shjg, shyj, request, shjb);
			}catch(Exception e){
				result = false;
			}
			request.setAttribute("result", result);
		}
		HashMap<String, String>  map = service.getKnsrd2shtjjg(pkVal,userType);
		List<String[]> rs = service.getKnsrd2Stush(pkVal,userType,shjb);
		map.put("shjg", shjg);
		
		request.setAttribute("map", map);
		request.setAttribute("operFlag", operFlag);
		request.setAttribute("rs", rs);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("chkList", service.getChkList(3));
		request.setAttribute("topTr", service.getKnsrd2TitForStush(userType));
		return mapping.findForward("knsrd2shdmOne");
	}
	
	/**
	 * �������϶�2��Ϣ���� knsrd2Exp ----- �������϶�2��Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd2Exp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, actionForm);
		service.getKnsrd2Exp(queryModel, response, request);
		return mapping.findForward("knsrd2Exp");
	}
	
	/**
	 * �������϶�2�����ϸҳ�� knsrd2shOne ----- �������϶�2�����ϸҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd2shOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsrd2xx(pkVal);

		actionForm.setFdysh(stuMap.get("fdysh"));
		actionForm.setXysh(stuMap.get("xysh"));
		actionForm.setXxsh(stuMap.get("xxsh"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		if(Globals.deptModel.equalsIgnoreCase(Globals.getXszzAuditingModel().get(StandardOperation.getXxdm()))){
			//�Բ���Ϊ��λ���
			request.setAttribute("shmodel", "depModel");
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(12));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsrd2shOne");
	}
	
	/**
	 * �����������϶�2�����Ϣ knsrd2shSave ---- �����������϶�2�����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd2shSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Knsrdb1Model model = new Knsrdb1Model();
		BeanUtils.copyProperties(model, actionForm);
		XszzCommonN05Service service = new XszzCommonN05Service();
		boolean bJg = service.saveKnsrd2Shxx(model, request);// ���������������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsrd2xx(pkVal);

		actionForm.setFdysh(stuMap.get("fdysh"));
		actionForm.setXysh(stuMap.get("xysh"));
		actionForm.setXxsh(stuMap.get("xxsh"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(12));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsrd2shSave");
	}
	
	
	
	/**
	 * �������϶�2��ӡ���� knsrd2Print 
	 * knsrd2Print
	 * author ������
	 * data 2010-7-15
	 * @throws IOException 
	 */
	public ActionForward knsrd2Print(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			XszzCommonN05Service service = new XszzCommonN05Service();
			XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
			QueryModel queryModel = new QueryModel();
			queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
			queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",1));	
			String modelPath =servlet.getServletContext().getRealPath("") + "/print/xszz/nbty_knsrd2.xls";
			response.setContentType("application/vnd.ms-excel");
			BeanUtils.copyProperties(queryModel, actionForm);
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
		    service.printNbtyKnsrd2(queryModel,request, wwb);
		    return mapping.findForward("");
	}
	
	
	/**
	 * ���ҽ�ѧ��1��ӡ���� gjjxj1Print 
	 * knsrd2Print
	 * author ������
	 * data 2010-7-15
	 * @throws IOException 
	 */
	public ActionForward gjjxj1Print(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			XszzCommonN05Service service = new XszzCommonN05Service();
			XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
			QueryModel queryModel = new QueryModel();
			queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
			queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",1));	
			String modelPath =servlet.getServletContext().getRealPath("") + "/print/xszz/nbty_gjjxj1.xls";
			response.setContentType("application/vnd.ms-excel");
			BeanUtils.copyProperties(queryModel, actionForm);
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
		    service.printNbtyGjjxj1(queryModel,request, wwb);
		    return mapping.findForward("");
	}
	
	/**
	 * ���ҽ�ѧ��1��ӡ����  
	 * gjjxj1hzPrint
	 * author ������
	 * data 2010-7-15
	 * @throws IOException 
	 */
	public ActionForward gjjxj1hzPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			XszzCommonN05Service service = new XszzCommonN05Service();
			XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
			QueryModel queryModel = new QueryModel();
			queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
			queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",1));	
			String modelPath =servlet.getServletContext().getRealPath("") + "/print/xszz/nbty_gjjxj1hz.xls";
			response.setContentType("application/vnd.ms-excel");
			BeanUtils.copyProperties(queryModel, actionForm);
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(modelPath), response.getOutputStream());
		    service.printNbtyGjjxj1hz(queryModel,request, wwb);
		    return mapping.findForward("");
	}
	
	/**
	 * �������϶�3����ҳ��
	 * knsrd3sq ----- �������϶�3����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd3sq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String xh = "";// ѧ��
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// �û�������ѧ����ֱ�ӻ�ȡ�û���
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getKnsrd3xx(pkVal);// �õ���ϸ��Ϣ

			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);
				stuMap.put("sqsj", GetTime.getSystemTime());
			}
		}
		// ---------2010/5/28 edit by luojw ------------
		String xxdm = Base.xxdm;
		String xmmc =!Globals.XXDM_GZDX.equalsIgnoreCase(xxdm) ? "�������϶�"
				: "�����������϶�";
		// ---------end ------------
		String type = request.getParameter("type");
		request.setAttribute("type", type);
		request.setAttribute("sfksq", service.getSqQx(xmmc, sUserType, xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsrd3sq");
	}
	
	/**
	 * �����������϶�3������Ϣ knsrd3sqSave ---- �����������϶�3������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd3sqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Knsrdb3Model model = new Knsrdb3Model();
		BeanUtils.copyProperties(model, actionForm);
		XszzCommonN05Service service = new XszzCommonN05Service();
		// ===========2010.9.10 edit by luojw====================
		String type = request.getParameter("type");
		XszzService tyService = new XszzService();
		XszzTyForm tyModel = new XszzTyForm();

		// ɾ���ļ�
		boolean flag = tyService.fileDel("xszz_com_knsrdb3", "scdz", "xn||xh",
				model.getXn() + model.getXh());

		// �ϴ��ļ�
		if (flag) {
			tyModel.setUploadFile(model.getUploadFile());
			String filePath = tyService.uploadFile(tyModel, request);
			model.setScdz(filePath);
		}
		// ===========end====================
		
		// =============2010.9.13 edit by luojw======================
		// ѧУ����
		String xxdm = Base.xxdm;
		String xmdm = "kns";
		actionForm.setXmdm(xmdm);
		String message = "";
		// ������������
		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
			message = service.zzsqTjyz(actionForm);
			request.setAttribute("message", message);
		}
		boolean bJg = false;
		if (Base.isNull(message)) {
			bJg = service.saveKnsrd3Sqxx(model, request);// ������Ϣ
		}

		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		// ===========end====================
		
		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getKnsrd3xx(pkVal);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);// ��ǰѧ��
				stuMap.put("sqsj", GetTime.getSystemTime());
			}
		}

		request.setAttribute("type", type);
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsrd3sqSave");
	}
	
	/**
	 * �������϶�3�����ҳ�� knsrd3sqb ----- �������϶�3�����ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd3sqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		XszzCommonN05Service service = new XszzCommonN05Service();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsrd3xx(actionForm.getXn() + actionForm.getXh());
		
		stuMap.put("xxmc", StandardOperation.getXxmc());
		stuMap.put("xxdm", StandardOperation.getXxdm());
		
		request.setAttribute("rs", stuMap);
		return mapping.findForward("knsrd3sqb");
	}
	
	/**
	 * �������϶�3���ҳ�� knsrd3sh ----- �������϶�3���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd3sh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delKnsrd3xx(actionForm.getPk(), request);
			queryModel.setGo("go");
		}
		if ("ybkn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsrd3xx(actionForm.getPk(), "һ������", request);
			queryModel.setGo("go");
		}
		if ("kn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsrd3xx(actionForm.getPk(), "����", request);
			queryModel.setGo("go");
		}
		if ("tskn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsrd3xx(actionForm.getPk(), "��������", request);
			queryModel.setGo("go");
		}
		if ("bkn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsrd3xx(actionForm.getPk(), "������", request);
			queryModel.setGo("go");
		}
		if ("pd".equalsIgnoreCase(queryModel.getGo())) {
			service.plPdkns(actionForm.getPk(), request);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			actionForm.setXn(Base.currXn);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getKnsrd3Tit();
		List<String[]> resList = service.getKnsrd3Res(queryModel, request,
				actionForm);
		String xh = DealString.toGBK(actionForm.getXh());
		actionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getKnsrd3ResNum(queryModel, request)));
		
		String realTable = "xszz_com_knsrdb1";
		String tableName = "view_xszz_com_knsrdb1";
		// ---------2010/5/31 edit by luojw ------------
		String xxdm = Base.xxdm;
		realTable =Globals.XXDM_GZDX.equalsIgnoreCase(xxdm) ? "xszz_com_knsrdb3"
				:realTable;
		tableName =Globals.XXDM_GZDX.equalsIgnoreCase(xxdm) ? "view_xszz_com_knsrdb3"
				:tableName;
		// ---------end ------------
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", realTable);
		request.setAttribute("tableName", tableName);
		return mapping.findForward("knsrd3sh");
	}
	
	/**
	 * �������϶�3��ʾҳ�� knsrd3gs ----- �������϶�3��ʾ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd3gs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));

		actionForm.setXn(Base.currXn);
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getKnsrd3gsTit();
		List<String[]> resList = service.getKnsrd3gsRes(queryModel, request,
				actionForm);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		actionForm.setXh(DealString.toGBK(actionForm.getXh()));
		actionForm.setXm(DealString.toGBK(actionForm.getXm()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getKnsrd3gsResNum(queryModel, request)));
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", actionForm);
		return mapping.findForward("knsrd3gs");
	}
	
	/**
	 * �������϶�3���
	 * knsrd3gsyj ----- �������϶�3���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd3gsyj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String xh = sUserName;// ѧ��
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getKnsrd3yjxx(xh);// �õ���ϸ��Ϣ
		}

		request.setAttribute("rs", stuMap);
		return mapping.findForward("knsrd3gsyj");
	}
	
	/**
	 * �������϶�3�������
	 * knsrd3gsyjSave ----- �������϶�3�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd3gsyjSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String xh = sUserName;// ѧ��
		
		String yj = Base.chgNull(request.getParameter("yj"), "", 1);
		
		boolean bJg = service.saveKnsrd3Yj(yj, request);// ������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getKnsrd3yjxx(xh);// �õ���ϸ��Ϣ
		}

		request.setAttribute("rs", stuMap);
		return mapping.findForward("knsrd3gsyjSave");
	}
	
	/**
	 * �������϶�3����б�
	 * knsrd3ckyj ----- �������϶�3����б�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd3ckyj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));

		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getKnsrd3yjTit();
		List<String[]> resList = service.getKnsrd3yjRes(queryModel, request,
				actionForm);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getKnsrd3yjResNum(queryModel, request)));
		
		HashMap<String, String> rs1 = new HashMap<String, String>();
		rs1.put("txsj1", queryModel.getTxsj1());
		rs1.put("txsj2", queryModel.getTxsj2());
		request.setAttribute("rs1", rs1);
		request.setAttribute("pk", "xh");
		request.setAttribute("hForm", actionForm);
		return mapping.findForward("knsrd3ckyj");
	}
	
	/**
	 * �������϶�3��Ϣ���� knsrd3Exp ----- �������϶�3��Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd3Exp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, actionForm);
		service.getKnsrd3Exp(queryModel, response, request);
		return mapping.findForward("knsrd3Exp");
	}
	
	/**
	 * �������϶�3�����ϸҳ�� knsrd3shOne ----- �������϶�3�����ϸҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd3shOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsrd3xx(pkVal);

		actionForm.setFdysh(stuMap.get("fdysh"));
		actionForm.setXysh(stuMap.get("xysh"));
		actionForm.setXxsh(stuMap.get("xxsh"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		
		//���ݴ�ѧ
		String xxdm = Base.xxdm;
		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
			String xh = stuMap.get("xh");
			actionForm.setXh(xh);
			service.getLnZzInfoList(actionForm, request);
		}
		
		String method=request.getParameter("method");
		String type = request.getParameter("type");
		request.setAttribute("type", type);
		request.setAttribute("method", method);
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(12));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsrd3shOne");
	}
	
	/**
	 * �����������϶�3�����Ϣ knsrd3shSave ---- �����������϶�3�����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd3shSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Knsrdb3Model model = new Knsrdb3Model();
		BeanUtils.copyProperties(model, actionForm);
		XszzCommonN05Service service = new XszzCommonN05Service();
		boolean bJg = service.saveKnsrd3Shxx(model, request);// ���������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsrd3xx(pkVal);

		actionForm.setFdysh(stuMap.get("fdysh"));
		actionForm.setXysh(stuMap.get("xysh"));
		actionForm.setXxsh(stuMap.get("xxsh"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(12));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsrd3shSave");
	}
	
	/**
	 * �������϶�4����ҳ��
	 * knsrd4sq ----- �������϶�4����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd4sq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String xh = "";// ѧ��
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// �û�������ѧ����ֱ�ӻ�ȡ�û���
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getknsrd4xx(pkVal);// �õ���ϸ��Ϣ

			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);
			}
		}

		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("sfksq", service.getSqQx("�������϶�", sUserType, xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsrd4sq");
	}
	
	/**
	 * �����������϶�4������Ϣ knsrd4sqSave ---- �����������϶�4������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd4sqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Knsrdb1Model model = new Knsrdb1Model();
		BeanUtils.copyProperties(model, actionForm);
		XszzCommonN05Service service = new XszzCommonN05Service();
		boolean bJg = service.saveknsrd4Sqxx(model, request);// ������Ϣ
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
			stuMap = service.getknsrd4xx(pkVal);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);// ��ǰѧ��
			}
		}

		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsrd4sqSave");
	}
	
	/**
	 * �������϶�4�����ҳ�� knsrd4sqb ----- �������϶�4�����ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd4sqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		XszzCommonN05Service service = new XszzCommonN05Service();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getknsrd4xx(actionForm.getXn() + actionForm.getXh());
		
		stuMap.put("xxmc", StandardOperation.getXxmc());

		String fdysh = Base.chgNull(stuMap.get("fdysh"), "", 1);
		String xysh = Base.chgNull(stuMap.get("xysh"), "", 1);
		String xxsh = Base.chgNull(stuMap.get("xxsh"), "", 1);

		if (!"δ���".equalsIgnoreCase(fdysh)
				&& StringUtils.isIgnoreCaseEqual(fdysh, xysh)) {
			request.setAttribute("xt1", "is");
		} else {
			request.setAttribute("xt1", "no");
		}
		if (!"δ���".equalsIgnoreCase(xysh)
				&& StringUtils.isIgnoreCaseEqual(xysh, xxsh)) {
			request.setAttribute("xt2", "is");
		} else {
			request.setAttribute("xt2", "no");
		}
		
		request.setAttribute("rs", stuMap);
		return mapping.findForward("knsrd4sqb");
	}
	
	/**
	 * �������϶�4���ؼ�ͥ������� knsrd4ExpJtqkdcb ----- �������϶�4���ؼ�ͥ�������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd4ExpJtqkdcb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String modelPath = servlet.getServletContext().getRealPath("")
				+ "/print/xsjjtqkdcb.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
				modelPath), response.getOutputStream());
		ExcelMethods.submitWritableWorkbookOperations(wwb);
		return mapping.findForward("");
	}
	
	/**
	 * �������϶�4���ҳ�� knsrd4sh ----- �������϶�4���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd4sh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delknsrd4xx(actionForm.getPk(), request);
			queryModel.setGo("go");
		}
		if ("kn".equalsIgnoreCase(queryModel.getGo())) {
			service.modknsrd4xx(actionForm.getPk(), "����", request);
			queryModel.setGo("go");
		}
		if ("tskn".equalsIgnoreCase(queryModel.getGo())) {
			service.modknsrd4xx(actionForm.getPk(), "��������", request);
			queryModel.setGo("go");
		}
		if ("bkn".equalsIgnoreCase(queryModel.getGo())) {
			service.modknsrd4xx(actionForm.getPk(), "������", request);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			actionForm.setXn(Base.currXn);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getknsrd4Tit();
		List<String[]> resList = service.getknsrd4Res(queryModel, request,
				actionForm);
		String xh = DealString.toGBK(actionForm.getXh());
		actionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getknsrd4ResNum(queryModel, request)));
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_com_knsrdb1");
		request.setAttribute("tableName", "view_xszz_com_knsrdb1");
		return mapping.findForward("knsrd4sh");
	}
	
	/**
	 * �������϶�4��Ϣ���� knsrd4Exp ----- �������϶�4��Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd4Exp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, actionForm);
		service.getknsrd4Exp(queryModel, response, request);
		return mapping.findForward("knsrd4Exp");
	}
	
	/**
	 * �������϶�4�����ϸҳ�� knsrd4shOne ----- �������϶�4�����ϸҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd4shOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getknsrd4xx(pkVal);

		actionForm.setFdysh(stuMap.get("fdysh"));
		actionForm.setXysh(stuMap.get("xysh"));
		actionForm.setXxsh(stuMap.get("xxsh"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}

		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(19));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsrd4shOne");
	}
	
	/**
	 * �����������϶�4�����Ϣ knsrd4shSave ---- �����������϶�4�����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrd4shSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Knsrdb1Model model = new Knsrdb1Model();
		BeanUtils.copyProperties(model, actionForm);
		XszzCommonN05Service service = new XszzCommonN05Service();
		boolean bJg = service.saveknsrd4Shxx(model, request);// ���������������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getknsrd4xx(pkVal);

		actionForm.setFdysh(stuMap.get("fdysh"));
		actionForm.setXysh(stuMap.get("xysh"));
		actionForm.setXxsh(stuMap.get("xxsh"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(19));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsrd4shSave");
	}
	
	/**
	 * ���ҽ�ѧ��1����ҳ��
	 * gjjxj1sq ----- ���ҽ�ѧ��1����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxj1sq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String xh = "";// ѧ��
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// �û�������ѧ����ֱ�ӻ�ȡ�û���
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		String sfkns = service.getSfkns("view_xszz_com_gjjxj1");
		if ("1".equalsIgnoreCase(sfkns)) {
			request.setAttribute("sfKns", "yes");
		} else {
			request.setAttribute("sfKns", "no");
		}
		if (!"".equalsIgnoreCase(xh)) {
			if ("1".equalsIgnoreCase(sfkns)) {
				request.setAttribute("isKns", dao.isKns(xh));
			}
			stuMap = service.getGjjxj1xx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);
			}
		}

		// ------------2010/5/13 edit by luojw-------------
		String xxdm = StandardOperation.getXxdm();
		request.setAttribute("xxdm", xxdm);
		// ------------end-------------
		
		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("sfksq", service.getSqQx("���ҽ�ѧ��", sUserType, xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjjxj1sq");
	}

	/**
	 * ������ҽ�ѧ��1������Ϣ gjjxj1sqSave ---- ������ҽ�ѧ��1������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxj1sqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Gjjxj1Model model = new Gjjxj1Model();
		BeanUtils.copyProperties(model, actionForm);
		XszzCommonN05Service service = new XszzCommonN05Service();
		String shjb = service.getShjb("view_xszz_com_gjjxj1");
		
		boolean bJg = service.saveGjjxj1Sqxx(model, request,shjb);// ������Ϣ
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
			stuMap = service.getGjjxj1xx(pkVal);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);// ��ǰѧ��
			}
		}

		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjjxj1sqSave");
	}

	/**
	 * ���ҽ�ѧ��1�����ҳ�� gjjxj1sqb ----- ���ҽ�ѧ��1�����ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxj1sqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		XszzCommonN05Service service = new XszzCommonN05Service();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjjxj1xx(actionForm.getXn() + actionForm.getXh());
		
		stuMap.put("xxmc", StandardOperation.getXxmc());
		
		String xxdm=Base.xxdm;
		String sfzh = Base.chgNull(stuMap.get("sfzh"), "", 1);
		String[] sF = new String[18];
		
		for (int i = 0; i < sfzh.length(); i++) {
			sF[i] = sfzh.substring(i, i+1);
		}
		for (int i = 1; i < 19; i++) {
			stuMap.put("sfzh"+i, sF[i-1]);
		}
		
		request.setAttribute("rs", stuMap);
		
		if(Globals.XXDM_NBTYZYJSXY.equalsIgnoreCase(xxdm)){
			return mapping.findForward("gjjxj1sqbnbty");
		}
		return mapping.findForward("gjjxj1sqb");
	}
	
	
	/**
	 * ���ҽ�ѧ��1���ҳ�� gjjxj1sh ----- ���ҽ�ѧ��1���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxj1sh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xxdm = StandardOperation.getXxdm();
		String auditingModel = Globals.getXszzAuditingModel().get(xxdm);
		ActionForward af = null;
		String isQuery = Base.chgNull(request.getParameter("isQuery"), "", 1);
		if(Globals.stuModel.equalsIgnoreCase(auditingModel) || Base.isNull(auditingModel) || "is".equalsIgnoreCase(isQuery)){
			//��ѧ��Ϊ��λ���
			af = gjjxj1shStuModel(mapping, form, request, response);
		}else{//�Բ���Ϊ��λ���
			af = gjjxj1shDeptModel(mapping, form, request, response);
		}
		return af;
	}
	
	/**
	 * ���ҽ�ѧ��1���ҳ�� gjjxj1shStuModel ----- ���ҽ�ѧ��1���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxj1shStuModel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));
		String shjb = service.getShjb("view_xszz_com_gjjxj1");

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delGjjxj1xx(actionForm.getPk(), request, shjb);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjjxj1xx(actionForm.getPk(), "ͨ��", request, shjb);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjjxj1xx(actionForm.getPk(), "��ͨ��", request, shjb);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			actionForm.setXn(Base.currXn);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getGjjxj1Tit(shjb);
		List<String[]> resList = service.getGjjxj1Res(queryModel, request,
				actionForm, shjb);
		String xh = DealString.toGBK(actionForm.getXh());
		actionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getGjjxj1ResNum(queryModel, request, shjb)));
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_com_gjjxj1");
		request.setAttribute("tableName", "view_xszz_com_gjjxj1");
		return mapping.findForward("gjjxj1sh");
	}
	
	/**
	 * ���ҽ�ѧ��1���ҳ�� gjjxj1shByFdy ----- ���ҽ�ѧ��1��� ����Ա���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxj1shByFdy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",1));
		String shjb = service.getShjb("view_xszz_com_gjjxj1");

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delGjjxj1xx(actionForm.getPk(), request, shjb);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjjxj1xxByDepModel(actionForm.getPk(), "ͨ��", request, shjb);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjjxj1xxByDepModel(actionForm.getPk(), "��ͨ��", request, shjb);
			queryModel.setGo("go");
		}
		if("tjsh".equalsIgnoreCase(queryModel.getGo())){
			actionForm.setXn(request.getParameter("xn"));
			actionForm.setBjdm(request.getParameter("tjbjdm"));
			actionForm.setUserName(session.getAttribute("userName").toString());
			service.addGjjxj1shtj(actionForm,request);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			actionForm.setXn(Base.currXn);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getGjjxj1Tit(shjb);
		List<String[]> resList = service.getGjjxj1ResByFdy(queryModel, request,actionForm, shjb);
		String xh = DealString.toGBK(actionForm.getXh());
		actionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getGjjxj1ResNum(queryModel, request, shjb)));
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_com_gjjxj1");
		request.setAttribute("tableName", "view_xszz_com_gjjxj1");
		return mapping.findForward("gjjxj1sh");
	}

	/**
	 * ���ҽ�ѧ��1���ҳ�� gjjxj1shDeptModel ----- ���ҽ�ѧ��1��� deptModel���ģʽ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxj1shDeptModel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		HttpSession session = request.getSession();
		ActionForward af = null;		
		
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = session.getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",1));
		String shjb = service.getShjb("view_xszz_com_gjjxj1");

		if("3".equalsIgnoreCase(shjb) && session.getAttribute("fdyQx").equals(true)){//������� �û��Ǹ���Ա ��ѧ��Ϊ��λ���
			//���ѡ���˰༶����ʾ�ύ״̬
			if(!Base.isNull(actionForm.getBjdm())){
				actionForm.setXn(Base.currXn);
				request.setAttribute("tjxx", service.getFdyshtjbxx("gjjxj1",actionForm.getBjdm(),actionForm.getXn()));
			}
			request.setAttribute("fdyshFlag", true);
			af = gjjxj1shByFdy(mapping, form, request, response);
		}else{//�������
			actionForm.setUserName(userName);
			actionForm.setShjb(shjb);
			if ("del".equalsIgnoreCase(queryModel.getGo())) {
				service.delGjjxj1xx(actionForm.getPk(), request, shjb);
				queryModel.setGo("go");
			}
			if ("tg".equalsIgnoreCase(queryModel.getGo())) {
				actionForm.setShjg("ͨ��");
				service.audiGjjxj1ForDep(actionForm, request);
				queryModel.setGo("go");
			}
			if ("btg".equalsIgnoreCase(queryModel.getGo())) {
				actionForm.setShjg("��ͨ��");
				service.audiGjjxj1ForDep(actionForm, request);
				queryModel.setGo("go");
			}
			if ("th".equalsIgnoreCase(queryModel.getGo())) {//�˻�
				actionForm.setShjg("�˻�");
				service.audiGjjxj1ForDep(actionForm, request);
				queryModel.setGo("go");
			}
			if("tjjg".equalsIgnoreCase(queryModel.getGo())){//�ύ��˽��
				service.commitResult(userDep,userName, "gjjxj1", "xn", Base.currXn);
				queryModel.setGo("go");
			}
	
			if (userType.equalsIgnoreCase("xy")) {
				actionForm.setXydm(userDep);
			}
			if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
				actionForm.setXn(Base.currXn);
			}
			BeanUtils.copyProperties(queryModel, actionForm);
			List<HashMap<String, String>> topList = service.getGjjxj1TitForDM(userType);
			List<String[]> resList = service.getGjjxj1ResForDM(queryModel, request,actionForm, shjb);
			String xh = DealString.toGBK(actionForm.getXh());
			actionForm.setXh(xh);
			request.setAttribute("topTr", topList);
			request.setAttribute("rs", resList);
			request.setAttribute("rsNum", resList != null ? resList.size() : 0);
	
			XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
			XszzCommenBean commenBean = new XszzCommenBean();
			BeanUtils.copyProperties(commenBean, actionForm);
			commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�
	
			//actionForm.getPages().setMaxRecord(Integer.parseInt(service.getGjjxj1ResNum(queryModel, request, shjb)));
			request.setAttribute("pk", "xn||xh");
			request.setAttribute("hForm", actionForm);
			request.setAttribute("isQuery", queryModel.getIsQuery());
			request.setAttribute("realTable", "xszz_com_gjjxj1");
			request.setAttribute("tableName", "view_xszz_com_gjjxj1");
			request.setAttribute("tjjg", service.queryXyshjg(userDep,Base.currXn,"gjjxj1"));
			request.setAttribute("shjb", shjb);
			af = mapping.findForward("gjjxj1shdm");
		}
		return af;
	}
	
	/**
	 * ���ҽ�ѧ��1�����ϸ��Ϣҳ�� gjjxj1shdmOne ----- ���ҽ�ѧ��1��� deptModel���ģʽ	
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxj1shdmOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm model = (XszzCommonN05ActionForm)form;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String userType = session.getAttribute("userType").toString();
		String shjb = service.getShjb("view_xszz_com_gjjxj1");
		String type = request.getParameter("type");
		String shjg = request.getParameter("shjg");
		String shyj = request.getParameter("shyj");
		String operFlag = request.getParameter("operFlag");
		String doType = request.getParameter("doType");
		
		if(!Base.isNull(type) && "save".equalsIgnoreCase(type)){
			boolean result = true;
			try{
				model.setShjb(shjb);
				model.setShjg(shjg);
				model.setShyj(shyj);
				model.setUserName(session.getAttribute("userName").toString());
				model.setPk(new String[]{"",pkVal});
				service.audiGjjxj1ForDep(model, request);
			}catch(Exception e){
				result = false;
			}
			request.setAttribute("result", result);
		}
		HashMap<String, String>  map = service.getGjjxj1shtjjg(pkVal,userType,shjb);
		List<String[]> rs = service.getGjjxj1Stush(pkVal,userType,shjb);
		map.put("shjg", shjg);
		
		request.setAttribute("map", map);
		request.setAttribute("operFlag", operFlag);
		request.setAttribute("doType", doType);
		request.setAttribute("rs", rs);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("chkList", service.getChkList(3));
		request.setAttribute("topTr", service.getGjjxj1TitForStush(userType));
		request.setAttribute("chkList", service.getChkList(27));//�����˻�ѡ��
		return mapping.findForward("gjjxj1shdmOne");
	}
	
	/**
	 * ���ҽ�ѧ��1��Ϣ���� gjjxj1Exp ----- ���ҽ�ѧ��1��Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxj1Exp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		QueryModel queryModel = new QueryModel();

		String shjb = service.getShjb("view_xszz_com_gjjxj1");
		BeanUtils.copyProperties(queryModel, actionForm);
		service.getGjjxj1Exp(queryModel, response, request, shjb);
		return mapping.findForward("gjjxj1Exp");
	}
	
	/**
	 * ���ҽ�ѧ��1�����ϸҳ�� gjjxj1shOne ----- ���ҽ�ѧ��1�����ϸҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxj1shOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String shjb = service.getShjb("view_xszz_com_gjjxj1");
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjjxj1xx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		if(Globals.deptModel.equalsIgnoreCase(Globals.getXszzAuditingModel().get(StandardOperation.getXxdm()))){
			//�Բ���Ϊ��λ���
			request.setAttribute("shmodel", "depModel");
		}
		// ------------2010/5/13 edit by luojw-------------
		String xxdm = StandardOperation.getXxdm();
		request.setAttribute("xxdm", xxdm);
		// ------------end-------------
		
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("shjb", shjb);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjjxj1shOne");
	}
	
	/**
	 * ������ҽ�ѧ��1�����Ϣ gjjxj1shSave ---- ������ҽ�ѧ��1�����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxj1shSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String shjb = service.getShjb("view_xszz_com_gjjxj1");
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Gjjxj1Model model = new Gjjxj1Model();
		BeanUtils.copyProperties(model, actionForm);
		boolean bJg = service.saveGjjxj1Shxx(model, request, shjb);// ���������������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjjxj1xx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("shjb", shjb);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjjxj1shSave");
	}

	/**
	 * ������ѧ��1����ҳ��
	 * gjzxj1sq ----- ������ѧ��1����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxj1sq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String doType=request.getParameter("doType");
		String xh = "";// ѧ��
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// �û�������ѧ����ֱ�ӻ�ȡ�û���
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		
		String sfkns = service.getSfkns("view_xszz_com_gjzxj1");
		if ("1".equalsIgnoreCase(sfkns)) {
			request.setAttribute("sfKns", "yes");
		} else {
			request.setAttribute("sfKns", "no");
		}
		if (!"".equalsIgnoreCase(xh)) {
			if ("1".equalsIgnoreCase(sfkns)) {
				request.setAttribute("isKns", dao.isKns(xh));
			}
			stuMap = service.getGjzxj1xx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);
			}
		}
		if("back".equalsIgnoreCase(doType)){
			xh=request.getParameter("xh");
			if(!Base.isNull(xh)){
				pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
				stuMap = service.getGjzxj1xx(pkVal);// �õ���ϸ��Ϣ
			}
		}
		
		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("sfksq", service.getSqQx("������ѧ��", sUserType, xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxj1sq");
	}
	
	/**
	 * ���������ѧ��1������Ϣ gjzxj1sqSave ---- ���������ѧ��1������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxj1sqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Gjzxj1Model model = new Gjzxj1Model();
		BeanUtils.copyProperties(model, actionForm);
		XszzCommonN05Service service = new XszzCommonN05Service();
		boolean bJg = service.saveGjzxj1Sqxx(model, request);// ������Ϣ
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
			stuMap = service.getGjzxj1xx(pkVal);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);// ��ǰѧ��
			}
		}

		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxj1sqSave");
	}

	/**
	 * ������ѧ��1�����ҳ�� gjzxj1sqb ----- ������ѧ��1�����ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxj1sqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		XszzCommonN05Service service = new XszzCommonN05Service();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxj1xx(actionForm.getXn() + actionForm.getXh());
		
		stuMap.put("xxmc", StandardOperation.getXxmc());
		request.setAttribute("rs", stuMap);
		return mapping.findForward("gjzxj1sqb");
	}
	
	/**
	 * ������ѧ��1���ҳ�� gjzxj1sh ----- ������ѧ��1���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxj1sh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));
		String shjb = service.getShjb("view_xszz_com_gjzxj1");

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delGjzxj1xx(actionForm.getPk(), request, shjb);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjzxj1xx(actionForm.getPk(), "ͨ��", request, shjb);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjzxj1xx(actionForm.getPk(), "��ͨ��", request, shjb);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			actionForm.setXn(Base.currXn);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getGjzxj1Tit(shjb);
		List<String[]> resList = service.getGjzxj1Res(queryModel, request,
				actionForm, shjb);
		String xh = DealString.toGBK(actionForm.getXh());
		actionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�
		List<HashMap<String, String>> djList = service.getGjzxjdj();
		request.setAttribute("djList", djList);
		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getGjzxj1ResNum(queryModel, request, shjb)));
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_com_gjzxj1");
		request.setAttribute("tableName", "view_xszz_com_gjzxj1");
		return mapping.findForward("gjzxj1sh");
	}

	/**
	 * ������ѧ��1��Ϣ���� gjzxj1Exp ----- ������ѧ��1��Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxj1Exp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		QueryModel queryModel = new QueryModel();

		String shjb = service.getShjb("view_xszz_com_gjzxj1");
		BeanUtils.copyProperties(queryModel, actionForm);
		service.getGjzxj1Exp(queryModel, response, request, shjb);
		return mapping.findForward("gjzxj1Exp");
	}
	
	/**
	 * ������ѧ��1�����ϸҳ�� gjzxj1shOne ----- ������ѧ��1�����ϸҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxj1shOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String shjb = service.getShjb("view_xszz_com_gjzxj1");
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxj1xx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}

		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("gjzxjList", service.getGjzxjList());
		request.setAttribute("shjb", shjb);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxj1shOne");
	}
	
	/**
	 * ���������ѧ��1�����Ϣ gjzxj1shSave ---- ���������ѧ��1�����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxj1shSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String shjb = service.getShjb("view_xszz_com_gjzxj1");
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Gjzxj1Model model = new Gjzxj1Model();
		BeanUtils.copyProperties(model, actionForm);
		boolean bJg = service.saveGjzxj1Shxx(model, request, shjb);// ���������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxj1xx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("gjzxjList", service.getGjzxjList());
		request.setAttribute("shjb", shjb);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxj1shSave");
	}
	
	/**
	 * ������ѧ��2����ҳ��
	 * gjzxj2sq ----- ������ѧ��2����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxj2sq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String xh = "";// ѧ��
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// �û�������ѧ����ֱ�ӻ�ȡ�û���
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		
		String sfkns = service.getSfkns("view_xszz_com_gjzxj2");
		if ("1".equalsIgnoreCase(sfkns)) {
			request.setAttribute("sfKns", "yes");
		} else {
			request.setAttribute("sfKns", "no");
		}
		if (!"".equalsIgnoreCase(xh)) {
			if ("1".equalsIgnoreCase(sfkns)) {
				request.setAttribute("isKns", dao.isKns(xh));
			}
			stuMap = service.getGjzxj2xx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("rdqk", service.getKns1Rdqk(xh, xn));
				stuMap.put("xn", Base.currXn);
			}
		}

		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("sfksq", service.getSqQx("������ѧ��", sUserType, xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxj2sq");
	}
	
	/**
	 * ���������ѧ��2������Ϣ gjzxj2sqSave ---- ���������ѧ��2������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxj2sqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Gjzxj1Model model = new Gjzxj1Model();
		BeanUtils.copyProperties(model, actionForm);
		XszzCommonN05Service service = new XszzCommonN05Service();
		boolean bJg = service.saveGjzxj2Sqxx(model, request);// ������Ϣ
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
			stuMap = service.getGjzxj2xx(pkVal);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);// ��ǰѧ��
			}
		}

		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxj2sqSave");
	}

	/**
	 * ������ѧ��2�����ҳ�� gjzxj2sqb ----- ������ѧ��2�����ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxj2sqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		XszzCommonN05Service service = new XszzCommonN05Service();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxj2xx(actionForm.getXn() + actionForm.getXh());
		
		String sfzh = Base.chgNull(stuMap.get("sfzh"), "", 1);
		String[] sF = new String[18];
		
		for (int i = 0; i < sfzh.length(); i++) {
			sF[i] = sfzh.substring(i, i+1);
		}
		for (int i = 1; i < 19; i++) {
			stuMap.put("sfzh"+i, sF[i-1]);
		}
		stuMap.put("xxmc", StandardOperation.getXxmc());
		request.setAttribute("rs", stuMap);
		return mapping.findForward("gjzxj2sqb");
	}
	
	/**
	 * ������ѧ��2���ҳ�� gjzxj2sh ----- ������ѧ��2���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxj2sh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));
		String shjb = service.getShjb("view_xszz_com_gjzxj2");

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delGjzxj2xx(actionForm.getPk(), request, shjb);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjzxj2xx(actionForm.getPk(), "ͨ��", request, shjb);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjzxj2xx(actionForm.getPk(), "��ͨ��", request, shjb);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			actionForm.setXn(Base.currXn);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getGjzxj2Tit(shjb);
		List<String[]> resList = service.getGjzxj2Res(queryModel, request,
				actionForm, shjb);
		String xh = DealString.toGBK(actionForm.getXh());
		actionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getGjzxj2ResNum(queryModel, request, shjb)));
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_com_gjzxj2");
		request.setAttribute("tableName", "view_xszz_com_gjzxj2");
		return mapping.findForward("gjzxj2sh");
	}

	/**
	 * ������ѧ��2��Ϣ���� gjzxj2Exp ----- ������ѧ��2��Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxj2Exp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		QueryModel queryModel = new QueryModel();

		String shjb = service.getShjb("view_xszz_com_gjzxj2");
		BeanUtils.copyProperties(queryModel, actionForm);
		service.getGjzxj2Exp(queryModel, response, request, shjb);
		return mapping.findForward("gjzxj2Exp");
	}
	
	/**
	 * ������ѧ��2�����ϸҳ�� gjzxj2shOne ----- ������ѧ��2�����ϸҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxj2shOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String shjb = service.getShjb("view_xszz_com_gjzxj2");
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxj2xx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}

		request.setAttribute("gjzxjList", service.getGjzxjList());
		request.setAttribute("shjb", shjb);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxj2shOne");
	}
	
	/**
	 * ���������ѧ��2�����Ϣ gjzxj2shSave ---- ���������ѧ��2�����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxj2shSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String shjb = service.getShjb("view_xszz_com_gjzxj2");
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Gjzxj1Model model = new Gjzxj1Model();
		BeanUtils.copyProperties(model, actionForm);
		boolean bJg = service.saveGjzxj2Shxx(model, request, shjb);// ���������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxj2xx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		request.setAttribute("gjzxjList", service.getGjzxjList());
		request.setAttribute("shjb", shjb);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxj2shSave");
	}

	/**
	 * ������ѧ��3����ҳ��
	 * gjzxj3sq ----- ������ѧ��3����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxj3sq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//TODO
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String doType =request.getParameter("doType");
		String xh = "";// ѧ��
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// �û�������ѧ����ֱ�ӻ�ȡ�û���
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		
		String sfkns = service.getSfkns("view_xszz_com_gjzxj3");
		if ("1".equalsIgnoreCase(sfkns)) {
			request.setAttribute("sfKns", "yes");
		} else {
			request.setAttribute("sfKns", "no");
		}
		if (!"".equalsIgnoreCase(xh)) {
			if ("1".equalsIgnoreCase(sfkns)) {
				request.setAttribute("isKns", dao.isKns(xh));
			}
			stuMap = service.getGjzxj3xx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);
			}
		}

		// ------------2010/5/13 edit by luojw-------------
		String xxdm = StandardOperation.getXxdm();
		request.setAttribute("xxdm", xxdm);
		// ------------end-------------
		if("back".equalsIgnoreCase(doType)){
			xh=request.getParameter("xh");
			if(!Base.isNull(xh)){
				pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);
			}
		}
		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("sfksq", service.getSqQx("������ѧ��", sUserType, xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxj3sq");
	}
	
	/**
	 * ���������ѧ��3������Ϣ gjzxj3sqSave ---- ���������ѧ��3������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxj3sqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Gjzxj3Model model = new Gjzxj3Model();
		BeanUtils.copyProperties(model, actionForm);
		XszzCommonN05Service service = new XszzCommonN05Service();
		String shjb = service.getShjb("view_xszz_com_gjzxj3");
		
		boolean bJg = service.saveGjzxj3Sqxx(model, request,shjb);// ������Ϣ
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
			stuMap = service.getGjzxj3xx(pkVal);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);// ��ǰѧ��
			}
		}

		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxj3sqSave");
	}

	/**
	 * ������ѧ��3�����ҳ�� gjzxj3sqb ----- ������ѧ��3�����ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxj3sqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		XszzCommonN05Service service = new XszzCommonN05Service();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxj3xx(actionForm.getXn() + actionForm.getXh());
		
		stuMap.put("xxmc", StandardOperation.getXxmc());
		request.setAttribute("rs", stuMap);
		return mapping.findForward("gjzxj3sqb");
	}
	
	/**
	 * ������ѧ��3���ҳ�� gjzxj3sh ----- ������ѧ��3���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxj3sh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xxdm = StandardOperation.getXxdm();
		String auditingModel = Globals.getXszzAuditingModel().get(xxdm);
		ActionForward af = null;
		XszzCommonN05Service service = new XszzCommonN05Service(); 
		String isQuery = Base.chgNull(request.getParameter("isQuery"), "",
				1);
		List<HashMap<String, String>> djList = service.getGjzxjdj();
		request.setAttribute("djList", djList);
		//�˴���������˷�ʽ��һ��Ϊ�����û����ʱ��ѧ����ѯ��ˣ�stuModel������һ��Ϊ���������ʱ��ѧ����ѯ��ˣ�ѧԺ��ѧУ���ʱ���༶��ѯ���ͨ��(classModel)
		if(Globals.stuModel.equalsIgnoreCase(auditingModel) || Base.isNull(auditingModel) || "is".equalsIgnoreCase(isQuery)){
			//�����û����ʱ��ѧ����ѯ��ˣ�stuModel��
			af = gjzxj3shStuModel(mapping, form, request, response);
		}else{
			//���������ʱ��ѧ����ѯ��ˣ�ѧԺ��ѧУ���ʱ���༶��ѯ���ͨ��(classModel)
			af = gjzxj3shDeptModel(mapping, form, request, response);
		}
		return af;
	}
	
	/**
	 * ������ѧ��3���ҳ�� gjzxj3shStuModel ----- ������ѧ��3���StuModel���ģʽ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxj3shStuModel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));
		String shjb = service.getShjb("view_xszz_com_gjzxj3");

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delGjzxj3xx(actionForm.getPk(), request, shjb);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjzxj3xx(actionForm.getPk(), "ͨ��", request, shjb);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjzxj3xx(actionForm.getPk(), "��ͨ��", request, shjb);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			actionForm.setXn(Base.currXn);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getGjzxj3Tit(shjb);
		List<String[]> resList = service.getGjzxj3Res(queryModel, request,
				actionForm, shjb);
		String xh = DealString.toGBK(actionForm.getXh());
		actionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getGjzxj3ResNum(queryModel, request, shjb)));
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_com_gjzxj3");
		request.setAttribute("tableName", "view_xszz_com_gjzxj3");
		return mapping.findForward("gjzxj3sh");
	}
	
	/**
	 * ������ѧ��3���ҳ�� gjzxj3shByFdy ----- ������ѧ��3��� ����Ա���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxj3shByFdy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",1));
		String shjb = service.getShjb("view_xszz_com_gjzxj3");

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delGjzxj3xx(actionForm.getPk(), request, shjb);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjzxj3xxByDepModel(actionForm.getPk(), "ͨ��", request, shjb);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjzxj3xxByDepModel(actionForm.getPk(), "��ͨ��", request, shjb);
			queryModel.setGo("go");
		}
		if("tjsh".equalsIgnoreCase(queryModel.getGo())){
			actionForm.setXn(request.getParameter("xn"));
			actionForm.setBjdm(request.getParameter("tjbjdm"));
			actionForm.setUserName(session.getAttribute("userName").toString());
			service.addGjzxj3shtj(actionForm,request);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			actionForm.setXn(Base.currXn);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getGjzxj3Tit(shjb);
		List<String[]> resList = service.getGjzxj3ResByFdy(queryModel, request,
				actionForm, shjb);
		String xh = DealString.toGBK(actionForm.getXh());
		actionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(Integer.parseInt(service.getGjzxj3ResNum(queryModel, request, shjb)));
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_com_gjzxj3");
		request.setAttribute("tableName", "view_xszz_com_gjzxj3");
		return mapping.findForward("gjzxj3sh");
	}
	
	/**
	 * ������ѧ��3���ҳ�� gjzxj3shDeptModel ----- ������ѧ��3��� deptModel���ģʽ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxj3shDeptModel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		ActionForward af = null;		
		String shjb = service.getShjb("view_xszz_com_gjzxj3");
		
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = session.getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		
		if("3".equalsIgnoreCase(shjb) && session.getAttribute("fdyQx").equals(true)){//������� �û��Ǹ���Ա ��ѧ��Ϊ��λ���
			//���ѡ���˰༶����ʾ�ύ״̬
			if(!Base.isNull(actionForm.getBjdm())){
				actionForm.setXn(Base.currXn);		
				request.setAttribute("tjxx", service.getFdyshtjbxx("gjzxj3",actionForm.getBjdm(),actionForm.getXn()));
			}
			request.setAttribute("fdyshFlag", true);
			af = gjzxj3shByFdy(mapping, form, request, response);
		}else{//�������		
			actionForm.setShjb(shjb);
			actionForm.setUserName(userName);
			if ("del".equalsIgnoreCase(queryModel.getGo())) {
				service.delGjzxj3xx(actionForm.getPk(), request, shjb);
				queryModel.setGo("go");
			}
			if ("tg".equalsIgnoreCase(queryModel.getGo())) {
				actionForm.setShjg("ͨ��");				
				service.audiGjzxj3ForDep(actionForm, request);
				queryModel.setGo("go");
			}
			if ("btg".equalsIgnoreCase(queryModel.getGo())) {
				actionForm.setShjg("��ͨ��");
				service.audiGjzxj3ForDep(actionForm, request);
				queryModel.setGo("go");
			}
			if ("th".equalsIgnoreCase(queryModel.getGo())) {//�˻�
				actionForm.setShjg("�˻�");
				service.audiGjzxj3ForDep(actionForm,request);
				queryModel.setGo("go");
			}
			if ("tjjg".equalsIgnoreCase(queryModel.getGo())) {//�ύ���
				service.commitResult(userDep,userName,"gjzxj3","xn",Base.currXn);
				queryModel.setGo("go");
			}
	
			if (userType.equalsIgnoreCase("xy")) {
				actionForm.setXydm(userDep);
			}
			if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
				actionForm.setXn(Base.currXn);
			}
			BeanUtils.copyProperties(queryModel, actionForm);
			List<HashMap<String, String>> topList = service.getGjzxj3TitForDM(userType);
			List<String[]> resList = service.getGjzxj3ResForDM(queryModel, request,actionForm, shjb);
			
			request.setAttribute("topTr", topList);
			request.setAttribute("rs", resList);
			request.setAttribute("rsNum", resList != null ? resList.size() : 0);
	
			XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
			XszzCommenBean commenBean = new XszzCommenBean();
			BeanUtils.copyProperties(commenBean, actionForm);
			commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�
	
			//actionForm.getPages().setMaxRecord(Integer.parseInt(service.getGjzxj3ResNum(queryModel, request, shjb)));
			request.setAttribute("pk", "xn||xh");
			request.setAttribute("hForm", actionForm);
			request.setAttribute("isQuery", queryModel.getIsQuery());
			request.setAttribute("realTable", "xszz_com_gjzxj3");
			request.setAttribute("tableName", "view_xszz_com_gjzxj3");
			request.setAttribute("tjjg", service.queryXyshjg(userDep,Base.currXn,"gjzxj3"));
			request.setAttribute("shjb", shjb);
			af = mapping.findForward("gjzxj3shdm");
		}
		return af;
	}
	
	/**
	 * ������ѧ��3�����ϸ��Ϣҳ�� gjzxj3shdmOne ----- ������ѧ��3���deptModel���ģʽ	
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxj3shdmOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm model = (XszzCommonN05ActionForm)form;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String userType = session.getAttribute("userType").toString();
		String shjb = service.getShjb("view_xszz_com_gjzxj3");
		String type = request.getParameter("type");
		String shjg = request.getParameter("shjg");
		String shyj = request.getParameter("shyj");
		String operFlag = request.getParameter("operFlag");
		String doType = request.getParameter("doType");
		
		if(!Base.isNull(type) && "save".equalsIgnoreCase(type)){
			boolean result = true;
			try{
				model.setPk(new String[]{"",pkVal});
				model.setShjg(shjg);
				model.setShyj(shyj);
				model.setShjb(shjb);
				model.setUserName(session.getAttribute("userName").toString());
				service.audiGjzxj3ForDep(model, request);
			}catch(Exception e){
				result = false;
			}
			request.setAttribute("result", result);
		}
		HashMap<String, String>  map = service.getGjzxj3shtjjg(pkVal,userType,shjb);
		List<String[]> rs = service.getGjzxj3Stush(pkVal,userType,shjb);
		map.put("shjg", shjg);
		
		request.setAttribute("map", map);
		request.setAttribute("operFlag", operFlag);
		request.setAttribute("doType", doType);
		request.setAttribute("rs", rs);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("chkList", service.getChkList(27));//�����˻�ѡ��
		request.setAttribute("topTr", service.getGjzxj3TitForStush(userType));
		return mapping.findForward("gjzxj3shdmOne");
	}

	/**
	 * ������ѧ��3��Ϣ���� gjzxj3Exp ----- ������ѧ��3��Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxj3Exp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		QueryModel queryModel = new QueryModel();

		String shjb = service.getShjb("view_xszz_com_gjzxj3");
		BeanUtils.copyProperties(queryModel, actionForm);
		service.getGjzxj3Exp(queryModel, response, request, shjb);
		return mapping.findForward("gjzxj3Exp");
	}
	
	/**
	 * ������ѧ��3�����ϸҳ�� gjzxj3shOne ----- ������ѧ��3�����ϸҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxj3shOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String shjb = service.getShjb("view_xszz_com_gjzxj3");
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxj3xx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		if(Globals.deptModel.equalsIgnoreCase(Globals.getXszzAuditingModel().get(StandardOperation.getXxdm()))){
			//�Բ���Ϊ��λ���
			request.setAttribute("shmodel", "depModel");
		}

		// ------------2010/5/13 edit by luojw-------------
		String xxdm = StandardOperation.getXxdm();
		request.setAttribute("xxdm", xxdm);
		// ------------end-------------
		
		request.setAttribute("gjzxjList", service.getGjzxjList());
		request.setAttribute("shjb", shjb);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxj3shOne");
	}
	
	/**
	 * ���������ѧ��3�����Ϣ gjzxj3shSave ---- ���������ѧ��3�����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxj3shSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String shjb = service.getShjb("view_xszz_com_gjzxj3");
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Gjzxj3Model model = new Gjzxj3Model();
		BeanUtils.copyProperties(model, actionForm);
		boolean bJg = service.saveGjzxj3Shxx(model, request, shjb);// ���������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxj3xx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		request.setAttribute("gjzxjList", service.getGjzxjList());
		request.setAttribute("shjb", shjb);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxj3shSave");
	}
	
	/**
	 * ������־��ѧ��1����ҳ��
	 * gjlzjxj1sq ----- ������־��ѧ��1����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxj1sq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String xh = "";// ѧ��
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// �û�������ѧ����ֱ�ӻ�ȡ�û���
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		String sfkns = service.getSfkns("view_xszz_com_gjlzjxj1");
		if ("1".equalsIgnoreCase(sfkns)) {
			request.setAttribute("sfKns", "yes");
		} else {
			request.setAttribute("sfKns", "no");
		}
		if (!"".equalsIgnoreCase(xh)) {
			if ("1".equalsIgnoreCase(sfkns)) {
				request.setAttribute("isKns", dao.isKns(xh));
			}
			stuMap = service.getGjlzjxj1xx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);
			}
		}

		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("sfksq", service.getSqQx("������־��ѧ��", sUserType, xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjlzjxj1sq");
	}

	/**
	 * ���������־��ѧ��1������Ϣ gjlzjxj1sqSave ---- ���������־��ѧ��1������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxj1sqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Gjlzjxj1Model model = new Gjlzjxj1Model();
		BeanUtils.copyProperties(model, actionForm);
		XszzCommonN05Service service = new XszzCommonN05Service();
		boolean bJg = service.saveGjlzjxj1Sqxx(model, request);// ������Ϣ
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
			stuMap = service.getGjlzjxj1xx(pkVal);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);// ��ǰѧ��
			}
		}

		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjlzjxj1sqSave");
	}

	/**
	 * ������־��ѧ��1�����ҳ�� gjlzjxj1sqb ----- ������־��ѧ��1�����ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxj1sqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		XszzCommonN05Service service = new XszzCommonN05Service();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjlzjxj1xx(actionForm.getXn() + actionForm.getXh());
		
		stuMap.put("xxmc", StandardOperation.getXxmc());
		request.setAttribute("rs", stuMap);
		return mapping.findForward("gjlzjxj1sqb");
	}
	
	/**
	 * ������־��ѧ��1���ҳ�� gjlzjxj1sh ----- ������־��ѧ��1���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxj1sh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));
		String shjb = service.getShjb("view_xszz_com_gjlzjxj1");

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delGjlzjxj1xx(actionForm.getPk(), request, shjb);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjlzjxj1xx(actionForm.getPk(), "ͨ��", request, shjb);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjlzjxj1xx(actionForm.getPk(), "��ͨ��", request, shjb);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			actionForm.setXn(Base.currXn);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getGjlzjxj1Tit(shjb);
		List<String[]> resList = service.getGjlzjxj1Res(queryModel, request,
				actionForm, shjb);
		String xh = DealString.toGBK(actionForm.getXh());
		actionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getGjlzjxj1ResNum(queryModel, request, shjb)));
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_com_gjlzjxj1");
		request.setAttribute("tableName", "view_xszz_com_gjlzjxj1");
		return mapping.findForward("gjlzjxj1sh");
	}

	/**
	 * ������־��ѧ��1��Ϣ���� gjlzjxj1Exp ----- ������־��ѧ��1��Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxj1Exp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		QueryModel queryModel = new QueryModel();

		String shjb = service.getShjb("view_xszz_com_gjlzjxj1");
		BeanUtils.copyProperties(queryModel, actionForm);
		service.getGjlzjxj1Exp(queryModel, response, request, shjb);
		return mapping.findForward("gjlzjxj1Exp");
	}
	
	/**
	 * ������־��ѧ��1�����ϸҳ�� gjlzjxj1shOne ----- ������־��ѧ��1�����ϸҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxj1shOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String shjb = service.getShjb("view_xszz_com_gjlzjxj1");
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjlzjxj1xx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}

		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("shjb", shjb);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjlzjxj1shOne");
	}
	
	/**
	 * ���������־��ѧ��1�����Ϣ gjlzjxj1shSave ---- ���������־��ѧ��1�����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxj1shSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String shjb = service.getShjb("view_xszz_com_gjlzjxj1");
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Gjlzjxj1Model model = new Gjlzjxj1Model();
		BeanUtils.copyProperties(model, actionForm);
		boolean bJg = service.saveGjlzjxj1Shxx(model, request, shjb);// ���������������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjlzjxj1xx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("shjb", shjb);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjlzjxj1shSave");
	}

	/**
	 * ������־��ѧ��2����ҳ��
	 * gjlzjxj2sq ----- ������־��ѧ��2����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxj2sq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String xh = "";// ѧ��
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// �û�������ѧ����ֱ�ӻ�ȡ�û���
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		String sfkns = service.getSfkns("view_xszz_com_gjlzjxj2");
		if ("1".equalsIgnoreCase(sfkns)) {
			request.setAttribute("sfKns", "yes");
		} else {
			request.setAttribute("sfKns", "no");
		}
		if (!"".equalsIgnoreCase(xh)) {
			if ("1".equalsIgnoreCase(sfkns)) {
				request.setAttribute("isKns", dao.isKns(xh));
			}
			stuMap = service.getGjlzjxj2xx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);
			}
		}

		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("sfksq", service.getSqQx("������־��ѧ��", sUserType, xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjlzjxj2sq");
	}

	/**
	 * ���������־��ѧ��2������Ϣ gjlzjxj2sqSave ---- ���������־��ѧ��2������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxj2sqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Gjlzjxj2Model model = new Gjlzjxj2Model();
		BeanUtils.copyProperties(model, actionForm);
		XszzCommonN05Service service = new XszzCommonN05Service();
		boolean bJg = service.saveGjlzjxj2Sqxx(model, request);// ������Ϣ
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
			stuMap = service.getGjlzjxj2xx(pkVal);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);// ��ǰѧ��
			}
		}

		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjlzjxj2sqSave");
	}

	/**
	 * ������־��ѧ��2�����ҳ�� gjlzjxj2sqb ----- ������־��ѧ��2�����ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxj2sqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		XszzCommonN05Service service = new XszzCommonN05Service();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjlzjxj2xx(actionForm.getXn() + actionForm.getXh());
		
		stuMap.put("xxmc", StandardOperation.getXxmc());
		request.setAttribute("rs", stuMap);
		return mapping.findForward("gjlzjxj2sqb");
	}
	
	/**
	 * ������־��ѧ��2���ҳ�� gjlzjxj2sh ----- ������־��ѧ��2���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxj2sh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));
		String shjb = service.getShjb("view_xszz_com_gjlzjxj2");

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delGjlzjxj2xx(actionForm.getPk(), request, shjb);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjlzjxj2xx(actionForm.getPk(), "ͨ��", request, shjb);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjlzjxj2xx(actionForm.getPk(), "��ͨ��", request, shjb);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			actionForm.setXn(Base.currXn);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getGjlzjxj2Tit(shjb);
		List<String[]> resList = service.getGjlzjxj2Res(queryModel, request,
				actionForm, shjb);
		String xh = DealString.toGBK(actionForm.getXh());
		actionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getGjlzjxj2ResNum(queryModel, request, shjb)));
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_com_gjlzjxj2");
		request.setAttribute("tableName", "view_xszz_com_gjlzjxj2");
		return mapping.findForward("gjlzjxj2sh");
	}

	/**
	 * ������־��ѧ��2��Ϣ���� gjlzjxj2Exp ----- ������־��ѧ��2��Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxj2Exp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		QueryModel queryModel = new QueryModel();

		String shjb = service.getShjb("view_xszz_com_gjlzjxj2");
		BeanUtils.copyProperties(queryModel, actionForm);
		service.getGjlzjxj2Exp(queryModel, response, request, shjb);
		return mapping.findForward("gjlzjxj2Exp");
	}
	
	/**
	 * ������־��ѧ��2�����ϸҳ�� gjlzjxj2shOne ----- ������־��ѧ��2�����ϸҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxj2shOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String shjb = service.getShjb("view_xszz_com_gjlzjxj2");
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjlzjxj2xx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}

		request.setAttribute("shjb", shjb);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjlzjxj2shOne");
	}
	
	/**
	 * ���������־��ѧ��2�����Ϣ gjlzjxj2shSave ---- ���������־��ѧ��2�����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxj2shSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String shjb = service.getShjb("view_xszz_com_gjlzjxj2");
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Gjlzjxj2Model model = new Gjlzjxj2Model();
		BeanUtils.copyProperties(model, actionForm);
		boolean bJg = service.saveGjlzjxj2Shxx(model, request, shjb);// ���������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjlzjxj2xx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		request.setAttribute("shjb", shjb);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjlzjxj2shSave");
	}
	
	/**
	 * ѧ�Ѽ���1����ҳ��
	 * xfjm1sq ----- ѧ�Ѽ���1����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfjm1sq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String xh = "";// ѧ��
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// �û�������ѧ����ֱ�ӻ�ȡ�û���
		String xn = Base.isNull(request.getParameter("xn")) ? Base.currXn
				: request.getParameter("xn");
		xh = Base.isNull(request.getParameter("xh")) ? xh : request
				.getParameter("xh");
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		String sfkns = service.getSfkns("view_xszz_com_xfjm1");
		stuMap.put("xn", xn);
		if ("1".equalsIgnoreCase(sfkns)) {
			request.setAttribute("sfKns", "yes");
		} else {
			request.setAttribute("sfKns", "no");
		}
		if (!"".equalsIgnoreCase(xh)) {
			if ("1".equalsIgnoreCase(sfkns)) {
				request.setAttribute("isKns", dao.isKns(xh));
			}
			stuMap = service.getXfjm1xx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("ymcs", service.getXfjm1cs(xh));
				stuMap.put("xn", Base.currXn);
			}
		}

		// ================�Ͼ���ʦ ѧ�����==================
		// edit by luojw 2010/7/19
		
		String xxdm = Base.xxdm;
		String jg = request.getParameter("jg");
		request.setAttribute("jg", jg);
		
		if (Globals.XXDM_NJJS.equalsIgnoreCase(xxdm) && !Base.isNull(xh)) {
			
			XszzTyForm model = new XszzTyForm();
			model.setXn(xn);
			model.setXh(xh);
			
			XszzNjjsService njjsService = new XszzNjjsService();
			//�շ���Ϣ�б�
			List<HashMap<String,String>> sfList = njjsService.getSfxxList(model);
			request.setAttribute("sfList", sfList);
		}
		// ================�Ͼ���ʦ ѧ����� end==================
		
		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("sfksq", service.getSqQx("ѧ�Ѽ���", sUserType, xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xfjm1sq");
	}

	/**
	 * ����ѧ�Ѽ���1������Ϣ xfjm1sqSave ---- ����ѧ�Ѽ���1������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfjm1sqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Xfjm1Model model = new Xfjm1Model();
		BeanUtils.copyProperties(model, actionForm);
		XszzCommonN05Service service = new XszzCommonN05Service();
		boolean bJg = service.saveXfjm1Sqxx(model, request);// ������Ϣ
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
			stuMap = service.getXfjm1xx(pkVal);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);// ��ǰѧ��
			}
		}

		// ================�Ͼ���ʦ ѧ�����==================
		// edit by luojw 2010/7/19
		
		String xxdm = Base.xxdm;
		
		if (Globals.XXDM_NJJS.equalsIgnoreCase(xxdm) && bJg) {
				
			actionForm.setXn(xn);
			
			XszzNjjsService njjsService = new XszzNjjsService();
			
			//�շ���Ϣ�б�
			boolean result = njjsService.saveXfjm(actionForm);
			request.setAttribute("result", result);
			
			XszzTyForm tyModel = new XszzTyForm();
			tyModel.setXn(xn);
			tyModel.setXh(xh);
			
			//�շ���Ϣ�б�
			List<HashMap<String,String>> sfList = njjsService.getSfxxList(tyModel);
			request.setAttribute("sfList", sfList);
		}
		// ================�Ͼ���ʦ ѧ����� end==================
		
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xfjm1sqSave");
	}

	/**
	 * ѧ�Ѽ���1�����ҳ�� xfjm1sqb ----- ѧ�Ѽ���1�����ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfjm1sqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		XszzCommonN05Service service = new XszzCommonN05Service();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getXfjm1xx(actionForm.getXn() + actionForm.getXh());
		
		stuMap.put("xxmc", StandardOperation.getXxmc());
		request.setAttribute("rs", stuMap);
		

		// ================�Ͼ���ʦ ѧ�����==================
		// edit by luojw 2010/7/19

		String xxdm = Base.xxdm;
		String xn = stuMap.get("xn");
		String xh = stuMap.get("xh");
		String type = request.getParameter("type");
		String jg = request.getParameter("jg");
		request.setAttribute("type", type);
		request.setAttribute("jg", jg);
		
		if (Globals.XXDM_NJJS.equalsIgnoreCase(xxdm) && !Base.isNull(xh)) {

			XszzNjjsService njjsService = new XszzNjjsService();

			XszzTyForm tyModel = new XszzTyForm();
			tyModel.setXn(xn);
			tyModel.setXh(xh);

			// �շ���Ϣ�б�
			List<HashMap<String, String>> sfList = njjsService
					.getSfxxList(tyModel);
			request.setAttribute("sfList", sfList);
		}
		// ================�Ͼ���ʦ ѧ����� end==================
		
		return mapping.findForward("xfjm1sqb");
	}
	
	/**
	 * ѧ�Ѽ���1���ҳ�� xfjm1sh ----- ѧ�Ѽ���1���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfjm1sh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));
		String shjb = service.getShjb("view_xszz_com_xfjm1");

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delXfjm1xx(actionForm.getPk(), request, shjb);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modXfjm1xx(actionForm.getPk(), "ͨ��", request, shjb);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modXfjm1xx(actionForm.getPk(), "��ͨ��", request, shjb);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			actionForm.setXn(Base.currXn);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getXfjm1Tit(shjb);
		List<String[]> resList = service.getXfjm1Res(queryModel, request,
				actionForm, shjb);
		String xh = DealString.toGBK(actionForm.getXh());
		actionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getXfjm1ResNum(queryModel, request, shjb)));
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_com_xfjm1");
		request.setAttribute("tableName", "view_xszz_com_xfjm1");
		return mapping.findForward("xfjm1sh");
	}

	/**
	 * ѧ�Ѽ���1��Ϣ���� xfjm1Exp ----- ѧ�Ѽ���1��Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfjm1Exp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		QueryModel queryModel = new QueryModel();

		String shjb = service.getShjb("view_xszz_com_xfjm1");
		BeanUtils.copyProperties(queryModel, actionForm);
		service.getXfjm1Exp(queryModel, response, request, shjb);
		return mapping.findForward("xfjm1Exp");
	}
	
	/**
	 * ѧ�Ѽ���1�����ϸҳ�� xfjm1shOne ----- ѧ�Ѽ���1�����ϸҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfjm1shOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String shjb = service.getShjb("view_xszz_com_xfjm1");
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getXfjm1xx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}

		request.setAttribute("shjb", shjb);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		
		// ================�Ͼ���ʦ ѧ�����==================
		// edit by luojw 2010/7/19

		String xxdm = Base.xxdm;
		String xn = stuMap.get("xn");
		String xh = stuMap.get("xh");

		if (Globals.XXDM_NJJS.equalsIgnoreCase(xxdm) && !Base.isNull(xh)) {

			XszzNjjsService njjsService = new XszzNjjsService();

			XszzTyForm tyModel = new XszzTyForm();
			tyModel.setXn(xn);
			tyModel.setXh(xh);

			// �շ���Ϣ�б�
			List<HashMap<String, String>> sfList = njjsService
					.getSfxxList(tyModel);
			request.setAttribute("sfList", sfList);
		}
		// ================�Ͼ���ʦ ѧ����� end==================
		
		return mapping.findForward("xfjm1shOne");
	}
	
	/**
	 * ����ѧ�Ѽ���1�����Ϣ xfjm1shSave ---- ����ѧ�Ѽ���1�����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfjm1shSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String shjb = service.getShjb("view_xszz_com_xfjm1");
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Xfjm1Model model = new Xfjm1Model();
		BeanUtils.copyProperties(model, actionForm);
		boolean bJg = service.saveXfjm1Shxx(model, request, shjb);// ���������������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getXfjm1xx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		request.setAttribute("shjb", shjb);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xfjm1shSave");
	}

	/**
	 * ѧ�Ѽ���2����ҳ��
	 * xfjm2sq ----- ѧ�Ѽ���2����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfjm2sq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String xh = "";// ѧ��
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// �û�������ѧ����ֱ�ӻ�ȡ�û���
		String xn = Base.currXn;
		String xq = Base.currXq;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xq + xh : pkVal;
		xh = pkVal.substring(11);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		String sfkns = service.getSfkns("view_xszz_com_xfjm2");
		if ("1".equalsIgnoreCase(sfkns)) {
			request.setAttribute("sfKns", "yes");
		} else {
			request.setAttribute("sfKns", "no");
		}
		if (!"".equalsIgnoreCase(xh)) {
			if ("1".equalsIgnoreCase(sfkns)) {
				request.setAttribute("isKns", dao.isKns(xh));
			}
			stuMap = service.getXfjm2xx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);
				stuMap.put("xqmc", Base.getDqxqmc());
				stuMap.put("xq", Base.currXq);
			}
		}

		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("sfksq", service.getSqQx("ѧ�Ѽ���", sUserType, xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xfjm2sq");
	}

	/**
	 * ����ѧ�Ѽ���2������Ϣ xfjm2sqSave ---- ����ѧ�Ѽ���2������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfjm2sqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Xfjm2Model model = new Xfjm2Model();
		BeanUtils.copyProperties(model, actionForm);
		XszzCommonN05Service service = new XszzCommonN05Service();
		String shjb = service.getShjb("view_xszz_com_xfjm2");
		
		boolean bJg = service.saveXfjm2Sqxx(model, request,shjb);// ������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String xq = model.getXq();
		String xn = model.getXn();
		String pkVal = xn + xq + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getXfjm2xx(pkVal);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);// ��ǰѧ��
				stuMap.put("xqmc", Base.getDqxqmc());
				stuMap.put("xq", Base.currXq);
			}
		}

		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xfjm2sqSave");
	}

	/**
	 * ѧ�Ѽ���2�����ҳ�� xfjm2sqb ----- ѧ�Ѽ���2�����ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfjm2sqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		XszzCommonN05Service service = new XszzCommonN05Service();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getXfjm2xx(actionForm.getXn() + actionForm.getXq()
				+ actionForm.getXh());
		request.setAttribute("rs", stuMap);
		return mapping.findForward("xfjm2sqb");
	}
	
	/**
	 * ѧ�Ѽ���2���ҳ�� xfjm2sh ----- ѧ�Ѽ���2���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfjm2sh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xxdm = StandardOperation.getXxdm();
		String auditingModel = Globals.getXszzAuditingModel().get(xxdm);
		ActionForward af = null;
		String isQuery = Base.chgNull(request.getParameter("isQuery"), "",1);
		if(Globals.stuModel.equalsIgnoreCase(auditingModel) || Base.isNull(auditingModel) || "is".equalsIgnoreCase(isQuery)){
			//��ѧ��Ϊ��λ���
			af = xfjm2shStuModel(mapping, form, request, response);
		}else{//�Բ���Ϊ��λ���
			af = xfjm2shDeptModel(mapping, form, request, response);
		}
		return af;
	}
	
	/**
	 * ѧ�Ѽ���2���ҳ�� xfjm2shStuModel ----- ѧ�Ѽ���2���StuModel���ģʽ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfjm2shStuModel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));
		String shjb = service.getShjb("view_xszz_com_xfjm2");

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delXfjm2xx(actionForm.getPk(), request, shjb);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modXfjm2xx(actionForm.getPk(), "ͨ��", request, shjb);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modXfjm2xx(actionForm.getPk(), "��ͨ��", request, shjb);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			actionForm.setXn(Base.currXn);
			actionForm.setXq(Base.currXq);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getXfjm2Tit(shjb);
		List<String[]> resList = service.getXfjm2Res(queryModel, request,
				actionForm, shjb);
		String xh = DealString.toGBK(actionForm.getXh());
		actionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getXfjm2ResNum(queryModel, request, shjb)));
		request.setAttribute("pk", "xn||xq||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_com_xfjm2");
		request.setAttribute("tableName", "view_xszz_com_xfjm2");
		return mapping.findForward("xfjm2sh");
	}
	
	
	/**
	 * ѧ�Ѽ���2���ҳ�� xfjm2shByFdy ----- ѧ�Ѽ���2��� ����Ա���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfjm2shByFdy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",1));
		String shjb = service.getShjb("view_xszz_com_xfjm2");

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delXfjm2xx(actionForm.getPk(), request, shjb);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modXfjm2xxByDepModel(actionForm.getPk(), "ͨ��", request, shjb);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modXfjm2xxByDepModel(actionForm.getPk(), "��ͨ��", request, shjb);
			queryModel.setGo("go");
		}
		if("tjsh".equalsIgnoreCase(queryModel.getGo())){
			actionForm.setXn(request.getParameter("xn"));
			actionForm.setXq(request.getParameter("xq"));
			actionForm.setBjdm(request.getParameter("tjbjdm"));
			actionForm.setUserName(session.getAttribute("userName").toString());
			service.addXfjs2shtj(actionForm,request);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			actionForm.setXn(Base.currXn);
			actionForm.setXq(Base.currXq);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getXfjm2Tit(shjb);
		List<String[]> resList = service.getXfjm2ResByFdy(queryModel, request, actionForm);
		String xh = DealString.toGBK(actionForm.getXh());
		actionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getXfjm2ResNum(queryModel, request, shjb)));
		request.setAttribute("pk", "xn||xq||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_com_xfjm2");
		request.setAttribute("tableName", "view_xszz_com_xfjm2");
		return mapping.findForward("xfjm2sh");
	}
	
	/**
	 * ѧ�Ѽ���2���ҳ�� xfjm2shdeptModel ----- ѧ�Ѽ���2���deptModel���ģʽ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfjm2shDeptModel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		HttpSession session = request.getSession();		
		ActionForward af = null;		
		String shjb = service.getShjb("view_xszz_com_xfjm2");
		String userName = session.getAttribute("userName").toString();
		
		if("3".equalsIgnoreCase(shjb) && session.getAttribute("fdyQx").equals(true)){//������� �û��Ǹ���Ա ��ѧ��Ϊ��λ���
			//���ѡ���˰༶����ʾ�ύ״̬
			if(!Base.isNull(actionForm.getBjdm())){
				actionForm.setXn(Base.currXn);
				actionForm.setXq(Base.currXq);			
				request.setAttribute("tjxx", service.getFdyshtjbxx("xfjm2",actionForm.getBjdm(),actionForm.getXn()+actionForm.getXq()));
			}
			request.setAttribute("fdyshFlag", true);
			af = xfjm2shByFdy(mapping, form, request, response);
		}else{//�������
			String userType = request.getSession().getAttribute("userType").toString();
			String userDep = request.getSession().getAttribute("userDep").toString();
			QueryModel queryModel = new QueryModel();
			queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
			queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",1));
			String pk = "xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType) ? "xn||xq||xydm" : "xn||xq||bjdm";//ѧԺ�û�
			
			actionForm.setPkcolumn(pk);	
			actionForm.setUserName(userName);
			actionForm.setShjb(shjb);
			if ("tg".equalsIgnoreCase(queryModel.getGo())) {
				actionForm.setShjg("ͨ��");
				service.audiXfjm2ForDep(actionForm, request);
				queryModel.setGo("go");
			}
			if ("btg".equalsIgnoreCase(queryModel.getGo())) {
				actionForm.setShjg("��ͨ��");
				service.audiXfjm2ForDep(actionForm, request);
				queryModel.setGo("go");
			}
			if ("th".equalsIgnoreCase(queryModel.getGo())) {//�˻�
				actionForm.setShjg("�˻�");
				service.audiXfjm2ForDep(actionForm,request);
				queryModel.setGo("go");
			}
			if("tjjg".equalsIgnoreCase(queryModel.getGo())){//�ύ��˽��
				service.commitResult(userDep,userName,"xfjm2","xn||xq",Base.currXn+Base.currXq);
				queryModel.setGo("go");
			}

			if (userType.equalsIgnoreCase("xy")) {
				actionForm.setXydm(userDep);
			}
			if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
				actionForm.setXn(Base.currXn);
				actionForm.setXq(Base.currXq);
			}
			BeanUtils.copyProperties(queryModel, actionForm);
			List<HashMap<String, String>> topList = service.getXfjm2TitForDM(shjb,userType);//��ȡ��ͷ
			List<String[]> resList = service.getXfjm2ResForDM(queryModel, request, actionForm, shjb);
			
			request.setAttribute("topTr", topList);
			request.setAttribute("rs", resList);
			request.setAttribute("rsNum", resList != null ? resList.size() : 0);

			XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
			XszzCommenBean commenBean = new XszzCommenBean();
			BeanUtils.copyProperties(commenBean, actionForm);
			commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

			//actionForm.getPages().setMaxRecord(Integer.parseInt(service.getXfjm2ResNum(queryModel, request, shjb)));
			request.setAttribute("pk", pk);
			request.setAttribute("hForm", actionForm);
			request.setAttribute("isQuery", queryModel.getIsQuery());
			request.setAttribute("realTable", "xszz_com_xfjm2");
			request.setAttribute("tableName", "view_xszz_com_xfjm2");	
			request.setAttribute("tjjg", service.queryXyshjg(userDep,Base.currXn+Base.currXq,"xfjm2"));
			request.setAttribute("shjb", shjb);
			af = mapping.findForward("xfjm2shdm");
		}	
		return af;
	}
	
	/**
	 * ѧ�Ѽ���2�����ϸ��Ϣҳ�� xfjm2shdmOne ----- ѧ�Ѽ���2���deptModel���ģʽ	
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfjm2shdmOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();	
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String shjb = service.getShjb("view_xszz_com_xfjm2");
		String type = request.getParameter("type");
		String shjg = request.getParameter("shjg");
		String shyj = request.getParameter("shyj");
		String operFlag = request.getParameter("operFlag");
		String doType = request.getParameter("doType");
		
		if(!Base.isNull(type) && "save".equalsIgnoreCase(type)){
			boolean result = true;
			try{
				actionForm.setPk(new String[]{"",pkVal});
				actionForm.setShjg(shjg);
				actionForm.setShyj(shyj);
				actionForm.setShjb(shjb);
				actionForm.setUserName(userName);
				service.audiXfjm2ForDep(actionForm, request);
			}catch(Exception e){
				result = false;
			}
			request.setAttribute("result", result);
		}
		HashMap<String, String>  map = service.getXfjm2shtjjg(pkVal,userType,shjb);
		List<String[]> rs = service.getXfjm2Stush(pkVal,userType,shjb);
		map.put("shjg", shjg);
		map.put("pzjmje", rs.get(0)[9]);
		
		request.setAttribute("map", map);
		request.setAttribute("operFlag", operFlag);
		request.setAttribute("rs", rs);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("chkList", service.getChkList(27));
		request.setAttribute("topTr", service.getXfjm2TitForStush(userType));
		request.setAttribute("doType", doType);
		return mapping.findForward("xfjm2shdmOne");
	}
	

	/**
	 * ѧ�Ѽ���2��Ϣ���� xfjm2Exp ----- ѧ�Ѽ���2��Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfjm2Exp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		QueryModel queryModel = new QueryModel();
		
		String shjb = service.getShjb("view_xszz_com_xfjm2");
		BeanUtils.copyProperties(queryModel, actionForm);
		service.getXfjm2Exp(queryModel, response, request, shjb);
		return mapping.findForward("xfjm2Exp");
	}
	
	/**
	 * ѧ�Ѽ���2�����ϸҳ�� xfjm2shOne ----- ѧ�Ѽ���2�����ϸҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfjm2shOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String shjb = service.getShjb("view_xszz_com_xfjm2");		
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getXfjm2xx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		if(Globals.deptModel.equalsIgnoreCase(Globals.getXszzAuditingModel().get(StandardOperation.getXxdm()))){
			//�Բ���Ϊ��λ���
			request.setAttribute("shmodel", "depModel");
		}
		request.setAttribute("shjb", shjb);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xfjm2shOne");
	}
	
	/**
	 * ����ѧ�Ѽ���2�����Ϣ xfjm2shSave ---- ����ѧ�Ѽ���2�����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfjm2shSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String shjb = service.getShjb("view_xszz_com_xfjm2");
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Xfjm2Model model = new Xfjm2Model();
		BeanUtils.copyProperties(model, actionForm);
		boolean bJg = service.saveXfjm2Shxx(model, request, shjb);// ���������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getXfjm2xx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		request.setAttribute("shjb", shjb);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xfjm2shSave");
	}
	
	/**
	 * ѧ�ѻ���1����ҳ��
	 * xfhj1sq ----- ѧ�ѻ���1����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfhj1sq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String xh = "";// ѧ��
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// �û�������ѧ����ֱ�ӻ�ȡ�û���
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		String sfkns = service.getSfkns("view_xszz_com_xfhj1");
		if ("1".equalsIgnoreCase(sfkns)) {
			request.setAttribute("sfKns", "yes");
		} else {
			request.setAttribute("sfKns", "no");
		}
		if (!"".equalsIgnoreCase(xh)) {
			if ("1".equalsIgnoreCase(sfkns)) {
				request.setAttribute("isKns", dao.isKns(xh));
			}
			stuMap = service.getXfhj1xx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);
			}
		}

		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("sfksq", service.getSqQx("ѧ�ѻ���", sUserType, xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xfhj1sq");
	}

	/**
	 * ����ѧ�ѻ���1������Ϣ xfhj1sqSave ---- ����ѧ�ѻ���1������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfhj1sqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Xfhj1Model model = new Xfhj1Model();
		BeanUtils.copyProperties(model, actionForm);
		XszzCommonN05Service service = new XszzCommonN05Service();
		boolean bJg = service.saveXfhj1Sqxx(model, request);// ������Ϣ
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
			stuMap = service.getXfhj1xx(pkVal);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);// ��ǰѧ��
			}
		}

		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xfhj1sqSave");
	}

	/**
	 * ѧ�ѻ���1�����ҳ�� xfhj1sqb ----- ѧ�ѻ���1�����ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfhj1sqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		XszzCommonN05Service service = new XszzCommonN05Service();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getXfhj1xx(actionForm.getXn() + actionForm.getXh());
		
		stuMap.put("xxmc", StandardOperation.getXxmc());
		String hjqx = Base.chgNull(stuMap.get("hjqx"), "", 1);
		
		if (hjqx.length() == 10) {
			String sT = hjqx.substring(0, 4) + "��" + hjqx.substring(5, 7) + "��" + hjqx.substring(8) + "��";
			hjqx = sT;
		}
		stuMap.put("hjqx", hjqx);
		
		request.setAttribute("rs", stuMap);
		return mapping.findForward("xfhj1sqb");
	}
	
	/**
	 * ѧ�ѻ���1���ҳ�� xfhj1sh ----- ѧ�ѻ���1���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfhj1sh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));
		String shjb = service.getShjb("view_xszz_com_xfhj1");

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delXfhj1xx(actionForm.getPk(), request, shjb);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modXfhj1xx(actionForm.getPk(), "ͨ��", request, shjb);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modXfhj1xx(actionForm.getPk(), "��ͨ��", request, shjb);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			actionForm.setXn(Base.currXn);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getXfhj1Tit(shjb);
		List<String[]> resList = service.getXfhj1Res(queryModel, request,
				actionForm, shjb);
		String xh = DealString.toGBK(actionForm.getXh());
		actionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getXfhj1ResNum(queryModel, request, shjb)));
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_com_xfhj1");
		request.setAttribute("tableName", "view_xszz_com_xfhj1");
		return mapping.findForward("xfhj1sh");
	}

	/**
	 * ѧ�ѻ���1��Ϣ���� xfhj1Exp ----- ѧ�ѻ���1��Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfhj1Exp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		QueryModel queryModel = new QueryModel();

		String shjb = service.getShjb("view_xszz_com_xfhj1");
		BeanUtils.copyProperties(queryModel, actionForm);
		service.getXfhj1Exp(queryModel, response, request, shjb);
		return mapping.findForward("xfhj1Exp");
	}
	
	/**
	 * ѧ�ѻ���1�����ϸҳ�� xfhj1shOne ----- ѧ�ѻ���1�����ϸҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfhj1shOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String shjb = service.getShjb("view_xszz_com_xfhj1");
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getXfhj1xx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}

		request.setAttribute("shjb", shjb);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xfhj1shOne");
	}
	
	/**
	 * ����ѧ�ѻ���1�����Ϣ xfhj1shSave ---- ����ѧ�ѻ���1�����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfhj1shSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String shjb = service.getShjb("view_xszz_com_xfhj1");
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Xfhj1Model model = new Xfhj1Model();
		BeanUtils.copyProperties(model, actionForm);
		boolean bJg = service.saveXfhj1Shxx(model, request, shjb);// ���������������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getXfhj1xx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		request.setAttribute("shjb", shjb);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xfhj1shSave");
	}
	
	/**
	 * ѧ�ѻ���2����ҳ��
	 * xfhj2sq ----- ѧ�ѻ���2����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfhj2sq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String xh = "";// ѧ��
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// �û�������ѧ����ֱ�ӻ�ȡ�û���
		String xsxh=xh;
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		String sfkns = service.getSfkns("view_xszz_com_xfhj2");
		if ("1".equalsIgnoreCase(sfkns)) {
			request.setAttribute("sfKns", "yes");
		} else {
			request.setAttribute("sfKns", "no");
		}
		String xxdm = Base.xxdm;
		String sqxf = "";
		String sqzsf = "";
		if (!"".equalsIgnoreCase(xh)) {
			if ("1".equalsIgnoreCase(sfkns)) {
				request.setAttribute("isKns", dao.isKns(xh));
			}
			stuMap = service.getXfhj2xx(pkVal);// �õ���ϸ��Ϣ
			// -------------2010/5/26 edit by luojw ----------
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("sfkns", dao.isKns(xh) ? "��" : "��");
				stuMap.put("sqsj", GetTime.getSystemTime());
				stuMap.put("xn", Base.currXn);
				if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
					service.getXsqfInfo(xh, request);
				}
			} else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
				// String[] qxf = service.getGzdxQfxx(pkVal);
				// if (qxf != null) {
				// sqxf = qxf[0];
				// sqzsf = qxf[0];
				// }
				service.getXsqfInfo(xsxh, request);
			}
			// -----------end-----------------
		}
		request.setAttribute("sqxf", Base.chgNull(sqxf, "", 1));
		request.setAttribute("sqzsf", Base.chgNull(sqzsf, "", 1));
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("sfksq", service.getSqQx("ѧ�ѻ���", sUserType, xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xfhj2sq");
	}

	/**
	 * ����ѧ�ѻ���2������Ϣ xfhj2sqSave ---- ����ѧ�ѻ���2������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfhj2sqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Xfhj2Model model = new Xfhj2Model();
		BeanUtils.copyProperties(model, actionForm);
		XszzCommonN05Service service = new XszzCommonN05Service();
		
		// =============2010.9.13 edit by luojw======================
		// ѧУ����
		String xxdm = Base.xxdm;
		String xmdm = "kns";
		actionForm.setXmdm(xmdm);
		String message = "";
		// ������������
		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
			message = service.zzsqTjyz(actionForm);
			request.setAttribute("message", message);
		}
		boolean bJg = false;
		if (Base.isNull(message)) {
			bJg = service.saveXfhj2Sqxx(model, request);// ������Ϣ
		}

		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		// ===========end====================
		
		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		String sqxf = "";
		String sqzsf = "";
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getXfhj2xx(pkVal);
			// -------------2010/5/26 edit by luojw ----------
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("sfkns", dao.isKns(xh) ? "��" : "��");
				stuMap.put("sqsj", GetTime.getSystemTime());
				stuMap.put("xn", Base.currXn);// ��ǰѧ��

				if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
					service.getXsqfInfo(pkVal, request);
				}
			} else if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
				service.getXsqfInfo(pkVal, request);
			}
			// -------------end ----------
		}
		request.setAttribute("sqxf", Base.chgNull(sqxf, "", 1));
		request.setAttribute("sqzsf", Base.chgNull(sqzsf, "", 1));
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xfhj2sqSave");
	}

	/**
	 * ѧ�ѻ���2�����ҳ�� xfhj2sqb ----- ѧ�ѻ���2�����ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfhj2sqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		XszzCommonN05Service service = new XszzCommonN05Service();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getXfhj2xx(actionForm.getXn() + actionForm.getXh());
		
		stuMap.put("xxmc", StandardOperation.getXxmc());
		String sqsj = Base.chgNull(stuMap.get("sqsj"), "", 1);
		
		if (sqsj.length() == 10) {
			String sT = sqsj.substring(0, 4) + "��" + sqsj.substring(5, 7) + "��" + sqsj.substring(8) + "��";
			sqsj = sT;
		}
		stuMap.put("sqsj", sqsj);
		
		request.setAttribute("rs", stuMap);
		return mapping.findForward("xfhj2sqb");
	}
	
	/**
	 * ѧ�ѻ���2���ҳ�� xfhj2sh ----- ѧ�ѻ���2���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfhj2sh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));
		String shjb = service.getShjb("view_xszz_com_xfhj2");

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delXfhj2xx(actionForm.getPk(), request, shjb);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modXfhj2xx(actionForm.getPk(), "ͨ��", request, shjb);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modXfhj2xx(actionForm.getPk(), "��ͨ��", request, shjb);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			actionForm.setXn(Base.currXn);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getXfhj2Tit(shjb);
		List<String[]> resList = service.getXfhj2Res(queryModel, request,
				actionForm, shjb);
		String xh = DealString.toGBK(actionForm.getXh());
		actionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getXfhj2ResNum(queryModel, request, shjb)));
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_com_xfhj2");
		request.setAttribute("tableName", "view_xszz_com_xfhj2");
		return mapping.findForward("xfhj2sh");
	}

	/**
	 * ѧ�ѻ���2��Ϣ���� xfhj2Exp ----- ѧ�ѻ���2��Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfhj2Exp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		QueryModel queryModel = new QueryModel();

		String shjb = service.getShjb("view_xszz_com_xfhj2");
		BeanUtils.copyProperties(queryModel, actionForm);
		service.getXfhj2Exp(queryModel, response, request, shjb);
		return mapping.findForward("xfhj2Exp");
	}
	
	/**
	 * ѧ�ѻ���2�޹�Ƿ������ xfhj2wgqfmd ----- ѧ�ѻ���2�޹�Ƿ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfhj2wgqfmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));
		String shjb = service.getShjb("view_xszz_com_xfhj2");

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			String xxdm = Base.xxdm;
			if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
				String xn = Base.chgNull(request.getParameter("xn"), "", 1);
				actionForm.setXn(xn);
				request.setAttribute("xn", xn);
			} else {
				actionForm.setXn(Base.currXn);
			}
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getXfhj2WgqfTit();
		List<String[]> resList = service.getXfhj2WgqfRes(queryModel, request,
				actionForm, shjb);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);

		request.setAttribute("hForm", actionForm);
		return mapping.findForward("xfhj2wgqfmd");
	}
	
	/**
	 * ѧ�ѻ���2ѧ�ѻ��ɼ��޹�Ƿ��ͳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfhj2hjtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		String isQuery = Base.chgNull(request.getParameter("isQuery"), "", 1);
		String xn = Base.chgNull(request.getParameter("xn"), "", 1);
		if (!"is".equalsIgnoreCase(isQuery)) {
			String xxdm = Base.xxdm;
			if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
				xn = Base.isNull(xn) ? Base.currXn : xn;
			} else {
				xn = Base.currXn;
			}
		}	
		List<HashMap<String, String>> topList = service.getXfhj2HjtjTit();
		List<String[]> resList = service.getXfhj2HjtjRes(xn);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);

		return mapping.findForward("xfhj2hjtj");
	}
	
	/**
	 * ѧ�ѻ���2ѧ��Ƿ����Ϣ XG_BKS_XSCJFMD ----- ѧ�ѻ���2ѧ��Ƿ����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @author luojw
	 */
	public ActionForward xfhj2xsqfxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;

		String tableName = "view_XG_BKS_XSCJFMD";
		String[] colList = new String[] { "xh", "xm", "xn", "bjmc", "YJXFJE",
				"SJJFJE", "QXFJE", "YJZSFJE", "SJJZSFJE", "QZSFJE", "YJQTFYJE",
				"SJQTFYJE", "QJQTFYJE" };

		if ("xy".equals(userType)) {
			actionForm.setXydm(userDep);
		} else if ("stu".equals(userType)) {
			actionForm.setXh(userName);
		}
		
		List<HashMap<String, String>> topTr = SearchUtils.getTopTr(tableName,colList, null);
		ArrayList<String[]> rs = service.getXsqfxxList(userType, actionForm,colList);

		if (null != rs) {
			request.setAttribute("topTr", topTr);
			request.setAttribute("rs", rs);
		}

		request.setAttribute("hForm", actionForm);
		return mapping.findForward("xfhj2xsqfxx");
	}
	
	/**
	 * ѧ�ѻ���2�����ϸҳ�� xfhj2shOne ----- ѧ�ѻ���2�����ϸҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfhj2shOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String shjb = service.getShjb("view_xszz_com_xfhj2");
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getXfhj2xx(pkVal);
		String xxdm = Base.xxdm;
		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		// -------------2010/5/26 edit by luojw ----------
		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
			service.getXsqfInfo(pkVal, request);
		}
		// -------------end ----------
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("shjb", shjb);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xfhj2shOne");
	}
	
	/**
	 * ����ѧ�ѻ���2�����Ϣ xfhj2shSave ---- ����ѧ�ѻ���2�����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfhj2shSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String shjb = service.getShjb("view_xszz_com_xfhj2");
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Xfhj2Model model = new Xfhj2Model();
		BeanUtils.copyProperties(model, actionForm);
		boolean bJg = service.saveXfhj2Shxx(model, request, shjb);// ���������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getXfhj2xx(pkVal);
		String xxdm = Base.xxdm;
		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		// -------------2010/5/26 edit by luojw ----------
		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
			service.getXsqfInfo(pkVal, request);
		}
		// -------------end ----------
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("shjb", shjb);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xfhj2shSave");
	}

	/**
	 * ��������ʱ����1����ҳ��
	 * knslsbz1sq ----- ��������ʱ����1����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knslsbz1sq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String xh = "";// ѧ��
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// �û�������ѧ����ֱ�ӻ�ȡ�û���
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		String sfkns = service.getSfkns("view_xszz_com_knslsbz1");
		if ("1".equalsIgnoreCase(sfkns)) {
			request.setAttribute("sfKns", "yes");
		} else {
			request.setAttribute("sfKns", "no");
		}
		if (!"".equalsIgnoreCase(xh)) {
			if ("1".equalsIgnoreCase(sfkns)) {
				request.setAttribute("isKns", dao.isKns(xh));
			}
			stuMap = service.getKnslsbz1xx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("kndj", service.getKns1Rdqk(xh, xn));
				stuMap.put("xn", Base.currXn);
			}
		}

		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("sfksq", service.getSqQx("��������ʱ����", sUserType, xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knslsbz1sq");
	}

	/**
	 * ������������ʱ����1������Ϣ knslsbz1sqSave ---- ������������ʱ����1������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knslsbz1sqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Knslsbz1Model model = new Knslsbz1Model();
		BeanUtils.copyProperties(model, actionForm);
		XszzCommonN05Service service = new XszzCommonN05Service();
		boolean bJg = service.saveKnslsbz1Sqxx(model, request);// ������Ϣ
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
			stuMap = service.getKnslsbz1xx(pkVal);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);// ��ǰѧ��
			}
		}

		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knslsbz1sqSave");
	}

	/**
	 * ��������ʱ����1��ӡ knslsbz1Dy ----- ��������ʱ����1��ӡ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knslsbz1Dy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05Service service = new XszzCommonN05Service();
		String pkVal = request.getParameter("pkVal");
		String modelPath = servlet.getServletContext().getRealPath("")+"/print/xszzN05_knslsbz1.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
				modelPath), response.getOutputStream());
		service.knslsbz1Dy(wwb, pkVal);
		return null;
	}
	
	/**
	 * ��������ʱ����1���ҳ�� knslsbz1sh ----- ��������ʱ����1���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knslsbz1sh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));
		String shjb = service.getShjb("view_xszz_com_knslsbz1");

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delKnslsbz1xx(actionForm.getPk(), request, shjb);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnslsbz1xx(actionForm.getPk(), "ͨ��", request, shjb);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnslsbz1xx(actionForm.getPk(), "��ͨ��", request, shjb);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			actionForm.setXn(Base.currXn);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getKnslsbz1Tit(shjb);
		List<String[]> resList = service.getKnslsbz1Res(queryModel, request,
				actionForm, shjb);
		String xh = DealString.toGBK(actionForm.getXh());
		actionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getKnslsbz1ResNum(queryModel, request, shjb)));
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_com_knslsbz1");
		request.setAttribute("tableName", "view_xszz_com_knslsbz1");
		return mapping.findForward("knslsbz1sh");
	}

	/**
	 * ��������ʱ����1��Ϣ���� knslsbz1Exp ----- ��������ʱ����1��Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knslsbz1Exp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		QueryModel queryModel = new QueryModel();

		String shjb = service.getShjb("view_xszz_com_knslsbz1");
		BeanUtils.copyProperties(queryModel, actionForm);
		service.getKnslsbz1Exp(queryModel, response, request, shjb);
		return mapping.findForward("knslsbz1Exp");
	}
	
	/**
	 * ��������ʱ����1�����ϸҳ�� knslsbz1shOne ----- ��������ʱ����1�����ϸҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knslsbz1shOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String shjb = service.getShjb("view_xszz_com_knslsbz1");
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnslsbz1xx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}

		request.setAttribute("shjb", shjb);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knslsbz1shOne");
	}
	
	/**
	 * ������������ʱ����1�����Ϣ knslsbz1shSave ---- ������������ʱ����1�����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knslsbz1shSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String shjb = service.getShjb("view_xszz_com_knslsbz1");
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Knslsbz1Model model = new Knslsbz1Model();
		BeanUtils.copyProperties(model, actionForm);
		boolean bJg = service.saveKnslsbz1Shxx(model, request, shjb);// ���������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnslsbz1xx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		request.setAttribute("shjb", shjb);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knslsbz1shSave");
	}

	/**
	 * ��ʱ����2����ҳ��
	 * lsbz2sq ----- ��ʱ����2����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsbz2sq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String xh = "";// ѧ��
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// �û�������ѧ����ֱ�ӻ�ȡ�û���
		String xn = Base.currXn;
		String xq = Base.currXq;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xq + xh : pkVal;
		xh = pkVal.substring(11);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		String sfkns = service.getSfkns("view_xszz_com_knslsbz2");
		if ("1".equalsIgnoreCase(sfkns)) {
			request.setAttribute("sfKns", "yes");
		} else {
			request.setAttribute("sfKns", "no");
		}
		if (!"".equalsIgnoreCase(xh)) {
			if ("1".equalsIgnoreCase(sfkns)) {
				request.setAttribute("isKns", dao.isKns(xh));
			}
			stuMap = service.getLsbz2xx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);
				stuMap.put("xqmc", Base.getDqxqmc());
				stuMap.put("xq", Base.currXq);
			}
		}

		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("sfksq", service.getSqQx("��ʱ����", sUserType, xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("lsbz2sq");
	}

	/**
	 * ������ʱ����2������Ϣ lsbz2sqSave ---- ������ʱ����2������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsbz2sqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Lsbz2Model model = new Lsbz2Model();
		BeanUtils.copyProperties(model, actionForm);
		XszzCommonN05Service service = new XszzCommonN05Service();
		String shjb = service.getShjb("view_xszz_com_knslsbz2");
		
		boolean bJg = service.saveLsbz2Sqxx(model, request,shjb);// ������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String xq = model.getXq();
		String xn = model.getXn();
		String pkVal = xn + xq + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getLsbz2xx(pkVal);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);// ��ǰѧ��
				stuMap.put("xqmc", Base.getDqxqmc());
				stuMap.put("xq", Base.currXq);
			}
		}

		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("lsbz2sqSave");
	}

	/**
	 * ��ʱ����2�����ҳ�� lsbz2sqb ----- ��ʱ����2�����ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsbz2sqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		XszzCommonN05Service service = new XszzCommonN05Service();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getLsbz2xx(actionForm.getXn() + actionForm.getXq()
				+ actionForm.getXh());
		request.setAttribute("rs", stuMap);
		return mapping.findForward("lsbz2sqb");
	}
	
	/**
	 * ��ʱ����2���ҳ�� lsbz2sh ----- ��ʱ����2���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsbz2sh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xxdm = StandardOperation.getXxdm();
		String auditingModel = Globals.getXszzAuditingModel().get(xxdm);
		ActionForward af = null;
		String isQuery = Base.chgNull(request.getParameter("isQuery"), "",1);
		if(Globals.stuModel.equalsIgnoreCase(auditingModel) 
				|| Base.isNull(auditingModel) 
				|| "is".equalsIgnoreCase(isQuery)){
			//��ѧ��Ϊ��λ���
			af = lsbz2shStuModel(mapping, form, request, response);
		}else{//�Բ���Ϊ��λ���
			af = lsbz2shDeptModel(mapping, form, request, response);
		}
		return af;
	}
	
	/**
	 * ��ʱ����2���ҳ�� lsbz2shStuModel ----- ��ʱ����2���StuModel���ģʽ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsbz2shStuModel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));
		String shjb = service.getShjb("view_xszz_com_knslsbz2");

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delLsbz2xx(actionForm.getPk(), request, shjb);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modLsbz2xx(actionForm.getPk(), "ͨ��", request, shjb);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modLsbz2xx(actionForm.getPk(), "��ͨ��", request, shjb);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			actionForm.setXn(Base.currXn);
			actionForm.setXq(Base.currXq);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getLsbz2Tit(shjb);
		List<String[]> resList = service.getLsbz2Res(queryModel, request,
				actionForm, shjb);
		String xh = DealString.toGBK(actionForm.getXh());
		actionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getLsbz2ResNum(queryModel, request, shjb)));
		request.setAttribute("pk", "xn||xq||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_com_knslsbz2");
		request.setAttribute("tableName", "view_xszz_com_knslsbz2");
		return mapping.findForward("lsbz2sh");
	}
	
	/**
	 * ��ʱ����2���ҳ�� lsbz2shByFdy ----- ��ʱ����2��� ����Ա���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsbz2shByFdy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = session.getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",1));
		String shjb = service.getShjb("view_xszz_com_knslsbz2");

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delLsbz2xx(actionForm.getPk(), request, shjb);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modLsbz2xx(actionForm.getPk(), "ͨ��", request, shjb);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modLsbz2xx(actionForm.getPk(), "��ͨ��", request, shjb);
			queryModel.setGo("go");
		}
		if("tjsh".equalsIgnoreCase(queryModel.getGo())){
			actionForm.setXn(request.getParameter("xn"));
			actionForm.setXq(request.getParameter("xq"));
			actionForm.setBjdm(request.getParameter("tjbjdm"));
			actionForm.setUserName(userName);
			service.addLsbz2shtj(actionForm,request);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			actionForm.setXn(Base.currXn);
			actionForm.setXq(Base.currXq);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getLsbz2Tit(shjb);
		List<String[]> resList = service.getLsbz2ResByFdy(queryModel, request,
				actionForm, shjb);
		String xh = DealString.toGBK(actionForm.getXh());
		actionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getLsbz2ResNum(queryModel, request, shjb)));
		request.setAttribute("pk", "xn||xq||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_com_knslsbz2");
		request.setAttribute("tableName", "view_xszz_com_knslsbz2");
		return mapping.findForward("lsbz2sh");
	}
	
	/**
	 * ��ʱ����2���ҳ�� lsbz2shDeptModel ----- ��ʱ����2��� deptModel���ģʽ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsbz2shDeptModel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		ActionForward af = null;		
		String shjb = service.getShjb("view_xszz_com_knslsbz2");
		
		if("3".equalsIgnoreCase(shjb) && session.getAttribute("fdyQx").equals(true)){//������� �û��Ǹ���Ա ��ѧ��Ϊ��λ���
			//���ѡ���˰༶����ʾ�ύ״̬
			if(!Base.isNull(actionForm.getBjdm())){
				actionForm.setXn(Base.currXn);
				actionForm.setXq(Base.currXq);			
				request.setAttribute("tjxx", service.getFdyshtjbxx("lsbz2",actionForm.getBjdm(),actionForm.getXn()+actionForm.getXq()));
			}
			request.setAttribute("fdyshFlag", true);
			af = lsbz2shByFdy(mapping, form, request, response);
		}else{//�������
			String userType = request.getSession().getAttribute("userType").toString();
			String userDep = request.getSession().getAttribute("userDep").toString();
			QueryModel queryModel = new QueryModel();
			queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
			queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",1));
	
			String pk = "xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType) ? "xn||xq||xydm" : "xn||xq||bjdm";//ѧԺ�û�
			
			actionForm.setPkcolumn(pk);	
			actionForm.setUserName(userName);
			actionForm.setShjb(shjb);
			if ("tg".equalsIgnoreCase(queryModel.getGo())) {
				actionForm.setShjg("ͨ��");
				service.audiLsbz2ForDep(actionForm, request);
				queryModel.setGo("go");
			}
			if ("btg".equalsIgnoreCase(queryModel.getGo())) {
				actionForm.setShjg("��ͨ��");
				service.audiLsbz2ForDep(actionForm, request);
				queryModel.setGo("go");
			}
			if ("th".equalsIgnoreCase(queryModel.getGo())) {//�˻�
				actionForm.setShjg("�˻�");
				service.audiLsbz2ForDep(actionForm, request);
				queryModel.setGo("go");
			}
			if("tjjg".equalsIgnoreCase(queryModel.getGo())){//�ύ��˽��
				service.commitResult(userDep,userName,"lsbz2","xn||xq",Base.currXn+Base.currXq);
				queryModel.setGo("go");
			}

			if (userType.equalsIgnoreCase("xy")) {
				actionForm.setXydm(userDep);
			}
			if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
				actionForm.setXn(Base.currXn);
				actionForm.setXq(Base.currXq);
			}
			BeanUtils.copyProperties(queryModel, actionForm);
			List<HashMap<String, String>> topList = service.getLsbz2TitForDM(shjb,userType);
			List<String[]> resList = service.getLsbz2ResForDM(queryModel, request,
					actionForm, shjb);
			String xh = DealString.toGBK(actionForm.getXh());
			actionForm.setXh(xh);
			request.setAttribute("topTr", topList);
			request.setAttribute("rs", resList);
			request.setAttribute("rsNum", resList != null ? resList.size() : 0);

			XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
			XszzCommenBean commenBean = new XszzCommenBean();
			BeanUtils.copyProperties(commenBean, actionForm);
			commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

			//actionForm.getPages().setMaxRecord(Integer.parseInt(service.getLsbz2ResNum(queryModel, request, shjb)));
			request.setAttribute("pk", "xn||xq||xh");
			request.setAttribute("hForm", actionForm);
			request.setAttribute("isQuery", queryModel.getIsQuery());
			request.setAttribute("realTable", "xszz_com_knslsbz2");
			request.setAttribute("tableName", "view_xszz_com_knslsbz2");
			request.setAttribute("tjjg", service.queryXyshjg(userDep,Base.currXn+Base.currXq,"lsbz2"));
			request.setAttribute("shjb", shjb);
			return mapping.findForward("lsbz2shdm");
		}	
		return af;
	}
	
	/**
	 * ��ʱ����2�����ϸ��Ϣҳ�� lsbz2shdmOne ----- ��ʱ����2��� deptModel���ģʽ	
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsbz2shdmOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();	
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm)form;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String shjb = service.getShjb("view_xszz_com_knslsbz2");
		String type = request.getParameter("type");
		String shjg = request.getParameter("shjg");
		String shyj = request.getParameter("shyj");
		String operFlag = request.getParameter("operFlag");
		String doType = request.getParameter("doType");
		
		if(!Base.isNull(type) && "save".equalsIgnoreCase(type)){
			boolean result = true;
			try{
				actionForm.setPk(new String[]{"",pkVal});
				actionForm.setShjg(shjg);
				actionForm.setShyj(shyj);
				actionForm.setShjb(shjb);
				actionForm.setUserName(userName);
				service.audiLsbz2ForDep(actionForm, request);
			}catch(Exception e){
				result = false;
			}
			request.setAttribute("result", result);
		}
		HashMap<String, String>  map = service.getLsbz2shtjjg(pkVal,userType,shjb);
		List<String[]> rs = service.getLsbz2Stush(pkVal,userType,shjb);
		map.put("shjg", shjg);
		map.put("pzje", rs.get(0)[9]);
		
		request.setAttribute("map", map);
		request.setAttribute("operFlag", operFlag);
		request.setAttribute("doType", doType);		
		request.setAttribute("rs", rs);		
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("chkList", service.getChkList(27));
		request.setAttribute("topTr", service.getLsbz2TitForStush(userType));
		return mapping.findForward("lsbz2shdmOne");
	}
	
	/**
	 * ��ʱ����2��Ϣ���� lsbz2Exp ----- ��ʱ����2��Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsbz2Exp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		QueryModel queryModel = new QueryModel();

		String shjb = service.getShjb("view_xszz_com_knslsbz2");
		BeanUtils.copyProperties(queryModel, actionForm);
		service.getLsbz2Exp(queryModel, response, request, shjb);
		return mapping.findForward("lsbz2Exp");
	}
	
	/**
	 * ��ʱ����2�����ϸҳ�� lsbz2shOne ----- ��ʱ����2�����ϸҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsbz2shOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String shjb = service.getShjb("view_xszz_com_knslsbz2");
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getLsbz2xx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		if(Globals.deptModel.equalsIgnoreCase(Globals.getXszzAuditingModel().get(StandardOperation.getXxdm()))){
			//�Բ���Ϊ��λ���
			request.setAttribute("shmodel", "depModel");
		}
		request.setAttribute("shjb", shjb);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("lsbz2shOne");
	}
	
	/**
	 * ������ʱ����2�����Ϣ lsbz2shSave ---- ������ʱ����2�����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsbz2shSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String shjb = service.getShjb("view_xszz_com_knslsbz2");
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Lsbz2Model model = new Lsbz2Model();
		BeanUtils.copyProperties(model, actionForm);
		boolean bJg = service.saveLsbz2Shxx(model, request, shjb);// ���������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getLsbz2xx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		request.setAttribute("shjb", shjb);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("lsbz2shSave");
	}

	/**
	 * ������ѧ��1����ҳ��
	 * wszxj1sq ----- ������ѧ��1����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wszxj1sq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String xh = "";// ѧ��
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// �û�������ѧ����ֱ�ӻ�ȡ�û���
		String xmdm = StringUtils.isNull(request.getParameter("xmdm")) ? "" : request.getParameter("xmdm");
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xmdm + xh : pkVal;
		xh = pkVal.substring(9+xmdm.length());
		HashMap<String, String> stuMap = new HashMap<String, String>();
		
		String sfkns = service.getSfkns("view_xszz_com_wszxj1"+xmdm);
		if ("1".equalsIgnoreCase(sfkns)) {
			request.setAttribute("sfKns", "yes");
		} else {
			request.setAttribute("sfKns", "no");
		}
		if (!"".equalsIgnoreCase(xh)) {
			if ("1".equalsIgnoreCase(sfkns)) {
				request.setAttribute("isKns", dao.isKns(xh));
			}
			stuMap = service.getWszxj1xx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);
			}
		}
		String xmmc = service.getWszxjmc(xmdm);
		request.setAttribute("xmmc", xmmc);
		request.setAttribute("xmdm", xmdm);
		request.setAttribute("wszxjJeList", service.getWszxjJeList(xmdm));
		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("sfksq", service.getSqQx(xmmc, sUserType, xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("wszxj1sq");
	}
	
	/**
	 * ����������ѧ��1������Ϣ wszxj1sqSave ---- ����������ѧ��1������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wszxj1sqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Wszxj1Model model = new Wszxj1Model();
		BeanUtils.copyProperties(model, actionForm);
		XszzCommonN05Service service = new XszzCommonN05Service();
		String xmdm = request.getParameter("xmdm");
		model.setZxjdm(xmdm);
		
		String shjb = service.getShjb("view_xszz_com_wszxj1"+xmdm);
		boolean bJg = service.saveWszxj1Sqxx(model, request, shjb);// ������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xmdm + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getWszxj1xx(pkVal);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);// ��ǰѧ��
			}
		}

		String xmmc = service.getWszxjmc(xmdm);
		request.setAttribute("xmdm", xmdm);
		request.setAttribute("xmmc", xmmc);
		request.setAttribute("wszxjJeList", service.getWszxjJeList(xmdm));
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("wszxj1sqSave");
	}

	/**
	 * ������ѧ��1�����ҳ�� wszxj1sqb ----- ������ѧ��1�����ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wszxj1sqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		XszzCommonN05Service service = new XszzCommonN05Service();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getWszxj1xx(actionForm.getXn() + actionForm.getZxjdm()
				+ actionForm.getXh());
		
		stuMap.put("xxmc", StandardOperation.getXxmc());
		request.setAttribute("rs", stuMap);
		return mapping.findForward("wszxj1sqb");
	}
	
	/**
	 * ������ѧ��1���ҳ�� wszxj1sh ----- ������ѧ��1���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wszxj1sh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xxdm = StandardOperation.getXxdm();
		String auditingModel = Globals.getXszzAuditingModel().get(xxdm);
		ActionForward af = null;
		String isQuery = Base.chgNull(request.getParameter("isQuery"), "",1);
		if(Globals.stuModel.equalsIgnoreCase(auditingModel) || Base.isNull(auditingModel) || "is".equalsIgnoreCase(isQuery)){
			//��ѧ��Ϊ��λ���
			af = wszxj1shStuModel(mapping, form, request, response);
		}else{//�Բ���Ϊ��λ���
			af = wszxj1shDeptModel(mapping, form, request, response);
		}
		return af;
	}
	
	/**
	 * ������ѧ��1���ҳ�� wszxj1shStuModel ----- ������ѧ��1��� StuModel���ģʽ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wszxj1shStuModel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));
		String zxjdm = request.getParameter("xmdm");
		String shjb = service.getShjb("view_xszz_com_wszxj1"+zxjdm);

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delWszxj1xx(actionForm.getPk(), request, shjb);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modWszxj1xx(actionForm.getPk(), "ͨ��", request, shjb);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modWszxj1xx(actionForm.getPk(), "��ͨ��", request, shjb);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			actionForm.setXn(Base.currXn);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getWszxj1Tit(shjb);
		List<String[]> resList = service.getWszxj1Res(queryModel, request,
				actionForm, shjb);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		actionForm.setXh(DealString.toGBK(actionForm.getXh()));
		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getWszxj1ResNum(queryModel, request, shjb)));
		String xmmc = service.getWszxjmc(zxjdm);
		request.setAttribute("xmdm", zxjdm);
		request.setAttribute("xmmc", xmmc);
		request.setAttribute("pk", "xn||zxjdm||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_com_wszxj1");
		request.setAttribute("tableName", "view_xszz_com_wszxj1");
		return mapping.findForward("wszxj1sh");
	}
	
	/**
	 * ������ѧ��1���ҳ�� wszxj1shByFdy ----- ������ѧ��1��� ����Ա���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wszxj1shByFdy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",1));
		String zxjdm = request.getParameter("xmdm");
		String shjb = service.getShjb("view_xszz_com_wszxj1"+zxjdm);

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delWszxj1xx(actionForm.getPk(), request, shjb);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modWszxj1xx(actionForm.getPk(), "ͨ��", request, shjb);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modWszxj1xx(actionForm.getPk(), "��ͨ��", request, shjb);
			queryModel.setGo("go");
		}
		if("tjsh".equalsIgnoreCase(queryModel.getGo())){
			actionForm.setXn(request.getParameter("xn"));
			actionForm.setZxjdm(zxjdm);
			actionForm.setBjdm(request.getParameter("tjbjdm"));
			actionForm.setUserName(session.getAttribute("userName").toString());
			service.addWszxj1shtj(actionForm,request);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			actionForm.setXn(Base.currXn);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getWszxj1Tit(shjb);
		List<String[]> resList = service.getWszxj1ResByFdy(queryModel, request,
				actionForm, shjb);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		actionForm.setXh(DealString.toGBK(actionForm.getXh()));
		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getWszxj1ResNum(queryModel, request, shjb)));
		String xmmc = service.getWszxjmc(zxjdm);
		request.setAttribute("xmdm", zxjdm);
		request.setAttribute("xmmc", xmmc);
		request.setAttribute("pk", "xn||zxjdm||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_com_wszxj1");
		request.setAttribute("tableName", "view_xszz_com_wszxj1");
		return mapping.findForward("wszxj1sh");
	}
	
	/**
	 * ������ѧ��1���ҳ�� wszxj1shDeptModel ----- ������ѧ��1��� ����Ա���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wszxj1shDeptModel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		ActionForward af = null;
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = session.getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",1));
		String zxjdm = request.getParameter("xmdm");
		String shjb = service.getShjb("view_xszz_com_wszxj1"+zxjdm);
		
		if("3".equalsIgnoreCase(shjb) && session.getAttribute("fdyQx").equals(true)){//������� �û��Ǹ���Ա ��ѧ��Ϊ��λ���
			//���ѡ���˰༶����ʾ�ύ״̬
			if(!Base.isNull(actionForm.getBjdm())){
				actionForm.setXn(Base.currXn);
				actionForm.setZxjdm(zxjdm);		
				request.setAttribute("tjxx", service.getFdyshtjbxx("wszxj1",actionForm.getBjdm(),actionForm.getXn()+actionForm.getZxjdm()));
			}
			request.setAttribute("fdyshFlag", true);
			af = wszxj1shByFdy(mapping, form, request, response);
		}else{//�������
			actionForm.setShjb(shjb);
			actionForm.setUserName(userName);
			if ("del".equalsIgnoreCase(queryModel.getGo())) {				
				service.delWszxj1xx(actionForm.getPk(), request, shjb);
				queryModel.setGo("go");
			}
			if ("tg".equalsIgnoreCase(queryModel.getGo())) {
				actionForm.setShjg("ͨ��");
				service.audiWszxj1ForDep(actionForm, request);
				queryModel.setGo("go");
			}
			if ("btg".equalsIgnoreCase(queryModel.getGo())) {
				actionForm.setShjg("��ͨ��");
				service.audiWszxj1ForDep(actionForm, request);
				queryModel.setGo("go");
			}
			if ("th".equalsIgnoreCase(queryModel.getGo())) {//�˻�
				actionForm.setShjg("�˻�");
				service.audiWszxj1ForDep(actionForm, request);
				queryModel.setGo("go");
			}
			if("tjjg".equalsIgnoreCase(queryModel.getGo())){//�ύ��˽��
				service.commitResult(userDep,userName,"wszxj1","xn||zxjdm",Base.currXn+zxjdm);
				queryModel.setGo("go");
			}
	
			if (userType.equalsIgnoreCase("xy")) {
				actionForm.setXydm(userDep);
			}
			if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
				actionForm.setXn(Base.currXn);
			}
			BeanUtils.copyProperties(queryModel, actionForm);
			List<HashMap<String, String>> topList = service.getWszxj1TitForDM(userType);
			List<String[]> resList = service.getWszxj1ResForDM(queryModel, request,
					actionForm, shjb);
			request.setAttribute("topTr", topList);
			request.setAttribute("rs", resList);
			request.setAttribute("rsNum", resList != null ? resList.size() : 0);
	
			XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
			XszzCommenBean commenBean = new XszzCommenBean();
			BeanUtils.copyProperties(commenBean, actionForm);
			commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�
	
			actionForm.setXh(DealString.toGBK(actionForm.getXh()));
			actionForm.setXm(DealString.toGBK(actionForm.getXm()));
			actionForm.setXb(DealString.toGBK(actionForm.getXb()));
			actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));
	
			//actionForm.getPages().setMaxRecord(Integer.parseInt(service.getWszxj1ResNum(queryModel, request, shjb)));
			String xmmc = service.getWszxjmc(zxjdm);
			request.setAttribute("xmdm", zxjdm);
			request.setAttribute("xmmc", xmmc);
			request.setAttribute("pk", "xn||zxjdm||xh");
			request.setAttribute("hForm", actionForm);
			request.setAttribute("isQuery", queryModel.getIsQuery());
			request.setAttribute("realTable", "xszz_com_wszxj1");
			request.setAttribute("tableName", "view_xszz_com_wszxj1");
			request.setAttribute("tjjg", service.queryXyshjg(userDep,Base.currXn+zxjdm,"wszxj1"));
			request.setAttribute("shjb", shjb);
			af = mapping.findForward("wszxj1shdm");
		}
		return af;
	}
	
	/**
	 * ������ѧ��1�����ϸ��Ϣҳ�� Wszxj1shdmOne ----- ������ѧ��1���deptModel���ģʽ	
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wszxj1shdmOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm model = (XszzCommonN05ActionForm)form;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String userType = session.getAttribute("userType").toString();
		
		String zxjdm = request.getParameter("xmdm");
		String shjb = service.getShjb("view_xszz_com_wszxj1"+zxjdm);
		String type = request.getParameter("type");
		String shjg = request.getParameter("shjg");
		String shyj = request.getParameter("shyj");
		String operFlag = request.getParameter("operFlag");
		String doType = request.getParameter("doType");
		
		if(!Base.isNull(type) && "save".equalsIgnoreCase(type)){
			boolean result = true;
			try{
				model.setPk(new String[]{"",pkVal});
				model.setShjg(shjg);
				model.setShjb(shjb);
				model.setShyj(shyj);
				model.setUserName(session.getAttribute("userName").toString());
				service.audiWszxj1ForDep(model, request);
			}catch(Exception e){
				result = false;
			}
			request.setAttribute("result", result);
		}
		HashMap<String, String>  map = service.getWszxj1shtjjg(pkVal,userType,shjb);
		List<String[]> rs = service.getWszxj1Stush(pkVal,userType,shjb);
		map.put("shjg", shjg);
		
		String xmmc = service.getWszxjmc(zxjdm);
		request.setAttribute("xmmc", xmmc);
		request.setAttribute("map", map);
		request.setAttribute("operFlag", operFlag);
		request.setAttribute("rs", rs);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("chkList", service.getChkList(27));
		request.setAttribute("topTr", service.getWszxj1TitForStush(userType));
		request.setAttribute("xmdm", zxjdm);
		request.setAttribute("doType", doType);		
		return mapping.findForward("wszxj1shdmOne");
	}
	
	/**
	 * ������ѧ��1��Ϣ���� wszxj1Exp ----- ������ѧ��1��Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wszxj1Exp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		QueryModel queryModel = new QueryModel();
		BeanUtils.copyProperties(queryModel, actionForm);

		String shjb = service.getShjb("view_xszz_com_wszxj1"+queryModel.getXmdm());
		service.getWszxj1Exp(queryModel, response, request, shjb);
		return mapping.findForward("wszxj1Exp");
	}
	
	/**
	 * ������ѧ��1�����ϸҳ�� wszxj1shOne ----- ������ѧ��1�����ϸҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wszxj1shOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String zxjdm = request.getParameter("xmdm");
		String shjb = service.getShjb("view_xszz_com_wszxj1"+zxjdm);
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getWszxj1xx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		if(Globals.deptModel.equalsIgnoreCase(Globals.getXszzAuditingModel().get(StandardOperation.getXxdm()))){
			//�Բ���Ϊ��λ���
			request.setAttribute("shmodel", "depModel");
		}
		
		String xmmc = service.getWszxjmc(zxjdm);
		request.setAttribute("xmdm", zxjdm);
		request.setAttribute("xmmc", xmmc);
		request.setAttribute("wszxjJeList", service.getWszxjJeList(zxjdm));
		request.setAttribute("shjb", shjb);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("wszxj1shOne");
	}
	
	/**
	 * ����������ѧ��1�����Ϣ wszxj1shSave ---- ����������ѧ��1�����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wszxj1shSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String zxjdm = request.getParameter("xmdm");
		String shjb = service.getShjb("view_xszz_com_wszxj1"+zxjdm);
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Wszxj1Model model = new Wszxj1Model();
		BeanUtils.copyProperties(model, actionForm);
		model.setZxjdm(zxjdm);
		boolean bJg = service.saveWszxj1Shxx(model, request, shjb);// ���������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + zxjdm + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getWszxj1xx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		String xmmc = service.getWszxjmc(zxjdm);
		request.setAttribute("xmdm", zxjdm);
		request.setAttribute("xmmc", xmmc);
		request.setAttribute("wszxjJeList", service.getWszxjJeList(zxjdm));
		request.setAttribute("shjb", shjb);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("wszxj1shSave");
	}
	
	/**
	 * ������ѧ��2����ҳ��
	 * wszxj2sq ----- ������ѧ��2����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wszxj2sq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String xh = "";// ѧ��
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// �û�������ѧ����ֱ�ӻ�ȡ�û���
		String xmdm = request.getParameter("xmdm");
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xmdm + xh : pkVal;
		xh = pkVal.substring(9+xmdm.length());
		HashMap<String, String> stuMap = new HashMap<String, String>();
		
		String sfkns = service.getSfkns("view_xszz_com_wszxj2"+xmdm);
		if ("1".equalsIgnoreCase(sfkns)) {
			request.setAttribute("sfKns", "yes");
		} else {
			request.setAttribute("sfKns", "no");
		}
		if (!"".equalsIgnoreCase(xh)) {
			if ("1".equalsIgnoreCase(sfkns)) {
				request.setAttribute("isKns", dao.isKns(xh));
			}
			stuMap = service.getWszxj2xx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("sqsj", GetTime.getSystemTime());
				stuMap.put("xn", Base.currXn);
			}
		}
		String xmmc = service.getWszxjmc(xmdm);
		request.setAttribute("xmmc", xmmc);
		request.setAttribute("xmdm", xmdm);
		request.setAttribute("wszxjJeList", service.getWszxjJeList(xmdm));
		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("sfksq", service.getSqQx(xmmc, sUserType, xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("wszxj2sq");
	}
	
	/**
	 * ����������ѧ��2������Ϣ wszxj2sqSave ---- ����������ѧ��2������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wszxj2sqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Wszxj2Model model = new Wszxj2Model();
		BeanUtils.copyProperties(model, actionForm);
		XszzCommonN05Service service = new XszzCommonN05Service();
		String xmdm = request.getParameter("xmdm");
		model.setZxjdm(xmdm);
		
		// =============2010.9.13 edit by luojw======================
		// ѧУ����
		String xxdm = Base.xxdm;
		actionForm.setXmdm(xmdm);
		String message = "";
		// ������������
		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
			message = service.zzsqTjyz(actionForm);
			request.setAttribute("message", message);
		}
		//-------------2010/11/29 by lr----------------
		//�ж���Ŀ�Ƿ����������ſ�����
		message = service.checkXmxztj(actionForm);
		if(StringUtils.isNotNull(message)){
			request.setAttribute("message", message);
		}
		//end-------------2010/11/29 by lr----------------
		boolean bJg = false;
		if (Base.isNull(message)) {
			bJg = service.saveWszxj2Sqxx(model, request);// ������Ϣ
		}
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		// =============end======================
		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xmdm + xh;
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getWszxj2xx(pkVal);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("sqsj", GetTime.getSystemTime());
				stuMap.put("xn", Base.currXn);// ��ǰѧ��
			}
		}

		String xmmc = service.getWszxjmc(xmdm);
		request.setAttribute("xmdm", xmdm);
		request.setAttribute("xmmc", xmmc);
		request.setAttribute("wszxjJeList", service.getWszxjJeList(xmdm));
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("wszxj2sqSave");
	}

	/**
	 * ������ѧ��2�����ҳ�� wszxj2sqb ----- ������ѧ��2�����ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wszxj2sqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		XszzCommonN05Service service = new XszzCommonN05Service();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getWszxj2xx(actionForm.getXn() + actionForm.getZxjdm()
				+ actionForm.getXh());
		
		stuMap.put("xxmc", StandardOperation.getXxmc());
		request.setAttribute("rs", stuMap);
		return mapping.findForward("wszxj2sqb");
	}
	
	/**
	 * ������ѧ��2���ҳ�� wszxj2sh ----- ������ѧ��2���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wszxj2sh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));
		String zxjdm = request.getParameter("xmdm");
		String shjb = service.getShjb("view_xszz_com_wszxj1"+zxjdm);

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delWszxj2xx(actionForm.getPk(), request, shjb);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modWszxj2xx(actionForm.getPk(), "ͨ��", request, shjb);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modWszxj2xx(actionForm.getPk(), "��ͨ��", request, shjb);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			actionForm.setXn(Base.currXn);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getWszxj2Tit(shjb);
		List<String[]> resList = service.getWszxj2Res(queryModel, request,
				actionForm, shjb);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		actionForm.setXh(DealString.toGBK(actionForm.getXh()));
		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getWszxj2ResNum(queryModel, request, shjb)));
		String xmmc = service.getWszxjmc(zxjdm);
		request.setAttribute("xmdm", zxjdm);
		request.setAttribute("xmmc", xmmc);
		request.setAttribute("pk", "xn||zxjdm||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_com_wszxj2");
		request.setAttribute("tableName", "view_xszz_com_wszxj2");
		return mapping.findForward("wszxj2sh");
	}
	
	/**
	 * ������ѧ��2��Ϣ���� wszxj2Exp ----- ������ѧ��2��Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wszxj2Exp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05Service service = new XszzCommonN05Service();
		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		QueryModel queryModel = new QueryModel();
		BeanUtils.copyProperties(queryModel, actionForm);

		String shjb = service.getShjb("view_xszz_com_wszxj1"+queryModel.getXmdm());
		service.getWszxj2Exp(queryModel, response, request, shjb);
		return mapping.findForward("wszxj2Exp");
	}
	
	/**
	 * ������ѧ��2�����ϸҳ�� wszxj2shOne ----- ������ѧ��2�����ϸҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wszxj2shOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String zxjdm = request.getParameter("xmdm");
		String shjb = service.getShjb("view_xszz_com_wszxj1"+zxjdm);
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getWszxj2xx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}

		//���ݴ�ѧ
		String xxdm = Base.xxdm;
		if (Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)) {
			XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
			String xh = stuMap.get("xh");
			actionForm.setXh(xh);
			service.getLnZzInfoList(actionForm, request);
		}
		
		String xmmc = service.getWszxjmc(zxjdm);
		request.setAttribute("xmdm", zxjdm);
		request.setAttribute("xmmc", xmmc);
		request.setAttribute("wszxjJeList", service.getWszxjJeList(zxjdm));
		request.setAttribute("shjb", shjb);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("wszxj2shOne");
	}
	
	/**
	 * ����������ѧ��2�����Ϣ wszxj2shSave ---- ����������ѧ��2�����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wszxj2shSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		XszzCommonN05Service service = new XszzCommonN05Service();
		String zxjdm = request.getParameter("xmdm");
		String shjb = service.getShjb("view_xszz_com_wszxj1"+zxjdm);
		ArrayList<String> userBj = new ArrayList<String>();
		
		if ("3".equalsIgnoreCase(shjb)) {
			userBj = dao.getUserBj(userName);
		}

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		Wszxj2Model model = new Wszxj2Model();
		BeanUtils.copyProperties(model, actionForm);
		model.setZxjdm(zxjdm);
		boolean bJg = service.saveWszxj2Shxx(model, request, shjb);// ���������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + zxjdm + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getWszxj2xx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		String xmmc = service.getWszxjmc(zxjdm);
		request.setAttribute("xmdm", zxjdm);
		request.setAttribute("xmmc", xmmc);
		request.setAttribute("wszxjJeList", service.getWszxjJeList(zxjdm));
		request.setAttribute("shjb", shjb);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("wszxj2shSave");
	}
	
	/**
	 * @describe ɾ�����ϴ��ļ�
	 * @author luojw
	 * @throws Exception
	 */
	public ActionForward fileDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzCommonN05ActionForm actionForm = (XszzCommonN05ActionForm) form;
		XszzService tyService = new XszzService();

		String pk = "xn||xh";
		String pkValue = actionForm.getXn()+actionForm.getXh();
		String forward = request.getParameter("url") + "&xh="
				+ actionForm.getXh();
		String realTable = "xszz_com_knsrdb3";
		String type = request.getParameter("type");
		
		if("sh".equalsIgnoreCase(type)){
			forward = "/n05_xszz.do?method=knsrd3shOne&pkVal=" + pkValue;
		}
		if (!Base.isNull(type)) {
			forward += "&type=" + type;
		}
		if (!Base.isNull(pkValue)) {
			tyService.fileDel(realTable, "scdz", pk, pkValue);
		}

		return new ActionForward(forward, false);
	}
}
