package com.zfsoft.xgxt.zhdj.djgzjl;

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
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @�๦������:����������¼
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-06-11 17:36
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class DjgzjlAction extends SuperAction<DjgzjlForm,DjgzjlService>{
    private  DjgzjlService service = new DjgzjlService();
    private static final  String url = "zhdj_djgzjl_djgzjl.do";


    @SystemAuth(url = url)
    public ActionForward getList(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DjgzjlForm model = (DjgzjlForm) form;
        User user = getUser(request);
        if (QUERY.equals(model.getType())) {
            String flag = request.getParameter("flag");
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            // ��ѯ
            List<HashMap<String, String>> resultList = service.getPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        SearchModel searchModel=new SearchModel();
        searchModel.setSearch_tj_xn(new String[]{Base.currXn});
        searchModel.setSearch_tj_xq(new String[]{Base.currXq});
        request.setAttribute("searchTj", searchModel);
        String path = "zhdj_djgzjl_djgzjl.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("hdjgList");
    }
    public ActionForward add(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DjgzjlForm model = (DjgzjlForm) form;
        User user = getUser(request);
        request.setAttribute("xymc",user.getUserDepName());
        request.setAttribute("xydm",user.getUserDep());
        request.setAttribute("xn",Base.currXn);
        request.setAttribute("xqmc","01".equals(Base.currXq) ? "��һѧ��" : "�ڶ�ѧ��");
        request.setAttribute("xqdm",Base.currXq);
        return mapping.findForward("add");
    }
    public ActionForward update(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DjgzjlForm model = (DjgzjlForm) form;
        User user = getUser(request);
        HashMap<String,String> map = service.getInfo(model);
        BeanUtils.copyProperties(model,map);
        request.setAttribute("model",model);
        return mapping.findForward("update");
    }
    public ActionForward save(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DjgzjlForm model = (DjgzjlForm) form;
        User user = getUser(request);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sbsj = formatter.format(new Date());
        model.setSbsj(sbsj);
        boolean re = service.save(model);
        String messageKey = re ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }
    public ActionForward view(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DjgzjlForm model = (DjgzjlForm) form;
        User user = getUser(request);
        HashMap<String,String> map = service.getInfo(model);
        request.setAttribute("map",map);
        return mapping.findForward("view");
    }
    public ActionForward del(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //���id
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

    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DjgzjlForm model = (DjgzjlForm) form;
        // ���ɸ߼���ѯ����
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        model.getPages().setPageSize(Integer.MAX_VALUE);
        // ��ѯ
        List<HashMap<String, String>> resultList = service.getAllList(model,user);// ��ѯ�����м�¼������ҳ
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
