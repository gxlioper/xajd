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
		<script type="text/javascript">
				
		
		function newpage_viewmore(obj)
		{
			var pkValue=  curr_row.getElementsByTagName("input")[1].value;
		     obj.href = "viewgrjlinfo.do?method=grjlinfo&jytype=jyweb&pkValue="+pkValue;
		}
	
		
		function delone(obj){
		  var pkValue =  curr_row.getElementsByTagName("input")[0].value;
		  alert(pkValue);
		  obj.href = "dwviewallgrjlinfo.do?method=dwviewallgrjlinfo&jytype=jyweb&doType=del&rid="+pkValue;
		}
		
		function refreshtheweb()
		{
			document.forms[0].action = "dwviewallgrjlinfo.do?method=dwviewallgrjlinfo&jytype=jyweb";
            document.forms[0].submit();
		}
		</script>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body>
		<jsp:include flush="true" page="head.jsp"></jsp:include>
		<html:form action="/dwviewallgrjlinfo" method="post">

			<div class="mainframe">
				<div class="jy_midframe">
					<h1>
						学生投递简历列表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;共
						<FONT color="red">&nbsp;<b><bean:write name="rsNum" />
						</b>&nbsp;</FONT>条相关记录
					</h1>
					<table width="97%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr height="25" align="center">
							<td>
								<font color="red">学号</font>
							</td>
							<td>
								<font color="red">公司名称</font>
							</td>
							<td>
								<font color="red">招聘职位</font>
							</td>
							<td>
								<font color="red">投递人</font>
							</td>
							<td>
								<font color="red">投递时间</font>
							</td>
							<td>
								<font color="red">操作</font>
							</td>

						</tr>
						<logic:iterate name="rs" id="s">
							<tr onmouseover="rowOnClick2(this)" height="25" align="center">
								<td>
								<input type="hidden" name="rid"
									value="<bean:write name='s' property="rid"/>" />
								<input type="hidden" name="xh"
									value="<bean:write name='s' property="xh"/>" />
									<bean:write name="s" property="xh" />
								</td>
								<td>
									<bean:write name="s" property="gsmc" />
								</td>
								<td>
									<bean:write name="s" property="zpzw" />
								</td>
								<td>
									<bean:write name="s" property="xm" />
								</td>
								<td>
									<bean:write name="s" property="tdsj" />
								</td>

								<td>
									<a onclick="newpage_viewmore(this)" href="" target="_blank"><font
										color="#0F03AF">查看详细</font> </a>&nbsp;|&nbsp;
									<a onclick="delone(this)" href=""><font color="#0F03AF">删除</font>
									</a>

								</td>
							</tr>
						</logic:iterate>
					</table>
					<TABLE width="99%" id="rsTable" class="tbstyle">
						<TR>
							<TD height=3></TD>
						</TR>
						<TR>
							<TD>
								<jsp:include flush="true"
									page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
							</TD>
						</TR>
						<TR>
							<TD height=3></TD>
						</TR>
					</TABLE>
					<h3>

					</h3>
				</div>
			</div>
			<button onclick="refreshtheweb()" id="search_go"
				style="display: none">
			</button>


			<jsp:include flush="true" page="foot.jsp"></jsp:include>
			<logic:notEmpty name="delete">
				<logic:equal name="delete" value="ok">
					<script>
                      alert("记录删除成功！");
                    </script>
				</logic:equal>
			</logic:notEmpty>
			<logic:notEmpty name="delete">
				<logic:equal name="delete" value="no">
					<script>
                      alert("记录删除失败！");
                    </script>
				</logic:equal>
			</logic:notEmpty>

		</html:form>
	</body>
</html>
