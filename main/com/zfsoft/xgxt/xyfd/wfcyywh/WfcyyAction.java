package com.zfsoft.xgxt.xyfd.wfcyywh;


import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xyfd.fdkcwh.fdkcjg.FdkcjgService;
import common.newp.StringUtil;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/8/6.
 */
public class WfcyyAction extends SuperAction<FdyyForm,FdyyService> {
    private FdyyService service = new FdyyService();
    private FdkcjgService fdkcjgService = new FdkcjgService();
    private static final String url = "xyfd_xyfd_fqyy.do";
    private static List<HashMap<String, String>> jbxxList = null;

    public static String XYFD = "hdbl";

    static {
        BdpzService bdpzService = new BdpzService();
        // ѧ��������Ϣ��ʾ����
        jbxxList = bdpzService.getJbxxpz(XYFD);
    }

    /**
     * ԤԼ�б�
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = url)
    public ActionForward fcyyList(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        FdyyForm model = (FdyyForm)form;
        if (QUERY.equalsIgnoreCase(model.getType())) {
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // ��ѯ
            List<HashMap<String, String>> resultList = service.getPageList(model, user);

            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        String path = "xyfd_xyfd_fqyy.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("fcyyList");
    }

    /**
     * ����ԤԼ��������
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward addYy(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdyyForm model = (FdyyForm)form;

        String yysf = request.getParameter("yysf"); //ԤԼ��ݣ�ѧ������ʦ/�󱲣�
        User user = getUser(request);
        boolean isJsOrPb = true;
        if(yysf.equals("stu")){
            model.setXh(user.getUserName());
            isJsOrPb = false;
        }else { // yysf = 'tea'
            if(user.getUserType().equals("stu")){
                isJsOrPb = service.isPb(user);//�Ƿ�Ϊ��־Ը��
            }
        }
        request.setAttribute("isJsOrPb",isJsOrPb);
        if(!StringUtil.isNull(model.getFdkc())){
            HashMap<String,String> fdkcxx = service.getFdkc(model);
            request.setAttribute("fdkcxx",fdkcxx);
        }
        if (!StringUtil.isNull(model.getXh())) {
            // ����ѧ��������Ϣ
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
            request.setAttribute("jbxx", xsjbxx);

            List<HashMap<String,String>> lsyyList = service.getMyYyList(model);
            request.setAttribute("lsyyList",lsyyList);
        }
        request.setAttribute("yysf", yysf);
        request.setAttribute("jbxxList", jbxxList);
        request.setAttribute("model",model);
        request.setAttribute("gotopath","xyfd_fqyy.do?method=addYy&yysf="+yysf);
        return mapping.findForward("addYy");
    }

    /**
     * ��������ԤԼ
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveAdd(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdyyForm model = (FdyyForm)form;
        String yysf = request.getParameter("yysf");
        String isJsOrPb = request.getParameter("isJsOrPb");
        if(yysf.equals("stu")){
            if(isJsOrPb.equals("true")){
                model.setYyr("��");
            }else {
                model.setYyr("����");
            }
        }else {
            model.setYyr("��ʦ");
        }
        User user = getUser(request);
        model.setFqr(user.getUserName());
        model.setFqsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
        boolean result = service.saveWdyy(model);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
                : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     * ����ԤԼ�޸Ľ���
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward updateYy(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdyyForm model = (FdyyForm)form;
        model = service.getModel(model);
        if(!StringUtil.isNull(model.getFdkc())){
            HashMap<String,String> fdkcxx = service.getFdkc(model);
            request.setAttribute("fdkcxx",fdkcxx);
        }
        boolean isJsOrPb = false;
        request.setAttribute("isJsOrPb",isJsOrPb);

        if (!StringUtil.isNull(model.getXh())) {
            // ����ѧ��������Ϣ
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        request.setAttribute("jbxxList", jbxxList);
        request.setAttribute("model",model);
        return mapping.findForward("updateYy");
    }

    /**
     * ����ԤԼ�޸�
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveUpdate(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdyyForm model = (FdyyForm)form;
        boolean result = service.updateWdyy(model);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
                : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     * ɾ��ԤԼ
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward yyDel(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response) throws Exception{
        String values = request.getParameter("values");
        if (!StringUtil.isNull(values)) {
            String[] ids = values.split(",");
            int num = service.runDelete(ids);
            boolean result = num > 0;
            String message = result ? MessageUtil.getText(
                    MessageKey.SYS_DEL_NUM, num) : MessageUtil
                    .getText(MessageKey.SYS_DEL_FAIL);
            response.getWriter().print(getJsonMessage(message));
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }

    /**
     * ����ѡ�񸨵��γ̽���
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward selectFdkc(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdyyForm model = (FdyyForm)form;
        String gotoPath = request.getParameter("goto");
        request.setAttribute("gotoPath", gotoPath);
        request.setAttribute("path","xyfd_xyfd_fdkcjg.do");
        return mapping.findForward("selectFdkc");
    }

    /**
     * �ύԤԼ
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward submit(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        FdyyForm model = (FdyyForm) form;
        String values = request.getParameter("values");
        model.setYyid(values);
        boolean result = service.submit(model);
        String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
                : MessageKey.SYS_SUBMIT_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     * ȡ��δ��ȷ�ϸ�����ԤԼ
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        FdyyForm model = (FdyyForm) form;
        String values = request.getParameter("values");
        model.setYyid(values);
        FdyyForm fdyyForm = service.getModel(model);
        String zt = fdyyForm.getZt();
        if(!StringUtil.isNull(zt)&&zt.equals("4")){
            response.getWriter().print(getJsonMessage("�ø���ԤԼ����ɣ��޷�ȡ��"));
            return null;
        }
        boolean result = service.cancel(model);
        String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
                : MessageKey.SYS_CANCEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     * ȡ���ѱ�ȷ�ϸ�����ԤԼ
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward qxYy(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        FdyyForm model = (FdyyForm) form;
        if (SAVE.equalsIgnoreCase(model.getType())) {
            User user = getUser(request);
            boolean result = service.cancel(model);
            if(result){
                result = service.qxYy(model,user);
            }
            String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
                    : MessageKey.SYS_CANCEL_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
            return null;
        }
        request.setAttribute("yyid",model.getYyid());
        return mapping.findForward("qxYy");
    }

    /**
     * �鿴ԤԼ
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward viewYy(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdyyForm model = (FdyyForm)form;
        model = service.getModel(model);
        if(!StringUtil.isNull(model.getFdkc())){
            HashMap<String,String> fdkcxx = service.getFdkc(model);
            request.setAttribute("fdkcxx",fdkcxx);
        }
        boolean isJsOrPb = false;
        request.setAttribute("isJsOrPb",isJsOrPb);

        if (!StringUtil.isNull(model.getXh())) {
            // ����ѧ��������Ϣ
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        request.setAttribute("jbxxList", jbxxList);
        request.setAttribute("model",model);
        return mapping.findForward("viewYy");
    }

    /**
     * ����ԤԼ��ѧ�����棩
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = url)
    public ActionForward fdyyListStu(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        FdyyForm model = (FdyyForm)form;
        if (QUERY.equalsIgnoreCase(model.getType())) {
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // ��ѯ
            List<HashMap<String, String>> resultList = service.yykcList(model,user);

            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        String path = "xyfd_xyfd_fqyy.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("fdyyListStu");
    }

    /**
     * ����ԤԼ����ʦ/�󱲽��棩
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = url)
    public ActionForward fdyyListTea(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        FdyyForm model = (FdyyForm)form;
        User user = getUser(request);
        if (QUERY.equalsIgnoreCase(model.getType())) {
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            // ��ѯ
            model.setYyzt("tea");
            List<HashMap<String, String>> resultList = service.yykcList(model,user);

            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        boolean isJsOrPb = true;
        if(user.getUserType().equals("stu")){
            isJsOrPb = service.isPb(user);//�Ƿ�Ϊ��־Ը��
        }
        request.setAttribute("isJsOrPb",isJsOrPb);

        String path = "xyfd_xyfd_fqyy.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("fdyyListTea");
    }

    /**
     * ����γ�����
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward pjkc(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdyyForm model = (FdyyForm)form;
        HashMap<String,String> kcpjxx = service.getKcpj(model);
        if(SAVE.equalsIgnoreCase(model.getType())){
            User user = getUser(request);
            boolean result = service.saveKcpj(model,user);
            String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
                    : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
            return null;
        }
        HashMap<String,String> fdyyxx = service.getFdyyByYyh(model);
        request.setAttribute("fdyyxx",fdyyxx);
        request.setAttribute("kcpjxx",kcpjxx);
        return mapping.findForward("pjkc");
    }
}
