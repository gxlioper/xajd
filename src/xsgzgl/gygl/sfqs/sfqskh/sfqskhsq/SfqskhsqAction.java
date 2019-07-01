package xsgzgl.gygl.sfqs.sfqskh.sfqskhsq;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import net.sf.json.JSONArray;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.sfqs.sfqscj.sfqscjsq.SfqscjsqForm;
import xsgzgl.gygl.sfqs.sfqscj.sfqscjsq.SfqscjsqService;
import xsgzgl.gygl.xjdfygl.cwxxgl.CwxxglService;
import xsgzgl.gygl.xjdfygl.ldxxgl.LdxxglService;
import xsgzgl.gygl.xjdfygl.qsxxgl.QsxxglService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @描述：TODO
 * @作者：WANCHEN
 * @日期：
 */
public class SfqskhsqAction extends SuperAction<SfqskhsqForm,SfqskhsqService> {
    private LdxxglService ldxxglService = new LdxxglService();
    private QsxxglService qsxxglService = new QsxxglService();
    private CwxxglService cwxxglService = new CwxxglService();
    private ShlcInterface shlc = new CommShlcImpl();


    public ActionForward sqList(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        SfqskhsqForm model = (SfqskhsqForm)form;
        if(QUERY.equals(model.getType())){
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            List<HashMap<String,String>> data = getService().getPageList(model,user);
            response.getWriter().print(JSONArray.fromObject(data));
            return null;
        }
        request.setAttribute("path","gygl_sfqskh_sq.do");
//        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("sqList");
    }

    public ActionForward shList(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        SfqskhsqForm model = (SfqskhsqForm)form;
        if(QUERY.equals(model.getType())){
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            User user = getUser(request);
            List<HashMap<String,String>> data = getService().getShList(model,user);
            response.getWriter().print(JSONArray.fromObject(data));
            return null;
        }
        request.setAttribute("path","gygl_sfqskh_sh.do");
//        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("shList");
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        SfqskhsqForm model = (SfqskhsqForm)form;
        SfqskhsqService service = getService();
        if(SAVE.equals(model.getType()) || SUBMIT.equals(model.getType())){
            User user = getUser(request);
            model.setXh(user.getUserName());
            String yzmsg = service.yz(model,"add");
            if("true".equals(yzmsg)){
                boolean f = service.sqSave(model);
                String key = f ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
                response.getWriter().print(getJsonMessageByKey(key));
                return null;
            } else {
                response.getWriter().print(getJsonMessage(yzmsg));
                return null;
            }

        }
        model.setXn(Base.currXn);
        request.setAttribute("xqList",ldxxglService.getXqList());
        request.setAttribute("xnList", Base.getXnndList());
        return mapping.findForward("add");
    }
    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        SfqskhsqForm model = (SfqskhsqForm)form;
        SfqskhsqService service = getService();
        if(SAVE.equals(model.getType()) || SUBMIT.equals(model.getType())){
            User user = getUser(request);
            model.setXh(user.getUserName());
            String yzmsg = service.yz(model,"update");
            if("true".equals(yzmsg)){
                boolean f = service.update(model);
                String key = f ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
                response.getWriter().print(getJsonMessageByKey(key));
                return null;
            } else {
                response.getWriter().print(getJsonMessage(yzmsg));
                return null;
            }
        }
        SfqskhsqForm data = service.getModel(model);
        HashMap<String,String> xqMap = ldxxglService.getXqdmByLddm(data.getLddm());
        request.setAttribute("xqList",ldxxglService.getXqList());
        request.setAttribute("xnList", Base.getXnndList());
        request.setAttribute("ldList",ldxxglService.getLdxxByXq(xqMap.get("xqdm")));
        request.setAttribute("qsList",qsxxglService.getQsxxListByLddm(data.getLddm()));
        request.setAttribute("qscyList",service.getQscyList(data.getLddm(),data.getQsh(),data.getXn()));
        request.setAttribute("filepath",data.getFilepath());
        BeanUtils.copyProperties(model, StringUtils.formatData(data));
        request.setAttribute("zysj",model.getZysj());
        model.setXqdm(xqMap.get("xqdm"));
        return mapping.findForward("update");
    }
    public ActionForward del(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ids = request.getParameter("values");
        int i = getService().runDelete(ids.split(","));
        String msg = i>0 ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,i) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
        response.getWriter().print(getJsonMessage(msg));
        return null;
    }
    public ActionForward submit(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SfqskhsqForm model = (SfqskhsqForm) form;
        String id = request.getParameter("values");
        model.setSqid(id);
        model.setType(SUBMIT);
        boolean result = getService().submit(model);
        String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }
    public ActionForward cancel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String sqid = request.getParameter("values");
        String lcid = request.getParameter("splc");
        //只有刚提交并且第一级未审核的前提下，申请人可以撤销

        boolean result = shlc.firstStepCancle(sqid,lcid);
        if(result){
            //更新业务状态为'未提交'
            SfqskhsqForm model = new SfqskhsqForm();
            model.setSqid(sqid);
            model.setSplc(lcid);
            model.setShzt(Constants.YW_WTJ);
            result = getService().runUpdate(model);
        }
        String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
                : MessageKey.SYS_CANCEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }
    public ActionForward sh(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request, HttpServletResponse response) throws Exception {
        SfqskhsqForm model = (SfqskhsqForm)form;
        SfqskhsqService service = getService();
        if(SAVE.equals(model.getType())){
            User user = getUser(request);
            boolean f = service.saveSh(model,user);
            String key = f ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(key));
            return null;
        }
        SfqskhsqForm data = service.getModel(model);
        HashMap<String,String> xqMap = ldxxglService.getXqdmByLddm(data.getLddm());
        data.setXqmc(xqMap.get("xqmc"));
        request.setAttribute("qscyList",service.getQscyList(data.getLddm(),data.getQsh(),data.getXn()));
        request.setAttribute("model",StringUtils.formatData(data));
        request.setAttribute("filepath",data.getFilepath());
        return mapping.findForward("sh");
    }
    public ActionForward plsh(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SfqskhsqForm myForm = (SfqskhsqForm) form;
        User user = getUser(request);
        if("SAVE".equalsIgnoreCase(myForm.getType())){
            String message = getService().plshBc(myForm, user);
            response.getWriter().print(getJsonMessage(message));
            return null;
        }
        return mapping.findForward("plsh");
    }

    public ActionForward cancelSh(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {
        SfqskhsqForm model = (SfqskhsqForm) form;
        boolean isSuccess = getService().cancelSh(model);
        String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
        response.getWriter().print(getJsonMessageByKey(messageKey));
        return null;
    }
    public ActionForward view(ActionMapping mapping, ActionForm form,
                            HttpServletRequest request, HttpServletResponse response) throws Exception {
        SfqskhsqForm model = (SfqskhsqForm)form;
        SfqskhsqService service = getService();

        SfqskhsqForm data = service.getModel(model);
        HashMap<String,String> xqMap = ldxxglService.getXqdmByLddm(data.getLddm());
        data.setXqmc(xqMap.get("xqmc"));
        request.setAttribute("qscyList",service.getQscyList(data.getLddm(),data.getQsh(),data.getXn()));
        request.setAttribute("model",StringUtils.formatData(data));
        request.setAttribute("filepath",data.getFilepath());
        return mapping.findForward("view");
    }

    public ActionForward getLddxxList(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response) throws Exception {
        String xqdm = request.getParameter("xqdm");
        List<HashMap<String,String>> list = ldxxglService.getLdxxByXq(xqdm);
        response.getWriter().print(JSONArray.fromObject(list));
        return null;
    }

    public ActionForward getQsxxList(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        String lddm = request.getParameter("lddm");
        List<HashMap<String,String>> list = qsxxglService.getQsxxListByLddm(lddm);
        response.getWriter().print(JSONArray.fromObject(list));
        return null;
    }
    public ActionForward getQshXsxxList(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response) throws Exception {
        String lddm = request.getParameter("lddm");
        String qsh = request.getParameter("qsh");
        List<HashMap<String,String>> list = cwxxglService.getQshXsxxList(lddm,qsh);
        response.getWriter().print(JSONArray.fromObject(list));
        return null;
    }

    public ActionForward downloadWord(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SfqskhsqForm model = (SfqskhsqForm)form;
        SfqskhsqService service = getService();
        HashMap<String,String> sqRs = service.getMap(model.getSqid());
        StringUtils.formatData(sqRs);
        HashMap<String,Object> data = new HashMap<String, Object>();
        data.putAll(sqRs);
        List<HashMap<String,String>> cyList = service.getQscyList(sqRs.get("lddm"),sqRs.get("qsh"),sqRs.get("xn"));
        for(int i=0;i<4;i++){
            if(i<cyList.size()){
                data.put("xh"+(i+1),cyList.get(i).get("xh"));
                data.put("xm"+(i+1),cyList.get(i).get("xm"));
            } else {
                data.put("xh"+(i+1),"");
                data.put("xm"+(i+1),"");
            }
        }
        List<HashMap<String,String>> shyjList = new CommShlcImpl().getShyjListByYwid(model.getSqid());
        data.put("syshyj",shyjList.get(0).get("shyj"));
        data.put("xxshyj",shyjList.get(1).get("shyj"));

        File file = FreeMarkerUtil.getWordFile(data, "classpath:templates/gygl", "sfqskhsqb_10698.xml", "西安交通大学"+sqRs.get("ldmc")+"-"+sqRs.get("qsh")+"宿舍示范宿舍考核表");
        FileUtil.outputWord(response, file);
        return null;
    }

    public ActionForward downloadZip(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String sqids = request.getParameter("sqids");
        SfqskhsqService service = getService();
        List<File> files = new ArrayList<File>();

        if(!StringUtils.isNull(sqids)){
            String[] ids = sqids.split(",");
            File file;
            for(int x=0;x<ids.length;x++){
                HashMap<String,String> sqRs = service.getMap(ids[x]);
                StringUtils.formatData(sqRs);
                HashMap<String,Object> data = new HashMap<String, Object>();
                data.putAll(sqRs);
                List<HashMap<String,String>> cyList = service.getQscyList(sqRs.get("lddm"),sqRs.get("qsh"),sqRs.get("xn"));
                for(int i=0;i<4;i++){
                    if(i<cyList.size()){
                        data.put("xh"+(i+1),cyList.get(i).get("xh"));
                        data.put("xm"+(i+1),cyList.get(i).get("xm"));
                    } else {
                        data.put("xh"+(i+1),"");
                        data.put("xm"+(i+1),"");
                    }
                }
                List<HashMap<String,String>> shyjList = new CommShlcImpl().getShyjListByYwid(ids[x]);
                data.put("syshyj",shyjList.get(0).get("shyj"));
                data.put("xxshyj",shyjList.get(1).get("shyj"));

                file = FreeMarkerUtil.getWordFile(data, "classpath:templates/gygl", "sfqskhsqb_10698.xml", "西安交通大学"+sqRs.get("ldmc")+"-"+sqRs.get("qsh")+"宿舍示范宿舍考核表");
                files.add(file);
            }
            File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
            FileUtil.outputZip(response, zipFile);
        }

        return null;
    }
}
