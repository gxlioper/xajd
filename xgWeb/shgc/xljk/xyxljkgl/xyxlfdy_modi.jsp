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
				<em>您的当前位置:</em><a>${title } - 修改 </a>
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
										修 改
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
								心理专干编号
						</logic:equal>
								<logic:notEqual value="10827" name="xxdm">
								辅导员编号
						</logic:notEqual>
							</th>
							<td align="left">
								<html:text property="fdybh" styleId="fdybh" onblur=""
									onkeypress="" readonly="true" />
							</td>
							<th>
								<font color="red">*</font>姓 名
							</th>
							<td align="left">
								<html:text property="xm" styleId="xm" maxlength="10"/>
							</td>
						</tr>
						<tr>
							<logic:equal value="10827" name="xxdm">
								<th colspan="2">
									<font color="red">*</font>毕 业 院 校
								</th>
								<td align="left">
									<html:text property="byyx" styleId="byyx" maxlength="20"/>
								</td>
							</logic:equal>
							<logic:notEqual value="10827" name="xxdm">
								<th colspan="2">
									<font color="red">*</font>性 别
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
								出 生 年 月
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
								<font color="red">*</font>学 院
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
								<font color="red">*</font>任 职 时 间
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
								职 务
							</th>
							<td align="left">
								<html:text property="zw" styleId="zw" maxlength="10"/>
							</td>
							<th>
								职 称
							</th>
							<td align="left">
								<html:text property="zc" styleId="zc" maxlength="15"/>
							</td>
						</tr>
						<tr>
							<th colspan="2">
								<font color="red">*</font>学 历
							</th>
							<td align="left">
								<html:text property="xl" styleId="xl" maxlength="20"/>
							</td>
							<th>
								<font color="red">*</font>专 业
							</th>
							<td align="left">
								<html:text property="zy" maxlength="20"/>
							</td>
						</tr>
						<tr>
							<th colspan="2">
								<font color="red">*</font>手 机 号 码
							</th>
							<td align="left">
								<html:text property="sjhm" styleId="sjhm" 
								maxlength="20"
								onkeyup="value=value.replace(/[^\d]/g,'')"/>
							</td>
							<th>
								<font color="red">*</font>联 系 电 话
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
									<font color="red">*</font>毕 业 院 校
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
								<font color="red">*</font>从事心理健康教育
								<br />
								工作经历
							</th>
							<td colspan="4" style="word-break:break-all;">
								<html:textarea rows="7" style="width:98%" property="gzjl"
									styleId="a" onblur="checkLen(this,500)" />
							</td>
						</tr>
						<tr>
							<th colspan="2">
								<font color="red">*</font>参加心理健康教育
								<br />
								培训情况
							</th>
							<td colspan="4" style="word-break:break-all;">
								<html:textarea rows="7" style="width:98%" property="pxqk"
									styleId="a" onblur="checkLen(this,500)" />
							</td>
						</tr>
						<tr>
							<th colspan="2">
								<font color="red">*</font>心理健康教育方面的论文
								<br />
								及科研成果
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
								备 注
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
			<script>alert("编号已经存在!增加失败")</script>
		</logic:equal>
		<logic:equal name="message" value="xh_exits">
			<script>alert("该学生已经存在!增加失败")</script>
		</logic:equal>
		<logic:equal name="message" value="update_success">
			<script>
				alert("修改成功!");
				dialogArgumentsQueryChick();
				Close();
			</script>
		</logic:equal>
		<logic:equal name="message" value="insert_fail">
			<script>
				alert("增加失败!");
				document.getElementById("tmpdiv").style.display = "none";
			</script>
		</logic:equal>
	</logic:notEmpty>
</html>
