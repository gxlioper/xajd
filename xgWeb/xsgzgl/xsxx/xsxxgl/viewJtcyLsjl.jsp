<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script language="javascript" src="js/comm/dateUtils.js"></script>

    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                pager:"pager",
                url:"xsxx_xsxxgl.do?method=viewJtcyLsjl&type=query&xh="+jQuery("#xh").val(),
                colList:[
                    { label : 'key', name : 'id', index : 'id',key : true, hidden : true },
                    { label : '姓名',name : 'cyxm', index : 'cyxm',width : '10%'},
                    { label : '出生年月', name : 'csny', index : 'csny', width : '10%'},
                    { label : '关系', name : 'jtcygxmc', index : 'jtcygxmc', width : '10%'},
                    { label : '证件类型', name : 'jtcyzjlx', index : 'jtcyzjlx', width : '10%'},
                    { label : '证件号码', name : 'jtcyzjhm', index : 'jtcyzjhm', width : '10%'},
                    { label : '文化程度', name : 'ylzd1', index : 'ylzd1', width : '10%'},
                    { label : '工作单位', name : 'cyxxdw', index : 'cyxxdw', width : '10%'},
                    { label : '职务', name : 'cyzy', index : 'cyzy', width : '10%'},
                    { label : '政治面貌', name : 'zzmmmc', index : 'zzmmmc', width : '10%'},
                    { label : '联系电话', name : 'cylxdh', index : 'cylxdh', width : '10%'},
                    { label : '操作人', name : 'czrxm', index : 'czrxm', width : '10%'},
                    { label : '操作时间', name : 'czsj', index : 'czsj', width : '10%'},
                    { label : '操作类型', name : 'czlx', index : 'czlx', width : '10%'}
                ],
                sortname : "czsj",
                sortorder : "desc",
                multiselect:false};
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
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
<html:form action="/xsxx_xsxxgl" method="post">
    <!-- 隐藏域 -->
    <html:hidden property="xh" styleId="xh"/>
    <html:hidden property="zd" styleId="zd"/>

    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>

            </ul>
        </div>
    </div>
    <!-- 过滤条件 -->
    <div style="display: none">
        <%@ include file="/comm/search/superSearchArea.jsp"%>
    </div>

    <!-- 过滤条件 end-->
</html:form>
<div class="formbox">
    <!--标题start-->
    <h3 class="datetitle_01">
        <span> 历史记录列表 </span>
    </h3>

    <table id="dataTable" ></table>
    <div id="pager"></div>

</div>
<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
    <tfoot>
    <tr>
        <td colspan="5">
            <div class="bz">"<span class="red">*</span>"为必填项  </div>
            <div class="btn">
                <button type="button"  name="关闭" onclick="Close()" id="buttonClose">关 闭</button>
            </div>
        </td>
    </tr>
    </tfoot>
</table>
</body>
</html>
