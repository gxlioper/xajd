/**
 * @部门:学工产品事业部
 * @日期：2013-11-23 下午03:50:06 
 */
package com.zfsoft.xgxt.base.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.MessageResources;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.xtwh.general.news.NewQxfwForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 高级查询操作
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2013-11-23 下午03:50:06
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class SearchUtil {
	private static Map<String, SearchModel> searTj = new Hashtable<String, SearchModel>();

	/**
	 * 
	 * @描述:保存高级查询条件
	 * @作者：张昌路[工号：982]
	 * @日期：2013-11-23 下午03:53:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @deprecated 暂时废弃
	 * @param search
	 *            当前操作的条件
	 * @param user
	 *            操作用户 void 返回类型
	 */
	public static synchronized void saveSearch(SearchModel search, User user) {
		searTj.put(user.getUserName(), search);
	}

	/**
	 * 
	 * @描述:获取高级查询条件
	 * @作者：张昌路[工号：982]
	 * @日期：2013-11-23 下午03:54:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 *            操作用户
	 * @deprecated 暂时废弃
	 * @return SearchModel 返回类型
	 */
	public static synchronized SearchModel getSearch(User user) {
		return searTj.get(user.getUserName());
	}

	/**
	 * 
	 * @描述: 清空
	 * @作者：张昌路[工号：982]
	 * @日期：2013-11-25 上午10:36:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @deprecated 暂时废弃 void 返回类型
	 * @throws
	 */
	public static synchronized void clean(User user) {
		searTj.remove(user.getUserName());
	}

	/**
	 * 
	 * @描述: 从request获取查询条件
	 * @作者：张昌路[工号：982]
	 * @日期：2013-11-23 下午04:47:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @return SearchModel 返回类型
	 * @throws
	 */
	public static SearchModel getSearchForRequest(HttpServletRequest request) {
		RequestForm rForm = new RequestForm();
		CommService cs = new CommService();
		SearchModel searchModel = null;
		try {
			searchModel = cs.setSearchModel(rForm, request);
		} catch (Exception e) {
			throw new RuntimeException("设置高级查询条件错误!");
		}
		return searchModel;
	}

	/**
	 * 
	 * @描述:通过旧版高级查询方法获取SearchModel （代码为直接复制更改，请不要纠结规范）
	 * @作者：张昌路[工号：982]
	 * @日期：2013-11-23 下午07:45:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param input_mhcx
	 * @param mhcx_lx
	 * @param searchLxs
	 * @param searchTjs
	 * @param searchTjzs
	 * @return SearchModel 返回类型
	 */
	public static SearchModel getSearchModel(String input_mhcx, String mhcx_lx,
			String searchLxs, String searchTjs, String searchTjzs) {
		SearchModel searchModel = new SearchModel();
		searchModel.setInput_mhcx(input_mhcx);
		searchModel.setMhcx_lx(mhcx_lx);
		// 过滤类型
		String[] searchLx = StringUtil.isNull(searchLxs) ? new String[] {}
				: searchLxs.split("!!@@!!");
		String[] searchTj = StringUtil.isNull(searchTjs) ? new String[] {}
				: searchTjs.split("!!@@!!");
		String[] searchTjz = StringUtil.isNull(searchTjzs) ? new String[] {}
				: searchTjzs.split("!!@@!!");

		if (searchTj != null && searchTj.length > 0) {

			for (int i = 0; i < searchLx.length; i++) {

				ArrayList<String> valueList = new ArrayList<String>();
				String lx = "search_tj_" + searchLx[i];

				for (int j = 0; j < searchTj.length; j++) {

					String tj = searchTj[j];
					String tjz = searchTjz[j];
					tjz = tjz.replace("!!DDBGG!!", "");
					if (lx.equalsIgnoreCase(tj.replace("searchModel.", ""))) {
						valueList.add(tjz);
					}

				}

				Class myClass = searchModel.getClass();

				String setMethod = "setS" + lx.substring(1, lx.length());
				String[] newValue = valueList.toArray(new String[] {});

				if (newValue != null && newValue.length > 0) {

					try {
						Method methodName = myClass.getMethod(setMethod,
								new Class[] { String[].class });

						methodName.invoke(searchModel, (Object) newValue);
					} catch (Exception e) {
						// TODO 自动生成 catch 块
						e.printStackTrace();
					}

				}
			}
		}

		return searchModel;
	}

	public static SearchUtil getInstance() {
		return new SearchUtil();
	}

	public SearchDao getNewsForSerarchDao() {
		return new SearchDao();
	}
	/**
	 * 用户是否有权限（通知公告、下载专区）
	 */
	public NewQxfwForm getNewQxfwForm(String newsId) {
		return getNewsForSerarchDao().getNewQxfwForm(newsId);
	}

	/**
	 * 
	 * @描述:保存对应高级查询权限
	 * @作者：张昌路[工号：982]
	 * @日期：2013-11-23 下午05:01:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @param newsId
	 * @throws Exception void 返回类型
	 */
	public boolean saveSearchModel(HttpServletRequest request, String newsId)
			throws Exception {
		NewQxfwForm nqf = new NewQxfwForm();
		nqf.setNewQxfwFormForRequest(request);
		nqf.setNewsid(newsId);
		SearchDao dap = new SearchDao();
		try {
			return dap.runInsert(nqf);
		} catch (Exception e) {
			throw new RuntimeException("保存新闻权限配置错误");
		}
	}

	/**
	 * @描述:修改查询权限
	 * @作者：张昌路[工号：982]
	 * @日期：2013-11-25 上午09:38:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @param newsId
	 * @return
	 * @throws Exception boolean 返回类型
	 */
	public boolean updateSearchModel(HttpServletRequest request, String newsId)
			throws Exception {
		String qxfwid = request.getParameter("qxfwid");
		NewQxfwForm nqf = new NewQxfwForm();
		nqf.setNewQxfwFormForRequest(request);
		nqf.setNewsid(newsId);
		nqf.setQxfwid(qxfwid);
		SearchDao dap = new SearchDao();
		try {
			return dap.runUpdate(nqf);
		} catch (Exception e) {
			throw new RuntimeException("保存新闻权限配置错误");
		}
	}

	/**
	 * 
	 * @描述:删除权限范围
	 * @作者：张昌路[工号：982]
	 * @日期：2013-11-25 下午03:51:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @param newsId
	 * @return boolean 返回类型
	 */
	public boolean deleteSearch(HttpServletRequest request, String newsId) {
		String qxfwid = request.getParameter("qxfwid");
		SearchDao dap = new SearchDao();
		return dap.deleteQx(qxfwid) > 0 ? true : false;
	}
	
	/**
	 * 
	 * @描述:用户是否有权限（通知公告、下载专区）
	 * @作者：张昌路[工号：982]
	 * @日期：2013-11-25 下午03:01:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param newsId
	 * @return boolean 返回类型
	 */
	public boolean isHaveQx(User user, String newsId, String toWho) {
		SearchDao dap = new SearchDao();
		return dap.isHaveQxInner(user, newsId, toWho);
	}

	/**
	 * 
	 * @描述:获取已选条件
	 * @作者：张昌路[工号：982]
	 * @日期：2013-11-25 下午05:23:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param nqf
	 * @return HashMap<String,List<String>> 返回类型
	 */
	public List<Map<String, String>> getSelectTj(NewQxfwForm nqf) {
		MessageResources message = MessageResources
				.getMessageResources("config.ApplicationResources");

		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		if (nqf.isNull()) {
			return null;
		}
		SearchDao sd = new SearchDao();
		List<Map<String, String>> selectTj = new ArrayList<Map<String, String>>();
		if (null == nqf.getSeletTj() || nqf.getSeletTj().size() <= 0) {
			String[] tjsz = nqf.getSearchTj().split("!!@@!!");
			String[] tjzsz = nqf.getSearchTjz().split("!!@@!!");
			String bz = "";// 标志
			int i = 0;
			for (String str : tjsz) {
				Map<String, String> map = new HashMap<String, String>();
				// 获取字段标志
				bz = str.substring(str.lastIndexOf("_") + 1, str.length());
				if ("xy".equals(bz)) {
					map.put("mc", Base.YXPZXY_KEY );
					map.put("jtmc", sd.getXymc(tjzsz[i]));
				} else if ("zy".equals(bz)) {
					map.put("mc", "专业");
					map.put("jtmc", sd.getZymc(tjzsz[i]));
				} else if ("bj".equals(bz)) {
					map.put("mc", "班级");
					map.put("jtmc", sd.getBjmc(tjzsz[i]));
				}
				i++;
				selectTj.add(map);
			}
		}
		return selectTj;
	}

	class SearchDao extends SuperDAOImpl<NewQxfwForm> {
		@Override
		protected void setTableInfo() {
			this.setKey("QXFWID");
			this.setClass(NewQxfwForm.class);
			this.setTableName("XGXT_SEARCH");
		}

		@Override
		public List<HashMap<String, String>> getPageList(NewQxfwForm t,
				User user) throws Exception {
			return null;
		}

		@Override
		public List<HashMap<String, String>> getPageList(NewQxfwForm t)
				throws Exception {
			return null;
		}

		/**
		 * 
		 * @描述:用户是否有权限（通知公告、下载专区）
		 * @作者：张昌路[工号：982]
		 * @日期：2013-11-23 下午06:13:38
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param newsId
		 * @return NewQxfwForm 返回类型
		 */
		public NewQxfwForm getNewQxfwForm(String newsId) {
			NewQxfwForm nqf = new NewQxfwForm();
			StringBuffer sb = new StringBuffer();
			sb.append("select * from XGXT_SEARCH");
			sb.append(" where 1=1");
			sb.append(" and newsId=?");
			Map<String, String> map = dao.getMapNotOut(sb.toString(),
					new String[] { newsId });
			try {
				nqf.setMhcx_lx(map.get("mhcx_lx"));
				nqf.setSearchLx(map.get("searchlx"));
				nqf.setSearchTj(map.get("searchtj"));
				nqf.setSearchTjz(map.get("searchtjz"));
				nqf.setNewsid(map.get("newsid"));
				nqf.setQxfwid(map.get("qxfwid"));
			} catch (Exception e) {
				throw new RuntimeException("获取通知公告、下载专区对应权限时数据错误!");
			}
			return nqf;
		}

		/**
		 * 
		 * @描述:用户是否有权限（通知公告、下载专区）
		 * @作者：张昌路[工号：982]
		 * @日期：2013-11-25 下午02:58:59
		 * @修改记录: 修改者名字-修改日期-修改内容
		 * @param user
		 * @param newsId
		 * @return List<HashMap<String,String>> 返回类型
		 */
		public boolean isHaveQxInner(User user, String newsId, String toWho) {
			NewQxfwForm nqf = SearchUtil.getInstance().getNewQxfwForm(newsId);
			SearchModel sm = getSearchModel("", nqf.getMhcx_lx(), nqf.getSearchLx(), nqf.getSearchTj(), nqf.getSearchTjz());
			try {
				// 用户类型
				String userType = user.getUserType();
				// 用户身份
				String userStatus = user.getUserStatus();
				if ("xy".equalsIgnoreCase(userStatus) ||
						"bzr".equalsIgnoreCase(userStatus) ||
						"fdy".equalsIgnoreCase(userStatus) ||
						"jd".equalsIgnoreCase(userStatus)) {
					if("all_teastu".equals(toWho) || "all_tea".equals(toWho)){
						return true;
					}else if("some_teastu".equals(toWho) || "some_tea".equals(toWho)){
						if (sm == null) {
							return true;
						}
						sm.setPath("newselectxy.do");
						String searchTj = SearchService.getSearchTj(sm);
						String[] inputV = SearchService.getTjInput(sm);
						StringBuffer sb = new StringBuffer();
						sb.append("select 1 from ( ");
						sb.append("select bmdm xydm from fdyxxb where zgh='"+ user.getUserName() + "'");
						sb.append(") a where 1=1 ");
						sb.append(searchTj);
						List<HashMap<String, String>> rs =  dao.getListNotOut(sb.toString(), inputV);
						return rs.size() > 0;
					}
				} else if("stu".equalsIgnoreCase(userType)){ // 学生
					if("all_teastu".equals(toWho) || "all_stu".equals(toWho)){
						return true;
					}else if("some_teastu".equals(toWho) || "some_stu".equals(toWho)){
						if (sm == null) {
							return true;
						}
						sm.setPath("newselectbj.do");
						String searchTj = SearchService.getSearchTj(sm);
						String[] inputV = SearchService.getTjInput(sm);
						StringBuffer sb = new StringBuffer();
						sb.append("select 1 from view_xsxxb where xh='" + user.getUserName() + "'");
						sb.append(searchTj);
						List<HashMap<String, String>> rs =  dao.getListNotOut(sb.toString(), inputV);
						return rs.size() > 0;
					}
				}else{ // 管理员、学校用户
					return true;
				}
			} catch (Exception e) {
				throw new RuntimeException("获取权限范围错误！", e);
			}
			return false;
		}

		public int deleteQx(String qxfwid) {
			try {
				return dao.runDelete("delete xgxt_search where qxfwid=?",
						new String[] { qxfwid });
			} catch (Exception e) {
				throw new RuntimeException("删除去细纹权限范围错误！", e);
			}
		}

		public String getXymc(String xydm) {
			StringBuffer sb = new StringBuffer();
			sb.append(" select xymc from view_njxyzybj where xydm=?");
			return dao.getOneRs(sb.toString(), new String[] { xydm }, "xymc");
		}

		public String getZymc(String zydm) {
			StringBuffer sb = new StringBuffer();
			sb.append(" select zymc from view_njxyzybj where zydm=?");
			return dao.getOneRs(sb.toString(), new String[] { zydm }, "zymc");
		}

		public String getBjmc(String bjdm) {
			StringBuffer sb = new StringBuffer();
			sb.append(" select bjmc from view_njxyzybj where bjdm=?");
			return dao.getOneRs(sb.toString(), new String[] { bjdm }, "bjmc");
		}
	}
}
