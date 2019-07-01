/*
 * 
 * Ajax 分页查询插件
 * @author 屈朋辉
 * 日期：2013-4-8
 * 
 * 修改记录：
 * 1、2013-8-6 增加 radioselect 用于支持表格只能选择一条;
 * 
 * 
 */

(function($) {

	$.dataGrid = $.dataGrid || {};
	var gridConfig = {};
	$.extend($.dataGrid,{
		defaultConfig : {
			className:"dateline",
			width:"100%",
			sortTitle:"三角向上的是升序，向下的是降序",
			multiselect:true,
			radioselect:false,
			uselastrow:false,
			usercols:null,
			uselastid:"",
			useurl:"",
			selectColor:"#CBE4F8",
			height:"auto",
			pager:"pager",
			usedefined :false,//自定义表头，需要在页面自己写thead,注意，会失去自动排序的功能，因为没有直接写在页面没有绑定排序事件，主要用于复杂统计查询
			rowNum:10,
			pageList:[10,20,50,100,200]
		},loadDataGrid : function(setting) {
			//默认配置
			var config = $.dataGrid.defaultConfig;
			//参数集
			var params = $.extend(config, setting || {});
			
			var ajaxParams = {
				"pages.pageSize":config["rowNum"],
				"pages.currentPage":1,
				"pages.sortName":config["sortname"],
				"pages.sortOrder":config["sortorder"]
			};
			
			//dataGrid 表格
			var table = $("#"+config["id"]);
			//表格样式 
			table.addClass(config["className"]);
			table.attr("width",config["width"]);
			
			//列表配置
			var colList = params["colList"];
			//
			var initThead = function(){

				var thead = $("<thead></thead>");
				var tr = $("<tr class='nowrap'></tr>");
				var cols = 0;
				
				//支持多选
				if(config["multiselect"]){
					var checkbox = $("<input type='checkbox' name='grid_selectAll'/>");
					var td = $("<td width='2%'></td>");
					td.append(checkbox);
					tr.append(td);

					var selectAll = function(){
						//[982] 张昌路
						//取消所有不可选中的checkbox背景色
						$("input[name=grid_key]").each(function(){
							var isD=$(this).attr("disabled");
							if(isD){
								$(this).parents("tr").css('background','');
								$(this).parents("tr").attr("bgselect","0");
							}
						});
						
						var checked = $(this).prop("checked");
						if (checked){
							$("input[name=grid_key]:not(:checked)").each(function(){
								//[982] 张昌路
								//如果为非可选择checkbox 不做操作
								var isD=$(this).attr("disabled");
								if(!isD){
									$(this).parent().click();
								}
							});
						} else {
							$("input[name=grid_key]:checked").parent().click();
						/*	$("input[name=grid_key]:not(:checked)").each(function(){
								//扩展支持如果背景色为选中则认为已经选中
								var bg=$(this).parents("tr").attr("bgselect");
								if(bg=="1"){
									$(this).parent().click();
								}
							});*/
						}
					};
					//只能选择一行
					if (config["radioselect"]){
						checkbox.attr("disabled",true);
					} else {
						checkbox.bind("click",selectAll);
					}
					
					cols++;
				}

				var sortName = config["sortname"];
				
				$.each(colList,function(i,e){

					var td = $("<th></th>");
					
					if(e["index"] == sortName){
						var sortOrder = config["sortorder"];
						
						var aHtml = $("<a href='javascript:void(0);' name='grid_sort'></a>");
						aHtml.addClass("asc" == sortOrder ? "Sort_up" : "Sort_down");
						aHtml.append(e["label"]);
						aHtml.attr("title",config["sortTitle"]);
						td.append(aHtml);
						td.attr("sortOrder",sortOrder);
					} else {
						td.append(e["label"]);
					}

					td.attr("width",e["width"]);
					td.attr("sortName",e["index"]);
					tr.append(td);

					if(e["hidden"]){
						td.css("display","none");
					} else{
						cols++;
					}

					//表头排序
					if (e["noSort"] == null || !e["noSort"]){
						td.bind("click",function(){
							var sortOrder = $(this).attr("sortOrder");
							var sortName = $(this).attr("sortName");
							var sortClass;
							
							if (sortOrder != null && sortOrder != ""){
								sortOrder = sortOrder == "asc" ? "desc" : "asc";
								sortClass = sortOrder == "asc" ? "Sort_up" : "Sort_down";
							} else {
								sortOrder = "asc";
								sortClass = sortOrder == "asc" ? "Sort_up" : "Sort_down";
							}
	
							var sortHtml = $("<a href='javascript:void(0);' name='grid_sort'></a>");
							sortHtml.attr("class",sortClass);
							$('a[name=grid_sort]').parent().html($('a[name=grid_sort]').html());
							$(this).wrapInner(sortHtml);
							$(this).attr("sortOrder",sortOrder);
							ajaxParams["pages.sortOrder"] = sortOrder;
							ajaxParams["pages.sortName"] = sortName;
							initTbody();
						});
					}
				});
				config["cols"] = cols;
				thead.append(tr);
				table.find("thead").remove();
				table.append(thead);
			},initTbody = function(){

				var url = config["url"];
				
				var tbody = $("<tbody></tbody>");
				var ajaxOption = $.extend(ajaxParams, config["params"] || {});
				
				$.ajaxSetup({
					 contentType:"application/x-www-form-urlencoded;charset=utf-8"
				});

				var tips = loading();	
				tips.show(true);
				//$.dialog.tips('数据加载中...',600,'loading.gif');
				$.post(url,ajaxOption,function(data){
					var rows = [];
					
					$.each(data,function(i,e){
						var tr = $("<tr></tr>");
						var rowObject = {};
						$.each(colList,function(c,v){
							rowObject[v["name"]] = e[v["name"]];
						});
						if (config["multiselect"]){
							var td = $("<td style='width:12px;'></td>");
							var checkbox = $("<input type='checkbox' name='grid_key'/>");
							//[982] 张昌路
							//设置不可选checkbox
							//checkboxFormatter不可与selectFormatter格式参数同时使用
							//checkboxFormatter优先级大于selectFormatter
							var checkboxFormatter=config["checkboxFormatter"];
							if(checkboxFormatter){
								checkboxFormatter = eval(checkboxFormatter);
								var isCanSelect=checkboxFormatter(rowObject);
								if(!isCanSelect){
									checkbox=$("<input type='checkbox' name='grid_key' disabled='disabled'/>");
								}
							}else{
								//[982] 张昌路
								//增加格式化显示checkbox
								//方法请返回具体html
								var selectFormatter=config["selectFormatter"];
								if(selectFormatter){
									selectFormatter = eval(selectFormatter);
									var selectHtml=selectFormatter(rowObject);
									checkbox=selectHtml;
								}
							}
							td.append(checkbox);
							tr.append(td);
						}
						$.each(colList,function(c,v){
							var text = e[v["name"]];

							if (v["key"]){
								$('input[name=grid_key]').val(text);
								rowObject["grid_row_key"] = text;
							}

							var td = $("<td style='word-break:break-all;'></td>");
							var formatter = v["formatter"];

							if (formatter != null){
								formatter = eval(formatter);
								var html = formatter(text,rowObject);
								td.append(html);
							} else {
								td.append(text);
							}
							tr.append(td);
							if (v["hidden"]){
								td.css("display","none");
							}
						});
						
						rows.push(rowObject);
						tr.attr("rowindex",i);
						
						if (config["dblclick"] != null && jQuery.isFunction(config["dblclick"])){
							var rowClick = config["dblclick"];
							
							$(tr).bind("dblclick",function(){
								rowClick(rowObject);
							});
						}
						tbody.append(tr);

						if (i == data.length  - 1){
							var total = data[i]["total"];
							var pageNum = (data[0]["rowindex"]-1)/config["rowNum"]+1;
							var temp = data[i]["total"]/config["rowNum"];
							var pageCont;
							if (data[i]["total"]%config["rowNum"] == 0 && temp != 0){
								pageCont = temp;
							} else {
								pageCont = temp == 0 ? temp : parseInt(temp)+1;
							}
							
							
							$('#rowConut').text(total);
							$('#pageNum').val(pageNum);
							$('#pageCount').text(pageCont);
							$('#numList').val(config["rowNum"]);
						}
					});

					$("tbody",table).remove();
					if(data != null && data.length > 0 && config['uselastrow']){
						var dataMap = null;
						jQuery.ajax({
							type:'post',
							url:config["useurl"],
							dataType:'json',
							contentType:"application/x-www-form-urlencoded; charset=UTF-8",
							data:config["params"],
							async: false,
							success:function(result){
							    dataMap = result;
							}
							
						});
						for(var key in dataMap) {  
							jQuery("#"+config['uselastid']).find("td[name='"+key+"']").text(dataMap[key]);
						}  
						tbody.append(jQuery("#"+config['uselastid']).html());
					}
					table.append(tbody);
					
					if (data == null || data.length == 0){
						if(config["usercols"] != null){
							config["cols"] = config["usercols"];
						}
						var td = $("<td style='text-align:center;'></td>");
							td.attr("colspan",config["cols"]);
							td.text("未找到任何记录！");
						var tr = $("<tr></tr>");
							tr.append(td);
						tbody.append(tr);
						
						$('#rowConut').text(0);
						$('#pageNum').val(0);
						$('#pageCount').text(0);
						
					} else {
						$('tr',tbody).bind("click",function(){
							var selectRow = $(this).attr('selectRow');
							if (selectRow == 'true'){
								$(this).attr('selectRow','false');
								$(this).css('background','');

								if (config["multiselect"]){
									$('input[name=grid_key]',$(this)).attr('checked',false);
								}
							} else {
								if (config["radioselect"]){
									$("tr",tbody).attr("selectRow",false);
									$("tr",tbody).css("background",'');
									$('input:checked',tbody).attr('checked',false);
								}
								//[982] 张昌路
								//如果使用扩展设置不可用
								if(config["checkboxFormatter"]){
									var isDis=$('input[name=grid_key]',$(this)).attr("disabled");
									if(!isDis){
										$(this).attr('selectRow','true');
										$('input[name=grid_key]',$(this)).attr('checked',true);
										$(this).css('background',config["selectColor"]);
									}else{
										//反选颜色设置
										var bgselect=$(this).attr('bgselect');
										if(!bgselect||bgselect=="0"){
											$(this).css('background',config["selectColor"]);
											$(this).attr("bgselect","1");
										}else{
											$(this).css('background','');
											$(this).attr("bgselect","0");
										}
									}
								}else{
									$(this).attr('selectRow','true');
									if (config["multiselect"]){
										$('input[name=grid_key]',$(this)).attr('checked',true);
									}
									$(this).css('background',config["selectColor"]);
								}
							}
						});
					}
					
					gridConfig[config["id"]]["rows"] = rows;
					
					if (config["callBack"]){
						config["callBack"].call();
					}
					try{ // 捕获异常：SCRIPT5007: 无法获取属性“style”的值: 对象为 null 或未定义
					tips.hide();
					}catch(e){
					}
					//lhgdialog.list["Tips"].hide();
				},"json");
				
				
			},initPager = function(){

				var pager = $('#'+config["pager"]);
				
				if (!pager){
					return ;
				}
				
				
				var pagerHtml = "";
					pagerHtml +="<div class=\"pageleft\">";
					pagerHtml +="<p class=\"pagenum\"><span style='display:none'>第";
					pagerHtml +="<input type='text' id='pageNum' value='1' style='text-align:center;width:20px;'/>";
					pagerHtml +="/<span class='red' id='pageCount'></span>页，</span>每页显示";
					pagerHtml +="<select name='numList' id='numList'>";

					for (var i = 0 ,n = config["pageList"].length; i < n ; i++){
						pagerHtml +="<option value='"+config["pageList"][i]+"'>";
						pagerHtml +=config["pageList"][i];
						pagerHtml +="</option>";
					}
					
					pagerHtml +="</select>";
					pagerHtml +="条 /  共<span class='red' id='rowConut'>0</span>";
					pagerHtml +="条记录 </p>";
					pagerHtml +="</div>";

					pagerHtml +="<div class=\"pageright\">";
					pagerHtml +="<div class=\"paging\"> ";
					pagerHtml +="<a id=\"first\" class=\"first pointer\" title=\"首页\">首&nbsp;页</a>&nbsp;";
					pagerHtml +="<a id=\"back\"  class=\"prev pointer\" title=\"上一页\">上一页</a>&nbsp;";
					pagerHtml +="<a id=\"next\" class=\"next pointer\" title=\"下一页\">下一页</a>&nbsp;";
					pagerHtml +="<a id=\"last\" class=\"last pointer\" title=\"末页\">末&nbsp;页</a>";
					pagerHtml +="</div></div>";

					pager.addClass("pagination");
					pager.html(pagerHtml);

					$('#numList').val(config["rowNum"]);

					$('#numList').bind("change",function(){
						ajaxParams["pages.pageSize"] = $(this).val();
						ajaxParams["pages.currentPage"]=1,
						config["rowNum"] = $(this).val();
						initTbody();	
					});

					var toFirst = function(){

						var currentPage = parseInt($("#pageNum").val());

						if (currentPage != 1){
							ajaxParams["pages.currentPage"] = 1;
							initTbody();
						}
						
					},toNext = function(){
						var pageCount = parseInt($.trim($('#pageCount').text()));
						var currentPage = parseInt($("#pageNum").val());

						if (pageCount != currentPage){
							ajaxParams["pages.currentPage"] = currentPage+1;
							initTbody();
						}
					},toBack = function(){
						var currentPage = parseInt($("#pageNum").val());

						if (currentPage != 1 ){
							ajaxParams["pages.currentPage"] = currentPage - 1;
							initTbody();
						}
					},toLast = function(){
						var pageCount = parseInt($.trim($('#pageCount').text()));
						var currentPage = parseInt($("#pageNum").val());

						if (pageCount != currentPage){
							ajaxParams["pages.currentPage"] = pageCount;
							initTbody();
						}
					},toPage = function(){
						var pageNum = parseInt($(this).val());
						var pageCount = parseInt($.trim($('#pageCount').text()));
						
						if (/[1-9]+[\d]{0,}/.test(pageNum) && pageNum < pageCount+1 ){
							ajaxParams["pages.currentPage"] = pageNum;
							initTbody();
						}
					}

					$('#first').bind("click",toFirst);
					$('#next').bind("click",toNext);
					$('#back').bind("click",toBack);
					$('#last').bind("click",toLast);
					$('#pageNum').bind("change",toPage);
			},init = function(){
				initPager();
				//自定义表头，需要在页面自己写thead,注意，会失去自动排序的功能，因为没有直接写在页面没有绑定排序事件，主要用于复杂统计查询
				if(!config["usedefined"] || config["usedefined"] == "" || config["usedefined"] == null || config["usedefined"] == "undefined"){
					initThead();
				}
				initTbody(config["params"]);
			},reset = function(){
				initTbody(config["params"]);
			}

			if (config["init"]){
				init();
			} else {
				reset();
			}
		}
	});
	
	$.fn.initGrid = function(setting){
		var id = $(this).attr("id");
		setting["id"] = id;
		gridConfig[id] = setting;
		setting["init"] = true;
		$.dataGrid.loadDataGrid(setting);
	}

	$.fn.reloadGrid = function(params){
		var id = $(this).attr("id");
		var config = gridConfig[id];
		config["params"] = params || config["params"];
		config["rowNum"] = $('#numList').val();
		config["init"] = false;
		//gridConfig[id] = config;
		$.dataGrid.loadDataGrid(config);
	}
	
	$.fn.getSeletRow = function(){
		var rows = [];
		var _self = $(this);
		var selectRow = $("tr[selectRow=true]",_self);
		
		$.each(selectRow,function(i,e){
			rows.push(gridConfig[_self.attr("id")]["rows"][$(e).attr("rowindex")]);
		});
		return rows;
	}
	
	$.fn.getSeletIds = function(){
		var ids = [];
		var _self = $(this);
		var selectRow = $("tr[selectRow=true]",_self);
		
		$.each(selectRow,function(i,e){
			ids.push(gridConfig[_self.attr("id")]["rows"][$(e).attr("rowindex")]["grid_row_key"]);
		});
		return ids;
	}
	/**
	 * 获得所有行
	 * @author 张昌路[982]
	 */
	$.fn.getSeletAllRow = function(){
		var rows = [];
		var _self = $(this);
		var selectRow = $("tr[rowindex]",_self);
		
		$.each(selectRow,function(i,e){
			rows.push(gridConfig[_self.attr("id")]["rows"][$(e).attr("rowindex")]);
		});
		return rows;
	}
	/**
	 * 设置不可选择（disabled）
	 * key:   判断字段
	 * value: 判断值
	 * 当key字段值为value时此行设置为不可选择
	 * @author [982] 张昌路
	 */
	$.fn.unSelect=function(key,value){
		var selectRow = $(this).getSeletAllRow();
		jQuery.each(selectRow,function(i,e){
			var v=selectRow[i][key];
			if(v==value){
				jQuery(jQuery("tr[rowindex]")[i]).unbind();
				jQuery(jQuery("input[name='grid_key']")[i]).attr("disabled","disabled");
			}
		});
	}
	
	/**
	 *获取查询总条数
	 * @author [1036] 张小彬
	 */
	$.fn.getRowCount=function(){
		
		return $.trim($('#rowConut').text());
		
	}
	
})(jQuery);