<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/wjcfFuction.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script type="text/javascript">
			function saveCxcf() {
				var cxsj = "";
				var cxwh = "";
				if($("jcsj")){
					cxsj = document.getElementById('jcsj').value;
				}
				
				if($("jcwh")){
					cxwh = document.getElementById('jcwh').value;
				}
				
				var blog=true;
				
				if ($("jcsj") &&(cxsj==null || cxsj=="") ) {
					alert("��\*���ֶα�����д��");
					return false;
				}
				
				if ($("jcwh") &&(cxwh==null || cxwh=="")) {
					alert("��\*���ֶα�����д��");
					return false;
				}
				refreshForm('wjcf_csmz_cxcfsave.do');BatAlert.showTips('���ڲ��������Ե�...');
			}
		</script>
	</head>
	<body>	   
	    <html:form action="/wjcfcsmzwh" method="post">
	    <input type="hidden" id="pkVal" name="pkVal"
				value="<bean:write name="pkValue" scope="request"/>"/>
		   <div class="tab_cur" id="title_m">
				<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>
					<logic:equal value="10827" name="xxdm">
						<bean:message key="wjcf_csmz_cxcfsq" bundle="wjcfcsmz"/>
					</logic:equal>
					<logic:notEqual value="10827" name="xxdm">
						
						<logic:equal value="13022" name="xxdm">
						��ǰ����λ�ã�Υ�ʹ��� - ����쿴����� - ���
						</logic:equal>
						<logic:notEqual value="13022" name="xxdm">
							��ǰ����λ�ã�Υ�ʹ��� - <logic:equal value="10827" name="xxdm">���</logic:equal><logic:notEqual value="10827" name="xxdm">������ֹ��� - ���</logic:notEqual>���						
						</logic:notEqual>
					</logic:notEqual>
				</a>
				</p>
			</div>								
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>	
			<logic:notEmpty name="rs">
				<div class="tab">
					<table width="100%"  border="0" class="formlist">
					 <thead>
	    				<tr>
	        				<th colspan="4"><span>�������</span></th>
	        			</tr>
	   				 </thead>
	   				  <tfoot>
						      <tr>
						        <td colspan="6"><div class="bz">
						          <div class="btn">
						          	 <button type="button" class="button2"
									onclick="saveCxcf()"
									 id="buttonSave">
									�� ��
										</button>
										&nbsp;&nbsp;
										<button type="button" class="button2" onclick="Close();return false;" 
											id="buttonClose">
											�� ��
										</button>
						          </div>
						          </td>
						      </tr>
						    </tfoot>
			   			<tbody>	
						<tr>
							<th align="right" width="20%">
								ѧ��
							</th>
							<td align="left" width="30%">
								<bean:write name="rs" property="xh" />
								<input type="hidden" name="xh" value="<bean:write name="rs" property="xh" />" /> 
							</td>
							<th align="right" width="20%">
								�����ļ���
							</th>
							<td align="left" width="30%">
								<bean:write name="rs" property="cfwh" />
								<input type="hidden" name="cfwh" value="<bean:write name="rs" property="cfwh" />"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								����
							</th>
							<td align="left">
								<bean:write name="rs" property="xm" />
							</td>
							<th align="right">
								ѧ��
							</th>
							<td align="left">
								<bean:write name="rs" property="xn" />
							</td>
						</tr>
						<tr>
							<th align="right">
								�Ա�
							</th>
							<td align="left">
								<bean:write name="rs" property="xb" />
							</td>
							<th align="right">
								����ʱ��
							</th>
							<td align="left">
								<bean:write name="rs" property="cfsj" />
								<input type="hidden" name="cfsj" value="<bean:write name="rs" property="cfsj" />"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								ѧ��
							</th>
							<td align="left">
								<bean:write name="rs" property="xn" />
							</td>
							<th align="right">
								�������
							</th>
							<td align="left" colspan="">
								<bean:write name="rs" property="cflbmc" />
							</td>
						</tr>
						<tr>
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								<bean:write name="rs" property="xymc" />
							</td>
							<th align="right">
								��������
							</th>
							<td align="left" colspan="">
								<bean:write name="rs" property="cfyymc" />
							</td>
						</tr>
						<tr>
							<th align="right">
								רҵ��
							</th>
							<td align="left">
								<bean:write name="rs" property="zymc" />
							</td>
							
							<th align="right">
					       ����ʱ��
							</th>
							<td align="left" colspan="">
								<bean:write name="rs" property="cxsj" />
							</td>
						</tr>
						<tr>
							<th align="right">
								�༶
							</th>
							<td align="left">
								<bean:write name="rs" property="bjmc" />
							</td>
							<th align="right">
								<font color="red">*</font>���
							</th>
							<td align="left" colspan="">
								<html:select property="sh" style="width:100px"
									styleId="sh">
									<html:options collection="shList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<!-- �㽭��ý -->
						<logic:equal value="11647" name="xxdm">
							<logic:notEqual value="xy" name="userType">
								<tr>
									<th align="right">
										<font color="red">*</font>����ĺ�
									</th>
									<td align="left">
										<html:text property="jcwh" styleId="jcwh" maxlength="30"></html:text>
									</td>
									<th align="right">
										<font color="red">*</font>���ʱ��
									</th>
									<td align="left" colspan="">
										<html:text property="jcsj" styleId="jcsj" onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('jcsj','y-mm-dd');"></html:text>
									</td>
								</tr>
								<tr>
									<th align="right">
										���Ĳ���
									</th>
									<td align="left">
										<html:text property="fwbm" styleId="fwbm"></html:text>
									</td>
									<th align="right">
										
									</th>
									<td align="left" colspan="">
										
									</td>
								</tr>
							</logic:notEqual>
						</logic:equal>
						<!-- ���㽭��ý -->
						<logic:notEqual value="11647" name="xxdm">
							<!-- �ǳ�ɳ���� -->
							<logic:notEqual value="10827" name="xxdm">
								<!-- ѧУ�û� -->
								<logic:notEqual value="xy" name="userType">
									<logic:notEqual value="true" name="fdyQx">
									<logic:notEqual value="true" name="bzrQx">
									<tr>
									<th align="right">
										<font color="red">*</font>���ʱ��
									</th>
									<td align="left" colspan="">
										<html:text property="jcsj" styleId="jcsj" onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('jcsj','y-mm-dd');"></html:text>
									</td>
									<th align="right">
										<font color="red">*</font>����ĺ�
									</th>
									<td align="left">
										<html:text property="jcwh" styleId="jcwh" maxlength="30"></html:text>
									</td>
								</tr>
								
								<logic:equal value="10827" name="xxdm">
								<tr>
									<th align="right">
										������
									</th>
									<td align="left" colspan="3">
										<html:text property="cxjg" styleId="cxjg"></html:text>
									</td>
								</tr>
								</logic:equal>
								</logic:notEqual>
								</logic:notEqual>
								</logic:notEqual>
							</logic:notEqual>
						</logic:notEqual>
						<tr>
							<th align="right">
					  			��������
							</th>
							<td align="left" colspan="3" >
								<html:textarea name="rs" property="bz" style="width:95%;word-break:break-all;" rows="4" readonly="true"></html:textarea>
							</td>
						</tr>
						<tr>
							<th align="right">
								������
							</th>
							<td align="left" colspan="3">
								<html:textarea  property="shyj" rows="3"
									style="width:95%;word-break:break-all;" styleId="shyj">
								</html:textarea>
							</td>
						</tr>
					</table>
					</div>
			</logic:notEmpty>
		  <logic:equal value="yes" name="done">
			<script>
				alert("�����ɹ�!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		   </logic:equal>
		   <logic:equal value="no" name="done">
			<script>
				alert("����ʧ��!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		   </logic:equal>
	  </html:form>	
	</body>
</html>
