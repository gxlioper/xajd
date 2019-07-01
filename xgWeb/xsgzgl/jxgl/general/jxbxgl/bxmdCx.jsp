<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
		<script type='text/javascript' src="js/comm/message.js"></script>	
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

		//查询
		function searchRs(){
			var url = "jxgl_jxbxgl_ajax.do?method=bxmdCx&pkValue="+jQuery("#pkValue").val();
			var ie = "10.0";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}

		function bxmdCz(type){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len>=1){
				var array = jQuery("[name=div_pkValue]:checked");
				var str = "";
				for (var i=0;i<array.length;i++) {
					var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
					str += pkValue+"!!@@!!";
				}
				var parameter={}
				var url="jxgl_jxbxgl_ajax.do?method=bxmdCz";	
				parameter["pkValue"]=escape(jQuery("#pkValue").val());
				parameter["xh"]=str;
				parameter["doType"]=type;
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
			}else{
				alertInfo("请勾选需要操作的数据！",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
			}
		}

		//导出
		function exportConfig(){
			var DCCLBH='jxgl_jxbxgl.do';
			customExport(DCCLBH, exportData);
		}
		function exportData(){
			var DCCLBH='jxgl_jxbxgl.do';
			setSearchTj();//设置高级查询条件
			var url = "jxgl_jxbxgl.do?method=exportData&dcclbh=" + DCCLBH+
			        "&pkValue="+jQuery("#pkValue").val();//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}		
		</script>
	</head>
	<body>

		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
				勾选学生记录后点击<font color="blue">获得</font>或<font color="blue">撤销</font>维护学生军训表现信息
				</span>
			</p>
		</div>
		<!-- 提示信息 end-->

		<html:form action="/jxgl_jxcjgl" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<input type="hidden" id="jxid" name="jxid" value="${rs.jxid }"/>
			<!-- 隐藏域 -->
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_fh" onclick="refreshForm('jxgl_jxkhgl_jxbxgl.do');return false;">返回</a>
							<a href="#" class="btn_shtg" onclick="bxmdCz('add');return false;">获得</a>
							<a href="#" class="btn_shbtg" onclick="bxmdCz('del');return false;">取消</a>
							<a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a>
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
					<span>
							军训信息:<font class="blue">${rs.jxmc }</font>&nbsp;
							表现类别:<font class="blue">${rs.bxlbmc }</font>&nbsp;
							具体表现:<font class="blue">${rs.jtbxmc }</font>
					</span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=jxglJxbxglForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>