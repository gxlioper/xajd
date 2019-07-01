package xgxt.pjpy.nbty.wmbj;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.GetTime;

public class NbtyWmbjService {
	
	
	//�ж��Ƿ��ǰ�ɲ�
	public boolean isBgb(String xh){
		boolean blog=false;
		NbtyWmbjDao nbtyWmbjDao=new NbtyWmbjDao();
		if(nbtyWmbjDao.isBgb(xh).size()>0){
			blog=true;
		}
		return blog;
	}
	
	//�ж϶Է��ؽ���Ķ�дȨ
	public void disabled(HttpServletRequest request,String userType,String userOnLine){
		
		if("student".equalsIgnoreCase(userOnLine)){
			request.setAttribute("clientColumns", "(case xysh when 'ͨ��' then 'disabled' else '' end)disabled,");
		}else if("xy".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns", "(case xxsh when 'ͨ��' then 'disabled' else '' end)disabled,");
		}else if("xx".equalsIgnoreCase(userType) 
				|| "admin".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns", "(case xxsh when 'ͨ��' then '' else '' end)disabled,");
	
		}
	}
	
	
	//	�ж϶Է��ؽ���Ķ�дȨ
	public void disabled(HttpServletRequest request,String userType){
		
		 if("xy".equalsIgnoreCase(userType)){
				request.setAttribute("clientColumns", "(case xxsh when 'ͨ��' then 'disabled' else '' end)disabled,");
		 }else if("xx".equalsIgnoreCase(userType) 
					|| "admin".equalsIgnoreCase(userType)){
				request.setAttribute("annexTerm", " and xysh='ͨ��' ");
				request.setAttribute("clientColumns", "(case xxsh when 'ͨ��' then '' else '' end)disabled,");
		 }
	}
	
	//�Ǹɲ���ѧ��
	public boolean notGbStudent(String userName,String userOnLine){
		NbtyWmbjService nbtyWmbjService=new NbtyWmbjService();
		if("student".equalsIgnoreCase(userOnLine) 
				&& !nbtyWmbjService.isBgb(userName)){
			return true;	
		}else{
			return false;
		}
	}
	
	//��дȨ���ж�
	public void canWrite(HttpServletRequest request,HashMap<String,String>rs,
			String doType,String write,String userType,String userOnLine){
		//ѧԺ���ͨ��ʱ��ѧ���ɲ�ֻ�ܲ鿴�����޸�
		if(!"view".equalsIgnoreCase(doType)){
			if("student".equalsIgnoreCase(userOnLine)
				&&	"ͨ��".equals(rs.get("xysh"))){     
				write="no";
				//ѧУ���ͨ��ʱ��ѧ���ɲ�ֻ�ܲ鿴�����޸�
			}else if("xy".equalsIgnoreCase(userType)
				&&	"ͨ��".equals(rs.get("xxsh"))){	
				write="no";
			}
		}
		request.setAttribute("write", write);
	}
	
	//	��дȨ���ж�
	public void canWrite(HttpServletRequest request,HashMap<String,String>rs,
			String write,String userType,String userOnLine){
		//ѧԺ���ͨ��ʱ��ѧ���ɲ�ֻ�ܲ鿴�����޸�
		if("student".equalsIgnoreCase(userOnLine)
			&&	"ͨ��".equals(rs.get("xysh"))){     
			write="no";
			//ѧУ���ͨ��ʱ��ѧ���ɲ�ֻ�ܲ鿴�����޸�
		}else if("xy".equalsIgnoreCase(userType)
			&&	"ͨ��".equals(rs.get("xxsh"))){	
			write="no";
		}
		
		request.setAttribute("write", write);
	}
	
	//����Ҫ�޸���Ϣ��Map
	public Map<String,String> getValueMap(String shjg,String userType){
		Map<String,String>valueMap=new HashMap<String,String>();
		String shzd="";
		String shsj="";
		if("xy".equalsIgnoreCase(userType)){
			shzd="xysh";
			shsj="xyshsj";
		}else if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			shzd="xxsh";
			shsj="xxshsj";
		}
		valueMap.put(shzd,shjg );
		valueMap.put(shsj, GetTime.getNowTime2());
		return valueMap;
	}
	
	public void setSh(HttpServletRequest request,NbtyWmbjForm nbtyWmbjForm,String userType){
		HashMap<String,String> rs=(HashMap)request.getAttribute("rs");
		if("xy".equalsIgnoreCase(userType)){
			nbtyWmbjForm.setSave_xysh(rs.get("xysh"));	
		}else if("xx".equalsIgnoreCase(userType) 
				|| "admin".equalsIgnoreCase(userType)){
			nbtyWmbjForm.setSave_xxsh(rs.get("xxsh"));	
		}
	}
}	
