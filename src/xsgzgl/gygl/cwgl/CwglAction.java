package xsgzgl.gygl.cwgl;

import java.io.File;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.gyglry.GyglryService;
import xsgzgl.gygl.qsgl.QsglService;

import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 公寓管理_床位维护action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author sjf
 * @version 1.0
 */
public class CwglAction extends BasicExtendAction{
	/**
	 * 床位管理主页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-房源管理-床位信息管理-{doType}维护PK:{primarykey_checkVal}")
	public ActionForward cwglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", "gyglnew_cwgl_cwgl.do");
		CwglService service = new CwglService();
		
		String doType = request.getParameter("doType");
		CwglForm myForm = (CwglForm)form;
		CwglModel model = new CwglModel();
		BeanUtils.copyProperties(model, myForm);
		
		// 删除操作
		if("del".equalsIgnoreCase(doType)){
			String[] pk = request.getParameterValues("primarykey_checkVal");
			String message = service.delCw(pk) ? "删除成功！" : "删除失败！";
			
			request.setAttribute("message", message);
		}else if("cwdd".equals(doType)){//床位对调
			HttpSession session = request.getSession();
			model.setCzr(session.getAttribute("userName").toString());//设置操作人
			String[] pk = request.getParameterValues("primarykey_checkVal");
			String message = service.cwdd(pk,model) ? "对调成功！" : "对调失败！";
			
			request.setAttribute("message", message);
		}
		
		User user = getUser(request);
		
		request.setAttribute("rs", service.queryCw(model,request));
		request.setAttribute("searchTj", myForm.getSearchModel());
		request.setAttribute("yrzcws", service.getYrzrs(model,user));		//当前数据集中已入住的床位数
		request.setAttribute("wrzcws", service.getWrzrs(model));		//当前数据集中未入住的床位数
		request.setAttribute("searchTjstr", service.getSearchTjstr(model));
		
		// write和titile
		setWriteAbleAndTitle(request, "gyglnew_cwgl_cwgl.do");

		request.setAttribute("topTr", service.getTopTr("cwwh"));
		request.setAttribute("realTable", "xg_gygl_new_cwxxb");	// 导入表
		request.setAttribute("tableName", "xg_gygl_new_cwxxb");	// 导出视图

		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("cwglManage");
	}
	
	/**
	 * 
	 * @描述:床位对调弹出页面
	 * @作者：Dlq [工号：995]
	 * @日期：2013-8-22 下午02:51:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemLog(description="访问公寓管理-房源管理-床位信息管理-床位对调PK:{idList}")
	public ActionForward cwDd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CwglService service = new CwglService();
		String doType = request.getParameter("doType");
		CwglForm myForm = (CwglForm)form;
		CwglModel model = new CwglModel();
		BeanUtils.copyProperties(model, myForm);
		String idList = request.getParameter("idList");
		idList = URLDecoder.decode(idList,"utf-8");
		idList.substring(0, idList.length()-1);
		request.setAttribute("idList", idList);
		
		// 删除操作
		if("cwdd".equals(doType)){
			//床位对调

			// 调整原因代码
			model.setRzyy(myForm.getTsyy());
			// 调整时间
			model.setRzsj(myForm.getTssj());
			
			HttpSession session = request.getSession();
			model.setCzr(session.getAttribute("userName").toString());//设置操作人
			String[] pk = idList.split(",");
			String message = service.cwdd(pk,model) ? "对调成功！" : "对调失败！";
			Map<String,String> map = new HashMap<String, String>();
			map.put("message", message);
			JSONObject json = JSONObject.fromObject(map); 
			response.getWriter().print(json);
			return null;
			//request.setAttribute("message", message);
		}
		request.setAttribute("tsyyList", service.getTzyyList());

		// 学年 学期
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		return mapping.findForward("cwdd");
	}
	
	/**
	 * 床位信息管理自定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward cwxxglExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		CwglService service = new CwglService();		
		CwglForm myForm = (CwglForm)form;
		CwglModel model = new CwglModel();
		BeanUtils.copyProperties(model, myForm);
		
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.queryExportCw(model,request);
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * 新增床位维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-房源管理-床位信息管理-增加LDDM:{lddm},QSH:{qsh},CWH:{cwh}")
	public ActionForward cwwhAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		
		CwglForm myForm = (CwglForm)form;
	
		CwglService service = new CwglService();
		
		// 保存新增加寝室
		if("save".equalsIgnoreCase(doType)){
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			// copy属性值
			CwglModel model = new CwglModel();
			BeanUtils.copyProperties(model, myForm);
			
			String message = service.saveCwwh(model);
			
			request.setAttribute("message", message);
		}
		
		request.setAttribute("ldList", service.getLdList());
		request.setAttribute("xxdm", Base.xxdm);
		this.saveToken(request);
		return mapping.findForward("cwwhAdd");
	}
	
	/**
	 * 床位信息修改（入住，修改学号，及床位所属）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemLog(description="访问公寓管理-房源管理-床位信息管理-修改PK:{pkValue}")
	public ActionForward cwwhModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 主键
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		CwglForm myForm = (CwglForm)form;
		CwglService service = new CwglService();
		

		String xh = request.getParameter("xh");
		HashMap<String, String> stuInfo = new HashMap<String, String>();
		
		
		if(null != xh && !"".equalsIgnoreCase(xh)){		
			stuInfo = service.getStuInfo_gy(xh);
			request.setAttribute("stuInfo", stuInfo);
		}
		if("save".equalsIgnoreCase(doType)){
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			} else {
				super.resetToken(request);
			}
			// copy属性值
			CwglModel model = new CwglModel();
			BeanUtils.copyProperties(model, myForm);
			HttpSession session = request.getSession();
			model.setCzr(session.getAttribute("userName").toString());//设置操作人
			
			String message = service.updateCwwh(pkValue, model);
			
			request.setAttribute("message", message);
		}
		//入住
		if("ruzhu".equalsIgnoreCase(doType)){
			// copy属性值
			CwglModel model = new CwglModel();
			BeanUtils.copyProperties(model, myForm);
			HttpSession session = request.getSession();
			model.setCzr(session.getAttribute("userName").toString());//设置操作人
			String message = service.ruzhu(pkValue, model);
			request.setAttribute("message", message);
		}
		request.setAttribute("rzyylist", service.getRzyyList());
		// 寝室详细信息
		request.setAttribute("rs", service.getCwForPk(pkValue));
		request.setAttribute("stuInfo", stuInfo);
		this.saveToken(request);
		return mapping.findForward("cwwhModi");
	}
	
	/**
	 * 寝室信息查看
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cwwhView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 主键
		String pkValue = request.getParameter("pkValue");
		
		CwglService service = new CwglService();
		
		// 寝室详细信息
		request.setAttribute("rs", service.getCwForPk(pkValue));
		//获取辅导员班主任信息
		request.setAttribute("fdyList", service.getFdyList(pkValue));
		
		return mapping.findForward("cwwhView");
	}
	
	/**
	 * 获取寝室号通过楼栋代码，为ajax调用
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getQshForLddm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String lddm = request.getParameter("lddm");
		String ch = request.getParameter("ch");
		
		QsglService qsglService = new QsglService();
		
		List<HashMap<String, String>> list = qsglService.getQshForLd(lddm, ch);
		
		JSONArray jsonArr = JSONArray.fromArray(list.toArray());
		
		response.getWriter().write(jsonArr.toString());
		return null;
	}
	
	/** 
	 * @描述:获取寝室长信息通过楼栋代码，为ajax调用
	 * @作者：江水才[工号：1150]
	 * @日期：2014-10-31 上午11:01:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	public ActionForward getQszInfoForLddm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String lddm = request.getParameter("lddm");
		String qsh = request.getParameter("qsh");
		
		GyglryService gyglryService = new GyglryService();
		
		HashMap<String, String> map = gyglryService.getQszInfo(lddm, qsh);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		
		response.getWriter().write(jsonObject.toString());
		return null;
	}
	
	/**
	 * 获取当前寝室的最大床位号List
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getCwhForQs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String lddm = request.getParameter("lddm");
		String qsh = request.getParameter("qsh");
		
		CwglService service = new CwglService();
		
		Map<String, String> rs =service.getCwhForQs(lddm, qsh);
		String json = JSONObject.fromObject(rs).toString();
		response.getWriter().write(json);
		
		
		return null;
	}
	
	/**
	 * 床位信息批量保留
	 * @author zhanghui
	 */
	@SystemLog(description="访问公寓管理-房源管理-床位信息管理-批量保留PK:{xhstr}")
	public ActionForward cwwhModibl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		
		CwglForm myForm = (CwglForm)form;
		CwglService service = new CwglService();
		
		if("save".equalsIgnoreCase(doType)){
			User user = getUser(request);
			String xhstr = request.getParameter("xhstr");
			String[] primarykey_checkVal = null;
			if(xhstr !=null && !"".equalsIgnoreCase(xhstr)){
				primarykey_checkVal = xhstr.split("!!splitOne!!");
			}
			myForm.setPrimarykey_checkVal(primarykey_checkVal);
			String message = service.updateCwbl(myForm,user);
			
			request.setAttribute("message", message);
		}
		// 浙江建设 预留类别
		if("12862".equals(Base.xxdm)){
			request.setAttribute("yllbList", service.getYllbList());
		}	
		request.setAttribute("xxdm", Base.xxdm);	
		return mapping.findForward("cwwhModibl");
	}
	
	/**
	 * 退宿
	 * @author zhanghui
	 */
	@SystemLog(description="访问公寓管理-房源管理-床位信息管理-退宿PK:{xh}")
	public ActionForward cwglPlts(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");	
		
		CwglService service = new CwglService();	
		CwglForm myForm = (CwglForm)form;
		// 保存退宿信息
		if("save".equalsIgnoreCase(doType)){
			String message = service.saveTsxx(myForm,request);			
			request.setAttribute("message", message);
		}		

		// 学年 学期
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		request.setAttribute("tsyyList", service.getTsyyList());
		return mapping.findForward("cwglPlts");
	} 
	
	/**
	 * @描述：学籍异动、毕业处理后的退宿(武昌首义学院个性化)
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年10月22日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	@SystemLog(description="访问公寓管理-房源管理-床位信息管理-退宿PK:{xh}")
	public ActionForward cwglPlts2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");	
		String xh = request.getParameter("xh");
		User user = getUser(request);
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		CwglService service = new CwglService();	
		CwglForm myForm = (CwglForm)form;
		if(StringUtils.isNull(xh)){
			String selected = request.getParameter("selected");
			if ("all".equals(selected)) {
				searchModel.setPath("xjyd_bycl.do");
				// 查询取得所有人数
				int counts = service.getCounts(myForm, searchModel,user);
				request.setAttribute("count", counts);

			} else {
				// 设定VALUE和人数
				String values = request.getParameter("values");
				request.setAttribute("xhstr", values);
				if (StringUtils.isNotNull(values)) {
					request.setAttribute("count", values.split(",").length);
				} else {
					request.setAttribute("count", "0");
				}
			}
			String searchTjstr=service.getSeachTj(myForm, searchModel, user);
			request.setAttribute("searchTjstr", searchTjstr);
		}
		// 保存退宿信息
		if("save".equalsIgnoreCase(doType)){
			String message = service.saveTsxx(myForm,request);		
			request.setAttribute("message", message);
		}		
		// 学年 学期
		request.setAttribute("xh", xh);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		request.setAttribute("tsyyList", service.getTsyyList());
		return mapping.findForward("cwglPlts2");
	}
	
	/**
	 * 床位所属初始化
	 * @author zhanghui
	 */
	@SystemLog(description="访问公寓管理-房源管理-床位信息管理-初始化PK:{cwStr}")
	public ActionForward cwglCwssInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");	
		
		CwglService service = new CwglService();	
		CwglForm myForm = (CwglForm)form;
		
		if("init".equalsIgnoreCase(doType)){
			String message = service.initCwss(myForm,request);			
			request.setAttribute("message", message);
		}		
		request.setAttribute("cshlxList", service.getCshlxList());
		return mapping.findForward("cwglCwssInit");
	}
	
	/**
	 * 加载楼栋信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadXsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xh = request.getParameter("xh");
		CwglService service = new CwglService();

		Map<String, String> map = service.getXsxx(xh,request);
		String json = JSONObject.fromObject(map).toString();	
		
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		return null;
	}
	
	public ActionForward getStuInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CwglForm myForm = (CwglForm)form;
		myForm.setXb(URLDecoder.decode(myForm.getXb(),"UTF-8"));
		CwglService service = new CwglService();
		
		request.setAttribute("topTr", service.getTopTr("xs"));
		request.setAttribute("rs", service.queryXs(myForm,request));
		
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
		String xb = myForm.getXb();
		request.setAttribute("xb", xb);
		return mapping.findForward("stuInfo");
	}
}
