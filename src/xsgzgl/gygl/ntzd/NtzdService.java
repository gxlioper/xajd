package xsgzgl.gygl.ntzd;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


public class NtzdService extends SuperServiceImpl<NtzdForm, NtzdDAO>{
	
	NtzdDAO dao = new NtzdDAO();
	public NtzdService() {
		super.setDao(dao);
	}
	/** 
	 * @描述:取得月考核系数管理 列表
	 * @作者：qlm
	 * @日期：2013-9-26 
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getNykhxsPageList(NtzdForm myForm) throws Exception {
		return dao.getNykhxsPageList(myForm);
	}
	
	/** 
	 * @描述:取得班级月考核得分 列表
	 * @作者：qlm
	 * @日期：2013-9-26 
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws Exception 
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getBjydfPageList(NtzdForm myForm) throws Exception {
		if(NtzdAction._EXPORT_FLGE.equals(myForm.getType())){//zcl 处理导出
			return dao.getBjydfPageList(myForm, true);
		}
		return dao.getBjydfPageList(myForm);
	}

	/**
	 * 
	 * @描述:判断该年月数据库是否存在（false:不存在；true:存在）
	 * @作者：qilm
	 * @日期：2013-9-26 
	 * @param ny
	 * @return
	 * boolean 返回类型 
	 * @throws SQLException 
	 */
	public boolean getCountNykhxs(String ny) throws SQLException {
		int count = dao.getCountNykhxs(ny);
		if(count!=0){
			return true;
		}
		return false;
	}
	
	/** 
	 * @描述:取得学院月考核得分 列表
	 * @作者：qlm
	 * @日期：2013-9-26 
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws Exception 
	 * @throws Exception 
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getXyydfPageList(NtzdForm myForm) throws Exception {
		if(NtzdAction._EXPORT_FLGE.equals(myForm.getType())){//zcl 处理导出
			return dao.getXyydfPageList(myForm, true);
		}
		return dao.getXyydfPageList(myForm);
	}
	
	/** 
	 * @描述:取得特殊宿舍 列表
	 * @作者：qlm
	 * @日期：2013-9-26 
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws Exception 
	 * @throws Exception 
	 * @throws Exception 
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getTsqstjPageList(NtzdForm myForm) throws Exception {
		if(NtzdAction._EXPORT_FLGE.equals(myForm.getType())){//zcl 处理导出
			return dao.getTsqstjPageList(myForm, true);
		}
		return dao.getTsqstjPageList(myForm);
	}
}
