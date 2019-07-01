package xgxt.comm.menuManage;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class MenuManageService {

	MenuManageDAO dao = new MenuManageDAO();

	/**
	 * 返回全部一级菜单
	 * @see xgxt.comm.menuManage.MenuManageDAO#getTipGnmkList();
	 * @return List<HashMap<String, String>>
	 */
	protected List<HashMap<String, String>> getTipGnmkList(){
		
		return dao.getTipGnmkList();
	}
	
	/**
	 * 返回指定级别功能模块
	 * @return
	 */
	protected List<HashMap<String, String>> getGnmkList(String cdjb){
		return dao.getGnmkList(cdjb);
	}
	
	
	/**
	 * 批量保存是否启用
	 * @param pkValue 对应gnmkdm
	 * @param sfqy 是否启用
	 * @see xgxt.comm.menuManage.MenuManageDAO#batchUpdateSfqy(String[], String[]);
	 * @return boolean
	 */
	protected boolean batchUpdateSfqy(String[] pkValue,String[] sfqy) {
		
		try {
			return dao.batchUpdateSfqy(pkValue, sfqy);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * 获取全部快捷方式图片
	 * @return
	 * @throws SQLException 
	 */
	protected List<String> getShortcutPic() throws SQLException{
		
		return dao.getShortcutPic();
	}
	
	
	
	/**
	 * 修改快捷方式图片
	 * @param model
	 * @return
	 */
	protected boolean updateShortcutPic(MenuManageForm model) {
		
		try {
			return dao.updateRepeatPath(model) && dao.updateShortcutPic(model);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * 根据访问路径返回功能按钮列表
	 * @param path
	 * @return
	 */
	protected List<HashMap<String,String>> getBtnList(String path){
		return dao.getBtnList(path);
	}
	
	
	/**
	 * 检查某功能模块在用户权限表是否存在
	 * @param gnmkdm
	 */
	protected void checkExistsYhqx(String gnmkdm) {
		int count = Integer.valueOf(dao.checkYhqxExists(gnmkdm));
		
		if (!(count > 0)) {
			dao.saveYhqx(gnmkdm);
		}
	}
	
}
