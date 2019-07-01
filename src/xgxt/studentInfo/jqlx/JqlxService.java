package xgxt.studentInfo.jqlx;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.FormModleCommon;


public class JqlxService {
	
	JqlxDAO dao = new JqlxDAO();
	/**
	 * 获取学生信息
	 * @param xh 学号
	 * @return
	 */
	public HashMap<String, String> getStuInfo(String xh){
		return dao.getStuInfo(xh);
	}
	
	/**
	 * 获取学生假期留校申请信息
	 * @param pk 学年+学期+学号
	 * @return
	 */
	public HashMap<String, String> getJqsqInfo(String pk){
		return dao.getJqsqInfo(pk);
	}
	
	/**
	 * 获取学生假期申请条数
	 * @param pk 学年+学期+学号
	 * @return
	 */
	public String getCont(String pk){
		return dao.getCont(pk);
	}
	
	/**
	 * 获取学期名称
	 * @return
	 */
	public String getXqmcFromXqdm(){
		return dao.getXqmcFromXqdm();
	}
	
	/**
	 * 下拉列表
	 * @param request
	 * @param flg
	 */
	public void setList(HttpServletRequest request,String flg) {
		
		if ("sh".equalsIgnoreCase(flg)) {
			//补办原因
			List shztList = dao.getSelectList("shzt");
			request.setAttribute("shztList", shztList);
		} else if ("xq".equals(flg)) {
			List xq = dao.getXq();
			request.setAttribute("xq", xq);
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
	}
}
