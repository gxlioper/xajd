<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
					caption:"�ҽ̰����б�",
					pager:"pager",
					radioselect:false,
					rowNum:20,
					url:"jjgl_jjalgl.do?method=queryJJalList&type=1",
					colList:[
					   {name:'sid', index: 'sid',key:true,hidden:true},
					   {label:'�ҽ̶���',name:'jjdx', index: 'jjdx'},
					   {label:'ѧ��',name:'fdxk', index: 'fdxk'},
					   {label:'�ҽ�ʱ��',name:'jjsj', index: 'jjsj'},
					   {label:'¼��ʱ��',name:'jlrq', index: 'jlrq'},
					   {label:'�Ƿ񷢲�',name:'sffbmc', index: 'sffbmc'},
					   {name:'sffb', index: 'sffb',hidden:true}
					]
				};
	
			/**
			 * ҳǩ�л�
			 * @return
			 */
			function selectTab(obj,query_type){
				gridSetting['url'] =  "jjgl_jjalgl.do?method=queryJJalList&type=" + query_type;
				
				if(query_type == "1"){
					jQuery('#listName').text('�ѷ����б�');
				} else {
					jQuery('#listName').text('δ�����б�');
				}
				jQuery("#dataTable").initGrid(gridSetting);
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
			}

			//�޸�
			function xg(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����¼��");
				} else {
					var url = "jjgl_jjalgl.do?method=jjalXg&sid="+jQuery("#dataTable").getSeletIds()[0];
					var title = "�޸�";
					showDialog(title,780,400,url);
				}
			}
			/**
			*�鿴
			**/
			function ck(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����¼��");
				} else {
					var url = "jjgl_jjalgl.do?method=jjalCk&sid="+jQuery("#dataTable").getSeletIds()[0];
					var title = "�鿴�ҽ̰�������";
					showDialog(title,780,400,url);
				}
			}

			/**
			 * ����
			 * @return
			 */
			function xz(){
				showDialog('�����ҽ̰���',780,400,'jjgl_jjalgl.do?method=jjalXz');
			}

			/**
			 * ɾ��
			 * @return
			 */
			function sc(){
				var rows = jQuery("#dataTable").getSeletRow();
				var ids = jQuery("#dataTable").getSeletIds();
				if (rows.length == 0){
					showAlertDivLayer("��ѡ��һ����Ҫɾ���ļ�¼��");
					return false;
				} else{
					showConfirmDivLayer("��ȷ��Ҫɾ����ѡ��¼��",{"okFun":function(){
						jQuery.post("jjgl_jjalgl.do?method=jjalSc",{sid:ids.toString()},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
			
			/**
			*��ʼ��
			*/
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			/**
			*���¼�������
			*/
			function reloadWindow(){
				jQuery("#dataTable").reloadGrid();
			}
		</script>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<div class="toolbox">
			<!-- �������� -->
			<div class="searchtab">
				<html:form action="/jjgl_zcyhgl" method="post" >
					<div class="buttonbox">
						<ul>
							<logic:equal name="writeAble" value="yes">
							<li><a href="javascript:void(0);" onclick="xz();" class="btn_zj" >����</a></li>
							<li><a href="javascript:void(0);" onclick="xg();" class="btn_xg" >�޸�</a></li>
							<li><a href="javascript:void(0);" onclick="sc();" class="btn_sc" >ɾ��</a></li>		
							</logic:equal>
							<li><a href="javascript:void(0);" onclick="ck();" class="btn_ck" >�鿴</a></li>
						</ul>
					</div>
					<div class="comp_title" id="comp_title">
				      <ul style="width:90%" id="tabUl">
				      	<li class="ha" >
				      		<a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>�ѷ���</span></a>
				      	</li>
						<li ><a href="javascript:void(0);" onclick="selectTab(this,'0');"><span>δ����</span></a></li>
				      </ul>
				    </div>
				</html:form>
			</div>
		</div>
		<div class="formbox">
			<div>
				<h3 class="datetitle_01">
					<span id="listName"> 
						�ҽ̰����б�
					</span>
				</h3>
			</div>
			<table id="dataTable"></table>
		</div>
		<div id="pager"></div>
		
	</body>
</html>
