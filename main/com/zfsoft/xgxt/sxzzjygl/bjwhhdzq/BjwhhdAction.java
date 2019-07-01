package com.zfsoft.xgxt.sxzzjygl.bjwhhdzq;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.sxzzjygl.bjhdgl.BjhdSqForm;
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

public class BjwhhdAction extends SuperAction<BjwhhdForm,BjwhhdService> {
    BjwhhdService service = new BjwhhdService();
    private static final String url = "sxzzjy_bjwhhdzq_bjwh.do";


//    @SystemAuth(url = url)
//    public ActionForward getList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
//                                HttpServletResponse response) throws Exception {
//        BjwhhdForm model = (BjwhhdForm) form;
//        User user = getUser(request);
//        if (QUERY.equalsIgnoreCase(model.getType())) {
//            // 生成高级查询对象
//            CommService comService = new CommService();
//            SearchModel searchModel = comService.getSearchModel(request);
//            model.setSearchModel(searchModel);
//            // 查询
//            List<HashMap<String, String>> resultList = service.getPageList(model, user);
//            JSONArray dataList = JSONArray.fromObject(resultList);
//            response.getWriter().print(dataList);
//            return null;
//        }
//        //将用户身份置回页面
//        request.setAttribute("path", url);
//        request.setAttribute("userType", user.getUserStatus());
//        FormModleCommon.commonRequestSet(request);
//        return mapping.findForward("getList");
//    }

    public ActionForward getList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("getList");
    }

    /**
     * @描述:班级文化互动专区展示的类别
     * @作者：lyx [工号：1599]
     * @日期：2018/6/29 13:57
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward getAlbForView(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<HashMap<String, String>> resultList = service.getAlbForView();
        JSONArray dataList = JSONArray.fromObject(resultList);
        response.getWriter().print(dataList);
        return null;
    }


    /**
     * @描述:班级文化互动专区展示内容
     * @作者：lyx [工号：1599]
     * @日期：2018/6/28 17:05
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward getNewsList(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String typedm = request.getParameter("typedm");
        String size = request.getParameter("size");
        List<HashMap<String, String>> resultList = service.getNewsList(typedm,size);
        JSONArray dataList = JSONArray.fromObject(resultList);
        response.getWriter().print(dataList);
        return null;
    }

    /**
     * @描述:更多
     * @作者：lgx [工号：1553]
     * @日期：2018/6/29 9:07
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward getNewsmore(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjwhhdForm model = (BjwhhdForm) form;
        User user = getUser(request);
        if (QUERY.equals(model.getType())) {
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            // 查询
            List<HashMap<String, String>> resultList = service.getPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        //将用户身份置回页面
        request.setAttribute("path", url);
        request.setAttribute("hdlx",model.getHdlx());
        request.setAttribute("userType", user.getUserStatus());
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("getNewsmore");
    }


}
