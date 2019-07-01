package xgxt.pjpy.nbzy.jxj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.pjpy.nbzy.zhszcp.ZhszcpModel;
import xgxt.utils.String.StringUtils;

public class JxjService {

	JxjDAO dao = null;
	
	public HashMap<String, String> viewJxjInfo(String xh) throws Exception {
		dao = new JxjDAO();
		return dao.viewJxjInfo(xh);
	}
	
	public String sqSave(ZhszcpModel model, HttpServletRequest request) throws Exception {
		dao = new JxjDAO();
		ArrayList<String[]> tjList = dao.tjList(Base.getJxjsqxn(), model.getJxjdm());
		HashMap<String, String> rs = dao.getJxjsqTj(model.getXh());
		if (rs == null) {
			return "not";
		}
		String zypm = StringUtils.isNull(rs.get("zypm")) ? "0" : rs.get("zypm").trim();
		String zjpm = StringUtils.isNull(rs.get("zjpm")) ? "0" : rs.get("zjpm").trim();
		String zhpm = StringUtils.isNull(rs.get("zhpm")) ? "0" : rs.get("zhpm").trim();
		String zkpm = StringUtils.isNull(rs.get("zkpm")) ? "0" : rs.get("zkpm").trim();
		String kcxpm = StringUtils.isNull(rs.get("kcxpm")) ? "0" : rs.get("kcxpm").trim();
		for (int i=0;i<tjList.size();i++) {
			String[] tmp = tjList.get(i);
			if (tmp.length==2 && "zysyf".equalsIgnoreCase(tmp[0])) {
				String zyBl = StringUtils.isNull(tmp[1]) ? "0" : tmp[1].trim();
				if (Integer.parseInt(zypm) > Integer.parseInt(zyBl)) {
					return "zypm";
				}
			}
			if (tmp.length==2 && "zyjnsyf".equalsIgnoreCase(tmp[0])) {
				String zjBl = StringUtils.isNull(tmp[1]) ? "0" : tmp[1].trim();
				if (Integer.parseInt(zjpm) > Integer.parseInt(zjBl)) {
					return "zjpm";
				}
			}
			if (tmp.length==2 && "kcxfzsyf".equalsIgnoreCase(tmp[0])) {
				String kcxbl = StringUtils.isNull(tmp[1]) ? "0" : tmp[1].trim();
				if (Integer.parseInt(kcxpm) > Integer.parseInt(kcxbl)) {
					return "kcxpm";
				}
			}
			if (tmp.length==2 && "zhcpzf".equalsIgnoreCase(tmp[0])) {
				String zhbl = StringUtils.isNull(tmp[1]) ? "0" : tmp[1].trim();
				if (Integer.parseInt(zhpm) > Integer.parseInt(zhbl)) {
					return "zhpm";
				}
			}
			if (tmp.length==2 && "zk".equalsIgnoreCase(tmp[0])) {
				String zkbl = StringUtils.isNull(tmp[1]) ? "0" : tmp[1].trim();
				if (Integer.parseInt(zkpm) > Integer.parseInt(zkbl)) {
					return "zkpm";
				}
			} 
		}
		return dao.sqSave(model, request);
	}
	
	public List<HashMap<String, String>> getJxjTitle() throws Exception {
		dao = new JxjDAO();
		String[] enList = new String[] { "pk", "rownum", "xh","xm", "xn", "nj", "bjmc",
				"jxjmc", "xysh", "xxsh" };
		String[] cnList = new String[] { "pk", "行号", "学号","姓名", "学年", "年级","班级名称",
				"奖学金名称", "学院审核", "学校审核"};
		return dao.getQryTitle(enList, cnList);
	}
	
	public List<String[]> getJxjResult(ZhszcpModel model) throws Exception {
		dao = new JxjDAO();
		return dao.getJxjResult(model);
	}
	
	public HashMap<String, String> viewJxjxx(String pkValue) throws Exception {
		dao = new JxjDAO();
		return dao.viewJxjxx(pkValue);
	}
	
	public boolean jxjmodiSave(ZhszcpModel model, String pkValue,
			HttpServletRequest request) throws Exception {
		dao = new JxjDAO();
		return dao.jxjmodiSave(model, pkValue, request);
	}
	
	public String jxjDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new JxjDAO();
		return dao.jxjDel(keys, request);
	}
	
	public List<HashMap<String, String>> xxJxjshTitle() throws Exception {
		dao = new JxjDAO();
		String[] enList = new String[] { "pk", "rownum", "xh","xm", "xn", "nj", "bjmc",
				"jxjmc", "zysyf", "zypm", "zhcpzf", "zhpm", "xxsh" };
		String[] cnList = new String[] { "pk", "行号", "学号","姓名", "学年", "年级","班级名称",
				"奖学金名称","职业素养分", "名次", "综合分", "名次", "学校审核"};
		return dao.getQryTitle(enList, cnList);
	}
	
	public List<HashMap<String, String>> xyJxjTitle() throws Exception {
		dao = new JxjDAO();
		String[] enList = new String[] { "pk", "rownum", "xh","xm", "xn", "nj", "bjmc",
				"jxjmc", "zysyf", "zypm", "zhcpzf", "zhpm", "xysh"};
		String[] cnList = new String[] { "pk", "行号", "学号","姓名", "学年", "年级","班级名称",
				"奖学金名称","职业素养分", "名次", "综合分", "名次", "学院审核"};
		return dao.getQryTitle(enList, cnList);
	}
	
	public List<HashMap<String, String>> shTitle(String userType) throws Exception {
		if ("xy".equalsIgnoreCase(userType)) {
			return xyJxjTitle();
		} else {
			return xxJxjshTitle();
		}
	}
	
	public List<String[]> shResult(String userType, ZhszcpModel model) throws Exception {
		dao = new JxjDAO();
		if ("xy".equalsIgnoreCase(userType)) {
			return dao.xyJxjshResult(model);
		} else {
			return dao.xxJxjshResult(model);
		}
	}
	
	public String result(String userType, String[] keys,
			HttpServletRequest request, String jg) throws Exception {
		dao = new JxjDAO();
		if ("xy".equalsIgnoreCase(userType)) {
			return dao.xyjxjshResult(keys, jg, request);
		} else {
			return dao.xxjxjshResult(keys, jg, request);
		}
	} 
	
	public HashMap<String, String> jxjShone(String pkValue, String userType) throws Exception {
		dao = new JxjDAO();
		if ("xy".equalsIgnoreCase(userType)) {
			return dao.xyjxjShone(pkValue);
		} else {
			return dao.xxjxjShone(pkValue);
		}
	}
	
	public boolean jxjoneshSave(String userType, String pkValue, String yesNo, String yj,String xyshyj, HttpServletRequest request) throws Exception {
		dao = new JxjDAO();
		if ("xy".equalsIgnoreCase(userType)) {
			return dao.xyJxjshonesave(pkValue, yesNo, yj, request);
		} else {
			return dao.xxJxjshonesave(pkValue, yesNo, yj, xyshyj, request);
		}
	}
	
	public List<HashMap<String, String>> tjTitle() throws Exception {
		dao = new JxjDAO();
		return dao.tjTitle();
	}
	
	public List<String[]> tjResult(String xn, String jxjdm) throws Exception {
		dao = new JxjDAO();
		return dao.tjResult(xn, jxjdm);
	}
	
	public boolean tjSave(String xn, String jxjdm, String tj, String bl) throws Exception {
		dao = new JxjDAO();
		return dao.tjSave(xn, jxjdm, tj, bl);
	}
	
	public boolean tjDel(String[] keys) throws Exception {
		dao = new JxjDAO();
		return dao.tjDel(keys);
	}
	
	public String getBugInfo(String res) {
		res = StringUtils.isNull(res) ? "" : res;
		if ("zypm".equalsIgnoreCase(res)) {
			return "操作失败,该生职业素养分排名不符合申请条件!";
		} else if ("zjpm".equalsIgnoreCase(res)) {
			return "操作失败,该生职业技能素养分排名不符合申请条件!";
		} else if ("zhpm".equalsIgnoreCase(res)) {
			return "操作失败,该生综合测评总分排名不符合申请条件!";
		} else if ("zkpm".equalsIgnoreCase(res)) {
			return "操作失败,该生职业技能与可持续发展分之和排名不符合申请条件!";
		} else if ("kcxpm".equalsIgnoreCase(res)) {
			return "操作失败,该生可持续发展分排名不符合申请条件!";
		} else if ("not".equalsIgnoreCase(res)) {
			return "操作失败,该生无任何素养分,请确认综合测评表中有无相关成绩!";
		} else {
			return "操作失败!";
		}
	}
	
	public HashMap<String, String> jxjPrint(String pkValue) throws Exception {
		dao = new JxjDAO();
		return dao.jxjPrint(pkValue);
	}
}
