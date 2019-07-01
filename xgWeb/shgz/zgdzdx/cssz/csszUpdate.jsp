<%@ page language="java" contentType="text/html; charset=GBK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
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
	function saveCssz(obj){
		if($("hddm").value == ""){
			alert("活动名称不能为空，请确认！！");
			return false;
		}
		if($("xn").value == ""){
			alert("学年不能为空，请确认！！");
			return false;
		}
		if($("zd").value == ""){
			alert("字段不能为空，请确认！！");
			return false;
		}
		if($("zdm").value == ""){
			alert("字段名不能为空，请确认！！");
			return false;
		}
		showTips('处理数据中，请等待......');
		refreshForm('/xgxt/zgddShgzCssz.do?method=csszUpdate&doType=save');
		obj.disabled=true;
		
<%--		if($("hdV")&&$("hdV")!=null){--%>
<%--			url+="&hdV="+$("hdV").value;--%>
<%--		}--%>
<%--		if($("zdV")&&$("zdV")!=null){--%>
<%--			url+="&zdV="+$("zdV").value;--%>
<%--		}--%>
<%--		if($("xnV")&&$("xnV")!=null){--%>
<%--			url+="&xnV="+$("xnV").value;--%>
<%--		}--%>
	}
	
	function checkZd(obj,zbid){
		var pk = $("hddm").value+$("xn").value+zbid;
		getCsszDAO.getZd(pk,function(data){
			if (data != null) {
				if(data != "0"){
					alert("该活动中，此字段已经存在，请重新输入");
					obj.value="";
					obj.focus();
				}
			}
		});
	}
	
	function chHd(hddm){
		var hddm = $("hddm").value;
		var xn = $("xn").value;
		if(hddm == "" || xn == ""){
			$("zd").disabled=true;
			$("zd").value= "";
		}else{
			$("zd").disabled=false;
		}
	}
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getCsszDAO.js'></script>
		<html:form action="/zgddShgzCssz" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>社会工作 - 参数设置 - 参数设置</a>
				</p>
			</div>

			<logic:notEmpty name="rs">
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="pk" scope="request"/>" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />

				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>字段管理</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"
										<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<logic:notEqual name="doType" value="view">
											<button class="" onclick="saveCssz(this);" style="width:80px"
												id="buttonSave">
												保存
											</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:notEqual>
										<button class="" onclick="Close();return false;" style="width:80px"
											id="buttonClose">
											关闭
										</button>
									</div>
									</div>
								</td>
							</tr>
						</tfoot>


						<tbody>
							<tr>
								<td align="right" style="width:50%">
									活动名称
								</td>
								<td align="left" style="width:50%">
									<logic:equal name="doType" value="add">
										<html:select name="rs" property="hddm" style=""
											onchange="chHd()">
											<html:option value=""></html:option>
											<html:options collection="hdList" property="hddm"
												labelProperty="hdmc" />
										</html:select>
									</logic:equal>
									<logic:notEqual name="doType" value="add">
										<input type="hidden" id="hdV" name="hdV"
											value="<bean:write name="rs" property="hddm"/>" />
										<html:select name="rs" property="hddm" style=""
											disabled="true">
											<html:option value=""></html:option>
											<html:options collection="hdList" property="hddm"
												labelProperty="hdmc" />
										</html:select>
									</logic:notEqual>
								</td>
							</tr>
							<tr>
								<td align="right" style="width:50%">
									学年
								</td>
								<td align="left">
									<logic:equal name="doType" value="add">
										<html:select name="rs" property="xn" style=""
											onchange="chHd()">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</logic:equal>
									<logic:notEqual name="doType" value="add">
										<html:select name="rs" property="xn" style="" disabled="true">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</logic:notEqual>
									<input type="hidden" id="xnV" name="xnV"
										value="<bean:write name="rs" property="xn"/>" />
								</td>
							</tr>
							<tr>
								<td align="right">
									字段
								</td>
								<td align="left">
									<html:text name='rs' property="zd" styleId="zd"
										onblur="checkZd(this,this.value)" disabled="true" />
									<input type="hidden" id="zdV" name="zdV"
										value="<bean:write name="rs" property="zd"/>" />
								</td>
							</tr>
							<tr>
								<td align="right">
									字段名
								</td>
								<td align="left">
									<html:text name='rs' property="zdm" styleId="zdm" />
								</td>
							</tr>
							<tr>
								<td align="right">
									字段类型
								</td>
								<td align="left">
									<html:select name="rs" property="zdlx">
										<html:option value="001">文本</html:option>
										<html:option value="002">时间</html:option>
										<html:option value="003">文本域</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<font color="red">注字段建议输入拼音或英文缩写，例“指导老师”为zdls，不建议录入纯数字或汉字；</font>
									<font color="red">字段名建议录入有意义的汉字或英文。</font>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</logic:notEmpty>
			<logic:present name="result">
				<logic:equal name="result" value="yes">
					<script>
				    alert("提交成功！");
				    dialogArgumentsQueryChick();
					Close();
				    </script>
				</logic:equal>
				<logic:equal name="result" value="no">
					<script>
				    alert("提交失败！");
				    </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
