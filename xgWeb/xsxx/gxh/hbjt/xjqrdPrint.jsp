<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	</head>

	<body>
		<div class="prompt noPrin">
			<h3>
				<span>最佳打印效果设置：</span>
			</h3>
			<p>
				纸张大小-A4（横向）、页边距(毫米)-左（12.05）、右（12.05）、上（标屏14.05，宽屏12.05）、下（标屏14.05，宽屏12.05），建议每次最多打印100个学生。
			</p>
			<a class="close" title="隐藏"
			   onclick="this.parentNode.style.display='none'"></a>
		</div>
		<div align="center" class='noPrin'>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(8,1);return false;">
				页面设置
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(7,1);return false;">
				打印预览
			</button>
			<button type="button" class='button2' onclick="WebBrowser.ExecWB(6,6);return false;"
				id="printButton">
				直接打印
			</button>
			<br/>
		</div>
		
		
		<center>
			<span style="font-size:22px;font-family:黑体">${xxmc}学生学籍注册信息确认表</span>
		</center>


		<logic:present name="rs">
			<logic:iterate id="r" name="rs" indexId="i">
				<%
					if (i != 0 && i%4 == 0){
				%>
				<br/><br/>
				<%	
					}
				 %>
			
				<%
				if (i == 0 || i % 2 == 0) {
				%>
				<table width="100%">
					<tr>
						<%
						}
						%>

						<td width="50%" algin="center" >
							<table class="printtab" width="99.5%" style="font-size:10px;text-align:center">
								<tr>
									<td rowspan="10" width="40%" >
										<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${r.xh}" width="100" height="128" /> 
									</td>
									<td width="20%" >
										姓名 
									</td>
									<td >
										${r.xm }|
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
								</tr>
								<tr>
									<td  >
										性别
									</td>
									<td>${r.xb }</td>
								</tr>
								<tr>
									<td  >
										班级
									</td>
									<td>${r.bjmc }</td>
								</tr>
								<tr>
									<td  >
										学号
									</td>
									<td>${r.xh }</td>
								</tr>
								<tr>
									<td  >
										考生号
									</td>
									<td>${r.ksh }</td>
								</tr>
								<tr>
									<td  >
										身份证号
									</td>
									<td>${r.sfzh }</td>
								</tr>
								<tr>
									<td  >
										出生年月
									</td>
									<td>${r.csrq }</td>
								</tr>
								<tr>
									<td  >
										政治面貌
									</td>
									<td>${r.zzmmmc }</td>
								</tr>
								<tr>
									<td  >
										民族
									</td>
									<td>${r.mzmc }</td>
								</tr>
								<tr>
									<td  >
										地区
									</td>
									<td>${r.sydsmc }</td>
								</tr>
								<tr>
									<td  >
										<bean:message key="lable.xb" />
									</td>
									<td colspan="2">${r.xymc }</td>
								</tr>
								<tr>
									<td  >
										专业
									</td>
									<td colspan="2">${r.zymc }</td>
								</tr>
								<tr>
									<td  >
										专业方向
									</td>
									<td colspan="2">${r.zyfx }</td>
								</tr>
							</table>
						</td>
						
						<%
						if (i % 2 == 1) {
						%>
					</tr>
				</table>
				<%
				}
				%>
			</logic:iterate>
			
			<%
				List<HashMap<String,String>> rs = (List<HashMap<String,String>>)request.getAttribute("rs");
				
				if (rs.size()%2 == 1){
			  %>
			  	<td width="50%" align="center">
					<table class="printtab" width="99.5%" style="font-size:10px;text-align:center">
						<tr>
							<td rowspan="10" width="40%"></td>
							<td width="20%"  >
								姓名
							</td>
							<td></td>
						</tr>
						<tr>
							<td  >
								性别
							</td>
							<td></td>
						</tr>
						<tr>
							<td  >
								班级
							</td>
							<td></td>
						</tr>
						<tr>
							<td >
								学号
							</td>
							<td></td>
						</tr>
						<tr>
							<td  >
								考生号
							</td>
							<td></td>
						</tr>
						<tr>
							<td >
								身份证号
							</td>
							<td></td>
						</tr>
						<tr>
							<td  >
								出生年月
							</td>
							<td></td>
						</tr>
						<tr>
							<td  >
								政治面貌
							</td>
							<td></td>
						</tr>
						<tr>
							<td  >
								民族
							</td>
							<td></td>
						</tr>
						<tr>
							<td  >
								地区
							</td>
							<td></td>
						</tr>
						<tr>
							<td  >
								<bean:message key="lable.xb" />
							</td>
							<td colspan="2"></td>
						</tr>
						<tr>
							<td >
								专业
							</td>
							<td colspan="2"></td>
						</tr>
						<tr>
							<td >
								专业方向
							</td>
							<td colspan="2"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
			 <%	
				}
			 %>
		</logic:present>
	</body>
</html>

