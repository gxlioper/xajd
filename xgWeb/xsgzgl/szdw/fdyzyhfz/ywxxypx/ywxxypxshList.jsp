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
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript">
        jQuery(function(){
            var gridSetting = {
                caption:"",
                pager:"pager",
                url:"szdw_fdy_ywxxypx.do?method=ywxxypxShList&type=query",
                colList:[
                    {label:'sqid',name:'sqid', index: 'sqid',width:'10%',key:true,hidden:true},
                    {label:'职工号',name:'zgh', index: 'zgh',width:'10%',formatter:function(cell,row){
                        return "<a href='javascript:void(0);' onclick='view(\""+row["sqid"]+"\")'>"+cell+"</a>";
                    }},
                    {label:'姓名',name:'xm', index: 'xm',width:'10%'},
                    {label:'性别',name:'xb', index: 'xb',width:'10%'},
                    {label:'所在书院',name:'symc', index: 'symc',width:'10%'},
                    {label:'所在学院',name:'bmmc', index: 'bmmc',width:'10%'},
                    {label:'培训名称',name:'pxmc', index: 'pxmc',width:'10%'},
                    {label:'培训时间',name:'pxsj', index: 'pxsj',width:'10%'},
                    {label:'组织部门',name:'zzbmmc', index: 'zzbmmc',width:'10%'},
                    {label:'学时',name:'xs', index: 'xs',width:'10%'},
                    {label:'审核状态',name:'shztmc', index: 'shztmc',width:'10%'},
                    {label:'审核id',name:'shid', index: 'shid',width:'11%',hidden:true},
                    {label:'gwid',name:'gwid', index: 'gwid',width:'11%',hidden:true},
                    {label:'shzt',name:'shztx', index: 'shztx',width:'10%',hidden:true},
                    {label:'splc',name:'splc', index: 'splc',width:'10%',hidden:true}
                ],
                sortname: "zgh",
                sortorder: "asc"
            }
            var map = getSuperSearch();
            map["shzt"] = "dsh";
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs(){
            var map = getSuperSearch();
            var shzt = jQuery("#shzt").val();
            if (shzt != ""){
                map["shzt"] = shzt;
            }
            jQuery("#dataTable").reloadGrid(map);
        }
        function selectTab(obj,shzt){
            jQuery("#shzt").val(shzt);

            if (shzt == "dsh"){
                jQuery("#li_sh").css("display","");
                jQuery("#li_qx").css("display","none");
            } else {
                jQuery("#li_sh").css("display","none");
                jQuery("#li_qx").css("display","");
            }

            jQuery(".ha").removeClass("ha");
            jQuery(obj).parent().addClass("ha");

            searchRs();
        }
        function view(val){
            showDialog("家访记录查看",700,550,"szdw_fdy_ywxxypx.do?method=view&sqid="+val);
        }
        function sh(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length == 0){
                showAlertDivLayer("请至少选定一条记录！");
                return false;
            } else if(rows.length == 1){
                var url = "szdw_fdy_ywxxypx.do?method=sh&sqid="+rows[0]["sqid"]+"&zgh="+rows[0]["zgh"]+"&gwid="+rows[0]["gwid"]+"&shid="+rows[0]["shid"];
                showDialog("岗位发布审核",750,550,url);
            } else {
                showDialog("岗位发布批量审核",500,300,"szdw_fdy_ywxxypx.do?method=plsh");
            }
        }
        //撤销审核
        function cancelSh(){
            var rows = jQuery("#dataTable").getSeletRow();
            if (rows.length != 1){
                showAlertDivLayer("请选择一条您要撤消的审核记录！");
            } else {
                var splc = rows[0]["splc"];
                var shid = rows[0]["shid"];
                var sqid = rows[0]["sqid"];
                var shzt = rows[0]["shzt"];
                showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
                    jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
                        // 判断是否最后一级撤销(1:最后一级撤销成功）
                        if("1" == data["cancelFlg"]){
                            jQuery.post("szdw_fdy_ywxxypx.do?method=cancelSh",{sqid:sqid,shzt:shzt},function(result){
                                showAlertDivLayer(result["message"],{},{"clkFun":function(){
                                    jQuery("#dataTable").reloadGrid();
                                }});
                            },'json');
                        }else{
                            showAlertDivLayer(data["message"],{},{"clkFun":function(){
                                jQuery("#dataTable").reloadGrid();
                            }});
                        }
                    },'json');
                }});
            }
        }
        function splcInfo(){
            var ids = jQuery("#dataTable").getSeletIds();
            var rows = jQuery("#dataTable").getSeletRow();
            if (1!=ids.length){
                showAlertDivLayer("请选择一条流程跟踪记录！");
            } else {
                showDialog("审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
            }
        }
        //批量审核
        function savePlsh(shzt, shyj) {
            var rows = jQuery("#dataTable").getSeletRow();
            var sqids = new Array();
            var gwids = new Array();
            var sqrs = new Array();
            var splcids =  new Array();
            jQuery.each(rows, function(i, row) {
                sqids.push(row["sqid"]);
                gwids.push(row["gwid"]);
                sqrs.push(row["zgh"]);
                splcids.push(row["splc"]);
            });
            jQuery.post("szdw_fdy_ywxxypx.do?method=plsh&type=save", {
                shzt : shzt,
                splcids : splcids,
                id : sqids,
                gwids : gwids,
                sqrs : sqrs,
                shyj : shyj,
            }, function(data) {

                showAlertDivLayer(data["message"], {}, {
                    "clkFun" : function() {
                        jQuery("#dataTable").reloadGrid();
                    }
                });
            }, 'json');
        }
    </script>
</head>

<body>

<div class="tab_cur">
    <p class="location">
        <em>您的当前位置：</em><a>${title }</a>
    </p>
</div>
<html:form action="/szdw_jfxx.do">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <input type="hidden" value="dsh" id="shzt"/>
    <div class="toolbox">
            <%--<logic:equal value="yes" name="writeAble">--%>
        <div class="buttonbox">
            <ul>
                <li id="li_sh">
                    <a href="javascript:void(0);" onclick="sh();return false;"
                       title="选中您要审核的记录，点击该按钮可以打开审核页面。"
                       class="btn_sh">审核</a>
                </li>
                <li id="li_qx" style="display: none;">
                    <a href="javascript:void(0);" onclick="cancelSh();return false;"
                       title="选中一条记录，点击该按钮您可以撤消失误的审核操作。"
                       class="btn_qxsh">撤消</a>
                </li>
                <li><a href="#" onclick="splcInfo();return false;" class="btn_cs">流程跟踪</a></li>
            </ul>
        </div>
            <%--</logic:equal>--%>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
        <div class="comp_title" id="comp_title">
            <ul style="width:90%">
                <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>待处理</span></a></li>
                <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>已处理</span></a></li>
            </ul>
        </div>
    </div>
</html:form>
<div class="mainbox">
    <!--标题start-->
    <h3 class="datetitle_01">
        <span></span>
    </h3>

    <table id="dataTable" ></table>
    <div id="pager"></div>

</div>
</body>
</html>
