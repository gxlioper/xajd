package com.zfsoft.xgxt.sxzzjygl.bjhdgl;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 *班级活动申请
 */
public class BjhdSqAction extends SuperAction<BjhdSqForm,BjhdSqService> {
    private BjhdSqService service = new BjhdSqService();
    private static final String url = "sxzzjy_bjhdgl_bjhdsq.do";


    /**
     *申请列表
     */
    @SystemAuth(url = url)
    public ActionForward bjhdSq(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        BjhdSqForm model = (BjhdSqForm) form;
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
        //获取申请审核开关信息
        String[] sqshkg = service.getSqShKg();
        request.setAttribute("sqkg", sqshkg == null ? "0" : sqshkg[0]);
        request.setAttribute("path", url);
        return mapping.findForward("bjhdSq");
    }

    /**
     *跳转添加申请页面
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward bjhdSqAdd(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("bjhdSqAdd");

    }


    /**
     * //获取学生信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getXx(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        BjhdSqForm model = (BjhdSqForm) form;
        if (QUERY.equals(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);

            User user = getUser(request);
            // 查询
            List<HashMap<String, String>> resultList = service.getXxList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("url", url);
        request.setAttribute("path", "sxzzjy_bjhdgl_bjhdsq.do?method=getXx");

        return mapping.findForward("getXx");
    }


    /**
     * //获取班级信息
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward getBj(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        BjhdSqForm model = (BjhdSqForm) form;
        if (QUERY.equals(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);

            User user = getUser(request);
            // 查询
            List<HashMap<String, String>> resultList = service.getBjList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("url", url);
        request.setAttribute("path", "sxzzjy_bjhdgl_bjhdsq.do?method=getBj");

        return mapping.findForward("getBj");
    }


    /**
     *申请添加保存
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward bjhdSqSaveForAdd(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjhdSqForm model = (BjhdSqForm) form;
        User user = getUser(request);
        model.setSqr(user.getUserName());
        boolean result = service.bjhdSqSaveForAdd(model);
        String messageKey = "submit".equals(model.getType())?(result? MessageKey.SYS_SUBMIT_SUCCESS:MessageKey.SYS_SUBMIT_FAIL)
                :(result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL);
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }


    /**
     *申请修改
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward bjhdSqEdit(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        BjhdSqForm model = (BjhdSqForm) form;
        BjhdSqForm myForm = service.getModel(model);
        if (myForm!=null) {
            BeanUtils.copyProperties(model,myForm);
        }
        String[] hdfzr =service.getHdfzr(model);
        request.setAttribute("hdfzr",hdfzr[0]);
        String[] bjmc =service.getBjmc(model);
        request.setAttribute("bjmc",bjmc[0]);
        return mapping.findForward("bjhdSqEdit");
    }

    /**
     *申请的修改保存
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward bjhdSqSaveForEdit(ActionMapping mapping, ActionForm form,
                                           HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        BjhdSqForm model = (BjhdSqForm) form;
        User user = getUser(request);
        model.setSqr(user.getUserName());
        boolean result = service.bjhdSqSaveForEdit(model);
        String messageKey = "submit".equals(model.getType())?(result?MessageKey.SYS_SUBMIT_SUCCESS:MessageKey.SYS_SUBMIT_FAIL)
                :(result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL);
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     *申请删除
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward bjhdSqDel(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception {

        String values = request.getParameter("values");
        if (!StringUtil.isNull(values)) {
            String[] ids = values.split(",");
            int num = service.runDelete(ids);
            boolean result = num > 0;
            String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
            response.getWriter().print(getJsonMessage(message));
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }

    /**
     * 申请提交
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward bjhdSqSubmit(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response) throws Exception {

        BjhdSqForm model = (BjhdSqForm) form;
        BjhdSqForm myForm = service.getModel(model);
        if (myForm!=null) {
            BeanUtils.copyProperties(model,myForm);
        }
        boolean result = service.bjhdSqSubmit(model);
        String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     * 申请撤销
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward bjhdSqCancel(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response) throws Exception {

        String sqid = request.getParameter("values");
        String lcid = request.getParameter("splcid");
        // 只有刚提交并且第一级未审核的前提下，申请人可以撤销
        boolean result = service.bjhdSqCancel(sqid, lcid);

        String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }


    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward getHdInfo(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        BjhdSqForm model = (BjhdSqForm) form;
        BjhdSqForm myForm = service.getModel(model);
        if (myForm!=null) {
            BeanUtils.copyProperties(model,myForm);
        }
        String[] hdfzr =service.getHdfzr(model);
        request.setAttribute("hdfzr",hdfzr[0]);
        String[] bjmc =service.getBjmc(model);
        request.setAttribute("bjmc",bjmc[0]);
        request.setAttribute("model",model);
        request.setAttribute("fj",myForm.getFj());
        return mapping.findForward("getHdInfo");
    }

}
