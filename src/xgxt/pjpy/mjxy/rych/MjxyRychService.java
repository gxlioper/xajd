package xgxt.pjpy.mjxy.rych;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.GetTime;

public class MjxyRychService {
	public void insertRychxx(String xh,String brjl,String drzw,String zhpfmc){
		MjxyRychDao mjxyRychDao=new MjxyRychDao();
		mjxyRychDao.insertRychxx(xh, brjl,drzw,zhpfmc);
	}
	
	/**
	 * ����ֶ�����
	 */
	public HashMap<String,String> setShzd(String userType,String shjg){
		HashMap<String,String>valueMap=new HashMap<String,String>();
		//���ʱ��
		String shsj="";
		String shzd="";
		if("xy".equalsIgnoreCase(userType)){
			shsj="xyshsj";
			shzd="xysh";
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			shsj="xxshsj";
			shzd="xxsh";
		}
		valueMap.put(shzd,shjg);
		valueMap.put(shsj, GetTime.getNowTime2());
		return valueMap;
	}
	
	/**
	 * ����CHECK BOX��disabled����
	 */
	public void setDisabled(HttpServletRequest request,String userType){
		if("xy".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns", "(case when xxsh='ͨ��' then 'disabled' else '' end)disabled,");
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns", "('')disabled,");
			request.setAttribute("annexTerm", " and xysh='ͨ��' ");
		}
	}
	
	/**
	 * ����CHECK BOX��disabled���� ��ѯҳ��
	 */
	public void setDisabledCx(HttpServletRequest request,String userType){
		if("xy".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns", "(case when xxsh='ͨ��' then 'disabled' else '' end)disabled,");
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns", "('')disabled,");
		}
	}
	
	/**
	 * ��дȨ
	 * 
	 */
	public void writeRead(MjxyRychForm form,HttpServletRequest request,
			HashMap<String,String>hashMap,String userType){
		String write="yes";
		if("xy".equalsIgnoreCase(userType)){
			form.setSave_xysh(hashMap.get("xysh"));
			if("ͨ��".equalsIgnoreCase(hashMap.get("xxsh"))){
				write="no";
			}
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			form.setSave_xxsh(hashMap.get("xxsh"));
			write="yes";
		}else{
			write="no";
		}
		request.setAttribute("write", write);
	}
}
