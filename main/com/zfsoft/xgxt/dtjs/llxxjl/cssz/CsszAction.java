package com.zfsoft.xgxt.dtjs.llxxjl.cssz;

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
 * @类功能描述:
 * @作者： llf [工号:1754]
 * @时间： 2019-07-12 15:19
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class CsszAction extends SuperAction<CsszForm, CsszService> {
    private static final String KGZT_CLOSE = "0";

    private static final String url = "dtjs_llxxjl_cssz.do";

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
        List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("dtjs");// 基本设置中审核流程列表的取值通用方法
        request.setAttribute("shlcList", shlc);
        List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// 开启关闭
        request.setAttribute("onoffList", onoffList);
        request.setAttribute("path", "dtjs_llxxjl_cssz.do");
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("cssz");
    }
    /**
     * 参数设置保存
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
