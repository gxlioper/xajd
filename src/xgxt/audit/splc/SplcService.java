package xgxt.audit.splc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommService;
import xgxt.gygl.gywh.GyglGywhForm;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 审批流程Service</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: zfsoft</p>
 * <p>Author: zhuang</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2011-5-26</p>
 */
public class SplcService extends CommService {
	
	SplcDao dao = new SplcDao();
	
	
	public List<String[]>getSplcList(SplcForm myForm) throws Exception{
		
		return dao.getSplcList(myForm);
	}
	
	public HashMap<String,String>getSplc(SplcForm myForm){
		return dao.getSplc(myForm);
	}
	
	public boolean addSplc(SplcForm myForm) throws Exception{

		return dao.addSplc(myForm);
	}
	
	public boolean modiSplc(SplcForm myForm) throws Exception{

		return dao.modiSplc(myForm);
	}
	
	public boolean delSplc(SplcForm myForm) throws Exception{
		
		dao.delSplc(myForm);
		return true;
	}
	
	public List<HashMap<String,String>> getTopTr(SplcForm myForm){

		DAO dao = DAO.getInstance();
	
		String[] colListCN = null;
		String[] colListEN = null;

		colListCN=new String[]{"单据类型","名称","描述","默认"};
		colListEN= new String[]{"djlx", "mc", "ms", "sfmr"};
		return dao.arrayToList(colListEN, colListCN);
		
	}

}
