package com.zfsoft.xgxt.xlzx.zxxzwh;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.base.DealString;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:
 * @作者： lgx [工号:1553]
 * @时间： 2018-12-26 11:02
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZxxzwhAction extends SuperAction<ZxxzwhForm,ZxxzwhService> {
    private static final String url = "xlzx_jcsz_xlzxxzwh.do";

    private static ZxxzwhService service = new ZxxzwhService();

    @SystemAuth(url = url)
    public ActionForward zxxzwh(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZxxzwhForm myForm = (ZxxzwhForm) form;
        ZxxzwhForm t = service.getModel();
        if(t != null){
            BeanUtils.copyProperties(myForm,t);
            request.setAttribute("editorid", t.getZxxz());
        }
        request.setAttribute("path", "xlzx_jcsz_xlzxxzwh.do");
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("zxxzwh");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ZxxzwhForm myForm = (ZxxzwhForm) form;
        String content = DealString.toGBK(request.getParameter("editorid"));
        myForm.setZxxz(content);
        User user = getUser(request);
        boolean result = service.save(myForm);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
                : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

}
