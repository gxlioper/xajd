package xgxt.szdw.ghxy.njzrwh;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class GhxyNjzrwhDao {
	
	public List<HashMap<String,String>>getXxbmdm(){
		DAO dao=DAO.getInstance();
		String sql="select bmdm,bmmc from zxbz_xxbmdm";
		return dao.getList(sql, new String[]{}, new String[]{"bmdm","bmmc"});
	}
	
	//����ְ���Ÿ����꼶���εĹ�Ͻ�꼶
	public List<HashMap<String,String>>getFdyNj(String zgh){
		DAO dao=DAO.getInstance();
		String sql="select nj from ghxy_njfdyb where zgh =?";
		return dao.getList(sql, new String[]{zgh}, new String[]{"nj"});
	}
}
