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
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/BatAlert.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="js/commit.js"></script>
</head>
<body>

	<html:form action="/hndx_fzxsz.do" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ�ã�
				<logic:equal value="view" name="operation">ѧ����չ�����ʷֵ����鿴</logic:equal>
				<logic:equal value="modi" name="operation">ѧ����չ�����ʷֵ����޸�</logic:equal>
			</div>
		</div>

		<input type="hidden" name="pkValue" value="${param.pkValue }" />
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:22px">
					<td colspan="12" align="center">
						<b>��չ�����ʷ���Ϣ</b>
					</td>
				</tr>
			</thead>

			<logic:empty name="rs">
				<tr>
					<td>
					<br />
					<br />
					<p align="center">
						��ѧ�����ʷ���Ϣδ¼�룡
					</p>
					</td>
				</tr>
			</logic:empty>

			<logic:notEmpty name="rs">
				<logic:iterate id="map" name="rs" length="1">
					<tr>
						<td align="center" width="20%">
							ѧ��
						</td>
						<td align="left" width="30%">
							${map.xh }
						</td>
						<td width="20%">
							<div align="center">
								ѧ��
							</div>
						</td>
						<td width="30%">
							<input type="hidden" name="xn" value="${map.xn }" readonly="true" />
							${map.xn }
						</td>

					</tr>
					<tr>
						<td width="16%">
							<div align="center">
								����
							</div>
						</td>
						<td width="34%">
							${map.xm }
						</td>
						<td>
							<div align="center">
								�Ա�
							</div>
						</td>
						<td>
							${map.xb }
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								<bean:message key="lable.xb" />
							</div>
						</td>
						<td>
							${map.xymc }
						</td>
						<td>
							<div align="center">
								רҵ
							</div>
						</td>
						<td>
							${map.zymc }
						</td>
					</tr>
					<tr>
						<td>
							<div align="center">
								�༶
							</div>
						</td>
						<td>
							${map.bjmc }
						</td>
					</tr>
				</logic:iterate>

				<logic:iterate id="map" name="rs">
					<tr>
						<td>
							<div align="center">
								��Ŀ����
							</div>
						</td>
						<td>
							${map.xmmc }
						</td>
						<td>
							<div align="center">
								��Ŀ�ӷ�
							</div>
						</td>
						<td>
							<input type="hidden" name="xh" value="${map.xh }" />
							<input type="hidden" name="xmdm" value="${map.xmdm }" />
							<input type="hidden" name="xmmc" value="${map.xmmc }" />
							<input type="text" name="xmjf" value="${map.xmjf }" />
						</td>
					</tr>
				</logic:iterate>
			</logic:notEmpty>
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
			<logic:equal value="modi" name="operation">
				<button type="button" class="button2" style="width:80px"
					onClick="saveData('hndx_fzxsz.do?method=fzxszViewAndModi&doType=save','');">
					�� ��
				</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			</logic:equal>
			<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
				id="buttonClose">
				�� ��
			</button>
		</div>
	</html:form>
	<logic:present name="msg">
		<input type="hidden" id="message" value="${msg }" />
		<script type="text/javascript">
			alert(document.getElementById('message').value);
				Close();
				if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
					window.dialogArguments.document.getElementById('search_go').click();	
				}
		</script>
	</logic:present>
</body>
</html:html>
