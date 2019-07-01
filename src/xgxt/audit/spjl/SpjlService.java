package xgxt.audit.spjl;

import java.util.HashMap;
import java.util.List;

import xgxt.audit.spgw.SpgwForm;


/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 审批记录Service</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: zfsoft</p>
 * <p>Author: zhuang</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2011-5-26</p>
 */
public class SpjlService {
	
	SpjlDao dao = new SpjlDao();
	
	public List<String[]>getSpjlList(SpjlForm myForm) throws Exception{
		
		return dao.getSpjlList(myForm);
	}
	
	public HashMap<String,String>getSpjl(SpjlForm myForm){
		return dao.getSpjl(myForm);
	}
	
	public boolean addSpjl(SpjlForm myForm) throws Exception{

		return dao.addSpjl(myForm);
	}
	
	public boolean modiSpjl(SpjlForm myForm) throws Exception{

		return dao.modiSpjl(myForm);
	}
	
	public boolean delSpjl(SpjlForm myForm) throws Exception{
		
		dao.delSpjl(myForm);
		return true;
	}
	
	public List<HashMap<String,String>>getSpbzList(SpjlForm myForm){
		return dao.getSpbzList(myForm);
	}

}
