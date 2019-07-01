/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-7-4 ����02:27:43
 */
package xsgzgl.qgzx.xsgl;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
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
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ڹ���ѧ
 * @�๦������: �ڹ���ѧѧ��ά��
 * @���ߣ� �ո־� [1075]
 * @ʱ�䣺 2014-7-4 ����02:27:43 
 * @�汾�� V5.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class QgzxXsglAction extends SuperAction {

    private static final String url = "qgzx_xsgl_xswh.do";

    /**
     *
     * @����:�ڹ���ѧѧ��ά��
     * @���ߣ��ո־� [1075]
     * @���ڣ�2014-7-4 ����04:22:35
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
    @SystemAuth(url = url)
    public ActionForward xswh(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        QgzxXsglForm model = (QgzxXsglForm) form;
        QgzxXsglService service = new QgzxXsglService();

        if (QUERY.equals(model.getType())) {
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

        SearchModel searchModel = new SearchModel();
        request.setAttribute("searchTj", searchModel);
        String path = "qgzx_xsgl_xswh.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("xsWh");

    }

    /**
     *
     * @����:��ȡѧ��
     * @���ߣ��ո־� [1075]
     * @���ڣ�2014-7-7 ����04:23:27
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
    public ActionForward getStu(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        QgzxXsglService service = new QgzxXsglService();
        QgzxXsglForm model = (QgzxXsglForm) form;
        if (QUERY.equals(model.getType())) {
            //���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            //��ѯ
            List<HashMap<String, String>> resultList = service.getXsPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }

        String path = "qgzx_xsgl.do?method=getStu";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("getStu");
    }

    /**
     *
     * @����:TODO(������һ�仰�����������������)
     * @���ߣ�����[���ţ�1104]
     * @���ڣ�2014-7-30 ����02:06:01
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
    @SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
    @SystemLog(description = "�����ڹ���ѧ-��������-�ڹ�ѧ��ά��-���ӻ��޸�XH{values}")
    public ActionForward addQgxs(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        QgzxXsglService service = new QgzxXsglService();
        String values = request.getParameter("values");

        if (!StringUtil.isNull(values)) {
            boolean result = true;
            String[] xhs = values.split(",");
            for (String xh : xhs) {
                result = service.saveQgzxXs(xh);
            }
            String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }

    /**
     *
     * @����:ɾ������
     * @���ߣ��ո־� [1075]
     * @���ڣ�2014-7-7 ����05:18:45
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
    @SystemAuth(url = url, rewritable = ReadWrite.WRITEABLE)
    @SystemLog(description = "�����ڹ���ѧ-��������-�ڹ�ѧ��ά��-ɾ��XH{values}")
    public ActionForward deleteQgxs(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        QgzxXsglService service = new QgzxXsglService();
        String values = request.getParameter("values");

        if (!StringUtil.isNull(values)) {
            int num = service.runDelete(values.split(","));
            boolean result = num > 0;
            String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM, num)
                    : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
            response.getWriter().print(getJsonMessage(message));
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }

    /**
     * @��������:ά���Ƿ�����ҳ��
     * @auther: ������[1692]
     */
    public ActionForward updateSfgmbx(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        QgzxXsglService service = new QgzxXsglService();
        QgzxXsglForm model = (QgzxXsglForm) form;
        String xh = model.getXh();
        HashMap<String, String> dataModel = service.getDetail(xh);
        request.setAttribute("dataModel", dataModel);
        return mapping.findForward("updateSfgmbx");
    }

    /**
     * @��������:�޸��Ƿ�������Ϣ
     * @auther: ������[1692]
     */
    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        QgzxXsglService service = new QgzxXsglService();
        QgzxXsglForm model = (QgzxXsglForm) form;
        boolean result = service.updateSfgmbx(model);
        String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    /**
     * @��������:��������������Ϣ
     * @auther: ������[1692]
     */
    public ActionForward plUpdate(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        QgzxXsglService service = new QgzxXsglService();
        String values = request.getParameter("values");
        String[] xhs = values.split(",");
        for (String xh:xhs){
            boolean result = service.plUpdate(xh);
            String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
        }
        return null;
    }

    /**
     * @��������:�����ڹ���Ա��Ϣ
     * @auther: ������[1692]
     */
    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        QgzxXsglService service = new QgzxXsglService();
        QgzxXsglForm model = (QgzxXsglForm) form;

        //���ɸ߼���ѯ����
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String,String>> resultList = service.getQgryAllList(model,user);
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = model.getExportModel();
        exportModel.setZgh(user.getUserName());//��ǰ����Ա
        exportModel.setDataList(resultList);//��������
        exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
        File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
        FileUtil.outputExcel(response, file);
        return null;
    }
}
