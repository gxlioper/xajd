<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>   
		<script language="javascript" src="js/function.js"></script>      
		<script language="javascript">
		function to_save(){
		    var jlhz=$("zyzbjlhz").value;
		    var clqk=$("xgbmclqk").value;
		    var ldps=$("yldps").value;
		    if(jlhz.length>2000){
		       alert("��Ҫֵ���¼��������������");
		       return false;
		    }
		    if(clqk.length>2000){
		       alert("��ز��Ŵ����������������");
		       return false;
		    }
		    if(ldps.length>50){
		       alert("Ժ�쵼��ʾ����������");
		       return false;
		    }
		    if(checkSearchTj("qssj","jssj")){
		    	 dataDoSave('qssj-jssj');
		    }
		   
		}
		</script>
	</head>

	<body onload="checkWinType();">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ֵ���¼ - һ�ܻ���</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/data_search" method="post">
							<logic:empty name="rs">
								<br />
								<br />
								<p align="center">
									<!-- �д������� -->
								</p>
							</logic:empty>
							<logic:notEmpty name="rs">
								<input type="hidden" id="doType" name="doType"
									value="<bean:write name="doType" scope="request"/>" />
								<input type="hidden" id="pkValue" name="pkValue"
									value="<bean:write name="pkValue" scope="request"/>" />
								<input type="hidden" id="disableEle" name="disableEle" value="" />
								<input type="hidden" id="readonlyEle" name="readonlyEle"
									value="" />
								<input type="hidden" id="url" name="url" value="/sjcz/yzzbhzb.jsp" />
								<table class="formlist" border="0" align="center" style="width: 100%">
								<thead>
									<tr>
										<th colspan="4">
											<span>һ��ֵ�����ά��</span>
										</th>
									</tr>
								</thead>
								<tbody>	
										<tr>
											<th align="right" width="20%">
												<font color="red">*</font>ʱ�䣺
											</th>
											<td align="left"><html:text name='rs' property="qssj" styleId="qssj" onblur="dateFormatChg(this)" style="cursor:hand;width:80px" onclick="return showCalendar('qssj','y-mm-dd');" /> - <html:text name='rs' property="jssj" styleId="jssj" onblur="dateFormatChg(this)" style="cursor:hand;width:80px" onclick="return showCalendar('jssj','y-mm-dd');" />
											</td>
											<logic:notPresent name="syzy">
											<th align="right"  width="15%">
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
											</logic:notPresent>
											<logic:present name="syzy">
												<th align="right">
												<font color="red">*</font>�����ˣ�
											</th>
											<td align="left">
												<html:text name="rs" property="hzr"/>
											</td>
											</logic:present>
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
											<th align="right">
												��Ҫֵ���¼���ܣ�<br>
												<font color="red"><��2000����></font>
											</th>
											<td colspan="3">
												<html:textarea name='rs' property='zyzbjlhz' style="width:95%"
													rows='6' />
											</td>
										</tr>
										<tr>
											<th align="right">
												��ز��Ŵ��������<br>
												<font color="red"><��2000����></font>
											</th>
											<td colspan="3">
												<html:textarea name='rs' property='xgbmclqk' style="width:95%"
													rows='6' />
											</td>
										</tr>
										<tr>
											<th align="right">
												Ժ�쵼��ʾ��<br>
												<font color="red"><��50����></font>
											</th>
											<td colspan="3">
												<html:textarea name='rs' property='yldps' style="width:95%"
													rows='5' />
											</td>
										</tr>
										</tbody>
										<tfoot>
											<tr bgcolor="EEF4F9" align="center">
												<td colspan="4">
													<div class="btn">
															<button type="button" id="buttonSave" 
															onclick="to_save()"
																style="width: 80px">
																��	��
															</button>
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
