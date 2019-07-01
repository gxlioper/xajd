<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/fbjg/js/fbjg.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"��ѯ���",
				pager:"pager",
				url:"fbglfbjg.do?method=list&type=query",
				multiselect:false,
				colList:[
				   {label:'pk',name:'pk', index: 'pk',key:true,hidden:true},
				   {label:'�꼶',name:'nj', index: 'nj'},
				   {label:'<bean:message key="lable.xb" />',name:'bmmc', index: 'bmmc'},
				   {label:'רҵ',name:'zymc', index: 'zymc'},
				   {label:'���',name:'pyccmc', index: 'pyccmc'},
				   {label:'ѧ��',name:'xz', index: 'xz'},
				   {label:'�༶��',name:'bjs', index: 'bjs'},
				   {label:'����',name:'rs', index: 'rs'},
				   {label:'����/Ů��',name:'nvqk', index: 'nvqk'},
				   {label:'δ�ְ�/�ѷְ�',name:'fbqk', index: 'fbqk'},
				   {label:'δ��ѧ��/�ѱ�ѧ��',name:'bxhqk', index: 'bxhqk'}
				]
				
	} 
	 var gridSetting2 = {
				caption:"��ѯ���",
				pager:"pager",
				url:"fbglfbjg.do?method=list&type=query&tjzt=ytj",
				multiselect:false,
				colList:[
				   {label:'pk',name:'pk', index: 'pk',key:true,hidden:true},
				   {label:'�꼶',name:'nj', index: 'nj'},
				   {label:'<bean:message key="lable.xb" />',name:'xy', index: 'xy'},
				   {label:'רҵ',name:'zymc', index: 'zymc'},
				   {label:'���',name:'pyccmc', index: 'pyccmc'},
				   {label:'ѧ��',name:'xz', index: 'xz'},
				   {label:'�༶��',name:'bjs', index: 'bjs'},
				   {label:'����',name:'rs', index: 'rs'},
				   {label:'����/Ů��',name:'nvqk', index: 'nvqk'}
				]
				
	}
			jQuery(function($){
				 	var map = getSuperSearch();
					gridSetting["params"] = map;
					$("#dataTable").initGrid(gridSetting);
					$("#cx").hide();
			});
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/fbglxsxx?method=list&type=query">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" value="${tjzt}" id="tjzt"/>
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li id="tj">
							<a href="javascript:void(0);" onclick="tj();return false;"
								class="btn_tj">�ύ��ʽ��</a>
						</li>
						<li id="cx">
							<a href="javascript:void(0);" onclick="cx();return false;"
								class="btn_cx">�����ύ</a>
						</li>
					</ul>
				</div>
				</logic:equal>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'wtj');"><span>δ�ύ</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ytj');"><span>���ύ</span></a></li>
			      </ul>
				</div>
			</div>
		</html:form>
		<div class="toolbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span style="width: 50%">��ѯ��� <a id="title"
					href="javascript:;"
					style="float: right; margin-right: 30px; color: blue"></a> </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
