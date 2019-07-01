package xgxt.xtwh.xtwhOther;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.DAO.DAO;

public class XtwhOtherDAO extends DAO{
	/**
	 * 修改开关状态
	 * @param model
	 * @author lr
	 * @throws Exception
	 */
	public Boolean updateKgzt(XtwhOtherForm model) throws Exception {

		boolean flag = false;
		// 项目代码
		String[] xmdm = model.getXmdm();
		// 项目开关
		String[] xmkg = model.getXmkg();

		StringBuffer sql = new StringBuffer();

		if (xmdm != null && xmdm.length > 0) {

			String[] arr_sql = new String[xmdm.length];

			for (int i = 0; i < xmdm.length; i++) {
				sql = new StringBuffer();

				sql.append("update xgxtgy_gnkgb set ");
				sql.append("kgzt = '" + xmkg[i] + "' ");
				sql.append("where gndm = '" + xmdm[i] + "' ");

				arr_sql[i] = sql.toString();
			}

			flag = saveArrDate(arr_sql);
		}

		return flag;
	}

	/**
	 * 获取默认的列表
	 * @param lx
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getDefaultList(String lx){
		
		String[] dm = null;
		String[] mc = null;

		if ("kgzt".equalsIgnoreCase(lx)) {
			//开关状态
			dm = new String[] { "开", "关" };
			mc = new String[] { "开", "关" };
		}
		
		return arrayToList(dm, mc);
	}
	
	/**
	 * 判断inputvalue值是否有
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-17 下午04:10:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isInputValueExist(String inputValue){
		StringBuffer sql = new StringBuffer();	
		sql.append("  select count(1) num from xg_rcsw_hczdb where zdmc = ?");
		String num = getInstance().getOneRs(sql.toString(), new String[]{inputValue}, "num");
		if(xgxt.utils.String.StringUtils.isNotNull(num) && !("0").equals(num)){
			return true;
		}else{
			return false;
		}
	}
}
