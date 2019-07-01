package xgxt.zxdk.xnmz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommSetting;
import xgxt.form.RequestForm;
import xgxt.gygl.qsgl.GyglQsglForm;

public class ZxdkInit {
	
	/**
	 * 助学贷款查询信息初始
	 * @param request
	 * @author 裘力俊
	 * 
	 */
	public void initZxdkCx(RequestForm rForm, ZxdkForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "zxdk";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "zxdk_xnmz.do?method=zxdkcxManage";
		// 输出字段
		String[] colList = new String[] {"dis","pkValue","xh","xm","xb","nj","xymc","zymc","bjmc","shzt" };
		// 表头
		List<HashMap<String, String>> topTr = getTopTr(colList, "cx");
		// 是否查询操作(默认查询)
		String search ="true";

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum = String.valueOf(topTr.size());
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * 助学贷款审核信息初始
	 * @param request
	 * @author 裘力俊
	 * 
	 */
	public void initZxdkSh(RequestForm rForm, ZxdkForm model,
			HttpServletRequest request) {

		// 功能模块
		String gnmk = "zxdk";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "zxdk_xnmz.do?method=zxdkshManage";
		// 输出字段
		String[] colList = new String[] {"disabled","pkValue", "xh","xm","xb","nj","xymc","zymc","bjmc","shzt" };
		// 表头
		List<HashMap<String, String>> topTr = getTopTr(colList, "sh");
		// 是否查询操作(默认查询)
		String search = !"go".equalsIgnoreCase(request.getParameter("go")) ? "false"
				: "true";

		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum = String.valueOf(topTr.size());
		commSetting.setShowNum(showNum);

		// ===============通用设置 end ============

		rForm.setDoType(doType);
		rForm.setGnmk(gnmk);
		rForm.setPath(path);
		rForm.setColList(colList);
		rForm.setTopTr(topTr);
		rForm.setSearch(search);
		rForm.setCommSetting(commSetting);

	}
	
	/**
	 * 根据模块类型获取表头
	 * @param lx
	 * @return  List<HashMap<String,String>>
	 */
	public List<HashMap<String,String>>getTopTr(String[]colList,String lx){
		
		DAO dao=DAO.getInstance();
		String[] cn = null;
		
		if("cx".equalsIgnoreCase(lx)){
			
			cn=new String[]{"学号","姓名","性别","年级",Base.YXPZXY_KEY,"专业","班级","审核状态"};
		}else if("sh".equalsIgnoreCase(lx)){
			cn=new String[]{"学号","姓名","性别","年级",Base.YXPZXY_KEY,"专业","班级","审核状态"};
		}
		
		return dao.arrayToList(colList, cn);
		
	}

}
