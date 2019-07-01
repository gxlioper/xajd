<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript">
			function checkType(){
			   var userType = $("userType").value;
			   if(userType=="xx"||userType=="admin"){
			      if($("checkpass")){ $("checkpass").value="学校审核通过";}
			      if($("checknopass")){ $("checknopass").value="学校审核不通过";}
			   }else if(userType=="xy"){
			      if($("checkpass")){ $("checkpass").value="<bean:message key="lable.xsgzyxpzxy" />审核通过";}
			      if($("checknopass")){ $("checknopass").value="<bean:message key="lable.xsgzyxpzxy" />审核不通过";}
			   }else{
			      if($("checkpass")){ $("checkpass").value="班级审核通过";}
			      if($("checknopass")){ $("checknopass").value="班级审核不通过";}
			   }
			}
			
			function chec(){
			  for(i=0;i<document.getElementsByName("pkV").length;i++){
			    document.getElementsByName("pkV")[i].checked=document.getElementsByName("fyxx")[0].checked;
			  }
			}
			function CheckDo(){
			    var	w = 600;
				var	h = 400;
				var realTable = document.getElementById("realTable").value;
				url = "/xgxt/csmz_sztz.do?method=tzcg_shView";	
				url += "&pkValue=";
				url += curr_row.cells[0].getElementsByTagName("input")[0].value;
				showTopWin(url, w, h);	
			}
			
			function allBjSh(url){
			    confirmTxt = "全班通过审核将以某学年、学期项目\n及班级为单位进行批量通过审核! \n";
			    if(document.forms[0].xn.value==""){
					alert(confirmTxt+"请选择学年！");
					return false;
			    }
			    if(document.forms[0].xq.value==""){
			        alert(confirmTxt+"请选择学期！");
			        return false;
			    }
			    if (document.forms[0].bjdm.value == "") {
					alert("请选择班级！");
					return false;
				}else{
				   confirmTxt = "全班通过审核将耗费较长的时间，确定要开始自动审核吗？";
					if (confirm(confirmTxt)) {		
					    alert("点击\"确定\"后开始审核！");		    
					    sztzShowTips('正在进行整批审核，请稍候......');
						refreshForm(url);
						return true;
					} else {
						return false;
					}
				}	
			}
			function myxyDisabled(ele) {
			    var xxdm = $("xxdm").value;
			    if(xxdm!='11417'){
			       var userT = document.getElementById("userType").value;
				   if (userT == "xy"||userT =="stu") {
					var tmp = ele.split("-");
					for (i = 0; i < tmp.length; i++) {
						document.getElementById(tmp[i]).disabled = true;
					}
				   }
				}
			}
			
		 	function batchCheck(str){
		           var userType = $("userType").value;
		           var xxdm  = $("xxdm").value;
		           var url = "/xgxt/csmz_sztz.do?method=plCheck&check="+str; 
				   var RowsStr="!!";		  
				   var userType = $("userType").value;		   
				   xyshDone = (str=="yes")?"通过":"不通过";
				   /*
				   var pkVArray = "'";
				   for (i=0; i<document.getElementsByName("pkV").length; i++){
			    	  if(document.getElementsByName("pkV")[i].checked){	    		 
			    		 pkVArray+=document.getElementsByName("pkV")[i].value+"','"
			    		 RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
			    	  }	    	  
				   }		   
				   if (RowsStr=="!!"){
					   alert("请选择要操作的记录！\n(单击每条记录前复选框)");
					   return false;
				   }
				   document.forms[0].pkVStr.value = RowsStr;
				   pkVArray=pkVArray.substring(0,pkVArray.length-2);
				  	   
				   getSztzData.getInfoEx2("csmz_tzcgb"," (xh||xmid||cyjs||jxlb) in ("+pkVArray+") and (xysh<>'通过' or ( '13022'='"+xxdm+"' and fdysh<>'通过' ) ) ",function(boolean){		       
				          if(boolean){
				               if(userType=="xx"||userType=="admin"){
				                   if(confirm("所选中记录中，有下级部门审核\"不通过\"或\"未审核\"的记录！\n\n确定要\""+xyshDone+"\"所选记录？")){
				                       toSave(url,str)
				                       return false;  
				                   }
				               }else{
				                   var clin ="确定要\""+xyshDone+"\"所选记录？";
				                   if(userType=="xy"){
				                       if(xxdm=='13022'){//浙江大学宁波理工<bean:message key="lable.xsgzyxpzxy" />
				                          clin = "所选中记录中，有班级审核\"不通过\"或\"未审核\"的记录！\n\n确定要\""+xyshDone+"\"所选记录？";		                      
				                       }
				                   }
				                   if (confirm(clin)){
				                       toSave(url,str)
				                   }		                
				              }
				          }else{
				               if (confirm("确定要\""+xyshDone+"\"所选记录？")){
					              toSave(url,str)
				               }
				          }		      
				   });
				   */
				    var pkValue=new Array();
					var i=0;
					
					jQuery("input[name=pkV]:checked").each(function(){
						pkValue[i]=jQuery(this).val();
						RowsStr+=jQuery(this).val()+"!!";
						i++;
					});
					if (RowsStr=="!!"){
					   alert("请选择要操作的记录！\n(单击每条记录前复选框)");
					   return false;
				    }
				    document.forms[0].pkVStr.value = RowsStr;
					var parameter={};
					parameter["array_pkValue"]=escape(pkValue.join("!!array!!"));
					
				   jQuery.ajaxSetup({async:false});
					jQuery.ajax({
						 type: "post",
					     url: "csmz_sztz.do?method=cgshSave",
					     dataType:"json",
					     data: parameter,
					     success: function(data){
						     if(data){
					               if(userType=="xx"||userType=="admin"){
					                   if(confirm("所选中记录中，有下级部门审核\"不通过\"或\"未审核\"的记录！\n\n确定要\""+xyshDone+"\"所选记录？")){
					                       toSave(url,str);
					                       return false;  
					                   }
					               }else{
					                   var clin ="确定要\""+xyshDone+"\"所选记录？5";
					                   if(userType=="xy"){
					                       if(xxdm=='13022'){//浙江大学宁波理工<bean:message key="lable.xsgzyxpzxy" />
					                          clin = "所选中记录中，有班级审核\"不通过\"或\"未审核\"的记录！\n\n确定要\""+xyshDone+"\"所选记录？";		                      
					                       }
					                   }
					                   if (confirm(clin)){
					                       toSave(url,str);
					                   }		                
					              }
					          }else{
					               if (confirm("确定要\""+xyshDone+"\"所选记录？6")){
						              toSave(url,str);
					               }
					          }
						     
					   	 }
					});
					jQuery.ajaxSetup({async:true});
				               
		  	}
			function toSave(url,str){
			    var xxdm = $("xxdm").value;
			    if(xxdm=='11417'){
			       if(str=="yes"){
			          refreshForm(url); 
			       }else{
			          showClearPoint(); 
			       }
			    }else{
			       refreshForm(url); 
			    }    
			}
			</script>
	</head>
	<body onload="bjDisabled('nj-xy-zy-bj');myxyDisabled('xy');removeXnXq();checkType()">
		
    <html:form action="/csmz_sztz.do?method=data_search" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="xxdm" name="xxdm"
				value="<bean:write name="xxdm" scope="request"/>" />
	        <input type="hidden" name="userType" id="userType" 
		        value="<bean:write name="userType" scope="request"/>">	
		     <input type="hidden" name="userName" id="userName" 
		        value="<bean:write name="userName" scope="session"/>">
			<input type="hidden" id="pkVStr" name="pkVStr"
				value="" />	
<%--			<input type="hidden" id="kcxf"	name="kcxf" value="">--%>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${tips }</a>
				</p>
			</div>
			<div class="toolbox">
				 <!-- 按钮 -->
				 <div class="buttonbox">
				    <ul>
					  <li> <a href="#"  onclick="batchCheck('yes')" id="checkpass" class="btn_shtg"> 通过 </a> </li>
				      <li> <a href="#" onclick="batchCheck('no')"  class="btn_shbtg"> 不通过 </a> </li>
				    </ul>
				 </div>
				<div id="showDiv" style="display:none" align="center">
					<fieldset style="width:90%;height:90%">
						<legend>
							操作提示信息
						</legend>

						<table class='buttontool' hight='100px' >
							<thead>
								<tr>
									<td colspan='2'>
										信用分扣除
									</td>
								</tr>
							</thead>
							<tr>
								<td align='right' width='40%'>
									<font color=red>*</font>扣 分 值：
								</td>
								<td align='left'>
									<input type='text' name='kcfv'>
								</td>
							</tr>
							<tfoot>
								<tr>
									<td colspan='2'>
										<button class='button2' id="kfbtnSave" onclick='savePoint()'>
											提交
										</button>
										&nbsp;&nbsp;
										<button id="kfbtnClose" onclick='closeClearPoint()' class='button2'>
											关闭
										</button>
									</td>
								</tr>
							</tfoot>
						</table>
					</fieldset>
				</div>	
				<div class="searchtab">
				<table width="100%" border="0">
				      <tfoot>
				        <tr>
				          <td colspan="6">
				            <div class="btn">
				               <input type="hidden" name="go" value="a" />
				              <button class="btn_cx" id="search_go" 
				              	onclick="document.forms[0].go.value='go';refreshForm('csmz_sztz.do?method=data_search&act='+$('act').value)">
				              	查 询
				              </button>
				              &nbsp;&nbsp;&nbsp;&nbsp;
				              <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
				              	重 置
				              </button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					<tbody>
								<tr>
									<th align="left">
										学年
									</th>
									<td>
										<html:select property="xn" style="width:100px" styleId="xn"
											onchange="genNdList(this)">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</td>
									<th>
										学期
									</th>
									<td>
										<html:select property="xq" style="width:50px" styleId="xq">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
									</td>
									<th>										
										年级
									</th>
									<td>
										<html:select property="nj" style="width:50px"
											onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
									</td>								
								</tr>
								<tr>
									<th>
										学号
									</th>
									<td>
										<html:text property="xh" style="width:90px"></html:text>
									</td>
									<th>
										姓名
									</th>
									<td>
										<html:text property="xm" style="width:90px"></html:text>
									</td>
									<th>
										<logic:notEqual name="xxdm" value="13022">审核状态</logic:notEqual>
									</th>
									<td>
										<logic:notEqual name="xxdm" value="13022">
										<html:select property="yesNo" style="width:70px">
										    <html:option value=""></html:option>
											<html:options collection="chkList" property="en"
												labelProperty="cn" />											
										</html:select>	
										</logic:notEqual>
									</td>	
								</tr>
								<tr>
									<th align="left" nowrap>
										<bean:message key="lable.xsgzyxpzxy" />(部门)
									</th>
									<td>
										<html:select property="xydm" style="width:150px" styleId="xy"
											onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</td>
									<th>
										专业
									</th>
									<td>
										<html:select property="zydm" style="width:180px" styleId="zy"
											onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
									</td>
									<th>
										班级
									</th>
									<td>
										<html:select property="bjdm" style="width:180px" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
									</td>
								</tr>
								<logic:equal name="xxdm" value="13022">
								<tr>
									<th>
										班级审核
									</th>
									<td>
										<html:select property="fdyshYesNo" style="width:70px">
										    <html:option value=""></html:option>
											<html:options collection="chkList" property="en"
												labelProperty="cn" />											
										</html:select>	
									</td>
									<th>
										<bean:message key="lable.xsgzyxpzxy" />审核
									</th>
									<td>
										<html:select property="xyshYesNo" style="width:70px">
										    <html:option value=""></html:option>
											<html:options collection="chkList" property="en"
												labelProperty="cn" />											
										</html:select>	
									</td>
									<th>
										学校审核
									</th>
									<td>
										<html:select property="xxshYesNo" style="width:70px">
										    <html:option value=""></html:option>
											<html:options collection="chkList" property="en"
												labelProperty="cn" />											
										</html:select>	
									</td>
								</tr>	
								</logic:equal>						
						</tbody>
					</table>
				  </div>
				</div>
				<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs">
							<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序;</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>

				<logic:notEmpty name="rs">
				  <table summary="" class="dateline" align="" width="100%" id="rsTable">
						<thead>
							<tr style="cursor:hand">
								<td title="全选">
									<input type="checkbox" name="fyxx" value="all" onclick="chec()">
								</td>
								<logic:iterate id="tit" name="topTr" offset="2">
									<td align="center" id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this);"
								style="cursor:hand;background-color:
                                        <logic:iterate id="v" name="s" offset="0" length="1">
                                        <bean:write name="v"/>
                                        </logic:iterate>
                                        "
								ondblclick="CheckDo()">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="checkbox" name="pkV"
												value="<bean:write name="v"/>">
										</logic:iterate>
									</logic:iterate>
								</td>
								<td>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
								<jsp:include flush="true"
									page="/sjcz/turnpage.jsp?form=sztzForm"></jsp:include>
				 </logic:notEmpty>	
				 </div>	
             
<%--					<button class="button2" onclick="allBjSh('csmz_sztz.do?method=all_check&bjdm='+document.forms[0].bjdm.value)"--%>
<%--								style="width:80px">--%>
<%--								全班通过--%>
<%--					</button>										--%>					  	
				 		 											
	</html:form>
	<script type="text/javascript" src="js/bottomButton.js"></script>		
    </body>
	<logic:equal name="autoChk" value="ok" scope="request">
		<script language="javascript">
                   alert("自动审核完成！");
                   document.getElementById('search_go').click();	      
	    </script>
	</logic:equal>
	<logic:equal name="autoChk" value="no" scope="request">
		<script language="javascript">
                 alert("自动审核失败！");
                 document.getElementById('search_go').click();	      
 	    </script>
	</logic:equal>
 <logic:present name="done">
	 	<logic:equal value="true" name="done">
	 		<script>
	 			alertInfo('操作成功！');
	 		</script>
	 	</logic:equal>
	 	<logic:equal value="false" name="done">
	 		<script>
	 			alertInfo('操作失败！');
	 		</script>
	 	</logic:equal>
	 	</logic:present>
</html>	
