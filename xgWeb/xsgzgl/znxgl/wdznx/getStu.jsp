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
				caption:"��ѯ���",
				pager:"pager",
				rowNum:10,
				url:"wdznx.do?method=getStu&type=query"+"&xhArr="+jQuery("#xhArr").val(),
				params:getSuperSearch(),
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',width:'12%',key:true},
				   {label:'����',name:'xm', index: 'xm',width:'8%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
				   {label:'�꼶',name:'nj', index: 'nj',width:'8%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'20%'},
				   {label:'רҵ',name:'zymc', index: 'zymc',width:'20%'},
				   {label:'�༶',name:'bjmc', index: 'bjmc',width:'20%'},
				   {label:'��ϵ�绰',name:'lxdh', index: 'lxdh',width:'10%'},
				   {label:'ѧ��',name:'xz', index: 'xz',width:'7%'}
				],
				sortname: "xh",
			 	sortorder: "asc"
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
				jQuery.ajaxSetup({async:false});
				
				var map = getSuperSearch();
				if (rows.length == 0) {
					var url="wdznx.do?method=getStu&type=query"+"&xhArr="+jQuery("#xhArr").val();
					jQuery.post(url, {
						sffy:"0",
						searchLx:map.searchLx,
						searchTj:map.searchTj,
						searchTjz:map.searchTjz,
						input_mhcx:map.input_mhcx,
						mhcx_lx:map.mhcx_lx,
						path:map.path
					}, function(data) {
						rows=data;
						}, 'json');
				}
				var api = frameElement.api;
				var parentsW = api.get('parentDialog');
				var html = "";
				var jsrhtml = "";
				var oldjsrhtml = parentsW.jQuery("#jsrxm").val();
				var oldnum = parentsW.jQuery("#stugroup").length-1;
				for(var i=0;i<rows.length;i++){
					var jsrbh = rows[i]['xh'];
					var jsrxm = rows[i]['xm'];
					var lxdh = rows[i]['lxdh'];
					if(jsrbh != null && jsrbh != ""){
						html += "<input type='hidden' id='stu_"+(oldnum+i+1)+"' name='jsrbh'"+" value='"+jsrbh+"' />"+"<br/>";
						html += "<input type='hidden' id='stu_lxdh_"+(oldnum+i+1)+"' name='lxdh'" + " value='"+lxdh+"' />"+"<br/>"
					}
					if(jsrxm != null || jsrxm != ""){
						jsrhtml +=jsrxm+";";
					}else{
						jsrhtml +=jsrbh+";";
					}
				}
				parentsW.jQuery("#stugroup").append(html);
				parentsW.jQuery("#jsrxm").val(oldjsrhtml+jsrhtml);
				closeDialog();
				jQuery.ajaxSetup({async:true});
			}
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/wdznx">
		<input type="hidden" name="xhArr" id="xhArr" value="${xhArr}"/>
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
		<div class="formbox" style="width:100%;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
