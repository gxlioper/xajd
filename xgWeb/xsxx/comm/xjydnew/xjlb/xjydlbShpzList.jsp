<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="xsxx/comm/xjydnew/js/xjydlb.js"></script>
		<script language="javascript" src="xsgzgl/comm/bbdmpz/js/commBbdm.js"></script>
		<script type="text/javascript">

 	   var gridSetting = {
			caption:"ѧ���춯�����������б�",
			pager:"pager",
			url:"xjyd.do?method=xjydlbShpzList&type=query",
			colList:[
			   {label:'xjlbdm',name:'xjlbdm', index: 'xjlbdm',key:true,hidden:true},
			   {label:'dybb',name:'dybb', index: 'dybb',hidden:true},
			   {label:'ѧ���춯<br>�������',name:'xjlbmc', index: 'xjlbmc'},
			   {label:'�Ƿ���ѧ��',name:'sfyxjmc', index: 'sfyxj'},
			   {label:'�Ƿ���У',name:'sfzxmc', index: 'sfzx'},
			   {label:'�����༶',name:'sftjbjmc', index: 'sftjbjmc'},
			   {label:'¼����ֹ<br>ʱ��',name:'lrqzsjmc', index: 'lrqzsjmc'},
			   {label:'�������',name:'shlcmc', index: 'shlcmc'},
			   {label:'������<br>����',name:'sfksqmc', index: 'sfksq',width:'5%'},
			   {label:'��������ֹʱ��',name:'sqqzsj', index: 'sqqzsj'}
			],
			sortname: "xjlbdm",
		 	sortorder: "asc"
		}


		jQuery(function(){
			jQuery("#dataTable").initGrid(gridSetting);
		});

		function query(){
			var map = {};
			map["xjlbmc"] = jQuery("#xjlbmc").val();
			jQuery("#dataTable").reloadGrid(map);
		}

		
		function bbsz(){
			var rows = jQuery("#dataTable").getSeletRow();
			
			if (rows.length != 1) {
				showAlertDivLayer("��ѡ����Ҫ���õǼǱ����Ŀ��");
			} else {
				var type = rows[0]['xjlbmc'];
				commBbdm({
					ywid:rows[0]['xjlbdm'], //���ñ����ֶεı������ ����������ϵ�
					h_title: type + ' �ǼǱ�����', //��ѡ�񱨱�ҳ�洫��
					mkdm:'xjydshlb.do', //ģ����룬Ψһ
					thlj: jQuery('#hidden_path').val()
				});
			}
		}

					
		
		</script>
	</head>
	<body>
		<input type="hidden" value="${path}" id="hidden_path"/>
		<%@ include file="/xsgzgl/comm/bbdmpz/csszForm.jsp" %>
		
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
			<!-- ��ť -->
			<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
					  <logic:equal value="yes" name="writeAble">
						<li><a href="javascript:void(0);" onclick="addShpz();" class="btn_zj">����</a></li>
						<li><a href="javascript:void(0);" onclick="updateShpz();" class="btn_xg">�޸�</a></li>
						<li><a href="javascript:void(0);" onclick="delShpz();" class="btn_sc">ɾ��</a></li>		
						<li><a href="javascript:void(0);" onclick="bbsz();" class="btn_sz">�ǼǱ�����</a></li>		
					  </logic:equal>
					</ul>
				</div>
			</logic:equal>
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="20%">ѧ���춯�������</th>
						<td>
							<input type="text" id="xjlbmc" name="xjlbmc" maxleng="25" 
							onkeypress="if(pressEnter(event)){query();return false;}"
							/>
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									�� ѯ
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
		</div>
		<div class="formbox">
		<!--����start-->
			<h3 class="datetitle_01">
				<span> ѧ���춯�����������б� </span>
			</h3>	
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>	
	</body>
</html>
