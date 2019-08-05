package com.zfsoft.xgxt.xyfd.fdkcwh.fdkcsh;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.xyfd.fdkcwh.fdkcsq.FdkcsqForm;
import com.zfsoft.xgxt.xyfd.fdkcwh.fdkcsq.FdkcsqService;
import com.zfsoft.xgxt.xyfd.fdswh.FdsForm;
import com.zfsoft.xgxt.xyfd.fdswh.FdsService;
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
 * Created by llf on 2019/7/31.
 */
public class FdkcshAction extends SuperAction<FdkcshForm,FdkcshService> {
    private FdkcshService service = new FdkcshService();
    private FdkcsqService fdkcsqService = new FdkcsqService();
    private FdsService fdsService = new FdsService();
    private static final String url = "xyfd_xyfd_fdkcsh.do";

    @SystemAuth(url = url)
    public ActionForward fdkcshList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdkcshForm model = (FdkcshForm)form;
        if (QUERY.equalsIgnoreCase(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            List<HashMap<String, String>> resultList = service.getPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path", "xyfd_xyfd_fdkcsh.do");
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("fdkcshList");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward fdkcDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FdkcshForm model = (FdkcshForm) form;
        if (SAVE.equalsIgnoreCase(model.getType())) {
            User user = getUser(request);
            // 保存单个审核
            boolean result = service.saveSh(model, user);
            String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
            return null;
        }
        model = service.getModel(model);
        model.setShid(request.getParameter("shid"));
        request.setAttribute("model", StringUtils.formatData(model));

        FdkcsqForm fdkcsqForm = new FdkcsqForm();
        BeanUtils.copyProperties(fdkcsqForm,model);
        StringBuilder fdjs = new StringBuilder();
        if(!StringUtil.isNull(fdkcsqForm.getFdjs())&&fdkcsqForm.getFdjs().startsWith("JS")){//教师登记号
            String xm = fdkcsqService.getFdjs(fdkcsqForm);
            fdjs.append(fdkcsqForm.getFdjs() + "：" + xm);
        }else if(!StringUtil.isNull(fdkcsqForm.getFdjs())&&fdkcsqForm.getFdjs().startsWith("PB")){//朋辈志愿者登记号
            String xm = fdkcsqService.getFdjs(fdkcsqForm);
            fdjs.append(fdkcsqForm.getFdjs() + "：" + xm);
        }else {
            throw new Exception("登记号不存在");
        }
        request.setAttribute("fdjsxm",fdjs.toString());
        HashMap<String,String> fdjsxx = fdkcsqService.getFdjsxx(fdkcsqForm);//辅导教师（朋辈志愿者）信息
        request.setAttribute("fdjsxx",fdjsxx);


        return mapping.findForward("fdkcDgsh");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward fdkcPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FdkcshForm model = (FdkcshForm) form;
        User user = getUser(request);
        if (SAVE.equalsIgnoreCase(model.getType())) {
            String message = service.savePlsh(model, user);
            response.getWriter().print(getJsonMessage(message));
            return null;
        }
        return mapping.findForward("fdkcPlsh");
    }
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward cancelSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        FdkcshForm model = (FdkcshForm) form;
        String sqid = request.getParameter("sqid");
        String shzt = request.getParameter("shzt");
        model.setShzt(shzt);
        model.setSqid(sqid);
        // 最后一级撤销
        boolean isSuccess = service.cancel(model);
        String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }
}
