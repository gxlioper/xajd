package com.zfsoft.xgxt.sxzzjygl.ztbhgl;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.xsxx.zyfwgl.zyfwjcsz.ZyfwJcszForm;
import com.zfsoft.xgxt.xsxx.zyfwgl.zyfwjcsz.ZyfwJcszService;
import com.zfsoft.xgxt.zhdj.djyj.ZbrsyjService;
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
 * �������������
 */
public class ZtbhjcszAction extends SuperAction<ZtbhjcszForm,ZtbhjcszService> {
    private static final String KGZT_CLOSE = "0";
    private static final String url = "ztbhgl_ztbhjcsz.do?method=ztbhJcsz";

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

    public ActionForward ztbhJcsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ZtbhjcszForm ztbhjcszForm = (ZtbhjcszForm) form;
        ZtbhjcszService ztbhjcszService = new ZtbhjcszService();

        ZtbhjcszForm model = ztbhjcszService.getModel();
        if (model != null) {
            if (StringUtil.isNull(model.getSqkg())) {
                model.setSqkg(KGZT_CLOSE);
            }
            BeanUtils.copyProperties(ztbhjcszForm, model);
        } else {
            ztbhjcszForm.setSqkg(KGZT_CLOSE);
        }

        XtwhShlcService shlcService = new XtwhShlcService();
        List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("sxzzjy");// ������������������б��ȡֵͨ�÷���
        request.setAttribute("shlcList", shlc);
        List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// �����ر�
        request.setAttribute("onoffList", onoffList);
        request.setAttribute("path",url);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("ztbhJcsz");
    }
}
