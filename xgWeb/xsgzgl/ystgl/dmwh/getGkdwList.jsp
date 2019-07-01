<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"�ҿ���λ�б�",
				pager:"pager",
				url:"ystglDmwh.do?method=getGkdwList&type=query",
				colList:[
				   {label:'�ҿ���λ����',name:'gkdwdm', index: 'gkdwdm',key:true,width:'15%'},
				   {label:'�ҿ���λ����',name:'gkdwmc', index: 'gkdwmc',width:'85%'}
				],
				sortname: "gkdwdm",
			 	sortorder: "asc"
			} 
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function query(){
				var map = {};
				map["gkdwmc"] = jQuery("#gkdwmc").val();
				jQuery("#dataTable").reloadGrid(map);
			}
		function add(){
			var url = "ystglDmwh.do?method=addGkdw";
			var title = "���ӹҿ���λ";
			showDialog(title,470,180,url);
		}
		function update(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1){
				showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
			} else {
				var url = 'ystglDmwh.do?method=updateGkdw&gkdwdm='+rows[0]["gkdwdm"];
				var title = "�޸Ĺҿ���λ";
				showDialog(title,470,190,url);
			}
		}
		function del(){
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0){
				showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
			} else {
				showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
						jQuery.post("ystglDmwh.do?method=delGkdw",{values:ids.toString()},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
				}});
			}
		}
		function newChgCode(obj){
			allNotEmpThenGo(obj.id);
		}
		</script>
		
	</head>
	<body>
	<html:form action="/ystglDmwh" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
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
			<div class="compTab" id="card">
				<div class="comp_title">
					<ul>
						<li ><a href="#" onclick="newChgCode(this);return false;" id="ystglDmwh.do?method=getYstlbList"><span>���������</span></a></li>
						<li ><a href="#" onclick="newChgCode(this);return false;" id="ystglDmwh.do?method=getXmlbList"><span>��Ŀ���</span></a></li>
						<li class="ha"><a href="#" onclick="newChgCode(this);return false;" id="ystglDmwh.do?method=getGkdwList"><span>�ҿ���λ</span></a></li>
					</ul>
				</div>
			</div>	
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%" >�ҿ���λ����</th>
						<td>
							<input type="text" id="gkdwmc" name="gkdwmc" maxleng="20" onkeypress="if(pressEnter(event)){query();return false;}" />
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
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
					<span> ����ά���б� </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
