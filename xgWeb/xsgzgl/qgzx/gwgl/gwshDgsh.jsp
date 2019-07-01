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
		<script type="text/javascript" src="js/qgzx/gwgl/gwsh.js" defer="defer"></script>
	</head>
	<body>
		<html:form action="/qgzx_gwgl" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden" name="xn" id ="xn" value ="${rs.xn}"/>
			<input type="hidden" name="yrdwdm" id ="yrdwdm" value ="${rs.yrdwdm}"/>
			<input type="hidden"  id="sfsdgwcjsx" value="${jcpz.sfsdgwcjsx}"/>
			<input type="hidden"  id="gwzgcjsx" value="${jcpz.gwzgcjsx}"/>
			<input type="hidden"  id="sfkgggwcjsx" value="${jcpz.sfkgggwcjsx}"/>
			<%--<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			--%><div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<logic:equal name="doType" value="update">
						<tr>
							<th colspan="8">
								<span>��λ�޸�</span>
							</th>
						</tr>
						</logic:equal>
						<logic:notEqual name="doType" value="update">
						<tr>
							<th colspan="8">
								<span>��λ������Ϣ</span>
							</th>
						</tr>
						</logic:notEqual>
					</thead>
					
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								ѧ��
							</th>
							<td width="34%" colspan="3" >
								<html:hidden name="rs" property="xn" styleId="xn"/>
								${rs.xn}
							</td>
							<th width="16%">
								���˲���
							</th>
							
							<td width="34%" colspan="3" >
								<html:hidden name="rs" property="yrdwdm" styleId="yrbm"/>
								${rs.yrdwmc}
							</td>
						</tr>
							<tr>
							<th width="16%">
								��λ����
							</th>
							
							<td width="34%" colspan="3" >
								${rs.gwmc }
							</td>
							<th width="16%">
								��λ����
							</th>
							<td width="34%" colspan="3" >
								${rs.gwxzmc }
							</td>
						</tr>
						<tr>
							<th width="16%">
								��������
							</th>
							<td width="34%" colspan="3" >
								${rs.xqrs }(��)
							</td>
							<th width="16%">
								��������
							</th>
							
							<td width="34%" colspan="3">
								${rs.knsrs }(��)
							</td>
						</tr>
						<tr id="gwcjsxTr">
							<th width="16%">
								<font id="sxcolor" color="red">*</font>��λ�������
							</th>
							<td colspan="7">
								<html:text  name="rs" property="gwcjsx" styleId="gwcjsx" size="10" maxlength="7" onblur="checkInt(this)"></html:text>
								<span id="gwcjsxh">${rs.gwcjsx}</span>
								<span>Ԫ/��  &nbsp;&nbsp;(�ø�λÿ��ÿ�³������)</span>
							</td>
						</tr>
						<tr>
							<th width="16%">
								������
							</th>
							<td width="34%" colspan="3" >
								${rs.sqr }
							</td>
							<th width="16%">
								����ʱ��
							</th>
							
							<td width="34%" colspan="3">
								${rs.sqsj }
							</td>
						</tr>
						<tr style="height:40px">
							<th align="right" >
								��λ����
							</th>
							<td colspan="7" style="word-break:break-all;width:97%">
								${rs.gwms }
							</td>
						</tr>
						<tr style="height:40px">
							<th align="right" >
								��λ��ԱҪ��
							</th>
							<td colspan="7" style="word-break:break-all;width:97%">
								${rs.gwryyq }
							</td>
							
						</tr>
						<tr style="height:40px">
							<th align="right" >
								��λԤ����ԱЧ��
							</th>
							<td colspan="7" style="word-break:break-all;width:97%">
								${rs.gwyqryxg }
							</td>
							
						</tr>
						<tr style="height:40px">
							<th align="right" >
								��ע
							</th>
							<td colspan="7" style="word-break:break-all;width:97%">
								${rs.bz }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="8">
								<span>��λ�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr style="height:40px">
							<th align="right" >
								<span class="red">*</span>������<br/><font color="red">(��1000��)</font>
							</th>
							<td colspan="7">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=cdgl&id=shyj" />
								
								<html:textarea name="rs" property='shyj' styleId="shyj" style="word-break:break-all;width:97%;margin-top:5px" onblur="chLengs(this,1000);"
									rows='4'   />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 30px">
			</div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="8">
							<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="gwxxshBc(1);return false;">
										ͨ��
									</button>
									<button type="button" onclick="gwxxshBc(2);return false;">
										��ͨ��
									</button>
									<button type="button" onclick="gwxxshBc(3);return false;">
										�˻�����
									</button>
									<button type="button" onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

