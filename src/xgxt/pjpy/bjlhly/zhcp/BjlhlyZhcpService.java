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
	 * 查询综测结果信息列表
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
	 * 执行综合总分计算存储过程
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean zhfjs(BjlhlyZhcpForm model) throws Exception {

		return dao.zhfjs(model);
	}

	/**
	 * 获取学年、学期分组信息
	 * 
	 * @return
	 */
	public List<HashMap<String,String>>getGroupList(BjlhlyZhcpForm model){
		
		return dao.getGroupList(model);
	}
	
	/**
	 * 获取学生成绩信息
	 * 
	 * @return
	 */
	public List<HashMap<String,String>>getCjList(BjlhlyZhcpForm model){
		
		return dao.getCjList(model);
	}
}
