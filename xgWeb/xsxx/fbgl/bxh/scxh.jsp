<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsxx/fbgl/bxh/js/bxh.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/gzsd/gzyl.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
			jQuery(function($){
			});
		</script>
	</head>
	<body>
		<div>
			<html:form action="/fbglbbgl.do?method=add&type=query">
				<%@ include file="/comm/hiddenValue.jsp"%>
				<input id="pk" value="${pk}" type="hidden"/>
				<input id="bxhzt" value="${bxhzt}" type="hidden"/>
				<div style="" id="div_help" class="prompt">
					<h3>
						<span>��ʾ��</span>
					</h3>
					<p>
						<span>	��ѧ��ʱ����ѧ��δ�ְ࣬���ܱ�ѧ�š�
								<br />�ѱ�ѧ�ŵ�ѧ�Ž�������
						</span>
					</p>
				</div>
				<table width="100%" border="0" class="formlist">
					<thead>
							<tr>
								<th colspan="2">
									<span>����ѧ��</span>
								</th>
							</tr>
					</thead>
					<tr>
						<th align="right">
							<font color="red">*</font>ѡ���ѧ�Ź���
						</th>
						<td align="left">
							<html:select property="pzgzid" styleId="pzgzid" disabled="false"
								style="width:125px;">
								<html:options collection="pzgzList" property="pzgzid"
									labelProperty="pzgzmc" />
							</html:select>
							<span style="margin-left: 100px;">
								<a href="#" onclick="ckgzxx();return false;" style="text-decoration: underline;color: blue;">��ѧ�Ź���鿴</a>
							</span>
						</td>
					</tr><%--
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>��ѡ��ѧ����Χ
						</th>
						<td align="left" style="color: red;">
							${zysl}
						</td>
					</tr>
					--%><tr>
						<th align="right">
							��ǰѧ������
						</th>
						<td align="left" id="xszs">
							${xszs}
						</td>
					</tr>
					<tr>
						<th align="right" >
							�ѱ�ѧ��
						</th>
						<td align="left" id="ybxh">
							${ybxh}
						</td>
					</tr>
					<tr>
						<th align="right">
							δ��ѧ��
						</th>
						<td align="left" id="wbxh">
							${wbxh}
						</td>
					</tr>
				</table>
			</html:form>
			<%@include file="/xsgzgl/comm/browser/progressBar.jsp"%>
		</div>
		<table width="100%" border="0" class="formlist"
			style="position: fixed; _position: absolute; bottom: 0;">
			<tfoot>
				<tr>
					<td colspan="2">
						<div class="bz">
							"
							<span class="red">*</span>"Ϊ������
						</div>
						<div class="btn">
							<button id="buttonSave"
								onclick="scxhSave();return false;"
								type="button">
								����
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button id="buttonClose" onclick="iFClose();" type="button">
								�� ��
							</button>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</body>
</html>
