<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript"  src="js/pjpy/pjpyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getWjdcDAO.js'></script>
		<script language="javascript"  src="js/wjdc/wjdc.js"></script>
		<script language="javascript"  src="js/wjdc/wjdcMk.js"></script>
	</head>
	<body onload="">
		<html:form action="/wjdc">
			<!-- ������ -->
			<%@ include file="/wjdc/hiddenValue.jsp"%>
			<!-- ������ end-->
			
			<div class="tab">
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button name="�� ��" onclick="window.close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<td colspan="4">
								<span>������Ϣ</span>
							</td>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th width="15%">
							ѧ��
						</th>
						<td width="35%">
							<html:hidden name="rs" property="xn"/>
							${rs.xn }
						</td>
						<th width="15%">
							ѧ��
						</th>
						<td>
							<html:hidden name="rs" property="xq"/>
							${rs.xqmc }
						</td>
					</tr>
					<tr>
						<th>
							���
						</th>
						<td>
							<html:hidden name="rs" property="nd"/>
							${rs.nd }
						</td>
						<th>
							����ʱ��
						</th>
						<td>
							<html:hidden name="rs" property="jlsj"/>
							${rs.jlsjmc }
						</td>
					</tr>
					</tbody>
					<thead>
						<tr>
							<td colspan="4">
								<span>�ʾ���Ϣ</span>
							</td>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th>
							<font color="red">*</font>�ʾ�����
							<br/>
							<font color="red">(��50��)</font>
						</th>
						<td colspan="3">
							<html:text name="rs" property="wjmc" styleId="wjmc" style="width: 90%" maxlength="50" readonly="true"/>			
						</td>
					</tr>
					<tr>
						<th>
							��ע
							<br/>
							<font color="red">(��500��)</font>
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="bz" rows="5" styleId="bz" readonly="true"
								onblur="chLeng(this,500)" style="width: 90%"/>
						</td>
					</tr>
					</tbody>
					<thead>
						<tr>
							<td colspan="4">
								<span>�ش�����</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table width="100%" class="tbstyle">
									<logic:iterate name="zjlxList" id="lx">
										<thead>
											<tr>
												<td align="center" colspan="2">
													${lx.lxmc}
												</td>
											</tr>
										</thead>
										<!-- ��ѡ�� -->
										<logic:equal name="lx" property="lxdm" value="oneChoose">
											<%@ include file="stxx/pjOneChoose.jsp"%>
										</logic:equal>
										<!-- ��ѡ�� -->
										<logic:equal name="lx" property="lxdm" value="allChoose">
											<%@ include file="stxx/pjAllChoose.jsp"%>
										</logic:equal>
											<!-- �ʴ��� -->
										<logic:equal name="lx" property="lxdm" value="questions">
											<%@ include file="stxx/pjQueChoose.jsp"%>
										</logic:equal>
									</logic:iterate>		
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('�����ɹ���');
						dialogArgumentsQueryChick();
						window.close();
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('����ʧ�ܣ�');
					</script>
				</logic:equal>
			</logic:present>	
		</html:form>
	</body>
</html>
