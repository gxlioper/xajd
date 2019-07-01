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
<html:html>
<base target="_self" />
<head>


	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript'src='/xgxt/dwr/interface/getSztzData.js'></script>
    <script type='text/javascript'src='/xgxt/dwr/interface/GetListData.js'></script>
    <script type='text/javascript'src='/xgxt/dwr/interface/gyglJhzyDAO.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function dataSave(){
		    var xh = "";
			var hdnr = "";
	        if($("xh")){
	           xh = $("xh").value;
	        }
	        if($("hdnr")){
	           hdnr = $("hdnr").value;
	        }
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			if(hdnr != null){
	         	if(hdnr.replace(/[^\u0000-\u00ff]/g, "**").length > 1000){	         
	          		 alert("活动内容不能大于500个字");
	          		 return false;
	       		 }
	       	}
			refreshForm("/xgxt/jhzy_gygl.do?method=gydjInfoAdd&doType=save");	   							
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function fzrInfo(){
		    var dm = $("ssgyzb").value;
		    gyglJhzyDAO.getZbfzr(dm,function(data){
		         $("zbfzr").value=data;
		    });
		}
	</script>
</head>
<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：公寓管理 - 信息维护 - 公寓党建信息 - 维护 
		</div>
	</div>
	<html:form action="/jhzy_gygl" method="post">
		<html:hidden name="rs" property="guid"/>
		<input type="hidden" id="url" name="url"
			value="/jhzy_gygl.do?method=gydjInfoAdd" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xymc-zymc-bjmc-nj" />
		<input type="hidden" name="act" id="act" value="${act}" />
		<table class="tbstyle" width="100%">
			<tr>
				<td align="center" width="16%">
					<font color="red">*</font>学号
				</td>
				<td align="left" width="34%">
					<html:text name='rs' property="xh" styleId="xh" readonly="true"
						onkeypress="autoFillStuInfo(event.keyCode,this)" />
					<logic:empty name="act">
						<button onclick="showTopWin('/xgxt/stu_LdQsInfo.do',750,550);"
							class="btn_01" id="buttonFindStu">
							选择
						</button>
					</logic:empty>
				</td>
				<td width="16%">
					<div align="center">
						姓名
					</div>
				</td>
				<td width="34%">
				${rs.xm }
<%--					<html:text name="rs" property="xm"  style="width:100%" readonly="true"/>--%>
				</td>
			</tr>
			<tr>
			    <td>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />
					</div>
				</td>
				<td>
				${rs.xymc }
<%--					<html:text name="rs" property="xymc"  style="width:100%" readonly="true"/>--%>
				</td>
				<td>
					<div align="center">
						专业
					</div>
				</td>
				<td>
				${rs.zymc }
<%--					<html:text name="rs" property="zymc"  style="width:100%" readonly="true"/>--%>
				</td>
			</tr>
			<tr>
			    <td>
					<div align="center">
						班级
					</div>
				</td>
				<td>
				${rs.bjmc }
<%--					<html:text name="rs" property="bjmc"  style="width:100%" readonly="true"/>--%>
				</td>
				<td>
					<div align="center">
						年级
					</div>
				</td>
				<td>
				${rs.nj }
<%--					<html:text name="rs" property="nj"  style="width:100%" readonly="true"/>--%>
				</td>
			</tr>
			<tr>
			    <td>
					<div align="center">
						楼栋
					</div>
				</td>
				<td>
				${rs.ldmc }
<%--					<html:text name="rs" property="ldmc"  style="width:100%" readonly="true"/>--%>
				</td>
				<td>
					<div align="center">
						楼层
					</div>
				</td>
				<td>
				${rs.cs }
<%--					<html:text name="rs" property="cs"  style="width:100%" readonly="true"/>--%>
				</td>
			</tr>
			<tr>
			    <td>
					<div align="center">
						寝室号
					</div>
				</td>
				<td>
				${rs.qsh }
<%--					<html:text name="rs" property="qsh"  style="width:100%" readonly="true"/>--%>
				</td>
				<td>
					<div align="center">
						宿舍编号
					</div>
				</td>
				<td>
					<html:text name="rs" property="ssbh"  style="width:100%" maxlength="50" readonly="true"/>
				</td>
			</tr>
			<tr>
			    <td>
					<div align="center">
						考核对象
					</div>
				</td>
				<td>
				<html:select name="rs" property="khdx" styleId="khdx">
				<html:option value=""></html:option>
											<html:options collection="khdxList" property="dm"
												labelProperty="khdx" />
										</html:select>
<%--					<html:text name="rs" property="khdx"  style="width:100%" maxlength="50"/>--%>
				</td>
				<td>
					<div align="center">
						入到考察时间
					</div>
				</td>
				<td>
					<input type="text" readonly style="cursor:hand;width:100%"
							onclick="return showCalendar('rdkcsj','y-mm-dd');"
							value="<bean:write name='rs' property="rdkcsj" />" name="rdkcsj"
							id="rdkcsj" />
				</td>
			</tr>
			<tr>
			    <td>
					<div align="center">
						所属公寓支部
					</div>
				</td>
				<td>
				<html:select name="rs" property="ssgyzb" styleId="ssgyzb" onchange="fzrInfo()">
				<html:option value=""></html:option>
											<html:options collection="gyzbList" property="dm"
												labelProperty="zbmc" />
										</html:select>
				
<%--					<html:text name="rs" property="ssgyzb"  style="width:100%" maxlength="50"/>--%>
				</td>
				<td>
					<div align="center">
						支部负责人
					</div>
				</td>
				<td>				
					<html:text name="rs" property="zbfzr"  style="width:100%" maxlength="50" readonly="true"/>
				</td>
			</tr>
			<tr>
			    <td>
					<div align="center">
						参加活动时间
					</div>
				</td>
				<td>
					<input type="text" readonly style="cursor:hand;width:100%"
							onclick="return showCalendar('cjhdsj','y-mm-dd');"
							value="<bean:write name='rs' property="cjhdsj" />" name="cjhdsj"
							id="cjhdsj" />
				</td>
				<td>
					<div align="center">
						活动主题
					</div>
				</td>
				<td>
					<html:text name="rs" property="hdzt"  style="width:100%" maxlength="50"/>
				</td>
			</tr>
			<tr>
			    <td>
					<div align="center">
						活动内容
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rs" property="hdnr" rows='5' style="width:100%" onblur="yzdx(this,'hdnr');chLeng(this,100);"/>
					<input type="hidden" id="hdnr" name="hdnr" value="">
				</td>
			</tr>
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
			<logic:notEqual value="view" name="act">			
				<button class="button2" onClick="dataSave();" id="buttonSave">
					保&nbsp;&nbsp;存
				</button>	
			&nbsp;&nbsp;
			</logic:notEqual>
			<button class="button2" onClick="Close();">
				关&nbsp;&nbsp;闭
			</button>
		</div>	
	</html:form>
				<logic:equal value="true" name="done">
			  <script type="text/javascript">
			    alert('保存成功！');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>	
			<logic:equal value="false" name="done">
			  <script type="text/javascript">
			    alert('保存失败！');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>
</body>
<logic:present name="msg">
	<script>
			alert(''+document.getElementById('msg').value);
		</script>
</logic:present>
</html:html>
