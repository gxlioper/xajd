package xsgzgl.xtwh.general.menu;

import java.sql.SQLException;

import xgxt.comm.CommService;
import xgxt.form.User;

/**
 * <p>
 * ���ܲ˵�����
 * <p>
 * @author Penghui.Qu
 */
public class MenuService extends CommService {

	private MenuDao dao = new MenuDao();
	
	
	/**
	 * ���ӿ�ݹ��ܲ˵� 
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
	 * ɾ����ݹ��ܲ˵�
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
	 * �����û���ݹ��ܲ˵�
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
