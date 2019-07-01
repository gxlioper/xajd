package xgxt.comm.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

public class SearchTjByRoles {
	
	/**
	 * 获得用户功能角色
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public List<HashMap<String, String>> getUserGnmkRoles(
			SearchForm searchForm, User user) throws Exception {

		// 访问路径
		String path = searchForm.getPath();

		SearchDAO dao = new SearchDAO();

		// 功能模块代码
		String gnmkdm = dao.getOneValue("gnmkdmb", "gnmkdm", "dyym", path);
		// 功能模块角色
		String[] gnmkRoles = dao.getGnmkRoles(user, gnmkdm);
		// 用户角色
		String[] userRoles = user.getUserRoles();

		ArrayList<String> rolesList = new ArrayList<String>();

		if (gnmkRoles != null && gnmkRoles.length > 0) {
			
			for (int i = 0; i < gnmkRoles.length; i++) {
				
				String roalsByGnmk = gnmkRoles[i];
				
				if (userRoles != null && userRoles.length > 0) {
					
					for (int j = 0; j < userRoles.length; j++) {
						
						String roalsByUser = userRoles[j];
			
						if(roalsByGnmk.equalsIgnoreCase(roalsByUser)){
							rolesList.add(roalsByUser);
						}
					}
				}
			}
		}

		List<HashMap<String, String>> roles = getUserRoles(rolesList.toArray(new String[]{}));
		
		return roles;
	}
	
	/**
	 * 获得用户功能角色
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	private List<HashMap<String, String>> getUserRoles(String[] userRoles) throws Exception {
		
		SearchDAO dao = new SearchDAO();

		// 角色信息
		List<HashMap<String, String>> list = dao.getUserRolesInfo(userRoles);

		List<HashMap<String, String>> rolesList = new ArrayList<HashMap<String, String>>();
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				
				boolean flag = false;
				
				HashMap<String, String> map = list.get(i);
				// 角色层面代码
				String jscmdm = map.get("jscmdm");

				for (int j = 0; j < list.size(); j++) {
					HashMap<String, String> jsInfo = list.get(j);
					// 角色层面代码
					String js_jscmdm = jsInfo.get("jscmdm");
					if ("01".equalsIgnoreCase(jscmdm)
							|| "05".equalsIgnoreCase(jscmdm)
							|| "06".equalsIgnoreCase(jscmdm)) {// 学校级别,公寓管理员，用人单位
						flag = true;
					} else if ("02".equalsIgnoreCase(jscmdm)) {// 学院级别
						if (!"01".equalsIgnoreCase(js_jscmdm)) {
							flag = true;
						} else {
							flag = false;
						}
					} else if ("03".equalsIgnoreCase(jscmdm)
							|| "04".equalsIgnoreCase(jscmdm)) {// 辅导员级别 or
						// 班主任级别
						if (!"01".equalsIgnoreCase(js_jscmdm)
								&& !"02".equalsIgnoreCase(js_jscmdm)) {
							flag = true;
						} else {
							flag = false;
						}
					}
				}
				
				if(flag){
					rolesList.add(map);
				}
			}
		}
		
		return rolesList;
	}
	
	/**
	 * 获得用户身份
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public String getUserStatus(List<HashMap<String, String>> userRoles,User user) {

		String userStatus = "";

		if (userRoles != null && userRoles.size() > 0) {
			
			boolean xx_flag = false;
			boolean xy_flag = false;
			boolean fdy_flag = false;
			boolean bzr_flag = false;
			
			for (int i = 0; i < userRoles.size(); i++) {

				HashMap<String, String> map = userRoles.get(i);
				// 角色层面代码
				String jscmdm = map.get("jscmdm");
				
				if ("01".equalsIgnoreCase(jscmdm)) {// 学校级别
					xx_flag = true;
				} else if ("02".equalsIgnoreCase(jscmdm)) {// 学院级别
					xy_flag = true;
				} else if ("03".equalsIgnoreCase(jscmdm)) {// 辅导员级别
					fdy_flag = true;
				} else if ("04".equalsIgnoreCase(jscmdm)) {// 班主任级别
					bzr_flag = true;
				} else if ("06".equalsIgnoreCase(jscmdm)) {// 公寓管理员
					user.setGyglyQx("yes");
				}
			}
			
			if (xx_flag) {
				userStatus = "xx";// 学校用户（管理员）
			}else if (xy_flag) {
				userStatus = "xy";// 学院
			}else if (fdy_flag && bzr_flag) {
				userStatus = "jd";// 班主任兼辅导员
			}else if (fdy_flag) {
				userStatus = "fdy";// 辅导员
			} else if (bzr_flag) {
				userStatus = "bzr";// 班主任
			}
			
		} else {
			SearchDAO dao = new SearchDAO();
			// 用户名
			String userName = user.getUserName();
			// 默认级别
			String mrjb = dao.getOneValue("xg_xtwh_yhb", "mrjb", "yhm",
					userName);
			
			if ("01".equalsIgnoreCase(mrjb)) {// 学校级别
				userStatus = "xx";
			} else if ("02".equalsIgnoreCase(mrjb)) {// 学院级别
				userStatus = "xy";
			} else if ("03".equalsIgnoreCase(mrjb)) {// 辅导员级别
				userStatus = "fdy";
			} else if ("04".equalsIgnoreCase(mrjb)) {// 班主任级别
				userStatus = "bzr";
			} 
		}
		
		return userStatus;
	}
}
