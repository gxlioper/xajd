package xgxt.pjpy.ycwsxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

public class PjpyYcwsDAO {

	private DAO dao = DAO.getInstance();
	
	private static PjpyYcwsDAO mydao = new PjpyYcwsDAO();
	
	public static PjpyYcwsDAO getInstance() {
		return mydao;
	}
	
	private ArrayList<String> values = null;
	
	public StringBuffer getWhereSql(PjpyYcwsModel model) throws Exception {
		values = new ArrayList<String>();
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(model.getXh())) {
			whereSql.append(" and xh = ?");
			values.add(model.getXh());
		}
		if (!StringUtils.isNull(model.getXn())) {
			whereSql.append(" and xn = ?");
			values.add(model.getXn());
		}
		if (!StringUtils.isNull(model.getXq())) {
			whereSql.append(" and xq = ?");
			values.add(model.getXq());
		}
		if (!StringUtils.isNull(model.getNj())) {
			whereSql.append(" and nj = ?");
			values.add(model.getNj());
		}
		if (!StringUtils.isNull(model.getXydm())) {
			whereSql.append(" and xydm = ?");
			values.add(model.getXydm());
		}
		if (!StringUtils.isNull(model.getZydm())) {
			whereSql.append(" and zydm = ?");
			values.add(model.getZydm());
		}
		if (!StringUtils.isNull(model.getBjdm())) {
			whereSql.append(" and bjdm = ?");
			values.add(model.getBjdm());
		}
		if (!StringUtils.isNull(model.getXm())) {
			whereSql.append(" and xm like '%" + DealString.toGBK(model.getXm())
					+ "%'");

		}
		if (!StringUtils.isNull(model.getJxhjxm())) {
			whereSql.append(" and jxhjxm = ?");
			values.add(model.getJxhjxm());
		}
		if (!StringUtils.isNull(model.getJxjdm())) {
			whereSql.append(" and jxjdm = ?");
			values.add(model.getJxjdm());
		}
		if (!StringUtils.isNull(model.getRychdm())) {
			whereSql.append(" and rychdm = ?");
			values.add(model.getRychdm());
		}

		return whereSql;
	}
	
	public List<String[]> getBjjxhjList(PjpyYcwsModel model, PjpyYcwsActionForm dataSearchForm) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String[] colList = new String[]{"pk", "r", "xn", "xymc", "zymc", "bjmc", "jxhjmc"};
		return dao
				.rsToVator(
						"select * from (select xn||bjdm||jxhjxm pk,rownum r,xn,xymc,zymc,bjmc,jxhjmc from view_pjpy_bjjxhj where 1=1"
								+ whereSql.toString() + ") where r<="
								+ (dataSearchForm.getPages().getStart() + dataSearchForm
										.getPages().getPageSize()) + " and r> "
								+ dataSearchForm.getPages().getStart(), values != null ? values
								.toArray(new String[0]) : new String[] {},
						colList);
	}
	
	public int getBjjxhjListNum(PjpyYcwsModel model) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String[] colList = new String[]{"pk", "rownum", "xn", "xymc", "zymc", "bjmc", "jxhjmc"};
		return dao
				.rsToVator(
						"select xn||bjdm||jxhjxm pk,rownum,xn,xymc,zymc,bjmc,jxhjmc from view_pjpy_bjjxhj where 1=1"
								+ whereSql.toString(), values != null ? values
								.toArray(new String[0]) : new String[] {},
						colList).size();
	}
	
	public boolean addBjjxhjxx(PjpyYcwsModel model, HttpServletRequest request) {
		return StandardOperation.insert("pjpy_bjjxhjb", new String[] { "xn",
				"bjdm", "jxhjxm", "hjmx", "bz" }, new String[] { model.getXn(),
				model.getBjdm(), model.getJxhjxm(),
				DealString.toGBK(model.getHjmx()),
				DealString.toGBK(model.getBz()) }, request);
	} 
	
	public boolean updateBjjxhjxx(String pkValue, PjpyYcwsModel model,
			HttpServletRequest request) throws Exception {
		return StandardOperation.update("pjpy_bjjxhjb", new String[] { "xn",
				"bjdm", "jxhjxm", "hjmx", "bz" }, new String[] {model.getXn(),
				model.getBjdm(), model.getJxhjxm(),
				DealString.toGBK(model.getHjmx()),
				DealString.toGBK(model.getBz())},
				"xn||bjdm||jxhjxm", pkValue, request);
	}
	
	public String deleteBjjxhjxx(String[] keys) throws Exception {
		String rs = "";
		String[] sql = new String[keys.length];
		for (int i = 0; i < keys.length; i++) {
			StringBuffer sb = new StringBuffer(
					"delete from pjpy_bjjxhjb where xn||bjdm||jxhjxm='");
			sb.append(keys[i]);
			sb.append("'");
			sql[i] = sb.toString();
		}
		int flag[] = dao.runBatch(sql);
		for (int i = 0; i < flag.length; i++) {
			if (flag[i] == -1) {
				rs += (i + 1) + ",";
			}
		}
		return StringUtils.isNull(rs) ? "" : rs.substring(0, rs.length() - 1);
	}
	
	public HashMap<String, String> viewBjjxhjxx(String pkValue) {
		return dao.getMapNotOut("select xn,bjdm,jxhjxm,jxhjmc,hjmx,bz,xymc,xydm,zymc,zydm,bjmc,bjdm,nj from view_pjpy_bjjxhj where xn||bjdm||jxhjxm=?", new String[]{pkValue});
	}
	
	public List<HashMap<String, String>> getTitleList(String[] enList,
			String[] cnList) {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < enList.length; i++) {
			HashMap<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("en", enList[i]);
			tmpMap.put("cn", cnList[i]);
			topList.add(tmpMap);
		}
		return topList;
	}
	
	/**
	 * 根据传入的对象生成可以供其他工具方法生成sqlwhere语句的list，
	 * 结果list中的HashMap中只有一个key/value,并且仅是对有值的属性进行数据抽取，无值的属性不在结果中
	 * @param o
	 * @return
	 */
	public StringBuffer getInstanceProperties(Object o, String[] likeList){
		values = new ArrayList<String>();
		StringBuffer whereSql = new StringBuffer("");
		Class c = o.getClass();
		Method[] methods = c.getDeclaredMethods();
		//List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		for(Method m : methods){
			try {
				String methodName = m.getName();
				if(methodName.startsWith("get") || methodName.startsWith("is")){
					String v = (String)m.invoke(o);
					if(v != null && !"".equals(v.trim())){
						whereSql.append(" and ");
						String zdName =  methodName.startsWith("get")? 
					            methodName.substring(3).toLowerCase():
					            	(methodName.startsWith("is")? 
					            			methodName.substring(2).toLowerCase():"");
					    for (String s : likeList) {// like query
					    	if (zdName.equalsIgnoreCase(s)) {
					    		whereSql.append(s);
					    		whereSql.append(" like '%");
					    		whereSql.append(v);
					    		whereSql.append("%'");
					    	} else {
					    		whereSql.append(s);
					    		whereSql.append(" = ?");
					    		values.add(v);
					    	}
					    }
					}
				}
			} catch (Exception e){
				System.out.println("调用目标对象的方法报错！");
			}
		}
		System.out.println(whereSql.toString());
		return whereSql;
	}
	
//	public static void main(String ... args) {
//		PjpyYcwsDAO dao = new PjpyYcwsDAO();
//		PjpyYcwsModel model = new PjpyYcwsModel();
//		model.setXh("001");
//		model.setXm("lt");
//		model.setNj("2002");
//		String[] likeList = new String[]{"xh", "xm"};
//		StringBuffer whereSql = dao.getInstanceProperties(model, likeList);
//		System.out.println(whereSql);
//	}
}
