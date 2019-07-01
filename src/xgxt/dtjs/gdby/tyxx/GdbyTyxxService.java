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
	
	//�ж���Ա�Ƿ��ڼ�
	public void checkTyZj(String xh){
		//�춯״̬Ϊת��
		GdbyTyxxDao gdbyTyxxDao=new GdbyTyxxDao();
		gdbyTyxxDao.updateZjxx(xh);
	}
	
	public void delteTyyd(SaveForm saveForm,SaveForm saveModel){
		DAO dao=DAO.getInstance();
		try {
			dao.delDate(saveForm,saveForm);
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}
	
	//��ȡ��Ա�춯״̬
	public List<Map<String,String>> getTyydzt(){
		Map<String,String>map=new HashMap<String,String>();
		List<Map<String,String>>list=new ArrayList<Map<String,String>>();
		map.put("mc", "ת��");
		list.add(map);
		map=new HashMap<String,String>();
		map.put("mc", "ת��");
		list.add(map);
		return list;
	}
	
	//�����Ա�춯״̬List
	public List<HashMap<String,String>>ydztList(){
		GdbyTyxxDao gdbyTyxxDao=new GdbyTyxxDao();
		return gdbyTyxxDao.ydztList();
	}
	
	//��ȡ��Ա�ƶ�����list
	public List<HashMap<String,String>> ydlxList(){
		GdbyTyxxDao gdbyTyxxDao=new GdbyTyxxDao();
		return gdbyTyxxDao.ydlxList();
	}
}
