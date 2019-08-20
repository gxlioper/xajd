package com.zfsoft.xgxt.hdgl.bmdwh;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by llf on 2019/8/20.
 */
public class BmdAction extends SuperAction<BmdForm,BmdService> {
    private static final String url = "hdgl_hdgl_bmdwh.do";
    private BmdService service = new BmdService();

    @SystemAuth(url = url)
    public ActionForward bmdList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception{
        BmdForm model = (BmdForm)form;
        if(QUERY.equalsIgnoreCase(model.getType())){
            User user = getUser(request);
            // ≤È—Ø
            List<HashMap<String, String>> resultList = service.getPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        String path = "hdgl_hdgl_bmdwh.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("bmdList");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward addBmd(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws Exception{
        BmdForm model = (BmdForm)form;
        if(SAVE.equalsIgnoreCase(model.getType())){
            String md5 = model.getPassword()+model.getIp();
            for(int i=0;i<3;i++){
                md5 = DigestUtils.md5Hex(md5)+model.getIp();
            }
            model.setPassword(md5);
            boolean result = service.runInsert(model);
            String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
                    : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
            return null;
        }
        return mapping.findForward("addBmd");
    }
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward updateBmd(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception{
        BmdForm model = (BmdForm)form;
        if(UPDATE.equalsIgnoreCase(model.getType())){
            boolean result = service.runUpdate(model);
            String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
                    : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
            return null;
        }
        model = service.getModel(model);
        request.setAttribute("model",model);
        return mapping.findForward("updateBmd");
    }
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward delBmd(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception{
        String values = request.getParameter("values");
        if (!StringUtil.isNull(values)){
            String[] mess = service.delBmd(values.split(","));
            if(null==mess||mess.length!=1){
                String message= MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
                response.getWriter().print(getJsonMessage(message));
                return null;
            }
            Map<String, String> map = new HashMap<String, String>();
            map.put("num",mess[0]);
            JSONObject json = JSONObject.fromObject(map);
            response.getWriter().print(json);
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }
}
