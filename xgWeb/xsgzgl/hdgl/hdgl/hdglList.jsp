<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/hdgl/js/hdgl.js"></script>
    <script type="text/javascript">
        var gridSetting = {
            caption:"活动列表",
            pager:"pager",
            url:"hdgl_hdgl.do?method=hdglList&type=query",
            colList:[
                {label:'活动id',name:'hdid', index: 'hdid',key:true,hidden:true},
                {label:'活动名称',name:'hdmc', index: 'hdmc',width:'18%',formatter:hdLink},
                {label:'发布方',name:'fbf', index: 'fbf',width:'18%'},
                {label:'活动类型',name:'hdlxmc', index: 'hdlcmc',width:'18%'},
                {label:'报名人数',name:'bmrs', index: 'bmrs',width:'10%'},
                {label:'发布时间',name:'fbsj', index: 'fbsj',width:'18%'},
                {label:'活动状态',name:'hdjxzt', index: 'hdjxzt',width:'18%'}
            ],
            sortname: "fbsj",
            sortorder: "desc"
        }

        function hdLink(cellValue,rowObject){
            var hdid = rowObject["hdid"];
            return "<a href='javascript:void(0);' onclick=\"hdShow('"+hdid+"')\" class='name'>"+cellValue+"</a>";
        }

        jQuery(function(){
            var map = getSuperSearch();
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
<html:form action="/hdgl_hdgl">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
        	<!-- 这是之前的方法 -->
<%--            <ul>--%>
<%--                <li><a href="javascript:void(0);" onclick="zjtcysz()" class="btn_sz">专家团成员设置</a></li>--%>
<%--            </ul>--%>
			<ul>
				<li><a href="javascript:void(0);" onclick="jdcysz()" class="btn_sz">阶段成员设置</a></li>
			</ul>
        </div>
        <!-- 过滤条件 -->
        <logic:equal name="userType" value="stu">
            <%@ include file="/comm/search/superSearchAreaStu.jsp"%>
        </logic:equal>
        <logic:notEqual name="userType" value="stu">
            <%@ include file="/comm/search/superSearchArea.jsp"%>
        </logic:notEqual>
        <!-- 过滤条件 end-->
    </div>
</html:form>
<div class="formbox">
    <!--标题start-->
    <h3 class="datetitle_01">
        <span> 活动列表 </span>
    </h3>

    <table id="dataTable" ></table>
    <div id="pager"></div>

</div>
</body>
</html>
