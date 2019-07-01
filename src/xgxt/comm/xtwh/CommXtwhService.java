package xgxt.comm.xtwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.comm.CommService;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

public class CommXtwhService extends CommService {

	CommXtwhDAO dao = new CommXtwhDAO();

	//系统快捷方式图片列表
	public static List<HashMap<String, String>> picList = null;
	
	/**
	 * 保存首页设置
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveSysz(CommXtwhForm model, HttpServletRequest request)
			throws Exception {
		return dao.saveSysz(model, request);
	}
	
	/**
	 * 获得首页设置相关信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public HashMap<String, String> getSyInfo(CommXtwhForm model) {

		String tableName = "xtwh_syszb";
		// 主键
		String pk = "rownum";
		// 主键值得
		String pkValue = "1";
		// 输出字段
		String[] colList = new String[] { "tsyj" };

		// 首页信息
		HashMap<String, String> map = CommonQueryDAO.commonQueryOne(tableName,
				colList, pk, pkValue);

		return map;
	}
	
	/**
	 * 获得用户快捷方式列表
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getYhkjfsList(CommXtwhForm model,
			User user) {

		// 表
		String tableName = "xtwh_kjfsszb";
		// 用户名
		String yhm = user.getUserName();
		// 查询条件
		String query = " where 1 = 1 ";
		query += " and yhm = ? ";
		// 输入字段
		String[] inPutList = new String[] { yhm };
		// 输出字段
		String[] colList = new String[] { "pic" };
		// 快捷方式列表
		List<HashMap<String, String>> list = CommonQueryDAO.commonQueryforList(
				tableName, query, inPutList, colList, "");
		
		return list;
	}
	
	/**
	 * 获得用户快捷方式列表
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getAllKjfsList(CommXtwhForm model,
			User user) {

		// 所有图片列表
		if (picList == null || picList.size() == 0) {
			// 表
			String tableName = "xtwh_kjfs_picb";
			// 输出字段
			String[] colList = new String[] { "picpath", "gnmk", "mkms" };
			// 快捷方式列表
			picList = CommonQueryDAO.commonQueryforList(tableName, "",
					new String[] {}, colList, "");
		}

		return picList;
	}
	
	/**
	 * 设置用户快捷方式列表
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> setKjfsList(CommXtwhForm model,
			User user) {
		// 用户功能列表
		List<HashMap<String, String>> yhList = getYhkjfsList(model, user);
		// 用户功能列表
		List<HashMap<String, String>> picList = getAllKjfsList(model, user);
		// 用户功能列表
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (picList != null && picList.size() > 0) {

			for (int i = 0; i < picList.size(); i++) {

				HashMap<String, String> picMap = picList.get(i);
				HashMap<String, String> map = new HashMap<String, String>();
				// 图片路径
				String picPath = picMap.get("picpath");

				if (yhList != null && yhList.size() > 0) {

					for (int j = 0; j < yhList.size(); j++) {

						HashMap<String, String> yhMap = yhList.get(j);
						// 用户功能路径
						String yhPath = yhMap.get("pic");

						if (yhPath.equalsIgnoreCase(picPath)) {
							map.put("iskjfs", "yes");
							break;
						}
					}
				}

				map.putAll(picMap);
				list.add(map);
			}
		}

		return list;
	}
	
	/**
	 * 保存快捷方式设置
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveKjfssz(CommXtwhForm model, User user) throws Exception {

		// 快捷方式设置表
		String tableName = "xtwh_kjfsszb";
		String[] onezd = new String[] { "yhm" };
		String[] arrzd = new String[] { "pic" };
		String pk = "yhm";
		String userName = user.getUserName();

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { userName });

		model.setYhm(userName);
		
		return dao.saveData(saveForm, model, user);

	}
	
	/**
	 * 获得用户快捷方式列表
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKjfsMenuList(String userName) {

		// 表
		String tableName = "xg_view_xtwh_yhkjfs";
		// 用户名
		String yhm = userName;
		// 查询条件
		String query = " where 1 = 1 ";
		query += " and yhm = ? ";
		// 输入字段
		String[] inPutList = new String[] { yhm };
		// 输出字段
		String[] colList = new String[] { "picpath", "gnmk", "showmk","mkms" };
		// 快捷方式列表
		List<HashMap<String, String>> list = CommonQueryDAO.commonQueryforList(
				tableName, query, inPutList, colList, "");

		return list;
	}
	
	/**
	 * 是否拥有权限
	 * 
	 * @author luojw
	 */
	public Boolean hadQx(User user, String path) {
		return dao.hadQx(user, path);
	}
}
