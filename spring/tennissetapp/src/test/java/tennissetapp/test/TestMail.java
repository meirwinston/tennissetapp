package tennissetapp.test;

import java.util.List;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import com.tennissetapp.config.DataConfig;
import com.tennissetapp.config.RootConfig;
import com.tennissetapp.config.SecurityConfig;
import com.tennissetapp.config.SocialConfig;
import com.tennissetapp.config.WebMvcConfig;
import com.tennissetapp.forms.FindByLocationForm;
import com.tennissetapp.persistence.dao.DaoManager;
import com.tennissetapp.persistence.entities.Address;
import com.tennissetapp.persistence.entities.TennisCenter;
import com.tennissetapp.service.MailService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
//locations={
//	"/hd/git/tennissetup/tennissetapp/src/main/webapp/etc/spring/root-context.xml"
//},
classes={
	RootConfig.class,
	DataConfig.class,
	SecurityConfig.class,
	SocialConfig.class,
	WebMvcConfig.class,
})
@Transactional
public class TestMail {
	protected Logger logger = Logger.getLogger(TestMail.class);
	
	@Autowired 
	MailService mailService;
	
	@Autowired
	DaoManager daoManager;
	
//	@Test
//	public void testSendMail(){
////		mailService.sendSystemMail("meirwinston@yahoo.com", "tennis setapp subject", "the message body");
////		System.out.println("--aAAAAAAAAAAAAA--------------" + mailService);
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("activationToken", UUID.randomUUID().toString());
//		mailService.sendActivateAccount("meirwinston@yahoo.com", map);
//	}
	
//	@Test
	public void testQeuries(){
//		FindByLocationForm form = new FindByLocationForm();
////		form.setTerm("Ba");
////		form.setMaxRows(12);
//		form.setLatitude("31.75541089999999");
//		form.setLongitude("35.20205480000004");
//		form.setDistance("200");
//		System.out.println(daoManager.findNearbyCourts(form.toArgs()).size());
	}
	
	@Transactional
	@Test
	public void addresses(){
		double[][] a = new double[][]{
				{-23.6817424,-46.7097809},
				{-19.9252565,-43.934682699999},
				{23.7642945,90.42876},
				{25.6913771,-80.2849225},
				{25.7255797,-80.2435376},
				{25.7364997,-80.218363599999},
				{25.7415184,-80.276946899999},
				{25.7463307,-80.1893175},
				{25.7611406,-80.231928999999},
				{25.768197,-80.193129},
				{25.7802919,-80.162955899999},
				{25.7818629,-80.137375599999},
				{25.7858303,-80.201365899999},
				{25.793797,-80.235978999999},
				{25.7938696,-80.186304899999},
				{25.7947567,-80.262296499999},
				{25.808705,-80.186794999999},
				{25.8100649,-80.207653999999},
				{25.8134551,-80.1865369},
				{25.8156733,-80.135671899999},
				{25.819406,-80.237063999999},
				{25.8208668,-80.219599},
				{25.8250483,-80.230676799999},
				{25.8269946,-80.180550999999},
				{25.8284361,-80.272765699999},
				{25.8287986,-80.2491261},
				{25.831368,-80.227162},
				{25.8333275,-80.203889399999},
				{25.8356629,-80.182426},
				{25.846877,-80.228774},
				{25.8570662,-80.123720499999},
				{25.8596192,-80.2377078},
				{25.8655349,-80.183333199999},
				{25.8667622,-80.284603699999},
				{25.8780477,-80.2875396},
				{30.1686295,-97.808629699999},
				{30.2274742999999,-97.7909958},
				{30.2274742999999,-97.7909958},
				{30.2366353,-97.789471899999},
				{30.2366353,-97.789471899999},
				{30.2385673,-97.6876295},
				{30.2406307,-97.767949199999},
				{30.2406307,-97.767949199999},
				{30.2406307,-97.767949199999},
				{30.2431237,-97.7511071},
				{30.2512436,-97.7744208},
				{30.2512436,-97.7744208},
				{30.267153,-97.743060799999},
				{30.267153,-97.743060799999},
				{30.267153,-97.743060799999},
				{30.267153,-97.743060799999},
				{30.267153,-97.743060799999},
				{30.267153,-97.743060799999},
				{30.267153,-97.743060799999},
				{30.267153,-97.743060799999},
				{30.267153,-97.743060799999},
				{30.267153,-97.743060799999},
				{30.2704749999999,-97.714241},
				{30.273597,-97.690926499999},
				{30.2746009,-97.770085999999},
				{30.2746009,-97.770085999999},
				{30.2752887,-97.8130021},
				{30.2775753,-97.750211299999},
				{30.2776965,-97.757600599999},
				{30.2779786,-97.8143327},
				{30.286365,-97.7647392},
				{30.286365,-97.7647392},
				{30.2965869,-97.709588},
				{30.3016655,-97.747253699999},
				{30.3078462,-97.726781},
				{30.3124,-97.655811},
				{30.3141189,-97.6813336},
				{30.3294046,-97.7604532},
				{30.3370945,-97.741392599999},
				{30.3444584,-97.7300209},
				{30.3644899,-97.710188899999},
				{31.9820486,-102.0920095},
				{31.9832887,-102.1179943},
				{32.6815558,-117.1753684},
				{32.696141,-117.18948599999},
				{32.700231,-117.10804289999},
				{32.7056843,-96.854456299999},
				{32.7209659,-117.15335829999},
				{32.7211526,-117.2327764},
				{32.7254013,-117.24474029999},
				{32.735687,-97.108065599999},
				{32.7399906,-117.22359419999},
				{32.7503257,-117.1344955},
				{32.7540562,-117.1388397},
				{32.755215,-96.751099},
				{32.7553984,-117.24044229999},
				{32.7587608,-96.780393199999},
				{32.7801399,-96.800451099999},
				{32.7828739,-117.2502301},
				{32.7907615,-117.1458637},
				{32.792863,-117.1694521},
				{32.797252,-96.7434905},
				{32.8004297,-117.15939639999},
				{32.8012513,-117.24520849999},
				{32.8021463,-117.22009809999},
				{32.80376,-117.1674636},
				{32.806563,-96.750770999999},
				{32.808861,-117.2022286},
				{32.8120756,-96.7937878},
				{32.8120769,-96.843548399999},
				{32.8205487,-117.21412229999},
				{32.8244918,-117.10412559999},
				{32.8328923,-117.19620279999},
				{32.83428,-117.2716113},
				{32.8353952,-117.2121389},
				{32.841225,-117.27788099999},
				{32.8445841,-96.812969599999},
				{32.8473359,-96.805387399999},
				{32.8502676,-96.7899989},
				{32.8586392,-96.789650199999},
				{32.8588986,-96.8018384},
				{32.8593671,-117.20678199999},
				{32.8764713,-96.7437762},
				{32.878062,-117.24267099999},
				{32.8800886,-96.823945299999},
				{32.8822411,-96.784812999999},
				{32.8824546,-96.8433287},
				{32.8863653,-96.724169299999},
				{33.3534437,-111.98598579999},
				{33.3705345,-112.05723089999},
				{33.3722112,-112.1374215},
				{33.3860466,-111.9851517},
				{33.3903843,-112.06469979999},
				{33.3997313,-112.0082909},
				{33.4051234,-112.06018819999},
				{33.4361077999999,-112.08045319999},
				{33.4471683,-112.0481356},
				{33.4687854,-112.05634099999},
				{33.4703355,-111.9827232},
				{33.4717051,-112.09135459999},
				{33.4859996,-112.08899389999},
				{33.4886737,-112.0563683},
				{33.4943399,-86.845457099999},
				{33.4984564,-112.0518202},
				{33.5176609,-112.0955662},
				{33.5296219,-112.10458679999},
				{33.5476624,-112.1270901},
				{33.662427,-84.444439999999},
				{33.6661027,-84.360530999999},
				{33.6829798,-84.4348547},
				{33.7014063,-84.3826247},
				{33.7041558,-84.355803199999},
				{33.705537,-84.461619999999},
				{33.7117263,-84.395076899999},
				{33.7119982,-84.410956899999},
				{33.7181173,-84.386911899999},
				{33.7189242,-84.3749851},
				{33.7191954,-84.3880085},
				{33.7195026,-84.42786},
				{33.7336854,-84.447485299999},
				{33.7366207,-84.3841022},
				{33.7380033,-84.347247799999},
				{33.7396187,-84.361792799999},
				{33.7409904,-84.3983023},
				{33.741183,-84.374861799999},
				{33.7422761,-84.4239233},
				{33.7441336,-84.769631699999},
				{33.7441336,-84.769631699999},
				{33.745087,-84.328111999999},
				{33.7456189,-84.380479499999},
				{33.7462247,-84.339764199999},
				{33.747166,-84.3013869},
				{33.7479932,-84.4011143},
				{33.7524779,-84.355608599999},
				{33.752512,-84.420556499999},
				{33.7527053,-84.4392558},
				{33.7528296,-84.375508299999},
				{33.7552598,-84.321364899999},
				{33.7567032,-84.4248377},
				{33.7588845,-84.4505026},
				{33.7602419,-84.4694984},
				{33.7648671,-84.338544899999},
				{33.7711857,-84.3424994},
				{33.77402,-84.440318999999},
				{33.7815385,-84.391945099999},
				{33.7842017,-84.4263075},
				{33.7868106,-84.3728591},
				{33.7923183,-84.3156132},
				{33.793798,-84.374111999999},
				{33.8046377,-84.417740999999},
				{33.807076,-84.3178858},
				{33.813697,-84.407702299999},
				{33.8179061,-84.3773337},
				{33.830032,-84.350969999999},
				{33.8326102,-84.407387999999},
				{33.8366036,-84.374010099999},
				{33.8463002,-84.436869099999},
				{33.8463002,-84.436869099999},
				{33.8477749,-84.433407699999},
				{33.8488195,-84.417035599999},
				{33.8507965,-84.3291926},
				{33.8559327,-84.380972199999},
				{33.9364006,-118.30466309999},
				{33.936529,-118.247739},
				{33.9442646,-118.2491449},
				{33.9461595,-118.2285444},
				{33.9481925,-118.309026},
				{33.9548119,-118.20859669999},
				{33.9555963,-118.26826399999},
				{33.956328,-118.32648169999},
				{33.9588630999999,-118.3112036},
				{33.9674719,-118.2422121},
				{33.974595,-118.19158099999},
				{33.9832541,-118.3035517},
				{33.9870828,-118.2192524},
				{33.9981581,-118.32879689999},
				{33.99821,-118.269918},
				{33.99821,-118.269918},
				{34.0277149,-118.2627581},
				{34.0277149,-118.26275809999},
				{34.0277149,-118.26275809999},
				{34.028741,-118.26212509999},
				{34.028741,-118.26212509999},
				{34.0297536,-118.2611647},
				{34.030873,-118.30333789999},
				{34.030873,-118.30333789999},
				{34.0370434,-118.1592365},
				{34.0392219,-118.2112052},
				{34.0399694,-118.21064849999},
				{34.0399694,-118.21064849999},
				{34.0443628,-118.2239608},
				{34.0443628,-118.22396079999},
				{34.0443628,-118.22396079999},
				{34.0492951,-118.1794853},
				{34.0492951,-118.1794853},
				{34.0497287,-118.27538859999},
				{34.0522342,-118.2436849},
				{34.0522342,-118.2436849},
				{34.0522342,-118.2436849},
				{34.055126,-118.332757},
				{34.0604068,-118.28389329999},
				{34.0611544,-118.2621691},
				{34.0611544,-118.2621691},
				{34.0611544,-118.2621691},
				{34.0642607,-118.28564929999},
				{34.0642607,-118.28564929999},
				{34.0643763,-118.2856509},
				{34.0666313,-118.1654947},
				{34.0670544,-118.28969089999},
				{34.0670544,-118.28969089999},
				{34.0681396,-118.2610033},
				{34.0685,-118.261319},
				{34.0685,-118.261319},
				{34.0689494,-118.32656529999},
				{34.0689494,-118.32656529999},
				{34.0706884,-118.18627129999},
				{34.0706884,-118.18627129999},
				{34.0756471,-118.1817495},
				{34.0756471,-118.1817495},
				{34.0792569,-118.2355775},
				{34.0792569,-118.23557749999},
				{34.0792569,-118.23557749999},
				{34.0951323,-118.20453609999},
				{34.0951323,-118.20453609999},
				{34.1145129,-118.19908709999},
				{34.1157194,-118.1608287},
				{34.1161995,-118.2336288},
				{34.1315798,-118.16066639999},
				{34.1332681,-118.2069541},
				{34.1335501,-118.2069569},
				{34.1385549,-118.24460999999},
				{34.1425078,-118.255075},
				{34.1515297,-118.20939799999},
				{34.1572157,-118.26948549999},
				{34.163255,-118.238868},
				{34.528455,69.1717029},
				{35.9626899,-78.719645799999},
				{36.0297921,-115.15059639999},
				{36.0325676,-115.1433369},
				{36.0714961,-115.11578839999},
				{36.1023602,-115.1121863},
				{36.1078801,-115.14778189999},
				{36.1105892,-115.23807469999},
				{36.11135,-86.783430899999},
				{36.1126489,-115.2320755},
				{36.114646,-115.172816},
				{36.114646,-115.172816},
				{36.114646,-115.172816},
				{36.114646,-115.172816},
				{36.114646,-115.172816},
				{36.1184992,-80.0805166},
				{36.1203096,-115.13106149999},
				{36.1300344,-115.20327259999},
				{36.1516131,-115.17975369999},
				{36.1525284,-115.2621783},
				{36.1904597,-115.217959},
				{36.2032052,-115.09244319999},
				{36.2172133,-115.2603672},
				{36.2176555,-115.09870439999},
				{36.2269464,-115.17763079999},
				{37.09024,-95.712891},
				{37.09024,-95.712891},
				{37.09024,-95.712891},
				{37.09024,-95.712891},
				{37.09024,-95.712891},
				{37.09024,-95.712891},
				{37.09024,-95.712891},
				{37.09024,-95.712891},
				{37.6621454,-122.4711991},
				{37.6646064,-122.4458061},
				{37.6648096,-122.4246431},
				{37.666935,-122.441183},
				{37.669959,-122.41705139999},
				{37.6860487,-122.48193149999},
				{37.6929638,-122.4142714},
				{37.6947802,-122.45920999999},
				{37.7066248,-122.4859296},
				{37.7094777999999,-122.4478126},
				{37.7138731999999,-122.4331951},
				{37.7143086,-122.4495099},
				{37.7159988,-122.4561105},
				{37.7174865,-122.4698538},
				{37.7205349,-122.4336041},
				{37.7216936,-122.44203729999},
				{37.7225231,-122.41243209999},
				{37.7240831,-122.48507789999},
				{37.7249667,-122.4449117},
				{37.7253361,-122.4734789},
				{37.7266375,-122.44502139999},
				{37.7287679,-122.4095507},
				{37.7290334,-122.46694479999},
				{37.7293607999999,-122.47458069999},
				{37.7323017,-122.40252169999},
				{37.7332043,-122.4467414},
				{37.7340852,-122.38942109999},
				{37.7346951,-122.47813819999},
				{37.7350727,-122.42021629999},
				{37.7364085,-122.439745},
				{37.7368783999999,-122.42109069999},
				{37.7392827,-122.44931869999},
				{37.7393186,-122.47591439999},
				{37.7394867,-122.3859517},
				{37.7417974,-122.46547729999},
				{37.7428108,-122.42783029999},
				{37.7432147,-122.481085},
				{37.7449289,-122.4399341},
				{37.7476661,-122.49859249999},
				{37.7478738,-122.43826209999},
				{37.7498979,-122.4056659},
				{37.7501704,-122.4683985},
				{37.7510211,-122.43885929999},
				{37.7520701,-122.4654615},
				{37.756932,-122.48635489999},
				{37.7572535,-122.41554739999},
				{37.7574024,-122.3970039},
				{37.7628972,-122.45170229999},
				{37.7644669,-122.43993069999},
				{37.7703578,-122.460255},
				{37.7736594,-122.4874785},
				{37.7749295,-122.4194155},
				{37.7756506,-122.43285559999},
				{37.7763044999999,-122.4274136},
				{37.7792441,-122.44212449999},
				{37.7795548,-122.45705559999},
				{37.7795999999999,-122.47824709999},
				{37.7798087,-122.49009949999},
				{37.779895,-122.4463243},
				{37.78272,-122.4913592},
				{37.7843283,-122.45072419999},
				{37.7846911,-122.47111719999},
				{37.7848686,-122.48401289999},
				{37.7849308,-122.43478859999},
				{37.7851866,-122.4776031},
				{37.7903121,-122.4375059},
				{37.7905451,-122.4542195},
				{37.7906884,-122.4274707},
				{37.7941248,-122.40754679999},
				{37.7960324,-122.4201504},
				{37.7960324,-122.4201504},
				{37.8008839,-122.4200352},
				{37.8013917,-122.4318698},
				{37.8020045,-122.4121344},
				{37.8392051999999,-94.354671899999},
				{37.8506103,-122.4825164},
				{37.8621493,-122.58136669999},
				{37.8814637,-122.4668585},
				{38.7595488,-77.1346949},
				{38.7705413,-77.0998716},
				{38.773914,-77.102278},
				{38.7757473,-77.013367099999},
				{38.776986,-77.070295999999},
				{38.7774971,-77.189016499999},
				{38.77986,-77.169797},
				{38.7997011,-77.092977799999},
				{38.8040803,-77.1515195},
				{38.8118081,-77.184662399999},
				{38.8123355,-77.0614676},
				{38.8137121,-77.041472699999},
				{38.8147409,-77.056538999999},
				{38.822339,-77.082587999999},
				{38.8252157,-77.193372299999},
				{38.8282594,-77.073699199999},
				{38.8311433999999,-77.0593205},
				{38.8346666,-77.128423399999},
				{38.8412916,-77.1267609},
				{38.842223,-77.1921256},
				{38.8424932,-77.1354372},
				{38.8437841,-77.087533399999},
				{38.8448609,-77.0992097},
				{38.8481797,-77.0692181},
				{38.848298,-77.107629999999},
				{38.8532066,-77.1242958},
				{38.8556201,-77.149664299999},
				{38.8556201,-77.149664299999},
				{38.8572439,-77.085490499999},
				{38.8621459,-77.1818521},
				{38.8624422,-77.070853399999},
				{38.8696168,-77.093295799999},
				{38.8745682,-77.1341171},
				{38.8763735,-77.0123618},
				{38.8775262,-77.0135718},
				{38.8793876,-77.1791281},
				{38.8819206,-77.150637599999},
				{38.8820774,-77.1023743},
				{38.8823752,-77.138329199999},
				{38.8896317,-77.103362699999},
				{38.8913953,-77.1583653},
				{38.8948225,-77.0942845},
				{38.9004942,-77.1135367},
				{38.9096240999999,-77.021151},
				{38.9097734,-77.0666668},
				{38.9098997,-77.1726888},
				{38.9108981,-77.1087158},
				{38.914747,-77.192267},
				{38.9160321,-77.139237499999},
				{38.9186587,-77.041670399999},
				{38.921929,-77.1262582},
				{38.9230433,-77.1865814},
				{38.9242008,-77.1533719},
				{38.925277,-77.1032966},
				{38.930124,-77.036374},
				{38.9348941,-77.074174999999},
				{38.9369371,-77.0271771},
				{38.9388868,-77.0746704},
				{38.940514,-77.071625999999},
				{38.946693,-77.0660429},
				{38.9507094,-77.0658025},
				{38.9507363,-77.0793676},
				{38.950822,-77.165587},
				{38.9516808,-77.1082478},
				{38.9518255,-77.120807899999},
				{38.9642547,-77.081272399999},
				{38.9674308,-77.121910299999},
				{38.9679623,-77.016195299999},
				{38.9686239,-77.0668732},
				{38.9699247,-77.0332933},
				{38.9736983,-77.1310285},
				{38.9752422,-77.093740099999},
				{38.9796745,-77.067151799999},
				{38.9819784,-77.1608658},
				{38.9830101,-77.086772399999},
				{38.9837,-77.102542999999},
				{38.984652,-77.0947092},
				{38.984696,-77.024575},
				{38.9851078,-77.061873699999},
				{38.985337,-77.089529999999},
				{38.9872416,-77.0210894},
				{38.9880512,-77.1508628},
				{38.9909317,-77.0844491},
				{38.991454,-77.125375299999},
				{38.9924372,-77.0997267},
				{38.9929681,-77.1075922},
				{38.996248,-77.0561452},
				{39.830391,-75.240460699999},
				{39.8968129,-92.484487899999},
				{39.9181686,-75.071283999999},
				{39.952335,-75.163789},
				{39.952335,-75.163789},
				{39.952335,-75.163789},
				{39.952335,-75.163789},
				{39.968808,18.0211515999999},
				{40.0112288,-75.1297422},
				{40.0361682,-75.2233873},
				{40.522611,-74.190037599999},
				{40.5779329,-73.9429819},
				{40.579585,-73.99696},
				{40.5936488,-74.135536699999},
				{40.5949648,-74.0012444},
				{40.5963112,-74.001367299999},
				{40.6004042,-73.9727457},
				{40.601211,-73.819666999999},
				{40.6024346,-73.762495},
				{40.6034564,-73.957509599999},
				{40.6039471,-73.781615399999},
				{40.6040402,-73.9576572},
				{40.6046289,-74.1622821},
				{40.6102573999999,-73.9485775},
				{40.6118435,-74.024055299999},
				{40.6119911,-73.9330429},
				{40.6120808,-74.0140275},
				{40.6123791,-74.035691999999},
				{40.6173752,-73.974605399999},
				{40.6189896,-73.900075799999},
				{40.6215043,-73.980709199999},
				{40.6223817,-73.9755635},
				{40.6252792,-74.0172731},
				{40.6274015,-74.0403092},
				{40.6281052,-74.039933099999},
				{40.6319215,-74.013295799999},
				{40.6321326,-73.9527243},
				{40.6344459,-73.916918},
				{40.644081,-74.108515},
				{40.6510157,-73.9714234},
				{40.6589543,-73.8871125},
				{40.659979,-73.7455792},
				{40.6610734,-73.761759499999},
				{40.6672766,-73.812374299999},
				{40.6673435,-73.9264296},
				{40.6755827,-73.7852922},
				{40.6755827,-73.7852922},
				{40.6758076,-73.9974191},
				{40.6785956,-73.932966099999},
				{40.6805943,-73.927631599999},
				{40.6807227,-73.7809315},
				{40.6811093,-73.9364595},
				{40.6839472,-73.972534399999},
				{40.6880777,-73.770172699999},
				{40.6883698,-73.8874799},
				{40.6896329,-73.9730812},
				{40.6904049,-73.999530999999},
				{40.6914808,-73.8854013},
				{40.69155,-73.974921999999},
				{40.6989194,-74.0817844},
				{40.7000227,-73.856608699999},
				{40.7015856,-73.7842172},
				{40.7152937,-73.975276099999},
				{40.7169743,-74.011466999999},
				{40.7211252,-73.8845994},
				{40.723731,-74.0888719},
				{40.7275956,-73.904750999999},
				{40.7354247,-74.0104026},
				{40.7357684,-73.8742859},
				{40.7367631,-73.7683784},
				{40.7367631,-73.7683784},
				{40.7400174,-73.735281999999},
				{40.7428053999999,-73.8448469},
				{40.7428053999999,-73.8448469},
				{40.7461756,-73.808086899999},
				{40.747316,-73.7429555},
				{40.7537964,-73.889317399999},
				{40.7593894,-74.0431176},
				{40.761386,-74.0676283},
				{40.7616814,-73.7328251},
				{40.7686679,-73.7469892},
				{40.7718307,-73.767643},
				{40.7737505,-73.8713099},
				{40.7765701,-73.923729299999},
				{40.7923636,-73.964215299999},
				{40.795813,-73.922516999999},
				{40.7961095,-73.976658899999},
				{40.7980022,-73.999282499999},
				{40.8010447,-73.917471499999},
				{40.8110256,-73.9643166},
				{40.8122741,-73.914073},
				{40.8188073,-73.922117099999},
				{40.820099,-73.911537399999},
				{40.8205375,-73.930816},
				{40.8209202,-73.9489945},
				{40.822522,-73.955461},
				{40.8240049,-73.8766722},
				{40.8249658,-73.9365119},
				{40.8366558,-73.892195899999},
				{40.8494748,-73.946673299999},
				{40.8524228,-73.8687104},
				{40.8655654,-73.89731},
				{40.8666005,-73.806503},
				{40.8715417,-73.925694799999},
				{40.8725333,-73.8395362},
				{40.875363,-72.508198999999},
				{40.879138,-73.882062},
				{40.884709,-73.890542699999},
				{40.885941,-73.9165092},
				{40.8886522,-72.536011},
				{40.937522,-72.294836},
				{40.961885,-72.186360999999},
				{40.970728,-72.200795999999},
				{40.972907,-72.148273799999},
				{41.4399936,-81.7392934},
				{41.5045982,-81.6765161},
				{41.7806042,-87.584597199999},
				{41.7811084,-87.6762201},
				{41.7938445,-87.6849829},
				{41.7955022,-87.626810999999},
				{41.7979895,-87.594170599999},
				{41.802465,-87.611009},
				{41.8110101,-87.633138199999},
				{41.8230359999999,-87.6812959},
				{41.8264565,-87.6010202},
				{41.830053,-87.609628999999},
				{41.8415983,-87.6479742},
				{41.8731529999999,-87.6205959},
				{41.8736053,-87.647019899999},
				{41.8757571,-87.715736699999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8781136,-87.629798199999},
				{41.8853527,-87.6652386},
				{41.9067696,-87.701287799999},
				{41.9111519,-87.631359999999},
				{41.925223,-87.678173},
				{41.9271334,-87.838778199999},
				{41.9594104,-87.698404999999},
				{41.9631007,-87.6862352},
				{41.9817028999999,-87.7179865},
				{41.9858948,-87.7284869},
				{41.9884897,-87.7114685},
				{41.9899211,-87.667550699999},
				{41.9904085,-87.703056399999},
				{42.3189984,-83.092336499999},
				{42.367781,-71.057919999999},
				{42.3736158,-71.1097335},
				{42.3776582,-71.069986299999},
				{43.16103,-77.6109219},
				{44.9238552,-92.9593797},
				{44.9238552,-92.9593797},
				{44.9396341,-93.2727565},
				{44.9401737,-93.253726499999},
				{44.9401737,-93.253726499999},
				{44.9628536,-93.2512772},
				{44.9677167,-93.2011319},
				{44.9688633,-93.2593279},
				{44.9713104,-93.2827112},
				{44.9771828,-93.3109255},
				{44.9788445,-93.231623799999},
				{44.9826088,-93.2988439},
				{44.983334,-93.266669999999},
				{44.983334,-93.266669999999},
				{44.983334,-93.266669999999},
				{44.983334,-93.266669999999},
				{44.983334,-93.266669999999},
				{44.983334,-93.266669999999},
				{44.983334,-93.266669999999},
				{44.983334,-93.266669999999},
				{44.983334,-93.266669999999},
				{44.983334,-93.266669999999},
				{44.983334,-93.266669999999},
				{44.983334,-93.266669999999},
				{44.983334,-93.266669999999},
				{44.983334,-93.266669999999},
				{44.983334,-93.266669999999},
				{44.983334,-93.266669999999},
				{44.983334,-93.266669999999},
				{44.983334,-93.266669999999},
				{44.983334,-93.266669999999},
				{44.983334,-93.266669999999},
				{44.996917,-93.243384699999},
				{44.9983948,-93.301995499999},
				{44.9992477,-93.252459499999},
				{45.0125208,-93.234603199999},
				{45.0295097,-93.235828599999},
				{45.0350013,-93.2944218},
				{45.4659599,9.26286570000002},
				{45.4785487,9.2366143},
				{45.4912762,9.2427112},
				{45.4942593,9.1972608},
				{45.4944187,9.231472},
				{45.881293,-95.3822721},
				{45.9858407,12.7035513},
				{46.2174355999999,10.1750296},
				{47.5152229,-122.2593507},
				{47.5396758,-122.3963501},
				{47.5526115,-122.3223411},
				{47.5631768999999,-122.3633681},
				{47.5676485,-122.2718102},
				{47.5706548,-122.22206729999},
				{47.5736423,-122.3043842},
				{47.5809164,-122.2886379},
				{47.5894635,-122.2944666},
				{47.6051268,-122.3022871},
				{47.6116231,-122.28934659999},
				{47.6317608,-122.31664319999},
				{47.6346869,-122.27713019999},
				{47.6649436999999,-122.31974009999},
				{47.6686942,-122.3038823},
				{47.6704999,-122.34262109999},
				{47.6816428,-122.3275386},
				{47.7074919999999,-122.29562299999},
				{50.003685,36.2474936999999},
				{51.5124441,-0.1786359999999},
				{64.8324588,-147.4617266},
				{64.8324588,-147.4617266}	
		};
		
		System.out.println("---start---");
		for(int i = 0 ; i < a.length ; i++){
//			List<Address> l = daoManager.findNearbyAddresses(a[i][0], a[i][1], 1000.0);
			List<Address> l = daoManager.findNearbyAddresses(a[i][0], a[i][1], 0.001);
			
			
			if(l.size() == 1){
//				System.out.println("--> " + i + ", " + a[i][0] + ", " +  a[i][1] + ", address: " + l.get(0));
				
				TennisCenter court = daoManager.findTennisCourt(a[i][0], a[i][1]);
				if(court != null){
//					court.setLatitude(l.get(0).getLatitude());
//					court.setLongitude(l.get(0).getLongitude());
//					
//					daoManager.merge(court);
//					daoManager.flush();
//					System.out.println("FOUND 1 " + a[i][0] + ", " + a[i][1] + " changed to " + l.get(0).getLatitude() + ", " + l.get(0).getLongitude());
//					System.out.println("The court is " + court.getLatitude() + ", " + court.getLongitude() + ", " );
					
					System.out.println("update TennisCourts set latitude = " + l.get(0).getLatitude()
							+ ", longitude = " + l.get(0).getLongitude() + " where latitude = " + a[i][0]
									+ " and longitude = " + a[i][1] + ";");
				}
			}
			else{
//				System.out.println("NOT FOUND: the size is " + l.size() + ", " + a[i][0] + ", " + a[i][1]);
			}
//			try {
//				Thread.sleep(50l);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		}
		System.out.println("---end---");
	}
	
}