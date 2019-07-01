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
		<title><bean:message key="lable.title" />
		</title>
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

		
		function refreshtheweb()
		{
			document.forms[0].action = "jxglphotobrowse.do?doType=query";
            document.forms[0].submit();
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
            alert("本图片仅供浏览，谢谢合作！");
            event.cancelBubble = true;
            event.returnValue = false;
            return false;
           }
          }

            document.oncontextmenu = nocontextmenu;//适用于IE5+
            document.onmousedown = norightclick;//所有都适用
            
            
        function searchinfo(){
           
           document.forms[0].action="jxglphotobrowse.do?doType=query";
           document.forms[0].submit();
        
        }


		</script>

		<html:form action="/jxglphotobrowse.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：军训管理 - 军训风采 - 军训照片浏览
				</div>
			</div>
			<fieldset>
				<legend>
					查询
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td>
								标题：
								<html:text name="rs1" property="bt" />
								&nbsp;&nbsp; 发布人：
								<html:text name="rs1" property="fbr" />
								&nbsp;&nbsp; 发布时间:
								<html:select name="rs1" property="xjsj" style="width:90px">
									<html:option value=""></html:option>
									<html:option value="-1">当天</html:option>
									<html:option value="-2">近两天</html:option>
									<html:option value="-7">一周内</html:option>
									<html:option value="-15">半月内</html:option>
									<html:option value="-30">一月内</html:option>
									<html:option value="-90">三月内</html:option>
									<html:option value="-180">半年内</html:option>
									<html:option value="-365">一年内</html:option>
								</html:select>
								&nbsp;&nbsp;
								照片分类：
								<html:select name="rs1" property="remark">
								    <html:option value=""></html:option>
								    <html:option value="国旗护卫队"></html:option>
								    <html:option value="军训相关"></html:option>
								</html:select>
							</td>
							<td>
								<button type="button" class="button2" onclick="searchinfo();">
									搜索
								</button>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					未找到任何记录！
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
									<img title="标题：<bean:write name="v" property="bt" />   图片说明：<bean:write name="v" property="nr" />"
										src="<bean:write name="v" property="path" />"
										style="width:180px;height:127px" /> </a>
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
				<TABLE width="99%" id="rsTable" class="tbstyle">
					<TR>
						<TD>
							<jsp:include flush="true"
								page="/jxgl/jxgl_turnpage.jsp?form=jxglForm"></jsp:include>
						</TD>
					</TR>
				</TABLE>
			</logic:notEmpty>
			<button type="button" onclick="refreshtheweb()" id="search_go"
				style="display: none" >
			</button>
		</html:form>
	</body>
</html>
