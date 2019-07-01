<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/gyfdyDWR.js'></script>
		<script language="javascript">
		//选择公寓辅导员
		function showGyfdy(){
			showTopWin("/xgxt/gyglGyfdy.do?method=gyfdyxx",800,600);
		}
		
		//
		function showGltj(){
			viewTempDiv('公寓辅导员管辖楼栋','gxldDiv',360,250);
		}
		
		//获取园区信息
		function getYqxx(){
			var xqdm=$("select_xqdm").value;
			var objId=$("select_yqdm");
			gyfdyDWR.getYqxxDWR(xqdm,function(data){
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"yqdm","yqmc");
				
			});
		}
		
		//增加楼栋
		function addGyfdyld(){
	   		var wfpld = $("wfpld");
	   		var yfpld = $("yfpld");
	   		var lddms=new Array();
	   		var ldmcs=new Array();
	   		
	   		var n=0;
	   		var blog=false;
	   		for(i=0;i<wfpld.options.length;i++){
		    	if(wfpld.options[i].selected
		    		 && wfpld.options[i].value!=""
		    		 && wfpld.options[i].value!=null){
		    		
			    	lddms[n]=wfpld.options[i].value;
	                ldmcs[n]=wfpld.options[i].text;
	             	n++;
	             	blog=true;
			    }
		    }
		    
		    if(!blog){
		    	alert("请先选择一幢或多幢需要分配的楼栋后,再点击分配按钮!");
		    	return false;
		    }
		    
			var n=yfpld.options.length
		    for(i=0;i<lddms.length;i++){
		   		 yfpld.options[n] = new Option(ldmcs[i],lddms[i]); 
		   		 n++
		    }
		    
		     for(i=wfpld.options.length-1;i>=0;i--){
		    	for(j=0;j<lddms.length;j++){
			    	if(wfpld.options[i].value==lddms[j]){
			    		wfpld.options[i]=null;
			    		break;
			    	}
		    	}
		    }
		}	
		
		//删除楼栋
		function delGyfdyld(){
	   		var wfpld = $("wfpld");
	   		var yfpld = $("yfpld");
	   		var lddms=new Array();
	   		var ldmcs=new Array();
	   		
	   		var n=0;
	   		var blog=false;
	   		for(i=0;i<yfpld.options.length;i++){
		    	if(yfpld.options[i].selected 
		    		&& yfpld.options[i].value!=""
		    		&& yfpld.options[i].value!=null){
			    	lddms[n]=yfpld.options[i].value;
	                ldmcs[n]=yfpld.options[i].text;
	                blog=true;
	                n++;
			    }
		    }
		    
		   if(!blog){
		    	alert("请先选择一幢或多幢需要取消分配的楼栋后,再点击取消按钮!");
		    	return false;
		    }
			
		    for(i=0;i<lddms.length;i++){
		   		 wfpld.options[wfpld.options.length] = new Option(ldmcs[i],lddms[i]); 
		    }
		    
		     for(i=yfpld.options.length-1;i>=0;i--){
		    	for(j=0;j<lddms.length;j++){
			    	if(yfpld.options[i].value==lddms[j]){
			    		yfpld.options[i]=null;
			    		break;
			    	}
		    	}
		    }
		}	
		
		//保存管辖楼栋
		function saveGxld(){
			var obj=document.createElement("hidden");
			var yfpcwS=document.getElementById("yfpld");
			
			for(i=0;i<yfpcwS.options.length;i++){
		    	var obj=document.createElement("input");
		    	obj.type="hidden";
		    	obj.name="yfpldArr";
		    	obj.value=yfpcwS.options[i].value;
		    	document.forms[0].appendChild(obj);
		    }
		    
		    var url="gyglGyfdy.do?method=gyldfdy&doType=save";
		    
		    refreshForm(url);
		}
		
		function searchWfpld(){
			
			$("ldmc").value=$("ldText").value;
			
			var yhm=$("yhm").value;
			
			var xq="";
			if($("select_xqdm")){
				xq=$("select_xqdm").value;
				$("yqdm").value=$("select_yqdm").value;
			}
			var yq="";
			if($("select_yqdm")){
				yq=$("select_yqdm").value;
				$("xqdm").value=$("select_xqdm").value;
			}
			var xbArr=document.getElementsByName("xbxd");
			var xb="";
			for(i=0;i<xbArr.length;i++){
				if(xbArr[i].checked){
					xb=xbArr[i].value;
					break;
				}
			}			
			
			var ldmc=$("ldmc").value;
			var ldcx=new Array();
			var obj = $("wfpld")
			ldcx=[yhm,xq,yq,xb,ldmc];
			gyfdyDWR.getWfpldList(ldcx,function(data){	
				DWRUtil.removeAllOptions(obj);			
				DWRUtil.addOptions(obj,data,"lddm","ldmc");
			});
		}
		
		function goGyfdyxx(){
		
			var url="gyglGyfdy.do?method=gyfdyManage";
			refreshForm(url);
			
		}
		
		function checkFdy(){
			if($("yhm") && $("yhm").value!=""){
				$("addld").disabled=false;
				$("delld").disabled=false;
				$("ldgl").disabled=false;
				$("bcfpjg").disabled=false;
				
				$("ldgl").attachEvent('onclick', function(){
					showGltj();
				});
			}else{
				$("addld").disabled=true;
				$("delld").disabled=true;
				$("ldgl").disabled=true;
				$("bcfpjg").disabled=true;
				$("ldgl").attachEvent('onclick', function(){
					return false;
				});  
			}
		}
		</script>
	</head>
	<body onload="checkFdy()">

		<html:form action="/gyglGyfdy" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
				</p>
			</div>			
			<!-- 标题 end-->
			<!-- 提示信息 START-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					点击"选择"按钮可以选择公寓辅导员。点"过滤楼栋"按钮后，会弹出楼栋过滤条件，选择条件后点击"查询"按钮<br/>
					可对未分配楼栋进行过滤。选择一条或多条未分配楼栋信息后，点击"分配"按钮可以将未分配楼栋划分给当前选<br/>
					中的公寓辅导员。	选择一条或多条已分配楼栋信息后，点击"取消"按钮可以对已分配楼栋进行取消分配操作。
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 隐藏域 -->
			<html:hidden property="xb" styleId="xb" value="不限制"/>
			<html:hidden property="xqdm" styleId="xqdm" />
			<html:hidden property="yqdm" styleId="yqdm" />
			<html:hidden property="ldmc" styleId="ldmc" value=""/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->

			<div class="tab">
				<table width="100%" border="0" class="formlist">


					<thead>
						<tr>
							<th colspan="4">
								<span>公寓辅导员</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								职工号
							</th>
							<td width="34%">
								<html:text property="yhm" readonly="true"/>
								<button class="btn_01" onclick='showGyfdy()'>
									选择
								</button>
							</td>
							<th width="16%">
								姓名
							</th>
							<td width="34%">
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th>
								所属部门
							</th>
							<td>
								${rs.bmmc}
							</td>
							<th>
								职务
							</th>
							<td>
								${rs.zwmc }
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td>
								${rs.xb}
							</td>
							<th>

							</th>
							<td>

							</td>
						</tr>

						<tr>
							<td colspan="4">
								<table width="100%">
									<tr>
										<td width="45%">
											未分配楼栋
											<a href="#" class="but_show" id="ldgl"> 
												<img src="<%=stylePath%>/images/blue/ico_59.gif" onclick="showGltj()"/>楼栋过滤 </a>
										</td>
										<td width="10%"></td>
										<td width="45%">
											管辖楼栋
										</td>
									</tr>
									<tr>
										<td>
											<html:select property="wfpld" multiple="multiple" styleId="wfpld" size="15"
												 style="width:100%" onmouseover="">
												<html:options collection="wfpList" property="lddm"
													labelProperty="ldmc" />
											</html:select>
										</td>
										<td>
											&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
											<button style="" class="btn" id="addld"
												onclick="addGyfdyld()">
												分配
											</button>
											<br />
											<br />
											<br />
											&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;
											<button style="" class="btn" id="delld"
												onclick="delGyfdyld()">
												取消
											</button>
										</td>
										<td>
											<html:select property="yfpld" multiple="multiple" styleId="yfpld" size="15"
												 style="width:100%" onmouseover="">
												<html:options collection="yfpList" property="lddm"
													labelProperty="ldmc" />
											</html:select>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button id="bcfpjg" onclick='saveGxld();'>
										保 存
									</button>
									&nbsp;&nbsp;
									<button onclick='goGyfdyxx();'>
										返 回
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<div id="gxldDiv" style="display: none">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span> 分配楼栋过滤 </span>
								</th>
							</tr>
						</thead>
						<tbody>
							<logic:equal name="czxq" value="是">
							<tr>
								<th width="32%">
									校区
								</th>
								<td width="68%">
									<html:select property="xqdm" styleId="select_xqdm"
										style="width:150px" onchange="getYqxx()">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</td>
							</tr>
							</logic:equal>
							<logic:equal name="czyq" value="是">
							<tr>
								<th>
									园区
								</th>
								<td width="68%">
									<html:select property="yqdm" styleId="select_yqdm"
										style="width:150px">
										<html:options collection="yqList" property="yqdm"
											labelProperty="yqmc" />
									</html:select>
								</td>
							</tr>
							</logic:equal>
							<tr>
								<th>
									性别限制
								</th>
								<td width="68%">
									
									<input type="radio" name="xbxd" id="xb_nan" value='男' onclick="$('xb').value=this.value"/>
									男
									<input type="radio" name="xbxd" id="xb_nv" value='女' onclick="$('xb').value=this.value"/>
									女
									<input type="radio" name="xbxd" id="xb_hun" value='混合' onclick="$('xb').value=this.value"/>
									混合
									<input type="radio" name="xbxd" id="xb_bcl" checked="checked" value='不限制' onclick="$('xb').value=this.value"/>
									不限制
								</td>
							</tr>
							<tr>
								<th>
									楼栋名称
								</th>
								<td width="68%">
									<input type="text" name="ldText" id="ldText" value=""/>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button onclick='searchWfpld();hiddenMessage(true,true);'>
											查询
										</button>
										&nbsp;&nbsp;
										<button onclick='hiddenMessage(true,true);'>
											关闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</html:form>
		<logic:notPresent name="messages">
			<logic:present name="message">
				<script>
					alert($("message").value);	
					$("message").value = "";
					$("doType").value = "";	
				</script>
			</logic:present>
		</logic:notPresent>
	</body>
</html>
