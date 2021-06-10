package com.example.dheerajmajor;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity3 extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main3);
        TextView disease=findViewById(R.id.disease);
        String title= getIntent().getStringExtra("title");
        String bitmap= getIntent().getStringExtra("img");
        String desc="",treat="";

        float conf=getIntent().getFloatExtra("conf",0.0f);
        disease.setText(title);
        Bitmap bitmap1=StringToBitMap(bitmap);
        TextView textView=findViewById(R.id.treatment);
        TextView textView1=findViewById(R.id.second_desc);

        switch(title.toLowerCase().trim())
        {
            case "apple apple scab":
                desc="Apple scab is the most common disease of apple and crabapple trees in Minnesota. Scab is caused by a fungus that infects both leaves and fruit. Scabby fruit are often unfit for eating. Infected leaves have olive green to brown spots.";
                treat="Apple scab overwinters primarily in fallen leaves and in the soil. Disease development is favored by wet, cool weather that generally occurs in spring and early summer. Fungal spores are carried by wind, rain or splashing water from the ground to flowers, leaves or fruit. During damp or rainy periods, newly opening apple leaves are extremely susceptible to infection. The longer the leaves remain wet, the more severe the infection will be. Apple scab spreads rapidly between 55-75 degrees F.";
                break;

            case "apple black rot":
                desc="Black rot is a disease of apples that infects fruit, leaves, and bark caused by the fungus Botryosphaeria obtusa. It can also jump to healthy tissue on pear or quince trees but is typically a secondary fungus of weak or dead tissues in other plants. Begin checking your apple trees for signs of infection about a week after the petals fall from your apple blossoms.\n" +
                        "\n" +
                        "Read more at Gardening Know How: What Is Black Rot: Treating Black Rot On Apple Trees https://www.gardeningknowhow.com/edible/fruits/apples/black-rot-on-apple-trees.htm";
                treat="Treating black rot on apple trees starts with sanitation. Because fungal spores overwinter on fallen leaves, mummified fruits, dead bark, and cankers, it’s important to keep all the fallen debris and dead fruit cleaned up and away from the tree. During the winter, check for red cankers and remove them by cutting them out or pruning away the affected limbs at least six inches (15 cm.) beyond the wound. Destroy all infected tissue immediately and keep a watchful eye out for new signs of infection. Once black rot disease is under control in your tree and you’re again harvesting healthy fruits, make sure to remove any injured or insect-invaded fruits to avoid re-infection.";
                break;
            case "apple cedar apple rust":
                desc="Gymnosporangium juniperi-virginianae is a plant pathogen that causes cedar-apple rust. In virtually any location where apples or crabapples and Eastern red-cedar coexist, cedar apple rust can be a destructive or disfiguring disease on both the apples and cedars.";
                treat="his is critical in the spring, when the juniper galls are releasing their spores.\n" +
                    "\n" +
                    "The time to treat your tree is between the pink stage of the blossoms (when the leaves are turning green) to the period when the petals drop.\n" +
                    "\n" +
                    "The most effective types of fungicides to use are those that inhibit fungal sterols. They are known as “SI,” or sterol inhibitors.\n" +
                    "\n" +
                    "In the old days, sprays for apple scab would also take care of cedar apple rust. However, this is no longer the case.\n" +
                    "\n" +
                    "The fungus that causes apple scab is now frequently resistant to the sterol-inhibiting fungicides, and manufacturers have moved on to using newer classes of fungicides. Only certain types of fungicides are effective.\n" +
                    "\n" +
                    "Extension agents at North Carolina State University attribute this trend to an increase in occurrences on apple trees in the state.\n" +
                    "\n" +
                    "Unfortunately, captan, the fungicide in many pre-mixed sprays for home fruit trees, does not work on on this particular fungal pathogen.\n" +
                    "\n" +
                    "Several extension agencies recommend that you use Immunox® to control cedar apple rust. It contains myclobutanil as its active ingredient.";
            break;
            case "apple healthy":
                desc="No Diseases Found,Healthy Apple";
            treat="water it daily";
            break;
            case "blueberry healthy":
                desc="No Diseases Found,Healthy BlueBerries";
                treat="water it daily";
                break;
            case "cherry including sour powdery mildew":
                desc="Powdery mildew of sweet and sour cherry is caused by Podosphaera clandestina, an obligate biotrophic fungus. Mid- and late-season sweet cherry (Prunus avium) cultivars are commonly affected, rendering them unmarketable due to the covering of white fungal growth on the cherry surface";
                treat="Manage irrigation. In the arid west in a typical dry spring, early irrigation may stimulate early cherry powdery mildew infections. For example, in 2002 Dr. Gary Grove documented the initiation of cherry powdery mildew infections in relationship to spring irrigation. Initial symptoms appeared 7-10 days after the first irrigation of the season in all five orchards monitored. Generally, the ground is still moist in spring and early irrigation may only jumpstart your powdery mildew season versus help your trees. Recent research has shown that a two-week delay in irrigation delayed disease initiation two weeks with no negative impact on fruit.\n" +
                    "Pruning. Humid conditions favor cherry powdery mildew. A well pruned canopy will promote more air flow and leaf drying, reducing these humid conditions favorable for disease. Pruning will also help to achieve good spray coverage.\n" +
                    "Root sucker management. Root suckers are a preferred by powdery mildews since they are comprised of highly susceptible leaf tissue. Root suckers are close to the irrigation and high humidity levels favor the onset and spread of infections. Additionally, sprays aimed at the canopy often do not protect root suckers allowing fungal inocula to survive all season. Remove root suckers to eliminate this source for infection of the canopy and fruit";
            break;
            case "cherry including sour healthy":
                desc="No Diseases Found,Healthy Cherry";
                treat="water it daily";
                break;
            case "corn maize cercospora leaf spot gray leaf spot":
                desc="Grey leaf spot (GLS) is a foliar fungal disease that affects maize, also known as corn. ... There are two fungal pathogens that cause GLS: Cercospora zeae-maydis and Cercospora zeina. Symptoms seen on corn include leaf lesions, discoloration (chlorosis), and foliar blight.";
            treat="Management techniques include crop resistance, crop rotation, residue management, use of fungicides, and weed control. The purpose of disease management is to prevent the amount of secondary disease cycles as well as to protect leaf area from damage prior to grain formation.";
            break;
            case "corn maize common rust":
                treat="Numerous fungicides are available for rust control. Products containing mancozeb, pyraclostrobin, pyraclostrobin + metconazole, pyraclostrobin + fluxapyroxad, azoxystrobin + propiconazole, trifloxystrobin + prothioconazole can be used to control the disease.";
                desc="Common corn rust, caused by the fungus Puccinia sorghi, is the most frequently occurring of the two primary rust diseases of corn in the U.S., but it rarely causes significant yield losses in Ohio field (dent) corn. ... Sweet corn is generally more susceptible than field corn.";
            break;
            case "corn maize northern leaf blight":
                treat="If possible, planting in low areas that receive heavy dew and fog should be avoided. A combination of crop rotation for one to two years followed by tillage is recommended to prevent NCLB disease development. The use of foliar fungicides for corn have also been shown to control NCLB.";
                desc="Northern corn leaf blight (NCLB) or Turcicum leaf blight (TLB) is a foliar disease of corn (maize) caused by Exserohilum turcicum, the anamorph of the ascomycete Setosphaeria turcica. With its characteristic cigar-shaped lesions, this disease can cause significant yield loss in susceptible corn hybrids.";
            break;
            case "corn maize healthy":desc="No Diseases Found,Healthy Corn";
                treat="water it daily";
                break;
            case "grape black rot":
                treat="Mancozeb is available as BONIDE MANCOZEB FLOWABLE fungicide. It contains 37% Mancozeb and should be very effective for controlling black rot. Nova (myclobutanil) is available in IMMUNOX FUNGICIDE. It is 1.55 % myclobutanil and should be effective for controlling black rot.";
                desc="Grape black rot is a fungal disease caused by an ascomycetous fungus, Guignardia bidwellii, that attacks grape vines during hot and humid weather. “Grape black rot originated in eastern North America, but now occurs in portions of Europe, South America, and Asia. Wikipedia";
            break;
            case "grape esca black measles":
                treat="Presently, there are no effective management strategies for measles. Wine grape growers with small vineyards will often have field crews remove infected fruit prior to harvest. Raisins affected by measles will be discarded during harvest or at the packing house, while table grape growers will leave affected fruit on the vine. Current research is focused on protecting pruning wounds from fungal infections to minimize suspect fungi from colonizing fresh wounds.\n";
                desc="Grapevine measles, also called esca, black measles or Spanish measles, has long plagued grape growers with its cryptic expression of symptoms and, for a long time, a lack of identifiable causal organism(s). The name ‘measles’ refers to the superficial spots found on the fruit (Fig. 1). During the season, the spots may coalesce over the skin surface, making berries black in appearance. Spotting can develop anytime between fruit set and a few days prior to harvest. Berries affected at fruit set tend not to mature and will shrivel and dry up. In addition to spotting, fruit affected later in the season will also have an acrid taste.";
            break;
            case "grape leaf blight isariopsis leaf spot":
                treat="Till date there is no effective method to control this disease. Remove the infected berries, leaves and trunk and destroy them. Protect the prune wounds to minimize fungal infection using wound sealant (5% boric acid in acrylic paint) or essential oil or suitable fungicides.";
                desc="The Common or European grapevine (Vitis vinifera) is a long stemmed, woody vine (liana) which produces high value berries, or grapes. The vines can reach lengths in excess of 30 m and can live for many years with proper management. The leaves of the grape vine are alternately arranged on the stem and are long and broad with 5–7 lobes, typically reaching sizes of 5–20 cm (2.0–7.9 in). Flowers are produced in clusters and fruit. The fruit is a berry known as a grape and grows in clusters from the vine. In wild species, the fruit is 6 mm (1/5 in) in diameter and ripens to dark purple to black with a pale wax bloom. In cultivated plants, the berry is usually much larger, up to 3 cm (1.2 in) long and can be green, red or purple. Vitis vinifera is native to the Mediterranean region, central Europe, and southwestern Asia but is cultivated on every continent except Antarctica. Most grape cultivation centers on the use of Vitis vinifera, however, in North America the related species Vitis labrusca, Vitis riparia and Vitis rotundifolia are also grown. Vitis amurensis is native to Asia and has been hybridized with Vitis vinifera to produce cold tolerant grapevine varieties.";
            break;
            case "grape healthy":
                desc="No Diseases Found,Healthy Grape";
                treat="water it daily";
                break;
            case "orange haunglongbing citrus greening":
                treat="No cure for citrus greening disease is known, and efforts to control it have been slow because infected citrus plants are difficult to maintain, regenerate, and study. Ongoing challenges associated with mitigating disease at the field-scale include seasonality of the phytopathogen (Candidatus Liberibacter spp.)";
                desc="Huanglongbing (HLB), also known as citrus greening disease, is perhaps the most important citrus disease in areas where the disease and its vector are both present (Bové, 2006). The symptoms of include an asymmetrical mottling of the leaves; frequently the midribs of the leaves are yellowed.";
            break;
            case "peach bacterial spot":
                treat="Compounds available for use on peach and nectarine for bacterial spot include copper, oxytetracycline (Mycoshield and generic equivalents), and syllit+captan; however, repeated applications are typically necessary for even minimal disease control.";
                desc="Bacterial spot is an important disease of peaches, nectarines, apricots, and plums caused by Xanthomonas campestris pv. pruni. ... Leaf symptoms of bacterial spot on peaches and nectarines are generally dark, small lesions, often clustered at the leaf tip where water collects during dews and rain.";
            break;
            case "peach healthy":
                desc="No Diseases Found,Healthy Peach";
                treat="water it daily";
                break;
            case "pepper bell bacterial spot":
                treat="Seed treatment with hot water, soaking seeds for 30 minutes in water pre-heated to 125 F/51 C, is effective in reducing bacterial populations on the surface and inside the seeds. However, seed germination may be affected by heat treatment if not done accurately, while the risk is relatively low with bleach treatment.";
                desc="Bacterial spot is one of the most devastating diseases of pepper and tomato. The disease occurs worldwide where pepper and tomato are grown in warm, moist areas. When it occurs soon after transplanting and weather conditions remain favorable for disease development, the results are usually total crop loss.";
            break;
            case "pepper bell healthy":
                desc="No Diseases Found,Healthy Bell Pepper";
                treat="water it daily";
                break;
            case "potato early blight":
                treat="Treatment of early blight includes prevention by planting potato varieties that are resistant to the disease; late maturing are more resistant than early maturing varieties. Avoid overhead irrigation and allow for sufficient aeration between plants to allow the foliage to dry as quickly as possible.";
                desc="Early blight of potato is caused by the fungal pathogen Alternaria solani. The disease affects leaves, stems and tubers and can reduce yield, tuber size, storability of tubers, quality of fresh-market and processing tubers and marketability of the crop.";
            break;
            case "potato late blight":
                treat="The severe late blight can be effectively managed with prophylactic spray of mancozeb at 0.25% followed by cymoxanil+mancozeb or dimethomorph+mancozeb at 0.3% at the onset of disease and one more spray of mancozeb at 0.25% seven days after application of systemic fungicides in West Bengal";
                desc="Phytophthora infestans is an oomycete or water mold, a fungus-like microorganism that causes the serious potato and tomato disease known as late blight or potato blight. Early blight, caused by Alternaria solani, is also often called \"potato blight\".";
            break;
            case "potato healthy":
                desc="No Diseases Found,Healthy Potato";
                treat="water it daily";
                break;
            case "raspberry healthy":
                desc="No Diseases Found,Healthy Raspberry";
                treat="water it daily";
                break;
            case "soybean healthy":
                desc="No Diseases Found,Healthy SoyaBean";
                treat="water it daily";
                break;
            case "squash powdery mildew":
                treat="Baking Soda. Baking soda itself isn’t normally effective as a powdery mildew treatment, but when it’s combined with liquid soap and water, it can be a powerful weapon. It’s normally most beneficial if used as a preventative measure rather than a treatment. Combine one tablespoon baking soda and one-half teaspoon of liquid, non-detergent soap with one gallon of water, and spray the mixture liberally"
                +"\n"+
                "Organic Fungicide Treatments. If you don’t want a do-it-yourself solution, there is a variety of commercial treatment options that are just as environmentally friendly and approved for organic gardening. By going this route, you also know exactly what types of pests the treatment will kill and which types of plants it’s most helpful for."
                +"\n"+
                "Mouthwash. The mouthwash you may use on a daily basis for killing the germs in your mouth can also be effective at killing powdery mildew spores. Since its function is to kill germs, the powdery mildew spores can’t withstand it. Using three parts water to one part mouthwash has been found to be a good ratio, but new growth can be damaged since mouthwash is potent, so use with caution.";
                ;
                desc="Powdery mildew, mainly caused by the fungus Podosphaera xanthii, infects all cucurbits, including muskmelons, squash, cucumbers, gourds, watermelons and pumpkins. In severe cases, powdery mildew can cause premature death of leaves, and reduce yield and fruit quality.";
            break;
            case "strawberry leaf scorch":
                treat="While leaf scorch on strawberry plants can be frustrating, there are some strategies which home gardeners may employ to help prevent its spread in the garden. The primary means of strawberry leaf scorch control should always be prevention. Since this fungal pathogen overwinters on the fallen leaves of infected plants, proper garden sanitation is key. This includes the removal of infected garden debris from the strawberry patch, as well as the frequent establishment of new strawberry transplants. The creation of new plantings and strawberry patches is key to maintaining a consistent strawberry harvest, as older plants are more likely to show signs of severe infection. Read more at Gardening Know How: Strawberries With Leaf Scorch – Treating Strawberry Leaf Scorch Symptoms https://www.gardeningknowhow.com/edible/fruits/strawberry/strawberries-with-leaf-scorch.htm";
                desc="Diplocarpon earlianum is a fungus that causes leaf scorch, one of the most common leaf diseases of strawberry. This ascomycete produces disk-shaped, dark brown to black apothecia (0.25-1 mm) on advanced-stage lesions on strawberry leaves and leaf residues";
            break;
            case "strawberry healthy":
                desc="No Diseases Found,Healthy Strawberry";
                treat="water it daily";
                break;
            case "tomato bacterial spot":
                treat="A plant with bacterial spot cannot be cured. Remove symptomatic plants from the field or greenhouse to prevent the spread of bacteria to healthy plants. Burn, bury or hot compost the affected plants and DO NOT eat symptomatic fruit.";
                desc="Bacterial spot can affect all above ground parts of a tomato plant, including the leaves, stems, and fruit. Bacterial spot appears on leaves as small (less than ⅛ inch), sometimes water-soaked (i.e., wet-looking) circular areas. Spots may initially be yellow-green, but darken to brownish-red as they age.";
            break;
            case "tomato early blight":
                treat="Treatment. Tomatoes that have early blight require immediate attention before the disease takes over the plants. Thoroughly spray the plant (bottoms of leaves also) with Bonide Liquid Copper Fungicide concentrate or Bonide Tomato & Vegetable. Both of these treatments are organic.";
                desc="Early blight is one of the most common tomato diseases, occurring nearly every season wherever tomatoes are grown. It affects leaves, fruits and stems and can be severely yield limiting when susceptible cultivars are used and weather is favorable. Severe defoliation can occur and result in sunscald on the fruit.";
            break;
            case "tomato late blight":
                treat="Use fungicide sprays based on mandipropamid, chlorothalonil, fluazinam, mancozeb to combat late blight. Fungicides are generally needed only if the disease appears during a time of year when rain is likely or overhead irrigation is practiced.";
                desc="Tomato late blight is caused by the oomycete pathogen Phytophthora infestans (P. infestans). The pathogen is best known for causing the devastating Irish potato famine of the 1840s, which killed over a million people, and caused another million to leave the country. Late blight on tomato leaf.";
            break;
            case "tomato leaf mold":
                treat="Use drip irrigation and avoid watering foliage. Use a stake, strings, or prune the plant to keep it upstanding and increase airflow in and around it. Remove and destroy (burn) all plants debris after the harvest.";
                desc="Tomato leaf mold is a fungal disease that can develop when there are extended periods of leaf wetness and the relative humidity is high (greater than 85 percent). ... The optimal temperature tomato leaf mold is in the low 70s. Symptoms of disease include yellow spots on the upper leaf surface.";
            break;
            case "tomato septoria leaf spot":
                treat="Removing infected leaves. Remove infected leaves immediately, and be sure to wash your hands and pruners thoroughly before working with uninfected plants.\n" +
                        "Consider organic fungicide options. Fungicides containing either copper or potassium bicarbonate will help prevent the spreading of the disease. Begin spraying as soon as the first symptoms appear and follow the label directions for continued management.\n" +
                        "Consider chemical fungicides. While chemical options are not ideal, they may be the only option for controlling advanced infections. One of the least toxic and most effective is chlorothalonil (sold under the names Fungonil and Daconil).";
                desc="Septoria leaf spot is caused by a fungus, Septoria lycopersici. It is one of the most destructive diseases of tomato foliage and is particularly severe in areas where wet, humid weather persists for extended periods. Septoria leaf spot usually appears on the lower leaves after the first fruit sets.";
            break;
            case "tomato spider mites two spotted spider mite":
                treat="The best way to begin treating for two-spotted mites is to apply a pesticide specific to mites called a miticide. Ideally, you should start treating for two-spotted mites before your plants are seriously damaged. Apply the miticide for control of two-spotted mites every 7 days or so.";
                desc="The two-spotted spider mite is the most common mite species that attacks vegetable and fruit crops in New England. Spider mites can occur in tomato, eggplant, potato, vine crops such as melons, cucumbers, and other crops. Two-spotted spider mites are one of the most important pests of eggplant.";
            break;
            case "tomato target spot":
                treat="Cage or stake tomato plants to keep the plants above the soil. Water tomato plants in the morning so the leaves have time to dry. Water at the base of the plant or use a soaker hose or drip system to keep the leaves dry. Apply a mulch to keep the fruit from coming in direct contact with the soil.";
                desc="Target spot is a fungal disease that mainly affects the foliage of many members of the Solanacea family such as potatoes, tomatoes and capsicums. It can also be transferred to the tubers of potatoes and occasionally the fruit of others. It is also referred to as 'early blight' on potatoes.";
            break;
            case "tomato tomato yellow leaf curl virus":
                treat="Use only virus-and whitefly-free tomato and pepper transplants. Transplants should be treated with Capture (bifenthrin) or Venom (dinotefuran) for whitefly adults and Oberon for eggs and nymphs. Imidacloprid or thiamethoxam should be used in transplant houses at least seven days before shipping.";
                desc="Tomato yellow leaf curl virus (TYLCV) is a tomato (Solanum lycopersicum)-infecting plant virus transmitted by whitefly Bemisia tabaci1,2. It belongs to the genus Begomovirus of the family Geminiviridae and has a single-stranded circular DNA genome of about 2.8 kb encapsidated in a twinned icosahedral virion";
            break;
            case "tomato tomato mosaic virus":
                treat="There are no cures for viral diseases such as mosaic once a plant is infected.\n" +
                        "Fungicides will NOT treat this viral disease.\n" +
                        "Plant resistant varieties when available or purchase transplants from a reputable source.\n" +
                        "Do NOT save seed from infected crops.";
                desc="Tomato mosaic virus (ToMV) can cause yellowing and stunting of tomato plants resulting in loss of stand and reduced yield. ToMV may cause uneven ripening of fruit, further reducing yield. Tobacco mosaic virus (TMV) was once thought to be more common on tomato.";
            break;
            case "tomato healthy":
                desc="No Diseases Found,Healthy Tomato";
                treat="water it daily";
                break;
            default:
//                throw new IllegalStateException("Unexpected value: " + desc);
        }

        textView.setText(treat);
        textView1.setText(desc);

        CircleImageView imageView=findViewById(R.id.plantimg);
        imageView.setImageBitmap(bitmap1);
    }
    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
}