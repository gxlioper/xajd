package com.zfsoft.xgxt.cxcy.sbwh.jzsb;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import common.newp.StringUtil;
import net.sf.json.JSONArray;
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
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * @�๦������:���´�ҵ-�ϱ�ά��-�����ϱ�
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-09-07 09:14
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class JzsbAction extends SuperAction<JzsbForm,JzsbService> {
    private static final String url = "cxcy_sbwh_jzsb.do";
    private JzsbService service = new JzsbService();

    public ActionForward getList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JzsbForm model = (JzsbForm)form;
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
        SearchModel searchModel=new SearchModel();
        searchModel.setSearch_tj_xn(new String[] { Base.currXn });
        searchModel.setSearch_tj_xq(new String[] { Base.currXq });
        request.setAttribute("searchTj", searchModel);
        request.setAttribute("path", url);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("jzsbList");
    }
    public ActionForward jzsbAdd(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JzsbForm model = (JzsbForm)form;
        User user = getUser(request);
        request.setAttribute("xn",Base.currXn);
        request.setAttribute("xq",Base.currXq);
        request.setAttribute("xqmc",Base.getXqmcForXqdm(Base.currXq));
        request.setAttribute("user",user);
        request.setAttribute("xyList",Base.getXyNewList());
        return mapping.findForward("jzsbAdd");
    }

    public ActionForward jzsbUpdate(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JzsbForm myForm = (JzsbForm)form;
        User user = getUser(request);
        HashMap<String,String> map = service.getInfoById(myForm.getId());
        BeanUtils.copyProperties(myForm,map);
        request.setAttribute("map", map);
        return mapping.findForward("jzsbUpdate");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JzsbForm model = (JzsbForm)form;
        User user = getUser(request);
        boolean rs = true;
        try {
            rs = service.save(model,user);
        }catch (SystemException e) {
            response.getWriter().print(getJsonMessage(e.getMessage()));
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    public ActionForward jzsbDel(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String values = request.getParameter("values");
        if (!StringUtil.isNull(values)) {
            String[] ids = values.split(",");
            int num = service.runDelete(ids);
            boolean result = num > 0;
            String message = result ? MessageUtil.getText(
                    MessageKey.SYS_DEL_NUM, num) : MessageUtil
                    .getText(MessageKey.SYS_DEL_FAIL);
            response.getWriter().print(getJsonMessage(message));
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }

    public ActionForward jzsbView(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JzsbForm myForm = (JzsbForm)form;
        HashMap<String,String> map = service.getInfoById(myForm.getId());
        request.setAttribute("map", map);
        return mapping.findForward("jzsbView");
    }


    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        JzsbForm model = (JzsbForm) form;
        // ���ɸ߼���ѯ����
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);

        User user = getUser(request);
        // ��ѯ
        List<HashMap<String, String>> resultList = service.getAllList(model,
                user);// ��ѯ�����м�¼������ҳ
        // �������ܴ���
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = model.getExportModel();
        exportModel.setZgh(user.getUserName());// ��ǰ����Ա
        exportModel.setDataList(resultList);// ��������
        exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
        File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
        FileUtil.outputExcel(response, file);
        return null;
    }

}
