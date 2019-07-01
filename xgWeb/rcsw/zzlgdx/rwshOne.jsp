<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var shyj = document.getElementById('shyj').value;
			
	       	if(shyj != null){
	         	if(shyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("审核意见不能大于100个字");
	          		 return false;
	       		 }
	       	}
			refreshForm('/xgxt/zzlgdx_rcsw.do?method=rwshSave');
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
	</head>
	<body onload="checkWinType();document.all('buttonClose').focus()">

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>日常事务 - 审核 - 入伍审核详细信息</a>
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
								<span>入伍审核详细信息</span>
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
								民族
							</th>
							<td>
								<bean:write name="rs" property="mzmc" />
							</td>
							<th>
								政治面貌
							</th>
							<td>
								<bean:write name="rs" property="zzmmmc" />
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
								出生日期
							</th>
							<td>
								<bean:write name="rs" property="csrq" />
							</td>
							<th>
								申请时间
							</th>
							<td>
								<bean:write name="rs" property="sqsj" />
							</td>
						</tr>
						<tr>
							<th>
								手机
							</th>
							<td>
								<bean:write name="rs" property="sj" />
							</td>
							<th>
								是否已告知家长
								<br />
								并得到家长的同意
							</th>
							<td>
								<bean:write name="rs" property="sfygzjzbty" />
							</td>
						</tr>
						<tr>
							<th>
								现今户籍
							</th>
							<td>
								<bean:write name="rs" property="xjhj" />
							</td>
							<th>
								入学前户口
								<br />
								所在地
							</th>
							<td>
								<bean:write name="rs" property="rxqhkszd" />
							</td>
						</tr>
						<tr>
							<th>
								家庭详细地址
							</th>
							<td>
								<bean:write name="rs" property="jtxxdz" />
							</td>
							<th>
								父母联系方式
							</th>
							<td>
								<bean:write name="rs" property="fmlxfs" />
							</td>
						</tr>
						<tr>
							<th>
								审核结果
							</th>
							<td>
								<html:select name="rs" property="shjg">
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
							<td>
							</td>
							<td>
							</td>
						</tr>
						<tr>
							<th>
								审核意见
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="shyj" rows='3'
									style="width:100%" onblur="yzdx(this,'shyj')" />
								<input type="hidden" id="shyj" name="shyj" value="">
							</td>
						</tr>
				</table>
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
