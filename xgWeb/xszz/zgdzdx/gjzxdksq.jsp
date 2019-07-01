<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>

	<!-- 头文件 -->
	<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var csrq = document.getElementById('csrq').value;
			var ssdh = document.getElementById('ssdh').value;
			var jtxxzz = document.getElementById('jtxxzz').value;
			var jtyb = document.getElementById('jtyb').value;
			var hkczh = document.getElementById('hkczh').value;
			var zddkje = document.getElementById('zddkje').value;
			var sqdkje = document.getElementById('sqdkje').value;
			var dkqx = $('dkqx').value;
			
			if ('' == dkqx){
				alert('贷款期限不能为空！');
				return false;
			}
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			if((csrq == null) || (csrq == "")){
				alert("出生日期不能为空!");
				return false;
			}
			if((ssdh == null) || (ssdh == "")){
				alert("宿舍电话不能为空!");
				return false;
			}
			if((jtxxzz == null) || (jtxxzz == "")){
				alert("家庭详细住址不能为空!");
				return false;
			}
			if((jtyb == null) || (jtyb == "")){
				alert("家庭邮编不能为空!");
				return false;
			}
			if((hkczh == null) || (hkczh == "")){
				alert("还款帐号不能为空!");
				return false;
			}
			if (''==sqdkje || 0==sqdkje){
				alert('贷款总金额不能为0！');
				return false;
			}
			confirmInfo("是否要保存当前数据？",tjsq);
			
		}
		
		function tjsq(tag){
			if(tag=="ok"){
				document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=gjzxdksqSave";
				document.forms[0].submit();
			}
		}
		
		function hm(){
			var hkczh = document.getElementById('hkczh').value;
			if(hkczh.length != 18){
				alert("还款帐号号码非法!");
				return false;
			}
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		
		function setFkzje() {
			var je1=0;
			var je2=0;
			var je3=0;
			var je4=0;
			var je5=0;
			var sqdkje = $('sqdkje').value;
			
			if ($('je1')) {
				je1 = Number($('je1').value);
			}
			if ($('je2')) {
				je2 = Number($('je2').value);
			}
			if ($('je3')) {
				je3 = Number($('je3').value);
			}
			if ($('je4')) {
				je4 = Number($('je4').value);
			}
			if ($('je5')) {
				je5 =Number( $('je5').value);
			}
			$('sqdkje').value = je1+je2+je3+je4+je5;
		}
	</script>
</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<html:form action="zgdzdx_xszz.do?method=gjzxdksq" method="post">
			<input type="hidden" id="url" name="url"
				value="/zgdzdx_xszz.do?method=gjzxdksq" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />
			<input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="rs" property="pkVal" />">
			<input type="hidden" id="shjg" name="shjg"
				value="<bean:write name="rs" property="shjg" />">
			<input type="hidden" id="zddkje" name="zddkje"
				value="<bean:write name="rs" property="zddkje" />">
			<input type="hidden" id="fkcs" value="${fkcs }" />


			<logic:present name="isPASS">
				<logic:match value="is" name="isPASS">
					<script language="javascript">
	         			alert("已通过审核，不能申请！");
	         		</script>
				</logic:match>
			</logic:present>
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
				   <thead>
				    	<tr>
				    		<logic:notEqual value="update" name="doType">
				    			<logic:notEqual   name="doType" value="view">
						    		<logic:equal name="sfksq" value="false">
												<th colspan="4"><span>现在不在申请时间内或已有生源地贷款！！！</span></th>
									</logic:equal>
									<logic:notEqual name="sfksq" value="false">
									<th colspan="4"><span>国家助学贷款申请</span></th>
									</logic:notEqual>
								</logic:notEqual>
								<logic:equal name="doType" value="view">
									<th colspan="4"><span>国家助学贷款申请</span></th>
								</logic:equal>
							</logic:notEqual>
							<logic:equal value="update" name="doType">
								<th colspan="4"><span>国家助学贷款申请</span></th>
							</logic:equal>
				        </tr>
				    </thead>
			  		<tfoot>
				      <tr>
				          	<logic:equal name="sfksq" value="true">
								<td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
							     <div class="btn">
										<button type="button"  onclick="yz();">
											提交申请
										</button>
								</div>
								</td>
							</logic:equal>
							<logic:equal value="update" name="doType">
								<td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button"  onclick="yz();">
										提交申请
									</button>
								</div>
								</td>
							</logic:equal>
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
						<th width="16%" align="center">
							姓名
						</th>
						<td width="34%">
							<input type="text" readonly="readonly" id="xm" name="xm"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="xm"/>"
								readonly="readonly">
						</td>
					</tr>
					<tr>
						<th align="center">
							性别
						</th>
						<td>
							<input type="text" id="xb" readonly="readonly" name="xb"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="xb"/>">
						</td>
						<th align="center">
							<font color="red">*</font>出生日期
						</th>
						<td>
							<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('csrq','y-mm-dd');"
								value="<bean:write name='rs' property="csrq" />" name="csrq"
								id="csrq" />
						</td>
					</tr>
					<tr>
						<th align="center">
							民族
						</th>
						<td>
							<input type="text" id="mzmc" readonly="readonly" name="mzmc"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="mzmc"/>">
						</td>
						<th align="center">
							毕业年月
						</th>
						<td>
							<input type="text" id="byny" readonly="readonly" name="byny"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="byny"/>">
						</td>
					</tr>
					<tr>
						<th align="center">
							身份证号
						</th>
						<td>
							<input type="text" id="sfzh" name="sfzh" readonly="readonly"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="sfzh"/>">
						</td>
						<th align="center">
							<font color="red">*</font>宿舍电话
							<br />
							<font color="red">(也可以是手机)</font>
						</th>
						<td>
							<input type="text" id="ssdh" name="ssdh" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="ssdh"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<th align="center">
							学制
						</th>
						<td>
							<input type="text" id="xz" name="xz" readonly="readonly"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="xz"/>">
						</td>
						<th align="center">
							学历
						</th>
						<td>
							<input type="text" id="xl" name="xl" readonly="readonly"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="xl"/>">
						</td>
					</tr>
					<tr>
						<th align="center">
							年级
						</th>
						<td>
							<input type="text" id="nj" readonly="readonly" name="nj"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="nj"/>">
						</td>
						<th align="center">
							<bean:message key="lable.xb" />名称
						</th>
						<td>
							<input type="text" id="xymc" name="xymc" readonly="readonly"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="xymc"/>">
						</td>
					</tr>
					<tr>
						<th align="center">
							专业名称
						</th>
						<td>
							<input type="text" id="zymc" readonly="readonly" name="zymc"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="zymc"/>">
						</td>
						<th align="center">
							行政班号
						</th>
						<td>
							<input type="text" id="bjmc" name="bjmc" readonly="readonly"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="bjmc"/>">
						</td>
					</tr>
					<tr>
						<th align="center">
							E-Mail与QQ
						</th>
						<td>
							<input type="text" id="emailqq" name="emailqq" maxlength="100"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="emailqq"/>">
						</td>
						<th align="center">
							<font color="red">*</font>还款账号
							<br />
							<font color="red">(请填写中行卡下方一排数字)</font>
						</th>
						<td>
							<input type="text" id="hkczh" name="hkczh" maxlength="18"
								style="width:100%;heigh:100%" onblur="hm();"
								value="<bean:write name="rs" property="hkczh"/>">
						</td>
					</tr>
					<tr>
						<th align="center">
							区域经济状况
						</th>
						<td>
							<html:select name="rs" property="qyjjzk"
								style="width:100%;heigh:100%">
								<html:option value="经济欠发达">经济欠发达</html:option>
								<html:option value="经济状况一般">经济状况一般</html:option>
								<html:option value="经济发达">经济发达</html:option>
							</html:select>
						</td>
						<th align="center">
							是否为放款银行当地居民
						</th>
						<td align="center">
							<logic:present name="rs" property="sfwfkyhddjm">
								<logic:match value="是" name="rs" property="sfwfkyhddjm">
									<input type="radio" name="sfwfkyhddjm" value="是" checked>&nbsp;是
							    <input type="radio" name="sfwfkyhddjm" value="否">&nbsp;否
							</logic:match>
								<logic:match value="否" name="rs" property="sfwfkyhddjm">
									<input type="radio" name="sfwfkyhddjm" value="是">&nbsp;是
							    <input type="radio" name="sfwfkyhddjm" value="否" checked>&nbsp;否
							</logic:match>
							</logic:present>
							<logic:notPresent name="rs" property="sfwfkyhddjm">
								<input type="radio" name="sfwfkyhddjm" value="是" checked>&nbsp;是
							<input type="radio" name="sfwfkyhddjm" value="否">&nbsp;否
						</logic:notPresent>
						</td>
					</tr>
					<tr>
						<th align="center">
							贷款期限(月)
						</th>
						<td>
							<input type="text" id="dkqxy" readonly="readonly" name="dkqxy"
								style="width:100%;heigh:100%"
								value="${rs.dkqxy }">
						</td>
						<th align="center">
							<font color="red">*</font>贷款期限
						</th>
						<td>
							<input type="text" id="dkqx" name="dkqx" readonly="readonly"
								style="width:100%;heigh:100%"
								value="${rs.dkqx }">
						</td>
					</tr>
					<tr>
						<th align="center">
							<font color="red">*</font>贷款总金额
						</th>
						<td>
							<input type="text" id="sqdkje" name="sqdkje" readonly="true"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="sqdkje"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onblur="setFkzje();">
						</td>
						<td colspan="2">
						</td>
					</tr>
					<logic:notEqual value="update" name="doType">
						<logic:notEqual value="view" name="doType">
					<tr>
						<th align="center" style="display:none" id="fk_xn1">
							第一次放款金额
						</th>
						<td style="display:none" id="fk_xn1_je">
							<html:text property="fk_xn1_je" maxlength="7" style="width:100%"
								onblur="checkMoney(this);setFkzje();" styleId="je1" value="${rs.fk_xn1_je}"></html:text>
						</td>
						<th align="center" style="display:none" id="fk_xn2">
							第二次放款金额
						</th>
						<td style="display:none" id="fk_xn2_je">
							<html:text property="fk_xn2_je" maxlength="7" style="width:100%"
								onblur="checkMoney(this);setFkzje();" styleId="je2" value="${rs.fk_xn2_je}"></html:text>
						</td>
					</tr>
					<tr>
						<th align="center" style="display:none" id="fk_xn3">
							第三次放款金额
						</th>
						<td style="display:none" id="fk_xn3_je">
							<html:text property="fk_xn3_je" maxlength="7" style="width:100%"
								onblur="checkMoney(this);setFkzje();" styleId="je3" value="${rs.fk_xn3_je}"></html:text>
						</td>
						<th align="center" style="display:none" id="fk_xn4">
							第四次放款金额
						</th>
						<td style="display:none" id="fk_xn4_je">
							<html:text property="fk_xn4_je" maxlength="7" style="width:100%"
								onblur="checkMoney(this);setFkzje();" styleId="je4" value="${rs.fk_xn4_je}"></html:text>
						</td>
					</tr>
						<logic:equal value="5" name="rs" property="xz" >
						<tr style="display:none" id="fk_xn5_je">
							<th align="center">
								第五次放款金额
							</th>
							<td>
								<html:text property="fk_xn5_je" maxlength="7" style="width:100%"
									onblur="checkMoney(this);setFkzje();" styleId="je5" value="${rs.fk_xn5_je}">
								</html:text>
							</td>
							<td align="right">
							</td>
							<td>
	
							</td>
						</tr>
						</logic:equal>
					</logic:notEqual>
					</logic:notEqual>
					<logic:equal value="update" name="doType">
					<tr>
						<th align="center"  id="fk_xn1">
							第一次放款金额
						</th>
						<td  id="fk_xn1_je">
							<html:text property="fk_xn1_je" maxlength="7" style="width:100%"
								onblur="checkMoney(this);setFkzje();" styleId="je1" value="${rs.fk_xn1_je}"></html:text>
						</td>
						<th align="center"  id="fk_xn2">
							第二次放款金额
						</th>
						<td  id="fk_xn2_je">
							<html:text property="fk_xn2_je" maxlength="7" style="width:100%"
								onblur="checkMoney(this);setFkzje();" styleId="je2" value="${rs.fk_xn2_je}"></html:text>
						</td>
					</tr>
					<tr>
						<th align="center" id="fk_xn3">
							第三次放款金额
						</th>
						<td  id="fk_xn3_je">
							<html:text property="fk_xn3_je" maxlength="7" style="width:100%"
								onblur="checkMoney(this);setFkzje();" styleId="je3" value="${rs.fk_xn3_je}"></html:text>
						</td>
						<th align="center" id="fk_xn4">
							第四次放款金额
						</th>
						<td  id="fk_xn4_je">
							<html:text property="fk_xn4_je" maxlength="7" style="width:100%"
								onblur="checkMoney(this);setFkzje();" styleId="je4" value="${rs.fk_xn4_je}"></html:text>
						</td>
					</tr>
					<logic:equal value="5" name="rs" property="xz" >
						<tr id="fk_xn5_je">
							<th align="center">
								第五次放款金额
							</th>
							<td>
								<html:text property="fk_xn5_je" maxlength="7" style="width:100%"
									onblur="checkMoney(this);setFkzje();" styleId="je5" value="${rs.fk_xn5_je}">
								</html:text>
							</td>
							<td align="right">
							</td>
							<td>
	
							</td>
						</tr>
					</logic:equal>
					</logic:equal>
					<logic:equal value="view" name="doType">
					<tr>
						<th align="center"  id="fk_xn1">
							第一次放款金额
						</th>
						<td  id="fk_xn1_je">
							<html:text property="fk_xn1_je" maxlength="7" style="width:100%"
								onblur="checkMoney(this)" styleId="je1" value="${rs.fk_xn1_je}"></html:text>
						</td>
						<th align="center"  id="fk_xn2">
							第二次放款金额
						</th>
						<td  id="fk_xn2_je">
							<html:text property="fk_xn2_je" maxlength="7" style="width:100%"
								onblur="checkMoney(this)" styleId="je2" value="${rs.fk_xn2_je}"></html:text>
						</td>
					</tr>
					<tr>
						<th align="center" id="fk_xn3">
							第三次放款金额
						</th>
						<td  id="fk_xn3_je">
							<html:text property="fk_xn3_je" maxlength="7" style="width:100%"
								onblur="checkMoney(this)" styleId="je3" value="${rs.fk_xn3_je}"></html:text>
						</td>
						<th align="center" id="fk_xn4">
							第四次放款金额
						</th>
						<td  id="fk_xn4_je">
							<html:text property="fk_xn4_je" maxlength="7" style="width:100%"
								onblur="checkMoney(this)" styleId="je4" value="${rs.fk_xn4_je}"></html:text>
						</td>
					</tr>
						<logic:equal value="5" name="rs" property="xz" >
						<tr id="fk_xn5_je">
							<th align="center">
								第五次放款金额
							</th>
							<td>
								<html:text property="fk_xn5_je" maxlength="7" style="width:100%"
									onblur="checkMoney(this)" styleId="je5" value="${rs.fk_xn5_je}">
								</html:text>
							</td>
							<td align="right">
							</td>
							<td>
	
							</td>
						</tr>
						</logic:equal>
					</logic:equal>
					<tr>
						<th align="center">
							每年最大贷款金额
						</th>
						<td>
							<input type="text" id="mndkje" name="mndkje"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="mndkje"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
						<th align="center">
							家庭月均收入
						</th>
						<td>
							<input type="text" id="jtysr" name="jtysr" maxlength="6"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtysr"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
					</tr>
					<tr>
						<th align="center">
							<font color="red">*</font>家庭详细地址
						</th>
						<td colspan="3">
							<input type="text" id="jtxxzz" name="jtxxzz" maxlength="200"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtxxzz"/>">
						</td>
					</tr>
					<tr>
						<th align="center">
							<font color="red">*</font>家庭邮编
						</th>
						<td>
							<input type="text" id="jtyb" maxlength="6" name="jtyb"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jtyb"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</td>
						<th align="center">
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
						<th align="center">
							父亲姓名
						</th>
						<td>
							<input type="text" id="fqxm" maxlength="40" name="fqxm"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fqxm"/>">
						</td>
						<th align="center">
							母亲姓名
						</th>
						<td>
							<input type="text" id="mqxm" name="mqxm" maxlength="40"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="mqxm"/>">
						</td>
					</tr>
					<tr>
						<th align="center">
							父亲职业
						</th>
						<td>
							<input type="text" id="fqzy" maxlength="40" name="fqzy"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fqzy"/>">
						</td>
						<th align="center">
							母亲职业
						</th>
						<td>
							<input type="text" id="mqzy" name="mqzy" maxlength="40"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="mqzy"/>">
						</td>
					</tr>
					<tr>
						<th align="center">
							父亲身份证号
						</th>
						<td>
							<input type="text" id="fqsfzh" maxlength="18" name="fqsfzh"
								style="width:100%;heigh:100%" onkeyup="value=value.replace(/[^\d]/g,'')"
								value="<bean:write name="rs" property="fqsfzh"/>">
						</td>
						<th align="center">
							母亲身份证号
						</th>
						<td>
							<input type="text" id="mqsfzh" name="mqsfzh" maxlength="18"
								style="width:100%;heigh:100%" onkeyup="value=value.replace(/[^\d]/g,'')"
								value="<bean:write name="rs" property="mqsfzh"/>">
						</td>
					</tr>
					<tr>
						<th align="center">
							备注
							<br />
							(未成年人在此注明)
						</th>
						<td colspan="3">
							<input type="text" id="bz" name="bz" maxlength="200"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="bz"/>">
						</td>
					</tr>
					</tbody>
					<logic:equal name="isQuery" value="is">
						<tfoot>
							 <tr>
							 <td colspan="4">
						          		<div class="btn">
											<button type="button" onclick="window.close();return false;" id="buttonClose">
												关 闭
											</button>      
						         		 </div>
						          </td>
							 </tr>
						</tfoot>
					</logic:equal>
				</table>
			</div>
		</html:form>

		<script type="text/javascript">
		
			var fkcs = $('fkcs').value;
			
			if (fkcs==5) {
				$('fk_xn1').style.display='';
				$('fk_xn1_je').style.display=''; 
				$('fk_xn2').style.display='';
				$('fk_xn2_je').style.display='';
				$('fk_xn3').style.display='';
				$('fk_xn3_je').style.display='';
				$('fk_xn4').style.display='';
				$('fk_xn4_je').style.display='';
				$('fk_xn5_je').style.display='';
			} else if (fkcs==4){
				$('fk_xn1').style.display='';
				$('fk_xn1_je').style.display='';
				$('fk_xn2').style.display='';
				$('fk_xn2_je').style.display='';
				$('fk_xn3').style.display='';
				$('fk_xn3_je').style.display='';
				$('fk_xn4').style.display='';
				$('fk_xn4_je').style.display='';
			}else if (fkcs==3){
				$('fk_xn1').style.display='';
				$('fk_xn1_je').style.display='';
				$('fk_xn2').style.display='';
				$('fk_xn2_je').style.display='';
				$('fk_xn3').style.display='';
				$('fk_xn3_je').style.display='';
			}else if (fkcs==2){
				$('fk_xn1').style.display='';
				$('fk_xn1_je').style.display='';
				$('fk_xn2').style.display='';
				$('fk_xn2_je').style.display='';
			}else if (fkcs==1){
				$('fk_xn1').style.display='';
				$('fk_xn1_je').style.display='';
			}
		</script>

		<logic:present name="ok">
			<logic:match value="ok" name="ok">
				<script language="javascript">
	         	alert("保存成功！");
	         	Close();
	         	dialogArgumentsQueryChick();
	         	</script>
			</logic:match>
			<logic:match value="no" name="ok">
				<script language="javascript">
	         	alert("保存失败！");
	         	</script>
			</logic:match>
		</logic:present>
	</body>
</html>
    