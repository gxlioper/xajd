<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xthd/hdkhgl/js/hdkhgl.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form action="/txhd_hdkhgl" method="post" styleId="hdkhForm">
			<div style="tab;overflow-x:hidden;overflow-y:hidden;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>���Ŀ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							
							<th width="16%">
								��Ŀ����
							</th>
							<td width="34%">
								${data.xmmc}
							</td>
							<th width="16%">
								�ʱ��
							</th>
							<td width="34%" >
								${data.hdkssj}��${data.hdjssj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								����
							</th>
							<td width="34%">
								${hdlbmc}
							</td>
							<th width="16%">
								��ص�
							</th>
							<td width="34%" >
								${data.hddd}
							</td>
						</tr>
						<tr>
							<th width="16%">
								������������
							</th>
							<td width="34%" >
								${data.sqrssx}
							</td>
							<th width="16%">
								�����������
							</th>
							<td width="34%" >
								${data.shrssx}
							</td>
							
						</tr>
						<tr>
						<th align="right" width="10%">
						�а쵥λ
						</th>
						<td align="left" >
							${data.cbdw}
						</td>
						<th align="right" width="10%">
						����
						</th>
						<td align="left" >
							${hdgg}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">�����</th>
						<td align="left" colspan="3" >
							${data.hdzt}
						</td>
					</tr>
					<tr>
					<th align="right" width="10%">
							�Ŀ�ļ�����
						</th>
						<td colspan="3">
							${data.hdmdyy}
						</td>
					</tr>
					<tr>
					<th align="right" width="10%">
							�����
						</th>
						<td colspan="3">
							${data.hdfa}
						</td>
					</tr>
						<tr>
							<th width="16%">
								�˵��
							</th>
							<td width="34%" colspan="3">
								${data.hdsm}
							</td>
						</tr>
					</tbody>
					
				</table>
			</div>
			<div style="margin-top:1px;">
			  <table width="100%" border="0" class="formlist" >
				<thead>
						<tr>
							<th colspan="8">
								<span>���Ա��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_cjpd">
						<tr>
							<th width="10%" style="text-align:left">ѧ��</th>
							<th width="10%" style="text-align:left">����</th>
							<th width="10%" style="text-align:left">�Ա�</th>
							<th width="10%" style="text-align:left">ѧԺ</th>
							<th width="10%" style="text-align:left">רҵ</th>
							<th width="10%" style="text-align:left">�༶</th>
							<th width="20%" style="text-align:left">��/�ɹ����</th>
							<th width="20%" style="text-align:left" >�Ƿ���ѧ��</th>
						</tr>
						<logic:iterate id="s" name="hdkhcjlist">
							<tr>
							  <input type="hidden" name="id" value="${s.id }"/>
							  <input type="hidden" name="xh" value="${s.xh}"/>
								<td>${s.xh}</td>
								<td>${s.xm}</td>
								<td>${s.xb}</td>
								<td>${s.xymc}</td>
								<td>${s.zymc}</td>
								<td>${s.bjmc}</td>
								<td>${s.hjqk}</td>
								<td>
									${s.sfhdxf}
								</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</div>
			<div style="height:33px;">
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
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