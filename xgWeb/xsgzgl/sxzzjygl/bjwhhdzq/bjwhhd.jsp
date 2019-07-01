<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="xsgzgl/sxzzjygl/ztbhgl/ztbhsh/js/ztbhSh.js"></script>
	<script type="text/javascript" src="xsgzgl/xstgl/comm/js/comm.js"></script>
	<script type="text/javascript">

        var colList = [
            { label : 'key', name : 'jgid', index : 'jgid',key : true, hidden : true },
            { label : '活动名称', name : 'hdmcbjmc', index : 'hdmcbjmc', width : '60%',formatter: hdLink},
            { label : '活动日期', name : 'hdrq', index : 'hdrq', width : '40%' },
        ];

        var gridSetting = {
            pager:"pager",
            url:"bjwhhdzq_bjwh.do?method=getList&type=query",
            colList:colList,
            sortname: "hdrq",
            sortorder: "asc",
            radioselect:false,
            multiselect:false,
            usedefined:true
        }

        jQuery(function(){
            var map = getSuperSearch();
            map["hdlx"]="ztbh";
            gridSetting["params"] = map;
            jQuery("#dataTable").initGrid(gridSetting);
        });

        //活动查看
        function hdLink(cellValue, rowObject) {
            return "<a href='javascript:void(0);' class='name' onclick='View(\""
                + rowObject['jgid'] + "\");'>" + cellValue
                + "</a>";
        }


        function View(jgid) {
            var hdlx = jQuery("#hdlx").val();
            if(hdlx == "ztbh")
			{
                showDialog("活动信息", 900, 450, "ztbhgl_ztbhjg.do?method=getHdInfo&jgid=" + jgid );
			}
			else{
                showDialog("活动信息", 900, 450, "bjhdgl_bjhdjg.do?method=getHdInfo&jgid=" + jgid );
			}

        }


        function selectTab(obj, hdlx) {
            jQuery("#hdlx").val(hdlx);
            if (hdlx == "ztbh") {
                var map = getSuperSearch();
                map["hdlx"]="ztbh";
                gridSetting["params"] = map;
            } else {
                var map = getSuperSearch();
                map["hdlx"]="bjhd";
                gridSetting["params"] = map;
            }
            jQuery("#dataTable").initGrid(gridSetting);
            jQuery(".ha").removeClass("ha");
            jQuery(obj).parent().addClass("ha");
        }

        function searchRs() {
            var map = getSuperSearch();
            var hdlx = jQuery("#hdlx").val();
            if (null!=hdlx&&hdlx != "") {
                map["hdlx"] = hdlx;
            }else{
                map["hdlx"] = "bjhd";
            }
            jQuery("#dataTable").reloadGrid(map);
        }


	</script>
</head>

<body>
<div class="tab_cur">
	<p class="location">
		<em>您的当前位置：</em><a>${title }</a>
	</p>
</div>
<html:form action="/ztbhgl_ztbhsh">
	<input type="hidden" id="hdlx" value="ztbh"/>
	<%@ include file="/comm/hiddenValue.jsp"%>
	<div class="toolbox">
		<!-- 过滤条件 -->
		<%@ include file="/comm/search/superSearchArea.jsp"%>
		<!-- 过滤条件 end-->
		<div class="comp_title" id="comp_title">
			<ul style="width:90%">
				<li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'ztbh');"><span>主题班会</span></a></li>
				<li><a href="javascript:void(0);" onclick="selectTab(this,'bjhd');"><span>班级活动</span></a></li>
			</ul>
		</div>
	</div>
</html:form>
<div class="main_box">
	<div class="con_overlfow">
		<table id="dataTable" ></table>
		<div id="pager"></div>
	</div>
</div>
</body>
</html>
