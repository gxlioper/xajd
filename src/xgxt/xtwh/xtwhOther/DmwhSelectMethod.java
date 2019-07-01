package xgxt.xtwh.xtwhOther;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.base.DealString;

/**
 * 代码维护一些固定的下拉框方法
 */
public class DmwhSelectMethod {

	// 学年
	public static List<HashMap<String, String>> getXnList() {
		ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		String xn = null;
		map.put("xn", "");
		aList.add(map);
		int currentNd = Integer.parseInt(DealString.getDateTime().substring(0,
				4));
		for (int i = currentNd - 5; i < currentNd + 5; i++) {
			map = new HashMap<String, String>();
			xn = String.valueOf(i) + "-" + String.valueOf(i + 1);
			map.put("dm", xn);
			map.put("mc", xn);
			aList.add(map);
		}
		return aList;
	}

	// 年度
	public static List<HashMap<String, String>> getNdList() {
		ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		String nd = null;
		map.put("nd", "");
		aList.add(map);
		int currentNd = Integer.parseInt(DealString.getDateTime().substring(0,
				4));
		for (int i = currentNd - 5; i < currentNd + 5; i++) {
			map = new HashMap<String, String>();
			nd = String.valueOf(i);
			map.put("dm", nd);
			map.put("mc", nd);
			aList.add(map);
		}
		return aList;
	}

	/**
	 * 是否在校
	 */
	public static List<HashMap<String, String>> getSfList() {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("dm", "是");
		map.put("mc", "是");
		list.add(map);

		map = new HashMap<String, String>();

		map.put("dm", "否");
		map.put("mc", "否");
		list.add(map);

		return list;
	}
	
	/**
	 * 是否
	 * 
	 * @return
	 */
	public static List<HashMap<String, String>> getIsNot() {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("dm", "yes");
		map.put("mc", "是");
		list.add(map);

		map = new HashMap<String, String>();

		map.put("dm", "no");
		map.put("mc", "否");
		list.add(map);

		return list;
	}

	/**
	 * 是否
	 * 
	 * @return
	 */
	public static List<HashMap<String, String>> getIsNotByNumb() {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();

		map.put("dm", "1");
		map.put("mc", "是");
		list.add(map);

		map = new HashMap<String, String>();

		map.put("dm", "0");
		map.put("mc", "否");
		list.add(map);

		return list;
	}

	/**
	 * 是否在校
	 */
	public static List<HashMap<String, String>> getSfzxList() {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dm", "在校");
		map.put("mc", "在校");
		list.add(map);

		map = new HashMap<String, String>();

		map.put("dm", "不在校");
		map.put("mc", "不在校");
		list.add(map);

		return list;
	}
	
	/**
	 * 审核部门
	 */
	public static List<HashMap<String, String>> getShbm() {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dm", "校审");
		map.put("mc", "校审");
		list.add(map);

		map = new HashMap<String, String>();

		map.put("dm", "院审");
		map.put("mc", "院审");
		list.add(map);

		return list;
	}

	/**
	 * 归档资料类别 获取操作人
	 */
	public static List<HashMap<String, String>> getCzrList() {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dm", "学校");
		map.put("mc", "学校");
		list.add(map);

		map = new HashMap<String, String>();

		map.put("dm", "学院");
		map.put("mc", "学院");
		list.add(map);

		map = new HashMap<String, String>();

		map.put("dm", "辅导员");
		map.put("mc", "辅导员");
		list.add(map);

		return list;
	}

	/**
	 * 获取性别链表
	 */
	public static List<HashMap<String, String>> getXbList() {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dm", "男");
		map.put("mc", "男");
		list.add(map);

		map = new HashMap<String, String>();

		map.put("dm", "女");
		map.put("mc", "女");
		list.add(map);

		return list;
	}
	
	/**
	 * 获取公寓性别链表
	 */
	public static List<HashMap<String, String>> getGyXbList() {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dm", "男");
		map.put("mc", "男");
		list.add(map);

		map = new HashMap<String, String>();

		map.put("dm", "女");
		map.put("mc", "女");
		list.add(map);

		map = new HashMap<String, String>();

		map.put("dm", "混合");
		map.put("mc", "混合");
		list.add(map);
		
		return list;
	}
	
	/**
	 * 社团名称
	 * @return
	 */
	public static List<HashMap<String, String>> getStmc(){
		DAO dao=DAO.getInstance();
		StringBuilder sb=new StringBuilder();
		sb.append("select a.stmc || a.stxz || a.bmdm dm, ");
		sb.append("a.stmc || '(' || case  when a.stxz = '学院' then ");
		sb.append("(select distinct b.xymc from view_njxyzybj b where a.bmdm = b.xydm) ");
		sb.append("when a.stxz = '专业' then ");
		sb.append("(select distinct b.zymc from view_njxyzybj b where a.bmdm = b.zydm) ");
		sb.append("when a.stxz = '班级' then ");
		sb.append("(select distinct b.bjmc from view_njxyzybj b where a.bmdm = b.bjdm) ");
		sb.append("else '学校' end || ')' mc from xsh_stglb a ");
		return dao.getList(sb.toString(), new String[]{}, new String[]{"dm","mc"});
	}
	
	/**
	 * 获取单位类别
	 */
	public static List<HashMap<String, String>> getDwlb() {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dm", "校内");
		map.put("mc", "校内");
		list.add(map);

		map = new HashMap<String, String>();

		map.put("dm", "校外");
		map.put("mc", "校外");
		list.add(map);

		return list;
	}
	
	/**
	 * 保险类型
	 */
	public static List<HashMap<String, String>> getBxlx() {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dm", "按学制");
		map.put("mc", "按学制");
		list.add(map);

		map = new HashMap<String, String>();

		map.put("dm", "自定义");
		map.put("mc", "自定义");
		list.add(map);

		return list;
	}
	
	/**
	 * 获取性别链表
	 */
	public static List<HashMap<String, String>> getXbForSsxxList() {

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dm", "男");
		map.put("mc", "男");
		list.add(map);

		map = new HashMap<String, String>();

		map.put("dm", "女");
		map.put("mc", "女");
		list.add(map);
		
		map = new HashMap<String, String>();
		
		map.put("dm", "混合");
		map.put("mc", "混合");
		list.add(map);

		return list;
	}
	
	/**
	 *  获取加减分性质
	 */
	
	public static List<HashMap<String, String>> getJjfList(){
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("dm", "减分");
		map.put("mc", "减分");
		list.add(map);

		map = new HashMap<String, String>();

		map.put("dm", "加分");
		map.put("mc", "加分");
		list.add(map);

		return list;
	}
}
