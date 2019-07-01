
package xgxt.pjpy.ynys;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class YxbysService extends PjpyYnysService {

	YxbysDAO dao = null;
	
	/**
	 * 学生辅导信息
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> stuDetailsInfo(String xh) throws Exception {
		dao = new YxbysDAO();
		return dao.stuDetailsInfo(xh);
	}
	
	/**
	 * 优秀毕业生申请保存
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveYxbysxx(YxbysModel model, HttpServletRequest request) throws Exception {
		dao = new YxbysDAO();
		dao.saveYxbysfzxx(model, request);
		return dao.saveYxbysxx(model, request);
	}
	
	/**
	 * 优秀毕业生查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getYxbysQryTitle() throws Exception {
		dao = new YxbysDAO();
		String[] enList = new String[] { "pk", "rownum", "xn", "nj","xh", "xm", "xymc",
				"zymc", "bjmc", "grry", "yxsh", "xxsh" };
		String[] cnList = new String[] { "pk", "行号", "学年", "年级", "学号", "姓名", "学院名称",
				"专业名称", "班级名称", "个人荣誉", "院系审核", "学校审核" };
		return dao.getQryTitle(enList, cnList);
	}
	
	/**
	 * 优秀毕业生查询结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> yxbysQryRes(YxbysModel model) throws Exception {
		dao = new YxbysDAO();
		return dao.yxbysQryRes(model);
	}
	
	/**
	 * 显示优秀毕业生信息
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> viewYxbysxx(String pkValue) {
		dao = new YxbysDAO();
		return dao.viewYxbysxx(pkValue);
	}
	
	/**
	 * 修改保存结果
	 * @param pkValue
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateYxbysxx(String pkValue, YxbysModel model, HttpServletRequest request) throws Exception {
		dao = new YxbysDAO();
		dao.saveYxbysfzxx(model, request);
		return dao.modiYxbysxx(pkValue, model, request);
	}
	
	/**
	 * 批量删除
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String delYxbysxx(String[] keys, HttpServletRequest request) throws Exception {
		dao = new YxbysDAO();
		return dao.delYxbysxx(keys, request);
	}
	
	/**
	 * 优秀毕业生报表打印
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> yxbysPrint(String pkValue) throws Exception {
		dao = new YxbysDAO();
		return dao.yxbysPrint(pkValue);
	}
}
