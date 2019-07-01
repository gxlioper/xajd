<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/pjpy/pjpyComm.js"></script>
		<script language="javascript" defer="defer">
		//ҳ���ʼ��
		function onShow(){
	
		}
		
		//���������Ϣ
		function setOtherXsxx(xh){
			
		}

		//��֤����
		function checkSaveSxjy(){

			var flag = true;
			var xh = jQuery("#input_xh").val();
			
			if(xh == ""){
				alertError("����ѡ��ѧ��");
				flag = false;
			}
			
			if(flag){
				confirmInfo("����ȷ���Ƿ�ִ�б��������",saveSxjy);
			}
		}

		//ִ�б���
		function saveSxjy(tag){
				
			if(tag=="ok"){
				// �õ�JSON����
		        var parameter ={};
		      	//ָ����ȡ�Ŀؼ����ͣ�����ѭ��
				jQuery("input,textarea").each(function(){
					//��ȡ���ؼ�name
					var name=jQuery(this).attr("name");
					//����json����
					parameter[name]=escape(jQuery(this).val());
				});
				
				var url = "general_jygl_sxjy_ajax.do?method=saveSxjy";

				jQuery.ajaxSetup({async:false});
				
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
					}
				);

				jQuery.ajaxSetup({async:true});
			}
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  ondrag="return false">
		
		<html:form action="/general_jygl" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="tab" style="width:100%;height:300px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">	
					<thead>
						<tr style="height:22px">
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<!-- ���� begin -->
						<logic:equal name="doType" value="add">
							<tr>
								<th width="20%">
									<font color="red">*</font>ѧ��
								</th>
								<td width="30%">
									<input type="text" name="str_xh" 
										readonly="readonly" id="input_xh" 
										style="width:100px" value="${rs.xh }"/>
									<button type="button" class="btn_01" onclick="showChooseDiv();">ѡ��</button>
								</td>
								<th width="20%">
									����		
								</th>
								<td width="">
									<span id="span_xm">${rs.xm }</span>
								</td>
							</tr>
						</logic:equal>
						<!-- ���� end -->
						
						<!-- �޸� or�鿴 begin -->
						<logic:notEqual name="doType" value="add">
							<tr>
								<th width="20%">
									ѧ��
								</th>
								<td width="30%">
									<input type="hidden" name="str_xh" id="input_xh" value="${rs.xh }"/>
									${rs.xh }
								</td>
								<th width="20%">
									����		
								</th>
								<td width="">
									<span id="span_xm">${rs.xm }</span>
								</td>
							</tr>
						</logic:notEqual>
						<!-- �޸� or�鿴 end -->
						<tr>
							<th width="">
								�꼶
							</th>
							<td width="">
								<span id="span_nj">${rs.nj }</span>
							</td>
							<th width="">
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td width="">
								<span id="span_xymc">${rs.xymc }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								רҵ
							</th>
							<td width="">
								<span id="span_zymc">${rs.zymc }</span>
							</td>
							<th width="">
								�༶
							</th>
							<td width="">
								<span id="span_bjmc">${rs.bjmc }</span>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr style="height:22px">
							<th colspan="4">
								<span>ʵϰ��ҵ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>	
						<tr>
							<th width="">
								��ҵ��λ
							</th>
							<td width="" colspan="3">
								<input type="text" name="str_jydw" maxlength="30" 
									id="jydw" value="${rs.jydw }"/>
							</td>
						</tr>
						<tr>
							<th width="">
								ʵϰ���<br />
								<font color="blue">(��100��)</font>
							</th>
							<td width="" colspan="3">
								<textarea rows="5" name="str_sxqk" cols="" onblur="chLeng(this,100)"
									id="bz" style="width:99%">${rs.sxqk }</textarea>
							</td>
						</tr>	
					</tbody>
			    </table>
		    </div>
		    
		    <div>
		    	<table width="100%" border="0" class="formlist">	
					<tfoot>
						<tr>
							<td>
								<div class="btn">
									<!-- �޸� or���� begin -->
									<logic:notEqual name="doType" value="view">
										<button type="button" name="����" onclick="checkSaveSxjy();">�� ��</button>
									</logic:notEqual>
									<!-- �޸� or���� end -->
									<button type="button" name="�ر�" onclick="Close();return false;">�� ��</button>
								</div>
							</td>
						</tr>
				    </tfoot>
			    </table>
		    </div>
		    <!-- ѧ��ѡ�� -->
			<%@ include file="/comm/other/choiceXh.jsp"%>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>