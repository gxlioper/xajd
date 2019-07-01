package xgxt.pjpy.comm.pjpy.tzfwsz;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.utils.String.StringUtils;

public class PjpyTzfwszService {
	
	private PjpyTzfwszDAO dao = new PjpyTzfwszDAO();
	
	
	/**
	 * 保存项目调整范围设置
	 * @param model
	 * @return
	 */
	protected boolean saveTzfwsz(PjpyTzfwszForm model) {
		String xmdm = model.getXmdm();
		String[] tzfwxm = model.getTzxmdm();

		if (StringUtils.isNotNull(xmdm) && null != tzfwxm) {
			try {
				return dao.delTzfwsz(xmdm) && dao.saveTzfwsz(xmdm, tzfwxm);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * 获取非兼得项目列表
	 * @param xmdm
	 * @return
	 */
	public String[] getTzfwxm(String xmdm){
		
		if (StringUtils.isNotNull(xmdm)){
			try {
				return dao.findTzfwxm(xmdm);
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

	
	/**
	 * 项目调整范围设置查询
	 * @param model
	 * @return
	 */
	protected List<HashMap<String,String>> tzfwxmQuery(PjpyTzfwszForm model){
		
		try {
			return dao.findTzfwxmList(model);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 删除调整范围设置
	 * @param xmdms
	 * @return
	 */
	public boolean delTzfwsz(String[] xmdms){
		
		if (null != xmdms && xmdms.length > 0){
			try {
				return dao.delTzszByXmdm(xmdms);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
		
	}
}
