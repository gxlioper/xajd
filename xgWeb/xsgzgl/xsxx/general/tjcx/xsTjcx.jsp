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
				var njid = jQuery("#nj").val();
				var xyid = jQuery("#xydm").val();
				var zyid = jQuery("#zydm").val();

				jQuery("#btn_fh").bind("click",function(){
					document.location.href="general_xsxx_tjcx.do?method=tjcxByBj&nj="+njid+"&xydm="+xyid+"&zydm="+zyid;
				});
				
			});

			function cxXs(){
				var njid = jQuery("#nj").val();
				var xyid = jQuery("#xydm").val();
				var zyid = jQuery("#zydm").val();
				var bjid = jQuery("#bjdm").val();
				allNotEmpThenGo("general_xsxx_tjcx.do?method=tjxsByBjdm&bjdm="+bjid+"&nj="+njid+"&xydm="+xyid+"&zydm="+zyid);
				}

			function sjDc(){
				var url = "general_xsxx_tjcx.do?method=dcsjByType&type=xs";
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
		<html:hidden property="xydm" name="xsxxTjcxForm" styleId="xydm"/>
		<html:hidden property="zydm" name="xsxxTjcxForm" styleId="zydm"/>
		<html:hidden property="bjdm" name="xsxxTjcxForm" styleId="bjdm"/>
		<input type="hidden" id="search_go" onclick="cxXs()"></input>
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
		
		<div  style="width:100%;height:450px;overflow-x:hidden;overflow-y:auto;">
				<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; <logic:empty name="rsList">
							<font color="red">未找到任何记录！</font>
						</logic:empty> 
						<%
						ArrayList<String[]> rsList = (ArrayList<String[]>)request.getAttribute("rsList");
						String[] map = rsList.get(0);
						%>
						<logic:notEmpty name="rsList">
							年级:<font color="blue"><%=map[7]%></font>&nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="lable.xb" />:<font color="blue"><%=map[8]%></font>&nbsp;&nbsp;&nbsp;&nbsp;
							专业:<font color="blue"><%=map[9]%></font>&nbsp;&nbsp;&nbsp;&nbsp;班级:<font color="blue"><%=map[10]%></font>
						</logic:notEmpty> 
				</h3>
				<logic:notEmpty name="rsList">
					<table summary="" class="dateline" align="" width="100%">
					<thead>
							<tr align="center" style="cursor:hand">
								<td>学号</td>
								<td>姓名</td>
								<td>性别</td>
								<td>身份证号</td>
								<td>民族</td>
								<td>政治面貌</td>
								<td>联系电话</td>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rsList">
								<logic:iterate name="rsList" id="s" indexId="index">
								<tr>
									<logic:iterate id="v" name="s" offset="0" length="7">
									<td align="left">
									${v}
									</td>
								</logic:iterate>
								</tr>
								</logic:iterate>
							</logic:notEmpty>
						</tbody>
					</table>
					</logic:notEmpty>
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=xsxxTjcxForm"></jsp:include>
					<script type="text/javascript">
						$('choose').className="hide";
					</script>	
			</div>
		</html:form>
	</body>
</html>
