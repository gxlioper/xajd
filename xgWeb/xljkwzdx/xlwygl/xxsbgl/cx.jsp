<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
		
	var gridSetting_1 = {
		pager : "pager",
		url : "xljk_xlwygl_xxsbglwh.do?method=query&sblx=0",
		colList : [
				{ label : 'sbsqid', name : 'sbsqid', index : 'sbsqid',key : true, hidden : true },
				{ label : 'sblx', name : 'sblx', index : 'sblx',hidden : true },
				{ label : 'sblxx', name : 'sblxx', index : 'sblxx',hidden : true },
				{ label : '学年', name : 'xn', index : 'xn', width : '15%'},
				{ label : '学期', name : 'xqmc', index : 'xqmc', width : '10%' },
				{ label : '周次', name : 'zbzc', index : 'zbzc', width : '10%' , formatter : link},
				{ label : '开始日期', name : 'zbksrq', index : 'zbksrq', width : '10%' },
				{ label : '结束日期', name : 'zbjsrq', index : 'zbjsrq', width : '10%' },
				{ label : '上报时间', name : 'sbsj', index : 'sbsj', width : '10%' },
				{ label : '审核状态', name : 'shztmc', index : 'shztmc', width : '8%' },
				{ label : 'shzt', name : 'shzt', index : 'shzt', hidden : true },
				{ label : 'splcid', name : 'splcid', index : 'splcid', hidden : true },
				{ label : 'zbid', name : 'zbid', index : 'zbid', hidden : true }],
		sortname : "sbsj", sortorder : "desc" }

	var gridSetting_2 = {
			pager : "pager",
			url : "xljk_xlwygl_xxsbglwh.do?method=query&sblx=1",
			colList : [
					{ label : 'sbsqid', name : 'sbsqid', index : 'sbsqid',key : true, hidden : true },
				{ label : 'sblx', name : 'sblx', index : 'sblx',hidden : true },
				{ label : 'sblxx', name : 'sblxx', index : 'sblxx',hidden : true },
				{ label : '学年', name : 'xn', index : 'xn', width : '15%'},
				{ label : '学期', name : 'xqmc', index : 'xqmc', width : '10%' },
				{ label : '周次', name : 'zbzc', index : 'zbzc', width : '10%',formatter : link},
				{ label : '开始日期', name : 'zbksrq', index : 'zbksrq', width : '10%' },
				{ label : '结束日期', name : 'zbjsrq', index : 'zbjsrq', width : '10%' },
				{ label : '上报时间', name : 'sbsj', index : 'sbsj', width : '10%' },
				{ label : '审核状态', name : 'shztmc', index : 'shztmc', width : '8%' },
				{ label : 'shzt', name : 'shzt', index : 'shzt', hidden : true },
				{ label : 'splcid', name : 'splcid', index : 'splcid', hidden : true },
				{ label : 'zbid', name : 'zbid', index : 'zbid', hidden : true }],
			sortname : "sbsj", sortorder : "desc" }

	var gridSetting_3 = {
			pager : "pager",
			url : "xljk_xlwygl_xxsbglwh.do?method=query&sblx=2",
			colList : [
					{ label : 'sbsqid', name : 'sbsqid', index : 'sbsqid',key : true, hidden : true },
					{ label : 'sblx', name : 'sblx', index : 'sblx',hidden : true },
					{ label : 'sblxx', name : 'sblxx', index : 'sblxx',hidden : true },
					{ label : '学年', name : 'xn', index : 'xn', width : '15%'},
					{ label : '学期', name : 'xqmc', index : 'xqmc', width : '10%' },
					{ label : '学号', name : 'xh', index : 'xh', width : '15%',formatter : link},
					{ label : '姓名', name : 'xm', index : 'xm', width : '20%'},
					{ label : '上报时间', name : 'sbsj', index : 'sbsj', width : '35%' },
					{ label : '审核状态', name : 'shztmc', index : 'shztmc', width : '25%' },
					{ label : 'shzt', name : 'shzt', index : 'shzt', hidden : true },
					{ label : 'splcid', name : 'splcid', index : 'splcid', hidden : true }],
			sortname : "sbsj", sortorder : "desc" }
	
	jQuery(function() {
		//gridSetting_1["params"] = getSuperSearch();
		//jQuery("#dataTable").initGrid(gridSetting_1);
		jQuery('#tab_title li:first').css('ha');
		jQuery('#tab_title li:first').children().click();
	});

	/**
	 * 待处理、已处理面签切换
	 * @param obj
	 * @param shzt
	 * @return
	 */
	function selectTab(obj,query_type){
		jQuery("#query_type").val(query_type);

		if (query_type == "0"){
			jQuery("#dataTable").initGrid(gridSetting_1);
		} else if(query_type == "1"){
			jQuery("#dataTable").initGrid(gridSetting_2);
		}else if(query_type == "2"){
			jQuery("#dataTable").initGrid(gridSetting_3);
		}
		jQuery(".ha").removeClass("ha");
		jQuery(obj).parent().addClass("ha");
		
		searchRs();
	}
	
	/**
	 * 高级查询
	 * @return
	 */
	function searchRs() {
		var map = getSuperSearch();
		jQuery("#dataTable").reloadGrid(map);
	}

	/**
	 * 上报
	 * @return
	 */
	function sb(){
		var sblx = jQuery("#query_type").val();
		var rows = jQuery("#dataTable").getSeletRow();
		if ((sblx=='1' || sblx == '0') && rows.length != 1){
			showAlertDivLayer("请选择一个上报周次！");
			return false;
		} else{
			if(sblx == '1' || sblx == '0'){
				var shzt = rows[0]['shzt'];
				if(shzt != '' && shzt != null){
					showAlertDivLayer("请选择未上报的周次！");
					return false;
				}
				showDialog('上报',680,430,'xljk_xlwygl_xxsbglwh.do?method=sb&sblx=' + rows[0]['sblxx'] + "&sbzbid=" + rows[0]['zbid']);
			}else{

					if(jQuery('#sfxypssb').val() == '0'){
						showAlertDivLayer("暂不允许上报信息!");
						return false;
					}
				
				showDialog('上报',680,430,'xljk_xlwygl_xxsbglwh.do?method=sb&sblx=2');
			}
		}
	}

	/**
	 * 删除
	 * @return
	 */
	function del(){
		var rows = jQuery("#dataTable").getSeletRow();
		var ids = jQuery("#dataTable").getSeletIds();
		if (rows.length == 0){
			showAlertDivLayer("请选择一条您要删除的记录！");
			return false;
		} else{
			showConfirmDivLayer("您确定要删除所选记录？",{"okFun":function(){
				jQuery.post("xljk_xlwygl_xxsbglwh.do?method=delAction",{pks:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}});
		}
	}
	
	/**
	 * 修改
	 * @return
	 */
	function update(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1){
			showAlertDivLayer("请选择一条您要修改的记录！");
			return false;
		} else{
			var shzt = rows[0]['shzt'];
			if(shzt == '' || shzt == null){
				showAlertDivLayer("请选择未提交或者已退回的记录！");
				return false;
			}
			if(shzt != '0' && shzt != '3'){
				showAlertDivLayer("请选择未提交或者已退回的记录！");
				return false;
			}
			showDialog('修改',680,430,'xljk_xlwygl_xxsbglwh.do?method=xg&sbsqid=' + rows[0]['sbsqid'] + '&sblx=' + rows[0]['sblx']);
		}
	}

	/**
	 * 撤销
	 * @return
	 */
	function cancle(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlertDivLayer("请选择一条要撤销的记录！");
		} else {
			for(var i=0;i<rows.length;i++){
				if(rows[i]['shzt'] != '5'){
					showAlertDivLayer("只有审核中的记录才能被撤销！");
					return false;
				}
			}
			
			showConfirmDivLayer("您确定要撤销选择的记录吗？", {
				"okFun" : function() {
					jQuery.post("xljk_xlwygl_xxsbglwh.do?method=cancelAction", {
						sbsqid : rows[0]['sbsqid']
					}, function(data) {
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}, 'json');
				}
			});
		}
	}

	/**
	 * 流程跟踪
	 * @return
	 */
	function lcinfo(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1){
			showAlertDivLayer("请选择一条记录！");
		} else {
			if(rows[0]["shzt"]=="0" || rows[0]["shzt"]=="" || rows[0]["shzt"]==null){
				showAlertDivLayer("请选择已提交的记录！");
				return false;
			}
			showDialog("审批流程跟踪",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['sbsqid']+"&splc="+rows[0]['splcid']);
		}
	}
	/**
	 * 提交
	 * @return
	 */
	function submitBusi(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1) {
			showAlert("请选择一条要提交的记录！");
			return false;
		} else {
			if(rows[0]['shzt']!='0'&&rows[0]['shzt']!='3'){
				showAlert("请选择未提交或者已退回的记录！");
				return false;
			}
			
			showConfirmDivLayer("您确定要提交选择的记录吗？", {
				"okFun" : function() {
					jQuery.post("xljk_xlwygl_xxsbglwh.do?method=submitAction", {
						sbsqid : rows[0]['sbsqid'],
						sblx : rows[0]['sblxx']
					}, function(data) {
						if((data||{})['code'] == '-1'){
							showAlertDivLayer("审批流程未设置,请联系管理员!");
							return false;
						}else if((data||{})['code'] == '-2'){
							showAlertDivLayer("任职日期已过，不能上班班级周报!");
							return false;
						}else if((data||{})['code'] == '-3'){
							showAlertDivLayer("无法上报平时情况!");
							return false;
						}else if((data||{})['code'] == '0'){
							showAlertDivLayer(data["message"]);
							return false;
						}else if((data||{})['code'] == '1'){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						}else{
							showAlertDivLayer("未知系统错误!");
							return false;
						}
					}, 'json');
				}
			});
		}
	}

	/**
	 * 链接
	 * @param cellValue
	 * @param rowObject
	 * @return
	 */

	function link(cellValue,rowObject){

		//return "<a href='javascript:void(0);' class='name' onclick='knsView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
		var onclickfn = "onclick=\"" + "showDialog('详细信息' , 680,430 , 'xljk_xlwygl_xxsbglwh.do?method=ck&sbsqid=" + rowObject['sbsqid'] + "')" + "\"";
		if(rowObject['sbsqid'] == '' || rowObject['sbsqid'] == null){
			onclickfn = "onclick=\"" + "showAlertDivLayer('信息未上报！')" + "\"";
		}
		var href = "href = 'javascript:void(0);'";

		var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
		
		return el;
	}

	function query(){
		var map = {};
		map["xn"] = jQuery("#xn").val();
		map["xq"] = jQuery("#xq").val();
		jQuery("#dataTable").reloadGrid(map);
	}
	
</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xljk_xlwygl_xxsbglwh">
			<input type="hidden" id="query_type" value="0"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj"
								onclick="sb();return false;">上报</a>
						</li>
						<li>
							<a href="javascript:void(0);"
								onclick="update();return false;" class="btn_xg"
							>修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">提交</a>
						</li>
						
						<li>
							<a href="javascript:void(0);" onclick="cancle();return false;" class="btn_sr">撤销</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="lcinfo();return false;" class="btn_cs">流程跟踪</a>
						</li>
					</ul>
				</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%" id="tab_title">
			       <logic:notEmpty name="xssqCheck">
			       		<logic:equal value="Y" 	name="xssqCheck" property="bjxlwy">
			       			<li><a href="javascript:void(0);" onclick="selectTab(this,'0');"><span>班级心理周报</span></a></li>
			       		</logic:equal>
				       	<logic:equal value="Y" 	name="xssqCheck" property="gygly">
				       		<li><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>公寓心理周报</span></a></li>
				        </logic:equal>
				        <logic:equal value="Y" 	name="xssqCheck" property="tsxs">
				        	<input type="hidden" id="sfxypssb" value="${xssqCheck.sfxypssb}"/>
				        	<li><a href="javascript:void(0);" onclick="selectTab(this,'2');"><span>平时情况上报</span></a></li>
				        </logic:equal>
			       </logic:notEmpty>
			      </ul>
			    </div>
				<div class="searchtab">
					<table width="100%" border="0">
						<tr>
							<th width="10%">学年</th>
							<td>
								<html:select property="xn" styleId="xn" style="width:155px">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<th width="10%">学期</th>
							<td>
								<html:select property="xq" styleId="xq" style="width:155px">
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>								
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
		</html:form>

		<div class="main_box">
			<h3 class=datetitle_01>
				<span>查询结果&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
