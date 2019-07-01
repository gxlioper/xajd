<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/jskp/dmwh/js/dmwh.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
						caption : "��Ŀ���ά���б�",
						pager : "pager",
						url : "jskp_dmwh.do?method=searchForDmwhList",
						colList : [ {
							label : 'key',
							name : 'xmlbdm',
							index : 'xmlbdm',
							key : true,
							hidden : true
						}, {
							label : '��Ŀ�������',
							name : 'xmlbmc',
							index : 'xmlbmc',
							width : '100%'
						}],
						sortname : "xmlbmc",
						sortorder : "asc"
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
	
		<div class="toolbox">
			<!-- ��ť -->
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>						
				</ul>
			</div>
			</logic:equal>
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="10%">��Ŀ�������</th>
						<td>
							<input type="text" id="xmlbmc" name="xmlbmc"  onkeydown="keydown_search(event)" />
						</td>
						<td>
							<div class="btn">
									<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false"">
										�� ѯ
									</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
		</div>
		
	
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ��Ŀ���ά���б� </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
