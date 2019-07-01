package com.zfsoft.xgxt.szdw.fdyzyhfz.ywxsypx;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.szdw.fdyxx.FdyxxService;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class YwxsypxAction extends SuperAction<YwxsypxForm,YwxsypxService>{
    private FdyxxService fdyxxService = new FdyxxService();
    private XtwhShlcService shlcService = new XtwhShlcService();
    private ShlcInterface shlc = new CommShlcImpl();
    public ActionForward cssz(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        YwxsypxForm model = (YwxsypxForm) form;
        YwxsypxService service = getService();
        if(SAVE.equals(model.getType())){
            boolean f = service.cssz(model.getSplc());
            String key = f ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }
        request.setAttribute("splcList",shlcService.getShlcByDjlx("szdw"));
        YwxsypxForm mform = new YwxsypxForm();
        mform.setSplc(service.getSplc());
        request.setAttribute("model", mform);
        return mapping.findForward("cssz");
    }
    public ActionForward ywxxypxSqList(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {

        YwxsypxForm model = (YwxsypxForm) form;
        if(QUERY.equals(model.getType())){
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // 查询
            List<HashMap<String, String>> resultList = getService().getPageList(model,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path","szdw_fdy_ywxxypx_sq.do");
        return mapping.findForward("ywxxypxsqList");
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response) throws Exception {

        YwxsypxForm model = (YwxsypxForm) form;
        User user = getUser(request);
        YwxsypxService service = getService();
        if(SAVE.equals(model.getType()) || SUBMIT.equals(model.getType())){
            boolean f = service.save(model);
            String key = f ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }

        HashMap<String,String> data = fdyxxService.getFdyInfo1(user.getUserName());
        List<HashMap<String,String>> bmList = service.getAllBmList();
        request.setAttribute("fdyxx",data);
        request.setAttribute("bmList",bmList);
        return mapping.findForward("add");
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {

        YwxsypxForm model = (YwxsypxForm) form;
        User user = getUser(request);
        YwxsypxService service = getService();
        if(SAVE.equals(model.getType()) || SUBMIT.equals(model.getType())){
            boolean f = service.update(model);
            String key = f ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }
        YwxsypxForm result = service.getModel(model);
        HashMap<String,String> data = fdyxxService.getFdyInfo1(result.getZgh());
        List<HashMap<String,String>> bmList = service.getAllBmList();
        request.setAttribute("fdyxx",data);
        request.setAttribute("bmList",bmList);
        request.setAttribute("filepath",result.getFilepath());
        BeanUtils.copyProperties(model,result);
        return mapping.findForward("update");
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {

        YwxsypxForm model = (YwxsypxForm) form;
        YwxsypxService service = getService();

        YwxsypxForm result = service.getModel(model);
        HashMap<String,String> data = fdyxxService.getFdyInfo1(result.getZgh());
        request.setAttribute("fdyxx",data);
        request.setAttribute("filepath",result.getFilepath());
        request.setAttribute("model",result);
        return mapping.findForward("view");
    }
    public ActionForward submit(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        YwxsypxForm model = (YwxsypxForm) form;
        String id = request.getParameter("values");
        model.setSqid(id);
        model.setType(SUBMIT);
        model.setShzt(Constants.YW_SHZ);
        boolean result = getService().update(model);
        String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String sqid = request.getParameter("values");
        String lcid = request.getParameter("splc");
        //只有刚提交并且第一级未审核的前提下，申请人可以撤销

        boolean result = shlc.firstStepCancle(sqid,lcid);
        if(result){
            //更新业务状态为'未提交'
            YwxsypxForm model = new YwxsypxForm();
            model.setSqid(sqid);
            model.setSplc(lcid);
            model.setShzt(Constants.YW_WTJ);
            result = getService().update(model);
        }
        String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
                : MessageKey.SYS_CANCEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }
    public ActionForward del(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ids = request.getParameter("values");
        int i = getService().runDelete(ids.split(","));
        String msg = i>0 ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,i) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
        response.getWriter().print(getJsonMessage(msg));
        return null;
    }
    public ActionForward ywxxypxShList(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response) throws Exception {

        YwxsypxForm model = (YwxsypxForm) form;
        if(QUERY.equals(model.getType())){
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // 查询
            List<HashMap<String, String>> resultList = getService().getShList(model,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path","szdw_fdy_ywxxypx_sh.do");
        return mapping.findForward("ywxxypxshList");
    }

    public ActionForward sh(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws Exception {

        YwxsypxForm model = (YwxsypxForm) form;
        YwxsypxService service = getService();
        if(SAVE.equals(model.getType())){
            User user = getUser(request);
            boolean flag = service.saveSh(model,user);
            String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
            return null;
        }
        YwxsypxForm result = service.getModel(model);
        HashMap<String,String> data = fdyxxService.getFdyInfo1(result.getZgh());
        request.setAttribute("fdyxx",data);
        request.setAttribute("filepath",result.getFilepath());
        request.setAttribute("model",result);
        return mapping.findForward("sh");
    }

    public ActionForward cancelSh(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request, HttpServletResponse response) throws Exception {
        YwxsypxForm model = (YwxsypxForm) form;
        boolean isSuccess = getService().cancelSh(model);
        String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    public ActionForward plsh(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        YwxsypxForm myForm = (YwxsypxForm) form;
        User user = getUser(request);
        if("SAVE".equalsIgnoreCase(myForm.getType())){
            String message = getService().plshBc(myForm, user);
            response.getWriter().print(getJsonMessage(message));
            return null;
        }
        return mapping.findForward("plsh");
    }
}
