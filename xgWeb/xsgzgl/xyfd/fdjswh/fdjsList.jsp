<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption:"",
                pager:"pager",
                url:"xyfd_fdjswh.do?method=fdjsList&type=query",
                colList:[
                    {label:'登记号',name:'djh', index: 'djh',width:"10%",key:true,formatter:jsLink},
                    {label:'职工号',name:'zgh', index: 'zgh',width:'10%' },
                    {label:'姓名',name:'xm', index: 'xm',width:'10%'},
                    {label:'性别',name:'xb', index: 'xb',width:'5%',formatter:function (cell,rowObject) {
                            if(rowObject["xb"]=="1"){
                                return "男";
                            }else if(rowObject["xb"]=="2"){
                                return "女";
                            }else {
                                return rowObject["xb"];
                            }
                        }},
                    {label:'职务/职称',name:'zc', index: 'zc',width:'10%'},
                    {label:'所在单位',name:'bmmc', index: 'bmmc',width:'10%'},
                    {label:'任课名称',name:'kcmc', index: 'kcmc',width:'10%'},
                    {label:'学科/专业',name:'xkzy', index: 'xkzy',width:'10%'},
                    {label:'辅导科目',name:'fdkm', index: 'fdkm',width:'10%'},
                    {label:'辅导室',name:'fdsmc', index: 'fdsmc',width:'10%'}
                ]
            }
            gridSetting["params"]=getSuperSearch();
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }

        function add(){
            showDialog("新增辅导教师",900,550,"xyfd_fdjswh.do?method=addfdjs");
        }
        function update(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length!=1){
                showAlertDivLayer("请选择一条您要修改的记录");
                return false;
            }
            showDialog("修改辅导教师",900,550,"xyfd_fdjswh.do?method=updatefdjs&djh="+rows[0]["djh"] );

        }

        function jsLink(cellValue,rowObject) {
            var djh = rowObject["djh"];
            return "<a href='javascript:void(0);' onclick=\"fdjsShow('"+djh+"')\" class='name'>"+cellValue+"</a>";
        }
        function fdjsShow(djh) {
            showDialog("查看辅导教师",800,550,"xyfd_fdjswh.do?method=fdjsView&djh="+djh );
        }

        function deleteFdjs(){
            var ids = jQuery("#dataTable").getSeletIds();
            if (ids.length == 0) {
                showAlertDivLayer("请选择您要删除的记录！");
            } else {
                var rows = jQuery("#dataTable").getSeletRow();
                showConfirmDivLayer("您确定要删除选择的记录吗？", {
                    "okFun" : function() {
                        jQuery.post("xyfd_fdjswh.do?method=deleteFdjs", {
                            values : ids.toString()
                        }, function(data) {
                            var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
                            mes+="</br>";
                            if(data["nodel"]!="-1"){
                                mes+="<font color='red'>"+data["nodel"]+"</font>";
                                mes+="正常运行中不能删除!";
                            }
                            showAlertDivLayer(mes);
                            jQuery("#dataTable").reloadGrid();
                        }, 'json');
                    }
                });
            }
        }
    </script>
</head>
<body>
<div class="tab_cur">
    <p class="location">
        <em>您的当前位置：</em><a>${title }</a>
    </p>
</div>
<html:form action="/hdgl_hdgl_tj">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <li><a href="#" class="btn_zj" onclick="add();return false;">增加</a></li>
                <li><a href="#" class="btn_xg" onclick="update();return false;">修改</a></li>
                <li><a href="#" class="btn_sc" onclick="deleteFdjs();return false;">删除</a></li>
                <%--<li><a href="#" class="btn_dr" onclick="importConfig();return false;">导入</a></li>--%>
                <%--<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>--%>
                    <%--<li><a href="#" class="btn_dc" onclick="return false;">推送报名数据</a></li>--%>
            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>活动签到&nbsp;&nbsp; </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
