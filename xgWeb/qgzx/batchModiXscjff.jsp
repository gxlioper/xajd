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
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="Copyright" content="正方软件 zfsoft">
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/cjff.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script>
		function save(){
			var pkV = val('pkV');
			var nd = val('nd');
			var yf = val('yf');
			var gzsj = val('gzsj');
			var cjje = val('cjje');
			var yhkh = val('yhkh');
			var yhmc = val('yhmc');
			var bz = val('bz');
			var jfglkg = val('jfglkg');
			
			if(!(nd == "" || yf =="")){
				//判断修改后的数据是否和数据库中的数据有重复
				cjff.checkRepeat({pkV:pkV,nd:nd,yf:yf},function(data){
					if(data != null && data != ""){
						alert(data + "操作失败！");
						return false;
					}else{
						if(jfglkg == "1"){//启用经费管理功能
							//判断剩余经费是否充足
							cjff.getBatchOperSyjf({pkV:pkV,nd:nd,yf:yf,cjje:cjje},function(data){
								if(data != null && data != ""){
									alert(data + "操作失败！");
									return false;
								}else{
									refreshForm("qgzxcjff.do?method=modiXscjffBatch");
								}
							});
						}else{
							refreshForm("qgzxcjff.do?method=modiXscjffBatch");
						}
						
					}
				});
			}else{
				alert("请选择时间！");
				return false;
			}
		}
	</script>
	<base target="_self">
	<body>
		<html:form action="/qgzxcjff.do">
			<input type="hidden" name="pkV" id="pkV" value="${pkV}">
			<input type="hidden" name="jfglkg" id="jfglkg" value="${config.jfglkg}">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：勤工助学 - 酬金发放- 批量修改
				</div>
			</div>
			<fieldset>
				<legend>
					选择发放时间
				</legend>
				<table width="100%" class="tbstyle" align="center">
					<thead align="center">
						<tr>
							<td align="center" colspan="4">
								&nbsp;&nbsp;
							</td>
						</tr>
					</thead>					
						<tr>
							<td style="width:50%">
								<div align="right">
									<font color="red">*</font>年度：
								</div>
							</td>
							<td style="width:50%">
								<html:select property="nd" styleId="nd">
									<html:options collection="ndList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>						
						</tr>
						<tr>
							<td align="right">
								<font color="red">*</font>月份：
							</td>
							<td>
								<html:select property="yf" styleId="yf">
									<html:option value=""></html:option>
									<html:options collection="yfList" property="yf" labelProperty="yf" />
								</html:select>
							</td>						
						</tr>
					</table>
				</fieldset>

				<fieldset>
				<legend>
					填写不复制的信息
				</legend>
				<table width="100%" class="tbstyle" align="center">
					<thead align="center">
						<tr>
							<td align="center" colspan="4">
								&nbsp;&nbsp;
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right" width="50%">
							工作时间：
						</td>
						<td> 
							<html:text property="gzsj" maxlength="6" onkeyup="value=value.replace(/[^\d|.]/g,'')"></html:text>
						</td>						
					</tr>
					<tr>
						<td align="right" nowrap="nowrap">
							酬金金额：
						</td>
						<td>
							<html:text property="cjje" maxlength="6" onkeyup="value=value.replace(/[^\d|.]/g,'')"></html:text>
						</td>						
					</tr>
					<tr>
						<td align="right">
							银行名称：
						</td>
						<td>
							<html:text property="yhmc" maxlength="15"></html:text>
						</td>						
					</tr>
					<tr>
						<td align="right">
							银行卡号：
						</td>
						<td>
							<html:text property="yhkh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'')" ></html:text>
						</td>
					</tr>
					<tr>
						<td align="right">
							备注：
						</td>
						<td colspan="3">
							<html:textarea property="bz" styleId="bz" cols="30" rows="4" onblur="chLeng(this,'60')"></html:textarea>
						</td>
					</tr>
				</table>
				</fieldset>
				<center>
					<div class="buttontool" id="btn">
						<button type="button" class="button2"
							onclick="save();return false;"
							style="width:80px">
							保 存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="window.close();return false;"
							style="width:80px">
							关 闭
						</button>
					</div>
				</center>

			 <logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
					alert("操作成功！");
					Close();
					window.dialogArguments.document.getElementById('search_go').click();		
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
					alert("操作失败！");
					Close();
				</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
