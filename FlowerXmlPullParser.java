package app.mma.jsonxml2;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FlowerXmlPullParser {
    private InputStream input;
    private List<Flower> flowerList;
    private Flower flower;
    private String tag;

    public FlowerXmlPullParser(InputStream input) {
        this.input = input;
    }
    public List<Flower> parseXml(){
        flowerList=new ArrayList<>();
        try {
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser=factory.newPullParser();
            parser.setInput(input,null);
            int eventType=parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    handleStartTag(parser.getName());

                } else if (eventType == XmlPullParser.TEXT) {
                    handltext(parser.getText());

                } else if (eventType == XmlPullParser.END_TAG) {
                    tag=null;
                }
                eventType=parser.next();
            }
            input.close();
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return flowerList;
    }

    private void handltext(String text) {
        if (tag==null)return;
        switch (tag){
            case "productId" :
                flower.setProductId(Integer.valueOf(text));
                break;
            case "category":
                flower.setCategory(text);
                break;
            case  "name" :
                flower.setName(text);
                break;
            case "instructions":
                flower.setInstructions(text);
                break;
            case "price":
                flower.setPrice(Double.valueOf(text));
                break;
            case "photo":
                flower.setPhoto(text);
                break;
            default: break;
        }
    }

    private void handleStartTag(String tagName) {
        if ("product".equals(tagName)){
            flower=new Flower();
            flowerList.add(flower);

        }else {
            tag=tagName;
        }


    }
}
