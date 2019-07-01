<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<!-- 作者：qph. （注：拷别人的页面把名字注释去掉是好人！） -->

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">

			function sjDc(){
				var url = "general_xsxx_tjcx.do?method=dcsjByType&type=njxy";
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";	
			}
			
		</script>
	</head>

	<body>
	<html:form action="/general_xsxx_tjcx" method="post">
		
		<!-- 多功能操作区 -->
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<li>
						<a id="add" onclick="sjDc();" class="btn_dc"> 导 出 </a>
					</li>
				</ul>
			</div>
		</div>
	
		<div class="class_infor after">
			<ul>
				<logic:notEmpty name="rsList">
					<%
						List<HashMap<String,String>> rsList = (List<HashMap<String,String>>)request.getAttribute("rsList");
						Map<String,String> zyzsMap = (Map<String,String>)request.getAttribute("zyzsMap");
						Map<String,String> bjzsMap = (Map<String,String>)request.getAttribute("bjzsMap");
						for (HashMap<String,String> map : rsList){
					%>
					
						<li>
							<h2>
								<a href="<%=map.get("xydm")!=null?"general_xsxx_tjcx.do?method=tjcxByXyZy&&xydm="+map.get("xydm"):"#" %>" 
								   title="<%=map.get("xymc")==null?"未知":map.get("xymc") %>">
									<%=map.get("xymc")!=null?(map.get("xymc").length()>11?map.get("xymc").substring(0,11)+"...":map.get("xymc")):"未知" %>
								</a>
								<p>
									专业<%=null == zyzsMap.get(map.get("xydm")) ? 0 : zyzsMap.get(map.get("xydm"))%> 个&nbsp;&nbsp;
									班级<%=null == bjzsMap.get(map.get("xydm")) ? 0 : bjzsMap.get(map.get("xydm"))%> 个 
								</p>
							</h2>
							<div class="con">
								<p class="num_all">
									学生数
									<em><%=map.get("allrs") %></em>人
								</p>
								<p>
									男生
									<em><%=map.get("man") %></em>人&nbsp;&nbsp;&nbsp;占 <%=map.get("manbl") %>&nbsp;&nbsp;&nbsp;
									女生
									<em><%=map.get("woman") %></em>人&nbsp;&nbsp;&nbsp;占 <%=map.get("womanbl") %>
								</p>
							</div>
						</li>
					
					<%
						}
					%>
					
				</logic:notEmpty>
			</ul>
		</div>
		</html:form>
	</body>
</html>
