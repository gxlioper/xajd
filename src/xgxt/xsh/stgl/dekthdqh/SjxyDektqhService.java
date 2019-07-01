package xgxt.xsh.stgl.dekthdqh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class SjxyDektqhService {
	
	//�ж��Ƿ������Ÿ����ˣ��ǵĻ�����������Ϣ
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
	
	
	//�����󻮵���Ϣ
	public void setQhInfo(HttpServletRequest request,
			String userName,String userType,String userDep){
		
		SjxyDektqhService service=new SjxyDektqhService();
		if(!service.getXshInfo(request, userName)){
			if("xy".equalsIgnoreCase(userType)){
				//��ȡѧԺ�û���½ʱ����Ϣ
				request.setAttribute("jbbm", service.getXymc(userDep));
			}else{
				request.setAttribute("write", "no");
			}
		}
	}
	
	//���ý����ѯҳ��disabled���ԺͲ�ѯ����
	public void disabled(HttpServletRequest request,
			String userType,String userDep,String userName){
		
		SjxyDektqhService service=new SjxyDektqhService();
		
		if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns", "('')disabled,");
		}else{
			request.setAttribute("clientColumns", "(case xxsh when 'ͨ��' then  'disabled' else '' end)disabled,");
			//ѧԺ�û�
			if(service.getXshInfo(request,userName)){
				HashMap<String,String>map=(HashMap<String,String>)request.getAttribute("rs");
				request.setAttribute("annexTerm","and jbbm='"+map.get("jbbm")+"'");
				request.setAttribute("jbbm", map.get("jbbm"));
			}else if("xy".equalsIgnoreCase(userType)){
				request.setAttribute("annexTerm", "and jbbm='"+service.getXymc(userDep)+"'");
				request.setAttribute("jbbm", service.getXymc(userDep));
				//ѧ������
			}
			
		}
	}
	
//	���÷���ҳ��disabled���ԺͲ�ѯ����
	public void disabledFk(HttpServletRequest request,
			String userType,String userDep,String userName){
		
		SjxyDektqhService service=new SjxyDektqhService();
		
		if("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)){
			request.setAttribute("clientColumns", "('')disabled,");
			request.setAttribute("annexTerm", "and xxsh='ͨ��' and hdxg is not null and sjcxrs is not  null ");
		}else{
			
			//ѧԺ�û�
			if("xy".equalsIgnoreCase(userType)){
				request.setAttribute("annexTerm", "and jbbm='"+service.getXymc(userDep)+"' and xxsh='ͨ��' ");
				//ѧ������
			}else if(service.getXshInfo(request,userName)){
				HashMap<String,String>map=(HashMap<String,String>)request.getAttribute("rs");
				request.setAttribute("annexTerm","and jbbm='"+map.get("jbbm")+"' and xxsh='ͨ��' ");
			}
			
		}
	}
	
	

	
	//���õ������ʱ�����״̬
	public void setShZt(SjxyDektqhForm form,HttpServletRequest request){
		HashMap<String,String>hashMap=(HashMap<String,String>)request.getAttribute("rs");
		
		form.setSave_xxsh(hashMap.get("xxsh"));
	}
	
	//���ûЧ��
	public void setHdxg(SjxyDektqhForm form,HttpServletRequest request){
		HashMap<String,String>hashMap=(HashMap<String,String>)request.getAttribute("rs");
		
		form.setSave_hdxg(hashMap.get("hdxg"));
	}
	
	public void setHdxg(HttpServletRequest request){
		
		List<HashMap<String,String>> list=new ArrayList<HashMap<String,String>>();
		String[]str={"��","��","��","��"};
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
		 //��ȡֵ�ĳ��ȴ���0
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
				("��".equals(sfbx) || "��".equals(sfytp) || "��".equals(sfytxg))){
			request.setAttribute("write", "no");
		}
	}
	
	public void getWrite(HttpServletRequest request,String userType){
		HashMap<String,String>map=(HashMap<String,String>)request.getAttribute("rs");
		String write="save";
		//��ѧУ�û�
		if(!"xx".equalsIgnoreCase(userType) 
				&& ! "admin".equalsIgnoreCase(userType)){
			if("ͨ��".equalsIgnoreCase(map.get("xxsh"))){
				write="no";
			}
		}
		request.setAttribute("write",write);
	}
	
	
}
