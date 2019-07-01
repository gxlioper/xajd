/**
 * @部门:学工产品事业部
 * @日期：2014-4-29 下午03:06:03 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.xlzxcl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询（温大）-心理健康咨询 -心理咨询处理
 * @类功能描述: 
 * @作者： 王志刚[工号:1060]
 * @时间： 2014-4-29 下午03:06:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XlzxclService extends SuperServiceImpl<XlzxclForm, XlzxclDao>{

	public XlzxclService() {
		super.setDao(new XlzxclDao());
	}
	
	/**
	 * 查询咨询师信息
	 * @param zgh  职工号
	 * @return
	 */
	public HashMap<String,String> getZxsxx(String zgh){
		return dao.getZxsxx(zgh);
	}
	
	/**
	 * 根据学生心理咨询预约申请号查询心理咨询处理
	 * @param sqid 
	 * @return
	 */
	public HashMap<String,String> getXlzxclInfo(String sqid){
		return dao.getXlzxclInfo(sqid);
	}
	
	/**
	 * 根据学生心理咨询预约申请号删除心理咨询处理
	 * @param sqid 
	 * @return
	 * @throws Exception 
	 */
	public boolean deleteXlzxclBySqid(String sqid) throws Exception{
		return dao.deleteXlzxclBySqid(sqid);
	}
	
	/**
	 * 
	 * @描述:查询导出数据
	 * @作者：王志刚
	 * @日期：2014-5-8 下午15:58:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 */
	public List<HashMap<String, String>> getAllXlzxclList(XlzxclForm model)
			throws Exception {
		return dao.getAllXlzxclList(model);
	}
}
