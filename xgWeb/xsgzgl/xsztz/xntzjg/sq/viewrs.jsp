<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		function query(){
			var map = {};
			map["cxmc"] = jQuery("#cxmc").val();
			jQuery("#dataTable").reloadGrid(map);
		}
		
			jQuery(function(){
				var gridSetting = {
					caption:"ѧ���϶��б�",
					pager:"pager",
					rowNum:10,
					url:"jcftz_sq.do?method=renDing&type=query&xmdm=${xmdm}&way=view",
					colList:[
					   {label:'key',name:'jgid',index:'jgid',hidden:true,key:true},
				   	   {label:'����',name:'xm',index:'xm',width:'10%'},
				       {label:'�༶',name:'bjmc',index:'bjmc',width:'10%'},
				       {label:'��Ŀ</br>������',name:'jcxf',index:'jcxf',width:'6%'},
				       {label:'������</br>������',name:'tzhjcf',index:'tzhjcf',width:'6%'},
					   {label:'��ý���',name:'jxmc',index:'jxmc',width:'10%'},
					   {label:'�Ƿ�</br>ȱ��',name:'sfqqrs',index:'sfqqmc',width:'6%'},
					   {label:'���</br>��ѧ��',name:'zf',index:'zf',width:'6%'}
					   <logic:equal name="xxdm" value="13627">
					   ,{label:'��ע1',name:'bz1',index:'bz1',width:'10%'},
					   {label:'��ע2',name:'bz2',index:'bz2',width:'10%'},
					   {label:'��ע3',name:'bz3',index:'bz3',width:'10%'},
					   {label:'��ע4',name:'bz4',index:'bz4',width:'10%'},
					   {label:'��ע5',name:'bz5',index:'bz5',width:'10%'}
					   </logic:equal>
					],
					sortname:"zf",
					sortorder:"desc"
				}
				jQuery("#dataTable").initGrid(gridSetting);
			});
		</script>
	</head>

	<body>
		<div class="toolbox">
		<input type="hidden" id="jgid" value="${rs.jgid}" />
				<div class="searchtab">
				<table width="100%" border="0">
					<tbody>
						<tr>
							<th width="15%">
								ѧ��/����
							</th>
							<td>
								<input type="text" id="cxmc"
									onkeypress="if(event.keyCode==13){query();}" />
							</td>
							<td>
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go"
										onclick="query()">
										�� ѯ
									</button>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="formbox" style="width:100%;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
			<div style="height:35px"></div>  
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
									<button type="button" onclick="iFClose();">
										�ر�
									</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
	</body>
</html>
