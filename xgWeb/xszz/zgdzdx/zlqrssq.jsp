<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self" />
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=zlqrssqSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//输出相应的打印页面
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=zlqrssqb";
			document.forms[0].submit();
		}
		function chengHy(){
			var hyzk = document.getElementById('hyzk').value;
			
			if ("未婚"==hyzk){
				document.getElementById('pomc').value="";
				document.getElementById('polxdh').value="";
				document.getElementById('pomc').disabled="true";
				document.getElementById('polxdh').disabled="true";
			} else {
				document.getElementById('pomc').disabled="";
				document.getElementById('polxdh').disabled="";
			}
		}
	</script>
</head>

<body>
	<div class="tab_cur">
		<p class="location">
			<em>您的当前位置:</em><a>${title }</a>
		</p>
	</div>
		<html:form action="zgdzdx_xszz.do?method=zlqrssq" method="post">
			<input type="hidden" id="url" name="url"
				value="/zgdzdx_xszz.do?method=zlqrssq" />
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
			        	毕业生资料确认书
			        	</logic:notEqual>
			        	</span></th>
			        </tr>
			    </thead>
			 <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <logic:equal name="sfksq" value="1">
						<div class="btn">
						<button type="button" class="button2" onClick="yz();">
							提&nbsp;&nbsp;&nbsp;&nbsp;交
						</button>
						<logic:equal name="userType" value="stu">
							<logic:equal name="rs" property="sftxzlqrs" value="是">
								<button type="button" class="button2" onClick="toPrintOut();">
								打&nbsp;&nbsp;&nbsp;&nbsp;印
								</button>
							</logic:equal>
						</logic:equal>
						<logic:notEqual name="userType" value="stu">
						<button type="button" class="button2" onClick="toPrintOut();">
							打&nbsp;&nbsp;&nbsp;&nbsp;印
						</button>
						</logic:notEqual>
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
								class="btn_01" id="buttonFindStu">
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
							性别
					</th>
					<td>
						<input type="text" id="xb" readonly="readonly" name="xb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xb"/>">
					</td>
					<th>
							出生日期
					</th>
					<td>
						<input type="text" readonly style="cursor:hand;width:100%"
							onclick="return showCalendar('csrq','y-mm-dd');"
							value="<bean:write name='rs' property="csrq" />" name="csrq"
							id="csrq" />
					</td>
				</tr>
				<tr>
					<th>
							民族
					</th>
					<td>
						<input type="text" id="mzmc" readonly="readonly" name="mzmc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzmc"/>">
					</td>
					<th>
							身份证号
					</th>
					<td>
						<input type="text" id="sfzh" name="sfzh" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
				</tr>
				<tr>
					<th>
							学制
					</th>
					<td>
						<input type="text" id="xz" name="xz" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xz"/>">
					</td>
					<th>
							学历
					</th>
					<td>
						<input type="text" id="xl" name="xl" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xl"/>">
					</td>
				</tr>
				<tr>
					<th>
							年级
					</th>
					<td>
						<input type="text" id="nj" readonly="readonly" name="nj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nj"/>">
					</td>
					<th>
							<bean:message key="lable.xsgzyxpzxy" />名称
					</th>
					<td>
						<input type="text" id="xymc" name="xymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
					</td>
				</tr>
				<tr>
					<th>
							专业名称
					</th>
					<td>
						<input type="text" id="zymc" readonly="readonly" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
					</td>
					<th>
							行政班号
					</th>
					<td>
						<input type="text" id="bjmc" name="bjmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
				</tr>
				<tr>
					<th>
							E-Mail与QQ
					</th>
					<td>
						<input type="text" id="emailqq" name="emailqq" maxlength="100"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="emailqq"/>">
					</td>
					<th>
							合同编号
					</th>
					<td>
						<input type="text" id="htbh" name="htbh" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="htbh"/>">
					</td>
				</tr>
				<tr>
					<th>
							婚姻状况
					</th>
					<td>
						<html:select name="rs" property="hyzk" style="width:60px;heigh:100%" onchange="chengHy();">
							<html:option value="未婚">未婚</html:option>
							<html:option value="已婚">已婚</html:option>
							<html:option value="离异">离异</html:option>
							<html:option value="丧偶">丧偶</html:option>
						</html:select>
					</td>
					<th>
							配偶姓名
					</th>
					<td>
						<input type="text" id="pomc" name="pomc" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="pomc"/>">
					</td>
				</tr>
				<tr>
					<th>
							配偶联系电话
					</th>
					<td>
						<input type="text" id="polxdh" maxlength="20" name="polxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="polxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
							工作单位<br />
							<font color="red">(借款人工作单位)
							<br />(考上研究生同学填继续攻读学校名称)
							</font>
					</th>
					<td>
						<input type="text" id="gzdw" name="gzdw" maxlength="50"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="gzdw"/>">
					</td>
				</tr>
				<tr>
					<th>
							单位电话
					</th>
					<td>
						<input type="text" id="dwdh" maxlength="20" name="dwdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dwdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
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
							单位地址
					</th>
					<td>
						<input type="text" id="dwdz" maxlength="200" name="dwdz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dwdz"/>">
					</td>
					<th>
							移动电话
					</th>
					<td>
						<input type="text" id="yddh" name="yddh" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yddh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th>
							联系人姓名<br />
							<font color="red">(除父母之外的联系人)</font>
					</th>
					<td>
						<input type="text" id="lxrxm" maxlength="40" name="lxrxm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxrxm"/>">
					</td>
					<th>
							联系人性别
					</th>
					<td>
						<html:select name="rs" property="lxrxb" style="width:40px;heigh:100%">
							<html:option value=""></html:option>
							<html:option value="男">男</html:option>
							<html:option value="女">女</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
							出生日期
					</th>
					<td>
						<input type="text" readonly style="cursor:hand;width:100%"
							onclick="return showCalendar('lxrcsrq','y-mm-dd');"
							value="<bean:write name='rs' property="lxrcsrq" />" name="lxrcsrq"
							id="lxrcsrq" />
					</td>
					<th>
							与借款人关系
					</th>
					<td>
						<input type="text" id="lxrgx" name="lxrgx" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxrgx"/>">
					</td>
				</tr>
				<tr>
					<th>
							联系电话
					</th>
					<td>
						<input type="text" id="lxrlxdh" maxlength="20" name="lxrlxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxrlxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
							单位/地址<br />
							<font color="red">(联系人)</font>
					</th>
					<td>
						<input type="text" id="lxrdwdz" name="lxrdwdz" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxrdwdz"/>">
					</td>
				</tr>
				<tr>
					<th>
							家庭详细地址
					</th>
					<td colspan="3">
						<input type="text" id="jtxxzz" name="jtxxzz" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtxxzz"/>">
					</td>
				</tr>
				<tr>
					<th>
							家庭邮编
					</th>
					<td>
						<input type="text" id="jtyb" maxlength="6" name="jtyb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtyb"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
							家庭电话
					</th>
					<td>
						<input type="text" id="jtdh" name="jtdh" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtdh"/>">
					</td>
				</tr>
				<tr>
					<th colspan="4">
						<font color="red">注：家庭电话请按“区号-电话号码”格式填写；父母亲已故的请填写“已故”；无职业的，在父母职业处填写“无”。</font>
					</th>
				</tr>
				<tr>
					<th>
							父亲姓名
					</th>
					<td>
						<input type="text" id="fqxm" maxlength="40" name="fqxm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="fqxm"/>">
					</td>
					<th>
							母亲姓名
					</th>
					<td>
						<input type="text" id="mqxm" name="mqxm" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mqxm"/>">
					</td>
				</tr>
				<tr>
					<th>
							父亲职业
					</th>
					<td>
						<input type="text" id="fqzy" maxlength="40" name="fqzy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="fqzy"/>">
					</td>
					<th>
							母亲职业
					</th>
					<td>
						<input type="text" id="mqzy" name="mqzy" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mqzy"/>">
					</td>
				</tr>
				<tr>
					<th>
							父亲身份证号
					</th>
					<td>
						<input type="text" id="fqsfzh" maxlength="18" name="fqsfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="fqsfzh"/>">
					</td>
					<th>
							母亲身份证号
					</th>
					<td>
						<input type="text" id="mqsfzh" name="mqsfzh" maxlength="18"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mqsfzh"/>">
					</td>
				</tr>
				<tr>
					<th>
							备注
					</th>
					<td colspan="3">
						<input type="text" id="bz" name="bz" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bz"/>">
					</td>
				</tr>
				</tbody>
			</table>
	
		</html:form>
</body>

</html>
