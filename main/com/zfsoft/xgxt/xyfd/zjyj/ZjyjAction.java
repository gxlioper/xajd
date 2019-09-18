package com.zfsoft.xgxt.xyfd.zjyj;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.xyfd.aljlwh.AljlForm;
import com.zfsoft.xgxt.xyfd.aljlwh.AljlService;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/9/9.
 */
public class ZjyjAction extends SuperAction<ZjyjForm,ZjyjService> {
    private static final String url = "xyfd_xyfd_zjyj.do";
    private ZjyjService service = new ZjyjService();
    private AljlService aljlService = new AljlService();

    @SystemAuth(url = url)
    public ActionForward getZjyjList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        ZjyjForm model = (ZjyjForm)form;
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
        String path = "xyfd_xyfd_zjyj.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("getZjyjList");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward saveZjjl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ZjyjForm model = (ZjyjForm) form;
        AljlForm aljlForm = new AljlForm();
        aljlForm.setJdh(model.getAlbh());
        aljlForm = aljlService.getModel(aljlForm);
        if(aljlForm.getSfzj().equals("是")){//已转介
            response.getWriter().print(getJsonMessage("已转介，请勿重复操作！"));
            return null;
        }
        User user = getUser(request);
        boolean result = service.saveZjjl(model,user);
        String message = result ? MessageKey.SYS_SAVE_SUCCESS :
                MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(message));
        return null;
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward jszj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ZjyjForm model = (ZjyjForm) form;
        model = service.getModel(model);
        if(StringUtils.isNotNull(model.getQrsj())){
            response.getWriter().print(getJsonMessage("已接收！"));
            return null;
        }
        User user = getUser(request);
        model.setQrr(user.getUserName());
        model.setQrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
        boolean result = service.runUpdate(model);
        String message = result ? MessageKey.SYS_SAVE_SUCCESS :
                MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(message));
        return null;
    }


}
