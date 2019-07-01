package com.zfsoft.xgxt.cxcy.bzsbwh.bzsbwhsh;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.cxcy.bzsbwh.bzsbwhsq.BzsbwhsqService;
import com.zfsoft.xgxt.cxcy.cssz.CsszForm;
import com.zfsoft.xgxt.cxcy.cssz.CsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @�๦������:���´�ҵ�����걨���
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-09-06 10:39
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class BzsbwhshAction extends SuperAction<BzsbwhshForm,BzsbwhshService> {
    private static final String url = "cxcy_bzsbwh_sh.do";
    private BzsbwhshService service = new BzsbwhshService();
    private static final String CXCY = "cxcy";
    private static List<HashMap<String, String>> jbxxList = null;
    static{
        BdpzService bdpzService = new BdpzService();
		/*ѧ��������Ϣ��ʾ����*/
        jbxxList = bdpzService.getJbxxpz(CXCY);
    }

    public ActionForward getList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BzsbwhshForm model = (BzsbwhshForm)form;
        if (QUERY.equalsIgnoreCase(model.getType())) {
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // ��ѯ
            List<HashMap<String, String>> resultList = service.getPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        CsszForm ccszModel =new CsszService().getModel();
        request.setAttribute("ccszModel",ccszModel);
        SearchModel searchModel=new SearchModel();
        searchModel.setSearch_tj_xn(new String[] { Base.currXn });
        searchModel.setSearch_tj_xq(new String[] { Base.currXq });
        request.setAttribute("searchTj", searchModel);
        request.setAttribute("path", url);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("bzsbwhshList");
    }
    
    public ActionForward bzsbwhDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BzsbwhshForm myForm = (BzsbwhshForm)form;
		HashMap<String,String> map = new BzsbwhsqService().getInfoById(myForm.getSqid());
		if(map != null){
			BeanUtils.copyProperties(myForm, map);
		}
		if (!StringUtil.isNull(myForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CXCY);
		request.setAttribute("jbxxList", jbxxList);
		String path = "cxcy_bzsbwhsh.do?method=bzsbwhDgsh";
		request.setAttribute("xqmc",Base.getXqmcForXqdm(myForm.getXq()));
		request.setAttribute("path", path);
        request.setAttribute("map", myForm);
        request.setAttribute("tbrmc", map.get("tbrmc"));
		return mapping.findForward("bzsbwhDgsh");
	}
	
	
    public ActionForward saveDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        BzsbwhshForm model = (BzsbwhshForm) form;
        User user = getUser(request);
        // ���浥�����
        boolean result = service.saveSh(model, user);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     * @����:�������
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/9/6 16:28
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward bzsbwhPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        return mapping.findForward("bzsbwhPlsh");
    }

    /**
     * @����:������˱���
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/9/6 11:44
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward savePlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        BzsbwhshForm model = (BzsbwhshForm) form;
        User user = getUser(request);
        String message = service.savePlsh(model, user);
        response.getWriter().print(getJsonMessage(message));
        return null;
    }

    /**
     * @����:����
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/9/6 11:45
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward cancelSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        BzsbwhshForm model = (BzsbwhshForm) form;
        String id = request.getParameter("sqid");
        String shzt = request.getParameter("shzt");
        model.setShzt(shzt);
        model.setSqid(id);
        // ���һ������
        boolean isSuccess = service.cancel(model);
        String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     * @����:����
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/9/6 11:45
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        BzsbwhshForm model =new BzsbwhshForm();
        String shid = request.getParameter("shid");
        String splc = request.getParameter("shlc");
        model.setShlc(splc);
        model.setShid(shid);
        User user = getUser(request);
        HashMap<String,String> shxx = ShlcUtil.getShxx(shid);
        String cancelFlg = service.cxshnew(shxx.get("ywid"),model,user);
        // ��˳����ɹ�
        String messageKey = Constants.CANCLE_FLG_SUCCESS.equals(cancelFlg)
                || Constants.CANCLE_FLG_SUCCESS_ZHYJ.equals(cancelFlg) ? MessageKey.SYS_CANCEL_SUCCESS
                : MessageKey.SYS_CANCEL_FAIL;
        Map<String, String> map = new HashMap<String, String>();
        map.put("message", MessageUtil.getText(messageKey));
        map.put("cancelFlg", cancelFlg);
        response.getWriter().print(JSONObject.fromObject(map));
        return null;
    } 
    
}
