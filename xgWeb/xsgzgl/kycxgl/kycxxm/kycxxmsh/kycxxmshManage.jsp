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
						caption:"������Ŀ����б�",
						pager:"pager",
						url:"kycxgl_kycxxm_kycxxmshgl.do?method=kycxxmshManage&type=query",
						colList:[
						   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
						   {label:'��������',name:'splc', index: 'splc',hidden:true},
						   {label:'���Id',name:'shid', index: 'shid',hidden:true},
						   {label:'��λId',name:'gwid', index: 'gwid',hidden:true},
						   {label:'���״̬',name:'shzt', index: 'shzt',hidden:true},
						   {label:'����������',name:'lbdm', index: 'lbdm',hidden:true},
						   {label:'������',name:'czr', index: 'czr',hidden:true},
						   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
						   {label:'������Ŀ����',name:'xmmc', index: 'xmmc',width:'30%',formatter:xmmcLink},
						   {label:'�������',name:'lbmc', index: 'lbmc',width:'10%'},
						   {label:'����ʱ��',name:'xmsqsj', index: 'xmsqsj',width:'10%'},
						   {label:'��Ŀ������',name:'xmsqrxm', index: 'xmsqrxm',width:'10%'},
						   {label:'ָ����ʦ',name:'zdlsxm', index: 'zdlsxm',width:'10%'},
						   {label:'���״̬',name:'shztmc', index: 'shztmc',width:'13%'}
						],
						params:{shzt:"dsh"},
						sortname: "xmsqsj",
					 	sortorder: "desc",
					 	radioselect:false
				};
				gridSetting2 = {
						caption:"������Ŀ����б�",
						pager:"pager",
						url:"kycxgl_kycxxm_kycxxmshgl.do?method=kycxxmshManage&type=query",
						colList:[
							{label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
						   {label:'��������',name:'splc', index: 'splc',hidden:true},
						   {label:'���Id',name:'shid', index: 'shid',hidden:true},
						   {label:'��λId',name:'gwid', index: 'gwid',hidden:true},
						   {label:'���״̬',name:'shzt', index: 'shzt',hidden:true},
						   {label:'����������',name:'lbdm', index: 'lbdm',hidden:true},
						   {label:'������',name:'czr', index: 'czr',hidden:true},
						   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
						   {label:'������Ŀ����',name:'xmmc', index: 'xmmc',width:'30%',formatter:xmmcLink},
						   {label:'�������',name:'lbmc', index: 'lbmc',width:'10%'},
						   {label:'����ʱ��',name:'xmsqsj', index: 'xmsqsj',width:'10%'},
						   {label:'��Ŀ������',name:'xmsqrxm', index: 'xmsqrxm',width:'10%'},
						   {label:'ָ����ʦ',name:'zdlsxm', index: 'zdlsxm',width:'10%'},
						   {label:'���״̬',name:'shztmc', index: 'shztmc',width:'13%'}
						],
						params:{shzt:"ysh"},
						sortname: "xmsqsj",
					 	sortorder: "desc",
					 	radioselect:true
				};
				var searchJson = getSuperSearch();
				searchJson.shzt = "dsh";
				gridSetting["params"] = searchJson;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function viewKycxxmsh(sqid) {
				showDialog("������Ŀ�鿴", 727,460, "kycxgl_kycxxm_kycxxmsqgl.do?method=viewKycxxmsq&sqid=" + sqid);
			}
			function xmmcLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='viewKycxxmsh(\"" + rowObject["sqid"] + "\");'>" + cellValue + "</a>";
			}
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
			function kycxxmSh(){
				var rows = jQuery("#dataTable").getSeletRow();
				var shzt = jQuery("#shzt").val();
				if(shzt=="ysh"){
					showAlertDivLayer("�Ѵ����¼�����ٴ���ˣ�");
					return false;
				} else if(rows.length == 0){
					showAlertDivLayer("��ѡ��һ����Ҫ��˵ļ�¼��");
					return false;
				} else if (rows.length == 1){
					var url = "kycxgl_kycxxm_kycxxmshgl.do?method=kycxxmDgsh&sqid="+rows[0]["sqid"]+'&shid=' +rows[0]["shid"]+'&gwid=' +rows[0]["gwid"] ;
					showDialog("������Ŀ���",727,495,url);
				} else{
					showDialog("������Ŀ�������",530,255,"kycxgl_kycxxm_kycxxmshgl.do?method=kycxxmPlsh");
				} 
			}
			function savePlsh(shzt,shyj,xbjf){
				var rows = jQuery("#dataTable").getSeletRow();
				var guid = new Array();
				var splc = new Array();
				var gwid = new Array();
				var czr = new Array();
				jQuery.each(rows,function(i,row){
					guid.push(row["sqid"]);
					splc.push(row["splc"]);
					gwid.push(row["gwid"]);
					czr.push(row["czr"]);
				});
				jQuery.post("kycxgl_kycxxm_kycxxmshgl.do?method=kycxxmPlsh&type=save",
					{
					 id:guid,
					 splcs:splc,
					 gwids:gwid,
					 czrs:czr,
					 shzt:shzt,
					 shyj:shyj,
					 xbjf:xbjf
					},function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							jQuery("#dataTable").reloadGrid();
						}});
					}, 'json');
			}
			function kycxxmshLcinfo(){
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length != 1){
					showAlertDivLayer(jQuery("#lable_one_lcgz").val());
				} else {		
					showDialog("�������̸���",550,450,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
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
								jQuery.post("kycxgl_kycxxm_kycxxmshgl.do?method=cancelKycxxmsh",{shlc:splc,shid:shid,sqid:sqid},function(result){
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
		</script>
	</head>
	<body style="min-height: 800px;">
		<html:form action="/kycxgl_kycxxm_kycxxmshgl">
			<input type="hidden" value="dsh" id="shzt"/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ�ã�</em><a>${title}</a>
				</p>
			</div>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="kycxxmSh();return false;" 
							   title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
							   class="btn_sh">���</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelShnew();return false;" 
							   title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
							   class="btn_qxsh">����</a>
						</li>	
						</logic:equal>					
						<li><a href="javascript:void(0);" onclick="kycxxmshLcinfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>	
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
				<span>������Ŀ����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
