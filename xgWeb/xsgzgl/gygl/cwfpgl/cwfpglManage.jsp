<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="dwr/interface/getCommGygl.js"></script>
		<script language="javascript" defer="defer">
		//��ѯ�����
		function searchRs(){
			// ��ʾ�������
			var resultSet = jQuery("#resultSet").val();
			allNotEmpThenGo('gyglnew_cwfpgl_cwfp.do?resultSet='+resultSet);
		}
		
		//���ò�ѯ��ʾ��λ��
		function setSearchMsgWz(left,top){
			$("input_mhcx_msg").style.left=left;
			$("input_mhcx_msg").style.top=top;
		}		

		//��λ�ֶ�����
		function goQssdfpByHand(act){

			if(!curr_row){
				alertInfo('��ѡ��һ��');
				return false;
			}

			// ��ʾ�������
			var resultSet = jQuery("#resultSet").val();
			// userType
			var userType = jQuery("#userType").val();
			if("xy"==userType && "xyli" == resultSet){
				alertInfo(jQuery("#xbmc").val()+"�û����ܶ�"+jQuery("#xbmc").val()+"���з��䣬���л�������ٽ��в�����");
				return false;
			}
			
			var pkValue = curr_row.getElementsByTagName('input')[0].value;
			$("primarykey_checkVal").value=pkValue;
			
			var url="gyglnew_cwfpgl.do?method=cwfpglSdfp&act="+act+"&resultSet="+resultSet;
			refreshForm(url);
			BatAlert.showTips('���ڲ��������Ե�...');
		}	
		
		jQuery(function() {

			setSearchMsgWz('130px','145px');
			
			// ��λ�������
			var cwfpdx = jQuery("#cwfpdx").val();
			
			// ��ʾ�������
			var resultSet = jQuery("#resultSet").val();
			
			// ��λ�������רҵ
			if("zydm" == cwfpdx){
				//������ʾ�����(�༶��
				jQuery(".imitation_xl ul li:last-child").hide();
			}
			
			// ��λ�������ѧԺ
			if("xydm" == cwfpdx){
				//������ʾ�����
				jQuery("#xsjglb").hide();
			}else{
				if("xyli" == resultSet){
					resultSet = jQuery("#xbmc").val();
				}else if("zyli"== resultSet){
					resultSet = "רҵ";
				}else if("bjli"== resultSet){
					resultSet = "�༶";
				}
				jQuery(".imitation_xl div").html(resultSet);
			
				jQuery(".imitation_xl div").click(function(event){
					event.stopPropagation();
					if(jQuery(this).prev("ul").is(":hidden")){
						jQuery(this).prev("ul").show();
					}else{
						jQuery(this).prev("ul").hide();
					}
				});
				jQuery(".imitation_xl ul li a").click(function(){
					var html1	=	jQuery(this).html();
					jQuery(".imitation_xl div").html(html1);
					jQuery(".imitation_xl ul").hide();
					
					var liid = jQuery(this).parent().attr("id");
					allNotEmpThenGo('gyglnew_cwfpgl_cwfp.do?resultSet='+liid);
					BatAlert.showTips('���ڲ��������Ե�...');
				});
				
				jQuery("body").click(function(){jQuery(".imitation_xl ul").hide()});
	
			}
	    });
		    
		</script>
	</head>
	<body >
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>			
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				��ѯ����еġ��ѷ��䴲λ����ָ��������꼶<bean:message key="lable.xsgzyxpzxy" />/רҵ/�༶���Ա�Ĵ�λ������<br/>
			</p>
			<a class="close" title="����"
			   onclick="this.parentNode.style.display='none';setSearchMsgWz('130px','85px')"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->		
		
		<html:form action="/gyglnew_cwfpgl">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="primarykey_checkVal" id="primarykey_checkVal" />
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<!-- ������ -->

			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
				<div class="buttonbox">
					<ul>						
						<!-- �ֶ����� -->
						<li>
							<a href="#" id="btn_xg"
								onclick="goQssdfpByHand('to_fp');return false;"
								class="btn_sh">
								����
							</a>
						</li>
						<!-- ȡ������ -->
						<li>
							<a href="#" id="btn_xg"
								onclick="goQssdfpByHand('to_qxfp');return false;"
								class="btn_qxsh">
								ȡ������
							</a>
						</li>
					</ul>
				</div>
				</logic:equal>		
				<!-- ��ť end-->	
				
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				
			</div>
			<!-- �๦�ܲ����� end -->
			
			<!-- ��ѯ���-->
			<div class="formbox">		
				<h3 class="datetitle_01">
					<span>
						��ѯ���
						<logic:empty name="rsArrList">
							&nbsp;&nbsp;<font color="red">δ�ҵ��κμ�¼��</font> 
						</logic:empty>
					</span>
					<logic:notEmpty name="rsArrList">
					    <input type="hidden" id="userType" name="resultSet" value="${userType }" />
						<input type="hidden" id="resultSet" name="resultSet" value="${resultSet }" />
						<input type="hidden" id="cwfpdx" name="cwfpdx" value="${cwfpdx }" />
				        <div class="xl" style="float:right" id="xsjglb">
				        	<div class="xl1">�л���ʾ�������</div>
				            <div class="imitation_xl">
				                <ul style="width:65px;">
				                    <li id="xyli"><a href="javascript:;"><bean:message key="lable.xb" /></a></li>
				                    <li id="zyli"><a href="javascript:;">רҵ</a></li>
				                    <li id="bjli"><a href="javascript:;">�༶</a></li>
				                </ul>
				                <div style="width:65px;"><bean:message key="lable.xb" /></div>
				            </div>
				        </div>
					</logic:notEmpty>
				</h3>
				
				<div class="con_overlfow" >
				<table summary="" class="dateline" align="" width="100%">
					<!-- ��ͷ -->
					<thead>
						<tr align="center" style="cursor:hand">
							<td width="5px" style="display: none;">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
							</td>
							<logic:iterate id="tit" name="topTr" offset="0" >
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort_hc(this,1)"
									nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<!-- ��ͷ end-->
					<!--���� -->
					<logic:empty name="rsArrList">
					  	<%
						for(int i=0; i<11; i++){
						%>
						<tr>
							<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
							<td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
					 	</tr>
						<%}%>
					 </logic:empty>
					<logic:iterate name="rsArrList" id="s" indexId="index">
						<tr onclick="rowOnClick(this);" style="cursor:hand">
							<logic:iterate id="v" name="s" offset="0" length="1">
								<td align="center" width="5px" style="display: none;">
									<input type="checkbox" id="pk_${index }"
									value="${v }"/>
								</td>
							</logic:iterate>
							<!-- ��ʾ��Ϣ -->
							<logic:iterate id="v" name="s" offset="1">
								<td align="left">
									${v }
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
					<!--���� end-->
					<!-- ������ -->
					<%--<%@ include file="/comm/noRows.jsp"%>
					--%><!-- ������ end-->
				</table>
				</div>
				<!--��ҳ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewCwfpglForm"></jsp:include>
				<script type="text/javascript">
					$('choose').className="hide";
				</script>
				<!--��ҳend-->
			</div>
			<!-- ��ѯ��� end-->
			
		</html:form>
	</body>
</html>