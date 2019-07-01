<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/rtgl/rtsq/js/rtsq.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption:"������Ŀ��ϸ",
				pager:"pager",
				rowNum:10,
				url:"stglRtsq.do?method=getStxmList&type=query"+"&xh="+jQuery("#xh").val(),
				params:getSuperSearch(),
				colList:[
				   {label:'stid',name:'stid', index: 'stid',key:true,hidden:true},
				   {label:'��Чѧ��',name:'xn', index: 'xn',width:'8%'},
				   {label:'������Ŀ����',name:'stxmmc', index: 'stid',width:'8%'},
				   {label:'�������',name:'stlbmc', index: 'stlbdm',width:'5%'},
				   {label:'��Ŀ���',name:'xmlbmc', index: 'xmlbdm',width:'8%'},
				   /*�汾������kssj��jssj�ֶβ���д��Ҳ�޷�ͨ��ϵͳ�޸ģ���ʱ����
				   {label:'������Ч��ʼʱ��',name:'kssj', index: 'kssj',width:'8%'},
				   {label:'������Ч����ʱ��',name:'jssj', index: 'jssj',width:'8%'},
				   */
//				   {label:'ָ����ʦ',name:'zdlsxm', index: 'zdlsxm',width:'8%'},
				   {label:'sqkg',name:'sqkg', index: 'sqkg',hidden:true},
				   {label:'gkdw',name:'gkdw', index: 'gkdw',hidden:true},
				   {label:'sqsj',name:'sqsj', index: 'sqsj',hidden:true},
				   {label:'jtr',name:'jtr', index: 'jtr',hidden:true},
				   {label:'stfzr',name:'stfzr', index: 'stfzr',hidden:true},
				   {label:'stfzrxm',name:'stfzrxm', index: 'stfzrxm',hidden:true},
				   {label:'zdlszc',name:'zdlszc', index: 'zdlszc',hidden:true},
				   {label:'zdlslxfs',name:'zdlslxfs', index: 'zdlslxfs',hidden:true},
				   {label:'lxdh',name:'lxdh', index: 'lxdh',hidden:true},
				   {label:'ssbm',name:'ssbm', index: 'ssbm',hidden:true},
				   {label:'fzrlb',name:'fzrlb', index: 'fzrlb',hidden:true},
				   {label:'xmlbdm',name:'xmlbdm', index: 'xmlbdm',hidden:true},
				   {label:'stclsj',name:'stclsj', index: 'stclsj',hidden:true},
				   {label:'sthjqk',name:'sthjqk', index: 'sthjqk',hidden:true},
				   {label:'stsm',name:'stsm', index: 'stsm',hidden:true},
				   {label:'splc',name:'splc', index: 'splc',hidden:true}
				],
				sortname: "xn",
			 	sortorder: "desc",
			 	radioselect:true
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
			
			
		});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function sqxsZj() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length == 0) {
					showAlertDivLayer("��ѡ��һ��������Ŀ��");
					return false;
				}else if(rows[0]['sqkg'] != '1'){
					showAlertDivLayer("����Ŀ���뿪���Ѿ��رգ��޷�ѡ��");
					return false;
				}

			    setSqxs(rows);
			
			}

		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/stglRtsq">
		<input type="hidden" name="xh" id="xh" value="${xh}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
				<ul>
						<li>
							<a href="javascript:void(0);" onclick="sqxsZj();return false;" class="btn_zj">���</a>
						</li>
				</ul>
			</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="toolbox">
					<!--����start-->
			<h3 class="datetitle_01">
				<span>������Ŀ��ϸ
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
