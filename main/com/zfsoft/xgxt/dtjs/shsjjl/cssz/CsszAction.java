package com.zfsoft.xgxt.dtjs.shsjjl.cssz;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.OptionUtil;
import common.newp.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2019-03-01 09:20
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class CsszAction extends SuperAction<CsszForm,CsszService> {
    private static final String KGZT_CLOSE = "0";

    private static final String url = "stgl_shsjjl_jcsz.do";

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward cssz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CsszForm myForm = (CsszForm) form;
        CsszService service = new CsszService();
        CsszForm model = service.getModel();
        if (model != null) {
            if (StringUtil.isNull(model.getSqkg())) {
                model.setSqkg(KGZT_CLOSE);
            }
            BeanUtils.copyProperties(myForm, model);
        } else {
            myForm.setSqkg(KGZT_CLOSE);
        }
        XtwhShlcService shlcService = new XtwhShlcService();
        List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("dtjs");// ������������������б��ȡֵͨ�÷���
        request.setAttribute("shlcList", shlc);
        List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// �����ر�
        request.setAttribute("onoffList", onoffList);
        request.setAttribute("path", "stgl_shsjjl_jcsz.do");
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("cssz");
    }
    /**
     * �������ñ���
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CsszForm model = (CsszForm) form;
        CsszService service = new CsszService();
        boolean result = false;
        service.deleteJcsz();
        result = service.runInsert(model);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
                : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }
}
