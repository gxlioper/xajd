<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- 头文件 -->
<html:html>
<body>
	<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	<!-- end -->
	<html:form action="/typj" method="post">
		<table width="95%" border="0" id="theTable" align="center">
			<tr>
				<td align="left">
					<h3>
						509
					</h3>
				</td>
			</tr>
			<tr>
				<td align="center">
					<h2>
						闽江学院学生临时困难补助申请表V1.4
					</h2>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="5%"></td>
							<td width="4%"></td>
							<td width="4%"></td>
							<td width="6%"></td>
							<td width="5%"></td>
							<td width="4%"></td>
							<td width="5%"></td>
							<td width="6%"></td>
							<td width="8%"></td>
							<td width="1%"></td>
							<td width="3%"></td>
							<td width="7%"></td>
							<td width="10%"></td>
							<td width="3%"></td>
							<td width="5%"></td>
							<td width="4%"></td>
							<td width="2%"></td>
							<td width="1%"></td>
							<td width="10%"></td>
							<td width="7%"></td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="2">姓名</td>
							<td align="center" colspan="2">${rs.xm }</td>
							<td align="center" colspan="2">性别</td>
							<td align="center">${rs.xb }</td>
							<td align="center">民族</td>
							<td align="center"colspan="2">${rs.mzmc }</td>
							<td align="center"colspan="2">学号</td>
							<td align="center"colspan="3">${rs.xh }</td>
							<td align="center"colspan="2">宿舍</td>
							<td align="center"colspan="3">${rs.ssbh }</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="2">单位</td>
							<td align="center" colspan="13">${rs.xymc }&nbsp;&nbsp;系&nbsp;&nbsp;${rs.nj }&nbsp;&nbsp;年级&nbsp;&nbsp;${rs.zymc }&nbsp;&nbsp;专业&nbsp;&nbsp;</td>
							<td align="center" colspan="2">电话</td>
							<td align="center" colspan="3">${rs.lxdh }</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="4">家庭地址及邮<br/>政编码</td>
							<td align="center" colspan="6">${rs.jtdz }&nbsp;&nbsp;${rs.jtyb }</td>
							<td align="center" colspan="2">家庭<br/>电话</td>
							<td align="center" colspan="3">${rs.jtdh }</td>
							<td align="center" colspan="3">申请补<br/>助金额</td>
							<td align="center" colspan="2"></td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="10">当年院（系）学习成绩排名:&nbsp;&nbsp;&nbsp;&nbsp;${rs.cjpmbl }&nbsp;&nbsp;%</td>
							<td align="center" colspan="10">当年院（系）综合测评排名:&nbsp;&nbsp;&nbsp;&nbsp;${rs.zjfpmBl }&nbsp;&nbsp;%</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="3" rowspan="2">是否已<br/>贷款<br/>及金额</td>
							<td align="center" colspan="2" rowspan="2">${rs.sfydk }<br/>${rs.ydkje }</td>
							<td align="center" colspan="3" rowspan="2">接受何团体（或个人）的资助及金额</td>
							<td align="center" colspan="2" rowspan="2">${rs.jsnzz }<br/>${rs.jszzje }</td>
							<td align="center" colspan="2" rowspan="2">何时领过<br/>困难补助<br/>及金额</td>
							<td align="center" colspan="2" rowspan="2">${rs.hslgbz }<br/>${rs.bzje }</td>
							<td align="center" colspan="4">上学期是否<br/>做勤工</td>
							<td align="center" colspan="2">${rs.sxqqgje }元/月×${rs.sxqqgys }&nbsp;月</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="4">这学期是否<br/>做勤工</td>
							<td align="center" colspan="2">${rs.bxqqgje }元/月×${rs.bxqqgys }&nbsp;月</td>
						</tr>
						
						<%
							List<HashMap<String, String>> cyList = (List<HashMap<String, String>>)request.getAttribute("cyList");
						 %>
						
						<tr height="40px">
							<td align="center" rowspan="<%=cyList.size()/2+2 %>">家<br/>庭<br/>人<br/>口<br/>情<br/>况</td>
							<td align="center" colspan="3">家庭<br/>户口</td>
							<td align="center" colspan="3">${rs.jthk }</td>
							<td align="center" colspan="4">家庭总人口</td>
							<td align="center"></td>
							<td align="center" colspan="2">家庭总收入</td>
							<td align="center" colspan="4"></td>
							<td align="center">人均月收入</td>
							<td align="center">元</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="3">姓名</td>
							<td align="center">年<br/>龄</td>
							<td align="center" colspan="2">称谓</td>
							<td align="center" colspan="4">在何处工作<br/>或学习</td>
							<td align="center">每月收入</td>
							<td align="center" colspan="2">姓名</td>
							<td align="center">年龄</td>
							<td align="center" colspan="3">称谓</td>
							<td align="center">在何处工作或<br/>学习</td>
							<td align="center">每月<br/>收入</td>
						</tr>
						
						<%
							int i=0;
							while (i<cyList.size()){
						 %>
							<tr height="40px">
								<td align="center" colspan="3">
									<%=null==cyList.get(i).get("cyxm")?"":cyList.get(i).get("cyxm") %>&nbsp;
								</td>
								<td align="center">
									<%=null==cyList.get(i).get("cynl")?"":cyList.get(i).get("cynl") %>&nbsp;
								</td>
								<td align="center" colspan="2">
									<%=null==cyList.get(i).get("mc")?"":cyList.get(i).get("mc") %>
								</td>
								<td align="center" colspan="4">
									<%=null==cyList.get(i).get("cygzdw")?"":cyList.get(i).get("cygzdw") %>&nbsp;
								</td>
								<td align="center">
									<%=null==cyList.get(i).get("cynsr")?"":Math.rint(Integer.parseInt(cyList.get(i).get("cynsr"))/12) %>
								</td>
								<%
									i++;
								 %>
								<td align="center" colspan="2">
									<%=null==cyList.get(i).get("cyxm")?"":cyList.get(i).get("cyxm") %>&nbsp;
								</td>
								<td align="center">
									<%=null==cyList.get(i).get("cynl")?"":cyList.get(i).get("cynl") %>&nbsp;
								</td>
								<td align="center" colspan="3">
									<%=null==cyList.get(i).get("mc")?"":cyList.get(i).get("mc") %>
								</td>
								<td align="center">
									<%=null==cyList.get(i).get("cygzdw")?"":cyList.get(i).get("cygzdw") %>&nbsp;
								</td>
								<td align="center">
									<%=null==cyList.get(i).get("cynsr")?"":Math.rint(Integer.parseInt(cyList.get(i).get("cynsr"))/12) %>
								</td>
							</tr>
						
						<%
							i++;
							}
						 %>
						 <tr>
						 	<td align="center">申<br/>请<br/>理<br/>由</td>
						 	<td colspan="19">
						 		<p style="height:100px">
						 			&nbsp;&nbsp;&nbsp;&nbsp;
						 			${rs.sqly }
						 		</p>
						 		本人保证上述内容真实无误。
						 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 		本人签名：
						 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 	</td>
						 </tr>
						 <tr height="40px">
						 	<Td colspan="7" align="center">班级认定评议小组意见</Td>
						 	<Td colspan="6" align="center">院系资助工作组意见</Td>
						 	<Td colspan="7" align="center">校学生资助管理中心审批意见</Td>
						 </tr>
						 <tr height="40px">
						 	<Td colspan="7">
						 		<p style="height:100px">
						 		
						 		</p>
						 		<div align="right">
						 			负责人签名：
						 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			<br/>
						 			年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			日&nbsp;&nbsp;&nbsp;&nbsp;
						 		</div>
						 	</Td>
						 	<Td colspan="6">
						 		<p style="height:80px">
						 		
						 		</p>
						 		<div align="right">
						 			负责人签名：
						 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			<br/>
						 			（盖公章）
						 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			<br/>
						 			年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			日&nbsp;&nbsp;&nbsp;&nbsp;
						 		</div>
						 	</Td>
						 	<Td colspan="7">
						 		<p style="height:80px">
						 		经会议研究决定，同意补助该生<br/>__________元整。
						 		</p>
						 		<div align="right">
						 			负责人签名：
						 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			<br/>
						 			（盖公章）
						 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			<br/>
						 			年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 			日&nbsp;&nbsp;&nbsp;&nbsp;
						 		</div>
						 	</Td>
						 </tr>
					</table>
					
				</td>
			</tr>
		</table>
	</html:form>
	<div align="center" class='noPrin'>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			页面设置
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			打印预览
		</button>
		<button type="button"  class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			直接打印
		</button>
	</div>
</body>
</html:html>
