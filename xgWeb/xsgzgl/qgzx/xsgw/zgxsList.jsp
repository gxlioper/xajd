<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type='text/javascript' src="js/xgutil.js"></script>
    <script type='text/javascript' src='dwr/engine.js'></script>
    <script type='text/javascript' src='dwr/util.js'></script>
    <script type='text/javascript' src='dwr/interface/exportData.js'></script>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="xsgzgl/qgzx/xsgw/js/xsgwsh.js"></script>
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption:"学生岗位申请列表",
                pager:"pager",
                url:"qgzx_xsgwsh.do?method=zgxsList&type=query",
                colList:[
                    {label:'学号',name:'xh', index: 'xh',width:'12%',formatter:function(value,row){
                        return "<a href='javascript:void(0);' class='name' onClick='seeInfo(\""+row["gwdm"]+"\",\""+row["xh"]+"\")'>"+value+"</a>";
                    }},
                    {label:'姓名',name:'xm', index: 'xm',width:'8%'},
                    {label:'类别',name:'pycc', index: 'pycc',width:'7%',formatter:function(value,row){
                        if(value == "1"){
                            return "博士生";
                        } else if(value == "2"){return "硕士生";} else if(value == "3"){return "本科生"}
                        else if(value == "4"){
                            return "专科生"
                        } else if(value == "9"){
                            return "其他";
                        } else {
                            return value;
                        }
                    }},
                    {label:'行政班级',name:'bjmc', index: 'bjmc',width:'12%'},
                    {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'10%'},
                    {label:'岗位名称',name:'gwmc', index: 'gwmc',width:'10%'},
                    {label:'用人单位',name:'yrdwmc', index: 'yrdwmc',width:'12%'},
                    {label:'单位联系方式',name:'lxdh', index: 'lxdh',width:'12%'},
                    {label:'录用时间',name:'sgsj', index: 'sgsj',width:'15%'},
                    {label:'岗位代码',name:'gwdm', index: 'gwdm',width:'15%',hidden:true},
                    {label:'pk',name:'pk', index: 'pk',width:'15%',hidden:true}
                ],
                sortname: "xh",
                sortorder: "desc"
            };

            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function seeInfo(gwdm,xh){
            showDialog("学生岗位审核",765,500,"qgzx_xsgwsh.do?method=xsgwmxck&xh="+xh+"&gwdm="+gwdm);
        }
        function pljg() {
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length == 0){
                showAlertDivLayer("请选择学生！");
                return false;
            }
            var list = [];
            for(var i=0;i<rows.length;i++){
                var map = {};
                map["gwdm"] = rows[i]["gwdm"];
                map["xh"] = rows[i]["xh"];
                list.push(map);
            }

            var jgList = encodeURIComponent(encodeURIComponent(JSON.stringify(list)));
            var url = "qgzx_xsgwsh.do?method=pljg&jgList="+jgList;
            showDialog("学生离职申请",765,500,url);
        }
        function ckgzjl(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length != 1){
                showAlertDivLayer("请选择一条记录！");
                return false;
            }
            window.location.href="qgzx_xsgwsh.do?method=ckgzjl&xh="+rows[0]["xh"];
        }
        function add(){
            showDialog("增加在岗学生",800,500,"qgzx_xsgwsh.do?method=zjzgxs");
        }
        //dcglbh,导出功能编号
        var DCGLBH = "qgzx_zgxs_dc";

        //自定义导出 功能
        function exportConfig() {
            //DCCLBH导出功能编号,执行导出函数
            customExport(DCGLBH, exprotData);
        }

        //导出方法
        function exprotData() {
            setSearchTj();//设置高级查询条件
            var url = "qgzx_xsgwsh.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,导出功能编号
            url = addSuperSearchParams(url);//设置高级查询参数
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
    </script>
</head>

<body>

<div class="tab_cur">
    <p class="location">
        <em>您的当前位置：</em><a>${title }</a>
    </p>
</div>
<html:form action="/qgzx_jfcjgl_cjff.do?method=gjcxCjff">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
        <div class="buttonbox">
            <ul>
                <logic:equal value="yes" name="writeAble">
                    <li>
                        <a href="javascript:void(0);" id="btn_sh" onclick="pljg();return false;" class="btn_sc">批量解雇</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" id="btn_qxsh" onclick="ckgzjl();return false;" class="btn_sh">查看工作记录</a>
                    </li>
                    <li>
                        <a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">增加在岗学生</a>
                    </li>
                </logic:equal>
                <li>
                    <a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">导出</a>
                </li>
            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>
<div class="main_box">
    <!--标题start-->
    <h3 class="datetitle_01">
        <span> 在岗学生列表</span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>

</div>
</body>
</html>
