<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/sqsh/js/bdpz.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	</head>
	<body>
		<html:form action="/bzjl_sqsh" method="post" styleId="sqshForm">
			<html:hidden property="xh" />
			<html:hidden property="xmdm" />
		
			<div style='tab;width:100%; overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
						<%@ include file="/xsgzgl/xpjpy/wdpj/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>���뽱��</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								��������
							</th colspan="3">
							<td>
								<bean:write property="xn" name="xpjSqshModel"/>&nbsp;<bean:write property="xqmc" name="xpjSqshModel"/>
							</td>
						</tr>
						<tr>
							<th>
								��Ŀ����
							</th>
							<td>
								${xmwhModel.xmmc }
							</td>
							<th>
								��Ŀ���
							</th>
							<td>
								${xmwhModel.xmje }
							</td>
						</tr>
						<logic:equal value="	10279" name="xxdm">
						<tr>
							<th>
								��������
							</th>
							<td colspan="3">
								${mkxxForm.ylzd1 }
							</td>
						</tr>
						<tr>
							<th>
								����ʱ��
							</th>
							<td colspan="3">
								${mkxxForm.ylzd3}
							</td>
						</tr>
						<tr>
							<th>
								���쵥λ
							</th>
							<td colspan="3">
								${mkxxForm.ylzd4 }
							</td>
						</tr>
						</logic:equal>
						<logic:equal value="10355" name="xxdm">
							<logic:equal value="1" name="hjjgxskg">
								<tr id="hjtr">
								<th>����Ϣ</th>
								<td colspan="3">
									<table width="100%">
									<thead>
										<tr>
											<td width="30%">������</td>
											<td width="15%">��ʱ��</td>
											<td width="55%">�佱��λ</td>
										</tr>
									</thead>
									<tbody id="hjxx">
										<logic:iterate id="i" name="hjjgList">
											<tr>
												<td>${i.hjmc}</td>
												<td>${i.hjsj}</td>
												<td>${i.fjdw}</td>
											</tr>									
										</logic:iterate>
									</tbody>
								</table>
								</td>
							</tr>
							</logic:equal>
						</logic:equal>
						<tr>
							<th>
								������Ϣ
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden property="ylzd5" styleId="fjid"/>
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
						<%--����ҽҩ�ߵ�ר�Ƹ��Ի��ֶ�--%>
						<logic:equal value="70002" name="xxdm">
						<tr>
						   <th>
								���ܺν���
						   </th>
						   <td colspan="3" style="word-break:break-all;">
						   	<bean:write property="djjl" name="xpjSqshModel" filter="false"/>
						   </td>
						</tr>
						</logic:equal>
						<tr>
							<th>
								��������
							</th>
							<td colspan="3" style="word-break:break-all;">
								<bean:write property="sqly" name="xpjSqshModel" filter="false"/>
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td colspan="3" id="checkTd">
								<logic:notEmpty name="checkResult">
									<logic:iterate id="check" name="checkResult" indexId="i">
										<logic:equal value="true" name="check" property="result">
											<img src='images/ico_38.gif' title='��������'/> ${i+1 }��${check.sqts }<br/>
										</logic:equal>
										<logic:equal value="false" name="check" property="result">
											<img src='images/ico_39.gif' name='faidImg' title='����������'/> ${i+1 }��${check.sqts }<br/>
										</logic:equal>
									</logic:iterate>
								</logic:notEmpty>
								<logic:empty name="checkResult">
									<font color='green'>����������</font>
								</logic:empty>								
							</td>
						</tr>
					</tbody>
					<logic:equal name="SFBJPY_Y" value="true">
					<thead>
						<tr>
							<th colspan="4">
								<span>�༶������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								����С���Ա
							</th>
							<td colspan="3">
								${mkxxForm.bjpyxzcyxms }
							</td>
						</tr>
						<tr>
							<th>
								����С�����
							</th>
							<td colspan="3">
								${mkxxForm.bjpyxzdbxms }
							</td>
						</tr>
						<tr>
							<th>
								������
							</th>
							<td colspan="3">
								${mkxxForm.bjpyjgshztmc }
							</td>
						</tr>
						<tr>
							<th>
								�����ʱ��
							</th>
							<td colspan="3">
								${mkxxForm.bjpyjgpyhsj }
							</td>
						</tr>
						<tr>
							<th>
								�����ص�
							</th>
							<td colspan="3">
								${mkxxForm.bjpyjgpyhdd }
							</td>
						</tr>
						<tr>
							<th>
								�������
							</th>
							<td colspan="3" style="word-break: break-all;">
								${mkxxForm.bjpyjgpyyj }
							</td>
						</tr>
					</tbody>
					</logic:equal>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table width="99%" style="text-align: center;">
									<tr>
										<th style="text-align: center;">���</th>
										<th style="text-align: center;">��˸�λ</th>
										<th style="text-align: center;">�����</th>
										<th style="text-align: center;">��˽��</th>
										<th style="text-align: center;">���ʱ��</th>
										<th style="text-align: center;" width="20%">������</th>
										<th style="text-align: center;">���/��������</th>
									</tr>
									<logic:present name="spjlList">
										<logic:iterate id="s" name="spjlList" indexId="i">
											<tr>
												<td>${i+1 }</td>
												<td>${s.gwmc }</td>
												<td>${s.xm }</td>
												<td>${s.shztmc }</td>
												<td>${s.shsj }</td>
												<td>${s.shyj }</td>
												<td>${s.xmmc }</td>
											</tr>
										</logic:iterate>
										<logic:empty name="spjlList">
											<tr>
												<td colspan="7">δ�ҵ��κμ�¼��</td>
											</tr>
										</logic:empty>
									</logic:present>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 30px"></div>
			<div class='tab'>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" type="button" onclick="iFClose();">
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

