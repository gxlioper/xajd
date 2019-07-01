/**
 * @部门:学工产品事业部
 * @日期：2015-07-31 下午02:33:02 
 */
package com.zfsoft.xgxt.ystgl.dmwh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-07-31 下午02:33:02
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class YstglDmwhService extends SuperServiceImpl<YstglDmwhForm, YstglDmwhDao> {
	private YstglDmwhDao dao = new YstglDmwhDao();
	/**
	 * 
	 * @描述:艺术团类别列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-1-7 下午03:25:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>>  getYstlbList(YstglDmwhForm model) throws Exception{
		return dao.getYstlbList(model);
	}
	public List<HashMap<String, String>> getYstlbListAll() throws Exception {
		return dao.getYstlbListAll();
	}
	public List<HashMap<String, String>> getXmlbListAll(String ystlbdm) throws Exception {
		return dao.getXmlbListAll(ystlbdm);
	}
	public List<HashMap<String, String>> getGkdwListAll() throws Exception {
		return dao.getGkdwListAll();
	}
	public List<HashMap<String, String>>  getGkdwList(YstglDmwhForm model) throws Exception{
		return dao.getGkdwList(model);
	}
	/**
	 * 
	 * @描述:项目类别
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-1-11 上午08:48:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>>  getXmlbList(YstglDmwhForm model) throws Exception{
		return dao.getXmlbList(model);
	}
	
	/**
	 * 
	 * @描述:增加艺术团类别
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-07-31 下午04:00:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean addYstlb(YstglDmwhForm model) throws Exception{
		return dao.addYstlb(model);
		
	}
	public boolean updateYstlb(YstglDmwhForm model) throws Exception{
		return dao.updateYstlb(model);
	}
	public int deleteYstlb(String[] values) throws Exception {
		return dao.deleteYstlb(values);
	}
	public YstglDmwhForm getYstlb(YstglDmwhForm model) throws Exception{
		return dao.getYstlb(model);
	}
	/**
	 * 
	 * @描述:增加项目类别
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-1-7 下午05:55:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean addXmlb(YstglDmwhForm model) throws Exception{
		return dao.addXmlb(model);
		
	}
	public boolean updateXmlb(YstglDmwhForm model) throws Exception{
		return dao.updateXmlb(model);
	}
	public int deleteXmlb(String[] values) throws Exception {
		return dao.deleteXmlb(values);
	}
	public YstglDmwhForm getXmlb(YstglDmwhForm model) throws Exception{
		return dao.getXmlb(model);
	}
	
	/**
	 * 
	 * @描述:挂靠单位
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-1-14 下午05:55:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean addGkdw(YstglDmwhForm model) throws Exception{
		return dao.addGkdw(model);
		
	}
	public boolean updateGkdw(YstglDmwhForm model) throws Exception{
		return dao.updateGkdw(model);
	}
	public int deleteGkdw(String[] values) throws Exception {
		return dao.deleteGkdw(values);
	}
	public YstglDmwhForm getGkdw(YstglDmwhForm model) throws Exception{
		return dao.getGkdw(model);
	}
	
	
	/**
	 * 
	 * @描述:艺术团类别是否被占用
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-07-31 下午02:58:59
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
			for (int i = 0; i < lbdms.length; i++) {
				flag = dao.isExistsXmData(lbdms[i]);
				if (flag) {
					break;
				}
			}
		}
		return flag;
	}
	
	public boolean isExistsXmlbData(String values) throws Exception {
		boolean flag = false;
		if (values != null) {
			String[] lbdms = values.split(",");
			for (int i = 0; i < lbdms.length; i++) {
				flag = dao.isExistsXmlbData(lbdms[i]);
				if (flag) {
					break;
				}
			}
		}
		return flag;
	}
	
	public boolean isExistsGkdwData(String values) throws Exception {
		boolean flag = false;
		if (values != null) {
			String[] lbdms = values.split(",");
			for (int i = 0; i < lbdms.length; i++) {
				flag = dao.isExistsGkdwData(lbdms[i]);
				if (flag) {
					break;
				}
			}
		}
		return flag;
	}
	
}
