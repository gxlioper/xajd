<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/getInsureInfo.js'></script>
		<script type="text/javascript">
		function checkRule(){
				var num = parseInt(document.getElementById("bxxzNum").value);	
				for(var i=0;i<num;i++){		
					if(i==0){
						for(var j=1;j<num;j++)	{
						if($("bxxzdm"+j)){
						 if(document.getElementById("bxxzdm"+i).value== document.getElementById("bxxzdm"+j).value){	
						 	alert("���������ظ���");
						 	return false;
						 }
						 }
						}
					}else if(i==num){
						for(var j=num;j>0;j--){
							if($("bxxzdm"+j)){
							if(document.getElementById("bxxzdm"+i).value==document.getElementById("bxxzdm"+j).value){
								alert("���������ظ���");
								return false;
							}
							}
						}
					}else{
						for(var j=i;j<num-1;j++){
						if(j-1!=i){
							if($("bxxzdm"+parseInt(j-1))){
							if(document.getElementById("bxxzdm"+i).value=="" || document.getElementById("bxxzdm"+i).value==null){
							}else{
							if(document.getElementById("bxxzdm"+i).value==document.getElementById("bxxzdm"+parseInt(j-1)).value){
								alert("���������ظ���");
								return false;
							}
							}
							}
						}
						}
						for(var m=i;m>=0;m--){
							if(m-1!=i){
							if($("bxxzdm"+parseInt(m-1))){
							if(document.getElementById("bxxzdm"+i).value=="" || document.getElementById("bxxzdm"+i).value==null){
							}else{				
							if(document.getElementById("bxxzdm"+i).value==document.getElementById("bxxzdm"+parseInt(m-1)).value){
								alert("���������ظ���");
								return false;
							}			
							}
							}				
							}				
						}//end else			
					}	//end for						
				}//end fucntion
				return true;
			}
			
			function loadBxxz(){
				var bxgsdm = document.getElementById("bxgsdm").value;
					getInsureInfo.getBxxzByBxgs(bxgsdm,function(data){
					var cellMuster=[
					function(data){return data.bxxzdm},
					function(data){return data.bxxzmc}
					];
					if (data != null && typeof data == 'object') {
						if ($("bxxzdm").tagName.toLowerCase() =="select"){
							DWRUtil.removeAllOptions("bxxzdm");			
							DWRUtil.addOptions("bxxzdm",data,"bxxzdm","bxxzmc");	
						}
					}else{
						showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
					}
				});
			}
		</script>
	</head>
	<body onload="checkWinType();dataManLoad();">
		<html:form action="/data_search" method="post">
		<input type="hidden" id="doType"/>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("�������ѧ����Ч!");
    </script>
				</logic:equal>
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/sjcz/lpxxb.jsp" />
				<input type="hidden" id="tableName" name="tableName" value="view_xsjbxx"/>
			<input type="hidden" id="lx" name="lx" value="ѧ��"/>
			
			
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣά��</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<logic:equal value="10865" name="xxdm">
									<button type="button" class="button2"
										onclick="if(checkXnNd('xn','nd')&&checkRule())dataDoSave('xh-nd-bxgsdm-slrq');"
										id="buttonSave">
										�� ��
									</button>
									</logic:equal>
									<logic:notEqual value="10865" name="xxdm">
									<button type="button" class="button2"
										onclick="if(checkXnNd('xn','nd')){dataDoSave('xh-nd-bxgsdm-slrq');}"
										id="buttonSave">
										�� ��
									</button>
									</logic:notEqual>
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
							<th>
								<font color="red">*</font>ѧ��
							</th>
							<td align="left">
								<html:text name='rs' property="xh" readonly="readonly"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do','800','600')"
									class="btn_01" id="buttonFindStu" style="">
									ѡ��
								</button>
							</td>
							<th>
								<font color="red">*</font>���
							</th>
							<td align="left">
								<html:select name="rs" property="nd" style="width:90px"
									styleId="nd">
									<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" />
							</td>
							<th>
								<font color="red">*</font>ѧ��
							</th>
							<td align="left">
								<html:select name="rs" property="xn" style="width:90px"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" />
							</td>
							<th>
								ѧ��
							</th>
							<td align="left">
								<html:select name="rs" property="xq" style="width:90px"
									styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								�꼶
							</th>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" />
							</td>
							<th>
								<font color="red">*</font>���չ�˾
							</th>
							<td align="left">
								<html:select name="rs" property="bxgsdm" style="width:150px"
									styleId="bxgsdm" onchange="loadBxxz();">
									<html:option value=""></html:option>
									<html:options collection="bxgsList" property="bxgsdm"
										labelProperty="bxgsmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								ѧ��
							</th>
							<td align="left">
								<html:text name='rs' property="xz" styleId="xz" />
							</td>
							<th>
								<font color="red">*</font>��������
							</th>
							<td align="left">
								<html:select name="rs" property="bxxzdm" style="width:150px"
									styleId="bxxzdm">
									<html:option value=""></html:option>
									<html:options collection="bxxzList" property="bxxzdm"
										labelProperty="bxxzmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" />
							</td>
							<th>
								������
							</th>
							<td align="left">
								<html:text name='rs' property='lpje' onkeyup="value=value.replace(/[^\d]/g,'') " style="width:90px" maxlength="8"/>(Ԫ)
							</td>
						</tr>
						<tr>
							<th>
								רҵ
							</th>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" />
							</td>
							<th>
								<font color="red">*</font>��������
							</th>
							<td align="left">
								<html:text name='rs' property="slrq" styleId="slrq"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('slrq','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<th>
								�༶
							</th>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" />
							</td>
							<th>
								����ʱ��
							</th>
							<td align="left">
							<%--�Ϻ����̼�����ѧ--%>
							<logic:equal value="10856" name="xxdm">
								<html:select property="dzsj" styleId="dzsj" name="rs">
									<html:option value=""></html:option>
									<html:option value="���չ�˾������">���չ�˾������</html:option>
									<html:option value="�ѵ��ʣ��뾡�쵽ѧ������ȡ">�ѵ��ʣ��뾡�쵽ѧ������ȡ</html:option>
									<html:option value="�������">�������</html:option>
									</html:select>
							</logic:equal>
							<logic:notEqual value="10856" name="xxdm">
								<html:text name='rs' property="dzsj" styleId="dzsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('dzsj','y-mm-dd');" />
								</logic:notEqual>	
							</td>
						</tr>
						
						<%--�Ϻ����̼�����ѧ--%>
						<logic:equal value="10856" name="xxdm">
						<input type= "hidden" id="bxxzNum" name="bxxzNum" value="<bean:write name="bxxzNum"/>"/>						
							
							<td colspan="4">
							<table class="tbstyle" align="center" width="100%">	
							
							<logic:empty name="bxfyList">
							<logic:iterate id="bxxz" indexId="in" name="bxxzList">
							<tr>
							<th>	
								��������							
							</th>
							<td align="left" width="120px">
								<select name="bxxzdm${in}" style="width:120px">	
								<option></option>
								 <logic:iterate id="bxxz" name="bxxzList" indexId="index">
								 	<option value="${bxxz.bxxzdm}">${bxxz.bxxzmc}</option>
								 </logic:iterate>			
								</select>
							</td>
							<th>
								���ѷ���
							</th>
							<td align="left">
<%--								<html:text name='rs' property="hffy" styleId="hffy${index}" />(Ԫ)--%>
								<input type="text" name="hffy${in}" value=""/>(Ԫ)
							</td>
							<th>
								������
							</th>
							<td align="left">
								<input type="text" name="lpje${in}" value=""/>(Ԫ)
							</td>							
							</tr>
							</logic:iterate>
							</logic:empty>
							
							<logic:notEmpty name="bxfyList">
							<input type="hidden" id="bxfyNum" value="${bxfyNum}"/>
							<logic:iterate id="info" name="bxfyList" offset="0" length="${bxfyNum}" indexId="in">
							<tr>
							<th>	
								��������						
							</th>
							<td align="left" width="160px">
								<select id="bxxzdm${in}" name="bxxzdm${in}" style="width:160px">	
								<option></option>
								 <logic:iterate id="bxxz" name="bxxzList" indexId="index">
								 	<option value="${bxxz.bxxzdm}">${bxxz.bxxzmc}</option>								 	
								 </logic:iterate>			
								</select>
								<input type="hidden" id="bxxzdmV${in}" name="bxxzdmV${in}" value="${info.bxxzdm}"/>					
							</td>
							<th>
								���ѷ���
							</th>
							<td align="left">
								<input type="text" name="hffy${in}" value=""/>(Ԫ)
								<input type="hidden" id="hffyV${in}" value="${info.hffy}" />	
							</td>	
							<th>
								������
							</th>
							<td align="left">
								<input type="text" name="lpje${in}" value="" />(Ԫ)
								<input type="hidden" id="lpjeV${in}" value="${info.lpje}" />	
							</td>						
							</tr>
							</logic:iterate>
							<script>
							   var num = document.getElementById("bxfyNum").value;
							   for(var i=0;i<parseInt(num); i++){
							    document.getElementById("bxxzdm" + i).value = document.getElementById("bxxzdmV" + i).value;
							    document.getElementById("hffy" + i).value = document.getElementById("hffyV" + i).value;
							    document.getElementById("lpje" + i).value = document.getElementById("lpjeV" + i).value;
							   }								
							</script>
							<logic:iterate id="info" name="bxxzList" length="${bxxzNum-bxfyNum}" indexId="in">
							<tr>
							<th>	
								��������					
							</th>
							<td align="left" width="160px">		
								<select name="bxxzdm${in+bxfyNum}" style="width:160px">	
								<option></option>
								 <logic:iterate id="bxxz" name="bxxzList" indexId="index">
								 	<option value="${bxxz.bxxzdm}">${bxxz.bxxzmc}</option>
								 </logic:iterate>			
								</select>						
							</td>
							<th>
								���ѷ���
							</th>
							<td align="left">
								<input type="text" name="hffy${in+bxfyNum}"/>(Ԫ)
							</td>
							<th>
								������
							</th>
							<td align="left">
								<input type="text" name="lpje${in}" value="" onkeyup="value=value.replace(/[^\d]/g,'') "/>(Ԫ)
							</td>							
							</tr>
							</logic:iterate>
							</logic:notEmpty>												
							</table>
							</td>							
							<tr align="left">
							<th>
								ѧУ������
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea name='rs' property='xxshyj' style="width:99%" rows='4' styleId="xxshyj"/>
							</td>
						</tr>
						</logic:equal>		
						<%--��ɳ����--%>
						<logic:equal value="10827" name="xxdm">
							<tr>
							<th>
								���ѷ���
							</th>
							<td align="left" colspan="3"><html:text property="hffy" name="rs" maxlength="8" onkeyup="value=value.replace(/[^\d]/g,'') "/>(Ԫ)
							</td>
							</tr>
							</logic:equal>					
						<tr>
							<th>
								����ԭ��
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea name='rs' property='bz' style="width:99%" rows='4' />
							</td>
						</tr>
						<tr>
							<th>
								��ע
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea name='rs' property='bz' style="width:99%"
									rows='4' />
							</td>
						</tr>
						</tbody>
					</table>
				</div>
			</logic:notEmpty>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert('�����ɹ�');
				if (window.dialogArguments) {
					close();
					if(window.dialogArguments.document.getElementById("isPage")){
							window.dialogArguments.document.getElementById("isPage").value="yes";
					}
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
		</logic:equal>
	</body>
