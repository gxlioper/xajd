package xgxt.studentInfo.zzdx.service;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.studentInfo.zzdx.dao.TransferStuInfoDAO;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ѧ����Ϣת��Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
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
