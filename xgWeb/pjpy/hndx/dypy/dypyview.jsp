<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/BatAlert.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
</head>
<body>
	<html:form action="hndx_dypy.do" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ�ã���������ά�� - ��������ɼ�¼��
			</div>
		</div>
		<input type="hidden" id="url" name="url"
			value="/hndx_dypy.do?method=dypyadd" />
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm" />
		<input type="hidden" id="doType" value="" />
		<input type="hidden" name="pkValue" value="${param.pkValue }"/>
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:22px">
					<td colspan="12" align="center">
						<b>ѧ��������Ϣ</b>
					</td>
				</tr>
			</thead>
			<tr>

				<td align="center" width="20%">
					<font color="red">*</font>ѧ��
				</td>
				<td align="left" width="30%">
					<html:text property="xh" styleId="xh" readonly="readonly" value="${rs.xh}"/>
				</td>

				<td width="20%">
					<div align="center">
						ѧ��
					</div>
				</td>
				<td width="30%">
					<input type="hidden" name="xn" value="${rs.xn }" />
					${rs.xn }
				</td>

			</tr>
			<tr>
				<td>
					<div align="center">
						��Ŀ����
					</div>
				</td>
				<td>
					<input type="hidden" name="xmdm" value="${rs.xmdm }" />
					${rs.xmmc }
				</td>
				<td>
					<div align="center">
						��Ŀ�ӷ�
					</div>
				</td>
				<td>
					<input type="text" name="xmjf" value="${rs.xmjf }" />
				</td>
			</tr>
			<tr>
				<td width="16%">
					<div align="center">
						����
					</div>
				</td>
				<td width="34%">
					${rs.xm }
				</td>
				<td>
					<div align="center">
						�Ա�
					</div>
				</td>
				<td>
					${rs.xb }
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
				</td>
				<td>
					<div align="center">
						רҵ
					</div>
				</td>
				<td>
					${rs.zymc }
				</td>
			</tr>
			<tr>

				<td>
					<div align="center">
						�༶
					</div>
				</td>
				<td>
					${rs.bjmc }
				</td>
			</tr>
		</table>
	
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<button type="button" class="button2"
					onClick="saveData('hndx_dypy.do?method=dypyViewAndModi&doType=save','');">
					�� ��
				</button>
				&nbsp;&nbsp;
				<button type="button" class="button2"
					onClick="Close();">
					�� ��
				</button>
			</div>
	</html:form>
	<logic:present name="msg">
		<input type="hidden" id="msg" value="${msg }" />
		<script type="text/javascript">
			alert(document.getElementById('msg').value);
			Close();
			if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
				window.dialogArguments.document.getElementById('search_go').click();	
			}
		</script>
	</logic:present>
</body>
</html:html>
