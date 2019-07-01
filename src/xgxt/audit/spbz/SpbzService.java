package xgxt.audit.spbz;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.audit.splc.SplcForm;
import xgxt.comm.CommService;


/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 审批步骤Service</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: zfsoft</p>
 * <p>Author: zhuang</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2011-5-26</p>
 */
public class SpbzService extends CommService {
	
	SpbzDao dao = new SpbzDao();
	
	public List<String[]>getSpbzList(SpbzForm myForm) throws Exception{
		
		return dao.getSpbzList(myForm);
	}
	
	public HashMap<String,String>getSpbz(SpbzForm myForm){
		return dao.getSpbz(myForm);
	}
	
	public boolean addSpbz(SpbzForm myForm) throws Exception{

		return dao.addSpbz(myForm);
	}
	
	public boolean modiSpbz(SpbzForm myForm) throws Exception{

		return dao.modiSpbz(myForm);
	}
	
	public boolean delSpbz(SpbzForm myForm) throws Exception{
		
		dao.delSpbz(myForm);
		return true;
	}
	
	public List<HashMap<String,String>>getSpgwList(){
		return dao.getSpgwList();
	}
	
	public List<HashMap<String,String>> getTopTr(SpbzForm myForm){

		DAO dao = DAO.getInstance();
	
		String[] colListCN = null;
		String[] colListEN = null;

		colListCN=new String[]{"序号","审批岗位名称"};
		colListEN= new String[]{"xh", "spgwmc"};
		return dao.arrayToList(colListEN, colListCN);
		
	}

}
