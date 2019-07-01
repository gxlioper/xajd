package xgxt.szdw.ghxy.njzrwh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.dtjs.czxx.tyxx.TyxxbcModel;
import xgxt.form.SaveForm;
import xgxt.xsgygl.ghxy.qyfdywh.GhxyQyfdywhDao;

public class GhxyNjzrwhService {
	
	/**
	 * ��ȡѧУ�Ĳ��Ŵ��롢����List
	 */
	public void getXxbmdm(HttpServletRequest request){
		GhxyNjzrwhDao dao=new GhxyNjzrwhDao();
		request.setAttribute("xxbmdmList", dao.getXxbmdm());
	}
	
	public boolean saveTyxx(SaveForm saveForm,Object ghxyNjzrwhModel) throws Exception{
		DAO dao = DAO.getInstance();
		return dao.saveData(saveForm, ghxyNjzrwhModel);
	}
	
	//����ְ���ţ������꼶���εĹ�Ͻ�꼶
	public List<HashMap<String,String>> getFdyNj(String zgh){
		GhxyNjzrwhDao dao=new GhxyNjzrwhDao();
		return dao.getFdyNj(zgh);
	}
	
	public boolean isNjfdy(String userName){
		GhxyNjzrwhDao dao=new GhxyNjzrwhDao();
		if(dao.getFdyNj(userName).size()>0){
			return true;
		}else{
			return false;
		}
	}
}
