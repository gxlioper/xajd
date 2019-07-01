package com.zfsoft.xgxt.qgzx.wycjqgzx;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.qgzx.xsgw.WdgwsqService;
import com.zfsoft.xgxt.rcsw.qjgl.qjjg.QjjgService;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @类功能描述:我要参加勤工助学
 * @作者： lgx [工号:1553]
 * @时间： 2018-07-06 10:53
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class WycjqgzxAction extends SuperAction<WycjqgzxForm,WycjqgzxService>{
    private WycjqgzxService service = new WycjqgzxService();
    private static final String url = "qgzx_gwglnew_wycjqgzx.do";

    @SystemAuth(url = url)
    public ActionForward wycjqgzxManage(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        User user = getUser(request);
        WdgwsqService wservice = new WdgwsqService();
        String xh = "";
        if("stu".equals(user.getUserType()))
            xh=user.getUserName();
        /*学生课余时间*/
        List<HashMap<String, String>> qgxmList= wservice.getQgmxList(xh);
        request.setAttribute("qgxmList", new QjjgService().getQjxmList());//和日常事务当中的课程时间一致
        request.setAttribute("qgmxList", qgxmList);
        request.setAttribute("qgxmSize", qgxmList.size());
        WycjqgzxForm model = new WycjqgzxForm();
        model.setXh(xh);
        model = service.getModel(model);
        if(model != null && model.getDgzt() != null){
            request.setAttribute("dgzt", model.getDgzt());
        }
        boolean sfxg = service.checkSfxg(xh);//检查是否可以修改，当学生有审核中或审核通过的数据则不允许修改
        request.setAttribute("sfxg", sfxg?"yes":"no");
        request.setAttribute("path", "qgzx_gwglnew_wycjqgzx.do");
        FormModleCommon.commonRequestSet(request);
        System.out.println(sfxg?"yes":"no");
        return mapping.findForward("wycjqgzxManage");
    }
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward save(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception{
        User user = getUser(request);

        String xh = "";
        if("stu".equals(user.getUserType()))
            xh=user.getUserName();
        boolean result = service.saveDgxs(xh);
        String[] mxrq = request.getParameterValues("mxrq");
        List<String[]> mxxmList = new ArrayList<String[]>();

        if (mxrq != null && mxrq.length > 0){
            for (int i = 0 ; i < mxrq.length ; i++){
                String[] mxxm = request.getParameterValues("mxxm"+i);
                mxxmList.add(mxxm);
            }
        }
        if(null!=mxrq&&mxrq.length!=0){
            service.saveKysj(xh, mxrq, mxxmList);
        }
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
                : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     * @描述:取消报名
     * @作者：lgx [工号：1553]
     * @日期：2018/7/6 16:41
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward qxbm(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws Exception{
        User user = getUser(request);

        String xh = "";
        if("stu".equals(user.getUserType()))
            xh=user.getUserName();
        WycjqgzxForm model = new WycjqgzxForm();
        model.setXh(xh);
        model.setDgzt("0");
        boolean result = service.runUpdate(model);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
                : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }
}
