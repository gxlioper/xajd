<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<body onload="">
	<html:form action="/xmlgpjpy" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		当前位置:评奖评优 - 参数设置 - 金额查看
       		</div>
    	</div>
			<table style="width:100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="${num }" align="center">
						<b>	${xn }学年各<bean:message key="lable.xsgzyxpzxy" />奖学金金额调整汇总表</b>
						</td>
					</tr>
				</thead>
				<!-- 输出表头 -->
				<logic:notEmpty name="title">
					<logic:iterate name="title" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand;" align="center">
								<logic:iterate id="v" name="s" >
									<td>
										${v }
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
				</logic:notEmpty>
				<!-- 输出结果 -->
				<logic:notEmpty name="rs">
					<logic:iterate name="rs" id="oneRs">
							<tr onclick="rowOnClick(this)" style="cursor:hand;" align="center">
								<logic:iterate id="oneData" name="oneRs" >
									<td>
										${oneData }
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
				</logic:notEmpty>
			</table>
			<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						关 闭
					</button>
				</div>	
	</html:form>
</body>