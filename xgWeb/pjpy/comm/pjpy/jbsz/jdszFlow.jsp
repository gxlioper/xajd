<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
		<script>
			jQuery(function(){
				var xmdm = jQuery('#xmdm').val();
				jQuery.ajax({
					type:'post',
					url:'pjpy_ty_ajax.do?method=getFjddm&xmdm='+xmdm,
					dataType:'json',
					success:function(data){
						if (null != data){
							jQuery.each(data,function(i,n){
								jQuery('input[value="'+n+'"]').attr('checked',true);
							});
						}
					}
				})
			});

            
			
			function saveCheck(){
				var fjddms=document.getElementsByName("fjddm");
				if(fjddms&&fjddms.length){
					for(var i=0;i<fjddms.length;i++){
						if(fjddms[i].checked){
							return true;
						}
					}
				}
				alert("��ѧ��������ƺ�����ѡ������һ�");
				return false;
			}
			function confim(){
				if(!saveCheck()){
                   return false;
				}else{
				   confirmInfo("��Ŀ������������,�������ò��ɷ���,�����Ҫ�޸��뵽��Ŀ���������ֱ���޸�,�Ƿ�Ҫ������һ��������?",mbDownLoad);
				}
				
			}
			function mbDownLoad(tag){
				if(tag=='ok'){
					saveUpdate('pjpy_ty_jdsz.do?method=jdszFlow&doType=save','');
				}
			}

			//��ʾ��������Ϣ
			function showHelpDiv(){

				if($("div_help")){
					if($("div_help").style.display == "none"){
						$("div_help").style.display = "";
					}else{
						$("div_help").style.display = "none";
					}
				}
				
			}
		</script>
	</head>

	<body>
	        <div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��������-��Ŀ����-��Ŀ�������</a>
				</p>
				
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
				</p>
			</div>
	<!-- ���̰�ť���� -->
		 <div class="flow-steps">
			  <ol class="num5">
			    <li class="current" style = "width:18%"><span class="first">1. ��Ŀ�������</span></li>
			    <li class="current-next" style = "width:18%"><span>2. ��Ŀ������Χ����</span></li>
			    <li class="current-next" style = "width:18%"><span>3. ��Ŀ��������</span></li>
			    <li class="current-next" style = "width:18%"><span>4. ��Ŀʱ����</span></li>
			    <li class="last" style = "width:18%"><span>5. ��Ŀ��������</span></li>
			  </ol>
        </div>
         <!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				1.���������ָ��ǰ����Ŀ<font color="blue">����</font>������Ľ�ѧ����������ƺż��
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->			

		<html:form action="/pjpy_ty_jdsz" method="post">
			<html:hidden property="xmdm" styleId="xmdm"/>
		
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									<span class="red">*��ѡ���ò��ɼ�õ���Ŀ</span>
								</div>
								<div class="btn">
								    <button type="button" type="reset" onclick="refresh('pjpy_ty_tzfw.do?method=tzfwszFlow&doType=');">
								                  ����
								    </button>
									<button type="button" id="buttonSave" onclick="confim()">
										����
									</button>
									<button type="button" id="buttonSave" onclick="winClose();">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								��Ŀ����
							</th>
							<td>
								<html:select property="xmdm" style="width:200px" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xmList" property="xmdm"
										labelProperty="xmmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>��ѧ��</th>
							<td>
								<logic:iterate id="x" name="xmList">
									<logic:notEqual value="02" name="x" property="xmlx">
										<logic:notEqual value="${pjpyJdszForm.xmdm }" name="x" property="xmdm">
											<input type="checkbox" name="fjddm" value="${x.xmdm }"/> ${x.xmmc }
										</logic:notEqual>									
									</logic:notEqual>
								</logic:iterate>
							</td>
						</tr>
						<tr>
							<th>�����ƺ�</th>
							<td>
								<logic:iterate id="x" name="xmList">
									<logic:notEqual value="01" name="x" property="xmlx">
										<logic:notEqual value="${pjpyJdszForm.xmdm }" name="x" property="xmdm">
											<input type="checkbox" name="fjddm" value="${x.xmdm }"/> ${x.xmmc }
										</logic:notEqual>									
									</logic:notEqual>
								</logic:iterate>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
