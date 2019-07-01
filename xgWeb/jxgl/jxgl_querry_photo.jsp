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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
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
	<script language="javascript">
</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script type="text/javascript">
		function updatethepic(){
		     var bt = document.getElementById("bt").value;
		     var nr = document.getElementById("nr").value;
		
		if(bt==""||nr==""){
		    alert("�뽫��*�ŵĲ�����д������");
		    return false;
		}
			
		    document.forms[0].action = "updatethephoto.do?doType=save";
	        document.forms[0].submit();
		}
		function refreshtheweb()
		{
		    var remark = $("remark").value;
			document.forms[0].action = "jxglphotoquery.do?remark="+remark;
            document.forms[0].submit();
		}
		function addphoto()
		{
		     showTopWin("updatethephoto.do", 800, 280);

		}
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
		</script>

		<html:form action="/put_Info.do?pkValue=" method="post"
			enctype="multipart/form-data">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���ѵ���� - ��ѵ��� - ��ѵ��Ƭ����
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<table>
					<tr>
						<logic:iterate id="v" name="rs" indexId="index">
							<%
						int i=index+1;
						%>
							<td>
								&nbsp;&nbsp;&nbsp;
								<a href="#"
									onclick="showTopWin('jxglphotoinfo.do?pkValue=<bean:write name="v" property="rid" />&doType=view',700,600)">
									<img
										title="���⣺<bean:write name="v" property="bt" />   ͼƬ˵����<bean:write name="v" property="nr" />"
										src="<bean:write name="v" property="path" />"
										style="width:180px;height:135px" /> </a>
							</td>
							<%%>


							<%
						if (i % 4 == 0) {
						%>
						
					</tr>
					<tr>
						<%
					}
					%>
						</logic:iterate>
					</tr>
				</table>
			</logic:notEmpty>
			<TABLE width="99%" id="rsTable" class="tbstyle">
				<TR>
					<td>
						<div align="center">
							<button type="button" class="button2" onclick="addphoto();">
								����
							</button>
						</div>
					</td>
					<td>
						<div align="center">
							��ѡ����ࣺ
							<html:select name="rs1" property="remark"
								onchange="refreshtheweb()">
								<html:option value=""></html:option>
								<html:option value="���커����"></html:option>
								<html:option value="��ѵ���"></html:option>
							</html:select>
						</div>
					</td>
					<TD>
						<jsp:include flush="true"
							page="/jxgl/jxgl_turnpage.jsp?form=jxglForm"></jsp:include>
					</TD>
				</TR>
			</TABLE>


			<button type="button" onclick="refreshtheweb()" id="search_go"
				style="display: none">
			</button>
		</html:form>
	</body>
</html>
