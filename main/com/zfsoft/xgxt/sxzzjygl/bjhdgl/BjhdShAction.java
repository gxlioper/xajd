package com.zfsoft.xgxt.sxzzjygl.bjhdgl;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.message.MessageKey;
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
 *班级活动审核
 */
public class BjhdShAction extends SuperAction<BjhdShForm,BjhdShService> {

    BjhdShService service = new BjhdShService();
    private static final String url = "sxzzjy_bjhdgl_bjhdsh.do";

    /**
     *班级活动审核列表
     */
    @SystemAuth(url = url)
    public ActionForward bjhdSh(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        BjhdShForm model = (BjhdShForm) form;
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
        return mapping.findForward("bjhdSh");
    }
    /**
     *班级活动单个审核
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward bjhdShDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        BjhdShForm bjhdShForm = (BjhdShForm) form;
        BjhdShForm ztbh = service.getModel(bjhdShForm);
        bjhdShForm.setSplc(ztbh.getSplc());
        List<HashMap<String,String>>model =service.getBjhdInfo(bjhdShForm.getSqid());
        request.setAttribute("model",model.get(0));
        request.setAttribute("sqid",bjhdShForm.getSqid());
        request.setAttribute("splc",ztbh.getSplc());
        request.setAttribute("shid",bjhdShForm.getShid());
        request.setAttribute("fj",ztbh.getFj());
        return mapping.findForward("bjhdShDgsh");
    }

    /**
     *单个审核保存
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward saveForDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        BjhdShForm model = (BjhdShForm) form;
        User user = getUser(request);
        // 保存单个审核
        boolean result = service.saveForDgsh(model, user);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     *跳转批量审核页面
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward bjhdShPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        return mapping.findForward("bjhdShPlsh");
    }

    /**
     *批量保存
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward saveForPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        BjhdShForm model = (BjhdShForm) form;
        User user = getUser(request);
        String message = service.saveForPlsh(model, user);
        response.getWriter().print(getJsonMessage(message));
        return null;
    }

    /**
     *撤销审核
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward cancelShLast(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        BjhdShForm bjhdShForm = (BjhdShForm) form;
        // 最后一级撤销
        boolean isSuccess = service.cancelShLast(bjhdShForm);
        String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }


}
