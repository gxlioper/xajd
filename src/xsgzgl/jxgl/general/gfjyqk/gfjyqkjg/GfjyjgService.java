/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package xsgzgl.jxgl.general.gfjyqk.gfjyqkjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xsgzgl.jxgl.general.gfjyqk.gfjyqksq.GfjysqForm;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;

public class GfjyjgService extends SuperServiceImpl<GfjyjgForm, GfjyjgDao>{
	public static String _BCZSCID="-1";
	
	public GfjyjgService() {
		super.setDao(dao);
	}

	public boolean isExist(GfjyjgForm model) {
		boolean flag = false;
		if("save".equalsIgnoreCase(model.getType())) {
			 flag =  dao.isExist(model);
		}else{
			 flag = dao.isExistforUpdate(model);
		}
		return flag;
	}

	public boolean updateGfjyqk(GfjyjgForm model) throws Exception {
		//�޸Ĺ�����������ʱ�����������������ֶ�
		if ("1".equals(model.getGfqkfl())) {
			model.setCjrwsj("");model.setRwpzdw("");model.setTwfxsj("");model.setTwpzdw("");
			model.setPjpydw("");model.setPjpysj("");model.setJcdw("");model.setJcsj("");
			model.setCjhddd("");model.setCjhdsj("");
		}else if ("2".equals(model.getGfqkfl())) {
			model.setBydjsj("");model.setBydjdd("");model.setTwfxsj("");model.setTwpzdw("");
			model.setPjpydw("");model.setPjpysj("");model.setJcdw("");model.setJcsj("");
			model.setCjhddd("");model.setCjhdsj("");
		}else if ("3".equals(model.getGfqkfl())) {
			model.setBydjsj("");model.setBydjdd("");model.setCjrwsj("");model.setRwpzdw("");
			model.setPjpydw("");model.setPjpysj("");model.setJcdw("");model.setJcsj("");
			model.setCjhddd("");model.setCjhdsj("");
		}else if ("4".equals(model.getGfqkfl())) {
			model.setBydjsj("");model.setBydjdd("");model.setCjrwsj("");model.setRwpzdw("");
			model.setTwfxsj("");model.setTwpzdw("");model.setJcdw("");model.setJcsj("");
			model.setCjhddd("");model.setCjhdsj("");
		}else if ("5".equals(model.getGfqkfl())) {
			model.setBydjsj("");model.setBydjdd("");model.setCjrwsj("");model.setRwpzdw("");
			model.setTwfxsj("");model.setTwpzdw("");model.setPjpydw("");model.setPjpysj("");
			model.setCjhddd("");model.setCjhdsj("");
		}else if ("6".equals(model.getGfqkfl())) {
			model.setBydjsj("");model.setBydjdd("");model.setCjrwsj("");model.setRwpzdw("");
			model.setTwfxsj("");model.setTwpzdw("");model.setPjpydw("");model.setPjpysj("");
			model.setJcdw("");model.setJcsj("");
		}
			return  super.runUpdate(model);
		}

	public String[] deleteGfjyqkjg(String[] ids) throws Exception {
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
				HashMap<String, String> hm=dao.getGfjyqkjg(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?gfjyqkDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//ȥ�������ය��
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}

	private int gfjyqkDelete(String[] array) throws Exception {
		return runDelete(array);
	}

	public HashMap<String, String> getGfjyqkInfo(GfjyjgForm model) {
		return dao.getGfjyqkInfo(model);
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
