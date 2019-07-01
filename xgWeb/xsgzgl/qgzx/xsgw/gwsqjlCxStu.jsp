<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			var gridSetting1 = {
					caption:"��λѡ���б�",
					radioselect:true,
					pager:"pager",
					url:"qgzx_wdgwsq.do?method=wdgwsqjlCx&type=query",
					colList:[
                        {label:'������',name:'sqbh', index: 'sqbh',width:'10%',hidden:true,key:true},
					   {label:'��λ����',name:'gwdm', index: 'gwdm',hidden:true},
                        {label:'��λ����',name:'gwmc', index: 'gwmc',width:'10%'},
					   {label:'���˵�λ',name:'yrdwmc', index: 'yrdwmc',width:'15%'},
					   {label:'��λ���',name:'dwlb', index: 'dwlb',width:'8%',formatter:function(value,row){
					       if(value=="01"){
					           return "У�ڵ�λ";
						   } else {
                               return "У�ⵥλ";
						   }
					   }},
                        {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'12%'},
					   {label:'��Ƹ����',name:'xqrs', index: 'xqrs',width:'8%'},
					   {label:'��¼������',name:'ylyrs', index: 'ylyrs',width:'8%'},
					   {label:'����״̬',name:'shztmc', index: 'shztmc',width:'8%'},
                        {label:'����id',name:'fid', index: 'fid',width:'10%',hidden:true},
                        {label:'����״̬',name:'shzt', index: 'shzt',width:'5%',hidden:true},
                        {label:'��������',name:'splc', index: 'splc',hidden:true}
					],
					//dblclick:function(rowObject){
						//ѡ���λ
					//	xzGw(rowObject);
					//},
					sortname: "sqsj",
				 	sortorder: "desc",
				 	/*checkboxFormatter:function(rowObj){
						var check = rowObj['sfksq'];
						return check == 'N' ? false : true;
						}*/
					};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting1);
				/*var isopen = jQuery("#xssqkg").val();
				if(isopen==null||isopen==''){
					jQuery("#prompt_isopen").show();
					jQuery("#prompt_null_isopen").show();
					return false;
				}
				if ("false" == isopen){
					jQuery("#prompt_isopen").show();
					jQuery("#prompt_false_isopen").show();
					return false;
				}*/
			});

			/**
			 * ���̸���
			 * @return
			 */
			function viewLcgz(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����¼��");
				} else {
					if(rows[0]["shzt"]=="0"){
						showAlertDivLayer(jQuery("#lable_wxglcxx").val());
						return false;
					}
					showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqbh']+"&splc="+rows[0]['splc']);
				}
			}	

			//����
			function cxSqb(){
				/*var isopen = jQuery("#xssqkg").val();
				if(isopen==null||isopen==''){
					showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
					return false;
				}
				if ("false" == isopen){
					showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
					return false;
				}*/
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0) {
					showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
				} else if (ids.length >1 ) {
					showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
				} else {
					var rows = jQuery("#dataTable").getSeletRow();
					
					for(var i=0;i<ids.length;i++){
						if(rows[i]['shzt']!='5'){
							showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
							return false;
						}
					}
					
					showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
						"okFun" : function() {
						
							jQuery.post("qgzx_wdgwsq.do?method=cancle", {
								values : ids.toString(),
								lcid : rows[0]['splc']
							}, function(data) {
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							}, 'json');
							
						}
					});
				}
			}


			//�ύ
			function submitBusi(){
				/*var isopen = jQuery("#xssqkg").val();
				if(isopen==null||isopen==''){
					showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
					return false;
				}*/
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length != 1){
					/*if ("false" == isopen){
						showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
						return false;
					}*/
					showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
				}else{
					var rows = jQuery("#dataTable").getSeletRow();
					/*if ('3'!=rows[0]["shzt"] && "false" == isopen){
						showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
						return false;
					}*/
					var xh = jQuery('#userName').val();
					for(var i=0;i<ids.length;i++){
						if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
							showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
							return false;
						}
					}
					showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
						"okFun" : function() {
							/*jQuery.post("qgzx_wdgwsq.do?method=yzgwsq",{xh:xh,gwdm:rows[0]["gwdm"]},function(data){
								var message = data["message"];
								if(message=="true"){*/
									jQuery.post("qgzx_wdgwsq.do"+"?method=subBusi", {
										values : rows[0]['sqbh'],
										lcid : rows[0]['splc'],
										xh :xh,
										gwdm:rows[0]["gwdm"]
									}, function(data) {
										showAlertDivLayer(data["message"]);
										jQuery("#dataTable").reloadGrid();
									}, 'json');
							/*	}else{
									showAlertDivLayer(message);
								}
							},'json');*/
						}
					});
				}
				
			}

			
			/**
			 * ɾ������
			 */
			function delSqb(){
				var rows = jQuery("#dataTable").getSeletRow();
				var ids = [];
				for ( var i = 0; i < rows.length; i++) {
					ids.push(rows[i]['sqbh'])
				}
				if (ids.length == 0){
					showAlertDivLayer("��ѡ����Ҫȡ������ļ�¼��");
				} else if (rows[0]["shzt"] != "0"&&rows[0]["shzt"] != "3"){
					showAlertDivLayer(jQuery("#lable_wjt_sc").val());
					return false;
				}else {
					showConfirmDivLayer("��ȷ��Ҫȡ����������",{"okFun":function(){
						jQuery.post("qgzx_wdgwsq.do?method=del",{values:ids.toString()},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
			
			function xzxys(){
                var rows = jQuery("#dataTable").getSeletRow();
                if(rows.length != 1){
                    showAlertDivLayer("��ѡ��һ����¼��");
                    return false;
				}
                window.open('common_upload.do?method=asyncDownload&fid='+rows[0]["fid"]);
			}
		</script>
	</head>

	<body>
		<input type="hidden" name="curXn" id="curXn" value="${curXn}"/>
		<input type="hidden" name="isQgzxStu" id="isQgzxStu" value="${isQgzxStu}"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<div class="prompt" id="prompt_isopen" style="display:none;">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p id="prompt_null_isopen" style="display:none;">
				<bean:message key="lable.jcszwcsh_prompt" />
			</p>
			<p id="prompt_false_isopen" style="display:none;">
				<bean:message key="lable.dqwkfsq_prompt" />
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		
		<input type="hidden" id="xssqkg" value="${cssz.xssqkg }" />
		<html:form action="/qgzx_wdgwsq" method="post" styleId="wdgwsqForm">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
					<div class="buttonbox">
						<ul>
							<logic:equal name="writeAble" value="yes">
							<logic:equal name="sfkfsq" value="1">
								<li><a href="javascript:void(0);" id="btn_xzxys" class="btn_xg" onclick="xzxys();return false;">����Э����</a></li>
								<li><a href="javascript:void(0);" id="btn_sc" onclick="delSqb();return false;" class="btn_sc">ɾ��</a></li>
								<li><a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">�ύ</a></li>
								<li><a href="javascript:void(0);" id="btn_cx" onclick="cxSqb();return false;" class="btn_sr">����</a></li>
								<li><a href="javascript:void(0);" id="btn_cs" onclick="viewLcgz();return false;" class="btn_cs">���̸���</a></li>
							</logic:equal>
							</logic:equal>
						</ul>

					</div>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchAreaStu.jsp"%>
				<!-- �������� end-->

			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>��λ������Ϣ</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>

		</div>
	</body>
</html>
