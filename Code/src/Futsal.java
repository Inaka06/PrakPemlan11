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

        void setTim(String tim) {
            this.tim = tim;
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

    static class compareTinggi implements Comparator<Pemain> {
        public int compare(Pemain a, Pemain b) {
            return a.tinggiBadan - b.tinggiBadan;
        }
    }

    static ArrayList<Pemain> sortByTinggi(ArrayList<Pemain> tim) {
        ArrayList<Pemain> sorted = new ArrayList<>(tim);
        Collections.sort(sorted, new compareTinggi());
        return sorted;
    }

    static class compareBerat implements Comparator<Pemain> {
        public int compare(Pemain a, Pemain b) {
            return a.beratBadan - b.beratBadan;
        }
    }

    static ArrayList<Pemain> sortByBerat(ArrayList<Pemain> tim) {
        ArrayList<Pemain> sorted = new ArrayList<>(tim);
        Collections.sort(sorted, new compareBerat());
        return sorted;
    }

    //method untuk nomor 2b
    static void cariTinggi(ArrayList<Pemain> tim, int target){ //karena binary search akan cuma ambil 1 objek yang sesuai dengan perintah pencarian, saya buat sendiri just in case ada lebih dari satu objek yang sesuai :D
        ArrayList<Pemain> hasil = new ArrayList<>(); //list sementara buat nyimpen hasil
        Collections.sort(tim, new compareTinggi()); //sort lagi karena entah kenapa agak buggy kalo abis ga nemu apa apa

        while(true){
            int index = Collections.binarySearch(tim, new Pemain(null, target, 0), new compareTinggi()); //masih pake binarysearch kok
            if(index < 0) break; //kalau ga nemu, break aja
            hasil.add(tim.get(index)); //masukin ke hasil
            tim.remove(index); //hapus dari list awal biar ga ditemuin lagi
        }

        System.out.println("\nJumlah pemain dengan tinggi " + target + " yang ditemukan ada: " + hasil.size());

        for(Pemain pemain : hasil){
            System.out.println(pemain.tim + " - Tinggi Badan: " + pemain.tinggiBadan + ", Berat Badan: " + pemain.beratBadan);
        }

        tim.addAll(hasil); //balikin ke list awal

    }

    static void cariBerat(ArrayList<Pemain> tim, int target){ //sama aja kaya cariTinggi, tapi buat berat
        ArrayList<Pemain> hasil = new ArrayList<>();
        Collections.sort(tim, new compareBerat());

        while(true){
            int index = Collections.binarySearch(tim, new Pemain(null, 0, target), new compareBerat()); //disininya aja diganti
            if(index < 0) break;
            hasil.add(tim.get(index));
            tim.remove(index);
        }

        System.out.println("\nJumlah pemain dengan berat " + target + " yang ditemukan ada: " + hasil.size());

        for(Pemain pemain : hasil){
            System.out.println(pemain.tim + " - Tinggi Badan: " + pemain.tinggiBadan + ", Berat Badan: " + pemain.beratBadan);
        }

        tim.addAll(hasil);

    }

    static void cekKesamaan(ArrayList<Pemain> timA, ArrayList<Pemain> timB) {
        Collections.sort(timB, new compareTinggi());
        boolean tinggiSama = false;

        for (Pemain pemainA : timA) {
            int index = Collections.binarySearch(timB, new Pemain(null, pemainA.tinggiBadan, 0), new compareTinggi());
            if (index >= 0) {
                tinggiSama = true;
                break;
            }
        }

        Collections.sort(timB, new compareBerat());
        boolean beratSama = false;
        for (Pemain pemainA : timA) {
            int index = Collections.binarySearch(timB, new Pemain(null, 0, pemainA.beratBadan), new compareBerat());

            if (index >= 0) {
                beratSama = true;
                break;
            }
        }

        System.out.println();

        if (tinggiSama) {
            System.out.println("Ada pemain dengan tinggi yang sama");
        }
        else {
            System.out.println("Tidak ada pemain dengan tinggi yang sama");
        }

        if (beratSama) {
            System.out.println("Ada pemain dengan berat yang sama");
        }
        else {
            System.out.println("Tidak ada pemain dengan berat yang sama");
        }
    }

    static void main() {
        ArrayList<Pemain> timA = buatTim(dataTimA, "A");
        ArrayList<Pemain> timB = buatTim(dataTimB, "B");

        //1. Dengan program java, urutkan data pemain diantara kedua tim tersebut:
        //a. Berdasarkan Tinggi Badannya secara Ascending/menaik dan Descending/menurun
        ArrayList<Pemain> timATinggiAsc = sortByTinggi(timA);
        ArrayList<Pemain> timATinggiDesc = new ArrayList<>(timATinggiAsc);
        Collections.reverse(timATinggiDesc);

        ArrayList<Pemain> timBTinggiAsc = sortByTinggi(timB);
        ArrayList<Pemain> timBTinggiDesc = new ArrayList<>(timBTinggiAsc);
        Collections.reverse(timBTinggiDesc);


        System.out.println("Tim A Berdasarkan Tinggi(Ascending):");
        for (Pemain pemain : timATinggiAsc) {
            System.out.println(pemain.tim + " - Tinggi Badan: " + pemain.tinggiBadan + ", Berat Badan: " + pemain.beratBadan);
        }

        System.out.println("\nTim A Berdasarkan Tinggi(Descending):");
        for (Pemain pemain : timATinggiDesc) {
            System.out.println(pemain.tim + " - Tinggi Badan: " + pemain.tinggiBadan + ", Berat Badan: " + pemain.beratBadan);
        }

        System.out.println("\nTim B Berdasarkan Tinggi(Ascending):");
        for (Pemain pemain : timBTinggiAsc) {
            System.out.println(pemain.tim + " - Tinggi Badan: " + pemain.tinggiBadan + ", Berat Badan: " + pemain.beratBadan);
        }

        System.out.println("\nTim B Berdasarkan Tinggi(Descending):");
        for (Pemain pemain : timBTinggiDesc) {
            System.out.println(pemain.tim + " - Tinggi Badan: " + pemain.tinggiBadan + ", Berat Badan: " + pemain.beratBadan);
        }

        //b.Berdasarkan Berat Badannya secara Ascending/menaik dan Descending/menurun

        ArrayList<Pemain> timABeratAsc = sortByBerat(timA);
        ArrayList<Pemain> timABeratDesc = new ArrayList<>(timABeratAsc);
        Collections.reverse(timABeratDesc);

        ArrayList<Pemain> timBBeratAsc = sortByBerat(timB);
        ArrayList<Pemain> timBBeratDesc = new ArrayList<>(timBBeratAsc);
        Collections.reverse(timBBeratDesc);

        System.out.println("\nTim A Berdasarkan Berat (Ascending):");
        for (Pemain pemain : timABeratAsc) {
            System.out.println(pemain.tim + " - Tinggi Badan: " + pemain.tinggiBadan + ", Berat Badan: " + pemain.beratBadan);
        }

        System.out.println("\nTim A Berdasarkan Berat (Descending):");
        for (Pemain pemain : timABeratDesc) {
            System.out.println(pemain.tim + " - Tinggi Badan: " + pemain.tinggiBadan + ", Berat Badan: " + pemain.beratBadan);
        }

        System.out.println("\nTim B Berdasarkan Berat (Ascending):");
        for (Pemain pemain : timBBeratAsc) {
            System.out.println(pemain.tim + " - Tinggi Badan: " + pemain.tinggiBadan + ", Berat Badan: " + pemain.beratBadan);
        }

        System.out.println("\nTim B Berdasarkan Berat (Descending):");
        for (Pemain pemain : timBBeratDesc) {
            System.out.println(pemain.tim + " - Tinggi Badan: " + pemain.tinggiBadan + ", Berat Badan: " + pemain.beratBadan);
        }

        //c. Cari tinggi badan dan berat badan tertinggi dan terendah dari kedua tim


        System.out.println("\nTertinggi:");
        System.out.println("Tim A - Tinggi Badan Tertinggi : " + timATinggiDesc.get(0).tinggiBadan);
        System.out.println("Tim B - Tinggi Badan Tertinggi : " + timBTinggiDesc.get(0).tinggiBadan);
        System.out.println("Tim A - Berat Badan Tertinggi  : " + timABeratDesc.get(0).beratBadan);
        System.out.println("Tim B - Berat Badan Tertinggi  : " + timBBeratDesc.get(0).beratBadan);

        System.out.println("\nTerendah");
        System.out.println("Tim A - Tinggi Badan Terendah : " + timATinggiAsc.get(0).tinggiBadan);
        System.out.println("Tim B - Tinggi Badan Terendah : " + timBTinggiAsc.get(0).tinggiBadan);
        System.out.println("Tim A - Berat Badan Terendah  : " + timABeratAsc.get(0).beratBadan);
        System.out.println("Tim B - Berat Badan Terendah  : " + timBBeratAsc.get(0).beratBadan);

        //d. copy tim b ke tim c
        ArrayList<Pemain> timC = new ArrayList<>(
                Collections.nCopies(timB.size(), null)
        );
        Collections.copy(timC, timB); //bisa copy gini, bisa copy kaya line 100-106, tapi sama sama shallow copy, alias copy by reference.

        System.out.println("\nHasil copy Tim B ke Tim C");
        for (Pemain pemain : timC) {
            System.out.println(pemain.tim + " - Tinggi Badan: " + pemain.tinggiBadan + ", Berat Badan: " + pemain.beratBadan);
        }

        //2. Buatlah implementasi Binary Search dalam program java berdasarkan kondisi berikut:
        //a. Implementasikan ArrayList untuk menyimpan data tim A dan tim B dalam bentuk ArrayList terpisah.
        //Jawaban(?) : Datanya sudah dalam bentuk ArrayList dari awal dan terpisah, jadi tidak ada perubahan.

        //b. Dari data tim B, dicari jumlah pemain yang mempunyai tinggi badan 168 cm dan 160 cm.
        cariTinggi(timB, 168);
        cariTinggi(timB, 160);

        //c. Dari data tim A, dicari jumlah pemain yang mempunyai berat badan 56 kg dan 53 kg.
        cariBerat(timA, 56);
        cariBerat(timA, 53);

        //d.Ingin diketahui apakah pemain di Tim A ada yang mempunyai tinggi badan atau berat badan yang sama dengan pemain di Tim B?
        cekKesamaan(timA, timB);
    }
}
