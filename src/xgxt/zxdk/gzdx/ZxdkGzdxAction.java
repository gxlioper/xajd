package xgxt.zxdk.gzdx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.pjpy.zjlg.ZjlgPjpyUnit;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.zjlg.WjcfZjlgActionForm;
import xgxt.wjcf.zjlg.WjcfZjlgModel;
import xgxt.wjcf.zjlg.WjcfZjlgService;

import com.zfsoft.basic.BasicAction;
import common.Globals;


/**
 * 
 *助学贷款数据维护Action
 *
 */
public class ZxdkGzdxAction extends BasicAction {

	
	
	/**
	 * 助学贷款数据维护页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxdkSjwhQuery(ActionMapping mapping,ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String title = "助学贷款 - 基本数据维护 - 助学贷款数据维护";
		request.setAttribute("title", title);
		ZxdkGzdxActionForm zxdkForm = (ZxdkGzdxActionForm) form;
		String userType = request.getSession().getAttribute("userType") != null ? request
				.getSession().getAttribute("userType").toString()
				: "";
				if (Fdypd.isFdy(request.getSession().getAttribute("userName").toString())) {
					userType = "fdy";
				}
				String userName = request.getSession().getAttribute("userName").toString();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		if ("xy".equalsIgnoreCase(userType)) {
			zxdkForm.setXydm(request.getSession().getAttribute("userDep").toString());
		}
		String operType = request.getParameter("operType");
		//if ("query".equalsIgnoreCase(operType)) {//选择"查询"才显示结果
			ZxdkGzdxModel model = new ZxdkGzdxModel();
			BeanUtils.copyProperties(model, zxdkForm);
			ZxdkGzdxService service = new ZxdkGzdxService();
			topTr = service.queryZxdkxxTitle();//查询助学贷款数据维护表头
			rs = service.queryZxdkxxResult(zxdkForm, model, userType, userName);//查询助学贷款数据结果
			int count = service.queryZxdkxxResultNum(userType, model, userName);
			zxdkForm.getPages().setMaxRecord(Integer.parseInt(String.valueOf(count)));//设置最大的记录数
		//}
		ZjlgPjpyUnit unit = new ZjlgPjpyUnit();
		//对用户读写权进行判断
		request.setAttribute("path", "zxdk_gzdx_sjwh.do");
		unit.commonRequestSet(request, "xg_view_gzdx_zxdk_xszxdkb", "xg_gzdx_zxdk_xszxdkb",  rs, topTr);
		//加载学院，专业，班级下拉列表
		setNjXyZyBjList(request, zxdkForm);
		request.setAttribute("userType", userType);
		zxdkForm.setXm(DealString.toGBK(zxdkForm.getXm()));
		zxdkForm.setXh(DealString.toGBK(zxdkForm.getXh()));
		zxdkForm.setZxdkmc(DealString.toGBK(zxdkForm.getZxdkmc()));
		return mapping.findForward("zxdkSjwhQuery");
	}
	/**
	 * 加载学院，年级，专业，班级下拉列表值
	 * @param request
	 * @param myForm
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public void setNjXyZyBjList(HttpServletRequest request,
			ZxdkGzdxActionForm myForm) throws Exception,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		// 在request保存年级学院专业班级List的方法
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName = session.getAttribute("userName").toString();
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();
		//
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;
		//
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}

		String bjKey = xy + "!!" + zy + "!!" + nj;
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());//学期列表
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		if (session.getAttribute("fdyQx").toString() != null
				&& "true".equalsIgnoreCase(session.getAttribute("fdyQx")
						.toString())) {
			// 辅导员登录
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));// 发送班级列表
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));// 发送班级列表
		}

	}
	
	/**
	 * 增加助学贷款数据维护信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addZxdkSjwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String title = "助学贷款 - 基本数据维护 - 助学贷款数据维护 - 增加";
		request.setAttribute("title", title);
		ZxdkGzdxActionForm zxdkForm = (ZxdkGzdxActionForm) form;
		String pkValue = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String zxdkmc = request.getParameter("zxdkmc");
		String pk = pkValue+xn+zxdkmc;
		ZxdkGzdxService service = new ZxdkGzdxService();
		HashMap<String, String> rs = service.queryZxdkxxOne(pkValue);
		String operType = request.getParameter("operType");
		
		if ("save".equalsIgnoreCase(operType)) {
			ZxdkGzdxModel model = new ZxdkGzdxModel();
			BeanUtils.copyProperties(model, zxdkForm);
			//判断要新增的信息是否存在
			HashMap<String, String> jg = service.queryAddZxdk(pk);
			if(jg.size()==0){//不存在执行新增操作
				boolean result = service.saveZxdkxx(model);
				if (result) {
					request.setAttribute("inserted", "yes");
				} else {
					request.setAttribute("inserted", "no");
				}
			}else{
				request.setAttribute("inserted", "no");
			}
			
		}
		
		request.setAttribute("rs", rs);
		//		加载学院，专业，班级下拉列表
		setNjXyZyBjList(request, zxdkForm);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("addZxdkSjwh");
	}
	
	/**
	 * 查询助学贷款数据跳转
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxdkSjwhxxQueryOpen(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String title = "助学贷款 - 基本数据维护 - 助学贷款数据维护";
		request.setAttribute("title", title);
		return mapping.findForward("zxdkSjwhxxQueryOpen");
	}
	/**
	 * 查询助学贷款数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxdkSjwhxxQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZxdkGzdxActionForm zxdkForm = (ZxdkGzdxActionForm) form;
		FormModleCommon.formToGBK(zxdkForm);
		String userType = request.getSession().getAttribute("userType").toString();
		if (Fdypd.isFdy(request.getSession().getAttribute("userName").toString())) {
			userType = "fdy";
		}
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String zxdkmc = zxdkForm.getZxdkmc();
		if ("xy".equalsIgnoreCase(userType) || "fdy".equalsIgnoreCase(userType)) {
			zxdkForm.setXydm(userDep);
		}
		String dkje_ks=request.getParameter("dkje_ks");
		String dkje_js=request.getParameter("dkje_js");
		zxdkForm.setDkje_ks(dkje_ks);
		zxdkForm.setDkje_js(dkje_js);
		ZxdkGzdxService service = new ZxdkGzdxService();
		List<HashMap<String, String>> topTr = service.queryZxdkdkxxTitle();//查询学生数据数据表头
		ArrayList<String[]> rs = service.queryZxdkxxResultnotExis(zxdkForm,userType, userName);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs != null ? rs.size() : 0);
		zxdkForm.setZxdkmc(DealString.toGBK(zxdkForm.getZxdkmc()));
		return mapping.findForward("zxdkSjwhxxQuery");
	}
	/**
	 * 修改助学贷款数据维护信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateZxdkSjwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String title = "助学贷款 - 基本数据维护 - 助学贷款数据维护 - 修改";
		request.setAttribute("title", title);
		
		ZxdkGzdxActionForm zxdkForm = (ZxdkGzdxActionForm) form;
		String pkValue = request.getParameter("pkValue");
		System.out.println(pkValue);
		String operType = request.getParameter("operType");
		if ("view".equalsIgnoreCase(operType)) {//详细信息页面标题
			title = "助学贷款 - 基本数据维护 - 助学贷款数据维护 - 详细信息";
			request.setAttribute("title", title);
		}
		ZxdkGzdxService service = new ZxdkGzdxService();
		//保存数据操作
		if (SAVE.equalsIgnoreCase(operType)) {
			ZxdkGzdxModel model = new ZxdkGzdxModel();
			BeanUtils.copyProperties(model, zxdkForm);
			boolean result = service.updateZxdkSjwh(pkValue, model);
			appendOperResult(request, result);
		}
		HashMap<String, String> rs = service.viewZxdkxx(pkValue);
		zxdkForm.setXn(rs.get("xn"));
		zxdkForm.setZxdkmc(rs.get("zxdkmc"));
		zxdkForm.setDkje(rs.get("dkje"));
		zxdkForm.setBz(rs.get("bz"));
		request.setAttribute("rs", rs);
		setNjXyZyBjList(request, zxdkForm);
		request.setAttribute("writable", operType);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("updateZxdkSjwh");
	}
	/**
	 * 在REQUEST中存放数据操作后的结果
	 * @param request
	 * @param result
	 */
	public void appendOperResult(HttpServletRequest request, boolean result) {
		if (result) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
	} 
	/**
	 * 删除助学贷款信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delZxdkSjwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String[] pkValue = request.getParameterValues("cbv");
		ZxdkGzdxService service = new ZxdkGzdxService();
		boolean result = false;
		result = service.delZxdkSjwh(pkValue);
		if (result) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
		}
		zxdkSjwhQuery(mapping, form, request, response);
		return mapping.findForward("zxdkSjwhQuery");
	}
	
}
