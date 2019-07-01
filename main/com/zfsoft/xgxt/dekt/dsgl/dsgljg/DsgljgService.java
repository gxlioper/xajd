/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.dekt.dsgl.dsgljg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class DsgljgService extends SuperServiceImpl<DsgljgForm, DsgljgDao>{
	public static String _BCZSCID="-1";
	public boolean isExist(DsgljgForm model) {
		boolean flag = false;
		if("save".equalsIgnoreCase(model.getType())) {
			 flag =  dao.isExist(model);
		}else{
			 flag = dao.isExistforUpdate(model);
		}
		return flag;
	}

	public boolean updateDsxx(DsgljgForm model) throws Exception {
		return  super.runUpdate(model);
	}

	public String[] deleteDsxxjg(String[] ids) throws Exception {
		List<String> delId=new ArrayList<String>();//��ɾ����id����
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.isCanDel(str)){
				delId.add(str);//��¼ɾ��id
			}else{
				HashMap<String, String> hm=dao.getDsxxjg(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?dsxxDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//ȥ�������ය��
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}

	private int dsxxDelete(String[] ids) throws Exception {
		return runDelete(ids);
	}

	public HashMap<String, String> getDsxxInfo(DsgljgForm model) {
		return dao.getDsxxInfo(model);
	}

	public List<String[]> getYdxqInfo(String xh) {
		String[] inputValue = new String[]{xh};
		String[] outputValue = new String[]{"xn","xqmc","ssm","sqsj"};
		return dao.getYdxqInfo(inputValue, outputValue);
	}

}
