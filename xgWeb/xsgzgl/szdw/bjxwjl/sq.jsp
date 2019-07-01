<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true" ></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/bjxwjl/js/wh.js"></script>
		<script type="text/javascript" src="js/json.js"></script>
		<script type="text/javascript" defer="defer">

		var index = 0; //下标
		/******************Tag 命名规则************************/
			var _base_selectbox = '_b_selectbox_';
			var _base_bjdm = '_b_bjdm_';
			var _base_xndsdm = '_b_xndsdm_';
			var _base_jlsj = '_b_jlsj_';
			var _base_jlnr = '_b_jlnr_';
			var _base_tr = '_b_tr_';
		/******************Tag 命名规则************************/
		var _data_checked = []; //被选中的记录index
		var _data_ = []; //待提交记录index
		var _blank_tr ;//jQuery('#bjxwjldata tbody #_blank_tr'); //拷贝
		
		jQuery(function(){
				
			_blank_tr = jQuery('#bjxwjldata tbody #_blank_tr'); //拷贝
			
				//注册事件处理函数
				jQuery('#addlink').click(addLinkFn);
				//注册事件处理函数
				jQuery('#dellink').click(dellinkFn);
				//注册事件处理函数
				jQuery('#save_button').click(saveFn);
		});


	function addLinkFn(){
		if(jQuery('#bjxwjldata tbody #_blank_tr').length == 1)
			jQuery('#bjxwjldata tbody #_blank_tr').remove();
		var clone = jQuery('#_hidden_tr').clone();
		clone.attr('id' , _base_tr + index);
		clone.find(":checkbox").data('index' , index)
							.attr('id' , _base_selectbox + index);
		clone.find(":checkbox").bind('click' , function(){
					if(jQuery(this).attr('checked')){
						if(jQuery.inArray(jQuery(this).data('index') , _data_checked))
								_data_checked.push(jQuery(this).data('index'));
					}else{
						if(jQuery.inArray(jQuery(this).data('index') , _data_checked) >= 0)
							_data_checked.splice(jQuery.inArray(jQuery(this).data('index') , _data_checked) , 1);
					}
			});
		clone.find("select[name='bjdm']").attr('id' , _base_bjdm + index);
		clone.find("select[name='xndsdm']").attr('id' , _base_xndsdm + index);
		clone.find("input[name='jlsj']").attr('id' , _base_jlsj + index);
		clone.find("textarea[name='jlnr']").attr('id' , _base_jlnr + index);
		
		clone.find("input[name='jlsj']").bind('focus' , function(){
			return showCalendar(this.id,'yyyyMMdd');
		});
		
		clone.appendTo(jQuery('#bjxwjldata tbody'));
		_data_.push(index);
		index++;
	}


	function dellinkFn(){
		if(!_data_checked || _data_checked.length == 0)
			return;
		jQuery.each(_data_checked , function(i,n){
				jQuery('#' + _base_tr + n).remove(); //删除节点
				_data_.splice(jQuery.inArray(n , _data_) , 1);//过滤
			});
		//清空
		_data_checked.splice(0 , _data_checked.length);

		if(jQuery('#bjxwjldata tbody tr').length == 0){
			_blank_tr.appendTo(jQuery('#bjxwjldata tbody'));
			}
	}

	function saveFn(){
		//return false;
		if(!_data_ || _data_.length == 0){
			showAlertDivLayer("请至少填写一条班级行为记录！");
			return false;
		}
		
		var submit_data = [];

		var check = true;
		jQuery.each(_data_ , function(i , n){
			var _bjdm = jQuery('#' +_base_bjdm + n );
			var _xndsdm = jQuery('#' +_base_xndsdm + n );
			var _jlsj = jQuery('#' +_base_jlsj + n );
			var _jlnr = jQuery('#' +_base_jlnr + n );
			if(_jlsj.val() == '' || _jlnr.val() == '')
			{
				showAlertDivLayer("请将必填项填写完整！");
				check = false;
				return false;
			}
			if(_jlnr.val().length > 500){
				showAlertDivLayer("记录内容最大字数不超过"+500+",请确认！");
				check = false;
				return false;
			}

			var item = {
				ibjdm : _bjdm.val(),
				ixndsdm : _xndsdm.val(),
				ijlsj : _jlsj.val(),
				ijlnr : _jlnr.val()
			}
			submit_data.push(item);		
		});

		if(!check) return false;
		
		jQuery("input[name='jsondata']").val(JSON.stringify(submit_data));
		
		var url = "szdw_bjxwjlwh.do?method=sqAction";
		ajaxSubFormWithFun("bjxwjlwhForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
	}
		</script>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		
		<html:form action="/szdw_bjxwjlwh" method="post" styleId="bjxwjlwhForm">
		<input name="xn" value="${fdyxx.xn}" type="hidden"/>
		<input name="xqdm" value="${fdyxx.xq}" type="hidden"/>
		<input name="jlr" value="${fdyxx.zgh}" type="hidden"/>
		<input name="jsondata"  type="hidden"/>
			<table style="display: none;" >
				<tr id="_hidden_tr">
					<td width="10px">
						<input type="checkbox" name="selectbox"/>
					</td>
					<td>
						<select name="bjdm" >
							<logic:iterate id="d" name="fdybjxxList" >
								<option value="${d.bjdm}">${d.bjmc}</option>
							</logic:iterate>
						</select>
					</td>
					<td>
						<select name="xndsdm">
							<logic:iterate id="d" name="lbxxList" >
								<option value="${d.xndsdm}">${d.xndsmc}</option>
							</logic:iterate>
						</select>
					</td>
					<td>
						<input type="text" name="jlsj"  style="width:120px;" id="__hidden_tr_jlsj" />
					</td>
					<td>
						<textarea rows="1" style="width:97%" onblur="checkLen(this,200);" name="jlnr"></textarea>
					</td>
				</tr>
			</table>
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>学年</th>
							<td style="font-weight: bold">${fdyxx.xn}</td>
							<th>学期</th>
							<td style="font-weight: bold">${fdyxx.xqmc}</td>
						</tr>
						<tr>
							<th>职工号</th>
							<td>${fdyxx.zgh}</td>
							<th>姓名  </th>
							<td>${fdyxx.xm}</td>
						</tr>
						<tr>
							<th>部门  </th>
							<td>${fdyxx.bmmc}</td>
							<th>岗位</th>
							<td>${fdyxx.gwmc}</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>班级行为记录</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<img src="images/knsrd/jiahao.gif" style="margin-left: 20px; cursor: pointer" id="addlink" title="增加"/>
								
								<img src="images/knsrd/jianhao.gif" style="margin-left: 20px; cursor: pointer" id="dellink" title="删除"/>
								<%--<button  type="button" >
											增加
								</button>
								<button  type="button" >
											删除
								</button>
							--%></td>
						</tr>
						<tr>
						<td colspan="4">
							<div style="width: 100%; height: 350px; overflow: scroll;">
							<table  width="100%" id="bjxwjldata">
								<thead>
									<tr>
										<td  width="10px"></td>
										<td><font color="red">*</font>班级</td>
										<td><font color="red">*</font>类别</td>
										<td><font color="red">*</font>记录时间</td>
										<td><font color="red">*</font>记录内容</td>
									</tr>
								</thead>
								<tbody>
									<tr id="_blank_tr">
										<td colspan="5" align="center" style="color: red;font-weight: bold;">
											请增加记录！
										</td>
									</tr>
								</tbody>
							</table>
							</div>
						</td>
						</tr>
					</tbody>
					</table>
					
					<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button id="save_button" type="button" >
										保存
									</button>
									<button type="button" name="关 闭" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>

