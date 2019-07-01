<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	
	<script	type="text/javascript">
		function getJllbList(obj){
			jQuery.post('gyglnew_gyjlgl.do?method=getJllbList',{jldldm:obj.value},function(data){
					var option = "<option value=''></option>";
					for(var i = 0; i < data.length; i++){
						option +="<option value='"+data[i].jllbdm+"'>"+data[i].jllbmc+"</option>";
					}
					jQuery('#jllbdm').empty().append(option);
			},'json');
			
		}	

		function getJllbListById(){
			jQuery.post('gyglnew_gyjlgl.do?method=getJllbList',{jldldm:$("jldldm").value},function(data){
					var option = "<option value=''></option>";
					for(var i = 0; i < data.length; i++){
						option +="<option value='"+data[i].jllbdm+"'>"+data[i].jllbmc+"</option>";
					}
					jQuery('#jllbdm').empty().append(option);
					jQuery('#jllbdm').val($("hidden_jllb").value);				
			},'json');
		}	
		
		function save(){
			if(jQuery('#wjxh').val()==""){
				alertInfo("��ѡ��ѧ�ţ�");
				return false;
			}
			if(jQuery('#xn').val()==""){
				alertInfo("��ѡ��Υ��ѧ�꣡");
				return false;
			}
			if(jQuery('#xq').val()==""){
				alertInfo("��ѡ��Υ��ѧ�ڣ�");
				return false;
			}
			if(jQuery('select[name=jldldm]').val()==""){
				alertInfo("����д���ɴ��࣡");
				return false;
			}
			if(jQuery('select[name=jllbdm]').val()==""){
				alertInfo("����д�������");
				return false;
			}
			if(jQuery('#wjsj').val()==""){
				alertInfo("����дΥ��ʱ�䣡");
				return false;
			}
			
			var str = jQuery("#wjxh").val()+"!!@@!!";
			var len=jQuery("input[type=checkbox][name=xhs]:checked").length;
			if(len>=1){
				var array = jQuery("input[type=checkbox][name=xhs]:checked");
				
				for (var i=0;i<array.length;i++) {
					var pkValue = jQuery(array[i]).parents().find("td").eq(0).find("input[type='checkbox']").val();
					str += pkValue+"!!@@!!";
				}
			}
			saveData('gyglnew_gyjlgl.do?method=gyjlxxZj&doType=save&wjxh='+str,'');
		}
		function changeTqs(){
			var obj = document.getElementById("tqs");
			if(obj.checked){
				getTqs();
				jQuery('input[type=checkbox][name=xhs]').attr('checked',jQuery(obj).attr('checked'));
				jQuery('input[type=checkbox][name=th]').attr('checked',jQuery(obj).attr('checked'));
				jQuery("#xs_body").css("display","");
			}else{
				jQuery('input[type=checkbox][name=th]').attr('checked',jQuery(obj).attr('checked'));
				jQuery('input[type=checkbox][name=xhs]').attr('checked',jQuery(obj).attr('checked'));
				jQuery("#xs_body").css("display","none");	
				}
			}

		function getTqs(){
			jQuery.ajaxSetup({async:false});
			jQuery.post('gyglnew_gyjlgl.do?method=getTqs',{xh:jQuery("#wjxh").val()},function(data){
				
				if(data!=null && data.length!=0){
					var tr="";
					for(var i = 0; i < data.length; i++){
						tr +="<tr><td><input name='xhs' type='checkbox' value='"+data[i].xh+"'/></td>";
						tr +="<td>"+data[i].cwh+"</td>";
						tr +="<td>"+data[i].xh+"</td>";
						tr +="<td>"+data[i].xm+"</td>";
						tr +="<td>"+data[i].nj+"</td>";
						tr +="<td>"+data[i].xymc+"</td>";
						tr +="<td>"+data[i].zymc+"</td>";
						tr +="<td>"+data[i].bjmc+"</td></tr>";
					}
					jQuery('#tbody_tqs').empty().append(tr);
				}else{
					jQuery('#tbody_tqs').empty().append("<tr><td colspan='8'><b>��ͬ����ѧ����</b></td></tr>");
				}	
			},'json');
			jQuery.ajaxSetup({async:true});
		}

		function sendXxGy(){
			var tableName=$("tableName").value;
			var url = "gyjl_gyjlglnew_gyjlxscx.do";
			showTopWin(url,800,600);
		}

		function getStuInfo(){
			jQuery.ajaxSetup({async:false});
			var parameter = {};
			parameter["xh"]=escape(jQuery("#wjxh").val());
			var url="gyjl_gyjlglnew_ajax.do?method=getStuInfo";
			jQuery.getJSON(url,parameter,
					function(data){
						if(data!=null){
							jQuery("#xm").html(data.xm);
							jQuery("#nj").html(data.nj);
							jQuery("#xymc").html(data.xymc);
							jQuery("#zymc").html(data.zymc);
							jQuery("#bjmc").html(data.bjmc);
							jQuery("#ldmc").html(data.ldmc);
							jQuery("#qsh").html(data.qsh);
							jQuery("#cwh").html(data.cwh);
							jQuery("#ssdh").html(data.qsdh);
							changeTqs();
						}
					}
				);
			jQuery.ajaxSetup({async:true});
		}

		//ȫѡ
		function selectAll(obj){ 
			jQuery('input[type=checkbox][name=xhs]').attr('checked',jQuery(obj).attr('checked'));
		}
				
				
		jQuery(function(){
			getJllbListById();
		})
		
	</script>
</head>
<body >
	<html:form action="/gyglnew_gyjlgl" method="post" >	
		<input type="hidden" id="xh" name="xh"��value=""/>
		<input type="hidden" name="url" id="url" value="gyglnew_gyjlgl.do?method=gyjlxxZj"/>	
		<input type="hidden" id="xhstr" name="xhstr" value=""/>
		<input type="hidden" id="searchTjstr" name="searchTjstr" value=""/>
		<input type="hidden" name="tableName" id="tableName" value="view_xsjbxx"/>	
		<input type="hidden" id="count" value=""/>
		<input type="hidden" id="mklx" value="gygl"/>
		<input type="hidden" id="hidden_jllb" value="${jllb}"/>
		<div class="tab">
		<table class="formlist" width="95%">	
			<thead>
				<tr>
					<th colspan="4">
					<span>ѧ����Ϣ</span>
					</th>
					</tr>
			</thead>
			<tbody>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>ѧ��
						</th>
						<td align="left" width="30%">
							<html:text  property="xh" styleId="wjxh" value="${rs.xh}"  
							 maxlength="20" readonly="true"/>
						<button type="button" id="btn_getStuInfo" onclick="getStuInfo();" style="display: none;"></button>
							<button type="button" onclick="sendXxGy()"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</td>
						<th align="right" width="20%">
							����
						</th>
						<td align="left" id="xm">
							${rs.xm}
						</td>
					</tr>
					<tr>
						<th align="right">
							�꼶
						</th>
							<td align="left" id="nj">
							${rs.nj}
						</td>
						<th align="right">
							<bean:message key="lable.xb" />
						</th>
							<td align="left" id="xymc">
							${rs.xymc}
						</td>
					</tr>
					<tr>
						<th align="right">
							רҵ
						</th>
							<td align="left"  id="zymc">
							${rs.zymc}
						</td>
						<th align="right">
							�༶
						</th>
						<td align="left" id="bjmc">
							${rs.bjmc}
						</td>
					</tr>
					<tr>
						<th>
							¥������
						</th>
						<td id="ldmc">
							${rs.ldmc }
						</td>
						<th>
							���Һ�
						</th>
						<td id="qsh">
							${rs.qsh }
						</td>
					</tr>
					<tr>
						<th>
							��λ��
						</th>
						<td id="cwh">
							${rs.cwh }
						</td>
						<th>
							���ҵ绰
						</th>
						<td id="ssdh">
							${rs.ssdh}
						</td>
					</tr>
			</tbody>
			<thead>
				<tr>
					<th colspan="4">
					<span>��Ԣ������Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="20%">
					<font color="red">*</font>Υ��ѧ��
				</th>
				<td align="left" width="30%">
					<html:select name="rs" property="xn" styleId="xn" style="width:140px">
					<html:options collection="xnList" labelProperty="xn" property="xn"/>
					</html:select>
				</td>
					<th align="right" width="20%">
						<font color="red">*</font>Υ��ѧ��
					</th>
				<td align="left" width="30%">
					<html:select  property="xq" styleId="xq" style="width:140px" value="${rs.wjxq}">
					<html:option value=""></html:option>
					<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<th align="right" width="20%">
					<font color="red">*</font>���ɴ���
				</th>
				<td align="left" width="30%">
					<html:select property="jldldm" styleId="jldldm" style="width:140px" onchange="getJllbList(this)">
					<html:option value=""></html:option>
					<html:options collection="jldlList" labelProperty="jldlmc" property="jldldm"/>
					</html:select>
				</td>
				<th align="right" width="20%">
					<font color="red">*</font>�������				
				</th>
				<td align="left" width="30%" >
					<html:select property="jllbdm" styleId="jllbdm" style="width:140px" >
					<html:option value=""></html:option>	
					<html:options collection="jllbList" labelProperty="jllbmc" property="jllbdm"/>
					</html:select>
				</td>
			</tr>
			<tr>
					<th width="20%">
						<font color="red">*</font>Υ��ʱ��				
					</th>
					<td align="legt" width="30%">
						<html:text property="wjsj" styleId="wjsj" style="width:140px" onkeypress="onlyBackSpace(this,event);"
							onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm:ss')" readonly="true"></html:text>
					</td>
			</tr>
			<tr>
					<th>
						��ע
						<br /><font color="red">(������1000����)</font>
					</th>
					<td colspan="4">
						<html:textarea property='bz' style="width:98%" styleId="bz" rows='3' onblur="chLeng(this,1000);"/>
					</td>
			</tr>
				<tr>
					<td colspan="5">
<%--					<span>��Ԣ������Ϣ</span>--%>
						<b>ͬ����</b><input id="tqs" type="checkbox" onclick="changeTqs();"/>
						<font color="blue">��ʾ����ʾͬ����ѧ��������ͬ������ͬ��Ԣ����.</font>
						
					</td>
				</tr>
			</tbody>
			</table>
			<div style="height:120px;overflow-x:hidden;overflow-y:auto;">
				<table id="xs_body" style="display: none;" width="97%" class="datelist tablenowrap">
					<thead>
						<tr>
							<th>
								<input name="th" type="checkbox" onclick="selectAll(this);"/>
							</th>
							<th>
								��λ��
							</th>
							<th>
								ѧ��
							</th>
							<th>
								����
							</th>
							<th>
								�꼶
							</th>
							<th>
								<bean:message key="lable.xb" />����
							</th>
							<th>
								רҵ����
							</th>
							<th>
								�༶����
							</th>
							
						</tr>
					</thead>
					<tbody id="tbody_tqs">
					</tbody>
			</table>
		</div>
			<table class="formlist" width="95%">
			<tfoot>
			      <tr style="height:22px">
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<button type="button"  name="�ύ" id="buttonSave" onclick="save();return false;">����</button>
			            <button type="button"  name="�ر�" id="buttonClose" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			 </tfoot>
			</table>
		</div>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript" defer="defer">
			
			
			 showAlert('${message }',{},{"clkFun":function(){
 				if (parent.window){
 					refershParent();
 				}
 			}});
		</script>
	</logic:present>
	
</body>
</html>
