package mhci.teamsix.ugs.incampus.asynctask;

import mhci.teamsix.ugs.incampus.util.Event;
import mhci.teamsix.ugs.incampus.util.FoodStore;

/**
 * Created by Vincey on 8/3/2017.
 */

public class ForStaticDataToTest {
    public ForStaticDataToTest(){

    }
    public Event getStaticEventData(){
        String[] imgList = {"http://thesmartlocal.com/media/reviews/photos/original/02/fb/78/f-club-76-1452225427.jpg",
                "http://djanemag.com/sites/default/files/styles/full_photo/public/reiko3.jpg?itok=qGD2Hh-j",
                "https://scontent-sin6-1.xx.fbcdn.net/v/t31.0-8/12671710_208292202875875_8232458109346018496_o.jpg?oh=e1eaee440e1e91334e5caf0891b08988&oe=59303474",
                "https://www.edmhive.com/wp-content/uploads/2016/02/fclub-singapore-1024x576.jpg",
                "https://scontent-sin6-1.xx.fbcdn.net/v/t31.0-8/12474025_208292539542508_5745108681417313756_o.jpg?oh=889641833bdb717c267406922825ab9b&oe=5962F3D8"};
        String description = "Step into the spotlight at the world's first FashionTV club.\n" +
                "\n" +
                "Experience the glitz and glamour and get a front row seat to international events, fashion shows and live entertainment.\n" +
                "\n" +
                "Indulge in our premium bottle service with the finest champagne and spirits.\n" +
                "\n" +
                "Designed to impress, f. Club features an extravagant interior inspired by Paris renaissance baroque styling, a tunnel entrance encrusted with 10,000 crystals, rich velvet couches and handmade oil paintings.\n" +
                "\n" +
                "Featuring two party areas: the Ruby Room, which plays Hip-Hop, R&B and chart-topping remixes, and the Diamond Hall, which plays Vocal House and Progressive House music.\n" +
                "\n" +
                "f. CLUB- AN ULTRA-CHIC VENUE IN THE HEART OF SINGAPORE'S PARTY DISTRICT";
        Event event = new Event("2", "[ALPHA] Drink till Drop", "31/12/2019", "fClub", imgList, description);
        return event;
    }

    public FoodStore getFoodStore(){
        String[] imageList = {"http://2.bp.blogspot.com/-cU-N5Pp4uKI/UghnjANInPI/AAAAAAAAASs/WP6MpHmR8Yk/s1600/koufu1.jpg",
        "http://republicpolytechnicpolicy.weebly.com/uploads/5/1/7/9/51790791/191171594_orig.jpg",
        "http://3.bp.blogspot.com/-sZGl4aB0TP0/Ugho8bIeMxI/AAAAAAAAATM/opzhqgckeMI/s1600/lawn+(1).jpg",
        "http://4.bp.blogspot.com/-kk8USGjwHNg/Uf8tMfs_8XI/AAAAAAAAACs/cATDk5Z5R0M/s1600/20130805_112828%5B1%5D.jpg",
        "http://3.bp.blogspot.com/-4ahXYbCKChU/Ughp_pB1cII/AAAAAAAAAUs/76c06QunByQ/s1600/back+(6).jpg",
        "http://3.bp.blogspot.com/-_RrZ3S5bVCw/U5FMMVE4xWI/AAAAAAAAADI/DguNcRB8HEw/s1600/IMG_4859.JPG"};

        return new FoodStore("10", "FOODIES IN RP (TEST DATA)", "INFINITE RANGE", "THIS IS TO SHOW THAT CAROUSEL VIEW WORKS HERE TOO. HOWEVER BEACON IS FOREVER DISABLED ! ", imageList, "unknown", "unknown");
    }
}
