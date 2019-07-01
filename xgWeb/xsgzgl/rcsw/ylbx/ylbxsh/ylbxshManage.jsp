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
						caption:"ҽ�Ʊ��մ����",
						pager:"pager",
						url:"rcsw_ylbx_ylbxshgl.do?method=ylbxshManage&type=query",
						colList:[
						   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
						   {label:'��������',name:'splc', index: 'splc',hidden:true},
						   {label:'���Id',name:'shid', index: 'shid',hidden:true},
						   {label:'��λId',name:'gwid', index: 'gwid',hidden:true},
						   {label:'���״̬����',name:'shzt', index: 'shzt',hidden:true},
						   <logic:notEqual name="xxdm" value="14073">
						   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
						   </logic:notEqual>
						   <logic:equal name="xxdm" value="14073">
						   {label:'���',name:'zd5', index: 'zd5',width:'10%'},
						   </logic:equal>
						   {label:'ѧ��',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
						   {label:'����',name:'xm', index: 'xm',width:'8%'},
						   {label:'�꼶',name:'nj', index: 'nj',width:'6%'},
						   {label:jQuery("#xymc").val(),name:'xymc', index: 'xymc',width:'11%'},
						   {label:'רҵ',name:'zymc', index: 'zymc',width:'12%'},
						   {label:'�༶',name:'bjmc', index: 'bjdm',width:'11%'},
						   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'17%'},
						   {label:'���״̬',name:'shztmc', index: 'shztmc',width:'13%'}
						],
						params:{shzt:"dsh"},
						sortname: "sqsj",
					 	sortorder: "desc",
					 	radioselect:false
				}
				gridSetting2 = {
						caption:"ҽ�Ʊ��������",
						pager:"pager",
						url:"rcsw_ylbx_ylbxshgl.do?method=ylbxshManage&type=query",
						colList:[
							{label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
						   {label:'��������',name:'splc', index: 'splc',hidden:true},
						   {label:'���Id',name:'shid', index: 'shid',hidden:true},
						   {label:'��λId',name:'gwid', index: 'gwid',hidden:true},
						   {label:'���״̬����',name:'shzt', index: 'shzt',hidden:true},
						   <logic:notEqual name="xxdm" value="14073">
						   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
						   </logic:notEqual>
						   <logic:equal name="xxdm" value="14073">
						   {label:'���',name:'zd5', index: 'zd5',width:'10%'},
						   </logic:equal>
						   {label:'ѧ��',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
						   {label:'����',name:'xm', index: 'xm',width:'8%'},
						   {label:'�꼶',name:'nj', index: 'nj',width:'6%'},
						   {label:jQuery("#xymc").val(),name:'xymc', index: 'xymc',width:'11%'},
						   {label:'רҵ',name:'zymc', index: 'zymc',width:'12%'},
						   {label:'�༶',name:'bjmc', index: 'bjdm',width:'11%'},
						   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'17%'},
						   {label:'���״̬',name:'shztmc', index: 'shztmc',width:'13%'}
						],
						params:{shzt:"ysh"},
						sortname: "sqsj",
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
			function ylbxSh(){
				var rows = jQuery("#dataTable").getSeletRow();
				var shzt = jQuery("#shzt").val();
				if(shzt=="ysh"){
					showAlertDivLayer("�Ѵ����¼�����ٴ����");
					return false;
				} else if(rows.length == 0){
					showAlertDivLayer("��ѡ��һ����Ҫ��˵ļ�¼��");
					return false;
				} else if (rows.length == 1){
					var url = "rcsw_ylbx_ylbxshgl.do?method=ylbxDgsh&sqid="+rows[0]["sqid"]+'&xn=' +rows[0]["xn"]+'&shid=' +rows[0]["shid"]+'&gwid=' +rows[0]["gwid"]+'&splc=' +rows[0]["splc"] + '&xh=' + rows[0]["xh"];
					showDialog(jQuery("#gnmkmc").val(),700,495,url);
				} else{
					showDialog("�������",500,220,"rcsw_ylbx_ylbxshgl.do?method=ylbxPlsh");
				} 
			}
	
			// ������˱���
			function savePlsh(shzt,shyj){
				var rows = jQuery("#dataTable").getSeletRow();
				var guid = new Array();
				var splc = new Array();
				var gwid = new Array();
				var xh = new Array();
				jQuery.each(rows,function(i,row){
					guid.push(row["sqid"]);
					splc.push(row["splc"]);
					gwid.push(row["gwid"]);
					xh.push(row["xh"]);
				});
				jQuery.post("rcsw_ylbx_ylbxshgl.do?method=ylbxPlsh&type=save",
					{
					 id:guid,
					 splcs:splc,
					 gwids:gwid,
					 xhs:xh,
					 shzt:shzt,
					 shyj:shyj
					},function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							jQuery("#dataTable").reloadGrid();
						}});
					}, 'json');
			}
	
			function ylbxshLcinfo(){
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length != 1){
					showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
				} else {		
					showDialog("�������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
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
								jQuery.post("rcsw_ylbx_ylbxshgl.do?method=cancelYlbxsh",{shlc:splc,shid:shid,sqid:sqid},function(result){
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
	
			function viewYlbxsh(sqid, xh) {
				showDialog(jQuery("#gnmkmc").val()+"�鿴", 700, 495, "rcsw_ylbx_ylbxsqgl.do?method=viewYlbxsq&sqid=" + sqid + '&xh=' + xh);
			}

			function xhLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='viewYlbxsh(\"" + rowObject["sqid"] + "\",\"" + rowObject["xh"] + "\");'>" + cellValue
						+ "</a>";
			}
	
			var DCCLBH = "rcsw_ylbx_ylbxsh.do";//dcclbh,�������ܱ��
	
			//�Զ��嵼�� ����
			function exportConfig() {
				//DCCLBH�������ܱ��,ִ�е������� 
				customExport(DCCLBH, ylbxshExportData);
			}
	
			// ��������
			function ylbxshExportData() {
				var shlx = jQuery("#shzt").val();
				setSearchTj();//���ø߼���ѯ����
				var url = "rcsw_ylbx_ylbxshgl.do?method=exportData&dcclbh=" + DCCLBH + "&shlx=" + shlx;//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
		</script>
	</head>
	<body style="min-height: 800px;">
		<html:form action="/rcsw_ylbx_ylbxshgl">
			<input type="hidden" value="dsh" id="shzt"/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ�ã�</em><a>${title}</a>
				</p>
			</div>
			<input type="hidden" id="xymc" value="<bean:message key="lable.xy" />"/>
			<input type="hidden" id="gnmkmc" value="${gnmkmc}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="ylbxSh();return false;" 
							   title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
							   class="btn_sh">���</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelShnew();return false;" 
							   title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
							   class="btn_qxsh">����</a>
						</li>	
						</logic:equal>					
						<li><a href="javascript:void(0);" onclick="ylbxshLcinfo();return false;" 
							   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
							   class="btn_cs">���̸���</a></li>	   
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
									
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
				<span>${gnmkmc}�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
