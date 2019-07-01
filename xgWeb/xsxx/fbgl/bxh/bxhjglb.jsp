<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/bxh/js/bxh.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/gzsd/gzyl.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"��ѯ���",
				pager:"pager",
				url:"fbglbxh.do?method=list&type=query",
				colList:[
				   {label:'pk',name:'pk', index: 'pk',key:true,hidden:true},
				   {label:'�꼶',name:'nj', index: 'nj'},
				   {label:'<bean:message key="lable.xb" />',name:'bmmc', index: 'bmmc'},
				   {label:'רҵ',name:'zymc', index: 'zymc'},
				   {label:'���',name:'pyccmc', index: 'pyccmc'},
				   {label:'ѧ��',name:'xz', index: 'xz'},
				   {label:'����',name:'rs', index: 'rs'},
				   {label:'δ�ְ�/�ѷְ�',name:'fbqk', index: 'fbqk'},
				   {label:'δ��ѧ��/�ѱ�ѧ��',name:'bxhqk', index: 'bxhqk'}
				],
				sortname: "nj",
			 	sortorder: "asc"
	} 
	 var gridSetting2 = {
				caption:"��ѯ���",
				pager:"pager",
				url:"fbglbxh.do?method=jglist&type=query",
				colList:[
				   {label:'pk',name:'pk', index: 'pk',key:true,hidden:true},
				   {label:'ѧ��',name:'xh', index: 'xh'},
				   {label:'����',name:'xm', index: 'xm'},
				   {label:'�Ա�',name:'xb', index: 'xb'},
				   {label:'�꼶',name:'nj', index: 'nj'},
				   {label:'<bean:message key="lable.xb" />',name:'xy1', index: 'xy1'},
				   {label:'רҵ',name:'zymc1', index: 'zymc1'},
				   {label:'רҵ',name:'zydm', index: 'zydm',hidden:true},
				   {label:'�༶',name:'bjmc', index: 'bjmc'},
				   {label:'Ͷ���ɼ�',name:'tdcj', index: 'tdcj'},
				   {label:'��Դ��',name:'sydmc', index: 'sydmc'},
				   {label:'������',name:'ksh', index: 'ksh'},
				   {label:'ѧ��',name:'xz', index: 'xz'},
				   {label:'���',name:'pyccmc', index: 'pyccmc'},
				   {label:'��ѧ�Ź���id',name:'bxhgz', index: 'bxhgz',hidden:true},
				   {label:'��ѧ�Ź���',name:'bxhgzmc', index: 'bxhgzmc',formatter:ckgz}
				]
	} 
			jQuery(function($) {
				$("#tsxx").hide();
				var map = getSuperSearch();
				map["xhzt"] = $("#xhzt").val();
				gridSetting["params"] = map;
				$("#dataTable").initGrid(gridSetting2);
				//�Ƿ��ж�Ӧ��ѧ�Ź���
				if (isHaveGzxx("BXHGZ")) {
					$("#tsxx").hide();
				} else {
					$("#tsxx").show();
				}
			});
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/fbglbxh.do?method=list&type=query">
			<input type="hidden" value="${xhzt}" id="xhzt"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li id="fb">
							<a href="javascript:void(0);" onclick="scxh('ybxh');return false;"
								class="btn_xg">����ѧ��</a>
						</li>
						<li id="tzfb">
							<a href="javascript:void(0);" onclick="delxh();return false;"
								class="btn_xg">ɾ��ѧ��</a>
						</li>
					</ul>
				</div>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
				      <ul style="width:90%">
				        <li ><a href="javascript:void(0);" onclick="selectTab(this,'wbxh');"><span>��ѧ��</span></a></li>
				        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'ybxh');"><span>ѧ�����ɽ��</span></a></li>
				        <span id="tsxx" style="text-align: right;color: red;margin-left: 360px;font-size: 12px;">��ѧ�Ź���δ�趨��δ���ã�</span>
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
