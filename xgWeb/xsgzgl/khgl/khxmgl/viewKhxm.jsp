<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/khgl/khxmgl/js/khxmgl.js"></script>	
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
		
		</script>
	</head>
	<body>
		<html:form action="/khglKhxmgl" method="post"
			styleId="KhxmglForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table align="center" class="formlist">
						<thead>
						<tr>
							<th colspan="4">
								<span>������Ŀ��Ϣ</span>
							</th>
						</tr>
						</thead>
					<tbody>
						<tr>
							<th>
								��Ŀ����
							</th>
							<td >
								${rs.xmmc }
							</td>
							<th>
								���˶���
							</th>
							<td>
								${rs.khdxmc }
							</td>
						</tr>
						<tr>
							<th>
								���ֿ�ʼʱ��
							</th>
							<td>
								${rs.kssj }
							</td>
							<th>
								���ֽ���ʱ��
							</th>
							<td>
								${rs.jssj }
							</td>
						</tr>
						<tr>
							<th>
								��Ŀ����
							</th>
							<td colspan="3">
								${rs.xmms }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>
									���ֶ�����Ϣ
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
					<td colspan="4">
					<div  id="pfdxDiv" class="con_overlfow" style="width:100%;overflow-y:auto;vertical-align: top;">
					<logic:iterate id="i" name="pfdxList" indexId="index01">
									<table name="tab_pfdx" class="dateline" width="100%" id="tab_${index01}">
									<tr><td colspan="4" >
									<span style="float:left">
									<a  style="text-align: left;" onclick="showPfzmx(this,'orign');" class="up"
											href="javascript:void(0);"> <font color="blue">���չ��/����</font>
										</a>
										</span>
										</td>
										</tr>
										<tbody id="pfzTbody_${index01}" class="pfdxTbody">
										<tr name="pfzTr">
											<th width="20%">
												������
											</th>
											<td width="30%">
												${i.pfzmc}
												
											</td>
											<th width="20%">
											<logic:equal name='i' property='sfnz' value="2">
												���ַ�Χ
												</logic:equal>
											</th>
											<td name="pffwTd" width="30%">
											<logic:equal name='i' property='sfnz' value="2">
												${i.sjfw}
											</logic:equal>
											</td>
										</tr>
										<tr>
											<th width="20%">
												���˱�
											</th>
											<td colspan="3" >
												${i.khbmc}
											</td>
										</tr>
										<tr>
											<th width="20%">
												���㷽ʽ
											</th>
											<td width="30%">
											    ${i.jsfsmc}
											</td>
											<th width="20%">
												��ռȨ��
											</th>
											<td width="30%" >
												${i.szqz}%
											</td>
										</tr>
										<logic:equal value="2" name="i" property="jsfs">
										<tr>
											
											<th width="20%">
												���㷽ʽ�ٷֱ�
											</th>
											<td width="30%" colspan="3">
												${i.jsfsbfb}%
											</td>
										</tr>
										</logic:equal>
										</tbody>
									</table>
									</logic:iterate>
									</div>
									</td>
									</tr>
									</tbody>
					</table>
			      </div>
			      <div style="height: 35px"></div>
			      <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<button type="button" type="button"
										onclick="iFClose();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>

		</html:form>
	</body>
</html>

