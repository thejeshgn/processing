import processing.core.*; 
import processing.xml.*; 

import java.applet.*; 
import java.awt.*; 
import java.awt.image.*; 
import java.awt.event.*; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class filters extends PApplet {

PFont font;
String fontpath = "ArialMT-200.vlw";
int fontstart = 300;
int fontend = 8;
float fontsize = fontstart;
float fontsizedecrease = 0.97f;
PImage img;
String imgpath = "mlk10.jpg";
String letters = "IhaveadreamIamhappytojoinwithyoutodayinwhatwillgodowninhistoryasthegreatestdemonstrationforfreedominthehistoryofournationFivescoreyearsagoagreatAmericaninwhosesymbolicshadowwestandtodaysignedtheEmancipationProclamationThismomentousdecreecameasagreatbeaconlightofhopetomillionsofNegroslaveswhohadbeensearedintheflamesofwitheringinjusticeItcameasajoyousdaybreaktoendthelongnightoftheircaptivityButonehundredyearslatertheNegrostillisnotfreeOnehundredyearslaterthelifeoftheNegroisstillsadlycrippledbythemanaclesofsegregationandthechainsofdiscriminationOnehundredyearslatertheNegrolivesonalonelyislandofpovertyinthemidstofavastoceanofmaterialprosperityOnehundredyearslatertheNegroisstilllanguishedinthecornersofAmericansocietyandfindshimselfanexileinhisownlandAndsowevecomeheretodaytodramatizeashamefulconditionInasensewevecometoournationscapitaltocashacheckWhenthearchitectsofourrepublicwrotethemagnificentwordsoftheConstitutionandtheDeclarationofIndependencetheyweresigningapromissorynotetowhicheveryAmericanwastofallheirThisnotewasapromisethatallmenyesblackmenaswellaswhitemenwouldbeguaranteedtheunalienableRightsofLifeLibertyandthepursuitofHappinessItisobvioustodaythatAmericahasdefaultedonthispromissorynoteinsofarashercitizensofcolorareconcernedInsteadofhonoringthissacredobligationAmericahasgiventheNegropeopleabadcheckacheckwhichhascomebackmarkedinsufficientfundsButwerefusetobelievethatthebankofjusticeisbankruptWerefusetobelievethatthereareinsufficientfundsinthegreatvaultsofopportunityofthisnationAndsowevecometocashthischeckacheckthatwillgiveusupondemandtherichesoffreedomandthesecurityofjusticeWehavealsocometothishallowedspottoremindAmericaofthefierceurgencyofNowThisisnotimetoengageintheluxuryofcoolingoffortotakethetranquilizingdrugofgradualismNowisthetimetomakerealthepromisesofdemocracyNowisthetimetorisefromthedarkanddesolatevalleyofsegregationtothesunlitpathofracialjusticeNowisthetimetoliftournationfromthequicksandsofracialinjusticetothesolidrockofbrotherhoodNowisthetimetomakejusticearealityforallofGodschildrenItwouldbefatalforthenationtooverlooktheurgencyofthemomentThisswelteringsummeroftheNegroslegitimatediscontentwillnotpassuntilthereisaninvigoratingautumnoffreedomandequalityNineteensixtythreeisnotanendbutabeginningAndthosewhohopethattheNegroneededtoblowoffsteamandwillnowbecontentwillhavearudeawakeningifthenationreturnstobusinessasusualAndtherewillbeneitherrestnortranquilityinAmericauntiltheNegroisgrantedhiscitizenshiprightsThewhirlwindsofrevoltwillcontinuetoshakethefoundationsofournationuntilthebrightdayofjusticeemergesButthereissomethingthatImustsaytomypeoplewhostandonthewarmthresholdwhichleadsintothepalaceofjusticeIntheprocessofgainingourrightfulplacewemustnotbeguiltyofwrongfuldeedsLetusnotseektosatisfyourthirstforfreedombydrinkingfromthecupofbitternessandhatredWemustforeverconductourstruggleonthehighplaneofdignityanddisciplineWemustnotallowourcreativeprotesttodegenerateintophysicalviolenceAgainandagainwemustrisetothemajesticheightsofmeetingphysicalforcewithsoulforceThemarvelousnewmilitancywhichhasengulfedtheNegrocommunitymustnotleadustoadistrustofallwhitepeopleformanyofourwhitebrothersasevidencedbytheirpresenceheretodayhavecometorealizethattheirdestinyistiedupwithourdestinyAndtheyhavecometorealizethattheirfreedomisinextricablyboundtoourfreedomWecannotwalkaloneAndaswewalkwemustmakethepledgethatweshallalwaysmarchaheadWecannotturnbackTherearethosewhoareaskingthedevoteesofcivilrightsWhenwillyoubesatisfiedWecanneverbesatisfiedaslongastheNegroisthevictimoftheunspeakablehorrorsofpolicebrutalityWecanneverbesatisfiedaslongasourbodiesheavywiththefatigueoftravelcannotgainlodginginthemotelsofthehighwaysandthehotelsofthecitiesWecannotbesatisfiedaslongasthenegrosbasicmobilityisfromasmallerghettotoalargeroneWecanneverbesatisfiedaslongasourchildrenarestrippedoftheirselfhoodandrobbedoftheirdignitybysignsstatingForWhitesOnlyWecannotbesatisfiedaslongasaNegroinMississippicannotvoteandaNegroinNewYorkbelieveshehasnothingforwhichtovoteNonowearenotsatisfiedandwewillnotbesatisfieduntiljusticerollsdownlikewatersandrighteousnesslikeamightystream\u00b9IamnotunmindfulthatsomeofyouhavecomehereoutofgreattrialsandtribulationsSomeofyouhavecomefreshfromnarrowjailcellsAndsomeofyouhavecomefromareaswhereyourquestquestforfreedomleftyoubatteredbythestormsofpersecutionandstaggeredbythewindsofpolicebrutalityYouhavebeentheveteransofcreativesufferingContinuetoworkwiththefaiththatunearnedsufferingisredemptiveGobacktoMississippigobacktoAlabamagobacktoSouthCarolinagobacktoGeorgiagobacktoLouisianagobacktotheslumsandghettosofournortherncitiesknowingthatsomehowthissituationcanandwillbechangedLetusnotwallowinthevalleyofdespairIsaytoyoutodaymyfriendsAndsoeventhoughwefacethedifficultiesoftodayandtomorrowIstillhaveadreamItisadreamdeeplyrootedintheAmericandreamIhaveadreamthatonedaythisnationwillriseupandliveoutthetruemeaningofitscreedWeholdthesetruthstobeselfevidentthatallmenarecreatedequalIhaveadreamthatonedayontheredhillsofGeorgiathesonsofformerslavesandthesonsofformerslaveownerswillbeabletositdowntogetheratthetableofbrotherhoodIhaveadreamthatonedayeventhestateofMississippiastateswelteringwiththeheatofinjusticeswelteringwiththeheatofoppressionwillbetransformedintoanoasisoffreedomandjusticeIhaveadreamthatmyfourlittlechildrenwillonedayliveinanationwheretheywillnotbejudgedbythecoloroftheirskinbutbythecontentoftheircharacterIhaveadreamtodayIhaveadreamthatonedaydowninAlabamawithitsviciousracistswithitsgovernorhavinghislipsdrippingwiththewordsofinterpositionandnullificationonedayrightthereinAlabamalittleblackboysandblackgirlswillbeabletojoinhandswithlittlewhiteboysandwhitegirlsassistersandbrothersIhaveadreamtodayIhaveadreamthatonedayeveryvalleyshallbeexaltedandeveryhillandmountainshallbemadelowtheroughplaceswillbemadeplainandthecrookedplaceswillbemadestraightandthegloryoftheLordshallberevealedandallfleshshallseeittogetherThisisourhopeandthisisthefaiththatIgobacktotheSouthwithWiththisfaithwewillbeabletohewoutofthemountainofdespairastoneofhopeWiththisfaithwewillbeabletotransformthejanglingdiscordsofournationintoabeautifulsymphonyofbrotherhoodWiththisfaithwewillbeabletoworktogethertopraytogethertostruggletogethertogotojailtogethertostandupforfreedomtogetherknowingthatwewillbefreeonedayAndthiswillbethedaythiswillbethedaywhenallofGodschildrenwillbeabletosingwithnewmeaningMycountrytisoftheesweetlandoflibertyoftheeIsingLandwheremyfathersdiedlandofthePilgrimsprideFromeverymountainsideletfreedomringAndifAmericaistobeagreatnationthismustbecometrueAndsoletfreedomringfromtheprodigioushilltopsofNewHampshireLetfreedomringfromthemightymountainsofNewYorkLetfreedomringfromtheheighteningAllegheniesofPennsylvaniaLetfreedomringfromthesnowcappedRockiesofColoradoLetfreedomringfromthecurvaceousslopesofCaliforniaButnotonlythatLetfreedomringfromStoneMountainofGeorgiaLetfreedomringfromLookoutMountainofTennesseeLetfreedomringfromeveryhillandmolehillofMississippiFromeverymountainsideletfreedomringAndwhenthishappenswhenweallowfreedomringwhenweletitringfromeveryvillageandeveryhamletfromeverystateandeverycitywewillbeabletospeedupthatdaywhenallofGodschildrenblackmenandwhitemenJewsandGentilesProtestantsandCatholicswillbeabletojoinhandsandsinginthewordsoftheoldNegrospiritualFreeatlastFreeatlastThankGodAlmightywearefreeatlast";
char[] chars = new char[52];
int nchars = 0;
int iterations = 500;
int c = 0;
PGraphics letter,lettersquare,drawing;

public void setup(){
  //initialize the sketch
  size(438,600);
  background(255);
  //initialize the font
  //font = loadFont(fontpath);
  ///*
  for(int i=0;i<letters.length();i++){
    boolean found = false;
    char lc = letters.charAt(i);
    for(int j=0;j<nchars;j++){
      if(chars[j]==lc){
        found = true;
        break;
      }
    }
    if(!found) chars[nchars++] = lc;
  }
  chars = (char[]) subset(chars,0,nchars);
  font = createFont("Arial",200,true,chars);
  //*/
  textAlign(CENTER,CENTER);
  //load the image that will be filled with letters
  img = loadImage(imgpath);
  //posterize the image
  img.filter(THRESHOLD,0.4f);
  img.filter(BLUR,3);
  img.filter(THRESHOLD,0.6f);
  //initialize the drawing buffers
  letter = createGraphics((int)fontsize,(int)fontsize,JAVA2D);
  lettersquare = createGraphics((int)fontsize,(int)fontsize,P2D);
  drawing = createGraphics(width,height,JAVA2D);
  drawing.beginDraw();
  drawing.background(255);
  drawing.endDraw();
}

public void draw(){
  background(255);
  if(floor(fontsize)>fontend&&c<letters.length()-1){
    if(!letterfit()){
      fontsize *= fontsizedecrease;
    }else{
      c++;
      if(c==11){
        fontsize *= 0.75f;
      }
    }
    tint(255);
    image(drawing,0,0);
    if(mousePressed){
      tint(255,100);
      image(img,0,0);
    }
  }else{
    tint(255);
    image(drawing,0,0);
    println(c+" "+letters.length());
    /*
    save("mlk-"+hour()+""+minute()+""+second()+".tif");
    exit();
    */
    noLoop();
  }
}

public boolean letterfit(){
  letter.beginDraw();
  letter.background(255,0);
  letter.fill(0); 
  letter.textAlign(CENTER,CENTER);
  letter.translate(fontsize/2,fontsize/2); 
  letter.rotate(random(TWO_PI));
  letter.scale(fontsize/fontstart); 
  letter.textFont(font); 
  letter.smooth();
  letter.text(letters.charAt(c),0,0); 
  letter.endDraw();
  lettersquare.beginDraw(); 
  lettersquare.background(255); 
  lettersquare.image(letter,0,0);
  lettersquare.filter(ERODE);
  lettersquare.filter(ERODE);
  lettersquare.endDraw();
  for(int i=0;i<iterations;i++){
    int x = floor(random(width-fontsize));
    int y = floor(random(height-fontsize));
    boolean fits = true;
    for(int dx=0;dx<fontsize&&fits;dx++){ 
      for(int dy=0;dy<fontsize&&fits;dy++){
        if(brightness(img.get(x+dx,y+dy))>127){
          fits = false;
          break;
        }
        if(brightness(lettersquare.get(dx,dy))<127){ 
          if(brightness(drawing.get(x+dx,y+dy))<127){
            fits = false;
          }
        }
      }
    }
    if(fits){
      drawing.beginDraw();
      drawing.image(letter,x,y);
      drawing.endDraw();
      return true;
    }
  }
  return false;
}

/*
void mousePressed(){
  save("mlk-"+hour()+""+minute()+""+second()+".tif");
  exit();
}
*/

  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#F0F0F0", "filters" });
  }
}
