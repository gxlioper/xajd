<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"�߷�����ͳ���б�",
				pager:"pager",
				url:"jygl_zfss_zfssCt.do?type=query",
				colList:[
				   {label:'����',name:'xm', index: 'xm',width:'60%'},
				   {label:'����',name:'cs', index: 'cs',width:'40%'}
				],
				sortname: "xm",
			 	sortorder: "asc"
			}
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["zgh"] = jQuery("#zgh").val();
				map["bfwss"] = jQuery("#bfwss").val();
				map["jrsjks"] = jQuery("#jrsjks").val();
				map["jrsjjz"] = jQuery("#jrsjjz").val();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			function exportZfxxCountList() {
				customExport("jygl_zfss_zfssCt.do", exportZfxxCountData,750,500);
			}
			
			// ��������
			function exportZfxxCountData() {
				//setSearchTj();//���ø߼���ѯ����
				var url = "zfss_zfss.do?method=exportZfxxCountData&dcclbh=" + "jygl_zfss_zfssCt.do";//dcclbh,�������ܱ��
				//url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
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
		<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<li><a href="#" onclick="exportZfxxCountList();return false;" class="btn_dc">����</a>	</li>			
				</ul>
			</div>
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
							<th width="16%">����</th>
							<td width="84%">
								<html:text property="zgh" styleId ="zgh"/>
							</td>
						</tr>
						<tr>
							<th>����ʱ���</th>
							<td>
								<html:text property="jrsjks" styleId ="jrsjks" onclick="return showCalendar(this.id,'yyyy-MM-dd',true,'jrsjjz');"/>��
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
				<span>�߷�����ͳ���б� </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
