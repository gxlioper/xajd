package com.zfsoft.xgxt.xyfd.xyyj;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xyfd.xyzyzxwh.XyzyzxForm;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by llf on 2019/9/10.
 */
public class XyyjAction extends SuperAction<XyyjForm,XyyjService> {
    private XyyjService service = new XyyjService();
    private static final String url = "xyfd_xyfd_xyyj.do";
    @SystemAuth(url = url)
    public ActionForward getXyyjList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        XyyjForm model = (XyyjForm)form;
        if(QUERY.equalsIgnoreCase(model.getType())){
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // 查询
            List<HashMap<String, String>> resultList = service.getPageList(model, user);

            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        String path = "xyfd_xyfd_xyyj.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("getXyyjList");
    }

    public ActionForward xscjList(ActionMapping mapping,ActionForm form,HttpServletRequest request,
                                  HttpServletResponse response) throws Exception{
        XyyjForm model = (XyyjForm)form;
        if(QUERY.equalsIgnoreCase(model.getType())){
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // 查询
            List<HashMap<String, String>> resultList = service.getXscjList(model, user);

            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("model",model);
        String path = "xyfd_xyyj.do?method=xscjList";
        request.setAttribute("path", path);
        return mapping.findForward("xscjList");
    }

    /**
     * 学生成绩分析
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward xscjfx(ActionMapping mapping,ActionForm form,HttpServletRequest request,
                                  HttpServletResponse response) throws Exception{
        XyyjForm model = (XyyjForm)form;

        XsxxService xsxxService = new XsxxService();
        HashMap<String, String> xsxx = service.getXsxx(model);
        request.setAttribute("xsxx",xsxx);

        List<HashMap<String,String>> xscjfb = service.xscjfb(model); //各学期成绩分布情况
        ArrayList<String> xnxq = new ArrayList<>(); //学年学期
        ArrayList<Integer> zsxx = new ArrayList<>(); //总数
        ArrayList<Integer> ylxx = new ArrayList<>(); //优良数
        ArrayList<Integer> bjgxx = new ArrayList<>(); //不及格数
        for(HashMap<String,String> xscjxx:xscjfb){
            xnxq.add(xscjxx.get("xn") + "_" + xscjxx.get("xq"));
            zsxx.add(Integer.parseInt(xscjxx.get("zs")));
            ylxx.add(Integer.parseInt(xscjxx.get("yl")));
            bjgxx.add(Integer.parseInt(xscjxx.get("bjg")));
        }
        JSONArray xnxqList = JSONArray.fromObject(xnxq);
        JSONArray zsxxList = JSONArray.fromObject(zsxx);
        JSONArray ylxxList = JSONArray.fromObject(ylxx);
        JSONArray bjgxxList = JSONArray.fromObject(bjgxx);
        request.setAttribute("xnxqList",xnxqList);
        request.setAttribute("zsxxList",zsxxList);
        request.setAttribute("ylxxList",ylxxList);
        request.setAttribute("bjgxxList",bjgxxList);

        List<HashMap<String,String>> xfcjqs = service.xfcjqs(model);//学分成绩趋势
        ArrayList<String> xnxq2 = new ArrayList<>(); //学年学期
        ArrayList<Double> xfcjxx = new ArrayList<>(); //学分成绩
        for (HashMap<String,String> xfcj:xfcjqs){
            xnxq2.add(xfcj.get("xn")+"_"+xfcj.get("xq"));
            xfcjxx.add(Double.parseDouble(xfcj.get("xfcj")));
        }
        JSONArray xnxq2List = JSONArray.fromObject(xnxq2);
        JSONArray xfcjxxList = JSONArray.fromObject(xfcjxx);
        request.setAttribute("xnxq2List",xnxq2List);
        request.setAttribute("xfcjxxList",xfcjxxList);

        return mapping.findForward("xscjfx");
    }

    /**
     * 课程成绩分析
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward kccjfx(ActionMapping mapping,ActionForm form,HttpServletRequest request,
                                HttpServletResponse response) throws Exception{
        XyyjForm model = (XyyjForm)form;
        User user = getUser(request);
        Map<String,String[]> map=request.getParameterMap();
        if(QUERY.equalsIgnoreCase(model.getType())){
            Pages pages = new Pages();
            pages.setCurrentPage(Integer.parseInt(map.get("currentPage")[0]));
            model.setPages(pages);
            List<HashMap<String,String>> kccjList = service.kccj(model,user);
            int total = Integer.parseInt(kccjList.get(0).get("total"));
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("pages",pages);
            resultMap.put("resultList",kccjList);
            resultMap.put("total",total);
            JSONArray dataList = JSONArray.fromObject(resultMap);
            response.getWriter().print(dataList);
            return null;
        }
        return mapping.findForward("kccjfx");
    }

    /**
     * 课程成绩趋势
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward kccjqs(ActionMapping mapping,ActionForm form,HttpServletRequest request,
                                HttpServletResponse response) throws Exception{
        XyyjForm model = (XyyjForm)form;

        List<HashMap<String,String>> kccjqsList = service.kccjqs(model);

        ArrayList<String> njlist = new ArrayList<>();
        ArrayList<Double> pjflist = new ArrayList<>();
        ArrayList<Integer> bjglist = new ArrayList<>();
        ArrayList<Integer> yllist = new ArrayList<>();

        for(HashMap<String,String> kccjqs:kccjqsList){
            njlist.add(kccjqs.get("nj"));
            pjflist.add(Double.parseDouble(kccjqs.get("pjf")));
            bjglist.add(Integer.parseInt(kccjqs.get("bjg")));
            yllist.add(Integer.parseInt(kccjqs.get("yl")));
        }
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("nj",njlist);
        resultMap.put("pjf",pjflist);
        resultMap.put("bjg",bjglist);
        resultMap.put("yl",yllist);

        JSONArray dataList = JSONArray.fromObject(resultMap);
        response.getWriter().print(dataList);
        return null;
    }

    /**
     * 专业成绩分析
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward zycjfx(ActionMapping mapping,ActionForm form,HttpServletRequest request,
                                HttpServletResponse response) throws Exception{
        XyyjForm model = (XyyjForm)form;
        User user = getUser(request);
        Map<String,String[]> map=request.getParameterMap();
        if(QUERY.equalsIgnoreCase(model.getType())){
            Pages pages = new Pages();
            pages.setCurrentPage(Integer.parseInt(map.get("currentPage")[0]));
            model.setPages(pages);
            List<HashMap<String,String>> zycjList = service.zycj(model,user);
            int total = Integer.parseInt(zycjList.get(0).get("total"));
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("pages",pages);
            resultMap.put("resultList",zycjList);
            resultMap.put("total",total);
            JSONArray dataList = JSONArray.fromObject(resultMap);
            response.getWriter().print(dataList);
            return null;
        }
        return mapping.findForward("zycjfx");
    }

    /**
     * 专业成绩趋势
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward zycjqs(ActionMapping mapping,ActionForm form,HttpServletRequest request,
                                HttpServletResponse response) throws Exception{
        XyyjForm model = (XyyjForm)form;

        List<HashMap<String,String>> zycjqsList = service.zycjqs(model);

        ArrayList<String> xqlist = new ArrayList<>(); //学年学期
        ArrayList<Double> ylllist = new ArrayList<>(); //优良率
        ArrayList<Double> jgllist = new ArrayList<>(); //及格率

        for(HashMap<String,String> zycjqs:zycjqsList){
            xqlist.add(zycjqs.get("xn") + "_" + zycjqs.get("xq"));
            ylllist.add(Double.parseDouble(zycjqs.get("yll")));
            jgllist.add(Double.parseDouble(zycjqs.get("jgl")));
        }
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("xq",xqlist);
        resultMap.put("yll",ylllist);
        resultMap.put("jgl",jgllist);

        JSONArray dataList = JSONArray.fromObject(resultMap);
        response.getWriter().print(dataList);
        return null;
    }

}
