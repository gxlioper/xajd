package com.zfsoft.xgxt.rcsw.xsgzqkbb.xsgzqkybb.xyybb;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.rcsw.xsgzqkbb.xsgzqkzbb.XsgzqkZbbForm;
import com.zfsoft.xgxt.rcsw.xsgzqkbb.xsgzqkzbb.XsgzqkZbbService;
import com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbsq.XsgzzbsqService;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ����ũҵ��ѧ
 * ѧ���������ѧԺ�±���action.
 *
 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
 * @date 2018-04-13 15:14
 */
public class XsgzqkXyYbbAction extends SuperAction<XsgzqkXyYbbForm, XsgzqkXyYbbService> {

    private XsgzqkXyYbbService service = new XsgzqkXyYbbService();
    private static final String url = "rcsw_xsgzqkbb_xyybb.do";

    /**
     *  ת��ѧԺ�±����б�ҳ��.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-24 15:35
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    @SystemAuth(url = url)
    public ActionForward xyYbbList(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // Ĭ�ϸ߼���ѯ����
        SearchModel searchModel = new SearchModel();
        searchModel.setSearch_tj_xn(new String[] { Base.currXn });
        searchModel.setSearch_tj_xq(new String[] { Base.currXq });
        request.setAttribute("searchTj", searchModel);
        request.setAttribute("path", url);

        FormModleCommon.commonRequestSet(request);
        return mapping.findForward("xyYbbList");
    }

    /**
     *  ��ȡѧԺ�±����б�json����.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-17 15:25
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    @SystemAuth(url = url)
    public ActionForward getXyYbbListData(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkXyYbbForm xsgzqkXyYbbForm = (XsgzqkXyYbbForm) form;

        // ���ɸ߼���ѯ����
        CommService comService = new CommService();
        SearchModel searchModel = comService.getSearchModel(request);
        xsgzqkXyYbbForm.setSearchModel(searchModel);
        User user = getUser(request);
        // ��ѯ
        List<HashMap<String, String>> resultList = service.getPageList(xsgzqkXyYbbForm, user);
        JSONArray dataList = JSONArray.fromObject(resultList);
        response.getWriter().print(dataList);
        return null;
    }

    /**
     *  ת��ѧԺ�±�������ҳ��.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-18 16:09
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    @SystemAuth(url = url)
    public ActionForward xyYbbAdd(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkXyYbbForm xsgzqkXyYbbForm = (XsgzqkXyYbbForm) form;
        User user = getUser(request);

        xsgzqkXyYbbForm.setTbr(user.getUserName());
        xsgzqkXyYbbForm.setTbrmc(user.getRealName());
        xsgzqkXyYbbForm.setXydm(user.getUserDep());
        xsgzqkXyYbbForm.setXymc(user.getUserDepName());

        // ѧ�� ѧ��list
        xsgzqkXyYbbForm.setXn(Base.currXn);
        xsgzqkXyYbbForm.setXq(Base.currXq);

        if("xx".equals(user.getUserStatus())){
            XsgzzbsqService xsgzzbsqService = new XsgzzbsqService();
            List<HashMap<String,String>> xyList = xsgzzbsqService.getXyList();
            request.setAttribute("xyList",xyList);
        }

        request.setAttribute("xnList", Base.getXnndList());
        request.setAttribute("xqList", Base.getXqList());
        return mapping.findForward("xyYbbAdd");
    }

    /**
     *  ת��ѧԺ�±����޸�ҳ��.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-18 16:09
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward xyYbbEdit(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkXyYbbForm xsgzqkXyYbbForm = (XsgzqkXyYbbForm) form;
        User user = getUser(request);
        HashMap<String,String> xyYbb = service.getXyYbbById(xsgzqkXyYbbForm.getId());

        BeanUtils.copyProperties(xsgzqkXyYbbForm, xyYbb);

        if("xx".equals(user.getUserStatus())){
            XsgzzbsqService xsgzzbsqService = new XsgzzbsqService();
            List<HashMap<String,String>> xyList = xsgzzbsqService.getXyList();
            request.setAttribute("xyList",xyList);
        }

        request.setAttribute("xnList", Base.getXnndList());
        request.setAttribute("xqList", Base.getXqList());
        return mapping.findForward("xyYbbEdit");
    }

    /**
     *  ѧԺ�±������ӵı���.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-18 16:10
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward xyYbbSaveForAdd(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkXyYbbForm xsgzqkXyYbbForm = (XsgzqkXyYbbForm) form;
        //�ظ�����֤
        if(service.isXyYfRepeat(xsgzqkXyYbbForm)){
            //�����ظ�
            String messageKey = MessageKey.RCSW_XSGZQKBB_YBB_XYYFREPEAT;
            response.getWriter().print(getJsonMessageByKey(messageKey));
        }else{
            //�������ظ�
            boolean result = service.runInsert(xsgzqkXyYbbForm);
            String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
        }
        return null;
    }

    /**
     *  ѧԺ�±����޸ĵı���.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-18 16:10
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward xyYbbSaveForEdit(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        XsgzqkXyYbbForm xsgzqkXyYbbForm = (XsgzqkXyYbbForm) form;
        //�ظ�����֤
        if(service.isXyYfRepeat(xsgzqkXyYbbForm)){
            //�����ظ�
            String messageKey = MessageKey.RCSW_XSGZQKBB_YBB_XYYFREPEAT;
            response.getWriter().print(getJsonMessageByKey(messageKey));
        }else {
            //�������ظ�
            boolean result = service.runUpdate(xsgzqkXyYbbForm);
            String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
            response.getWriter().print(getJsonMessageByKey(messageKey));
        }
        return null;
    }

    /**
     *  ѧԺ�±�������ɾ����ɾ��ǰ���ж��Ƿ��а༶����¼�룩.
     *
     * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
     * @date 2018-04-19 17:18
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return org.apache.struts.action.ActionForward
     * @throw
     */
    public ActionForward xyYbbDel(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String values = request.getParameter("values");
        if (!StringUtil.isNull(values)){
            String[] idArr = values.split(",");
            if(service.isBjYbbDataExists(idArr)){
                //���ڰ༶��������
                String messageKey = MessageKey.RCSW_XSGZQKBB_YBB_BJYBBDATAEXISTS;
                response.getWriter().print(getJsonMessageByKey(messageKey));
            }else {
                //�����ڰ༶��������
                int num = service.runDelete(idArr);
                boolean result =  num > 0;
                String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num)
                        : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
                response.getWriter().print(getJsonMessage(message));
            }
        } else {
            throw new SystemException(MessageKey.SYS_DEL_NULL);
        }
        return null;
    }
    public ActionForward getJsdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
    	XsgzqkXyYbbForm myForm = (XsgzqkXyYbbForm) form;
		File wordFile = getJsdjWord(myForm,request);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	private File getJsdjWord(XsgzqkXyYbbForm myForm,HttpServletRequest request) throws Exception{
		XsgzqkXyYbbService xsgzqkXyYbbService = new XsgzqkXyYbbService();
		User user = getUser(request);
		File file = null;
		Map<String,Object> data = new HashMap<String,Object>();
		String ids = myForm.getId();
		String zsh = xsgzqkXyYbbService.getzsh(ids);
		int zs = Integer.parseInt(zsh);
		
		HashMap<String,String> xyYbb = service.getXyYbbById(ids);
		data.put("xyYbb", xyYbb);
		String ybN = "";
		String ybY = "";
		if(xyYbb != null && xyYbb.get("yf") != null){
			String ybNy = xyYbb.get("yf").replace("��", "").replace("��", "");
			ybN = ybNy.substring(0, 4);
			ybY = ybNy.substring(4, 6);
		}
		data.put("ybN", ybN);
		data.put("ybY", ybY);

		String tbN = "";
		String tbY = "";
		String tbR = "";
		if(xyYbb != null && xyYbb.get("tbrq") != null){
			String[] tbrq = xyYbb.get("tbrq").split("-");
			tbN = tbrq[0];
			tbY = tbrq[1];
			tbR = tbrq[2];
		}
		data.put("tbN", tbN);
		data.put("tbY", tbY);
		data.put("tbR", tbR);
		
		if(zs>0){
			int cnt = 15;
			List<HashMap<String,String>> fdybsbList = xsgzqkXyYbbService.getFdybsbList(ids);
			data.put("fdybsbList", fdybsbList);
			int size1=(15 - fdybsbList.size())<0?0:(15 - fdybsbList.size());
			data.put("blankList1", getBlankList(size1));
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//rcsw","fdygzybb.xml","����Ա�±���");
			return file;
		}else{
			file = FreeMarkerUtil.getWordFile(data,"classpath://templates//rcsw","fdybsbnull.xml","����Ա�±���");
			return file;
		}
	}
	public List<HashMap<String,String>> getBlankList(int size){
		
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>(size);
		
		for (int i = 0 ; i < size ; i++){
			list.add(new HashMap<String, String>());
		}
		
		return list;
	}

}
