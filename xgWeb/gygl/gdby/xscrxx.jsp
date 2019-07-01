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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/XsGyGlLogic.do?method=xsGyXsCr" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					公寓管理 - 信息维护 - 学生非常规出入登记
				</div>
			</div>
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />			
			<fieldset>
				<legend>
					查询条件
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
							   &nbsp;&nbsp;年度：
							   <html:select  property="nd" style="width:100px" styleId="nd">
									<html:options collection="ndList" property="nd"
										labelProperty="nd" />
								</html:select>
								 &nbsp;&nbsp;月份：
								<html:select  property="yf" style="width:100px" styleId="yf">
								    <html:option value=""></html:option>
									<html:options collection="yfList" property="yf"
										labelProperty="yf" />
								</html:select>
								&nbsp;&nbsp;楼栋名称：
								<html:select property="lddm" style="width:90px" styleId="lddm">
									<html:option value=""></html:option>
									<html:options collection="ldList" property="lddm" labelProperty="ldmc" />
								</html:select>							
								&nbsp;&nbsp;日期：
								<html:text property="rq" styleId="rq" readonly="true" onblur="dateFormatChg(this)"
						    onclick="return showCalendar('rq','y-mm-dd');" style="width:100px;cursor:hand " ></html:text>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button class="button2" style="height:40px" id="search_go"
									onclick="document.forms[0].go.value='go';refreshForm('/xgxt/XsGyGlLogic.do?method=xsGyXsCr')">
									查 询
								</button>
							</td>
						</tr>
						<tr>
						<td align="left">
								&nbsp;&nbsp;学号：
								<html:text property="xh" style="width:100px;"></html:text>
								&nbsp;&nbsp;姓名：
								<html:text property="xm" style="width:100px;"></html:text>
								&nbsp;&nbsp;携带物品：
								<html:text property="xdp"  style="width:100px;"></html:text>
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
				<fieldset>
					<legend>
						当前记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr  onclick="rowOnClick(this)"
								style="cursor:hand;background-color:
                          <logic:iterate id="v" name="s" offset="0" length="1">
                           <bean:write name="v"/>
                            </logic:iterate>
                             " style="cursor:hand" ondblclick="myViewMore('view')">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td >
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				<TABLE width="99%" id="rsTable" class="tbstyle">
					<TR>
						<TD height=3></TD>
					</TR>
					<TR>
						<TD>
							<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=xsgyglForm"></jsp:include>
						</TD>
					</TR>
					<TR>
						<TD height=3></TD>
					</TR>
				</TABLE>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<logic:equal value="yes" name="writeAble" scope="request">
						<button class="button2" onclick="myViewMore('add')"
							style="width:80px">
							增 加
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="myViewMore('modi')"
							style="width:80px">
							修 改
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="myViewMore('del')"
							style="width:80px">
							删 除
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:equal>												
					</div>
				</center>
		</html:form>
		<script language="javascript">
document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
document.getElementById("btn").style.width = "96%";
window.setInterval("initBTNTool('btn')",1);
</script>
<script type="text/javascript">
function myViewMore(doType){
   var pkValue = "";
   var url = "/xgxt/XsGyGlLogic.do?method=xsGyXsCr_Modi&doType=";
   url += doType;
   url += "&pkValue=";
   if(doType=="add"){
      url +=pkValue;
      showTopWin(url,'600','400');
   }else {
      if(curr_row == null){
        alert("请选择要操作的数据！\n（单击相应的行）");
		return false;
      }
      pkValue = curr_row.getElementsByTagName("input")[0].value;  
      url +=pkValue;
      if(doType=="view"){
        showTopWin(url,'600','400');
      }
      if(doType=="modi"){                        
         if(confirm("确定要修改该数据？")){
             showTopWin(url,'600','400');
         }else{
             return false;
         }
      }
      if(doType=="del"){
         if(confirm("确定要修删除该行数据？")){
             refreshForm(url);
         }else{
             return false;
         }
      }
   }
}
function BbTj(url){
 if(document.getElementById('lddm').value==""){
		alert("楼栋名称不能为空！");
		return false;
    }
    url+="&lddm="+document.getElementById('lddm').value;
    url+="&nd="+document.getElementById('nd').value;
    url+="&yf="+document.getElementById('yf').value;
    url+="&rq="+document.getElementById('rq').value;
     showTopWin(url,'800','700');
}
</script>

<logic:equal value="ok" name="done">
<script type="text/javascript">
alert("操作成功！");
document.getElementById('search_go').click();   
</script>
</logic:equal>
<logic:equal value="no" name="done">
<script type="text/javascript">
alert("操作失败！");
document.getElementById('search_go').click();   
</script>
</logic:equal>
</body>
</html>		

		
