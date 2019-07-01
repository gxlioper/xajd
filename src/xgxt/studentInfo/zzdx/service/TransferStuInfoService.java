package xgxt.studentInfo.zzdx.service;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.studentInfo.zzdx.dao.TransferStuInfoDAO;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 学生信息转换Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-08</p>
 */
public class TransferStuInfoService {
	public boolean xsjbxxlsbToXsxxb(HttpServletRequest request){
		String nd = Base.currNd;
		TransferStuInfoDAO dao = new TransferStuInfoDAO();
		boolean flag = true;
		flag = dao.xsjbxxlsbToXsxxb(nd, request);
		return flag;
	}
}
