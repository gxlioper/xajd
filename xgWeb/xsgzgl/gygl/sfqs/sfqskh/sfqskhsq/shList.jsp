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
    <script type="text/javascript" src="js/calendar/calendar.js"></script>
    <script>
        var gridSetting = {
            caption:"",
            pager:"pager",
            url:"gygl_sfqskh_wh.do?method=shList&type=query",
            colList:[
                {label:'sqid',name:'sqid',index :'sqid',key:true,hidden:true,width:'10%'},
                {label:'学年',name:'xn',index:'xn',width:'10%',formatter:function(val,row){
                    return "<a href='javascript:void(0);' onclick='view(\""+row["sqid"]+"\")'>"+val+"</a>";
                }},
                {label:'楼栋名称',name:'ldmc',index:'ldmc',width:'10%'},
                {label:'寝室号',name:'qsh',index:'qsh',width:'10%'},
                {label:'申请人',name:'xm',index:'xm',width:'5%'},
                {label:'申请时间',name:'sqsj',index:'sqsj',width:'10%'},
                {label:'审核状态',name:'shztmc',index:'shztmc',width:'10%'},
                {label:'审核id',name:'shid', index: 'shid',width:'11%',hidden:true},
                {label:'gwid',name:'gwid', index: 'gwid',width:'11%',hidden:true},
                {label:'splc',name:'splc', index: 'splc',width:'11%',hidden:true},
                {label:'xh',name:'xh', index: 'xh',width:'11%',hidden:true},
                {label:'shztx',name:'shztx', index: 'shztx',hidden:true}
            ],
            sortname: "xn,sqsj",
            sortorder: "desc"
        };
        //初始化
        jQuery(document).ready(function(){
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

        function view(sqid){
            var url="gygl_sfqskh_wh.do?method=view&sqid="+sqid;
            showDialog("查看", 700, 600, url);
        }
        function sh(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length == 0){
                showAlertDivLayer("请至少选定一条记录！");
                return false;
            } else if(rows.length == 1){
                var url = "gygl_sfqskh_wh.do?method=sh&sqid="+rows[0]["sqid"]+"&xh="+rows[0]["xh"]+"&gwid="+rows[0]["gwid"]+"&shid="+rows[0]["shid"];
                showDialog("审核",750,600,url);
            } else {
                showDialog("批量审核",500,300,"gygl_sfqskh_wh.do?method=plsh");
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
                var shzt = rows[0]["shztx"];
                showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
                    jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
                        // 判断是否最后一级撤销(1:最后一级撤销成功）
                        if("1" == data["cancelFlg"]){
                            jQuery.post("gygl_sfqskh_wh.do?method=cancelSh",{sqid:sqid,shzt:shzt},function(result){
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
        function selectTab(obj,shzt){
            jQuery("#shzt").val(shzt);

            if (shzt == "dsh"){
//                jQuery("#dataTable").initGrid(gridSetting);
                jQuery("#li_sh").css("display","");
                jQuery("#li_qx").css("display","none");
            } else {
//                jQuery("#dataTable").initGrid(gridSetting2);
                jQuery("#li_sh").css("display","none");
                jQuery("#li_qx").css("display","");
            }

            jQuery(".ha").removeClass("ha");
            jQuery(obj).parent().addClass("ha");

            searchRs();
        }
        //批量审核
        function savePlsh(shzt, shyj) {
            var rows = jQuery("#dataTable").getSeletRow();
            var guid = new Array();
            var gwid = new Array();
            var sqrs = new Array();
            var splcs =  new Array();
            jQuery.each(rows, function(i, row) {
                guid.push(row["sqid"]);
                gwid.push(row["gwid"]);
                sqrs.push(row["xh"]);
                splcs.push(row["splc"]);
            });
            jQuery.post("gygl_sfqskh_wh.do?method=plsh&type=save", {
                shzt : shzt,
                splcs : splcs,
                sqids : guid,
                gwids : gwid,
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
        function printWord() {
            var ids = jQuery("#dataTable").getSeletIds();
            var url;
            if(ids.length == 1){
                url = "gygl_sfqskh_wh.do?method=downloadWord&sqid="+ids[0];
            } else if(ids.length > 1){
                url = "gygl_sfqskh_wh.do?method=downloadZip&sqids="+ids.toString();
            } else {
                showAlert("请至少选择一条记录");
                return false;
            }

            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
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

<html:form action="/gygl_sfqskh_wh" method="post">
    <input type="hidden" value="dsh" id="shzt"/>
    <!-- 隐藏域 -->
    <%@ include file="/comm/hiddenValue.jsp"%>
    <!-- 隐藏域 -->
    <div class="toolbox" id="dgncz">
        <!-- 按钮 -->
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
                <li><a href="#" onclick="printWord();return false;" class="btn_dy">打印</a></li>

            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <div class="comp_title" id="comp_title">
            <ul style="width:90%">
                <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>待处理</span></a></li>
                <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>已处理</span></a></li>
            </ul>
        </div>
    </div>

</html:form>
<div class="main_box">
    <!--标题start-->
    <h3 class="datetitle_01">
        <span> 空调申请审核列表 </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
