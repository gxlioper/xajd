package xgxt.pjpy.scjz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.String.StringUtils;

public class PjpyScjzService {

	private PjpyScjzDAO dao = PjpyScjzDAO.getInstance();
	
	public static PjpyScjzService service = new PjpyScjzService();
	
	public static PjpyScjzService getInstance() {
		return service;
	}
	
	/**
	 * 获取学生基本信息加学习成绩信息班级排名，专业排名
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuDetailsInfo(String xh) throws Exception {
		return dao.getStuDetailsInfo(xh);
	}
	
	/**
	 * 获取学生在评奖学年的处分信息
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getStuWjcfInfo(String xh) throws Exception {
		return dao.getStuWjcfInfo(xh);
	}
	
	/**
	 * 检查学生在评奖学年是否有成绩不及格
	 * @param rs
	 * @return
	 */
	public String chkCjtj(HashMap<String, String> rs) {
		if (rs != null) {
			String zdcj = rs.get("zdcj");
			double cj = StringUtils.isNull(zdcj) ? 0 : Double.parseDouble(zdcj);
			if (cj<60) {
				return "该生在评奖学年有必修课成绩不及格，不符合申请条件！";
			}
		}
		return "";
	}
	
	/**
	 * 保存奖学金申请信息
	 * @param model
	 * @param reuqest
	 * @return
	 * @throws Exception
	 */
	public boolean saveJxjsqInfo(ScjzModel model, HttpServletRequest reuqest)
			throws Exception {
		return dao.saveJxjsqInfo(model, reuqest);
	}
	
	/**
	 * 修改奖学金信息
	 * @param model
	 * @param pkValue
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateJxjsqInfo(ScjzModel model, String pkValue, HttpServletRequest request) throws Exception {
		return dao.updateJxjsqInfo(model, pkValue, request);
	}
	
	/**
	 * 删除奖学金信息
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String deleteJxjSqInfo(String[] keys) throws Exception {
		return dao.deleteJxjSqInfo(keys);
	}
	
	public HashMap<String, String> viewJxjSqInfo(String pkValue) throws Exception {
		return dao.viewJxjSqInfo(pkValue);
	}
	
	/**
	 * 查询奖学金审核信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getJxjshInfo(String pkValue) throws Exception {
		return dao.getJxjshInfo(pkValue);
	}
	
	public String jxjsh(String userType, String isFdy, String cxcj, String yesNo, String shyj, String pkValue, String jxjdm, String jlje, String xydm) throws Exception {
		if ("xy".equalsIgnoreCase(userType)) {
			if ("true".equalsIgnoreCase(isFdy)) {
				return dao.fdysh(pkValue, cxcj, yesNo, shyj, jxjdm, jlje, xydm);
			} else {
				return dao.xysh(pkValue, cxcj, yesNo, shyj, jxjdm, jlje, xydm);
			}
		} else {
			dao.xxsh(pkValue, yesNo, shyj);
			return "";
		}
	}
	
	public String[] getJe(String je) throws Exception {
		if (!StringUtils.isNull(je) && (je.lastIndexOf("-") != -1)) {
			return je.split("-");
		} else {
			return new String[]{"", ""};
		}
	}
}
