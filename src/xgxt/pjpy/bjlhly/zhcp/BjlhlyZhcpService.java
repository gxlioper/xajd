package xgxt.pjpy.bjlhly.zhcp;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;

public class BjlhlyZhcpService extends CommService {

	BjlhlyZhcpDAO dao = new BjlhlyZhcpDAO();

	/**
	 * ��ѯ�۲�����Ϣ�б�
	 * 
	 * @param model
	 * @param colList
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public List<String[]> getZcjgList(BjlhlyZhcpForm model, String[] colList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		return dao.getZcjgList(model, colList);
	}

	/**
	 * ִ���ۺ��ּܷ���洢����
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean zhfjs(BjlhlyZhcpForm model) throws Exception {

		return dao.zhfjs(model);
	}

	/**
	 * ��ȡѧ�ꡢѧ�ڷ�����Ϣ
	 * 
	 * @return
	 */
	public List<HashMap<String,String>>getGroupList(BjlhlyZhcpForm model){
		
		return dao.getGroupList(model);
	}
	
	/**
	 * ��ȡѧ���ɼ���Ϣ
	 * 
	 * @return
	 */
	public List<HashMap<String,String>>getCjList(BjlhlyZhcpForm model){
		
		return dao.getCjList(model);
	}
}
