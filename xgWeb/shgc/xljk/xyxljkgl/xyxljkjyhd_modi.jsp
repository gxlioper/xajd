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
				<em>���ĵ�ǰλ��:</em><a>${title }-�޸Ľ����</a>
			</p>
		</div>


		<html:form action="/xljk_xyxljkjyhd" method="post">
			<input type="hidden" id="modi_flag" name="modi_flag" value="no" />
			<input type="hidden" id="userDep" name="userDep"
				value="<bean:write name="userDep" scope="request"/>" />
			<input type="hidden" id="xn_id" name="xn_id"
				value="<bean:write name="xn_id" scope="request"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="8">
								<div class="btn">
									<button onclick="xyxljkjyhd_save('xyxljkjyhd_modi')"
										id="buttonUpdate">
										����
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
								<html:text style="width:98%" property="zt" styleId="zt"  maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th colspan="2">
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left" colspan="2">
								<html:select property="xydm" style="width:155px" styleId="xy"
									disabled="">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
						</tr>

						<tr>
							<th colspan="2">
								�� �� �� ʽ
							</th>
							<td align="left" colspan="2">
								<html:select property="hdxs" styleId="hdxs" style="width:155px"
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
								<html:text  styleId="dateF"
									property="rq" onclick="return showCalendar('dateF','y-mm-dd');"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<th colspan="2">
								�� ʼ ʱ ��
							</th>
							<td align="left" colspan="2">
								<html:text property="kssj" styleId="kssj" maxlength="10"/>
							</td>
							<th colspan="2">
								�� �� ʱ ��
							</th>
							<td align="left" colspan="2">
								<html:text property="jssj" styleId="jssj" maxlength="10"/>
							</td>
						</tr>
						<tr>
							<th colspan="2">
								<font color="red">*</font>�� �� ��
							</th>
							<td align="left" colspan="2">
								<html:text property="zcr" styleId="zy" maxlength="10" />
							</td>
							<th colspan="2">
								ѧ ��
							</th>
							<td align="left" colspan="2">
								<html:text property="xs" maxlength="10" />
							</td>
						</tr>
						<tr>
							<th colspan="2">
								<font color="red">*</font>�� �� ѧ ��
							</th>
							<td colspan="6" align="left">
								<html:text style="width:98%" property="cyxs" styleId="cyxs" maxlength="25"/>
							</td>
						</tr>
						<tr>
							<th colspan="2" nowrap="nowrap">
								<font color="red">*</font>�� �� ѧ �� �� ��
							</th>
							<td align="left" colspan="6">
								<html:text property="rs" styleId="rs" maxlength="10"/>
							</td>
						</tr>
						
						<!-- ����ѧԺ�ļ����� -->
						<logic:equal name="xxdm" value="10395">
						<tr>
							<th colspan="2" nowrap="nowrap">
								�ļ�����
							</th>
							<td align="left" title="�����ļ�����" colspan="6">
								<a href="downloadfilewj.do?len=&wjsclj=${wjdz}" target="_blank"><font color="red">����</font></a>
							</td>
						</tr>
						</logic:equal>
						
						<tr>
							<th colspan="2">
								<font color="red">*</font> �� �� �� �� �� ¼
							</th>
							<td colspan="6" align="left">
								<html:textarea rows="5" style="width:98%" property="hdjl" cols="76"  style="word-break:break-all;"
									styleId="a" onblur="checkLen(this,500)" />
							</td>
						</tr>
						<tr>
							<th colspan="2">
								<font color="red">*</font>�� �� �� �� Ч ��
							</th>
							<td colspan="6" align="left">
								<html:textarea rows="5" style="width:98%" property="hdxg" cols="76"  style="word-break:break-all;"
									styleId="a" onblur="checkLen(this,500)"/>
							</td>
						</tr>
					</tbody>
				</table>
				<div id="tmpdiv"></div>
			</div>
			<logic:notEmpty name="message">
				<logic:equal name="message" value="update_success">
					<script>
						alert("����ɹ�!");
						opener.document.getElementById("search_go").click();
						Close();
						</script>
				</logic:equal>
				<logic:equal name="message" value="update_fail">
					<script>
						alert("����ʧ��!");
						document.getElementById("tmpdiv").style.display = "none";
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
