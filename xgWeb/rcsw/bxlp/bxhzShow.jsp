<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script language="javascript" src="js/xszzFunction.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/checkUtils.js"></script>
	</head>

	<body>

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�ճ�����-���ա�����-���ջ���</a>
			</p>
		</div>


		<html:form action="/rcsw_bxlp" method="post">
			<input type="hidden" name="pk" value="${pk }" />
			<input type="hidden" id="url" name="url"
				value="/rcsw_bxlp.do?method=bxwhSave" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>���ա�����-���ջ���</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" id="buttonSave" onclick="window.close();return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								ѧ��
							</th>
							<td width="34%">
								<html:text property="xh" name="hzxx" readonly="true"></html:text>
							</td>
							<th width="16%">
								����
							</th>
							<td width="34%">
								<html:text property="xm" name="hzxx" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td>
								<html:text property="xb" name="hzxx" readonly="true"></html:text>
							</td>
							<th>
								���֤��
							</th>
							<td>
								<html:text property="sfzh" readonly="true" name="hzxx"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								�꼶
							</th>
							<td>
								<html:select property="nj" name="hzxx" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
								����
							</th>
							<td>
								<html:select property="xydm" name="hzxx" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								רҵ����
							</th>
							<td>
								<html:select property="zydm" name="hzxx" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>
								�༶����
							</th>
							<td>
								<html:select property="bjdm" name="hzxx" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								���
							</th>
							<td>
								<html:select property="nd" name="hzxx" disabled="true">
									<html:options collection="ndList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
							<th>
								�ۼƽ�
							</th>
							<td>
								<input type="text" value="${hzxx.ljje }" readonly="true" />
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4"><span>���ռ�¼</span></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">

								<div class="formbox">
									 <logic:empty name="rs">
												<font color="red">δ�ҵ��κμ�¼��</font>
											</logic:empty>
									<logic:notEmpty name="rs">
										<table summary="" class="dateline" align="" width="100%">
											<thead>
												<tr align="center" style="cursor:hand">
													<logic:iterate id="tit" name="topTr" offset="0"
														scope="request">
														<td id="${tit.en}" nowra>
															${tit.cn}
														</td>
													</logic:iterate>
												</tr>
											</thead>
											<tbody>
												<logic:iterate name="rs" id="s">
													<tr onclick="rowOnClick(this)" style="cursor:hand;">
														<logic:iterate id="v" name="s" offset="0">
															<td align=center>
																<bean:write name="v" />
															</td>
														</logic:iterate>
													</tr>
												</logic:iterate>
											</tbody>
										</table>
										<!--��ҳ��ʾ-->
										<!--��ҳ��ʾ-->
									</logic:notEmpty>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
