<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			var gridSetting;
			var gridSetting2;
			jQuery(function(){
				gridSetting = {
						caption:"�ܱ������",
						pager:"pager",
						url:"rcsw_xsgzzb_xsgzzbshgl.do?method=xsgzzbshManage&type=query&gzzblx=bj",
						colList:[
						   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
						   {label:'��������',name:'splc', index: 'splc',hidden:true},
						   {label:'���Id',name:'shid', index: 'shid',hidden:true},
						   {label:'��λId',name:'gwid', index: 'gwid',hidden:true},
						   {label:'���״̬����',name:'shzt', index: 'shzt',hidden:true},
						   {label:'��д���û���',name:'lrr', index: 'lrr',hidden:true},
						   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
						   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'7%'},
						   {label:'�ܴ�',name:'zcmc', index: 'zcmc',width:'7%'},
						   {label:jQuery("#xymc").val(),name:'xymc', index: 'xymc',width:'13%'},
						   {label:'רҵ',name:'zymc', index: 'zymc',width:'13%'},
						   {label:'�༶',name:'bjmc', index: 'bjmc',width:'13%',formatter:bjmcLink},
						   {label:'Ӧ��/ʵ��/���/δ��<br/>���ˣ�',name:'rstj', index: 'rstj',width:'9%'},
						   {label:'��дʱ��',name:'lrsj', index: 'lrsj',width:'9%'},
						   {label:'��д��',name:'lrrxm', index: 'lrrxm',width:'9%'},
						   {label:'���״̬',name:'shztmc', index: 'shztmc',width:'13%'}
						],
						params:{shzt:"dsh"},
						sortname: "lrsj",
					 	sortorder: "desc",
					 	radioselect:false
				};
				gridSetting2 = {
						caption:"�ܱ������",
						pager:"pager",
						url:"rcsw_xsgzzb_xsgzzbshgl.do?method=xsgzzbshManage&type=query&gzzblx=bj",
						colList:[
							{label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
						   {label:'��������',name:'splc', index: 'splc',hidden:true},
						   {label:'���Id',name:'shid', index: 'shid',hidden:true},
						   {label:'��λId',name:'gwid', index: 'gwid',hidden:true},
						   {label:'���״̬����',name:'shzt', index: 'shzt',hidden:true},
						   {label:'��д���û���',name:'lrr', index: 'lrr',hidden:true},
						   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
						   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'7%'},
						   {label:'�ܴ�',name:'zcmc', index: 'zcmc',width:'7%'},
						   {label:jQuery("#xymc").val(),name:'xymc', index: 'xymc',width:'13%'},
						   {label:'רҵ',name:'zymc', index: 'zymc',width:'13%'},
						   {label:'�༶',name:'bjmc', index: 'bjmc',width:'13%',formatter:bjmcLink},
						   {label:'Ӧ��/ʵ��/���/δ��<br/>���ˣ�',name:'rstj', index: 'rstj',width:'9%'},
						   {label:'��дʱ��',name:'lrsj', index: 'lrsj',width:'9%'},
						   {label:'��д��',name:'lrrxm', index: 'lrrxm',width:'9%'},
						   {label:'���״̬',name:'shztmc', index: 'shztmc',width:'13%'}
						],
						params:{shzt:"ysh"},
						sortname: "lrsj",
					 	sortorder: "desc",
					 	radioselect:true
				};
				var searchJson = getSuperSearch();
				searchJson.shzt = "dsh";
				gridSetting["params"] = searchJson;
				jQuery("#dataTable").initGrid(gridSetting);
			});
				
			function searchRs(){
				var map = getSuperSearch();
				var shzt = jQuery("#shzt").val();
				if (shzt != ""){
					map["shzt"] = shzt;
				}
				jQuery("#dataTable").reloadGrid(map);
			}
				
			function selectTab(obj,shzt){
				jQuery("#shzt").val(shzt);
				var map = getSuperSearch();
				map["shzt"] = shzt;
				if (shzt == "dsh"){
					jQuery("#li_sh").css("display","");
					jQuery("#li_qx").css("display","none");
					gridSetting["params"] = map;
					jQuery("#dataTable").initGrid(gridSetting);
				} else {			
					jQuery("#li_sh").css("display","none");
					jQuery("#li_qx").css("display","");
					gridSetting2["params"] = map;
					jQuery("#dataTable").initGrid(gridSetting2);
				}
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
			}
			// ���
			function xsgzzbSh(){
				var rows = jQuery("#dataTable").getSeletRow();
				var shzt = jQuery("#shzt").val();
				if(shzt=="ysh"){
					showAlertDivLayer("�Ѵ����¼�����ٴ����");
					return false;
				} else if(rows.length == 0){
					showAlertDivLayer("��ѡ��һ����Ҫ��˵ļ�¼��");
					return false;
				} else if (rows.length == 1){
					var url = "rcsw_xsgzzb_xsgzzbshgl.do?method=xsgzzbDgsh&gzzblx=bj&sqid="+rows[0]["sqid"]+'&shid=' +rows[0]["shid"]+'&gwid=' +rows[0]["gwid"] ;
					showDialog("�ܱ����",700,495,url);
				} else{
					showDialog("�ܱ��������",500,220,"rcsw_xsgzzb_xsgzzbshgl.do?method=xsgzzbPlsh&gzzblx=bj");
				} 
			}
	
			// ������˱���
			function savePlsh(shzt,shyj){
				var rows = jQuery("#dataTable").getSeletRow();
				var guid = new Array();
				var splc = new Array();
				var gwid = new Array();
				var lrr = new Array();
				jQuery.each(rows,function(i,row){
					guid.push(row["sqid"]);
					splc.push(row["splc"]);
					gwid.push(row["gwid"]);
					lrr.push(row["lrr"]);
				});
				jQuery.post("rcsw_xsgzzb_xsgzzbshgl.do?method=xsgzzbPlsh&type=save&gzzblx=bj",
					{
					 id:guid,
					 splcs:splc,
					 gwids:gwid,
					 lrrs:lrr,
					 shzt:shzt,
					 shyj:shyj
					},function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							jQuery("#dataTable").reloadGrid();
						}});
					}, 'json');
			}
	
			function xsgzzbshLcinfo(){
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length != 1){
					showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
				} else {		
					showDialog("�ܱ��������̸���",550,450,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
				}
			}
	
			function cancelShnew(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
				} else {
					var splc = rows[0]["splc"];
					var shid = rows[0]["shid"];
					var sqid = rows[0]["sqid"];
					showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
						jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
							// �ж��Ƿ����һ������(1:���һ�������ɹ���
							if("1" == data["cancelFlg"]){
								jQuery.post("rcsw_xsgzzb_xsgzzbshgl.do?method=cancelXsgzzbsh&gzzblx=bj",{shlc:splc,shid:shid,sqid:sqid},function(result){
									showAlertDivLayer(result["message"],{},{"clkFun":function(){
										jQuery("#dataTable").reloadGrid();
									}});
								},'json');
							}else{
								showAlertDivLayer(data["message"],{},{"clkFun":function(){
									jQuery("#dataTable").reloadGrid();
								}});
							}
					},'json');
					}});
				}
			}
	
			function viewXsgzzbsh(sqid) {
				showDialog("�ܱ��鿴", 700, 445, "rcsw_xsgzzb_xsgzzbsqgl.do?method=viewXsgzzbsq&gzzblx=bj&sqid=" + sqid);
			}

			function bjmcLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='viewXsgzzbsh(\""
						+ rowObject["sqid"] + "\");'>" + cellValue
						+ "</a>";
			}
	
			var DCCLBH = "rcsw_bjgzzb_bjgzzbsh.do";//dcclbh,�������ܱ��
	
			//�Զ��嵼�� ����
			function exportConfig() {
				//DCCLBH�������ܱ��,ִ�е������� 
				customExport(DCCLBH, xsgzzbshExportData);
			}
	
			// ��������
			function xsgzzbshExportData() {
				var shlx = jQuery("#shzt").val();
				setSearchTj();//���ø߼���ѯ����
				var url = "rcsw_xsgzzb_xsgzzbshgl.do?method=exportData&gzzblx=bj&dcclbh=" + DCCLBH + "&shlx=" + shlx;//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
		</script>
	</head>
	<body style="min-height: 800px;">
		<html:form action="/rcsw_xsgzzb_xsgzzbshgl">
			<input type="hidden" value="dsh" id="shzt"/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ�ã�</em><a>${title}</a>
				</p>
			</div>
			<input type="hidden" id="xymc" value="<bean:message key="lable.xy" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="xsgzzbSh();return false;" 
							   title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
							   class="btn_sh">���</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelShnew();return false;" 
							   title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
							   class="btn_qxsh">����</a>
						</li>	
						</logic:equal>					
						<li><a href="javascript:void(0);" onclick="xsgzzbshLcinfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>	
						<logic:equal name="writeAble" value="yes">	   
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
						</logic:equal>				
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>������</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>�Ѵ���</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�ܱ�����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
