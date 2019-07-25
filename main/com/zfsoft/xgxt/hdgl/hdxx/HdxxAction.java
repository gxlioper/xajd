/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
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
 * @className	： HdxxAction
 * @description	： 活动信息action(描述这个类的作用)
 * @author 		：柳俊（1282）
 * @date		： 2018-1-31 上午11:49:09
 * @version 	V1.0 
 */

public class HdxxAction extends SuperAction<HdxxForm, HdxxService> {
	private HdxxService service = new HdxxService();
	private static final String url = "hdgl_hdgl_hdxx.do";
	//学生基本信息配置
	private static List<HashMap<String, String>> jbxxList = null;

	public static String HDBL = "hdbl";

	static {
		BdpzService bdpzService = new BdpzService();
		// 学生基本信息显示配置
		jbxxList = bdpzService.getJbxxpz(HDBL);
	}
	/**
	 * @description	： 活动信息列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-2-1 上午10:54:36
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
			// 查询
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
	 * 最新活动用于首页显示
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
		// 查询
		List<HashMap<String, String>> resultList = service.zxHdxxList(model, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @description	： 获取活动信息
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-2-1 上午10:55:34
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
	 * 个人报名验证
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
	 * 组队报名验证
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
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("hdid",model.getHdid());
		List<HashMap<String,String>> jldxList = service.getBtZdList(model.getHdid());
		request.setAttribute("jldxList",jldxList);//简历单项字段信息
		String lx = request.getParameter("lx");//报名方式：个人活动报名，队长创建队伍报名，队员加入队伍报名
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
	 * 上传照片
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
	 * @description	： 保存报名
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-2-1 上午11:46:33
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
	 * @description	： 阶段填写
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-2-6 下午04:23:27
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
	 * @description	： 保存学生阶段填写
	 * @author 		： lj（1282）
	 * @date 		：2018-2-7 下午02:47:00
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
	 * @description	： 创建组队
	 * @author 		： lj（1282）
	 * @date 		：2018-2-9 下午02:29:41
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
		model.setDwzw("1");//1：队长   2：成员
        HdxxForm hdxxForm = service.getModel(model);

		if((xhs.length+1)>Integer.parseInt(hdxxForm.getDwrs())){
            String msg = "队友人数超出队伍上限！";
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
	 * 创建队伍验证
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
	 * @description	： 获取队伍列表
	 * @author 		： lj（1282）
	 * @date 		：2018-2-10 下午03:14:41
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
	 * @description	： 加入队伍
	 * @author 		： lj（1282）
	 * @date 		：2018-2-10 下午03:44:33
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
	 * @description	： 阶段填写
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-2-6 下午04:23:27
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
	 * @description	：活动评论
	 * @author 		： lj（1282）
	 * @date 		：2018-3-2 下午04:48:16
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
	 * @description	： 保存评论
	 * @author 		： lj（1282）
	 * @date 		：2018-3-5 上午10:48:09
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
	 * @description	： 查看阶段
	 * @author 		： lj（1282）
	 * @date 		：2018-3-15 下午02:53:59
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
	 * @description	： 查看组队阶段
	 * @author 		： lj（1282）
	 * @date 		：2018-3-26 下午03:39:45
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
        //阶段信息
        List<HashMap<String,String>> jdxx = service.getJdxx(model.getHdid());
        request.setAttribute("jdxxList", jdxx);
        request.setAttribute("data", data);
        return mapping.findForward("ckHdxx");
    }
    /**
     * @description	： 设置活动信息
     * @author 		： lj（1282）
     * @date 		：2018-4-2 下午04:50:59
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward szHdxx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		HdxxForm model = (HdxxForm) form;
		//活动类型列表
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

		//判断是否可以设置
		boolean result = service.sfJdsz(model);
		request.setAttribute("sfksz", result ? "1" : "2");
		List<HashMap<String, String>> hdjdLists = service.getHdjdLists(model);
		request.setAttribute("hdjdList", hdjdLists);
		List<HashMap<String, String>> hdjxList = service.getHdjxs(model);
		request.setAttribute("hdjxList", hdjxList);

		//活动类型列表
		List<HashMap<String, String>> hdlxList = hdblsqshService.getHdlxList();
		request.setAttribute("hdlxList", hdlxList);


		/*活动标签列表*/
		HdbljgService hdbljgService = new HdbljgService();
		List<HashMap<String, String>> activityLabelList = hdbljgService.getHdbqList();
		request.setAttribute("activityLabelList", activityLabelList);

		/*讲座类型列表*/
		List<HashMap<String,String>> jzlxList = hdbljgService.getJzlxList();
		request.setAttribute("jzlxList", jzlxList);

		//自选课程列表
		List<HashMap<String,String>> zxckclxList = hdbljgService.getZxkcDmList();
		request.setAttribute("zxckclxList", zxckclxList);

		/*能力标签*/
		List<HashMap<String,String>> abilityLabelList = hdbljgService.getAbilityLabelList();
		request.setAttribute("abilityLabelList", abilityLabelList);

		/*主讲人职称*/
		List<HashMap<String, String>> zjrzcList = hdblsqshService.getZjrzcList();
		request.setAttribute("zjrzcList", zjrzcList);
		return mapping.findForward("szHdxx");
	}


	
	/**
	 * @description	： 保存设置
	 * @author 		： lj（1282）
	 * @date 		：2018-4-3 下午04:46:04
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
	 * @description	： 学生成绩单
	 * @author 		： CP（1352）
	 * @date 		：2018-4-9 下午05:01:47
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
			//加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			//取具体活动
			List<List<HashMap<String,String>>> list=new ArrayList<List<HashMap<String,String>>>();//总的
			List<HashMap<String, String>> hdList = service.getHdlistByXh(model.getXh());
			String curr_lx=hdList.get(0).get("hdlxmc");//标记类型
			List<HashMap<String,String>> lxlist=new ArrayList<HashMap<String,String>>();//标记类型集合
			for(int i=0;i<hdList.size();i++){
				if(!curr_lx.equals(hdList.get(i).get("hdlxmc"))){//判断当前类型与迭代类型是否相同
					list.add(lxlist);
					lxlist=new ArrayList<HashMap<String,String>>();
					curr_lx=hdList.get(i).get("hdlxmc");
				}
				lxlist.add(hdList.get(i));
				if(i==hdList.size()-1){//最后一个
					list.add(lxlist);
				}
			}
			request.setAttribute("hdList", hdList);
			request.setAttribute("list", list);
			
			//取具体活动的学分
			List<HashMap<String, String>> hdxfList = service.getHdXflistByXh(model.getXh());
			request.setAttribute("hdxfList", hdxfList);
			
			//取活动总学分的年级排名
			HashMap<String,String> njpmMap = service.getNjPmByXh(model.getXh());
			request.setAttribute("njpmMap", njpmMap);
		}
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("xscjd");
	}
	
	
	/**
	 * @description	： 取学生4个100信息完成图
	 * @author 		： CP（1352）
	 * @date 		：2018-4-12 下午05:19:51
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
	 * @description	： 学生参加活动(获得能力)雷达图
	 * @author 		： CP（1352）
	 * @date 		：2018-4-12 下午08:20:50
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
     * 通过大厅活动id获取报名人员
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