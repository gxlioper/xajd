<%@ page language="java" contentType="text/html; charset=GBK" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini" %>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript">
        jQuery(function () {
            var gridSetting = {
                caption: "",
                pager: "pager",
                url: "general_szdw.do?method=szdwSzBzrbb&type=query",
                colList: [
                    {label: 'pk', name: 'pk', index: 'pk', width: '10%',hidden:true},
                    {label: '年级', name: 'nj', index: 'nj', width: '10%'},
                    {label: '学院', name: 'xymc', index: 'xymc', width: '10%'},
                    {label: '专业', name: 'zymc', index: 'zymc', width: '10%'},
                    {label: '行政代码', name: 'bjdm', index: 'bjdm', width: '10%',hidden:true},
                    {label: '专业班级', name: 'bjmc', index: 'bjmc', width: '10%'},
                    {label: '专业班级人数', name: 'rs', index: 'rs', width: '10%',formatter:function(cellVal,row){
                        return "<a href='#' onclick='xsxxView(\""+cellVal+"\",\""+row["bjdm"]+"\")'>"+cellVal+"</a>";
                    }},
                    {label: '班主任', name: 'bzrxm', index: 'bzrxm', width: '10%'}
                ],
                sortname: "nj",
                sortorder: "asc",
                radioselect: true
            }
            var map = getSuperSearch();
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        })

        function searchRs() {
            var map = getSuperSearch();
            jQuery("#dataTable").reloadGrid(map);
        }
        function fdybbSetting(){
            /*var isopen = jQuery("#isopen").val();
            if(isopen==null||isopen==''){
                showAlertDivLayer('基础设置未初始化，请联系管理员！');
                return false;
            }
            if ("false" == isopen){
                showAlertDivLayer("当前未开放编班，请联系管理员！");
                return false;
            }*/
			var rows = jQuery("#dataTable").getSeletRow();
            var nj="";
            var xydm="";
            var zydm="";
            var bjdm="";
            if(rows.length>1){

                alertInfo("仅可选择一个班级!");
                return false;
            }else if(rows.length==1){
                var pkArr=new Array();
                pkArr=rows[0]["pk"].split('!!luojw!!');
                nj=pkArr[0];
                xydm=pkArr[1];
                zydm=pkArr[2];
                bjdm=pkArr[3];

            }

            var url = "general_szdw.do?method=szbbSetting&fplx=bzr";
            url+="&nj="+nj;
            url+="&xydm="+xydm
            url+="&zydm="+zydm
            url+="&bjdm="+bjdm
            refreshForm(url);

        }
        // 学生信息
        function xsxxView(n,bjdm){

            if(0 == n) {
                alertError("班级人数为0，无法查看详情！");
                return false;
            }
            showDialog("学生详细信息",900,500,"general_szdw.do?method=xsxxList&bjdm="+bjdm+"&bbType=bzr");
        }
        function szdwbbExportConfig() {
            customExport("general_szdw_bzr.do", szdwbbExportData);
        }

        // 导出方法
        function szdwbbExportData() {
            setSearchTj();//设置高级查询条件
            var url = "szdw_szbb.do?method=szdwbbExportData&dcclbh=" + "general_szdw_bzr.do";//dcclbh,导出功能编号
            url = addSuperSearchParams(url);//设置高级查询参数
            jQuery("form").eq(0).attr("action", url);
            jQuery("form").eq(0).submit();
        }
	</script>
</head>

<body>
<html:form action="/ztbhgl_ztbhjg">
	<%@ include file="/comm/hiddenValue.jsp" %>
	<div class="toolbox">
		<!-- 按钮 -->
		<div class="buttonbox">
			<ul>
				<li>
					<a href="#" class="btn_sr" onclick="fdybbSetting();return false;">
						增加班主任
					</a>
				</li>
				<li><a href="#" class="btn_dc" onclick="szdwbbExportConfig();return false;">导出</a></li>
			</ul>
		</div>
		<!-- 过滤条件 -->
		<%@ include file="/comm/search/superSearchArea.jsp" %>
		<!-- 过滤条件 end-->
	</div>
</html:form>

<div class="main_box">
	<h3 class=datetitle_01>
		<span>人员&nbsp;&nbsp;</span>
	</h3>
	<div class="con_overlfow">
		<table id="dataTable"></table>
		<div id="pager"></div>
	</div>
</div>
</body>
</html>
