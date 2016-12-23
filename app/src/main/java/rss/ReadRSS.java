package rss;


import org.jsoup.Jsoup;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.NodeList;

import java.io.BufferedReader;


import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import model.ItemRSS;

/**
 * Created by Nhozip on 6/13/2016.
 */
public class ReadRSS {
    private static String TAG_TITLE = "title";
    private static String TAG_LINK = "link";
    private static String TAG_DESRIPTION = "description";
    private static String TAG_ITEM = "item";
    private static String TAG_PUB_DATE = "pubDate";


    public ArrayList<ItemRSS> getData(String url) {
        ArrayList<ItemRSS> itemRSSes = new ArrayList<>();
        XMLDOMParser parser = new XMLDOMParser();
        Document document = parser.getDomElement(getDataFromURL(url));
        NodeList nodeList = document.getElementsByTagName(TAG_ITEM);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            String title = parser.getValue(element, TAG_TITLE);
            String link = parser.getValue(element, TAG_LINK);
            String description = parser.getValue(element, TAG_DESRIPTION);
            String pubDate = parser.getValue(element, TAG_PUB_DATE);

            org.jsoup.nodes.Document docHtml = Jsoup.parse(description);
            String img = docHtml.select("img").attr("src");

//            int vtr=description.indexOf("</br>");
//            String des =description.substring(vtr+5);
            itemRSSes.add(new ItemRSS(title, link,pubDate,img));
        }

        return itemRSSes;
    }


    public String getDataFromURL(String theUrl) {
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(theUrl);

            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

//
//    public Document getDomElement(String xml) {
//        Document doc = null;
//        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//        try {
//
//            DocumentBuilder db = dbf.newDocumentBuilder();
//
//            InputSource is = new InputSource();
//            is.setCharacterStream(new StringReader(xml));
//            doc = (Document) db.parse(is);
//
//        } catch (ParserConfigurationException e) {
//            Log.e("Error: ", e.getMessage());
//            return null;
//        } catch (SAXException e) {
//            Log.e("Error: ", e.getMessage());
//            return null;
//        } catch (IOException e) {
//            Log.e("Error: ", e.getMessage());
//            return null;
//        }
//
//        return doc;
//    }
//
//    public final String getElementValue(Node elem) {
//        Node child;
//        if (elem != null) {
//            if (elem.hasChildNodes()) {
//                for (child = elem.getFirstChild(); child != null; child = child
//                        .getNextSibling()) {
//                    if (child.getNodeType() == Node.TEXT_NODE
//                            || (child.getNodeType() == Node.CDATA_SECTION_NODE)) {
//                        return child.getNodeValue();
//                    }
//                }
//            }
//        }
//        return "";
//    }
//
//    public String getValue(Element item, String str) {
//        NodeList n = item.getElementsByTagName(str);
//        return this.getElementValue(n.item(0));
//    }
//    public ArrayList<ItemRSS> getRSSFeedItems(String rss_url) {
//        ArrayList<ItemRSS> itemsList = new ArrayList<ItemRSS>();
//        String rss_feed_xml;
//
//        rss_feed_xml = this.getDataFromURL(rss_url);
//
//        if (rss_feed_xml != null) {
//            try {
//                Document doc = this.getDomElement(rss_feed_xml);
//                NodeList nodeList = doc.getElementsByTagName(TAG_CHANNEL);
//                Element e = (Element) nodeList.item(0);
//
//                // Getting items array
//                NodeList items = e.getElementsByTagName(TAG_ITEM);
//
//                // looping through each item
//                for (int i = 0; i < items.getLength(); i++) {
//                    Element e1 = (Element) items.item(i);
//
//                    String title = this.getValue(e1, TAG_TITLE);
//                    String link = this.getValue(e1, TAG_LINK);
//                    String description = this.getValue(e1, TAG_DESRIPTION);
//                    String pubdate = this.getValue(e1, TAG_PUB_DATE);
//
//                    org.jsoup.nodes.Document docHtml = Jsoup.parse(description);
//                    Elements imgEle = docHtml.select("img");
//                    String img = imgEle.attr("src");
//
//                    String detail = "";
//
//
//
//                    ItemRSS rssItem = new ItemRSS(title, link, detail,
//                            pubdate, img);
//
//                    // adding item to list
//                    itemsList.add(rssItem);
//                }
//            } catch (Exception e) {
//                // Check log for errors
//                e.printStackTrace();
//            }
//        }
//
//        // return item list
//        return itemsList;
//    }

}
