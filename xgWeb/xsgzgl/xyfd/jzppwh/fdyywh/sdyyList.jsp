<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xyfd/jzppwh/fdyywh/js/wfcyy.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>

		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
					caption : "收到预约列表",
					pager : "pager",
					url : "xyfd_sdyy.do?method=sdyyList&type=query",
					colList : [
						{ label : 'yyid', name : 'yyid', index : 'yyid',key : true,hidden : true },
						{ label : '预约号', name : 'yyh', index : 'yyh',width:'12%',formatter:yyhLink},
						{ label : '预约学生', name : 'xh', index : 'xh',hidden:true},
						{ label : '预约学生', name : 'xm', index : 'xm',width:'10%'},
						{ label : '预约人', name : 'yyr', index : 'yyr',width:'10%'},
						{ label : '辅导时间', name : 'fdsj', index : 'fdsj',width:'10%'},
                        { label : '辅导课程', name : 'fdkc', index : 'fdkc', hidden : true },
						{ label : '课程名称', name : 'kcmc', index : 'kcmc', width : '15%' },
						{ label : '审核状态', name : 'shztmc', index : 'shztmc', width : '10%' },
						{ label : '审核状态', name : 'zt', index : 'zt', hidden : true}
					],
					params:{yyzt:"dsh"},
                    sortname : "yyh",
                    sortorder : "desc",
                    radioselect:false
				}
				var map = getSuperSearch();
                map["yyzt"]="dsh";
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			})
			//确认预约
			function submitYy() {
                var ids = jQuery("#dataTable").getSeletIds();
                var rows = jQuery("#dataTable").getSeletRow();
                if (ids.length != 1) {
                    showAlertDivLayer("请选择一条您要确认的预约！");
                    return false;
                }
                if(rows[0]['zt']=='3'||rows[0]['zt']=='0'){
                    showAlertDivLayer("该辅导预约已取消！");
                    return false;
				}
                if(rows[0]['zt']=='1'){
                    showAlertDivLayer("该辅导预约已确认！");
                    return false;
                }
                if(rows[0]['zt']=='4'||rows[0]['zt']=='6'){
                    showAlertDivLayer("该辅导预约已完成，请勿重复确认！");
                    return false;
				}
                showConfirmDivLayer("您确定要确认选择的预约吗？", {
                    "okFun" : function() {
                        jQuery.post("xyfd_sdyy.do?method=submitYy", {
                            values : ids.toString()
                        }, function(data) {
                            showAlertDivLayer(data["message"]);
                            jQuery("#dataTable").reloadGrid();
                        }, 'json');
                    }
                });
            }
            //取消预约
            function cancelYy() {
                var ids = jQuery("#dataTable").getSeletIds();
                var rows = jQuery("#dataTable").getSeletRow();
                if (ids.length != 1) {
                    showAlertDivLayer("请选择一条您要取消的预约！");
                    return false;
                }
                if (rows[0]['zt'] != '5'&&rows[0]['zt'] != '1') {
                    showAlertDivLayer("只有预约中或已预约的记录才能被取消！");
                    return false;
                }
                var height = jQuery(window).height();
                var url = 'xyfd_fqyy.do?method=qxYy&yyid=' + rows[0]["yyid"];
                showDialog('取消预约原因', 600, height-250, url);
            }

            function add(yysf) {
                var height = jQuery(window).height();
                var rows = jQuery("#dataTable").getSeletRow();
                if (rows.length != 1) {
                    showAlertDivLayer("请选择一条您要预约的课程！");
                }
                var url = "xyfd_fqyy.do?method=addYy&yysf="+yysf+"&fdkc="+rows[0]['fdkc']+"&xh="+rows[0]['xh'];
                showDialog("辅导课程申请", 800, height-250, url);
            }
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xyfd_fqyy">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<logic:equal name="isJsOrPb" value="true">
								<li>
									<a href="javascript:void(0);" onclick="submitYy();return false;" class="btn_sr">确认预约</a>
								</li>
								<%--<li>--%>
									<%--<a href="javascript:void(0);" onclick="cancelYy();return false;" class="btn_shuc">取消预约</a>--%>
								<%--</li>--%>
								<li>
									<a href="javascript:void(0);" onclick="add('tea');return false;"  class="btn_zj" >预约下次辅导</a>
								</li>
							</logic:equal>
						</logic:equal>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/xsgzgl/xyfd/jzppwh/fdyywh/superSearchAreaforFdyy.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>活动补录申请列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
