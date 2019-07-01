<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title><%=session.getAttribute("xxmc")%>就业网</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta content="all" name="robots" />
		<meta name="author" content="杭州正方电子工程有限公司 hzzfsoft@126.com" />
		<meta name="Copyright" content="Copyright" />
		<meta name="description" content="description" />
		<meta name="keywords" content="description" />

		<link id="csss" rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link href="jyweb/style/module.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/base.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/forms.css" rel="stylesheet" type="text/css">

		<script language="javascript" src="js/function.js"></script>


	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body>
		<html:form action="/qzxxgl" method="post">
			<jsp:include flush="true" page="head.jsp"></jsp:include>
			<input type="hidden" name="webtype" value="qzgl" />
			<div class="mainframe">
				<div class="ny_midframe">
					<div class="leftframe">
						<jsp:include flush="true" page="contrl.jsp"></jsp:include>
						<div class="rdxw">
							<h1></h1>
						</div>
						<div class="yqlj">
							<h1></h1>
							<span></span>
						</div>
					</div>
					<div class="ny_rightframe">
						<div class="ny_con">
							<h1>
								<h3>
									当前位置：<a href="index.do">首页</a>&gt;求职信息管理 共
									<font color="red"><b>&nbsp;<bean:write name="rsNum" />&nbsp;</b>
									</font>条相关记录
									<font color="red">提示：单击表头可以排序</font>
								</h3>
							</h1>
							<table width="99%" align="center" class="tbborder">
								<thead>
									<tr align="center" style="cursor:hand" height="30"
										bgcolor="E6F4FF">
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)">
												<FONT color="red"><bean:write name="tit"
														property="cn" /> </FONT>
											</td>
										</logic:iterate>
										<td>
											<FONT color="red"> 操作 </FONT>
										</td>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr height="25" onmouseover="rowOnClick2(this)">
										<input type="hidden" value="<bean:write name="s" property="xh" />"/>
	                                     <td>
	                                         <bean:write name="s" property="xm" />
	                                     </td>
                                         <td>
	                                         <bean:write name="s" property="xb" />
	                                     </td>
	                                     <td>
	                                         <bean:write name="s" property="zymc" />
	                                     </td>
	                                     <td>
	                                         <bean:write name="s" property="qzyx" />
	                                     </td>
	                                     <td>
	                                         <bean:write name="s" property="fbsj" />
	                                     </td>
										<td><a target="_blank" href="qzxxinfo.do?method=qzxxinfo&doType=view&jytype=jyweb&pkValue=<bean:write name="s" property="xh" />">查看详细</a>&nbsp;|&nbsp;
											<a  href="" onclick="delqzxx(this)">删除</a>
										</td>
									</tr>
								</logic:iterate>
							</table>
							<TABLE width="99%" align="center" class="tbborder">
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
									</TD>
								</TR>
							</TABLE>
							<br>
						</div>
						<h2></h2>
					</div>
				</div>
			</div>
			<jsp:include flush="true" page="foot.jsp"></jsp:include>
			<button onclick="refreshtheweb()" id="search_go"
				style="display: none" />
		</html:form>
		<logic:notEmpty name="delete">
			<logic:equal name="delete" value="ok">
				<script>
                      alert("记录删除成功！");
                    </script>
			</logic:equal>
			<logic:equal name="delete" value="no">
				<script>
                      alert("记录删除失败！");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
	<script type="text/javascript">	
		function delqzxx(obj){
		   var pkValue = curr_row.getElementsByTagName("input")[0].value;

           obj.href = "qzxxgl.do?method=qzxxgl&jytype=jyweb&doType=del&pkValue="+pkValue;
           
		}
		</script>
</html>
