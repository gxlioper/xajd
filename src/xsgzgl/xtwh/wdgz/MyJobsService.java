package xsgzgl.xtwh.wdgz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;


/**
 * 
 * <p>�ҵĹ���<p>
 * 
 * @author Penghui.Qu 2013-1-7
 */
public class MyJobsService {

	private MyJobsDao dao = new MyJobsDao();
	
	
	/**
	 * ��ѯ�ҵĹ���
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
	 * ��ѯ�ҵ�����
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
