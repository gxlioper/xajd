<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/jjgl/xqsh/script/xqsh.js"></script>
		<script type="text/javascript">
			var gridSetting = {
					caption:"�ҽ������б�",
					pager:"pager",
					multiselect:true,
					rowNum:10,
					url:"jjgl_xqshgl.do?method=queryXqList&type=dsh",
					colList:[
					   {label:'ID',name:'xqid', index: 'xqid',hidden:true,key:true},
					   {label:'�ҽ̿�Ŀ',name:'jjxkmc', index: 'jjxkmc'},
					   {label:'�ҽ��꼶',name:'jjnjmc', index: 'jjnjmc'},
					   {label:'�ҽ̵ص�',name:'jjdd', index: 'jjdd'},
					   {label:'�ҽ�ʱ��',name:'jjsj', index: 'jjsj'},
					   {label:'�ҽ���ʦҪ��',name:'jjlsyq', index: 'jjlsyq'},
					   {label:'������',name:'sqr', index: 'sqr'},
					   {label:'����ʱ��',name:'sqsj', index: 'sqsj'},
					   {label:'���״̬',name:'shztmc', index: 'shztmc'}
					]
				};
	
				function searchRs(){
					var map = {};
					map["sqr"] = jQuery("#sqr").val();
					jQuery("#dataTable").reloadGrid(map);
				}
	
			/**
			 * ҳǩ�л�
			 * @return
			 */
			function selectTab(obj,query_type){
				gridSetting['url'] =  "jjgl_xqshgl.do?method=queryXqList&type=" + query_type;
				
				if(query_type == "dsh"){
					jQuery("#xqshLinkLi").css("display","");
				} else {
					jQuery("#xqshLinkLi").css("display","none");
				}
				jQuery("#dataTable").initGrid(gridSetting);
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
			}

			//���
			function xqsh(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����Ҫ��˵ļ�¼��");
				} else {
					var type = jQuery('#hiddenQryType').val();
					var url = "jjgl_xqshgl.do?method=xqDgsh&xqid="+jQuery("#dataTable").getSeletIds()[0];
					var title = "���";
					showDialog(title,700,430,url);
				}
			}


			//�鿴
			function xqck(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
				} else {
					var url = "jjgl_xqshgl.do?method=xqshck&xqid="+jQuery("#dataTable").getSeletIds()[0];
					var title = "�鿴";
					showDialog(title,700,430,url);
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
				<html:form action="/jjgl_xqshgl" method="post" >
					<!-- ��ť -->
					<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
							<li id="xqshLinkLi"><a href="javascript:void(0);" onclick="xqsh();" class="btn_sh" id="xqshLink">���</a></li>
							<li id="ckLinkLi"><a href="javascript:void(0);" onclick="xqck();" class="btn_ck" id="xqckLink">�鿴</a></li>				
						</ul>
					</div>
					</logic:equal>
					<table width="100%" border="0">
						<tr>
							<th width="10%">���״̬</th>
							<td>
								<html:select property="shzt" styleId="shzt">
									<html:option value="">--��ѡ��--</html:option>
									<html:option value="0">δ���</html:option>
									<html:option value="1">ͨ��</html:option>
									<html:option value="2">��ͨ��</html:option>
								</html:select>
							</td>
							<th width="10%">������</th>
							<td>
								<html:text property="sqr" styleId="sqr" ></html:text>
							</td>
							<td>
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go" onclick="searchRs()">
										�� ѯ
									</button>
								</div>
							</td>
						</tr>					
					</table>
					<div class="comp_title" id="comp_title">
				      <ul style="width:90%" id="tabUl">
				      	<li class="ha" >
				      		<a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>�����</span></a>
				      	</li>
						<li ><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>�����</span></a></li>
				      </ul>
				    </div>
				</html:form>
			</div>
		</div>
		<div class="formbox">
			<div>
				<h3 class="datetitle_01">
					<span> 
						�����б�
					</span>
				</h3>
			</div>
			<table id="dataTable"></table>
		</div>
		<div id="pager"></div>
		
	</body>
</html>
