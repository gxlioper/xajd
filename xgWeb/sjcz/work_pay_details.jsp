<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript">
	function commit(){
		var xxdm = document.getElementById('xxdm').value;
		var jfglkg = document.getElementById('jfglkg').value;
		
		if(jfglkg == "1"){//���ѹ����ؿ�
			if(parseFloat(document.forms[0].syjf.value)-parseFloat(document.getElementById('zje').innerText)<0){
				alert('���Ѳ��㣬����ʧ�ܣ�');
				Close();
				return false;
			}
		}
		refreshForm('workPaySave.do');		
	}
	
	function defaultValue(){
		var count = val('count');
		for(var i=0; i<parseInt(count); i++){
			setVal('gzsj'+i,val('khgzsj'+i));
			setVal('cjje'+i,val('khcjje'+i));
		}
		changezje();
	}
	</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body onload="checkWinType();changezje()">
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<%--��ɳ����--%>
					<logic:equal value="10827" name="xxdm">
						<span id="tipFollow"> ѧ���幤 - ��𷢷� - ��𷢷� - ��𷢷��굥</span>
					</logic:equal>
					<logic:notEqual value="10827" name="xxdm">
						<span id="tipFollow"> �ڹ���ѧ - ��𷢷� - ��𷢷� - ��𷢷��굥</span>
					</logic:notEqual>
				</div>
			</div>
			<input type="hidden" id="mes" name="mes" value="${mes}" />
			<logic:notEmpty name="workInfo">
				<input type="hidden" id="doType" name="doType" value="" />
				<input type="hidden" id="pkValue" name="pkValue" value="" />
				<input type="hidden" id="disableEle" name="disableEle" value="" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo" value="" />
				<input type="hidden" id="url" name="url" value="/sjcz/xspxxxb.jsp" />
				<input type="hidden" id="gwdm" name="gwdm" value="<bean:write name="workInfo" property="gwdm"/>" />
				<input type="hidden" id="gwsbsj" name="gwsbsj" value="<bean:write name="workInfo" property="gwsbsj"/>" />
				<input type="hidden" id="jybcbz" name="jybcbz" value="<bean:write name="workInfo" property="jybcbz"/>" />
				<input type="hidden" id="count" name="count" value="<bean:write name="count"/>" />
				<input type="hidden" id="xn" name="xn" value="<bean:write name="workInfo" property="xn"/>" />
				<input type="hidden" id="nd" name="nd" value="<bean:write name="workInfo" property="nd"/>" />
				<input type="hidden" id="xq" name="xq" value="<bean:write name="workInfo" property="xq"/>" />
				<input type="hidden" id="yf" name="yf" value="<bean:write name="workInfo" property="yf"/>" />
				<input type="hidden" id="gwxz" name="gwxz" value="<bean:write name="workInfo" property="gwxz"/>" />
				<input type="hidden" id="syjf" name="syjf" value="<bean:write name="workInfo" property="syjf"/>" />
				<input type="hidden" id="jfglkg" name="jfglkg" value="<bean:write name="workInfo" property="jfglkg"/>" />
				<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm"/>" />
				<fieldset>
					<legend>
						<logic:notPresent name="showbjlh">
						<%--�㽭����ְҵ����<bean:message key="lable.xsgzyxpzxy" />--%>
						<logic:equal value="12861" name="xxdm">
						���:${cjffY}&nbsp;&nbsp;&nbsp;�·�:${cjffM}
						</logic:equal>
						<%--end�㽭����ְҵ����<bean:message key="lable.xsgzyxpzxy" />--%>		
						
						<%--���㽭����ְҵ����<bean:message key="lable.xsgzyxpzxy" />--%>
						<logic:notEqual value="12861" name="xxdm">
						ѧ��:<bean:write name="workInfo" property="xn" />&nbsp;&nbsp;&nbsp;���:<bean:write
								name="workInfo" property="nd" />&nbsp;&nbsp;&nbsp;ѧ��:<bean:write
								name="workInfo" property="xq" />&nbsp;&nbsp;&nbsp;�·�:<bean:write
								name="workInfo" property="yf" />
						</logic:notEqual>
						<%--end���㽭����ְҵ����<bean:message key="lable.xsgzyxpzxy" />--%>
						
						</logic:notPresent>
						<logic:present name="showbjlh">	
						���:<bean:write name="workInfo" property="xn" />&nbsp;&nbsp;&nbsp;ѧ��:<bean:write
								name="workInfo" property="xq" />&nbsp;&nbsp;&nbsp;�·�:<bean:write
								name="workInfo" property="yf" />
						</logic:present>
					</legend>
					<table width="100%" class="tbstyle" id="rsT">
						<tr>
							<td width="15%" height="27" align="center">
								���˵�λ
							</td>
							<td width="85%" align="center">
								<bean:write name="workInfo" property="yrdwmc" />
							</td>
						</tr>
						<tr>
							<td width="15%" height="27" align="center">
								��������
							</td>
							<td width="85%" align="center">
								<bean:write name="workInfo" property="xymc" />
							</td>
						</tr>
						<tr>
							<td height="27" align="center">
								��������
							</td>
							<td align="center">
								<bean:write name="workInfo" property="gznr" />
							</td>
						</tr>
						<tr>
							<td height="27" align="center">
								��λ����
							</td>
							<td align="center">
								<bean:write name="workInfo" property="gwxzmc" />
							</td>
						</tr>
						<tr>
							<td height="27" align="center">
								�Ƴ��׼
							</td>
							<td align="center">
								<bean:write name="workInfo" property="jybcbz" />
								<input type="hidden" id="jybcbz"
									value="<bean:write name="workInfo" property="jybcbz" />" />
								<bean:write name="workInfo" property="jcfs" />
							</td>
						</tr>
						<!--���ѹ����ؿ�-->
						<logic:equal value="1" name="workInfo" property="jfglkg">
							<tr>
								<td height="27" align="center">
									ʣ�ྭ��
								</td>
								<td align="center">
									<bean:write name="workInfo" property="syjf" />
									����λ:Ԫ��
								</td>
							</tr>
						</logic:equal>
						<!--end���ѹ����ؿ�-->
						
						<%--�人����ѧ--%>
						<logic:equal value="10497" name="xxdm">
							<tr>
								<td height="27" align="center">
									���ű����׼
								</td>
								<td align="center">
									<html:text name="workInfo" property="jybcbz" styleId="ffbcbz"
										onblur="if(parseInt(this.value)>parseInt(document.getElementById('jybcbz').value)) alert('���ű����׼����������׼��')" />
								</td>
							</tr>
						</logic:equal>
						<tr>
							<td align="center">
								��
								<br />
								��
								<br />
								��
								<br />
								Ա
								<br />
								��
								<br />
								��
							</td>
							<td valign="top" align="center">
								<table width="100%" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center">
													ѧ��
												</div>
											</td>
											<td >
												<div align="center">
													����
												</div>
											</td>
											<td width="16%">
												<div align="center">
													�༶
												</div>
											</td>
											<!--���ݴ�ѧ-->
											<logic:equal value="11078" name="xxdm">
												<td>
													<div align="center">
														<bean:message key="lable.xsgzyxpzxy" />�ϱ�����ʱ��
													</div>
												</td>
												<td>
													<div align="center">
														<bean:message key="lable.xsgzyxpzxy" />�ϱ������
													</div>
												</td>
											</logic:equal>
											<td>
												<div align="center">
													����ʱ��(��λ:
													<bean:write name="workInfo" property="gzsjdw" />
													)
												</div>
											</td>
											<td width="8%">
												<div align="center">
													���
												</div>
											</td>
											<!--���ݴ�ѧ-->
											<logic:equal value="11078" name="xxdm">
												<td width="16%">
												<div align="center">
													��������
												</div>
												</td>
											</logic:equal>
											<!--end���ݴ�ѧ-->
											<logic:present name="showjsxx">
												<td width="10%">
													<div align="center">
														ǩ��
													</div>
												</td>
											</logic:present>
											<!--�㽭��ý<bean:message key="lable.xsgzyxpzxy" />-->
											<!--�㽭����-->
											<logic:notEmpty name="needYh">
											<td>
												<div align="center">
													���п���
												</div>
											</td>
											<td>
												<div align="center">
													��������
												</div>
											</td>	
											</logic:notEmpty>
											<!--end�㽭����-->
											<!--end�㽭��ý<bean:message key="lable.xsgzyxpzxy" />-->
											<td width="10%">
												<div align="center">
													��ע
												</div>
											</td>
										</tr>
									</thead>
									<logic:empty name="ffList">
										<tr>
											<td colspan="6">
												�޲μӸø�λ��ѧ����¼!
											</td>
										</tr>
									</logic:empty>
									<logic:notEmpty name="ffList">
										<logic:iterate name="ffList" id="ffList" indexId="index">
											<tr>
												<td>
													<div align="center">
														<input type="text" name="xh${index}"
															value="<bean:write name="ffList" property="xh"/>"
															style="width:95%" readonly/>
													</div>
												</td>
												<td>
													<div align="center">
														<bean:write name="ffList" property="xm" />
													</div>
												</td>
												<td>
													<div align="center">
														<bean:write name="ffList" property="bjmc" />
													</div>
												</td>
												<!--���ݴ�ѧ-->
												<logic:equal value="11078" name="xxdm">
													<td>
													${ffList.khgzsj}
													<input type="hidden" id="khgzsj${index}"
																		name="khgzsj${index}"
																		value="${ffList.khgzsj}"
                                                                        readonly="readonly"/>
													</td>
													<td>
													${ffList.khcjje}
													<input type="hidden" id="khcjje${index}"
																		name="khcjje${index}"
																		value="${ffList.khcjje}"
                                                                        readonly="readonly"/>
													</td>	
												</logic:equal>
												<!--���ݴ�ѧend-->
												<logic:present name="bjlh">
													<td>
														<div align="center">
															<input type="text" id="gzsj${index}" name="gzsj${index}"
																value="<bean:write name="ffList" property="gzsj"/>"
																style="width:95%"
																onblur="changecjje(this);document.all(this.id.replace('gzsj','cjje')).focus();"
																readonly />
														</div>
													</td>
												</logic:present>
												<logic:notPresent name="bjlh">
													<td>
														<div align="center">
															<%--�人����ѧ--%>
															<logic:equal value="10497" name="xxdm">
																<logic:equal value="ͨ��" name="ffList" property="xxsh">
																	<input type="text" id="gzsj${index}"
																		name="gzsj${index}"
																		value="<bean:write name="ffList" property="gzsj"/>"
																		style="width:95%" 
																		readonly="readonly"
																		onblur="changecjje(this);document.all(this.id.replace('gzsj','cjje')).focus();" />
																</logic:equal>
																
																<logic:notEqual value="ͨ��" name="ffList" property="xxsh">
																	<input type="text" id="gzsj${index}"
																		name="gzsj${index}"
																		value="<bean:write name="ffList" property="gzsj"/>"
																		style="width:95%"
																		maxlength="6"
																		onkeyup="value=value.replace(/[^\d|.]/g,'')"
																		onblur="changecjje(this);document.all(this.id.replace('gzsj','cjje')).focus();" />
																</logic:notEqual>
															</logic:equal>
															<%--�人����ѧend--%>
															<logic:notEqual value="10497" name="xxdm">
																<input type="text" id="gzsj${index}" name="gzsj${index}"
																	value="<bean:write name="ffList" property="gzsj"/>"
																	style="width:95%"
																	maxlength="6"
																	onblur="changecjje(this);document.all(this.id.replace('gzsj','cjje')).focus();" 
																	onkeyup="value=value.replace(/[^\d|.]/g,'')"/>																
															</logic:notEqual>
														</div>
													</td>
												</logic:notPresent>
												<logic:present name="bjlh">
													<td>
														<div align="center">
															<input type="text" id="cjje${index}" name="cjje${index}"
																value="<bean:write name="ffList" property="cjje"/>"
																style="width:95%"
																onblur="changezje();if(this.value>300&&document.forms[0].gwxz.value == '001') {alert('����,����д��Ӧ��ע!');document.all(this.id.replace('cjje','bz')).focus();}"
																readonly />
														</div>
													</td>
												</logic:present>
												<logic:notPresent name="bjlh">
													<%--�人����ѧ--%>
													<logic:equal value="10497" name="xxdm">
														<td>
															<div align="center">
																<input type="text" id="cjje${index}" name="cjje${index}"
																	value="<bean:write name="ffList" property="cjje"/>"
																	style="width:95%" 
																	readonly="readonly"
																	onblur="changezje();" />
															</div>
														</td>
													</logic:equal>
													<%--�人����ѧend--%>
													<logic:notEqual value="10497" name="xxdm">
														<td>
															<div align="center">
																<input type="text" id="cjje${index}" name="cjje${index}"
																	value="<bean:write name="ffList" property="cjje"/>"
																	style="width:95%"
																	maxlength="6"
																	onkeyup="value=value.replace(/[^\d|.]/g,'')"
																	onblur="changezje();" />
															</div>
														</td>
													</logic:notEqual>
												</logic:notPresent>
												<logic:present name="showjsxx">
													<td>
														<div align="center">
															&nbsp;
														</div>
													</td>
												</logic:present>
												<!--���ݴ�ѧ-->
												<logic:equal value="11078" name="xxdm">
													<td nowrap="nowrap">
														<input type="text" id="gzpj${index}" name="gzpj${index}"
																	value="<bean:write name="ffList" property="gzpj"/>"
																	style="width:95%"
																	maxlength="150"
																	onblur="" />
													</td>
												</logic:equal>
												<!--���ݴ�ѧ-->

												<!--�㽭��ý<bean:message key="lable.xsgzyxpzxy" />-->
												<!--�㽭���� edit by luojw 2010.10.21-->
												<logic:notEmpty name="needYh">
												<td>
													<logic:notEmpty name="ffList" property="kh">
													<input type="text" name="yhkh${index}"
																value="<bean:write name="ffList" property="kh"/>"
																maxlength="20"
																style="width:95%"
																readonly="readonly"
																onkeyup="value=value.replace(/[^\d]/g,'') "/>	
													</logic:notEmpty>		
													<logic:empty name="ffList" property="kh">
													<input type="text" name="yhkh${index}"
																value="<bean:write name="ffList" property="yhkh"/>"
																maxlength="20"
																style="width:95%"
																readonly="readonly"
																onkeyup="value=value.replace(/[^\d]/g,'') "/>	
													</logic:empty>
												</td>
												<td>
													<input type="text" name="yhmc${index}"
																value="<bean:write name="ffList" property="yhmc"/>"
																maxlength="15"
																readonly="readonly"
																style="width:95%"/>				
												</td>
												</logic:notEmpty>
												<!--end�㽭����-->
												<!--end�㽭��ý<bean:message key="lable.xsgzyxpzxy" />-->
												<td nowrap="nowrap">
													<div align="center">
														<%--�人����ѧ--%>
														<logic:equal value="10497" name="xxdm">
															<logic:equal value="ͨ��" name="ffList" property="xxsh">
																<input type="text" name="bz${index}"
																	value="<bean:write name="ffList" property="bz"/>"
																	style="width:95%" readonly="readonly" />
															</logic:equal>
															<logic:notEqual value="ͨ��" name="ffList" property="xxsh">
																<input type="text" name="bz${index}"
																	value="<bean:write name="ffList" property="bz"/>"
																	maxlength="60"
																	style="width:95%" />
															</logic:notEqual>
														</logic:equal>
														<%--���人����ѧ--%>
														<logic:notEqual value="10497" name="xxdm">
															<input type="text" name="bz${index}"
																value="<bean:write name="ffList" property="bz"/>"
																maxlength="60"
																style="width:95%" />
														</logic:notEqual>
													</div>
												</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
							</td>
						</tr>
						<tr>
							<td height="27">
								<div align="center">
									����ܼƣ���λ:Ԫ��
								</div>
							</td>
							<td>
								<div align="center" id="zje"></div>
							</td>
						</tr>
					</table>
				</fieldset>
				<div class="buttontool">
					<button type="button" class="button2" onclick="commit();" style="width:80px"
						id="buttonSave">
						�� ��
					</button>
					<!--���ݴ�ѧ-->
					<logic:equal value="11078" name="xxdm">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="defaultValue()" style="width:80px"
						id="buttonDefault">
						Ĭ��
					</button>
					</logic:equal>
					<!--end���ݴ�ѧ-->
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonSave">
						�ر�
					</button>
				</div>
			</logic:notEmpty>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('�����ɹ���');
					</script>
					<logic:present name="mes">
						<script>
							alert(document.getElementById('mes').value);
						</script>
					</logic:present>
					<script>
					Close();
				</script>
				</logic:equal>

				<logic:equal value="false" name="result">
					<logic:present name="mes">
						<script>
						alert(document.getElementById('mes').value);
						Close();
						</script>
					</logic:present>
					<logic:notPresent name="mes">
						<script>
							alert("����ʧ�ܣ�");
						</script>
					</logic:notPresent>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>

