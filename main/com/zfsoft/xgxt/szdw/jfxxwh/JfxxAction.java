package com.zfsoft.xgxt.szdw.jfxxwh;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.sxzzjygl.ztbhgl.ZtbhJgForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @������TODO
 * @���ߣ�WANCHEN
 * @���ڣ�
 */
public class JfxxAction extends SuperAction<JfxxForm,JfxxService> {
    private static final String  CJFF = "jfxx";
    private BdpzService bdpzService = new BdpzService();
    private List<HashMap<String,String>> jbxxList = null;
    public ActionForward jfjlList(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {

        JfxxForm model = (JfxxForm) form;
        if(QUERY.equals(model.getType())){
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // ��ѯ
            List<HashMap<String, String>> resultList = getService().getPageList(model,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path","szdw_jfxxwh.do");
        return mapping.findForward("jfjlList");
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        JfxxForm model = (JfxxForm) form;
        String xh = ((JfxxForm) form).getXh();
        if(SAVE.equals(model.getType())){
            SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
            model.setLrsj(sdf.format(new Date()));
            model.setLrr(getUser(request).getUserName());
            boolean flag = getService().saveAdd(model);
            String msg = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(JSONObject.fromObject(getJsonMessageByKey(msg)));
            return null;
        }
        //��ѯѧ����Ϣ
        if(xh != null && !"".equals(xh)){
            //����ѧ��������Ϣ
            XsxxService xsxxService = new XsxxService();
            HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
            request.setAttribute("jbxx", xsjbxx);
            request.setAttribute("xh", xh);
        }
        //ѧ��������Ϣ��ʾ����
        jbxxList = bdpzService.getJbxxpz(CJFF);
        request.setAttribute("jbxxList", jbxxList);
        request.setAttribute("path","szdw_jfxx.do?method=add");
        return mapping.findForward("add");
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        JfxxForm model = (JfxxForm) form;
        if(SAVE.equals(model.getType())){
            SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
            model.setLrsj(sdf.format(new Date()));
            model.setLrr(getUser(request).getUserName());
            boolean flag = getService().saveUpdate(model);
            String msg = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(JSONObject.fromObject(getJsonMessageByKey(msg)));
            return null;
        }
        JfxxForm data = getService().getModel(model);
        //��ѯѧ����Ϣ
        //����ѧ��������Ϣ
        XsxxService xsxxService = new XsxxService();
        HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(data.getXh());
        request.setAttribute("jbxx", xsjbxx);
        request.setAttribute("xh", data.getXh());
        //ѧ��������Ϣ��ʾ����
        jbxxList = bdpzService.getJbxxpz(CJFF);
        request.setAttribute("jbxxList", jbxxList);
        request.setAttribute("path","szdw_jfxx.do?method=update");
        request.setAttribute("filepath",data.getFilepath());
       /* List<HashMap<String,String>> list = getService().getCyList(data.getJgid());
        request.setAttribute("cyList",list);
        request.setAttribute("gxList",getService().jfcygxList());*/
        BeanUtils.copyProperties(model,data);
        return mapping.findForward("update");
    }

    public ActionForward getCyxxForUpdate(ActionMapping mapping, ActionForm form,
                                          HttpServletRequest request, HttpServletResponse response) throws Exception {
        String jgid = request.getParameter("jgid");
        List<HashMap<String,String>> list = getService().getCyList(jgid);
        response.getWriter().print(JSONArray.fromObject(list));
        return null;
    }
    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws Exception {
        JfxxForm model = (JfxxForm) form;

        JfxxForm data = getService().getModel(model);
        //��ѯѧ����Ϣ
        //����ѧ��������Ϣ
        XsxxService xsxxService = new XsxxService();
        HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(data.getXh());
        request.setAttribute("jbxx", xsjbxx);
        request.setAttribute("xh", data.getXh());
        //ѧ��������Ϣ��ʾ����
        jbxxList = bdpzService.getJbxxpz(CJFF);
        request.setAttribute("jbxxList", jbxxList);
//        request.setAttribute("path","szdw_jfxx.do?method=update");
        request.setAttribute("filepath",data.getFilepath());
        List<HashMap<String,String>> list = getService().getCyList(data.getJgid());
        request.setAttribute("cyList",list);
        request.setAttribute("gxList",getService().jfcygxList());
        request.setAttribute("model",data);
        return mapping.findForward("view");
    }

    public ActionForward del(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ids = request.getParameter("values");
        int num = getService().runDelete(ids.split(","));
        String msg = num > 0 ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
        response.getWriter().print(getJsonMessage(msg));
        return null;
    }

    public ActionForward exportData(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JfxxForm model = (JfxxForm) form;

        //���ɸ߼���ѯ����
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        model.setSearchModel(searchModel);
        User user = getUser(request);
        List<HashMap<String,String>> resultList = getService().getAllList(model,user);

        //�������ܴ���
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = model.getExportModel();
        exportModel.setZgh(user.getUserName());//��ǰ����Ա
        exportModel.setDataList(resultList);//��������
        exportModel.setDcclbh(request.getParameter("dcglbh"));//���õ�ǰ�������ܱ��
        File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
        FileUtil.outputExcel(response, file);
        return null;
    }

    public ActionForward jfcygxList(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        List<HashMap<String,String>> list = getService().jfcygxList();
        response.getWriter().print(JSONArray.fromObject(list));
        return null;
    }

    public ActionForward selectStudent(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        JfxxForm model = (JfxxForm) form;
        if(QUERY.equals(model.getType())){
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            // ��ѯ
            List<HashMap<String, String>> resultList = getService().selectXs(model,user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path","szdw_jfxx.do?method=selectStudent");
        return mapping.findForward("selectStudent");
    }
}
