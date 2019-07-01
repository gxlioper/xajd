package com.zfsoft.xgxt.dycjgl.jcsz;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

public class DydmwhAction extends SuperAction<DydmwhForm,DydmwhService> {
    private static final String url = "dycjgl_jcsz_dmwh.do";


    public ActionForward dycjglDmwh(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String path = "dycjgl_jcsz_dmwh.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("dycjglDmwh");
    }

    public ActionForward dmwhQuery(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DydmwhForm model = (DydmwhForm) form;
        DydmwhService service = new DydmwhService();
        //查询结果集
        List<HashMap<String,String>> resultList = service.getPageList(model);
        JSONArray dataList = JSONArray.fromObject(resultList);
        response.getWriter().print(dataList);
        return null;
    }

    public ActionForward addDmwh(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("addDmwh");
    }




    public ActionForward saveDmwh(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DydmwhForm model = (DydmwhForm) form;
        DydmwhService service = new DydmwhService();
        //判断项目代码和名称是否存在
        boolean isExist = service.isExistByXmmc(model);
        if(!isExist){
            boolean result = service.runInsert(model);
            String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
            return null;
        }else{
            response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_FYFF_DMWH_EXIST));
            return null;
        }
    }


    public ActionForward delDmwh(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        DydmwhForm model = (DydmwhForm) form;
        DydmwhService service = new DydmwhService();
        String values = request.getParameter("values");

        if(!StringUtil.isNull(values)){
            //判断分数表当中是否已使用
            String checkFfxmForFfjg = service.checkfsb(values);

            if(!checkFfxmForFfjg.trim().equals("")){
                String message= MessageUtil.getText(MessageKey.XSXX_WBDZC_UPDATE,checkFfxmForFfjg);
                response.getWriter().print(getJsonMessage(message));
                return null;
            }
            //判断补考表中是否使用
            String checkFfxmForbkcj = service.checkbkcj(values);
            if(!checkFfxmForbkcj.trim().equals("")){
                String message= MessageUtil.getText(MessageKey.XSXX_WBDZC_UPDATE,checkFfxmForbkcj);
                response.getWriter().print(getJsonMessage(message));
                return null;
            }

            int num = service.runDelete(values.split(","));
            boolean result = num > 0;
            String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num)
                    : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
            response.getWriter().print(getJsonMessage(message));
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }

        return null;
    }


    public ActionForward updateDmwh(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DydmwhForm model = (DydmwhForm) form;
        DydmwhService service = new DydmwhService();
        DydmwhForm myForm = service.getModel(model);
        BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
        return mapping.findForward("updateDmwh");

    }

    public ActionForward updateSaveDmwh(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DydmwhForm model = (DydmwhForm) form;
        DydmwhService service = new DydmwhService();

        boolean isExist=service.isExist(model);
        if(!isExist){
            boolean result = service.runUpdate(model);
            String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
            return null;
        }else{
            response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_FYFF_DMWH_MCEXIST));
            return null;

        }
    }
}
