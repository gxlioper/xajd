<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
		var xxdm = jQuery("#xxdm").val();
		
		var gridSetting;
		//�����Ƽ���ѧ���Ի�
		if(xxdm = '10704'){
			gridSetting = {
				caption:"�ۺϲ�������б�",
				pager:"pager",
				url:"rcsw_rcxwwhnew_rcxwdmwhgl.do?method=rcxwlbManage&type=query",
				colList:[
				   {label:'�ۺϲ���������',name:'rcxwlbdm', index: 'rcxwlbdm',key:true,width:'15%'},
				   {label:'�ۺϲ����������',name:'rcxwlbmc', index: 'rcxwlbmc',width:'85%'}
				 ],
				sortname: "rcxwlbdm",
				sortorder: "asc"
			}
		}else{
			gridSetting = {
				caption:"��Ϊ����б�",
				pager:"pager",
				url:"rcsw_rcxwwhnew_rcxwdmwhgl.do?method=rcxwlbManage&type=query",
				colList:[
				   {label:'��Ϊ������',name:'rcxwlbdm', index: 'rcxwlbdm',key:true,width:'15%'},
				   {label:'��Ϊ�������',name:'rcxwlbmc', index: 'rcxwlbmc',width:'85%'}
				],
				sortname: "rcxwlbdm",
			 	sortorder: "asc"
			}
		}
		
		 
		jQuery(function(){
			jQuery("#dataTable").initGrid(gridSetting);
		});
		function query(){
			var map = {};
			map["rcxwlbmc"] = jQuery("#rcxwlbmc").val();
			jQuery("#dataTable").reloadGrid(map);
		}
		function del(){
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0){
				showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
			} else {
				showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
						jQuery.post("rcsw_rcxwwhnew_rcxwdmwhgl.do?method=delRcxwlb",{values:ids.toString()},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
				}});
				
			}
		}
		function newChgCode(obj){
			allNotEmpThenGo(obj.id);
		}
		function add(){
			var url = "rcsw_rcxwwhnew_rcxwdmwhgl.do?method=addRcxwlb";
			var title;
			//�����Ƽ���ѧ���Ի�
			if(xxdm = '10704'){
				title = "�����ۺϲ������";
			}else{
				title = "������Ϊ���";
			}
			showDialog(title,370,175,url);
		}
		function update(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1){
				showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
			} else {
				/*jQuery.post("rcsw_rcxwwhnew_rcxwdmwhgl.do?method=checkRcxwlb",{rcxwlbdm:rows[0]["rcxwlbdm"]},function(data){
					if(data["message"] == ""){*/
						var url = 'rcsw_rcxwwhnew_rcxwdmwhgl.do?method=updateRcxwlb&rcxwlbdm='+rows[0]["rcxwlbdm"];
						var title;
						//�����Ƽ���ѧ���Ի�
						if(xxdm = '10704'){
							title = "�޸��ۺϲ������";
						}else{
							title = "�޸���Ϊ���";
						}
						showDialog(title,370,175,url);
					/*}else{
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}
				},'json');*/
			}
		}
		// �û���Ȩ
		function yhsq(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1){
				showAlertDivLayer("��ѡ��һ����Ҫ��Ȩ�ļ�¼��");
			} else {
				var url = "/xgxt/splcNew.do?method=splcYhsz&spgw="+rows[0]["rcxwlbdm"]+"&yhszlx=rcxwwh";
				var title = "�û���Ȩ";
				showDialog(title,720,400,url);
			}
		}
		</script>
	</head>
	<body>
	<input type="hidden" id="xxdm" value="${xxdm}"/>
	<html:form action="/rcsw_rcxwwhnew_rcxwdmwhgl" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
			<!-- ��ť -->
			<logic:equal name="writeAble" value="yes">	
			<logic:equal name="userType" value="admin">	
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>						
					<li><a href="javascript:void(0);" onclick="yhsq();" class="btn_sq">�û���Ȩ</a></li>						
				</ul>
			</div>
			</logic:equal>
			</logic:equal>
			<div class="compTab" id="card">
				<div class="comp_title">
					<ul>
						<li class="ha">
							<a href="#" onclick="newChgCode(this);return false;" id="rcsw_rcxwwhnew_rcxwdmwhgl.do?method=rcxwlbManage">
								<span>
								<logic:equal value="10704" name="xxdm">
									�ۺϲ������
								</logic:equal>
								<logic:notEqual value="10704" name="xxdm">								
									��Ϊ���
								</logic:notEqual>
								</span>
							</a>
						</li>
						<li>
							<a href="#" onclick="newChgCode(this);return false;" id="rcsw_rcxwwhnew_rcxwdmwhgl.do?method=rcxwdlManage">
								<span>
									<logic:equal value="10704" name="xxdm">
										�ۺϲ�������
									</logic:equal>
									<logic:notEqual value="10704" name="xxdm">								
										��Ϊ����
									</logic:notEqual>									
								</span>
							</a>
						</li>
						<li>
							<a href="#" onclick="newChgCode(this);return false;" id="rcsw_rcxwwhnew_rcxwdmwhgl.do?method=rcxwxlManage">
								<span>
									<logic:equal value="10704" name="xxdm">
										�ۺϲ���С��
									</logic:equal>
									<logic:notEqual value="10704" name="xxdm">								
										��ΪС��
									</logic:notEqual>									
								</span>
							</a>
						</li>
					</ul>
				</div>
			</div>	
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%" >
							<logic:equal value="10704" name="xxdm">
								�ۺϲ����������
							</logic:equal>
							<logic:notEqual value="10704" name="xxdm">								
								��Ϊ�������
							</logic:notEqual>							
						</th>
						<td>
							<input type="text" id="rcxwlbmc" name="rcxwlbmc" maxleng="20" onkeypress="if(pressEnter(event)){query();return false;}" />
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
					<span>
						<logic:equal value="10704" name="xxdm">
							�ۺϲ�������б�
						</logic:equal>
						<logic:notEqual value="10704" name="xxdm">								
							��Ϊ����б�
						</logic:notEqual>						
					</span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
