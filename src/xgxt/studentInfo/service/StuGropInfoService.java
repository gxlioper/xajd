package xgxt.studentInfo.service;

import java.util.List;

import xgxt.studentInfo.dao.StuInfoDAO;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ѧ��������ϢService</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-16</p>
 */
public class StuGropInfoService {
	/**
	 * ���ݰ༶���ƻ�ȡ�ð༶�µ�ѧ����Ϣ
	 * */
	public List getStuinfoByBjmc(String bjmc){
		StuInfoDAO dao = new StuInfoDAO();		
		return dao.selectStuinfoByBjmc(bjmc);
	}
	
	public String[] getTopTr(){
		StuInfoDAO dao = new StuInfoDAO();
		return dao.getTopTr();
	}
}
