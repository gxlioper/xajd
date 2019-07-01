package xsgzgl.gygl.zcgl.zcxx;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xsgzgl.gygl.xjdgybz.ktsq.KtsqForm;
import xsgzgl.gygl.xjdgybz.ktsq.KtsqService;
import xsgzgl.gygl.xjdgybz.ktsqjg.KtsqjgForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class ZcxxAction extends SuperAction<ZcxxFrom,ZcxxService> {
    public ActionForward zcxxList(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        ZcxxFrom model = (ZcxxFrom)form;
        if(QUERY.equals(model.getType())){
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            List<HashMap<String,String>> data = getService().getPageList(model,user);
            response.getWriter().print(JSONArray.fromObject(data));
            return null;
        }
        request.setAttribute("path","gygl_zcgl_zcxxwh.do");
        return mapping.findForward("zcxxList");
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        ZcxxFrom model = (ZcxxFrom)form;
        ZcxxService service = getService();
        if(SAVE.equals(model.getType())) {
            boolean f = service.runInsert(model);
            String key = f ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }
        request.setAttribute("lxList",service.getLxdmList());
        return mapping.findForward("add");
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        ZcxxFrom model = (ZcxxFrom)form;
        ZcxxService service = getService();
        if(SAVE.equals(model.getType())) {
            boolean f = service.runUpdate(model);
            String key = f ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }
        ZcxxFrom data = service.getModel(model);
        BeanUtils.copyProperties(model,data);
        request.setAttribute("lxList",service.getLxdmList());
        return mapping.findForward("update");
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        ZcxxFrom model = (ZcxxFrom)form;
        ZcxxService service = getService();
        ZcxxFrom data = service.getModel(model);
        request.setAttribute("rs",data);
        return mapping.findForward("view");
    }

    public ActionForward del(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ids = request.getParameter("ids");
        int i = getService().runDelete(ids.split(","));
        String msg = i > 0 ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,i) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
        response.getWriter().print(msg);
        return null;
    }
}
