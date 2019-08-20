package com.zfsoft.xgxt.xyfd.wsdyywh;


import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xyfd.fdkcwh.fdkcjg.FdkcjgService;
import com.zfsoft.xgxt.xyfd.wfcyywh.FdyyForm;
import com.zfsoft.xgxt.xyfd.wfcyywh.FdyyService;
import common.newp.StringUtil;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/8/6.
 */
public class WsdyyAction extends SuperAction<FdyyForm,FdyyService> {
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

    @SystemAuth(url = url)
    public ActionForward sdyyList(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        FdyyForm model = (FdyyForm)form;
        User user = getUser(request);
        if (QUERY.equalsIgnoreCase(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);

            // 查询
            List<HashMap<String, String>> resultList = service.getPageList(model, user);

            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        boolean isJsOrPb = true;
        if(user.getUserType().equals("stu")){
            isJsOrPb = service.isPb(user);//是否为朋辈志愿者
        }
        request.setAttribute("isJsOrPb",isJsOrPb);
        String path = "xyfd_xyfd_fqyy.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("sdyyList");
    }

    public ActionForward submitYy(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        FdyyForm model = (FdyyForm) form;
        String values = request.getParameter("values");
        model.setYyid(values);
        FdyyForm fdyyForm = service.getModel(model);
        String zt = fdyyForm.getZt();
        if(!StringUtil.isNull(zt)&&(zt.equals("4")||zt.equals("6"))){
            response.getWriter().print(getJsonMessage("该辅导预约已完成，请勿重复确认！"));
            return null;
        }
        boolean result = service.submitYy(model);
        String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
                : MessageKey.SYS_SUBMIT_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    public ActionForward cancelYy(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        FdyyForm model = (FdyyForm) form;
        String values = request.getParameter("values");
        model.setYyid(values);
        FdyyForm fdyyForm = service.getModel(model);
        String zt = fdyyForm.getZt();
        if(!StringUtil.isNull(zt)&&(zt.equals("4")||zt.equals("6"))){
            response.getWriter().print(getJsonMessage("该辅导预约已完成，无法取消！"));
            return null;
        }
        boolean result = service.cancelYy(model);
        String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
                : MessageKey.SYS_CANCEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

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


}
