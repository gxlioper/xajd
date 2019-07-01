<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>
		--%><%@ include file="/syscommon/head.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		function onShow(){ 
			searchRs();
		}

		//增加
		function addCfsb(){
			var url="general_wjcf_cfsb_ajax.do?method=zjWjcf";
			//showTopWin(url,'780','660');
			showDialog("", 800, 500, url);
		}

		//修改
		function modCfsb(){
			var objs = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked");
			var cfId="";
			if(objs.length!=1){
				alertError("请选择一条记录！");
				return false;
			}else{
				cfId+=objs[0].value;
			}

			var pk = new Array();   	
			var i=0;			  
			jQuery("input[name=primarykey_checkVal]:checked").each(function(){pk[i] = jQuery(this).val();i++;});
			
			jQuery.ajaxSetup({async:false});
			var url = "general_wjcf_cfsb_ajax.do?method=checkCfsb";
			//参数
		 	var parameter = {
		 			"primarykey_checkVal":pk
			};
			
			jQuery.post(url,
				parameter,
				function(result){
				if(""!=result){
					alertError("学号为："+result.substr(0,result.length-1)+"的违纪处分已经审核，不能修改！");
					return false;
					}else{
						var url="general_wjcf_cfsb_ajax.do?method=xgCfsb&cfId="+cfId;
						//showTopWin(url,'780','660');
						showDialog("", 800, 500, url);
					}
				}
			);
			jQuery.ajaxSetup({async:true});

		}

		//查看
		function viewCfsb(){
			var objs = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked");
			var cfId="";
			if(objs.length!=1){
				alertError("请选择一条记录！");
				return false;
			}else{
				cfId+=objs[0].value;
			}
			var url="general_wjcf_cfsb_ajax.do?method=ckCfsb&cfId="+cfId;
			//showTopWin(url,'780','660');
			showDialog("", 800, 500, url);
		}

		//删除
		function csCfsb(){
			var checkBoxArr = document.getElementsByName("primarykey_checkVal");
			var flag = false;
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					flag = true;
				}
			}
			var pk = new Array();   	
			var i=0;			  
			jQuery("input[name=primarykey_checkVal]:checked").each(function(){pk[i] = jQuery(this).val();i++;});
			
			if(flag){
				confirmInfo("此操作将会删除您所勾选的记录，是否确定？",function(tag){
					if(tag=="ok"){

						jQuery.ajaxSetup({async:false});
						var url = "general_wjcf_cfsb_ajax.do?method=checkCfsb";
						//参数
					 	var parameter = {
					 			"primarykey_checkVal":pk
						};
						
						jQuery.post(url,
							parameter,
							function(result){
							if(""!=result){
								alertError("学号为："+result.substr(0,result.length-1)+"的违纪处分已经审核或者无需审核（处分成立），不能删除！");
								return false;
								}else{

									jQuery.ajaxSetup({async:false});
									var url = "general_wjcf_cfsb_ajax.do?method=scCfsb";
									//参数
								 	var parameter = {
								 			"primarykey_checkVal":pk
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
							}
						);
						jQuery.ajaxSetup({async:true});
						
					}
				});
		}else{
			alertInfo("请选择要删除的数据！");
			return false;
			}
		}

		//执行查询操作
		function searchRs(){
			jQuery.ajaxSetup({async:false});
			var v4Path = stylePath;//v4样式路径
			var url = "general_wjcf_cfsb_ajax.do?method=searchWjcfResult";
			var ie = "ie";
			
			var otherValue = [ie,v4Path];
			searchRsByAjax(url,otherValue);
			setTimeout("setDivHeight()","2000");
			jQuery.ajaxSetup({async:true});
		}
		
		jQuery(function(){
			onShow();
		})
		
		function cfsbExportConfig() {
		//DCCLBH导出功能编号,执行导出函数 
		customExport("general_wjcf_cfsb_ajax.do", cfsbExportData);
		}
		
	
		
		// 导出方法
		function cfsbExportData() {
			setSearchTj();//设置高级查询条件
			var url = "general_wjcf_cfsb_ajax.do?method=cfsbExportData&dcclbh=" + "general_wjcf_cfsb_ajax.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		</script>
	</head>
	<body  ondrag="return false">
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
				
		</div>
		<!-- 标题 end-->
		
		
		<html:form action="/general_xsxx" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li>
							<a href="#" onclick="addCfsb();return false;" class="btn_zj">
								增加
							</a>
						</li>
						<li>
							<a href="#" onclick="modCfsb();return false;" class="btn_xg">
								修改
							</a>
						</li>
						<li>
							<a href="#" onclick="csCfsb();return false;" class="btn_sc">
								删除
							</a>
						</li>
						
						</logic:equal>
						<li>
							<a href="#" onclick="viewCfsb();return false;" class="btn_ck">
								查看
							</a>
						</li>
						<li><a href="#" class="btn_dc" onclick="cfsbExportConfig();return false;">导出</a></li>
						
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
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=wjcfGeneralForm"></jsp:include>
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