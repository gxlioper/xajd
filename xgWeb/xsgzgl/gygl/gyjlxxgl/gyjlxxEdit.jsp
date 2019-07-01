<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	
	<script	type="text/javascript">
	function loadXsxx(){
		jQuery.post('gyglnew_gyjlgl.do?method=loadXsxx',{xh:jQuery('#xh').val()},function(data){
			var xh = data.xh;
			if(xh!=undefined ){
				var xm = data.xm;
				var xymc = data.xymc;
				var zymc = data.zymc;
				var bjmc = data.bjmc;
											
				var option = "<tr><input type='hidden' name='pk_xh' value='"+xh+"'/><td align='right'>" + xh + 
							"</td><td align='right'>" + xm + "</td><td>" + xymc + 
							"</td><td align='right'>" + zymc + "</td><td align='right'>" + bjmc + "</td></tr>"
				
				jQuery('#xsxx').append(option);		
				jQuery('#xstr').css({display:''});	
			}else{
				jQuery('#xstr').css({display:'none'});	
			}					
		},'json');
	}
	function getJllbList(obj){
		jQuery.post('gyglnew_gyjlgl.do?method=getJllbList',{jldldm:obj.value},function(data){
				var option = "<option value=''>--��ѡ��������--</option>";
				for(var i = 0; i < data.length; i++){
					option +="<option value='"+data[i].jllbdm+"'>"+data[i].jllbmc+"</option>";
				}
				jQuery('#jllbdm').empty().append(option);			
		},'json');
	}	
		function loadXs(){
			//��ѡ��ѡ��ѧ��
			var pks = window.dialogArguments.document.getElementsByName("primarykey_checkVal");
			var xhs = window.dialogArguments.document.getElementsByName("xh");
			//��ѯ�������ݼ��� ����			
			var num = window.dialogArguments.document.getElementById("num").value;
			//��ѯ�����ݼ��Ĳ�ѯ����
			var searchTjstr = window.dialogArguments.document.getElementById("searchTjstr").value;
			
			var RowsStr="";
			var count =0;
			for (i=0; i<pks.length; i++){
	 			if(pks[i].checked){
	 				RowsStr+=xhs[i].value+"!!splitOne!!";
	 				count++;
	 			}
			}
			if(count==0){//δѡ��ѧ��
				jQuery('#searchTjstr').val(searchTjstr);
				jQuery('#xhtd').html("��ǰ��ѯ����й���<font color='red'>"+num+"</font>��ѧ������ִ��Υ�Ͳ���");
				if(num == "1"){//�����ݼ���һ�����ݣ�����ѧ����Ϣ
					jQuery('#xhstr').val(xhs[0].value);
					jQuery('#xh').val(xhs[0].value);
					loadXsxx();
				}
				if(num == 0){
					jQuery('#buttonSave').attr({disabled:'disabled'});
				}
			}else{//ѡ��ѧ��
				jQuery('#xhstr').val(RowsStr);
				jQuery('#xhtd').html("��ǰ��ѡ��<font color='red'>"+count+"</font>��ѧ������ִ��Υ�Ͳ���");
				if(count=="1"){//ѡ��һ��ѧ��������ѧ����Ϣ
					jQuery('#xh').val(RowsStr.replace("!!splitOne!!",""));
					loadXsxx();
				}
			}
		}
		
		function save(){
			if(jQuery('#wjsj').val()==""){
				alertInfo("����дΥ��ʱ�䣡");
				return false;
			}
			if(jQuery('select[name=jldldm]').val()==""||jQuery('select[name=jllbdm]').val()==""){
				alertInfo("��ѡ��Υ�����ͣ�");
				return false;
			}
			saveData('gyglnew_gyjlgl.do?method=gyjlxxEdit&doType=save','');
		}
		
		jQuery(function(){
			loadXs();
		})

		
	</script>
</head>
<body>
	<html:form action="/gyglnew_gyjlgl" method="post">	
		<input type="hidden" id="xh" name="xh"��value=""/>
		<input type="hidden" id="xhstr" name="xhstr" value=""/>
		<input type="hidden" id="searchTjstr" name="searchTjstr" value=""/>
		<input type="hidden" id="count" value=""/>
		
		<div class="tab">
		<table class="formlist" width="95%">			
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>Υ����Ϣ</span>
					</th>
				</tr>
			</thead>			
			<tbody>
				<tr >
					<th width="16%">
						<font color="red">*</font>Υ�Ͳ���˵��				
					</th>
					<td colspan="3" id="xhtd">
						
					</td>
				</tr>
				<tr id="xstr" style="display: none">
					<th width="16%">
						<font color="red">*</font>Υ��ѧ����Ϣ				
					</th>
					<td colspan="3" >
						<table id="xsxx" style="width: 100%">
							<tr>
								<th>ѧ��</th><th>����</th><th><bean:message key="lable.xsgzyxpzxy" /></th>
								<th>רҵ</th><th>�༶</th>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<th width="16%">
						<font color="red">*</font>Υ��ʱ��				
					</th>
					<td colspan="3">
						<html:text property="wjsj" styleId="wjsj" onkeypress="onlyBackSpace(this,event);"
							onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm:ss')" ></html:text>
					</td>
				</tr>
				<tr>
					<th width="16%">
						<font color="red">*</font>Υ������				
					</th>
					<td>
						<html:select property="jldldm" styleId="jldldm" onchange="getJllbList(this)">
							<html:option value="">--��ѡ����ɴ���--</html:option>
							<html:options collection="jldlList" labelProperty="jldlmc" property="jldldm"/>
						</html:select>
					</td>
					<td colspan="2">
						<html:select property="jllbdm" styleId="jllbdm" >
							<html:option value="">--��ѡ��������--</html:option>	
							<html:options collection="jllbList" labelProperty="jllbmc" property="jllbdm"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						��ע
						<br /><font color="red">(������1000����)</font>
					</th>
					<td colspan="3">
						<html:textarea property='bz' style="width:95%" styleId="bz" rows='3'/>
					</td>
				</tr>
			</tbody>
			<tfoot>
			      <tr>
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
		<script type="text/javascript">
			alertInfo('����ɹ�', function(){
				refreshParent2();
			});
			
		</script>
	</logic:present>
</body>
</html>
