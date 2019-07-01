package com.zfsoft.xgxt.xyfd.fdswh;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.form.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/6/24.
 */
public class FdsAction extends SuperAction<FdsForm,FdsService> {

    private FdsService fdsService = new FdsService();
    /**
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward fdsList(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdsForm model = (FdsForm)form;
        if (QUERY.equalsIgnoreCase(model.getType())){
            User user = getUser(request);
            List<HashMap<String,String>> resultList = getService().getPageList(model,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        String path = "xg_xyfd_fdswh.do";
        request.setAttribute("path", path);
        return mapping.findForward("fdsList");
    }

    /**
     * 新增辅导室
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addfds(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdsForm model = (FdsForm)form;
        if (SAVE.equalsIgnoreCase(model.getType())){
            boolean flag = fdsService.saveFds(model);
            String key = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }
        return mapping.findForward("addfds");
    }

    /**
     * 修改辅导室
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updatefds(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdsForm model = (FdsForm)form;
        HashMap<String,String> fdsxx = fdsService.getFds(model);
        BeanUtils.copyProperties(model,fdsxx);
        request.setAttribute("fdsxx",fdsxx);
        if (UPDATE.equalsIgnoreCase(model.getType())){
            boolean flag = fdsService.updateFds(model);
            String key = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }
        return mapping.findForward("updatefds");
    }

}
