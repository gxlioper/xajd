/**
 * @部门:学工产品事业部
 * @日期：2016-12-2 下午05:37:40 
 */  
package com.zfsoft.xgxt.xsxx.dyxj.zgk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xsxx.dyxj.cssz.CsszModel;
import com.zfsoft.xgxt.xsxx.dyxj.cssz.CsszService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-12-2 下午05:37:40 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class zgkService extends SuperServiceImpl<zgkForm, zgkDao> {
	/**
	 * 
	 * @描述: 检验是否存在项目被使用
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-5 上午10:35:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotUsed(String[] ids){
		return dao.checkIsNotUsed(ids);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:保存增加学生到资格库
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-5 下午02:49:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveAddXsIntoZgk(String[] xhs) throws Exception{
		CsszModel csszModel = new CsszService().getModel();
		List<String[]> paraList = new ArrayList<String[]>();
		for (int i = 0; i < xhs.length; i++) {
			paraList.add(new String[]{csszModel.getSqxn(),csszModel.getSqxq() ,xhs[i]});
		}
		return dao.saveBatchXs(paraList);
	}
	
	/**
	 * 
	 * @描述: 从资格库中筛选学生
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-5 下午05:13:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZgkStuList(zgkForm t, User user)
	throws Exception {
		return dao.getZgkStuList(t, user);
	}
	
	/**
	 * 
	 * @描述: 验证学号，学年，学期是否存在资格库中
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-6 上午09:24:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsInZgk(String xh,String xn,String xq){
		return dao.checkIsInZgk(xh, xn, xq);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:保存增加学生到资格库
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-5 下午02:49:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveAddXsIntoZgkBytj(List<HashMap<String, String>> resultList) throws Exception{
		CsszModel csszModel = new CsszService().getModel();
		List<String[]> paraList = new ArrayList<String[]>();
		for (int i = 0; i < resultList.size(); i++) {
			paraList.add(new String[]{csszModel.getSqxn(),csszModel.getSqxq() ,resultList.get(i).get("xh")});
		}
		return dao.saveBatchXs(paraList);
	}

}
