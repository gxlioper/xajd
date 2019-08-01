package com.zfsoft.xgxt.xyfd.pbwh.pbsh;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.xyfd.fdswh.FdsForm;
import com.zfsoft.xgxt.xyfd.fdswh.FdsService;
import com.zfsoft.xgxt.xyfd.pbwh.pbsq.PbsqForm;
import com.zfsoft.xgxt.xyfd.pbwh.pbsq.PbsqService;
import common.newp.StringUtil;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/7/31.
 */
public class PbshAction extends SuperAction<PbshForm,PbshService> {
    private PbshService service = new PbshService();
    private PbsqService pbsqService = new PbsqService();
    private FdsService fdsService = new FdsService();
    private static final String url = "xyfd_xyfd_pbsh.do";

    @SystemAuth(url = url)
    public ActionForward pbshList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception{
        PbshForm model = (PbshForm)form;
        if (QUERY.equalsIgnoreCase(model.getType())) {
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            List<HashMap<String, String>> resultList = service.getPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path", "xyfd_xyfd_pbsh.do");
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("pbshList");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward pbDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PbshForm model = (PbshForm) form;
        if (SAVE.equalsIgnoreCase(model.getType())) {
            User user = getUser(request);
            // ���浥�����
            boolean result = service.saveSh(model, user);
            String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
            return null;
        }
        PbsqForm pbsqForm = new PbsqForm();
        pbsqForm.setSqid(model.getSqid());
        //=====================��־Ը����Ϣ=====================//
        HashMap<String,String> pbxxlist = pbsqService.getPbxx(pbsqForm);
        BeanUtils.copyProperties(pbsqForm,pbxxlist);
        request.setAttribute("rs", StringUtils.formatData(pbxxlist));

        //==================רҵ������Ϣ=====================//
        HashMap<String,String> zypmlist = pbsqService.getZypmlist(pbsqForm,new User());
        request.setAttribute("zypmlist",zypmlist);
        //======================ѧ����Ϣ=====================//
        HashMap<String,String> xsxxlist = pbsqService.getXsxx(pbsqForm);
        request.setAttribute("xsxxlist",xsxxlist);
        //=================����ѧ�𼰱��ý���====================//
        List<HashMap<String,String>> jlxxlist = pbsqService.getJlxx(pbsqForm);
        StringBuilder jlxx = new StringBuilder();
        for(int i=0;i<jlxxlist.size();i++){
            jlxx.append(jlxxlist.get(i).get("xmmc")+" ");
        }
        request.setAttribute("jlxx",jlxx);
        //======================��������Ϣ=====================//
        FdsForm fdsForm = new FdsForm();
        fdsForm.setId(pbsqForm.getFds());
        HashMap<String,String> fdsxx = fdsService.getFds(fdsForm);
        request.setAttribute("fdsxx",fdsxx);

        model = service.getModel(model);
        model.setShid(request.getParameter("shid"));
        request.setAttribute("model", StringUtils.formatData(model));
        return mapping.findForward("pbDgsh");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward pbPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PbshForm model = (PbshForm) form;
        User user = getUser(request);
        if (SAVE.equalsIgnoreCase(model.getType())) {
            String message = service.savePlsh(model, user);
            response.getWriter().print(getJsonMessage(message));
            return null;
        }
        return mapping.findForward("pbPlsh");
    }
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward cancelSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PbshForm model = (PbshForm) form;
        String sqid = request.getParameter("sqid");
        String shzt = request.getParameter("shzt");
        model.setShzt(shzt);
        model.setSqid(sqid);
        // ���һ������
        boolean isSuccess = service.cancel(model);
        String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }
}
