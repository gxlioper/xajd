package xgxt.rcsw.kqgl;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

public class KqglService {

	KqglDAO dao = new KqglDAO();
	
	public List<HashMap<String,String>> getList(String flg) {
		
		List<HashMap<String,String>> list = null;
		
		if ("kqlx".equals(flg)) {
			String[] dm = new String[] {"迟到","旷课","旷寝"};
			list = dao.arrayToList(dm, dm);
		} else if ("kqlxdm".equals(flg)){
			list = dao.getWhList("xg_rcsw_kqlxdmb", "dm", "mc", "", "", "");
		}
		return list;
	}

	
	/**
	 * 学生考勤统计
	 * @param tjlb
	 * @param model
	 * @return
	 */
	public List<String[]> getXskqTj(String tjlb,KqglForm model){
		
		if ("stu".equals(tjlb)) {
			return dao.getKqtjByStu(model);
		} else if ("xy".equals(tjlb)) {
			return dao.getKqtjByXy(model);
		} else if ("zy".equals(tjlb)) {
			return dao.getKqtjByZy(model);
		} else if ("bj".equals(tjlb)) {
			return dao.getKqtjByBj(model);
		} else if ("all".equals(tjlb)){
			return dao.getKqtjByAll(model);
		}
		
		return null;
	}
	
	
	/**
	 * 自已维护的考勤类型名称
	 * @param kqlxList
	 * @return
	 */
	protected String[] getKqlxTopTr() {
		List<HashMap<String, String>> kqlxList = dao.getKqlxList();
		
		return dao.getKqlxTopTr(kqlxList);
	}
	
	/**
	 * 学生考勤查询
	 * @param model
	 * @param user
	 * @param outPutList
	 * @return
	 * @throws Exception
	 * @author honglin
	 * @date 2013-01-22
	 */
	public List<String[]> getKqglList(KqglForm model, User user, String[] outPutList) throws Exception {
		return dao.getKqglList(model, user, outPutList);
	}
}
