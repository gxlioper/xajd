<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/lrh_new_js.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		function jd(){
			if($("jd")){
				$("jd").focus();
			}
		}
		</script>
	</head>
	<body onload="lrh_xyDisabled();jd()">
	
		<div class="tab_cur" id="jd">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }-���ӽ����</a>
			</p>
		</div>
	
		<html:form action="/xljk_xyxljkjyhd" method="post" enctype="multipart/form-data">
			<input type="hidden" id="add_flag" name="add_flag" value="no" />
			<input type="hidden" id="userDep" name="userDep"
				value="<bean:write name="userDep" scope="request"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
				
				
			<div class="tab">
				<table width="100%"  border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="8">
								<div class="btn">
									<button  onclick="xyxljkjyhd_save('xyxljkjyhd_add')"
										id="buttonSave">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>	
					<tbody>
				<tr>
					<th colspan="2">
						<font color="red">*</font>�� ��
					</th>
					<td colspan="6" align="left">
						<html:text style="width:70%" property="zt" styleId="zt" maxlength="50"/>
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left" colspan="2">
						<html:select property="xydm" style="width:155px" styleId="xy">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
					</td>
					<th colspan="2">
					</th>
					<td colspan="2">
					</td>
				</tr>
				<tr>
					<th colspan="2">
						�� �� �� ʽ
					</th>
					<td align="left" colspan="2">
						<html:select property="hdxs"  styleId="hdxs" style="width:155px"
							onchange="check_qthdxs()">
							<html:option value=""></html:option>
							<html:options collection="hdxsList" property="DMH"
								labelProperty="DMMC" />
						</html:select>
					</td>
					<th colspan="2">
						�� �� �� ʽ
					</th>
					<td align="left" colspan="2">
						<html:text property="qthdxs" styleId="qthdxs" readonly="true" maxlength="25"/>
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<font color="red">*</font>�� ��
					</th>
					<td align="left" colspan="2">
						<html:text property="dd" styleId="dd" maxlength="25"/>
					</td>
					<th colspan="2" nowrap="nowrap">
						<font color="red">*</font>�� �� �� ��
					</th>
					<td colspan="2">
						<html:text styleId="dateF"
							property="rq" onclick="return showCalendar('dateF','y-mm-dd');"
							readonly="true" />
					</td>
				</tr>
				<tr>
					<th colspan="2">
						�� ʼ ʱ ��
					</th>
					<td align="left" colspan="2">
						<html:text property="kssj" 
								   styleId="kssj" 
								   maxlength="10"/>
					</td>
					<th colspan="2">
						�� �� ʱ ��
					</th>
					<td align="left" colspan="2">
						<html:text property="jssj" 
						           styleId="jssj" 
						           maxlength="10"/>
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<font color="red">*</font>�� �� ��
					</th>
					<td align="left" colspan="2">
						<html:text property="zcr" styleId="zy" maxlength="10"/>
					</td>
					<th colspan="2">
						ѧ ��
					</th>
					<td align="left" colspan="2">
						<html:text property="xs" maxlength="10"/>
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<font color="red">*</font>�� �� ѧ ��
					</th>
					<td colspan="6" align="left">
						<html:text style="width:70%" property="cyxs" styleId="cyxs" maxlength="25"/>
					</td>
				</tr>
				<tr>
					<th colspan="2" nowrap="nowrap">
						<font color="red">*</font>�� �� ѧ �� �� ��
					</th>
					<td align="left" colspan="6">
						<html:text property="rs" styleId="rs" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="10"/>
					</td>
				</tr>
				
				<!-- ����ѧԺ�ļ��ϴ� -->
				<logic:equal name="xxdm" value="10395">
				<tr>
					<th colspan="2" nowrap="nowrap">
						�ļ��ϴ�
					</th>
					<td align="left" title="�����ļ�����" colspan="6">
						<input type="file" name="uploadFile" style="width:240px"  value="" contenteditable="false"  />
					</td>
				</tr>
				</logic:equal>
				<tr>
					<th colspan="2">
						<font color="red">*</font> �� �� �� �� �� ¼
					</th>
					<td colspan="6" align="left">
						<html:textarea rows="5"  style="width:98%;word-break:break-all;"
							 property="hdjl"  onblur="checkLen(this,500)"/>
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<font color="red">*</font>�� �� �� �� Ч ��
					</th>
					<td colspan="6" align="left">
						<html:textarea rows="5"  style="width:98%;word-break:break-all;"
							 property="hdxg"   onblur="checkLen(this,500)"/>
					</td>
				</tr>
				</tbody>
			</table>
			</div>
			<div id="tmpdiv"></div>
			<logic:notEmpty name="message">
				<logic:equal name="message" value="insert_success">
					<script>
						alert("����ɹ�!");
						dialogArgumentsQueryChick();
						Close();
					</script>
				</logic:equal>
				<logic:equal name="message" value="insert_fail">
					<script>
						alert("����ʧ��!");
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
