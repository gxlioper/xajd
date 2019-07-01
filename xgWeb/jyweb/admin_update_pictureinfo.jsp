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
           document.forms[0].action = "updatepictureinfo.do?method=updatePictureInfo&jytype=jyweb&doType=update&pkValue="+pkValue;
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
		<html:form action="/updatepictureinfo" method="post" enctype="multipart/form-data">
		    <html:hidden name="rs" property="rowid"  />
			<table width="99%" align="center" class="tbborder">
				<tr>
					<td align=right>
						标题
					</td>
					<td>
						<DIV align="left">
							<html:text name="rs" property="bt" maxlength="25"
								style="width:60%" />
						</DIV>
					</td>
				</tr>
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
					<TD align=right>
						图片路径
					</TD>
					<td align="left">
						<div align="left">
							<html:text name="rs" property="picpath1" style="width:60%" styleId="viewpath"/>
							<input type="file" name="uploadFile" style="width:1px" onchange="changedata(this)" styleId="path">
						</div>

					</td>
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
			<script>
				function changedata(obj){
					document.getElementById('viewpath').value=obj.value;
				}
     
            </script>
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
