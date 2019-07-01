<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- 头文件 -->
<html:html>
<body>
	<!-- 打印控件begin -->
	<object id="WebBrowser" width=0 height=0
		classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
			.noPrin{display:none;};
			.PageNext{
			page-break-after: always;
		}
		</style>
	<!-- end -->
	<html:form action="/stu_info_add" method="post">
		<table width="100%" border="0" id="theTable" align="center">
			<tr>
				<td height="80px" align="center">
					<span  height="60px" style="font-family: 黑体;margin-bottom: 10px;vertical-align: bottom"><h2>普通本科高等学校国家助学金申请审批表</h2></span>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="90%" class="printStyle">
						<tr  align="center" >
							<td  rowspan=6 align="center" valign="middle">
								本<br/>人<br/>情<br/>况
								</p>
							</td>
							<td height="45px">
								姓&nbsp;&nbsp;&nbsp;名
							</td>
							<td  height="45px">
								${rs.xm}
							</td>
							<td   height="45px">
								性别
							</td>
							<td colspan=2 >
								${rs.xb}
							</td>
							<td >
								出生年月
							</td>
							<td  >
								${rs.csrq}
							</td>
							<td rowspan=4 >
								<img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
									border="0" align="absmiddle" width="96" height="128"  />
							</td>
						</tr>
						<tr align="center">
							<td height="45px">
								民&nbsp;&nbsp;&nbsp;族
							</td>
							<td >
								${rs.mzmc}
							</td>
							<td >
								政治<br/>面貌
							</td>
							<td colspan=2 >
								${rs.zzmmmc}
							</td>
							<td >
								入学时间
							</td>
							<td >
								${rs.rxrq}
							</td>
						</tr>
						<tr align="center" height="25px">
							<td >
								身份证号码
							</td>
							<td colspan=6 >
								${rs.sfzh }
							</td>
						</tr>
						<tr align="center" height="25px">
							<td >
								联系电话
							</td>
							<td colspan=6 >
								${rs.sjhm }
							</td>
						</tr>
						<tr  align="center" height="25px">
							<td  colspan=8 >
								<p>
									<span lang=EN-US >${rs.xxmc}</span>
									<span lang=EN-US style="margin-left:40px ">${rs.xymc}</span>
									<span lang=EN-US style="margin-left:40px ">${rs.nj}</span>
									<span lang=EN-US style="margin-left:40px "> ${rs.bjmc}</span> 
								</p>
							</td>
						</tr>
						<tr height="45px"  align="center">
							<td colspan=2 >
								曾获何种奖励
							</td>
							<td  colspan=6 >
								&nbsp;
							</td>
						</tr>
						<tr height="45px" align="center">
							<td rowspan=3 >
								家<br/>庭<br/>经<br/>济<br/>情<br/>况
							</td>
							<td >
								家庭户口
							</td>
							<td colspan=5 >
								<logic:present name="rs" property="jthk">
									<logic:equal value="城镇" property="jthk" name="rs">
										城镇
									</logic:equal>
									<logic:equal value="农村" property="jthk" name="rs">
										农村
									</logic:equal>
								</logic:present>
								<logic:notPresent name="rs" property="jthk">
									A、城镇 B、农村
								</logic:notPresent>	
							</td>
							<td  height="45px" align="center" >
								家庭人<br/>口总数
							</td>
							<td >
								${rs.jtrs}
							</td>
						</tr>
						<tr height="45px" align="center">
							<td >
								家庭月总收入
							</td>
							<td >
								${rs.jtyzsr}
							</td>
							<td colspan=3 >
								人均月收入
							</td>
							<td >
								${rs.jtrjysr}
							</td>
							<td >
								收入<br/>来源
							</td>
							<td >
								${rs.srly }
							</td>
						</tr>
						<tr height="45px" align="center">
							<td >
								家庭住址
							</td>
							<td colspan=5 >
								${rs.jtdz }
							</td>
							<td >
								邮政<br/>编码
							</td>
							<td >
								${rs.jtyb }
							</td>
						</tr>
						<tr align="center" >
							<td rowspan=6 valign="center">
								家<br/>庭<br/>成<br/>员<br/>情<br/>况
							</td>
							<td >
								姓&nbsp;名
							</td>
							<td >
								年龄
							</td>
							<td colspan=3 >
								与本人关系
							</td>
							<td colspan=3 >
								工作或学习单位
							</td>
						</tr>
						<logic:iterate name="cyList" id="cy">
						<tr >
							<td align="center">
									${cy.cyxm }&nbsp;
							</td>
							<td align="center">
									${cy.cynl }&nbsp;
							</td>
							<td align="center"  colspan="3">
									${cy.mc }&nbsp;
							</td>
							<td align="center"  colspan="3">
									${cy.cyzy }&nbsp;
							</td>
						</tr>
						</logic:iterate>
						<tr style="height:250px">
							<td colspan=9 valign=top >
								<span style='font-family:宋体;"Times New Roman";vertical-align: top;height: 200px' >申请理由：${rs.sqly }</span>
								<br/>
								<span lang=EN-US style="float: right;margin-right: 50px">
									<span  style="margin-right: 200px">申请人签名：</span>
									<span style='font-family:宋体;
 										 &quot;Times New Roman&quot;'>年</span><span
										lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><span
										style='font-family:宋体;"Times New Roman"'>月</span><span
										lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><span
										style='font-family:宋体;"Times New Roman"'>日</span>
								</span>
							</td>
						</tr>
						<tr style="height:150px">
							<td colspan=9 valign=top >
								<span style='font-family:宋体;"Times New Roman";vertical-align: top;height: 100px' >学校审核意见：${rs.shzt3yj }</span>
								<br/>
								<span lang=EN-US style="float: right;margin-right: 50px">
									<span  style="margin-right: 250px">(公章)</span>
									<span style='font-family:宋体;
 										 &quot;Times New Roman&quot;'>年</span><span
										lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><span
										style='font-family:宋体;"Times New Roman"'>月</span><span
										lang=EN-US>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span><span
										style='font-family:宋体;"Times New Roman"'>日</span>
								</span>
								</p>
							</td>
						</tr>
						<tr height=0>
							<td width=7.5%></td>
							<td width=14%></td>
							<td width=8%></td>
							<td width=7% ></td>
							<td width=4% ></td>
							<td width=5% ></td>
							<td width=10% ></td>
							<td width=8% ></td>
							<td width=10% ></td>
						</tr>
					</table>
					
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
