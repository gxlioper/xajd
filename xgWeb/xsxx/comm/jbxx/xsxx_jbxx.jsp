<%@ page language="java" contentType="text/html; charset=GBK"%>

<table align="center" class="formlist breakword" style="width:100%;">
	<logic:iterate name="xsqList" id="xsq">
		<logic:equal name="xsq" property="sfzd" value="·ñ">
			<thead>
				<tr>
					<th colspan="4" onclick="showHiddenNr('hi_${xsq.xsqdm }');" style="cursor:hand;display:<logic:equal name="xsq" property="sfzk" value="·ñ">none</logic:equal>">
						<span>${xsq.xsqmc }</span>

					</th>
				</tr>
			</thead>
			<tbody id="hi_${xsq.xsqdm }">
				<!-- ÕÕÆ¬ÏÔÊ¾ÔÚÓÒ±ß-->
				<logic:equal name="xsq" property="zpxs" value="ÊÇ">
					<logic:equal name="xsq" property="zpwz" value="ÓÒ">
						<tr>
							<logic:iterate name="xsq" property="xshxx" id="xsh">
								<logic:equal name="xsh" property="leftMap.dygl" value="1">
									<logic:equal name="xsh" property="leftMap.dygh" value="1">
										<th width="16%">
											${xsh.leftMap.zdm }
										</th>
										<td width="34%" id="td_${xsh.leftMap.zd}">
											<bean:write name="rs" property="${xsh.leftMap.zd}" />
										</td>
									</logic:equal>
								</logic:equal>
							</logic:iterate>
							<!-- ÓÒ -->
							<th width="16%" rowspan="${xsq.zpszhs }">
								Ñ§
								</br>
								Éú
								</br>
								ÕÕ
								</br>
								Æ¬
							</th>
							<td width="34%" rowspan="${xsq.zpszhs }">
								<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}"
									width="96" height="128" />
							</td>
						</tr>
						<logic:iterate name="xsq" property="xshxx" id="xsh" offset="1"
							length="${xsq.zpszhs-1}">
							<logic:empty name="xsh" property="leftMap">
								<tr>
									<th width="16%">
										&nbsp;
									</th>
									<td width="34%">
										&nbsp;
									</td>
								</tr>
							</logic:empty>
							<logic:notEmpty name="xsh" property="leftMap">
								<tr>
									<th width="16%">
										${xsh.leftMap.zdm }
									</th>
									<td width="34%" id="td_${xsh.leftMap.zd}">
										<bean:write name="rs" property="${xsh.leftMap.zd}" />
									</td>
								</tr>
							</logic:notEmpty>
						</logic:iterate>
					</logic:equal>
					<!-- ÕÕÆ¬ÏÔÊ¾ÔÚ×ó±ß-->
					<logic:equal name="xsq" property="zpwz" value="×ó">
						<tr>
							<!-- ×ó -->
							<th width="16%" rowspan="${xsq.zpszhs }">
								Ñ§
								</br>
								Éú
								</br>
								ÕÕ
								</br>
								Æ¬
							</th>
							<td width="34%" rowspan="${xsq.zpszhs }">
								<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}"
									width="96" height="128" />
							</td>
							<!-- ÓÒ -->
							<logic:iterate name="xsq" property="xshxx" id="xsh" length="1">
								<logic:notEmpty name="xsh" property="rightMap">
									<logic:equal name="xsh" property="rightMap.dygl" value="3">
										<logic:equal name="xsh" property="rightMap.dygh" value="1">
											<th width="16%">
												${xsh.rightMap.zdm }
											</th>
											<td width="34%" id="td_${xsh.rightMap.zd}">
												<bean:write name="rs" property="${xsh.rightMap.zd}" />
											</td>
										</logic:equal>
									</logic:equal>
								</logic:notEmpty>
								<logic:empty name="xsh" property="rightMap">
									<th width="16%">
										&nbsp;
									</th>
									<td width="34%">
										&nbsp;
									</td>
								</logic:empty>
							</logic:iterate>
						</tr>
						<logic:iterate name="xsq" property="xshxx" id="xsh" offset="1"
							length="${xsq.zpszhs-1}">
							<logic:empty name="xsh" property="rightMap">
								<tr>
									<th width="16%">
										&nbsp;
									</th>
									<td width="34%">
										&nbsp;
									</td>
								</tr>
							</logic:empty>
							<logic:notEmpty name="xsh" property="rightMap">
								<tr>
									<th width="16%">
										${xsh.rightMap.zdm}
									</th>
									<td width="34%" id="td_${xsh.rightMap.zd}">
										<bean:write name="rs" property="${xsh.rightMap.zd}" />
									</td>
								</tr>
							</logic:notEmpty>
						</logic:iterate>
					</logic:equal>

				</logic:equal>
				<logic:iterate name="xsq" property="xshxx" id="xsh" indexId="index">
					<logic:empty name="xsh">
						<tr>
							<th width="16%">
								&nbsp;
							</th>
							<td width="34%">
								&nbsp;
							</td>
							<th width="16%">
								&nbsp;
							</th>
							<td width="34%">
								&nbsp;
							</td>
						</tr>
					</logic:empty>
					<logic:notEmpty name="xsh">
						<logic:equal name="xsh" property="sfhb" value="ÊÇ">
							<tr>
								<logic:empty name="xsh" property="leftMap">
									<th width="16%">
										&nbsp;
									</th>
									<td colspan="3" width="84%">
										&nbsp;
									</td>
								</logic:empty>
								<logic:notEmpty name="xsh" property="leftMap">
									<th width="16%">
										${xsh.leftMap.zdm }
									</th>
									<td colspan="3" width="84%" id="td_${xsh.leftMap.zd}">
										<bean:write name="rs" property="${xsh.leftMap.zd}" />
									</td>
								</logic:notEmpty>
							</tr>
						</logic:equal>
						<logic:equal name="xsh" property="sfhb" value="·ñ">
							<tr>
								<logic:empty name="xsh" property="leftMap">
									<th width="16%">
										&nbsp;
									</th>
									<td width="34%">
										&nbsp;
									</td>
								</logic:empty>
								<logic:notEmpty name="xsh" property="leftMap">
									<th width="16%">
										${xsh.leftMap.zdm }
									</th>
									<td width="34%" id="td_${xsh.leftMap.zd}">
										<bean:write name="rs" property="${xsh.leftMap.zd}" />
									</td>
								</logic:notEmpty>
								<logic:empty name="xsh" property="rightMap">
									<th width="16%">
										&nbsp;
									</th>
									<td width="34%">
										&nbsp;
									</td>
								</logic:empty>
								<logic:notEmpty name="xsh" property="rightMap">
									<th width="16%">
										<logic:equal name="xsh" property="rightMap.wkxz" value="²»¿ÉÎª¿Õ">
											<font color="red">*</font>
										</logic:equal>
										${xsh.rightMap.zdm }
									</th>
									<td width="34%" id="td_${xsh.rightMap.zd}">
										<bean:write name="rs" property="${xsh.rightMap.zd}" />
									</td>
								</logic:notEmpty>
							</tr>
						</logic:equal>
					</logic:notEmpty>
				</logic:iterate>
			</tbody>
		</logic:equal>
	</logic:iterate>
</table>
