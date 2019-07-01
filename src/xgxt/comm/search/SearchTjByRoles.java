package xgxt.comm.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

public class SearchTjByRoles {
	
	/**
	 * ����û����ܽ�ɫ
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	public List<HashMap<String, String>> getUserGnmkRoles(
			SearchForm searchForm, User user) throws Exception {

		// ����·��
		String path = searchForm.getPath();

		SearchDAO dao = new SearchDAO();

		// ����ģ�����
		String gnmkdm = dao.getOneValue("gnmkdmb", "gnmkdm", "dyym", path);
		// ����ģ���ɫ
		String[] gnmkRoles = dao.getGnmkRoles(user, gnmkdm);
		// �û���ɫ
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
	 * ����û����ܽ�ɫ
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 * 
	 */
	private List<HashMap<String, String>> getUserRoles(String[] userRoles) throws Exception {
		
		SearchDAO dao = new SearchDAO();

		// ��ɫ��Ϣ
		List<HashMap<String, String>> list = dao.getUserRolesInfo(userRoles);

		List<HashMap<String, String>> rolesList = new ArrayList<HashMap<String, String>>();
		
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				
				boolean flag = false;
				
				HashMap<String, String> map = list.get(i);
				// ��ɫ�������
				String jscmdm = map.get("jscmdm");

				for (int j = 0; j < list.size(); j++) {
					HashMap<String, String> jsInfo = list.get(j);
					// ��ɫ�������
					String js_jscmdm = jsInfo.get("jscmdm");
					if ("01".equalsIgnoreCase(jscmdm)
							|| "05".equalsIgnoreCase(jscmdm)
							|| "06".equalsIgnoreCase(jscmdm)) {// ѧУ����,��Ԣ����Ա�����˵�λ
						flag = true;
					} else if ("02".equalsIgnoreCase(jscmdm)) {// ѧԺ����
						if (!"01".equalsIgnoreCase(js_jscmdm)) {
							flag = true;
						} else {
							flag = false;
						}
					} else if ("03".equalsIgnoreCase(jscmdm)
							|| "04".equalsIgnoreCase(jscmdm)) {// ����Ա���� or
						// �����μ���
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
	 * ����û����
	 * 
	 * @author ΰ�����
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
				// ��ɫ�������
				String jscmdm = map.get("jscmdm");
				
				if ("01".equalsIgnoreCase(jscmdm)) {// ѧУ����
					xx_flag = true;
				} else if ("02".equalsIgnoreCase(jscmdm)) {// ѧԺ����
					xy_flag = true;
				} else if ("03".equalsIgnoreCase(jscmdm)) {// ����Ա����
					fdy_flag = true;
				} else if ("04".equalsIgnoreCase(jscmdm)) {// �����μ���
					bzr_flag = true;
				} else if ("06".equalsIgnoreCase(jscmdm)) {// ��Ԣ����Ա
					user.setGyglyQx("yes");
				}
			}
			
			if (xx_flag) {
				userStatus = "xx";// ѧУ�û�������Ա��
			}else if (xy_flag) {
				userStatus = "xy";// ѧԺ
			}else if (fdy_flag && bzr_flag) {
				userStatus = "jd";// �����μ渨��Ա
			}else if (fdy_flag) {
				userStatus = "fdy";// ����Ա
			} else if (bzr_flag) {
				userStatus = "bzr";// ������
			}
			
		} else {
			SearchDAO dao = new SearchDAO();
			// �û���
			String userName = user.getUserName();
			// Ĭ�ϼ���
			String mrjb = dao.getOneValue("xg_xtwh_yhb", "mrjb", "yhm",
					userName);
			
			if ("01".equalsIgnoreCase(mrjb)) {// ѧУ����
				userStatus = "xx";
			} else if ("02".equalsIgnoreCase(mrjb)) {// ѧԺ����
				userStatus = "xy";
			} else if ("03".equalsIgnoreCase(mrjb)) {// ����Ա����
				userStatus = "fdy";
			} else if ("04".equalsIgnoreCase(mrjb)) {// �����μ���
				userStatus = "bzr";
			} 
		}
		
		return userStatus;
	}
}
