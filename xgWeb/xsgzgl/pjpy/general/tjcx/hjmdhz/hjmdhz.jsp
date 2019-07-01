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
		
		//查询结果集
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "general_tjcx_hjmdhz_ajax.do?method=createHjmdhzHTML";
			var ie = "ie";
			
			var otherValue = [ie];
		
			searchRsByAjax(url,otherValue);
			
			jQuery.ajaxSetup({async:true});
		}
		
		//检验可否查询
		function checkSearch(){
		
			var flag = true;

			var xn_num =  jQuery("a[name=a_name_xn]").length;
			var xmlx_num = jQuery("a[name=a_name_xmlx]").length;
			
			if(xn_num != "1"){
				alertError("学年条件不可为空，且只能选择一个！");
				flag = false;
			}else if(xmlx_num != "1"){
				alertError("请选择一个项目类型！");
				flag = false;
			}

			return flag;
		}
		
		//导出为Excel
		function expToExcel(){
		
			if(checkSearch()){
				var xn = jQuery("a[name=a_name_xn]").eq(0).attr("id").replace("a_id_","");
				var xmlx = jQuery("a[name=a_name_xmlx]").eq(0).attr("id").replace("a_id_","");
				
				var url = "general_tjcx_hjmdhz_ajax.do?method=expHjmdhz";
					url+= "&str_xn="+xn;
					url+= "&str_xmlx="+xmlx;
					
				var xydm = new Array();//读书名称
				
				var n=0;
				
				jQuery("a[name=a_name_xy]").each(function(){
					var id = jQuery(this).attr("id");
					var xy = id.replace("a_id_","");
					if(xy !=null && xy!=""){
						xydm[n] = xy;
						n++;
					}
				});
				
				if(xydm != null && xydm.length>0){
					url+= "&array_xydm="+xydm.join("!!array!!");
				}
					
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
			</div>
			
			<!-- 内容显示区开始 -->
			<div class="main_box">
				<div id="div_rs"
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
<%--				<script type="text/javascript">--%>
<%--					$('choose').className="hide";--%>
<%--				</script>--%>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>