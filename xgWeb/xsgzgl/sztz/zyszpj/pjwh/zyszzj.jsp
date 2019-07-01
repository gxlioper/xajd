<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
			<script type="text/javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/comm/ymPrompt.js" ></script>
	<script type='text/javascript' src="js/comm/message.js"></script>
	<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/sztz/zyszpj/pjwh/js/pjwh.js"></script>
		<link rel="stylesheet" href="<%=stylePath %>/js/validate/css/validate.css" type="text/css" media="all" />
		<script type="text/javascript" src="<%=stylePath %>/js/validate/talent-validate-all-min.js"></script>
		<script>
			var i=0;
			jQuery(function(){
				addTr();
			});
			function save(){
				var zxm=jQuery("input[name=zxm]");
				if(zxm.length==1){
					return alertError("����Ҫ��һ��<font color='red'>����Ŀ</font>��Ϣ��");
				}
				if(!check()){
					return alertError("�뽫��<font color='red'>*</font>����Ŀ��д������");
				}
			 	jQuery("#form").ajaxSubmit({
					url:"zyszpjwh.do?method=add&type=save",
					type:"post",
					dataType:"json",
					success:function(data){
				 		 if(data["message"]=="����ɹ���"){
				    		 showAlert(data["message"],{},{"clkFun":function(){
				    				if (parent.window){
				    					refershParent();
				    				}
				    			}});
				    	 }else{
				    		 showAlert(data["message"]);
				    		 
				    	 }
					},
					contentType:"application/x-www-form-urlencoded;charset=utf-8"
				});	
			}
			</script>
	</head>
	<html:form action="/zyszpjwh" method="post"
		 styleId="form"> 
		<input type="hidden" name="url" id="url"
			value="zyszpjwh.do?method=add">
			<input type="hidden" name="tableName" id="tableName"
				value="view_xsxxb">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="6">
								<span>ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="right" width="15%">
								<font color="red">*</font>ѧ�ţ�
								<logic:equal name="iszfgly" value="false">
									<html:hidden property="xh" value="${stuInfo.xh}"/>
									<td align="left" width="40%">
										${stuInfo.xh}
									</td>
								</logic:equal>
								<logic:equal name="iszfgly" value="true">
								<td align="left" width="40%">
									<html:text property="xh" styleId="xh" value="${stuInfo.xh}"
										readonly="true" />
	
									<button type="button" onclick="sendXx();return false;" class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</td>
								</logic:equal>
							</td>
							<td align="right" width="15%">
								������
							</td>
							<td align="left">
								${stuInfo.xm}
							</td>
						</tr>
						<tr>
							<td align="right">
								�꼶��
							</td>
							<td align="left">
								${stuInfo.nj}
							</td>
							<td align="right">
								<bean:message key="lable.xb" />��
							</td>
							<td align="left">
								${stuInfo.xymc}
							</td>
						</tr>
						<tr>
							<td align="right">
								רҵ��
							</td>
							<td align="left">
								${stuInfo.zymc}
							</td>
							<td align="right">
								�༶��
							</td>
							<td align="left">
								${stuInfo.bjmc}
							</td>
						</tr>
						<tr>
							<td align="right" width="10%">
								ѧ�꣺
							</td>
							<td align="left">
								${dqxn}
							</td>
							<td align="right">
								ѧ�ڣ�
							</td>
							<td align="left">
								${dqxq}
							</td>
						</tr>
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist" id="tbody_add">
					<thead>
						<tr>
							<th colspan="6">
								<span>ְҵ���ʻ���� </span>
								<a href="javascript:void(0);" onclick="addTr()"><font color="blue">[����]</font></a>|<a href="javascript:void(0);" onclick="delTr()"><font color="blue">[ɾ��]</font></a>
							</th>
						</tr>
					</thead>
					
						<tr align="center">
							<td>
							</td>
							<td>
								<font color="red">*</font>����Ŀ
							</td>
							<td>
								<font color="red">*</font>ʱ��
							</td>
							<td>
								<font color="red">*</font>�ص�<br/><font color="red">(��15��)</font>
							</td>
							<td>
								<font color="red">*</font>�����<br/><font color="red">(��30��)</font>
							</td>
							<td>
								<font color="red">*</font>���뼰�����<br/><font color="red">(��100��)</font>
							</td>
						</tr>
				</table>
								<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="6">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td width="15%"><font color="red">*</font>����
							<br /><font color="red">(��200��)</font>
							</td>
							<td width="85%"><html:textarea property="zpxx" rows="8" onblur="checkLen(this,200)" style="width:100%" /></td>
						</tr>
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button"  onclick="save();return false;" id="buttonSave">
									�� ��
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button"  onclick="iFClose();" id="buttonClose">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
	</html:form>
		<html:form action="/zyszpjwh" method="post"
		 styleId="form"> 
				<div style="display: none">
					<table id="addstr">
						<tr>
							<td style="width:5%">
								<input type="checkbox" id="zxm" name="zxm" value="1" style="width:100%"/>
							</td>
							<td style="width:20%">
								<html:select property="xmlbid" styleId="xmlbid"	style="width:100%">
									<html:option value=""></html:option>
									<html:options collection="xmlb" property="xmlbid" labelProperty="mc" />
								</html:select>
							</td>
							<td style="width:15%">
								<input type="text" name="sj" class="text_nor" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:92%"/>
							</td>
							<td style="width:18%">
								<input type="text" name="dd"  onblur="checkLen(this,15)" style="width:92%"/>
							</td>
							<td style="width:20%">
								<textarea name="hdnr" rows="5" cols="15"  onblur="checkLen(this,30)" style="width:95%"></textarea>
								
							</td>
							<td style="width:22%">
								<textarea name="cyjhjqk" rows="5" cols="15" onblur="checkLen(this,100)" style="width:100%"></textarea>
							</td>
						</tr>
					</table>
				</div>
				</html:form>
				<div id="input_mhcx_msg" class="hide"
								style="left: 130px;top: 85px;">
								<div class="prompcon" style="width: 250px">
									<p id="tsxx_span"></p>
								</div>
							</div>
</html>