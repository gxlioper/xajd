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
				<td align="center">
					<h2>
						贵州大学<logic:present name="rs">${rs.xn }</logic:present>
						<logic:notPresent name="rs">200&nbsp;&nbsp;&nbsp;&nbsp;—&nbsp;&nbsp;200&nbsp;&nbsp;&nbsp;&nbsp;</logic:notPresent>
						年度奖学金审批表
					</h2>
				</td>
			</tr>
			<tr>
				<td align="left">
					学号：${rs.xh } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<bean:message key="lable.xb" />：${rs.xymc }
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="4%"></td>
							<td width="6%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
							<td width="10%"></td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="2">姓名</td>
							<td align="center" colspan="2">${rs.xm }</td>
							<td align="center">专业</td>
							<td align="center" colspan="2">${rs.zymc }</td>
							<td align="center">年级</td>
							<td align="center">${rs.nj }</td>
							<td align="center">性别</td>
							<td align="center">${rs.xb }</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="2">年龄</td>
							<td align="center"></td>
							<td align="center">民族</td>
							<td align="center">${stuMap.mzmc }</td>
							<td align="center">政治面貌</td>
							<td align="center" colspan="2">
								${stuMap.zzmmmc }
							</td>
							<td align="center">任何职务</td>
							<td align="center" colspan="2">
								${stuMap.zw }
							</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="3">是否受过处分</td>
							<td align="center" colspan="2">
								<logic:present name="rs">
									是〇否√
								</logic:present>
								<logic:notPresent name="rs">
									是〇否〇
								</logic:notPresent>
							</td>
							<td align="center">奖学金名称</td>
							<td align="center" colspan="2">${rs.jxjmc }</td>
							<td align="center" >是否欠学费</td>
							<td align="center" colspan="2">
								<logic:present name="rs">
									<logic:equal value="是" property="sfqf" name="rs">
										是√否〇
									</logic:equal>
									<logic:equal value="否" property="sfqf" name="rs">
										是〇否√
									</logic:equal>
									<logic:equal value="" property="sfqf" name="rs">
										是〇否〇
									</logic:equal>
								</logic:present>
								<logic:notPresent name="rs">
									是〇否〇
								</logic:notPresent>
							</td>
						</tr>
						
						<%
							List<HashMap<String,String>> clList1 = (List<HashMap<String,String>>)request.getAttribute("cjList1");
							List<HashMap<String,String>> clList2 = (List<HashMap<String,String>>)request.getAttribute("cjList2");
							
							int m = null !=clList1 && !clList1.isEmpty() ?  Double.valueOf(clList1.size()/3).intValue()+1 : 0;
							int n = null !=clList2 && !clList2.isEmpty() ?  Double.valueOf(clList2.size()/3).intValue()+1 : 0;
						 %>
						
						<tr height="40px">
							<td align="center" rowspan="<%=m+n+2 %>">原<br/><br/>始<br/><br/>成<br/><br/>绩</td>
							<td align="center" rowspan="<%=m+1 %>">第<br/><br/>一<br/><br/>学<br/><br/>期<br/><br/>成<br/><br/>绩</td>
							<td align="center" colspan="2">科目</td>
							<td align="center">成绩</td>
							<td align="center" colspan="2">科目</td>
							<td align="center">成绩</td>
							<td align="center" colspan="2">科目</td>
							<td align="center">成绩</td>
						</tr>
						<%
							int i = 0;
							while (i<m*3){
						 %>
						
						<tr height="40px">
							<td align="center" colspan="2">&nbsp;<%=i<clList1.size() ? clList1.get(i).get("kcmc") : "" %></td>
							<td align="center">&nbsp;<%=i<clList1.size() ? clList1.get(i).get("cj") : "" %></td>
							<%
								i++;
							 %>
							<td align="center" colspan="2">&nbsp;<%=i<clList1.size() ? clList1.get(i).get("kcmc") : "" %></td>
							<td align="center">&nbsp;<%=i<clList1.size() ? clList1.get(i).get("cj") : "" %></td>
							<%
								i++;
							 %>
							<td  align="center"colspan="2">&nbsp;<%=i<clList1.size() ? clList1.get(i).get("kcmc") : "" %></td>
							<td align="center">&nbsp;<%=i<clList1.size() ? clList1.get(i).get("cj") : "" %></td>
							<%
								i++;
							 %>
						</tr>
						<%
							}
						 %>
						<tr height="40px">
							<td align="center" rowspan="<%=n+1 %>">第<br/><br/>二<br/><br/>学<br/><br/>期<br/><br/>成<br/><br/>绩</td>
							<td align="center" colspan="2">科目</td>
							<td align="center">成绩</td>
							<td align="center" colspan="2">科目</td>
							<td align="center">成绩</td>
							<td align="center" colspan="2">科目</td>
							<td align="center">成绩</td>
						</tr>
						
						<%
							i = 0;
							while (i<n*3){
						 %>
						
						<tr height="40px">
							<td colspan="2" align="center">&nbsp;<%=i<clList2.size() ? clList2.get(i).get("kcmc") : "" %></td>
							<td align="center">&nbsp;<%=i<clList2.size() ? clList2.get(i).get("cj") : "" %></td>
							<%
								i++;
							 %>
							<td align="center" colspan="2">&nbsp;<%=i<clList2.size() ? clList2.get(i).get("kcmc") : "" %></td>
							<td align="center">&nbsp;<%=i<clList2.size() ? clList2.get(i).get("cj") : "" %></td>
							<%
								i++;
							 %>
							<td  align="center"colspan="2">&nbsp;<%=i<clList2.size() ? clList2.get(i).get("kcmc") : "" %></td>
							<td align="center">&nbsp;<%=i<clList2.size() ? clList2.get(i).get("cj") : "" %></td>
							<%
								i++;
							 %>
						</tr>
						<%
							}
						 %>
						<tr height="40px">
							<td align="center" colspan="3">班级总人数：</td>
							<td align="center">${stuMap.bjrs }</td>
							<td align="center" colspan="2">平均成绩:</td>
							<td align="center">${stuMap.zycj }</td>
							<td align="center" colspan="2">总成绩排名：</td>
							<td align="center" colspan="2">${stuMap.zypm }</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="3">综合测评名次:</td>
							<td align="center">${stuMap.zpm }</td>
							<td align="center" colspan="2">综合测评分数:</td>
							<td align="center">${stuMap.fs }</td>
							<td align="center" colspan="2">补考科目数：</td>
							<td align="center" colspan="2">${stuMap.bkkms }</td>
						</tr>
						<tr height="100px">
							<td align="center" colspan="2">其他获<br/>奖情况</td>
							<td colspan="9"></td>
						</tr>
					</table>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<br/>
					<table width="100%" class="tbstyle">
						<tr>
							<td width="8%" align="center">班<br/><br/>级<br/><br/>意<br/><br/>见</td>
							<td>
								<p style="height:160px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.fdyyj }
									<div style="margin-bottom: 0px" align="right">
										审核人：${rs.fdyshr }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										审核结果：${rs.fdysh }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
										班长签名:
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<logic:present property="xyshsj" name="rs">
											<%=((HashMap<String,String>)request.getAttribute("rs")).get("fdyshsj").substring(0,4) %>
											&nbsp;&nbsp;年&nbsp;&nbsp;
											<%=((HashMap<String,String>)request.getAttribute("rs")).get("fdyshsj").substring(5,7) %>
											&nbsp;&nbsp;月
											&nbsp;&nbsp;
											<%=((HashMap<String,String>)request.getAttribute("rs")).get("fdyshsj").substring(8,10) %>
											&nbsp;&nbsp;
											日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:present>
										<logic:notPresent  property="xyshsj" name="rs">
											年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:notPresent>
									</div>
								</p>
							</td>
						</tr>
						<tr>
							<td align="center">学<br/><br/>院<br/><br/>意<br/><br/>见</td>
							<td>
								<p style="height:140px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyyj }
									<div style="margin-bottom: 0px" align="right">
										审核人：${rs.xyshr }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										审核结果：${rs.xysh }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
										盖&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;章&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
										<logic:present property="xyshsj" name="rs">
											<%=((HashMap<String,String>)request.getAttribute("rs")).get("xyshsj").substring(0,4) %>
											&nbsp;&nbsp;年&nbsp;&nbsp;
											<%=((HashMap<String,String>)request.getAttribute("rs")).get("xyshsj").substring(5,7) %>
											&nbsp;&nbsp;月
											&nbsp;&nbsp;
											<%=((HashMap<String,String>)request.getAttribute("rs")).get("xyshsj").substring(8,10) %>
											&nbsp;&nbsp;
											日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:present>
										<logic:notPresent  property="xyshsj" name="rs">
											年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:notPresent>
									</div>
								</p>
							</td>
						</tr>
						<tr>
							<td align="center">学<br/>校<br/>审<br/>批<br/>意<br/>见<br/></td>
							<td>
								<p style="height:140px">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxyj }
									<div style="margin-bottom: 0px" align="right">
										
										审核人：${rs.xxshr }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										审核结果：${rs.xxsh }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
										盖&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;章&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
										<logic:present property="xxshsj" name="rs">
											<%=((HashMap<String,String>)request.getAttribute("rs")).get("xxshsj").substring(0,4) %>
											&nbsp;&nbsp;年&nbsp;&nbsp;
											<%=((HashMap<String,String>)request.getAttribute("rs")).get("xxshsj").substring(5,7) %>
											&nbsp;&nbsp;月
											&nbsp;&nbsp;
											<%=((HashMap<String,String>)request.getAttribute("rs")).get("xxshsj").substring(8,10) %>
											&nbsp;&nbsp;
											日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:present>
										<logic:notPresent  property="xxshsj" name="rs">
											年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											日&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:notPresent>
										<br/>
										
										
									</div>
								</p>
							</td>
						</tr>
						<tr>
							<td align="center">备<br/><br/>注</td>
							<td>
								<p style="height:140px">
									
								</p>
							</td>
						</tr>
					</table>
					<div align="left">
						&nbsp;&nbsp;&nbsp;&nbsp;此表审批完毕后由<bean:message key="lable.xb" />保存、装档，每一栏（除备注外）必需填写。
					</div>
				</td>
			</tr>
		</table>
		<div align="center" class='noPrin'>
		<button class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
			页面设置
		</button>
		<button class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
			打印预览
		</button>
		<button class='button2' onclick="WebBrowser.ExecWB(6,6);return false;">
			直接打印
		</button>
	</div>
	</html:form>
</body>
</html:html>
