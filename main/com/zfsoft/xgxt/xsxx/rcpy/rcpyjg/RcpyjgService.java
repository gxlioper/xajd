/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.xsxx.rcpy.rcpyjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class RcpyjgService extends SuperServiceImpl<RcpyjgForm, RcpyjgDao>{
	public static String _BCZSCID="-1";
	public boolean isExist(RcpyjgForm model) {
		boolean flag = false;
		if("save".equalsIgnoreCase(model.getType())) {
			 flag =  dao.isExist(model);
		}else{
			 flag = dao.isExistforUpdate(model);
		}
		return flag;
	}

	public String[] delRcpyjg(String[] ids) throws Exception {
		List<String> delId=new ArrayList<String>();//可删除的id集合
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.isCanDel(str)){
				delId.add(str);//记录删除id
			}else{
				HashMap<String, String> hm=dao.getRcpyXhXm(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?rcpyjgDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}

	private int rcpyjgDelete(String[] array) throws Exception {
		return runDelete(array);
	}

	public HashMap<String, String> getRcpyjgInfo(RcpyjgForm model) {
		return dao.getRcpyjgInfo(model);
	}

}
