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
			<p align="left">附件 1 ：</p>
			<p align="center"> 
			<h3 align="center">${xxmc}${rs.xn}学年奖学金申请表</h3></p>
			<p align="center"><strong>&nbsp; </strong></p>
			<p align="left">
			二级<bean:message key="lable.xb" />：${rs.xymc} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			专业：${rs.zymc } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			班级： ${rs.bjmc}</p>
			
			<div align="left">
			  <table cellspacing="0" cellpadding="0" class="tbstyle" width="100%">
			    <tr>
			      <td width="55"><p align="center">姓名 </p></td>
			      <td width="72" colspan="2"><p align="center">${rs.xm } </p></td>
			      <td width="72" colspan="2"><p align="center">性别 </p></td>
			      <td width="48" colspan="2"><p align="center">${rs.xb } </p></td>
			      <td width="72" colspan="3"><p align="center">出生 </p>
			          <p align="center">年月 </p></td>
			      <td width="60" colspan="3"><p align="center">${rs.csrq } </p></td>
			      <td width="60" colspan="2"><p align="center">籍 贯 </p></td>
			      <td width="60" colspan="3"><p align="center">${rs.jg } </p></td>
			      <td width="96" colspan="3"><p align="center">党团员 </p></td>
			      <td width="36"><p align="center">${rs.zzmmmc }</p></td>
			    </tr>
			    <tr>
			      <td width="55"><p align="center">班级人数 </p></td>
			      <td width="72" colspan="2"><p align="center">${rs.bjrs } </p></td>
			      <td width="72" colspan="2"><p align="center">综合测评名次 </p></td>
			      <td width="48" colspan="2"><p align="center">${rs.zfpm } </p></td>
			      <td width="72" colspan="3"><p align="center">学习成绩名次 </p></td>
			      <td width="60" colspan="3"><p align="center">${rs.cjpm } </p></td>
			      <td width="60" colspan="2"><p align="center">获奖 </p>
			          <p align="center">情况 </p></td>
			      <td width="60" colspan="3"><p align="center">${jxjRs.jfqk} </p></td>
			      <td width="96" colspan="3"><p align="center">大学生体质健康成绩 </p></td>
			      <td width="36"><p align="center">${rs.tzjkcj }</p></td>
			    </tr>
			    <tr>
			      <td width="127" colspan="3"><p align="center">是否有违纪 </p>
			          <p align="center">受处分 </p></td>
			      <td width="192" colspan="7"><p align="center">${rs.sfwj} </p></td>
			      <td width="120" colspan="5"><p align="center">是否有旷课 </p></td>
			      <td width="192" colspan="7"><p align="center">&nbsp; </p></td>
			    </tr>
			    <tr>
			      <td width="55" rowspan="5"><p align="center">一 </p>
			          <p align="center">学 </p>
			          <p align="center">年 </p>
			          <p align="center">各 </p>
			          <p align="center">科 </p>
			          <p align="center">成 </p>
			          <p align="center">绩 </p>
			          <p align="center">&nbsp; </p></td>
			      <td width="72" height="30" colspan="2"><p align="center">&nbsp; </p></td>
			      <td colspan="20" rowspan="5">
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
			      <td width="36" rowspan="2"><p align="center">第一学期 </p></td>
			      <td width="36" height="120"><p align="center">科 </p>
			          <p align="center">&nbsp; </p>
			          <p align="center">目 </p></td>
			      
			    </tr>
			    <tr>
			      <td width="36" height="40"><p align="center">成绩 </p></td>
			     
			    </tr>
			    <tr>
			      <td width="36" rowspan="2"><p align="center">第二学期 </p></td>
			      <td width="36" height="120"><p align="center">科 </p>
			          <p align="center">&nbsp; </p>
			      <p align="center">目 </p></td>
			      
			    </tr>
			    <tr>
			      <td width="36" height="40"><p align="center">成绩 </p></td>
			      
			    </tr>
			    <tr>
			      <td width="55" height="70">		
					<p align="center">班级意见 </p>
				  </td>
			      <td colspan="21">
						<p>
							<logic:notEmpty name="jxjRs" property="jxjmc">
								推荐为 ${jxjRs.jxjmc}
							</logic:notEmpty>
							<logic:empty name="jxjRs" property="jxjmc">
								推荐为  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;等奖学金 
							</logic:empty>
							
						</p>
			          <p align="right" style="width:100%"> 
						班主任签名： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  </p>
				 </td>
			    </tr>
			    <tr>
			      <td width="55" height="70"><p align="center"><bean:message key="lable.xb" />意见 </p></td>
			      <td colspan="21" valign="top" >
						<p>${rs.jxjRs.xyyj}</p>
						<p>&nbsp;</p>
						<p style="width:100%" align="right">						   
							领导签名： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</p>
			          <p align="right">
						
							<bean:message key="lable.xb" />盖章： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					  </p>
				</td>
			    </tr>
			    <tr>
			      <td width="55" height="70"><p align="center">学校意见 </p></td>
			      <td width="577" colspan="21"><p align="center">${jxjRs.xxyj} </p></td>
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

