<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
</head>
<body onload="checkWinType();" onunload="ableAllSel('xn-nd')">
		<html:form action="/xySetStuNum.do?act=save" method="post">
			<br />
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					未找到任何记录！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				
				<center>
					<div id="repTit">
						${jxjsqxn }
						学年（
						${jxjsqnd }
						年度）奖学金参评人数一览表
					</div>
				</center>
				<div class="tab">
					<table width="98%" id="rsTable" class="formlist">
						<thead>
							<tr align="left" style="cursor:hand">
								<th >
									部门代码
								</th>
								<th>
									部门名称
								</th>
								<th >
									年级
								</th>
								<th >
									参评人数(<bean:message key="lable.xsgzyxpzxy" />实际人数)
								</th>
							</tr>
						</thead>
						<tbody>
						<logic:iterate id="rs" name="rs">
							<tr align="left" onclick="rowOnClick(this)" style="cursor:hand">
								<td>
									<bean:write name="rs" property="bmdm" />
								</td>
								<td>
									<bean:write name="rs" property="mc" />
								</td>
								<td>
									<bean:write name="rs" property="nj" />
								</td>
								<td>
									<bean:write name="rs" property="cprs" />
								</td>
							</tr>
						</logic:iterate>
						</tbody>
						<tfoot>
					      <tr>
					        <td colspan="4">
					          <div class="btn">					            
<%--								<button type="button" class="button2" onclick="expTab('rsTable','','repTit')">--%>
<%--									生成报表--%>
<%--								</button>--%>
<%--								&nbsp;&nbsp;&nbsp;&nbsp;--%>
								<button type="button" class="button2" onclick="Close();return false;">
									关闭
								</button>
					          </div>
					        </td>
					      </tr>
					    </tfoot>
					</table>
				</div>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
