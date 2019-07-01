<%@ page language="java" import="xgxt.jygl.comman.JyWEBDAO,java.util.*" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/jyweb.ini"%>
	<div class="head">
      <div class="logo">
        <h2 class="floatleft"><img src="<%=stylePath%>logo/logo_school.png" /></h2>
        <h3 class="floatleft"><img src="<%=stylePath %>logo/logo_jyw.png" /></h3>
      </div>
    </div>
    <div class="menu" id="menu">
    	<div class="nav">
	      	<ul>
		      	<%
		      		JyWEBDAO dao = new JyWEBDAO();
		      		String userType = (String)session.getAttribute("jyweb_userType");
		      		
		      		List<HashMap<String,String>> menus = dao.getJywebMenus(userType);
		      		
		      		for (int i = 0 ; i<menus.size() ; i++){
		      			out.print("<li id='");
		      			out.print(menus.get(i).get("mkly"));
		      			out.print("'><a href=\"");
		      			out.print(menus.get(i).get("mkly"));
		      			out.print("\">");
		      			out.print(menus.get(i).get("mc"));
		      			out.print("</a></li>");
		      		}
		      		
		      	%>
	      	</ul>
      	</div>
    </div>

    
	<script type="text/javascript" defer>
		var path = document.location.href.split('xgxt/')[1];
		if ($(path)){
			$(path).className = "current";
		} else {
			$('index.do').className = "current";
		}
		
	</script>    
    