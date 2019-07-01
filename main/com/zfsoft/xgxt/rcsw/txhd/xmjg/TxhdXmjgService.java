/**
 * @部门:学工产品事业部
 * @日期：2014-6-24 上午09:40:22 
 */  
package com.zfsoft.xgxt.rcsw.txhd.xmjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.base.BaseDAO;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2014-6-24 上午09:40:22 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TxhdXmjgService extends SuperServiceImpl<TxhdXmjgForm, TxhdXmjgDao> {
	
	public static String _BCZSCID = "-1";
	TxhdXmjgDao dao = new TxhdXmjgDao();
	@Override
	public List<HashMap<String, String>> getPageList(TxhdXmjgForm t, User user)
			throws Exception {
		return getHdxx(super.getPageList(t, user));
	}
	public List<HashMap<String, String>> getHdxx(
			List<HashMap<String, String>> list) {
		return list;
	}
	
	/**
	 * 
	 * @描述:活动结果修改
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-06-24 下午12:56:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean update(TxhdXmjgForm model) throws Exception {
			return super.runUpdate(model);
		}
	
	/**
	 * 删除对应申请数据
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2014-6-26 下午04:29:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * @throws Exception
	 * boolean 返回类型
	 */
	public boolean deleteForSh(String sqid) throws Exception{
		return dao.deleteForSh(sqid)>=0?true:false;
	}
	/**
	 * 
	 * @描述:活动结果增加
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-06-24 下午14:21:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean save(TxhdXmjgForm model) throws Exception {
		model.setSqsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
			return super.runInsert(model);
		}
	
	/**
	 * @throws Exception
	 * 
	 * @描述:删除
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-06-24 下午15:52:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 *             String[] 返回类型 String数组 0为成功删除条数为不能删除的
	 * @throws
	 */
	public String[] delete(String[] ids) throws Exception {
		// StringBuffer del=new StringBuffer();
		List<String> delId = new ArrayList<String>();// 可删除的id集合
		List<String> delSqId = new ArrayList<String>();// 对应可删除的申请数据id
		if (null == ids || ids.length <= 0) {
			return null;
		}
		for (String str : ids) {
				delId.add(str);// 记录删除id
				delSqId.add(getModel(str).getGuid());
			}
		
		int i = delId.size() > 0 ? runDelete(delId.toArray(new String[] {}))
				: 0;
		return new String[] { String.valueOf(i)};
	}
	
	@Override
	public TxhdXmjgForm getModel(TxhdXmjgForm t) throws Exception {
		t = super.getModel(t);
		if(t!=null){
			// 把学期代码转换为学期名称
			t.setXqmc(BaseDAO.getInstance().getXqmcForXqdm(t.getXq()));
		}
		return t;
	}
	public boolean isAdd(TxhdXmjgForm myForm){
		return dao.isAdd(myForm);
	}
	
	
	public HashMap<String, String> getOneHdjgList(String id){
		return dao.getOneHdjgList(id);
	}
	
}
