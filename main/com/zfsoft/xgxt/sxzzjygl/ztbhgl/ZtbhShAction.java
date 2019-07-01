package com.zfsoft.xgxt.sxzzjygl.ztbhgl;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import common.newp.StringUtil;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;


/**
 *主题班会审核
 */
public class ZtbhShAction extends SuperAction<ZtbhShForm,ZtbhShService> {
    ZtbhShService service = new ZtbhShService();
    private static final String url = "sxzzjy_ztbhgl_ztbhsh.do";

    /**
     *审核信息列表
     */
    @SystemAuth(url = url)
    public ActionForward ztbhSh(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        ZtbhShForm model = (ZtbhShForm) form;
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
        //将用户身份置回页面
        request.setAttribute("path", url);
        request.setAttribute("userType", user.getUserStatus());
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("ztbhSh");
    }

    /**
     *单个审核
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward ztbhShDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        ZtbhShForm ztbhShForm = (ZtbhShForm) form;
        ZtbhShForm ztbh = service.getModel(ztbhShForm);
        ztbhShForm.setSplc(ztbh.getSplc());
        List<HashMap<String,String>>model =service.getZtbhInfo(ztbhShForm.getSqid());
        request.setAttribute("model",model.get(0));
        request.setAttribute("sqid",ztbhShForm.getSqid());
        request.setAttribute("splc",ztbh.getSplc());
        request.setAttribute("shid",ztbhShForm.getShid());
        request.setAttribute("fj",ztbh.getFj());
        return mapping.findForward("ztbhShDgsh");
    }



    /**
     *单个审核保存
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward saveForDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        ZtbhShForm model = (ZtbhShForm) form;

        User user = getUser(request);
        // 保存单个审核
        boolean result = service.saveForDgsh(model, user);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     *批量审核
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward ztbhShPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        return mapping.findForward("ztbhShPlsh");
    }

    /**
     *批量审核保存
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward saveForPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        ZtbhShForm ztbhShForm = (ZtbhShForm) form;
        User user = getUser(request);
        String message = service.saveForPlsh(ztbhShForm, user);
        response.getWriter().print(getJsonMessage(message));
        return null;
    }


    /**
     *撤销审核
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward cancelShLast(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        ZtbhShForm ztbhShForm = (ZtbhShForm) form;
        // 最后一级撤销
        boolean isSuccess = service.cancelShLast(ztbhShForm);
        String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }
}
