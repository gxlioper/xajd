package xgxt.pjpy.comm.pjpy.sjcl.sjdr;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class PjpySjdrService {
	
	/**
	 * ��ȡ��Ҫ��������ݱ�
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getSjdrlx(){
		
		DAO dao=DAO.getInstance();
		
		String[]en={"xg_pjpy_cxfpdb","xg_pjpy_cqlb","xg_pjpy_zhcpcjb"};
		String[]cn={"���з�������","�����ʱ�","�����۲���Ϣ��"};
		
		return dao.arrayToList(en, cn);
	}
}
