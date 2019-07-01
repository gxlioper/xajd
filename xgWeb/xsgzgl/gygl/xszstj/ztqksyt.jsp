<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script src="js/highcharts/highcharts.js"></script>
		<style type='text/css'>
		.dorm-square {
			width: 12.7%;
			height: 120px;
			border:1px solid #999999;
			border-radius:5px;
			text-align:center;
			margin:10px 5px 10px 5px;
			float:left;
		}
		
		.dorm-top {
			background: #33CC00;
			line-height: 25px;
			color: #FFFFFF;
			border-radius: 5px;
			margin:3px;
		}
		.dorm-in {
			color: #33CC00;
			margin:13px auto 14px
		}
		.dorm-bot {
			background: #33CC00;
			line-height: 25px;
			color: #FFFFFF;
			border-radius: 12px;
			width:25px;
			margin:1px auto
		}
		.dorm-top-blu {
			background: #0099FF;
			line-height: 25px;
			color: #FFFFFF;
			border-radius: 5px;
			margin:3px;
		}
		.dorm-in-blu {
			color: #0099FF;
			margin:13px auto 14px
		}
		.dorm-bot-blu {
			background: #0099FF;
			line-height: 25px;
			color: #FFFFFF;
			border-radius: 12px;
			width:25px;
			margin:1px auto
		}
		.dorm-title{
			height:26px;
			line-height:26px;
			padding:0.5px;
			text-align:left;
			background:#E8F0FB;
			border:1px solid #B0CEE6;
		}
		
		</style>
		<script	type="text/javascript">
		
			jQuery(function () {
				var ldmc = "${ldmc}".split(",");
				
				var yrz = "${yrz}".split(",");
				var yrzs = new Array();
				for(var i=0;i<yrz.length;i++){
					yrzs.push(parseInt(yrz[i]))
				}
				

				//�մ�λ
				var kcw = "${kcw}".split(",");
				var kcws = new Array();
				for(var i=0;i<kcw.length;i++){
					kcws.push(parseInt(kcw[i]))
				}

				//��ס��
				var rzl = "${rzl}".split(",");
				var rzls = new Array();
				for(var i=0;i<rzl.length;i++){
					rzls.push(Math.round(rzl[i]*100)/100)
				}
				
				jQuery('#sslrzgk').highcharts({
			        chart: {
			            type: 'column'
			        },
			        title: {
			            text: '����¥��ס�ſ�'
			        },
			        xAxis: {
			            categories: ldmc,
			            crosshair: true
			        },
			        yAxis: {
			            min: 0,
			            title: {
			                text: '��λ��'
			            }
			        },
			        tooltip: {
			            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
			            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
			                '<td style="padding:0"><b>{point.y} </b></td></tr>',
			            footerFormat: '</table>',
			            shared: true,
			            useHTML: true
			        },
			        plotOptions: {
			            column: {
			                pointPadding: 0.2,
			                borderWidth: 0
			            }
			        },
			        series: [{
			            name: '����ס',
			            color:"#2F7ED8", 
			            data: yrzs
	
			        }, {
			            name: '�մ�λ',
			            color:"#8BBC21", 
			            data: kcws
	
			        }],
			        legend: {
			            layout: 'vertical',
			            align: 'right',
			            verticalAlign: 'top',
			            floating: true,
			            x: 10,
			            y: 10
			        },
			        credits: {
		            	text: ''
		        	}
			    });
			    jQuery('#gsslrzl').highcharts({
			        chart: {
			            type: 'column'
			        },
			        title: {
			            text: '������¥��ס��'
			        },
			        subtitle: {
			            text: ''
			        },
			        xAxis: {
			            categories: ldmc,
			            crosshair: true
			        },
			        yAxis: {
			            min: 0,
			            title: {
			                text: '%'
			            }
			        },
			        tooltip: {
			            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
			            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
			                '<td style="padding:0"><b>{point.y:.2f} </b></td></tr>',
			            footerFormat: '</table>',
			            shared: true,
			            useHTML: true
			        },
			        plotOptions: {
			            column: {
			                pointPadding: 0.2,
			                borderWidth: 0
			            }
			        },
			        series: [{
			            name: '����¥��ס��(%)',
			            color:"#2F7ED8",  
			            data: rzls
	
			        }],
			        legend: {
			            layout: 'vertical',
			            align: 'right',
			            verticalAlign: 'top',
			            floating: true,
			            x: 10,
			            y: 10
			        },
			        credits: {
		            	text: ''
		        	}
			    });
			});
		</script>
	</head>
	<body >
		<div class="formbox" >
			<h3 class="datetitle_01">
				<font style="font-weight:bold;">
					����¥�ſ�ͳ�ƣ�ס�޸ſ�ͳ��
				</font>
			</h3>
			<div class="dorm-square">
				<div class="dorm-top">
					����¥����
				</div>
				<div class="dorm-in">
					<font style="font-weight:bold;font-size:20px;">${zsgktj.sslzs }</font>
				</div>
				<div class="dorm-bot">
					<font style="font-weight:bold;">��</font>
				</div>
			</div>
			<div class="dorm-square">
				<div class="dorm-top-blu">
					��������
				</div>
				<div class="dorm-in-blu">
					<font style="font-weight:bold;font-size:20px;">${zsgktj.fjzs }</font>
				</div>
				<div class="dorm-bot-blu">
					<font style="font-weight:bold;">��</font>
				</div>
			</div>
			<div class="dorm-square">
				<div class="dorm-top">
					��λ��
				</div>
				<div class="dorm-in">
					<font style="font-weight:bold;font-size:20px;">${zsgktj.cws }</font>
				</div>
				<div class="dorm-bot">
					<font style="font-weight:bold;">��</font>
				</div>
			</div>
			<div class="dorm-square">
				<div class="dorm-top-blu">
					��ס��
				</div>
				<div class="dorm-in-blu">
					<font style="font-weight:bold;font-size:20px;">${zsgktj.rzl }</font>
				</div>
				<div class="dorm-bot-blu">
					<font style="font-weight:bold;">%</font>
				</div>
			</div>
			<div class="dorm-square">
				<div class="dorm-top">
					�մ�λ��
				</div>
				<div class="dorm-in">
					<font style="font-weight:bold;font-size:20px;">${zsgktj.kcws }</font>
				</div>
				<div class="dorm-bot">
					<font style="font-weight:bold;">��</font>
				</div>
			</div>
			<div class="dorm-square">
				<div class="dorm-top-blu">
					�ɰ�������
				</div>
				<div class="dorm-in-blu">
					<font style="font-weight:bold;font-size:20px;">${zsgktj.kapns }</font>
				</div>
				<div class="dorm-bot-blu">
					<font style="font-weight:bold;">��</font>
				</div>
			</div>
			<div class="dorm-square">
				<div class="dorm-top">
					�ɰ���Ů��
				</div>
				<div class="dorm-in">
					<font style="font-weight:bold;font-size:20px;">${zsgktj.kapnv }</font>
				</div>
				<div class="dorm-bot">
					<font style="font-weight:bold;">��</font>
				</div>
			</div>
			<div style="clear:both;"></div>	
			<div style="height:10px;">
				<font size="3px" color="#2F7ED8"><strong>����¥��ס�������</strong></font>
			</div>
			
			<hr size=3 height:2px; color=#599DEC align=center noshade></hr>
			<div id="sslrzgk" style="min-width: 310px; height: 400px; margin: 0 auto">
			</div>
			
			<div style="height:10px;">
				<font size="1px" color="#2F7ED8"><strong>������¥��ס���</strong>
				</font>
			</div>
				<hr size=3 color=#599DEC align=center noshade></hr>
			
			<div id="gsslrzl" style="min-width: 310px; height: 400px; margin: 0 auto">
			</div>
			
		</div>
	</body>
</html>

