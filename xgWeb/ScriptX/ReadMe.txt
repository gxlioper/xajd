++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
About client-side deployment of ScriptX version 6,1,432,1
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
'Free' ScriptX
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

* To use  the 'free' script enhancing and printing (Header, Footer, Margins & Orientation) functionality of ScriptX on your IE4.01SP1 - IE6.0SP1 pages, you will need:

  ScriptX.cab

* If you wish to you may reference ScriptX.cab directly from the MeadCo site:

<object id="factory" viewastext  style="display:none"
  classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814"
  codebase="http://www.meadroid.com/scriptx/ScriptX.cab#Version=6,1,432,1">
</object>

* Or you may want to pre-install 'basic' ScriptX over an intranet, in which case use the (admin-only):

  ScriptX.exe

An administrator should run ScriptX.exe on each client machine in your Intranet as an alternative to having the control auto-download the first time a user hits a 'basic' ScriptX-enabled page.


++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
Licensed ScriptX
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

* The enclosed smsx.cab is for use in cases where ScriptX' extended printing functionality is being used in conjunction with a publishing license. It contains the MeadCo Security Manager, MeadCo ScriptX and MeadCo MaxiPT resource dlls.

To evaluate the advanced printing functionality of ScriptX on your own computer, use smsx.cab together with the enclosed trial license SXLIC.MLF:

<!-- MeadCo Security Manager -->
<object id="secmgr" viewastext style="display:none"
  classid="clsid:5445be81-b796-11d2-b931-002018654e2e"
  codebase="http://[your_path]/smsx.cab#Version=6,1,432,1">
  <param name="GUID" value="{67533199-D16A-46D3-BA23-5AA77981F726}">
  <param name="Path" value="http://[your_path]/sxlic.mlf">
  <param name="Revison" value="0">
</object>

<!-- MeadCo ScriptX -->
<object id="factory" viewastext  style="display:none"
  classid="clsid:1663ed61-23eb-11d2-b92f-008048fdd814">
</object>

<!-- MaxiPT Resources -->
<object id="maxipt" viewastext style="display:none"
classid="clsid:C29F168A-7488-42A0-BDA1-47B3578652BE">
</object>

Note that [your_path] is shown as a placeholder only, and should be replaced by your own path to the smsx.cab and sxlic.mlf files.

The <object> code shown above should appear on ALL your ScriptX-enabled pages, including ASP. You can NOT use CreateObject or new ActiveXObject to call ScriptX client-side. You should call ScriptX in all cases by the ID of the on-page ScriptX object.

Note: the {67533199-D16A-46D3-BA23-5AA77981F726} value of the GUID parameter used above identifies the MeadCo evaluation license that authors may use to experiment with Advanced printing capabilities. The license validates local filesystem (file://, My Computer Security Zone only) and local website (http://localhost/) content ONLY for evaluation purposes on a single development computer. 

The evaluation license DOES NOT VALIDATE and WILL NOT WORK FROM any other address. Registered customers are issued with an unique license identifier and a digitally signed sxlic.mlf file. 

* If you want to pre-install the licensed ScriptX binaries over an intranet, use the(admin-only):

  smsx.exe

An administrator should run smsx.exe on each client machine in your Intranet as an alternative to having the control auto-download the first time a user hits a licensed ScriptX-enabled page.


++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

Full documentation and licensing details can be found at http://www.meadroid.com/scriptx/

Scripting Factory and ScriptX are Copyright (c) Mead & Co Limited 1998, 1999, 2000, 2001, 2002, 2003, 2004.

Contact us at: feedback@meadroid.com 
