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
			searchRs();
		}
		
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}
		
		function searchRs(){
			var url = "gyglnew_qszf_ajax.do?method=qszfCx";
			var ie = "10.0";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}
		
		function showModi(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len==1){	
				var pkValue=jQuery("[name=div_pkValue]:checked").val();	
				var url="gyglnew_qszf.do?method=qszfXg";		
				url+="&pkValue="+pkValue;
				showTopWin(url,600,450);
			}else{
				alertInfo("请勾选一条需要修改的记录！");
				return false;
			}
		}
		
		function deleteQszf(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			var blog=true;
			if(len>=1){	
				if(blog){
					confirmInfo("该操作将会删除寝室走访信息，是否确定继续操作？",function(tag){
						if(tag=="ok"){
							var array = jQuery("[name=div_pkValue]:checked");
							var str = "";
							for (var i=0;i<array.length;i++) {
								var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
								str += pkValue+"!!@@";
							}
							var parameter={}
							var url="gyglnew_qszf.do?method=qszfSc";	
							parameter["str"]=str;							
							jQuery.ajaxSetup({async:false});	
							jQuery.post(url,
								parameter,
								function(result){
									alertInfo(result,function(tag){
										if(tag=="ok"){
											onShow();
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
		
		jQuery(function(){
			onShow();
		})
	</script>
	</head>
	<body >

		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/gyglnew_qszf" method="post">

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
							<a href="#" onclick="showTopWin('gyglnew_qszf.do?method=qszfZj',600,450);return false;" class="btn_zj">增加</a>
						</li>
						<li>
							<a href="#" onclick="showModi();return false;" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="#" onclick="deleteQszf();return false;" class="btn_sc">删除</a>
						</li>
						</logic:equal>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="#" onclick="impAndChkData();return false;" class="btn_dr">导入</a>
						</li>
						</logic:equal>
						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">导出设置</a></li>
						<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出</a></li>
						<li><a href="#" class="btn_dc" onclick="showTopWin('gyglnew_qszf.do?method=xqcsTj',500,400);return false;">走访次数统计</a></li>
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
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=qszfForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
				
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
