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
<html>
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script language="JavaScript">

if(window.Event)
    document.captureEvents(Event.MOUSEUP);

function nocontextmenu()
{
    event.cancelBubble = true;
    event.returnValue = false;
    return false;
}

function norightclick(e)
{
    if(window.Event)
    {
        if(e.which == 2 || e.which == 3)
        return false;
    }
    else
    if(event.button == 2 || event.button == 3)
    {
        alert("本图片仅供浏览，谢谢合作！");
        event.cancelBubble = true;
        event.returnValue = false;
        return false;
    }
}

document.oncontextmenu = nocontextmenu;//适用于IE5+
document.onmousedown = norightclick;//所有都适用

   function delphoto(){
      var pkValue= $("rid").value;
      document.forms[0].action = "jxgldelphoto.do?doType=del&pkValue="+pkValue;
	  document.forms[0].submit();
   
   }
   function refreshtheweb()
		{
		    var pkValue= $("rid").value;
			document.forms[0].action = "jxglphotoinfo.do?doType=view&pkValue="+pkValue;
            document.forms[0].submit();
		}
</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body>
		<html:form action="/jxgldelphoto" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="rid"
				value="<bean:write name="rs" property="rid" />" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：军训管理 - 军训风采 - 军训照片管理
				</div>
			</div>

			<div align="center">
				<br>
				<logic:notEmpty name="rs">
					<html:hidden name="rs" property="rid" />
					<table>
						<tr>
							<td align="center">
								<bean:write name="rs" property="bt" />
								<hr style="width:85%">
							</td>
						</tr>
						<tr>
							<td align="center">
								图片类型：
								<bean:write name="rs" property="remark" />
								&nbsp;&nbsp;&nbsp;发布人：
								<bean:write name="rs" property="fbr" />
								&nbsp;&nbsp;&nbsp;发布时间：
								<bean:write name="rs" property="fbsj" />

							</td>
						</tr>
						<tr>
							<td>
								<img src="<bean:write name="rs" property="path" />"
									style="width:520px;height:380px" />
							</td>
						</tr>
						<tr align="center">
							<td>
							  <logic:equal name="who" value="xx">
								<button type="button" class="button2"
										onclick="BatAlert.showTips('正在删除，请等待...');delphoto();">
										删除
									</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="button2"
										onclick="showTopWin('jxglupdatephoto.do?pkValue=<bean:write name="rs" property="rid" />',500,300)">
										修改
									</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="button2"
										onclick="Close();window.dialogArguments.document.getElementById('search_go').click();">
										关闭
									</button>
								</logic:equal>
							</td>
						</tr>
						<tr>
							<td>
								<br>
								图片说明：
								<br>
								<bean:write name="rs" property="nr" />
							</td>
						</tr>
					</table>
				</logic:notEmpty>
				<logic:empty name="rs">
					<div align="center">
						<img src="jyweb/images/caozuo_ok.gif" border="0" align="absmiddle">
						<br>
						<button type="button" class="button2"
							onclick="Close();window.dialogArguments.document.getElementById('search_go').click();">
							返回
						</button>
					</div>
				</logic:empty>
				<br>
				<br>
				<br>
			</div>
			<button type="button" onclick="refreshtheweb()" id="search_go"
				style="display: none" >
			</button>
		</html:form>
	</body>
</html>
