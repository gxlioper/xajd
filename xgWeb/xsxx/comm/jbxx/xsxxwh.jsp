<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<jsp:directive.page import="java.util.HashMap" />
	<jsp:directive.page import="java.util.List" />
	<jsp:directive.page import="java.util.ArrayList" />
	<head>
		<!-- 头文件 -->
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>	
		<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>	
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type='text/javascript' src='js/moveDiv.js'></script>
		<script type='text/javascript' src='js/comm/searchTj.js'></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type="text/javascript">
		function hiddenButton(){
			var xxdm = document.getElementById("xxdm").value;
			<%--安徽建筑工业学院--%>
			if(xxdm=="10878"){
				document.getElementById("btn").style.display="none";
			}
		}
		
		function showDiv(){
			hiddenSelect();
			createOtherDiv("一日规范情况",$('addTp').outerHTML,'addTp',300,200);
		}
		
		function chxj(xj){
			var xxdm = $("xxdm").value;
			if(xxdm == "10491"){
				dwr.engine.setAsync(false);
				var objId = "xszt";
				if(xj == "有学籍"){
					getStuDetails.getXsztList(xj,function(data){
						if (data != null && typeof data == 'object') {
							DWRUtil.removeAllOptions(objId);
							DWRUtil.addOptions(objId,data,"dm","mc");
						}
					});
				}else{
					getStuDetails.getXsztList(xj,function(data){
						if (data != null && typeof data == 'object') {
							DWRUtil.removeAllOptions(objId);
							DWRUtil.addOptions(objId,data,"dm","mc");
						}
					});
				}
				dwr.engine.setAsync(true);
				$("xj").value=xj;
			}
		}
		
		
		function changeXjxx(){
			setVal("nj",val("t_ydhnj"));
			setVal("xy",val("t_ydhxydm"));
			setVal("zy",val("t_ydhzydm"));
			setVal("bj",val("t_ydhbjdm"));
			setVal("xz",val("t_ydhxz"));
			
			var xxdm = $("xxdm").value;
			//非地大
			if( xxdm != "10491"){
				setVal("ydhxj",val("t_ydhxjztm"));
			}
		}
		//加载班级的其它信息
		function loadParentInfo(bjdm){
			getXjydInfo.queryBjParentInfo(bjdm,function(data){
				if(data != null){
					setVal('nj',data.nj == null ? "" : data.nj);
					setVal('xy',data.xydm == null ? "" : data.xydm);
					setVal('zy',data.zydm == null ? "" : data.zydm);
				}
			});
		}
		
		function dataCommit(){
			
			var pk=$('pk').value;
			url='/xgxt/stu_info_add.do?method=stuInfoSave&oper=${oper}';	
			var key = new Array();
			if(pk!=""){
				key=pk.split("-");
			}
			if(key.length > 0){
				for(var i=0;i<key.length;i++){
					if($(key[i])){
						if($(key[i]).value == ""){
							alert("必填字段不能为空！");
							return false;
						}
					}
				}
			}
			showTips('保存中请稍候......');
			document.forms[0].action = url;
			document.forms[0].submit();
			//dataLoad(true);
			var tmpBut = document.getElementsByTagName("button");
			for (i = 0; i < tmpBut.length; i++) {
				tmpBut[i].disabled = true;
			}
			if($("search_go")){
			   $("search_go").disabled=true;
			}
			if($("buttonSave")){
			   $("buttonSave").disabled=true;
			}	
		}
		
		
		
	function xsxxInit(){
		//验证长度
		
		//加载特殊事件
		tssjInit();
	}
	function divHTML(){
		var divHtml ="";
		divHtml+="<div id='addTps' style='display:none'>";
		divHtml+="<table width='99%' border='0' class='formlist'>";
		divHtml+="<thead>";
		divHtml+="<tr>";
		divHtml+="<th colspan='4'><span>上传照片</span></th>";
		divHtml+="</tr>";
		divHtml+="<tr><th colspan='4'  style='color:#0F5DC1;align:left' >";
		divHtml+="类型：jpg   容量：小于100k  大小：96*128像素              ";
		divHtml+="</th></tr>";
		divHtml+="</thead>";
		divHtml+="<tbody>";
		divHtml+="<tr>";
		divHtml+="<th>请选择要导入的图片</th>";
		divHtml+="<td  colspan='3'>";
		divHtml+="<input type='file' size='20'  name='uploadFile' onchange='PreviewImg(this);' id='checkZp' />	";
		divHtml+="</td>";
		divHtml+="</tr>";
		divHtml+="</tbody>";
		divHtml+="<tfoot>";
		divHtml+="<tr>";
		divHtml+="<td colspan='4'>";
		divHtml+="<div class='btn'>";
		divHtml+="<button type='button' class='button2' onclick=\"hiddenZyDiv('addTp');return false;\">";
		divHtml+="确定";
		divHtml+="</button>";
		divHtml+="&nbsp;&nbsp;&nbsp;&nbsp;";
		divHtml+="<button type='button' class='button2' onclick=\"hiddenZyDiv('addTp');return false;\">";
		divHtml+="关闭";
		divHtml+="</button>";
		divHtml+="</div>";
		divHtml+="</td>";
		divHtml+="</tr>";
		divHtml+="</tfoot>";
		divHtml+="</table>";
		divHtml+="</div>";
		createDiv(divHtml);
	}
	
	function createDiv(divHtml){
			var width=400;
			var height=170;
			var title="照片上传";
			var divid="addTp";
			var floatDiv = document.createElement('div');
			floatDiv.id = divid;
			floatDiv.style.position = "absolute";
			floatDiv.style.width = width;
			floatDiv.style.height = height;
			floatDiv.style.backgroundColor = "#FFFFFF";
			
			var d_width = document.body.clientWidth;
			var d_height = document.body.clientHeight;
			var d_left_top = (d_width - width) / 2 +"px";
			var d_top_top =  "100" + "px";
			
			floatDiv.style.top = d_top_top;
			floatDiv.style.left = d_left_top;
			floatDiv.style.zIndex  = 1001;
			
		
			//temp_divHtml=divHtml;
			
			var dd_html ="";
			dd_html += "<div id=\"windown-title\" align='left'><h2>"+title+"</h2>";
			dd_html += "<span id=\"windown-close\" onclick=\"hiddenZyDiv('"+divid+"')\">确定</span>";
			dd_html += "</div>";
			dd_html+="<table style=\"width:100%;height:170;\" border='0' class='formlist open01'>";
			dd_html+="<tbody><tr><td colspan='4'>";
			dd_html+="</td></tr>";
			
			
			
			dd_html+="<tr style=\"width:100%;height:40;\"><td colspan='4'>";
			dd_html+="<div style=\"width:100%;height:170;overflow-x:hidden;overflow-y:auto;\"><table style=\"width:100%\">";
			dd_html += "</div>";
			dd_html += "<div id=\"open_win\" class=\"open_win01\" style=\"padding-top:5px\">";
			floatDiv.innerHTML = dd_html+divHtml+"</div>";
			document.forms[0].appendChild(floatDiv);
			$(divid).style.display="none";
		}
	
	//事件的加载
	function tssjInit(){
		var dm=document.getElementsByName('xxszddm');
		var lrxs=document.getElementsByName('xxszdlrxs');
		
		for(i=0;i<dm.length;i++){
			//录入形式为输入框
			if(lrxs[i].value=="输入框"){
				setTimeout("checkLrgs('"+i+"');",100); 
			}
		}
			
	}
	function showSelect(id){
			hiddenZyDiv(id)	
			i = document.getElementsByTagName("select").length;
			for (j = 0; j < i; j++) {
				var obj = document.getElementsByTagName("select")[j];
				var id = obj.id;
				var arr;
				var splitName;
				
				if ($(id)){
					arr = id.split('_');
				}
				
				if (null != arr && arr.length>0){
					splitName=arr[0];
				}
				
				if (splitName!="select"){
					obj.style.display = "";
				}
		
			}
		}
		function hiddenSelect(){
		
			i = document.getElementsByTagName("select").length;
			for (j = 0; j < i; j++) {
				var obj = document.getElementsByTagName("select")[j];
				var id = obj.id;
				var arr;
				var splitName;
				
				if ($(id)){
					arr = id.split('_');
				}
				
				if (null != arr && arr.length>0){
					splitName=arr[0];
				}
				
				if (splitName!="select"){
					obj.style.display = "none";
				}
		
			}
		}
	
	function checkLrgs(i){
		//字段
		var dm=document.getElementsByName('xxszddm');
		//长度
		var len=document.getElementsByName('xxszdlen');
		//录入形式
		var lrxs=document.getElementsByName('xxszdlrxs');
		//录入限制
		var lrxz=document.getElementsByName('xxszdlrxz');
		var id=dm[i].value;
		var lens=len[i].value;
		
		//onblur时间加载
		$(id).attachEvent('onblur', function(){
			//长度验证的事件加载
			checkLen($(id),lens);
			if(lrxz[i].value=="整数限制"){//整数
				checkNum(id); 
			}else if(lrxz[i].value=="数字限制"){//可带小数点
				checkNumber(id) 
			}else if(lrxz[i].value=="英数字限制"){//英数字
				checkWordNum(id,lens);
			}else if(lrxz[i].value=="中文限制"){//中文
				checkInputIsZw(id,lens); 
			}
		});
	}
	
	//判断数字限制
	function checkNumber(id){
		//判断只能是数字/[^\d]/g
		if ($(id).value!="" &&  $(id).value.match(/[^\d+.{0,1}\d{0,3}]/g)) {
		  alert("只能输入数字!");
		  return false;
	  	}
	}
	
	//判断整数
	function checkNum(id){
		//判断只能是数字/[^\d]/g
		if ($(id).value!="" &&  $(id).value.match(/[^\d]/g)) {
		  alert("只能输入整数!");
		  return false;
	  	}
	}	
	
	//验证是否是中文
	function checkInputIsZw(id) {
		//判断只能是中文/[^\u4e00-\u9fa5]/g
		if ($(id).value!="" &&  $(id).value.match(/[^\u4e00-\u9fa5]/g)) {
		  alert("只能输中文!");
		  return false;
	  	}
	}
	
	//验证英数字
	function checkWordNum(id) {
		//判断只能是中文/[^\u4e00-\u9fa5]/g
		if ($(id).value!="" &&  $(id).value.match(/[^0-9,a-z,A-Z]/g)) {
		  alert("只能输英数字!");
		  return false;
	  	}
	}
	
	//省市县保存值
	function saveSsx(jc){
		$(jc).value=$(jc+'shen').value+"/"+$(jc+'shi').value+"/"+$(jc+'xian').value;
	}
	function addZpsc() {
		viewTempDiv('学生照片上传','tmpdiv1',400,200);
	}
	
	function addZp() {
		viewTempDiv('学生照片上传','addTp',400,200);
	}
	
	 	function PreviewImg(imgFile){
			var i = new Image()
			var f_val = imgFile.value
			var isImg = /jpg/i.test( (f_val.match(/\.(\w+)$/)[0]).toLowerCase() )
			var num_tmp = 0;
			i.src = f_val;
			if(!isImg){
				alert('图片格式不对，请选择正确的图片');
				//选择的不是图片的时候;
				return false;
			}
			var newSetInterval = setInterval(function(){
				num_tmp++;
				
				if(i.fileSize && i.fileSize != -1){
					if(i.fileSize<102400){
						$("newPreview").innerHTML="";
						clearInterval(newSetInterval);
					    var newPreview = document.getElementById("newPreview");
					    newPreview.innerHTML="";
					    newPreview.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgFile.value;
					    newPreview.style.width = "96px";
					    newPreview.style.height = "128px";
					    
					}else{
						alert("图片太大，请选择正确大小的图片！");
						clearInterval(newSetInterval);
						return false;
					}
				};
			}, 20);
	 	
	    
    }
    
	</script>
	<style type="text/css">
	#newPreview   /*这个就是预览的DIV的ID*/
	{
	    filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);
	}
	</style>
	</head>
	<body onload="divHTML()">
		<html:form action="/xsxxJbxx" enctype="multipart/form-data">
			<input type="hidden" name="pk" id="pk" value="${pk}"/>
			
			<!-- 需显示字段信息 -->
<%--			<logic:iterate name="xxszdList" id="xxszd" >--%>
<%--				<!-- 字段 -->--%>
<%--				<input type="hidden" name="xxszddm" id="xxszddm" value="${xxszd.zd}"/>--%>
<%--				<!-- 字段名 -->--%>
<%--				<input type="hidden" name="xxszdmc" id="xxszdmc" value="${xxszd.mc}"/>--%>
<%--				<!-- 字段长度 -->--%>
<%--				<input type="hidden" name="xxszdlen" id="xxszdlen" value="${xxszd.len}"/>--%>
<%--				<!-- 录入性质 -->--%>
<%--				<input type="hidden" name="xxszdlrxz" id="xxszdlrxz" value="${xxszd.lrxz}"/>--%>
<%--				<!-- 录入性质 -->--%>
<%--				<input type="hidden" name="xxszdlrxs" id="xxszdlrxs" value="${xxszd.lrxs}"/>--%>
<%--				--%>
<%--				--%>
<%--			</logic:iterate>--%>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>学籍异动-学籍异动修改</a>
				</p>
			</div>
			<div style="height: 450px;overflow:scroll;overflow-x:hidden">
			<table width="99%" >
					<tbody>
						<tr>
						<td>
							<logic:iterate name="xsqList" id="xsq">
									<logic:equal name="xsq" property="sfzd" value="是">
										<table class="formlist" border="0" align="center"
											style="width: 98%" id="xsq${xsq.xsqdm }">
											<thead>
												<tr>
													<th colspan="4">
														<span>${xsq.xsqmc }</span>

													</th>
												</tr>
											</thead>
											<tbody>
												<tr id="xsq${xsq.xsqdm }_1_tr">
													<td colspan="4">
														<div id="xsq${xsq.xsqdm }_1_div">
															<table width="100%">
																<!-- 照片显示在右边-->
																<logic:equal name="xsq" property="zpxs" value="是">
																	<logic:equal name="xsq" property="zpwz" value="右">
																		<tr>
																			<logic:iterate name="xsq" property="xshxx" id="xsh">
																			<logic:equal name="xsh" property="leftMap.dygl" value="1">
																				<logic:equal name="xsh" property="leftMap.dygh" value="1">
																				<th width="16%" >
																					<logic:equal name="xsh" property="leftMap.wkxz" value="不可为空">
																						<font color="red">*</font>
																					</logic:equal>
																					${xsh.leftMap.zdm }
																				</th>
																				<td width="34%" >
																					<%@ include file="leftzdxx.jsp"%>
																				</td>
																				</logic:equal>
																			</logic:equal>
																			</logic:iterate>
																			<!-- 右 -->
																			<th width="16%" rowspan="${xsq.zpszhs }">
																					学生照片
																			</th>
																			<td width="34%" rowspan="${xsq.zpszhs }">
																				<div id="newPreview" >
																					<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}" width="96" height="128"/>
																				</div>
																				<logic:equal value="add" name="oper">			
																				<a href="#" onclick="showDiv()"><font color="blue">上传照片</font></a>				
																				</logic:equal>
																				
																				<logic:notEqual value="add" name="oper">
																					<div>
																					<a href="#" onclick="addZpsc('uploadPicture.do?method=uploadPicture&type=stu&id=${rs.xh}')">上传照片</a>
																					
																					</div>
																				</logic:notEqual>
																				<button type="button" style="display: none" id="reloadF" onclick="refreshForm('/xgxt/stu_info_add.do?method=showStuInfo&oper=update&xh=${rs.xh}')"></button>						
																			</td>
																		</tr>
																		<logic:iterate name="xsq" property="xshxx" id="xsh" offset="1" length="${xsq.zpszhs-1}">
																			<logic:empty name="xsh" property="leftMap">
																			<tr>
																					<th width="16%">
																						&nbsp;
																					</th>
																					<td width="34%" >
																						&nbsp;
																					</td>
																			</tr>
																			</logic:empty>
																			<logic:notEmpty name="xsh" property="leftMap">
																			<tr>
																				<th width="16%" >
																					<logic:equal name="xsh" property="leftMap.wkxz" value="不可为空">
																						<font color="red">*</font>
																					</logic:equal>
																					${xsh.leftMap.zdm }
																				</th>
																				<td width="34%" >
																					<%@ include file="leftzdxx.jsp"%>
																				</td>
																			</tr>
																			</logic:notEmpty>
																		</logic:iterate>
																	</logic:equal>
																	<!-- 照片显示在左边-->
																	<logic:equal name="xsq" property="zpwz" value="左">
																		<tr>
																			<!-- 左 -->
																			<th width="16%" rowspan="${xsq.zpszhs }">
																					学生照片
																			</th>
																			<td width="34%" rowspan="${xsq.zpszhs }">
																				<div id="newPreview" >
																					<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}" width="96" height="128"/>
																				</div>
																				<logic:equal value="add" name="oper">
																				<a href="#" onclick="showDiv()"><font color="blue">上传照片</font></a>							
																				</logic:equal>
																				
																				<logic:notEqual value="add" name="oper">
																					<div>
																					<a href="#" onclick="addZpsc('uploadPicture.do?method=uploadPicture&type=stu&id=${rs.xh}')">上传照片</a>
																					
																					</div>
																				</logic:notEqual>
																				<button type="button" style="display: none" id="reloadF" onclick="refreshForm('/xgxt/stu_info_add.do?method=showStuInfo&oper=update&xh=${rs.xh}')"></button>						
																			</td>
																			<!-- 右 -->
																			<logic:iterate name="xsq" property="xshxx" id="xsh" length="1">
																				<logic:notEmpty name="xsh" property="rightMap">
																					<logic:equal name="xsh" property="rightMap.dygl" value="3">
																						<logic:equal name="xsh" property="rightMap.dygh" value="1">
																						<th width="16%" >
																							<logic:equal name="xsh" property="rightMap.wkxz" value="不可为空">
																									<font color="red">*</font>
																								</logic:equal>
																								${xsh.rightMap.zdm }
																						</th>
																						<td width="34%" >
																							<%@ include file="rightzdxx.jsp"%>
																						</td>
																						</logic:equal>
																					</logic:equal>
																				</logic:notEmpty>
																				<logic:empty name="xsh" property="rightMap">
																					<th width="16%" >
																						&nbsp;
																					</th>
																					<td width="34%" >
																						&nbsp;
																					</td>
																				</logic:empty>
																			</logic:iterate>
																		</tr>
																		<logic:iterate name="xsq" property="xshxx" id="xsh" offset="1" length="${xsq.zpszhs-1}">
																			<logic:empty name="xsh" property="rightMap">
																			<tr>
																					<th width="16%">
																						&nbsp;
																					</th>
																					<td width="34%" >
																						&nbsp;
																					</td>
																			</tr>
																			</logic:empty>
																			<logic:notEmpty name="xsh" property="rightMap">
																			<tr>
																				<th width="16%" >
																					<logic:equal name="xsh" property="rightMap.wkxz" value="不可为空">
																						<font color="red">*</font>
																					</logic:equal>
																					${xsh.rightMap.zdm }
																				</th>
																				<td width="34%" >
																					<%@ include file="rightzdxx.jsp"%>
																				</td>
																			</tr>
																			</logic:notEmpty>
																		</logic:iterate>
																	</logic:equal>
																	
																</logic:equal>
																<logic:iterate name="xsq" property="xshxx" id="xsh"
																	indexId="index">
																	<logic:empty name="xsh">
																		<tr>
																			<th width="16%">
																				&nbsp;
																			</th>
																			<td width="34%">
																				&nbsp;
																			</td>
																			<th width="16%">
																				&nbsp;
																			</th>
																			<td width="34%">
																				&nbsp;
																			</td>
																		</tr>
																	</logic:empty>
																	<logic:notEmpty name="xsh">
																		<logic:equal name="xsh" property="sfhb" value="是">
																			<tr>
																				<logic:empty name="xsh" property="leftMap">
																					<th width="16%">
																						&nbsp;
																					</th>
																					<td colspan="3" width="84%">
																						&nbsp;
																					</td>
																				</logic:empty>
																				<logic:notEmpty name="xsh" property="leftMap">
																					<th width="16%">
																						<logic:equal name="xsh" property="leftMap.wkxz" value="不可为空">
																						<font color="red">*</font>
																						</logic:equal>
																						${xsh.leftMap.zdm }
																					</th>
																					<td colspan="3" width="84%">
																						<%@ include file="leftzdxx.jsp"%>
																					</td>
																				</logic:notEmpty>
																			</tr>
																		</logic:equal>
																		<logic:equal name="xsh" property="sfhb" value="否">
																			<tr>
																				<logic:empty name="xsh" property="leftMap">
																					<th width="16%">
																						&nbsp;
																					</th>
																					<td width="34%">
																						&nbsp;
																					</td>
																				</logic:empty>
																				<logic:notEmpty name="xsh" property="leftMap">
																					<th width="16%">
																						<logic:equal name="xsh" property="leftMap.wkxz" value="不可为空">
																							<font color="red">*</font>
																						</logic:equal>
																						${xsh.leftMap.zdm }
																					</th>
																					<td width="34%">
																						<%@ include file="leftzdxx.jsp"%>
																					</td>
																				</logic:notEmpty>
																				<logic:empty name="xsh" property="rightMap">
																					<th width="16%">
																						&nbsp;
																					</th>
																					<td width="34%">
																						&nbsp;
																					</td>
																				</logic:empty>
																				<logic:notEmpty name="xsh" property="rightMap">
																					<th width="16%">
																						<logic:equal name="xsh" property="rightMap.wkxz" value="不可为空">
																							<font color="red">*</font>
																						</logic:equal>
																						${xsh.rightMap.zdm }
																					</th>
																					<td width="34%">
																						<%@ include file="rightzdxx.jsp"%>
																					</td>
																				</logic:notEmpty>
																					
																			</tr>
																		</logic:equal>
																	</logic:notEmpty>
																</logic:iterate>
															</table>
														</div>
													</td>
												</tr>

											</tbody>
										</table>
									</logic:equal>
								</logic:iterate>
						</td>
					</tr>
					<tr>
						<td>
							<div id="gdxsq"
								style="height: 400px;overflow:scroll;overflow-x:hidden">

								<logic:iterate name="xsqList" id="xsq">
									<logic:equal name="xsq" property="sfzd" value="否">
										<table class="formlist" border="0" align="center"
											style="width: 98%" >
											<thead>
												<tr>
												<th colspan="4" onclick="document.getElementById('xsq${xsq.xsqdm }').style.display=(document.getElementById('xsq${xsq.xsqdm }').style.display==''?'none':'')" ><span>${xsq.xsqmc }</span>
												</th></tr>
											</thead>
											<tbody id="xsq${xsq.xsqdm }"
												style="display:<logic:equal name="xsq" property="sfzk" value="否">none</logic:equal>">
												<tr id="xsq${xsq.xsqdm }_1_tr">
													<td colspan="4">
														<div id="xsq${xsq.xsqdm }_1_div">
															<table width="100%">
																<!-- 照片显示在右边-->
																<logic:equal name="xsq" property="zpxs" value="是">
																	<logic:equal name="xsq" property="zpwz" value="右">
																		<tr>
																			<logic:iterate name="xsq" property="xshxx" id="xsh">
																			<logic:equal name="xsh" property="leftMap.dygl" value="1">
																				<logic:equal name="xsh" property="leftMap.dygh" value="1">
																				<th width="16%" >
																					<logic:equal name="xsh" property="leftMap.wkxz" value="不可为空">
																						<font color="red">*</font>
																					</logic:equal>
																					${xsh.leftMap.zdm }
																				</th>
																				<td width="34%" >
																					<%@ include file="leftzdxx.jsp"%>
																				</td>
																				</logic:equal>
																			</logic:equal>
																			</logic:iterate>
																			<!-- 右 -->
																			<th width="16%" rowspan="${xsq.zpszhs }">
																				学生照片
																			</th>
																			<td width="34%" rowspan="${xsq.zpszhs }">
																				<div id="newPreview" >
																					<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}" width="96" height="128"/>
																				</div>
																				<logic:equal value="add" name="oper">
																				<a href="#" onclick="showDiv()"><font color="blue">上传照片</font></a>						
																				</logic:equal>
																				
																				<logic:notEqual value="add" name="oper">
																					<div>
																					<a href="#" onclick="addZpsc('uploadPicture.do?method=uploadPicture&type=stu&id=${rs.xh}')">上传照片</a>
																					
																					</div>
																				</logic:notEqual>
																				<button type="button" style="display: none" id="reloadF" onclick="refreshForm('/xgxt/stu_info_add.do?method=showStuInfo&oper=update&xh=${rs.xh}')"></button>						
																			</td>
																		</tr>
																		<logic:iterate name="xsq" property="xshxx" id="xsh" offset="1" length="${xsq.zpszhs-1}">
																			<logic:empty name="xsh" property="leftMap">
																			<tr>
																					<th width="16%">
																						&nbsp;
																					</th>
																					<td width="34%" >
																						&nbsp;
																					</td>
																			</tr>
																			</logic:empty>
																			<logic:notEmpty name="xsh" property="leftMap">
																			<tr>
																				<th width="16%" >
																						<logic:equal name="xsh" property="leftMap.wkxz" value="不可为空">
																							<font color="red">*</font>
																						</logic:equal>
																						${xsh.leftMap.zdm }
																				</th>
																				<td width="34%" >
																					<%@ include file="leftzdxx.jsp"%>
																				</td>
																			</tr>
																			</logic:notEmpty>
																		</logic:iterate>
																	</logic:equal>
																	<!-- 照片显示在左边-->
																	<logic:equal name="xsq" property="zpwz" value="左">
																		<tr>
																			<!-- 左 -->
																			<th width="16%" rowspan="${xsq.zpszhs }">
																				学生照片
																			</th>
																			<td width="34%" rowspan="${xsq.zpszhs }">
																				<div id="newPreview" >
																					<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}" width="96" height="128"/>
																				</div>
																				<logic:equal value="add" name="oper">
																				<a href="#" onclick="showDiv()"><font color="blue">上传照片</font></a>											
																				</logic:equal>
																				
																				<logic:notEqual value="add" name="oper">
																					<div>
																					<a href="#" onclick="addZpsc('uploadPicture.do?method=uploadPicture&type=stu&id=${rs.xh}')">上传照片</a>
																					
																					</div>
																				</logic:notEqual>
																				<button type="button" style="display: none" id="reloadF" onclick="refreshForm('/xgxt/stu_info_add.do?method=showStuInfo&oper=update&xh=${rs.xh}')"></button>						
																			</td>
																			<!-- 右 -->
																			<logic:iterate name="xsq" property="xshxx" id="xsh" length="1">
																				<logic:notEmpty name="xsh" property="rightMap">
																					<logic:equal name="xsh" property="rightMap.dygl" value="3">
																						<logic:equal name="xsh" property="rightMap.dygh" value="1">
																						<th width="16%" >
																							<logic:equal name="xsh" property="rightMap.wkxz" value="不可为空">
																									<font color="red">*</font>
																								</logic:equal>
																								${xsh.rightMap.zdm }
																						</th>
																						<td width="34%" >
																							<%@ include file="rightzdxx.jsp"%>
																						</td>
																						</logic:equal>
																					</logic:equal>
																				</logic:notEmpty>
																				<logic:empty name="xsh" property="rightMap">
																					<th width="16%" >
																						&nbsp;
																					</th>
																					<td width="34%" >
																						&nbsp;
																					</td>
																				</logic:empty>
																			</logic:iterate>
																		</tr>
																		<logic:iterate name="xsq" property="xshxx" id="xsh" offset="1" length="${xsq.zpszhs-1}">
																			<logic:empty name="xsh" property="rightMap">
																			<tr>
																					<th width="16%">
																						&nbsp;
																					</th>
																					<td width="34%" >
																						&nbsp;
																					</td>
																			</tr>
																			</logic:empty>
																			<logic:notEmpty name="xsh" property="rightMap">
																			<tr>
																				<th width="16%" >
																					<logic:equal name="xsh" property="rightMap.wkxz" value="不可为空">
																						<font color="red">*</font>
																					</logic:equal>
																					${xsh.rightMap.zdm }
																				</th>
																				<td width="34%" >
																					<%@ include file="rightzdxx.jsp"%>
																				</td>
																			</tr>
																			</logic:notEmpty>
																		</logic:iterate>
																	</logic:equal>
																	
																</logic:equal>
																<logic:iterate name="xsq" property="xshxx" id="xsh"
																	indexId="index">
																	<logic:empty name="xsh">
																		<tr>
																			<th width="16%">
																				&nbsp;
																			</th>
																			<td width="34%">
																				&nbsp;
																			</td>
																			<th width="16%">
																				&nbsp;
																			</th>
																			<td width="34%">
																				&nbsp;
																			</td>
																		</tr>
																	</logic:empty>
																	<logic:notEmpty name="xsh">
																		<logic:equal name="xsh" property="sfhb" value="是">
																			<tr>
																				<logic:empty name="xsh" property="leftMap">
																					<th width="16%">
																						&nbsp;
																					</th>
																					<td colspan="3" width="84%">
																						&nbsp;
																					</td>
																				</logic:empty>
																				<logic:notEmpty name="xsh" property="leftMap">
																					<th width="16%">
																						<logic:equal name="xsh" property="leftMap.wkxz" value="不可为空">
																						<font color="red">*</font>
																						</logic:equal>
																						${xsh.leftMap.zdm }
																					</th>
																					<td colspan="3" width="84%">
																						<%@ include file="leftzdxx.jsp"%>
																					</td>
																				</logic:notEmpty>
																			</tr>
																		</logic:equal>
																		<logic:equal name="xsh" property="sfhb" value="否">
																			<tr>
																				<logic:empty name="xsh" property="leftMap">
																					<th width="16%">
																						&nbsp;
																					</th>
																					<td width="34%">
																						&nbsp;
																					</td>
																				</logic:empty>
																				<logic:notEmpty name="xsh" property="leftMap">
																					<th width="16%">
																						<logic:equal name="xsh" property="leftMap.wkxz" value="不可为空">
																							<font color="red">*</font>
																						</logic:equal>
																						${xsh.leftMap.zdm }
																					</th>
																					<td width="34%">
																						<%@ include file="leftzdxx.jsp"%>
																					</td>
																				</logic:notEmpty>
																				<logic:empty name="xsh" property="rightMap">
																					<th width="16%">
																						&nbsp;
																					</th>
																					<td width="34%">
																						&nbsp;
																					</td>
																				</logic:empty>
																				<logic:notEmpty name="xsh" property="rightMap">
																					<th width="16%">
																						<logic:equal name="xsh" property="rightMap.wkxz" value="不可为空">
																							<font color="red">*</font>
																						</logic:equal>
																						${xsh.rightMap.zdm }
																					</th>
																					<td width="34%">
																						<%@ include file="rightzdxx.jsp"%>
																					</td>
																				</logic:notEmpty>
																					
																			</tr>
																		</logic:equal>
																	</logic:notEmpty>
																</logic:iterate>
															</table>
														</div>
													</td>
												</tr>

											</tbody>
										</table>
										
									</logic:equal>
								</logic:iterate>
								<%-- 新版本家庭信息与学生信息放在一块维护  --%>
								<table class="formlist" border="0" align="center"
												style="width: 98%">
											<logic:equal value="new" name="edition">
												<%@ include file="/xsxx/stu_info_family.jsp" %>
											</logic:equal>
								</table>
								<%-- end --%>
							</div>
						</td>
					</tr>
					</tbody>
					
				</table>
				</div>
				<table class="formlist" border="0" align="center" style="width: 100%">
					<tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
				          <div class="btn">
					         <%--武汉理工大学--%>
							<logic:notEqual value="10497" name="xxdm">
							<%--浙江机电职业技术学院--%>
							<logic:notEqual value="12861" name="xxdm">
								<logic:notEqual value="stu" name="userType">	
									<logic:notEqual value="view" name="userOper">					
									<button type="button" class="button2" id="buttonSave" style="height:20px;width:80px"
										onclick="dataCommit()">
										保 存
									</button>			
									</logic:notEqual>
								</logic:notEqual>
								&nbsp;&nbsp;&nbsp;
								<button type="button" onclick="Close();return false;"
									style="width:80px">
									关闭
								</button>
							</logic:notEqual>
							</logic:notEqual>
				          </div>
				        </td>
				      </tr>
				    </tfoot>
				    </table>
				<logic:notEmpty name="result" scope="request">			
					<logic:equal value="true" name="result" scope="request">
						<script>
						alert("操作成功！");
						Close();					
						if(window.dialogArguments){
							window.dialogArguments.document.getElementById('search_go').click();
						}		
					</script>
					</logic:equal>
					<logic:equal value="false" name="result" scope="request">
						<script>
						Close();					
						if(window.dialogArguments){
							window.dialogArguments.document.getElementById('search_go').click();
						}
					</script>
					</logic:equal>
				</logic:notEmpty>
				
				
				<div id="tmpdiv1" style="display:none">
				<iframe name='mainFrame' style='height:100%; width:100%; ' marginwidth='0' marginheight='0' framespacing='0' frameborder='0' scrolling='yes' src='uploadPicture.do?method=uploadPicture&type=stu&id=${rs.xh}'></iframe>
				</div>	
				
		</html:form>
	</body>
	<script defer>
<%--	tssjInit();--%>
	</script>
</html>
