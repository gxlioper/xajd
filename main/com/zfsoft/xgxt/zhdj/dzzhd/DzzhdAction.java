package com.zfsoft.xgxt.zhdj.dzzhd;

import com.alibaba.fastjson.JSON;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import net.sf.json.JSONArray;
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
import java.util.Map;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���Ž���-����֯�������-����֯�����
 * @�๦������: ���Ž�������
 * @����: ��ˬ [����:1730]
 * @ʱ��: 2019/7/19 16:02
 */
public class DzzhdAction extends SuperAction<DzzhdForm,DzzhdService> {

    private DzzhdService dzzhdService = DzzhdService.getDzzhdService();
    private static final String hdfbUrl = "zhdj_dzzhd_hdfb.do";

    /**
     * @����: ������б��ȡ
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/19 16:33
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param mapping
	* @param form
	* @param request
	* @param response
     * @return org.apache.struts.action.ActionForward
     **/
    @SystemAuth(url=hdfbUrl,rewritable = SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward getFbPageList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        DzzhdForm model = (DzzhdForm) form;
        String doType = request.getParameter("doType");
        User user = getUser(request);
        if(QUERY.equals(doType)){
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            List<HashMap<String, String>> resultList = dzzhdService.getFbPageList(model,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path", "zhdj_dzzhd_hdfb.do");
        request.setAttribute("userType",user.getUserType());
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("getFbPageList");
    }

    /**
     * @����: �����б�
     * @����: ��ˬ[����:1730]
     * @����: 2019/8/1 15:29
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param mapping
	* @param form
	* @param request
	* @param response 
     * @return org.apache.struts.action.ActionForward
     **/
    @SystemAuth(url=hdfbUrl,rewritable = SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward getHdPageList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        DzzhdForm model = (DzzhdForm) form;
        String doType = request.getParameter("doType");
        User user = getUser(request);
        if(QUERY.equals(doType)){
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            List<HashMap<String, String>> resultList = dzzhdService.getFbPageList(model,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path", "zhdj_dzzhd_hdfb.do");
        request.setAttribute("userType",user.getUserType());
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("getHdPageList");
    }

    /**
     * @����: ��Ϣ�鿴
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/19 16:37
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param mapping
	* @param form
	* @param request
	* @param response
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward hdInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        DzzhdForm model = (DzzhdForm) form;
        User user = getUser(request);
        String hdId = model.getId();
        String status = request.getParameter("status");
        if(StringUtils.isNotNull(hdId)){
            //����id�������ȡ������Ϣ
            HashMap<String,String> data = dzzhdService.getById(hdId);
            request.setAttribute("data",data);
            if(QUERY.equals(status)){
                Map<String,Object> resultMap = new HashMap<>();
                resultMap.put("code",1);
                resultMap.put("msg","��ȡ�ɹ�!");
                resultMap.put("data",data);
                response.getWriter().print(JSON.parseObject(JSON.toJSONString(resultMap)));
                return null;
            }
        }
        request.setAttribute("status",status);
        request.setAttribute("mxdxList",dzzhdService.getZzmmList());
        return mapping.findForward("dzzhdEdit");
    }

    /**
     * @����: ���Ϣ����
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/22 8:49
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param mapping
	* @param form
	* @param request
	* @param response
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward hdSave(ActionMapping mapping,ActionForm form,HttpServletRequest request,
                                HttpServletResponse response) throws Exception{
        DzzhdForm model = (DzzhdForm) form;
        String hdnr = DealString.toGBK(model.getHdnr());
        model.setHdnr(hdnr);
        User user = getUser(request);
        HashMap<String,Object> data = dzzhdService.hdSave(model,user);
        response.getWriter().print(JSON.parseObject(JSON.toJSONString(data)));
        return null;
    }

    /**
     * @����: ���Ϣɾ��
     * @����: ��ˬ[����:1730]
     * @����: 2019/7/22 8:50
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param mapping
	* @param form
	* @param request
	* @param response
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward removeById(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        Map<String,Object> result = dzzhdService.removeById(id);
        response.getWriter().print(JSON.parseObject(JSON.toJSONString(result)));
        return null;
    }

    /**
     * @����: ���Ա�б�
     * @����: ��ˬ[����:1730]
     * @����: 2019/8/1 15:49
     * @�޸ļ�¼: �޸���-�޸�ʱ��-�޸�����
     * @param mapping
	* @param form
	* @param request
	* @param response
     * @return org.apache.struts.action.ActionForward
     **/
    public ActionForward getHdryList(ActionMapping mapping,ActionForm form,HttpServletRequest request,
                                     HttpServletResponse response) throws Exception{
        DzzhdForm myForm = (DzzhdForm) form;
        String joinStatus = request.getParameter("joinStatus");//����״̬ 0 ����� 1�����
        String hdid = request.getParameter("hdid");//�id
        myForm.setId(hdid);
        myForm.setJoinStatus(joinStatus);
        String doType = request.getParameter("doType");
        User user = getUser(request);
        if(QUERY.equals(doType)){
            //��ȡ���Ա��Ϣ�б�
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            myForm.setSearchModel(searchModel);
            List<HashMap<String,String>> xsxxList = dzzhdService.getHdRyxxList(myForm,user);
            JSONArray dataList = JSONArray.fromObject(xsxxList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path","zhdj_dzzhd_hdry.do");
        request.setAttribute("joinStatus",joinStatus);
        request.setAttribute("hdid",hdid);
        return mapping.findForward("hdryList");
    }

    //���Ա������� hdRySave
    public ActionForward hdRySave(ActionMapping mapping,ActionForm form,HttpServletRequest request,
                                  HttpServletResponse response) throws Exception{
        String hdid = request.getParameter("hdid");
        String xhs = request.getParameter("xhs");
        Map<String,Object> resultMap = new HashMap<>();
        if(StringUtils.isNull(hdid) || StringUtils.isNull(xhs)){
            resultMap.put("code",0);
            resultMap.put("msg","�ύ��Ϣ����!");
        }else{
            boolean flag = dzzhdService.batchAdd(hdid,xhs);
            if(flag){
                resultMap.put("code",1);
                resultMap.put("msg","��ӳɹ�!");
            }else{
                resultMap.put("code",0);
                resultMap.put("msg","���ʧ��!");
            }
        }
        response.getWriter().print(JSON.parseObject(JSON.toJSONString(resultMap)));
        return null;
    }
    //���Ա����ɾ�� hdRyRemove
    public ActionForm hdRyRemove(ActionMapping mapping,ActionForm form,HttpServletRequest request,
                                 HttpServletResponse response) throws Exception{
        String hdid = request.getParameter("hdid");
        String xhs = request.getParameter("xhs");
        Map<String,Object> resultMap = new HashMap<>();
        if(StringUtils.isNull(hdid) || StringUtils.isNull(xhs)){
            resultMap.put("code",0);
            resultMap.put("msg","�ύ��Ϣ����!");
        }else{
            boolean flag = dzzhdService.batchRemove(hdid,xhs);
            if(flag){
                resultMap.put("code",1);
                resultMap.put("msg","�Ƴ��ɹ�!");
            }else{
                resultMap.put("code",0);
                resultMap.put("msg","�Ƴ�ʧ��!");
            }
        }
        response.getWriter().print(JSON.parseObject(JSON.toJSONString(resultMap)));
        return null;
    }

    //��ĵ�
    public ActionForward hdXdInfo(ActionMapping mapping,ActionForm form,HttpServletRequest request,
                                  HttpServletResponse response) throws Exception{
        String hdid = request.getParameter("hdid");
        String xh = request.getParameter("xh");
        String status = request.getParameter("status");
        User user = getUser(request);
        if(StringUtils.isNull(xh) && "stu".equals(user.getUserType())){
            xh = user.getUserName();
        }
        //��ȡ���Ա��Ϣ
        HashMap<String,String> result = dzzhdService.getHdxdInfo(hdid,xh);
        if(SAVE.equals(status)){
            Map<String,Object> resultMap = new HashMap<>();
            String fj = request.getParameter("fj");
            String hdxd = DealString.toGBK(request.getParameter("hdxd"));
            HashMap<String,String> data = new HashMap<>();
            data.put("hdxd",hdxd);//��ĵ�
            data.put("fj",fj);//������Ϣ
            data.put("id",result.get("id"));//��ĵ�id
            data.put("tjsj", DateTranCnDate.timeStampToDate(System.currentTimeMillis()));//�ύʱ��
            boolean flag  = dzzhdService.saveHdxd(data);
            if(flag){
                resultMap.put("code",1);
                resultMap.put("msg","�ύ�ɹ�!");
            }else{
                resultMap.put("code",0);
                resultMap.put("msg","�ύʧ��!");
            }
            response.getWriter().print(JSON.parseObject(JSON.toJSONString(resultMap)));
            return null;
        }
        request.setAttribute("status",status);
        request.setAttribute("data",result);
        return mapping.findForward("hdXdEdit");
    }
}
