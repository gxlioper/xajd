<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption : "��Ϊ����б�",
				pager : "pager",
				url : "rcsw_rcxwwhnew_rcxwdmwhgl.do?method=getXwlbList&type=query",
				colList : [ {
					label : '������Ϊ����',
					name : 'rcxwlbdlmc',
					index : 'rcxwlbdlmc',
					width : '25%'
				}, {
					label : '��Ϊ��<br>�����',
					name : 'rcxwlbdm',
					index : 'rcxwlbdm',
					key : true,
					width : '8%'
				}, {
					label : '��Ϊ�������',
					name : 'rcxwlbmc',
					index : 'rcxwlbmc',
					width : '18%'
				}, {
					label : '��ֵ<br>����',
					name : 'fzlxmc',
					index : 'fzlxmc',
					width : '7%'
				}, {
					label : '��Ϊ���<br>��ֵ����',
					name : 'fzqj',
					index : 'fzqj',
					width : '12%'
				}, {
					label : '��ע',
					name : 'rcxwlbbz',
					index : 'rcxwlbbz',
					width : '30%'
				}, {
					label : '��ͷ�ֵ',
					name : 'rcxwlbzdfz',
					index : 'rcxwlbzdfz',
					width : '',
					hidden:true
				}, {
					label : '��߷�ֵ',
					name : 'rcxwlbzgfz',
					index : 'rcxwlbzgfz',
					width : '',
					hidden:true
				}, {
					label : '������Ϊ����',
					name : 'rcxwlbdldm',
					index : 'rcxwlbdldm',
					hidden : true
				} ],
				sortname : "rcxwlbdm",
				sortorder : "asc"
			}

			jQuery(function() {
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query() {
				var map = {};
				var rcxwlbdlmc = jQuery("#rcxwlbdlmc").val();
				map["rcxwlbmc"] = jQuery("#rcxwlbmc").val();
				if (jQuery.trim(rcxwlbdlmc) != "") {
					map["rcxwlbdlmc"] = jQuery("#rcxwlbdlmc").val();
				}
				jQuery("#dataTable").reloadGrid(map);
			}
			function queryRcxwlbdldm() {
				var map = {};
				var rcxwlbmc = jQuery("#rcxwlbmc").val();
				map["rcxwlbdlmc"] = jQuery("#rcxwlbdlmc").val();

				if (jQuery.trim(rcxwlbmc) != "") {
					map["rcxwlbmc"] = jQuery("#rcxwlbmc").val();
				}
				jQuery("#dataTable").reloadGrid(map);
			}
			function selectLb(){
				var api=frameElement.api;
				var rows = jQuery("#dataTable").getSeletRow();
				if(rows.length==0){
					showAlertDivLayer("��ѡ����Ҫ�������Ϊ���");
					return false;
				}
				var tbody = jQuery(api.get('parentDialog').document).find("#xmInfo");
				tbody.find("tr").remove();
				jQuery.each(rows,function(i,e){
					var html="<tr><td><input type='hidden' name='xwlbdmArr' value='"+rows[i]['rcxwlbdm']+"'/>";
					html+="<input type='hidden' name='xwdldmArr' value='"+rows[i]['rcxwlbdldm']+"'/>";
					html+=rows[i]['rcxwlbmc']+"</td>";
					html+="<td>"+rows[i]['rcxwlbdlmc']+"</td>";
					html+="<td>"+rows[i]['fzlxmc']+"</td>";
					html+="<td>"+rows[i]['fzqj']+"</td>";
					html+="<td><input type='text' style='width:50%' name='fzArray' onkeyup='checkInputNum(this)' onblur='checkValue(this)' maxlength='8'/>";
					html+="<input type='hidden' name='rcxwlbzdfz' value='"+rows[i]['rcxwlbzdfz']+"'/>";
					html+="<input type='hidden' name='rcxwlbzgfz' value='"+rows[i]['rcxwlbzgfz']+"'/>";
					html+="<font color='red'>*</font>";
					html+="</td></tr>";
					tbody.append(html);
				});
				iFClose();
			}
		</script>
	</head>
	<body>
	<html:form action="/rcsw_rcxwwhnew_rcxwdmwhgl" method="post">
			<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="selectLb();" class="btn_zj">ȷ��</a></li>
				</ul>
			</div>
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%">������Ϊ����</th>
						<td width="5%">
							<html:select property="rcxwlbdlmc" styleId="rcxwlbdlmc" style="width:130px" onchange="queryRcxwlbdldm();return false;">
									<html:option value=""></html:option>
									<html:options collection="xwdlList" property="rcxwlbdlmc" labelProperty="rcxwlbdlmc" />
							</html:select>
						</td>
						<th width="12%" >��Ϊ�������</th>
						<td>
							<input type="text" id="rcxwlbmc" name="rcxwlbmc" maxlength="20" 
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
					<span> �ճ���Ϊ����ά���б� </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
