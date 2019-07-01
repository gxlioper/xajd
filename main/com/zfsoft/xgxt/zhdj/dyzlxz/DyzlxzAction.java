package com.zfsoft.xgxt.zhdj.dyzlxz;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.zhdj.djyj.DyslyjService;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

public class DyzlxzAction extends SuperAction<DyzlxzForm,DyzlxzService> {
    private DyzlxzService  service = new  DyzlxzService();
    private static final String url = "zhdj_dzdy_dyzlxz.do";


    public ActionForward getDyzlList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        DyzlxzForm model = (DyzlxzForm) form;
        User user = getUser(request);
        HashMap<String, String> rs = service.getUserZzmm(user);
        if(rs != null )
        {
            String zzmm = rs.get("zzmm");
            if("01".equals(zzmm) || "02".equals(zzmm) )//��Ա��Ԥ����Ա
            {
                if (QUERY.equalsIgnoreCase(model.getType())) {
                    // ��ѯ
                    List<HashMap<String, String>> resultList = service.getPageList(model, user);
                    JSONArray dataList = JSONArray.fromObject(resultList);
                    response.getWriter().print(dataList);
                    return null;
                }
                //���û�����û�ҳ��
                request.setAttribute("userType", user.getUserStatus());
                FormModleCommon.commonRequestSet(request);
                return mapping.findForward("search");
            }
            else{
                String msg = "��ģ��ֻ����ѧ����Ա�û����ʣ���ȷ�ϣ�";
                request.setAttribute("yhInfo", msg);
                return new ActionForward("/yhInfo.do", false);
            }

        }
        else{
            String msg = "��ģ��ֻ����ѧ����Ա�û����ʣ���ȷ�ϣ�";
            request.setAttribute("yhInfo", msg);
            return new ActionForward("/yhInfo.do", false);
        }

    }
}
