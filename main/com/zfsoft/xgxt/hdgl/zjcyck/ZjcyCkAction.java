package com.zfsoft.xgxt.hdgl.zjcyck;

import com.zfsoft.xgxt.base.action.SuperAction;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：1601
 * @日期：
 */
public class ZjcyCkAction extends SuperAction<ZjcyCkForm,ZjcyCkService>{

    public ActionForward zjcyList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ZjcyCkForm model = (ZjcyCkForm) form;
        if (QUERY.equalsIgnoreCase(model.getType())) {
            User user = getUser(request);
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);

            List<HashMap<String, String>> resultList = getService().getPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }

        request.setAttribute("path", "hdgl_hdgl_zjcyck.do");
        return mapping.findForward("zjcyList");
    }
}
