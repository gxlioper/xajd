<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="xsgzgl/xyfd/jzfdwh/jljgtj/js/jljgtj.js"></script>
	<script type="text/javascript">
        var gridSetting = {
            caption : "收到预约列表",
            pager : "pager",
            url : "xyfd_jljgtj.do?method=jljgtjList&type=query",
            colList : [
                { label : 'yyh', name : 'yyh', index : 'yyh',key : true,hidden : true },
                { label : '辅导时间', name : 'fdsj', index : 'fdsj',width:'15%'},
                { label : '辅导地点', name : 'fddd', index : 'fddd', width : '20%' },
                { label : '辅导人员', name : 'fdjsxm', index : 'fdjsxm', width : '15%' },
                { label : '辅导人员类型', name : 'fdjslx', index : 'fdjslx', width : '5%' },
                { label : '课程名称', name : 'kcmc', index : 'kcmc', width : '10%' },
                { label : '学生姓名', name : 'xm', index : 'xm', width : '15%' },
                { label : '学生电话', name : 'lxdh', index : 'lxdh', hidden : true},
                { label : '学生电话', name : 'sjhm', index : 'sjhm', hidden : true},
                { label : '学生电话', name : '', index : '', width : '10%',formatter:function (cellValue, rowObject) {
					if(rowObject['lxdh']!=null&&rowObject['lxdh']!=''){
					    return rowObject['lxdh'];
					}else {
                        return rowObject['sjhm'];
					}
                }},
                { label : '是否解决', name : 'sfjj', index : 'sfjj', width : '5%'},
                { label : '评分', name : 'pf', index : 'pf', width : '5%'}
            ],

            radioselect:false
        }

        var gridSetting2 = {
            caption : "收到预约列表",
            pager : "pager",
            url : "xyfd_jljgtj.do?method=jljgtjList&type=query",
            colList : [
                { label : 'zxid', name : 'zxid', index : 'zxid',key : true,hidden : true },
                { label : '辅导时间', name : 'fdrq', index : 'fdrq',width:'15%'},
                { label : '辅导地点', name : 'fddd', index : 'fddd', width : '20%' },
                { label : '辅导人员', name : 'fdjsxm', index : 'fdjsxm', width : '15%' },
                { label : '辅导人员类型', name : 'fdjslx', index : 'fdjslx', width : '5%' },
                { label : '学生姓名', name : 'xm', index : 'xm', width : '15%' },
                { label : '学生电话', name : 'lxdh', index : 'lxdh', hidden : true},
                { label : '学生电话', name : 'sjhm', index : 'sjhm', hidden : true},
                { label : '学生电话', name : '', index : '', width : '10%',formatter:function (cellValue, rowObject) {
                    if(rowObject['lxdh']!=null&&rowObject['lxdh']!=''){
                        return rowObject['lxdh'];
                    }else {
                        return rowObject['sjhm'];
                    }
                }},
                { label : '咨询原因', name : 'zxyymc', index : 'zxyymc', width : '5%'},
                { label : '是否解决', name : 'sfjj', index : 'sfjj', width : '5%'},
                { label : '评分', name : 'pf', index : 'pf', width : '5%'}
            ],

            radioselect:false
        }

        jQuery(function(){
            var map = getSuperSearch();
            map["cxmb"]="fd";
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
	<input type="hidden" id="cxmb" value="fd"/>
	<%@ include file="/comm/hiddenValue.jsp"%>
	<div class="toolbox">
		<!-- 按钮 -->
		<div class="buttonbox">
			<ul>
				<li id="li_ck_fd">
					<a href="javascript:void(0);" onclick="ckfd();return false;"
					   title="选中您要查看的辅导记录，点击该按钮可以打开查看页面。"
					   class="btn_sh">查看详情</a>
				</li>
				<li id="li_ck_zx" style="display: none">
					<a href="javascript:void(0);" onclick="ckzx();return false;"
					   title="选中您要查看的学业与专业咨询记录，点击该按钮可以打开查看页面。"
					   class="btn_ck">查看详情</a>
				</li>
				<logic:equal name="writeAble" value="yes">
					<li id="li_dr_fd">
						<a href="javascript:void(0);" onclick="fddr();return false;"
						   class="btn_dr">导入</a>
					</li>
					<li id="li_dr_zx" style="display: none">
						<a href="javascript:void(0);" onclick="zxdr();return false;"
						   class="btn_dr">导入</a>
					</li>
					<li id="li_dc_fd">
						<a href="javascript:void(0);" onclick="fddc();return false;"
						   class="btn_dc">导出</a>
					</li>
					<li id="li_dc_zx" style="display: none">
						<a href="javascript:void(0);" onclick="zxdc();return false;"
						   class="btn_dc">导出</a>
					</li>
				</logic:equal>

			</ul>
		</div>
		<!-- 过滤条件 -->
		<%@ include file="/xsgzgl/xyfd/jzppwh/fdyywh/superSearchAreaforFdyy.jsp"%>
		<!-- 过滤条件 end-->
		<div class="comp_title" id="comp_title">
			<ul style="width:90%">
				<li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'fd');"><span>课程辅导记录</span></a></li>
				<li><a href="javascript:void(0);" onclick="selectTab(this,'zx');"><span>学业与专业咨询记录</span></a></li>
			</ul>
		</div>
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
