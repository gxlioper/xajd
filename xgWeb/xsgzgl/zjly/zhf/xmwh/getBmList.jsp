<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/zjly/zhf/js/xmwh.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"部门列表",
				pager:"pager",
				url:"zhf_jfxmwh.do?method=getBmList&type=query",
				colList : [
							{ label : 'bmdm', name : 'bmdm', index : 'bmdm',key : true, hidden : true },
							{ label : '部门代码', name : 'bmdm', index : 'bmdm',width:'40%' },
							{ label : '部门名称', name : 'bmmc', index : 'bmmc', width : '60%'}
							],
				params:{cxzd:"wsq",jfxmdm:${jfxms}},
				sortname: "bmdm",
			 	sortorder: "asc",
			 	radioselect:false
		}

		var gridSetting2 = {
				caption:"部门列表",
				pager:"pager",
				url:"zhf_jfxmwh.do?method=getBmList&type=query",
				colList : [
							{ label : 'bmdm', name : 'bmdm', index : 'bmdm',key : true, hidden : true },
							{ label : '部门代码', name : 'bmdm', index : 'bmdm',width:'40%' },
							{ label : '部门名称', name : 'bmmc', index : 'bmmc', width : '60%'},
							],
				params:{cxzd:"ysq",jfxmdm:${jfxms}},
				sortname: "bmdm",
			 	sortorder: "asc",
			 	radioselect:false
		}
			jQuery(function(){
				var map = {};
				map["cxzd"]="wsq";
				map["jfxmdm"]=${jfxms};
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
		</script>
	</head>
	<body>
	<div class="searchtab">
		<html:form action="/zhf_jfxmwh">
			<table width="100%" border="0">
					<tbody>
						<tr>
							<th width="15%" style="margin-left:20px" id="xsmc">
								部门名称
							</th>
							<td>
								<input type="text" id="cxmc" style="width:230px"/>
								<button type="button" class="btn_cx" id="search_go"
										onclick="queryBm()">
										查 询
									</button>
							</td>
						</tr>
					</tbody>
				</table>
			</html:form>
		</div>
		<input type="hidden" id="cxzt" value="wsq"/> 
		<input type="hidden" id="jfxms" value="${jfxms}"/>
		<div class="comp_title" id="comp_title">
	      <ul style="width:90%">
	        <li class="ha"><a href="javascript:void(0);" onclick="selectSqxm(this,'wsq');"><span>未授权</span></a></li>
	        <li><a href="javascript:void(0);" onclick="selectSqxm(this,'ysq');"><span>已授权</span></a></li>
	      </ul>
	    </div>
			<div class="main_box">
				<h3 class=datetitle_01>
					<span>部门列表&nbsp;&nbsp; </span>
				</h3>
				<div class="con_overlfow">
					<table id="dataTable" ></table>
					<div id="pager"></div>
				</div>
			</div>
			
			<div style="height:30px;"></div>
			 <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td>
							<div id ="qxsq" style="display:none" class="btn">
								<button  type="button" onclick="qx();">
									取消授权
								</button>
								<button type="button" name="关 闭" onclick="iFClose();">
									关 闭
								</button>
							</div>
							<div id="bcsq" class="btn">
								<button id="bcsq" type="button" onclick="sq();">
									保存授权
								</button>
								<button type="button" name="关 闭" onclick="iFClose();">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				</table>
		
	</body>
</html>
