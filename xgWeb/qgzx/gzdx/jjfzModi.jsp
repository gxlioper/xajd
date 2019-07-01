<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
<body>
	<div class="title">
		<div class="title_img" id="title_m"> 
			前所在位置：勤工助学 - 勤工助学评奖评优 - 积极分子审核	
		</div>
	</div>
		<html:form action="/gzdxQgzx.do" method="post">
			<table class="tbstyle" width="100%">
				<tr>
					<td align="center" width="16%">
							学号：
						</td>
						<td align="left" width="34%">
							${rs.xh}						
						</td>
					<td width="16%">
						<div align="center">
							姓名：
						</div>
					</td>
					<td width="34%">
						${rs.xm}
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							性别：
						</div>
					</td>
					<td>
						${rs.xb}
					</td>
					<td>
						<div align="center">
							身份证号：
						</div>
					</td>
					<td>
						${rs.sfzh}
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							民族：
						</div>
					</td>
					<td>
						${rs.mzmc}
					</td>
					<td>
						<div align="center">
							政治面貌：
						</div>
					</td>
					<td>
						${rs.zzmmmc}
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							年级：
						</div>
					</td>
					<td>
						${rs.nj}
					</td>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />名称：
						</div>
					</td>
					<td>
						${rs.xymc}
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							专业名称：
						</div>
					</td>
					<td>
						${rs.zymc}
					</td>
					<td>
						<div align="center">
							班级名称：
						</div>
					</td>
					<td>
						${rs.bjmc}
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							学年：
						</div>
					</td>
					<td colspan="3">
						${rs.xn}
					</td>					
				</tr>
				<tr>
					<td>
						<div align="center">
							年度：
						</div>
					</td>
					<td colspan="3">
						${rs.nd}
					</td>					
				</tr>				
				<tr>
					<td>
						<div align="center">
							学期：
						</div>
					</td>
					<td colspan="3">
						${rs.xqmc}
					</td>
				</tr>						
				
				<tr>
					<td>
						<div align="center">
							辅导员审核：
						</div>
					</td>
					<td colspan="3">
						${rs.save_fdysh}
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							辅导员审核意见：
						</div>
					</td>
					<td colspan="3">
						${rs.save_fdyshyj}
					</td>
				</tr>

				<tr>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />审核：
						</div>
					</td>
					<td colspan="3">
						${rs.save_xysh}
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />审核意见：
						</div>
					</td>
					<td colspan="3">
						${rs.save_xyshyj}
					</td>
				</tr>

				<tr>
					<td>
						<div align="center">
							学校审核：
						</div>
					</td>
					<td colspan="3">
						${rs.save_xxsh}
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							学校审核意见：
						</div>
					</td>
					<td colspan="3">
						${rs.save_xxshyj}
					</td>
				</tr>	
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<logic:notEqual value="view" name="type">
				<button type="button" class="button2" id="buttonSave"
					onClick="yz();">
					保&nbsp;&nbsp;&nbsp;&nbsp;存
				</button>
				&nbsp;&nbsp;
				</logic:notEqual>
				<button type="button" class="button2"
					onClick="Close()">
					关&nbsp;&nbsp;&nbsp;&nbsp;闭
				</button>
			</div>
		</html:form>
	</body>	
