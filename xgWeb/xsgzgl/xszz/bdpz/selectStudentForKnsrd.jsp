<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<tbody>
	<%
		List<HashMap<String,String>> jbxxList = (List<HashMap<String,String>>)request.getAttribute("jbxxList");
	%>
	<logic:present name="jbxxList">
		<tr>
			<logic:iterate id="j" name="jbxxList" indexId="i">
				<th width="16%">
					<logic:notEqual value="stu" name="userType">
						<logic:equal value="0" name="i">
							<logic:notEqual name="type" value="update">
								<font color="red">*</font>
							</logic:notEqual>
						</logic:equal>
					</logic:notEqual>
					${j.zdmc }
				</th>
				<td width="34%" class="${j.zddm }">
					<logic:equal value="stu" name="userType">
						<html:hidden property="xh" styleId="xh"/>
						<logic:present name="jbxx">
							<bean:write name="jbxx" property="${j.zddm }"/>
						</logic:present>
					</logic:equal>
					
					<logic:notEqual value="stu" name="userType">
						<logic:equal value="0" name="i">
							
							<logic:notEqual name="type" value="update">
								<input type="text" name="xh" value="${jbxx.xh }" id="xh" style="width:120px;"/>
								<script type="text/javascript">
			jQuery(function($) {
			   jQuery("#xh").blur(function(){
				     var xh=jQuery("#xh").val();
				     if(xh!="")
				     showXsxxAjax(xh)
				});
				jQuery("#xh").keydown(function(event){
				     if(13==event.keyCode){
				      var xh=jQuery("#xh").val();
				      if(xh!="")
				      showXsxxAjax(xh);}
				});
				});
				
			
			//�첽����ѧ�Ų�ѯѧ��������Ϣ�����ֲ�ˢ��ҳ��
			var showXsxxAjax = function(xh){
				jQuery.ajax({
					url:"xsxx_xsgl.do?method=getXsjbxxMore&xh="+xh,
					dataType: "JSON",
					type: "POST",
					success: function(data){

						//����ҳ������
						jQuery("td").each(function(i){
							var td = jQuery(this);
							var id = td.attr("class")
							if(id=="xh"){
								td.children("input").val(data[id]);
							}else{
								td.text(data[id]);
							}
						});
						
						//����change�¼�����δ֪ԭ��ֻ�ܴ���һ�ΰ󶨺�����������ʱ������
						try{
							whenXhChange(xh);
						}catch(e){
							
						}
					}
				});
			}
		</script>
					<button class="btn_01" type="button"  
									onclick="showDialog('��ѡ��һ��ѧ��',900,540,'xsxx_xsgl.do?method=showStudentsAjax');return false;">ѡ��</button>
						</logic:notEqual>
						
						<logic:equal name="type" value="update">
							<input type="hidden" name="xh" value="${jbxx.xh }" id="xh" />
							${jbxx.xh }
						</logic:equal>
							
					</logic:equal>
					<logic:notEqual value="0" name="i">
						<logic:present name="jbxx">
							<bean:write name="jbxx" property="${j.zddm }"/>
						</logic:present>
					</logic:notEqual>
				</logic:notEqual>
				</td>
				<%
					if ((i+1) % 2 == 0 && i != jbxxList.size()-1){
				%>
					</tr>
					<tr>
				<%
					}
				%>
			</logic:iterate>
		</tr>
	</logic:present>
</tbody>