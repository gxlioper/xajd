package xgxt.rcsw.hcyhk.hcyhkff;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchService;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.rcsw.RcswForm;
import xgxt.utils.Pages;

public class HcyhkffService extends CommService {

	HcyhkffDAO dao=new HcyhkffDAO();
	
	/**
	 * 火车优惠卡发放查询
	 * @param model
	 * @param colList
	 * @param lcmc
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryHcyhkff(RcswForm model) throws Exception{
		return dao.queryHcyhkff(model);
	}
	
	/**
	 * 火车优惠卡批量发放
	 * @param model
	 * @param colList
	 * @param lcmc
	 * @return
	 * @throws Exception
	 */
	public boolean plHcyhkff(RcswForm model) throws Exception{
		
		return dao.plHcyhkff(model);
	}
	
	/**
	 * 删除火车优惠卡发放
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean delHcyhkff(RcswForm model) throws Exception{
		
		return dao.delHcyhkff(model);
	}

	/**
	 * 获取火车优惠卡记录
	 * @param model
	 * @return
	 */
	public HashMap<String,String>getHcyhkMap(RcswForm model){
		
		return dao.getHcyhkMap(model);
	}
	
	
	/**
	 * 增加火车优惠卡
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean addHcyhk(RcswForm myForm) throws Exception {
		
		return dao.addHcyhk(myForm);
	}
	
	/**
	 * 增加火车优惠卡
	 * @param myForm
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateHcyhk(RcswForm myForm) throws Exception {
		
		return dao.updateHcyhk(myForm);
	}
	
	public List<HashMap<String, String>> getTopTr(String lx,RequestForm rForm) {

		DAO dao = DAO.getInstance();
		
		String[]en=null;
		String[]cn=null;
		if("hcyhkff".equalsIgnoreCase(lx)){
			 en =new String[] { "pkValue","xh","xm","nj","xymc","zymc","bjmc","jbrxm","ffsj","sfff"};
			 cn =new String[] { "主键", "学号", "姓名", "年级", Base.YXPZXY_KEY, "专业", "班级", "经办人姓名",
				"发放时间","是否发放" };
		}
		rForm.setColList(en);
		return dao.arrayToList(en, cn);
	}
	/**
	 * 
	 * @描述:获得导出数据list
	 * @作者：张昌路[工号：982]
	 * @日期：2013-6-21 上午09:44:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param rf
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getExportList(RcswForm rf, User user) throws Exception{
		rf.setUser(user);
		Pages pages =rf.getPages();
		pages.setPageSize(Integer.MAX_VALUE);
		rf.setPages(pages);//
		return dao.getExportList(rf);
	}
}
