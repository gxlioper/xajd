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
	 * �ļ����Ų�ѯ
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> queryFile(WjffModel model,User user){
		
		return dao.queryFile(model, user);
	}
	
	
	
	/**
	 * �û����б�
	 * @param xxdm
	 * @return
	 */
	public List<HashMap<String,String>> getYhzList(String xxdm){
		
		
		if (Globals.XXDM_ZGKYDX.equals(xxdm)){
			//���Ĳ��������˵�λ 
			return dao.getYhzNotExistsYrdwAndStu();
		} else {
			
			return dao.getYhzNotExistsStu();
		}
		
	}
	
	
	/**
	 * �û��б�
	 * @param xxdm
	 * @return
	 */
	public List<HashMap<String, String>> getYhList(String xxdm){
		
		if (Globals.XXDM_ZGKYDX.equals(xxdm)){
			//���Ĳ��������˵�λ 
			return dao.getYhNotExistsYrdwAndStu();
		} else {
			
			return dao.getYhNotExistsStu();
		}
	}
	
	
	
	/**
	 * �ļ�����ɾ������
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
	 * �ļ����Ų�ѯ����
	 * @param model
	 * @param user
	 * @return
	 */
	public HashMap<String , Object> getQueryFileSql(WjffModel model,User user){
	
		return dao.getQueryFileSql(model, user);
	}
}
