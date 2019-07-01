<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type='text/javascript' src="js/uicomm.js"></script>
	<script type='text/javascript' src="js/String.js"></script>
	
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	
	<script	type="text/javascript">
	//ѧ���춯����ҵ��������޵�ҳ�棬���������Ի�
	
	function loadXsxx(){
		var xh=jQuery('#xh').val();
		if(""==xh||null==xh){
			xh=jQuery('#xhstr').val();
		}
		jQuery.post('gyglnew_cwgl.do?method=loadXsxx',{xh:xh},function(data){
			var xh = data.xh;
			if(xh!=undefined ){
				
				var xm = data.xm;
				var xb = data.xb;
				var ldmc = data.ldmc;
				var qsh = data.qsh;
				var cwh = data.cwh;
				var xymc = data.xymc;
				var nj = data.nj;
				var option = "<tr><input type='hidden' name='pk_xh' value='"+xh+"'/><td align='left'>" + xh + 
							"</td><td align='left'>" + xm + "</td><td align='left'>" + xb + "</td><td align='left'>" + nj + "</td><td>" + xymc + 
							"</td><td align='left'>" + ldmc + "</td><td align='left'>" + qsh + "</td><td align='left'>" + cwh + 
							"</td></tr>"
				
				jQuery('#xsxx').append(option);		
				jQuery('#xstr').css({display:''});	
			}else{
				jQuery('#buttonSave').css({display:'none'});	
				jQuery('#czsm').append("��ѧ����������Ϣ");	
			}					
		},'json'); 
	}
		
	function loadXs(){
		var xh=jQuery('#xh').val();
		var xhstr=jQuery('#xhstr').val();
		var count =jQuery('#count').val();
		
		if(""!=xh&&null!=xh){
			loadXsxx();
			return false;
		}
		jQuery('#xhtd').html("��ǰ��ѡ��<font color='red'>"+count+"</font>��ѧ������ִ�����޲���");
		if(count=="1"){//ѡ��һ��ѧ��������ѧ����Ϣ
			loadXsxx();
		}
	}
		
	function saveTsxx(){
		var xn = jQuery('#xn').val();
		if(!check("tsyy-tssj-xn-xq")){
			return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
		}else{
			jQuery("#buttonSave").attr({disabled:'disabled'});
			saveData('gyglnew_cwgl.do?method=cwglPlts2&doType=save','');
		}
	} 
			
	jQuery(function(){
		loadXs()
		
	})
		
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
		
		function refershParent1(){
	
			if(frameElement.api){
				var api = frameElement.api,W = api.opener;
				jQuery(W.document).find('#search_go').click();
				closeDialog();
			} else {
				jQuery(parent.window.document).find('#search_go').click();
				iFClose();
			}
		}
	</script>
</head>
<body >
	<html:form action="/gyglnew_cwgl" method="post">	
		<input type="hidden" id="xh" name="xh" value="${xh}"/>
		<input type="hidden" id="xhstr" name="xhstr" value="${shstr}"/>
		<input type="hidden" id="searchTjstr" name="searchTjstr" value="${searchTjstr}"/>
		<input type="hidden" id="count" value="${count}"/>
		<%--<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>������Ϣ����</a>
			</p>
		</div>		
		--%>
		<div class="prompt" id="span_qsh">
	       <h3><span>��ʾ��</span></h3>
	       <p>����ʱ�����ǡ��Ƿ��ʼ����λ������ѡ���ǡ���ϵͳ�Զ���ʼ����λ����������λ����״̬���Ϊδ����</p>
	   	</div>
		
		<div class="tab">
		<table class="formlist" width="95%">			
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>������Ϣ:</span>
					</th>
				</tr>
			</thead>			
			<tbody>
				<tr >
					<th width="16%">
						<span class="red">*</span>���޲���˵��				
					</th>
					<td colspan="3" id="xhtd">
						<span id="czsm"></span>
					</td>
				</tr>
				<tr id="xstr" style="display: none">
					<th width="16%">
						<span class="red">*</span>����ѧ����Ϣ				
					</th>
					<td colspan="3" >
						<table id="xsxx" style="width: 100%">
							<tr>
								<th style="text-align: left">ѧ��</th>
								<th style="text-align: left">����</th>
								<th style="text-align: left">�Ա�</th>
								<th style="text-align: left">�꼶</th>
								<th style="text-align: left"><bean:message key="lable.xsgzyxpzxy" /></th>
								<th style="text-align: left">¥��</th>
								<th style="text-align: left">����</th>
								<th style="text-align: left">��λ</th>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<th width="16%">
						<span class="red">*</span>����ԭ��				
					</th>
					<td>
						<html:select property="tsyy" styleId="tsyy" >						
							<html:options collection="tsyyList" labelProperty="tsyymc" property="tsyydm"/>
						</html:select>
					</td>
					<th width="16%">
						<span class="red">*</span>����ʱ��				
					</th>
					<td>
						<html:text property="tssj" styleId="tssj" onkeypress="onlyBackSpace(this,event);" style="width:100px;"
							onclick="return showCalendar(this.id,'yyyy-MM-dd','','',320,10)" ></html:text>
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
					<th width="16%">
						<span class="red">*</span>��λ�Ƿ��ʼ��			
					</th>
					<td>
						<logic:equal value="xx" name="userStatus" scope="session">
							<input type="radio" name="sfqccwss" value="��" checked="checked"/>��
							<input type="radio" name="sfqccwss" value="��" />��
						</logic:equal>
						<logic:notEqual value="xx" name="userStatus" scope="session">
							<input type="radio" name="sfqccwss" value="��" disabled="disabled"/>��
							<input type="radio" name="sfqccwss" value="��" checked="checked" disabled="disabled"/>��
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<th>
						��ע
						<br /><font color="red">(��1000��)</font>
					</th>
					<td colspan="3">
						<html:textarea property='bz' style="width:95%" styleId="bz" rows='4'/>
					</td>
				</tr>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<button type="button"  name="�ύ" id="buttonSave" onclick="saveTsxx();">����</button>
			            <button type="button"  name="�ر�" id="buttonClose" onclick="Close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">0
		showAlert("����ɹ�",{},{"clkFun":function(){
				if (parent.window){
					refershParent1();
				}
			}});
		</script>
	</logic:present>
</body>
</html>
