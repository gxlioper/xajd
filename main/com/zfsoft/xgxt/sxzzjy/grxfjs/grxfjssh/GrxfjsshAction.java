package com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjssh;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjshb.GrxfjshbForm;
import com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjshb.GrxfjshbService;
import com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjssq.GrxfjssqForm;
import com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjssq.GrxfjssqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
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
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-06-26 15:41
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class GrxfjsshAction extends SuperAction<GrxfjsshForm,GrxfjsshService>{
    private  GrxfjsshService service = new GrxfjsshService();
    private static final String url = "sxzzjy_grxfjs_grxfjssh.do";
    private static final String GRXFJS = "grxfjs";
    private static List<HashMap<String, String>> jbxxList = null;
    static {
        BdpzService bdpzService = new BdpzService();
        // ѧ��������Ϣ��ʾ����
        jbxxList = bdpzService.getJbxxpz(GRXFJS);
    }

    @SystemAuth(url = url)
    public ActionForward getList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrxfjsshForm model = (GrxfjsshForm) form;
        User user = getUser(request);
        if (QUERY.equalsIgnoreCase(model.getType())){
            //���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            //��ѯ�������
            List<HashMap<String,String>> resultList = service.getPageList(model,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }

        SearchModel searchModel=new SearchModel();
        searchModel.setSearch_tj_nd(new String[] {Base.currNd});
        searchModel.setSearch_tj_xn(new String[] {Base.currXn});
        request.setAttribute("searchTj", searchModel);
        String path = "sxzzjy_grxfjs_grxfjssh.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("grxfjsshList");
    }

    public ActionForward grxfjsSh(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrxfjsshForm model = (GrxfjsshForm) form;
        if (SAVE.equalsIgnoreCase(model.getType())){
            String num = service.checkExistForSave(model);
            if("0".equals(num)) {
                User user = getUser(request);
                boolean result = service.saveSh(model,user);
                String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
                response.getWriter().print(getJsonMessageByKey(messageKey));
                return null;
            }else {
                response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_YLBX_YLBXSH));
                return null;
            }

        }
        String sqlx = model.getSqlx();
        if("sq".equals(sqlx)){
            GrxfjssqService sqService = new GrxfjssqService();
            GrxfjssqForm sqForm = new GrxfjssqForm();
            sqForm.setSqid(model.getSqid());
            HashMap<String,String> map = sqService.getGrxfjssqInfo(sqForm);
            request.setAttribute("map",map);
        }else{
            GrxfjshbService grxfjshbService = new GrxfjshbService();
            GrxfjshbForm grxfjshbForm = grxfjshbService.getModel(model.getSqid());
            request.setAttribute("map",grxfjshbForm);
        }
        if (!StringUtil.isNull(model.getXh())) {
            // ����ѧ��������Ϣ
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        //ѧ��������Ϣ��ʾ����
        request.setAttribute("jbxxList", jbxxList);
        request.setAttribute("sqlx", sqlx);
        return mapping.findForward("grxfjsSh");
    }

    public ActionForward cancelgrxfjssh(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrxfjsshForm model = (GrxfjsshForm) form;
        User user = getUser(request);
        String sqid = request.getParameter("sqid");
        model.setSqid(sqid);
        boolean isSuccess = service.newCancelSh(model, user);
        String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    public ActionForward grxfjsPlsh(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrxfjsshForm model = (GrxfjsshForm) form;

        User user = getUser(request);
        if("SAVE".equalsIgnoreCase(model.getType())){
            String message = service.savePlsh(model, user);
            response.getWriter().print(getJsonMessage(message));
            return null;
        }
        return mapping.findForward("grxfjsPlsh");
    }
}
