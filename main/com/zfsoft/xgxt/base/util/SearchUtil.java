/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-11-23 ����03:50:06 
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
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �߼���ѯ����
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-11-23 ����03:50:06
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class SearchUtil {
	private static Map<String, SearchModel> searTj = new Hashtable<String, SearchModel>();

	/**
	 * 
	 * @����:����߼���ѯ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-11-23 ����03:53:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @deprecated ��ʱ����
	 * @param search
	 *            ��ǰ����������
	 * @param user
	 *            �����û� void ��������
	 */
	public static synchronized void saveSearch(SearchModel search, User user) {
		searTj.put(user.getUserName(), search);
	}

	/**
	 * 
	 * @����:��ȡ�߼���ѯ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-11-23 ����03:54:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 *            �����û�
	 * @deprecated ��ʱ����
	 * @return SearchModel ��������
	 */
	public static synchronized SearchModel getSearch(User user) {
		return searTj.get(user.getUserName());
	}

	/**
	 * 
	 * @����: ���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-11-25 ����10:36:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @deprecated ��ʱ���� void ��������
	 * @throws
	 */
	public static synchronized void clean(User user) {
		searTj.remove(user.getUserName());
	}

	/**
	 * 
	 * @����: ��request��ȡ��ѯ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-11-23 ����04:47:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @return SearchModel ��������
	 * @throws
	 */
	public static SearchModel getSearchForRequest(HttpServletRequest request) {
		RequestForm rForm = new RequestForm();
		CommService cs = new CommService();
		SearchModel searchModel = null;
		try {
			searchModel = cs.setSearchModel(rForm, request);
		} catch (Exception e) {
			throw new RuntimeException("���ø߼���ѯ��������!");
		}
		return searchModel;
	}

	/**
	 * 
	 * @����:ͨ���ɰ�߼���ѯ������ȡSearchModel ������Ϊֱ�Ӹ��Ƹ��ģ��벻Ҫ����淶��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-11-23 ����07:45:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param input_mhcx
	 * @param mhcx_lx
	 * @param searchLxs
	 * @param searchTjs
	 * @param searchTjzs
	 * @return SearchModel ��������
	 */
	public static SearchModel getSearchModel(String input_mhcx, String mhcx_lx,
			String searchLxs, String searchTjs, String searchTjzs) {
		SearchModel searchModel = new SearchModel();
		searchModel.setInput_mhcx(input_mhcx);
		searchModel.setMhcx_lx(mhcx_lx);
		// ��������
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
						// TODO �Զ����� catch ��
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
	 * �û��Ƿ���Ȩ�ޣ�֪ͨ���桢����ר����
	 */
	public NewQxfwForm getNewQxfwForm(String newsId) {
		return getNewsForSerarchDao().getNewQxfwForm(newsId);
	}

	/**
	 * 
	 * @����:�����Ӧ�߼���ѯȨ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-11-23 ����05:01:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @param newsId
	 * @throws Exception void ��������
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
			throw new RuntimeException("��������Ȩ�����ô���");
		}
	}

	/**
	 * @����:�޸Ĳ�ѯȨ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-11-25 ����09:38:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @param newsId
	 * @return
	 * @throws Exception boolean ��������
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
			throw new RuntimeException("��������Ȩ�����ô���");
		}
	}

	/**
	 * 
	 * @����:ɾ��Ȩ�޷�Χ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-11-25 ����03:51:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param request
	 * @param newsId
	 * @return boolean ��������
	 */
	public boolean deleteSearch(HttpServletRequest request, String newsId) {
		String qxfwid = request.getParameter("qxfwid");
		SearchDao dap = new SearchDao();
		return dap.deleteQx(qxfwid) > 0 ? true : false;
	}
	
	/**
	 * 
	 * @����:�û��Ƿ���Ȩ�ޣ�֪ͨ���桢����ר����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-11-25 ����03:01:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param user
	 * @param newsId
	 * @return boolean ��������
	 */
	public boolean isHaveQx(User user, String newsId, String toWho) {
		SearchDao dap = new SearchDao();
		return dap.isHaveQxInner(user, newsId, toWho);
	}

	/**
	 * 
	 * @����:��ȡ��ѡ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-11-25 ����05:23:26
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param nqf
	 * @return HashMap<String,List<String>> ��������
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
			String bz = "";// ��־
			int i = 0;
			for (String str : tjsz) {
				Map<String, String> map = new HashMap<String, String>();
				// ��ȡ�ֶα�־
				bz = str.substring(str.lastIndexOf("_") + 1, str.length());
				if ("xy".equals(bz)) {
					map.put("mc", Base.YXPZXY_KEY );
					map.put("jtmc", sd.getXymc(tjzsz[i]));
				} else if ("zy".equals(bz)) {
					map.put("mc", "רҵ");
					map.put("jtmc", sd.getZymc(tjzsz[i]));
				} else if ("bj".equals(bz)) {
					map.put("mc", "�༶");
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
		 * @����:�û��Ƿ���Ȩ�ޣ�֪ͨ���桢����ר����
		 * @���ߣ��Ų�·[���ţ�982]
		 * @���ڣ�2013-11-23 ����06:13:38
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param newsId
		 * @return NewQxfwForm ��������
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
				throw new RuntimeException("��ȡ֪ͨ���桢����ר����ӦȨ��ʱ���ݴ���!");
			}
			return nqf;
		}

		/**
		 * 
		 * @����:�û��Ƿ���Ȩ�ޣ�֪ͨ���桢����ר����
		 * @���ߣ��Ų�·[���ţ�982]
		 * @���ڣ�2013-11-25 ����02:58:59
		 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
		 * @param user
		 * @param newsId
		 * @return List<HashMap<String,String>> ��������
		 */
		public boolean isHaveQxInner(User user, String newsId, String toWho) {
			NewQxfwForm nqf = SearchUtil.getInstance().getNewQxfwForm(newsId);
			SearchModel sm = getSearchModel("", nqf.getMhcx_lx(), nqf.getSearchLx(), nqf.getSearchTj(), nqf.getSearchTjz());
			try {
				// �û�����
				String userType = user.getUserType();
				// �û����
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
				} else if("stu".equalsIgnoreCase(userType)){ // ѧ��
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
				}else{ // ����Ա��ѧУ�û�
					return true;
				}
			} catch (Exception e) {
				throw new RuntimeException("��ȡȨ�޷�Χ����", e);
			}
			return false;
		}

		public int deleteQx(String qxfwid) {
			try {
				return dao.runDelete("delete xgxt_search where qxfwid=?",
						new String[] { qxfwid });
			} catch (Exception e) {
				throw new RuntimeException("ɾ��ȥϸ��Ȩ�޷�Χ����", e);
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
