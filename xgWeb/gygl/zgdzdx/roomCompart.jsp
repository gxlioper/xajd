<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/gyglShareData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>
		<script language="javascript">
	      function hiddenField() {
		     i = document.getElementsByTagName("select").length;
		    for (j = 0; j < i; j++) {
			  document.getElementsByTagName("select")[j].style.visibility = "hidden";
		    } 
	      }
	      function setTBGbed(){
	          totalBed.innerText="0";
	          boyBed.innerText="0";
	          girlBed.innerText="0";
	      }
	    </script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ������� - ���Ữ��</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/zgdzdx_Gygl" method="post">
			<html:hidden name="zgdzdxForm" property="conditionSqlValue"
				styleId="conditionSqlValue" />
			<input type="hidden" name="oldCondiSqlValue" id="oldCondiSqlValue"
				value="<bean:write name="oldCondiSqlValue"/>" />
			<input type="hidden" name="userName" id="userName"
				value="<bean:write name="userName"/>" />
			<input type="hidden" name="xxdm" id="xxdm" value="" />
				<!-- ���Ữ�� -->
				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<th colspan="2">
								<span>���Ữ��</span>
							</th>
						</tr>
					</thead>
					<tbody>		
						<tr align="center">
							<th width="38%" align="left" rowspan="2">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;У������
								<html:select property="xiaoqu" style="width:180px"
									styleId="xiaoqu">
									<html:option value="">--��ѡ��--</html:option>
									<html:options collection="xiaoQuList" property="dm"
										labelProperty="mc" />
								</html:select>
								<br>
								¥���Ա��޶���
								<html:select property="xbxd" style="width:180px" styleId="xbxd"
									onchange="ldLbIinit()">
									<html:options collection="xbXdList" property="dm"
										labelProperty="mc" />
								</html:select>
								<br>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="red">*</font>¥������
								<html:select property="lddm" styleId="lddm" style="width:180px"
									onfocus="beforSubmit();"
									onchange="SsIinit();lcLbIinit();YhfSsIinit();">
									<html:options collection="ldList" property="dm"
										labelProperty="mc" />
								</html:select>

								<br>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								��ţ�
								<html:select property="cs" styleId="cs" style="width:180px"
									onfocus="beforSubmit();" onchange="SsIinit();YhfSsIinit();">
									<html:options collection="lcList" property="dm"
										labelProperty="mc" />
								</html:select>
							</th>
							<td width="62%" align="left">
								<font color="red">*</font>�꼶��
								<html:select property="nj" styleId="nj" style="width:100px"
									onfocus="beforSubmit();" onchange="YhfSsIinit();getData();">
									<html:option value="">--��ѡ��--</html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;&nbsp;
								<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />��
								<html:select property="xydm" style="width:150px" styleId="xy"
									onfocus="beforSubmit();" onchange="YhfSsIinit();getData();">
									<html:option value="">--��ѡ��--</html:option>
									<html:options collection="xyList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td>
								&nbsp;&nbsp;&nbsp;δ����ѧ������(��):
								<span id="allbody" style="width: 60px"> <bean:write
										name="allbody" scope="request" /> </span>(��):
								<span id="allboy" style="width: 60px"> <bean:write
										name="allboy" scope="request" /> </span>(Ů):
								<span id="allgirl" style="width: 60px"> <bean:write
										name="allgirl" scope="request" /> </span>
								<br>
								&nbsp;&nbsp;&nbsp;�ѻ��ִ�λ����(��):
								<span id="totalBed" style="width: 60px"> <bean:write
										name="totalBed" scope="request" /> </span> (��):
								<span id="boyBed" style="width: 60px"> <bean:write
										name="boyBed" scope="request" /> </span> (Ů):
								<span id="girlBed" style="width: 60px"> <bean:write
										name="girlBed" scope="request" /> </span> (���):
								<span id="bgBed" style="width: 60px"> <bean:write
										name="bgBed" scope="request" /> </span>
							</td>
						</tr>
						</tbody>
					</table>
					<table class="permissionlist" border="0" align="center" style="width: 100%">
						<tr align="center">
							<td colspan="3">
								<table width="100%" align="center" class="">
									<tr align="center" bgcolor="#D0E0EE">
										<td align="center">
											δ��������
										</td>
										<td>
										</td>
										<td align="center">
											�ѻ������
										</td>
									</tr>
									<tr align="center">

										<td align="center" style="width:40%;" align="left">
											<font color="red" style="font-size:10px;">��ʾ����סCtrl��Shift��(���������������ƶ�)���ж�ѡ</font>
											<br>
											¥������/����/���Һ�/��λ��
											<html:select property="oracleItem" style="width:100%; " onmouseover="null"
												size="27" styleId="oracleList" multiple="multiple">
												<html:options collection="ssxxList" property="dm"
													labelProperty="mc" />
											</html:select>
										</td>

										<td>
											<span id="btn1">
												<table width="97%" align="center" class="tbstyle">
													<tr align="center">
														<td>
															�� �� ��
															<br>
															<br>
															<button class="button2" onclick="afj_addColum()"
																style="width:50px;height: 20px" title="���Ữ��">
																&rarr;

															</button>
															<br>
															<br>
															<button class="button2" onclick="afj_delColum()"
																style="width:50px;height: 20px" title="�����ͷ�">
																&larr;
															</button>
															<br>
															<br>
														</td>
													</tr>
												</table> <br />
												<table width="97%" align="center" class="tbstyle">
													<tr align="center">
														<td>
															�� ¥ ��
															<br>
															<br>
															<button class="button2" onclick="acs_ald_addColum('acs')"
																style="width:50px;height: 20px" title="���Ữ��">

																&rarr;
															</button>
															<br>
															<br>
															<button class="button2" onclick="acs_ald_delColum('acs')"
																style="width:50px;height: 20px" title="�����ͷ�">
																&larr;
															</button>
															<br>
															<br>
														</td>
													</tr>
												</table> <br />
												<table width="97%" align="center" class="tbstyle">
													<tr align="center">
														<td>

															�� ¥ ��
															<br>
															<br>
															<button class="button2" onclick="acs_ald_addColum('ald')"
																style="width:50px;height: 20px" title="���Ữ��">
																&rarr;
															</button>
															<br>
															<br>
															<button class="button2"
																onclick="acs_ald_delColum('ald');"
																style="width:50px;height: 20px" title="�����ͷ�">
																&larr;
															</button>
															<br>
															<br>
														</td>
													</tr>
												</table> </span>
										</td>
										<td align="center" align="left">
											<font color="red" style="font-size:10px;">��ʾ����סCtrl����Shift��(���������������ƶ�)���ж�ѡ</font>
											<br>
											�꼶/<bean:message key="lable.xsgzyxpzxy" />/¥������/����/���ұ��/��λ��
											<html:select property="sql" style="width:100%;" size="27" onmouseover="null"
												styleId="sql" multiple="multiple">
												<html:options collection="ssYhfList" property="dm"
													labelProperty="mc" />
											</html:select>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						</table>
						<table class="formlist" border="0" align="center" style="width: 100%">
						<tfoot>
						<tr>
							<td align="center" colspan="2">
								<button name="button1"
									style="width:100px"
									onclick="if(confirm('ȷ��Ҫ�ύ�ѻ�������б����ݣ�')){SsHfDataSave();}else{return false;}">
									ȷ ��
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<button name="button2"
									style="width:100px"
									onclick="refreshForm('/xgxt/zgdzdx_Gygl.do?method=roomCompartition')">
									�� ��	
								</button>
							</td>
						</tr>
						</tfoot>
					</table>
		</html:form>
		<logic:notEmpty name="doFlag">
			<logic:equal name="doFlag" value="true">
				<script>				   
					alert("�����ɹ�!");					
				</script>
			</logic:equal>
			<logic:equal name="doFlag" value="false">
				<script>				    
					alert("����ʧ��!");
				</script>
			</logic:equal>
		</logic:notEmpty>
	</body>
	<script type="text/javascript">

	
	</script>
</html>
