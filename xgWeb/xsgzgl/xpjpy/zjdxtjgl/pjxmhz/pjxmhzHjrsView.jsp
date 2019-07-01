<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxtjgl/pjxmhz/js/pjxmhzHjrs.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"������Ŀ�����б�",
				pager:"pager",
				url:"xpjpy_pjxmhz.do?method=pjxmhzHjrsView&type=query",
				colList:[
				   {label:'id',name:'id', index: 'id',width:'2%',key:true,hidden:true},
				   {label:'ѧ��',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
				   {label:'����',name:'xm', index: 'xm',width:'5%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'2%'},
				   {label:'�꼶',name:'nj', index: 'nj',width:'4%'},
				   {label:'�༶',name:'bjmc', index: 'bjmc',width:'10%'},
				   {label:'ѧ��',name:'xn', index: 'xn',width:'6%'},
				   {label:'��Ŀ����',name:'xmlx', index: 'xmlx',width:'6%'},
				   {label:'��Ŀ����',name:'xmxz', index: 'xmxz',width:'6%'},
				   {label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'8%'},
				   {label:'���',name:'xmje', index: 'xmje',width:'4%'}
				],
				multiselect:false,
				sortname: "xn",
			 	sortorder: "desc"
			}
		</script>
	</head>

	<body>
		<html:form action="/xpjpy_pjxmhz">
			<input type="hidden" id="xn" value="${model.xn}">
			<input type="hidden" id="lxdm" value="${model.lxdm}">
			<input type="hidden" id="xzdm" value="${model.xzdm}">
			<input type="hidden" id="xmmc" value="${model.xmmc}">
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="reBack();return false;" class="btn_fh">����</a></li>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
					</ul>
				</div>
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ��������б� </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>