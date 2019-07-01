package com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjsjcsz;

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
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-06-26 09:09
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class GrxfjsjcszAction extends SuperAction<GrxfjsjcszForm,GrxfjsjcszService> {
    private static final String KGZT_CLOSE = "0";
    private static final String url = "sxzzjy_grxfjs_jcsz.do";
    private GrxfjsjcszService service = new GrxfjsjcszService();



    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward grxfjsJcsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        GrxfjsjcszForm myForm = (GrxfjsjcszForm) form;

        List<HashMap<String,String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);//开启关闭
        request.setAttribute("onoffList", onoffList);
        //审核流程列表
        XtwhShlcService shlcService = new XtwhShlcService();
        List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("sxzzjy");
        request.setAttribute("shlcList", shlc);
        String path = "sxzzjy_grxfjs_jcsz.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        GrxfjsjcszForm model = service.getModel();
        if (model != null){
            BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
        }
        return mapping.findForward("grxfjsJcsz");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward saveJcsz(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrxfjsjcszForm myForm = (GrxfjsjcszForm) form;
        boolean result = service.saveJcsz(myForm);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }
}
