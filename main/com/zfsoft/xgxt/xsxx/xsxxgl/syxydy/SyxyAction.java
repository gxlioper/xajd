package com.zfsoft.xgxt.xsxx.xsxxgl.syxydy;

import com.zfsoft.xgxt.base.action.SuperAction;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

public class SyxyAction extends SuperAction<SyxyForm, SyxyService> {
    public ActionForward syXyList(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        SyxyService syxyService = new SyxyService();
        SyxyForm myForm = (SyxyForm) form;
        if(QUERY.equals(myForm.getType())){
            List<HashMap<String, String>> resultList = syxyService.getSyxy();
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path", "xsxx_xsxxgl_syxydy.do");
        return mapping.findForward("syXyList");
    }
}
