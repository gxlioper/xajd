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

		var index = 0; //�±�
		/******************Tag ��������************************/
			var _base_selectbox = '_b_selectbox_';
			var _base_bjdm = '_b_bjdm_';
			var _base_xndsdm = '_b_xndsdm_';
			var _base_jlsj = '_b_jlsj_';
			var _base_jlnr = '_b_jlnr_';
			var _base_tr = '_b_tr_';
		/******************Tag ��������************************/
		var _data_checked = []; //��ѡ�еļ�¼index
		var _data_ = []; //���ύ��¼index
		var _blank_tr ;//jQuery('#bjxwjldata tbody #_blank_tr'); //����
		
		jQuery(function(){
				
			_blank_tr = jQuery('#bjxwjldata tbody #_blank_tr'); //����
			
				//ע���¼�������
				jQuery('#addlink').click(addLinkFn);
				//ע���¼�������
				jQuery('#dellink').click(dellinkFn);
				//ע���¼�������
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
				jQuery('#' + _base_tr + n).remove(); //ɾ���ڵ�
				_data_.splice(jQuery.inArray(n , _data_) , 1);//����
			});
		//���
		_data_checked.splice(0 , _data_checked.length);

		if(jQuery('#bjxwjldata tbody tr').length == 0){
			_blank_tr.appendTo(jQuery('#bjxwjldata tbody'));
			}
	}

	function saveFn(){
		//return false;
		if(!_data_ || _data_.length == 0){
			showAlertDivLayer("��������дһ���༶��Ϊ��¼��");
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
				showAlertDivLayer("�뽫��������д������");
				check = false;
				return false;
			}
			if(_jlnr.val().length > 500){
				showAlertDivLayer("��¼�����������������"+500+",��ȷ�ϣ�");
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
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>ѧ��</th>
							<td style="font-weight: bold">${fdyxx.xn}</td>
							<th>ѧ��</th>
							<td style="font-weight: bold">${fdyxx.xqmc}</td>
						</tr>
						<tr>
							<th>ְ����</th>
							<td>${fdyxx.zgh}</td>
							<th>����  </th>
							<td>${fdyxx.xm}</td>
						</tr>
						<tr>
							<th>����  </th>
							<td>${fdyxx.bmmc}</td>
							<th>��λ</th>
							<td>${fdyxx.gwmc}</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�༶��Ϊ��¼</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<img src="images/knsrd/jiahao.gif" style="margin-left: 20px; cursor: pointer" id="addlink" title="����"/>
								
								<img src="images/knsrd/jianhao.gif" style="margin-left: 20px; cursor: pointer" id="dellink" title="ɾ��"/>
								<%--<button  type="button" >
											����
								</button>
								<button  type="button" >
											ɾ��
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
										<td><font color="red">*</font>�༶</td>
										<td><font color="red">*</font>���</td>
										<td><font color="red">*</font>��¼ʱ��</td>
										<td><font color="red">*</font>��¼����</td>
									</tr>
								</thead>
								<tbody>
									<tr id="_blank_tr">
										<td colspan="5" align="center" style="color: red;font-weight: bold;">
											�����Ӽ�¼��
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
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button id="save_button" type="button" >
										����
									</button>
									<button type="button" name="�� ��" onclick="iFClose();">
										�� ��
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

