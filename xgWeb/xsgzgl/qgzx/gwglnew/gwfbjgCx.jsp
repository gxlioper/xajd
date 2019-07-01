<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/syscommon/head.ini"%>
    <script type='text/javascript' src="js/uicomm.js"></script>
    <script type='text/javascript' src="js/String.js"></script>
    <script type='text/javascript' src="js/xgutil.js"></script>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script>
        var gridSetting = {
            caption:"岗位发布维护列表",
            pager:"pager",
            url:"qgzx_gwglnew.do?method=gwfbjgCx&type=query",
            colList:[
                {label:'key',name:'gwdm', index: 'gwdm',key:true,hidden:true},
                {label:'岗位名称',name:'gwmc', index: 'gwmc',formatter:function(cell,rowObject){
                    return "<a href='javascript:void(0);' class='name' onclick='view(\""
                        + rowObject["gwdm"] + "\");'>" + cell
                        + "</a>";
                }},
                {label:'用人单位',name:'yrdwmc', index: 'yrdwmc',width:'13%'},
                {label:'岗位类别',name:'gwlbmc', index: 'gwlbmc',width:'10%'},
                {label:'工作性质',name:'gwxzdm', index: 'gwxzdm',width:'3%',formatter:function(cell,rowObject){
                    if(cell == '0'){
                        return "临时";
                    } else if(cell == '1'){
                        return "正式";
                    } else {
                        return cell;
                    }
                }},
                {label:'单位类别',name:'dwlb', index: 'dwlb',formatter:function(cell,rowObject){
                    if(cell == "01"){
                        return "校内单位";
                    }
                    return "校外企业";
                }},
                {label:'招聘人数',name:'xqrs', index: 'xqrs',width:'7%'},
                {label:'截止时间',name:'zpjssj', index: 'zpjssj',width:'13%'},
                {label:'发布时间',name:'fbsj', index: 'fbsj',width:'11%'},
//                {label:'审核状态',name:'shztmc', index: 'shztmc',width:'11%'},
                {label:'审核状态',name:'shzt', index: 'shzt',width:'11%',hidden:true},
                {label:'审批流程',name:'splc', index: 'splc',width:'11%',hidden:true},
                {label:'数据来源',name:'sjly', index: 'sjly',width:'11%',formatter:function(cell,row){
                    if(cell == '1'){
                        return "结果增加数据"
                    } else {
                        return "审核流数据";
                    }
                }}
            ],
            sortname: "fbsj",
            sortorder: "desc"
        };
        //初始化
        jQuery(document).ready(function(){
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });

        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }

        function yrdwwhExportConfig() {
            //DCCLBH导出功能编号,执行导出函数
            customExport("qgzx_gwgl_gwfb.do", yrdwwhExportData);
        }



        // 导出方法
        function yrdwwhExportData() {
            //setSearchTj();//设置高级查询条件
            var url = "qgzx_gwglnew.do?method=gwExportData&dcclbh=" + "qgzx_gwgl_gwfb.do";//dcclbh,导出功能编号
            //url = addSuperSearchParams(url);//设置高级查询参数
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }


        function zjyrdw(){
            var url="qgzx_gwglnew.do?method=gwfbjgZj";
            showDialog("岗位信息增加", 800, 660, url);
        }

        function xgyrdw(){
            var row = jQuery("#dataTable").getSeletRow();
            if(row.length != 1){
                showAlertDivLayer("请选择一条您要修改的数据！");
                return;
            }
            if(row[0]["sjly"] != '1'){
                showAlertDivLayer("审核流来源数据不能修改！");
                return;
            }
            var url="qgzx_gwglnew.do?method=gwfbjgXg&gwdm="+row[0]["gwdm"];
            showDialog("岗位信息修改", 800, 600, url);
        }
        function view(id){
            var url="qgzx_gwglnew.do?method=gwfbjgCk&gwdm="+id;
            showDialog("用人单位查看", 800, 400, url);
        }

        function yrdwSc(){
            var ids = jQuery("#dataTable").getSeletIds();
            var rows = jQuery("#dataTable").getSeletRow();
            if(ids.length == 0){
                showAlertDivLayer("请选择要删除的数据！");
                return false;
            }
            for(var i=0;i<rows.length;i++){
                if(rows[i]["sjly"] != "1"){
                    showAlertDivLayer("选中数据中包含审核流来源数据，请确认！");
                    return false;
                }
            }

            showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
                jQuery.post("qgzx_gwglnew.do?method=gwSc",
                    {
                        values:ids.toString()
                    },function(data){
                        showAlertDivLayer(data["message"]);
                        jQuery("#dataTable").reloadGrid();
                    },'json');
            }});
        }
    </script>
</head>
<body>
<div class="tab_cur" >
    <p class="location">
        <em>您的当前位置:</em><a>${title }</a>
    </p>
    <p class="help">
        <a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
    </p>
</div>

<html:form action="/qgzx_gwglnew" method="post">
    <!-- 隐藏域 -->
    <%@ include file="/comm/hiddenValue.jsp"%>
    <!-- 隐藏域 -->
    <div class="toolbox" id="dgncz">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <logic:equal value="yes" name="writeAble">
                    <li><a href="#" onclick="zjyrdw();return false;" class="btn_zj">岗位发布</a></li>
                    <li><a href="#" onclick="xgyrdw();return false;" class="btn_xg">岗位修改</a></li>
                    <li><a href="#" onclick="yrdwSc();return false;" class="btn_sc">删除</a></li>
                </logic:equal>

                <li><a href="#" onclick="yrdwwhExportConfig();return false;" class="btn_dc">导出</a></li>
            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
    </div>

</html:form>
<div class="formbox">
    <!--标题start-->
    <h3 class="datetitle_01">
        <span> 岗位发布列表 </span>
    </h3>

    <table id="dataTable" ></table>
    <div id="pager"></div>

</div>
</body>
</html>
