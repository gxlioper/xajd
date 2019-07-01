package xgxt.xtwh.portallet;

import java.util.List;

import xgxt.form.CommanForm;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 系统维护提供给Portallet操作的Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-07-078</p>
 */
public class XtwhForPortalletService {
	XtwhForPortalletDAO dao = new XtwhForPortalletDAO();
	
	/**
	 * 获取模块列表
	 * @return List
	 * */
	public List getModelList(){
		return dao.getModelList();
	}
	
	/**
	 * 新闻信息查询
	 * @param model
	 * @return List
	 * */
	public List queryNews(CommanForm model){		
		return dao.queryNews(model);
	}
	
	/**
	 * 新闻查询表头
	 * @return List
	 * */
	public List getQueryNewTitle(){
		String[] colList = {"newsid","newspart","lesstitle","pubtime","puber","newstitle"};
		String[] CNList = {"新闻ID", "所属模块","新闻标题","发布时间","发布人","全标题"};
		return dao.arrayToList(colList, CNList);
	}
}
