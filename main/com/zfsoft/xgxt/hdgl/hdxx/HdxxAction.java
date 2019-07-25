/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.hdgl.hdxx;

import java.net.URLDecoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.zfsoft.ms.mail.util.BooleanUtils;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.hdgl.hdxq.HdEwm;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.hdgl.hdbljg.HdbljgService;
import com.zfsoft.xgxt.hdgl.hdblsq.HdblsqshService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import xsgzgl.comm.userutils.UserUtils;

/**
 * @className	�� HdxxAction
 * @description	�� ���Ϣaction(��������������)
 * @author 		��������1282��
 * @date		�� 2018-1-31 ����11:49:09
 * @version 	V1.0 
 */

public class HdxxAction extends SuperAction<HdxxForm, HdxxService> {
	private HdxxService service = new HdxxService();
	private static final String url = "hdgl_hdgl_hdxx.do";
	//ѧ��������Ϣ����
	private static List<HashMap<String, String>> jbxxList = null;

	public static String HDBL = "hdbl";

	static {
		BdpzService bdpzService = new BdpzService();
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(HDBL);
	}
	/**
	 * @description	�� ���Ϣ�б�
	 * @author 		�� ������1282��
	 * @date 		��2018-2-1 ����10:54:36
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward hdxxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdxxForm model = (HdxxForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		User user1 = UserUtils.getUser((String)request.getSession().getAttribute("userName"));
		request.setAttribute("sy",user1.getUserSyDep());
		request.setAttribute("xy",(String)request.getSession().getAttribute("userDep"));

		if(!StringUtils.isNull(model.getPxfs())){
			request.setAttribute("pxfs", model.getPxfs());
		}
		String path = "hdgl_hdgl_hdxx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("hdxxList");
	}

	/**
	 * ���»������ҳ��ʾ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxHdxxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdxxForm model = (HdxxForm) form;

		User user = UserUtils.getUser((String)request.getSession().getAttribute("userName"));
		user.setUserDep((String)request.getSession().getAttribute("userDep"));
		// ��ѯ
		List<HashMap<String, String>> resultList = service.zxHdxxList(model, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @description	�� ��ȡ���Ϣ
	 * @author 		�� ������1282��
	 * @date 		��2018-2-1 ����10:55:34
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward getHdxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdxxForm model = (HdxxForm) form;
		User user = getUser(request);
		HashMap<String,String> data = service.getHdxx(model);
		request.setAttribute("data", data);
		return mapping.findForward("hdrybm");
	}

	/**
	 * ���˱�����֤
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward geBmCheck(ActionMapping mapping, ActionForm form,
							HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdxxForm model = (HdxxForm) form;
		User user = getUser(request);
		model.setXh(user.getUserName());
		String msg = service.yz(model.getHdid(),user);
		response.getWriter().print(getJsonMessage(msg));
		return null;
	}

	/**
	 * ��ӱ�����֤
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward gezdBmCheck(ActionMapping mapping, ActionForm form,
								   HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdxxForm model = (HdxxForm) form;
		User user = getUser(request);
		model.setXh(user.getUserName());
		String msg = service.checkBm(model,new String[]{});
		response.getWriter().print(getJsonMessage(msg));
		return null;
	}

	@SystemAuth(url = url)
	public ActionForward bm(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = getUser(request);
		HdxxForm model = (HdxxForm) form;
		if ("stu".equals(user.getUserType())) {
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("hdid",model.getHdid());
		List<HashMap<String,String>> jldxList = service.getBtZdList(model.getHdid());
		request.setAttribute("jldxList",jldxList);//���������ֶ���Ϣ
		String lx = request.getParameter("lx");//������ʽ�����˻�������ӳ��������鱨������Ա������鱨��
		request.setAttribute("lx",lx);
		String[] xhs = request.getParameterValues("xhs");
		request.getSession().setAttribute("xhs",xhs);
		request.setAttribute("xhs",JSONArray.fromObject(xhs) );

		if("jrdw".equals(lx)){
			request.setAttribute("dwid",model.getDwid());
		}
		HashMap<String,String> bmMap = service.getBmSqxx(model);
		request.setAttribute("data",bmMap);
		return mapping.findForward("bm");
	}

	/**
	 * �ϴ���Ƭ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward uploadZp(ActionMapping mapping, ActionForm form,
							HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdxxForm model = (HdxxForm) form;
		String msg = service.saveStuPic(model);
		response.getWriter().print(getJsonMessage(msg));
		return null;
	}

	/**
	 * @description	�� ���汨��
	 * @author 		�� ������1282��
	 * @date 		��2018-2-1 ����11:46:33
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward saveBm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdxxForm model = (HdxxForm) form;
		User user = getUser(request);
		model.setXh(user.getUserName());
		String msg = service.saveCheck(model);
		if("true".equals(msg)){
			boolean result = service.saveBm(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		} else {
			response.getWriter().print(getJsonMessage(msg));
		}

		return null;
	}
	
	/**
	 * @description	�� �׶���д
	 * @author 		�� ������1282��
	 * @date 		��2018-2-6 ����04:23:27
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward jdtx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdxxForm model = (HdxxForm) form;
		User user = getUser(request);
		HashMap<String,String> hdxxMap = service.getHdjdInfo(model, user);
		request.setAttribute("data", hdxxMap);
		List<HashMap<String, String>> hdjdList = service.getHdjdList(model);
		request.setAttribute("jdList", hdjdList);
		return mapping.findForward("jdtx");
	}
	
	/**
	 * @description	�� ����ѧ���׶���д
	 * @author 		�� lj��1282��
	 * @date 		��2018-2-7 ����02:47:00
	 * @return
	 */
	public ActionForward saveStudentStage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		HdxxForm model = (HdxxForm) form;
		User user = getUser(request);
		model.setXh(user.getUserName());
		String msg = service.yz(model);
		if("true".equals(msg)){
			boolean result = service.updateStudentStage(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		} else {
			response.getWriter().print(getJsonMessage(msg));
		}

		return null;
	}
	
	/**
	 * @description	�� �������
	 * @author 		�� lj��1282��
	 * @date 		��2018-2-9 ����02:29:41
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createGroup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{

		String[] xhs = (String[]) request.getSession().getAttribute("xhs");
		if(xhs==null){
			xhs = new String[]{};
		}
		HdxxForm model = (HdxxForm) form;
		User user = getUser(request);
		model.setXh(user.getUserName());
		model.setDwzw("1");//1���ӳ�   2����Ա
        HdxxForm hdxxForm = service.getModel(model);

		if((xhs.length+1)>Integer.parseInt(hdxxForm.getDwrs())){
            String msg = "�������������������ޣ�";
            response.getWriter().print(getJsonMessage(msg));
            return null;
        }
        String checkBmMsg = service.checkBm(model,xhs);
        if(!"true".equals(checkBmMsg)){
			response.getWriter().print(getJsonMessage(checkBmMsg));
			return null;
		}
		String msg = service.saveCheck(model);
        if("true".equals(msg)){
			boolean	result = service.createGroup(model,xhs);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}else {
			response.getWriter().print(getJsonMessage(msg));
		}
		return null;
	}

	/**
	 * ����������֤
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zdCheck(ActionMapping mapping, ActionForm form,
							  HttpServletRequest request, HttpServletResponse response) throws Exception{
		HdxxForm model = (HdxxForm) form;
		User user = getUser(request);
		model.setXh(user.getUserName());
		String msg = service.checkZrs(model);
		response.getWriter().print(getJsonMessage(msg));
		return null;
	}
    public ActionForward ckdw(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
        HdxxForm model = (HdxxForm) form;
        User user = getUser(request);
        if (QUERY.equalsIgnoreCase(model.getType())) {
            List<HashMap<String,String>> dwList = service.getDwList(model);
            JSONArray dataList = JSONArray.fromObject(dwList);
            response.getWriter().print(dataList);
            return null;
        }
        String dwid = model.getDwid();
//        if(StringUtil.isNull(dwid)){
            dwid = service.getDwid(model,user.getUserName());
//        }
        request.setAttribute("dwid",dwid);
        request.setAttribute("hdid", model.getHdid());
        return mapping.findForward("ckdw");
    }
	/**
	 * @description	�� ��ȡ�����б�
	 * @author 		�� lj��1282��
	 * @date 		��2018-2-10 ����03:14:41
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getDwList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		HdxxForm model = (HdxxForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			List<HashMap<String,String>> dwList = service.getDwList(user.getUserName(),model.getHdid());
			JSONArray dataList = JSONArray.fromObject(dwList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("hdid", model.getHdid());
		return mapping.findForward("showDw");
	}
	public ActionForward jrdwCheck(ActionMapping mapping, ActionForm form,
								 HttpServletRequest request, HttpServletResponse response) throws Exception{
		HdxxForm model = (HdxxForm) form;
		User user = getUser(request);
		model.setXh(user.getUserName());
		String msg = service.checkDwrs(model);
		response.getWriter().print(getJsonMessage(msg));
		return null;
	}
	/**
	 * @description	�� �������
	 * @author 		�� lj��1282��
	 * @date 		��2018-2-10 ����03:44:33
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jrdw(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		HdxxForm model = (HdxxForm) form;
		User user = getUser(request);
		model.setXh(user.getUserName());
		boolean	result = service.jrzd(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));

		return null;
	}
	
	/**
	 * @description	�� �׶���д
	 * @author 		�� ������1282��
	 * @date 		��2018-2-6 ����04:23:27
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward zdjdtx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdxxForm model = (HdxxForm) form;
		User user = getUser(request);
		HashMap<String,String> hdxxMap = service.getHdjdInfoForZd(model, user);
		request.setAttribute("data", hdxxMap);
		List<HashMap<String, String>> hdjdList = service.getHdjdList(model);
		request.setAttribute("jdList", hdjdList);
		return mapping.findForward("jdtx");
	}
	
	/**
	 * @description	�������
	 * @author 		�� lj��1282��
	 * @date 		��2018-3-2 ����04:48:16
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward hdpl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdxxForm model = (HdxxForm) form;
		User user = getUser(request);
		HashMap<String, String> plnrByUser = service.getPlnrByUser(model, user);
		if(null != plnrByUser){
			model.setPlid(plnrByUser.get("plid"));
			model.setPlnr(plnrByUser.get("plnr"));
		}
		request.setAttribute("hdmc", URLDecoder.decode(model.getHdmc(), "UTF-8"));
		return mapping.findForward("hdpl");
	}
	
	/**
	 * @description	�� ��������
	 * @author 		�� lj��1282��
	 * @date 		��2018-3-5 ����10:48:09
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward savePl(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdxxForm model = (HdxxForm) form;
		User user = getUser(request);
		boolean result;
		if(StringUtils.isNull(model.getPlid())){
			result = service.insertPl(model, user); 
		}else{
			result = service.updatePlnr(model);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @description	�� �鿴�׶�
	 * @author 		�� lj��1282��
	 * @date 		��2018-3-15 ����02:53:59
	 * @return
	 */
	public ActionForward ckjd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdxxForm model = (HdxxForm) form;
		User user = getUser(request);
		//String parameter = request.getParameter("prejdid");
		//System.out.println(parameter);
		model.setXh(user.getUserName());
		HashMap<String,String> hdxxMap = service.getNextHdInfo(model);
		request.setAttribute("data", hdxxMap);
		List<HashMap<String, String>> hdjdList = service.getHdjdList(model);
		request.setAttribute("jdList", hdjdList);
		request.setAttribute("UserName", user.getUserName());
		return mapping.findForward("ckjd");
	}
	
	/**
	 * @description	�� �鿴��ӽ׶�
	 * @author 		�� lj��1282��
	 * @date 		��2018-3-26 ����03:39:45
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ckjdzd(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HdxxForm model = (HdxxForm) form;
		User user = getUser(request);
		model.setXh(user.getUserName());
		HashMap<String,String> hdxxMap = service.getNextHdInfoForZd(model);
		request.setAttribute("data", hdxxMap);
		List<HashMap<String, String>> hdjdList = service.getHdjdList(model);
		request.setAttribute("jdList", hdjdList);
		return mapping.findForward("ckjd");
	}

    public ActionForward ckHdxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HdxxForm model = (HdxxForm) form;
        HashMap<String,String> data = service.getHdxx(model);
        //�׶���Ϣ
        List<HashMap<String,String>> jdxx = service.getJdxx(model.getHdid());
        request.setAttribute("jdxxList", jdxx);
        request.setAttribute("data", data);
        return mapping.findForward("ckHdxx");
    }
    /**
     * @description	�� ���û��Ϣ
     * @author 		�� lj��1282��
     * @date 		��2018-4-2 ����04:50:59
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward szHdxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		HdxxForm model = (HdxxForm) form;
		//������б�
		HdblsqshService hdblsqshService = new HdblsqshService();

		HashMap<String,String> hdxxMap = service.getHdxx(model);
		if(!hdxxMap.isEmpty()){
			String nlbq = hdxxMap.get("nlbq");
			if(StringUtils.isNotNull(nlbq)) model.setNlbqs(nlbq.split(","));
			BeanUtils.copyProperties(model,hdxxMap);
		}
		request.setAttribute("data", hdxxMap);
		List<String> hdbq = service.getHdbq(model.getHdid());
		if(hdbq != null){
			String[] bqArray = hdbq.toArray(new String[]{});
			model.setHdbqs(bqArray);
		}

		//�ж��Ƿ��������
		boolean result = service.sfJdsz(model);
		request.setAttribute("sfksz", result ? "1" : "2");
		List<HashMap<String, String>> hdjdLists = service.getHdjdLists(model);
		request.setAttribute("hdjdList", hdjdLists);
		List<HashMap<String, String>> hdjxList = service.getHdjxs(model);
		request.setAttribute("hdjxList", hdjxList);

		//������б�
		List<HashMap<String, String>> hdlxList = hdblsqshService.getHdlxList();
		request.setAttribute("hdlxList", hdlxList);


		/*���ǩ�б�*/
		HdbljgService hdbljgService = new HdbljgService();
		List<HashMap<String, String>> activityLabelList = hdbljgService.getHdbqList();
		request.setAttribute("activityLabelList", activityLabelList);

		/*���������б�*/
		List<HashMap<String,String>> jzlxList = hdbljgService.getJzlxList();
		request.setAttribute("jzlxList", jzlxList);

		//��ѡ�γ��б�
		List<HashMap<String,String>> zxckclxList = hdbljgService.getZxkcDmList();
		request.setAttribute("zxckclxList", zxckclxList);

		/*������ǩ*/
		List<HashMap<String,String>> abilityLabelList = hdbljgService.getAbilityLabelList();
		request.setAttribute("abilityLabelList", abilityLabelList);

		/*������ְ��*/
		List<HashMap<String, String>> zjrzcList = hdblsqshService.getZjrzcList();
		request.setAttribute("zjrzcList", zjrzcList);
		return mapping.findForward("szHdxx");
	}


	
	/**
	 * @description	�� ��������
	 * @author 		�� lj��1282��
	 * @date 		��2018-4-3 ����04:46:04
	 * @return
	 */
	public ActionForward saveSz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		HdxxForm model = (HdxxForm) form;
		boolean result = service.szHdjd(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * @description	�� ѧ���ɼ���
	 * @author 		�� CP��1352��
	 * @date 		��2018-4-9 ����05:01:47
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xscjdPri(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HdxxForm model = (HdxxForm) form;
		User user = getUser(request);
		if ("stu".equals(user.getUserType())){
			model.setXh(user.getUserName());
		}
		if (!StringUtil.isNull(model.getXh())){
			request.setAttribute("xh", model.getXh());
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//ȡ����
			List<List<HashMap<String,String>>> list=new ArrayList<List<HashMap<String,String>>>();//�ܵ�
			List<HashMap<String, String>> hdList = service.getHdlistByXh(model.getXh());
			String curr_lx=hdList.get(0).get("hdlxmc");//�������
			List<HashMap<String,String>> lxlist=new ArrayList<HashMap<String,String>>();//������ͼ���
			for(int i=0;i<hdList.size();i++){
				if(!curr_lx.equals(hdList.get(i).get("hdlxmc"))){//�жϵ�ǰ��������������Ƿ���ͬ
					list.add(lxlist);
					lxlist=new ArrayList<HashMap<String,String>>();
					curr_lx=hdList.get(i).get("hdlxmc");
				}
				lxlist.add(hdList.get(i));
				if(i==hdList.size()-1){//���һ��
					list.add(lxlist);
				}
			}
			request.setAttribute("hdList", hdList);
			request.setAttribute("list", list);
			
			//ȡ������ѧ��
			List<HashMap<String, String>> hdxfList = service.getHdXflistByXh(model.getXh());
			request.setAttribute("hdxfList", hdxfList);
			
			//ȡ���ѧ�ֵ��꼶����
			HashMap<String,String> njpmMap = service.getNjPmByXh(model.getXh());
			request.setAttribute("njpmMap", njpmMap);
		}
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("xscjd");
	}
	
	
	/**
	 * @description	�� ȡѧ��4��100��Ϣ���ͼ
	 * @author 		�� CP��1352��
	 * @date 		��2018-4-12 ����05:19:51
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getviewWct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		String xh = request.getParameter("xh");
		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("ds",service.getdsCount(xh));
		dataMap.put("js",service.getjsCount(xh));
		dataMap.put("jz",service.getjzCount(xh));
		dataMap.put("hd",service.gethdCount(xh));
		JSONObject dataMapJson = JSONObject.fromObject(dataMap);
		response.getWriter().print(dataMapJson);
		return null;
	}
	/**
	 * @description	�� ѧ���μӻ(�������)�״�ͼ
	 * @author 		�� CP��1352��
	 * @date 		��2018-4-12 ����08:20:50
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getLDWct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception{
		String xh = request.getParameter("xh");
		HashMap<String, Object> dataMap = new HashMap<String, Object>();
		List<HashMap<String, String>> hdgsList = service.getHdslistByXh(xh);
		request.setAttribute("hdgsList", hdgsList);
		dataMap.put("hdgsList",hdgsList);
		JSONObject dataMapJson = JSONObject.fromObject(dataMap);
		response.getWriter().print(dataMapJson);
		return null;
	}
	public ActionForward getBmEwm(ActionMapping mapping, ActionForm form,
								HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		HdxxForm model = (HdxxForm)form;
        request.setAttribute("hdid",model.getHdid());
		if (QUERY.equalsIgnoreCase(model.getType())) {
			JSONObject jobj = new JSONObject();
			HdEwm hdEwm = new HdEwm();
			StringBuilder url = new StringBuilder();
			url.append(request.getScheme());
			url.append("://" + request.getServerName());
			url.append(":" + request.getServerPort());
			url.append(request.getContextPath());
			url.append("/hdgl_hdxx.do?method=getHdxx&hdid=");
			url.append( model.getHdid());
			String code = hdEwm.getEwm(url.toString());
			code = "data:image/png;base64,"+code;
			jobj.put("base64_png",code);
			jobj.put("bmlj",url.toString());
			response.getWriter().print(jobj);
			return null;
		}

		return mapping.findForward("getBmEwm");
	}

    /**
     * ͨ�������id��ȡ������Ա
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	public ActionForward getBmRyList(ActionMapping mapping, ActionForm form,
								  HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		String dthdid = request.getParameter("dthdid");
		if(StringUtil.isNull(dthdid)){
		    return null;
        }
		List<HashMap<String,String>> result = service.getBmRys(dthdid);
        JSONArray dataList = JSONArray.fromObject(result);
        response.getWriter().print(dataList);
		return null;
	}
}