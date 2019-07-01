<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/check.js"></script>

		<script type="text/javascript">

		function tipsAndSave(url){
			BatAlert.showTips("���ڱ��棬���Ժ�");
			saveData(url,'');
		}
		
		function autoFillTeaInfo(cod){
			if(cod == 13){
				var url = $('url').value;
				document.forms[0].action = url;
				document.forms[0].submit();
			}
		}
	
		
		//����ѧ��������Ϣ
		function save(){
			
			var xh=jQuery("#xh").val();
			
			var blog= true;
			if(xh==""){
				alertInfo("ѧ��Ϊ�����ֶβ���Ϊ�գ�",function(tag){
				
				});
				
				blog= false;
				
			}
			
			//����ǰ��ʾ��Ϣ
			if(blog){
				
				confirmInfo("�ò������ᱣ�����޸���Ϣ���Ƿ������",function(tag){
					//�ж��Ƿ�ѡ��ȷ������ť
					if(tag=="ok"){
					
						//����һ��json����
						var parameter={};
						
						//ָ����ȡ�Ŀؼ����ͣ�����ѭ��
						var hid_obj=jQuery("input,select").each(function(){
							
							//��ȡ���ؼ�name
							var name=jQuery(this).attr("name");
							//����json����
							parameter[name]=escape(jQuery(this).val());
						});
						
						//����URL
						var url = "xsxx_qtxx_ajax.do?method=save";
						
						//------------AJAX���� begin -------------
						jQuery.ajaxSetup({async:false});
							jQuery.post(url,
							parameter,
							function(result){
								
								$("divWaiting").style.display="none";
								$("divDisable").style.display="none";
								alertInfo(result);
							}
						);
						
						jQuery.ajaxSetup({async:true});
						//------------AJAX���� end -------------
						
						
					}else {
					
						return false;
					}
				});
			}
		}
		
		//����ѧ��������Ϣ
		function update(){
			
			//����ǰ��ʾ��Ϣ
			confirmInfo("�ò������ᱣ�����޸���Ϣ���Ƿ������",function(tag){
				//�ж��Ƿ�ѡ��ȷ������ť
				if(tag=="ok"){
				
					//����һ��json����
					var parameter={};
					
					//ָ����ȡ�Ŀؼ����ͣ�����ѭ��
					var hid_obj=jQuery("input,select").each(function(){
						
						//��ȡ���ؼ�name
						var name=jQuery(this).attr("name");
						//����json����
						parameter[name]=escape(jQuery(this).val());
					});
					
					//����URL
					var url = "xsxx_qtxx_ajax.do?method=update";
					
					//------------AJAX���� begin -------------
					jQuery.ajaxSetup({async:false});
						jQuery.post(url,
						parameter,
						function(result){
							
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							alertInfo(result);
						}
					);
					
					jQuery.ajaxSetup({async:true});
					//------------AJAX���� end -------------
					
					
				}else {
				
					return false;
				}
			});
		}
	</script>
	</head>
	<body>
		<html:form action="/xsxx_qtxx" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<input type="hidden" name="xmdm" value="${xmdm}" />
			<input type="hidden" name="url" id="url"
				value="/xgxt/xsxx_qtxx.do?method=xsqtxxDetail&doType=add" />
			<input type="hidden" name="tableName" id="tableName"
				value="view_xsjbxx" />
			<input type="hidden" name="va" id="va" value="${xmdm}" />
			<div class="tab"
				style="width:100%;height:450px;overflow-x:hidden;overflow-y:auto;">
				<table class="formlist" width="93%">

					<thead onclick="hiddenMk('mk_xsxx')">
						<tr>
							<th colspan="4" style="cursor:hand">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="mk_xsxx">
						<tr>
							<logic:notEqual name="doType" value="add">
								<th width="20%">
									<font color="red">*</font>ѧ��
								</th>
								<td width="30%">
									<input type="text" name="xh" id="xh" value="${rs.xhv }"
										readonly="readonly" />

								</td>
							</logic:notEqual>
							<logic:equal name="doType" value="add">
								<th width="20%">
									<font style="color: red">*</font>ѧ��
								</th>
								<td width="30%">
									<input type="text" name="xh" id="xh" readonly="readonly"
										value="${rs.xhv }"
										onchange="checkXhExists($('getStuInfo').value);"
										onkeypress="autoFillTeaInfo(event.keyCode)" />
									<button type="button" onclick="sendXx();return false;" class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</td>
							</logic:equal>
							<th width="20%">
								����
							</th>
							<td width="30%">
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th>
								�꼶
							</th>
							<td>
								${rs.nj }
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								${rs.xymc }
							</td>
						</tr>
						<tr>
							<th>
								רҵ
							</th>
							<td>
								${rs.zymc }
							</td>
							<th>
								�༶
							</th>
							<td>
								${rs.bjmc }
							</td>
						</tr>
					</tbody>
					<thead onclick="hiddenMk('mk_lhbx')">
						<tr>
							<th colspan="4" style="cursor:hand">
								<span>���ϰ�ѧ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="mk_lhbx">
						<tr>
							<th>
								��ְԺУ
							</th>
							<td>
								<html:text name="rs" property="zd1" styleId="zd1" maxlength="50" />
							</td>
							<th>
								�ɿ��ɼ�
							</th>
							<td>
								<html:text name="rs" property="zd2" styleId="zd2" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th>
								��ְԺУ
							</th>
							<td>
								<html:text name="rs" property="zd3" styleId="zd3" maxlength="50"/>
							</td>
							<th>
								¼ȡ�ɼ�
							</th>
							<td>
								<html:text name="rs" property="zd4" styleId="zd4" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th>
								¼ȡ���
							</th>
							<td>
								<html:text name="rs" property="zd5" styleId="zd5" maxlength="50"/>
							</td>
							<th>
								¼ȡרҵ
							</th>
							<td>
								<html:text name="rs" property="zd6" styleId="zd6" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th>
								��ҵ���
							</th>
							<td>
								<html:text name="rs" property="zd7" styleId="zd7" maxlength="50"/>
							</td>
							<th>
								¼ȡ���
							</th>
							<td>
								<html:text name="rs" property="zd8" styleId="zd8" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th>
								��ְ�༶
							</th>
							<td>
								<html:text name="rs" property="zd23" styleId="zd23" maxlength="50"/>
							</td>
							<th>
								��ְ����Ա
							</th>
							<td>
								<html:text name="rs" property="zd24" styleId="zd24" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th>
								˫��༶
							</th>
							<td>
								<html:text name="rs" property="zd25" styleId="zd25" maxlength="50"/>
							</td>
							<th>
								˫�縨��Ա
							</th>
							<td>
								<html:text name="rs" property="zd26" styleId="zd26" maxlength="50"/>
							</td>
						</tr>
						</tbody>
						<thead onclick="hiddenMk('mk_zhxx')">
							<tr>
								<th colspan="4" style="cursor:hand">
									<span>�ۺ���Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody id="mk_zhxx">
						<tr>
							<th>
								��ѧ��
							</th>
							<td>
								<html:select name="rs" property="zd9" styleId="zd9">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
							<th>
								�������
							</th>
							<td>
								<html:text name="rs" property="zd10" styleId="zd10" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th>
								����ѧУ
							</th>
							<td>
								<html:text name="rs" property="zd11" styleId="zd11" maxlength="50"/>
							</td>
							<th>
								����רҵ
							</th>
							<td>
								<html:text name="rs" property="zd12" styleId="zd12" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th>
								��ѵ
							</th>
							<td>
								<html:select name="rs" property="zd13" styleId="zd13">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
							<th>
								���
							</th>
							<td>
								<html:select name="rs" property="zd14" styleId="zd14">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								ѧ��֤
							</th>
							<td>
								<html:select name="rs" property="zd15" styleId="zd15">
									<html:option value=""></html:option>
									<html:option value="�ѽ�">�ѽ�</html:option>
									<html:option value="δ��">δ��</html:option>
								</html:select>
							</td>
							<th>
								���ͨ
							</th>
							<td>
								<html:select name="rs" property="zd16" styleId="zd16">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td>
								<html:select name="rs" property="zd17" styleId="zd17">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
							<th>
								��������ʱ��
							</th>
							<td>
								 <html:text name="rs"  property="zd18" styleId="zd18" 
								onclick="return showCalendar('zd18','y-mm-dd');" 
								onblur="dateFormatChg(this)" readonly="true" />
								
							</td>
						</tr>
						<tr>
							<th>
								����Ʊ�ݺ�
							</th>
							<td>
								<html:text name="rs" property="zd19" styleId="zd19" maxlength="50"/>
							</td>
							<th>
								��Ʒ����
							</th>
							<td>
								<html:select name="rs" property="zd20" styleId="zd20">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								��Ʒ����ʱ��
							</th>
							<td>
								 <html:text name="rs"  property="zd21" styleId="zd21" 
								onclick="return showCalendar('zd21','y-mm-dd');" 
								onblur="dateFormatChg(this)" readonly="true" />
							</td>
							<th>
								��ƷƱ�ݺ�
							</th>
							<td>
								<html:text name="rs" property="zd22" styleId="zd22" maxlength="50"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<table class="formlist" width="93%">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"
								<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<logic:equal value="add" name="doType">
									<button type="button" name="�ύ" id="buttonSave" onclick="save();return false;">
										�� ��
									</button>
								</logic:equal>
								<logic:equal value="update" name="doType">
									<button type="button" name="�ύ" onclick="update();return false;">
										�� ��
									</button>
								</logic:equal>
								<button type="button" name="�ر�" onclick="window.close();return false;">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</html:form>
		<%@include file="/comm/other/tsxx.jsp"%>
	</body>
</html>
