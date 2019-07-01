<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- Í·ÎÄ¼þ -->
<body>
<logic:empty name="rsNum">
				
</logic:empty>
<logic:notEmpty name="rsNum">
	<logic:notEmpty name="wsjc">
	<table width="99%" id="rsTable" class="tbstyle">
					  <thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="wsjcTr" offset="0" >
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap  width="20%">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="wsjc" id="s">
							<tr style="cursor:hand">
								<logic:iterate id="v" name="s" offset="0">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
		</logic:notEmpty>
</logic:notEmpty>
</body>