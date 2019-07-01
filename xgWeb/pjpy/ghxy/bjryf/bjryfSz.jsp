
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
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script language="javascript">	
   	 function sqSave(){
      	
        refreshForm("/xgxt/ghxyBjryf.do?method=bjryfSz&doType=save");
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
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：${title }
				</div>
			</div>

			<logic:present name="result">
			<script>
				alert(''+$('message').value);
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
			</logic:present>

			<table width="99%" id="rsT" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="5" align="center">
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
						等级列表：
					</td>
					<td align="left">
						<html:select property="save_djdm">
							<html:option value=""></html:option>
							<html:options collection="ryfdjList" property="djdm"
								labelProperty="djmc" />
						</html:select>
					</td>
					<td align="right" style="width: 10%">
						比例：
					</td>
					<td align="left" style="width: 40%">
						<html:text property="save_bjbl" onkeypress="return onlyNum(this,10)"/>%
					</td>
					<td>
					<logic:equal name="writeAble" value="yes">
						<button type="button" class="button2" id="buttonSave" onclick="sqSave();" style="width:80px">
							保  存 
						</button>
					</logic:equal>
					</td>
				</tr>
			</table>
			<logic:notEmpty name="rsNum">
				<logic:notEmpty name="map">
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
								<td>
									<input type="checkbox"  name="all" value="all" onclick="chec()">
								</td>
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
								<td>
								<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="primarykey_cbv" id="pkV" 
											   value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
										>
								</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="0" length="3">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="3" length="1">
									<td nowrap>
										<bean:write name="v" />%
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				</logic:notEmpty>
			</logic:notEmpty>
			<div class="buttontool" id="btn" style="position: absolute;left:1%;top:100px" width="99%">	
				
					<button type="button" class="button2" onclick="dataBatch('ghxyBjryf.do?method=bjryfSz&doType=delete');" style="width:80px">
						清   除
					</button>
				</div>
				<script language="javascript">
						document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
						document.getElementById("btn").style.width = "96%";
						window.setInterval("initBTNTool('btn')",1);
				</script>
			<logic:equal name="done" value="true">
				<script>
			          alert("申请成功！");
			        </script>
			</logic:equal>
			<logic:equal name="done" value="false">
				<script>
			          alert("申请失败！");
			    </script>
			</logic:equal>
			<html:hidden property="noSq"  value="${notSqsj}" styleId="notSqsj"/>
			<logic:equal name="notKns" value="yes">
				<script>
			        alert("对不起，只有困难生才能申请家庭经济困难生困难补助！");			    
			        </script>
			</logic:equal>
		</html:form>
	</body>


</html>

