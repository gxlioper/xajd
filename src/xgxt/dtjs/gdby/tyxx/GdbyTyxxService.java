package xgxt.dtjs.gdby.tyxx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.form.SaveForm;

public class GdbyTyxxService {
	public  void insertTyxx(SaveForm saveForm,GdbyTyxxForm gdbyTyxxForm) throws Exception{
		DAO dao=DAO.getInstance();
		dao.saveData(saveForm, gdbyTyxxForm);
	}
	
	public List<HashMap<String,String>> initTyYdLx(String ydzt){
		GdbyTyxxDao gdbyTyxxDao=new GdbyTyxxDao();
		return gdbyTyxxDao.initTyYdLx(ydzt);
	}
	
	//判断团员是否在籍
	public void checkTyZj(String xh){
		//异动状态为转出
		GdbyTyxxDao gdbyTyxxDao=new GdbyTyxxDao();
		gdbyTyxxDao.updateZjxx(xh);
	}
	
	public void delteTyyd(SaveForm saveForm,SaveForm saveModel){
		DAO dao=DAO.getInstance();
		try {
			dao.delDate(saveForm,saveForm);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}
	
	//获取团员异动状态
	public List<Map<String,String>> getTyydzt(){
		Map<String,String>map=new HashMap<String,String>();
		List<Map<String,String>>list=new ArrayList<Map<String,String>>();
		map.put("mc", "转入");
		list.add(map);
		map=new HashMap<String,String>();
		map.put("mc", "转出");
		list.add(map);
		return list;
	}
	
	//获得团员异动状态List
	public List<HashMap<String,String>>ydztList(){
		GdbyTyxxDao gdbyTyxxDao=new GdbyTyxxDao();
		return gdbyTyxxDao.ydztList();
	}
	
	//获取团员移动类型list
	public List<HashMap<String,String>> ydlxList(){
		GdbyTyxxDao gdbyTyxxDao=new GdbyTyxxDao();
		return gdbyTyxxDao.ydlxList();
	}
}
