package xgxt.xtwh.kjfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.CommService;
import xgxt.comm.xtwh.CommXtwhForm;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系统维护_快捷方式_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class KjfsService extends CommService {

	KjfsDAO dao = new KjfsDAO();

	/**
	 * 设置用户快捷方式列表
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public List<HashMap<String, String>> setKjfsList(KjfsForm model, User user) {

		// 用户已分配功能列表
		List<HashMap<String, String>> yhList = getYhkjfsList(model, user);
		// 用户有权限功能列表
		List<HashMap<String, String>> picList = getYhAllkjfsList(model, user);
		// 最终功能列表
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (picList != null && picList.size() > 0) {

			int n = 0;

			for (int i = 0; i < picList.size(); i++) {

				HashMap<String, String> picMap = picList.get(i);
				HashMap<String, String> map = new HashMap<String, String>();
				// 图片路径
				String picPath = picMap.get("picpath");

				if (yhList != null && yhList.size() > 0) {

					for (int j = 0; j < yhList.size(); j++) {

						HashMap<String, String> yhMap = yhList.get(j);
						// 用户功能路径
						String yhPath = yhMap.get("picpath");

						if (yhPath.equalsIgnoreCase(picPath)) {
							map.put("iskjfs", "yes");
							map.put("yhnum", String.valueOf(n));
							n++;
							break;
						}
					}
				}

				map.putAll(picMap);
				list.add(map);
			}
		}
		
		int len=0;
		if(list!=null){
			len=list.size();
		}
		for(int i=0;i<18-len;i++){
			HashMap<String,String>hashMap=new HashMap<String,String>();
			hashMap.put("picpath", "");
			hashMap.put("showmk", "未设置");
			hashMap.put("iskjfs", "none");
			list.add(hashMap);
		}

		return list;
	}

	/**
	 * 获得用户快捷方式列表
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getYhkjfsList(KjfsForm model, User user) {

		// 表
		String tableName = "xg_view_xtwh_yhkjfs";
		// 用户名
		String yhm = user.getUserName();
		// 查询条件
		String query = " where 1 = 1 ";
		query += " and yhm = ? ";
		// 输入字段
		String[] inPutList = new String[] { yhm };
		// 输出字段
		String[] colList = new String[] { "picpath" };
		// 快捷方式列表
		List<HashMap<String, String>> list = CommonQueryDAO.commonQueryforList(
				tableName, query, inPutList, colList, "");

		return list;
	}

	/**
	 * 获得用户快捷方式列表
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getYhAllkjfsList(KjfsForm model,
			User user) {
		String newQxwh = user.getUserRolesApply();
		List<HashMap<String, String>> picList;
		if(newQxwh.equalsIgnoreCase("yes")){
			picList = dao.getYhAllkjfsListNew(model,
				user);
		}else{
			picList = dao.getYhAllkjfsList(model,
					user);
		}
		// List<HashMap<String, String>> picList = KjfsForm.getPicList();
		// // 所有图片列表
		// if (picList == null || picList.size() == 0) {
		// // 表
		// String tableName = "xg_view_xtwh_kjfs";
		// // 输出字段
		// String[] colList = new String[] { "picpath", "gnmk", "mkms", "bz",
		// "showmk", "showbz" };
		// // 快捷方式列表
		// picList = CommonQueryDAO.commonQueryforList(tableName, "",
		// new String[] {}, colList, "");
		//
		// KjfsForm.setPicList(picList);
		//		}

		return picList;
	}

	/**
	 * 保存用户快捷方式
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveYhKjfs(KjfsForm model, User user) throws Exception {

		// 保存表
		String tableName = "xtwh_kjfsszb";
		// 主键
		String pk = "yhm";
		// 主键值
		String[] pkValue = new String[] { user.getUserName() };
		// 单一字段
		String[] onezd = new String[] { "yhm" };
		// 批量字段
		String[] arrzd = new String[] { "pic", "xssx" };

		// 图片地址
		String[] pic = model.getPic();
		// 显示顺序
		String[] xssx = null;
		if (pic != null && pic.length > 0) {
			xssx = new String[pic.length];
			for (int i = 0; i < xssx.length; i++) {
				xssx[i] = String.valueOf(i);
			}
		}

		model.setYhm(user.getUserName());
		model.setXssx(xssx);

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);

		return saveInfoToDb(saveForm, model, user);

	}

	/**
	 * 获得用户快捷方式列表
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getKjfsList(User user) {

		return dao.getKjfsList(user);
	}
	
	/**
	 * 是否拥有权限
	 * 
	 * @author 伟大的骆
	 */
	public Boolean hadQx(User user, String path) {
		return dao.hadQx(user, path);
	}
	
	/**
	 * 获取服务列表
	 * @param model
	 * @param user
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getFwlb(KjfsForm model, User user){
		
		return dao.getFwlb(model, user);
	}
}