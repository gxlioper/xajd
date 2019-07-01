package com.zfsoft.xgxt.sxzzjygl.bjhdgl;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.message.MessageKey;
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
 *�༶����
 */
public class BjhdShAction extends SuperAction<BjhdShForm,BjhdShService> {

    BjhdShService service = new BjhdShService();
    private static final String url = "sxzzjy_bjhdgl_bjhdsh.do";

    /**
     *�༶�����б�
     */
    @SystemAuth(url = url)
    public ActionForward bjhdSh(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        BjhdShForm model = (BjhdShForm) form;
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
        //���û�����û�ҳ��
        request.setAttribute("path", url);
        request.setAttribute("userType", user.getUserStatus());
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("bjhdSh");
    }
    /**
     *�༶��������
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward bjhdShDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        BjhdShForm bjhdShForm = (BjhdShForm) form;
        BjhdShForm ztbh = service.getModel(bjhdShForm);
        bjhdShForm.setSplc(ztbh.getSplc());
        List<HashMap<String,String>>model =service.getBjhdInfo(bjhdShForm.getSqid());
        request.setAttribute("model",model.get(0));
        request.setAttribute("sqid",bjhdShForm.getSqid());
        request.setAttribute("splc",ztbh.getSplc());
        request.setAttribute("shid",bjhdShForm.getShid());
        request.setAttribute("fj",ztbh.getFj());
        return mapping.findForward("bjhdShDgsh");
    }

    /**
     *������˱���
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward saveForDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        BjhdShForm model = (BjhdShForm) form;
        User user = getUser(request);
        // ���浥�����
        boolean result = service.saveForDgsh(model, user);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     *��ת�������ҳ��
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward bjhdShPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {

        return mapping.findForward("bjhdShPlsh");
    }

    /**
     *��������
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward saveForPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        BjhdShForm model = (BjhdShForm) form;
        User user = getUser(request);
        String message = service.saveForPlsh(model, user);
        response.getWriter().print(getJsonMessage(message));
        return null;
    }

    /**
     *�������
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward cancelShLast(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        BjhdShForm bjhdShForm = (BjhdShForm) form;
        // ���һ������
        boolean isSuccess = service.cancelShLast(bjhdShForm);
        String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }


}
