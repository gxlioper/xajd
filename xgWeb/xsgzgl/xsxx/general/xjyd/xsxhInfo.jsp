<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
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
			var url = "general_xsxx_xjyd_ajax.do?method=getXhAjax";
			var ie = "ie";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}
		
		function sendXh(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len==1){	
				var pkValue=jQuery("[name=div_pkValue]:checked").val();
				window.dialogArguments.document.getElementById('xh').value = pkValue;
				Close();
				window.dialogArguments.document.getElementById('disbutton').click();
			}else{
				alertInfo("请勾选一条记录！",function(){});
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
				<em>您的当前位置：</em><a>学生信息-个人信息-信息查询</a>
			</p>
		</div>


		<html:form action="/gyglnew_qszf_ajax" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox" id="dgncz">
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_sr" onclick="sendXh();return false;">确定</a></li>
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
					page="/sjcz/turnpage.jsp?form=xsxxGeneralForm"></jsp:include>
			</div>
			<div id="tempDiv" style="display:none">
				
			</div>		
				
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>