package com.zfsoft.xgxt.zhdj.xsdzbhdygl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import Freeze.Map;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;

public class DzbzwwhAction extends SuperAction<DzbzwwhForm,DzbzwwhService>{
    private DzbzwwhService service = new DzbzwwhService();
    private static final String url = "zhdj_dzdy_zwwh.do";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    
    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception ActionForward ��������
     * @throws
     * @����: ��ȡְ��ά���б�
     * @���ߣ�������
     * @���ڣ�2018-12-25 ����16:15:56
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     */

    public ActionForward getZwwhList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        DzbzwwhForm model = (DzbzwwhForm) form;
        User user = getUser(request);
        if (QUERY.equalsIgnoreCase(model.getType())) {
            // ��ѯ
            List<HashMap<String, String>> resultList = service.getPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        //���û�����û�ҳ��
        request.setAttribute("userType", user.getUserStatus());
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("search");
    }
    
    /**
     * ��תְ�����ҳ��
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward addZw(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        return mapping.findForward("addPage");
    }
    
    /**
     * ����ְ�� ����
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward saveDm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        DzbzwwhForm model = (DzbzwwhForm) form;
        boolean rs = service.saveDm(model);
        String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }
    
    /**
     * ��תְ������޸�ҳ��
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {

    	DzbzwwhForm model = (DzbzwwhForm) form;
    	DzbzwwhForm myForm = service.getModel(model);
        List<HashMap<String, String>> zwList = service.getZwList(myForm);
        if (myForm!= null) {
            BeanUtils.copyProperties(model, myForm);

        } else {
            request.setAttribute("message", "��ǰѡ���޸ĵĴ��뱻�����û�ɾ����");
            return mapping.findForward("error");
        }
        request.setAttribute("dm",zwList.get(0).get("dm"));
        request.setAttribute("mc",zwList.get(0).get("mc"));
        request.setAttribute("zwss",zwList.get(0).get("zwss"));
        request.setAttribute("zwlx",zwList.get(0).get("zwlx"));
        return mapping.findForward("update");
    }

    /**
     * �޸�ְ�����
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateSavaDm(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
    	DzbzwwhForm model = (DzbzwwhForm) form;
        boolean rs = service.updateSava(model);
        String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    
    /**
     * ɾ��ְ��ά������
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward delZw(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                               HttpServletResponse response) throws Exception {

        DzbzwwhForm model = (DzbzwwhForm) form;
        boolean rs = service.delZw(model);
        String messageKey = rs ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

}
