<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/khgl/khpf/js/khpf.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript" >
			var gridSetting = {
					caption : "���˶����б�",
					pager : "pager",
					url : "khglKhpf.do?method=addPf&type=query",
					multiselect:false,
					radioselect:true
				}
			
			var colList = [ 
					   {label : 'zbmxid',name : 'zbmxid',index : 'zbmxid',key:true,hidden:true},
					   {label : 'fzlx',name : 'fzlx',index : 'fzlx',hidden:true},
					   {label : 'pflx',name : 'pflx',index : 'pflx',hidden:true},
					   {label : 'fzqj',name : 'fzqj',index : 'fzqj',hidden:true},
					   {label : 'fzzgf',name : 'fzzgf',index : 'fzzgf',hidden:true},
					   {label : 'fzzdf',name : 'fzzdf',index : 'fzzdf',hidden:true},
					   <logic:notEqual name="xxdm" value="11527">
					   {label : '��������',name : 'khnr',index : 'khnr',width : '58%'}, 
					   </logic:notEqual>
					   {label : 'һ��ָ��',name : 'yjzb',index : 'yjzb',width : '10%'}, 
					   {label : '����ָ��',name : 'ejzb',index : 'ejzb',width : '22%'}, 
					   {label : '��ֵ',name : 'fzqj',index : 'fzqj',width : '8%'}, 
					   {label : '��������',name : 'pflxmc',index : 'pflxmc',width : '8%'}
				   ];

			var json = {label:'�÷�',
					name:"fs",
					index:"fs",
					width : '10%'
					};
			json["formatter"] = function(cell,rowObject){
				
				 var html = "";
				 if(null== rowObject.fs){
					 rowObject.fs="";
				 }
				 var sftj=jQuery("#sftj").val();
				 if(rowObject.fzlx == "1"){   //��ֵ����Ϊ�̶�
					
					html+="<select class='data-type' lx='"+rowObject.pflx+"' zb='"+rowObject.zbmxid+"' style='width:50px;' value='"+rowObject.fs+"'";
					//�ж�ѧ���������Ƿ����ύ
					if("1"==sftj){
						html+="disabled='disabled' ";
					}
					html+="onchange=\"saveKhfs(this)\">";
					html+="<option value='' selected='selected' ></option>";
					html+="<option value='0' selected='selected'>0</option>";
					html+="<option value='"+rowObject.fzqj+"' selected='selected' >"+rowObject.fzqj+"</option>";
			   		html+="</select>";
			   		var select = jQuery(html);
			   		select.val(rowObject.fs);
			   		select.find("option[value!='"+rowObject.fs+"']").removeAttr('selected');
			   		html = jQuery("<div></div>").append(select).html()
				 }else{
					html+= "<input class='data-type' lx='"+rowObject.pflx+"' zb='"+rowObject.zbmxid+"' onblur=\"checkInputNum(this);saveKhfs(this";
					html+=")\" type='text' onkeyup='toNext(this,event);checkInputNum(this);'";
					if("1"==sftj){
						html+="readonly='readonly' ";
					}
					html+=" style='width:50px;' maxlength='3' value='";
					html+=cell==null ? "" : cell;
					html+="' name='fs' max='"+rowObject.fzzgf+"' min='"+rowObject.fzzdf+"'/>";
				 }
				 
				 return html;
			}
			var bzJson={label:'��ע',
					name:"bz",
					index:"bz",
					width : '10%'
					};
			
			colList.push(json);
			<logic:equal value="11527" name="xxdm">
			colList.push(bzJson);
			</logic:equal>	
			gridSetting["colList"] = colList;
			jQuery(function() {
				var map = {xmid:jQuery("#xmid").val(),khbid:jQuery("#khbid").val(),khdxr:jQuery("#khdxr").val(),xmszid:jQuery("#xmszid").val()};
				gridSetting["params"] = map;
				jQuery.ajaxSetup({async:false});
				jQuery("#dataTable").initGrid(gridSetting);
				clickToTotal();
				autoRowSpan();
				jQuery.ajaxSetup({async:true});
			});
		</script>
	</head>
	<body>
	<html:form action="/khglKhpf" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
	<input type="hidden" name="xmid" id="xmid" value="${xmInfo.xmid}" />
	<input type="hidden" name="khbid" id="khbid" value="${model.khbid}" />
	<input type="hidden" name="khdxr" id="khdxr" value="${ryInfo.ry}" />
	<input type="hidden" name="xmszid" id="xmszid" value="${model.xmszid}" />
	<input type="hidden" name="jsonStr" id="jsonStr" value="${jsonStr}" />
	<input type="hidden" name="sftj" id="sftj" value="${pfxxMap.sftj}" />
	<input type="hidden" id="khlx" value="${khlx}"/>
	</html:form>
	<div class="main_box">
		<h3 class=datetitle_01>
			<span>
			��Ŀ:<font color="blue">${xmInfo.xmmc}</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			������:<font color="blue">${ryInfo.xm}</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			���ڲ���:<font color="blue">${ryInfo.xymc}</font>
			</span>
		</h3>
		<div class="con_overlfow">
			<table id="dataTable" ></table>
			<table width="100%" border="0" class="formlist">
				<html:form action="/khglKhpf" method="post">
					<input type="hidden" id="xxdm" value="${xxdm}"></input>
					<!-- ���������Ի� -->
					<logic:equal value="12309" name="xxdm">
					<!-- ��¼�û�Ϊѧ�� -->
						<logic:equal value="stu" name="userStatus">
							<!-- ���˶���Ϊ��ʦ -->
							<logic:equal value="2" name="khlx">
							<input type="hidden" id="khlx" value="${khlx}"/>					
								<th width="20%">
									�Ը���Ա����ͽ���</br>
									<span class="red">(��1000��)</span>
								</th>
								<td colspan="3">
									<div>							
										<html:textarea rows="8" cols="3" property="yjjy" style="width:99%; overflow-x:hidden" styleId="yjjy" onblur="saveYjjy(this);" ></html:textarea>
									</div>
								</td>										
							</logic:equal>					
						</logic:equal>
					</logic:equal>
					
					<!-- �㽭��ҵ��ʦѧԺ���Ի� -->
					<logic:equal value="33333" name="xxdm">
					<!-- ��¼�û�Ϊѧ�� -->
						<logic:equal value="stu" name="userStatus">
							<!-- ���˶���Ϊ��ʦ -->
							<logic:equal value="2" name="khlx">
								<th width="20%">
									�԰༶�������<br/>�Ľ�������</br>
									<span class="red">(��1000��)</span>
								</th>
								<td colspan="3">
									<div>							
										<html:textarea rows="8" cols="3" property="yjjy" style="width:99%; overflow-x:hidden" styleId="yjjy" onblur="saveYjjy(this);" ></html:textarea>
									</div>
								</td>										
							</logic:equal>					
						</logic:equal>
					</logic:equal>
				</html:form>
			</table>
		</div>
		<div style="height: 30px"></div>		
		<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute;  bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz" style="margin-left:30px;">
								<b> �÷ֺϼƣ�<span class="blue" id="total" >0</span></b>
							</div>
							<div class="btn">
							<logic:equal value="stu" name="userType">
							<logic:equal value="11527" name="xxdm">
							<button type="button" onclick="getStuCptjWord();return false;">
									�����ɼ�����
								</button>
							</logic:equal>
							</logic:equal>
							<logic:notEqual value="1" property="sftj" name="pfxxMap">
							
								<button type="button" onclick="submitTj();return false;">
									�ύ
								</button>
								<logic:notEqual value="stu" name="userType">
								<button type="button" onclick="submitNext();return false;">
									�ύ����һ��
								</button>
								</logic:notEqual>
								<button type="button"  onclick="refershParent();iFClose();" id="buttonClose">
									�� ��
								</button>
							</logic:notEqual>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
<%--			</html:form>--%>
		</div>
	</div>		
	</body>
</html>
