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
            caption:"岗位发布审核列表",
            pager:"pager",
            url:"qgzx_gwglnew.do?method=gwfbshCx&type=query",
            colList:[
                {label:'key',name:'gwdm', index: 'gwdm',key:true,hidden:true},
                {label:'岗位名称',name:'gwmc', index: 'gwmc',formatter:function(cell,rowObject){
                    return "<a href='javascript:void(0);' class='name' onclick='view(\""
                        + rowObject["gwdm"] + "\",\""+ rowObject["yrdwid"] +"\");'>" + cell
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
                {label:'审核id',name:'shid', index: 'shid',width:'11%',hidden:true},
                {label:'gwid',name:'gwid', index: 'gwid',width:'11%',hidden:true},
                {label:'splc',name:'splc', index: 'splc',width:'11%',hidden:true},
                {label:'yrdwid',name:'yrdwid', index: 'yrdwid',width:'11%',hidden:true},
                {label:'shzt',name:'shzt', index: 'shzt',hidden:true}
            ],
            sortname: "fbsj",
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

        function view(gwdm,yrdwid){
            var url="qgzx_gwglnew.do?method=gwfbshCk&gwdm="+gwdm+"&yrdwid="+yrdwid;
            showDialog("岗位申请查看", 800, 400, url);
        }
        function sh(){
            var rows = jQuery("#dataTable").getSeletRow();
            if(rows.length == 0){
                showAlertDivLayer("请至少选定一条记录！");
                return false;
            } else if(rows.length == 1){
                var url = "qgzx_gwglnew.do?method=gwfbsh&gwdm="+rows[0]["gwdm"]+"&yrdwid="+rows[0]["yrdwid"]+"&gwid="+rows[0]["gwid"]+"&shid="+rows[0]["shid"];
                showDialog("岗位发布审核",750,600,url);
            } else {
                showDialog("岗位发布批量审核",500,300,"qgzx_gwglnew.do?method=plsh");
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
                var sqid = rows[0]["gwdm"];
                var shzt = rows[0]["shzt"];
                showConfirmDivLayer("您确定要撤消对该记录的审核操作吗？",{"okFun":function(){
                    jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
                        // 判断是否最后一级撤销(1:最后一级撤销成功）
                        if("1" == data["cancelFlg"]){
                            jQuery.post("qgzx_gwglnew.do?method=cancelGwSh",{sqid:sqid,shzt:shzt},function(result){
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
                showDialog("审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['gwdm']+"&splc="+rows[0]['splc']);
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
            var yrdwids = new Array();
            var splcids =  new Array();
            jQuery.each(rows, function(i, row) {
                guid.push(row["gwdm"]);
                gwid.push(row["gwid"]);
                yrdwids.push(row["yrdwid"]);
                splcids.push(row["splc"]);
            });
            jQuery.post("qgzx_gwglnew.do?method=plsh&type=save", {
                shzt : shzt,
                splcids : splcids,
                id : guid,
                gwids : gwid,
                sqrs : yrdwids,
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
<div class="tab_cur" >
    <p class="location">
        <em>您的当前位置:</em><a>${title }</a>
    </p>
    <p class="help">
        <a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
    </p>
</div>

<html:form action="/qgzx_gwglnew" method="post">
    <input type="hidden" value="dsh" id="shzt"/>
    <!-- 隐藏域 -->
    <%@ include file="/comm/hiddenValue.jsp"%>
    <!-- 隐藏域 -->
    <div class="toolbox" id="dgncz">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <logic:equal value="yes" name="writeAble">
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
                </logic:equal>

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
        <span> 岗位发布审核列表 </span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
