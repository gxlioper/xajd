
jQuery(function(){
		// 路径配置
	var man=jQuery("#man").val();
	var woman=jQuery("#woman").val();
	var fzxman=jQuery("#fzxman").val();
	var fzxwoman=jQuery("#fzxwoman").val();
	require.config({
		paths: {
			echarts: './js/echarts'
		}
	});

	// 使用
	require(
		[
			'echarts',
			'echarts/chart/bar',
			'echarts/chart/line',
			'echarts/chart/pie',
			'echarts/chart/radar'
		],
		function(ec, theme) {
			var myChart = ec.init(document.getElementById('xsrs'), theme);

			option = {
				grid: {
				zlevel: 0,
				z: 0,
				x: 80,
				y: 60,
				x2: 80,
				y2: 60,
				height: '50%',
				backgroundColor: 'rgba(0,0,0,0)',
				borderWidth: 1,
				borderColor: '#ccc',
				},
				legend: {
					 orient : 'horizontal',
			    	 y : 'bottom',
			        data:['男生','女生']
			    },
				tooltip: { // Option config. Can be overwrited by series or data
					trigger: 'axis',
					//show: true,   //default true
					showDelay: 0,
					hideDelay: 50,
					transitionDuration: 0,
					backgroundColor: 'rgba(50,50,50,0.4)',
					borderColor: 'black',
					borderRadius: 8,
					borderWidth: 2,
					padding: 10, // [5, 10, 15, 20]
					position: function(p) {
						// 位置回调
						// console.log && console.log(p);
						return [p[0] + 10, p[1] - 10];
					},
					textStyle: {
						color: 'white',
						decoration: 'none',
						fontFamily: 'Verdana, sans-serif',
						fontSize: 15,
						fontStyle: 'italic',
						fontWeight: 'bold'
					},
					formatter: function(params, ticket, callback) {
						var res = params[0].name;
						for(var i = 0, l = params.length; i < l; i++) {
							res += '<br/>' + params[i].seriesName + ' : ' + params[i].value;
						}
						setTimeout(function() {
							// 仅为了模拟异步回调
							callback(ticket, res);
						}, 1000)
						return 'loading';
					}
					//formatter: "Template formatter: <br/>{b}<br/>{a}:{c}<br/>{a1}:{c1}"
				},
				toolbox: {
					show: true,
					feature: {
					}
				},
				calculable: true,
				xAxis: {
					data: ['在校生', '非在校生']
				},
				yAxis: {
					type: 'value'
				},
				series: [
					{
						name: '男生',
						type: 'bar',
						stack: '在校生',
						barWidth : 20,
						data: [man, fzxman]
					},
					{
						name: '女生',
						type: 'bar',
						stack: '在校生',
						barWidth : 20,
						data: [woman, fzxwoman]
					}
				]
			};
			myChart.setOption(option);
			
			//各年级学生数统计
			var njxsChart = ec.init(document.getElementById('njxs'), theme);
			njxsOption = {
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		    	 orient : 'horizontal',
		    	 y : 'bottom',
		        data:njData
		    },
		    toolbox: {
		        feature : {
		            magicType : {
		                show: true, 
		                type: ['pie', 'funnel'],
		                option: {
		                    funnel: {
		                        x: '25%',
		                        width: '50%',
		                        funnelAlign: 'left',
		                        max: 1548
		                    }
		                }
		            },
		        }
		    },
		    calculable : true,
		    series : [
		        {
		            name:'年级人数',
		            type:'pie',
		            radius : '55%',
		            center: ['50%', '45%'],
		            itemStyle: 　{
						normal: {
							label: {
								show: false
							},
							labelLine: {
								show: false
							}
						},
					},
		            data:njxsJson
		        }
		    ]
			};
			njxsChart.setOption(njxsOption);
			//政治面貌分类统计
			var zzmmChart = ec.init(document.getElementById('zzmm'), theme);
			zzmmOption = {
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		    	 orient : 'vertical',
		         x : 'right',
		         y: 'center',
		        data:zmData
		    },
		    toolbox: {
		        feature : {
		            magicType : {
		                show: true, 
		                type: ['pie', 'funnel'],
		                option: {
		                    funnel: {
		                        x: '25%',
		                        width: '50%',
		                        funnelAlign: 'left',
		                        max: 1548
		                    }
		                }
		            },
		        }
		    },
		    calculable : true,
		    series : [
		        {
		            name:'政治面貌',
		            type:'pie',
		            radius :  ['35%', '55%'],
		            center: ['30%', '45%'],
		            minAngle:15,
		            itemStyle: 　{
						normal: {
							label: {
								show: false
							},
							labelLine: {
								show: false
							}
						},
					},
		            data:zzmmJson
		        }
		    ]
			};
			zzmmChart.setOption(zzmmOption);
		}
	);
		
	});
