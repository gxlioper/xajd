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
			if("10704"==jQuery("#xxdm").val()&&'${rs.gwxzmc}'=="�̶���"){
			jQuery("#gwcjsxTr").hide();
			jQuery("#gdgcjbzTr").show();
		}
		});
		//��ʾ�˸���Ϣ
		function showTgxsxx(obj){
			jQuery(obj).parent().parent().hide();
			jQuery("#div_tgxsxx").show();
		}

		//�°�鿴������
		function zxsxxView(xh) {
			showDialog("ѧ����Ϣ��ѯ", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh);
		}
		</script>
	</head>
	<body>
	
		<html:form action="/qgzx_gwglnew" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden"  id="xxdm" value="${xxdm}"/>
						
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
								���˲���
							</th>
							<td width="34%" >
								<html:hidden property="yrdwdm" styleId="yrbm"/>
								${rs.yrdwmc}
							</td>
							<th width="16%">
								��ϵ��
							</th>
							<td width="34%">
								<input type="hidden" property="lxr" id="lxr" />
								${rs.lxr}
							</td>
						</tr>
						<tr>
							<th width="16%">
								�걨ʱ��
							</th>
							<td width="34%" >
								<input type="hidden" id="sqsj" name="sqsj" />
								${rs.sqsj }
							</td>
							<th width="16%">
								��ϵ�绰
							</th>
							<td width="34%">
								<input type="hidden" id="lxPhone" name="lxPhone" />
								${rs.lxphone}
							</td>
						</tr>
					<thead>
							<tr>
								<th colspan="5">
									<span>��λ������Ϣ
									</span>
								</th>
							</tr>
					</thead>
						<tr>
							<th width="16%">
								��λ����
							</th>
							
							<td width="34%">
								<html:hidden name="rs" property="gwmc" styleId="gwmc" value="${rs.gwmc}"/>
								${rs.gwmc}
							</td>
							<th width="16%">
								��������
							</th>
							<td width="34%">
								<html:hidden name="rs" property="xqrs" styleId="xqrs" value="${rs.xqrs}"/>
								${rs.xqrs}
							</td>
						</tr>
						<tr>
							<th width="16%">
								��λ����
							</th>
							
							<td width="34%">
								<html:hidden name="rs" property="gwxzdm" styleId="gwxzdm" value="${rs.gwxzmc}"/>
								${rs.gwxzmc}
							</td>
							<th width="16%">
								��������
							</th>
							<td width="34%">
								<html:hidden name="rs" property="knsrs" styleId="knsrs" value="${rs.knsrs}"/>
								${rs.knsrs}
							</td>
						</tr>
						<tr>
								<th>��λ�����</th>
								<td width="34%">
									<html:hidden name="rs" property="gwshr" styleId="gwshr" value="${rs.gwshr}"/>
									${rs.gwshr}
								</td>
								<th>��λ���������</th>
								<td width="34%">
									<html:hidden name="rs" property="gwshrxm" styleId="gwshrxm" value="${rs.gwshrxm}"/>
									${rs.gwshrxm}
								</td>
						</tr>
						<tr>
								<th>��λҪ��</th>
								<td colspan="3">
									<html:hidden name="rs" property="gwryyq" styleId="gwryyq" />
									${rs.gwryyq}
								</td>
						</tr>
						<tr>
								<th align="right" >
									��������
								</th>
								<td colspan="3">
									<html:hidden name="rs" property="gwms" styleId="gwms" value="${rs.gwms}"/>
									${rs.gwms}
								</td>
						</tr>
						<logic:notEmpty name="rs" property="xn" >
							<tr>
								<th width="16%">
									�ڸ����
								</th>
								<td colspan="3">
									<input type="hidden" id="xn" name="xn" />
									${rs.xn}${rs.xqmc}
								</td>
							</tr>
							<tr>
								<th width="16%">��Чʱ����</th>
								<td width="34%">
									${rs.yxsszmc}
								</td>
								<th width= 16%>�Ƿ��ܸ�λ����������</th>
								<td width="34%">
									${rs.sfsgwsqsxzmc}
								</td>
							</tr>
								<th width="16%">��λ��ʼ����</th>
								<td width="34%">
									${rs.gwkssj}
								</td>
								<th width= 16%>��λ��������</th>
								<td width="34%">
									${rs.gwjssj}
								</td>
							</tr>
						</logic:notEmpty>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�ڸ�ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
				</table>
				<!-- <div style="height:250px;overflow-y:auto;"> -->
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
							<td width='25%'>�༶</td>
							<td width='12%'>�Ƿ�������</td>
							<td width='10%'>�Ƿ���У</td>
							<logic:equal value="12036" name='xxdm'>
							<td width='10%'>�ϸ��·�</td>
							</logic:equal>
							<logic:notEqual value="12036" name='xxdm'>
							<td width='10%'>�ϸ�����</td>
							</logic:notEqual>
						</tr>
					</thead>
					<tbody id="tbody_zgryxx">
						${rs.zgryHtml }
						<tr>
							<td colspan="7">
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
								<td width='15%'>�Ƿ�������</td>
								<td width='15%'>�Ƿ���У</td>
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
				<!--</div>-->
			</div>
				<table border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="8">
							
								<div class="btn">
									<button type="button" onclick="iFClose();">
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

