<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		function onShow(){ 
			
			searchRs()
			
		}
		
		//查询结果集
		function searchRs(){
		
				//按钮控制
				var url = "xljk_hzny_ajax.do?method=zxsglSearch";
				
				//ie版本
				var ie = "ie";
	
				var parameter={}
				parameter["ie"]=ie;
	
				showWaitingDiv("1000");
				
				zxsglSearch(url,parameter);
			
		
		}
		
		function zxsglSearch(url,parameter){

			var currentPage = "1";
			if($("currentPage")){
				currentPage = $("currentPage").value;
			}
			
			var editPageSize = "";
			if($("editPageSize")){
				editPageSize = $("editPageSize").value;	
			}
				
			var pagesize = "";
			if($("pagesize")){
				pagesize = $("pagesize").value;
			}
		
			var input_mhcx = "";
			if($("input_mhcx")){
				input_mhcx = $("input_mhcx").value;
			}
			
			var mhcx_lx = "";
			if($("mhcx_lx")){
				mhcx_lx = $("mhcx_lx").value;
			}
		
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
			
			parameter["currentPage"]=currentPage;
			parameter["editPageSize"]=editPageSize;
			parameter["pagesize"]=pagesize;
			parameter["input_mhcx"]=escape(input_mhcx);
			parameter["mhcx_lx"]=mhcx_lx;
			parameter["searchTj"]=searchTj.join("!!@@!!");
			parameter["searchTjz"]=searchTjz.join("!!@@!!");
			parameter["searchLx"]=searchLx.join("!!@@!!");
		  
		 	$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			jQuery("#div_rs").load(url,parameter,function(){
				setTimeout("setPageInfo()",500);
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
			});
		}

		
		function save(tag){
			if(tag=="ok"){
		
				//主键
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// 得到JSON对象
		        var parameter ={};;
			
				jQuery("[name=div_pkValue]:checked").each(function(i){
							
					pkValue[i] =escape(jQuery(this).val());
							
				});
				
				var xh=new Array();
				jQuery("[name=div_pkValue]:checked").each(function(i){
							
					xh[i] =escape(jQuery(this).val());
							
				});
				
				var url = "xljk_hzny_ajax.do?method=zxsglModi";
	          	 
			 	parameter["str_byjd"]=escape(byjdV);
				parameter["array_pkValue"]=pkValue.join("!!array!!");
				parameter["array_xh"]=xh.join("!!array!!");
	
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result,function(tag){
						
							if(tag=="ok"){
						
								closeWindown();	
								searchRs();
								
							}
						});
						
						
					}
				);
				
				jQuery.ajaxSetup({async:true});
			}
			
		
		}
		
		function showModi(){
		
			var len=jQuery("[name=div_pkValue]:checked").length;
			
			if(len==1){	
				
				var pkValue=jQuery("[name=div_pkValue]:checked").val();
				
				var url="xljk_hzny.do?method=zxsglDetail&doType=modi";
				
				url+="&pkValue="+pkValue;
				
				showTopWin(url,800,600);
			}else{
				
				alertInfo("请勾选一条需要修改的记录！");
				
				return false;
			}
		}
		
		function showView(){
		
			var len=jQuery("[name=div_pkValue]:checked").length;
			
			if(len==1){	
				
				var pkValue=jQuery("[name=div_pkValue]:checked").val();
				
				var xysh=jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(5).html();
				
				var url="xljk_hzny.do?method=zxsglDetail&doType=view";
				
				url+="&pkValue="+pkValue;
				
				showTopWin(url,800,600);
			}else{
				
				alertInfo("请勾选一条需要查看的记录！");
				
				return false;
			}
		}
		
		function deleteYbbx(){
			
			var n=jQuery("[name=div_pkValue]:checked").length;
			
			var blog=true;
			if(n>0){
				

				if(blog){
					confirmInfo("该操作将会删除心理咨询师信息，是否确定继续操作？",function(tag){
						
						if(tag=="ok"){
							
							var pkValue=new Array();
							
							var xh=new Array();
							
							jQuery("[name=div_pkValue]:checked").each(function(i){
								
								pkValue[i]=jQuery(this).val();
							
							});
							
							var parameter={}
							
							
							parameter["pkValue"]=escape(pkValue.join("!!array!!"));
							
							var url= "xljk_hzny_ajax.do?method=delete";
							
							jQuery.ajaxSetup({async:false});	
							
							jQuery.post(url,
								parameter,
								function(result){
								
									alertInfo(result,function(tag){
										
										if(tag=="ok"){
											searchRs();
										}
									
									});
									
								}
							);
							
							jQuery.ajaxSetup({async:true});
						}
					});
				}
			}else{
				
				alertInfo("请勾选需要删除的数据！",function(tag){
					
					if(tag=="ok"){
						return false;
					}
				
				});
			}
		}
		</script>
	</head>
	<body onload="onShow()">

		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/xszzYbbx" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />

			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="#" onclick="showTopWin('xljk_hzny.do?method=zxsglDetail&doType=add',800,600);return false;" class="btn_zj">增加</a>
						</li>
						<li>
							<a href="#" onclick="showModi();return false;" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="#" onclick="deleteYbbx();return false;" class="btn_sc">删除</a>
						</li>
						</logic:equal>
						<li>
							<a href="#" onclick="showView();return false;" class="btn_ck">查看</a>
						</li>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="#" onclick="impAndChkData();return false;" class="btn_dr">导入数据</a>
						</li>
						</logic:equal>
						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">导出设置</a></li>
						<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出数据</a></li>
						
					</ul>
				</div>
				
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- 多功能操作区 end-->

			<!-- 内容显示区开始 -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- 查询得到的数据量显示区域 -->
						</p>
					</div>
				</div>
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:notEmpty name="rsList">
							<font color="blue">提示：单击表头可以排序；双击记录可查看详细信息</font>
						</logic:notEmpty> </span>
				</h3>
				<div id="div_rs"
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=xszzYbbxForm"></jsp:include>
<%--				<script type="text/javascript">--%>
<%--						$('choose').className="hide";--%>
<%--				</script>--%>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->
			
			
			<div id="div_detail" style="display:none">
			</div>		
				
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
