<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：wujian -->
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
		
			var bm_num =  jQuery("a[name=a_name_bm]").length;
			var yf_num =  jQuery("a[name=a_name_yf]").length;
			var nd_num =  jQuery("a[name=a_name_nd]").length;
			if(nd_num != 1){
				alertInfo("必须且只能选择一个年度！");
				return false;
			}
			if(yf_num != 1){
				alertInfo("必须且只能选择一个月份！");
				return false;
			}
			if(bm_num != 1){
				alertInfo("必须且只能选择一个部门！");
				return false;
			}
			
			var url = "qgzx_cjtjcx_ajax.do?method=bmcjfftjCx";
			var ie = "ie";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}
		
		function expBmcjfftj(){
			var url = "qgzx_cjtjcx_ajax.do?method=expBmcjfftj";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";	
		}
		</script>
	</head>
	<body>

		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>勤工助学-酬金管理-酬金统计查询</a>
			</p>
		</div>
		<!-- 按钮 -->
		<div class="toolbox" id="dgncz">
			<div class="buttonbox">
				<ul>
					<li><a href="#" class="btn_dc" onclick="expBmcjfftj();return false;">导出</a></li>
				</ul>
			</div>
		</div>
		<html:form action="/qgzx_cjtjcx" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<!-- 隐藏域 -->
			<input type="text" name="aa" value="" style="display:none;"/>
			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- 多功能操作区 end-->
			<div class="comp_title">
	      <ul>
	        <li><a href="javascript:$('go').value='';refreshForm('qgzx_cjtjcx.do?method=ydcjfftjCx');"><span>月度酬金发放统计</span></a></li>
	        <li><a href="javascript:$('go').value='';refreshForm('qgzx_cjtjcx.do?method=ndcjfftjCx');"><span>年度酬金发放统计</span></a></li>
	        <li class="ha"><a href="javascript:$('go').value='';refreshForm('qgzx_cjtjcx.do?method=bmcjfftjCx');"><span>部门酬金发放统计</span></a></li>
	        <li><a href="javascript:$('go').value='';refreshForm('qgzx_cjtjcx.do?method=grcjfftjCx');"><span>个人酬金发放统计</span></a></li>
	      </ul>
	    </div>
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
					<span> 查询结果&nbsp;&nbsp; <font color="blue">提示：年度、月份、部门（必须且只能选择一个）</font><logic:notEmpty name="rsList">
							<font color="blue">提示：单击表头可以排序；双击记录可查看详细信息</font>
						</logic:notEmpty> </span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
				
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>