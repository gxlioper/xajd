/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:06:43 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjlx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:06:43
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class QjlxService extends SuperServiceImpl<QjlxForm, QjlxDao> {
	QjlxDao dao = new QjlxDao();

	/**
	 * 不存在不可删除数据
	 */
	public static String _BCZSCID="-1";
	public QjlxService() {
		this.setDao(dao);
	}

	/**
	 * @throws Exception
	 * 
	 * @描述:保存请假类型
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-9 下午06:46:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qf
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean save(QjlxForm qf) throws Exception {
		qf.setQjlxid(dao.getNextId());
		return this.runInsert(qf);
	}
	/**
	 * 
	 * @描述:是否可以进行添加
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-9 下午07:19:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qf
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isCanAdd(QjlxForm qf) {
		return dao.checkQjlx(qf);
	}
	/**
	 * @throws Exception 
	 * 
	 * @描述:删除
	 * @作者：张昌路[工号：982]
	 * @日期：2013-9-13 下午02:31:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * String[] 返回类型   String数组 0为成功删除条数为不能删除的
	 * @throws
	 */
	public String[] delete(String[] ids) throws Exception{
		//StringBuffer del=new StringBuffer();
		List<String> delId=new ArrayList<String>();
		StringBuffer noDel=new StringBuffer();
		boolean isHaveNoId=false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.isCanDel(str)){
				delId.add(str);//记录删除id
				//del.append(str);
				//del.append(",");
			}else{
				noDel.append(str);
				noDel.append(",");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?runDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//去除最后多余逗号
		str=isHaveNoId?str.substring(0,str.length()-1):_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
}
