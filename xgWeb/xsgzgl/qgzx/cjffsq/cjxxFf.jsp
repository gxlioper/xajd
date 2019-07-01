<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/cjffsq/js/cjxxFf.js"></script>
	</head>
	<body>
		<html:form action="/qgzx_cjffsq" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden" name="sxzd" id="sxzd" value="${rs.sxzd }" />
			<input type="hidden" name="sxsz" id="sxsz" value="${rs.sxsz}" />
			<input type="hidden" name="cjbz" id="cjbz" value="${rs.cjbz}" />
			<input type="hidden" name="yf" id="yf" value="${rs.ffny }" />
			<input type="hidden" name="xhs" id="xhs" value="" />
			<input type="hidden" name="gwdms" id="gwdms" value="" />
			<input type="hidden" name="gss" id="gss" value="" />
			<input type="hidden" name="jes" id="jes" value="" />
			<input type="hidden" name="jfhbs" id="jfhbs" value="" />
			<input type="hidden" name="zcs" id="zcs" value="" />
			<input type="hidden" name="bzs" id="bzs" value="" />
			<input type="hidden" name="jcdlgss" id="jcdlgss" value="" />
			<input type="hidden" name="zhdlgss" id="zhdlgss" value="" />
			<input type="hidden" name="khdjs" id="khdjs" value="" />
			<input type="hidden" name="cjsx" id="cjsx" value="" />
			<input type="hidden" id="yffxh" value=""/>
			<input type="hidden" id="yffgw" value="" />
			<input type="hidden" id="xxdm" value="${xxdm}" />
			<input type="hidden" name="cjffr" id="cjffr" value="${rs.cjffr }" />
			<logic:equal value="10351" name="xxdm">
				<input type="hidden" name="pkscjbz"  id="pkscjbz" value="${cs.pkscjbz}" />
				<input type="hidden" name="pkscjzgsx" id="pkscjzgsx" value="${cs.pkscjzgsx}" />
			</logic:equal>
			<input type="hidden" id="csz" value="${csz}" />
			<!-- ��ʾ��Ϣ end-->
			<div style="overflow-x:hidden;overflow-y:auto;">
				<div class="prompt" id="div_help">
					<h3>
						<span>��ʾ��</span>
					</h3>
					<p>
						<span>
						1.����Ľ��Ϊ��ʱ����������ύ�ü�¼��<br/>
						<logic:equal name="xxdm" value="10351">
						2.ƶ������λ���޽��${cs.pkscjzgsx}Ԫ��<br/>
						</logic:equal>
						</span>
					</p>
					<%--
					<a class="close" title="����"
					   onclick="this.parentNode.style.display='none'"></a>
					--%>
				</div>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��λ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_gwxx">
						<tr>
							<th width="16%">
								<font class="red">*</font>ѧ��
								<logic:equal value="xq" name="cs" property="qgzq">
									ѧ��
								</logic:equal>
							</th>
							<td width="34%">
								<html:select name="rs" property="xn" styleId="xn" onchange="getGwdm();getFFny();onShow()" disabled="${rs.dis}" style="width:100px">
									<html:options collection="xnList" labelProperty="xn" property="xn" />
								</html:select>
								<logic:equal value="xq" name="cs" property="qgzq" >
									<html:select property="xq" styleId="xq"  onchange="getGwdm();onShow()" disabled="${rs.dis}">
										<html:options collection="xqList" labelProperty="xqmc" property="xqdm" />
									</html:select>
								</logic:equal>
								<logic:equal value="xn" name="cs" property="qgzq" >
									<input type="hidden" id="xq" name="xq" value="" />
								</logic:equal>
							</td>
							<th width="16%">
								<font class="red">*</font>���˲���
							</th>
							<td width="34%">
								<logic:empty name="rs" property="disQg">
									<html:select name="rs" property="yrbm" styleId="yrbm" onchange="getGwdm();getFFny();onShow()" disabled="${rs.dis}" style="width:200px">
										<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
									</html:select>
								</logic:empty>
								<logic:notEmpty name="rs" property="disQg">
									<html:select name="rs" property="yrbm" styleId="yrbm" onchange="getGwdm();getFFny();onShow()" disabled="true" style="width:200px">
										<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
									</html:select>
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<th>
								�ڸ�״̬
							</th>
							<td>
								<html:select property="zgzt" styleId="zgzt" onchange="onShow()" style="width:200px">
									<option value=''>ȫ��</option>
									<option value='zg' selected="selected">�ڸ�</option>
								</html:select>
							</td>
							<th>
								<font class="red">*</font>��������
							</th>
							<td>
								<html:select name="rs" property="ffny" styleId="ffny" onchange="onShow()" disabled="${rs.dis}" style="width:200px">
									<html:option value="${rs.ffny}">${rs.ffny}</html:option>
									<html:options collection="ffnyList" property="ffny" labelProperty="ffny" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								�Ƿ�¼��
							</th>
							<td>
								<html:select property="sqzt" styleId="sqzt" onchange="onShow()" style="width:200px">
									<option value="">----ȫ��----</option>
									<html:option value="yll">��¼��</html:option>
									<html:option value="wll">δ¼��</html:option>
								</html:select>
							</td>
							<th>
								��λ����
							</th>
							<td>
								<html:select name="rs" property="gwdm" styleId="gwdm" onchange="onShow()" style="width:200px">
									<option value="">----ȫ����λ----</option>
									<html:options collection="gwList" property="gwdm" labelProperty="gwmc" />
								</html:select>
							</td>
							<%--�㽭��ý --%>
							<logic:equal name="xxdm" value="11647">
							<th>
							</th>
							<td>
							</td>
							</logic:equal>
							<%--�㽭��ý end--%>
							<%--ɽ���Ƽ���ѧ --%>
							<logic:equal name="xxdm" value="10424">
							<th>
							</th>
							<td>
							</td>
							</logic:equal>
							<%--ɽ���Ƽ���ѧ end--%>
							<%--���㽭��ý���������ѧԺ��ɽ���Ƽ���ѧ --%>
							<%--<logic:notEqual name="xxdm" value="11647">
								<logic:notEqual name="xxdm" value="12309">
									<logic:notEqual name="xxdm" value="10424">
										<th>
											����׼
										</th>
										<td>
											<font class="red">${rs.cjbz}</font>Ԫ/ʱ
										</td>
									</logic:notEqual>
								</logic:notEqual>
							</logic:notEqual>--%>
							<%--���㽭��ý���������ѧԺ��ɽ���Ƽ���ѧend --%>
						</tr>
					</tbody>
				</table>
				<div style="display: none;">
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
				<!-- ������ʾ����ʼ -->
				
				<div class="main_box">
					<div class="mid_box">
						<div class="title">
							<p>
								<!-- ��ѯ�õ�����������ʾ���� -->
							</p>
						</div>
					</div>
					<h3 class="datetitle_01">
<!--						<span style="float: left;"> ��ѯ���&nbsp;&nbsp;[ֻ�ܷ���&nbsp;&nbsp;<font color="red" id="tsny"></font>  �����Լ�֮ǰ��]</span>-->
					</h3>
					<div id="div_rs"
						style="width:100%;overflow-x:auto;overflow-y:hidden;">
					</div>
				</div>
				<div id="div_detail" style="display:none">
				</div>		
			</div>
			
			<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td>
							<div class="btn">
								<button type="button" onclick="saveCjxxff();return false;">
									�� ��
								</button>
								<button type="button" onclick="iFClose();">
									�� ��
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

