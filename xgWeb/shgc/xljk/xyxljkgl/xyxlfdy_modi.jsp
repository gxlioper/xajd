<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/lrh_new_js.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" >
			function jd(){
				if($("jd")){
					$("jd").focus();
				}
			}
		</script>
	</head>
	<body onload="usercheck();jd()">
		<div class="tab_cur" id="jd">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title } - �޸� </a>
			</p>
		</div>


		<html:form action="/xljk_xljkfdy" method="post">
			<input type="hidden" id="userDep" name="userDep"
				value="<bean:write name="userDep" scope="request"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" id="modi_flag" name="modi_flag" value="no" />
			<input type="hidden" id="xxdm1" name="xxdm1" value="${xxdm }" />
			<input type="hidden" id="fdy_xnid" name="fdy_xnid"
				value="<bean:write name="xnid" scope="request" />" />
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<button onclick="fdy_save('fdy_modi')" id="buttonUpdate">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th colspan="2" nowrap="nowrap">
								<font color="red">*</font>
								<logic:equal value="10827" name="xxdm">
								����ר�ɱ��
						</logic:equal>
								<logic:notEqual value="10827" name="xxdm">
								����Ա���
						</logic:notEqual>
							</th>
							<td align="left">
								<html:text property="fdybh" styleId="fdybh" onblur=""
									onkeypress="" readonly="true" />
							</td>
							<th>
								<font color="red">*</font>�� ��
							</th>
							<td align="left">
								<html:text property="xm" styleId="xm" maxlength="10"/>
							</td>
						</tr>
						<tr>
							<logic:equal value="10827" name="xxdm">
								<th colspan="2">
									<font color="red">*</font>�� ҵ Ժ У
								</th>
								<td align="left">
									<html:text property="byyx" styleId="byyx" maxlength="20"/>
								</td>
							</logic:equal>
							<logic:notEqual value="10827" name="xxdm">
								<th colspan="2">
									<font color="red">*</font>�� ��
								</th>
								<td>
									<html:select property="xb" style="width:50px" styleId="xb"
										onchange="">
										<html:option value=""></html:option>
										<html:options collection="sexList" property="DMH"
											labelProperty="DMMC" />
									</html:select>
							</logic:notEqual>
							<th nowrap="nowrap">
								�� �� �� ��
							</th>
							<td align="left">
								<html:text property="csny" styleId="csrq"
									onblur='dateFormatChg(this)'
									readonly="true"
									onclick="showCalendar(this.id,'y-mm-dd')" />
							</td>
						</tr>
						<tr>
							<th colspan="2">
								<font color="red">*</font>ѧ Ժ
							</th>
							<td align="left">
								<html:select property="xydm" style="width:180px" styleId="xy"
									disabled="">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>�� ְ ʱ ��
							</th>
							<td align="left">
								<html:text style="cursor:hand; width:145px;" styleId="dateF"
									property="rzsj"
									onclick="return showCalendar('dateF','y-mm-dd');"
									readonly="readonly" />
							</td>
						</tr>
						<tr>
							<th colspan="2">
								ְ ��
							</th>
							<td align="left">
								<html:text property="zw" styleId="zw" maxlength="10"/>
							</td>
							<th>
								ְ ��
							</th>
							<td align="left">
								<html:text property="zc" styleId="zc" maxlength="15"/>
							</td>
						</tr>
						<tr>
							<th colspan="2">
								<font color="red">*</font>ѧ ��
							</th>
							<td align="left">
								<html:text property="xl" styleId="xl" maxlength="20"/>
							</td>
							<th>
								<font color="red">*</font>ר ҵ
							</th>
							<td align="left">
								<html:text property="zy" maxlength="20"/>
							</td>
						</tr>
						<tr>
							<th colspan="2">
								<font color="red">*</font>�� �� �� ��
							</th>
							<td align="left">
								<html:text property="sjhm" styleId="sjhm" 
								maxlength="20"
								onkeyup="value=value.replace(/[^\d]/g,'')"/>
							</td>
							<th>
								<font color="red">*</font>�� ϵ �� ��
							</th>
							<td align="left">
								<html:text property="lxdh" 
								maxlength="20"
								onkeyup="value=value.replace(/[^\d]/g,'')"/>
							</td>
						</tr>
						<logic:notEqual value="10827" name="xxdm">
							<tr>
								<th colspan="2">
									<font color="red">*</font>�� ҵ Ժ У
								</th>
								<td align="left">
									<html:text property="byyx" styleId="byyx" maxlength="20"/>
								</td>

								<th>

								</th>
								<td align="left">

								</td>
							</tr>
						</logic:notEqual>
						<tr>
							<th colspan="2">
								<font color="red">*</font>��������������
								<br />
								��������
							</th>
							<td colspan="4" style="word-break:break-all;">
								<html:textarea rows="7" style="width:98%" property="gzjl"
									styleId="a" onblur="checkLen(this,500)" />
							</td>
						</tr>
						<tr>
							<th colspan="2">
								<font color="red">*</font>�μ�����������
								<br />
								��ѵ���
							</th>
							<td colspan="4" style="word-break:break-all;">
								<html:textarea rows="7" style="width:98%" property="pxqk"
									styleId="a" onblur="checkLen(this,500)" />
							</td>
						</tr>
						<tr>
							<th colspan="2">
								<font color="red">*</font>�������������������
								<br />
								�����гɹ�
							</th>
							<td colspan="4" style="word-break:break-all;">
								<html:textarea rows="7" style="width:98%" property="lwcg"
									styleId="a" onblur="checkLen(this,500)"/>
							</td>
						</tr>
						<tr>
						</tr>
						<tr>
							<th colspan="2">
								�� ע
							</th>
							<td colspan="4" align="left style="word-break:break-all;"">
								<html:textarea rows="5" style="width:98%" property="bz"
									styleId="a" onblur="checkLen(this,200)"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="tmpdiv"></div>
		</html:form>
	</body>
	<logic:notEmpty name="message">
		<logic:equal name="message" value="fdybh_exits">
			<script>alert("����Ѿ�����!����ʧ��")</script>
		</logic:equal>
		<logic:equal name="message" value="xh_exits">
			<script>alert("��ѧ���Ѿ�����!����ʧ��")</script>
		</logic:equal>
		<logic:equal name="message" value="update_success">
			<script>
				alert("�޸ĳɹ�!");
				dialogArgumentsQueryChick();
				Close();
			</script>
		</logic:equal>
		<logic:equal name="message" value="insert_fail">
			<script>
				alert("����ʧ��!");
				document.getElementById("tmpdiv").style.display = "none";
			</script>
		</logic:equal>
	</logic:notEmpty>
</html>
