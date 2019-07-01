/**
 * @部门:学工产品事业部
 * @日期：2014-7-4 下午02:27:43 
 */  
package xsgzgl.qgzx.xsgl;


import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import xgxt.form.User;
import xgxt.utils.Pages;

import java.util.HashMap;
import java.util.List;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 勤工助学
 * @类功能描述: 勤工助学学生维护
 * @作者： 陶钢军 [1075]
 * @时间： 2014-7-4 下午02:27:43 
 * @版本： V5.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class QgzxXsglService extends SuperServiceImpl<QgzxXsglForm, QgzxXsglDao> {
	
	private QgzxXsglDao dao = new QgzxXsglDao();

	/**
	 * 
	 * @描述:获取学生
	 * @作者：陶钢军 [1075]
	 * @日期：2014-7-7 下午04:33:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXsPageList(QgzxXsglForm model, User user) throws Exception{
		
		return dao.getXsPageList(model, user);
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:添加勤工助学学生
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-30 下午02:13:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveQgzxXs(String xh) throws Exception{
		return dao.saveQgzxXs(xh);
	}

	/**
	 * @功能描述:维护是否够买保险
	 * @auther: 王晨辉[1692]
	 */
	public boolean updateSfgmbx(QgzxXsglForm model) throws Exception{
		return dao.updateSfgmbx(model);
	}

	public HashMap<String, String> getDetail(String xh) {
		return dao.getDetail(xh);
	}

	public boolean plUpdate(String xh) throws Exception {
		return dao.plUpdate(xh);
	}

	public List<HashMap<String,String>> getQgryAllList(QgzxXsglForm t, User user)
		throws Exception {
			Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
			pages.setPageSize(Integer.MAX_VALUE);

			t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return dao.getQgryAllList(t,user);
	}
}
