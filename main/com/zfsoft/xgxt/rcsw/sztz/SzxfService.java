/**
 * @部门:学工产品事业部
 * @日期：2014-10-29 上午09:48:42 
 */  
package com.zfsoft.xgxt.rcsw.sztz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


public class SzxfService extends SuperServiceImpl<SzxfModel, SzxfDao> {
	private static final String PASS = "合格";
	private static final String FAIL = "不合格";

	
	
	/**
	 * 
	 * @描述: 学分汇总
	 * @作者：屈朋辉[工号：445]
	 * @日期：2015-3-16 下午06:44:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getPageXfhzList(SzxfModel model, User user) throws Exception{
		
		return dao.getPageXfhzList(model, user);
	}
	
	
	public List<HashMap<String,String>> getAllXfhzList(SzxfModel model, User user) throws Exception{
		
		Pages pages = model.getPages();
		pages.setPageSize(Integer.MAX_VALUE);
		
		model.setPages(pages);
		
		return dao.getPageXfhzList(model, user);
	}
	
	/** 
	 * @描述:获取个人学分统计列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-2 上午10:56:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPageGrxftjList(SzxfModel t, User user) throws Exception {
		return dao.getPageGrxftjList(t, user);
	}
	
	/** 
	 * @描述:导出个人学分统计列表（安徽国防个性化定制）
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-2 下午04:18:46
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
	public List<HashMap<String, String>> getAllGrxftjList(SzxfModel t, User user) throws Exception{
		Pages pages = t.getPages();
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.setPages(pages);
		
		List<HashMap<String, String>> list = dao.getPageGrxftjList(t, user);
		for(HashMap<String, String> map : list){
			String zxf = map.get("zxf");    //总分
			String axf = map.get("azf");    //A项目分数
			String bxf = map.get("bzf");    //B项目分数
			String cxf = map.get("czf");    //C项目分数
			String dxf = map.get("dzf");    //D项目分数
			String exf = map.get("ezf");    //E项目分数
			if(Double.parseDouble(zxf)< 8){  //如果总分小于8，不合格
				map.put("sfhg", FAIL);
			}else{
				//如果个项目分数不达标，不合格
				if(Double.parseDouble(axf)<0.5 || Double.parseDouble(bxf)<0.5 || Double.parseDouble(cxf)<2 || Double.parseDouble(dxf)<0.5 || Double.parseDouble(exf)<0.5){
					map.put("sfhg", FAIL);
				}else{
					map.put("sfhg", PASS);
				}
			}
		}
		return list;
	}
}
