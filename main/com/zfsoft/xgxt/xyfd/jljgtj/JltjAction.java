package com.zfsoft.xgxt.xyfd.jljgtj;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
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
 * 记录结果统计
 * Created by llf on 2019/8/21.
 */
public class JltjAction extends SuperAction<JltjForm,JltjService> {
    private JltjService service = new JltjService();

    private static final String url = "xyfd_xyfd_jljgtj.do";

    @SystemAuth(url = url)
    public ActionForward jljgtjList(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws Exception {
        JltjForm model = (JltjForm)form;
        if(QUERY.equalsIgnoreCase(model.getType())){
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            List<HashMap<String, String>> resultList = service.getPageList(model,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        String path = "xyfd_xyfd_jljgtj.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("jljgtjList");
    }

    /**
     * 加载详细信息
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward view(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        JltjForm model = (JltjForm)form;
        HashMap<String,String> jlxx = service.getJlxx(model);
        request.setAttribute("jlxx", jlxx);
        if(model.getCxmb().equals("fd")){ //查询辅导详情
            return mapping.findForward("viewFdkc");
        }else { //查询咨询详情
            return mapping.findForward("viewXyzy");
        }
    }

}
