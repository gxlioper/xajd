package xgxt.rcsw.nthy;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import common.newp.ArrayUtil;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.date.DateUtils;

/**
 * 学费缓交管理SERVICE
 */
public class XfhjglService {

	XfhjglDao dao = new XfhjglDao();
	 
	/**
	 * 查询学费缓交开关信息
	 * @return
	 */
	public HashMap<String, String> queryXfhjkg() {
		return dao.queryXfhjkg(); 
	}
	
	/**
	 * 保存学费缓交开关信息
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfhjkg(XfhjglActionForm form) throws Exception{
		HashMap<String, String> map = queryXfhjkg();
		if ("0".equalsIgnoreCase(form.getKg())) {
			form.setHjjssj("");
			form.setHjkssj("");
		}
		if (map != null && map.size() > 0) {
			return dao.updateXfhjkg(form);
		} else {
			return dao.insertXfhjkg(form);
		}
	}
	
	/**
	 * 返回学生是否申请缓交信息
	 * @param xh
	 * @return
	 */
	public String checkXsxfsfhj(String xh) {
		XfhjglActionForm form = new XfhjglActionForm();
		form.setXh(xh);
		form.setXn(Base.currXn);
		HashMap<String, String> map = dao.queryXsxfsfqf(form);
		String data = "";
		if (map != null && map.size() > 0) {//存在欠费情况
			map = new HashMap<String, String>();
			map = dao.queryXsxfhjxx(form);
			if (map == null || map.get("xh") == null) {//没有进行缓交申请
				HashMap<String, String> rs = dao.queryXfhjkg();
				if ("1".equalsIgnoreCase(rs.get("kg"))) {//缓交申请开关已打开
					String kssj = rs.get("chkhjkssj");
					String jssj = rs.get("chkhjjssj");
					String currsj = DateUtils.getTime();
					if (!StringUtil.isNull(kssj)
							&& !StringUtil.isNull(jssj)
							&& (Float.parseFloat(kssj) <= Float
									.parseFloat(currsj))
							&& (Float.parseFloat(currsj) <= Float
									.parseFloat(jssj))) {//当前时间可以进行缓交
						data = "ksq";
					} else {
						data = "bksq";
					}
				} else if (rs == null || rs.size()<=0 || rs.get("kg") == null) {
					data = "ksq";
				} else {
					data = "bksq";
				}
			} else {
				if ("通过".equalsIgnoreCase(map.get("xxsh")) && "通过".equalsIgnoreCase(map.get("xysh")) && "通过".equalsIgnoreCase(map.get("fdysh"))) {
					data = "yhj";
				} else {
					data = "shz";
				}
			}
		} else {
			data = "wqf";
		}
		return data;
	}
	
	/**
	 * 检验学生是否可以进行学籍注册
	 * 
	 * @param xh  学号
	 * @param xn  学年 
	 * @return  返回TRUE则可以注册，返回FALSE不能进行注册 要么欠费，要么没有进行学费缓交。
	 */
	public boolean checkXssfkzc(String xh, String xn) {
		boolean flag = true;
		XfhjglActionForm form = new XfhjglActionForm();
		form.setXh(xh);
		form.setXn(xn);
		HashMap<String, String> map = dao.queryXsxfsfqf(form);
		if (map != null && map.get("xh")!= null) {//存在欠费情况
			map = new HashMap<String, String>();
			map = dao.queryXsxfhjxx(form);
			if (map != null && "通过".equalsIgnoreCase(map.get("xxsh"))
					&& "通过".equalsIgnoreCase(map.get("xysh"))
					&& "通过".equalsIgnoreCase(map.get("fdysh"))) {//已缓交清
				flag = true;
			} else {
				flag = false;
			}
		}
		return flag;
	}
	
	/**
	 * 查询学生学费缓交是否有申请
	 * @param form
	 * @return
	 */
	public HashMap<String, String> queryXfhjsqxx(XfhjglActionForm form) {
		return dao.queryXfhjsqxx(form);
	}
	
	/**
	 * 保存学费缓交申请信息
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean saveXfhjsqxx(XfhjglActionForm form) throws Exception{
		HashMap<String, String> rs = queryXfhjsqxx(form);
		if (rs != null && rs.size() >0) {
			return dao.updateXfhjsqxx(form);
		} else {
			return dao.insertXfhjsqxx(form);
		}
	}
	
	/**
	 * 查询学生学费缓交是否有申请
	 * @param form
	 * @return
	 */
	public String checkXfhjsqxx(String xh, String xn) {
		XfhjglActionForm form = new XfhjglActionForm();
		form.setXh(xh);
		form.setXn(xn);
		HashMap<String, String> rs = dao.queryXfhjsqxx(form);
		if (rs == null || rs.get("xh")==null) {
			return "ksq";
		}
		if (rs != null &&  ("通过".equalsIgnoreCase(rs.get("xxsh"))
				|| "通过".equalsIgnoreCase(rs.get("xysh"))
				|| "通过".equalsIgnoreCase(rs.get("fdysh")))) {
			return "shz";
		} else {
			return "";
		}
	}
	
	/**
	 * 查询学费缓交审核表头
	 * @return
	 */
	public List<HashMap<String, String>> queryXfhjshTitle(XfhjglActionForm form) {
		String[] en = {"pk", "dis","r", "xn", "xh", "xm", "xb", "bjmc", "shsj", "shjg"};
		String[] cn = {"pk", "dis","行号", "学年", "学号", "姓名", "性别", "班级", "审核时间", "学校审核"};
		
		if ("xy".equalsIgnoreCase(form.getUserType())) {
			en = new String[]{"pk", "dis","r", "xn", "xh", "xm", "xb", "bjmc", "shsj", "shjg", "sjsh"};
			cn = new String[]{"pk","dis", "行号", "学年", "学号", "姓名", "性别", "班级", "审核时间", "学院审核", "学校审核"};
		}
		if (form.isFdy()) {
			en = new String[]{"pk", "dis","r", "xn", "xh", "xm", "xb", "bjmc", "shsj", "shjg", "sjsh"};
			cn = new String[]{"pk", "dis","行号", "学年", "学号", "姓名", "性别", "班级", "审核时间", "辅导员审核", "学院审核"};
		}
		return ArrayUtil.arrayToList(en, cn);
	}
	
	/**
	 * 查询学费缓交审核结果
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXfhjshResult(XfhjglActionForm form) throws Exception{
		if ("xy".equalsIgnoreCase(form.getUserType())) {
			if (form.isFdy()) {
				return dao.queryXfhjfdyshResult(form);
			} else {
				return dao.queryXfhjxyshResult(form);
			}
		} else {
			return dao.queryXfhjxxshResult(form);
		}
	}
	
	/**
	 * 查询学费审核单条信息
	 * @param pk
	 * @return
	 */
	public HashMap<String, String> queryXfhjshxx(String pk) { 
		return dao.queryXfhjshxx(pk);
	}
	/**
	 * 修改审核信息
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean updateXfhjshxx(XfhjglActionForm form) throws Exception{ 
		return dao.updateXfhjshxx(form);
	}
	
	/**
	 * 批量审核学费缓交信息
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public boolean plshXfhjxx(XfhjglActionForm form) throws Exception{ 
		if (form.getPk() == null || form.getPk().length==0) return false;
		StringBuffer sql = new StringBuffer();
		for (int i=0;i<form.getPk().length;i++) {
			sql.append("update xg_rcsw_nthy_xfhjsqb set ");
			if ("xy".equalsIgnoreCase(form.getUserType())) {
				if (form.isFdy()) {
					sql.append(" fdysh='");
					sql.append(form.getShjg());
					sql.append("',fdyyj='");
					sql.append(form.getShyj());
					sql.append("',fdyshsj='");
					sql.append(form.getShsj());
					sql.append("',fdyzgh='");
					sql.append(form.getZgh());
					sql.append("' where xh||xn = '");
					sql.append(form.getPk()[i]);
					sql.append("'!@");
				} else {
					sql.append(" xysh='");
					sql.append(form.getShjg());
					sql.append("',xyyj='");
					sql.append(form.getShyj());
					sql.append("',xyshsj='");
					sql.append(form.getShsj());
					sql.append("',xyzgh='");
					sql.append(form.getZgh());
					sql.append("' where xh||xn = '");
					sql.append(form.getPk()[i]);
					sql.append("'!@");
				}
			} else {
				sql.append(" xxsh='");
				sql.append(form.getShjg());
				sql.append("',xxyj='");
				sql.append(form.getShyj());
				sql.append("',xxshsj='");
				sql.append(form.getShsj());
				sql.append("',xxzgh='");
				sql.append(form.getZgh());
				sql.append("' where xh||xn = '");
				sql.append(form.getPk()[i]);
				sql.append("'!@");
			}
		}
		DAO mydao = DAO.getInstance();
		return mydao.checkBatch(mydao.runBatch(sql.toString().split("!@")));
	}
	
	/**
	 * 查询学费缓交结果信息
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXfhjjg(XfhjglActionForm form) throws Exception { 
		return dao.queryXfhjjg(form);
	}
	
	/**
	 * 学费缓交查询表头
	 * @return
	 */
	public List<HashMap<String, String>> queryXfhjjgTitle() {
		String[] en = {"pk", "dis","r", "xn", "xh", "xm", "xb", "bjmc", "fdysh", "xysh", "xxsh"};
		String[] cn = {"pk", "dis","行号", "学年", "学号", "姓名", "性别", "班级", "辅导员审核", "学院审核", "学校审核"};
		return ArrayUtil.arrayToList(en, cn);
	} 
	
	/**
	 * 删除学费缓交申请信息
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	public boolean deleteXfhjsqxx(String[] pk) throws Exception{ 
		return dao.deleteXfhjsqxx(pk);
	}
}
