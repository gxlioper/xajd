<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="xsgzgl/xyfd/jzfdwh/xyzyzxwh/js/xyzyzx.js"></script>
	<script type="text/javascript">
        var gridSetting = {
            caption : "收到预约列表",
            pager : "pager",
            url : "xyfd_xyzyzxjl.do?method=xyzyzxList&type=query",
            colList : [
                { label : 'zxid', name : 'zxid', index : 'zxid',key : true ,hidden : true},
                { label : '学生学号', name : 'xh', index : 'xh',hidden:true},
                { label : '学生姓名', name : 'xm', index : 'xm',width:'10%'},
                { label : '所在班级', name : 'bjmc', index : 'bjmc',width:'10%'},
                { label : '咨询原因', name : 'zxyymc', index : 'zxyymc',width:'5%'},
                { label : '时间', name : 'fdrq', index : 'fdrq',width:'10%'},
                { label : '辅导人员', name : 'fdjs', index : 'fdjs',width:'10%',hidden : true},
                { label : '辅导人员', name : 'yhm', index : 'yhm',width:'10%',hidden : true},
                { label : '辅导日期', name : 'fdrq', index : 'fdrq',width:'10%',hidden : true},
                { label : '辅导时间', name : 'fdsj', index : 'fdsj',width:'10%',hidden : true},
                { label : '辅导地点', name : 'fddd', index : 'fddd', width : '15%' },
                { label : '具体举措', name : 'jtjc', index : 'jtjc', width : '25%',formatter:function (cellValue, rowObject) {
                    return rowObject['jtjc'].substring(0,20)+'......'
                } },
                { label : '咨询状态', name : 'zxzt', index : 'zxzt', width : '15%',hidden : true },
                { label : 'lrr', name : 'lrr', index : 'lrr', width : '30%',hidden : true },
                { label : 'lrsj', name : 'lrsj', index : 'lrsj', width : '30%',hidden : true }
            ],
            sortname: "lrsj",
            sortorder: "desc",
            radioselect:false
        }

        jQuery(function(){
            var map = getSuperSearch();
//            map["yyzt"]="dfd";
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });



	</script>
</head>

<body>
<div class="tab_cur">
	<p class="location">
		<em>您的当前位置：</em><a>${title }</a>
	</p>
</div>
<html:form action="/xyfd_fdjl">
	<%@ include file="/comm/hiddenValue.jsp"%>
	<input type="hidden" id="userName" name="userName" value="${userName}"/>
	<div class="toolbox">
		<!-- 按钮 -->
		<div class="buttonbox">
			<ul>
				<logic:equal name="writeAble" value="yes">
					<logic:equal name="isJsOrPb" value="true">
						<li>
							<a href="javascript:void(0);" onclick="zjjl();return false;" class="btn_zj">新增</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="xgjl();return false;" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >删除</a>
						</li>
					</logic:equal>
				</logic:equal>
				<li>
					<a href="javascript:void(0);" onclick="ckjl();return false;" class="btn_ck" >查看</a>
				</li>
			</ul>
		</div>
		<!-- 过滤条件 -->
		<%@ include file="/xsgzgl/xyfd/jzppwh/fdyywh/superSearchAreaforFdyy.jsp"%>
		<!-- 过滤条件 end-->
	</div>
</html:form>
<div class="main_box">
	<h3 class=datetitle_01>
		<span>活动补录审核列表&nbsp;&nbsp; </span>
	</h3>
	<div class="con_overlfow">
		<table id="dataTable" ></table>
		<div id="pager"></div>
	</div>
</div>
</body>
</html>
