<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<!-- ���ߣ�qph. ��ע�������˵�ҳ�������ע��ȥ���Ǻ��ˣ��� -->

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
		
		<!-- �๦�ܲ����� -->
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<li>
						<a id="add" onclick="sjDc();" class="btn_dc"> �� �� </a>
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
								   title="<%=map.get("xymc")==null?"δ֪":map.get("xymc") %>">
									<%=map.get("xymc")!=null?(map.get("xymc").length()>11?map.get("xymc").substring(0,11)+"...":map.get("xymc")):"δ֪" %>
								</a>
								<p>
									רҵ<%=null == zyzsMap.get(map.get("xydm")) ? 0 : zyzsMap.get(map.get("xydm"))%> ��&nbsp;&nbsp;
									�༶<%=null == bjzsMap.get(map.get("xydm")) ? 0 : bjzsMap.get(map.get("xydm"))%> �� 
								</p>
							</h2>
							<div class="con">
								<p class="num_all">
									ѧ����
									<em><%=map.get("allrs") %></em>��
								</p>
								<p>
									����
									<em><%=map.get("man") %></em>��&nbsp;&nbsp;&nbsp;ռ <%=map.get("manbl") %>&nbsp;&nbsp;&nbsp;
									Ů��
									<em><%=map.get("woman") %></em>��&nbsp;&nbsp;&nbsp;ռ <%=map.get("womanbl") %>
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
