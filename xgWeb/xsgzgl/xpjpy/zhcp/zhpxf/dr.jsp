<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zhcp/zhpxf/js/zhpxf.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<html:form action="/xpj_zhpxf" method="post" styleId="zhpxfForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<tbody>
						<tr>
							<th style="width:20%">����</th>
							<td style="width:30%">
								${jsxx.xm}
							</td>
							<th style="width:20%">����</th>
							<td  style="width:30%">
								${jsxx.zgh}
							</td>
						</tr>
						<tr>
							<th>����</th>
							<td>
								${jsxx.bmmc}
							</td>
							<th>��ϵ��ʽ</th>
							<td>
							    ${jsxx.lxfs}
							</td>
						</tr>
						<tr>
							<th>����ģ������
							</th>
							<td colspan="3">
								<a href="javascript:void(0);" onclick="downloadxzmb();" class="name"  style="margin-left:3px;font-weigth:bold;font-size:14px">����</a>
								
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>ѡ�����ļ�
							</th>
							<td colspan="3">
								<input type="file" onchange="attachfilename(this)" id="drmb" name="drmb"/>
							</td>
						</tr>
						<tr>
						<th>����˵��</th>
						<td colspan="3"><span class="red" style="font-weight:bold">��ʹ�ñ�ҳ���ṩ������ģ����е��룡</span></td>
						</tr>
						<tr id="errortr" style="display: none">
						     <th>������������</th>
							<td colspan="3">
								<a href="javascript:void(0);" data_file="" onclick="downloaderror();" id = "errora" class="name" style="margin-left:3px;font-weigth:bold;font-size:14px">����</a>
							</td>
						</tr>
					</tbody>
				</table>
					        
				</div>	
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveRr();">
										����
									</button>
									<button type="button" onclick="iFClose();">
										�ر�
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