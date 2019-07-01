<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html>
<head>
    <title>教师信息列表</title>
    <%@ include file="/syscommon/head.ini"%>
    <script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
    <script type="text/javascript" src="js/check.js"></script>
    <script language="javascript" src="xsgzgl/szdw/fdypx/js/fdypxjg.js"></script>
    <script language="javascript" src="js/xsgzgl/szdw/dwwh.js"></script>
    <script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
    <script type="text/javascript">
        function initGrid(){
            var gridSetting = {
                caption:"教师信息列表",
                pager:"pager",
                url:"general_szdw.do?method=fdyxxwh&type=query",
                colList:[
                    {label:'职工号',name:'zgh', index: 'zgh',key:true,width:'10%',formatter:function(val,row){
                        return "<a class='name' href='#' onclick='viewFdyxx(\"" + val + "\")'>"+val+"</a>";
                    }},
                    {label:'姓名',name:'xm', index: 'xm',width:'8%'},
                    {label:'性别',name:'xb', index: 'xb',width:'5%'},
                    {label:'联系电话',name:'lxdh', index: 'lxdh',width:'10%'},
                    {label:'所属部门',name:'bmmc', index: 'bmmc',width:'13%'},
                    {label:'辅导员带班数',name:'fdydbs', index: 'fdydbs',width:'5%'},
                    {label:'班主任带班数',name:'bzrdbs', index: 'bzrdbs',width:'5%'},
                    {label:'用户身份',name:'yhsf', index: 'yhsf',width:'5%'},
                    {label:'系统登录次数',name:'dls', index: 'dls',width:'5%'}
                ]
            };
            gridSetting["params"]=getSuperSearch();
            jQuery("#dataTable").initGrid(gridSetting);
        };
        function searchRs(){
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        jQuery(function(){
            initGrid();
        })
        function viewFdyxx(zgh){
            var url='szdw_fdyxx.do?method=fdyxxView&zgh='+zgh;
            showDialog('', 830, 500, url);

        }
        function view(){
            var row = jQuery("#dataTable").getSeletRow();
            if(row.length == 1){
                viewFdyxx(row[0]["zgh"]);
            }else{
                showAlertDivLayer("请勾选<font color='blue'>一条记录</font>进行查看");
                return false;
            }
        }
        function configureExportData() {
            customExport("szdw_general_dwwh.do", fdyxxwhExportData);
        }

        // 导出方法
        function fdyxxwhExportData() {
            setSearchTj();//设置高级查询条件
//            var ids = jQuery("#dataTable").getSeletIds();
            var url = "szdw_dwwh.do?method=fdyxxwhExportData&dcclbh=" + "szdw_general_dwwh.do";//dcclbh,导出功能编号
            /*if(ids.length > 0){
                url += "&zghs="+ids.toString();
            }*/
            url = addSuperSearchParams(url);//设置高级查询参数
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
        function jsxxdy(){
            var ids = jQuery("#dataTable").getSeletIds();
            var len = ids.length;
            if(len == 0){
                showAlertDivLayer("请先勾选数据，再进行导出操作！");
                return false;
            }
            var url = "xsxx_hzdc.do?method=xsxxDcPrepare&zghs="+ids+"&type=tea";
            showDialog("教师信息导出", 690, 500, url);
        }
    </script>
</head>
<body>
<html:form action="/general_szdw">
    <%@ include file="/comm/hiddenValue.jsp"%>

    <div class="toolbox">
        <!-- 按钮 -->
        <div class="buttonbox">
            <ul>
                <li>
                    <a href="#" class="btn_zj"
                       onclick="createDwwhDiv('insert');return false;"> 增加 </a>
                </li>
                <li>
                    <a href="#" class="btn_xg"
                       onclick="checkDwwhUpdate();return false;"> 修改 </a>
                </li>
                <li>
                    <a href="#" class="btn_sc" onclick="deleteDwwh();return false;">
                        删除 </a>
                </li>
                <li>
                    <a href="#" class="btn_ck" onclick="view();return false;">
                        查看 </a>
                </li>
                <li>
                    <a href="#" id="btn_dr"
                       onclick="toImportData('IMPORT_N100108'); return false;"
                       class="btn_dr">导入</a>
                </li>
                <li>
                    <a href="#" class="btn_dc" onclick="configureExportData();return false;">导出</a>
                </li>
                <li>
                    <a href="#" onclick="jsxxdy();return false;" class="btn_dy">教师信息打印</a>
                </li>
            </ul>
        </div>
        <!-- 过滤条件 -->
        <%@ include file="/comm/search/superSearchArea.jsp"%>
        <!-- 过滤条件 end-->
    </div>
</html:form>

<div class="main_box">
    <h3 class=datetitle_01>
        <span>教师信息列表</span>
    </h3>
    <div class="con_overlfow">
        <table id="dataTable" ></table>
        <div id="pager"></div>
    </div>
</div>
</body>
</html>
