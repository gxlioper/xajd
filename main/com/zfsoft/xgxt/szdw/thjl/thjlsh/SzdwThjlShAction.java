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
 * @������TODO
 * @���ߣ�1601
 * @���ڣ�
 */
public class SzdwThjlShAction extends SuperAction<SzdwThjlShForm,SzdwThjlShService>{
    private static List<HashMap<String,String>> jbxxList = null;
    private static List<HashMap<String,String>> thlxList = null;
    private static final String SZDWTHJL = "szdwthjl";

    static {
        BdpzService bdpzService = new BdpzService();
        // ѧ��������Ϣ��ʾ����
        jbxxList = bdpzService.getJbxxpz(SZDWTHJL);
        SzdwThlxService thlxService = new SzdwThlxService();
        // ��ѯ����̸������
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
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // ��ѯ
            List<HashMap<String, String>> resultList = getService().getPageList(model,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path","szdw_thjl_thjl_sh.do");
        return mapping.findForward("shList");
    }

    /**
     * ���
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
                    thjlInfo.put("gzdj", "����");
                }
            }
        }


//        String path = "szdw_thjl.do?method=zjsq";

        //����ѧ��������Ϣ
        if(user.getUserStatus().equalsIgnoreCase("stu")){
            szXsxx(request,user.getUserName());
            request.setAttribute("type", "update");
        }else{
            szXsxx(request,xh);
        }
        request.setAttribute("jbxxList", jbxxList);
        SzdwThlxService thlxService = new SzdwThlxService();
        // ��ѯ����̸������
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
        //��ѯѧ����Ϣ
        if(xh != null && !"".equals(xh)){
            //����ѧ��������Ϣ
            XsxxService xsxxService = new XsxxService();
            HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
            request.setAttribute("jbxx", xsjbxx);
            request.setAttribute("xh", xh);
        }
        //ѧ��������Ϣ��ʾ����
        request.setAttribute("jbxxList", jbxxList);
    }

    /**
     * ������˱���
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
