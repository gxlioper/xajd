package com.zfsoft.xgxt.sxzzjygl.bjhdgl;

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
 *班级活动基础设置
 */
public class BjhdjcszAction extends SuperAction<BjhdjcszForm,BjhdjcszService> {

    private static final String KGZT_CLOSE = "0";
    private static final String url = "bjhdgl_jcsz.do?method=bjhdJcsz";

    /**
     * @描述:转到基础设置页面
     * @作者：xuwen[工号：1426]
     * @日期：2017年5月4日 上午11:03:50
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward 返回类型
     * @throws
     */

    public ActionForward bjhdJcsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BjhdjcszForm bjhdjcszForm = (BjhdjcszForm) form;
        BjhdjcszService bjhdjcszService = new BjhdjcszService();

        BjhdjcszForm model = bjhdjcszService.getModel();
        if (model != null) {
            if (StringUtil.isNull(model.getSqkg())) {
                model.setSqkg(KGZT_CLOSE);
            }
            BeanUtils.copyProperties(bjhdjcszForm, model);
        } else {
            bjhdjcszForm.setSqkg(KGZT_CLOSE);
        }

        XtwhShlcService shlcService = new XtwhShlcService();
        List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("sxzzjy");// 基本设置中审核流程列表的取值通用方法
        request.setAttribute("shlcList", shlc);
        List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// 开启关闭
        request.setAttribute("onoffList", onoffList);
        request.setAttribute("path",url);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("bjhdJcsz");
    }
}
