<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script>
		jQuery(document).ready(function(){
			var sfsdgwcjsx=jQuery("#sfsdgwcjsx").val();
			// �������� ���õ�н������
			var gwzgcjsx=jQuery("#gwzgcjsx").val();
			var sfkgggwcjsx=jQuery("#sfkgggwcjsx").val();
			// ��λ���ó������
			var gwcjsx=jQuery("#gwcjsx").val();
			// ����˸�λδ����
			if(gwcjsx==""){
				jQuery("#gwcjsx").val(gwzgcjsx);
				jQuery("#gwcjsxh").text(gwzgcjsx);
			}
			if("no"==sfsdgwcjsx){
				jQuery("#gwcjsxTr").hide();
			}else{
				jQuery("#gwcjsxTr").show();
				jQuery("#gwcjsx").hide();
				jQuery("#gwcjsxh").show();
				jQuery("#sxcolor").hide();
			}
		});
		//��ʾ�˸���Ϣ
		function showTgxsxx(obj){
			jQuery(obj).parent().parent().hide();
			jQuery("#div_tgxsxx").show();
		}

		//�°�鿴������
		function zxsxxView(xh) {
			showDialog("ѧ����Ϣ��ѯ", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh
					+ "&xs");
		}
		</script>
	</head>
	<body>
	
		<html:form action="/qgzx_gwgl" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			
						
			<input type="hidden"  id="sfsdgwcjsx" value="${jcpz.sfsdgwcjsx}"/>
			<input type="hidden"  id="gwzgcjsx" value="${jcpz.gwzgcjsx}"/>
			<input type="hidden"  id="sfkgggwcjsx" value="${jcpz.sfkgggwcjsx}"/>
			<div style="height:450px;overflow-x:hidden;overflow-y:auto;padding-right:3px;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��λ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								ѧ��
							</th>
							<td width="34%">
								${rs.xn}
							</td>
							<th width="16%">
								���˲���
							</th>
							
							<td width="34%">
								${rs.yrdwmc}
							</td>
						</tr>
						<tr>
							<th width="16%">
								��λ����
							</th>
							
							<td width="34%">
								${rs.gwmc}
							</td>
							<th width="16%">
								��λ����
							</th>
							
							<td width="34%">
								${rs.gwxzmc}
							</td>
						</tr>
						<tr>
							<th width="16%">
								��������
							</th>
							<td width="34%">
								${rs.xqrs}(�ڸ�������<font color="red" >${rs.zgrs}</font>)
							</td>
							<th width="16%">
								��������
							</th>
							<td width="34%">
								${rs.knsrs}
							</td>
						</tr>
						<logic:equal value="10052" name="xxdm">
						<tr>
							<th width="16%">
								��λ���
							</th>
							<td width="34%" colspan="6">
								${rs.gwxh}
							</td>
						</tr>
						</logic:equal>
						<tr id="gwcjsxTr">
							<th width="16%">
								<font id="sxcolor" color="red">*</font>��λ�������
							</th>
							<td width="34%" colspan="6">
								<html:text  name="rs" property="gwcjsx" styleId="gwcjsx" size="10" maxlength="7" onblur="checkInt(this)"></html:text>
								<span id="gwcjsxh">${rs.gwcjsx}</span>
								<span>Ԫ/��  &nbsp;&nbsp;(�ø�λÿ��ÿ�³������)</span>
							</td>
						</tr>
						<tr>
							<th align="right" >
								��λ����
							</th>
							<td colspan="3" style="word-break:break-all;width:100%">
								${rs.gwms}
							</td>
						</tr>
						<tr>
							<th align="right" >
								��λ��ԱҪ��
							</th>
							<td colspan="3" style="word-break:break-all;width:100%">
								${rs.gwryyq}
							</td>
							
						</tr>
						<tr>
							<th align="right" >
								��λԤ����ԱЧ��
							</th>
							<td colspan="3" style="word-break:break-all;width:100%">
								${rs.gwyqryxg}
							</td>
						</tr>
						<tr>
							<th align="right" >
								��ע
							</th>
							<td colspan="3" style="word-break:break-all;width:100%">
								${rs.bz}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�ڸ�ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
				</table>
				<div style="height:200px;overflow-y:auto;">
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<logic:empty name="rs" property="zgryHtml">
					<tbody>
						<tr>
							<td>�ø�λ���ڸ���Ա��</td>
						</tr>
						<tr>
							<td>
								<a href="#" onclick="showTgxsxx(this);return false;">�鿴�˸�ѧ����Ϣ...</a>
							</td>
						</tr>
					</tbody>
					</logic:empty>
					<logic:notEmpty name="rs" property="zgryHtml">
					<thead>
						<tr>
							<td width='10px'>�к�</td>
							<td width='15%'>ѧ��</td>
							<td width='15%'>����</td>
							<td width='30%'>�༶</td>
							<td width='20%'>�Ƿ�������</td>
							<td width='20%'>�ϸ�����</td>
						</tr>
					</thead>
					<tbody id="tbody_zgryxx">
						${rs.zgryHtml }
						<tr>
							<td colspan="6">
								<a href="#" onclick="showTgxsxx(this);return false;">�鿴�˸�ѧ����Ϣ...</a>
							</td>
						</tr>
					</tbody>
					</logic:notEmpty>
				</table>
				<div id="div_tgxsxx" style="display: none;">
					<table border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>�˸�ѧ����Ϣ</span>
								</th>
							</tr>
						</thead>
					</table>
					<table width="100%" border="0" class="datelist" style="margin:2px auto;">
						<logic:empty name="rs" property="tgryHtml">
						<tbody>
							<tr>
								<td colspan="4">�ø�λ���˸���Ա��</td>
							</tr>
						</tbody>
						</logic:empty>
						<logic:notEmpty name="rs" property="tgryHtml">
						<thead>
							<tr>
								<td width='10px'>�к�</td>
								<td width='15%'>ѧ��</td>
								<td width='15%'>����</td>
								<td width='25%'>�༶</td>
								<td width='15%'>�Ƿ�ƶ����</td>
								<td width='15%'>�ϸ�����</td>
								<td width='15%'>�˸�����</td>
							</tr>
						</thead>
						<tbody id="tbody_tgryxx">
							${rs.tgryHtml }
						</tbody>
						</logic:notEmpty>
					</table>
					</div>
				</div>
			</div>
				<table border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="8">
							
								<div class="btn">
									<button type="button" onclick="Close();return false;">
										�� ��
									</button>
								</div>
							
							</td>
						</tr>
					</tfoot>
				</table>
			
		</html:form>
	</body>
</html>

