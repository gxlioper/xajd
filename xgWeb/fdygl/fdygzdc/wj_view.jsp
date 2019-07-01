<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/fdyglFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	</head>
	<body onload="loadTestSelectItem();">
		<html:form action="/wj_view" method="post">
			<input type="hidden" id="xxStr" name="xxStr" value="<bean:write name="xxStr"/>" />	
			<input type="hidden" id="stLen" name="stLen" value="<bean:write name="stLen"/>" />	
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>辅导员管理 - 信息维护 - 辅导员工作调查问卷维护 - 问卷预览</a>
				</p>
			</div>
			<logic:empty name="stList">
			<div align="center"><font color="red"><br/><br/><br/>暂无问题!</font></div>
			</logic:empty>
			<logic:notEmpty name="stList">
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">

						<tr>
							<td align="center">
								<B><font size="5"> 辅导员工作情况学生问卷 </font> </B>
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
								<bean:message key="lable.xsgzyxpzxy" />___________&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								性别___________&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								年级___________&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td align="left">
								<B>同学您好！<br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;为了了解辅导员工作状况，我们进行这次抽样调查，所有数据均用于统计研究，请按实际情况和真实想法回答问题。感谢您对本次调查的大力支持。祝您学习进步！<br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									学生工作部（处）</B>
							</td>
						</tr>
						<tr>
							<td align="left">
								<logic:notEmpty name="stList">
									<table width="100%" class="tbstyle" id="tp">
										<logic:iterate id="stList" name="stList">
											<tr>
												<td align="left"
													id="<bean:write name="stList" property="id"/>" width="100%">
													<B><bean:write name="stList" property="id" />
													.</B>&nbsp;
													<bean:write name="stList" property="stmc" />
													(&nbsp; )
												</td>
											</tr>
										</logic:iterate>
									</table>
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<td>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;问卷到此结束，再次感谢您的耐心与真诚合作！
							</td>
						</tr>
					</table>
						<div class="buttontool" id="btn" align="center">
						<button onclick="expTab('rsT','','')">
							打印 
						</button>
						</div>			
				</div>
			</logic:notEmpty>
		</html:form>		
	</body>
</html>
