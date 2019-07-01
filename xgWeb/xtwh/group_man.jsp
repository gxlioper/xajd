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
			var prompt = "即将导出";//提示信息 
			
			if(yhz != null && yhz != "" && yhz != 'null'){//选择了用户组
				prompt += document.getElementById('topGroup').options[document.getElementById('topGroup').selectedIndex].text + "用户组的";
			}else{
				prompt += "所有用户组的";
			}
			
			if(gnmkdm != null && gnmkdm != "" && gnmkdm != 'null'){//选择了功能
				prompt += document.getElementById('powerTop').options[document.getElementById('powerTop').selectedIndex].text + "模块下的";
			}
			prompt += "权限,确定导出吗？";
			if(confirm(prompt)){//确定导出数据
				window.open('expGroup.do?yhz=' + yhz + '&gnmkdm=' + gnmkdm);
			}
		}
		function selectYhz(){
			var yhz=document.getElementById('topGroup');
			if(yhz.value==''){
				alert('请先选择您要分配权限的用户组！');
				return  false;
			}
			return true;
		}
		function saveFun(){
			var zmc = document.getElementById('zmc');
			if (zmc.value == "0001") {
				alert("不能修改此组权限!");
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
					<em>您的当前位置:</em><a>系统维护 - 权限维护 - 组维护</a>
			</p>
		</div>
		
		
		<div class="tab">
		  <table width="100%" border="0" class="permissionlist">
		    <thead>
		    	<tr>
		        	<td colspan="3"><span>组权限维护</span></td>
		        </tr>
		    </thead>
				 <tr>
       				 <td valign="top" width="35%">
						<table width="100%">
				          <tr>
				           <td>
				        	<div class="filter" id="div_first_gnmk">
				        		<label>子系统</label>
<!--				        		<select name="gnmkmc"-->
<!--										onchange="loadGroupSubPower();" id="powerTop">-->
<!--								</select>-->
				        	</div>
				        	<script language="javascript" defer="defer">
				        	
				        		//一切为了南京技师...
								setTimeout("createFirstGnmkDiv()",500);
								
								//创建第一级功能菜单
								function createFirstGnmkDiv(){
									//路径
									var url = "general_xtwh_power.do?method=createFirstGnmkDiv";	
									//參數
								 	var parameter = {};
									
									jQuery("#div_first_gnmk").load(
										url,
										parameter,
										function(){
											createSecondGnmkDiv();
										}
									);
								}
								
								//创建第二级功能菜单
								function createSecondGnmkDiv(){
									
									var gnmkdm = jQuery("#powerTop").val();
									var zdm = jQuery("#topGroup").val();
									
									//路径
									var url = "general_xtwh_power.do?method=createSecondGnmkDiv";	
									//參數
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
								
								//创建用户组功能菜单
								function createYhzGnmkDiv(){
									
									var zdm = jQuery("#topGroup").val();
									//路径
									var url = "general_xtwh_power.do?method=createYhzGnmkDiv";	
									//參數
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
	                <label>组</label>
	                <!--									onchange="submitForm()" -->
                 	<html:select property="zmc" style="width:150px;"
									onchange="createYhzGnmkDiv()"
									styleId="topGroup"
									onfocus="beforSubmitForm()">
						<html:option value="">---请选择---</html:option>
						<html:options collection="listGroup" labelProperty="zmc"
										property="zdm" />
					</html:select>
					<br/><br/>
					<span id="btn4">
                  		<button type="button" onclick="viewTempDiv('添加组','addGroup',300, 140)">添加</button>
                  		<button type="button" onclick="showModi();getSfxsG()">修改</button>
                  		<button type="button" onclick="showDel()">删除</button>
                  		<!--  <button type="button" onclick="showSz();getSfxsG()">设置</button> -->
                  		<button type="button" onclick="zqxExp();">导出</button>
                  	</span>
                 </td>
              </tr>
              <tr>
                <td>
                	<label>当前用户组已拥有功能模块</label>
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
			            <button type="button"  name="保存"  onclick="saveFun();">保 存</button>
			            <button type="button"  name="所选设为只读" onclick="chgR()">所选设为只读</button>
			            <button type="button"  name="所选设为可写" onclick="chgW()">所选设为可写</button>
			            <button type="button"  name="全部只读" onclick="readOnlyAll()">全部只读</button>
			            <button type="button"  name="全部可写" onclick="writeAll()">全部可写</button>
<%--			            <jsp:include flush="true" page="/myJsp.jsp"></jsp:include>--%>
			      </td>
		      </tr>
            </tfoot>
            </table>
            </div>
            
             <!-- 添加组弹出层 -->
           
            <div class="open_win01" style="display:none;" id="addGroup">
			<table width='80%' class='formlist'>
				<tbody>
					<tr>
						<th>
							<font color='red'>*</font> 组名称
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
								<span class="red">*</span>"为必填项
							</div>
							<div class='btn'>
								<button type="button" onclick='if(checkZMCLength()) addGroup()'>
									添加
								</button>
								&nbsp;&nbsp;
								<button type="button" onclick="hiddenMessage(true,true);return false;">
									关闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		
		<!-- 修改组弹出层 -->
		<div class="open_win01" style="display:none;" id="modiGroup">
				<table width='80%' class='formlist'>
					<tbody>
						<tr height='30'>
							<th>
								<font color='red'>*</font>组名称
							</th>
							<td>
								<input type='text' name="ssss" id="newGName2" class='text_nor' />
								<input type='hidden' name="yymc" id="yymc"/>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>是否在用户维护中显示
							</th>
							<td>
								<input type='radio' name='sfxsG' id='sfxsG' value='1' />
								&nbsp;是&nbsp;&nbsp;&nbsp;&nbsp;
								<input type='radio' name='sfxsG' id='sfxsG' value='0' />
								&nbsp;否
							</td>
						</tr>
					<tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
								"
									<span class="red">*</span>"为必填项
								</div>
								<div class='btn'>
									<button type="button"  onclick='modiGroup()'>
										修改
									</button>
									&nbsp;&nbsp;
									<button type="button" onclick="hiddenMessage(true,true);return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		
		
			<!-- 设置组要弹出 的层 -->
<%--			<div class="open_win01" style="display:none;" id="szGroup">--%>
<%--				<table width='300' class='formlist'>--%>
<%--					<tbody>--%>
<%--						<tr>--%>
<%--							<th>--%>
<%--								<font color="red">*</font>是否在用户维护中显示--%>
<%--							</th>--%>
<%--							<td>--%>
<%--								<input type='radio' name='sfxsG' id='sfxsG' value='1' />--%>
<%--								&nbsp;是&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--								<input type='radio' name='sfxsG' id='sfxsG' value='0' />--%>
<%--								&nbsp;否--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--					<tbody>--%>
<%--					<tfoot>--%>
<%--						<tr>--%>
<%--							<td colspan="2">--%>
<%--								<div class="bz">--%>
<%--									"--%>
<%--									<span class="red">*</span>"为必填项--%>
<%--								</div>--%>
<%--								<div class='btn'>--%>
<%--								<button type="button"  onclick='szGroup()'>--%>
<%--									保存--%>
<%--								</button>--%>
<%--								&nbsp;&nbsp;--%>
<%--								<button type="button" onclick="hiddenMessage(true,true);return false;">--%>
<%--									关闭--%>
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
							<th>为防止误操作，请输入您要删除的组名称</th>
							<td><input type='text' name='newGName' id="delNewGName"/></td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<button type="button" class='button2' onclick='delGroup()'>删除</button>
									<button type="button" class='button2' onclick='hiddenMessage(true,true)'>关闭</button>
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
			////document.forms[0].powerTop.options[document.forms[0].powerTop.options.length] = new Option("----全部-----","all");
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


