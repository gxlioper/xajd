<%@ page language="java" import="java.util.*"  pageEncoding="GB18030"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
	<style media='print'>
		.noPrin{display:none;}
	</style>
  </head>
  
  <body>
  	&nbsp;<br/>
  	<p style='text-align:center'>
		<b><span style='font-size:18.0pt;'>
			南京技师学院<u>&nbsp;&nbsp;${rs.bynd }&nbsp;&nbsp;</u>年毕业生就业推荐表
			</span>
		</b>
	</p>
	<br/><br/><br/>
	<table align="center">
		<tr>
			<td width="800px;">
				<b>
					<span style='font-size:12.0pt;height: 20px;'> 
						编号：NGJ-QD-15-05&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						版号：A/0&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						流水号：${lsh }
					</span>
				</b>
				<table class="printtab" width="100%" height="800px;">
			  		<tr>
			  			<td colspan="2" width="15%" align="center">姓名</td>
			  			<td colspan="2" width="17%" align="center">${stu.xm }</td>
			  			<td width="10%" align="center">性别</td>
			  			<td width="10%" align="center">${stu.xb }</td>
			  			<td width="10%" align="center">民族</td>
			  			<td width="18%" align="center">${stu.mzmc }</td>
			  			<td rowspan="4" width="20%" align="center">
			  				<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}"
								 width="96" height="128" />
			  			</td>
			  		</tr>
			  		<tr>
			  			<td colspan="3" align="center">出生年月</td>
			  			<td colspan="2" align="center">${stu.csrq }</td>
			  			<td colspan="2" align="center">政治面貌</td>
			  			<td align="center">${stu.zzmmmc }</td>
			  		</tr>
			  		<tr>
			  			<td colspan="3" align="center">家庭地址</td>
			  			<td colspan="5" align="center">${jtcy.jtszd}</td>
			  		</tr>
			  		<tr>
			  			<td colspan="3" align="center">邮政编码</td>
			  			<td colspan="2" align="center">${jtcy.jtyb }</td>
			  			<td colspan="2" align="center">健康状况</td>
			  			<td align="center">${stu.jkzk }</td>
			  		</tr>
			  		<tr height="50px">
			  			<td colspan="3" align="center">特  长</td>
			  			<td colspan="4" align="center">${stu.tc }</td>
			  			<td align="center">联系电话</td>
			  			<td align="center">${stu.lxdh }</td>
			  		</tr>
			  		<tr>
			  			<td colspan="4" align="center">在学校任何职务</td>
			  			<td colspan="5" align="center">
			  				${zw }
			  			</td>
			  		</tr>
			  		<tr>
			  			<td colspan="2" align="center">奖惩<br/><br/>情况</td>
			  			<td colspan="7" valign="top">
			  				<p>
			  					${rs.jcqk }
			  				</p>
			  			</td>
			  		</tr>
			  		<tr>
			  			<td align="center">毕<br/><br/>业<br/><br/>鉴<br/><br/>定</td>
			  			<td colspan="8" valign="top">
			  				<p>
			  					&nbsp;&nbsp;${rs.byjd }
			  				</p>
			  				<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
			  				<p align="right">
			  					班主任：
			  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  				</p>
			  			</td>
			  		</tr>
			  	</table>
			</td>
			<td>
				&nbsp;
			</td>
			<td width="800px;">
				<span style='font-size:12.0pt;height: 20px;'> 
					班级：<u>&nbsp;&nbsp;&nbsp;${stu.bjmc }&nbsp;&nbsp;&nbsp;</u>
					专业：<u>&nbsp;&nbsp;&nbsp;${stu.zymc }&nbsp;&nbsp;&nbsp;</u>
				</span>
			  	<table class="printtab" width="100%" height="800px;">
			  		<tr>
			  			<td colspan="10" align="center">学   习   成   绩</td>
			  		</tr>
			  		<tr>
			  			<td width="8%" align="center">序号</td>
			  			<td colspan="3" width="32%" align="center">科  目</td>
			  			<td width="10%" align="center">成绩</td>
			  			<td width="8%" align="center">序号</td>
			  			<td colspan="3" width="30%" align="center">科  目</td>
			  			<td width="10%" align="center">成绩</td>
			  		</tr>
			  		
			  		<%
			  			List<HashMap<String,String>> cjList = (List<HashMap<String,String>>)request.getAttribute("cjList");
			  			
			  			int i = 0 ;
			  			
			  			while (cjList.size()-1 > i){
			  		 %>
			  		
			  			<tr>
			  				<td align="center"><%=i+1 %></td>
			  				<td align="center" colspan="3"><%=null != cjList.get(i).get("kcmc") ? cjList.get(i).get("kcmc") : "" %></td>
			  				<td align="center"><%=null != cjList.get(i).get("cj") ? cjList.get(i).get("cj") : "" %></td>
			  				<%i++; %>
			  				<td align="center"><%=i+1 %></td>
			  				<td align="center" colspan="3"><%=null != cjList.get(i).get("kcmc") ? cjList.get(i).get("kcmc") : "" %></td>
			  				<td align="center"><%=null != cjList.get(i).get("cj") ? cjList.get(i).get("cj") : "" %></td>
			  			</tr>
					<%
						i++;
						}
					 %>  		
			  		
			  		<tr>
			  			<td align="center" colspan="2" width="13%" style='font-size:11.0pt;font-family:宋体;"Times New Roman"'>
			  				职业技术<br/>等级
			  			</td>
			  			<td align="center" width="12%" style='font-size:11.0pt;font-family:宋体;"Times New Roman"'>
			  				${rs.zyjsdj }
			  			</td>
			  			<td align="center" width="13%" style='font-size:11.0pt;font-family:宋体;"Times New Roman"'>
			  				职业鉴定<br/>
							理论成绩
			  			</td>
			  			<td align="center" width="12%" style='font-size:11.0pt;font-family:宋体;"Times New Roman"'>
			  				${rs.zjjdllcj }
			  			</td>
			  			<td align="center" colspan="2" width="13%" style='font-size:11.0pt;font-family:宋体;"Times New Roman"'> 
			  				职业鉴定<br/>技能成绩
			  			</td>
			  			<td align="center" width="12%" style='font-size:11.0pt;font-family:宋体;"Times New Roman"'>
			  				${rs.zjjdjncj }
			  			</td>
			  			<td align="center" width="13%" style='font-size:11.0pt;font-family:宋体;"Times New Roman"'>操作等第</td>
			  			<td align="center" width="12%" style='font-size:11.0pt;font-family:宋体;"Times New Roman"'>
			  				${rs.cxdd }
			  			</td>
			  		</tr>
			  		<tr>
			  			<td align="center" colspan="2" style='font-size:11.0pt;font-family:宋体;"Times New Roman"'>
			  				<bean:message key="lable.xb" /><br/>推荐<br/>意见
			  			</td>
			  			<td colspan="3" valign="top" style='font-size:11.0pt;font-family:宋体;"Times New Roman"'>
			  				<p>
			  				&nbsp;&nbsp;&nbsp;&nbsp;${rs.xytjyj }
			  				</p>
			  			</td>
			  			<td align="center" colspan="2" style='font-size:11.0pt;font-family:宋体;"Times New Roman"'>
			  				接收<br/>单位<br/>意见
			  			</td>
			  			<td colspan="3" valign="top" style='font-size:11.0pt;font-family:宋体;"Times New Roman"'>
			  				<p>
			  					&nbsp;&nbsp;&nbsp;&nbsp;${rs.jsdwyj }
			  				</p>
			  			</td>
			  		</tr>
			  		<tr>
			  			<td align="center" colspan="2" style='font-size:11.0pt;font-family:宋体;"Times New Roman"'>
			  				备注
			  			</td>
			  			<td colspan="8" valign="top" style='font-size:11.0pt;font-family:宋体;"Times New Roman"'>
			  				<p>
			  					&nbsp;&nbsp;&nbsp;&nbsp;${rs.bz }
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
  </body>
</html>
