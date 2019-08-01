package com.zfsoft.xgxt.xszz.xfjm;

import com.alibaba.fastjson.JSON;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.szdw.xgsz.CsszModel;
import common.newp.StringUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������:
 * @�๦������:
 * @����: ��ˬ [����:1730]
 * @ʱ��: 2019/7/1 20:10
 */
public class XfjmCsszAction extends SuperAction<CsszModel, XfjmCsszService> {

    private static final String KGZT_CLOSE = "0";

    private static final String url = "xszz_new_xfjm_cssz.do";

    private XfjmCsszService csszService = getService();

    @SystemAuth(url = url,rewritable= SystemAuth.ReadWrite.WRITEABLE)
    public ActionForward cssz(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        CsszModel csszForm = (CsszModel) form;

        XfjmCsszService service = getService();
        CsszModel model = service.getModel();

        if (model != null){
            if (StringUtil.isNull(csszForm.getSqkg())){
                csszForm.setSqkg(KGZT_CLOSE);
            }
            BeanUtils.copyProperties(csszForm, model);
        } else {
            csszForm.setSqkg(KGZT_CLOSE);
        }

        XtwhShlcService shlcService = new XtwhShlcService();
        // ������������������б��ȡֵͨ�÷���
        List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("xszz");
        request.setAttribute("shlcList", shlc);

        List<HashMap<String,String>> onOff = new OptionUtil().getOptions(OptionUtil.ONOFF);
        request.setAttribute("onOff", onOff);

        request.setAttribute("path", url);
        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("cssz");
    }

    public ActionForward save(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        CsszModel model = (CsszModel) form;
        User user = getUser(request);
        Map<String,Object> result = new HashMap<String, Object>();
        if("stu".equalsIgnoreCase(user.getUserType())){
            result.put("code",0);
            result.put("message","ѧ���û���Ȩ����");
            response.getWriter().print(JSON.parseObject(JSON.toJSONString(result)));
            return null;
        }
        boolean isAdd = false;
        if(StringUtils.isNull(model.getId())){
            model.setId(UniqID.getInstance().getUniqIDHash().toUpperCase());
            isAdd = true;
        }
        boolean flag = csszService.save(model,isAdd);
        if(flag){
            result.put("code",1);
            result.put("message","�����ɹ���");
            result.put("id",model.getId());
            response.getWriter().print(JSON.parseObject(JSON.toJSONString(result)));
            return null;
        }else{
            result.put("code",0);
            result.put("message","����ʧ�ܣ�");
            response.getWriter().print(JSON.parseObject(JSON.toJSONString(result)));
            return null;
        }
    }
}
