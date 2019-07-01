/**
 * @部门:学工产品(1)部
 * @日期：2018-2-6 下午05:42:56 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxtjgl.fwmddc;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.xpjpy.tjcx.HjmdExportModel;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.dmwh.xmlx.XmlxService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 发文名单导出
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-2-6 下午05:42:56 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FwmddcAction extends SuperAction{
	private static final String url = "xpjpy_tjgl_fwmddc.do";
	private FwmddcService service = new FwmddcService();
	
	/**
	 * @描述: 发文名单导出查询列表
	 * @作者：Meng.Wei[工号：1186]
	 * @日期：2018-2-7 上午10:43:05
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
	@SystemLog(description = "访问新评奖评优-统计管理-发文名单导出-查询页面")
	public ActionForward getFwmddcList(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		
		FwmddcForm model = (FwmddcForm)form;
		FwmddcService service = new FwmddcService();
		/**查询*/
		if (QUERY.equals(model.getType())){
			/*生成高级查询对象*/
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			/*查询*/
			List<HashMap<String,String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		/**返回path*/
		request.setAttribute("path", url);
		/**取参数设置信息*/
		CsszService csszService = new CsszService();
		HashMap<String,String> csszInfo = csszService.getCszzInfo();
		request.setAttribute("cssz", csszInfo);
		/**默认高级查询条件*/
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{csszInfo.get("xn")});
		/**设置默认项目类型高级查询条件*/
		XmlxService xmlxService = new XmlxService();
		List<HashMap<String, String>> xmlxList = xmlxService.getXmlx();
		if(!xmlxList.isEmpty()&&xmlxList.size()>0){
			String[] xmlx = new String[xmlxList.size()];
			for (int i = 0; i < xmlxList.size(); i++) {
				xmlx[i] = xmlxList.get(i).get("dm");
			}
			searchModel.setSearch_tj_xxmlx(xmlx);
		}
		request.setAttribute("searchTj", searchModel);
		/**返回评奖周期*/
		request.setAttribute("pjzq", csszInfo.get("xn"));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fwmddcList");
	}
	
	/**
	 * @描述: 获取获奖名单导出
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-2-24 下午05:52:42
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
	@SystemAuth(url = url,rewritable = ReadWrite.WRITEABLE)
	public ActionForward expHjmdtj(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
		throws Exception{
		
		User user = getUser(request);
		CommService comService = new CommService();
		/**用之前的获奖名单model*/
		HjmdExportModel exporModel = new HjmdExportModel();
		comService.getModelValue(exporModel, request);
		
		/**项目类型中文乱码*/
		String array_xmlx = request.getParameter("array_xmlx");
		array_xmlx = java.net.URLDecoder.decode(array_xmlx,"UTF-8");
		String xmlxs[] = array_xmlx.split("!!array!!");
		String array_xmmc = request.getParameter("array_xmmc");
		if(null != array_xmmc){
			array_xmmc = java.net.URLDecoder.decode(array_xmmc,"UTF-8");
			String xmmcs[] = array_xmmc.split("!!array!!");
			exporModel.setXmmc(xmmcs);
		}
		exporModel.setXmlx(xmlxs);
		
		response.setContentType("application/vnd.ms-excel");
		/**加入文件名，防止因为api操作系统兼容性不好而引起的excel后缀名改成.do*/
		 response.setHeader("Content-Disposition", "attachment;filename=\"" + new String("xpj_tjcx.xls".getBytes(), "GBK") + "\"");
		
		 /**获取导出方式
		  * 导出（dc）、导出word（dcwd）、年鉴导出（njdc）、年鉴导出word（njdcwd）
		  */
		String dcfs = request.getParameter("dcfs");
		
		if("dcwd".equals(dcfs)){
			String path = servlet.getServletContext().getRealPath("/temp/")+"/"+"/dummy.doc";
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
		return null;
	}
}
