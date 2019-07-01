package xgxt.xszz.guizdx;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.jxgl.JxglTyForm;
import xgxt.jxgl.JxglTyService;
import xgxt.utils.CommonQueryDAO;
import xgxt.xszz.XszzTyForm;
import xgxt.xszz.XszzService;

public class XszzGuizdxService extends XszzService {

	XszzGuizdxDAO dao = new XszzGuizdxDAO();
	
	/**
	 * 获得个性化表头
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getTopTr(String lx) {
		
		DAO dao = DAO.getInstance();
		
		String[] colListCN = null;
		String[] colListEN = null;

		//副食补助_分配
		if ("fsbz_fp".equalsIgnoreCase(lx)) {
			
			colListCN = new String[] {"年度", Base.YXPZXY_KEY+"名称", "专业名称" };
			colListEN = new String[] { "nd", "xymc", "zymc" };
		}
		// 副食补助_发放
		else if ("fsbz_ff".equalsIgnoreCase(lx)) {

			colListCN = new String[] { "是否登记", "学号", "姓名", "年级", Base.YXPZXY_KEY+"名称",
					"专业名称", "班级名称", "年度", "月份", "专业所分配补助", };
			colListEN = new String[] { "sfdj", "xh", "xm", "nj", "xymc",
					"zymc", "bjmc", "nd", "yf", "bzmc" };
		}
		// 副食补助_未发放
		else if ("fsbz_wff".equalsIgnoreCase(lx)) {
			colListCN = new String[] {  "学号", "姓名","性别", "年级", Base.YXPZXY_KEY+"名称",
					"专业名称", "班级名称", "未发放年度", "未发放月份", "专业所分配补助", };
			colListEN = new String[] {"xh", "xm", "xb", "nj", "xymc",
					"zymc", "bjmc", "nd","yf", "bzmc" };
		}
		return dao.arrayToList(colListEN, colListCN);
	}
	/**
	 * 获得专业列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 */
	public ArrayList<String[]> getZyList(XszzTyForm model, String[] colList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getZyList(model, colList);
	}
	
	/**
	 * 获得副食补助项目列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 */
	public List<HashMap<String, String>> getFsbzXmList(XszzTyForm model) {
		return dao.getFsbzXmList(model);
	}

	/**
	 * 保存副食补助专业项目
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveFsbzZyxm(XszzTyForm model, String tableName)
			throws Exception {
		
		// 年度
		String nd = model.getNd();
		// 专业
		String[] arrZydm = model.getBzzy();
		// 专业项目
		String[] zyxmList = model.getZyxmList();

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		
		if (arrZydm != null && arrZydm.length > 0 && zyxmList != null
				&& zyxmList.length > 0) {
			for (int i = 0; i < arrZydm.length; i++) {
				
				String[] arrZyxm = zyxmList[i].split("!!@@!!");
				String zydm = arrZydm[i];
				
				//勾选了补助类型
				if (arrZyxm != null && arrZyxm.length > 0) {
					for (int j = 0; j < arrZyxm.length; j++) {
						String bzlx = arrZyxm[j];
						HashMap<String, String> map = new HashMap<String, String>();
						map.put("nd", nd);
						map.put("zydm", zydm);
						map.put("bzlx", bzlx);
						
						list.add(map);
					}
				}
				//未勾选补助类型
				else{
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("nd", nd);
					map.put("zydm", zydm);
					map.put("bzlx", "");
					
					list.add(map);
				}
			}
		}
		
		return dao.saveFsbzZyxm(list, tableName);
	}
	
	/**
	 * 获得副食补助学生发放列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 */
	public ArrayList<String[]> getFsbzXsffList(XszzTyForm model, String[] colList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getFsbzXsffList(model, colList);
	}
	
	/**
	 * 获得当前月份
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 */
	public String getDqyf() {

		DAO dao = DAO.getInstance();

		// 当前时间
		String nowTime = dao.getNowTime("YYYYMMDD");
		// 月份
		String yf = "";

		if (!Base.isNull(nowTime) && nowTime.length() >= 8) {
			yf = nowTime.substring(4, 6);
		}
		return yf;
	}
	
	/**
	 * 删除副食补助发放信息
	 * 
	 * @author luojw
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * 
	 * @throws Exception
	 */
	public boolean delFsbzFf(XszzTyForm model,String tableName) throws Exception {

		// 年度
		String nd = model.getNd();
		// 月份
		String yf = model.getYf();
		// 补助类型
		String bzlx = model.getBzlx();
		// 页面显示全部学号
		String[] all_xh = model.getAll_xh();
		// 主键
		String pk = "xh||nd||yf||bzlx";
		// /主键值
		String[] pkValue = null;
		// 构建主键
		if (all_xh != null && all_xh.length > 0) {
			pkValue = new String[all_xh.length];
			for (int i = 0; i < all_xh.length; i++) {
				pkValue[i] = all_xh[i] + nd + yf + bzlx;
			}
		}
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		
		boolean flag = delXszz(saveForm, model);

		return flag;
	}
	
	/**
	 * 获得副食补助未发放列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 */
	public ArrayList<String[]> getFsbzWffList(XszzTyForm model,String[] colList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getFsbzWffList(model, colList);
	}
}
