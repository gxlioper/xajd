<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var userType = document.getElementById('userType').value;
			var xxsh = document.getElementById('xxsh').value;
			var xxshyj = document.getElementById('xxshyj').value;
			var xyshyj = document.getElementById('xyshyj').value;
			
			if(("未审核" != xxsh ) && (userType == "xy")){
				alert("学校已审核，不能再修改审核结果!");
	          	return false;
			}
	       	if(xyshyj != null){
	         	if(xyshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />审核意见不能大于200个字符");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("学校审核意见不能大于200个字符");
	          		 return false;
	       		 }
	       	}
			refreshForm('/xgxt/zzlgdx_rcsw.do?method=cgjshSave');
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
	</head>
	<body onload="checkWinType();document.all('buttonClose').focus()">

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>日常事务 - 审核 - 出国(境)审核详细信息</a>
			</p>
		</div>


		<html:form action="/zzlgdx_rcsw" method="post">
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" name="guid" value="${pkVal }"/>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" />" />


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>出国(境)审核详细信息</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="yz()" id="buttonSave">
										保 存
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" onclick="Close();return false;" id="buttonClose">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								学号
							</th>
							<td width="34%">
								<bean:write name='rs' property="xh" />
							</td>
							<th width="16%">
								姓名
							</th>
							<td width="34%">
								<bean:write name="rs" property="xm" />
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td>
								<bean:write name="rs" property="xb" />
							</td>
							<th>
								身份证号
							</th>
							<td>
								<bean:write name="rs" property="sfzh" />
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td>
								<bean:write name="rs" property="nj" />
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
								名称
							</th>
							<td>
								<bean:write name="rs" property="xymc" />
							</td>
						</tr>
						<tr>
							<th>
								专业名称
							</th>
							<td>
								<bean:write name="rs" property="zymc" />
							</td>
							<th>
								班级名称
							</th>
							<td>
								<bean:write name="rs" property="bjmc" />
							</td>
						</tr>
						<tr>
							<th>
								本人联系方式
							</th>
							<td>
								<bean:write name="rs" property="lxfs_br" />
							</td>
							<th>
								家长联系方式
							</th>
							<td>
								<bean:write name="rs" property="lxfs_jz" />
							</td>
						</tr>
						<tr>
							<th>
								申请时间
							</th>
							<td>
								<bean:write name="rs" property="sqsj" />
							</td>
							<th>
								申请去向
							</th>
							<td>
								<bean:write name="rs" property="sqqx" />
							</td>
						</tr>
						<tr>
							<th>
								出国日期
							</th>
							<td>
								<bean:write name='rs' property="cgrq" />
							</td>
							<th>
								返回日期
							</th>
							<td>
								<bean:write name='rs' property="fhrq" />
							</td>
						</tr>
						<tr>
							<th>
								申请理由
							</th>

							<td colspan="3" style="word-break:break-all;">
								<bean:write name="rs" property="sqly" />
							</td>
						</tr>
						<logic:equal name="userType" value="xy">
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
									审核
								</th>
								<td>
									<html:select name="rs" property="xysh">
										<html:options collection="chkList" property="en"
											labelProperty="cn" />
									</html:select>
									<input type="hidden" id="xxsh" name="xxsh"
										value="<bean:write name="rs" property="xxsh"/>" />
								</td>
								<td>
								</td>
								<td>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
									审核意见
								</th>
								<td colspan="3" style="word-break:break-all;">
									<html:textarea name="rs" property="xyshyj" rows='3'
										style="width:100%" onblur="yzdx(this,'xyshyj')" />
									<input type="hidden" id="xyshyj" name="xyshyj" value="" />
									<input type="hidden" id="xxshyj" name="xxshyj"
										value="<bean:write name="rs" property="xxshyj"/>" />
								</td>
							</tr>
						</logic:equal>
						<logic:equal name="userType" value="xx">
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
									审核
								</th>
								<td>
									<bean:write name="rs" property="xysh" />
								</td>
								<th>
									学校审核
								</th>
								<td>
									<html:select name="rs" property="xxsh">
										<html:options collection="chkList" property="en"
											labelProperty="cn" />
									</html:select>
									<input type="hidden" id="xysh" name="xysh"
										value="<bean:write name="rs" property="xysh"/>" />
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
									审核意见
								</th>
								<td colspan="3" style="word-break:break-all;">
									<bean:write name="rs" property="xyshyj" />
								</td>
							</tr>
							<tr>
								<th>
									学校审核意见
								</th>
								<td colspan="3" style="word-break:break-all;">
									<html:textarea name="rs" property="xxshyj" rows='3'
										style="width:100%" onblur="yzdx(this,'xxshyj')" />
									<input type="hidden" id="xxshyj" name="xxshyj" value="" />
									<input type="hidden" id="xyshyj" name="xyshyj"
										value="<bean:write name="rs" property="xyshyj"/>" />
								</td>
							</tr>
						</logic:equal>
						</tbody>
				</table>
				</div>
		</html:form>
		<logic:present name="ok">
			<input type="hidden" id="msg" value="${ok }"/>
			<script type="text/javascript">
				alert($('msg').value);
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:present>
	</body>
</html>
