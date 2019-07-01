<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/bxgl/xsyybxxx/js/xsyybxxx.js"></script>
				<script type="text/javascript">
					jQuery(function(){
					     var gridSetting = {
									caption:"ѧ��ԤԼ������Ϣ",
									pager:"pager",
									url:"rcsw_bxgl_xsyybx.do?method=xsyybxxxList&type=query",
									params:getSuperSearch(),
									colList:[
									   {label:'ԤԼ����id',name:'yybxid', index: 'yybxid',key:true,hidden:true},
									   {label:'ѧ��',name:'xh', index: 'xh', width : '10%',formatter:xhLink},
									   {label:'����',name:'xm', index: 'xm'},
									   {label:'�Ա�',name:'xb', index: 'xb'},
									   {label:'�꼶',name:'nj', index: 'nj', width : '6%'},
									   {label:'ϵ��',name:'xymc', index: 'xymc'},
									   {label:'רҵ',name:'zymc', index: 'zymc'},
									   {label:'�༶',name:'bjmc', index: 'bjmc'},
									   {label:'ԤԼʱ��',name:'yysj', index: 'yysj', width : '8%'},
									   {label:'��������',name:'blnr', index: 'blnr'}
									],
									sortname: "yysj",
								 	sortorder: "desc"
								}
							jQuery("#dataTable").initGrid(gridSetting);
					    	jQuery("#btn_zj").click(add);
							jQuery("#btn_xg").click(update);
							jQuery("#btn_sc").click(deletes);
					});
				</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/rcsw_bxgl_xsyybx?method=xsyybxxxList&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">	
						<li><a href="javascript:void(0);" id="btn_zj" class="btn_zj">��д</a></li>
						<li><a href="javascript:void(0);" id="btn_xg" class="btn_xg">�޸�</a></li>
						<li><a href="javascript:void(0);" id="btn_sc" class="btn_sc">ɾ��</a></li>
					</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">����</a>
						</li>		   
				</ul>
			</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
		</div>
		</html:form>
		<div class="toolbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span style="width: 50%"> ѧ��ԤԼ������Ϣ<a id="title" href="javascript:;" style="float:right;margin-right:30px;color: blue"></a> </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
