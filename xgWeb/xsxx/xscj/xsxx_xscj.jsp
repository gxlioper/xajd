<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
<script language="javascript" src="/xgxt/wjcf/shgc/shgcjs/shgcjs.js"></script>	
<script type="text/javascript">
function checkXz(){
	var iskcxz = $("iskcxz");
	var flag = false;
	
	var checkBoxArr = document.getElementsByName("kcxz");
	var flag = false;
	
	if(iskcxz.checked){
		$("kcxzTr").style.display = "";
	}else{
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				var id = "kcxz"+i;
				$(id).checked = false;
			}
			$("kcxzTr").style.display = "none";		
		}
	}
}

function viewKcxzTj(){
	var cjlx = $("cjlx").value;
	if("cjb" == cjlx){
		if($("kcxzSp")){
			$("kcxzSp").style.display = "";
		}
	}else{
		if($("kcxzSp")){
			$("kcxzSp").style.display = "none";
		}
	}
}

function checkedKcxz(){
	if($("checked_Kcxz")){
		var checked_Kcxz = $("checked_Kcxz").value;
		if(checked_Kcxz != ""){
			if ($('iskcxz')) $("iskcxz").checked = true;
			if ($("kcxzTr")) $("kcxzTr").style.display = "";		
		}
		var kcxz = checked_Kcxz.split("!!@@!!");
		if(kcxz != null && kcxz.length>0){
			if ($("kcxz")) {
				var checkBoxArr = document.getElementsByName("kcxz");
				for(var i=0;i<kcxz.length;i++){
					for(var j=0;j<checkBoxArr.length;j++){				
						if(checkBoxArr[j].value==kcxz[i]){
							var id = "kcxz"+j;
							$(id).checked = true;
						}
					}
				}
			}
		}
	}
}
//列选
function lxgn(){
	var cjlx=$("cjlx").value;
	showTopWin("/xgxt/xscj.do?method=kindChoose&cjlx="+cjlx,800,500);
}


	function getBjkcInfo(){
		 var xn=jQuery("#xn").val();
		 var xq=jQuery("#xq").val();
		 var bjdm=jQuery("#bj").val();
			 
		 if(xn!="" && xq!="" && bjdm!=""){		
		 	
		 	 
			 var parameter={}
			 
			 parameter["xn"]=xn;
			 
			 parameter["xq"]=xq;
			 
			 parameter["bjdm"]=bjdm;
			
			 var url = "xscj.do?method=getBjkcInfo";
			
			
			 jQuery.post(url,
				parameter,
				function(result){
					
					var json=eval(result);
					if(json.length>0){
					
						jQuery("#tr_kcmc").attr("style","display:black");
		 	
						var kcSelect="<select name=\"kcmc\" id=\"kcmc\">";
				 			kcSelect+="<option value=\"\"></option>"
						for(i=0;i<json.length;i++){
							kcSelect+="<option value=\""+json[i].kcmc+"\">"
							kcSelect+=json[i].kcmc
							kcSelect+="</option>"
						}
							kcSelect+="</select>";
				 
						jQuery.ajaxSetup({async:true});
						
						jQuery("#div_kcmc").html(kcSelect);
						
						if(jQuery("#hid_kcmc").val()){
							jQuery("#kcmc").val(jQuery("#hid_kcmc").val());
						}
					}else{
						if(jQuery("#div_kcmc")){
						 	jQuery("#div_kcmc").html("");
						 }
						 
						 if(jQuery("#tr_kcmc")){
						 	jQuery("#tr_kcmc").attr("style","display:none");
						 }
					}
				}
			 );
			
			 
					
		}else{
			 if(jQuery("#div_kcmc")){
			 	jQuery("#div_kcmc").html("");
			 }
			 
			 if(jQuery("#tr_kcmc")){
			 	jQuery("#tr_kcmc").attr("style","display:none");
			 }
		 	 
		}	
	}
</script>

</head>
<body onload="xyDisabled('xy');viewKcxzTj();checkedKcxz();getBjkcInfo()">	
		<html:form action="/xscj" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="${userType }"/>
				<input type="hidden" name="zyV" id="zyV"/>
				<input type="hidden" name="bjV" id="bjV"/>
				<input type="hidden" name="xyV" id="xyV"/>
				<input type="hidden" name="path" id="path" value="xsxx_xscjQuery.do"/>
				<logic:equal name="xxdm" value="12741">
				<input type="hidden" name="tableName" value="xsxx_xscj.do" id="tableName"/>
				</logic:equal>
				<logic:notEqual name="xxdm" value="12741">
				<input type="hidden" name="tableName" value="${tableName }" id="tableName"/>
				</logic:notEqual>
				<input type="hidden" name="realTable" value="${realTable }" id="realTable"/>
				<input type="hidden" name="userName" id="userName" value="${userName}" />
				<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
				<input type="hidden" name="isBzr" id="isBzr" value="${bzrQx}" />
				<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }"/>
				<input type="hidden" name="checked_Kcxz" id="checked_Kcxz" value="${checked_Kcxz }"/>
				<input type="hidden" name="hid_kcmc" id="hid_kcmc" value="${kcmc }"/>
				<div class="toolbox">
				<logic:equal name="writeAble" value="yes">
				<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_dc" onclick="cjexp();return false">导出数据</a></li>
						<li>
							<a href="#" onclick="lxgn();return false;" class="btn_zt">列选
							</a>
						</li>
					</ul>
				</div>
				</logic:equal>
				</div>
		              	
				<div class="searchtab">
						<table width="100%" border="0">
						<tfoot>
		        		<tr>
		          			<td colspan="6">
		            		<div class="btn">
			              		<input type="hidden" name="go" value="a" />
			              		<button type="button" class="btn_cx" id="search_go" 
			              		onclick="if (document.getElementById('cjlx').value=='') {alert('请选择要查询的成绩类型！');return;} else allNotEmpThenGo('xsxx_xscjQuery.do');">
			              		查询
			              		</button>
		             		 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
		              			重置
		             		 </button>
		            		</div>
		          			</td>
		       			</tr>
		     			</tfoot>
		              	<tbody>
						<tr>
							<th>
								学年
							</th>
							<td>
								<html:select property="xn" style="width:180px" 
									styleId="xn" styleClass="select">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>			
							<th>
								学期
							</th>
							<td><html:select property="xq" style="width:180px" 
									styleId="xq" styleClass="select">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select></td>
							<th>年级</th>
							<td>
							<html:select property="nj" styleId="nj" style="width:180px" onchange="initZyList();initBjList()">
								<html:option value=""></html:option>
												<html:options collection="njList" property="nj"
													labelProperty="nj" />
							</html:select></td>
						</tr>
						<tr>
							<th align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:select property="xydm" style="width:180px" styleId="xy"
								 onchange="initZyList();initBjList()" styleClass="select">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							
							<th>专业</th>
							<td><html:select property="zydm" style="width:180px" styleId="zy"
								 onchange="initBjList()" styleClass="select">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm" 
									labelProperty="zymc"/>
								</html:select></td>
							<th>班级</th>
							<td><html:select property="bjdm" style="width:180px" styleId="bj"
								styleClass="select" onchange="getBjkcInfo()">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
							
								<!-- 广州大学 200edit by luojw -->
								<logic:equal name="xxdm" value="11078">
								<td>
									<span id="kcxzSp" style="display : none">
									&nbsp;&nbsp;课程性质过滤条件
									<input type="checkbox" id="iskcxz" name="iskcxz" onclick="checkXz()"/>
									<input type="hidden" id="kcxzTj" name="kcxzTj"/>
									</span>
									</td>
								</logic:equal>
								<!-- 广州大学 end -->
							
						</tr>
						<tr>
						<th>成绩类型</th>
							<td>
							<html:select property="cjlx" styleId="cjlx" style="width:180px" onchange="refreshForm('xsxx_xscj.do')">
								<html:option value="cjb">学生成绩表</html:option>
								<html:option value="djksb">学生等级考试表</html:option>
							</html:select></td>
						<th>学号</th>
							<td>
							<logic:equal name="userType" value="stu">
								<html:text property="xh" styleId="xh" style="width:170px" styleClass="inputtext" value="${userName }" readonly="true"></html:text>
							</logic:equal>
							<logic:notEqual name="userType" value="stu">
								<html:text property="xh" styleId="xh" style="width:170px" styleClass="inputtext"></html:text>
							</logic:notEqual>
							</td>
								
							<th>姓名</th>
							<td><html:text property="xm" styleId="xm" style="width:170px" styleClass="inputtext"></html:text>
							</td>
						</tr>
						<logic:equal value="xsdjksb" name="realTable">
						<tr>
						<th>等级考试名称</th>
							<td>
								<html:select property="djksmc" styleId="djksmc" style="width:180px" >
									<html:option value=""></html:option>
									<html:options collection="djksmcList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
							<th></th>
							<td></td>
							<th></th>
							<td></td>
						</tr>
						</logic:equal>
						<logic:equal value="cjb" name="realTable">
							<logic:equal name="xxdm" value="12741">
							<tr id="tr_kcmc" style="display:none">
							<th>课程名称</th>
								<td>
									<div id="div_kcmc">
										
									</div>
								</td>
								<th></th>
								<td></td>
								<th></th>
								<td></td>
							</tr>
							</logic:equal>
						</logic:equal>
						<!-- 广州大学 edit by luojw -->
						<logic:equal name="xxdm" value="11078">
						<tr id = "kcxzTr" style="display : none">
							<td colspan="2">
								<logic:iterate name="kcxzList" id="kcxz" indexId="index">
									<input type="checkbox" id="kcxz${index }" name="kcxz" value="${kcxz.mc }"/>${kcxz.dm }&nbsp;&nbsp;
								</logic:iterate>
							</td>
						</tr>
						</logic:equal>
						<!-- 广州大学 end -->
					</tbody>
					</table>
				</div>
			
			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		</logic:empty>
			   </span>
			</h3>
			</div>
			
			<logic:notEmpty name="rs">
				
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
						<thead>
							<tr align="left" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0">
									<td 
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" align="left"
								style="cursor:hand;background-color:
    							<logic:iterate id="v" name="s" offset="1" length="1">
    							<bean:write name="v"/>
   					 			</logic:iterate>"
   					 			ondblclick="">							
								<logic:iterate id="v" name="s" offset="0">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
					</div>
				<!--分页显示-->
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xscjForm"></jsp:include>
			  	<!--分页显示-->
	
			</logic:notEmpty>
			
	
			<div id="tmpdiv"></div>
		</html:form>
		<script language="javascript">
     			 if ($('choose')) {$('choose').className="hide";}
				function cjexp() {
					var xxdm = $("xxdm").value;
					var cjlx = document.getElementById('cjlx').value;
					var bjdm=$("bj").value;
					if (cjlx=='' || cjlx==null) {
						alert('成绩类型为必选项!');
						return;
					}else {
					 	
					 	if("12741" == xxdm){
							if(bjdm==""){
								alert("请选择一个班级！");
								return false;
							}
						}
						
						if("11078" == xxdm){//广州大学
							var checkBoxArr = document.getElementsByName("kcxz");
							var kcxzTj = "";
							for(var i=0;i<checkBoxArr.length;i++){
								if(checkBoxArr[i].checked==true){
									var id = "kcxz"+i;
									kcxzTj += $(id).value + "!!@@!!";
								}
							}
							$("kcxzTj").value = kcxzTj;
						}
						//dataExport();
						//allNotEmpThenGo('xsxx_xscjExp.do?doType=exp');
						var url = "/xgxt/xscj.do?method=xscjExp";
					 	document.forms[0].action = url;
					 	document.forms[0].target = "_blank";
					 	document.forms[0].submit();
					 	document.forms[0].target = "_self";
					}
				}
				function dr() {
					var cjlx = document.getElementById('cjlx').value;
					if (cjlx=='' || cjlx==null) {
						alert('成绩类型为必选项!');
						return;
					} else {
						impAndChkData();
					}
				}
				
				</script>
				<logic:present name="inserted">
					<logic:equal value="yes" name="inserted">
						<script>
							alert('操作成功！');
						</script>
					</logic:equal>
					<logic:equal value="no" name="inserted">
						<script>
							alert('操作失败！');
						</script>
					</logic:equal>
				</logic:present>
	</body>
</html>