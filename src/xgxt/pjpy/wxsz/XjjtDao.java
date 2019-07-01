package xgxt.pjpy.wxsz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

/**
 * 先进集体
 * @author 屈朋辉
 * @version 1.0
 */
public class XjjtDao {

	/**
	 * 获取数据
	 * @param tableName
	 * @param model
	 * @param colList
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public ArrayList<String[]> getXjjtList(String tableName, XjjtModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		// TODO 自动生成方法存根
		String[] queryList = new String[] { "jtrs", "xydm", "fzls", "xysh",
				"xxsh", "xn", "xq" };
		String[] queryLikeList = new String[] { "jtmc" };
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString();
		
		return CommonQueryDAO.commonQuery(tableName, query, myQuery
				.getInputList(), colList, "", model);
	}

	/**
	 * 获取单条数据赋值给对象并返回
	 * @param model
	 * @param pkValue
	 * @return
	 */
	public XjjtModel getOneXjjt(XjjtModel model, String pkValue) {
		// TODO 自动生成方法存根
		String[] colList = new String[] { "jtmc", "jtrs", "xydm", "fzls",
				"zysj", "yxyj", "xgcyj", "xyyj" };
		DAO dao = new DAO();
		List<HashMap<String, String>> list = dao.getList(
				"select * from view_xjjt where jtmc||xn||xq=?",
				new String[] { pkValue }, colList);
		model.setJtmc(list.get(0).get("jtmc"));
		model.setJtrs(list.get(0).get("jtrs"));
		model.setXydm(list.get(0).get("xydm"));
		model.setFzls(list.get(0).get("fzls"));
		model.setZysj(list.get(0).get("zysj"));
		model.setYxyj(list.get(0).get("yxyj"));
		model.setXgcyj(list.get(0).get("xgcyj"));
		model.setXyyj(list.get(0).get("xyyj"));
		return model;
	}
	
	public static void main(String[] args) {
		Date d1 = new Date(java.sql.Date.valueOf("2010-4-26").getTime());
		Date d2 = new Date(java.sql.Date.valueOf("2010-4-26").getTime());
		System.out.println(d2.before(d1));
		//DateFormat.getDateTimeInstance().format(new Date());
	}
	
}
