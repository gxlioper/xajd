<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script type="text/javascript">
		function GetCwhList(){
			var lddm = document.getElementById("lddm").value;
			var qsh = document.getElementById("qsh").value;
			var ssbh=lddm+"-"+qsh;
			GetListData.GetCwhList(ssbh,initTjList);
		}
		function GetSsbh(){
			var lddm = document.getElementById("lddm").value;
			var qsh = document.getElementById("qsh").value;
			var cwh = document.getElementById("cwh").value;
			var ssbh ="";
			if(lddm!=""&&qsh!=""&&cwh!=""){
				ssbh=lddm+"-"+qsh +"-"+cwh;
				document.getElementById("ssbh").value=ssbh;
			}	
		}
		function hh_jqlxsave(){
			if($("bz").value.length>200){
				alert("��ע�������ܴ���200�֣�");
				return false;		      
			}		      		      
                   
				var xn = $("xn").value;
				var xq = $("xq").value;
				var xh = $("xh").value; 
				var ssbh = $("ssbh").value;              
				pkV = xn+xq+xh;
				var doType = $("doType").value;
				if(ssbh!=""&&xh!=""){
					if(confirm("ȷ��Ҫ���棿\n\n���\"ȷ��\"��������Ϣ��\n���\"ȡ��\"�����������棡")){
					Savedata('xh-ssbh','holiday_lsxx_save.do');
				}
				}else{
					alert("��ȷ�������Ŀ��д����");
				}
		}
		function showSs(){
		 	var doType = document.getElementById("doType").value;
		 	var xxdm = document.getElementById("xxdm").value;
		 	if(xxdm=="11641"){
		 	if(doType == "view"){
		 		document.getElementById("editss").style.display="none";
		 		document.getElementById("editcw").style.display="none";
		 		document.getElementById("edityss").style.display="none";
		 		document.getElementById("editbtn").style.display="none";
		 		document.getElementById("viewss").style.display="";
		 	}
		 	if(doType == "modi"){
		 		document.getElementById("editss").style.display="none";
		 		document.getElementById("editcw").style.display="none";
		 		document.getElementById("edityss").style.display="none";
		 		document.getElementById("viewss").style.display="";
		 		var ssbh = $("ssbhV").value.split("��¥");
		 		var qsh = ssbh[1].split("/");
		 		document.getElementById("ssbh").value=ssbh[0]+"-"+qsh[0]+"-"+qsh[1].replace("��","");
		 	}
		 	if(doType == "add"){
		 		document.getElementById("editss").style.display="";
		 		document.getElementById("editcw").style.display="";
		 		document.getElementById("edityss").style.display="";
		 		document.getElementById("viewss").style.display="none";
		 	}
		 	}
		}
		function showTr(){
			document.getElementById("editss").style.display="";
		 	document.getElementById("editcw").style.display="";
		 	document.getElementById("edityss").style.display="";
		 	document.getElementById("viewss").style.display="none";
		}
		
		function setQsh(){
			var qsh = "";
			if($("qshV")){
				qsh = $("qshV").value;
				$("qsh").value = qsh;
			}
		}	
		
		
	</script>
	</head>

	<body onload="checkWinType();GetQshList();showSs();setQsh()">	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ������估���� - ������Уѧ����Ϣ</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/holiday_lsxx" method="post">
			
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
				<logic:equal name="xxdm" value="11641">	
				<input type="hidden" id="readonlyEle" name="readonlyEle" 
					value="lxdh-lxyy-zskssj-zsjssj-xn-xq-yssbh" />
				</logic:equal>
				<logic:notEqual name="xxdm" value="11641">	
				<input type="hidden" id="readonlyEle" name="readonlyEle" 
					value="lxyy-zskssj-zsjssj-xn-xq" />
				</logic:notEqual>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-nj-xymc-zymc-bjmc" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-nj-xymc-zymc-bjmc" />
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="request"/>" />
				<input type="hidden" id="xxdm" name="xxdm"
					value="<bean:write name="xxdm" scope="request"/>" />
				<input type="hidden" id="url" name="url" value="/holiday_lsxx.do?doType=add&pkValue=" />
				<input type="hidden" name="lcV" id="lcV" value=""/>
				<input type="hidden" name="cwhV" id="cwhV" value=""/>
				<input type="hidden" name="qshV" id="qshV" value="${rs.qsh }"/>
				<input type="hidden" name="ssbh" id="ssbh" />
				<input type="hidden" name="userOnLine" id="userOnLine" styleId="userOnLine" value="${userOnLine }">
				<!-- ������Уѧ����Ϣ -->
				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Уѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>						
					<tr>
						<td align="right" width="15%">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left">
							<logic:notEqual name="userOnLine" value="student">
							<html:text name='rs' property="xh" readonly="readonly" onblur="dctStuXh()"
								styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<logic:equal name="doType" value="add">
								<button onclick="showTopWin('/xgxt/stu_info.do?jq=jq',750,550);"
									class="btn_01" id="buttonFindStu" style="" >
									ѡ��
								</button>
							</logic:equal>
							</logic:notEqual>
							<logic:equal name="userOnLine" value="student">
							<html:text name="rs" property="xh" readonly="true" onblur="dctStuXh()"
								styleId="xh"  />
							</logic:equal>
						</td>
						<td align="right" width="20%">
							������
						</td>
						<td align="left">
							<html:text name='rs' property="xm" styleId="xm" />
						</td>						
					</tr>					
					<tr>
						<td align="right">
							�Ա�
						</td>
						<td align="left">
							<html:text name='rs' property="xb" styleId="xb" />
						</td>
						<td align="right">
							<font color="red">*</font>ѧ�꣺
						</td>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="xn" value="${rs.xn}" readonly="true"></html:text>
							</logic:notEqual>
							<logic:equal value="add" name="doType">
								<html:select name="rs" property="xn" styleId="xn" >
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</logic:equal>
						</td>											
					</tr>
					<tr>
						<td align="right">
							�꼶��
						</td>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" />
						</td>
						<td align="right">
							<font color="red">*</font>ѧ�ڣ�
						</td>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:hidden name="rs" property="xq" value="${rs.xq}"/>
								<input type="text" value="${rs.xqmc}" readonly="true"/>
							</logic:notEqual>							
							<logic:equal value="add" name="doType">
								<html:select name="rs" property="xq" styleId="xq">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</logic:equal>
						</td>				
					</tr>
					<logic:notEqual name="xxdm" value="11641">							
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							<html:text name='rs' property="xymc" styleId="xymc" />
						</td>
						<td align="right">
							<font color="red">*</font>¥�����ƣ�
						</td>
						<td align="left">
							<logic:equal value="12872" name="xxdm">
								<logic:notEqual value="add" name="doType">
								    <html:hidden name="rs" property="lddm"/>
									<html:text name="rs" property="ldmc" value="${rs.ldmc}"
										readonly="true"></html:text>
								</logic:notEqual>
								<logic:equal value="add" name="doType">
									<html:select name="rs" property="lddm" styleId="lddm"
										onchange="GetQshList()">
										<html:options collection="ldList" property="lddm"
											labelProperty="ldmc" />
									</html:select>
								</logic:equal>
							</logic:equal>
							<logic:notEqual value="12872" name="xxdm">
								<html:select name="rs" property="lddm" styleId="lddm"
									onchange="GetQshList()">
									<html:options collection="ldList" property="lddm"
										labelProperty="ldmc" />
								</html:select>
							</logic:notEqual>
						</td>
					</tr>
					<tr>
						<td align="right">
							רҵ��
						</td>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zymc" />
						</td>
						<td align="right">
							<font color="red">*</font>���Һţ�
						</td>
						<td align="left">
							<logic:equal value="12872" name="xxdm">
								<logic:notEqual value="add" name="doType">
									<html:text name='rs' property="qsh" styleId="qsh" readonly="true"/>
								</logic:notEqual>
								<logic:equal value="add" name="doType">
									<input type="hidden" name="qshV" id="qshV" />
									<html:select name="rs" property="qsh" styleId="qsh">
										<html:option value=""></html:option>										
										<html:options collection="ssList" property="qsh"
											labelProperty="qsh" />
									</html:select>
								</logic:equal>
							</logic:equal>
							<logic:notEqual value="12872" name="xxdm">
								<html:select name="rs" property="qsh"  styleId="qsh">
									<html:option value=""></html:option>
									<html:options collection="ssList" property="qsh"
										labelProperty="qsh" />
								</html:select>
							</logic:notEqual>
						</td>
					</tr>
					</logic:notEqual>
					<logic:equal name="xxdm" value="11641">	
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							<html:text name='rs' property="xymc" styleId="xymc" />
						</td>
						<td align="right">
							רҵ��
						</td>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zymc" />
						</td>	
					</tr>
					</logic:equal>
					<tr>
						<td align="right">
							�༶��
						</td>
						<td align="left">
							<html:text name='rs' property="bjmc" styleId="bjmc" />
						</td>
						<td align="right">
							<font color="red">*</font>��ϵ�绰��									
						</td>
						<td align="left">
							<html:text name="rs" property="lxdh" styleId="lxdh"/>	
						</td>
					</tr>
					<logic:equal name="xxdm" value="11641">		
					<tr id="viewss">
						<td align="right">
							��У���᣺
						</td>
						<td align="left">
							<html:text name="rs" property="ssbh" styleId="ssbhV"/>
							<button onclick="showTr()" id="editbtn" class="button2" >
								�޸�
							</button>	
						</td>
						<td align="right">
							ԭס�����ң�		
						</td>
						<td>
							<html:text name="rs" property="yssbh" styleId="yssbh"/>	
						</td>
					</tr>		
					<tr id="editss">
						<td align="right" rowspan="2">
							��У���᣺
						</td>
						<td align="left">
							&nbsp;&nbsp;¥����:
							<html:select property="lddm" style="width:120px"  styleId="lddm"
								onchange="getLcList()">
								<html:option value="">--��ѡ��--</html:option>
								<html:options collection="ldList" property="lddm"
								labelProperty="ldmc" />
								</html:select>
							</td>
							<td  colspan="2">
							&nbsp;&nbsp;¥��:
							<html:select property="lc" style="width:80px" styleId="lc"
								onchange="getQshList2()">										
								<html:options collection="lcList" property="dm"
								labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr id="editcw">
						<td align="left">
							&nbsp;&nbsp;���Һ�:
							<html:select property="qsh" style="width:80px" styleId="qsh"
							onchange="GetCwhList()">	
								<html:options collection="qshList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
						<td  colspan="2">
							&nbsp;&nbsp;��λ��:
							<html:select property="cwh" style="width:80px" styleId="cwh"
							onchange="GetSsbh()">	
								<html:options collection="cwhList" property="dm"
									labelProperty="mc" />
							</html:select>		
						</td>
					</tr>
					<tr id="edityss">
						<td align="right">
							ԭס�����ң�
						</td>
						<td align="left"  colspan="3">
							<html:text name="rs" property="yssbh" styleId="yssbh"/>	
						</td>
					</tr>
					</logic:equal>
					<tr>
						<td align="right">
							<font color="red">*</font>ס�޿�ʼʱ�䣺									
						</td>
						<td align="left">
							<html:text name="rs" property="zskssj" readonly="true" onblur="dateFormatChg(this)"
									onclick="return showCalendar('zskssj','y-mm-dd');" style="cursor:hand "/>	
						</td>
						<td align="right">
							<font color="red">*</font>ס�޽���ʱ�䣺								
						</td>
						<td align="left">
							<html:text name="rs" property="zsjssj" readonly="true" onblur="dateFormatChg(this)"
									onclick="return showCalendar('zsjssj','y-mm-dd');" style="cursor:hand "/>	
						</td>
					</tr>
					<tr>
						<td align="right">
							����ԭ��	
							<br><font color="blue">��200�ַ�</font>								
						</td>
						<td align="left" colspan="3">
							<textarea rows="5" cols="60" name="lxyy" id="lxyy" type="_moz"><bean:write name="rs" property="lxyy"/></textarea>			
						</td>					
					</tr>
					<tr>
						<td align="right">
							��ע��	
							<br><font color="blue">��200�ַ�</font>									
						</td>
						<td align="left" colspan="3">
							<textarea rows="5" cols="60" name="bz" id="bz" type="_moz"><bean:write name="rs" property="bz"/></textarea>			
						</td>					
					</tr>
					</tbody>
					<tfoot>
						<tr bgcolor="EEF4F9" align="center">
							<td colspan="4">
								<div class="btn">
									<logic:notEqual name="xxdm" value="11641">		
									<button  onclick="jqlxsave()" style="width:80px" id="buttonSave">
										�� ��
									</button>
									</logic:notEqual>
									<logic:equal name="xxdm" value="11641">	
									<button  onclick="hh_jqlxsave()" style="width:80px" id="buttonSave">
										�� ��
									</button>
									</logic:equal>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button  onclick="Close();return false;" style="width:80px" id="buttonClose">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</logic:notEmpty>		
		</html:form>
		<logic:equal value="ok" name="result">
			<script>
				alert("�����ɹ���");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("����ʧ�ܣ�");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<script type="text/javascript">
		function jqlxsave(){
		          if($("lxyy").value.length>200){
		           alert("��Уԭ���������ܴ���200�֣�");
		           return false;
		          }
		          if($("bz").value.length>200){
		            alert("��ע�������ܴ���200�֣�");
		            return false;		      
		          }		      		      
		           if(IsNoEmpty('xh-xn-xq-lxdh-zskssj-zsjssj-lddm-qsh')){                      
                    var xn = $("xn").value;
                    var xq = $("xq").value;
                    var xh = $("xh").value;                
                    pkV = xn+xq+xh;
                    var doType = $("doType").value;
                      getSztzData.getInfoEx("gygl_jqlxxxb","xn||xq||xh",pkV,"1=1",function(str){
		               if(str){		
		               	  if(checkSearchTj('zskssj','zsjssj')){         	
			                  if(confirm("��ѧ�ꡢѧ�ڡ���������У��Ϣ�Ѵ��ڣ�\n\nȷ��Ҫ���棿\n\n���\"ȷ��\"���������ݲ������Ѵ�����Ϣ��\n���\"ȡ��\"�����������ģ�")){
			                    Savedata('xh-xn-xq-lxdh-zskssj-zsjssj-lddm-qsh','holiday_lsxx_save.do');
	                            
			                  }else{
			                     return false;
			                  }	 
			               }else{
						  	return false;
						  }         			              
		               }else{
		               	  if(checkSearchTj('zskssj','zsjssj')){
								if(confirm("ȷ��Ҫ���棿\n\n���\"ȷ��\"��������Ϣ��\n���\"ȡ��\"�����������棡")){
				                    Savedata('xh-xn-xq-lxdh-zskssj-zsjssj-lddm-qsh','holiday_lsxx_save.do');
		                            	                  
				                  }else{
				                     return false;
				                  }	
						  }else{
						  	return false;
						  }
 		               }
                      }); 
                 } 
		}
		</script>
  </body>
</html>
