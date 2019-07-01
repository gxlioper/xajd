<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/xsgyglFunction.js"></script>
		<script type="text/javascript">	
		function onShow(){
			var lddm = document.getElementById("lddm").value;
			var qsh  = document.getElementById("qsh").value;
			var cwh  = document.getElementById("cwh").value;
			var yzsscw = lddm+qsh+cwh;
			$("yzsscw").value=yzsscw;
		}
		
		function getStuInfo(){
			
			var tableName = $("tableName").value;
			var colList = ["xh","xm","xb","nj","xymc","zymc","bjmc","ssbh","cwh","rzrq","sfbz"];
			var pk = "xh";
			var pkValue = $("sendxh").value;
		
			getOtherData.getTableInfo(tableName,colList,pk,	pkValue,"",function(data){
				if( data != null && data.length > 0){
					for (i = 0; i < colList.length; i++) {
						var id = colList[i];
						var nr = "";
						if(data[i] !="" && data[i] != null){
							nr = data[i];
						}
						
						if(id=="ssbh"){
							$("ydqssbh").value = nr;
						}if(id=="cwh"){
							$("ydqcwh").value = nr;
						}if(id=="rzrq"){
							$("ydqrzsj").value = nr;
						}if(id=="sfbz"){
							$("ydqsfbz").value = nr;
						}else if($(id)){
							$(id).value = nr;
						}
					}
				}
			});
		}
		
		function getXsxxInfo(){
			var url = jQuery('#url').val();
			url+="&xh="+jQuery('#xh').val();
			jQuery('form')[0].action = url;
			jQuery('form')[0].submit();
		}
		
		</script>
	</head>
	
	<body onload="checkWinType();onShow();getStuInfo()">	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ������� - �����춯</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/data_search" method="post">
			<logic:notEmpty name="rs">
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="userOnLine" name="userOnLine"
					value="<bean:write name="userOnLine" scope="session"/>" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-nj-xymc-zymc-bjmc" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-nj-xymc-zymc-bjmc" />
<%--				<input type="hidden" id="url" name="url" value="/sjcz/ssydxxb.jsp" />	--%>
				<input type="hidden" name="yzsscw" id="yzsscw" value=""/>			
				
				<!-- ѧ����ѯ -->
				<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-xydm-xymc-zymc-bjmc"/>
				<input type="hidden" id="url" name="url" value="/xgxt/modiData.do?realTable=ssydxxb&doType=add&tableName=view_ssydxx&pk=xh||ydsj"/>
				<input type="hidden" id="tableName" name="tableName" value="view_xszsxx"/>
				<input type="hidden" id="lx" name="lx" value="ѧ��ס��"/>
				<input type="hidden" id="mklx" name="mklx" value="gygl"/>
				<input type="hidden" id="sendxh" name="sendxh" value="${sendxh }"/>
				
				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<th colspan="4">
								<span>ס���춯���</span>
							</th>
						</tr>
					</thead>
					<tbody>										
					<tr>
						<th align="right" style="width: 100">
							<font color="red">*</font>ѧ��
						</th>
						<td align="left">
							<html:text name='rs' property="xh" 
								styleId="xh" onkeypress="if(event.keyCode==13){getXsxxInfo();}"
								onblur="chkIsStu(this,'view_xsjbxx','xh')" />
							<logic:equal name="doType" value="add">
								<button type="button" onclick="sendXx();return false;"
									class="btn_01" id="buttonFindStu" style="" >
									ѡ��
								</button>
							</logic:equal>
						</td>
						<th align="right" style="width: 70">
							����
						</th>
						<td align="left" >
							<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
						</td>						
					</tr>					
					<tr>
						<th align="right">
							�Ա�
						</th>
						<td align="left">
							<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
						</td>
						<th align="right">
							<font color="red">*</font>ѧ��
						</th>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="xn" readonly="true"></html:text>
							</logic:notEqual>
							<logic:equal value="add" name="doType">
								<html:select name="rs" property="xn" style="width:100px" styleId="xn" >
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</logic:equal>
						</td>											
					</tr>
					<tr>
						<th align="right">
							�꼶
						</th>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" readonly="true"/>
						</td>
						<th align="right">
							<font color="red">*</font>ѧ��
						</th>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:hidden name="rs" property="xq"/>
								<html:text name="rs" property="xqmc" readonly="true"/>
							</logic:notEqual>							
							<logic:equal value="add" name="doType">
								<html:select name="rs" property="xq" style="width:100px" styleId="xq">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</logic:equal>
						</td>				
					</tr>					
					<tr>
						<th align="right">
							<bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td align="left">
							<html:text name='rs' property="xymc" styleId="xymc" readonly="true"/>
						</td>
						<th align="right">
							<font color="red">*</font>�춯��¥������
						</th>
						<td align="left">											
<%--								<html:select name="rs" property="lddm" style="width:150px" styleId="lddm" onchange="GetQshList()">--%>
<%--									<html:option value="">--��ѡ��--</html:option>--%>
<%--									<html:options collection="ldList" property="lddm" labelProperty="ldmc" />--%>
<%--								</html:select>								--%>
							<input type="hidden" name="lcV" id="lcV" value="" />
							<html:select name="rs" property="lddm" style="width:120px" styleId="lddm"
								onchange="getLcList()">
								<html:options collection="ldList" property="lddm"
									labelProperty="ldmc" />
							</html:select>
						</td>				
					</tr>
					<tr>
						<th align="right">
							רҵ
						</th>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zymc" readonly="true"/>
						</td>
						<th align="right">
							<font color="red">*</font>�춯ʱ��									
						</th>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="ydsj" value="${rs.ydsj}" readonly="true"></html:text>
							</logic:notEqual>							
							<logic:equal value="add" name="doType">
								<html:text name="rs" property="ydsj" readonly="true" onblur="dateFormatChg(this)"
									onclick="return showCalendar('ydsj','y-mm-dd');" style="cursor:hand "/>
							</logic:equal>		
						</td>			
					</tr>
					<tr>
						<th align="right">
							�༶
						</th>
						<td align="left">
							<html:text name='rs' property="bjmc" styleId="bjmc" readonly="true"/>
						</td>
						<th align="right">
							�춯����
						</th>
						<td align="left">
							<html:text name="rs" property="ydly" styleId="ydly"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							�춯ǰ�����
						</th>
						<td align="left">
							<html:text name="rs" property="ydqssbh" readonly="true" styleId="ydqssbh" />
						</td>
						<th align="right">
<%--							<font color="red">*</font>�춯�����Һ�--%>
                                <font color="red">*</font>�춯��¥��
						</th>
						<td align="left">
<%--							<input type="hidden" name="qshV" value=""/>--%>
<%--							<html:select name="rs" property="qsh" style="width:110px" onchange="GetCwhList()">								--%>
<%--								<html:options collection="ssList" property="dm"--%>
<%--									labelProperty="mc" />--%>
<%--							</html:select>--%>
                            <input type="hidden" name="qshV" id="qshV" value="" />
							<html:select name="rs" property="lc" style="width:80px" styleId="lc"
								onchange="getQshList2()">
								<html:options collection="lcList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>	
					</tr>
					<tr>
						<th align="right">
							�춯ǰ��λ
						</th>
						<td align="left">
							<html:text name="rs" property="ydqcwh" readonly="true" styleId="ydqcwh"/>
						</td>
						<th align="right">
							<font color="red">*</font>
                             �춯������
						</th>
						<td align="left">
						<input type="hidden" name="cwhV" value="" />
							<html:select name="rs" property="qsh" style="width:80px" styleId="qsh" onchange="GetCwhList()">
								<html:options collection="qshList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="right">
							�춯ǰ��סʱ��
						</th>
						<td align="left">
							<html:text name="rs" property="ydqrzsj" readonly="true" styleId="ydqrzsj"/>	
						</td>
						<th align="right">
							<font color="red">*</font>
                             �춯��λ
						</th>
						<td>
					
							<html:select name="rs" property="cwh" style="width:130px"
								styleId="cwh" onchange="getRzQsXsXx()">
								<html:options collection="cwhList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="right">
							�춯ǰ�շѱ�׼
						</th>
						<td align="left">
							<html:text name="rs" property="ydqsfbz" readonly="true"/>
						</td>
						<th align="right">
							�춯���շѱ�׼
						</th>
						<td>
							<html:text name="rs" property="ydhsfbz" readonly="true"/>
						</td>
					</tr>
					<tr>
						<th align="right">
							��ע	
							<br/>
							<font color="red">��¼��100��</font>					
						</th>
						<td align="left" colspan="3">
							<textarea rows="5" cols="76" name="bz" id="bz" type="_moz" onblur="chLeng(this,100)">${rs.bz}</textarea>			
						</td>					
					</tr>
					</tbody>
					<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="btn">
								<logic:notEqual name="doType" value="view">
								<logic:equal value="notIn" name="message">
									<button type="button" onclick="alert('��ѧ����δס�ޣ��޷����������ƶ�!');"
										id="buttonSave">
										�� ��
									</button>
								</logic:equal>
								<logic:notEqual value="notIn" name="message">
									<button type="button" 
										onclick="toSave();return false;"
										style="width:80px;" id="buttonSave">
										�� ��
									</button>
								</logic:notEqual>
								&nbsp;&nbsp;
								</logic:notEqual>
								<button type="button" id="buttonClose" onclick="Close();return false;"
									style="width: 80px">
									��	��
								</button>
							</div>
						</td>
					</tr>
					</tfoot>				
				</table>
				<table width="100%" class="tbstyle" >
				<tbody id="rsTables">
				</tbody>
				</table>
			</logic:notEmpty>		
		</html:form>
		<logic:equal value="view" name="result">
		    <logic:equal value="notIn" name="message">
			<script>
				alert("��ѧ����δס�ޣ��޷����������ƶ�!");
			</script>
		    </logic:equal>
		</logic:equal>
		<logic:present name="isSuccess">
				<logic:equal value="true" name="isSuccess">
					<script>
						alert('�����ɹ���');
						dialogArgumentsQueryChick();
						window.close();
					</script>
				</logic:equal>
				<logic:equal value="false" name="isSuccess">
					<script>
						alert('����ʧ�ܣ�');
					</script>
				</logic:equal>
			</logic:present>
  </body>
</html>
