<%@ page language="java" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script>
        function onLoad(){
        	var xmdm = $("xmdm").value;
			jQuery.ajax({
				type:'post',
				url:'pjpy_ty_ajax.do?method=isRsszSet&xmdm='+xmdm,
				dataType:'json',
				success:function(data){
				   if(!data){
					   jQuery("#nextStep").hide();
				   }else{
					   jQuery("#nextStep").show();
				   }
				}
			});
        }
		
		function confim(){
			var xmdm = $("xmdm").value;
			jQuery.ajax({
				type:'post',
				url:'pjpy_ty_ajax.do?method=isRsszSet&xmdm='+xmdm,
				dataType:'json',
				success:function(data){
				   if(!data){
					     saveUpdate('pjpy_ty_sjsz.do?method=sjszUpdate&doType=save','')
						window.close();
						window.dialogArguments.window.alertInfo("����ɹ�");
				   }else{
					   confirmInfo("��Ŀʱ�����������,�������ò��ɷ���,�����Ҫ�޸��뵽��Ŀʱ��������ֱ���޸�,�Ƿ�Ҫ������һ��������?",mbDownLoad);
				   }
				}
			});
		}
		function mbDownLoad(tag){
			if(tag=='ok'){
				saveUpdate('pjpy_ty_sjsz.do?method=sjszFlow&doType=save','');
			}
		}
		
		
		</script>
	</head>

	<body onload="onLoad()">
	<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��������-��Ŀ����-����ʱ������</a>
				</p>
	  </div>
	
	 <div class="flow-steps">
			  <ol class="num5">
			    <li class="done" style = "width:19%"><span class="first">1. ��Ŀ�������</span></li>
			    <li class="done" style = "width:19%"><span>2. ��Ŀ��÷�Χ����</span></li>
			    <li class="done current-prev"><span>3. ��Ŀ��������</span></li>
			    <li class="current" style = "width:19%"><span>4. ��Ŀʱ������</span></li>
			    <li class="last" style = "width:19%"><span>5. ��Ŀ��������</span></li>
			  </ol>
        </div>
	
		<html:form action="/pjpy_ty_sjsz" method="post">
			<input type="hidden" id="pjxn" value="${pjxtszModel.pjxn }" name="save_pjxn"/>
			<input type="hidden" id="pjxq" value="${pjxtszModel.pjxq }" name="save_pjxq"/>
			<input type="hidden" id="pjnd" value="${pjxtszModel.pjnd }" name="save_pjnd"/>
			<input type="hidden" id="xmdm" value="${pjpySjszForm.xmdm }" name="save_xmdm"/>
		    <html:hidden property="xmdm" styleId="xmdm"/>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
								    <button type="button" name="��һ��"  id="nextStep" onclick="saveUpdate('pjpy_comm_rssz.do?method=rsszManageFlow&rssz=${xmszModel.rssz}','')" >
										  ����
									</button>
									<button type="button" onclick="confim()">
										����
									</button>

									<button type="button" id="buttonSave" onclick="window.close();return false;">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="2">
								<span>������Ŀʱ������</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
					<td colspan="2">
					<div class="prompt" id="div_help">
				            <p>
								��Ŀ���ƣ�${xmmc }&nbsp;&nbsp;
								ѧ�꣺${pjxtszModel.pjxn }&nbsp;&nbsp;
								��ȣ�${pjxtszModel.pjnd }&nbsp;&nbsp;
								ѧ�ڣ�${pjxtszModel.pjxqmc }&nbsp;&nbsp;
							</p>
      	            </div>
      	           </td>
					</tr>
						<tr>
							<th width="32%">
								���뿪ʼʱ��
							</th>
							<td width="68%">
								<input type="text" id="save_sqkssj" name="save_sqkssj"
									   style="width:100px" readonly="true" 
									   onclick="return showCalendar(this.id,'y-mm-dd')"/>
							</td>
						</tr>
						<tr>
							<th>
								�������ʱ��
							</th>
							<td>
								<input type="text" id="save_sqjssj" name="save_sqjssj"
									   style="width:100px" readonly="true" 
									   onclick="return showCalendar(this.id,'y-mm-dd')"/>
							</td>
						</tr>
						<tr>
							<th>
								������ƿ���
							</th>
							<td>
								<input type="radio"  name="save_sqkzkg"  value="0"/>��
								<input type="radio"  name="save_sqkzkg"  value="1"  checked="checked" />��
							</td>
						</tr>
						<tr>
							<th>
								��˿�ʼʱ��
							</th>
							<td>
								<input type="text" id="save_shkssj" name="save_shkssj"
									   style="width:100px" readonly="true" 
									   onclick="return showCalendar(this.id,'y-mm-dd')"/>
							</td>
						</tr>
						<tr>
							<th>
								��˽���ʱ��
							</th>
							<td>
								<input type="text" id="save_shjssj" name="save_shjssj"
									   style="width:100px" readonly="true" 
									   onclick="return showCalendar(this.id,'y-mm-dd')"/>
							</td>
						</tr>
						<tr>
							<th>
								��˿��ƿ���
							</th>
							<td>
								<input type="radio"  name="save_shkzkg"  value="0"/>��
								<input type="radio"  name="save_shkzkg"  value="1"  checked="checked"/>��
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
