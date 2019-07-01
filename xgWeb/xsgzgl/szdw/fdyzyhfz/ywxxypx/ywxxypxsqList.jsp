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
                url:"szdw_fdy_ywxxypx.do?method=ywxxypxSqList&type=query",
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
                    {label:'状态',name:'shztmc', index: 'shztmc',width:'10%'},
                    {label:'shzt',name:'shzt', index: 'shzt',width:'10%',hidden:true},
                    {label:'splc',name:'splc', index: 'splc',width:'10%',hidden:true}
                ],
                sortname: "zgh",
                sortorder: "asc"
            }
            jQuery("#dataTable").initGrid(gridSetting);
        });
        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function add(){
            showDialog("增加培训经历",700,550,"szdw_fdy_ywxxypx.do?method=add");
        }
        function update(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length != 1){
                showAlertDivLayer("请选择一条您要修改的记录！");
                return false;
            }
            if(rows[0]["shzt"] != '0' && rows[0]["shzt"] != '3'){
                showAlertDivLayer("审核中数据不能修改！");
                return;
            }
            showDialog("修改培训经历",700,550,"szdw_fdy_ywxxypx.do?method=update&sqid="+rows[0]["sqid"]);
        }
        function view(val){
            showDialog("培训经历查看",700,550,"szdw_fdy_ywxxypx.do?method=view&sqid="+val);
        }
        function del() {
            var ids = jQuery("#dataTable").getSeletIds();
            if(ids == 0){
                showAlertDivLayer("请选择一条您要删除的记录！");
                return false;
            }
            var rows = jQuery("#dataTable").getSeletRow();
            for(var i=0;i<rows.length;i++){
                if(rows[i]["shzt"] != '0' && rows[i]["shzt"] != '3'){
                    showAlertDivLayer("审核中数据不能删除，请确认！");
                    return false;
                }
            }
            confirmInfo("您确定要删除"+ids.length +"条记录吗?",function(ty){
                if(ty=="ok"){
                    jQuery.post("szdw_fdy_ywxxypx.do?method=del",{values:ids.toString()},function(data){
                        alertInfo(data["message"]);
                        jQuery("#dataTable").reloadGrid();
                    },'json');
                }
            });
        }
        function submit(){
            var ids = jQuery("#dataTable").getSeletIds();
            if (ids.length != 1){
                showAlertDivLayer("请选择一条您要提交的记录！");
            }else{
                var rows = jQuery("#dataTable").getSeletRow();
                var url = "szdw_fdy_ywxxypx.do?method=submit";
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
                    jQuery.post("szdw_fdy_ywxxypx.do?method=cancel",
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
                showDialog("绿色通道审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
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
<html:form action="/szdw_jfxx.do">
    <%@ include file="/comm/hiddenValue.jsp"%>
    <div class="toolbox">
            <%--<logic:equal value="yes" name="writeAble">--%>
        <div class="buttonbox">
            <ul>
                <li><a href="javascript:void(0);" id="btn_zj" class="btn_zj" onclick="add();return false;">增加</a></li>
                <li><a href="javascript:void(0);" id="btn_xg" class="btn_xg" onclick="update();return false;">修改</a></li>
                <li><a href="javascript:void(0);" id="btn_sc" class="btn_sc" onclick="del();return false;">删除</a></li>
                <li><a href="javascript:void(0);" onclick="submit();return false;" class="btn_shuc">提交</a></li>
                <li><a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">撤销</a></li>
                <li><a href="javascript:void(0);" onclick="Lcinfo();return false;" class="btn_cs">流程跟踪</a></li>
            </ul>
        </div>
            <%--</logic:equal>--%>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>
<div class="formbox">
    <!--标题start-->
    <h3 class="datetitle_01">
        <span></span>
    </h3>

    <table id="dataTable" ></table>
    <div id="pager"></div>

</div>
</body>
</html>
