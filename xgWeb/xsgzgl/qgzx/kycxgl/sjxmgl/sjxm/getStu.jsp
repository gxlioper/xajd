<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/kycxgl/sjxmgl/sjxm/js/sjxmsq.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption:"��ѯ���",
				pager:"pager",
				rowNum:10,
				url:"qgzx_kycxsjxmsq.do?method=getStu&type=query&xmid="+jQuery("#xmid").val()+"&xhs="+jQuery("#xhs").val(),
				params:getSuperSearch(),
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',width:'12%',key:true},
				   {label:'����',name:'xm', index: 'xm',width:'8%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
				   {label:'�꼶',name:'nj', index: 'nj',width:'8%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'20%'},
				   {label:'רҵ',name:'zymc', index: 'zymc',width:'20%'},
				   {label:'�༶',name:'bjmc', index: 'bjmc',width:'20%'},
				   {label:'��ϵ�绰',name:'lxdh', index: 'lxdh',width:'15%'},
				   {label:'ѧ��',name:'xz', index: 'xz',width:'7%'}
				],
				sortname: "xh",
			 	sortorder: "desc"
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function sqxsZj() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length == 0) {
					showAlertDivLayer("������ѡ��һ��ѧ����");
					return false;
				}
				
					setSqxs(rows);
				
			}
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/qgzx_kycxsjxmsq">
		<html:hidden property="xmid" styleId="xmid"/>
		<input type="hidden" name="xhs" id="xhs" value="${xhs}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
				<ul>
						<li>
							<a href="javascript:void(0);" onclick="sqxsZj();return false;" class="btn_zj">���</a>
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
				<span> ��ѯ���
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:300px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
