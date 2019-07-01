<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var sfzh = document.getElementById('sfzh').value;
			var userType = document.getElementById('userType').value;
			var xxsh = document.getElementById('xxsh').value;
			var yxshyj = document.getElementById('yxshyj').value;
			var xxshyj = document.getElementById('xxshyj').value;
			if(("未审核" != xxsh ) && (userType != "admin")){
				alert("学校已审核，不能再修改审核结果!");
	          	return false;
			}
			if(yxshyj != null){
	         	if(yxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("系审核意见不能大于200个字符");
	          		 return false;
	       		 }
	       	}
	       	if(xxshyj != null){
	         	if(xxshyj.replace(/[^\u0000-\u00ff]/g, "**").length > 200){	         
	          		 alert("<bean:message key="lable.xsgzyxpzxy" />审核意见不能大于200个字符");
	          		 return false;
	       		 }
	       	}
			
			document.forms[0].action = "/xgxt/zgmsxy_xszz.do?method=xssqshSave";
			document.forms[0].submit();
		}
		function hm(){
			var hkczh = document.getElementById('hkczh').value;
			if(hkczh.length != 18){
				alert("还款帐号号码位数不对!");
				return false;
			}
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		
	</script>
</head>

<body>
	<div class="tab_cur">
		<p class="location">
			<em>您的当前位置:</em><a>助学贷款 - 学生申请 - 单个学生申请审核 </a>
		</p>
	</div>
		<html:form action="zgmsxy_xszz.do?method=xssqshSave" method="post">
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" />" />

			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("保存成功！");
	         	if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
					Close();
					window.dialogArguments.document.getElementById('search_go').click();	
				}
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("保存失败！");
	         	if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
					Close();
					window.dialogArguments.document.getElementById('search_go').click();	
				}
	         	</script>
				</logic:match>
			</logic:present>
			<div class="tab">
			<table class="formlist" width="90%">
				<thead>
					<tr>
						<th colspan="4">
							<span>学生基本信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						
						<th>
							<font color="red">*</font>学号
						</th>
						<td align="left" width="34%">
							<bean:write name='rs' property="xh" />
							<input type="hidden" id="xh" name="xh" value="<bean:write name="rs" property="xh"/>" />
							<input type="hidden" id="nd" name="nd" value="<bean:write name="rs" property="nd"/>" />
						</td>
					</logic:equal>
					<th width="16%">
						<div>
							姓名
						</div>
					</th>
					<td width="34%">
						<bean:write name="rs" property="xm"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							性别
						</div>
					</th>
					<td>
						<bean:write name="rs" property="xb"/>
					</td>
					<th>
						<div>
							<bean:message key="lable.xb" />
						</div>
					</th>
					<td>
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>
				<tr>
					
					<th>
						<div>
							专业
						</div>
					</th>
					<td>
						<bean:write name="rs" property="zymc"/>
					</td>
					<th>
						<div>
							班级
						</div>
					</th>
					<td>
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							入学日年学习费用合计
						</div>
					</th>
					<td>
						<bean:write name="rs" property="rxrq"/>
					</td>
					<th>
						<div>
							毕业时间
						</div>
					</th>
					<td>
						<bean:write name="rs" property="byny"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							学制
						</div>
					</th>
					<td>
						<bean:write name="rs" property="xz"/>
					</td>
					<th>
						<div>
							家庭联系电话（固话）
						</div>
					</th>
					<td>
						<bean:write name="rs" property="lxdh"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
						本人手机号码
						</div>
					</th>
					<td>
						<bean:write name="rs" property="sjhm"/>
					</td>
					<th>
						<div>
							身份证号
						</div>
					</th>
					<td>
						<bean:write name="rs" property="sfzh"/>
						<input type="hidden" id="sfzh" name="sfzh" value="<bean:write name="rs" property="sfzh"/>" />
					</td>
				</tr>
				<tr>
					<th>
						<div>
							家庭邮编
						</div>
					</th>
					<td>
						<bean:write name="rs" property="jtyb"/>
					</td>
					<th>
						<div>
							身份证有效终止日期
						</div>
					</th>
					<td>
						<bean:write name="rs" property="sfzyxzzrq"/>
						<input type="hidden" id="sfzyxzzrq" name="sfzyxzzrq" value="<bean:write name="rs" property="sfzyxzzrq"/>" />
					</td>
				</tr>
				<tr>
					<th>
						<div>
							家庭详细地址
						</div>
					</th>
					<td colspan="3">
						<bean:write name="rs" property="jtdz"/>
						<input type="hidden" id="jtdz" name="jtdz" value="<bean:write name="rs" property="jtdz"/>" />
					</td>
				</tr>
				</tbody>
				<thead>
					<tr>
						<th colspan="4">
							<span>助学贷款学生申请信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th>
						<div>
							家庭联系电话2
						</div>
					</th>
					<td>
						<bean:write name="rs" property="lxdh2"/>
						<input type="hidden" id="lxdh2" name="lxdh2" value="<bean:write name="rs" property="lxdh2"/>" />
					</td>
					<th>
						<div>
							家庭联系电话3
						</div>
					</th>
					<td>
						<bean:write name="rs" property="lxdh3"/>
						<input type="hidden" id="lxdh3" name="lxdh3" value="<bean:write name="rs" property="lxdh3"/>" />
					</td>
				</tr>
				<tr>
					<th>
						<div>
							QQ
						</div>
					</th>
					<td>
						<bean:write name="rs" property="qq"/>
						<input type="hidden" id="qq" name="qq" value="<bean:write name="rs" property="qq"/>" />
					</td>
					<th>
						<div>
							Email
						</div>
					</th>
					<td>
						<bean:write name="rs" property="email"/>
						<input type="hidden" id="email" name="email" value="<bean:write name="rs" property="email"/>" />
					</td>
				</tr>
				<tr>
					<th>
						<div>
							父亲姓名
						</div>
					</th>
					<td>
						<bean:write name="rs" property="fqxm"/>
						<input type="hidden" id="fqxm" name="fqxm" value="<bean:write name="rs" property="fqxm"/>" />
					</td>
					<th>
						<div>
							父亲身份证号
						</div>
					</th>
					<td>
							<bean:write name="rs" property="fqsfzh"/>
							<input type="hidden" id="fqsfzh" name="fqsfzh" value="<bean:write name="rs" property="fqsfzh"/>" />
					</td>
				</tr>
				<tr>
					<th>
						<div>
							母亲姓名
						</div>
					</th>
					<td>
						<bean:write name="rs" property="mqxm"/>
						<input type="hidden" id="mqxm" name="mqxm" value="<bean:write name="rs" property="mqxm"/>" />
					</td>
					<th>
						<div>
							母亲身份证号
						</div>
					</th>
					<td>
							<bean:write name="rs" property="mqsfzh"/>
							<input type="hidden" id="mqsfzh" name="mqsfzh" value="<bean:write name="rs" property="mqsfzh"/>" />
					</td>
				</tr>
				<tr>
					<th>
							贷款合计
					</th>
					<td>
						<bean:write name="rs" property="dkhj"/>
						<input type="hidden" id="dkhj" name="dkhj" value="<bean:write name="rs" property="dkhj"/>" />
					</td>
					<th>
						第一学年学习费用合计
					</th>
					<td>
						<bean:write name="rs" property="xq1"/>
						<input type="hidden" id="xq1" name="xq1" value="<bean:write name="rs" property="xq1"/>" />
					</td>
				</tr>
				<tr>
					<th>
						第二学年学习费用合计
					</th>
					<td>
						<bean:write name="rs" property="xq2"/>
						<input type="hidden" id="xq2" name="xq2" value="<bean:write name="rs" property="xq2"/>" />
					</td>
					<th>
						第三学年学习费用合计
					</th>
					<td>
						<bean:write name="rs" property="xq3"/>
						<input type="hidden" id="xq3" name="xq3" value="<bean:write name="rs" property="xq3"/>" />
					</td>
				</tr>
				<tr>
					<th>
						第四学年学习费用合计
					</th>
					<td>
						<bean:write name="rs" property="xq4"/>
						<input type="hidden" id="xq4" name="xq4" value="<bean:write name="rs" property="xq4"/>" />
					</td>
					<th>
						第五学年学习费用合计
					</th>
					<td>
						<bean:write name="rs" property="xq5"/>
						<input type="hidden" id="xq5" name="xq5" value="<bean:write name="rs" property="xq5"/>" />
					</td>
				</tr>
				<tr>
					<th>
						学费金额
					</th>
					<td>
						<bean:write name="rs" property="xfje"/>
						<input type="hidden" id="xfje" name="xfje" value="<bean:write name="rs" property="xfje"/>" />
					</td>
					<th>
						住宿费金额
					</th>
					<td>
						<bean:write name="rs" property="zsfje"/>
						<input type="hidden" id="zsfje" name="zsfje" value="<bean:write name="rs" property="zsfje"/>" />
					</td>
				</tr>
				<logic:equal name="userType" value="xy">
				<tr>
					<th>
						<div>
							<bean:message key="lable.xsgzyxpzxy" />审核
						</div>
					</th>
					<td>
						<logic:equal name="rs" property="xxsh" value="未审核">
						<html:select name="rs" property="yxsh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
			          	</logic:equal>
						<logic:notEqual name="rs" property="xxsh" value="未审核">
							<bean:write name='rs' property="xxsh" />
						</logic:notEqual>
						<input type="hidden" id="xxsh" name="xxsh"
								value="<bean:write name='rs' property="xxsh" />" />
					</td>
					<th>
						<div>
						</div>
					</th>
					<td>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							<bean:message key="lable.xsgzyxpzxy" />审核意见
						</div>
					</th>
					<td colspan="3">
						<textarea rows="2" id="yxshyj" name="yxshyj" cols="60"><bean:write name="rs" property="yxshyj" /></textarea>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							学校审核
						</div>
					</th>
					<td>
						<bean:write name="rs" property="xxsh" />
						
					</td>
					<th>
						<div>
						</div>
					</th>
					<td>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							学校审核意见
						</div>
					</th>
					<td colspan="3">
						<bean:write name="rs" property="xxshyj" />
						<input type="hidden" id="xxshyj" name="xxshyj" value="<bean:write name='rs' property="xxshyj" />" />
					</td>
				</tr>
				</logic:equal>
				<logic:equal name="userType" value="admin">
				<tr>
					<th>
						<div>
							<bean:message key="lable.xsgzyxpzxy" />审核
						</div>
					</th>
					<td>
						<bean:write name='rs' property="yxsh" />
						<input type="hidden" id="yxsh" name="yxsh" value="<bean:write name="rs" property="yxsh"/>" />
					</td>
					<th>
						<div>
						</div>
					</th>
					<td>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							<bean:message key="lable.xsgzyxpzxy" />审核意见
						</div>
					</th>
					<td colspan="3">
						<bean:write name="rs" property="yxshyj" />
						<input type="hidden" id="yxshyj" name="yxshyj" value="<bean:write name="rs" property="yxshyj"/>" />
					</td>
				</tr>
				<tr>
					<th>
						<div>
							学校审核
						</div>
					</th>
					<td>
						<html:select name="rs" property="xxsh">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
					<th>
						<div>
						</div>
					</th>
					<td>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							学校审核意见
						</div>
					</th>
					<td colspan="3">
						<textarea rows="2" style="width:600px" id="xxshyj" name="xxshyj" ><bean:write name="rs" property="xxshyj" /></textarea>
					</td>
				</tr>
				</logic:equal>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4">
			        	<div class="bz">
							 <logic:equal name="sfksq" value="1">
					        	"<span class="red">*</span>"为必填项
					        	
					        	<logic:equal value="xy" name="userType">
			          				<logic:notEqual name="rs" property="xxsh" value="未审核"> 
			          					&nbsp;&nbsp;<span class="red"><bean:message key="lable.xb" />用户不能修改学校用户审核通过的信息！</span>
			          				</logic:notEqual>
			          			</logic:equal>
					         </logic:equal>
						</div>
			          <div class="btn">
			          	<logic:equal value="xy" name="userType">
			          		<logic:equal name="rs" property="xxsh" value="未审核">
			          			<button type="button" name="提交" onclick="yz();">保存</button>
			          		</logic:equal>
			          		<logic:notEqual name="rs" property="xxsh" value="未审核">
			          		</logic:notEqual>
			          	</logic:equal>
			          	<logic:notEqual value="xy" name="userType">
			          		<logic:notEqual value="yes" name="fdyQx">
			          			<logic:notEqual value="yes" name="bzrQx">
			          				<button type="button" name="提交" onclick="yz();">保存</button>
			          			</logic:notEqual>
			          		</logic:notEqual>
			          	</logic:notEqual>
			          		<button type="button" name="关闭" id="buttonClose" onclick="window.close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
		</html:form>
</body>
</html>
