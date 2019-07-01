package xgxt.jxgl.gzdx;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.jxgl.JxglTyForm;
import xgxt.jxgl.JxglTyService;
import xgxt.utils.CommonQueryDAO;

public class JxglGzdxService extends JxglTyService {

	JxglGzdxDAO dao = new JxglGzdxDAO();
	
	/**
	 * 获得免缓训学生申请基本信息
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getMhxSqInfo(JxglTyForm model) {

		HashMap<String, String> map = new HashMap<String, String>();

		// 学号
		String xh = model.getXh();
		// 学年
		String xn = model.getXn();
		//主键
		String pkValue = xn + xh;
		// 学生基本信息
		map = getStuInfo(xh);

		map.put("xn", xn);
		map.put("save_xn", xn);
		map.put("save_xh", xh);

		HashMap<String, String> ldInfo = dao.getStuLdInfo(model);
		// 连队名称
		String ldmc = ldInfo.get("ldmc");
		// 指导老师姓名
		String jsxm = ldInfo.get("jsxm");
		map.put("ldmc", ldmc);
		map.put("jsxm", jsxm);

		return map;
	}
	
	/**
	 * 获得籍贯信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String getJgInfo(String jg) throws Exception {
		return dao.getJgInfo(jg);
	}
}
