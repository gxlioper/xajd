package xgxt.zxdk.gzdx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.wjcf.zjlg.WjcfZjlgActionForm;
import xgxt.wjcf.zjlg.WjcfZjlgDAO;
import xgxt.wjcf.zjlg.WjcfZjlgModel;

public class ZxdkGzdxService {

	
	/**
	 * 查询助学贷款数据维护表头
	 * @return
	 */
	public List<HashMap<String, String>> queryZxdkxxTitle() {
		ZxdkGzdxDao dao = new ZxdkGzdxDao();
		return dao.queryZxdkxxTitle();
	}
	
	/**
	 * 查询助学贷款数据结果
	 * @param userType
	 * @param model
	 * @return
	 */
	public ArrayList<String[]> queryZxdkxxResult(ZxdkGzdxActionForm zxdkForm, ZxdkGzdxModel model, String userType, String userName) {
		ZxdkGzdxDao dao = new ZxdkGzdxDao();
		if ("xy".equalsIgnoreCase(userType) || "fdy".equalsIgnoreCase(userType)) {
			return dao.queryZxdkxxResultByxy(model, zxdkForm, userType,userName);
		} else {
			return dao.queryZxdkxxResult(model, zxdkForm);
		}
	}
	/**
	 * 查询助学贷款数据记录数
	 * @param userType
	 * @param model
	 * @return
	 */
	public int queryZxdkxxResultNum(String userType, ZxdkGzdxModel model, String userName) {
		ZxdkGzdxDao dao = new ZxdkGzdxDao();
		if ("xy".equalsIgnoreCase(userType) || "fdy".equalsIgnoreCase(userType)) {
			return dao.queryZxdkxxResultNumByxy(model, userType, userName);
		} else {
			return dao.queryZxdkxxResultNum(model);
		}
	}
	
	/**
	 * 单个显示详细信息
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryZxdkxxOne(String pkValue) {
		ZxdkGzdxDao dao = new ZxdkGzdxDao();
		return dao.queryZxdkxxOne(pkValue);
	}
	/**
	 * 新增前查询信息是否已经存在
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> queryAddZxdk(String pkValue) {
		ZxdkGzdxDao dao = new ZxdkGzdxDao();
		return dao.queryAddZxdk(pkValue);
	}
	/**
	 * 助学贷款信息单个保存
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveZxdkxx(ZxdkGzdxModel model) throws Exception {
		ZxdkGzdxDao dao = new ZxdkGzdxDao();
		return dao.saveZxdkxx(model);
	}
	/**
	 * 查询学生数据数据表头
	 * @return
	 */
	public List<HashMap<String, String>> queryZxdkdkxxTitle() {
		ZxdkGzdxDao dao = new ZxdkGzdxDao();
		return dao.queryZxdkdkxxTitle();
	}
	/**
	 * 查询学生信息结果
	 * @param zxdkForm
	 * @param userType
	 * @param userName
	 * @return
	 */
	public ArrayList<String[]> queryZxdkxxResultnotExis(ZxdkGzdxActionForm zxdkForm, String userType, String userName) {
		ZxdkGzdxDao dao = new ZxdkGzdxDao();
		return dao.queryZxdkxxResultnotExis(zxdkForm, userType, userName);
	}
	/**
	 * 修改助学贷款数据维护信息
	 * @param pkValue
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean updateZxdkSjwh(String pkValue, ZxdkGzdxModel model) throws Exception{
		ZxdkGzdxDao dao = new ZxdkGzdxDao();
		return dao.updateZxdkSjwh(pkValue, model);
	}
	/**
	 * 查看助学贷款信息
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewZxdkxx(String pkValue) {
		ZxdkGzdxDao dao = new ZxdkGzdxDao();
		return dao.viewZxdkxx(pkValue);
	}
	/**
	 * 删除助学贷款信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean delZxdkSjwh(String[] pkValue) throws Exception {
		ZxdkGzdxDao dao = new ZxdkGzdxDao();
		return dao.delZxdkSjwh(pkValue);
	}
}
