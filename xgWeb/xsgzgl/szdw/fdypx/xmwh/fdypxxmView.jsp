<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	</head>
	<body style="width:100%">
		<html:form action="/rcsw_xsxxgl" method="post" styleId="demoForm">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�鿴����Ա��ѵ��Ŀ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							
							<th width="16%">
								��Ŀ����
							</th>
							<td width="34%">
								${model.xmmc}
							</td>
							<th width="16%">
								��ѵ�ص�
							</th>
							<td width="34%" >
								${model.pxdd}
							</td>
						</tr>
						
						<tr>
						<logic:equal value="1"  name="view_type">
							<th width="16%">
								���뿪��
							</th>
							<td width="34%">
								 <logic:notEmpty property="sqkg" name="model">
									  <logic:equal value="1" property="sqkg" name="model">
											��
										</logic:equal>
										<logic:equal value="0" property="sqkg" name="model">
											��
										</logic:equal>
								 </logic:notEmpty>
									
								<logic:empty property="sqkg" name="model">
									δ����
								</logic:empty>
							</td>
							<th width="16%">
								����ʱ��
							</th>
							<td width="34%" >
								${model.sqkssj}��
								${model.sqjssj}
							</td>
						</logic:equal>
						</tr>
						
						<tr>
							<th width="16%">
								��ѵʱ��
							</th>
							<td width="34%">
								${model.pxsj}
							</td>
							<th width="16%">
								<font color="red">*</font>��ѵѧʱ
							</th>
							<td width="34%">
								${model.pxxs}
							</td>

						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>��֯����
							</th>
							<td width="34%" colspan="3">
								<html:select property="bmdm" styleId="bmdm" name="model" disabled="true">
									<html:options collection="bmList" property="bmdm" labelProperty="bmmc"></html:options>
								</html:select>
							</td>
						</tr>
						
						<tr>
							<th width="16%">
								��ѵ���
							</th>
							<td width="34%" colspan="3">
								${model.pxjj}
							</td>
						</tr>
						
						
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" type="button" id= "but_close" onclick="iFClose();">
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

