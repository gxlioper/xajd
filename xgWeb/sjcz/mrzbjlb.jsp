<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
			function to_save(){
			    var zbjl=$("zbjl").value;
			    var tfsjjcl=$("tfsjjcl").value;	    
			    if(zbjl.length>2000){
			       alert("ֵ���¼����������");
			       return false;
			    }
			    if(tfsjjcl.length>2000){
			       alert("ͻ���¼�����������������");
			       return false;
			    }	    
			    dataDoSave('sj-zbrydm-zbrydm1')
			}
		</script>
	</head>

	<body onload="checkWinType();">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ֵ���¼ - ÿ�ռ�¼</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/data_search" method="post">
			<logic:empty name="rs">
								<br />
								<br />
								<p align="center">
									<!--  �д�������-->
								</p>
							</logic:empty>
							<logic:notEmpty name="rs">
								<input type="hidden" id="doType" name="doType"
									value="${doType }" />
								<input type="hidden" id="pkValue" name="pkValue"
									value="${pkValue }" />
								<input type="hidden" id="disableEle" name="disableEle" value="" />
								<input type="hidden" id="readonlyEle" name="readonlyEle"
									value="" />
								<input type="hidden" id="url" name="url" value="/sjcz/mrzbjlb.jsp" />
								<table class="formlist" border="0" align="center" style="width: 100%">
								<thead>
									<tr>
										<th colspan="4">
											<span>ÿ��ֵ���¼ά��</span>
										</th>
									</tr>
								</thead>
								<tbody>		
										<tr>
											<th align="right" width="20%">
												<font color="red">*</font>���ڣ�
											</th>
											<td align="left">
												<html:text name='rs' property="sj" styleId="sj" onblur="dateFormatChg(this)" style="cursor:hand;" onclick="return showCalendar('sj','y-mm-dd');" />
											</td>
											<th align="right" width="15%">
												������
											</th>
											<td align="left">
												<html:text name='rs' property="tq" style="width: 120px" styleId="tq" />
											</td>
										</tr>
										<logic:present name="syzy">
										<tr>
											<th align=right>У����</th>
											<td>
												<html:select name="rs" property="xqdm" style="width:150px">
													<html:option value=""></html:option>
													<html:options collection="xiaoqquList" property="dm"
														labelProperty="xqmc" />
												</html:select>
											</td>
											<th></th>
											<td></td>
										</tr>
										</logic:present>
										<tr>
											<logic:notPresent name="syzy">
											<th align="right">
												<font color="red">*</font>¥�����ƣ�
											</th>
											<td align="left">
												<html:select name="rs" property="lddm" style="width:150px"
													styleId="lddm">
													<html:option value=""></html:option>
													<html:options collection="ldList" property="lddm"
														labelProperty="ldmc" />
												</html:select>
											</td>											
											<th align="right">
												<font color="red">*</font>ֵ����Ա��
											</th>
											<logic:present name="showZjjj"><!-- �㽭����ְҵ����<bean:message key="lable.xsgzyxpzxy" /> -->
											  <td align="left">
											  <html:text name="rs" property="zbrydm"></html:text>
											  </td>											
											</logic:present>
											<logic:notPresent name="showZjjj">
											   <td align="left">
												<html:select name="rs" property="zbrydm" style="width:150px"
													styleId="zbrydm">
													<html:option value=""></html:option>
													<html:options collection="zbryList" property="zbrydm"
														labelProperty="zbrymc" />
												</html:select>
											   </td>
											</logic:notPresent>
											
											</logic:notPresent>
											<logic:present name="syzy">
											<th align="right">
												<font color="red">*</font>ֵ����Ա1��
											</th>
											<td align="left">
												<html:select name="rs" property="zbrydm" style="width:150px"
													styleId="zbrydm">
													<html:option value=""></html:option>
													<html:options collection="zbryList" property="zbrydm"
														labelProperty="zbrymc" />
												</html:select>
											</td>
											<th align="right">
												<font color="red">*</font>ֵ����Ա2��
											</th>
											<td align="left">
												<html:select name="rs" property="zbrydm1" style="width:150px"
													styleId="zbrydm1">
													<html:option value=""></html:option>
													<html:options collection="zbryList" property="zbrydm"
														labelProperty="zbrymc" />
												</html:select>
											</td>
											</logic:present>
										</tr>
										<logic:present name="showZjjj"><!-- �㽭����ְҵ����<bean:message key="lable.xsgzyxpzxy" /> -->
										<tr>
											<th align="right">
												ְ��
											</th>
											<td align="left">
												<html:text name='rs' property="zw" style="width: 120px"  />
											</td>
											<th align="right">
											    ���ţ� 												
											</th >
											<td align="left">	
												<html:select name="rs" property="bmdm" style="width:300px">
													<html:options collection="bmList" property="bmdm"
														labelProperty="bmmc" />
												</html:select>
											</td>
										</tr>
										<tr>
											<th align="right">
												����ʱ�䣺
											</th>
											<td align="left">
												<html:text name='rs' property="dgsj" style="width: 120px"  />
											</td>
											<th align="right">
											    ���ʱ�䣺 												
											</th >
											<td align="left">	
											    <html:text name='rs' property="lgsj" style="width: 120px" />											
											</td>
										</tr>
										</logic:present>
										<logic:present name="zbrLxList">
										<tr>
											<th align="right">
												����ʱ�䣺
											</th>
											<td align="left">
												<html:text name='rs' property="dgsj" style="width: 120px"  />
											</td>
											<th align="right">
											    ���ʱ�䣺 												
											</th >
											<td align="left">	
											    <html:text name='rs' property="lgsj" style="width: 120px" />											
											</td>
										</tr>
										<tr>
											<th align="right">
												ֵ����Ա���ͣ�
											</th>
											<td align="left">
												<html:select name="rs" property="zbrlx">
													<html:options collection="zbrLxList" property="en"
														labelProperty="cn" />
												</html:select>
											</td>
											<th align="right"> 												
											</th >
											<td align="left">												
											</td>
										</tr>
										</logic:present>
										<tr>
											<th align="right">
												ֵ���¼��<br>
												<font color="red"><��2000����></font>
											</th>
											<td colspan="3">
												<html:textarea name='rs' property='zbjl' style="width:95%"
													rows='8' />
											</td>
										</tr>
										<tr>
											<th align="right">
												ͻ���¼�������<br>
												<font color="red"><��2000����></font>
											</th>
											<td colspan="3">
												<html:textarea name='rs' property='tfsjjcl' style="width:95%"
													rows='8' />
											</td>
										</tr>
										</tbody>
										<tfoot>
											<tr bgcolor="EEF4F9" align="center">
												<td colspan="4">
													<div class="btn">
														<logic:notPresent name="syzy">
															<button type="button"
																onclick="to_save()"
																style="width:80px" id="buttonSave">
																�� ��
															</button>
															</logic:notPresent>
															<logic:present name="syzy">
															<button type="button"
																onclick=""
																style="width:80px" id="buttonSave">
																�� ��
															</button>
														</logic:present>
														&nbsp;&nbsp;
														<button type="button" id="buttonClose" onclick="Close();return false;"
															style="width: 80px">
															��	��
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
						dialogArgumentsQueryChick();
						window.close();
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('����ʧ�ܣ�');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
