<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		function getGridSettiong(){
		    var temp = "行为";
		    if(jQuery("#xxdm").val() == "13431") temp = "加分";
			var gridSetting = {
				caption:temp+"大类分统计结果",
				pager:"pager",
				url:"rcsw_rcxwwh_rcxwjggl.do?method=getXwdlfList&type=query",
				params:getSuperSearch(),
				multiselect:false
			};
				var colList=[
				   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
				   {label:'学年',name:'xn', index: 'xn',width:'10%'},
                   {label:'学号',name:'xh', index: 'xh',width:'15%',formatter:xhLink},
				   {label:'姓名',name:'xm', index: 'xm',width:'7%'},
				   {label:'性别',name:'xb', index: 'xb',width:'5%'},
				   {label:'学院',name:'xymc', index: 'xymc',width:'12%'},
				   {label:'班级',name:'bjmc', index: 'bjdm',width:'12%'}
				];
				
			var xwdl = jQuery("font[name=xwdl]");
			jQuery.each(xwdl,function(i,n){
				var xwdlJson = {
						label:jQuery(n).attr("xwmc"),
						name:"fz"+i,
						index:"fz"+i
				};
				colList.push(xwdlJson);
			});
			gridSetting["colList"] = colList;
			return gridSetting;				
		
			}

		function searchRs(){
			jQuery.ajaxSetup({async:false});
			var gridSetting = getGridSettiong();
			var map = getSuperSearch();
			gridSetting.params = map;
			//jQuery("#dataTable").reloadGrid(map);
			jQuery("#dataTable").initGrid(gridSetting);
			jQuery.ajaxSetup({async:true});
		}
		jQuery(function(){
			searchRs();
		});
		//日常行为大类总分导出
		function rcxwdlfDc() {
			var url ="rcsw_rcxwwh_rcxwjggl.do?method=rcxwdlfDc";
			var xnLength=jQuery("a[name=a_name_xn]").length;
			if(xnLength != "1"){
				showAlertDivLayer("请选择一个学年查询条件！");
				return false;
			}
			setSearchTj();
			url = addSuperSearchParams(url);
			
			document.forms[0].action = url;
			document.forms[0].submit();
		}
		function xhLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='xwjgView(\""
					+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
					+ "</a>";
		}
		function xwjgView(id, xh) {
            var temp = "日常行为结果查询";
            if(jQuery("#xxdm").val() == "13431") temp = "加分结果查询";
			showDialog(temp, 720, 520,
					"rcsw_rcxwwh_rcxwjggl.do?method=viewXwdljg&id=" + id + "&xh=" + xh);
		}
			
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<logic:present name="xwdlList">
		<div id="xwdlDiv">
			<logic:iterate id="z" name="xwdlList">
				<font style="display:none;" xwdm="${z.rcxwlbdldm }" xwmc="${z.rcxwlbdlmc }" name="xwdl"></font>
			</logic:iterate>
		</logic:present>
		<html:form action="/rcsw_rcxwwh_rcxwjggl">
			<input type="hidden" name="tableName" id="tableName" value="xg_rcsw_rcxwjg"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_dc" onclick="rcxwdlfDc();return false;">导出</a></li>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>
					<logic:equal name="xxdm" value="13431">
						加分大类分统计结果
					</logic:equal>
					<logic:notEqual name="xxdm" value="13431">
						日常行为大类分统计结果
					</logic:notEqual>&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
