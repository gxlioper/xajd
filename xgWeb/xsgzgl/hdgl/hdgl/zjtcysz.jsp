<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/hdgl/js/zjtcysz.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"",
				pager:"pager",
				url:"hdgl_hdgl.do?method=zjtcysz&type=query&jdid=${jdid}",
				colList:[
					{label:'用户名',name:'yhm', index: 'yhm',width:'35%',key:true},
					{label:'姓名',name:'xm', index: 'xm',width:'30%'},
					{label:'所在部门',name:'bmmc', index: 'bmmc',width:'35%'}
				],
				params:{sffp:"0"},
				sortname: "yhm",
			 	sortorder: "asc",
			 	radioselect:false
		}

		var gridSetting2 = {
				caption:"",
				pager:"pager",
				url:"hdgl_hdgl.do?method=zjtcysz&type=query&jdid=${jdid}",
				colList:[
					{label:'用户名',name:'yhm', index: 'yhm',width:'35%',key:true},
					{label:'姓名',name:'xm', index: 'xm',width:'30%'},
					{label:'所在部门',name:'bmmc', index: 'bmmc',width:'35%'}
				],
				params:{sffp:"1"},
				sortname: "yhm",
			 	sortorder: "asc",
			 	radioselect:false
		}
			
		jQuery(function(){
			var map = getSuperSearch();
			map["sffp"]="0";
			var checkVal = new Array();
			var checkValObj = jQuery("[name='checkVal']");
			for(var i=0;i<checkValObj.length;i++){
				checkVal.push(jQuery(checkValObj[i]).val());
			}
			map["ids"]=checkVal;
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
		</script>
	</head>

	<body>
		<div class="prompt" id="div_help">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				选中多个活动时，为了避免冲突只有未分配页签！
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<html:form action="/hdgl_hdgl">
			<input type="hidden" id="sffp" value="0"/>
			<input type="hidden" id="jdid" value="${jdid}"/>
			<div style="display:none">
				<logic:iterate id="id" name="ids">
					<input name="checkVal" value="${id}" type="hidden"/>
				</logic:iterate>
			</div>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li id="li_bc">
							<a href="javascript:void(0);" onclick="saveFp();return false;" 
							   title=""
							   class="btn_ccg">分配</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelFp();return false;" 
							   title=""
							   class="btn_qxsh">取消分配</a>
						</li>	
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'0');"><span>未分配</span></a></li>
			        <logic:equal value="true" name="isShow" >
			        	<li><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>已分配</span></a></li>
			        </logic:equal>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>查询结果&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
