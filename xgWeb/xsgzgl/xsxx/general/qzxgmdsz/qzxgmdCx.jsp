<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		function onShow(){ 
			searchRs();
		}
		var DCCLBH = "general_xsxx_qzxgmd.do";//dcclbh,导出功能编号
		//自定义导出 功能
		function exportConfig() {
			customExport(DCCLBH, qzmdExportData);
		}

		// 导出方法
		function qzmdExportData() {
			setSearchTj();//设置高级查询条件
			var url = "general_xsxx_qzxgmd.do?method=qzmdExportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		function qzmddc() {
			setSearchTj();//设置高级查询条件
			var url = "general_xsxx_qzxgmd.do?method=qzmdExportData&dcclbh=" + "general_xsxx_qzxgmd.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		//强制修改
		function qzxg(){
			var checkBoxArr = document.getElementsByName("primarykey_checkVal");
			var pk = new Array();   	
			var i=0;			  
			jQuery("input[name=primarykey_checkVal]:checked").each(function(){pk[i] = jQuery(this).val();i++;});
						jQuery.ajaxSetup({async:false});
						var url = "general_xsxx_qzxgmd.do?method=checkQzxg";
						//参数
						//模糊查询
						var input_mhcx = "";
						if($("input_mhcx")){
							input_mhcx = $("input_mhcx").value;
						}
						
						var mhcx_lx = "";
						if($("mhcx_lx")){
							mhcx_lx = $("mhcx_lx").value;
						}

						//点击查询
						var searchLx = new Array();
						var searchTj = new Array();
						var searchTjz = new Array();
						
						var n=0;
						var m=3;
						
						searchLx[0]="xy";
						searchLx[1]="zy";
						searchLx[2]="bj";
						
						for(var i=0;i<jytj.length;i++){
							searchLx[m]=jytj[i];
							m++;
						}

						var tj_num = $("searchTjDiv").getElementsByTagName('input').length;
							
						for(var j=0;j<tj_num;j++){
							var obj = $("searchTjDiv").getElementsByTagName('input')[j];
							searchTj[n]=obj.name;
							searchTjz[n]=escape(obj.value);
							n++;
						}
						setSearchTj();
					 	var parameter = {
					 			"primarykey_checkVal":pk,
					 			"input_mhcx":escape(input_mhcx),
					 			"mhcx_lx":mhcx_lx,
					 			"searchTj":searchTj.join("!!@@!!"),
					 			"searchTjz":searchTjz.join("!!@@!!"),
					 			"searchLx":searchLx.join("!!@@!!")
						};
						jQuery.post(url,
							parameter,
							function(result){
							if(""!=result){
								confirmInfo(result,function(tag){
										if(tag=="ok"){
											jQuery.ajaxSetup({async:false});
											var url = "general_xsxx_qzxgmd.do?method=szQzxg";
											//参数
										 	var parameter = {
										 			"primarykey_checkVal":pk,
										 			"input_mhcx":escape(input_mhcx),
										 			"mhcx_lx":mhcx_lx,
										 			"searchTj":searchTj.join("!!@@!!"),
										 			"searchTjz":searchTjz.join("!!@@!!"),
										 			"searchLx":searchLx.join("!!@@!!")
											};
											
										 	$("divWaiting").style.display="";
											$("divDisable").style.display="";
											
											jQuery.post(url,
												parameter,
												function(result){
													alertInfo(result);
													searchRs();
												}
											);
											jQuery.ajaxSetup({async:true});
										}
									});
									
								}
							}
						);
						jQuery.ajaxSetup({async:true});
				
		}

		function qxqzxg(){
			var checkBoxArr = document.getElementsByName("primarykey_checkVal");
			var pk = new Array();   	
			var i=0;			  
			jQuery("input[name=primarykey_checkVal]:checked").each(function(){pk[i] = jQuery(this).val();i++;});
						jQuery.ajaxSetup({async:false});
						var url = "general_xsxx_qzxgmd.do?method=checkQzxg";
						//参数
						//模糊查询
						var input_mhcx = "";
						if($("input_mhcx")){
							input_mhcx = $("input_mhcx").value;
						}
						
						var mhcx_lx = "";
						if($("mhcx_lx")){
							mhcx_lx = $("mhcx_lx").value;
						}

						//点击查询
						var searchLx = new Array();
						var searchTj = new Array();
						var searchTjz = new Array();
						
						var n=0;
						var m=3;
						
						searchLx[0]="xy";
						searchLx[1]="zy";
						searchLx[2]="bj";
						
						for(var i=0;i<jytj.length;i++){
							searchLx[m]=jytj[i];
							m++;
						}

						var tj_num = $("searchTjDiv").getElementsByTagName('input').length;
							
						for(var j=0;j<tj_num;j++){
							var obj = $("searchTjDiv").getElementsByTagName('input')[j];
							searchTj[n]=obj.name;
							searchTjz[n]=escape(obj.value);
							n++;
						}
						setSearchTj();
					 	var parameter = {
					 			"primarykey_checkVal":pk,
					 			"input_mhcx":escape(input_mhcx),
					 			"mhcx_lx":mhcx_lx,
					 			"searchTj":searchTj.join("!!@@!!"),
					 			"searchTjz":searchTjz.join("!!@@!!"),
					 			"searchLx":searchLx.join("!!@@!!")
						};
						jQuery.post(url,
							parameter,
							function(result){
							if(""!=result){
								confirmInfo(result,function(tag){
										if(tag=="ok"){
											jQuery.ajaxSetup({async:false});
											var url = "general_xsxx_qzxgmd.do?method=qxQzxg";
											//参数
										 	var parameter = {
										 			"primarykey_checkVal":pk,
										 			"input_mhcx":escape(input_mhcx),
										 			"mhcx_lx":mhcx_lx,
										 			"searchTj":searchTj.join("!!@@!!"),
										 			"searchTjz":searchTjz.join("!!@@!!"),
										 			"searchLx":searchLx.join("!!@@!!")
											};
											
										 	$("divWaiting").style.display="";
											$("divDisable").style.display="";
											
											jQuery.post(url,
												parameter,
												function(result){
													alertInfo(result);
													searchRs();
												}
											);
											jQuery.ajaxSetup({async:true});
										}
									});
									
								}
							}
						);
						jQuery.ajaxSetup({async:true});
			}
		
		//执行查询操作
		function searchRs(){
			jQuery.ajaxSetup({async:false});
			var v4Path = stylePath;//v4样式路径
			var url = "general_xsxx_qzxgmd.do?method=searchQzxgmd";
			var ie = "ie";
			
			var otherValue = [ie,v4Path];
			searchRsByAjax(url,otherValue);
			setTimeout("setDivHeight()","2000");
			jQuery.ajaxSetup({async:true});
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  ondrag="return false">
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
					本功能主要针对学生登录时是否需要强制修改完善学生信息进行设置 。<br/>
					当<font color="red">不勾选</font>任何学生时，对符合选中<font color="red">查询条件</font>的学生设置强制修改状态<br/>
					当<font color="red">勾选</font>学生时，对<font color="red">选中</font>的学生设置强制修改状态。
				</span>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/general_xsxx_qzxgmd" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						
						<li>
							<a href="#" onclick="qzxg();return false;" class="btn_sz">
								设为强制修改
							</a>
						</li>
						<li>
							<a href="#" onclick="qxqzxg();return false;" class="btn_sz">
								取消强制修改
							</a>
						</li>
					</logic:equal>
					<li>
						<a href="#" onclick="exportConfig();return false;" class="btn_dc">
							导出
						</a>
					</li>
					</ul>
				</div>
				<!-- 按钮 end-->
				
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			
			<!-- 内容显示区开始 -->
			<div class="main_box">
			<h3 class="datetitle_01">
					<span> 查询结果</span>
				</h3>
				<div id="div_rs"
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=qzxgmdSzForm"></jsp:include>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
		<logic:present name="message">
			<script>
				alertInfo('${message}');
			</script>
		</logic:present>
	</body>
</html>