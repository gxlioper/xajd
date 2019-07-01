package xgxt.xsh.stgl.dekthdqh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class SjxyDektqhService {
	
	//判断是否是社团负责人，是的话返回社团信息
	public boolean getXshInfo(HttpServletRequest request,String userName){
		 SjxyDektqhDao dao=new  SjxyDektqhDao();
		 int length=dao.getXshInfo(userName).size();
		 if(length<1){
			 return false;
		 }else{
			 HashMap<String, String> map=dao.getXshInfo(userName).get(0);
			 request.setAttribute("rs", map);	
			 return true;
		 }
	}
	
	
	//设置企划的信息
	public void setQhInfo(HttpServletRequest request,
			String userName,String userType,String userDep){
		
		SjxyDektqhService service=new SjxyDektqhService();
		if(!service.getXshInfo(request, userName)){
			if("xy".equalsIgnoreCase(userType)){
				//获取学院用户登陆时的信息
				request.setAttribute("jbbm", service.getXymc(userDep));
			}else{
				request.setAttribute("write", "no");
			}
		}
	}
	
	//设置结果查询页面disabled属性和查询条件
	public void disabled(HttpServletRequest request,
			String userType,String userDep,String userName){
		
		SjxyDektqhService service=new SjxyDektqhService();
		
		if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns", "('')disabled,");
		}else{
			request.setAttribute("clientColumns", "(case xxsh when '通过' then  'disabled' else '' end)disabled,");
			//学院用户
			if(service.getXshInfo(request,userName)){
				HashMap<String,String>map=(HashMap<String,String>)request.getAttribute("rs");
				request.setAttribute("annexTerm","and jbbm='"+map.get("jbbm")+"'");
				request.setAttribute("jbbm", map.get("jbbm"));
			}else if("xy".equalsIgnoreCase(userType)){
				request.setAttribute("annexTerm", "and jbbm='"+service.getXymc(userDep)+"'");
				request.setAttribute("jbbm", service.getXymc(userDep));
				//学生社团
			}
			
		}
	}
	
//	设置反馈页面disabled属性和查询条件
	public void disabledFk(HttpServletRequest request,
			String userType,String userDep,String userName){
		
		SjxyDektqhService service=new SjxyDektqhService();
		
		if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns", "('')disabled,");
			request.setAttribute("annexTerm", "and xxsh='通过' and hdxg is not null and sjcxrs is not  null ");
		}else{
			
			//学院用户
			if("xy".equalsIgnoreCase(userType)){
				request.setAttribute("annexTerm", "and jbbm='"+service.getXymc(userDep)+"' and xxsh='通过' ");
				//学生社团
			}else if(service.getXshInfo(request,userName)){
				HashMap<String,String>map=(HashMap<String,String>)request.getAttribute("rs");
				request.setAttribute("annexTerm","and jbbm='"+map.get("jbbm")+"' and xxsh='通过' ");
			}
			
		}
	}
	
	

	
	//设置单个审核时的审核状态
	public void setShZt(SjxyDektqhForm form,HttpServletRequest request){
		HashMap<String,String>hashMap=(HashMap<String,String>)request.getAttribute("rs");
		
		form.setSave_xxsh(hashMap.get("xxsh"));
	}
	
	//设置活动效果
	public void setHdxg(SjxyDektqhForm form,HttpServletRequest request){
		HashMap<String,String>hashMap=(HashMap<String,String>)request.getAttribute("rs");
		
		form.setSave_hdxg(hashMap.get("hdxg"));
	}
	
	public void setHdxg(HttpServletRequest request){
		
		List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
		String[]str={"优","良","中","差"};
		for(int i=0;i<str.length;i++){
			HashMap<String,String>hashMap=new HashMap<String,String>();
			hashMap.put("hdxg", str[i]);
			list.add(hashMap);
		}
		request.setAttribute("hdxgList", list);
	}
	
	public void setTrueFalse(SjxyDektqhForm form,HttpServletRequest request){
		List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
		HashMap<String,String>hashMap=(HashMap<String,String>)request.getAttribute("rs");
		form.setSave_sfbx(hashMap.get("sfbx"));
		form.setSave_sfytp(hashMap.get("sfytp"));
		form.setSave_sfytxg(hashMap.get("sfytxg"));
	}
	
	public String getXymc(String  userDep){
		 SjxyDektqhDao dao=new  SjxyDektqhDao();
		 List<HashMap<String,String>>list=dao.getXymc(userDep);
		 String xymc="";
		 //获取值的长度大于0
		 if(list.size()>0){
			 HashMap<String,String>map=list.get(0);
			 xymc=map.get("xymc");
		 }
		 return xymc;
	}
	
	public void getFkWrite(HttpServletRequest request,String userType){
		HashMap<String,String> map=(HashMap<String,String>)request.getAttribute("rs");
		String sfbx=map.get("sfbx");
		String sfytp=map.get("sfytp");
		String sfytxg=map.get("sfytxg");
		if("xy".equalsIgnoreCase(userType) &&
				("是".equals(sfbx) || "是".equals(sfytp) || "是".equals(sfytxg))){
			request.setAttribute("write", "no");
		}
	}
	
	public void getWrite(HttpServletRequest request,String userType){
		HashMap<String,String>map=(HashMap<String,String>)request.getAttribute("rs");
		String write="save";
		//非学校用户
		if(!"xx".equalsIgnoreCase(userType) 
				&& ! "admin".equalsIgnoreCase(userType)){
			if("通过".equalsIgnoreCase(map.get("xxsh"))){
				write="no";
			}
		}
		request.setAttribute("write",write);
	}
	
	
}
