import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) {
        int start = 217;
        int finish = 1839;
        String url = "link";

        Spider spider = new Spider();
        spider.findTheseWords();

        /*FIRST METHOD: GIVE START AND FINISH SET*/
//        for (int i = start; i < finish; i++) {
//            String modifyURl = url + i;
//            spider.readUrl(modifyURl);
//        }

        /*SECOND METHOD: GIVE ONLY FIRST VALUE*/
        int count = 0;
        while (true) {
            if (spider.nextPath.size() != 0) {
            if (spider.nextPath.get(spider.nextPath.size() - 1) == "false") {
                break;
            }
            }

            if (count == 0) {
                spider.readModifiedUrl("link");
                count++;
                continue;
            }
            spider.readModifiedUrl("link" + spider.nextPath.get(spider.nextPath.size() - 1));
        }

        try {
            PrintWriter writer = new PrintWriter("name.txt", "UTF-8");
            for (int j = 0; j < spider.linksToRead.size(); j++) {
                writer.println(spider.linksToRead.get(j).toString());
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
