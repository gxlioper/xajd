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
				caption : "行为类别列表",
				pager : "pager",
				url : "rcsw_rcxwwhnew_rcxwdmwhgl.do?method=getXwlbList&type=query",
				colList : [ {
					label : '所属行为大类',
					name : 'rcxwlbdlmc',
					index : 'rcxwlbdlmc',
					width : '25%'
				}, {
					label : '行为类<br>别代码',
					name : 'rcxwlbdm',
					index : 'rcxwlbdm',
					key : true,
					width : '8%'
				}, {
					label : '行为类别名称',
					name : 'rcxwlbmc',
					index : 'rcxwlbmc',
					width : '18%'
				}, {
					label : '分值<br>类型',
					name : 'fzlxmc',
					index : 'fzlxmc',
					width : '7%'
				}, {
					label : '行为类别<br>分值区间',
					name : 'fzqj',
					index : 'fzqj',
					width : '12%'
				}, {
					label : '备注',
					name : 'rcxwlbbz',
					index : 'rcxwlbbz',
					width : '30%'
				}, {
					label : '最低分值',
					name : 'rcxwlbzdfz',
					index : 'rcxwlbzdfz',
					width : '',
					hidden:true
				}, {
					label : '最高分值',
					name : 'rcxwlbzgfz',
					index : 'rcxwlbzgfz',
					width : '',
					hidden:true
				}, {
					label : '所属行为大类',
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
					showAlertDivLayer("请选择您要申请的行为类别！");
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
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="selectLb();" class="btn_zj">确定</a></li>
				</ul>
			</div>
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%">所属行为大类</th>
						<td width="5%">
							<html:select property="rcxwlbdlmc" styleId="rcxwlbdlmc" style="width:130px" onchange="queryRcxwlbdldm();return false;">
									<html:option value=""></html:option>
									<html:options collection="xwdlList" property="rcxwlbdlmc" labelProperty="rcxwlbdlmc" />
							</html:select>
						</td>
						<th width="12%" >行为类别名称</th>
						<td>
							<input type="text" id="rcxwlbmc" name="rcxwlbmc" maxlength="20" 
							   onkeypress="if(pressEnter(event)){query();return false;}"
							/>
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									查 询
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
		</div>
			<div class="formbox">
			<!--标题start-->
				<h3 class="datetitle_01">
					<span> 日常行为代码维护列表 </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
