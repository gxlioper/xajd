<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/gygl/gzdx/gyglTyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script language="javascript" src="js/xsgzgl/gygl/gzdx/wsjc.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript">
		</script>
	</head>

	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>������� - ���һ������</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/gzdxWsjc">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ end-->
			<!-- ���������� -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>����������</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr style="height: 23px">
						<th align="right" width="20%">
							У��
						</th>
						<td align="left" width="30%">
							${rs.xqmc }
						</td>
						<th align="right" width="20%">
							¥��
						</th>
						<td align="left">
							${rs.ldmc }
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							��������
						</th>
						<td align="left">
							��${rs.cs }��
						<th align="right">
							���Һ�
						</th>
						<td align="left">
							${rs.qsh }
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							����<bean:message key="lable.xb" />
						</th>
						<td align="left">
							${rs.xymc }
						</td>
						<th align="right">
							�����Ա
						</th>
						<td align="left">
							${rs.jcry }
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							<logic:equal name="jczq" value="��">
								����ܴ�
							</logic:equal>
							<logic:equal name="jczq" value="��">
								���ʱ��
							</logic:equal>
						</th>
						<td align="left">
							<logic:equal name="jczq" value="��">
								��${rs.jczc }��
							</logic:equal>
							<logic:equal name="jczq" value="��">
								${rs.jcsj }
							</logic:equal>
						</td>
						<th align="right">
							<logic:equal name="lrxs" value="����">
								<logic:notEqual name="doType" value="view">
									<font color="red">*</font>
								</logic:notEqual>
								������
							</logic:equal>
							<logic:equal name="lrxs" value="�ȼ�">
								�����ȼ�
							</logic:equal>
						</th>
						<td align="left">
							<logic:equal name="doType" value="view">
								<logic:equal name="lrxs" value="����">
									${rs.wsffs }
								</logic:equal>
								<logic:equal name="lrxs" value="�ȼ�">
									${rs.wsfdj }
								</logic:equal>
							</logic:equal>
							<logic:notEqual name="doType" value="view">
								<logic:equal name="lrxs" value="����">
									<html:text name="rs" property="fssx" styleId="fs" 
											maxlength="3" onblur="chMax(this,100);"
											onkeydown="return onlyNum(this,3)"
										 	onmousedown="return onlyNum(this,3)"
											style="width: 70px;ime-mode:disabled"/>
								</logic:equal>
								<logic:equal name="lrxs" value="�ȼ�">
									${rs.wsfdj }
								</logic:equal>
							</logic:notEqual>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							��ע
							<logic:equal name="doType" value="update">
								</br>
								<font color="red">(��¼��250��)</font>
							</logic:equal>
						</th>
						<td align="left" colspan="3">
							<logic:equal name="doType" value="view">
								<html:textarea name="rs" property="bz" styleId="bz" rows="5"
									style="width: 500px" onblur="chLeng(this,250)" readonly="true"/>
							</logic:equal>
							<logic:notEqual name="doType" value="view">
								<html:textarea name="rs" property="bz" styleId="bz" rows="5"
									style="width: 500px" onblur="chLeng(this,250)"/>
							</logic:notEqual>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<logic:notPresent name="rsArrList">
								���������˾�ס
							</logic:notPresent>
							<logic:present name="rsArrList">
								<fieldset>
									<table width="100%" id="rsTable" class="tbstyle">
										<thead>	
											<tr align="center" style="cursor:hand">
												<td>ѧ��</td>
												<td>����</td>
												<td>�Ա�</td>
												<td>Ժϵ</td>
												<td>רҵ</td>
												<td>�༶</td>
												<td>��λ��</td>
											</tr>
										</thead>
										<logic:iterate name="rsArrList" id="s" indexId="index">
											<tr>
												<logic:iterate id="v" name="s">
													<td align="left">
														<bean:write name="v" />
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
									</table>
								</fieldset>
							</logic:present>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="btn">
								<logic:notEqual name="doType" value="view">
									<button type="button" id="buttonSave" onclick="saveUpdate('/xgxt/gzdxWsjc.do?method=fscxUpdate&doType=save&pk='+$('pk').value,'fs')"
										style="width: 80px">
										��	��
									</button>
								</logic:notEqual>
								&nbsp;&nbsp;
								<button type="button" id="buttonClose" onclick="Close();return false;"
									style="width: 80px">
									��	��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
