package xgxt.audit.spgw;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.audit.splc.SplcForm;

public class SpgwService {
	
	SpgwDao dao = new SpgwDao();
	
	public List<String[]>getSpgwList(SpgwForm myForm) throws Exception{
		
		return dao.getSpgwList(myForm);
	}
	
	public HashMap<String,String>getSpgw(SpgwForm myForm){
		return dao.getSpgw(myForm);
	}
	
	public boolean addSpgw(SpgwForm myForm) throws Exception{

		return dao.addSpgw(myForm);
	}
	
	public boolean modiSpgw(SpgwForm myForm) throws Exception{

		return dao.modiSpgw(myForm);
	}
	
	public boolean delSpgw(SpgwForm myForm) throws Exception{
		
		dao.delSpgw(myForm);
		return true;
	}
	
	public List<String[]>getSpgwYhList(SpgwForm myForm) throws Exception{
		
		return dao.getSpgwList(myForm);
	}
	
	
	public boolean addSpgwYh(SpgwForm myForm) throws Exception{

		dao.addSpgwYh(myForm);
		return true;
	}
	
	public boolean delSpgwYh(SpgwForm myForm) throws Exception{
		
		dao.delSpgwYh(myForm);
		return true;
	}
	
	public List<HashMap<String,String>>getYhList(SpgwForm myForm){
		return dao.getYhList(myForm);
	}
	
	public List<HashMap<String,String>>getNotExistedYhList(SpgwForm myForm){
		return dao.getNotExistedYhList(myForm);
	}
	
	
	public List<HashMap<String,String>>getGwyhList(SpgwForm myForm){
		return dao.getGwyhList(myForm);
	}
	
	public List<String[]>getGwyhLists(SpgwForm myForm) throws Exception{
		
		return dao.getGwyhLists(myForm);
	}
	
	public List<HashMap<String,String>> getTopTr(SpgwForm myForm){

		DAO dao = DAO.getInstance();
	
		String[] colListCN = null;
		String[] colListEN = null;

		colListCN=new String[]{"Ãû³Æ","ÃèÊö","×éÖ¯"};
		colListEN= new String[]{"mc", "ms", "sfzz"};
		return dao.arrayToList(colListEN, colListCN);
		
	}
	
}
