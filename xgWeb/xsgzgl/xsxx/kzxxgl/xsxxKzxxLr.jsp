<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/extend/zfsoft-dataExtend.js"></script>
		<script type="text/javascript">

		

	/**
	 * ����������ύ���ݲ�������������Լ�ʵ�֣������ṩ
	  data��������
	  type����������,save �� submit
	 */
	jQuery(function() {
		var sfqy = '${sfqy}';
		var yxqkz = '${yxqkz}';
		var splsz = '${splsz}';
		var configError = '${configError}';
		
		function actionButtonFn(data, type) {
			var sqid = jQuery("#xskzxxgl_sqid").val();
			var shzt = jQuery("#xskzxxgl_shzt").val();
			var splc = jQuery("#xskzxxgl_splc").val();
			var moduleId = jQuery('#xskzxxgl_module').val();
			if(sfqy == '0'){
				showAlertDivLayer('��ǰ����δ����ʹ�ã�����ϵ����Ա��');
				return false;
			}
			if(yxqkz == '0'){
				showAlertDivLayer('��ǰʱ��θù���δ����ʹ�ã�����ϵ����Ա��');
				return false;
			}
			if(splsz == '0'){
				showAlertDivLayer('�����������ò���ȷ������ϵ����Ա��');
				return false;
			}
			//�����������������У��������κβ���
			if(sqid!=""&&splc!=""&&shzt=="5"){
				showAlertDivLayer('����������������У��ݲ��ܱ�����ύ���ݣ�');
				return false;
			}
			//����������룬֮ǰ���ݱ���˲�ͨ����֮ǰ�������ͨ��������Ҫ�����룬��ô������ID
			//�������δ�ύ���߱��˻أ���ô����������ID
			
			var url = "xsxx_kzxxgl.do?method=xslrAction";
			var msg = '��ȷ��Ҫ�ύ��';
			if(type=='save'){
				msg = '��ȷ��Ҫ������';
			};
			showConfirmDivLayer(msg, {
				"okFun" : function() {
					jQuery.post(url, {
						"extendData" : JSON.stringify(data),
						"actionType" : type,
						"sqid"       : sqid,
						"shzt"       : shzt,
						"splc"       : splc,
						"exendDateModuleId": moduleId
					}, function(_data) {  
						showAlertDivLayer(_data['message'], {}, 
							{'clkFun': function(){
							window.location.reload();
						}});
					}, 'json');
				}
			});
			
		}
		
		jQuery('#ExtendDataContent').dataExtend( {
			"mid"      : jQuery('#xskzxxgl_module').val(),
			"dataId"   : jQuery('#xskzxxgl_sqid').val(),
			"dataType" : "2",
			"xh"       : jQuery('#xskzxxgl_xh').val(),
			"bdpzid"   : "xsxxgl",
			"actionFn" : actionButtonFn,
			"naviBar"  :��true,
			"mode"     : "edit"
		});
	});

	
	function showLcinfo(){
		var sqid = jQuery("#xskzxxgl_sqid").val();
		var shzt = jQuery("#xskzxxgl_shzt").val();
		var splc = jQuery("#xskzxxgl_splc").val();
		if(sqid == ""){
			showAlertDivLayer("��δ�ύ�κ����ݣ������������Ϣ��");
			return false;
		}
		if(splc=="" || splc=="wxsh"){
			showAlertDivLayer("��������������ˣ������������Ϣ��");
			return false;
		}
		if(shzt=="0"){
			showAlertDivLayer("��������δ�ύ�������������Ϣ��");
			return false;
		}
		showDialog("ѧ����չ��Ϣ���̸���",600,400,'comm_spl.do?method=lcgz&sqid='+sqid+"&splc="+splc);
	}
		
	
</script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">��Ϣ</a>
			</p>
		</div>			
		<!-- ���� end-->
		<logic:equal value="1" name="configError">
			<!-- ��ʾ��Ϣ START-->
			<div class="prompt" id="div_help">
				<h3>
					<span>������Ϣ��</span>
				</h3>
				<p>
					<span id="div_help" style="color:red;font-weight: bold;">
					<logic:equal value="0" name="sfqy" scope="request">
						��չ��Ϣ��δ����,����ϵ����Ա.</br>
					</logic:equal>
					<logic:equal value="0" name="yxqkz" scope="request">
						��ǰ���ڿ���ʱ��������,���޷�������д��,����ϵ����Ա.</br>
					</logic:equal>
					<logic:equal value="0" name="splsz" scope="request">
						�����������ò���ȷ,����ϵ����Ա.</br>
					</logic:equal>
					</span>
				</p>
				<a onclick="this.parentNode.style.display='none';" title="����"
					class="close"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->
		</logic:equal>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<li>
					<a href="javascript:void(0);" onclick="showLcinfo();return false;" 
						   title="�鿴������̡�"
						   class="btn_cs">���̸���</a>
					</li>
				</ul>
			</div
		</div>
		<input type="hidden" name="xskzxxgl_sqid" value="${sqid}" id="xskzxxgl_sqid"/>
		<input type="hidden" name="xskzxxgl_splc" value="${splc}" id="xskzxxgl_splc"/>
		<input type="hidden" name="xskzxxgl_shzt" value="${shzt}" id="xskzxxgl_shzt"/>
		<input type="hidden" name="xskzxxgl_module" value="${dataExtendModule}" id="xskzxxgl_module"/>
		<input type="hidden" name="xskzxxgl_xh" value="${xh}" id="xskzxxgl_xh"/>
		<div id="ExtendDataContent"></div>
	</body>
</html>
