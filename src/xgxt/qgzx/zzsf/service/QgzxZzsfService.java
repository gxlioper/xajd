package xgxt.qgzx.zzsf.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.qgzx.form.QgzxForm;
import xgxt.qgzx.zzsf.dao.QgzxZzsfDAO;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����ʦ��ѧԺ�ڹ���ѧService</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-06-24</p>
 */
public class QgzxZzsfService {
	QgzxZzsfDAO dao = new QgzxZzsfDAO();
	
	/**
	 * ��ѯѧ��������Ϣ�͵�ǰʱ��
	 * */
	public HashMap<String, String> getBaseInfo(String xh){
		return dao.getBaseInfo(xh);
	}
	
	/**
	 * ��ѯ��λ�б�
	 * */
	public List getGwList(){
		return dao.getGwList();
	}
	
	/**
	 * ��ѯѧ����λ��Ϣ
	 * @param pkV
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getXsgwxx(String pkV){
		return dao.getXsgwxx(pkV);
	}
	
	/**
	 * �����λ��Ϣ
	 * @param model
	 * @param request
	 * @return boolean
	 * @throws Exception
	 * */
	public boolean saveXsgwxx(QgzxForm model, HttpServletRequest request) throws Exception{
		boolean result = false;
		String tableName = "xsgwxxb";
		String[] columns = {};
		String[] values = {};
		String primaryKey = "xh||gwdm||'-'||gwsbsj";
		String pkValue = "";
				
		model.setXmdm(DealString.toGBK(model.getXmdm()));
		model.setLxdh(DealString.toGBK(model.getLxdh()));
		model.setXssq(DealString.toGBK(model.getXssq()));
		model.setGzjl(DealString.toGBK(model.getGzjl()));
		//��ȡ��λ��Ϣ
		HashMap<String, String> map = dao.getGwxx(model.getXmdm());
		boolean isKns = dao.isKns(model.getXh());
		String kns = isKns == true ? "��" : "��";
		
		if(dao.checkXsgwExists(model.getXh(), model.getXmdm())){//�����¼�Ѿ����ڽ����޸Ĳ���
			columns = new String[]{"lxdh","gzjl","xssq","bz","sfpks"};
			values = new String[]{model.getLxdh(), model.getGzjl(), model.getXssq(), model.getBz(),kns};
			pkValue = model.getXh() + model.getXmdm();
			result = StandardOperation.update(tableName, columns, values, primaryKey, pkValue, request);
		}else{//��¼�����ڽ������Ӳ���
			HashMap<String, String> tmpMap = dao.getQgzxcs();
			
			columns = new String[]{"xn","nd","xq","xh", "gwdm" , "gwsbsj", "lxdh", "gzjl", "xssq", "bz", "fdyyj", "xyyj", "xxyj","sfpks"};
			values = new String[]{tmpMap.get("xn"),tmpMap.get("nd"),tmpMap.get("xq"),model.getXh(), map.get("gwdm"), map.get("gwsbsj"),model.getLxdh(),model.getGzjl(),model.getXssq(),model.getBz(),"ͨ��","ͨ��","ͨ��",kns};
			result = StandardOperation.insert(tableName, columns, values, request);
		}
		
		return result;
	}
}
