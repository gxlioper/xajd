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
		<script type="text/javascript" src="js/qgzx/gwgl/gwgl.js"></script>
	</head>
	<body>
		<html:form action="/qgzx_gwgl" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden" name="xhs" id="xhs" value="" />
			<input type="hidden" name="sgsjs" id="sgsjs" value="" />
			<input type="hidden" name="sqbhs" id="sqbhs" value="" />
			<input type="hidden" name="xhTal" id="xhTal" value="" />
						
			<input type="hidden"  id="sfsdgwcjsx" value="${jcpz.sfsdgwcjsx}"/>
			<input type="hidden"  id="gwzgcjsx" value="${jcpz.gwzgcjsx}"/>
			<input type="hidden"  id="sfkgggwcjsx" value="${jcpz.sfkgggwcjsx}"/>
			<%--<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			--%><div style="height:450px;overflow-x:hidden;overflow-y:auto;">
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
								${rs.xqrs}(�ڸ�������<font class="red" >${rs.zgrs}</font>)
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
				<div style="height:240px;overflow-y:auto;">
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<logic:empty name="rs" property="zgryHtml">
						<tbody>
							<tr>
								<td>�ø�λ���ڸ���Ա�������˸ڣ�</td>
							</tr>
						</tbody>
					</logic:empty>
					<logic:notEmpty name="rs" property="zgryHtml">
					<thead>
						<tr>
							<td width='10px'><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
							<td width='15%'>ѧ��</td>
							<td width='15%'>����</td>
							<td width='30%'>�༶</td>
							<td width='20%'>�Ƿ�������</td>
							<td width='20%'>Ŀǰ�ڹ���λ��</td>
						</tr>
					</thead>
					<tbody id="tbody_zgryxx">
						${rs.zgryHtml }
					</tbody>
					</logic:notEmpty>
				</table>
				</div>
			</div>
				<table border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<logic:notEmpty name="rs" property="zgryHtml">
									<button type="button" onclick="tgRyxx()">
										�˸�
									</button>
									</logic:notEmpty>
									<button type="button" onclick="refreshParentTg();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			
			<%@ include file="/comm/other/tsxx.jsp"%>
			<div id="tempDiv" style="display: none">
				<table align="center" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>��λ�˸�</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								�˸���Ա
							</th>
							<td>
								<font id="tgry"></font>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>�˸�ԭ��
							</th>
							<td>
								<textarea name='tgyy' id="tgyy" style="word-break:break-all;width:97%" onblur="chLeng(this,1000);"
									rows='5'></textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" name="����" onclick="bcTgryxx();">
										����
									</button>
									<button type="button" name="ȡ��" onclick="closeWindown();return false;">
										ȡ ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<div id="xszgxxDiv" style="display: none;">
				<%@ include file="/xsgzgl/qgzx/gwgl/ryxxCk.jsp"%>
			</div>
		</html:form>
	</body>
</html>

