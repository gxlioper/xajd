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
			
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			if((sfzh == null) || (sfzh == "")){
				alert("身份证不能为空，请到学生信息处维护!");
				return false;
			}
			//if(!chkSfzh(sfzh)){
			//	return false;
			//}
			//if ("6000" < Math.round(sqje)){
			//	alert("申请贷款金额已超过最大贷款金额6000元!");
			//	return false;
			//}
			
			document.forms[0].action = "/xgxt/zgmsxy_xszz.do?method=xssqSave";
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
		/**定义公用方法:检验身份证号码*/
function chkSfzh(obj) {
	var sfzh = obj.toLowerCase();
	var OldID;
	if(sfzh.length == 15){
	    if(sfzh.substring(6,8)<'80'){
	    alert("您输入的身份证号码是８０年以前的，请输入学生本人的身份证号码！");
		return false;
	    }else{
		OldID = sfzh;
		return true;
		}
	}else if(sfzh.length == 18){
	    if(sfzh.substring(6,10)<'1980'){
	    alert("您输入的身份证号码是１９８０年以前的，请输入学生本人的身份证号码！");
		return false;
	    }else{
		OldID = sfzh.substring(0, 6) + sfzh.substring(8,sfzh.length-1);
		}
		
	}else{
		alert("请输入正确的身份证号码！");
		return false;
	}
	var W = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
	var A = new Array("1", "0", "x","9", "8", "7", "6", "5", "4", "3", "2");
	var i, j, S;
	var NewID, ID, strNF;
	NewID = OldID.substring(0, 6) + "19" + OldID.substring(6,OldID.length);
	S = 0;
	for( i = 0; i <= 16; i++){
		j = NewID.substring(i, i+1) * W[i];
		S = S + j;
	}
	S = S % 11;
	if(sfzh != NewID + A[S]){
		alert("请输入正确的身份证号码！");
		return false;
	}
	return true;
}
	</script>
</head>

<body>
	<div class="tab_cur">
		<p class="location">
			<em>您的当前位置:</em><a>助学贷款 - 学生申请 - 学生申请 </a>
		</p>
	</div>
		<html:form action="zgmsxy_xszz.do?method=xssq_xssq" method="post">
			<input type="hidden" id="url" name="url"
				value="/zgmsxy_xszz.do?method=xssq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="rs" property="pkVal" />"/>
			<input type="hidden" id="nd" name="nd"
				value="<bean:write name="rs" property="nd" />"/>

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
			<logic:present name="isPASS">
				<logic:match value="is" name="isPASS">
					<script language="javascript">
	         			alert("已通过审核，不能申请或修改！");
	         		</script>
				</logic:match>
			</logic:present>
			<logic:present name="result">
				<logic:match value="false" name="result">
					<script language="javascript">
					window.close();
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
							<html:text name='rs' property="xh" styleId="xh"
								readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<logic:notEqual value="modi" name="doType">
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									选择
								</button>
							</logic:notEqual>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<th>
							<font color="red">*</font>学号
						</th>
						<td align="left" width="34%">
							<input type="text" id="xh" name="xh"
								value="<bean:write name='rs' property="xh" />" readonly="readonly"/>
						</td>
					</logic:equal>
					<th width="16%">
						<div>
							姓名
						</div>
					</th>
					<td width="34%">
						<%--<bean:write name="rs" property="xm"/>--%>
						<input type="text" id="xm" name="xm" readonly="readonly" value="<bean:write name="rs" property="xm"/>"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							性别
						</div>
					</th>
					<td>
						<%--<bean:write name="rs" property="xb"/>--%>
						<input type="text" id="xb" name="xb" readonly="readonly" value="<bean:write name="rs" property="xb"/>"/>
					</td>
					<th>
						<div>
							<bean:message key="lable.xb" />
						</div>
					</th>
					<td>
						<%--<bean:write name="rs" property="xymc"/>--%>
						<input type="text" id="xymc" name="xymc" readonly="readonly" value="<bean:write name="rs" property="xymc"/>"/>
					</td>
				</tr>
				<tr>
					
					<th>
						<div>
							专业
						</div>
					</th>
					<td>
						<%--<bean:write name="rs" property="zymc"/>--%>
						<input type="text" id="zymc" name="zymc" readonly="readonly" value="<bean:write name="rs" property="zymc"/>"/>
					</td>
					<th>
						<div>
							班级
						</div>
					</th>
					<td>
						<%--<bean:write name="rs" property="bjmc"/>--%>
						<input type="text" id="bjmc" name="bjmc" readonly="readonly" value="<bean:write name="rs" property="bjmc"/>"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							入学日期
						</div>
					</th>
					<td>
						<%--<bean:write name="rs" property="rxrq"/>--%>
						<input type="text" id="rxrq" name="rxrq" readonly="readonly" value="<bean:write name="rs" property="rxrq"/>"/>
					</td>
					<th>
						<div>
							毕业时间
						</div>
					</th>
					<td>
						<%--<bean:write name="rs" property="byny"/>--%>
						<input type="text" id="byny" name="byny" readonly="readonly" value="<bean:write name="rs" property="byny"/>"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							学制
						</div>
					</th>
					<td>
						<%--<bean:write name="rs" property="xz"/>--%>
						<input type="text" id="xz" name="xz" readonly="readonly" value="<bean:write name="rs" property="xz"/>"/>
					</td>
					<th>
						<div>
							家庭联系电话（固话）
						</div>
					</th>
					<td>
						<%--<bean:write name="rs" property="lxdh"/>--%>
						<input type="text" id="lxdh" name="lxdh" readonly="readonly" value="<bean:write name="rs" property="lxdh"/>"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
						本人手机号码
						</div>
					</th>
					<td>
						<%--<bean:write name="rs" property="sjhm"/>--%>
						<input type="text" id="sjhm" name="sjhm" readonly="readonly" value="<bean:write name="rs" property="sjhm"/>"/>
					</td>
					<th>
						<div>
							<font color="red">*</font>身份证号
						</div>
					</th>
					<td>
						<%--<bean:write name="rs" property="sfzh"/>--%>
						<input type="text" id="sfzh" name="sfzh" readonly="readonly" value="<bean:write name="rs" property="sfzh"/>"/>
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
						<%--<bean:write name="rs" property="jtyb"/>--%>
						<input type="text" id="jtyb" name="jtyb" readonly="readonly" value="<bean:write name="rs" property="jtyb"/>"/>
					</td>
					<th>
						<div>
							身份证有效终止日期
						</div>
					</th>
					<td>
						<input type="text" id="sfzyxzzrq" name="sfzyxzzrq"
							value="<bean:write name="rs" property="sfzyxzzrq"/>" 
							readonly="readonly" onclick="return showCalendar('sfzyxzzrq','ymmdd');"/>
					</td>
				</tr>
				<tr>
					<th>
						<div>
							家庭详细地址
						</div>
					</th>
					<td colspan="3">
						<%--<bean:write name="rs" property="jtdz"/>--%>
						<input type="text" id="jtdz" name="jtdz" readonly="readonly" value="<bean:write name="rs" property="jtdz"/>"/>
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
						<input type="text" id="lxdh2" name="lxdh2" maxlength="14"
						 value="<bean:write name="rs" property="lxdh2"/>" 
							onkeyup="value=value.replace(/[^\d]/g,'')" />
					</td>
					<th>
						<div>
							家庭联系电话3
						</div>
					</th>
					<td>
						<input type="text" id="lxdh3" name="lxdh3" maxlength="14"
						 value="<bean:write name="rs" property="lxdh3"/>"  
							onkeyup="value=value.replace(/[^\d]/g,'')" />
					</td>
				</tr>
				<tr>
					<th>
						<div>
							QQ
						</div>
					</th>
					<td>
						<input type="text" id="qq" maxlength="18" name="qq"
							value="<bean:write name="rs" property="qq"/>"
							onkeyup="value=value.replace(/[^\d]/g,'')" />
					</td>
					<th>
						<div>
							Email
						</div>
					</th>
					<td>
						<input type="text" id="email" maxlength="18" name="email" value="<bean:write name="rs" property="email"/>" />
					</td>
				</tr>
				<tr>
					<th>
						<div>
							父亲姓名
						</div>
					</th>
					<td>
						<input type="text" id="fqxm" maxlength="20" name="fqxm" value="<bean:write name="rs" property="fqxm"/>" />
					</td>
					<th>
						<div>
							父亲身份证号
						</div>
					</th>
					<td>
						<input type="text" id="fqsfzh" maxlength="18" name="fqsfzh"
						 value="<bean:write name="rs" property="fqsfzh"/>" 
							onblur="if(!checkSfzh(this)){this.focus();}" />
					</td>
				</tr>
				<tr>
					<th>
						<div>
							母亲姓名
						</div>
					</th>
					<td>
						<input type="text" id="mqxm" maxlength="20" name="mqxm" value="<bean:write name="rs" property="mqxm"/>" />
					</td>
					<th>
						<div>
							母亲身份证号
						</div>
					</th>
					<td>
						<input type="text" id="mqsfzh" name="mqsfzh" maxlength="18"
						 value="<bean:write name="rs" property="mqsfzh"/>" 
							onblur="if(!checkSfzh(this)){this.focus();}" />
					</td>
				</tr>
				<tr>
					<th>
							贷款合计
					</th>
					<td>
						<input type="text" id="dkhj" name="dkhj" maxlength="10"
							onkeyup="value=value.replace(/[^\d]/g,'')" value="<bean:write name="rs" property="dkhj"/>" 
							/>
					</td>
					<th>
						第一学年学习费用合计
					</th>
					<td>
						<input type="text" id="xq1" name="xq1" maxlength="10"
							onkeyup="value=value.replace(/[^\d]/g,'')" value="<bean:write name="rs" property="xq1"/>"/>
					</td>
				</tr>
				<tr>
					<th>
						第二学年学习费用合计
					</th>
					<td>
						<input type="text" id="xq2" name="xq2" maxlength="10"
							onkeyup="value=value.replace(/[^\d]/g,'')" value="<bean:write name="rs" property="xq2"/>" 
							/>
					</td>
					<th>
						第三学年学习费用合计
					</th>
					<td>
						<input type="text" id="xq3" name="xq3" maxlength="10"
							onkeyup="value=value.replace(/[^\d]/g,'')" value="<bean:write name="rs" property="xq3"/>" 
							/>
					</td>
				</tr>
				<tr>
					<th>
						第四学年学习费用合计
					</th>
					<td>
						<input type="text" id="xq4" name="xq4" maxlength="10"
							onkeyup="value=value.replace(/[^\d]/g,'')" value="<bean:write name="rs" property="xq4"/>" 
							/>
					</td>
					<th>
						第五学年学习费用合计
					</th>
					<td>
						<input type="text" id="xq5" name="xq5" maxlength="10"
							value="<bean:write name="rs" property="xq5"/>" 
							onkeyup="value=value.replace(/[^\d]/g,'')" 
							/>
					</td>
				</tr>
				<tr>
					<th>
						学费金额
					</th>
					<td>
						<input type="text" id="xfje" name="xfje" maxlength="10"
							onkeyup="value=value.replace(/[^\d]/g,'')"  
							value="<bean:write name="rs" property="xfje"/>" 
							/>
					</td>
					<th>
						住宿费金额
					</th>
					<td>
						<input type="text" id="zsfje" name="zsfje" maxlength="10"
							onkeyup="value=value.replace(/[^\d]/g,'')" value="<bean:write name="rs" property="zsfje"/>" 
							/>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4">
			        	<div class="bz">
							 <logic:equal name="sfksq" value="1">
					        	"<span class="red">*</span>"为必填项
					        	
					        	<logic:equal value="xy" name="userType">
			          				<logic:equal name="rs" property="xxsh" value="通过"> 
			          					&nbsp;&nbsp;<span class="red"><bean:message key="lable.xb" />用户不能修改学校用户审核通过的信息！</span>
			          				</logic:equal>
			          			</logic:equal>
					         </logic:equal>
					          
					      	  <logic:equal name="sfksq" value="-1">
					        		<span class="red">现在不在申请时间内！</span>
					          </logic:equal>
						</div>
			          <div class="btn">
			         	<logic:equal name="sfksq" value="1">
			          		<logic:equal value="xy" name="userType">
			          			<logic:notEqual name="rs" property="xxsh" value="通过"> 
			          				<button type="button" name="提交" onclick="yz();">保存</button>
			          			</logic:notEqual>
			          		</logic:equal>
			          		<logic:notEqual value="xy" name="userType">
			          			<button type="button" name="提交" onclick="yz();">保存</button>
			          		</logic:notEqual>
			          	</logic:equal>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
		</html:form>
</body>
</html>
