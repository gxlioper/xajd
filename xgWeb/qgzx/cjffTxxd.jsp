<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
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
		if(confirm('�ò������ϱ����¿��˹���ʱ�䡢���������д����Ӧ�Ĺ���ʱ��ͽ���У�ȷ��������')){
			var count = val('count');
			for(var i=0; i<parseInt(count); i++){
				setVal('gzsj'+i,val('khgzsj'+i));
				setVal('cjje'+i,val('khcjje'+i));
			}
			changezje();
		}
	}
	</script>
</head>
	<body onload="checkWinType();changezje()">
		<html:form action="/data_search" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em>
					<a>
						<%--��ɳ����--%>
						<logic:equal value="10827" name="xxdm">
							<span id="tipFollow"> ѧ���幤 - ��𷢷� - ��𷢷� - ��𷢷��굥</span>
						</logic:equal>
						<logic:notEqual value="10827" name="xxdm">
							<span id="tipFollow"> �ڹ���ѧ - ��𷢷� - ��𷢷� - ��𷢷��굥</span>
						</logic:notEqual>
					</a>
				</p>
			</div>
			<input type="hidden" id="mes" name="mes" value="${mes}" />
			<logic:notEmpty name="workInfo">
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
				<div class="tab">
		  		<table width="100%" class="formlist" id="rsT">
					<thead>
						<!--�Ǳ�������-->
						<logic:notEqual value="11417" name="xxdm">
						<%--�㽭����ְҵ����ѧԺ--%>
						<logic:equal value="12861" name="xxdm">
							<tr>
								<th colspan="2">���:${cjffY}; �·�:${cjffM}</th>
							</tr>
						</logic:equal>
						<%--end�㽭����ְҵ����ѧԺ--%>		
						
						<%--���㽭����ְҵ����ѧԺ--%>
						<logic:notEqual value="12861" name="xxdm">
							<tr>
								<th colspan="2">ѧ��:${workInfo.xn};���:${workInfo.nd};ѧ��:${workInfo.xqmc};�·�:${workInfo.yf}</th>
							</tr>
						</logic:notEqual>
						<%--end���㽭����ְҵ����ѧԺ--%>						
						</logic:notEqual>
						<!--end�Ǳ�������-->

						<!--��������-->
						<logic:equal value="11417" name="xxdm">							
							<tr>
								<th colspan="2">���:${workInfo.nd};ѧ��:${workInfo.xq};�·�:${workInfo.yf}</th>
							</tr>
						</logic:equal>
						<!--end��������-->
						</thead>
						<tbody>
						<tr>
							<th>���˵�λ</th>
							<td>
								<bean:write name="workInfo" property="yrdwmc" />
							</td>
						</tr>
						<tr>
							<th>��������</th>
							<td>
								<bean:write name="workInfo" property="xymc" />
							</td>
						</tr>
						<tr>
							<th>��������</th>
							<td>
								<bean:write name="workInfo" property="gznr" />
							</td>
						</tr>
						<tr>
							<th>��λ����</th>
							<td>
								<bean:write name="workInfo" property="gwxzmc" />
							</td>
						</tr>
						<!-- �ǹ��ݴ�ѧ -->
						<logic:notEqual name="xxdm" value="11078">
						<tr>
							<th>�Ƴ��׼</th>
							<td>
								<bean:write name="workInfo" property="jybcbz" />
								<input type="hidden" id="jybcbz"
									value="<bean:write name="workInfo" property="jybcbz" />" />
								(<bean:write name="workInfo" property="jcfsmc" />)
							</td>
						</tr>
						</logic:notEqual>
						<!--���ѹ����ؿ�-->
						<logic:equal value="1" name="workInfo" property="jfglkg">
							<tr>
								<th>ʣ�ྭ��</th>
								<td>
									<bean:write name="workInfo" property="syjf" />
									����λ:Ԫ��
								</td>
							</tr>
						</logic:equal>
						<!--end���ѹ����ؿ�-->
						
						<%--�人����ѧ--%>
						<logic:equal value="10497" name="xxdm">
							<tr>
								<th>���ű����׼</th>
								<td>
									<html:text name="workInfo" property="jybcbz" styleId="ffbcbz"
										onblur="if(parseInt(this.value)>parseInt(document.getElementById('jybcbz').value)) alert('���ű����׼����������׼��')" />
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th>
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
							</th>
							<td>
								<table width="100%" style="height:100%" class="formlist">
									<thead>
										<tr>
											<th>ѧ��</th>
											<th>����</th>
											<th>�༶</th>
											<!--���ݴ�ѧ-->
											<logic:equal value="11078" name="xxdm">
												<th><bean:message key="lable.xsgzyxpzxy" />�ϱ�����ʱ��</th>
												<th><bean:message key="lable.xsgzyxpzxy" />�ϱ������</th>
											</logic:equal>		
											<!--�㽭�Ƽ�-->
											<logic:equal value="11057" name="xxdm">
												<th>�¿���ʱ��</th>
												<th>�¿��˳��</th>
											</logic:equal>	
											<!--end�㽭�Ƽ�-->									
											
											<th>����ʱ��(��λ:<bean:write name="workInfo" property="jcfsmc" />)</th>
											<th>���</th>

											<!--���ݴ�ѧ-->
											<logic:equal value="11078" name="xxdm">
												<th>��������</th>
											</logic:equal>
											<!--end���ݴ�ѧ-->

											<logic:present name="showjsxx">
												<th>ǩ��</td>
											</logic:present>

											<!--�㽭��ýѧԺ-->
											<logic:equal value="11647" name="xxdm">
											<th>���п���</th>
											<th>��������</th>	
											</logic:equal>
											<!--end�㽭��ýѧԺ-->

											<th>��ע</th>
										</tr>
									</thead>
									<logic:empty name="ffList">
										<tr>
											<!--�㽭�Ƽ�-->
											<logic:equal value="11057" name="xxdm">
												<th colspan="8">
													�޲μӸø�λ��ѧ����¼!
												</th>
											</logic:equal>	
											<!--end�㽭�Ƽ�-->
											<logic:notEqual value="11057" name="xxdm">
												<th colspan="6">
													�޲μӸø�λ��ѧ����¼!
												</th>
											</logic:notEqual>
										</tr>
									</logic:empty>
									<tbody>
									<logic:notEmpty name="ffList">
										<logic:iterate name="ffList" id="ffList" indexId="index">
											<tr>
												<td>
													<input type="text" name="xh${index}"
														value="<bean:write name="ffList" property="xh"/>"
														style="width:80px" readonly/>
												</td>
												<td>
													<bean:write name="ffList" property="xm" />
												</td>
												<td>
													<bean:write name="ffList" property="bjmc" />
												</td>
												<!--�㽭�Ƽ�-->
												<logic:equal value="11057" name="xxdm">
												<td>
													${ffList.ygzsj}
													<input type="hidden" id="khgzsj${index}"
																			name="khgzsj${index}"
																			value="${ffList.ygzsj}"/>
												</td>
												<td>
													${ffList.ffcjje}
													<input type="hidden" id="khcjje${index}"
																			name="khcjje${index}"
																			value="${ffList.ffcjje}"/>
												</td>
												</logic:equal>	
												<!--end�㽭�Ƽ�-->
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
														<input type="text" id="gzsj${index}" name="gzsj${index}"
															value="<bean:write name="ffList" property="gzsj"/>"
															style="width:80px"
															onblur="changecjje(this);document.all(this.id.replace('gzsj','cjje')).focus();"
															readonly />
													</td>
												</logic:present>
												<logic:notPresent name="bjlh">
													<td>
														<%--�人����ѧ--%>
														<logic:equal value="10497" name="xxdm">
															<logic:equal value="ͨ��" name="ffList" property="xxsh">
																<input type="text" id="gzsj${index}"
																	name="gzsj${index}"
																	value="<bean:write name="ffList" property="gzsj"/>"
																	style="width:80px" 
																	readonly="readonly"
																	onblur="changecjje(this);document.all(this.id.replace('gzsj','cjje')).focus();" />
															</logic:equal>
															<%--�人����ѧend--%>
															<logic:notEqual value="ͨ��" name="ffList" property="xxsh">
																<input type="text" id="gzsj${index}"
																	name="gzsj${index}"
																	value="<bean:write name="ffList" property="gzsj"/>"
																	style="width:80px"
																	maxlength="6"
																	onkeyup="value=value.replace(/[^\d|.]/g,'')"
																	onblur="changecjje(this);document.all(this.id.replace('gzsj','cjje')).focus();" />
															</logic:notEqual>
														</logic:equal>
														<logic:notEqual value="10497" name="xxdm">
															<input type="text" id="gzsj${index}" name="gzsj${index}"
																value="<bean:write name="ffList" property="gzsj"/>"
																style="width:80px"
																maxlength="6"
																onblur="changecjje(this);document.all(this.id.replace('gzsj','cjje')).focus();" 
																onkeyup="value=value.replace(/[^\d|.]/g,'')"/>
														</logic:notEqual>
													</td>
												</logic:notPresent>
												<logic:present name="bjlh">
													<td>
														<input type="text" id="cjje${index}" name="cjje${index}"
															value="<bean:write name="ffList" property="cjje"/>"
															style="width:60px"
															onblur="changezje();if(this.value>300&&document.forms[0].gwxz.value == '001') {alert('����,����д��Ӧ��ע!');document.all(this.id.replace('cjje','bz')).focus();}"
															readonly />
													</td>
												</logic:present>
												<logic:notPresent name="bjlh">
													<%--�人����ѧ--%>
													<logic:equal value="10497" name="xxdm">
														<td>
															<input type="text" id="cjje${index}" name="cjje${index}"
																value="<bean:write name="ffList" property="cjje"/>"
																style="width:60px" 
																readonly="readonly"
																onblur="changezje();" />
														</td>
													</logic:equal>
													<%--�人����ѧend--%>
													<logic:notEqual value="10497" name="xxdm">
														<td>
															<input type="text" id="cjje${index}" name="cjje${index}"
																value="<bean:write name="ffList" property="cjje"/>"
																style="width:60px"
																maxlength="6"
																onkeyup="value=value.replace(/[^\d|.]/g,'')"
																onblur="changezje();" />
														</td>
													</logic:notEqual>
												</logic:notPresent>
												<logic:present name="showjsxx">
													<td>
														&nbsp;
													</td>
												</logic:present>
												<!--���ݴ�ѧ-->
												<logic:equal value="11078" name="xxdm">
													<td>
														<input type="text" id="gzpj${index}" name="gzpj${index}"
																	value="<bean:write name="ffList" property="gzpj"/>"
																	style="width:120px"
																	maxlength="150"
																	onblur="" />
													</td>
												</logic:equal>
												<!--���ݴ�ѧ-->

												<!--�㽭��ýѧԺ-->
												<logic:equal value="11647" name="xxdm">
												<td>
													<input type="text" name="yhkh${index}"
																value="<bean:write name="ffList" property="kh"/>"
																maxlength="20"
																style="120px"
																readonly="readonly"
																onkeyup="value=value.replace(/[^\d]/g,'') "/>			
												</td>
												<td>
													<input type="text" name="yhmc${index}"
																value="<bean:write name="ffList" property="yhmc"/>"
																maxlength="15"
																readonly="readonly"
																style="width:120px"/>				
												</td>
												</logic:equal>
												<!--end�㽭��ýѧԺ-->
												<td>
													<%--�人����ѧ--%>
													<logic:equal value="10497" name="xxdm">
														<logic:equal value="ͨ��" name="ffList" property="xxsh">
															<input type="text" name="bz${index}"
																value="<bean:write name="ffList" property="bz"/>"
																style="width:120px" readonly="readonly" />
														</logic:equal>
														<logic:notEqual value="ͨ��" name="ffList" property="xxsh">
															<input type="text" name="bz${index}"
																value="<bean:write name="ffList" property="bz"/>"
																maxlength="60"
																style="width:120px" />
														</logic:notEqual>
													</logic:equal>
													<%--���人����ѧ--%>
													<logic:notEqual value="10497" name="xxdm">
														<input type="text" name="bz${index}"
															value="<bean:write name="ffList" property="bz"/>"
															maxlength="60"
															style="120px" />
													</logic:notEqual>
												</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
							</td>
						</tr>
						<tr>
							<th>����ܼƣ���λ:Ԫ��</th>
							<td>
								<div align="center" id="zje"></div>
							</td>
						</tr>
						</tbody>
						<tfoot>
					      <tr>
					        <td colspan="2">
					          <div class="btn">
					            <button type="button" onclick="commit();return false;" style="width:80px"
									id="buttonSave">
									�� ��
								</button>
								<!--�㽭�Ƽ�ѧԺ-->
								<logic:equal value="11057" name="xxdm">
									<button type="button" onclick="defaultValue()" style="width:80px"
										id="buttonDefault">
										Ĭ��
									</button>
								</logic:equal>
								<!--end�㽭�Ƽ�ѧԺ-->

								<!--���ݴ�ѧ-->
								<logic:equal value="11078" name="xxdm">
									<button type="button" onclick="defaultValue();return false;" style="width:80px"
										id="buttonDefault">
										Ĭ��
									</button>
								</logic:equal>
								<!--end���ݴ�ѧ-->

								<button type="button" onclick="Close();return false;" style="width:80px"
									id="buttonSave">
									�� ��
								</button>
					          </div>
					        </td>
					      </tr>
					    </tfoot>
					</table>
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

