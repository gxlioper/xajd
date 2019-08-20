package com.zfsoft.xgxt.xyfd.fdjlwh;


import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xyfd.fdkcwh.fdkcjg.FdkcjgService;
import com.zfsoft.xgxt.xyfd.wfcyywh.FdyyForm;
import com.zfsoft.xgxt.xyfd.wfcyywh.FdyyService;
import common.newp.StringUtil;
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

/**
 * Created by llf on 2019/8/6.
 */
public class FdjlAction extends SuperAction<FdyyForm,FdyyService> {
    private FdyyService service = new FdyyService();
    private FdkcjgService fdkcjgService = new FdkcjgService();
    private static final String url = "xyfd_xyfd_fqyy.do";
    private static List<HashMap<String, String>> jbxxList = null;

    public static String XYFD = "hdbl";

    static {
        BdpzService bdpzService = new BdpzService();
        // ѧ��������Ϣ��ʾ����
        jbxxList = bdpzService.getJbxxpz(XYFD);
    }

    @SystemAuth(url = url)
    public ActionForward fdjlList(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        FdyyForm model = (FdyyForm)form;
        User user = getUser(request);
        if (QUERY.equalsIgnoreCase(model.getType())) {
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);

            // ��ѯ
            List<HashMap<String, String>> resultList = service.getPageList(model, user);

            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        boolean isJsOrPb = true;
        if(user.getUserType().equals("stu")){
            isJsOrPb = service.isPb(user);//�Ƿ�Ϊ��־Ը��
        }
        request.setAttribute("isJsOrPb",isJsOrPb);
        String path = "xyfd_xyfd_fqyy.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("fdjlList");
    }
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward addFdjl(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        FdyyForm model = (FdyyForm)form;
        HashMap<String,String> fdyyxx = service.getFdyyByYyh(model);
        if (SAVE.equalsIgnoreCase(model.getType())){
            String zt = fdyyxx.get("zt"); //��ȡ��ǰԤԼ״̬
            if(zt.equals("4")||zt.equals("6")){ //�Ѹ���
                response.getWriter().print(getJsonMessage("��ԤԼ��ȷ�ϸ����������ظ�ȷ�ϣ�"));
                return null;
            }
            if(zt.equals("0")||zt.equals("3")){ // ԤԼδ�ύ�����˻�
                response.getWriter().print(getJsonMessage("��ԤԼ�ѱ�������"));
                return null;
            }
            User user = getUser(request);
            boolean result = service.saveFdjl(model,user);
            String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
                    : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
            return null;
        }
        String xh = fdyyxx.get("xh");
        if(!StringUtil.isNull(xh)){
            // ����ѧ��������Ϣ

            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
            request.setAttribute("jbxx", xsjbxx);
        }
        request.setAttribute("fdyyxx",fdyyxx);
        return mapping.findForward("addFdjl");
    }

    public ActionForward viewFdjl(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception{
        FdyyForm model = (FdyyForm)form;
        HashMap<String,String> fdyyxx = service.getFdyyByYyh(model);
        String xh = fdyyxx.get("xh");
        if(!StringUtil.isNull(xh)){
            // ����ѧ��������Ϣ

            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xh);
            request.setAttribute("jbxx", xsjbxx);
        }
        request.setAttribute("fdyyxx",fdyyxx);
        HashMap<String,String> fdjlxx = service.getFdjl(model);
        request.setAttribute("fdjlxx",fdjlxx);
        return mapping.findForward("viewFdjl");
    }

}
