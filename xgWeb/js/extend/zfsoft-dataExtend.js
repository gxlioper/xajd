/**
 * 扩展信息插件
 * 作者：张小彬
 * 开发日期：2015-06-05
 * 版本：v1.0
 * 
 * 调用方式
 * --selector 
 * --DataExtendModuleID
 * jQuery(selector).dataExtend({DataExtendModuleID:'DataExtendModuleID'});
 */
;(function($){
	
	var PluginInfoPackage = {version: "v.1.0",author: "1036", dept: "XG", corp: "ZFSOFT", release: "2015-6-10"};
	
	var PluginInfoHelp = {helpMessage: ""};
	
	var CSS = {'error-input':{'background-color':'rgb(244, 212, 54)','color':'red'},'no-error-input':{'background-color': '',  'color': ''}};
	
	var g_type = ["normal","list","stuinfo","teainfo"];
	
	var el_type = ["text","textarea","radio","checkbox","select","file"];
	
	var ObtainConfigURL = "data_extend.do?method=obtainConfig";
	
	var ObtainDataURL = "data_extend.do?method=obtainData";
	
	var GetStuinfoURL = "data_extend.do?method=getStuInfo";
	
	var ServerValidationURL = "data_extend.do?method=validation";
	
	var GetSSXURL = "data_extend.do?method=getPluginData&lx=ssx";
	
	var _defaultOptions = {"naviBar": true, "actionBar": true, "edit": true, "listMax": 50, "dataType":"1","mode":"edit"};
	
	/**
	 * 构建页面
	 */
	function buildHTMLPage(tg,configDate,options){
		//创建一个存放配置的隐藏域
		_buildHiddenConfigEl(tg,configDate,options);
		//1.build top head
		if(options['naviBar']){
			_buildNavHead(tg,configDate);	
		}
		//2.build datatable
		_buildDataTable(tg,configDate,options);
		
		//3.budil action button
		if(options['actionBar']){
			_buildActionBar(tg, options);
		}
		//如果有数据就填充
		_fillDataIfPossible(tg);
		
		
	}
	
	/**
	 * 构建拓展数据
	 */
	$.fn.dataExtend = function(options){
		$.extend(_defaultOptions, options);
		_defaultOptions['extendModuleID'] = _defaultOptions['mid'];
		var $this = $(this);
		$this.attr('mid', _defaultOptions['extendModuleID']);
		$this.attr('etype', 'mcontent');
		_ajax_query_config(_defaultOptions,buildHTMLPage,$this);
		return jQuery(this);
	}
	
	/**
	 * 获取拓展数据JSON格式
	 */
	$.fn.getExtendData = function(){
		var check = _primeValidation($(this));
		if(!check){
			showAlertDivLayer('信息填写不完整！');
			return false;
		}else{
			return _obtainExtendData($(this));
		}
	}

	/**
	 * 客户端校验，不完全校验
	 * 只检验：
	 * 是否必填，长度控制，列表行数控制
	 */
	$.fn.primeExendDataValidation = function(){
		return _primeValidation($(this));
	}
	
	/**
	 * 省市县
	 */
	$.fn.ssx = function(){
		
	}
	
	/**
	 * 兼容IE6
	 */
	$.fn.bgIframe = $.fn.bgiframe = function(s) {
		// This is only for IE6
		if ( $.browser.msie && /6.0/.test(navigator.userAgent) ) {
			s = $.extend({
				top     : 'auto', // auto == .currentStyle.borderTopWidth
				left    : 'auto', // auto == .currentStyle.borderLeftWidth
				width   : 'auto', // auto == offsetWidth
				height  : 'auto', // auto == offsetHeight
				opacity : true,
				src     : 'javascript:false;'
			}, s || {});
			var prop = function(n){return n&&n.constructor==Number?n+'px':n;},
			    html = '<iframe class="bgiframe"frameborder="0"tabindex="-1"src="'+s.src+'"'+
			               'style="display:block;position:absolute;z-index:-1;'+
				               (s.opacity !== false?'filter:Alpha(Opacity=\'0\');':'')+
						       'top:'+(s.top=='auto'?'expression(((parseInt(this.parentNode.currentStyle.borderTopWidth)||0)*-1)+\'px\')':prop(s.top))+';'+
						       'left:'+(s.left=='auto'?'expression(((parseInt(this.parentNode.currentStyle.borderLeftWidth)||0)*-1)+\'px\')':prop(s.left))+';'+
						       'width:'+(s.width=='auto'?'expression(this.parentNode.offsetWidth+\'px\')':prop(s.width))+';'+
						       'height:'+(s.height=='auto'?'expression(this.parentNode.offsetHeight+\'px\')':prop(s.height))+';'+
						'"/>';
			return this.each(function() {
				if ( $('> iframe.bgiframe', this).length == 0 )
					this.insertBefore( document.createElement(html), this.firstChild );
			});
		}
		return this;
	};
	
	
	function _makeSSXSelectDiv(tg){
		if(tg.find('#_selectSsx_').length == 0){
			var div = $('<div id="_selectSsx_" style="display: none; position: absolute;background:#FFF;border:1px solid #B0CBE0;overflow:hidden;z-index:1000">');
			var divTitle = $('<div id="_selectItemAd_" style="background:#5E96DE;line-height:20px;height:20px;margin:1px;padding-left:12px">');
			var title_1 = $('<h2 id="_selectItemTitle_" style="float:left;margin:0;padding:0;font-size:12px;font-weight:bold;color:#fff;">请选择</h2>');
			var title_2 = $('<div id="_selectItemConfirm_" style="float:right;cursor:pointer;color:#fff;">【确定】</div>');
			var title_3 = $('<div id="_selectItemClose_" style="float:right;cursor:pointer;color:#fff;">【取消】</div>');
			title_3.bind('click',function(){
				div.hide();
			});
			var count = $('<div id="_selectItemCount_" style="padding:8px">');
			var sh = $('<select style="border:1px solid darkgrey;" name="_sh_" ></select>');
			var ss = $('<select style="border:1px solid darkgrey;" name="_ss_" value=""></select>');
			var xx = $('<select style="border:1px solid darkgrey;" name="_xx_" value=""></select>');
			$.post(GetSSXURL,{},function(d){
				div.data('ssx' , d);
				sh.append('<option value=""></option>');
				ss.append('<option value=""></option>');
				xx.append('<option value=""></option>');;
				$.each(d,function(i,n){
					sh.append('<option value="'+n['value']+'">'+n['treeNode']+'</option>');
				});
				
				sh.change(function(){
					ss.find('option[value!=""]').remove();
					xx.find('option[value!=""]').remove();
					var shval = $(this).val();
					if(shval == ''){
						return false;
					}
				    $.each(d,function(j,k){
				    	if(shval == k['value']){
				    		var sschild = k['childNode'];
				    		$.each(sschild,function(l,m){
				    			ss.append('<option value="'+m['value']+'">'+m['treeNode']+'</option>');
				    		});
				    		return false;
				    	}
				    });
		
				});
				
				ss.change(function(){
					xx.find('option[value!=""]').remove();
					var ssval = $(this).val();
					if(ssval == ''){
						return false;
					}
					$.each(d,function(i,n){
						var dm = n['value'];
					    $.each(n['childNode'],function(j,k){
					    	if(ssval == k['value']){
					    		var xxchild = k['childNode'];
					    		$.each(xxchild,function(l,m){
					    			xx.append('<option value="'+m['value']+'">'+m['treeNode']+'</option>');
					    		});
					    		return false;
					    	}
					    });
						
					});
				});
				
				count.append(sh).append(ss).append(xx);
				divTitle.append(title_1).append(title_2).append(title_3);
				div.append(divTitle).append(count);
				tg.append(div);
				
			},'json');
		}
		
		
		
		//<div id="selectItem" class="selectItemhidden" style="display: none; position: absolute;"> 
		//	<div id="selectItemAd" class="selectItemtit bgc_ccc"> 
		//		<h2 id="selectItemTitle" class="selectItemleft">省市县</h2> 
		//		<div id="selectItemConfirm" class="selectItemright">【确定】</div>
		//	</div> 
		//	<div id="selectItemCount" class="selectItemcont"> 
		//		<div id="selectSub"> 
		//			
		//		</div> 
		//	</div> 
		//</div>
		
	}
	
	function _makeFormView(tg){
		//var el_type = ["text","textarea","radio","checkbox","select","file"];
		tg.find('[category="edit"]').hide();
		tg.find('[etype="megdataitem"]').each(function(i){
			var $t = $(this);
			$t.hide();
			var type = $t.attr('gtype');
			var span = $('<span>');
			if(el_type[0] == type || el_type[1] ==type){
				span.text($t.val());
				$t.after(span);
			}else if(el_type[4] == type){
				span.text($t.find("option:selected").text());
				$t.after(span);
			}
		});
		tg.find('[etype="megdataitem"]').remove();
	}
	
	function _primeValidation($this){
		$this.find('[etype="megdataitem"]').css(CSS['no-error-input']);
		$this.find('#Extend_List_More_TR').css('background-color','');
		var validationResult = true;
		var megdataTables = $this.find('table[etype="megdata"]');	
		$.each(megdataTables,function(i, n){
			var group = $(n);
			var grouplx = group.attr('lx');
			if(grouplx == g_type[0]){
				var gitems = group.find('[etype="megdataitem"]');
				var dataArray = new Array(gitems.length);
				$.each(gitems,function(i, n){
					$(n).next().remove('[name="errortip"]');
					var cd = $(n).attr('cd');
					var bt = $(n).attr('bt');
					var gitemVal = $(n).val();
					if(bt == '1' && $.trim(gitemVal) == ''){
						validationResult = false;
						$(n).css(CSS['error-input']);
						$(n).after('<div name="errortip" style="color: red;">*[必填]</div>');
					}
				});
			}else if(grouplx == g_type[1]){
				var tsxz = group.attr('tsxz');
				var tsxzMin = 0;
				var tsxzMax = _defaultOptions['listMax'];
				if(tsxz != undefined && $.trim(tsxz) != ''){
					tsxz = $.trim(tsxz);
					tsxzMin = tsxz.substring(0,tsxz.indexOf('-'));
				    tsxzMax = tsxz.substring(tsxz.indexOf('-')+1);
				} 
				var datarows = group.find('tr[datarow="true"]');
				var dataArray = new Array(datarows.length);
				if(datarows.length == 0 && tsxzMin > 0){
					validationResult = false;
					group.find('#Extend_List_More_TR').css('background-color','rgb(244, 212, 54)');
				}
				for ( var rn = 0; rn < datarows.length; rn++) {
					var datarow = datarows[rn];
					$.each($(datarow).find('[etype="megdataitem"]'), function(i, n){
						$(n).next().remove('[name="errortip"]');
						var cd = $(n).attr('cd');
						var bt = $(n).attr('bt');
						var gitemVal = $(n).val();
						if(bt == '1' && $.trim(gitemVal) == ''){
							validationResult = false;
							$(n).css(CSS['error-input']);
							$(n).after('<div name="errortip" style="color: red;">*[必填]</div>');
						}
					});
				}
			}
		});
		return validationResult;
	}
	
	function _serverValidation(data, target){
		target.find('[etype="megdataitem"]').css(CSS['no-error-input']);
		target.find('#Extend_List_More_TR').css('background-color','');
		var check = true;
		$.ajaxSetup({async:false});
		$.post(ServerValidationURL,{'extendModuleID': data['DataModule']['id'] , 'data': JSON.stringify(data['data'])},function(d){
			//如果有错误，显示错误信息
			if(d['success'] == '0'){
				var vdata = d['data'];
				$.each(vdata, function(i,n){
					var gid  = n['gid'];
					var mid  = n['mid'];
					var meid = n['meid'];
					var lx   = n['lx'];
					var gdata= n['gdata'];
					if(lx == g_type[0]){
						$.each(gdata, function(ii,nn){
							var el = target.find('[etype="megdata"][gid="'+gid+'"]').find('[etype="megdataitem"][gid="'+gid+'"][name="'+nn['zd']+'"]');
							if(nn['v'] && nn['v'] == '0'){
								check = false;
								el.css(CSS['error-input']);
								el.after('<div name="errortip" style="color: red;">*['+nn['m']+']</div>');
							}
						});
						
					}else if(lx == g_type[1]){
						$.each(gdata,function(ii,nn){
							var tr = target.find('[etype="megdata"][gid="'+gid+'"]').find('tr[datarow="true"]:eq('+ii+')');
							$.each(nn,function(j,k){
								var el = tr.find('[etype="megdataitem"][gid="'+gid+'"][name="'+k['zd']+'"]');
								if(k['v'] && k['v'] == '0'){
									check = false;
									el.css(CSS['error-input']);
									el.after('<div name="errortip" style="color: red;">*['+k['m']+']</div>');
								}
							});
						});
					}
				});
			}
		},'json');
		$.ajaxSetup({async:true});
		return check;
	}
	
	function _obtainExtendData(_this){
		var extendConfig = _this.find('#ExtendConfigEl').data('ExtendConfigData');
		var extendOptions =  _this.find('#ExtendConfigEl').data('ExtendOptions');
		var extendData = {'data':[],'DataModule':extendConfig['DataModule']};
		var megdataTables = $('table[etype="megdata"]');	
		$.each(megdataTables,function(i, n){
			var gdata = {};
			var group = $(n);
			var mid = group.attr('mid');
			var meid = group.attr('meid');
			var gid = group.attr('gid');
			var grouplx = group.attr('lx');
			$.extend(gdata,{'mid':mid,'meid': meid, 'gid': gid, 'lx': grouplx});
			if(grouplx == g_type[0]){
				var gitems = group.find('[etype="megdataitem"]');
				var dataArray = new Array(gitems.length);
				$.each(gitems,function(i, n){
					var gitemName = $(n).attr('name');
					var gitemVal = $(n).val();
					var gitemGs = $(n).attr('gs');
					if(gitemGs == 'ssx'){
						gitemVal =  $(n).attr('realdata');
					}
					dataArray[i] = {'zd': gitemName, 'zdz': gitemVal, 'gs': gitemGs};
				});
				gdata['gdata'] = dataArray;
				extendData['data'][i] = gdata;
			}else if(grouplx == g_type[1]){
				var datarows = group.find('tr[datarow="true"]');
				var dataArray = new Array(datarows.length);
				for ( var rn = 0; rn < datarows.length; rn++) {
					var datarow = datarows[rn];
					var rowdata = new Array(datarows.length);
					$.each($(datarow).find('[etype="megdataitem"]'), function(i, n){
						var gitemName = $(n).attr('name');
						var gitemVal = $(n).val();
						var gitemGs = $(n).attr('gs');
						if(gitemGs == 'ssx'){
							gitemVal =  $(n).attr('realdata');
						}
						rowdata[i] = {'zd': gitemName, 'zdz': gitemVal, 'gs': gitemGs};
					});
					dataArray[rn] = rowdata;
				}
				gdata['gdata'] = dataArray;
				extendData['data'][i] = gdata;
			}
			
		});
		return extendData;
	}
	
	
	
	function _buildHiddenConfigEl(tg,configDate,options){
		var hiddenConfigEl = jQuery('<input type="hidden" id="ExtendConfigEl">');
		hiddenConfigEl.attr('etype','mconfig');
		hiddenConfigEl.data('ExtendConfigData', configDate);
		hiddenConfigEl.data('ExtendOptions', options);
		tg.append(hiddenConfigEl);
	}
	
	function _fillDataIfPossible(_this){
		var postData = 
		{
			'dataId' : _defaultOptions['dataId'],
			'extendModuleID':_defaultOptions['extendModuleID'],
			'dataType':_defaultOptions['dataType']
		};
		$.post(ObtainDataURL,postData,function(data){
			if(data['success'] == 'false'){
				_this.find('[etype="megdataitem"]').val('');
				if(_defaultOptions['mode'] == 'view'){
					_makeFormView(_this);
				}
				return false;
			}
			$.each(data['data'],function(i,n){
				var mid = n['mid'];
				var meid = n['meid'];
				var gid = n['gid'];
				var lx = n['lx'];
				var gdata = n['gdata'];
				var groupTable = _this.find('table[etype="megdata"][mid="'+mid+'"]'+'[meid="'+meid+'"][gid="'+gid+'"]');
				if(lx == g_type[0]){
					$.each(gdata,function(j,k){
						var zd = k['zd'];
						var zdz = k['zdz'];
						var gitem = groupTable.find('[name="'+zd+'"][etype="megdataitem"]');
						var gs = gitem.attr('gs');
						if(gs == 'ssx' && zdz != undefined && zdz != ''){
							var ssxdata = _this.find('#_selectSsx_').data('ssx');
							var sh = zdz.substr(0,2)+'0000';
							var ss = zdz.substr(0,4)+'00';
							var xx = zdz;
							var shmc = '';
							var ssmc = '';
							var xxmc = '';
							$.each(ssxdata, function(i,n){
								if(n['value'] == sh){
									shmc = n['treeNode'];
									var ssd = n['childNode'];
									$.each(ssd,function(ii,nn){
										if(nn['value'] == ss){
											ssmc = 	nn['treeNode'];
											var xxd = nn['childNode'];
											$.each(xxd,function(iii,nnn){
												if(nnn['value'] == xx){
													xxmc = nnn['treeNode'];
													return false;
												}
											});
											return false;
										}
									});
									return false;
								}
							});
							gitem.attr('realdata',zdz);
							gitem.val(shmc + ' ' + ssmc + ' ' + xxmc);
						}else{
							gitem.val(zdz);
						}
					});
				}else if(lx == g_type[1]){
					$.each(gdata,function(j,k){
						var tr = _makeFormElementTRWithData(groupTable.data('extendGroupElementList'),j,k,_this);
						groupTable.find('#Extend_List_More_TR').before(tr);
						groupTable.find('#Extend_List_More_TR').find('a').data('rn',j+1);
					});
				}
			});
			if(_defaultOptions['mode'] == 'view'){
				_makeFormView(_this);
			}
		},'json');
	}
	
	function _buildActionBar(tg, options){
		var actionSaveButton = $('<button name="Extend_Save_Action" id="Extend_Save_Action" type="button">保存草稿</button>');
		var actionSubmitButton = $('<button name="Extend_Submit_Action" id="Extend_Submit_Action" type="button">提交申请</button>');
		actionSaveButton.bind('click', function(){
			var check = _primeValidation(tg);
			if(!check){
				showAlertDivLayer('信息填写不完整,请检查!');
				return false;
			}
			var extendData=_obtainExtendData(tg);
			var check2 = _serverValidation(extendData, tg);
			if(!check2){
				showAlertDivLayer('信息填写有误,请按提示填写!');
				return false;
			}
			var fn = options['actionFn'];
			if(typeof fn == 'function'){
				fn(extendData,'save');
			}
		});
		actionSubmitButton.bind('click', function(){
			var check = _primeValidation(tg);
			if(!check){
				showAlertDivLayer('信息填写不完整,请检查!');
				return false;
			}
			
			var extendData=_obtainExtendData(tg);
			var check2 = _serverValidation(extendData, tg, function(d){});
			if(!check2){
				showAlertDivLayer('信息填写有误,请按提示填写!');
				return false;
			}
			
			var fn = options['actionFn'];
			if(typeof fn == 'function'){
				fn(extendData,'submit');
			}
		});
		var actionbarHTML = '<table width="100%" border="0" class="formlist" category="edit">'
						+'<tfoot>'
						+'	<tr>'
						+'		<td colspan="5">'
						+'			<div class="bz">"<span class="red">*</span>" 为必填项  </div>'
						+'			<div class="btn" id="actionBar"></div>'
						+'		</td>'
						+'   </tr>'
						+'</tfoot>'
					    +'</table>';
		var actionbar = $(actionbarHTML);
		$(actionbar).find('#actionBar').append(actionSaveButton).append(actionSubmitButton);
		$(actionbar).attr('etype','maction');
		tg.append(actionbar);
	}
	
	function _buildNavHead(tg,data){
		if(!data){return;}
		var DIV = jQuery('<div style="height:50px"></div>');
		DIV.attr('etype','mnavigation');
		var navigationDIV = jQuery('<div id="navigation" style="top: 0; background: #fff; position: fixed; width: 100%;_position:absolute;_bottom:auto;_top:expression(eval(document.documentElement.scrollTop));"></div>');
		var titleDIV = jQuery('<div class="title_xxxx"></div>');
		var positionDIV = jQuery('<div class="position_xxxx after"></div>')
		var xmSPAN = jQuery('<span class="people_xx"><span id="xmView">' + data['DataModule']['mc'] + '</span></span>');
		var wxtsSPAN = jQuery('<span class="wxts">温馨提醒： <span>点击下面的类别，可以快速定位到您所要查看的信息</span></span>');
		titleDIV.append(xmSPAN).append(wxtsSPAN);
		var listUL = jQuery('<ul class="list_xxxx"></ul>');
		jQuery.each(data['DataModuleElement'],function(i,n){
			var listLI = jQuery('<li></li>');
			var listLI_anchor = jQuery('<a href="#Extend_'+ n['id'] +'" class="smooth">'+ n['mc'] +'</a>');
			listUL.append(listLI.append(listLI_anchor));
		});
		positionDIV.append(listUL);
		tg.append(DIV.append(titleDIV).append(positionDIV));
	}
	
	function _buildDataTable(tg,data,options){
		if(!data){return;}
		var CONTENT = jQuery('<div class="demo_xxxx" style="margin-top: 20px; overflow-x: hidden; display: block;" id="extend_content"></div>');
		CONTENT.attr('etype','mdatacontent');
		var DataModule = data['DataModule'];
		jQuery.each(data['DataModuleElement'],function(i,n){
			var DIV = jQuery('<div>',{'id':'Extend_'+ n['id'], 'mid':DataModule['id'], 'eid': n['id']});
			DIV.attr('etype','medatacontent');
			DIV.append(_makeDataTableTitle(n));
			DIV.append('<a name="Extend_' + n['id'] + '"></a>');
			jQuery.each(n['extendGroupList'],function(i,item){
				var t = jQuery('<table width="100%" border="0" style="margin-bottom: 3px;" class="formlist" etype="megdataheader">'
					  + '	<thead>'
					  + '		<tr onclick="" style="cursor: hand;">'
					  + '			<th colspan="4">'
					  + '				<span>'+ item['mc'] +'</span>'
					  + '			</th>'
					  + '		</tr>'
					  + '	</thead>'
					  + '</table>');
				DIV.append(t);
				DIV.append(_makeDataTableBody(DataModule,item,options,tg));
			});
			CONTENT.append(DIV);
		});
		tg.append(CONTENT);
	}
	
	
	function _makeDataTableBody(DataModule,data,options,tg){
		var gid = data['id'];
		var meid = data['meid'];
		var mid = DataModule['id'];
		var lx = data['lx'];
		var extendGroupElementList = data['extendGroupElementList'];
		var table = jQuery('<table>',{
			"id" :    "Extend_Data_"+gid,
			"mid":    mid,
			"meid":   meid,
			"gid":    gid,
			"lx" :    lx,
			"width":  "100%",
			"border": "0",
			"style":  "margin-bottom: 3px",
			"class":  "formlist",
			"eType":  "megdata",
			"tsxz":   data['tsxz']
		});
		table.data('extendGroupElementList',extendGroupElementList);
		var tbody = jQuery('<tbody>');
		//如果是普通类型
		if(g_type[0] == lx.toLowerCase()){
			var tr;
			jQuery.each(extendGroupElementList,function(i,n){
				var th;
				if((i+1)%2 != 0){
					tr = jQuery('<tr>');
					tbody.append(tr);
				}
				var th = jQuery('<th width="16%">');
				if('1' == n['bt']){
					th.append('<font category="edit" color="red">* </font>');
				}
				th.append(n['mc']);
				var td = jQuery('<td width="34%">');
				td.append(_makeFormElement(n,{'width': '209px'},tg));
				tr.append(th).append(td);
			});
			table.append(tbody);
			return table;
		//如果是列表类型
		}else if(g_type[1] == lx.toLowerCase()){
			var listTableHead = jQuery('<tr>');
			var actionItem = jQuery('<tr id="Extend_List_More_TR" category="edit">');
			var actionItemTD = jQuery('<td colspan=' + (extendGroupElementList.length + 1) + '>');
			var actionLink = jQuery('<a class="name" href="javascript:;" style="text-decoration: none;">点击增加</a>');
			if(data['tsxz']!=undefined && data['tsxz'].trim() != ''){
				actionLink.html('点击增加  (必填，条数限制：'+data['tsxz']+')');
			}
			actionLink.data('rn', 0);
			actionLink.bind('click', function(){
				$(this).closest('#Extend_List_More_TR').css('background-color','');
				var RN = jQuery(this).data('rn');
				var tsxzMax = _defaultOptions['listMax'];
				if(data['tsxz'] != undefined && $.trim(data['tsxz']) != ''){
					var tsxz = $.trim(data['tsxz']);
				    tsxzMax = tsxz.substring(tsxz.indexOf('-')+1);
				} 
				if($(this).closest('[etype="megdata"]').find('[datarow="true"]').length > (tsxzMax-1)){
					showAlertDivLayer('已达到最大条数限制！');
					return false;
				}
				//var tgElSelector = "#Extend_Data_" + gid + " #Extend_List_More_TR";
				var tgElSelector = "#Extend_Data_" + gid;
				var elsTR=_makeFormElementTR(extendGroupElementList,RN,tg);
				$(tgElSelector).find('tr:last-child').before(elsTR);
				//elsTR.insertBefore(tgElSelector);
				$(this).data('rn',RN+1);
			});
			
			jQuery.each(extendGroupElementList,function(i,n){
				var th;
				var theadEl = jQuery('<th>');
				theadEl.css('width', n['elcd']);
				theadEl.append(jQuery('<div style="text-align:center;font-weight:bold;">'+ n['mc'] +'</div>'));
				listTableHead.append(theadEl);
				
			});
			var actionTH = jQuery('<th style="width:10%" category="edit">').append('<div style="text-align:center;text-decoration: none;">【操作】</div>');
			listTableHead.append(actionTH);
			tbody.append(listTableHead);
			tbody.append(actionItem.append(actionItemTD.append(jQuery('<div style="text-align:center;font-weight:bold;">').append(actionLink))));
			table.append(tbody);
			return table;
		}else if(g_type[2] == lx.toLowerCase()){
			var xh = options['xh'];
			var bdpzid = options['bdpzid'];
			if(xh == undefined || xh == null || xh.trim == ''){
				return;
			}
			$.get(GetStuinfoURL,{'xh':xh,'bdpzid':bdpzid},function(data){
				 var stuinfoBody = jQuery(data);
				 table.append(stuinfoBody);
			},'html');
			
			return table;
		}
	}
	
	/*生成一个TR*/
	function _makeFormElementTR(ns,RN,tg){
		var $this = jQuery(this);
		var TR = jQuery('<tr>',{r: RN, 'datarow':true});
		for ( var i = 0; i < ns.length; i++) {
			var n = ns[i];
			var el = _makeFormElement(n,{},tg);
			TR.append(jQuery('<td>',{align:'center'}).append(el));
		}
		var deleteAction = jQuery('<a class="name" href="javascript:;">【删除】</a>');
		TR.append(jQuery('<td category="edit">').append(deleteAction));
		deleteAction.bind('click', function(){
			TR.remove();
		});
		return TR;
	}
	
	/*生成一个TR*/
	function _makeFormElementTRWithData(ns,RN,data,tg){
		var TR = jQuery('<tr>',{r: RN, 'datarow':true});
		for ( var i = 0; i < ns.length; i++) {
			var n = ns[i];
			var itemData;
			var zd = n['zd'];
			for ( var j = 0; j < data.length; j++) {
				var zdData = data[j];
				if(zdData['zd'] == zd){
					itemData = zdData['zdz'];
					break;
				}
			}
			var el = _makeFormElementWithData(n,{},itemData,tg);
			itemData = '';
			TR.append(jQuery('<td>',{align:'center'}).append(el));
		}
		var deleteAction = jQuery('<a class="name" href="javascript:;">【删除】</a>');
		TR.append(jQuery('<td category="edit">').append(deleteAction));
		deleteAction.bind('click', function(){
			TR.remove();
		});
		return TR;
	}
	
	function _makeFormElementWithData(n,css,val,tg){
		var bt = n['bt'];
		var bz = n['bz'];
		var cd = n['cd'];
		var gid = n['gid'];
		var mc = n['mc'];
		var mrz = n['mrz'];
		var xssx = n['xssx'];
		var gs = n['gs'];
		var eltype = n['lx'];
		var zd = n['zd'];
		var sfbj = n['sfbj'];
		var zdData = n['zdData'];
		//1.text
		if(eltype.toLowerCase() == el_type[0]){
			elementObject = jQuery('<input type="text" style="border:1px solid darkgrey;width:100%">');
			elementObject.val(val);
			//格式处理
			if(gs && (gs.match('datetime') != null)){
				elementObject.bind('focus',function(){
					var format = gs.substring(gs.indexOf('(')+1, gs.indexOf(')'));
					if(format == undefined || format == ''){
						format = 'yyyy-MM-dd';
					}
					WdatePicker({dateFmt:format});
				});
			}
		//2.textarea
		}else if(eltype.toLowerCase() == el_type[1]){
			elementObject = jQuery('<textarea style="border:1px solid darkgrey;word-break:break-all;width:100%" rows="4" ></textarea>');
			elementObject.val(val);
		//3.select
		}else if(eltype.toLowerCase() == el_type[4]){
			elementObject = jQuery('<select style="border:1px solid darkgrey;width:100%">');
			
			if(zdData && zdData.length > 0){
				elementObject.append('<option value=""></option>');
				jQuery.each(zdData,function(i,n){
					elementObject.append('<option value="'+n['dm']+'">'+n['mc']+'</option>');
				});
			}else{
				elementObject.append('<option value=""></option>');
			}
			elementObject.find("option[value='" + val + "']").attr("selected",true);
		}
		elementObject.css(css);
		elementObject.attr({
			"name":    zd,
			"bt": 	   bt,
			"cd": 	   cd,
			"gid": 	   gid,
			"xssx":    xssx,
			"gtype":   eltype,
			"title":   bz,
			"etype":   "megdataitem",
			"gs"   :   gs
		});
		
		if(sfbj && sfbj=='0'){
			elementObject.attr('disabled',true);
		}
		return elementObject;
	}
	
	function _makeFormElement(n,css,tg){
		var bt = n['bt'];
		var bz = n['bz'];
		var cd = n['cd'];
		var gid = n['gid'];
		var mc = n['mc'];
		var mrz = n['mrz'];
		var xssx = n['xssx'];
		var gs = n['gs'];
		var eltype = n['lx'];
		var zd = n['zd'];
		var sfbj = n['sfbj'];
		var zdData = n['zdData'];
		
		/**
		 * 如果是省市县，需要创建省市县选择控件
		 */
		if(gs == 'ssx'){
			_makeSSXSelectDiv(tg);
		}
		
		//1.text
		if(eltype.toLowerCase() == el_type[0]){
			elementObject = jQuery('<input type="text" style="border:1px solid darkgrey;width:100%">');
			elementObject.val(mrz);
			//格式处理
			if(gs && (gs.match('datetime') != null)){
				elementObject.bind('focus',function(){
					var format = gs.substring(gs.indexOf('(')+1, gs.indexOf(')'));
					if(format == undefined || format == ''){
						format = 'yyyy-MM-dd';
					}
					WdatePicker({dateFmt:format});
				});
			}else if(gs == 'ssx'){
				elementObject.attr('readOnly', true);
				elementObject.click(function(){
					var _self = $(this);
					var targetId = $('#_selectSsx_'); 
					var A_top = $(this).offset().top + $(this).outerHeight(true);  //  1
					var A_left =  $(this).offset().left;
					targetId.find("#_selectItemConfirm_").unbind('click');
					targetId.find("#_selectItemConfirm_").bind('click',function(){
						var sh = targetId.find('[name="_sh_"]');
						var ss = targetId.find('[name="_ss_"]');
						var xx = targetId.find('[name="_xx_"]');
						var selectVal = xx.val()||ss.val()||sh.val();
						var selectText = sh.find('option:selected').text() + ' ' + ss.find('option:selected').text() + ' ' + xx.find('option:selected').text();
						_self.val(selectText);
						_self.attr('realdata', selectVal);
						targetId.hide();
					});
					targetId.bgiframe();
					targetId.show().css({"position":"absolute","top":A_top+"px" ,"left":A_left+"px"});
				});
			}
		//2.textarea
		}else if(eltype.toLowerCase() == el_type[1]){
			elementObject = jQuery('<textarea style="border:1px solid darkgrey;word-break:break-all;width:100%" rows="4" ></textarea>');
			elementObject.val(mrz);
		//3.select
		}else if(eltype.toLowerCase() == el_type[4]){
			elementObject = jQuery('<select style="border:1px solid darkgrey;width:100%">');
			
			if(zdData && zdData.length > 0){
				elementObject.append('<option value=""></option>');
				jQuery.each(zdData,function(i,n){
					elementObject.append('<option value="'+n['dm']+'">'+n['mc']+'</option>');
				});
			}else{
				elementObject.append('<option value=""></option>');
			}
			elementObject.find("option[value='" + mrz + "']").attr("selected",true);
		}
		elementObject.css(css);
		elementObject.attr({
			"name":    zd,
			"bt": 	   bt,
			"cd": 	   cd,
			"gid": 	   gid,
			"xssx":    xssx,
			"gtype":   eltype,
			"title":   bz,
			"etype":   "megdataitem",
			"gs"   :   gs
		});
		
		if(sfbj && sfbj=='0'){
			elementObject.attr('disabled',true);
		}
		return elementObject;
	}
	
	function _makeDataTableTitle(data){
		var H3 = jQuery('<h3 class="college_title" style="margin-bottom:3px;" etype="meheader"></h3>')
				.append('<span class="title_name">'+ data['mc'] +'</span>');
		return H3;
	}
	
	
	//查询modeule配置
	function _ajax_query_config(opts,callback, $this){
		$.ajaxSetup({async:false});
		var _data;
		$.ajax({
			   cache:       false,
			   dataType:    "json",
			   contentType: "application/x-www-form-urlencoded",
			   async:       false,
			   type:        "POST",
			   url:         ObtainConfigURL,
			   data:        {'extendModuleID': opts['extendModuleID']},
			   error:       function(XMLHttpRequest, textStatus, errorThrown){
				   				//showAlertDivLayer('数据检索错误');
							},
			   success:     function(data){
						     callback($this,data,opts);
			                }
			});
		$.ajaxSetup({async:true});
		return _data;
	}
	//发送数据校验
	function _ajax_server_validation(data, target, fn){
		$.ajaxSetup({async:false});
		$.post(ServerValidationURL,data,function(d){
			if(fn!=undefined && (typeof fn == 'function')){
				fn(d);
			}
		},'json');
		$.ajaxSetup({async:true});
	}
	
	//ajax请求函数封装(同步)
	function _AJAX(url,opts,callback, $this){
		$.ajaxSetup({async:false});
		var _data;
		$.ajax({
			   cache:       false,
			   dataType:    "json",
			   contentType: "application/x-www-form-urlencoded",
			   async:       false,
			   type:        "POST",
			   url:         url,
			   data:        opts,
			   error:       function(XMLHttpRequest, textStatus, errorThrown){
								//showAlertDivLayer('数据检索错误');
							},
			   success:     function(data){
						     callback($this,data,opts);
			                }
			});
		$.ajaxSetup({async:true});
		return _data;
	}
	
})(jQuery);

String.prototype.trim = function () {
    return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
}