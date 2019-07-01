<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		
		<script type="text/javascript">
        var gridSetting = {
				caption:"����ԭ���б�",
				pager:"pager",
				url:"xszz_knsdc.do?method=knyyList&type=query",
				colList:[
				   {label:'����',name:'yydm', index: 'yydm',hidden:true,key:true},
				   {label:'���',name:'xh', index: 'xh',width:'10%'},
				   {label:'����',name:'yymc', index: 'yymc',width:'90%'}
				  
				],
				sortname: "to_number(xh)",
			 	sortorder: "asc"
			};

			function yymcLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name'>"+cellValue+"</a>";
			}


			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["yymc"] = jQuery("#yymc").val();
				jQuery("#dataTable").reloadGrid(map);
			}

			function add(){
				var url = "xszz_knsdc.do?method=addKnyy";
				var title = "��������ԭ��";
				showDialog(title,400,200,url);
			}
			function update(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
				} else {
					var url = 'xszz_knsdc.do?method=updateKnyy&yydm='+rows[0]["yydm"];
					var title = "�޸�����ԭ��";
					showDialog(title,400,200,url);
				}
			}

			function del(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0){
					showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
				} else {
					showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
							jQuery.post("xszz_knsdc.do?method=delKnyy",{values:ids.toString()},function(data){
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
	<html:form action="/xszz_knsdc" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	
		<div class="toolbox">
			<!-- ��ť -->
			
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">	
					<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>		
					</logic:equal>				
				</ul>
			</div>
			<!-- �Ϻ��������Ի�-->
			<logic:equal value="10277" name="xxdm">
			<div class="compTab" id="card">
				<div class="comp_title">
					<ul>
						<li><a href="#" onclick="newChgCode(this);return false;" id="xszz_knsdc.do?method=dcwhList"><span>���ѵ���</span></a></li>
						<li class="ha"><a href="#" onclick="newChgCode(this);return false;" id="xszz_knsdc.do?method=knyyList"><span>����ԭ��</span></a></li>
						
					</ul>
				</div>
			</div>	
			</logic:equal>
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="10%">����</th>
						<td>
							<input type="text" id="yymc" name="yymc" maxleng="20" 
							onkeypress="if(pressEnter(event)){query();return false;}"/>
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
