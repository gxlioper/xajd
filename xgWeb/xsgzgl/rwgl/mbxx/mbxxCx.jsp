<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：yyp -->
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
			var url = "rwgl_mbxxgl_ajax.do?method=mbxxCx";
			var ie = "ie";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}

		//修改
		function bmxxXg(type){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len==1){
				var pkValue = jQuery("[name=div_pkValue]:checked").val()
				var parameter={}
				var url="rwgl_mbxxgl.do?method=mbxxXg&doType="+type+"&pkValue="+pkValue;
				showTopWin(url,700,530);
			}else{
				alertInfo("请勾选一条需要修改的记录！",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
			}
		}
		
		//删除信息
		function bmxxSc(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len>=1){
				confirmInfo("是否确定删除勾选的记录？",function(tag){
					if(tag=="ok"){
						var array = jQuery("[name=div_pkValue]:checked");
						var str = "";
						for (var i=0;i<array.length;i++) {
							var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
							str += pkValue+"!!@@!!";
						}
						var parameter={}
						var url="rwgl_mbxxgl_ajax.do?method=mbxxSc";
						parameter["pkValue"]=escape(str);
						jQuery.ajaxSetup({async:false});	
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
			}else{
				alertInfo("请勾选需要删除的记录信息！",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
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


		<html:form action="/rwgl_mbxxgl" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<!-- 隐藏域 -->
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="#" class="btn_zj" onclick="showTopWin('rwgl_mbxxgl.do?method=mbxxZj',700,530);return false;">增加</a>
						</li>
						<li>
							<a href="#" class="btn_xg" onclick="bmxxXg('update');return false;">修改</a>
						</li>
						<li>
							<a href="#" class="btn_sc" onclick="bmxxSc();return false;">删除</a>
						</li>
						</logic:equal>
						<li>
							<a href="#" class="btn_ck" onclick="bmxxXg('view');return false;">查看</a>
						</li>
						<li><a href="#" onclick="choiceFields();return false;" class="btn_qx">导出设置</a></li>
						<li><a href="#" onclick="configureExportData();return false;" class="btn_dc">导出数据</a></li>
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
					page="/sjcz/turnpage.jsp?form=rwglMbxxForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>