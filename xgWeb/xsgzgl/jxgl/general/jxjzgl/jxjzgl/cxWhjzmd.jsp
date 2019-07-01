<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>		
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/xsgzgl/jxgl/general/jxxxwh/jxmd.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		jQuery(document).ready(function(){ 
			searchRs();
		});


		//查询结果集
		function searchRs(){
			//按钮控制

			//controlBtn();
			var url = "jxjzgl_cxJxjz_ajax.do?method=cxWhjzmdAjax";
		
			var ie = "10.0";

			var otherValue = [ie];
			
			searchRsByAjax(url,otherValue);
			
			setTimeout("setDivHeight()","2000");
		
			
		}

		//
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}

		//保存建制名单
		function saveJzmd(){
			var jqCheck=jQuery("[name=div_pkValue]:checked");
			var jqDqCheck=null;
			var pkValues="";
			if(jqCheck.length==0){
				alertInfo("请选择学生信息");
				return false;
			}else{
				for(var i=0;i<jqCheck.length;i++){
					if(pkValues==""){
						pkValues=jqCheck[i].value;
					}else{
						pkValues=pkValues+"@@##"+jqCheck[i].value;
					}
				}
			}
			var jzid=jQuery("#jzid").val();
			var url="jxjzgl_cxJxjz_ajax.do?method=zjBcWhjxjzmd";
			var map={"pks":pkValues,"jzid":jzid};
			jQuery.post(url,map,function(data){
				if(data==true){
					alertInfo("保存成功!");
					//刷新父页面
					window.parent.dialogArguments.refurbishJxjz();
					Close();
				}else{
					alertInfo("保存失败!");
				}
			},"json");
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
				选择学生点击确定按钮，将选择学生保存到当前选择的军训建制下。
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->

		<html:form action="/jxjzgl_cxJxjz" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="jzid" name="jzid" value="${model.jzid }"/>
			
			<!-- 隐藏域 -->
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_ccg" onclick="saveJzmd();return false;">确定</a>
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
					style="width:100%;overflow-x:hidden;overflow-y:auto">
				</div>

				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=jxjzglFrom"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			
		</html:form>
	</body>
</html>