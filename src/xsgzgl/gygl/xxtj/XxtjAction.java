package xsgzgl.gygl.xxtj;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.xszz.zzxmjg.ZzxmjgService;

import xgxt.action.Base;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.User;
import xgxt.gygl.gyfzr.gyfdy.GyglGyfdyService;
import xsgzgl.gygl.comm.GyglNewInit;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 公寓管理_信息统计action类
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
public class XxtjAction extends BasicExtendAction{
	/**
	 * 信息统计管理主页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xxtjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XxtjForm myForm = (XxtjForm)form;
		
		XxtjService service = new XxtjService();
		
		XxtjModel model = new XxtjModel();
		BeanUtils.copyProperties(model, myForm);
		
		request.setAttribute("rs", service.getLdqscwTj(model));
		request.setAttribute("xqList", service.getXqxx());
		request.setAttribute("model", model);
	
		request.setAttribute("topTr", service.getTopTr("xxtj"));
		// 设置writeAbel和title
		setWriteAbleAndTitle(request, "gyglnew_xxtj_xxtj.do");
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("xqbj", GyglNewInit.XQBJ);
		return mapping.findForward("xxtjManage");
	}
	
	/**
	 * 信息统计详细
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xxtjDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String lddm  = request.getParameter("pkValue");
		XxtjService service = new XxtjService();
		User user = getUser(request);
		LdModel ldModel = service.getLdDetailTj(lddm,user);
		
		request.setAttribute("ldModel", ldModel);
		return mapping.findForward("xxtjDetail");
	}
	
	/**
	 * 统计单个寝室的信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xxtjForQs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String lddm = request.getParameter("lddm");
		String qsh = request.getParameter("qsh");
		User user = getUser(request);
		XxtjService service = new XxtjService();
		
		
		List<HashMap<String, String>> rs = service.xxtjForQs(lddm, qsh);
		//验证寝室学生是否含用户所带学生
		if (!service.CheckYhqx(rs,user) && !"12673".equals(Base.xxdm)) {
			response.getWriter().print(getJsonMessage("false"));
			return null;
		}
		String json = JSONArray.fromObject(rs).toString();
		response.setCharacterEncoding("gbk");
		response.getWriter().write(json);
		
		return null;
	}
	public ActionForward xxtjDczsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{

		String lddm = request.getParameter("lddm");
		XxtjService service = new XxtjService();
		String fileName = new String("楼栋住宿信息.xls".getBytes("GBK"),"iso8859-1");
		response.setContentType("application/vnd.ms-excel");		
		response.addHeader("Content-Disposition","attachment; filename=\"" + fileName + "\"");
		service.xxtjDczsxx(lddm, response.getOutputStream());
		return null;
	}
	/**
	 * 导出住宿信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	public ActionForward ldzsxxDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{

		String lddm = request.getParameter("lddm");
		XxtjService service = new XxtjService();
		String fileName = new String("楼栋住宿信息.xls".getBytes("GBK"),"iso8859-1");
		response.setContentType("application/vnd.ms-excel");		
		response.addHeader("Content-Disposition","attachment; filename=\"" + fileName + "\"");
		service.ldzsxxDc(lddm, response.getOutputStream());
		return null;
	}
	
	/**
	 * @描述：导出
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年3月7日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward getExportFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String lddm = request.getParameter("lddm");
		XxtjService service = new XxtjService();
		String defaultPhotoPath=request.getSession().getServletContext()
				.getRealPath("/images/type_pic.gif");
		File wordFile = service.getHmcFile(lddm,defaultPhotoPath);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * 
	 * @描述: 宁波技师导出楼栋信息
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-19 上午08:55:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward exportLdxxByNbJs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException, Exception{
		String[] params = request.getParameterValues("params");
		XxtjService service = new XxtjService();
		response.setHeader("Content-Disposition", "attachment;filename=\""
	               + new String("nbjs_ldxx_dc.xls".getBytes(), "GBK") + "\"");
		service.createWwb(params,response.getOutputStream());
		return null;
	}
}
