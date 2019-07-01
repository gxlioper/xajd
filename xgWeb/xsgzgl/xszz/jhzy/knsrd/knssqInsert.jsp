<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){ 
			createKnslbHtml();
			var isJtqkdc = jQuery("#isJtqkdc").val();
			var xh = jQuery("#xh").val();
			var shzt = jQuery("#shzt").val();
			var knssqzt = jQuery("#knssqzt").val();
			
			if(knssqzt != ""){
				alertError(knssqzt);
				jQuery("#buttonSave").attr("disabled","disabled");
				return false;
			}if(xh!="" && isJtqkdc=="false"){
				alertError("������д����ѧ��<font color='blue'>��ͥ�������</font>��ѧ�����ſ��Խ������������룬����ȷ��^_^||");
				jQuery("#buttonSave").attr("disabled","disabled");
				return false;
			}else if(xh!="" && (shzt == "shz" || shzt == 'tg' || shzt == 'btg')){
				alertError("��ѧ���������¼�Ѿ�������˽׶Σ��޷��ظ����룬�����رձ�ҳ�棬���<font color='blue'>���̸���</font>���в鿴Ŀǰ�Ľ�չ^_^||");
				jQuery("#buttonSave").attr("disabled","disabled");
				return false;
			}		
		}
		
		//�������������
		function createKnslbHtml(){
			
			var sqlb = jQuery("#sqlb").val();
			
			//·��
			var url = "jhzy_knsrd.do?method=createKnslbHtml";
			//����
		 	var parameter = {
				"str_sqlb":escape(sqlb)
			};
			
			$("divWaiting").style.display="";
			$("divDisable").style.display="";

			jQuery("#div_knslb").load(
				url,
				parameter,
				function(){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
				}
			);
		}
		
		//�����������϶�
		function saveKnsrdSq(){
			var xh = jQuery("#xh").val();
			var sqly = jQuery("#sqly").val();
			var sqlb = "";
			
			jQuery("input[name=checkbox_sqlb]:checked").each(function(){
				sqlb+=jQuery(this).val()+"luojw";
			});
			
			if(xh == ""){
				alertError("ѧ�Ų���Ϊ�գ�����ȷ��^_^||");
				return false;
			}
			
			if(sqlb == ""){
				alertError("�����ٹ�ѡһ���������^_^||");
				return false;
			}
			
			if(sqly == ""){
				alertError("�������ɲ���Ϊ�գ�����ȷ��^_^||");
				return false;
			}
		
			var url = "jhzy_knsrd.do?method=saveKnsrdSq";
			
			//����
		 	var parameter = {
		 		"str_xh":escape(xh),
		 		"str_sqlb":escape(sqlb),
		 		"str_sqly":escape(sqly)
			};
	
		 	$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			jQuery.ajaxSetup({async:false});
			
			jQuery.post(url,
				parameter,
				function(result){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					alertInfo(result);
					closeWindown();		
				}
			);
	
			jQuery.ajaxSetup({async:true});
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  >
		<html:form action="/general_pjpy" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="url" name="url" value="jhzy_knsrd.do?method=knssqInsert"/>
			<input type="hidden" id="lx" name="lx" value="ѧ��"/>
			<input type="hidden" id="isJtqkdc" name="isJtqkdc" value="${isJtqkdc }"/>
			<input type="hidden" id="shzt" name="shzt" value="${rs.shzt }"/>
			<input type="hidden" id="knssqzt" name="knssqzt" value="${knssqzt }"/>

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<!-- ѧ��������Ϣ begin-->
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr >
							<th align="right" width="25%">
								<font color="red">*</font>ѧ��
							</th>
							<td align="left" width="25%">
								<logic:equal name="userType" value="stu">
									<input type="hidden" id="xh"  value="${xh }"/>
									${xh }
								</logic:equal>
								<logic:notEqual name="userType" value="stu">
									<input type="text" id="xh" readonly="readonly" value="${rs.xh }"/>
									<button type="button" onclick="sendXx();return false;"
										class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</logic:notEqual>	
							</td>
							<th align="right" width="25%">
								����
							</th>
							<td align="left" width="25%">
								${rs.xm }
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								�Ա�
							</th>
							<td align="left" width="">
								${rs.xb }
							</td>
							<th align="right" width="">
								ѧ��
							</th>
							<td align="left" width="">
								${rs.xz }
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								�꼶
							</th>
							<td align="left" width="">
								${rs.nj }
							</td>
							<th align="right" width="">
								<bean:message key="lable.xb" />
							</th>
							<td align="left" width="">
								${rs.xymc }
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								רҵ
							</th>
							<td align="left" width="">
								${rs.zymc }
							</td>
							<th align="right" width="">
								�༶
							</th>
							<td align="left" width="">
								${rs.bjmc}
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								����
							</th>
							<td align="left" width="">
								${rs.mzmc }
							</td>
							<th align="right" width="">
								������ò
							</th>
							<td align="left" width="">
								${rs.zzmmmc }
							</td>
						</tr>
						<tr >
							<th align="right" width="">
								���֤��
							</th>
							<td align="left" width="">
								${rs.sfzh }
							</td>
							<th align="right" width="">
								��������
							</th>
							<td align="left" width="">
								${rs.csrq }
							</td>
						</tr>
					</tbody>
					<!-- ѧ��������Ϣend -->
					
					<!-- ��������Ϣ begin-->
					<thead>
						<tr>
							<th colspan="4">
								<span>��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="25%">
								<font color="red">*</font>�������
								<br/><font color="red">(�ɶ�ѡ)</font>
							</th>
							<td align="left" width="75%" colspan="3">
								<input type="hidden" id="sqlb" value="${rs.sqlb }"/>
								<div id="div_knslb" style="width:100%;height:200px;overflow-x:hidden;overflow-y:auto;"></div>
							</td>
						</tr>
						<tr>
							<th align="right" width="">
								<font color="red">*</font>��������
								<br/><font color="red">(��������200)</font>
							</th>
							<td align="left" width="" colspan="3">
								<textarea rows="3" id="sqly" cols="" 
									onblur="chLeng(this,200);"
									style="word-break:break-all;width:99%" >${rs.sqly }</textarea>
							</td>
						</tr>
					</tbody>
					<!-- ��������Ϣ end-->			
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" name="����" onclick="saveKnsrdSq();return false;" id="buttonSave">
										�� ��
									</button>
									<button type="button" name="�ر�" onclick="Close();return false;" id="buttonClose">�� ��</button>					           
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>