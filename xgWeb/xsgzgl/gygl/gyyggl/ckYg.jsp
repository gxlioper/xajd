<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="hqgl/rcgl/js/lfdj.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>

	</head>
	<body style="width:100%">
		<html:form action="/gyglnew_gyygxxgl" method="post" styleId="gyygxxglForm">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�鿴��Ƹ��Ա</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="20%">
								���
							</th>
							<td width="30%">
								${yhxx.ygbh}
							</td>
							<th width="20%">
								����
							</th>
							<td width="30%">
								${yhxx.xm}
							</td>
							</tr>
							<tr>
							<th width="20%">
								�Ա�
							</th>
							<td  width="30%">
							${yhxx.xbmc}
							</td>
							<th width="20%">
								����
							</th>
							<td width="30%">
								${yhxx.nl}
							</td>
							</tr>
							<tr>
							<th width="20%">
								Ƹǰְҵ״��
							</th>
							<td width="30%">
								${yhxx.pqzyzk}
							</td>
							<th width="20%">
								�ָ�
							</th>
							<td width="30%">
								${yhxx.zwmc}
							</td>
							</tr>
							<tr>
							<th width="20%">
								Ƹ������
							</th>
							<td width="30%">
								${yhxx.pyrq}
							</td>
							<th width="20%">
								���֤��
							</th>
							<td width="30%">
								${yhxx.sfzh}
							</td>
							</tr>
							<tr>
							<th width="20%">
								��ϵ�绰
							</th>
							<td width="30%">
								${yhxx.lxdh}
							</td>
							<th width="20%">
								���ʱ�׼
							</th>
							<td width="30%">
								${yhxx.gzbz}
							</td>
							</tr>
							<tr>
								<th width="20%">
									�Ƿ��ڸ�
								</th>
								<td width="30%" colspan="3">
									${yhxx.zgztmc }
								</td>
							</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" type="button" onclick="iFClose();">
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

