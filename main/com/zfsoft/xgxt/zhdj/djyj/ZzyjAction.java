package com.zfsoft.xgxt.zhdj.djyj;

import com.zfsoft.xgxt.base.action.SuperAction;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

public class ZzyjAction extends SuperAction<ZzyjForm,ZzyjService> {
    private ZzyjService service = new  ZzyjService();
    private static final String url = "zhdj_dzdy_zzsjyj.do";


    public ActionForward getZzyjList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        ZzyjForm model = (ZzyjForm) form;
        User user = getUser(request);
        if (QUERY.equalsIgnoreCase(model.getType())) {
            // 查询
            List<HashMap<String, String>> resultList = service.getPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        //将用户身份置回页面
        request.setAttribute("userType", user.getUserStatus());
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("search");
    }
}
