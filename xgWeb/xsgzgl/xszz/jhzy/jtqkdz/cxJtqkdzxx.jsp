<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：lt -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>		
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		jQuery(document).ready(function(){ 
			searchRs();
		});

		
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}

		function searchRs(){
			var url = "xszz_jhzy_jtqkdzAjax.do";
			var ie = "ie";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}

		//修改
		function updateJtdzxx(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len==1){	
				var pkValue=jQuery("[name=div_pkValue]:checked").val();	
				var url="xszz_jhzy_xgJtqkdz.do";		
				url+="?pkStr="+pkValue;
				showTopWin(url,900,800);
			}else{
				alertInfo("请勾选一条记录进行修改！");
				return false;
			}
		}

		//查看
		function showView(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len==1){	
				var pkValue=jQuery("[name=div_pkValue]:checked").val();	
				var url="xszz_jhzy_ckJtqkdz.do";			
				url+="?pkStr="+pkValue;
				showTopWin(url,900,800);
			}else{
				alertInfo("请勾选一条记录进行查看！");
				return false;
			}
		}
		
		//删除
		function deleteJtqkdzxx(){
			
			var n=jQuery("[name=div_pkValue]:checked").length;
			
			var blog=true;
			if(n>0){
				if(blog){
					confirmInfo("该操作将会删除家庭情况调查信息，是否确定继续操作？",function(tag){
						
						if(tag=="ok"){
							
							var pkValue=new Array();
							
							var xh=new Array();
							
							jQuery("[name=div_pkValue]:checked").each(function(i){
								
								pkValue[i]=jQuery(this).val();
							
							});
							
							var parameter={}

							parameter["pkValue"]=escape(pkValue.join("!!@@!!"));
							
							var url= "xszz_jhzy_scJtqkdz.do";
							
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
		
		
		function showPrint(){
		
			var n=jQuery("[name=div_pkValue]:checked").length;
			
			if(n==1){
				var xh=jQuery("[name=div_pkValue]:checked").parents("tr").find("[name=xh_array]").eq(0).val();
				var xn=jQuery("[name=div_pkValue]:checked").parents("tr").find("[name=xn_array]").eq(0).val();
				showOpenWindow("jtqkdzGl.do?method=jtqkdcb&xh="+xh+"&xn="+xn);
			}else{
				
				alertInfo("请勾选一条需要打印的记录！");
				return false;
			}
		}
		</script>
	</head>
	<body>

		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
				<!-- 提示信息 end-->
		<logic:notEmpty name="czqx">
		<div class="prompt">
			<h3>
				<span>提示：${czqx }</span>
			</h3>
			<p>
				
			</p>
		</div>
</logic:notEmpty>
		<html:form action="/jtqkdzGl" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				
				<div class="buttonbox">
					<ul>
						<logic:empty name="czqx"><!-- 在时间范围内才能操作 -->
						<li>
							<a href="#" class="btn_zj" onclick="showTopWin('xszz_jhzy_zjJtqkdz.do',900,800);return false;">增加</a>
						</li>
						<li>
							<a href="#" class="btn_xg" onclick="updateJtdzxx();return false;">修改</a>
						</li>
						<li>
							<a href="#" class="btn_sc" onclick="deleteJtqkdzxx();return false;">删除</a>
						</li>
						</logic:empty>
						<li>
							<a href="#" class="btn_zj" onclick="showView();return false;">查看</a>
						</li>
						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">导出设置</a></li>
						<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出数据</a></li>
						<li>
							<a href="#" class="btn_dy" onclick="showPrint();return false;" onclick="return false;">打印报表</a>
						</li>
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
					<span> 查询结果&nbsp;&nbsp; </span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=xszzJtqkdzActionForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>