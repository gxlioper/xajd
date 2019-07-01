<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript">
        var gridSetting;
        jQuery(function(){
            gridSetting = {
                caption:"",
                pager:"pager",
                url:"qgzx_jcdmwh_ajax.do?method=selectTeacher&type=query",
                colList:[
                    {label:'职工号',name:'zgh', index: 'zgh',width:'20%'},
                    {label:'姓名',name:'xm', index: 'xm',width:'10%'},
                    {label:'性别',name:'xb', index: 'xb',width:'10%'},
                    {label:'所属部门',name:'bmmc', index: 'bmmc',width:'20%'},
                    {label:'联系电话',name:'lxdh', index: 'lxdh',width:'20%'},
                    {label:'操作',name:'', index: '',width:'10%',formatter:function(cell,rowObject){
                        return "<label class='btn_01' onclick=\"select('"+rowObject["zgh"]+"','"+rowObject["xm"]+"','"+rowObject["lxdh"]+"','"+rowObject["bgdd"]+"','"+rowObject["bgdh"]+"','"+rowObject["dzyx"]+"');\">选择</label>";
                    }},
                    {label:'办公地点',name:'bgdd', index: 'bgdd',width:'20%',hidden:true},
                    {label:'办公电话',name:'bgdh', index: 'bgdh',width:'20%',hidden:true},
                    {label:'电子邮箱',name:'dzyx', index: 'dzyx',width:'20%',hidden:true}
                ],
                sortname: "zgh",
                sortorder: "asc",
                radioselect:false
            }

            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function select(zgh,xm,lxdh,bgdd,bgdh,dzyx){
            var api = frameElement.api;
            var parent = api.get('parentDialog');
            parent.jQuery("#zgh").val(zgh);
            parent.jQuery("#xmtr").html(xm);
            parent.jQuery("#lxdh").val(lxdh == 'null' ? '' : lxdh);
            parent.jQuery("#bgdd").val(bgdd == 'null' ? '' : bgdd);
            parent.jQuery("#bgdh").val(bgdh == 'null' ? '' : bgdh);
            parent.jQuery("#dzyx").val(dzyx == 'null' ? '' : dzyx);
//            parent.jQuery("#qq").val(rowObject[""]);
            iFClose();
        }
    </script>
</head>

<body>
<html:form action="/qgzx_jcdmwh_ajax">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- 按钮 -->
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
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
