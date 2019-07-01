
package xgxt.pjpy.shcbys.jxj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.String.StringUtils;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
public class JxjService {

	JxjDAO dao = null;
	
	/**
	 * 考勤查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> kqyTitleResult() throws Exception {
		dao = new JxjDAO();
		String[] enList = new String[] { "pk", "rownum", "xn", "xq","xh", "xm", "xb",
				"nj", "bjmc", "kkcs", "zdcs", "ztcs" };
		String[] cnList = new String[]{"pk","行号","学年","学期","学号", "姓名",
				"性别", "年级", "班级名称", "旷课次数", "迟到次数", "早退次数"};
		return dao.getTitleResult(enList, cnList);
	}
	
	/**
	 * 考勤信息查询结果
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> kqxxResult(JxjModel model) throws Exception {
		dao = new JxjDAO();
		return dao.kqxxResult(model);
	}
	
	/**
	 * 考勤详细信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> kqxxDetails(String pkValue) throws Exception {
		dao = new JxjDAO();
		return dao.kqxxDetails(pkValue);
	}
	
	/**
	 * 考试修改
	 * @param pkValue
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean kqxxUpdate(String pkValue, JxjModel model,
			HttpServletRequest request) throws Exception {
		dao = new JxjDAO();
		return dao.kqxxUpdate(pkValue, model, request);
	}
	
	/**
	 * 考勤信息批量删除
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String kqxxDel(String[] keys, HttpServletRequest request) throws Exception {
		dao = new JxjDAO();
		return dao.kqxxDel(keys, request);
	}
	
	/**
	 * 学生详细信息
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuDetails(String xh) throws Exception {
		dao = new JxjDAO();
		return dao.getStuDetails(xh);
	}
	
	/**
	 * 学期名称
	 * @param xqdm
	 * @return
	 * @throws Exception
	 */
	public String getXqmc(String xqdm) throws Exception {
		dao = new JxjDAO();
		return dao.getXqmc(xqdm);
	}
	
	/**
	 * 学生成绩
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCjList(String xh) throws Exception {
		dao = new JxjDAO();
		String jwflag = dao.getJwFlag();
		jwflag = StringUtils.isNull(jwflag) ? "" : jwflag.trim();//1学分0过渡
		if ("1".equalsIgnoreCase(jwflag)) {
			return dao.getCjListByxf(xh);
		} else {
			return dao.getCjList(xh);
		}
	}
	
	/**
	 * 学生成绩
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCjList(HashMap<String, String> map) throws Exception {
		dao = new JxjDAO();
		String jwflag = dao.getJwFlag();
		jwflag = StringUtils.isNull(jwflag) ? "" : jwflag.trim();//1学分0过渡
		if ("1".equalsIgnoreCase(jwflag)) {
			return dao.getCjListByxf(map);
		} else {
			return dao.getCjList(map);
		}
	}
	
	/**
	 * 学生成绩浙江理工
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCjzjlgList(String xh) throws Exception {
		dao = new JxjDAO();
		String jwflag = dao.getJwFlag();
		jwflag = StringUtils.isNull(jwflag) ? "" : jwflag.trim();//1学分0过渡
		if ("1".equalsIgnoreCase(jwflag)) {
			return dao.getCjListByxf(xh);
		} else {
			return dao.getCjzjlgList(xh);
		}
	}
	
	public HashMap<String, String> getJd(String xh) throws Exception {
		dao = new JxjDAO();
		return dao.getJd(xh);
	}
	
	/**
	 * 检查学生是否符合该奖学金申请条件
	 * @param xh
	 * @param jd
	 * @param jxjdm
	 * @return 学生成绩,缓考成绩,考勤信息,绩点成绩
	 * @throws Exception
	 */
	public String chkJxjsqtj(String xh, String jd, String jxjdm) throws Exception {
		dao = new JxjDAO();
		
		/*boolean chkKqxx = dao.chkKqxx(xh);//考勤信息
		if (!chkKqxx) {
			return "kqbhg";//考勤不符合
		} else {
			//boolean chkCjhk = dao.chkCjhk(xh);//是否有缓考成绩
			//if (!chkCjhk) {
			//	return "cjhkbhg";//缓考不合格
			//} else {
				boolean chkJd = dao.chkJd(jd, jxjdm);//学期绩点
				if (chkJd) {
					return "";//符合该奖学金申请条件
				} else {
					return "jdbhg";//学期绩点不符合
				}
			}*/
		
		
		List<String[]> cjList = dao.getCjList(xh);//学生成绩
		if (cjList != null && cjList.size() > 0) {//成绩表中有成绩
			boolean chkStucj = dao.chkStucj(xh);//学年学期内学生是否有成绩不及格
			if (!chkStucj) {
				return "cjbhg";//不及格成绩
			} else {
				boolean chkKqxx = dao.chkKqxx(xh);//考勤信息
				if (!chkKqxx) {
					return "kqbhg";//考勤不符合
				} else {
					//boolean chkCjhk = dao.chkCjhk(xh);//是否有缓考成绩
					boolean chkCjhk = true;
					if (!chkCjhk) {
						return "cjhkbhg";//缓考不合格
					} else {
						boolean chkJd = dao.chkJd(jd, jxjdm);//学期绩点
						if (chkJd) {
							return "";//符合该奖学金申请条件
						} else {
							return "jdbhg";//学期绩点不符合
						}
					}
				}
			}
		} else {
			return "wcj";//无学生成绩
		}
	}
	
	/**
	 * 保存奖学金信息
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean jxjsqSave(JxjModel model, HttpServletRequest request) throws Exception {
		dao = new JxjDAO();
		return dao.jxjsqSave(model, request);
	}
	
	/**
	 * 奖学金审核查询表头
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> jxjshTableTitle(String userType, String isFdy)
			throws Exception {
		dao = new JxjDAO();
		String[] enList = null;
		String[] cnList = null;
		if (StringUtils.isEqual("xy", userType)) {// 学院用户
			enList = new String[] { "flag","bgcolor","pk", "rownum","xn", "xq", "xm", "xb",
					"bjmc", "jd","mc", "jxjdm", "xysh" };
			cnList = new String[] {"flag","bgcolor", "pk","行号", "学年", "学期", "姓名", "性别",
					"班级名称", "绩点","专业排名", "奖学金", "学院审核" };
		} else {// 学校用户
			enList = new String[] {  "bgcolor","pk","行号", "xn", "xq", "xm", "xb",
					"bjmc", "jd", "mc","jxjdm", "xysh" };
			cnList = new String[] { "bgcolor","pk","行号", "学年", "学期", "姓名", "性别",
					"班级名称", "绩点", "专业排名","奖学金", "学校审核" };
		}
		if ("true".equalsIgnoreCase(isFdy)) {
			enList = new String[] { "flag","bgcolor","pk", "rownum","xn", "xq", "xm", "xb",
					"bjmc", "jd","mc1", "jxjdm", "fdysh" };
			cnList = new String[] {"flag","bgcolor", "pk","行号", "学年", "学期", "姓名", "性别",
					"班级名称", "绩点","专业排名","奖学金", "辅导员审核" };
		}
		return dao.getTitleResult(enList, cnList);
	}
	
	/**
	 * 奖学金审核查询结果
	 * @param userType
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> jxjshTableResult(String userType, JxjModel model, String isFdy, String userName) throws Exception {
		dao = new JxjDAO();
		List<String[]> resList = new ArrayList<String[]>();
		if ("true".equalsIgnoreCase(isFdy)) {
			resList = dao.fdyjxjshQryRes(model, userName);
		} else {
			if (StringUtils.isEqual("xy", userType)) {// 学院用户
				resList = dao.xyjxjshQryRes(model);
			} else {// 学校用户
				resList = dao.xxjxjshQryRes(model);
			}
		}
		
		return resList;
	}
	
	/**
	 * 学院审核奖学金结果(兼得检查,人数检查)
	 * @param tg
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String xyjxjshRes(String userType, String tg, String[] keys, HttpServletRequest request, String isFdy,JxjModel model ) throws Exception {
		dao = new JxjDAO();
		if ("true".equalsIgnoreCase(isFdy)) {//辅导员审核
			return dao.fdyjxjshRes(tg, keys, request,model);
		} else {
			if (StringUtils.isEqual("xy", userType)) {
				return dao.xyjxjshRes(tg, keys, request);
			} else {
				return dao.xxjxjshRes(tg, keys, request);
			}
		}
	}
	
	/**
	 * 奖学金单个审核
	 * @param pkValue
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> jxjshone(String pkValue, String userType, String isFdy) throws Exception {
		dao = new JxjDAO();
		if ("true".equalsIgnoreCase(isFdy)) {
			return dao.fdyjxjshDetails(pkValue);
		} else {
			if (StringUtils.isEqual("xy", userType)) {
				return dao.xyjxjshDetails(pkValue);
			} else {
				return dao.xxjxjshDetails(pkValue);
			}
		}
	}
	
	/**
	 * 奖学金单个审核
	 * @param model
	 * @param pkValue
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public String jxjshone(JxjModel model, String pkValue, String userType, String isFdy) throws Exception {
		dao = new JxjDAO();
		if ("true".equalsIgnoreCase(isFdy)) {
			return dao.fdyjxjshoneRes(model, pkValue, userType);
		} else {
			return dao.jxjshoneRes(model, pkValue, userType);
		}
	}
	
	/**
	 * 学生成绩
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCjList1(String pkValue) throws Exception {
		dao = new JxjDAO();
		return dao.getCjList1(pkValue);
	}
	
	/**
	 * 学生成绩
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCjList2(String xh, String pkValue) throws Exception {
		dao = new JxjDAO();
		return dao.getCjList2(xh, pkValue);
	}
	
	/**
	 * 查询学生申请奖学金详细信息
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String,String>
	 * */
	public HashMap<String, String> getJxjsqInfo(String pk,String pkValue){
		dao = new JxjDAO();
		return dao.getJxjsqInfo(pk,pkValue);
	}
	
	/**
	 * 检测绩点是否达标
	 * @param xh
	 * @param jxjdm
	 * @param jd
	 * @return boolean
	 * */
	public boolean checkJxjJddb(String jxjdm, String jd){
		dao = new JxjDAO();
		boolean chkJd = dao.chkJd(jd, jxjdm);//学期绩点
		return chkJd;
	}
	
	/**
	 * 学生奖学金申请信息修改
	 * @param pkValue
	 * @param model
	 * @param request
	 * @return boolean 
	 * */
	public boolean jxjModiSave(String pkValue, PjpyShcbysJxjActionForm model,HttpServletRequest request) throws Exception{
		String drzw = DealString.toGBK(model.getDrzw());
		String jd = DealString.toGBK(model.getJd());
		String jxjdm = DealString.toGBK(model.getJxjdm());
		String sqly = DealString.toGBK(model.getSqly());
		
		return StandardOperation.update("xsjxjb", new String[]{"drzw","jd","jxjdm","sqly"}, new String[]{drzw,jd,jxjdm,sqly}, "xh||xn||jxjdm", pkValue, request);
	}
	
	public String stuname(String xh) throws Exception {
		dao = new JxjDAO();
		return dao.stuname(xh);
	}
	
	public HashMap sn(String pkValue) {
		dao = new JxjDAO();
		return dao.sn(pkValue);
	}
	
	public String getJxjlb(String jxjdm) {
		dao = new JxjDAO();
		return dao.getJxjlb(jxjdm);
	}
	
	public HashMap<String, String> getPjpyZq() {
		dao = new JxjDAO();
		return dao.getPjpyZq();
	}
}
