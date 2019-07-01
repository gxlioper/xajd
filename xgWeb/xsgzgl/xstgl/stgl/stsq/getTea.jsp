<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/stgl/stsq/js/stgl.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption:"查询结果",
				pager:"pager",
				rowNum:10,
				url:"stglStsq.do?method=getTea&type=query",
				params:getSuperSearch(),
				colList:[
				   {label:'工号',name:'zgh', index: 'zgh',width:'12%',key:true},
				   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
				   {label:'性别',name:'xb', index: 'xb',width:'5%'},
				   {label:'所在部门',name:'bmmc', index: 'bmdm',width:'8%'},
				   {label:'bmdm',name:'bmdm', index: 'bmdm',hidden:true},
				   {label:'zc',name:'zc', index: 'zc',hidden:true},
                    {label:'zcmc',name:'zcmc', index: 'zcmc',hidden:true},
				   {label:'lxdh',name:'lxdh', index: 'lxdh',hidden:true}
				   ],
				sortname: "zgh",
			 	sortorder: "desc",
			 	radioselect:false
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
				if (rows.length == 0 || rows.length!=1) {
					showAlertDivLayer("请选择一个老师！");
					return false;
				}
				var api = frameElement.api;
				var parentsW = api.get('parentDialog');
				parentsW.jQuery("#stfzrxm").val(rows[0]['xm']);
				parentsW.jQuery("#stfzr").val(rows[0]['zgh']);
				parentsW.jQuery('#fzrlb').attr('disabled',false);
				closeDialog();
			}
//			function selZdls() {
//				var rows = jQuery("#dataTable").getSeletRow();
//				if (rows.length == 0) {
//					showAlertDivLayer("请选择一个老师！");
//					return false;
//				}
//				var api = frameElement.api;
//				var parentsW = api.get('parentDialog');
//				parentsW.jQuery("#zdlsxm").val(rows[0]['xm']);
//				parentsW.jQuery("#zdls").val(rows[0]['zgh']);
//				if(!parentsW.jQuery("#zdlszc option[value="+rows[0]['zc']+"]").val()){
//					parentsW.jQuery("#zdlszc").val("");
//				}else{
//					parentsW.jQuery("#zdlszc").val(rows[0]['zc']);
//				};
//				parentsW.jQuery("#ssbm").val(rows[0]['bmdm']);
//				parentsW.jQuery("#zdlslxfs").val(rows[0]['lxdh']);
//				closeDialog();
//			}
        function selZdls() {
            var rows = jQuery("#dataTable").getSeletRow();
            if (rows.length == 0) {
                showAlertDivLayer("请至少选择一个老师！");
                return false;
            }
            var html = "";
            for(var i = 0; i < rows.length; i++){
                var zgh = rows[i]["zgh"] == null ? "" : rows[i]["zgh"];
                var xm = rows[i]["xm"] == null ? "" : rows[i]["xm"];
                var bmdm = rows[i]["bmdm"] == null ? "" : rows[i]["bmdm"];
                var bmmc = rows[i]["bmmc"] == null ? "" : rows[i]["bmmc"];
                var lxdh = rows[i]["lxdh"] == null ? "" : rows[i]["lxdh"];
                var zcmc = rows[i]["zcmc"] == null ? "" : rows[i]["zcmc"];
                var zc = rows[i]["zc"] == null ? "" : rows[i]["zc"];

                html += "<tr name='deltr'>";
                html += "<td><input type='checkbox' name='chk'></td>";
                html += "<td><input name='zgh' type='hidden' value='"+zgh+"' style='width:90%'/><label name = 'xm'>"+xm+"</label></td>";
                html += "<td><input name='bmdm' type='hidden' value='"+bmdm+"' style='width:90%'/><label name = 'bmmc'>"+bmmc+"</label></td>";
                html += "<td><label name = 'lxdh'>"+lxdh+"</label></td>";
                html += "<td><input name='zc' type='hidden' value='"+zc+"' style='width:90%'/><label name = 'zcmc'>"+zcmc+"</label></td>";
                html += "</tr>";
            }
            var W;
            var api = frameElement.api;
            if (api) {
                if (api.get('childDialog')) {
                    W = api.get('parentDialog')
                } else {
                    W = api.opener;
                }
            } else if (parent.window) {
                W = parent.window;
            }
            jQuery("#tablebody", W.document).append(html);
            iFClose();
        }
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/stglStsq">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
				<ul>
					<logic:equal name="flag" value="selzdls">
						<li>
							<a href="javascript:void(0);" onclick="selZdls();return false;" class="btn_zj">添加</a>
						</li>
					</logic:equal>
					<logic:notEqual name="flag" value="selzdls">
					    <li>
							<a href="javascript:void(0);" onclick="sqxsZj();return false;" class="btn_zj">添加</a>
						</li>
					</logic:notEqual>
				</ul>
			</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="toolbox">
					<!--标题start-->
			<h3 class="datetitle_01">
				<span> 查询结果
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:300px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
