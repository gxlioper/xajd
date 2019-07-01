package xgxt.action.news;

import java.util.HashMap;
import java.util.List;

import common.Globals;

import xgxt.action.Base;
import xgxt.form.User;





public class NewsService {

	NewsDAO dao = new NewsDAO();
	
	/**
	 * ��ѯ����
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getNews(NewsModel model,User user) {
		
		if (Base.xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){
			return dao.getBjlhNews(model, user);
		} else {
			return dao.getNews(model,user);
		}
	}
	

	
	/**
	 * ����������������
	 * @return
	 */
	public List<HashMap<String,String>> getWwkjNews(){
		
		return dao.getWwkjNews();
	}
}
