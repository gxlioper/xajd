<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/hdgl/js/hdbljgImport.js"></script>
	</head>
	<body>
		<form id="importForm" name="importForm" action="zfxg/drdcsj/import_importData.html" method="post" enctype="multipart/form-data">
			<input type="hidden" id="drmkdm" name="drmkdm" value="${drmbxx.drmkdm}">
			<div class="tab">
			<table width="100%" border="0" class=" formlist" cellpadding="0"
					cellspacing="0">
					<thead>
						<tr>
							<td colspan="4">
								<span>��������</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								ģ������
							</th>
							<td colspan="3">
								${drmbxx.drmkmc}
							</td>
						</tr>
						<tr>
							<th>
								ģ������
							</th>
							<td colspan="3">
								<a href="javascript:void(0);" class="name" onclick="downloadTemplate();">����EXCELģ��</a> 
							</td>
						</tr>
						<tr>
							<th>
								�ϴ�����
							</th>
							<td colspan="3">
								<input type="file" name="importFile" id="importFile"
									style="width: 200px;" />
								<span style="color:red;font-style:normal"><span>��ʾ��</span><span>������Excel�ļ�</span></span>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button id="drBtn" onclick="selectImport();" type="button" class="ui_state_highlight">
										����
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				<table width="100%" border="0" class=" formlist" cellpadding="0" id="dr_result"
					style="margin-top: 2px; display: none;" cellspacing="0" >
					<tbody>
						<tr>
							<th width="20%">
								������
							</th>
							<td colspan="3" id="importResult" style="font-weight: bold">
								&nbsp;&nbsp;��
							</td>
						</tr>
						<tr>
							<th>
								�쳣����
							</th>
							<td colspan="3" id="cwsj">
								<font>�޴�������</font>
							</td>
						</tr>
					</tbody>
				</table>
				<div id="drts">
					<div id="rule_header">
						<table width="100%" border="0" class=" formlist" cellpadding="0" style="margin-top: 2px;" 
						cellspacing="0" align="center">
						<thead>
							<tr>
								<td colspan="6">
									<span>�������</span>
								</td>
							</tr>
							<tr style="background-color: #FFFACD;font-weight: bold">
								<td align='right'  style="width: 15%" id="rule_header_1">
									������
								</td>
								<td align='center' style="width:10%"  id="rule_header_2">
									����
								</td>
								<td align='center'  style="width:10%"  id="rule_header_3">
									�Ƿ�Ψһ
								</td>
								<td align='center'  style="width:10%"  id="rule_header_4">
									����Ϊ��
								</td>
								<td align='center'  style="width:10%"  id="rule_header_5">
									��󳤶�
								</td>
								<td align='center'  style="width:55%"  id="rule_header_6">
									���ݸ�ʽ
								</td>
							</tr>
						</thead>
					</table>
					</div>
					<div id="rule_div" style="height: 220px; overflow-x: hidden; overflow-y: scroll;">
						<table width="100%" border="0" class=" formlist" cellpadding="0"  frame="vsides"
						cellspacing="0" align="center">
							<tbody>
								<logic:iterate id="drgzxx" name="drgzxxList" indexId="rowid">
				                     <tr>
										<td align='right' style="width: 15%">${drgzxx.drlmc }</td>
										<td align='center' style="width:10%">
											${drgzxx.sfzj==1?"<img src='/xgweb/images/right.png'>":""}
										</td>
										<td align='center' style="width:10%">
											${drgzxx.sfwy==1?"<img src='/xgweb/images/right.png'>":""}
										</td>
										<td align='center' style="width:10%">
											${drgzxx.sfbt==1?"<img src='/xgweb/images/right.png'>":""}
										</td>
										<td align='center' style="width:10%">${drgzxx.zdcd}</td>
										<td align='center' style="width:55%">${drgzxx.gshxx}</td>
									 </tr>
								 </logic:iterate>
							</tbody>
						</table>
					</div>
					<div id="drts-rule-table-bottom" style="border-bottom: 1px solid #B0CBE0;background-color: #eee;"></div>
				</div>
			</div>
			<div style="height: 30px;"></div>
		</form>
	</body>
</html>
	