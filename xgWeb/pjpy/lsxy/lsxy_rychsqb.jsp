<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<style media="print">
		.noprint{
			display:none
		}
		.print{
			display:block
		}
	</style>
<object id="WebBrowser" width=0 height=0 classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>		
<body>	
		<center>
			<html:form action="/pjpyLsxy" method="post">
			<p align="center"> 
			<h2 align="center">${xxmc}${rs.xn}学生先进个人申报表</h2><br/>
				<h4 align="center">（三好学生、优秀学生干部、单项奖）</h4>
			</p>
			<p align="center"></p>
			<p align="left">
			二级<bean:message key="lable.xb" />：${rs.xymc} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			专业：${rs.zymc } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			班级： ${rs.bjmc}</p>
			
			<div align="left">
			  <table cellspacing="0" cellpadding="0" class="tbstyle" width="100%">
			    <tr>
			      <td width="127"><p align="center">姓名 </p></td>
			      <td colspan="2"><p align="center">${rs.xm } </p></td>
			      <td width="192"><p align="center">性别 </p></td>
			      <td width="48"><p align="center">${rs.xb } </p></td>
			      <td width="72"><p align="center">出生 </p> <p align="center">年月 </p></td>
			      <td width="120"><p align="center">${rs.csrq } </p></td>
			      <td width="60"><p align="center">籍 贯 </p></td>
			      <td width="192" colspan="3"><p align="center">${rs.jg } </p></td>
			      <td width="96"><p align="center">党团员 </p></td>
			      <td width="36" colspan="3"><p align="center">${rs.zzmmmc }</p></td>
			    </tr>
			    <tr>
			      <td width="127"><p align="center">班级人数 </p></td>
			      <td colspan="2"><p align="center">${rs.bjrs } </p></td>
			      <td width="192"><p align="center"> 有无旷课记录 </p></td>
			      <td width="48"><p align="center">&nbsp;</p></td>
			      <td width="72"><p align="center"> 有无违纪记录 </p></td>
			      <td width="120"><p align="center">${rs.sfwj } </p></td>
			      <td width="60"><p align="center"> 是否获奖学金 </p> </td>
			      <td width="192" colspan="3"><p align="center">${rychRs.sfhdjxj} </p></td>
			      <td width="96"><p align="center">大学生体质健康成绩 </p></td>
			      <td width="36" colspan="3"><p align="center">${rs.tzjkcj }</p></td>
			    </tr>
			    <tr>
			      <td width="127" rowspan="5"><p align="center">一 </p>
			          <p align="center">学 </p>
			          <p align="center">年 </p>
			          <p align="center">各 </p>
			          <p align="center">科 </p>
			          <p align="center">成 </p>
			          <p align="center">绩 </p>
			          <p align="center">&nbsp; </p></td>
			      <td height="30" colspan="2"><p align="center">&nbsp; </p></td>
			      <td colspan="12" rowspan="5">
					<table class="tbstyle" width="100%" height="100%">
					<tr height="30">
						<td colspan="7">
							考试科目（${kscj1Len} 门）
						</td>
						<td colspan="7">
							考查科目（${kccj1Len} 门）和实习 
						</td>						
					</tr>
					<tr height="120">
						<logic:iterate id="s" name="kscjRs1" length="7">
						<td width="60px">
							${s.kcmc}
						</td>
						</logic:iterate>
						<logic:greaterEqual value="0" name="kscjRs1Leng">
						<% 
							int len= Integer.parseInt(request.getAttribute("kscjRs1Leng").toString());
							for(int i=0; i<len; i++){
						%>
						<td width="60px">							
						</td>
						<%} %>
						</logic:greaterEqual>
		
						<logic:iterate id="s" name="kccjRs1" length="7">
						<td width="60px">
							${s.kcmc}
						</td>
						</logic:iterate>
						<logic:greaterEqual value="0" name="kccjRs1Leng">
						<% 
							int len= Integer.parseInt(request.getAttribute("kccjRs1Leng").toString());
							for(int i=0; i<len; i++){
						%>
						<td width="60px">
							
						</td>
						<%} %>	
						</logic:greaterEqual>		
					</tr>
					<tr height="40">
						<logic:iterate id="s" name="kscjRs1" length="7">
						<td width="60px">
							${s.cj}
						</td>
						</logic:iterate>

						<logic:greaterEqual value="0" name="kscjRs1Leng">
						<% 
							int len= Integer.parseInt(request.getAttribute("kscjRs1Leng").toString());
							for(int i=0; i<len; i++){
						%>
						<td width="60px">
							
						</td>
						<%} %>		
						</logic:greaterEqual>

						<logic:iterate id="s" name="kccjRs1" length="7">
						<td width="60px">
							${s.cj }
						</td>
						</logic:iterate>
						<logic:greaterEqual value="0" name="kccjRs1Leng">
						<% 
							int len= Integer.parseInt(request.getAttribute("kccjRs1Leng").toString());
							for(int i=0; i<len; i++){
						%>
						<td width="60px">
							
						</td>
						<%} %>			
						</logic:greaterEqual>		
					</tr>
					<tr height="120">
						<logic:iterate id="s" name="kscjRs2" length="7">
						<td width="60px">
							${s.kcmc}
						</td>
						</logic:iterate>

						<logic:greaterEqual value="0" name="kscjRs2Leng">
						<% 
							int len= Integer.parseInt(request.getAttribute("kscjRs2Leng").toString());
							for(int i=0; i<len; i++){
						%>
						<td width="60px">
							
						</td>
						<%} %>		
						</logic:greaterEqual>
						<logic:iterate id="s" name="kccjRs2" length="7">
						<td width="60px">
							${s.kcmc}
						</td>
						</logic:iterate>
						<logic:greaterEqual value="0" name="kccjRs2Leng">
						<% 
							int len= Integer.parseInt(request.getAttribute("kccjRs2Leng").toString());
							for(int i=0; i<len; i++){
						%>
						<td width="60px">
							
						</td>
						<%} %>			
						</logic:greaterEqual>
					</tr>
					<tr height="40">
						<logic:iterate id="s" name="kscjRs2" length="7">
						<td width="60px">
							${s.cj}
						</td>
						</logic:iterate>
						<logic:greaterEqual value="0" name="kscjRs2Leng">
						<% 
							int len= Integer.parseInt(request.getAttribute("kscjRs2Leng").toString());
							for(int i=0; i<len; i++){
						%>
						<td width="60px">
							
						</td>
						<%} %>	
						</logic:greaterEqual>	
						<logic:iterate id="s" name="kccjRs2" length="7">
						<td width="60px">
							${s.cj}
						</td>
						</logic:iterate>
						<logic:greaterEqual value="0" name="kccjRs2Leng">
						<% 
							int len= Integer.parseInt(request.getAttribute("kccjRs2Leng").toString());
							for(int i=0; i<len; i++){
						%>
						<td width="60px">
							
						</td>
						<%} %>
						</logic:greaterEqual>						
					</tr>
					</table>
				</td>			     
			    </tr>
			    <tr>
			      <td rowspan="2"><p align="center">第一学期 </p></td>
			      <td width="36" height="120"><p align="center">科 </p>
			          <p align="center">&nbsp; </p>
			          <p align="center">目 </p></td>
			      
			    </tr>
			    <tr>
			      <td width="36" height="40"><p align="center">成绩 </p></td>
			     
			    </tr>
			    <tr>
			      <td rowspan="2"><p align="center">第二学期 </p></td>
			      <td width="36" height="120"><p align="center">科 </p>
			          <p align="center">&nbsp; </p>
			      <p align="center">目 </p></td>
			      
			    </tr>
			    <tr>
			      <td width="36" height="40"><p align="center">成绩 </p></td>			      
			    </tr>
			    <tr>
			      <td height="70">
					<p align="center">申报材料 </p> （相关证书） 
				  </td>
			      <td colspan="14">
					<p>（可另附）</p>
		          	<p>${rychRs.zysj}</p>
				  </td>
		        </tr>
			    <tr>
			      <td width="127" height="70">		
					<p align="center">班级意见 </p>
				  </td>
			      <td colspan="14">
						<p>
						同意推荐为： ${rychRs.rychmc}
						</p>
			          <p align="right" style="width:100%"> 
						班主任签名： 
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  </p>
				 </td>
			    </tr>
			    <tr>
			      <td width="127" height="70"><p align="center"><bean:message key="lable.xb" />意见 </p></td>
			      <td colspan="14" valign="top" >
						<p>${rychRs.xyyj}</p>
						<p>&nbsp;</p>
						<p style="width:100%" align="right">						   
 							领导签名： 
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
				        <p align="right">						
	 						二级<bean:message key="lable.xb" />盖章： 
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
				</td>
			    </tr>
			    <tr>
			      <td width="127" height="70"><p align="center">学校意见 </p></td>
			      <td width="577" colspan="14"><p align="center">${rychRs.xxyj} </p></td>
			    </tr>
			  </table>
			  注：此表一式二份，学生档案一份，学生科一份。</div>	
			<div class="noprint" align="center">
				<input type='button' class='button2' value='页面设置'
					onclick="WebBrowser.ExecWB(8,1);return false;">
				<input type='button' class='button2' value='打印预览'
					onclick="WebBrowser.ExecWB(7,1);return false;">
				<input type='button' class='button2' value='直接打印'
					onclick="WebBrowser.ExecWB(6,6);return false;">
			</div>
			</html:form>
        </center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
</body>

