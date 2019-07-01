package com.zfsoft.xgxt.zhdj.dyzlxz;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.zhdj.djyj.DyslyjService;
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

public class DyzlxzAction extends SuperAction<DyzlxzForm,DyzlxzService> {
    private DyzlxzService  service = new  DyzlxzService();
    private static final String url = "zhdj_dzdy_dyzlxz.do";


    public ActionForward getDyzlList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        DyzlxzForm model = (DyzlxzForm) form;
        User user = getUser(request);
        HashMap<String, String> rs = service.getUserZzmm(user);
        if(rs != null )
        {
            String zzmm = rs.get("zzmm");
            if("01".equals(zzmm) || "02".equals(zzmm) )//党员和预备党员
            {
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
            else{
                String msg = "该模块只允许学生党员用户访问，请确认！";
                request.setAttribute("yhInfo", msg);
                return new ActionForward("/yhInfo.do", false);
            }

        }
        else{
            String msg = "该模块只允许学生党员用户访问，请确认！";
            request.setAttribute("yhInfo", msg);
            return new ActionForward("/yhInfo.do", false);
        }

    }
}
