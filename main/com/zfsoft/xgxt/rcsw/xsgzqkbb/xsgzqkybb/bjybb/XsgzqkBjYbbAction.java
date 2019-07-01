package com.zfsoft.xgxt.rcsw.xsgzqkbb.xsgzqkybb.bjybb;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * ����ũҵ��ѧ
 * ѧ����������༶�±���action.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2018-04-13 15:14
 */
public class XsgzqkBjYbbAction extends SuperAction<XsgzqkBjYbbForm, XsgzqkBjYbbService> {

    private XsgzqkBjYbbService service = new XsgzqkBjYbbService();
    private static final String url = "rcsw_xsgzqkbb_xyybb.do";

    /**
     *  ת���༶�±����б�ҳ�棨ѧԺ�������д��.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-24 15:35
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    @SystemAuth(url = url)
    public ActionForward bjYbbList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkBjYbbForm xsgzqkBjYbbForm = (XsgzqkBjYbbForm) form;
        request.setAttribute("path", url);
        request.setAttribute("czpath","rcsw_xsgzqkbb_bjybb.do");

        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("bjYbbList");
    }

    /**
     *  ��ȡ�༶�±����б�json����.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-17 15:25
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    @SystemAuth(url = url)
    public ActionForward getBjYbbListData(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkBjYbbForm xsgzqkBjYbbForm = (XsgzqkBjYbbForm) form;

        // ���ɸ߼���ѯ����
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        xsgzqkBjYbbForm.setSearchModel(searchModel);
        User user = getUser(request);
        // ��ѯ
        List<HashMap<String, String>> resultList = service.getPageList(xsgzqkBjYbbForm);
        JSONArray dataList = JSONArray.fromObject(resultList);
        response.getWriter().print(dataList);
        return null;
    }

    /**
     *  ת���༶�±�������ҳ��.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-18 16:09
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    @SystemAuth(url = url)
    public ActionForward bjYbbAdd(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkBjYbbForm xsgzqkBjYbbForm = (XsgzqkBjYbbForm) form;
        return mapping.findForward("bjYbbAdd");
    }

    /**
     *  ת���༶�±����޸�ҳ��.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-18 16:09
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward bjYbbEdit(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkBjYbbForm xsgzqkBjYbbForm = (XsgzqkBjYbbForm) form;
        User user = getUser(request);
        HashMap<String,String> bjYbb = service.getBjYbbById(xsgzqkBjYbbForm.getId());

        BeanUtils.copyProperties(xsgzqkBjYbbForm, bjYbb);

        return mapping.findForward("bjYbbEdit");
    }

    /**
     *  ת���༶�б�ҳ�棬��ѡ��.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-27 15:44
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward bjManage(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkBjYbbForm xsgzqkBjYbbForm = (XsgzqkBjYbbForm) form;
        SearchModel searchModel=new SearchModel();

        String xyybbid = xsgzqkBjYbbForm.getXyybbid();
        request.setAttribute("xyybbid",xyybbid);

        request.setAttribute("searchTj", searchModel);
        String path = "rcsw_xsgzqkbb_bjybbgl.do?method=bjManage";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("bjManage");
    }

    /**
     *  ��ȡ�༶�б�JSON����.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-27 15:48
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward getBjListData(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkBjYbbForm xsgzqkBjYbbForm = (XsgzqkBjYbbForm) form;
        User user = getUser(request);

        //��ѯ����ѧԺ����
        String xydm = service.getXydmByXyybbid(xsgzqkBjYbbForm.getXyybbid());
        xsgzqkBjYbbForm.setXydm(xydm);

        // ���ɸ߼���ѯ����
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        xsgzqkBjYbbForm.setSearchModel(searchModel);
        // ��ѯ
        List<HashMap<String, String>> resultList = service.getBjList(xsgzqkBjYbbForm, user);
        JSONArray dataList = JSONArray.fromObject(resultList);
        response.getWriter().print(dataList);
        return null;
    }

    /**
     *  �༶�±������ӵı���.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-18 16:10
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward bjYbbSaveForAdd(ActionMapping mapping, ActionForm form,
                                         HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkBjYbbForm xsgzqkBjYbbForm = (XsgzqkBjYbbForm) form;
        //�ظ�����֤
        if(service.isBjYfRepeat(xsgzqkBjYbbForm)){
            //�����ظ�
            String messageKey = MessageKey.RCSW_XSGZQKBB_YBB_BJYFREPEAT;
            response.getWriter().print(getJsonMessageByKey(messageKey));
        }else{
            //�������ظ�
            boolean result = service.runInsert(xsgzqkBjYbbForm);
            String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
        }
        return null;
    }

    /**
     *  �༶�±����޸ĵı���.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-18 16:10
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward bjYbbSaveForEdit(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkBjYbbForm xsgzqkBjYbbForm = (XsgzqkBjYbbForm) form;
        //�ظ�����֤
        if(service.isBjYfRepeat(xsgzqkBjYbbForm)){
            //�����ظ�
            String messageKey = MessageKey.RCSW_XSGZQKBB_YBB_BJYFREPEAT;
            response.getWriter().print(getJsonMessageByKey(messageKey));
        }else {
            //�������ظ�
            boolean result = service.runUpdate(xsgzqkBjYbbForm);
            String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
        }
        return null;
    }

    /**
     *  �༶�±�������ɾ����ɾ��ǰ���ж��Ƿ��а༶����¼�룩.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-19 17:18
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward bjYbbDel(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String values = request.getParameter("values");
        if (!StringUtil.isNull(values)){
            int num = service.runDelete(values.split(","));
            boolean result =  num > 0;
            String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num)
                    : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
            response.getWriter().print(getJsonMessage(message));
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }

    /**
     *  �༶�±���ͨ�õ���.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-19 19:31
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    @SystemAuth(url = url)
    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkBjYbbForm xsgzqkBjYbbForm = (XsgzqkBjYbbForm) form;

        //���ɸ߼���ѯ����
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        xsgzqkBjYbbForm.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String,String>> resultList = service.getAllList(xsgzqkBjYbbForm);

        //�������ܴ���
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = xsgzqkBjYbbForm.getExportModel();
        exportModel.setZgh(user.getUserName());//��ǰ����Ա
        exportModel.setDataList(resultList);//��������
        exportModel.setDcclbh(request.getParameter("dcglbh"));//���õ�ǰ�������ܱ��
        File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
        FileUtil.outputExcel(response, file);
        return null;
    }
}
