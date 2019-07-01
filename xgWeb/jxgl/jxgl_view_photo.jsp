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

		<meta name="Copyright" content="������� zfsoft" />
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
        alert("��ͼƬ���������лл������");
        event.cancelBubble = true;
        event.returnValue = false;
        return false;
    }
}

document.oncontextmenu = nocontextmenu;//������IE5+
document.onmousedown = norightclick;//���ж�����

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
					��ǰ����λ�ã���ѵ���� - ��ѵ��� - ��ѵ��Ƭ����
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
								ͼƬ���ͣ�
								<bean:write name="rs" property="remark" />
								&nbsp;&nbsp;&nbsp;�����ˣ�
								<bean:write name="rs" property="fbr" />
								&nbsp;&nbsp;&nbsp;����ʱ�䣺
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
										onclick="BatAlert.showTips('����ɾ������ȴ�...');delphoto();">
										ɾ��
									</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="button2"
										onclick="showTopWin('jxglupdatephoto.do?pkValue=<bean:write name="rs" property="rid" />',500,300)">
										�޸�
									</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="button2"
										onclick="Close();window.dialogArguments.document.getElementById('search_go').click();">
										�ر�
									</button>
								</logic:equal>
							</td>
						</tr>
						<tr>
							<td>
								<br>
								ͼƬ˵����
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
							����
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
