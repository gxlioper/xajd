<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
			<script type="text/javascript" src="js/calendar/calendar.js"></script>
			<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
			<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
	<script language="javascript">
	function saveTsxx(){
		var hjrs=jQuery("#hjrs").val();
		if(hjrs ==0){

			showAlertDivLayer("��������У������ѧ��!",{},{"clkFun":function(){
					Close();
				}
			});
			return false;
		}
		if(!check("tsyy-tssj-xn-xq")){
			return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		}
		
		var ts="ȷ���ԡ���У����ռ��λ����<font style='font-size: 15px;font-weight: 600;color:red;'>"+hjrs+"</font>��ѧ������<font style='font-size: 15px;font-weight: 600;color:red;'>����</font>������";
		showConfirmDivLayer(ts, {
			"okFun" : function() {
			var url="gyglnew_zsxxgl.do?method=plLx&doType=pllx";
			 	jQuery("#form").ajaxSubmit({
					url:url,
					type:"post",
					dataType:"json",
					success:function(data){
						 var message=data["message"];
						 if(message=="����ɹ�"){
							 message="���޳ɹ�!";
						 }else{
							 message="����ʧ��!";
						 }	
			    		 showAlert(message,{},{"clkFun":function(){
			    				if (parent.window){
			    					refershParent();
			    				}
			    		}});
					},
					contentType:"application/x-www-form-urlencoded;charset=utf-8"
				});	
			}
		});
	}
	
	/**
	 * ��֤�Ƿ���ڿ���
	 * @param ids Ҫ��֤�Ŀؼ�id "-"�ָ�
	 * @return
	 */
	function check(ids){
		var id=ids.split("-");
		for(var i=0;i<id.length;i++){
			var lddm=jQuery("#"+id[i]).val();
			if(lddm==null||""==lddm){
				//alert(id[i]);
				return false;
			}
		}
		return true;
	}
	jQuery(function(){
	     var gridSetting = {
					caption:"����У��������Ϣ",
					pager:"pager",
					url:"gyglnew_zsxxgl.do?method=plLx&doType=query",
					colList:[
					   {label:'njxyzy',name:'njxyzy',index:'njxyzy',key:true,hidden:true},			
					   {label:'�꼶',name:'nj', index: 'nj'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
					   {label:'רҵ',name:'zymc', index: 'zymc'},
					   {label:'zydm',name:'zydm', index: 'zydm',hidden:true},
					   {label:'xydm',name:'xydm', index: 'xydm',hidden:true},
					   {label:'��Уδ����ѧ����',name:'dlxrs', index: 'dlxrs',formatter:rsLink}
					],
					sortname: "nj",
				 	sortorder: "desc",
				 	multiselect:false,
					rowNum:5,
					pageList:[5,10,50,100,200]					 	
				}
			jQuery("#dataTable").initGrid(gridSetting);
	});
	function rsLink(cellValue,rowObject){
		return "<a href='javascript:void(0);' class='name' onclick='pllxclj(\""+rowObject["nj"]+"\",\""+rowObject["xydm"]+"\",\""+rowObject["zydm"]+"\");'>"+rowObject["dlxrs"]+"</a>";
	}
	function pllxclj(nj,xydm,zydm){
		showDialog("��У������ѧ����Ϣ",550,400,"gyglnew_zsxxgl.do?method=pllxview&nj="+nj+"&xydm="+xydm+"&zydm="+zydm);
   	}
	</script>
	</head>
	<body>
		<html:form action="/gyglnew_zsxxgl" styleId="form"
			method="post">
			<input type="hidden" id="hjrs" value="${hjrs}"/>
			<div class="prompt" id="span_qsh">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					�ԡ���У����ռ��λ����ѧ�������������޲���
					</br>
					����ʱ�������Ƿ��ʼ����λ������ѡ���ǡ�����ϵͳ�Զ���ʼ����λ��������λ����״̬���Ϊδ���䡣
				</p>
				<a class="close" onclick="this.parentNode.style.display='none';" title="����"></a>
			</div>

			<div class="tab">
				<table class="formlist" width="95%">
					<thead>
						<tr style="height: 22px">
							<th colspan="4">
								<span>����У��������Ϣ</span>
							</th>
						</tr>
					</thead>
				</table>
				
				<table id="dataTable"></table>
				<div id="pager"></div>
				<table class="dateline" width="100%">
					<tbody>
						<tr style="font-size: 15px;font-weight: 600;" align="right">
							<td colspan="4">
								<span style="color: red;margin-right: 30px;">�ϼ�:&nbsp;&nbsp;${hjrs}��</span>
							</td>
						</tr>
					</tbody>
				</table>
				<table class="formlist" width="95%">
					<thead>
						<tr style="height: 22px">
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<span class="red">*</span>����ԭ��
							</th>
							<td>
								<html:select property="tsyy" styleId="tsyy" >						
									<html:options collection="tsyyList" labelProperty="tsyymc" property="tsyydm"/>
								</html:select>
							</td>
							<th width="20%">
								<span class="red">*</span>����ʱ��
							</th>
							<td>
								<html:text property="tssj" styleId="tssj"
									onkeypress="onlyBackSpace(this,event);"
									onclick="return showCalendar(this.id,'yyyy-MM-dd')" style="width:100px;"></html:text>
							</td>
						</tr>
						<tr>
							<th align="right">
								<span class="red">*</span>ѧ��/ѧ��
							</th>
							<td align="left">
								<html:select property="xn" styleId="xn" disabled="false">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
								</html:select>
								<html:select property="xq" styleId="xq" disabled="false" >
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
								</html:select>
							</td>
							<th width="20%">
								<span class="red">*</span>�Ƿ��ʼ����λ����
							</th>
							<td>
								<logic:equal value="xx" name="userStatus" scope="session">
									<input type="radio" name="sfqccwss" value="��" checked="checked" />��
									&nbsp;
									<input type="radio" name="sfqccwss" value="��" />��
								</logic:equal>
								<logic:notEqual value="xx" name="userStatus" scope="session">
									<input type="radio" name="sfqccwss" value="��"
										disabled="disabled" />��
										&nbsp;
									<input type="radio" name="sfqccwss" value="��" checked="checked"
										disabled="disabled" />��
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th>
								��ע
								<br />
								<font color="red">(��1000��)</font>
							</th>
							<td colspan="3">
								<html:textarea property='bz' style="width:95%" styleId="bz"  onblur="checkLen(this,1000);"
									rows='3' />
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" name="�ύ" id="buttonSave" onclick="saveTsxx();">
										�ύ
									</button>
									<button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">
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
