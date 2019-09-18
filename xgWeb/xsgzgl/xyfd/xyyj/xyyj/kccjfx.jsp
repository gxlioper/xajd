<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
	<script type="text/javascript" src="xsgzgl/xyfd/xyyj/xyyj/js/xyyj.js"></script>
	<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
	<script type="text/javascript" src="xsgzgl/xyfd/js/highcharts.js"></script>

	<script type="text/javascript">
        jQuery(function(){
            search();
        })
        function search_enter(e) {
            if(e.keyCode == 13){
                search();
            }
        }
        function search() {
            var kcsbm = jQuery("#kcsbm").val();
            var kcmc = jQuery("#kcmc").val();
            jQuery.post(
                "xyfd_xyyj.do?method=kccjfx&type=query",
				{
				    kcsbm : kcsbm,
                    kcmc : kcmc,
                    pageSize : jQuery("#pageSize").val(),
                    currentPage : jQuery("#pageNum").val()
                },
                function(data) {
                    jQuery("#total").empty().append(data[0]["total"]);
                    jQuery("#currentPage").empty().append(data[0]["pages"]["currentPage"]);
                    jQuery("#pageNum").val(data[0]["pages"]["currentPage"]);
					var html = "";
                    html += "<tr><th width='60%'>课程名称</th><th width='10%'>平均分</th><th width='10%'>不及格人数</th><th width='10%'>优良人数</th>" +
						"<th width='10%'>操作</th></tr>";
					var size = data[0]["resultList"].length;
					for(var i=0;i<size;i++){
                        html += "<tr><td style='text-align: center'>" + data[0]["resultList"][i]["kcmc"] + "</td><td style='text-align: center'>" + data[0]["resultList"][i]["pjf"] +
							"</td><td style='text-align: center'>" + data[0]["resultList"][i]["bjg"] + "</td><td style='text-align: center'>" + data[0]["resultList"][i]["yl"] +
							"</td><td style='text-align: center'><a href='#' class='button' onclick=kccjqs('" + data[0]["resultList"][i]["kcsbm"] + "','" + data[0]["resultList"][i]["kcmc"] + "')>选择</a></td></tr>";
					}
					jQuery("#dataTable").empty().append(html);
                },
				'json'
			);
        }
        
        function kccjqs(kcsbm,kcmc) {
			jQuery("#div_kc").empty().append("历年课程成绩趋势 【<font>" + kcmc + "(" + kcsbm + ")</font>】");
            jQuery.post(
                "xyfd_xyyj.do?method=kccjqs",
                {
                    kcsbm : kcsbm
                },
                function(data) {
                    var title = {
                        text: '课程成绩趋势'
                    };
                    var xAxis = {
                        categories: data[0]["nj"]
                    };
                    var yAxis = {
                        title: {
                            text: ''
                        },
                        plotLines: [{
                            value: 0,
                            width: 1,
                            color: '#808080'
                        }]
                    };
                    var tooltip = {
                        valueSuffix: ''
                    }
                    var legend = {
                        layout: 'vertical',
                        align: 'right',
                        verticalAlign: 'middle',
                        borderWidth: 0
                    };
                    var series =  [
                        {
                            name: '平均分',
                            data: data[0]["pjf"]
                        },
                        {
                            name: '不及格人数',
                            data: data[0]["bjg"]
                        },
                        {
                            name: '优良人数',
                            data: data[0]["yl"]
                        }
                    ];
                    var json = {};
                    json.title = title;
                    json.xAxis = xAxis;
                    json.yAxis = yAxis;
                    json.tooltip = tooltip;
                    json.legend = legend;
                    json.series = series;
                    jQuery('#container').highcharts(json);
                },
                'json'
            );
        }

	</script>
</head>

<body>

<html:form action="/xyfd_xyyj">
	<%@ include file="/comm/hiddenValue.jsp"%>
<div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
	<table style="width: 80%;margin: 0 auto;">
		<tbody>
			<tr>
				<td colspan="5" style="width: 70%;text-align: center">
					课程编号<input type="text" id="kcsbm" name="kcsbm" onkeypress="search_enter(event);"/>&nbsp;&nbsp;
					课程名称<input type="text" id="kcmc" name="kcmc" onkeypress="search_enter(event);"/>
					<button onclick="search();return false;">查询</button>
				</td>
			</tr>
			<tr>
				<td colspan="5" style="width: 60%">
					<div class="con_overlfow" style="width: 100%">
						<table id="dataTable" style="width: 100%" ></table>

					</div>
					<div id="pager">
						<div style="float: right ">
							<p class="pagenum"><span>第
							<input type='hidden' id='pageNum' value='1' style='text-align:center;width:20px;'/>
								<span class='red' id='currentPage'>1</span>页，</span>每页显示11
							条 /  共<span class='red' id='total'>0</span>
							条记录
							<a id="first" class="button" onclick="toFirst()" title="首页">首&nbsp;页</a>&nbsp;
							<a id="back"  class="button" onclick="before()" title="上一页">上一页</a>&nbsp;
							<a id="next" class="button" onclick="next()" title="下一页">下一页</a>&nbsp;
							<a id="last" class="button" onclick="toLast()" title="末页">末&nbsp;页</a>
						</p>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="5" style="text-align: center">
					<div id="div_kc">历年课程成绩趋势</div>
				</td>
			</tr>
			<tr>
				<td colspan="5" style="width: 80%;text-align: center">
					<div id="container" style="width: 100%; height: 400px; margin: 0 auto"></div>
				</td>
			</tr>
		</tbody>
	</table>
</div>
</html:form>
</body>
<script type="text/javascript">

	function toFirst() {
        var currentPage = jQuery("#pageNum").val();
        var total = jQuery("#total").text();
		if(parseInt(currentPage) <= 1){ //第一页
		    return false;
		}
        jQuery("#pageNum").val(1);
        search();
    }
    function before() {
        var currentPage = jQuery("#pageNum").val();
        var total = jQuery("#total").text();
        if(parseInt(currentPage) <= 1){ //第一页
            return false;
        }
        jQuery("#pageNum").val(parseInt(currentPage) - 1);
        search();
    }

    function next() {
        var currentPage = jQuery("#pageNum").val();
        var total = jQuery("#total").text();
		if(parseInt(currentPage) >= Math.ceil(total/11)){ //最后一页
		    return false;
		}
        jQuery("#pageNum").val(parseInt(currentPage) + 1);
        search();
    }
    function toLast() {
        var currentPage = jQuery("#pageNum").val();
        var total = jQuery("#total").text();
        if(parseInt(currentPage) >= Math.ceil(total/11)){ //最后一页
            return false;
        }
        jQuery("#pageNum").val(Math.ceil(total/11));
        search();
    }


</script>
<style>
	table, td, th
	{
		border:1px solid #166480;
	}
	th
	{
		background-color: #166480;
		color:white;
	}
</style>
</html>
