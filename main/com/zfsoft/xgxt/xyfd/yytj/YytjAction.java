package com.zfsoft.xgxt.xyfd.yytj;


import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.xszz.lstd.LstdForm;
import com.zfsoft.xgxt.xszz.lstd.PrintModel;
import com.zfsoft.xgxt.xyfd.fdjswh.FdjsForm;
import com.zfsoft.xgxt.xyfd.fdjswh.FdjsService;
import com.zfsoft.xgxt.xyfd.fdkcwh.fdkcsq.FdkcsqService;
import com.zfsoft.xgxt.xyfd.wfcyywh.FdyyForm;
import com.zfsoft.xgxt.xyfd.wfcyywh.FdyyService;
import common.newp.StringUtil;
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
import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by llf on 2019/8/22.
 */
public class YytjAction extends SuperAction<FdyyForm,YytjService> {

    private FdyyService fdyyService = new FdyyService();
    private FdkcsqService fdkcsqService = new FdkcsqService();
    private YytjService service = new YytjService();
    private static final String url = "xyfd_xyfd_yytj.do";

    @SystemAuth(url = url)
    public ActionForward yytjList(ActionMapping mapping, ActionForm form,
                                    HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdyyForm model = (FdyyForm)form;
        User user = getUser(request);
        if(QUERY.equalsIgnoreCase(model.getType())){
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);

            // ��ѯ
            List<HashMap<String, String>> resultList = service.getYytjList(model,user);

            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }
//        boolean canWrite = false; //�Ƿ��д
//        if(user.getUserType().equals("stu")){
//            if (fdyyService.isPb(user)){//��־Ը��
//                canWrite = true;
//            }
//        }else {
//            if(!fdkcsqService.isAdmin(user)){ //���ǹ���ԱҲ���Ǹ���������Ա
//                if("fdy".equalsIgnoreCase(user.getUserStatus())){ //�Ǹ���Ա
//                    canWrite = true;
//                }else {
//                    if(fdyyService.isJs(user)){ //�ǵǼǵĸ�����ʦ
//                        canWrite = true;
//                    }
//                }
//            }else {
//                canWrite = true;
//            }
//        }
//        request.setAttribute("canWrite",canWrite);

        String path = "xyfd_xyfd_yytj.do";
        request.setAttribute("path", path);
//        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("yytjList");
    }

    @SystemAuth(url = url)
    public ActionForward pjtj(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdyyForm model = (FdyyForm)form;
        User user = getUser(request);
        if(QUERY.equalsIgnoreCase(model.getType())){
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            // ��ѯ
            List<HashMap<String, String>> resultList = service.getYytjList(model,user);

            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }

        String path = "xyfd_yytj.do?method=pitj";
        request.setAttribute("path", path);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("pjtj");
    }

    @SystemAuth(url = url)
    public ActionForward qxtj(ActionMapping mapping, ActionForm form,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception{
        FdyyForm model = (FdyyForm)form;
        User user = getUser(request);
        if(QUERY.equalsIgnoreCase(model.getType())){
            // ���ɸ߼���ѯ����
            CommService comService = new CommService();
            SearchModel searchModel = comService.getSearchModel(request);
            model.setSearchModel(searchModel);
            // ��ѯ
            List<HashMap<String, String>> resultList = service.getYytjList(model,user);

            JSONArray dataList = JSONArray.fromObject(resultList);
            response.getWriter().print(dataList);
            return null;
        }

        String path = "xyfd_yytj.do?method=qxtj";
        request.setAttribute("path", path);
//        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("qxtj");
    }

    /**
     * ������ϸ����ͳ��
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward grxxpj(ActionMapping mapping, ActionForm form,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        FdyyForm model = (FdyyForm) form;
        String djh = request.getParameter("djh");
        if(StringUtil.isNull(djh)){
            request.setAttribute("errMsg","�ǼǺŲ���Ϊ��");
            request.getRequestDispatcher("errmsg.jsp").forward(request,response);
            return null;
        }
        HashMap<String,String> pjtjxx = service.getPjtj(djh); //������ʦ����ͳ����Ϣ
        request.setAttribute("pjtjxx",pjtjxx);
        List<HashMap<String,String>> pjList = service.getPjList(djh); //��ʷ���ۼ�¼
        request.setAttribute("pjList",pjList);
        request.setAttribute("size",pjList.size()+1);
        ArrayList<Double> sclspf = new ArrayList<>(); //���ʮ����ʷ����
        for(int i=9;i>=0;i--){
            if(i<pjList.size()){
                if(!StringUtil.isNull(pjList.get(i).get("pf"))){
                    sclspf.add(Double.parseDouble(pjList.get(i).get("pf")));
                }else {
                    sclspf.add(0.0);
                }
            }else {
                sclspf.add(0.0);
            }
        }
        JSONArray dataList = JSONArray.fromObject(sclspf);
        request.setAttribute("dataList",dataList);
        List<HashMap<String,String>> xjpfList = service.getXjpftj(djh);//���Ǽ�����ͳ��
        ArrayList<Integer> xjpfcs = new ArrayList<>(); //���Ǽ�����
        double j = 0.0;
        while (j<=5.0){
            boolean flag = false;
            for(int i=0;i<xjpfList.size();i++){
                String pf = xjpfList.get(i).get("pf");
                if(Double.parseDouble(pf)==j){
                    xjpfcs.add(Integer.parseInt(xjpfList.get(i).get("ljcs")));
                    flag = true;
                    break;
                }
            }
            if(!flag){
                xjpfcs.add(0);
            }
            j += 0.5;
        }
        JSONArray dataList2 = JSONArray.fromObject(xjpfcs);
        request.setAttribute("dataList2",dataList2);
        return mapping.findForward("grxxpj");
    }

    /**
     * ������Աȡ�����ͳ��
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward grqvqk(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        FdyyForm model = (FdyyForm) form;
        String djh = request.getParameter("djh");
        if(StringUtil.isNull(djh)){
            request.setAttribute("errMsg","�ǼǺŲ���Ϊ��");
            request.getRequestDispatcher("errmsg.jsp").forward(request,response);
            return null;
        }
        HashMap<String,String> qxtj = service.getJsqxxx(djh); //ԤԼȡ���ϼ�
        request.setAttribute("qxtj",qxtj);

        List<HashMap<String,String>> qxjlList = service.getQxjl(djh);//ԤԼȡ����¼
        request.setAttribute("qxjlList",qxjlList);
        request.setAttribute("size",qxjlList.size()+1);

        List<HashMap<String,String>> qxyytj = service.getQxyytj(djh);//��ȡ��ԭ��ͳ��
        ArrayList<Integer> qxyycs = new ArrayList<>();
        for(int j=1;j<=5;j++){
            boolean flag = false;
            for(int i=0;i<qxyytj.size();i++){
                String qxyy = qxyytj.get(i).get("qxyy");
                if(qxyy.equals(""+j)){
                    qxyycs.add(Integer.parseInt(qxyytj.get(i).get("qxcs")));
                    flag = true;
                    break;
                }
            }
            if(!flag){
                qxyycs.add(0);
            }
        }
        request.setAttribute("qxyycs",qxyycs);
        return mapping.findForward("grqvqk");
    }
}
