package com.zfsoft.xgxt.rcsw.hcyhkgl.hcyhklx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * 火车优惠卡类型
 */
public class HcyhklxService extends SuperServiceImpl<HcyhklxForm, HcyhklxDao>  {

	private HcyhklxDao dao = new HcyhklxDao();
	public static String _BCZSCID="-1";
	
	public HcyhklxService() {
		super.setDao(dao);
	}
	
	/**
	 * 获得最大序号的火车优惠卡类型代码
	 */
	public String changeHcyhkgllxdm() {
		String max = dao.getMaxHcyhkgllxdm();
		if(Base.isNull(max)){
			return "001";
		}else{
			max = String.valueOf((Integer.parseInt(max)+1));
			String pre = "";
			for(int i = 0; i < 3-max.length();i ++){
				pre+="0";
			}
			return pre+max;
		}
	}
	
	/**
	 * 增加，修改火车优惠卡类型
	 */
	public boolean saveInfo(HcyhklxForm model,String type) throws Exception{
		boolean result = false;
		if ("add".equals(type)) {
			String hcyhkgllxdm = changeHcyhkgllxdm();
			model.setLxdm(hcyhkgllxdm);
			return dao.addInfo(model);
		} else if ("update".equals(type)) {
			return dao.updateInfo(model);
		}
		return result;
	}
	/**
	 * 火车优惠卡类型是否存在
	 */
	public boolean isExist(HcyhklxForm model, String type)
			throws Exception {
		return dao.isExist(model, type);
	}
	/**
	 * 修改火车优惠卡类型 单个查询
	 */
	public HcyhklxForm getHcyhklxForm(HcyhklxForm t) throws Exception{
		return dao.getHcyhklxForm(t);
	}
	
	/**
	 * 删除学生证补办申请
	 */
	public String[] deleteHcyhklxInfo(String[] ids) throws Exception{
		List<String> delId=new ArrayList<String>();//可删除的id集合
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		HcyhklxForm t = new HcyhklxForm();
		for(String str : ids){
			if(dao.isCanDel(str)){
				delId.add(str);//记录删除id
			}else{
				t.setLxdm(str);
				HcyhklxForm temp = dao.getHcyhklxForm(t);
				noDel.append(temp.getLxmc());
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?runDelete(delId.toArray(new String[]{})):0;
		String str = new StringBuffer(noDel.reverse().toString().replaceFirst(",", "")).reverse().toString();
		//去除最后多余逗号
		str=isHaveNoId?str:_BCZSCID;
		
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 获取所有火车优惠卡类型
	 */
	public List<HashMap<String, String>> getHcyhklxList()
		throws Exception {
		return dao.getHcyhklxList();
	}
	
}
