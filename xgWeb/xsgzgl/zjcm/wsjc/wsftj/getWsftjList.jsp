<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="xsgzgl/zjcm/wsjc/wsftj/js/wsftj.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" defer="defer"> 
		var first = false;
		//初始化
		function onShow(){
			first=true;
		}
		
		//查询结果集
		function searchRs(){
			jQuery.ajaxSetup({async:false});
			
			var url = "cjWsf_wsftjajax.do?method=getWsftjList";
			var ie = "ie";
			
			var otherValue = [ie];
			
			if(checkSearch()){
				searchRsByAjax(url,otherValue);
			}
			
			jQuery.ajaxSetup({async:true});
			first=false;
		}
		
		//检验可否查询
		function checkSearch(){
		
			var flag = true;

			var nd_num =  jQuery("a[name=a_name_nd]").length;
			var yf_num =  jQuery("a[name=a_name_yf]").length;
			if(!first&&nd_num != "1"){
				alertError("年度条件不可为空，且只能选择一个！");
				flag = false;
			}else if ( !first&&yf_num != "1"){
				alertError("月份条件不可为空，且只能选择一个！ ");
				flag = false;
			}else {
				var map = getSuperSearch();
				jQuery.post("cjWsftj.do?method=canSearch",map,function(data) {
						if(data != "" && data != null){
							alertError(data["message"]);
							flag = false;
						}
					}, 'json');
			}					
			return flag;
		}
		
		//导出为Excel
		function expToExcel(){
		
			if(checkSearch()){
				var xn = jQuery("a[name=a_name_xn]").eq(0).attr("id").replace("a_id_","");
				var xq = jQuery("a[name=a_name_xq]").eq(0).attr("id").replace("a_id_","");
				var bjdm =jQuery("a[name=a_name_bj]").eq(0).attr("id").replace("a_id_","");
				
				var url = "general_tjcx_zcbjmd_ajax.do?method=expZcbjmd";
					url+= "&str_xn="+xn;
					url+= "&str_xq="+xq;
					url+= "&str_bjdm="+bjdm;
					url+= "&str_lx=djks";
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
			}
		}
		
		jQuery(function(){
			onShow();
			searchRs();		
		})
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
		
		<html:form action="/cjWsftj" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
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

				<!-- 内容显示区开始 -->
				<div class="main_box">
					<div class="mid_box">
						<div class="title">
							<p>
								<!-- 查询得到的数据量显示区域 -->
							</p>
						</div>
					</div>
					<!-- From内容 -->
					<div id="div_rs">
					
					</div>			
				</div>
				<!-- 内容显示区开始 end-->
			</div>
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>