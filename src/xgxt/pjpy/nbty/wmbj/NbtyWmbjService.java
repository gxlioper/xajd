package xgxt.pjpy.nbty.wmbj;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.GetTime;

public class NbtyWmbjService {
	
	
	//判断是否是班干部
	public boolean isBgb(String xh){
		boolean blog=false;
		NbtyWmbjDao nbtyWmbjDao=new NbtyWmbjDao();
		if(nbtyWmbjDao.isBgb(xh).size()>0){
			blog=true;
		}
		return blog;
	}
	
	//判断对返回结果的读写权
	public void disabled(HttpServletRequest request,String userType,String userOnLine){
		
		if("student".equalsIgnoreCase(userOnLine)){
			request.setAttribute("clientColumns", "(case xysh when '通过' then 'disabled' else '' end)disabled,");
		}else if("xy".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns", "(case xxsh when '通过' then 'disabled' else '' end)disabled,");
		}else if("xx".equalsIgnoreCase(userType) 
				|| "admin".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns", "(case xxsh when '通过' then '' else '' end)disabled,");
	
		}
	}
	
	
	//	判断对返回结果的读写权
	public void disabled(HttpServletRequest request,String userType){
		
		 if("xy".equalsIgnoreCase(userType)){
				request.setAttribute("clientColumns", "(case xxsh when '通过' then 'disabled' else '' end)disabled,");
		 }else if("xx".equalsIgnoreCase(userType) 
					|| "admin".equalsIgnoreCase(userType)){
				request.setAttribute("annexTerm", " and xysh='通过' ");
				request.setAttribute("clientColumns", "(case xxsh when '通过' then '' else '' end)disabled,");
		 }
	}
	
	//非干部的学生
	public boolean notGbStudent(String userName,String userOnLine){
		NbtyWmbjService nbtyWmbjService=new NbtyWmbjService();
		if("student".equalsIgnoreCase(userOnLine) 
				&& !nbtyWmbjService.isBgb(userName)){
			return true;	
		}else{
			return false;
		}
	}
	
	//读写权的判断
	public void canWrite(HttpServletRequest request,HashMap<String,String>rs,
			String doType,String write,String userType,String userOnLine){
		//学院审核通过时，学生干部只能查看不能修改
		if(!"view".equalsIgnoreCase(doType)){
			if("student".equalsIgnoreCase(userOnLine)
				&&	"通过".equals(rs.get("xysh"))){     
				write="no";
				//学校审核通过时，学生干部只能查看不能修改
			}else if("xy".equalsIgnoreCase(userType)
				&&	"通过".equals(rs.get("xxsh"))){	
				write="no";
			}
		}
		request.setAttribute("write", write);
	}
	
	//	读写权的判断
	public void canWrite(HttpServletRequest request,HashMap<String,String>rs,
			String write,String userType,String userOnLine){
		//学院审核通过时，学生干部只能查看不能修改
		if("student".equalsIgnoreCase(userOnLine)
			&&	"通过".equals(rs.get("xysh"))){     
			write="no";
			//学校审核通过时，学生干部只能查看不能修改
		}else if("xy".equalsIgnoreCase(userType)
			&&	"通过".equals(rs.get("xxsh"))){	
			write="no";
		}
		
		request.setAttribute("write", write);
	}
	
	//返回要修改信息的Map
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
