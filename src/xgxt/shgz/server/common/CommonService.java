
package xgxt.shgz.server.common;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.szdw.dao.common.CommonDAO;

public class CommonService {
	
	/**
	* <p>Title: ѧ������ϵͳ</p>
	* <p>Description: ѧ����Ϣ����˼������-����service��</p>
	* <p>Copyright: Copyright (c) 2008</p>
	* <p>Company: zfsoft</p>
	* @author ³��
	* @version 1.0
	*/
	
	CommonDAO dao = new CommonDAO();
	
	public HashMap<String, String> inputXnNjXq(HashMap<String, String> rs){
	//    �ж�rs��ѧ�꣬��ȣ�ѧ���Ƿ���ֵ��������뵱ǰѧ�꣬��ȣ�ѧ��
		String xn           = Base.currXn;
		String xq           = Base.currXq;
		String nd           = Base.currNd;
		if(rs.get("xn").equalsIgnoreCase("")){
			rs.put("xn", xn);
		}
		
		if(rs.get("nd").equalsIgnoreCase("")){
			rs.put("nd", nd);
		}
		
		if(rs.get("xq").equalsIgnoreCase("")){
			rs.put("xq", xq);
		}
		return rs;
	}
	
	public List getShztList() {
		//    �õ����״̬�б�
			List ShztList = dao.getChkList(3);//��ͷ 
			return ShztList;
	}

}
