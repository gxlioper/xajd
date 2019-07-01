<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/gyglFunction.js"></script>
	</head>
	<body onload="loadView();">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a> ��Ԣ���� - ��Ϣά�� - �Ǳ�У��ס����Ϣά��</a>
			</p>
		</div>
		<!-- ���� end-->		
	
		<html:form action="/zgdzdx_Gygl" method="post">
		<input type="hidden" id="pkValue" name="pkValue"
		value="<bean:write name="pkValue" scope="request"/>" />
		<input type="hidden" id="isview" name="isview"
				value="<bean:write name="isview" scope="request"/>" />
			
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">	
			<!-- ס����Ϣά�� -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>ס����Ϣά��</span>
						</th>
					</tr>
				</thead>
				<tbody>		
						<tr>
							<th align="right" width="15%">
								<font color="red">*</font>ѧ�ţ�								
							</th>
							<td align="left" width="15%">
							<html:text name="rs" property="xh" styleId="xh" disabled="true" maxlength="20"/>
							</td>
							<th align="right">
								<font color="red">*</font>������
							</th>
							<td align="left">
								<html:text name="rs" property="xm" styleId="xm" maxlength="15"/>
							</td>						
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>�Ա�
							</th>
							<td align="left">
								<html:select name="rs" property="xb"  styleId="xb"  style="width:150px"
								onchange="refreshForm('/xgxt/zgdzdx_Gygl.do?method=wxsDormUserModi');">                                
                                 <html:option value=""></html:option>
                                 <html:option value="��">��</html:option>
                                 <html:option value="Ů">Ů</html:option>
								</html:select>
							</td>
							<th align="right">
								<font color="red">*</font>����ţ�
							</th>
							<td align="left">
								<html:select name="rs"  property="ssbh" 
									styleId="ssbh" onchange="refreshForm('/xgxt/zgdzdx_Gygl.do?method=wxsDormUserModi');">
									<html:option value=""></html:option>
									<html:options collection="ssList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							</tr>
						<tr>
												
							<th align="right">
								<font color="red">*</font>�꼶��
							</th>
							<td align="left">
								<html:select name="rs" property="nj" 
									styleId="nj" onchange="">
									<html:options collection="njList" property="nd"
										labelProperty="nd" />
                                </html:select>
							</td>
						
							<th align="right">
								<font color="red">*</font>��λ�ţ�
							</th>
							<td align="left">
								<html:select name="rs" property="cwh"  styleId="cwh"  style="width:150px">																		
									<html:options collection="cwhList" property="dm" labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
						<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							</th>
							<td align="left">
								<html:text name="rs" property="xymc" styleId="xy" maxlength="25"/>
							</td> 
							<th align="right">
								<font color="red">*</font>��סʱ�䣺
							</th>
							<td align="left">
								<html:text name="rs"  property="rzrq" styleId="rzrq"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('rzrq','y-mm-dd');" readonly="true"/>
							</td>							
						</tr>
						<tr>
							<th align="right">
								רҵ��
							</th>
							<td align="left">
								<html:text name="rs"  property="zymc" styleId="zy" maxlength="25"/>
							</td>
							<th align="right">								
							</th>
							<td align="left">
							</td>                         
						</tr>
						<tr>
						<th align="right">
							�༶��
						</th>
						<td align="left">
							<html:text name="rs" property="bjmc" styleId="bj" maxlength="25"/>
						</td>
						<th align="right">

						</th>
						<td align="left" colspan="2">
						</td>
					</tr>						
						<tr align="left">
							<th align="right">
								��ע��
							</th>
							<td colspan="4">
								<html:textarea  name="rs" property='bz' style="width:400px"
									rows='5' />
							</td>
						</tr>
						</tbody>
						<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="btn">
									<button id="buttonSave" 
										onclick="wxs_DUAddModi('xh-xm-xb-ssbh-cwh-nj-rzrq')"
										style="width: 80px">
										��	��
									</button>
								&nbsp;&nbsp;
								<button id="buttonClose" onclick="Close();return false;"
									style="width: 80px">
									��	��
								</button>
							</div>
						</td>
					</tr>
						</tfoot>
					</table>
		</logic:notEmpty>
		<logic:equal value="true" name="done">
			<script type="text/javascript">
				alert("�����ɹ���");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script type="text/javascript">
				alert("����ʧ��,���ѧ��(ѧ��)�Ѿ���ס��������������ݺ����ύ��");
			</script>
		</logic:equal>
				
		</html:form>
	</body>
	<script type="text/javascript">
	     function wxs_DUAddModi(mustFill){
	           var eles = mustFill.split("-");
	           for (i = 0; i < eles.length; i++) {
		           if (document.getElementById(eles[i]).value == "") {
			       alert("�뽫��\"*\"�ŵ���Ŀ����������");
			       return false;
		           }		
	           }	
	       if($("bz").value.length>200){
	           alert("��ע�������ܳ���200�֣�");
	           return false;
	       }	                        
           refreshForm("/xgxt/zgdzdx_Gygl.do?method=wxsDormUserModi&doType=Save");
           $("buttonSave").disabled=true;
	     }
	</script>
</html>
