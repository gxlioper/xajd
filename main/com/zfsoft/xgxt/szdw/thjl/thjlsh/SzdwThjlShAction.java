package com.zfsoft.xgxt.szdw.thjl.thjlsh;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.szdw.thjl.SzdwThjlService;
import com.zfsoft.xgxt.szdw.thjl.thlx.SzdwThlxService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：1601
 * @日期：
 */
public class SzdwThjlShAction extends SuperAction<SzdwThjlShForm,SzdwThjlShService>{
    private static List<HashMap<String,String>> jbxxList = null;
    private static List<HashMap<String,String>> thlxList = null;
    private static final String SZDWTHJL = "szdwthjl";

    static {
        BdpzService bdpzService = new BdpzService();
        // 学生基本信息显示配置
        jbxxList = bdpzService.getJbxxpz(SZDWTHJL);
        SzdwThlxService thlxService = new SzdwThlxService();
        // 查询所有谈话类型
        try {
            thlxList = thlxService.getAllThlxList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ActionForward shList(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response) throws Exception {

        SzdwThjlShForm model = (SzdwThjlShForm) form;
        if(QUERY.equals(model.getType())){
            // 生成高级查询对象
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // 查询
            List<HashMap<String, String>> resultList = getService().getPageList(model,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path","szdw_thjl_thjl_sh.do");
        return mapping.findForward("shList");
    }

    /**
     * 审核
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward sh(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        SzdwThjlShForm myForm = (SzdwThjlShForm) form;
        User user = getUser(request);
        /*String xh = request.getParameter("xh");
        if(StringUtil.isNull(xh)){
            xh = myForm.getXh();
        }
        String zgh = request.getParameter("zgh");
        if(StringUtil.isNull(zgh)){
            zgh = myForm.getZgh();
        }*/
        String doType = request.getParameter("doType");
        HashMap<String, String> thjlInfo=new HashMap<String,String>();
        SzdwThjlService service = new SzdwThjlService();
        String xh = null;
        if(!StringUtil.isNull(myForm.getSqid())){

            thjlInfo = service.getSqMap(myForm.getSqid());
            xh = thjlInfo.get("xh");
            if(thjlInfo!=null && thjlInfo.size()>0){
                if("update".equals(doType) && StringUtils.isNull(thjlInfo.get("gzdj"))){
                    thjlInfo.put("gzdj", "三星");
                }
            }
        }


//        String path = "szdw_thjl.do?method=zjsq";

        //设置学生基本信息
        if(user.getUserStatus().equalsIgnoreCase("stu")){
            szXsxx(request,user.getUserName());
            request.setAttribute("type", "update");
        }else{
            szXsxx(request,xh);
        }
        request.setAttribute("jbxxList", jbxxList);
        SzdwThlxService thlxService = new SzdwThlxService();
        // 查询所有谈话类型
        List<HashMap<String,String>> thlxList = thlxService.getAllThlxList();
        request.setAttribute("thlxList", thlxList);

//        request.setAttribute("path", path);
//        request.setAttribute("zgh", zgh);

        request.setAttribute("doType", "view");
        request.setAttribute("thjlInfo", StringUtils.formatData(thjlInfo));
        request.setAttribute("userStatus", user.getUserStatus());
        OptionUtil optionUtil = new OptionUtil();
        request.setAttribute("gzdjList", optionUtil.getOptions(OptionUtil.THJL_GZDJ));
        request.setAttribute("isnotList", optionUtil.getOptions(OptionUtil.ISNOT));
        request.setAttribute("xh",xh);
        return mapping.findForward("sh");
    }

    private void szXsxx(HttpServletRequest request,String xh){
        //查询学生信息
        if(xh != null && !"".equals(xh)){
            //加载学生基本信息
            XsxxService xsxxService = new XsxxService();
            HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
            request.setAttribute("jbxx", xsjbxx);
            request.setAttribute("xh", xh);
        }
        //学生基本信息显示配置
        request.setAttribute("jbxxList", jbxxList);
    }

    /**
     * 单个审核保存
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward shBc(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request, HttpServletResponse response) throws Exception {
        SzdwThjlShForm myForm = (SzdwThjlShForm) form;
        User user = getUser(request);
        boolean flag = getService().saveSh(myForm,user);
        String messageKey = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    public ActionForward cancelSh(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        SzdwThjlShForm myForm = (SzdwThjlShForm) form;
        boolean isSuccess = getService().cancelSh(myForm);
        String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    public ActionForward plsh(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SzdwThjlShForm myForm = (SzdwThjlShForm) form;
        User user = getUser(request);
        if("SAVE".equalsIgnoreCase(myForm.getType())){
            String message = getService().plshBc(myForm, user);
            response.getWriter().print(getJsonMessage(message));
            return null;
        }
        return mapping.findForward("plsh");
    }
}
