import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

public class Spider {

    public List<Person> linksToRead = new ArrayList<Person>();
    public List<String> wordsToFind = new ArrayList<String>();
    public List<String> nextPath = new ArrayList<String>();

//    Words to find
    public void findTheseWords() {
        wordsToFind.add("to find");
    }

//    Method one with set start to finish
    public void readUrl(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            String bodyText = doc.body().text();
            int count = 0;
            Person peopleInvolved = null;
            for (int i = 0; i < wordsToFind.size(); i++) {
                if (bodyText.toLowerCase().contains(wordsToFind.get(i).toLowerCase())) {
                    if (count == 0) {
                        peopleInvolved = new Person(wordsToFind.get(i), url);
                        count++;
                    } else {
                        peopleInvolved.setNames(wordsToFind.get(i));
                    }
                }
            }
            if (peopleInvolved != null) {
                System.out.println(peopleInvolved.toString());
                linksToRead.add(peopleInvolved);
            }
        } catch (Exception e) {
            System.out.println("Crashed at " + url);
            e.printStackTrace();
        }

    }

//    Method two: only start given
    public void readModifiedUrl(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            String nextURL = doc.select("body > div.main > div > div > div > div.col-lg-9.col-md-8 " +
                    "> div.section > div > div.top-bar-area > ul > li.next > a").attr("href");

            if (nextURL.isEmpty()) {
                nextPath.add("false");
            } else {
                nextPath.add(nextURL);
            }

            String bodyText = doc.body().text();
            int count = 0;
            Person peopleInvolved = null;
            for (int i = 0; i < wordsToFind.size(); i++) {
                if (bodyText.toLowerCase().contains(wordsToFind.get(i).toLowerCase())) {
                    if (count == 0) {
                        count++;
                        peopleInvolved = new Person(wordsToFind.get(i), url);
                    } else {
                        peopleInvolved.setNames(wordsToFind.get(i));
                    }
                }
            }
            if (peopleInvolved != null) {
                System.out.println(peopleInvolved.toString());
                linksToRead.add(peopleInvolved);
            }
        } catch (Exception e) {
            System.out.println("Crashed at " + url);
            e.printStackTrace();
        }
    }

}
