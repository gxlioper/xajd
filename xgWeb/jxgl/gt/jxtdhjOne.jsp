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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<%@ include file="/syscommon/pagehead_V4.ini" %>
		
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />

	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/jxglFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getJxglDAO.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getJxglTyDAO.js'></script>
	
	<script type="text/javascript">
	
	function chBzList(jxnd){
		getJxglDAO.getTdList(jxnd,function(data){
		if (data != null && typeof data == 'object') {
			var objId = "lddm";
			if($(objId) && $(objId).tagName.toLowerCase() == "select"){
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"bzdm","bzmc");
				}
			}else{
				showMsgWin("有错误出现：远程数据读取失败！");
			}
		});
	}
	function saveJxhj(){
		var lddm = $('lddm').value;
		if("0000" == lddm){
			alert("请将带\"*\"号的项目输入完整！");
			return false;	
		}
		Savedata('xn-nd-xq-lddm-jxdm','jxglgt.do?method=jxtdhjOne&type=save');
	}
	
	function getHjsj(){
		var xn=$("xn").value;
		var nd=$("nd").value;
		var xq=$("xq").value;
		var lddm=$("lddm").value;
		var jxdm=$("jxdm").value;
		if(xn !="" && nd!="" && xq!="" && lddm != "" && jxdm != ""){
			var tableName="jxtdhjb";
			var colList =["hjsj"];
			var pk = "xn||nd||xq||lddm||jxdm";
			var pkValue = xn + nd + xq + lddm + jxdm;
			getJxglTyDAO.getJxglInfo(tableName, pk, pkValue,colList,function(data){
				if(data!=null){
					if(data.hjsj !=""){
						$("hjsj").value = data.hjsj;
					}else{
						$("hjsj").value = "";
					}
				}
			});
		}
	}
	</script>
	</head>
	<body onload="disableElement('xn-nd-xq-lddm-jxdm')">
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
		
		<html:form action="/jxgl" method="post">
		
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType"/>" />
			<input type="hidden" id="doType" name="doType"
				value="<bean:write name="doType" scope="request"/>" />
			<input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>" />
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>当前位置：</em>
					<a>军训管理-军训编制-单个军训团队获奖信息维护</a>
				</p>
			</div>
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
						<tr>
							<th colspan="4">
								<span>军训团队获奖信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th>
							<font color="red">*</font>学年：
						</th>
						<td align="left">
							<html:select property="xn" name="rs" style="width:150px" onchange="getHjsj()">
								<html:option value=""></html:option>
								<html:options collection="xnList" labelProperty="xn"
									property="xn" />
							</html:select>
							<html:hidden property="xn" name="rs" />
						</td>
						<th>
							<font color="red">*</font>连队：
						</th>
						<td align="left">
							<html:select property="lddm" name="rs" style="width:150px" onchange="getHjsj()">
								<html:options collection="ldList" labelProperty="bzmc"
									property="bzdm" />
							</html:select>
							<html:hidden property="lddm" name="rs" />
						</td>
					</tr>

					<tr>
						<th align="right">
							<font color="red">*</font>年级：
						</th>
						<td align="left">
							<html:select property="nd" name="rs" style="width:150px" 
								onchange="chBzList(this.value);getHjsj();">
								<html:options collection="njList" labelProperty="njmc"
									property="njdm" />
							</html:select>
							<html:hidden property="nd" name="rs" />
						</td>
						<th align="right">
							<font color="red">*</font>奖项：
						</th>
						<td align="left">
							<html:select property="jxdm" name="rs" style="width:150px" onchange="getHjsj()">
								<html:option value=""></html:option>
								<html:options collection="jxList" labelProperty="jxmc"
									property="jxdm" />
							</html:select>
							<html:hidden property="jxdm" name="rs" />
						</td>
					</tr>

					<tr>
						<th align="right">
							<font color="red">*</font>学期：
						</th>
						<td align="left">
							<html:select name="rs" property="xq" style="width:150px" styleId="xq" onchange="getHjsj()">
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
							<html:hidden property="xq" name="rs" />
						</td>
						<th align="right">
							获奖时间：
						</th>
						<td >
							<html:text name="rs" property="hjsj" styleId="hjsj"
								styleClass="text_nor" 
								onclick="return showCalendar('hjsj','y-mm-dd');" readonly="true"></html:text>
						</td>
					</tr>
					</tbody>
					<tfoot>
					<tr>
					<td colspan="4">
						<div class="bz">
							"<span class="red">*</span>"为必填项
						</div>
						<div class="btn">
						<logic:notEqual name="doType" value="view">
							<button type="button" class="button2" onclick="saveJxhj()" style="width:80px"
								id="buttonSave">
								保 存
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:notEqual>
						<button type="button" class="button2" onclick="Close();return false;" style="width:80px" id="buttonClose">
							关 闭
						</button>
						</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				alert("操作成功!");
				dialogArgumentsQueryChick();
				window.close();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<logic:present name="msg">
				<input type="hidden" id="msg" value="<bean:write name="msg"/>" />
				<script>
					alert(document.getElementById('msg').value);
				</script>
			</logic:present>
			<logic:notPresent name="msg">
				<script>
					alert("操作失败!");
				</script>
			</logic:notPresent>
		</logic:equal>
	</body>
</html>
