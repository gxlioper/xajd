<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<body>
	<html:form action="/pjpyLsxy" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>	
			<div class="title_img" id="title_m">
				当前所在位置: ${title }
			</div>
		</div>
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="9" align="center">
						学生成绩详细信息
					</td>
				</tr>
				<tr style="height:23px">
					<td align="center">
						学年
					</td>
					<td align="center">
						学期
					</td>
					<td align="center">
						学号
					</td>
					<td align="center">
						姓名
					</td>
					<td align="center">
						班级
					</td>
					<td align="center">
						年级	
					</td>
					<td align="center">
						课程名称
					</td>
					<td align="center">
						成绩
					</td>
					<td align="center">
						总学时
					</td>
				</tr>	
			</thead>
			<logic:iterate name="rs" id="s">
				<tr onclick="rowOnClick(this)"
					style="cursor:hand;">					
					<logic:iterate id="v" name="s">
						<td align=center nowrap="nowrap">
							<bean:write name="v" />
						</td>
					</logic:iterate>
				</tr>
			</logic:iterate>
		</table>
		<div class="buttontool" align="center">			
			<button class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
				id="buttonClose">
				关 闭
			</button>
		</div>
	</html:form>
</body>
