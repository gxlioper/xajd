<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
		jQuery(function(){
			jQuery("#div_help").show();
			jQuery("#buttonSave").click(function(){
				if(!checkNotNull("xzkssjh-xzkssjm-xzjssjh-xzjssjm")){
					showAlertDivLayer("�뽫��<font class='red'>*</font>����Ŀ��д������");
					return false;
				}
				if(parseInt(jQuery("#xzkssjh").val()+jQuery("#xzkssjm").val()) > parseInt(jQuery("#xzjssjh").val()+jQuery("#xzjssjm").val())){
					showAlertDivLayer("��ʼʱ�α���С�ڵ��ڽ���ʱ�Σ�");
					return false;
				}
					
				saveForm();
			
			})
		});
		//����
		function saveForm(){	  
			  var id = jQuery("#id").val();
				var url = "qjgl_sdcssz.do?method=saveSdxzcssz";
		      ajaxSubFormWithFun("CsszForm",url,function(data){
		    	  showAlertDivLayer(data["message"],{},{"clkFun":function(){
			    	  document.location.href = "xg_qjgl_sdcssz.do";
					}});;
				});
		  }
		</script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /> </a>
		   </p>
			<p class="help">
				<a href="#" onclick="return false;" onmouseover ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ -->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				��������ѧ����ٵ�ʱ�Σ�
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		</div>
		<html:form action="/qjgl_sdcssz" method="post" styleId="CsszForm">
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<tbody id="tbody_jbxx">
						<tr>
							<th width="35%">
								<font color="red">*</font>ʱ������
							</th>
							<td width="65%">
								<html:select style="width:60px" property="xzkssjh" styleId="xzkssjh">
									<html:option value="00">00</html:option>
									<html:option value="01">01</html:option>
									<html:option value="02">02</html:option>
									<html:option value="03">03</html:option>
									<html:option value="04">04</html:option>
									<html:option value="05">05</html:option>
									<html:option value="06">06</html:option>
									<html:option value="07">07</html:option>
									<html:option value="08">08</html:option>
									<html:option value="09">09</html:option>
									<html:option value="10">10</html:option>
									<html:option value="11">11</html:option>
									<html:option value="12">12</html:option>
									<html:option value="13">13</html:option>
									<html:option value="14">14</html:option>
									<html:option value="15">15</html:option>
									<html:option value="16">16</html:option>
									<html:option value="17">17</html:option>
									<html:option value="18">18</html:option>
									<html:option value="19">19</html:option>
									<html:option value="20">20</html:option>
									<html:option value="21">21</html:option>
									<html:option value="22">22</html:option>
									<html:option value="23">23</html:option>
									<html:option value="24">24</html:option>
								</html:select>ʱ
								<html:select style="width:60px"   property="xzkssjm" styleId="xzkssjm">
									<html:option value="00">00</html:option>
									<html:option value="01">01</html:option>
									<html:option value="02">02</html:option>
									<html:option value="03">03</html:option>
									<html:option value="04">04</html:option>
									<html:option value="05">05</html:option>
									<html:option value="06">06</html:option>
									<html:option value="07">07</html:option>
									<html:option value="08">08</html:option>
									<html:option value="09">09</html:option>
									<html:option value="10">10</html:option>
									<html:option value="11">11</html:option>
									<html:option value="12">12</html:option>
									<html:option value="13">13</html:option>
									<html:option value="14">14</html:option>
									<html:option value="15">15</html:option>
									<html:option value="16">16</html:option>
									<html:option value="17">17</html:option>
									<html:option value="18">18</html:option>
									<html:option value="19">19</html:option>
									<html:option value="20">20</html:option>
									<html:option value="21">21</html:option>
									<html:option value="22">22</html:option>
									<html:option value="23">23</html:option>
									<html:option value="24">24</html:option>
									<html:option value="25">25</html:option>
									<html:option value="26">26</html:option>
									<html:option value="27">27</html:option>
									<html:option value="28">28</html:option>
									<html:option value="29">29</html:option>
									<html:option value="30">30</html:option>
									<html:option value="31">31</html:option>
									<html:option value="32">32</html:option>
									<html:option value="33">33</html:option>
									<html:option value="34">34</html:option>
									<html:option value="35">35</html:option>
									<html:option value="36">36</html:option>
									<html:option value="37">37</html:option>
									<html:option value="38">38</html:option>
									<html:option value="39">39</html:option>
									<html:option value="40">40</html:option>
									<html:option value="41">41</html:option>
									<html:option value="42">42</html:option>
									<html:option value="43">43</html:option>
									<html:option value="44">44</html:option>
									<html:option value="45">45</html:option>
									<html:option value="46">46</html:option>
									<html:option value="47">47</html:option>
									<html:option value="48">48</html:option>
									<html:option value="49">49</html:option>
									<html:option value="50">50</html:option>
									<html:option value="51">51</html:option>
									<html:option value="52">52</html:option>
									<html:option value="53">53</html:option>
									<html:option value="54">54</html:option>
									<html:option value="55">55</html:option>
									<html:option value="56">56</html:option>
									<html:option value="57">57</html:option>
									<html:option value="58">58</html:option>
									<html:option value="59">59</html:option>
									<html:option value="60">60</html:option>
								</html:select>��&nbsp;&nbsp;��&nbsp;&nbsp;
								<html:select style="width:60px"  property="xzjssjh" styleId="xzjssjh">
									<html:option value="00">00</html:option>
									<html:option value="01">01</html:option>
									<html:option value="02">02</html:option>
									<html:option value="03">03</html:option>
									<html:option value="04">04</html:option>
									<html:option value="05">05</html:option>
									<html:option value="06">06</html:option>
									<html:option value="07">07</html:option>
									<html:option value="08">08</html:option>
									<html:option value="09">09</html:option>
									<html:option value="10">10</html:option>
									<html:option value="11">11</html:option>
									<html:option value="12">12</html:option>
									<html:option value="13">13</html:option>
									<html:option value="14">14</html:option>
									<html:option value="15">15</html:option>
									<html:option value="16">16</html:option>
									<html:option value="17">17</html:option>
									<html:option value="18">18</html:option>
									<html:option value="19">19</html:option>
									<html:option value="20">20</html:option>
									<html:option value="21">21</html:option>
									<html:option value="22">22</html:option>
									<html:option value="23">23</html:option>
									<html:option value="24">24</html:option>
								</html:select>ʱ
								<html:select style="width:60px"  property="xzjssjm" styleId="xzjssjm">
									<html:option value="00">00</html:option>
									<html:option value="01">01</html:option>
									<html:option value="02">02</html:option>
									<html:option value="03">03</html:option>
									<html:option value="04">04</html:option>
									<html:option value="05">05</html:option>
									<html:option value="06">06</html:option>
									<html:option value="07">07</html:option>
									<html:option value="08">08</html:option>
									<html:option value="09">09</html:option>
									<html:option value="10">10</html:option>
									<html:option value="11">11</html:option>
									<html:option value="12">12</html:option>
									<html:option value="13">13</html:option>
									<html:option value="14">14</html:option>
									<html:option value="15">15</html:option>
									<html:option value="16">16</html:option>
									<html:option value="17">17</html:option>
									<html:option value="18">18</html:option>
									<html:option value="19">19</html:option>
									<html:option value="20">20</html:option>
									<html:option value="21">21</html:option>
									<html:option value="22">22</html:option>
									<html:option value="23">23</html:option>
									<html:option value="24">24</html:option>
									<html:option value="25">25</html:option>
									<html:option value="26">26</html:option>
									<html:option value="27">27</html:option>
									<html:option value="28">28</html:option>
									<html:option value="29">29</html:option>
									<html:option value="30">30</html:option>
									<html:option value="31">31</html:option>
									<html:option value="32">32</html:option>
									<html:option value="33">33</html:option>
									<html:option value="34">34</html:option>
									<html:option value="35">35</html:option>
									<html:option value="36">36</html:option>
									<html:option value="37">37</html:option>
									<html:option value="38">38</html:option>
									<html:option value="39">39</html:option>
									<html:option value="40">40</html:option>
									<html:option value="41">41</html:option>
									<html:option value="42">42</html:option>
									<html:option value="43">43</html:option>
									<html:option value="44">44</html:option>
									<html:option value="45">45</html:option>
									<html:option value="46">46</html:option>
									<html:option value="47">47</html:option>
									<html:option value="48">48</html:option>
									<html:option value="49">49</html:option>
									<html:option value="50">50</html:option>
									<html:option value="51">51</html:option>
									<html:option value="52">52</html:option>
									<html:option value="53">53</html:option>
									<html:option value="54">54</html:option>
									<html:option value="55">55</html:option>
									<html:option value="56">56</html:option>
									<html:option value="57">57</html:option>
									<html:option value="58">58</html:option>
									<html:option value="59">59</html:option>
									<html:option value="60">60</html:option>
								</html:select>��
								
								
							</td>
						</tr>
					</tbody>
				<tfoot>
						<tr>
							<td align="center" colspan="2">							
			        			<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick=";" id="buttonSave">
										�� ��
									</button>	
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>

