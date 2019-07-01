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
						海南大学学生临时困难补助申请表
					</h2>
				</td>
			</tr>
			<tr>
				<td align="center">
					<table width="100%" class="tbstyle">
					  <tr>
					  	<td width="7%"></td>
					  	<td width="9%"></td>
					  	<td width="11%"></td>
					  	<td width="7%"></td>
					  	<td width="7%"></td>
					  	<td width="6%"></td>
					  	<td width="7%"></td>
					  	<td width="6%"></td>
					  	<td width="7%"></td>
					  	<td width="6%"></td>
					  	<td width="7%"></td>
					  	<td width="6%"></td>
					  	<td width="7%"></td>
					  	<td width="8%"></td>
					  </tr>
					  <tr height="40px">
					    <td><p align="center">姓 名 </p></td>
					    <td><p align="center">${rs.xm } </p></td>
					    <td><p align="center">专业、班级 </p></td>
					    <td colspan="3"><p align="center">${rs.zymc }&nbsp;&nbsp;${rs.bjmc } </p></td>
					    <td colspan="2"><p align="center">宿舍号 </p></td>
					    <td colspan="3"><p align="center">${rs.ssbh } </p></td>
					    <td><p align="center">电话 </p></td>
					    <td colspan="2"><p align="center">${rs.lxdh } </p></td>
					  </tr>
					  <tr height="40px">
					    <td width="60"rowspan="${cyNum+1 }">
					    	<p align="center">家 </p>
					        <p align="center">庭 </p>
					        <p align="center">主 </p>
					        <p align="center">要 </p>
					        <p align="center">成 </p>
					        <p align="center">员 </p></td>
					    <td align="center">姓 名</td>
					    <td colspan="3" align="center">与本人关系及电话 </td>
					    <td colspan="4" align="center">工 作 单 位</td>
					    <td colspan="5" align="center">经济收入</td>
					  </tr>
					  <logic:iterate name="cyList" id="cy">
							<tr height="25px">
								<td align="center">
										${cy.cyxm }&nbsp;&nbsp;
								</td>
								<td align="center" colspan="3">
										${cy.mc }&nbsp;&nbsp;
								</td>
								<td align="center" colspan="4">
										${cy.cygzdw }&nbsp;&nbsp;
								</td>
								<td align="center" colspan="5">
										${cy.cynsr }&nbsp;&nbsp;
								</td>
							</tr>
						</logic:iterate>
					  <tr height="40px">
					    <td colspan="2"><p align="center">家庭年收入 </p></td>
					    <td colspan="3"><p align="center">${rs.jtnzsr } </p></td>
					    <td colspan="4"><p align="center">在校期间月均生活费 </p></td>
					    <td colspan="5"><p align="center">${rs.yjshf } </p></td>
					  </tr>
					  <tr height="40px">
					    <td colspan="2"><p align="center">学费标准 </p></td>
					    <td colspan="3"><p align="center">${rs.xfbz } </p></td>
					    <td colspan="4"><p align="center">目前欠费额度 </p></td>
					    <td colspan="5"><p align="center">${rs.qfje } </p></td>
					  </tr>
					  <tr height="40px">
					    <td colspan="2"><p align="center">家庭详细地址（邮编） </p></td>
					    <td colspan="12"><p align="center">${rs.jtdz } </p></td>
					  </tr>
					  <tr>
					    <td rowspan="${cjNum+3 }"><p align="center">学 </p>
					        <p align="center">习 </p>
					        <p align="center">成 </p>
					        <p align="center">绩 </p></td>
					    <td><p align="center">科 目 </p></td>
					    <td colspan="2"><p align="center">成 绩 </p></td>
					    <td colspan="3"><p align="center">科 目 </p></td>
					    <td colspan="3"><p align="center">成 绩 </p></td>
					    <td colspan="3"><p align="center">科 目 </p></td>
					    <td><p align="center">成 绩 </p></td>
					  </tr>
					<%
						List<HashMap<String, String>> cjList = (List<HashMap<String, String>>)request.getAttribute("cjList");
						
						int i=0;
						while (i<cjList.size()){
					 %>
					 	<tr>
					 		<td align="center"><%if(i<cjList.size()) out.print(cjList.get(i).get("kcmc")); %></td>
					 		<td align="center" colspan="2"><%if(i<cjList.size()) out.print(cjList.get(i).get("cj")); %></td>
					 		<%
								i++;
							 %>
					 		<td align="center" colspan="3"><%if(i<cjList.size()) out.print(cjList.get(i).get("kcmc")); %></td>
					 		<td align="center" colspan="3"><%if(i<cjList.size()) out.print(cjList.get(i).get("cj")); %></td>
					 		<%
								i++;
							%>
					 		<td align="center" colspan="3"><%if(i<cjList.size()) out.print(cjList.get(i).get("kcmc")); %></td>
					 		<td align="center"><%if(i<cjList.size()) out.print(cjList.get(i).get("cj")); %></td>
					 	</tr>
					 <%
					 	i++;
					 	}
					  %>
					  <tr>
					 		<td align="center">&nbsp;</td>
					 		<td align="center" colspan="2"></td>
					 		<td align="center" colspan="3"></td>
					 		<td align="center" colspan="3"></td>
					 		<td align="center" colspan="3"></td>
					 		<td align="center"></td>
					 	</tr>
					 	<tr>
					 		<td align="center">&nbsp;</td>
					 		<td align="center" colspan="2"></td>
					 		<td align="center" colspan="3"></td>
					 		<td align="center" colspan="3"></td>
					 		<td align="center" colspan="3"></td>
					 		<td align="center"></td>
					 	</tr>
					  <tr>
					    <td width="60"  align="center">
					    	申 <br/>
					        请 <br/>
					        理 <br/>
					        由 <br/>
					    </td>
					    <td width="640" colspan="13">
					    	<p style="height:80px">
					    		&nbsp;&nbsp;&nbsp;&nbsp;${rs.sqly }
					    	</p>
					    </td>
					  </tr>
					  <tr>
					    <td width="60"><p align="center">家庭 </p>
					        <p align="center">情况 </p></td>
					    <td width="640" colspan="13"><p align="center">&nbsp; </p>
					        <p>孤儿（
					        	<logic:equal value="是" name="rs" property="sfgc">
					        		√
					        	</logic:equal>
					        	
					        　）　单亲家庭（
					        	<logic:equal value="是" name="rs" property="sfdq">
					        		√
					        	</logic:equal>
					        　）　城乡低保户（　
					        	<logic:equal value="是" name="rs" property="sfdb">
					        		√
					        	</logic:equal>
					       	 ）　家庭其它情况（说明：
					       	 <logic:equal value="是" name="rs" property="lszn">
					        		√烈士子女
					        	</logic:equal>
					       	 　 　　　） </p>
					        <p align="center">&nbsp; </p></td>
					  </tr>
					  <tr>
					    <td colspan="14" align="center">
					    	<strong>本人保证以上所填内容真实有效。 </strong>
					    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					    	<br/>
					        申请人签名：
					    </td>
					  </tr>
					  <tr>
					    <td width="60" align="center">
					        学 <br/>
					        院 <br/>
					        意 <br/>
					        见<br/>
					    </td>
					    <td colspan="13" valign="top">
					    	<p style="height:80px">（该生在校各方面的主要表现） 
					    		<br/>&nbsp;&nbsp;&nbsp;&nbsp;${rs.xyshyj }
					    	</p>
					        <p align="center">　　　　　　　　　　　　　　　　　　　　　　盖　章：　　年　　月　　日 </p>
					  </tr>
					  <tr>
					    <td width="60" align="center">
					        学 <br/>
					        校 <br/>
					        意 <br/>
					        见 <br/>
					     </td>
					    <td colspan="13">
					    	<p style="height:80px">
					    		&nbsp;&nbsp;&nbsp;&nbsp;${rs.xxshyj }
					    	 </p>
					        <p align="center">　　　　　　　　　　　　　　　　　　　　　　盖　章：　　年　　月　　日 </p>
					   </td>
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
