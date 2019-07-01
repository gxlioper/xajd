<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/hczd/js/dmwh.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"��վ��ά���б�",
				pager:"pager",
				url:"hczdgl_hczdwh.do?method=hczdList&type=query",
				colList:[
			       {label:'վ������',name:'zdmc', index:'zdmc',key:true,width:'50%'},
				   {label:'ʡ��',name:'shenfen', index:'shenfen',width:'50%'}
				]
			};
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["zdmc"] = jQuery("#zdmc").val();
				map["shenfen"] = jQuery("#shenfen").val();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			//����
			function dr() {
				// ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
				toImportDataNew("IMPORT_HCZD");
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
		<input type="hidden" id="text"
			value="<bean:message key="wjcf.text" />" />
		<div class="toolbox">
			<!-- ��ť -->
			<logic:equal value="yes" name="writeAble">
			<div class="buttonbox">
				<ul>
						<li>
							<a href="javascript:void(0);"
								onclick="showDialog('����վ��',450,310,'hczdgl_hczdwh.do?method=hczdAdd');"
								class="btn_zj">����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="dr();return false;" class="btn_dr">����</a>
						</li>
				</ul>
			</div>
			</logic:equal>
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tbody>
						<tr>
							<th width="15%">
								վ������
							</th>
							<td>
								<input type="text" id="zdmc"
									onkeypress="if(event.keyCode==13){query();}" />
							</td>
							<th width="15%">
								ʡ��
							</th>
							<td>
								<input type="text" id="shenfen"
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


		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>��վ��ά���б�</span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
