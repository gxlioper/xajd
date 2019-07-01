/**
 * @部门:学工产品事业部
 * @日期：2015-5-14 下午01:46:29 
 */
package com.zfsoft.xgxt.xsxx.tsxsgl.xslxwh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-5-14 下午01:46:29
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XslxwhService extends SuperServiceImpl<XslxwhForm, XslxwhDao> {
	private XslxwhDao dao = new XslxwhDao();
	/**
	 * 
	 * @描述:获取学生类型
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-5-14 下午04:12:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXslxList() {
		return dao.getXslxList();
	}
	public List<HashMap<String, String>> getXslxList(String[] xslxdms){
		
		return dao.getXslxList(xslxdms);
	}
	public String getXslxMc(String xslxdms){
		String xslxmc = "";
		if(xslxdms!=null){
			String[] xslxdm = xslxdms.split(",");
			List<HashMap<String, String>> knlxInfoList = this.getXslxList(xslxdm);
			if(knlxInfoList!=null && knlxInfoList.size()>0){
				for(int i=0;i<knlxInfoList.size();i++){
					if(i==knlxInfoList.size()-1){
						xslxmc += knlxInfoList.get(i).get("xslxmc");
					}else{
						xslxmc += knlxInfoList.get(i).get("xslxmc")+" 、";
					}
				}
			}
		}
		return xslxmc;
	}

	/**
	 * 获取类别列表
	 */
	public List<HashMap<String, String>> getPageList(XslxwhForm model) throws Exception {
		return super.getPageList(model);
	}
	/**
	 * 
	 * @描述:增加
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-5-15 下午01:14:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean addXslx(XslxwhForm model) throws Exception{
		return dao.addXslx(model);
		
	}
	/**
	 * 
	 * @描述:修改
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-5-15 下午01:14:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateXslx(XslxwhForm model) throws Exception{
		return dao.runUpdate(model);
	}
	/**
	 * 
	 * @描述:删除学生类型
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-2 下午04:10:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public int delXslx(String values) throws Exception{
		return dao.deleteXslx(values);
	}
	/**
	 * 
	 * @描述:学生类型是否被占用
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-10 下午02:58:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isUsed(String values) throws Exception {
		boolean flag = false;
		if (values != null) {
			String[] lbdms = values.split(",");
				flag = isExistsXmData(lbdms);
		}
		return flag;
	}
	
	/**
	 *获取学生类型
	 */
	public String getXslxmc(String lbdm) throws Exception {
		return dao.getXslxmc(lbdm);

	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:判断学生类别是否已使用
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-5-15 下午01:37:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * void 返回类型 
	 * @throws
	 */
	private boolean isExistsXmData(String[] lbdms) throws Exception{
		boolean flag = false;
		List<String>  xslxData = dao.getTsxslxData();
		for (int i = 0; i < lbdms.length; i++) {
			if(xslxData.contains(lbdms[i])){
				flag=true;
				break;
			}
			
		}
		return flag;
		
	}

}
