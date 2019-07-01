<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/zzxmTj.js'></script>
	<script type="text/javascript" src="js/String.js"></script>
	
	<script language="javascript" defer="defer">
		
		function zqxExp(){
			var yhz = document.getElementById('topGroup').value;
			var gnmkdm = document.getElementById('powerTop').value;
			var prompt = "��������";//��ʾ��Ϣ 
			
			if(yhz != null && yhz != "" && yhz != 'null'){//ѡ�����û���
				prompt += document.getElementById('topGroup').options[document.getElementById('topGroup').selectedIndex].text + "�û����";
			}else{
				prompt += "�����û����";
			}
			
			if(gnmkdm != null && gnmkdm != "" && gnmkdm != 'null'){//ѡ���˹���
				prompt += document.getElementById('powerTop').options[document.getElementById('powerTop').selectedIndex].text + "ģ���µ�";
			}
			prompt += "Ȩ��,ȷ��������";
			if(confirm(prompt)){//ȷ����������
				window.open('expGroup.do?yhz=' + yhz + '&gnmkdm=' + gnmkdm);
			}
		}
		function selectYhz(){
			var yhz=document.getElementById('topGroup');
			if(yhz.value==''){
				alert('����ѡ����Ҫ����Ȩ�޵��û��飡');
				return  false;
			}
			return true;
		}
		function saveFun(){
			var zmc = document.getElementById('zmc');
			if (zmc.value == "0001") {
				alert("�����޸Ĵ���Ȩ��!");
				return false;
			}
			save('saveGroupPower.do');
		}
	</script>
	</head>
	<body>
		<html:form action="/saveGroupPower" method="post">
			    <input type="hidden" id="writeAble" name="writeAble" value="<bean:write name="writeAble" scope="request"/>" />
				<html:hidden property="displaySubPower" styleId="allPower" />
				<input type="hidden" name="power" value="" />
				<input type="hidden" name="newGroupName" id="newGroupName" value="" />
				<input type="hidden" name="sfxsGroup" id="sfxsGroup" value="" />
				<input type="hidden" name="sUserName" id="sUserName" value="${userName}" id="sUserName" />
	
	
		<div id="tempdiv"></div>
		<div class="tab_cur">
			<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ϵͳά�� - Ȩ��ά�� - ��ά��</a>
			</p>
		</div>
		
		
		<div class="tab">
		  <table width="100%" border="0" class="permissionlist">
		    <thead>
		    	<tr>
		        	<td colspan="3"><span>��Ȩ��ά��</span></td>
		        </tr>
		    </thead>
				 <tr>
       				 <td valign="top" width="35%">
						<table width="100%">
				          <tr>
				           <td>
				        	<div class="filter" id="div_first_gnmk">
				        		<label>��ϵͳ</label>
<!--				        		<select name="gnmkmc"-->
<!--										onchange="loadGroupSubPower();" id="powerTop">-->
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
									var zdm = jQuery("#topGroup").val();
									
									//·��
									var url = "general_xtwh_power.do?method=createSecondGnmkDiv";	
									//����
								 	var parameter = {
								 		"str_gnmkdm":gnmkdm,
								 		"str_zdm":zdm
								 	};
									
									jQuery("#CNLTreeMenu2").load(
										url,
										parameter,
										function(){
										
										}
									);
								}
								
								//�����û��鹦�ܲ˵�
								function createYhzGnmkDiv(){
									
									var zdm = jQuery("#topGroup").val();
									//·��
									var url = "general_xtwh_power.do?method=createYhzGnmkDiv";	
									//����
								 	var parameter = {
								 		"str_zdm":zdm
								 	};
									
									jQuery("#div_yhz_gnmk").load(
										url,
										parameter,
										function(){
										
										}
									);
								}
				        		</script>
				            <div class="CNLTreeMenu2" id="CNLTreeMenu2">
<!--				            	<select -->
<!--				            			onmouseover="null"-->
<!--				            			name="gnmkdm" -->
<!--				            			style="width:100%"-->
<!--				            			size="23" -->
<!--				            			name="powerSub"-->
<!--										id="powerSub">-->
<!--														-->
<!--								</select>-->
				            </div>
							<script type="text/javascript">						
							//var MyCNLTreeMenu1=new CNLTreeMenu("CNLTreeMenu2","li");
							//MyCNLTreeMenu1.InitCss("Opened","Closed","Child","../s.gif");
							</script>
						</td>
						</tr>
					</table>
        </td>
        <td style="padding:0">
        	<span id="btn1">
        	<button type="button" class="right" onclick="if(selectYhz()){addPower()}"></button>
        	 &nbsp;	
		    <br/>
			<br/>
			<br/>
        	<button type="button" class="left" onclick="if(selectYhz()){delPower()}"></button>
        	
        	</span>
        </td>
        <td valign="top" width="57%">
          <table width="100%">
              <tr>
                <td>
	                <label>��</label>
	                <!--									onchange="submitForm()" -->
                 	<html:select property="zmc" style="width:150px;"
									onchange="createYhzGnmkDiv()"
									styleId="topGroup"
									onfocus="beforSubmitForm()">
						<html:option value="">---��ѡ��---</html:option>
						<html:options collection="listGroup" labelProperty="zmc"
										property="zdm" />
					</html:select>
					<br/><br/>
					<span id="btn4">
                  		<button type="button" onclick="viewTempDiv('�����','addGroup',300, 140)">���</button>
                  		<button type="button" onclick="showModi();getSfxsG()">�޸�</button>
                  		<button type="button" onclick="showDel()">ɾ��</button>
                  		<!--  <button type="button" onclick="showSz();getSfxsG()">����</button> -->
                  		<button type="button" onclick="zqxExp();">����</button>
                  	</span>
                 </td>
              </tr>
              <tr>
                <td>
                	<label>��ǰ�û�����ӵ�й���ģ��</label>
                	<div id="div_yhz_gnmk">
                		<html:select property="zdm" size="20" styleId="groupPower" styleClass="selectlist">	
							<html:options collection="powerListG" labelProperty="gnmkmc"
											property="gnmkdm" />
						</html:select>
                	</div> 
               </td>
              </tr>
            </table>
            </td>
            </tr>
            <tfoot>
            	<tr>
			      <td align="right" colspan="3">
			            <button type="button"  name="����"  onclick="saveFun();">�� ��</button>
			            <button type="button"  name="��ѡ��Ϊֻ��" onclick="chgR()">��ѡ��Ϊֻ��</button>
			            <button type="button"  name="��ѡ��Ϊ��д" onclick="chgW()">��ѡ��Ϊ��д</button>
			            <button type="button"  name="ȫ��ֻ��" onclick="readOnlyAll()">ȫ��ֻ��</button>
			            <button type="button"  name="ȫ����д" onclick="writeAll()">ȫ����д</button>
<%--			            <jsp:include flush="true" page="/myJsp.jsp"></jsp:include>--%>
			      </td>
		      </tr>
            </tfoot>
            </table>
            </div>
            
             <!-- ����鵯���� -->
           
            <div class="open_win01" style="display:none;" id="addGroup">
			<table width='80%' class='formlist'>
				<tbody>
					<tr>
						<th>
							<font color='red'>*</font> ������
						</th>
						<td>
							<input type='text' name='newGName' id='newGName1' class='text_nor' />
						</td>
					</tr>
				<tbody>
				<tfoot>
					<tr>
						<td colspan='2'>
							<div class="bz">
								"
								<span class="red">*</span>"Ϊ������
							</div>
							<div class='btn'>
								<button type="button" onclick='if(checkZMCLength()) addGroup()'>
									���
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
		
		<!-- �޸��鵯���� -->
		<div class="open_win01" style="display:none;" id="modiGroup">
				<table width='80%' class='formlist'>
					<tbody>
						<tr height='30'>
							<th>
								<font color='red'>*</font>������
							</th>
							<td>
								<input type='text' name="ssss" id="newGName2" class='text_nor' />
								<input type='hidden' name="yymc" id="yymc"/>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�Ƿ����û�ά������ʾ
							</th>
							<td>
								<input type='radio' name='sfxsG' id='sfxsG' value='1' />
								&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;
								<input type='radio' name='sfxsG' id='sfxsG' value='0' />
								&nbsp;��
							</td>
						</tr>
					<tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
								"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class='btn'>
									<button type="button"  onclick='modiGroup()'>
										�޸�
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
		
		
			<!-- ������Ҫ���� �Ĳ� -->
<%--			<div class="open_win01" style="display:none;" id="szGroup">--%>
<%--				<table width='300' class='formlist'>--%>
<%--					<tbody>--%>
<%--						<tr>--%>
<%--							<th>--%>
<%--								<font color="red">*</font>�Ƿ����û�ά������ʾ--%>
<%--							</th>--%>
<%--							<td>--%>
<%--								<input type='radio' name='sfxsG' id='sfxsG' value='1' />--%>
<%--								&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--								<input type='radio' name='sfxsG' id='sfxsG' value='0' />--%>
<%--								&nbsp;��--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--					<tbody>--%>
<%--					<tfoot>--%>
<%--						<tr>--%>
<%--							<td colspan="2">--%>
<%--								<div class="bz">--%>
<%--									"--%>
<%--									<span class="red">*</span>"Ϊ������--%>
<%--								</div>--%>
<%--								<div class='btn'>--%>
<%--								<button type="button"  onclick='szGroup()'>--%>
<%--									����--%>
<%--								</button>--%>
<%--								&nbsp;&nbsp;--%>
<%--								<button type="button" onclick="hiddenMessage(true,true);return false;">--%>
<%--									�ر�--%>
<%--								</button>--%>
<%--								</div>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--					</tfoot>--%>
<%--				</table>--%>
<%--			</div>--%>
			
			
			<div class="open_win01" style="display:none;" id="delGroup">
				<table width='300' class='formlist'>
					<tbody>
						<tr>
							<th>Ϊ��ֹ���������������Ҫɾ����������</th>
							<td><input type='text' name='newGName' id="delNewGName"/></td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<button type="button" class='button2' onclick='delGroup()'>ɾ��</button>
									<button type="button" class='button2' onclick='hiddenMessage(true,true)'>�ر�</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			
			<div id="tmpdiv"></div>
	</html:form>
		<logic:present name="message">
			<script defer="defer">
				alert("${message}");
			</script>
		</logic:present>
		<script language="javascript">	
			//var powerChanged = false;
			//var i = 1;
			//var j = 1;
			//var dpd = new Array();
			//var txt = document.forms[0].allPower.value;
			//var SplitSignOne = "!!SplitSignOne!!";
			//var SplitSignTwo = "!!SplitSignTwo!!";
			//var initStrToSplit = txt.split(SplitSignOne);
		
			//document.forms[0].powerTop.options.length = 0;
			////document.forms[0].powerTop.options[document.forms[0].powerTop.options.length] = new Option("----ȫ��-----","all");
			//for(i = 1;i<initStrToSplit.length;i++){
				//dpd[i] = initStrToSplit[i].split(SplitSignTwo);
				//if(dpd[i][1].length == 3){
				//	document.forms[0].powerTop.options[document.forms[0].powerTop.options.length] = new Option(dpd[i][1]+" | "+dpd[i][2],dpd[i][1]);
				//}
				
			//}
			//document.forms[0].powerTop.options.length = 0;
			//loadSubList();
		</script>
		<script language="javascript">
			listAll();
			init=null;
			for(i = 0;i<document.getElementsByTagName("select").length;i++){
			  if(document.getElementsByTagName("select")[i].length>0){
				if(document.getElementsByTagName("select")[i].selectedIndex < 0){
					document.getElementsByTagName("select")[i].options[0].selected = true;
				}
			  }
			}
			i=1;
		</script>
	</body>
</html>


