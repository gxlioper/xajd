<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/rtgl/rtjg/js/rtjg.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form action="/stglRtjg" method="post" styleId="RtjgForm">
			<div style="tab;overflow-x:hidden;overflow-y:hidden;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>���Ż�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="25%">������Ŀ����</th>
							<td width="25%">${stxx.stxmmc}</td>
							<th width="25%"></th>
							<td width="25%"></td>
						</tr>
						<tr>
							<th>�������</th>
							<td>${stxx.stlbmc}</td>
							<th>�ҿ���λ</th>
							<td>${stxx.gkdw}</td>
						</tr>
						<tr>
							<th>��Ŀ���</th>
							<td>${stxx.xmlbmc}</td>
							<th>��Чѧ��</th>
							<td>${stxx.xn }</td>
						</tr>
						<!--  
						<tr>
							<th>������Ч��ʼʱ��</th>
							<td>${stxx.kssj }</td>
							<th>������Ч��ֹʱ��</th>
							<td>${stxx.jssj }</td>
						</tr>
						-->
						<tr>
							<th>���Ÿ�����</th>
							<td>${stxx.stfzrxm }</td>
							<th>������</th>
							<td>${stxx.jtrxm }</td>
						</tr>
						<tr>
							<th>ָ����ʦ</th>
							<td>${stxx.zdlsxm }</td>
							<th>����ʱ��</th>
							<td>${stxx.sqsj }</td>
						</tr>
					</tbody>
					
				</table>
			</div>
			<div style="margin-top:1px;">
			  <table width="100%" border="0" class="formlist" >
				<thead>
						<tr>
							<th colspan="6">
								<span>���ų�Ա��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="15%" style="text-align:left">ѧ��</th>
							<th width="15%" style="text-align:left">����</th>
							<th width="15%" style="text-align:left">�༶</th>
							<th width="15%" style="text-align:left">ѧԺ</th>
							<th width="20%" style="text-align:left">��������</th>
							<th width="20%" style="text-align:left">�س�</th>
						</tr>
						<logic:iterate id="s" name="stcyxx">
							<tr>
								<td>${s.xh}</td>
								<td>${s.xm}</td>
								<td>${s.bjmc}</td>
								<td>${s.xymc}</td>
								<td>${s.sqly}</td>
								<td>${s.tc}</td>
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
								<div class="bz">
								</div>
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