<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="hqgl/jygl/js/sfss.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"�߷������б�",
				pager:"pager",
				url:"jygl_zfss_zfssMg.do?type=query",
				colList:[
				   {label:'key',name:'djid', index: 'djid',key:true ,hidden:true},
				   {label:'ְ����',name:'zgh', index: 'zgh',width:'10%'},
				   {label:'����',name:'xm', index: 'xm',width:'12%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'6%'},
				   {label:'����',name:'bmmc', index: 'bmmc',width:'15%'},
				   {label:'����ʱ��',name:'jrsj', index: 'jrsj',width:'15%'},
				   {label:'�뿪ʱ��',name:'lksj', index: 'lksj',width:'15%'},
				   {label:'�߷����',name:'fwly', index: 'fwly',width:'43%'}
				],
				sortname: "jrsj",
			 	sortorder: "desc"
			}
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["zgh"] = jQuery("#zgh").val();
				map["jrsjks"] = jQuery("#jrsjks").val();
				map["jrsjjz"] = jQuery("#jrsjjz").val();
				jQuery("#dataTable").reloadGrid(map);
			}

			function updateZfss(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
				} else {
					var url = 'zfss_zfss.do?method=updateZfss&djid='+rows[0]["djid"];
					showWindow('�޸��߷õǼ�',720,370,url);
				}
			}

			function delZfss(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0){
					alertInfo("��ѡ����Ҫɾ���ļ�¼��");
				} else {
					jQuery.post("zfss_zfss.do?method=delZfss",{values:ids.toString()},function(data){
						alertInfo(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}
			}

			function addZfss(){
				showDialog("�����߷õǼ�",720,370,'zfss_zfss.do?method=addZfss');
			}

			function exportZfxxList() {
				customExport("jygl_zfss_zfssMg.do", exportZfxxData,750,500);
			}
			
			// ��������
			function exportZfxxData() {
				//setSearchTj();//���ø߼���ѯ����
				var url = "zfss_zfss.do?method=exportZfxxData&dcclbh=" + "jygl_zfss_zfssMg.do";//dcclbh,�������ܱ��
				//url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			// ����
			function drZfxxList(){
				toImportData("IMPORT_N381601");
				return false;
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/zfss_zfss">
		<div class="toolbox">
			<logic:equal value="yes" name="writeAble">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="addZfss();return false;" class="btn_zj">���õǼ�</a></li>
						<li><a href="javascript:void(0);" onclick="updateZfss();" class="btn_xg">ά���뿪</a></li>
						<li><a href="javascript:void(0);" onclick="delZfss();" class="btn_sc">ɾ��</a></li>	
						<li><a href="#" onclick="drZfxxList();return false;" class="btn_dr">����</a>	</li>	
						<li><a href="#" onclick="exportZfxxList();return false;" class="btn_dc">����</a>	</li>	
					</ul>
				</div>
			</logic:equal>		
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go" onclick="query()">
										�� ѯ
									</button>
									<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset()">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">ְ����</th>
							<td width="84%">
								<html:text property="zgh" styleId ="zgh"/>
							</td>
						</tr>
						<tr>
							<th>����ʱ���</th>
							<td>
							<html:text property="jrsjks" styleId ="jrsjks"  onclick="return showCalendar(this.id,'yyyy-MM-dd',true,'jrsjjz');"/>��
							<html:text property="jrsjjz" styleId ="jrsjjz" onclick="return showCalendar(this.id,'yyyy-MM-dd',false,'jrsjks');"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>�߷õǼ��б� </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
