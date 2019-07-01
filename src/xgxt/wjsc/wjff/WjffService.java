package xgxt.wjsc.wjff;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import common.Globals;

import xgxt.base.DealString;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xgxt.wjsc.WjscDataAccessAction;

public class WjffService {

	private WjffDAO dao = new WjffDAO();
	
	
	/**
	 * 文件发放查询
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> queryFile(WjffModel model,User user){
		
		return dao.queryFile(model, user);
	}
	
	
	
	/**
	 * 用户组列表
	 * @param xxdm
	 * @return
	 */
	public List<HashMap<String,String>> getYhzList(String xxdm){
		
		
		if (Globals.XXDM_ZGKYDX.equals(xxdm)){
			//矿大的不包含用人单位 
			return dao.getYhzNotExistsYrdwAndStu();
		} else {
			
			return dao.getYhzNotExistsStu();
		}
		
	}
	
	
	/**
	 * 用户列表
	 * @param xxdm
	 * @return
	 */
	public List<HashMap<String, String>> getYhList(String xxdm){
		
		if (Globals.XXDM_ZGKYDX.equals(xxdm)){
			//矿大的不包含用人单位 
			return dao.getYhNotExistsYrdwAndStu();
		} else {
			
			return dao.getYhNotExistsStu();
		}
	}
	
	
	
	/**
	 * 文件批量删除操作
	 * @param wjh
	 * @return
	 */
	public boolean delFiles(String[] wjh,boolean isDelFile){
		if (null != wjh && wjh.length > 0){
			
			if (isDelFile){
				for (int i = 0 ; i < wjh.length ; i++){
					String wjsclj = WjscDataAccessAction.getFilePath(wjh[i]);
					if (StringUtils.isNotNull(wjsclj) && (new File(wjsclj).exists())) {
						try {
							new File(wjsclj).delete();
						} catch (SecurityException e) {
							e.printStackTrace();
						}
					}
				}
			}
			try {
				return dao.delWjs(wjh);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		return false;
	}
	
	/**
	 * 文件发放查询条件
	 * @param model
	 * @param user
	 * @return
	 */
	public HashMap<String , Object> getQueryFileSql(WjffModel model,User user){
	
		return dao.getQueryFileSql(model, user);
	}
}
