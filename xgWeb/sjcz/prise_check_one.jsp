<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
</head>
	<body onload="checkWinType();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<html:form action="/data_search" method="post">
				
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�������� - ��� - ��ѧ����� - ������ѧ�����</a>
				</p>
			</div>
			
			<input type="hidden" name="pkVal" value="${pkVal }" />
			<input type="hidden" name="tg" id="tg" value="${tgres }" />
			<input type="hidden" name="res" id="res" value="${sResult}" />
			<input type="hidden" name="userType" id="userType"
				value="${userType }" />
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }" />
			<input type="hidden" name="xsh" id="xsh" value="${xxsh }" />
			<input type="hidden" name="ysh" id="ysh" value="${xysh }" />
			<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
			<!-- �㽭���� -->
			<logic:present name="showzjjd">
				<table width="100%" align="center" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								������ѧ�����
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<th align="right">
							ѧ��
						</th>
						<td align="left" id="selxh">
							<bean:write name="XH" />
							<input type="hidden" name="xh" id="xh"
								value="<bean:write name="XH" />" />
						</td>
						<th align="right">
							���
						</th>
						<td align="left">
							<bean:write name="ND" />
							<input type="hidden" name="nd" id="nd"
								value="<bean:write name="ND" />" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							����
						</th>
						<td align="left">
							<bean:write name="XM" />
						</td>
						<th align="right">
							ѧ��
						</th>
						<td align="left">
							<bean:write name="XN" />
							<input type="hidden" name="xn" id="xn"
								value="<bean:write name="XN" />" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							�Ա�
						</th>
						<td align="left">
							<bean:write name="XB" />
						</td>
						<th align="right">
							��ѧ��
						</th>
						<td align="left">
							<bean:write name="JXJMC" />
						</td>
					</tr>

					<tr style="height:22px">
						<th align="right">
							�꼶
						</th>
						<td align="left">
							<bean:write name="NJ" />
						</td>
						<th align="right">
							�����ܷ�
						</th>
						<td align="left">
							${dyzf }
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							<bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td align="left">
							<bean:write name="XYMC" />
						</td>
						<th align="right">
							�����ܷ�
						</th>
						<td align="left">
							${zyzf }
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							רҵ
						</th>
						<td align="left">
							<bean:write name="ZYMC" />
						</td>
						<th align="right">
							�����ܷ�
						</th>
						<td align="left">
							${tyzf }
						</td>
					</tr>

					<tr style="height:22px">
						<th align="right">
							�༶
						</th>
						<td align="left">
							<bean:write name="BJMC" />
						</td>
						<th align="right">
							���
						</th>
						<td align="left">
							<html:select property="yesNo" styleId="yesNo">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>

					<tr style="height:22px">
						<th align="right">
							�ۺϲ����ܷ�
						</th>
						<td align="left">
							${zhszcpzf }
						</td>
						<th align="right">
							�ۺϲ�������
						</th>
						<td align="left">
							${zhszpm }
						</td>
					</tr>
					<tr style="height: 22px">
						<th align="right">
							�༶�����ȼ�
						</th>
						<td align="left">
							${bjpddj }
						</td>
						<td align="right">
							&nbsp;
						</td>
						<td align="left">
							&nbsp;
						</td>
					</tr>
					<logic:equal value="xy" scope="request" name="userType">
						<tr>
							<th align="right">
								����Ա���
							</th>
							<td align="left" colspan="3">
								<textarea name="fdyyj" style="width:100%" rows="3" type="_moz"><bean:write name="FDYYJ" /></textarea>
							</td>
						</tr>
						<tr>
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />����ίԱ�����
							</th>
							<td align="left" colspan="3">
								<textarea name="wyhyj" style="width:100%" rows="3" type="_moz"><bean:write name="XYPSWYHYJ" /></textarea>
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />������
							</th>
							<td align="left" colspan="3">
								<textarea name="xyyj" style="width:100%" rows="3" type="_moz"><bean:write name="XYSHYJ" /></textarea>
							</td>
						</tr>

					</logic:equal>
					<logic:equal value="xx" scope="request" name="userType">
						<tr>
							<th align="right">
								����Ա���
							</th>
							<td align="left" colspan="3">
								<bean:write name="FDYYJ" />
							</td>
						</tr>
						<tr>
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />����ίԱ�����
							</th>
							<td align="left" colspan="3">
								<bean:write name="XYPSWYHYJ" />
							</td>
						</tr>
						<tr>
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />������
							</th>
							<td align="left" colspan="3">
								<bean:write name="XYSHYJ" />
							</td>
						</tr>
						<tr>
							<th align="right">
								ѧУ������
							</th>
							<td align="left" colspan="3">
								<textarea name="xxyj" style="width:100%" rows="3" type="_moz"><bean:write name="XXSHYJ" /></textarea>
							</td>
						</tr>

					</logic:equal>
				</table>
			</logic:present>
			<!-- ���㽭���� -->
			<logic:notPresent name="showzjjd">
			<div class="tab">
				<table width="100%" align="center" class="formlist">
					<thead>
						<tr style="height:22px">
							<th colspan="4">
							<span>������ѧ�����</span>
							</th>
						</tr>
					</thead>
					<tr style="height:22px">
						<th align="right" width="15%">
							ѧ��
						</th>
						<td align="left" id="selxh">
							<bean:write name="XH" />
							<input type="hidden" name="xh" id="xh"
								value="<bean:write name="XH" />" />
						</td>
						<th align="right">
							���
						</th>
						<td align="left">
							<bean:write name="ND" />
							<input type="hidden" name="nd" id="nd"
								value="<bean:write name="ND" />" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							����
						</th>
						<td align="left">
							<bean:write name="XM" />
						</td>
						<th align="right">
							ѧ��
						</th>
						<td align="left">
							<bean:write name="XN" />
							<input type="hidden" name="xn" id="xn"
								value="<bean:write name="XN" />" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							�Ա�
						</th>
						<td align="left">
							<bean:write name="XB" />
						</td>
						<th align="right">
							��ѧ��
						</th>
						<td align="left">
							<bean:write name="JXJMC" />
						</td>
					</tr>
					<logic:present name="showhzy">
						<tr style="height:22px">
							<th align="right">
								�꼶
							</th>
							<td align="left">
								<bean:write name="NJ" />
							</td>
							<th align="right">
								ѧ��
							</th>
							<td align="left">
								<bean:write name="xq" />
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								<bean:write name="XYMC" />
							</td>
							<th align="right">
								רҵ
							</th>
							<td align="left">
								<bean:write name="ZYMC" />
							</td>
						</tr>
					</logic:present>
					<logic:notPresent name="isgdby">
						<logic:notPresent name="showhzy">
							<!-- ���ս�����ҵ -->
							<logic:equal value="10878" name="xxdm">
								<tr style="height:22px">
									<th align="right">
										�꼶
									</th>
									<td align="left">
										<bean:write name="NJ" />
									</td>
									<th align="right">
										<bean:message key="lable.xsgzyxpzxy" />
									</th>
									<td align="left">
										<bean:write name="XYMC" />
									</td>
								</tr>
							</logic:equal>
							<!-- �ǰ��ս�����ҵ -->
							<logic:notEqual value="10878" name="xxdm">
								<tr style="height:22px">
									<th align="right">
										�꼶
									</th>
									<td align="left">
										<bean:write name="NJ" />
									</td>
									<logic:notEqual value="10355" name="xxdm">
										<logic:notEqual value="11647" name="xxdm">
											<logic:notEqual value="13742" name="xxdm">
												<th align="right">
													<logic:equal value="12682" name="xxdm">
									��Ϊ�淶
									</logic:equal>
													<logic:notEqual value="12682" name="xxdm">
										�³ɼ�
									</logic:notEqual>

												</th>
												<td align="left">
													<bean:write name='DCJ' />
												</td>
											</logic:notEqual>
										</logic:notEqual>
									</logic:notEqual>
									<logic:equal value="10355" name="xxdm">
										<td></td>
										<td></td>
									</logic:equal>
									<logic:equal value="11647" name="xxdm">
										<td></td>
										<td></td>
									</logic:equal>
									<logic:equal value="13742" name="xxdm">
										<th align="right">
											�������˵ȼ�:
										</th>
										<td align="left">
											${dykhdj }
										</td>
									</logic:equal>
								</tr>
								<tr style="height:22px">
									<th align="right">
										<bean:message key="lable.xsgzyxpzxy" />
									</th>
									<td align="left">
										<bean:write name="XYMC" />
									</td>
									<logic:notEqual value="13742" name="xxdm">
										<logic:notEqual value="10355" name="xxdm">
											<logic:notEqual value="11647" name="xxdm">
												<th align="right">
													<logic:equal value="12682" name="xxdm">
									�Ļ��ɼ�
									</logic:equal>
													<logic:notEqual value="12682" name="xxdm">
										�ǳɼ�
									</logic:notEqual>
												</th>
												<td align="left">
													<bean:write name="ZCJ" />
												</td>
											</logic:notEqual>
										</logic:notEqual>
									</logic:notEqual>
									<logic:equal value="10355" name="xxdm">
										<td></td>
										<td></td>
									</logic:equal>
									<logic:equal value="11647" name="xxdm">
										<td></td>
										<td></td>
									</logic:equal>
									<logic:equal value="13742" name="xxdm">
										<th align="right">
											ѧ�����ʽ�����׼:
										</th>
										<td align="left">
											${xstzjkbz }
										</td>
									</logic:equal>
								</tr>
								<tr style="height:22px">
									<th align="right">
										רҵ
									</th>
									<td align="left">
										<bean:write name="ZYMC" />
									</td>
									<logic:notEqual value="13742" name="xxdm">
										<logic:notEqual value="10355" name="xxdm">
											<logic:notEqual value="11647" name="xxdm">
												<th align="right">
													�����ɼ�
												</th>
												<td align="left">
													<bean:write name="TCJ" />
												</td>
											</logic:notEqual>
										</logic:notEqual>
									</logic:notEqual>
									<logic:equal value="11647" name="xxdm">
										<td></td>
										<td></td>
									</logic:equal>
									<logic:equal value="10355" name="xxdm">
										<td></td>
										<td></td>
									</logic:equal>
									<logic:equal value="13742" name="xxdm">
										<th align="right">
											�༶
										</th>
										<td align="left">
											<bean:write name="BJMC" />
										</td>
									</logic:equal>
								</tr>
							</logic:notEqual>
						</logic:notPresent>
						<logic:present name="shownblg">
							<tr style="height:22px">
								<th align="right">
									Ӣ���ļ�
								</th>
								<td align="left">
									${cet4 }
								</td>
								<th align="right">
									Ӣ������
								</th>
								<td align="left">
									${cet6 }
								</td>
							</tr>
							<tr style="height:22px">
								<th align="right">
									��Ȩ��
								</th>
								<td align="left">
									<bean:write name="jqf" />
								</td>
								<th align="right">
									�ۺ������ܷ�
								</th>
								<td align="left">
									<bean:write name="zhszcpzf" />
								</td>
							</tr>
							<tr style="height:22px">
								<th align="right">
									��Ȩ��רҵ����
								</th>
								<td align="left">
									${jqfpm }
								</td>
								<th align="right">
									�ۺ����ʰ༶����
								</th>
								<td align="left">
									${zhszcpzfpm }
								</td>
							</tr>
						</logic:present>
						<logic:notPresent name="shownblg">
							<logic:notEqual value="10878" name="xxdm">
								<logic:notEqual value="11417" name="xxdm">

									<logic:notEqual value="10355" name="xxdm">

										<logic:equal value="11647" name="xxdm">
											<tr style="height:22px">
												<td align="right">
													�ۺ����ʷ�
												</td>
										</logic:equal>
										<logic:notEqual value="11647" name="xxdm">
											<logic:equal value="12682" name="xxdm">
												<tr style="height:22px">
													<td align="right">
														�ܳɼ�
													</td>
											</logic:equal>
											<logic:notEqual value="12682" name="xxdm">
												<%--							�ɼ�����--%>
											</logic:notEqual>
										</logic:notEqual>


										<logic:equal value="12872" name="xxdm">
											<td align="left">
												<html:text property="cjmc" styleId="cjmc" value="${cjmc}"></html:text>
											</td>
										</logic:equal>
										<logic:notEqual value="12872" name="xxdm">
											<logic:equal value="11647" name="xxdm">
												<td align="left">
													${cj }
												</td>
											</logic:equal>
											<logic:notEqual value="11647" name="xxdm">

												<logic:equal value="12682" name="xxdm">
													<td align="left">
														${zpf }
													</td>
												</logic:equal>
												<logic:notEqual value="12682" name="xxdm">
										${cjmc }
										</logic:notEqual>
											</logic:notEqual>

										</logic:notEqual>


										<logic:notPresent name="showcsmzxy">
											<!-- quers -->

										</logic:notPresent>
										<logic:present name="showcsmzxy">
											<td align="right">
												������չѧ������
											</td>
											</tr>
										</logic:present>


										<logic:notPresent name="showcsmzxy">
											<logic:present name="showhzy">
												<td align="left">
													<html:text property="zhpfmc" styleId="zhpfmc"
														value="${zhpfmc}"></html:text>
												</td>
												</tr>
											</logic:present>
											<logic:notPresent name="showhzy">
												<td align="left">
													${zhszcpcjpm}
												</td>
												</tr>
											</logic:notPresent>
										</logic:notPresent>
										<logic:present name="showcsmcxy">
											<td align="left">
												${zhpfmc}
											</td>
											</tr>
										</logic:present>

									</logic:notEqual>

								</logic:notEqual>
							</logic:notEqual>
						</logic:notPresent>
					</logic:notPresent>

					<logic:equal value="yes" name="isgdby">

						<tr style="height:22px">
							<th align="right">
								�꼶
							</th>
							<td align="left">
								<bean:write name="NJ" />
							</td>
							<td align="right">
								&nbsp;
							</td>
							<td align="left">
								&nbsp;
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								<bean:write name="XYMC" />
							</td>
							<td align="right">
								&nbsp;
							</td>
							<td align="left">
								&nbsp;
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								רҵ
							</th>
							<td align="left">
								<bean:write name="ZYMC" />
							</td>
							<th align="right">
								���гɼ�
							</th>
							<td align="left">
								<bean:write name="CXCJ" />
							</td>
						</tr>

					</logic:equal>

					<tr style="height:22px">
						<logic:notEqual value="13742" name="xxdm">
							<th align="right">
								�༶
							</th>
							<td align="left">
								<bean:write name="BJMC" />
							</td>
						</logic:notEqual>
						<logic:equal value="12872" name="xxdm">
							<logic:equal value="true" name="isFdy">
								<td align="right">
									<input type="hidden" name="yesNo" value="${yesNo}" />
								</td>
								<td align="left">

								</td>
							</logic:equal>
							<logic:notEqual value="true" name="isFdy">
								<th align="right">
									���
								</th>
								<td align="left">
									<html:select property="yesNo" styleId="yesNo">
										<html:options collection="chkList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
							</logic:notEqual>
						</logic:equal>
						<logic:notEqual value="12872" name="xxdm">
							<th align="right">
								���
							</th>
							<td align="left">
								<html:select property="yesNo" styleId="yesNo">
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</logic:notEqual>
						<!-- ������Ϣְҵ����ѧԺ -->
						<logic:notEqual value="13108" name="xxdm">
							
						</logic:notEqual>
					</tr>
					<tr style="height:22px">
					<logic:notEqual value="13742" name="xxdm">
							<th align="right">
								����ְ��
							</th>
							<td align="left" colspan="">
								<bean:write name="DRZW" />
							</td>
							<td></td>
							<td></td>
					</logic:notEqual>
					<logic:present name="isjgs">
						<th align="right">
							�Ƿ�ʦ��
						</th>
						<td align="left">
							<bean:write name="sfsf" />
						</td>
					</logic:present>
					<logic:notPresent name="isjgs">
						<logic:notPresent name="showcsmzxy">
							<logic:present name="showhzy">
								<th align="right">
									ƽ���ɼ�:
								</th>
								<td align="left">
									${pjcj }
								</td>
							</logic:present>
						</logic:notPresent>
					</logic:notPresent>
					</tr>
					<logic:equal value="10878" name="xxdm">
						<logic:equal value="true" name="isFdy">
							<tr style="height:22px">
								<th align="right">
									��Ŀ����
								</th>
								<td align="left" colspan="3">
									<html:text property="szmc1" styleId="szmc1" style="width:90px"
										value="${szmc1}"></html:text>

									<html:text property="szmc2" styleId="szmc2" style="width:90px"
										value="${szmc2}"></html:text>

									<html:text property="szmc3" styleId="szmc3" style="width:90px"
										value="${szmc3}"></html:text>
									<html:text property="szmc4" styleId="szmc4" style="width:90px"
										value="${szmc4}"></html:text>
									<html:text property="szmc5" styleId="szmc5" style="width:90px"
										value="${szmc5}"></html:text>
								</td>
							</tr>
							<tr style="height:22px">
								<th align="right">
									��Ŀ�ɼ�
								</th>
								<td align="left" colspan="3">
									<html:text property="szcj1" styleId="szcj1" style="width:90px"
										onblur="ckinpdata(this)" value="${szcj1}"></html:text>

									<html:text property="szcj2" styleId="szcj2" style="width:90px"
										onblur="ckinpdata(this)" value="${szcj2}"></html:text>

									<html:text property="szcj3" styleId="szcj3" style="width:90px"
										onblur="ckinpdata(this)" value="${szcj3}"></html:text>
									<html:text property="szcj4" styleId="szcj4" style="width:90px"
										onblur="ckinpdata(this)" value="${szcj4}"></html:text>
									<html:text property="szcj5" styleId="szcj5" style="width:90px"
										onblur="ckinpdata(this)" value="${szcj5}"></html:text>
								</td>
							</tr>
							<tr style="height:22px">
								<th align="right">
									�ۺϳɼ�
								</th>
								<td align="left" colspan="">
									<html:text property="zhszcpcj" style="width:90px"
										styleId="zhszcpcj" onblur="ckinpdata(this)"
										value="${zhszcpcj}"></html:text>
								</td>
								<th align="right">
									�ۺ�����
								</th>
								<td align="left" colspan="">
									<html:text property="zhszcpcjpm" style="width:90px"
										styleId="zhszcpcjpm" onblur="ckinpdata(this)"
										value="${zhszcpcjpm}"></html:text>
								</td>
							</tr>
						</logic:equal>
						<logic:notEqual value="true" name="isFdy">
							<tr style="height:22px">
								<th align="right">
									��Ŀ����
								</th>
								<td align="left" colspan="3">
									<html:text property="szmc1" styleId="szmc1" style="width:90px"
										value="${szmc1}" readonly="true"></html:text>
									<html:text property="szmc2" styleId="szmc2" style="width:90px"
										value="${szmc2}" readonly="true"></html:text>
									<html:text property="szmc3" styleId="szmc3" style="width:90px"
										value="${szmc3}" readonly="true"></html:text>
									<html:text property="szmc4" styleId="szmc4" style="width:90px"
										value="${szmc4}" readonly="true"></html:text>
									<html:text property="szmc5" styleId="szmc5" style="width:90px"
										value="${szmc5}" readonly="true"></html:text>
								</td>
							</tr>
							<tr style="height:22px">
								<th align="right">
									��Ŀ�ɼ�
								</th>
								<td align="left" colspan="3">
									<html:text property="szcj1" styleId="szcj1" style="width:90px"
										value="${szcj1}" readonly="true"></html:text>
									<html:text property="szcj2" styleId="szcj2" style="width:90px"
										value="${szcj2}" readonly="true"></html:text>
									<html:text property="szcj3" styleId="szcj3" style="width:90px"
										value="${szcj3}" readonly="true"></html:text>
									<html:text property="szcj4" styleId="szcj4" style="width:90px"
										value="${szcj4}" readonly="true"></html:text>
									<html:text property="szcj5" styleId="szcj5" style="width:90px"
										value="${szcj5}" readonly="true"></html:text>
								</td>
							</tr>
							<tr style="height:22px">
								<th align="right">
									�ۺϳɼ�
								</th>
								<td align="left" colspan="">
									<html:text property="zhszcpcj" style="width:90px"
										styleId="zhszcpcj" value="${zhszcpcj}" readonly="true"></html:text>
								</td>
								<th align="right">
									�ۺ�����
								</th>
								<td align="left" colspan="">
									<html:text property="zhszcpcjpm" style="width:90px"
										styleId="zhszcpcjpm" value="${zhszcpcjpm}" readonly="true"></html:text>
								</td>
							</tr>
						</logic:notEqual>
					</logic:equal>
					<tr style="height:22px">
						<logic:present name="showhzy">
							<td align="right">
								�������
							</td>
							<td align="left" colspan="7">
								<textarea name='jfqk' style="width:99%" rows='3' type="_moz"><bean:write name="jfqk" /></textarea>
							</td>
						</logic:present>
						<logic:notPresent name="showhzy">
							<logic:notEqual value="13742" name="xxdm">
								<th align="right">
									������Ŀ
								</th>
								<td colspan="3">
									<textarea name='kyxm' style="width:97%" rows='5' type="_moz"readonly="readonly"><bean:write name="KYCG" /></textarea>
								</td>
							</logic:notEqual>
						</logic:notPresent>
					</tr>
					<logic:present name="isjgs">
						<tr>
							<th align="right">
								��ѧ�����
							</th>
							<td align="left" colspan="3">
								<textarea rows="3" cols="4" style="width: 90%" name="hjxjqk" type="_moz"><bean:write name="hjxjqk" /></textarea>
							</td>
						</tr>
					</logic:present>
					<tr style="height:22px">
						<logic:present name="showhzy">
							<th align="right">
								��������
							</th>
							<td align="left" colspan="3">
								<textarea name="sqly" style="width:100%" rows="3" type="_moz"><bean:write name="SQLY" /></textarea>
							</td>
						</logic:present>
						<logic:notPresent name="showhzy">
							<th align="right">
								��������
							</th>
							<td align="left" colspan="3">
								<bean:write name="SQLY" />
							</td>
						</logic:notPresent>
					</tr>
					<logic:equal value="10355" name="xxdm">
						<tr style="height:22px">
							<th align="right">
								��ע
								<font color="red">(�ܴ���,����,<br />�������,<br />ѧϰ�ɼ����)</font>
								<br />
							</th>
							<td colspan="7">
								<html:textarea property='bz' style="width:99%" rows='5'
									readonly="true"></html:textarea>
							</td>
						</tr>
					</logic:equal>
					<logic:equal value="xy" scope="request" name="userType">
						<logic:present name="showhzy">
							<tr>
								<th align="right">
									���������
								</th>
								<td align="left" colspan="3">
									<textarea name="fdyyj" style="width:100%" rows="3" type="_moz"><logic:equal value=" " name="FDYYJ">ͬ���Ƽ�</logic:equal><logic:notEqual value=" " name="FDYYJ"><bean:write name="FDYYJ" /></logic:notEqual></textarea>
								</td>
							</tr>


							<logic:equal value="no" name="writeAble">
								<tr>
									<th align="right">
										ϵ(Ժ)���
									</th>
									<td align="left" colspan="3">
										<textarea name="xyyj" style="width:100%" rows="3" type="_moz"><logic:equal value=" " name="XYSHYJ">ͬ���Ƽ�</logic:equal><logic:notEqual value=" " name="XYSHYJ"><bean:write name="XYSHYJ" /></logic:notEqual></textarea>
									</td>
								</tr>
							</logic:equal>
							<logic:equal value="yes" name="writeAble">
								<tr>
									<td align="right">
										ϵ(Ժ)���
									</td>
									<td align="left" colspan="3">
										<textarea name="xyyj" style="width:100%" rows="3" type="_moz"><logic:equal value=" " name="XYSHYJ">ͬ���Ƽ�</logic:equal><logic:notEqual value=" " name="XYSHYJ"><bean:write name="XYSHYJ" /></logic:notEqual></textarea>
									</td>
								</tr>
							</logic:equal>
							<tr>
								<th align="right">
									������ǩ��
								</th>
								<td align="left">
									<input type="text" name="fdyqm" id="fdyqm" value="${fdyqm}">
								</td>
								<logic:equal value="true" name="isFdy">
									<td align="right">

									</td>
									<td align="left">
										<input type="hidden" name="xyqm" id="xyqm" value="${xyqm}">
									</td>
								</logic:equal>
								<logic:notEqual value="true" name="isFdy">
									<th align="right">
										ϵ(Ժ)ǩ��
									</th>
									<td align="left">
										<input type="text" name="xyqm" id="xyqm" value="${xyqm}">
									</td>
								</logic:notEqual>
								<logic:equal value="true" name="idFdy">
									<td align="right">
										&nbsp;
									</td>
									<td align="left">
										&nbsp;
									</td>
								</logic:equal>
							</tr>
						</logic:present>
						<logic:notPresent name="showhzy">
							<logic:present name="iscsmz">
								<logic:equal value="fdy" name="iscsmz">
									<tr>
										<th align="right">
											����Ա���
										</th>
										<td align="left" colspan="3">
											<textarea name="fdyyj" style="width:100%" rows="3" type="_moz"><bean:write name="FDYYJ" /></textarea>
										</td>
									</tr>
								</logic:equal>
								<logic:equal value="xy" name="iscsmz">
									<tr>
										<th align="right">
											<bean:message key="lable.xsgzyxpzxy" />����ίԱ�����
										</th>
										<td align="left" colspan="3">
											<textarea name="wyhyj" style="width:100%" rows="3" type="_moz"><bean:write name="XYPSWYHYJ" /></textarea>
										</td>
									</tr>
									<tr style="height:22px">
										<th align="right">
											<bean:message key="lable.xsgzyxpzxy" />������
										</th>
										<td align="left" colspan="3">
											<textarea name="xyyj" style="width:100%" rows="3" type="_moz"><bean:write name="XYSHYJ" /></textarea>
										</td>
									</tr>
								</logic:equal>
							</logic:present>
							<logic:notPresent name="iscsmz">
								<tr>
									<th align="right">
										<logic:equal value="13742" name="xxdm">
						�༶����
						</logic:equal>
										<logic:notEqual value="13742" name="xxdm">
						                                          ����Ա���
						              </logic:notEqual>
									</th>
									<td align="left" colspan="3">
										<textarea name="fdyyj" style="width:100%" rows="3" type="_moz"><bean:write name="FDYYJ" /></textarea>
									</td>
								</tr>
								<logic:equal value="false" name="isFdy">
									<tr>
										<th align="right">
											<bean:message key="lable.xsgzyxpzxy" />����ίԱ�����
										</th>
										<td align="left" colspan="3">
											<textarea name="wyhyj" style="width:100%" rows="3" type="_moz"><bean:write name="XYPSWYHYJ" /></textarea>
										</td>
									</tr>
									<tr style="height:22px">
										<th align="right">
											<bean:message key="lable.xsgzyxpzxy" />������
										</th>
										<td align="left" colspan="3">
											<textarea name="xyyj" style="width:100%" rows="3" type="_moz" id="xyyj6"><bean:write name="XYSHYJ" /></textarea>
										</td>
									</tr>
								</logic:equal>
							</logic:notPresent>
						</logic:notPresent>
					</logic:equal>
					<logic:equal value="xx" scope="request" name="userType">

						<logic:present name="showhzy">
							<tr>
								<th align="right">
									���������
								</th>
								<td align="left" colspan="3"><bean:write name="FDYYJ" />
								</td>
							</tr>
							<tr>
								<th align="right">
									ϵ(Ժ)���
								</th>
								<td align="left" colspan="3"><bean:write name="XYSHYJ" /></td>
							</tr>
							<tr>
								<th align="right">
									<bean:message key="lable.xsgzyxpzxy" />���
								</th>
								<td align="left" colspan="3">
									<textarea name="xxyj" style="width:100%" rows="3" type="_moz">
										<logic:equal value=" " name="XXSHYJ">ͬ��</logic:equal>
										<logic:notEqual value=" " name="XXSHYJ">
											<bean:write name="XXSHYJ" />
										</logic:notEqual>
									</textarea>
								</td>
							</tr>
						</logic:present>
						<logic:notPresent name="showhzy">
							<tr>
								<th align="right">
									����Ա���
								</th>
								<td align="left" colspan="3"><bean:write name="FDYYJ" /></td>
							</tr>
							<logic:notEqual value="13742" name="xxdm">
								<tr>
									<th align="right">
										<bean:message key="lable.xsgzyxpzxy" />����ίԱ�����
									</th>
									<td align="left" colspan="3"><bean:write name="XYPSWYHYJ" /></td>
								</tr>
							</logic:notEqual>
							<tr>
								<th align="right">
									<logic:equal value="13742" name="xxdm"><bean:message key="lable.xsgzyxpzxy" />�������</logic:equal>
									<logic:notEqual value="13742" name="xxdm"><bean:message key="lable.xsgzyxpzxy" />������</logic:notEqual>
								</th>
								<td align="left" colspan="3">
									<bean:write name="XYSHYJ" />
								</td>
							</tr>
							<tr>
								<th align="right">
									<logic:equal value="13742" name="xxdm"><bean:message key="lable.xsgzyxpzxy" />������</logic:equal>
									<logic:notEqual value="13742" name="xxdm">ѧУ������</logic:notEqual>
								</th>
								<td align="left" colspan="3">
									<textarea name="xxyj" style="width:97%" rows="3" type="_moz"><bean:write name="XXSHYJ" /></textarea>
								</td>
							</tr>
						</logic:notPresent>
					</logic:equal>
					<tfoot>
						<tr>
							<td colspan="4">
								 <div class="btn">
				<button type="button" class="" onclick="ckflag()" 
					id="buttonSave">
					�� ��
				</button>
				<logic:present name="showhzy">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class=""
						onclick="showTopWin('/xgxt/stu_info_details.do?xh=' + document.getElementById('selxh').innerText, 800, 600)" id="buttonQuery">
						�鿴ѧ����Ϣ
					</button>
				</logic:present>
				<logic:equal value="13108" name="xxdm">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class=""
						onclick="showTopWin('/xgxt/stu_cj_details.do?xh=' + document.getElementById('selxh').innerText, 800, 600)" id="buttonQuery">
						�鿴ѧ���ɼ�
					</button>
				</logic:equal>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="" onclick="Close();return false;" 
					id="buttonClose">
					�� ��
				</button>
				<%--<logic:present name="showhzy">
				&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="window.open('/xgxt/dxjxjsp.do?method=dxjxjsp&pk=<bean:write name="xn||nd||xh||jxjdm"/>')" style="width:90px"
					id="buttonQuery">
					��ӡ����
				</button>
				</logic:present>
			--%>
			</div>
							</td>
						</tr>
					</tfoot>
				</table>
				</div>
			</logic:notPresent>
			
		</html:form>
		<logic:present name="result">
			<logic:equal value="view" name="result">
				<script>
				alert("�����ɹ�!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
			</logic:equal>
			<logic:equal value="no" name="result">
				<script>
					alert("����ʧ�ܣ�"+document.getElementById('res').value);
					Close();
			</script>
			</logic:equal>
		</logic:present>
		<script type="text/javascript">
		function ckflag() {
				var xxdm;
				var uType = document.getElementById('userType').value;
				var isFdy = document.getElementById('isFdy').value;
				var xsh = document.getElementById('xsh').value;
				var ysh = document.getElementById('ysh').value;
				if ($('xxdm')) {
					xxdm = document.getElementById('xxdm').value;
				}
				if (xxdm=='10827') {
					if (uType=='xy') {//fdy
						if (isFdy=='true') {
							if (xsh=='ͨ��' || ysh=='ͨ��') {
								alert('����ز����������ͨ��,�������޸�!');
								return;
							} else {
								BatAlert.showTips('���ڲ�������ȴ�...');
								refreshForm('/xgxt/priseChkOne.do?act=save');
								return;
							}
						} else {//xy
							if (xsh=='ͨ��') {
								alert('����ز����������ͨ��,�������޸�!');
								return;
							} else {
								refreshForm('/xgxt/priseChkOne.do?act=save');
								BatAlert.showTips('���ڲ�������ȴ�...');
								return;
							}
						}
					} else {
						refreshForm('/xgxt/priseChkOne.do?act=save');
						BatAlert.showTips('���ڲ�������ȴ�...');
						return;
					}
				} else {
					refreshForm('/xgxt/priseChkOne.do?act=save');
					BatAlert.showTips('���ڲ�������ȴ�...');
					return;
				}
			}
	</script>
	</body>
</html>
