
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

		<meta name="Copyright" content="正方软件 zfsoft" />
		<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script language="javascript">	
   
   	 function sqSave(){
        var pc = "";
        var bj="";
        if($("bj")){
           bj = $("bj").value;
        }
        if($("pc")){
           pc = $("pc").value;
        }
    
        if(bj==""){
          alert("班级不能为空！");
          return false;
        }
        if(pc==""){
          alert("批次不能为空！");
          return false;
        } 
           
        refreshForm("/xgxt/ghxyBjryf.do?method=bjryfShOne&doType=modi");
        showTips("保存中，请稍等...");
        if($("buttonSave")){
          $("buttonSave").disabled=true;
        }
     }
	</script>
	<body>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
		   <html:form action="/ghxyBjryf" method="post">
		   	<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="url" name="url" value="/nbtyJtjjkns.do?method=jtjjknsSq&doType=view" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
			<input type="hidden" name="bjdm" id="bjdm" value="${rs.bjdm}"/>
			<input type="hidden" name="isNjzr" id="isNjzr" value="${isNjzr}"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：评奖评优 - 班级荣誉分审核 - 单个审核
				</div>
			</div>
			<table width="99%" id="rsT" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
						<b>班级荣誉分单个审核</b>
						</td>
					</tr>
				</thead>
				<tr>
				<td align="right" style="width: 10%">
						学年：
					</td>
					<td align="left">
						<bean:write name="rs" property="xn"/>
						<html:hidden property="save_xn" value="${rs.xn}"/>
					</td>
					<td align="right" style="width: 10%">
						学期：
					</td>
					<td align="left" style="width: 40%">
						<bean:write name="xqmc"/>	
						<html:hidden property="save_xq"  value="${rs.xq}"/>
					</td>
				</tr>
					<tr style="height:22px">
					<td align="right">
						年级：
					</td>
					<td align="left">
						<bean:write name="rs" property="nj"/>	
					</td>
					<td align="right">
						<bean:message key="lable.xb" />：
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc"/>	
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						专业：
					</td>
					<td align="left">
						<bean:write name="rs" property="zymc"/>	
					</td>
					<td align="right">
						班级：
					</td>
					<td>
						<bean:write name="rs" property="bjmc"/>	
						<html:hidden property="save_plbjdm" value="${rs.plbjdm}" styleId="bj"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<font color="red"></font>批次：
					</td>
					<td align="left">
						<bean:write name="rs" property="pcmc"/>
						<html:hidden property ="save_pc" value="${rs.pc}" styleId="pc"/>
					</td>		
					<td align="right">
						总分值：
					</td>
					<td align="left">
						<bean:write name="rs" property="dxfz"/>
					</td>
				</tr>
				<tr>
					
					<td align="right">
						审核：
					</td>
					<td align="left">
					<logic:equal name="isNjzr" value="true">
							<html:select property="save_njzrsh" styleId="njzrsh">
								<html:option value="未审核">未审核</html:option>
								<html:option value="通过">通过</html:option>
								<html:option value="不通过">不通过</html:option>
							</html:select>
					</logic:equal>
					<logic:notEqual name="isNjzr" value="true">
						<html:select property="save_xxsh">
								<html:option value="未审核">未审核</html:option>
								<html:option value="通过">通过</html:option>
								<html:option value="不通过">不通过</html:option>
							</html:select>
					</logic:notEqual>
					</td>
					
					<td align="right" style="width: 10%">
						等级：
					</td>
					<td align="left">
					<logic:equal name="isNjzr" value="true">
						<html:select name="rs" property="save_djdm" styleId="djdm">
							<html:option value=""></html:option>
							<html:options collection="ryfdjList" property="djdm"
								labelProperty="djmc" />
						</html:select>
					</logic:equal>
					<logic:notEqual name="isNjzr" value="true">
							<bean:write name="rs" property="djmc" />
					</logic:notEqual>
					</td>
				</tr>
			</table>
			<logic:notEmpty name="rsNum">
				<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：单击表头可以排序</font>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0" indexId="index">
										<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="map" id="s">
							<tr onclick="rowMoreClick(this,'',event);"style="cursor:hand">
								<logic:iterate id="v" name="s" offset="0">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				</logic:notEmpty>
			</logic:notEmpty>
			<div class="buttontool" id="btn"  width="100%">	
				<logic:notEqual name="write" value="no">
					<button type="button" class="button2" id="buttonSave" onclick="sqSave();" style="width:80px">
						保  存 
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:notEqual>
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px" id="buttonSave">
						关 闭
					</button>
			</div>
		</html:form>
		<logic:present name="result">
		<logic:equal name="bjbl" value="no">
					<script>
						alert('超出该等级限定的班级比例！');
					</script>		
				</logic:equal>
		<logic:notEqual name="bjbl" value="no">
		<input type="hidden" id="message" value="${message}"/>
		<script>
				alert(document.getElementById('message').value);
				if(opener){
			 		opener.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
			</logic:notEqual>
		</logic:present>
	</body>


</html>

