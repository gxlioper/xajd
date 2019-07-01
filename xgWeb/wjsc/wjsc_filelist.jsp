<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	
	<script language="javascript" src="js/function.js"></script>
	</head>

	<body>
		<FORM method="POST" name="myform" action="eWebEditor/submit.jsp">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>文件管理 - 接收 - 文件接收</a>
				</p>
			</div>

			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	文 件 列 表&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">您暂无可接收文件！</font> 
			 		 </logic:empty>
			    </span>
			    </h3>

				<logic:notEmpty name="rs">
					 <table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" bgcolor="#D0E0EE">
								<td>
									文件号
								</td>
								<td>
									文件名
								</td>
								<td>
									发放部门
								</td>
								<td>
									文件发放时间
								</td>
							</tr>
						</thead>
						<tbody>
						<logic:iterate id="list" name="rs">
							<tr onmouseover="rowOnClick(this)">
								<td>
									<a
										href="fileView.do?wjh=<bean:write name="list" property="wjh"/>&yd=yes"
										target="_blank" style="color:blue"> <bean:write name="list" property="wjh" />
									</a>
									<logic:equal name="list" property="newmark" value="new">
										<img style="width:30px" src="images/newmark.gif" />
									</logic:equal>
								</td>
								<td>
									<bean:write name="list" property="wjm" />
								</td>
								<td>
									<bean:write name="list" property="bmmc" />
								</td>
								<td>
									<bean:write name="list" property="wjffsj" />
								</td>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
				</logic:notEmpty>
				<logic:empty name="rs">
					<center>
						暂无上传
					</center>
				</logic:empty>
		</FORM>
	</body>
</html>
