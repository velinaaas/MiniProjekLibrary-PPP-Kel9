import java.util.ArrayList;
import java.util.Scanner;

abstract class Buku {
    protected String judul;
    protected String penulis;

    public abstract void infoBuku();
}

class BukuFiksi extends Buku {
    private String genre;

    public BukuFiksi(String judul, String penulis, String genre) {
        this.judul = judul;
        this.penulis = penulis;
        this.genre = genre;
    }

    @Override
    public void infoBuku() {
        System.out.println("Buku Fiksi: " + judul + " oleh " + penulis + " [Genre: " + genre + "]");
    }
}

class Jurnal extends Buku {
    private String doi;

    public Jurnal(String judul, String penulis, String doi) {
        this.judul = judul;
        this.penulis = penulis;
        this.doi = doi;
    }

    @Override
    public void infoBuku() {
        System.out.println("Jurnal: " + judul + " oleh " + penulis + " [DOI: " + doi + "]");
    }
}

abstract class BukuFactory {
    public abstract Buku buatBuku(String judul, String penulis, String dataTambahan);
}

class BukuFiksiFactory extends BukuFactory {
    @Override
    public Buku buatBuku(String judul, String penulis, String genre) {
        return new BukuFiksi(judul, penulis, genre);
    }
}


class JurnalFactory extends BukuFactory {
    @Override
    public Buku buatBuku(String judul, String penulis, String doi) {
        return new Jurnal(judul, penulis, doi);
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Buku> daftarBuku = new ArrayList<>();

        int pilihan;
        do {
            System.out.println("\n=== MENU SISTEM BUKU ===");
            System.out.println("1. Tambah Buku Fiksi");
            System.out.println("2. Tambah Jurnal");
            System.out.println("3. Lihat Semua Buku");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = sc.nextInt();
            sc.nextLine(); // buang newline

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan Judul: ");
                    String judulFiksi = sc.nextLine();
                    System.out.print("Masukkan Penulis: ");
                    String penulisFiksi = sc.nextLine();
                    System.out.print("Masukkan Genre: ");
                    String genre = sc.nextLine();

                    BukuFactory fiksiFactory = new BukuFiksiFactory();
                    Buku bukuFiksi = fiksiFactory.buatBuku(judulFiksi, penulisFiksi, genre);
                    daftarBuku.add(bukuFiksi);

                    System.out.println(">> Buku Fiksi berhasil ditambahkan!");
                    break;

                case 2:
                    System.out.print("Masukkan Judul: ");
                    String judulJurnal = sc.nextLine();
                    System.out.print("Masukkan Penulis: ");
                    String penulisJurnal = sc.nextLine();
                    System.out.print("Masukkan DOI: ");
                    String doi = sc.nextLine();

                    BukuFactory jurnalFactory = new JurnalFactory();
                    Buku jurnal = jurnalFactory.buatBuku(judulJurnal, penulisJurnal, doi);
                    daftarBuku.add(jurnal);

                    System.out.println(">> Jurnal berhasil ditambahkan!");
                    break;

                case 3:
                    System.out.println("\n=== DAFTAR BUKU ===");
                    if (daftarBuku.isEmpty()) {
                        System.out.println("Belum ada buku yang ditambahkan.");
                    } else {
                        for (int i = 0; i < daftarBuku.size(); i++) {
                            System.out.print((i + 1) + ". ");
                            daftarBuku.get(i).infoBuku();
                        }
                    }
                    break;

                case 0:
                    System.out.println("Terima kasih! Program selesai.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 0);

        sc.close();
    }
}
