<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
		
		//�������϶�ָ������
		var count = 0;
		//����
		function addTr(){
			tr = jQuery('#hidden_knsrdzb').html();
			tr = tr.replace(/sxmc!!@@!!id/i,'zbsj'+(count));
			tr = tr.replace(/qzbl!!@@!!onclick/i,'zbsj'+(count++));
			jQuery('#tbody_knsrdzb').append(tr);
			return false;
		}

		//ָ����������
		var countZbnr = 0;
		//����
		function addZbnr(obj){
			tr = jQuery('#hidden_knsrdzbnr').html();
			tr = tr.replace(/zbnr!!@@!!id/i,'zbsj'+(countZbnr));
			tr = tr.replace(/knfz!!@@!!onclick/i,'zbsj'+(countZbnr++));
			jQuery(obj).parents("#knsrdzbnr").find('#tbody_knsrdzbnr').append(tr);
			return false;
		}

		function xzknsfz(obj){
			if(jQuery(obj).is(":checked")){
				jQuery(obj).parents("#knsrdzbxxnr").find("#qjfz").show();
				jQuery(obj).parents("#knsrdzbxxnr").find("#knfz").hide();
			}else{
				jQuery(obj).parents("#knsrdzbxxnr").find("#qjfz").hide();
				jQuery(obj).parents("#knsrdzbxxnr").find("#knfz").show();
			}
		}

		//ɾ��
		function delTr(obj){
			showConfirmDivLayer("��ȷ��Ҫɾ��������¼��", {
				"okFun" : function() {
				  jQuery(obj).parents("tr").eq(0).remove();
				}
			});
		}
		
		function save555(){	
				
			var base=new Array();
			var i=1;
			var zbmc = jQuery("input[name=zbmc]").val();
			if(zbmc == ""){
				showAlertDivLayer("�������϶�ָ������Ϊ�������Ϊ�գ�");
				return false;
			}
			var knsrdzbSfwk = false;//�������϶�ָ�������Ƿ�Ϊ��
			
			var nrmcSfwk = false;//���������Ƿ�Ϊ��
			var fzSfwk = false;//���������Ƿ�Ϊ��
			jQuery("#tbody_knsrdzb>tr").each(function(){
				//�ж�ָ������Ϊ�������Ϊ��
				if(nrmcSfwk == true ){
					showAlertDivLayer("ָ������Ϊ�������Ϊ�գ�");
					return false;
				}
				//��ֵΪ�������Ϊ��
				if(fzSfwk == true ){
					showAlertDivLayer("��ֵΪ�������Ϊ�գ�");
					return false;
				}
				knsrdzbSfwk = true;
				var rdzb=new Object();
				var sxmc = jQuery(this).find("input[name=sxmc]").val();
				rdzb.sxmc
				if(sxmc == ""){
					showAlertDivLayer("����ָ��Ϊ�������Ϊ�գ�");
					return false;
				}
				
				var qzbl=jQuery(this).find("input[name=qzbl]").val();
				rdzb.qzbl;
				if(qzbl == ""){
					showAlertDivLayer("Ȩ�ر���Ϊ�������Ϊ�գ�");
					return false;
				}
				
				rdzb.xssx=i;
				var zbnra=new Array();
				var xxnri=1;

				var zbnrSfwk = false;//ָ�������Ƿ�Ϊ��
				jQuery(this).find("#knsrdzbxxnr").each(function(){
					zbnrSfwk = true;
					var zbnr=new Object();
					var nrmc = jQuery(this).find("input[name=nrmc]").val();
					zbnr.nrmc;
					if(nrmc == ""){
						nrmcSfwk = true;
						return false;
					}
					
					if(jQuery(this).find("input[name=fzlx]").is(":checked")){
						zbnr.fzlx = "1";
						var fz = jQuery(this).find("input[name=knqjksfz]").val()+"-"+jQuery(this).find("input[name=knqjjsfz]").val();
						zbnr.fz;
						if(fz == ""){
							fzSfwk = true;
							return false;
						}
						
					}else{
						zbnr.fzlx = "0";
						var fz = jQuery(this).find("input[name=knfz]").val();
						zbnr.fz;
						if(fz == ""){
							fzSfwk = true;
							return false;
						}
						
					}
					zbnr.xssx=xxnri;
					zbnra[xxnri-1]=zbnr;
					xxnri++;
					
				});
				if(zbnrSfwk == false){
					showAlertDivLayer("ָ�����ݲ���Ϊ�գ�");
					return false;
				}
				rdzb.zbnr=zbnra;
				base[i-1]=rdzb;
				i++;

				
			});

			if(knsrdzbSfwk == false){
				showAlertDivLayer("�������϶�ָ�����ò���Ϊ�գ�");
				return false;
			}		
			
			var json=JSON.stringify(base);
			alert(json);
			return false;
			var url = "xg_xszz_knsrd_knzbgl.do?method=addKnsrdzb&type=save";
			jQuery.ajax({
				   type: "POST",
				   url: url,
				   dataType:"json",
				   data:{json:json,zbmc:zbmc},
				   success: function(msg){
				     alert( "Data Saved: " + msg );
				   }
			});
			
		}		

		function save(){	
			var base=new Array();
			var i=1;
			var zbmc = jQuery("input[name=zbmc]").val();
			//var zbmc = jQuery("input[name=zbmc]").val();
			if(zbmc == ""){
				showAlertDivLayer("�������϶�ָ������Ϊ�������Ϊ�գ�");
				return false;
			}
			var knsrdzbSfwk = false;//�������϶�ָ�������Ƿ�Ϊ��
			var knzbSfwk = false;//����ָ���Ƿ�Ϊ��
			var qzblSfwk = false;//Ȩ�ر����Ƿ�Ϊ��
			var knzbnrSfwk = false;//����ָ���Ƿ�Ϊ��
			var nrmcSfwk = false;//���������Ƿ�Ϊ��
			var fzSfwk = false;//���������Ƿ�Ϊ��
			jQuery("#tbody_knsrdzb>tr").each(function(){
				knsrdzbSfwk = true;
				var rdzb=new Object();
				var sxmc = jQuery(this).find("input[name=sxmc]").val();
				rdzb.sxmc = jQuery(this).find("input[name=sxmc]").val();;
				
				if(sxmc == ""){
					knzbSfwk = true;
					return false;
				}
				
				var qzbl=jQuery(this).find("input[name=qzbl]").val();
				rdzb.qzbl=jQuery(this).find("input[name=qzbl]").val();
				if(qzbl == ""){
					qzblSfwk = true;
					return false;
				}
				
				rdzb.xssx=i;
				var zbnra=new Array();
				var xxnri=1;
				knzbnrSfwk = false;
				jQuery(this).find("#knsrdzbxxnr").each(function(){
					knzbnrSfwk = true;
					var zbnr=new Object();
					var nrmc=jQuery(this).find("input[name=nrmc]").val();
					zbnr.nrmc=jQuery(this).find("input[name=nrmc]").val();

					if(nrmc == ""){
						nrmcSfwk = true;
						return false;
					}
					
					if(jQuery(this).find("input[name=fzlx]").is(":checked")){
						zbnr.fzlx = "1";
						var fz = jQuery(this).find("input[name=knqjksfz]").val()+"-"+jQuery(this).find("input[name=knqjjsfz]").val();
						zbnr.fz = jQuery(this).find("input[name=knqjksfz]").val()+"-"+jQuery(this).find("input[name=knqjjsfz]").val();
						if(fz == ""){
							fzSfwk = true;
							return false;
						}	
					}else{
						zbnr.fzlx = "0";
						var fz = jQuery(this).find("input[name=knfz]").val();
						zbnr.fz = jQuery(this).find("input[name=knfz]").val();
						if(fz == ""){
							fzSfwk = true;
							return false;
						}
					}
					zbnr.xssx=xxnri;
					zbnra[xxnri-1]=zbnr;
					xxnri++;
				});

				//�ж�ָ������Ϊ������
				if(knzbnrSfwk == false){
					return false;
				}
				//�ж���������Ϊ������
				if(nrmcSfwk == true){
					return false;
				}
				//�жϷ�ֵ����Ϊ������
				if(fzSfwk == true){
					return false;
				}
				
				rdzb.zbnr=zbnra;
				base[i-1]=rdzb;
				i++;
				
			});

			if(knsrdzbSfwk == false){
				showAlertDivLayer("�������϶�ָ�����ò���Ϊ�գ�");
				return false;
			}	
			if(knzbSfwk == true){
				showAlertDivLayer("����ָ��Ϊ�������Ϊ�գ�");
				return false;
			}
			if(qzblSfwk == true){
				showAlertDivLayer("Ȩ�ر���Ϊ�������Ϊ�գ�");
				return false;
			}

			if(knzbnrSfwk == false){
				showAlertDivLayer("�������϶�ָ������Ϊ�������Ϊ�գ�");
				return false;
			}
			//�ж�ָ������Ϊ�������Ϊ��
			if(nrmcSfwk == true ){
				showAlertDivLayer("ָ������Ϊ�������Ϊ�գ�");
				return false;
			}
			//��ֵΪ�������Ϊ��
			if(fzSfwk == true ){
				showAlertDivLayer("��ֵΪ�������Ϊ�գ�");
				return false;
			}
			var json=JSON.stringify(base);
			var url = "xg_xszz_knsrd_knzbgl.do?method=addKnsrdzb&type=save";
			jQuery.ajax({
				   type: "POST",
				   url: url,
				   dataType:"json",
				   data:{json:json,zbmc:zbmc},
				   success: function(data){
					   if(data["message"]=="����ɹ���" ){
				    		 showAlert(data["message"],{},{"clkFun":function(){
									if (parent.window){
										refershParent();
									}
								}});
				    	 }else{
				    		 showAlert(data["message"]);
				    	 }
				   }
			});
		}	
	
		</script>


	</head>
	<body>
		<html:form action="/xg_xszz_knsrd_knzbgl" method="post"
			styleId="knsrdzbForm" onsubmit="return false;">
			<div id="divHead" style="width: 100%; height:112px;overflow: hidden;position: fixed; _position: absolute; top: 0;" >
				<div class="prompt">
					<h3>
						<span>��ʾ��</span>
					</h3>
					<p>
						��ͨ������
						<img src="images/knsrd/jiahao.gif" return false;"/>
						/
						<img src="images/knsrd/jianhao.gif" return false;"/>
						�������������϶�ָ��
					</p>
					<a class="close" title="����"
						onclick="this.parentNode.style.display='none';"></a>
				</div>
				<div style="">
					<!-- ��ʾ��Ϣ end-->
					<table width="100%" border="0" class="formlist">

						<tbody id="tbody_jbxx">
							<tr>
								<th colspan="2">
									<font class="red">*</font><span>����</span>
								</th>
								<td colspan="2">
									<input type='text' name='zbmc' style='width: 250px' />
								</td>
							</tr>
						</tbody>
					</table>

				</div>

				<table width="100%" border="0" class="datelist"
					style="margin: 2px auto;" id="knsrdzbTable">
					<thead>
						<tr>
							<td width="7%">
								<img src="images/knsrd/jiahao.gif"
									onclick="addTr();return false;" />
							</td>
							<td width="23%">
								<font class="red">*</font>����ָ��
							</td>
							<td width="10%">
								<font class="red">*</font>Ȩ�ر���
							</td>
							<td width="60%">
								<font class="red">*</font>ָ������
							</td>
						</tr>
					</thead>
				</table>
				</div>

				<div id="divBody" style="width:100%;height:400px;border: 0px black solid; padding: 0px; margin: 0px; overflow: scroll;position: fixed; _position: absolute;top:112px">
				
				<table width="100%" border="0" class="datelist"
					style="margin: 2px auto;" >
						<tbody id="tbody_knsrdzb"></tbody>
				</table>
				</div>
				
				
				<div style="height: 30px"></div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="save();return false;">
										�� ��
									</button>
									<button type="button" onclick="Close();return false;">
										�� ��
									</button>

								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				
				
				
				
			<div style="display: none;">
				<table>
					<tbody id="hidden_knsrdzb">
						<tr>
							<td width="7%">
								<img src="images/knsrd/jianhao.gif"
									onclick="delTr(this);return false;" />
							</td>
							<td width="23%">
								<input type='text' id='sxmc!!@@!!id' name='sxmc'
									style='width: 160px' maxlength="50"/>
							</td>
							<td width="10%">
								<input type='text' id='qzbl' name='qzbl' size='5' maxlength='5'  onblur="checkInt(this);"  style="width:30px"/>
								%
							</td>
							<td width="60%">
								<table width="100%" border="0" class="datelist" id="knsrdzbnr"
									style="margin: 2px auto;">
									<thead>
										<tr>
											<td width="15%">
												<img src="images/knsrd/jiahao.gif"
													onclick="addZbnr(this);return false;" />
											</td>
											<td width="50%">
												<font class="red">*</font>ָ������
											</td>
											<td width="10%">
												�����
											</td>
											<td width="25%">
												<font class="red">*</font>��ֵ
											</td>

										</tr>
										<tbody id="tbody_knsrdzbnr">
										</tbody>
									</thead>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

			<div style="display: none;">
				<table>
					<tbody id="hidden_knsrdzbnr">
						<tr id="knsrdzbxxnr">

							<td width="15%">
								<img src="images/knsrd/jianhao.gif"
									onclick="delTr(this);return false;" />
							</td>
							<td width="50%">
								<input type='text' id='nrmc!!@@!!id' name='nrmc'
									style='width: 150px' maxlength="50"/>
							</td>
							<td width="10%">
								<input type='checkbox' onclick="xzknsfz(this)" name="fzlx" />
							</td>
							<td width="25%" id="knfz">
								<input type='text' id='knfz' name='knfz' size='10'
									maxlength='10' onblur="checkInt(this);" style="width:30px"/>
							</td>
							<td width="25%" style="display: none" id="qjfz">
								<input type='text' id='knqjksfz' name='knqjksfz' size='2'
									maxlength='4' onblur="checkInt(this);" style="width:30px"/>
								-
								<input type='text' id='knqjjsfz' name='knqjjsfz' size='2'
									maxlength='4' onblur="checkInt(this);" style="width:30px"/>
							</td>

						</tr>
					</tbody>
				</table>
			</div>

		</html:form>
	</body>
</html>

