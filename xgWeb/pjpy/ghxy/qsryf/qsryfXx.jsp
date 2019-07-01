
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
        var xmfz="";
        if($("xmfz")){
          xmfz = $("xmfz").value;
        }
        if(xmfz==""){
          alert("指标分值不能为空！");
          return false;
        }  
        refreshForm("/xgxt/ghxyQsryf.do?method=qsryfXx&doType=modi");
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
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：评奖评优 - 班级荣誉分查询 - 填写表
				</div>
			</div>
			<table width="99%" id="rsT" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
						<logic:equal name="notSqsj" value="no">
							<b>家庭经济困难学生补助申请</b>
						</logic:equal>
						<logic:equal name="notSqsj" value="yes">
							<font color="red">目前不在申请时间范围内，暂不开放申请！</font>
						</logic:equal>
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
						<bean:write  name="rs" property="xq"/>	
						<html:hidden property="save_xq"  value="${rs.xqmc}"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						寝室区域：
					</td>
					<td align="left">
						<bean:write name="rs" property="yqqmc"/>	
					</td>
					<td align="right">
						楼栋名称：
					</td>
					<td align="left">
						<bean:write name="rs" property="ldmc"/>	
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						楼层：
					</td>
					<td align="left">
						<bean:write name="rs" property="cs"/>	
					</td>
					<td align="right">
						寝室号：
					</td>
					<td>
						<bean:write name="rs" property="qsh"/>	
						<html:hidden property="save_plssbh" value="${rs.plssbh}" styleId="plssbh"/>
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
						指标内容：
					</td>
					<td align="left">
						<bean:write name="rs" property="xmmc"/>
						<html:hidden property ="save_xmdm" value="${rs.xmdm}" styleId="xmdm"/>
					</td>	
				</tr>
				<tr>
					<td align="right">
						分值：
					</td>
					<td align="left">
						<html:text property="save_xmfz" styleId="xmfz" value="${rs.xmfz}"/>
					</td>
					<td>
						
					</td>
					<td>
					
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
						<logic:iterate name="rs" id="s">
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
				</logic:notEqual>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px" id="buttonSave">
						关 闭
					</button>
			</div>
		</html:form>
		<logic:present name="result">
		<input type="hidden" id="message" value="${message}"/>
		<script>
				alert(document.getElementById('message').value);
				if(opener){
			 		opener.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
	</body>


</html>

