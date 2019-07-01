<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	
	<script language="javascript">
	function yz(){
		
		document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=gjzxdklsOneSave";
		document.forms[0].submit();
	}
</script>
</head>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		</script>
		<html:form action="/zgdzdx_xszz.do?method=gjzxdklsOne" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>助学贷款 - 审核 - 国家助学贷款历史个人详细信息</a>
				</p>
			</div>
	
			<input type="hidden" name="pkVal" value="<bean:write name="pkVal"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" />" />
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
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
				<thead>
			    	<tr>
			        	<th colspan="4"><span>国家助学贷款历史个人详细信息</span></th>
			        </tr>
			    </thead>
			     <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			         <logic:equal name="userType" value="admin">
			          		<button type="button" name="提交"  onClick="yz();">提 交</button>
			         </logic:equal>
			            <button type="button" name="关闭" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();" 
					id="buttonClose">关 闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
			    
			    <tbody>
				<tr>
					<th align="center" width="16%">
						学号：
					</th>
					<td align="left" width="34%">
						<input type="text" readonly="readonly" id="xh" name="xh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xh"/>">
					</td>
					<th width="16%">
							姓名：
					</th>
					<td width="34%">
						<input type="text" maxlength="50" id="xm" name="xm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xm"/>">
					</td>
				</tr>
				<tr>
					<th>
							性别：
					</th>
					<td>
						<input type="text" maxlength="10" id="xb" name="xb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xb"/>">
					</td>
					<th>
							出生日期：
					</th>
					<td>
						<input type="text" maxlength="10" id="csrq" name="csrq"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="csrq"/>">
					</td>
				</tr>
				<tr>
					<th>
							身份证号：
					</th>
					<td>
						<input type="text" id="sfzh" name="sfzh" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfzh"/>">
					</td>
					<th>
							宿舍电话：
					</th>
					<td>
						<input type="text" id="ssdh" name="ssdh" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="ssdh"/>">
					</td>
				</tr>
				<tr>
					<th>
							年级：
					</th>
					<td>
						<input type="text" id="nj" maxlength="4" name="nj"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="nj"/>">
					</td>
					<th>
							学制：
					</th>
					<td>
						<input type="text" id="xz" name="xz" maxlength="4"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xz"/>">
					</td>
				</tr>
				<tr>
					<th>
							<bean:message key="lable.xsgzyxpzxy" />代码：
					</th>
					<td>
						<input type="text" id="xydm" maxlength="10" name="xydm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xydm"/>">
					</td>
					<th>
							<bean:message key="lable.xsgzyxpzxy" />名称：
					</th>
					<td>
						<input type="text" id="xymc" name="xymc" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
					</td>
				</tr>
				<tr>
					<th>
							专业代码：
					</th>
					<td>
						<input type="text" id="zydm" maxlength="10" name="zydm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zydm"/>">
					</td>
					<th>
							专业名称：
					</th>
					<td>
						<input type="text" id="zymc" maxlength="40" name="zymc"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zymc"/>">
					</td>
				</tr>
				<tr>
					<th>
							班级代码：
					</th>
					<td>
						<input type="text" id="bjdm" maxlength="20" name="bjdm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjdm"/>">
					</td>
					<th>
							行政班号：
					</th>
					<td>
						<input type="text" id="bjmc" name="bjmc" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bjmc"/>">
					</td>
				</tr>
				<tr>
					<th>
							学历：
					</th>
					<td>
						<input type="text" id="xl" name="xl" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xl"/>">
					</td>
					<th>
							是否毕业：
					</th>
					<td>
						<input type="text" id="sfby" name="sfby" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfby"/>">
					</td>
				</tr>
				<tr>
					<th>
							入学日期：
					</th>
					<td>
						<input type="text" id="rxny" name="rxny" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="rxny"/>">
					</td>
					<th>
							毕业日期：
					</th>
					<td>
						<input type="text" id="byny" name="byny" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="byny"/>">
					</td>
				</tr>
				<tr>
					<th>
							民族：
					</th>
					<td>
						<input type="text" id="mzmc" name="mzmc" maxlength="50"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mzmc"/>">
					</td>
					<th>
							政治面貌：
					</th>
					<td>
						<input type="text" id="zzmmmc" name="zzmmmc" maxlength="50"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zzmmmc"/>">
					</td>
				</tr>
				<tr>
					<th>
							区域经济状况：
					</th>
					<td>
						<input type="text" id="qyjjzk" name="qyjjzk" maxlength="50"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="qyjjzk"/>">
					</td>
					<th>
							是否为放款银行当地居民：
					</th>
					<td>
						<input type="text" id="sfwfkyhddjm" name="sfwfkyhddjm" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfwfkyhddjm"/>">
					</td>
				</tr>
				<tr>
					<th>
							家庭月均收入：
					</th>
					<td>
						<input type="text" id="jtysr" name="jtysr" maxlength="6"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtysr"/>">
					</td>
					<th>
							家庭详细地址：
					</th>
					<td>
						<input type="text" id="jtxxzz" name="jtxxzz" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtxxzz"/>">
					</td>
				</tr>
				<tr>
					<th>
							家庭邮编：
					</th>
					<td>
						<input type="text" id="jtyb" maxlength="6" name="jtyb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtyb"/>">
					</td>
					<th>
							家庭电话：
					</th>
					<td>
						<input type="text" id="jtdh" name="jtdh" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jtdh"/>">
					</td>
				</tr>
				<tr>
					<th>
							父亲姓名：
					</th>
					<td>
						<input type="text" id="fqxm" maxlength="40" name="fqxm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="fqxm"/>">
					</td>
					<th>
							母亲姓名：
					</th>
					<td>
						<input type="text" id="mqxm" name="mqxm" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mqxm"/>">
					</td>
				</tr>
				<tr>
					<th>
							父亲职业：
					</th>
					<td>
						<input type="text" id="fqzy" maxlength="40" name="fqzy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="fqzy"/>">
					</td>
					<th>
							母亲职业：
					</th>
					<td>
						<input type="text" id="mqzy" name="mqzy" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mqzy"/>">
					</td>
				</tr>
				<tr>
					<th>
							父亲身份证号：
					</th>
					<td>
						<input type="text" id="fqsfzh" maxlength="18" name="fqsfzh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="fqsfzh"/>">
					</td>
					<th>
							母亲身份证号：
					</th>
					<td>
						<input type="text" id="mqsfzh" name="mqsfzh" maxlength="18"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="mqsfzh"/>">
					</td>
				</tr>
				<tr>
					<th>
							婚姻状况：
					</th>
					<td>
						<input type="text" id="hyzk" name="hyzk" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hyzk"/>">
					</td>
					<th>
							配偶姓名：
					</th>
					<td>
						<input type="text" id="pomc" name="pomc" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="pomc"/>">
					</td>
				</tr>
				<tr>
					<th>
							配偶联系电话：
					</th>
					<td>
						<input type="text" id="polxdh" maxlength="20" name="polxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="polxdh"/>">
					</td>
					<th>
							工作单位：
					</th>
					<td>
						<input type="text" id="gzdw" name="gzdw" maxlength="50"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="gzdw"/>">
					</td>
				</tr>
				<tr>
					<th>
							单位电话：
					</th>
					<td>
						<input type="text" id="dwdh" maxlength="20" name="dwdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dwdh"/>">
					</td>
					<th>
							工作后月收入：
					</th>
					<td>
						<input type="text" id="gzhysr" name="gzhysr" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="gzhysr"/>">
					</td>
				</tr>
				<tr>
					<th>
							单位地址：
					</th>
					<td>
						<input type="text" id="dwdz" maxlength="200" name="dwdz"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dwdz"/>">
					</td>
					<th>
							单位邮编：
					</th>
					<td>
						<input type="text" id="dwyb" name="dwyb" maxlength="6"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dwyb"/>">
					</td>
				</tr>
				<tr>
					<th>
							EMAIL和QQ：
					</th>
					<td>
						<input type="text" id="emailqq" name="emailqq" maxlength="100"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="emailqq"/>">
					</td>
					<th>
							移动电话：
					</th>
					<td>
						<input type="text" id="yddh" maxlength="20" name="yddh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="yddh"/>">
					</td>
				</tr>
				<tr>
					<th>
							联系人姓名：
					</th>
					<td>
						<input type="text" id="lxrxm" maxlength="40" name="lxrxm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxrxm"/>">
					</td>
					<th>
							联系人出生日期：
					</th>
					<td>
						<input type="text" id="lxrcsrq" maxlength="20" name="lxrcsrq"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxrcsrq"/>">
					</td>
				</tr>
				<tr>
					<th>
							联系人性别：
					</th>
					<td>
						<input type="text" id="lxrxb" maxlength="10" name="lxrxb"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxrxb"/>">
					</td>
					<th>
							联系人联系电话：
					</th>
					<td>
						<input type="text" id="lxrlxdh" maxlength="20" name="lxrlxdh"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxrlxdh"/>">
					</td>
				</tr>
				<tr>
					<th>
							联系人与借款人关系：
					</th>
					<td>
						<input type="text" id="lxrgx" name="lxrgx" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxrgx"/>">
					</td>
					<th>
							联系人单位/地址：
					</th>
					<td>
						<input type="text" id="lxrdwdz" name="lxrdwdz" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="lxrdwdz"/>">
					</td>
				</tr>
				<tr>
					<th>
							备注：
					</th>
					<td colspan="3">
						<input type="text" id="bz" name="bz" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="bz"/>">
					</td>
				</tr>
				<tr>
					<th>
							合同编号：
					</th>
					<td>
						<input type="text" id="htbh" name="htbh" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="htbh"/>">
					</td>
					<th>
							贷款金额：
					</th>
					<td>
						<input type="text" id="sqdkje" maxlength="20" name="sqdkje"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sqdkje"/>">
					</td>
				</tr>
				<tr>
					<th>
							贷款期限(月)：
					</th>
					<td>
						<input type="text" id="dkqxy" maxlength="10" name="dkqxy"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dkqxy"/>">
					</td>
					<th>
							贷款期限：
					</th>
					<td>
						<input type="text" id="dkqx" name="dkqx" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dkqx"/>">
					</td>
				</tr>
				<tr>
					<th>
							贷款利率：
					</th>
					<td>
						<input type="text" id="dkll" name="dkll" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="dkll"/>">
					</td>
					<th>
							客户号：
					</th>
					<td>
						<input type="text" id="khh" name="khh" maxlength="50"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="khh"/>">
					</td>
				</tr>
				<tr>
					<th rowspan="2">
							第一学年
					</th>
					<th>
							放款金额
					</th>
					<th>
							是否放款
					</th>
					<th>
							提款时间
					</th>
				</tr>
				<tr>
					<td>
						<div align="center">
							<input type="text" id="fk_xn1_je" name="fk_xn1_je" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn1_je"/>">
						</div>
					</td>
					<td>
						<div>
							<input type="text" id="fk_xn1_sffk" name="fk_xn1_sffk" maxlength="10"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn1_sffk"/>">
						</div>
					</td>
					<td>
						<div>
							<input type="text" id="fk_xn1_tksj" name="fk_xn1_tksj" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn1_tksj"/>">
						</div>
					</td>
				</tr>
				<tr>
					<th rowspan="2">
							第二学年
					</th>
					<th>
							放款金额
					</th>
					<th>
							是否放款
					</th>
					<th>
							提款时间
					</th>
				</tr>
				<tr>
					<td>
							<input type="text" id="fk_xn2_je" name="fk_xn2_je" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn2_je"/>">
					</td>
					<td>
						<div>
							<input type="text" id="fk_xn2_sffk" name="fk_xn2_sffk" maxlength="10"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn2_sffk"/>">
						</div>
					</td>
					<td>
						<div>
							<input type="text" id="fk_xn2_tksj" name="fk_xn2_tksj" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn2_tksj"/>">
						</div>
					</td>
				</tr>
				<tr>
					<th rowspan="2">
							第三学年
					</th>
					<th>
							放款金额
					</th>
					<th>
							是否放款
					</th>
					<th>
							提款时间
					</th>
				</tr>
				<tr>
					<td>
							<input type="text" id="fk_xn3_je" name="fk_xn3_je" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn3_je"/>">
					</td>
					<td>
							<input type="text" id="fk_xn3_sffk" name="fk_xn3_sffk" maxlength="10"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn3_sffk"/>">
					</td>
					<td>
							<input type="text" id="fk_xn3_tksj" name="fk_xn3_tksj" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn3_tksj"/>">
					</td>
				</tr>
				<tr>
					<th rowspan="2">
							第四学年
					</th>
					<th>
							放款金额
					</th>
					<th>
							是否放款
					</th>
					<th>
							提款时间
					</th>
				</tr>
				<tr>
					<td>
							<input type="text" id="fk_xn4_je" name="fk_xn4_je" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn4_je"/>">
					</td>
					<td>
							<input type="text" id="fk_xn4_sffk" name="fk_xn4_sffk" maxlength="10"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn4_sffk"/>">
					</td>
					<td>
							<input type="text" id="fk_xn4_tksj" name="fk_xn4_tksj" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn4_tksj"/>">
					</td>
				</tr>
				<tr>
					<th rowspan="2">
							第五学年
					</th>
					<th>
							放款金额
					</th>
					<th>
							是否放款
					</th>
					<th>
							提款时间
					</th>
				</tr>
				<tr>
					<td>
							<input type="text" id="fk_xn5_je" name="fk_xn5_je" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn5_je"/>">
					</td>
					<td>
							<input type="text" id="fk_xn5_sffk" name="fk_xn5_sffk" maxlength="10"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn5_sffk"/>">
					</td>
					<td>
							<input type="text" id="fk_xn5_tksj" name="fk_xn5_tksj" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fk_xn5_tksj"/>">
					</td>
				</tr>
				<tr>
					<th>
							放款总金额
					</th>
					<td>
						<input type="text" id="fkzje" name="fkzje" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="fkzje"/>">
					</td>
					<th>
							贷款余额
					</th>
					<td>
						<input type="text" id="dkye" name="dkye" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="dkye"/>">
					</td>
				</tr>
				<tr>
					<th>
							助学贷款申请时间
					</th>
					<td>
						<input type="text" id="sqsj" name="sqsj" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="sqsj"/>">
					</td>
					<th>
							借款人最后与银行联系时间：
					</th>
					<td>
						<input type="text" id="jkrzhyyhlxsj" name="jkrzhyyhlxsj" maxlength="20"
								style="width:100%;heigh:100%"
								value="<bean:write name="rs" property="jkrzhyyhlxsj"/>">
					</td>
				</tr>
				<tr>
					<th>
							借款人还款情况：
					</th>
					<td colspan="3">
						<input type="text" id="jkrhkqk" name="jkrhkqk" maxlength="200"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jkrhkqk"/>">
					</td>
				</tr>
				<tr>
					<th>
							是否签订还款协议：
					</th>
					<td>
						<input type="text" id="sfqdhkxy" name="sfqdhkxy" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sfqdhkxy"/>">
					</td>
					<th>
							还款协议签署时间：
					</th>
					<td>
						<input type="text" id="hkxyqssj" name="hkxyqssj" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hkxyqssj"/>">
					</td>
				</tr>
				<tr>
					<th>
							还款存折号：
					</th>
					<td>
						<input type="text" id="hkczh" name="hkczh" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hkczh"/>">
					</td>
					<th>
							还款帐户余额：
					</th>
					<td>
						<input type="text" id="hkzhye" name="hkzhye" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hkzhye"/>">
					</td>
				</tr>
				<tr>
					<th>
							还款起止时间：
					</th>
					<td>
						<input type="text" id="hkqzsj" name="hkqzsj" maxlength="40"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hkqzsj"/>">
					</td>
					<th>
							还款方式：
					</th>
					<td>
						<input type="text" id="hkfs" name="hkfs" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="hkfs"/>">
					</td>
				</tr>
				<tr>
					<th>
							展期时间：
					</th>
					<td>
						<input type="text" id="zqsj" name="zqsj" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zqsj"/>">
					</td>
					<th>
							展期期限：
					</th>
					<td>
						<input type="text" id="zqqx" name="zqqx" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zqqx"/>">
					</td>
				</tr>
				<tr>
					<th>
							展期利率：
					</th>
					<td>
						<input type="text" id="zqlv" name="zqlv" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zqlv"/>">
					</td>
					<th>
							展期金额：
					</th>
					<td>
						<input type="text" id="zqje" name="zqje" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zqje"/>">
					</td>
				</tr>
				<tr>
					<th>
							展期所在单位：
					</th>
					<td colspan="3">
						<input type="text" id="zqszdw" name="zqszdw" maxlength="50"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zqszdw"/>">
					</td>
				</tr>
				<tr>
					<th>
							借款人拖欠利息总额：
					</th>
					<td>
						<input type="text" id="jkrtqlxze" name="jkrtqlxze" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jkrtqlxze"/>">
					</td>
					<th>
							借款人拖欠利息时间：
					</th>
					<td>
						<input type="text" id="jkrtqlxsj" name="jkrtqlxsj" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jkrtqlxsj"/>">
					</td>
				</tr>
				<tr>
					<th>
							借款人拖欠本金总额：
					</th>
					<td>
						<input type="text" id="jkrtqbjze" name="jkrtqbjze" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jkrtqbjze"/>">
					</td>
					<th>
							借款人拖欠本金时间：
					</th>
					<td>
						<input type="text" id="jkrtqbjsj" name="jkrtqbjsj" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="jkrtqbjsj"/>">
					</td>
				</tr>
				<tr>
					<th>
							是否提前还款：
					</th>
					<td>
						<input type="text" id="sftqhk" name="sftqhk" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sftqhk"/>">
					</td>
					<th>
							提前还款金额：
					</th>
					<td>
						<input type="text" id="tqhkje" name="tqhkje" maxlength="10"
							style="width:100%;heigh:100%" 
							value="<bean:write name="rs" property="tqhkje"/>">
					</td>
				</tr>
				<tr>
					<th>
							提醒方式：
					</th>
					<td>
						<input type="text" id="txfs" name="txfs" maxlength="50"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="txfs"/>">
					</td>
					<th>
							已提醒次数：
					</th>
					<td>
						<input type="text" id="ytxcs" name="ytxcs" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="ytxcs"/>">
					</td>
				</tr>
				<tr>
					<th>
							最后一次提醒时间：
					</th>
					<td>
						<input type="text" id="zjyctxsj" name="zjyctxsj" maxlength="10"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zjyctxsj"/>">
					</td>
					<th>
							是否填写资料确认书：
					</th>
					<td>
						<input type="text" id="sftxzlqrs" name="sftxzlqrs" maxlength="20"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="sftxzlqrs"/>">
					</td>
				</tr>
				</tbody>
			</table>
			</div>
		</html:form>
	</body>
</html>
