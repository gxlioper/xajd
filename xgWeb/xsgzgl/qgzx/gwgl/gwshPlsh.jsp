<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script type='text/javascript' src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/qgzx/gwgl/gwsh.js" defer="defer"></script>
	</head>
	<body>
		<html:form action="/qgzx_gwgl" method="post">
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue}"/>
			<div id="tempDiv" >
				<table align="center" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>��λ��Ϣ�������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr  style="height:40px">
							<th width="30%">
								<span class="red">*</span>������<br/><font color="red">(��1000��)</font>
							</th>
							<td width="70%">
								<html:textarea property='shyj' styleId="shyj" style="word-break:break-all;width:97%" onblur="chLeng(this,1000);"
									rows='4'   />
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="gwxxshBc(1);">
										ͨ��
									</button>
									<button type="button" onclick="gwxxshBc(2);">
										��ͨ��
									</button>
									<button type="button" onclick="gwxxshBc(3);">
										�˻�����
									</button>
									<button type="button" name="�ر�" onclick="Close();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>

