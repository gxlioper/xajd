<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				pager:"pager",
				url:"jddj_dmwh.do?method=getDmwhList",
				colList:[
				   {label:'����',name:'dm', index: 'dm',hidden:true,key:true},
				   {label:'����',name:'mc', index: 'dm'}
				],
				params:{lx:"1"},
				sortname: "dm",
			 	sortorder: "asc"
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["lx"] = jQuery("#lx").val();
				map["mc"] = jQuery("#mc").val();
				jQuery("#dataTable").reloadGrid(map);
			}

			function update(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
				} else {
					showDialog('�޸�',400,120,'jddj_dmwh.do?method=dmwhEdit&dm='+rows[0]["dm"]);
				}
			}

			function dmwhDel(){
				var ids = jQuery("#dataTable").getSeletIds();

				if (ids.length == 0){
					alertInfo("��ѡ����Ҫɾ���ļ�¼��");
				} else {
					showConfirmDivLayer("��ȷ��Ҫɾ���ü�¼��",{"okFun":function(){
						jQuery.post("jddj_dmwh.do?method=dmwhDel",{ids:ids.toString()},function(data){
							alertInfo(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
			
			function addDmwh(){
				var lx = jQuery("#lx").val();
				showDialog('����',400,120,'jddj_dmwh.do?method=dmwhAdd&lx='+lx);;
			}
			
			function selectTab(obj,lx){
				jQuery("#lx").val(lx);
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
				query();
			}
		</script>
	</head>

	<body>
		<input type="hidden" name="lx" id="lx" value="1"/>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="addDmwh()" class="btn_zj">����</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
					<li><a href="javascript:void(0);" onclick="dmwhDel();" class="btn_sc">ɾ��</a></li>						
				</ul>
			</div>
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tbody>
						<tr>
						
							<th>����</th>
							<td>
								<input type="text" id="mc"/>
							</td>
							<td align="right">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									�� ѯ
								</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="comp_title" id="comp_title">
	      <ul style="width:90%">
	        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>��Ŀ����</span></a></li>
	        <li><a href="javascript:void(0);" onclick="selectTab(this,'2');"><span>��Ŀ����</span></a></li>
	      </ul>
	    </div>
	
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> �����б� </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
