package com.zfsoft.xgxt.sxzzjygl.xxwkqk;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
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

public class XxwktjAction extends SuperAction<XxwktjForm,XxwktjService> {
    XxwktjService service = new XxwktjService();
    private static final String url = "sxzzjy_xxwkqk_xxwktj.do";


    @SystemAuth(url = url)
    public ActionForward getTjList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        XxwktjForm model = (XxwktjForm) form;

        return mapping.findForward("getTjList");
    }



    public ActionForward getBtList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        User user = getUser(request);
        XxwktjForm model = (XxwktjForm) form;
        if(model.getJssj()!=null && model.getKssj()!=null)
        {
            List<HashMap<String, String>> result = service.getBtList(model, user);
            JSONArray resultList = JSONArray.fromObject(result); // 转为json格式
            response.setContentType("text/html;charset=gbk"); // ajax请求返回数据转码，否则会中文乱码
            response.getWriter().print(resultList);
        }
        return null;
    }


    public ActionForward getList(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        User user = getUser(request);
        XxwktjForm model = (XxwktjForm) form;
        if(model.getJssj()!=null && model.getKssj()!=null)
        {
            List<HashMap<String, String>> result = service.getList(model, user);
            JSONArray resultList = JSONArray.fromObject(result); // 转为json格式
            response.setContentType("text/html;charset=gbk"); // ajax请求返回数据转码，否则会中文乱码
            response.getWriter().print(resultList);
        }
        return null;
    }



    @SystemAuth(url = url)
    public ActionForward getrqjcInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        XxwktjForm model = (XxwktjForm) form;
        User user = getUser(request);
        request.setCharacterEncoding("GBK");
        String jc = request.getParameter("jc");
        String rq = request.getParameter("rq");
        String cxnj = request.getParameter("cxnj");
        String cxxy = request.getParameter("cxxy");
        List<HashMap<String,String>> njList = service.getnjList();
        List<HashMap<String, String>> xyList = service.getjcBtList(model, user);
        String nj ="";
        if (cxnj==null || cxnj.equals(""))
        {
            for (int i = 0; i < njList.size(); i++) {
                if(i==0)
                {
                    nj=njList.get(i).get("nj");
                }
                else{
                    if(njList.get(i).get("nj")!=null) {
                        nj = nj + "," + njList.get(i).get("nj");
                    }
                }
            }
        }
        else{
            nj=cxnj;
        }

        request.setAttribute("njString",nj);
        request.setAttribute("njList",njList);
        request.setAttribute("xyList",xyList);
        request.setAttribute("jc",jc);
        request.setAttribute("rq",rq);
        request.setAttribute("xymc",cxxy);
        return mapping.findForward("getrqjcInfo");
    }

    public ActionForward getjcrqBtList(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        User user = getUser(request);
        XxwktjForm model = (XxwktjForm) form;
            List<HashMap<String, String>> result = service.getjcrqBtList(model, user);
            JSONArray resultList = JSONArray.fromObject(result); // 转为json格式
            response.setContentType("text/html;charset=gbk"); // ajax请求返回数据转码，否则会中文乱码
            response.getWriter().print(resultList);

        return null;
    }


    public ActionForward getjcrqInfoList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        User user = getUser(request);
        XxwktjForm model = (XxwktjForm) form;
            List<HashMap<String, String>> result = service.getjcrqInfoList(model, user);
            JSONArray resultList = JSONArray.fromObject(result); // 转为json格式
            response.setContentType("text/html;charset=gbk"); // ajax请求返回数据转码，否则会中文乱码
            response.getWriter().print(resultList);

        return null;
    }


    @SystemAuth(url = url)
    public ActionForward  getxyrqInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        XxwktjForm model = (XxwktjForm) form;
        User user = getUser(request);
        if (QUERY.equalsIgnoreCase(model.getType())) {
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
        request.setAttribute("path", "sxzzjy_xxwkqk_xxwktj.do?method=getxyrqInfo");
        request.setAttribute("userType", user.getUserStatus());
        request.setAttribute("jc",model.getJc());
        request.setAttribute("rq",model.getRq());

        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("getxyrqInfo");
    }



}
