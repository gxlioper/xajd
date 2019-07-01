/**
 * @部门:学工产品事业部
 * @日期：2013-11-4 下午06:11:52 
 */  
package com.zfsoft.xgxt.xpjpy.tjcx;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.xmsz.xmwh.XmwhService;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.util.ResourceUtils;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优
 * @类功能描述: 统计查询 
 * @作者：CQ [工号：785]
 * @时间： 2013-11-4 下午06:11:52 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TjcxAction extends SuperAction{
	
	private String targetFilePath;
	
	private static final String url = "pj_tjcx.do";

	/**
	 * 
	 * @描述:获奖名单汇总
	 * @作者：cq [工号：785]
	 * @日期：2013-11-7 下午06:51:43
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
	@SystemAuth(url = url)
	public ActionForward viewTjcxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TjcxModel model = (TjcxModel) form;
		TjcxService service = new TjcxService();

		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = new ArrayList<HashMap<String,String>>();
			if(Base.xxdm.equals("10277")){
				resultList = service.getPageListShTyxy(model, user);
			}else{
				resultList = service.getPageList(model,user);
			}
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		//默认高级查询条件
		String pjzq = csszService.getCsz("pjzq");
		SearchModel searchModel=new SearchModel();	
		searchModel.setSearch_tj_xn(new String[]{csszModel.getXn()});
		if("1".equals(pjzq)){ 
			searchModel.setSearch_tj_xq(new String[]{csszModel.getXq()});
		}

		request.setAttribute("searchTj", searchModel);
		
		String path = "pj_tjcx.do";
		
		request.setAttribute("pjzq", pjzq);
		request.setAttribute("path", path);
		request.setAttribute("cssz", csszModel);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewTjcxList");
	}
	
	/**
	 * 
	 * @描述:获奖名单导出
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-2 上午11:06:08
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward expHjmdtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		User user = getUser(request);
		TjcxService service = new TjcxService();
		CommService comService = new CommService();
		HjmdExportModel exporModel = new HjmdExportModel();
		comService.getModelValue(exporModel, request);
		//项目类型中文乱码
		String array_xmlx = request.getParameter("array_xmlx");
		array_xmlx = java.net.URLDecoder.decode(array_xmlx,"UTF-8");
		String xmlxs[] = array_xmlx.split("!!array!!");
		String array_xmmc = request.getParameter("array_xmmc");
		if(null!=array_xmmc){
		array_xmmc = java.net.URLDecoder.decode(array_xmmc,"UTF-8");
		String xmmcs[] = array_xmmc.split("!!array!!");
		exporModel.setXmmc(xmmcs);
		}
		exporModel.setXmlx(xmlxs);
		response.setContentType("application/vnd.ms-excel");
		//加入文件名，防止因为api操作系统兼容性不好而引起的excel后缀名改成.do
		 response.setHeader("Content-Disposition", "attachment;filename=\""
	               + new String("xpj_tjcx.xls".getBytes(), "GBK") + "\"");
		String dcfs = request.getParameter("dcfs");
		
		if(Base.xxdm.equals("10335")){ //浙江大学
			//发文名单导出word
			if("dcwd".equals(dcfs)){
			String path =	servlet.getServletContext().getRealPath(
				"/temp/")+"/"+"/dummy.doc";
				service.createWord(response,exporModel,user,path);
				return null;
			}
			
			if("njdcwd".equals(dcfs)){
				service.createWordNjdc(response,exporModel,user);
				return null;
			}
			if("njdc".equals(dcfs)){
				service.exportHjmdtj_10335_njdc(exporModel, response.getOutputStream(),user);
			}else{
				service.exportHjmdtj_10335(exporModel, response.getOutputStream(),user);
			}
		}else if(Base.xxdm.equals("12072")){
			service.exportHjmdtj_12072(exporModel, response.getOutputStream(),user);
		}else{
			service.exportHjmdtj(exporModel, response.getOutputStream(),user);
		}
		return null;
	}
	
	/**
	 * @描述:获奖名单导出（上海电机学院）
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-6 下午05:24:54
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward expHjmdtj_11458(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		TjcxService service = new TjcxService();
		CommService comService = new CommService();
		HjmdExportModel exporModel = new HjmdExportModel();
		comService.getModelValue(exporModel, request);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename="+new String("公示名单".getBytes("gb2312"),"iso-8859-1")+".xls");
		
		service.exportHjmdtj_11458(exporModel, response.getOutputStream());
		
		return null;
	}
	
	
	/**
	 * 
	 * @描述:浙江大学奖学金名额分配一览表
	 * @作者：张昌路[工号：982]
	 * @日期：2015-3-10 下午02:52:08
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
	@SystemAuth(url = url)
	public ActionForward viewJxjmefpList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TjcxModel model = (TjcxModel) form;
		TjcxService service = new TjcxService();
		
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			model.setXn(searchModel.getSearch_tj_xn()[0]);//学年
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getJxjmefpList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{csszModel.getXn()});
		request.setAttribute("searchTj", searchModel);
		model.setSearchModel(searchModel);
		model.setXn(searchModel.getSearch_tj_xn()[0]);
		
		//需统计项目
		List<HashMap<String, String>> pjxmList = service.getPjxmList(model);

		request.setAttribute("pjxmList", pjxmList);
		request.setAttribute("path", "pj_jxjmefp.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewJxjmefpList");
	}
	
	
	/**
	 * 
	 * @描述:获得统计项目（浙江大学
	 * @作者：张昌路[工号：982]
	 * @日期：2015-3-11 上午11:22:32
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
	
	public ActionForward initPjxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TjcxModel model = (TjcxModel) form;
		TjcxService service = new TjcxService();
		String xn = request.getParameter("xn");//学年
		
		if(StringUtils.isBlank(xn)){
			CsszService csszService = new CsszService();
			CsszModel csszModel = csszService.getModel();
			xn=csszModel.getXn();
		}
		model.setXn(xn);
		
		List<HashMap<String, String>> pjxmList = service.getPjxmList(model);
		JSONArray dataList = JSONArray.fromObject(pjxmList);
		response.getWriter().print(dataList);
		return null;
	}
	
	
	/**
	 * 
	 * @描述:统计导出（浙江大学
	 * @作者：cq [工号：785]
	 * @日期：2013-8-17 上午11:43:51
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportViewTjjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TjcxModel model = (TjcxModel) form;
		TjcxService service = new TjcxService();
			
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		String xn = searchModel.getSearch_tj_xn()[0];
		if(StringUtils.isBlank(xn)){
			CsszService csszService = new CsszService();
			CsszModel csszModel = csszService.getModel();
			xn=csszModel.getXn();
		}
		model.setXn(xn);
		
		User user = getUser(request);
		
		File file = service.getTjjgFile(model,user);
		//导出文件
		FileUtil.outputExcel(response, file);
		return null;
			
	}
	
	
	/**
	 * 
	 * @描述:发放汇总导出
	 * @作者：cq [工号：785]
	 * @日期：2015-4-29 下午03:10:57
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportFfhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TjcxModel model = (TjcxModel) form;
		TjcxService service = new TjcxService();
			
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		String xn = searchModel.getSearch_tj_xn()[0];
		if(StringUtils.isBlank(xn)){
			CsszService csszService = new CsszService();
			CsszModel csszModel = csszService.getModel();
			xn=csszModel.getXn();
		}
		model.setXn(xn);
		
		User user = getUser(request);
		
		File file = service.exportFfhz(model,user);
		//导出文件
		FileUtil.outputExcel(response, file);
		return null;
			
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportGjjxjhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TjcxModel model = (TjcxModel) form;
		TjcxService service = new TjcxService();
			
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		String xn = searchModel.getSearch_tj_xn()[0];
		if(StringUtils.isBlank(xn)){
			CsszService csszService = new CsszService();
			CsszModel csszModel = csszService.getModel();
			xn=csszModel.getXn();
		}
		model.setXn(xn);
		response.setContentType("application/vnd.ms-excel");
		User user = getUser(request);
		
		service.exportGjjxjhz(model,response.getOutputStream(),user);
		
		return null;
			
	}
	
	
	/**
	 * 
	 * @描述:浙江大学——证书打印
	 * @作者：cq
	 * @日期：2015-5-5 上午09:34:05
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
	@SystemAuth(url = "pj_zsdy.do")
	public ActionForward viewZsdy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TjcxModel model = (TjcxModel) form;
		TjcxService service = new TjcxService();
		
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.viewZsdy(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
//		searchModel.setSearch_tj_xn(new String[]{CsszService.getPjzq().get("xn")});
//		if(!StringUtil.isNull(CsszService.getPjzq().get("cxxq"))){
//			searchModel.setSearch_tj_xq(new String[]{CsszService.getPjzq().get("cxxq")});
//		}
		request.setAttribute("searchTj", searchModel);
		String path = "pj_zsdy.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
	
		return mapping.findForward("viewZsdy");
	}
	
	/**
	 * 
	 * @描述:浙江大学证书打印软件下载
	 * @作者：cq [工号：785]
	 * @日期：2015-5-6 下午02:26:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	@SystemAuth(url = "pj_zsdy.do",rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportZs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String fileName = "zsprint.xls"; // 下载文件名称包含扩展名
		response.setContentType("application/octet-stream");
		try {
			response.setHeader( "Content-Disposition", "attachment;filename="  + new String( "证书打印软件.xls".getBytes("gb2312"), "ISO8859-1" ) );
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		OutputStream os = null;
		FileInputStream fis = null;
		try {
			//用输出流输出文件
			os = response.getOutputStream();
			fis = new FileInputStream(ResourceUtils.getFile("classpath:templates/xszz/excel")+"/" + fileName); //设置下载文件路径
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len = fis.read(buffer)) > 0){
				os.write(buffer, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if(os != null){
					os.flush();
					os.close();
				}
				if(fis != null){
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 浙江大学证书打印软件下载NEW
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportZsNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		TjcxModel myForm = (TjcxModel) form;
		TjcxService service = new TjcxService();
		
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		
		User user = getUser(request);
		String filePath = servlet.getServletContext().getRealPath("")+"/WEB-INF/classes/templates/xszz/excel/zsprint.xls";
		response.setContentType("application/octet-stream");
		response.setHeader( "Content-Disposition", "attachment;filename="  + new String( "证书打印软件.xls".getBytes("gb2312"), "ISO8859-1" ) );
		service.exportZsNew(myForm, user, response, filePath);
		return null;
	}
	
	
	/**
	 * 
	 * @描述:浙大数据导出
	 * @作者：cq [工号：785]
	 * @日期：2015-5-6 下午05:06:36
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TjcxModel model = (TjcxModel) form;
		TjcxService service = new TjcxService();
		String lxmc = request.getParameter("lxmc");
		
		User user = getUser(request);
		
		File file = service.exportData(model,user,lxmc);
		//导出文件
		FileUtil.outputExcel(response, file);
		return null;
		
	}
	
	/**
	 * 上海体育学院结果集导出
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportDataShty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TjcxModel  model = (TjcxModel ) form;
		TjcxService service = new TjcxService();
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getPageListShTyxy(model,
				user);// 查询出所有记录，不分页
		
		

		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}

	/**
	 *  获取评优名单汇总表（Word）.
	 *  <P>暂为湘潭大学个性化</P>
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-10-19 15:39
	 * @param
	 * @return org.apache.struts.action.ActionForward
	 * @throw IOException
	 */
	public ActionForward getPymdhzb(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response) throws IOException {

		TjcxModel  model = (TjcxModel ) form;
		TjcxService service = new TjcxService();

		//根据学年、学院数组查询生成名单汇总表文件数组
		File[] files = service.getPymdhzbFiles(model);

		if(files.length>0){
			if(files.length>1){
				File zipFile = ZipUtil.zip(files);
				FileUtil.outputZip(response, zipFile);
			}else{
				FileUtil.outputWord(response, files[0]);
			}
		}
		return null;
	}

	public ActionForward getJxjExcel(ActionMapping mapping, ActionForm form,
									 HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TjcxModel model = (TjcxModel) form;
		TjcxService service = new TjcxService();
		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("userName",user.getRealName());
		map.put("xn",searchModel.getSearch_tj_xn()[0]);
		map.put("xqmc",Base.getXqmcForXqdm(searchModel.getSearch_tj_xq()[0]));
		if(searchModel.getSearch_tj_xy()!=null) {
			String[] xymcs = service.getXymcByDms(searchModel.getSearch_tj_xy());
			String xymc = Arrays.toString(xymcs);
			xymc=xymc.substring(xymc.indexOf("[")+1,xymc.indexOf("]"));
			map.put("xymc",xymc);
		}

		if("tj".equals(model.getType())){
			model.setType("jxjtjExcel");
			List<HashMap<String,String>> resultList = service.getAllList(model,user);
			File file = service.getTjExcelFile(resultList,map);//生成导出文件
			FileUtil.outputExcel(response, file);
		}else{
			model.setType("jxjlqExcel");
			String sj = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
			map.put("sj", sj);
			List<HashMap<String,String>> resultList = service.getAllList(model,user);
			File file = service.getLqExcelFile(resultList,map);//生成导出文件
			FileUtil.outputExcel(response, file);
		}

		return null;
	}
	
}
