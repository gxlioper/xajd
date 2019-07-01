<%@page 
	language="java"
	contentType="text/html; charset=GB2312"
	pageEncoding="GB2312"
	import="com.wiscom.is.*,xgxt.action.Base,java.net.*,xgxt.action.CommanAction,xgxt.utils.Fdypd"
%>

<%
String user = request.getParameter("username");

CommanAction test = new CommanAction();
    String [] returnStrings = test.userLoginForPage(user);
    if(!returnStrings[4].equalsIgnoreCase("")){
    request.setAttribute("errMsg",returnStrings[0]);
    session.setAttribute("pjzq", returnStrings[1]); //学校的评奖周期,xn
	session.setAttribute("userOnLine", returnStrings[2]); //用户类型（学生、老师）
	session.setAttribute("userName", returnStrings[3]); //登陆名
	session.setAttribute("userDep", returnStrings[4]); //用户部门
	session.setAttribute("userNameReal", returnStrings[5]); //用户姓名
	session.setAttribute("xxmc", returnStrings[6]);
	session.setAttribute("xxdm", returnStrings[7]);
	session.setAttribute("LoginXn", returnStrings[8]);
	session.setAttribute("LoginXq", returnStrings[9]);
	session.setAttribute("LoginZc", returnStrings[10]);
	session.setAttribute("openType",returnStrings[11]);
	session.setAttribute("bmmc", returnStrings[12]);
	session.setAttribute("userType", returnStrings[14]);
	session.setAttribute("istysfrz", "yes");
	if(returnStrings[13].equalsIgnoreCase("yes")){
		session.setAttribute("isFdy", true);
	}else{
		 session.setAttribute("isFdy", false);
    }
    if(returnStrings[15].equalsIgnoreCase("yes")){
		session.setAttribute("isBzr", true);
	}else{
		 session.setAttribute("isBzr", false);
    }    
    if(returnStrings[16].equalsIgnoreCase("yes")){
		session.setAttribute("fdyQx", true);
	}else{
		 session.setAttribute("fdyQx", false);
    }    
    if(returnStrings[17].equalsIgnoreCase("yes")){
		session.setAttribute("bzrQx", true);
	}else{
		 session.setAttribute("bzrQx", false);
    }
    //		 版本
    String edition = Base.getEdition();
		// 是否需要高级查询
    String superSearch = Base.getSuperSearch();

	session.setAttribute("edition", edition);
	session.setAttribute("superSearch", superSearch); 
	session.setAttribute("isFdy", Fdypd.isFdy(user));
    session.setAttribute("fdyQx", Fdypd.fdybjck(user));
    session.setAttribute("isFdyUser", Fdypd.isFdy(user));//是否是辅导员
	if(returnStrings[2].equalsIgnoreCase("student")){   
    	response.sendRedirect("/xgxt/stuPage.jsp");
    }else{
    	response.sendRedirect("/xgxt/teaPage.jsp");
    }
    }else{
    	request.setAttribute("errMsg",returnStrings[0]);
    	response.sendRedirect("/xgxt/loginWrongfornblg.jsp");
   }
 
%>