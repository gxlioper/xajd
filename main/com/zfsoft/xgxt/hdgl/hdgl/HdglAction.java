package com.zfsoft.xgxt.hdgl.hdgl;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.hdgl.hdxx.HdxxForm;
import com.zfsoft.xgxt.hdgl.hdxx.HdxxService;
import net.sf.json.JSONArray;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

/**
 * �����action.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2018-02-05 09:40
 */
public class HdglAction extends SuperAction<HdxxForm, HdxxService> {

    private HdxxService hdxxService = new HdxxService();
    private static final String url = "hdgl_hdgl_hdgl.do";

    /**
     *  �����list.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-02-05 09:47
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    @SystemAuth(url = url)
    public ActionForward hdglList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HdxxForm model = (HdxxForm) form;
        if (QUERY.equalsIgnoreCase(model.getType())) {
            User user = getUser(request);
            //���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            // ��ѯ
            List<HashMap<String, String>> resultList = hdxxService.getHdglList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path", url);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("hdglList");
    }

    /**
     *  �����view.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-02-06 11:13
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward hdglView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HdxxForm model = (HdxxForm) form;
        HashMap<String,String> hdxxMap = hdxxService.getHdxx(model);
        request.setAttribute("hdxx",hdxxMap);
        return mapping.findForward("hdglView");
    }

    /**
     *  ��¼�.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-02-06 15:17
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward hdxj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HdxxForm model = (HdxxForm) form;
        boolean result = hdxxService.hdxjById(model.getHdid());
        response.getWriter().print(getJsonResult(MessageKey.SYS_OPERATE_FAIL,result));
        return null;
    }

    /**
     *  ר���ų�Ա����.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-02-07 11:07
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward zjtcysz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HdxxForm model = (HdxxForm) form;
        String[] ids = model.getIds();
        String sffp = request.getParameter("sffp");
        if (QUERY.equalsIgnoreCase(model.getType())) {
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            List<HashMap<String, String>> resultList = hdxxService.getZjtcyList(model,sffp);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        String path = "hdgl_hdgl.do?method=zjtcysz";
        request.setAttribute("path", path);
        request.setAttribute("ids",ids);
        request.setAttribute("isShow",ids.length==1);
        /*���׶�id*/
        request.setAttribute("jdid", request.getParameter("jdid"));
        return mapping.findForward("zjtcysz");
    }

    /**
     *  ר���ų�Ա���ã��������.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-02-08 09:15
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward zjtcyFp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HdxxForm model = (HdxxForm) form;
        boolean rs = hdxxService.zjtcyFp(model);
        String key = rs ? MessageKey.SYS_FP_SUCCESS :MessageKey.SYS_FP_FAIL;
        response.getWriter().print(getJsonMessageByKey(key));
        return null;
    }

    /**
     *  ר���ų�Ա���ã�ȡ������.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-02-08 09:14
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward zjtcyQxfp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        HdxxForm model = (HdxxForm) form;
        boolean rs = hdxxService.zjtcyQxfp(model);
        String key = rs ? MessageKey.SYS_QXCP_SUCCESS :MessageKey.SYS_QXCP_FAIL;
        response.getWriter().print(getJsonMessageByKey(key));
        return null;
    }
    
    /**
     * @����: �׶γ�Ա����
     * @���ߣ� MengWei[���ţ�1186]
     * @���ڣ� 2018-6-11 ����04:51:52
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     * ActionForward �������� 
     * @throws
     */
    public ActionForward jdcysz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception{
    	
    	/*��ȡǰ̨���صĻid*/
    	String hdid = request.getParameter("hdid");
    	
    	/*���ݻId��ȡ���Ϣ*/
    	HashMap<String,String> hdxx = hdxxService.getHdxxByHdid(hdid);
    	request.setAttribute("hdxx", hdxx);
    	
    	/*�׶γ�Ա��Ϣ*/
         List<HashMap<String, String>> jdcyxxList = hdxxService.getJdcyxxList(hdid);
    	request.setAttribute("jdcyxxList", jdcyxxList);
    	return mapping.findForward("jdcysz");
    }
}
