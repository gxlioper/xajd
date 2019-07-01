<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
					caption:"ѧ���б�",
					pager:"pager",
					url:"grttxm_jdsz.do?method=getStu&type=query&xhs="+jQuery("#xhs").val()+"&xmdm="+jQuery("#xmdm").val(),
					colList:[
					   {label:'ѧ��',name:'xh', index: 'xh',width:'12%',key:true},
					   {label:'����',name:'xm', index: 'xm',width:'8%'},
					   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
					   {label:'�꼶',name:'nj', index: 'nj',width:'8%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'20%'},
					   {label:'רҵ',name:'zymc', index: 'zymc',width:'20%'},
					   {label:'�༶',name:'bjmc', index: 'bjmc',width:'20%'},
					   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'20%'},
					   {name:'jgid', index: 'jgid',width:'20%',hidden:true}
					   
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
			function save() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length == 0) {
					showAlertDivLayer("������ѡ��һ��ѧ����");
					return false;
				}
				//��������
				var jdcys = new Array();
				for(var i = 0; i < rows.length; i++){
					var jdcy = rows[i]["jgid"] == null ? "" : rows[i]["jgid"];
					jdcys.push(jdcy);
				}
				jQuery.post("grttxm_jdsz.do?method=saveJdszCy", {
					jdcys : jdcys,
					xmdm : jQuery("#xmdm").val(),
					jdid : jQuery("#jdid").val(),
					jbf : jQuery("#jdf").val()
				}, function(data) {
					if(data["message"] == '����ɹ���'){
						var W;
						var api = frameElement.api;
						if (api) {
							if (api.get('childDialog')) {
								W = api.get('parentDialog')
							} else {
								W = api.opener;
							}
						} else if (parent.window) {
							W = parent.window;
						}
                        W.doQuery();
						iFClose();
					}else{
						showAlertDivLayer(data["message"]);
					}
					
					
				}, 'json');
				
				
			}
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/jyglnew_jygl_cyyqglgl">
		<html:hidden property="xhs" styleId="xhs" value="${xhs}"/>
		<html:hidden property="xmdm" styleId="xmdm" value="${xmdm}"/>
		<html:hidden property="jdid" styleId="jdid" value="${jdid}"/>
		<html:hidden  property="jdf" styleId="jdf" value="${jdf}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="save();return false;" class="btn_zj">���</a>
						</li>
					</ul>
				</div>
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
		</html:form>
		<div class="toolbox">
			<h3 class="datetitle_01">
				<span>ѧ���б�</span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
