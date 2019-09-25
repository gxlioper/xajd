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
import net.sf.json.JSONObject;
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
import java.util.Map;

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
        // 学生基本信息显示配置
        jbxxList = bdpzService.getJbxxpz(XYFD);
    }

    /**
     * 预约列表
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
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // 查询
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
     * 加载预约新增界面
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

        String yysf = request.getParameter("yysf"); //预约身份（学生、教师/朋辈）
        User user = getUser(request);
        boolean isJsOrPb = true;
        if(yysf.equals("stu")){
            model.setXh(user.getUserName());
            isJsOrPb = false;
        }else { // yysf = 'tea'
            if(user.getUserType().equals("stu")){
                isJsOrPb = service.isPb(user);//是否为朋辈志愿者
            }
        }
        request.setAttribute("isJsOrPb",isJsOrPb);
        if(!StringUtil.isNull(model.getFdkc())){
            HashMap<String,String> fdkcxx = service.getFdkc(model);
            request.setAttribute("fdkcxx",fdkcxx);
        }
        if (!StringUtil.isNull(model.getXh())) {
            // 加载学生基本信息
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
     * 保存新增预约
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
                model.setYyr("朋辈");
            }else {
                model.setYyr("本人");
            }
        }else {
            model.setYyr("教师");
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
     * 加载预约修改界面
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
            // 加载学生基本信息
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        request.setAttribute("jbxxList", jbxxList);
        request.setAttribute("model",model);
        return mapping.findForward("updateYy");
    }

    /**
     * 保存预约修改
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
     * 删除预约
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
     * 弹出选择辅导课程界面
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
     * 提交预约
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
     * 取消未被确认辅导的预约
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
        if(!StringUtil.isNull(zt)&&zt.equals("1")){
            response.getWriter().print(getJsonMessage("1"));
            return null;
        }
        if(!StringUtil.isNull(zt)&&zt.equals("4")){
            response.getWriter().print(getJsonMessage("该辅导预约已完成，无法取消"));
            return null;
        }
        boolean result = service.cancel(model);
        String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
                : MessageKey.SYS_CANCEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     * 取消已被确认辅导的预约
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
            boolean result = service.qxYy(model,user);
            String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
                    : MessageKey.SYS_CANCEL_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
            return null;
        }
        List<HashMap<String,String>> qxyyList = service.getQxyyList();//取消原因
        request.setAttribute("qxyyList",qxyyList);
        request.setAttribute("yyid",model.getYyid());
        return mapping.findForward("qxYy");
    }

    /**
     * 查看预约
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
            // 加载学生基本信息
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        request.setAttribute("jbxxList", jbxxList);
        request.setAttribute("model",model);
        return mapping.findForward("viewYy");
    }

    /**
     * 辅导预约（学生界面）
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
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // 查询
            List<HashMap<String, String>> resultList = service.yykcList(model,user);

            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        String path = "xyfd_xyfd_fdyy_stu.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("fdyyListStu");
    }

    /**
     * 辅导预约（教师/朋辈界面）
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
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            // 查询
            model.setYyzt("tea");
            List<HashMap<String, String>> resultList = service.yykcList(model,user);

            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        boolean isJsOrPb = true;
        if(user.getUserType().equals("stu")){
            isJsOrPb = service.isPb(user);//是否为朋辈志愿者
        }
        request.setAttribute("isJsOrPb",isJsOrPb);

        String path = "xyfd_xyfd_fdyy_js.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("fdyyListTea");
    }

    /**
     * 保存课程评价
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
