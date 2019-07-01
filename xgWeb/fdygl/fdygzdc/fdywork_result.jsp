<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
    </script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/fdyglFunction.js"></script>
</head>
	<body onload="xyDisabled('xy')">
		 <center>			
			<html:form action="/fdywork_result" method="post">	
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>辅导员管理 - 思政考核 - 调查结果查看</a>
				</p>
			</div>
			<div class="toolbox">
			 <!-- 按钮 -->
			 <logic:notEmpty name="rs">
			 <div class="buttonbox">
			    <ul>
				<li> <a href="#" onclick="expTab('rsTable','','')" class="btn_dy"> 打印报表 </a> </li>
			    </ul>
			 </div>
			 </logic:notEmpty>
			
			<div class="searchtab">
			<table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="6">
		            <div class="btn">
		              <input type="hidden" name="go" value="a" />
		              <button class="btn_cx" id="search_go" 
		              	onclick="if(document.getElementById('fdy').value==''){alert('请选择辅导员！');}else{allNotEmpThenGo('/xgxt/fdywork_result.do')}">
		              	查 询
		              </button>
		              &nbsp;&nbsp;&nbsp;&nbsp;
		              <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
		              	重 置
		              </button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			
			<input type="hidden" name="userType" id="userType" value="<bean:write name ="userType" scope="session" />"/>
				<legend>
					<bean:write name="fdyglForm" property="xn" />
					学年 (
					<bean:write name="fdyglForm" property="nd" />
					年度)， 第
					<bean:write name="fdyglForm" property="xq" />
					学期 辅导员工作问卷调查结果查询
				</legend>
					<tbody>
						<tr>
							<td align="left" nowrap >
								<logic:notEqual value="stu" name="userType" scope="session">
	                                 <bean:message key="lable.xsgzyxpzxy" />： 
	                                  <html:select property="xydm"
										style="width:230px" styleId="xy"
										onchange="refreshForm('fdywork_result.do')">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select> &nbsp;&nbsp;&nbsp;&nbsp; 
	                                </logic:notEqual>
								辅导员：
								<html:select property="zgh" style="width:230px" styleId="fdy"
									onchange="">
									<html:option value=""></html:option>
									<html:options collection="fdyList" property="zgh"
										labelProperty="xm" />
								</html:select>
							</td>
						</tr>
					</tbody>
				</table>
			<logic:empty name="rs">
				<p align="center">
					未找到任何记录！
				</p>
			</logic:empty>
				<logic:notEmpty name="rs">
					<br>
					<table width="99%" class="tbstyle" id="rsTable">
						<tr>
							<td align="center">
								<B><font size="5"> 辅导员工作情况调查结果 </font> </B>
								<div align="right">
									<bean:write name="fdyglForm" property="xn" />
									学年 (
									<bean:write name="fdyglForm" property="nd" />
									年度)， 第
									<bean:write name="fdyglForm" property="xq" />
									学期
								</div>
							</td>
						</tr>
						<tr>
							<td align="center">
							    姓名：<bean:write name="xm" scope="request"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								性别：<bean:write name="xb" scope="request"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<bean:message key="lable.xsgzyxpzxy" />：<bean:write name="bmmc" scope="request"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;								
							</td>
						</tr>
						<tr>
							<td align="left">
								<b> 调查参与总人数： <bean:write name="allCount" /> 人： 平均分为：   <bean:write name="pjf" /></b>
							</td>
						</tr>
								<tr>
									<td align="center">										
										<table width="100%" class="tbstyle">
									<thead>
									  <tr>
									  <td width="20%">
									    问题列
									  </td>									 								  
									  <td  width="20%">
									    选项列
									  </td>
									  <td width="10%">
									     人数
									  </td>
									  <td>
									    比例
									  </td>
									  
									  </tr>
									</thead>
									 <logic:iterate name="rs" id="s">
										<logic:iterate id="v" name="s" property="stList">
											<tr>
											 <td rowspan="<bean:write name="v" property="xxcount"/>" onmouseover=this.style.backgroundColor="#D7E6F0" onmouseout=this.style.backgroundColor="#FFFFFF">	
											   第<bean:write name="v" property="id"/>道问题											
											   </td>
											   <logic:notEqual value="0" name="v" property="xxcount">											  
											   	<logic:iterate id="n" name="s" property="xxList" >									 																																											   										    											    												
												<td onmouseover=this.style.backgroundColor="#D7E6F0" onmouseout=this.style.backgroundColor="#FFFFFF">													
													<bean:write name="n" property="xxl" />													
												</td>
												<td onmouseover=this.style.backgroundColor="#D7E6F0" onmouseout=this.style.backgroundColor="#FFFFFF">
												  <bean:write name="n" property="sumc" />
												</td>
												<td onmouseover=this.style.backgroundColor="#D7E6F0" onmouseout=this.style.backgroundColor="#FFFFFF">
											       <img src="fdygl/fdygzdc/total.jpg" width="<bean:write name="n" property="numbl"/>" height="10px"/>												   
												   <bean:write name="n" property="numbl" />%								   
												</td>
												<% out.print("</tr>"); %>	
												</logic:iterate>
												</logic:notEqual>
												<logic:equal value="0" name="v" property="xxcount">
													<td>
													</td>
													<td>
													</td>
													<td>
													</td>
												</logic:equal>
											</tr>																																
											</logic:iterate>
									</logic:iterate>
								  </table>
								</td>
							</tr>
					</table>
				</logic:notEmpty>				
	   </html:form>
	  </center>
	</body>	
</html>
