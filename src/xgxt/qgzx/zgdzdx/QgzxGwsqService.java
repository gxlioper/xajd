package xgxt.qgzx.zgdzdx;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.qgzx.dao.QgzxDao;

public class QgzxGwsqService {
	QgzxGwsqDAO qgzxgwsqdao = new QgzxGwsqDAO();
	
	/**��λ����������Ϣ����
	 * @throws Exception */
	public boolean gwxx_saveService(HashMap<String, String> tmp) throws Exception{
		String xh = tmp.get("xh");
		String xn = tmp.get("xn");
		String nd = tmp.get("nd");
		String xq = tmp.get("xq");
		
		String i=qgzxgwsqdao.getgwxxcount(xh);
		String qgzxNum = qgzxgwsqdao.getQgzxcount(xh,xn, nd, xq);
		if("0".equalsIgnoreCase(qgzxNum)){
			boolean res = qgzxgwsqdao.qgzx_save(tmp);
			if(!res){
				return res;
			}
		}
		return qgzxgwsqdao.gwxx_save(tmp,i);
	}
	
	/**
	 * �ж�ѧ���Ƿ���������
	 * @param xh
	 * @return boolean
	 * */
	public boolean checkIsKns(String xh){
		return qgzxgwsqdao.isKns(xh);
	}
	
	/**
	 * ��ȡ�������λ�б�
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getKsqgwList(){
		QgzxDao dao = new QgzxDao();
		return dao.getKsqgwList();
	}
	
	/**
	 * ��ȡ������ò�б�
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getZzmmList(){
		QgzxDao dao = new QgzxDao();
		return dao.getZzmmList();
	}
}
