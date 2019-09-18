package com.zfsoft.xgxt.xyfd.aljlwh;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;
import common.newp.StringUtil;
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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

/**
 * Created by llf on 2019/8/30.
 */
public class AljlAction extends SuperAction<AljlForm,AljlService> {
    private static final String url = "xyfd_xyfd_gzaljl.do";
    private AljlService service = new AljlService();

    @SystemAuth(url = url)
    public ActionForward gzalList(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response) throws Exception{
        AljlForm model = (AljlForm) form;
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
        String path = "xyfd_xyfd_gzaljl.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("gzalList");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward addGzal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AljlForm model = (AljlForm) form;
        User user = getUser(request);
        if(StringUtils.isNotNull(model.getXh())){
            HashMap<String,String> xsxx = service.getXsxx(model.getXh());
            request.setAttribute("xsxx",xsxx);

            List<HashMap<String,String>> fdyxx = service.getFdyxx(xsxx.get("bjdm"));
            List<HashMap<String,String>> bzrxx = service.getBzrxx(xsxx.get("zybj"));

            request.setAttribute("fdyxx",fdyxx);
            request.setAttribute("bzrxx",bzrxx);
        }
        List<HashMap<String,String>> xnList = service.getXn();
        List<HashMap<String,String>> xqList = service.getXq();
        request.setAttribute("xnList",xnList);
        request.setAttribute("xqList",xqList);

        String path = "xyfd_gzaljl.do?method=addGzal";
        request.setAttribute("gotopath", path);
        return mapping.findForward("addGzal");
    }

    /**
     * ������γ�
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward bjgkc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AljlForm model = (AljlForm) form;
        Map<String,Object> map = new HashMap<String, Object>();
        List<HashMap<String,String>> bjgkclist = service.getBjgkcList(model);
        if(bjgkclist.size()<=0){
            map.put("status","no");
            map.put("message","������");
        }else {
            map.put("status","ok");
            map.put("message","��"+bjgkclist.size()+"������");
            map.put("bjgkclist",bjgkclist);
        }
        JSONObject json = JSONObject.fromObject(map);
        response.getWriter().print(json);
        return null;
    }

    /**
     * ���湤������
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward saveGzal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AljlForm model = (AljlForm) form;
        User user = getUser(request);
        HashMap<String,String> aljlxx = service.getAljlxx(model);
        if(aljlxx.size()>0){
            response.getWriter().print(getJsonMessage("�Ѵ��ڰ�����¼"));
            return null;
        }
        boolean result = service.saveGzal(model,user);
        String message = result ? MessageKey.SYS_SAVE_SUCCESS :
                MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(message));
        return null;
    }
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward toUpdateGzal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AljlForm model = (AljlForm) form;
        model = service.getModel(model);
        User user = getUser(request);
        if(StringUtils.isNotNull(model.getXh())){
            HashMap<String,String> xsxx = service.getXsxx(model.getXh());
            request.setAttribute("xsxx",xsxx);

            List<HashMap<String,String>> fdyxx = service.getFdyxx(xsxx.get("bjdm"));
            List<HashMap<String,String>> bzrxx = service.getBzrxx(xsxx.get("zybj"));

            request.setAttribute("fdyxx",fdyxx);
            request.setAttribute("bzrxx",bzrxx);
        }
        List<HashMap<String,String>> xnList = service.getXn();
        List<HashMap<String,String>> xqList = service.getXq();
        request.setAttribute("xnList",xnList);
        request.setAttribute("xqList",xqList);

        request.setAttribute("model",model);
        String path = "xyfd_gzaljl.do?method=addGzal";
        request.setAttribute("gotopath", path);
        return mapping.findForward("toUpdateGzal");
    }
    /**
     * ���湤����¼
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward addGzjl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AljlForm model = (AljlForm) form;
        User user = getUser(request);
        if(SAVE.equalsIgnoreCase(model.getType())){
            boolean result = service.saveGzjl(model,user);
            String message = result ? MessageKey.SYS_SAVE_SUCCESS :
                    MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(message));
            return null;
        }
        List<HashMap<String,String>> jyList = service.getJyList();//��һ������
        request.setAttribute("jyList",jyList);
        request.setAttribute("model",model);
        return mapping.findForward("addGzjl");
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward cd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AljlForm model = (AljlForm) form;
        request.setAttribute("model",model);
        return mapping.findForward("cd");
    }
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward xgjb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AljlForm model = (AljlForm) form;
        request.setAttribute("model",model);
        return mapping.findForward("xgjb");
    }
    /**
     * �޸ģ��������޸ļ���
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward updateGzal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AljlForm model = (AljlForm) form;
        User user = getUser(request);
        if(StringUtils.isNotNull(model.getType())&&model.getType().equals("cd")){//����
            model.setAlzt("0");
        }
        boolean result = service.runUpdate(model);
        String message = result ? MessageKey.SYS_SAVE_SUCCESS :
                MessageKey.SYS_SAVE_FAIL;
        response.getWriter().print(getJsonMessageByKey(message));
        return null;
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward delAl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AljlForm model = (AljlForm) form;
        String[] ids = request.getParameterValues("values");
        String message = service.delAl(ids);
        response.getWriter().print(getJsonMessage(message));
        return null;
    }

    public ActionForward viewGzal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AljlForm model = (AljlForm) form;
        model = service.getModel(model);//������Ϣ
        request.setAttribute("model",model);

        HashMap<String,String> xsxx = service.getXsxx(model.getXh());//ѧ����Ϣ
        request.setAttribute("xsxx",xsxx);

        List<HashMap<String,String>> fdyxx = service.getFdyxx(xsxx.get("bjdm"));//����Ա
        List<HashMap<String,String>> bzrxx = service.getBzrxx(xsxx.get("zybj"));//������
        request.setAttribute("fdyxx",fdyxx);
        request.setAttribute("bzrxx",bzrxx);

        List<HashMap<String,String>> bjgkclist = service.getBjgkcList(model);//������γ�
        request.setAttribute("bjgkclist",bjgkclist);

        List<HashMap<String,String>> gzjlList = service.getGzjlList(model); //������¼
        request.setAttribute("gzjlList",gzjlList);

        return mapping.findForward("viewGzal");
    }


    public ActionForward printJsp(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        AljlForm myForm = (AljlForm) form;
        String jdhs[]=myForm.getJdh().split(",");
        if(null!=jdhs&&jdhs.length==1){//һ������
            if (myForm != null){
                File file=print(myForm,jdhs[0],request);
                FileUtil.outputWord(response, file);
            }
        }else{//��������
            List<File> files = new ArrayList<File>();
            for(String jdh:jdhs){
                File file=print(myForm,jdh,request);
                files.add(file);
            }
            File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
            FileUtil.outputZip(response, zipFile);
        }

        return null;
    }

    private File print(AljlForm myForm,String guid,HttpServletRequest request) throws Exception{
        myForm.setJdh(guid);
        AljlService service = new AljlService();
        File file=null;
        AljlForm model = service.getModel(myForm);
        User user = getUser(request);

        if (model != null){
            //����ѧ��������Ϣ
            HashMap<String,String> xsxx = service.getXsxx(model.getXh());//ѧ����Ϣ
            BeanUtils.copyProperties(myForm, model);
            file=service.printForWord(xsxx,model, request);
        }
        return file;
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward alzj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AljlForm model = (AljlForm) form;
        model = service.getModel(model);
        request.setAttribute("model",model);
        return mapping.findForward("alzj");
    }
}
