/**
 * @部门:学工产品事业部
 * @日期：2013-5-24 下午02:24:52 
 */  
package com.zfsoft.xgxt.pjpy.pjgl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 评奖评优之我的评奖 
 * @作者： zhangjw 
 * @时间： 2013-5-24 下午02:24:52 
 * @版本： V5.8.16
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class WdpjAction   extends SuperAction{

	private WdpjService service = new WdpjService();
	/**
	 * @描述:我的评奖 荣誉证书打印功能  单选
	 * @作者：zhangjw
	 * @日期：2013-5-24 下午02:52:23
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
	public ActionForward dyryzs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WdpjForm myForm = (WdpjForm) form;
		String xh = myForm.getXh();
		String[] xhs = xh.split(",");
		if(xhs.length>1){
			return dyryzsZip(mapping, myForm, request, response);
		}
		File wordFile = service.getRyzsWord(xh);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	/**
	 * @描述:荣誉证书打印  多选
	 * @作者：zhangjw
	 * @日期：2013-5-28 上午08:34:24
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
	public ActionForward dyryzsZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String value = request.getParameter("xh");
		
		if (StringUtils.isNotNull(value)){
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0 , n = values.length ; i < n ; i++){
				File file = service.getRyzsWord(values[i]);
				files.add(file);
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[]{}));
			FileUtil.outputZip(response, zipFile);
		}
		return null;
	}	
}
