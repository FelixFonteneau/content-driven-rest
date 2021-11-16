package com.felix.fonteneau.contentdrivenrest;

import com.felix.fonteneau.contentdrivenrest.model.Alternative;
import com.felix.fonteneau.contentdrivenrest.model.ApplicationDataString;
import com.felix.fonteneau.contentdrivenrest.model.Content;
import com.felix.fonteneau.contentdrivenrest.model.Contentable;
import com.felix.fonteneau.contentdrivenrest.service.ContentService;
import com.felix.fonteneau.contentdrivenrest.util.EntityGenerator;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ContentDrivenRestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DeserializationTests {

    @Autowired
    private ContentService contentService;

    @Test
    void generateContentsFromJsonText() throws Exception {
        String json =
                "{\"id\":\"id1\", \"type\":\"text\", \"text\":\"blablabla\"}";
        Contentable contentable = EntityGenerator.generateContentFromJson(json);
        assertEquals("id1", contentable.getId());
        assertEquals("blablabla", ((Content) contentable).getText());
    }


    @Test
    void generateContentsFromJsonLink() throws Exception {
        String json =
                "[{\"id\":\"id1\", \"type\":\"link\", \"link\":{\"to\":\"id2\"}}]";
        List<Contentable> list = EntityGenerator.generateContentsFromJson(json);
        assertEquals("id1", list.get(0).getId());
        assertEquals("id2", ((Content) list.get(0)).getLink().getTo());
    }

    @Test
    void generateContentsFromJsonMax() throws Exception {
        String json = "[{\"id\":\"test15\",\"type\":\"screen\",\"nestedContent\":[{\"type\":\"button\",\"text\":\"next\",\"link\":{\"to\":\"test16\"}},{\"type\":\"group\",\"nestedContent\":[{\"type\":\"bullet-list\",\"nestedContent\":[{\"type\":\"sound-effect\",\"media\":\"server.address\\/quality\\/behavior\",\"metadata\":{\"style\":[\"shock\",\"act\",\"place\",\"grass\",\"base\",\"fold\"]}},{\"type\":\"sound-effect\",\"media\":\"server.address\\/quality\\/behavior\",\"metadata\":{\"style\":[\"shock\",\"act\",\"place\",\"grass\",\"base\",\"fold\"]}}]},{\"type\":\"story\",\"text\":\"Request selection story profit room steam protest amusement. flame rhythm condition grip ink look mine. measure soap range animal adjustment pain size.\",\"metadata\":{\"style\":[\"question\",\"pull\",\"reward\",\"sand\",\"sense\",\"story\",\"linen\"]}}]},{\"type\":\"paragraph\",\"text\":\"Force butter night way expert smoke position. noise dust wine manager cork wind. road mine behavior land.<div> <br \\/> <\\/div>industry thing control reaction observation night need kiss business. stitch friend paste structure example direction son ink field. system shade committee wound middle sex sense fight ray reaction.<div> <br \\/> <\\/div>country night help quality copy trade. base opinion event wax pleasure sister competition increase. agreement meat rub land hole motion death. birth offer flower sense mist business effect river wine. daughter field plant page increase price.<div> <br \\/> <\\/div>cry punishment rate word industry society debt. meeting meal religion selection rest digestion animal trade statement. curve trouble oil flight level letter cover fiction. behavior attempt nation verse.<div> <br \\/> <\\/div>change motion news person burst fruit. copy place milk order. stone canvas stone view. bread care harbor sense theory. weather payment mark land purpose apparatus voice transport.\",\"metadata\":{\"style\":[\"writing\",\"business\",\"river\",\"day\",\"kick\",\"attempt\",\"oil\"]}}]},{\"id\":\"test16\",\"type\":\"screen\",\"nestedContent\":[{\"type\":\"button\",\"text\":\"next\",\"link\":{\"to\":\"test17\"}},{\"type\":\"button\",\"text\":\"home page\",\"link\":{\"to\":\"test0\"}},{\"type\":\"button\",\"text\":\"previous\",\"link\":{\"to\":\"test15\"}},{\"type\":\"bullet-list\",\"nestedContent\":[{\"type\":\"list\",\"nestedContent\":[{\"type\":\"text\",\"text\":\"Market attempt sense prose father. song hole blood fear servant smash. rain damage week idea sand fall lift shame sort cook.<div> <br \\/> <\\/div>desire journey noise stitch paper grass soup side smile test. room smile salt work bread answer. soap dust industry industry rate observation attention news. breath credit prose amusement night meal reaction owner butter iron. wood price religion interest.<div> <br \\/> <\\/div>sea stitch direction impulse. increase room error walk verse oil. coal question steam writing request oil taste owner. road law addition example. butter agreement secretary push.<div> <br \\/> <\\/div>sign payment stone tendency error. memory swim opinion market bread example offer number brass reaction. soap love rhythm attempt fact punishment curve opinion. music back sneeze existence stage roll. measure selection need soup brass shake.<div> <br \\/> <\\/div>weather care owner existence attraction sleep milk price wound voice. motion judge coal size representative verse transport cotton. discussion daughter ornament work crush interest digestion attention edge. twist reading mark structure pull error protest. day reason color stage roll.\",\"metadata\":{\"style\":[\"shock\",\"person\",\"view\",\"chance\",\"lift\",\"month\",\"measure\"]}},{\"type\":\"text\",\"text\":\"Market attempt sense prose father. song hole blood fear servant smash. rain damage week idea sand fall lift shame sort cook.<div> <br \\/> <\\/div>desire journey noise stitch paper grass soup side smile test. room smile salt work bread answer. soap dust industry industry rate observation attention news. breath credit prose amusement night meal reaction owner butter iron. wood price religion interest.<div> <br \\/> <\\/div>sea stitch direction impulse. increase room error walk verse oil. coal question steam writing request oil taste owner. road law addition example. butter agreement secretary push.<div> <br \\/> <\\/div>sign payment stone tendency error. memory swim opinion market bread example offer number brass reaction. soap love rhythm attempt fact punishment curve opinion. music back sneeze existence stage roll. measure selection need soup brass shake.<div> <br \\/> <\\/div>weather care owner existence attraction sleep milk price wound voice. motion judge coal size representative verse transport cotton. discussion daughter ornament work crush interest digestion attention edge. twist reading mark structure pull error protest. day reason color stage roll.\",\"metadata\":{\"style\":[\"shock\",\"person\",\"view\",\"chance\",\"lift\",\"month\",\"measure\"]}}]},{\"type\":\"paragraph\",\"text\":\"Silver hole silk mist. year thing edge insect. meat front summer wind paint society smash shade tin. fight fiction opinion humor grip authority wool. person detail burn vessel smell committee sense.<div> <br \\/> <\\/div>poison work family payment observation. connection part land wound test plant road growth tin industry. force thunder ray payment. distance smoke insect theory approval.<div> <br \\/> <\\/div>fear plant person rice son news. pull polish art stitch liquid sneeze rain record ink. time start impulse space wood waste glass paint. invention sense condition smash run control space sugar.<div> <br \\/> <\\/div>impulse law grip offer thing business selection degree ornament. shade measure fruit control. meat account bite tin. observation distance steel addition kiss current pull behavior. chalk substance belief cough.<div> <br \\/> <\\/div>fiction snow position polish wine space. song balance peace sand decision. turn feeling transport bread twist. statement example linen surprise coal.\",\"metadata\":{\"style\":[\"paste\",\"room\",\"thunder\",\"mother\",\"need\",\"science\",\"care\"]}}]},{\"type\":\"main-title\",\"text\":\"Copy hole run need sand poison crush care hope jump.\",\"metadata\":{\"style\":[\"market\",\"son\",\"flame\",\"slip\",\"addition\"]}}]}]";
        List<Contentable> list = EntityGenerator.generateContentsFromJson(json);
        assertEquals(2, list.size());
        assertEquals(3, ((Content) list.get(0)).getNestedContent().size());
    }

    @Test
    void generateContentsFromJsonAlternative() throws Exception {
        String json = "{\"id\":\"test2\",\"type\":\"ALTERNATIVE_REQUEST\",\"possibleAlternativesWithCondition\":[{{\"id\":\"test2\",\"type\":\"screen\",\"nestedContent\":[{\"type\":\"button\",\"text\":\"next\",\"link\":{\"to\":\"test2\\/0\\/0\"}},{\"type\":\"tab\",\"nestedContent\":[{\"type\":\"tab\",\"nestedContent\":[{\"type\":\"icon\",\"media\":\"server.address\\/lift\\/sky\",\"metadata\":{\"style\":[\"name\",\"competition\",\"destruction\",\"sugar\",\"end\",\"gold\"]}}]}]}]},{\"filtersName\":[\"alwaysTrue\"]}]},\"test2\\/0\\/0\":{\"id\":\"test2\\/0\\/0\",\"type\":\"screen\",\"nestedContent\":[{\"type\":\"button\",\"text\":\"next\",\"link\":{\"to\":\"1\"}},{\"type\":\"button\",\"text\":\"home page\",\"link\":{\"to\":\"test2\"}},{\"type\":\"button\",\"text\":\"previous\",\"link\":{\"to\":\"test2\"}},{\"type\":\"list\",\"nestedContent\":[{\"type\":\"group\",\"nestedContent\":[{\"type\":\"video\",\"media\":\"server.address\\/smile\\/crime\",\"metadata\":{\"style\":[\"fear\",\"development\",\"expert\",\"love\",\"cause\",\"memory\"]}}]}]}]}";
        Contentable contentable = EntityGenerator.generateContentFromJson(json);
        assertEquals("test2", contentable.getId());
        assertTrue(contentable instanceof Alternative);
        assertEquals(1, ((Alternative) contentable).getPossibleAlternativesWithCondition().size());

        contentService.addContentAsJson(json);
        contentService.getScreen("test2", new ApplicationDataString(""));

    }
}
