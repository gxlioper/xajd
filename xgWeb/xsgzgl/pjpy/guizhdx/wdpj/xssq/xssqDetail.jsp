<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
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
		
		//�����ۺϲ�����Ϣ
		function saveXssqInfo(){
			
			//����ǰ��ʾ��Ϣ
			confirmInfo("�ò������ᱣ�����޸���Ϣ���Ƿ������",function(tag){
				//�ж��Ƿ�ѡ��ȷ������ť
				if(tag=="ok"){
				
					//����һ��json����
					var parameter={};
					
					//ָ����ȡ�Ŀؼ����ͣ�����ѭ��
					var hid_obj=jQuery("input,radio,select,textArea,checkbox").each(function(){
						
						//��ȡ���ؼ�name
						var name=jQuery(this).attr("name");
						//����json����
						parameter[name]=escape(jQuery(this).val());
					});
					
					//����URL
					var url = "general_wdpj_xssq_ajax.do?method=saveXssqInfo";
					
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
	<html:form action="/pjpy_comm_xmsq" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�������� - �ҵ����� - ѧ��������Ϣ</a>
			</p>
		</div>
		<input type="hidden" name="tjr" value="${userName }"/>
		<input type="hidden" name="xh" value="${wdpjXssqInfo.stuInfo.xh }"/>
		<input type="hidden" name="xmdm" value="${wdpjXssqInfo.xmInfo.xmdm }"/>
		<input type="hidden" name="xmmc" value="${wdpjXssqInfo.xmInfo.xmmc }"/>
		<input type="hidden" name="xn" value="${wdpjXssqInfo.xmInfo.pjxn }"/>
		<input type="hidden" name="xq" value="${wdpjXssqInfo.xmInfo.pjxq }"/>
		<input type="hidden" name="nd" value="${wdpjXssqInfo.xmInfo.pjnd }"/>
		<input type="hidden" name="pjxn" value="${wdpjXssqInfo.xmInfo.pjxn }"/>
		<input type="hidden" name="pjxq" value="${wdpjXssqInfo.xmInfo.pjxq }"/>
		<input type="hidden" name="pjnd" value="${wdpjXssqInfo.xmInfo.pjnd }"/>
		<input type="hidden" name="opera" value="${opera}" />
		<input type="hidden" name="sqsj" value="${sqsj}" />
		<input type="hidden" id="url" name="/xgxt/pjpy_comm_xmsq.do?method=xssqUpdate" />
		<input type="hidden" id="refreshParent" value="yes" />
		
		<button type="button" id="disbutton" onclick="autoFillTeaInfo(13);" style="display: none"></button>
		
		<div class="tab" style="width:100%;height:450px;overflow-x:hidden;overflow-y:auto;">
		<table class="formlist" width="93%">
			<%@include file="xmxx.jsp" %>
			<%@include file="xsxx.jsp" %>
			<%@include file="zcxx.jsp" %>
			<%@include file="cjxx.jsp" %>
			<%@include file="sqxx.jsp" %>
			<%@include file="qtxx.jsp" %>
		</table>
		</div>
		<table  class="formlist" width="93%">
		<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
		          <div class="btn">
		          	 <logic:equal value="add" name="opera">
		          		<button type="button" name="�ύ" id="buttonSave" onclick="saveXssqInfo()">�� ��</button>
		        	 </logic:equal>
		        	 <logic:equal value="update" name="opera">
		          		<button type="button" name="�ύ" onclick="saveXssqInfo()">�� ��</button>
		        	 </logic:equal>
		            <button type="button" name="�ر�" onclick="window.close();return false;">�� ��</button>
		          </div></td>
		      </tr>
		    </tfoot>
		</table>
	</html:form>
	<%@include file="/comm/other/tsxx.jsp" %>
</body>
</html>
