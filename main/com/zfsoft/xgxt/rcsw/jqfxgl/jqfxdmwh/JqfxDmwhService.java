/**
 * @部门:学工产品事业部
 * @日期：2018-4-20 下午03:23:54 
 */  
package com.zfsoft.xgxt.rcsw.jqfxgl.jqfxdmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： lgx[工号:1553]
 * @时间： 2018-4-20 下午03:23:54 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JqfxDmwhService  extends SuperServiceImpl<JqfxDmwhForm,JqfxDmwhDao>{
	
    private JqfxDmwhDao dao=new JqfxDmwhDao();
    public static String _BCZSCID="-1";
    
	@SuppressWarnings("deprecation")
	public JqfxDmwhService() {
		super.setDao(dao);
	}
	
	
	/**
	 * 
	 * @描述:TODO(增加假期返校类别维护)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-11 上午09:21:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveJqfxgldmwhInfo(JqfxDmwhForm model,String type) throws Exception{
		
		boolean result = false;
		model.setFxdm(model.getFxdm().trim());
		model.setFxmc(model.getFxmc().trim());
		if ("add".equals(type)) {
			return dao.addFxgldmInfo(model);
		} else if ("update".equals(type)) {
			return dao.updateFxgldmInfo(model);
		}
		return result;
		
	}
		
	/**
	 * 
	 * @描述:TODO(删除返校代码维护)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-16 下午02:19:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] deletFxlbdmwhInfo(String[] ids) throws Exception{
		List<String> delId=new ArrayList<String>();//可删除的id集合
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.isCanDel(str)){
				delId.add(str);//记录删除id
			}else{
				HashMap<String, String> hm=dao.getFxmc(str);
				noDel.append(hm.get("fxmc"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?fxlbdmwhDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//去除最后多余逗号
		str=isHaveNoId?str:_BCZSCID;		
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 
	 * @描述:TODO(删除返校代码维护)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-3-16 下午02:18:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param fxdm
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	private int fxlbdmwhDelete(String[] fxdm) throws Exception {		
		return runDelete(fxdm);
	}
	 
}
