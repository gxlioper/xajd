package xgxt.comm.menuManage;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class MenuManageService {

	MenuManageDAO dao = new MenuManageDAO();

	/**
	 * ����ȫ��һ���˵�
	 * @see xgxt.comm.menuManage.MenuManageDAO#getTipGnmkList();
	 * @return List<HashMap<String, String>>
	 */
	protected List<HashMap<String, String>> getTipGnmkList(){
		
		return dao.getTipGnmkList();
	}
	
	/**
	 * ����ָ��������ģ��
	 * @return
	 */
	protected List<HashMap<String, String>> getGnmkList(String cdjb){
		return dao.getGnmkList(cdjb);
	}
	
	
	/**
	 * ���������Ƿ�����
	 * @param pkValue ��Ӧgnmkdm
	 * @param sfqy �Ƿ�����
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
	 * ��ȡȫ����ݷ�ʽͼƬ
	 * @return
	 * @throws SQLException 
	 */
	protected List<String> getShortcutPic() throws SQLException{
		
		return dao.getShortcutPic();
	}
	
	
	
	/**
	 * �޸Ŀ�ݷ�ʽͼƬ
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
	 * ���ݷ���·�����ع��ܰ�ť�б�
	 * @param path
	 * @return
	 */
	protected List<HashMap<String,String>> getBtnList(String path){
		return dao.getBtnList(path);
	}
	
	
	/**
	 * ���ĳ����ģ�����û�Ȩ�ޱ��Ƿ����
	 * @param gnmkdm
	 */
	protected void checkExistsYhqx(String gnmkdm) {
		int count = Integer.valueOf(dao.checkYhqxExists(gnmkdm));
		
		if (!(count > 0)) {
			dao.saveYhqx(gnmkdm);
		}
	}
	
}
