<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<html:form action="/gyjc_jcjglr" method="post" styleId="JcjglrForm" onsubmit="return false;">
		<table width="100%" border="0" class="formlist">
			<thead>
			<tr>
				<th colspan="4">
				<span>������Ϣ
				</span>
				</th>
			</tr>
			</thead>
			<tbody>
			    <tr>
					<th>¥��</th>
					<td id="ldmc" width="30%">${qsxx.ldmc}</td>
					<th width="20%">���</th>
					<td id="ch">${qsxx.ch}</td>
				</tr>
				<tr>
					<th width="20%">����</th>
					<td id="qshTd" width="30%">${qsxx.qsh}</td>
					<th width="20%">ѧԺ</th>
					<td id="xymc">${qsxx.xymc}</td>
				</tr>
			</tbody>
			<logic:equal value="1" name="jcmxMap" property="wsjc">
			<thead>
				<tr>
					<th colspan="4">
					<span>�������&nbsp;&nbsp;
					</span>
					<logic:iterate id="i" name="djList">
						<logic:equal value="1" name="i" property="jjlx">
							&nbsp;&nbsp;<font color='blue'>�������ҵȼ���${i.dj}</font>
							<logic:notEmpty name="i" property="xh">
								&nbsp;&nbsp;<font color='blue'>�����˵����${i.xh}</font>
							</logic:notEmpty>
							&nbsp;&nbsp;<font color='blue'>����ˣ�${i.xm}</font>
							&nbsp;&nbsp;<font color='blue'>���ʱ�䣺${i.jcrq}</font>
						</logic:equal>
					</logic:iterate>
					</th>
				</tr>
			</thead>
			  <tbody id="wsjcTbody">
			  <tr>
			  <th colspan="2"><div align="center">��������</div></th>
			  <th colspan="2"><div align="center">Ҫ��</div></th>
			  </tr>
			  <logic:iterate id="i" name="wsjcPfList">
			  		<tr class='jcxmTr'>
			  			<td colspan="2">
			  					${i.fjmc}
			  			</td>
			  			<td colspan="2">
			  					${i.wsqkyq}
			  			</td>
			  		</tr>
			  </logic:iterate>
			  </tbody>
			  </logic:equal>
			  <logic:equal value="2" name="jcmxMap" property="aqjc">
			  <thead>
				<tr>
					<th colspan="4">
					<span>��ȫ���&nbsp;&nbsp;
					</span>
							<logic:iterate id="i" name="djList">
						<logic:equal value="2" name="i" property="jjlx">
							&nbsp;&nbsp;<font color='blue'>�������ҵȼ���${i.dj}</font>
							<logic:notEmpty name="i" property="xh">
								&nbsp;&nbsp;<font color='blue'>�����˵����${i.xh}</font>
							</logic:notEmpty>
							&nbsp;&nbsp;<font color='blue'>����ˣ�${i.xm}</font>
							&nbsp;&nbsp;<font color='blue'>���ʱ�䣺${i.jcrq}</font>
						</logic:equal>
					</logic:iterate>
					</th>
				</tr>
			</thead>
			  <tbody id="aqjcTbody">
			  <tr>
			  <th colspan="2"><div align="center">��Ŀ</div></th>
			  <th colspan="2"><div align="center">Ҫ��</div></th>
			  </tr>
			    <logic:iterate id="i" name="aqjcPfList">
			  		<tr class='jcxmTr'>
			  			<td colspan="2">
			  					${i.fjmc}
			  			</td>
			  			<td colspan="2">
			  					${i.wsqkyq}
			  			</td>
			  		</tr>
			  </logic:iterate>
			  </tbody>
			  </logic:equal>
			  <logic:equal value="3" name="jcmxMap" property="jljc">
			 <thead>
				<tr>
					<th colspan="4">
					<span>���ɼ��&nbsp;&nbsp;
					</span>
					<logic:iterate id="i" name="djList">
						<logic:equal value="3" name="i" property="jjlx">
							&nbsp;&nbsp;<font color='blue'>�������ҵȼ���${i.dj}</font>
							<logic:notEmpty name="i" property="xh">
								&nbsp;&nbsp;<font color='blue'>�����˵����${i.xh}</font>
							</logic:notEmpty>
							&nbsp;&nbsp;<font color='blue'>����ˣ�${i.xm}</font>
							&nbsp;&nbsp;<font color='blue'>���ʱ�䣺${i.jcrq}</font>
						</logic:equal>
					</logic:iterate>
					</th>
					
				</tr>
			</thead>
			  <tbody id="jljcTbody">
			  <tr>
			  <th colspan="2"><div align="center">��Ŀ</div></th>
			  <th colspan="2"><div align="center">Ҫ��</div></th>
			  </tr>
			   <logic:iterate id="i" name="jljcPfList">
			  		<tr class='jcxmTr'>
			  			<td colspan="2">
			  					${i.fjmc}
			  			</td>
			  			<td colspan="2">
			  					${i.wsqkyq}
			  			</td>
			  		</tr>
			  </logic:iterate>
			  </tbody>
			  </logic:equal>
			  <thead>
				<tr>
					<th colspan="4">
					<span>������Ϣ
					</span>
					</th>
				</tr>
			</thead>
			  <tbody>
			  <tr>
							<th>
								������Ϣ
							</th>
							<td colspan="3">
								<html:hidden property="fjid" styleId="fjid" value="${jcmxMap.fjid}" />
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<script type="text/javascript">
								//���ø��� 
								jQuery(function(){
									var gid = jQuery('#fjid').val();
									jQuery.MultiUploader_q({
										gid : gid
										});
								});
							</script>
							</td>
						</tr>
			  </tbody>
			  </table>
				<div style="height:35px"></div>
			    <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" onclick="Close();return false;">
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