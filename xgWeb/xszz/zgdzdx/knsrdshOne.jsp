<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		function yz(){
			var userType = document.getElementById('userType').value;
			var xxsh = document.getElementById('xxsh').value;
			var csly = document.getElementById('csly').value;
			if(("未审核" != xxsh ) && (userType != "admin")){
				alert("学校已审核，不能再修改审核结果!");
	          	return false;
			}
			if(csly != null){
	         	if(csly.replace(/[^\u0000-\u00ff]/g, "**").length > 600){	         
	          		 alert("陈述理由不能大于600个字符");
	          		 return false;
	       		 }
	       	}
			refreshForm('/xgxt/zgdzdx_xszz.do?method=knsrdshSave');
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		</script>
	</head>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		
		<html:form action="/zgdzdx_xszz.do?method=knsrdshSave" method="post">
			<div class="tab_cur">
				<p class="location">
				<em>您的当前位置：</em><a>
				困难生 - 审核 - 困难生认定审核 - 单个审核
				</a>
				</p>
			</div>
			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("保存成功！");
	         	Close();
	         	window.dialogArguments.document.getElementById('search_go').click()
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("保存失败！");
	         	</script>
				</logic:match>
			</logic:present>
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" />" />
			<div class="tab">
				<table width="100%"  border="0" class="formlist">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							困难生认定
						</td>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th width="16%">
						学号
					</th>
					<td  width="34%">
						<bean:write name='rs' property="xh" />
						<input type="hidden" id="xh" name="xh"
							value="<bean:write name='rs' property="xh" />" />
					</td>
					<th width="16%">
						<div align="center">
							姓名
						</div>
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
						<bean:write name="rs" property="xb"/>
					</td>
					<th>
							学年
					</th>
					<td>
						<bean:write name="rs" property="xn"/>
						<input type="hidden" id="xn" name="xn"
							value="<bean:write name='rs' property="xn" />" />
					</td>
				</tr>
				<tr>
					<th>
							民族
					</th>
					<td>
						<bean:write name="rs" property="mzmc"/>
					</td>
					<th>
							身份证号
					</th>
					<td>
						<bean:write name="rs" property="sfzh"/>
					</td>
				</tr>
				<tr>
					<th>
						年级
					</th>
					<td>
						<bean:write name="rs" property="nj"/>
					</td>
					<th>
							<bean:message key="lable.xsgzyxpzxy" />名称
					</th>
					<td>
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>
				<tr>
					<th>
							专业名称
					</th>
					<td>
						<bean:write name="rs" property="zymc"/>
					</td>
					<th>
							班级名称
					</th>
					<td>
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>
				<tr>
					<th>
							 家庭人均年收入
					</th>
					<td>
						<bean:write name="rs" property="jtrjnsr"/>
					</td>
					<th>
							在校联系电话
					</th>
					<td>
						<bean:write name="rs" property="zxlxdh"/>
					</td>
				</tr>
				<tr>
					<th>
							 家庭地址
					</th>
					<td colspan="3">
						<bean:write name="rs" property="jtdz"/>
					</td>
				</tr>
				<tr>
					<th>
							所属县市
					</th>
					<td>
						<bean:write name="rs" property="ssxs"/>
					</td>
					<th>
							是否地震重灾区
					</th>
					<td>
						<bean:write name="rs" property="sfdzzzq"/>
					</td>
				</tr>
				<tr>
					<th>
							学生陈述申请理由
					</th>
					<td colspan="3">
						<bean:write name="rs" property="xscssqly"/>
					</td>
				</tr>
				<tr>
					<th>
							申请时间
					</th>
					<td>
						<bean:write name="rs" property="sqsj" />
					</td>
					<logic:equal name="userType" value="xy">
						<th>
								推荐档次
						</th>
						<td>
							<html:select name="rs" property="tjdc">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
							<input type="hidden" id="xxsh" name="xxsh"
								value="<bean:write name='rs' property="xxsh" />" />
						</td>
					</logic:equal>
					<logic:equal name="userType" value="admin">
						<th>
								推荐档次
						</th>
						<td>
							<html:select name="rs" property="tjdc">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</logic:equal>
				</tr>
				<logic:equal name="userType" value="xy">
					<tr>
						<th>
								<bean:message key="lable.xsgzyxpzxy" />审核
						</th>
						<td>
							<html:select name="rs" property="xysh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<td colspan="2">
						</td>
					</tr>
				</logic:equal>
				<logic:equal name="userType" value="admin">
					<tr>
						<th>
								<bean:message key="lable.xsgzyxpzxy" />审核
						</th>
						<td>
							<html:select name="rs" property="xysh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<th>
								学校审核
						</th>
						<td>
							<html:select name="rs" property="xxsh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
				</logic:equal>
				<tr>
					<th>
							陈述理由
					</th>
					<td colspan="3">
						<html:textarea name="rs" property="csly" rows='5'
							style="width:100%" onblur="yzdx(this,'csly')" />
						<input type="hidden" id="csly" name="csly" value="">
					</td>
				</tr>
				</tbody>

				 <tfoot>
				      <tr>
				        <td colspan="6"><div class="bz">"<span class="red">*</span>"为必填项</div>
				          <div class="btn">
				          	<logic:equal name="shqx" value="1">
							<button type="button" name="保存" onclick="yz()" id="buttonSave">
								保 存
							</button>
							</logic:equal>
							<logic:equal name="shqx" value="-1">
							<button type="button"   onclick="yz()"  disabled="disabled"
								id="buttonSave">
								保 存
							</button>
							</logic:equal>
							<button type="button"   onclick="Close();window.dialogArguments.document.getElementById('search_go').click();" 
								id="buttonClose">
								关 闭
							</button>					           
				          </div>
				          </td>
				      </tr>
				    </tfoot>
			</table>
			</div>
		</html:form>
	</body>
</html>
