<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<%@ include file="/syscommon/pagehead_V4.ini"%>
<head>
	<base target="_self" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var hkczh = document.getElementById('hkczh').value;
			
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			if((hkczh == null) || (hkczh == "")){
				alert("还款帐号不能为空!");
				return false;
			}
			
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=hkxysqSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//输出相应的打印页面
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=hkxysqb";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<div class="tab_cur">
		<p class="location">
			<em>您的当前位置:</em><a>${title }</a>
		</p>
	</div>
		<html:form action="zgdzdx_xszz.do?method=hkxysq" method="post">
			<input type="hidden" id="url" name="url"
				value="/zgdzdx_xszz.do?method=hkxysq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="rs" property="pkVal" />">

			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("保存成功！");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("保存失败！");
	         	</script>
				</logic:match>
			</logic:present>
			<logic:present name="isNull">
				<logic:match value="is" name="isNull">
					<script language="javascript">
	         			alert("您没有审核通过的国家助学贷款信息！");
	         		</script>
				</logic:match>
			</logic:present>
			
			
		
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>
			        		<logic:equal name="sfksq" value="-1">
										现在不在申请时间内!
							</logic:equal>
							<logic:notEqual name="sfksq" value="-1">
			        			还款协议
			        		</logic:notEqual>
			        		</span>
			        	</th>
			        </tr>
			    </thead>
				 <tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
				          <logic:equal name="sfksq" value="1">
							<div class="btn" id="btn" >
							<logic:equal name="rs" property="xyshjg" value="通过">
								<button type="button"  onClick="yz();" disabled="disabled">
									提&nbsp;&nbsp;&nbsp;&nbsp;交
								</button>
							</logic:equal>
							<logic:notEqual name="rs" property="xyshjg" value="通过">
								<button type="button"  onClick="yz();">
									提&nbsp;&nbsp;&nbsp;&nbsp;交
								</button>
							</logic:notEqual>
							<logic:notEqual name="sfkdy" value="0">
								<button type="button"  onClick="toPrintOut();">
									打&nbsp;&nbsp;&nbsp;&nbsp;印
								</button>
							</logic:notEqual>
							<logic:equal name="sfkdy" value="0">
								<button type="button"  onClick="toPrintOut();" disabled="disabled">
									打&nbsp;&nbsp;&nbsp;&nbsp;印
								</button>
							</logic:equal>
							</div>
						</logic:equal>
						</td>
				      </tr>
			 </tfoot>
			<tbody>
				<tr>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<th align="center" width="16%">
							<font color="red">*</font>学号
						</th>
						<td align="left" width="34%">
							<html:text name='rs' property="xh" styleId="xh"
								readonly="readonly"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								 id="buttonFindStu">
								选择
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<th align="center" width="16%">
							<font color="red">*</font>学号
						</th>
						<td align="left" width="34%">
							<input type="text" id="xh" name="xh"
								style="width:100%;heigh:100%"
								value="<bean:write name='rs' property="xh" />" readonly="true">
						</td>
					</logic:equal>
					<th width="16%">
							姓名
					</th>
					<td width="34%">
						<input type="text" readonly="readonly" id="xm" name="xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xm"/>" readonly="readonly">
					</td>
				</tr>
				<tr>
					<th>
							行政班号
					</th>
					<td>
						<input type="text" id="bjmc" readonly="readonly" name="bjmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
					<th>
							身份证号
					</th>
					<td>
						<input type="text" id="sfzh" readonly="readonly" name="sfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
				</tr>
				<tr>
					<th>
							家庭地址
					</th>
					<td>
						<input type="text" id="jtxxzz" readonly="readonly" name="jtxxzz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtxxzz"/>">
					</td>
					<th>
							家庭邮编
					</th>
					<td>
						<input type="text" id="jtyb" name="jtyb" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtyb"/>">
					</td>
				</tr>
				<tr>
					<th>
							工作单位
					</th>
					<td>
						<input type="text" id="gzdw" name="gzdw" maxlength="50"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="gzdw"/>">
					</td>
					<th>
							单位邮编
					</th>
					<td>
						<input type="text" id="dwyb" name="dwyb" maxlength="6"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dwyb"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th>
							联系电话
					</th>
					<td>
						<input type="text" id="yddh" maxlength="20" name="yddh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yddh"/>">
					</td>
					<th>
							合同号
					</th>
					<td>
						<input type="text" id="htbh" name="htbh" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="htbh"/>">
					</td>
				</tr>
				<tr>
					<th>
							实际发放金额
					</th>
					<td>
						<input type="text" id="sjffje" readonly="readonly" name="sjffje"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sjffje"/>">
						<input type="hidden" id="sjffje_dx" name="sjffje_dx"
								value="<bean:write name="rs" property="sjffje_dx" />">
					</td>
					<th>
							<font color="red">*</font>还款账号<br />
							<font color="red">(请填写中行卡下方一排数字)</font>
					</th>
					<td>
						<input type="text" id="hkczh" name="hkczh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hkczh"/>">
					</td>
				</tr>
				<tr>
					<th>
							贷款账号
					</th>
					<td>
						<input type="text" id="khh" readonly="readonly" name="khh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="khh"/>">
					</td>
					<th>
							是否毕业
					</th>
					<td>
						<input type="text" id="sfby" name="sfby" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfby"/>">
					</td>
				</tr>
				</tbody>
			</table>
		</div>
		</html:form>
</body>
</html>
