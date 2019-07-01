package xsgzgl.xtwh.general.menu;

import java.sql.SQLException;

import xgxt.comm.CommService;
import xgxt.form.User;

/**
 * <p>
 * 功能菜单操作
 * <p>
 * @author Penghui.Qu
 */
public class MenuService extends CommService {

	private MenuDao dao = new MenuDao();
	
	
	/**
	 * 增加快捷功能菜单 
	 * @param user
	 * @param model
	 * @return
	 */
	boolean addKjfs(User user, MenuModel model){
		
		try {
			return dao.addKjfs(user.getUserName(), model.getGnmkdm());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * 删除快捷功能菜单
	 * @param user
	 * @param model
	 * @return
	 */
	boolean removeKjfs(User user, MenuModel model){
		try {
			return dao.removeKjfs(user.getUserName(), model.getGnmkdm());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * 查找用户快捷功能菜单
	 * @param user
	 * @param model
	 * @return
	 */
	String[] getKjfs(User user, MenuModel model){
		
		try {
			return dao.getKjfs(user.getUserName(), model.getGnmkdm());
		} catch (SQLException e) {
			e.printStackTrace();
			return new String[]{};
		}
	}
	
}
