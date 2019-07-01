<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsxx/fbgl/gzsd/tjgzpz/js/fbglgzpztj.js"></script>
		<script type="text/javascript">
					jQuery(function(){
					     var gridSetting = {
									caption:"��ѯ���",
									pager:"pager",
									url:"fbglgzpztj.do?method=list&type=query",
									params:getSuperSearch(),
									colList:[
									   {label:'pzgzid',name:'pzgzid', index: 'pzgzid',key:true,hidden:true},
									   {label:'��������',name:'pzgzmc', index: 'pzgzmc',formatter:dcmcLink},
									   {label:'��������',name:'gzmc', index: 'gzmc'},
									   {label:'��������',name:'gzdm', index: 'gzdm',hidden:true},
									   {label:'����״̬',name:'qyztmc', index: 'qyztmc'},
									   {label:'�Ƿ�ϵͳ����',name:'sfnzmc', index: 'sfnzmc'},
									   {label:'�Ƿ���ʹ��',name:'sfysy', index: 'sfysy'},
									   {label:'����ʱ��',name:'gxsj', index: 'gxsj'}
									],
									sortname: "gzmc",
								 	sortorder: "asc"
								}
							jQuery("#dataTable").initGrid(gridSetting);
					});
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/fbglgzpztj?method=list&type=query">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="add();return false;"
								class="btn_zj">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;"
								class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;"
								class="btn_sc">ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="copy();return false;"
								class="btn_fz">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="qysz('1');return false;"
								class="btn_plqy">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="qysz('0');return false;"
								class="btn_plty">ͣ��</a>
						</li>
					</ul>
				</div>
				</logic:equal>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
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
