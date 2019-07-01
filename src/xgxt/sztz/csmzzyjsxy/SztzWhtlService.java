package xgxt.sztz.csmzzyjsxy;

import java.util.HashMap;
import java.util.List;

public class SztzWhtlService {
	
	SztzWhtlDAO dao=new SztzWhtlDAO();
	
	public List<HashMap<String,String>>getXmList(String xn,String xq){
			
		return dao.getXmList(xn, xq);
	}
	
	public List<HashMap<String,String>>getJxList(String xmid){
		
		return dao.getJxList(xmid);
	}
	
}
