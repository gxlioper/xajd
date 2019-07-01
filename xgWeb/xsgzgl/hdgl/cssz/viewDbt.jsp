<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/style.css" rel="stylesheet">
		<%@ include file="/syscommon/jquery-1.11.1_migrate.ini"%>
		<script type="text/javascript" src="js/echarts/echarts.min.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var dom = document.getElementById("container");
				var myChart = echarts.init(dom);
				var app = {};
				option = null;
				app.title = '4个100完成状态';

                //取数据
                var xh = jQuery("#xh").val();
                var url = "dekt_dsglsq.do?method=getviewWct";
                var data = null;
                var para = {xh:jQuery("#xh").val()};
                jQuery.ajax({
                    type:'post',
                    url:url,
                    dataType:'json',
                    contentType:"application/x-www-form-urlencoded; charset=UTF-8",
                    data:para,
                    async: false,
                    success:function(result){

                        var ds =result['ds'];
                        var js =result['js'];
                        var jz =result['jz'];
                        var hd =result['hd'];

                        option = {
                            title: {
                                text: '4个100完成状态图',
                                textStyle:{
                                    //文字颜色
                                    color:'#000',
                                    //字体风格,'normal','italic','oblique'
                                    fontStyle:'normal',
                                    //字体粗细 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
                                    fontWeight:'bold',
                                    //字体系列
                                    fontFamily:'sans-serif',
                                    //字体大小
                                    fontSize:18
                                },
                                left:'center'
                            },
                            tooltip: {
                                trigger: 'axis',
                                axisPointer: {
                                    type: 'shadow'
                                }
                            },
                            grid: {
                                left: '3%',
                                right: '4%',
                                bottom: '3%',
                                containLabel: true
                            },
                            xAxis: {
                                type: 'value',
                                min: 0,
                                max: 100,
                                interval:10
                            },
                            yAxis: {
                                type: 'category',
                                data: ['100本书','100位老师','100场讲座','100场活动']
                            },
                            series: [
                                {
                                    name: '完成数',
                                    type: 'bar',
                                    data: [ds, js, jz, hd],
                                    barWidth : 30,
                                    //配置样式
                                    itemStyle: {
                                        //通常情况下
                                        normal:{
                                            label:{show: true, position: 'inside'},
                                            //每个柱子的颜色即为colorList数组里的每一项，如果柱子数目多于colorList的长度，则柱子颜色循环使用该数组
                                            color: function (params){
                                                var colorList = ['#3498DB','#EFE42A','#64BD3D','#EE9201'];
                                                return colorList[params.dataIndex];
                                            }
                                        },
                                        //鼠标悬停时
                                        emphasis: {
                                            shadowBlur: 10,
                                            shadowOffsetX: 0,
                                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                                        }
                                    },
                                }
                            ]
                        };
                        if (option && typeof option === "object") {
                            myChart.setOption(option, true);
                        }
                    }
                });
			});
		</script>
	</head>
	<body>
		<div class="col-md-10 col-sm-10 padding_r0">
				<div class="panel panel-default index_list margin_t15">
					<input type="hidden" id="xh" value="${xh}" />
					<div class="panel-body">
					<div class="row">
						<div class="col-lg-12">
							<div id="container" style="height:325px;border:1px solid #ccc;padding:10px;"></div>
						</div>
					</div>
					</div>
				</div>
		</div>
	</body>
</html>

