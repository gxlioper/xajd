package com.zfsoft.xgxt.dycjgl.jcsz;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.OptionUtil;
import common.newp.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.utils.FormModleCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

public class JcszAction extends SuperAction<JcszForm,JcszService> {
    private static final String KGZT_CLOSE = "0";
    /**
     * @����:ת����������ҳ��
     * @���ߣ�xuwen[���ţ�1426]
     * @���ڣ�2017��5��4�� ����11:03:50
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward ��������
     * @throws
     */
    public ActionForward dycjglJcsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        JcszForm jcszForm = (JcszForm) form;
        JcszService jcszService = new JcszService();

        JcszForm model = jcszService.getModel();
        if (model != null) {
            if (StringUtil.isNull(model.getKqkg())) {
                model.setKqkg(KGZT_CLOSE);
            }
            BeanUtils.copyProperties(jcszForm, model);
        } else {
            jcszForm.setKqkg(KGZT_CLOSE);
        }
        List<HashMap<String,String>> onOff = new OptionUtil().getOptions(OptionUtil.ONOFF);
        request.setAttribute("onOff", onOff);
        request.setAttribute("path", "xsxx_dyxj_cssz.do");
        request.setAttribute("xnList", Base.getXnndList());
        request.setAttribute("xqList", Base.getXqList());

        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("dycjglJcsz");
    }
}
