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
	<body >
		<html:form action="/fdywork_research" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>辅导员管理 - 思政考核 - 辅导员工作问卷调查</a>
				</p>
			</div>	
			<div class="toolbox">
			 <!-- 按钮 -->
			 <div class="buttonbox">
			    <ul>
				<li> <a href="#" onclick="if(document.getElementById('fdy').value==''){alert('请选择辅导员！');}else{showTopWin('/xgxt/wj_view.do?act=answer&zgh='+document.getElementById('fdy').value,'700','800')}" class="btn_zj"> 填写问卷 </a> </li>
			    </ul>
			 </div>
		<div class="searchtab">
			<table width="100%" border="0">
			<thead>				
			<logic:equal value="0" name="sfkf">
			<div align="center"><font color="red">调查问卷暂未开放！</font></div>
			</logic:equal>
			<logic:equal value="1" name="sfkf">
						<bean:write name="fdyglForm" property="xn" />
						学年 (
						<bean:write name="fdyglForm" property="nd" />
						年度)， 第
						<bean:write name="fdyglForm" property="xq" />
						学期 辅导员工作问卷调查
			</logic:equal>
				<tbody>
							<tr>
								<td align="left" colspan="4">
									<logic:notEqual value="stu" name="userType" scope="session">
	                                 <bean:message key="lable.xsgzyxpzxy" />： 
	                               
	                                  <html:select property="xydm" style="width:230px" styleId="xy"
											onchange="refreshForm('fdywork_research.do')">
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
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script> 
	</body>	
</html>
