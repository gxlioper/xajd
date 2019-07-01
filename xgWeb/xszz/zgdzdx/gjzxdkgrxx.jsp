<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<base target="_self" />
	</head>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
		</script>
		<html:form action="/zgdzdx_xszz.do?method=gjzxdkshSave" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
				<logic:equal name="userType" value="stu">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>国家助学贷款个人详细信息</span></th>
			        </tr>
			    </thead>

				<tr>
					<th align="center" width="16%">
						学号
					</th>
					<td align="left" width="34%">
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
							出生日期
					</th>
					<td>
						<bean:write name="rs" property="csrq" />
					</td>
				</tr>
				<tr>
					<th>
							身份证号
					</th>
					<td>
						<bean:write name="rs" property="sfzh"/>
					</td>
					<th>
							宿舍电话
					</th>
					<td>
						<bean:write name="rs" property="ssdh"/>
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
							行政班号
					</th>
					<td>
						<bean:write name="rs" property="bjmc"/>
					</td>
				</tr>
				<tr>
					<th>
							学制
					</th>
					<td>
						<bean:write name="rs" property="xz"/>
					</td>
					<th>
							E-Mail与QQ
					</th>
					<td>
						<bean:write name="rs" property="emailqq"/>
					</td>
				</tr>
				<tr>
					<th>
							毕业时间
					</th>
					<td>
						<bean:write name="rs" property="byny"/>
					</td>
					<th>
							是否毕业
					</th>
					<td>
						<bean:write name="rs" property="sfby"/>
					</td>
				</tr>
				<tr>
					<th>
							区域经济状况
					</th>
					<td>
						<bean:write name="rs" property="qyjjzk"/>
					</td>
					<th>
							是否为放款银行当地居民
					</th>
					<td>
						<bean:write name="rs" property="sfwfkyhddjm"/>
					</td>
				</tr>
				<tr>
					<th>
							家庭月均收入
					</th>
					<td>
						<bean:write name="rs" property="jtysr"/>
					</td>
					<th>
							家庭详细地址
					</th>
					<td>
						<bean:write name="rs" property="jtxxzz"/>
					</td>
				</tr>
				<tr>
					<th>
							家庭邮编
					</th>
					<td>
						<bean:write name="rs" property="jtyb"/>
					</td>
					<th>
							家庭电话
					</th>
					<td>
						<bean:write name="rs" property="jtdh"/>
					</td>
				</tr>
				<tr>
					<th>
							父亲姓名
					</th>
					<td>
						<bean:write name="rs" property="fqxm"/>
					</td>
					<th>
							母亲姓名
					</th>
					<td>
						<bean:write name="rs" property="mqxm"/>
					</td>
				</tr>
				<tr>
					<th>
							父亲职业
					</th>
					<td>
						<bean:write name="rs" property="fqzy"/>
					</td>
					<th>
							母亲职业
					</th>
					<td>
						<bean:write name="rs" property="mqzy"/>
					</td>
				</tr>
				<tr>
					<th>
							父亲身份证号
					</th>
					<td>
						<bean:write name="rs" property="fqsfzh"/>
					</td>
					<th>
							母亲身份证号
					</th>
					<td>
						<bean:write name="rs" property="mqsfzh"/>
					</td>
				</tr>
				<tr>
					<th>
							婚姻状况
					</th>
					<td>
						<bean:write name="rs" property="hyzk"/>
					</td>
					<th>
							配偶姓名
					</th>
					<td>
						<bean:write name="rs" property="pomc"/>
					</td>
				</tr>
				<tr>
					<th>
							配偶联系电话
					</th>
					<td>
						<bean:write name="rs" property="polxdh"/>
					</td>
					<th>
							工作单位
					</th>
					<td>
						<bean:write name="rs" property="gzdw"/>
					</td>
				</tr>
				<tr>
					<th>
							单位电话
					</th>
					<td>
						<bean:write name="rs" property="dwdh"/>
					</td>
					<th>
							移动电话
					</th>
					<td>
						<bean:write name="rs" property="yddh"/>
					</td>
				</tr>
				<tr>
					<th>
							单位地址
					</th>
					<td colspan="3">
						<bean:write name="rs" property="dwdz"/>
					</td>
				</tr>
				<tr>
					<th>
							单位邮编
					</th>
					<td>
						<bean:write name="rs" property="dwyb"/>
					</td>
					<th>
							联系人姓名
					</th>
					<td>
						<bean:write name="rs" property="lxrxm"/>
					</td>
				</tr>
				<tr>
					<th>
							联系人出生日期
					</th>
					<td>
						<bean:write name="rs" property="lxrcsrq"/>
					</td>
					<th>
							联系人性别
					</th>
					<td>
						<bean:write name="rs" property="lxrxb"/>
					</td>
				</tr>
				<tr>
					<th>
							联系人联系电话
					</th>
					<td>
						<bean:write name="rs" property="lxrlxdh"/>
					</td>
					<th>
							联系人与借款人关系
					</th>
					<td>
						<bean:write name="rs" property="lxrgx"/>
					</td>
				</tr>
				<tr>
					<th>
							联系人单位/地址
					</th>
					<td colspan="3">
						<bean:write name="rs" property="lxrdwdz"/>
					</td>
				</tr>
				<tr>
					<th>
							备注
					</th>
					<td colspan="3">
						<bean:write name="rs" property="bz"/>
					</td>
				</tr>
				<tr>
					<th>
							合同编号
					</th>
					<td>
						<bean:write name="rs" property="htbh" />
					</td>
					<th>
							贷款金额
					</th>
					<td>
						<bean:write name="rs" property="sqdkje"/>
					</td>
				</tr>
				<tr>
					<th>
							贷款期限(月)
					</th>
					<td>
						<bean:write name="rs" property="dkqxy"/>
					</td>
					<th>
							贷款期限
					</th>
					<td>
						<bean:write name="rs" property="dkqx"/>
					</td>
				</tr>
				<tr>
					<th>
							贷款利率
					</th>
					<td>
						<bean:write name="rs" property="dkll"/>
					</td>
					<th>
							客户号
					</th>
					<td>
						<bean:write name="rs" property="khh"/>
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
							<bean:write name="rs" property="fk_xn1_je"/>
					</td>
					<td>
							&nbsp;<bean:write name="rs" property="fk_xn1_sffk"/>&nbsp;
					</td>
					<td>
							<bean:write name="rs" property="fk_xn1_tksj"/>
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
							<bean:write name="rs" property="fk_xn2_je"/>
					</td>
					<td>
							&nbsp;<bean:write name="rs" property="fk_xn2_sffk"/>&nbsp;
					</td>
					<td>
							<bean:write name="rs" property="fk_xn2_tksj"/>
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
							<bean:write name="rs" property="fk_xn3_je"/>
					</td>
					<td>
							&nbsp;<bean:write name="rs" property="fk_xn3_sffk"/>&nbsp;
					</td>
					<td>
							<bean:write name="rs" property="fk_xn3_tksj"/>
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
							<bean:write name="rs" property="fk_xn4_je"/>
					</td>
					<td>
							&nbsp;<bean:write name="rs" property="fk_xn4_sffk"/>&nbsp;
					</td>
					<td>
							<bean:write name="rs" property="fk_xn4_tksj"/>
					</td>
				</tr>
				<logic:equal value="5" property="xz" name="rs">
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
							<bean:write name="rs" property="fk_xn5_je"/>
					</td>
					<td>
							&nbsp;<bean:write name="rs" property="fk_xn5_sffk"/>&nbsp;
					</td>
					<td>
							<bean:write name="rs" property="fk_xn5_tksj"/>
					</td>
				</tr>
				</logic:equal>
				<tr>
					<th>
							放款总金额
					</th>
					<td>
						<bean:write name="rs" property="fkzje"/>
					</td>
					<th>
							贷款余额
					</th>
					<td>
						<bean:write name="rs" property="dkye"/>
					</td>
				</tr>
				<tr>
					<th>
							助学贷款申请时间
					</th>
					<td>
						<bean:write name="rs" property="sqsj"/>
					</td>
					<th>
							借款人最后与银行联系时间
					</th>
					<td>
						<bean:write name="rs" property="jkrzhyyhlxsj"/>
					</td>
				</tr>
				<tr>
					<th>
							借款人还款情况
					</th>
					<td colspan="3">
						<bean:write name="rs" property="jkrhkqk"/>
					</td>
				</tr>
				<tr>
					<th>
							是否签订还款协议
					</th>
					<td>
						<bean:write name="rs" property="sfqdhkxy"/>
					</td>
					<th>
							还款协议签署时间
					</th>
					<td>
						<bean:write name="rs" property="hkxyqssj"/>
					</td>
				</tr>
				<tr>
					<th>
							还款存折号
					</th>
					<td>
						<bean:write name="rs" property="hkczh"/>
					</td>
					<th>
							还款帐户余额
					</th>
					<td>
						<bean:write name="rs" property="hkzhye"/>
					</td>
				</tr>
				<tr>
					<th>
							展期时间
					</th>
					<td>
						<bean:write name="rs" property="zqsj"/>
					</td>
					<th>
							展期期限
					</th>
					<td>
						<bean:write name="rs" property="zqqx"/>
					</td>
				</tr>
				<tr>
					<th>
							展期利率
					</th>
					<td>
						<bean:write name="rs" property="zqlv"/>
					</td>
					<th>
							展期金额
					</th>
					<td>
						<bean:write name="rs" property="zqje"/>
					</td>
				</tr>
				<tr>
					<th>
							展期所在单位
					</th>
					<td colspan="3">
						<bean:write name="rs" property="zqszdw"/>
					</td>
				</tr>
				<tr>
					<th>
							借款人拖欠利息总额
					</th>
					<td>
						<bean:write name="rs" property="jkrtqlxze"/>
					</td>
					<th>
							借款人拖欠利息时间
					</th>
					<td>
						<bean:write name="rs" property="jkrtqlxsj"/>
					</td>
				</tr>
				<tr>
					<th>
							借款人拖欠本金总额
					</th>
					<td>
						<bean:write name="rs" property="jkrtqbjze"/>
					</td>
					<th>
							借款人拖欠本金时间
					</th>
					<td>
						<bean:write name="rs" property="jkrtqbjsj"/>
					</td>
				</tr>
				<tr>
					<th>
							是否提前还款
					</th>
					<td>
						<bean:write name="rs" property="sftqhk"/>
					</td>
					<th>
							提前还款金额
					</th>
					<td>
						<bean:write name="rs" property="tqhkje"/>
					</td>
				</tr>
				<tr>
					<th>
							提醒方式
					</th>
					<td>
						<bean:write name="rs" property="txfs"/>
					</td>
					<th>
							已提醒次数
					</th>
					<td>
						<bean:write name="rs" property="ytxcs"/>
					</td>
				</tr>
				<tr>
					<th>
							最后一次提醒时间
					</th>
					<td>
						<bean:write name="rs" property="zjyctxsj"/>
					</td>
					<th>
							是否填写资料确认书
					</th>
					<td>
						<bean:write name="rs" property="sftxzlqrs"/>
					</td>
				</tr>
			</table>
			</logic:equal>
			<logic:notEqual name="userType" value="stu">
				<div align="center">
					<br />
					<h3>该功能只开放给学生！</h3>
				</div>
			</logic:notEqual>
		</html:form>
	</body>
</html>
