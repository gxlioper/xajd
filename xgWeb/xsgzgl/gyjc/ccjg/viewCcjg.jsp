<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	</head>
	<body>
		<html:form action="/gyjc_ccjgcx" method="post" styleId="ccjgForm" onsubmit="return false;">
		<table width="100%" border="0" class="formlist">
			<thead>
			<tr>
				<th colspan="4">
				<span>������Ϣ</span>
				</th>
			</tr>
			</thead>
			<tbody>
			    <tr>
					<th>¥��</th>
					<td id="ldmc" width="30%">${model.ldmc}</td>
					<th width="20%">���</th>
					<td id="ch">${model.ch}</td>
				</tr>
				<tr>
					<th width="20%">����</th>
					<td id="qshTd" width="30%">${model.qsh}</td>
					<th width="20%">ѧԺ</th>
					<td id="xymc">${model.xymc}</td>
				</tr>
			</tbody>
			<thead>
				<tr>
					<th colspan="4">
					<span>�������
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
			  <th><div align="center">��������</div></th>
			  <th colspan="3"><div align="center">Ҫ��</div></th>
			 
			  </tr>
			  <logic:iterate id="i" name="ccjgList" indexId="index01">
					<logic:equal name="i" property="jjlx" value="1">
					<tr>
						<td>
						${i.mc}
						</td>
						<td class="pfTd" colspan='3'>
						${i.pfmc}
						</td>
					</tr>
					</logic:equal>
				</logic:iterate>
			  </tbody>
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
			  <th><div align="center">��Ŀ</div></th>
			  <th colspan="3"><div align="center">Ҫ��</div></th>
			  </tr>
			  <logic:iterate id="i" name="ccjgList" indexId="index01">
					<logic:equal name="i" property="jjlx" value="2">
					<tr>
						<td>
						${i.mc}
						</td>
						<td class="pfTd" colspan='3'>
						${i.pfmc}
						</td>
					</tr>
					</logic:equal>
				</logic:iterate>
			  </tbody>
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
			  <th><div align="center">��Ŀ</div></th>
			  <th colspan="3"><div align="center">Ҫ��</div></th>
			  </tr>
			  <logic:iterate id="i" name="ccjgList" indexId="index01">
					<logic:equal name="i" property="jjlx" value="3">
					<tr>
						<td>
						${i.mc}
						</td>
						<td class="pfTd" colspan='3'>
						${i.pfmc}
						</td>
					</tr>
					</logic:equal>
				</logic:iterate>
			  </tbody>
			  <thead>
				<tr>
					<th colspan="4">
					<span>������Ϣ
					</span>
					</th>
				</tr>
			</thead>
			  <tbody>
			  <tr><th>�������
			  </th>
			  <td colspan="3">
				${model.jcrq}
			</td>
			  </tr>
			  <tr>
							<th align="right" width="10%">
								������Ϣ
							</th>
							<td colspan="3">
							<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<html:hidden property="fjid" styleId="fjid"/>
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