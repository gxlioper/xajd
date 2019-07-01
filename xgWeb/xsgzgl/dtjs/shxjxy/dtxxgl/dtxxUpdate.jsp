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
		function saveDtxx(){
<%--			alert(jQuery('[name=kssj]').size());--%>
<%--			alert(jQuery('[name=jssj]').size());--%>
<%--			return false;--%>

			var curr_jddm=jQuery("#curr_jddm").val();
			if(curr_jddm==""){
				alertInfo("��ѡ��ѧ�����Ž��̣�");
				return false;
			}
			if(jQuery("#kssj_"+curr_jddm).val()==""){
				alertInfo("��ѡ��"+jQuery("#span_jdmc_"+curr_jddm).html()+"�Ŀ�ʼʱ��");
				return false;
			}
			//ʱ���Ⱥ�У��
			var jddm=document.getElementsByName("jddm");
			var kssj=document.getElementsByName("kssj");
			var jssj=document.getElementsByName("jssj");
			var jddm_kssj="";
			var jddm_jssj="";
			var kssj_temp="0";
			var jssj_temp="0";
			for(var i=0;i<jddm.length;i++){
				if(kssj[i].value!=""){
					if(kssj[i].value<kssj_temp){//�жϿ�ʼʱ������һ����ʼʱ��
						alertInfo(jQuery("#span_jdmc_"+jddm[i].value).html()+"��ʼʱ�䲻����С��"+
								jQuery("#span_jdmc_"+jddm_kssj).html()+"�Ŀ�ʼʱ��");
						return false;
					}
					if(kssj[i].value<jssj_temp){//�жϿ�ʼʱ������һ������ʱ��
						alertInfo(jQuery("#span_jdmc_"+jddm[i].value).html()+"��ʼʱ�䲻����С��"+
								jQuery("#span_jdmc_"+jddm_jssj).html()+"�Ľ���ʱ��");
						return false;
					}
					jddm_kssj=jddm[i].value;
					kssj_temp=kssj[i].value;
				}
				if(jssj[i].value!=""){
					if(jssj[i].value<kssj_temp){//�жϿ�ʼʱ������һ����ʼʱ��
						alertInfo(jQuery("#span_jdmc_"+jddm[i].value).html()+"����ʱ�䲻����С��"+
								jQuery("#span_jdmc_"+jddm_kssj).html()+"�Ŀ�ʼʱ��");
						return false;
					}
					if(jssj[i].value<jssj_temp){//�жϿ�ʼʱ������һ������ʱ��
						alertInfo(jQuery("#span_jdmc_"+jddm[i].value).html()+"����ʱ�䲻����С��"+
								jQuery("#span_jdmc_"+jddm_jssj).html()+"�Ľ���ʱ��");
						return false;
					}
					jddm_jssj=jddm[i].value;
					jssj_temp=jssj[i].value;
				}
				if(kssj[i].value!=""&&jssj[i].value!=""){
					if(kssj[i].value>jssj[i].value){
						alertInfo(jQuery("#span_jdmc_"+jddm[i].value).html()+"��ʼʱ�䲻����С��"+
								jQuery("#span_jdmc_"+jddm[i].value).html()+"�Ľ���ʱ��");
						return false;
					}	
				}
			}
			refreshForm('dtjs_dtxxgl.do?method=dtxxUpdate&doType=update');
		}

		function show(){
			var jdsx = jQuery('#jdsx').val();
			jQuery('[name=sj]').css({display:'none'})
			for(var i=1;i<=jdsx;i++){
				jQuery('#sj_'+i).css({display:''})
			}
			jQuery('[name=bq]').css({color:'gray'});
			jQuery('#bq_'+jdsx).css({color:'black'});
			jQuery("#curr_jddm").val(document.getElementById("td_jdmc_"+jdsx).getElementsByTagName("input")[0].value);
		}

		function go(fx){
			var jdsx = jQuery('#jdsx').val();
			if(fx=='next'){
				if(jQuery('#jdsx').val()!=jQuery('[name=jddm]').size()){
					jdsx++;
				}
			}else{
				if(jQuery('#jdsx').val()!=jQuery('#dqjdsx').val() && jQuery('#jdsx').val()>1){
					jdsx--;
				}
			}
			
			jQuery('#jdsx').val(jdsx);
			show();
			if(jQuery('#jdsx').val()==jQuery('#dqjdsx').val()){
				jQuery("#buttonBack").hide();
			}else{
				jQuery("#buttonBack").show();
			}
			
		}
	</script>
</head>
<body>
	<html:form action="/dtjs_dtxxgl" method="post">	
		<input type="hidden" id="dqjdsx" value="${dqjd.jdsx}"/>
		<input type="hidden" id="jdsx" value="${dqjd.jdsx}"/>
		<input type="hidden" id="curr_jddm" name="curr_jddm" value="${dqjd.jddm}"/>
		<%--<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>������Ϣ����</a>
			</p>
		</div>		
		--%>
		<div class="tab">
		<table class="formlist" width="95%">			
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>ѧ����Ϣ</span>
					</th>
				</tr>
			</thead>			
			<tbody>
				<tr >
					<th width="16%">
						<font color="red">*</font>ѧ��				
					</th>
					<td width="34%">
						<html:text property="xh" styleId="xh" readonly="true" value="${rs.xh }" />
					</td>
					<th width="16%">
						����				
					</th>
					<td width="34%">
						${rs.xm}
					</td>
				</tr>
				<tr>
					<th width="16%">
						�Ա�			
					</th>
					<td>
						${rs.xb}
					</td>
					<th width="16%">
						������ò
					</th>
					<td>
						${rs.zzmmmc}
					</td>
				</tr>
				<tr>
					<th width="16%">
						�꼶				
					</th>
					<td>
						${rs.nj}
					</td>
					<th width="16%">
						<bean:message key="lable.xsgzyxpzxy" />			
					</th>
					<td>
						${rs.xymc}
					</td>
				</tr>
				<tr>
					<th width="16%">
						רҵ			
					</th>
					<td>						
						${rs.zymc}
					</td>
					<th width="16%">
						�༶			
					</th>
					<td>
						${rs.bjmc}
					</td>
				</tr>
			</tbody>						
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>ѧ�����ŷ�չ����</span>
					</th>
				</tr>
			</thead>		
			<tbody>
				<tr>
					<td colspan="4">
					
					<div class="Join_party">
						<ul>
						<logic:iterate name="jdList" id="s">
					        <li>
					        	<div class="lc_bg">
					        		<div id="td_jdmc_${s.jdsx}" class="text">
					        		    <input type="hidden" name="jddm" value="${s.jddm}"/>
					        			<p id="span_jdmc_${s.jddm}"  onclick="show('${s.jdsx}','${s.jddm }')" class="Process">${s.jdmc}</p>
					        			<p name="sj" id="sj_${s.jdsx}" class="time" style="display: none;">
					        				<input name="kssj" id="kssj_${s.jddm}" value="${s.kssj}" style="width: 70px"
										onclick="return showCalendar(this.id,'yyyy-MM-dd')"/>
										<logic:equal value="��" name="s" property="sfszjssj">
											~
										</logic:equal>	
										<input name="jssj" id="jssj_${s.jddm}" value="${s.jssj}" style="width: 70px;display:
											<logic:equal value="��" name="s" property="sfszjssj">
												none
											</logic:equal>
											<logic:equal value="��" name="s" property="sfszjssj">
												''
											</logic:equal>
											"
											onclick="return showCalendar(this.id,'yyyy-MM-dd')"/>
					        			</p>
					        		</div>
					        	</div>
					        	<logic:notPresent name="s" property="isLast">
					        	<span class="Arrow"></span>
					        	</logic:notPresent>
					        </li>
					    </logic:iterate>
						</ul>
					</div>
					
<%--					<table class="formlist">--%>
<%--						<tr>						--%>
<%--							<logic:iterate name="jdList" id="s">--%>
<%--							<td align="center" id="td_jdmc_${s.jdsx}">--%>
<%--								<input type="hidden" name="jddm" value="${s.jddm}"/>--%>
<%--								<a name="bq" id="bq_${s.jdsx}" >--%>
<%--									<span id="span_jdmc_${s.jddm}">${s.jdmc}</span>--%>
<%--								</a>--%>
<%--							</td>--%>
<%--							</logic:iterate>--%>
<%--						</tr>--%>
<%--						<tr>						--%>
<%--							<logic:iterate name="jdList" id="s">--%>
<%--							<td align="center">--%>
<%--								<div name="sj" id="sj_${s.jdsx}" style="display: none">--%>
<%--									<input name="kssj" id="kssj_${s.jddm}" value="${s.kssj}" style="width: 70px"--%>
<%--										onclick="return showCalendar(this.id,'yyyy-MM-dd')"/>--%>
<%--									<br/>	--%>
<%--									<input name="jssj" id="jssj_${s.jddm}" value="${s.jssj}" style="width: 70px;display:--%>
<%--										<logic:equal value="��" name="s" property="sfszjssj">--%>
<%--											none--%>
<%--										</logic:equal>--%>
<%--										<logic:equal value="��" name="s" property="sfszjssj">--%>
<%--											''--%>
<%--										</logic:equal>--%>
<%--										"--%>
<%--										onclick="return showCalendar(this.id,'yyyy-MM-dd')"/>--%>
<%--								</div>--%>
<%--							</td>--%>
<%--							</logic:iterate>--%>
<%--						</tr>			--%>
<%--					</table>--%>
					</td>									
				</tr>
			</tbody>
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>������Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr >
					<th>
						��ϵ�Ƿ�ת��				
					</th>
					<td>
						<select id="zd1" name="zd1">
							<option value="��" <logic:equal value="��" property="zd1" name="other">selected="selected"</logic:equal>>��</option>
							<option value="��" <logic:equal value="��" property="zd1" name="other">selected="selected"</logic:equal>>��</option>
						</select>					
					</td>
					<th>
						������ȷ�չ�ƻ�				
					</th>
					<td >
						<select id="zd3" name="zd3">
							<option value="��" <logic:equal value="��" property="zd3" name="other">selected="selected"</logic:equal>>��</option>
							<option value="��" <logic:equal value="��" property="zd3" name="other">selected="selected"</logic:equal>>��</option>
						</select>	
					</td>
				</tr>
				<tr>
					<th width="16%">
						������ϵ��1		
					</th>
					<td>
						<logic:equal value="null" name="other" property="zd4">
							<input type="text" id="zd4" name="zd4"  maxlength="20" />
						</logic:equal>
						<logic:notEqual value="null" name="other" property="zd4">
							<input type="text" id="zd4" name="zd4" value="${other.zd4}"maxlength="20"  />
						</logic:notEqual>
					</td>
					<th width="16%">
						������ϵ��2
					</th>
					<td>
						<logic:equal value="null" name="other" property="zd5">
							<input type="text" id="zd5" name="zd5"  maxlength="20" />
						</logic:equal>
						<logic:notEqual value="null" name="other" property="zd5">
							<input type="text" id="zd5" name="zd5" value="${other.zd5}"maxlength="20"  />
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<th width="16%">
						��֯��ϵ���յ�λ
					</th>
					<td colspan="3">
						<logic:equal value="null" name="other" property="zd2">
							<input type="text" id="zd2" name="zd2"  maxlength="20" />
						</logic:equal>
						<logic:notEqual value="null" name="other" property="zd2">
							<input type="text" id="zd2" name="zd2" value="${other.zd2}"maxlength="20"  />
						</logic:notEqual>
					</td>
				</tr>
				
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<button type="button"  name="ת����һ�׶�" id="buttonNext" onclick="go('next')">ת����һ�׶�</button>
			          	<button type="button"  name="�˻�" id="buttonBack" onclick="go('back')" style="display: none;">�˻�</button>
			          	<button type="button"  name="�ύ" id="buttonSave" onclick="saveDtxx();">����</button>
			            <button type="button"  name="�ر�" id="buttonClose" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
	
	<script>
	show();
	</script>
	
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
