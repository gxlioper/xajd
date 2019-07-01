<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.*" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<style type="text/css">
             .note{
                position:absolute;line-height:15px;padding:3px 5px;top:20px;
             }
		</style>
		<script type='text/javascript'>
		var sl;

		function yc(obj){
			var div = jQuery(obj).siblings("div").eq(0);
			jQuery(div).css("display","none");
		}

		function xs(obj){			
			var zhi = jQuery(obj).val();
			var div = jQuery(obj).siblings("div").eq(0);
			if(zhi == null || zhi == ""){
				jQuery(div).css("display","block");
			}else{
				jQuery(div).css("display","none");
			}
		}
		
		function saveFdgl(type) {
			var flag = false;
			var fdrq = "";
			var fdnr = "";
			var fdbz = "";
			jQuery("input:text[name=rq]").each(function(index){
				if(flag){
					fdrq += ",";
				}else{
					flag = true;
				}
				fdrq += jQuery(this).val();
			});
			flag = false;
			jQuery("input:text[name=nr]").each(function(index){
				if(flag){
					fdnr += ",";
				}else{
					flag = true;
				}
				fdnr += jQuery(this).val();
			});
			flag = false;
			jQuery("textarea[name=bz]").each(function(index){
				if(flag){
					fdbz += ",";
				}else{
					flag = true;
				}
				fdbz += jQuery(this).val();
			});
			jQuery("#fdrq").val(fdrq);
			jQuery("#fdnr").val(fdnr);
			jQuery("#fdbz").val(fdbz);			
			if(fdrq.replace(",","")=="" || fdrq==null || fdnr.replace(",","")=="" || fdnr==null){
				showAlertDivLayer("��������дһ��������¼��");
				return false;
			}
			var url = "zzyrxmglbfdgl.do?method=addBfdgljl&type="+type;
			ajaxSubFormWithFun("bfdglForm", url, function(data) {
				if (data["message"] == "����ɹ���") {
					showAlert(data["message"], {}, {
						"clkFun" : function() {
							if (parent.window) {
								refershParent();
							}
						}
					});
				} else {
					showAlert(data["message"]);
				}
			});
			
		}
		function delThis(obj){
			jQuery(obj).parents("tr[name='fdnr']").remove();
			var id=jQuery(obj).parents("tr[name='fdnr']").find("input[id='id']").val();
			if(id != ""){
			var url = "zzyrxmglbfdgl.do?method=delFdjl";
				jQuery.post(url, {
					id : id
				}, function(data) {
					//
				}, 'json');
			}
		}
		
		function addConMoreUpdateTr(flszid) {
			sl++;
			var trHtml = '<tr name="fdnr"><td><input type="hidden" name="id" id="id" value=""/><input type="text" name="rq" maxlength="10" '+
				'onfocus="WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss\'})" /></td>'+
				'<td><input type="text" name="nr" maxlength="100" /></td>'+
				'<td><div style="position:relative;"><textarea name="bz" rows="6" onfocus="yc(this);" onblur="xs(this);checkLen(this,500);"></textarea><div id="note_'+sl+'" class="note"><font color="#777">1�����θ������</font></br><font color="#777">2�����θ�����������ȣ������⡢���⡢�Ƚ����⡢�ǳ����⣩</font></br></div></div></td>'+
				'<td><button type="button" id="but" onclick="delThis(this);">ɾ��</button></td></tr>';
			jQuery("#headTr").append(trHtml);
		}

			jQuery(function(){
				sl = jQuery("[name='fdnr']").length;			
			})
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/zzyrxmglbfdgl" method="post" styleId="bfdglForm" onsubmit="return false;">
			<html:hidden property="fdxxid"/>
			<input type="hidden" name="fdlx" value="1"/>
			<input type="hidden" name="fdrq" id="fdrq" value=""/>
			<input type="hidden" name="fdnr" id="fdnr" value=""/>
			<input type="hidden" name="fdbz" id="fdbz" value=""/>
			<input type="hidden" name="fdjssj" id="fdjssj" value="" />
			<input type="hidden" name="gs" id="gs" value="" />
			<input type="hidden" name="fddd" id="fddd" value="" /> 
			<input type="hidden" name="fdpj" id="fdpj" value="" />  
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tr>
						<th width="20%">��������</th>
						<td width="30%">${rs.fdrxm }</td>
						<th width="20%">������ϵ�绰</th>
						<td width="30%">${rs.fdrlxdh }</td>
					</tr>
					<tr>
						<th>ѧԱ����</th>
						<td>${rs.bfdrxm }</td>
						<th>ѧԱ��ϵ�绰</th>
						<td>${rs.bfdrlxdh }</td>
					</tr>
					<tr>
						<th>������Ŀ</th>
						<td colspan="3">${rs.fdkm }</td>
					</tr>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>������¼<a class='name' href='javascript:;' onclick="addConMoreUpdateTr();">����һ��</a></span>
							</th>
						</tr>
					</thead>
					<tbody id="headTr">
						<tr>
							<td >
								<span class="red">*</span>��������
							</td>
							<td >
								<span class="red">*</span>��������
							</td>
							<td>
								��ע
							</td>
							<td>
								����
							</td>
						</tr>
						<logic:notEqual name="size" value="0">
							<logic:iterate name="fdjlList" id="f" indexId="i">
							<tr name="fdnr">
								<td><input type="hidden" name="id" id="id" value="${f.id }"/><input type="text" name="rq" value="${f.fdrq }" maxlength="10" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></td>
								<td><input type="text" name="nr" value="${f.fdnr }" maxlength="100" /></td>
								<td>
									<div style="position:relative;">
										<textarea name="bz"  rows="6" onfocus="yc(this);" onblur="xs(this);checkLen(this,500);">${f.fdbz }</textarea>
										<div id="note_${i}" class="note" style="display: none">
											<font color="#777">1�����θ������</font></br>
											<font color="#777">2�����θ�����������ȣ������⡢���⡢�Ƚ����⡢�ǳ����⣩</font></br>
										</div>
								</td>
								<td><button type="button" id="but" onclick="delThis(this);">ɾ��</button></td>
				      		</tr>
							</logic:iterate>
						</logic:notEqual>
						<logic:equal name="size" value="0">
							<tr name="fdnr">
								<td><input type="hidden" name="id" id="id" value=""/><input type="text" name="rq" maxlength="10" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></td>
								<td><input type="text" name="nr" maxlength="100" /></td>
								<td>
									<div style="position:relative;">
										<textarea name="bz" rows="6" onblur="xs(this);checkLen(this,500);" onfocus="yc(this);"></textarea>
										<div id="note" class="note">
											<font color="#777">1�����θ������</font></br>
											<font color="#777">2�����θ�����������ȣ������⡢���⡢�Ƚ����⡢�ǳ����⣩</font></br>
										</div>
								</td>
								<td><button type="button" id="but" onclick="delThis(this);">ɾ��</button></td>
				      		</tr>
						</logic:equal>
					</tbody>
				 </table>			
				</div>
			  <div style="height:35px"></div>   
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
									<button type="button" onclick="saveFdgl('save');">
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
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

