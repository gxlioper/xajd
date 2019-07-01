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
 * @�๦������:��Ҫ�μ��ڹ���ѧ
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-07-06 10:53
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
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
        /*ѧ������ʱ��*/
        List<HashMap<String, String>> qgxmList= wservice.getQgmxList(xh);
        request.setAttribute("qgxmList", new QjjgService().getQjxmList());//���ճ������еĿγ�ʱ��һ��
        request.setAttribute("qgmxList", qgxmList);
        request.setAttribute("qgxmSize", qgxmList.size());
        WycjqgzxForm model = new WycjqgzxForm();
        model.setXh(xh);
        model = service.getModel(model);
        if(model != null && model.getDgzt() != null){
            request.setAttribute("dgzt", model.getDgzt());
        }
        boolean sfxg = service.checkSfxg(xh);//����Ƿ�����޸ģ���ѧ��������л����ͨ���������������޸�
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
     * @����:ȡ������
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/7/6 16:41
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
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
