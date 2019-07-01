<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<!-- 作者：qph. （注：拷别人的页面把名字注释去掉是好人！） -->

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
			jQuery(function(){

				jQuery("#btn_fh").bind("click",function(){
					document.location.href="general_xsxx_tjcx.do?method=tjcxByNj";
				});
				
				var nj = jQuery("#nj").val();
				var njVal = nj != "" ? nj.split("!!@!!") : [];

				for (var i = 0 ; i < njVal.length ; i++){
					jQuery("a[name=selectNj][value="+njVal[i]+"]").addClass("current");
				}

				jQuery("a[name=selectNj]").bind("click",function(){

					var njArr = [];
					
					if (jQuery(this).attr("class") == "current"){
						jQuery(this).removeClass("current");
					} else {
						jQuery(this).addClass("current");
					}
					
					var selectNj = jQuery("a[name=selectNj][class=current]");

					for (var i = 0 ; i < selectNj.length ; i++){
						njArr.push(selectNj.eq(i).attr("value"));
					}
					document.location.href="general_xsxx_tjcx.do?method=tjcxByNjXy&nj="+njArr.join("!!@!!");
				});

			});

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
		<html:hidden property="nj" name="xsxxTjcxForm" styleId="nj"/>
		
		
		<!-- 多功能操作区 -->
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<li>
						<a id="add" onclick="sjDc();" class="btn_dc"> 导 出 </a>
					</li>
					<li>
						<a id="btn_fh" class="btn_fh"> 返 回 </a>
					</li>
				</ul>
			</div>
		</div>
		<div class="filter_condition after filter_float">
			<h3>
				年级：
			</h3>
			<ul>
				<logic:notEmpty name="njList">
					<logic:iterate id="n" name="njList">
						<li>
							<a href="javascript:void(0);" name="selectNj" value="${n.nj }">${n.nj }</a><span>|</span>
						</li>
					</logic:iterate>
				</logic:notEmpty>
			</ul>
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
								<a href="general_xsxx_tjcx.do?method=tjcxByZy&nj=${xsxxTjcxForm.nj }&xydm=<%=map.get("xydm") %>" title="<%=map.get("xymc") %>">
									<%=map.get("xymc").length()>11?map.get("xymc").substring(0,11)+"...":map.get("xymc") %></a>
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
