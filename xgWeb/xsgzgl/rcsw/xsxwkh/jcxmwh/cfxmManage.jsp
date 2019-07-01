<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xsxwkh/jcxmwh/js/jcxmwh.js"></script>
		<script type="text/javascript">
		  var gridSetting = {
				caption:"������ά���б�",
				pager:"pager",
				url:"xsxwkhJcxmwh.do?method=cfxmManage&type=query",
				colList:[
				   {label:'����',name:'dm', index: 'dm',key:true,hidden : true},
				   {label:'��Ŀ����',name:'mc', index: 'mc',width:'30%'},
				   {label:'��ֵ����/�Σ�',name:'fz', index: 'fz',width:'30%'},
				   {label:'��ע',name:'bz', index: 'bz',width:'40%'}
				],
				sortname: "dm",
			 	sortorder: "asc"
			}
			
			function add(){
				var url = "xsxwkhJcxmwh.do?method=addCfxm";
				var title = "����";
				showDialog(title,550,300,url);
			}
			function update(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
				} else {
					var url = 'xsxwkhJcxmwh.do?method=updateCfxm&dm='+rows[0]["dm"];
					var title = "�޸�";
					showDialog(title,550,300,url);
				}
			}
			
			function newChgCode(obj){
				allNotEmpThenGo(obj.id);
			}
		</script>
	</head>
	<body>
	<html:form action="/xsxwkhJcxmwh" method="post">
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
					<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
						<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>						
					</logic:equal>
				</ul>
			</div>
			</logic:equal>
			<div class="compTab" id="card">
				<div class="comp_title">
					<ul>
						<li><a href="#" onclick="newChgCode(this);return false;" id="xsxwkhJcxmwh.do?method=jlxmManage"><span>������</span></a></li>
						<li  class="ha"><a href="#" onclick="newChgCode(this);return false;" id="xsxwkhJcxmwh.do?method=cfxmManage"><span>������</span></a></li>
					</ul>
				</div>
			</div>	
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%">����</th>
						<td width="5%">
							<input type="text" id="mc" name="mc" maxleng="20" 
							onkeypress="if(pressEnter(event)){query();return false;}"
							/>
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
					<span>������ά���б� </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
