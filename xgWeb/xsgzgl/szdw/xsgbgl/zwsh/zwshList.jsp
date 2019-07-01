<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/xsgbgl/js/zwsh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript">
		//��ʼ����ѯ
		var gridSetting = {
				caption:"ѧ���ɲ�ְ������б�",
				pager:"pager",
				url:"szdw_zwsh.do?method=zwshList&type=query",
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},//,formatter:xhLink
				   {label:'����',name:'xm', index: 'xm',width:'8%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'15%'},
				   {label:'רҵ',name:'zymc', index: 'zymc',width:'18%'},
				   {label:'�༶',name:'bjmc', index: 'bjmc',width:'10%'},
				   {label:'����ְ��',name:'zwmc', index: 'zwmc',width:'15%'},
				   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'18%'},
				   {label:'���״̬',name:'shzt', index: 'shzt',width:'18%'},
				   {label:'����',name:'shid', index: 'shid',hidden:true},
				   {label:'splc',name:'splc', index: 'splc',hidden:true},
				   {label:'gwid',name:'gwid', index: 'gwid',hidden:true},
				   {label:'zwid',name:'zwid', index: 'zwid',hidden:true},
				   {label:'����',name:'sqid', index: 'sqid',key:true,hidden:true}
				],
				params:{shlx:"dsh"},
				sortname: "sqsj",
			 	sortorder: "asc",
			 	radioselect:false
		}
		var gridSetting2 = {
				caption:"ѧ���ɲ�ְ������б�",
				pager:"pager",
				url:"szdw_zwsh.do?method=zwshList&type=query",
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},//,formatter:xhLink
				   {label:'����',name:'xm', index: 'xm',width:'8%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'15%'},
				   {label:'רҵ',name:'zymc', index: 'zymc',width:'18%'},
				   {label:'�༶',name:'bjmc', index: 'bjmc',width:'10%'},
				   {label:'����ְ��',name:'zwmc', index: 'zwmc',width:'15%'},
				   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'18%'},
				   {label:'���״̬',name:'shzt', index: 'shzt',width:'18%'},
				   {label:'���״̬',name:'shztb', index: 'shztb',width:'18%',hidden:true},
				   {label:'����',name:'shid', index: 'shid',hidden:true},
				   {label:'splc',name:'splc', index: 'splc',hidden:true},
				   {label:'����',name:'sqid', index: 'sqid',key:true,hidden:true}
				],
				params:{shlx:"ysh"},
				sortname: "shsj",
			 	sortorder: "desc",
			 	radioselect: true
		}
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
				jQuery("#btn_sh").click(go_sh);
				jQuery("#btn_cs").click(lcgz);
				//jQuery("#btn_qxsh").click(cxsh);
				jQuery("#btn_qxsh").hide();
			});

			function cancelShnew(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
				} else {
					var splc = rows[0]["splc"];
					var shid = rows[0]["shid"];
					var sqid = rows[0]["sqid"];
					var shzt = rows[0]["shztb"];
					showConfirmDivLayer("��ȷ��Ҫ�����Ըü�¼����˲�����",{"okFun":function(){
						jQuery.post("szdw_zwsh.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
							// �ж��Ƿ����һ������(1:���һ�������ɹ���
							if("1" == data["cancelFlg"]){
								jQuery.post("szdw_zwsh.do?method=cancelZwsq",{sqid:sqid,shzt:shzt},function(result){
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

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
				<input type="hidden" name="shlx" id="shlx" value="dsh"/>
			</p>
		</div>
		<html:form action="/szdw_zwsh.do">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" id="btn_sh" class="btn_sh">���</a>
						<a href="javascript:void(0);" id="btn_qxsh" class="btn_qxsh" onclick="cancelShnew();return false;">����</a>
						</li>
						</logic:equal>
						<li><a href="javascript:void(0);" id="btn_cs" class="btn_cs">���̸���</a></li>	
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="query(this,'dsh');"><span>������</span></a></li>
			        <li><a href="javascript:void(0);" onclick="query(this,'ysh');"><span>�Ѵ���</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="mainbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>ѧ���ɲ�ְ������б�</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
