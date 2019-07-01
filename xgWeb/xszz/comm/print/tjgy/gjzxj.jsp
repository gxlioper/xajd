<%@ page language="java" pageEncoding="GB18030"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<style>
			.radic {
				position:relative;
			}
			.radic em {
				position:absolute;
				top:3px; 
				left:-4px;
				font-family:Arial;
				font-size:22px;
			}
		</style>
	</head>
	<body>
		<p style="height:50px">&nbsp;</p>
		<center>
			<span style="font-size:21px;font-family:黑体">普通本科高校、高等职业学校国家助学金申请表</span>
			<br />
			<br />
		</center>
		<table class="printtab" style="font-family:仿宋_GB2312;font-size:14px;width:17.67cm;">
			<tr class="nowrap" >
				<td width="7%" rowspan="4" align="center" valign="center">
						本人
						<br/>
						情况
				</td>
				<td align="center" width="19%" valign="center">
						姓名
				</td>
				<td align="center" width="14%" valign="center">
						${rs.xm }
				</td>
				<td align="center" width="10%" valign="center">
					性别
				</td>
				<td align="center" width="10%" valign="center">
					${rs.kzzd1 }
				</td>
				<td align="center" width="14%" valign="center">
						出生年月
				</td>
				<td align="center" width="18%" valign="center">
						${rs.kzzd2 }
				</td>
				<td rowspan="4" align="center" width="110px" valign="center" style="padding:0px 0px 0px 0px">
						<img src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
									border="0" align="absmiddle" style="width:110px;height:140px" />
				</td>
			</tr>
			<tr class="nowrap">
				<td align="center">
						民族
				</td>
				<td align="center">
						${rs.kzzd3 }
				</td>
				<td align="center" style="padding:0px 0px 0px 0px">
						政治<br/>面貌
				</td>
				<td align="center">
						${rs.kzzd4 }
				</td>
				<td align="center">
						入学时间
				</td>
				<td align="center">
						${rs.kzzd5 }
				</td>
			</tr>
			<tr class="nowrap">
				<td align="center">
						公民身份号码
				</td>
				<td align="center" colspan="3">
						${rs.kzzd6 }
				</td>
				<td align="center">
						联系电话
				</td>
				<td align="center">
						${rs.kzzd7 }
				</td>
			</tr>
			<tr class="nowrap">
				<td align="center" valign="center" colspan="6" style="position:relative;">
					&nbsp;
					<span style="position:absolute;right:300px;">${xxmc}</span>
					<span style="position:absolute;right:200px;">${rs.kzzd8}<bean:message key="lable.xb" /></span>
					<span style="position:absolute;right:100px;">${rs.kzzd9}系</span>
					<span style="position:absolute;right:0px;">${rs.kzzd10}班</span>
				</td>
			</tr>
			<tr class="nowrap">
				<td align="center" rowspan="3">
						家
						<br />
						庭
						<br />
						经
						<br />
						济
						<br />
						情
						<br />
						况
				</td>
				<td align="center">
						家庭户口
				</td>
				<td align="center" colspan="4" style="padding:0px 0px 0px 0px">
						<logic:present name="rs" property="jthk">
							<logic:equal value="城镇" property="jthk" name="rs">
								<span class="radic">A<em>√</em></span>、城镇&nbsp;&nbsp;&nbsp;&nbsp;B、农村
							</logic:equal>
							<logic:equal value="农村" property="jthk" name="rs">
								A、城镇&nbsp;&nbsp;&nbsp;&nbsp;<span class="radic">B<em>&radic;</em></span>、农村
							</logic:equal>
							<logic:equal value="" property="jthk" name="rs">
								A、城镇 B、农村
							</logic:equal>
						</logic:present>
						<logic:notPresent name="rs" property="jthk">
							A、城镇 B、农村
						</logic:notPresent>
				</td>
				<td align="center">
						家庭人口总数
				</td>
				<td align="center">
						${rs.jtrs }
				</td>
			</tr>
			<tr class="nowrap">
				<td align="center">
						家庭月总收入
				</td>
				<td align="center">
						${rs.jtyzsr }
				</td>
				<td align="center" colspan="2">
						人均月收入
				</td>
				<td align="center">
						${rs.jtrjysr }
				</td>
				<td align="center">
						收入来源
				</td>
				<td align="center">
						${rs.srly }
				</td>
			</tr>
			<tr class="nowrap">
				<td align="center">
						家庭住址
				</td>
				<td align="center" colspan="4">
						${rs.jtdz }
				</td>
				<td align="center">
						邮政编码
				</td>
				<td align="center">
						${rs.jtyb }
				</td>
			</tr>
			<tr class="nowrap" style="height:35px">
				<td align="center" rowspan="5">
						家<br/>
						庭<br/>
						成<br/>
						员<br/>
						情<br/>
						况<br/>
				</td>
				<td align="center">
						姓名
				</td>
				<td align="center">
						年龄
				</td>
				<td align="center" colspan="2">
						与本人关系
				</td>
				<td align="center" colspan="3">
						工作或学习单位
				</td>
			</tr>
			<logic:iterate name="cyList" id="cy" length="4">
			<tr class="nowrap" style="height:35px">
				<td align="center">
						${cy.cyxm }
				</td>
				<td align="center">
						${cy.cynl }
				</td>
				<td align="center" colspan="2">
						${cy.mc }
				</td>
				<td align="center" colspan="3">
						${cy.cygzdw }
				</td>
			</tr>
			</logic:iterate>
			<%
				int cyNum = Integer.valueOf(request.getAttribute("cyNum").toString());
				for (int i = 0 ; i < 4-cyNum; i++){
			 %>
			<tr class="nowrap" style="height:35px">
				<td align="center">
						&nbsp;
				</td>
				<td align="center">
						&nbsp;
				</td>
				<td align="center" colspan="2">
						&nbsp;
				</td>
				<td align="center" colspan="3">
						&nbsp;
				</td>
			</tr>
			<%
				}
			 %>
			
			<tr >
				<td colspan="8" style="padding:0px 0px 0px 0px">
					<p>
						&nbsp;申请理由
					</p>
					<p style="height:100px;">
						<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
					</p>
					<p>
						&nbsp;&nbsp;&nbsp;&nbsp;承诺每学年应参加勤工助学的时间不少于80小时，或参与社会公益活动、实践活动的时间不少于20小时。
					</p>
					<p align="right">
						<br/>
						申请人签名：
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<span style="width:200px;">
							<script>document.write("&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日");</script>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</span>
						
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="8" style="position:relative;">
					学校资助管理机构评审意见：
					<p style="height:70px">
						<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.shzt1yj }
					</p>
					&nbsp;
					<p align="right" style="position:absolute;right:95px;">
						（公章） 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<span style="width:180px;" align="right">
							<logic:notEqual name="rs" property="shsj1" value="">
								${rs.shsj1 }
							</logic:notEqual>
							<logic:equal name="rs" property="shsj1" value="">
								&nbsp;&nbsp;&nbsp;&nbsp;
								年&nbsp;&nbsp;月&nbsp;&nbsp;日
							</logic:equal>
						</span>
					</p>
				</td>
			</tr>
			<tr>
				<td colspan="8" valign="top" style="position:relative;">
					<br/>
					学校审核意见：
					<p style="height:70px">
						<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.shzt2yj }
					</p>
					&nbsp;
					<p align="right" style="position:absolute;right:95px;">
						（公章） 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<span style="width:180px;" align="right">
							<logic:notEqual name="rs" property="shsj2" value="">
								${rs.shsj2 }
							</logic:notEqual>
							<logic:equal name="rs" property="shsj2" value="">
								&nbsp;&nbsp;&nbsp;&nbsp;
								年&nbsp;&nbsp;月&nbsp;&nbsp;日
							</logic:equal>
						</span>
					</p>
				</td>
			</tr>
		</table>
		<p>
			&nbsp;
		</p>
		<p>
			&nbsp;
		</p>

		<div align="center" class='noPrin'>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				页面设置
			</button>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				打印预览
			</button>
			<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;"
				id="printButton">
				直接打印
			</button>
		</div>
	</body>
</html>
