<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- 头文件 -->
<!-- 打印控件begin -->
<object id="WebBrowser" width=0 height=0
	classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
<style media='print'>
		.noPrin{display:none;}
	</style>
<!-- end -->
<body>
	<html:form action="/pjpyzgkdwh" method="post">
		<logic:empty name="rs">
			<p align="center">无任何数据！</p>
		</logic:empty>
		<logic:notEmpty name="rs">
			<logic:iterate id="v" name="rs">
				<div align="center" style="font-size:20px;font:'黑体'"><b><logic:iterate id="d" name="v" offset="0" length="1"><logic:iterate id="s" name="d" offset="1" length="1"><bean:write name="s" /></logic:iterate><logic:iterate id="s" name="d" offset="2" length="1"><bean:write name="s" /></logic:iterate></logic:iterate>级素质测评结果汇总表</b></div>
				<table width="100%" class="printstyle">
				<thead>
						<tr align="center" style="cursor:hand">
							<td nowrap align="center" width="34%">
								专业
							</td>
							<td nowrap align="center" width="10%">
								人数
							</td>
							<td nowrap align="center" width="14%">
								优秀
							</td>
							<td nowrap align="center" width="14%">
								良好
							</td>
							<td nowrap align="center" width="14%">
								合格
							</td>
							<td nowrap align="center" width="14%">
								不合格
							</td>
						</tr>
					</thead>
				<logic:iterate id="d" name="v">
					<tr onclick="rowOnClick(this)" style="cursor:hand">
					<logic:iterate id="s" name="d" offset="4">					
						<td align=center>
							<bean:write name="s" />
						</td>
					</logic:iterate>
					</tr>
				</logic:iterate>
				</table>
				<br/><br/>
			</logic:iterate>
		</logic:notEmpty>

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
	</html:form>
</body>
