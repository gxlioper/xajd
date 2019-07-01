<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
					caption:"�ҽ�ע���û��б�",
					pager:"pager",
					radioselect:true,
					rowNum:20,
					url:"jjgl_zcyhgl.do?method=queryZcyhList&type=w",
					colList:[
					   {label:'�ҳ����',name:'yhm', index: 'yhm',key:true},
					   {label:'����',name:'xm', index: 'xm'},
					   {label:'���֤��',name:'sfzh', index: 'sfzh'},
					   {label:'��ϵ�绰',name:'lxdh', index: 'lxdh'},
					   {label:'��ͥסַ',name:'jtzz', index: 'jtzz'},
					   {label:'������λ',name:'gzdw', index: 'gzdw'},
					   {label:'�Ǽ�ʱ��',name:'zcsj', index: 'zcsj'}
					]
				};
	
				function searchRs(){
					var map = {};
					map["yhm"] = jQuery("#yhm").val();
					map["xm"] = jQuery("#xm").val();
					map["sfzh"] = jQuery("#sfzh").val();
					jQuery("#dataTable").reloadGrid(map);
				}
	
			/**
			 * ҳǩ�л�
			 * @return
			 */
			function selectTab(obj,query_type){
				gridSetting['url'] =  "jjgl_zcyhgl.do?method=queryZcyhList&type=" + query_type;
				
				if(query_type == "w"){
					gridSetting['colList'] = [
					   					   {label:'�ҳ����',name:'yhm', index: 'yhm',key:true},
										   {label:'����',name:'xm', index: 'xm'},
										   {label:'���֤��',name:'sfzh', index: 'sfzh'},
										   {label:'��ϵ�绰',name:'lxdh', index: 'lxdh'},
										   {label:'��ͥסַ',name:'jtzz', index: 'jtzz'},
										   {label:'������λ',name:'gzdw', index: 'gzdw'},
										   {label:'�Ǽ�ʱ��',name:'zcsj', index: 'zcsj'}
										];
					jQuery("#zjLinkLi").css("display","");
					jQuery("#xgLinkLi").css("display","");
					jQuery("#scLinkLi").css("display","");
					jQuery("#drLinkLi").css("display","");
					jQuery("#dcLinkLi").css("display","");
					jQuery("#blackLinkLi").css("display","");
					jQuery("#cancelBlackLinkLi").css("display","none");
					jQuery('#listName').text('ע���û��б�');
				} else {
					gridSetting['colList'] = [
						   					   {label:'�ҳ����',name:'yhm', index: 'yhm',key:true},
											   {label:'����',name:'xm', index: 'xm'},
											   {label:'���֤��',name:'sfzh', index: 'sfzh'},
											   {label:'��ϵ�绰',name:'lxdh', index: 'lxdh'},
											   {label:'��ͥסַ',name:'jtzz', index: 'jtzz'},
											   {label:'������λ',name:'gzdw', index: 'gzdw'},
											   {label:'�Ǽ�ʱ��',name:'zcsj', index: 'zcsj'},
											   {label:'��������ʱ��',name:'sj', index: 'sj'}
											];
					
					jQuery("#zjLinkLi").css("display","none");
					jQuery("#xgLinkLi").css("display","none");
					jQuery("#scLinkLi").css("display","none");
					jQuery("#drLinkLi").css("display","none");
					jQuery("#dcLinkLi").css("display","none");
					jQuery("#blackLinkLi").css("display","none");
					jQuery("#cancelBlackLinkLi").css("display","");
					jQuery('#listName').text('�������б�');
				}
				jQuery("#dataTable").initGrid(gridSetting);
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
			}

			//����
			function bl(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����¼��");
				} else {
					var url = "jjgl_zcyhgl.do?method=hmd&yhm="+jQuery("#dataTable").getSeletIds()[0];
					var title = "������";
					showDialog(title,600,250,url);
				}
			}

			//����
			function cbl(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����¼��");
				} else {
					showConfirm("��ȷ��Ҫ����Ҫ��������",{"okFun" : function(){
						jQuery.getJSON("jjgl_zcyhgl.do?method=hmdCancelSubmit" , 
								{yhm:jQuery("#dataTable").getSeletIds()[0]} , 
								function(data){
									showAlert(data["message"],{},{"clkFun":function(){
										jQuery("#dataTable").reloadGrid();
									}});
								});
						}});
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
					var url = "jjgl_zcyhgl.do?method=viewZcyh&yhm="+jQuery("#dataTable").getSeletIds()[0];
					var title = "�鿴�û���Ϣ";
					showDialog(title,850,450,url);
				}
			}


			function zj() {
                var url = "jjgl_zcyhgl.do?method=jzxxAdd";
                var title = "�ҳ���Ϣ����";
                showDialog(title, 800, 550, url);
            }
            
            function xg() {
                var rows = jQuery("#dataTable").getSeletRow();
                if (rows.length != 1) {
                    showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
                } else {
                    var url = 'jjgl_zcyhgl.do?method=jzxxEdit&yhm=' + rows[0]["yhm"];
                    var title = "�ҳ���Ϣ�޸�";
                    showDialog(title, 800, 550, url);
                }
            }
            
            function sc() {
                var ids = jQuery("#dataTable").getSeletIds();
                if (ids.length == 0) {
                    showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
                    return false;
                }

                showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
                    "okFun" : function() {
                        jQuery.post("jjgl_zcyhgl.do?method=jzxxDel", {
                                values : ids.toString()
                            },
                            function(data) {
                                showAlertDivLayer(data["message"]);
                                jQuery("#dataTable").reloadGrid();
                            }, 'json');
                    }
                });
            }
            
            function dr() {
                // ����ͨ�õĵ���function�������ǵ��빦��ģ�����
                toImportDataNew("IMPORT_JJLSJG");
                return false;
            }

            /**
             * ����
             */
            var DCCLBH = "jjgl_jjlsjg.do";//dcclbh,�������ܱ��

            //�Զ��嵼�� ����
            function dc() {
                //DCCLBH�������ܱ��,ִ�е�������
                customExport(DCCLBH, exportData);
            }

            //��������
            function exportData() {
                setSearchTj();//���ø߼���ѯ����
                var url = "jjgl_jjlsjggl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
                url = addSuperSearchParams(url);//���ø߼���ѯ����
                jQuery("form").eq(0).attr("action", url);
                jQuery("form").eq(0).submit();
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
							<li id = "zjLinkLi"><a href="javascript:void(0);" onclick="zj();" class="btn_zj" id="zjLink">����</a></li>
							<li id = "xgLinkLi"><a href="javascript:void(0);" onclick="xg();" class="btn_xg" id="xgLink">�޸�</a></li>
							<li id = "scLinkLi"><a href="javascript:void(0);" onclick="sc();" class="btn_sc" id="scLink">ɾ��</a></li>

							<li id = "ckLinkLi"><a href="javascript:void(0);" onclick="ck();" class="btn_ck" id="ckLink">�鿴</a></li>

							<li id = "drLinkLi"><a href="javascript:void(0);" onclick="dr();" class="btn_dr" id="drLink">����</a></li>
							<li id = "dcLinkLi"><a href="javascript:void(0);" onclick="dc();" class="btn_dc" id="dcLink">����</a></li>

							<li id = "blackLinkLi"><a href="javascript:void(0);" onclick="bl();" class="btn_sh" id="blackLink">��������</a></li>
							<li id="cancelBlackLinkLi" style="display:none"><a href="javascript:void(0);" onclick="cbl();" class="btn_qxsh" id="cancelBlackLink">����������</a></li>
						</ul>
					</div>
					<div class="comp_title" id="comp_title">
				      <ul style="width:90%" id="tabUl">
				      	<li class="ha" >
				      		<a href="javascript:void(0);" onclick="selectTab(this,'w');"><span>�û��б�</span></a>
				      	</li>
						<li ><a href="javascript:void(0);" onclick="selectTab(this,'b');"><span>������</span></a></li>
				      </ul>
				    </div>
					
					<table width="100%" border="0">
						<tr>
							<th width="10%">����</th>
							<td>
								<html:text property="xm" styleId="xm" ></html:text>
							</td>
							<th width="10%">���֤��</th>
							<td>
								<html:text property="sfzh" styleId="sfzh" ></html:text>
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
				</html:form>
			</div>
		</div>
		<div class="formbox">
			<div>
				<h3 class="datetitle_01">
					<span id="listName"> 
						ע���û��б�
					</span>
				</h3>
			</div>
			<table id="dataTable"></table>
		</div>
		<div id="pager"></div>
		
	</body>
</html>
