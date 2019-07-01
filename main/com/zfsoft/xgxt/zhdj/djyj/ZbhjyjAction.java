package com.zfsoft.xgxt.zhdj.djyj;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.zhdj.xsdzbhdygl.DzbglForm;
import com.zfsoft.xgxt.zhdj.xsdzbhdygl.DzbglService;
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

public class ZbhjyjAction  extends SuperAction<ZbhjyjForm,ZbhjyjService> {
    private ZbhjyjService service = new  ZbhjyjService();
    private static final String url = "zhdj_djyj_zbhjyj.do";


    public ActionForward getZbhjyjList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        ZbhjyjForm model = (ZbhjyjForm) form;
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
