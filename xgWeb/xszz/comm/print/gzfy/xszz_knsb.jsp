<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<title>家庭经济困难学生认定申请表</title>
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<object id=eprint classid="clsid:CA03A5A8-9890-49BE-BA4A-8C524EB06441"
			codebase="images/webprint.cab" viewasext>
		</object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
	</head>
	<body>
		<center>
			<span style="font-size:22px;font-family:黑体">广州番禺职业技术学院家庭经济困难学生认定申请表</span>
			<br/><br/>
			<span style="font-size:12px;">
				系（院）：<u>&nbsp;&nbsp;&nbsp;&nbsp;${rs.xymc }&nbsp;&nbsp;&nbsp;&nbsp;</u>
				年级、班级：<u>&nbsp;&nbsp;&nbsp;&nbsp;${rs.nj}-${rs.bjmc }&nbsp;&nbsp;&nbsp;&nbsp;</u>
				学号：<u>&nbsp;&nbsp;&nbsp;&nbsp;${rs.xh }&nbsp;&nbsp;&nbsp;&nbsp;</u></span>
		</center>
		<table class="printtab">
			<tr>
				<td width="48" rowspan="2">
					<p align="center">
						<strong>学生 </strong>
					</p>
					<p align="center">
						<strong>本人 </strong>
					</p>
					<p align="center">
						<strong>基本 </strong>
					</p>
					<p align="center">
						<strong>情况 </strong>
					</p>
				</td>
				<td width="60" colspan="2">
					<p align="center">
						<strong>姓名 </strong>
					</p>
				</td>
				<td width="108" colspan="4">
					<p align="center">
						${rs.xm }
					</p>
				</td>
				<td width="60" colspan="3">
					<p align="center">
						<strong>性别 </strong>
					</p>
				</td>
				<td width="60" colspan="2">
					<p align="center">
						${rs.xb }
					</p>
				</td>
				<td width="96" colspan="4">
					<p align="center">
						<strong>民族 </strong>
					</p>
				</td>
				<td width="96" colspan="5">
					<p align="center">
						${rs.mzmc }
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						<strong>生源地 </strong>
					</p>
				</td>
				<td width="108" colspan="3">
					<p align="center">
						${rs.syd }
					</p>
				</td>
			</tr>
			<tr>
				<td width="120" colspan="4">
					<p align="center">
						<strong>孤儿或单亲 </strong>
					</p>
				</td>
				<td width="108" colspan="5">
					<p align="center">
						<logic:present name="rs">
							<logic:equal name="rs" property="sfdq" value="是">
								<logic:notEqual name="rs" property="sfge" value="是">
									■ 是  □ 否
								</logic:notEqual>
							</logic:equal>
							<logic:equal name="rs" property="sfge" value="是">
								<logic:notEqual name="rs" property="sfdq" value="是">
									■ 是  □ 否
								</logic:notEqual>
							</logic:equal>
							<logic:equal name="rs" property="sfdq" value="是">
								<logic:equal name="rs" property="sfge" value="是">
									■ 是  □ 否
								</logic:equal>
							</logic:equal>
								<logic:notEqual name="rs" property="sfdq" value="是">
									<logic:notEqual name="rs" property="sfge" value="是">
										□ 是   ■ 否
									</logic:notEqual>
								</logic:notEqual>
						</logic:present>
						<logic:notPresent name="rs">
							□ 是   □ 否
						</logic:notPresent>
					</p>
				</td>
				<td width="60" colspan="2">
					<p align="center">
						<strong>低保 </strong>
					</p>
				</td>
				<td width="96" colspan="4">
					<p align="center">
						<logic:present name="rs">
							<logic:equal value="是" name="rs" property="sfdb">
								■ 是   □ 否
							</logic:equal>
							<logic:equal value="否" name="rs" property="sfdb">
								□ 是   ■ 否
							</logic:equal>
						</logic:present>
						<logic:notPresent name="rs">
							□ 是  □ 否
						</logic:notPresent>
					</p>
				</td>
				<td width="96" colspan="5">
					<p align="center">
						<strong>联系电话 </strong>
					</p>
				</td>
				<td width="180" colspan="5">
					<p align="center">
						${rs.lxdh }
					</p>
				</td>
			</tr>
			<tr>
				<td width="48" rowspan="5">
					<p align="center">
						<strong>家庭成员情况 </strong>
					</p>
				</td>
				<td width="84" colspan="3">
					<p align="center">
						<strong>姓名 </strong>
					</p>
				</td>
				<td width="48" colspan="2">
					<p align="center">
						<strong>年龄 </strong>
					</p>
				</td>
				<td width="80" colspan="3">
					<p align="center">
						<strong>与学生关系</strong>
					</p>
				</td>
				<td width="84" colspan="2">
					<p align="center">
						<strong>职业 </strong>
					</p>
				</td>
				<td width="95" colspan="4">
					<p align="center">
						<strong>年收入（元）</strong>
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						<strong>姓名 </strong>
					</p>
				</td>
				<td width="48" colspan="3">
					<p align="center">
						<strong>年龄 </strong>
					</p>
				</td>
				<td width="80" colspan="2">
					<p align="center">
						<strong>与学生关系 </strong>
					</p>
				</td>
				<td width="84" colspan="3">
					<p align="center">
						<strong>职业 </strong>
					</p>
				</td>
				<td width="80">
					<p align="center">
						<strong>年收入（元）</strong>
					</p>
				</td>
			</tr>
			
			<%
				List<HashMap<String,String>> cyList = (List<HashMap<String,String>>)request.getAttribute("cyList");
				
				int i = 0;
				while (i < cyList.size() && i<6){
			 %>
				<tr>
					<td width="84" colspan="3">
						<p align="center">
							<%= i != cyList.size() && null != cyList.get(i).get("cyxm") ? cyList.get(i).get("cyxm") : "" %>
						</p>
					</td>
					<td width="48" colspan="2">
						<p align="center">
							<%= i != cyList.size() && null != cyList.get(i).get("cynl") ? cyList.get(i).get("cynl") : "" %>
						</p>
					</td>
					<td width="60" colspan="3">
						<p align="center">
							<%= i != cyList.size() && null != cyList.get(i).get("mc") ? cyList.get(i).get("mc") : "" %>
						</p>
					</td>
					<td width="84" colspan="2">
						<p align="center">
							<%= i != cyList.size() && null != cyList.get(i).get("cyzy") ? cyList.get(i).get("cyzy") : "" %>
						</p>
					</td>
					<td width="60" colspan="4">
						<p align="center">
							<%= i != cyList.size() && null != cyList.get(i).get("cynsr") ? cyList.get(i).get("cynsr") : "" %>
						</p>
					</td>
					<%i++; %>
					<td width="72" colspan="2">
						<p align="center">
							<%= i != cyList.size() && null != cyList.get(i).get("cyxm") ? cyList.get(i).get("cyxm") : "" %>
						</p>
					</td>
					<td width="48" colspan="3">
						<p align="center">
							<%= i != cyList.size() && null != cyList.get(i).get("cynl") ? cyList.get(i).get("cynl") : "" %>
						</p>
					</td>
					<td width="60" colspan="2">
						<p align="center">
							<%= i != cyList.size() && null != cyList.get(i).get("mc") ? cyList.get(i).get("mc") : "" %>
						</p>
					</td>
					<td width="84" colspan="3">
						<p align="center">
							<%= i != cyList.size() && null != cyList.get(i).get("cyzy") ? cyList.get(i).get("cyzy") : "" %>
						</p>
					</td>
					<td width="60">
						<p align="center">
							<%= i != cyList.size() && null != cyList.get(i).get("cynsr") ? cyList.get(i).get("cynsr") : "" %>
						</p>
					</td>
				</tr>
			<%
				i++;
				}
				
				int n = 0;
								
				if (cyList.size()%2==1){
					n = 3 - (cyList.size()+1)/2;
				} else {
					n = 3 - cyList.size()/2;
				}
				
				for (int j = 0 ; j < n; j++){
			 %>			
			<tr>
				<td width="84" colspan="3">
					<p align="center">
						&nbsp;
					</p>
				</td>
				<td width="48" colspan="2">
					<p align="center">
						&nbsp;
					</p>
				</td>
				<td width="60" colspan="3">
					<p align="center">
						&nbsp;
					</p>
				</td>
				<td width="84" colspan="2">
					<p align="center">
						&nbsp;
					</p>
				</td>
				<td width="60" colspan="4">
					<p align="center">
						&nbsp;
					</p>
				</td>
				<td width="72" colspan="2">
					<p align="center">
						&nbsp;
					</p>
				</td>
				<td width="48" colspan="3">
					<p align="center">
						&nbsp;
					</p>
				</td>
				<td width="60" colspan="2">
					<p align="center">
						&nbsp;
					</p>
				</td>
				<td width="84" colspan="3">
					<p align="center">
						&nbsp;
					</p>
				</td>
				<td width="60">
					<p align="center">
						&nbsp;
					</p>
				</td>
			</tr>
			<%
				}
			 %>
			<tr>
				<td width="661" colspan="25">
					<p>
						还有其他情况请在申请理由里注明
					</p>
				</td>
			</tr>
			<tr>
				<td width="48">
					<p align="center">
						<strong>学生陈述申请认定理由 </strong>
					</p>
				</td>
				<td width="661" colspan="25" valign="bottom">
					<p valign="top">
						&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
					</p>
					<br/>
					<p>
						注：以上由学生本人填写，以下由 班委或班主任填写。
					</p>
				</td>
			</tr>
			<tr>
				<td width="48" rowspan="11">
					<p align="center">
						<strong>上一学年学习情况 </strong>
					</p>
				</td>
				<td width="84" colspan="3">
					<p align="center">
						<strong>时间 </strong>
					</p>
				</td>
				<td width="144" colspan="6">
					<p align="center">
						<strong>获奖、助学金 </strong>
					</p>
					<p align="center">
						<strong>及其他荣誉名称 </strong>
					</p>
				</td>
				<td width="67" colspan="3">
					<p align="center">
						<strong>时间 </strong>
					</p>
				</td>
				<td width="149" colspan="6">
					<p align="center">
						<strong>获奖、助学金 </strong>
					</p>
					<p align="center">
						<strong>及其他荣誉名称 </strong>
					</p>
				</td>
				<td width="72" colspan="3">
					<p align="center">
						<strong>时间 </strong>
					</p>
				</td>
				<td width="144" colspan="4">
					<p align="center">
						<strong>获奖、助学金 </strong>
					</p>
					<p align="center">
						<strong>及其他荣誉名称 </strong>
					</p>
				</td>
			</tr>
			<tr>
				<td width="84" colspan="3">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="144" colspan="6">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="67" colspan="3">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="149" colspan="6">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="72" colspan="3">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="144" colspan="4">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
			</tr>
			<tr>
				<td width="84" colspan="3">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="144" colspan="6">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="67" colspan="3">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="149" colspan="6">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="72" colspan="3">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="144" colspan="4">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
			</tr>
			<tr>
				<td width="84" colspan="3">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="144" colspan="6">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="67" colspan="3">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="149" colspan="6">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="72" colspan="3">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="144" colspan="4">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
			</tr>
			<tr>
				<td width="84" colspan="3">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="144" colspan="6">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="67" colspan="3">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="149" colspan="6">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="72" colspan="3">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="144" colspan="4">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
			</tr>
			<tr>
				<td width="120" colspan="4">
					<p align="center">
						<strong>旷课情况 </strong><strong></strong>
					</p>
				</td>
				<td width="108" colspan="5">
					<p align="center">
						<strong>迟到情况 </strong>
					</p>
				</td>
				<td width="108" colspan="5">
					<p align="center">
						<strong>早退情况 </strong>
					</p>
				</td>
				<td width="324" colspan="11">
					<p align="center">
						<strong>其他违纪处分情况 </strong>
					</p>
				</td>
			</tr>
			<tr>
				<td width="120" colspan="4">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="108" colspan="5">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="108" colspan="5">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="324" colspan="11">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
			</tr>
			<tr>
				<td width="48" rowspan="4">
					<p align="center">
						<strong>不及 </strong>
					</p>
					<p align="center">
						<strong>格科 </strong>
					</p>
					<p align="center">
						<strong>目的 </strong>
					</p>
					<p align="center">
						<strong>情况 </strong>
					</p>
				</td>
				<td width="126" colspan="6">
					<p align="center">
						<strong>课程名称 </strong>
					</p>
				</td>
				<td width="54" colspan="2">
					<p align="center">
						<strong>分数 </strong>
					</p>
				</td>
				<td width="108" colspan="5">
					<p align="center">
						<strong>课程名称 </strong>
					</p>
				</td>
				<td width="81" colspan="3">
					<p align="center">
						<strong>分数 </strong>
					</p>
				</td>
				<td width="162" colspan="6">
					<p align="center">
						<strong>课程名称 </strong>
					</p>
				</td>
				<td width="81" colspan="2">
					<p align="center">
						<strong>分数 </strong>
					</p>
				</td>
			</tr>
			
			
			<%
				List<HashMap<String,String>> bjgcjList = (List<HashMap<String,String>>)request.getAttribute("bjgcjList");
				
				int j = 0;
				while (j < bjgcjList.size()){
			 %>
			
			
			<tr>
				<td width="126" colspan="6">
					<p align="center">
						<%=j < bjgcjList.size()  && null != bjgcjList.get(j).get("kcmc") ? bjgcjList.get(j).get("kcmc") : "" %>
					</p>
				</td>
				<td width="54" colspan="2">
					<p align="center">
						<%=j < bjgcjList.size() && null != bjgcjList.get(j).get("cj") ? bjgcjList.get(j).get("cj") : "" %>
					</p>
				</td>
				<%j++; %>
				<td width="108" colspan="5">
					<p align="center">
						<%=j < bjgcjList.size() && null != bjgcjList.get(j).get("kcmc") ? bjgcjList.get(j).get("kcmc") : "" %>
					</p>
				</td>
				<td width="81" colspan="3">
					<p align="center">
						<%=j < bjgcjList.size() && null != bjgcjList.get(j).get("cj") ? bjgcjList.get(j).get("cj") : "" %>
					</p>
				</td>
				<%j++; %>
				<td width="162" colspan="6">
					<p align="center">
						<%=j < bjgcjList.size() && null != bjgcjList.get(j).get("kcmc") ? bjgcjList.get(j).get("kcmc") : "" %>
					</p>
				</td>
				<td width="81" colspan="2">
					<p align="center">
						<%=j < bjgcjList.size() && null != bjgcjList.get(j).get("cj") ? bjgcjList.get(j).get("cj") : "" %>
					</p>
				</td>
			</tr>
			
			<%
					j++;
				}
				
				for (int c = 0 ; c < 3-j/3 ; c++){
			 %>
			<tr>
				<td width="126" colspan="6">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="54" colspan="2">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="108" colspan="5">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="81" colspan="3">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="162" colspan="6">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
				<td width="81" colspan="2">
					<p align="center">
						<strong>&nbsp; </strong>
					</p>
				</td>
			</tr>
			<%
				}
			 %>
			<tr>
				<td width="48" rowspan="3">
					<p align="center">
						<strong>民主评议 </strong>
					</p>
				</td>
				<td width="48" rowspan="3">
					<p align="center">
						<strong>推荐档次 </strong><strong></strong>
					</p>
				</td>
				<td width="240" colspan="10">
					<p>
						
						<strong>
							<logic:equal value="家庭经济一般困难" property="knjb" name="rs">
								■
							</logic:equal>
							<logic:notEqual value="家庭经济一般困难" property="knjb" name="rs">
								□ 
							</logic:notEqual>
							A 、家庭经济一般困难 
						</strong>
					</p>
				</td>
				<td width="36" colspan="2" rowspan="3">
					<p align="center">
						<strong>陈述理由 </strong><strong></strong>
					</p>
				</td>
				<td width="336" colspan="12" rowspan="3" valign="bottom">
					<p>
						<strong>班委或班主任签字： </strong>
					</p>
					<p align="right">
						<strong>
							年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							月&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
							日 
						</strong>
					</p>
				</td>
			</tr>
			<tr>
				<td width="240" colspan="10">
					<p>
						<strong>
							<logic:equal value="家庭经济特殊困难" property="knjb" name="rs">
								■
							</logic:equal>
							<logic:notEqual value="家庭经济特殊困难" property="knjb" name="rs">
								□ 
							</logic:notEqual>
							 B 、家庭经济特殊困难 
						</strong>
					</p>
				</td>
			</tr>
			<tr>
				<td width="240" colspan="10">
					<p>
						<strong>
							<logic:equal value="家庭经济不困难" property="knjb" name="rs">
								■
							</logic:equal>
							<logic:notEqual value="家庭经济不困难" property="knjb" name="rs">
								□ 
							</logic:notEqual>
								C 、家庭经济不困难 
						</strong>
					</p>
				</td>
			</tr>
			<tr height="100">
				<td width="48">
					<p align="center">
						<strong>备注 </strong>
					</p>
				</td>
				<td width="661" colspan="25"  valign="top">
						&nbsp;&nbsp;&nbsp;${rs.bz }
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
