<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- 头文件 -->
<html>
	<head>
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	</head>

	<body>
		<html:form action="zxdksq.do" method="post">
			<div align="right">
				<h4>
					合同编号&nbsp;
					<logic:empty name="rs" property="htbh">
						<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>
					</logic:empty>
					<logic:notEmpty name="rs" property="htbh">
						<u>&nbsp;<bean:write name="rs" property="htbh" />&nbsp;</u>
					</logic:notEmpty>
				</h4>
			</div>
			<div align="center" style="font-size:21px;font-family:黑体;">
				
					中国银行国家助学贷款申请审批表
				
			</div>
				<br/>
					经办支行：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					贷款编号：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				
						<table width="100%" class="printtab" style="font-family:宋体;font-size:14px;">
							<tr>
								<td width="7%"></td>
								<td width="5%"></td>
								<td width="4%"></td>
								<td width="5%"></td>
								<td width="5%"></td>
								<td width="5%"></td>
								<td width="5%"></td>
								<td width="5%"></td>
								<td width="5%"></td>
								<td width="5%"></td>
								<td width="5%"></td>
								<td width="7%"></td>
								<td width="5%"></td>
								<td width="5%"></td>
								<td width="7%"></td>
								<td width="5%"></td>
							</tr>
							<tr class="nowrap">
								<td width="10%" colspan="2">
									申请人姓名
								</td>
								<td colspan="3" width="20%">
									&nbsp;
									<bean:write name="rs" property="xm" />
								</td>
								<td width="6%">
									性别
								</td>
								<td align="10%" colspan="4">
									&nbsp;&nbsp;&nbsp;
									<logic:equal name="rs" property="xb" value="男">
								√
								</logic:equal>
									<logic:notEqual name="rs" property="xb" value="男">
								□
								</logic:notEqual>
									&nbsp;男&nbsp;&nbsp;
									<logic:equal name="rs" property="xb" value="女">
								√
								</logic:equal>
									<logic:notEqual name="rs" property="xb" value="女">
								□
								</logic:notEqual>
									&nbsp;女
								</td>
								<td width="10%" colspan="2">
									出生日期
								</td>
								<td colspan="4">
									&nbsp;
									<logic:equal name="rs" property="csrq_year" value="">
								&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:equal>
									<logic:notEqual name="rs" property="csrq_year" value="">
										<bean:write name="rs" property="csrq_year" />
									</logic:notEqual>
									&nbsp;年&nbsp;
									<logic:equal name="rs" property="csrq_month" value="">
								&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:equal>
									<logic:notEqual name="rs" property="csrq_month" value="">
										<bean:write name="rs" property="csrq_month" />
									</logic:notEqual>
									&nbsp;月
								</td>
								<td rowspan="4" width="15%">
									申请人照片
								</td>
							</tr>
							<tr class="nowrap">
								<td colspan="2">
									身份证号码
								</td>
								<td colspan="7">
									&nbsp;
									<bean:write name="rs" property="sfzh" />
								</td>
								<td colspan="2">
									民族
								</td>
								<td colspan="5">
									&nbsp;
									<bean:write name="rs" property="mzmc" />
								</td>
							</tr>
							<tr class="nowrap">
								<td colspan="2">
									学校
								</td>
								<td colspan="3">
									中国地质大学(武汉)
								</td>
								<td>
									院系
								</td>
								<td colspan="3">
									<bean:write name="rs" property="xymc" />
								</td>
								<td colspan="2">
									专业
								</td>
								<td colspan="5">
									<bean:write name="rs" property="zymc" />
								</td>
							</tr>
							<tr>
								<td colspan="2">
									班级
								</td>
								<td colspan="3">
									<bean:write name="rs" property="bjmc" />
								</td>
								<td>
									学号
								</td>
								<td colspan="3">
									<bean:write name="rs" property="xh" />
								</td>
								<td colspan="2">
									政治面貌
								</td>
								<td colspan="5">
									<bean:write name="rs" property="zzmmmc" />
								</td>
							</tr>

							<tr>
								<td colspan="2">
									学制
								</td>
								<td colspan="3">
									<bean:write name="rs" property="xz" />
									年
								</td>
								<td>
									学历
								</td>
								<td colspan="11">
									<logic:equal name="rs" property="xl" value="专科">
								&nbsp;&nbsp;&nbsp;&nbsp;√
								</logic:equal>
									<logic:notEqual name="rs" property="xl" value="专科">
								□
								</logic:notEqual>
									&nbsp;专科 &nbsp;&nbsp;
									<logic:equal name="rs" property="xl" value="本科">
								√
								</logic:equal>
									<logic:notEqual name="rs" property="xl" value="本科">
								□
								</logic:notEqual>
									&nbsp;本科&nbsp;&nbsp; □&nbsp;硕士生&nbsp;&nbsp;□&nbsp;博士生
								</td>
							</tr>
							<tr>
								<td colspan="2">
									婚姻状况
								</td>
								<td colspan="3">
									&nbsp;
								</td>
								<td colspan="2">
									健康状况
								</td>
								<td colspan="2">
									&nbsp;
								</td>
								<td colspan="2">
									宿舍电话
								</td>
								<td colspan="3">
									&nbsp;
									<bean:write name="rs" property="ssdh" />
								</td>
								<td colspan="2">
									移动电话
								</td>
								<td>
									&nbsp;
									<bean:write name="rs" property="sjhm" />
								</td>
							</tr>
							<tr>
								<td rowspan="5" colspan="2">
									家庭情况
								</td>
								<td colspan="3">
									家庭地址
								</td>
								<td colspan="4">
									<bean:write name="rs" property="jtxxzz" />
								</td>
								<td colspan="2">
									邮政编码
								</td>
								<td colspan="3">
									<bean:write name="rs" property="jtyb" />
								</td>
								<td colspan="2">
									家庭年收入
								</td>
								<td>
									${nsr }元
								</td>
							</tr>
							<tr>
								<td colspan="3">
									家庭成员
								</td>
								<td colspan="3">
									姓名
								</td>
								<td colspan="3">
									工作单位地址
								</td>
								<td colspan="4">
									证件类型及号码
								</td>
								<td colspan="2">
									联系电话
								</td>
							</tr>
							<tr>
								<td colspan="3">
									父亲
								</td>
								<td colspan="3">
									<logic:notEmpty name="rs" property="fqxm">
										<bean:write name="rs" property="fqxm" />
									</logic:notEmpty>
									<logic:empty name="rs" property="fqxm">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								</td>
								<td colspan="3">
								</td>
								<td colspan="4">
									<logic:notEmpty name="rs" property="fqsfzh">
										<bean:write name="rs" property="fqsfzh" />
									</logic:notEmpty>
									<logic:empty name="rs" property="fqsfzh">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								</td>
								<td colspan="2">
								</td>
							</tr>
							<tr>
								<td colspan="3">
									母亲
								</td>
								<td colspan="3">
									<logic:notEmpty name="rs" property="mqxm">
										<bean:write name="rs" property="mqxm" />
									</logic:notEmpty>
									<logic:empty name="rs" property="mqxm">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								</td>
								<td colspan="3">
								</td>
								<td colspan="4">
									&nbsp;&nbsp;
									<logic:notEmpty name="rs" property="mqsfzh">
										<bean:write name="rs" property="mqsfzh" />
									</logic:notEmpty>
									<logic:empty name="rs" property="mqsfzh">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
								</td>
								<td colspan="2">
								</td>
							</tr>
							<tr>
								<td colspan="3">
									其他亲属
								</td>
								<td colspan="3">
								</td>
								<td colspan="3">
								</td>
								<td colspan="4">
								</td>
								<td colspan="2">
								</td>
							</tr>
							<tr>
								<td colspan="2" rowspan="5">
									见证人情况
								</td>
								<td colspan="3">
									姓名
								</td>

								<td colspan="2">
									黄燕 
								</td>
								<td colspan="2">
									性别
								</td>
								<td>
									□&nbsp;男&nbsp;&nbsp;√&nbsp;女
								</td>
								<td colspan="4">
									证件类型及号码
								</td>
								<td colspan="3">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td colspan="3">
									与借款人关系
								</td>
								<td colspan="2">
									师生
								</td>
								<td colspan="2">
									联系电话
								</td>
								<td colspan="3">
									027-67883443
								</td>
								<td colspan="2">
									通讯地址
								</td>
								<td colspan="3">
									中国地质大学（武汉）学工处资助中心
								</td>
							</tr>
							<tr>
								<td colspan="3">
									姓名
								</td>

								<td colspan="2">
									&nbsp;
								</td>
								<td colspan="2">
									性别
								</td>
								<td>
									□&nbsp;男&nbsp;&nbsp;□&nbsp;女
								</td>
								<td colspan="4">
									证件类型及号码
								</td>
								<td colspan="3">
								</td>
							</tr>
							<tr>
								<td colspan="3">
									与借款人关系
								</td>
								<td colspan="2">
									&nbsp;
								</td>
								<td colspan="2">
									联系电话
								</td>
								<td colspan="3">

								</td>
								<td colspan="2">
									通讯地址
								</td>
								<td colspan="3">

								</td>
							</tr>
							<tr style="height:10px">
								<td colspan="4">
									见证人一
									<br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本人见证上述贷款事实， 自愿履行作证义务
								</td>
								<td colspan="4">
									签字:
									<br>
									<br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
								</td>
								<td colspan="4">
									见证人二
									<br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本人见证上述贷款事实， 自愿履行作证义务
									<br>
								</td>
								<td colspan="3">
									签字:
									<br>
									<br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
								</td>
							</tr>
							<tr>
								<td rowspan="3" colspan="2">
									申请
									<br>
									借款
									<br>
									情况
								</td>
								<td colspan="3">
									贷款种类
								</td>
								<td colspan="12">
									&nbsp;&nbsp;&nbsp;&nbsp;√中央财政贴息国家助学贷款&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;□地方财政贴息国家助学贷款
								</td>
							</tr>
							<tr>
								<td colspan="3">
									贷款总金额
								</td>
								<td colspan="4">
									<logic:notEmpty name="rs" property="sqdkje">
										<bean:write name="rs" property="sqdkje" />
									</logic:notEmpty>
									<logic:empty name="rs" property="sqdkje">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:empty>
									&nbsp;&nbsp;元
								</td>
								<td colspan="8">
									其中：学费&nbsp;&nbsp;&nbsp;&nbsp;元、住宿费&nbsp;&nbsp;&nbsp;&nbsp;元、生活费&nbsp;&nbsp;&nbsp;&nbsp;元
								</td>
							</tr>
							<tr>
								<td colspan="3">
									贷款期限
								</td>
								<td colspan="4">
									<logic:notEqual name="rs" property="dkqxy" value="">
										<bean:write name="rs" property="dkqxy" />
									</logic:notEqual>
									<logic:equal name="rs" property="dkqxy" value="">
								&nbsp;&nbsp;
								</logic:equal>
									&nbsp;&nbsp;月
								</td>
								<td colspan="8" align="center">
									自

									<logic:notEqual name="rs" property="dkqx1_year" value="">
										<bean:write name="rs" property="dkqx1_year" />
									</logic:notEqual>
									<logic:equal name="rs" property="dkqx1_year" value="">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:equal>
									年

									<logic:notEqual name="rs" property="dkqx1_mon" value="">
										<bean:write name="rs" property="dkqx1_mon" />
									</logic:notEqual>
									<logic:equal name="rs" property="dkqx1_mon" value="">
								&nbsp;&nbsp;
								</logic:equal>
									月

									<logic:notEqual name="rs" property="dkqx1_day" value="">
										<bean:write name="rs" property="dkqx1_day" />
									</logic:notEqual>
									<logic:equal name="rs" property="dkqx1_day" value="">
								&nbsp;&nbsp;
								</logic:equal>
									日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;至
									<logic:notEqual name="rs" property="dkqx2_year" value="">
										<bean:write name="rs" property="dkqx2_year" />
									</logic:notEqual>
									<logic:equal name="rs" property="dkqx2_year" value="">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:equal>
									年
									<logic:notEqual name="rs" property="dkqx2_mon" value="">
										<bean:write name="rs" property="dkqx2_mon" />
									</logic:notEqual>
									<logic:equal name="rs" property="dkqx2_mon" value="">
								&nbsp;&nbsp;
								</logic:equal>
									月
									<logic:notEqual name="rs" property="dkqx2_day" value="">
										<bean:write name="rs" property="dkqx2_day" />
									</logic:notEqual>
									<logic:equal name="rs" property="dkqx2_day" value="">
								&nbsp;&nbsp;
								</logic:equal>
									日
								</td>
							</tr>

							<tr>
								<td colspan="17">
									借款人声明：
									<br>
									1、以上申请书及其所附资料所填内容为本人所填，且完全属实，本人愿意承担由此产生的一切法律责任；
									<br>
									2、本人承认以此申请书作为向贵行借款的依据，报送的资料复印件可留存贵行作备查凭证；
									<br>
									3、经贵行审查不符合规定的借款条件而未予受理时，本人无异议；
									<br>
									4、本人保证在取得银行贷款后，按时足额偿还贷款本息；
									<br>
									5、未还清银行贷款前，如上述资料发生变化，本人保证在变化后一周内向银行提供更新后的资料。
									<br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;申请人签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;法定监护人签字：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
								</td>
							</tr>
							<tr>
								<td colspan="17">
									学校意见：
									<br>
									1、借款人系我校就读学生；
									<br>
									2、借款人基本信息和家庭经济困难情况属实，同意其申请国家助学贷款。
									<br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;贷款介绍人/学校（签章）：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
								</td>
							</tr>
							<tr>
								<td colspan="17">
									客户经理意见：
									<br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;借款人向我行申请国家助学贷款。根据国家相关政策规定，已委托借款人所在高校向借款申请人收取贷款申请资料，并在现场见证借款申请人本人签字，经初步审核，拟同意其借款申请，并提请部门主管及有权审批人审核。
									<br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;客户经理：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
								</td>
							</tr>
							<tr>
								<td colspan="17">
									部门主管意见：
									<br>
									<div align="center">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;□同意客户经理意见&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;□不同意客户经理意见
										<br />
									</div>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;部门主管：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
								</td>
							</tr>
							<tr>
								<td colspan="17">
									有权审核人意见：
									<br>
									<div align="center">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;□同意发放贷款&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;□不同意发放贷款
										<br />
									</div>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;有权审批人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日
								</td>
							</tr>
						</table>
						
						</html:form>
						<div align="center" class='noPrin'>
							<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
								页面设置
							</button>
							<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
								打印预览
							</button>
							<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
								直接打印
							</button>
						</div>
	</body>
</html>
