<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/AjaxFunction.js"></script>
	
	<script	type="text/javascript">
				
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
					return false;
				}
			}
			return true;
		}
		
		function bycl(){
			var yxzxss = jQuery("#yxzxss").val();
			var selected = jQuery("#selected").val();
			if("0" == yxzxss){
				return showAlert("������ѡ��һ��ѧ����");;
			}
			if(!check("byny")){
				return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
			}
			var url = "";
			if(selected==""){
				url += "&values="+jQuery("#values").val();
			}else{

				//�߼���ѯ
				url +="&searchTj=";
				url +=jQuery("#searchTj").val();
				url +="&searchTjz=";
				url +=jQuery("#searchTjz").val();
				url +="&mhcx_lx=";
				url +=jQuery("#mhcx_lx").val();
				url +="&searchLx=";
				url +=jQuery("#searchLx").val();

				//ģ����ѯ
				url +="&input_mhcx=";
				url +=jQuery("#input_mhcx").val();
				url +="&path=";
				url +=jQuery("#path").val();			
				url +="&selected=all";
			}

			confirmInfo("��ȷ����<font color='red'>"+yxzxss +"</font>��ѧ�����С���ҵ������?",function(ty){
				if(ty=="ok"){
			      ajaxSubFormWithFun("byclForm","bycl.do?method=bycl&type=save"+url,function(data){
				    	 if(data["message"]=="����ɹ���"){
				    		 if("12309"==jQuery("#xxdm").val()){  //��ҵ��������ޣ��������ѧԺ���Ի�
			    			 	confirmInfo("����ɹ����Ƿ�ͬʱ�������޴���",function(ty){
					    			if(ty=="ok"){
					    				showDialog("����", 730, 365, "gyglnew_cwgl.do?method=cwglPlts2"+url);
					    			}
					    			if (parent.window){
				    					refershParent();
				    				}
					    		});
				    		 }else{
				    			 if (parent.window){
				    				refershParent();
				    			}
				    		 }
				    	 }else{
				    		 showAlert(data["message"]);
				    	 }
					});
				}
			});
				
		}
	</script>
</head>
<body >
	<html:form action="/bycl" method="post" styleId="byclForm" onsubmit="return false;">
		<input type="hidden" id="selected" name="selected" value="${byclForm.selected}"/>
		<input type="hidden" id="values" name="values" value="${byclForm.values }"/>
		<input type="hidden" id="yxzxss" name="yxzxss" value="${yxzxss }"/>
		<input type="hidden" id="searchTj" name="searchTj" value="${byclForm.searchTj }"/>
		<input type="hidden" id="searchTjz" name="searchTjz" value="${byclForm.searchTjz }"/>
		<input type="hidden" id="mhcx_lx" name="mhcx_lx" value="${byclForm.mhcx_lx }"/>
		<input type="hidden" id="searchLx" name="searchLx" value="${byclForm.searchLx }"/>
		<input type="hidden" id="input_mhcx" name="input_mhcx" value="${byclForm.input_mhcx }"/>
		<input type="hidden" id="path" name="path" value="${byclForm.path }"/>
		<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}"/>
		
		<div style='tab;width:100%;height:180px;overflow-x:hidden;overflow-y:auto;'>
		<table class="formlist" width="95%">			
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>��ҵ������Ϣ</span>
					</th>
				</tr>
			</thead>			
			<tbody>
				<tr >
					<th width="30%">
						��ҵ����˵��				
					</th>
					<td colspan="3" id="xhtd">
						��ǰ��ѡ��ѧ��<span style="color:red;">${yxzxss }</span>��
					</td>
				</tr>
				<tr>
					<th width="30%">
						<span class="red">*</span>��ҵ����
					</th>
					<td colspan="3">
						<html:text property="byny" styleId="byny" style="width:60px"
							onclick="return showCalendar(this.id,'yyyy-MM','','',200,10)" ></html:text>
					</td>
				</tr>
		</table>
		</div>
		
		<div>
			<table width="95%" class="formlist">
			   <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<button type="button"  name="����" id="buttonSave" onclick="bycl();">�� ��</button>
			            <button type="button"  name="�ر�" id="buttonClose" onclick="iFClose();return false;">�� ��</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
		</div>
	</html:form>
</body>
</html>
