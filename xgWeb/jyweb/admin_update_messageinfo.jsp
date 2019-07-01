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
        <base target="_self">
		<link id="csss" rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link href="jyweb/style/module.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/base.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/forms.css" rel="stylesheet" type="text/css">

		<script language="javascript" src="js/function.js"></script>
        <script type="text/javascript">
        function updatemessage(){
           var pkValue = document.getElementById("rowid").value;
           document.getElementById('content1').value = frames('eWebEditor1').getHTML();
           document.forms[0].action = "updatemessageinfo.do?method=updateMessageInfo&jytype=jyweb&doType=update&pkValue="+pkValue;
	       document.forms[0].submit();
        
        }
        
        
        
        </script>

	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body onunload="window.dialogArguments.document.getElementById('search_go').click();">
		<html:form action="/updatemessageinfo" method="post">
		    <html:hidden name="rs" property="rowid"  />
			<table width="99%" align="center" class="tbborder">
				<tr>
					<td width="30" align="center">
						标题
					</td>
					<td>
						<DIV align="left">
							<html:text name="rs" property="wjbt" maxlength="25"
								style="width:60%" />
						</DIV>
					</td>
				</tr>
				<TR>
					<TD align=right>
						栏目
					</TD>
					<TD>
						<div align="left">
							<html:select name="rs" property="wjlx" >
								<html:option value="">
								</html:option>
								<html:option value="公告栏">
										公告栏
								</html:option>
								<html:option value="校园专场招聘会">
										校园专场招聘会
								</html:option>
								<html:option value="生源介绍">
										生源介绍
								</html:option>
								<html:option value="国家级文件">
										国家级文件
								</html:option>
								<html:option value="市级文件">
										市级文件
								</html:option>
								<html:option value="校园专场招聘会">
										校级文件
								</html:option>
							</html:select>
						</div>
					</TD>
				</TR>
				<TR>
					<TD align=right>
						内容
					</TD>
					<TD align=center>
						<html:hidden name="rs" property="content1" />
						<IFRAME ID="eWebEditor1" src="BatEditor" frameborder="0"
							scrolling="no" width="100%" height="350"></IFRAME>
					</TD>
				</TR>
				<TR>
					<TD colspan="2" align=center>
						<button onclick="updatemessage();return false;">
							提交
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="reset">
							重置
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<button  onclick="Close();return false;">
							关闭
						</button>
					</TD>
				</TR>
			</table>
			<logic:notEmpty name="update">
			<logic:equal name="update" value="ok">
				<script>
                      alert("记录修改成功！");
                    </script>
			</logic:equal>
			<logic:equal name="update" value="no">
				<script>
                      alert("记录修改失败！");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		</html:form>
	</body>
</html>
