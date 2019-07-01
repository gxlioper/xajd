<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List"/>
<jsp:directive.page import="java.util.HashMap"/>
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
						贵州大学
							<logic:present name="rs">${rs.xn }</logic:present>
						<logic:notPresent name="rs">200&nbsp;&nbsp;&nbsp;&nbsp;—&nbsp;&nbsp;200&nbsp;&nbsp;&nbsp;&nbsp;</logic:notPresent>
						年度先进个人申报表
					</h2>
				</td>
			</tr>
			<tr>
				<td align="left">
					学号：${rs.xh } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<bean:message key="lable.xb" />：${rs.xymc }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					申报类别：${rs.rychmc }
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
						<tr>
							<td width="10%"></td>
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
						<tr height="50px">
							<td align="center">姓名</td>
							<td align="center">${rs.xm }</td>
							<td align="center">专业</td>
							<td align="center" colspan="2">${rs.zymc }</td>
							<td align="center" colspan="2">班级</td>
							<td align="center">${rs.bjmc }</td>
							<td align="center" rowspan="3" colspan="2">
								<img
													src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh }"
													border="0" align="absmiddle" style="width:140;height:140" />
							</td>
						</tr>
						<tr height="50px">
							<td align="center">民族</td>
							<td align="center">${stuMap.mzmc }</td>
							<td align="center">性别</td>
							<td align="center">${rs.xb }</td>
							<td align="center" colspan="2">政治面貌</td>
							<td align="center" colspan="2">${stuMap.mzmmmc }</td>
						</tr>
						<tr height="50px">
							<td align="center" colspan="5">评优学年担任职务（申报优干必须填写）</td>
							<td align="center" colspan="3"></td>
						</tr>
						
						
						<%
							List<HashMap<String,String>> clList1 = (List<HashMap<String,String>>)request.getAttribute("cjList1");
							List<HashMap<String,String>> clList2 = (List<HashMap<String,String>>)request.getAttribute("cjList2");
							
							int m = null !=clList1 && !clList1.isEmpty() ?  Double.valueOf(clList1.size()/3).intValue()+1 : 0;
							int n = null !=clList2 && !clList2.isEmpty() ?  Double.valueOf(clList2.size()/3).intValue()+1 : 0;
						 %>
						
						
						<tr height="50px">
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
						 
						 
						 <tr height="50px">
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
						
						<tr height="50px">
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
							<td align="center" rowspan="3">综<br/><br/>合<br/><br/>测<br/><br/>评</td>
							<td align="center">班级<br/>人数</td>
							<td align="center">总成绩<br/>排名</td>
							<td align="center">平均<br/>成绩</td>
							<td align="center" colspan="2">综合测<br/>评分数</td>
							<td align="center" colspan="2">综合测<br/>评名次</td>
							<td align="center" colspan="2">补考科目数<br/>（无补考者填无）</td>
						</tr>
						<tr height="40px">
							<td align="center">${stuMap.bjrs }</td>
							<td align="center">${stuMap.zypm }</td>
							<td align="center">${stuMap.zycj }</td>
							<td align="center" colspan="2">${stuMap.fs }</td>
							<td align="center" colspan="2">${stuMap.zpm }</td>
							<td align="center" colspan="2">${stuMap.bkkms }</td>
						</tr>
						<tr height="40px">
							<td align="center" colspan="3">是否受过处分</td>
							<td align="center">
								<logic:present name="rs">
									<logic:equal value="yes" property="iscf" name="rs">
										是√否〇
									</logic:equal>
									<logic:equal value="no" property="iscf" name="rs">
										是〇否√
									</logic:equal>	
								</logic:present>
								<logic:notPresent name="rs">
									是〇否〇
								</logic:notPresent>
							
							</td>
							<td align="center" colspan="3">是否欠学费</td>
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
						<tr height="80px">
							<td align="center">备<br/><br/>注</td>
							<td align="center" colspan="9"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr style="display:none">
				<td align="center">
					<br/><br/><br/>
					<table width="100%" class="tbstyle">
						<tr>
							<td>
								<div style="border-top: 0px" align="center"><h2>个人鉴定</h2></div>
								<p style="height:850px">
									
								</p>
							</td>
						</tr>
					</table>
					<br/><br/>
				</td>
			</tr>
			<tr>
				<td align="center">
					<br/><br/><br/><br/><br/><br/>
					<br/><br/><br/><br/><br/><br/>
					<br/><br/><br/>
					
					
					
					<table width="100%" class="tbstyle">
						<tr style="display:none">
							<td colspan="2">
								<p style="height:160px">
									
								</p>
							</td>
						</tr>
						<tr>
							<td align="center" width="10%">班级<br/>意见</td>
							<td>
								<p style="height:170px">
									${rs.fdyyj }
									<div align="right" style="margin-bottom: 0px">
										审核人：${rs.fdyshr }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										审核结果：${rs.fdysh }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
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
							<td align="center"><bean:message key="lable.xb" /><br/>意见</td>
							<td>
								<p style="height:170px" style="margin-bottom: 0px">
									${rs.xyyj }
									<div align="right">
										审核人：${rs.xyshr }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										审核结果：${rs.xysh }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
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
							<td align="center">校审<br/><br/>批意<br/><br/>见</td>
							<td>
								<p style="height:170px">
									${rs.xxyj }
									<div align="right" style="margin-bottom: 0px">
										审核人：${rs.xxshr }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										审核结果：${rs.xxsh }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/>
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
									</div>
								</p>
							</td>
						</tr>
					</table>
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
