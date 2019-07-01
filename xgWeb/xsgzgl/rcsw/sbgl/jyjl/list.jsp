<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				pager:"pager",
				url:"rcswSbglJyjl.do?method=getList",
				colList:[
				   {label:'id',name:'id', index: 'id',hidden:true,key:true},
				   {label:'ְ����',name:'zgh', index: 'zgh',formatter:function(v,r){
					   return "<a class='name' href='javascript:view(\""+r["id"]+"\")'>"+v+"</a>";
				   }},
				   {label:'����',name:'xm', index: 'xm'},
				   {label:'����',name:'bmmc', index: 'bmmc'},
				   {label:'�豸����',name:'flmc', index: 'flmc'},
				   {label:'�豸����',name:'sbmc', index: 'sbmc'},
				   {label:'����ʱ��',name:'jysj', index: 'jysj'},
				   {label:'�黹״̬',name:'ghzt', index: 'ghzt'},
				   {label:'�黹ʱ��',name:'ghsj', index: 'ghsj'}
				],
				sortname: "jysj",
			 	sortorder: "desc"
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			function update(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
				} else {
					showDialog('�޸�',650,400,'rcswSbglJyjl.do?method=edit&id='+rows[0]["id"]);
				}
			}

			function view(id){
				showDialog('�鿴',650,400,'rcswSbglJyjl.do?method=view&id='+id);
			}
			
			function del(){
				var ids = jQuery("#dataTable").getSeletIds();

				if (ids.length == 0){
					showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
				} else {
					showConfirmDivLayer("��ȷ��Ҫɾ���ü�¼��",{"okFun":function(){
						jQuery.post("rcswSbglJyjl.do?method=delete",{ids:ids.toString()},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
			
			function add(){
				showDialog('�Ǽ�',650,450,'rcswSbglJyjl.do?method=add');;
			}
			
			function ghsb(){
				var ids = jQuery("#dataTable").getSeletIds();
				
				if (ids.length == 0){
					showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
				} else {	
					tipsWindownNew("�豸�黹","id:sbgh",520,200,"",{
						button:[
							{name:"����",focus: true,callback:function(){
								var ghbz = jQuery("#ghbz",this.content.document).val();
								var ghsj = jQuery("#ghsj",this.content.document).val();
								var ghjbr = jQuery("#ghjbr",this.content.document).val();
								
								jQuery.post("rcswSbglJyjl.do?method=sbgh",{'ghbz':ghbz,'ghsj':ghsj,'ghjbr':ghjbr,'ids':ids.toString()},function(data){
									searchRs();
								});
								
							}},
							{name:"ȡ��",callback:function(){
								
							}}
						]
					});
				}
			}
			
			
			//����
			function exportConfig(){
				var DCCLBH='rcsw_sbgl.do';
				customExport(DCCLBH, exportData);
			}

			function exportData(){
				var DCCLBH='rcsw_sbgl.do';
				setSearchTj();//���ø߼���ѯ����
				
				var url = "rcswSbglJyjl.do?method=export&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			
			function importJyjl(){
				toImportData("rcsw_sbgl_jyjl");
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
	<html:form action="/rcswSbglJyjl" method="post" styleId="form">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">
					<li><a href="javascript:void(0);" onclick="add()" class="btn_zj">�Ǽ�</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>						
					<li><a href="javascript:void(0);" onclick="ghsb();" class="btn_gx">�黹</a></li>						
					<li><a href="javascript:void(0);" onclick="importJyjl();" class="btn_dr">����</a></li>	
					</logic:equal>					
					<li><a href="javascript:void(0);" onclick="exportConfig();" class="btn_dc">����</a></li>						
				</ul>
			</div>
			<!-- �������� -->	
			<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- �������� end-->
		</div>
	
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> �豸���ü�¼�б� </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
		</html:form>
		
		<div id="sbgh" style="display:none;">
			<table class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>�豸�黹</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="20%">
							�黹ʱ��
						</th>
						<td width="30%">
							<input type="text" value="${now }" readonly="readonly" name="ghsj" id="ghsj"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',skin:'whyGreen'})"
							/>
						</td>
						<th align="right" width="20%">
							������
						</th>
						<td width="30%">
							${userNameReal }
							<input type="hidden" name="ghjbr" id="ghjbr" value="${userName }"/>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							�黹��ע <br/>
							<font color="red">����400�֣�</font>
						</th>
						<td colspan="3">
							<textarea rows="5" style="width:95%;" id="ghbz"
								onblur="checkLen(this,400);"
							></textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>
