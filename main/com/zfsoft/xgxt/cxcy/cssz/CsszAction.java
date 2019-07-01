package com.zfsoft.xgxt.cxcy.cssz;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.OptionUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:创新创业参数设置
 * @作者： lgx [工号:1553]
 * @时间： 2018-09-05 14:18
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class CsszAction extends SuperAction<CsszForm,CsszService> {
    private static final String KGZT_CLOSE = "0";
    private static final String url = "cxcy_jcsz_cssz.do";
    private CsszService service = new CsszService();

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward cssz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CsszForm myForm = (CsszForm) form;

        List<HashMap<String,String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);//开启关闭
        request.setAttribute("onoffList", onoffList);
        //审核流程列表
        XtwhShlcService shlcService = new XtwhShlcService();
        List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("cxcy");
        request.setAttribute("shlcList", shlc);
        String path = "cxcy_jcsz_cssz.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        CsszForm model = service.getModel();
        if (model != null){
            BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
        }
        return mapping.findForward("cssz");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward saveCcsz(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CsszForm myForm = (CsszForm) form;
        boolean result = service.saveCcsz(myForm);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }
}
