<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<jsp:directive.page import="java.util.ArrayList" />
<jsp:directive.page import="java.util.HashMap" />
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
		<table width="100%" border="0" id="theTable" align="center">
			<tr>
				<td align="center">
					<h2>
						（${rs.xn}学年）普通高校国家助学金申请审批表
					</h2>
				</td>
			</tr>
			<tr>
				<td align="left">
					<div style="font-size:15px;">
						<b>
						学校：金华职业技术学院
						<bean:message key="lable.xb" />：${rs.xymc }
						专业：${rs.zymc }
						班级：${rs.bjmc }
						</b>
					</div>
				</td>
			</tr>
			<tr>
				<td align="center">
					
					<table width="100%" class="printstyle">
						<tr>
							<td width="4.5%"></td>
							<td width="13.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
							<td width="4.5%"></td>
						</tr>
						<tr height="30px">
							<td align="center" rowspan="4">本人<br/>情况</td>
							<td align="center">姓名</td>
							<td align="center" colspan="4">${rs.xm }</td>
							<td align="center" colspan="2">性别</td>
							<td align="center" colspan="4">${rs.xb }</td>
							<td align="center" colspan="3">出生年月</td>
							<td align="center" colspan="5">${rs.csrq }</td>
							
						</tr>
						<tr height="30px">
							<td align="center">学号</td>
							<td align="center" colspan="4">${rs.xh }</td>
							<td align="center" colspan="2">民族</td>
							<td align="center" colspan="4">${rs.mzmc }</td>
							<td align="center" colspan="3">入学时间</td>
							<td align="center" colspan="5">${rs.rxrq }</td>
						</tr>
						<tr height="30px">
							<td align="center">政治面貌</td>
							<td align="center" colspan="4">${rs.zzmmmc }</td>
							<td align="center" colspan="4">联系电话</td>
							<td align="center" colspan="10">${rs.sjhm }</td>
						</tr>
						<tr>
							<td>
								<div align="center">
									身份证号
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh1" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh2" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh3" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh4" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh5" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh6" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh7" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh8" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh9" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh10" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh11" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh12" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh13" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh14" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh15" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh16" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh17" />
								</div>
							</td>
							<td width="4%">
								<div align="center">
									<bean:write name='rs' property="sfzh18" />
								</div>
							</td>
						</tr>
						<tr height="30px">
							<td align="center" rowspan="4">
								家<br/>庭<br/>经<br/>济<br/>情<br/>况
							</td>
							<td align="center">家庭户口</td>
							<td align="center" colspan="10">
								<logic:notEmpty name="rs" property="jthk">
									<logic:equal name="rs" property="jthk" value="城镇">
										<u>A、城镇</U> B、农村
									</logic:equal>
									<logic:equal name="rs" property="jthk" value="农村">
										A、城镇 <u>B、农村</U> 
									</logic:equal>
								</logic:notEmpty>
								<logic:empty name="rs" property="jthk">
										A、城镇  B、农村
								</logic:empty>
							</td>
							<td align="center"  colspan="3">收入来源</td>
							<td align="center"  colspan="5">${rs.srly }</td>
						</tr>
						<tr height="30px">
							<td align="center">家庭月<br/>总收入</td>
							<td align="center" colspan="10">${rs.jtnzsr}</td>
							<td align="center" colspan="3">家庭人<br/>口总数</td>
							<td align="center" colspan="5">${rs.jtrks }</td>
						</tr>
						<tr height="30px">
							<td align="center">家庭住址</td>
							<td align="center" colspan="10">${rs.jtdz }</td>
							<td align="center" colspan="3">邮政编码</td>
							<td align="center" colspan="5">${rs.jtyb }</td>
						</tr>
						<tr height="30px">
							<td align="center">认定情况</td>
							<td align="center" colspan="18">
								<logic:notEmpty name="rs" property="knstjdc">
									<logic:equal name="rs" property="knstjdc" value="特别困难">
										 <u>A、家庭经济特别困难</U>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B、家庭经济一般困难 
									</logic:equal>
									<logic:equal name="rs" property="knstjdc" value="一般困难">
										A、家庭经济特别困难&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<u>B、家庭经济一般困难 </U> 
									</logic:equal>
								</logic:notEmpty>
								<logic:empty name="rs" property="knstjdc">
										A、家庭经济特别困难&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;B、家庭经济一般困难
								</logic:empty>
							
							</td>
						</tr>
						
					<tr style="height:30px">
							<td align="center" rowspan="5">
									家<br>
									庭<br>
									成<br>
									员<br>
									情<br>
									况
							</td>
							<td align="center">
									姓名
							</td>
							<td align="center" colspan="2">
									年龄
							</td>
							<td align="center" colspan="4">
									与本人关系
							</td>
							<td align="center" colspan="12">
									工作（学习）单位
							</td>
						</tr>
				
				<%
				
				ArrayList<HashMap<String,String>>cyList=(ArrayList<HashMap<String,String>>)request.getAttribute("cyList");
				int len=0;
				if(cyList!=null && cyList.size()>0){
					len=cyList.size();
				}
				
				for(int i=0;i<len;i++){
					HashMap<String,String>cyMap=cyList.get(i);
				%>
				<tr style="height:40px">
					 <td align=center>
						<div align="center">
							<%=cyMap.get("cyxm")==null ? "" : cyMap.get("cyxm")%>
						</div>
					</td>
					<td colspan="2" align=center>
						<div align="center">
							<%=cyMap.get("cynl")==null ? "" : cyMap.get("cynl")%>
						</div>
					</td>
					<td  colspan="4" align=center>
						<div align="center">
							<%=cyMap.get("cygx")==null ? "" : cyMap.get("cygx")%>
						</div>
					</td>
					<td colspan="12" align=center>
						<div align="center">
							<%=cyMap.get("cygzxxdw")==null ? "" : cyMap.get("cygzxxdw")%>
						</div>
					</td>
				</tr>
				<%
				}
				%> 
				 
				 
				<%
				
				for(int i=0;i<4-len;i++){
				%>
				<tr style="height:40px">
					<td  align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td colspan="2"  align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td colspan="4"   align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
					<td  colspan="12" align=center>
						<div align="center">
							&nbsp;&nbsp;
						</div>
					</td>
				
				</tr>
				<%
				}
				%>
				
						
						<tr style="height:150px">
							<td align="center">
									申请<br/>理由<br/>（100<br/>字）<br/>
							</td>
							<td colspan="19">
								<p style="height:80px">
									${rs.sqly }
								</p>
								<div align="right" style="padding-right: 30px">
									申请人签名：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日
								</div>
							</td>
						</tr>
						<tr style="height:120px">
							<td align="center"  >
									院<br/>（系）<br/>意<br/>见
							</td>
							<td colspan="19">
								<p style="height:70px">
								<bean:message key="lable.xb" />审核意见（请注明公示情况）：${rs.xyshyj }
								</p>
								<div align="right" style="padding-right: 30px">
									签名：
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日
								</div>
							</td>
						</tr>
						<tr style="height:120px">
							<td align="center">
									学<br/>校<br/>意<br/>见
							</td>
							<td colspan="19">
								<p style="height:70px">
								学生工作部意见：${rs.xxshyj }
								</p>
								<div align="right" style="padding-right: 30px">
									（公章）
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									日
								</div>
							</td>
						</tr>
					</table>
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
</html:html>
