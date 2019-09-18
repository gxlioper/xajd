package com.zfsoft.xgxt.xyfd.yjgywh;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.xyfd.aljlwh.AljlForm;
import com.zfsoft.xgxt.xyfd.aljlwh.AljlService;
import com.zfsoft.xgxt.xyfd.xyyj.XyyjForm;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * 预警干预
 * Created by llf on 2019/9/17.
 */
public class YjgyAction extends SuperAction<YjgyForm,YjgyService> {

    private YjgyService service = new YjgyService();
    private static final String url = "xyfd_xyfd_yjgy.do";

    @SystemAuth(url = url)
    public ActionForward yjgyList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                              HttpServletResponse response) throws Exception{
        YjgyForm model = (YjgyForm)form;
        if(QUERY.equalsIgnoreCase(model.getType())){
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // 查询

            HashMap<String,String> xnxq = service.getXnxq();
            model.setXn(xnxq.get("xn"));
            model.setXq(xnxq.get("xq"));
            List<HashMap<String, String>> resultList = service.getPageList(model, user);

            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        String path = "xyfd_xyfd_yjgy.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("yjgyList");
    }

    /**
     * 预警转介
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward yjzj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        YjgyForm model = (YjgyForm) form;
        if(SAVE.equalsIgnoreCase(model.getType())){
            User user = getUser(request);
            model.setZjr(user.getUserName());
            model.setZjsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
            boolean result = service.saveZjjl(model);
            String message = result ? MessageKey.SYS_SAVE_SUCCESS :
                    MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(message));
            return null;
        }
        HashMap<String,String> xnxq = service.getXnxq();
        model.setXn(xnxq.get("xn"));
        model.setXq(xnxq.get("xq"));
        HashMap<String,String> yjzjxx = service.getYjzjjl(model);
        BeanUtils.copyProperties(model,yjzjxx);
        request.setAttribute("model",model);
        return mapping.findForward("yjzj");
    }

    public ActionForward selectJs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        YjgyForm model = (YjgyForm) form;
        if(QUERY.equalsIgnoreCase(model.getType())){
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            // 查询
            List<HashMap<String, String>> resultList = service.selectJs(model);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("model",model);
        return mapping.findForward("selectJs");
    }

    /**
     * 干预结束
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward gyjs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        YjgyForm model = (YjgyForm) form;
        User user = getUser(request);
        AljlService aljlService = new AljlService();
        AljlForm aljlForm = new AljlForm();
        HashMap<String,String> xnxq = service.getXnxq();
        aljlForm.setXn(xnxq.get("xn"));
        aljlForm.setXq(xnxq.get("xq"));
        aljlForm.setXh(model.getXh());
        aljlForm.setJdyy(model.getYjyy());
        aljlForm.setJdrq(GetTime.getTimeByFormat("yyyy-MM-dd"));

        HashMap<String,String> aljlxx = aljlService.getAljlxx(aljlForm);
        if(aljlxx.size()>0){
            response.getWriter().print(getJsonMessage("已存在工作案例！"));
            return null;
        }

        boolean result = aljlService.saveGzal(aljlForm,user);
        String message = result ? MessageKey.SYS_SAVE_SUCCESS :
                MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(message));
        return null;
    }
}
