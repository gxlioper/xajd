package com.zfsoft.xgxt.jjgl.jjsc;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.jjgl.xqwh.XqwhService;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @功能描述:家教时长
 * @auther: 王晨辉[1692]
 */
public class JjscAction extends SuperAction<JjscForm, JjscService> {

    private static final String PATH = "jjgl_jjsccx.do";

    public ActionForward jjscCx(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JjscForm model = (JjscForm) form;
        String doType = request.getParameter("doType");
        if (StringUtils.isNotNull(doType)&&QUERY.equals(doType)){
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            //查询
            List<HashMap<String,String>> resultList = getService().getPageList(model,getUser(request));
            if(resultList == null){
                resultList = new ArrayList<>();
            }
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path", PATH);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("jjscCx");

    }

    public ActionForward jjscCk(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JjscForm model = (JjscForm) form;
        //需求id参数
        String xqid = model.getJjbh();
        XqwhService service = new XqwhService();
        Map<String,String> xqwhMap = service.getXqwhMap(xqid);
        request.setAttribute("xqwhMap",xqwhMap);
        return mapping.findForward("jjscCk");
    }
}
