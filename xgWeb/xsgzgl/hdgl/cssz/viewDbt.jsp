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
				app.title = '4��100���״̬';

                //ȡ����
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
                                text: '4��100���״̬ͼ',
                                textStyle:{
                                    //������ɫ
                                    color:'#000',
                                    //������,'normal','italic','oblique'
                                    fontStyle:'normal',
                                    //�����ϸ 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
                                    fontWeight:'bold',
                                    //����ϵ��
                                    fontFamily:'sans-serif',
                                    //�����С
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
                                data: ['100����','100λ��ʦ','100������','100���']
                            },
                            series: [
                                {
                                    name: '�����',
                                    type: 'bar',
                                    data: [ds, js, jz, hd],
                                    barWidth : 30,
                                    //������ʽ
                                    itemStyle: {
                                        //ͨ�������
                                        normal:{
                                            label:{show: true, position: 'inside'},
                                            //ÿ�����ӵ���ɫ��ΪcolorList�������ÿһ����������Ŀ����colorList�ĳ��ȣ���������ɫѭ��ʹ�ø�����
                                            color: function (params){
                                                var colorList = ['#3498DB','#EFE42A','#64BD3D','#EE9201'];
                                                return colorList[params.dataIndex];
                                            }
                                        },
                                        //�����ͣʱ
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

