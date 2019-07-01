package xgxt.studentInfo.zzdx.service;

import xgxt.action.Base;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 分班Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-08</p>
 */
public class DistributeClassService {
	/**
	 * 将新加入的班级信息加入到Base中的map中，使不用启动服务器即可查询到新的班级
	 * */
	public void addBjInfoToBase(){
		Base.initialBj init = new Base.initialBj();
		new Thread(init).start();
	}
}
