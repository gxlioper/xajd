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
                multiselect:false,
                url:"xyfd_fdkcsq.do?method=selectPb&type=query",
                colList:[
                    { label : 'jgid', name : 'jgid', index : 'jgid',key : true,hidden : true },
                    { label : '登记号', name : 'djh', index : 'djh', width : '8%'},
                    { label : '学号', name : 'xh', index : 'xh', width : '10%'},
                    { label : '姓名', name : 'xm', index : 'xm', width : '10%' },
                    { label : '辅导科目', name : 'fdkm', index : 'fdkm', width : '10%' },
                    { label : '辅导室', name : 'fdsmc', index : 'fdsmc', width : '10%' },
                    { label : '辅导室地点', name : 'fdsdd', index : 'fdsdd', width : '15%' },
                    { label : '使用日期',name:'syksrq', index: 'syksrq',width:'7%',formatter:function (cell,rowObject) {
                        return rowObject["syksrq"] + "-" + rowObject["syjsrq"];
                    }},
                    { label : '使用结束日期',name:'syjsrq', index: 'syjsrq',width:'1%',hidden:true},
                    { label : '联系电话', name : 'lxdh', index : 'lxdh', width : '10%' },
                    { label : '数据来源', name : 'sjly', index : 'sjly', hidden : true},
                    { label : 'lrsj', name : 'lrsj', index : 'lrsj', hidden : true},
                    {label:'操作',name:'', index: '',width:'10%',formatter:function(cell,rowObject){
                        return "<label class='btn_01' onclick=\"select('"+rowObject["djh"]+"','"+rowObject["xm"]+"');\">选择</label>";
                    }}
                ],
                sortname: "djh",
                sortorder: "desc",
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
        function select(djh,xm){
            var api = frameElement.api;
            var parent = api.get('parentDialog');
            parent.jQuery("#fdjs").val(djh);
            parent.jQuery("#xm").val(xm == 'null' ? '' : djh+'：'+xm);
            jQuery.post("xyfd_fdkcsq.do?method=getFdjs", {
                    fdjs : djh
                },
                function(data) {
                    jQuery.each(parent.jQuery("input:checkbox"),function (element,index) {
                        jQuery(this).attr("disabled",false);
                        jQuery(this).prop("checked","");
                    })
                    var mon = data.fdjs.mon;
                    var tues = data.fdjs.tues;
                    var wed = data.fdjs.wed;
                    var thur = data.fdjs.thur;
                    var fri = data.fdjs.fri;
                    var sat = data.fdjs.sat;
                    var sun = data.fdjs.sun;
                    var mond = new Array();
                    var tuesd = new Array();
                    var wedd = new Array();
                    var thurd = new Array();
                    var frid = new Array();
                    var satd = new Array();
                    var sund = new Array();
                    if(mon!=null&&mon!=undefined){
                        mond = mon.split(",");
                    }
                    if(tues!=null&&tues!=undefined){
                        tuesd = tues.split(",");
                    }
                    if(wed!=null&&wed!=undefined){
                        wedd = wed.split(",");
                    }
                    if(thur!=null&&thur!=undefined){
                        thurd = thur.split(",");
                    }
                    if(fri!=null&&fri!=undefined){
                        frid = fri.split(",");
                    }
                    if(sat!=null&&sat!=undefined){
                        satd = sat.split(",");
                    }
                    if(sun!=null&&sun!=undefined){
                        sund = sun.split(",");
                    }
                    if(mond!=null){
                        for(var i=0;i<mond.length;i++){
                            parent.jQuery("input:checkbox[name='mond'][value='"+mond[i]+"']").prop("checked","checked");
                        }
                    }
                    if(tuesd!=null){
                        for(var i=0;i<tuesd.length;i++){
                            parent.jQuery("input:checkbox[name='tuesd'][value='"+tuesd[i]+"']").prop("checked","checked");
                        }
                    }
                    if(wedd!=null){
                        for(var i=0;i<wedd.length;i++){
                            parent.jQuery("input:checkbox[name='wedd'][value='"+wedd[i]+"']").attr("checked","checked");
                        }
                    }
                    if(thurd!=null){
                        for(var i=0;i<thurd.length;i++){
                            parent.jQuery("input:checkbox[name='thurd'][value='"+thurd[i]+"']").prop("checked","checked");
                        }
                    }
                    if(frid!=null){
                        for(var i=0;i<frid.length;i++){
                            parent.jQuery("input:checkbox[name='frid'][value='"+frid[i]+"']").prop("checked","checked");
                        }
                    }
                    if(satd!=null){
                        for(var i=0;i<satd.length;i++){
                            parent.jQuery("input:checkbox:checkbox[name='satd'][value='"+satd[i]+"']").prop("checked","checked");
                        }
                    }
                    if(sund!=null){
                        for(var i=0;i<sund.length;i++){
                            parent.jQuery("input:checkbox[name='sund'][value='"+sund[i]+"']").prop("checked","checked");
                        }
                    }
                    jQuery.each(parent.jQuery("input:checkbox"),function (element,index) {
                        if(!jQuery(this).is(':checked')){
                            jQuery(this).attr("disabled","disabled");
                        }

                    })
                    iFClose();
                }, 'json');

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
