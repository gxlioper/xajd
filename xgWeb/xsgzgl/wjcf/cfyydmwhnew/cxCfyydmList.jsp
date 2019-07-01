<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/wjcf/cfyydmwhnew/js/cfyydm.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"����ԭ����Ϣ�б�",
				pager:"pager",
				url:"wjcf_cfyydmwh.do?method=cxCfyydmList&type=query",
				colList:[
			       {label:'pk',name:'cfyydm', index: 'cfyydm',key:true,hidden:true},
				   {label:'����',name:'cfyymc', index: 'cfyymc',width:'40%'},
			       {label:'�۷�ֵ',name:'kffs', index: 'kffs',width:'30%'},
                    {label:'����ʱ��',name:'cjsj', index: 'cjsj',width:'30%'}
				],
				sortname: "cjsj",
			 	sortorder: "asc"
			}
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["cfyymc"] = jQuery("#cfyymc").val();
				jQuery("#dataTable").reloadGrid(map);
			}

			
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
			<logic:equal value="yes" name="writeAble">
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="showDialog('���Ӵ���ԭ��',450,150,'wjcf_cfyydmwh.do?method=cfyydmAdd');" class="btn_zj">����</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>						
				</ul>
			</div>
			</logic:equal>
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tbody>
						<tr>
							<th width="15%">����ԭ������</th>
							<td>
								<input type="text" id="cfyymc" onkeypress="if(event.keyCode==13){query();}"/>
							</td>
							<td>
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go" onclick="query()">
										�� ѯ
									</button>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
	
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ����ԭ����Ϣ�б� </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
