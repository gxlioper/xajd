<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<base target="_self"/>
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
	function saveGjzxdk(){
		var xh = document.getElementById('xh').value;
		var sqdkje = document.getElementById('sqdkje').value;
		var fk_xn1_je = document.getElementById('fk_xn1_je').value;
		var fk_xn2_je = document.getElementById('fk_xn2_je').value;
		var fk_xn3_je = document.getElementById('fk_xn3_je').value;
		var fk_xn4_je = document.getElementById('fk_xn4_je').value;
		var fk_xn5_je = '';
		var fk_xn1_sffk = document.getElementById('fk_xn1_sffk').value;
		var fk_xn2_sffk = document.getElementById('fk_xn2_sffk').value;
		var fk_xn3_sffk = document.getElementById('fk_xn3_sffk').value;
		var fk_xn4_sffk = document.getElementById('fk_xn4_sffk').value;
		var fk_xn5_sffk = '';
		var tqhkje = document.getElementById('tqhkje').value;

		if (tqhkje == null || tqhkje == ""){
			tqhkje = "0";
		}
		
		var fkzje = 0;
		
		if (document.getElementById('fk_xn5_sffk')){
			fk_xn5_je = document.getElementById('fk_xn5_je').value;
			fk_xn5_sffk = document.getElementById('fk_xn5_sffk').value;
		}
		
		if (fk_xn1_sffk=="是") {
			fkzje = fkzje + Math.round(fk_xn1_je);
		}
		if (fk_xn2_sffk=="是") {
			fkzje = fkzje + Math.round(fk_xn2_je);
		}
		if (fk_xn3_sffk=="是") {
			fkzje = fkzje + Math.round(fk_xn3_je);
		}
		if (fk_xn4_sffk=="是") {
			fkzje = fkzje + Math.round(fk_xn4_je);
		}
		if (fk_xn5_sffk=="是") {
			fkzje = fkzje + Math.round(fk_xn5_je);
		}
		
		if (fkzje > Math.round(sqdkje)){
			alert("放款总金额已大于总贷款金额!");
			return false;
		}
		if (Math.round(tqhkje) > fkzje) {
			alert("提前还款金额大于放款总金额!");
			return false;
		}
			
		if((xh == null) || (xh == "")){
			alert("学号不能为空!");
			return false;
		}
			
		showTips('处理数据中，请等待......');
		document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=gjzxdkQueryOneSave";
		document.forms[0].submit();
	}
	function je(){
		var sqdkje = document.getElementById('sqdkje').value;
		var fk_xn1_je = document.getElementById('fk_xn1_je').value;
		var fk_xn2_je = document.getElementById('fk_xn2_je').value;
		var fk_xn3_je = document.getElementById('fk_xn3_je').value;
		var fk_xn4_je = document.getElementById('fk_xn4_je').value;
		var fk_xn5_je = '';
		var fk_xn1_sffk = document.getElementById('fk_xn1_sffk').value;
		var fk_xn2_sffk = document.getElementById('fk_xn2_sffk').value;
		var fk_xn3_sffk = document.getElementById('fk_xn3_sffk').value;
		var fk_xn4_sffk = document.getElementById('fk_xn4_sffk').value;
		var fk_xn5_sffk =''; 
		
		var fkzje = 0;
		
		if (document.getElementById('fk_xn5_sffk')){
			fk_xn5_je = document.getElementById('fk_xn5_je').value
			fk_xn5_sffk = document.getElementById('fk_xn5_sffk').value;
		}
		
		if (fk_xn1_sffk=="是") {
			fkzje = fkzje + Math.round(fk_xn1_je);
		}
		if (fk_xn2_sffk=="是") {
			fkzje = fkzje + Math.round(fk_xn2_je);
		}
		if (fk_xn3_sffk=="是") {
			fkzje = fkzje + Math.round(fk_xn3_je);
		}
		if (fk_xn4_sffk=="是") {
			fkzje = fkzje + Math.round(fk_xn4_je);
		}
		if (fk_xn5_sffk=="是") {
			fkzje = fkzje + Math.round(fk_xn5_je);
		}
		
		if (fkzje > Math.round(sqdkje)){
			alert("放款总金额已大于总贷款金额!");
			return false;
		}
		
		document.getElementById('fkzje').value = fkzje;
		
		//-------2010/5/6 luojw begin-------
		//document.getElementById('dkye').value = Math.round(sqdkje) - fkzje;
		var sftqhk = $("sftqhk").value;
		var tqhkje = $("tqhkje").value;
		var iszq = $("iszq").value;

		if("是" == sftqhk){
			$("dkye").value = fkzje - tqhkje;
			if(iszq = "yes"){
				$("zqje").value = fkzje - tqhkje;
			}
		}else{
			$("dkye").value = fkzje;
			if(iszq = "yes"){
				$("zqje").value = fkzje;
			}
		}

		//--------------end-----------------
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
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		</script>
		<html:form action="/zgdzdx_xszz.do?method=gjzxdkshSave" method="post">
			<input type="hidden" id="iszq" name="iszq" value="${rs.iszq }"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>助学贷款 - 审核 - 国家助学贷款个人详细信息</a>
				</p>
			</div>
			
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" />" />
			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("保存成功！");
	         	if (window.dialogArguments){
	         		close();
	         		dialogArgumentsQueryChick();
	         	}
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("保存失败！");
	         	</script>
				</logic:match>
			</logic:present>
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>国家助学贷款个人详细信息</span></th>
			        </tr>
			    </thead>
 				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          <logic:equal name="userType" value="admin">
			          		<button type="button" name="提交" onclick="saveGjzxdk();">提 交</button>
			          </logic:equal>
			            <button type="button" name="关闭" onclick="Close();return false;"id="buttonClose">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
			    <tbody>
				<tr>
					<th align="center" width="16%">
						学号
					</th>
					<td align="left" width="34%">
						<input type="text" readonly="readonly" id="xh" name="xh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xh"/>" readonly="readonly">
					</td>
					<th width="16%">
						<div align="center">
							姓名
						</div>
					</th>
					<td width="34%">
						<input type="text" readonly="readonly" id="xm" name="xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xm"/>" readonly="readonly">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							性别
						</div>
					</th>
					<td>
						<input type="text" readonly="readonly" id="xb" name="xb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xb"/>" readonly="readonly">
					</td>
					<th>
						<div align="center">
							出生日期
						</div>
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
						<div align="center">
							身份证号
						</div>
					</th>
					<td>
						<input type="text" id="sfzh" name="sfzh" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
					<th>
						<div align="center">
							宿舍电话
						</div>
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
					<th>
						<div align="center">
							年级
						</div>
					</th>
					<td>
						<input type="text" id="nj" readonly="readonly" name="nj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nj"/>">
					</td>
					<th>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />名称
						</div>
					</th>
					<td>
						<input type="text" id="xymc" name="xymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							专业名称
						</div>
					</th>
					<td>
						<input type="text" id="zymc" readonly="readonly" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
					</td>
					<th>
						<div align="center">
							行政班号
						</div>
					</th>
					<td>
						<input type="text" id="bjmc" name="bjmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							学制
						</div>
					</th>
					<td>
						<input type="text" id="xz" name="xz" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xz"/>">
					</td>
					<th>
						<div align="center">
							E-Mail与QQ
						</div>
					</th>
					<td>
						<input type="text" id="emailqq" name="emailqq" maxlength="100"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="emailqq"/>">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							毕业时间
						</div>
					</th>
					<td>
						<input type="text" id="byny" name="byny" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="byny"/>">
					</td>
					<th>
						<div align="center">
							是否毕业
						</div>
					</th>
					<td>
						<input type="text" id="sfby" name="sfby" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfby"/>">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							区域经济状况
						</div>
					</th>
					<td>
						<html:select name="rs" property="qyjjzk" style="width:100%;heigh:100%">
							<html:option value="经济欠发达">经济欠发达</html:option>
							<html:option value="经济状况一般">经济状况一般</html:option>
							<html:option value="经济发达">经济发达</html:option>
						</html:select>
					</td>
					<th>
						<div align="center">
							是否为放款银行当地居民
						</div>
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
					<th>
						<div align="center">
							家庭月均收入
						</div>
					</th>
					<td>
						<input type="text" id="jtysr" name="jtysr" maxlength="6"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtysr"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
						<div align="center">
							家庭详细地址
						</div>
					</th>
					<td>
						<input type="text" id="jtxxzz" name="jtxxzz" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtxxzz"/>">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							家庭邮编
						</div>
					</th>
					<td>
						<input type="text" id="jtyb" maxlength="6" name="jtyb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtyb"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
						<div align="center">
							家庭电话
						</div>
					</th>
					<td>
						<input type="text" id="jtdh" name="jtdh" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtdh"/>">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							父亲姓名
						</div>
					</th>
					<td>
						<input type="text" id="fqxm" maxlength="40" name="fqxm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="fqxm"/>">
					</td>
					<th>
						<div align="center">
							母亲姓名
						</div>
					</th>
					<td>
						<input type="text" id="mqxm" name="mqxm" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mqxm"/>">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							父亲职业
						</div>
					</th>
					<td>
						<input type="text" id="fqzy" maxlength="40" name="fqzy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="fqzy"/>">
					</td>
					<th>
						<div align="center">
							母亲职业
						</div>
					</th>
					<td>
						<input type="text" id="mqzy" name="mqzy" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mqzy"/>">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							父亲身份证号
						</div>
					</th>
					<td>
						<input type="text" id="fqsfzh" maxlength="18" name="fqsfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="fqsfzh"/>">
					</td>
					<th>
						<div align="center">
							母亲身份证号
						</div>
					</th>
					<td>
						<input type="text" id="mqsfzh" name="mqsfzh" maxlength="18"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mqsfzh"/>">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							婚姻状况
						</div>
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
						<div align="center">
							配偶姓名
						</div>
					</th>
					<td>
						<input type="text" id="pomc" name="pomc" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="pomc"/>">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							配偶联系电话
						</div>
					</th>
					<td>
						<input type="text" id="polxdh" maxlength="20" name="polxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="polxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
						<div align="center">
							工作单位
						</div>
					</th>
					<td>
						<input type="text" id="gzdw" name="gzdw" maxlength="50"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="gzdw"/>">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							单位电话
						</div>
					</th>
					<td>
						<input type="text" id="dwdh" maxlength="20" name="dwdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dwdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
						<div align="center">
							移动电话
						</div>
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
						<div align="center">
							单位地址
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="dwdz" maxlength="200" name="dwdz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dwdz"/>">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							单位邮编
						</div>
					</th>
					<td>
						<input type="text" id="dwyb" name="dwyb" maxlength="6"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dwyb"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
						<div align="center">
							联系人姓名
						</div>
					</th>
					<td>
						<input type="text" id="lxrxm" maxlength="40" name="lxrxm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxrxm"/>">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							联系人出生日期
						</div>
					</th>
					<td>
						<input type="text" readonly style="cursor:hand;width:100%"
							onclick="return showCalendar('lxrcsrq','y-mm-dd');"
							value="<bean:write name='rs' property="lxrcsrq" />" name="lxrcsrq"
							id="lxrcsrq" />
					</td>
					<th>
						<div align="center">
							联系人性别
						</div>
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
						<div align="center">
							联系人联系电话
						</div>
					</th>
					<td>
						<input type="text" id="lxrlxdh" maxlength="20" name="lxrlxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxrlxdh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
						<div align="center">
							联系人与借款人关系
						</div>
					</th>
					<td>
						<input type="text" id="lxrgx" name="lxrgx" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxrgx"/>">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							联系人单位/地址
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="lxrdwdz" name="lxrdwdz" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxrdwdz"/>">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							备注
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="bz" name="bz" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bz"/>">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							合同编号
						</div>
					</th>
					<td>
						<input type="text" id="htbh" name="htbh" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="htbh"/>">
					</td>
					<th>
						<div align="center">
							贷款金额
						</div>
					</th>
					<td>
						<input type="text" id="sqdkje" maxlength="20" name="sqdkje"
							style="width:100%;heigh:100%" onblur="je();"
							value="<bean:write name="rs" property="sqdkje"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							贷款期限(月)
						</div>
					</th>
					<td>
						<input type="text" id="dkqxy" readonly="readonly" name="dkqxy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dkqxy"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
						<div align="center">
							贷款期限
						</div>
					</th>
					<td>
						<input type="text" id="dkqx" name="dkqx" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dkqx"/>">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							贷款利率
						</div>
					</th>
					<td>
						<input type="text" id="dkll" name="dkll" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dkll"/>">
					</td>
					<th>
						<div align="center">
							<!-- 客户号 -->贷款账号
						</div>
					</th>
					<td>
						<input type="text" id="khh" name="khh" maxlength="50"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="khh"/>">
					</td>
				</tr>
				<tr>
					<th rowspan="2">
						<div align="center">
							<!--第一学年-->第一次放款
						</div>
					</th>
					<th>
						<div align="center">
							放款金额
						</div>
					</th>
					<th>
						<div align="center">
							是否放款
						</div>
					</th>
					<th>
						<div align="center">
							提款时间
						</div>
					</th>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="fk_xn1_je" name="fk_xn1_je" maxlength="20"
								style="width:100%;heigh:100%" onblur="je();"
								value="<bean:write name="rs" property="fk_xn1_je"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<html:select name="rs" property="fk_xn1_sffk" style="width:40px;heigh:100%" onchange="je();">
								<html:option value="否">否</html:option>
								<html:option value="是">是</html:option>
							</html:select>
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('fk_xn1_tksj','y-mm-dd');"
								value="<bean:write name='rs' property="fk_xn1_tksj" />" name="fk_xn1_tksj"
								id="fk_xn1_tksj" />
						</div>
					</td>
				</tr>
				<tr>
					<th rowspan="2">
						<div align="center">
							<!-- 第二学年-->第二次放款
						</div>
					</th>
					<th>
						<div align="center">
							放款金额
						</div>
					</th>
					<th>
						<div align="center">
							是否放款
						</div>
					</th>
					<th>
						<div align="center">
							提款时间
						</div>
					</th>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="fk_xn2_je" name="fk_xn2_je" maxlength="20"
								style="width:100%;heigh:100%" onblur="je();"
								value="<bean:write name="rs" property="fk_xn2_je"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<html:select name="rs" property="fk_xn2_sffk" style="width:40px;heigh:100%" onchange="je();">
								<html:option value="否">否</html:option>
								<html:option value="是">是</html:option>
							</html:select>
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('fk_xn2_tksj','y-mm-dd');"
								value="<bean:write name='rs' property="fk_xn2_tksj" />" name="fk_xn2_tksj"
								id="fk_xn2_tksj" />
						</div>
					</td>
				</tr>
				<tr>
					<th rowspan="2">
						<div align="center">
							<!-- 第三学年-->第三次放款
						</div>
					</th>
					<th>
						<div align="center">
							放款金额
						</div>
					</th>
					<th>
						<div align="center">
							是否放款
						</div>
					</th>
					<th>
						<div align="center">
							提款时间
						</div>
					</th>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="fk_xn3_je" name="fk_xn3_je" maxlength="20"
								style="width:100%;heigh:100%" onblur="je();"
								value="<bean:write name="rs" property="fk_xn3_je"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<html:select name="rs" property="fk_xn3_sffk" style="width:40px;heigh:100%" onchange="je();">
								<html:option value="否">否</html:option>
								<html:option value="是">是</html:option>
							</html:select>
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('fk_xn3_tksj','y-mm-dd');"
								value="<bean:write name='rs' property="fk_xn3_tksj" />" name="fk_xn3_tksj"
								id="fk_xn3_tksj" />
						</div>
					</td>
				</tr>
				<tr>
					<th rowspan="2">
						<div align="center">
							<!-- 第四学年-->第四次放款
						</div>
					</th>
					<th>
						<div align="center">
							放款金额
						</div>
					</th>
					<th>
						<div align="center">
							是否放款
						</div>
					</th>
					<th>
						<div align="center">
							提款时间
						</div>
					</th>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="fk_xn4_je" name="fk_xn4_je" maxlength="20"
								style="width:100%;heigh:100%" onblur="je();"
								value="<bean:write name="rs" property="fk_xn4_je"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<html:select name="rs" property="fk_xn4_sffk" style="width:40px;heigh:100%" onchange="je();">
								<html:option value="否">否</html:option>
								<html:option value="是">是</html:option>
							</html:select>
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('fk_xn4_tksj','y-mm-dd');"
								value="<bean:write name='rs' property="fk_xn4_tksj" />" name="fk_xn4_tksj"
								id="fk_xn4_tksj" />
						</div>
					</td>
				</tr>
				<logic:equal value="5" name="rs" property="xz">
				<tr>
					<th rowspan="2">
						<div align="center">
							<!-- 第五学年-->第五次放款
						</div>
					</th>
					<th>
						<div align="center">
							放款金额
						</div>
					</th>
					<th>
						<div align="center">
							是否放款
						</div>
					</th>
					<th>
						<div align="center">
							提款时间
						</div>
					</th>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="fk_xn5_je" name="fk_xn5_je" maxlength="20"
								style="width:100%;heigh:100%" onblur="je();"
								value="<bean:write name="rs" property="fk_xn5_je"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						</div>
					</td>
					<td>
						<div align="center">
							<html:select name="rs" property="fk_xn5_sffk" style="width:40px;heigh:100%" onchange="je();">
								<html:option value="否">否</html:option>
								<html:option value="是">是</html:option>
							</html:select>
						</div>
					</td>
					<td>
						<div align="center">
							<input type="text" readonly style="cursor:hand;width:100%"
								onclick="return showCalendar('fk_xn5_tksj','y-mm-dd');"
								value="<bean:write name='rs' property="fk_xn5_tksj" />" name="fk_xn5_tksj"
								id="fk_xn5_tksj" />
						</div>
					</td>
				</tr>
				</logic:equal>
				<tr>
					<th>
						<div align="center">
							放款总金额
						</div>
					</th>
					<td>
						<input type="text" id="fkzje" name="fkzje" readonly="readonly"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fkzje"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
						<div align="center">
							贷款余额
						</div>
					</th>
					<td>
						<input type="text" id="dkye" name="dkye" readonly="readonly"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="dkye"/>"
								onkeyup="value=value.replace(/[^\d]/g,'') "
								onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							助学贷款申请时间
						</div>
					</th>
					<td>
						<input type="text" readonly style="cursor:hand;width:100%"
							value="<bean:write name='rs' property="sqsj" />" name="sqsj"
							id="sqsj" />
					</td>
					<th>
						<div align="center">
							借款人最后与银行联系时间
						</div>
					</th>
					<td>
						<input type="text" readonly style="cursor:hand;width:100%"
							onclick="return showCalendar('jkrzhyyhlxsj','y-mm-dd');"
							value="<bean:write name='rs' property="jkrzhyyhlxsj" />" name="jkrzhyyhlxsj"
							id="jkrzhyyhlxsj" />
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							借款人还款情况
						</div>
					</th>
					<td colspan="3">
						<input type="text" id="jkrhkqk" name="jkrhkqk" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jkrhkqk"/>">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							是否签订还款协议
						</div>
					</th>
					<td>
						<html:select name="rs" property="sfqdhkxy" style="width:40px;heigh:100%">
							<html:option value="否">否</html:option>
							<html:option value="是">是</html:option>
						</html:select>
					</td>
					<th>
						<div align="center">
							还款协议签署时间
						</div>
					</th>
					<td>
						<input type="text" readonly style="cursor:hand;width:100%"
							onclick="return showCalendar('hkxyqssj','y-mm-dd');"
							value="<bean:write name='rs' property="hkxyqssj" />" name="hkxyqssj"
							id="hkxyqssj" />
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							还款存折号
						</div>
					</th>
					<td>
						<input type="text" id="hkczh" name="hkczh" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hkczh"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') "
							onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
					</td>
					<th>
						<div align="center">
							还款帐户余额
						</div>
					</th>
					<td>
						<input type="text" id="hkzhye" name="hkzhye" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hkzhye"/>">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							还款起止时间
						</div>
					</th>
					<td>
						<input type="text" id="hkqzsj" name="hkqzsj" maxlength="40"
							style="width:100%;heigh:100%" readonly="true"
							value="${rs.hkqzsj }">
					</td>
					<th>
						<div align="center">
							还款方式
						</div>
					</th>
					<td>
						<input type="text" id="hkfs" name="hkfs" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hkfs"/>">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							展期时间
						</div>
					</th>
					<td>
						<input type="text" id="zqsj" name="zqsj" maxlength="20"
							style="width:100%;heigh:100%" readonly="readonly"
							value="${rs.zqnx }年">
					</td>
					<th>
						<div align="center">
							展期期限
						</div>
					</th>
					<td>
						<input type="text" id="zqqx" name="zqqx" maxlength="20"
							style="width:100%;heigh:100%" readonly="readonly"
							value="<bean:write name="rs" property="zqqx"/>">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							展期利率
						</div>
					</th>
					<td>
						<input type="text" id="zqlv" name="zqlv" maxlength="20"
							style="width:100%;heigh:100%" readonly="readonly"
							value="<bean:write name="rs" property="zqlv"/>">
					</td>
					<th>
						<div align="center">
							展期金额
						</div>
					</th>
					<td>
						<input type="text" id="zqje" name="zqje" maxlength="20"
							style="width:100%;heigh:100%" readonly="readonly"
							value="<bean:write name="rs" property="zqje"/>">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							展期所在单位
						</div>
					</th>
					<td>
						<input type="text" id="zqszdw" name="zqszdw" maxlength="50"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="zqszdw"/>">
					</td>
					<th align="center">
						展期审核结果
					</th>
					<td>
						${rs.zqshjg }
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							借款人拖欠利息总额
						</div>
					</th>
					<td>
						<input type="text" id="jkrtqlxze" name="jkrtqlxze" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jkrtqlxze"/>">
					</td>
					<th>
						<div align="center">
							借款人拖欠利息时间
						</div>
					</th>
					<td>
						<input type="text" id="jkrtqlxsj" name="jkrtqlxsj" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jkrtqlxsj"/>">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							借款人拖欠本金总额
						</div>
					</th>
					<td>
						<input type="text" id="jkrtqbjze" name="jkrtqbjze" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jkrtqbjze"/>">
					</td>
					<th>
						<div align="center">
							借款人拖欠本金时间
						</div>
					</th>
					<td>
						<input type="text" id="jkrtqbjsj" name="jkrtqbjsj" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jkrtqbjsj"/>">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							是否提前还款
						</div>
					</th>
					<td>
						<html:select name="rs" property="sftqhk" style="width:40px;heigh:100%" onchange="je()">
							<html:option value="否">否</html:option>
							<html:option value="是">是</html:option>
						</html:select>
					</td>
					<th>
						<div align="center">
							提前还款金额
						</div>
					</th>
					<td>
						<input type="text" id="tqhkje" name="tqhkje"
							onblur="je();" value="<bean:write name="rs" property="tqhkje"/>"
							onkeypress="return onlyNum(this,20)" 
							style="width:100%;heigh:100%;ime-mode:disabled">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							提醒方式
						</div>
					</th>
					<td>
						<input type="text" id="txfs" name="txfs" maxlength="50"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="txfs"/>">
					</td>
					<th>
						<div align="center">
							已提醒次数
						</div>
					</th>
					<td>
						<input type="text" id="ytxcs" name="ytxcs" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="ytxcs"/>">
					</td>
				</tr>
				<tr>
					<th>
						<div align="center">
							最后一次提醒时间
						</div>
					</th>
					<td>
						<input type="text" readonly style="cursor:hand;width:100%"
							onclick="return showCalendar('zjyctxsj','y-mm-dd');"
							value="<bean:write name='rs' property="zjyctxsj" />" name="zjyctxsj"
							id="zjyctxsj" />
					</td>
					<th>
						<div align="center">
							是否填写资料确认书
						</div>
					</th>
					<td>
						<html:select name="rs" property="sftxzlqrs" style="width:40px;heigh:100%">
							<html:option value="否">否</html:option>
							<html:option value="是">是</html:option>
						</html:select>
					</td>
				</tr>
				</tbody>
			</table>
			</div>
		</html:form>
	</body>
<script language="javascript">
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
</script>
</html>
