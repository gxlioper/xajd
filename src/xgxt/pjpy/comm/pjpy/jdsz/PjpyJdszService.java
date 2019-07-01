package xgxt.pjpy.comm.pjpy.jdsz;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.utils.String.StringUtils;

public class PjpyJdszService {

	private PjpyJdszDAO dao = new PjpyJdszDAO();
	
	
	
	/**
	 * ����������
	 * @param model
	 * @return
	 */
	protected boolean saveJdsz(PjpyJdszForm model){
		
		String xmdm = model.getXmdm();
		String[] fjddm = model.getFjddm();
		
		if (StringUtils.isNotNull(xmdm) && null != fjddm){
			try {
				return dao.delJdsz(xmdm) && dao.saveJdsz(xmdm, fjddm);
			}  catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}
	
	
	
	/**
	 * ��ȡ�Ǽ����Ŀ�б�
	 * @param xmdm
	 * @return
	 */
	public String[] getFjdxm(String xmdm){
		
		if (StringUtils.isNotNull(xmdm)){
			try {
				return dao.findFjdxm(xmdm);
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}
	
	/**
	 * �Ǽ����Ŀ���ò�ѯ
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>> fjdxmQuery(PjpyJdszForm model){
		
		try {
			return dao.findJdszList(model);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * ɾ���������
	 * @param xmdms
	 * @return
	 */
	public boolean delJdsz(String[] xmdms){
		
		if (null != xmdms && xmdms.length > 0){
			try {
				return dao.delJdszByXmdm(xmdms);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
		
	}
}
