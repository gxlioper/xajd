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
			var message = "本功能统计必须选择";
				message+= "<font color='red'>一个</font>学年、";
				message+= "<font color='red'>一个</font>学期及";
				message+= "<font color='red'>一个</font>班级";
			
			alertInfo(message);
		}
		
		//查询结果集
		function searchRs(){
		
			jQuery.ajaxSetup({async:false});
			
			var url = "general_tjcx_zcbjmd_ajax.do?method=createZcbjmdDjksHTML";
			var ie = "ie";
			
			var otherValue = [ie];
			
			if(checkSearch()){
				searchRsByAjax(url,otherValue);
			}
			
			jQuery.ajaxSetup({async:true});
		}
		
		//检验可否查询
		function checkSearch(){
		
			var flag = true;

			var xn_num =  jQuery("a[name=a_name_xn]").length;
			var xq_num =  jQuery("a[name=a_name_xq]").length;
			var bj_num =  jQuery("a[name=a_name_bjNew]").length;
			
			if(xn_num != "1"){
				alertError("学年条件不可为空，且只能选择一个！");
				flag = false;
			}else if ( xq_num != "1"){
				alertError("学期条件不可为空，且只能选择一个！ ");
				flag = false;
			}else if( bj_num != "1"){
				alertError("班级条件不可为空，且只能选择一个！");
				flag = false;
			}

			return flag;
		}
		
		//导出为Excel
		function expToExcel(){
		
			if(checkSearch()){
				var xn = jQuery("a[name=a_name_xn]").eq(0).attr("id").replace("a_id_","");
				var xq = jQuery("a[name=a_name_xq]").eq(0).attr("id").replace("a_id_","");
				var bjdm =jQuery("a[name=a_name_bjNew]").eq(0).attr("id").replace("a_id_","");
				
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
		
		<html:form action="/general_pjpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="expToExcel();return false;" class="btn_dc">
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