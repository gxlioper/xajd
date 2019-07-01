package com.zfsoft.xgxt.szdw.thjl.jcsz;

import com.zfsoft.xgxt.base.action.SuperAction;
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
 * @描述：TODO
 * @作者：1601
 * @日期：
 */
public class JcszAction extends SuperAction<JcszForm,JcszService> {
    private static final String KGZT_CLOSE = "0";
    private static final String url = "szdw_thjl_jcszwh.do?method=jcsz";

    public ActionForward jcsz(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws Exception {
        JcszForm jcszForm = (JcszForm) form;
        JcszService jcszService = new JcszService();

        JcszForm model = jcszService.getModel();
        if (model != null) {
            if (StringUtil.isNull(model.getSqkg())) {
                model.setSqkg(KGZT_CLOSE);
            }
            BeanUtils.copyProperties(jcszForm, model);
        } else {
            jcszForm.setSqkg(KGZT_CLOSE);
        }

        XtwhShlcService shlcService = new XtwhShlcService();
        List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("szdw");// 基本设置中审核流程列表的取值通用方法
        request.setAttribute("shlcList", shlc);
        List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// 开启关闭
        request.setAttribute("onoffList", onoffList);
        request.setAttribute("path",url);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("jcsz");
    }
}
