<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="/xgxt/dwr/interface/systemFunction.js"></script>
		<script>
			
			function yhqxExp(){
				var yhm = document.getElementById('topGroup').value;
				var gnmkdm = document.getElementById('powerTop').value;
				var prompt = "��������";//��ʾ��Ϣ 
				
				if(gnmkdm == 'null'){
					gnmkdm = '';
				}				
				if(yhm == 'null'){
					yhm = '';
				}
				
				if(yhm != null && yhm != "" && yhm != 'null'){//ѡ�����û�
					prompt += yhm + "�û���";
				}else{
					prompt += "�����û���";
				}
				
				if(gnmkdm != null && gnmkdm != "" && gnmkdm != 'all'){//ѡ���˹���
					prompt += document.getElementById('powerTop').options[document.getElementById('powerTop').selectedIndex].text + "ģ���µ�";
				}
				prompt += "Ȩ��,ȷ��������";
				
				if(confirm(prompt)){//ȷ����������
					window.open('expUser.do?yhm=' + yhm + '&gnmkdm=' + gnmkdm);
				}
			}
			
			function yhqxExpAll(){
				var prompt = "�������������û���Ȩ�ޣ�ȷ��������";//��ʾ��Ϣ 
				if(confirm(prompt)){//ȷ����������
					window.open('expUser.do?yhm=&gnmkdm=');
				}
			}
			
			function exportData(){
				var tmp_type = document.getElementsByName('userExp');
				var type = "";
				for(var i=0;i<tmp_type.length;i++){
					if(tmp_type[i].checked){
						type = tmp_type[i].value;
					}
				}
				if(type == '1'){
					yhqxExpAll();
				}
				if(type == '0'){
					yhqxExp();
				}
			}
		</script>
	</head>
	<body>
		<html:form action="/user_man" method="post">
				<input type="hidden" id="writeAble" name="writeAble"
					value="<bean:write name="writeAble" scope="request"/>" />
				<input type="hidden" id="sUserName" name="sUserName" value="<bean:write name="sUserName" scope="request"/>" />
			    
				<html:hidden property="userPower" styleId="allPower" />
				<html:hidden property="groupList" styleId="allGroup" />
				<html:hidden property="partList" styleId="allPart" />
				<html:hidden property="unitList" styleId="allUnit" />
				<input type="hidden" id="globlVar" name="globlVar" value="" />
<%--				�������ϴ�ѧ--%>
				<logic:equal name="xxdm" value="11417" scope="request">
					<input type="hidden" name="flag" id="flag" value="yes" />
				</logic:equal>
<%--				end�������ϴ�ѧ--%>
				<input type="hidden" id="xxdm" name="xxdm"
					value="<bean:write name="xxdm" scope="request"/>" />
				<input type="hidden" name="power" value="" />
				<input type="hidden" name="newGroupName" value="" />
		
				<div id="tempdiv"></div>
		
				<div class="tab_cur" id="title">
					<p class="location">
							<em>���ĵ�ǰλ��:</em><a>ϵͳά�� - Ȩ��ά�� - �û�ά��</a>
					</p>
				</div>
				
				<div class="tab">
				  <table width="100%" border="0" class="permissionlist">
				    <thead>
				    	<tr>
				        	<td colspan="3"><span>�û�Ȩ��ά��</span></td>
				        </tr>
				    </thead>
				      <tr>
				        <td valign="top" width="35%">
				        	<table width="100%">
				            <tr><td>
				        	<div class="filter" id="div_first_gnmk">
				        		<label>��ϵͳ</label>
<!--				        		<select name="gnmkmc" -->
<!--										onchange="loadSubPower()" id="powerTop">-->
<!--								</select>-->
				        		
				        	</div>
				        	<script language="javascript" defer="defer">
				        	
				        		//һ��Ϊ���Ͼ���ʦ...
								setTimeout("createFirstGnmkDiv()",500);
								
								//������һ�����ܲ˵�
								function createFirstGnmkDiv(){
									//·��
									var url = "general_xtwh_power.do?method=createFirstGnmkDiv";	
									//����
								 	var parameter = {};
									
									jQuery("#div_first_gnmk").load(
										url,
										parameter,
										function(){
											createSecondGnmkDiv();
										}
									);
								}
								
								//�����ڶ������ܲ˵�
								function createSecondGnmkDiv(){
									
									var gnmkdm = jQuery("#powerTop").val();
									var yhm = jQuery("#topGroup").val();
									
									//·��
									var url = "general_xtwh_power.do?method=createSecondGnmkDiv";	
									//����
								 	var parameter = {
								 		"str_gnmkdm":gnmkdm,
								 		"str_yhm":yhm
								 	};
									
									jQuery("#CNLTreeMenu2").load(
										url,
										parameter,
										function(){
										
										}
									);
								}
								
								//�����û����ܲ˵�
								function createYhGnmkDiv(){
									
									var yhm = jQuery("#topGroup").val();
									//·��
									var url = "general_xtwh_power.do?method=createYhGnmkDiv";	
									//����
								 	var parameter = {
								 		"str_yhm":yhm
								 	};
									
									jQuery("#div_yh_gnmk").load(
										url,
										parameter,
										function(){
										
										}
									);
								}
				        		</script>
				            <div class="CNLTreeMenu2" id="CNLTreeMenu2">
<!--				              	<select onmouseover="null"-->
<!--				              			name="gnmkdm" style="width:100%;"-->
<!--										size="26" id="powerSub">-->
<!--								</select>-->
				            </div>
							<script type="text/javascript">
							
							//var MyCNLTreeMenu1=new CNLTreeMenu("CNLTreeMenu2","li");
							//MyCNLTreeMenu1.InitCss("Opened","Closed","Child","../s.gif");
							
							</script>
						</td></tr></table>
				        </td>
				        <td style="padding:0">
				        	<span id="btn1">
					        	<button type="button" class="right" onclick="addPower()"></button>
					        	 &nbsp;	
		    					<br/>
								<br/>
								<br/>
					        	<button type="button" class="left" onclick="delPower()"></button>
				        	</span>
				        </td>
				        <td valign="top" width="57%">
				          <table width="100%">
				          	   <tr>
				          	   		<td>
				          	   			<button type="button" onclick="showAddUser()">����û�</button>
				                  		<button type="button" onclick="showModiUser()">�޸��û�</button>
				                 		<button type="button" onclick="showDelUser()">ɾ���û�</button>
				                 		<br/>
<!--				                <logic:notEqual value="11078" name="xxdm">-->
<!--									<button type="button" onclick="viewPassword();">-->
<!--										��ѯ����-->
<!--									</button>-->
<!--								</logic:notEqual>-->
<!--								<logic:equal value="11078" name="xxdm">-->
								
				                 		
<!--								</logic:equal>-->
								
								 
								<button type="button" onclick="viewTempDiv('Ȩ�޵���','expData',300, 120)">
									Ȩ�޵���
								</button> 
								<button type="button" onclick="showOneTeaUser()">
										��������
								 </button>
								<button type="button" onclick="showAllTeaUser()">
										��ʼ������
								 </button>
				          	   	</td>
				          	   </tr>
				              <tr>
				                <td>
				                  <font color="red">��Ӧ˵��:�û���/����/������/���ڲ���/����״̬</font>
				                                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                  <button type="button" onclick="showTopWin('user_query.do','350','350');">
										�û���ѯ
									</button>
									<div id="div_user">
						                 <html:select property="userName" onchange="createYhGnmkDiv()"
												 size="10" styleId="topGroup" styleClass="selectlist">
											<html:options collection="listGroup" labelProperty="xm" property="yhm" />	
									 	</html:select>
								 	 </div>
								 	<input type="hidden" id="hidden_yhm"/>
								 	<input type="hidden" id="hidden_xm"/>
								 	<input type="hidden" id="hidden_zdm"/>
								 	<input type="hidden" id="hidden_szbm"/>
								 	<button type="button" id="btn_user_query" onclick="createUserDiv()" style="display:none">���û�</button>
								 	<script language="javascript" defer="defer">
								 		//�����û�DIV
										function createUserDiv(){
											
											jQuery.ajaxSetup({async:false});
									
											  var url = "general_xtwh_power.do?method=createUserDiv";  
											  
						                      //����
						                      var parameter = {
						                        "str_yhm":escape(jQuery("#hidden_yhm").val()),
						                        "str_xm":escape(jQuery("#hidden_xm").val()),
						                      	"str_zdm":escape(jQuery("#hidden_zdm").val()),
						                        "str_szbm":escape(jQuery("#hidden_szbm").val())
						                       };
						                       
						             
												jQuery("#div_user").load(
													url,
													parameter,
													function(){
													
													}
												);
												
											jQuery.ajaxSetup({async:true});
										}
								 	</script>
								
				                  
				               </td>
				              </tr>
				              <tr>
				                <td>
				                  <label>��ѡ�û���ӵ�й���ģ��</label>
				                  <div id="div_yh_gnmk">
					                  <html:select property="userPower" styleClass="selectlist"
												 size="10" styleId="groupPower">
											<html:options collection="powerListG" labelProperty="gnmkmc" property="gnmkdm" />
									  </html:select>
								  </div>
				                </td>
				              </tr>
				            </table></td>
				          </tr>
				     <tfoot>
				      <tr>
				      	<td align="right" colspan="3">
				            <button type="button"  onclick="save('saveUserPower.do')">�� ��</button>
				            <button type="button"  onclick="chgR()">��ѡ��Ϊֻ��</button>
				            <button type="button"  onclick="chgW()">��ѡ��Ϊ��д</button>
				            <button type="button"  onclick="readOnlyAll()">ȫ��ֻ��</button>
				            <button type="button"  onclick="writeAll()">ȫ����д</button>
				      	</td>
				      </tr>
				    </tfoot>
				  </table>
				</div>
			<!-- ������û� -->
			<div class="open_win01" style="display:none;" id="addUser" >
				<table width='80%' class='formlist'>
					<tbody>
						<tr>
							<th width='25%'>
								<font color=red>*</font>�û���
							</th>
							<td>
								<input type='text' name='newUserID' class='text_nor' id="newUserIDd" onblur="checkExists('yhb','yhm',this,'button_user','span_user');"/>
								<span id="span_user" style="display: none;color:red">�û����Ѵ��ڣ�</span>
							</td>
						</tr>
						<tr>
							<th>
								<font color=red>*</font>����
							</th>
							<td>
								
								<input type='password' name='newPassword' id='newPasswordd' class='text_nor'/>
							</td>
						</tr>
						<tr>
							<th>
								<font color=red>*</font>ȷ�Ͽ���
							</th>
							<td>
								<input type='password' name='newPassword2' id="newPassword2d" class='text_nor'  />
							</td>
						</tr>
						<tr>
							<th>
								<font color=red>*</font>������
							</th>
							<td>
								<select id='select_newUserGroup' name='newUserGroup' 
									style='width:152px'  ></select>
							</td>
						</tr>
						<logic:notEqual value="10338" name="xxdm" scope="session">
							<tr>
								<th>
									<font color=red>*</font>������λ
								</th>
								<td>
									<input type='text' name='dwText' id='dwText1' class='text_nor'
										style="width:142px;z-index:999;position:relative;border:0!important;padding:0 2px;margin-left:1px;"
										maxlength='128'/>
										<select
											id='select_newUserUnit' name='newUserUnit'
											style='margin-left:-152px;width:168px;z-index:2;position:relative;'
											onclick='fillText(this,"dwText1")'>
										</select>
								</td>
							</tr>
						</logic:notEqual>
						<tr>
							<th>
								<font color=red>*</font>����
							</th>
							<td>
								<select id='select_newUserPart' name='newUserPart' id="newUserPart" style='width:152px;' ></select>
							</td>
						</tr>
						<tr>
							<th>
								<font color=red>*</font>����
							</th>
							<td>
								<input type='text' name='newUserName' id="newUserName2" class='text_nor'/>
							</td>
						</tr>
						<tr>
							<th>
								�Ƿ�����
							</th>
							<td>
								<select id='select_purview' name='purview' style='width:152px;'>
									<option value='1'>
										����
									</option>
									<option value='0'>
										������
									</option>
								</select>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan='2' align='center'>
								<div class="bz">
										"
										<span class="red">*</span>"Ϊ������
									</div>
									<div class='btn'>
									<button type="button" id="button_user" onclick='addUser()'>
										����
									</button>
									&nbsp;&nbsp;
									<button type="button" onclick="hiddenMessage(true,true);return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		
		<!-- �޸��û� -->
		<div class="open_win01" style="display:none;" id="modiUser">
				<table width='80%' class="formlist">
					<tbody>
						<tr>
						<th width='25%'>
							<font color=red>*</font>�û���
						</th>
						<td align='left'>
							<input type='text' name='newUserID' disabled="true" id="userID"  class='text_nor' />
						</td>
					</tr>
					<tr>
						<th>
							<font color=red>*</font>������
						</th>
						<td align='left'>
							<select id='select_userGroup' name='newUserGroup' style='width:152px;' ></select>
						</td>
					</tr>
					<logic:notEqual value="10338" name="xxdm" scope="session">
						<tr>
						<th>
							<font color=red>*</font>������λ
						</th>
						<td align='left' style="z-index:0;position:relative;">
							<input type='text' name='dwText' style="width:142px;z-index:999;position:relative;border:0!important;padding:0 2px;margin-left:1px;"
								id='modiDwText' maxlength='30' />
							<span style='width:18px;'> 
								<select id='select_userUnit2' name='newUserUnit'
											style='margin-left:-152px;width:168px;z-index:2;position:relative;'
											onclick='fillText(this,"modiDwText")'>
								</select>
							</span>
						</td>
					</tr>
					</logic:notEqual>
					
					<tr>
						<th>
							<font color=red>*</font>����
						</th>
						<td align='left'>
							<input type='text' name='newUserName' id="modiUserName" class='text_nor'/>
						</td>
					</tr>
					<tr>
						<th>
							<font color=red>*</font>����
						</th>
						<td align='left'>
							<select id='select_userPart' name='newUserPart' style='width:152px;' ></select>
						</td>
					</tr>
					<tr>
						<th>
							�Ƿ�����
						</th>
						<td>
							<select id='select_purview' name='purview' style='width:152px;'>
								<option value='1'>
									����
								</option>
								<option value='0'>
									������
								</option>
							</select>
						</td>
					</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan='2' >
								<div class="bz">
										"
										<span class="red">*</span>"Ϊ������
									</div>
									<div class='btn'>
									<button type="button" class='button2' id='buttonSave' onclick='modiUser()'>
										����
									</button>
									&nbsp;&nbsp;
									<button type="button" onclick="hiddenMessage(true,true);return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- ��ѯ�û����� -->
			<div style="display:none;" id="viewUser">

				<table width='80%' class='formlist'>
				<tbody>
					<tr>
						<th width='25%'>
							�û���
						</th>
						<td>
							<div id="div1">
							</div>
						</td>
					</tr>
					<tr>
						<th>
							����
						</th>
						<td>
							<div id="div2">
							</div>
						</td>
					</tr>
					<tr>
						<th>
							����
						</th>
						<td>
							<div id="div3">
							</div>
						</td>
					</tr>
				</tbody>
					
					<tfoot>
						<tr>
							<td colspan='2'>
								<div class='btn'>
									<button type="button" onclick="hiddenMessage(true,true);return false;">
											�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		
		
		
			<!--����Ȩ�� ��ѡ�񵼳����ݷ�Χ -->
			<div style="display:none;" id="expData">
				<table width='80%' class='formlist'>
					<tbody>
						<tr>
							<th>
								ѡ�����ݷ�Χ
							</th>
							<td>
								<input type='radio' name='userExp' value='1' checked />
								&nbsp;����ȫ��
								<input type='radio' name='userExp' value='0' />
								&nbsp;������ѡ
							</td>
						</tr>
					<tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class='btn'>
									<button type="button" onclick='exportData()'>
										ȷ��
									</button>
									&nbsp;&nbsp;
									<button type="button" onclick="hiddenMessage(true,true);return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		
				<logic:notEmpty scope="request" name="commanForm" property="password">
					<html:hidden property="password" styleId="pass" />
						<script type="text/javascript" defer>
							var tmp = document.forms[0].pass.value.split("!!SplitSigne!!");
							
							document.getElementById('div1').innerHTML= tmp[0];
							document.getElementById('div2').innerHTML= tmp[1];
							document.getElementById('div3').innerHTML= tmp[2];
							
				         	viewTempDiv("��ѯ�û�����","viewUser",300, 150);
				         	document.forms[0].pass.value = "";
				         	
				        </script>
				</logic:notEmpty>
			</html:form>
				
			
			<logic:equal value="ok" name="result">
				<script>
					alert("���ɳɹ���");
				</script>
			</logic:equal>
			<logic:equal value="no" name="result">
				<script>
					alert("����ʧ�ܣ�");
				</script>
			</logic:equal>
		<script language="javascript">
			//var powerChanged = false;
			//var i = 1;
			//var j = 1;
			//var dpd = new Array();
			//var txt = document.forms[0].allPower.value;
			// SplitSignOne = "!!SplitSignOne!!";
			// SplitSignTwo = "!!SplitSignTwo!!";
			//var initStrToSplit = txt.split(SplitSignOne);
			//document.forms[0].powerTop.options.length = 0;
			//document.forms[0].powerTop.options[document.forms[0].powerTop.options.length] = new Option("----ȫ��-----","all");
			//for(i = 1;i<initStrToSplit.length;i++){
			//	dpd[i] = initStrToSplit[i].split(SplitSignTwo);
			//	if(dpd[i][1].length == 3){
			//		document.forms[0].powerTop.options[document.forms[0].powerTop.options.length] = new Option(dpd[i][1]+" | "+dpd[i][2],dpd[i][1]);
			//	}
				
			//}
			
			//listAll();
			//init=null;
			//for(i = 0;i<document.getElementsByTagName("select").length;i++){
			////  if(document.getElementsByTagName("select")[i].length>0){
			//	if(document.getElementsByTagName("select")[i].selectedIndex < 0){
				//	document.getElementsByTagName("select")[i].options[0].selected = true;
			//	}
		//  }
		//}
			//i=1;
			
	</script>
	<logic:present name="message">
		<script defer="defer">
			alert("${message}");
		</script>
	</logic:present>
	</body>
</html>
