package xsgzgl.gygl.cwfpgl;

import com.alibaba.fastjson.JSONObject;
import com.zfsoft.ms.mail.util.CollectionUtils;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xsgzgl.gygl.xjdfygl.ldxxgl.LdxxglService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class CwfpglNewAction extends SuperAction<CwfpglForm,CwfpglNewService> {

    private LdxxglService ldxxglService = new LdxxglService();
    public ActionForward cwfpList(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        CwfpglForm model = (CwfpglForm)form;
        if(QUERY.equals(model.getType())){
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            List<HashMap<String,String>> data = getService().searchList(model,user);
            response.getWriter().print(JSONArray.fromObject(data));
            return null;
        }
        request.setAttribute("path","gygl_zsgl_cwfp.do");
        return mapping.findForward("cwfpList");
    }

    public ActionForward cwfp(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        CwfpglForm model = (CwfpglForm)form;
        CwfpglNewService service = getService();
        if(SAVE.equals(model.getType())){
            User user = getUser(request);
            boolean flag = service.save(model,user);
            String key = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }
        List<HashMap<String,String>> njList = service.getNjList();
        List<HashMap<String,String>> xyList;
        List<HashMap<String,String>> zyList;
        List<HashMap<String,String>> bjList;
        List<HashMap<String,String>> bjfpxx;
        Map<String,String> tjxx;
        //未选择表格数据，默认查询出来的第一个
        if(StringUtil.isNull(model.getPks())){
            String nj0 = njList.get(0).get("nj");
            xyList = service.getXyListByNj(nj0);
            String xy0 = xyList.get(0).get("dm");
            zyList = service.getZyListByNjXy(nj0,xy0);
            String zy0 = zyList.get(0).get("dm");
            bjList = service.getBjListByNjXyZy(nj0,xy0,zy0);
            String bj0 = bjList.get(0).get("dm");
            bjfpxx = service.getYfpTjXx(nj0,xy0,zy0,bj0);
            tjxx = service.getBjTjXx(nj0,xy0,zy0,bj0);
        } else {//选择了表格数据
            String[] pk = model.getPks().split("@!!!");
            xyList = service.getXyListByNj(pk[0]);
            zyList = service.getZyListByNjXy(pk[0],pk[1]);
            bjList = service.getBjListByNjXyZy(pk[0],pk[1],pk[2]);
            bjfpxx = service.getYfpTjXx(pk[0],pk[1],pk[2],pk[3]);
            tjxx = service.getBjTjXx(pk[0],pk[1],pk[2],pk[3]);
        }
        request.setAttribute("bjfpxx",bjfpxx);//当前选中班级已分配统计表格数据
        request.setAttribute("tjxx",tjxx);//当前选中班级已分配统计数据
        request.setAttribute("njList",njList);
        request.setAttribute("bjList",bjList);
        request.setAttribute("zyList",zyList);
        request.setAttribute("xyList",xyList);
        request.setAttribute("ldList",ldxxglService.getLdxxByXq(""));
        return mapping.findForward("cwfp");
    }
    public ActionForward qkFp(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception{
        CwfpglForm model = (CwfpglForm)form;
        boolean f = getService().qkFp(model);
        String key = f ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(key));
        return null;
    }
    //当前选中班级已分配统计数据
    public ActionForward getBjFpxx1(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws Exception{
        String nj = request.getParameter("nj");
        String xy = request.getParameter("xy");
        String zy = request.getParameter("zy");
        String bj = request.getParameter("bj");
        CwfpglNewService service = getService();
        Map<String,String> map = service.getBjTjXx(nj,xy,zy,bj);
        response.getWriter().print(net.sf.json.JSONObject.fromObject(map));
        return null;
    }
    //当前选中班级已分配统计表格数据
    public ActionForward getBjFpxx2(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception{
        String nj = request.getParameter("nj");
        String xy = request.getParameter("xy");
        String zy = request.getParameter("zy");
        String bj = request.getParameter("bj");
        CwfpglNewService service = getService();
        List<HashMap<String,String>> data = service.getYfpTjXx(nj,xy,zy,bj);
        response.getWriter().print(JSONArray.fromObject(data));
        return null;
    }
    public ActionForward search(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws Exception{
        String lddm = request.getParameter("lddm");
        String qsxb = request.getParameter("qsxb");
        User user = getUser(request);
        List<HashMap<String,String>> lcList = getService().getLdLc(lddm,qsxb,user);
        response.getWriter().print(JSONArray.fromObject(lcList));
        return null;
    }
    public ActionForward getQsCw(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception{
        String key = request.getParameter("key");
        User user = getUser(request);
        Map<String,List<HashMap<String,String>>> qsMap = getService().getQscw(key,user);
        //为了使画面展示有序
        JSONObject jb = new JSONObject(16,true);
        jb.putAll(qsMap);
        response.getWriter().print(jb);
        return null;
    }

    public ActionForward getXyListByNj(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws Exception{
        String nj = request.getParameter("nj");
        List<HashMap<String,String>> data = getService().getXyListByNj(nj);
        response.getWriter().print(JSONArray.fromObject(data));
        return null;
    }

    public ActionForward getZyListByNjXy(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response) throws Exception{
        String nj = request.getParameter("nj");
        String xy = request.getParameter("xy");
        List<HashMap<String,String>> data = getService().getZyListByNjXy(nj,xy);
        response.getWriter().print(JSONArray.fromObject(data));
        return null;
    }

    public ActionForward getBjListByNjXyZy(ActionMapping mapping, ActionForm form,
                                               HttpServletRequest request, HttpServletResponse response) throws Exception{
        String nj = request.getParameter("nj");
        String xy = request.getParameter("xy");
        String zy = request.getParameter("zy");
        List<HashMap<String,String>> data = getService().getBjListByNjXyZy(nj,xy,zy);
        response.getWriter().print(JSONArray.fromObject(data));
        return null;
    }

    //获取当前班级已分配统计信息
    public ActionForward getYfpTjXx(ActionMapping mapping, ActionForm form,
                                           HttpServletRequest request, HttpServletResponse response) throws Exception{
        String nj = request.getParameter("nj");
        String xy = request.getParameter("xy");
        String zy = request.getParameter("zy");
        String bj = request.getParameter("bj");
        List<HashMap<String,String>> data = getService().getYfpTjXx(nj,xy,zy,bj);
        response.getWriter().print(JSONArray.fromObject(data));
        return null;
    }

    //===============书院床位分配=======================
    public ActionForward sycwfp(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws Exception{
        CwfpglForm model = (CwfpglForm)form;
        CwfpglNewService service = getService();
        if(SAVE.equals(model.getType())){
            User user = getUser(request);
            boolean flag = service.saveForSy(model,user);
            String key = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }
        List<HashMap<String,String>> njList = service.getNjListForSy();
        List<HashMap<String,String>> syList = null;
        List<HashMap<String,String>> bjList = null;
        List<HashMap<String,String>> bjfpxx;
        Map<String,String> tjxx;
        //未选择表格数据，默认查询出来的第一个
        if(StringUtil.isNull(model.getPks())){
            String nj0 = njList.get(0).get("nj");
            syList = service.getSyListByNj(nj0);
            String sy0 = syList.get(0).get("dm");
            bjList = service.getBjListByNjSy(nj0,sy0);
            String bj0 = bjList.get(0).get("dm");
            bjfpxx = service.getYfpTjXxForSy(nj0,sy0,bj0);
            tjxx = service.getBjTjXxForSy(nj0,sy0,bj0);
        } else {//选择了表格数据
            String[] pk = model.getPks().split("@!!!");
            syList = service.getXyListByNj(pk[0]);
            bjList = service.getBjListByNjSy(pk[0],pk[1]);
            bjfpxx = service.getYfpTjXxForSy(pk[0],pk[1],pk[2]);
            tjxx = service.getBjTjXxForSy(pk[0],pk[1],pk[2]);
        }
        request.setAttribute("bjfpxx",bjfpxx);//当前选中班级已分配统计表格数据
        request.setAttribute("tjxx",tjxx);//当前选中班级已分配统计数据
        request.setAttribute("njList",njList);
        request.setAttribute("bjList",bjList);
        request.setAttribute("syList",syList);
        request.setAttribute("ldList",ldxxglService.getLdxxByXq(""));
        return mapping.findForward("sycwfp");
    }

    public ActionForward getSyListByNj(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response) throws Exception{
        String nj = request.getParameter("nj");
        List<HashMap<String,String>> data = getService().getSyListByNj(nj);
        response.getWriter().print(JSONArray.fromObject(data));
        return null;
    }

    public ActionForward getBjListByNjSy(ActionMapping mapping, ActionForm form,
                                           HttpServletRequest request, HttpServletResponse response) throws Exception{
        String nj = request.getParameter("nj");
        String sy = request.getParameter("sy");
        List<HashMap<String,String>> data = getService().getBjListByNjSy(nj,sy);
        response.getWriter().print(JSONArray.fromObject(data));
        return null;
    }

    //当前选中班级已分配统计数据
    public ActionForward getBjFpxx1ForSy(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception{
        String nj = request.getParameter("nj");
        String sy = request.getParameter("sy");
        String bj = request.getParameter("bj");
        CwfpglNewService service = getService();
        Map<String,String> map = service.getBjTjXxForSy(nj,sy,bj);
        response.getWriter().print(net.sf.json.JSONObject.fromObject(map));
        return null;
    }
    //当前选中班级已分配统计表格数据
    public ActionForward getBjFpxx2ForSy(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception{
        String nj = request.getParameter("nj");
        String sy = request.getParameter("sy");
        String bj = request.getParameter("bj");
        CwfpglNewService service = getService();
        List<HashMap<String,String>> data = service.getYfpTjXxForSy(nj,sy,bj);
        response.getWriter().print(JSONArray.fromObject(data));
        return null;
    }
}
