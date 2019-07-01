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
            url:"qgzx_gwglnew.do?method=gwfbCx&type=query",
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
                {label:'审核状态',name:'shztmc', index: 'shztmc',width:'11%'},
                {label:'审核状态',name:'shzt', index: 'shzt',width:'11%',hidden:true},
                {label:'审批流程',name:'splc', index: 'splc',width:'11%',hidden:true}
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
            var url = "qgzx_gwglnew.do?method=hmdyz";
            jQuery.post(url,{},function(data){
                if(data["message"] == "success"){//不是黑名单单位
                    var url="qgzx_gwglnew.do?method=gwfbZj";
                    showDialog("岗位信息增加", 800, 600, url);
                } else {
                    showAlertDivLayer(data["message"]);
                }
            },'json')
        }

        function xgyrdw(){
            var row = jQuery("#dataTable").getSeletRow();
            if(row.length != 1){
                showAlertDivLayer("请选择一条您要修改的数据！");
                return;
            }
            if(row[0]["shzt"] != '0' && row[0]["shzt"] != '3'){
                showAlertDivLayer("已审核以及审核中数据不能修改！");
                return;
            }
            var url="qgzx_gwglnew.do?method=gwfbXg&gwdm="+row[0]["gwdm"];
            showDialog("岗位信息修改", 800, 600, url);
        }
        function view(id){
            var url="qgzx_gwglnew.do?method=gwfbCk&gwdm="+id;
            showDialog("用人单位查看", 800, 400, url);
        }
        function submit(){
            var ids = jQuery("#dataTable").getSeletIds();
            if (ids.length != 1){
                showAlertDivLayer("请选择一条您要提交的记录！");
            }else{
                var rows = jQuery("#dataTable").getSeletRow();
                var url = "qgzx_gwglnew.do?method=submitGwfb";
                for(var i=0;i<ids.length;i++){
                    if(rows[i]['shzt']!='0' && rows[i]['shzt']!='3' ){
                        showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
                        return false;
                    }
                }
                showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
                    jQuery.post(url,
                        {
                            values:ids.toString(),
                            splc : rows[0]['splc'],
                            shzt : rows[0]['shzt']
                        },function(data){
                            showAlertDivLayer(data["message"]);
                            jQuery("#dataTable").reloadGrid();
                        },'json');
                }});
            }
        }
        function cancel(){
            var ids = jQuery("#dataTable").getSeletIds();
            if (ids.length == 0) {
                showAlertDivLayer("请选择您要撤销的记录！");
            } else if (ids.length >1 ) {
                showAlertDivLayer("请选择一条您要撤销的记录！");
            } else {
                var rows = jQuery("#dataTable").getSeletRow();
                for(var i=0;i<ids.length;i++){
                    if(rows[i]['shzt']!='5'){
                        showAlertDivLayer("只有审核中的记录才能被撤销！");
                        return false;
                    }
                }
                showConfirmDivLayer("您确定要撤销选择的记录吗？",{"okFun":function(){
                    jQuery.post("qgzx_gwglnew.do?method=cancelGwfb",
                        {
                            values:ids.toString(),
                            splc : rows[0]['splc']
                        },function(data){
                            showAlertDivLayer(data["message"]);
                            jQuery("#dataTable").reloadGrid();
                        },'json');
                }});
            }
        }
        function Lcinfo(){
            var ids = jQuery("#dataTable").getSeletIds();
            var rows = jQuery("#dataTable").getSeletRow();
            if (ids.length != 1){
                showAlertDivLayer("请选择一条流程跟踪记录！");
            } else {
                var shzt = rows[0]["shzt"];
                if ("0" == shzt){
                    showAlertDivLayer(jQuery("#lable_wxglcxx").val());
                    return false;
                }
                showDialog("绿色通道审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['gwdm']+"&splc="+rows[0]['splc']);
            }
        }
        function yrdwSc(){
            var ids = jQuery("#dataTable").getSeletIds();
            var rows = jQuery("#dataTable").getSeletRow();
            if(ids.length == 0){
                showAlertDivLayer("请选择要删除的数据！");
                return false;
            }
            for(var i=0;i<rows.length;i++){
                if(rows[i]["shzt"] != "0" && rows[i]["shzt"] != "3"){
                    showAlertDivLayer("审核数据不能删除！");
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
                <logic:equal value="1" name="sfkfsq">
                    <li><a href="#" onclick="zjyrdw();return false;" class="btn_zj">岗位发布</a></li>
                    <li><a href="#" onclick="xgyrdw();return false;" class="btn_xg">岗位修改</a></li>
                    <li><a href="#" onclick="yrdwSc();return false;" class="btn_sc">删除</a></li>
                    <li><a href="#" onclick="submit();return false;" class="btn_shuc">提交</a></li>
                    <li><a href="#" onclick="cancel();return false;" class="btn_sr">撤销</a></li>
                    <li><a href="#" onclick="Lcinfo();return false;" class="btn_cs">流程跟踪</a></li>
                    <%--<li><a href="#" onclick="mmcsh();return false;" class="btn_dr">导入</a></li>--%>
                </logic:equal>
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
