package com.zfsoft.xgxt.ttgl.stgl.stjggl;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.ttgl.stgl.stcygl.StcyglForm;
import com.zfsoft.xgxt.ttgl.stgl.stcygl.StcyglService;
import com.zfsoft.xgxt.ttgl.stgl.stgljg.StglService;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2019/6/28.
 */
public class StjgglAction extends SuperAction {

    public ActionForward stjgglList(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        StcyglForm model = (StcyglForm) form;
        StcyglService service = new StcyglService();
        if (QUERY.equals(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            List<HashMap<String, String>> resultList = service.getPageList(
                    model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path", "xg_ttgl_stcygl.do");
        request.setAttribute("userType", getUser(request).getUserType());
        SearchModel searchModel=new SearchModel();
        request.setAttribute("searchTj", searchModel);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("stjgglList");
    }

    public ActionForward stjggl(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        StcyglForm model = (StcyglForm) form;
        StcyglService service = new StcyglService();
        if (SAVE.equalsIgnoreCase(model.getType())) {
            boolean result = service.saveStzz(model);
            String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
                    : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
            return null;
        }
        String path = "ttgl_stcygl.do?method=stzz";
        HashMap<String,String> stxxMap = service.getStxxInfo(model);
        //学生组织经费来源
        StglService stglService = new StglService();
        List<HashMap<String, String>> xszzjflyList = stglService.getXszzjflyList();
        request.setAttribute("xszzjflyList",xszzjflyList);
        model.setSjly(stxxMap.get("sjly"));
        request.setAttribute("fzrxxInfo", service.getFzrxx(model));
        request.setAttribute("tzsxxInfo", service.getTzsxx(model));
        request.setAttribute("rs", StringUtils.formatData(stxxMap));
        request.setAttribute("jgid", model.getJgid());
        request.setAttribute("path", path);
        return mapping.findForward("stjggl");
    }
}
