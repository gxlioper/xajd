<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/gygl/wsjc.js"></script>
		<script language="javascript" src="js/pclUtil.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript">
		
		</script>
	</head>
	<body onload="onShow_cssz()">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/commWsjc">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>		
			<div class="tab">		
				<!-- �������û������ -->
				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<th colspan="4">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<tbody>		
						<tr style="">
							<th align="right" width="15%">
								<font color="red">*</font>��������
							</th>
							<td align="left" width="35%">
								<html:hidden name="rs" property="jczq" styleId="zq"/>
								<html:radio name="rs" property="jczq" value="��" onclick="$('zq').value=this.value;showZcTr()"/>��
								&nbsp;&nbsp;
								<html:radio name="rs" property="jczq" value="��" onclick="$('zq').value=this.value;showZcTr()"/>��
							</td>
							<td align="left" width="50%" colspan="2">
								<font color="blue">��ʾ������������Ϊ�ܣ�������¼������������ܴ�Ϊ׼������Ϊ�յ�������������ơ�</font>
							</td>
						</tr>
						<tr id="zgzcTr" style="display: none">
							<th align="right">
								<font color="red">*</font>�ܹ��ܴ�
							</th>
							<td align="left">
								<html:text name="rs" property="zgzc" styleId="zgzc"
									onkeydown="return onlyNum(this,2)"
									onmousedown="return onlyNum(this,2)"
									onblur="chMax(this,52)"
									maxlength="2"
									style="width: 25px;ime-mode:disabled"/>��
							</td>
							<td align="left"colspan="2">
								<font color="blue">��ʾ������Ŀ��ϵ��������¼�����������</font>
							</td>
						</tr>
						<tr id="qsrqTr" style="display: none">
							<th align="right">
								<font color="red">*</font>��ʼ����
							</th>
							<td align="left">
								<html:text name="rs" property="qsrq" styleId="qsrq"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('qsrq','y-mm-dd');"
								/>
							</td>
							<td align="left"colspan="2">
								<font color="blue">��ʾ������Ŀ��ϵ��������¼��ʱ����ǰ�ܴε�ȡ�á�</font>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>����������</span>
							</th>
						</tr>
					</thead>
					<tbody>	
						<tr id="jcfTr" style="display: ">
							<th align="right">
								ѧ��������
							</th>
							<td align="left">
								<html:text name="rs" property="jcf"/>
							</td>
							<td align="left"colspan="2">
								<font color="blue">��ʾ������Ŀ��ϵ��ÿһλѧ��ÿһѧ�ڵĻ��������֡�
								</font>
							</td>
						</tr>	
						<tr style="">
							<th align="right" width="15%">
								¼����ʽ
							</th>
							<td align="left" width="35%">
								<html:hidden name="rs" property="lrxs" styleId="xs"/>
								<html:radio name="rs" property="lrxs" value="����" onclick="$('xs').value=this.value;showGlTr()"/>����
								&nbsp;&nbsp;
								<html:radio name="rs" property="lrxs" value="�ȼ�" onclick="$('xs').value=this.value;showGlTr()"/>�ȼ�
<%--								&nbsp;&nbsp;--%>
<%--								<html:radio name="rs" property="lrxs" value="�۷�" onclick="$('xs').value=this.value;"/>�۷�--%>
							</td>
							<td align="left" width="50%" colspan="2">
								<font color="blue">��ʾ������Ŀ��ϵ�������ֵ�¼����ʽ�������ֻ����������õĵȼ���</font>
							</td>
						</tr>
						
						<tr id="gldjTr" style="display: none">
							<th align="right">
								�Ƿ�����ȼ�
							</th>
							<td align="left">
								<html:hidden name="rs" property="gldj" styleId="dj"/>
								<html:radio name="rs" property="gldj" value="��" onclick="$('dj').value=this.value;showGlTb();"/>��
								&nbsp;&nbsp;
								<html:radio name="rs" property="gldj" value="��" onclick="$('dj').value=this.value;showGlTb();"/>��
								&nbsp;&nbsp;
							</td>
							<td align="left"colspan="2">
								<font color="blue">��ʾ������Ŀ��ϵ��������¼����鿴ʱ���Ƿ���Ҫ��ʾ������ȼ��Ĺ�����
								���磺���95�ֵ����ң������ֵȼ�ΪA��
								</font>
							</td>
						</tr>
						<tr id="glfsTr" style="display: none">
							<th align="right">
								�Ƿ��������
							</th>
							<td align="left">
								<html:hidden name="rs" property="glfs" styleId="fs"/>
								<html:radio name="rs" property="glfs" value="��" onclick="$('fs').value=this.value;showGlTb();"/>��
								&nbsp;&nbsp;
								<html:radio name="rs" property="glfs" value="��" onclick="$('fs').value=this.value;showGlTb();"/>��
								&nbsp;&nbsp;
							</td>
							<td align="left"colspan="2">
								<font color="blue">��ʾ������Ŀ��ϵ��������¼����鿴ʱ���Ƿ���Ҫ��ʾ�ȼ�������Ĺ���������ͳ��ƽ���ֵ����ݡ�
								�����Ƿ���Ҫ����������Ҫ���þ���ĵȼ����ơ�
								</font>
							</td>
						</tr>
					</tbody>
					<!-- ���������ȼ� -->
					<thead id="gldjTh" style="display: none">
						<tr>
							<th colspan="4">
								<span>�ȼ���������</span>
							</th>
						</tr>
					</thead>
					<!-- �ȼ��������� -->
					<thead id="glfsTh" style="display: none">
						<tr>
							<th colspan="4">
								<span>������������</span>
							</th>
						</tr>
					</thead>
					<!-- �ȼ����������� -->
					<thead id="bglfsTh" style="display: none">
						<tr>
							<th colspan="4">
								<span>�ȼ�����</span>
							</th>
						</tr>
					</thead>
					<tbody id="nrTb" style="display: none">
						<tr>
							<td colspan="4">
							
							<input type="button" value="+" onclick="addDj('add')" style="width: 20px"/>
							<input type="button" value="-" onclick="trDel('nr','delRow')" style="width: 20px"/>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;
							<input type="text" name="nrAdd" id="nrAdd" style="width: 20px" onblur="addDj('madd')"/>
							&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;
							<input type="text" name="nrDel" id="nrDel" style="width: 20px" onblur="trDelAll('nr','nrDel')"/>
							&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							
							<table class="formlist" align="center" width="100%" id="tTb">
								<tr>
									<td>
										<div class="mid_box">
											<table align="center" style="width: 100%" id="rsT" class="tbstyle">
												<thead style="height: 23px">
													<!-- ���������ȼ� -->
													<tr id="gldjTopTr" style="display: none">
														<logic:iterate name="gldjTopList" id="gldjNrTitle">
															<td align="center" nowrap="nowrap" style='width:5px'>
																${gldjNrTitle.cn }
															</td>
														</logic:iterate>
													</tr>
													<!-- �ȼ��������� -->
													<tr id="glfsTopTr" style="display: none">
														<logic:iterate name="glfsTopList" id="glfsNrTitle">
															<td align="center" nowrap="nowrap" style='width:5px'>
																${glfsNrTitle.cn }
															</td>
														</logic:iterate>
													</tr>
													<!-- �ȼ����������� -->
													<tr id="bglfsTopTr" style="display: none">
														<logic:iterate name="bglfsTopList" id="bglfsNrTitle">
															<td align="center" nowrap="nowrap" style='width:5px'>
																${bglfsNrTitle.cn }
															</td>
														</logic:iterate>
													</tr>
												</thead>		
												<tbody width="100%" class="tbstyle" id="nr">
												
												</tbody>	
											</table>
										</div>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button onclick="saveCssz()" id="buttonSave">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>