package com.zfsoft.xgxt.xyfd.pjwh;

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
import xgxt.utils.GetTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * 满意度评价
 * Created by llf on 2019/8/17.
 */
public class MydAction extends SuperAction<MydForm,MydService> {
    private static final String url = "xyfd_xyfd_mydpj.do";
    private MydService service = new MydService();

    @SystemAuth(url = url)
    public ActionForward mydpjList(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        MydForm model = (MydForm)form;
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
        String path = "xyfd_xyfd_mydpj.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("mydpjList");
    }

    /**
     * 填写评价
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward txpj(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response) throws Exception{
        MydForm model = (MydForm)form;
        if(SAVE.equalsIgnoreCase(model.getType())){
            User user = getUser(request);
            model.setPjr(user.getUserName());
            model.setPjsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
            boolean result = service.savePj(model);
            String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
                    : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
            return null;
        }
        HashMap<String,String> pjxx = service.getPjxx(model);
        request.setAttribute("pjxx",pjxx);
        HashMap<String,String> jlxx = service.getJlxx(model);
        request.setAttribute("jlxx",jlxx);
        return mapping.findForward("txpj");
    }

    public ActionForward viewPj(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws Exception{
        MydForm model = (MydForm)form;
        HashMap<String,String> pjxx = service.getPjxx(model);
        request.setAttribute("pjxx",pjxx);
        HashMap<String,String> jlxx = service.getJlxx(model);
        request.setAttribute("jlxx",jlxx);
        return mapping.findForward("viewPj");
    }

}
