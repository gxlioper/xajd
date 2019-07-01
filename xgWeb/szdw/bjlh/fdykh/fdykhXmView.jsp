<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
</head>
<body onload="">
	<html:form action="/bjlh_fdykh" method="post">
		<html:hidden property="khbid" name="rs" styleId="khbid"/>
		
		<div class="tab">
		<table class="formlist" width="95%">
			<thead>
				<tr>
					<th colspan="5"><span>辅导员测评表：${rs.khbmc}</span></th>
				</tr>
			</thead>
			<tbody>

<%--			<tr>--%>
<%--			<td colspan="5">学院，辅导员姓名</td>--%>
<%--			</tr>--%>
			<thead>
			<tr>
				<th>一级指标</th>
				<th>二级指标</th>
				<th>考核内容</th>
				<th>分值</th>
				<th>得分</th>
			</tr>
			</thead>
			<logic:present name="xmList">
			<logic:iterate id="xm" name="xmList">
				<tr>
					<logic:notEmpty name="xm" property="yjzbRowNum">
						<td width="150px" style="word-break:break-all" rowspan="${xm.yjzbRowNum}">${xm.yjzb }</td>
					</logic:notEmpty>
					<td width="150px" style="word-break:break-all" >${xm.ejzb }</td>
					<td width="300px" style="word-break:break-all" >${xm.khnr }</td>
					<td width="35px">${xm.fzqj }</td>
					<td width="35px">${xm.df}</td>
				</tr>
			</logic:iterate>
			</logic:present>
			</tbody>
		</table>
		</div>
	</html:form>
	<logic:present name="back">
		<script type="text/javascript">
			alertInfo("${back}",function(){
				if(window.dialogArguments){
					if(window.dialogArguments.document.getElementById("search_go")){
						dialogArgumentsQueryChick();
					}
					window.close();
				}
			});
		</script>
	</logic:present>
</body>
</html>
