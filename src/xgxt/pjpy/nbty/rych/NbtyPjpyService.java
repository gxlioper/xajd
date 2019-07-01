package xgxt.pjpy.nbty.rych;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.pjpy.nbty.NbtyPjpyForm;
import xgxt.utils.CommonQueryDAO;

public class NbtyPjpyService {
	public HashMap<String, String> getStuInfo(String xh)
	{
		CommonQueryDAO commonQueryDao =new CommonQueryDAO();
		return commonQueryDao.getStuInfo(xh);
	}
	
	public List<HashMap<String,String>>serv_getRychList(){
		RychDAO rychDao=new RychDAO();
		return rychDao.getRychList();
	}
	public List<HashMap<String,String>>getZzmmList(){
		RychDAO rychDao=new RychDAO();
		return rychDao.getZzmmList();
	}
	
	
}
