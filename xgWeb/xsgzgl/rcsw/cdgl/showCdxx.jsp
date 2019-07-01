<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/cdgl/js/cdxxwh.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		
	var gridSetting = {
		caption : "场地信息列表",
		pager : "pager",
		url : "rcsw_cdgl_cdxxwh.do?method=showCdxxQuery",
		multiselect:false,
		rowNum:10,
		colList : [
				{ label : 'key', name : 'cdid', index : 'cdid',key : true, hidden : true },
				{ label : '场地名称', name : 'cdmc', index : 'cdmc', width : '15%' },
				{ label : '楼栋', name : 'ld', index : 'ld', width : '10%' },
				{ label : '房间', name : 'fj', index : 'fj', width : '5%' },
				{ label : '容纳人数', name : 'rnrs', index : 'rnrs', width : '5%' },
				{ label : '收费标准', name : 'sfbz', index : 'sfbz', width : '5%' },
				{ label : 'splcid', name : 'splcid', index : 'splcid',hidden : true },
				{ label : '对外开放时间', name : 'dwkfsj', index : 'dwkfsj', width : '15%' },
				{ label : '用途', name : 'yt', index : 'yt', width : '18%' },
				{ name : 'sfkfsq', index : 'sfkfsq', hidden : true },
				{label:'操作',name:'cdid', index: '',width:'12%',noSort:true,formatter:function(cell,rowObject){
					   return "<label class='btn_01' onclick=\"selectCd('"+cell+"');\">选择</label>";}}
				  ],
		sortname : "cdmc", sortorder : "desc" }

	jQuery(function() {
		gridSetting["params"] = getCdcxSearch();
		jQuery("#dataTable").initGrid(gridSetting);
	});

	function selectCd(cdid){
		var gotoPath = jQuery("#gotoPath").val();

		if (gotoPath.split("?").length > 1){
			gotoPath = gotoPath+"&cdid="+cdid;
		} else {
			gotoPath = gotoPath+"?cdid="+cdid;
		}
		var api = frameElement.api;
		
		if (api){
			if (api.get('childDialog')){
				api.reload(api.get('parentDialog') ,gotoPath);
			} else {
				var W = api.opener;
				W.location=gotoPath;			
			}
		} else if (parent.window){
			parent.window.document.location=gotoPath;						
		}
		
		iFClose();
	}

	/**
	 * 查询
	 * @return
	 */
	function searchCd() {
		var map = getCdcxSearch();
		jQuery("#dataTable").reloadGrid(map);
	}

	/**
	*reset
	*/
	function resetSearchCd(){
		jQuery("#search_cdmc").val("");
		jQuery("#search_rnrsmin").val("");
		jQuery("#search_rnrsmax").val("");
		jQuery("#search_yt").val("");
		jQuery("#search_dwkfsjkssj").val("");
		jQuery("#search_dwkfsjjssj").val("");
	}
</script>
	</head>

	<body>
		<html:form action="/rcsw_cdgl_cdxxwh" >
			<input type="hidden" value="${gotoPath}" id="gotoPath"/>
			<input type="hidden" value="${search_sfkfsq}" id="search_sfkfsq"/>
			<%@ include file="/comm/hiddenValue.jsp"%>

			<div class="toolbox">
				<div class="searchtab">
				<!-- 模糊查询 -->
					<div class="adv_filter">
						<table border="0" width="100%">
							<tbody>
								<tr>
									<td style="text-align:right">
										场地名称
									</td>
									<td style="padding-left:25px;position: relative">
										<input type="text" name="search_cdmc" id="search_cdmc"
											size="20" />
									</td>
									<td style="text-align:right">
										容纳人数
									</td>
									<td>
										<input type="text" name="search_rnrsmin" id="search_rnrsmin" size="10" />
										-
										<input type="text" name="search_rnrsmax" id="search_rnrsmax" size="10" />
									</td>
								</tr>
								<tr>
									<td style="text-align:right">
										用途
									</td>
									<td style="padding-left:25px;position: relative">
										<input type="text" name="search_yt" id="search_yt" size="20" />
									</td>
									<td style="text-align:right">
										开放时间段
									</td>
									<td>
										<input type="text" name="search_dwkfsjkssj"
											id="search_dwkfsjkssj" size="10" onclick="return showCalendar('search_dwkfsjkssj','HH:mm',true,'search_dwkfsjjssj');"  readonly="true" />
										-
										<input type="text" name="search_dwkfsjjssj"
											id="search_dwkfsjjssj" size="10" onclick="return showCalendar('search_dwkfsjjssj','HH:mm',false,'search_dwkfsjkssj');" readonly="true"/>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<div class="btn">
											<button type="button" class="btn_cx" id="search_go"
												onclick="searchCd();return false;">
												查 询
											</button>
											<button type="button" class="btn_cz"
												onclick="resetSearchCd();return false;">
												重 置
											</button>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				<!-- 模糊查询 end-->
				</div>
			</div>
		</html:form>

		<div class="main_box">
			<h3 class=datetitle_01>
				<span>场地信息 &nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
