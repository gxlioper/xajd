<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xthd/xmsh/js/txhdsh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${data.sqid}&tt="+new Date().getTime());
		});
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/rcsw_txhd_sh">
		<% String xxdm = (String) request.getAttribute("xxdm"); %>
		 <html:hidden property="sqid"/>
		 <html:hidden property="xn"/>
		 <html:hidden property="xq"/>
		 <html:hidden property="xh"/>
		 <html:hidden property="splc" styleId="splc"/>
		 <html:hidden property="shid" styleId="shid"/>
		 <html:hidden property="shzt" styleId="shzt"/>
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp"%>
				<thead>
					<tr>
						<th colspan="4">
							<span>��ѧ��Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							��Ŀ����
						</th>
						<td align="left">
							${xmxx.xmmc}
						</td>
						<th align="right">
							�ʱ��
						</th>
						<td align="left">
							${xmxx.hdkssj}&nbsp;��&nbsp;${xmxx.hdjssj}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							��������
						</th>
						<td align="left">
							${xmxx.shrssx}
						</td>
						<th align="right">
							��ص�
						</th>
						<td align="left">
							${xmxx.hddd}
						</td>
					</tr>
					<% if("13023".equals(xxdm)){ %>
					<tr>
						<th align="right" width="10%">
							����ѧ��
						</th>
						<td align="left">
							${xmxx.syxf}
						</td>
						<th align="right">
						</th>
						<td align="left">
						</td>
					</tr>
					<% } %>
					<tr>
						<th align="right" width="10%">
							�˵��
						</th>
						<td align="left"  colspan="3">
							${xmxx.hdsm}
						</td>
					</tr>
				</tbody>
			<logic:notEqual value="�������" name="shztmc">	
				<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>�����Ϣ</span>
								</th>
							</tr>
						</thead>				
						<tbody>
							<tr>
								<td colspan="4" id="shlccx">
								
								</td>
							</tr>
						
						</tbody>
					
				</table>
			</logic:notEqual>	
		</div>
		<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">
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
			<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
</html>
