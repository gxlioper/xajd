<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		jQuery(function(){
			jQuery(".ipt").focus(function(){
				//jQuery(this).removeClass(redipt);
			});
		});
		function saveXmjdSz(){
			//���м�¼ʱ������֤���޼�¼ʱ������֤��ֱ�ӽ��б����������̨��ֱ�ӽ���delete������
			if(jQuery(".ipt").length != 0){
				var alldataWrite = true;
				var mcNotRepeat = true;
				var sxNotRepeat = true;
				//������֤
				jQuery(".ipt").each(function(){
					if(this.value == null || jQuery.trim(this.value).length == 0){
						alldataWrite = false;
						jQuery(this).addClass("redipt");
					}
				});
				if(!alldataWrite){
					showAlert("�뽫�����������д������");
					return false;
				}
				//�����ظ���֤
				jQuery("[name='jdmc']").each(function(){
					var val = this.value;
					var obj = jQuery("input[name='jdmc'][value="+val+"]");
					if(obj.length > 1){
						mcNotRepeat = false;
						jQuery(obj).addClass("blueipt");
					}else{
						jQuery(obj).removeClass("blueipt");
					}
				});
				if(!mcNotRepeat){
					showAlert("��Ŀ�׶����Ʋ����ظ���");
					return false;
				}
				//�׶�˳���ظ���֤
				jQuery("[name='jdsx']").each(function(){
					var val = this.value;
					var obj = jQuery("[name='jdsx'][value="+val+"]");
					if(obj.length > 1){
						sxNotRepeat = false;
						jQuery(obj).addClass("purpleipt");
					}else{
						jQuery(obj).removeClass("purpleipt");
					}
				});
				if(!sxNotRepeat){
					showAlert("�׶�˳�����ظ���");
					return false;
				}
			}
			//������Ŀ�׶�����
			var url = "xmsbXmsbjg.do?method=saveXmjdSz";
			ajaxSubFormWithFun("XmsbjgForm", url, function(data) {
				 if(data["message"]=="����ɹ���"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
		    	 }else{
		    		 showAlert(data["message"]);
		    		}
				});
		}
		function selectAll(obj){
			if(obj.checked){
				jQuery("[name='chk']").not("[disabled]").attr("checked","checked");
			}else{
				jQuery("[name='chk']").removeAttr("checked");
			}
			
		}
		function delRow(){
			var obj = jQuery("[name='chk']:checked").parents("tr");
			if(obj.length == 0){
				showAlert("���ȹ�ѡ��Ŀ�׶����ݣ�");
				return false;
			}
			jQuery(obj).remove();
		}
		function addRow(){
			jQuery("#tbody_xmjd").append(
				"<tr class='autorow'>"+
             		"<td width='5%'><input type='checkbox' name='chk'  /><input type='hidden' name='jdid' /></td>"+
				    "<td width='15%'><input type='text' maxlength='50'  onblur = 'removeRed(this)'  class='ipt' name='jdmc' /></td>"+
				    "<td width='15%'><input type='text' maxlength='3' onblur = 'removeRed(this)' onkeyup='checkInputNum(this)' class='ipt' name='jdf' /></td>"+
				    "<td width='15%'><input type='text' maxlength='3' onblur = 'removeRed(this)' onkeyup='checkInput(this)' class='ipt' name='jdsx'/></td>"+
             	"</tr>"
			);
		}

		//onblurʱ������֤
		function removeRed(obj){
			var objclass = jQuery(obj).attr("class");
			var objval = obj.value;
			if(objclass.indexOf("redipt") != -1){
				if(jQuery(obj).val() !=null && jQuery.trim(objval).length != 0){
					jQuery(obj).removeClass("redipt");
				}
			}
			if(objclass.indexOf("blueipt") != -1){
				jQuery("[name='jdmc']").each(function(){
					var val = this.value;
					var obj = jQuery("input[name='jdmc'][value="+val+"]");
					if(obj.length <= 1){
						jQuery(obj).removeClass("blueipt");
					}
				});
			}
			if(objclass.indexOf("purpleipt") != -1){
				jQuery("[name='jdsx']").each(function(){
					var val = this.value;
					var obj = jQuery("[name='jdsx'][value="+val+"]");
					if(obj.length <= 1){
						jQuery(obj).removeClass("purpleipt");
					}
				});
			}
		}
		</script>
		<style type="text/css">
			.ipt{width:90%;}
			.redipt{border-color:red;}
			.blueipt{border-color:blue;}
			.purpleipt{border-color:purple;}
		</style>
	</head>
	<body style="width: 100%">
		<html:form action="/xmsbXmsbjg" method="post" styleId="XmsbjgForm" onsubmit="return false;">
			<html:hidden property="xmdm" styleId="xmdm" value ="${xmdm}"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<tbody id="tbody_xmjd">
						<tr>
							<th colspan="4">
									<span style="float:left">��Ŀ�׶�</span>
								<img  style="float:left;margin-left:4px" src="xsxx/fbgl/images/add-icons-1.gif" alt="����" onclick="addRow();return false;">
								<img  style="float:left;margin-left:4px" src="xsxx/fbgl/images/delete-icons-2.gif" alt="ɾ��" onclick="delRow();return false;">
								<a href="javascript:void(0)"  style="float:left;margin-left:40px;color:blue;font-weight:bold" >${xmmc}</a>
							</th>
                         </tr>
                          <tr>
							<td width='5%'><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
							<td width='15%'>��Ŀ�׶�����</td>
							<td width='15%'>�׶η�</td>
							<td width='15%'>�׶�˳��</td>
                         </tr>
                         <logic:iterate id="i" name="xmjdsz">
                         	<tr class="autorow">
                         		
                         	<logic:equal value="0" name="i" property="num">
                         		<td width='5%'>
                         			<input type="checkbox" name="chk"  />
                         			<input type="hidden" name="jdid" value="${i.jdid}"/>
                         		</td>
                         	   <td width='15%'><input type="text" class="ipt" onblur = 'removeRed(this)'  maxlength="50"  name="jdmc" value="${i.jdmc}"/></td>
							    <td width='15%'><input type="text" class="ipt" onblur = 'removeRed(this)'  onkeyup="checkInputNum(this)" maxlength="3" name="jdf" value="${i.jdf}"/></td>
							    <td width='15%'><input type="text" class="ipt" onblur = 'removeRed(this)'  onkeyup="checkInput(this)" maxlength="3" name="jdsx" value="${i.jdsx}"/></td>
                         		
                         	</logic:equal>
                       		<logic:notEqual value="0" name="i" property="num">
                       			<td width='5%'>
                         			<input type="checkbox" name="chk" disabled="disabled"  />
                         			<input type="hidden" name="jdid" value="${i.jdid}"/>
                         		</td>
                         	   <td width='15%'><input readonly="readonly"type="text" class="ipt" onblur = 'removeRed(this)'  maxlength="50"  name="jdmc" value="${i.jdmc}"/></td>
							    <td width='15%'><input readonly="readonly" type="text" class="ipt" onblur = 'removeRed(this)'  onkeyup="checkInputNum(this)" maxlength="3" name="jdf" value="${i.jdf}"/></td>
							    <td width='15%'><input readonly="readonly" type="text" class="ipt" onblur = 'removeRed(this)'  onkeyup="checkInput(this)" maxlength="3" name="jdsx" value="${i.jdsx}"/></td>
                       		</logic:notEqual>
                         	
							   
                         	</tr>
                         </logic:iterate>
					</tbody>
				</table>
			</div>
			  <div style="height:30px"></div>   
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" onclick="saveXmjdSz();">
										����
									</button>
									<button type="button" onclick="iFClose();">
										�ر�
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

