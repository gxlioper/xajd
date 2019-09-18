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
            var zydm = jQuery("#zydm").val();
            var zymc = jQuery("#zymc").val();
            jQuery.post(
                "xyfd_xyyj.do?method=zycjfx&type=query",
				{
                    zydm : zydm,
                    zymc : zymc,
                    pageSize : jQuery("#pageSize").val(),
                    currentPage : jQuery("#pageNum").val()
                },
                function(data) {
                    jQuery("#total").empty().append(data[0]["total"]);
                    jQuery("#currentPage").empty().append(data[0]["pages"]["currentPage"]);
                    jQuery("#pageNum").val(data[0]["pages"]["currentPage"]);
					var html = "";
                    html += "<tr><th width='60%'>רҵ����</th><th width='10%'>������</th><th width='10%'>������</th><th width='10%'>����</th></tr>";
					var size = data[0]["resultList"].length;
					for(var i=0;i<size;i++){
                        html += "<tr><td style='text-align: center'>" + data[0]["resultList"][i]["zymc"] + "</td><td style='text-align: center'>" + data[0]["resultList"][i]["yll"] +
							"</td><td style='text-align: center'>" + data[0]["resultList"][i]["jgl"] +
							"</td><td style='text-align: center'><a href='#' class='button' onclick=zycjqs('" + data[0]["resultList"][i]["zydm"] +
							"','" + data[0]["resultList"][i]["zymc"] + "')>ѡ��</a></td></tr>";
					}
					jQuery("#dataTable").empty().append(html);


                },
				'json'
			);
        }
        
        function zycjqs(zydm,zymc) {
			jQuery("#div_kc").empty().append("רҵ�ɼ����� ��<span>" + zymc + "(" + zydm + ")</span>��");
            jQuery.post(
                "xyfd_xyyj.do?method=zycjqs",
                {
                    zydm : zydm
                },
                function(data) {
                    var title = {
                        text: 'רҵ�ɼ�����'
                    };
                    var xAxis = {
                        categories: data[0]["xq"]
                    };
                    var yAxis = {
                        title: {
                            text: ''
                        },
                        labels: {//y��̶����ֱ�ǩ
                            formatter: function () {
                                return this.value + '%';//y�����%
                            }
                        },
                        plotLines: [{
                            value: 0,
                            width: 1,
                            color: '#808080'
                        }]
                    };
                    var tooltip = {
                        valueSuffix: '%'
                    }
                    var legend = {
                        layout: 'vertical',
                        align: 'right',
                        verticalAlign: 'middle',
                        borderWidth: 0
                    };
                    var rangeSelector = {
                        selected: 1
                    };
                    var series =  [
                        {
                            name: '������',
                            data: data[0]["yll"]
                        },
                        {
                            name: '������',
                            data: data[0]["jgl"]
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
					רҵ����<input type="text" id="zydm" name="kcsbm" onkeypress="search_enter(event);"/>&nbsp;&nbsp;
					רҵ����<input type="text" id="ztmc" name="kcmc" onkeypress="search_enter(event);"/>
					<button onclick="search();return false;">��ѯ</button>
				</td>
			</tr>
			<tr>
				<td colspan="5" style="width: 60%">
					<div class="con_overlfow" style="width: 100%">
						<table id="dataTable" style="width: 100%" ></table>
					</div>
					<div id="pager">
						<div style="float: right ">
							<p class="pagenum"><span>��
							<input type='hidden' id='pageNum' value='1' style='text-align:center;width:20px;'/>
								<span class='red' id='currentPage'>1</span>ҳ��</span>ÿҳ��ʾ11
							�� /  ��<span class='red' id='total'>0</span>
							����¼
							<a id="first" class="button" onclick="toFirst()" title="��ҳ">��&nbsp;ҳ</a>&nbsp;
							<a id="back"  class="button" onclick="before()" title="��һҳ">��һҳ</a>&nbsp;
							<a id="next" class="button" onclick="next()" title="��һҳ">��һҳ</a>&nbsp;
							<a id="last" class="button" onclick="toLast()" title="ĩҳ">ĩ&nbsp;ҳ</a>
						</p>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="5" style="text-align: center">
					<div id="div_kc">����רҵ�ɼ�����</div>
				</td>
			</tr>
			<tr>
				<td colspan="5" style="width: 80%;text-align: center">
					<div id="container" style="width: 100%; height: 400px; margin: 0 auto;"></div>
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
		if(parseInt(currentPage) <= 1){ //��һҳ
		    return false;
		}
        jQuery("#pageNum").val(1);
        search();
    }
    function before() {
        var currentPage = jQuery("#pageNum").val();
        var total = jQuery("#total").text();
        if(parseInt(currentPage) <= 1){ //��һҳ
            return false;
        }
        jQuery("#pageNum").val(parseInt(currentPage) - 1);
        search();
    }

    function next() {
        var currentPage = jQuery("#pageNum").val();
        var total = jQuery("#total").text();
		if(parseInt(currentPage) >= Math.ceil(total/11)){ //���һҳ
		    return false;
		}
        jQuery("#pageNum").val(parseInt(currentPage) + 1);
        search();
    }
    function toLast() {
        var currentPage = jQuery("#pageNum").val();
        var total = jQuery("#total").text();
        if(parseInt(currentPage) >= Math.ceil(total/11)){ //���һҳ
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
