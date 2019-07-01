<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
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
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script type="text/javascript">
	function save(obj){
	
		var xxdm = $("xxdm").value;
		
		//���ݴ�ѧ
		if("11078" == xxdm){
			var checkBoxArr = document.getElementsByName("checkVal");
			var flag = false;
			obj = "xh";
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					flag = true;
				}
			}
			
			if(!flag){
				alert("�빴ѡҪ������ĸ�λ���ʣ���");
				return false;
			}
		}
		
		var value = obj.split("-");
		for(var i=0; i<value.length; i++){
			if(val(value[i])==""){
				alert("�뽫��\*�ŵ���Ŀ��д������");				
				return false;
			}
		}
		var yhtc = val('yhtc');
		var bz = val('bz');    
		
		if(yhtc != null){
	         	if(yhtc.replace(/[^\u0000-\u00ff]/g, "**").length > 100){	         
	          		 alert("�к��س����ܴ���100���ַ�");
	          		 return false;
	       		 }
			}
		if(bz != null){
	         	if(bz.replace(/[^\u0000-\u00ff]/g, "**").length > 250){	         
	          		 alert("��ע���ܴ���250���ַ�");
	          		 return false;
	       		 }
			}		
		refreshForm('qgzxZgdzdx.do?method=saveQgzxsq');
	}
	
	function printReport(){
		window.open('qgzxZgdzdx.do?method=printGzdxQgzxsqb');
	}
	
	function onShow(){
		if($("gwxznum")){
			var xn = $("xn").value;
			var xq = $("xq").value;
			var nd = $("nd").value;
			var xh = $("xh").value;
			
			var tableName = "qgzxsqb";
			var colList = ["xh", "xn", "nd", "xq", "lxdh", "kcjqgzxsj", "sqly", "bz","zzmmdm", "yhtc", "kcsgz","jjqk"];
			var pk = "xn||xq||nd||xh";
			var pkValue = xn + xq + nd + xh;
			var query = "and rownum = 1";
			
			if(xn != "" && xq != "" && nd != "" && xh != ""){
				getTableInfo(tableName,colList,pk,pkValue,query);
			}
			
			dwr.engine.setAsync(false);
				
			var gwxznum = $("gwxznum").value;
			colList = ["gwxzdm"];
			
			getOtherData.getTableListInfo(tableName, colList, pk, pkValue,"",function(data){
				if( data != null && data.length > 0){
					for (i = 0; i < data.length; i++) {
						for(j=0; j<gwxznum; j++){
							var id = "gwxzdm"+j;
							if(data[i].gwxzdm == $(id).value){
								$(id).checked="checked";
							}
						}
					}
				}
			});
			
			dwr.engine.setAsync(true);
		}
		
	}
	</script>
	<body onload="onShow()">
		<html:form action="/qgzxZgdzdx.co" method="post">
			<div class="title">
					<div class="title_img" id="title_m">
						��ǰλ�ã��ڹ���ѧ - ���� - �����ڹ���ѧ
					</div>			
			</div>
			<logic:equal value="1" name="inTime">
				<br />
				<br />
				<p align="center">
					��������ʱ���ڣ���ʱ���������룡
				</p>
			</logic:equal>
			<logic:notEqual value="1" name="inTime">
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/qgzxZgdzdx.do?method=qgzxsq" />
				<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }" />
				<input type="hidden" id="gwxznum" name="gwxznum" value="${gwxznum }" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td height="18" colspan="4" align="center">
									<b>��д�����</b>
							</td>
						</tr>
					</thead>					
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<input type="hidden" name="xh" id="xh" value="<bean:write name='rs' property="xh" />">
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left">								
								<bean:write name='rs' property="xh" />
							</td>
						</logic:equal>
						<td align="right">
							��ȣ�
						</td>
						<td align="left">
							<html:hidden name="rs" property="nd"/>
							<bean:write name='rs' property="nd" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							������
						</td>
						<td align="left">
							<bean:write name='rs' property="xm" />
						</td>
						<td align="right">
							ѧ�꣺
						</td>
						<td align="left">
							<html:hidden name="rs" property="xn"/>
							<bean:write name='rs' property="xn" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�Ա�
						</td>
						<td align="left">
							<bean:write name='rs' property="xb" />
						</td>
						<td align="right">
							ѧ�ڣ�
						</td>
						<td align="left">
							<bean:write name='rs' property="xqmc" />
							<html:hidden name="rs" property="xq"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							<bean:write name='rs' property="xymc" />
						</td>
						<td align="right">
							�����ţ�
						</td>
						<td align="left">
							<bean:write name='rs' property="ssbh" />
						</td>						
					</tr>
					<tr style="height:22px">
						<td align="right">
							רҵ��
						</td>
						<td align="left">
							<bean:write name='rs' property="zymc" />
						</td>
						<td align="right">
							��ϵ�绰��
						</td>
						<td align="left">
							<html:text name='rs' property="lxdh" styleId="lxdh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "/>
						</td>												
					</tr>
					<tr style="height:22px">
						<td align="right">
							�༶��
						</td>
						<td align="left">
							<bean:write name='rs' property="bjmc" />
						</td>
						<td align="right">
							�к��س���
						</td>
						<td>
							<html:text name="rs"  property="yhtc" styleId="yhtc" style="width:150px;height:21px;font-size:10pt;" maxlength="100"/><span style="width:18px;border:0px solid red;">
							<select style="margin-left:-150px;width:168px; background-color:#FFEEEE;" onchange="ele('yhtc').value=this.value">
								<option value="�鷨">�鷨</option>
								<option value="�滭">�滭</option>
								<option value="���Դ���">���Դ���</option>
								<option value="��ҳ����">��ҳ����</option>
								<option value="д��">д��</option>
							</select>
						</td>
					</tr>
					<!-- ���ݴ�ѧ -->
					<logic:equal name="xxdm" value="11078">
					<tr>
						<td align="right">
							<font color="red">*</font>�μӸ�λ���ʣ�
						</td>
						<td colspan="3">
							<logic:iterate name="gwxzList" id="s" indexId="index">
								<input type="checkbox" name="checkVal" id="gwxzdm${index }" value="${s.gwxzdm }">${s.gwxzmc }		
							</logic:iterate>
						</td>
					</tr>
					</logic:equal>
					<!-- �ǹ��ݴ�ѧ -->
					<logic:notEqual name="xxdm" value="11078">
					<tr>
						<td align="right">
							<font color="red">*</font>�μӸ�λ���ʣ�
						</td>
						<td colspan="3">
							<html:select property="gwxzdm" styleId="gwxzdm" name="rs">
								<html:option value=""></html:option>
								<html:options collection="gwxzList" property="gwxzdm" labelProperty="gwxzmc"/>
							</html:select>
						</td>
					</tr>
					</logic:notEqual>
					<tr>
						<td align="right">
							�ɲμ��ڹ���ѧʱ�䣺
						</td>
							<logic:present name="kxList">
							<td colspan="3">
								<table border="0" cellpadding="0" cellspacing="0" align="center">
									<tr>
										<td align="center">
											ʱ��
										</td>
										<td>
											����һ
										</td>
										<td>
											���ڶ�
										</td>
										<td>
											������
										</td>
										<td>
											������
										</td>
										<td>
											������
										</td>
									</tr>
									<logic:iterate id="kxsj" name="kxList">
										<tr>
											<td>
												${kxsj.sj}
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}1" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}1"
													value="${kxsj.mon}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}2" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}2"
													value="${kxsj.tue}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}3" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}3"
													value="${kxsj.wed}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}4" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}4"
													value="${kxsj.thu}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}5" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}5"
													value="${kxsj.fri}" />
											</td>
										</tr>
									</logic:iterate>
								</table>
								</td>
								<script language="javascript">
										for(var i=0;i<8;i++){
											for(var j=1;j<6;j++){
											 	if(document.getElementById("index"+i+j)){
											 		if(document.getElementById("index"+i+j).value==1){
														document.getElementById(i+""+j).checked="checked";
												    }
											 	}
											}
										}
									</script>							
						</logic:present>
						<logic:notPresent name="kxList">
							<input type="hidden" name="sfwh" value="sfwh" />
							<td colspan="3" align="center">
								<table border="0" cellpadding="0" cellspacing="0" align="center"
									class="tbstyle">
									<tbody id="tbSj">
										<tr>
											<td align="center">
												ʱ��
											</td>
											<td>
												����һ
											</td>
											<td>
												���ڶ�
											</td>
											<td>
												������
											</td>
											<td>
												������
											</td>
											<td>
												������
											</td>
										</tr>
										<logic:iterate id="kxsj" name="whkxList">
											<tr>
												<td>
													${kxsj.sj}
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}1" value="1" />
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}2" value="1" />
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}3" value="1" />
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}4" value="1" />
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}5" value="1" />
												</td>
											</tr>
										</logic:iterate>
									</tbody>
								</table>
							</td>
						</logic:notPresent>
					</tr>
					<tr>
						<td align="right">
							���˼�ͥ����״����
						</td>
						<td align="left" colspan="3">
							<html:textarea name='rs' property='jjqk' styleId="jjqk" style="width:99%" rows='5' onblur="chLeng(this,'600')"/>
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							�ɴ��º��ֹ�����
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='kcsgz' styleId="kcsgz" style="width:99%" rows='5' onblur="chLeng(this,'150')"/>
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<td align="right">
							��ע��
						</td>
						<td colspan="3"><html:textarea name='rs' property='bz' styleId="bz" style="width:99%" rows='5' onblur="chLeng(this,'250')"/>
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button type="button" class="button2" onclick="save('xh-gwxzdm')">
						�� �� �� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="window.open('/xgxt/qgzx/gzdx/qgzxsqb.jsp')">
						�� ӡ Ԥ ��
					</button>
				</div>
			</logic:notEmpty>
			</logic:notEqual>

			<logic:equal name="bmztj" value="true">
					<script>
				    	alert("�����������ڹ���ѧ��������");
				    </script>
				</logic:equal>
			<logic:notEmpty name="result">
				<logic:equal name="result" value="true">
					<script>
				    	alert("����ɹ���");
				    	Close();
				    	if(window.dialogArguments)
						window.dialogArguments.document.getElementById('search_go').click();
				    </script>
				</logic:equal>
				<logic:equal name="result" value="false">				
				<logic:notPresent name="hmdMember">
					<script>
				    	alert("����ʧ�ܣ�");
				    </script>
				  </logic:notPresent>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
