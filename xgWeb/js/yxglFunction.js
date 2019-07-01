var refreshFlag = 0;
var charPYStr = "°¡°¢°£°¤°¥°¦°§°¨°©°ª°«°¬°­°®°¯°°°±°²°³°´°µ°¶°·°¸°¹°º°»°¼°½°¾°¿°À°Á°Â°Ã°Ä°Å°Æ°Ç°È°É°Ê°Ë°Ì°Í°Î°Ï°Ð°Ñ°Ò°Ó°Ô°Õ°Ö°×°Ø°Ù°Ú°Û°Ü°Ý°Þ°ß°à°á°â°ã°ä°å°æ°ç°è°é°ê°ë°ì°í°î°ï°ð°ñ°ò°ó°ô°õ°ö°÷°ø°ù°ú°û°ü°ý°þ±¡±¢±£±¤±¥±¦±§±¨±©±ª±«±¬±­±®±¯±°±±±²±³±´±µ±¶±·±¸±¹±º±»±¼±½±¾±¿±À±Á±Â±Ã±Ä±Å±Æ±Ç±È±É±Ê±Ë±Ì±Í±Î±Ï±Ð±Ñ±Ò±Ó±Ô±Õ±Ö±×±Ø±Ù±Ú±Û±Ü±Ý±Þ±ß±à±á±â±ã±ä±å±æ±ç±è±é±ê±ë±ì±í±î±ï±ð±ñ±ò±ó±ô±õ±ö±÷±ø±ù±ú±û±ü±ý±þ²¡²¢²£²¤²¥²¦²§²¨²©²ª²«²¬²­²®²¯²°²±²²²³²´²µ²¶²·²¸²¹²º²»²¼²½²¾²¿²À²Á²Â²Ã²Ä²Å²Æ²Ç²È²É²Ê²Ë²Ì²Í²Î²Ï²Ð²Ñ²Ò²Ó²Ô²Õ²Ö²×²Ø²Ù²Ú²Û²Ü²Ý²Þ²ß²à²á²â²ã²ä²å²æ²ç²è²é²ê²ë²ì²í²î²ï²ð²ñ²ò²ó²ô²õ²ö²÷²ø²ù²ú²û²ü²ý²þ³¡³¢³£³¤³¥³¦³§³¨³©³ª³«³¬³­³®³¯³°³±³²³³³´³µ³¶³·³¸³¹³º³»³¼³½³¾³¿³À³Á³Â³Ã³Ä³Å³Æ³Ç³È³É³Ê³Ë³Ì³Í³Î³Ï³Ð³Ñ³Ò³Ó³Ô³Õ³Ö³×³Ø³Ù³Ú³Û³Ü³Ý³Þ³ß³à³á³â³ã³ä³å³æ³ç³è³é³ê³ë³ì³í³î³ï³ð³ñ³ò³ó³ô³õ³ö³÷³ø³ù³ú³û³ü³ý³þ´¡´¢´£´¤´¥´¦´§´¨´©´ª´«´¬´­´®´¯´°´±´²´³´´´µ´¶´·´¸´¹´º´»´¼´½´¾´¿´À´Á´Â´Ã´Ä´Å´Æ´Ç´È´É´Ê´Ë´Ì´Í´Î´Ï´Ð´Ñ´Ò´Ó´Ô´Õ´Ö´×´Ø´Ù´Ú´Û´Ü´Ý´Þ´ß´à´á´â´ã´ä´å´æ´ç´è´é´ê´ë´ì´í´î´ï´ð´ñ´ò´ó´ô´õ´ö´÷´ø´ù´ú´û´ü´ý´þµ¡µ¢µ£µ¤µ¥µ¦µ§µ¨µ©µªµ«µ¬µ­µ®µ¯µ°µ±µ²µ³µ´µµµ¶µ·µ¸µ¹µºµ»µ¼µ½µ¾µ¿µÀµÁµÂµÃµÄµÅµÆµÇµÈµÉµÊµËµÌµÍµÎµÏµÐµÑµÒµÓµÔµÕµÖµ×µØµÙµÚµÛµÜµÝµÞµßµàµáµâµãµäµåµæµçµèµéµêµëµìµíµîµïµðµñµòµóµôµõµöµ÷µøµùµúµûµüµýµþ¶¡¶¢¶£¶¤¶¥¶¦¶§¶¨¶©¶ª¶«¶¬¶­¶®¶¯¶°¶±¶²¶³¶´¶µ¶¶¶·¶¸¶¹¶º¶»¶¼¶½¶¾¶¿¶À¶Á¶Â¶Ã¶Ä¶Å¶Æ¶Ç¶È¶É¶Ê¶Ë¶Ì¶Í¶Î¶Ï¶Ð¶Ñ¶Ò¶Ó¶Ô¶Õ¶Ö¶×¶Ø¶Ù¶Ú¶Û¶Ü¶Ý¶Þ¶ß¶à¶á¶â¶ã¶ä¶å¶æ¶ç¶è¶é¶ê¶ë¶ì¶í¶î¶ï¶ð¶ñ¶ò¶ó¶ô¶õ¶ö¶÷¶ø¶ù¶ú¶û¶ü¶ý¶þ·¡·¢·£·¤·¥·¦·§·¨·©·ª·«·¬·­·®·¯·°·±·²·³·´·µ·¶···¸·¹·º·»·¼·½·¾·¿·À·Á·Â·Ã·Ä·Å·Æ·Ç·È·É·Ê·Ë·Ì·Í·Î·Ï·Ð·Ñ·Ò·Ó·Ô·Õ·Ö·×·Ø·Ù·Ú·Û·Ü·Ý·Þ·ß·à·á·â·ã·ä·å·æ·ç·è·é·ê·ë·ì·í·î·ï·ð·ñ·ò·ó·ô·õ·ö·÷·ø·ù·ú·û·ü·ý·þ¸¡¸¢¸£¸¤¸¥¸¦¸§¸¨¸©¸ª¸«¸¬¸­¸®¸¯¸°¸±¸²¸³¸´¸µ¸¶¸·¸¸¸¹¸º¸»¸¼¸½¸¾¸¿¸À¸Á¸Â¸Ã¸Ä¸Å¸Æ¸Ç¸È¸É¸Ê¸Ë¸Ì¸Í¸Î¸Ï¸Ð¸Ñ¸Ò¸Ó¸Ô¸Õ¸Ö¸×¸Ø¸Ù¸Ú¸Û¸Ü¸Ý¸Þ¸ß¸à¸á¸â¸ã¸ä¸å¸æ¸ç¸è¸é¸ê¸ë¸ì¸í¸î¸ï¸ð¸ñ¸ò¸ó¸ô¸õ¸ö¸÷¸ø¸ù¸ú¸û¸ü¸ý¸þ¹¡¹¢¹£¹¤¹¥¹¦¹§¹¨¹©¹ª¹«¹¬¹­¹®¹¯¹°¹±¹²¹³¹´¹µ¹¶¹·¹¸¹¹¹º¹»¹¼¹½¹¾¹¿¹À¹Á¹Â¹Ã¹Ä¹Å¹Æ¹Ç¹È¹É¹Ê¹Ë¹Ì¹Í¹Î¹Ï¹Ð¹Ñ¹Ò¹Ó¹Ô¹Õ¹Ö¹×¹Ø¹Ù¹Ú¹Û¹Ü¹Ý¹Þ¹ß¹à¹á¹â¹ã¹ä¹å¹æ¹ç¹è¹é¹ê¹ë¹ì¹í¹î¹ï¹ð¹ñ¹ò¹ó¹ô¹õ¹ö¹÷¹ø¹ù¹ú¹û¹ü¹ý¹þº¡º¢º£º¤º¥º¦º§º¨º©ºªº«º¬º­º®º¯º°º±º²º³º´ºµº¶º·º¸º¹ººº»º¼º½º¾º¿ºÀºÁºÂºÃºÄºÅºÆºÇºÈºÉºÊºËºÌºÍºÎºÏºÐºÑºÒºÓºÔºÕºÖº×ºØºÙºÚºÛºÜºÝºÞºßºàºáºâºãºäºåºæºçºèºéºêºëºìºíºîºïºðºñºòºóºôºõºöº÷ºøºùºúºûºüºýºþ»¡»¢»£»¤»¥»¦»§»¨»©»ª»«»¬»­»®»¯»°»±»²»³»´»µ»¶»·»¸»¹»º»»»¼»½»¾»¿»À»Á»Â»Ã»Ä»Å»Æ»Ç»È»É»Ê»Ë»Ì»Í»Î»Ï»Ð»Ñ»Ò»Ó»Ô»Õ»Ö»×»Ø»Ù»Ú»Û»Ü»Ý»Þ»ß»à»á»â»ã»ä»å»æ»ç»è»é»ê»ë»ì»í»î»ï»ð»ñ»ò»ó»ô»õ»ö»÷»ø»ù»ú»û»ü»ý»þ¼¡¼¢¼£¼¤¼¥¼¦¼§¼¨¼©¼ª¼«¼¬¼­¼®¼¯¼°¼±¼²¼³¼´¼µ¼¶¼·¼¸¼¹¼º¼»¼¼¼½¼¾¼¿¼À¼Á¼Â¼Ã¼Ä¼Å¼Æ¼Ç¼È¼É¼Ê¼Ë¼Ì¼Í¼Î¼Ï¼Ð¼Ñ¼Ò¼Ó¼Ô¼Õ¼Ö¼×¼Ø¼Ù¼Ú¼Û¼Ü¼Ý¼Þ¼ß¼à¼á¼â¼ã¼ä¼å¼æ¼ç¼è¼é¼ê¼ë¼ì¼í¼î¼ï¼ð¼ñ¼ò¼ó¼ô¼õ¼ö¼÷¼ø¼ù¼ú¼û¼ü¼ý¼þ½¡½¢½£½¤½¥½¦½§½¨½©½ª½«½¬½­½®½¯½°½±½²½³½´½µ½¶½·½¸½¹½º½»½¼½½½¾½¿½À½Á½Â½Ã½Ä½Å½Æ½Ç½È½É½Ê½Ë½Ì½Í½Î½Ï½Ð½Ñ½Ò½Ó½Ô½Õ½Ö½×½Ø½Ù½Ú¾¥¾¦¾§¾¨¾©¾ª¾«¾¬¾­¾®¾¯¾°¾±¾²¾³¾´¾µ¾¶¾·¾¸¾¹¾º¾»¾¼¾½¾¾¾¿¾À¾Á¾Â¾Ã¾Ä¾Å¾Æ¾Ç¾È¾É¾Ê¾Ë¾Ì¾Í¾Î¾Ï¾Ð¾Ñ¾Ò¾Ó¾Ô¾Õ¾Ö¾×¾Ø¾Ù¾Ú¾Û¾Ü¾Ý¾Þ¾ß¾à¾á¾â¾ã¾ä¾å¾æ¾ç¾è¾é¾ê¾ë¾ì¾í¾î¾ï¾ð¾ñ¾ò¾ó¾ô½Û½Ü½Ý½Þ½ß½à½á½â½ã½ä½å½æ½ç½è½é½ê½ë½ì½í½î½ï½ð½ñ½ò½ó½ô½õ½ö½÷½ø½ù½ú½û½ü½ý½þ¾¡¾¢¾£¾¤¾õ¾ö¾÷¾ø¾ù¾ú¾û¾ü¾ý¾þ¿¡¿¢¿£¿¤¿¥¿¦¿§¿¨¿©¿ª¿«¿¬¿­¿®¿¯¿°¿±¿²¿³¿´¿µ¿¶¿·¿¸¿¹¿º¿»¿¼¿½¿¾¿¿¿À¿Á¿Â¿Ã¿Ä¿Å¿Æ¿Ç¿È¿É¿Ê¿Ë¿Ì¿Í¿Î¿Ï¿Ð¿Ñ¿Ò¿Ó¿Ô¿Õ¿Ö¿×¿Ø¿Ù¿Ú¿Û¿Ü¿Ý¿Þ¿ß¿à¿á¿â¿ã¿ä¿å¿æ¿ç¿è¿é¿ê¿ë¿ì¿í¿î¿ï¿ð¿ñ¿ò¿ó¿ô¿õ¿ö¿÷¿ø¿ù¿ú¿û¿ü¿ý¿þÀ¡À¢À£À¤À¥À¦À§À¨À©ÀªÀ«À¬À­À®À¯À°À±À²À³À´ÀµÀ¶À·À¸À¹ÀºÀ»À¼À½À¾À¿ÀÀÀÁÀÂÀÃÀÄÀÅÀÆÀÇÀÈÀÉÀÊÀËÀÌÀÍÀÎÀÏÀÐÀÑÀÒÀÓÀÔÀÕÀÖÀ×ÀØÀÙÀÚÀÛÀÜÀÝÀÞÀßÀàÀáÀâÀãÀäÀåÀæÀçÀèÀéÀêÀëÀìÀíÀîÀïÀðÀñÀòÀóÀôÀõÀöÀ÷ÀøÀùÀúÀûÀüÀýÀþÁ¡Á¢Á£Á¤Á¥Á¦Á§Á¨Á©ÁªÁ«Á¬Á­Á®Á¯Á°Á±Á²Á³Á´ÁµÁ¶Á·Á¸Á¹ÁºÁ»Á¼Á½Á¾Á¿ÁÀÁÁÁÂÁÃÁÄÁÅÁÆÁÇÁÈÁÉÁÊÁËÁÌÁÍÁÎÁÏÁÐÁÑÁÒÁÓÁÔÁÕÁÖÁ×ÁØÁÙÁÚÁÛÁÜÁÝÁÞÁßÁàÁáÁâÁãÁäÁåÁæÁçÁèÁéÁêÁëÁìÁíÁîÁïÁðÁñÁòÁóÁôÁõÁöÁ÷ÁøÁùÁúÁûÁüÁýÁþÂ¡Â¢Â£Â¤Â¥Â¦Â§Â¨Â©ÂªÂ«Â¬Â­Â®Â¯Â°Â±Â²Â³Â´ÂµÂ¶Â·Â¸Â¹ÂºÂ»Â¼Â½Â¾Â¿ÂÀÂÁÂÂÂÃÂÄÂÅÂÆÂÇÂÈÂÉÂÊÂËÂÌÂÍÂÎÂÏÂÐÂÑÂÒÂÓÂÔÂÕÂÖÂ×ÂØÂÙÂÚÂÛÂÜÂÝÂÞÂßÂàÂáÂâÂãÂäÂåÂæÂçÂèÂéÂêÂëÂìÂíÂîÂïÂðÂñÂòÂóÂôÂõÂöÂ÷ÂøÂùÂúÂûÂüÂýÂþÃ¡Ã¢Ã£Ã¤Ã¥Ã¦Ã§Ã¨Ã©ÃªÃ«Ã¬Ã­Ã®Ã¯Ã°Ã±Ã²Ã³Ã´ÃµÃ¶Ã·Ã¸Ã¹ÃºÃ»Ã¼Ã½Ã¾Ã¿ÃÀÃÁÃÂÃÃÃÄÃÅÃÆÃÇÃÈÃÉÃÊÃËÃÌÃÍÃÎÃÏÃÐÃÑÃÒÃÓÃÔÃÕÃÖÃ×ÃØÃÙÃÚÃÛÃÜÃÝÃÞÃßÃàÃáÃâÃãÃäÃåÃæÃçÃèÃéÃêÃëÃìÃíÃîÃïÃðÃñÃòÃóÃôÃõÃöÃ÷ÃøÃùÃúÃûÃüÃýÃþÄ¡Ä¢Ä£Ä¤Ä¥Ä¦Ä§Ä¨Ä©ÄªÄ«Ä¬Ä­Ä®Ä¯Ä°Ä±Ä²Ä³Ä´ÄµÄ¶Ä·Ä¸Ä¹ÄºÄ»Ä¼Ä½Ä¾Ä¿ÄÀÄÁÄÂÄÃÄÄÄÅÄÆÄÇÄÈÄÉÄÊÄËÄÌÄÍÄÎÄÏÄÐÄÑÄÒÄÓÄÔÄÕÄÖÄ×ÄØÄÙÄÚÄÛÄÜÄÝÄÞÄßÄàÄáÄâÄãÄäÄåÄæÄçÄèÄéÄêÄëÄìÄíÄîÄïÄðÄñÄòÄóÄôÄõÄöÄ÷ÄøÄùÄúÄûÄüÄýÄþÅ¡Å¢Å£Å¤Å¥Å¦Å§Å¨Å©ÅªÅ«Å¬Å­Å®Å¯Å°Å±Å²Å³Å´ÅµÅ¶Å·Å¸Å¹ÅºÅ»Å¼Å½Å¾Å¿ÅÀÅÁÅÂÅÃÅÄÅÅÅÆÅÇÅÈÅÉÅÊÅËÅÌÅÍÅÎÅÏÅÐÅÑÅÒÅÓÅÔÅÕÅÖÅ×ÅØÅÙÅÚÅÛÅÜÅÝÅÞÅßÅàÅáÅâÅãÅäÅåÅæÅçÅèÅéÅêÅëÅìÅíÅîÅïÅðÅñÅòÅóÅôÅõÅöÅ÷ÅøÅùÅúÅûÅüÅýÅþÆ¡Æ¢Æ£Æ¤Æ¥Æ¦Æ§Æ¨Æ©ÆªÆ«Æ¬Æ­Æ®Æ¯Æ°Æ±Æ²Æ³Æ´ÆµÆ¶Æ·Æ¸Æ¹ÆºÆ»Æ¼Æ½Æ¾Æ¿ÆÀÆÁÆÂÆÃÆÄÆÅÆÆÆÇÆÈÆÉÆÊÆËÆÌÆÍÆÎÆÏÆÐÆÑÆÒÆÓÆÔÆÕÆÖÆ×ÆØÆÙÆÚÆÛÆÜÆÝÆÞÆßÆàÆáÆâÆãÆäÆåÆæÆçÆèÆéÆêÆëÆìÆíÆîÆïÆðÆñÆòÆóÆôÆõÆöÆ÷ÆøÆùÆúÆûÆüÆýÆþÇ¢Ç£Ç¤Ç¥Ç¦Ç§Ç¨Ç©ÇªÇ«Ç¬Ç­Ç®Ç¯Ç°Ç±Ç²Ç³Ç´ÇµÇ¶Ç·Ç¸Ç¹ÇºÇ»Ç¼Ç½Ç¾Ç¿ÇÀÇÁÇÂÇÃÇÄÇÅÇÆÇÇÇÈÇÉÇÊÇËÇÌÇÍÇÎÇÏÇÐÇÑÇÒÇÓÇÔÇÕÇÖÇ×ÇØÇÙÇÚÇÛÇÜÇÝÇÞÇßÇàÇáÇâÇãÇäÇåÇæÇçÇèÇéÇêÇëÇìÇíÇîÇïÇðÇñÇòÇóÇôÇõÇöÇ÷ÇøÇùÇúÇûÇüÇýÇþÈ¡È¢È£È¤È¥È¦È§È¨È©ÈªÈ«È¬È­È®È¯È°È±È²È³È´ÈµÈ¶È·È¸È¹ÈºÈ»È¼È½È¾È¿ÈÀÈÁÈÂÈÃÈÄÈÅÈÆÈÇÈÈÈÉÈÊÈËÈÌÈÍÈÎÈÏÈÐÈÑÈÒÈÓÈÔÈÕÈÖÈ×ÈØÈÙÈÚÈÛÈÜÈÝÈÞÈßÈàÈáÈâÈãÈäÈåÈæÈçÈèÈéÈêÈëÈìÈíÈîÈïÈðÈñÈòÈóÈôÈõÈöÈ÷ÈøÈùÈúÈûÈüÈýÈþÉ¡É¢É£É¤É¥É¦É§É¨É©ÉªÉ«É¬É­É®É¯É°É±É²É³É´ÉµÉ¶É·É¸É¹ÉºÉ»É¼É½É¾É¿ÉÀÉÁÉÂÉÃÉÄÉÅÉÆÉÇÉÈÉÉÉÊÉËÉÌÉÍÉÎÉÏÉÐÉÑÉÒÉÓÉÔÉÕÉÖÉ×ÉØÉÙÉÚÉÛÉÜÉÝÉÞÉßÉàÉáÉâÉãÉäÉåÉæÉçÉèÉéÉêÉëÉìÉíÉîÉïÉðÉñÉòÉóÉôÉõÉöÉ÷ÉøÉùÉúÉûÉüÉýÉþÊ¡Ê¢Ê£Ê¤Ê¥Ê¦Ê§Ê¨Ê©ÊªÊ«Ê¬Ê­Ê®Ê¯Ê°Ê±Ê²Ê³Ê´ÊµÊ¶Ê·Ê¸Ê¹ÊºÊ»Ê¼Ê½Ê¾Ê¿ÊÀÊÁÊÂÊÃÊÄÊÅÊÆÊÇÊÈÊÉÊÊÊËÊÌÊÍÊÎÊÏÊÐÊÑÊÒÊÓÊÔÊÕÊÖÊ×ÊØÊÙÊÚÊÛÊÜÊÝÊÞÊßÊàÊáÊâÊãÊäÊåÊæÊçÊèÊéÊêÊëÊìÊíÊîÊïÊðÊñÊòÊóÊôÊõÊöÊ÷ÊøÊùÊúÊûÊüÊýÊþË¡Ë¢Ë£Ë¤Ë¥Ë¦Ë§Ë¨Ë©ËªË«Ë¬Ë­Ë®Ë¯Ë°Ë±Ë²Ë³Ë´ËµË¶Ë·Ë¸Ë¹ËºË»Ë¼Ë½Ë¾Ë¿ËÀËÁËÂËÃËÄËÅËÆËÇËÈËÉËÊËËËÌËÍËÎËÏËÐËÑËÒËÓËÔËÕËÖË×ËØËÙËÚËÛËÜËÝËÞËßËàËáËâËãËäËåËæËçËèËéËêËëËìËíËîËïËðËñËòËóËôËõËöË÷ËøËùËúËûËüËýËþÌ¡Ì¢Ì£Ì¤Ì¥Ì¦Ì§Ì¨Ì©ÌªÌ«Ì¬Ì­Ì®Ì¯Ì°Ì±Ì²Ì³Ì´ÌµÌ¶Ì·Ì¸Ì¹ÌºÌ»Ì¼Ì½Ì¾Ì¿ÌÀÌÁÌÂÌÃÌÄÌÅÌÆÌÇÌÈÌÉÌÊÌËÌÌÌÍÌÎÌÏÌÐÌÑÌÒÌÓÌÔÌÕÌÖÌ×ÌØÌÙÌÚÌÛÌÜÌÝÌÞÌßÌàÌáÌâÌãÌäÌåÌæÌçÌèÌéÌêÌëÌìÌíÌîÌïÌðÌñÌòÌóÌôÌõÌöÌ÷ÌøÌùÌúÌûÌüÌýÌþÍ¡Í¢Í£Í¤Í¥Í¦Í§Í¨Í©ÍªÍ«Í¬Í­Í®Í¯Í°Í±Í²Í³Í´ÍµÍ¶Í·Í¸Í¹ÍºÍ»Í¼Í½Í¾Í¿ÍÀÍÁÍÂÍÃÍÄÍÅÍÆÍÇÍÈÍÉÍÊÍËÍÌÍÍÍÎÍÏÍÐÍÑÍÒÍÓÍÔÍÕÍÖÍ×ÍØÍÙÍÚÍÛÍÜÍÝÍÞÍßÍàÍáÍâÍãÍäÍåÍæÍçÍèÍéÍêÍëÍìÍíÍîÍïÍðÍñÍòÍóÍôÍõÍöÍ÷ÍøÍùÍúÍûÍüÍýÍþÎ¡Î¢Î£Î¤Î¥Î¦Î§Î¨Î©ÎªÎ«Î¬Î­Î®Î¯Î°Î±Î²Î³Î´ÎµÎ¶Î·Î¸Î¹ÎºÎ»Î¼Î½Î¾Î¿ÎÀÎÁÎÂÎÃÎÄÎÅÎÆÎÇÎÈÎÉÎÊÎËÎÌÎÍÎÎÎÏÎÐÎÑÎÒÎÓÎÔÎÕÎÖÎ×ÎØÎÙÎÚÎÛÎÜÎÝÎÞÎßÎàÎáÎâÎãÎäÎåÎæÎçÎèÎéÎêÎëÎìÎíÎîÎïÎðÎñÎòÎóÎôÎõÎöÎ÷ÎøÎùÎúÎûÎüÎýÎþÏ¡Ï¢Ï£Ï¤Ï¥Ï¦Ï§Ï¨Ï©ÏªÏ«Ï¬Ï­Ï®Ï¯Ï°Ï±Ï²Ï³Ï´ÏµÏ¶Ï·Ï¸Ï¹ÏºÏ»Ï¼Ï½Ï¾Ï¿ÏÀÏÁÏÂÏÃÏÄÏÅÏÆÏÇÏÈÏÉÏÊÏËÏÌÏÍÏÎÏÏÏÐÏÑÏÒÏÓÏÔÏÕÏÖÏ×ÏØÏÙÏÚÏÛÏÜÏÝÏÞÏßÏàÏáÏâÏãÏäÏåÏæÏçÏèÏéÏêÏëÏìÏíÏîÏïÏðÏñÏòÏóÏôÏõÏöÏ÷ÏøÏùÏúÏûÏüÏýÏþÐ¡Ð¢Ð£Ð¤Ð¥Ð¦Ð§Ð¨Ð©ÐªÐ«Ð¬Ð­Ð®Ð¯Ð°Ð±Ð²Ð³Ð´ÐµÐ¶Ð·Ð¸Ð¹ÐºÐ»Ð¼Ð½Ð¾Ð¿ÐÀÐÁÐÂÐÃÐÄÐÅÐÆÐÇÐÈÐÉÐÊÐËÐÌÐÍÐÎÐÏÐÐÐÑÐÒÐÓÐÔÐÕÐÖÐ×ÐØÐÙÐÚÐÛÐÜÐÝÐÞÐßÐàÐáÐâÐãÐäÐåÐæÐçÐèÐéÐêÐëÐìÐíÐîÐïÐðÐñÐòÐóÐôÐõÐöÐ÷ÐøÐùÐúÐûÐüÐýÐþÑ¡Ñ¢Ñ£Ñ¤Ñ¥Ñ¦Ñ§Ñ¨Ñ©ÑªÑ«Ñ¬Ñ­Ñ®Ñ¯Ñ°Ñ±Ñ²Ñ³Ñ´ÑµÑ¶Ñ·Ñ¸Ñ¹ÑºÑ»Ñ¼Ñ½Ñ¾Ñ¿ÑÀÑÁÑÂÑÃÑÄÑÅÑÆÑÇÑÈÑÉÑÊÑËÑÌÑÍÑÎÑÏÑÐÑÑÑÒÑÓÑÔÑÕÑÖÑ×ÑØÑÙÑÚÑÛÑÜÑÝÑÞÑßÑàÑáÑâÑãÑäÑåÑæÑçÑèÑéÑêÑëÑìÑíÑîÑïÑðÑñÑòÑóÑôÑõÑöÑ÷ÑøÑùÑúÑûÑüÑýÑþÒ¡Ò¢Ò£Ò¤Ò¥Ò¦Ò§Ò¨Ò©ÒªÒ«Ò¬Ò­Ò®Ò¯Ò°Ò±Ò²Ò³Ò´ÒµÒ¶Ò·Ò¸Ò¹ÒºÒ»Ò¼Ò½Ò¾Ò¿ÒÀÒÁÒÂÒÃÒÄÒÅÒÆÒÇÒÈÒÉÒÊÒËÒÌÒÍÒÎÒÏÒÐÒÑÒÒÒÓÒÔÒÕÒÖÒ×ÒØÒÙÒÚÒÛÒÜÒÝÒÞÒßÒàÒáÒâÒãÒäÒåÒæÒçÒèÒéÒêÒëÒìÒíÒîÒïÒðÒñÒòÒóÒôÒõÒöÒ÷ÒøÒùÒúÒûÒüÒýÒþÓ¡Ó¢Ó£Ó¤Ó¥Ó¦Ó§Ó¨Ó©ÓªÓ«Ó¬Ó­Ó®Ó¯Ó°Ó±Ó²Ó³Ó´ÓµÓ¶Ó·Ó¸Ó¹ÓºÓ»Ó¼Ó½Ó¾Ó¿ÓÀÓÁÓÂÓÃÓÄÓÅÓÆÓÇÓÈÓÉÓÊÓËÓÌÓÍÓÎÓÏÓÐÓÑÓÒÓÓÓÔÓÕÓÖÓ×ÓØÓÙÓÚÓÛÓÜÓÝÓÞÓßÓàÓáÓâÓãÓäÓåÓæÓçÓèÓéÓêÓëÓìÓíÓîÓïÓðÓñÓòÓóÓôÓõÓöÓ÷ÓøÓùÓúÓûÓüÓýÓþÔ¡Ô¢Ô£Ô¤Ô¥Ô¦Ô§Ô¨Ô©ÔªÔ«Ô¬Ô­Ô®Ô¯Ô°Ô±Ô²Ô³Ô´ÔµÔ¶Ô·Ô¸Ô¹ÔºÔ»Ô¼Ô½Ô¾Ô¿ÔÀÔÁÔÂÔÃÔÄÔÅÔÆÔÇÔÈÔÉÔÊÔËÔÌÔÍÔÎÔÏÔÐÔÑÔÒÔÓÔÔÔÕÔÖÔ×ÔØÔÙÔÚÔÛÔÜÔÝÔÞÔßÔàÔáÔâÔãÔäÔåÔæÔçÔèÔéÔêÔëÔìÔíÔîÔïÔðÔñÔòÔóÔôÔõÔöÔ÷ÔøÔùÔúÔûÔüÔýÔþÕ¡Õ¢Õ£Õ¤Õ¥Õ¦Õ§Õ¨Õ©ÕªÕ«Õ¬Õ­Õ®Õ¯Õ°Õ±Õ²Õ³Õ´ÕµÕ¶Õ·Õ¸Õ¹ÕºÕ»Õ¼Õ½Õ¾Õ¿ÕÀÕÁÕÂÕÃÕÄÕÅÕÆÕÇÕÈÕÉÕÊÕËÕÌÕÍÕÎÕÏÕÐÕÑÕÒÕÓÕÔÕÕÕÖÕ×ÕØÕÙÕÚÕÛÕÜÕÝÕÞÕßÕàÕáÕâÕãÕäÕåÕæÕçÕèÕéÕêÕëÕìÕíÕîÕïÕðÕñÕòÕóÕôÕõÕöÕ÷ÕøÕùÕúÕûÕüÕýÕþÖ¡Ö¢Ö£Ö¤Ö¥Ö¦Ö§Ö¨Ö©ÖªÖ«Ö¬Ö­Ö®Ö¯Ö°Ö±Ö²Ö³Ö´ÖµÖ¶Ö·Ö¸Ö¹ÖºÖ»Ö¼Ö½Ö¾Ö¿ÖÀÖÁÖÂÖÃÖÄÖÅÖÆÖÇÖÈÖÉÖÊÖËÖÌÖÍÖÎÖÏÖÐÖÑÖÒÖÓÖÔÖÕÖÖÖ×ÖØÖÙÖÚÖÛÖÜÖÝÖÞÖßÖàÖáÖâÖãÖäÖåÖæÖçÖèÖéÖêÖëÖìÖíÖîÖïÖðÖñÖòÖóÖôÖõÖöÖ÷ÖøÖùÖúÖûÖüÖýÖþ×¡×¢×£×¤×¥×¦×§×¨×©×ª×«×¬×­×®×¯×°×±×²×³×´×µ×¶×·×¸×¹×º×»×¼×½×¾×¿×À×Á×Â×Ã×Ä×Å×Æ×Ç×È×É×Ê×Ë×Ì×Í×Î×Ï×Ð×Ñ×Ò×Ó×Ô×Õ×Ö×××Ø×Ù×Ú×Û×Ü×Ý×Þ×ß×à×á×â×ã×ä×å×æ×ç×è×é×ê×ë×ì×í×î×ï×ð×ñ×ò×ó×ô×õ×ö×÷×ø×ù";

function yxglXybdModi(){
	if((curr_row == null)|| (typeof(curr_row) == undefined)){
		alert("ÇëÑ¡ÔñÒªÐÞ¸ÄµÄ¼ÇÂ¼£¡£¡");
		return false;
	}
	showTopWin('yxgl_xybd_one.do?active=modify&ksh='+curr_row.cells[1].innerText.replace(/^\s+/g,"").replace(/\s*$/g,""),600,450);
}

function yxglXyybdModi(){
	if((curr_row == null)|| (typeof(curr_row) == undefined)){
		alert("ÇëÑ¡ÔñÒªÐÞ¸ÄµÄ¼ÇÂ¼£¡£¡");
		return false;
	}
	showTopWin('yxgl_xyybd_one.do?active=modify&ksh='+curr_row.cells[1].innerText.replace(/^\s+/g,"").replace(/\s*$/g,""),600,450);
}
function yxglStbdModi(){
	if((curr_row == null)|| (typeof(curr_row) == undefined)){
		alert("ÇëÑ¡ÔñÒªÐÞ¸ÄµÄ¼ÇÂ¼£¡£¡");
		return false;
	}
	showTopWin('yxgl_stbd_one.do?active=modify&ksh='+curr_row.cells[1].innerText.replace(/^\s+/g,"").replace(/\s*$/g,""),600,450);
}
function yxglSsbdModi(){
	if((curr_row == null)|| (typeof(curr_row) == undefined)){
		alert("ÇëÑ¡ÔñÒªÐÞ¸ÄµÄ¼ÇÂ¼£¡£¡");
		return false;
	}
	showTopWin('yxgl_ssbd_one.do?active=modify&ksh='+curr_row.cells[1].innerText.replace(/^\s+/g,"").replace(/\s*$/g,""),600,450);
}

function yxglXsbdModi(type){
	var ksh = document.getElementById("ksh").value;
	var xh = document.getElementById("xh").value;
	if((ksh == null)||ksh == '' ){
		alert("ÇëÊäÈë¿¼ÉúºÅ£¡£¡");
		return false;
	}
	if(xh.length > 0){
		yxglFun.isXhExists(xh,function(data){
			if(!data){
				alert("¶Ô²»Æð£¬¸ÃÑ§ºÅ²»´æÔÚ");
				return false;
			}
		});
	}else{
		if(type == "xy"){
		showTopWin('yxgl_xybd_one.do?active=modify&ksh='+ksh.replace(/^\s+/g,"").replace(/\s*$/g,""),600,450);
		}else if(type == "yy"){
		showTopWin('yxgl_xyybd_one.do?active=modify&ksh='+ksh.replace(/^\s+/g,"").replace(/\s*$/g,""),600,450);	
		}else if(type == "st"){
		showTopWin('yxgl_stbd_one.do?active=modify&ksh='+ksh.replace(/^\s+/g,"").replace(/\s*$/g,""),600,450);
		}else if(type == "ss"){
		showTopWin('yxgl_ssbd_one.do?active=modify&ksh='+ksh.replace(/^\s+/g,"").replace(/\s*$/g,""),600,450);
		}
	}
}

function yxglXybdDelete(){
	if((curr_row == null)|| (typeof(curr_row) == undefined)){
		alert("ÇëÑ¡ÔñÒªÉ¾³ýµÄ¼ÇÂ¼£¡£¡");
		return false;
	}
	document.forms[0].action = "yxgl_xybd.do?active=delete&ksh="+curr_row.cells[1].innerText.replace(/^\s+/g,"").replace(/\s*$/g,"");	
	underDealWith();
	document.forms[0].submit();
}

//µã»÷¿Õ¸ñ»ò»Ø³µÖ®ºó¼¤·¢ÏàÓ¦¶ÔÏó
function keyPressDo(obj){
	if(window.event.keyCode==13 || window.event.keyCode==32){
		obj.click();
	}
}
//½«½¹µãÒÆµ½ÏàÓ¦¶ÔÏó
function getfocus(objectId){
	var obj = document.getElementById(objectId);
	obj.focus();
}
//Ê¹¸¸´°¿ÚÏàÓ¦¶ÔÏó»ñµÃ½¹µã
function yxglXybdOneUnLoad(objId){
	var winObj = window.dialogArguments.document.getElementById(objId);
	winObj.focus();
}
//Ñ§Ôº±¨µ½Ò³Ãæ¼ÓÔØ
function yxglBdLoad(){
	var flag = document.getElementById("flag").value;
	if (flag == 'no'){
		return false;
	} else {
		getfocus('add');
	}
}
//ÓÃÓÚÉ¨Ãè¿¼ÉúºÅ·¢Éú±ä»¯Ê±´¥·¢
function inputChange(){
	var bdType = document.getElementById("bdType").value;
	if(bdType == "xybd"){
		document.forms[0].action="yxgl_xybd_one.do";	
	}
	document.forms[0].submit();
}
//Ñ§Éú±¨µ½µ¥Êý¾ÝÖÐÅÐ¶ÏÊÇ·ñÐè¼ìÒß
function sfjyFun(){
	var sfjyFlag = document.getElementById("sfjyFlag").value;
	if(sfjyFlag == "yes"){
		document.getElementById("sfjy").style.display = block;
	}	
}

//¸ù¾ÝÎÄ±¾¿òÖÐÊäÈëµÄÖµ»ñµÃÊ¡·Ý´úÂë
function getSf(obj){
	var sfmc = obj.value;
	var sel = document.getElementById("sfdmId");
	for(i=0;i<sel.options.length;i++){
		if(sel.options[i].innerText.search(sfmc) != -1){
			sel.selectedIndex = i;
			obj.value = sel.options[i].innerText;
		}
	}
}
//Ð£Ò½ÔºÉè¶¨¼ìÒßÇøÓò
function setMedicineArea(){
	document.forms[0].action = "yxgl_xyybd_sdjyqy.do";
	document.forms[0].submit();
}
//Ð£Ò½Ôºµ¥¸öÑ§Éú±¨µ½ÐÅÏ¢ÐÞ¸Ä
function yxglXyybdModi(){
	if((curr_row == null)|| (typeof(curr_row) == undefined)){
		alert("ÇëÑ¡ÔñÒªÐÞ¸ÄµÄ¼ÇÂ¼£¡£¡");
		return false;
	}
	showTopWin('yxgl_xyybd_one.do?active=modify&ksh='+curr_row.cells[1].innerText.replace(/^\s+/g,"").replace(/\s*$/g,""),600,450);
}

function yxglBdDelete(url){
	if(curr_row == null || typeof(curr_row) == undefined){
		alert("ÇëÑ¡ÔñÒªÉ¾³ýµÄ¼ÇÂ¼£¡£¡");
		return false;
	}
	document.forms[0].action=url+'?active=delete&pk='+curr_row.cells[1].innerText.replace(/^\s+/g,"").replace(/\s*$/g,"");
	document.forms[0].submit();
}

function getParentPageSearch(){
	refreshFlag++;
	window.dialogArguments.document.getElementById('search_go').click();
}
//²éÑ¯º¯Êý
function search(url){
	if (refreshFlag != 0||document.getElementById("ksh").value!=""||document.getElementById("xh").value!=""||document.getElementById("xm").value!="") {
		refreshFlag = 0;
		document.forms[0].go.value = "go";
		refreshForm(url);
	} else {
		allNotEmpThenGo(url);
	}
}

//±¨µ½ÐÞ¸Ä
function yxglBdModi(url){
	if((curr_row == null)|| (typeof(curr_row) == undefined)){
		alert("ÇëÑ¡ÔñÒªÐÞ¸ÄµÄ¼ÇÂ¼£¡£¡");
		return false;
	}
	showTopWin(url+'?active=modify&ksh='+curr_row.cells[1].innerText.replace(/^\s+/g,"").replace(/\s*$/g,""),600,500);
}

function updataChange(){
	var bdType = document.getElementById("bdType").value;
	if(bdType == "xybd"){
		document.forms[0].action="yxgl_xybd_one.do";	
	}
	document.forms[0].submit();
}

function yxglXsglModi(){
	if((curr_row == null)|| (typeof(curr_row) == undefined)){
		alert("ÇëÑ¡ÔñÒªÐÞ¸ÄµÄ¼ÇÂ¼£¡£¡");
		return false;
	}
	showTopWin('yxgl_xsgl_one.do?active=modify&ksh='+curr_row.cells[1].innerText.replace(/^\s+/g,"").replace(/\s*$/g,""),600,450);
}

/*
	²é¿´ÐÅÏ¢
*/
function yxglXsglView(){
	if((curr_row == null)|| (typeof(curr_row) == undefined)){
		alert("ÇëÑ¡ÔñÒªÐÞ¸ÄµÄ¼ÇÂ¼£¡£¡");
		return false;
	}
	showTopWin('yxgl_xsgl_one.do?active=view&ksh='+curr_row.cells[1].innerText.replace(/^\s+/g,"").replace(/\s*$/g,""),600,450);
}
function search2(url){
	if (refreshFlag != 0) {
		refreshFlag = 0;
		document.forms[0].go.value = "go";
		refreshForm(url);
	} else {
		allNotEmpThenGo(url);
	}
}

function yxgltableSort(the_td) {//
	arrowUp = document.createElement("SPAN");
	arrowUp.innerHTML = "5";
	arrowUp.style.cssText = "PADDING-RIGHT: 0px; MARGIN-TOP: -3px; PADDING-LEFT: 0px; FONT-SIZE: 10px; MARGIN-BOTTOM: 2px; PADDING-BOTTOM: 2px; OVERFLOW: hidden; WIDTH: 10px; COLOR: blue; PADDING-TOP: 0px; FONT-FAMILY: webdings; HEIGHT: 11px";
	arrowDown = document.createElement("SPAN");
	arrowDown.innerHTML = "6";
	arrowDown.style.cssText = "PADDING-RIGHT: 0px; MARGIN-TOP: -3px; PADDING-LEFT: 0px; FONT-SIZE: 10px; MARGIN-BOTTOM: 2px; PADDING-BOTTOM: 2px; OVERFLOW: hidden; WIDTH: 10px; COLOR: blue; PADDING-TOP: 0px; FONT-FAMILY: webdings; HEIGHT: 11px";
	the_td.mode = !the_td.mode;
	var cur_col = the_td.cellIndex+1;
	var the_table = getPapaElement(the_td, "table");
	if (the_table.rows.length > 200) {
		if (!confirm("µ±Ç°±íµÄÐÐÊý³¬¹ý200ÐÐ,ÅÅÐò½«ºÄ·Ñ±È½Ï³¤µÄÊ±¼ä,È·¶¨ÒªÅÅÐòÂð?")) {
			return false;
		}
	}
	if (sort_col != null) {
		with (the_table.rows[0].cells[sort_col]) {
			removeChild(lastChild);
		}
	}
	with (the_table.rows[0].cells[cur_col]) {
		appendChild(the_td.mode ? arrowUp : arrowDown);
	}
	sort_tab(the_table, cur_col, the_td.mode);
	sort_col = cur_col;
}

function getSfValue(){
   var getSelectText = $("sfdmId").options[$("sfdmId").selectedIndex].text;
   var realValue = document.getElementById("format").value;
   if(realValue==""){
   document.getElementById("format").value = getSelectText; 
   }else{
   document.getElementById("format").value = realValue+","+getSelectText;
   }
   var getSelectValue =document.getElementById("sfdmId").value;
   var realValue = document.getElementById("sxsfdm").value;
   if(realValue==""){
   document.getElementById("sxsfdm").value = getSelectValue; 
   }else{
   document.getElementById("sxsfdm").value = realValue+","+getSelectValue;
   }                
}

function yxglNotSfmc(){
	var sfmc = document.getElementById("sfmc").value;
	if((sfmc == null)||sfmc == '' ){
		alert("ÇëÑ¡ÔñÊ¡·Ý£¡£¡");
		return false;
	}
	document.forms[0].action="yxgl_xyybd_sdjyqy.do?doType=modify";
	document.forms[0].submit();
}
function xybdjs(){
	var xxdm = document.getElementById("xxdm").value;
	var ksh = document.getElementById("ksh").value;
	if(xxdm == "10463"){//ºÓÄÏ¹¤Òµ´óÑ§
		document.location= "yxgl_hngy_dybdd.do?ksh=" + ksh;
	}else{
		if(document.getElementById('sflsj')){
		var sflsj = document.getElementById('sflsj').value;
		}
		window.open('/xgxt/yxgl_bjly_xsbdd.do?ksh='+document.getElementById('ksh').value+'&xh='+document.getElementById('xh').value+'&sflsj='+sflsj);
		window.dialogArguments.document.getElementById('search_go').click();
		window.close();
	}
	
}

/**
	¸Ãº¯ÊýÕë¶ÔÓÚºÓÄÏ¹¤Òµ´óÑ§£¬ÒÔ¿¼ÉúºÅ×÷Îª±ê×¼£¨ksh£©
	¹æ·¶±¨µ½Á÷³Ì£¬ÏÈÈ¥Ñ§Ôº±¨µ½£¬ÔÙÈ¥ËÞÉá±¨µ½  
*/
function judgeXybdBySchool(){
	//var xh = document.forms[0].xh.value;
	var ksh = document.getElementById("ksh").value;
	var xxdm = document.forms[0].xxdm.value;
	if(xxdm == "10463"){ //ºÓÄÏ¹¤Òµ´óÑ§  ËÞÉá±¨µ½
		if($("xybd").value=="·ñ"){
				alert("¶Ô²»Æð£¬ÇëÏÈµ½Ñ§Ôº±¨µ½!");
				return;
			}else{
				doBD('ss');
			}
	}else{
		doBD('ss');
	}	
}

function doBD(bdlx){
	if(!confirm("È·¶¨Òª±¨µ½Âð?")){
		return;
	}
	var xxdm = "";
	if($("xxdm")){
		xxdm = $("xxdm").value;
	}
	var xh = document.forms[0].xh.value; 
	var array = "";	
	var ksh = document.forms[0].ksh.value;
	if(bdlx == "xy"){ //Ñ§Ôº±¨µ½
		if($("sflsj")){
			var sflsj = document.getElementById("sflsj").value;
			array = (sflsj == null) ? "" : sflsj;
		}	
	}else if(bdlx == "yy"){ //Ò½Ôº±¨µ½
		var sfzsym = (document.getElementById("sfzsym").value == null) ? "1" : document.getElementById("sfzsym").value;
		var sftj = (document.getElementById("sftj").value == null) ? "1" : document.getElementById("sftj").value;
		array = sfzsym + "-" + sftj;
	}
	/*
		¶ÔÓÚÊ³ÌÃºÍËÞÉá±¨µ½£¬Ã»ÓÐ¶àµÄ²ÎÊý¿ÉÒÔ´«µÝ£¬²»±Ø×ö´¦Àí£¡
//	*/
//	dwr.engine.setAsync(false);
	yxglFun.xsbdOneByOne(ksh,xh,bdlx,array,function(data){
		if(data == true){
			//¶ÔÓÚºÓÄÏ¹¤Òµ´óÑ§µÄÑ§Ôº±¨µ½Èç¹û³É¹¦Ö±½ÓÌø×ªµ½´²Î»·ÖÅäµÄ½çÃæ
			if(bdlx == "xy" && xxdm == "10463"){
				//ÅÐ¶Ï¸ÃÑ§ÉúÊÇ·ñÒÑ¾­ÊÇ·ÖÅäÁË·¿¼ä
				if($("qsh").value==""){
				    conText ="±¨µ½³É¹¦£¬ÊÇ·ñÒªÎª¸ÃÉú·ÖÅä·¿¼ä(´²Î»)£¿";
				}else{
				    conText ="±¨µ½³É¹¦£¬ÒÑ¾­Îª¸ÃÉú·ÖÅäÁË·¿¼ä(´²Î»)£¬ÊÇ·ñÒªÖØÐÂ·ÖÅä£¿";
				}
				if(confirm(conText)){
				   showTopWin('/xgxt/csmz_gygl.do?method=xsZsxxAdd&xh='+ksh,500,300);
				}
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			}else{
				alert('±¨µ½³É¹¦');
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
				alert('!');
			}	
		}else{
			alert('±¨µ½Ê§°Ü');
		}
	});
	
}

/**
¸Ä±äÒª²Ù×÷±íµÄÃû×Ö
*/
function chaTable(tableName){
	if(tableName == "view_newstureportinfo"){
		document.getElementById("realTable").value = "view_newstureportinfo";
		document.getElementById("tableName").value = "view_newstureportinfo";
	}else if(tableName == "newstusinfo"){
		document.getElementById("realTable").value = "newstusinfo";
		document.getElementById("tableName").value = "newstusinfo";
	}	
}

/*
	¸ù¾ÝÂ¥¶°´úÂë·µ»ØÏàÓ¦µÄÇÞÊÒÁÐ±í
*/
function initQsList(){
	var lddm = $("lddm").value;	
	yxglFun.getQsListByLddm(lddm,function initTjList(data){
	if (data != null && typeof data == 'object') {
		var objId = data[0].dm; //qsh
		if($(objId) && $(objId).tagName.toLowerCase() == "select"){
		DWRUtil.removeAllOptions(objId);			
		DWRUtil.addOptions(objId,data,"dm","mc");		
		$(objId).options[0].value = "";
		if($(objId + "V")){
		if($(objId +"V").value != "" && $(objId + "V").value != null){
		for(var i = 0;i < $(objId).options.length; i++){
		if($(objId).options[i].value == $(objId +"V").value){
			$(objId).options[i].selected = true;
		return true;
        }}}}}
	}else{
		showMsgWin("ÓÐ´íÎó³öÏÖ£ºÔ¶³ÌÊý¾Ý¶ÁÈ¡Ê§°Ü£¡");
	}
	});
}

/*
	Èç¹ûÊÇÑ¡ÔñÁËÂ¥¶°£¬ÄÇÃ´¾ÍÔÚ±¨µÀÀà±ðÉÏÑ¡ÔñÊÇ¡®ËÞÉá±¨µ½¡¯
*/
function judgeIsNull(){
	//var ldmc = document.forms[0].bgzldm.options[document.forms[0].bgzldm.selectedIndex].text; //[ËÞÉá±¨µ½]
	var ldmc = document.forms[0].lddm.options[document.forms[0].lddm.selectedIndex].text;
	if(ldmc != ""){
		var ldmc = document.forms[0].bgzldm.options[4].selected = true; //ËÞÉá±¨µ½µÄÑ¡Ïî
		//document.forms[0].bgzldm.disabled = true;
	}else{
		//document.forms[0].bgzldm.disabled = false;
	}
}

/**
	µ¼ÈëÒ³ÃæµÄÊ±ºò£¬Èç¹ûÖ®Ç°ÓÐÑ¡ÔñÂ¥¶°£¬¾Í½«ÇÞÊÒÁÐ±í¼ÓÉÏ
*/
function getQshListBe(){
	if($("lddm")){
		var ldmc = document.forms[0].lddm.options[document.forms[0].lddm.selectedIndex].text;
		if(ldmc != ""){
			initQsList();
		}
	}	
}

/**
	calNum Í³¼Æ
*/

function calNum(id,num){
	$("tishi").innerHTML = "";  //ÏÈÇå¿Õ
	var id = $(id).value;
	totalNum = 0;
	for(var i=0;i< id.length;i++){
		if(charPYStr.indexOf(id.substring(i,i+1)) > 0){
			totalNum += 2;
		}else{
			totalNum += 1;
		}
	}
	if(num < totalNum){
		$("tishi").innerHTML =
		  "<font color='blue'>³¬¹ýÏÞ¶¨×Ö·û,ÄãÒÑ¾­ÊäÈëÁË<font color='red'>" + totalNum + "</font>¸ö×Ö·û</font>";
	}
}

/*
	½«ËùÓÐµÄÎÄ±¾¿ò£¬ÏÂÀ­¿òºÍÎÄ±¾ÓòÉèÖÃÎªdisableµÄ
*/
function setAllDisabled(sp) {
	//var inp = document.getElementsByTagName("input");
	//var inp = document.getElementById("table").getElementsByTagName("input");
	//var table = document.body.getElementsByTagName("TABLE");
	//var inp = table.getElementsByTagName("input");
	var inp = sp.split('-');
	var sel = document.getElementsByTagName("select");
	var area = document.getElementsByTagName("textarea");
	
	for (i = 0; i < inp.length; i++) {
		//if(inp[i].type == "text"){
			//inp[i].disabled = true;
		//}	
		$(inp[i]).disabled = true;
	}
	for (i = 0; i < sel.length; i++) {
		sel[i].disabled = true;
	}
	for (i = 0; i < area.length; i++) {
		area[i].disabled = true;
	}
}

/*
	ÊÍ·ÅËùÓÐÎÄ±¾¿òµÄreadonly»òdisabledµÄÖµÎªfalse
*/
function releaseAllElement(sp){
	var inp = sp.split('-');
	for (i = 0; i < inp.length; i++) {
		if($(inp[i]).disabled == true){
			$(inp[i]).disabled = false;
		}else if($(inp[i]).readOnly == true){
			$(inp[i]).readOnly = false;
		}
	}
}

/*
	½«´ø*ºÅµÄÑ¡Ïî¶¼ÌîÉÏ£¬Ôò·µ»Øtrue
*/
function allFillYxgl(str){
	var array = str.split('-');
	for(i=0;i<array.length;i++){
		if(document.getElementById(array[i]).value == ""){
			alert("Çë½«´ø\*ºÅµÄÏîÄ¿ÌîÐ´ÍêÕû£¡");
			return false;
		}
	}
	return true;
}

/*
	Ìá½»Ç°µÄÑéÖ¤
*/
function beforeSubmit(){
	var str = "ksh-xm-xb-xy-zy";
	if(!allFillYxgl(str)){
		return ;
	}
	var ksh = $("ksh").value;
	yxglFun.isKshExists(ksh,function(data){
		if(data == true){
			alert("¸Ã¿¼ÉúºÅÒÑ¾­´æÔÚ");
		}else{
			document.forms[0].action='yxgl_xsgl_one.do?doType=add';
			document.forms[0].submit();
			window.dialogArguments.document.getElementById('search_go').click();
			window.close();
		}	
	});	
}

/*
	³·Ïú¸ÃÑ§ÉúµÄ±¨µÀ¹¦ÄÜ
*/
function cancleBd(type){
	if(curr_row != null){
		var ksh =  curr_row.cells[1].innerText.trim();
		yxglFun.cancleBd(ksh,type,function(data){
			if(data){
				alert("³·Ïú±¨µ½³É¹¦!");
				if(confirm("ÊÇ·ñË¢ÐÂ¸ÃÒ³Ãæ")){
					document.getElementById("search_go").click();
				}	
			}else{
				alert("³·Ïú±¨µ½Ê§°Ü!");
			}
		});
	}else{
		alert("ÇëÑ¡ÔñÒª³·Ïú±¨µ½µÄ¼ÇÂ¼£¡\n£¨µ¥»÷ÏàÓ¦µÄÐÐ£©");
	}
}

/**
	¸ù¾ÝÑ§ÔºµÃµ½Ñ§ÉúµÄ±¨µ½ÐÅÏ¢¹¦ÄÜ
*/
function getStuDataByXy(expType){
	if(curr_row != null){
		var xydm = curr_row.cells[0].getElementsByTagName("input")[0].value;	
		var tag = curr_row.getElementsByTagName("select")[0].value;
		var url = "/xgxt/getStuDataByXy.do?xydm="+xydm+"&tag="+tag+"&expType="+expType;
		if(expType == "print"){   //´òÓ¡
			showTopWin(url,700,550);
		}else if(expType == "exp"){ //µ¼³ö
			window.open(url);
		}
	}
}

/**
	·µ»ØËùÓÐÑ§ÉúµÄ±¨µ½ÐÅÏ¢
*/
function getTotalStuData(expType){
	if(curr_row != null){
		var tag = curr_row.getElementsByTagName("select")[0].value;
		var url = "/xgxt/getStuDataByXy.do?school=true&tag=" + tag + "&expType=" + expType;
		if(expType == "print"){   //´òÓ¡
			showTopWin(url,700,550);
		}else if(expType == "exp"){ //µ¼³ö
			window.open(url);
		}
	}
}

/**
	µÃµ½µ±Ç°µÄÊ±¼ä
*/
function getNowDate(){
	 var newdate = document.getElementById("newdate");	
	 var Timer=new Date()
	 var year=Timer.getYear();
	 var month=Timer.getMonth()+1;
	 var date_=Timer.getDate();
	 var day=Timer.getDay()+1;
	 var hours=Timer.getHours()
	 var minutes=Timer.getMinutes()
	 var seconds=Timer.getSeconds() 
	 
	 function initArray(){
		 this.length=initArray.arguments.length
		  for(var i=0;i<this.length;i++)
		    this[i+1]=initArray.arguments[i]  
		  }
		 if (hours==0)
		   hours=12
		           if (minutes<=9)
		   minutes="0"+minutes
		     if (seconds<=9)
		   seconds="0"+seconds 
		 var d=new initArray("ÐÇÆÚÈÕ","ÐÇÆÚÒ»","ÐÇÆÚ¶þ","ÐÇÆÚÈý","ÐÇÆÚËÄ","ÐÇÆÚÎå","ÐÇÆÚÁù"); 
		 var clock=""+year+"Äê"+month+"ÔÂ"+date_+"ÈÕ" + " " +hours + ":"+minutes+":"+seconds+" " + d[day];
    newdate.innerHTML=clock;//×¢ÒâÕâÀïµÄpostionÊÇÒ»¸ö±ê¼ÇµÄid
    setTimeout("getNowDate()",1000);
}
