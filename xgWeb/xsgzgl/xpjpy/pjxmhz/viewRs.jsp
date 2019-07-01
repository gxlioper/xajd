<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/pjxmhz/js/pjxmhzCx.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"评奖项目汇总列表",
				pager:"pager",
				url:"xpj_pjxmhz.do?method=viewRs&type=query",
				colList:[
				   {label:'id',name:'id', index: 'id',width:'2%',key:true,hidden:true},
				   {label:'学号',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
				   {label:'姓名',name:'xm', index: 'xm',width:'5%'},
				   {label:'性别',name:'xb', index: 'xb',width:'2%'},
				   {label:'年级',name:'nj', index: 'nj',width:'4%'},
				   {label:'班级',name:'bjmc', index: 'bjmc',width:'10%'},
				   {label:'学年',name:'xn', index: 'xn',width:'6%'},
				   {label:'项目类型',name:'xmlx', index: 'xmlx',width:'6%'},
				   {label:'项目性质',name:'xmxz', index: 'xmxz',width:'6%'},
				   {label:'项目名称',name:'xmmc', index: 'xmmc',width:'8%'},
				   {label:'金额',name:'xmje', index: 'xmje',width:'4%'}
				],
				multiselect:false,
				sortname: "xn",
			 	sortorder: "desc"
			}
		</script>
	</head>

	<body>
		<html:form action="/xpj_pjxmhz">
			<input type="hidden" id="xn" value="${model.xn}">
			<input type="hidden" id="lxdm" value="${model.lxdm}">
			<input type="hidden" id="xzdm" value="${model.xzdm}">
			<input type="hidden" id="xmmc" value="${model.xmmc}">
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="reBack();return false;" class="btn_fh">返回</a></li>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
					</ul>
				</div>
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 评奖结果列表 </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>