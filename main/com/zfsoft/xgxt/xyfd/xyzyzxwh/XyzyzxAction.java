package com.zfsoft.xgxt.xyfd.xyzyzxwh;


import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.hdgl.hdbljg.HdbljgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xyfd.fdkcwh.fdkcjg.FdkcjgService;
import com.zfsoft.xgxt.xyfd.fdkcwh.fdkcsq.FdkcsqDao;
import com.zfsoft.xgxt.xyfd.fdkcwh.fdkcsq.FdkcsqForm;
import com.zfsoft.xgxt.xyfd.fdkcwh.fdkcsq.FdkcsqService;
import com.zfsoft.xgxt.xyfd.wfcyywh.FdyyForm;
import com.zfsoft.xgxt.xyfd.wfcyywh.FdyyService;
import common.newp.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by llf on 2019/8/14.
 */
public class XyzyzxAction extends SuperAction<XyzyzxForm,XyzyzxService> {
    private XyzyzxService service = new XyzyzxService();
    private FdyyService fdyyService = new FdyyService();
    private FdkcsqService fdkcsqService = new FdkcsqService();
    private static final String url = "xyfd_xyfd_fqyy.do";
    private static List<HashMap<String, String>> jbxxList = null;

    public static String XYFD = "hdbl";

    static {
        BdpzService bdpzService = new BdpzService();
        // 学生基本信息显示配置
        jbxxList = bdpzService.getJbxxpz(XYFD);
    }

    /**
     * 学业与专业咨询记录列表
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = url)
    public ActionForward xyzyzxList(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        XyzyzxForm model = (XyzyzxForm)form;
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
        boolean isJsOrPb = false;
        if(user.getUserType().equals("stu")){
            isJsOrPb = fdyyService.isPb(user);//是否为朋辈志愿者
        }else {
            isJsOrPb = fdyyService.isJs(user);//是否为辅导教师
            if(!isJsOrPb){ //不是登记的辅导教师
                isJsOrPb = fdkcsqService.isAdmin(user); //是否为管理员或辅导中心人员
            }
        }
        request.setAttribute("isJsOrPb",isJsOrPb);
        String path = "xyfd_xyfd_xyzyzxjl.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("xyzyzxList");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward addZxjl(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response) throws Exception{
        XyzyzxForm model = (XyzyzxForm)form;
        User user = getUser(request);
        if(SAVE.equalsIgnoreCase(model.getType())){
            model.setXn(Base.currXn);
            model.setXq(Base.currXq);
            model.setLrr(user.getUserName());
            model.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
            boolean result = service.runInsert(model);
            String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
                    : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
            return null;
        }
        if (!StringUtil.isNull(model.getXh())) {
            // 加载学生基本信息
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        List<HashMap<String,String>> zxyyList = service.getZxyyList();
        request.setAttribute("zxyyList",zxyyList);
        request.setAttribute("model",model);
        request.setAttribute("gotopath","xyfd_xyzyzxjl.do?method=addZxjl");
        return mapping.findForward("addZxjl");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward updateZxjl(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception{
        XyzyzxForm model = (XyzyzxForm)form;
        if(SAVE.equalsIgnoreCase(model.getType())){
            User user = getUser(request);
            model.setLrr(user.getUserName());
            model.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
            boolean result = service.runUpdate(model);
            String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
                    : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
            return null;
        }
        model = service.getModel(model);
        if (!StringUtil.isNull(model.getXh())) {
            // 加载学生基本信息
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        //加载辅导教师信息
        FdkcsqForm fdkcsqForm = new FdkcsqForm();
        fdkcsqForm.setFdjs(model.getFdjs());
        String fdjsxm = fdkcsqService.getFdjs(fdkcsqForm);
        request.setAttribute("fdjsxm",fdjsxm);
        if(!StringUtil.isNull(model.getFdjs())&&model.getFdjs().startsWith("PB")){
            request.setAttribute("fdjslx","朋辈");
        }
        if(!StringUtil.isNull(model.getFdjs())&&model.getFdjs().startsWith("JS")){
            request.setAttribute("fdjslx","教师");
        }
        List<HashMap<String,String>> zxyyList = service.getZxyyList();
        request.setAttribute("zxyyList",zxyyList);
        request.setAttribute("model",model);
        request.setAttribute("gotopath","xyfd_xyzyzxjl.do?method=addZxjl");
        return mapping.findForward("updateZxjl");
    }
    public ActionForward delZxjl(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception{
        String values = request.getParameter("values");
        if (!StringUtil.isNull(values)){
            String[] mess = service.deleteZxjl(values.split(","));
            if(null==mess||mess.length!=2){
                String message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
                response.getWriter().print(getJsonMessage(message));
                return null;
            }
            Map<String, String> map = new HashMap<String, String>();
            map.put("num",mess[0]);
            map.put("nodel",mess[1]);
            JSONObject json = JSONObject.fromObject(map);
            response.getWriter().print(json);
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward viewZxjl(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception{
        XyzyzxForm model = (XyzyzxForm)form;
        model = service.getModel(model);
        if (!StringUtil.isNull(model.getXh())) {
            // 加载学生基本信息
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        //加载辅导教师信息
        FdkcsqForm fdkcsqForm = new FdkcsqForm();
        fdkcsqForm.setFdjs(model.getFdjs());
        String fdjsxm = fdkcsqService.getFdjs(fdkcsqForm);
        request.setAttribute("fdjsxm",fdjsxm);
        if(!StringUtil.isNull(model.getFdjs())&&model.getFdjs().startsWith("PB")){
            request.setAttribute("fdjslx","朋辈");
        }
        if(!StringUtil.isNull(model.getFdjs())&&model.getFdjs().startsWith("JS")){
            request.setAttribute("fdjslx","教师");
        }
        HashMap<String,String> zxjlxx = service.getZxjlxx(model);
        List<HashMap<String,String>> zxyyList = service.getZxyyList();
        request.setAttribute("zxyyList",zxyyList);
        request.setAttribute("model",zxjlxx);
        request.setAttribute("gotopath","xyfd_xyzyzxjl.do?method=addZxjl");
        return mapping.findForward("viewZxjl");
    }

    public ActionForward selectFdjs(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        XyzyzxForm model = (XyzyzxForm)form;
        if (QUERY.equalsIgnoreCase(model.getType())) {
            User user = getUser(request);
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            // 查询
            List<HashMap<String, String>> resultList = service.getFdjsList(model,user);

            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        return mapping.findForward("selectFdjs");
    }
}
