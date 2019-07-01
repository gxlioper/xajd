<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/jskp/lxsq/js/comm.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption : "",
				pager : "pager",
				url : "jskp_lxsq.do?method=searchRysz",
				colList : [ {
					label : 'key',
					name : 'sqid',
					index : 'sqid',
					key : true,
					hidden : true
				}, {
					label : 'ѧ��',
					name : 'xh',
					index : 'xh',
					width : '40%'
				}, {
					label : '����',
					name : 'xm',
					index : 'xm',
					width : '40%'
				}, {
					label : '�걨���״̬',
					name : 'shztmc',
					index : 'shztmc',
					width : '20%'
				},{
					label : 'sjly',
					name : 'sjly',
					index : 'sjly',
					hidden : true
				},{
					label : 'shzt',
					name : 'shzt',
					index : 'shzt',
					hidden : true
				}]
			}
			jQuery(function(){
				var map = {};
				map["sqid"]=jQuery("#sqid").val();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
		</script>
	</head>

	<body>
		<input type="hidden" name="sqid" id="sqid" value="${sqid}" />
		<input type="hidden" name="shzt" id="shzt" value="${shzt}"/>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>						
				</ul>
			</div>
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="10%">ѧ��/����</th>
						<td>
							<input type="text" id="xh" name="xh"  onkeydown="keydown_search(event)" />
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
				<span>������Ա�б�&nbsp;&nbsp;&nbsp;<font color="blue">��Ŀ���</font><font color="red">${rs.xmmc}</font>&nbsp;<font color="blue">��Ŀ���ƣ�</font><font color="red">${xmlbmc}</font> </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
