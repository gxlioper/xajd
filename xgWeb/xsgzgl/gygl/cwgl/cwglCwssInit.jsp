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
		function loadXs(){
			
			var api = frameElement.api,W = api.opener;
			//��ѡ��ѡ��ѧ��
			var pks = W.document.getElementsByName("primarykey_checkVal");
			//��ѯ�������ݼ��� ����			
			var num = W.document.getElementById("wrzcws").value;
			//��ѯ�����ݼ��Ĳ�ѯ����
			var searchTjstr = W.document.getElementById("searchTjstr").value;

			var RowsStr="";
			var count =0;
			for (i=0; i<pks.length; i++){
	 			if(pks[i].checked){
	 				RowsStr+=pks[i].value+"!!splitOne!!";
	 				count++;
	 			}
			}
			if(count==0){//δѡ��ѧ��
				jQuery('#searchTjstr').val(searchTjstr);
				jQuery('#xhtd').html("��ǰ��ѯ����й���<font color='red'>"+num+"</font>��δ��ס���ѷ���Ĵ�λ����ִ�д�λ������ʼ������");
				if(num == 0){
					jQuery('#buttonSave').attr({disabled:'disabled'});
				}
			}else{//ѡ��ѧ��
				jQuery('#cwStr').val(RowsStr);
				jQuery('#xhtd').html("��ǰ��ѡ��<font color='red'>"+count+"</font>��δ��ס���ѷ���Ĵ�λ����ִ�д�λ������ʼ������");

			}
		}
		
		function saveTsxx(){
			saveData('gyglnew_cwgl.do?method=cwglCwssInit&doType=init','');
		}
				
		jQuery(function(){
			loadXs();
		})
		
		function refreshParent3(){
			var api = frameElement.api,W = api.opener;
			if(W == undefined){
				if(W && W.document.getElementById('search_go')){
					W.document.getElementById('search_go').click();
				}
			}else{
				if(	W && W.document.getElementById('search_go')){
					W.document.getElementById('search_go').click();		
				}
			}
			Close();
		}
	</script>
</head>
<body>
	<html:form action="/gyglnew_cwgl" method="post">	
		<input type="hidden" id="cwStr" name="cwStr" value=""/>
		<input type="hidden" id="searchTjstr" name="searchTjstr" value=""/>
		<input type="hidden" id="count" value=""/>
		<%--<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��ʼ����λ����</a>
			</p>
		</div>		
		--%>
		<div class="prompt" id="span_qsh">
	       <h3><span>��ʾ��</span></h3>
	       <p>��ʼ����λ����ʱ�����ǡ��Ƿ��ʼ����Ӧ����������ѡ���ǡ�����ô�������еĴ�λ������������δ�����<bean:message key="lable.xsgzyxpzxy" />ʱ��ϵͳ�Զ���ʼ���������������ҷ���״̬���Ϊδ����</p>
	   	</div>
		
		<div class="tab">
		<table class="formlist" width="95%">			
			<thead>
				<tr style="height:22px">
					<th colspan="2">
						<span>��ʼ����λ����</span>
					</th>
				</tr>
			</thead>			
			<tbody>
				<tr >
					<th width="16%">
						<font color="red">*</font>��ʼ����λ����				
					</th>
					<td id="xhtd">
						
					</td>
				</tr>
				<tr>
					<th width="16%">
						<font color="red">*</font>��ʼ������			
					</th>
					<td>
						<html:select property="cshlx" styleId="cshlx" >						
							<html:options collection="cshlxList" labelProperty="mc" property="dm"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th width="16%">
						<font color="red">*</font>�Ƿ��ʼ����Ӧ��������				
					</th>
					<td>
						<input type="radio" name="sfqcqsss" value="��" checked="checked"/>��
						<input type="radio" name="sfqcqsss" value="��"/>��
					</td>
				</tr>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="2"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
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
		<script type="text/javascript">
			alertInfo('����ɹ�', function(){
				refreshParent3();
			});
			
		</script>
	</logic:present>
</body>
</html>
