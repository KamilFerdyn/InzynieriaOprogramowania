package vod.repository.mem;

import vod.model.Art;
import vod.model.Theatre;
import vod.model.Director;

import java.util.ArrayList;
import java.util.List;

class SampleData {

    static List<Theatre> theatres = new ArrayList<>();

    static List<Director> directors = new ArrayList<>();

    static List<Art> arts = new ArrayList<>();

    static {

        Director smarzowski = new Director(1, "Wojciech", "Smarzowski");
        Director vega = new Director(2, "Patryk", "Vega");
        Director wajda = new Director(3, "Andrzej", "Wajda");
        Director skolimowski = new Director(4, "Jerzy", "Skolimowski");

        Art wolyn = new Art(1, "Wolyn", "https://ocdn.eu/pulscms-transforms/1/D0gk9kuTURBXy8zYzFhMDRhZS1jOGRiLTQxN2YtOTcwYy1iNjRjZDBkMjc4MDYuanBlZ5GTBc0DFM0BvIGhMAU", smarzowski, (float) 4.1);
        Art wesele = new Art(2, "Wesele", "https://fwcdn.pl/fpo/40/98/124098/7521214.6.jpg", smarzowski, (float) 4.3);

        Art polityka = new Art(3, "Polityka", "https://i.iplsc.com/-/00094J03E94SMPSS-C122.jpg", vega, (float) 3.9);
        Art pitbul = new Art(4, "Pitbul", "https://bi.im-g.pl/im/5b/9b/12/z19510363V,-Pitbull--Nowe-porzadki---rez--Patryk-Vega--plakat.jpg", vega, (float) 3.1);

        Art katyn = new Art(5, "Katyn", "http://www.gokmichalowo.pl/imprezy2007/katyn/plakat_maly.jpg", wajda, (float) 4.7);
        Art tatarak = new Art(6, "Tatarak", "http://gapla.fn.org.pl/public/cache/P21829-483x700.jpg", wajda, (float) 4.4);

        Art essential = new Art(7, "Essential killing", "https://m.media-amazon.com/images/M/MV5BNTE5NjAxMTEzNl5BMl5BanBnXkFtZTcwMjYzMDQ0Ng@@._V1_UX182_CR0,0,182,268_AL_.jpg", skolimowski, (float) 4.9);
        Art ferdydurke = new Art(8, "Ferdydurke", "http://gapla.fn.org.pl/public/cache/P19423-483x700.jpg", skolimowski, (float) 4.3);

        bind(wolyn, smarzowski);
        bind(wesele, smarzowski);

        bind(polityka, vega);
        bind(pitbul, vega);

        bind(katyn, wajda);
        bind(tatarak, wajda);

        bind(essential, skolimowski);
        bind(ferdydurke, skolimowski);

        Theatre kinoteka = new Theatre(1, "Kinoteka", "https://www.kinoteka.pl/img/logo.png");
        Theatre podBaranami = new Theatre(2, "Kino pod Baranami", "http://www.festiwalfilmuniemego.pl/wp-content/uploads/2015/11/Kino-pod-Baranami.png");
        Theatre noweHoryzonty = new Theatre(3, "Kino Nowe Horyzonty", "https://i2.wp.com/garretreza.pl/wp-content/uploads/2018/07/nh.jpg");
        Theatre zak = new Theatre(4, "Kino Zak", "https://static2.s-trojmiasto.pl/zdj/c/n/19/2276/250x0/2276445.jpg");

        bind(kinoteka, wesele);
        bind(noweHoryzonty, wesele);
        bind(noweHoryzonty, wolyn);
        bind(noweHoryzonty, polityka);

        bind(kinoteka, tatarak);
        bind(zak, tatarak);
        bind(zak, essential);
        bind(podBaranami, essential);
        bind(podBaranami, polityka);

        arts.add(wolyn);
        arts.add(wesele);
        arts.add(polityka);
        arts.add(pitbul);
        arts.add(katyn);
        arts.add(tatarak);
        arts.add(essential);
        arts.add(ferdydurke);

        directors.add(smarzowski);
        directors.add(vega);
        directors.add(wajda);
        directors.add(skolimowski);

        theatres.add(kinoteka);
        theatres.add(podBaranami);
        theatres.add(noweHoryzonty);
        theatres.add(zak);

    }

    private static void bind(Theatre c, Art m) {
        c.addArt(m);
        m.addTheatre(c);
    }

    private static void bind(Art m, Director d) {
        d.addArt(m);
        m.setDirector(d);
    }

}
