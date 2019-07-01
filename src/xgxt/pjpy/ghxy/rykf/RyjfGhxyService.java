package xgxt.pjpy.ghxy.rykf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.dtjs.gdby.tygl.BasicExtendService;
import xgxt.form.SaveForm;
import xgxt.pjpy.ghxy.ryjf.grryf.RyjfModel;
import xgxt.utils.CommonQueryDAO;

/**
 * 
 */
public class RyjfGhxyService extends BasicExtendService {

	/**
	 * 根据级别获得项目列表
	 * 
	 * @param jb
	 * @return
	 */
	public List<HashMap<String, String>> getXmList(String jb) {
		String[] output = new String[] { "xmdm", "xmmc", "xmjb" };
		String query = " where xmjb=?";
		return CommonQueryDAO.commonQueryforList("ghxy_ryjfxmb", query,
				new String[] { jb }, output, "");
	}

	/**
	 * 批量保存学生荣誉减分
	 * 
	 * @param model
	 * @return
	 */
	public boolean saveXsRyjf(RyjfModel model, String dqqx) {
		boolean flag = false;
		String pk = "xh||xn||xq||xmdm||njbzrsh";
		
		List<HashMap<String,String>> xmList = null;
		if("fdy".equalsIgnoreCase(dqqx)){
			xmList = getXmList("1");
		}else{
			xmList = getXmList("2");
		}
		
		String[] pkValue = new String[xmList.size()];
		for(int i=0;i<xmList.size();i++){
			pkValue[i] = model.getXh() + model.getXn()+ model.getXq()+ xmList.get(i).get("xmdm") + "未审核";
		}

		String[] onezd = new String[] { "xn", "xh", "xq" };
		String[] arrzd = new String[] { "xmdm", "jfsy", "bz", "sj", "jf" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName("ghxy_ryjfb");
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);

		DAO dao = DAO.getInstance();

		try {
			flag = dao.saveData(saveForm, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 根据该录入人员可录入的选项获得某学生减分信息
	 * 
	 * @param xh
	 * @param xn
	 * @param xq
	 * @param xmList
	 * @return
	 */
	public List<HashMap<String, String>> getXsjfxx(String xh, String xn,
			String xq, String[] shzt, List<HashMap<String, String>> xmList) {
		String tableName = "view_ghxy_ryjf";
		String[] colList = new String[] { "xmdm","xmmc", "jfsy", "jf", "bz", "sj","njbzrsh" };
		StringBuilder query = new StringBuilder(" where xh=? and xn=? and xq=?");
		String[] input = null;
		
		if(shzt == null){
			input = new String[] {xh, xn, xq};
		}else{
			input = new String[shzt.length+3];
			input[0] = xh;
			input[1] = xn;
			input[2] = xq;
			
			for(int i=0;i<shzt.length;i++){
				input[3+i] = shzt[i];
				
				if(i==0){
					query.append(" and (njbzrsh=?");
				}else{
					query.append(" or njbzrsh=?");
				}
				
				if(i==shzt.length-1){
					query.append(")");
				}
			}
		}
		
		List<HashMap<String, String>> jfList = CommonQueryDAO
				.commonQueryforList(tableName, query.toString(), input, colList, "");
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		// 根据xmList的项目，获取这些项目的减分信息
		for (int i = 0; i < xmList.size(); i++) {
			HashMap<String, String> xmMap = xmList.get(i);

			for (int j = 0; j < jfList.size(); j++) {
				HashMap<String, String> jfMap = jfList.get(j);
				if (xmMap != null
						&& jfMap != null
						&& xmMap.get("xmdm")
								.equalsIgnoreCase(jfMap.get("xmdm"))) {
					list.add(jfMap);
				}
			}
		}
		
//		while(list.size()<xmList.size()){
//			list.add(new HashMap<String, String>());
//		}
		return list;
	}

	/**
	 * 获得某学生单条减分信息
	 * 
	 * @param pkValue
	 * @return
	 */
	public Map<String, String> getXsjfxxone(String pkValue) {
		String pk = "id";
		String[] colList = new String[] { "xh", "xm", "nj", "xymc", "zymc",
				"bjmc", "xq", "xqmc", "xn", "xmdm", "xmmc", "jf", "jfsy", "bz",
				"sj", "xb", "xxsh", "njbzrsh" };
		return CommonQueryDAO.commonQueryOne("view_ghxy_ryjf", colList, pk,
				pkValue);
	}
	
	public List<HashMap<String,String>> getLdlb(String xqdm,String yhm){
		DAO dao=DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select '' dm, '---请选择---'mc from dual union").
			append(" select lddm dm,ldmc mc from view_gyfdyxx where xqdm=? and yhm=?").
			append("order by dm nulls first,dm");
		List<HashMap<String,String>> list = dao.getList(sql.toString(), new String[]{xqdm,yhm},new String[]{"dm","mc"});
		for(int i=0;i<list.size();i++){
			Map<String,String> map = list.get(i);
			if(map.get("dm") == null){
				map.put("dm", "");
			}
		}
		return list; 
	}
	
	public List<HashMap<String,String>> getCslb(String lddm){
		DAO dao=DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select '' dm, '---请选择---'mc from dual union").
			append(" select cs dm,cs mc from view_ssxx where lddm=?").
			append("order by dm nulls first,dm");
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { lddm }, new String[] { "dm", "mc" });
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = list.get(i);
			if (map.get("dm") == null) {
				map.put("dm", "");
			}
		}
		return list;
	}
	
	public List<HashMap<String,String>> getQslb(String lddm, String cs){
		DAO dao=DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select '' dm, '---请选择---'mc from dual union").
			append(" select qsh dm,qsh mc from view_ssxx where lddm=? and cs=?").
			append("order by dm nulls first,dm");
		List<HashMap<String,String>> list = dao.getList(sql.toString(), new String[]{lddm,cs},new String[]{"dm","mc"});
		
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = list.get(i);
			if (map.get("dm") == null) {
				map.put("dm", "");
			}
		}
		return list;
	}
}
