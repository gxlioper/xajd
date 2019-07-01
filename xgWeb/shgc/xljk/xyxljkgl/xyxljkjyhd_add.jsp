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
				<em>您的当前位置:</em><a>${title }-增加教育活动</a>
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
										保 存
									</button>
								</div>
							</td>
						</tr>
					</tfoot>	
					<tbody>
				<tr>
					<th colspan="2">
						<font color="red">*</font>主 题
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
						教 育 形 式
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
						其 他 形 式
					</th>
					<td align="left" colspan="2">
						<html:text property="qthdxs" styleId="qthdxs" readonly="true" maxlength="25"/>
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<font color="red">*</font>地 点
					</th>
					<td align="left" colspan="2">
						<html:text property="dd" styleId="dd" maxlength="25"/>
					</td>
					<th colspan="2" nowrap="nowrap">
						<font color="red">*</font>活 动 日 期
					</th>
					<td colspan="2">
						<html:text styleId="dateF"
							property="rq" onclick="return showCalendar('dateF','y-mm-dd');"
							readonly="true" />
					</td>
				</tr>
				<tr>
					<th colspan="2">
						开 始 时 间
					</th>
					<td align="left" colspan="2">
						<html:text property="kssj" 
								   styleId="kssj" 
								   maxlength="10"/>
					</td>
					<th colspan="2">
						结 束 时 间
					</th>
					<td align="left" colspan="2">
						<html:text property="jssj" 
						           styleId="jssj" 
						           maxlength="10"/>
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<font color="red">*</font>主 持 人
					</th>
					<td align="left" colspan="2">
						<html:text property="zcr" styleId="zy" maxlength="10"/>
					</td>
					<th colspan="2">
						学 生
					</th>
					<td align="left" colspan="2">
						<html:text property="xs" maxlength="10"/>
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<font color="red">*</font>参 与 学 生
					</th>
					<td colspan="6" align="left">
						<html:text style="width:70%" property="cyxs" styleId="cyxs" maxlength="25"/>
					</td>
				</tr>
				<tr>
					<th colspan="2" nowrap="nowrap">
						<font color="red">*</font>参 与 学 生 人 数
					</th>
					<td align="left" colspan="6">
						<html:text property="rs" styleId="rs" onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="10"/>
					</td>
				</tr>
				
				<!-- 闽江学院文件上传 -->
				<logic:equal name="xxdm" value="10395">
				<tr>
					<th colspan="2" nowrap="nowrap">
						文件上传
					</th>
					<td align="left" title="处分文件下载" colspan="6">
						<input type="file" name="uploadFile" style="width:240px"  value="" contenteditable="false"  />
					</td>
				</tr>
				</logic:equal>
				<tr>
					<th colspan="2">
						<font color="red">*</font> 教 育 活 动 记 录
					</th>
					<td colspan="6" align="left">
						<html:textarea rows="5"  style="width:98%;word-break:break-all;"
							 property="hdjl"  onblur="checkLen(this,500)"/>
					</td>
				</tr>
				<tr>
					<th colspan="2">
						<font color="red">*</font>教 育 活 动 效 果
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
						alert("保存成功!");
						dialogArgumentsQueryChick();
						Close();
					</script>
				</logic:equal>
				<logic:equal name="message" value="insert_fail">
					<script>
						alert("保存失败!");
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
