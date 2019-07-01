package com.zfsoft.xgxt.hdgl.hdqd;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.hdgl.hdxx.HdxxForm;
import com.zfsoft.xgxt.hdgl.hdxx.HdxxService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import net.sf.json.JSONArray;
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
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

/**
 * @������TODO
 * @���ߣ�WANCHEN
 * @���ڣ�
 */
public class HdqdAction extends SuperAction<HdqdForm,HdqdService> {
    private HdxxService hdxxService = new HdxxService();
    //ѧ��������Ϣ����
    private static List<HashMap<String, String>> jbxxList = null;

    public static String HDBL = "hdbl";

    static {
        BdpzService bdpzService = new BdpzService();
        // ѧ��������Ϣ��ʾ����
        jbxxList = bdpzService.getJbxxpz(HDBL);
    }

    public ActionForward hdqdList(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response) throws Exception {
        HdqdForm model = (HdqdForm)form;
        if(QUERY.equalsIgnoreCase(model.getType())){
            User user = getUser(request);
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            // ��ѯ
            List<HashMap<String, String>> resultList = getService().getPageList(model, user);
            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
        request.setAttribute("path", "hdgl_hdgl_hdqd.do");
        return mapping.findForward("hdqdList");
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        HdqdForm model = (HdqdForm)form;

        if (!StringUtil.isNull(model.getXh())) {
            // ����ѧ��������Ϣ
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        //���ػ��Ϣ
        HashMap<String, String> hdxx = hdxxService.getHdxx(model);
        BeanUtils.copyProperties(model,hdxx);
        request.setAttribute("hdxx",hdxx);
        if(!StringUtil.isNull(model.getXh())&&!StringUtil.isNull(model.getHdid())){
            if(!StringUtil.isNull(model.getDzxm())){
                String dz = URLDecoder.decode(model.getDzxm(),"UTF-8");
                model.setDzxm(dz);
            }
        }
        if(SAVE.equalsIgnoreCase(model.getType())){
            HdqdService service = getService();
            if(service.isExist(model)){
                response.getWriter().print(getJsonMessage("�����ظ�����ȷ�ϣ�"));
                return null;
            }
            boolean flag = service.save(model);
            String key = flag ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }
        request.setAttribute("jbxxList", jbxxList);

        request.setAttribute("path", "hdgl_hdgl_hdqd_wh.do?method=add");

        return mapping.findForward("add");
    }

    public ActionForward updateqdView(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        HdqdForm model = (HdqdForm)form;
        if (!StringUtil.isNull(model.getXh())) {
            // ����ѧ��������Ϣ
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
            request.setAttribute("jbxx", xsjbxx);
        }
        if(!StringUtil.isNull(model.getHdid())){
            //���ػ��Ϣ
            HashMap<String, String> hdxx = hdxxService.getHdxx(model);
            BeanUtils.copyProperties(model,hdxx);
            request.setAttribute("hdxx",hdxx);
        }
        if(!StringUtil.isNull(model.getXh())&&!StringUtil.isNull(model.getHdid())){
            HdqdService hdqdService = new HdqdService();
            if("0".equals(model.getBmlx())){
                //���������Ϣ
                HashMap<String,String> dwxx = hdqdService.getDwxx(model);
                dwxx.put("xh",model.getXh());
                BeanUtils.copyProperties(model,dwxx);
            }
            //����ǩ����Ϣ
            HashMap<String,String> hdqd = hdqdService.getHdqd(model);
            BeanUtils.copyProperties(model,hdqd);
            request.setAttribute("hdqd",hdqd);
        }
        request.setAttribute("jbxxList", jbxxList);
        request.setAttribute("path", "hdgl_hdgl_hdqd_wh.do?method=updateqdView");

        return mapping.findForward("updateqdView");
    }
    public ActionForward saveQdxx(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        HdqdForm model = (HdqdForm)form;
        HdqdService service = getService();
        String messageKey;
        if(service.modifyQd(model)){
            messageKey = MessageKey.SYS_SAVE_SUCCESS;
        } else {
            messageKey = MessageKey.SYS_SAVE_FAIL;
        }
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }
    public ActionForward getHdxxList(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        HdqdForm model = (HdqdForm)form;
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
        String gotoPath = request.getParameter("goto");
        request.setAttribute("gotoPath", gotoPath);
        request.setAttribute("path","hdgl_hdgl_hdqd_wh.do?method=getHdxxList");
        return mapping.findForward("hdxxList");
    }

    public ActionForward getDwList(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        HdqdForm model = (HdqdForm)form;
        if (QUERY.equalsIgnoreCase(model.getType())) {
            HdxxService service = new HdxxService();
            List<HashMap<String,String>> dwList = service.getDwList(model.getXh(),model.getHdid());
            JSONArray dataList = JSONArray.fromObject(dwList);
            response.getWriter().print(dataList);
            return null;
        }
        String gotoPath = request.getParameter("goto");
        request.setAttribute("xh", model.getXh());
        request.setAttribute("hdid", model.getHdid());
        request.setAttribute("gotoPath", gotoPath);
        request.setAttribute("path","hdgl_hdgl_hdqd_wh.do?method=getDwList");
        return mapping.findForward("showDw");
    }
    public ActionForward jrdwCheck(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception{
        HdqdForm model = (HdqdForm) form;
        HdxxService service = new HdxxService();
        String msg = service.checkDwrs(model);
        response.getWriter().print(getJsonMessage(msg));
        return null;
    }
    public ActionForward deleteQd(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request, HttpServletResponse response) throws Exception{
        HdqdForm model = (HdqdForm)form;
        String qdxxlist = request.getParameter("qdxxlist");
        if(!StringUtil.isNull(qdxxlist)){
            HdqdService hdqdService = getService();
            boolean result = true;
            String[] qdxxs = qdxxlist.split(",");
            HashMap<String,Object> msg = new HashMap<>();
            try{
                for(int i=0;i<qdxxs.length;i++){
                    model.setXh(qdxxs[i].split("_")[0]);
                    model.setHdid(qdxxs[i].split("_")[1]);
                    HashMap<String, String> hdxx = hdxxService.getHdxx(model);
                    BeanUtils.copyProperties(model,hdxx);
                    msg = hdqdService.del(model);
                    if(!(boolean)msg.get("result")){
                        result = false;
                        break;
                    }
                }
                String message = "";
                if(result){
                    message = MessageUtil.getText(MessageKey.SYS_DEL_NUM, qdxxs.length);
                    response.getWriter().print(getJsonMessage(message));
                }else {
                    if (!StringUtil.isNull((String)msg.get("error"))){
                        response.getWriter().print(getJsonMessage((String)msg.get("error")));
                    }else {
                        message = MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
                        response.getWriter().print(getJsonMessage(message));
                    }
                }
            } catch (Exception e){
                throw new SystemException(MessageKey.SYS_DEL_NULL);
            }
        }else{
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }
    public ActionForward export(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HdqdForm myForm=(HdqdForm)form;

        //���ɸ߼���ѯ����
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        myForm.setSearchModel(searchModel);

        User user = getUser(request);
        List<HashMap<String,String>> resultList = getService().getAllList(myForm,user);

        //�������ܴ���
        IExportService exportService = new ExportExcelImpl();
        ExportModel exportModel = myForm.getExportModel();
        exportModel.setZgh(user.getUserName());//��ǰ����Ա
        exportModel.setDataList(resultList);//��������
        exportModel.setDcclbh(request.getParameter("dcclbh"));//���õ�ǰ�������ܱ��
        File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
        FileUtil.outputExcel(response, file);
        return null;
    }
}
