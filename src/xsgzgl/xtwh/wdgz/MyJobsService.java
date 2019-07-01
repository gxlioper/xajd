package xsgzgl.xtwh.wdgz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;


/**
 * 
 * <p>我的工作<p>
 * 
 * @author Penghui.Qu 2013-1-7
 */
public class MyJobsService {

	private MyJobsDao dao = new MyJobsDao();
	
	
	/**
	 * 查询我的工作
	 * @param user
	 * @return
	 */
	public List<HashMap<String,String>> getWdgzList(MyJobsForm model,User user){
//		
//		try {
//			dao.callWdgz(user);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		
		return dao.getWdgzList(model,user);
	}
	
	
	/**
	 * 查询我的申请
	 * @param user
	 * @return
	 */
	public List<HashMap<String,String>> getWdsqList(MyJobsForm model,User user){
		
		/*try {
			synchronized (user) {
				dao.callWdgz(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}*/
		
		return dao.getWdsqList(model,user.getUserName());
	}
	
}
