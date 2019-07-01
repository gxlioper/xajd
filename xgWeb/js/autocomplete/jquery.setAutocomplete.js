/*		
 * SetAutocomplete		
 *    智能模糊查询		
 * Copyright (c) 2013 qilm		
 *		
 * params设置：		
 *   dataTable (String): 		         表/视图名
 *   dataField (String): 	                    取得的名称
 *   dataFieldKey (String): 		取得的KEY字段
 *   dataFieldKeyID (String): 	 	存放返回KEY的ID控件
 *   url (String): 		
 *   minChars (Number):	
 *   width (Number): 	
 *   max (Number):		
 *   mustMatch (Booolean):	
 *   multiple (Boolean): 	
 *   multipleSeparator (String):	
 *   scroll (Boolean):		
 *   scrollHeight (Number):自动完成提示的卷轴高度用像素大小表示 Default: 180 		
 *   formatItem (Function):		
 *   formatResult (Function):	
 *   scroll (Boolean):		
 * Revision: 2013-8-15 1.0.0		
 */
(function($) {
	$.fn.extend({
		setAutocomplete :function (option){
			var defaultConfig = {
					dataTable:"",
					dataField:"",
					dataFieldKey:"",
					dataFieldKeyId:"",
					sqlTj:"", // js方法名，用于根据页面元素动态拼接个性化sql条件
					url:"xtwhOther.do?method=getAutocomplete&r="+Math.random(),
					minChars: 0,
					delay: 400,
					matchCase: false,
					matchSubset: true,
					matchContains: true,
					cacheLength: 10,
					max: 100,
					mustMatch: false,
					extraParams: {},
					selectFirst: true,
					formatMatch: null,
					autoFill: false,
					width: 0,
					multiple: false,
					multipleSeparator: ", ",
					highlight: function(value, term) {
						return value.replace(new RegExp("(?![^&;]+;)(?!<[^<>]*)(" + term.replace(/([\^\$\(\)\[\]\{\}\*\.\+\?\|\\])/gi, "\\$1") + ")(?![^<>]*>)(?![^&;]+;)", "gi"), "<strong>$1</strong>");
					},
				    scroll: true,
				    scrollHeight: 250
					
			};
			$.extend(defaultConfig,option || {});
			var dataField = defaultConfig["dataField"];
			var dataFieldKey = defaultConfig["dataFieldKey"];
			var dataFieldKeyId = defaultConfig["dataFieldKeyId"];
//			var params = "dataTable="+defaultConfig["dataTable"]+"&dataField="+defaultConfig["dataField"];
			var params = {};
			params["dataTable"] = defaultConfig["dataTable"];
			params["dataField"] = defaultConfig["dataField"];
			// js方法名，用于根据页面元素动态拼接个性化sql条件
			var sqlTj = defaultConfig["sqlTj"];
			if (sqlTj!=null&& sqlTj!=""){
				params["sqlTj"] = sqlTj();
			}
			if (dataFieldKey!=null&& dataFieldKey!=""){
//				params +="&dataFieldKey="+defaultConfig["dataFieldKey"];
				params["dataFieldKey"] = defaultConfig["dataFieldKey"];
			}
			if(dataFieldKeyId!=null&& dataFieldKeyId!=""){
					jQuery($(this)).blur(function(){
						params["param"] = jQuery($(this)).val();
						jQuery.ajax({
							type:'post',
							url:defaultConfig["url"],
							contentType:"application/x-www-form-urlencoded; charset=UTF-8",
//							data:params+'&param='+jQuery($(this)).val(),
							data:params,
							async:false,
							success:function(data){
			 				if(data!=null&&data!=""){
			 					var datas = eval('('+data+')');
			 					if(datas!=null&&datas!=""&&datas.length!=0){
				 					jQuery($("#"+dataFieldKeyId)).val(datas[0][dataFieldKey]);
			 					}else{
			 						jQuery($("#"+dataFieldKeyId)).val("");
			 					}
			 				}else{
		 						jQuery($("#"+dataFieldKeyId)).val("");
		 					}
		
			 				var dataVal =jQuery("#"+dataField).val();
			 				if(dataVal=null||dataVal==""){
			 					jQuery($("#"+dataFieldKeyId)).val("");
			 				}
						}
					});
				});
			}
			var id = $(this).attr("id");
			  $.ajax({
					type:'post',
					url:defaultConfig["url"],
					data:params,
					dataType:"json",
					success:function(data){
		 				if(data!=null && data!=""){
			    			$("#"+id).autocomplete(data, {
								minChars: defaultConfig["minChars"],
								delay: defaultConfig["delay"],
								matchCase: defaultConfig["matchCase"],
								matchSubset: defaultConfig["matchSubset"],
								matchContains: defaultConfig["matchContains"],
								cacheLength: defaultConfig["cacheLength"],
								max: defaultConfig["max"],
								mustMatch: defaultConfig["mustMatch"],
								extraParams: defaultConfig["extraParams"],
								selectFirst: defaultConfig["selectFirst"],
								formatMatch: defaultConfig["formatMatch"],
								autoFill: defaultConfig["autoFill"],
								width: defaultConfig["width"],
								multiple: defaultConfig["multiple"],
								multipleSeparator: defaultConfig["multipleSeparator"],
								highlight: defaultConfig["highlight"],
							    scroll: defaultConfig["scroll"],
							    scrollHeight: defaultConfig["scrollHeight"],
			    				formatItem: function(row, i, max) {
			    					return row[dataField];
			    				},
			    				formatResult: function(row) {
			    					return row[dataField];
			    				}
			    			}).result(function(event, data2, formatted) {
				    			if(data2){
					    			if(dataFieldKeyId!=null&& dataFieldKeyId!=""){
			    						jQuery($("#"+dataFieldKeyId)).val(data2[dataFieldKey]);
					    			}
				    			}
			    			});	
	 				}
		 				
				}
			});
		}
	});
})(jQuery);