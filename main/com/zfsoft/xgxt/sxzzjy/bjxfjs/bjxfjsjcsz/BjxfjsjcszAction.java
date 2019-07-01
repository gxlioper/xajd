package com.zfsoft.xgxt.sxzzjy.bjxfjs.bjxfjsjcsz;

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
 * @�๦������:�༶ѧ�罨���������
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-06-20 16:08
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class BjxfjsjcszAction extends SuperAction<BjxfjsjcszForm,BjxfjsjcszService> {
    private static final String KGZT_CLOSE = "0";
    private static final String url = "sxzzjy_bjxfjs_jcsz.do";
    private BjxfjsjcszService service = new BjxfjsjcszService();



    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward bjxfjsJcsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BjxfjsjcszForm myForm = (BjxfjsjcszForm) form;

        List<HashMap<String,String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);//�����ر�
        request.setAttribute("onoffList", onoffList);
        //��������б�
        XtwhShlcService shlcService = new XtwhShlcService();
        List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("sxzzjy");
        request.setAttribute("shlcList", shlc);
        String path = "sxzzjy_bjxfjs_jcsz.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        BjxfjsjcszForm model = service.getModel();
        if (model != null){
            BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
        }
        return mapping.findForward("bjxfjsJcsz");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward saveJcsz(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BjxfjsjcszForm myForm = (BjxfjsjcszForm) form;
        boolean result = service.saveJcsz(myForm);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

}
