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
		<script type="text/javascript" src="js/qgzx/gwgl/gwsq.js"></script>
				<script type="text/javascript">
			jQuery(document).ready(function(){
				var sfsdgwcjsx=jQuery("#sfsdgwcjsx").val();
				//�������� ���õ�н������
				var gwzgcjsx=jQuery("#gwzgcjsx").val();
				var sfkgggwcjsx=jQuery("#sfkgggwcjsx").val();
				//��λ���ó������
				var gwcjsx=jQuery("#gwcjsx").val();
				//����˸�λδ����
				if(gwcjsx==""){
					jQuery("#gwcjsx").val(gwzgcjsx);
					jQuery("#gwcjsxh").text(gwzgcjsx);
				}
				if("no"==sfsdgwcjsx){
					jQuery("#gwcjsxTr").hide();
				}else{
					jQuery("#gwcjsxTr").show();
					if("no"==sfkgggwcjsx){
						jQuery("#gwcjsx").hide();
						jQuery("#gwcjsxh").show();
						jQuery("#sxcolor").hide();
					}else{
						jQuery("#gwcjsx").show();
						jQuery("#gwcjsxh").hide();
						jQuery("#sxcolor").show();
					}
				}

				var doType=jQuery("#doType").val();
				//�鿴����
				if(doType!="update"){
					jQuery("#gwcjsx").hide();
					jQuery("#gwcjsxh").show();
					jQuery("#sxcolor").hide();
					jQuery(".bz").hide();
				}
			});
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
			<input type="hidden" name="oldGwxh" id="oldGwxh" value="${rs.gwxh }" />
			<input type="hidden"  id="sfsdgwcjsx" value="${jcpz.sfsdgwcjsx}"/>
			<input type="hidden"  id="gwzgcjsx" value="${jcpz.gwzgcjsx}"/>
			<input type="hidden"  id="sfkgggwcjsx" value="${jcpz.sfkgggwcjsx}"/>
			<%--<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			--%>		
			<div style='tab��width:100%;overflow-x:hidden;overflow-y:auto;height:555px;'>
				<table border="0" class="formlist" >
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
						<logic:equal name="doType" value="update">
						<tr>
							<th width="16%">
								��λ����
							</th>
							
							<td width="34%" colspan="3" >
								<html:text name="rs" property="gwmc" styleId="gwmc" maxlength="50" style="width:200px" ></html:text>
							</td>
							<th width="16%">
								��λ����
							</th>
							<td width="34%" colspan="3" >
								<html:select name="rs" property="gwxzdm" styleId="gwxzdm" style="width:200px" >
									<html:option value="">---------��ѡ��---------</html:option>
									<html:options collection="gwxzList" property="gwxzdm" labelProperty="gwxzmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font class="red">*</font>��������
							</th>
							<td width="34%" colspan="3" >
								<html:text name="rs" property="xqrs" styleId="xqrs" size="10" maxlength="3" onblur="checkInputNum(this)"></html:text>
							</td>
							<th width="16%">
								<font class="red">*</font>��������
							</th>
							
							<td width="34%" colspan="3">
								<html:text name="rs" property="knsrs" styleId="knsrs" size="10" maxlength="3" onblur="checkXqrs(this)"></html:text>  (��)
							</td>
						</tr>
							
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
						<tr style="height:80px">
							<th align="right" >
								<font class="red">*</font>��λ����<br/><font color="red">(��1000��)</font>
							</th>
							<td colspan="7" >
							<html:textarea  name="rs" property='gwms' styleId="gwms" style="word-break:break-all;width:97%" onblur="chLengs(this,1000);"
									rows='6' />
							</td>
						</tr>
						<tr style="height:80px">
							<th align="right" >
								<font class="red">*</font>��λ��ԱҪ��<br/><font color="red">(��1000��)</font>
							</th>
							<td colspan="7" >
							<html:textarea  name="rs" property='gwryyq' styleId="gwryyq" style="word-break:break-all;width:97%" onblur="chLengs(this,1000);"
									rows='6' />
							</td>
							
						</tr>
						<tr style="height:80px">
							<th align="right" >
								��λԤ����ԱЧ��<br/><font color="red">(��1000��)</font>
							</th>
							<td colspan="7" >
							<html:textarea  name="rs" property='gwyqryxg' styleId="gwyqryxg" style="word-break:break-all;width:97%" onblur="chLengs(this,1000);"
									rows='6' />
							</td>
							
						</tr>
						<tr style="height:80px">
							<th align="right" >
								��ע<br/><font color="red">(��1000��)</font>
							</th>
							<td colspan="7" >
							<html:textarea  name="rs" property='bz' styleId="bz" style="word-break:break-all;width:97%" onblur="chLengs(this,1000);"
									rows='4'   />
							</td>
						</tr>
						</logic:equal>
						<logic:notEqual name="doType" value="update">
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
							<td width="34%" colspan="7">
								<html:text  name="rs" property="gwcjsx" styleId="gwcjsx" size="10" maxlength="7" onblur="checkInt(this)"></html:text>
								<span id="gwcjsxh">${rs.gwcjsx}</span>
								<span>Ԫ/��  &nbsp;&nbsp;(�ø�λÿ��ÿ�³������)</span>
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
						</logic:notEqual>
					</tbody>
					<logic:notEqual name="doType" value="update">
					<thead>
						<tr>
							<th colspan="8">
								<span>��λ�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								���״̬
							</th>
							
							<td width="34%" colspan="3" >
								${rs.shzt }
							</td>
							<th width="16%">
								�����
							</th>
							<td width="34%" colspan="3" >
								${rs.shr }
							</td>
						</tr>
						<tr>
							<th width="16%">
								���ʱ��
							</th>
							
							<td width="34%" colspan="3" >
								${rs.shsj }
							</td>
							<th width="16%">
							</th>
							<td width="34%" colspan="3" >
							</td>
						</tr>
						<tr style="height:40px">
							<th align="right" >
								������
							</th>
							<td colspan="7" style="word-break:break-all;width:97%">
								${rs.shyj }
							</td>
						</tr>
					</tbody>
					</logic:notEqual>
				</table>
			</div>
			<div style="height: 30px">
			</div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="8">
							<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<logic:equal name="doType" value="update">
										<button type="button" onclick="bcXgGwsq()">
											�� ��
										</button>
									</logic:equal>
									<button type="button" onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

