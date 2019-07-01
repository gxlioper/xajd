<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/zzkff/js/zzkff.js"></script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/xszz_zzkff">
		<input type="hidden" id="xxdm" value="${xxdm}"/>
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/selectStudent.jsp" %>
				<thead>
						<tr>
							<th colspan="4">
								<span>���������Ϣ</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>ѧ��
						</th>
						<td align="left">
							<html:select property="xn" styleId="xn" disabled="false" 
									style="width:125px;">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
							</html:select>
						</td>
						<th align="right">
							<span class="red">*</span>ѧ��
						</th>
						<td align="left">
							<html:select property="xq" styleId="xq" disabled="false" 
									style="width:125px;">
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>��Ŀ����
						</th>
						<td align="left">
							<html:text property="xmmc" styleId="xmmc" style="width:120px;" maxlength="100"></html:text>
						</td>
						<th align="right">
							<span class="red">*</span>���
						</th>
						<td align="left">
							<html:text property="je" styleId="je" style="width:120px;" maxlength="10" ></html:text>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>��������״̬
						</th>
						<td colspan="3">
							<html:text property="wsyhzt" styleId="wsyhzt" style="width:60%" maxlength="200"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>���з�����Ϣ
						</th>
						<td colspan="3">
							<html:text property="yhfkxx" styleId="yhfkxx" style="width:60%" maxlength="200"></html:text>
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
								<button type="button"  onclick="save('xszz_zzkff.do?method=zzkffZj&type=save','xh-xn-xq-xmmc-je-wsyhzt-yhfkxx');return false;" id="buttonSave">
									�� ��
								</button>
								<button type="button"  onclick="iFClose();" id="buttonClose">
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
