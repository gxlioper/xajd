package com.zfsoft.xgxt.xstgl.sthdgl.sthdjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import xgxt.form.User;
import xgxt.utils.GetTime;

public class SthdjgService extends SuperServiceImpl<SthdjgForm, SthdjgDao>{
	SthdjgDao dao = new SthdjgDao();
    

	// 保存
	public boolean saveSthdjg(SthdjgForm model,User user) throws Exception {
		String id = null;
		if ("save".equalsIgnoreCase(model.getType())) {
			model.setLrr(user.getUserName());
			model.setLrsj(GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss"));
			id = UniqID.getInstance().getUniqIDHash();
			model.setHdid(id);
			// 保存结果表
			return dao.runInsert(model);
		}
		else{
			return dao.runUpdate(model);
		}
		
	}
	/**
	 * 
	 * @描述:验证是否重复填写
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-7-30 上午10:13:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkExistForSave(SthdjgForm model){
		return dao.checkExistForSave(model);
	}
	/**
	 * 
	 * @描述:获取学生社团活动登记历史信息
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-30 上午10:11:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * ArrayList<String[]> 返回类型 
	 * @throws
	 */
	public ArrayList<String[]>  getMoreHdjgList(SthdjgForm model)throws Exception {
		
		return dao.getMoreHdjgList(model);
	}
	
	/**
	 *
	 * @描述:陕西师范大学志愿者结果list学生信息查看自定义表单配置用
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2015-12-7 上午09:14:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getSthdlist(String xh){
		return dao.getSthdlist(xh);
	}

	public boolean delByFwsj(SthdjgForm t) throws Exception {
		return dao.delByFwsj(t);
	}

	public HashMap<String,String> getEkxx(SthdjgForm t) {
		return dao.getEkxx(t);
	}
}
