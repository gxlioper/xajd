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
					caption:"�ҵĸ�λ�б�",
					radioselect:true,
					pager:"pager",
					url:"qgzx_wdgwsq.do?method=wdqggwCx&type=query",
					colList:[
                        {label:'ѧ��',name:'xn', index: 'xn',width:'7%'},
					   {label:'��λ����',name:'gwdm', index: 'gwdm',hidden:true},
                        {label:'��λ����',name:'gwmc', index: 'gwmc',width:'10%'},
                        {label:'��������',name:'gwxzdm', index: 'gwxzdm',width:'8%',formatter:function(value,row){
                            if(value=="0"){
                                return "��ʱ";
                            } else {
                                return "��ʽ";
                            }
						}},
					   {label:'���˵�λ',name:'yrdwmc', index: 'yrdwmc',width:'15%'},
					   {label:'��λ���',name:'dwlb', index: 'dwlb',width:'8%',formatter:function(value,row){
					       if(value=="01"){
					           return "У�ڵ�λ";
						   } else {
                               return "У�ⵥλ";
						   }
					   }},
                        {label:'¼��ʱ��',name:'sgsj', index: 'sgsj',width:'12%'},
					   {label:'�ڸ�״̬',name:'zgztmc', index: 'zgztmc',width:'8%'},
					   {label:'�ܳ��(Ԫ)',name:'zcj', index: 'zcj',width:'8%'},
                        {label:'������',name:'sqbh', index: 'sqbh',width:'8%',hidden:true}
					],
					sortname: "gwmc",
				 	sortorder: "desc",

					};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting1);
			});

			function lzsq(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����¼��");
					return false;
				}

				if(rows[0]["sqbh"] != "" && rows[0]["sqbh"] != null){
                    showAlertDivLayer("�����룬�����ظ��ύ��");
                    return false;
				}
				showDialog("��ְ����",700,400,"qgzx_wdgwsq.do?method=lzsq&gwdm="+rows[0]["gwdm"]);
			}

            function gwmx(){
                var rows = jQuery("#dataTable").getSeletRow();
                if (rows.length != 1){
                    showAlertDivLayer("��ѡ��һ����¼��");
                    return false;
                }

                showDialog("��λ��ϸ",700,400,"qgzx_wdgwsq.do?method=gwmx&gwdm="+rows[0]["gwdm"]);
            }

            function gzmx(){
                var rows = jQuery("#dataTable").getSeletRow();
                if (rows.length != 1){
                    showAlertDivLayer("��ѡ��һ����¼��");
                    return false;
                }

                showDialog("��λ��ϸ",700,400,"qgzx_wdgwsq.do?method=gzmx&gwdm="+rows[0]["gwdm"]);
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
								<li><a href="javascript:void(0);" id="btn_sc" onclick="gwmx();return false;" class="btn_ck">��λ��ϸ</a></li>
								<li><a href="javascript:void(0);" id="btn_xzxys" class="btn_xg" onclick="lzsq();return false;">��ְ����</a></li>
								<li><a href="javascript:void(0);" onclick="gzmx();return false;" class="btn_ck">������ϸ</a></li>
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
				<span>�ҵĸ�λ�б�</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>

		</div>
	</body>
</html>
