<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
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
								<span>ͳ�Ʒ�Χ(���޷�Χ�Ļ�Ϊͳ��ȫУ)</span>
							</td>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th width="15%">
							�꼶
						</th>
						<td width="35%">
							<html:select property="nj" style="" onchange="" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj" />
							</html:select>
						</td>
						<th width="15%">
							<bean:message key="lable.xsgzyxpzxy" />��
						</th>
						<td>
							<html:hidden name="rs" property="xq"/>
							<html:select property="xydm" style="" styleId="xy" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm" labelProperty="xymc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							רҵ
						</th>
						<td>
							<html:select property="zydm" style="" styleId="zy" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm" labelProperty="zymc" />
							</html:select>
						</td>
						<th>
							�༶
						</th>
						<td>
							<html:select property="bjdm" style="" styleId="bj" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							�Ա�
						</th>
						<td>
							<html:select property="xb" style="" styleId="xb" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xbList" property="en" labelProperty="cn" />
							</html:select>
						</td>
						<th>
							������ò
						</th>
						<td align="left">
							<html:select property="zzmm" style="" styleId="zzmm" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc" />
							</html:select>
						</td>
					</tr>
					</tbody>
					<thead>
						<tr>
							<td colspan="4">
								<span>������Ϣ</span>
							</td>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th>
							ѧ��
						</th>
						<td>
							<html:hidden name="rs" property="xn"/>
							${rs.xn }
						</td>
						<th>
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
								<span>ͳ����Ϣ��ѡ���˴�/�ܴ��������</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>	
							<td colspan="4">
								<table width="100%" class="tbstyle">
									<logic:iterate name="zjlxList" id="lx">
										<logic:notEqual name="lx" property="lxdm" value="questions">
											<thead>
												<tr>
													<td align="center" colspan="2">
														${lx.lxmc}
													</td>
												</tr>
											</thead>
											<!-- ��ѡ�� -->
											<logic:equal name="lx" property="lxdm" value="oneChoose">
												<%@ include file="stxx/tjOneChoose.jsp"%>
											</logic:equal>
											<!-- ��ѡ�� -->
											<logic:equal name="lx" property="lxdm" value="allChoose">
												<%@ include file="stxx/tjAllChoose.jsp"%>
											</logic:equal>
										</logic:notEqual>
									</logic:iterate>		
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		<!-- ��ʾ��Ϣ -->
		<%@ include file="other/tsxx.jsp"%>
		<!-- ��ʾ��Ϣ end-->
		</html:form>
	</body>
</html>
