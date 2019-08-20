<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="xsgzgl/xyfd/jzfdwh/pjwh/js/mydpj.js"></script>
	<script type="text/javascript">
        var gridSetting = {
            caption : "收到预约列表",
            pager : "pager",
            url : "xyfd_mydpj.do?method=mydpjList&type=query",
            colList : [
                { label : 'jlbh', name : 'jlbh', index : 'jlbh',key : true,hidden : true },
                { label : '预约学生', name : 'xh', index : 'xh',hidden:true},
                { label : '预约学生', name : 'xm', index : 'xm',width:'10%',hidden:true},
                { label : '类型', name : 'lxmc', index : 'lxmc',width:'10%'},
                { label : '辅导时间', name : 'fdsj', index : 'fdsj',width:'10%'},
                { label : '辅导地点', name : 'fddd', index : 'fddd', width : '15%' },
                { label : '辅导人员姓名', name : 'fdjsxm', index : 'fdjsxm', width : '15%' },
                { label : '辅导人员类型', name : 'fdjslx', index : 'fdjslx', width : '10%' },
                { label : '课程名称', name : 'kcmc', index : 'kcmc', width : '10%' },
                { label : '预约人', name : 'yyr', index : 'yyr',width:'5%'},
                { label : '审核状态', name : 'shztmc', index : 'shztmc', width : '10%',hidden:true },
                { label : '审核状态', name : 'zt', index : 'zt', hidden : true}
            ],
            sortname: "fdsj",
            sortorder: "desc",
            radioselect:false
        }

        var gridSetting2 = {
            caption : "收到预约列表",
            pager : "pager",
            url : "xyfd_mydpj.do?method=mydpjList&type=query",
            colList : [
                { label : 'jlbh', name : 'jlbh', index : 'jlbh',key : true,hidden : true },
                { label : '预约学生', name : 'xh', index : 'xh',hidden:true},
                { label : '预约学生', name : 'xm', index : 'xm',width:'10%',hidden:true},
                { label : '类型', name : 'lxmc', index : 'lxmc',width:'10%'},
                { label : '辅导时间', name : 'fdsj', index : 'fdsj',width:'10%'},
                { label : '辅导地点', name : 'fddd', index : 'fddd', width : '15%' },
                { label : '辅导人员姓名', name : 'fdjsxm', index : 'fdjsxm', width : '15%' },
                { label : '辅导人员类型', name : 'fdjslx', index : 'fdjslx', width : '10%' },
                { label : '课程名称', name : 'kcmc', index : 'kcmc', width : '10%' },
                { label : '预约人', name : 'yyr', index : 'yyr',width:'5%'},
                { label : '评分', name : 'pf', index : 'pf',width:'5%'},
                { label : '审核状态', name : 'shztmc', index : 'shztmc', width : '10%',hidden:true },
                { label : '审核状态', name : 'zt', index : 'zt', hidden : true}
            ],
            sortname: "fdsj",
            sortorder: "desc",
            radioselect:false
        }

        jQuery(function(){
            var map = getSuperSearch();
            map["pjzt"]="dpj";
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
<html:form action="/xyfd_mydpj">
	<input type="hidden" id="pjzt" value="dpj"/>
	<%@ include file="/comm/hiddenValue.jsp"%>
	<div class="toolbox">
		<!-- 按钮 -->
		<div class="buttonbox">
			<ul>
				<logic:equal name="writeAble" value="yes">
					<li id="li_tj">
						<a href="javascript:void(0);" onclick="txpj();return false;"
						   title="选中您要添加评价的记录，点击该按钮可以打开评价页面。"
						   class="btn_sh">填写评价</a>
					</li>
				</logic:equal>
				<li id="li_ck" style="display: none">
					<a href="javascript:void(0);" onclick="ckpj();return false;"
					   title="选中您要查看的评价，点击该按钮可以打开查看页面。"
					   class="btn_ck">查看评价</a>
				</li>
			</ul>
		</div>
		<!-- 过滤条件 -->
		<%@ include file="/xsgzgl/xyfd/jzppwh/fdyywh/superSearchAreaforFdyy.jsp"%>
		<!-- 过滤条件 end-->
		<div class="comp_title" id="comp_title">
			<ul style="width:90%">
				<li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dpj');"><span>待评价</span></a></li>
				<li><a href="javascript:void(0);" onclick="selectTab(this,'ypj');"><span>已评价</span></a></li>
			</ul>
		</div>
	</div>
</html:form>
<div class="main_box">
	<h3 class=datetitle_01>
		<span>满意度评价列表&nbsp;&nbsp; </span>
	</h3>
	<div class="con_overlfow">
		<table id="dataTable" ></table>
		<div id="pager"></div>
	</div>
</div>
</body>
</html>
