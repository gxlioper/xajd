<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/xsgyglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript">
			function cffs(fs){
				document.getElementById("fs").value=fs;
			}     
	      function zsjlSave(mustFill){ 
  	        var cljg = "";
	        var bz   = "";
	        var wjsy = "";
	        if($("bz")){
	           bz = $("bz").value;
	        }	  
	        if($("cljg")){
	           cljg = $("cljg").value;
	        }	
	        if($("wjsy")){
	           wjsy = $("wjsy").value;
	        }	
	        if(wjsy.length>100){
	            alert("��������������100���ڣ�");
	            return false;
	        }	      
	        if(cljg.length>100){
	            alert("����������������100���ڣ�");
	            return false;
	        }	      
	        if(bz.length>100){
	            alert("��ע����������100���ڣ�");
	            return false;
	        }	      
	           var eles = mustFill.split("-");
	           for (i = 0; i < eles.length; i++) {
	               if($(eles[i])!=null||$(eles[i])){
		             if ($(eles[i]).value == "") {
			          alert("�뽫��\"*\"�ŵ���Ŀ����������");
			          return false;
		             }
		           }
	           }
	           if($("doType").value == "add"){	         
	           var saveType = document.getElementById("saveType").checked;         
		           if(saveType){
		               if(confirm("��ȷ�ϱ�����Ϣ��")){
		                  dataDoSave(mustFill);
		               }else{
		                  return false;
		               }  
		           }else{
		               dataDoSave(mustFill);
		           }
	           }else{
					dataDoSave(mustFill);
	           }
	      }
	      function showXsxx(){
	           if(document.getElementById("saveType").checked){
	               document.getElementById("rsxsxx").style.display="";
	              getRzXsXx1();
	           }else{
	               document.getElementById("rsxsxx").style.display="none";
	           }
	      }
	      
	function getRzXsXx1(){
	var ssbh = "";
	var xh = $("xh").value;
	if($("ssbh")){
	  ssbh = $("ssbh").value;
	}else{
	  ssbh = $("lddm").value+"-"+$("qsh").value;
	}
	GetListData.GetQsRzXsXxHH(ssbh,xh,TjRzSsXsXx1);
	
	}
	
	function cheXs(xsxx){
		alert(xsss);
	}
function TjRzSsXsXx1(data){
	var xx="";
	var cellfu =[function(data) {return data;}];
	if (data != null && typeof data == 'object') {
		if ($("rsTables").tagName.toLowerCase() =="tbody"){
			DWRUtil.removeAllRows("rsTables");		
			//DWRUtil.addRows("rsTables",data,cellfu,{escapeHtml:false});	
			for(var i=0;i<data.length;i++){
			var xsxx=data[i].replace("&nbsp;","").replace("&nbsp;","").replace("&nbsp;","").replace("&nbsp;","").replace("&nbsp;","").replace("&nbsp;","");
			var che = document.createElement("input");
				che.type="checkbox";
				che.id = "che" + i;
				che.name="che" + i;
				che.value = xsxx;
			DWRUtil.addRows('rsTables',['dd'],[che,xsxx]);	
			$(che.id).onclick=function(){
				xx = xx.replace(this.value,"") + this.value + splitSignOne;
				document.getElementById("xsxx").value = xx;	
				}
			}
			}	
			if($("rsNum")){
			   $("rsNum").innerText = data.length;
			}
			if($("ldqs")){
			  var ldmc=($("ldmc"))?$("ldmc").value:document.forms[0].lddm.options[document.forms[0].lddm.selectedIndex].text;
			  $("ldqs").innerText = ldmc+$("qsh").value;
			}
		}else{
			showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
		}
}
		</script>
	</head>

	<body onload="checkWinType();">	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ��Ϣά�� - ס�޼��� </a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/data_search" method="post">
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-nj-xymc-zymc-bjmc" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-nj-xymc-zymc-bjmc" />
		        <input type="hidden" id="url" name="url" value="/modiData.do?realTable=zsjlb&tableName=view_zsjlxx&pk=xh-xn&doType=add" />		        			
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="request"/>" />
				<input type="hidden" id="xxdm" name="xxdm"
					value="<bean:write name="xxdm" scope="request"/>" />
				
				<input type="hidden" id="userOnLine" name="userOnLine"
					value="<bean:write name="userOnLine" scope="session"/>" />
				
				<html:hidden name='rs' property="xsxx" styleId="xsxx" />
				<input type="hidden" id="ssbh" name="ssbh"
					value="${rs.ssbh}" />
				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<th colspan="4">
								<span>ס�޼���</span>
							</th>
						</tr>
					</thead>
					<tbody>				
					<tr>
						<th align="right">
							<font color="red">*</font>ѧ�ţ�
						</th>
						<td align="left">
							<logic:equal name="doType" value="add">
								<html:text name='rs' property="xh" readonly="readonly" onblur="dctStuXh()"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_LdQsInfo.do',750,550);"
									class="btn_01" id="buttonFindStu" style="" >	
									ѡ��				
								</button>
							</logic:equal>
							<logic:notEqual name="doType" value="add">
								<html:text name='rs' property="xh" 
									readonly="true" onblur="dctStuXh()"
									styleId="xh"/>
							</logic:notEqual>
						</td>
						<th align="right">
							������
						</th>
						<td align="left">
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
							<font color="red">*</font>ѧ�꣺
						</th>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="xn" value="${rs.xn}" readonly="true"></html:text>
							</logic:notEqual>
							<logic:equal value="add" name="doType">
								<html:select name="rs" property="xn" style="width:90px" styleId="xn" >
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</logic:equal>
						</td>											
					</tr>
					<tr>
						<th align="right">
							�꼶��
						</th>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" readonly="true"/>
						</td>
						<th align="right">
							<font color="red">*</font>ѧ�ڣ�
						</th>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:hidden name="rs" property="xq" value="${rs.xq}"/>
								<input type="text" value="${rs.xqmc}" readonly="true"/>
							</logic:notEqual>							
							<logic:equal value="add" name="doType">
								<html:select name="rs" property="xq" style="width:90px" styleId="xq">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</logic:equal>
						</td>				
					</tr>					
					<tr>
						<th align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</th>
						<td align="left">
							<html:text name='rs' property="xymc" styleId="xymc" readonly="true"/>
						</td>
						<th align="right">
							<font color="red">*</font>¥�����ƣ�
						</th>
						<td align="left">
						   <html:hidden name="rs" property="lddm" value="${rs.lddm}"></html:hidden>
							<logic:notEqual value="add" name="doType">
								<html:text name='rs' property="ldmc" styleId="ldmc" readonly="true" />									
							</logic:notEqual>
							<logic:equal value="add" name="doType">	
							<html:text name='rs' property="ldmc" styleId="ldmc" readonly="true" />													    
<%--								<html:select name="rs" property="lddm" style="width:150px" styleId="lddm" onchange="GetQshList()">--%>
<%--									<html:option value=""></html:option>--%>
<%--									<html:options collection="ldList" property="lddm" labelProperty="ldmc" />--%>
<%--								</html:select>--%>
							</logic:equal>
						</td>				
					</tr>
					<tr>
						<th align="right">
							רҵ��
						</th>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zymc" readonly="true"/>
						</td>
						<th align="right">
							<font color="red">*</font>���Һţ�
						</th>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="qsh" styleId="qsh" value="${rs.qsh}" readonly="true"></html:text>
							</logic:notEqual>
							<logic:equal value="add" name="doType">
							<html:text name="rs" property="qsh" styleId="qsh"  readonly="true"/>													
<%--								<input type="hidden" name="qshV" value=""/>--%>
<%--								<input type="hidden" name="rs" property="qsh"/>--%>
<%--								<html:select name="rs" property="qsh" style="width:110px">--%>
<%--									<html:option value=""></html:option>--%>
<%--									<html:options collection="ssList" property="qsh"--%>
<%--										labelProperty="qsh" />--%>
<%--								</html:select>--%>
							</logic:equal>
						</td>			
					</tr>
					<tr>
						<th align="right">
							�༶��
						</th>
						<td align="left">
							<html:text name='rs' property="bjmc" styleId="bjmc" readonly="true"/>
						</td>
						<th align="right">
							<font color="red">*</font>(����)ʱ�䣺									
						</th>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="wjsj" value="${rs.wjsj}" readonly="true"></html:text>
							</logic:notEqual>							
							<logic:equal value="add" name="doType">
								<html:text name="rs" property="wjsj" readonly="true" onblur="dateFormatChg(this)"
									onclick="return showCalendar('wjsj','y-mm-dd');" style="cursor:hand "/>
							</logic:equal>		
						</td>
					</tr>
					<logic:present name="showhzy">
						<tr>
							<th align="right">
								���ţ�
							</th>
							<td align="left">
								<html:text name="rs" property="cwh" styleId="c" readonly="true"/>
							</td>
							<td align="right">
							</td>
							<td align="left">
							</td>
						</tr>
					</logic:present>
						<tr>
							<th align="right">
								<logic:notEqual name="xxdm" value="11641">
									<font color="red">*</font>�������
								</logic:notEqual>
								<logic:equal name="xxdm" value="11641">
									<font color="red">*</font>Υ��ԭ��
								</logic:equal>
							</th>
							<td align="left"  colspan="3">
								<html:select name="rs" property="wjlbdm" style="width:150px"
									styleId="wjlb">
									<html:options collection="wjlbList" property="wjlbdm"
										labelProperty="wjlbmc" />
								</html:select>
								&nbsp;&nbsp;
								<!--<logic:notEqual name="xxdm" value="11641">
								<input type="checkbox" name="saveType" value="more" title="�������" onclick="showXsxx()"/>
							    ����ȫ���Ա&nbsp;&nbsp;&nbsp;&nbsp;
							    <span><font color="blue">ѡ�м�Ϊ������ȫ���Ա��������������Ϣ</font></span>						    
							   	</logic:notEqual>-->
							   	<logic:equal name="doType" value="add">
								<input type="checkbox" name="saveType" value="more" title="�������" onclick="showXsxx()"/>
							    ���ҳ�Ա
							    </logic:equal>
							</td>							
						</tr>
					<logic:equal name="doType" value="add">
					<tr id="rsxsxx" style="">
						<td colspan="4">
						    <fieldset>
									<legend>
									    <span id="ldqs"></span>ͬ���������ˣ���<span id="rsNum"></span>��
									</legend>
							<table width="100%" class="tbstyle">
								
								<tbody id="rsTables">
								  
								</tbody>
								
							</table>
							</fieldset>
						</td>
					</tr>
					</logic:equal>
					<tr>
						<th align="right">
							<font color="red">*</font>���ɣ�							
							<br>
							<span style="color: red">
							<��100��>	</span>										
						</th>
						<td align="left" colspan="3">
							<textarea rows="5" cols="78" name="wjsy" id="wjsy" type="_moz">${rs.wjsy}</textarea>			
						</td>					
					</tr>
					<logic:equal name="xxdm" value="11641">
					<tr>
						<th align="right">
							<font color="red">*</font>���ֵȼ���									
						</th>
						<td align="left">
							<html:select name="rs" property="yjfs" style="width:150px"
								styleId="yjfs" onchange="cffs(this.value);">
								<html:option value=""></html:option>
								<html:options collection="cfdjList" property="cfdjfs"
									labelProperty="cfdjmc" />
							</html:select>	
						</td>	
						<th align="right">
							Ӧ��������									
						</th>
						<td align="left">
							<html:text name="rs" property="fs" styleId="fs" readonly="true"/>
						</td>				
					</tr>
					</logic:equal>
					<tr>
						<th align="right">
							��������							
							<br>
							<span style="color: red">
							<��100��>	</span>										
						</th>
						<td align="left" colspan="3">
							<textarea rows="5" cols="78" name="cljg" id="cljg" type="_moz">${rs.cljg}</textarea>			
						</td>					
					</tr>
					<tr>
						<th align="right">
							��ע��							
							<br>
							<span style="color: red">
							<��100��>	</span>										
						</th>
						<td align="left" colspan="3">
							<textarea rows="5" cols="78" name="bz" id="bz" type="_moz">${rs.bz}</textarea>			
						</td>					
					</tr>
					</tbody>
					<tfoot>
						<td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
							<div class="btn">
								<logic:notEqual name="doType" value="view">
									<button type="button" id="buttonSave" onclick="zsjlSave('xh-xn-xq-lddm-qsh-fs-wjsj-wjlbdm-wjsy')"
										style="width: 80px">
										��	��
									</button>
								</logic:notEqual>
								&nbsp;&nbsp;
								<button type="button" id="buttonClose" onclick="Close();return false;"
									style="width: 80px">
									��	��
								</button>
							</div>
						</td>
					</tfoot>
				</table>
			</logic:notEmpty>	
			<logic:equal value="true" name="isSuccess">
			  <script type="text/javascript">
			    alert('�����ɹ���');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>	
			<logic:equal value="false" name="isSuccess">
			  <script type="text/javascript">
			    alert('����ʧ�ܣ�');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>
		</html:form>
  </body>
	<logic:equal value="notIn" name="message">
		<script>
			alert("��ѧ��Ŀǰ��δס�ޣ�");
		</script>
	</logic:equal>	
</html>
