package com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjssq;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.*;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjsjcsz.GrxfjsjcszForm;
import com.zfsoft.xgxt.sxzzjy.grxfjs.grxfjsjcsz.GrxfjsjcszService;
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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @�๦������:
 * @���ߣ� lgx [����:1553]
 * @ʱ�䣺 2018-06-26 09:42
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class GrxfjssqAction extends SuperAction<GrxfjssqForm,GrxfjssqService> {
    private static final String url = "sxzzjy_grxfjs_grxfjssq.do";
    private GrxfjssqService service = new GrxfjssqService();

    private static final String GRXFJS = "grxfjs";
    private static List<HashMap<String, String>> jbxxList = null;
    static {
        BdpzService bdpzService = new BdpzService();
        // ѧ��������Ϣ��ʾ����
        jbxxList = bdpzService.getJbxxpz(GRXFJS);
    }

    @SystemAuth(url = url)
    public ActionForward getList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrxfjssqForm model = (GrxfjssqForm) form;
        User user = getUser(request);
        if (QUERY.equals(model.getType())) {
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
        GrxfjsjcszService grxfjsjcszService = new GrxfjsjcszService();
        GrxfjsjcszForm jcszModel = grxfjsjcszService.getModel();
        request.setAttribute("jcszModel",jcszModel);
        SearchModel searchModel=new SearchModel();
        searchModel.setSearch_tj_xn(new String[]{Base.currXn});
        searchModel.setSearch_tj_xq(new String[]{Base.currXq});
        request.setAttribute("searchTj", searchModel);
        String path = "sxzzjy_grxfjs_grxfjssq.do";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("grxfjssqList");
    }
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    @SystemLog(description="˼�����ν�������-����ѧ�罨�����-����ѧ�ֽ�������-���� XH:{xh},XFJSMC:{xfjsmc},SBLX:{sblx},BXNMB:{bxnmb},JSSL:{jssl}")
    public ActionForward grxfjssqAdd(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrxfjssqForm model = (GrxfjssqForm) form;
        User user = getUser(request);
        List<HashMap<String, String>> sblxList = service.getAllSblx();
        request.setAttribute("sblxList", sblxList);
        model.setXn(Base.currXn);
        model.setXq(Base.currXq);
        if (SAVE.equalsIgnoreCase(model.getType()) || SUBMIT.equalsIgnoreCase(model.getType())) {
            boolean isExist = false;
            model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
            isExist = service.isExist(model);
            if (!isExist) {
                super.resetToken(request);
                // ���
                String sqid = UniqID.getInstance().getUniqIDHash();
                model.setSqid(sqid);
                model.setSqr(user.getUserName());
                boolean result = service.saveGrxfjssq(model);
                String messageKey = "";
                if (SAVE.equalsIgnoreCase(model.getType())) {
                    messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
                } else {
                    messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
                }
                response.getWriter().print(getJsonMessageByKey(messageKey));
                return null;
            } else {
                String message = MessageUtil.getText(MessageKey.RCSW_YLBX_SQ_REPEAT);
                response.getWriter().print(getJsonMessage(message));
                return null;
            }
        }else {
            if ("stu".equals(user.getUserType())){
                model.setXh(user.getUserName());
            }
            if (!StringUtil.isNull(model.getXh())) {
                // ����ѧ��������Ϣ
                XsxxService xsxxService = new XsxxService();
                HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
                request.setAttribute("jbxx", xsjbxx);
            }
            //ѧ��������Ϣ��ʾ����
            request.setAttribute("jbxxList", jbxxList);
            String path = "sxzzjy_grxfjssq.do?method=grxfjssqAdd";
            request.setAttribute("path", path);
        }
        String xnxq = Base.currXn + Base.dqxqmc;
        request.setAttribute("xnxq", xnxq);
        return mapping.findForward("grxfjssqAdd");
    }
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    @SystemLog(description="˼�����ν�������-����ѧ�罨�����-����ѧ�ֽ�������-�޸� SQID:{sqid},XH:{xh},XFJSMC:{xfjsmc},SBLX:{sblx},BXNMB:{bxnmb},JSSL:{jssl}")
    public ActionForward grxfjssqUpdate(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrxfjssqForm model = (GrxfjssqForm) form;
        User user = getUser(request);
        List<HashMap<String,String>> sblxList = service.getAllSblx();
        request.setAttribute("sblxList",sblxList);
        if (SAVE.equalsIgnoreCase(model.getType())
                || SUBMIT.equalsIgnoreCase(model.getType())) {
            boolean isExist = false;
            model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
            isExist = service.isExist(model);
            if (!isExist) {
                boolean result = service.updateGrxfjssq(model);
                String messageKey = "";
                if (SAVE.equalsIgnoreCase(model.getType())) {
                    messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
                } else {
                    messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
                }
                response.getWriter().print(getJsonMessageByKey(messageKey));
                return null;
            } else {
                String message = MessageUtil.getText(MessageKey.RCSW_YLBX_SQ_REPEAT) ;
                response.getWriter().print(getJsonMessage(message));
                return null;
            }
        }else {
            GrxfjssqForm myform = service.getModel(model);
            BeanUtils.copyProperties(model, StringUtils.formatData(myform));
            if (!StringUtil.isNull(model.getXh())) {
                // ����ѧ��������Ϣ
                XsxxService xsxxService = new XsxxService();
                HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
                request.setAttribute("jbxx", xsjbxx);
            }else{}
            //ѧ��������Ϣ��ʾ����
            request.setAttribute("jbxxList", jbxxList);
            String path = "sxzzjy_grxfjssq.do?method=grxfjssqUpdate";
            request.setAttribute("path", path);
        }
        HashMap<String,String> map = service.getGrxfjssqInfo(model);
        request.setAttribute("map",map);
        String xnxq = model.getXn()+Base.dqxqmc;
        request.setAttribute("xnxq",xnxq);
        return mapping.findForward("grxfjssqUpdate");
    }
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    @SystemLog(description="˼�����ν�������-����ѧ�罨�����-����ѧ�ֽ�������-ɾ�� VALUES:{values}")
    public ActionForward grxfjssqDel(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String values = request.getParameter("values");
        if (!StringUtil.isNull(values)) {
            // ɾ��
            int num = service.runDelete(values.split(","));
            Map<String, String> map = new HashMap<String, String>();
            map.put("num", num + "");
            JSONObject json = JSONObject.fromObject(map);
            response.getWriter().print(json);
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }
    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    @SystemLog(description="˼�����ν�������-����ѧ�罨�����-����ѧ�ֽ�������-�ύ SQID:{sqid}")
    public ActionForward grxfjssqSubmit(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrxfjssqForm model = (GrxfjssqForm) form;
        GrxfjssqForm myForm = service.getModel(model);
        boolean result = service.grxfjssqSubmit(myForm);
        String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    @SystemLog(description="˼�����ν�������-����ѧ�罨�����-����ѧ�ֽ�������-���� SQID:{sqid}")
    public ActionForward grxfjssqCancel(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrxfjssqForm model = (GrxfjssqForm) form;
        // ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
        boolean result = service.cancelRecord(model.getSqid(), model.getSplc());
        if (result) {
            // ����ҵ��״̬Ϊ'δ�ύ'
            ShlcDao shlcdao = new ShlcDao();
            if(Integer.valueOf(shlcdao.getExistTh(model.getSqid()))>0){
                model.setShzt(Constants.YW_YTH);
            }else{
                model.setShzt(Constants.YW_WTJ);
            }
            service.grxfjssqCancel(model);
        }
        String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }


    public ActionForward grxfjssqView(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        GrxfjssqForm model = (GrxfjssqForm) form;
        HashMap<String,String> map = service.getGrxfjssqInfo(model);
        if (!StringUtil.isNull(map.get("xh"))) {
            // ����ѧ��������Ϣ
            XsxxService xsxxService = new XsxxService();
            HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(map.get("xh"));
            request.setAttribute("jbxx", xsjbxx);
        }
        //ѧ��������Ϣ��ʾ����
        request.setAttribute("jbxxList", jbxxList);
        String path = "sxzzjy_grxfjssq.do?method=grxfjssqView";
        request.setAttribute("path", path);
        request.setAttribute("map",map);
        request.setAttribute("shztmc",map.get("shztmc"));
        return mapping.findForward("grxfjssqView");
    }

    public ActionForward getPrint(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String id =  request.getParameter("sqid");
        File wordFile = getWord(id);
        FileUtil.outputWord(response, wordFile);
        return null;
    }

    /**
     * @����:
     * @���ߣ�lgx [���ţ�1553]
     * @���ڣ�2018/7/31 15:33
     * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
     * @param: [mapping, form, request, response]
     * @return: org.apache.struts.action.ActionForward
     */
    public ActionForward getPrintZip(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String value = request.getParameter("value");
        if (StringUtils.isNotNull(value)){
            String[] values = value.split(",");
            List<File> files = new ArrayList<File>();
            for (int i = 0 , n = values.length ; i < n ; i++){
                File file = getWord(values[i]);
                files.add(file);
            }

            File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
            FileUtil.outputZip(response, zipFile);
        }
        return null;
    }

    private File getWord (String id) throws Exception {
        GrxfjssqForm model = new GrxfjssqForm();
        model.setSqid(id);
        HashMap<String, String> map = service.getGrxfjssqInfo(model);
        XsxxService xsxxService = new XsxxService();
        HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(map.get("xh"));

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("model",map);
        data.putAll(xsjbxx);
        String tbrq =new SimpleDateFormat("yyyy��MM��dd��").format(new Date());
        data.put("tbrq",tbrq);
        File file = null;
            file = FreeMarkerUtil.getWordFile(data,"classpath://templates//sxzzjygl",
                    "grxfjssq_10699.xml",FreeMarkerUtil.getFileName(xsjbxx.get("xh")+"_"+"����"));
        return file;
    }
}
