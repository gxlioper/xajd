package xgxt.dtjs.zjcm;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.dtjs.DtjsTyService;

public class ZjcmDtjsService extends DtjsTyService {

	ZjcmDtjsDAO dao = new ZjcmDtjsDAO();

	/**
	 * 获得发展对象列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getFzdxList(String tableName, ZjcmDtjsModel model,
			String[] colList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		
		// 获得发展对象列表
		String kssj = model.getKssj();
		String zsyw = model.getZsyw();
		String other_query = "";
		
		if (!Base.isNull(kssj)) {
			other_query += " and kssj >='" + kssj + "'";
		}
		
		ArrayList<String[]> list = dao.getDtjsList(tableName, model, colList,other_query);
		ArrayList<String[]> fzdxList = new ArrayList<String[]>();
		if (list != null && list.size() != 0) {
			// 获得卫生检查等级列表
			List<HashMap<String, String>> pxzsList = getPxzsList();

			for (int i = 0; i < list.size(); i++) {
				// 重构结果集，使发展对象列表增加一列值（培训证书获得情况）
				String[] rs = new String[list.get(i).length + 1];
				for (int k = 0; k < list.get(i).length; k++) {
					rs[k] = list.get(i)[k];
				}
				if (pxzsList != null && pxzsList.size() != 0) {
					for (int j = 0; j < pxzsList.size(); j++) {
						HashMap<String, String> map = pxzsList.get(j);
						if(rs[4].equalsIgnoreCase(map.get("xh"))){
							rs[list.get(i).length] = "有";
						}
					}

				}
				if (Base.isNull(zsyw)) {
					fzdxList.add(rs);
				}else{
					if(zsyw.equalsIgnoreCase(rs[list.get(i).length])){
						fzdxList.add(rs);
					}else if("无".equalsIgnoreCase(zsyw)&&Base.isNull(rs[list.get(i).length])){
						fzdxList.add(rs);
					}
				}
			}
		}
		
		//model.getPages().setMaxRecord(fzdxList.size());
		return fzdxList;
	}
	
	/**
	 * 获得培训证书列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getPxzsList() {
		return dao.getPxzsList();
	}
	
	/**
	 * 修改党员类型
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean changeDylx(String[] key, String lx,
			HttpServletRequest request) throws Exception {

		boolean flag = false;

		String zysj = request.getParameter("zysj");

		if ("发展对象".equalsIgnoreCase(lx)) {
			String befTab = "fzdx";
			String afTab = "ybdyxxb";
			String[] zd = new String[] { "xh", "xn", "xq", "nd" };
			String[] before = new String[] { "fzdx" };
			String[] after = new String[] { "dyxxb" };
			String[] pk = new String[] { "xn", "xq", "xh" };
			String zyzd = "kssj";

			flag = changeDylx(befTab, afTab, pk, zd, key, before, after, zyzd,
					zysj);
		}

		return flag;
	}
	
	/**
	 * 提交人是否有人没有培训证书
	 * 
	 * @author luojw
	 */
	public boolean isPxzs(String[] pkValue){
		return dao.isPxzs(pkValue);
	}
	
}
