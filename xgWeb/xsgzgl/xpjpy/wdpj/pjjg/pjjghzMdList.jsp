<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption:"查询结果",
				pager:"pager",
				rowNum:10,
				url:"xpj_pjjg.do?method=getpjjghzMd&type=query&xn="+jQuery("#xn").val()+"&xq="+jQuery("#xq").val()
				+"&lxdm="+jQuery("#lxdm").val()+"&xzdm="+jQuery("#xzdm").val(),
				colList:[
				   {label:'学号',name:'xh', index: 'xh',width:'12%'},
				   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
				   {label:'性别',name:'xb', index: 'xb',width:'5%'},
				   {label:'年级',name:'nj', index: 'nj',width:'5%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'20%'},
				   {label:'班级',name:'bjmc', index: 'bjmc',width:'20%'},
				   {label:'金额',name:'xmje', index: 'xmje',width:'8%'}
						],
				sortname: "xh",
			 	sortorder: "asc"
			}
			var map = getSuperSearch();
			map["xmmc"]=jQuery("#xmmc").val();
			gridSetting["params"]=map;
			jQuery("#dataTable").initGrid(gridSetting);
			
		});
		
		function searchRs() {
			var map = getSuperSearch();
			map["xmmc"]=jQuery("#xmmc").val();
			jQuery("#dataTable").reloadGrid(map);
		}
		
		</script>
	</head>

	<body>
		<html:form action="/xpj_sqsh">
		<input type="hidden" id="xn" value="${xpjPjjgModel.xn}"/>
		<input type="hidden" id="xq" value="${xpjPjjgModel.xq}"/>
		<input type="hidden" id="xmmc" value="${xpjPjjgModel.xmmc}"/>
		<input type="hidden" id="lxdm" value="${xpjPjjgModel.lxdm}"/>
		<input type="hidden" id="xzdm" value="${xpjPjjgModel.xzdm}"/>
			<div class="prompt">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					当前查看项目为：<font color="red">${xpjPjjgModel.xn} <logic:notEmpty name="xqmc" >${xqmc}</logic:notEmpty> ${xpjPjjgModel.xmmc}</font>
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>	
		<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
			<!-- 按钮 -->
			
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="toolbox">
					<!--标题start-->
			<h3 class="datetitle_01">
				<span> 查询结果
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
