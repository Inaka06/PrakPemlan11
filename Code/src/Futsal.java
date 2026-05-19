import java.lang.reflect.Array;
import java.util.*;

public class Futsal {
    static class Pemain {
        String tim;
        int tinggiBadan;
        int beratBadan;

        Pemain(String tim, int tinggi, int berat) {
            this.tim = tim;
            this.tinggiBadan = tinggi;
            this.beratBadan = berat;
        }
    }

    static int[][] dataTimA = {
            {168, 50}, {170, 60}, {165, 56}, {168, 55}, {172, 60},
            {170, 70}, {169, 66}, {165, 56}, {171, 72}, {166, 56}
    };

    static int[][] dataTimB = {
            {170, 66}, {167, 60}, {165, 59}, {166, 58}, {168, 58},
            {175, 71}, {172, 68}, {171, 68}, {168, 65}, {169, 60}
    };

    static ArrayList<Pemain> buatTim(int[][] dataTim, String namaTim) {
        ArrayList<Pemain> tim = new ArrayList<>();
        for (int i = 0; i < dataTim.length; i++)
            tim.add(new Pemain(namaTim, dataTim[i][0], dataTim[i][1]));
        return tim;
    }

    static class compareTinggi implements Comparator<Pemain>{
        public int compare(Pemain a, Pemain b){
            return a.tinggiBadan - b.tinggiBadan;
        }
    }

    static ArrayList<Pemain> sortByTinggi(ArrayList<Pemain> tim){
        Collections.sort(tim, new compareTinggi());
        return tim;
    }

    static void main() {
        ArrayList<Pemain> timA = buatTim(dataTimA, "A");
        ArrayList<Pemain> timB = buatTim(dataTimB, "B");

        //1. Dengan program java, urutkan data pemain diantara kedua tim tersebut:
        //a. Berdasarkan Tinggi Badannya secara Ascending/menaik dan Descending/menurun
        ArrayList<Pemain> timASortAsc = sortByTinggi(timA);
        ArrayList<Pemain> timASortDesc = timASortAsc;
        Collections.reverse(timASortDesc);
        ArrayList<Pemain> timBSortAsc = sortByTinggi(timB);
        ArrayList<Pemain> timBSortDesc = Collections.reverse(timBSortAsc);

        System.out.println("Tim A (Ascending):");
        for (Pemain pemain : timASortAsc) {
            System.out.println(pemain.tim + " - Tinggi Badan: " + pemain.tinggiBadan + ", Berat Badan: " + pemain.beratBadan);
        }

        System.out.println("\nTim A (Descending):");
        for (Pemain pemain : timASortDesc) {
            System.out.println(pemain.tim + " - Tinggi Badan: " + pemain.tinggiBadan + ", Berat Badan: " + pemain.beratBadan);
        }

        System.out.println("\nTim B (Ascending):");
        for (Pemain pemain : timBSortAsc) {
            System.out.println(pemain.tim + " - Tinggi Badan: " + pemain.tinggiBadan + ", Berat Badan: " + pemain.beratBadan);
        }

        System.out.println("\nTim B (Descending):");
        for (Pemain pemain : timBSortDesc) {
            System.out.println(pemain.tim + " - Tinggi Badan: " + pemain.tinggiBadan + ", Berat Badan: " + pemain.beratBadan);
        }

    }
}
